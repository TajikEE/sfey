## Notes

Project has been done using the same pre-setup Spring application that was provided. The schema.sql file has been removed because tables are auto initialized with entity model and account data is seeded from src > main > resources > data.sql

There is also a test which can be run by:

```
gradle test
```

## To build a project

- Java 1.8 or newer
- Gradle 6.0 or newer

## About default setup

- `gradle assemble` to initialize a project
- You can start Spring application via your favourite ide or using `gradle bootjar` and then executing previously created jar
- It uses h2 for db so no need to setup any external database. To access running application db http://localhost:8080/h2-console/login.jsp (login parameters are in application.properties file)
- schema.sql will generate sql tables when Spring application starts
- If you have any problems to run this pre-setup Spring application then feel free to create your own Spring project

## Assignment

- Write endpoint POST `/payment` with json body
  - Request json example:
    ```
    {
        "senderAccountId": "1",
        "receiverAccountId: "2",
        "amount": "100.00"
    }
    ```
- Requirements
  - Amount must be a number > 0 and can have two decimal places
  - senderAccountId, receiverAccountId are account table id-s
    - Account balance cannot go negative.
  - Money is withdrawn from sender account and deposited into receiver account
    - And everything else what you think that is important
- Example
  - Before payment
    - Sender account balance 100.00
    - Receiver account balance 100.00
  - Do payment
    - POST `/payment`
      ```
      {
      	"senderAccountId": "1",
      	"receiverAccountId: "2",
      	"amount": "100.00"
      }
      ```
  - After payment
    - Sender account balance 0.00
    - Receiver account balance 200.00
