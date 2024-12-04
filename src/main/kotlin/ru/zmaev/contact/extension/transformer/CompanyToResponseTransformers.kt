package ru.zmaev.contact.extension.transformer

import ru.zmaev.contact.model.dto.response.CompanyResponse
import ru.zmaev.contact.model.entity.Company

fun Company.toResponse(): CompanyResponse =
    CompanyResponse(
        id = id.toString(),
        name = name!!,
        industry = industry!!
    )