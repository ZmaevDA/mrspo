package ru.zmaev.contact.service

import org.springframework.stereotype.Service
import ru.zmaev.contact.model.entity.Company
import ru.zmaev.contact.extension.transformer.toResponse
import ru.zmaev.contact.model.dto.response.CompanyResponse
import ru.zmaev.contact.repository.CompanyRepository
import java.util.UUID

@Service
class CompanyService(
    private val companyRepository: CompanyRepository
) {

    fun saveCompany(name: String, industry: String): CompanyResponse {
        val company = Company(
            name = name,
            industry = industry
        )

        val savedCompany = companyRepository.save(company)
        return savedCompany.toResponse()
    }

    fun loadCompanyById(id: UUID): Company? {
        return loadCompanyOrThrow(id)
    }

    fun updateCompanyById(id: UUID, name: String, industry: String): CompanyResponse {
        val company = loadCompanyOrThrow(id)
        val companyToUpdate = Company(
            id = company!!.id,
            name = name,
            industry = industry,
            employees = company.employees
        )
        val savedCompany = companyRepository.save(companyToUpdate)
        return savedCompany.toResponse()
    }

    fun deleteCompany(id: UUID) {
        val company = loadCompanyOrThrow(id)
        companyRepository.delete(company!!)
    }

    fun loadAll(): List<CompanyResponse> {
        return companyRepository.findAll().map { it.toResponse() }
    }

    private fun loadCompanyOrThrow(id: UUID): Company? =
        companyRepository.findById(id).orElseThrow { RuntimeException("Company Not Found") }
}
