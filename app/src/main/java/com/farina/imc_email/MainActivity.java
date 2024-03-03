package com.farina.imc_email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
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
            Toast toast = Toast.makeText(getApplicationContext(), "Ocorreu algum problema nos dados da Pessoa !",
                    Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void enviarEmail(View view){
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        // DEFINA AQUI O ENDEREÇO DO REMETENTE
        emailIntent.setData(Uri.parse("mailfrom:" + edtNome.getText()));
        emailIntent.setData(Uri.parse("mailto:" + edtDestino.getText()));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Avaliacao do IMC");
        IMC(view);
        String conteudo;
        conteudo = "Nome: " + edtNome.getText();
        conteudo += "\nPeso: " + edtPeso.getText();
        conteudo += "\nAltura: " + edtAltura.getText();
        conteudo += "\nIMC = " + String.format("%.2f", this.imc);

        edtMensagem.setText(conteudo);
        emailIntent.putExtra(Intent.EXTRA_TEXT, conteudo);

        try {
            startActivity(Intent.createChooser(emailIntent, "Envian E-mail..."));
        } catch (android.content.ActivityNotFoundException ex){
            Toast.makeText(MainActivity.this, "Nao existe Clientes configurados", Toast.LENGTH_LONG).show();
        }

    }

}