package blog

import blog.users.*
import blog.dashboard.Dashboard
import blog.shared.{BlogServices, InMemoryBlogServices}

@main def run(): Unit = {
  println("Hello, World!")
  val usersApi: UsersApi = new UsersApiImpl()
  val postsApi = new blog.posts.PostsApiImpl()
  val services: BlogServices = new InMemoryBlogServices(usersApi, postsApi)
  println(Dashboard.render(services))
}
