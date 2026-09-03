package blog.users

trait UsersApi {
  def getUsers(): List[User]
  def getUser(id: Int): Option[User] = getUsers().find(_.id == id)
}

class UsersApiImpl extends UsersApi {
  var users = List(User(1, "Alice"), User(2, "Bob"))
  override def getUsers(): List[User] = users
}
