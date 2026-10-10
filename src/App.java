import java.nio.file.Path;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        Path caminhoCsv = Path.of(
            "JAVA_PROJETO_SEDUC_novo",
            "csv",
            "versao_que_vamos_usar_completo.csv"
        );

        List<Municipio> municipios = new LeitorCSV().ler(caminhoCsv);

        for (int i = 0; i < municipios.size(); i++) {
            Municipio municipio = municipios.get(i);

            System.out.println("Código: " + municipio.getCodigo());
            System.out.println("Nome: " + municipio.getNome());
            System.out.println("Matrículas: " + municipio.getTotalMatriculas());
            System.out.println("Receita prevista: " + municipio.getReceitaPrevista());
            System.out.println("----------------------");
        }
    }
}
