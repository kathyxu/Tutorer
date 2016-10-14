package com.example.kathyxu.googlesheetsapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
        final Student detailStudent = myDbAccess.getStudent(getIntent().getStringExtra("number"));

        ImageView image = (ImageView)findViewById(R.id.photo);

        TextView name = (TextView)findViewById(R.id.name);
        name.setText(detailStudent.getFname() + " " + detailStudent.getLname());

        TextView id = (TextView)findViewById(R.id.zID);
        id.setText(String.valueOf(detailStudent.getId()));

        TextView zmail = (TextView)findViewById(R.id.zmail);
        zmail.setText(detailStudent.getZmail());

        TextView tutorial = (TextView)findViewById(R.id.tut);
        tutorial.setText(detailStudent.getTut());

        //TODO tablelayout
        TableLayout assignmentTable = (TableLayout)findViewById(R.id.assessments);

        final EditText assessmentOneEdit = (EditText)findViewById(R.id.assessmentOneEdit);
        final EditText assessmentTwoEdit = (EditText)findViewById(R.id.assessmentTwoEdit);
        final TextView assessmentOneDisplay = (TextView)findViewById(R.id.assessmentOneDisplay);
        final TextView assessmentTwoDisplay = (TextView)findViewById(R.id.assessmentTwoDisplay);

        Button save = (Button) findViewById(R.id.saveButton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int assessmentOne = 0;
//                int assessmentTwo = 0;
//                int assessmentThree = 0;

                if(assessmentOneEdit.getText()!=null) {
                    int assessmentOne = Integer.parseInt(String.valueOf(assessmentOneEdit.getText()));
                    detailStudent.setAssessmentOne(assessmentOne);
                    assessmentOneDisplay.setText(String.valueOf(detailStudent.getAssessmentOne()));

                    assessmentOneDisplay.setVisibility(View.VISIBLE);
                    assessmentOneEdit.setVisibility(View.GONE);

                }
//                if(assessmentTwoEdit.getText()!=null) {
//                    int assessmentTwo = Integer.parseInt(String.valueOf(assessmentTwoEdit.getText()));
//                    detailStudent.setAssessmentTwo(assessmentTwo);
//                    assessmentTwoDisplay.setText(detailStudent.getAssessmentTwo());
//                }



            }
        });
        //if there is avlue in database
        //display value
        // on tap open edit text

        //fi there is no value in database
        // display nothing
        //on tap open edit text

        TextView hello = (TextView)findViewById(R.id.ass1);
        hello.setText("First Quiz");

        TextView comments = (TextView)findViewById(R.id.comments);
        comments.setText(detailStudent.getComments());


    }
}
