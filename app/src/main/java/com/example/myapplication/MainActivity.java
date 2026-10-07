package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RadioGroup radioGroupPytania;
    RadioButton radioButtonA, radioButtonB, radioButtonC;
    Button buttonNastepne;
    TextView textViewPytanie;
    List<Pytanie> listaPytan;
    int aktualnyIndeks = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroupPytania = findViewById(R.id.radioGroupPytania);
        radioButtonA = findViewById(R.id.radioButton);
        radioButtonB = findViewById(R.id.radioButton2);
        radioButtonC = findViewById(R.id.radioButton3);
        buttonNastepne = findViewById(R.id.button);
        textViewPytanie = findViewById(R.id.textViewPytanie);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/Danq28/retrofit_pytania/main/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Pytanie>> call = jsonPlaceHolderApi.getPytania();

        call.enqueue(new Callback<List<Pytanie>>() {
            @Override
            public void onResponse(Call<List<Pytanie>> call, Response<List<Pytanie>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Błąd: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                listaPytan = response.body();
                aktualnyIndeks = 0;
                wyswietlPytanie(aktualnyIndeks);
            }

            @Override
            public void onFailure(Call<List<Pytanie>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Błąd połączenia: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        buttonNastepne.setOnClickListener(v -> {
            if (listaPytan != null && !listaPytan.isEmpty()) {
                aktualnyIndeks++;
                if (aktualnyIndeks < listaPytan.size()) {
                    wyswietlPytanie(aktualnyIndeks);
                } else {
                    Toast.makeText(MainActivity.this, "Koniec pytań", Toast.LENGTH_SHORT).show();
                    aktualnyIndeks = listaPytan.size() - 1;
                }
            }
        });
    }

    private void wyswietlPytanie(int index) {
        if (listaPytan != null && index >= 0 && index < listaPytan.size()) {
            Pytanie aktualnePytanie = listaPytan.get(index);

            textViewPytanie.setText(aktualnePytanie.getTresc());
            radioButtonA.setText(aktualnePytanie.getOdp_a());
            radioButtonB.setText(aktualnePytanie.getOdp_b());
            radioButtonC.setText(aktualnePytanie.getOdp_c());

            radioGroupPytania.clearCheck();
        }
    }
}