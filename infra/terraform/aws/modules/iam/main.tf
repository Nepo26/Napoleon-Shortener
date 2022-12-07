resource "aws_iam_user" "ecr_publisher" {
  name = "ecr-publisher"
  path = "/serviceaccounts/"
}

resource "aws_iam_role" "ecs_service" {
  name               = "fargate-assume-role-role"
  path               = "/serviceaccounts/"
  assume_role_policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Action": "sts:AssumeRole",
      "Principal": {
        "Service": "ecs-tasks.amazonaws.com"
      },
      "Effect": "Allow"
    }
  ]
}
EOF
}

resource "aws_iam_user_policy" "ecr_publisher" {
  name = "ecr-publisher"
  user = aws_iam_user.ecr_publisher.name

  policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Action": [
        "iam:PassRole",
        "iam:GetRole",
        "ecs:DescribeTaskDefinition",
        "ecs:DescribeServices",
        "ecs:UpdateService",
        "ecs:RegisterTaskDefinition",
        "ecr:CompleteLayerUpload",
        "ecr:DescribeRepositories",
        "ecr:ListImages",
        "ecr:DescribeImages",
        "ecr:GetAuthorizationToken",
        "ecr:GetDownloadUrlForLayer",
        "ecr:GetLifecyclePolicy",
        "ecr:InitiateLayerUpload",
        "ecr:PutImage",
        "ecr:UploadLayerPart"
      ],
      "Effect": "Allow",
      "Resource": "*"
    }
  ]
}
EOF
}

resource "aws_iam_access_key" "ecr_publisher" {
  user = aws_iam_user.ecr_publisher.name
}


data "aws_iam_policy_document" "fargate_execution_policy" {
  statement {
    actions = [
      "ecr:CompleteLayerUpload", "ecr:DescribeRepositories", "ecr:ListImages", "ecr:DescribeImages",
      "ecr:GetAuthorizationToken", "ecr:GetDownloadUrlForLayer", "ecr:GetLifecyclePolicy"
    ]

    effect    = "Allow"
    resources = [
      "*"
    ]

  }
}

data "aws_iam_policy_document" "ecs_service_alb" {
  statement {
    effect = "Allow"

    actions = ["ec2:Describe"]

    resources = ["*"]
  }

  statement {
    effect = "Allow"

    actions = [
      "elasticloadbalancing:DeregisterInstancesFromLoadBalancer", "elasticloadbalancing:DeregisterTargets",
      "elasticloadbalancing:Describe*", "elasticloadbalancing:RegisterInstancesWithLoadBalancer",
      "elasticloadbalancing:RegisterTargets"
    ]

    resources = [var.alb.arn]
  }
}

data "aws_iam_policy_document" "ecs_service_standard" {
  statement {
    effect = "Allow"

    actions = [
      "ec2:DescribeTags", "ecs:DeregisterContainerInstance", "ecs:DiscoverPollEndpoint", "ecs:Poll",
      "ecs:RegisterContainerInstance", "ecs:StartTelemetrySession", "ecs:UpdateContainerInstancesState", "ecs:Submit*",
      "logs:CreateLogGroup", "logs:CreateLogStream", "logs:PutLogEvents"
    ]

    resources = ["*"]
  }
}

data "aws_iam_policy_document" "ecs_service_scaling" {
  statement {
    effect = "Allow"

    actions = [
      "application-autoscaling:*", "ecs:DescribeServices", "ecs:UpdateService", "cloudwatch:DescribeAlarms",
      "cloudwatch:PutMetricAlarm", "cloudwatch:DeleteAlarms", "cloudwatch:DescribeAlarmHistory",
      "cloudwatch:DescribeAlarms", "cloudwatch:DescribeAlarmsForMetric", "cloudwatch:GetMetricStatistics",
      "cloudwatch:ListMetrics", "cloudwatch:PutMetricAlarm", "cloudwatch:DisableAlarmActions",
      "cloudwatch:EnableAlarmActions", "iam:CreateServiceLinkedRole", "sns:CreateTopic", "sns:Subscribe", "sns:Get*",
      "sns:List*"
    ]

    resources = ["*"]

  }
}

resource "aws_iam_policy" "ecs_service_elb" {
  name        = "${var.application_name}-alb"
  path        = "/"
  description = "Allow access to the service application load balancer"

  policy = data.aws_iam_policy_document.ecs_service_alb.json
}

resource "aws_iam_policy" "ecs_service_standard" {
  name        = "${var.application_name}-standard"
  path        = "/"
  description = "Allow standard ecs actions"

  policy = data.aws_iam_policy_document.ecs_service_standard.json
}

resource "aws_iam_policy" "ecs_service_scaling" {
  name        = "${var.application_name}-scaling"
  path        = "/"
  description = "Allow ecs service scaling"

  policy = data.aws_iam_policy_document.ecs_service_scaling.json
}

resource "aws_iam_role_policy_attachment" "ecs_service_alb" {
  role       = aws_iam_role.ecs_service.name
  policy_arn = aws_iam_policy.ecs_service_elb.arn
}

resource "aws_iam_role_policy_attachment" "ecs_service_standard" {
  role       = aws_iam_role.ecs_service.name
  policy_arn = aws_iam_policy.ecs_service_standard.arn
}

resource "aws_iam_role_policy_attachment" "ecs_service_scaling" {
  role       = aws_iam_role.ecs_service.name
  policy_arn = aws_iam_policy.ecs_service_scaling.arn
}


