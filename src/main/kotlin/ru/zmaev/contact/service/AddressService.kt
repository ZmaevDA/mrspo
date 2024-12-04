package ru.zmaev.contact.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.zmaev.contact.model.entity.Address
import ru.zmaev.contact.extension.transformer.toResponse
import ru.zmaev.contact.model.dto.response.AddressResponse
import ru.zmaev.contact.repository.AddressRepository
import java.util.UUID

@Service
class AddressService(
    private val addressRepository: AddressRepository
) {

    @Transactional
    fun saveAddress(street: String, city: String, state: String, postalCode: String, country: String, contactId: UUID): AddressResponse {
        val address = Address(
            street = street,
            city = city,
            state = state,
            postalCode = postalCode,
            country = country
        )

        val savedAddress = addressRepository.save(address)
        return savedAddress.toResponse()
    }

    fun loadAddressById(id: UUID): Address? {
        return loadAddressOrThrow(id)
    }

    @Transactional
    fun updateAddressById(id: UUID, street: String, city: String, state: String, postalCode: String, country: String): AddressResponse {
        val address = loadAddressOrThrow(id)
        val addressToUpdate = Address(
            id = address!!.id,
            street = street,
            city = city,
            state = state,
            postalCode = postalCode,
            country = country,
            contact = address.contact
        )
        val savedAddress = addressRepository.save(addressToUpdate)
        return savedAddress.toResponse()
    }

    @Transactional
    fun deleteAddress(id: UUID) {
        val address = loadAddressOrThrow(id)
        addressRepository.delete(address!!)
    }

    fun loadAll(): List<AddressResponse> {
        return addressRepository.findAll().map { it.toResponse() }
    }

    private fun loadAddressOrThrow(id: UUID): Address? =
        addressRepository.findById(id).orElseThrow { RuntimeException("Address Not Found") }
}
