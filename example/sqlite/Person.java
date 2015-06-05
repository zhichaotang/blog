package com.example.sqlite;

public class Person {

	private Integer personId;
	private Integer age;
	private String name;
	private String sex;

	public Person() {
	}

	public Person(Integer age, String name, String sex) {
		this.age = age;
		this.name = name;
		this.sex = sex;
	}

	public Person(Integer personId, Integer age, String name, String sex) {
		this.personId = personId;
		this.age = age;
		this.name = name;
		this.sex = sex;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
}
