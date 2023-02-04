package voll.med.api.paciente;

public record DadosListagemIndividual(

        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        String rua,
        String cidade,
        String uf,
        Boolean ativos
) {
    public DadosListagemIndividual(Paciente paciente) {
        this(paciente.getId(),
        paciente.getNome(),
        paciente.getEmail(),
        paciente.getTelefone(),
        paciente.getCpf(),
        paciente.getEndereco().getLogradouro(),
        paciente.getEndereco().getCidade(),
        paciente.getEndereco().getUf(),
        paciente.getAtivo());
    }
}
