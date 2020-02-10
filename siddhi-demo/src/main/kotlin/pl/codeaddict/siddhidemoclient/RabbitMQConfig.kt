package pl.codeaddict.siddhidemoclient

import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig {
    @Value("\${siddhidemo.rabbitmq.messageQueue}")
    val messageQueue: String? = null

//    @Value("\${siddhidemo.rabbitmq.exchange}")
//    val exchange: String? = null
//
//    @Value("\${siddhidemo.rabbitmq.routingkey}")
//    val routingkey: String? = null

    @Bean
    fun queue(): Queue {
        return Queue(messageQueue, false);
    }

//    @Bean
//    fun exchange(): DirectExchange {
//        return DirectExchange(exchange)
//    }
//
//    @Bean
//    fun binding(queue: Queue, exchange: DirectExchange): Binding {
//        return BindingBuilder.bind(queue).to(exchange).with(routingkey)
//    }
//
//    @Bean
//    fun jsonMessageConverter(): MessageConverter {
//        return Jackson2JsonMessageConverter()
//    }
//
//    @Bean
//    fun rabbitTemplate(connectionFactory: ConnectionFactory): AmqpTemplate {
//        val rabbitTemplate = RabbitTemplate(connectionFactory)
//        rabbitTemplate.messageConverter = jsonMessageConverter()
//        return rabbitTemplate
//    }
}