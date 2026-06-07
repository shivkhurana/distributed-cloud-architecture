# Enterprise Microservice Routing (Scaffold)

This project is a lightweight scaffold for an enterprise microservice that demonstrates
Controller, Service, Repository layering, validation, global error handling, and unit tests.

Build & test (requires Maven):

```bash
cd enterprise_microservice_routing
mvn test
```

End points:
- POST `/api/v1/transactions/process` - process transaction (JSON payload)
- GET `/api/v1/transactions/{id}` - retrieve processed transaction
