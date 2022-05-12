package domainModel.circle

import domainModel.user.User

import scala.util.Try

case class Circle private (
    id: CircleId,
    name: CircleName,
    owner: User,
    members: List[User]
) {
  def isFull: Boolean =
    members.size >= (30-1)

  def addUser(user: User): Try[Circle] =
  Try {
    if(isFull) {
      new RuntimeException("サークルの人数はオーナー含めて29人まで")
    }
    Circle(id, name, owner, members :+ user)
  }
}

object Circle {
  def from(name: String, user: User): Try[Circle] = {
    val id = CircleId("id") // TODO: Guid生成ライブラリなどで生成する
    Try(Circle(id, CircleName(name), user, List()))
  }
}
