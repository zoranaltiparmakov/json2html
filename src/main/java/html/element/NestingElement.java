package html.element;

import html.BaseElement;
import html.Element;
import html.Nesting;
import html.util.Utils;

import java.util.Map;

/**
 * Element that can contain other elements.
 */
public class NestingElement extends BaseElement implements Nesting {
    private final static String element = "<%s%s>\n%s</%s>";

    private StringBuilder nestedContent;

    private StringBuilder content;

    public NestingElement(String tag, Object attrs) {
        super(tag);

        nestedContent = new StringBuilder();
        this.content = new StringBuilder();

        if (attrs != null) {
            this.content.append(Utils.generateStringFromMap((Map) attrs));
            //else if (attrs instanceof List) {
            //    ((List<Map>) attrs).forEach(item -> this.content.append(Utils.generateStringFromMap(item)));
            //}
        }
    }

    public NestingElement(String tag) {
        this(tag, null);
    }

    @Override
    public String render() {
        return String.format(element, getTag(), content, nestedContent, getTag());
    }

    @Override
    public void append(Element element) {
        nestedContent.append(element.render()).append("\n");
    }
}
