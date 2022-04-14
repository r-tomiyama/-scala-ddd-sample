package infrastructure

import domainModel.{User, UserId}

import scala.util.Try

trait UserRepository {
  def find(name: String): Option[User]
  def find(user: UserId): Option[User]
  def save(user: User): Try[User]
}

class UserRepositoryImpl extends UserRepository {
  // TODO: ID採番方法をDB依存に変える（ファクトリ）
  // TODO: トランザクションを取り入れる
  def find(name: String): Option[User] =
    // 永続化サービスアクセス
    if (name == "すでにある名前")
      Some(User("1", name))
    else
      None

  def find(userId: UserId) =
    if (userId.value == "1")
      Some(User("1", "すでにある名前"))
    else
      None

  def save(user: User): Try[User] =
    // 永続化サービスアクセス
    Try(user)
}
