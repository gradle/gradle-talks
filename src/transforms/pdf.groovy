transform {
    head().append("""
        <link rel="stylesheet" href="style/pdf.less.css" /> 
    """)
}

transform {
    def i = 1
    body().select("section.slide").each {
        it.addClass(i++ % 2 == 0 ? "even" : "odd")
    }
}