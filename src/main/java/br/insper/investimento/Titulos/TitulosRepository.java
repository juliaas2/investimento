package br.insper.investimento.Titulos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TitulosRepository extends JpaRepository<Titulos, Integer> {
    List<Titulos> findByTipo(String tipo);
    Titulos getById(Integer id);
}
