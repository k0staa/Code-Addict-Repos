package pl.codeaddict.demohazelcast.cache

import com.hazelcast.config.Config
import com.hazelcast.config.EvictionPolicy
import com.hazelcast.config.MaxSizeConfig
import com.hazelcast.config.MapConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class HazelcastConfiguration {
    @Bean
    fun hazelCastConfig(): Config {
        val config = Config()
        config
                .addMapConfig(
                        MapConfig()
                                .setName("configuration")
                                .setMaxSizeConfig(MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                                .setEvictionPolicy(EvictionPolicy.LRU)
                                .setTimeToLiveSeconds(-1))
        return config
    }
}