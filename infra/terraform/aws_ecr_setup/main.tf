provider "aws" {
  region  = var.awsregion
  access_key = var.AWS_ACCESS_KEY
  secret_key = var.AWS_SECRET_KEY
}

module "ecr" {
  source           = "../aws/modules/ecr"
  application_name = var.app
}