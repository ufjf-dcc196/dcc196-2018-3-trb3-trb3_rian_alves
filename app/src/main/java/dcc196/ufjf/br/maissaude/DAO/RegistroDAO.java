package dcc196.ufjf.br.maissaude.DAO;

import java.util.ArrayList;

import dcc196.ufjf.br.maissaude.Modelo.Registro;

public class RegistroDAO {

     private static ArrayList<Registro> lstRegistros = new ArrayList<Registro>();


    public static ArrayList<Registro> CarregarDadosUnidades() {
        lstRegistros.add(new Registro("USF Mãe Preta", "25812050", "25815080", "Atenção Básica", 443, "/res/drawable/ufjf.png"));
        lstRegistros.add(new Registro("USF Centro", "25802120", "25802120", "Atenção Básica", 74, "////////drawable/logo.png"));
        lstRegistros.add(new Registro("USF Cantagalo", "25805340", "25805340", "Atenção Básica", 100, "////////drawable/logo.png"));
        lstRegistros.add(new Registro("Central de Imunização", "25812050", "25805025", "Imunização", 300, "////////drawable/logo.png"));
        lstRegistros.add(new Registro("Central de Imunização", "25805340", "25805025", "Imunização", 300, "////////drawable/logo.png"));
        lstRegistros.add(new Registro("UPA - Três Rios", "25812050", "25820180", "Pronto Atendimento", 100, "////////drawable/logo.png"));
        lstRegistros.add(new Registro("UPA - Três Rios ", "25805340", "25820180", "Pronto Atendimento", 100, "////////drawable/logo.png"));
        lstRegistros.add(new Registro("UPA - Três Rios", "25870000", "25820180", "Pronto Atendimento", 100, "////////drawable/logo.png"));
        lstRegistros.add(new Registro("USF Mãe Preta", "25815080", "25815080", "Atenção Básica", 443, "////////drawable/logo.png"));


       return lstRegistros;
    }


}
