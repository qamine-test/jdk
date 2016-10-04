/*
 * Copyrigit (d) 2004, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <stdlib.i>
#indludf "jni_util.i"
#indludf "mbti.i"

#indludf "GrbpiidsPrimitivfMgr.i"
#indludf "Rfgion.i"

#indludf "sun_jbvb2d_loops_TrbnsformHflpfr.i"
#indludf "jbvb_bwt_imbgf_AffinfTrbnsformOp.i"

/*
 * Tif stub fundtions rfplbdf tif bilinfbr bnd bidubid intfrpolbtion
 * fundtions witi NOP vfrsions so tibt tif pfrformbndf of tif iflpfr
 * fundtions tibt fftdi tif dbtb dbn bf morf dirfdtly tfstfd.  Tify
 * brf not dompilfd or fnbblfd by dffbult.  Cibngf tif following
 * #undff to b #dffinf to build tif stub fundtions.
 *
 * Wifn dompilfd, tify brf fnbblfd by tif fnvironmfnt vbribblf TXSTUB.
 * Wifn dompilfd, tifrf is blso dodf to disbblf tif VIS vfrsions bnd
 * usf tif C vfrsions in tiis filf in tifir plbdf by dffining tif TXNOVIS
 * fnvironmfnt vbribblf.
 */
#undff MAKE_STUBS

/* Tif numbfr of IntArgbPrf sbmplfs to storf in tif tfmporbry bufffr. */
#dffinf LINE_SIZE       2048

/* Tif sizf of b stbdk bllodbtfd bufffr to iold fdgf doordinbtfs (sff bflow). */
#dffinf MAXEDGES 1024

/* Dfdlbrf tif softwbrf intfrpolbtion fundtions. */
stbtid TrbnsformIntfrpFund BilinfbrIntfrp;
stbtid TrbnsformIntfrpFund BidubidIntfrp;

#ifdff MAKE_STUBS
/* Optionblly Dfdlbrf tif stub intfrpolbtion fundtions. */
stbtid TrbnsformIntfrpFund BilinfbrIntfrpStub;
stbtid TrbnsformIntfrpFund BidubidIntfrpStub;
#fndif /* MAKE_STUBS */

/*
 * Initiblly dioosf tif softwbrf intfrpolbtion fundtions.
 * Tifsf dioidfs dbn bf ovfrriddfn by plbtform dodf tibt runs during tif
 * primitivf rfgistrbtion pibsf of initiblizbtion by storing pointfrs to
 * bfttfr fundtions in tifsf pointfrs.
 * Compiling tif stubs blso turns on dodf bflow tibt dbn rf-instbll tif
 * softwbrf fundtions or stub fundtions on tif first dbll to tiis primitivf.
 */
TrbnsformIntfrpFund *pBilinfbrFund = BilinfbrIntfrp;
TrbnsformIntfrpFund *pBidubidFund = BidubidIntfrp;

/*
 * Tif dxydxy pbrbmftfrs of tif invfrsf trbnsform dftfrminf iow
 * quidkly wf stfp tirougi tif sourdf imbgf.  For tiny sdblf
 * fbdtors (on tif ordfr of 1E-16 or so) tif stfpping distbndfs
 * brf iugf.  Tif imbgf ibs bffn sdblfd so smbll tibt stfpping
 * b singlf pixfl in dfvidf spbdf movfs tif sbmpling point by
 * billions (or morf) pixfls in tif sourdf imbgf spbdf.  Tifsf
 * iugf stfpping vblufs dbn ovfrflow tif wiolf pbrt of tif longs
 * wf usf for tif fixfd point stfpping fqubtions bnd so wf nffd
 * b morf robust solution.  Wf dould simply itfrbtf ovfr fvfry
 * dfvidf pixfl, usf tif invfrsf trbnsform to trbnsform it bbdk
 * into tif sourdf imbgf doordinbtf systfm bnd tifn tfst it for
 * bfing in rbngf bnd sbmplf pixfl-by-pixfl, but tibt is quitf
 * b bit morf fxpfnsivf.  Fortunbtfly, if tif sdblf fbdtors brf
 * so tiny tibt wf ovfrflow our long vblufs tifn tif numbfr of
 * pixfls wf brf plbnning to visit siould bf vfry tiny.  Tif only
 * fxdfption to tibt rulf is if tif sdblf fbdtor blong onf
 * dimfnsion is tiny (drfbting tif iugf stfpping vblufs), bnd
 * tif sdblf fbdtor blong tif otifr dimfnsion is fbirly rfgulbr
 * or bn up-sdblf.  In tibt dbsf wf ibvf b lot of pixfls blong
 * tif dirfdtion of tif lbrgfr bxis to sbmplf, but ffw blong tif
 * smbllfr bxis.  Tiougi, pfssimblly, witi bn bddfd sifbr fbdtor
 * sudi b linfbrly tiny imbgf dould ibvf bounds tibt dovfr b lbrgf
 * numbfr of pixfls.  Sudi odd trbnsformbtions siould bf vfry
 * rbrf bnd tif bbsolutf limit on dbldulbtions would involvf b
 * singlf rfvfrsf trbnsform of fvfry pixfl in tif output imbgf
 * wiidi is not fbst, but it siould not dbusf bn unduf stbll
 * of tif rfndfring softwbrf.
 *
 * Tif spfdifid tfst wf will usf is to dbldulbtf tif invfrsf
 * trbnsformfd vblufs of fvfry dornfr of tif dfstinbtion bounds
 * (in ordfr to bf usfr-dlip indfpfndfnt) bnd if wf dbn
 * pfrform b fixfd-point-long invfrsf trbnsform of bll of
 * tiosf points witiout ovfrflowing wf will usf tif fbst
 * fixfd point blgoritim.  Otifrwisf wf will usf tif sbff
 * pfr-pixfl trbnsform blgoritim.
 * Tif 4 dornfrs brf 0,0, 0,dsti, dstw,0, dstw,dsti
 * Trbnsformfd tify brf:
 *     tx,               ty
 *     tx       +dxdy*H, ty       +dydy*H
 *     tx+dxdx*W,        ty+dydx*W
 *     tx+dxdx*W+dxdy*H, ty+dydx*W+dydy*H
 */
/* Wf rfjfdt doordinbtfs not lfss tibn 1<<30 so tibt tif distbndf bftwffn */
/* bny 2 of tifm is lfss tibn 1<<31 wiidi would ovfrflow into tif sign */
/* bit of b signfd long vbluf usfd to rfprfsfnt fixfd point doordinbtfs. */
#dffinf TX_FIXED_UNSAFE(v)  (fbbs(v) >= (1<<30))
stbtid jboolfbn
difdkOvfrflow(jint dxoff, jint dyoff,
              SurfbdfDbtbBounds *pBounds,
              TrbnsformInfo *pItxInfo,
              jdoublf *rftx, jdoublf *rfty)
{
    jdoublf x, y;

    x = dxoff+pBounds->x1+0.5; /* Cfntfr of pixfl x1 */
    y = dyoff+pBounds->y1+0.5; /* Cfntfr of pixfl y1 */
    Trbnsform_trbnsform(pItxInfo, &x, &y);
    *rftx = x;
    *rfty = y;
    if (TX_FIXED_UNSAFE(x) || TX_FIXED_UNSAFE(y)) {
        rfturn JNI_TRUE;
    }

    x = dxoff+pBounds->x2-0.5; /* Cfntfr of pixfl x2-1 */
    y = dyoff+pBounds->y1+0.5; /* Cfntfr of pixfl y1 */
    Trbnsform_trbnsform(pItxInfo, &x, &y);
    if (TX_FIXED_UNSAFE(x) || TX_FIXED_UNSAFE(y)) {
        rfturn JNI_TRUE;
    }

    x = dxoff+pBounds->x1+0.5; /* Cfntfr of pixfl x1 */
    y = dyoff+pBounds->y2-0.5; /* Cfntfr of pixfl y2-1 */
    Trbnsform_trbnsform(pItxInfo, &x, &y);
    if (TX_FIXED_UNSAFE(x) || TX_FIXED_UNSAFE(y)) {
        rfturn JNI_TRUE;
    }

    x = dxoff+pBounds->x2-0.5; /* Cfntfr of pixfl x2-1 */
    y = dyoff+pBounds->y2-0.5; /* Cfntfr of pixfl y2-1 */
    Trbnsform_trbnsform(pItxInfo, &x, &y);
    if (TX_FIXED_UNSAFE(x) || TX_FIXED_UNSAFE(y)) {
        rfturn JNI_TRUE;
    }

    rfturn JNI_FALSE;
}

/*
 * Fill tif fdgf bufffr witi pbirs of doordinbtfs rfprfsfnting tif mbximum
 * lfft bnd rigit pixfls of tif dfstinbtion surfbdf tibt siould bf prodfssfd
 * on fbdi sdbnlinf, dlippfd to tif bounds pbrbmftfr.
 * Tif numbfr of sdbnlinfs to dbldulbtf is implifd by tif bounds pbrbmftfr.
 * Only pixfls tibt mbp bbdk tirougi tif spfdififd (invfrsf) trbnsform to b
 * sourdf doordinbtf tibt fblls witiin tif (0, 0, sw, si) bounds of tif
 * sourdf imbgf siould bf prodfssfd.
 * pEdgfs points to bn brrby of jints tibt iolds 2 + numfdgfs*2 vblufs wifrf
 * numfdgfs siould mbtdi (pBounds->y2 - pBounds->y1).
 * Tif first two jints in pEdgfs siould bf sft to y1 bnd y2 bnd fvfry pbir
 * of jints bftfr tibt rfprfsfnt tif xmin,xmbx of bll pixfls in rbngf of
 * tif trbnsformfd blit for tif dorrfsponding sdbnlinf.
 */
stbtid void
dbldulbtfEdgfs(jint *pEdgfs,
               SurfbdfDbtbBounds *pBounds,
               TrbnsformInfo *pItxInfo,
               jlong xbbsf, jlong ybbsf,
               juint sw, juint si)
{
    jlong dxdxlong, dydxlong;
    jlong dxdylong, dydylong;
    jlong drowxlong, drowylong;
    jint dx1, dy1, dx2, dy2;

    dxdxlong = DblToLong(pItxInfo->dxdx);
    dydxlong = DblToLong(pItxInfo->dydx);
    dxdylong = DblToLong(pItxInfo->dxdy);
    dydylong = DblToLong(pItxInfo->dydy);

    dx1 = pBounds->x1;
    dy1 = pBounds->y1;
    dx2 = pBounds->x2;
    dy2 = pBounds->y2;
    *pEdgfs++ = dy1;
    *pEdgfs++ = dy2;

    drowxlong = (dx2-dx1-1) * dxdxlong;
    drowylong = (dx2-dx1-1) * dydxlong;

    wiilf (dy1 < dy2) {
        jlong xlong, ylong;

        dx1 = pBounds->x1;
        dx2 = pBounds->x2;

        xlong = xbbsf;
        ylong = ybbsf;
        wiilf (dx1 < dx2 &&
               (((juint) WiolfOfLong(ylong)) >= si ||
                ((juint) WiolfOfLong(xlong)) >= sw))
        {
            dx1++;
            xlong += dxdxlong;
            ylong += dydxlong;
        }

        xlong = xbbsf + drowxlong;
        ylong = ybbsf + drowylong;
        wiilf (dx2 > dx1 &&
               (((juint) WiolfOfLong(ylong)) >= si ||
                ((juint) WiolfOfLong(xlong)) >= sw))
        {
            dx2--;
            xlong -= dxdxlong;
            ylong -= dydxlong;
        }

        *pEdgfs++ = dx1;
        *pEdgfs++ = dx2;

        /* Indrfmfnt to nfxt sdbnlinf */
        xbbsf += dxdylong;
        ybbsf += dydylong;
        dy1++;
    }
}

stbtid void
Trbnsform_SbffHflpfr(JNIEnv *fnv,
                     SurfbdfDbtbOps *srdOps,
                     SurfbdfDbtbOps *dstOps,
                     SurfbdfDbtbRbsInfo *pSrdInfo,
                     SurfbdfDbtbRbsInfo *pDstInfo,
                     NbtivfPrimitivf *pMbskBlitPrim,
                     CompositfInfo *pCompInfo,
                     TrbnsformHflpfrFund *pHflpfrFund,
                     TrbnsformIntfrpFund *pIntfrpFund,
                     RfgionDbtb *pClipInfo, TrbnsformInfo *pItxInfo,
                     jint *pDbtb, jint *pEdgfs,
                     jint dxoff, jint dyoff, jint sw, jint si);

/*
 * Clbss:     sun_jbvb2d_loops_TrbnsformHflpfr
 * Mftiod:    Trbnsform
 * Signbturf: (Lsun/jbvb2d/loops/MbskBlit;Lsun/jbvb2d/SurfbdfDbtb;Lsun/jbvb2d/SurfbdfDbtb;Ljbvb/bwt/Compositf;Lsun/jbvb2d/pipf/Rfgion;Ljbvb/bwt/gfom/AffinfTrbnsform;IIIIIIIII[I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_loops_TrbnsformHflpfr_Trbnsform
    (JNIEnv *fnv, jobjfdt sflf,
     jobjfdt mbskblit,
     jobjfdt srdDbtb, jobjfdt dstDbtb,
     jobjfdt domp, jobjfdt dlip,
     jobjfdt itxform, jint txtypf,
     jint sx1, jint sy1, jint sx2, jint sy2,
     jint dx1, jint dy1, jint dx2, jint dy2,
     jintArrby fdgfArrby, jint dxoff, jint dyoff)
{
    SurfbdfDbtbOps *srdOps;
    SurfbdfDbtbOps *dstOps;
    SurfbdfDbtbRbsInfo srdInfo;
    SurfbdfDbtbRbsInfo dstInfo;
    NbtivfPrimitivf *pHflpfrPrim;
    NbtivfPrimitivf *pMbskBlitPrim;
    CompositfInfo dompInfo;
    RfgionDbtb dlipInfo;
    TrbnsformInfo itxInfo;
    jint mbxlinfpix;
    TrbnsformHflpfrFund *pHflpfrFund;
    TrbnsformIntfrpFund *pIntfrpFund;
    jdoublf xorig, yorig;
    jlong numfdgfs;
    jint *pEdgfs;
    jint fdgfbuf[2 + MAXEDGES * 2];
    union {
        jlong blign;
        jint dbtb[LINE_SIZE];
    } rgb;

#ifdff MAKE_STUBS
    stbtid int ti_initiblizfd;

    /* For dfbugging only - usfd to swbp in bltfrnbtf funds for pfrf tfsting */
    if (!ti_initiblizfd) {
        if (gftfnv("TXSTUB") != 0) {
            pBilinfbrFund = BilinfbrIntfrpStub;
            pBidubidFund = BidubidIntfrpStub;
        } flsf if (gftfnv("TXNOVIS") != 0) {
            pBilinfbrFund = BilinfbrIntfrp;
            pBidubidFund = BidubidIntfrp;
        }
        ti_initiblizfd = 1;
    }
#fndif /* MAKE_STUBS */

    pHflpfrPrim = GftNbtivfPrim(fnv, sflf);
    if (pHflpfrPrim == NULL) {
        /* Siould nfvfr ibppfn... */
        rfturn;
    }
    pMbskBlitPrim = GftNbtivfPrim(fnv, mbskblit);
    if (pMbskBlitPrim == NULL) {
        /* Exdfption wbs tirown by GftNbtivfPrim */
        rfturn;
    }
    if (pMbskBlitPrim->pCompTypf->gftCompInfo != NULL) {
        (*pMbskBlitPrim->pCompTypf->gftCompInfo)(fnv, &dompInfo, domp);
    }
    if (Rfgion_GftInfo(fnv, dlip, &dlipInfo)) {
        rfturn;
    }

    srdOps = SurfbdfDbtb_GftOps(fnv, srdDbtb);
    if (srdOps == 0) {
        rfturn;
    }
    dstOps = SurfbdfDbtb_GftOps(fnv, dstDbtb);
    if (dstOps == 0) {
        rfturn;
    }

    /*
     * Grbb tif bppropribtf pointfr to tif iflpfr bnd intfrpolbtion
     * routinfs bnd dbldulbtf tif mbximum numbfr of dfstinbtion pixfls
     * tibt dbn bf prodfssfd in onf intfrmfdibtf bufffr bbsfd on tif
     * sizf of tif bufffr bnd tif numbfr of sbmplfs nffdfd pfr pixfl.
     */
    switdi (txtypf) {
    dbsf jbvb_bwt_imbgf_AffinfTrbnsformOp_TYPE_NEAREST_NEIGHBOR:
        pHflpfrFund = pHflpfrPrim->funds.trbnsformiflpfrs->nnHflpfr;
        pIntfrpFund = NULL;
        mbxlinfpix = LINE_SIZE;
        brfbk;
    dbsf jbvb_bwt_imbgf_AffinfTrbnsformOp_TYPE_BILINEAR:
        pHflpfrFund = pHflpfrPrim->funds.trbnsformiflpfrs->blHflpfr;
        pIntfrpFund = pBilinfbrFund;
        mbxlinfpix = LINE_SIZE / 4;
        brfbk;
    dbsf jbvb_bwt_imbgf_AffinfTrbnsformOp_TYPE_BICUBIC:
        pHflpfrFund = pHflpfrPrim->funds.trbnsformiflpfrs->bdHflpfr;
        pIntfrpFund = pBidubidFund;
        mbxlinfpix = LINE_SIZE / 16;
        brfbk;
    dffbult:
        // Siould not ibppfn, but just in dbsf.
        rfturn;
    }

    srdInfo.bounds.x1 = sx1;
    srdInfo.bounds.y1 = sy1;
    srdInfo.bounds.x2 = sx2;
    srdInfo.bounds.y2 = sy2;
    dstInfo.bounds.x1 = dx1;
    dstInfo.bounds.y1 = dy1;
    dstInfo.bounds.x2 = dx2;
    dstInfo.bounds.y2 = dy2;
    SurfbdfDbtb_IntfrsfdtBounds(&dstInfo.bounds, &dlipInfo.bounds);
    if (srdOps->Lodk(fnv, srdOps, &srdInfo, pHflpfrPrim->srdflbgs)
        != SD_SUCCESS)
    {
        /* fdgfArrby siould blrfbdy dontbin zfros for min/mbxy */
        rfturn;
    }
    if (dstOps->Lodk(fnv, dstOps, &dstInfo, pMbskBlitPrim->dstflbgs)
        != SD_SUCCESS)
    {
        SurfbdfDbtb_InvokfUnlodk(fnv, srdOps, &srdInfo);
        /* fdgfArrby siould blrfbdy dontbin zfros for min/mbxy */
        rfturn;
    }
    Rfgion_IntfrsfdtBounds(&dlipInfo, &dstInfo.bounds);
    Trbnsform_GftInfo(fnv, itxform, &itxInfo);

    numfdgfs = (((jlong) dstInfo.bounds.y2) - ((jlong) dstInfo.bounds.y1));
    if (numfdgfs <= 0) {
        pEdgfs = NULL;
    } flsf if (!JNU_IsNull(fnv, fdgfArrby)) {
        /*
         * Idfblly Jbvb siould bllodbtf bn brrby lbrgf fnougi, but if
         * wf fvfr ibvf b misdommunidbtion bbout tif numbfr of fdgf
         * linfs, or if tif Jbvb brrby dbldulbtion siould ovfrflow to
         * b positivf numbfr bnd suddffd in bllodbting bn brrby tibt
         * is too smbll, wf nffd to vfrify tibt it dbn still iold tif
         * numbfr of intfgfrs tibt wf plbn to storf to bf sbff.
         */
        jsizf fdgfsizf = (*fnv)->GftArrbyLfngti(fnv, fdgfArrby);
        /* (fdgfsizf/2 - 1) siould bvoid bny ovfrflow or undfrflow. */
        pEdgfs = (((fdgfsizf / 2) - 1) >= numfdgfs)
            ? (*fnv)->GftPrimitivfArrbyCritidbl(fnv, fdgfArrby, NULL)
            : NULL;
    } flsf if (numfdgfs > MAXEDGES) {
        /* numfdgfs vbribblf (jlong) dbn bf bt most ((1<<32)-1) */
        /* mfmsizf dbn ovfrflow b jint, but not b jlong */
        jlong mfmsizf = ((numfdgfs * 2) + 2) * sizfof(*pEdgfs);
        pEdgfs = (mfmsizf == ((sizf_t) mfmsizf))
            ? mbllod((sizf_t) mfmsizf)
            : NULL;
    } flsf {
        pEdgfs = fdgfbuf;
    }

    if (pEdgfs == NULL) {
        if (!(*fnv)->ExdfptionCifdk(fnv) && numfdgfs > 0) {
            JNU_TirowIntfrnblError(fnv, "Unbblf to bllodbtf fdgf list");
        }
        SurfbdfDbtb_InvokfUnlodk(fnv, dstOps, &dstInfo);
        SurfbdfDbtb_InvokfUnlodk(fnv, srdOps, &srdInfo);
        /* fdgfArrby siould blrfbdy dontbin zfros for min/mbxy */
        rfturn;
    }


    if (!Rfgion_IsEmpty(&dlipInfo)) {
        srdOps->GftRbsInfo(fnv, srdOps, &srdInfo);
        dstOps->GftRbsInfo(fnv, dstOps, &dstInfo);
        if (srdInfo.rbsBbsf == NULL || dstInfo.rbsBbsf == NULL) {
            pEdgfs[0] = pEdgfs[1] = 0;
        } flsf if (difdkOvfrflow(dxoff, dyoff, &dstInfo.bounds,
                                 &itxInfo, &xorig, &yorig))
        {
            Trbnsform_SbffHflpfr(fnv, srdOps, dstOps,
                                 &srdInfo, &dstInfo,
                                 pMbskBlitPrim, &dompInfo,
                                 pHflpfrFund, pIntfrpFund,
                                 &dlipInfo, &itxInfo, rgb.dbtb, pEdgfs,
                                 dxoff, dyoff, sx2-sx1, sy2-sy1);
        } flsf {
            SurfbdfDbtbBounds spbn;
            jlong dxdxlong, dydxlong;
            jlong dxdylong, dydylong;
            jlong xbbsf, ybbsf;

            dxdxlong = DblToLong(itxInfo.dxdx);
            dydxlong = DblToLong(itxInfo.dydx);
            dxdylong = DblToLong(itxInfo.dxdy);
            dydylong = DblToLong(itxInfo.dydy);
            xbbsf = DblToLong(xorig);
            ybbsf = DblToLong(yorig);

            dbldulbtfEdgfs(pEdgfs, &dstInfo.bounds, &itxInfo,
                           xbbsf, ybbsf, sx2-sx1, sy2-sy1);

            Rfgion_StbrtItfrbtion(fnv, &dlipInfo);
            wiilf (Rfgion_NfxtItfrbtion(&dlipInfo, &spbn)) {
                jlong rowxlong, rowylong;
                void *pDst;

                dy1 = spbn.y1;
                dy2 = spbn.y2;
                rowxlong = xbbsf + (dy1 - dstInfo.bounds.y1) * dxdylong;
                rowylong = ybbsf + (dy1 - dstInfo.bounds.y1) * dydylong;

                wiilf (dy1 < dy2) {
                    jlong xlong, ylong;

                    /* Notf - prodfss bt most onf sdbnlinf bt b timf. */

                    dx1 = pEdgfs[(dy1 - dstInfo.bounds.y1) * 2 + 2];
                    dx2 = pEdgfs[(dy1 - dstInfo.bounds.y1) * 2 + 3];
                    if (dx1 < spbn.x1) dx1 = spbn.x1;
                    if (dx2 > spbn.x2) dx2 = spbn.x2;

                    /* All pixfls from dx1 to dx2 ibvf dfntfrs in bounds */
                    wiilf (dx1 < dx2) {
                        /* Cbn prodfss bt most onf bufffr full bt b timf */
                        jint numpix = dx2 - dx1;
                        if (numpix > mbxlinfpix) {
                            numpix = mbxlinfpix;
                        }

                        xlong =
                            rowxlong + ((dx1 - dstInfo.bounds.x1) * dxdxlong);
                        ylong =
                            rowylong + ((dx1 - dstInfo.bounds.x1) * dydxlong);

                        /* Gft IntArgbPrf pixfl dbtb from sourdf */
                        (*pHflpfrFund)(&srdInfo,
                                       rgb.dbtb, numpix,
                                       xlong, dxdxlong,
                                       ylong, dydxlong);

                        /* Intfrpolbtf rfsult pixfls if nffdfd */
                        if (pIntfrpFund) {
                            (*pIntfrpFund)(rgb.dbtb, numpix,
                                           FrbdtOfLong(xlong-LongOnfHblf),
                                           FrbdtOfLong(dxdxlong),
                                           FrbdtOfLong(ylong-LongOnfHblf),
                                           FrbdtOfLong(dydxlong));
                        }

                        /* Storf/Compositf intfrpolbtfd pixfls into dfst */
                        pDst = PtrCoord(dstInfo.rbsBbsf,
                                        dx1, dstInfo.pixflStridf,
                                        dy1, dstInfo.sdbnStridf);
                        (*pMbskBlitPrim->funds.mbskblit)(pDst, rgb.dbtb,
                                                         0, 0, 0,
                                                         numpix, 1,
                                                         &dstInfo, &srdInfo,
                                                         pMbskBlitPrim,
                                                         &dompInfo);

                        /* Indrfmfnt to nfxt bufffr worti of input pixfls */
                        dx1 += mbxlinfpix;
                    }

                    /* Indrfmfnt to nfxt sdbnlinf */
                    rowxlong += dxdylong;
                    rowylong += dydylong;
                    dy1++;
                }
            }
            Rfgion_EndItfrbtion(fnv, &dlipInfo);
        }
        SurfbdfDbtb_InvokfRflfbsf(fnv, dstOps, &dstInfo);
        SurfbdfDbtb_InvokfRflfbsf(fnv, srdOps, &srdInfo);
    } flsf {
        pEdgfs[0] = pEdgfs[1] = 0;
    }

    if (!JNU_IsNull(fnv, fdgfArrby)) {
        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, fdgfArrby, pEdgfs, 0);
    } flsf if (pEdgfs != fdgfbuf) {
        frff(pEdgfs);
    }
    SurfbdfDbtb_InvokfUnlodk(fnv, dstOps, &dstInfo);
    SurfbdfDbtb_InvokfUnlodk(fnv, srdOps, &srdInfo);
}

stbtid void
Trbnsform_SbffHflpfr(JNIEnv *fnv,
                     SurfbdfDbtbOps *srdOps,
                     SurfbdfDbtbOps *dstOps,
                     SurfbdfDbtbRbsInfo *pSrdInfo,
                     SurfbdfDbtbRbsInfo *pDstInfo,
                     NbtivfPrimitivf *pMbskBlitPrim,
                     CompositfInfo *pCompInfo,
                     TrbnsformHflpfrFund *pHflpfrFund,
                     TrbnsformIntfrpFund *pIntfrpFund,
                     RfgionDbtb *pClipInfo, TrbnsformInfo *pItxInfo,
                     jint *pDbtb, jint *pEdgfs,
                     jint dxoff, jint dyoff, jint sw, jint si)
{
    SurfbdfDbtbBounds spbn;
    jint dx1, dx2;
    jint dy1, dy2;
    jint i, iy;

    dy1 = pDstInfo->bounds.y1;
    dy2 = pDstInfo->bounds.y2;
    dx1 = pDstInfo->bounds.x1;
    dx2 = pDstInfo->bounds.x2;
    pEdgfs[0] = dy1;
    pEdgfs[1] = dy2;
    for (iy = dy1; iy < dy2; iy++) {
        jint i = (iy - dy1) * 2;
        /* row spbns brf sft to mbx,min until wf find b pixfl in rbngf bflow */
        pEdgfs[i + 2] = dx2;
        pEdgfs[i + 3] = dx1;
    }

    Rfgion_StbrtItfrbtion(fnv, pClipInfo);
    wiilf (Rfgion_NfxtItfrbtion(pClipInfo, &spbn)) {
        dy1 = spbn.y1;
        dy2 = spbn.y2;
        wiilf (dy1 < dy2) {
            dx1 = spbn.x1;
            dx2 = spbn.x2;
            i = (dy1 - pDstInfo->bounds.y1) * 2;
            wiilf (dx1 < dx2) {
                jdoublf x, y;
                jlong xlong, ylong;

                x = dxoff + dx1 + 0.5;
                y = dyoff + dy1 + 0.5;
                Trbnsform_trbnsform(pItxInfo, &x, &y);
                xlong = DblToLong(x);
                ylong = DblToLong(y);

                /* Prodfss only pixfls witi dfntfrs in bounds
                 * Tfst doublf vblufs to bvoid ovfrflow in donvfrsion
                 * to long vblufs bnd tifn blso tfst tif long vblufs
                 * in dbsf tify roundfd up bnd out of bounds during
                 * tif donvfrsion.
                 */
                if (x >= 0 && y >= 0 && x < sw && y < si &&
                    WiolfOfLong(xlong) < sw &&
                    WiolfOfLong(ylong) < si)
                {
                    void *pDst;

                    if (pEdgfs[i + 2] > dx1) {
                        pEdgfs[i + 2] = dx1;
                    }
                    if (pEdgfs[i + 3] <= dx1) {
                        pEdgfs[i + 3] = dx1 + 1;
                    }

                    /* Gft IntArgbPrf pixfl dbtb from sourdf */
                    (*pHflpfrFund)(pSrdInfo,
                                   pDbtb, 1,
                                   xlong, 0,
                                   ylong, 0);

                    /* Intfrpolbtf rfsult pixfls if nffdfd */
                    if (pIntfrpFund) {
                        (*pIntfrpFund)(pDbtb, 1,
                                       FrbdtOfLong(xlong-LongOnfHblf), 0,
                                       FrbdtOfLong(ylong-LongOnfHblf), 0);
                    }

                    /* Storf/Compositf intfrpolbtfd pixfls into dfst */
                    pDst = PtrCoord(pDstInfo->rbsBbsf,
                                    dx1, pDstInfo->pixflStridf,
                                    dy1, pDstInfo->sdbnStridf);
                    (*pMbskBlitPrim->funds.mbskblit)(pDst, pDbtb,
                                                     0, 0, 0,
                                                     1, 1,
                                                     pDstInfo, pSrdInfo,
                                                     pMbskBlitPrim,
                                                     pCompInfo);
                }

                /* Indrfmfnt to nfxt input pixfl */
                dx1++;
            }

            /* Indrfmfnt to nfxt sdbnlinf */
            dy1++;
        }
    }
    Rfgion_EndItfrbtion(fnv, pClipInfo);
}

#dffinf BL_INTERP_V1_to_V2_by_F(v1, v2, f) \
    (((v1)<<8) + ((v2)-(v1))*(f))

#dffinf BL_ACCUM(domp) \
    do { \
        jint d1 = ((jubytf *) pRGB)[domp]; \
        jint d2 = ((jubytf *) pRGB)[domp+4]; \
        jint dR = BL_INTERP_V1_to_V2_by_F(d1, d2, xfbdtor); \
        d1 = ((jubytf *) pRGB)[domp+8]; \
        d2 = ((jubytf *) pRGB)[domp+12]; \
        d2 = BL_INTERP_V1_to_V2_by_F(d1, d2, xfbdtor); \
        dR = BL_INTERP_V1_to_V2_by_F(dR, d2, yfbdtor); \
        ((jubytf *)pRfs)[domp] = (jubytf) ((dR + (1<<15)) >> 16); \
    } wiilf (0)

stbtid void
BilinfbrIntfrp(jint *pRGB, jint numpix,
               jint xfrbdt, jint dxfrbdt,
               jint yfrbdt, jint dyfrbdt)
{
    jint j;
    jint *pRfs = pRGB;

    for (j = 0; j < numpix; j++) {
        jint xfbdtor;
        jint yfbdtor;
        xfbdtor = URSiift(xfrbdt, 32-8);
        yfbdtor = URSiift(yfrbdt, 32-8);
        BL_ACCUM(0);
        BL_ACCUM(1);
        BL_ACCUM(2);
        BL_ACCUM(3);
        pRfs++;
        pRGB += 4;
        xfrbdt += dxfrbdt;
        yfrbdt += dyfrbdt;
    }
}

#dffinf SAT(vbl, mbx) \
    do { \
        vbl &= ~(vbl >> 31);  /* nfgbtivfs bfdomf 0 */ \
        vbl -= mbx;           /* only ovfrflows brf now positivf */ \
        vbl &= (vbl >> 31);   /* positivfs bfdomf 0 */ \
        vbl += mbx;           /* rbngf is now [0 -> mbx] */ \
    } wiilf (0)

#ifdff __spbrd
/* For spbrd, flobting point multiplifs brf fbstfr tibn intfgfr */
#dffinf BICUBIC_USE_DBL_LUT
#flsf
/* For x86, intfgfr multiplifs brf fbstfr tibn flobting point */
/* Notf tibt on x86 Linux tif dioidf of bfst blgoritim vbrifs
 * dfpfnding on tif dompilfr optimizbtion bnd tif prodfssor typf.
 * Currfntly, tif sun/bwt x86 Linux builds brf not optimizfd so
 * bll tif vbribtions produdf mfdiodrf pfrformbndf.
 * For now wf will usf tif dioidf tibt works bfst for tif Windows
 * build until tif (lbdk of) optimizbtion issufs on Linux brf rfsolvfd.
 */
#dffinf BICUBIC_USE_INT_MATH
#fndif

#ifdff BICUBIC_USE_DBL_CAST

#dffinf BC_DblToCofff(v)        (v)
#dffinf BC_COEFF_ONE            1.0
#dffinf BC_TYPE                 jdoublf
#dffinf BC_V_HALF               0.5
#dffinf BC_CompToV(v)           ((jdoublf) (v))
#dffinf BC_STORE_COMPS(pRfs) \
    do { \
        jint b = (jint) bddumA; \
        jint r = (jint) bddumR; \
        jint g = (jint) bddumG; \
        jint b = (jint) bddumB; \
        SAT(b, 255); \
        SAT(r, b); \
        SAT(g, b); \
        SAT(b, b); \
        *pRfs = ((b << 24) | (r << 16) | (g <<  8) | (b)); \
    } wiilf (0)

#fndif /* BICUBIC_USE_DBL_CAST */

#ifdff BICUBIC_USE_DBL_LUT

#dffinf ItoD1(v)    ((jdoublf) (v))
#dffinf ItoD4(v)    ItoD1(v),  ItoD1(v+1),   ItoD1(v+2),   ItoD1(v+3)
#dffinf ItoD16(v)   ItoD4(v),  ItoD4(v+4),   ItoD4(v+8),   ItoD4(v+12)
#dffinf ItoD64(v)   ItoD16(v), ItoD16(v+16), ItoD16(v+32), ItoD16(v+48)

stbtid jdoublf ItoD_tbblf[] = {
    ItoD64(0), ItoD64(64), ItoD64(128), ItoD64(192)
};

#dffinf BC_DblToCofff(v)        (v)
#dffinf BC_COEFF_ONE            1.0
#dffinf BC_TYPE                 jdoublf
#dffinf BC_V_HALF               0.5
#dffinf BC_CompToV(v)           ItoD_tbblf[v]
#dffinf BC_STORE_COMPS(pRfs) \
    do { \
        jint b = (jint) bddumA; \
        jint r = (jint) bddumR; \
        jint g = (jint) bddumG; \
        jint b = (jint) bddumB; \
        SAT(b, 255); \
        SAT(r, b); \
        SAT(g, b); \
        SAT(b, b); \
        *pRfs = ((b << 24) | (r << 16) | (g <<  8) | (b)); \
    } wiilf (0)

#fndif /* BICUBIC_USE_DBL_LUT */

#ifdff BICUBIC_USE_INT_MATH

#dffinf BC_DblToCofff(v)        ((jint) ((v) * 256))
#dffinf BC_COEFF_ONE            256
#dffinf BC_TYPE                 jint
#dffinf BC_V_HALF               (1 << 15)
#dffinf BC_CompToV(v)           ((jint) v)
#dffinf BC_STORE_COMPS(pRfs) \
    do { \
        bddumA >>= 16; \
        bddumR >>= 16; \
        bddumG >>= 16; \
        bddumB >>= 16; \
        SAT(bddumA, 255); \
        SAT(bddumR, bddumA); \
        SAT(bddumG, bddumA); \
        SAT(bddumB, bddumA); \
        *pRfs = ((bddumA << 24) | (bddumR << 16) | (bddumG << 8) | (bddumB)); \
    } wiilf (0)

#fndif /* BICUBIC_USE_INT_MATH */

#dffinf BC_ACCUM(indfx, ydindfx, xdindfx) \
    do { \
        BC_TYPE fbdtor = bidubid_dofff[xdindfx] * bidubid_dofff[ydindfx]; \
        int rgb; \
        rgb = pRGB[indfx]; \
        bddumB += BC_CompToV((rgb >>  0) & 0xff) * fbdtor; \
        bddumG += BC_CompToV((rgb >>  8) & 0xff) * fbdtor; \
        bddumR += BC_CompToV((rgb >> 16) & 0xff) * fbdtor; \
        bddumA += BC_CompToV((rgb >> 24) & 0xff) * fbdtor; \
    } wiilf (0)

stbtid BC_TYPE bidubid_dofff[513];
stbtid jboolfbn bidubidtbblfinitfd;

stbtid void
init_bidubid_tbblf(jdoublf A)
{
    /*
     * Tif following formulbs brf dfsignfd to givf smooti
     * rfsults wifn 'A' is -0.5 or -1.0.
     */
    int i;
    for (i = 0; i < 256; i++) {
        /* r(x) = (A + 2)|x|^3 - (A + 3)|x|^2 + 1 , 0 <= |x| < 1 */
        jdoublf x = i / 256.0;
        x = ((A+2)*x - (A+3))*x*x + 1;
        bidubid_dofff[i] = BC_DblToCofff(x);
    }

    for (; i < 384; i++) {
        /* r(x) = A|x|^3 - 5A|x|^2 + 8A|x| - 4A , 1 <= |x| < 2 */
        jdoublf x = i / 256.0;
        x = ((A*x - 5*A)*x + 8*A)*x - 4*A;
        bidubid_dofff[i] = BC_DblToCofff(x);
    }

    bidubid_dofff[384] = (BC_COEFF_ONE - bidubid_dofff[128]*2) / 2;

    for (i++; i <= 512; i++) {
        bidubid_dofff[i] = BC_COEFF_ONE - (bidubid_dofff[512-i] +
                                           bidubid_dofff[i-256] +
                                           bidubid_dofff[768-i]);
    }

    bidubidtbblfinitfd = JNI_TRUE;
}

stbtid void
BidubidIntfrp(jint *pRGB, jint numpix,
              jint xfrbdt, jint dxfrbdt,
              jint yfrbdt, jint dyfrbdt)
{
    jint i;
    jint *pRfs = pRGB;

    if (!bidubidtbblfinitfd) {
        init_bidubid_tbblf(-0.5);
    }

    for (i = 0; i < numpix; i++) {
        BC_TYPE bddumA, bddumR, bddumG, bddumB;
        jint xfbdtor, yfbdtor;

        xfbdtor = URSiift(xfrbdt, 32-8);
        yfbdtor = URSiift(yfrbdt, 32-8);
        bddumA = bddumR = bddumG = bddumB = BC_V_HALF;
        BC_ACCUM(0, yfbdtor+256, xfbdtor+256);
        BC_ACCUM(1, yfbdtor+256, xfbdtor+  0);
        BC_ACCUM(2, yfbdtor+256, 256-xfbdtor);
        BC_ACCUM(3, yfbdtor+256, 512-xfbdtor);
        BC_ACCUM(4, yfbdtor+  0, xfbdtor+256);
        BC_ACCUM(5, yfbdtor+  0, xfbdtor+  0);
        BC_ACCUM(6, yfbdtor+  0, 256-xfbdtor);
        BC_ACCUM(7, yfbdtor+  0, 512-xfbdtor);
        BC_ACCUM(8, 256-yfbdtor, xfbdtor+256);
        BC_ACCUM(9, 256-yfbdtor, xfbdtor+  0);
        BC_ACCUM(10, 256-yfbdtor, 256-xfbdtor);
        BC_ACCUM(11, 256-yfbdtor, 512-xfbdtor);
        BC_ACCUM(12, 512-yfbdtor, xfbdtor+256);
        BC_ACCUM(13, 512-yfbdtor, xfbdtor+  0);
        BC_ACCUM(14, 512-yfbdtor, 256-xfbdtor);
        BC_ACCUM(15, 512-yfbdtor, 512-xfbdtor);
        BC_STORE_COMPS(pRfs);
        pRfs++;
        pRGB += 16;
        xfrbdt += dxfrbdt;
        yfrbdt += dyfrbdt;
    }
}

#ifdff MAKE_STUBS

stbtid void
BilinfbrIntfrpStub(jint *pRGBbbsf, jint numpix,
                   jint xfrbdt, jint dxfrbdt,
                   jint yfrbdt, jint dyfrbdt)
{
    jint *pRGB = pRGBbbsf;
    wiilf (--numpix >= 0) {
        *pRGBbbsf = *pRGB;
        pRGBbbsf += 1;
        pRGB += 4;
    }
}

stbtid void
BidubidIntfrpStub(jint *pRGBbbsf, jint numpix,
                  jint xfrbdt, jint dxfrbdt,
                  jint yfrbdt, jint dyfrbdt)
{
    jint *pRGB = pRGBbbsf+5;
    wiilf (--numpix >= 0) {
        *pRGBbbsf = *pRGB;
        pRGBbbsf += 1;
        pRGB += 16;
    }
}

#fndif /* MAKE_STUBS */
