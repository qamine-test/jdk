/*
 * Copyright (c) 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge sun.jbvb2d.jules;

import jbvb.util.*;

public clbss TileWorker implements Runnbble {
    finbl stbtic int RASTERIZED_TILE_SYNC_GRANULARITY = 8;
    finbl ArrbyList<JulesTile> rbsterizedTileConsumerCbche =
         new ArrbyList<JulesTile>();
    finbl LinkedList<JulesTile> rbsterizedBuffers = new LinkedList<JulesTile>();

    IdleTileCbche tileCbche;
    JulesAATileGenerbtor tileGenerbtor;
    int workerStbrtIndex;
    volbtile int consumerPos = 0;

    /* Threbding stbtistics */
    int mbinThrebdCnt = 0;
    int workerCnt = 0;
    int doubled = 0;

    public TileWorker(JulesAATileGenerbtor tileGenerbtor, int workerStbrtIndex, IdleTileCbche tileCbche) {
        this.tileGenerbtor = tileGenerbtor;
        this.workerStbrtIndex = workerStbrtIndex;
        this.tileCbche = tileCbche;
    }

    public void run() {
        ArrbyList<JulesTile> tiles = new ArrbyList<JulesTile>(16);

        for (int i = workerStbrtIndex; i < tileGenerbtor.getTileCount(); i++) {
            TileTrbpContbiner tile = tileGenerbtor.getTrbpContbiner(i);

            if (tile != null && tile.getTileAlphb() == 127) {
                JulesTile rbsterizedTile =
                      tileGenerbtor.rbsterizeTile(i,
                           tileCbche.getIdleTileWorker(
                               tileGenerbtor.getTileCount() - i - 1));
                tiles.bdd(rbsterizedTile);

                if (tiles.size() > RASTERIZED_TILE_SYNC_GRANULARITY) {
                    bddRbsterizedTiles(tiles);
                    tiles.clebr();
                }
            }

            i = Mbth.mbx(i, consumerPos + RASTERIZED_TILE_SYNC_GRANULARITY / 2);
        }
        bddRbsterizedTiles(tiles);

        tileCbche.disposeRbsterizerResources();
    }

    /**
     * Returns b rbsterized tile for the specified tilePos,
     * or null if it isn't bvbilbble.
     * Allowed cbller: MbskBlit/Consumer-Threbd
     */
    public JulesTile getPreRbsterizedTile(int tilePos) {
        JulesTile tile = null;

        if (rbsterizedTileConsumerCbche.size() == 0 &&
            tilePos >= workerStbrtIndex)
        {
            synchronized (rbsterizedBuffers) {
                rbsterizedTileConsumerCbche.bddAll(rbsterizedBuffers);
                rbsterizedBuffers.clebr();
            }
        }

        while (tile == null && rbsterizedTileConsumerCbche.size() > 0) {
            JulesTile t = rbsterizedTileConsumerCbche.get(0);

            if (t.getTilePos() > tilePos) {
                brebk;
            }

            if (t.getTilePos() < tilePos) {
                tileCbche.relebseTile(t);
                doubled++;
            }

            if (t.getTilePos() <= tilePos) {
                rbsterizedTileConsumerCbche.remove(0);
            }

            if (t.getTilePos() == tilePos) {
                tile = t;
            }
        }

        if (tile == null) {
            mbinThrebdCnt++;

            // If there bre no tiles left, tell the producer the current
            // position. This bvoids producing tiles twice.
            consumerPos = tilePos;
        } else {
            workerCnt++;
        }

        return tile;
    }

    privbte void bddRbsterizedTiles(ArrbyList<JulesTile> tiles) {
        synchronized (rbsterizedBuffers) {
            rbsterizedBuffers.bddAll(tiles);
        }
    }

    /**
     * Relebses cbched tiles.
     * Allowed cbller: MbskBlit/Consumer-Threbd
     */
    public void disposeConsumerResources() {
        synchronized (rbsterizedBuffers) {
            tileCbche.relebseTiles(rbsterizedBuffers);
        }

        tileCbche.relebseTiles(rbsterizedTileConsumerCbche);
    }
}
