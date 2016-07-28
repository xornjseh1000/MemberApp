package com.abc.com.memberapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hsh on 2016-07-27.
 */
public class MemberDAO extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "member";
    public static final String ID = "id";
    public static final String PW = "pw";
    public static final String NAME = "name";
    public static final String SSN = "ssn";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public MemberDAO(Context context) {

        super(context,"hanbitdb", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql ="create table if not exists " +
                TABLE_NAME
                +" ( "
                +ID+" text primary key, "
                +PW+" text, "
                +NAME+" text, "
                +SSN+" text, "
                +EMAIL+" text, "
                +PHONE+" text "
                +" ); ";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists "+TABLE_NAME;
        db.execSQL(sql);
        this.onCreate(db);
    }

    public int insert(MemberBean mem){
        int result = 0;
        String sql = "insert into member(id,pw,name,regdate,ssn,email,profile_img,phone)"
                + "values(?,?,?,?,?,?,?,?,?)";

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
        return result;
    }
    public int update(MemberBean mem){
        String sql = "update member"
                +" set pw = ? , email = ?"
                +" where id = ?";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
        int result = 0;
        return result;
    }

    // list
    public List<MemberBean> list() {
        String sql="select * from member";
        List<MemberBean> list = new ArrayList<MemberBean>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        return list;
    }
    // findByPK
    public MemberBean findById(String pk) {
        String sql = "select * from member where id = ?";
        MemberBean temp = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToFirst()){

        }
        System.out.println(temp);
        return temp;
    }
    // findByNotPK
    public List<MemberBean> findByName(String name) {
        List<MemberBean> list = new ArrayList<MemberBean>();
        String sql = "select * from member where name=?";
        MemberBean temp1 = new MemberBean();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        return list;
    }
    // count
    public int count() {
        String sql = "select count(*) as count from member";
        int count = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        return count;
    }

    public int delete(MemberBean member) {
        String sql = "delete from member where id = ? and pw = ?";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
        int result = 0;

        return result;
    }

    public boolean login(MemberBean param) {
        boolean loginOk= false;
        String sql = "";
        if(param.getId()!=null
                && param.getPw()!=null
                && this.existId(param.getId())){
            MemberBean member = this.findById(param.getId());
            if(member.getPw().equals(param.getPw())){
                loginOk = true;
            }
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(sql,null);
        }
        return loginOk;
    }
    public boolean existId(String id){
        boolean existOK = false;
        String sql = "select count(*) as count from member where id = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        int result = 0;

        return existOK;
    }
}
