package br.insper.investimento.Investimentos;

import br.insper.investimento.Investidor.Investidor;
import br.insper.investimento.Investidor.InvestidorService;
import br.insper.investimento.Titulos.Titulos;
import br.insper.investimento.Titulos.TitulosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestimentosService {

    @Autowired
    private InvestimentosRepository investimentosRepository;

    @Autowired
    private InvestidorService investidorService;

    @Autowired
    private TitulosService titulosService;

    public Investimentos cadastrarInvestimento(Investimentos investimento) {
        Investidor investidor = investidorService.getInvestidor(investimento.getInvestidor().getId());
        Titulos titulo = titulosService.getTitulo(investimento.getTitulo().getId());

        if (investidor == null) {
            throw new RuntimeException("Investidor não encontrado!");
        }

        if (titulo == null) {
            throw new RuntimeException("Título não encontrado!");
        }

        if (investidor.getPerfilInvestidor().equals("Conservador") && titulo.getTipo().equals("Ação")) {
            throw new RuntimeException("Investidor conservador não pode investir em ações!");
        }

        if (investidor.getPerfilInvestidor().equals("Moderado")) {
            validarInvestimentoModerado(investidor, investimento);
        }

        return investimentosRepository.save(investimento);
    }

    private void validarInvestimentoModerado(Investidor investidor, Investimentos investimento) {
        List<Investimentos> investimentos = investimentosRepository.findByInvestidor(investidor);
        Double valorTotalInvestido = investimentos.stream().mapToDouble(Investimentos::getValorInvestido).sum();
        Double valorTotalAcoes = investimentos.stream().filter(inv -> inv.getTitulo().getTipo().equals("Ação"))
                .mapToDouble(Investimentos::getValorInvestido).sum();

        if (valorTotalAcoes / valorTotalInvestido + investimento.getValorInvestido() / (valorTotalInvestido + investimento.getValorInvestido()) > 0.5) {
            throw new RuntimeException("Investidor moderado não pode investir mais de 50% em ações!");
        }
    }

    public List<Investimentos> listarInvestimentos(String cpf) {
        Investidor investidor = investidorService.getInvestidorByCpf(cpf);

        if (investidor == null) {
            throw new RuntimeException("Investidor não encontrado!");
        }

        return investimentosRepository.findByInvestidor(investidor);
    }
}

