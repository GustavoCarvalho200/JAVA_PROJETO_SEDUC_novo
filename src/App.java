import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;



// Inicia o programa, lê o CSV e exibe os municípios.
public class App {
    public static void main(String[] args) throws Exception {
        Path caminhoCsv = args.length > 0
                ? Path.of(args[0])
                : Path.of("csv", "versao_que_vamos_usar_completo.csv");
        if (args.length == 0 && !Files.isRegularFile(caminhoCsv)) {
            caminhoCsv = Path.of("JAVA_PROJETO_SEDUC_novo", "csv", "versao_que_vamos_usar_completo.csv");
        }

        Relatorio relatorio = new Relatorio();
        LeitorCSV leitor = new LeitorCSV();
        List<Municipio> municipios = leitor.ler(caminhoCsv);
        CalculoFundeb cf = new CalculoFundeb();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("0-sair");
            System.out.println("1-exibir todos os municipios");
            System.out.println("2-ver relatorios");
            System.out.println("3-somar receita prevista de todos os municipios");
            System.out.println("4-buscar municipio");
            int opcao = sc.nextInt();

            if (opcao == 0) {
                System.out.println("saindo");
                break;

            } else if (opcao == 1) {
                for (Municipio m : municipios) {
                    relatorio.exibirMunicipio(m);
                }
            } else if (opcao == 2) {
                    System.out.println("1-maior fundeb");
                    System.out.println("2-indicador medio por aluno");
                    System.out.println("3-ranking");
                    System.out.println("4-fundeb do estado");
                    System.out.println("5-media por municipio");
                    int entrada = sc.nextInt();
                if(entrada == 1){
                    
                }
                else if(entrada ==2){
                    double valorPorAluno = cf.valorPorAluno(municipios);
                    System.out.printf(
                            Locale.forLanguageTag("pt-BR"),
                            "Indicador médio por aluno: R$ %,.2f%n",
                            valorPorAluno);
                }
                else if (entrada == 3) {
                    municipios.sort((a, b) -> Double.compare(b.getReceitaPrevista(), a.getReceitaPrevista()));
                    System.out.println("===== TOP 3 MAIORES FUNDEBS =====");
                        for (int j = 0; j < Math.min(3, municipios.size()); j++) {
                            System.out.printf(
                                    Locale.forLanguageTag("pt-BR"),
                                    "%d. %s - R$ %,.2f%n",
                                    j + 1,
                                    municipios.get(j).getNome(),
                                    municipios.get(j).getReceitaPrevista());
                        }

                } else if (entrada == 4) {
                        double totalPonderado = cf.fundebEstado(municipios);
                        System.out.printf(
                                Locale.forLanguageTag("pt-BR"),
                                "Total ponderado: %,.2f%n",
                                totalPonderado);

                } else if (entrada == 5) {
                    double media = cf.mediaPorMunicipio(municipios);
                    System.out.printf(
                            Locale.forLanguageTag("pt-BR"),
                            "Média da receita prevista por município: R$ %,.2f%n",
                            media);
                }
            } else if (opcao == 3) {
                double totalReceitaPrevista = cf.totalReceitaPrevista(municipios);
                System.out.printf(
                        Locale.forLanguageTag("pt-BR"),
                        "Total da receita prevista: R$ %,.2f%n",
                        totalReceitaPrevista);
            } else if (opcao == 4) {
                sc.nextLine();
                leitor.buscar(municipios, sc);
            }
        }
        sc.close();
    }
}
