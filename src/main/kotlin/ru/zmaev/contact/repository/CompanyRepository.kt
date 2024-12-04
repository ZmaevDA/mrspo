package ru.zmaev.contact.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.zmaev.contact.model.entity.Company
import java.util.UUID

@Repository
interface CompanyRepository: JpaRepository<Company, UUID> {
}