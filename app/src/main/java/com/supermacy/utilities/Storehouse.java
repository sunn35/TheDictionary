package com.supermacy.utilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by sudhanshu on 28/1/16.
 */
public class Storehouse extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Dictionary.db";
    private static final String WORD_TABLE_NAME = "WORD_TABLE";
    private static final String COMMA_SEP = ",";
    private static final String TEXT_TYPE = " TEXT";
    private static final String KEY_ID = "ID";
    private static final String KEY_EXAMPLES = " EXAMPLES";
    private static final String KEY_DEFINITIONS = " DEFINITIONS";
    private static final String KEY_TOP_EXAMPLE = " TOP_EXAMPLE";
    private static final String KEY_RELATED_WORDS = " RELATED_WORDS";
    private static final String KEY_HYPHENATION = " HYPHENATION";
    private static final String KEY_AUDIO = " AUDIO";
    private static final String KEY_PRONUNCIATION = " PRONUNCIATION";
    private static final String KEY_PHRASES = " PHRASES";
    private static final String KEY_ETYMOLOGY = " ETYMOLOGY";
    private static final String KEY_FREQUENCY = " FREQUENCY";
    private static final String CREATE_WORD_TABLE_QUERY = "CREATE TABLE " + WORD_TABLE_NAME + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL" + COMMA_SEP +
            KEY_EXAMPLES + TEXT_TYPE + COMMA_SEP + KEY_DEFINITIONS + TEXT_TYPE + COMMA_SEP + KEY_TOP_EXAMPLE + TEXT_TYPE + COMMA_SEP + KEY_RELATED_WORDS + TEXT_TYPE + COMMA_SEP + KEY_HYPHENATION + TEXT_TYPE + COMMA_SEP + KEY_AUDIO + TEXT_TYPE + COMMA_SEP + KEY_PRONUNCIATION + TEXT_TYPE + COMMA_SEP + KEY_PHRASES + TEXT_TYPE + COMMA_SEP + KEY_ETYMOLOGY + TEXT_TYPE + COMMA_SEP + KEY_FREQUENCY + TEXT_TYPE + " );";
    private static final String DELETE_FROM_WORD_TABLE_QUERY = "DELETE FROM " + WORD_TABLE_NAME + "WHERE ID=1;";
    private static SQLiteDatabase db;

    public Storehouse(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("DATABASE", "DATABASE CREATED SUCCESSFULLY");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            this.db = db;
            db.execSQL(CREATE_WORD_TABLE_QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d("CREATE", "ERROR OCCURED WHILE CREATING TABLE IN DATABASE");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

/*    public void insertIntoWordTable(String EXAMPLES, String DEFINITIONS, String TOP_EXAMPLE, String PRONUNCIATION, String HYPHENATION, String ETYMOLOGY, String PHRASES, String FREQUENCY, String RELATED_WORDS, String AUDIO) {
        try {
            String INSERT_TO_WORD_TABLE_QUERY = "INSERT INTO " + WORD_TABLE_NAME + " (" + KEY_EXAMPLES + COMMA_SEP + KEY_DEFINITIONS + COMMA_SEP + KEY_TOP_EXAMPLE + COMMA_SEP + KEY_RELATED_WORDS + COMMA_SEP + KEY_HYPHENATION + COMMA_SEP + KEY_AUDIO + COMMA_SEP + KEY_PRONUNCIATION + COMMA_SEP + KEY_PHRASES + COMMA_SEP + KEY_ETYMOLOGY + COMMA_SEP + KEY_FREQUENCY + ") VALUES (" + EXAMPLES + COMMA_SEP + DEFINITIONS + COMMA_SEP + TOP_EXAMPLE + COMMA_SEP + RELATED_WORDS + COMMA_SEP + HYPHENATION + COMMA_SEP + AUDIO + COMMA_SEP + PRONUNCIATION + COMMA_SEP + PHRASES + COMMA_SEP + ETYMOLOGY + COMMA_SEP + FREQUENCY + ");";
            Log.d("WORDTABLE_INSERT_QUERY", INSERT_TO_WORD_TABLE_QUERY);
            db.execSQL(INSERT_TO_WORD_TABLE_QUERY);
            Log.d("INSERT QUERY EXECUTED", "INSERT QUERY EXECUTED SUCCESSFULLY");
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d("ERROR", "ERROR OCCURED WHILE INSERTING VALUES IN WORD_TABLE");
        }
    }*/

    public void insertIntoWordTable(String EXAMPLES, String DEFINITIONS, String TOP_EXAMPLE, String PRONUNCIATION, String HYPHENATION, String ETYMOLOGY, String PHRASES, String FREQUENCY, String RELATED_WORDS, String AUDIO) {
        try {
            long rowId;
            ContentValues values = new ContentValues();
            values.put(KEY_EXAMPLES, EXAMPLES);
            values.put(KEY_DEFINITIONS, DEFINITIONS);
            values.put(KEY_TOP_EXAMPLE, TOP_EXAMPLE);
            values.put(KEY_RELATED_WORDS, RELATED_WORDS);
            values.put(KEY_HYPHENATION, HYPHENATION);
            values.put(KEY_AUDIO, AUDIO);
            values.put(KEY_PRONUNCIATION, PRONUNCIATION);
            values.put(KEY_PHRASES, PHRASES);
            values.put(KEY_ETYMOLOGY, ETYMOLOGY);
            values.put(KEY_FREQUENCY, FREQUENCY);

            deleteFromWordTable();
            rowId = db.insert(WORD_TABLE_NAME, null, values);
            Log.d("INSERT", "INSERTION INTO WORD TABLE SUCCESSFULL");
            Log.d("INSERT", "ROW ID OF THE INSERTED ROW :: " + rowId);
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d("INSERTION ERROR", "ERROR OCCURRED WHILE INSERTING A NEW ROW IN THE WORD_TABLE");
        }
    }

/*    public void deleteFromWordTable() {
        try {
            Log.d("WORD_TABLE_DELETE_QUERY", DELETE_FROM_WORD_TABLE_QUERY);
            db.execSQL(DELETE_FROM_WORD_TABLE_QUERY);
            Log.d("DELETE EXECUTED", "DELETED QUERY EXECUTED SUCCESSFULLY");
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d("ERROR", "ERROR OCCURED WHILE DELETING VALUES FROM WORD_TABLE");
        }
    }*/

    private void deleteFromWordTable() {
        try {
            String selection = KEY_ID + " LIKE ?";
            String[] selectionArgs = {String.valueOf("" + 1)};
            db.delete(WORD_TABLE_NAME, selection, selectionArgs);
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d("DELETE ERROR", "ERROR OCCURRED WHILE DELETING THE ROW FROM TABLE WORD_TABLE");
        }
    }

    public void getWordDetails() {
        try {
            String[] projection = {KEY_ID, KEY_EXAMPLES, KEY_DEFINITIONS, KEY_TOP_EXAMPLE, KEY_RELATED_WORDS, KEY_HYPHENATION, KEY_AUDIO, KEY_PRONUNCIATION, KEY_PHRASES, KEY_ETYMOLOGY, KEY_FREQUENCY};
            String selection = "ID";
            String selectionArgs[] = {"1"};
            Cursor cursor = db.query(WORD_TABLE_NAME, projection, selection, selectionArgs, null, null, null);
            cursor.moveToFirst();
            long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(KEY_ID));
            Log.d("ITEM ID", "" + itemId);
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d("READ ERROR", "ERROR OCCURED WHILE GETTING THE DETAILS FROM WORD_TABLE");
        }
    }

}
