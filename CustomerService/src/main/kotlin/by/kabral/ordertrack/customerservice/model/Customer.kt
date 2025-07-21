package by.kabral.ordertrack.customerservice.model

import jakarta.persistence.*
import java.util.UUID

@Entity(name = "customers")
data class Customer (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    var id: UUID?,

    @Column(name = "lastname")
    var lastname: String,

    @Column(name = "firstname")
    var firstname: String,

    @Column(name = "email")
    var email: String,

    @Column(name = "password")
    var password: String
)