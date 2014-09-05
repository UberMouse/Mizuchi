React = require('react')
Link = require('rrouter').Link

module.exports = React.createClass
  render: ->
    React.DOM.div null,
      new Link({to: '/index'}, 'Index'),
      new Link({to: '/create_show'}, 'Create Show')