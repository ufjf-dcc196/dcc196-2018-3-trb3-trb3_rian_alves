package dcc196.ufjf.br.maissaude;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnBuscarCEP;
    Button btnCadastrarUnidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBuscarCEP = (Button) findViewById(R.id.btn_usf);
        btnCadastrarUnidade = (Button) findViewById(R.id.btn_cadastrar);

        btnBuscarCEP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BuscaUnidadeActivity.class);
                startActivity(intent);
            }
        });

        btnCadastrarUnidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastrarUnidadeActivity.class);
                startActivity(intent);
            }
        });



    }
}
