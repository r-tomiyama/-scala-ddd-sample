package domainModel.user

import scala.util.Try

trait IUserRepository {
  def find(name: UserName): Option[User]

  def find(user: UserId): Option[User]

  def save(user: User): Try[User]

  def delete(user: User): Try[Unit]
}
