package voll.med.api.domain.consulta.validacoes.agendamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import voll.med.api.domain.consulta.ConsultaRepository;
import voll.med.api.domain.consulta.DadosAgendamentoConsulta;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoConsulta {

    @Autowired
    private ConsultaRepository repository;
    public void validar(DadosAgendamentoConsulta dados){
        repository.existsByMedicoIdAndDataAndMotivocancelamentoIsNull(dados.idMedico(), dados.data());
    }
}
