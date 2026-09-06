package blog.users

import blog.model.UserId

trait UsersApi {
  def getUsers(): List[User]
  def getUser(id: UserId): Option[User] = getUsers().find(_.id == id)
  def register(name: String): User
}

class UsersApiImpl extends UsersApi {
  private var users = List(User(UserId(1), "Alice"), User(UserId(2), "Bob"))

  override def getUsers(): List[User] = users

  override def register(name: String): User =
    val nextId = users.map(_.id.value).maxOption.getOrElse(0) + 1
    val user = User(UserId(nextId), name)
    users = users :+ user
    user
}

/** Bulk imports users through the public API, one registration at a time. */
object BulkUserImport {
  def importUsers(api: UsersApi, names: List[String]): List[User] =
    names.map(api.register)
}
