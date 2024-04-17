package br.insper.investimento.Investidor;

import br.insper.investimento.Investimentos.Investimentos;
import br.insper.investimento.Investimentos.InvestimentosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvestidorService {

    @Autowired
    private InvestidorRepository investidorRepository;

    @Autowired
    private InvestimentosRepository investimentosRepository;

    public Investidor cadastrarInvestidor(Investidor investidor) {
        if (investidor.getNome() == null || investidor.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome do investidor é obrigatório");
        }
        return investidorRepository.save(investidor);
    }

    public Investidor getInvestidor(Integer id) {
        Optional<Investidor> investidorOptional = investidorRepository.findById(id);
        return investidorOptional.orElse(null);
    }

    public Investidor getInvestidorByCpf(String cpf) {
        return investidorRepository.findByCpf(cpf);
    }

    public List<Investidor> listarInvestidoresPorPerfil(String perfilInvestidor) {
        if (perfilInvestidor != null) {
            return investidorRepository.findByPerfilInvestidor(perfilInvestidor);
        }
        return investidorRepository.findAll();
    }

    public void deletarInvestidor(Integer id) {
        Investidor investidor = getInvestidor(id);
        if (investidor == null) {
            throw new IllegalArgumentException("Investidor não encontrado");
        }

        List<Investimentos> investimentos = investimentosRepository.findByInvestidor(investidor);
        if (!investimentos.isEmpty()) {
            throw new IllegalStateException("Investidor possui investimentos e não pode ser excluído");
        }

        investidorRepository.delete(investidor);
    }
}
