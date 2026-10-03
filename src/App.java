import java.nio.file.Path;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        
        Path caminhoCsv = Path.of("csv", "versao_que_vamos_usar_completo.csv");
        
        List<Municipio> municipios = new LeitorCSV().ler(caminhoCsv);

        // Cria uma modalidade de ensino
        ModalidadeEnsino fundamental = new ModalidadeEnsino("Ensino Fundamental", 1.0);

        // Cria um município
        Municipio municipio = new Municipio("Municipio Teste", 123);

        // Cria uma matrícula
        Matricula matricula = new Matricula(fundamental, 500);

        // Adiciona a matrícula ao município
        municipio.adicionarMatricula(matricula);

        // Mostra os dados básicos
        System.out.println("Municipio: " + municipio.getNome());
        System.out.println("Codigo: " + municipio.getCodigo());
        System.out.println("Modalidade: " + matricula.getModalidade().getNome());
        System.out.println("Alunos: " + matricula.getQuantidadeAlunos());

        // Cria o objeto responsável pelo cálculo
        CalculoFundeb calculo = new CalculoFundeb(5000);

        // Calcula o repasse
        double repasse = calculo.calcularRepasse(municipio);

        System.out.println("Repasse estimado: R$ " + repasse);

        // Cria o relatório
        Relatorio relatorio = new Relatorio();

        // Adiciona o município ao relatório
        relatorio.adicionarMunicipio(municipio);

        // Define o valor do repasse
        relatorio.setValorRepasse(repasse);

        // Exibe o relatório
        relatorio.exibirRelatorio();

        // Cria o indicador
        Indicador indicador = new Indicador(municipio);

        // Calcula a média de alunos
        indicador.calcularMediaAlunos();

        // Exibe o indicador
        indicador.exibirIndicador();

        System.out.println("cu do gustavo");
    }
}