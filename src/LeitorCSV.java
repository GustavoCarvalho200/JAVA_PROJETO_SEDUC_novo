import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

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


        /*esse CSVParser eh uma classe do csv apache que tem a funcao de entender o arquivo csv linha e coluna
        
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

            List<CSVRecord> linhas = dados.getRecords();//pega cada linha do csv e transfprma num objeto tipo csv record

            for (CSVRecord linha : linhas) {

                int codigoMunicipio = Integer.parseInt(linha.get(2).trim());
                //integer eh uma classe, parse int vai passar os dados linha.get(2), que eh o codigo do municipio 
                String nomeMunicipio = linha.get(1).trim();

                Municipio municipio = new Municipio(nomeMunicipio, codigoMunicipio);

                int total = Integer.parseInt(linha.get(colunaTotalMatricula).trim());
                
                double receita = Double.parseDouble(linha.get(colunaReceita).trim());

                municipio.setTotalMatriculas(total);
                municipio.setReceitaPrevista(receita);
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
}
