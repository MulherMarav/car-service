# github-service

## Projeto

<p>Neste projeto foi usado <b>Retrofit</b> para comunicação com a API do Github, foi usando <b>Circuit Breaker</b> para resiliência do projeto em 
tratativa de falhas para evitar chamadas excessivas a API do Github. Foi implementado <b>Coroutines</b> para ver mais a fundo requisições assíncronas 
(paralelas) e além disso, foi criado testes com <b>Kotest</b> e <b>MockK</b> para garantir que tudo esteja funcionando como esperado.</p>

### Retrofit:

<p>O Retrofit facilita a comunicação com outros serviços através de interfaces que representam chamadas para esses serviços. Uma vez que os dados são 
tratados pelo seu serviço, eles podem ser devolvidos como resposta em um endpoint próprio para solicitações recebidas por seu aplicativo.</p>

### Circuit Breaker:

<p>Para melhorar a resiliência de um sistema, o Circuit Breaker funciona como um interruptor de segurança. Quando ativado, ele monitora as chamadas para 
serviços externos. Se detectar uma taxa excessiva de falhas ou latência alta, o Circuit Breaker entra em ação, interrompendo temporariamente as chamadas 
para o serviço com problemas. Em vez de continuar tentando acessar um serviço com falha, o Circuit Breaker retorna respostas rápidas e predefinidas, como 
erros ou respostas alternativas.</p>

### Coroutines:

<p>Requisições assíncronas utilizando coroutines e <b>virtual threads</b> proporcionam um processo não bloqueante, empregando lambdas na programação 
funcional para uma implementação mais simplificada. Esse método utiliza threads mais leves e é mais fácil de implementar. Com essa abordagem, não é necessário 
que a mesma thread finalize a requisição, pois tem o dinamismo de: uma thread pode enviar a requisição para outro serviço e continuar processando outras 
requisições. Quando a primeira requisição retornar, será processada por outra thread disponível.</p>

### Container do Redis:

<p>Para facilitar o uso do Redis, foi utilizado uma imagem do Redis, para gerar um container do Docker localmente.</p>

```bash
docker pull redis:bullseye
```

```bash
docker run -d --name redis -p 6379:6379 redis:bullseye
```

### Profiles:

<p>Para rodar aplicação sem informar o profile, será utilizado o profile default (local)</p>

```bash
mvn spring-boot:run
```

* para rodar aplicação informando o profile

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

### Docker Images:

<p>Para gerar a imagem do projeto e subir no DockerHub, é necessário ter conta no DockerHub, o Docker instalado na máquina e executar os comandos abaixo:</p>

````bash
docker login
mvn clean 
mvn install
docker image build -t github-service:v1
docker tag sha256:3499ff6010c6b9a2f2b85999869ef5e6f3aff9410bfaa028937a97283629484c mulhermarav/github-service:v1
docker push mulhermarav/github-service:v1 
````