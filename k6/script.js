import http from 'k6/http';
import { sleep } from 'k6';

export const options = {
  stages: [
    { duration: '1s', target: 50 },
    { duration: '5m', target: 50 },
    { duration: '1s', target: 100 },
    { duration: '5m', target: 100 }
  ]
};

export default function() {
  http.get('http://fiap-techchallenge.local/api/v1/products?category=MAIN_COURSE&page=0&size=10');
  sleep(1);
}
