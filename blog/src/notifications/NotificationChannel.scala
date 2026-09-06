package blog.notifications

/** Placeholder for upcoming notification delivery; not wired into the app yet. */
trait NotificationChannel {
  def send(userId: Int, message: String): Unit
}

class LoggingNotificationChannel extends NotificationChannel {
  override def send(userId: Int, message: String): Unit =
    println(s"[notify] user=$userId: $message")
}
