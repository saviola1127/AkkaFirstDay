package akka.dt.app.java.actors;

import akka.actor.*;
import akka.dt.app.java.messages.Result;

/**
 * Created by savypan
 * On 2018/10/4 21:54
 */
public class MasterActor extends UntypedActor {

    private ActorRef aggregateActor = getContext().actorOf(
            new Props(AggregateActor.class), "aggregate");

    private ActorRef reduceActor = getContext().actorOf(
            new Props(new UntypedActorFactory() {
                public Actor create() {
                    return new ReduceActor(aggregateActor);
                }
            }), "reduce"
    );

    private ActorRef mapActor = getContext().actorOf(
            new Props(new UntypedActorFactory() {
                public Actor create() {
                    return new MapActor(reduceActor);
                }
            }), "map"
    );

    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            mapActor.tell(message);
        } else if (message instanceof Result) {
            aggregateActor.tell(message);
        } else {
            unhandled(message);
        }
    }
}
