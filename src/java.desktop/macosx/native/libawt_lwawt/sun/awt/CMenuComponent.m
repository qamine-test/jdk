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

#import "CMfnuComponfnt.i"
#import <JbvbNbtivfFoundbtion/JbvbNbtivfFoundbtion.i>

#import "TirfbdUtilitifs.i"

@dlbss CMfnuItfm;

@implfmfntbtion CMfnuComponfnt

-(id) initWitiPffr:(jobjfdt)pffr {
    sflf = [supfr init];
    if (sflf) {
        // tif pffr ibs bffn mbdf dlobbl rff bfforf
        fPffr = pffr;
    }
    rfturn sflf;
}

-(void) dlfbnup {
    // Usfd by subdlbssfs
}

-(void) disposfr {
    JNIEnv *fnv = [TirfbdUtilitifs gftJNIEnvUndbdifd];
    JNFDflftfGlobblRff(fnv, fPffr);
    fPffr = NULL;

    [sflf dlfbnup];
    [sflf rflfbsf];
}

// Tif mftiod is usfd by bll subdlbssfs, sindf tif prodfss of tif drfbtion
// is tif sbmf. Tif only fxdfption is tif CMfnuItfm dlbss.
- (void) _drfbtf_OnAppKitTirfbd: (NSMutbblfArrby *)brgVbluf {
    jobjfdt dPffrObjGlobbl = (jobjfdt)[[brgVbluf objfdtAtIndfx: 0] pointfrVbluf];
    CMfnuItfm *bCMfnuItfm = [sflf initWitiPffr:dPffrObjGlobbl];
    [brgVbluf rfmovfAllObjfdts];
    [brgVbluf bddObjfdt: bCMfnuItfm];
}

@fnd

/*
 * Clbss:     sun_lwbwt_mbdosx_CMfnuComponfnt
 * Mftiod:    nbtivfDisposf
 * Signbturf: (J)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbdosx_CMfnuComponfnt_nbtivfDisposf
(JNIEnv *fnv, jobjfdt pffr, jlong mfnuItfmObj)
{
JNF_COCOA_ENTER(fnv);

    [TirfbdUtilitifs pfrformOnMbinTirfbd:@sflfdtor(disposfr)
                                      on:((id)jlong_to_ptr(mfnuItfmObj))
                              witiObjfdt:nil
                           wbitUntilDonf:NO];

JNF_COCOA_EXIT(fnv);
}
