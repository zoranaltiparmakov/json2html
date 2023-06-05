package html;

/**
 * Doctype element used to define document type of the document.
 */
public class DoctypeElement implements Element {
    private final static String element = "<!doctype %s>\n";

    private String content;

    public DoctypeElement(Object content) {
        this.content = (String) content;
    }

    @Override
    public String render() {
        return String.format(element, content);
    }
}
