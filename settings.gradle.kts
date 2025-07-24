rootProject.name = "OrderTrack"

include(
    ":Common",
    ":CustomerService",
    ":EurekaServer",
    ":ApiGateway",
    ":ProductService",
    ":PaymentService",
    ":OrderService",
    ":NotificationService")

project(":Common").projectDir = file("Common")
project(":CustomerService").projectDir = file("CustomerService")
project(":ApiGateway").projectDir = file("ApiGateway")
project(":EurekaServer").projectDir = file("EurekaServer")
project(":ProductService").projectDir = file("ProductService")
project(":PaymentService").projectDir = file("PaymentService")
project(":OrderService").projectDir = file("OrderService")
project(":NotificationService").projectDir = file("NotificationService")
