package domainModel

import scala.util.Try

case class User private (id: UserId, name: UserName) {
  def changeName(newName: String): Try[User] =
    Try(User(id, UserName(newName)))
}

object User {
  def apply(userId: String, userName: String): User = {
    new User(UserId(userId), UserName(userName))
  }

  def from(userName: String): Try[User] =
    Try(new User(UserId(), UserName(userName)))
}
