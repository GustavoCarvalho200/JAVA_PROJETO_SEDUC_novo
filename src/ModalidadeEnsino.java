public class ModalidadeEnsino {

    private String nome;
    private double fatorPonderado;

    public ModalidadeEnsino(String nome, double fatorPonderado) {
        this.nome = nome;
        this.fatorPonderado = fatorPonderado;
    }

    public String getNome() {
        return nome;
    }

    public double getFatorPonderado() {
        return fatorPonderado;
    }

    public void setFatorPonderado(double fatorPonderado) {
        this.fatorPonderado = fatorPonderado;
    }
}