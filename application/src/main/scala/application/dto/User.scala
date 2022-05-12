package application.dto

case class User(id: String, name: String)

object User {
  def from(user: domainModel.user.User): User =
    User(user.id.value, user.name.value)
}
