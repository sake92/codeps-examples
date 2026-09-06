package blog

import blog.users.*
import blog.dashboard.{PostCatalog, UserDirectory}
import blog.shared.{BlogServices, InMemoryBlogServices}

@main def run(): Unit = {
  println("Hello, World!")
  val usersApi: UsersApi = new UsersApiImpl()
  val postsApi = new blog.posts.PostsApiImpl()
  val services: BlogServices = new InMemoryBlogServices(usersApi, postsApi)
  val users = UserDirectory.summaries(services)
  val posts = PostCatalog.summaries(services)
  println(s"${users.size} users, ${posts.size} posts\n${users.mkString("\n")}\n${posts.mkString("\n")}")
}
