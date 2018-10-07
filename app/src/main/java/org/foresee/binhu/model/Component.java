package org.foresee.binhu.model;

public class Component {
	private int id;
	private int prescriptId;
	private String medicineName;
	private String weight;
	private String process;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrescriptId() {
		return prescriptId;
	}

	public void setPrescriptId(int prescriptId) {
		this.prescriptId = prescriptId;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}
}
