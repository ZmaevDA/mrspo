package ru.zmaev.contact.repository

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.zmaev.contact.model.entity.Contact
import java.util.*

@Repository
interface ContactRepository : JpaRepository<Contact, UUID> {

//    @Query("SELECT c FROM Contact c LEFT JOIN FETCH c.company LEFT JOIN FETCH c.addresses")
//    override fun findAll(): List<Contact>

    @EntityGraph(
        attributePaths = [
            "company",
            "addresses"
        ], type = EntityGraph.EntityGraphType.LOAD
    )
    override fun findAll(): List<Contact>
}