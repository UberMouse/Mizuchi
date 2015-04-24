import React from 'react'
import T from 'tcomb-react'
import Anime from 'models/Anime'

const ShowsProps = T.struct({
  shows: T.list(Anime)
})

class Shows extends React.Component {
  render() {
    return (
      <Table striped bordered condensed hover>
        <thead>
          <tr>
            <td>Canonical Title</td>
            <td>TVDB Id</td>
            <td>Season</td>
          </tr>
        </thead>
        <tbody>
          {this.props.shows.map((show)=> <Show show={show} />)}
        </tbody>
      </Table>
    );
  }
}
Shows.propTypes = T.react.toPropTypes(ShowsProps)

export default Shows
