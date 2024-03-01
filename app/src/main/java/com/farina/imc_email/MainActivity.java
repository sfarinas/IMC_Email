package com.farina.imc_email;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtNome, edtPeso, edtAltura, edtDestino, edtMensagem;
    double imc = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNome = findViewById(R.id.edtEmail);
        edtPeso = findViewById(R.id.edtPeso);
        edtAltura = findViewById(R.id.edtAltura);
        edtDestino = findViewById(R.id.edtDestinatario);
        edtMensagem = findViewById(R.id.edtMensagem);

    }
    public void IMC(View view){
        double peso, altura;
        try {
            altura = Double.parseDouble(edtAltura.getText().toString());
            peso = Double.parseDouble(edtPeso.getText().toString());

            this.imc = peso / Math.pow(altura, 2);
            Toast toast = Toast.makeText(getApplicationContext(), "IMC = " +
                    String.format("%.2f", this.imc), Toast.LENGTH_LONG);
            toast.show();
        } catch (Exception e){
            
        }

    }

}