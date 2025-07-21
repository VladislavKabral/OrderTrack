package by.kabral.ordertrack.productservice.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import java.util.UUID

@Entity(name = "product_quantity")
data class ProductQuantity(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    var id: UUID?,

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false, unique = true)
    var product: Product?,

    @Column(name = "quantity")
    var quantity: Long,
)
