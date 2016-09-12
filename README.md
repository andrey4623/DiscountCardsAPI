# DiscountCardsAPI

DiscountCardsAPI is a REST API web application for providing an access to store/cafe/company discount cards for your website or mobile app. If you want to let your customers to check online their discount value and total spendings but you don't want to share access to CRM (because it can be too dangerous!), the DiscountCardsAPI project is what you want.

How does it work:

- Server sends discount cards data from CRM to this app using REST API
- A public website or mobile app gets data from this app using REST API
- Everything is secure and your customers are happy

Thanks Spring 4 and Hibernate 5 for making this implementation reliable and easy.

## REST API Endpoints

The app has two endpoints.

Get card's info
```sh
GET 127.0.0.1:8080/cards?number=1111111111111
```

Update card's info or create a new card if it doesn't exist

```sh
POST 127.0.0.1:8080/card?number=1111111111111&value=5&discount=7
```

## Requirements

- Java 1.8 or newer
- MySQL 5 or newer

## Building

DiscountCardsAPI requires Java 1.8 and Maven 3.3.9.


```sh
$ mvn clean install
```

## Deployment

```sh
$ cd target/
$ vi jdbc.properties
$ java -jar cards-1.0.0.jar
```

Now you can try to add a new card and retrieve its data. Please refer to the section REST API Endpoints for that.

## License

MIT
