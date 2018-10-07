package org.foresee.binhu.model;

public class Component {
	private int mId;
	private int mPrescriptId;
	private String mMedicineName;
	private String mWeight;
	private String mProcess;

	public int getId() {
		return mId;
	}

	public void setId(int id) {
		this.mId = id;
	}

	public int getPrescriptId() {
		return mPrescriptId;
	}

	public void setPrescriptId(int prescriptId) {
		this.mPrescriptId = prescriptId;
	}

	public String getMedicineName() {
		return mMedicineName;
	}

	public void setMedicineName(String medicineName) {
		this.mMedicineName = medicineName;
	}

	public String getWeight() {
		return mWeight;
	}

	public void setWeight(String weight) {
		this.mWeight = weight;
	}

	public String getProcess() {
		return mProcess;
	}

	public void setProcess(String process) {
		this.mProcess = process;
	}
}
