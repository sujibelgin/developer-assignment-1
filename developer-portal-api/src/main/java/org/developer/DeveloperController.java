package org.developer;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DeveloperController {

    private static Map<String, Developer> DevelopersList = new HashMap<>();
    static
    {
        // creating the initial list to perform the operations
        Developer emp1 = new Developer();
        emp1.setId("1");
        emp1.setName("Developer1");
        emp1.setSkills("Java, SpringBoot");
        DevelopersList.put(emp1.getId(), emp1);

        Developer emp2 = new Developer();
        emp2.setId("2");
        emp2.setName("Developer2");
        emp2.setSkills("Java, SpringBoot,AWS");
        DevelopersList.put(emp2.getId(), emp2);
    }

    @RequestMapping(value = "/developers")
    public ResponseEntity<Object> getDevelopers() {
        return new ResponseEntity<>(DevelopersList.values(), HttpStatus.OK);
    }

    @RequestMapping(value = "/developers/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteDeveloper(@PathVariable("id") String id) {
        DevelopersList.remove(id);
        return new ResponseEntity<>("Developer is deleted successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/developers/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateDeveloper(@PathVariable("id") String id, @RequestBody Developer developer) {
        DevelopersList.remove(id); //deleting
        developer.setId(id);
        DevelopersList.put(id, developer); // adding
        return new ResponseEntity<>("Developers is updated successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/developers", method = RequestMethod.POST)
    public ResponseEntity<Object> createDeveloper(@RequestBody Developer developer) {
        DevelopersList.put(developer.getId(), developer);
        return new ResponseEntity<>("Developer is created successfully", HttpStatus.CREATED);
    }
}

