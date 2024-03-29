package voll.med.api.domain.consulta;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import voll.med.api.domain.medico.Especialidade;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(
        @JsonAlias({"id_Medico", "id_medico", "medico_id", "Medico_id"}) Long idMedico,
        @NotNull
        @JsonAlias({"id_paciente", "id_Paciente", "paciente_id", "Paciente_id"}) Long idPaciente,
        @NotNull
        @Future
                @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime data,

        Especialidade especialidade) {
}
