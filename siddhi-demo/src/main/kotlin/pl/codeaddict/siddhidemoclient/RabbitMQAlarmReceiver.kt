package pl.codeaddict.siddhidemoclient

import org.springframework.amqp.rabbit.annotation.Exchange
import org.springframework.amqp.rabbit.annotation.Queue
import org.springframework.amqp.rabbit.annotation.QueueBinding
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component


@Component
class RabbitMQAlarmReceiver {

    @RabbitListener(bindings = [QueueBinding(value = Queue(value = "alerts", durable = "true"),
            exchange = Exchange(value = "direct_alerts", ignoreDeclarationExceptions = "true"),
            key = ["alerts"])]
    )
    fun receiver(`in`: String) {
        println(" [x] Received '$`in`'")
    }
}