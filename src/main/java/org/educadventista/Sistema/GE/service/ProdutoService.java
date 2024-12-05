package org.educadventista.Sistema.GE.service;

import org.educadventista.Sistema.GE.entity.Produto;
import org.educadventista.Sistema.GE.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Produto findById(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void deleteById(Long id) {
        produtoRepository.deleteById(id);
    }

    public Produto update(Long id, Produto produtoAtualizado) {
        Optional<Produto> produtoExistenteOpt = produtoRepository.findById(id);
        if (!produtoExistenteOpt.isPresent()) {
            return null; // ou lançar uma exceção apropriada
        }

        Produto produtoExistente = produtoExistenteOpt.get();

        // Atualiza apenas os campos fornecidos
        if (produtoAtualizado.getNome() != null) {
            produtoExistente.setNome(produtoAtualizado.getNome());
        }
        if (produtoAtualizado.getDescricao() != null) {
            produtoExistente.setDescricao(produtoAtualizado.getDescricao());
        }
        if (produtoAtualizado.getPreco() != null) {
            produtoExistente.setPreco(produtoAtualizado.getPreco());
        }
        if (produtoAtualizado.getQuantidade() != null) {
            produtoExistente.setQuantidade(produtoAtualizado.getQuantidade());
        }

        return produtoRepository.save(produtoExistente);
    }
}
