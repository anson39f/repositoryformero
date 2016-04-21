package com.zhongchengtechnology.business;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Contacts.Intents.Insert;
/**
 * 该数据库工具类用来操作*/
@SuppressWarnings("deprecation")
public class DBHelper extends SQLiteOpenHelper{
	SQLiteDatabase db;
	private static final String _USER_NAME="username";
	private static final String _USER_PWD="password";
	private static final String _ID="id";
	private static final String DBNAME="account.db";//数据库名
	private static final String TABLE_ACCOUNT="create table account(_USER_NAME TEXT,_USER_PWD INTEGER,_ID TEXT)";//创建表
	public DBHelper(Context context) {
		//创建数据库
		super(context, DBNAME, null, 1);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//创建表
		db.execSQL(TABLE_ACCOUNT);
	}
	//插入数据
	public void insertAccount(String account,String pwd,String id){
		db=this.getWritableDatabase();
		ContentValues values=new ContentValues();
		values.put(_USER_NAME, account);
		values.put(_USER_PWD, pwd);
		values.put(_ID, id);
		db.insert(TABLE_ACCOUNT, null, values);
	}
	//删除数据
	public void delete(int id){
		db=this.getWritableDatabase();
		String whereClause=_USER_NAME+"=?";
		String[] whereArgs={Integer.toString(id)};
		db.delete(TABLE_ACCOUNT, whereClause, whereArgs);
	}
	//更新数据
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		String sql="drop table if exists"+TABLE_ACCOUNT;
		db.execSQL(sql);
		onCreate(db);
	}

	//改表，用于增加id
	public void update(String text,int id){
		db=this.getWritableDatabase();
		String whereClause=_ID+"=?";
		ContentValues values=new ContentValues();
		//为指定的列增加id
		String[] where={Integer.toString(id)};
		values.put(_ID, id);
		db.update(TABLE_ACCOUNT, values, whereClause,where );
	}
	public Cursor query(){
		db=this.getReadableDatabase();
		Cursor cursor = null;
		cursor=db.query(TABLE_ACCOUNT, null, null, null, null, null, null);
		return cursor;
		
	}

}
