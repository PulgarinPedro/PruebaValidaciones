package com.example.member.controller

import com.example.member.model.Member
import com.example.member.model.Register
import com.example.member.service.RegisterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/register")
class
RegisterController {

    @Autowired
    lateinit var registerService: RegisterService

    @GetMapping
    fun list (register: Register, pageable: Pageable):ResponseEntity<*>{
        val response= registerService.list(pageable,register)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PostMapping
    fun save(@RequestBody register: Register): Register?{
        return registerService.save(register)

    }

    @PutMapping
    fun update(@RequestBody register: Register): ResponseEntity<Register> {
        return ResponseEntity(registerService.update(register), HttpStatus.ACCEPTED)
    }

    @PatchMapping
    fun updateTotal(@RequestBody register: Register): ResponseEntity<Register> {
        return ResponseEntity(registerService.updateTotal(register), HttpStatus.ACCEPTED)
    }

    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return registerService.delete(id)
    }
    @GetMapping("/{id}")
    fun listById (@PathVariable ("id") id: Long):ResponseEntity<Register>{
        return ResponseEntity(registerService.listById(id), HttpStatus.OK)
    }



}
