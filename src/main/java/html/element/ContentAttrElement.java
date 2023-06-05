package html.element;

import html.BaseElement;
import html.Element;
import html.util.Utils;

import java.util.Map;

/**
 * Element that can have content, nested element(s), and/or attributes.
 *
 * Usages:
 * <a href="#">Link</a>
 * <title>X</title>
 * <div></div>
 * <p style="color: 'red'">This is code.</p>
 */
public class ContentAttrElement extends BaseElement implements Element {
    private final static String element = "<%s%s>%s</%s>";

    private String content;

    private String attrs;

    public ContentAttrElement(String tag, String content, Map attrs) {
        super(tag);
        this.content = content;
        this.attrs = "";

        if (attrs != null) {
            this.attrs = Utils.generateStringFromMap(attrs);
        }
    }

    @Override
    public String render() {
        return String.format(element, getTag(), attrs, content, getTag());
    }
}
