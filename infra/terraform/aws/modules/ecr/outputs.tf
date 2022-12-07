output "arn" {
  value = aws_ecr_repository.repository.arn
}

output "name" {
  value = aws_ecr_repository.repository.name
}

output "registry" {
  value = aws_ecr_repository.repository.registry_id
}