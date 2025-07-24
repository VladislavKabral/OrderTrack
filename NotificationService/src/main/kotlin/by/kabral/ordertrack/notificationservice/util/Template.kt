package by.kabral.ordertrack.notificationservice.util

object Template {
    val NEW_ORDER_NOTIFICATION_TEMPLATE = """
        <html>
          <body style="font-family: Arial; background-color: #f4f4f4; padding: 20px;">
            <div style="max-width: 600px; margin: auto; background-color: #ffffff; border-radius: 8px; padding: 20px; box-shadow: 0 2px 8px rgba(0,0,0,0.1);">
              <h2 style="color: #2a9d8f;">Thank you for your order, ${'$'}lastname ${'$'}firstname!</h2>
              <p>We've successfully received your order and have already started processing it. Here are its details:</p>
              <table style="width: 100%; border-collapse: collapse; margin: 20px 0;">
                <tr><td><strong>ID of the order:</strong></td><td>${'$'}orderId</td></tr>
                <tr><td><strong>Product:</strong></td><td>${'$'}productName</td></tr>
                <tr><td><strong>Count:</strong></td><td>${'$'}productCount</td></tr>
                <tr><td><strong>Total amount:</strong></td><td>${'$'}totalAmount</td></tr>
                <tr><td><strong>Date of receipt:</strong></td><td>${'$'}createdAt</td></tr>
              </table>
              <p>You will receive an additional notification when the order is shipped 📦</p>
              <hr style="margin-top: 30px; border: none; border-top: 1px solid #eee;">
              <p style="font-size: 12px; color: #777;">OrderTrack • Thank you for choosing us!</p>
            </div>
          </body>
        </html>
    """.trimIndent()

}