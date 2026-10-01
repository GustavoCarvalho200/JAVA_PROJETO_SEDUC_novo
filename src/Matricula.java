public class Matricula {

    private ModalidadeEnsino modalidade;
    private int quantidadeAlunos;

    public Matricula(ModalidadeEnsino modalidade, int quantidadeAlunos) {
        this.modalidade = modalidade;
        this.quantidadeAlunos = quantidadeAlunos;
    }

    public ModalidadeEnsino getModalidade() {
        return modalidade;
    }

    public int getQuantidadeAlunos() {
        return quantidadeAlunos;
    }

    public void setQuantidadeAlunos(int quantidadeAlunos) {
        this.quantidadeAlunos = quantidadeAlunos;
    }
}