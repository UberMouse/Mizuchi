class CreateAnime < ActiveRecord::Migration
  def change
    create_table :anime do |t|
      t.string :tvdb_id
      t.string :hummingbird_slug
      t.string :canonical_title
      t.string :tvdb_title

      t.timestamps null: false
    end
  end
end
