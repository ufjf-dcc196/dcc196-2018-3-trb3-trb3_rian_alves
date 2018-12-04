package dcc196.ufjf.br.maissaude.Modelo;

public class Registro {

    private String nomeUnidade;
    private String CEPUsuario;
    private String CEPUnidade;
    private String tipo;
    private int numero;

    public Registro(String nomeUnidade, String CEPUsuario, String CEPUnidade, String tipo, int numero) {
        this.nomeUnidade = nomeUnidade;
        this.CEPUsuario = CEPUsuario;
        this.CEPUnidade = CEPUnidade;
        this.tipo = tipo;
        this.numero = numero;
    }

    public String getNomeUnidade() {
        return nomeUnidade;
    }

    public void setNomeUnidade(String nomeUnidade) {
        this.nomeUnidade = nomeUnidade;
    }

    public String getCEPUsuario() {
        return CEPUsuario;
    }

    public void setCEPUsuario(String CEPUsuario) {
        this.CEPUsuario = CEPUsuario;
    }

    public String getCEPUnidade() {
        return CEPUnidade;
    }

    public void setCEPUnidade(String CEPUnidade) {
        this.CEPUnidade = CEPUnidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
