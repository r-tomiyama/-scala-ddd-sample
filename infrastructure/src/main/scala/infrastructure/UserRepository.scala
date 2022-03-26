package infrastructure

import domainModel.User

import scala.util.Try

class UserRepository {
  def find(user: User): Option[User] = {
    // 永続化サービスアクセス
    Some(user)
  }

  def save(user: User): Try[User] = {
    // 永続化サービスアクセス
    Try(user)
  }
}
