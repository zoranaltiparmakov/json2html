package html.element;

import html.BaseElement;
import html.Element;
import html.util.Utils;

import java.util.Map;

/**
 * Element that has named attributes with no content;
 *
 * Usages:
 * <meta name="viewport" content="width=device-width, initial-scale=1.0">
 */
public class NamedAttrElement extends BaseElement implements Element {
    private final static String element = "<%s name=\"%s\" content=\"%s\">";

    private String attrName;

    private String values;

    public NamedAttrElement(String tag, String attrName, Map values) {
        super(tag);
        this.attrName = attrName;
        this.values = Utils.generateStringFromMap(values, true);
    }

    @Override
    public String render() {
        return String.format(element, getTag(), attrName, values);
    }
}
