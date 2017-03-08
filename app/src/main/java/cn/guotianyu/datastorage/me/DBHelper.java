package cn.guotianyu.datastorage.me;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 郭天宇 on 2017/2/7.
 */

public class DBHelper extends SQLiteOpenHelper {


    //context上下文
    //name 数据库的名称
    //factory 游标工厂,null默认游标工厂
    //version 版本号,从1开始
    public DBHelper(Context context){
        super(context,"qq.db",null,1);
    }
    /**
     * @param db 数据表结构
     * _id 主键 int
     * username 用户名 varchar(20)
     * password 密码 varchar(20)
     */    //数据库第一次被创建时调用,适合数据库表结构的初始化工作
    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("onCreate 数据库第一次被创建");
        db.execSQL("create table qq(_id integer primary key autoincrement,username varchar(20),password varchar(20))");
    }

    //数据库版本号增加时调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("数据库onUpgrade");
    }
}
