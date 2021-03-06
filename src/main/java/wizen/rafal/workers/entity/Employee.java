package wizen.rafal.workers.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="personal_identity_number", unique = true)
	private int personalIdentityNumber;
	
	@OneToMany(mappedBy="employee", cascade = CascadeType.ALL)
	private List<WorkTime> workTimes;

	public Employee() {
		
	}
	
	public Employee(String firstName, String lastName, int personalIdentityNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.personalIdentityNumber = personalIdentityNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getPersonalIdentityNumber() {
		return personalIdentityNumber;
	}

	public void setPersonalIdentityNumber(int personalIdentityNumber) {
		this.personalIdentityNumber = personalIdentityNumber;
	}
	
	public List<WorkTime> getWorkTimes() {
		return workTimes;
	}

	public void setWorkTimes(List<WorkTime> workTimes) {
		this.workTimes = workTimes;
	}

	// method to easy add WorkTimes bi-directional
	public void add(WorkTime tempWorkTime) {
		if (workTimes == null) {
			workTimes = new ArrayList<>();
		}
		workTimes.add(tempWorkTime);
		tempWorkTime.setEmployee(this);
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", personalIdentityNumber=" + personalIdentityNumber + "]";
	}
	
}
