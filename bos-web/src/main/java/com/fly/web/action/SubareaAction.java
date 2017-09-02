package com.fly.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fly.bos.entity.Region;
import com.fly.bos.entity.Subarea;
import com.fly.bos.service.ISubareaService;
import com.fly.bos.utils.FileUtils;

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private ISubareaService subareaService;
	
	private String decidedzoneId;
	
	public String add() {
		subareaService.add(t);
		return LIST;
	}
	
	/**
	 * 带条件分页查询
	 * subarea --> region, decidedzone; region --> subareas; decidedzone --> subareas 出现互相引用从而造成死循环;
	 * @return
	 */
	public String pageQuery() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Subarea.class);
		String addresskey = t.getAddresskey();
		if (StringUtils.isNotBlank(addresskey)) {
			criteria.add(Restrictions.like("addresskey", "%" + addresskey + "%"));
		}
		
		Region region = t.getRegion();
		if (region != null) {
			String province = region.getProvince();
			String city = region.getCity();
			String district = region.getDistrict();
			// 取别名进行联合查询
			criteria.createAlias("region", "r");
			// 要进行非空判断
			if(StringUtils.isNotBlank(province)){
				criteria.add(Restrictions.like("r.province", "%"+province+"%"));
			}
			if(StringUtils.isNotBlank(city)){
				criteria.add(Restrictions.like("r.city", "%"+city+"%"));
			}
			if(StringUtils.isNotBlank(district)){
				criteria.add(Restrictions.like("r.district", "%"+district+"%"));
			}
		}
		pageBean.setCriteria(criteria);
		subareaService.pageQuery(pageBean);
		java2Json(pageBean, new String[]{"currentPage", "criteria", "pageSize", "decidedzone", "subareas"});
		return NONE;
	}
	
	/**
	 * 到处数据到指定位置的xls格式
	 * @return
	 * @throws IOException 
	 */
	public String export() throws IOException{
		// 1.获取所有分区数据
		List<Subarea> subList = subareaService.findAll(t);
		// 2.在内存中创建xls文件
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		// 2.1添加第一个行头信息
		HSSFSheet sheet = hssfWorkbook.createSheet("分区数据");
		HSSFRow headRow = sheet.createRow(0);
		headRow.createCell(0).setCellValue("分区编号");
		headRow.createCell(1).setCellValue("开始编号");
		headRow.createCell(2).setCellValue("结束编号");
		headRow.createCell(3).setCellValue("位置信息");
		headRow.createCell(4).setCellValue("省市区");
		
		for (Subarea subarea : subList) {
			// 遍历数据，添加每行的数据信息
			HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
			dataRow.createCell(0).setCellValue(subarea.getId());
			dataRow.createCell(1).setCellValue(subarea.getStartnum());
			dataRow.createCell(2).setCellValue(subarea.getEndnum());
			dataRow.createCell(3).setCellValue(subarea.getPosition());
			dataRow.createCell(4).setCellValue(subarea.getRegion().getName());
		}
		
		// 用IO流输出(一个流，两个头)
		// 一个流
		ServletOutputStream out = ServletActionContext.getResponse().getOutputStream();
		// 两个头，必须设置，不然浏览器不能解析
		String filename = "分区数据.xls";
		String mimeType = ServletActionContext.getServletContext().getMimeType(filename);
		ServletActionContext.getResponse().setContentType(mimeType);
		String agent = ServletActionContext.getRequest().getHeader("User-Agent");
		filename = FileUtils.encodeDownloadFilename(filename, agent);
		ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename=" + filename);
		hssfWorkbook.write(out);
		return NONE;
	}

	/**
	 * 查询没有关联的分区，即decidedzone_id为null的记录
	 * @return
	 */
	public String list() {
		List<Subarea> list = subareaService.findNotJoinArea();
		java2Json(list, new String[]{"region", "decidedzone"});
		return NONE;
	}
	
	/**
	 * 根据定区id查分区
	 * @return
	 */
	public String findByDecidedzoneId() {
		List<Subarea> list = subareaService.findByDecidedId(decidedzoneId);
		java2Json(list, new String[]{"decidedzone", "subareas"});
		return NONE;
	}
	
	/**
	 * 分组查询省份
	 * @return
	 */
	public String findSubareasGroupByProvince(){
		List<Object> list = subareaService.findSubareasGroupByProvince();
		java2Json(list, new String[]{});
		return NONE;
	}

	public void setDecidedzoneId(String decidedzoneId) {
		this.decidedzoneId = decidedzoneId;
	}
	
	
}







