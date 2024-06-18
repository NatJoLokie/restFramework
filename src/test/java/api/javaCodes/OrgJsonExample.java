package api.javaCodes;
import org.json.JSONArray;
import org.json.JSONObject;

public class OrgJsonExample {

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
            String titleToFind = "The Lord of the Rings"; // Replace with the title you're looking for
            String author = getAuthorByTitle(jsonPayload, titleToFind);

            if (author != null) {
                System.out.println("Author: " + author);
            } else {
                System.out.println("Book not found.");
            }
        }

        public static String getAuthorByTitle(String jsonPayload, String title) {
            JSONObject obj = new JSONObject(jsonPayload);
            JSONArray books = obj.getJSONObject("store").getJSONArray("book");

            for (int i = 0; i < books.length(); i++) {
                JSONObject book = books.getJSONObject(i);
                if (title.equals(book.getString("title"))) {
                    return book.getString("author");
                }
            }

            return null; // Book not found
        }



}
