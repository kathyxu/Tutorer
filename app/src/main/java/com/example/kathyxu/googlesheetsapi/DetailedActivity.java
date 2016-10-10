package com.example.kathyxu.googlesheetsapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

/**
 * Created by Kathy on 9/10/2016.
 */

public class DetailedActivity extends AppCompatActivity {

    private ImageView image;
    private TextView name;
    private TextView lname;
    private TextView id;
    private TextView zmail;
    private TextView tutorial;

    private TextView comments;

    private TableLayout assignmentTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_view);

        fillFields();
    }

    private void fillFields() {
        StudentDBHelper myDbHelper = new StudentDBHelper(this);
        StudentAccess myDbAccess = new StudentAccess(myDbHelper);
        Student detailStudent = myDbAccess.getStudent(getIntent().getStringExtra("number"));

        ImageView image = (ImageView)findViewById(R.id.photo);
        TextView name = (TextView)findViewById(R.id.name);
        TextView id = (TextView)findViewById(R.id.zID);
        TextView zmail = (TextView)findViewById(R.id.zmail);
        TextView tutorial = (TextView)findViewById(R.id.tut);

        //TODO tablelayout
        TableLayout assignmentTable = (TableLayout)findViewById(R.id.assessments);

        TextView comments = (TextView)findViewById(R.id.comments);


    }
}
