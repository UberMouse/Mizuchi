React = require('react')
R = React.DOM

module.exports = React.createClass
  render: ->
    R.li null,
      R.p(null, @props.text1)