package blog.dashboard

import blog.shared.BlogServices

/** Read-only view over users for the admin UI. */
object UserDirectory {
  def summaries(services: BlogServices): List[String] =
    services.users.getUsers().map { user =>
      val status = if services.isBanned(user.id) then "banned" else "active"
      s"${user.name} (#${user.id.value}) [$status]"
    }
}
