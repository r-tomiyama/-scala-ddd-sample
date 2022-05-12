package infrastructure

import domainModel.user.{User, UserId, UserName, IUserRepository}

import scala.util.Try

class UserRepositoryImpl extends IUserRepository {
  // TODO: ID採番方法をDB依存に変える（ファクトリ）
  // TODO: トランザクションを取り入れる
  def find(userName: UserName): Option[User] =
    // 永続化サービスアクセス
    if (userName.value == "すでにある名前")
      Some(User("1", userName.value))
    else
      None

  def find(userId: UserId): Option[User] =
    // 永続化サービスアクセス
    if (userId.value == "1")
      Some(User("1", "すでにある名前"))
    else
      None

  def save(user: User): Try[User] =
    // 永続化サービスアクセス
    Try(user)

  def delete(user: User): Try[Unit] =
    // 永続化サービスアクセス
    Try(Unit)
}
