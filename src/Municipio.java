import java.util.ArrayList;
import java.util.Locale;

// Guarda os dados, as matrículas e os totais educacionais de um município.
public class Municipio {

    private String nome;
    private int codigo;
    private ArrayList<Matricula> matriculas;
    private int totalMatriculas;
    private double receitaPrevista;
    private int totalCreche;
    private int totalPreEscola;
    private int totalEnsinoFundamental;
    private int totalEnsinoMedio;
    private int totalEja;
    private int totalEducacaoEspecial;
    private int totalAtendimentoEducacionalEspecializado;
    private int totalEducacaoProfissionalETecnica;
    private int totalEducacaoBilingue;
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setMatriculas(ArrayList<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    public int getTotalMatriculas() {
        return totalMatriculas;
    }

    public void setTotalMatriculas(int totalMatriculas) {
        this.totalMatriculas = totalMatriculas;
    }

    public double getReceitaPrevista() {
        return receitaPrevista;
    }

    public void setReceitaPrevista(double receitaPrevista) {
        this.receitaPrevista = receitaPrevista;
    }

    public int getTotalCreche() {
        return totalCreche;
    }

    public void setTotalCreche(int totalCreche) {
        this.totalCreche = totalCreche;
    }

    public int getTotalPreEscola() {
        return totalPreEscola;
    }

    public void setTotalPreEscola(int totalPreEscola) {
        this.totalPreEscola = totalPreEscola;
    }

    public int getTotalEnsinoFundamental() {
        return totalEnsinoFundamental;
    }

    public void setTotalEnsinoFundamental(int totalEnsinoFundamental) {
        this.totalEnsinoFundamental = totalEnsinoFundamental;
    }

    public int getTotalEnsinoMedio() {
        return totalEnsinoMedio;
    }

    public void setTotalEnsinoMedio(int totalEnsinoMedio) {
        this.totalEnsinoMedio = totalEnsinoMedio;
    }

    public int getTotalEja() {
        return totalEja;
    }

    public void setTotalEja(int totalEja) {
        this.totalEja = totalEja;
    }

    public int getTotalEducacaoEspecial() {
        return totalEducacaoEspecial;
    }

    public void setTotalEducacaoEspecial(int totalEducacaoEspecial) {
        this.totalEducacaoEspecial = totalEducacaoEspecial;
    }

    public int getTotalAtendimentoEducacionalEspecializado() {
        return totalAtendimentoEducacionalEspecializado;
    }

    public void setTotalAtendimentoEducacionalEspecializado(int totalAtendimentoEducacionalEspecializado) {
        this.totalAtendimentoEducacionalEspecializado = totalAtendimentoEducacionalEspecializado;
    }

    public int getTotalEducacaoProfissionalETecnica() {
        return totalEducacaoProfissionalETecnica;
    }

    public void setTotalEducacaoProfissionalETecnica(int totalEducacaoProfissionalETecnica) {
        this.totalEducacaoProfissionalETecnica = totalEducacaoProfissionalETecnica;
    }

    public int getTotalEducacaoBilingue() {
        return totalEducacaoBilingue;
    }

    public void setTotalEducacaoBilingue(int totalEducacaoBilingue) {
        this.totalEducacaoBilingue = totalEducacaoBilingue;
    }

    public Municipio(String nome, int codigo) {
        this(nome, codigo, 0, 0.0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    public Municipio(String nome, int codigo, int totalMatriculas, double receitaPrevista,
            int totalCreche, int totalPreEscola, int totalEnsinoFundamental,
            int totalEnsinoMedio, int totalEja, int totalEducacaoEspecial,
            int totalAtendimentoEducacionalEspecializado,
            int totalEducacaoProfissionalETecnica, int totalEducacaoBilingue) {
        this.nome = nome;
        this.codigo = codigo;
        this.matriculas = new ArrayList<>();
        this.totalMatriculas = totalMatriculas;
        this.receitaPrevista = receitaPrevista;
        this.totalCreche = totalCreche;
        this.totalPreEscola = totalPreEscola;
        this.totalEnsinoFundamental = totalEnsinoFundamental;
        this.totalEnsinoMedio = totalEnsinoMedio;
        this.totalEja = totalEja;
        this.totalEducacaoEspecial = totalEducacaoEspecial;
        this.totalAtendimentoEducacionalEspecializado = totalAtendimentoEducacionalEspecializado;
        this.totalEducacaoProfissionalETecnica = totalEducacaoProfissionalETecnica;
        this.totalEducacaoBilingue = totalEducacaoBilingue;
    }

    public void adicionarMatricula(Matricula matricula) {
        matriculas.add(matricula);
    }

    public String getNome() {
        return nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public ArrayList<Matricula> getMatriculas() {
        return matriculas;
    }

  


    @Override
    public String toString() {
        return "Municipio [nome=" + nome + ", codigo=" + codigo + ", matriculas=" + matriculas + ", totalMatriculas="
                + totalMatriculas + ", receitaPrevista=" + receitaPrevista + "]";
    }
    
}