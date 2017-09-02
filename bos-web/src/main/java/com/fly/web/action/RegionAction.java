package com.fly.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fly.bos.entity.Region;
import com.fly.bos.service.IRegionService;
import com.fly.bos.utils.PinYin4jUtils;

@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IRegionService regionService;
	
	private String q;
	
	// 接受文件的对象;
	private File regionFile;
	
	/**
	 * 实现区域设置中的导入功能
	 * @return
	 * @throws Exception 
	 */
	public String importFile() throws Exception {
		List<Region> regionList = new ArrayList<Region>();
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(regionFile));
		HSSFSheet sheet = hssfWorkbook.getSheetAt(0); // 不会写死吗 ？？
		for (Row row : sheet) {
			int num = row.getRowNum();
			if (num == 0)	continue; // 获取行号

			String id = row.getCell(0).getStringCellValue();
			String province = row.getCell(1).getStringCellValue();
			String city = row.getCell(2).getStringCellValue();
			String district = row.getCell(3).getStringCellValue();
			String postcode = row.getCell(4).getStringCellValue();
			
			// 封装区域简码
			String province1 = province.substring(0, province.length() - 1);
			String city1 = city.substring(0, city.length() - 1);
			String district1 = district.substring(0, district.length() - 1);
			
			String info = province1 + city1 + district1;
			String[] infoArr = PinYin4jUtils.getHeadByString(info);
			String shortcode = StringUtils.join(infoArr);
			// 封装城市编码
			String citycode = PinYin4jUtils.hanziToPinyin(city1, "");
			// 包装到一个区域对象
			Region region = new Region(id, province, city, district, postcode, shortcode, citycode, null);
			regionList.add(region);
		}
		regionService.saveBatch(regionList);
		return NONE;
	}
	
	/**
	 * 区域设置的分页查询
	 * @return
	 * @param subareas 结局主键相互引用出现死循环从而前台页面无法正常显示数据的情况
	 * 这种情况下不适合用于前台需要展示subareas数据，如果需要展示就要在subarea.hbm.xml映射文件中将lazy属性设置为false；
	 */
	public String pageQuery() {
		regionService.pageQuery(pageBean);
		java2Json(pageBean, new String[]{"currentPage", "criteria", "pageSize", "subareas"});
		return NONE;
	}
	
	/**
	 * 区域设置添加功能
	 * @return
	 */
	public String add() {
		regionService.add(t);
		return LIST;
	}
	
	/**
	 * 区域设置删除功能
	 * @return
	 */
	public String delete() {
		regionService.delete(t);
		return LIST;
	}
	
	/**
	 * 区域设置修改功能
	 * @return
	 */
	public String edit() {
		regionService.edit(t);
		return LIST;
	}
	
	/**
	 * 区域设置查询所有功能
	 * @return
	 */
	public String list() {
		List<Region> regionList = null;
		if (q != null){
			regionList = regionService.findByQ(q);
		} else {
			regionList = regionService.findAll(t);
		}
		java2Json(regionList, new String[]{"subareas", "citycode", "shortcode", "postcode", "province", "city", "district"});
		return NONE;
	}
	
	public void setRegionFile(File regionFile) {
		this.regionFile = regionFile;
	}

	public void setQ(String q) {
		this.q = q;
	}
	
}
