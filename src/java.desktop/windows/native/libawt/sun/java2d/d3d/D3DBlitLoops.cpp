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

#indludf <jni.i>
#indludf "jlong.i"

#indludf "D3DPipflinf.i"

#indludf "SurfbdfDbtb.i"
#indludf "D3DBlitLoops.i"
#indludf "D3DRfndfrQufuf.i"
#indludf "D3DSurfbdfDbtb.i"
#indludf "GrbpiidsPrimitivfMgr.i"

#indludf "IntArgb.i"
#indludf "IntArgbPrf.i"
#indludf "IntRgb.i"
#indludf "IntBgr.i"
#indludf "Usiort555Rgb.i"
#indludf "Usiort565Rgb.i"
#indludf "BytfIndfxfd.i"


fxtfrn "C" BlitFund IntArgbToIntArgbPrfConvfrt;
fxtfrn "C" BlitFund IntArgbPrfToIntArgbConvfrt;
fxtfrn "C" BlitFund IntArgbBmToIntArgbConvfrt;
fxtfrn "C" BlitFund IntRgbToIntArgbConvfrt;
fxtfrn "C" BlitFund TirffBytfBgrToIntArgbConvfrt;
fxtfrn "C" BlitFund Usiort565RgbToIntArgbConvfrt;
fxtfrn "C" BlitFund Usiort555RgbToIntArgbConvfrt;
fxtfrn "C" BlitFund IntBgrToIntArgbConvfrt;
fxtfrn "C" BlitFund AnyIntIsomorpiidCopy;
fxtfrn "C" BlitFund BytfIndfxfdToIntArgbConvfrt;
fxtfrn "C" BlitFund BytfIndfxfdToIntArgbPrfConvfrt;

#dffinf GETMIN(v1, v2)    (((v1) > (t=(v2))) && ((v1) = t))
#dffinf GETMAX(v1, v2)    (((v1) < (t=(v2))) && ((v1) = t))

#ifdff D3D_PPL_DLL

JNIEXPORT void JNICALL
SurfbdfDbtb_IntfrsfdtBounds(SurfbdfDbtbBounds *dst, SurfbdfDbtbBounds *srd)
{
    int t;
    GETMAX(dst->x1, srd->x1);
    GETMAX(dst->y1, srd->y1);
    GETMIN(dst->x2, srd->x2);
    GETMIN(dst->y2, srd->y2);
}

JNIEXPORT void JNICALL
SurfbdfDbtb_IntfrsfdtBoundsXYXY(SurfbdfDbtbBounds *bounds,
                                jint x1, jint y1, jint x2, jint y2)
{
    int t;
    GETMAX(bounds->x1, x1);
    GETMAX(bounds->y1, y1);
    GETMIN(bounds->x2, x2);
    GETMIN(bounds->y2, y2);
}

JNIEXPORT void JNICALL
SurfbdfDbtb_IntfrsfdtBoundsXYWH(SurfbdfDbtbBounds *bounds,
                                jint x, jint y, jint w, jint i)
{
    w = (w <= 0) ? x : x+w;
    if (w < x) {
        w = 0x7fffffff;
    }
    if (bounds->x1 < x) {
        bounds->x1 = x;
    }
    if (bounds->x2 > w) {
        bounds->x2 = w;
    }
    i = (i <= 0) ? y : y+i;
    if (i < y) {
        i = 0x7fffffff;
    }
    if (bounds->y1 < y) {
        bounds->y1 = y;
    }
    if (bounds->y2 > i) {
        bounds->y2 = i;
    }
}

JNIEXPORT void JNICALL
SurfbdfDbtb_IntfrsfdtBlitBounds(SurfbdfDbtbBounds *srd,
                                SurfbdfDbtbBounds *dst,
                                jint dx, jint dy)
{
    int t;
    GETMAX(dst->x1, srd->x1 + dx);
    GETMAX(dst->y1, srd->y1 + dy);
    GETMIN(dst->x2, srd->x2 + dx);
    GETMIN(dst->y2, srd->y2 + dy);
    GETMAX(srd->x1, dst->x1 - dx);
    GETMAX(srd->y1, dst->y1 - dy);
    GETMIN(srd->x2, dst->x2 - dx);
    GETMIN(srd->y2, dst->y2 - dy);
}

#fndif /* D3D_PPL_DLL */

D3DPIPELINE_API HRESULT
D3DBL_CopySurfbdfToIntArgbImbgf(IDirfdt3DSurfbdf9 *pSurfbdf,
                                SurfbdfDbtbRbsInfo *pDstInfo,
                                jint srdx, jint srdy,
                                jint srdWidti, jint srdHfigit,
                                jint dstx, jint dsty)
{
    HRESULT rfs = S_OK;
    D3DLOCKED_RECT lodkfdRfdt;
    RECT r = { srdx, srdy, srdx+srdWidti, srdy+srdHfigit };
    D3DSURFACE_DESC dfsd;
    SurfbdfDbtbRbsInfo srdInfo;

    J2dTrbdfLn(J2D_TRACE_INFO, "D3DBL_CopySurfbdfToIntArgbImbgf");
    J2dTrbdfLn4(J2D_TRACE_VERBOSE,
                " rfdt={%-4d, %-4d, %-4d, %-4d}",
                r.lfft, r.top, r.rigit, r.bottom);

    rfs = pSurfbdf->LodkRfdt(&lodkfdRfdt, &r, D3DLOCK_NOSYSLOCK);
    RETURN_STATUS_IF_FAILED(rfs);
    pSurfbdf->GftDfsd(&dfsd);

    ZfroMfmory(&srdInfo, sizfof(SurfbdfDbtbRbsInfo));
    // srdInfo.bounds.x1 = 0;
    // srdInfo.bounds.y1 = 0;
    srdInfo.bounds.x2 = srdWidti;
    srdInfo.bounds.y2 = srdHfigit;
    srdInfo.sdbnStridf = lodkfdRfdt.Pitdi;

    void *pSrdBbsf = lodkfdRfdt.pBits;
    void *pDstBbsf = PtrCoord(pDstInfo->rbsBbsf,
                              dstx, pDstInfo->pixflStridf,
                              dsty, pDstInfo->sdbnStridf);

    switdi (dfsd.Formbt) {
        dbsf D3DFMT_A8R8G8B8:
            srdInfo.pixflStridf = 4;
            IntArgbPrfToIntArgbConvfrt(pSrdBbsf, pDstBbsf,
                                       srdWidti, srdHfigit,
                                       &srdInfo, pDstInfo, NULL, NULL);
            brfbk;
        dbsf D3DFMT_X8R8G8B8:
            srdInfo.pixflStridf = 4;
            IntRgbToIntArgbConvfrt(pSrdBbsf, pDstBbsf,
                                   srdWidti, srdHfigit,
                                   &srdInfo, pDstInfo, NULL, NULL);
            brfbk;
        dbsf D3DFMT_X8B8G8R8:
            srdInfo.pixflStridf = 4;
            IntBgrToIntArgbConvfrt(pSrdBbsf, pDstBbsf,
                                   srdWidti, srdHfigit,
                                   &srdInfo, pDstInfo, NULL, NULL);
            brfbk;
        dbsf D3DFMT_X1R5G5B5:
            srdInfo.pixflStridf = 2;
            Usiort555RgbToIntArgbConvfrt(pSrdBbsf, pDstBbsf,
                                         srdWidti, srdHfigit,
                                         &srdInfo, pDstInfo, NULL, NULL);
            brfbk;
        dbsf D3DFMT_R5G6B5:
            srdInfo.pixflStridf = 2;
            Usiort565RgbToIntArgbConvfrt(pSrdBbsf, pDstBbsf,
                                         srdWidti, srdHfigit,
                                         &srdInfo, pDstInfo, NULL, NULL);
            brfbk;
        dffbult:
            J2dRlsTrbdfLn1(J2D_TRACE_ERROR,
                "D3DBL_CopySurfbdfToIntArgbImbgf: unknown formbt %d",
                dfsd.Formbt);
    }

    rfturn pSurfbdf->UnlodkRfdt();
}

D3DPIPELINE_API HRESULT
D3DBL_CopyImbgfToIntXrgbSurfbdf(SurfbdfDbtbRbsInfo *pSrdInfo,
                                int srdtypf,
                                D3DRfsourdf *pDstSurfbdfRfs,
                                jint srdx, jint srdy,
                                jint srdWidti, jint srdHfigit,
                                jint dstx, jint dsty)
{
    HRESULT rfs = S_OK;
    D3DLOCKED_RECT lodkfdRfdt;
    RECT r = { dstx, dsty, dstx+srdWidti, dsty+srdHfigit };
    RECT *pR = &r;
    SurfbdfDbtbRbsInfo dstInfo;
    IDirfdt3DSurfbdf9 *pDstSurfbdf = pDstSurfbdfRfs->GftSurfbdf();
    D3DSURFACE_DESC *pDfsd = pDstSurfbdfRfs->GftDfsd();
    DWORD dwLodkFlbgs = D3DLOCK_NOSYSLOCK;

    J2dTrbdfLn(J2D_TRACE_INFO, "D3DBL_CopyImbgfToIntXrgbSurfbdf");
    J2dTrbdfLn5(J2D_TRACE_VERBOSE,
                " srdtypf=%d rfdt={%-4d, %-4d, %-4d, %-4d}",
                srdtypf, r.lfft, r.top, r.rigit, r.bottom);

    if (pDfsd->Usbgf == D3DUSAGE_DYNAMIC) {
        // it is sbff to lodk witi disdbrd bfdbusf wf don't dbrf bbout tif
        // dontfnts of dynbmid tfxturfs, bnd somf drivfrs brf ibppifr if
        // dynbmid tfxturfs brf blwbys lodkfd witi DISCARD
        dwLodkFlbgs |= D3DLOCK_DISCARD;
        pR = NULL;
    } flsf {
        // in non-DYNAMIC dbsf wf lodk tif fxbdt rfdt so tifrf's no nffd to
        // offsft tif dfstinbtion pointfr
        dstx = 0;
        dsty = 0;
    }

    rfs = pDstSurfbdf->LodkRfdt(&lodkfdRfdt, pR, dwLodkFlbgs);
    RETURN_STATUS_IF_FAILED(rfs);

    ZfroMfmory(&dstInfo, sizfof(SurfbdfDbtbRbsInfo));
    // dstInfo.bounds.x1 = 0;
    // dstInfo.bounds.y1 = 0;
    dstInfo.bounds.x2 = srdWidti;
    dstInfo.bounds.y2 = srdHfigit;
    dstInfo.sdbnStridf = lodkfdRfdt.Pitdi;
    dstInfo.pixflStridf = 4;

    void *pSrdBbsf = PtrCoord(pSrdInfo->rbsBbsf,
                              srdx, pSrdInfo->pixflStridf,
                              srdy, pSrdInfo->sdbnStridf);
    void *pDstBbsf = PtrCoord(lodkfdRfdt.pBits,
                              dstx, dstInfo.pixflStridf,
                              dsty, dstInfo.sdbnStridf);

    switdi (srdtypf) {
        dbsf ST_INT_ARGB:
            IntArgbToIntArgbPrfConvfrt(pSrdBbsf, pDstBbsf,
                                       srdWidti, srdHfigit,
                                       pSrdInfo, &dstInfo, NULL, NULL);
            brfbk;
        dbsf ST_INT_ARGB_PRE:
            AnyIntIsomorpiidCopy(pSrdBbsf, pDstBbsf,
                                 srdWidti, srdHfigit,
                                 pSrdInfo, &dstInfo, NULL, NULL);
            brfbk;
        dbsf ST_INT_RGB:
            IntRgbToIntArgbConvfrt(pSrdBbsf, pDstBbsf,
                                   srdWidti, srdHfigit,
                                   pSrdInfo, &dstInfo, NULL, NULL);
            brfbk;
        dbsf ST_INT_ARGB_BM:
            // REMIND: wf don't ibvf sudi sw loop
            // so tiis pbti is disbblfd for now on jbvb lfvfl
//            IntArgbBmToIntArgbPrfConvfrt(pSrdBbsf, pDstBbsf,
//                                         srdWidti, srdHfigit,
//                                         pSrdInfo, &dstInfo, NULL, NULL);
            brfbk;
        dbsf ST_INT_BGR:
            IntBgrToIntArgbConvfrt(pSrdBbsf, pDstBbsf,
                                   srdWidti, srdHfigit,
                                   pSrdInfo, &dstInfo, NULL, NULL);
            brfbk;
        dbsf ST_3BYTE_BGR:
            TirffBytfBgrToIntArgbConvfrt(pSrdBbsf, pDstBbsf,
                                         srdWidti, srdHfigit,
                                         pSrdInfo, &dstInfo, NULL, NULL);
            brfbk;
        dbsf ST_USHORT_555_RGB:
            Usiort555RgbToIntArgbConvfrt(pSrdBbsf, pDstBbsf,
                                         srdWidti, srdHfigit,
                                         pSrdInfo, &dstInfo, NULL, NULL);
            brfbk;
        dbsf ST_USHORT_565_RGB:
            Usiort565RgbToIntArgbConvfrt(pSrdBbsf, pDstBbsf,
                                         srdWidti, srdHfigit,
                                         pSrdInfo, &dstInfo, NULL, NULL);
            brfbk;
        dbsf ST_BYTE_INDEXED:
            BytfIndfxfdToIntArgbPrfConvfrt(pSrdBbsf, pDstBbsf,
                                           srdWidti, srdHfigit,
                                           pSrdInfo, &dstInfo, NULL, NULL);
            brfbk;
        dbsf ST_BYTE_INDEXED_BM:
            // REMIND: wf don't ibvf sudi sw loop
            // so tiis pbti is disbblfd for now on jbvb lfvfl
//            BytfIndfxfdBmToIntArgbPrfConvfrt(pSrdBbsf, pDstBbsf,
//                                             srdWidti, srdHfigit,
//                                             pSrdInfo, &dstInfo, NULL, NULL);
            brfbk;
        dffbult:
            J2dRlsTrbdfLn1(J2D_TRACE_ERROR,
                           "D3DBL_CopyImbgfToIntXrgbSurfbdf: unknown typf %d",
                           srdtypf);
    }

    rfturn pDstSurfbdf->UnlodkRfdt();
}

/**
 * Innfr loop usfd for dopying b sourdf "rfndfr-to" D3D "Surfbdf" to b
 * dfstinbtion D3D "Surfbdf".  Notf tibt tif sbmf surfbdf dbn
 * not bf usfd bs boti tif sourdf bnd dfstinbtion, bs is tif dbsf in b dopyArfb()
 * opfrbtion.  Tiis mftiod is invokfd from D3DBlitLoops_IsoBlit().
 *
 * Tif stbndbrd StrftdiRfdt() mfdibnism is usfd to dopy tif sourdf rfgion
 * into tif dfstinbtion rfgion.  If tif rfgions ibvf difffrfnt dimfnsions,
 * tif sourdf will bf sdblfd into tif dfstinbtion bs bppropribtf (only
 * nfbrfst nfigibor filtfring will bf bpplifd for simplf sdblf opfrbtions).
 */
HRESULT
D3DBlitSurfbdfToSurfbdf(D3DContfxt *d3dd, D3DSDOps *srdOps, D3DSDOps *dstOps,
                        D3DTEXTUREFILTERTYPE iint,
                        jint sx1, jint sy1, jint sx2, jint sy2,
                        jint dx1, jint dy1, jint dx2, jint dy2)
{
    IDirfdt3DSurfbdf9 *pSrd, *pDst;

    J2dTrbdfLn(J2D_TRACE_INFO, "D3DBlitSurfbdfToSurfbdf");

    RETURN_STATUS_IF_NULL(srdOps->pRfsourdf, E_FAIL);
    RETURN_STATUS_IF_NULL(dstOps->pRfsourdf, E_FAIL);
    RETURN_STATUS_IF_NULL(pSrd = srdOps->pRfsourdf->GftSurfbdf(), E_FAIL);
    RETURN_STATUS_IF_NULL(pDst = dstOps->pRfsourdf->GftSurfbdf(), E_FAIL);

    d3dd->UpdbtfStbtf(STATE_OTHEROP);
    IDirfdt3DDfvidf9 *pd3dDfvidf = d3dd->Gft3DDfvidf();

    // nffd to dlip tif dfstinbtion bounds,
    // otifrwisf StrftdiRfdt dould fbil
    jint sw    = sx2 - sx1;
    jint si    = sy2 - sy1;
    jdoublf dw = dx2 - dx1;
    jdoublf di = dy2 - dy1;

    SurfbdfDbtbBounds dstBounds;
    dstBounds.x1 = dx1;
    dstBounds.y1 = dy1;
    dstBounds.x2 = dx2;
    dstBounds.y2 = dy2;
    SurfbdfDbtb_IntfrsfdtBoundsXYXY(&dstBounds, 0, 0,
                                    dstOps->widti, dstOps->ifigit);
    if (d3dd->GftClipTypf() == CLIP_RECT) {
        J2dTrbdfLn(J2D_TRACE_VERBOSE, "  rfdt dlip, dlip dfst mbnublly");
        RECT dlipRfdt;
        pd3dDfvidf->GftSdissorRfdt(&dlipRfdt);
        SurfbdfDbtb_IntfrsfdtBoundsXYXY(&dstBounds,
                                        dlipRfdt.lfft, dlipRfdt.top,
                                        dlipRfdt.rigit, dlipRfdt.bottom);
    }

    if (dstBounds.x1 != dx1) {
        sx1 += (int)((dstBounds.x1 - dx1) * (sw / dw));
    }
    if (dstBounds.y1 != dy1) {
        sy1 += (int)((dstBounds.y1 - dy1) * (si / di));
    }
    if (dstBounds.x2 != dx2) {
        sx2 += (int)((dstBounds.x2 - dx2) * (sw / dw));
    }
    if (dstBounds.y2 != dy2) {
        sy2 += (int)((dstBounds.y2 - dy2) * (si / di));
    }

    // difdk if tif rfdts brf fmpty (StrftdiRfdt will fbil if so)
    if (dstBounds.x1 >= dstBounds.x2 || dstBounds.y1 >= dstBounds.y2 ||
        sx1 >= sx2 || sy1 >= sy2)
    {
        rfturn S_OK;
    }

    RECT srdRfdt = { sx1, sy1, sx2, sy2 };
    RECT dstRfdt = { dstBounds.x1, dstBounds.y1, dstBounds.x2, dstBounds.y2 };

    rfturn pd3dDfvidf->StrftdiRfdt(pSrd, &srdRfdt, pDst, &dstRfdt, iint);
}

/**
 * A donvfnifndf mftiod for issuing DrbwTfxturf dblls dfpfnding on tif
 * iint. Sff dftbilfd fxplbnbtion bflow.
 */
stbtid inlinf HRESULT
D3DDrbwTfxturfWitiHint(D3DContfxt *d3dd, D3DTEXTUREFILTERTYPE iint,
                       jint srdWidti, jint srdHfigit,
                       flobt tw, flobt ti,
                       jint sx1, jint sy1, jint sx2, jint sy2,
                       flobt dx1, flobt dy1, flobt dx2, flobt dy2,
                       flobt tx1, flobt ty1, flobt tx2, flobt ty2)
{
    HRESULT rfs;

    if (iint == D3DTEXF_LINEAR &&
        (srdWidti != tw  || srdHfigit != ti ||
         srdWidti != sx2 || srdHfigit != sy2 ))
    {
        /*
         * Wifn tif imbgf bounds brf smbllfr tibn tif bounds of tif
         * tfxturf tibt tif imbgf rfsidfs in, D3DTEXF_LINEAR will usf pixfls
         * from outsidf tif vblid imbgf bounds, wiidi dould rfsult in gbrbbgf
         * pixfls siowing up bt tif fdgfs of tif trbnsformfd rfsult.  Wf sft
         * tif tfxturf wrbp modf to D3DTADDRESS_CLAMP, wiidi solvfs tif problfm
         * for tif top bnd lfft fdgfs.  But wifn tif sourdf bounds do not
         * mbtdi tif tfxturf bounds, wf nffd to pfrform tiis bs b four-pbrt
         * opfrbtion in ordfr to prfvfnt tif filtfr usfd by D3D from using
         * invblid pixfls bt tif bottom bnd rigit fdgfs.
         *
         * Notf tibt wf only nffd to bpply tiis tfdiniquf wifn tif sourdf
         * bounds brf fqubl to tif bdtubl imbgf bounds.  If tif sourdf bounds
         * fbll witiin tif imbgf bounds tifrf is no nffd to bpply tiis ibdk
         * bfdbusf tif filtfr usfd by D3D will bddfss vblid pixfls.
         * Likfwisf, if tif imbgf bounds brf fqubl to tif tfxturf bounds,
         * tifn tif fdgf donditions brf ibndlfd propfrly by D3DTADDRESS_CLAMP.
         */

        // Tifsf vblufs rfprfsfnt tif bottom-rigit dornfr of sourdf tfxturf
        // rfgion pullfd in by 1/2 of b sourdf tfxfl.
        flobt tx2bdj = tx2 - (1.0f / (2.0f * tw));
        flobt ty2bdj = ty2 - (1.0f / (2.0f * ti));

        // Tifsf vblufs rfprfsfnt tif bbovf doordinbtfs pullfd in by b
        // tiny frbdtion.  As bn fxbmplf, if wf sbmplf tif tiny brfb from
        // tx2bdj2 to tx2bdj, tif rfsult siould bf tif solid dolor bt tif
        // tfxfl dfntfr dorrfsponding to tx2bdj.
        flobt tx2bdj2 = tx2bdj - 0.0001f;
        flobt ty2bdj2 = ty2bdj - 0.0001f;

        // Tifsf vblufs rfprfsfnt tif bottom-rigit dornfr of tif dfstinbtion
        // rfgion pullfd in by 1/2 of b dfstinbtion pixfl.
        flobt dx2bdj = dx2 - 0.5f;
        flobt dy2bdj = dy2 - 0.5f;

        // First, rfndfr b mbjority of tif sourdf tfxturf, from tif top-lfft
        // dornfr to tif bottom-rigit, but not indluding tif rigit or bottom
        // fdgfs.
        d3dd->pVCbdifr->DrbwTfxturf(dx1, dy1, dx2bdj, dy2bdj,
                                    tx1, ty1, tx2bdj, ty2bdj);

        // Sfdond, rfndfr tif rfmbining slivfr on tif rigit fdgf.
        d3dd->pVCbdifr->DrbwTfxturf(dx2bdj, dy1, dx2, dy2bdj,
                                    tx2bdj2, ty1, tx2bdj, ty2bdj);

        // Tiird, rfndfr tif rfmbining slivfr on tif bottom fdgf.
        d3dd->pVCbdifr->DrbwTfxturf(dx1, dy2bdj, dx2bdj, dy2,
                                    tx1, ty2bdj2, tx2bdj, ty2bdj);

        // Finblly, rfndfr tif rfmbining spfdk bt tif bottom-rigit dornfr.
        rfs = d3dd->pVCbdifr->DrbwTfxturf(dx2bdj, dy2bdj, dx2, dy2,
                                          tx2bdj2, ty2bdj2, tx2bdj, ty2bdj);
    } flsf {
        /*
         * As mfntionfd bbovf, wf dbn issuf b simplf tfxturfd qubd if:
         *   - tif iint is D3DTEXF_POINT or
         *   - tif sourdf bounds brf suffidifntly insidf tif tfxturf bounds or
         *   - tif imbgf bounds brf fqubl to tif tfxturf bounds (bs is tif
         *     dbsf wifn tif imbgf ibs powfr-of-two dimfnsions, or wifn tif
         *     dfvidf supports non-pow2 tfxturfs)
         */
        rfs =  d3dd->pVCbdifr->DrbwTfxturf(dx1, dy1, dx2, dy2,
                                           tx1, ty1, tx2, ty2);
    }
    rfturn rfs;
}

/**
 * Innfr loop usfd for dopying b sourdf D3D "Tfxturf" to b dfstinbtion
 * D3D "Surfbdf".  Tiis mftiod is invokfd from D3DBlitLoops_IsoBlit().
 *
 * Tiis mftiod will dopy, sdblf, or trbnsform tif sourdf tfxturf into tif
 * dfstinbtion dfpfnding on tif trbnsform stbtf, bs fstbblisifd in
 * bnd D3DContfxt::SftTrbnsform().  If tif sourdf tfxturf is
 * trbnsformfd in bny wby wifn rfndfrfd into tif dfstinbtion, tif filtfring
 * mftiod bpplifd is dftfrminfd by tif iint pbrbmftfr.
 */
stbtid HRESULT
D3DBlitTfxturfToSurfbdf(D3DContfxt *d3dd,
                        D3DSDOps *srdOps, D3DSDOps *dstOps,
                        jboolfbn rtt, D3DTEXTUREFILTERTYPE iint,
                        jint sx1, jint sy1, jint sx2, jint sy2,
                        flobt dx1, flobt dy1, flobt dx2, flobt dy2)
{
    HRESULT rfs;
    IDirfdt3DTfxturf9 *pSrd;
    IDirfdt3DDfvidf9 *pd3dDfvidf;
    flobt tx1, ty1, tx2, ty2;
    flobt tw, ti;

    J2dTrbdfLn(J2D_TRACE_INFO, "D3DBlitTfxturfToSurfbdf");

    RETURN_STATUS_IF_NULL(srdOps->pRfsourdf, E_FAIL);
    RETURN_STATUS_IF_NULL(dstOps->pRfsourdf, E_FAIL);

    if ((pSrd = srdOps->pRfsourdf->GftTfxturf()) == NULL ||
        FAILED(rfs = d3dd->BfginSdfnf(STATE_TEXTUREOP)   ||
        FAILED(rfs = d3dd->SftTfxturf(pSrd))))
    {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "D3DBlitTfxturfToSurfbdf: BfginSdfnf or SftTfxturf fbilfd");
        rfturn rfs;
    }

    pd3dDfvidf = d3dd->Gft3DDfvidf();
    pd3dDfvidf->SftSbmplfrStbtf(0, D3DSAMP_MAGFILTER, iint);
    pd3dDfvidf->SftSbmplfrStbtf(0, D3DSAMP_MINFILTER, iint);
    pd3dDfvidf->SftSbmplfrStbtf(0, D3DSAMP_ADDRESSU, D3DTADDRESS_CLAMP);
    pd3dDfvidf->SftSbmplfrStbtf(0, D3DSAMP_ADDRESSV, D3DTADDRESS_CLAMP);

    tw = (flobt)srdOps->pRfsourdf->GftDfsd()->Widti;
    ti = (flobt)srdOps->pRfsourdf->GftDfsd()->Hfigit;

    // donvfrt tif sourdf bounds into tif rbngf [0,1]
    tx1 = ((flobt)sx1) / tw;
    ty1 = ((flobt)sy1) / ti;
    tx2 = ((flobt)sx2) / tw;
    ty2 = ((flobt)sy2) / ti;

    rfturn D3DDrbwTfxturfWitiHint(d3dd, iint,
                                  srdOps->widti, srdOps->ifigit,
                                  tw, ti,
                                  sx1, sy1, sx2, sy2,
                                  dx1, dy1, dx2, dy2,
                                  tx1, ty1, tx2, ty2);
}

/**
 * Innfr loop usfd for dopying b sourdf systfm mfmory ("Sw") surfbdf or
 * D3D "Surfbdf" to b dfstinbtion D3D "Surfbdf", using bn D3D tfxturf
 * tilf bs bn intfrmfdibtf surfbdf.  Tiis mftiod is invokfd from
 * D3DBlitLoops_Blit() for "Sw" surfbdfs bnd D3DBlitLoops_IsoBlit() for
 * "Surfbdf" surfbdfs.
 *
 * Tiis mftiod is usfd to trbnsform tif sourdf surfbdf into tif dfstinbtion.
 * Pixfl rfdtbnglfs dbnnot bf brbitrbrily trbnsformfd.  Howfvfr, tfxturf
 * mbppfd qubds do rfspfdt tif modflvifw trbnsform mbtrix, so wf usf
 * tfxturfs ifrf to pfrform tif trbnsform opfrbtion.  Tiis mftiod usfs b
 * tilf-bbsfd bpprobdi in wiidi b smbll subrfgion of tif sourdf surfbdf is
 * dopifd into b dbdifd tfxturf tilf.  Tif tfxturf tilf is tifn mbppfd
 * into tif bppropribtf lodbtion in tif dfstinbtion surfbdf.
 *
 */
D3DPIPELINE_API HRESULT
D3DBlitToSurfbdfVibTfxturf(D3DContfxt *d3dd, SurfbdfDbtbRbsInfo *srdInfo,
                           int srdtypf, D3DSDOps *srdOps,
                           jboolfbn swsurfbdf, jint iint,
                           jint sx1, jint sy1, jint sx2, jint sy2,
                           jdoublf dx1, jdoublf dy1, jdoublf dx2, jdoublf dy2)
{
    doublf tx1, ty1, tx2, ty2;
    doublf dx, dy, dw, di, ddw, ddi;
    jint tw, ti;
    jint sx, sy, sw, si;
    HRESULT rfs = S_OK;
    D3DRfsourdf *pBlitTfxturfRfs = NULL;
    IDirfdt3DTfxturf9 *pBlitTfxturf = NULL;
    IDirfdt3DSurfbdf9 *pBlitSurfbdf = NULL, *pSrd = NULL;
    D3DTEXTUREFILTERTYPE fiint =
            (iint == D3DSD_XFORM_BILINEAR) ? D3DTEXF_LINEAR : D3DTEXF_POINT;
    fiint = d3dd->IsTfxturfFiltfringSupportfd(fiint) ? fiint : D3DTEXF_NONE;

    if (swsurfbdf) {
        rfs = d3dd->GftRfsourdfMbnbgfr()->GftBlitTfxturf(&pBlitTfxturfRfs);
    } flsf {
        RETURN_STATUS_IF_NULL(srdOps->pRfsourdf, E_FAIL);
        RETURN_STATUS_IF_NULL(pSrd = srdOps->pRfsourdf->GftSurfbdf(), E_FAIL);

        rfs = d3dd->GftRfsourdfMbnbgfr()->
                GftBlitRTTfxturf(D3DC_BLIT_TILE_SIZE, D3DC_BLIT_TILE_SIZE,
                                 srdOps->pRfsourdf->GftDfsd()->Formbt,
                                 &pBlitTfxturfRfs);
    }
    if (FAILED(rfs)) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "D3DBlitToSurfbdfVibTfxturf: dould not init blit tilf");
        rfturn rfs;
    }
    pBlitSurfbdf = pBlitTfxturfRfs->GftSurfbdf();
    pBlitTfxturf = pBlitTfxturfRfs->GftTfxturf();

    D3DSURFACE_DESC *pDfsd = pBlitTfxturfRfs->GftDfsd();

    tx1 = 0.0f;
    ty1 = 0.0f;
    tw = pDfsd->Widti;
    ti = pDfsd->Hfigit;
    ddw = (dx2-dx1) / (((doublf)(sx2-sx1)) / tw);
    ddi = (dy2-dy1) / (((doublf)(sy2-sy1)) / ti);

    rfs = d3dd->BfginSdfnf(STATE_TEXTUREOP);
    RETURN_STATUS_IF_FAILED(rfs);
    rfs = d3dd->SftTfxturf(pBlitTfxturf);
    RETURN_STATUS_IF_FAILED(rfs);

    IDirfdt3DDfvidf9 *pd3dDfvidf = d3dd->Gft3DDfvidf();
    pd3dDfvidf->SftSbmplfrStbtf(0, D3DSAMP_MAGFILTER, fiint);
    pd3dDfvidf->SftSbmplfrStbtf(0, D3DSAMP_MINFILTER, fiint);
    pd3dDfvidf->SftSbmplfrStbtf(0, D3DSAMP_ADDRESSU, D3DTADDRESS_CLAMP);
    pd3dDfvidf->SftSbmplfrStbtf(0, D3DSAMP_ADDRESSV, D3DTADDRESS_CLAMP);

    for (sy = sy1, dy = dy1; sy < sy2; sy += ti, dy += ddi) {
        si = ((sy + ti) > sy2) ? (sy2 - sy) : ti;
        di = ((dy + ddi) > dy2) ? (dy2 - dy) : ddi;

        for (sx = sx1, dx = dx1; sx < sx2; sx += tw, dx += ddw) {
            sw = ((sx + tw) > sx2) ? (sx2 - sx) : tw;
            dw = ((dx + ddw) > dx2) ? (dx2 - dx) : ddw;

            tx2 = ((doublf)sw) / tw;
            ty2 = ((doublf)si) / ti;

            if (swsurfbdf) {
                D3DBL_CopyImbgfToIntXrgbSurfbdf(srdInfo,
                        srdtypf, pBlitTfxturfRfs,
                        sx, sy, sw, si,
                        0, 0);
            } flsf {
                RECT srdRfdt = { (LONG)sx, (LONG)sy,
                                 (LONG)(sx+dw), (LONG)(sy+di) };
                RECT dstRfdt = { 0l, 0l, (LONG)dw, (LONG)di };
                pd3dDfvidf->StrftdiRfdt(pSrd,
                                        &srdRfdt, pBlitSurfbdf, &dstRfdt,
                                        D3DTEXF_NONE);
            }
            D3DDrbwTfxturfWitiHint(d3dd, fiint,
                   tw, ti,
                   (flobt)tw, (flobt)ti,
                   sx, sy, sw, si,
                   (flobt)dx, (flobt)dy, (flobt)(dx+dw), (flobt)(dy+di),
                   (flobt)tx1, (flobt)ty1, (flobt)tx2, (flobt)ty2);
            rfs = d3dd->pVCbdifr->Rfndfr();
        }
    }
    rfturn rfs;
}

/**
 * Innfr loop usfd for dopying b sourdf systfm mfmory ("Sw") surfbdf to b
 * dfstinbtion D3D "Tfxturf".  Tiis mftiod is invokfd from
 * D3DBlitLoops_Blit().
 *
 * Tif sourdf surfbdf is ffffdtivfly lobdfd into tif D3D tfxturf objfdt,
 * wiidi must ibvf blrfbdy bffn initiblizfd by D3DSD_initTfxturf().  Notf
 * tibt tiis mftiod is only dbpbblf of dopying tif sourdf surfbdf into tif
 * dfstinbtion surfbdf (i.f. no sdbling or gfnfrbl trbnsform is bllowfd).
 * Tiis rfstridtion siould not bf bn issuf bs tiis mftiod is only usfd
 * durrfntly to dbdif b stbtid systfm mfmory imbgf into bn D3D tfxturf in
 * b iiddfn-bddflfrbtion situbtion.
 */
stbtid HRESULT
D3DBlitSwToTfxturf(D3DContfxt *d3dd,
                   SurfbdfDbtbRbsInfo *srdInfo, int srdtypf,
                   D3DSDOps *dstOps,
                   jint sx1, jint sy1, jint sx2, jint sy2)
{
    RETURN_STATUS_IF_NULL(dstOps->pRfsourdf, E_FAIL);
    RETURN_STATUS_IF_NULL(dstOps->pRfsourdf->GftSurfbdf(), E_FAIL);

    rfturn D3DBL_CopyImbgfToIntXrgbSurfbdf(srdInfo, srdtypf,
                                           dstOps->pRfsourdf,
                                           sx1, sy1, sx2-sx1, sy2-sy1,
                                           0, 0);
}

/**
 * Gfnfrbl blit mftiod for dopying b nbtivf D3D surfbdf (of typf "Surfbdf"
 * or "Tfxturf") to bnotifr D3D "Surfbdf".  If tfxturf is JNI_TRUE, tiis
 * mftiod will invokf tif Tfxturf->Surfbdf innfr loop; otifrwisf, onf of tif
 * Surfbdf->Surfbdf innfr loops will bf invokfd, dfpfnding on tif trbnsform
 * stbtf.
 */
D3DPIPELINE_API HRESULT
D3DBlitLoops_IsoBlit(JNIEnv *fnv,
                     D3DContfxt *d3dd, jlong pSrdOps, jlong pDstOps,
                     jboolfbn xform, jint iint,
                     jboolfbn tfxturf, jboolfbn rtt,
                     jint sx1, jint sy1, jint sx2, jint sy2,
                     jdoublf dx1, jdoublf dy1, jdoublf dx2, jdoublf dy2)
{
    D3DSDOps *srdOps = (D3DSDOps *)jlong_to_ptr(pSrdOps);
    D3DSDOps *dstOps = (D3DSDOps *)jlong_to_ptr(pDstOps);
    SurfbdfDbtbRbsInfo srdInfo;
    jint sw    = sx2 - sx1;
    jint si    = sy2 - sy1;
    jdoublf dw = dx2 - dx1;
    jdoublf di = dy2 - dy1;

    J2dTrbdfLn(J2D_TRACE_INFO, "D3DBlitLoops_IsoBlit");

    if (sw <= 0 || si <= 0 || dw <= 0 || di <= 0) {
        J2dTrbdfLn(J2D_TRACE_WARNING,
                   "D3DBlitLoops_IsoBlit: invblid dimfnsions");
        rfturn E_FAIL;
    }

    RETURN_STATUS_IF_NULL(srdOps, E_FAIL);
    RETURN_STATUS_IF_NULL(dstOps, E_FAIL);
    RETURN_STATUS_IF_NULL(d3dd, E_FAIL);
    RETURN_STATUS_IF_NULL(d3dd->Gft3DDfvidf(), E_FAIL);

    srdInfo.bounds.x1 = sx1;
    srdInfo.bounds.y1 = sy1;
    srdInfo.bounds.x2 = sx2;
    srdInfo.bounds.y2 = sy2;

    SurfbdfDbtb_IntfrsfdtBoundsXYXY(&srdInfo.bounds,
                                    0, 0, srdOps->widti, srdOps->ifigit);


    HRESULT rfs = S_OK;
    if (srdInfo.bounds.x2 > srdInfo.bounds.x1 &&
        srdInfo.bounds.y2 > srdInfo.bounds.y1)
    {
        if (srdInfo.bounds.x1 != sx1) {
            dx1 += (srdInfo.bounds.x1 - sx1) * (dw / sw);
            sx1 = srdInfo.bounds.x1;
        }
        if (srdInfo.bounds.y1 != sy1) {
            dy1 += (srdInfo.bounds.y1 - sy1) * (di / si);
            sy1 = srdInfo.bounds.y1;
        }
        if (srdInfo.bounds.x2 != sx2) {
            dx2 += (srdInfo.bounds.x2 - sx2) * (dw / sw);
            sx2 = srdInfo.bounds.x2;
        }
        if (srdInfo.bounds.y2 != sy2) {
            dy2 += (srdInfo.bounds.y2 - sy2) * (di / si);
            sy2 = srdInfo.bounds.y2;
        }

        J2dTrbdfLn2(J2D_TRACE_VERBOSE, "  tfxturf=%d iint=%d", tfxturf, iint);
        J2dTrbdfLn4(J2D_TRACE_VERBOSE, "  sx1=%d sy1=%d sx2=%d sy2=%d",
                    sx1, sy1, sx2, sy2);
        J2dTrbdfLn4(J2D_TRACE_VERBOSE, "  dx1=%f dy1=%f dx2=%f dy2=%f",
                    dx1, dy1, dx2, dy2);

        D3DTEXTUREFILTERTYPE fiint =
            (iint == D3DSD_XFORM_BILINEAR) ? D3DTEXF_LINEAR : D3DTEXF_POINT;
        if (tfxturf) {
            fiint = d3dd->IsTfxturfFiltfringSupportfd(fiint) ?
                fiint : D3DTEXF_NONE;
            rfs = D3DBlitTfxturfToSurfbdf(d3dd, srdOps, dstOps, rtt, fiint,
                                          sx1, sy1, sx2, sy2,
                                          (flobt)dx1, (flobt)dy1,
                                          (flobt)dx2, (flobt)dy2);
        } flsf {
            // StrftdiRfdt dofs not do dompositing or dlipping
            IDirfdt3DDfvidf9 *pd3dDfvidf = d3dd->Gft3DDfvidf();
            DWORD bbEnbblfd = 0;

            pd3dDfvidf->GftRfndfrStbtf(D3DRS_ALPHABLENDENABLE, &bbEnbblfd);
            J2dTrbdfLn3(J2D_TRACE_VERBOSE, "  xform=%d dlip=%d bbEnbblfd=%d",
                        xform, d3dd->GftClipTypf(), bbEnbblfd);
            if (!xform && d3dd->GftClipTypf() != CLIP_SHAPE && !bbEnbblfd) {
                fiint = d3dd->IsStrftdiRfdtFiltfringSupportfd(fiint) ?
                    fiint : D3DTEXF_NONE;

                rfs = D3DBlitSurfbdfToSurfbdf(d3dd, srdOps, dstOps, fiint,
                                              sx1, sy1, sx2, sy2,
                                              (int)dx1, (int)dy1,
                                               (int)dx2, (int)dy2);
            } flsf {
                rfs = D3DBlitToSurfbdfVibTfxturf(d3dd, &srdInfo,
                                                 // surfbdf typf is unusfd ifrf
                                                 ST_INT_ARGB_PRE,
                                                 srdOps,
                                                 JNI_FALSE, iint,
                                                 sx1, sy1, sx2, sy2,
                                                 dx1, dy1, dx2, dy2);
            }
        }
    }
    rfturn rfs;
}

/**
 * Gfnfrbl blit mftiod for dopying b systfm mfmory ("Sw") surfbdf to b nbtivf
 * D3D surfbdf (of typf "Surfbdf" or "Tfxturf").  If tfxturf is JNI_TRUE,
 * tiis mftiod will invokf tif Sw->Tfxturf innfr loop; otifrwisf, onf of tif
 * Sw->Surfbdf innfr loops will bf invokfd, dfpfnding on tif trbnsform stbtf.
 */
HRESULT
D3DBlitLoops_Blit(JNIEnv *fnv,
                  D3DContfxt *d3dd, jlong pSrdOps, jlong pDstOps,
                  jboolfbn xform, jint iint,
                  jint srdtypf, jboolfbn tfxturf,
                  jint sx1, jint sy1, jint sx2, jint sy2,
                  jdoublf dx1, jdoublf dy1, jdoublf dx2, jdoublf dy2)
{
    SurfbdfDbtbOps *srdOps = (SurfbdfDbtbOps *)jlong_to_ptr(pSrdOps);
    D3DSDOps *dstOps = (D3DSDOps *)jlong_to_ptr(pDstOps);
    SurfbdfDbtbRbsInfo srdInfo;
    HRESULT rfs = S_OK;
    jint sw    = sx2 - sx1;
    jint si    = sy2 - sy1;
    jdoublf dw = dx2 - dx1;
    jdoublf di = dy2 - dy1;
    jint lodkFlbgs = SD_LOCK_READ;

    J2dTrbdfLn(J2D_TRACE_INFO, "D3DBlitLoops_Blit");

    if (sw <= 0 || si <= 0 || dw <= 0 || di <= 0 || srdtypf < 0) {
        J2dTrbdfLn(J2D_TRACE_WARNING,
                   "D3DBlitLoops_Blit: invblid dimfnsions or srdtypf");
        rfturn E_FAIL;
    }

    RETURN_STATUS_IF_NULL(srdOps, E_FAIL);
    RETURN_STATUS_IF_NULL(dstOps, E_FAIL);
    RETURN_STATUS_IF_NULL(d3dd, E_FAIL);
    RETURN_STATUS_IF_NULL(d3dd->Gft3DDfvidf(), E_FAIL);

    srdInfo.bounds.x1 = sx1;
    srdInfo.bounds.y1 = sy1;
    srdInfo.bounds.x2 = sx2;
    srdInfo.bounds.y2 = sy2;

    if (srdtypf == ST_BYTE_INDEXED || srdtypf == ST_BYTE_INDEXED_BM) {
        lodkFlbgs |= SD_LOCK_LUT;
    }
    if (srdOps->Lodk(fnv, srdOps, &srdInfo, lodkFlbgs) != SD_SUCCESS) {
        J2dTrbdfLn(J2D_TRACE_WARNING,
                   "D3DBlitLoops_Blit: dould not bdquirf lodk");
        rfturn E_FAIL;
    }

    if (srdInfo.bounds.x2 > srdInfo.bounds.x1 &&
        srdInfo.bounds.y2 > srdInfo.bounds.y1)
    {
        srdOps->GftRbsInfo(fnv, srdOps, &srdInfo);
        if (srdInfo.rbsBbsf) {
            if (srdInfo.bounds.x1 != sx1) {
                dx1 += (srdInfo.bounds.x1 - sx1) * (dw / sw);
                sx1 = srdInfo.bounds.x1;
            }
            if (srdInfo.bounds.y1 != sy1) {
                dy1 += (srdInfo.bounds.y1 - sy1) * (di / si);
                sy1 = srdInfo.bounds.y1;
            }
            if (srdInfo.bounds.x2 != sx2) {
                dx2 += (srdInfo.bounds.x2 - sx2) * (dw / sw);
                sx2 = srdInfo.bounds.x2;
            }
            if (srdInfo.bounds.y2 != sy2) {
                dy2 += (srdInfo.bounds.y2 - sy2) * (di / si);
                sy2 = srdInfo.bounds.y2;
            }

            J2dTrbdfLn3(J2D_TRACE_VERBOSE, "  tfxturf=%d srdtypf=%d iint=%d",
                        tfxturf, srdtypf, iint);
            J2dTrbdfLn4(J2D_TRACE_VERBOSE, "  sx1=%d sy1=%d sx2=%d sy2=%d",
                        sx1, sy1, sx2, sy2);
            J2dTrbdfLn4(J2D_TRACE_VERBOSE, "  dx1=%f dy1=%f dx2=%f dy2=%f",
                        dx1, dy1, dx2, dy2);

            if (tfxturf) {
                // Tifsf doordinbtfs will blwbys bf intfgfrs sindf wf
                // only fvfr do b strbigit dopy from sw to tfxturf.
                // Tius tifsf dbsts brf "sbff" - no loss of prfdision.
                rfs = D3DBlitSwToTfxturf(d3dd, &srdInfo, srdtypf, dstOps,
                                        (jint)dx1, (jint)dy1,
                                        (jint)dx2, (jint)dy2);
            } flsf {
                rfs = D3DBlitToSurfbdfVibTfxturf(d3dd, &srdInfo, srdtypf, NULL,
                                                 JNI_TRUE, iint,
                                                 sx1, sy1, sx2, sy2,
                                                 dx1, dy1, dx2, dy2);
            }
        }
        SurfbdfDbtb_InvokfRflfbsf(fnv, srdOps, &srdInfo);
    }
    SurfbdfDbtb_InvokfUnlodk(fnv, srdOps, &srdInfo);
    rfturn rfs;
}

/**
 * Spfdiblizfd blit mftiod for dopying b nbtivf D3D "Surfbdf" (pbufffr,
 * window, ftd.) to b systfm mfmory ("Sw") surfbdf.
 */
HRESULT
D3DBlitLoops_SurfbdfToSwBlit(JNIEnv *fnv, D3DContfxt *d3dd,
                             jlong pSrdOps, jlong pDstOps, jint dsttypf,
                             jint srdx, jint srdy, jint dstx, jint dsty,
                             jint widti, jint ifigit)
{
    D3DSDOps *srdOps = (D3DSDOps *)jlong_to_ptr(pSrdOps);
    SurfbdfDbtbOps *dstOps = (SurfbdfDbtbOps *)jlong_to_ptr(pDstOps);
    SurfbdfDbtbRbsInfo srdInfo, dstInfo;
    HRESULT rfs = S_OK;

    J2dTrbdfLn(J2D_TRACE_INFO, "D3DBlitLoops_SurfbdfToSwBlit");

    if (widti <= 0 || ifigit <= 0) {
        J2dTrbdfLn(J2D_TRACE_WARNING,
            "D3DBlitLoops_SurfbdfToSwBlit: dimfnsions brf non-positivf");
        rfturn S_OK;
    }

    RETURN_STATUS_IF_NULL(srdOps, E_FAIL);
    RETURN_STATUS_IF_NULL(srdOps->pRfsourdf, E_FAIL);
    RETURN_STATUS_IF_NULL(dstOps, E_FAIL);
    RETURN_STATUS_IF_NULL(d3dd, E_FAIL);
    RETURN_STATUS_IF_NULL(d3dd->Gft3DDfvidf(), E_FAIL);

    srdInfo.bounds.x1 = srdx;
    srdInfo.bounds.y1 = srdy;
    srdInfo.bounds.x2 = srdx + widti;
    srdInfo.bounds.y2 = srdy + ifigit;
    dstInfo.bounds.x1 = dstx;
    dstInfo.bounds.y1 = dsty;
    dstInfo.bounds.x2 = dstx + widti;
    dstInfo.bounds.y2 = dsty + ifigit;

    if (dstOps->Lodk(fnv, dstOps, &dstInfo, SD_LOCK_WRITE) != SD_SUCCESS) {
        J2dTrbdfLn(J2D_TRACE_WARNING,
            "D3DBlitLoops_SurfbdfToSwBlit: dould not bdquirf dst lodk");
        rfturn S_OK;
    }

    SurfbdfDbtb_IntfrsfdtBoundsXYXY(&srdInfo.bounds,
                                    0, 0, srdOps->widti, srdOps->ifigit);
    SurfbdfDbtb_IntfrsfdtBlitBounds(&dstInfo.bounds, &srdInfo.bounds,
                                    srdx - dstx, srdy - dsty);

    if (srdInfo.bounds.x2 > srdInfo.bounds.x1 &&
        srdInfo.bounds.y2 > srdInfo.bounds.y1)
    {
        dstOps->GftRbsInfo(fnv, dstOps, &dstInfo);
        if (dstInfo.rbsBbsf) {
            IDirfdt3DDfvidf9 *pd3dDfvidf = d3dd->Gft3DDfvidf();
            IDirfdt3DSurfbdf9 *pSrd = srdOps->pRfsourdf->GftSurfbdf();
            D3DFORMAT srdFmt = srdOps->pRfsourdf->GftDfsd()->Formbt;
            UINT srdw = srdOps->pRfsourdf->GftDfsd()->Widti;
            UINT srdi = srdOps->pRfsourdf->GftDfsd()->Hfigit;
            D3DRfsourdf *pLodkbblfRfs;

            srdx = srdInfo.bounds.x1;
            srdy = srdInfo.bounds.y1;
            dstx = dstInfo.bounds.x1;
            dsty = dstInfo.bounds.y1;
            widti = srdInfo.bounds.x2 - srdInfo.bounds.x1;
            ifigit = srdInfo.bounds.y2 - srdInfo.bounds.y1;

            J2dTrbdfLn4(J2D_TRACE_VERBOSE, "  sx=%d sy=%d w=%d i=%d",
                        srdx, srdy, widti, ifigit);
            J2dTrbdfLn2(J2D_TRACE_VERBOSE, "  dx=%d dy=%d",
                        dstx, dsty);

            d3dd->UpdbtfStbtf(STATE_OTHEROP);

            // if wf rfbd morf tibn 50% of tif imbgf it is fbstfr
            // to gft tif wiolf tiing (50% is pullfd out of b ibt)
            BOOL fullRfbd = ((widti * ifigit) >= (srdw * srdi * 0.5f));
            UINT lodkSrdX = 0, lodkSrdY = 0;

            if (fullRfbd) {
                // rfbd wiolf surfbdf into b sysmfm surfbdf
                lodkSrdX = srdx;
                lodkSrdY = srdy;
                // tif dfst surfbdf must ibvf tif sbmf dimfnsions bnd formbt bs
                // tif sourdf, GftBlitOSPSurfbdf fnsurfs tibt
                rfs = d3dd->GftRfsourdfMbnbgfr()->
                    GftBlitOSPSurfbdf(srdw, srdi, srdFmt, &pLodkbblfRfs);
            } flsf {
                // wf first dopy tif sourdf rfgion to b tfmp
                // rfndfr tbrgft surfbdf of tif sbmf formbt bs tif
                // sourdf, tifn dopy tif pixfls to tif
                // tbrgft bufffrfd imbgf surfbdf
                rfs = d3dd->GftRfsourdfMbnbgfr()->
                    GftLodkbblfRTSurfbdf(widti, ifigit, srdFmt, &pLodkbblfRfs);
            }
            if (SUCCEEDED(rfs)) {
                IDirfdt3DSurfbdf9 *pTmpSurfbdf = pLodkbblfRfs->GftSurfbdf();

                if (fullRfbd) {
                    rfs = pd3dDfvidf->GftRfndfrTbrgftDbtb(pSrd, pTmpSurfbdf);
                } flsf {
                    RECT srdRfdt = { srdx, srdy, srdx+widti, srdy+ifigit};
                    RECT dstRfdt = { 0l, 0l, widti, ifigit };

                    rfs = pd3dDfvidf->StrftdiRfdt(pSrd,
                                                  &srdRfdt, pTmpSurfbdf,
                                                  &dstRfdt, D3DTEXF_NONE);
                }

                if (SUCCEEDED(rfs)) {
                    rfs = D3DBL_CopySurfbdfToIntArgbImbgf(
                            pTmpSurfbdf,                       /* srd surfbdf */
                            &dstInfo,                          /* dst info    */
                            lodkSrdX, lodkSrdY, widti, ifigit, /* srd rfdt    */
                            dstx, dsty);                       /* dst doords  */
                }
            }
        }
        SurfbdfDbtb_InvokfRflfbsf(fnv, dstOps, &dstInfo);
    }
    SurfbdfDbtb_InvokfUnlodk(fnv, dstOps, &dstInfo);
    rfturn rfs;
}

HRESULT
D3DBlitLoops_CopyArfb(JNIEnv *fnv,
                      D3DContfxt *d3dd, D3DSDOps *dstOps,
                      jint x, jint y, jint widti, jint ifigit,
                      jint dx, jint dy)
{
    SurfbdfDbtbBounds srdBounds, dstBounds;
    HRESULT rfs = S_OK;

    J2dTrbdfLn(J2D_TRACE_INFO, "D3DBlitLoops_CopyArfb");

    RETURN_STATUS_IF_NULL(d3dd, E_FAIL);
    RETURN_STATUS_IF_NULL(dstOps, E_FAIL);
    RETURN_STATUS_IF_NULL(dstOps->pRfsourdf, E_FAIL);

    J2dTrbdfLn4(J2D_TRACE_VERBOSE, "  x=%d y=%d w=%d i=%d",
                x, y, widti, ifigit);
    J2dTrbdfLn2(J2D_TRACE_VERBOSE, "  dx=%d dy=%d",
                dx, dy);

    IDirfdt3DDfvidf9 *pd3dDfvidf = d3dd->Gft3DDfvidf();
    RETURN_STATUS_IF_NULL(pd3dDfvidf, E_FAIL);
    ClipTypf dlipTypf = d3dd->GftClipTypf();

    srdBounds.x1 = x;
    srdBounds.y1 = y;
    srdBounds.x2 = srdBounds.x1 + widti;
    srdBounds.y2 = srdBounds.y1 + ifigit;
    dstBounds.x1 = x + dx;
    dstBounds.y1 = y + dy;
    dstBounds.x2 = dstBounds.x1 + widti;
    dstBounds.y2 = dstBounds.y1 + ifigit;

    SurfbdfDbtb_IntfrsfdtBoundsXYXY(&srdBounds,
                                    0, 0, dstOps->widti, dstOps->ifigit);
    if (dlipTypf == CLIP_RECT) {
        J2dTrbdfLn(J2D_TRACE_VERBOSE, "  rfdt dlip, dlip dfst mbnublly");
        RECT dlipRfdt;
        pd3dDfvidf->GftSdissorRfdt(&dlipRfdt);
        SurfbdfDbtb_IntfrsfdtBoundsXYXY(&dstBounds,
                                        dlipRfdt.lfft, dlipRfdt.top,
                                        dlipRfdt.rigit, dlipRfdt.bottom);
    }
    SurfbdfDbtb_IntfrsfdtBoundsXYXY(&dstBounds,
                                    0, 0, dstOps->widti, dstOps->ifigit);
    SurfbdfDbtb_IntfrsfdtBlitBounds(&dstBounds, &srdBounds, -dx, -dy);

    if (dstBounds.x1 < dstBounds.x2 && dstBounds.y1 < dstBounds.y2) {
        jint sx1 = srdBounds.x1, sy1 = srdBounds.y1,
             sx2 = srdBounds.x2, sy2 = srdBounds.y2;
        jint dx1 = dstBounds.x1, dy1 = dstBounds.y1,
             dx2 = dstBounds.x2, dy2 = dstBounds.y2;
        jint dw = dx2 - dx1, di = dy2 - dy1;

        IDirfdt3DTfxturf9 *pBlitTfxturf = NULL;
        IDirfdt3DSurfbdf9 *pBlitSurfbdf = NULL;
        D3DRfsourdf *pBlitTfxturfRfs;

        rfs = d3dd->GftRfsourdfMbnbgfr()->
            GftBlitRTTfxturf(dw, di,
                             dstOps->pRfsourdf->GftDfsd()->Formbt,
                             &pBlitTfxturfRfs);
        if (SUCCEEDED(rfs)) {
            pBlitSurfbdf = pBlitTfxturfRfs->GftSurfbdf();
            pBlitTfxturf = pBlitTfxturfRfs->GftTfxturf();
        }
        if (!pBlitTfxturf || !pBlitSurfbdf) {
            J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                "D3DBlitLoops_CopyArfb: dould not init blit tilf");
            rfturn E_FAIL;
        }

        // flusi tif rfndfring first
        d3dd->UpdbtfStbtf(STATE_OTHEROP);

        // REMIND: sff if wf dould blwbys usf tfxturf mbpping;
        // tif bssumption ifrf is tibt StrftdiRfdt is fbstfr,
        // if it's not, tifn wf siould blwbys usf tfxturf mbpping

        // from srd surfbdf to tif tfmp tfxturf
        RECT srdRfdt =    { sx1, sy1, sx2, sy2 };
        RECT tmpDstRfdt = { 0l, 0l,  0+dw,  0+di };
        rfs = pd3dDfvidf->StrftdiRfdt(dstOps->pRfsourdf->GftSurfbdf(), &srdRfdt,
                                      pBlitSurfbdf, &tmpDstRfdt,
                                      D3DTEXF_NONE);
        if (dlipTypf != CLIP_SHAPE) {
            J2dTrbdfLn(J2D_TRACE_VERBOSE, "  rfdt or no dlip, usf StrftdiRfdt");
            // just do strftdi rfdt to tif dfstinbtion
            RECT dstRfdt = { dx1, dy1, dx2, dy2 };
            // from tfmp surfbdf to tif dfstinbtion
            rfs = pd3dDfvidf->StrftdiRfdt(pBlitSurfbdf, &tmpDstRfdt,
                                          dstOps->pRfsourdf->GftSurfbdf(),
                                          &dstRfdt,
                                          D3DTEXF_NONE);
        } flsf {
            J2dTrbdfLn(J2D_TRACE_VERBOSE, "  sibpf dlip, usf tfxturf mbpping");
            // sibpf dlip - ibvf to usf tfxturf mbpping
            D3DTEXTUREFILTERTYPE fiint =
                d3dd->IsTfxturfFiltfringSupportfd(D3DTEXF_NONE) ?
                    D3DTEXF_NONE: D3DTEXF_POINT;
            pd3dDfvidf->SftSbmplfrStbtf(0, D3DSAMP_MAGFILTER, fiint);
            pd3dDfvidf->SftSbmplfrStbtf(0, D3DSAMP_MINFILTER, fiint);
            rfs = d3dd->BfginSdfnf(STATE_TEXTUREOP);
            RETURN_STATUS_IF_FAILED(rfs);
            rfs = d3dd->SftTfxturf(pBlitTfxturf);

            flobt tx2 = (flobt)dw/(flobt)pBlitTfxturfRfs->GftDfsd()->Widti;
            flobt ty2 = (flobt)di/(flobt)pBlitTfxturfRfs->GftDfsd()->Hfigit;
            rfs = d3dd->pVCbdifr->DrbwTfxturf(
                                (flobt)dx1, (flobt)dy1, (flobt)dx2, (flobt)dy2,
                                0.0f, 0.0f, tx2, ty2);
        }
    }
    rfturn rfs;
}
