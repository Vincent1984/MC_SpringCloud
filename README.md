VincentTest-eureka-server: 架构服务注册中心<br/>
VincentTest-eureka-provider： 服务提供方<br/>
VincentTest-eureka-consu： 服务消费方， （Ribbon+RestTemplate）+Hytrix<br/>
VincentTest-eureka-consu-feign: 服务消费方 （Feign）+ Hytrix<br/>
VincnetTest-eureka-zull 路由器<br/>
VincentTest-config-server: 配置中心服务<br/>
VincentTest-config-client: 配置中心客户端<br/>
VincentTest-swagger-spring: swagger 演示<br/>

启动的时候 先启动 eureka-server 项目， 然后启动 eureka-provider 项目 其他的逐步启动 就可以
