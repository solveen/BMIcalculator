package solveen.example.bmicalculator;


import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.text.TextUtils;

import android.view.View;

import android.widget.Button;

import android.widget.EditText;

import android.widget.TextView;

import android.widget.Toast;



import java.util.regex.Matcher;

import java.util.regex.Pattern;



public class MainActivity extends AppCompatActivity {





    private EditText txt_height,txt_weight;

    private Button btn_submint;

    private TextView BMI;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        txt_height = findViewById(R.id.txt_height);

        txt_weight=findViewById(R.id.txt_weight);

        btn_submint=findViewById(R.id.btn_calc);

        BMI = findViewById(R.id.BMI);

        btn_submint.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                if (validate()) {

                    float height = Float.parseFloat(txt_height.getText().toString());

                    float weight = Float.parseFloat(txt_weight.getText().toString());

                    bmical calc = new bmical();

                    calc.setHeight(height);

                    calc.setWeight(weight);

                    float bim = calc.calculate();

                    BMI.setText(Float.toString(bim));

                    if (bim < 18.50) {



                        Toast.makeText(getApplicationContext(), "UnderWeight", Toast.LENGTH_LONG).show();



                    } else if (bim >= 18.5 && bim < 25) {

                        Toast.makeText(getApplicationContext(), "Normal Weight", Toast.LENGTH_LONG).show();

                    } else if (bim >= 25 && bim <= 30) {

                        Toast.makeText(getApplicationContext(), "Over weight", Toast.LENGTH_LONG).show();

                    } else if (bim > 30) {

                        Toast.makeText(getApplicationContext(), "Obesity", Toast.LENGTH_LONG).show();

                    }

                }

            }

        });

    }



    private boolean validate() {

        boolean flag = true;

        Pattern p = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+");

        Matcher m1 = p.matcher(txt_height.getText().toString());

        Matcher m2 = p.matcher(txt_weight.getText().toString());



        //Validating for Empty Text

        if (TextUtils.isEmpty(txt_height.getText().toString())) {

            txt_height.setError("Enter Height");

            txt_height.requestFocus();

            flag = false;

        } else if (TextUtils.isEmpty(txt_weight.getText().toString())) {

            txt_weight.setError("Enter Weight");

            txt_weight.requestFocus();

            flag = false;

        }

        else if (!m1.find())    //validating for number only

        {

            Toast.makeText(getApplicationContext(), "Enter a valid height number", Toast.LENGTH_LONG).show();

            txt_height.setError("Enter Valid Height Number");

            txt_height.requestFocus();

            flag = false;

        }

        else if (!m2.find())

        {

            Toast.makeText(getApplicationContext(), "Enter a valid weight number", Toast.LENGTH_LONG).show();

            txt_height.setError("Enter Valid Weight Number");

            txt_weight.requestFocus();

            flag = false;

        }

        return flag;

    }

}