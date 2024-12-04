package ru.zmaev.contact.model.entity

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "addresses")
data class Address(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: UUID? = UUID.randomUUID(),
    var street: String? = null,
    var city: String? = null,
    var state: String? = null,
    var postalCode: String? = null,
    var country: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id")
    var contact: Contact? = null
)