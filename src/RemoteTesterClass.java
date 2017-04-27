import com.example.b2c_core.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by alex on 4/26/17.
 */
public class RemoteTesterClass
{
    public void draftTiles(DraftTransferObject dto) throws JsonProcessingException
    {
        PostDraftTransferObject pdto = PostDraftTransferObject.create(dto.availableTiles[0], dto.availableTiles[1]);
        ObjectMapper mapper = new ObjectMapper();
        App.getSocket().emit(Routes.ToServer.DRAFT_COMPLETE, mapper.writeValueAsString(pdto));
    }

    private void placeTile(User player, BuildingType tile, SharedCity city, boolean isLeftCity)
    {
        City c = city.getCity();
        for (int y = 0; y < 4; y++)
        {
            for (int x = 0; x < 4; x++)
            {
                    /* TODO: return placement data.
                    if (_mngr.placeTile(player, tile, isLeftCity, x, y))
                    {
                        return;
                    }*/
            }
        }
    }

    public void placeTile(PlaceTransferObject pto)
    {
        BuildingType[] tiles = pto.tiles.get(pto.currentUser);
        //placeTile(tiles[0]);
    }
}
