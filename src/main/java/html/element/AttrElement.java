package html.element;

import html.BaseElement;
import html.Element;
import html.util.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Element that has attributes with no content.
 *
 * Usages:
 * <meta charset="utf-8">
 * <link rel="stylesheet" type="text/css" href="layout.css">
 * <img src="img.jpg" width="57" height="120" alt="Hope Guy">
 */
public class AttrElement extends BaseElement implements Element {
    private final static String element = "<%s%s>";

    private StringBuilder str;

    public AttrElement(String tag, Object attrs) {
        super(tag);

        str = new StringBuilder();

        if (attrs != null) {
            if (attrs instanceof Map) {
                str.append(Utils.generateStringFromMap((Map) attrs));
            }
        }
    }

    @Override
    public String render() {
        return String.format(element, getTag(), str);
    }
}
