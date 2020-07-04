# Spring Cloud Config Server Demo

- **application.yml** under resources file has the configuration for config-server itself.

- Config Server gets all information from the given git source in **application.yml**.

- **default-label** attribute is the selected branch. There's currently only one branch.

- If given git source is not public, **username** and **password** should be given.

- Client services only have a **bootstrap.yml** file including **application name** and **config uri**.

- Names of the files under configs folder should be **{application-name}-{env}.yml**. For example **client-service-test.yml** includes test environment configuration of client-service. When client-service is run with test profile, this yml will be read.

- **application-dev.yml**, **application-test.yml** and **application-prod.yml** files are common for all services. When you run client-service with test profile, both **application-test** and **client-service-test.yml** files are applied.

- You can test config server with a **GET** request to [localhost:8080/{application-name}/{env}](http://localhost:8080/{application-name}/{env}). For example a **GET** request to [localhost:8080/client-service/test](http://localhost:8080/client-service/test) will return both properties from both **application-test.yml** and **client-service-test.yml** in this case.

  - GET
  - [localhost:8080/client-service/test](http://localhost:8080/client-service/test)

  - Response:
```json
  {
	"name": "client-service",
	"profiles": [
		"test"
	],
	"label": null,
	"version": "a13fab766e284e0bfd693a907695ab6af932b39e",
	"state": null,
	"propertySources": [
		{
			"name": "https://github.com/celikeren/spring-cloud-config-server-demo.git/config-server/src/main/resources/configs/client-service-test.yml",
			"source": {
				"server.port": 8081,
				"value": "client-service-test"
			}
		},
		{
			"name": "https://github.com/celikeren/spring-cloud-config-server-demo.git/config-server/src/main/resources/configs/application-test.yml",
			"source": {
				"management.endpoint.health.show-details": "always",
				"management.endpoint.health.show-components": "always",
				"value": "application-test"
			}
		}
	]
  }
```

- If there's same value in both **application-test.yml** and **client-service-test.yml**, application specific value overrides common value.

  - For example there are two **value** values in the json above. But when you send a GET request to [localhost:8081/value](http://localhost:8081/value) which returns the **value** from config file, **application-test** will return.