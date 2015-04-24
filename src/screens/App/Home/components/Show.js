import React from 'react'
import t from 'tcomb-react'

const ShowProps = t.struct({
  show: Anime
})

class Show extends React.Component {
  render() {
    let a = this.props.show
    return (
      <tr>
        <td>{a.canonicalTitle}</td>
        <td>{a.tvdbId}</td>
        <td>{a.season}</td>
      </tr>
    )
  }
}
Show.propTypes = t.react.toPropTypes(ShowProps)

export default Show
