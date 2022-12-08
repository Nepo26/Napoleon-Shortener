output "ecr_repository_name" {
  value = module.ecr.name
}

output "ecr_repository_registry" {
  value = module.ecr.registry
}

output "ecs_service_name" {
  value = module.ecs.ecs_service.name
}

output "ecs_cluster_name" {
  value = module.ecs.ecs_cluster.name
}