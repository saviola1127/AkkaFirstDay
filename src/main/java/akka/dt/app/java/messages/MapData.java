package akka.dt.app.java.messages;

import java.util.List;

/**
 * Created by savypan
 * On 2018/10/4 20:31
 */
public class MapData {

    private List<WordCount> dataList;

    public MapData(List<WordCount> dataList) {
        this.dataList = dataList;
    }

    public List<WordCount> getDataList() {
        return dataList;
    }

    public void setDataList(List<WordCount> dataList) {
        this.dataList = dataList;
    }
}
