package voll.med.api.domain.consulta.validacoes.cancelamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import voll.med.api.domain.ValidacaoException;
import voll.med.api.domain.consulta.ConsultaRepository;
import voll.med.api.domain.consulta.DadosCancelamentoConsulta;

import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedenciaCancelamento")
public class ValidadorHorarioAntecedencia implements ValidadorCancelamentoConsulta{
    @Autowired
    private ConsultaRepository repository;
    @Override
    public void validar(DadosCancelamentoConsulta dados) {
        var consulta = repository.getReferenceById(dados.consulta_id());
        if(consulta.getData().minusDays(1L).compareTo(LocalDateTime.now()) < 0)
            throw new ValidacaoException("A consulta não pode ser cancelada com menos de 24h de antecedência");
    }
}
