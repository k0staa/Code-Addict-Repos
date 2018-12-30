package pl.codeaddict.demohazelcast

import com.hazelcast.client.HazelcastClient
import com.hazelcast.client.config.ClientConfig
import com.hazelcast.core.HazelcastInstance
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class HazelcastConfiguration {
    @Bean
    fun clientConfig(): ClientConfig {
        val clientConfig = ClientConfig()
        val networkConfig = clientConfig.getNetworkConfig()
        networkConfig.addAddress("localhost:5701", "localhost:5702")
                .setSmartRouting(true)
                .addOutboundPortDefinition("34700-34710")
                .setRedoOperation(true)
                .setConnectionTimeout(5000)
                .setConnectionAttemptLimit(5)

        return clientConfig

    }

    @Bean
    fun hazelcastInstance(clientConfig: ClientConfig): HazelcastInstance {
        return HazelcastClient.newHazelcastClient(clientConfig)
    }

}