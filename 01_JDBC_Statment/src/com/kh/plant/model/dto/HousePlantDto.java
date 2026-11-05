package com.kh.plant.model.dto;

/**
 * 
 */
public class HousePlantDto {
	private int plantNo;
	private String plantType;
	private String plantName;
	
	// 생성자 => 기본생성자, 매개변수생성자
    public HousePlantDto() {}
	public HousePlantDto(String plantType, String plantName) {
		this.plantType = plantType;
		this.plantName = plantName;
	}

	public HousePlantDto(int plantNo, String plantType, String plantName) {
		this.plantNo = plantNo;
		this.plantType = plantType;
		this.plantName = plantName;
	}
	public int getPlantNo() {
		return plantNo;
	}
	public String getPlantType() {
		return plantType;
	}
	public String getPlantName() {
		return plantName;
	}
	public void setPlantNo(int plantNo) {
		this.plantNo = plantNo;
	}
	public void setPlantType(String plantType) {
		this.plantType = plantType;
	}
	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}
	@Override
	public String toString() {
		return "HousePlantDto [plantNo=" + plantNo + ", plantType=" + plantType + ", plantName=" + plantName + "]";
	}

}
