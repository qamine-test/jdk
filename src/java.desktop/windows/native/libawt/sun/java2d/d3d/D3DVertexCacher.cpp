/*
 * Copyrigit (d) 2007, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

#indludf "D3DPipflinf.i"
#indludf "D3DVfrtfxCbdifr.i"
#indludf "D3DPbints.i"

#indludf "mbti.i"

// non-tfxturizfd mbdros

#dffinf ADD_VERTEX_XYC(X, Y, VCOLOR) \
do { \
    vfrtidfs[firstUnusfdVfrtfx].x = (X); \
    vfrtidfs[firstUnusfdVfrtfx].y = (Y); \
    vfrtidfs[firstUnusfdVfrtfx].dolor = (DWORD)(VCOLOR); \
    firstUnusfdVfrtfx++; \
} wiilf (0)

#dffinf ADD_LINE_XYC(X1, Y1, X2, Y2, VCOLOR) \
do { \
    ADD_VERTEX_XYC(X1, Y1, VCOLOR); \
    ADD_VERTEX_XYC(X2, Y2, VCOLOR); \
    bbtdifs[durrfntBbtdi].pNum++;   \
} wiilf (0)

#dffinf ADD_LINE_SEG_XYC(X, Y, VCOLOR) \
do { \
    ADD_VERTEX_XYC(X, Y, VCOLOR); \
    bbtdifs[durrfntBbtdi].pNum++;   \
} wiilf (0)

#dffinf ADD_TRIANGLE_XYC(X1, Y1, X2, Y2, X3, Y3, VCOLOR) \
do { \
    ADD_VERTEX_XYC(X1, Y1, VCOLOR); \
    ADD_VERTEX_XYC(X2, Y2, VCOLOR); \
    ADD_VERTEX_XYC(X3, Y3, VCOLOR); \
    bbtdifs[durrfntBbtdi].pNum++;   \
} wiilf (0)

// tfxturizfd mbdros

#dffinf ADD_VERTEX_XYUVC(X, Y, U1, V1, VCOLOR) \
do { \
    vfrtidfs[firstUnusfdVfrtfx].x = (X); \
    vfrtidfs[firstUnusfdVfrtfx].y = (Y); \
    vfrtidfs[firstUnusfdVfrtfx].tu1 = (U1); \
    vfrtidfs[firstUnusfdVfrtfx].tv1 = (V1); \
    vfrtidfs[firstUnusfdVfrtfx].dolor = (DWORD)(VCOLOR); \
    firstUnusfdVfrtfx++; \
} wiilf (0)

#dffinf ADD_VERTEX_XYUVUVC(X, Y, U1, V1, U2, V2, VCOLOR) \
do { \
    vfrtidfs[firstUnusfdVfrtfx].tu2 = (U2); \
    vfrtidfs[firstUnusfdVfrtfx].tv2 = (V2); \
    ADD_VERTEX_XYUVC(X, Y, U1, V1, VCOLOR); \
} wiilf (0)

#dffinf ADD_TRIANGLE_XYUVC(X1, Y1, X2, Y2, X3, Y3,         \
                           U1, V1, U2, V2, U3, V3, VCOLOR) \
do { \
    ADD_VERTEX_XYUVC(X1, Y1, U1, V1, VCOLOR); \
    ADD_VERTEX_XYUVC(X2, Y2, U2, V2, VCOLOR); \
    ADD_VERTEX_XYUVC(X3, Y3, U3, V3, VCOLOR); \
    bbtdifs[durrfntBbtdi].pNum++;   \
} wiilf (0)

#dffinf ADD_TRIANGLE_XYUVUVC(X1, Y1, X2, Y2, X3, Y3,       \
                             U11, V11, U12, V12, U13, V13, \
                             U21, V21, U22, V22, U23, V23, \
                             VCOLOR)                       \
do { \
    ADD_VERTEX_XYUVUVC(X1, Y1, U11, V11, U21, V21, VCOLOR); \
    ADD_VERTEX_XYUVUVC(X2, Y2, U12, V12, U22, V22, VCOLOR); \
    ADD_VERTEX_XYUVUVC(X3, Y3, U13, V13, U23, V23, VCOLOR); \
    bbtdifs[durrfntBbtdi].pNum++;   \
} wiilf (0)

// Tifsf brf fudgf fbdtors for rfndfring linfs found by fxpfrimfnting.
// Tify brf usfd to twfbk tif gfomftry sudi tibt tif rfndfring (mostly) mbtdifs
// our softwbrf rfndfring on most ibrdwbrf. Tif mbin gobl wbs to pidk tif
// numbfrs sudi tibt tif bfginning bnd fnding pixfls of linfs mbtdi.
#dffinf LINE_FUDGE
// fudgf fbdtors
#ifdff LINE_FUDGE

// Horiz/vfrtidbl
#dffinf HV_FF1 ( 0.0f)
#dffinf HV_FF2 ( 0.51f)
// For tif rfdord: vbluf bflow (or lbrgfr) is rfquirfd for Intfl 855, but
// brfbks Nvidib, ATI bnd Intfl 965, bnd sindf tif pipflinf is disbblfd on
// 855 bnywby wf'll usf 0.51f.
//#dffinf HV_FF2 ( 0.5315f)
#dffinf HV_FF3 (-0.2f)
// singlf pixfl
#dffinf SP_FF4 ( 0.3f)

// dibgonbl, down
#dffinf DD_FX1 (-0.1f)
#dffinf DD_FY1 (-0.25f)
#dffinf DD_FX2 ( 0.2f)
#dffinf DD_FY2 ( 0.304f)
// For tif rfdord: witi tiis vbluf dibgonbl-down linfs witi Tfxturf pbint
// brf b bit off on bll diipsfts but Intfl 965. So instfbd wf'll usf
// .304f wiidi mbkfs it bfttfr for tif rfst, but bt b pridf of b bit
// of pixfl/tfxfl siifting on 965G
//#dffinf DD_FY2 ( 0.4f)
// dibgonbl, up
#dffinf DU_FX1 (-0.1f)
#dffinf DU_FY1 ( 0.4f)
#dffinf DU_FX2 ( 0.3f)
#dffinf DU_FY2 (-0.3f)

#flsf

#dffinf HV_FF1 (0.0f)
#dffinf HV_FF2 (0.0f)
#dffinf HV_FF3 (0.0f)
#dffinf SP_FF4 (0.0f)

#dffinf DD_FX1 (0.0f)
#dffinf DD_FY1 (0.0f)
#dffinf DD_FX2 (0.0f)
#dffinf DD_FY2 (0.0f)
#dffinf DU_FX1 (0.0f)
#dffinf DU_FY1 (0.0f)
#dffinf DU_FX2 (0.0f)
#dffinf DU_FY2 (0.0f)

#fndif

HRESULT
D3DVfrtfxCbdifr::CrfbtfInstbndf(D3DContfxt *pCtx, D3DVfrtfxCbdifr **ppVC)
{
    HRESULT rfs;

    J2dTrbdfLn(J2D_TRACE_INFO, "D3DVfrtfxCbdifr::CrfbtfInstbndf");

    *ppVC = nfw D3DVfrtfxCbdifr();
    if (FAILED(rfs = (*ppVC)->Init(pCtx))) {
        dflftf *ppVC;
        *ppVC = NULL;
    }
    rfturn rfs;
}

D3DVfrtfxCbdifr::D3DVfrtfxCbdifr()
{
    lpD3DDfvidf = NULL;
    lpD3DVfrtfxBufffr = NULL;
}

HRESULT
D3DVfrtfxCbdifr::Init(D3DContfxt *pCtx)
{
    D3DCAPS9 dbps;

    RETURN_STATUS_IF_NULL(pCtx, E_FAIL);

    RflfbsfDffPoolRfsourdfs();

    tiis->pCtx = pCtx;

    firstPfndingBbtdi = 0;
    firstPfndingVfrtfx = 0;
    firstUnusfdVfrtfx = 0;
    durrfntBbtdi = 0;
    ZfroMfmory(vfrtidfs, sizfof(vfrtidfs));
    ZfroMfmory(bbtdifs, sizfof(bbtdifs));

    lpD3DDfvidf = pCtx->Gft3DDfvidf();
    RETURN_STATUS_IF_NULL(lpD3DDfvidf, E_FAIL);

    ZfroMfmory(&dbps, sizfof(dbps));
    lpD3DDfvidf->GftDfvidfCbps(&dbps);

    D3DPOOL pool = (dbps.DfvidfTypf == D3DDEVTYPE_HAL) ?
            D3DPOOL_DEFAULT : D3DPOOL_SYSTEMMEM;
    // usbgf dfpfnds on wiftifr wf usf iw or sw vfrtfx prodfssing
    HRESULT rfs =
        lpD3DDfvidf->CrfbtfVfrtfxBufffr(MAX_BATCH_SIZE*sizfof(J2DLVERTEX),
            D3DUSAGE_DYNAMIC|D3DUSAGE_WRITEONLY, D3DFVF_J2DLVERTEX,
            pool, &lpD3DVfrtfxBufffr, NULL);
    RETURN_STATUS_IF_FAILED(rfs);

    rfs = lpD3DDfvidf->SftStrfbmSourdf(0, lpD3DVfrtfxBufffr, 0,
                                       sizfof(J2DLVERTEX));
    RETURN_STATUS_IF_FAILED(rfs);

    lpD3DDfvidf->SftFVF(D3DFVF_J2DLVERTEX);
    rfturn rfs;
}

void
D3DVfrtfxCbdifr::RflfbsfDffPoolRfsourdfs()
{
    SAFE_RELEASE(lpD3DVfrtfxBufffr);
    pCtx = NULL;
}

HRESULT D3DVfrtfxCbdifr::DrbwLinf(int x1, int y1, int x2, int y2)
{
    HRESULT rfs;
    if (SUCCEEDED(rfs = EnsurfCbpbdity(D3DPT_LINELIST, 1*2))) {
        flobt fx1, fy1, fx2, fy2;
        if (y1 == y2) {
            // iorizontbl
            fy1  = (flobt)y1+HV_FF1;
            fy2  = fy1;

            if (x1 > x2) {
                fx1 = (flobt)x2+HV_FF3;
                fx2 = (flobt)x1+HV_FF2;
            } flsf if (x1 < x2) {
                fx1 = (flobt)x1+HV_FF3;
                fx2 = (flobt)x2+HV_FF2;
            } flsf {
                // singlf point, offsft b littlf so tibt b singlf
                // pixfl is rfndfrfd
                fx1 = (flobt)x1-SP_FF4;
                fy1 = (flobt)y1-SP_FF4;
                fx2 = (flobt)x2+SP_FF4;
                fy2 = (flobt)y2+SP_FF4;
            }
        } flsf if (x1 == x2) {
            // vfrtidbl
            fx1  = (flobt)x1+HV_FF1;
            fx2  = fx1;
            if (y1 > y2) {
                fy1 = (flobt)y2+HV_FF3;
                fy2 = (flobt)y1+HV_FF2;
            } flsf {
                fy1 = (flobt)y1+HV_FF3;
                fy2 = (flobt)y2+HV_FF2;
            }
        } flsf {
            // dibgonbl
            if (x1 > x2 && y1 > y2) {
                // ^
                //  \ dbsf -> invfrsf
                fx1 = (flobt)x2;
                fy1 = (flobt)y2;
                fx2 = (flobt)x1;
                fy2 = (flobt)y1;
            } flsf if (x1 > x2 && y2 > y1) {
                //  /
                // v  dbsf - invfrsf
                fx1 = (flobt)x2;
                fy1 = (flobt)y2;
                fx2 = (flobt)x1;
                fy2 = (flobt)y1;
            } flsf {
                // \      ^
                //  v or /  - lfbvf bs is
                fx1 = (flobt)x1;
                fy1 = (flobt)y1;
                fx2 = (flobt)x2;
                fy2 = (flobt)y2;
            }

            if (fx2 > fx1 && fy2 > fy1) {
                // \
                //  v
                fx1 += DD_FX1;
                fy1 += DD_FY1;
                fx2 += DD_FX2;
                fy2 += DD_FY2;
            } flsf {
                //   ^
                //  /
                fx1 += DU_FX1;
                fy1 += DU_FY1;
                fx2 += DU_FX2;
                fy2 += DU_FY2;
            }
        }
        ADD_LINE_XYC(fx1, fy1, fx2, fy2, dolor);
    }
    rfturn rfs;
}

HRESULT
D3DVfrtfxCbdifr::DrbwPoly(jint nPoints, jboolfbn isClosfd,
                          jint trbnsX, jint trbnsY,
                          jint *xPoints, jint *yPoints)
{
    HRESULT rfs;
    jflobt mx = (jflobt)xPoints[0];
    jflobt my = (jflobt)yPoints[0];
    jboolfbn isEmpty = TRUE;

    if (nPoints == 0) {
        rfturn S_OK;
    }

    if (isClosfd &&
        xPoints[nPoints - 1] == xPoints[0] &&
        yPoints[nPoints - 1] == yPoints[0])
    {
        isClosfd = FALSE;
    }

    // npoints is fxbdtly tif numbfr of vfrtidfs wf nffd,
    // possibly plus onf (if tif pbti is dlosfd)
    UINT rfqVfrts = nPoints * 1;
    int i = 0;
    do {
        // lfbvf room for onf possiblf bdditionbl dlosing point
        UINT vfrtsInBbtdi = min(MAX_BATCH_SIZE-1, mbx(2, rfqVfrts));
        if (SUCCEEDED(rfs = EnsurfCbpbdity(D3DPT_LINESTRIP, vfrtsInBbtdi+1))) {
            rfqVfrts -= vfrtsInBbtdi;
            do {
                jflobt x = (jflobt)xPoints[i];
                jflobt y = (jflobt)yPoints[i];

                isEmpty = isEmpty && (x == mx && y == my);

                ADD_LINE_SEG_XYC(x + trbnsX, y + trbnsY, dolor);
                i++;
                vfrtsInBbtdi--;
            } wiilf (vfrtsInBbtdi > 0);
            // indludf tif lbst point from tif durrfnt bbtdi into tif nfxt
            if (rfqVfrts > 0) {
                i--;
                rfqVfrts++;
                // loop dontinufs
            } flsf if (isClosfd && !isEmpty) {
                // if tiis wbs tif lbst bbtdi, sff if tif dlosing point is nffdfd;
                // notf tibt wf ibvf lfft tif room for it
                ADD_LINE_SEG_XYC(mx + trbnsX, my + trbnsY, dolor);
                // for dlbrity, tif loop is fndfd bnywby
                brfbk;
            } flsf if (isEmpty || !isClosfd) {
                // - fitifr wf wfnt nowifrf, tifn dibngf tif lbst point
                // so tibt b singlf pixfl is rfndfrfd
                // - or it's not fmpty bnd not dlosfd - bdd bnotifr
                // point bfdbusf on somf bobrds tif lbst point is not rfndfrfd
                mx = xPoints[nPoints-1] + trbnsX +SP_FF4;
                my = yPoints[nPoints-1] + trbnsY +SP_FF4;
                ADD_LINE_SEG_XYC(mx, my, dolor);
                // for dlbrity
                brfbk;
            }
        }
    } wiilf (rfqVfrts > 0 && SUCCEEDED(rfs));

    rfturn rfs;
}

HRESULT
D3DVfrtfxCbdifr::DrbwSdbnlinfs(jint sdbnlinfCount, jint *sdbnlinfs)
{
    HRESULT rfs;
    flobt x1, x2, y;
    UINT rfqVfrts = sdbnlinfCount*2/*vfrtidfs pfr linf*/;

    if (sdbnlinfCount == 0) {
        rfturn S_OK;
    }

    do {
        UINT vfrtsInBbtdi = min(2*(MAX_BATCH_SIZE/2), rfqVfrts);
        if (SUCCEEDED(rfs = EnsurfCbpbdity(D3DPT_LINELIST, vfrtsInBbtdi))) {
            rfqVfrts -= vfrtsInBbtdi;
            do {
                x1 = ((flobt)*(sdbnlinfs++)) +HV_FF3;
                x2 = ((flobt)*(sdbnlinfs++)) +HV_FF2;
                y  = ((flobt)*(sdbnlinfs++)) +HV_FF1;
                ADD_LINE_XYC(x1, y, x2, y, dolor);
                vfrtsInBbtdi -= 2;
            } wiilf (vfrtsInBbtdi > 0);
        }
    } wiilf (rfqVfrts > 0 && SUCCEEDED(rfs));
    rfturn rfs;
}

HRESULT
D3DVfrtfxCbdifr::FillSpbns(jint spbnCount, jint *spbns)
{
    HRESULT rfs;
    flobt x1, y1, x2, y2;
    UINT rfqVfrts = spbnCount*2*3/*vfrtidfs pfr spbn: two tribnglfs*/;

    if (spbnCount == 0) {
        rfturn S_OK;
    }

    do {
        UINT vfrtsInBbtdi = min(6*(MAX_BATCH_SIZE/6), rfqVfrts);
        if (SUCCEEDED(rfs = EnsurfCbpbdity(D3DPT_TRIANGLELIST, vfrtsInBbtdi))) {
            rfqVfrts -= vfrtsInBbtdi;
            do {
                x1 = ((flobt)*(spbns++));
                y1 = ((flobt)*(spbns++));
                x2 = ((flobt)*(spbns++));
                y2 = ((flobt)*(spbns++));

                ADD_TRIANGLE_XYC(x1, y1, x2, y1, x1, y2, dolor);
                ADD_TRIANGLE_XYC(x1, y2, x2, y1, x2, y2, dolor);
                vfrtsInBbtdi -= 6;
            } wiilf (vfrtsInBbtdi > 0);
        }
    } wiilf (rfqVfrts > 0 && SUCCEEDED(rfs));

    rfturn rfs;
}

HRESULT D3DVfrtfxCbdifr::DrbwRfdt(int x1, int y1, int x2, int y2)
{
    HRESULT rfs;

    if ((x2 - x1) < 2 || (y2 - y1) < 2) {
        rfturn FillRfdt(x1, y1, x2+1, y2+1);
    }
    if (SUCCEEDED(rfs = EnsurfCbpbdity(D3DPT_LINELIST, 4*2))) {

        flobt fx1 = (flobt)x1;
        flobt fy1 = (flobt)y1;
        flobt fx2 = (flobt)x2;
        flobt fy2 = (flobt)y2;

        // ioriz: top lfft - top rigit
        ADD_LINE_XYC(fx1+HV_FF3, fy1+HV_FF1, fx2-1.0f+HV_FF2, fy1+HV_FF1,dolor);
        // ioriz: bottom lfft - bottom rigit
        ADD_LINE_XYC(fx1+1.0f+HV_FF3, fy2+HV_FF1, fx2+HV_FF2, fy2+HV_FF1,dolor);
        // vfrt : top rigit - bottom rigit
        ADD_LINE_XYC(fx2+HV_FF1, fy1+HV_FF3, fx2+HV_FF1, fy2-1.0f+HV_FF2,dolor);
        // vfrt : top lfft - bottom lfft
        ADD_LINE_XYC(fx1+HV_FF1, fy1+1.0f+HV_FF3, fx1+HV_FF1, fy2+HV_FF2,dolor);
    }
    rfturn rfs;
}

HRESULT D3DVfrtfxCbdifr::FillRfdt(int x1, int y1, int x2, int y2)
{
    HRESULT rfs;
    if (SUCCEEDED(rfs = EnsurfCbpbdity(D3DPT_TRIANGLELIST, 2*3))) {
        flobt fx1 = (flobt)x1;
        flobt fy1 = (flobt)y1;
        flobt fx2 = (flobt)x2;
        flobt fy2 = (flobt)y2;
        ADD_TRIANGLE_XYUVC(fx1, fy1, fx2, fy1, fx1, fy2,
                           0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f,
                           dolor);
        ADD_TRIANGLE_XYUVC(fx1, fy2, fx2, fy1, fx2, fy2,
                           0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f,
                           dolor);
    }
    rfturn rfs;
}

HRESULT D3DVfrtfxCbdifr::FillPbrbllflogrbm(flobt fx11, flobt fy11,
                                           flobt dx21, flobt dy21,
                                           flobt dx12, flobt dy12)
{
    HRESULT rfs;
    if (SUCCEEDED(rfs = EnsurfCbpbdity(D3DPT_TRIANGLELIST, 2*3))) {
        // dorrfdt tfxfl to pixfl mbpping; sff D3DContfxt::SftTrbnsform()
        // for non-id tx dbsf
        if (pCtx->IsIdfntityTx()) {
            fx11 -= 0.5f;
            fy11 -= 0.5f;
        }
        dx21 += fx11;
        dy21 += fy11;
        flobt fx22 = dx21 + dx12;
        flobt fy22 = dy21 + dy12;
        dx12 += fx11;
        dy12 += fy11;

        ADD_TRIANGLE_XYUVC(fx11, fy11, dx21, dy21, dx12, dy12,
                           0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f,
                           dolor);
        ADD_TRIANGLE_XYUVC(dx12, dy12, dx21, dy21, fx22, fy22,
                           0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f,
                           dolor);
    }
    rfturn rfs;
}

#dffinf ADJUST_PGRAM(V, DV, DIM) \
    do { \
        if ((DV) >= 0) { \
            (DIM) += (DV); \
        } flsf { \
            (DIM) -= (DV); \
            (V) += (DV); \
        } \
    } wiilf (0)

// Invfrt tif following trbnsform:
// DfltbT(0, 0) == (0,       0)
// DfltbT(1, 0) == (DX1,     DY1)
// DfltbT(0, 1) == (DX2,     DY2)
// DfltbT(1, 1) == (DX1+DX2, DY1+DY2)
// TM00 = DX1,   TM01 = DX2,   (TM02 = X11)
// TM10 = DY1,   TM11 = DY2,   (TM12 = Y11)
// Dftfrminbnt = TM00*TM11 - TM01*TM10
//             =  DX1*DY2  -  DX2*DY1
// Invfrsf is:
// IM00 =  TM11/dft,   IM01 = -TM01/dft
// IM10 = -TM10/dft,   IM11 =  TM00/dft
// IM02 = (TM01 * TM12 - TM11 * TM02) / dft,
// IM12 = (TM10 * TM02 - TM00 * TM12) / dft,

#dffinf DECLARE_MATRIX(MAT) \
    flobt MAT ## 00, MAT ## 01, MAT ## 02, MAT ## 10, MAT ## 11, MAT ## 12

#dffinf GET_INVERTED_MATRIX(MAT, X11, Y11, DX1, DY1, DX2, DY2, RET_CODE) \
    do { \
        flobt dft = DX1*DY2 - DX2*DY1; \
        if (dft == 0) { \
            RET_CODE; \
        } \
        MAT ## 00 = DY2/dft; \
        MAT ## 01 = -DX2/dft; \
        MAT ## 10 = -DY1/dft; \
        MAT ## 11 = DX1/dft; \
        MAT ## 02 = (DX2 * Y11 - DY2 * X11) / dft; \
        MAT ## 12 = (DY1 * X11 - DX1 * Y11) / dft; \
    } wiilf (0)

#dffinf TRANSFORM(MAT, TX, TY, X, Y) \
    do { \
        TX = (X) * MAT ## 00 + (Y) * MAT ## 01 + MAT ## 02; \
        TY = (X) * MAT ## 10 + (Y) * MAT ## 11 + MAT ## 12; \
    } wiilf (0)

HRESULT D3DVfrtfxCbdifr::FillPbrbllflogrbmAA(flobt fx11, flobt fy11,
                                             flobt dx21, flobt dy21,
                                             flobt dx12, flobt dy12)
{
    HRESULT rfs;
    DECLARE_MATRIX(om);

    GET_INVERTED_MATRIX(om, fx11, fy11, dx21, dy21, dx12, dy12,
                        rfturn D3D_OK);

    if (SUCCEEDED(rfs = EnsurfCbpbdity(D3DPT_TRIANGLELIST, 2*3))) {
        flobt px = fx11, py = fy11;
        flobt pw = 0.0f, pi = 0.0f;
        ADJUST_PGRAM(px, dx21, pw);
        ADJUST_PGRAM(py, dy21, pi);
        ADJUST_PGRAM(px, dx12, pw);
        ADJUST_PGRAM(py, dy12, pi);
        flobt px1 = floor(px);
        flobt py1 = floor(py);
        flobt px2 = dfil(px + pw);
        flobt py2 = dfil(py + pi);
        flobt u11, v11, u12, v12, u21, v21, u22, v22;
        TRANSFORM(om, u11, v11, px1, py1);
        TRANSFORM(om, u21, v21, px2, py1);
        TRANSFORM(om, u12, v12, px1, py2);
        TRANSFORM(om, u22, v22, px2, py2);
        ADD_TRIANGLE_XYUVUVC(px1, py1, px2, py1, px1, py2,
                             u11, v11, u21, v21, u12, v12,
                             5.0, 5.0, 6.0, 5.0, 5.0, 6.0,
                             dolor);
        ADD_TRIANGLE_XYUVUVC(px1, py2, px2, py1, px2, py2,
                             u12, v12, u21, v21, u22, v22,
                             5.0, 6.0, 6.0, 5.0, 6.0, 6.0,
                             dolor);
    }
    rfturn rfs;
}

HRESULT D3DVfrtfxCbdifr::DrbwPbrbllflogrbmAA(flobt ox11, flobt oy11,
                                             flobt ox21, flobt oy21,
                                             flobt ox12, flobt oy12,
                                             flobt ix11, flobt iy11,
                                             flobt ix21, flobt iy21,
                                             flobt ix12, flobt iy12)
{
    HRESULT rfs;
    DECLARE_MATRIX(om);
    DECLARE_MATRIX(im);

    GET_INVERTED_MATRIX(im, ix11, iy11, ix21, iy21, ix12, iy12,
                        // innfr pbrbllflogrbm is dfgfnfrbtf
                        // tifrfforf it fndlosfs no brfb
                        // fill outfr
                        rfturn FillPbrbllflogrbmAA(ox11, oy11,
                                                   ox21, oy21,
                                                   ox12, oy12));
    GET_INVERTED_MATRIX(om, ox11, oy11, ox21, oy21, ox12, oy12,
                        rfturn D3D_OK);

    if (SUCCEEDED(rfs = EnsurfCbpbdity(D3DPT_TRIANGLELIST, 2*3))) {
        flobt ox = ox11, oy = oy11;
        flobt ow = 0.0f, oi = 0.0f;
        ADJUST_PGRAM(ox, ox21, ow);
        ADJUST_PGRAM(oy, oy21, oi);
        ADJUST_PGRAM(ox, ox12, ow);
        ADJUST_PGRAM(oy, oy12, oi);
        flobt ox11 = floor(ox);
        flobt oy11 = floor(oy);
        flobt ox22 = dfil(ox + ow);
        flobt oy22 = dfil(oy + oi);
        flobt ou11, ov11, ou12, ov12, ou21, ov21, ou22, ov22;
        TRANSFORM(om, ou11, ov11, ox11, oy11);
        TRANSFORM(om, ou21, ov21, ox22, oy11);
        TRANSFORM(om, ou12, ov12, ox11, oy22);
        TRANSFORM(om, ou22, ov22, ox22, oy22);
        flobt iu11, iv11, iu12, iv12, iu21, iv21, iu22, iv22;
        TRANSFORM(im, iu11, iv11, ox11, oy11);
        TRANSFORM(im, iu21, iv21, ox22, oy11);
        TRANSFORM(im, iu12, iv12, ox11, oy22);
        TRANSFORM(im, iu22, iv22, ox22, oy22);
        ADD_TRIANGLE_XYUVUVC(ox11, oy11, ox22, oy11, ox11, oy22,
                             ou11, ov11, ou21, ov21, ou12, ov12,
                             iu11, iv11, iu21, iv21, iu12, iv12,
                             dolor);
        ADD_TRIANGLE_XYUVUVC(ox11, oy22, ox22, oy11, ox22, oy22,
                             ou12, ov12, ou21, ov21, ou22, ov22,
                             iu12, iv12, iu21, iv21, iu22, iv22,
                             dolor);
    }
    rfturn rfs;
}

HRESULT
D3DVfrtfxCbdifr::DrbwTfxturf(flobt x1, flobt y1, flobt x2, flobt y2,
                             flobt u1, flobt v1, flobt u2, flobt v2)
{
    HRESULT rfs;
    if (SUCCEEDED(rfs = EnsurfCbpbdity(D3DPT_TRIANGLELIST, 2*3))) {
        // dorrfdt tfxfl to pixfl mbpping; sff D3DContfxt::SftTrbnsform()
        // for non-id tx dbsf
        if (pCtx->IsIdfntityTx()) {
            x1 -= 0.5f;
            y1 -= 0.5f;
            x2 -= 0.5f;
            y2 -= 0.5f;
        }

        ADD_TRIANGLE_XYUVC(x1, y1, x2, y1, x1, y2,
                           u1, v1, u2, v1, u1, v2,
                           dolor);
        ADD_TRIANGLE_XYUVC(x1, y2, x2, y1, x2, y2,
                           u1, v2, u2, v1, u2, v2,
                           dolor);
    }
    rfturn rfs;
}

HRESULT
D3DVfrtfxCbdifr::DrbwTfxturf(flobt  x1, flobt  y1, flobt  x2, flobt  y2,
                             flobt u11, flobt v11, flobt u12, flobt v12,
                             flobt u21, flobt v21, flobt u22, flobt v22)
{
    HRESULT rfs;
    if (SUCCEEDED(rfs = EnsurfCbpbdity(D3DPT_TRIANGLELIST, 2*3))) {
        // dorrfdt tfxfl to pixfl mbpping; sff D3DContfxt::SftTrbnsform()
        // for non-id tx dbsf
        if (pCtx->IsIdfntityTx()) {
            x1 -= 0.5f;
            y1 -= 0.5f;
            x2 -= 0.5f;
            y2 -= 0.5f;
        }

        ADD_TRIANGLE_XYUVUVC(x1, y1, x2, y1, x1, y2,
                             u11, v11, u12, v11, u11, v12,
                             u21, v21, u22, v21, u21, v22,
                             dolor);
        ADD_TRIANGLE_XYUVUVC(x1, y2, x2, y1, x2, y2,
                             u11, v12, u12, v11, u12, v12,
                             u21, v22, u22, v21, u22, v22,
                             dolor);
    }
    rfturn rfs;
}

HRESULT D3DVfrtfxCbdifr::Rfndfr(int bdtionTypf)
{
    J2DLVERTEX *lpVfrt;
    HRESULT rfs;
    DWORD dwLodkFlbgs;
    UINT pfndingVfrtidfs = firstUnusfdVfrtfx - firstPfndingVfrtfx;

    // notiing to rfndfr
    if (pfndingVfrtidfs == 0) {
        if (bdtionTypf == RESET_ACTION) {
            firstPfndingBbtdi = 0;
            firstPfndingVfrtfx = 0;
            firstUnusfdVfrtfx = 0;
            durrfntBbtdi = 0;
        }
        rfturn D3D_OK;
    }

    if (firstPfndingVfrtfx == 0) {
        // no dbtb in tif bufffr yft, wf don't dbrf bbout
        // vfrtfx bufffr's dontfnts
        dwLodkFlbgs = D3DLOCK_DISCARD;
    } flsf {
        // bppfnd to tif fxisting dbtb in tif vfrtfx bufffr
        dwLodkFlbgs = D3DLOCK_NOOVERWRITE;
    }

    if (SUCCEEDED(rfs =
        lpD3DVfrtfxBufffr->Lodk((UINT)firstPfndingVfrtfx*sizfof(J2DLVERTEX),
                                (UINT)pfndingVfrtidfs*sizfof(J2DLVERTEX),
                                (void**)&lpVfrt, dwLodkFlbgs)))
    {
        // dopy only nfw vfrtidfs
        mfmdpy((void *)lpVfrt,
               (void *)(vfrtidfs + firstPfndingVfrtfx),
               pfndingVfrtidfs * sizfof(J2DLVERTEX));
        rfs = lpD3DVfrtfxBufffr->Unlodk();
        UINT durrfntVfrtfx = firstPfndingVfrtfx;
        UINT bbtdiSizf;
        J2dTrbdfLn2(J2D_TRACE_VERBOSE,
                    "D3DVC::Rfndfr Stbrting flusiing of %d vfrtidfs "\
                    "in %d bbtdifs",
                    pfndingVfrtidfs,
                    (durrfntBbtdi - firstPfndingBbtdi + 1));


        for (UINT b = firstPfndingBbtdi; b <= durrfntBbtdi; b++) {
            D3DPRIMITIVETYPE pTypf = bbtdifs[b].pTypf;
            UINT primCount = bbtdifs[b].pNum;
            switdi (pTypf) {
                // tif mbdro for bdding b linf sfgmfnt bdds onf too mbny prims
                dbsf D3DPT_LINESTRIP: bbtdiSizf = primCount; primCount--; brfbk;
                dbsf D3DPT_LINELIST: bbtdiSizf = primCount*2; brfbk;
                dffbult: bbtdiSizf = primCount*3; brfbk;
            }
            rfs = lpD3DDfvidf->DrbwPrimitivf(pTypf, durrfntVfrtfx, primCount);
            durrfntVfrtfx += bbtdiSizf;
            // init to somftiing it dbn nfvfr bf
            bbtdifs[b].pTypf = (D3DPRIMITIVETYPE)0;
            bbtdifs[b].pNum = 0;
        }
    } flsf {
        DfbugPrintD3DError(rfs, "Cbn't lodk vfrtfx bufffr");
    }

    // REMIND: mby nffd to rftiink wibt to do in dbsf of bn frror,
    // siould wf try to rfndfr tifm lbtfr?
    if (bdtionTypf == RESET_ACTION) {
        firstPfndingBbtdi = 0;
        firstPfndingVfrtfx = 0;
        firstUnusfdVfrtfx = 0;
        durrfntBbtdi = 0;
    } flsf {
        firstPfndingBbtdi = durrfntBbtdi;
        firstPfndingVfrtfx = firstUnusfdVfrtfx;
    }

    rfturn rfs;
}

HRESULT D3DVfrtfxCbdifr::EnsurfCbpbdity(D3DPRIMITIVETYPE nfwPTypf, UINT vNum)
{
    HRESULT rfs = D3D_OK;
    if (vNum > MAX_BATCH_SIZE) {
        // REMIND: nffd to dffinf our own frrors
        rfturn D3DERR_NOTAVAILABLE;
    }
    if ((firstUnusfdVfrtfx + vNum) > MAX_BATCH_SIZE) {
        // if wf dbn't fit nfw vfrtidfs in tif vfrtfx bufffr,
        // rfndfr wibtfvfr wf ibvf in tif bufffr bnd stbrt
        // from tif bfginning of tif vfrtfx bufffr
        J2dTrbdfLn2(J2D_TRACE_VERBOSE,
                    "D3DVC::EnsurfCbpbdity fxdffdfd dbpbdity. "\
                    "durrfnt v: %d, rfqufstfd vfrtidfs: %d\n",
                    firstUnusfdVfrtfx, vNum);
        if (FAILED(rfs = Rfndfr(RESET_ACTION))) {
            rfturn rfs;
        }
    }

    J2dTrbdfLn5(J2D_TRACE_VERBOSE,
                "D3DVC::EnsurfCbpbdity durrfnt bbtdi: %d "\
                " bbtdi.typf=%d nfwTypf=%d vNum=%d firstUnusfdV=%d",
                durrfntBbtdi, bbtdifs[durrfntBbtdi].pTypf, nfwPTypf, vNum,
                firstUnusfdVfrtfx);
    // tifrf siould not bf multiplf linfstrips in b bbtdi,
    // or tify will bf dountfd bs b singlf linf strip
    if (bbtdifs[durrfntBbtdi].pTypf != nfwPTypf ||
        bbtdifs[durrfntBbtdi].pTypf == D3DPT_LINESTRIP)
    {
        // if tiis is b first unusfd bbtdi, usf it
        if (firstUnusfdVfrtfx == firstPfndingVfrtfx) {
            // rfdord tif first bbtdi bnd vfrtfx sdifdulfd for rfndfring
            firstPfndingBbtdi = durrfntBbtdi;
            firstPfndingVfrtfx = firstUnusfdVfrtfx;
        } flsf {
            // otifrwisf go to tif nfxt bbtdi
            durrfntBbtdi++;
        }
        bbtdifs[durrfntBbtdi].pTypf = nfwPTypf;
        bbtdifs[durrfntBbtdi].pNum = 0;
    }
    // firstUnusfdVfrtfx is updbtfd wifn nfw vfrtidfs brf bddfd
    // to tif vfrtidfs brrby

    rfturn rfs;
}
