package com.example.kathyxu.googlesheetsapi;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.CustomElementCollection;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.util.ServiceException;
import com.google.gdata.util.parser.Intersection;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerViewAdapter adapter;

    StudentDBHelper myDbHelper;
    StudentAccess myDbAccess;

    private RecyclerView rView;

    List<Student> studentToSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDbHelper = new StudentDBHelper(this);
        myDbAccess = new StudentAccess(myDbHelper);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 4);

        rView = (RecyclerView)findViewById(R.id.recycler_view);//TODO
        rView.setHasFixedSize(true);
        rView.setLayoutManager(gridLayoutManager);



        boolean databaseExists = databaseExists();
        if (databaseExists==true){//if there is a database, load the display using the database
            System.out.println("setdisplay called!");
            setDisplay();
        }
        else{//if there is no database, go get the info from poke api using the getPokemon() method
            studentToSearch=myDbAccess.getAll();
            DownloadData downloadData = (DownloadData) new DownloadData();
            downloadData.execute();
            System.out.println("executed!");
        }

    }

    private boolean databaseExists(){
        boolean isExists = false;
        if(myDbAccess.getAll().size()>0){
            isExists=true;
        }
        return isExists;
    }


    public class DownloadData extends AsyncTask<Void, Void, Integer>{

        @Override
        protected Integer doInBackground(Void... params) {
            Student studentToAdd = new Student();
            SpreadsheetService service = new SpreadsheetService("com.example");
            try {
                System.out.println("hi1");

                String urlString = "https://spreadsheets.google.com/feeds/list/1WyczlSzNWQSWf88KqTVOwJccMhxK8JTARArUWLtxIps/default/public/values";

                URL url = new URL(urlString);

                ListFeed feed = service.getFeed(url, ListFeed.class);

                for (ListEntry entry : feed.getEntries()) {

                    CustomElementCollection elements = entry.getCustomElements();
//for firstname
                    String fname = elements.getValue("FirstName");
                    System.out.println("First Name is: " + fname);
                    studentToAdd.setFname(fname);

//for lastname
                    String lname = elements.getValue("LastName");
                    System.out.println("Last Name is: " + lname);
                    studentToAdd.setLname(lname);

//for zID
                    String id = elements.getValue("zID");
                    String idToAdd = "";
                    if(id.substring(0,1).toLowerCase().equals("z")){
                        System.out.println(id.substring(0,1));
                        idToAdd = id.substring(1);
                    }
                    else{
                        idToAdd = id;
                    }
                    System.out.println("zID: " + id);
                    studentToAdd.setId(Integer.parseInt(idToAdd));

//for zmail
                    String zmail = elements.getValue("zmail");
                    System.out.println("zmail is: " + zmail);
                    studentToAdd.setZmail(zmail);

//for tutorial time
                    String tut = elements.getValue("Tutorial");
                    System.out.println("tutorial is: "+ tut);
                    studentToAdd.setTut(tut);


                    studentToAdd.setComments("This is a comment for this student but i dont know how to fill it up");

                    myDbAccess.insertStudent(studentToAdd);//inserts the student into the database

                    System.out.println("student "+studentToAdd.getFname() + " " + studentToAdd.getLname() + " has zID of " + studentToAdd.getId() + " and a zmail of " + studentToAdd.getZmail() + " and a tutorial at " + studentToAdd.getTut() + " was added");

                }
            } catch (IOException e) {
                e.printStackTrace();

            } catch (ServiceException e) {
                e.printStackTrace();

            }
            //setDisplay();

            return 1;
        }
        @Override
        protected void onPostExecute(Integer hi) {
            setDisplay();
        }
    }

    private void setDisplay(){
        List <Student> dblist = myDbAccess.getAll();

        for(Student s : dblist){
            System.out.println("student: " + s.getFname() + " " + s.getLname());
        }

        adapter = new RecyclerViewAdapter(MainActivity.this, dblist);//here your arraylist of pokemon
        rView.setAdapter(adapter);
        System.out.println("SET DISPLAY WORKED!");


    }

    private void closeDb(){

    }

}
