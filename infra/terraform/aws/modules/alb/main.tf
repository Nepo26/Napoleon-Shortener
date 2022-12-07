resource "aws_alb" "application_load_balancer" {
  name               = "${var.application_name}-load-balancer"
  internal           = false
  load_balancer_type = "application"
  subnets            = [
    var.load_balancer_subnet_a, var.load_balancer_subnet_b, var.load_balancer_subnet_c
  ]
  security_groups = [var.load_balancer_sg.id]

  tags = {
    Name    = var.application_name
    Project = var.application_name
    Billing = var.application_name
  }
}

resource "aws_lb_target_group" "ecs_target_group" {
  for_each = var.ecs_task_ports

  name        = "${var.application_name}-ecs"
  port        = each.key
  protocol    = "HTTP"
  target_type = "ip"
  vpc_id      = var.vpc.id

  health_check {
    enabled             = true
    port                = "80"
    protocol            = "HTTP"
    path                = "/actuator/health"
    interval            = 300
    timeout             = 60
    matcher             = "200,201,301,302"
    healthy_threshold   = 2
    unhealthy_threshold = 6
  }

  tags = {
    Name    = var.application_name
    Project = var.application_name
    Billing = var.application_name
  }
}

resource "aws_lb_listener" "listener" {
  for_each = var.ecs_task_ports

  load_balancer_arn = aws_alb.application_load_balancer.arn
  port              = each.key
  protocol          = "HTTP"
  default_action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.ecs_target_group[each.key].arn
  }
}


