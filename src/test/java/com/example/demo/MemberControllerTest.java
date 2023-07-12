package com.example.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberControllerTest {

    @Test
    @DisplayName("제대로 되는가")
    void update() {
        /**
        * given
        * */
        Member member = new Member("박",
                "010",
                "1등급",
                "서울",
                "2022-01-05",
                "국민");

        /**
         * when
         * */
        member.setMname("김");
        member.setPhone("011");
        member.setAddress("부산");
        member.setGrade("2등급");
        member.setPromotion_day("2023-22-22");
        member.setBank_info("우리");

        /**
         * then
         * */
        assertThat(member.getMname()).isEqualTo("김");
    }
}