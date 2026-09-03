package blog

import blog.users.*

@main def run(): Unit = {
  println("Hello, World!")
  val usersApi: UsersApi = new UsersApiImpl()
  println(usersApi.getUsers())
}

