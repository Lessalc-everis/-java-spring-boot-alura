package voll.med.api.domain.consulta.validacoes.agendamento;

import voll.med.api.domain.consulta.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoConsulta {
    void validar(DadosAgendamentoConsulta dados);
}
