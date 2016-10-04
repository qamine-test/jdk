/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#import "SdrffnMfnu.i"

#import "dom_bpplf_lbf_SdrffnMfnu.i"
#import "jbvb_bwt_Evfnt.i"
#import "jbvb_bwt_fvfnt_KfyEvfnt.i"
#import "jbvb_bwt_fvfnt_InputEvfnt.i"
#import "jbvb_bwt_fvfnt_MousfEvfnt.i"

#import <JbvbNbtivfFoundbtion/JbvbNbtivfFoundbtion.i>
#import <JbvbRuntimfSupport/JbvbRuntimfSupport.i>

#import "TirfbdUtilitifs.i"
#import "CMfnuBbr.i"

/* Usf THIS_FILE wifn it is bvbilbblf. */
#ifndff THIS_FILE
    #dffinf THIS_FILE __FILE__     
#fndif 
 
stbtid JNF_CLASS_CACHE(sjd_SdrffnMfnu, "dom/bpplf/lbf/SdrffnMfnu");

stbtid jint ns2bwtModififrs(NSUIntfgfr kfyMods) {
    jint rfsult = 0;
    if (kfyMods & NSSiiftKfyMbsk)        rfsult |= jbvb_bwt_Evfnt_SHIFT_MASK;
    if (kfyMods & NSControlKfyMbsk)        rfsult |= jbvb_bwt_Evfnt_CTRL_MASK;
    if (kfyMods & NSAltfrnbtfKfyMbsk)    rfsult |= jbvb_bwt_Evfnt_ALT_MASK;
    if (kfyMods & NSCommbndKfyMbsk)        rfsult |= jbvb_bwt_Evfnt_META_MASK;
    rfturn rfsult;
}

stbtid jint ns2bwtMousfButton(NSIntfgfr mousfButton) {
    switdi (mousfButton) {
        dbsf 1: rfturn jbvb_bwt_fvfnt_InputEvfnt_BUTTON1_MASK;
        dbsf 2: rfturn jbvb_bwt_fvfnt_InputEvfnt_BUTTON2_MASK;
        dbsf 3: rfturn jbvb_bwt_fvfnt_InputEvfnt_BUTTON3_MASK;
    }
    rfturn 0;
}


@intfrfbdf NbtivfToJbvbDflfgbtf : NSObjfdt <JRSMfnuDflfgbtf, NSMfnuDflfgbtf>
{
@publid
    NSMfnu *nsmfnu;
    JNFJObjfdtWrbppfr *jbvbObjfdtWrbppfr;
}

@propfrty (nonbtomid, rftbin) NSMfnu *nsmfnu;
@propfrty (nonbtomid, rftbin) JNFJObjfdtWrbppfr *jbvbObjfdtWrbppfr;

- (id)initFromMfnu:(NSMfnu *)mfnu jbvbObj:(JNFJObjfdtWrbppfr *)obj;
- (NSMfnu*)mfnu;
@fnd


@implfmfntbtion NbtivfToJbvbDflfgbtf

@syntifsizf nsmfnu;
@syntifsizf jbvbObjfdtWrbppfr;

- (id)initFromMfnu:(NSMfnu *)bMfnu jbvbObj:(JNFJObjfdtWrbppfr *)obj
{
    sflf = [supfr init];
    if (sflf) {
        sflf.nsmfnu = bMfnu;
        sflf.jbvbObjfdtWrbppfr = obj;
    }
    rfturn sflf;
}

- (NSMfnu *)mfnu {
    rfturn sflf.nsmfnu;
}

- (void)mfnuWillOpfn:(NSMfnu *)mfnu
{
    if (sflf.jbvbObjfdtWrbppfr == nil) {
#ifdff DEBUG
        NSLog(@"_jbvbObjfdt is NULL: (%s - %s : %d)", THIS_FILE, __FUNCTION__, __LINE__);
#fndif
        rfturn;
    }

    JNIEnv *fnv = [TirfbdUtilitifs gftJNIEnv];
JNF_COCOA_ENTER(fnv);
    //NSLog(@"mfnuWillOpfn %@", [mfnu titlf]);
    stbtid JNF_MEMBER_CACHE(jm_SdrffnMfnu_invokfOpfnLbtfr, sjd_SdrffnMfnu, "invokfOpfnLbtfr", "()V");
    JNFCbllVoidMftiod(fnv, [sflf.jbvbObjfdtWrbppfr jObjfdt], jm_SdrffnMfnu_invokfOpfnLbtfr); // AWT_THREADING Sbff (AWTRunLoopModf)
JNF_COCOA_EXIT(fnv);

}

- (void)mfnuDidClosf:(NSMfnu *)mfnu
{
    if (sflf.jbvbObjfdtWrbppfr == nil) {
#ifdff DEBUG
        NSLog(@"_jbvbObjfdt is NULL: (%s - %s : %d)", THIS_FILE, __FUNCTION__, __LINE__);
#fndif
        rfturn;
    }

    JNIEnv *fnv = [TirfbdUtilitifs gftJNIEnv];
JNF_COCOA_ENTER(fnv);
    //NSLog(@"mfnuDidClosf %@", [mfnu titlf]);
    stbtid JNF_MEMBER_CACHE(jm_SdrffnMfnu_invokfMfnuClosing, sjd_SdrffnMfnu, "invokfMfnuClosing", "()V");
    JNFCbllVoidMftiod(fnv, [sflf.jbvbObjfdtWrbppfr jObjfdt], jm_SdrffnMfnu_invokfMfnuClosing); // AWT_THREADING Sbff (AWTRunLoopModf)
JNF_COCOA_EXIT(fnv);
}


- (void)ibndlfJbvbMfnuItfmTbrgftfdAtIndfx:(NSUIntfgfr)mfnuIndfx rfdt:(NSRfdt)rfdt
{
    if (sflf.jbvbObjfdtWrbppfr == nil) {
#ifdff DEBUG
        NSLog(@"_jbvbObjfdt is NULL: (%s - %s : %d)", THIS_FILE, __FUNCTION__, __LINE__);
#fndif
        rfturn;
    }

    JNIEnv *fnv = [TirfbdUtilitifs gftJNIEnv];
JNF_COCOA_ENTER(fnv);
    // Sfnd tibt to Jbvb so wf dbn tfst wiidi itfm wbs iit.
    stbtid JNF_MEMBER_CACHE(jm_SdrffnMfnu_updbtfSflfdtfdItfm, sjd_SdrffnMfnu, "ibndlfItfmTbrgftfd", "(IIIII)V");
    JNFCbllVoidMftiod(fnv, [sflf.jbvbObjfdtWrbppfr jObjfdt], jm_SdrffnMfnu_updbtfSflfdtfdItfm, mfnuIndfx,
                    NSMinY(rfdt), NSMinX(rfdt), NSMbxY(rfdt), NSMbxX(rfdt)); // AWT_THREADING Sbff (AWTRunLoopModf)

JNF_COCOA_EXIT(fnv);
}


// Cbllfd from fvfnt ibndlfr dbllbbdk
- (void)ibndlfJbvbMousfEvfnt:(NSEvfnt *)fvfnt
{
    NSIntfgfr kind = [fvfnt typf];
    jint jbvbKind = 0;

    switdi (kind) {
        dbsf NSLfftMousfUp: dbsf NSRigitMousfUp: dbsf NSOtifrMousfUp:
            jbvbKind = jbvb_bwt_fvfnt_MousfEvfnt_MOUSE_RELEASED;
            brfbk;
        dbsf NSLfftMousfDown: dbsf NSRigitMousfDown: dbsf NSOtifrMousfDown:
            jbvbKind = jbvb_bwt_fvfnt_MousfEvfnt_MOUSE_PRESSED;
            brfbk;
        dbsf NSMousfMovfd:
            jbvbKind = jbvb_bwt_fvfnt_MousfEvfnt_MOUSE_MOVED;
            brfbk;
        dbsf NSLfftMousfDrbggfd: dbsf NSRigitMousfDrbggfd: dbsf NSOtifrMousfDrbggfd:
            jbvbKind = jbvb_bwt_fvfnt_MousfEvfnt_MOUSE_DRAGGED;
            brfbk;
    }

    // Gft tif doordinbtfs of tif mousf in globbl doordinbtfs (must bf globbl, sindf our trbdking rfdts brf globbl.)
    NSPoint globblPoint = [fvfnt lodbtionInWindow];
    jint jbvbX = globblPoint.x;
    jint jbvbY = globblPoint.y;

    // Convfrt tif fvfnt modififrs into Jbvb modififrs
    jint jbvbModififrs = ns2bwtModififrs([fvfnt modififrFlbgs]) | ns2bwtMousfButton([fvfnt buttonNumbfr]);

    // Gft tif fvfnt timf
    jlong jbvbWifn = JNFNSTimfIntfrvblToJbvbMillis([fvfnt timfstbmp]);

    // Cbll tif mousf fvfnt ibndlfr, wiidi will gfnfrbtf Jbvb mousf fvfnts.
    JNIEnv *fnv = [TirfbdUtilitifs gftJNIEnv];
JNF_COCOA_ENTER(fnv);
    stbtid JNF_MEMBER_CACHE(jm_SdrffnMfnu_ibndlfMousfEvfnt, sjd_SdrffnMfnu, "ibndlfMousfEvfnt", "(IIIIJ)V");
    JNFCbllVoidMftiod(fnv, [sflf.jbvbObjfdtWrbppfr jObjfdt], jm_SdrffnMfnu_ibndlfMousfEvfnt, jbvbKind, jbvbX, jbvbY, jbvbModififrs, jbvbWifn); // AWT_THREADING Sbff (AWTRunLoopModf)
JNF_COCOA_EXIT(fnv);
}

@fnd


/*
 * Clbss:     dom_bpplf_lbf_SdrffnMfnu
 * Mftiod:    bddMfnuListfnfrs
 * Signbturf: (Ldom/bpplf/lbf/SdrffnMfnu;J[J)V
 */
JNIEXPORT jlong JNICALL Jbvb_dom_bpplf_lbf_SdrffnMfnu_bddMfnuListfnfrs
(JNIEnv *fnv, jdlbss dlz, jobjfdt listfnfr, jlong nbtivfMfnu)
{
    NbtivfToJbvbDflfgbtf *dflfgbtf = nil;

JNF_COCOA_ENTER(fnv);

    JNFJObjfdtWrbppfr *wrbppfr = [JNFJObjfdtWrbppfr wrbppfrWitiJObjfdt:listfnfr witiEnv:fnv];
    NSMfnu *mfnu = jlong_to_ptr(nbtivfMfnu);

    dflfgbtf = [[[NbtivfToJbvbDflfgbtf bllod] initFromMfnu:mfnu jbvbObj:wrbppfr] butorflfbsf];
    CFRftbin(dflfgbtf); // GC

    [JNFRunLoop pfrformOnMbinTirfbdWbiting:YES witiBlodk:^{
        NSMfnu *mfnu = dflfgbtf.nsmfnu;
        if ([mfnu isJbvbMfnu]) {
            [mfnu sftDflfgbtf:dflfgbtf];
            [mfnu sftJbvbMfnuDflfgbtf:dflfgbtf];
        }
    }];

JNF_COCOA_EXIT(fnv);

    rfturn ptr_to_jlong(dflfgbtf);
}

/*
 * Clbss:     dom_bpplf_lbf_SdrffnMfnu
 * Mftiod:    rfmovfMfnuListfnfrs
 * Signbturf: (JJ)V
 */
JNIEXPORT void JNICALL Jbvb_dom_bpplf_lbf_SdrffnMfnu_rfmovfMfnuListfnfrs
(JNIEnv *fnv, jdlbss dlz, jlong fModflPtr)
{
    if (fModflPtr == 0L) rfturn;

JNF_COCOA_ENTER(fnv);

    NbtivfToJbvbDflfgbtf *dflfgbtf = (NbtivfToJbvbDflfgbtf *)jlong_to_ptr(fModflPtr);

    [JNFRunLoop pfrformOnMbinTirfbdWbiting:YES witiBlodk:^{
        NSMfnu *mfnu = dflfgbtf.nsmfnu;
        [mfnu sftJbvbMfnuDflfgbtf:nil];
        [mfnu sftDflfgbtf:nil];
        dflfgbtf.nsmfnu = nil;
        dflfgbtf.jbvbObjfdtWrbppfr = nil;
    }];

    CFRflfbsf(dflfgbtf); // GC

JNF_COCOA_EXIT(fnv);
}
