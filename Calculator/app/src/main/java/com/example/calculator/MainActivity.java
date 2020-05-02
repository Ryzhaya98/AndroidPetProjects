package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity {
    Button btn0, btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btnPercen,btnMult,btnDiv,btnEqually, btnBracket,btnMin, btnSum,btnToch,btnClear;

    TextView tvInput, tvOutput;
    String enter;
    Boolean checkBracket = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn0 = (Button)findViewById(R.id.btnNull);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);
        btn5 = (Button)findViewById(R.id.btn5);
        btn6 = (Button)findViewById(R.id.btn6);
        btn7 = (Button)findViewById(R.id.btn7);
        btn8 = (Button)findViewById(R.id.btn8);
        btn9 = (Button)findViewById(R.id.btn9);
        btnPercen = (Button)findViewById(R.id.btnPercent);
        btnMult = (Button)findViewById(R.id.btnMult);
        btnDiv = (Button)findViewById(R.id.btnDiv);
        btnEqually = (Button)findViewById(R.id.btnEqually);
        btnBracket = (Button)findViewById(R.id.btnBracket);
        btnMin = (Button)findViewById(R.id.btnMin);
        btnSum = (Button)findViewById(R.id.btnSum);
        btnToch = (Button)findViewById(R.id.btnTochka);
        btnClear = (Button)findViewById(R.id.btnClear);

        tvInput = findViewById(R.id.tvInput);
        tvOutput = findViewById(R.id.tvOutput);


        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvInput.setText("");
                tvOutput.setText("");
            }
        });


        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enter = tvInput.getText().toString();
                tvInput.setText(enter + "0");
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enter = tvInput.getText().toString();
                tvInput.setText(enter + "1");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enter = tvInput.getText().toString();
                tvInput.setText(enter + "2");
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enter = tvInput.getText().toString();
                tvInput.setText(enter + "3");
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enter = tvInput.getText().toString();
                tvInput.setText(enter + "4");
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enter = tvInput.getText().toString();
                tvInput.setText(enter + "5");
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enter = tvInput.getText().toString();
                tvInput.setText(enter + "6");
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enter = tvInput.getText().toString();
                tvInput.setText(enter + "7");
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enter = tvInput.getText().toString();
                tvInput.setText(enter + "8");
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enter = tvInput.getText().toString();
                tvInput.setText(enter + "9");
            }
        });

        btnMult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enter = tvInput.getText().toString();
                tvInput.setText(enter + "x");
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enter = tvInput.getText().toString();
                tvInput.setText(enter + "÷");
            }
        });

        btnMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enter = tvInput.getText().toString();
                tvInput.setText(enter + "-");
            }
        });

        btnSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enter = tvInput.getText().toString();
                tvInput.setText(enter + "+");
            }
        });

        btnPercen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enter = tvInput.getText().toString();
                tvInput.setText(enter + "%");
            }
        });

        btnBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkBracket){
                    enter = tvInput.getText().toString();
                    tvInput.setText(enter + ")");
                    checkBracket = false;
                }else {
                    enter = tvInput.getText().toString();
                    tvInput.setText(enter + "(");
                    checkBracket = true;
                }
            }
        });

        btnEqually.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                enter = tvInput.getText().toString();

                enter = enter.replaceAll("x", "*");
                enter = enter.replaceAll("%", "/100");

                Context rhino = Context.enter();

                rhino.setOptimizationLevel(-1);

                String result = "";

                try {
                    Scriptable scriptable = rhino.initSafeStandardObjects();
                    result = rhino.evaluateString(scriptable,enter,"javascript", 1,null).toString();
                }catch (Exception e){
                    result = "0";
                }
                tvOutput.setText(result);
            }
        });



    }
}
