package android.exercises.exe1;

import static java.lang.String.format;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    TextView resultTextView, temporaryTextView;

    float temporarySum;
    char lastOperatorUsed;
    boolean isNewCalculation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);
        temporaryTextView = findViewById(R.id.temporaryTextView);

        refreshCalculator();
    }

    public void reset(View view) {
        resultTextView.setText("");
        temporaryTextView.setText("");

        refreshCalculator();
    }

    public void concatNumber(View view) {
        Button button = (Button) view;

        if (isNewCalculation) {
            resultTextView.setText(button.getText());
            isNewCalculation = false;
        } else {
            resultTextView.append(button.getText());
        }
    }

    public void operatorAction(View view) {
        Button button = (Button) view;

        updateTemporarySum();

        lastOperatorUsed = button.getText().toString().charAt(0);
        resultTextView.setText("");
        temporaryTextView.setText(format("%s %s", formatNumberToString(temporarySum), lastOperatorUsed));
    }

    public void calculateResult(View view) {
        updateTemporarySum();

        resultTextView.setText(formatNumberToString(temporarySum));
        temporaryTextView.setText("");
        refreshCalculator();
    }

    private void updateTemporarySum() {
        String textViewText = resultTextView.getText().toString();

        if (textViewText.length() > 0) {
            try {
                float newOperand = Float.parseFloat(textViewText);

                switch (lastOperatorUsed) {
                    case ('+'): {
                        temporarySum += newOperand;
                        break;
                    }
                    case ('-'): {
                        temporarySum -= newOperand;
                        break;
                    }
                    case ('%'): {
                        temporarySum /= newOperand;
                        break;
                    }
                    case ('*'): {
                        temporarySum *= newOperand;
                        break;
                    }
                    default: {
                        break;
                    }
                }
            } catch (Exception ignored) {

            }
        }
    }

    private void refreshCalculator() {
        temporarySum = 0;
        lastOperatorUsed = '+';
        isNewCalculation = true;
    }

    private String formatNumberToString(float number) {
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(3);

        return decimalFormat.format(number);
    }
}