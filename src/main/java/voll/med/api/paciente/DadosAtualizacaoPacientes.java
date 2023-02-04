package voll.med.api.paciente;

import jakarta.validation.constraints.NotNull;
import voll.med.api.endereco.DadosEndereco;

public record DadosAtualizacaoPacientes(
        @NotNull Long id,
        String nome,
        String telefone,
        DadosEndereco endereco
) {
}
