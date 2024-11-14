package com.example.calculo13app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Referência aos elementos de layout
        EditText salaryEditText = findViewById(R.id.salary);
        EditText dependentsEditText = findViewById(R.id.qtyDependents);
        EditText monthsEditText = findViewById(R.id.months);
        Spinner parcelasSpinner = findViewById(R.id.spinnerParcelas);
        Button calcButton = findViewById(R.id.calcButton);

        // Configura o evento de clique do botão de cálculo
        calcButton.setOnClickListener(v -> {
            // Captura os valores de cada campo
            String salaryStr = salaryEditText.getText().toString();
            String dependents = dependentsEditText.getText().toString();
            String months = monthsEditText.getText().toString();
            String parcelaSelecionada = parcelasSpinner.getSelectedItem().toString();

            // Verifica se o salário foi preenchido
            if (!salaryStr.isEmpty()) {
                // Converte o salário para double
                double salarioBruto = Double.parseDouble(salaryStr);

                // Definição das alíquotas
                double aliquotaINSS = 0.1102; // 11.02%
                double aliquotaIRRF = 0.2750; // 27.50%

                // Cálculo do INSS
                double descontoINSS = salarioBruto * aliquotaINSS;

                // Cálculo do IRRF
                double descontoIRRF = salarioBruto * aliquotaIRRF;

                // Cálculo do valor líquido
                double totalDescontos = descontoINSS + descontoIRRF;
                double decimoTerceiroLiquido = salarioBruto - totalDescontos;

                // Exibe os valores calculados no Logcat
                System.out.println("FormularioApp" + " Salário Bruto: R$ " + salarioBruto);
                System.out.println("FormularioApp" + " INSS (11.02%): R$ " + descontoINSS);
                System.out.println("FormularioApp" + " IRRF (27.50%): R$ " + descontoIRRF);
                System.out.println("FormularioApp" + " Total de Descontos: R$ " + totalDescontos);
                System.out.println("FormularioApp" + " Décimo Terceiro Líquido: R$ " + decimoTerceiroLiquido);
            } else {
                System.out.println("FormularioApp" + " Por favor, insira o salário bruto.");
            }
        });
    }
}
