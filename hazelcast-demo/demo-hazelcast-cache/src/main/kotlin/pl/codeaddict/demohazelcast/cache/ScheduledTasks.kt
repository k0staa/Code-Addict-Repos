package pl.codeaddict.demohazelcast.cache

import com.hazelcast.core.HazelcastInstance
import com.hazelcast.core.IMap
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class ScheduledTasks {
    val key = "MY_VALUE"

    @Autowired
    private val hazelcastInstance: HazelcastInstance? = null

    @Scheduled(fixedRate = 1000)
    fun changeHazelcastMap() {
        val hazelcastMap: IMap<String, Int> = hazelcastInstance?.getMap("my-map")!!
        if(!hazelcastMap.containsKey(key)){
            hazelcastMap.put(key,0)
        }else{
            var previousInteger: Int = hazelcastMap.get(key)!!
            val nextInteger = ++previousInteger
            hazelcastMap.put(key, nextInteger)
        }
    }
}
