package domainModel.circle

import domainModel.user.UserId

import scala.util.Try

trait ICircleFactory {

  def from(name: String, ownerId: UserId): Try[Circle]

}
