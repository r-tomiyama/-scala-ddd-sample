package domainModel

case class UserName private (value: String)

object UserName {
  def apply(value: String): UserName = {
    if (value.length < 3 || value.length > 20) {
      throw new RuntimeException("名前は3文字以上、20文字以下")
    }
    new UserName(value)
  }
}
