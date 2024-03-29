package wizen.rafal.workers.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="EMPLOYEE")
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
	@OneToOne(mappedBy = "employee")
	@JsonIgnore
	private User user;
	@OneToMany(mappedBy="employee", cascade = CascadeType.ALL)
	private List<WorkTime> workTimes;
	@OneToMany(mappedBy="manager", cascade = CascadeType.ALL)
	private List<Employee> team;
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH}) // cascade without DELETE
	@JoinColumn(name="manager_id")
	@JsonIgnore
	private Employee manager;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public List<WorkTime> getWorkTimes() {
		return workTimes;
	}

	public List<Employee> getTeam() {
		return team;
	}

	public void setTeam(List<Employee> team) {
		this.team = team;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
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

	@Transient
	public String getRole() {
		org.springframework.security.core.userdetails.User user
				= (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			return "Admin";
		}
		if (user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MANAGER"))) {
			return "Manager";
		}
		return "Employee";
	}

}
