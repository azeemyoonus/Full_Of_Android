package com.example.connecttointernet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.android.datafrominternet.utilities.NetworkUtlis;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView resutbox, viewbox;
    EditText searchbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchbox =(EditText) findViewById(R.id.search_box);
        viewbox =(TextView) findViewById(R.id.view_box);
        resutbox =(TextView) findViewById(R.id.result_box);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menuItemThatWasSelected = item.getItemId();
        if(menuItemThatWasSelected==R.id.action_search){
         makegithubquery();
         return true;
        }
        return true;
    }

    void makegithubquery(){
        String githubquery = searchbox.getText().toString();
      URL githubsearchurl = NetworkUtlis.buildUrl(githubquery);
        viewbox.setText(githubsearchurl.toString());
        String githubsearchresult = null;
        try{
            githubsearchresult=NetworkUtlis.getResponseFromHttpUrl(githubsearchurl);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
