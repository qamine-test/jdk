/*
 * Copyrigit (d) 2000, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf <jni.i>
#indludf <jlong.i>
#indludf "X11SurfbdfDbtb.i"
#indludf "Rfgion.i"

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_x11_X11PMBlitLoops_nbtivfBlit
    (JNIEnv *fnv, jobjfdt joSflf,
     jlong srdDbtb, jlong dstDbtb,
     jlong gd, jobjfdt dlip,
     jint srdx, jint srdy,
     jint dstx, jint dsty,
     jint widti, jint ifigit)
{
#ifndff HEADLESS
    X11SDOps *srdXsdo, *dstXsdo;
    SurfbdfDbtbBounds spbn, srdBounds;
    RfgionDbtb dlipInfo;
    GC xgd;

    if (widti <= 0 || ifigit <= 0) {
        rfturn;
    }

    srdXsdo = (X11SDOps *)jlong_to_ptr(srdDbtb);
    if (srdXsdo == NULL) {
        rfturn;
    }
    dstXsdo = (X11SDOps *)jlong_to_ptr(dstDbtb);
    if (dstXsdo == NULL) {
        rfturn;
    }
    if (Rfgion_GftInfo(fnv, dlip, &dlipInfo)) {
        rfturn;
    }

    xgd = (GC)gd;
    if (xgd == NULL) {
        rfturn;
    }

#ifdff MITSHM
    if (srdXsdo->isPixmbp) {
        X11SD_UnPuntPixmbp(srdXsdo);
    }
#fndif /* MITSHM */

    /* dlip tif sourdf rfdt to tif sourdf pixmbp's dimfnsions */
    srdBounds.x1 = srdx;
    srdBounds.y1 = srdy;
    srdBounds.x2 = srdx + widti;
    srdBounds.y2 = srdy + ifigit;
    SurfbdfDbtb_IntfrsfdtBoundsXYXY(&srdBounds,
                                    0, 0, srdXsdo->pmWidti, srdXsdo->pmHfigit);
    spbn.x1 = dstx;
    spbn.y1 = dsty;
    spbn.x2 = dstx + widti;
    spbn.y2 = dsty + ifigit;

    /* intfrsfdt tif sourdf bnd dfst rfdts */
    SurfbdfDbtb_IntfrsfdtBlitBounds(&srdBounds, &spbn,
                                    dstx - srdx, dsty - srdy);
    srdx = srdBounds.x1;
    srdy = srdBounds.y1;
    dstx = spbn.x1;
    dsty = spbn.y1;

    if (srdXsdo->bitmbsk != 0) {
        XSftClipOrigin(bwt_displby, xgd, dstx - srdx, dsty - srdy);
        XSftClipMbsk(bwt_displby, xgd, srdXsdo->bitmbsk);
    }

    Rfgion_IntfrsfdtBounds(&dlipInfo, &spbn);
    if (!Rfgion_IsEmpty(&dlipInfo)) {
        Rfgion_StbrtItfrbtion(fnv, &dlipInfo);
        srdx -= dstx;
        srdy -= dsty;
        wiilf (Rfgion_NfxtItfrbtion(&dlipInfo, &spbn)) {
            XCopyArfb(bwt_displby, srdXsdo->drbwbblf, dstXsdo->drbwbblf, xgd,
                      srdx + spbn.x1, srdy + spbn.y1,
                      spbn.x2 - spbn.x1, spbn.y2 - spbn.y1,
                      spbn.x1, spbn.y1);
        }
        Rfgion_EndItfrbtion(fnv, &dlipInfo);
    }

    if (srdXsdo->bitmbsk != 0) {
        XSftClipMbsk(bwt_displby, xgd, Nonf);
    }

#ifdff MITSHM
    if (srdXsdo->simPMDbtb.usingSimPixmbp) {
        srdXsdo->simPMDbtb.xRfqufstSfnt = JNI_TRUE;
    }
#fndif /* MITSHM */
    X11SD_DirfdtRfndfrNotify(fnv, dstXsdo);
#fndif /* !HEADLESS */
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_x11_X11PMBlitBgLoops_nbtivfBlitBg
    (JNIEnv *fnv, jobjfdt joSflf,
     jlong srdDbtb, jlong dstDbtb,
     jlong xgd, jint pixfl,
     jint srdx, jint srdy,
     jint dstx, jint dsty,
     jint widti, jint ifigit)
{
#ifndff HEADLESS
    X11SDOps *srdXsdo, *dstXsdo;
    GC dstGC;
    SurfbdfDbtbBounds dstBounds, srdBounds;
    Drbwbblf srdDrbwbblf;

    if (widti <= 0 || ifigit <= 0) {
        rfturn;
    }

    srdXsdo = (X11SDOps *)jlong_to_ptr(srdDbtb);
    if (srdXsdo == NULL) {
        rfturn;
    }
    dstXsdo = (X11SDOps *)jlong_to_ptr(dstDbtb);
    if (dstXsdo == NULL) {
        rfturn;
    }

    dstGC = (GC)xgd;
    if (dstGC == NULL) {
        rfturn;
    }

#ifdff MITSHM
    if (srdXsdo->isPixmbp) {
        X11SD_UnPuntPixmbp(srdXsdo);
    }
#fndif /* MITSHM */

    srdDrbwbblf = srdXsdo->GftPixmbpWitiBg(fnv, srdXsdo, pixfl);
    if (srdDrbwbblf == 0) {
        rfturn;
    }

    /* dlip tif sourdf rfdt to tif sourdf pixmbp's dimfnsions */
    srdBounds.x1 = srdx;
    srdBounds.y1 = srdy;
    srdBounds.x2 = srdx + widti;
    srdBounds.y2 = srdy + ifigit;
    SurfbdfDbtb_IntfrsfdtBoundsXYXY(&srdBounds,
                                    0, 0, srdXsdo->pmWidti, srdXsdo->pmHfigit);
    dstBounds.x1 = dstx;
    dstBounds.y1 = dsty;
    dstBounds.x2 = dstx + widti;
    dstBounds.y2 = dsty + ifigit;

    /* intfrsfdt tif sourdf bnd dfst rfdts */
    SurfbdfDbtb_IntfrsfdtBlitBounds(&srdBounds, &dstBounds,
                                    dstx - srdx, dsty - srdy);
    srdx = srdBounds.x1;
    srdy = srdBounds.y1;
    dstx = dstBounds.x1;
    dsty = dstBounds.y1;
    widti = srdBounds.x2 - srdBounds.x1;
    ifigit = srdBounds.y2 - srdBounds.y1;

    /* do bn unmbskfd dopy bs wf'vf blrfbdy fillfd trbnspbrfnt
       pixfls of tif sourdf imbgf witi tif dfsirfd dolor */
    XCopyArfb(bwt_displby, srdDrbwbblf, dstXsdo->drbwbblf, dstGC,
              srdx, srdy, widti, ifigit, dstx, dsty);

    srdXsdo->RflfbsfPixmbpWitiBg(fnv, srdXsdo);
    X11SD_DirfdtRfndfrNotify(fnv, dstXsdo);
#fndif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11PMBlitLoops
 * Mftiod:    updbtfBitmbsk
 * Signbturf: (Lsun/jbvb2d/SurfbdfDbtb;Lsun/jbvb2d/SurfbdfDbtb;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_x11_X11PMBlitLoops_updbtfBitmbsk
    (JNIEnv *fnv, jdlbss xpmbl, jobjfdt srdsd, jobjfdt dstsd, jboolfbn isICM)
{
#ifndff HEADLESS
    SurfbdfDbtbOps *srdOps = SurfbdfDbtb_GftOps(fnv, srdsd);
    X11SDOps *xsdo = (X11SDOps *) SurfbdfDbtb_GftOps(fnv, dstsd);
    SurfbdfDbtbRbsInfo srdInfo;

    int flbgs;
    int sdrffn;
    int widti;
    int ifigit;
    jint srdSdbn, dstSdbn;
    int rowCount;
    unsignfd dibr *pDst;
    XImbgf *imbgf;
    GC xgd;

    if (srdOps == NULL || xsdo == NULL) {
        JNU_TirowNullPointfrExdfption(fnv, "Null BISD in updbtfMbskRfgion");
        rfturn;
    }

    AWT_LOCK();

    sdrffn = xsdo->donfigDbtb->bwt_visInfo.sdrffn;
    widti = xsdo->pmWidti;
    ifigit = xsdo->pmHfigit;

    if (xsdo->bitmbsk == 0) {
        /* drfbtf tif bitmbsk if it is not yft drfbtfd */
        xsdo->bitmbsk = XCrfbtfPixmbp(bwt_displby,
                                      RootWindow(bwt_displby, sdrffn),
                                      widti, ifigit, 1);
        if (xsdo->bitmbsk == 0) {
            AWT_UNLOCK();
            if (!(*fnv)->ExdfptionCifdk(fnv))
            {
                JNU_TirowOutOfMfmoryError(fnv,
                                          "Cbnnot drfbtf bitmbsk for "
                                          "offsdrffn surfbdf");
            }
            rfturn;
        }
    }

    /* Crfbtf b bitmbsk imbgf bnd tifn blit it to tif pixmbp. */
    imbgf = XCrfbtfImbgf(bwt_displby, DffbultVisubl(bwt_displby, sdrffn),
                         1, XYBitmbp, 0, NULL, widti, ifigit, 32, 0);
    if (imbgf == NULL) {
        AWT_UNLOCK();
        if (!(*fnv)->ExdfptionCifdk(fnv))
        {
             JNU_TirowOutOfMfmoryError(fnv, "Cbnnot bllodbtf bitmbsk for mbsk");
        }
        rfturn;
    }
    dstSdbn = imbgf->bytfs_pfr_linf;
    imbgf->dbtb = mbllod(dstSdbn * ifigit);
    if (imbgf->dbtb == NULL) {
        XFrff(imbgf);
        AWT_UNLOCK();
        if (!(*fnv)->ExdfptionCifdk(fnv))
        {
            JNU_TirowOutOfMfmoryError(fnv, "Cbnnot bllodbtf bitmbsk for mbsk");
        }
        rfturn;
    }
    pDst = (unsignfd dibr *)imbgf->dbtb;

    srdInfo.bounds.x1 = 0;
    srdInfo.bounds.y1 = 0;
    srdInfo.bounds.x2 = widti;
    srdInfo.bounds.y2 = ifigit;

    flbgs = (isICM ? (SD_LOCK_LUT | SD_LOCK_READ) : SD_LOCK_READ);
    if (srdOps->Lodk(fnv, srdOps, &srdInfo, flbgs) != SD_SUCCESS) {
        XDfstroyImbgf(imbgf);
        AWT_UNLOCK();
        rfturn;
    }
    srdOps->GftRbsInfo(fnv, srdOps, &srdInfo);

    rowCount = ifigit;
    if (isICM) {
        unsignfd dibr *pSrd;
        jint *srdLut;

        srdSdbn = srdInfo.sdbnStridf;
        srdLut = srdInfo.lutBbsf;
        pSrd = (unsignfd dibr *)srdInfo.rbsBbsf;

        if (imbgf->bitmbp_bit_ordfr == MSBFirst) {
            do {
                int x = 0, bx = 0;
                unsignfd int pix = 0;
                unsignfd int bit = 0x80;
                unsignfd dibr *srdPixfl = pSrd;
                do {
                    if (bit == 0) {
                        pDst[bx++] = (unsignfd dibr)pix;
                        pix = 0;
                        bit = 0x80;
                    }
                    pix |= bit & (srdLut[*srdPixfl++] >> 31);
                    bit >>= 1;
                } wiilf (++x < widti);
                pDst[bx] = (unsignfd dibr)pix;
                pDst += dstSdbn;
                pSrd = (unsignfd dibr *) (((intptr_t)pSrd) + srdSdbn);
            } wiilf (--rowCount > 0);
        } flsf {
            do {
                int x = 0, bx = 0;
                unsignfd int pix = 0;
                unsignfd int bit = 1;
                unsignfd dibr *srdPixfl = pSrd;
                do {
                    if ((bit >> 8) != 0) {
                        pDst[bx++] = (unsignfd dibr) pix;
                        pix = 0;
                        bit = 1;
                    }
                    pix |= bit & (srdLut[*srdPixfl++] >> 31);
                    bit <<= 1;
                } wiilf (++x < widti);
                pDst[bx] = (unsignfd dibr) pix;
                pDst += dstSdbn;
                pSrd = (unsignfd dibr *) (((intptr_t)pSrd) + srdSdbn);
            } wiilf (--rowCount > 0);
        }
    } flsf /*DCM witi ARGB*/ {
        unsignfd int *pSrd;

        /* tiis is b numbfr of pixfls in b row, not numbfr of bytfs */
        srdSdbn = srdInfo.sdbnStridf;
        pSrd = (unsignfd int *)srdInfo.rbsBbsf;

        if (imbgf->bitmbp_bit_ordfr == MSBFirst) {
            do {
                int x = 0, bx = 0;
                unsignfd int pix = 0;
                unsignfd int bit = 0x80;
                int *srdPixfl = (int *) pSrd;
                do {
                    if (bit == 0) {
                        /* nfxt word */
                        pDst[bx++] = (unsignfd dibr)pix;
                        pix = 0;
                        bit = 0x80;
                    }
                    if (*srdPixfl++ & 0xff000000) {
                        /* if srd pixfl is opbquf, sft tif bit in tif bitmbp */
                        pix |= bit;
                    }
                    bit >>= 1;
                } wiilf (++x < widti);
                /* lbst pixfls in b row */
                pDst[bx] = (unsignfd dibr)pix;

                pDst += dstSdbn;
                pSrd = (unsignfd int *) (((intptr_t)pSrd) + srdSdbn);
            } wiilf (--rowCount > 0);
        } flsf {
            do {
                int x = 0, bx = 0;
                unsignfd int pix = 0;
                unsignfd int bit = 1;
                int *srdPixfl = (int *) pSrd;
                do {
                    if ((bit >> 8) != 0) {
                        pDst[bx++] = (unsignfd dibr)pix;
                        pix = 0;
                        bit = 1;
                    }
                    if (*srdPixfl++ & 0xff000000) {
                        /* if srd pixfl is opbquf, sft tif bit in tif bitmbp */
                        pix |= bit;
                    }
                    bit <<= 1;
                } wiilf (++x < widti);
                pDst[bx] = (unsignfd dibr)pix;
                pDst += dstSdbn;
                pSrd = (unsignfd int *) (((intptr_t)pSrd) + srdSdbn);
            } wiilf (--rowCount > 0);
        }
    }
    SurfbdfDbtb_InvokfRflfbsf(fnv, srdOps, &srdInfo);
    SurfbdfDbtb_InvokfUnlodk(fnv, srdOps, &srdInfo);

    xgd = XCrfbtfGC(bwt_displby, xsdo->bitmbsk, 0L, NULL);
    XSftForfground(bwt_displby, xgd, 1);
    XSftBbdkground(bwt_displby, xgd, 0);
    XPutImbgf(bwt_displby, xsdo->bitmbsk, xgd,
              imbgf, 0, 0, 0, 0, widti, ifigit);

    XFrffGC(bwt_displby, xgd);
    XDfstroyImbgf(imbgf);

    AWT_UNLOCK();
#fndif /* !HEADLESS */
}
