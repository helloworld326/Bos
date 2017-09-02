package com.fly.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fly.bos.entity.Decidedzone;
import com.fly.bos.service.IDecidedzoneService;
import com.fly.crm_bos.Customer;
import com.fly.crm_bos.ICustomerService;

/**
 * @author 轻舞飞扬
 * @version
 * @date2017年8月25日
 * 
 */
@Controller
public class DecidedzoneAction extends BaseAction<Decidedzone> {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ICustomerService proxy;

	private List<Integer> cust_ids;

	@Autowired
	private IDecidedzoneService decidedzoneService;

	private String[] subarea_id;

	/**
	 * 定区添加功能
	 * 
	 * @return
	 */
	public String add() {
		System.out.println(t);
		decidedzoneService.save(t, subarea_id);
		return LIST;
	}

	/**
	 * 定区功能分页查询
	 * 
	 * @return
	 */
	public String pageQuery() {
		decidedzoneService.pageQuery(pageBean);
		java2Json(pageBean, new String[] { "currentPage", "criteria", "pageSize", "subareas", "decidedzones" });
		return NONE;
	}

	/**
	 * 查找为关联的定区
	 * 
	 * @return
	 */
	public String findNotAssociate() {
		List<Customer> list = proxy.findListNotAssociate();
		java2Json(list, new String[] {});
		return NONE;
	}

	/**
	 * 
	 * 查找已关联的定区
	 * 
	 * @return
	 */
	public String findAssociated() {
		List<Customer> list = proxy.findListAssociated(t.getId());
		System.out.println(t.getId());
		java2Json(list, new String[] {});
		return NONE;
	}

	/**
	 * 定区关联客户 
	 * @return
	 */
	public String assignCustomer() {
		proxy.assignCustomer(t.getId(), cust_ids);
		return LIST;
	}

	
	
	public void setSubarea_id(String[] subarea_id) {
		this.subarea_id = subarea_id;
	}

	public void setCust_ids(List<Integer> cust_ids) {
		this.cust_ids = cust_ids;
	}

}
