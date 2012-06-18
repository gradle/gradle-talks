import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import com.uwyn.jhighlight.renderer.XhtmlRendererFactory

transform {
    prependChild(new org.jsoup.nodes.DocumentType("html", "-//W3C//DTD XHTML 1.0 Strict//EN", "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd", ""))
}

transform {
    head().append("""
        <meta charset='utf-8'>
        <link rel="stylesheet" href="style/reset.less.css">
        <link rel="stylesheet" href="style/base.less.css">
        <link rel="stylesheet" href="style/code.less.css" /> 
    """)
}

// Wrap each section in a “section”.
transform {
    def isHeading = { it.tagName() ==~ /h[12]/ }

    def firstChild = body().child(0)
    assert isHeading(firstChild) : "first child of the body is expected to be a heading, is ${firstChild.tagName()}"

    def siblings = firstChild.siblingElements()

    def heading = firstChild
    def inSection = [firstChild]
    Element next = heading.nextElementSibling()
    while (true) {
        if (next == null || isHeading(next)) {
            def section = heading.before("<section class='slide'/>").previousElementSibling()
            Elements inSectionElements = new Elements(inSection)
            section.html(inSectionElements.outerHtml())
            inSectionElements.remove()

            if (next == null) {
                break
            } else {
                inSection = [next]
                heading = next
            }
        } else {
            inSection << next
        }

        next = next.nextElementSibling()
    }
}

// add a class to title slides
transform {
    body().select("section.slide").each { section ->
        if (section.child(0).tagName() == "h1") {
            section.addClass("title-slide")
            def children = section.children()
            children.remove()
            section.append("<div class='title-slide-content' />").child(0).html(children.outerHtml())
        }
    }
}

// Turn “notes” into an aside
transform {
    body().select("section.slide").each { slide ->
        def hr = slide.select("hr")
        if (hr) {
            hr = hr.first()
            def content = []
            def next = hr.nextElementSibling()
            while (next != null) {
                content << next
                next = next.nextElementSibling()
            }
            if (content) {
                def notes = hr.before("<aside class='notes' />").previousElementSibling()
                def contentElements = new Elements(content)
                notes.html(contentElements.outerHtml())
                contentElements.remove()
                hr.remove()
            }
        }
    }
}

// add an inner container to each slide so we can place it
transform {
    body().select("section.slide").each { slide ->
        def children = slide.children()
        children.remove()
        def inner = slide.prepend("<div class='inner' />").child(0)
        children.each { inner.appendChild(it) }
    }
}

// Syntax highlighting
transform {
    def codeTags = body().select("code")
    codeTags.each { code ->
        def text = code.text()
        def input = new ByteArrayInputStream(code.text().getBytes("utf-8"))
        def renderer = XhtmlRendererFactory.getRenderer("groovy")
        def out = new ByteArrayOutputStream()
        renderer.highlight("test", input, out, "utf-8", true)
        code.html(new String(out.toByteArray(), "utf-8"))
        code.select("br").remove()
        code.childNodes().findAll { it.nodeName().equals("#comment") }*.remove()
        code.html(code.html().trim())
        def parent = code.parent()
        if (parent.tagName() == "pre") {
            parent.addClass("code")
        }
    }
}
