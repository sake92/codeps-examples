package blog.dashboard

import blog.shared.BlogServices

object Dashboard {
  def render(services: BlogServices): String =
    val users = services.users.getUsers()
    val postCount = services.posts.getPosts.size
    s"${users.size} users, $postCount posts"
}
