rootProject.name = "OrderTrack"

include(
    ":Common",
    ":CustomerService",
    ":EurekaServer",
    ":ApiGateway",
    ":ProductService",
    ":PaymentService")

project(":Common").projectDir = file("Common")
project(":CustomerService").projectDir = file("CustomerService")
project(":ApiGateway").projectDir = file("ApiGateway")
project(":EurekaServer").projectDir = file("EurekaServer")
project(":ProductService").projectDir = file("ProductService")
project(":PaymentService").projectDir = file("PaymentService")
