(() => {
  const enderecoBase = window.CONFIGURACAO_LATTESSCORE.URL_BASE_API.replace(/\/$/, '');
  async function requisitar(caminho, opcoes = {}) {
    const controlador = new AbortController();
    const tempoLimite = setTimeout(() => controlador.abort(), window.CONFIGURACAO_LATTESSCORE.TEMPO_LIMITE_REQUISICAO_MS);
    try {
      const resposta = await fetch(`${enderecoBase}${caminho}`, { ...opcoes, signal: controlador.signal });
      const texto = await resposta.text();
      if (!resposta.ok) throw new Error(texto || `Erro HTTP ${resposta.status}`);
      if (!texto) return null;
      try { return JSON.parse(texto); } catch { return texto; }
    } catch (erro) {
      if (erro.name === 'AbortError') throw new Error('A API demorou para responder. Tente novamente.');
      if (erro instanceof TypeError) throw new Error('Não foi possível conectar à API. Verifique se o Spring Boot está em execução e se o CORS está configurado.');
      throw erro;
    } finally { clearTimeout(tempoLimite); }
  }
  window.API_LATTESSCORE = {
    enderecoBase,
    cadastrar: dados => requisitar('/api/usuarios/registar', { method: 'POST', headers: {'Content-Type':'application/json'}, body: JSON.stringify(dados) }),
    buscarUsuario: enderecoEmail => requisitar(`/api/usuarios/buscar?email=${encodeURIComponent(enderecoEmail)}`),
    importarCurriculo: (identificadorLattes, arquivo) => { const formulario = new FormData(); formulario.append('idLattes', identificadorLattes); formulario.append('file', arquivo); return requisitar('/api/lattes/importar', { method:'POST', body:formulario }); },
    verificar: () => requisitar('/api/usuarios/buscar?email=__healthcheck__')
  };
})();
