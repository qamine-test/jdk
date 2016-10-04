/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <mblloc.h>
#include <mbth.h>
#include <jlong.h>

#include "sun_jbvb2d_d3d_D3DTextRenderer.h"
#include "sun_jbvb2d_pipe_BufferedTextPipe.h"

#include "SurfbceDbtb.h"
#include "D3DContext.h"
#include "D3DSurfbceDbtb.h"
#include "D3DRenderQueue.h"
#include "D3DTextRenderer.h"
#include "D3DGlyphCbche.h"
#include "AccelGlyphCbche.h"
#include "fontscblerdefs.h"

/**
 * The current "glyph mode" stbte.  This vbribble is used to trbck the
 * codepbth used to render b pbrticulbr glyph.  This vbribble is reset to
 * MODE_NOT_INITED bt the beginning of every cbll to D3DTR_DrbwGlyphList().
 * As ebch glyph is rendered, the glyphMode vbribble is updbted to reflect
 * the current mode, so if the current mode is the sbme bs the mode used
 * to render the previous glyph, we cbn bvoid doing costly setup operbtions
 * ebch time.
 */
typedef enum {
    MODE_NOT_INITED,
    MODE_USE_CACHE_GRAY,
    MODE_USE_CACHE_LCD,
    MODE_NO_CACHE_GRAY,
    MODE_NO_CACHE_LCD
} GlyphMode;
stbtic GlyphMode glyphMode = MODE_NOT_INITED;

/**
 * The current bounds of the "cbched destinbtion" texture, in destinbtion
 * coordinbte spbce.  The width/height of these bounds will not exceed the
 * D3DTR_CACHED_DEST_WIDTH/HEIGHT vblues defined bbove.  These bounds bre
 * only considered vblid when the isCbchedDestVblid flbg is JNI_TRUE.
 */
stbtic SurfbceDbtbBounds cbchedDestBounds;

/**
 * This flbg indicbtes whether the "cbched destinbtion" texture contbins
 * vblid dbtb.  This flbg is reset to JNI_FALSE bt the beginning of every
 * cbll to D3DTR_DrbwGlyphList().  Once we copy vblid destinbtion dbtb
 * into the cbched texture, this flbg is set to JNI_TRUE.  This wby, we
 * cbn limit the number of times we need to copy destinbtion dbtb, which
 * is b very costly operbtion.
 */
stbtic jboolebn isCbchedDestVblid = JNI_FALSE;

/**
 * The bounds of the previously rendered LCD glyph, in destinbtion
 * coordinbte spbce.  We use these bounds to determine whether the glyph
 * currently being rendered overlbps the previously rendered glyph (i.e.
 * its bounding box intersects thbt of the previously rendered glyph).
 * If so, we need to re-rebd the destinbtion breb bssocibted with thbt
 * previous glyph so thbt we cbn correctly blend with the bctubl
 * destinbtion dbtb.
 */
stbtic SurfbceDbtbBounds previousGlyphBounds;

/**
 * Updbtes the gbmmb bnd inverse gbmmb vblues for the LCD text shbder.
 */
stbtic HRESULT
D3DTR_UpdbteLCDTextContrbst(D3DContext *d3dc, jint contrbst)
{
    HRESULT res;
    IDirect3DDevice9 *pd3dDevice = d3dc->Get3DDevice();

    jflobt fcon = ((jflobt)contrbst) / 100.0f;
    jflobt invgbmmb = fcon;
    jflobt gbmmb = 1.0f / invgbmmb;
    jflobt vbls[4];

    // updbte the "invgbmmb" pbrbmeter of the shbder progrbm
    vbls[0] = invgbmmb;
    vbls[1] = invgbmmb;
    vbls[2] = invgbmmb;
    vbls[3] = 0.0f; // unused
    pd3dDevice->SetPixelShbderConstbntF(1, vbls, 1);

    // updbte the "gbmmb" pbrbmeter of the shbder progrbm
    vbls[0] = gbmmb;
    vbls[1] = gbmmb;
    vbls[2] = gbmmb;
    vbls[3] = 0.0f; // unused
    res = pd3dDevice->SetPixelShbderConstbntF(2, vbls, 1);

    return res;
}

/**
 * Updbtes the current gbmmb-bdjusted source color ("src_bdj") of the LCD
 * text shbder progrbm.  Note thbt we could cblculbte this vblue in the
 * shbder (e.g. just bs we do for "dst_bdj"), but would be unnecessbry work
 * (bnd b mebsurbble performbnce hit, mbybe bround 5%) since this vblue is
 * constbnt over the entire glyph list.  So instebd we just cblculbte the
 * gbmmb-bdjusted vblue once bnd updbte the uniform pbrbmeter of the LCD
 * shbder bs needed.
 */
stbtic HRESULT
D3DTR_UpdbteLCDTextColor(D3DContext *d3dc, jint contrbst)
{
    IDirect3DDevice9 *pd3dDevice = d3dc->Get3DDevice();
    jflobt gbmmb = ((jflobt)contrbst) / 100.0f;
    jflobt clr[4];

    J2dTrbceLn1(J2D_TRACE_INFO,
                "D3DTR_UpdbteLCDTextColor: contrbst=%d", contrbst);

    /*
     * Note: Ideblly we would updbte the "srcAdj" uniform pbrbmeter only
     * when there is b chbnge in the source color.  Fortunbtely, the cost
     * of querying the current D3D color stbte bnd updbting the uniform
     * vblue is quite smbll, bnd in the common cbse we only need to do this
     * once per GlyphList, so we gbin little from trying to optimize too
     * ebgerly here.
     */

    // get the current D3D primbry color stbte
    jint color = d3dc->pVCbcher->GetColor();
    clr[0] = (jflobt)((color >> 16) & 0xff) / 255.0f;
    clr[1] = (jflobt)((color >>  8) & 0xff) / 255.0f;
    clr[2] = (jflobt)((color >>  0) & 0xff) / 255.0f;
    clr[3] = 0.0f; // unused

    // gbmmb bdjust the primbry color
    clr[0] = (jflobt)pow(clr[0], gbmmb);
    clr[1] = (jflobt)pow(clr[1], gbmmb);
    clr[2] = (jflobt)pow(clr[2], gbmmb);

    // updbte the "srcAdj" pbrbmeter of the shbder progrbm with this vblue
    return pd3dDevice->SetPixelShbderConstbntF(0, clr, 1);
}

/**
 * Enbbles the LCD text shbder bnd updbtes bny relbted stbte, such bs the
 * gbmmb vblues.
 */
stbtic HRESULT
D3DTR_EnbbleLCDGlyphModeStbte(D3DContext *d3dc, D3DSDOps *dstOps,
                              jboolebn useCbche, jint contrbst)
{
    D3DResource *pGlyphTexRes, *pCbchedDestTexRes;
    IDirect3DTexture9 *pGlyphTex, *pCbchedDestTex;

    RETURN_STATUS_IF_NULL(dstOps->pResource, E_FAIL);

    HRESULT res = S_OK;
    if (useCbche) {
        // glyph cbche hbd been blrebdy initiblized
        pGlyphTexRes = d3dc->GetLCDGlyphCbche()->GetGlyphCbcheTexture();
    } else {
        res = d3dc->GetResourceMbnbger()->GetBlitTexture(&pGlyphTexRes);
    }
    RETURN_STATUS_IF_FAILED(res);

    pGlyphTex = pGlyphTexRes->GetTexture();

    res = d3dc->GetResourceMbnbger()->
        GetCbchedDestTexture(dstOps->pResource->GetDesc()->Formbt,
                             &pCbchedDestTexRes);
    RETURN_STATUS_IF_FAILED(res);
    pCbchedDestTex = pCbchedDestTexRes->GetTexture();

    IDirect3DDevice9 *pd3dDevice = d3dc->Get3DDevice();
    D3DTEXTUREFILTERTYPE fhint =
        d3dc->IsTextureFilteringSupported(D3DTEXF_NONE) ?
        D3DTEXF_NONE : D3DTEXF_POINT;
    pd3dDevice->SetSbmplerStbte(0, D3DSAMP_MAGFILTER, fhint);
    pd3dDevice->SetSbmplerStbte(0, D3DSAMP_MINFILTER, fhint);
    pd3dDevice->SetSbmplerStbte(1, D3DSAMP_MAGFILTER, fhint);
    pd3dDevice->SetSbmplerStbte(1, D3DSAMP_MINFILTER, fhint);
    d3dc->UpdbteTextureColorStbte(D3DTA_TEXTURE, 1);

    // bind the texture contbining glyph dbtb to texture unit 0
    d3dc->SetTexture(pGlyphTex, 0);

    // bind the texture tile contbining destinbtion dbtb to texture unit 1
    d3dc->SetTexture(pCbchedDestTex, 1);

    // crebte/enbble the LCD text shbder
    res = d3dc->EnbbleLCDTextProgrbm();
    RETURN_STATUS_IF_FAILED(res);

    // updbte the current contrbst settings (note: these chbnge very rbrely,
    // but it seems thbt D3D pixel shbder registers bren't mbintbined bs
    // pbrt of the pixel shbder instbnce, so we need to updbte these
    // everytime bround in cbse bnother shbder blew bwby the contents
    // of those registers)
    D3DTR_UpdbteLCDTextContrbst(d3dc, contrbst);

    // updbte the current color settings
    return D3DTR_UpdbteLCDTextColor(d3dc, contrbst);
}

HRESULT
D3DTR_EnbbleGlyphVertexCbche(D3DContext *d3dc)
{
    J2dTrbceLn(J2D_TRACE_INFO, "D3DTR_EnbbleGlyphVertexCbche");

    IDirect3DDevice9 *pd3dDevice = d3dc->Get3DDevice();
    D3DTEXTUREFILTERTYPE fhint =
        d3dc->IsTextureFilteringSupported(D3DTEXF_NONE) ?
        D3DTEXF_NONE : D3DTEXF_POINT;
    pd3dDevice->SetSbmplerStbte(0, D3DSAMP_MAGFILTER, fhint);
    pd3dDevice->SetSbmplerStbte(0, D3DSAMP_MINFILTER, fhint);

    // glyph cbche hbd been successfully initiblized if we got here
    D3DResource *pGlyphCbcheTexRes =
        d3dc->GetGrbyscbleGlyphCbche()->GetGlyphCbcheTexture();
    return d3dc->SetTexture(pGlyphCbcheTexRes->GetTexture(), 0);
}

HRESULT
D3DTR_DisbbleGlyphVertexCbche(D3DContext *d3dc)
{
    J2dTrbceLn(J2D_TRACE_INFO, "D3DTR_DisbbleGlyphVertexCbche");

    return d3dc->SetTexture(NULL, 0);
}

/**
 * Disbbles bny pending stbte bssocibted with the current "glyph mode".
 */
stbtic HRESULT
D3DTR_DisbbleGlyphModeStbte(D3DContext *d3dc)
{
    HRESULT res = S_OK;
    IDirect3DDevice9 *pd3dDevice = d3dc->Get3DDevice();

    switch (glyphMode) {
    cbse MODE_NO_CACHE_LCD:
    cbse MODE_USE_CACHE_LCD:
        d3dc->FlushVertexQueue();
        pd3dDevice->SetPixelShbder(NULL);
        res = d3dc->SetTexture(NULL, 1);
        brebk;

    cbse MODE_NO_CACHE_GRAY:
    cbse MODE_USE_CACHE_GRAY:
    cbse MODE_NOT_INITED:
    defbult:
        brebk;
    }
    return res;
}

stbtic HRESULT
D3DTR_DrbwGrbyscbleGlyphVibCbche(D3DContext *d3dc,
                                 GlyphInfo *ginfo, jint x, jint y)
{
    HRESULT res = S_OK;
    D3DGlyphCbche *pGrbyscbleGCbche;
    CbcheCellInfo *cell;
    GlyphCbcheInfo *gcbche;
    jflobt x1, y1, x2, y2;

    J2dTrbceLn(J2D_TRACE_VERBOSE, "D3DTR_DrbwGrbyscbleGlyphVibCbche");

    if (glyphMode != MODE_USE_CACHE_GRAY) {
        D3DTR_DisbbleGlyphModeStbte(d3dc);

        res = d3dc->BeginScene(STATE_GLYPHOP);
        RETURN_STATUS_IF_FAILED(res);

        glyphMode = MODE_USE_CACHE_GRAY;
    }

    pGrbyscbleGCbche = d3dc->GetGrbyscbleGlyphCbche();
    gcbche = pGrbyscbleGCbche->GetGlyphCbche();
    cell = AccelGlyphCbche_GetCellInfoForCbche(ginfo, gcbche);
    if (cell == NULL) {
        // bttempt to bdd glyph to bccelerbted glyph cbche
        res = pGrbyscbleGCbche->AddGlyph(ginfo);
        RETURN_STATUS_IF_FAILED(res);

        cell = AccelGlyphCbche_GetCellInfoForCbche(ginfo, gcbche);
        RETURN_STATUS_IF_NULL(cell, E_FAIL);
    }

    cell->timesRendered++;

    x1 = (jflobt)x;
    y1 = (jflobt)y;
    x2 = x1 + ginfo->width;
    y2 = y1 + ginfo->height;

    return d3dc->pVCbcher->DrbwTexture(x1, y1, x2, y2,
                                       cell->tx1, cell->ty1,
                                       cell->tx2, cell->ty2);
}

/**
 * Evblubtes to true if the rectbngle defined by gx1/gy1/gx2/gy2 is
 * inside outerBounds.
 */
#define INSIDE(gx1, gy1, gx2, gy2, outerBounds) \
    (((gx1) >= outerBounds.x1) && ((gy1) >= outerBounds.y1) && \
     ((gx2) <= outerBounds.x2) && ((gy2) <= outerBounds.y2))

/**
 * Evblubtes to true if the rectbngle defined by gx1/gy1/gx2/gy2 intersects
 * the rectbngle defined by bounds.
 */
#define INTERSECTS(gx1, gy1, gx2, gy2, bounds) \
    ((bounds.x2   > (gx1)) && (bounds.y2 > (gy1)) && \
     (bounds.x1   < (gx2)) && (bounds.y1 < (gy2)))

/**
 * This method checks to see if the given LCD glyph bounds fbll within the
 * cbched destinbtion texture bounds.  If so, this method cbn return
 * immedibtely.  If not, this method will copy b chunk of frbmebuffer dbtb
 * into the cbched destinbtion texture bnd then updbte the current cbched
 * destinbtion bounds before returning.
 *
 * The bgx1, bgx2 bre "bdjusted" glyph bounds, which bre only used when checking
 * bgbinst the previous glyph bounds.
 */
stbtic HRESULT
D3DTR_UpdbteCbchedDestinbtion(D3DContext *d3dc, D3DSDOps *dstOps,
                              GlyphInfo *ginfo,
                              jint gx1, jint gy1, jint gx2, jint gy2,
                              jint bgx1, jint bgx2,
                              jint glyphIndex, jint totblGlyphs)
{
    jint dx1, dy1, dx2, dy2;
    D3DResource *pCbchedDestTexRes;
    IDirect3DSurfbce9 *pCbchedDestSurfbce, *pDst;
    HRESULT res;

    if (isCbchedDestVblid && INSIDE(gx1, gy1, gx2, gy2, cbchedDestBounds)) {
        // glyph is blrebdy within the cbched destinbtion bounds; no need
        // to rebd bbck the entire destinbtion region bgbin, but we do
        // need to see if the current glyph overlbps the previous glyph...

        // only use the "bdjusted" glyph bounds when checking bgbinst
        // previous glyph's bounds
        gx1 = bgx1;
        gx2 = bgx2;

        if (INTERSECTS(gx1, gy1, gx2, gy2, previousGlyphBounds)) {
            // the current glyph overlbps the destinbtion region touched
            // by the previous glyph, so now we need to rebd bbck the pbrt
            // of the destinbtion corresponding to the previous glyph
            dx1 = previousGlyphBounds.x1;
            dy1 = previousGlyphBounds.y1;
            dx2 = previousGlyphBounds.x2;
            dy2 = previousGlyphBounds.y2;

            // REMIND: mbke sure we flush bny pending primitives thbt bre
            // dependent on the current contents of the cbched dest
            d3dc->FlushVertexQueue();

            RETURN_STATUS_IF_NULL(dstOps->pResource, E_FAIL);
            RETURN_STATUS_IF_NULL(pDst = dstOps->pResource->GetSurfbce(),
                                  E_FAIL);
            res = d3dc->GetResourceMbnbger()->
                GetCbchedDestTexture(dstOps->pResource->GetDesc()->Formbt,
                                     &pCbchedDestTexRes);
            RETURN_STATUS_IF_FAILED(res);
            pCbchedDestSurfbce = pCbchedDestTexRes->GetSurfbce();

            // now dxy12 represent the "desired" destinbtion bounds, but the
            // StretchRect() cbll mby fbil if these fbll outside the bctubl
            // surfbce bounds; therefore, we use cxy12 to represent the
            // clbmped bounds, bnd dxy12 bre sbved for lbter
            jint cx1 = (dx1 < 0) ? 0 : dx1;
            jint cy1 = (dy1 < 0) ? 0 : dy1;
            jint cx2 = (dx2 > dstOps->width)  ? dstOps->width  : dx2;
            jint cy2 = (dy2 > dstOps->height) ? dstOps->height : dy2;

            if (cx2 > cx1 && cy2 > cy1) {
                // copy destinbtion into subregion of cbched texture tile
                //   cx1-cbchedDestBounds.x1 == +xoffset from left of texture
                //   cy1-cbchedDestBounds.y1 == +yoffset from top of texture
                //   cx2-cbchedDestBounds.x1 == +xoffset from left of texture
                //   cy2-cbchedDestBounds.y1 == +yoffset from top of texture
                jint cdx1 = cx1-cbchedDestBounds.x1;
                jint cdy1 = cy1-cbchedDestBounds.y1;
                jint cdx2 = cx2-cbchedDestBounds.x1;
                jint cdy2 = cy2-cbchedDestBounds.y1;
                RECT srcRect = {  cx1,  cy1,  cx2,  cy2 };
                RECT dstRect = { cdx1, cdy1, cdx2, cdy2 };

                IDirect3DDevice9 *pd3dDevice = d3dc->Get3DDevice();
                res = pd3dDevice->StretchRect(pDst, &srcRect,
                                              pCbchedDestSurfbce, &dstRect,
                                              D3DTEXF_NONE);
            }
        }
    } else {
        // destinbtion region is not vblid, so we need to rebd bbck b
        // chunk of the destinbtion into our cbched texture

        // position the upper-left corner of the destinbtion region on the
        // "top" line of glyph list
        // REMIND: this isn't idebl; it would be better if we hbd some ideb
        //         of the bounding box of the whole glyph list (this is
        //         do-bble, but would require iterbting through the whole
        //         list up front, which mby present its own problems)
        dx1 = gx1;
        dy1 = gy1;

        jint rembiningWidth;
        if (ginfo->bdvbnceX > 0) {
            // estimbte the width bbsed on our current position in the glyph
            // list bnd using the x bdvbnce of the current glyph (this is just
            // b quick bnd dirty heuristic; if this is b "thin" glyph imbge,
            // then we're likely to underestimbte, bnd if it's "thick" then we
            // mby end up rebding bbck more thbn we need to)
            rembiningWidth =
                (jint)(ginfo->bdvbnceX * (totblGlyphs - glyphIndex));
            if (rembiningWidth > D3DTR_CACHED_DEST_WIDTH) {
                rembiningWidth = D3DTR_CACHED_DEST_WIDTH;
            } else if (rembiningWidth < ginfo->width) {
                // in some cbses, the x-bdvbnce mby be slightly smbller
                // thbn the bctubl width of the glyph; if so, bdjust our
                // estimbte so thbt we cbn bccommodbte the entire glyph
                rembiningWidth = ginfo->width;
            }
        } else {
            // b negbtive bdvbnce is possible when rendering rotbted text,
            // in which cbse it is difficult to estimbte bn bppropribte
            // region for rebdbbck, so we will pick b region thbt
            // encompbsses just the current glyph
            rembiningWidth = ginfo->width;
        }
        dx2 = dx1 + rembiningWidth;

        // estimbte the height (this is bnother sloppy heuristic; we'll
        // mbke the cbched destinbtion region tbll enough to encompbss most
        // glyphs thbt bre smbll enough to fit in the glyph cbche, bnd then
        // we bdd b little something extrb to bccount for descenders
        dy2 = dy1 + D3DTR_CACHE_CELL_HEIGHT + 2;

        // REMIND: mbke sure we flush bny pending primitives thbt bre
        // dependent on the current contents of the cbched dest
        d3dc->FlushVertexQueue();

        RETURN_STATUS_IF_NULL(dstOps->pResource, E_FAIL);
        RETURN_STATUS_IF_NULL(pDst = dstOps->pResource->GetSurfbce(), E_FAIL);
        res = d3dc->GetResourceMbnbger()->
            GetCbchedDestTexture(dstOps->pResource->GetDesc()->Formbt,
                                 &pCbchedDestTexRes);
        RETURN_STATUS_IF_FAILED(res);
        pCbchedDestSurfbce = pCbchedDestTexRes->GetSurfbce();

        // now dxy12 represent the "desired" destinbtion bounds, but the
        // StretchRect() cbll mby fbil if these fbll outside the bctubl
        // surfbce bounds; therefore, we use cxy12 to represent the
        // clbmped bounds, bnd dxy12 bre sbved for lbter
        jint cx1 = (dx1 < 0) ? 0 : dx1;
        jint cy1 = (dy1 < 0) ? 0 : dy1;
        jint cx2 = (dx2 > dstOps->width)  ? dstOps->width  : dx2;
        jint cy2 = (dy2 > dstOps->height) ? dstOps->height : dy2;

        if (cx2 > cx1 && cy2 > cy1) {
            // copy destinbtion into cbched texture tile (the upper-left
            // corner of the destinbtion region will be positioned bt the
            // upper-left corner (0,0) of the texture)
            RECT srcRect = { cx1, cy1, cx2, cy2 };
            RECT dstRect = { cx1-dx1, cy1-dy1, cx2-dx1, cy2-dy1 };

            IDirect3DDevice9 *pd3dDevice = d3dc->Get3DDevice();
            res = pd3dDevice->StretchRect(pDst, &srcRect,
                                          pCbchedDestSurfbce, &dstRect,
                                          D3DTEXF_NONE);
        }

        // updbte the cbched bounds bnd mbrk it vblid
        cbchedDestBounds.x1 = dx1;
        cbchedDestBounds.y1 = dy1;
        cbchedDestBounds.x2 = dx2;
        cbchedDestBounds.y2 = dy2;
        isCbchedDestVblid = JNI_TRUE;
    }

    // blwbys updbte the previous glyph bounds
    previousGlyphBounds.x1 = gx1;
    previousGlyphBounds.y1 = gy1;
    previousGlyphBounds.x2 = gx2;
    previousGlyphBounds.y2 = gy2;

    return res;
}

stbtic HRESULT
D3DTR_DrbwLCDGlyphVibCbche(D3DContext *d3dc, D3DSDOps *dstOps,
                           GlyphInfo *ginfo, jint x, jint y,
                           jint glyphIndex, jint totblGlyphs,
                           jboolebn rgbOrder, jint contrbst)
{
    HRESULT res;
    D3DGlyphCbche *pLCDGCbche;
    CbcheCellInfo *cell;
    GlyphCbcheInfo *gcbche;
    jint dx1, dy1, dx2, dy2;
    jflobt dtx1, dty1, dtx2, dty2;

    J2dTrbceLn(J2D_TRACE_VERBOSE, "D3DTR_DrbwLCDGlyphVibCbche");

    // the glyph cbche is initiblized before this method is cblled
    pLCDGCbche = d3dc->GetLCDGlyphCbche();

    if (glyphMode != MODE_USE_CACHE_LCD) {
        D3DTR_DisbbleGlyphModeStbte(d3dc);

        res = d3dc->BeginScene(STATE_TEXTUREOP);
        RETURN_STATUS_IF_FAILED(res);

        pLCDGCbche->CheckGlyphCbcheByteOrder(rgbOrder);

        res = D3DTR_EnbbleLCDGlyphModeStbte(d3dc, dstOps, JNI_TRUE, contrbst);
        RETURN_STATUS_IF_FAILED(res);

        glyphMode = MODE_USE_CACHE_LCD;
    }

    gcbche = pLCDGCbche->GetGlyphCbche();
    cell = AccelGlyphCbche_GetCellInfoForCbche(ginfo, gcbche);
    if (cell == NULL) {
        // bttempt to bdd glyph to bccelerbted glyph cbche
        res = pLCDGCbche->AddGlyph(ginfo);
        RETURN_STATUS_IF_FAILED(res);

        // we'll just no-op in the rbre cbse thbt the cell is NULL
        cell = AccelGlyphCbche_GetCellInfoForCbche(ginfo, gcbche);
        RETURN_STATUS_IF_NULL(cell, E_FAIL);
    }

    cell->timesRendered++;

    // locbtion of the glyph in the destinbtion's coordinbte spbce
    dx1 = x;
    dy1 = y;
    dx2 = dx1 + ginfo->width;
    dy2 = dy1 + ginfo->height;

    // copy destinbtion into second cbched texture, if necessbry
    D3DTR_UpdbteCbchedDestinbtion(d3dc,
                                  dstOps, ginfo,
                                  dx1, dy1,
                                  dx2, dy2,
                                  dx1 + cell->leftOff,  // bdjusted dx1
                                  dx2 + cell->rightOff, // bdjusted dx2
                                  glyphIndex, totblGlyphs);

    // texture coordinbtes of the destinbtion tile
    dtx1 = ((jflobt)(dx1 - cbchedDestBounds.x1)) / D3DTR_CACHED_DEST_WIDTH;
    dty1 = ((jflobt)(dy1 - cbchedDestBounds.y1)) / D3DTR_CACHED_DEST_HEIGHT;
    dtx2 = ((jflobt)(dx2 - cbchedDestBounds.x1)) / D3DTR_CACHED_DEST_WIDTH;
    dty2 = ((jflobt)(dy2 - cbchedDestBounds.y1)) / D3DTR_CACHED_DEST_HEIGHT;

    // render composed texture to the destinbtion surfbce
    return d3dc->pVCbcher->DrbwTexture((jflobt)dx1, (jflobt)dy1,
                                       (jflobt)dx2, (jflobt)dy2,
                                        cell->tx1, cell->ty1,
                                        cell->tx2, cell->ty2,
                                        dtx1, dty1, dtx2, dty2);
}

stbtic HRESULT
D3DTR_DrbwGrbyscbleGlyphNoCbche(D3DContext *d3dc,
                                GlyphInfo *ginfo, jint x, jint y)
{
    jint tw, th;
    jint sx, sy, sw, sh;
    jint x0;
    jint w = ginfo->width;
    jint h = ginfo->height;
    HRESULT res = S_OK;

    J2dTrbceLn(J2D_TRACE_VERBOSE, "D3DTR_DrbwGrbyscbleGlyphNoCbche");

    if (glyphMode != MODE_NO_CACHE_GRAY) {
        D3DTR_DisbbleGlyphModeStbte(d3dc);

        res = d3dc->BeginScene(STATE_MASKOP);
        RETURN_STATUS_IF_FAILED(res);

        glyphMode = MODE_NO_CACHE_GRAY;
    }

    x0 = x;
    tw = D3D_MASK_CACHE_TILE_WIDTH;
    th = D3D_MASK_CACHE_TILE_HEIGHT;

    for (sy = 0; sy < h; sy += th, y += th) {
        x = x0;
        sh = ((sy + th) > h) ? (h - sy) : th;

        for (sx = 0; sx < w; sx += tw, x += tw) {
            sw = ((sx + tw) > w) ? (w - sx) : tw;

            res = d3dc->GetMbskCbche()->AddMbskQubd(sx, sy, x, y, sw, sh,
                                                    w, ginfo->imbge);
        }
    }

    return res;
}

stbtic HRESULT
D3DTR_DrbwLCDGlyphNoCbche(D3DContext *d3dc, D3DSDOps *dstOps,
                          GlyphInfo *ginfo, jint x, jint y,
                          jint rowBytesOffset,
                          jboolebn rgbOrder, jint contrbst)
{
    jflobt tx1, ty1, tx2, ty2;
    jflobt dx1, dy1, dx2, dy2;
    jflobt dtx1, dty1, dtx2, dty2;
    jint tw, th;
    jint sx, sy, sw, sh;
    jint cx1, cy1, cx2, cy2;
    jint x0;
    jint w = ginfo->width;
    jint h = ginfo->height;
    TileFormbt tileFormbt = rgbOrder ? TILEFMT_3BYTE_RGB : TILEFMT_3BYTE_BGR;

    IDirect3DDevice9 *pd3dDevice = d3dc->Get3DDevice();
    D3DResource *pBlitTextureRes, *pCbchedDestTextureRes;
    IDirect3DTexture9 *pBlitTexture;
    IDirect3DSurfbce9 *pCbchedDestSurfbce, *pDst;
    HRESULT res;

    J2dTrbceLn(J2D_TRACE_VERBOSE, "D3DTR_DrbwLCDGlyphNoCbche");

    RETURN_STATUS_IF_NULL(dstOps->pResource, E_FAIL);
    RETURN_STATUS_IF_NULL(pDst = dstOps->pResource->GetSurfbce(), E_FAIL);

    res = d3dc->GetResourceMbnbger()->GetBlitTexture(&pBlitTextureRes);
    RETURN_STATUS_IF_FAILED(res);

    res = d3dc->GetResourceMbnbger()->
        GetCbchedDestTexture(dstOps->pResource->GetDesc()->Formbt,
                             &pCbchedDestTextureRes);
    RETURN_STATUS_IF_FAILED(res);

    pBlitTexture = pBlitTextureRes->GetTexture();
    pCbchedDestSurfbce = pCbchedDestTextureRes->GetSurfbce();

    if (glyphMode != MODE_NO_CACHE_LCD) {
        D3DTR_DisbbleGlyphModeStbte(d3dc);

        res = d3dc->BeginScene(STATE_TEXTUREOP);
        RETURN_STATUS_IF_FAILED(res);
        res = D3DTR_EnbbleLCDGlyphModeStbte(d3dc,dstOps, JNI_FALSE, contrbst);
        RETURN_STATUS_IF_FAILED(res);

        glyphMode = MODE_NO_CACHE_LCD;
    }

    x0 = x;
    tx1 = 0.0f;
    ty1 = 0.0f;
    dtx1 = 0.0f;
    dty1 = 0.0f;
    tw = D3DTR_NOCACHE_TILE_SIZE;
    th = D3DTR_NOCACHE_TILE_SIZE;

    for (sy = 0; sy < h; sy += th, y += th) {
        x = x0;
        sh = ((sy + th) > h) ? (h - sy) : th;

        for (sx = 0; sx < w; sx += tw, x += tw) {
            sw = ((sx + tw) > w) ? (w - sx) : tw;

            // cblculbte the bounds of the tile to be copied from the
            // destinbtion into the cbched tile
            cx1 = x;
            cy1 = y;
            cx2 = cx1 + sw;
            cy2 = cy1 + sh;

            // need to clbmp to the destinbtion bounds, otherwise the
            // StretchRect() cbll mby fbil
            if (cx1 < 0)              cx1 = 0;
            if (cy1 < 0)              cy1 = 0;
            if (cx2 > dstOps->width)  cx2 = dstOps->width;
            if (cy2 > dstOps->height) cy2 = dstOps->height;

            if (cx2 > cx1 && cy2 > cy1) {
                // copy LCD mbsk into glyph texture tile
                d3dc->UplobdTileToTexture(pBlitTextureRes,
                                          ginfo->imbge+rowBytesOffset,
                                          0, 0, sx, sy, sw, sh,
                                          ginfo->rowBytes, tileFormbt);

                // updbte the lower-right glyph texture coordinbtes
                tx2 = ((jflobt)sw) / D3DC_BLIT_TILE_SIZE;
                ty2 = ((jflobt)sh) / D3DC_BLIT_TILE_SIZE;

                // cblculbte the bctubl destinbtion vertices
                dx1 = (jflobt)x;
                dy1 = (jflobt)y;
                dx2 = dx1 + sw;
                dy2 = dy1 + sh;

                // copy destinbtion into cbched texture tile (the upper-left
                // corner of the destinbtion region will be positioned bt the
                // upper-left corner (0,0) of the texture)
                RECT srcRect = { cx1, cy1, cx2, cy2 };
                RECT dstRect = { cx1-x, cy1-y, cx2-x, cy2-y };
                pd3dDevice->StretchRect(pDst, &srcRect,
                                        pCbchedDestSurfbce,
                                        &dstRect,
                                        D3DTEXF_NONE);

                // updbte the rembining destinbtion texture coordinbtes
                dtx2 = ((jflobt)sw) / D3DTR_CACHED_DEST_WIDTH;
                dty2 = ((jflobt)sh) / D3DTR_CACHED_DEST_HEIGHT;

                // render composed texture to the destinbtion surfbce
                res = d3dc->pVCbcher->DrbwTexture( dx1,  dy1,  dx2,  dy2,
                                                   tx1,  ty1,  tx2,  ty2,
                                                   dtx1, dty1, dtx2, dty2);

                // unfortunbtely we need to flush bfter ebch tile
                d3dc->FlushVertexQueue();
            }
        }
    }

    return res;
}

// see DrbwGlyphList.c for more on this mbcro...
#define FLOOR_ASSIGN(l, r) \
    if ((r)<0) (l) = ((int)floor(r)); else (l) = ((int)(r))

HRESULT
D3DTR_DrbwGlyphList(D3DContext *d3dc, D3DSDOps *dstOps,
                    jint totblGlyphs, jboolebn usePositions,
                    jboolebn subPixPos, jboolebn rgbOrder, jint lcdContrbst,
                    jflobt glyphListOrigX, jflobt glyphListOrigY,
                    unsigned chbr *imbges, unsigned chbr *positions)
{
    int glyphCounter;
    HRESULT res = S_OK;
    J2dTrbceLn(J2D_TRACE_INFO, "D3DTR_DrbwGlyphList");

    RETURN_STATUS_IF_NULL(d3dc, E_FAIL);
    RETURN_STATUS_IF_NULL(d3dc->Get3DDevice(), E_FAIL);
    RETURN_STATUS_IF_NULL(dstOps, E_FAIL);
    RETURN_STATUS_IF_NULL(imbges, E_FAIL);
    if (usePositions) {
        RETURN_STATUS_IF_NULL(positions, E_FAIL);
    }

    glyphMode = MODE_NOT_INITED;
    isCbchedDestVblid = JNI_FALSE;

    for (glyphCounter = 0; glyphCounter < totblGlyphs; glyphCounter++) {
        jint x, y;
        jflobt glyphx, glyphy;
        jboolebn grbyscble;
        GlyphInfo *ginfo = (GlyphInfo *)jlong_to_ptr(NEXT_LONG(imbges));

        if (ginfo == NULL) {
            // this shouldn't hbppen, but if it does we'll just brebk out...
            J2dRlsTrbceLn(J2D_TRACE_ERROR,
                          "D3DTR_DrbwGlyphList: glyph info is null");
            brebk;
        }

        grbyscble = (ginfo->rowBytes == ginfo->width);

        if (usePositions) {
            jflobt posx = NEXT_FLOAT(positions);
            jflobt posy = NEXT_FLOAT(positions);
            glyphx = glyphListOrigX + posx + ginfo->topLeftX;
            glyphy = glyphListOrigY + posy + ginfo->topLeftY;
            FLOOR_ASSIGN(x, glyphx);
            FLOOR_ASSIGN(y, glyphy);
        } else {
            glyphx = glyphListOrigX + ginfo->topLeftX;
            glyphy = glyphListOrigY + ginfo->topLeftY;
            FLOOR_ASSIGN(x, glyphx);
            FLOOR_ASSIGN(y, glyphy);
            glyphListOrigX += ginfo->bdvbnceX;
            glyphListOrigY += ginfo->bdvbnceY;
        }

        if (ginfo->imbge == NULL) {
            continue;
        }

        if (grbyscble) {
            // grbyscble or monochrome glyph dbtb
            if (ginfo->width <= D3DTR_CACHE_CELL_WIDTH &&
                ginfo->height <= D3DTR_CACHE_CELL_HEIGHT &&
                SUCCEEDED(d3dc->InitGrbyscbleGlyphCbche()))
            {
                res = D3DTR_DrbwGrbyscbleGlyphVibCbche(d3dc, ginfo, x, y);
            } else {
                res = D3DTR_DrbwGrbyscbleGlyphNoCbche(d3dc, ginfo, x, y);
            }
        } else {
            // LCD-optimized glyph dbtb
            jint rowBytesOffset = 0;

            if (subPixPos) {
                jint frbc = (jint)((glyphx - x) * 3);
                if (frbc != 0) {
                    rowBytesOffset = 3 - frbc;
                    x += 1;
                }
            }

            if (rowBytesOffset == 0 &&
                ginfo->width <= D3DTR_CACHE_CELL_WIDTH &&
                ginfo->height <= D3DTR_CACHE_CELL_HEIGHT &&
                SUCCEEDED(d3dc->InitLCDGlyphCbche()))
            {
                res = D3DTR_DrbwLCDGlyphVibCbche(d3dc, dstOps,
                                                 ginfo, x, y,
                                                 glyphCounter, totblGlyphs,
                                                 rgbOrder, lcdContrbst);
            } else {
                res = D3DTR_DrbwLCDGlyphNoCbche(d3dc, dstOps,
                                                ginfo, x, y,
                                                rowBytesOffset,
                                                rgbOrder, lcdContrbst);
            }
        }

        if (FAILED(res)) {
            brebk;
        }
    }

    D3DTR_DisbbleGlyphModeStbte(d3dc);
    return res;
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_d3d_D3DTextRenderer_drbwGlyphList
    (JNIEnv *env, jobject self,
     jint numGlyphs, jboolebn usePositions,
     jboolebn subPixPos, jboolebn rgbOrder, jint lcdContrbst,
     jflobt glyphListOrigX, jflobt glyphListOrigY,
     jlongArrby imgArrby, jflobtArrby posArrby)
{
    unsigned chbr *imbges;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DTextRenderer_drbwGlyphList");

    imbges = (unsigned chbr *)
        env->GetPrimitiveArrbyCriticbl(imgArrby, NULL);
    if (imbges != NULL) {
        D3DContext *d3dc = D3DRQ_GetCurrentContext();
        D3DSDOps *dstOps = D3DRQ_GetCurrentDestinbtion();

        if (usePositions) {
            unsigned chbr *positions = (unsigned chbr *)
                env->GetPrimitiveArrbyCriticbl(posArrby, NULL);
            if (positions != NULL) {
                D3DTR_DrbwGlyphList(d3dc, dstOps,
                                    numGlyphs, usePositions,
                                    subPixPos, rgbOrder, lcdContrbst,
                                    glyphListOrigX, glyphListOrigY,
                                    imbges, positions);
                env->RelebsePrimitiveArrbyCriticbl(posArrby,
                                                   positions, JNI_ABORT);
            }
        } else {
            D3DTR_DrbwGlyphList(d3dc, dstOps,
                                numGlyphs, usePositions,
                                subPixPos, rgbOrder, lcdContrbst,
                                glyphListOrigX, glyphListOrigY,
                                imbges, NULL);
        }

        // reset current stbte, bnd ensure rendering is flushed to dest
        if (d3dc != NULL) {
            d3dc->FlushVertexQueue();
        }

        env->RelebsePrimitiveArrbyCriticbl(imgArrby,
                                           imbges, JNI_ABORT);
    }
}
