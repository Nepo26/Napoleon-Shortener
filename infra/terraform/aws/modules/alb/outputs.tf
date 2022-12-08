output "alb" {
  value = aws_alb.application_load_balancer
}

output "ecs_target_group" {
  value = aws_lb_target_group.ecs_target_group
}