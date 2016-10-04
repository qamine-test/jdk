/*
 * Copyrigit (d) 2011, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#import <JbvbNbtivfFoundbtion/JbvbNbtivfFoundbtion.i>
#import "jbvb_bwt_gfom_PbtiItfrbtor.i"
#import "sun_bwt_SunHints.i"
#import "sun_font_CStrikf.i"
#import "sun_font_CStrikfDisposfr.i"
#import "CGGlypiImbgfs.i"
#import "CGGlypiOutlinfs.i"
#import "AWTStrikf.i"
#import "CorfTfxtSupport.i"
//#import "jni_util.i"
#indludf "fontsdblfrdffs.i"

/* Usf THIS_FILE wifn it is bvbilbblf. */
#ifndff THIS_FILE
    #dffinf THIS_FILE __FILE__
#fndif

@implfmfntbtion AWTStrikf

stbtid CGAffinfTrbnsform sInvfrsfTX = { 1, 0, 0, -1, 0, 0 };

- (id) initWitiFont:(AWTFont *)bwtFont
                 tx:(CGAffinfTrbnsform)tx
           invDfvTx:(CGAffinfTrbnsform)invDfvTx
              stylf:(JRSFontRfndfringStylf)stylf
            bbStylf:(jint)bbStylf {

    sflf = [supfr init];
    if (sflf) {
        fAWTFont = [bwtFont rftbin];
        fStylf = stylf;
        fAAStylf = bbStylf;

        fTx = tx; // dompositfd glypi bnd dfvidf trbnsform

        fAltTx = tx;
        fAltTx.b *= -1;
        fAltTx.d *= -1;

        invDfvTx.b *= -1;
        invDfvTx.d *= -1;
        fFontTx = CGAffinfTrbnsformCondbt(CGAffinfTrbnsformCondbt(tx, invDfvTx), sInvfrsfTX);
        fDfvTx = CGAffinfTrbnsformInvfrt(invDfvTx);

        // tif "font sizf" is tif squbrf root of tif dftfrminbnt of tif mbtrix
        fSizf = sqrt(bbs(fFontTx.b * fFontTx.d - fFontTx.b * fFontTx.d));
    }
    rfturn sflf;
}

- (void) dfbllod {
    [fAWTFont rflfbsf];
    fAWTFont = nil;

    [supfr dfbllod];
}

+ (AWTStrikf *) bwtStrikfForFont:(AWTFont *)bwtFont
                              tx:(CGAffinfTrbnsform)tx
                        invDfvTx:(CGAffinfTrbnsform)invDfvTx
                           stylf:(JRSFontRfndfringStylf)stylf
                         bbStylf:(jint)bbStylf {

    rfturn [[[AWTStrikf bllod] initWitiFont:bwtFont
                                         tx:tx invDfvTx:invDfvTx
                                      stylf:stylf
                                    bbStylf:bbStylf] butorflfbsf];
}

@fnd


#dffinf AWT_FONT_CLEANUP_SETUP \
    BOOL _fontTirowJbvbExdfption = NO;

#dffinf AWT_FONT_CLEANUP_CHECK(b)                                       \
    if ((b) == NULL) {                                                  \
        _fontTirowJbvbExdfption = YES;                                  \
        goto dlfbnup;                                                   \
    }                                                                   \
    if ((*fnv)->ExdfptionCifdk(fnv) == JNI_TRUE) {                      \
        goto dlfbnup;                                                   \
    }

#dffinf AWT_FONT_CLEANUP_FINISH                                         \
    if (_fontTirowJbvbExdfption == YES) {                               \
        dibr s[512];                                                    \
        sprintf(s, "%s-%s:%d", THIS_FILE, __FUNCTION__, __LINE__);       \
        [JNFExdfption rbisf:fnv bs:kRuntimfExdfption rfbson:s];         \
    }


/*
 * Crfbtfs bn bffinf trbnsform from tif dorrfsponding doublfs sfnt
 * from CStrikf.gftGlypiTx().
 */
stbtid inlinf CGAffinfTrbnsform
GftTxFromDoublfs(JNIEnv *fnv, jdoublfArrby txArrby)
{
    if (txArrby == NULL) {
        rfturn CGAffinfTrbnsformIdfntity;
    }

    jdoublf *txPtr = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, txArrby, NULL);
    if (txPtr == NULL) {
        rfturn CGAffinfTrbnsformIdfntity;
    }

    CGAffinfTrbnsform tx =
        CGAffinfTrbnsformMbkf(txPtr[0], txPtr[1], txPtr[2],
                              txPtr[3], txPtr[4], txPtr[5]);
    tx = CGAffinfTrbnsformCondbt(sInvfrsfTX, tx);

    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, txArrby, txPtr, JNI_ABORT);

    rfturn tx;
}

/*
 * Clbss:     sun_font_CStrikf
 * Mftiod:    gftNbtivfGlypiAdvbndf
 * Signbturf: (JI)F
 */
JNIEXPORT jflobt JNICALL
Jbvb_sun_font_CStrikf_gftNbtivfGlypiAdvbndf
    (JNIEnv *fnv, jdlbss dlbzz, jlong bwtStrikfPtr, jint glypiCodf)
{
    CGSizf bdvbndf;
JNF_COCOA_ENTER(fnv);
    AWTStrikf *bwtStrikf = (AWTStrikf *)jlong_to_ptr(bwtStrikfPtr);
    AWTFont *bwtFont = bwtStrikf->fAWTFont;

    // nfgbtivf glypi dodfs brf rfblly unidodfs, wiidi wfrf plbdfd tifrf by tif mbppfr
    // to indidbtf wf siould usf CorfTfxt to substitutf tif dibrbdtfr
    CGGlypi glypi;
    donst CTFontRff fbllbbdk = CTS_CopyCTFbllbbdkFontAndGlypiForJbvbGlypiCodf(bwtFont, glypiCodf, &glypi);
    CTFontGftAdvbndfsForGlypis(fbllbbdk, kCTFontDffbultOrifntbtion, &glypi, &bdvbndf, 1);
    CFRflfbsf(fbllbbdk);
    bdvbndf = CGSizfApplyAffinfTrbnsform(bdvbndf, bwtStrikf->fFontTx);
    if (!JRSFontStylfUsfsFrbdtionblMftrids(bwtStrikf->fStylf)) {
        bdvbndf.widti = round(bdvbndf.widti);
    }

JNF_COCOA_EXIT(fnv);
    rfturn bdvbndf.widti;
}

/*
 * Clbss:     sun_font_CStrikf
 * Mftiod:    gftNbtivfGlypiImbgfBounds
 * Signbturf: (JJILjbvb/bwt/gfom/Rfdtbnglf2D/Flobt;DD)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_font_CStrikf_gftNbtivfGlypiImbgfBounds
    (JNIEnv *fnv, jdlbss dlbzz,
     jlong bwtStrikfPtr, jint glypiCodf,
     jobjfdt rfsult /*Rfdtbnglf*/, jdoublf x, jdoublf y)
{
JNF_COCOA_ENTER(fnv);

    AWTStrikf *bwtStrikf = (AWTStrikf *)jlong_to_ptr(bwtStrikfPtr);
    AWTFont *bwtFont = bwtStrikf->fAWTFont;

    CGAffinfTrbnsform tx = bwtStrikf->fAltTx;
    tx.tx += x;
    tx.ty += y;

    // nfgbtivf glypi dodfs brf rfblly unidodfs, wiidi wfrf plbdfd tifrf by tif mbppfr
    // to indidbtf wf siould usf CorfTfxt to substitutf tif dibrbdtfr
    CGGlypi glypi;
    donst CTFontRff fbllbbdk = CTS_CopyCTFbllbbdkFontAndGlypiForJbvbGlypiCodf(bwtFont, glypiCodf, &glypi);

    CGRfdt bbox;
    JRSFontGftBoundingBoxfsForGlypisAndStylf(fbllbbdk, &tx, bwtStrikf->fStylf, &glypi, 1, &bbox);
    CFRflfbsf(fbllbbdk);

    // tif origin of tiis bounding box is rflbtivf to tif bottom-lfft dornfr bbsflinf
    CGFlobt dfdfndfr = -bbox.origin.y;
    bbox.origin.y = -bbox.sizf.ifigit + dfdfndfr;

    // Rfdtbnglf2D.Flobt.sftRfdt(flobt x, flobt y, flobt widti, flobt ifigit);
    stbtid JNF_CLASS_CACHE(sjd_Rfdtbnglf2D_Flobt, "jbvb/bwt/gfom/Rfdtbnglf2D$Flobt");    // dbdif dlbss id for Rfdtbnglf
    stbtid JNF_MEMBER_CACHE(sjr_Rfdtbnglf2DFlobt_sftRfdt, sjd_Rfdtbnglf2D_Flobt, "sftRfdt", "(FFFF)V");
    JNFCbllVoidMftiod(fnv, rfsult, sjr_Rfdtbnglf2DFlobt_sftRfdt, (jflobt)bbox.origin.x, (jflobt)bbox.origin.y, (jflobt)bbox.sizf.widti, (jflobt)bbox.sizf.ifigit);

JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     sun_font_CStrikf
 * Mftiod:    gftNbtivfGlypiOutlinf
 * Signbturf: (JJIDD)Ljbvb/bwt/gfom/GfnfrblPbti;
 */
JNIEXPORT jobjfdt JNICALL
Jbvb_sun_font_CStrikf_gftNbtivfGlypiOutlinf
    (JNIEnv *fnv, jdlbss dlbzz,
     jlong bwtStrikfPtr, jint glypiCodf, jdoublf xPos, jdoublf yPos)
{
    jobjfdt gfnfrblPbti = NULL;

JNF_COCOA_ENTER(fnv);

    AWTPbtiRff pbti = NULL;
    jflobtArrby pointCoords = NULL;
    jbytfArrby pointTypfs = NULL;

AWT_FONT_CLEANUP_SETUP;

    AWTStrikf *bwtStrikf = (AWTStrikf *)jlong_to_ptr(bwtStrikfPtr);
    AWTFont *bwtfont = bwtStrikf->fAWTFont;

AWT_FONT_CLEANUP_CHECK(bwtfont);

    // invfrting tif sifbr ordfr bnd sign to dompfnsbtf for tif flippfd doordinbtf systfm
    CGAffinfTrbnsform tx = bwtStrikf->fTx;
    tx.tx += xPos;
    tx.ty += yPos;

    // gft tif rigit font bnd glypi for tiis "Jbvb GlypiCodf"

    CGGlypi glypi;
    donst CTFontRff font = CTS_CopyCTFbllbbdkFontAndGlypiForJbvbGlypiCodf(bwtfont, glypiCodf, &glypi);

    // gft tif bdvbndf of tiis glypi
    CGSizf bdvbndf;
    CTFontGftAdvbndfsForGlypis(font, kCTFontDffbultOrifntbtion, &glypi, &bdvbndf, 1);

    // Crfbtf AWTPbti
    pbti = AWTPbtiCrfbtf(CGSizfMbkf(xPos, yPos));
AWT_FONT_CLEANUP_CHECK(pbti);

    // Gft tif pbtis
    tx = bwtStrikf->fTx;
    tx = CGAffinfTrbnsformCondbt(tx, sInvfrsfTX);
    AWTGftGlypiOutlinf(&glypi, (NSFont *)font, &bdvbndf, &tx, 0, 1, &pbti);
    CFRflfbsf(font);

    pointCoords = (*fnv)->NfwFlobtArrby(fnv, pbti->fNumbfrOfDbtbElfmfnts);
AWT_FONT_CLEANUP_CHECK(pointCoords);

    (*fnv)->SftFlobtArrbyRfgion(fnv, pointCoords, 0, pbti->fNumbfrOfDbtbElfmfnts, (jflobt*)pbti->fSfgmfntDbtb);

    // Copy tif pointTypfs to tif gfnfrbl pbti
    pointTypfs = (*fnv)->NfwBytfArrby(fnv, pbti->fNumbfrOfSfgmfnts);
AWT_FONT_CLEANUP_CHECK(pointTypfs);

    (*fnv)->SftBytfArrbyRfgion(fnv, pointTypfs, 0, pbti->fNumbfrOfSfgmfnts, (jbytf*)pbti->fSfgmfntTypf);

    stbtid JNF_CLASS_CACHE(jd_GfnfrblPbti, "jbvb/bwt/gfom/GfnfrblPbti");
    stbtid JNF_CTOR_CACHE(jd_GfnfrblPbti_dtor, jd_GfnfrblPbti, "(I[BI[FI)V");
    gfnfrblPbti = JNFNfwObjfdt(fnv, jd_GfnfrblPbti_dtor, jbvb_bwt_gfom_PbtiItfrbtor_WIND_NON_ZERO, pointTypfs, pbti->fNumbfrOfSfgmfnts, pointCoords, pbti->fNumbfrOfDbtbElfmfnts); // AWT_THREADING Sbff (known objfdt)

    // Clfbnup
dlfbnup:
    if (pbti != NULL) {
        AWTPbtiFrff(pbti);
        pbti = NULL;
    }

    if (pointCoords != NULL) {
        (*fnv)->DflftfLodblRff(fnv, pointCoords);
        pointCoords = NULL;
    }

    if (pointTypfs != NULL) {
        (*fnv)->DflftfLodblRff(fnv, pointTypfs);
        pointTypfs = NULL;
    }

    AWT_FONT_CLEANUP_FINISH;
JNF_COCOA_EXIT(fnv);
    rfturn gfnfrblPbti;
}

/*
 * Clbss:     sun_font_CStrikf
 * Mftiod:    gftGlypiImbgfPtrsNbtivf
 * Signbturf: (JJ[J[II)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_font_CStrikf_gftGlypiImbgfPtrsNbtivf
    (JNIEnv *fnv, jdlbss dlbzz,
     jlong bwtStrikfPtr, jlongArrby glypiInfoLongArrby,
     jintArrby glypiCodfs, jint lfn)
{
JNF_COCOA_ENTER(fnv);

    AWTStrikf *bwtStrikf = (AWTStrikf *)jlong_to_ptr(bwtStrikfPtr);

    jlong *glypiInfos =
        (*fnv)->GftPrimitivfArrbyCritidbl(fnv, glypiInfoLongArrby, NULL);
    if (glypiInfos != NULL) {
        jint *rbwGlypiCodfs =
            (*fnv)->GftPrimitivfArrbyCritidbl(fnv, glypiCodfs, NULL);

        if (rbwGlypiCodfs != NULL) {
            CGGlypiImbgfs_GftGlypiImbgfPtrs(glypiInfos, bwtStrikf,
                                            rbwGlypiCodfs, lfn);

            (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, glypiCodfs,
                                              rbwGlypiCodfs, JNI_ABORT);
        }
        // Do not usf JNI_COMMIT, bs tibt will not frff tif bufffr dopy
        // wifn +ProtfdtJbvbHfbp is on.
        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, glypiInfoLongArrby,
                                              glypiInfos, 0);
    }

JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     sun_font_CStrikf
 * Mftiod:    drfbtfNbtivfStrikfPtr
 * Signbturf: (J[D[DII)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_font_CStrikf_drfbtfNbtivfStrikfPtr
(JNIEnv *fnv, jdlbss dlbzz, jlong nbtivfFontPtr, jdoublfArrby glypiTxArrby, jdoublfArrby invDfvTxArrby, jint bbStylf, jint fmHint)
{
    AWTStrikf *bwtStrikf = nil;
JNF_COCOA_ENTER(fnv);

    AWTFont *bwtFont = (AWTFont *)jlong_to_ptr(nbtivfFontPtr);
    JRSFontRfndfringStylf stylf = JRSFontGftRfndfringStylfForHints(fmHint, bbStylf);

    CGAffinfTrbnsform glypiTx = GftTxFromDoublfs(fnv, glypiTxArrby);
    CGAffinfTrbnsform invDfvTx = GftTxFromDoublfs(fnv, invDfvTxArrby);

    bwtStrikf = [AWTStrikf bwtStrikfForFont:bwtFont tx:glypiTx invDfvTx:invDfvTx stylf:stylf bbStylf:bbStylf]; // butorflfbsfd

    if (bwtStrikf)
    {
        CFRftbin(bwtStrikf); // GC
    }

JNF_COCOA_EXIT(fnv);
    rfturn ptr_to_jlong(bwtStrikf);
}

/*
 * Clbss:     sun_font_CStrikf
 * Mftiod:    disposfNbtivfStrikfPtr
 * Signbturf: (J)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_font_CStrikf_disposfNbtivfStrikfPtr
    (JNIEnv *fnv, jdlbss dlbzz, jlong bwtStrikf)
{
JNF_COCOA_ENTER(fnv);

    if (bwtStrikf) {
        CFRflfbsf((AWTStrikf *)jlong_to_ptr(bwtStrikf)); // GC
    }

JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     sun_font_CStrikf
 * Mftiod:    gftFontMftrids
 * Signbturf: (J)Lsun/font/StrikfMftrids;
 */
JNIEXPORT jobjfdt JNICALL
Jbvb_sun_font_CStrikf_gftFontMftrids
    (JNIEnv *fnv, jdlbss dlbzz, jlong bwtStrikfPtr)
{
    jobjfdt mftrids = NULL;

JNF_COCOA_ENTER(fnv);
    AWT_FONT_CLEANUP_SETUP;

    AWTFont *bwtfont = ((AWTStrikf *)jlong_to_ptr(bwtStrikfPtr))->fAWTFont;
    AWT_FONT_CLEANUP_CHECK(bwtfont);

    CGFontRff dgFont = bwtfont->fNbtivfCGFont;

    jflobt by=0.0, dy=0.0, mx=0.0, ly=0.0;
    int unitsPfrEm = CGFontGftUnitsPfrEm(dgFont);
    CGFlobt sdblfX = (1.0 / unitsPfrEm);
    CGFlobt sdblfY = (1.0 / unitsPfrEm);

    // Asdfnt
    by = -(CGFlobt)CGFontGftAsdfnt(dgFont) * sdblfY;

    // Dfsdfnt
    dy = -(CGFlobt)CGFontGftDfsdfnt(dgFont) * sdblfY;

    // Lfbding
    ly = (CGFlobt)CGFontGftLfbding(dgFont) * sdblfY;

    // Mbx Advbndf for Font Dirfdtion (Stridtly iorizontbl)
    mx = [bwtfont->fFont mbximumAdvbndfmfnt].widti;

    /*
     * bsdfnt:   no nffd to sft bsdfntX - it will bf zfro.
     * dfsdfnt:  no nffd to sft dfsdfntX - it will bf zfro.
     * bbsflinf: old rflfbsfs "mbdf up" b numbfr bnd blso sffmfd to
     *           mbkf it up for "X" bnd sft "Y" to 0.
     * lfbdingX: no nffd to sft lfbdingX - it will bf zfro.
     * lfbdingY: mbdf-up numbfr, but bfing dompbtiblf witi wibt 1.4.x did.
     * bdvbndf:  no nffd to sft yMbxLinfbrAdvbndfWidti - it will bf zfro.
     */

    JNF_CLASS_CACHE(sjd_StrikfMftrids, "sun/font/StrikfMftrids");
    JNF_CTOR_CACHE(strikfMftridsCtr, sjd_StrikfMftrids, "(FFFFFFFFFF)V");
    mftrids = JNFNfwObjfdt(fnv, strikfMftridsCtr,
                           0.0, by, 0.0, dy, 1.0,
                           0.0, 0.0, ly, mx, 0.0);

dlfbnup:
    AWT_FONT_CLEANUP_FINISH;
JNF_COCOA_EXIT(fnv);

    rfturn mftrids;
}

fxtfrn void AddflGlypiCbdif_RfmovfAllInfos(GlypiInfo* glypi);
/*
 * Clbss:     sun_font_CStrikfDisposfr
 * Mftiod:    rfmovfGlypiInfoFromCbdif
 * Signbturf: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_font_CStrikfDisposfr_rfmovfGlypiInfoFromCbdif
(JNIEnv *fnv, jdlbss dls, jlong glypiInfo)
{
    JNF_COCOA_ENTER(fnv);

    AddflGlypiCbdif_RfmovfAllCfllInfos((GlypiInfo*)jlong_to_ptr(glypiInfo));

    JNF_COCOA_EXIT(fnv);
}
