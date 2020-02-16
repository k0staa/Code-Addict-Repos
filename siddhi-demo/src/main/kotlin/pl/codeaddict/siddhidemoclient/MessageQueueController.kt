package pl.codeaddict.siddhidemoclient

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageQueueController(
        private val rabbitMQSender: RabbitMQSenderService) {

    @PostMapping(value = ["/messages"])
    fun addMessageToQueue(@RequestBody msg: String): String {
        rabbitMQSender.send(msg);
        return "Message sent to the RabbitMQ Successfully";
    }
}