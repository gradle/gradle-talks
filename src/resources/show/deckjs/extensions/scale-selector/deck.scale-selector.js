(function($, deck, undefined) {
  $d = $(document);
  var scaleSelectorDiv;
  var scaleSelectorSelect;

  $.extend(true, $[deck].defaults, {
    selectors: {
      scaleSelectorTarget: ".inner"
    },
    keys: {
      resolution: 82 // r
    },
    scaleSelector: {
      scales: {
        "1280x1024": 1,
        "1024x768": 0.8,
        "800x600": 0.625
      }
    }
  });

  $[deck]('extend', 'toggleScaleSelector', function() {
    var showing = !scaleSelectorDiv.is(":visible");
    scaleSelectorDiv.fadeToggle('fast');
    showing ? scaleSelectorSelect.focus() : scaleSelectorSelect.blur();
  });

  $[deck]('extend', 'scaleSlides', function(scale) {
    var opts = $[deck]('getOptions');
    var elements = $($.map($[deck]('getSlides'), function (i, val) { return i.get(0); }));
    var targetSelector = opts.selectors.scaleSelectorTarget;
    if (targetSelector) {
      elements = elements.find(targetSelector);
    };
    
    var transformValue = "scale(" + scale + ")";
    elements.css("transform", transformValue);
    elements.css("-ms-transform", transformValue);
    elements.css("-webkit-transform", transformValue);
    elements.css("-o-transform", transformValue);
    elements.css("-moz-transform", transformValue);
  });

  $d.bind('deck.init', function() {
    var opts = $[deck]('getOptions');
    var container = $[deck]('getContainer');

    scaleSelectorDiv = $("<div class='scale-selector'/>").appendTo(container);
    scaleSelectorSelect = $("<select name='scale-selector'/>").appendTo(scaleSelectorDiv);
    $.each(opts.scaleSelector.scales, function (name, scale) {
      var option = $("<option/>").appendTo(scaleSelectorSelect).val(scale).text(name);
      if (scale == 1) {
        option.attr("selected", "selected");
      };
    });

    scaleSelectorSelect.change(function() {
      $[deck]('scaleSlides', $(this).val());
      $[deck]('toggleScaleSelector');
    });

    /* Bind key events */
    var handler = function(e) {
      var modifier = e.altKey || e.ctrlKey || e.shiftKey || e.metaKey;
      var isKey = (e.which === opts.keys.resolution || $.inArray(e.which, opts.keys.resolution) > -1);
      var isEsc = e.keyCode == 27;
      if (!modifier && (isKey || (isEsc && scaleSelectorDiv.is(":visible")))) {
        $[deck]('toggleScaleSelector');
        e.preventDefault();
      }
    };
    
    $d.unbind('keydown.deck.scale-selector').bind('keydown.deck.scale-selector', handler);
    scaleSelectorSelect.unbind('keydown.deck.scale-selector').bind('keydown.deck.scale-selector', handler);
  });
})(jQuery, 'deck');
