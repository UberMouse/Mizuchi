React = require('react')
R = React.DOM

module.exports = React.createClass
  render: ->
    R.li null, @props.show.name