package hexlet.code;

public final class Node {
    private String key;
    private Object value;
    private String state;
    private Object oldValue;

    Node(String key, Object value, String state, Object oldValue) {
        this.key = key;
        this.value = value;
        this.state = state;
        this.oldValue = oldValue;
    }

    Node(String key, Object value, String state) {
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
