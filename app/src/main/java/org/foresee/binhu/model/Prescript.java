package org.foresee.binhu.model;

import java.sql.Timestamp;
import java.util.List;

public class Prescript {
	private int mId;
	private String mName;
	private List<Component> mComponents;
	private String mFunc;
	private Timestamp mUpdateTime = new Timestamp(System.currentTimeMillis());

	public Prescript() {
	}

	public Prescript(String name) {
		this.mName = name;
	}

	public Timestamp getUpdateTime() {
		return mUpdateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
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
