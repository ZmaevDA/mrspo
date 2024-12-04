package ru.zmaev.contact.extension.transformer

import ru.zmaev.contact.model.dto.response.ContactResponse
import ru.zmaev.contact.model.entity.Contact

fun Contact.toResponse(): ContactResponse =
    ContactResponse(
        id = id.toString(),
        name = name!!,
        email = email!!,
        phone = phone!!,
        company = company!!.toResponse(),
        addresses = addresses.map { it.toResponse() }
    )