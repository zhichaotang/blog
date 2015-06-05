package com.example.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

	public static SQLiteDatabase createDB(Context context) {
		DBOpenHelper openHelper = new DBOpenHelper(context);
		return openHelper.getWritableDatabase();// 第一次调用此方法或getReadableDatabase方法会创建数据库
	}

	public DBOpenHelper(Context context) {
		// 数据库文件保存在'/appName/appPackageName/databases/'
		super(context, "test.db",// 数据库文件名
				null,// 是用默认游标工厂
				2// 版本号不能为0，为0会报错，一般初始版本从1开始
		);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// 数据库创建时被触发，此方法内一般用于创建表等
		db.execSQL("create table person(person_id integer primary key autoincrement,name verchar(20),age integer)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// 数据库版本号更新时被触发，此方法一般操作数据库版本升级，如删除旧表，创建新表，修改表等
		db.execSQL("alter table person add sex verchar(2)");
	}
}
