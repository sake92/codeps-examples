package blog.shared

import blog.posts.PostsApi
import blog.users.UsersApi

trait BlogServices {
  def users: UsersApi
  def posts: PostsApi
}

class InMemoryBlogServices(val users: UsersApi, val posts: PostsApi) extends BlogServices
