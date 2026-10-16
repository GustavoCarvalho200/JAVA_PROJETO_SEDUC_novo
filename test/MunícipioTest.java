import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;

class MunicipioTest {
    
    @Test
    void deveCriarMunicipioComNomeCodigoEListaVazia(){
        Municipio municipio = new Municipio("", 1234567);
        
        assertEquals("", municipio.getNome());
        assertEquals(1234567, municipio.getCodigo());
        assertTrue(municipio.getMatriculas().isEmpty());
    }

    @Test
    void deveGuardarTotalDeMatriculasEReceita(){
        Municipio municipio = new  Municipio("Chaves", 1234567);
        municipio.setTotalMatriculas(126);
        municipio.setReceitaPrevista(0.4213423);
        
        assertEquals(126 ,municipio.getTotalMatriculas());
        assertEquals(0.4213423,municipio.getReceitaPrevista(), 0.001);
    }
}