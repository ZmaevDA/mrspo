package ru.zmaev.contact.controller

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.zmaev.contact.service.CompanyService
import ru.zmaev.contact.service.ContactService
import java.util.UUID

@Controller
@RequestMapping("/contacts")
class ContactController(
    private val contactService: ContactService,
    private val companyService: CompanyService
) {

    private val log = LoggerFactory.getLogger(ContactController::class.java)

    companion object {
        const val REDIRECT_CONTACT = "redirect:/contacts"
    }

    @GetMapping
    fun index(model: Model): String {
        model.addAttribute("companies", companyService.loadAll())
        model.addAttribute("contacts", contactService.loadAll())
        return "contacts"
    }

    @GetMapping("/add")
    fun addContactForm(model: Model): String {
        model.addAttribute("companies", companyService.loadAll())
        return "fragments/add-contact-modal"
    }

    @PostMapping("/add")
    fun addContact(
        @RequestParam name: String,
        @RequestParam email: String,
        @RequestParam phone: String,
        @RequestParam companyId: String
    ): String {
        contactService.saveContact(name, email, phone, companyId)
        return REDIRECT_CONTACT
    }

    @GetMapping("/edit")
    fun editContactForm(@RequestParam id: UUID, model: Model): String {
        val contact = contactService.loadContactById(id)
        val companies = companyService.loadAll()
        if (contact != null) {
            model.addAttribute("contact", contact)
            model.addAttribute("companies", companies)
            return "fragments/add-contact-modal"
        }
        return REDIRECT_CONTACT
    }

    @PostMapping("/edit")
    fun editContact(
        @RequestParam id: UUID,
        @RequestParam name: String,
        @RequestParam email: String,
        @RequestParam phone: String,
        @RequestParam companyId: String
    ): String {
        contactService.updateContactById(id, name, email, phone, companyId)
        return REDIRECT_CONTACT
    }

    @PostMapping("/remove")
    fun removeContact(@RequestParam id: UUID): String {
        contactService.deleteContact(id)
        return REDIRECT_CONTACT
    }
}
