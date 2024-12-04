package ru.zmaev.contact.model.entity

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "companies")
data class Company(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: UUID? = UUID.randomUUID(),
    var name: String? = null,
    var industry: String? = null,

    @OneToMany(mappedBy = "company", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var employees: List<Contact> = mutableListOf()
)