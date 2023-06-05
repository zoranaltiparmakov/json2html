import java.io.File;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Main class used to test Json2Html parser.
 *
 * @author Zoran Altiparmakov
 */
class HtmlGenerator {
    private static final Logger LOG = Logger.getLogger(HtmlGenerator.class.getName());

    private static final String INPUT_FILENAME = "input.json";

    public static void main(String[] args) {
        File inputFile = new File(INPUT_FILENAME);

        Json2Html json2Html = new Json2Html();

        Map jsonObject = json2Html.readJsonFile(inputFile);

        try {
            json2Html.generateHtml(jsonObject);
        } catch (ClassCastException c) {
            LOG.severe("Exception while casting: " + c.getMessage());
        }
    }
}
