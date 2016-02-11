class CreateAnimeTable < ActiveRecord::Migration
  def change
    create_table :anime do |t|
      t.string :hummingbird_slug
      t.string :tvdb_id
      t.integer :season
      t.boolean :absolute_ordering

      t.timestamps
    end
  end
end
