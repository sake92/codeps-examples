package blog.users

import blog.posts.Post

case class User(id: Int, name: String, posts: List[Post] = List.empty)