package com.fly.web.action;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fly.bos.entity.Workordermanager;
import com.fly.bos.service.IWorkordermanagerService;

@Controller
@Scope("prototype")
public class WorkordermanagerAction extends BaseAction<Workordermanager> {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IWorkordermanagerService workordermanagerService;
	
	public String add() throws IOException {
		System.out.println("helloworld");
		String f = "1";
		try {
			workordermanagerService.save(t);
		} catch (Exception e) {
			f = "0";
			e.printStackTrace();
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().write(f);
		return NONE;
	}

}
