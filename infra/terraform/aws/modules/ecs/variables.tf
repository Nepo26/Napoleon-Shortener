variable "application_name" {
  type        = string
  default     = "napoleon-shortener"
  description = "Name of the application"
}

variable "ecs_target_group" {}

variable "ecs_subnets_ids" {
  type        = list(string)
  description = "A set of subnets that the ecs will be part of "
}

variable "ecs_sg" {}

variable "ecs_role" {}

# Container related variables (Should be a particular one each run)

variable "container_name" {
  type        = string
  default     = "napoleon"
  description = "Name of the container to be used on ecs"
}

variable "container_image" {
  type        = string
  description = "Image to be used on the container"
}

variable "container_ports" {
  type        = list(number)
  default     = [4215, 80]
  description = "List of ports to be open on the container"
}

variable "container_lb_port" {
  type        = number
  default     = 4215
  description = "List of ports to be open on the container"
}

