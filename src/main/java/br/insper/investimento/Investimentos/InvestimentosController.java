package br.insper.investimento.Investimentos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/investimentos")
public class InvestimentosController {

    @Autowired
    private InvestimentosService investimentosService;

    @GetMapping("/{cpf}")
    public List<Investimentos> getInvestimentos(@PathVariable String cpf) {
        return investimentosService.listarInvestimentos(cpf);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Investimentos salvarInvestimentos(@RequestBody Investimentos investimentos) {
        return investimentosService.cadastrarInvestimento(investimentos);
    }
}

