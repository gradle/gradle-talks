describe("The book", function() {
  it("can be created", function() {
    var book = new Book("Building & Testing with Gradle", "O'Reilly Media")

    expect(book.title).toBe("Building & Testing with Gradle");
  });
});