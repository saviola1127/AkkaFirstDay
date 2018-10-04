package akka.dt.app.java.messages;

import java.util.HashMap;

/**
 * Created by savypan
 * On 2018/10/4 20:31
 */
public class ReduceData {

    private HashMap<String, Integer> reduceDataList;

    public ReduceData(HashMap<String, Integer> reduceDataList) {
        this.reduceDataList = reduceDataList;
    }

    public HashMap<String, Integer> getReduceDataList() {
        return reduceDataList;
    }

    public void setReduceDataList(HashMap<String, Integer> reduceDataList) {
        this.reduceDataList = reduceDataList;
    }
}
