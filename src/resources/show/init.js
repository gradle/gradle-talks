$(function() {
  
  // override the keyhandler to ignore key presses with modifiers
  $(document).bind('deck.init', function() {
      var opts = $["deck"]('getOptions');
      
      $d.unbind('keydown.deckclone').bind('keydown.deckclone', function(e) {
        var modifier = e.altKey || e.ctrlKey || e.shiftKey || e.metaKey;
        if (!modifier && (e.which === opts.keys.clone || $.inArray(e.which, opts.keys.clone) > -1)) {
          $["deck"]('addClone');
          e.preventDefault();
        }
      });
  });

  // override the notes handler because it's bugged
  $(document).bind('deck.change', function(e, from, to) {
      var slideTo = $["deck"]('getSlide', to);
      var notes = slideTo.find(".notes");
      var notesContainer = $("." + $["deck"]('getOptions').classes.notesContainer);
      notesContainer.html(notes.length > 0 ? notes.html() : "");
  });
  
  $.deck('.slide', {
    selectors: {
      container: 'body'
    },
    scaleSelector: {
      scales: {
        "1280x1024": 1.25,
        "1024x768": 1,
        "800x600": 0.78125
      }
    }
  });
});