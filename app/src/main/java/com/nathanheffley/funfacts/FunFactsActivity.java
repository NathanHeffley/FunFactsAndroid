package com.nathanheffley.funfacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    public final String API_URL = "http://theresonance.space/";

    // Declare necessary view variables
    public TextView factTextView;
    public Button showFactButton;

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

        View.OnClickListener buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick beginning execution");

                setRandomFact();

                Log.d(TAG, "onClick successfully executed");
            }
        };

        // Setup the button listener
        showFactButton.setOnClickListener(buttonListener);

        setRandomFact();

        Log.d(TAG, "onCreate successfully executed");
    }

    public void setRandomFact() {
        Call<Fact> call = service.getRandomFact();
        call.enqueue(new Callback<Fact>() {
            @Override
            public void onResponse(Call<Fact> call, Response<Fact> response) {
                Log.d(TAG, "Response status code: " + response.code());
                if (response.isSuccessful()) {
                    Fact fact = response.body();
                    factTextView.setText(fact.getText());
                } else {
                    factTextView.setText(R.string.missing_fact);
                    Log.e(TAG, "getRandomFact() call was unsuccessful");
                }
            }

            @Override
            public void onFailure(Call<Fact> call, Throwable t) {
                factTextView.setText(R.string.missing_fact);
                Log.e(TAG, t.getMessage());
            }
        });
    }
}
