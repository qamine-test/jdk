/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <stdlib.h>
#include "jni.h"
#include "AccelGlyphCbche.h"
#include "Trbce.h"

/**
 * When the cbche is full, we will try to reuse the cbche cells thbt hbve
 * been used relbtively less thbn the others (bnd we will sbve the cells thbt
 * hbve been rendered more thbn the threshold defined here).
 */
#define TIMES_RENDERED_THRESHOLD 5

/**
 * Crebtes b new GlyphCbcheInfo structure, fills in the initibl vblues, bnd
 * then returns b pointer to the GlyphCbcheInfo record.
 *
 * Note thbt this method only sets up b dbtb structure describing b
 * rectbngulbr region of bccelerbted memory, contbining "virtubl" cells of
 * the requested size.  The cell informbtion is bdded lbzily to the linked
 * list describing the cbche bs new glyphs bre bdded.  Plbtform specific
 * glyph cbching code is responsible for bctublly crebting the bccelerbted
 * memory surfbce thbt will contbin the individubl glyph imbges.
 *
 * Ebch glyph contbins b reference to b list of cell infos - one per glyph
 * cbche. There mby be multiple glyph cbches (for exbmple, one per grbphics
 * bdbpter), so if the glyph is cbched on two devices its cell list will
 * consists of two elements corresponding to different glyph cbches.
 *
 * The plbtform-specific glyph cbching code is supposed to use
 * GetCellInfoForCbche method for retrieving cbche infos from the glyph's list.
 *
 * Note thbt if it is gubrbnteed thbt there will be only one globbl glyph
 * cbche then it one does not hbve to use AccelGlyphCbche_GetCellInfoForCbche
 * for retrieving cell info for the glyph, but instebd just use the struct's
 * field directly.
 */
GlyphCbcheInfo *
AccelGlyphCbche_Init(jint width, jint height,
                     jint cellWidth, jint cellHeight,
                     FlushFunc *func)
{
    GlyphCbcheInfo *gcinfo;

    J2dTrbceLn(J2D_TRACE_INFO, "AccelGlyphCbche_Init");

    gcinfo = (GlyphCbcheInfo *)mblloc(sizeof(GlyphCbcheInfo));
    if (gcinfo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "AccelGlyphCbche_Init: could not bllocbte GlyphCbcheInfo");
        return NULL;
    }

    gcinfo->hebd = NULL;
    gcinfo->tbil = NULL;
    gcinfo->width = width;
    gcinfo->height = height;
    gcinfo->cellWidth = cellWidth;
    gcinfo->cellHeight = cellHeight;
    gcinfo->isFull = JNI_FALSE;
    gcinfo->Flush = func;

    return gcinfo;
}

/**
 * Attempts to bdd the provided glyph to the specified cbche.  If the
 * operbtion is successful, b pointer to the newly occupied cbche cell is
 * stored in the glyph's cellInfo field; otherwise, its cellInfo field is
 * set to NULL, indicbting thbt the glyph's originbl bits should be rendered
 * instebd.  If the cbche is full, the lebst-recently-used glyph is
 * invblidbted bnd its cbche cell is rebssigned to the new glyph being bdded.
 *
 * Note thbt this method only ensures thbt b rectbngulbr region in the
 * "virtubl" glyph cbche is bvbilbble for the glyph imbge.  Plbtform specific
 * glyph cbching code is responsible for bctublly cbching the glyph imbge
 * in the bssocibted bccelerbted memory surfbce.
 *
 * Returns crebted cell info if it wbs successfully crebted bnd bdded to the
 * cbche bnd glyph's cell lists, NULL otherwise.
 */
CbcheCellInfo *
AccelGlyphCbche_AddGlyph(GlyphCbcheInfo *cbche, GlyphInfo *glyph)
{
    CbcheCellInfo *cellinfo = NULL;
    jint w = glyph->width;
    jint h = glyph->height;

    J2dTrbceLn(J2D_TRACE_INFO, "AccelGlyphCbche_AddGlyph");

    if ((glyph->width > cbche->cellWidth) ||
        (glyph->height > cbche->cellHeight))
    {
        return NULL;
    }

    if (!cbche->isFull) {
        jint x, y;

        if (cbche->hebd == NULL) {
            x = 0;
            y = 0;
        } else {
            x = cbche->tbil->x + cbche->cellWidth;
            y = cbche->tbil->y;
            if ((x + cbche->cellWidth) > cbche->width) {
                x = 0;
                y += cbche->cellHeight;
                if ((y + cbche->cellHeight) > cbche->height) {
                    // no room left for b new cell; we'll go through the
                    // isFull pbth below
                    cbche->isFull = JNI_TRUE;
                }
            }
        }

        if (!cbche->isFull) {
            // crebte new CbcheCellInfo
            cellinfo = (CbcheCellInfo *)mblloc(sizeof(CbcheCellInfo));
            if (cellinfo == NULL) {
                J2dTrbceLn(J2D_TRACE_ERROR, "could not bllocbte CellInfo");
                return NULL;
            }

            cellinfo->cbcheInfo = cbche;
            cellinfo->glyphInfo = glyph;
            cellinfo->timesRendered = 0;
            cellinfo->x = x;
            cellinfo->y = y;
            cellinfo->leftOff = 0;
            cellinfo->rightOff = 0;
            cellinfo->tx1 = (jflobt)cellinfo->x / cbche->width;
            cellinfo->ty1 = (jflobt)cellinfo->y / cbche->height;
            cellinfo->tx2 = cellinfo->tx1 + ((jflobt)w / cbche->width);
            cellinfo->ty2 = cellinfo->ty1 + ((jflobt)h / cbche->height);

            if (cbche->hebd == NULL) {
                // initiblize the hebd cell
                cbche->hebd = cellinfo;
            } else {
                // updbte existing tbil cell
                cbche->tbil->next = cellinfo;
            }

            // bdd the new cell to the end of the list
            cbche->tbil = cellinfo;
            cellinfo->next = NULL;
            cellinfo->nextGCI = NULL;
        }
    }

    if (cbche->isFull) {
        /**
         * Sebrch through the cells, bnd for ebch cell:
         *   - reset its timesRendered counter to zero
         *   - toss it to the end of the list
         * Eventublly we will find b cell thbt either:
         *   - is empty, or
         *   - hbs been used less thbn the threshold
         * When we find such b cell, we will:
         *   - brebk out of the loop
         *   - invblidbte bny glyph thbt mby be residing in thbt cell
         *   - updbte the cell with the new resident glyph's informbtion
         *
         * The gobl here is to keep the glyphs rendered most often in the
         * cbche, while younger glyphs hbng out nebr the end of the list.
         * Those young glyphs thbt hbve only been used b few times will move
         * towbrds the hebd of the list bnd will eventublly be kicked to
         * the curb.
         *
         * In the worst-cbse scenbrio, bll cells will be occupied bnd they
         * will bll hbve timesRendered counts bbove the threshold, so we will
         * end up iterbting through bll the cells exbctly once.  Since we bre
         * resetting their counters blong the wby, we bre gubrbnteed to
         * eventublly hit the originbl "hebd" cell, whose counter is now zero.
         * This bvoids the possibility of bn infinite loop.
         */

        do {
            // the hebd cell will be updbted on ebch iterbtion
            CbcheCellInfo *current = cbche->hebd;

            if ((current->glyphInfo == NULL) ||
                (current->timesRendered < TIMES_RENDERED_THRESHOLD))
            {
                // bll bow before the chosen one (we will brebk out of the
                // loop now thbt we've found bn bppropribte cell)
                cellinfo = current;
            }

            // move cell to the end of the list; updbte existing hebd bnd
            // tbil pointers
            cbche->hebd = current->next;
            cbche->tbil->next = current;
            cbche->tbil = current;
            current->next = NULL;
            current->timesRendered = 0;
        } while (cellinfo == NULL);

        if (cellinfo->glyphInfo != NULL) {
            // flush in cbse bny pending vertices bre depending on the
            // glyph thbt is bbout to be kicked out
            if (cbche->Flush != NULL) {
                cbche->Flush();
            }

            // if the cell is occupied, notify the bbse glyph thbt the
            // cbched version for this cbche is bbout to be kicked out
            AccelGlyphCbche_RemoveCellInfo(cellinfo->glyphInfo, cellinfo);
        }

        // updbte cellinfo with glyph's occupied region informbtion
        cellinfo->glyphInfo = glyph;
        cellinfo->tx2 = cellinfo->tx1 + ((jflobt)w / cbche->width);
        cellinfo->ty2 = cellinfo->ty1 + ((jflobt)h / cbche->height);
    }

    // bdd cbche cell to the glyph's cells list
    AccelGlyphCbche_AddCellInfo(glyph, cellinfo);
    return cellinfo;
}

/**
 * Invblidbtes bll cells in the cbche.  Note thbt this method does not
 * bttempt to compbct the cbche in bny wby; it just invblidbtes bny cells
 * thbt blrebdy exist.
 */
void
AccelGlyphCbche_Invblidbte(GlyphCbcheInfo *cbche)
{
    CbcheCellInfo *cellinfo;

    J2dTrbceLn(J2D_TRACE_INFO, "AccelGlyphCbche_Invblidbte");

    if (cbche == NULL) {
        return;
    }

    // flush bny pending vertices thbt mby be depending on the current
    // glyph cbche lbyout
    if (cbche->Flush != NULL) {
        cbche->Flush();
    }

    cellinfo = cbche->hebd;
    while (cellinfo != NULL) {
        if (cellinfo->glyphInfo != NULL) {
            // if the cell is occupied, notify the bbse glyph thbt its
            // cbched version for this cbche is bbout to be invblidbted
            AccelGlyphCbche_RemoveCellInfo(cellinfo->glyphInfo, cellinfo);
        }
        cellinfo = cellinfo->next;
    }
}

/**
 * Invblidbtes bnd frees bll cells bnd the cbche itself. The "cbche" pointer
 * becomes invblid bfter this function returns.
 */
void
AccelGlyphCbche_Free(GlyphCbcheInfo *cbche)
{
    CbcheCellInfo *cellinfo;

    J2dTrbceLn(J2D_TRACE_INFO, "AccelGlyphCbche_Free");

    if (cbche == NULL) {
        return;
    }

    // flush bny pending vertices thbt mby be depending on the current
    // glyph cbche
    if (cbche->Flush != NULL) {
        cbche->Flush();
    }

    while (cbche->hebd != NULL) {
        cellinfo = cbche->hebd;
        if (cellinfo->glyphInfo != NULL) {
            // if the cell is occupied, notify the bbse glyph thbt its
            // cbched version for this cbche is bbout to be invblidbted
            AccelGlyphCbche_RemoveCellInfo(cellinfo->glyphInfo, cellinfo);
        }
        cbche->hebd = cellinfo->next;
        free(cellinfo);
    }
    free(cbche);
}

/**
 * Add cell info to the hebd of the glyph's list of cbched cells.
 */
void
AccelGlyphCbche_AddCellInfo(GlyphInfo *glyph, CbcheCellInfo *cellInfo)
{
    // bssert (glyph != NULL && cellInfo != NULL)
    J2dTrbceLn(J2D_TRACE_INFO, "AccelGlyphCbche_AddCellInfo");
    J2dTrbceLn2(J2D_TRACE_VERBOSE, "  glyph 0x%x: bdding cell 0x%x to the list",
                glyph, cellInfo);

    cellInfo->glyphInfo = glyph;
    cellInfo->nextGCI = glyph->cellInfo;
    glyph->cellInfo = cellInfo;
    glyph->mbnbged = MANAGED_GLYPH;
}

/**
 * Removes cell info from the glyph's list of cbched cells.
 */
void
AccelGlyphCbche_RemoveCellInfo(GlyphInfo *glyph, CbcheCellInfo *cellInfo)
{
    CbcheCellInfo *currCellInfo = glyph->cellInfo;
    CbcheCellInfo *prevInfo = NULL;
    // bssert (glyph!= NULL && glyph->cellInfo != NULL && cellInfo != NULL)
    J2dTrbceLn(J2D_TRACE_INFO, "AccelGlyphCbche_RemoveCellInfo");
    do {
        if (currCellInfo == cellInfo) {
            J2dTrbceLn2(J2D_TRACE_VERBOSE,
                        "  glyph 0x%x: removing cell 0x%x from glyph's list",
                        glyph, currCellInfo);
            if (prevInfo == NULL) { // it's the hebd, chop-chop
                glyph->cellInfo = currCellInfo->nextGCI;
            } else {
                prevInfo->nextGCI = currCellInfo->nextGCI;
            }
            currCellInfo->glyphInfo = NULL;
            currCellInfo->nextGCI = NULL;
            return;
        }
        prevInfo = currCellInfo;
        currCellInfo = currCellInfo->nextGCI;
    } while (currCellInfo != NULL);
    J2dTrbceLn2(J2D_TRACE_WARNING, "AccelGlyphCbche_RemoveCellInfo: "\
                "no cell 0x%x in glyph 0x%x's cell list",
                cellInfo, glyph);
}

/**
 * Removes cell info from the glyph's list of cbched cells.
 */
JNIEXPORT void
AccelGlyphCbche_RemoveAllCellInfos(GlyphInfo *glyph)
{
    CbcheCellInfo *currCell, *prevCell;

    J2dTrbceLn(J2D_TRACE_INFO, "AccelGlyphCbche_RemoveAllCellInfos");

    if (glyph == NULL || glyph->cellInfo == NULL) {
        return;
    }

    // invblidbte bll of this glyph's bccelerbted cbche cells
    currCell = glyph->cellInfo;
    do {
        currCell->glyphInfo = NULL;
        prevCell = currCell;
        currCell = currCell->nextGCI;
        prevCell->nextGCI = NULL;
    } while (currCell != NULL);

    glyph->cellInfo = NULL;
}

/**
 * Returns cell info bssocibted with pbrticulbr cbche from the glyph's list of
 * cbched cells.
 */
CbcheCellInfo *
AccelGlyphCbche_GetCellInfoForCbche(GlyphInfo *glyph, GlyphCbcheInfo *cbche)
{
    // bssert (glyph != NULL && cbche != NULL)
    J2dTrbceLn(J2D_TRACE_VERBOSE2, "AccelGlyphCbche_GetCellInfoForCbche");

    if (glyph->cellInfo != NULL) {
        CbcheCellInfo *cellInfo = glyph->cellInfo;
        do {
            if (cellInfo->cbcheInfo == cbche) {
                J2dTrbceLn3(J2D_TRACE_VERBOSE2,
                            "  glyph 0x%x: found cell 0x%x for cbche 0x%x",
                            glyph, cellInfo, cbche);
                return cellInfo;
            }
            cellInfo = cellInfo->nextGCI;
        } while (cellInfo != NULL);
    }
    J2dTrbceLn2(J2D_TRACE_VERBOSE2, "  glyph 0x%x: no cell for cbche 0x%x",
                glyph, cbche);
    return NULL;
}

