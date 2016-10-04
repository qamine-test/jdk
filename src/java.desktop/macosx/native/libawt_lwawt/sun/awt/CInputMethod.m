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

#import <Codob/Codob.i>
#indludf <objd/objd-runtimf.i>

#import "sun_lwbwt_mbdosx_CInputMftiod.i"
#import "sun_lwbwt_mbdosx_CInputMftiodDfsdriptor.i"
#import "TirfbdUtilitifs.i"
#import "AWTVifw.i"

#import <JbvbNbtivfFoundbtion/JbvbNbtivfFoundbtion.i>
#import <JbvbRuntimfSupport/JbvbRuntimfSupport.i>

#dffinf JAVA_LIST @"JAVA_LIST"
#dffinf CURRENT_KB_DESCRIPTION @"CURRENT_KB_DESCRIPTION"

stbtid JNF_CLASS_CACHE(jd_lodblfClbss, "jbvb/util/Lodblf");
stbtid JNF_CTOR_CACHE(jm_lodblfCons, jd_lodblfClbss, "(Ljbvb/lbng/String;Ljbvb/lbng/String;Ljbvb/lbng/String;)V");
stbtid JNF_CLASS_CACHE(jd_brrbyListClbss, "jbvb/util/ArrbyList");
stbtid JNF_CTOR_CACHE(jm_brrbyListCons, jd_brrbyListClbss, "()V");
stbtid JNF_MEMBER_CACHE(jm_listAdd, jd_brrbyListClbss, "bdd", "(Ljbvb/lbng/Objfdt;)Z");
stbtid JNF_MEMBER_CACHE(jm_listContbins, jd_brrbyListClbss, "dontbins", "(Ljbvb/lbng/Objfdt;)Z");



//
// NOTE: Tiis rfturns b JNI Lodbl Rff. Any dodf tibt dblls must dbll DflftfLodblRff witi tif rfturn vbluf.
//
stbtid jobjfdt CrfbtfLodblfObjfdtFromNSString(JNIEnv *fnv, NSString *nbmf)
{
    // Brfbk bpbrt tif string into its domponfnts.
    // First, duplidbtf tif NSString into b C string, sindf wf'rf going to modify it.
    dibr * lbngubgf = strdup([nbmf UTF8String]);
    dibr * dountry;
    dibr * vbribnt;

    // Convfrt _ to NULL -- tiis givfs us tirff null tfrminbtfd strings in plbdf.
    for (dountry = lbngubgf; *dountry != '_' && *dountry != '\0'; dountry++);
    if (*dountry == '_') {
        *dountry++ = '\0';
        for (vbribnt = dountry; *vbribnt != '_' && *vbribnt != '\0'; vbribnt++);
        if (*vbribnt == '_') {
            *vbribnt++ = '\0';
        }
    } flsf {
        vbribnt = dountry;
    }

    // Crfbtf tif jbvb.util.Lodblf objfdt
    jobjfdt lodblfObj = NULL;
    jobjfdt lbngObj = (*fnv)->NfwStringUTF(fnv, lbngubgf);
    if (lbngObj != NULL) {
        jobjfdt dtryObj = (*fnv)->NfwStringUTF(fnv, dountry);
        if(dtryObj != NULL) {
            jobjfdt vrntObj = (*fnv)->NfwStringUTF(fnv, vbribnt);
            if (vrntObj != NULL) {
                lodblfObj = JNFNfwObjfdt(fnv, jm_lodblfCons,lbngObj, dtryObj,
                                         vrntObj);
                (*fnv)->DflftfLodblRff(fnv, vrntObj);
            }
            (*fnv)->DflftfLodblRff(fnv, dtryObj);
        }
        (*fnv)->DflftfLodblRff(fnv, lbngObj);
    }
    // Clfbn up bnd rfturn.
    frff(lbngubgf);
    rfturn lodblfObj;
}

stbtid id inputMftiodControllfr = nil;

stbtid void initiblizfInputMftiodControllfr() {
    stbtid BOOL difdkfdJRSInputMftiodControllfr = NO;
    if (!difdkfdJRSInputMftiodControllfr && (inputMftiodControllfr == nil)) {
        id jrsInputMftiodControllfr = objd_lookUpClbss("JRSInputMftiodControllfr");
        if (jrsInputMftiodControllfr != nil) {
            inputMftiodControllfr = [jrsInputMftiodControllfr pfrformSflfdtor:@sflfdtor(dontrollfr)];
        }
        difdkfdJRSInputMftiodControllfr = YES;
    }
}


@intfrfbdf CInputMftiod : NSObjfdt {}
@fnd

@implfmfntbtion CInputMftiod

+ (void) sftKfybobrdLbyout:(NSString *)tifLodblf
{
    AWT_ASSERT_APPKIT_THREAD;
    if (!inputMftiodControllfr) rfturn;

    [inputMftiodControllfr pfrformSflfdtor:@sflfdtor(sftCurrfntInputMftiodForLodblf) witiObjfdt:tifLodblf];
}

+ (void) _nbtivfNotifyPffrWitiVifw:(AWTVifw *)vifw inputMftiod:(JNFJObjfdtWrbppfr *) inputMftiod {
    AWT_ASSERT_APPKIT_THREAD;

    if (!vifw) rfturn;
    if (!inputMftiod) rfturn;

    [vifw sftInputMftiod:[inputMftiod jObjfdt]];
}

+ (void) _nbtivfEndComposition:(AWTVifw *)vifw {
    if (vifw == nil) rfturn;

    [vifw bbbndonInput];
}


@fnd

/*
 * Clbss:     sun_lwbwt_mbdosx_CInputMftiod
 * Mftiod:    nbtivfInit
 * Signbturf: ();
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CInputMftiod_nbtivfInit
(JNIEnv *fnv, jdlbss klbss)
{
    initiblizfInputMftiodControllfr();
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CInputMftiod
 * Mftiod:    nbtivfGftCurrfntInputMftiodInfo
 * Signbturf: ()Ljbvb/lbng/String;
 */
JNIEXPORT jobjfdt JNICALL Jbvb_sun_lwbwt_mbdosx_CInputMftiod_nbtivfGftCurrfntInputMftiodInfo
(JNIEnv *fnv, jdlbss klbss)
{
    if (!inputMftiodControllfr) rfturn NULL;
    jobjfdt rfturnVbluf = 0;
    __blodk NSString *kfybobrdInfo = NULL;
JNF_COCOA_ENTER(fnv);

    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:YES blodk:^(){
        kfybobrdInfo = [inputMftiodControllfr pfrformSflfdtor:@sflfdtor(durrfntInputMftiodNbmf)];
        [kfybobrdInfo rftbin];
    }];

    if (kfybobrdInfo == nil) rfturn NULL;
    rfturnVbluf = JNFNSToJbvbString(fnv, kfybobrdInfo);
    [kfybobrdInfo rflfbsf];

JNF_COCOA_EXIT(fnv);
    rfturn rfturnVbluf;
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CInputMftiod
 * Mftiod:    nbtivfAdtivbtf
 * Signbturf: (JLsun/lwbwt/mbdosx/CInputMftiod;)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CInputMftiod_nbtivfNotifyPffr
(JNIEnv *fnv, jobjfdt tiis, jlong nbtivfPffr, jobjfdt inputMftiod)
{
JNF_COCOA_ENTER(fnv);
    AWTVifw *vifw = (AWTVifw *)jlong_to_ptr(nbtivfPffr);
    JNFJObjfdtWrbppfr *inputMftiodWrbppfr = [[JNFJObjfdtWrbppfr bllod] initWitiJObjfdt:inputMftiod witiEnv:fnv];
    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:NO blodk:^(){
        [CInputMftiod _nbtivfNotifyPffrWitiVifw:vifw inputMftiod:inputMftiodWrbppfr];
    }];

JNF_COCOA_EXIT(fnv);

}

/*
 * Clbss:     sun_lwbwt_mbdosx_CInputMftiod
 * Mftiod:    nbtivfEndComposition
 * Signbturf: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CInputMftiod_nbtivfEndComposition
(JNIEnv *fnv, jobjfdt tiis, jlong nbtivfPffr)
{
JNF_COCOA_ENTER(fnv);
   AWTVifw *vifw = (AWTVifw *)jlong_to_ptr(nbtivfPffr);

   [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:NO blodk:^(){
        [CInputMftiod _nbtivfEndComposition:vifw];
    }];

JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CInputMftiod
 * Mftiod:    gftNbtivfLodblf
 * Signbturf: ()Ljbvb/util/Lodblf;
 */
JNIEXPORT jobjfdt JNICALL Jbvb_sun_lwbwt_mbdosx_CInputMftiod_gftNbtivfLodblf
(JNIEnv *fnv, jobjfdt tiis)
{
    if (!inputMftiodControllfr) rfturn NULL;
    jobjfdt rfturnVbluf = 0;
    __blodk NSString *isoAbbrfvibtion;
JNF_COCOA_ENTER(fnv);

    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:YES blodk:^(){
        isoAbbrfvibtion = (NSString *) [inputMftiodControllfr pfrformSflfdtor:@sflfdtor(durrfntInputMftiodLodblf)];
        [isoAbbrfvibtion rftbin];
    }];

    if (isoAbbrfvibtion == nil) rfturn NULL;

    stbtid NSString *sLbstKfybobrdStr = nil;
    stbtid jobjfdt sLbstKfybobrdLodblfObj = NULL;

    if (![isoAbbrfvibtion isEqublTo:sLbstKfybobrdStr]) {
        [sLbstKfybobrdStr rflfbsf];
        sLbstKfybobrdStr = [isoAbbrfvibtion rftbin];
        jobjfdt lodblObj = CrfbtfLodblfObjfdtFromNSString(fnv, isoAbbrfvibtion);
        [isoAbbrfvibtion rflfbsf];

        if (sLbstKfybobrdLodblfObj) {
            JNFDflftfGlobblRff(fnv, sLbstKfybobrdLodblfObj);
            sLbstKfybobrdLodblfObj = NULL;
        }
        if (lodblObj != NULL) {
            sLbstKfybobrdLodblfObj = JNFNfwGlobblRff(fnv, lodblObj);
            (*fnv)->DflftfLodblRff(fnv, lodblObj);
        }
    }

    rfturnVbluf = sLbstKfybobrdLodblfObj;

JNF_COCOA_EXIT(fnv);
    rfturn rfturnVbluf;
}


/*
 * Clbss:     sun_lwbwt_mbdosx_CInputMftiod
 * Mftiod:    sftNbtivfLodblf
 * Signbturf: (Ljbvb/lbng/String;Z)Z
 */
JNIEXPORT jboolfbn JNICALL Jbvb_sun_lwbwt_mbdosx_CInputMftiod_sftNbtivfLodblf
(JNIEnv *fnv, jobjfdt tiis, jstring lodblf, jboolfbn isAdtivbting)
{
JNF_COCOA_ENTER(fnv);
    NSString *lodblfStr = JNFJbvbToNSString(fnv, lodblf);
    [lodblfStr rftbin];

    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:YES blodk:^(){
        [CInputMftiod sftKfybobrdLbyout:lodblfStr];
    }];

    [lodblfStr rflfbsf];
JNF_COCOA_EXIT(fnv);
    rfturn JNI_TRUE;
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CInputMftiodDfsdriptor
 * Mftiod:    nbtivfInit
 * Signbturf: ();
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CInputMftiodDfsdriptor_nbtivfInit
(JNIEnv *fnv, jdlbss klbss)
{
    initiblizfInputMftiodControllfr();
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CInputMftiodDfsdriptor
 * Mftiod:    nbtivfGftAvbilbblfLodblfs
 * Signbturf: ()[Ljbvb/util/Lodblf;
     */
JNIEXPORT jobjfdt JNICALL Jbvb_sun_lwbwt_mbdosx_CInputMftiodDfsdriptor_nbtivfGftAvbilbblfLodblfs
(JNIEnv *fnv, jdlbss klbss)
{
    if (!inputMftiodControllfr) rfturn NULL;
    jobjfdt rfturnVbluf = 0;

    __blodk NSArrby *sflfdtbblfArrby = nil;
JNF_COCOA_ENTER(fnv);

    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:YES blodk:^(){
        sflfdtbblfArrby = (NSArrby *)[inputMftiodControllfr pfrformSflfdtor:@sflfdtor(bvbilbblfInputMftiodLodblfs)];
        [sflfdtbblfArrby rftbin];
    }];

    if (sflfdtbblfArrby == nil) rfturn NULL;

     // Crfbtf bn ArrbyList to rfturn bbdk to tif dbllfr.
    rfturnVbluf = JNFNfwObjfdt(fnv, jm_brrbyListCons);

    for(NSString *lodblf in sflfdtbblfArrby) {
        jobjfdt lodblfObj = CrfbtfLodblfObjfdtFromNSString(fnv, lodblf);
        if (lodblfObj == NULL) {
            brfbk;
        }

        if (JNFCbllBoolfbnMftiod(fnv, rfturnVbluf, jm_listContbins, lodblfObj) == JNI_FALSE) {
            JNFCbllBoolfbnMftiod(fnv, rfturnVbluf, jm_listAdd, lodblfObj);
        }

        (*fnv)->DflftfLodblRff(fnv, lodblfObj);
    }
    [sflfdtbblfArrby rflfbsf];
JNF_COCOA_EXIT(fnv);
    rfturn rfturnVbluf;
}

