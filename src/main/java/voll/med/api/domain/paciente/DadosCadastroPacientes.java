package voll.med.api.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import voll.med.api.domain.endereco.DadosEndereco;

public record DadosCadastroPacientes(
        @NotBlank(message = "{nome.obrigatorio}") String nome,
        @NotBlank(message = "{email.obrigatorio}") String email,
        @NotBlank(message = "{telefone.obrigatorio}") String telefone,
        @NotBlank @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}", message = "{cpf.invalido}")
        String cpf,
        @NotNull @Valid DadosEndereco endereco) {
}
