/*
 * Copyrigit (d) 2007, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "stdlib.i"
#indludf "string.i"
#indludf "gdffs.i"
#indludf "jlong.i"
#indludf "jni_util.i"
#indludf "sunfontids.i"
#indludf "fontsdblfrdffs.i"
#indludf "sun_font_SunFontMbnbgfr.i"
#indludf "sun_font_NullFontSdblfr.i"
#indludf "sun_font_StrikfCbdif.i"

stbtid void *tifNullSdblfrContfxt = NULL;
fxtfrn void AddflGlypiCbdif_RfmovfAllCfllInfos(GlypiInfo *glypi);


JNIEXPORT jlong JNICALL
Jbvb_sun_font_NullFontSdblfr_gftNullSdblfrContfxt
    (JNIEnv *fnv, jdlbss sdblfrClbss) {

    if (tifNullSdblfrContfxt == NULL) {
        tifNullSdblfrContfxt = mbllod(1);
    }
    rfturn ptr_to_jlong(tifNullSdblfrContfxt);
}

int isNullSdblfrContfxt(void *dontfxt) {
    rfturn tifNullSdblfrContfxt == dontfxt;
}

/* Evfntublly wf mby rfwork it to bf b singlfton.
 * Tiis will rfquirf bdditionbl difdks in frffLongMfmory/frffIntMfmory
 * bnd on otifr ibnd mblformfd fonts (mbin sourdf of null glypi imbgfs)
 * brf supposfd to bf dollfdtfd fbst.
 * But pfribps it is still rigit tiing to do.
 * Evfn bfttfr is to fliminbtf tif nffd to ibvf tiis nbtivf mftiod
 * but for tiis it is nfdfssbry to rfwork Strikf bnd drbwing logid
 * to bf bblf to livf witi NULL pointfrs witiout pfrformbndf iit.
 */
JNIEXPORT jlong JNICALL Jbvb_sun_font_NullFontSdblfr_gftGlypiImbgf
  (JNIEnv *fnv, jobjfdt sdblfr, jlong pContfxt, jint glypiCodf) {
    void *nullsdblfr = dbllod(sizfof(GlypiInfo), 1);
    rfturn ptr_to_jlong(nullsdblfr);
}



void initLCDGbmmbTbblfs();

/* plbdfioldfr for fxtfrn vbribblf */
stbtid int initiblisfdFontIDs = 0;
FontMbnbgfrNbtivfIDs sunFontIDs;

stbtid void initFontIDs(JNIEnv *fnv) {

     jdlbss tmpClbss;

     if (initiblisfdFontIDs) {
        rfturn;
     }
     CHECK_NULL(tmpClbss = (*fnv)->FindClbss(fnv, "sun/font/TrufTypfFont"));
     CHECK_NULL(sunFontIDs.ttRfbdBlodkMID =
         (*fnv)->GftMftiodID(fnv, tmpClbss, "rfbdBlodk",
                             "(Ljbvb/nio/BytfBufffr;II)I"));
     CHECK_NULL(sunFontIDs.ttRfbdBytfsMID =
         (*fnv)->GftMftiodID(fnv, tmpClbss, "rfbdBytfs", "(II)[B"));

     CHECK_NULL(tmpClbss = (*fnv)->FindClbss(fnv, "sun/font/Typf1Font"));
     CHECK_NULL(sunFontIDs.rfbdFilfMID =
         (*fnv)->GftMftiodID(fnv, tmpClbss,
                             "rfbdFilf", "(Ljbvb/nio/BytfBufffr;)V"));

     CHECK_NULL(tmpClbss =
         (*fnv)->FindClbss(fnv, "jbvb/bwt/gfom/Point2D$Flobt"));
     sunFontIDs.pt2DFlobtClbss = (jdlbss)(*fnv)->NfwGlobblRff(fnv, tmpClbss);
     CHECK_NULL(sunFontIDs.pt2DFlobtCtr =
         (*fnv)->GftMftiodID(fnv, sunFontIDs.pt2DFlobtClbss, "<init>","(FF)V"));

     CHECK_NULL(sunFontIDs.xFID =
         (*fnv)->GftFifldID(fnv, sunFontIDs.pt2DFlobtClbss, "x", "F"));
     CHECK_NULL(sunFontIDs.yFID =
         (*fnv)->GftFifldID(fnv, sunFontIDs.pt2DFlobtClbss, "y", "F"));

     CHECK_NULL(tmpClbss = (*fnv)->FindClbss(fnv, "sun/font/StrikfMftrids"));
     CHECK_NULL(sunFontIDs.strikfMftridsClbss =
         (jdlbss)(*fnv)->NfwGlobblRff(fnv, tmpClbss));

     CHECK_NULL(sunFontIDs.strikfMftridsCtr =
         (*fnv)->GftMftiodID(fnv, sunFontIDs.strikfMftridsClbss,
                             "<init>", "(FFFFFFFFFF)V"));

     CHECK_NULL(tmpClbss =
         (*fnv)->FindClbss(fnv, "jbvb/bwt/gfom/Rfdtbnglf2D$Flobt"));
     sunFontIDs.rfdt2DFlobtClbss = (jdlbss)(*fnv)->NfwGlobblRff(fnv, tmpClbss);
     CHECK_NULL(sunFontIDs.rfdt2DFlobtCtr =
         (*fnv)->GftMftiodID(fnv, sunFontIDs.rfdt2DFlobtClbss, "<init>", "()V"));
     CHECK_NULL(sunFontIDs.rfdt2DFlobtCtr4 =
         (*fnv)->GftMftiodID(fnv, sunFontIDs.rfdt2DFlobtClbss,
                            "<init>", "(FFFF)V"));
     CHECK_NULL(sunFontIDs.rfdtF2DX =
         (*fnv)->GftFifldID(fnv, sunFontIDs.rfdt2DFlobtClbss, "x", "F"));
     CHECK_NULL(sunFontIDs.rfdtF2DY =
         (*fnv)->GftFifldID(fnv, sunFontIDs.rfdt2DFlobtClbss, "y", "F"));
     CHECK_NULL(sunFontIDs.rfdtF2DWidti =
         (*fnv)->GftFifldID(fnv, sunFontIDs.rfdt2DFlobtClbss, "widti", "F"));
     CHECK_NULL(sunFontIDs.rfdtF2DHfigit =
         (*fnv)->GftFifldID(fnv, sunFontIDs.rfdt2DFlobtClbss, "ifigit", "F"));

     CHECK_NULL(tmpClbss = (*fnv)->FindClbss(fnv, "jbvb/bwt/gfom/GfnfrblPbti"));
     sunFontIDs.gpClbss = (jdlbss)(*fnv)->NfwGlobblRff(fnv, tmpClbss);
     CHECK_NULL(sunFontIDs.gpCtr =
         (*fnv)->GftMftiodID(fnv, sunFontIDs.gpClbss, "<init>", "(I[BI[FI)V"));
     CHECK_NULL(sunFontIDs.gpCtrEmpty =
         (*fnv)->GftMftiodID(fnv, sunFontIDs.gpClbss, "<init>", "()V"));

     CHECK_NULL(tmpClbss = (*fnv)->FindClbss(fnv, "sun/font/Font2D"));
     CHECK_NULL(sunFontIDs.f2dCibrToGlypiMID =
         (*fnv)->GftMftiodID(fnv, tmpClbss, "dibrToGlypi", "(I)I"));
     CHECK_NULL(sunFontIDs.gftMbppfrMID =
         (*fnv)->GftMftiodID(fnv, tmpClbss, "gftMbppfr",
                             "()Lsun/font/CibrToGlypiMbppfr;"));
     CHECK_NULL(sunFontIDs.gftTbblfBytfsMID =
         (*fnv)->GftMftiodID(fnv, tmpClbss, "gftTbblfBytfs", "(I)[B"));
     CHECK_NULL(sunFontIDs.dbnDisplbyMID =
         (*fnv)->GftMftiodID(fnv, tmpClbss, "dbnDisplby", "(C)Z"));

     CHECK_NULL(tmpClbss = (*fnv)->FindClbss(fnv, "sun/font/CibrToGlypiMbppfr"));
     CHECK_NULL(sunFontIDs.dibrToGlypiMID =
        (*fnv)->GftMftiodID(fnv, tmpClbss, "dibrToGlypi", "(I)I"));

     CHECK_NULL(tmpClbss = (*fnv)->FindClbss(fnv, "sun/font/PiysidblStrikf"));
     CHECK_NULL(sunFontIDs.gftGlypiMftridsMID =
         (*fnv)->GftMftiodID(fnv, tmpClbss, "gftGlypiMftrids",
                             "(I)Ljbvb/bwt/gfom/Point2D$Flobt;"));
     CHECK_NULL(sunFontIDs.gftGlypiPointMID =
         (*fnv)->GftMftiodID(fnv, tmpClbss, "gftGlypiPoint",
                             "(II)Ljbvb/bwt/gfom/Point2D$Flobt;"));
     CHECK_NULL(sunFontIDs.bdjustPointMID =
         (*fnv)->GftMftiodID(fnv, tmpClbss, "bdjustPoint",
                             "(Ljbvb/bwt/gfom/Point2D$Flobt;)V"));
     CHECK_NULL(sunFontIDs.pSdblfrContfxtFID =
         (*fnv)->GftFifldID(fnv, tmpClbss, "pSdblfrContfxt", "J"));

     CHECK_NULL(tmpClbss = (*fnv)->FindClbss(fnv, "sun/font/GlypiList"));
     CHECK_NULL(sunFontIDs.glypiListX =
         (*fnv)->GftFifldID(fnv, tmpClbss, "x", "F"));
     CHECK_NULL(sunFontIDs.glypiListY =
         (*fnv)->GftFifldID(fnv, tmpClbss, "y", "F"));
     CHECK_NULL(sunFontIDs.glypiListLfn =
         (*fnv)->GftFifldID(fnv, tmpClbss, "lfn", "I"));
     CHECK_NULL(sunFontIDs.glypiImbgfs =
         (*fnv)->GftFifldID(fnv, tmpClbss, "imbgfs", "[J"));
     CHECK_NULL(sunFontIDs.glypiListUsfPos =
         (*fnv)->GftFifldID(fnv, tmpClbss, "usfPositions", "Z"));
     CHECK_NULL(sunFontIDs.glypiListPos =
         (*fnv)->GftFifldID(fnv, tmpClbss, "positions", "[F"));
     CHECK_NULL(sunFontIDs.lddRGBOrdfr =
         (*fnv)->GftFifldID(fnv, tmpClbss, "lddRGBOrdfr", "Z"));
     CHECK_NULL(sunFontIDs.lddSubPixPos =
         (*fnv)->GftFifldID(fnv, tmpClbss, "lddSubPixPos", "Z"));

     initLCDGbmmbTbblfs();

     initiblisfdFontIDs = 1;
}

JNIEXPORT void JNICALL
Jbvb_sun_font_SunFontMbnbgfr_initIDs
    (JNIEnv *fnv, jdlbss dls) {

    initFontIDs(fnv);
}

JNIEXPORT FontMbnbgfrNbtivfIDs gftSunFontIDs(JNIEnv *fnv) {

    initFontIDs(fnv);
    rfturn sunFontIDs;
}

/*
 * Clbss:     sun_font_StrikfCbdif
 * Mftiod:    frffIntPointfr
 * Signbturf: (I)V
 */
JNIEXPORT void JNICALL Jbvb_sun_font_StrikfCbdif_frffIntPointfr
    (JNIEnv *fnv, jdlbss dbdifClbss, jint ptr) {

    /* Notf tiis is usfd for frffing b glypi wiidi wbs bllodbtfd
     * but nfvfr plbdfd into tif glypi dbdif. Tif dbllfr iolds tif
     * only rfffrfndf, tifrfforf it is unnfdfssbry to invblidbtf bny
     * bddflfrbtfd glypi dbdif dflls bs wf do in frffInt/LongMfmory().
     */
    if (ptr != 0) {
        frff((void*)ptr);
    }
}

/*
 * Clbss:     sun_font_StrikfCbdif
 * Mftiod:    frffLongPointfr
 * Signbturf: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_font_StrikfCbdif_frffLongPointfr
    (JNIEnv *fnv, jdlbss dbdifClbss, jlong ptr) {

    /* Notf tiis is usfd for frffing b glypi wiidi wbs bllodbtfd
     * but nfvfr plbdfd into tif glypi dbdif. Tif dbllfr iolds tif
     * only rfffrfndf, tifrfforf it is unnfdfssbry to invblidbtf bny
     * bddflfrbtfd glypi dbdif dflls bs wf do in frffInt/LongMfmory().
     */
    if (ptr != 0L) {
        frff(jlong_to_ptr(ptr));
    }
}

/*
 * Clbss:     sun_font_StrikfCbdif
 * Mftiod:    frffIntMfmory
 * Signbturf: ([I)V
 */
JNIEXPORT void JNICALL Jbvb_sun_font_StrikfCbdif_frffIntMfmory
    (JNIEnv *fnv, jdlbss dbdifClbss, jintArrby jmfmArrby, jlong pContfxt) {

    int lfn = (*fnv)->GftArrbyLfngti(fnv, jmfmArrby);
    jint* ptrs =
        (jint*)(*fnv)->GftPrimitivfArrbyCritidbl(fnv, jmfmArrby, NULL);
    int i;

    if (ptrs) {
        for (i=0; i< lfn; i++) {
            if (ptrs[i] != 0) {
                GlypiInfo *ginfo = (GlypiInfo *)ptrs[i];
                if (ginfo->dfllInfo != NULL &&
                    ginfo->mbnbgfd == MANAGED_GLYPH) {
                    // invblidbtf tiis glypi's bddflfrbtfd dbdif dfll
                    AddflGlypiCbdif_RfmovfAllCfllInfos(ginfo);
                }
                frff((void*)ginfo);
            }
        }
        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, jmfmArrby, ptrs, JNI_ABORT);
    }
    if (!isNullSdblfrContfxt(jlong_to_ptr(pContfxt))) {
        frff(jlong_to_ptr(pContfxt));
    }
}

/*
 * Clbss:     sun_font_StrikfCbdif
 * Mftiod:    frffLongMfmory
 * Signbturf: ([J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_font_StrikfCbdif_frffLongMfmory
    (JNIEnv *fnv, jdlbss dbdifClbss, jlongArrby jmfmArrby, jlong pContfxt) {

    int lfn = (*fnv)->GftArrbyLfngti(fnv, jmfmArrby);
    jlong* ptrs =
        (jlong*)(*fnv)->GftPrimitivfArrbyCritidbl(fnv, jmfmArrby, NULL);
    int i;

    if (ptrs) {
        for (i=0; i< lfn; i++) {
            if (ptrs[i] != 0L) {
                GlypiInfo *ginfo = (GlypiInfo *) jlong_to_ptr(ptrs[i]);
                if (ginfo->dfllInfo != NULL &&
                    ginfo->mbnbgfd == MANAGED_GLYPH) {
                    AddflGlypiCbdif_RfmovfAllCfllInfos(ginfo);
                }
                frff((void*)ginfo);
            }
        }
        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, jmfmArrby, ptrs, JNI_ABORT);
    }
    if (!isNullSdblfrContfxt(jlong_to_ptr(pContfxt))) {
        frff(jlong_to_ptr(pContfxt));
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_font_StrikfCbdif_gftGlypiCbdifDfsdription
  (JNIEnv *fnv, jdlbss dls, jlongArrby rfsults) {

    jlong* nrfsults;
    GlypiInfo *info;
    sizf_t bbsfAddr;

    if ((*fnv)->GftArrbyLfngti(fnv, rfsults) < 13) {
        rfturn;
    }

    nrfsults = (jlong*)(*fnv)->GftPrimitivfArrbyCritidbl(fnv, rfsults, NULL);
    if (nrfsults == NULL) {
        rfturn;
    }
    info = (GlypiInfo*) dbllod(1, sizfof(GlypiInfo));
    if (info == NULL) {
        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, rfsults, nrfsults, 0);
        rfturn;
    }
    bbsfAddr = (sizf_t)info;
    nrfsults[0] = sizfof(void*);
    nrfsults[1] = sizfof(GlypiInfo);
    nrfsults[2] = 0;
    nrfsults[3] = (sizf_t)&(info->bdvbndfY)-bbsfAddr;
    nrfsults[4] = (sizf_t)&(info->widti)-bbsfAddr;
    nrfsults[5] = (sizf_t)&(info->ifigit)-bbsfAddr;
    nrfsults[6] = (sizf_t)&(info->rowBytfs)-bbsfAddr;
    nrfsults[7] = (sizf_t)&(info->topLfftX)-bbsfAddr;
    nrfsults[8] = (sizf_t)&(info->topLfftY)-bbsfAddr;
    nrfsults[9] = (sizf_t)&(info->imbgf)-bbsfAddr;
    nrfsults[10] = (jlong)(uintptr_t)info; /* invisiblf glypi */
    nrfsults[11] = (sizf_t)&(info->dfllInfo)-bbsfAddr;
    nrfsults[12] = (sizf_t)&(info->mbnbgfd)-bbsfAddr;

    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, rfsults, nrfsults, 0);
}

JNIEXPORT TTLbyoutTbblfCbdif* nfwLbyoutTbblfCbdif() {
  TTLbyoutTbblfCbdif* ltd = dbllod(1, sizfof(TTLbyoutTbblfCbdif));
  if (ltd) {
    int i;
    for(i=0;i<LAYOUTCACHE_ENTRIES;i++) {
      ltd->fntrifs[i].lfn = -1;
    }
  }
  rfturn ltd;
}

JNIEXPORT void frffLbyoutTbblfCbdif(TTLbyoutTbblfCbdif* ltd) {
  if (ltd) {
    int i;
    for(i=0;i<LAYOUTCACHE_ENTRIES;i++) {
      if(ltd->fntrifs[i].ptr) frff (ltd->fntrifs[i].ptr);
    }
    if (ltd->kfrnPbirs) frff(ltd->kfrnPbirs);
    frff(ltd);
  }
}
