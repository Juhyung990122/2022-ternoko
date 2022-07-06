package com.woowacourse.ternoko.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "interview_id")
    private Long id;

    @Column(nullable = false)
    private LocalDate reservationDate;

    @Column(nullable = false)
    private LocalTime reservationStartTime;

    @Column(nullable = false)
    private LocalTime reservationEndTime;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member coach;

    @Column(nullable = false)
    private String crewNickname;

    @Enumerated(EnumType.STRING)
    private Location location;

    @OneToMany
    @JoinColumn(name = "formItem_id")
    private List<FormItem> Items = new ArrayList<>();

    public Interview(LocalDate reservationDate,
                     LocalTime reservationStartTime,
                     LocalTime reservationEndTime,
                     Member coach,
                     String crewNickname,
                     Location location,
                     List<FormItem> items) {
        this.reservationDate = reservationDate;
        this.reservationStartTime = reservationStartTime;
        this.reservationEndTime = reservationEndTime;
        this.coach = coach;
        this.crewNickname = crewNickname;
        this.location = location;
        Items = items;
    }
}
