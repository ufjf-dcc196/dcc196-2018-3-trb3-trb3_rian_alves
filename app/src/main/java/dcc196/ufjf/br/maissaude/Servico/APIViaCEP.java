package dcc196.ufjf.br.maissaude.Servico;
import java.util.List;

import dcc196.ufjf.br.maissaude.Modelo.CEP;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIViaCEP {

    String BASE_URL = "https://viacep.com.br/ws/";

    /* Retorna uma lista de objetos CEP */
    @GET("{estado}/{cidade}/{endereco}/json/")
    Call<List<CEP>> getCEPByCidadeEstadoEndereco(@Path("estado") String estado,
                                                 @Path("cidade") String cidade,
                                                 @Path("endereco") String endereco);


    @GET("{CEP}/json/")
    Call<CEP> getEnderecoByCEP(@Path("CEP") String CEP);
}
