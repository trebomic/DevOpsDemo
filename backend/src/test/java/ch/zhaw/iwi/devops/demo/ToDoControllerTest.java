package ch.zhaw.iwi.devops.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

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
    public void testDelete() {
        ToDoController controller = new ToDoController();
        var todo = new ToDo(2, "Beispiel", "Beschreibung");
        controller.createTodo(2, todo);
        assertEquals(1, controller.count()); // Stellen Sie sicher, dass das Todo hinzugefügt wurde

        controller.deleteTodo(2);
        assertEquals(0, controller.count()); // Überprüfen, ob das Todo gelöscht wurde
        assertNull(controller.getTodo(2)); // Stellen Sie sicher, dass das Todo nicht mehr abgerufen werden kann
    }
}