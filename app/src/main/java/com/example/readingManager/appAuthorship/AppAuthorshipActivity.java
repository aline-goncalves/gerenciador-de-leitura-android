package com.example.readingManager.appAuthorship;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.readingManager.R;
import com.example.readingManager.book.BookListActivity;

public class AppAuthorshipActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_authorship);
    }

    public void callBookListActivity(View view){
        Intent intent = new Intent(this, BookListActivity.class);
        startActivity(intent);
    }
}
