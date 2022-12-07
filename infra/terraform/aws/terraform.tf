# Set Up Terraform Cloud to manage its state
# This is not necessary to run the project
terraform {
  cloud {
    organization = "napoleon-shortener"
    workspaces {
      name = "Napoleon-Shortener"
    }
  }

  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 4.45"
    }
  }
}