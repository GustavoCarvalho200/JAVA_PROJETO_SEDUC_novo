import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

// Lê o CSV e transforma seus registros em objetos Município.
public class LeitorCSV {

    public List<Municipio> ler(Path caminhoCSV) throws IOException{
        List<Municipio> municipios = new ArrayList<>();
        
        CSVFormat formato = CSVFormat.DEFAULT.builder() //inicia a construcao
            .setDelimiter(';') // delimita que as colunas sao separadas por ";"(so olhem o arquivo csv aq no vs que ces vao ver)
            
            .setHeader()//avisa que o LEITOR(classe que vao ser criada la embaixo) que a primeira linha indica os nomes da coluna, e vai ser guardado os nome pra uma consulta depoises
            
            .setSkipHeaderRecord(true)//pula as linhas quando o leitor ler as informacoes, pra nao ter erro na leitura 
            
            .get();//finaliza a configuracao


        /*o try ele vai executar oque ta dento dos parenteses pra depois executar oque tem dentro das chaves, NAO INTERPRETEM O TRY COMO UM dIF

        oque tem dentro dos parenteses eh uma preparacao para rodar oque tem nas chaves


        explicacao dos parenteses: 

        1 READER eh uma classe do java usada para textos de arquivos externos, no codigo aq embaixo estamos declarando uma variavel do tipo READER

        2 Files.newBufferedReader = Files eh uma classe, e o newbufferedreader eh a funcao que faz abrir o arquivo que nois quer e ler por BLOCOS 
        
        */
        try(Reader leitor = Files.newBufferedReader(caminhoCSV, StandardCharsets.UTF_8);


        /*esse CSVParser eh uma classe do csv apache que tem a funcao de entender o arquivo csv linha e coluna que vai receber "leitor" e leitor recebe o arquivo
        
        formato foi criado ali em cima

        .parser metodo de formato, que analisa o texto*/
          CSVParser dados = formato.parse(leitor)) {
            List<String> colunas = dados.getHeaderNames();
            //list mesma coisa que arraylist, soq nois so ta usando pq o get header so funciona com list, nao com arraylist

            //getheadernames pega os nomes das colunas

            int colunaTotalMatricula = -1;
            int colunaReceita = -1;

            for (int i = 0; i < colunas.size(); i++) {
                //vai remober acentos, aspas e quebas de linhas, vai basicamente normalizar o texto e armazenar em nome Coluna
                String nomeColuna = normalizarCabecalho(colunas.get(i));
                

              //ai se a variavel nome coluna tiver "matriculas" e  "totais", ele quarda o numero da coluna que tem matriculas totais
                if (nomeColuna.contains("matriculas") && nomeColuna.contains("totais")) {
                    colunaTotalMatricula = i;
                }


                if (nomeColuna.contains("receita") && nomeColuna.contains("contribuicao")
                        && nomeColuna.contains("fundeb")) {
                    colunaReceita = i;
                }
            }

            if (colunaTotalMatricula < 0 || colunaReceita < 0) {
                throw new IOException("Não foi possível localizar as colunas de matrículas totais e receita no CSV.");
            }

            List<CSVRecord> linhas = dados.getRecords();//pega cada linha do csv e transfprma num objeto tipo csv record

            for (CSVRecord linha : linhas) {
                int deslocamentoColunas = linha.size() - colunas.size();
                //subtrai a quantidade de linhas e a quantidade de colunas
                //a gente botou isso para nao ocorrer problemas de buscar a coluna errada 

                if (deslocamentoColunas != 0 && deslocamentoColunas != 1) {
                    throw new IOException("Quantidade de campos inválida na linha " + linha.getRecordNumber() + ".");
                }

                int codigoMunicipio = Integer.parseInt(linha.get(2).trim());
                //integer eh uma classe, parse int vai passar os dados linha.get(2), que eh o codigo do municipio 
                String nomeMunicipio = linha.get(1).trim();
                //vai pegar o nome do municipio

                int totalCreche = 0;
                int totalPreEscola = 0;
                int totalEnsinoFundamental = 0;
                int totalEnsinoMedio = 0;
                int totalEja = 0;
                int totalEducacaoEspecial = 0;
                int totalAtendimentoEducacionalEspecializado = 0;
                int totalEducacaoProfissionalETecnica = 0;
                int totalEducacaoBilingue = 0;

                for (int i = 0; i < colunas.size(); i++) {
                    String nomeColuna = normalizarCabecalho(colunas.get(i));
                    int quantidade;
                    
                    //incio
                    if (nomeColuna.startsWith("creche ")) {
                        quantidade = Integer.parseInt(linha.get(i + deslocamentoColunas).trim());
                        totalCreche += quantidade;
                    } else if (nomeColuna.startsWith("pre-escola ")) {
                        quantidade = Integer.parseInt(linha.get(i + deslocamentoColunas).trim());
                        totalPreEscola += quantidade;


                    } else if (nomeColuna.startsWith("ensino fundamental ")
                            || nomeColuna.startsWith("anos iniciais fundamental")
                            || nomeColuna.startsWith("anos finais fundamental")) {
                        quantidade = Integer.parseInt(linha.get(i + deslocamentoColunas).trim());
                        totalEnsinoFundamental += quantidade;


                    } else if (nomeColuna.startsWith("ensino medio ")) {
                        quantidade = Integer.parseInt(linha.get(i + deslocamentoColunas).trim());
                        totalEnsinoMedio += quantidade;


                    } else if (nomeColuna.startsWith("eja ")) {
                        quantidade = Integer.parseInt(linha.get(i + deslocamentoColunas).trim());
                        totalEja += quantidade;


                    } else if (nomeColuna.startsWith("educacao especial ")) {
                        quantidade = Integer.parseInt(linha.get(i + deslocamentoColunas).trim());
                        totalEducacaoEspecial += quantidade;


                    } else if (nomeColuna.equals("atendimento educacional especializado")) {
                        quantidade = Integer.parseInt(linha.get(i + deslocamentoColunas).trim());
                        totalAtendimentoEducacionalEspecializado += quantidade;


                    } else if (nomeColuna.startsWith("educacao profissional e tecnica")) {
                        quantidade = Integer.parseInt(linha.get(i + deslocamentoColunas).trim());
                        totalEducacaoProfissionalETecnica += quantidade;


                    } else if (nomeColuna.startsWith("educacao bilingue ")) {
                        quantidade = Integer.parseInt(linha.get(i + deslocamentoColunas).trim());
                        totalEducacaoBilingue += quantidade;
                    }
                }

                int total = Integer.parseInt(linha.get(colunaTotalMatricula + deslocamentoColunas).trim());//vai armazenar o total de matriculas, o deslocamento vai ajudar a localizar a coluna
                
                double receita = Double.parseDouble(linha.get(colunaReceita + deslocamentoColunas).trim());//vai armazenar as receitas

                //construtor
                Municipio municipio = new Municipio(
                        nomeMunicipio,
                        codigoMunicipio,
                        total,
                        receita,
                        totalCreche,
                        totalPreEscola,
                        totalEnsinoFundamental,
                        totalEnsinoMedio,
                        totalEja,
                        totalEducacaoEspecial,
                        totalAtendimentoEducacionalEspecializado,
                        totalEducacaoProfissionalETecnica,
                        totalEducacaoBilingue);
                municipios.add(municipio);
            }
            


        return municipios;
    }
    }

    private String normalizarCabecalho(String nomeColuna) {
        String semAcentos = Normalizer.normalize(nomeColuna, Normalizer.Form.NFD)
            .replaceAll("\\p{M}", "");

        return semAcentos
            .replace("\uFEFF", "")
            .replace("\"", "")
   
             .replace("\n", " ")
            .replace("\r", " ")
            .trim()
            .toLowerCase();
    }
public void buscar(List<Municipio> municipios, Scanner scanner) {
        System.out.print("Digite o código do município: ");
        if (!scanner.hasNextLine()) {
            System.out.println("Entrada encerrada.");
            return;//quando ele escanear todas as linhas 
        }
        String entrada = scanner.nextLine().trim();
        int codigo;
        try {
            codigo = Integer.parseInt(entrada);
            //passar o codigo da entrada para inteiro
        } catch (NumberFormatException e) {
            System.out.println("Código inválido.");
            return;//excesao que pode causar 
        }

        for (Municipio municipio : municipios) {
            if (municipio.getCodigo() == codigo) {
                System.out.println("Código: " + municipio.getCodigo());
                System.out.println("Nome: " + municipio.getNome());
                System.out.println("Matrículas: " + municipio.getTotalMatriculas());
                System.out.printf(Locale.forLanguageTag("pt-BR"), "Receita prevista: %,.2f%n",
                        municipio.getReceitaPrevista());
                return;
            }
        }

        System.out.println("Município não encontrado para o código " + codigo + ".");
    }
}