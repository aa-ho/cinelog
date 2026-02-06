package de.travelinski.utils.dummy

import de.travelinski.data.User

fun dummyUser1() = User(
    uuid = "abcd123",
    avatarUrl = "https://media0.faz.net/image/w1240/d5a32d2f6d6c/w2872h1616x128y384/201807/1.5800565/ralph-dommermuth.webp",
    name = "Christopher"
)

fun dummyUser2() = User(uuid = "abcd1234", name = "Charlie")
fun dummyUser3() = User(
    uuid = "abcd12345",
    name = "Benjamin",
    avatarUrl = "https://cdn.fedweb.org/cache/fed-1/1/512px-Benjamin_Netanyahu_2018_698027_resize_990__1_.jpg?v=1748310076"
)

fun dummyUser4() = User(
    uuid = "abcd12346",
    name = "Sydney",
    avatarUrl = "https://i.scdn.co/image/ab67616100005174140df32415d5a8a1e81ce0b3"
)

fun dummyUser5() = User(
    uuid = "abcd12347",
    name = "Charles",
    avatarUrl = "https://api.ardmediathek.de/image-service/images/urn:ard:image:0d6331add06550bd?w=448&ch=63f7b75c03e37afb"
)

fun dummyCurrentUser() = User(
    uuid = "abcd12348",
    name = "Michael",
    avatarUrl = "https://www.tagesspiegel.de/images/11214103/alternates/BASE_21_9_W1000/1707977469000/file-photo-jeffrey-epstein-appears-in-a-photo-taken-for-the-ny-division-of-criminal-justice-services-sex-offender-registry.jpeg"
)