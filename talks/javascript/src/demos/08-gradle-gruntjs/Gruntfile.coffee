module.exports = (grunt) ->
  grunt.initConfig
    coffee:
      all:
        options:
          bare: true
        files: [
          src: ["./src/main/coffee/**/*.coffee"]
          dest: "./src/ratpack/public/scripts/demo.js"
        ]

    watch:
      scripts:
        files: ["./src/main/coffee/**/*.coffee"]
        tasks: ["coffee"]
        options:
          nospawn: true

  grunt.loadNpmTasks "grunt-contrib-coffee"
  grunt.loadNpmTasks "grunt-contrib-watch"
