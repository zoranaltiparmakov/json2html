package html;

import html.element.AttrElement;
import html.element.ContentAttrElement;
import html.element.NamedAttrElement;

import java.util.HashMap;
import java.util.Map;

/**
 * HTML Document class.
 */
public class HtmlDoc {
    // Used for indentation type settings. It starts with 4 spaces.
    private static int indent = 4;
    private StringBuilder indentSpaces;

    private StringBuilder html;

    public HtmlDoc() {
        html = new StringBuilder();
    }

    public void appendChild(Element element) {
        html.append(element.render());
    }

    public String getHtml() {
        return html.toString();
    }

    /**
     * Creates new appropriate element, and appends it to the parent element.
     *
     * @param root    parent element
     * @param content {@link Map} object
     */
    public void createElement(Nesting root, Map content) {
        content.forEach((k, v) -> {
            if (v instanceof Map) {
                // If the element is not nested.
                //Element element = new AttrElement((String) k, v);
                //root.append(element);
                createElement(root, (String) k, (Map) v);
            } else {
                Element element;
                element = new ContentAttrElement((String) k, (String) v, null);
                root.append(element);
            }
        });
    }

    /**
     * Creates new appropriate element, and appends it to the parent element.
     *
     * @param root    parent element
     * @param tag     element tag
     * @param content {@link Map} object
     */
    private void createElement(Nesting root, String tag, Map content) {
        content.forEach((k, v) -> {
            Element element;
            if (v instanceof Map) {
                element = new NamedAttrElement(tag, (String) k, (Map) v);
            } else {
                Map m = new HashMap();
                m.put(k, v);
                element = new AttrElement(tag, m);
            }
            root.append(element);
        });
    }

    /**
     * Creates number of spaces predefined in indent constant variable.
     */
    private void indent() {
        indentSpaces = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            indentSpaces.append(" ");
        }
    }
}
