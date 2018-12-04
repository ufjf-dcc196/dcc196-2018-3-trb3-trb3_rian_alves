package dcc196.ufjf.br.maissaude.DAO;

import android.provider.BaseColumns;

public class SaudeContract {

    public final class Unidade implements BaseColumns {
        public final static String TABLE_NAME = "Unidade";
        public final static String COLUMN_NAME_UNIDADE = "nome";
        public final static String COLUMN_NAME_CEP_USUARIO = "cepUsuario";
        public final static String COLUMN_NAME_CEP_UNIDADE = "cepUnidade";
        public final static String COLUMN_NAME_TIPO = "tipo";
        public final static String COLUMN_NAME_NUMERO = "numero";
        public final static String COLUMN_NAME_FOTO = "foto";
        public final static String CREATE_UNIDADE  = "CREATE TABLE "+Unidade.TABLE_NAME+" ("
                + Unidade._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Unidade.COLUMN_NAME_UNIDADE+ " TEXT, "
                + Unidade.COLUMN_NAME_CEP_USUARIO+ " TEXT,"
                + Unidade.COLUMN_NAME_CEP_UNIDADE+ " TEXT,"
                + Unidade.COLUMN_NAME_TIPO+ " TEXT,"
                + Unidade.COLUMN_NAME_NUMERO+ " INTEGER,"
                + Unidade.COLUMN_NAME_FOTO+ " TEXT"
                +")";
        public final static String DROP_USF = "DROP TABLE IF EXISTS "+Unidade.TABLE_NAME;
    }
}
