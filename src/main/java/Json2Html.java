import com.fasterxml.jackson.databind.ObjectMapper;
import html.DoctypeElement;
import html.Element;
import html.HtmlDoc;
import html.Nesting;
import html.Section;
import html.element.NestingElement;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;

/**
 * Json2Html class contains methods to parse JSON file into Map and transforms Map into HTML code.
 *
 * @author Zoran Altiparmakov
 */
class Json2Html {
    private static final Logger LOG = Logger.getLogger(Json2Html.class.getName());

    // Output file name.
    private static final String OUTPUT_FILENAME = "output.html";

    private HtmlDoc htmlDoc = new HtmlDoc();

    /**
     * Reads JSON file which has HTML tags using Jackson library, and transforms it into {@link Map}.
     *
     * @param inputFile JSON file
     * @return {@link Map} object
     */
    Map readJsonFile(File inputFile) {
        ObjectMapper mapper = new ObjectMapper();
        Map map = null;
        try {
            map = mapper.readValue(inputFile, Map.class);
        } catch (IOException e) {
            LOG.severe("File " + inputFile.getAbsolutePath() + " can not be mapped.");
        }

        return map;
    }

    /**
     * Transforms and writes map object into HTML code.
     *
     * @param map {@link LinkedHashMap} object
     * @throws ClassCastException when casting can not be done
     */
    @SuppressWarnings("unchecked")
    void generateHtml(Map map) {
        Element doctype = new DoctypeElement(map.get(Section.DOCTYPE.getName()));
        htmlDoc.appendChild(doctype);

        Map lang = createMap(map, "language", "lang");
        Nesting html = new NestingElement(Section.HTML.getName(), lang);

        // Head section.
        Nesting head = new NestingElement(Section.HEAD.getName());
        htmlDoc.createElement(head, (Map) map.get(Section.HEAD.getName()));
        html.append(head);

        // Body section.
        Nesting body = new NestingElement(Section.BODY.getName());
        htmlDoc.createElement(body, (Map) map.get(Section.BODY.getName()));
        html.append(body);

        htmlDoc.appendChild(html);

        // Write to console for debugging.
        System.out.println(htmlDoc.getHtml());

        // Write to file.
        writeToFile(htmlDoc.getHtml());
    }

    /**
     * Creates new map with given attr as a new key, if the given key already exists in the map.
     *
     * @param m    original map
     * @param key  existing key in the map
     * @param attr new key
     * @return {@link Map}
     */
    public Map createMap(Map m, String key, String attr) {
        Map map = null;
        Object value = m.get(key);
        if (value != null) {
            map = new HashMap();
            map.put(attr, value);
        }

        return map;
    }

    /**
     * Writes given string into file.
     *
     * @param html HTML document in string
     */
    private void writeToFile(String html) {
        try (Writer fos = new FileWriter(OUTPUT_FILENAME)) {
            fos.write(html);
        } catch (FileNotFoundException e) {
            LOG.severe("File " + OUTPUT_FILENAME + " not found.");
        } catch (IOException e) {
            LOG.severe("Error while writing to file: " + e.getCause());
        }
    }
}
