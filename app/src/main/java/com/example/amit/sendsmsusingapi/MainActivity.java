package com.example.amit.sendsmsusingapi;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText sender,number,message;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sender=(EditText)findViewById(R.id.edit_sender);
        number=(EditText)findViewById(R.id.edit_number);
        message=(EditText)findViewById(R.id.edit_message);
        send=(Button)findViewById(R.id.btn_send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // Construct data
                    /*String API="afSQLFKHT0a9lQlXlmLJgQ";
                    String user="bislap";
                    String password="124578";
                    String  senderid="BACIND";
                    String channel="Trans";
                    String route="1";
                    String DCS="0";
                    String flashsms="0";
                    String user=Sender.getText().toString();
                    String number=Number.getText().toString();
                    String message=Message.getText().toString();*/



                    String apiKey = "apikey=" + "A7+DKbFGNRI-ZsJwMca7D9qRibns1haBwLNvfha6Kw";
                    String Message = "&message=" + message.getText().toString();
                    String Sender = "&sender=" + sender.getText().toString();
                    String Numbers = "&numbers=" + number.getText().toString();

                    // Send data
                    HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
                    String data = apiKey + Numbers + Message + Sender ;
                    /*HttpURLConnection conn = (HttpURLConnection) new URL("http://103.16.143.182/api/mt/SendSMS?").openConnection();
                    String data = API+user+number+message;*/
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
                    conn.getOutputStream().write(data.getBytes("UTF-8"));
                    final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    final StringBuffer stringBuffer = new StringBuffer();
                    String line;
                    while ((line = rd.readLine()) != null) {
                        stringBuffer.append(line);
                        Toast.makeText(getApplicationContext(),"The Message is "+line,Toast.LENGTH_LONG).show();
                    }
                    rd.close();

                    //String response = stringBuffer.toString();
                } catch (Exception e) {
                    //System.out.println("Error SMS "+e);
                    Toast.makeText(getApplicationContext(),"The Error Message is"+e,Toast.LENGTH_LONG).show();
                }
            }
        });
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }
}
