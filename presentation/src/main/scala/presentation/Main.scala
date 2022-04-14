package presentation

import application.UserUsecase

import scala.io.StdIn
import scala.util.{Failure, Success}

object Main {
  val operations = List("create_user", "find_user", "update_user")

  def main(args: Array[String]): Unit = {
    println(s"行いたい操作を入力してください: ${operations.mkString(",")}")
    val input_operation = StdIn.readLine()

    do_operation(input_operation)
  }

  def do_operation(operation: String): Unit = {
    val message = operation match {
      case "create_user" => create_user
      case "find_user"   => find_user
      case "update_user" => update_user
      case _             => do_nothing
    }
    println(message)

    if (operations.contains(operation)) {
      println(s"操作を続けますか？ 続ける場合は行いたい操作を入力してください: ${operations.mkString(",")}")
      val input_next_operation = StdIn.readLine()
      do_operation(input_next_operation)
    }
  }

  def create_user: String = {
    println("ユーザー名を入力してください")

    val input = StdIn.readLine()
    UserUsecase.create(input) match {
      case Success(u) => s"ユーザーを作成しました（id: ${u.id}, name: ${u.name}）"
      case Failure(e) => s"ユーザーを作成できませんでした（${e.getMessage}）"
    }
  }

  def find_user: String = {
    println("照会したいユーザーのIDを入力してください")

    val input = StdIn.readLine()
    UserUsecase.find(input) match {
      case Success(u) => s"id: ${u.id}, name: ${u.name}"
      case Failure(e) => s"ユーザは取得できませんでした（${e.getMessage}）"
    }
  }

  def update_user: String = {
    println("更新したいユーザーのIDと名前を入力してください（半角スペース区切り）")

    val inputs = StdIn.readLine().split(" ")
    if (inputs.size == 2) {
      UserUsecase.update(inputs(0), inputs(1)) match {
        case Success(u) => s"更新しました（id: ${u.id}, name: ${u.name}）"
        case Failure(e) => s"ユーザを更新できませんでした（${e.getMessage}）"
      }
    } else {
      "入力形式が間違っています"
    }
  }

  def do_nothing: String = {
    "終了します"
  }

  // TODO: サークル機能を作る

}
