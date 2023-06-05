package html;

/**
 * Section of html document.
 */
public enum Section {
    DOCTYPE  ("doctype"),
    HTML  ("html"),
    HEAD  ("head"),
    BODY  ("body");

    private final String section;

    Section(String section) {
        this.section = section;
    }

    public String getName() {
        return this.section;
    }

}
