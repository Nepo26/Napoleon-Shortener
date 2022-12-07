resource "aws_vpc" "vpc" {
  cidr_block           = "192.0.0.0/16"
  enable_dns_support   = true
  enable_dns_hostnames = true

  tags = {
    Name    = var.application_name
    Project = var.application_name
  }
}

resource "aws_internet_gateway" "internal_gateway" {
  vpc_id = aws_vpc.vpc.id

  tags = {
    Name    = var.application_name
    Project = var.application_name
    Billing = var.application_name
  }
}

# Route Table to describe the route for certain CIDR blocks
# Just routing all traffic to the internet gateway
# TODO Add a NAT Gateway to improve security by separating some traffic
resource "aws_route_table" "route_table" {
  vpc_id = aws_vpc.vpc.id

  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.internal_gateway.id
  }

  tags = {
    Name    = var.application_name
    Project = var.application_name
  }

}

# Subnets
# Creating 1 subnet for each availability zone on us-east-1. The total being 3
# Creating 3 more to mirror it on the Load Balancer side

data "aws_availability_zones" "available" {}

variable "subnet_names" {
  type    = list(string)
  default = ["elastic-load-balancer-a", "elastic-load-balancer-b", "elastic-load-balancer-c", "ecs-a", "ecs-b", "ecs-c"]
}

resource "aws_subnet" "subnet" {
  for_each = var.subnet_names

  vpc_id                  = aws_vpc.vpc.id
  # This just makes sure that each subnet gets a different cidr_block
  # 192.0.0.0/24
  # 192.0.1.0/24
  cidr_block              = "192.0.${index(var.subnet_names, each.key)}.0/24"
  availability_zone       = data.aws_availability_zones.available.names[0]
  map_public_ip_on_launch = true

  tags = {
    Name    = each.key
    Project = var.application_name
  }
}

resource "aws_route_table_association" "route" {
  for_each = var.subnet_names

  subnet_id      = aws_subnet.subnet[each.key].id
  route_table_id = aws_route_table.route_table.id
}

# Security Groups
resource "aws_security_group" "load_balancer" {
  vpc_id = aws_vpc.vpc.id

  tags = {
    Name    = "load-balancer"
    Project = var.application_name
  }
}

resource "aws_security_group" "ecs_task" {
  vpc_id = aws_vpc.vpc.id

  tags = {
    Name    = "ecs-task"
    Project = var.application_name
  }
}

# Security Group Rules

## Ingress Security Group Rules
resource "aws_security_group_rule" "ingress_load_balancer" {
  for_each = var.lb_ports

  from_port         = each.key
  to_port           = each.key
  protocol          = "tcp"
  security_group_id = aws_security_group.load_balancer.id
  cidr_blocks       = ["0.0.0.0/0"]
  type              = "ingress"
}

resource "aws_security_group_rule" "ingress_ecs_task_elb" {
  for_each = var.ecs_task_ports

  from_port                = each.key
  to_port                  = each.key
  protocol                 = "tcp"
  security_group_id        = aws_security_group.ecs_task.id
  source_security_group_id = aws_security_group.load_balancer.id
  type                     = "ingress"
}


## Egress Security Group Rules
resource "aws_security_group_rule" "egress_load_balancer" {
  from_port         = 0
  to_port           = 65535
  protocol          = "tcp"
  security_group_id = aws_security_group.load_balancer.id
  cidr_blocks       = ["0.0.0.0/0"]
  type              = "egress"
}

resource "aws_security_group_rule" "egress_ecs_task" {
  from_port         = 0
  to_port           = 65535
  protocol          = "tcp"
  security_group_id = aws_security_group.ecs_task.id
  cidr_blocks       = ["0.0.0.0/0"]
  type              = "egress"
}

