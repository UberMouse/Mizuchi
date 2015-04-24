import T from 'tcomb-react'

export default T.struct({
  canonicalTitle: T.Str,
  tvdbTitle: T.Str,
  tvdbId: T.Num,
  tvdbSeason: T.Num
})
