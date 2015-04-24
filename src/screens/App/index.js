import React from 'react'
import {RouteHandler} from 'react-router'
import {Well} from 'react-bootstrap'

export default class Index extends React.Component {
  render() {
    return (
      <Well>
        <RouteHandler />
      </Well>
    )
  }
}
