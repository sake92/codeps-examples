package blog.users

import blog.model.UserId

trait UsersApi {
  def getUsers(): List[User]
  def getUser(id: UserId): Option[User] = getUsers().find(_.id == id)
}

class UsersApiImpl extends UsersApi {
  var users = List(User(UserId(1), "Alice"), User(UserId(2), "Bob"))
  override def getUsers(): List[User] = users
}
