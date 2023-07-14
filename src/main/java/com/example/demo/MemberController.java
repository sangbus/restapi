package com.example.demo;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/member")
public class MemberController {
    private MemberRepository MemberRep;
    Message message = new Message();
    HttpHeaders headers= new HttpHeaders();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
    @Autowired
    public MemberController(MemberRepository MemberRep) {
        this.MemberRep = MemberRep;
    }

    //POST로 유저 추가
    @PostMapping("/post")
    public Object put(@Validated @RequestBody HashMap<String, String> member, BindingResult bindingResult) {
        log.info("회원 등록 API 컨트롤러 호출");
        if(bindingResult.hasErrors()){
            log.info("검증 오류 발생 errors={}",bindingResult);
            return bindingResult.getAllErrors();
        }
        MemberRep.save(new Member(String.valueOf(member.get("mname")),
                String.valueOf(member.get("phone")),
                String.valueOf(member.get("grade")),
                String.valueOf(member.get("address")),
                LocalDateTime.parse(member.get("promotion_day"),formatter),
                String.valueOf(member.get("bank_info"))));
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        message.setStatus(StatusEnum.OK);
        message.setMessage("회원가입 성공");
        message.setData(member.get("mname"));
        log.info("회원가입 성공 로직 실행");
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }

    //테이블 리스트 가져오기
    @GetMapping
    public Object list(){
        MemberRep.findAll();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        message.setStatus(StatusEnum.OK);
        message.setMessage("전체 회원 조회 성공");
        message.setData(MemberRep.count());
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }

    //id로 테이블 값 가져오기
    @GetMapping(value = "/{id}")
    public Object findOne(@Valid @PathVariable Long idx) {
        MemberRep.findById(idx);
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        message.setStatus(StatusEnum.OK);
        message.setMessage("회원 조회 성공");
        message.setData(MemberRep.count());
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }

    //id로 테이블 값 수정
    @PutMapping(value = "/{id}")
    public Object update(@Valid
                        @PathVariable Long idx,
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
        Member.get().setPromotion_day(LocalDateTime.parse(promotion_day,formatter));
        Member.get().setBank_info(bank_info);
        MemberRep.save(Member.get());
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        message.setStatus(StatusEnum.OK);
        message.setMessage("회원 수정 성공");
        message.setData(Member.get().getMname());
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }

    //id로 테이블 값 삭제
    @DeleteMapping
    public Object delete(@RequestBody Long idx) {
        MemberRep.deleteById(idx);
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        message.setStatus(StatusEnum.OK);
        message.setMessage("회원 삭제 성공");
        message.setData(MemberRep.count());
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }

}