import React from 'react'
import {Well} from 'react-bootstrap'
import {Table, AltContainer} from 'alt'
import t from 'tcomb-react'
import Anime from 'models/Anime'
import ShowStore from 'stores/Show'
import Shows from './components/Shows'

export default class Index extends React.Component {
  render() {
    return (
      <Well>
        <AltContainer 
          stores={
            {
              Shows: ShowStore
            }
          }
        >
          <Shows />
        </AltContainer>
      </Well>
    )
  }
}
