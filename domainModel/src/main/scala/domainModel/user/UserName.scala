package domainModel.user

case class UserName private (value: String)

object UserName {
  def apply(value: String): UserName = {
    require(value.length >= 3 && value.length <= 20)
    new UserName(value)
  }
}
