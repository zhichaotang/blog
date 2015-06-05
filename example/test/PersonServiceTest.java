package com.example.test;

import android.test.AndroidTestCase;

import com.example.sqlite.DBOpenHelper;
import com.example.sqlite.Person;
import com.example.sqlite.PersonService;

public class PersonServiceTest extends AndroidTestCase {

	private DBOpenHelper helper;
	private PersonService service;
	
	public PersonServiceTest() {
		helper = new DBOpenHelper(getContext());
		service = new PersonService(helper);
	}

	public void testCreateDB() {
		DBOpenHelper helper = new DBOpenHelper(getContext());
		helper.getWritableDatabase();
	}

	public void testAdd() {
		service.add(new Person(22, "tang", "ÄÐ"));
	}

//	public void testDelete() {
//		service.add(new Person(22, "tang", "ÄÐ"));
//	}

}
