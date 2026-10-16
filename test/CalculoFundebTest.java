import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

// Testa os cálculos de matrículas ponderadas e repasses do Fundeb.
class CalculoFundebTest {

    @Test
    void deveCalcularRepassePonderadoDeTodasAsMatriculas() {
        Municipio municipio = new Municipio("Cidade teste", 1234567);
        ModalidadeEnsino modalidadeIntegral = new ModalidadeEnsino("Integral", 1.2);
        ModalidadeEnsino modalidadeEspecial = new ModalidadeEnsino("Especial", 1.5);

        municipio.adicionarMatricula(
                new Matricula(modalidadeIntegral, 2));
        municipio.adicionarMatricula(
                new Matricula(modalidadeEspecial, 3));
        CalculoFundeb calculo = new CalculoFundeb();
        calculo.setValorPorAluno(1000.0);

        double repasse = calculo.calcularRepasse(municipio);

        assertEquals(6900.0, repasse, 0.001);
    }

    @Test
    void deveCalcularTotalPonderadoDeTodasAsMatriculas() {
        Municipio municipio = new Municipio(null, 1234567);
        municipio.adicionarMatricula(
                new Matricula(new ModalidadeEnsino("Integral", 1.2), 2));
        municipio.adicionarMatricula(
                new Matricula(new ModalidadeEnsino("Especial", 1.5), 3));
        CalculoFundeb calculo = new CalculoFundeb();
        calculo.setValorPorAluno(1000.0);

        assertEquals(6.9, calculo.calcularTotalPonderado(municipio), 0.001);
    }

    @Test
    void deveSomarTotaisDasModalidadesEMultiplicarPeloFatorPonderado() {
        Municipio municipio = new Municipio(
                "Cidade teste", 1234567, 999, 12345.67,
                10, 20, 30, 40, 50, 60, 70, 80, 90);
        CalculoFundeb calculo = new CalculoFundeb();

        assertEquals(540.0, calculo.total(municipio), 0.001);
    }

    @Test
    void deveRetornarZeroQuandoMunicipioNaoTemMatriculas() {
        Municipio municipio = new Municipio("Cidade teste", 1234567);
        CalculoFundeb calculo = new CalculoFundeb();
        calculo.setValorPorAluno(1000.0);

        assertEquals(0.0, calculo.calcularRepasse(municipio), 0.001);
    }

    @Test
    void deveRetornarValorPorAlunoInformadoNoConstrutor() {
        CalculoFundeb calculo = new CalculoFundeb();
        calculo.setValorPorAluno(1250.0);

        assertEquals(1250.0, calculo.getValorPorAluno(), 0.001);
    }

    @Test
    void deveUsarNovoValorPorAlunoAposAlteracao() {
        Municipio municipio = new Municipio("Cidade teste", 1234567);
        municipio.adicionarMatricula(
                new Matricula(new ModalidadeEnsino("Regular", 1.0), 4));
        CalculoFundeb calculo = new CalculoFundeb();
        calculo.setValorPorAluno(100.0);

        calculo.setValorPorAluno(250.0);

        assertEquals(250.0, calculo.getValorPorAluno(), 0.001);
        assertEquals(1000.0, calculo.calcularRepasse(municipio), 0.001);
    }
}
