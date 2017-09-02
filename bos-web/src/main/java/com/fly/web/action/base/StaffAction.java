package com.fly.web.action.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fly.bos.entity.Staff;
import com.fly.bos.service.IStaffService;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IStaffService staffService;
	
	public String add() {
		try {
			staffService.add(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return LIST;
	}
	
}
