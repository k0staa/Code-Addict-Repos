package pl.codeaddict.siddhidemoclient

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageQueueController {

    @Autowired
    private val rabbitMQSender: RabbitMQSenderService? = null

    @PostMapping(value = ["/messages"])
    fun addMessageToQueue(@RequestBody msg: String): String {
        rabbitMQSender!!.send(msg);
        return "Message sent to the RabbitMQ Successfully";
    }
}