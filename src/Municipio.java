import java.util.ArrayList;

public class Municipio {

    private String nome;
    private int codigo;
    private ArrayList<Matricula> matriculas;

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
}