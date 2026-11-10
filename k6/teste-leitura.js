import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    vus: 100,
    duration: '30s',
};

export default function () {
    const email = 'leitura@teste.com';

    const url = `http://localhost:8080/api/usuarios/buscar?email=${email}`;

    const res = http.get(url);

    check(res, {
        'status 200': (r) => r.status === 200,
    });

    sleep(1);
}