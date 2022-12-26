
package com.example.member.service


import com.example.member.model.Member
import com.example.member.model.Register
import com.example.member.repository.RegisterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
@Service
class RegisterService {
    @Autowired
    lateinit var registerRepository: RegisterRepository


    fun list (pageable: Pageable, register: Register): Page<Register> {
        val matcher = ExampleMatcher.matching()
            .withIgnoreNullValues()
            .withMatcher(("field"), ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
        return registerRepository.findAll(Example.of(register, matcher), pageable)
    }


    fun save (register: Register):Register{
        return registerRepository.save(register)
    }
    fun update(register: Register):Register{
        try {
            registerRepository.findById(register.id)
                ?: throw Exception("El id ${register.id} en detalle no existe")
            return registerRepository.save(register)
        }
        catch(ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun updateTotal(register: Register):Register{
        try{
            val response = registerRepository.findById(register.id)
                ?:throw Exception("El ${register.id} en detalle no existe")
            response.apply{
                id = register.id
            }
            return registerRepository.save(response)
        }
        catch (ex:Exception){
            throw  ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)

        }
    }
    fun delete (id: Long?):Boolean?{
        registerRepository.findById(id) ?:
        throw  Exception()
        registerRepository.deleteById(id!!)
        return true
    }
    fun listById (id: Long?): Register? {
        return registerRepository.findById(id)
    }

}
