React = require('react')
Fluxxor = require('fluxxor')
FluxMixin = Fluxxor.FluxMixin(React)
StoreWatchMixin = Fluxxor.StoreWatchMixin

ShowListing = require('components/show_listing')

module.exports = React.createClass
  mixins: [FluxMixin, StoreWatchMixin("ShowStore")]
  getStateFromFlux: ->
    flux = @getFlux()
    {shows: flux.store("ShowStore").getState().shows}
  render: ->
    R = React.DOM
    shows = @state.shows.map (show)->
      new ShowListing({show})
    R.ul null,
      shows