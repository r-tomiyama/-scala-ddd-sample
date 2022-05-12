package domainModel.user

import scala.util.Try

case class User private (id: UserId, name: UserName) {
  def changeName(newName: String): Try[User] =
    Try(User(id, UserName(newName)))
}

object User {
  def apply(userId: String, userName: String): User = {
    User(UserId(userId), UserName(userName))
  }

  def from(userName: String): Try[User] = {
    val userId = UserId() // TODO: Guid生成ライブラリなどで生成する
    Try(User(userId, UserName(userName)))
  }
}
