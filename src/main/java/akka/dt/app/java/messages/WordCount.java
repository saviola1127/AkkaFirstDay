package akka.dt.app.java.messages;

/**
 * Created by savypan
 * On 2018/10/4 20:31
 */
public class WordCount {

    private String name;
    private Integer count;

    public WordCount(String name, Integer count) {
        this.count = count;
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
