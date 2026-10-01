public class CalculoFundeb {

    private double valorPorAluno;

    public CalculoFundeb(double valorPorAluno) {
        this.valorPorAluno = valorPorAluno;
    }

    public double calcularRepasse(Municipio municipio) {

        double total = 0;

        for (Matricula matricula : municipio.getMatriculas()) {
            double valor = matricula.getQuantidadeAlunos() * matricula.getModalidade().getFatorPonderado() 
            * valorPorAluno;

            total += valor;
        }

        return total;
    }
    
    public double getValorPorAluno() {
        return valorPorAluno;
    }

    public void setValorPorAluno(double valorPorAluno) {
        this.valorPorAluno = valorPorAluno;
    }
}