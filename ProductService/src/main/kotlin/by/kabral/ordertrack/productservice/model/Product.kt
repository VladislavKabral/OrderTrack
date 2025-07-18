package by.kabral.ordertrack.productservice.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import java.math.BigDecimal
import java.util.UUID

@Entity(name = "products")
data class Product(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    var id: UUID?,

    @Column(name = "name")
    var name: String,

    @Column(name = "description")
    var description: String?,

    @Column(name = "price")
    var price: BigDecimal,

    @OneToOne(mappedBy = "product", cascade = [CascadeType.ALL], optional = false)
    var quantity: ProductQuantity
)
