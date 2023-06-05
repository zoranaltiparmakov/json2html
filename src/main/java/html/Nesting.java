package html;

/**
 * This interface should be implemented by elements that can nest other elements.
 */
public interface Nesting extends Element {
    void append(Element element);
}
