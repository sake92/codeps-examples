package blog.dashboard

import blog.model.{PostId, UserId}
import blog.shared.BlogServices
import blog.users.User

/** Handles querying, formatting, moderation, and storage access for the admin UI. */
object AdminDashboard {

  def userSummaries(services: BlogServices): List[String] =
    services.users.getUsers().map(formatUser(services, _))

  def formatUser(services: BlogServices, user: User): String =
    val status = if services.isBanned(user.id) then "banned" else "active"
    s"${user.name} (#${user.id.value}) [$status]"

  def postSummaries(services: BlogServices): List[String] =
    services.posts.getPosts.map { post =>
      val author = services.users.getUser(post.authorId).map(_.name).getOrElse("unknown")
      s"${post.title} by $author"
    }

  def moderateUser(services: BlogServices, id: UserId, ban: Boolean): Unit =
    if ban then services.banUser(id) else ()

  def render(services: BlogServices): String =
    val users = userSummaries(services)
    val posts = postSummaries(services)
    s"${users.size} users, ${posts.size} posts\n${users.mkString("\n")}\n${posts.mkString("\n")}"
}
