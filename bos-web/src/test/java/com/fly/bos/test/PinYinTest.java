package com.fly.bos.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.fly.bos.utils.PinYin4jUtils;

public class PinYinTest {
	
	@Test
	public void pinyinTest() {
		String province = "安徽省";
		String city = "蚌埠市";
		String district = "淮上区";
		province = province.substring(0, province.length() - 1);
		city = city.substring(0, city.length() - 1);
		district = district.substring(0, district.length() - 1);
		String info = province + city + district;
		System.out.println(info);
		
		// 简码
		String[] headByString = PinYin4jUtils.getHeadByString(info);
		String join = StringUtils.join(headByString);
		System.out.println(join);
		
		// 城市编码
		String citycode = PinYin4jUtils.hanziToPinyin(city, "");
		System.out.println(citycode);
	}
}
