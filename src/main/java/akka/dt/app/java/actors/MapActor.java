package akka.dt.app.java.actors;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.dt.app.java.messages.MapData;
import akka.dt.app.java.messages.WordCount;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by savypan
 * On 2018/10/4 22:09
 */
public class MapActor extends UntypedActor {

    private ActorRef reduceActor = null;

    String[] STOP_WORDS = {".", "is", "a"};

    private List<String> STOP_WORDS_LIST = Arrays.asList(STOP_WORDS);

    public MapActor(ActorRef inReduceActor) {
        reduceActor = inReduceActor;
    }

    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            String work = (String) message;

            MapData mapData = evaluateExpression(work);
            reduceActor.tell(mapData);
        } else {
            unhandled(message);
        }
    }

    private MapData evaluateExpression(String line) {

        List<WordCount> dataList = new ArrayList<WordCount>();
        StringTokenizer parser = new StringTokenizer(line);
        while (parser.hasMoreTokens()) {
            String word = parser.nextToken().toLowerCase();

            if (!STOP_WORDS_LIST.contains(word)) {
                dataList.add(new WordCount(word, Integer.valueOf(1)));
            }
        }

        return new MapData(dataList);
    }
}
