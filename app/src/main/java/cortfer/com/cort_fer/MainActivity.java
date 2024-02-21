package cortfer.com.cort_fer;

import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    // Variáveis para componentes da interface do usuário
    private TextView textResultado;
    private EditText editLargura, editAltura, editQuantidade;
    private Spinner spinnerValor, spinnerEspessura; // Spinner para o valor e espessura

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialização dos componentes da interface do usuário
        textResultado = findViewById(R.id.textResultado);
        editLargura = findViewById(R.id.editLargura);
        editAltura = findViewById(R.id.editAltura);
        editQuantidade = findViewById(R.id.editQuantidade);

        // Configurando o spinner para escolha de valores
        spinnerValor = findViewById(R.id.spinnerValor);
        String[] valores = new String[]{"Selecionar o valor", "17", "18"};
        ArrayAdapter<String> adapterValor = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, valores);
        spinnerValor.setAdapter(adapterValor);

        // Configurando o spinner para escolha de espessuras
        spinnerEspessura = findViewById(R.id.spinnerEspessura);
        Double[] valoresEspessura = new Double[]{0.45, 0.50, 0.65, 0.75, 0.80, 0.85, 0.90, 0.95, 1.00, 1.10, 1.25, 1.50, 2.00, 2.70, 3.00, 4.75};
        ArrayAdapter<Double> adapterEspessura = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, valoresEspessura);
        spinnerEspessura.setAdapter(adapterEspessura);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Verifica se o usuário está logado ao iniciar a atividade
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            Intent intent = new Intent(MainActivity.this, formLogin.class);
            startActivity(intent);
            finish(); // Finaliza a atividade se o usuário não estiver logado
        }
    }

    // Método para calcular o valor com base nos dados inseridos
    public void calcularvalor(View view) {
        try {
            float largura = Float.parseFloat(editLargura.getText().toString());
            float altura = Float.parseFloat(editAltura.getText().toString());

            if (spinnerEspessura.getSelectedItem() == null || spinnerValor.getSelectedItem() == null) {
                textResultado.setText("Selecione espessura e valor.");
                return;
            }

            double espessura = Double.parseDouble(spinnerEspessura.getSelectedItem().toString());
            int valor = Integer.parseInt(spinnerValor.getSelectedItem().toString());

            float quantidade = Float.parseFloat(editQuantidade.getText().toString());
            double resultado = largura * altura * espessura * valor * quantidade * 7.89;

            DecimalFormat df = new DecimalFormat("######");
            textResultado.setText("Resultado: " + df.format(resultado));
        } catch (NumberFormatException e) {
            textResultado.setText("Erro: entrada inválida.");
        } catch (Exception e) {
            textResultado.setText("Erro: " + e.getMessage());
        }
    }

    // Método para apagar todos os dados inseridos
    public void apagarTodosDados(View view){
        editLargura.setText("");
        editAltura.setText("");
        spinnerEspessura.setSelection(0); // Reseta a seleção do Spinner de espessura
        spinnerValor.setSelection(0); // Reseta a seleção do Spinner de valor
        editQuantidade.setText("");
        textResultado.setText("");
    }

    // Método para deslogar o usuário e retornar à tela de login
    public void deslogarUsuario(View view) {
        FirebaseAuth.getInstance().signOut(); // Desloga o usuário
        Intent intent = new Intent(MainActivity.this, formLogin.class);
        startActivity(intent);
        finish(); // Encerra a atividade atual
    }
}
