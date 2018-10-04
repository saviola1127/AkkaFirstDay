package akka.dt.app.java.actors;

import akka.actor.UntypedActor;
import akka.dt.app.java.messages.ReduceData;
import akka.dt.app.java.messages.Result;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by savypan
 * On 2018/10/4 21:50
 */
public class AggregateActor extends UntypedActor {


    private Map<String, Integer> finalReduceMap = new HashMap<String, Integer>();

    public void onReceive(Object message) throws Exception {

        if(message instanceof ReduceData) {
            ReduceData reduceData = (ReduceData) message;
            aggregateInMemoryReduce(reduceData.getReduceDataList());
        } else if (message instanceof Result) {
            System.out.println(finalReduceMap.toString());
        } else {
            unhandled(message);
        }

    }

    private void aggregateInMemoryReduce(HashMap<String,Integer> reduceDataList) {
        //System.out.println(reduceDataList);

        Integer count = null;
        for (String key : reduceDataList.keySet()) {
            if (finalReduceMap.containsKey(key)) {
                count = reduceDataList.get(key) + finalReduceMap.get(key);
                finalReduceMap.put(key, count);
            } else {
                finalReduceMap.put(key, reduceDataList.get(key));
            }
        }
    }
}
