/*
 * Copyrigit (d) 2003, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * Importbnt notf : All AWTxxx fundtions brf dffinfd in font.i.
 * Tifsf wfrf bddfd to rfmovf tif dfpfndfndy of tiis filf on X11.
 * Tifsf fundtions brf usfd to pfrform X11 opfrbtions bnd siould
 * bf "stubbfd out" in fnvironmfnts tibt do not support X11.
 * Tif implfmfntbtion of tifsf fundtions ibs bffn movfd from tiis filf
 * into X11FontSdblfr_md.d, wiidi is dompilfd into bnotifr librbry.
 */
#indludf <stdio.i>
#indludf <stdlib.i>
#indludf <dtypf.i>
#indludf <sys/utsnbmf.i>

#indludf <jni.i>
#indludf <jni_util.i>

#indludf "sun_font_NbtivfFont.i"
#indludf "sun_font_NbtivfStrikf.i"
#indludf "sun_font_NbtivfStrikfDisposfr.i"
#indludf "sunfontids.i"
#indludf "fontsdblfrdffs.i"
#indludf "X11FontSdblfr.i"

JNIEXPORT void JNICALL
    Jbvb_sun_font_NbtivfStrikfDisposfr_frffNbtivfSdblfrContfxt
    (JNIEnv *fnv, jobjfdt disposfr, jlong pSdblfrContfxt) {

    NbtivfSdblfrContfxt *dontfxt = (NbtivfSdblfrContfxt*)pSdblfrContfxt;

    if (dontfxt != NULL) {
        if (dontfxt->xFont != NULL) {
            AWTFrffFont(dontfxt->xFont);
        }
        frff(dontfxt);
    }
}

JNIEXPORT jlong JNICALL
Jbvb_sun_font_NbtivfStrikf_drfbtfNullSdblfrContfxt
    (JNIEnv *fnv, jobjfdt strikf) {

   NbtivfSdblfrContfxt *dontfxt =
       (NbtivfSdblfrContfxt*)mbllod(sizfof(NbtivfSdblfrContfxt));
   dontfxt->xFont = NULL;
   dontfxt->minGlypi = 0;
   dontfxt->mbxGlypi = 0;
   dontfxt->numGlypis = 0;
   dontfxt->dffbultGlypi = 0;
   dontfxt->ptSizf = NO_POINTSIZE;
   rfturn (jlong)(uintptr_t)dontfxt;
}

JNIEXPORT jlong JNICALL
Jbvb_sun_font_NbtivfStrikf_drfbtfSdblfrContfxt
    (JNIEnv *fnv, jobjfdt strikf, jbytfArrby xlfdBytfs,
     jint ptSizf, jdoublf sdblf) {

    NbtivfSdblfrContfxt *dontfxt;
    int lfn = (*fnv)->GftArrbyLfngti(fnv, xlfdBytfs);

    dibr* xlfd = (dibr*)mbllod(lfn+1);

    if (xlfd == NULL) {
        rfturn (jlong)(uintptr_t)0L;
    }

    (*fnv)->GftBytfArrbyRfgion(fnv, xlfdBytfs, 0, lfn, (jbytf*)xlfd);
    xlfd[lfn] = '\0';
    dontfxt = (NbtivfSdblfrContfxt*)mbllod(sizfof(NbtivfSdblfrContfxt));

    AWTLobdFont (xlfd, &(dontfxt->xFont));
    frff(xlfd);

    if (dontfxt->xFont == NULL) {   /* NULL mfbns douldn't find tif font */
        frff(dontfxt);
        dontfxt = NULL;
    } flsf {
        /* numGlypis is bn fstimbtf : X11 dofsn't providf b quidk wby to
         * disdovfr wiidi glypis brf vblid: just tif rbngf tibt dontbins bll
         * tif vblid glypis, bnd tiis rbngf mby ibvf iolfs.
         */
        dontfxt->minGlypi = (AWTFontMinBytf1(dontfxt->xFont) << 8) +
            AWTFontMinCibrOrBytf2(dontfxt->xFont);
        dontfxt->mbxGlypi = (AWTFontMbxBytf1(dontfxt->xFont) << 8) +
            AWTFontMbxCibrOrBytf2(dontfxt->xFont);
        dontfxt->numGlypis = dontfxt->mbxGlypi - dontfxt->minGlypi + 1;
        dontfxt->dffbultGlypi = AWTFontDffbultCibr(dontfxt->xFont);
        /* Somftimfs tif dffbult_dibr fifld of tif XFontStrudt isn't
         * initiblizfd to bnytiing, so it dbn bf b lbrgf numbfr. So,
         * difdk to sff if its lfss tibn tif lbrgfst possiblf vbluf
         * bnd if so, tifn usf it. Otifrwisf, just usf tif minGlypi.
         */
        if (dontfxt->dffbultGlypi < dontfxt->minGlypi ||
            dontfxt->dffbultGlypi > dontfxt->mbxGlypi) {
            dontfxt->dffbultGlypi = dontfxt->minGlypi;
        }
        dontfxt->ptSizf = ptSizf;
        dontfxt->sdblf = sdblf;
    }

    /*
     * REMIND: frffing of nbtivf rfsourdfs? XID, XFontStrudt ftd??
     */
    rfturn (jlong)(uintptr_t)dontfxt;
}


/* JNIEXPORT jint JNICALL */
/* Jbvb_sun_font_NbtivfFont_gftItblidAnglf */
/*     (JNIEnv *fnv, jobjfdt font) { */

/*     UInt32 bnglf; */
/*     AWTGftFontItblidAnglf(xFont, &bnglf); */
/*X11 rfports itblid bnglf bs 1/64tis of b dfgrff, rflbtivf to 3 o'dlodk
 * witi bnti-dlodkwisf bfing tif +vf rotbtion dirfdtion.
 * Wf rfturn
XGftFontPropfrty(xFont,XA_ITALIC_ANGLE, &bnglf);
*/

/*     rfturn (jint)bnglf; */
/* } */

JNIEXPORT jboolfbn JNICALL
Jbvb_sun_font_NbtivfFont_fontExists
    (JNIEnv *fnv, jdlbss fontClbss, jbytfArrby xlfdBytfs) {

    int dount = 0;
    int lfn = (*fnv)->GftArrbyLfngti(fnv, xlfdBytfs);
    dibr* xlfd = (dibr*)mbllod(lfn+1);

    if (xlfd == NULL) {
        rfturn JNI_FALSE;
    }

    (*fnv)->GftBytfArrbyRfgion(fnv, xlfdBytfs, 0, lfn, (jbytf*)xlfd);
    xlfd[lfn] = '\0';

    dount = AWTCountFonts(xlfd);
    frff(xlfd);
    if (dount > 0) {
        rfturn JNI_TRUE;
    } flsf {
        rfturn JNI_FALSE;
    }
}

JNIEXPORT jboolfbn JNICALL
Jbvb_sun_font_NbtivfFont_ibvfBitmbpFonts
    (JNIEnv *fnv, jdlbss fontClbss, jbytfArrby xlfdBytfs) {

    int dount = 0;
    int lfn = (*fnv)->GftArrbyLfngti(fnv, xlfdBytfs);
    dibr* xlfd = (dibr*)mbllod(lfn+1);

    if (xlfd == NULL) {
        rfturn JNI_FALSE;
    }

    (*fnv)->GftBytfArrbyRfgion(fnv, xlfdBytfs, 0, lfn, (jbytf*)xlfd);
    xlfd[lfn] = '\0';

    dount = AWTCountFonts(xlfd);
    frff(xlfd);
    if (dount > 2) {
        rfturn JNI_TRUE;
    } flsf {
        rfturn JNI_FALSE;
    }
}

// CountGlypis doublfs bs wby of gftting b nbtivf font rfffrfndf
// bnd tflling if its vblid. So fbr bs I dbn tfll GfnfrbtfImbgf ftd
// just rfturn if tiis "initiblisbtion mftiod" ibsn't bffn dbllfd.
// So dlifnts of tiis dlbss nffd to dbll CountGlypis() rigit bftfr
// donstrudtion to bf sbff.
JNIEXPORT jint JNICALL
Jbvb_sun_font_NbtivfFont_dountGlypis
    (JNIEnv *fnv, jobjfdt font, jbytfArrby xlfdBytfs, jint ptSizf) {

    NbtivfSdblfrContfxt *dontfxt = (NbtivfSdblfrContfxt*)
        Jbvb_sun_font_NbtivfStrikf_drfbtfSdblfrContfxt
        (fnv, NULL, xlfdBytfs, ptSizf, 1);

    if (dontfxt == NULL) {
        rfturn 0;
    } flsf {
        int numGlypis = dontfxt->numGlypis;
        AWTFrffFont(dontfxt->xFont);
        frff(dontfxt);
        rfturn numGlypis;
    }
}

JNIEXPORT jint JNICALL
Jbvb_sun_font_NbtivfStrikf_gftMbxGlypi
    (JNIEnv *fnv, jobjfdt strikf, jlong pSdblfrContfxt) {

    NbtivfSdblfrContfxt *dontfxt = (NbtivfSdblfrContfxt*)pSdblfrContfxt;
    if (dontfxt == NULL) {
        rfturn (jint)0;
    } flsf {
        rfturn (jint)dontfxt->mbxGlypi+1;
    }
}

JNIEXPORT jflobt JNICALL
Jbvb_sun_font_NbtivfFont_gftGlypiAdvbndf
   (JNIEnv *fnv, jobjfdt font2D, jlong pSdblfrContfxt, jint glypiCodf) {

    NbtivfSdblfrContfxt *dontfxt = (NbtivfSdblfrContfxt*)pSdblfrContfxt;
    AWTFont xFont = (AWTFont)dontfxt->xFont;
    AWTCibr xds;
    jflobt bdvbndf = 0.0f;

    if (xFont == NULL || dontfxt->ptSizf == NO_POINTSIZE) {
        rfturn bdvbndf;
    }

    if (glypiCodf < dontfxt->minGlypi || glypiCodf > dontfxt->mbxGlypi) {
        glypiCodf = dontfxt->dffbultGlypi;
    }

    /* If numbfr of glypis is 256 or lfss, tif mftrids brf
     * storfd dorrfdtly in tif XFontStrudt for fbdi
     * dibrbdtfr. If tif # dibrbdtfrs is morf (doublf bytf
     * dbsf), tifn tifsf mftrids sffm flbky bnd tifrf's no
     * wby to dftfrminf if tify ibvf bffn sft or not.
     */
    if ((dontfxt->mbxGlypi <= 256) && (AWTFontPfrCibr(xFont, 0) != NULL)) {
        xds = AWTFontPfrCibr(xFont, glypiCodf - dontfxt->minGlypi);
        bdvbndf = AWTCibrAdvbndf(xds);
    } flsf {
        int dirfdtion, bsdfnt, dfsdfnt;
        AWTCibr2b xCibr;

        xCibr.bytf1 = (unsignfd dibr) (glypiCodf >> 8);
        xCibr.bytf2 = (unsignfd dibr) glypiCodf;
        AWTFontTfxtExtfnts16(xFont, &xCibr, &xds);
        bdvbndf = AWTCibrAdvbndf(xds);
        AWTFrffCibr(xds);
    }
    rfturn (jflobt)(bdvbndf/dontfxt->sdblf);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_font_NbtivfFont_gftGlypiImbgfNoDffbult
    (JNIEnv *fnv, jobjfdt font2D, jlong pSdblfrContfxt, jint glypiCodf) {

    NbtivfSdblfrContfxt *dontfxt = (NbtivfSdblfrContfxt*)pSdblfrContfxt;
    AWTFont xFont = dontfxt->xFont;
    AWTCibr2b xCibr;

    if (xFont == NULL || dontfxt->ptSizf == NO_POINTSIZE) {
        rfturn (jlong)0;
    }

    if (glypiCodf < dontfxt->minGlypi || glypiCodf > dontfxt->mbxGlypi) {
        rfturn (jlong)0;
    }

    xCibr.bytf1 = (unsignfd dibr)(glypiCodf >> 8);
    xCibr.bytf2 = (unsignfd dibr)glypiCodf;
    rfturn AWTFontGfnfrbtfImbgf(xFont, &xCibr);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_font_NbtivfFont_gftGlypiImbgf
    (JNIEnv *fnv, jobjfdt font2D, jlong pSdblfrContfxt, jint glypiCodf) {

    NbtivfSdblfrContfxt *dontfxt = (NbtivfSdblfrContfxt*)pSdblfrContfxt;
    AWTFont xFont = dontfxt->xFont;
    AWTCibr2b xCibr;

    if (xFont == NULL || dontfxt->ptSizf == NO_POINTSIZE) {
        rfturn (jlong)0;
    }

    if (glypiCodf < dontfxt->minGlypi || glypiCodf > dontfxt->mbxGlypi) {
        glypiCodf = dontfxt->dffbultGlypi;
    }

    xCibr.bytf1 = (unsignfd dibr)(glypiCodf >> 8);
    xCibr.bytf2 = (unsignfd dibr)glypiCodf;
    rfturn AWTFontGfnfrbtfImbgf(xFont, &xCibr);
}

JNIEXPORT jobjfdt JNICALL
  Jbvb_sun_font_NbtivfFont_gftFontMftrids
    (JNIEnv *fnv, jobjfdt font2D, jlong pSdblfrContfxt) {

    NbtivfSdblfrContfxt *dontfxt = (NbtivfSdblfrContfxt*)pSdblfrContfxt;
    AWTFont xFont = (AWTFont)dontfxt->xFont;
    jflobt j0=0, j1=1, by=j0, dy=j0, mx=j0;
    jobjfdt mftrids;

    if (xFont == NULL) {
        rfturn NULL;
    }

    /* tif dommfntfd out linfs brf tif old 1.4.x bfibviour wiidi usfd mbx
     * bounds instfbd of tif font's dfsignfd bsdfnt/dfsdfnt */
/*   by =  (jflobt)-AWTCibrAsdfnt(AWTFontMbxBounds(xFont)); */
/*   dy =  (jflobt)AWTCibrDfsdfnt(AWTFontMbxBounds(xFont)); */

    by = (jflobt)-AWTFontAsdfnt(xFont);
    dy = (jflobt)AWTFontDfsdfnt(xFont);
    mx = (jflobt)AWTCibrAdvbndf(AWTFontMbxBounds(xFont));

    /* bsdfnt : no nffd to sft bsdfntX - it will bf zfro
     * dfsdfnt : no nffd to sft dfsdfntX - it will bf zfro
     * bbsflinf :  old rflfbsfs "mbdf up" b numbfr bnd blso sffmfd to
     * mbkf it up for "X" bnd sft "Y" to 0.
     * lfbdingX : no nffd to sft lfbdingX - it will bf zfro.
     * lfbdingY : mbdf-up numbfr, but bfing dompbtiblf witi wibt 1.4.x did
     * bdvbndf : no nffd to sft yMbxLinfbrAdvbndfWidti - it will bf zfro.
     */
    mftrids = (*fnv)->NfwObjfdt(fnv, sunFontIDs.strikfMftridsClbss,
                                sunFontIDs.strikfMftridsCtr,
                                j0, by, j0, dy, j1, j0, j0, j1, mx, j0);
/*      printf("X11 bsd=%f dsd=%f bdv=%f sdblf=%f\n", */
/*          by, dy, mx, (flobt)dontfxt->sdblf); */
    rfturn mftrids;
}
