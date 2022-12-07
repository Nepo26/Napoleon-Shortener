variable "app" {
  type        = string
  default     = "napoleon-shortener"
  description = "Name of the application"
}

variable "awsregion" {
  type        = string
  default     = "us-east-1"
  description = "AWS Region"
}

variable "cname" {
  type        = string
  default     = "napoleon"
  description = "The container name"
}

variable "cimage" {
  type        = string
  default     = "Placeholder123"
  description = "The image where it is hosted, be it with an ECR or Docker Hub or any other docker repository"
}

variable "container_ports" {
  type        = map(number)
  default     = {80:80, 443:443, 4215:4215}
  description = "The ports to which access the application, be it the container or the load balancer"
}

variable "ecs_container_ports" {
  type        = list(number)
  default     = [80, 443, 4215]
  description = "The ports to which access the application, be it the container or the load balancer"
}

variable "AWS_ACCESS_KEY" {}

variable "AWS_SECRET_KEY" {}