package com.example.readingManager.appAuthorship;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.readingManager.R;
import com.example.readingManager.book.BookListActivity;

public class AppAuthorshipActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_authorship);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void callBookListActivity(View view){
        Intent intent = new Intent(this, BookListActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        cancel();
    }

    private void cancel(){
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case android.R.id.home:
                cancel();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
