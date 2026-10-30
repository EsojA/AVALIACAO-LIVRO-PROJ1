package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Livro {
	private static final List<Livro> BIBLIOTECA = new ArrayList<>();
	private static boolean livrosCarregados = false;

	private int id;
	private String titulo;
	private String autor;
	private String ano;
	private final List<Avaliacao> avaliacoes = new ArrayList<>();
	private final List<String> comentarios = new ArrayList<>();

	public Livro() {
		this(0, "", "", "");
	}

	public Livro(int id, String titulo, String autor, String ano) {
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.ano = ano;
	}

	public static void carregarLivrosTeste() {
		if (livrosCarregados) {
			return;
		}

		BIBLIOTECA.clear();

		Livro livro1 = new Livro(1, "Dom Casmurro", "Machado de Assis", "1899");
		livro1.adicionarAvaliacao(5, "Excelente leitura, muito envolvente.");
		livro1.adicionarAvaliacao(4, "Gostei bastante, embora seja um pouco longo.");
		livro1.adicionarComentario("Narrativa muito rica e bem construída.");
		livro1.adicionarComentario("Personagens muito marcantes.");
		BIBLIOTECA.add(livro1);

		Livro livro2 = new Livro(2, "A Guerra dos Tronos", "George R. R. Martin", "1996");
		livro2.adicionarAvaliacao(5, "Imperdível, muito detalhado e intenso.");
		livro2.adicionarAvaliacao(3, "Boa história, porém muito densa.");
		livro2.adicionarComentario("Muitos personagens e reviravoltas.");
		BIBLIOTECA.add(livro2);

		Livro livro3 = new Livro(3, "O Pequeno Príncipe", "Antoine de Saint-Exupéry", "1943");
		livro3.adicionarAvaliacao(4, "Lindo e reflexivo, vale a pena.");
		livro3.adicionarAvaliacao(5, "Um clássico que sempre encanta.");
		livro3.adicionarComentario("Mensagem profunda e simples ao mesmo tempo.");
		BIBLIOTECA.add(livro3);

		Livro livro4 = new Livro(4, "1984", "George Orwell", "1949");
		livro4.adicionarAvaliacao(2, "Muito pesado e assustador.");
		livro4.adicionarAvaliacao(4, "Ótima crítica social e muito impactante.");
		livro4.adicionarComentario("Tema relevante até hoje.");
		BIBLIOTECA.add(livro4);

		Livro livro5 = new Livro(5, "A Metamorfose", "Franz Kafka", "1915");
		livro5.adicionarAvaliacao(3, "Interessante, mas bem estranho.");
		livro5.adicionarAvaliacao(5, "Uma obra muito forte e memorável.");
		livro5.adicionarComentario("Excelente para reflexão.");
		livro5.adicionarComentario("Muito simbólico e profundo.");
		BIBLIOTECA.add(livro5);

		livrosCarregados = true;
	}

	public static List<Livro> listarLivros() {
		carregarLivrosTeste();
		return new ArrayList<>(BIBLIOTECA);
	}

	public void listar() {
		System.out.println("Lista de livros disponiveis:");
		for (Livro livro : listarLivros()) {
			System.out.println("- " + livro.getId() + " | " + livro.getTitulo() + " | " + livro.getAutor() + " | " + livro.getAno());
		}
	}

	public void visualizarAvaliação() {
		System.out.println("Avaliações de: " + this.titulo);
		if (this.avaliacoes.isEmpty()) {
			System.out.println("Nenhuma avaliação registrada para este livro.");
			return;
		}
		for (Avaliacao avaliacao : this.avaliacoes) {
			System.out.println("Nota: " + avaliacao.getNota() + "/5 estrelas | Comentario: " + avaliacao.getComentario());
		}
		System.out.println("Media geral: " + mediaAvaliacoes() + "/5 estrelas");
		System.out.println("Comentarios:");
		for (String comentario : comentarios) {
			System.out.println("- " + comentario);
		}
	}

	public void adicionarAvaliacao(float nota, String comentario) {
		if (nota < 1 || nota > 5) {
			throw new IllegalArgumentException("A nota deve estar entre 1 e 5 estrelas.");
		}

		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setNota(nota);
		avaliacao.setComentario(comentario);
		avaliacao.registrar();
		this.avaliacoes.add(avaliacao);
		if (comentario != null && !comentario.trim().isEmpty()) {
			this.comentarios.add(comentario.trim());
		}
	}

	public void adicionarComentario(String comentario) {
		if (comentario == null || comentario.trim().isEmpty()) {
			return;
		}
		this.comentarios.add(comentario.trim());
	}

	public double mediaAvaliacoes() {
		if (this.avaliacoes.isEmpty()) {
			return 0.0;
		}
		double total = 0;
		for (Avaliacao avaliacao : this.avaliacoes) {
			total += avaliacao.getNota();
		}
		return total / this.avaliacoes.size();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public List<Avaliacao> getAvaliacoes() {
		return new ArrayList<>(avaliacoes);
	}

	public List<String> getComentarios() {
		return new ArrayList<>(comentarios);
	}
}