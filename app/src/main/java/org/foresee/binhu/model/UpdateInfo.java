package org.foresee.binhu.model;

import java.sql.Timestamp;

public class UpdateInfo {
	private int id;
	private Timestamp updateTime=new Timestamp(System.currentTimeMillis());
	private String part;
	private int dataSize;
	private int dataCount;
	
	public UpdateInfo() {
		super();
	}
	public UpdateInfo(Timestamp updateTime, String part, int dataSize, int dataCount) {
		super();
		this.updateTime = updateTime;
		this.part = part;
		this.dataSize = dataSize;
		this.dataCount = dataCount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public int getDataSize() {
		return dataSize;
	}
	public void setDataSize(int dataSize) {
		this.dataSize = dataSize;
	}
	public int getDataCount() {
		return dataCount;
	}
	public void setDataCount(int data_count) {
		this.dataCount = data_count;
	}
	
}
