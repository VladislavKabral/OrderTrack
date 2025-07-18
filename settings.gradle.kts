rootProject.name = "OrderTrack"

include(":Common", ":CustomerService", ":EurekaServer", ":ApiGateway")

project(":Common").projectDir = file("Common")
project(":CustomerService").projectDir = file("CustomerService")
project(":ApiGateway").projectDir = file("ApiGateway")
project(":EurekaServer").projectDir = file("EurekaServer")
