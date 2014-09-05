React = require('react')
Nav = require('components/nav')

module.exports = React.createClass
  render: ->
    R = React.DOM
    R.div null,
      new Nav(null),
      @props.view