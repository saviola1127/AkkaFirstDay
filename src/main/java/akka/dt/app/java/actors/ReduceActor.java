package akka.dt.app.java.actors;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.dt.app.java.messages.MapData;
import akka.dt.app.java.messages.ReduceData;
import akka.dt.app.java.messages.WordCount;
import scala.util.parsing.combinator.testing.Str;

import java.util.HashMap;
import java.util.List;

/**
 * Created by savypan
 * On 2018/10/4 22:05
 */
public class ReduceActor extends UntypedActor {

    private ActorRef aggregateActor = null;

    public ReduceActor(ActorRef aggregateActor) {
        this.aggregateActor = aggregateActor;
    }

    public void onReceive(Object message) throws Exception {
        if (message instanceof MapData) {
            MapData mapData = (MapData) message;

            ReduceData reduceData = reduce(mapData.getDataList());

            aggregateActor.tell(reduceData);
        } else unhandled(message);
    }

    private ReduceData reduce(List<WordCount> dataList) {
        HashMap<String, Integer> reduceMap = new HashMap<String, Integer>();

        for (WordCount wordCount : dataList) {
            if (reduceMap.containsKey(wordCount.getName())) {
                Integer value = reduceMap.get(wordCount.getName());
                value ++;
                reduceMap.put(wordCount.getName(), value);
            } else {
                reduceMap.put(wordCount.getName(), 1);
            }
        }

        return new ReduceData(reduceMap);
    }
}
