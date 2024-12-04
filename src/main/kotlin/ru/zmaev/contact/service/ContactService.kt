package ru.zmaev.contact.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.zmaev.contact.extension.transformer.toResponse
import ru.zmaev.contact.model.dto.response.ContactResponse
import ru.zmaev.contact.model.entity.Contact
import ru.zmaev.contact.repository.ContactRepository
import java.util.*

@Service
class ContactService(
    private val contactRepository: ContactRepository,
    private val companyService: CompanyService
) {

    @Transactional
    fun saveContact(name: String, email: String, phone: String, companyId: String) {
        val contact = Contact(
            name = name,
            email = email,
            phone = phone,
            company = companyService.loadCompanyById(UUID.fromString(companyId))
        )

        contactRepository.save(contact)
    }

    fun loadContactById(id: UUID): Contact? {
        return loadContactOrThrow(id)
    }

    @Transactional
    fun updateContactById(id: UUID, name: String, email: String, phone: String, companyId: String) {
        val contact = loadContactOrThrow(id)
        val contactToUpdate = Contact(
            id = contact!!.id,
            name = name,
            email = email,
            phone = phone
        )
        contactToUpdate.company = companyService.loadCompanyById(UUID.fromString(companyId))
        contactRepository.save(contactToUpdate)
    }

    @Transactional
    fun deleteContact(id: UUID){
        val contact = loadContactOrThrow(id)
        contactRepository.delete(contact!!)
    }

    fun loadAll(): List<ContactResponse> {
        return contactRepository.findAll().map { it.toResponse() }
    }

    private fun loadContactOrThrow(id: UUID): Contact? =
        contactRepository.findById(id).orElseThrow { RuntimeException("Not Found") }
}
