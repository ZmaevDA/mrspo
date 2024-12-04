package ru.zmaev.contact.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.zmaev.contact.service.AddressService
import java.util.UUID

@Controller
@RequestMapping("/addresses")
class AddressController(private val addressService: AddressService) {

    companion object {
        const val REDIRECT_ADDRESS = "redirect:/addresses"
    }

    @GetMapping
    fun index(model: Model): String {
        model.addAttribute("addresses", addressService.loadAll())
        return "address/address"
    }

    @GetMapping("/add")
    fun addAddressForm(model: Model): String {
        return "address/add-address"
    }

    @PostMapping("/add")
    fun addAddress(
        @RequestParam street: String,
        @RequestParam city: String,
        @RequestParam state: String,
        @RequestParam postalCode: String,
        @RequestParam country: String,
        @RequestParam contactId: UUID
    ): String {
        addressService.saveAddress(street, city, state, postalCode, country, contactId)
        return REDIRECT_ADDRESS
    }

    @GetMapping("/edit")
    fun editAddressForm(@RequestParam id: UUID, model: Model): String {
        val address = addressService.loadAddressById(id)
        if (address != null) {
            model.addAttribute("address", address)
            return "address/edit-address"
        }
        return REDIRECT_ADDRESS
    }

    @PostMapping("/edit")
    fun editAddress(
        @RequestParam id: UUID,
        @RequestParam street: String,
        @RequestParam city: String,
        @RequestParam state: String,
        @RequestParam postalCode: String,
        @RequestParam country: String
    ): String {
        addressService.updateAddressById(id, street, city, state, postalCode, country)
        return REDIRECT_ADDRESS
    }

    @PostMapping("/remove")
    fun removeAddress(@RequestParam id: UUID): String {
        addressService.deleteAddress(id)
        return REDIRECT_ADDRESS
    }
}
