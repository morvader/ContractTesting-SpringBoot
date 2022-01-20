# Pact JVM example (Maven + Springboot + JUnit5)

### Table of contents

- [Purpose of this project](#purpose-of-this-project)
- [Commands to generate Pacts in the Consumer side](#commands-to-generate-pacts-in-the-consumer-side)
- [Commands to verify Pacts in the Producer side](#commands-to-verify-pacts-in-the-producer-side)
- [Reference](#references)

## Purpose of this project

The aim of this project is to see how to configure 2 microservices, a provider and a consumer (both REST API Spring
Boot), to run contract testing with Pact using PactBroker to publish/verify contracts generated.

## Commands to generate Pacts in the Consumer side

To generate pacts locally for a specific contract file

```bash
 mvn verify -pl Consumer
 ```

To publish the pacts in the PactBroker

```bash
docker-compose up -d # To initialize the Pact Broker
mvn pact:publish -pl Consumer
```

## Commands to verify Pacts in the Producer side

To verify pacts without publishing results

```bash
mvn verify -pl Provider
```

To verify pacts and publish results in the Pact Broker

```bash
docker-compose up -d # To initialize the Pact Broker
mvn verify -Dpact.verifier.publishResults=true -Dpact.provider.version=0.0.1-SNAPSHOT -pl Provider
```

## References

- [Pact Workshop Maven + Springboot + JUnit5](https://github.com/pact-foundation/pact-workshop-Maven-Springboot-JUnit5)
- [Example Spring Boot project for the Pact workshop](https://github.com/pact-foundation/pact-workshop-jvm-spring)
- [Consumer Driven Contract Testing using pact java](https://blog.testproject.io/2020/05/27/consumer-driven-contract-testing-using-pact-java)
