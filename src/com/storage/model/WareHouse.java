package com.storage.model;

import java.io.Serializable;

public class WareHouse implements Serializable{
	private static final long serialVersionUID = -7584199950881947803L;
	private int sto_id;
	private String sto_name;
	private String sto_type;
	private float sto_money;
	private String sto_addr;
	
	public WareHouse() {
		// TODO Auto-generated constructor stub
	}
	public WareHouse(int sto_id, String sto_name, String sto_type, float sto_money, String sto_addr) {
		super();
		this.sto_id = sto_id;
		this.sto_name = sto_name;
		this.sto_type = sto_type;
		this.sto_money = sto_money;
		this.sto_addr = sto_addr;
	}
	public int getSto_id() {
		return sto_id;
	}
	public void setSto_id(int sto_id) {
		this.sto_id = sto_id;
	}
	public String getSto_name() {
		return sto_name;
	}
	public void setSto_name(String sto_name) {
		this.sto_name = sto_name;
	}
	public String getSto_type() {
		return sto_type;
	}
	public void setSto_type(String sto_type) {
		this.sto_type = sto_type;
	}
	public float getSto_money() {
		return sto_money;
	}
	public void setSto_money(float sto_money) {
		this.sto_money = sto_money;
	}
	public String getSto_addr() {
		return sto_addr;
	}
	public void setSto_addr(String sto_addr) {
		this.sto_addr = sto_addr;
	}
}
