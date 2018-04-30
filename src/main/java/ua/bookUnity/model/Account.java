package ua.bookUnity.model;

public class Account {
	
	private String login;
	

	private String password;
	private String gender;
	private String firstName;
	private String secondName;
	private String thirdName;
	private Integer age;
	private String phoneNumber;
	private String city;
	private String region;
	private String country;
	
	public Account() {}
	
	public Account(String login,String password,String gender,String firstName,String secondName,String thirdName,Integer age,String phoneNumber,String city,String region,String country) {
		this.login = login;
		this.password = password;
		this.gender = gender;
		this.firstName = firstName;
		this.secondName = secondName;
		this.thirdName = thirdName;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.city = city;
		this.region = region;
		this.country = country;	
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public String getThirdName() {
		return thirdName;
	}

	public void setThirdName(String thirdName) {
		this.thirdName = thirdName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
}
