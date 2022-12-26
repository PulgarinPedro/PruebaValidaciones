package com.example.member.repository


import com.example.member.model.Register
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface RegisterRepository:JpaRepository<Register, Long> {
    fun findById(id: Long?): Register?

}
