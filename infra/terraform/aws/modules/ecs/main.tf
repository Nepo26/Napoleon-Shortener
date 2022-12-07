# Cluster Definition
resource "aws_ecs_cluster" "ecs_cluster" {
  name = "${var.application_name}-ecs-cluster"

  setting {
    name  = "containerInsights"
    value = "enabled"
  }

  tags = {
    Name    = var.application_name
    Project = var.application_name
    Billing = var.application_name
  }
}

resource "aws_ecs_cluster_capacity_providers" "ecs_cluster_capacity_provider" {
  cluster_name       = aws_ecs_cluster.ecs_cluster.name
  capacity_providers = ["FARGATE"]
}


resource "aws_ecs_task_definition" "task_definition" {
  family                   = var.application_name
  requires_compatibilities = [
    "FARGATE",
  ]

  execution_role_arn = var.ecs_role.arn
  task_role_arn      = var.ecs_role.arn

  network_mode = "awsvpc"
  cpu          = 256
  memory       = 512

  container_definitions = jsonencode([
    {
      name        = var.container_name
      image       = var.container_image
      essential   = true
      cpu         = 256
      memory      = 512
      environment = [
        {
          "name" : "AUTHOR", "value" : "Nepo26"
        }
      ]
      portMappings = [
      for port in var.container_ports :
      {
        containerPort = port
        hostPort      = port
      }
      ]
    }
  ])

  tags = {
    Name    = var.application_name
    Project = var.application_name
    Billing = var.application_name
  }
}

# Service
resource "aws_ecs_service" "ecs_service" {
  name            = "${var.application_name}-ecs-service"
  cluster         = aws_ecs_cluster.ecs_cluster.id
  task_definition = aws_ecs_task_definition.task_definition.arn
  launch_type     = "FARGATE"
  desired_count   = 1

  lifecycle {
    ignore_changes = [
      desired_count
    ]
  }

  network_configuration {
    subnets          = [for subnet_id in var.ecs_subnets_ids : subnet_id]
    security_groups  = [var.ecs_sg.id]
    assign_public_ip = true
  }

  load_balancer {
    target_group_arn = var.ecs_target_group.arn
    container_name   = var.container_name
    container_port   = var.container_lb_port
  }

}