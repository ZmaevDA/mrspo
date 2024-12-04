package ru.zmaev.contact.model.entity

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "contacts")
data class Contact(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: UUID = UUID.randomUUID(),
    var name: String? = null,
    var email: String? = null,
    var phone: String? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    var company: Company? = null,

    @OneToMany(mappedBy = "contact", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var addresses: List<Address> = mutableListOf()
)
