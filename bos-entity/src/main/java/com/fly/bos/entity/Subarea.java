package com.fly.bos.entity;

/**
 * Subarea entity. @author MyEclipse Persistence Tools
 */

public class Subarea implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// Fields
	private String id;
	private Region region;
	private Decidedzone decidedzone;
	private String addresskey;
	private String startnum;
	private String endnum;
	private String single;
	private String position;

	// Constructors

	/** default constructor */
	public Subarea() {
	}

	/** minimal constructor */
	public Subarea(String id) {
		this.id = id;
	}

	/** full constructor */
	public Subarea(String id, Region region, Decidedzone decidedzone, String addresskey, String startnum, String endnum,
			String single, String position) {
		this.id = id;
		this.region = region;
		this.decidedzone = decidedzone;
		this.addresskey = addresskey;
		this.startnum = startnum;
		this.endnum = endnum;
		this.single = single;
		this.position = position;
	}

	// Property accessors
	
	// 用于解决定区设置功能中添加操作时出现两个同名的id的情况
	public String getSubarea_id() {
		return id;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Decidedzone getDecidedzone() {
		return this.decidedzone;
	}

	public void setDecidedzone(Decidedzone decidedzone) {
		this.decidedzone = decidedzone;
	}

	public String getAddresskey() {
		return this.addresskey;
	}

	public void setAddresskey(String addresskey) {
		this.addresskey = addresskey;
	}

	public String getStartnum() {
		return this.startnum;
	}

	public void setStartnum(String startnum) {
		this.startnum = startnum;
	}

	public String getEndnum() {
		return this.endnum;
	}

	public void setEndnum(String endnum) {
		this.endnum = endnum;
	}

	public String getSingle() {
		return this.single;
	}

	public void setSingle(String single) {
		this.single = single;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}