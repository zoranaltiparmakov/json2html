package html;

/**
 * Base element shares the same attributes with other elements.
 */
public class BaseElement {

    private String tag;

    public BaseElement(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
