package blog.posts

import blog.users.User

case class Post(id: Int, title: String, content: String, author: User)
