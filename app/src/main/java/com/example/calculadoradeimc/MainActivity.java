package com.example.calculadoradeimc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.calculadoradeimc.databinding.ActivityMainBinding;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String peso = binding.editPeso.getText().toString();
                String altura = binding.editAltura.getText().toString();

                if (peso.isEmpty()){
                    binding.editPeso.setError("Informe seu peso!");
                } else if (altura.isEmpty()) {
                    binding.editAltura.setError("Informe sua altura!");
                }else{
                    calcularImc();
                }

            }
        });
    }

    private void calcularImc() {

        float peso = Float.parseFloat(binding.editPeso.getText().toString().replace(",","."));
        float altura = Float.parseFloat(binding.editAltura.getText().toString().replace(",","."));
        float imc = peso/(altura*altura);

        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        if (imc < 18.5){
            binding.txtResultado.setText("Seu IMC é de: " + decimalFormat.format(imc) + "\nPeso Baixo");
        } else if (imc <= 24.9) {
            binding.txtResultado.setText("Seu IMC é de: " + decimalFormat.format(imc) + "\nPeso Normal");
        } else if (imc <= 29.9) {
            binding.txtResultado.setText("Seu IMC é de: " + decimalFormat.format(imc) + "\nSobre Peso");
        } else if (imc <= 34.9) {
            binding.txtResultado.setText("Seu IMC é de: " + decimalFormat.format(imc) + "\nObesidade (Grau I)");
        } else if (imc <= 39.9) {
            binding.txtResultado.setText("Seu IMC é de: " + decimalFormat.format(imc) + "\nObesidade Severa (Grau II)");
        } else {
            binding.txtResultado.setText("Seu IMC é de: " + decimalFormat.format(imc) + "\nObesidade Morbida (Grau III)");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();

        if (itemId == R.id.ic_limpar){
            binding.editPeso.setText("");
            binding.editAltura.setText("");
            binding.txtResultado.setText("");
        }

        return super.onOptionsItemSelected(item);
    }
}