import http from 'k6/http';
import { sleep } from 'k6';

var multiplyFactor = 20

export const options = {
  stages: [
    { duration: '1s', target: 5 * multiplyFactor },
    { duration: '2m', target: 5 * multiplyFactor },
    { duration: '1s', target: 10 * multiplyFactor },
    { duration: '2m', target: 10 * multiplyFactor },
    { duration: '1s', target: 15 * multiplyFactor },
    { duration: '2m', target: 15 * multiplyFactor },
    { duration: '1s', target: 20 * multiplyFactor },
    { duration: '2m', target: 20 * multiplyFactor },
    { duration: '1s', target: 25 * multiplyFactor },
    { duration: '2m', target: 25 * multiplyFactor }
  ]
};

  export default function() {
    http.get('http://fiap-techchallenge.local:30080/api/v1/products?category=MAIN_COURSE&page=0&size=10');
    sleep(1);
  }
