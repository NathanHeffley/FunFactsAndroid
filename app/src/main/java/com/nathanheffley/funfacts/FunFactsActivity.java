package com.nathanheffley.funfacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nathanheffley.funfacts.api.Fact;
import com.nathanheffley.funfacts.api.FunFactsService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FunFactsActivity extends AppCompatActivity {
    public final String TAG = FunFactsActivity.class.getSimpleName();
    private final String API_URL = "http://theresonance.space/";

    // Declare necessary view variables
    private TextView factTextView;
    private Button showFactButton;
    private RelativeLayout factViewLayout;

    // Networking library parts
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private FunFactsService service = retrofit.create(FunFactsService.class);

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

                setRandomFact();
                setColors();

                Log.d(TAG, "onClick successfully executed");
            }
        };

        // Setup the button listener
        showFactButton.setOnClickListener(buttonListener);

        setRandomFact();
        setColors();

        Log.d(TAG, "onCreate successfully executed");
    }

    public void setColors() {
        // Get the new color to be used
        int themeColor = ColorWheel.getColor();
        // Update the screen with our new themeColor
        factViewLayout.setBackgroundColor(themeColor);
        // Update the button text with our new themeColor
        showFactButton.setTextColor(themeColor);
    }

    public void setRandomFact() {
        Call<Fact> call = service.getRandomFact();
        call.enqueue(new Callback<Fact>() {
            @Override
            public void onResponse(Call<Fact> call, Response<Fact> response) {
                Log.d(TAG, "Response status code: " + response.code());
                if (response.isSuccessful()) {
                    Fact fact = response.body();
                    if (response.isSuccessful()) {
                        factTextView.setText(fact.getText());
                    } else {
                        factTextView.setText(R.string.missing_fact);
                    }
                } else {
                    Log.e(TAG, "getRandomFact() call was unsuccessful");
                }
            }

            @Override
            public void onFailure(Call<Fact> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }
}
