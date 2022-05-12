package domainModel.circle

import domainModel.user.UserId

import scala.util.Try

case class Circle private (
    id: CircleId,
    name: CircleName,
    ownerId: UserId,
    memberIds: List[UserId]
) {
  def isFull: Boolean =
    memberIds.size >= (30 - 1)

  def addUser(userId: UserId): Try[Circle] =
    Try {
      if (isFull) {
        new RuntimeException("サークルの人数はオーナー含めて29人まで")
      }
      Circle(id, name, ownerId, memberIds :+ userId)
    }
}
