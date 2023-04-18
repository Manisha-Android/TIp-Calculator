package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText billAmountEditText;
    private SeekBar tipPercentageSeekBar;
    private TextView tipAmountTextView;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        billAmountEditText = findViewById(R.id.billAmountEditText);
        tipPercentageSeekBar = findViewById(R.id.tipPercentageSeekBar);
        tipAmountTextView = findViewById(R.id.tipAmountTextView);

        billAmountEditText.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateTip();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        });

        SeekBar tipSeekBar = findViewById(R.id.tipPercentageSeekBar);
        TextView tipTextView = findViewById(R.id.tipPercentageTextView);
        tipSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                tipTextView.setText(progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @SuppressLint("DefaultLocale")
    private void calculateTip() {
        try {
            String billAmountStr = billAmountEditText.getText().toString();
            if (!billAmountStr.isEmpty()) {
                double billAmountValue = Double.parseDouble(billAmountStr);
                int tipPercentageValue = tipPercentageSeekBar.getProgress();
                double tipAmountValue = billAmountValue * tipPercentageValue / 100.0;
                tipAmountTextView.setText(String.format("%.2f", tipAmountValue));
            } else {
                tipAmountTextView.setText("");
            }
        } catch (NumberFormatException e) {
            tipAmountTextView.setText("");
        }
    }
}
