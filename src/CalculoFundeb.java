import java.util.List;

// Calcula matrículas ponderadas e repasses do Fundeb.
public class CalculoFundeb {

    private static final double fatPonderado = 1.2;
    private double valorPorAluno = 0;

    public CalculoFundeb() {
    }

   public CalculoFundeb(double d) {
        this.valorPorAluno = d;
    }

   
    public double calcularRepasse(Municipio municipio) {
        return calcularTotalPonderado(municipio) * valorPorAluno;
    }

    public double calcularTotalPonderado(Municipio municipio) {
        double totalPonderado = 0;
        for (Matricula matricula : municipio.getMatriculas()) {
            totalPonderado += matricula.getQuantidadeAlunos()
                    * matricula.getModalidade().getFatorPonderado();
        }
        return totalPonderado;
    }
    public double valorPorAluno(List<Municipio> municipios) {
        double receitaTotal = 0;
        int matriculasTotais = 0;
        for (Municipio municipio : municipios) {
            receitaTotal += municipio.getReceitaPrevista();
            matriculasTotais += municipio.getTotalMatriculas();
        }
        return matriculasTotais == 0 ? 0 : receitaTotal / matriculasTotais;
    }

    public double mediaPorMunicipio(List<Municipio> municipios) {
        return totalReceitaPrevista(municipios) / 144;
    }

    public double fundebEstado(List<Municipio> municipios) {
        double total = 0;
        for (Municipio municipio : municipios) {
            total += municipio.getReceitaPrevista();
        }
        return total * fatPonderado;
    }

    public double totalReceitaPrevista(List<Municipio> municipios) {
        double total = 0;
        for (Municipio municipio : municipios) {
            total += municipio.getReceitaPrevista();
        }
        return total;
    }

    public double total(Municipio municipio) {
        double totalModalidades = municipio.getTotalCreche()
                + municipio.getTotalPreEscola()
                + municipio.getTotalEnsinoFundamental()
                + municipio.getTotalEnsinoMedio()
                + municipio.getTotalEja()
                + municipio.getTotalEducacaoEspecial()
                + municipio.getTotalAtendimentoEducacionalEspecializado()
                + municipio.getTotalEducacaoProfissionalETecnica()
                + municipio.getTotalEducacaoBilingue();
        return totalModalidades * fatPonderado;
    }

    public double getValorPorAluno() {
        return valorPorAluno;
    }

    public void setValorPorAluno(double valorPorAluno) {
        this.valorPorAluno = valorPorAluno;
    }
}
