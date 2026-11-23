
(() => {

  // 1. UTILITÁRIOS E ESTADO GLOBAL

  const $ = (s, root = document) => root.querySelector(s);
  const $$ = (s, root = document) => [...root.querySelectorAll(s)];

  const estado = {
    usuario: null,
    demonstracao: false,
    importado: false
  };

  // Atualização dos anos no rodapé
  $('#ano-atual').textContent = new Date().getFullYear();
  $('#ano-rodape').textContent = new Date().getFullYear();

  // Exibição de avisos
  const exibirAviso = (mensagem, tipo = 'informacao') => {
    const el = document.createElement('div');

    el.className = `aviso ${tipo === 'informacao' ? 'informacao' : tipo}`;
    el.textContent = mensagem;

    $('#regiao-avisos').append(el);

    setTimeout(() => el.remove(), 4200);
  };

  // 2. AUTENTICAÇÃO

  function alternarAbaAutenticacao(aba) {
    const entrar = aba === 'entrar';

    $('#formulario-entrar').classList.toggle('oculto', !entrar);
    $('#formulario-cadastro').classList.toggle('oculto', entrar);

    $$('.aba-autenticacao').forEach(botaoAba => {
      botaoAba.classList.toggle(
        'ativo',
        botaoAba.dataset.abaAutenticacao === aba
      );
    });

    $('#titulo-acesso').textContent = entrar
      ? 'Acesse sua conta'
      : 'Crie seu acesso';

    $('#subtitulo-acesso').textContent = entrar
      ? 'Entre para acompanhar sua produção acadêmica.'
      : 'Cadastre-se para começar a organizar sua trajetória.';
  }

  // Alternância entre as abas de autenticação
  $$('[data-aba-autenticacao]').forEach(botaoAba => {
    botaoAba.addEventListener('click', () => {
      alternarAbaAutenticacao(botaoAba.dataset.abaAutenticacao);
    });
  });

  // Exibição e ocultação de senhas
  $$('.alternar-senha').forEach(botaoAba => {
    botaoAba.addEventListener('click', () => {
      const campo = botaoAba.previousElementSibling;

      campo.type = campo.type === 'password' ? 'text' : 'password';
    });
  });

  // Cadastro de usuário
  $('#formulario-cadastro').addEventListener('submit', async evento => {
    evento.preventDefault();

    const formulario = evento.currentTarget;
    const dados = Object.fromEntries(new FormData(formulario));
    const botao = $('button[type=submit]', formulario);

    botao.disabled = true;
    botao.textContent = 'Criando conta...';

    try {
      await API_LATTESSCORE.cadastrar(dados);

      exibirAviso(
        'Cadastro realizado com sucesso. Você já pode acessar quando a autenticação estiver disponível.',
        'sucesso'
      );

      formulario.reset();
      alternarAbaAutenticacao('entrar');

    } catch (erro) {
      exibirAviso(erro.message, 'erro');

    } finally {
      botao.disabled = false;
      botao.innerHTML = 'Criar minha conta <span>→</span>';
    }
  });

  // Tentativa de autenticação
  $('#formulario-entrar').addEventListener('submit', evento => {
    evento.preventDefault();

    exibirAviso(
      'O back-end atual não possui endpoint de autenticação. Para visualizar a interface, use o acesso de demonstração abaixo.',
      'informacao'
    );
  });

  // Botão de acesso à demonstração
  const botaoDemonstracao = document.createElement('button');

  botaoDemonstracao.type = 'button';
  botaoDemonstracao.className = 'botao botao-secundario botao-largo';
  botaoDemonstracao.textContent = 'Explorar demonstração';

  botaoDemonstracao.addEventListener('click', () => {
    const email =
      $('#formulario-entrar [name=email]').value ||
      'pesquisador@exemplo.edu.br';

    abrirAplicacao({
      email,
      perfil: 'PESQUISADOR'
    }, true);
  });

  $('#formulario-entrar').insertBefore(
    botaoDemonstracao,
    $('#aviso-login')
  );

  // 3. ACESSO À APLICAÇÃO

  function abrirAplicacao(usuario, demonstracao = false) {
    estado.usuario = usuario;
    estado.demonstracao = demonstracao;

    $('#tela-autenticacao').classList.add('oculto');
    $('#tela-aplicacao').classList.remove('oculto');

    const iniciais = (usuario.email || 'LS')
      .split('@')[0]
      .split(/[._-]/)
      .slice(0, 2)
      .map(x => x[0])
      .join('')
      .toUpperCase();

    $('#avatar-usuario-atual').textContent = iniciais || 'LS';
    $('#avatar-perfil').textContent = iniciais || 'LS';

    $('#email-perfil').textContent = usuario.email;
    $('#email-perfil-detalhe').textContent = usuario.email;

    $('#tipo-usuario-perfil').textContent = usuario.perfil || 'Usuário';
    $('#tipo-usuario-perfil-detalhe').textContent = usuario.perfil || '—';

    $('#estado-sessao-perfil').textContent = demonstracao
      ? 'Demonstração local'
      : 'Cadastro registrado';

    $('#endereco-api').textContent = API_LATTESSCORE.enderecoBase;

    $('#texto-estado-api').textContent = demonstracao
      ? 'Modo demonstração'
      : 'API não verificada';

    if (demonstracao) {
      $('#texto-estado-api').textContent = 'Modo demonstração';
    }

    navegarPara(location.hash.slice(1) || 'inicio');
  }

  // 4. NAVEGAÇÃO ENTRE PÁGINAS

  function navegarPara(pagina) {
    const paginasPermitidas = [
      'inicio',
      'importar',
      'producao',
      'avaliacao',
      'perfil'
    ];

    if (!paginasPermitidas.includes(pagina)) {
      pagina = 'inicio';
    }

    // Ativação da seção correspondente
    $$('.secao-pagina').forEach(s => {
      s.classList.toggle('ativo', s.id === `pagina-${pagina}`);
    });

    // Atualização dos links de navegação
    $$('.link-navegacao[data-pagina]').forEach(a => {
      a.classList.toggle('ativo', a.dataset.pagina === pagina);
    });

    // Atualização do título da página
    $('#navegacao-atual').textContent = ({
      inicio: 'Visão geral',
      importar: 'Importar currículo',
      producao: 'Produção acadêmica',
      avaliacao: 'Avaliação e pontuação',
      perfil: 'Meu perfil'
    })[pagina];

    history.replaceState(null, '', `#${pagina}`);

    fecharMenu();
  }

  // Eventos de navegação
  $$('[data-pagina]').forEach(a => {
    a.addEventListener('click', evento => {
      evento.preventDefault();
      navegarPara(a.dataset.pagina);
    });
  });

  window.addEventListener('hashchange', () => {
    navegarPara(location.hash.slice(1));
  });

  // Acesso ao perfil pelo avatar
  $('#avatar-usuario-atual').addEventListener('click', () => {
    navegarPara('perfil');
  });

  // Encerramento da sessão
  $('#botao-sair').addEventListener('click', () => {
    estado.usuario = null;
    estado.demonstracao = false;

    $('#tela-aplicacao').classList.add('oculto');
    $('#tela-autenticacao').classList.remove('oculto');

    $('#formulario-entrar').reset();
  });

  // 5. MENU LATERAL

  const barraLateral = $('#barra-lateral');
  const fundoLateral = $('#fundo-barra-lateral');

  function fecharMenu() {
    barraLateral.classList.remove('aberto');
    fundoLateral.classList.remove('exibido');
  }

  // Abrir menu
  $('#alternar-menu').addEventListener('click', () => {
    barraLateral.classList.add('aberto');
    fundoLateral.classList.add('exibido');
  });

  // Fechar menu
  $('#fechar-barra-lateral').addEventListener('click', fecharMenu);
  fundoLateral.addEventListener('click', fecharMenu);

  // 6. VERIFICAÇÃO DA API

  async function verificarApi() {
    const estadoApi = $('.estado-api');
    const selo = $('#selo-conexao');
    const ponto = $('#ponto-api');

    $('#texto-estado-api').textContent = 'Verificando...';

    try {
      await fetch(API_LATTESSCORE.enderecoBase, {
        method: 'GET',
        mode: 'no-cors'
      });

      estadoApi.classList.remove('desconectado');
      estadoApi.classList.add('conectado');

      $('#texto-estado-api').textContent = 'Servidor acessível';

      selo.textContent = 'Acessível';
      selo.className = 'selo-estado neutro';

      ponto.classList.add('pronto');

      exibirAviso(
        'Servidor respondeu à verificação de rede. Isso não confirma os endpoints da aplicação.',
        'sucesso'
      );

    } catch {
      estadoApi.classList.remove('conectado');
      estadoApi.classList.add('desconectado');

      $('#texto-estado-api').textContent = 'Sem conexão';

      selo.textContent = 'Indisponível';

      ponto.classList.remove('pronto');

      exibirAviso(
        'Não foi possível alcançar o servidor.',
        'erro'
      );
    }
  }

  $('#verificar-api').addEventListener('click', verificarApi);

  // 7. SELEÇÃO E ENVIO DE ARQUIVO XML

  const entradaArquivo = $('#arquivo-xml');
  const areaEnvio = $('#area-envio-arquivo');
  const arquivoSelecionado = $('#arquivo-selecionado');

  // Exibição do arquivo selecionado
  function mostrarArquivo(arquivo) {
    if (!arquivo) return;

    if (!arquivo.name.toLowerCase().endsWith('.xml')) {
      exibirAviso(
        'Selecione um arquivo com extensão .xml.',
        'erro'
      );

      entradaArquivo.value = '';
      arquivoSelecionado.classList.add('oculto');

      return;
    }

    arquivoSelecionado.textContent =
      `✓ ${arquivo.name} · ${(arquivo.size / 1024 / 1024).toFixed(2)} MB`;

    arquivoSelecionado.classList.remove('oculto');
  }

  // Seleção convencional de arquivo
  entradaArquivo.addEventListener('change', () => {
    mostrarArquivo(entradaArquivo.files[0]);
  });

  // Eventos de arrastar e soltar
  ['dragenter', 'dragover'].forEach(nomeEvento => {
    areaEnvio.addEventListener(nomeEvento, eventoArraste => {
      eventoArraste.preventDefault();
      areaEnvio.classList.add('arrastando-arquivo');
    });
  });

  ['dragleave', 'drop'].forEach(nomeEvento => {
    areaEnvio.addEventListener(nomeEvento, eventoArraste => {
      eventoArraste.preventDefault();
      areaEnvio.classList.remove('arrastando-arquivo');
    });
  });

  // Recebimento do arquivo arrastado
  areaEnvio.addEventListener('drop', evento => {
    const arquivo = evento.dataTransfer.files[0];

    if (arquivo) {
      const transferencia = new DataTransfer();

      transferencia.items.add(arquivo);
      entradaArquivo.files = transferencia.files;

      mostrarArquivo(arquivo);
    }
  });

  // 8. IMPORTAÇÃO DO CURRÍCULO LATTES

  $('#formulario-importacao').addEventListener('submit', async evento => {
    evento.preventDefault();

    const formulario = evento.currentTarget;
    const identificador = formulario.elements.idLattes.value.trim();
    const arquivo = entradaArquivo.files[0];

    const botao = $('#botao-enviar-importacao');
    const retorno = $('#retorno-importacao');

    retorno.className = 'retorno oculto';

    // Validação do arquivo
    if (!arquivo) {
      exibirAviso(
        'Selecione o arquivo XML antes de continuar.',
        'erro'
      );

      return;
    }

    // Confirmação para arquivos grandes
    if (
      arquivo.size > 20 * 1024 * 1024 &&
      !confirm('O arquivo possui mais de 20 MB. Deseja tentar enviar mesmo assim?')
    ) {
      return;
    }

    botao.disabled = true;
    botao.textContent = 'Enviando currículo...';

    try {
      const resultado = await API_LATTESSCORE.importarCurriculo(
        identificador,
        arquivo
      );

      retorno.textContent = typeof resultado === 'string'
        ? resultado
        : 'Currículo enviado com sucesso.';

      retorno.className = 'retorno sucesso';

      // Atualização do estado da importação
      estado.importado = true;

      $('#indicador-curriculo').textContent = 'Enviado';
      $('#observacao-curriculo').textContent = 'Arquivo recebido pela API';

      exibirAviso(
        'Arquivo enviado. O back-end confirmou o recebimento.',
        'sucesso'
      );

      formulario.reset();
      arquivoSelecionado.classList.add('oculto');

    } catch (erro) {
      retorno.textContent = erro.message;
      retorno.className = 'retorno erro';

      exibirAviso(
        'Falha ao importar currículo.',
        'erro'
      );

    } finally {
      botao.disabled = false;
      botao.innerHTML = 'Enviar currículo <span>→</span>';
    }
  });

})();
