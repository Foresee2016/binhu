package org.foresee.binhu.model;

import java.sql.Timestamp;

public class UpdateInfo {
	private int mId;
	private Timestamp mUpdateTime =new Timestamp(System.currentTimeMillis());
	private String mPart;
	private int mDataSize;
	private int mDataCount;
	
	public UpdateInfo() {
		super();
	}
	public UpdateInfo(Timestamp updateTime, String part, int dataSize, int dataCount) {
		super();
		this.mUpdateTime = updateTime;
		this.mPart = part;
		this.mDataSize = dataSize;
		this.mDataCount = dataCount;
	}
	public int getId() {
		return mId;
	}
	public void setId(int id) {
		this.mId = id;
	}
	public Timestamp getUpdateTime() {
		return mUpdateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.mUpdateTime = updateTime;
	}
	public String getPart() {
		return mPart;
	}
	public void setPart(String part) {
		this.mPart = part;
	}
	public int getDataSize() {
		return mDataSize;
	}
	public void setDataSize(int dataSize) {
		this.mDataSize = dataSize;
	}
	public int getDataCount() {
		return mDataCount;
	}
	public void setDataCount(int data_count) {
		this.mDataCount = data_count;
	}
	
}
