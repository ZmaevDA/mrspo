package ru.zmaev.contact.extension.transformer

import ru.zmaev.contact.model.dto.response.AddressResponse
import ru.zmaev.contact.model.entity.Address

fun Address.toResponse(): AddressResponse =
    AddressResponse(
        id = id.toString(),
        street = street!!,
        city = city!!,
        state = state!!,
        postalCode = postalCode!!,
        country = country!!
    )