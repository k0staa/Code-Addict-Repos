package pl.codeaddict.camelexample;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Michal Kostewicz on 30.08.19.
 */

@Component
public class QueueSelectRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer://dbQueryTimer?period=10s")
                .routeId("DATABASE_QUERY_TIMER_ROUTE")
                .to("sql:SELECT * FROM event_queue?dataSource=#dataSource")
                .process(xchg -> {
                    List<Map<String, Object>> row = xchg.getIn().getBody(List.class);
                    row.stream()
                            .map((x) -> {
                                EventQueue eventQueue = new EventQueue();
                                eventQueue.setId((Long) x.get("id"));
                                eventQueue.setData((String) x.get("data"));
                                return eventQueue;
                            }).collect(Collectors.toList());
                })
                .log(LoggingLevel.INFO, "******Database query executed - body:${body}******");
    }
}