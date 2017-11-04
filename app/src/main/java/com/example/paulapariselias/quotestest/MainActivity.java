package com.example.paulapariselias.quotestest;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.paulapariselias.quotestest.models.Quote;
import com.example.paulapariselias.quotestest.network.GetQuotes;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new BackgroundQuotes().execute("quote","author","category");



            }
        });
    }


    private class BackgroundQuotes extends GetQuotes {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
           progressDialog = new ProgressDialog(MainActivity.this);
           progressDialog.show();
        }

        @Override
        protected void onPostExecute(Quote quote) {
            if (quote != null){

                TextView quoteTextView = (TextView) findViewById(R.id.quoteTV);
                quoteTextView.setText(quote.getQuote());
                TextView authorTextView = (TextView) findViewById( R.id.authorTv);
                authorTextView.setText(quote.getAuthor());
                TextView categoryTextView = (TextView) findViewById(R.id.categoryTv);
                categoryTextView.setText(quote.getCategory());
            }

            progressDialog.dismiss();

        }
    }

    }

