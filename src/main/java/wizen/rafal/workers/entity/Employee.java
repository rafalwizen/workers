package wizen.rafal.workers.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {

	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="personal_identity_number")
	private int personalIdentityNumber;

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

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", personalIdentityNumber=" + personalIdentityNumber + "]";
	}
	
}
