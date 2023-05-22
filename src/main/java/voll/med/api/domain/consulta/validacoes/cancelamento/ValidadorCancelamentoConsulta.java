package voll.med.api.domain.consulta.validacoes.cancelamento;

import voll.med.api.domain.consulta.DadosAgendamentoConsulta;
import voll.med.api.domain.consulta.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoConsulta {

    void validar(DadosCancelamentoConsulta dados);
}
