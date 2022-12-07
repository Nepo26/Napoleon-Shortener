provider "aws" {
  region  = var.awsregion
  access_key = var.AWS_ACCESS_KEY
  secret_key = var.AWS_SECRET_KEY
}

module "vpc" {
  source           = "./modules/vpc"
  application_name = var.app
  lb_ports         = var.container_ports
  ecs_task_ports   = {80:80}
}

module "alb" {
  source           = "./modules/alb"
  application_name = var.app

  load_balancer_sg       = module.vpc.load_balancer_sg
  load_balancer_subnet_a = module.vpc.application_load_balancer_subnet_a
  load_balancer_subnet_b = module.vpc.application_load_balancer_subnet_b
  load_balancer_subnet_c = module.vpc.application_load_balancer_subnet_c
  vpc                    = module.vpc.vpc
}

module "ecr" {
  source           = "./modules/ecr"
  application_name = var.app
}

module "iam" {
  source           = "./modules/iam"
  application_name = var.app

  alb     = module.alb.alb
  ecr_arn = module.ecr.arn
}

module "ecs" {
  source           = "./modules/ecs"
  application_name = var.app

  ecs_role         = module.iam.ecs_role
  ecs_sg           = module.vpc.ecs_sg
  ecs_target_group = module.alb.ecs_target_group
  ecs_subnets_ids  = [
    module.vpc.application_load_balancer_subnet_a.id, module.vpc.application_load_balancer_subnet_b.id,
    module.vpc.application_load_balancer_subnet_c.id
  ]

  container_name    = var.cname
  container_image   = var.cimage
  container_ports   = var.ecs_container_ports
  container_lb_port = 80
}

module "auto_scaling" {
  source           = "./modules/auto-scaling"
  application_name = var.app

  ecs_cluster = module.ecs.ecs_cluster
  ecs_service = module.ecs.ecs_service
}