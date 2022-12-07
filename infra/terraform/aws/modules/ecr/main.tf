resource "aws_ecr_repository" "repository" {
  name                 = "${var.application_name}-repository"
  image_tag_mutability = "MUTABLE"
}

resource "aws_ecr_repository_policy" "repository_policy" {
  repository = "${aws_ecr_repository.repository.name}_Policy"
  policy     = <<EOF
  {
    "Version": "2012-10-17",
    "Statement": [
      {
        "Sid": "adds full ecr access to the ${aws_ecr_repository.repository.name} repository",
        "Effect": "Allow",
        "Principal": {
          "AWS": [

          ]
        },
        "Action": [
          "ecr:BatchCheckLayerAvailability",
          "ecr:BatchGetImage",
          "ecr:CompleteLayerUpload",
          "ecr:GetDownloadUrlForLayer",
          "ecr:GetLifecyclePolicy",
          "ecr:InitiateLayerUpload",
          "ecr:PutImage",
          "ecr:UploadLayerPart"
        ]
      }
    ]
  }
  EOF
}

