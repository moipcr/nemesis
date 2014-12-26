package com.nemesis.moises.autoseleccionador;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    Button start;
    ScrollView sc;
    EditText hashtag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button) findViewById(R.id.tgStartStop);
        sc = (ScrollView) findViewById(R.id.scUsers);
        hashtag = (EditText) findViewById(R.id.etHashtag);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Twitter().execute(hashtag.getText().toString());


            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Log.d("Item", String.valueOf(id));
        //noinspection SimplifiableIfStatement
        switch (id) {
            case 2131361865:
                Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivityForResult(i, RESULT_OK);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    private class Twitter extends AsyncTask<String, Integer, List> {

        @Override
        protected List doInBackground(String... params) {

            if (!getTimeline(params[0]).isEmpty()) ;

            return getTimeline(params[0]);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // int progreso = values[0].intValue();

            //pbarProgreso.setProgress(progreso);
        }

        public List getTimeline(String hashtag) {
            TwitterClass tweet = new TwitterClass();
            return tweet.getTimeline(hashtag);
        }

        @Override
        protected void onPreExecute() {
            // pbarProgreso.setMax(100);
            //   pbarProgreso.setProgress(0);
        }

        @Override
        protected void onPostExecute(List result) {
            if (!result.isEmpty())
                Toast.makeText(MainActivity.this.getBaseContext(), "Timeline recibido", Toast.LENGTH_LONG).show();
            LinearLayout ll = new LinearLayout(MainActivity.this.getBaseContext());
            TextView[] users = new TextView[result.size()];

            for (int i = 0; i < result.size(); i++) {
                Log.d("users",result.get(i).toString());
                ll.setOrientation(LinearLayout.VERTICAL);
                users[i] = new TextView(MainActivity.this.getBaseContext());
                users[i].setGravity(Gravity.LEFT);
                users[i].setTextColor(getResources().getColor(android.R.color.black));

                users[i].setPadding(20, 20, 20, 20);
                users[i].setTextSize(14);
                users[i].setText(result.get(i).toString());
                ll.addView(users[i]);
            }

            sc.addView(ll);
        }

        @Override
        protected void onCancelled() {

        }
    }
}
