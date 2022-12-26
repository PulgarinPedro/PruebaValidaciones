package com.example.member.service

import com.example.member.model.Event
import com.example.member.model.Member
import com.example.member.repository.EventRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class EventService {
    @Autowired
    lateinit var eventRepository: EventRepository



    fun list (pageable: Pageable, event: Event): Page<Event> {
        val matcher = ExampleMatcher.matching()
            .withIgnoreNullValues()
            .withMatcher(("field"), ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
        return eventRepository.findAll(Example.of(event, matcher), pageable)
    }

    fun save (event: Event):Event{
        return eventRepository.save(event)
    }

    fun update(event: Event):Event{
        try {
            eventRepository.findById(event.id)
                ?: throw Exception("El id ${event.id} en evento no existe")
            return eventRepository.save(event)
        }
        catch(ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun updateTotal(event: Event):Event{
        try{
            val response = eventRepository.findById(event.id)
                ?:throw Exception("El ${event.id} en evento no existe")
            response.apply{
                description = event.description
            }
            return eventRepository.save(response)
        }
        catch (ex:Exception){
            throw  ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)

        }
    }

    fun delete (id: Long?):Boolean?{
        eventRepository.findById(id) ?:
        throw  Exception()
        eventRepository.deleteById(id!!)
        return true
    }
    fun listById (id: Long?): Event {
        return eventRepository.findById(id)
    }


}

