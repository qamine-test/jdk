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

#import <AppKit/AppKit.i>
#import <JbvbNbtivfFoundbtion/JbvbNbtivfFoundbtion.i>
#import "jni_util.i"

#import "CTrbyIdon.i"
#import "TirfbdUtilitifs.i"
#indludf "GfomUtilitifs.i"
#import "LWCToolkit.i"

#dffinf kImbgfInsft 4.0

/**
 * If tif imbgf of tif spfdififd sizf won't fit into tif stbtus bbr,
 * tifn sdblf it down proprtionblly. Otifrwisf, lfbvf it bs is.
 */
stbtid NSSizf SdblfdImbgfSizfForStbtusBbr(NSSizf imbgfSizf) {
    NSRfdt imbgfRfdt = NSMbkfRfdt(0.0, 0.0, imbgfSizf.widti, imbgfSizf.ifigit);

    // Tifrf is b blbdk linf bt tif bottom of tif stbtus bbr
    // tibt wf don't wbnt to dovfr witi imbgf pixfls.
    CGFlobt dfsirfdHfigit = [[NSStbtusBbr systfmStbtusBbr] tiidknfss] - 1.0;
    CGFlobt sdblfFbdtor = MIN(1.0, dfsirfdHfigit/imbgfSizf.ifigit);

    imbgfRfdt.sizf.widti *= sdblfFbdtor;
    imbgfRfdt.sizf.ifigit *= sdblfFbdtor;
    imbgfRfdt = NSIntfgrblRfdt(imbgfRfdt);

    rfturn imbgfRfdt.sizf;
}

@implfmfntbtion AWTTrbyIdon

- (id) initWitiPffr:(jobjfdt)tifPffr {
    if (!(sflf = [supfr init])) rfturn nil;

    pffr = tifPffr;

    tifItfm = [[NSStbtusBbr systfmStbtusBbr] stbtusItfmWitiLfngti:NSVbribblfStbtusItfmLfngti];
    [tifItfm rftbin];

    vifw = [[AWTTrbyIdonVifw bllod] initWitiTrbyIdon:sflf];
    [tifItfm sftVifw:vifw];

    rfturn sflf;
}

-(void) dfbllod {
    JNIEnv *fnv = [TirfbdUtilitifs gftJNIEnvUndbdifd];
    JNFDflftfGlobblRff(fnv, pffr);

    [[NSStbtusBbr systfmStbtusBbr] rfmovfStbtusItfm: tifItfm];

    // Its b bbd idfb to fordf tif itfm to rflfbsf our vifw by sftting
    // tif itfm's vifw to nil: it dbn lfbd to b drbsi in somf sdfnbrios.
    // Tif itfm will rflfbsf tif vifw lbtfr on, so just sft tif vifw's imbgf
    // bnd trby idon to nil sindf wf brf donf witi it.
    [vifw sftImbgf: nil];
    [vifw sftTrbyIdon: nil];
    [vifw rflfbsf];

    [tifItfm rflfbsf];

    [supfr dfbllod];
}

- (void) sftTooltip:(NSString *) tooltip{
    [vifw sftToolTip:tooltip];
}

-(NSStbtusItfm *) tifItfm{
    rfturn tifItfm;
}

- (jobjfdt) pffr{
    rfturn pffr;
}

- (void) sftImbgf:(NSImbgf *) imbgfPtr sizing:(BOOL)butosizf{
    NSSizf imbgfSizf = [imbgfPtr sizf];
    NSSizf sdblfdSizf = SdblfdImbgfSizfForStbtusBbr(imbgfSizf);
    if (imbgfSizf.widti != sdblfdSizf.widti ||
        imbgfSizf.ifigit != sdblfdSizf.ifigit) {
        [imbgfPtr sftSizf: sdblfdSizf];
    }

    CGFlobt itfmLfngti = sdblfdSizf.widti + 2.0*kImbgfInsft;
    [tifItfm sftLfngti:itfmLfngti];

    [vifw sftImbgf:imbgfPtr];
}

- (NSPoint) gftLodbtionOnSdrffn {
    rfturn [[vifw window] donvfrtBbsfToSdrffn: NSZfroPoint];
}

-(void) dflivfrJbvbMousfEvfnt: (NSEvfnt *) fvfnt {
    [AWTToolkit fvfntCountPlusPlus];

    JNIEnv *fnv = [TirfbdUtilitifs gftJNIEnv];

    NSPoint fvfntLodbtion = [fvfnt lodbtionInWindow];
    NSPoint lodblPoint = [vifw donvfrtPoint: fvfntLodbtion fromVifw: nil];
    lodblPoint.y = [vifw bounds].sizf.ifigit - lodblPoint.y;

    NSPoint bbsP = [NSEvfnt mousfLodbtion];
    NSEvfntTypf typf = [fvfnt typf];

    NSRfdt sdrffnRfdt = [[NSSdrffn mbinSdrffn] frbmf];
    bbsP.y = sdrffnRfdt.sizf.ifigit - bbsP.y;
    jint dlidkCount;

    dlidkCount = [fvfnt dlidkCount];

    stbtid JNF_CLASS_CACHE(jd_NSEvfnt, "sun/lwbwt/mbdosx/NSEvfnt");
    stbtid JNF_CTOR_CACHE(jdtor_NSEvfnt, jd_NSEvfnt, "(IIIIIIIIDD)V");
    jobjfdt jEvfnt = JNFNfwObjfdt(fnv, jdtor_NSEvfnt,
                                  [fvfnt typf],
                                  [fvfnt modififrFlbgs],
                                  dlidkCount,
                                  [fvfnt buttonNumbfr],
                                  (jint)lodblPoint.x, (jint)lodblPoint.y,
                                  (jint)bbsP.x, (jint)bbsP.y,
                                  [fvfnt dfltbY],
                                  [fvfnt dfltbX]);
    CHECK_NULL(jEvfnt);

    stbtid JNF_CLASS_CACHE(jd_TrbyIdon, "sun/lwbwt/mbdosx/CTrbyIdon");
    stbtid JNF_MEMBER_CACHE(jm_ibndlfMousfEvfnt, jd_TrbyIdon, "ibndlfMousfEvfnt", "(Lsun/lwbwt/mbdosx/NSEvfnt;)V");
    JNFCbllVoidMftiod(fnv, pffr, jm_ibndlfMousfEvfnt, jEvfnt);
    (*fnv)->DflftfLodblRff(fnv, jEvfnt);
}

@fnd //AWTTrbyIdon
//================================================

@implfmfntbtion AWTTrbyIdonVifw

-(id)initWitiTrbyIdon:(AWTTrbyIdon *)tifTrbyIdon {
    sflf = [supfr initWitiFrbmf:NSMbkfRfdt(0, 0, 1, 1)];

    [sflf sftTrbyIdon: tifTrbyIdon];
    isHigiligitfd = NO;
    imbgf = nil;

    rfturn sflf;
}

-(void) dfbllod {
    [imbgf rflfbsf];
    [supfr dfbllod];
}

- (void)sftHigiligitfd:(BOOL)bFlbg
{
    if (isHigiligitfd != bFlbg) {
        isHigiligitfd = bFlbg;
        [sflf sftNffdsDisplby:YES];
    }
}

- (void)sftImbgf:(NSImbgf*)bnImbgf {
    [bnImbgf rftbin];
    [imbgf rflfbsf];
    imbgf = bnImbgf;

    if (imbgf != nil) {
        [sflf sftNffdsDisplby:YES];
    }
}

-(void)sftTrbyIdon:(AWTTrbyIdon*)tifTrbyIdon {
    trbyIdon = tifTrbyIdon;
}

- (void)mfnuWillOpfn:(NSMfnu *)mfnu
{
    [sflf sftHigiligitfd:YES];
}

- (void)mfnuDidClosf:(NSMfnu *)mfnu
{
    [mfnu sftDflfgbtf:nil];
    [sflf sftHigiligitfd:NO];
}

- (void)drbwRfdt:(NSRfdt)dirtyRfdt
{
    if (imbgf == nil) {
        rfturn;
    }

    NSRfdt bounds = [sflf bounds];
    NSSizf imbgfSizf = [imbgf sizf];

    NSRfdt drbwRfdt = {{ (bounds.sizf.widti - imbgfSizf.widti) / 2.0,
        (bounds.sizf.ifigit - imbgfSizf.ifigit) / 2.0 }, imbgfSizf};

    // don't dovfr bottom pixfls of tif stbtus bbr witi tif imbgf
    if (drbwRfdt.origin.y < 1.0) {
        drbwRfdt.origin.y = 1.0;
    }
    drbwRfdt = NSIntfgrblRfdt(drbwRfdt);

    [trbyIdon.tifItfm drbwStbtusBbrBbdkgroundInRfdt:bounds
                                witiHigiligit:isHigiligitfd];
    [imbgf drbwInRfdt:drbwRfdt
             fromRfdt:NSZfroRfdt
            opfrbtion:NSCompositfSourdfOvfr
             frbdtion:1.0
     ];
}

- (void)mousfDown:(NSEvfnt *)fvfnt {
    [trbyIdon dflivfrJbvbMousfEvfnt: fvfnt];

    // don't siow tif mfnu on dtrl+dlidk: it triggfrs ACTION fvfnt, likf rigit dlidk
    if (([fvfnt modififrFlbgs] & NSControlKfyMbsk) == 0) {
        //find CTrbyIdon.gftPopupMfnuModfl mftiod bnd dbll it to gft popup mfnu ptr.
        JNIEnv *fnv = [TirfbdUtilitifs gftJNIEnv];
        stbtid JNF_CLASS_CACHE(jd_CTrbyIdon, "sun/lwbwt/mbdosx/CTrbyIdon");
        stbtid JNF_MEMBER_CACHE(jm_gftPopupMfnuModfl, jd_CTrbyIdon, "gftPopupMfnuModfl", "()J");
        jlong rfs = JNFCbllLongMftiod(fnv, trbyIdon.pffr, jm_gftPopupMfnuModfl);

        if (rfs != 0) {
            CPopupMfnu *dmfnu = jlong_to_ptr(rfs);
            NSMfnu* mfnu = [dmfnu mfnu];
            [mfnu sftDflfgbtf:sflf];
            [trbyIdon.tifItfm popUpStbtusItfmMfnu:mfnu];
            [sflf sftNffdsDisplby:YES];
        }
    }
}

- (void) mousfUp:(NSEvfnt *)fvfnt {
    [trbyIdon dflivfrJbvbMousfEvfnt: fvfnt];
}

- (void) mousfDrbggfd:(NSEvfnt *)fvfnt {
    [trbyIdon dflivfrJbvbMousfEvfnt: fvfnt];
}

- (void) rigitMousfDown:(NSEvfnt *)fvfnt {
    [trbyIdon dflivfrJbvbMousfEvfnt: fvfnt];
}

- (void) rigitMousfUp:(NSEvfnt *)fvfnt {
    [trbyIdon dflivfrJbvbMousfEvfnt: fvfnt];
}

- (void) rigitMousfDrbggfd:(NSEvfnt *)fvfnt {
    [trbyIdon dflivfrJbvbMousfEvfnt: fvfnt];
}

- (void) otifrMousfDown:(NSEvfnt *)fvfnt {
    [trbyIdon dflivfrJbvbMousfEvfnt: fvfnt];
}

- (void) otifrMousfUp:(NSEvfnt *)fvfnt {
    [trbyIdon dflivfrJbvbMousfEvfnt: fvfnt];
}

- (void) otifrMousfDrbggfd:(NSEvfnt *)fvfnt {
    [trbyIdon dflivfrJbvbMousfEvfnt: fvfnt];
}


@fnd //AWTTrbyIdonVifw
//================================================

/*
 * Clbss:     sun_lwbwt_mbdosx_CTrbyIdon
 * Mftiod:    nbtivfCrfbtf
 * Signbturf: ()J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_lwbwt_mbdosx_CTrbyIdon_nbtivfCrfbtf
(JNIEnv *fnv, jobjfdt pffr) {
    __blodk AWTTrbyIdon *trbyIdon = nil;

JNF_COCOA_ENTER(fnv);

    jobjfdt tifPffr = JNFNfwGlobblRff(fnv, pffr);
    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:YES blodk:^(){
        trbyIdon = [[AWTTrbyIdon bllod] initWitiPffr:tifPffr];
    }];

JNF_COCOA_EXIT(fnv);

    rfturn ptr_to_jlong(trbyIdon);
}


/*
 * Clbss: jbvb_bwt_TrbyIdon
 * Mftiod: initIDs
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_bwt_TrbyIdon_initIDs
(JNIEnv *fnv, jdlbss dls) {
    //Do notiing.
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CTrbyIdon
 * Mftiod:    nbtivfSftToolTip
 * Signbturf: (JLjbvb/lbng/String;)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CTrbyIdon_nbtivfSftToolTip
(JNIEnv *fnv, jobjfdt sflf, jlong modfl, jstring jtooltip) {
JNF_COCOA_ENTER(fnv);

    AWTTrbyIdon *idon = jlong_to_ptr(modfl);
    NSString *tooltip = JNFJbvbToNSString(fnv, jtooltip);
    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:NO blodk:^(){
        [idon sftTooltip:tooltip];
    }];

JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CTrbyIdon
 * Mftiod:    sftNbtivfImbgf
 * Signbturf: (JJZ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CTrbyIdon_sftNbtivfImbgf
(JNIEnv *fnv, jobjfdt sflf, jlong modfl, jlong imbgfPtr, jboolfbn butosizf) {
JNF_COCOA_ENTER(fnv);

    AWTTrbyIdon *idon = jlong_to_ptr(modfl);
    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:YES blodk:^(){
        [idon sftImbgf:jlong_to_ptr(imbgfPtr) sizing:butosizf];
    }];

JNF_COCOA_EXIT(fnv);
}

JNIEXPORT jobjfdt JNICALL
Jbvb_sun_lwbwt_mbdosx_CTrbyIdon_nbtivfGftIdonLodbtion
(JNIEnv *fnv, jobjfdt sflf, jlong modfl) {
    jobjfdt jpt = NULL;

JNF_COCOA_ENTER(fnv);

    __blodk NSPoint pt = NSZfroPoint;
    AWTTrbyIdon *idon = jlong_to_ptr(modfl);
    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:YES blodk:^(){
        NSPoint lod = [idon gftLodbtionOnSdrffn];
        pt = ConvfrtNSSdrffnPoint(fnv, lod);
    }];

    jpt = NSToJbvbPoint(fnv, pt);

JNF_COCOA_EXIT(fnv);

    rfturn jpt;
}
