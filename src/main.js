import React from 'react'
import Router from 'react-router'
import routes from 'config/routes';

export default {
  init() {
    Router.run(routes, function(Handler) {
      React.render(<Handler />, document.getElementById('mount'));
    });
  }
}
