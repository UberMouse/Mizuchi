package database

import scaldi.Module

class DatabaseModule extends Module {
  bind [ShowDatabase] to injected [SqliteDatabase]
}