import java.util.ArrayList;
import java.util.Locale;

// Reúne municípios e exibe seus dados em um relatório.
public class Relatorio {

    private ArrayList<Municipio> municipios;
    private double valorRepasse;

    public Relatorio() {
        this.municipios = new ArrayList<>();
    }

    public void adicionarMunicipio(Municipio municipio) {
        municipios.add(municipio);
    }

  void exibirMunicipio(Municipio municipio) {
        System.out.println("Código: " + municipio.getCodigo());
        System.out.println("Nome: " + municipio.getNome());
        System.out.println("Matrículas: " + municipio.getTotalMatriculas());
        System.out.printf(
                Locale.forLanguageTag("pt-BR"),
                "Receita prevista: %,.2f%n",
                municipio.getReceitaPrevista());
        System.out.println("----------------------");
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