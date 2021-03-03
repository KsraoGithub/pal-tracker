package io.pivotal.pal.tracker;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {
    private final TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {

        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry timeEntryRecord = timeEntryRepository.create(timeEntryToCreate);
        return ResponseEntity.created(null).body(timeEntryRecord);
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        TimeEntry timeEntryRecord = timeEntryRepository.find(id);
        if (timeEntryRecord == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(timeEntryRecord);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntryList = timeEntryRepository.list();
        return ResponseEntity.ok().body(timeEntryList);

    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable long id, @RequestBody TimeEntry timeEntryToUpdate) {
        TimeEntry timeEntryRecord = timeEntryRepository.update(id, timeEntryToUpdate);
        if (timeEntryRecord == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(timeEntryRecord);
    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        timeEntryRepository.delete(id);
        return ResponseEntity.noContent().build();
    }
}
