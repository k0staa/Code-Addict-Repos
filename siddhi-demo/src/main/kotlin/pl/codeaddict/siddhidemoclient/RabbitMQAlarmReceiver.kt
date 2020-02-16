package pl.codeaddict.siddhidemoclient

import org.springframework.amqp.rabbit.annotation.Exchange
import org.springframework.amqp.rabbit.annotation.Queue
import org.springframework.amqp.rabbit.annotation.QueueBinding
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class RabbitMQAlarmReceiver {

    @RabbitListener(bindings = [QueueBinding(value = Queue(value = "\${siddhidemo.rabbitmq.alertsQueue}", durable = "true"),
            exchange = Exchange(value = "\${siddhidemo.rabbitmq.alertsExchange}", durable = "false", ignoreDeclarationExceptions = "true"),
            key = ["\${siddhidemo.rabbitmq.alertsRoutingKey}"])]
    )
    fun receiver(`in`: String) {
        println(" [x] Received '$`in`'")
    }
}