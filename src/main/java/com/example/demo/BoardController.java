package com.example.demo;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/board")
public class BoardController {
    private BoardRepository boardRep;

    @Autowired
    public BoardController(BoardRepository boardRep) {
        this.boardRep = boardRep;
    }

    //POST로 유저 추가
    @PostMapping
    public Board put(@RequestParam String mname, @RequestParam String phone, @RequestParam String grade, @RequestParam String address, @RequestParam String promotion_day, @RequestParam String bank_info) {
        return boardRep.save(new Board(mname, phone, grade, address, promotion_day, bank_info));
    }

    //테이블 리스트 가져오기
    @GetMapping
    public Iterable<Board> list(){
        return boardRep.findAll();
    }

    //id로 테이블 값 가져오기
    @GetMapping(value = "/{id}")
    public Optional<Board> findOne(@PathVariable Long idx) {
        return boardRep.findById(idx);
    }

    //id로 테이블 값 수정
    @PutMapping(value = "/{id}")
    public Board update(@PathVariable Long idx, @RequestParam String mname, @RequestParam String phone, @RequestParam String grade, @RequestParam String address, @RequestParam String promotion_day, @RequestParam String bank_info) {
        Optional<Board> board = boardRep.findById(idx);
        board.get().setMname(mname);
        board.get().setPhone(phone);
        return boardRep.save(board.get());
    }

    //id로 테이블 값 삭제
    @DeleteMapping
    public void delete(@RequestParam Long idx) {
        boardRep.deleteById(idx);
    }
}