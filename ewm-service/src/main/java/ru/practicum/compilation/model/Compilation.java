package ru.practicum.compilation.model;

import lombok.*;
import ru.practicum.event.model.Event;

import javax.persistence.*;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@Builder
@Table(name = "compilations")
public class Compilation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Boolean pinned;
    private String title;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "compilations_events",
            joinColumns = @JoinColumn(name = "compilation_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private Set<Event> events;
}