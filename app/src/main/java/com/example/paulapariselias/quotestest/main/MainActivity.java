package com.example.paulapariselias.quotestest.main;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.paulapariselias.quotestest.R;
import com.example.paulapariselias.quotestest.models.Quote;
import com.example.paulapariselias.quotestest.network.GetQuotes;

public class MainActivity extends AppCompatActivity {


    private Typeface typewriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();

        String font = "fonts/typewriter.ttf";
        this.typewriter = Typeface.createFromAsset(getAssets(),font);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            
            @Override
            public void onClick(View view) {

                new BackgroundQuotes().execute("quote","author","category");
                TextView textView= (TextView) findViewById(R.id.authorTv);
                textView.setVisibility(View.VISIBLE);


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
                quoteTextView.setTypeface(typewriter);
                TextView authorTextView = (TextView) findViewById( R.id.authorTv);
                authorTextView.setText(quote.getAuthor());
                authorTextView.setTypeface(typewriter);

            }

            progressDialog.dismiss();

        }
    }



    }

