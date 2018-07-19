package com.qa.persistence.domain;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;



@Entity
public class Trainee {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	private String firstName;
	private String secondName;
	@Size(min = 6, max = 6)
	private String traineeNumber;
	@JoinColumn(name = "trainee_id")
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Subject> subjects;

	public Trainee() {

	}

	public Trainee(String firstName, String secondName, String traineeNumber, List<Subject>subjects) {
		this.firstName = firstName;
		this.secondName = secondName;
		this.traineeNumber = traineeNumber;
		this.subjects = subjects;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getTraineeNumber() {
		return traineeNumber;
	}

	public void setTraineeNumber(String traineeNumber) {
		this.traineeNumber = traineeNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Subject> getTransaction() {
		return subjects;
	}

	public void setTransaction(List<Subject> subjects) {
		this.subjects = subjects;
	}

}
