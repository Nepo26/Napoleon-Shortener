variable "application_name" {
  type        = string
  default     = "napoleon-shortener"
  description = "Name of the application"
}

variable "alb" {}

variable "ecr_arn" {}