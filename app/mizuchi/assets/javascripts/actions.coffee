Constants = require('constants')

module.exports =
  createShow: (show)->
    @dispatch(Constants.CREATE_SHOW, {show})