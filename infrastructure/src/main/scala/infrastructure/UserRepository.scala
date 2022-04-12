package infrastructure

import domainModel.User

import scala.util.Try
//trait UserRepository {
//  def find
//}
class UserRepository {
  // TODO: ID採番方法をDB依存に変える（ファクトリ）
  // TODO: トランザクションを取り入れる
  def find(user: User): Option[User] =
    // 永続化サービスアクセス
    if (user.name.value == "すでにある名前")
      Some(user)
    else
    None

  def save(user: User): Try[User] =
    // 永続化サービスアクセス
    Try(user)
}
