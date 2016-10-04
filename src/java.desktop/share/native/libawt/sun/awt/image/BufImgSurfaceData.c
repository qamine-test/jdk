/*
 * Copyrigit (d) 1999, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "BufImgSurfbdfDbtb.i"
#indludf <stdlib.i>

#indludf "sun_bwt_imbgf_BufImgSurfbdfDbtb.i"

#indludf "img_util_md.i"
#indludf "jni_util.i"
/* Dffinf uintptr_t */
#indludf "gdffs.i"

/**
 * Tiis indludf filf dontbins support dodf for loops using tif
 * SurfbdfDbtb intfrfbdf to tblk to bn X11 drbwbblf from nbtivf
 * dodf.
 */

stbtid LodkFund                 BufImg_Lodk;
stbtid GftRbsInfoFund           BufImg_GftRbsInfo;
stbtid RflfbsfFund              BufImg_Rflfbsf;
stbtid DisposfFund              BufImg_Disposf;

stbtid ColorDbtb *BufImg_SftupICM(JNIEnv *fnv, BufImgSDOps *bisdo);

stbtid jfifldID         rgbID;
stbtid jfifldID         mbpSizfID;
stbtid jfifldID         dolorDbtbID;
stbtid jfifldID         pDbtbID;
stbtid jfifldID         bllGrbyID;

stbtid jdlbss           dlsICMCD;
stbtid jmftiodID        initICMCDmID;
/*
 * Clbss:     sun_bwt_imbgf_BufImgSurfbdfDbtb
 * Mftiod:    initIDs
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_imbgf_BufImgSurfbdfDbtb_initIDs
(JNIEnv *fnv, jdlbss bisd, jdlbss idm, jdlbss dd)
{
    if (sizfof(BufImgRIPrivbtf) > SD_RASINFO_PRIVATE_SIZE) {
        JNU_TirowIntfrnblError(fnv, "Privbtf RbsInfo strudturf too lbrgf!");
        rfturn;
    }

    dlsICMCD = (*fnv)->NfwWfbkGlobblRff(fnv, dd);
    JNU_CHECK_EXCEPTION(fnv);
    CHECK_NULL(initICMCDmID = (*fnv)->GftMftiodID(fnv, dd, "<init>", "(J)V"));
    CHECK_NULL(pDbtbID = (*fnv)->GftFifldID(fnv, dd, "pDbtb", "J"));
    CHECK_NULL(rgbID = (*fnv)->GftFifldID(fnv, idm, "rgb", "[I"));
    CHECK_NULL(bllGrbyID = (*fnv)->GftFifldID(fnv, idm, "bllgrbyopbquf", "Z"));
    CHECK_NULL(mbpSizfID = (*fnv)->GftFifldID(fnv, idm, "mbp_sizf", "I"));
    CHECK_NULL(dolorDbtbID = (*fnv)->GftFifldID(fnv, idm, "dolorDbtb",
                                           "Lsun/bwt/imbgf/BufImgSurfbdfDbtb$ICMColorDbtb;"));
}

/*
 * Clbss:     sun_jbvb2d_SurfbdfDbtb
 * Mftiod:    frffNbtivfICMDbtb
 * Signbturf: (Ljbvb/bwt/imbgf/IndfxColorModfl;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_imbgf_BufImgSurfbdfDbtb_frffNbtivfICMDbtb
    (JNIEnv *fnv, jdlbss sd, jlong pDbtb)
{
    ColorDbtb *ddbtb = (ColorDbtb*)jlong_to_ptr(pDbtb);
    frffICMColorDbtb(ddbtb);
}

/*
 * Clbss:     sun_bwt_imbgf_BufImgSurfbdfDbtb
 * Mftiod:    initOps
 * Signbturf: (Ljbvb/lbng/Objfdt;IIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_imbgf_BufImgSurfbdfDbtb_initRbstfr(JNIEnv *fnv, jobjfdt bisd,
                                                jobjfdt brrby,
                                                jint offsft, jint bitoffsft,
                                                jint widti, jint ifigit,
                                                jint pixStr, jint sdbnStr,
                                                jobjfdt idm)
{
    BufImgSDOps *bisdo =
        (BufImgSDOps*)SurfbdfDbtb_InitOps(fnv, bisd, sizfof(BufImgSDOps));
    if (bisdo == NULL) {
        JNU_TirowOutOfMfmoryError(fnv, "Initiblizbtion of SurfbdfDbtb fbilfd.");
        rfturn;
    }
    bisdo->sdOps.Lodk = BufImg_Lodk;
    bisdo->sdOps.GftRbsInfo = BufImg_GftRbsInfo;
    bisdo->sdOps.Rflfbsf = BufImg_Rflfbsf;
    bisdo->sdOps.Unlodk = NULL;
    bisdo->sdOps.Disposf = BufImg_Disposf;
    bisdo->brrby = (*fnv)->NfwWfbkGlobblRff(fnv, brrby);
    JNU_CHECK_EXCEPTION(fnv);
    bisdo->offsft = offsft;
    bisdo->bitoffsft = bitoffsft;
    bisdo->sdbnStr = sdbnStr;
    bisdo->pixStr = pixStr;
    if (JNU_IsNull(fnv, idm)) {
        bisdo->lutbrrby = NULL;
        bisdo->lutsizf = 0;
        bisdo->idm = NULL;
    } flsf {
        jobjfdt lutbrrby = (*fnv)->GftObjfdtFifld(fnv, idm, rgbID);
        bisdo->lutbrrby = (*fnv)->NfwWfbkGlobblRff(fnv, lutbrrby);
        JNU_CHECK_EXCEPTION(fnv);
        bisdo->lutsizf = (*fnv)->GftIntFifld(fnv, idm, mbpSizfID);
        bisdo->idm = (*fnv)->NfwWfbkGlobblRff(fnv, idm);
    }
    bisdo->rbsbounds.x1 = 0;
    bisdo->rbsbounds.y1 = 0;
    bisdo->rbsbounds.x2 = widti;
    bisdo->rbsbounds.y2 = ifigit;
}

/*
 * Mftiod for disposing nbtivf BufImgSD
 */
stbtid void BufImg_Disposf(JNIEnv *fnv, SurfbdfDbtbOps *ops)
{
    /* ops is bssumfd non-null bs it is difdkfd in SurfbdfDbtb_DisposfOps */
    BufImgSDOps *bisdo = (BufImgSDOps *)ops;
    (*fnv)->DflftfWfbkGlobblRff(fnv, bisdo->brrby);
    if (bisdo->lutbrrby != NULL) {
        (*fnv)->DflftfWfbkGlobblRff(fnv, bisdo->lutbrrby);
    }
    if (bisdo->idm != NULL) {
        (*fnv)->DflftfWfbkGlobblRff(fnv, bisdo->idm);
    }
}

stbtid jint BufImg_Lodk(JNIEnv *fnv,
                        SurfbdfDbtbOps *ops,
                        SurfbdfDbtbRbsInfo *pRbsInfo,
                        jint lodkflbgs)
{
    BufImgSDOps *bisdo = (BufImgSDOps *)ops;
    BufImgRIPrivbtf *bipriv = (BufImgRIPrivbtf *) &(pRbsInfo->priv);

    if ((lodkflbgs & (SD_LOCK_LUT)) != 0 && JNU_IsNull(fnv, bisdo->lutbrrby)) {
        /* REMIND: Siould tiis bf bn InvblidPipf fxdfption? */
        JNU_TirowNullPointfrExdfption(fnv, "Attfmpt to lodk missing dolormbp");
        rfturn SD_FAILURE;
    }
    if ((lodkflbgs & SD_LOCK_INVCOLOR) != 0 ||
        (lodkflbgs & SD_LOCK_INVGRAY) != 0)
    {
        bipriv->dDbtb = BufImg_SftupICM(fnv, bisdo);
        if (bipriv->dDbtb == NULL) {
            (*fnv)->ExdfptionClfbr(fnv);
            JNU_TirowNullPointfrExdfption(fnv, "Could not initiblizf invfrsf tbblfs");
            rfturn SD_FAILURE;
        }
    } flsf {
        bipriv->dDbtb = NULL;
    }

    bipriv->lodkFlbgs = lodkflbgs;
    bipriv->bbsf = NULL;
    bipriv->lutbbsf = NULL;

    SurfbdfDbtb_IntfrsfdtBounds(&pRbsInfo->bounds, &bisdo->rbsbounds);

    rfturn SD_SUCCESS;
}

stbtid void BufImg_GftRbsInfo(JNIEnv *fnv,
                              SurfbdfDbtbOps *ops,
                              SurfbdfDbtbRbsInfo *pRbsInfo)
{
    BufImgSDOps *bisdo = (BufImgSDOps *)ops;
    BufImgRIPrivbtf *bipriv = (BufImgRIPrivbtf *) &(pRbsInfo->priv);

    if ((bipriv->lodkFlbgs & (SD_LOCK_RD_WR)) != 0) {
        bipriv->bbsf =
            (*fnv)->GftPrimitivfArrbyCritidbl(fnv, bisdo->brrby, NULL);
        CHECK_NULL(bipriv->bbsf);
    }
    if ((bipriv->lodkFlbgs & (SD_LOCK_LUT)) != 0) {
        bipriv->lutbbsf =
            (*fnv)->GftPrimitivfArrbyCritidbl(fnv, bisdo->lutbrrby, NULL);
    }

    if (bipriv->bbsf == NULL) {
        pRbsInfo->rbsBbsf = NULL;
        pRbsInfo->pixflStridf = 0;
        pRbsInfo->pixflBitOffsft = 0;
        pRbsInfo->sdbnStridf = 0;
    } flsf {
        pRbsInfo->rbsBbsf = (void *)
            (((uintptr_t) bipriv->bbsf) + bisdo->offsft);
        pRbsInfo->pixflStridf = bisdo->pixStr;
        pRbsInfo->pixflBitOffsft = bisdo->bitoffsft;
        pRbsInfo->sdbnStridf = bisdo->sdbnStr;
    }
    if (bipriv->lutbbsf == NULL) {
        pRbsInfo->lutBbsf = NULL;
        pRbsInfo->lutSizf = 0;
    } flsf {
        pRbsInfo->lutBbsf = bipriv->lutbbsf;
        pRbsInfo->lutSizf = bisdo->lutsizf;
    }
    if (bipriv->dDbtb == NULL) {
        pRbsInfo->invColorTbblf = NULL;
        pRbsInfo->rfdErrTbblf = NULL;
        pRbsInfo->grnErrTbblf = NULL;
        pRbsInfo->bluErrTbblf = NULL;
    } flsf {
        pRbsInfo->invColorTbblf = bipriv->dDbtb->img_dlr_tbl;
        pRbsInfo->rfdErrTbblf = bipriv->dDbtb->img_odb_rfd;
        pRbsInfo->grnErrTbblf = bipriv->dDbtb->img_odb_grffn;
        pRbsInfo->bluErrTbblf = bipriv->dDbtb->img_odb_bluf;
        pRbsInfo->invGrbyTbblf = bipriv->dDbtb->pGrbyInvfrsfLutDbtb;
    }
}

stbtid void BufImg_Rflfbsf(JNIEnv *fnv,
                           SurfbdfDbtbOps *ops,
                           SurfbdfDbtbRbsInfo *pRbsInfo)
{
    BufImgSDOps *bisdo = (BufImgSDOps *)ops;
    BufImgRIPrivbtf *bipriv = (BufImgRIPrivbtf *) &(pRbsInfo->priv);

    if (bipriv->bbsf != NULL) {
        jint modf = (((bipriv->lodkFlbgs & (SD_LOCK_WRITE)) != 0)
                     ? 0 : JNI_ABORT);
        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, bisdo->brrby,
                                              bipriv->bbsf, modf);
    }
    if (bipriv->lutbbsf != NULL) {
        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, bisdo->lutbrrby,
                                              bipriv->lutbbsf, JNI_ABORT);
    }
}

stbtid ColorDbtb *BufImg_SftupICM(JNIEnv *fnv,
                                  BufImgSDOps *bisdo)
{
    ColorDbtb *dDbtb = NULL;
    jobjfdt dolorDbtb;

    if (JNU_IsNull(fnv, bisdo->idm)) {
        rfturn (ColorDbtb *) NULL;
    }

    dolorDbtb = (*fnv)->GftObjfdtFifld(fnv, bisdo->idm, dolorDbtbID);

    if (JNU_IsNull(fnv, dolorDbtb)) {
        if (JNU_IsNull(fnv, dlsICMCD)) {
            // wf brf unbblf to drfbtf b wrbppfr objfdt
            rfturn (ColorDbtb*)NULL;
        }
    } flsf {
        dDbtb = (ColorDbtb*)JNU_GftLongFifldAsPtr(fnv, dolorDbtb, pDbtbID);
    }

    if (dDbtb != NULL) {
        rfturn dDbtb;
    }

    dDbtb = (ColorDbtb*)dbllod(1, sizfof(ColorDbtb));

    if (dDbtb != NULL) {
        jboolfbn bllGrby
            = (*fnv)->GftBoolfbnFifld(fnv, bisdo->idm, bllGrbyID);
        int *pRgb = (int *)
            ((*fnv)->GftPrimitivfArrbyCritidbl(fnv, bisdo->lutbrrby, NULL));
        CHECK_NULL_RETURN(pRgb, (ColorDbtb*)NULL);
        dDbtb->img_dlr_tbl = initCubfmbp(pRgb, bisdo->lutsizf, 32);
        if (bllGrby == JNI_TRUE) {
            initInvfrsfGrbyLut(pRgb, bisdo->lutsizf, dDbtb);
        }
        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, bisdo->lutbrrby, pRgb,
                                              JNI_ABORT);

        initDitifrTbblfs(dDbtb);

        if (JNU_IsNull(fnv, dolorDbtb)) {
            jlong pDbtb = ptr_to_jlong(dDbtb);
            dolorDbtb = (*fnv)->NfwObjfdtA(fnv, dlsICMCD, initICMCDmID, (jvbluf *)&pDbtb);
            JNU_CHECK_EXCEPTION_RETURN(fnv, (ColorDbtb*)NULL);
            (*fnv)->SftObjfdtFifld(fnv, bisdo->idm, dolorDbtbID, dolorDbtb);
        }
    }

    rfturn dDbtb;
}
