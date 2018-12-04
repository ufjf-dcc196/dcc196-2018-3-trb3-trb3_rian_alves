package dcc196.ufjf.br.maissaude.DAO;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import dcc196.ufjf.br.maissaude.Modelo.Registro;

public class RegistroDAO {

    private static Registro registro,registro1,registro2,registro3,registro4;
    private static ArrayList<Registro> lstRegistros = new ArrayList<Registro>();
    private  SaudeDBHelper saudeDBHelper;

    public void CarregarBD() {
        registro = new Registro("USF Mãe Preta", "25812050", "25815080", "Atenção Básica", 443, "");
        registro1 = new Registro("USF Mãe Preta", "25815080", "25815080", "Atenção Básica", 443, "");
        registro2 = new Registro("USF Mãe Preta", "25815070", "25815080", "Atenção Básica", 443, "");
        registro3 = new Registro("UPA-Três Rios", "25812050", "25820180", "Pronto Atendimento", 10, "");
        registro4 = new Registro("Central de Imunização", "25812050", "25805022 ", "Imunização", 300, "");

        lstRegistros.add(registro);
        lstRegistros.add(registro1);
        lstRegistros.add(registro2);
        lstRegistros.add(registro3);
        lstRegistros.add(registro4);


        for(int i =0 ; i< lstRegistros.size(); i++) {
            SQLiteDatabase db = saudeDBHelper.getWritableDatabase();
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
