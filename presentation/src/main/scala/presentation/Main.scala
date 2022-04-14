package presentation

import application.UserUsecase

import scala.io.StdIn
import scala.util.{Failure, Success}

object Main {
  val operations = List("create_user", "find_user")

  def main(args: Array[String]): Unit = {
    println(s"行いたい操作を入力してください: ${operations.mkString(",")}")
    val input_operation = StdIn.readLine()

    do_operation(input_operation)
  }

  def do_operation(operation: String): Unit = {
    operation match {
      case "create_user" => create_user
      case "find_user"   => find_user
      case _             => do_nothing
    }

    if (operations.contains(operation)) {
      println(s"操作を続けますか？ 続ける場合は行いたい操作を入力してください: ${operations.mkString(",")}")
      val input_next_operation = StdIn.readLine()
      do_operation(input_next_operation)
    }
  }

  def create_user: Unit = {
    println("ユーザー名を入力してください")

    val input = StdIn.readLine()
    val result = UserUsecase.create(input)
    println(result)
  }

  def find_user: Unit = {
    println("照会したいユーザーのIDを入力してください")

    val input = StdIn.readLine()
    val message = UserUsecase.find(input) match {
      case Success(u) => s"id: ${u.id}, name: ${u.name}"
      case Failure(e) => s"ユーザは取得できませんでした（${e.getMessage}）"
    }
    println(message)
  }

  def do_nothing: Unit = {
    println("終了します")
  }

  // TODO: サークル機能を作る

}
