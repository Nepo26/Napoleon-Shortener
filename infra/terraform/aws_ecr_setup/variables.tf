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


variable "AWS_ACCESS_KEY" {}

variable "AWS_SECRET_KEY" {}
