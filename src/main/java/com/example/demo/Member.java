package com.example.demo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="members")
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idx")
    private Long idx;
    private String mname;
    private String phone;
    private String grade;
    private String address;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDateTime promotion_day;
    private String bank_info;
    public Member(String mname,
                 String phone,
                 String grade,
                 String address,
                 LocalDateTime promotion_day,
                 String bank_info) {
        this.mname = mname;
        this.phone = phone;
        this.grade = grade;
        this.address = address;
        this.promotion_day = promotion_day;
        this.bank_info = bank_info;
    }
}