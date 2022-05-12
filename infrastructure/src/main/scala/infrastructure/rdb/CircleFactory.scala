package infrastructure.rdb

import domainModel.circle.{Circle, CircleId, CircleName, ICircleFactory}
import domainModel.user.UserId

import scala.util.Try

trait CircleFactory extends ICircleFactory {

  def from(name: String, ownerId: UserId): Try[Circle] = {
    val id = CircleId("id") // TODO: シーケンスを利用して生成する
    Try(Circle(id, CircleName(name), ownerId, List()))
  }

}
