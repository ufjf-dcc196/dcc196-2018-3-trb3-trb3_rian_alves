package dcc196.ufjf.br.maissaude.DAO;

import android.provider.BaseColumns;

public class UsfContract {
    public final class USF implements BaseColumns {
        public final static String TABLE_NAME = "Usf";
        public final static String COLUMN_NAME_UNIDADE = "nome";
        public static final String COLUMN_NAME_CEP = "cep";
        public static final String COLUMN_NAME_FOTO = "foto";
        public final static String CREATE_UNIDADE  = "CREATE TABLE "+USF.TABLE_NAME+" ("
                + USF._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USF.COLUMN_NAME_UNIDADE+ " TEXT, "
                + USF.COLUMN_NAME_CEP+ " TEXT,"
                + USF.COLUMN_NAME_FOTO+ " TEXT"
                +")";
        public final static String DROP_USF = "DROP TABLE IF EXISTS "+USF.TABLE_NAME;
    }

    public final class Area implements BaseColumns {
        public final static String TABLE_NAME = "Area";
        public final static String COLUMN_NAME_CEP = "cep";
        public static final String COLUMN_NAME_ID_USF = "idUSF";
        public final static String CREATE_AREA  = "CREATE TABLE "+Area.TABLE_NAME+" ("
                + Area._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Area.COLUMN_NAME_CEP+ " TEXT, "
                + Area.COLUMN_NAME_ID_USF+ " TEXT,"
                +")";
        public final static String DROP_AREA = "DROP TABLE IF EXISTS "+Area.TABLE_NAME;
    }
}
