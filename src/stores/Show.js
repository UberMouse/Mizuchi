import Alt from 'my_alt'

class ShowStore {
  constuctor() {
    this.shows = [{canonicalTitle: 'Tengen Toppa Gurren Lagann', tvdbTitle: 'Gurren Lagann', tvdbId: 80096, season: 1}]
  }
}

export default Alt.createStore(ShowStore, 'ShowStore')
