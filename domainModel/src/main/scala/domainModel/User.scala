package domainModel

case class User private (id: UserId, name: UserName) {
  def changeName(newName: String): User = {
    new User(id, UserName(newName))
  }
}

object User {
  def apply(userName: String): User = {
    new User(UserId(), UserName(userName))
  }

  def apply(userId: String, userName: String): User = {
    new User(UserId(userId), UserName(userName))
  }
}
