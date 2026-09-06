package blog.model

case class UserId(value: Int)
case class PostId(value: Int)

case class Post(id: PostId, authorId: UserId, title: String, content: String)
