# github-service

### Retrofit:

<p>O Retrofit facilita a comunicação com outros serviços através de interfaces que representam chamadas para esses serviços. Uma vez que os dados são 
tratados pelo seu serviço, eles podem ser devolvidos como resposta em um endpoint próprio para solicitações recebidas por seu aplicativo.</p>

### Circuit Breaker:

<p>Para melhorar a resiliência de um sistema, o Circuit Breaker funciona como um interruptor de segurança. Quando ativado, ele monitora as chamadas para 
serviços externos. Se detectar uma taxa excessiva de falhas ou latência alta, o Circuit Breaker entra em ação, interrompendo temporariamente as chamadas 
para o serviço com problemas. Em vez de continuar tentando acessar um serviço com falha, o Circuit Breaker retorna respostas rápidas e predefinidas, como 
erros ou respostas alternativas.</p>

### Coroutines:

<p>Requisições assíncronas utilizando coroutines e virtual threads proporcionam um processo não bloqueante, empregando lambdas na programação funcional 
para uma implementação mais simplificada. Esse método utiliza threads mais leves e é mais fácil de implementar. Com essa abordagem, não é necessário 
que a mesma thread finalize a requisição, pois tem o dinamismo de: uma thread pode enviar a requisição para outro serviço e continuar processando outras 
requisições. Quando a primeira requisição retornar, será processada por outra thread disponível.</p>