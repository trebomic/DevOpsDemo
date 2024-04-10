package ch.zhaw.iwi.devops.demo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class ToDoController {

    private Map<Integer, ToDo> todos = new HashMap<>();

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        todos.put(1, new ToDo(1, "Neuer Job", "5 DevOps Engineers einstellen"));
        todos.put(2, new ToDo(2, "Geschäftsleitung", "Mit Präsentation von DevOps überzeugen"));
        todos.put(3, new ToDo(3, "Unit Tests", "Neues Projekt mit Unit Tests starten"));
        todos.put(4, new ToDo(4, "Deployment", "Jede Woche!"));
        todos.put(5, new ToDo(5, "Organigramm", "Löschen"));
        System.out.println("Init Data");
    }

    @GetMapping("/test")
    public String test() {
        return "ToDo app is up and running!";
    }

    @GetMapping("/services/ping")
    public String ping() {
        String languageCode = "de";
        return String.format("{ \"status\": \"ok\", \"userId\": \"admin\", \"languageCode\": \"%s\",\"version\": \"0.0.1\"}", languageCode);
    }

    @GetMapping("/count")
    public int count() {
        return todos.size();
    }

    @GetMapping("/services/todo")
    public List<PathListEntry<Integer>> todo() {
        List<PathListEntry<Integer>> result = new ArrayList<>();
        todos.values().forEach(todo -> {
            var entry = new PathListEntry<Integer>();
            entry.setKey(todo.getId(), "todoKey");
            entry.setName(todo.getTitle());
            entry.getDetails().add(todo.getDescription());
            entry.setTooltip(todo.getDescription());
            result.add(entry);
        });
        return result;
    }

    @GetMapping("/services/todo/{key}")
    public ToDo getTodo(@PathVariable Integer key) {
        return todos.get(key);
    }

    @PostMapping("/services/todo")
    public void createTodo(@RequestBody ToDo todo) {
        int newId = todos.keySet().stream().max(Comparator.naturalOrder()).orElse(0) + 1;
        todo.setId(newId);
        todos.put(newId, todo);
    }

    @PutMapping("/services/todo/{id}")
    public void createTodo(@PathVariable Integer id, @RequestBody ToDo todo) {
        todo.setId(id);
        todos.put(id, todo);
    }

    @DeleteMapping("/services/todo/{key}")
    public ToDo deleteTodo(@PathVariable Integer key) {
        return todos.remove(key);
    }

    @GetMapping("/services/todo/search")
    public List<ToDo> searchTodosByTitle(@RequestParam String title) {
        return todos.values().stream()
                .filter(todo -> todo.getTitle().toLowerCase().contains(title.toLowerCase()))
                .toList();
    }
}
