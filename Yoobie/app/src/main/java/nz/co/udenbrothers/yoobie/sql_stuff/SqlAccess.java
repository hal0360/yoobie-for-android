package nz.co.udenbrothers.yoobie.sql_stuff;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class SqlAccess extends SQLiteOpenHelper {

    private static SqlAccess sInstance;
    private SQLiteDatabase db;

    private SqlAccess(Context context) {
        super(context, SQL.DBNAME, null, SQL.VERSION);
    }

    public static synchronized SqlAccess getInstance(Context context) {
        if (sInstance == null) sInstance = new SqlAccess(context);
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {}

    public void makeTable(HashSet<String> fields, String table) {
        String CREATE_TABLE_NEW = "CREATE TABLE IF NOT EXISTS " + table + " (";

        for (String field : fields){
            CREATE_TABLE_NEW = CREATE_TABLE_NEW + field + ", ";
        }

        CREATE_TABLE_NEW = CREATE_TABLE_NEW.substring(0, CREATE_TABLE_NEW.length() - 2);
        CREATE_TABLE_NEW = CREATE_TABLE_NEW + ")";
        db.execSQL(CREATE_TABLE_NEW);
    }

    public void createTable(String sqls) {
        db.execSQL(sqls);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropAll();
    }

    public int update(String table, ContentValues cv, String field, String value){
        return db.update(table, cv, field + "=" + value, null);
    }

    public void dropAll(){
        try {
            Cursor c = db.rawQuery("SELECT name FROM " + SQL.DBNAME + " WHERE type='table'", null);
            List<String> tables = new ArrayList<>();
            while (c.moveToNext()) {
                tables.add(c.getString(0));
            }
            for (String table : tables) {
                String dropQuery = "DROP TABLE IF EXISTS " + table;
                db.execSQL(dropQuery);
            }
            c.close();
        } catch(SQLiteException e) {

        }
    }

    public long add(String table, ContentValues cv){
        return db.insert(table, null, cv);
    }

    public void get(String table, Cur cur) {
        String query = "SELECT  * FROM " + table;
        try {
            cur.setup(db.rawQuery(query, null));
        } catch(SQLiteException e) {
            cur.setNull();
        }
    }

    public void get(String table, String field, String value, Cur cur) {
        String query = "SELECT  * FROM " + table + " WHERE " + field + "= ?";
        try {
            cur.setup(db.rawQuery(query, new String[] { value }));
        } catch(SQLiteException e) {
            cur.setNull();
        }
    }

    public void getByQuery(String query, Cur cur){
        cur.setup(db.rawQuery(query, null));
    }

    public void delete(String table, String field, String value){
        db.execSQL("DELETE FROM " + table + " WHERE " + field + "= '" + value + "'");
    }

    public void clear(String table){
        db.delete(table, null, null);
    }

    public void makeDB(){
        db = getWritableDatabase();
    }
}
