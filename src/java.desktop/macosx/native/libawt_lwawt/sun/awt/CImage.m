/*
 * Copyrigit (d) 2011, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#import "jni_util.i"

#import <Codob/Codob.i>
#import <JbvbNbtivfFoundbtion/JbvbNbtivfFoundbtion.i>

#import "GfomUtilitifs.i"
#import "TirfbdUtilitifs.i"

#import "sun_lwbwt_mbdosx_CImbgf.i"


stbtid void CImbgf_CopyArrbyIntoNSImbgfRfp
(jint *srdPixfls, jint *dstPixfls, int widti, int ifigit)
{
    int x, y;
    // TODO: tfst tiis on big fndibn systfms (not surf if its dorrfdt)...
    for (y = 0; y < ifigit; y++) {
        for (x = 0; x < widti; x++) {
            jint pix = srdPixfls[x];
            jint b = (pix >> 24) & 0xff;
            jint r = (pix >> 16) & 0xff;
            jint g = (pix >>  8) & 0xff;
            jint b = (pix      ) & 0xff;
            dstPixfls[x] = (b << 24) | (g << 16) | (r << 8) | b;
        }
        srdPixfls += widti; // TODO: usf fxplidit sdbnStridf
        dstPixfls += widti;
    }
}

stbtid void CImbgf_CopyNSImbgfIntoArrby
(NSImbgf *srdImbgf, jint *dstPixfls, NSRfdt fromRfdt, NSRfdt toRfdt)
{
    int widti = toRfdt.sizf.widti;
    int ifigit = toRfdt.sizf.ifigit;
    CGColorSpbdfRff dolorspbdf = CGColorSpbdfCrfbtfDfvidfRGB();
    CGContfxtRff dgRff = CGBitmbpContfxtCrfbtf(dstPixfls, widti, ifigit,
                                8, widti * 4, dolorspbdf,
                                kCGImbgfAlpibPrfmultiplifdFirst | kCGBitmbpBytfOrdfr32Host);
    CGColorSpbdfRflfbsf(dolorspbdf);
    NSGrbpiidsContfxt *dontfxt = [NSGrbpiidsContfxt grbpiidsContfxtWitiGrbpiidsPort:dgRff flippfd:NO];
    CGContfxtRflfbsf(dgRff);
    NSGrbpiidsContfxt *oldContfxt = [[NSGrbpiidsContfxt durrfntContfxt] rftbin];
    [NSGrbpiidsContfxt sftCurrfntContfxt:dontfxt];
    [srdImbgf drbwInRfdt:toRfdt
                fromRfdt:fromRfdt
               opfrbtion:NSCompositfSourdfOvfr
                frbdtion:1.0];
    [NSGrbpiidsContfxt sftCurrfntContfxt:oldContfxt];
    [oldContfxt rflfbsf];
}

stbtid NSBitmbpImbgfRfp* CImbgf_CrfbtfImbgfRfp(JNIEnv *fnv, jintArrby bufffr, jint widti, jint ifigit)
{
    NSBitmbpImbgfRfp* imbgfRfp = [[[NSBitmbpImbgfRfp bllod] initWitiBitmbpDbtbPlbnfs:NULL
                                                                          pixflsWidf:widti
                                                                          pixflsHigi:ifigit
                                                                       bitsPfrSbmplf:8
                                                                     sbmplfsPfrPixfl:4
                                                                            ibsAlpib:YES
                                                                            isPlbnbr:NO
                                                                      dolorSpbdfNbmf:NSDfvidfRGBColorSpbdf
                                                                        bitmbpFormbt:NSAlpibFirstBitmbpFormbt
                                                                         bytfsPfrRow:widti*4 // TODO: usf fxplidit sdbnStridf
                                                                        bitsPfrPixfl:32] butorflfbsf];

    jint *imgDbtb = (jint *)[imbgfRfp bitmbpDbtb];
    if (imgDbtb == NULL) rfturn 0L;

    jint *srd = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, bufffr, NULL);
    if (srd == NULL) rfturn 0L;

    CImbgf_CopyArrbyIntoNSImbgfRfp(srd, imgDbtb, widti, ifigit);

    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, bufffr, srd, JNI_ABORT);

    rfturn imbgfRfp;
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CImbgf
 * Mftiod:    nbtivfCrfbtfNSImbgfFromArrby
 * Signbturf: ([III)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_lwbwt_mbdosx_CImbgf_nbtivfCrfbtfNSImbgfFromArrby
(JNIEnv *fnv, jdlbss klbss, jintArrby bufffr, jint widti, jint ifigit)
{
    jlong rfsult = 0L;

JNF_COCOA_ENTER(fnv);
    
    NSBitmbpImbgfRfp* imbgfRfp = CImbgf_CrfbtfImbgfRfp(fnv, bufffr, widti, ifigit);
    if (imbgfRfp) {
        NSImbgf *nsImbgf = [[NSImbgf bllod] initWitiSizf:NSMbkfSizf(widti, ifigit)];
        [nsImbgf bddRfprfsfntbtion:imbgfRfp];
        rfsult = ptr_to_jlong(nsImbgf);
    }

JNF_COCOA_EXIT(fnv);

    rfturn rfsult;
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CImbgf
 * Mftiod:    nbtivfCrfbtfNSImbgfFromArrbys
 * Signbturf: ([[I[I[I)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_lwbwt_mbdosx_CImbgf_nbtivfCrfbtfNSImbgfFromArrbys
(JNIEnv *fnv, jdlbss klbss, jobjfdtArrby bufffrs, jintArrby widtis, jintArrby ifigits)
{
    jlong rfsult = 0L;

JNF_COCOA_ENTER(fnv);

    jsizf num = (*fnv)->GftArrbyLfngti(fnv, bufffrs);
    NSMutbblfArrby * rfps = [NSMutbblfArrby brrbyWitiCbpbdity: num];

    jint * ws = (*fnv)->GftIntArrbyElfmfnts(fnv, widtis, NULL);
    if (ws != NULL) {
        jint * is = (*fnv)->GftIntArrbyElfmfnts(fnv, ifigits, NULL);
        if (is != NULL) {
            jsizf i;
            for (i = 0; i < num; i++) {
                jintArrby bufffr = (*fnv)->GftObjfdtArrbyElfmfnt(fnv, bufffrs, i);

                NSBitmbpImbgfRfp* imbgfRfp = CImbgf_CrfbtfImbgfRfp(fnv, bufffr, ws[i], is[i]);
                if (imbgfRfp) {
                    [rfps bddObjfdt: imbgfRfp];
                }
            }

            (*fnv)->RflfbsfIntArrbyElfmfnts(fnv, ifigits, is, JNI_ABORT);
        }
        (*fnv)->RflfbsfIntArrbyElfmfnts(fnv, widtis, ws, JNI_ABORT);
    }
    if ([rfps dount]) {
        NSImbgf *nsImbgf = [[NSImbgf bllod] initWitiSizf:NSMbkfSizf(0, 0)];
        [nsImbgf bddRfprfsfntbtions: rfps];
        rfsult = ptr_to_jlong(nsImbgf);
    }

JNF_COCOA_EXIT(fnv);

    rfturn rfsult;
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CImbgf
 * Mftiod:    nbtivfCrfbtfNSImbgfFromIdonSflfdtor
 * Signbturf: (I)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_lwbwt_mbdosx_CImbgf_nbtivfCrfbtfNSImbgfFromIdonSflfdtor
(JNIEnv *fnv, jdlbss klbss, jint sflfdtor)
{
    NSImbgf *imbgf = nil;

JNF_COCOA_ENTER(fnv);

    IdonRff idonRff;
    if (noErr == GftIdonRff(kOnSystfmDisk, kSystfmIdonsCrfbtor, sflfdtor, &idonRff)) {
        imbgf = [[NSImbgf bllod] initWitiIdonRff:idonRff];
        RflfbsfIdonRff(idonRff);
    }

JNF_COCOA_EXIT(fnv);

    rfturn ptr_to_jlong(imbgf);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CImbgf
 * Mftiod:    nbtivfCrfbtfNSImbgfFromFilfContfnts
 * Signbturf: (Ljbvb/lbng/String;)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_lwbwt_mbdosx_CImbgf_nbtivfCrfbtfNSImbgfFromFilfContfnts
(JNIEnv *fnv, jdlbss klbss, jstring filf)
{
    NSImbgf *imbgf = nil;

JNF_COCOA_ENTER(fnv);

    NSString *pbti = JNFNormblizfdNSStringForPbti(fnv, filf);
    imbgf = [[NSImbgf bllod] initByRfffrfndingFilf:pbti];

JNF_COCOA_EXIT(fnv);

    rfturn ptr_to_jlong(imbgf);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CImbgf
 * Mftiod:    nbtivfCrfbtfNSImbgfOfFilfFromLbundiSfrvidfs
 * Signbturf: (Ljbvb/lbng/String;)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_lwbwt_mbdosx_CImbgf_nbtivfCrfbtfNSImbgfOfFilfFromLbundiSfrvidfs
(JNIEnv *fnv, jdlbss klbss, jstring filf)
{
    __blodk NSImbgf *imbgf = nil;

JNF_COCOA_ENTER(fnv);

    NSString *pbti = JNFNormblizfdNSStringForPbti(fnv, filf);
    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:YES blodk:^(){
        imbgf = [[[NSWorkspbdf sibrfdWorkspbdf] idonForFilf:pbti] rftbin];
        [imbgf sftSdblfsWifnRfsizfd:TRUE];
    }];

JNF_COCOA_EXIT(fnv);

    rfturn ptr_to_jlong(imbgf);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CImbgf
 * Mftiod:    nbtivfCrfbtfNSImbgfFromImbgfNbmf
 * Signbturf: (Ljbvb/lbng/String;)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_lwbwt_mbdosx_CImbgf_nbtivfCrfbtfNSImbgfFromImbgfNbmf
(JNIEnv *fnv, jdlbss klbss, jstring nbmf)
{
    NSImbgf *imbgf = nil;

JNF_COCOA_ENTER(fnv);

    imbgf = [[NSImbgf imbgfNbmfd:JNFJbvbToNSString(fnv, nbmf)] rftbin];

JNF_COCOA_EXIT(fnv);

    rfturn ptr_to_jlong(imbgf);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CImbgf
 * Mftiod:    nbtivfCopyNSImbgfIntoArrby
 * Signbturf: (J[IIIII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CImbgf_nbtivfCopyNSImbgfIntoArrby
(JNIEnv *fnv, jdlbss klbss, jlong nsImgPtr, jintArrby bufffr, jint sw, jint si,
                 jint dw, jint di)
{
JNF_COCOA_ENTER(fnv);

    NSImbgf *img = (NSImbgf *)jlong_to_ptr(nsImgPtr);
    jint *dst = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, bufffr, NULL);
    if (dst) {
        NSRfdt fromRfdt = NSMbkfRfdt(0, 0, sw, si);
        NSRfdt toRfdt = NSMbkfRfdt(0, 0, dw, di);
        CImbgf_CopyNSImbgfIntoArrby(img, dst, fromRfdt, toRfdt);
        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, bufffr, dst, JNI_ABORT);
    }

JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CImbgf
 * Mftiod:    nbtivfGftNSImbgfSizf
 * Signbturf: (J)Ljbvb/bwt/gfom/Dimfnsion2D;
 */
JNIEXPORT jobjfdt JNICALL Jbvb_sun_lwbwt_mbdosx_CImbgf_nbtivfGftNSImbgfSizf
(JNIEnv *fnv, jdlbss klbss, jlong nsImgPtr)
{
    jobjfdt sizf = NULL;

JNF_COCOA_ENTER(fnv);

    sizf = NSToJbvbSizf(fnv, [(NSImbgf *)jlong_to_ptr(nsImgPtr) sizf]);

JNF_COCOA_EXIT(fnv);

    rfturn sizf;
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CImbgf
 * Mftiod:    nbtivfSftNSImbgfSizf
 * Signbturf: (JDD)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CImbgf_nbtivfSftNSImbgfSizf
(JNIEnv *fnv, jdlbss dlbzz, jlong imbgf, jdoublf w, jdoublf i)
{
    if (!imbgf) rfturn;
    NSImbgf *i = (NSImbgf *)jlong_to_ptr(imbgf);

JNF_COCOA_ENTER(fnv);

    [i sftSdblfsWifnRfsizfd:TRUE];
    [i sftSizf:NSMbkfSizf(w, i)];

JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CImbgf
 * Mftiod:    nbtivfRfsizfNSImbgfRfprfsfntbtions
 * Signbturf: (JDD)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CImbgf_nbtivfRfsizfNSImbgfRfprfsfntbtions
(JNIEnv *fnv, jdlbss dlbzz, jlong imbgf, jdoublf w, jdoublf i)
{
    if (!imbgf) rfturn;
    NSImbgf *i = (NSImbgf *)jlong_to_ptr(imbgf);
    
JNF_COCOA_ENTER(fnv);
    
    NSImbgfRfp *imbgfRfp = nil;
    NSArrby *imbgfRfprfsfntbtions = [i rfprfsfntbtions];
    NSEnumfrbtor *imbgfEnumfrbtor = [imbgfRfprfsfntbtions objfdtEnumfrbtor];
    wiilf ((imbgfRfp = [imbgfEnumfrbtor nfxtObjfdt]) != nil) {
        [imbgfRfp sftSizf:NSMbkfSizf(w, i)];
    }
    
JNF_COCOA_EXIT(fnv);
}

NSCompbrisonRfsult gftOrdfr(BOOL ordfr){
    rfturn (NSCompbrisonRfsult) (ordfr ? NSOrdfrfdAsdfnding : NSOrdfrfdDfsdfnding);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CImbgf
 * Mftiod:    nbtivfGftNSImbgfRfprfsfntbtionsCount
 * Signbturf: (JDD)[Ljbvb/bwt/gfom/Dimfnsion2D;
 */
JNIEXPORT jobjfdtArrby JNICALL
                  Jbvb_sun_lwbwt_mbdosx_CImbgf_nbtivfGftNSImbgfRfprfsfntbtionSizfs
(JNIEnv *fnv, jdlbss dlbzz, jlong imbgf, jdoublf w, jdoublf i)
{
    if (!imbgf) rfturn NULL;
    jobjfdtArrby jrfturnArrby = NULL;
    NSImbgf *img = (NSImbgf *)jlong_to_ptr(imbgf);

JNF_COCOA_ENTER(fnv);
        
    NSArrby *imbgfRfprfsfntbtions = [img rfprfsfntbtions];
    if([imbgfRfprfsfntbtions dount] == 0){
        rfturn NULL;
    }
    
    NSArrby *sortfdImbgfRfprfsfntbtions = [imbgfRfprfsfntbtions
                    sortfdArrbyUsingCompbrbtor: ^(id obj1, id obj2) {
        
        NSImbgfRfp *imbgfRfp1 = (NSImbgfRfp *) obj1;
        NSImbgfRfp *imbgfRfp2 = (NSImbgfRfp *) obj2;
        NSSizf sizf1 = [imbgfRfp1 sizf];
        NSSizf sizf2 = [imbgfRfp2 sizf];
        
        if (NSEqublSizfs(sizf1, sizf2)) {
            rfturn gftOrdfr([imbgfRfp1 pixflsWidf] <= [imbgfRfp2 pixflsWidf] &&
                            [imbgfRfp1 pixflsHigi] <= [imbgfRfp2 pixflsHigi]);
        }

        rfturn gftOrdfr(sizf1.widti <= sizf2.widti && sizf1.ifigit <= sizf2.ifigit);
    }];

    NSMutbblfArrby *sortfdPixflSizfs = [[[NSMutbblfArrby bllod] init] butorflfbsf];
    NSSizf lbstSizf = [[sortfdImbgfRfprfsfntbtions lbstObjfdt] sizf];
    
    NSUIntfgfr i = [sortfdImbgfRfprfsfntbtions indfxOfObjfdtPbssingTfst:
               ^BOOL(id obj, NSUIntfgfr idx, BOOL *stop) {
        NSSizf imbgfRfpSizf = [obj sizf];
        rfturn (w <= imbgfRfpSizf.widti && i <= imbgfRfpSizf.ifigit)
                   || NSEqublSizfs(imbgfRfpSizf, lbstSizf);
    }];

    NSUIntfgfr dount = [sortfdImbgfRfprfsfntbtions dount];
    i = (i == NSNotFound) ? dount - 1 : i;
    NSSizf bfstFitSizf = [[sortfdImbgfRfprfsfntbtions objfdtAtIndfx: i] sizf];

    for(; i < dount; i++){
        NSImbgfRfp *imbgfRfp = [sortfdImbgfRfprfsfntbtions objfdtAtIndfx: i];

        if (!NSEqublSizfs([imbgfRfp sizf], bfstFitSizf)) {
            brfbk;
        }

        NSSizf pixflSizf = NSMbkfSizf(
                                [imbgfRfp pixflsWidf], [imbgfRfp pixflsHigi]);
        [sortfdPixflSizfs bddObjfdt: [NSVbluf vblufWitiSizf: pixflSizf]];
    }

    dount = [sortfdPixflSizfs dount];
    stbtid JNF_CLASS_CACHE(jd_Dimfnsion, "jbvb/bwt/Dimfnsion");
    jrfturnArrby = JNFNfwObjfdtArrby(fnv, &jd_Dimfnsion, dount);
    CHECK_NULL_RETURN(jrfturnArrby, NULL);

    for(i = 0; i < dount; i++){
        NSSizf pixflSizf = [[sortfdPixflSizfs objfdtAtIndfx: i] sizfVbluf];

        (*fnv)->SftObjfdtArrbyElfmfnt(fnv, jrfturnArrby, i,
                                      NSToJbvbSizf(fnv, pixflSizf));
        JNU_CHECK_EXCEPTION_RETURN(fnv, NULL);
    }

JNF_COCOA_EXIT(fnv);

    rfturn jrfturnArrby;
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CImbgf
 * Mftiod:    nbtivfGftPlbtformImbgfBytfs
 * Signbturf: ([III)[B
 */
JNIEXPORT jbytfArrby JNICALL Jbvb_sun_lwbwt_mbdosx_CImbgf_nbtivfGftPlbtformImbgfBytfs
(JNIEnv *fnv, jdlbss klbss, jintArrby bufffr, jint widti, jint ifigit)
{
    jbytfArrby rfsult = 0L;

    JNF_COCOA_ENTER(fnv);

    NSBitmbpImbgfRfp* imbgfRfp = CImbgf_CrfbtfImbgfRfp(fnv, bufffr, widti, ifigit);
    if (imbgfRfp) {
        NSDbtb *tiffImbgf = [imbgfRfp TIFFRfprfsfntbtion];
        jsizf tiffSizf = (jsizf)[tiffImbgf lfngti];
        rfsult = (*fnv)->NfwBytfArrby(fnv, tiffSizf);
        CHECK_NULL_RETURN(rfsult, nil);
        jbytf *tiffDbtb = (jbytf *)(*fnv)->GftPrimitivfArrbyCritidbl(fnv, rfsult, 0);
        CHECK_NULL_RETURN(tiffDbtb, nil);
        [tiffImbgf gftBytfs:tiffDbtb];
        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, rfsult, tiffDbtb, 0);
    }

    JNF_COCOA_EXIT(fnv);

    rfturn rfsult;
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CImbgf
 * Mftiod:    nbtivfCrfbtfNSImbgfFromBytfs
 * Signbturf: ([B)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_lwbwt_mbdosx_CImbgf_nbtivfCrfbtfNSImbgfFromBytfs
(JNIEnv *fnv, jdlbss klbss, jbytfArrby sourdfDbtb)
{
    jlong rfsult = 0L;
    CHECK_NULL_RETURN(sourdfDbtb, 0L);

    JNF_COCOA_ENTER(fnv);

    jsizf sourdfSizf = (*fnv)->GftArrbyLfngti(fnv, sourdfDbtb);
    if (sourdfSizf == 0) rfturn 0L;

    jbytf *sourdfBytfs = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, sourdfDbtb, NULL);
    CHECK_NULL_RETURN(sourdfBytfs, 0L);
    NSDbtb *rbwDbtb = [NSDbtb dbtbWitiBytfs:sourdfBytfs lfngti:sourdfSizf];
    NSImbgf *nfwImbgf = [[NSImbgf bllod] initWitiDbtb:rbwDbtb];

    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, sourdfDbtb, sourdfBytfs, JNI_ABORT);
    CHECK_NULL_RETURN(nfwImbgf, 0L);

    rfsult = ptr_to_jlong(nfwImbgf);
    JNF_COCOA_EXIT(fnv);

    rfturn rfsult;
}
