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
docker image build -t github-service:v1 .
docker tag sha256:3499ff6010c6b9a2f2b85999869ef5e6f3aff9410bfaa028937a97283629484c mulhermarav/github-service:v1
docker push mulhermarav/github-service:v1 
````

### K3D:

<p>Para criar um cluster local no Docker com o K3D, é necessário ter o K3D instalado na máquina e executar os comandos abaixo:</p>

* https://k3d.io/v5.6.0/#install-specific-release

```bash
brew install k3d
```

````bash
k3d cluster create --port 8080:80@loadbalancer
````

![Captura de Tela 2024-01-15 às 10.48.18 AM.png](src%2Fmain%2Fresources%2Fimg%2FCaptura%20de%20Tela%202024-01-15%20%C3%A0s%2010.48.18%20AM.png)

### Minikube:

<p>Uma alternativa para K3D é o Minikube:</p>

```bash
brew install minikube
```

```bash
minikube start
```

### Kubectl:

<p>Usando a API do Kubernetes para fazer deploy localmente dos services e deployments.</p>

* Dentro da pasta k8s, executar os comandos abaixo:

```bash
~ k8s % kubectl apply -f .\redis\ 
deployment.apps/redis-deployment created
service/redis-service created
```
* Para verificar se o service e o deployment foram criados:

```bash
~ k8s % kubectl get pods
NAME                                      READY   STATUS    RESTARTS      AGE
redis-deployment-7969999f66-px4kr         1/1     Running   0             91s

~ k8s % kubectl get svc
NAME              TYPE        CLUSTER-IP       EXTERNAL-IP   PORT(S)        AGE
kubernetes        ClusterIP   10.96.0.1        <none>        443/TCP        140d
redis-service     ClusterIP   None             <none>        6379/TCP       2m47s
```

* Para fazer deploy da aplicação:

```bash
~ k8s % kubectl apply -f .\app\  
deployment.apps/github-deployment-app created
service/github-service-app created
```
* Consultando os pods e services:

```bash
~ k8s % kubectl get pods
NAME                                      READY   STATUS              RESTARTS      AGE
github-deployment-app-56797dbbd9-972g8    0/1     ContainerCreating   0             93s
redis-deployment-7969999f66-px4kr         1/1     Running             0             5m10s
```

* Para verificar os logs da aplicação:

```bash
~ k8s % kubectl logs github-deployment-app-56797dbbd9-972g8 

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.7.1)

2024-01-15 15:03:10.813  INFO 1 --- [           main] b.com.github.GithubServiceApplicationKt  : Starting GithubServiceApplicationKt v0.0.1-SNAPSHOT using Java 17.0.2 on github-deployment-app-56797dbbd9-972g8 with PID 1 (/github-service.jar started by root in /)
2024-01-15 15:03:10.820  INFO 1 --- [           main] b.com.github.GithubServiceApplicationKt  : The following 1 profile is active: "prod"
2024-01-15 15:03:15.854  INFO 1 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Multiple Spring Data modules found, entering strict repository configuration mode
2024-01-15 15:03:15.859  INFO 1 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data Redis repositories in DEFAULT mode.
2024-01-15 15:03:15.913  INFO 1 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 16 ms. Found 0 Redis repository interfaces.
2024-01-15 15:03:17.401  INFO 1 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2024-01-15 15:03:17.424  INFO 1 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-01-15 15:03:17.424  INFO 1 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.64]
2024-01-15 15:03:17.635  INFO 1 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-01-15 15:03:17.636  INFO 1 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 6682 ms
2024-01-15 15:03:29.430  INFO 1 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2024-01-15 15:03:29.528  INFO 1 --- [           main] b.com.github.GithubServiceApplicationKt  : Started GithubServiceApplicationKt in 21.211 seconds (JVM running for 23.101)
```

* Para fazer a ponte entre o service e a requisição:

```bash
~ k8s % kubectl apply -f .\ingress\ 
ingress.networking.k8s.io/github-service-app-ingress created
```
* Para verificar se o ingress foi criado:

````bash
~ k8s % kubectl get ingress
NAME                         CLASS    HOSTS   ADDRESS   PORTS   AGE
github-service-app-ingress   <none>   *  
````

* Para consultas todos os services, deployments e ingress criados:

```bash
~ k8s % kubectl get all
NAME                                          READY   STATUS    RESTARTS      AGE
pod/github-deployment-app-56797dbbd9-972g8    1/1     Running   0             13m
pod/redis-deployment-7969999f66-px4kr         1/1     Running   0             17m

NAME                         TYPE        CLUSTER-IP       EXTERNAL-IP   PORT(S)          AGE
service/github-service-app   NodePort    10.103.116.12    <none>        8080:30062/TCP   13m
service/kubernetes           ClusterIP   10.96.0.1        <none>        443/TCP          140d
service/redis-service        ClusterIP   None             <none>        6379/TCP         17m

NAME                                     READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/github-deployment-app    1/1     1            1           13m
deployment.apps/redis-deployment         1/1     1            1           17m
```

* No Unix para saber o IP do cluster **192.168.49.2**:

```shell
~ kubernetes % kubectl get nodes -o wide
NAME       STATUS   ROLES           AGE    VERSION   INTERNAL-IP    EXTERNAL-IP   OS-IMAGE             KERNEL-VERSION   CONTAINER-RUNTIME
minikube   Ready    control-plane   168d   v1.27.3   192.168.49.2   <none>        Ubuntu 22.04.2 LTS   6.1.32-0-virt    docker://24.0.4
```