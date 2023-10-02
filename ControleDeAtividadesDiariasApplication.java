package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ControleDeAtividadesDiariasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControleDeAtividadesDiariasApplication.class, args);
	}

}import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
public class DailyActivityApplication {

    public static void main(String[] args) {
        SpringApplication.run(DailyActivityApplication.class, args);
    }
}

@RestController
@RequestMapping("/api/activities")
class DailyActivityController {

    private final List<DailyActivity> activities = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong();

    @GetMapping("/{id}")
    public DailyActivity getActivityById(@PathVariable Long id) {
        return activities.stream()
                .filter(activity -> activity.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @GetMapping
    public List<DailyActivity> getAllActivities() {
        return activities;
    }

    @PostMapping
    public DailyActivity createActivity(@RequestBody DailyActivity activity) {
        activity.setId(idCounter.incrementAndGet());
        activities.add(activity);
        return activity;
    }

    @PutMapping("/{id}")
    public DailyActivity updateActivity(@PathVariable Long id, @RequestBody DailyActivity updatedActivity) {
        for (int i = 0; i < activities.size(); i++) {
            DailyActivity activity = activities.get(i);
            if (activity.getId().equals(id)) {
                updatedActivity.setId(id);
                activities.set(i, updatedActivity);
                return updatedActivity;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public boolean deleteActivity(@PathVariable Long id) {
        return activities.removeIf(activity -> activity.getId().equals(id));
    }
}

class DailyActivity {
    private Long id;
    private String name;
    private LocalDate date;

    // Construtores, getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

