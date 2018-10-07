package org.foresee.binhu.model;

import java.sql.Timestamp;
import java.util.List;

public class Prescript {
	private int id;
	private String name;
	private List<Component> components;
	private String func;
	private Timestamp updateTime = new Timestamp(System.currentTimeMillis());
	
	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Component> getComponents() {
		return components;
	}

	public void setComponents(List<Component> components) {
		this.components = components;
	}

	public String getFunc() {
		return func;
	}

	public void setFunc(String func) {
		this.func = func;
	}

}
