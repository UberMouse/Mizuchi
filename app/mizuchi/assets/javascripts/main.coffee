React = require('react')
$ = require('jquery')
R = React.DOM
Show = require('./ShowListing.jsx.coffee')

Index = React.createClass
  render: ->
    items = [["1", "2"], ["a", "b"], ["b", "a"]].map (pair)->
      new Show({text1: pair[0], text2: pair[1]})
    R.div(null, items)

$ ->
  React.renderComponent(new Index(null), document.getElementById("app"))