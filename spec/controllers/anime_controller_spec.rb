require 'rails_helper'

RSpec.describe 'AnimeController' do

  describe "#create" do
    context "valid data" do
      let(:anime) {
        {
          tvdb_id: '267441',
          tvdb_title: 'The Devil Is a Part-Timer!',
          canonical_title: 'The Devil is a Part-Timer!',
          hummingbird_slug: 'the-devil-is-a-part-timer'
        }
      }
      let(:event) {create_event('anime.create', anime)}

      it 'trigger a success message' do
        expect(event.dispatch).to trigger_success_message
      end
    
      it 'adds an anime to the database' do
        event.dispatch

        expect(Anime.last.to_json).to eq(anime)
      end
    end
  end
end
