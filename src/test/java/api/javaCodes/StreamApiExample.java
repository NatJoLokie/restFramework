package api.javaCodes;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Optional;

public class StreamApiExample {

    public static void main(String[] args) {
        String jsonPayload = "{\n" +
                "  \"store\":{\n" +
                "    \"book\":[\n" +
                "      {\n" +
                "        \"author\":\"Nigel Rees\",\n" +
                "        \"category\":\"reference\",\n" +
                "        \"price\":8.95,\n" +
                "        \"title\":\"Sayings of the Century\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"author\":\"Evelyn Waugh\",\n" +
                "        \"category\":\"fiction\",\n" +
                "        \"price\":12.99,\n" +
                "        \"title\":\"Sword of Honour\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"author\":\"Herman Melville\",\n" +
                "        \"category\":\"fiction\",\n" +
                "        \"isbn\":\"0-553-21311-3\",\n" +
                "        \"price\":8.99,\n" +
                "        \"title\":\"Moby Dick\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"author\":\"J. R. R. Tolkien\",\n" +
                "        \"category\":\"fiction\",\n" +
                "        \"isbn\":\"0-395-19395-8\",\n" +
                "        \"price\":22.99,\n" +
                "        \"title\":\"The Lord of the Rings\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}"; // Replace with your JSON string
        String titleToFind = "Sword of Honour"; // Replace with the title you're looking for
        Optional<String> author = getAuthorByTitle(jsonPayload, titleToFind);

        author.ifPresentOrElse(
                a -> System.out.println("Author: " + a),
                () -> System.out.println("Book not found.")
        );
    }

    public static Optional<String> getAuthorByTitle(String jsonPayload, String title) {
        JSONObject obj = new JSONObject(jsonPayload);
        JSONArray books = obj.getJSONObject("store").getJSONArray("book");

        return books.toList().stream()
                .map(book -> new JSONObject((java.util.HashMap)book))
                .filter(book -> title.equals(book.getString("title")))
                .findFirst()
                .map(book -> book.getString("author"));
    }
}

