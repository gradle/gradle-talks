load('lib/envjs.rhino.js');

Envjs.scriptTypes['text/javascript'] = true;

var specFile;

for (i = 0; i < arguments.length; i++) {
    specFile = arguments[i];
    
    console.log("Loading: " + specFile);
    
    window.location = specFile;
}
