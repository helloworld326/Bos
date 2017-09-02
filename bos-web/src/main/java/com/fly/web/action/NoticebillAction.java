package com.fly.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fly.bos.entity.Noticebill;
import com.fly.bos.service.INoticebillService;
import com.fly.crm_bos.Customer;
import com.fly.crm_bos.ICustomerService;

@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ICustomerService customerService;
	@Autowired
	private INoticebillService noticebillService;
	
	public String findCustomerByTelephone() {
		Customer customer = customerService.findCustomerByTelephone(t.getTelephone());
		java2Json(customer, new String[]{});
		return NONE;
	}
	
	/**
	 * 保存一个业务通知单，并尝试自动分单
	 * @return
	 */
	public String add() {
		noticebillService.add(t);
		return LIST;
	}
	
}
