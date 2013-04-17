package com.mindstormsoftware.examresults.entity;

import java.io.Serializable;

public class ExamResult implements Serializable {
	String seatNumber;
	String studentName;
	String marks_Math;
	String marks_CommSkills;
	String marks_Programming;
    String marks_ElectronicCircuits;
    String marks_Total;
    String marks_Percentage;
	
    public ExamResult() {
		super();
	}
	
	public ExamResult(String seatNumber, String studentName, String marks_Math,
			String marks_CommSkills, String marks_Programming,
			String marks_ElectronicCircuits, String marks_Total,
			String marks_Percentage) {
		super();
		this.seatNumber = seatNumber;
		this.studentName = studentName;
		this.marks_Math = marks_Math;
		this.marks_CommSkills = marks_CommSkills;
		this.marks_Programming = marks_Programming;
		this.marks_ElectronicCircuits = marks_ElectronicCircuits;
		this.marks_Total = marks_Total;
		this.marks_Percentage = marks_Percentage;
	}
	
	public String getSeatNumber() {
		return seatNumber;
	}
	
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
	
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getMarks_Math() {
		return marks_Math;
	}
	public void setMarks_Math(String marks_Math) {
		this.marks_Math = marks_Math;
	}
	public String getMarks_CommSkills() {
		return marks_CommSkills;
	}
	public void setMarks_CommSkills(String marks_CommSkills) {
		this.marks_CommSkills = marks_CommSkills;
	}
	public String getMarks_Programming() {
		return marks_Programming;
	}
	public void setMarks_Programming(String marks_Programming) {
		this.marks_Programming = marks_Programming;
	}
	public String getMarks_ElectronicCircuits() {
		return marks_ElectronicCircuits;
	}
	public void setMarks_ElectronicCircuits(String marks_ElectronicCircuits) {
		this.marks_ElectronicCircuits = marks_ElectronicCircuits;
	}
	public String getMarks_Total() {
		return marks_Total;
	}
	public void setMarks_Total(String marks_Total) {
		this.marks_Total = marks_Total;
	}
	public String getMarks_Percentage() {
		return marks_Percentage;
	}
	public void setMarks_Percentage(String marks_Percentage) {
		this.marks_Percentage = marks_Percentage;
	}
}
