import React from 'react'
import {Route, DefaultRoute} from 'react-router'
import App from 'screens/App/index'
import Home from 'screens/App/Home/index'

export default (
  <Route Handler={App} path="/">
    <DefaultRoute Handler={Home} />
  </Route>
)
