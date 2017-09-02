package com.fly.bos.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fly.bos.dao.IDecidedzoneDao;
import com.fly.bos.dao.INoticebillDao;
import com.fly.bos.dao.IWorkbillDao;
import com.fly.bos.entity.Decidedzone;
import com.fly.bos.entity.Noticebill;
import com.fly.bos.entity.User;
import com.fly.bos.entity.Workbill;
import com.fly.bos.service.INoticebillService;
import com.fly.bos.utils.BOSUtils;
import com.fly.crm_bos.ICustomerService;

@Service
@Transactional
public class NoticebillServiceImpl implements INoticebillService {
	@Autowired
	private INoticebillDao noticebillDao;
	@Autowired
	private ICustomerService customerService; // 远程调用crm服务接口
	@Autowired
	private IDecidedzoneDao decidedzoneDao;
	@Autowired
	private IWorkbillDao workbillDao;
	
	@Override
	public void add(Noticebill t) {
		User user = BOSUtils.getLoginUser();
		t.setUser(user); // 设置受理人
		noticebillDao.save(t);
		String pickaddress = t.getPickaddress();
		String decided_id = customerService.findDecidedIdByAddress(pickaddress);
		if (decided_id != null) {
			// 查询到了定区id，进行自动分单
			Decidedzone decidedzone = decidedzoneDao.findById(decided_id);
			t.setStaff(decidedzone.getStaff()); // 业务通知单关联取派员对象
			t.setOrdertype(Noticebill.ORDERTYPE_AUTO); // 自动分单
			
			/**
			 *  为取派员产生一个工单，保存工单，并发送短信通知！
			 */ 
			 Workbill workbill = new Workbill();
			 workbill.setAttachbilltimes(0);
			 workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));
			 workbill.setNoticebill(t);
			 workbill.setPickstate(Workbill.PICKSTATE_NO);
			 workbill.setRemark(t.getRemark());
			 workbill.setStaff(decidedzone.getStaff());
			 workbill.setType(Workbill.TYPE_1);
			 workbillDao.save(workbill);
			 
			 // 调用短信平台，发送短信通知
			 
		} else {
			// 人工分单
			t.setOrdertype(Noticebill.ORDERTYPE_MAN);
		}
	}
	
}
