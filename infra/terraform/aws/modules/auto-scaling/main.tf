# TODO Implement step scaling as shown in https://tutorialsdojo.com/step-scaling-vs-simple-scaling-policies-in-amazon-ec2/
# OBS.: Creating these resources will create four cloudwatch alarms

resource "aws_appautoscaling_target" "autoscale_target" {
  max_capacity       = 5
  min_capacity       = 1
  resource_id        = "service/${var.ecs_cluster.name}/${var.ecs_service.name}"
  scalable_dimension = "ecs:service:DesiredCount"
  service_namespace  = "ecs"
}

resource "aws_appautoscaling_policy" "autoscale_by_memory" {
  name               = "${var.application_name}-memory"
  policy_type        = "TargetTrackingScaling"
  resource_id        = aws_appautoscaling_target.autoscale_target.resource_id
  scalable_dimension = aws_appautoscaling_target.autoscale_target.scalable_dimension
  service_namespace  = aws_appautoscaling_target.autoscale_target.service_namespace

  target_tracking_scaling_policy_configuration {
    predefined_metric_specification {
      predefined_metric_type = "ECSServiceAverageMemoryUtilization"
    }
    target_value = 80
  }
}

resource "aws_appautoscaling_policy" "autoscale_by_cpu" {
  name               = "${var.application_name}-cpu"
  policy_type        = "TargetTrackingScaling"
  resource_id        = aws_appautoscaling_target.autoscale_target.resource_id
  scalable_dimension = aws_appautoscaling_target.autoscale_target.scalable_dimension
  service_namespace  = aws_appautoscaling_target.autoscale_target.service_namespace

  target_tracking_scaling_policy_configuration {
    predefined_metric_specification {
      predefined_metric_type = "ECSServiceAverageCPUUtilization"
    }
    target_value = 60
  }
}