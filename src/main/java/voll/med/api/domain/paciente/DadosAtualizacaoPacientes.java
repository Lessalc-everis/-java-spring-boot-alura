package voll.med.api.domain.paciente;

import jakarta.validation.constraints.NotNull;
import voll.med.api.domain.endereco.DadosEndereco;

public record DadosAtualizacaoPacientes(
        @NotNull Long id,
        String nome,
        String telefone,
        DadosEndereco endereco
) {
}
