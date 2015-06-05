package com.example.sqlite;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class PersonService {

	private static String SimpleClassName = PersonService.class.getSimpleName();

	private DBOpenHelper helper;

	public PersonService(DBOpenHelper helper) {
		this.helper = helper;
	}

	public void add(Person person) {

		SQLiteDatabase database = helper.getWritableDatabase();
		database.beginTransaction();
		try {
			Object[] parameter = { person.getName(), person.getAge(), person.getSex() };
			database.execSQL("insert into person(name,age,sex) values(?,?,?)", parameter);

			database.setTransactionSuccessful();// 事物默认标志是回滚，这里需要改变标识为true提交
		} catch (Exception e) {
			Log.e(SimpleClassName + " add", e.getMessage());
		} finally {
			database.endTransaction();
		}
	}

	public void add2(Person person) {

		SQLiteDatabase database = helper.getWritableDatabase();
		database.beginTransaction();
		try {
			ContentValues values = new ContentValues(3);
			values.put("name", person.getName());
			values.put("age", person.getAge());
			values.put("sex", person.getSex());
			helper.getWritableDatabase().insert("person", null, values);

			database.setTransactionSuccessful();// 事物默认标志是回滚，这里需要改变标识为true提交
		} catch (Exception e) {
			Log.e(SimpleClassName + " add2", e.getMessage());
		} finally {
			database.endTransaction();
		}
	}

	public void delete(Integer id) {

		helper.getWritableDatabase().beginTransaction();
		try {
			Object[] parameter = { id };
			helper.getWritableDatabase().execSQL("delete from person where person_id=?", parameter);

			helper.getWritableDatabase().setTransactionSuccessful();// 事物默认标志是回滚，这里需要改变标识为true提交
		} catch (Exception e) {
			Log.e(SimpleClassName + " delete", e.getMessage());
		} finally {
			helper.getWritableDatabase().endTransaction();
		}
	}

	public void delete2(Integer id) {

		SQLiteDatabase database = helper.getWritableDatabase();
		database.beginTransaction();
		try {
			String[] parameter = { id.toString() };
			database.delete("person", "person_id=?", parameter);

			database.setTransactionSuccessful();// 事物默认标志是回滚，这里需要改变标识为true提交
		} catch (Exception e) {
			Log.e(SimpleClassName + " delete2", e.getMessage());
		} finally {
			database.endTransaction();
		}
	}

	public void update(Person person) {

		SQLiteDatabase database = helper.getWritableDatabase();
		database.beginTransaction();
		try {
			Object[] parameter = { person.getName(), person.getAge(), person.getSex(), person.getPersonId() };
			database.execSQL("update person set name=?,age=?,sex=? where person_id=?", parameter);

			database.setTransactionSuccessful();// 事物默认标志是回滚，这里需要改变标识为true提交
		} catch (Exception e) {
			Log.e(SimpleClassName + " update", e.getMessage());
		} finally {
			database.endTransaction();
		}
	}

	public void update2(Person person) {

		helper.getWritableDatabase().beginTransaction();
		try {

			String[] parameter = { person.getPersonId().toString() };
			ContentValues values = new ContentValues(3);
			values.put("name", person.getName());
			values.put("age", person.getAge());
			values.put("sex", person.getSex());
			helper.getWritableDatabase().update("person", values, "person_id=?", parameter);

			helper.getWritableDatabase().setTransactionSuccessful();// 事物默认标志是回滚，这里需要改变标识为true提交
		} catch (Exception e) {
			Log.e(SimpleClassName + " update2", e.getMessage());
		} finally {
			helper.getWritableDatabase().endTransaction();
		}
	}

	public Person findById(Integer id) {
		String[] parameter = { id.toString() };
		try (Cursor cursor = helper.getReadableDatabase().rawQuery("select * from person where person_id=?", parameter)) {
			if (cursor.moveToFirst()) {// 如果没有数据会返回false
				Person person = new Person();
				person.setPersonId(cursor.getInt(cursor.getColumnIndex("person_id")));
				person.setName(cursor.getString(cursor.getColumnIndex("name")));
				person.setAge(cursor.getInt(cursor.getColumnIndex("age")));
				person.setSex(cursor.getString(cursor.getColumnIndex("sex")));
				return person;
			}
		} catch (Exception e) {
			Log.e(SimpleClassName + " findById", e.getMessage());
		}
		return null;
	}

	public Person findById2(Integer id) {
		String[] parameter = { id.toString() };
		String[] columns = { "name", "age", "sex", "person_id" };
		try (Cursor cursor = helper.getReadableDatabase().query("person", columns, "person_id=?", parameter, null, null, null)) {
			if (cursor.moveToFirst()) {// 如果没有数据会返回false
				Person person = new Person();
				person.setPersonId(cursor.getInt(cursor.getColumnIndex("person_id")));
				person.setName(cursor.getString(cursor.getColumnIndex("name")));
				person.setAge(cursor.getInt(cursor.getColumnIndex("age")));
				person.setSex(cursor.getString(cursor.getColumnIndex("sex")));
				return person;
			}
		} catch (Exception e) {
			Log.e(SimpleClassName + " findById2", e.getMessage());
		}
		return null;
	}

	public List<Person> findList(Integer beginIndex, Integer pageSize) {
		List<Person> persons = new LinkedList<>();
		String[] parameter = { beginIndex.toString(), pageSize.toString() };
		try (Cursor cursor = helper.getReadableDatabase().rawQuery("select * from person order by person_id asc limit ?,?", parameter)) {
			for (; cursor.moveToNext();) {// 如果没有数据会返回false
				Person person = new Person();
				person.setPersonId(cursor.getInt(cursor.getColumnIndex("person_id")));
				person.setName(cursor.getString(cursor.getColumnIndex("name")));
				person.setAge(cursor.getInt(cursor.getColumnIndex("age")));
				person.setSex(cursor.getString(cursor.getColumnIndex("sex")));
				persons.add(person);
			}
		} catch (Exception e) {
			Log.e(SimpleClassName + " findList", e.getMessage());
		}
		return persons;
	}

	public List<Person> findList2(Integer beginIndex, Integer pageSize) {
		List<Person> persons = new LinkedList<>();
		try (Cursor cursor = helper.getReadableDatabase().query("person", null, null, null, null, null, "person_id asc", beginIndex + "," + pageSize)) {
			for (; cursor.moveToNext();) {// 如果没有数据会返回false
				Person person = new Person();
				person.setPersonId(cursor.getInt(cursor.getColumnIndex("person_id")));
				person.setName(cursor.getString(cursor.getColumnIndex("name")));
				person.setAge(cursor.getInt(cursor.getColumnIndex("age")));
				person.setSex(cursor.getString(cursor.getColumnIndex("sex")));
				persons.add(person);
			}
		} catch (Exception e) {
			Log.e(SimpleClassName + " findList2", e.getMessage());
		}
		return persons;
	}

	public long findCount() {
		try (Cursor cursor = helper.getReadableDatabase().rawQuery("select count(person_id) from person ", null)) {
			if (cursor.moveToFirst()) {// 如果没有数据会返回false
				return cursor.getLong(0);
			}
		} catch (Exception e) {
			Log.e(SimpleClassName + " findCount", e.getMessage());
		}
		return 0;
	}

	public long findCount2() {
		String[] columns = { "count(person_id)" };
		try (Cursor cursor = helper.getReadableDatabase().query("person", columns, null, null, null, null, null)) {
			if (cursor.moveToFirst()) {// 如果没有数据会返回false
				return cursor.getLong(0);
			}
		} catch (Exception e) {
			Log.e(SimpleClassName + " findCount2", e.getMessage());
		}
		return 0;
	}
}
