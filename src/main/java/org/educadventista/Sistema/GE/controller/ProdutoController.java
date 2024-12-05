package org.educadventista.Sistema.GE.controller;

import org.educadventista.Sistema.GE.entity.Produto;
import org.educadventista.Sistema.GE.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> getAllProdutos() {
        return produtoService.findAll();
    }

    @GetMapping("/{id}")
    public Produto getProdutoById(@PathVariable Long id) {
        return produtoService.findById(id);
    }

    @PostMapping
    public Produto createProduto(@RequestBody Produto produto) {
        return produtoService.save(produto);
    }

    @PutMapping("/{id}")
    public Produto updateProduto(@PathVariable Long id, @RequestBody Produto produtoAtualizado) {
        return produtoService.update(id, produtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public void deleteProduto(@PathVariable Long id) {
        produtoService.deleteById(id);
    }
}
