package blog.posts

import blog.users.{User, UsersApi}

trait PostsApi {
  def getPosts: List[Post]
  def getPost(id: Int): Option[Post]
  def postsBy(author: User): List[Post] = getPosts.filter(_.authorId == author.id)
}

class PostsApiImpl extends PostsApi {
  private val posts = List(
    Post(1, 1, "First Post", "This is the content of the first post."),
    Post(2, 2, "Second Post", "This is the content of the second post.")
  )

  override def getPosts: List[Post] = posts

  override def getPost(id: Int): Option[Post] = posts.find(_.id == id)
}
