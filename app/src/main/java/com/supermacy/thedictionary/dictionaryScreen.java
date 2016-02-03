package com.supermacy.thedictionary;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.supermacy.utilities.Storehouse;
import com.supermacy.wordnikapi.Word;

public class dictionaryScreen extends AppCompatActivity {
    public Button searchButton;
    public TextView messageTextView;
    public Button button2;
    public View srchbtnview;
    public View msgtxtview;
    private int shortAnimation;
    private static Storehouse testDatabase;
    private static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

/*        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);*/

        setContentView(R.layout.activity_dictionary_screen);
        srchbtnview = findViewById(R.id.button);
        msgtxtview = findViewById(R.id.textView);
        shortAnimation = getResources().getInteger(android.R.integer.config_longAnimTime);
        button2 = (Button) findViewById(R.id.button2);

        testDatabase = new Storehouse(getApplicationContext());
        db = testDatabase.getWritableDatabase();
    }

    public void crossfade(View view) {
        msgtxtview.setAlpha(0f);
        msgtxtview.setVisibility(View.VISIBLE);
        msgtxtview.animate().alpha(1f).setDuration(shortAnimation).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                //msgtxtview.setVisibility(View.VISIBLE);
            }
        });
        srchbtnview.animate().alpha(1f).setDuration(shortAnimation).setListener(new AnimatorListenerAdapter() {
                                                                                    @Override
                                                                                    public void onAnimationEnd(Animator animation) {
                                                                                        //srchbtnview.setVisibility(View.GONE);
                                                                                        srchbtnview.setAlpha(0f);
                                                                                    }
                                                                                }
        );
    }

    public void deletetable(View view) {
        db.execSQL("DROP TABLE IF EXISTS HELLO");
    }

    public void buttonClicked(View view) {
        testDatabase.onCreate(db);
    }

    public void updateDatabase(View view) {
        db.execSQL("INSERT INTO HELLO VALUES(0,'qe','asd','zxc','ert','xcv','rty','dfg','xcv','fgh','vbn');");
    }

    public void access(View view) {
        Word obj = new Word("9f801cdaf4f29ca7bd88f034992178451b9fda98aa9b6190a", getApplicationContext());
        obj.examples("hill", false, true, 0, 5);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}


