package blog

import blog.users.*
import blog.dashboard.AdminDashboard
import blog.shared.{BlogServices, InMemoryBlogServices}

@main def run(): Unit = {
  println("Hello, World!")
  val usersApi: UsersApi = new UsersApiImpl()
  val postsApi = new blog.posts.PostsApiImpl()
  val services: BlogServices = new InMemoryBlogServices(usersApi, postsApi)
  println(AdminDashboard.render(services))
}
