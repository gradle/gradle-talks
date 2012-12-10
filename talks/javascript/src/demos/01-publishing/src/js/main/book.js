var Book = function(title, publisher) {
  this.title = title;
  this.publisher = publisher;
};

Book.prototype.toString = function() {
  return this.title + " (published by " + this.publisher + ")";
};