package blog.dashboard

import blog.model.UserId
import blog.shared.BlogServices

/** Moderation actions available to admins. */
object Moderation {
  def banUser(services: BlogServices, id: UserId): Unit = services.banUser(id)
}
