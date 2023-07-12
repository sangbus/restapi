package com.example.demo;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/member")
public class MemberController {
    private MemberRepository MemberRep;
    Message message = new Message();
    HttpHeaders headers= new HttpHeaders();

    @Autowired
    public MemberController(MemberRepository MemberRep) {
        this.MemberRep = MemberRep;
    }

    //POST로 유저 추가
    @PostMapping("/post")
    public Object put(@RequestBody HashMap<String, String> member) {
        MemberRep.save(new Member(String.valueOf(member.get("mname")),
                String.valueOf(member.get("phone")),
                String.valueOf(member.get("grade")),
                String.valueOf(member.get("address")),
                String.valueOf(member.get("promotion_day")),
                String.valueOf(member.get("bank_info"))));
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        message.setStatus(StatusEnum.OK);
        message.setMessage("회원가입 성공");
        message.setData(member.get("mname"));
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }

    //테이블 리스트 가져오기
    @GetMapping
    public Object list(){
        MemberRep.findAll();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        message.setStatus(StatusEnum.OK);
        message.setMessage("회원 조회 성공");
        message.setData(MemberRep.count());
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }

    //id로 테이블 값 가져오기
    @GetMapping(value = "/{id}")
    public Optional<Member> findOne(@PathVariable Long idx) {
        return MemberRep.findById(idx);
    }

    //id로 테이블 값 수정
    @PutMapping(value = "/{id}")
    public Member update(@PathVariable Long idx,
                        @RequestBody String mname,
                        @RequestBody String phone,
                        @RequestBody String grade,
                        @RequestBody String address,
                        @RequestBody String promotion_day,
                        @RequestBody String bank_info) {
        Optional<Member> Member = MemberRep.findById(idx);
        Member.get().setMname(mname);
        Member.get().setPhone(phone);
        Member.get().setGrade(grade);
        Member.get().setAddress(address);
        Member.get().setPromotion_day(promotion_day);
        Member.get().setBank_info(bank_info);
        return MemberRep.save(Member.get());
    }

    //id로 테이블 값 삭제
    @DeleteMapping
    public void delete(@RequestBody Long idx) {
        MemberRep.deleteById(idx);
    }

}