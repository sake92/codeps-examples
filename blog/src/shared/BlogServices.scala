package blog.shared

import blog.model.UserId
import blog.posts.PostsApi
import blog.users.UsersApi

trait BlogServices {
  def users: UsersApi
  def posts: PostsApi

  def banUser(id: UserId): Unit
  def isBanned(id: UserId): Boolean
}

class InMemoryBlogServices(val users: UsersApi, val posts: PostsApi) extends BlogServices {
  private var bannedUsers: Set[UserId] = Set.empty

  override def banUser(id: UserId): Unit = bannedUsers = bannedUsers + id
  override def isBanned(id: UserId): Boolean = bannedUsers.contains(id)
}
