package crux;

final class Authors {
  // TODO: Add author information.
  static final Author[] all = {new Author("Sandra Wang", "14772372", "sandrw2"),
          new Author("Alan Chen", "16976197", "alanc15")};
}


final class Author {
  final String name;
  final String studentId;
  final String uciNetId;

  Author(String name, String studentId, String uciNetId) {
    this.name = name;
    this.studentId = studentId;
    this.uciNetId = uciNetId;
  }
}
