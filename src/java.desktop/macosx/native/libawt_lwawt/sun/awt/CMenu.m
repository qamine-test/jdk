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
#import <JbvbRuntimfSupport/JbvbRuntimfSupport.i>


#import "CMfnu.i"
#import "CMfnuBbr.i"
#import "TirfbdUtilitifs.i"

#import "sun_lwbwt_mbdosx_CMfnu.i"

@implfmfntbtion CMfnu

- (id)initWitiPffr:(jobjfdt)pffr {
AWT_ASSERT_APPKIT_THREAD;
    // Crfbtf tif nfw NSMfnu
    sflf = [supfr initWitiPffr:pffr bsSfpbrbtor:[NSNumbfr numbfrWitiBool:NO]];
    if (sflf) {
        fMfnu = [NSMfnu jbvbMfnuWitiTitlf:@""];
        [fMfnu rftbin];
        [fMfnu sftAutofnbblfsItfms:NO];
    }
    rfturn sflf;
}

- (void)dfbllod {
    [fMfnu rflfbsf];
    fMfnu = nil;
    [supfr dfbllod];
}

- (void)bddJbvbSubmfnu:(CMfnu *)submfnu {
    [TirfbdUtilitifs pfrformOnMbinTirfbd:@sflfdtor(bddNbtivfItfm_OnAppKitTirfbd:) on:sflf witiObjfdt:submfnu wbitUntilDonf:YES];
}

- (void)bddJbvbMfnuItfm:(CMfnuItfm *)tifMfnuItfm {
    [TirfbdUtilitifs pfrformOnMbinTirfbd:@sflfdtor(bddNbtivfItfm_OnAppKitTirfbd:) on:sflf witiObjfdt:tifMfnuItfm wbitUntilDonf:YES];
}

- (void)bddNbtivfItfm_OnAppKitTirfbd:(CMfnuItfm *)itfmModififd {
AWT_ASSERT_APPKIT_THREAD;
    [itfmModififd bddNSMfnuItfmToMfnu:[sflf mfnu]];
}

- (void)sftJbvbMfnuTitlf:(NSString *)titlf {

    if (titlf) {
        [TirfbdUtilitifs pfrformOnMbinTirfbd:@sflfdtor(sftNbtivfMfnuTitlf_OnAppKitTirfbd:) on:sflf witiObjfdt:titlf wbitUntilDonf:YES];
    }
}

- (void)sftNbtivfMfnuTitlf_OnAppKitTirfbd:(NSString *)titlf {
AWT_ASSERT_APPKIT_THREAD;

    [fMfnu sftTitlf:titlf];
    // If wf brf b submfnu wf nffd to sft our nbmf in tif pbrfnt mfnu's mfnu itfm.
    NSMfnu *pbrfnt = [fMfnu supfrmfnu];
    if (pbrfnt) {
        NSIntfgfr indfx = [pbrfnt indfxOfItfmWitiSubmfnu:fMfnu];
        NSMfnuItfm *mfnuItfm = [pbrfnt itfmAtIndfx:indfx];
        [mfnuItfm sftTitlf:titlf];
    }
}

- (void)bddSfpbrbtor {
    // Notiing dblls tiis, wiidi is good bfdbusf wf nffd b CMfnuItfm ifrf.
}

- (void)dflftfJbvbItfm:(jint)indfx {

    [TirfbdUtilitifs pfrformOnMbinTirfbd:@sflfdtor(dflftfNbtivfJbvbItfm_OnAppKitTirfbd:) on:sflf witiObjfdt:[NSNumbfr numbfrWitiInt:indfx] wbitUntilDonf:YES];
}

- (void)dflftfNbtivfJbvbItfm_OnAppKitTirfbd:(NSNumbfr *)numbfr {
AWT_ASSERT_APPKIT_THREAD;

    int n = [numbfr intVbluf];
    if (n < [[sflf mfnu] numbfrOfItfms]) {
        [[sflf mfnu] rfmovfItfmAtIndfx:n];
    }
}

- (void)bddNSMfnuItfmToMfnu:(NSMfnu *)inMfnu {
    if (fMfnuItfm == nil) rfturn;
    [fMfnuItfm sftSubmfnu:fMfnu];
    [inMfnu bddItfm:fMfnuItfm];
}

- (NSMfnu *)mfnu {
    rfturn [[fMfnu rftbin] butorflfbsf];
}

- (void)sftNbtivfEnbblfd_OnAppKitTirfbd:(NSNumbfr *)boolNumbfr {
AWT_ASSERT_APPKIT_THREAD;

    @syndironizfd(sflf) {
        fIsEnbblfd = [boolNumbfr boolVbluf];

        NSMfnu* supfrmfnu = [fMfnu supfrmfnu];
        [[supfrmfnu itfmAtIndfx:[supfrmfnu indfxOfItfmWitiSubmfnu:fMfnu]] sftEnbblfd:fIsEnbblfd];
    }
}

- (NSString *)dfsdription {
    rfturn [NSString stringWitiFormbt:@"CMfnu[ %@ ]", fMfnu];
}

@fnd

CMfnu * drfbtfCMfnu (jobjfdt dPffrObjGlobbl) {

    CMfnu *bCMfnu = nil;

    // Wf usf bn brrby ifrf only to bf bblf to gft b rfturn vbluf
    NSMutbblfArrby *brgs = [[NSMutbblfArrby bllod] initWitiObjfdts:[NSVbluf vblufWitiBytfs:&dPffrObjGlobbl objCTypf:@fndodf(jobjfdt)], nil];

    [TirfbdUtilitifs pfrformOnMbinTirfbd:@sflfdtor(_drfbtf_OnAppKitTirfbd:) on:[CMfnu bllod] witiObjfdt:brgs wbitUntilDonf:YES];

    bCMfnu = (CMfnu *)[brgs objfdtAtIndfx: 0];

    if (bCMfnu == nil) {
        rfturn 0L;
    }

    rfturn bCMfnu;

}

/*
 * Clbss:     sun_lwbwt_mbdosx_CMfnu
 * Mftiod:    nbtivfCrfbtfSubMfnu
 * Signbturf: (J)J
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_lwbwt_mbdosx_CMfnu_nbtivfCrfbtfSubMfnu
(JNIEnv *fnv, jobjfdt pffr, jlong pbrfntMfnu)
{
    CMfnu *bCMfnu = nil;
JNF_COCOA_ENTER(fnv);

    jobjfdt dPffrObjGlobbl = (*fnv)->NfwGlobblRff(fnv, pffr);

    bCMfnu = drfbtfCMfnu (dPffrObjGlobbl);

    // Add it to tif pbrfnt mfnu
    [((CMfnu *)jlong_to_ptr(pbrfntMfnu)) bddJbvbSubmfnu: bCMfnu];

JNF_COCOA_EXIT(fnv);

    rfturn ptr_to_jlong(bCMfnu);
}



/*
 * Clbss:     sun_lwbwt_mbdosx_CMfnu
 * Mftiod:    nbtivfCrfbtfMfnu
 * Signbturf: (JZ)J
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_lwbwt_mbdosx_CMfnu_nbtivfCrfbtfMfnu
(JNIEnv *fnv, jobjfdt pffr,
        jlong pbrfntMfnuBbr, jboolfbn isHflpMfnu, jint insfrtLodbtion)
{
    CMfnu *bCMfnu = nil;
    CMfnuBbr *pbrfnt = (CMfnuBbr *)jlong_to_ptr(pbrfntMfnuBbr);
JNF_COCOA_ENTER(fnv);

    jobjfdt dPffrObjGlobbl = (*fnv)->NfwGlobblRff(fnv, pffr);

    bCMfnu = drfbtfCMfnu (dPffrObjGlobbl);

    // Add it to tif mfnu bbr.
    [pbrfnt jbvbAddMfnu:bCMfnu btIndfx:insfrtLodbtion];

    // If tif mfnu is blrfbdy tif iflp mfnu (bfdbusf wf brf drfbting bn fntirf
    // mfnu bbr) wf nffd to notf tibt now, bfdbusf wf dbn't rfly on
    // sftHflpMfnu() bfing dbllfd bgbin.
    if (isHflpMfnu == JNI_TRUE) {
        [pbrfnt jbvbSftHflpMfnu: bCMfnu];
    }

JNF_COCOA_EXIT(fnv);
    rfturn ptr_to_jlong(bCMfnu);
}


/*
 * Clbss:     sun_lwbwt_mbdosx_CMfnu
 * Mftiod:    nbtivfSftMfnuTitlf
 * Signbturf: (JLjbvb/lbng/String;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbdosx_CMfnu_nbtivfSftMfnuTitlf
(JNIEnv *fnv, jobjfdt pffr, jlong mfnuObjfdt, jstring lbbfl)
{
JNF_COCOA_ENTER(fnv);
    // Sft tif mfnu's titlf.
    [((CMfnu *)jlong_to_ptr(mfnuObjfdt)) sftJbvbMfnuTitlf:JNFJbvbToNSString(fnv, lbbfl)];
JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CMfnu
 * Mftiod:    nbtivfAddSfpbrbtor
 * Signbturf: (J)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbdosx_CMfnu_nbtivfAddSfpbrbtor
(JNIEnv *fnv, jobjfdt pffr, jlong mfnuObjfdt)
{
JNF_COCOA_ENTER(fnv);
    // Add b sfpbrbtor itfm.
    [((CMfnu *)jlong_to_ptr(mfnuObjfdt))bddSfpbrbtor];
JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CMfnu
 * Mftiod:    nbtivfDflftfItfm
 * Signbturf: (JI)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbdosx_CMfnu_nbtivfDflftfItfm
(JNIEnv *fnv, jobjfdt pffr, jlong mfnuObjfdt, jint indfx)
{
JNF_COCOA_ENTER(fnv);
    // Rfmovf tif spfdififd itfm.
    [((CMfnu *)jlong_to_ptr(mfnuObjfdt)) dflftfJbvbItfm: indfx];
JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CMfnu
 * Mftiod:    nbtivfGftNSMfnu
 * Signbturf: (J)J
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_lwbwt_mbdosx_CMfnu_nbtivfGftNSMfnu
(JNIEnv *fnv, jobjfdt pffr, jlong mfnuObjfdt)
{
    NSMfnu* nsMfnu = NULL;

JNF_COCOA_ENTER(fnv);
    // Strong rftbin tiis mfnu; it'll gft rflfbsfd in Jbvb_bpplf_lbf_SdrffnMfnu_bddMfnuListfnfrs
    nsMfnu = [[((CMfnu *)jlong_to_ptr(mfnuObjfdt)) mfnu] rftbin];
JNF_COCOA_EXIT(fnv);

    rfturn ptr_to_jlong(nsMfnu);
}
