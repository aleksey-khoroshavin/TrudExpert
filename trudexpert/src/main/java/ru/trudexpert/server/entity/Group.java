package ru.trudexpert.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "groups")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", nullable = false)
    private Long number;

    @Column(name = "study_start_date", nullable = false)
    private String studyStartDate;

    @Column(name = "study_end_date", nullable = false)
    private String studyEndDate;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Listener> listeners;

    public void addListener(Listener listener){
        listeners.add(listener);
        listener.setGroup(this);
    }

    public void removeListener(Listener listener){
        listeners.remove(listener);
        listener.setGroup(null);
    }

}
