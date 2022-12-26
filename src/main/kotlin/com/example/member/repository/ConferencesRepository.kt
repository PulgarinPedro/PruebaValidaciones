package com.example.member.repository


import com.example.member.model.Conferences
import org.springframework.data.jpa.repository.JpaRepository

import org.springframework.stereotype.Repository


@Repository
interface ConferencesRepository:JpaRepository<Conferences, Long> {
    fun findById(id: Long?): Conferences



}
