package com.example.kathyxu.googlesheetsapi;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.CustomElementCollection;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.util.ServiceException;
import com.google.gdata.util.parser.Intersection;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadData downloadData = new DownloadData();
        downloadData.execute();
        System.out.println("executed!");
    }


    public class DownloadData extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... params) {
            Student studentToAdd = new Student();
            SpreadsheetService service = new SpreadsheetService("com.example");
            try {
                System.out.println("hi1");

                String urlString = "https://spreadsheets.google.com/feeds/list/1WyczlSzNWQSWf88KqTVOwJccMhxK8JTARArUWLtxIps/default/public/values";

                URL url = new URL(urlString);

                ListFeed feed = service.getFeed(url, ListFeed.class);

                for (ListEntry entry : feed.getEntries()) {

                    CustomElementCollection elements = entry.getCustomElements();
                    String fname = elements.getValue("FirstName");
                    System.out.println("First Name is: " + fname);
                    studentToAdd.setFname(fname);


                    String lname = elements.getValue("LastName");
                    System.out.println("Last Name is: " + lname);
                    studentToAdd.setLname(lname);


                    String id = elements.getValue("zID");
                    String idToAdd = "";
                    if(id.substring(0).toLowerCase().equals("z")){
                        idToAdd = id.substring(1);
                    }
                    else{
                        idToAdd = id;
                    }
                    System.out.println("zID: " + id);
                    studentToAdd.setId(Integer.parseInt(idToAdd));


                    String zmail = elements.getValue("zmail");
                    System.out.println("zmail is: " + zmail);
                    studentToAdd.setZmail(zmail);


                }
            } catch (IOException e) {
                e.printStackTrace();

            } catch (ServiceException e) {
                e.printStackTrace();

            }
            //TODO insert studentToAdd to the database
            return null;
        }
    }

}
