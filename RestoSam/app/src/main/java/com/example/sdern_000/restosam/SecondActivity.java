package com.example.sdern_000.restosam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;

import com.example.sdern_000.restosam.Menu.MenuActivity;

public class SecondActivity extends AppCompatActivity {

    TextView editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_second);

        editText = (TextView) findViewById(R.id.editText);
        editText.setText("HUY");
        button = (Button) findViewById(R.id.button);

        OnClickListener oclBtnOk = new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Меняем текст в TextView (tvOut)
                editText.setText("Нажата кнопка ОК");
            }
        };

        // присвоим обработчик кнопке OK (btnOk)
        button.setOnClickListener(oclBtnOk);
    }

    public void OnClick(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
