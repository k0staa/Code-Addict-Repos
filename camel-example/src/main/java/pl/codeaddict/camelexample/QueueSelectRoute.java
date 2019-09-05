package pl.codeaddict.camelexample;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Michal Kostewicz on 30.08.19.
 */

@Component
public class QueueSelectRoute extends RouteBuilder {
    @Autowired
    DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void configure() throws Exception {
        from("timer://dbQueryTimer?period=10s")
                .routeId("DATABASE_QUERY_TIMER_ROUTE")
                .to("sql:SELECT * FROM allegro_queue_parcels?dataSource=#dataSource")
                .process(xchg -> {
                    List<Map<String, Object>> row = xchg.getIn().getBody(List.class);
                    row.stream()
                            .map((x) -> {
                                EventQueue eventQueue = new EventQueue();
                                eventQueue.setId((int)x.get("id"));
                                eventQueue.setId((int)x.get("data"));
                                return eventQueue;
                            }).collect(Collectors.toList());
                })
                .log(LoggingLevel.INFO,"******Database query executed - body:${body}******");

    }

}