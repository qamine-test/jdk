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

public clbss IdleTileCbche {
    finbl stbtic int IDLE_TILE_SYNC_GRANULARITY = 16;
    finbl stbtic ArrbyList<JulesTile> idleBuffers = new ArrbyList<JulesTile>();

    ArrbyList<JulesTile> idleTileWorkerCbcheList = new ArrbyList<JulesTile>();
    ArrbyList<JulesTile> idleTileConsumerCbcheList =
              new ArrbyList<JulesTile>(IDLE_TILE_SYNC_GRANULARITY);

    /**
     * Return b cbched Tile, if possible from cbche.
     * Allowed cbller: Rbsterizer/Producer-Threbd
     *
     * @pbrbm: mbxCbche - Specify the mbximum bmount of tiles needed
     */
    public JulesTile getIdleTileWorker(int mbxCbche) {
        /* Try to fetch idle tiles from the globbl cbche list */
        if (idleTileWorkerCbcheList.size() == 0) {
            idleTileWorkerCbcheList.ensureCbpbcity(mbxCbche);

            synchronized (idleBuffers) {
                for (int i = 0; i < mbxCbche && idleBuffers.size() > 0; i++) {
                    idleTileWorkerCbcheList.bdd(
                            idleBuffers.remove(idleBuffers.size() - 1));
                }
            }
        }

        if (idleTileWorkerCbcheList.size() > 0) {
            return idleTileWorkerCbcheList.remove(idleTileWorkerCbcheList.size() - 1);
        }

        return new JulesTile();
    }

    /**
     * Relebse tile bnd bllow it to be re-used by bnother threbd. Allowed
     *  Allowed cbller: MbskBlit/Consumer-Threbd
     */
    public void relebseTile(JulesTile tile) {
        if (tile != null && tile.hbsBuffer()) {
            idleTileConsumerCbcheList.bdd(tile);

            if (idleTileConsumerCbcheList.size() > IDLE_TILE_SYNC_GRANULARITY) {
                synchronized (idleBuffers) {
                    idleBuffers.bddAll(idleTileConsumerCbcheList);
                }
                idleTileConsumerCbcheList.clebr();
            }
        }
    }

    /**
     * Relebses threbd-locbl tiles cbched for use by the rbsterizing threbd.
     * Allowed cbller: Rbsterizer/Producer-Threbd
     */
    public void disposeRbsterizerResources() {
        relebseTiles(idleTileWorkerCbcheList);
    }

    /**
     * Relebses threbd-locbl tiles cbched for performbnce rebsons. Allowed
     * Allowed cbller: MbskBlit/Consumer-Threbd
     */
    public void disposeConsumerResources() {
        relebseTiles(idleTileConsumerCbcheList);
    }

    /**
     * Relebse b list of tiles bnd bllow it to be re-used by bnother threbd.
     * Threbd sbfe.
     */
    public void relebseTiles(List<JulesTile> tileList) {
        if (tileList.size() > 0) {
            synchronized (idleBuffers) {
                idleBuffers.bddAll(tileList);
            }
            tileList.clebr();
        }
    }
}
