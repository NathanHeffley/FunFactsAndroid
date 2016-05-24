package com.nathanheffley.funfacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;

public class FunFactsActivity extends AppCompatActivity {
    public final String TAG = FunFactsActivity.class.getSimpleName();

    // Declare necessary view variables
    private TextView factTextView;
    private Button showFactButton;
    private RelativeLayout factViewLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate beginning execution");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_facts);

        // Assign the views from the layout file to the corresponding variables
        factTextView = (TextView) findViewById(R.id.factTextView);
        showFactButton = (Button) findViewById(R.id.showFactButton);
        factViewLayout = (RelativeLayout) findViewById(R.id.factViewLayout);

        View.OnClickListener buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick beginning execution");

                // Update the screen with our next fact
                factTextView.setText(FactBook.getFact());

                // Get the new color to be used
                int themeColor = ColorWheel.getColor();
                // Update the screen with our new themeColor
                factViewLayout.setBackgroundColor(themeColor);
                // Update the button text with our new themeColor
                showFactButton.setTextColor(themeColor);

                Log.d(TAG, "onClick successfully executed");
            }
        };

        // Setup the button listener
        showFactButton.setOnClickListener(buttonListener);

        // Set the default fun fact
        factTextView.setText(FactBook.getFact());

        // Get the first color to be used
        int themeColor = ColorWheel.getColor();
        // Update the screen with our first themeColor
        factViewLayout.setBackgroundColor(themeColor);
        // Update the button text with our first themeColor
        showFactButton.setTextColor(themeColor);

        Log.d(TAG, "onCreate successfully executed");
    }
}
