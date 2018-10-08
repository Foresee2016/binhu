package org.foresee.binhu.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Prescript {
	private int mId;
	private String mName;
	private List<Component> mComponents;
	private String mFunc;
	private Date mUpdateTime;

	public Prescript() {
	}

	public Prescript(String name) {
		this.mName = name;
		mComponents=new ArrayList<>();
	}

	public Date getUpdateTime() {
		return mUpdateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.mUpdateTime = updateTime;
	}

	public int getId() {
		return mId;
	}

	public void setId(int id) {
		this.mId = id;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		this.mName = name;
	}

	public List<Component> getComponents() {
		return mComponents;
	}

	public void setComponents(List<Component> components) {
		this.mComponents = components;
	}

	public String getFunc() {
		return mFunc;
	}

	public void setFunc(String func) {
		this.mFunc = func;
	}

}
