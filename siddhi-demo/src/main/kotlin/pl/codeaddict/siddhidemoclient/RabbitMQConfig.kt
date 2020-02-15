package pl.codeaddict.siddhidemoclient

import org.springframework.amqp.core.Queue
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig {
    @Value("\${siddhidemo.rabbitmq.messageQueue}")
    val messageQueue: String? = null

    @Bean
    fun queue(): Queue {
        return Queue(messageQueue, false);
    }
}