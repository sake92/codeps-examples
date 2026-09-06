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

/** Bulk imports users directly by mutating the public collection. */
object BulkUserImport {
  def importUsers(api: UsersApiImpl, names: List[String]): Unit =
    val startId = api.users.map(_.id.value).maxOption.getOrElse(0) + 1
    val imported = names.zipWithIndex.map { case (name, i) => User(UserId(startId + i), name) }
    api.users = api.users ++ imported
}
