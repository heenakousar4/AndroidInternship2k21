package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.media.tv.TvContentRating;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText country;
    TextView conf,act,rec,dea;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        country=findViewById(R.id.country);
        conf = findViewById(R.id.conf);
        act = findViewById(R.id.act);
        rec = findViewById(R.id.rec);
        dea = findViewById(R.id.dea);
        pd=new ProgressDialog(this);
        pd.setMessage("Please wait.....");
        pd.setProgress(ProgressDialog.STYLE_SPINNER);
    }

    public void search(View view) {
        String c= country.getText().toString().trim();
        pd.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https;//api.covid19api.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Call<String> response = retrofit.create(MyInterface.class).getValue(c);

    }
}