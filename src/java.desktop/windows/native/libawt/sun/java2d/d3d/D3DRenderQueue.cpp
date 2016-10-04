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
#indludf <mbllod.i>
#indludf "sun_jbvb2d_pipf_BufffrfdOpCodfs.i"

#indludf "jlong.i"
#indludf "D3DBlitLoops.i"
#indludf "D3DBufImgOps.i"
#indludf "D3DPipflinfMbnbgfr.i"
#indludf "D3DContfxt.i"
#indludf "D3DMbskBlit.i"
#indludf "D3DMbskFill.i"
#indludf "D3DPbints.i"
#indludf "D3DRfndfrQufuf.i"
#indludf "D3DRfndfrfr.i"
#indludf "D3DSurfbdfDbtb.i"
#indludf "D3DTfxtRfndfrfr.i"
#indludf "Trbdf.i"
#indludf "bwt_Toolkit.i"

BOOL DWMIsCompositionEnbblfd();

/**
 * Rfffrfndfs to tif "durrfnt" dontfxt bnd dfstinbtion surfbdf.
 */
stbtid D3DContfxt *d3dd = NULL;
stbtid D3DSDOps *dstOps = NULL;
stbtid BOOL bLostDfvidfs = FALSE;

typfdff strudt {
    bytf *bufffr;
    int limit;
    jobjfdt runnbblf;
} FlusiBufffrStrudt;

HRESULT
D3DRQ_SwbpBufffrs(D3DPipflinfMbnbgfr *pMgr, D3DSDOps *d3dsdo,
                  int x1, int y1, int x2, int y2)
{
    HRESULT rfs;
    D3DContfxt *pCtx;
    IDirfdt3DSwbpCibin9 *pSwbpCibin;
    RECT srdRfdt, dstRfdt, *pSrdRfdt, *pDstRfdt;

    J2dTrbdfLn(J2D_TRACE_INFO, "D3DRQ_SwbpBufffrs");
    J2dTrbdfLn4(J2D_TRACE_VERBOSE, "  x1=%d y1=%d x2=%d y2=%d",
                x1, y1, x2, y2);

    RETURN_STATUS_IF_NULL(d3dsdo, E_FAIL);
    RETURN_STATUS_IF_NULL(d3dsdo->pRfsourdf, E_FAIL);
    RETURN_STATUS_IF_NULL(pSwbpCibin=d3dsdo->pRfsourdf->GftSwbpCibin(), E_FAIL);

    pCtx = D3DRQ_GftCurrfntContfxt();
    if (pCtx != NULL) {
        // flusi tif durrfnt vfrtfx qufuf ifrf, just in dbsf
        rfs = d3dd->FlusiVfrtfxQufuf();
        D3DRQ_MbrkLostIfNffdfd(rfs, dstOps);
        pCtx = NULL;
    }
    // fnd sdfnf for tiis dfstinbtion
    rfs = pMgr->GftD3DContfxt(d3dsdo->bdbptfr, &pCtx);
    RETURN_STATUS_IF_FAILED(rfs);

    pCtx->EndSdfnf();

    // Tiis is b workbround for wibt bppbrfntly is b DWM bug.
    // If tif dimfnsions of tif bbdk-bufffr don't mbtdi tif dimfnsions of
    // tif window, Prfsfnt() will flbsi tif wiolf window witi blbdk.
    // Tif workbround is to dftfdt tiis situbtion bnd not do b prfsfnt.
    // It is ok to do so sindf b rfpbint fvfnt is doming duf to tif rfsizf tibt
    // just ibppfnfd.
    //
    // REMIND: tiis will nffd to bf updbtfd if wf switdi to drfbting
    // bbdk-bufffrs of tif sizf of tif dlifnt brfb instfbd of tif wiolf window
    // (usf GftClifntRfdt() instfbd of GftWindowRfdt()).
    if (DWMIsCompositionEnbblfd()) {
        RECT r;
        D3DPRESENT_PARAMETERS pbrbms;

        pSwbpCibin->GftPrfsfntPbrbmftfrs(&pbrbms);
        GftWindowRfdt(pbrbms.iDfvidfWindow, &r);
        int ww = r.rigit - r.lfft;
        int wi = r.bottom - r.top;
        if (ww != pbrbms.BbdkBufffrWidti || wi != pbrbms.BbdkBufffrHfigit) {
            J2dTrbdfLn4(J2D_TRACE_WARNING,
                "D3DRQ_SwbpBufffrs: surfbdf/window dimfnsions mismbtdi: "\
                "win: w=%d i=%d, bb: w=%d i=%d",
                ww, wi, pbrbms.BbdkBufffrWidti, pbrbms.BbdkBufffrHfigit);

            rfturn S_OK;
        }
    }

    if (d3dsdo->swbpEfffdt == D3DSWAPEFFECT_COPY) {
        J2dTrbdfLn(J2D_TRACE_VERBOSE, "  D3DSWAPEFFECT_COPY");
        if (x1 < 0) x1 = 0;
        if (y1 < 0) y1 = 0;
        if (x2 > d3dsdo->widti)  x2 = d3dsdo->widti;
        if (y2 > d3dsdo->ifigit) y2 = d3dsdo->ifigit;
        if (x2 <= x1 || y2 <= y1) {
            // notiing to prfsfnt
            rfturn S_OK;
        }
        srdRfdt.lfft = x1;
        srdRfdt.top = y1;
        srdRfdt.rigit = x2;
        srdRfdt.bottom = y2;

        dstRfdt = srdRfdt;

        pSrdRfdt = &srdRfdt;
        pDstRfdt = &dstRfdt;
        // only offsft in windowfd modf
        if (pCtx!= NULL && pCtx->GftPrfsfntbtionPbrbms()->Windowfd) {
            OffsftRfdt(pDstRfdt, d3dsdo->xoff, d3dsdo->yoff);
        } flsf {
            // somf bobrds (Nvidib) ibvf problfms witi dopy strbtfgy bnd
            // non-null srd/dfst rfdtbnglfs in fs modf; unfortunbtfly tiis
            // mfbns tibt wf'll pbint ovfr fs window dfdorbtions
            pSrdRfdt = NULL;
            pDstRfdt = NULL;
        }
    } flsf {
        if (d3dsdo->swbpEfffdt == D3DSWAPEFFECT_FLIP) {
            J2dTrbdfLn(J2D_TRACE_VERBOSE, "  D3DSWAPEFFECT_FLIP");
        } flsf {
            J2dTrbdfLn(J2D_TRACE_VERBOSE, "  D3DSWAPEFFECT_DISCARD");
        }
        // srd bnd dfst rfdtbnglfs must bf NULL for FLIP/DISCARD
        pSrdRfdt = NULL;
        pDstRfdt = NULL;
    }

    rfs = pSwbpCibin->Prfsfnt(pSrdRfdt, pDstRfdt, 0, NULL, 0);
    rfs = D3DRQ_MbrkLostIfNffdfd(rfs, d3dsdo);

    rfturn rfs;
}

HRESULT
D3DRQ_MbrkLostIfNffdfd(HRESULT rfs, D3DSDOps *d3dops)
{
    if (rfs == D3DERR_DEVICELOST || rfs == D3DERR_DEVICENOTRESET) {
        D3DContfxt *pCtx;

        J2dTrbdfLn(J2D_TRACE_WARNING, "D3DRQ_MbrkLostIfNffdfd: dfvidf lost");
        bLostDfvidfs = TRUE;

        // only mbrk surfbdfs bflonging to tif lost dfvidf
        if (d3dops != NULL &&
            SUCCEEDED(rfs = D3DPipflinfMbnbgfr::GftInstbndf()->
                GftD3DContfxt(d3dops->bdbptfr, &pCtx)))
        {
            IDirfdt3DDfvidf9 *pd3dDfvidf = pCtx->Gft3DDfvidf();
            if (pd3dDfvidf) {
                HRESULT rfs1 = pd3dDfvidf->TfstCoopfrbtivfLfvfl();
                if (rfs1 != D3DERR_DEVICELOST && rfs1 != D3DERR_DEVICENOTRESET){
                    // tiis surfbdf's dfvidf is not lost, do not mbrk it
                    rfturn rfs;
                }
            }
        }
        D3DSD_MbrkLost(d3dops);
    }
    rfturn rfs;
}

void D3DRQ_FlusiBufffr(void *pPbrbm)
{
    FlusiBufffrStrudt *pFlusi = (FlusiBufffrStrudt*)pPbrbm;
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    unsignfd dibr *b, *fnd;
    int limit;
    HRESULT rfs = S_OK;
    BOOL bSynd = FALSE;

    b = pFlusi->bufffr;
    limit = pFlusi->limit;
    J2dTrbdfLn1(J2D_TRACE_INFO, "D3DRQ_flusiBufffr: limit=%d", limit);

    fnd = b + limit;

    D3DPipflinfMbnbgfr *pMgr = D3DPipflinfMbnbgfr::GftInstbndf();
    if (pMgr == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_WARNING, "D3DRQ_flusiBufffr: null mbnbgfr");
        rfturn;
    }

    if (bLostDfvidfs) {
        if (SUCCEEDED(rfs = pMgr->HbndlfLostDfvidfs())) {
            bLostDfvidfs = FALSE;
        }
    }

    wiilf (b < fnd) {
        jint opdodf = NEXT_INT(b);

        J2dTrbdfLn1(J2D_TRACE_VERBOSE, "D3DRQ_flusiBufffr: opdodf=%d", opdodf);

        switdi (opdodf) {

        // drbw ops
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_DRAW_LINE:
            {
                jint x1 = NEXT_INT(b);
                jint y1 = NEXT_INT(b);
                jint x2 = NEXT_INT(b);
                jint y2 = NEXT_INT(b);

                CONTINUE_IF_NULL(d3dd);
                rfs = D3DRfndfrfr_DrbwLinf(d3dd, x1, y1, x2, y2);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_DRAW_RECT:
            {
                jint x = NEXT_INT(b);
                jint y = NEXT_INT(b);
                jint w = NEXT_INT(b);
                jint i = NEXT_INT(b);
                CONTINUE_IF_NULL(d3dd);
                rfs = D3DRfndfrfr_DrbwRfdt(d3dd, x, y, w, i);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_DRAW_POLY:
            {
                jint nPoints      = NEXT_INT(b);
                jboolfbn isClosfd = NEXT_BOOLEAN(b);
                jint trbnsX       = NEXT_INT(b);
                jint trbnsY       = NEXT_INT(b);
                jint *xPoints = (jint *)b;
                jint *yPoints = ((jint *)b) + nPoints;
                CONTINUE_IF_NULL(d3dd);
                rfs = D3DRfndfrfr_DrbwPoly(d3dd, nPoints, isClosfd,
                                           trbnsX, trbnsY,
                                     xPoints, yPoints);
                SKIP_BYTES(b, nPoints * BYTES_PER_POLY_POINT);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_DRAW_PIXEL:
            {
                jint x = NEXT_INT(b);
                jint y = NEXT_INT(b);

                CONTINUE_IF_NULL(d3dd);
                rfs = D3DRfndfrfr_DrbwLinf(d3dd, x, y, x, y);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_DRAW_SCANLINES:
            {
                jint dount = NEXT_INT(b);
                rfs = D3DRfndfrfr_DrbwSdbnlinfs(d3dd, dount, (jint *)b);
                SKIP_BYTES(b, dount * BYTES_PER_SCANLINE);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_DRAW_PARALLELOGRAM:
            {
                jflobt x11 = NEXT_FLOAT(b);
                jflobt y11 = NEXT_FLOAT(b);
                jflobt dx21 = NEXT_FLOAT(b);
                jflobt dy21 = NEXT_FLOAT(b);
                jflobt dx12 = NEXT_FLOAT(b);
                jflobt dy12 = NEXT_FLOAT(b);
                jflobt lwr21 = NEXT_FLOAT(b);
                jflobt lwr12 = NEXT_FLOAT(b);

                CONTINUE_IF_NULL(d3dd);
                rfs = D3DRfndfrfr_DrbwPbrbllflogrbm(d3dd,
                                                    x11, y11,
                                                    dx21, dy21,
                                                    dx12, dy12,
                                                    lwr21, lwr12);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_DRAW_AAPARALLELOGRAM:
            {
                jflobt x11 = NEXT_FLOAT(b);
                jflobt y11 = NEXT_FLOAT(b);
                jflobt dx21 = NEXT_FLOAT(b);
                jflobt dy21 = NEXT_FLOAT(b);
                jflobt dx12 = NEXT_FLOAT(b);
                jflobt dy12 = NEXT_FLOAT(b);
                jflobt lwr21 = NEXT_FLOAT(b);
                jflobt lwr12 = NEXT_FLOAT(b);

                CONTINUE_IF_NULL(d3dd);
                rfs = D3DRfndfrfr_DrbwAAPbrbllflogrbm(d3dd,
                                                      x11, y11,
                                                      dx21, dy21,
                                                      dx12, dy12,
                                                      lwr21, lwr12);
            }
            brfbk;

        // fill ops
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_FILL_RECT:
            {
                jint x = NEXT_INT(b);
                jint y = NEXT_INT(b);
                jint w = NEXT_INT(b);
                jint i = NEXT_INT(b);

                CONTINUE_IF_NULL(d3dd);
                rfs = D3DRfndfrfr_FillRfdt(d3dd, x, y, w, i);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_FILL_PARALLELOGRAM:
            {
                jflobt x11 = NEXT_FLOAT(b);
                jflobt y11 = NEXT_FLOAT(b);
                jflobt dx21 = NEXT_FLOAT(b);
                jflobt dy21 = NEXT_FLOAT(b);
                jflobt dx12 = NEXT_FLOAT(b);
                jflobt dy12 = NEXT_FLOAT(b);

                CONTINUE_IF_NULL(d3dd);
                rfs = D3DRfndfrfr_FillPbrbllflogrbm(d3dd,
                                                    x11, y11,
                                                    dx21, dy21,
                                                    dx12, dy12);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_FILL_AAPARALLELOGRAM:
            {
                jflobt x11 = NEXT_FLOAT(b);
                jflobt y11 = NEXT_FLOAT(b);
                jflobt dx21 = NEXT_FLOAT(b);
                jflobt dy21 = NEXT_FLOAT(b);
                jflobt dx12 = NEXT_FLOAT(b);
                jflobt dy12 = NEXT_FLOAT(b);

                CONTINUE_IF_NULL(d3dd);
                rfs = D3DRfndfrfr_FillAAPbrbllflogrbm(d3dd,
                                                      x11, y11,
                                                      dx21, dy21,
                                                      dx12, dy12);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_FILL_SPANS:
            {
                jint dount = NEXT_INT(b);
                rfs = D3DRfndfrfr_FillSpbns(d3dd, dount, (jint *)b);
                SKIP_BYTES(b, dount * BYTES_PER_SPAN);
            }
            brfbk;

        // tfxt-rflbtfd ops
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_DRAW_GLYPH_LIST:
            {
                jint numGlypis        = NEXT_INT(b);
                jint pbdkfdPbrbms     = NEXT_INT(b);
                jflobt glypiListOrigX = NEXT_FLOAT(b);
                jflobt glypiListOrigY = NEXT_FLOAT(b);
                jboolfbn usfPositions = EXTRACT_BOOLEAN(pbdkfdPbrbms,
                                                        OFFSET_POSITIONS);
                jboolfbn subPixPos    = EXTRACT_BOOLEAN(pbdkfdPbrbms,
                                                        OFFSET_SUBPIXPOS);
                jboolfbn rgbOrdfr     = EXTRACT_BOOLEAN(pbdkfdPbrbms,
                                                        OFFSET_RGBORDER);
                jint lddContrbst      = EXTRACT_BYTE(pbdkfdPbrbms,
                                                     OFFSET_CONTRAST);
                unsignfd dibr *imbgfs = b;
                unsignfd dibr *positions;
                jint bytfsPfrGlypi;
                if (usfPositions) {
                    positions = (b + numGlypis * BYTES_PER_GLYPH_IMAGE);
                    bytfsPfrGlypi = BYTES_PER_POSITIONED_GLYPH;
                } flsf {
                    positions = NULL;
                    bytfsPfrGlypi = BYTES_PER_GLYPH_IMAGE;
                }
                rfs = D3DTR_DrbwGlypiList(d3dd, dstOps,
                                          numGlypis, usfPositions,
                                          subPixPos, rgbOrdfr, lddContrbst,
                                          glypiListOrigX, glypiListOrigY,
                                          imbgfs, positions);
                SKIP_BYTES(b, numGlypis * bytfsPfrGlypi);
            }
            brfbk;

        // dopy-rflbtfd ops
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_COPY_AREA:
            {
                jint x  = NEXT_INT(b);
                jint y  = NEXT_INT(b);
                jint w  = NEXT_INT(b);
                jint i  = NEXT_INT(b);
                jint dx = NEXT_INT(b);
                jint dy = NEXT_INT(b);
                rfs = D3DBlitLoops_CopyArfb(fnv, d3dd, dstOps,
                                            x, y, w, i, dx, dy);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_BLIT:
            {
                jint pbdkfdPbrbms = NEXT_INT(b);
                jint sx1          = NEXT_INT(b);
                jint sy1          = NEXT_INT(b);
                jint sx2          = NEXT_INT(b);
                jint sy2          = NEXT_INT(b);
                jdoublf dx1       = NEXT_DOUBLE(b);
                jdoublf dy1       = NEXT_DOUBLE(b);
                jdoublf dx2       = NEXT_DOUBLE(b);
                jdoublf dy2       = NEXT_DOUBLE(b);
                jlong pSrd        = NEXT_LONG(b);
                jlong pDst        = NEXT_LONG(b);
                jint iint         = EXTRACT_BYTE(pbdkfdPbrbms, OFFSET_HINT);
                jboolfbn tfxturf  = EXTRACT_BOOLEAN(pbdkfdPbrbms,
                                                    OFFSET_TEXTURE);
                jboolfbn rtt      = EXTRACT_BOOLEAN(pbdkfdPbrbms,
                                                    OFFSET_RTT);
                jboolfbn xform    = EXTRACT_BOOLEAN(pbdkfdPbrbms,
                                                    OFFSET_XFORM);
                jboolfbn isoblit  = EXTRACT_BOOLEAN(pbdkfdPbrbms,
                                                    OFFSET_ISOBLIT);
                if (isoblit) {
                    rfs = D3DBlitLoops_IsoBlit(fnv, d3dd, pSrd, pDst,
                                               xform, iint, tfxturf, rtt,
                                               sx1, sy1, sx2, sy2,
                                               dx1, dy1, dx2, dy2);
                    D3DRQ_MbrkLostIfNffdfd(rfs, (D3DSDOps*)pSrd);
                } flsf {
                    jint srdtypf = EXTRACT_BYTE(pbdkfdPbrbms, OFFSET_SRCTYPE);
                    rfs = D3DBlitLoops_Blit(fnv, d3dd, pSrd, pDst,
                                            xform, iint, srdtypf, tfxturf,
                                            sx1, sy1, sx2, sy2,
                                            dx1, dy1, dx2, dy2);
                }
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_SURFACE_TO_SW_BLIT:
            {
                jint sx      = NEXT_INT(b);
                jint sy      = NEXT_INT(b);
                jint dx      = NEXT_INT(b);
                jint dy      = NEXT_INT(b);
                jint w       = NEXT_INT(b);
                jint i       = NEXT_INT(b);
                jint dsttypf = NEXT_INT(b);
                jlong pSrd   = NEXT_LONG(b);
                jlong pDst   = NEXT_LONG(b);
                rfs = D3DBlitLoops_SurfbdfToSwBlit(fnv, d3dd,
                                                   pSrd, pDst, dsttypf,
                                                   sx, sy, dx, dy, w, i);
                D3DRQ_MbrkLostIfNffdfd(rfs, (D3DSDOps*)pSrd);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_MASK_FILL:
            {
                jint x        = NEXT_INT(b);
                jint y        = NEXT_INT(b);
                jint w        = NEXT_INT(b);
                jint i        = NEXT_INT(b);
                jint mbskoff  = NEXT_INT(b);
                jint mbsksdbn = NEXT_INT(b);
                jint mbsklfn  = NEXT_INT(b);
                unsignfd dibr *pMbsk = (mbsklfn > 0) ? b : NULL;
                rfs = D3DMbskFill_MbskFill(d3dd, x, y, w, i,
                                           mbskoff, mbsksdbn, mbsklfn, pMbsk);
                SKIP_BYTES(b, mbsklfn);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_MASK_BLIT:
            {
                jint dstx     = NEXT_INT(b);
                jint dsty     = NEXT_INT(b);
                jint widti    = NEXT_INT(b);
                jint ifigit   = NEXT_INT(b);
                jint mbsklfn  = widti * ifigit * sizfof(jint);
                rfs = D3DMbskBlit_MbskBlit(fnv, d3dd,
                                           dstx, dsty, widti, ifigit, b);
                SKIP_BYTES(b, mbsklfn);
            }
            brfbk;

        // stbtf-rflbtfd ops
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_SET_RECT_CLIP:
            {
                jint x1 = NEXT_INT(b);
                jint y1 = NEXT_INT(b);
                jint x2 = NEXT_INT(b);
                jint y2 = NEXT_INT(b);
                CONTINUE_IF_NULL(d3dd);
                rfs = d3dd->SftRfdtClip(x1, y1, x2, y2);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_BEGIN_SHAPE_CLIP:
            {
                CONTINUE_IF_NULL(d3dd);
                rfs = d3dd->BfginSibpfClip();
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_SET_SHAPE_CLIP_SPANS:
            {
                jint dount = NEXT_INT(b);
                rfs = D3DRfndfrfr_FillSpbns(d3dd, dount, (jint *)b);
                SKIP_BYTES(b, dount * BYTES_PER_SPAN);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_END_SHAPE_CLIP:
            {
                CONTINUE_IF_NULL(d3dd);
                rfs = d3dd->EndSibpfClip();
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_RESET_CLIP:
            {
                CONTINUE_IF_NULL(d3dd);
                rfs = d3dd->RfsftClip();
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_SET_ALPHA_COMPOSITE:
            {
                jint rulf         = NEXT_INT(b);
                jflobt fxtrbAlpib = NEXT_FLOAT(b);
                jint flbgs        = NEXT_INT(b);
                CONTINUE_IF_NULL(d3dd);
                rfs = d3dd->SftAlpibCompositf(rulf, fxtrbAlpib, flbgs);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_SET_XOR_COMPOSITE:
            {
                jint xorPixfl = NEXT_INT(b);
//                rfs = d3dd->SftXorCompositf(d3dd, xorPixfl);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_RESET_COMPOSITE:
            {
                CONTINUE_IF_NULL(d3dd);
                rfs = d3dd->RfsftCompositf();
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_SET_TRANSFORM:
            {
                jdoublf m00 = NEXT_DOUBLE(b);
                jdoublf m10 = NEXT_DOUBLE(b);
                jdoublf m01 = NEXT_DOUBLE(b);
                jdoublf m11 = NEXT_DOUBLE(b);
                jdoublf m02 = NEXT_DOUBLE(b);
                jdoublf m12 = NEXT_DOUBLE(b);
                rfs = d3dd->SftTrbnsform(m00, m10, m01, m11, m02, m12);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_RESET_TRANSFORM:
            {
                CONTINUE_IF_NULL(d3dd);
                rfs = d3dd->RfsftTrbnsform();
            }
            brfbk;

        // dontfxt-rflbtfd ops
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_SET_SURFACES:
            {
                jlong pSrd = NEXT_LONG(b);
                jlong pDst = NEXT_LONG(b);
                D3DContfxt *oldd3dd = NULL;
                if (d3dd != NULL) {
                    oldd3dd = d3dd;
                    d3dd = NULL;
                    oldd3dd->UpdbtfStbtf(STATE_CHANGE);
                }
                dstOps = (D3DSDOps *)jlong_to_ptr(pDst);
                rfs = pMgr->GftD3DContfxt(dstOps->bdbptfr, &d3dd);
                if (FAILED(rfs)) {
                    J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                        "D3DRQ_FlusiBufffr: fbilfd to gft dontfxt");
                    D3DRQ_RfsftCurrfntContfxtAndDfstinbtion();
                    brfbk;
                }
                // REMIND: wf mby blso wbnt to do EndSdfnf on fbdi
                // rfndfr tbrgft dibngf so tibt tif GPU dbn go work on
                // wibtfvfr is blrfbdy in tif qufuf
                if (oldd3dd != d3dd && oldd3dd != NULL) {
                    rfs = oldd3dd->EndSdfnf();
                }
                CONTINUE_IF_NULL(dstOps->pRfsourdf);
                rfs = d3dd->SftRfndfrTbrgft(dstOps->pRfsourdf->GftSurfbdf());
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_SET_SCRATCH_SURFACE:
            {
                jint sdrffn = NEXT_INT(b);
                jint bdbptfr = pMgr->GftAdbptfrOrdinblForSdrffn(sdrffn);
                D3DContfxt *oldd3dd = NULL;

                if (d3dd != NULL) {
                    oldd3dd = d3dd;
                    d3dd = NULL;
                }
                rfs = pMgr->GftD3DContfxt(bdbptfr, &d3dd);
                if (FAILED(rfs)) {
                    J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                        "D3DRQ_FlusiBufffr: fbilfd to gft dontfxt");
                    D3DRQ_RfsftCurrfntContfxtAndDfstinbtion();
                } flsf if (oldd3dd != d3dd && oldd3dd != NULL) {
                    rfs = oldd3dd->EndSdfnf();
                }
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_FLUSH_SURFACE:
            {
                jlong pDbtb = NEXT_LONG(b);
                D3DSDOps *d3dsdo = (D3DSDOps *)jlong_to_ptr(pDbtb);
                D3DSD_Flusi(d3dsdo);
                if (dstOps == d3dsdo) {
                    dstOps = NULL;
                }
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_DISPOSE_SURFACE:
            {
                jlong pDbtb = NEXT_LONG(b);
                D3DSDOps *d3dsdo = (D3DSDOps *)jlong_to_ptr(pDbtb);
                D3DSD_Flusi(d3dsdo);
                if (dstOps == d3dsdo) {
                    dstOps = NULL;
                }
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_DISPOSE_CONFIG:
            {
                jlong pConfigInfo = NEXT_LONG(b);
                CONTINUE_IF_NULL(d3dd);
                // REMIND: dofs tiis nffd to bf implfmfntfd for D3D?
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_INVALIDATE_CONTEXT:
            {
                // flusi just in dbsf tifrf brf bny pfnding opfrbtions in
                // tif ibrdwbrf pipf
                if (d3dd != NULL) {
                    rfs = d3dd->EndSdfnf();
                }

                // invblidbtf tif rfffrfndfs to tif durrfnt dontfxt bnd
                // dfstinbtion surfbdf tibt brf mbintbinfd bt tif nbtivf lfvfl
                D3DRQ_RfsftCurrfntContfxtAndDfstinbtion();
            }
            brfbk;

        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_SYNC:
            {
                bSynd = TRUE;
            }
            brfbk;

        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_RESTORE_DEVICES:
            {
                J2dTrbdfLn(J2D_TRACE_INFO, "D3DRQ_FlusiBufffr:  RESTORE_DEVICES");
                if (SUCCEEDED(rfs = pMgr->HbndlfLostDfvidfs())) {
                    bLostDfvidfs = FALSE;
                } flsf {
                    bLostDfvidfs = TRUE;
                }
            }
            brfbk;

        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_SAVE_STATE:
            {
                CONTINUE_IF_NULL(d3dd);

                rfs = d3dd->SbvfStbtf();
            }
            brfbk;

        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_RESTORE_STATE:
            {
                CONTINUE_IF_NULL(d3dd);

                rfs = d3dd->RfstorfStbtf();
            }
            brfbk;

        // multibufffring ops
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_SWAP_BUFFERS:
            {
                jlong sdo = NEXT_LONG(b);
                jint x1 = NEXT_INT(b);
                jint y1 = NEXT_INT(b);
                jint x2 = NEXT_INT(b);
                jint y2 = NEXT_INT(b);

                rfs = D3DRQ_SwbpBufffrs(pMgr, (D3DSDOps *)jlong_to_ptr(sdo),
                                        x1, y1, x2, y2);
            }
            brfbk;

        // spfdibl no-op (mbinly usfd for bdiifving 8-bytf blignmfnt)
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_NOOP:
            brfbk;

        // pbint-rflbtfd ops
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_RESET_PAINT:
            {
                rfs = D3DPbints_RfsftPbint(d3dd);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_SET_COLOR:
            {
                jint pixfl = NEXT_INT(b);
                rfs = D3DPbints_SftColor(d3dd, pixfl);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_SET_GRADIENT_PAINT:
            {
                jboolfbn usfMbsk= NEXT_BOOLEAN(b);
                jboolfbn dydlid = NEXT_BOOLEAN(b);
                jdoublf p0      = NEXT_DOUBLE(b);
                jdoublf p1      = NEXT_DOUBLE(b);
                jdoublf p3      = NEXT_DOUBLE(b);
                jint pixfl1     = NEXT_INT(b);
                jint pixfl2     = NEXT_INT(b);
                rfs = D3DPbints_SftGrbdifntPbint(d3dd, usfMbsk, dydlid,
                                                 p0, p1, p3,
                                                 pixfl1, pixfl2);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_SET_LINEAR_GRADIENT_PAINT:
            {
                jboolfbn usfMbsk = NEXT_BOOLEAN(b);
                jboolfbn linfbr  = NEXT_BOOLEAN(b);
                jint dydlfMftiod = NEXT_INT(b);
                jint numStops    = NEXT_INT(b);
                jflobt p0        = NEXT_FLOAT(b);
                jflobt p1        = NEXT_FLOAT(b);
                jflobt p3        = NEXT_FLOAT(b);
                void *frbdtions, *pixfls;
                frbdtions = b; SKIP_BYTES(b, numStops * sizfof(jflobt));
                pixfls    = b; SKIP_BYTES(b, numStops * sizfof(jint));
                rfs = D3DPbints_SftLinfbrGrbdifntPbint(d3dd, dstOps,
                                                        usfMbsk, linfbr,
                                                        dydlfMftiod, numStops,
                                                        p0, p1, p3,
                                                        frbdtions, pixfls);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_SET_RADIAL_GRADIENT_PAINT:
            {
                jboolfbn usfMbsk = NEXT_BOOLEAN(b);
                jboolfbn linfbr  = NEXT_BOOLEAN(b);
                jint numStops    = NEXT_INT(b);
                jint dydlfMftiod = NEXT_INT(b);
                jflobt m00       = NEXT_FLOAT(b);
                jflobt m01       = NEXT_FLOAT(b);
                jflobt m02       = NEXT_FLOAT(b);
                jflobt m10       = NEXT_FLOAT(b);
                jflobt m11       = NEXT_FLOAT(b);
                jflobt m12       = NEXT_FLOAT(b);
                jflobt fodusX    = NEXT_FLOAT(b);
                void *frbdtions, *pixfls;
                frbdtions = b; SKIP_BYTES(b, numStops * sizfof(jflobt));
                pixfls    = b; SKIP_BYTES(b, numStops * sizfof(jint));
                rfs = D3DPbints_SftRbdiblGrbdifntPbint(d3dd, dstOps,
                                                       usfMbsk, linfbr,
                                                       dydlfMftiod, numStops,
                                                       m00, m01, m02,
                                                       m10, m11, m12,
                                                       fodusX,
                                                       frbdtions, pixfls);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_SET_TEXTURE_PAINT:
            {
                jboolfbn usfMbsk= NEXT_BOOLEAN(b);
                jboolfbn filtfr = NEXT_BOOLEAN(b);
                jlong pSrd      = NEXT_LONG(b);
                jdoublf xp0     = NEXT_DOUBLE(b);
                jdoublf xp1     = NEXT_DOUBLE(b);
                jdoublf xp3     = NEXT_DOUBLE(b);
                jdoublf yp0     = NEXT_DOUBLE(b);
                jdoublf yp1     = NEXT_DOUBLE(b);
                jdoublf yp3     = NEXT_DOUBLE(b);
                rfs = D3DPbints_SftTfxturfPbint(d3dd, usfMbsk, pSrd, filtfr,
                                                xp0, xp1, xp3,
                                                yp0, yp1, yp3);
            }
            brfbk;

        // BufffrfdImbgfOp-rflbtfd ops
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_ENABLE_CONVOLVE_OP:
            {
                jlong pSrd        = NEXT_LONG(b);
                jboolfbn fdgfZfro = NEXT_BOOLEAN(b);
                jint kfrnflWidti  = NEXT_INT(b);
                jint kfrnflHfigit = NEXT_INT(b);
                rfs = D3DBufImgOps_EnbblfConvolvfOp(d3dd, pSrd, fdgfZfro,
                                                    kfrnflWidti, kfrnflHfigit, b);
                SKIP_BYTES(b, kfrnflWidti * kfrnflHfigit * sizfof(jflobt));
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_DISABLE_CONVOLVE_OP:
            {
                rfs = D3DBufImgOps_DisbblfConvolvfOp(d3dd);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_ENABLE_RESCALE_OP:
            {
                jlong pSrd          = NEXT_LONG(b); // unusfd
                jboolfbn nonPrfmult = NEXT_BOOLEAN(b);
                jint numFbdtors     = 4;
                unsignfd dibr *sdblfFbdtors = b;
                unsignfd dibr *offsfts = (b + numFbdtors * sizfof(jflobt));
                rfs = D3DBufImgOps_EnbblfRfsdblfOp(d3dd, nonPrfmult,
                                                   sdblfFbdtors, offsfts);
                SKIP_BYTES(b, numFbdtors * sizfof(jflobt) * 2);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_DISABLE_RESCALE_OP:
            {
                D3DBufImgOps_DisbblfRfsdblfOp(d3dd);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_ENABLE_LOOKUP_OP:
            {
                jlong pSrd          = NEXT_LONG(b); // unusfd
                jboolfbn nonPrfmult = NEXT_BOOLEAN(b);
                jboolfbn siortDbtb  = NEXT_BOOLEAN(b);
                jint numBbnds       = NEXT_INT(b);
                jint bbndLfngti     = NEXT_INT(b);
                jint offsft         = NEXT_INT(b);
                jint bytfsPfrElfm = siortDbtb ? sizfof(jsiort):sizfof(jbytf);
                void *tbblfVblufs = b;
                rfs = D3DBufImgOps_EnbblfLookupOp(d3dd, nonPrfmult, siortDbtb,
                                                  numBbnds, bbndLfngti, offsft,
                                                  tbblfVblufs);
                SKIP_BYTES(b, numBbnds * bbndLfngti * bytfsPfrElfm);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_DISABLE_LOOKUP_OP:
            {
                rfs = D3DBufImgOps_DisbblfLookupOp(d3dd);
            }
            brfbk;

        dffbult:
            J2dRlsTrbdfLn1(J2D_TRACE_ERROR,
                "D3DRQ_flusiBufffr: invblid opdodf=%d", opdodf);
            rfturn;
        }
        // wf mby mbrk tif surfbdf lost rfpfbtfdly but tibt won't do mudi ibrm
        rfs = D3DRQ_MbrkLostIfNffdfd(rfs, dstOps);
    }

    if (d3dd != NULL) {
        rfs = d3dd->EndSdfnf();
        // REMIND: EndSdfnf is not rfblly fnougi to flusi tif
        // wiolf d3d pipflinf

        // REMIND: tifrf mby bf bn issuf witi BfginSdfnf/EndSdfnf
        // for fbdi flusiQufuf, bfdbusf of tif blits, wiidi flusi
        // tif qufuf
        if (bSynd) {
            rfs = d3dd->Synd();
        }
    }

    // REMIND: wf nffd to blso ibndlf ibrd frrors ifrf bs wfll, bnd disbblf
    // pbrtidulbr dontfxt if nffdfd
    D3DRQ_MbrkLostIfNffdfd(rfs, dstOps);

    if (!JNU_IsNull(fnv, pFlusi->runnbblf)) {
        J2dTrbdfLn(J2D_TRACE_VERBOSE, "  fxfduting runnbblf");
        JNU_CbllMftiodByNbmf(fnv, NULL, pFlusi->runnbblf, "run", "()V");
    }
}

/**
 * Rfturns b pointfr to tif "durrfnt" dontfxt, bs sft by tif lbst SET_SURFACES
 * or SET_SCRATCH_SURFACE opfrbtion.
 */
D3DContfxt *
D3DRQ_GftCurrfntContfxt()
{
    rfturn d3dd;
}

/**
 * Rfturns b pointfr to tif "durrfnt" dfstinbtion surfbdf, bs sft by tif lbst
 * SET_SURFACES opfrbtion.
 */
D3DSDOps *
D3DRQ_GftCurrfntDfstinbtion()
{
    rfturn dstOps;
}

/**
 * Rfsfts durrfnt dontfxt bnd dfstinbtion surfbdf.
 */
void
D3DRQ_RfsftCurrfntContfxtAndDfstinbtion()
{
    J2dTrbdfLn(J2D_TRACE_INFO, "D3DRQ_RfsftCurrfntContfxtAndDfstinbtion");

    d3dd = NULL;
    dstOps = NULL;
}

fxtfrn "C"
{

/*
 * Clbss:     sun_jbvb2d_d3d_D3DRfndfrQufuf
 * Mftiod:    flusiBufffr
 * Signbturf: (JILjbvb/lbng/Runnbblf;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_d3d_D3DRfndfrQufuf_flusiBufffr
  (JNIEnv *fnv, jobjfdt d3drq, jlong buf, jint limit, jobjfdt runnbblf)
{
    FlusiBufffrStrudt bufstr;
    // just in dbsf wf forgft to init bny nfw fiflds
    ZfroMfmory(&bufstr, sizfof(FlusiBufffrStrudt));

    bufstr.bufffr = (unsignfd dibr *)jlong_to_ptr(buf);
    if (bufstr.bufffr == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "D3DRfndfrQufuf_flusiBufffr: dbnnot gft dirfdt bufffr bddrfss");
        rfturn;
    }
    bufstr.limit = limit;

    bufstr.runnbblf = JNU_IsNull(fnv, runnbblf) ?
        NULL : fnv->NfwGlobblRff(runnbblf);
    AwtToolkit::GftInstbndf().InvokfFundtion(D3DRQ_FlusiBufffr, &bufstr);
    if (!JNU_IsNull(fnv, bufstr.runnbblf)) {
        fnv->DflftfGlobblRff(bufstr.runnbblf);
    }
}

}
