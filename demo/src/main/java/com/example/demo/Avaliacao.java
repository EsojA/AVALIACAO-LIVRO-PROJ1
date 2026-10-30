package com.example.demo;

public class Avaliacao {
	private static int ultimoId = 0;

	private int id;
	private float nota;
	private String comentario;

	public Avaliacao() {
		this(0, 0.0f, "");
	}

	public Avaliacao(int id, float nota, String comentario) {
		this.id = id == 0 ? ++ultimoId : id;
		this.nota = nota;
		this.comentario = comentario == null ? "" : comentario.trim();
		if (this.comentario.isEmpty()) {
			this.comentario = "Sem comentário";
		}
	}

	public void registrar() {
		if (this.nota < 0 || this.nota > 10) {
			this.nota = 0;
		}
		if (this.comentario == null || this.comentario.trim().isEmpty()) {
			this.comentario = "Sem comentário";
		}
		if (this.id == 0) {
			this.id = ++ultimoId;
		}
	}

	public float mediaNota() {
		if (this.nota < 0 || this.nota > 10) {
			return 0.0f;
		}
		return this.nota;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getNota() {
		return nota;
	}

	public void setNota(float nota) {
		this.nota = nota;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario == null ? "" : comentario.trim();
		if (this.comentario.isEmpty()) {
			this.comentario = "Sem comentário";
		}
	}
}
