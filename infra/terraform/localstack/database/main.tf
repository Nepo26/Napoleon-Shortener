resource "aws_dynamodb_table" "ShortLinkTable" {
  name = "ShortLink"
  read_capacity = 10
  write_capacity = 5
  hash_key = "randomId"

  attribute {
    name = "randomId"
    type = "S"
  }

}