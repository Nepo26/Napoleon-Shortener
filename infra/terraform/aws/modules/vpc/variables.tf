variable "application_name" {
  type        = string
  default     = "napoleon-shortener"
  description = "Name of the application"
}

variable "lb_ports" {
  type        = list(number)
  default     = [80, 443, 4215]
  description = "List of ports to allow access on the load balancer"
}

variable "ecs_task_ports" {
  type        = list(number)
  default     = [80]
  description = "List of ports to allow access on the container"
}