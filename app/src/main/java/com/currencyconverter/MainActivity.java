package com.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText amountInput;
    Button convertBtn;
    Spinner currencyDropDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createDropDownCurrency();
        convert();
    }

    private void createDropDownCurrency(){
        currencyDropDown = (Spinner) findViewById(R.id.spinnerCurrency);
        String[] items = new String[]{Currencies.USD.name(), Currencies.EURO.name(), Currencies.RUR.name()};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        currencyDropDown.setAdapter(adapter);
    }

    private void convert(){
        amountInput = (EditText) findViewById(R.id.amountField);
        convertBtn = (Button) findViewById(R.id.convertBtn);

        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = String.valueOf(amountInput.getText());
                Spinner sp = findViewById(R.id.spinnerCurrency);
                String spString = sp.getSelectedItem().toString();
                if(!amount.equals("")) {
                    double amountForConvert = Double.parseDouble(String.valueOf(amountInput.getText()));
                    String convertationResult = calculation(amountForConvert, getCurrency(spString));
                    @SuppressLint("ShowToast") Toast toast = Toast.makeText(MainActivity.this, convertationResult, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 400);
                    LinearLayout toastLayout = (LinearLayout) toast.getView();
                    TextView toastTV = (TextView) toastLayout.getChildAt(0);
                    toastTV.setTextSize(30);
                    toast.show();
                }
            }
        });
    }

    private Currencies getCurrency(String currencyName){
        Currencies currency;
        switch (currencyName) {
            case "USD":
                currency = Currencies.USD;
                break;
            case "EURO":
                currency = Currencies.EURO;
                break;
            case "RUR":
                currency = Currencies.RUR;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + currencyName);
        }
        return currency;
    }

    @SuppressLint("DefaultLocale")
    private String calculation(double amount, Currencies currency){
        return String.format("%.2f", (amount / Double.parseDouble(currency.toString())));
//        return amount / Double.parseDouble(currency.toString());
    }
}