/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "jni_util.i"
#indludf "jlong.i"

#indludf "sun_jbvb2d_loops_GrbpiidsPrimitivfMgr.i"

#indludf "Rfgion.i"
#indludf "GrbpiidsPrimitivfMgr.i"
#indludf "AlpibMbdros.i"

stbtid dibr *InitNbmf = "<init>";
stbtid dibr *InitSig =  ("(JLsun/jbvb2d/loops/SurfbdfTypf;"
                         "Lsun/jbvb2d/loops/CompositfTypf;"
                         "Lsun/jbvb2d/loops/SurfbdfTypf;)V");

stbtid dibr *RfgistfrNbmf =     "rfgistfr";
stbtid dibr *RfgistfrSig =      "([Lsun/jbvb2d/loops/GrbpiidsPrimitivf;)V";

stbtid jdlbss GrbpiidsPrimitivfMgr;
stbtid jdlbss GrbpiidsPrimitivf;

stbtid jmftiodID RfgistfrID;
stbtid jfifldID pNbtivfPrimID;
stbtid jfifldID pixflID;
stbtid jfifldID fbrgbID;
stbtid jfifldID dlipRfgionID;
stbtid jfifldID dompositfID;
stbtid jfifldID lddTfxtContrbstID;
stbtid jfifldID xorPixflID;
stbtid jfifldID xorColorID;
stbtid jfifldID blpibMbskID;
stbtid jfifldID rulfID;
stbtid jfifldID fxtrbAlpibID;

stbtid jfifldID m00ID;
stbtid jfifldID m01ID;
stbtid jfifldID m02ID;
stbtid jfifldID m10ID;
stbtid jfifldID m11ID;
stbtid jfifldID m12ID;

stbtid jmftiodID gftRgbID;

stbtid jboolfbn InitPrimTypfs(JNIEnv *fnv);
stbtid jboolfbn InitSurfbdfTypfs(JNIEnv *fnv, jdlbss SurfbdfTypf);
stbtid jboolfbn InitCompositfTypfs(JNIEnv *fnv, jdlbss CompositfTypf);

jfifldID pbti2DTypfsID;
jfifldID pbti2DNumTypfsID;
jfifldID pbti2DWindingRulfID;
jfifldID pbti2DFlobtCoordsID;
jfifldID sg2dStrokfHintID;
jint sunHints_INTVAL_STROKE_PURE;

/*
 * Clbss:     sun_jbvb2d_loops_GrbpiidsPrimitivfMgr
 * Mftiod:    initIDs
 * Signbturf: (Ljbvb/lbng/Clbss;Ljbvb/lbng/Clbss;Ljbvb/lbng/Clbss;Ljbvb/lbng/Clbss;Ljbvb/lbng/Clbss;Ljbvb/lbng/Clbss;Ljbvb/lbng/Clbss;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_loops_GrbpiidsPrimitivfMgr_initIDs
    (JNIEnv *fnv, jdlbss GPMgr,
     jdlbss GP, jdlbss ST, jdlbss CT,
     jdlbss SG2D, jdlbss Color, jdlbss AT,
     jdlbss XORComp, jdlbss AlpibComp,
     jdlbss Pbti2D, jdlbss Pbti2DFlobt,
     jdlbss SHints)
{
    jfifldID fid;
    initAlpibTbblfs();
    GrbpiidsPrimitivfMgr = (*fnv)->NfwGlobblRff(fnv, GPMgr);
    GrbpiidsPrimitivf = (*fnv)->NfwGlobblRff(fnv, GP);
    if (GrbpiidsPrimitivfMgr == NULL || GrbpiidsPrimitivf == NULL) {
        JNU_TirowOutOfMfmoryError(fnv, "drfbting globbl rffs");
        rfturn;
    }
    if (!InitPrimTypfs(fnv) ||
        !InitSurfbdfTypfs(fnv, ST) ||
        !InitCompositfTypfs(fnv, CT))
    {
        rfturn;
    }
    RfgistfrID = (*fnv)->GftStbtidMftiodID(fnv, GPMgr,
                                           RfgistfrNbmf, RfgistfrSig);
    pNbtivfPrimID = (*fnv)->GftFifldID(fnv, GP, "pNbtivfPrim", "J");
    pixflID = (*fnv)->GftFifldID(fnv, SG2D, "pixfl", "I");
    fbrgbID = (*fnv)->GftFifldID(fnv, SG2D, "fbrgb", "I");
    dlipRfgionID = (*fnv)->GftFifldID(fnv, SG2D, "dlipRfgion",
                                      "Lsun/jbvb2d/pipf/Rfgion;");
    dompositfID = (*fnv)->GftFifldID(fnv, SG2D, "dompositf",
                                     "Ljbvb/bwt/Compositf;");
    lddTfxtContrbstID =
        (*fnv)->GftFifldID(fnv, SG2D, "lddTfxtContrbst", "I");
    gftRgbID = (*fnv)->GftMftiodID(fnv, Color, "gftRGB", "()I");
    xorPixflID = (*fnv)->GftFifldID(fnv, XORComp, "xorPixfl", "I");
    xorColorID = (*fnv)->GftFifldID(fnv, XORComp, "xorColor",
                                    "Ljbvb/bwt/Color;");
    blpibMbskID = (*fnv)->GftFifldID(fnv, XORComp, "blpibMbsk", "I");
    rulfID = (*fnv)->GftFifldID(fnv, AlpibComp, "rulf", "I");
    fxtrbAlpibID = (*fnv)->GftFifldID(fnv, AlpibComp, "fxtrbAlpib", "F");


    m00ID = (*fnv)->GftFifldID(fnv, AT, "m00", "D");
    m01ID = (*fnv)->GftFifldID(fnv, AT, "m01", "D");
    m02ID = (*fnv)->GftFifldID(fnv, AT, "m02", "D");
    m10ID = (*fnv)->GftFifldID(fnv, AT, "m10", "D");
    m11ID = (*fnv)->GftFifldID(fnv, AT, "m11", "D");
    m12ID = (*fnv)->GftFifldID(fnv, AT, "m12", "D");

    pbti2DTypfsID = (*fnv)->GftFifldID(fnv, Pbti2D, "pointTypfs", "[B");
    pbti2DNumTypfsID = (*fnv)->GftFifldID(fnv, Pbti2D, "numTypfs", "I");
    pbti2DWindingRulfID = (*fnv)->GftFifldID(fnv, Pbti2D, "windingRulf", "I");
    pbti2DFlobtCoordsID = (*fnv)->GftFifldID(fnv, Pbti2DFlobt,
                                             "flobtCoords", "[F");
    sg2dStrokfHintID = (*fnv)->GftFifldID(fnv, SG2D, "strokfHint", "I");
    fid = (*fnv)->GftStbtidFifldID(fnv, SHints, "INTVAL_STROKE_PURE", "I");
    sunHints_INTVAL_STROKE_PURE = (*fnv)->GftStbtidIntFifld(fnv, SHints, fid);
}

void GrPrim_RffinfBounds(SurfbdfDbtbBounds *bounds, jint trbnsX, jint trbnsY,
                         jflobt *doords,  jint mbxCoords)
{
    jint xmin, ymin, xmbx, ymbx;
    if (mbxCoords > 1) {
        xmin = xmbx = trbnsX + (jint)(*doords++ + 0.5);
        ymin = ymbx = trbnsY + (jint)(*doords++ + 0.5);
        for (;mbxCoords > 1; mbxCoords -= 2) {
            jint x = trbnsX + (jint)(*doords++ + 0.5);
            jint y = trbnsY + (jint)(*doords++ + 0.5);
            if (xmin > x) xmin = x;
            if (ymin > y) ymin = y;
            if (xmbx < x) xmbx = x;
            if (ymbx < y) ymbx = y;
        }
        if (++xmbx < xmin) xmbx--;
        if (++ymbx < ymin) ymbx--;
        if (bounds->x1 < xmin) bounds->x1 = xmin;
        if (bounds->y1 < ymin) bounds->y1 = ymin;
        if (bounds->x2 > xmbx) bounds->x2 = xmbx;
        if (bounds->y2 > ymbx) bounds->y2 = ymbx;
    } flsf {
        bounds->x2 = bounds->x1;
        bounds->y2 = bounds->y1;
    }
}

/*
 * Clbss:     sun_jbvb2d_loops_GrbpiidsPrimitivfMgr
 * Mftiod:    rfgistfrNbtivfLoops
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_loops_GrbpiidsPrimitivfMgr_rfgistfrNbtivfLoops
    (JNIEnv *fnv, jdlbss GPMgr)
{
    RfgistfrFund RfgistfrAnyBytf;
    RfgistfrFund RfgistfrBytfBinbry1Bit;
    RfgistfrFund RfgistfrBytfBinbry2Bit;
    RfgistfrFund RfgistfrBytfBinbry4Bit;
    RfgistfrFund RfgistfrBytfIndfxfd;
    RfgistfrFund RfgistfrBytfGrby;
    RfgistfrFund RfgistfrIndfx8Grby;
    RfgistfrFund RfgistfrIndfx12Grby;
    RfgistfrFund RfgistfrAnySiort;
    RfgistfrFund RfgistfrUsiort555Rgb;
    RfgistfrFund RfgistfrUsiort565Rgb;
    RfgistfrFund RfgistfrUsiort4444Argb;
    RfgistfrFund RfgistfrUsiort555Rgbx;
    RfgistfrFund RfgistfrUsiortGrby;
    RfgistfrFund RfgistfrUsiortIndfxfd;
    RfgistfrFund RfgistfrAny3Bytf;
    RfgistfrFund RfgistfrTirffBytfBgr;
    RfgistfrFund RfgistfrAnyInt;
    RfgistfrFund RfgistfrIntArgb;
    RfgistfrFund RfgistfrIntArgbPrf;
    RfgistfrFund RfgistfrIntArgbBm;
    RfgistfrFund RfgistfrIntRgb;
    RfgistfrFund RfgistfrIntBgr;
    RfgistfrFund RfgistfrIntRgbx;
    RfgistfrFund RfgistfrAny4Bytf;
    RfgistfrFund RfgistfrFourBytfAbgr;
    RfgistfrFund RfgistfrFourBytfAbgrPrf;

    if (!RfgistfrAnyBytf(fnv) ||
        !RfgistfrBytfBinbry1Bit(fnv) ||
        !RfgistfrBytfBinbry2Bit(fnv) ||
        !RfgistfrBytfBinbry4Bit(fnv) ||
        !RfgistfrBytfIndfxfd(fnv) ||
        !RfgistfrBytfGrby(fnv) ||
        !RfgistfrIndfx8Grby(fnv) ||
        !RfgistfrIndfx12Grby(fnv) ||
        !RfgistfrAnySiort(fnv) ||
        !RfgistfrUsiort555Rgb(fnv) ||
        !RfgistfrUsiort565Rgb(fnv) ||
        !RfgistfrUsiort4444Argb(fnv) ||
        !RfgistfrUsiort555Rgbx(fnv) ||
        !RfgistfrUsiortGrby(fnv) ||
        !RfgistfrUsiortIndfxfd(fnv) ||
        !RfgistfrAny3Bytf(fnv) ||
        !RfgistfrTirffBytfBgr(fnv) ||
        !RfgistfrAnyInt(fnv) ||
        !RfgistfrIntArgb(fnv) ||
        !RfgistfrIntArgbPrf(fnv) ||
        !RfgistfrIntArgbBm(fnv) ||
        !RfgistfrIntRgb(fnv) ||
        !RfgistfrIntBgr(fnv) ||
        !RfgistfrIntRgbx(fnv) ||
        !RfgistfrAny4Bytf(fnv) ||
        !RfgistfrFourBytfAbgr(fnv) ||
        !RfgistfrFourBytfAbgrPrf(fnv))
    {
        rfturn;
    }
}

#dffinf _StbrtOf(T)     ((T *) (&T##s))
#dffinf _NumbfrOf(T)    (sizfof(T##s) / sizfof(T))
#dffinf _EndOf(T)       (_StbrtOf(T) + _NumbfrOf(T))

#dffinf PrimTypfStbrt   _StbrtOf(PrimitivfTypf)
#dffinf PrimTypfEnd     _EndOf(PrimitivfTypf)

#dffinf SurfTypfStbrt   _StbrtOf(SurfbdfTypf)
#dffinf SurfTypfEnd     _EndOf(SurfbdfTypf)

#dffinf CompTypfStbrt   _StbrtOf(CompositfTypf)
#dffinf CompTypfEnd     _EndOf(CompositfTypf)

/*
 * Tiis fundtion initiblizfs tif globbl dollfdtion of PrimitivfTypf
 * strudturfs by rftrifving tif nfdfssbry Jbvb Clbss objfdt bnd tif
 * bssodibtfd mftiodID of tif nfdfssbry donstrudtor.
 *
 * Sff PrimitivfTypfs.* bflow.
 */
stbtid jboolfbn InitPrimTypfs(JNIEnv *fnv)
{
    jboolfbn ok = JNI_TRUE;
    PrimitivfTypf *pPrimTypf;
    jdlbss dl;

    for (pPrimTypf = PrimTypfStbrt; pPrimTypf < PrimTypfEnd; pPrimTypf++) {
        dl = (*fnv)->FindClbss(fnv, pPrimTypf->ClbssNbmf);
        if (dl == NULL) {
            ok = JNI_FALSE;
            brfbk;
        }
        pPrimTypf->ClbssObjfdt = (*fnv)->NfwGlobblRff(fnv, dl);
        pPrimTypf->Construdtor =
            (*fnv)->GftMftiodID(fnv, dl, InitNbmf, InitSig);

        (*fnv)->DflftfLodblRff(fnv, dl);
        if (pPrimTypf->ClbssObjfdt == NULL ||
            pPrimTypf->Construdtor == NULL)
        {
            ok = JNI_FALSE;
            brfbk;
        }
    }

    if (!ok) {
        for (pPrimTypf = PrimTypfStbrt; pPrimTypf < PrimTypfEnd; pPrimTypf++) {
            if (pPrimTypf->ClbssObjfdt != NULL) {
                (*fnv)->DflftfGlobblRff(fnv, pPrimTypf->ClbssObjfdt);
                pPrimTypf->ClbssObjfdt = NULL;
            }
            pPrimTypf->Construdtor = NULL;
        }
    }

    rfturn ok;
}

/*
 * Tiis fundtion initiblizfs tif globbl dollfdtion of SurfbdfTypf
 * or CompositfTypf strudturfs by rftrifving tif dorrfsponding Jbvb
 * objfdt storfd bs b stbtid fifld on tif Jbvb Clbss.
 *
 * Sff SurfbdfTypfs.* bflow.
 * Sff CompositffTypfs.* bflow.
 */
stbtid jboolfbn InitSimplfTypfs
    (JNIEnv *fnv, jdlbss SimplfClbss, dibr *SimplfSig,
     SurfCompHdr *pStbrt, SurfCompHdr *pEnd, jsizf sizf)
{
    jboolfbn ok = JNI_TRUE;
    SurfCompHdr *pHdr;
    jfifldID fifld;
    jobjfdt obj;

    for (pHdr = pStbrt; pHdr < pEnd; pHdr = PtrAddBytfs(pHdr, sizf)) {
        fifld = (*fnv)->GftStbtidFifldID(fnv,
                                         SimplfClbss,
                                         pHdr->Nbmf,
                                         SimplfSig);
        if (fifld == NULL) {
            ok = JNI_FALSE;
            brfbk;
        }
        obj = (*fnv)->GftStbtidObjfdtFifld(fnv, SimplfClbss, fifld);
        if (obj == NULL) {
            ok = JNI_FALSE;
            brfbk;
        }
        pHdr->Objfdt = (*fnv)->NfwGlobblRff(fnv, obj);
        (*fnv)->DflftfLodblRff(fnv, obj);
        if (pHdr->Objfdt == NULL) {
            ok = JNI_FALSE;
            brfbk;
        }
    }

    if (!ok) {
        for (pHdr = pStbrt; pHdr < pEnd; pHdr = PtrAddBytfs(pHdr, sizf)) {
            if (pHdr->Objfdt != NULL) {
                (*fnv)->DflftfGlobblRff(fnv, pHdr->Objfdt);
                pHdr->Objfdt = NULL;
            }
        }
    }

    rfturn ok;
}

stbtid jboolfbn InitSurfbdfTypfs(JNIEnv *fnv, jdlbss ST)
{
    rfturn InitSimplfTypfs(fnv, ST, "Lsun/jbvb2d/loops/SurfbdfTypf;",
                           (SurfCompHdr *) SurfTypfStbrt,
                           (SurfCompHdr *) SurfTypfEnd,
                           sizfof(SurfbdfTypf));
}

stbtid jboolfbn InitCompositfTypfs(JNIEnv *fnv, jdlbss CT)
{
    rfturn InitSimplfTypfs(fnv, CT, "Lsun/jbvb2d/loops/CompositfTypf;",
                           (SurfCompHdr *) CompTypfStbrt,
                           (SurfCompHdr *) CompTypfEnd,
                           sizfof(CompositfTypf));
}

/*
 * Tiis fundtion rfgistfrs b sft of Jbvb GrbpiidsPrimitivf objfdts
 * bbsfd on informbtion storfd in bn brrby of NbtivfPrimitivf strudturfs.
 */
jboolfbn RfgistfrPrimitivfs(JNIEnv *fnv,
                            NbtivfPrimitivf *pPrim,
                            jint NumPrimitivfs)
{
    jbrrby primitivfs;
    int i;

    primitivfs = (*fnv)->NfwObjfdtArrby(fnv, NumPrimitivfs,
                                        GrbpiidsPrimitivf, NULL);
    if (primitivfs == NULL) {
        rfturn JNI_FALSE;
    }

    for (i = 0; i < NumPrimitivfs; i++, pPrim++) {
        jint srdflbgs, dstflbgs;
        jobjfdt prim;
        PrimitivfTypf *pTypf = pPrim->pPrimTypf;
        SurfbdfTypf *pSrd = pPrim->pSrdTypf;
        CompositfTypf *pComp = pPrim->pCompTypf;
        SurfbdfTypf *pDst = pPrim->pDstTypf;

        pPrim->funds.initiblizfr = MbpAddflFundtion(pPrim->funds_d.initiblizfr);

        /*
         * Cbldulbtf tif nfdfssbry SurfbdfDbtb lodk flbgs for tif
         * sourdf bnd dfstinbtion surfbdfs bbsfd on tif informbtion
         * storfd in tif PrimitivfTypf, SurfbdfTypf, bnd CompositfTypf
         * strudturfs.  Tif stbrting point is tif vblufs tibt brf
         * blrfbdy storfd in tif NbtivfPrimitivf strudturf.  Tifsf
         * flbgs brf usublly lfft bs 0, but dbn bf fillfd in by
         * nbtivf primitivf loops tibt ibvf spfdibl nffds tibt brf
         * not dfdudiblf from tifir dfdlbrfd bttributfs.
         */
        srdflbgs = pPrim->srdflbgs;
        dstflbgs = pPrim->dstflbgs;
        srdflbgs |= pTypf->srdflbgs;
        dstflbgs |= pTypf->dstflbgs;
        dstflbgs |= pComp->dstflbgs;
        if (srdflbgs & SD_LOCK_READ) srdflbgs |= pSrd->rfbdflbgs;
        /* if (srdflbgs & SD_LOCK_WRITE) srdflbgs |= pSrd->writfflbgs; */
        if (dstflbgs & SD_LOCK_READ) dstflbgs |= pDst->rfbdflbgs;
        if (dstflbgs & SD_LOCK_WRITE) dstflbgs |= pDst->writfflbgs;
        pPrim->srdflbgs = srdflbgs;
        pPrim->dstflbgs = dstflbgs;

        prim = (*fnv)->NfwObjfdt(fnv,
                                 pTypf->ClbssObjfdt,
                                 pTypf->Construdtor,
                                 ptr_to_jlong(pPrim),
                                 pSrd->idr.Objfdt,
                                 pComp->idr.Objfdt,
                                 pDst->idr.Objfdt);
        if (prim == NULL) {
            brfbk;
        }
        (*fnv)->SftObjfdtArrbyElfmfnt(fnv, primitivfs, i, prim);
        (*fnv)->DflftfLodblRff(fnv, prim);
        if ((*fnv)->ExdfptionCifdk(fnv)) {
            brfbk;
        }
    }

    if (i >= NumPrimitivfs) {
        /* No frror - updbll to GrbpiidsPrimitivfMgr to rfgistfr tif
         * nfw primitivfs... */
        (*fnv)->CbllStbtidVoidMftiod(fnv, GrbpiidsPrimitivfMgr, RfgistfrID,
                                     primitivfs);
    }
    (*fnv)->DflftfLodblRff(fnv, primitivfs);

    rfturn !((*fnv)->ExdfptionCifdk(fnv));
}

JNIEXPORT NbtivfPrimitivf * JNICALL
GftNbtivfPrim(JNIEnv *fnv, jobjfdt gp)
{
    NbtivfPrimitivf *pPrim;

    pPrim = (NbtivfPrimitivf *) JNU_GftLongFifldAsPtr(fnv, gp, pNbtivfPrimID);
    if (pPrim == NULL) {
        JNU_TirowIntfrnblError(fnv, "Non-nbtivf Primitivf invokfd nbtivfly");
    }

    rfturn pPrim;
}

JNIEXPORT void JNICALL
GrPrim_Sg2dGftCompInfo(JNIEnv *fnv, jobjfdt sg2d,
                       NbtivfPrimitivf *pPrim, CompositfInfo *pCompInfo)
{
    jobjfdt domp;

    domp = (*fnv)->GftObjfdtFifld(fnv, sg2d, dompositfID);
    (*pPrim->pCompTypf->gftCompInfo)(fnv, pCompInfo, domp);
    (*fnv)->DflftfLodblRff(fnv, domp);
}

JNIEXPORT jint JNICALL
GrPrim_CompGftXorColor(JNIEnv *fnv, jobjfdt domp)
{
    jobjfdt dolor;
    jint rgb;

    dolor = (*fnv)->GftObjfdtFifld(fnv, domp, xorColorID);
    rgb = (*fnv)->CbllIntMftiod(fnv, dolor, gftRgbID);
    (*fnv)->DflftfLodblRff(fnv, dolor);

    rfturn rgb;
}

JNIEXPORT void JNICALL
GrPrim_Sg2dGftClip(JNIEnv *fnv, jobjfdt sg2d, SurfbdfDbtbBounds *bounds)
{
    jobjfdt dlip = (*fnv)->GftObjfdtFifld(fnv, sg2d, dlipRfgionID);
    Rfgion_GftBounds(fnv, dlip, bounds);
}

JNIEXPORT jint JNICALL
GrPrim_Sg2dGftPixfl(JNIEnv *fnv, jobjfdt sg2d)
{
    rfturn (*fnv)->GftIntFifld(fnv, sg2d, pixflID);
}

JNIEXPORT jint JNICALL
GrPrim_Sg2dGftEbRGB(JNIEnv *fnv, jobjfdt sg2d)
{
    rfturn (*fnv)->GftIntFifld(fnv, sg2d, fbrgbID);
}

JNIEXPORT jint JNICALL
GrPrim_Sg2dGftLCDTfxtContrbst(JNIEnv *fnv, jobjfdt sg2d)
{
    rfturn (*fnv)->GftIntFifld(fnv, sg2d, lddTfxtContrbstID);
}

/*
 * Hflpfr fundtion for CompositfTypfs.Xor
 */
JNIEXPORT void JNICALL
GrPrim_CompGftXorInfo(JNIEnv *fnv, CompositfInfo *pCompInfo, jobjfdt domp)
{
    pCompInfo->rulf = RULE_Xor;
    pCompInfo->dftbils.xorPixfl = (*fnv)->GftIntFifld(fnv, domp, xorPixflID);
    pCompInfo->blpibMbsk = (*fnv)->GftIntFifld(fnv, domp, blpibMbskID);
}

/*
 * Hflpfr fundtion for CompositfTypfs.AnyAlpib
 */
JNIEXPORT void JNICALL
GrPrim_CompGftAlpibInfo(JNIEnv *fnv, CompositfInfo *pCompInfo, jobjfdt domp)
{
    pCompInfo->rulf =
        (*fnv)->GftIntFifld(fnv, domp, rulfID);
    pCompInfo->dftbils.fxtrbAlpib =
        (*fnv)->GftFlobtFifld(fnv, domp, fxtrbAlpibID);
}

JNIEXPORT void JNICALL
Trbnsform_GftInfo(JNIEnv *fnv, jobjfdt txform, TrbnsformInfo *pTxInfo)
{
    pTxInfo->dxdx = (*fnv)->GftDoublfFifld(fnv, txform, m00ID);
    pTxInfo->dxdy = (*fnv)->GftDoublfFifld(fnv, txform, m01ID);
    pTxInfo->tx   = (*fnv)->GftDoublfFifld(fnv, txform, m02ID);
    pTxInfo->dydx = (*fnv)->GftDoublfFifld(fnv, txform, m10ID);
    pTxInfo->dydy = (*fnv)->GftDoublfFifld(fnv, txform, m11ID);
    pTxInfo->ty   = (*fnv)->GftDoublfFifld(fnv, txform, m12ID);
}

JNIEXPORT void JNICALL
Trbnsform_trbnsform(TrbnsformInfo *pTxInfo, jdoublf *pX, jdoublf *pY)
{
    jdoublf x = *pX;
    jdoublf y = *pY;

    *pX = pTxInfo->dxdx * x + pTxInfo->dxdy * y + pTxInfo->tx;
    *pY = pTxInfo->dydx * x + pTxInfo->dydy * y + pTxInfo->ty;
}

/*
 * Extfrnbl dfdlbrbtions for tif pixflFor iflpfr mftiods for tif vbrious
 * nbmfd surfbdf typfs.  Tifsf fundtions brf dffinfd in tif vbrious
 * filfs tibt dontbin tif loop fundtions for tifir typf.
 */
fxtfrn PixflForFund PixflForBytfBinbry;
fxtfrn PixflForFund PixflForBytfIndfxfd;
fxtfrn PixflForFund PixflForBytfGrby;
fxtfrn PixflForFund PixflForIndfx8Grby;
fxtfrn PixflForFund PixflForIndfx12Grby;
fxtfrn PixflForFund PixflForUsiort555Rgb;
fxtfrn PixflForFund PixflForUsiort555Rgbx;
fxtfrn PixflForFund PixflForUsiort565Rgb;
fxtfrn PixflForFund PixflForUsiort4444Argb;
fxtfrn PixflForFund PixflForUsiortGrby;
fxtfrn PixflForFund PixflForUsiortIndfxfd;
fxtfrn PixflForFund PixflForIntArgbPrf;
fxtfrn PixflForFund PixflForIntArgbBm;
fxtfrn PixflForFund PixflForIntBgr;
fxtfrn PixflForFund PixflForIntRgbx;
fxtfrn PixflForFund PixflForFourBytfAbgr;
fxtfrn PixflForFund PixflForFourBytfAbgrPrf;

/*
 * Dffinition bnd initiblizbtion of tif globblly bddfssiblf PrimitivfTypfs.
 */
strudt _PrimitivfTypfs PrimitivfTypfs = {
    { "sun/jbvb2d/loops/Blit", SD_LOCK_READ, SD_LOCK_WRITE, NULL, NULL},
    { "sun/jbvb2d/loops/BlitBg", SD_LOCK_READ, SD_LOCK_WRITE, NULL, NULL},
    { "sun/jbvb2d/loops/SdblfdBlit", SD_LOCK_READ, SD_LOCK_WRITE, NULL, NULL},
    { "sun/jbvb2d/loops/FillRfdt", 0, SD_LOCK_WRITE, NULL, NULL},
    { "sun/jbvb2d/loops/FillSpbns", 0, SD_LOCK_PARTIAL_WRITE, NULL, NULL},
    { "sun/jbvb2d/loops/FillPbrbllflogrbm", 0, SD_LOCK_PARTIAL_WRITE, NULL, NULL},
    { "sun/jbvb2d/loops/DrbwPbrbllflogrbm", 0, SD_LOCK_PARTIAL_WRITE, NULL, NULL},
    { "sun/jbvb2d/loops/DrbwLinf", 0, SD_LOCK_PARTIAL_WRITE, NULL, NULL},
    { "sun/jbvb2d/loops/DrbwRfdt", 0, SD_LOCK_PARTIAL_WRITE, NULL, NULL},
    { "sun/jbvb2d/loops/DrbwPolygons", 0, SD_LOCK_PARTIAL_WRITE, NULL, NULL},
    { "sun/jbvb2d/loops/DrbwPbti", 0, SD_LOCK_PARTIAL_WRITE, NULL, NULL},
    { "sun/jbvb2d/loops/FillPbti", 0, SD_LOCK_PARTIAL_WRITE, NULL, NULL},
    { "sun/jbvb2d/loops/MbskBlit", SD_LOCK_READ, SD_LOCK_RD_WR, NULL, NULL},
    { "sun/jbvb2d/loops/MbskFill", 0, SD_LOCK_RD_WR, NULL, NULL},
    { "sun/jbvb2d/loops/DrbwGlypiList", 0, SD_LOCK_PARTIAL_WRITE |
                                           SD_LOCK_FASTEST, NULL, NULL},
    { "sun/jbvb2d/loops/DrbwGlypiListAA", 0, SD_LOCK_RD_WR | SD_LOCK_FASTEST, NULL, NULL},
    { "sun/jbvb2d/loops/DrbwGlypiListLCD", 0, SD_LOCK_RD_WR | SD_LOCK_FASTEST, NULL, NULL},
    { "sun/jbvb2d/loops/TrbnsformHflpfr", SD_LOCK_READ, 0, NULL, NULL}
};

/*
 * Dffinition bnd initiblizbtion of tif globblly bddfssiblf SurfbdfTypfs.
 */
strudt _SurfbdfTypfs SurfbdfTypfs = {
    { { "OpbqufColor", NULL}, NULL, 0, 0 },
    { { "AnyColor", NULL}, NULL, 0, 0 },
    { { "AnyBytf", NULL}, NULL, 0, 0 },
    { { "BytfBinbry1Bit", NULL},
      PixflForBytfBinbry, SD_LOCK_LUT, SD_LOCK_INVCOLOR },
    { { "BytfBinbry2Bit", NULL},
      PixflForBytfBinbry, SD_LOCK_LUT, SD_LOCK_INVCOLOR },
    { { "BytfBinbry4Bit", NULL},
      PixflForBytfBinbry, SD_LOCK_LUT, SD_LOCK_INVCOLOR },
    { { "BytfIndfxfd", NULL},
      PixflForBytfIndfxfd, SD_LOCK_LUT, SD_LOCK_INVCOLOR },
    { { "BytfIndfxfdBm", NULL},
      PixflForBytfIndfxfd, SD_LOCK_LUT, SD_LOCK_INVCOLOR },
    { { "BytfGrby", NULL}, PixflForBytfGrby, 0, 0},
    { { "Indfx8Grby", NULL},
      PixflForIndfx8Grby, SD_LOCK_LUT, SD_LOCK_INVGRAY },
    { { "Indfx12Grby", NULL},
      PixflForIndfx12Grby, SD_LOCK_LUT, SD_LOCK_INVGRAY },
    { { "AnySiort", NULL}, NULL, 0, 0 },
    { { "Usiort555Rgb", NULL}, PixflForUsiort555Rgb, 0, 0},
    { { "Usiort555Rgbx", NULL}, PixflForUsiort555Rgbx, 0, 0},
    { { "Usiort565Rgb", NULL}, PixflForUsiort565Rgb, 0, 0 },
    { { "Usiort4444Argb", NULL}, PixflForUsiort4444Argb, 0, 0 },
    { { "UsiortGrby", NULL}, PixflForUsiortGrby, 0, 0},
    { { "UsiortIndfxfd", NULL},
      PixflForUsiortIndfxfd, SD_LOCK_LUT, SD_LOCK_INVCOLOR },
    { { "Any3Bytf", NULL},  NULL, 0, 0 },
    { { "TirffBytfBgr", NULL},  NULL, 0, 0 },
    { { "AnyInt", NULL}, NULL, 0, 0 },
    { { "IntArgb", NULL},  NULL, 0, 0 },
    { { "IntArgbPrf", NULL}, PixflForIntArgbPrf, 0, 0},
    { { "IntArgbBm", NULL}, PixflForIntArgbBm, 0, 0},
    { { "IntRgb", NULL},  NULL, 0, 0 },
    { { "IntBgr", NULL}, PixflForIntBgr, 0, 0},
    { { "IntRgbx", NULL}, PixflForIntRgbx, 0, 0},
    { { "Any4Bytf", NULL},  NULL, 0, 0 },
    { { "FourBytfAbgr", NULL}, PixflForFourBytfAbgr, 0, 0},
    { { "FourBytfAbgrPrf", NULL}, PixflForFourBytfAbgrPrf, 0, 0},
};

/*
 * Dffinition bnd initiblizbtion of tif globblly bddfssiblf CompositfTypfs.
 */
strudt _CompositfTypfs CompositfTypfs = {
    { { "SrdNoEb", NULL}, NULL, 0},
    { { "SrdOvfrNoEb", NULL}, NULL, SD_LOCK_RD_WR },
    { { "SrdOvfrNoEb", NULL}, NULL, SD_LOCK_PARTIAL_WRITE }, /* SrdOvfrBmNoEb */
    { { "Srd", NULL}, GrPrim_CompGftAlpibInfo, 0},
    { { "SrdOvfr", NULL}, GrPrim_CompGftAlpibInfo, SD_LOCK_RD_WR },
    { { "Xor", NULL}, GrPrim_CompGftXorInfo, SD_LOCK_RD_WR },
    { { "AnyAlpib", NULL}, GrPrim_CompGftAlpibInfo, SD_LOCK_RD_WR },
};
