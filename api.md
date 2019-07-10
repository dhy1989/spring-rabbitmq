****mq高级特性****
#自定义消费者
#消息的ack与重回队列
#消息的限流
#TTL消息
    time to live 消息存活时间
#死信队列
    DLX,Dead-Letter-Exchange
    利用DLX,当消息在一个队列中变成死信(dead message)之后,它能够被重新publish到另一个Exchange,这个Exchange就是DLX
    变成dead message的情况:
        1.消息被拒绝(basic.reject/basic.nack)并且requeue=false
        2.消息TTL过期
        3.队列达到最大长度
    DLX是一个正常的Exchange,和一般的Exchange没有区别,它能在任何的队列上被指定,实际上就是设置某个队列的属性
    当这个队列中有死信时,RabbitMq就会自动将这个消息发布到设置的exchange上去,进而被路由到另一个队列    
