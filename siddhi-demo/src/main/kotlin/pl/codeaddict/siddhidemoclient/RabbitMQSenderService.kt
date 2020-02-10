package pl.codeaddict.siddhidemoclient

import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class RabbitMQSenderService {
    @Autowired
    private val rabbitTemplate: RabbitTemplate? = null

    @Autowired
    private val queue: Queue? = null

    fun send(message: String) {
        rabbitTemplate!!.convertAndSend(queue!!.name, message);
        System.out.println(" [x] Sent '$message'");
    }
}