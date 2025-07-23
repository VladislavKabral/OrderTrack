package by.kabral.ordertrack.paymentservice.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.math.BigDecimal
import java.util.UUID

@Entity(name = "accounts")
data class Account(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    var id: UUID?,

    @Column(name = "customer_id")
    val customerId: UUID,

    @Column(name = "balance")
    var balance: BigDecimal?,

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    var status: AccountStatus?
)
