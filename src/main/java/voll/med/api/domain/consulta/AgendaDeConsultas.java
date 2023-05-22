package voll.med.api.domain.consulta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import voll.med.api.domain.ValidacaoException;
import voll.med.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoConsulta;
import voll.med.api.domain.consulta.validacoes.cancelamento.ValidadorCancelamentoConsulta;
import voll.med.api.domain.medico.Medico;
import voll.med.api.domain.medico.MedicoRepository;
import voll.med.api.domain.paciente.PacienteRepository;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoConsulta> validadores;

    @Autowired
    private List<ValidadorCancelamentoConsulta> validadoresCancelamento;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados){
        if(!pacienteRepository.existsById(dados.idPaciente()))
            throw new ValidacaoException("Id do paciênte informado não existe!");
        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico()))
            throw new ValidacaoException("Id do medico informado não existe!");

        validadores.forEach(v -> v.validar(dados));

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);
        if(medico == null){
            throw new ValidacaoException("Não existe médico disponível nesta data com essa especialidade");
        }

        var consulta = new Consulta(null, medico, paciente, dados.data(), null);
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if(dados.idMedico() != null)
            return medicoRepository.getReferenceById(dados.idMedico());

        if(dados.especialidade() == null)
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido");

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        if(!consultaRepository.existsById(dados.consulta_id()))
            throw new ValidacaoException("Id da consulta não existe");

        validadoresCancelamento.forEach(v -> v.validar(dados));

        var consulta = consultaRepository.getReferenceById(dados.consulta_id());
        consulta.cancelar(dados.motivo());

    }
}
