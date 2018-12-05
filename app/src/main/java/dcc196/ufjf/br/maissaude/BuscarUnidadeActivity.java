package dcc196.ufjf.br.maissaude;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.ArrayList;

import dcc196.ufjf.br.maissaude.DAO.SaudeContract;
import dcc196.ufjf.br.maissaude.DAO.SaudeDBHelper;
import dcc196.ufjf.br.maissaude.Modelo.CEP;
import dcc196.ufjf.br.maissaude.Modelo.Registro;
import dcc196.ufjf.br.maissaude.Servico.APIViaCEP;
import dcc196.ufjf.br.maissaude.Servico.CEPDeserializer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BuscarUnidadeActivity extends AppCompatActivity {

    private  Registro registro,registro1,registro2,registro3,registro4;
    private  ArrayList<Registro> lstRegistros = new ArrayList<Registro>();

    private EditText edtCEP;
    private TextView txtNomeUnidade;
    private TextView txtLogradouro;
    private TextView txtNumero;
    private TextView txtBairro;
    private TextView txtLocalidade;
    private TextView txtUF;
    private Spinner spTipos;
    private Button btnBuscaCEP;

    private SaudeDBHelper dbHelper;

    private String[]tipos = new String[]{"Atenção Básica", "Imunização","Pronto Atendimento"} ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_unidade);

        dbHelper = new SaudeDBHelper(getApplicationContext());

        //CarregarBD();

        ArrayAdapter<String> adapterTipo = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,tipos);
        adapterTipo.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);


        spTipos = (Spinner)findViewById(R.id.sp_tipo);
        edtCEP = (EditText)findViewById(R.id.edt_CEP2);
        txtNomeUnidade = (TextView) findViewById(R.id.txt_unidade);
        txtLogradouro = (TextView) findViewById(R.id.txt_logradouro);
        txtNumero = (TextView) findViewById(R.id.txt_numero);
        txtBairro = (TextView) findViewById(R.id.txt_bairro);
        txtLocalidade = (TextView) findViewById(R.id.txt_localidade);
        txtUF = (TextView) findViewById(R.id.txt_uf);

        btnBuscaCEP = (Button) findViewById(R.id.btn_buscar2);

        spTipos.setAdapter(adapterTipo);
        spTipos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Log.d("Combo: ",spTipos.getSelectedItem().toString());

        Gson g = new GsonBuilder().registerTypeAdapter(CEP.class, new CEPDeserializer()).create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIViaCEP.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(g))
                .build();

        final APIViaCEP serviceCEP = retrofit.create(APIViaCEP.class);

        btnBuscaCEP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Registro reg = buscaUnidade(edtCEP.getText().toString(),spTipos.getSelectedItem().toString());

                    Call<CEP> callDadosCEP = serviceCEP.getEnderecoByCEP(reg.getCEPUnidade());
                    callDadosCEP.enqueue(new Callback<CEP>() {
                        @Override
                        public void onResponse(Call<CEP> call, Response<CEP> response) {
                            if(!response.isSuccessful()){
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Erro, este CEP não está cadastrado",
                                        Toast.LENGTH_LONG).show();
                            } else{
                                CEP cep = response.body();
                                txtNomeUnidade.setText(reg.getNomeUnidade());
                                txtNumero.setText(String.valueOf(reg.getNumero()));
                                txtLogradouro.setText(cep.getLogradouro().toString());
                                txtBairro.setText(cep.getBairro().toString());
                                txtLocalidade.setText(cep.getLocalidade().toString());
                                txtUF.setText(cep.getUf().toString());

                               File imagem = new File(reg.getFoto());
                               Bitmap bitmap = BitmapFactory.decodeFile(imagem.getAbsolutePath());
                               ImageView imagemEscolhida = (ImageView) findViewById(R.id.imageView2);
                               imagemEscolhida.setImageBitmap(bitmap);
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
        });
    }

    public Registro buscaUnidade(String cepUsuario, String tipo)
    {
        Registro registro = new Registro();
        SQLiteDatabase db = dbHelper.getReadableDatabase() ;

        Cursor cursor = db.query(SaudeContract.Unidade.TABLE_NAME,new String[]{SaudeContract.Unidade._ID,SaudeContract.Unidade.COLUMN_NAME_UNIDADE,SaudeContract.Unidade.COLUMN_NAME_CEP_USUARIO
                ,SaudeContract.Unidade.COLUMN_NAME_CEP_UNIDADE,SaudeContract.Unidade.COLUMN_NAME_TIPO,SaudeContract.Unidade.COLUMN_NAME_NUMERO,
                SaudeContract.Unidade.COLUMN_NAME_FOTO}, SaudeContract.Unidade.COLUMN_NAME_CEP_USUARIO + "= ? AND " +
                SaudeContract.Unidade.COLUMN_NAME_TIPO +"= ?",new String[]{String.valueOf(cepUsuario),String.valueOf(tipo)},
                null,null,null);
        if (cursor!=null)
        {
            cursor.moveToFirst();
            if(cursor.getCount()>0)
            {
                registro.setId(cursor.getLong(0));
                registro.setNomeUnidade(cursor.getString(1));
                registro.setCEPUsuario(cursor.getString(2));
                registro.setCEPUnidade(cursor.getString(3));
                registro.setTipo(cursor.getString(4));
                registro.setNumero(cursor.getInt(5));
                registro.setFoto(cursor.getString(6));
            }else
                {
                    Toast.makeText(this, "CEP não está cadastrado", Toast.LENGTH_LONG).show();
                    return null;
                }

        }
        db.close();
        return registro;
    }

    public void CarregarBD() {
        registro = new Registro("USF Mãe Preta", "25812050", "25815080", "Atenção Básica", 443, "");
        registro1 = new Registro("USF Mãe Preta", "25815080", "25815080", "Atenção Básica", 443, "");
        registro2 = new Registro("USF Mãe Preta", "25815070", "25815080", "Atenção Básica", 443, "");
        registro3 = new Registro("SAMU", "25800001", "25820180", "Pronto Atendimento", 10, "drawable/logo.png");
        registro4 = new Registro("Central de Imunização", "25812050", "25870000 ", "Imunização", 300, "C:\\Users\\Rian Alves\\MaisSaude\\app\\src\\main\\res\\drawable\\logo.png");

        //lstRegistros.add(registro);
       // lstRegistros.add(registro1);
        //lstRegistros.add(registro2);
        lstRegistros.add(registro3);
       lstRegistros.add(registro4);


        for(int i =0 ; i< lstRegistros.size(); i++) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put(SaudeContract.Unidade.COLUMN_NAME_UNIDADE,lstRegistros.get(i).getNomeUnidade()) ;
            valores.put(SaudeContract.Unidade.COLUMN_NAME_CEP_USUARIO, lstRegistros.get(i).getCEPUsuario());
            valores.put(SaudeContract.Unidade.COLUMN_NAME_CEP_UNIDADE, lstRegistros.get(i).getCEPUnidade());
            valores.put(SaudeContract.Unidade.COLUMN_NAME_TIPO, lstRegistros.get(i).getTipo());
            valores.put(SaudeContract.Unidade.COLUMN_NAME_NUMERO, lstRegistros.get(i).getNumero());
            valores.put(SaudeContract.Unidade.COLUMN_NAME_FOTO, lstRegistros.get(i).getFoto());
            long id = db.insert(SaudeContract.Unidade.TABLE_NAME, null, valores);
            Log.i("DBINFO", "registro criado com id: " + id);
        }
    }

}
