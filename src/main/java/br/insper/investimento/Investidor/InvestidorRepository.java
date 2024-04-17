package br.insper.investimento.Investidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface InvestidorRepository extends JpaRepository<Investidor, Integer> {

    List<Investidor> findByPerfilInvestidor(String perfilInvestidor);

    Investidor findByCpf(String cpf);
}




