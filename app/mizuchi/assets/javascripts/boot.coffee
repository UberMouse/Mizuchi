React = require('react')
$ = require('jquery')
Fluxxor = require('fluxxor')
ShowStore = require('stores/show_store')
actions = require('actions')
router = require('rrouter')
Routes = router.Routes
Route = router.Route

window.React = React

shows = [{
  name: 'Akame ga Kill!'
}, {
  name: 'Gekkan Shoujo Nozaki-kun'
}];

stores =
  ShowStore: new ShowStore({shows: shows})
flux = new Fluxxor.Flux(stores, actions)

Index = require('pages/index')
CreateShow = require('pages/create_show')
routes = (
  new Routes null,
    new Route({path: '/', view: Index, flux, name: 'index'}),
    new Route({path: '/create_show', view: CreateShow, flux, name: 'create_show'})
)

Layout = require('components/layout')

$ ->
  router.start routes, (view)->
    React.renderComponent(new Layout({view}), document.body)