package com.example.r3l0ad3d.expendablelistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView exListView;

    private ExAdapter exAdapter;
    private List<String> parentList;
    private HashMap<String,List<String>> childList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        exListView = (ExpandableListView) findViewById(R.id.exListView);

        parentList = new ArrayList<>();
        childList = new HashMap<String, List<String>>();

        exAdapter = new ExAdapter(this,parentList,childList);
        exListView.setAdapter(exAdapter);

        setData();

        exListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String text = "List Header Name : "+parentList.get(groupPosition)+" \n"
                        +"Child Name : "+childList.get(parentList.get(groupPosition)).get(childPosition);
                Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }

    private void setData() {
        //parentList = new ArrayList<String>();
        //childList = new HashMap<String, List<String>>();

        // Adding child data
        parentList.add("Top 250");
        parentList.add("Now Showing");
        parentList.add("Coming Soon..");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");

        childList.put(parentList.get(0), top250); // Header, Child data
        childList.put(parentList.get(1), nowShowing);
        childList.put(parentList.get(2), comingSoon);

        exAdapter.notifyDataSetChanged();
    }
}
