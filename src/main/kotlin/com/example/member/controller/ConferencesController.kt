package com.example.member.controller

import com.example.member.model.Conferences
import com.example.member.model.Register
import com.example.member.service.ConferencesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/conferences")
class ConferencesController {

    @Autowired
    lateinit var conferencesService: ConferencesService

    @GetMapping
    fun list (conferences: Conferences, pageable: Pageable):ResponseEntity<*>{
        val response= conferencesService.list(pageable,conferences)
        return ResponseEntity(response, HttpStatus.OK)
    }


    @PostMapping
    fun save(@RequestBody conferences: Conferences): Conferences?{
        return conferencesService.save(conferences)

    }

    @PutMapping
    fun update(@RequestBody conferences: Conferences): ResponseEntity<Conferences> {
        return ResponseEntity(conferencesService.update(conferences), HttpStatus.ACCEPTED)
    }

    @PatchMapping
    fun updateTotal(@RequestBody conferences: Conferences): ResponseEntity<Conferences> {
        return ResponseEntity(conferencesService.updateTotal(conferences), HttpStatus.ACCEPTED)
    }
    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return conferencesService.delete(id)
    }
    @GetMapping("/{id}")
    fun listById (@PathVariable("id") id: Long): ResponseEntity<Conferences> {
        return ResponseEntity(conferencesService.listById(id), HttpStatus.OK)
    }

}