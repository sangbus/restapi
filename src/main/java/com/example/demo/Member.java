package com.example.demo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String promotion_day;
    private String bank_info;
    public Member(String mname,
                 String phone,
                 String grade,
                 String address,
                 String promotion_day,
                 String bank_info) {
        this.mname = mname;
        this.phone = phone;
        this.grade = grade;
        this.address = address;
        this.promotion_day = promotion_day;
        this.bank_info = bank_info;
    }
}
