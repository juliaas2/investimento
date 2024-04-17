package br.insper.investimento.Investimentos;


import br.insper.investimento.Investidor.Investidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvestimentosRepository extends JpaRepository<Investimentos, Integer> {
    List<Investimentos> findByInvestidor(Investidor investidor);
}
