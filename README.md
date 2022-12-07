# Napoleon-Shortener
A simple url shortener

Premises:
1. The site should short the URL and generate a unique code of 5 chars at max.
   1. The shortened URL should not expire
   2. If submited multiple times the URL should be different

2. The application should have an architecture that supports the max of simultaneous access
   1. Apllication should be **stateless** and auto scalable 
   2. SLA of 99% (Each 100 request should not have more than 1 failure)
3. Should not have authentication
4. No need of a front end

## How to run locally test it?

### Docker Compose
- Just run docker compose and wait for everything to start up.
- Then go to _infra/terraform/localstack/database_ and run `terraform apply -auto-approve`
- Now you can normally test the application.

## Though Process

### From a _Developer_ Perspective
For a simple approach we need to simply get the url and save it to a database and
return the generated ID. This being just a POST request to a VM with
a public IP.

### From a _DevOps_ Perspective
- We may use a _CI/CD_ to deploy the application faster and easier.
- Use a _Docker/Kubernetes_ to standardize the environment.
- To handle more requests we can use a _Load Balancer_ so that each instance handles
less
- "Hmmm, simulating the whole environment locally would be amazing."
- There should be automated tests and a coverage of 100% of the code.
- Auto scalibility
- Performance and load tests to see what is the breaking point
- Continuous profiling to analyse performance anywhere 
- As the application grows we may need a cache to better handle the amount of requests
- As the application is kind of simple the point of failure is the random id generation. 
So a specific service to do this and handle it can considerably improve the application speed.
- What could we do in case of a multi region architecture?


### From a _Product Owner/Stakeholder_ Perspective
- "Wouldn't be nice if we could view how many times a certain shortened link was hitted
on?"
- "What about the location and countries of each hit?"


## Timeline (Development Diary)

### Day 0 (29/11)
Though about what needed to be done, what could be accomplished, research of other solutions,
thinking about other perspectives.

### Day 1 (30/11)
Started to create a basic and simple solution. Just a shortener that works and redirects. Spent a little more
time than should have on code architecture. Day wasn't as productive  as I know it could have been.

### Day 2 (01/12)
Continue to develop basic application. Spent some time on AWS integration and AWS Localstack.

### Day 3 (02/12)
Started working on tests and didn't go too far. Study about other probable ways to think about
the architecture. Study how to start working with Github Actions. Created simple terraform file.

### Day 4 (03/12)
Continued to study other architectures. Improved how to locally test the application by making a 
better _docker-compose.yml_. Still trying to better understand github actions before a deep dive.
Afraid I'll not have enough time to implement what I wished for. Unit and integration tests not yet done.
