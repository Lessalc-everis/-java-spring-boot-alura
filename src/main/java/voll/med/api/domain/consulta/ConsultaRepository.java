package voll.med.api.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByMedicoIdAndDataAndMotivocancelamentoIsNull(Long idMedico, LocalDateTime data);

    boolean existsByPacienteIdAndDataBetweenAndMotivocancelamentoIsNull(Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);



}
