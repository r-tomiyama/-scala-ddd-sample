package infrastructure

import domainModel.User

import scala.util.Try

class UserRepository {
  // TODO: ID採番方法をDB依存に変える（ファクトリ）
  // TODO: トランザクションを取り入れる
  def find(user: User): Option[User] = {
    // 永続化サービスアクセス
    if (user.name.value == "すでにある名前") {
      None
    } else {
      Some(user)
    }
  }

  def save(user: User): Try[User] = {
    // 永続化サービスアクセス
    Try(user)
  }
}
