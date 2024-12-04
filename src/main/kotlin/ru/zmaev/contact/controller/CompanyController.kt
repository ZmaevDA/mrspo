package ru.zmaev.contact.controller

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.zmaev.contact.service.CompanyService
import java.util.*


@Controller
@RequestMapping("/companies")
class CompanyController(
    private val companyService: CompanyService
) {

    private val log = LoggerFactory.getLogger(CompanyController::class.java)

    companion object {
        const val REDIRECT_COMPANY = "redirect:/companies"
    }

    @GetMapping
    fun index(model: Model): String {
        model.addAttribute("companies", companyService.loadAll())
        return "companies"
    }

    @GetMapping("/add")
    fun addCompanyForm(model: Model): String {
        return "add-company"
    }

    @PostMapping("/add")
    fun addCompany(
        @RequestParam name: String,
        @RequestParam industry: String
    ): String {
        companyService.saveCompany(name, industry)
        return REDIRECT_COMPANY
    }

    @GetMapping("/edit")
    fun editCompanyForm(@RequestParam id: UUID, model: Model): String {
        val company = companyService.loadCompanyById(id)
        log.info("ABOBA: id: $id")
        if (company != null) {
            model.addAttribute("company", company)
            return "edit-company"
        }
        return REDIRECT_COMPANY
    }

    @PostMapping("/edit")
    fun editCompany(
        @RequestParam id: String,
        @RequestParam name: String,
        @RequestParam industry: String
    ): String {
        val companyId = UUID.fromString(id)
        companyService.updateCompanyById(companyId, name, industry)
        return REDIRECT_COMPANY
    }

    @PostMapping("/remove")
    fun removeCompany(@RequestParam id: UUID): String {
        companyService.deleteCompany(id)
        return REDIRECT_COMPANY
    }
}
