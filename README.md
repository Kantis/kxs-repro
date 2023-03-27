## Webflux KotlinX Serialization issue

When using KotlinX serialization with Webflux, we expect that any combination of `suspend`
functions and `ResponseEntity<T>` to play nicely together.

This project demonstrates that this is only the case when using `suspend` and **not** using `ResponseEntity<T>`. 
Any other combination of the two will cause Webflux to fall back to Jackson serialization which does not handle
value classes.


### How to run
1. ```./gradlew bootRun```

2. ```./gradlew check```