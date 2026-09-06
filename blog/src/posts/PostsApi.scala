package blog.posts

import blog.model.{Post, PostId}
import blog.users.User

trait PostsApi {
  def getPosts: List[Post]
  def getPost(id: PostId): Option[Post]
  def postsBy(author: User): List[Post] = getPosts.filter(_.authorId == author.id)
}

class PostsApiImpl extends PostsApi {
  private val posts = List(
    Post(PostId(1), blog.model.UserId(1), "First Post", "This is the content of the first post."),
    Post(PostId(2), blog.model.UserId(2), "Second Post", "This is the content of the second post.")
  )

  override def getPosts: List[Post] = posts

  override def getPost(id: PostId): Option[Post] = posts.find(_.id == id)
}
