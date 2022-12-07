output "vpc" {
  value = aws_vpc.vpc
}

output "application_load_balancer_subnet_a" {
  value = aws_subnet.subnet["elastic-load-balancer-a"]
}

output "application_load_balancer_subnet_b" {
  value = aws_subnet.subnet["elastic-load-balancer-b"]
}

output "application_load_balancer_subnet_c" {
  value = aws_subnet.subnet["elastic-load-balancer-c"]
}

output "ecs_subnet_a" {
  value = aws_subnet.subnet["ecs-a"]
}

output "ecs_subnet_b" {
  value = aws_subnet.subnet["ecs-b"]
}

output "ecs_subnet_c" {
  value = aws_subnet.subnet["ecs-c"]
}

output "load_balancer_sg" {
  value = aws_security_group.load_balancer
}

output "ecs_sg" {
  value = aws_security_group.ecs_task
}