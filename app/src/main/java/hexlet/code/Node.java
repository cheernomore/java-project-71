package hexlet.code;

public class Node {
    String key;
    Object value;
    String state;
    Object oldValue;

    public Node(String key, Object value, String state, Object oldValue) {
        this.key = key;
        this.value = value;
        this.state = state;
        this.oldValue = oldValue;
    }

    public Node(String key, Object value, String state) {
        this.key = key;
        this.value = value;
        this.state = state;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }
}
