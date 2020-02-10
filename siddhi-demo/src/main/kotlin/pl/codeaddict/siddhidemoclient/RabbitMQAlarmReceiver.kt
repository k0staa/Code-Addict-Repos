package pl.codeaddict.siddhidemoclient

import org.springframework.amqp.rabbit.annotation.RabbitHandler

import org.springframework.amqp.rabbit.annotation.RabbitListener


@RabbitListener(queues = ["alerts"])
class RabbitMQAlarmReceiver {

    @RabbitHandler
    fun receive(`in`: String) {
        println(" [x] Received '$`in`'")
    }
}