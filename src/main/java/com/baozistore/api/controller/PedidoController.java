package com.baozistore.api.controller;

import com.baozistore.api.model.Pedido;
import com.baozistore.api.repository.ClienteRepository;
import com.baozistore.api.repository.PedidoRepository;
import com.baozistore.api.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoController(PedidoRepository pedidoRepository,
                            ClienteRepository clienteRepository,
                            ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public ResponseEntity<Pedido> criar(@Valid @RequestBody Pedido pedido) {
        validarRelacionamentos(pedido);
        pedido.setId(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoRepository.save(pedido));
    }

    @GetMapping
    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Pedido buscarPorId(@PathVariable Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }

    @PutMapping("/{id}")
    public Pedido atualizar(@PathVariable Long id, @Valid @RequestBody Pedido dados) {
        Pedido pedido = buscarPorId(id);
        validarRelacionamentos(dados);
        pedido.setClienteId(dados.getClienteId());
        pedido.setProdutoId(dados.getProdutoId());
        pedido.setQuantidade(dados.getQuantidade());
        return pedidoRepository.save(pedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        Pedido pedido = buscarPorId(id);
        pedidoRepository.delete(pedido);
        return ResponseEntity.noContent().build();
    }

    private void validarRelacionamentos(Pedido pedido) {
        if (!clienteRepository.existsById(pedido.getClienteId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "clienteId inexistente");
        }
        if (!produtoRepository.existsById(pedido.getProdutoId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "produtoId inexistente");
        }
    }
}
