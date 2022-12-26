package com.example.member.controller

import com.example.member.model.Member
import com.example.member.service.MemberService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/member")
class MemberController {

    @Autowired
    lateinit var memberService: MemberService


    @PostMapping
    fun save(@RequestBody member: Member): Member?{
        return memberService.save(member)

    }

    @PutMapping
    fun update(@RequestBody member:Member): ResponseEntity<Member> {
       return ResponseEntity (memberService.update(member),HttpStatus.OK)
    }

    @PatchMapping
    fun updateName(@RequestBody member:Member): ResponseEntity<Member> {
        return ResponseEntity(memberService.updateName(member), HttpStatus.ACCEPTED)
    }

    @GetMapping
    fun list (member: Member, pageable: Pageable):ResponseEntity<*>{
        val response= memberService.list(pageable,member)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return memberService.delete(id)
    }
    @GetMapping("/{id}")
    fun listById (@PathVariable ("id") id: Long):ResponseEntity<Member>{
        return ResponseEntity(memberService.listById(id), HttpStatus.OK)
    }


}