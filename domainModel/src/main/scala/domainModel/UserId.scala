package domainModel

import scala.util.Try

case class UserId private (value: String)

object UserId {
  def apply(): UserId =
    new UserId("id")

  def apply(value: String): UserId = {
    if (value.length < 1) {
      throw new RuntimeException("IDは1文字以上")
    }
    new UserId(value)
  }

  def from(value: String): Try[UserId] =
    Try(UserId(value))

}
