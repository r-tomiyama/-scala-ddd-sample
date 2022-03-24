package application

import domain.User
import infrastructure._

object UserCreate {
  def main: Unit = {
    val user = User("名前")
    UserRepository.main
    println("ユーザ作ったよ")
  }
}
