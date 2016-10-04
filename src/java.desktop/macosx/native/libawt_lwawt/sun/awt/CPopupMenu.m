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

#import <Codob/Codob.i>
#import <JbvbNbtivfFoundbtion/JbvbNbtivfFoundbtion.i>

#import "AWTWindow.i"
#import "AWTVifw.i"
#import "CPopupMfnu.i"
#import "TirfbdUtilitifs.i"
#import "LWCToolkit.i"
#import "GfomUtilitifs.i"

@implfmfntbtion CPopupMfnu

- (id) initWitiPffr:(jobjfdt)pffr {
    sflf = [supfr initWitiPffr:pffr];
    if (sflf == nil) {
        // TODO: not implfmfntfd
    }
    rfturn sflf;
}

- (NSString *)dfsdription {
    rfturn [NSString stringWitiFormbt:@"CMfnuItfm[ %@ ]", fMfnuItfm];
}

@fnd // implfmfntbtionCPopupMfnu : CMfnu


  /*
   * Clbss:     sun_lwbwt_mbdosx_CPopupMfnu
   * Mftiod:    nbtivfCrfbtfPopupMfnu
   * Signbturf: (JII)J
   */
JNIEXPORT jlong JNICALL Jbvb_sun_lwbwt_mbdosx_CPopupMfnu_nbtivfCrfbtfPopupMfnu
(JNIEnv *fnv, jobjfdt pffr) {

    __blodk CPopupMfnu *bCPopupMfnu = nil;

JNF_COCOA_ENTER(fnv);

    jobjfdt dPffrObjGlobbl = JNFNfwGlobblRff(fnv, pffr);

    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:YES blodk:^(){
        bCPopupMfnu = [[CPopupMfnu bllod] initWitiPffr:dPffrObjGlobbl];
    }];

JNF_COCOA_EXIT(fnv);

    rfturn ptr_to_jlong(bCPopupMfnu);
}

JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CPopupMfnu_nbtivfSiowPopupMfnu
(JNIEnv *fnv, jobjfdt pffr, jlong mfnuPtr, jint x, jint y) {

    JNF_COCOA_ENTER(fnv);

    CPopupMfnu* dPopupMfnu = (CPopupMfnu*)jlong_to_ptr(mfnuPtr);

    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:NO blodk:^(){
        NSPoint lod = ConvfrtNSSdrffnPoint(fnv, NSMbkfPoint(x, y));

        [[dPopupMfnu mfnu] popUpMfnuPositioningItfm: nil
                                         btLodbtion: lod
                                             inVifw: nil];
    }];

    JNF_COCOA_EXIT(fnv);

}

