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

### From a _Product Owner/Stakeholder_ Perspective
- "Wouldn't be nice if we could view how many times a certain shortened link was hitted
on?"
- "What about the location and countries of each hit?"
