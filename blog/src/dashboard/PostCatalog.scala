package blog.dashboard

import blog.shared.BlogServices

/** Read-only view over posts for the admin UI. */
object PostCatalog {
  def summaries(services: BlogServices): List[String] =
    services.posts.getPosts.map { post =>
      val author = services.users.getUser(post.authorId).map(_.name).getOrElse("unknown")
      s"${post.title} by $author"
    }
}
