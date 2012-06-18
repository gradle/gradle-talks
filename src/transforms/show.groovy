transform {
    body().addClass("deck-container")
    head().append("""
        <title>Gradle</title>

        <script src="deckjs/jquery-1.7.min.js"></script>
        <script src="deckjs/modernizr.custom.js"></script>
        <script src="deckjs/core/deck.core.js"></script>

        <link rel="stylesheet" href="deckjs/core/deck.core.css">
        <link rel="stylesheet" href="deckjs/themes/transition/horizontal-slide.css">

        <script src="deckjs/extensions/hash/deck.hash.js"></script>
        <script src="deckjs/extensions/notes/deck.notes.js"></script>
        <script src="deckjs/extensions/clone/deck.clone.js"></script>

        <script src="deckjs/extensions/scale-selector/deck.scale-selector.js"></script>
        <link rel="stylesheet" href="deckjs/extensions/scale-selector/deck.scale-selector.css">

        <link rel="stylesheet" href="style/slideshow.less.css" /> 

        <script src="init.js"></script>
    """)
}


// add notes container
transform {
    body().append("""
        <div class="deck-notes">
            <div class="deck-notes-header">Slide Notes…</div>
            <div class="deck-notes-container"></div>
        </div>
    """)
}
