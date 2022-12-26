package com.example.member.controller


import com.example.member.model.Event
import com.example.member.model.Member
import com.example.member.service.EventService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/event")
class EventController {

    @Autowired
    lateinit var eventService: EventService

    @GetMapping
    fun list (event: Event, pageable: Pageable):ResponseEntity<*>{
        val response= eventService.list(pageable,event
        )
        return ResponseEntity(response, HttpStatus.OK)
    }


    @PostMapping
    fun save(@RequestBody event: Event): Event?{
        return eventService.save(event)

    }

    @PutMapping
    fun update(@RequestBody event: Event): ResponseEntity<Event> {
        return ResponseEntity(eventService.update(event), HttpStatus.ACCEPTED)
    }

    @PatchMapping
    fun updateTotal(@RequestBody event: Event): ResponseEntity<Event> {
        return ResponseEntity(eventService.updateTotal(event), HttpStatus.ACCEPTED)
    }
    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return eventService.delete(id)
    }
    @GetMapping("/{id}")
    fun listById (@PathVariable("id") id: Long):ResponseEntity<Event>{
        return ResponseEntity(eventService.listById(id), HttpStatus.OK)
    }

}