package com.example.member.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
@Entity
@Table(name="register")
class Register {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null

    @Column(name="member_id")
    var memberId:Long? = null

    @Column(name="conferences_id")
    var conferencesId:Long? = null

    @Column(name="registed_at")
    var registerAt:String? = null
    var assisted:String? = null
    var code: String? = null


}
