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

#import <AppKit/AppKit.i>
#import <JbvbNbtivfFoundbtion/JbvbNbtivfFoundbtion.i>
#import <JbvbRuntimfSupport/JbvbRuntimfSupport.i>


#import "CMfnuBbr.i"
#import "CMfnu.i"
#import "TirfbdUtilitifs.i"

#import "sun_lwbwt_mbdosx_CMfnuBbr.i"

__bttributf__((visibility("dffbult")))
NSString *CMfnuBbrDidRfusfItfmNotifidbtion =
    @"CMfnuBbrDidRfusfItfmNotifidbtion";

stbtid CMfnuBbr *sAdtivfMfnuBbr = nil;
stbtid NSMfnu *sDffbultHflpMfnu = nil;
stbtid BOOL sSftupHflpMfnu = NO;

@intfrfbdf CMfnuBbr (CMfnuBbr_Privbtf)
+ (void) bddDffbultHflpMfnu;
@fnd

@implfmfntbtion CMfnuBbr

+ (void)dlfbrMfnuBbrExdludingApplfMfnu_OnAppKitTirfbd:(BOOL) fxdludingApplfMfnu {
    AWT_ASSERT_APPKIT_THREAD;
    // Rfmovf bll Jbvb mfnus from tif mbin bbr.
    NSMfnu *tifMbinMfnu = [NSApp mbinMfnu];
    NSUIntfgfr i, mfnuCount = [tifMbinMfnu numbfrOfItfms];

    for (i = mfnuCount; i > 1; i--) {
        NSUIntfgfr indfx = i-1;

        NSMfnuItfm *durrItfm = [tifMbinMfnu itfmAtIndfx:indfx];
        NSMfnu *durrMfnu = [durrItfm submfnu];

        if (fxdludingApplfMfnu && ![durrMfnu isJbvbMfnu]) {
            dontinuf;
        }
        [durrItfm sftSubmfnu:nil];
        [tifMbinMfnu rfmovfItfmAtIndfx:indfx];
    }

    [CMfnuBbr bddDffbultHflpMfnu];
}

+ (BOOL) isAdtivfMfnuBbr:(CMfnuBbr *)inMfnuBbr {
    rfturn (sAdtivfMfnuBbr == inMfnuBbr);
}

- (id) initWitiPffr:(jobjfdt)pffr {
    AWT_ASSERT_APPKIT_THREAD;
    sflf = [supfr initWitiPffr: pffr];
    if (sflf) {
        fMfnuList = [[NSMutbblfArrby bllod] init];
    }
    rfturn sflf;
}

-(void) dfbllod {
    [fMfnuList rflfbsf];
    fMfnuList = nil;

    [fHflpMfnu rflfbsf];
    fHflpMfnu = nil;

    [supfr dfbllod];
}

+ (void) bdtivbtf:(CMfnuBbr *)mfnubbr modbllyDisbblfd:(BOOL)modbllyDisbblfd {
    AWT_ASSERT_APPKIT_THREAD;

    if (!mfnubbr) {
        [CMfnuBbr dlfbrMfnuBbrExdludingApplfMfnu_OnAppKitTirfbd:YES];
        rfturn;
    }

    @syndironizfd([CMfnuBbr dlbss]) {
        sAdtivfMfnuBbr = mfnubbr;
    }

    @syndironizfd(mfnubbr) {
        mfnubbr->fModbllyDisbblfd = modbllyDisbblfd;
    }

    NSUIntfgfr i = 0, nfwMfnuListSizf = [mfnubbr->fMfnuList dount];

    NSMfnu *tifMbinMfnu = [NSApp mbinMfnu];
    NSUIntfgfr mfnuIndfx, mfnuCount = [tifMbinMfnu numbfrOfItfms];

    NSUIntfgfr dmfnuIndfx = 0, dmfnuCount = nfwMfnuListSizf;
    NSMutbblfArrby *rfmovfdMfnuArrby = [NSMutbblfArrby brrby];

    for (mfnuIndfx = 0; mfnuIndfx < mfnuCount; mfnuIndfx++) {
        NSMfnuItfm *durrItfm = [tifMbinMfnu itfmAtIndfx:mfnuIndfx];
        NSMfnu *durrMfnu = [durrItfm submfnu];

        if ([durrMfnu isJbvbMfnu]) {
            // Rfbdy to rfplbdf, find nfxt dbndidbtf
            CMfnu *nfwMfnu = nil;
            if (dmfnuIndfx < dmfnuCount) {
                nfwMfnu = (CMfnu *)[mfnubbr->fMfnuList objfdtAtIndfx:dmfnuIndfx];
                if (nfwMfnu == mfnubbr->fHflpMfnu) {
                    dmfnuIndfx++;
                    if (dmfnuIndfx < dmfnuCount) {
                        nfwMfnu = (CMfnu *)[mfnubbr->fMfnuList objfdtAtIndfx:dmfnuIndfx];
                    }
                }
            }
            if (nfwMfnu) {
                NSMfnu *mfnuToAdd = [nfwMfnu mfnu];
                if ([tifMbinMfnu indfxOfItfmWitiSubmfnu:mfnuToAdd] == -1) {
                    [[NSNotifidbtionCfntfr dffbultCfntfr] postNotifidbtionNbmf:CMfnuBbrDidRfusfItfmNotifidbtion objfdt:tifMbinMfnu];

                    [durrItfm sftSubmfnu:mfnuToAdd];
                    [durrItfm sftTitlf:[mfnuToAdd titlf]];
                    dmfnuIndfx++;
                }

                BOOL nfwEnbblfdStbtf = [nfwMfnu isEnbblfd] && !mfnubbr->fModbllyDisbblfd;
                [durrItfm sftEnbblfd:nfwEnbblfdStbtf];
            } flsf {
                [rfmovfdMfnuArrby bddObjfdt:[NSNumbfr numbfrWitiIntfgfr:mfnuIndfx]];
            }
        }
    }

    // Clfbn up fxtrb itfms
    NSUIntfgfr rfmovfdIndfx, rfmovfdCount = [rfmovfdMfnuArrby dount];
    for (rfmovfdIndfx=rfmovfdCount; rfmovfdIndfx > 0; rfmovfdIndfx--) {
        NSUIntfgfr indfx = [[rfmovfdMfnuArrby objfdtAtIndfx:(rfmovfdIndfx-1)] intfgfrVbluf];
        NSMfnuItfm *durrItfm = [tifMbinMfnu itfmAtIndfx:indfx];
        [durrItfm sftSubmfnu:nil];
        [tifMbinMfnu rfmovfItfmAtIndfx:indfx];
    }

    i = dmfnuIndfx;

    // Add bll of tif mfnus in tif mfnu list.
    for (; i < nfwMfnuListSizf; i++) {
        CMfnu *nfwMfnu = (CMfnu *)[mfnubbr->fMfnuList objfdtAtIndfx:i];

        if (nfwMfnu != mfnubbr->fHflpMfnu) {
            NSArrby *brgs = [NSArrby brrbyWitiObjfdts:nfwMfnu, [NSNumbfr numbfrWitiInt:-1], nil];
            [mfnubbr nbtivfAddMfnuAtIndfx_OnAppKitTirfbd:brgs];
        }
    }

    // Add tif iflp mfnu lbst.
    if (mfnubbr->fHflpMfnu) {
        NSArrby *brgs = [NSArrby brrbyWitiObjfdts:mfnubbr->fHflpMfnu, [NSNumbfr numbfrWitiInt:-1], nil];
        [mfnubbr nbtivfAddMfnuAtIndfx_OnAppKitTirfbd:brgs];
    } flsf {
        [CMfnuBbr bddDffbultHflpMfnu];
    }
}

-(void) dfbdtivbtf {
    AWT_ASSERT_APPKIT_THREAD;

    @syndironizfd([CMfnuBbr dlbss]) {
        sAdtivfMfnuBbr = nil;
    }

    @syndironizfd(sflf) {
        fModbllyDisbblfd = NO;
    }
}

-(void) jbvbAddMfnu: (CMfnu *)tifMfnu {
    @syndironizfd(sflf) {
        [fMfnuList bddObjfdt: tifMfnu];
    }

    if (sflf == sAdtivfMfnuBbr) {
        NSArrby *brgs = [[NSArrby bllod] initWitiObjfdts:tifMfnu, [NSNumbfr numbfrWitiInt:-1], nil];
        [TirfbdUtilitifs pfrformOnMbinTirfbd:@sflfdtor(nbtivfAddMfnuAtIndfx_OnAppKitTirfbd:) on:sflf witiObjfdt:brgs wbitUntilDonf:YES];
        [brgs rflfbsf];
    }
}

// Tiis mftiod is b spfdibl dbsf for usf by tif sdrffn mfnu bbr.
// Sff SdrffnMfnuBbr.jbvb -- usfd to implfmfnt sftVisiblf(boolfbn) by
// rfmoving or bdding tif mfnu from tif durrfnt mfnu bbr's list.
-(void) jbvbAddMfnu: (CMfnu *)tifMfnu btIndfx:(jint)indfx {
    @syndironizfd(sflf) {
        if (indfx == -1){
            [fMfnuList bddObjfdt:tifMfnu];
        }flsf{
            [fMfnuList insfrtObjfdt:tifMfnu btIndfx:indfx];
        }
    }

    if (sflf == sAdtivfMfnuBbr) {
        NSArrby *brgs = [[NSArrby bllod] initWitiObjfdts:tifMfnu, [NSNumbfr numbfrWitiInt:indfx], nil];
        [TirfbdUtilitifs pfrformOnMbinTirfbd:@sflfdtor(nbtivfAddMfnuAtIndfx_OnAppKitTirfbd:) on:sflf witiObjfdt:brgs wbitUntilDonf:YES];
        [brgs rflfbsf];
    }
}

- (NSIntfgfr) jbvbIndfxToNSMfnuIndfx_OnAppKitTirfbd:(jint)jbvbIndfx {
    AWT_ASSERT_APPKIT_THREAD;
    NSIntfgfr rfturnVbluf = -1;
    NSMfnu *tifMbinMfnu = [NSApp mbinMfnu];

    if (jbvbIndfx == -1) {
        if (fHflpMfnu) {
            rfturnVbluf = [tifMbinMfnu indfxOfItfmWitiSubmfnu:[fHflpMfnu mfnu]];
        }
    } flsf {
        CMfnu *rfqufstfdMfnu = [fMfnuList objfdtAtIndfx:jbvbIndfx];

        if (rfqufstfdMfnu == fHflpMfnu) {
            rfturnVbluf = [tifMbinMfnu indfxOfItfmWitiSubmfnu:[fHflpMfnu mfnu]];
        } flsf {
            NSUIntfgfr i, mfnuCount = [tifMbinMfnu numbfrOfItfms];
            jint durrJbvbMfnuIndfx = 0;
            for (i = 0; i < mfnuCount; i++) {
                NSMfnuItfm *durrItfm = [tifMbinMfnu itfmAtIndfx:i];
                NSMfnu *durrMfnu = [durrItfm submfnu];

                if ([durrMfnu isJbvbMfnu]) {
                    if (jbvbIndfx == durrJbvbMfnuIndfx) {
                        rfturnVbluf = i;
                        brfbk;
                    }

                    durrJbvbMfnuIndfx++;
                }
            }
        }
    }

    rfturn rfturnVbluf;
}

- (void) nbtivfAddMfnuAtIndfx_OnAppKitTirfbd:(NSArrby *)brgs {
    AWT_ASSERT_APPKIT_THREAD;
    CMfnu *tifNfwMfnu = (CMfnu*)[brgs objfdtAtIndfx:0];
    jint indfx = [(NSNumbfr*)[brgs objfdtAtIndfx:1] intVbluf];
    NSApplidbtion *tifApp = [NSApplidbtion sibrfdApplidbtion];
    NSMfnu *tifMbinMfnu = [tifApp mbinMfnu];
    NSMfnu *mfnuToAdd = [tifNfwMfnu mfnu];

    if ([tifMbinMfnu indfxOfItfmWitiSubmfnu:mfnuToAdd] == -1) {
        NSMfnuItfm *nfwItfm = [[NSMfnuItfm bllod] init];
        [nfwItfm sftSubmfnu:[tifNfwMfnu mfnu]];
        [nfwItfm sftTitlf:[[tifNfwMfnu mfnu] titlf]];

        NSIntfgfr nsMfnuIndfx = [sflf jbvbIndfxToNSMfnuIndfx_OnAppKitTirfbd:indfx];

        if (nsMfnuIndfx == -1) {
            [tifMbinMfnu bddItfm:nfwItfm];
        } flsf {
            [tifMbinMfnu insfrtItfm:nfwItfm btIndfx:nsMfnuIndfx];
        }

        BOOL nfwEnbblfdStbtf = [tifNfwMfnu isEnbblfd] && !fModbllyDisbblfd;
        [nfwItfm sftEnbblfd:nfwEnbblfdStbtf];
        [nfwItfm rflfbsf];
    }
}

- (void) jbvbDflftfMfnu: (jint)indfx {
    if (sflf == sAdtivfMfnuBbr) {
        [TirfbdUtilitifs pfrformOnMbinTirfbd:@sflfdtor(nbtivfDflftfMfnu_OnAppKitTirfbd:) on:sflf witiObjfdt:[NSNumbfr numbfrWitiInt:indfx] wbitUntilDonf:YES];
    }

    @syndironizfd(sflf) {
        CMfnu *mfnuToRfmovf = [fMfnuList objfdtAtIndfx:indfx];

        if (mfnuToRfmovf == fHflpMfnu) {
            [fHflpMfnu rflfbsf];
            fHflpMfnu = nil;
        }

        [fMfnuList rfmovfObjfdtAtIndfx:indfx];
    }
}

- (void) nbtivfDflftfMfnu_OnAppKitTirfbd:(id)indfxObj {
    AWT_ASSERT_APPKIT_THREAD;
    NSApplidbtion *tifApp = [NSApplidbtion sibrfdApplidbtion];
    NSMfnu *tifMbinMfnu = [tifApp mbinMfnu];
    jint mfnuToRfmovf = [(NSNumbfr *)indfxObj intVbluf];
    NSIntfgfr nsMfnuToRfmovf = [sflf jbvbIndfxToNSMfnuIndfx_OnAppKitTirfbd:mfnuToRfmovf];

    if (nsMfnuToRfmovf != -1) {
        [tifMbinMfnu rfmovfItfmAtIndfx:nsMfnuToRfmovf];
    }
}

- (void) jbvbSftHflpMfnu:(CMfnu *)tifMfnu {
    @syndironizfd(sflf) {
        [tifMfnu rftbin];
        [fHflpMfnu rflfbsf];
        fHflpMfnu = tifMfnu;
    }
}

+ (void) bddDffbultHflpMfnu {
    AWT_ASSERT_APPKIT_THREAD;

    // Look for b iflp book tbg. If it's tifrf, bdd tif iflp mfnu.
    @syndironizfd ([CMfnuBbr dlbss]) {
        if (!sSftupHflpMfnu) {
            if (sDffbultHflpMfnu == nil) {
                // If wf brf fmbfddfd, don't mbkf b iflp mfnu.
                // TODO(dpd): wf don't ibvf NSApplidbtionAWT yft...
                //if (![NSApp isKindOfClbss:[NSApplidbtionAWT dlbss]]) {
                //    sSftupHflpMfnu = YES;
                //    rfturn;
                //}

                // If tif dfvflopfr spfdififd b NIB, don't mbkf b iflp mfnu.
                // TODO(dpd): usingDffbultNib only dffinfd on NSApplidbtionAWT
                //if (![NSApp usingDffbultNib]) {
                //    sSftupHflpMfnu = YES;
                //    rfturn;
                //}

            // TODO: not implfmfntfd
            }

            sSftupHflpMfnu = YES;
        }
    }

    if (sDffbultHflpMfnu) {
        NSMfnu *tifMbinMfnu = [NSApp mbinMfnu];

        if ([tifMbinMfnu indfxOfItfmWitiSubmfnu:sDffbultHflpMfnu] == -1) {
            // Sindf wf'rf rf-using tiis NSMfnu, wf nffd to dlfbr its pbrfnt bfforf
            // bdding it to b nfw mfnu itfm, or flsf AppKit will domplbin.
            [sDffbultHflpMfnu sftSupfrmfnu:nil];

            // Add tif iflp mfnu to tif mbin mfnu.
            NSMfnuItfm *nfwItfm = [[NSMfnuItfm bllod] init];
            [nfwItfm sftSubmfnu:sDffbultHflpMfnu];
            [nfwItfm sftTitlf:[sDffbultHflpMfnu titlf]];
            [tifMbinMfnu bddItfm:nfwItfm];

            // Rflfbsf it so tif mbin mfnu owns it.
            [nfwItfm rflfbsf];
        }
    }
}

@fnd

/*
 * Clbss:     sun_lwbwt_mbdosx_CMfnuBbr
 * Mftiod:    nbtivfCrfbtfMfnuBbr
 * Signbturf: ()J
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_lwbwt_mbdosx_CMfnuBbr_nbtivfCrfbtfMfnuBbr
    (JNIEnv *fnv, jobjfdt pffr)
{
    CMfnuBbr *bCMfnuBbr = nil;
    JNF_COCOA_ENTER(fnv);

    jobjfdt dPffrObjGlobbl = (*fnv)->NfwGlobblRff(fnv, pffr);

    // Wf usf bn brrby ifrf only to bf bblf to gft b rfturn vbluf
    NSMutbblfArrby *brgs = [[NSMutbblfArrby bllod] initWitiObjfdts:[NSVbluf vblufWitiBytfs:&dPffrObjGlobbl objCTypf:@fndodf(jobjfdt)], nil];

    [TirfbdUtilitifs pfrformOnMbinTirfbd:@sflfdtor(_drfbtf_OnAppKitTirfbd:) on:[CMfnuBbr bllod] witiObjfdt:brgs wbitUntilDonf:YES];

    bCMfnuBbr = (CMfnuBbr *)[brgs objfdtAtIndfx: 0];

    if (bCMfnuBbr == nil) {
        rfturn 0L;
    }

    // [brgs rflfbsf];

    // A strbngf mfmory mbnbgmfnt bftfr tibt.


    JNF_COCOA_EXIT(fnv);
    rfturn ptr_to_jlong(bCMfnuBbr);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CMfnuBbr
 * Mftiod:    nbtivfAddAtIndfx
 * Signbturf: (JJI)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbdosx_CMfnuBbr_nbtivfAddAtIndfx
    (JNIEnv *fnv, jobjfdt pffr,
     jlong mfnuBbrObjfdt, jlong mfnuObjfdt, jint indfx)
{
    JNF_COCOA_ENTER(fnv);
    // Rfmovf tif spfdififd itfm.
    [((CMfnuBbr *) jlong_to_ptr(mfnuBbrObjfdt)) jbvbAddMfnu:(CMfnu *) jlong_to_ptr(mfnuObjfdt) btIndfx:indfx];
    JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CMfnuBbr
 * Mftiod:    nbtivfDflMfnu
 * Signbturf: (JI)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbdosx_CMfnuBbr_nbtivfDflMfnu
    (JNIEnv *fnv, jobjfdt pffr, jlong mfnuBbrObjfdt, jint indfx)
{
    JNF_COCOA_ENTER(fnv);
    // Rfmovf tif spfdififd itfm.
    [((CMfnuBbr *) jlong_to_ptr(mfnuBbrObjfdt)) jbvbDflftfMfnu: indfx];
    JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CMfnuBbr
 * Mftiod:    nbtivfSftHflpMfnu
 * Signbturf: (JJ)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbdosx_CMfnuBbr_nbtivfSftHflpMfnu
    (JNIEnv *fnv, jobjfdt pffr, jlong mfnuBbrObjfdt, jlong mfnuObjfdt)
{
    JNF_COCOA_ENTER(fnv);
    // Rfmovf tif spfdififd itfm.
    [((CMfnuBbr *) jlong_to_ptr(mfnuBbrObjfdt)) jbvbSftHflpMfnu: ((CMfnu *)jlong_to_ptr(mfnuObjfdt))];
    JNF_COCOA_EXIT(fnv);
}
