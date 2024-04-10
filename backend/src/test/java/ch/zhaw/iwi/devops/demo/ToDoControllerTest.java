package ch.zhaw.iwi.devops.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import java.util.List;

public class ToDoControllerTest {

    @Test
    public void testCreate() {
        ToDoController controller = new ToDoController();
        var todo = new ToDo(1, "t", "d");
        controller.createTodo(1, todo);
        assertEquals(1, controller.count());
        assertEquals(1, controller.todo().size());
    }

    @Test
    public void testSearchTodosByTitle() {
        // Initialisierung
        ToDoController controller = new ToDoController();
        controller.createTodo(new ToDo(0, "Test Todo", "Beschreibung"));
        controller.createTodo(new ToDo(0, "Ein weiteres Todo", "Mit anderer Beschreibung"));
        controller.createTodo(new ToDo(0, "Relevante Suche", "Suche nach diesem"));

        // Suche nach einem Titel, der in einem ToDo vorhanden ist
        List<ToDo> results = controller.searchTodosByTitle("Relevante");
        assertEquals(1, results.size(), "Sollte genau ein ToDo finden.");
        assertEquals("Relevante Suche", results.get(0).getTitle(), "Der Titel des gefundenen ToDo stimmt nicht überein.");

        // Suche mit Gross- und Kleinschreibung
        results = controller.searchTodosByTitle("relevante");
        assertEquals(1, results.size(), "Sollte genau ein ToDo finden, unabhängig von der Gross-/Kleinschreibung.");

        // Suche, die keine Ergebnisse liefern sollte
        results = controller.searchTodosByTitle("Nicht existent");
        assertTrue(results.isEmpty(), "Sollte keine ToDos finden, da der Suchbegriff nicht vorhanden ist.");
    }

    @Test
    public void testTestEndpoint() {
        // Initialisierung
        ToDoController controller = new ToDoController();

        // Aufruf der zu testenden Methode
        String response = controller.test();

        // Überprüfung der Antwort
        assertEquals("ToDo app is up and running!", response, "Die Antwort des /test Endpunkts ist nicht wie erwartet.");
    }

}
