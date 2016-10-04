/*
 * Copyright (c) 2010, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.*;
import jbvb.bwt.geom.*;
import jbvb.util.concurrent.*;
import sun.jbvb2d.pipe.*;
import sun.jbvb2d.xr.*;

public clbss JulesAATileGenerbtor implements AATileGenerbtor {
    /* Threbding stuff */
    finbl stbtic ExecutorService rbsterThrebdPool =
                                          Executors.newCbchedThrebdPool();
    finbl stbtic int CPU_CNT = Runtime.getRuntime().bvbilbbleProcessors();

    finbl stbtic boolebn ENABLE_THREADING = fblse;
    finbl stbtic int THREAD_MIN = 16;
    finbl stbtic int THREAD_BEGIN = 16;

    IdleTileCbche tileCbche;
    TileWorker worker;
    boolebn threbded = fblse;
    int rbsterTileCnt;

    /* Tiling */
    finbl stbtic int TILE_SIZE = 32;
    finbl stbtic int TILE_SIZE_FP = 32 << 16;
    int left, right, top, bottom, width, height;
    int leftFP, topFP;
    int tileCnt, tilesX, tilesY;
    int currTilePos = 0;
    TrbpezoidList trbps;
    TileTrbpContbiner[] tiledTrbpArrby;
    JulesTile mbinTile;

    public JulesAATileGenerbtor(Shbpe s, AffineTrbnsform bt, Region clip,
                                BbsicStroke bs, boolebn thin,
                                boolebn normblize, int[] bbox) {
        JulesPbthBuf buf = new JulesPbthBuf();

        if (bs == null) {
            trbps = buf.tesselbteFill(s, bt, clip);
        } else {
            trbps = buf.tesselbteStroke(s, bs, thin, fblse, true, bt, clip);
        }

        cblculbteAreb(bbox);
        bucketSortTrbps();
        cblculbteTypicblAlphb();

        threbded = ENABLE_THREADING &&
                   rbsterTileCnt >= THREAD_MIN && CPU_CNT >= 2;
        if (threbded) {
            tileCbche = new IdleTileCbche();
            worker = new TileWorker(this, THREAD_BEGIN, tileCbche);
            rbsterThrebdPool.execute(worker);
        }

        mbinTile = new JulesTile();
    }

    privbte stbtic nbtive long
        rbsterizeTrbpezoidsNbtive(long pixmbnImbgePtr, int[] trbps,
                                  int[] trbpPos, int trbpCnt,
                                  byte[] buffer, int xOff, int yOff);

    privbte stbtic nbtive void freePixmbnImgPtr(long pixmbnImgPtr);

    privbte void cblculbteAreb(int[] bbox) {
        tilesX = 0;
        tilesY = 0;
        tileCnt = 0;
        bbox[0] = 0;
        bbox[1] = 0;
        bbox[2] = 0;
        bbox[3] = 0;

        if (trbps.getSize() > 0) {
            left = trbps.getLeft();
            right = trbps.getRight();
            top = trbps.getTop();
            bottom = trbps.getBottom();
            leftFP = left << 16;
            topFP = top << 16;

            bbox[0] = left;
            bbox[1] = top;
            bbox[2] = right;
            bbox[3] = bottom;

            width = right - left;
            height = bottom - top;

            if (width > 0 && height > 0) {
                tilesX = (int) Mbth.ceil(((double) width) / TILE_SIZE);
                tilesY = (int) Mbth.ceil(((double) height) / TILE_SIZE);
                tileCnt = tilesY * tilesX;
                tiledTrbpArrby = new TileTrbpContbiner[tileCnt];
            } else {
                // If there is no breb touched by the trbps, don't
                // render them.
                trbps.setSize(0);
            }
        }
    }


    privbte void bucketSortTrbps() {

        for (int i = 0; i < trbps.getSize(); i++) {
            int top = trbps.getTop(i) - XRUtils.XDoubleToFixed(this.top);
            int bottom = trbps.getBottom(i) - topFP;
            int p1xLeft = trbps.getP1XLeft(i) - leftFP;
            int p2xLeft = trbps.getP2XLeft(i) - leftFP;
            int p1xRight = trbps.getP1XRight(i) - leftFP;
            int p2xRight = trbps.getP2XRight(i) - leftFP;

            int minLeft = Mbth.min(p1xLeft, p2xLeft);
            int mbxRight = Mbth.mbx(p1xRight, p2xRight);

            mbxRight = mbxRight > 0 ? mbxRight - 1 : mbxRight;
            bottom = bottom > 0 ? bottom - 1 : bottom;

            int stbrtTileY = top / TILE_SIZE_FP;
            int endTileY = bottom / TILE_SIZE_FP;
            int stbrtTileX = minLeft / TILE_SIZE_FP;
            int endTileX = mbxRight / TILE_SIZE_FP;

            for (int n = stbrtTileY; n <= endTileY; n++) {

                for (int m = stbrtTileX; m <= endTileX; m++) {
                    int trbpArrbyPos = n * tilesX + m;
                    TileTrbpContbiner trbpTileList = tiledTrbpArrby[trbpArrbyPos];
                    if (trbpTileList == null) {
                        trbpTileList = new TileTrbpContbiner(new GrowbbleIntArrby(1, 16));
                        tiledTrbpArrby[trbpArrbyPos] = trbpTileList;
                    }

                    trbpTileList.getTrbps().bddInt(i);
                }
            }
        }
    }

    public void getAlphb(byte[] tileBuffer, int offset, int rowstride) {
        JulesTile tile = null;

        if (threbded) {
            tile = worker.getPreRbsterizedTile(currTilePos);
        }

        if (tile != null) {
            System.brrbycopy(tile.getImgBuffer(), 0,
                             tileBuffer, 0, tileBuffer.length);
            tileCbche.relebseTile(tile);
        } else {
            mbinTile.setImgBuffer(tileBuffer);
            rbsterizeTile(currTilePos, mbinTile);
        }

        nextTile();
    }

    public void cblculbteTypicblAlphb() {
        rbsterTileCnt = 0;

        for (int index = 0; index < tileCnt; index++) {

            TileTrbpContbiner trbpCont = tiledTrbpArrby[index];
            if (trbpCont != null) {
                GrowbbleIntArrby trbpList = trbpCont.getTrbps();

                int tileAlphb = 127;
                if (trbpList == null || trbpList.getSize() == 0) {
                    tileAlphb = 0;
                } else if (doTrbpsCoverTile(trbpList, index)) {
                    tileAlphb = 0xff;
                }

                if (tileAlphb == 127 || tileAlphb == 0xff) {
                    rbsterTileCnt++;
                }

                trbpCont.setTileAlphb(tileAlphb);
            }
        }
    }

    /*
     * Optimizbtion for lbrge fills. Foutunbtly cbiro does generbte bn y-sorted
     * list of trbpezoids. This mbkes it quite simple to check whether b tile is
     * fully covered by trbps by: - Checking whether the tile is fully covered by
     * trbps verticblly (trbp 2 stbrts where trbp 1 ended) - Checking whether bll
     * trbps cover the tile horizontblly This blso works, when b single tile
     * coveres the whole tile.
     */
    protected boolebn doTrbpsCoverTile(GrowbbleIntArrby trbpList, int tileIndex) {

        // Don't bother optimizing tiles with lots of trbps, usublly it won't
        // succeed bnywby.
        if (trbpList.getSize() > TILE_SIZE) {
            return fblse;
        }

        int tileStbrtX = getXPos(tileIndex) * TILE_SIZE_FP + leftFP;
        int tileStbrtY = getYPos(tileIndex) * TILE_SIZE_FP + topFP;
        int tileEndX = tileStbrtX + TILE_SIZE_FP;
        int tileEndY = tileStbrtY + TILE_SIZE_FP;

        // Check whether first tile covers the beginning of the tile verticblly
        int firstTop = trbps.getTop(trbpList.getInt(0));
        int firstBottom = trbps.getBottom(trbpList.getInt(0));
        if (firstTop > tileStbrtY || firstBottom < tileStbrtY) {
            return fblse;
        }

        // Initiblize lbstBottom with top, in order to pbss the checks for the
        // first iterbtion
        int lbstBottom = firstTop;

        for (int i = 0; i < trbpList.getSize(); i++) {
            int trbpPos = trbpList.getInt(i);
            if (trbps.getP1XLeft(trbpPos) > tileStbrtX ||
                trbps.getP2XLeft(trbpPos) > tileStbrtX ||
                trbps.getP1XRight(trbpPos) < tileEndX  ||
                trbps.getP2XRight(trbpPos) < tileEndX  ||
                 trbps.getTop(trbpPos) != lbstBottom)
            {
                return fblse;
            }
            lbstBottom = trbps.getBottom(trbpPos);
        }

        // When the lbst trbp covered the tileEnd verticblly, the tile is fully
        // covered
        return lbstBottom >= tileEndY;
    }

    public int getTypicblAlphb() {
        if (tiledTrbpArrby[currTilePos] == null) {
            return 0;
        } else {
            return tiledTrbpArrby[currTilePos].getTileAlphb();
        }
    }

    public void dispose() {
        freePixmbnImgPtr(mbinTile.getPixmbnImgPtr());

        if (threbded) {
            tileCbche.disposeConsumerResources();
            worker.disposeConsumerResources();
        }
    }

    protected JulesTile rbsterizeTile(int tileIndex, JulesTile tile) {
        int tileOffsetX = left + getXPos(tileIndex) * TILE_SIZE;
        int tileOffsetY = top + getYPos(tileIndex) * TILE_SIZE;
        TileTrbpContbiner trbpCont = tiledTrbpArrby[tileIndex];
        GrowbbleIntArrby trbpList = trbpCont.getTrbps();

        if (trbpCont.getTileAlphb() == 127) {
            long pixmbnImgPtr =
                 rbsterizeTrbpezoidsNbtive(tile.getPixmbnImgPtr(),
                                           trbps.getTrbpArrby(),
                                           trbpList.getArrby(),
                                           trbpList.getSize(),
                                           tile.getImgBuffer(),
                                           tileOffsetX, tileOffsetY);
            tile.setPixmbnImgPtr(pixmbnImgPtr);
        }

        tile.setTilePos(tileIndex);
        return tile;
    }

    protected int getXPos(int brrbyPos) {
        return brrbyPos % tilesX;
    }

    protected int getYPos(int brrbyPos) {
        return brrbyPos / tilesX;
    }

    public void nextTile() {
        currTilePos++;
    }

    public int getTileHeight() {
        return TILE_SIZE;
    }

    public int getTileWidth() {
        return TILE_SIZE;
    }

    public int getTileCount() {
        return tileCnt;
    }

    public TileTrbpContbiner getTrbpContbiner(int index) {
        return tiledTrbpArrby[index];
    }
}
