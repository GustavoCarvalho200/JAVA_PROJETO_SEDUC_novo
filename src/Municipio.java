import java.util.ArrayList;

public class Municipio {

    private String nome;
    private int codigo;
    private ArrayList<Matricula> matriculas;
    private int totalMatriculas;
    private double receitaPrevista;

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

    public Municipio(String nome, int codigo) {
        this.nome = nome;
        this.codigo = codigo;
        this.matriculas = new ArrayList<>();
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