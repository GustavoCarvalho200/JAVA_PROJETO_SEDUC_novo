public class Indicador {

    private Municipio municipio;
    private double mediaAlunos;

    public Indicador(Municipio municipio) {
        this.municipio = municipio;
    }

    public void calcularMediaAlunos() {

        if (municipio.getMatriculas().isEmpty()) {
            mediaAlunos = 0;
            return;
        }

        int totalAlunos = 0;

        for (Matricula matricula : municipio.getMatriculas()) {
            totalAlunos += matricula.getQuantidadeAlunos();
        }

        mediaAlunos = (double) totalAlunos / municipio.getMatriculas().size();
    }

    public void exibirIndicador() {
        System.out.println("===== INDICADOR =====");
        System.out.println("Municipio: " + municipio.getNome());
        System.out.println("Media de alunos por matricula: " + mediaAlunos);
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public double getMediaAlunos() {
        return mediaAlunos;
    }
}