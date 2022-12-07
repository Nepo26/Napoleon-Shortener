variable "application_name" {
  type        = string
  default     = "napoleon-shortener"
  description = "Name of the application"
}

variable "load_balancer_sg" {}

variable "load_balancer_subnet_a" {}

variable "load_balancer_subnet_b" {}

variable "load_balancer_subnet_c" {}

variable "vpc" {}

variable "ecs_task_ports" {
  type        = list(number)
  default     = [80]
  description = "List of ports to allow access on the container"
}