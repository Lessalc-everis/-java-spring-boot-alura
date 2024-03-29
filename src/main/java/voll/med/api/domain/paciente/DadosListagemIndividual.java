package voll.med.api.domain.paciente;

import voll.med.api.domain.endereco.Endereco;

public record DadosListagemIndividual(

        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        Endereco endereco,
        Boolean ativos
) {
    public DadosListagemIndividual(Paciente paciente) {
        this(paciente.getId(),
        paciente.getNome(),
        paciente.getEmail(),
        paciente.getTelefone(),
        paciente.getCpf(),
        paciente.getEndereco(),
        paciente.getAtivo());
    }
}
