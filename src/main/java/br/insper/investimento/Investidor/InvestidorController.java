package br.insper.investimento.Investidor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/investidores")
public class InvestidorController {

    @Autowired
    private InvestidorService investidorService;

    @GetMapping("/investidor")
    public List<Investidor> getInvestidores(@RequestParam(required = false) String perfilInvestidor) {
        return investidorService.listarInvestidoresPorPerfil(perfilInvestidor);
    }

    @PostMapping("/investidor")
    @ResponseStatus(HttpStatus.CREATED)
    public Investidor salvarInvestidor(@RequestBody Investidor investidor) {
        return investidorService.cadastrarInvestidor(investidor);
    }

    @DeleteMapping("/{id}")
    public void deletarInvestidor(@PathVariable Integer id) {
        investidorService.deletarInvestidor(id);
    }
}
