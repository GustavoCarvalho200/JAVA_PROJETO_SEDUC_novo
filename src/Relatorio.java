import java.util.ArrayList;

public class Relatorio {

    private ArrayList<Municipio> municipios;
    private double valorRepasse;

    public Relatorio() {
        this.municipios = new ArrayList<>();
    }

    public void adicionarMunicipio(Municipio municipio) {
        municipios.add(municipio);
    }

    public void exibirRelatorio() {

        System.out.println("===== RELATORIO FUNDEB =====");

        for (Municipio municipio : municipios) {

            System.out.println("Municipio: " + municipio.getNome());
            System.out.println("Codigo: " + municipio.getCodigo());

            System.out.println("Matriculas:");

            for (Matricula matricula : municipio.getMatriculas()) {

                System.out.println(matricula.getModalidade().getNome() + ": " + matricula.getQuantidadeAlunos() 
                + " alunos");
            }

            System.out.println();
        }

        System.out.println("Repasse estimado: R$ " + valorRepasse);
    }

    public ArrayList<Municipio> getMunicipios() {
        return municipios;
    }

    public double getValorRepasse() {
        return valorRepasse;
    }

    public void setValorRepasse(double valorRepasse) {
        this.valorRepasse = valorRepasse;
    }
}