package voll.med.api.domain.consulta.validacoes.agendamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import voll.med.api.domain.ValidacaoException;
import voll.med.api.domain.consulta.DadosAgendamentoConsulta;
import voll.med.api.domain.medico.MedicoRepository;

@Component
public class ValidacaoMedicoAtivo implements ValidadorAgendamentoConsulta{

    @Autowired
    private MedicoRepository repository;
    public void validar(DadosAgendamentoConsulta dados){
        if(dados.idMedico() == null)
            return;
        var medicoEstaAtivo = repository.findAtivoById(dados.idMedico());
        if(!medicoEstaAtivo)
            throw new ValidacaoException("Consulta não pode ser agendada com médico inativo");
    }
}
