package ru.zmaev.contact.model.dto.response

data class AddressResponse(
   val id: String,
   val street: String,
   val city: String,
   val state: String,
   val postalCode: String,
   val country: String
)