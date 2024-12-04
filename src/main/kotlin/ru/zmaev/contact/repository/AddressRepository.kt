package ru.zmaev.contact.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.zmaev.contact.model.entity.Address
import java.util.UUID

@Repository
interface AddressRepository : JpaRepository<Address, UUID>