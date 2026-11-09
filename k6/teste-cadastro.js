
import http from 'k6/http';
import { check, sleep } from 'k6';
const RUN_ID = Date.now();

export const options = {
  stages: [
    { duration: '10s', target: 5 },
    { duration: '20s', target: 5 },
    { duration: '10s', target: 10 },
    { duration: '20s', target: 10 },
    { duration: '10s', target: 20 },
    { duration: '20s', target: 20 },
    { duration: '10s', target: 0 },
  ],
};

export default function () {
  const url = 'http://localhost:8080/api/usuarios/registar';

  const payload = JSON.stringify({
    email: `k6_${RUN_ID}_${__VU}_${__ITER}@teste.local`,
    senha: 'senha_teste',
    perfil: 'DOCENTE',
  });

  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  const res = http.post(url, payload, params);

  if (res.status !== 200) {
  console.log(`STATUS: ${res.status} | RESPOSTA: ${res.body}`);
}

  check(res, {
    'cadastro retornou 200': (r) => r.status === 200,
    'resposta abaixo de 500ms': (r) => r.timings.duration < 500,
  });

  sleep(1);
}
