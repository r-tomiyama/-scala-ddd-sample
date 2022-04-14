package application.dto

case class User(id: String, name: String)

object User {
  def from(user: domainModel.User): User =
    User(user.id.value, user.name.value)
}
