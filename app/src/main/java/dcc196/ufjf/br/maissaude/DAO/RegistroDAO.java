package dcc196.ufjf.br.maissaude.DAO;

import java.util.ArrayList;

import dcc196.ufjf.br.maissaude.Modelo.Registro;

public class RegistroDAO {

    private static  Registro registro,registro1,registro2,registro3,registro4;
    private static ArrayList<Registro> lstRegistros = new ArrayList<Registro>();


    public static ArrayList<Registro> CarregarDadosUnidades() {
        registro = new Registro("USF Mãe Preta", "25812050", "25815080", "Atenção Básica", 443, "////////drawable/logo.png");
        registro1 = new Registro("USF Mãe Preta", "25815080", "25815080", "Atenção Básica", 443, "");
        registro2 = new Registro("USF Mãe Preta", "25815070", "25815080", "Atenção Básica", 443, "");
        registro3 = new Registro("UPA-Três Rios", "25812050", "25820180", "Pronto Atendimento", 10, "C:/Users/Rian Alves/MaisSaude/app/src/main/res/drawable/logo.png");
        registro4 = new Registro("Central de Imunização", "25812050", "25805022 ", "Imunização", 300, "");

        lstRegistros.add(registro);
        lstRegistros.add(registro1);
        lstRegistros.add(registro2);
        lstRegistros.add(registro3);
        lstRegistros.add(registro4);


       return lstRegistros;
    }


}
