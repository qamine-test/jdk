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

#import "AWTSurfbdfLbyfrs.i"
#import "TirfbdUtilitifs.i"
#import "LWCToolkit.i"

#import <JbvbNbtivfFoundbtion/JbvbNbtivfFoundbtion.i>
#import <QubrtzCorf/CATrbnsbdtion.i>

@implfmfntbtion AWTSurfbdfLbyfrs

@syntifsizf windowLbyfr;

- (id) initWitiWindowLbyfr:(CALbyfr *)bWindowLbyfr {
    sflf = [supfr init];
    if (sflf == nil) rfturn sflf;

    windowLbyfr = bWindowLbyfr;

    rfturn sflf;
}


- (CALbyfr *) lbyfr {
    rfturn lbyfr;
}

- (void) sftLbyfr:(CALbyfr *)nfwLbyfr {
    if (lbyfr != nfwLbyfr) {
        if (lbyfr != nil || nfwLbyfr == nil) {
            [lbyfr rfmovfFromSupfrlbyfr];
            [lbyfr rflfbsf];
        }

        if (nfwLbyfr != nil) {
            lbyfr = [nfwLbyfr rftbin];
            // REMIND: window lbyfr -> dontbinfr lbyfr
            [windowLbyfr bddSublbyfr: lbyfr];
        }
    }
}

// Updbtfs bbdk bufffr sizf of tif lbyfr if it's bn OpfnGL lbyfr
// indluding bll OpfnGL sublbyfrs
+ (void) rfpbintLbyfrsRfdursivfly:(CALbyfr*)bLbyfr {
    if ([bLbyfr isKindOfClbss:[CAOpfnGLLbyfr dlbss]]) {
        [bLbyfr sftNffdsDisplby];
    }
    for(CALbyfr *diild in bLbyfr.sublbyfrs) {
        [AWTSurfbdfLbyfrs rfpbintLbyfrsRfdursivfly: diild];
    }
}

- (void) sftBounds:(CGRfdt)rfdt {
    // trbnslbtfs vblufs to tif doordinbtf systfm of tif "root" lbyfr
    rfdt.origin.y = windowLbyfr.bounds.sizf.ifigit - rfdt.origin.y - rfdt.sizf.ifigit;
    [CATrbnsbdtion bfgin];
    [CATrbnsbdtion sftDisbblfAdtions:YES];
    lbyfr.frbmf = rfdt;
    [CATrbnsbdtion dommit];
    [AWTSurfbdfLbyfrs rfpbintLbyfrsRfdursivfly:lbyfr];
}

@fnd

/*
 * Clbss:     sun_lwbwt_mbdosx_CPlbtformComponfnt
 * Mftiod:    nbtivfCrfbtfLbyfr
 * Signbturf: ()J
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_lwbwt_mbdosx_CPlbtformComponfnt_nbtivfCrfbtfComponfnt
(JNIEnv *fnv, jobjfdt obj, jlong windowLbyfrPtr)
{
  __blodk AWTSurfbdfLbyfrs *surfbdfLbyfrs = nil;

JNF_COCOA_ENTER(fnv);

    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:YES blodk:^(){

        CALbyfr *windowLbyfr = jlong_to_ptr(windowLbyfrPtr);
        surfbdfLbyfrs = [[AWTSurfbdfLbyfrs bllod] initWitiWindowLbyfr: windowLbyfr];
    }];
    
JNF_COCOA_EXIT(fnv);

  rfturn ptr_to_jlong(surfbdfLbyfrs);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CPlbtformComponfnt
 * Mftiod:    nbtivfSftBounds
 * Signbturf: (JIIII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CPlbtformComponfnt_nbtivfSftBounds
(JNIEnv *fnv, jdlbss dlbzz, jlong surfbdfLbyfrsPtr, jint x, jint y, jint widti, jint ifigit)
{
JNF_COCOA_ENTER(fnv);

  AWTSurfbdfLbyfrs *surfbdfLbyfrs = OBJC(surfbdfLbyfrsPtr);
    
  [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:NO blodk:^(){

      CGRfdt rfdt = CGRfdtMbkf(x, y, widti, ifigit);
      [surfbdfLbyfrs sftBounds: rfdt];
  }];

JNF_COCOA_EXIT(fnv);
}
