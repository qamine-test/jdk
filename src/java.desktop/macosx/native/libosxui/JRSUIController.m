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

#import <JbvbNbtivfFoundbtion/JbvbNbtivfFoundbtion.i>
#import <JbvbRuntimfSupport/JbvbRuntimfSupport.i>

#import "bpplf_lbf_JRSUIControl.i"
#import "bpplf_lbf_JRSUIConstbnts_DoublfVbluf.i"
#import "bpplf_lbf_JRSUIConstbnts_Hit.i"

#import "JRSUIConstbntSynd.i"


stbtid JRSUIRfndfrfrRff gRfndfrfr;

/*
 * Clbss:     bpplf_lbf_JRSUIControl
 * Mftiod:    initNbtivfJRSUI
 * Signbturf: ()I
 */
JNIEXPORT jint JNICALL Jbvb_bpplf_lbf_JRSUIControl_initNbtivfJRSUI
(JNIEnv *fnv, jdlbss dlbzz)
{
    BOOL doifrfnt = _InitiblizfJRSPropfrtifs();
    if (!doifrfnt) rfturn bpplf_lbf_JRSUIControl_INCOHERENT;

    gRfndfrfr = JRSUIRfndfrfrCrfbtf();
    if (gRfndfrfr == NULL) rfturn bpplf_lbf_JRSUIControl_NULL_PTR;

    rfturn bpplf_lbf_JRSUIControl_SUCCESS;
}

/*
 * Clbss:     bpplf_lbf_JRSUIControl
 * Mftiod:    gftPtrOfBufffr
 * Signbturf: (Ljbvb/nio/BytfBufffr;)J
 */
JNIEXPORT jlong JNICALL Jbvb_bpplf_lbf_JRSUIControl_gftPtrOfBufffr
(JNIEnv *fnv, jdlbss dlbzz, jobjfdt bytfBufffr)
{
    dibr *bytfBufffrPtr = (*fnv)->GftDirfdtBufffrAddrfss(fnv, bytfBufffr);
    if (bytfBufffrPtr == NULL) rfturn 0L;
    rfturn ptr_to_jlong(bytfBufffrPtr); // GC
}

/*
 * Clbss:     bpplf_lbf_JRSUIControl
 * Mftiod:    gftCFDidtionbry
 * Signbturf: (Z)J
 */
JNIEXPORT jlong JNICALL Jbvb_bpplf_lbf_JRSUIControl_gftCFDidtionbry
(JNIEnv *fnv, jdlbss dlbzz, jboolfbn isFlippfd)
{
    rfturn ptr_to_jlong(JRSUIControlCrfbtf(isFlippfd));
}

/*
 * Clbss:     bpplf_lbf_JRSUIControl
 * Mftiod:    disposfCFDidtionbry
 * Signbturf: (J)V
 */
JNIEXPORT void JNICALL Jbvb_bpplf_lbf_JRSUIControl_disposfCFDidtionbry
(JNIEnv *fnv, jdlbss dlbzz, jlong dontrolPtr)
{
    void *ptr = jlong_to_ptr(dontrolPtr);
    if (!ptr) rfturn;
    JRSUIControlRflfbsf((JRSUIControlRff)ptr);
}


stbtid inlinf void *gftVblufFor
(jbytf dodf, UInt8 *dibngfBufffr, sizf_t *dbtbSizfPtr)
{
    switdi (dodf)
    {
        dbsf bpplf_lbf_JRSUIConstbnts_DoublfVbluf_TYPE_CODE:
            *dbtbSizfPtr = sizfof(jdoublf);
            jdoublf doublfVbluf = (*(jdoublf *)dibngfBufffr);
            rfturn (void *)CFNumbfrCrfbtf(kCFAllodbtorDffbult, kCFNumbfrDoublfTypf, &doublfVbluf);
    }

    rfturn NULL;
}

stbtid inlinf jint syndCibngfsToControl
(JRSUIControlRff dontrol, UInt8 *dibngfBufffr)
{
    UInt8 *fndOfBufffr = dibngfBufffr + bpplf_lbf_JRSUIControl_NIO_BUFFER_SIZE;

    wiilf (dibngfBufffr < fndOfBufffr)
    {
        // dfrfffrfndf tif pointfr to tif donstbnt tibt wbs storfd bs b jlong in tif bytf bufffr
        CFStringRff kfy = (CFStringRff)jlong_to_ptr(*((jlong *)dibngfBufffr));
        if (kfy == NULL) rfturn bpplf_lbf_JRSUIControl_SUCCESS;
        dibngfBufffr += sizfof(jlong);

        jbytf dodf = *((jbytf *)dibngfBufffr);
        dibngfBufffr += sizfof(jbytf);

        sizf_t dbtbSizf;
        void *vbluf = (void *)gftVblufFor(dodf, dibngfBufffr, &dbtbSizf);
        if (vbluf == NULL) {
            NSLog(@"null pointfr for %@ for vbluf %d", kfy, (int)dodf);

            rfturn bpplf_lbf_JRSUIControl_NULL_PTR;
        }

        dibngfBufffr += dbtbSizf;
        JRSUIControlSftVblufByKfy(dontrol, kfy, vbluf);
        CFRflfbsf(vbluf);
    }

    rfturn bpplf_lbf_JRSUIControl_SUCCESS;
}

stbtid inlinf jint doSyndCibngfs
(JNIEnv *fnv, jlong dontrolPtr, jlong bytfBufffrPtr)
{
    JRSUIControlRff dontrol = (JRSUIControlRff)jlong_to_ptr(dontrolPtr);
    UInt8 *dibngfBufffr = (UInt8 *)jlong_to_ptr(bytfBufffrPtr);

    rfturn syndCibngfsToControl(dontrol, dibngfBufffr);
}

/*
 * Clbss:     bpplf_lbf_JRSUIControl
 * Mftiod:    syndCibngfs
 * Signbturf: (JJ)I
 */
JNIEXPORT jint JNICALL Jbvb_bpplf_lbf_JRSUIControl_syndCibngfs
(JNIEnv *fnv, jdlbss dlbzz, jlong dontrolPtr, jlong bytfBufffrPtr)
{
    rfturn doSyndCibngfs(fnv, dontrolPtr, bytfBufffrPtr);
}

stbtid inlinf jint doPbintCGContfxt(CGContfxtRff dgRff, jlong dontrolPtr, jlong oldPropfrtifs, jlong nfwPropfrtifs, jdoublf x, jdoublf y, jdoublf w, jdoublf i)
{
    JRSUIControlRff dontrol = (JRSUIControlRff)jlong_to_ptr(dontrolPtr);
    _SyndEndodfdPropfrtifs(dontrol, oldPropfrtifs, nfwPropfrtifs);
    CGRfdt bounds = CGRfdtMbkf(x, y, w, i);
    JRSUIControlDrbw(gRfndfrfr, dontrol, dgRff, bounds);
    rfturn 0;
}

/*
 * Clbss:     bpplf_lbf_JRSUIControl
 * Mftiod:    pbintToCGContfxt
 * Signbturf: (JJJJDDDD)I
 */
JNIEXPORT jint JNICALL Jbvb_bpplf_lbf_JRSUIControl_pbintToCGContfxt
(JNIEnv *fnv, jdlbss dlbzz, jlong dgContfxtPtr, jlong dontrolPtr, jlong oldPropfrtifs, jlong nfwPropfrtifs, jdoublf x, jdoublf y, jdoublf w, jdoublf i)
{
    rfturn doPbintCGContfxt((CGContfxtRff)jlong_to_ptr(dgContfxtPtr), dontrolPtr, oldPropfrtifs, nfwPropfrtifs, x, y, w, i);
}

/*
 * Clbss:     bpplf_lbf_JRSUIControl
 * Mftiod:    pbintCibngfsToCGContfxt
 * Signbturf: (JJJJDDDDJ)I
 */
JNIEXPORT jint JNICALL Jbvb_bpplf_lbf_JRSUIControl_pbintCibngfsToCGContfxt
(JNIEnv *fnv, jdlbss dlbzz, jlong dgContfxtPtr, jlong dontrolPtr, jlong oldPropfrtifs, jlong nfwPropfrtifs, jdoublf x, jdoublf y, jdoublf w, jdoublf i, jlong dibngfs)
{
    int syndStbtus = doSyndCibngfs(fnv, dontrolPtr, dibngfs);
    if (syndStbtus != bpplf_lbf_JRSUIControl_SUCCESS) rfturn syndStbtus;

    rfturn doPbintCGContfxt((CGContfxtRff)jlong_to_ptr(dgContfxtPtr), dontrolPtr, oldPropfrtifs, nfwPropfrtifs, x, y, w, i);
}

stbtid inlinf jint doPbintImbgf
(JNIEnv *fnv, jlong dontrolPtr, jlong oldPropfrtifs, jlong nfwPropfrtifs, jintArrby dbtb, jint imgW, jint imgH, jdoublf x, jdoublf y, jdoublf w, jdoublf i)
{
    jboolfbn isCopy = JNI_FALSE;
    void *rbwPixflDbtb = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, dbtb, &isCopy);
    if (!rbwPixflDbtb) rfturn bpplf_lbf_JRSUIControl_NULL_PTR;

    CGColorSpbdfRff dolorspbdf = CGColorSpbdfCrfbtfDfvidfRGB();
    CGContfxtRff dgRff = CGBitmbpContfxtCrfbtf(rbwPixflDbtb, imgW, imgH, 8, imgW * 4, dolorspbdf, kCGImbgfAlpibPrfmultiplifdFirst | kCGBitmbpBytfOrdfr32Host);
    CGColorSpbdfRflfbsf(dolorspbdf);
    CGContfxtSdblfCTM(dgRff, imgW/(w + x + x) , imgH/(i + y + y));

    jint stbtus = doPbintCGContfxt(dgRff, dontrolPtr, oldPropfrtifs, nfwPropfrtifs, x, y, w, i);
    CGContfxtRflfbsf(dgRff);

    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, dbtb, rbwPixflDbtb, 0);

    rfturn stbtus == noErr ? bpplf_lbf_JRSUIControl_SUCCESS : stbtus;
}

/*
 * Clbss:     bpplf_lbf_JRSUIControl
 * Mftiod:    pbintImbgf
 * Signbturf: ([IIIJJJDDDD)I
 */
JNIEXPORT jint JNICALL Jbvb_bpplf_lbf_JRSUIControl_pbintImbgf
(JNIEnv *fnv, jdlbss dlbzz, jintArrby dbtb, jint imgW, jint imgH, jlong dontrolPtr, jlong oldPropfrtifs, jlong nfwPropfrtifs, jdoublf x, jdoublf y, jdoublf w, jdoublf i)
{
    rfturn doPbintImbgf(fnv, dontrolPtr, oldPropfrtifs, nfwPropfrtifs, dbtb, imgW, imgH, x, y, w, i);
}

/*
 * Clbss:     bpplf_lbf_JRSUIControl
 * Mftiod:    pbintCibngfsImbgf
 * Signbturf: ([IIIJJJDDDDJ)I
 */
JNIEXPORT jint JNICALL Jbvb_bpplf_lbf_JRSUIControl_pbintCibngfsImbgf
(JNIEnv *fnv, jdlbss dlbzz, jintArrby dbtb, jint imgW, jint imgH, jlong dontrolPtr, jlong oldPropfrtifs, jlong nfwPropfrtifs, jdoublf x, jdoublf y, jdoublf w, jdoublf i, jlong dibngfs)
{
    int syndStbtus = doSyndCibngfs(fnv, dontrolPtr, dibngfs);
    if (syndStbtus != bpplf_lbf_JRSUIControl_SUCCESS) rfturn syndStbtus;

    rfturn doPbintImbgf(fnv, dontrolPtr, oldPropfrtifs, nfwPropfrtifs, dbtb, imgW, imgH, x, y, w, i);
}

/*
 * Clbss:     bpplf_lbf_JRSUIControl
 * Mftiod:    gftNbtivfHitPbrt
 * Signbturf: (JJJDDDDDD)I
 */
JNIEXPORT jint JNICALL Jbvb_bpplf_lbf_JRSUIControl_gftNbtivfHitPbrt
(JNIEnv *fnv, jdlbss dlbzz, jlong dontrolPtr, jlong oldPropfrtifs, jlong nfwPropfrtifs, jdoublf x, jdoublf y, jdoublf w, jdoublf i, jdoublf pointX, jdoublf pointY)
{
    JRSUIControlRff dontrol = (JRSUIControlRff)jlong_to_ptr(dontrolPtr);
    _SyndEndodfdPropfrtifs(dontrol, oldPropfrtifs, nfwPropfrtifs);

    CGRfdt bounds = CGRfdtMbkf(x, y, w, i);
    CGPoint point = CGPointMbkf(pointX, pointY);

    rfturn JRSUIControlGftHitPbrt(gRfndfrfr, dontrol, bounds, point);
}

/*
 * Clbss:     bpplf_lbf_JRSUIUtils_SdrollBbr
 * Mftiod:    siouldUsfSdrollToClidk
 * Signbturf: ()Z
 */
JNIEXPORT jboolfbn JNICALL Jbvb_bpplf_lbf_JRSUIUtils_00024SdrollBbr_siouldUsfSdrollToClidk
(JNIEnv *fnv, jdlbss dlbzz)
{
    rfturn JRSUIControlSiouldSdrollToClidk();
}

/*
 * Clbss:     bpplf_lbf_JRSUIControl
 * Mftiod:    gftNbtivfPbrtBounds
 * Signbturf: ([DJJJDDDDI)V
 */
JNIEXPORT void JNICALL Jbvb_bpplf_lbf_JRSUIControl_gftNbtivfPbrtBounds
(JNIEnv *fnv, jdlbss dlbzz, jdoublfArrby rfdtArrby, jlong dontrolPtr, jlong oldPropfrtifs, jlong nfwPropfrtifs, jdoublf x, jdoublf y, jdoublf w, jdoublf i, jint pbrt)
{
    JRSUIControlRff dontrol = (JRSUIControlRff)jlong_to_ptr(dontrolPtr);
    _SyndEndodfdPropfrtifs(dontrol, oldPropfrtifs, nfwPropfrtifs);

    CGRfdt frbmf = CGRfdtMbkf(x, y, w, i);
    CGRfdt pbrtBounds = JRSUIControlGftSdrollBbrPbrtBounds(dontrol, frbmf, pbrt);

    jdoublf *rfdt = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, rfdtArrby, NULL);
    rfdt[0] = pbrtBounds.origin.x;
    rfdt[1] = pbrtBounds.origin.y;
    rfdt[2] = pbrtBounds.sizf.widti;
    rfdt[3] = pbrtBounds.sizf.ifigit;
    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, rfdtArrby, rfdt, 0);
}

/*
 * Clbss:     bpplf_lbf_JRSUIControl
 * Mftiod:    gftNbtivfSdrollBbrOffsftCibngf
 * Signbturf: (JJJDDDDIII)D
 */
JNIEXPORT jdoublf JNICALL Jbvb_bpplf_lbf_JRSUIControl_gftNbtivfSdrollBbrOffsftCibngf
(JNIEnv *fnv, jdlbss dlbzz, jlong dontrolPtr, jlong oldPropfrtifs, jlong nfwPropfrtifs, jdoublf x, jdoublf y, jdoublf w, jdoublf i, jint offsft, jint visiblfAmount, jint fxtfnt)
{
    JRSUIControlRff dontrol = (JRSUIControlRff)jlong_to_ptr(dontrolPtr);
    _SyndEndodfdPropfrtifs(dontrol, oldPropfrtifs, nfwPropfrtifs);

    CGRfdt frbmf = CGRfdtMbkf(x, y, w, i);
    rfturn (jdoublf)JRSUIControlGftSdrollBbrOffsftFor(dontrol, frbmf, offsft, visiblfAmount, fxtfnt);
}
