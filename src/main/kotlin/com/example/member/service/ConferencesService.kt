package com.example.member.service

import com.example.member.model.Conferences
import com.example.member.model.Member
import com.example.member.model.Register
import com.example.member.repository.ConferencesRepository
import com.example.member.repository.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException



@Service
class ConferencesService {
    @Autowired
    lateinit var conferencesRepository: ConferencesRepository
    
    @Autowired
    lateinit var memberRepository: MemberRepository


    fun save (conferences: Conferences):Conferences{
        try{
            memberRepository.findById(conferences.eventId)
                ?:throw Exception("El id ${conferences.eventId} de member no existe")
            return conferencesRepository.save(conferences)
        }catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }

    }

    fun update(conferences: Conferences):Conferences{
        try {
            conferencesRepository.findById(conferences.id)
                ?: throw Exception("El id ${conferences.id} en factura no existe")
            return conferencesRepository.save(conferences)
        }
        catch(ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }
    fun list (pageable: Pageable, conferences: Conferences): Page<Conferences> {
        val matcher = ExampleMatcher.matching()
            .withIgnoreNullValues()
            .withMatcher(("field"), ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
        return conferencesRepository.findAll(Example.of(conferences, matcher), pageable)
    }

    fun updateTotal(conferences: Conferences):Conferences{
        try{
            val response = conferencesRepository.findById(conferences.id)
                ?:throw Exception("El ${conferences.id} en factura no existe")

            response.apply{
                title = conferences.title
            }
            return conferencesRepository.save(response)
        }
        catch (ex:Exception){
            throw  ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)

        }
    }
    fun delete (id: Long?):Boolean?{
        conferencesRepository.findById(id) ?:
        throw  Exception()
        conferencesRepository.deleteById(id!!)
        return true
    }
    fun listById (id: Long?): Conferences {
        return conferencesRepository.findById(id)
    }
}
