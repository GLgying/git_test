多线程 线程池 队列mq  redis 与mysql 
 缓存不一致    分布式   集群  



交换机类型  BuiltinExchangeType.TOPIC    
1.DIRECT("direct"), 
2.FANOUT("fanout"), 扇形        消息广播  发布订阅
3.TOPIC("topic"), 主题  匹配  在路由模式的基础上，支持了对key的通配符匹配（星号以及井号），以满足更加复杂的消息分发场景。。“#” : 匹配一个或者多个
“*”：匹配一个。abc.#会匹配所有abc开头的key
4.HEADERS("headers");
不处理路由键。而是根据发送的消息内容中的headers属性进行匹配。在绑定Queue与Exchange时指定一组键值对；当消息发送到RabbitMQ时会取到该消息的headers与Exchange绑定时指定的键值对进行匹配；如果完全匹配则消息会路由到该队列，否则不会路由到该队列。headers属性是一个键值对，可以是Hashtable，键值对的值可以是任何类型。而fanout，direct，topic 的路由键都需要要字符串形式的。
匹配规则x-match有下列两种类型：
x-match = all ：表示所有的键值对都匹配才能接受到消息
x-match = any ：表示只要有键值对匹配就能接受到消息
