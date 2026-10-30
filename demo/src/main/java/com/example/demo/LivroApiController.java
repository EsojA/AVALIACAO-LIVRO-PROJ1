package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:5500", "http://127.0.0.1:5500"}, allowCredentials = "true")
public class LivroApiController {

    @GetMapping("/livros")
    public List<Map<String, Object>> listarLivros() {
        List<Livro> livros = Livro.listarLivros();
        List<Map<String, Object>> resultado = new ArrayList<>();

        for (Livro livro : livros) {
            Map<String, Object> item = new HashMap<>();
            item.put("id", livro.getId());
            item.put("titulo", livro.getTitulo());
            item.put("autor", livro.getAutor());
            item.put("ano", livro.getAno());

            List<Map<String, Object>> avaliacoes = new ArrayList<>();
            for (Avaliacao avaliacao : livro.getAvaliacoes()) {
                Map<String, Object> avaliacaoMap = new HashMap<>();
                avaliacaoMap.put("id", avaliacao.getId());
                avaliacaoMap.put("nota", avaliacao.getNota());
                avaliacaoMap.put("comentario", avaliacao.getComentario());
                avaliacoes.add(avaliacaoMap);
            }

            item.put("avaliacoes", avaliacoes);
            item.put("comentarios", livro.getComentarios());
            item.put("media", livro.mediaAvaliacoes());
            resultado.add(item);
        }

        return resultado;
    }

    @PostMapping("/livros/{id}/avaliacoes")
    public ResponseEntity<Map<String, Object>> registrarAvaliacao(
            @PathVariable int id,
            @RequestBody Map<String, Object> payload) {

        List<Livro> livros = Livro.listarLivros();
        Livro livroSelecionado = null;

        for (Livro livro : livros) {
            if (livro.getId() == id) {
                livroSelecionado = livro;
                break;
            }
        }

        if (livroSelecionado == null) {
            Map<String, Object> erro = new HashMap<>();
            erro.put("sucesso", false);
            erro.put("mensagem", "Livro não encontrado");
            return ResponseEntity.badRequest().body(erro);
        }

        Object notaObj = payload.get("nota");
        Object comentarioObj = payload.get("comentario");

        if (notaObj == null || comentarioObj == null) {
            Map<String, Object> erro = new HashMap<>();
            erro.put("sucesso", false);
            erro.put("mensagem", "Nota e comentário são obrigatórios");
            return ResponseEntity.badRequest().body(erro);
        }

        try {
            float nota = Float.parseFloat(String.valueOf(notaObj));
            String comentario = String.valueOf(comentarioObj);

            if (nota < 1 || nota > 5) {
                Map<String, Object> erro = new HashMap<>();
                erro.put("sucesso", false);
                erro.put("mensagem", "A nota deve estar entre 1 e 5 estrelas");
                return ResponseEntity.badRequest().body(erro);
            }

            livroSelecionado.adicionarAvaliacao(nota, comentario);

            Map<String, Object> resposta = new HashMap<>();
            resposta.put("sucesso", true);
            resposta.put("mensagem", "Avaliação registrada com sucesso");
            resposta.put("media", livroSelecionado.mediaAvaliacoes());
            return ResponseEntity.ok(resposta);
        } catch (NumberFormatException e) {
            Map<String, Object> erro = new HashMap<>();
            erro.put("sucesso", false);
            erro.put("mensagem", "Nota inválida");
            return ResponseEntity.badRequest().body(erro);
        }
    }
}
