package com.nemesis.moises.nemesis;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class MainActivity extends ActionBarActivity {
    Button btnRun;
    EditText etText;
    EditText etResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRun = (Button) findViewById(R.id.btnRun);
        etText = (EditText) findViewById(R.id.etText);
        etResult = (EditText) findViewById(R.id.etResult);

        btnRun.setOnClickListener(new View.OnClickListener() {


            @Override

            public void onClick(View view) {
                String text = etText.getText().toString();
                Log.d("IN", text);
                if (!text.isEmpty()) {
                    etResult.setText(" ");
                    String result = EncodeTo(text);
                    etResult.setText(result);
                }
            }
        });
    }


    private String EncodeTo(String text) {
        String result = "";
        Queue aux = new LinkedList();
        Stack pila = new Stack();
        if (pila.isEmpty() && aux.isEmpty())
            for (int i = 0; i < text.length(); i++)   //letras pares se insertan al comienzo y las impares al final
            {

                if (i % 2 != 0) {
                    pila.push(text.charAt(i));

                } else {

                    // aux.add(text.charAt(i));

                }
            }

        for (int i = 0; i < text.length(); i++) {
            if (i % 2 == 0) {
                result += pila.pop();  //la inversa de como se introducen las letras
            } else {
                result += aux.remove();
            }

        }


        aux.clear();
        pila.clear();


        return result;
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
