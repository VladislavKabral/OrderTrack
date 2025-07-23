package by.kabral.ordertrack.customerservice.rest

import by.kabral.ordertrack.customerservice.dto.CustomerDto
import by.kabral.ordertrack.customerservice.dto.CustomersDto
import by.kabral.ordertrack.customerservice.service.CustomersService
import by.kabral.ordertrack.dto.CustomerExistenceDto
import by.kabral.ordertrack.dto.RemovedEntityDto
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/customers")
class CustomersController(val customersService: CustomersService) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getCustomers(): CustomersDto = customersService.findAll()

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getCustomer(@PathVariable("id") id: UUID): CustomerDto = customersService.findById(id)

    @GetMapping("/{id}/isPresent")
    @ResponseStatus(HttpStatus.OK)
    fun isCustomerPresent(@PathVariable("id") id: UUID) : CustomerExistenceDto =
        customersService.customerExists(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun saveCustomer(@RequestBody @Valid customer: CustomerDto): CustomerDto = customersService.save(customer)

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateCustomer(@PathVariable("id") id: UUID, @RequestBody @Valid customer: CustomerDto): CustomerDto =
        customersService.update(id, customer)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteCustomer(@PathVariable("id") id: UUID) : RemovedEntityDto = customersService.deleteById(id)
}