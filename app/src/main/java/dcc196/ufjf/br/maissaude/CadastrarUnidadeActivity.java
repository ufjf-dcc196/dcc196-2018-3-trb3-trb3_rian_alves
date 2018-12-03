package dcc196.ufjf.br.maissaude;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dcc196.ufjf.br.maissaude.Modelo.CEP;
import dcc196.ufjf.br.maissaude.Servico.APIViaCEP;
import dcc196.ufjf.br.maissaude.Servico.CEPDeserializer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CadastrarUnidadeActivity extends AppCompatActivity {

    private EditText edtCEP;
    private EditText edtNomeUnidade;
    private TextView txtLogradouro;
    private TextView txtBairro;
    private TextView txtLocalidade;
    private TextView txtUF;

    private Button btnBuscaCEP;
    private Button btnCadastrarUSF;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_unidade);

        edtCEP = (EditText)findViewById(R.id.edt_CEP2);
        edtNomeUnidade = (EditText) findViewById(R.id.edt_nome_usf);
        txtLogradouro = (TextView) findViewById(R.id.txt_logradouro);
        txtBairro = (TextView) findViewById(R.id.txt_bairro);
        txtLocalidade = (TextView) findViewById(R.id.txt_localidade);
        txtUF = (TextView) findViewById(R.id.txt_uf);

        btnBuscaCEP = (Button) findViewById(R.id.btn_buscar2);
        btnCadastrarUSF = (Button) findViewById(R.id.btn_cadastrarusf);

        Gson g = new GsonBuilder().registerTypeAdapter(CEP.class, new CEPDeserializer()).create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIViaCEP.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(g))
                .build();

        final APIViaCEP serviceCEP = retrofit.create(APIViaCEP.class);

        btnBuscaCEP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtCEP.getText().toString().isEmpty())
                {
                    Call<CEP> callDadosCEP = serviceCEP.getEnderecoByCEP(edtCEP.getText().toString());
                    callDadosCEP.enqueue(new Callback<CEP>() {
                        @Override
                        public void onResponse(Call<CEP> call, Response<CEP> response) {
                            if(!response.isSuccessful()){
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Erro",
                                        Toast.LENGTH_LONG).show();
                            } else{
                                CEP cep = response.body();
                                txtLogradouro.setText(cep.getLogradouro().toString());
                                txtBairro.setText(cep.getBairro().toString());
                                txtLocalidade.setText(cep.getLocalidade().toString());
                                txtUF.setText(cep.getUf().toString());
                                Log.d("CEP: ",cep.toString());

                            }
                        }

                        @Override
                        public void onFailure(Call<CEP> call, Throwable t) {
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Erro geral",
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }
            }
        });
    }


}
