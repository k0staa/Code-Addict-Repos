package pl.codeaddict.siddhidemoclient

import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service


@Service
class RabbitMQSenderService(
        private val rabbitTemplate: RabbitTemplate,
        private val queue: Queue) {

    fun send(message: String) {
        rabbitTemplate.convertAndSend(queue.name, message);
        println(" [x] Sent '$message'");
    }
}