package pl.codeaddict.camelexample;

import lombok.Data;

/**
 * Created by Michal Kostewicz on 30.08.19.
 */
@Data
public class EventQueue {
    private Long id;
    private String data;

    @Override
    public String toString() {
        return String.format(
                "EventQueue[id=%d, data=%d]",
                id, data);
    }
}
