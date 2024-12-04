package ru.zmaev.contact.model.dto.response

data class ContactResponse(
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val company: CompanyResponse,
    val addresses: List<AddressResponse>
)