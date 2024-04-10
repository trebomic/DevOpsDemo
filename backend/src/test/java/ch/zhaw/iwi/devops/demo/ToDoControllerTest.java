package ch.zhaw.iwi.devops.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        controller.init(); // Initialisiert die ToDo-Liste mit Standardwerten.

        int initialCount = controller.count(); // Speichert die Anfangsgröße der ToDo-Liste.

        controller.deleteTodo(1); // Löscht das ToDo-Element mit der ID 1.

        assertEquals(initialCount - 1, controller.count()); // Überprüft, ob die Größe der Liste um eins verringert wurde.
        assertNull(controller.getTodo(1)); // Stellt sicher, dass das ToDo-Element mit der ID 1 nicht mehr existiert.
    }

}
