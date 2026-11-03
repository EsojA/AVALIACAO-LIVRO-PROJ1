const listaLivros = document.getElementById('listaLivros');

function formatarEstrelas(nota) {
  const valor = Number(nota) || 0;
  return '★'.repeat(valor) + '☆'.repeat(Math.max(0, 5 - valor));
}

function calcularMedia(avaliacoes) {
  if (!avaliacoes || avaliacoes.length === 0) return 0;
  const total = avaliacoes.reduce((soma, item) => soma + Number(item.nota), 0);
  return (total / avaliacoes.length).toFixed(1);
}

function renderizarLivro(livro) {
  const card = document.createElement('div');
  card.className = 'livro';

  const media = calcularMedia(livro.avaliacoes);

  card.innerHTML = `
    <h2>${livro.titulo}</h2>
    <div class="meta"><strong>Autor:</strong> ${livro.autor}</div>
    <div class="meta"><strong>Ano:</strong> ${livro.ano}</div>
    <div class="meta"><strong>Média:</strong> ${media} / 5</div>

    <div class="botoes">
      <button class="btn-visualizar" data-acao="visualizar" data-id="${livro.id}">Visualizar avaliações</button>
      <button class="btn-avaliar" data-acao="avaliar" data-id="${livro.id}">Registrar avaliação</button>
    </div>

    <div class="avaliacoes" id="avaliacoes-${livro.id}">
      ${
        livro.avaliacoes && livro.avaliacoes.length
          ? livro.avaliacoes.map(item => `
              <div class="avaliacao-item">
                <div class="estrelas">${formatarEstrelas(item.nota)}</div>
                <div>${item.comentario}</div>
              </div>
            `).join('')
          : '<div class="small">Nenhuma avaliação registrada ainda.</div>'
      }
    </div>

    <div class="form-avaliacao" id="form-${livro.id}">
      <label for="nota-${livro.id}">Nota (1 a 5 estrelas):</label>
      <select id="nota-${livro.id}">
        <option value="5">5 estrelas</option>
        <option value="4">4 estrelas</option>
        <option value="3">3 estrelas</option>
        <option value="2">2 estrelas</option>
        <option value="1">1 estrela</option>
      </select>

      <label for="comentario-${livro.id}">Comentário:</label>
      <textarea id="comentario-${livro.id}" placeholder="Digite seu comentário..."></textarea>

      <button class="btn-avaliar" data-acao="salvar" data-id="${livro.id}">Salvar avaliação</button>
    </div>
  `;

  return card;
}

async function carregarLivros() {
  const urls = ['/api/livros'];

  let ultimaErro = null;

  for (const url of urls) {
    try {
      const resposta = await fetch(url);
      if (!resposta.ok) {
        throw new Error('Resposta inválida da API');
      }

      const livros = await resposta.json();
      listaLivros.innerHTML = '';
      livros.forEach(livro => {
        listaLivros.appendChild(renderizarLivro(livro));
      });
      return;
    } catch (erro) {
      ultimaErro = erro;
    }
  }

  listaLivros.innerHTML = '<div class="small">Erro ao carregar os livros do sistema Java. Verifique se a aplicação Spring está rodando corretamente.</div>';
  console.error(ultimaErro);
}

document.addEventListener('click', async function (event) {
  const botao = event.target.closest('button');
  if (!botao) return;

  const acao = botao.dataset.acao;
  const id = Number(botao.dataset.id);

  if (acao === 'visualizar') {
    const painel = document.getElementById(`avaliacoes-${id}`);
    if (painel) {
      painel.classList.toggle('visible');
    }
  }

  if (acao === 'avaliar') {
    const form = document.getElementById(`form-${id}`);
    if (form) {
      form.classList.toggle('visible');
    }
  }

  if (acao === 'salvar') {
    const selectNota = document.getElementById(`nota-${id}`);
    const inputComentario = document.getElementById(`comentario-${id}`);

    const nota = Number(selectNota.value);
    const comentario = inputComentario.value.trim();

    if (!comentario) {
      alert('Digite um comentário antes de salvar.');
      return;
    }

    try {
      const url = `/api/livros/${id}/avaliacoes`;

      const resposta = await fetch(url, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ nota, comentario })
      });

      const resultado = await resposta.json();
      if (!resposta.ok) {
        throw new Error(resultado.mensagem || 'Erro ao registrar avaliação');
      }

      carregarLivros();
    } catch (erro) {
      alert(erro.message || 'Erro ao salvar avaliação.');
    }
  }
});

carregarLivros();
