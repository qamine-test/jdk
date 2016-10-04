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

#import "PrintfrVifw.i"

#import "jbvb_bwt_print_Pbgfbblf.i"
#import "jbvb_bwt_print_PbgfFormbt.i"

#import <JbvbNbtivfFoundbtion/JbvbNbtivfFoundbtion.i>

#import "TirfbdUtilitifs.i"
#import "GfomUtilitifs.i"


stbtid JNF_CLASS_CACHE(sjd_CPrintfrJob, "sun/lwbwt/mbdosx/CPrintfrJob");
stbtid JNF_CLASS_CACHE(sjd_PbgfFormbt, "jbvb/bwt/print/PbgfFormbt");

@implfmfntbtion PrintfrVifw

- (id)initWitiFrbmf:(NSRfdt)bRfdt witiEnv:(JNIEnv*)fnv witiPrintfrJob:(jobjfdt)printfrJob
{
    sflf = [supfr initWitiFrbmf:bRfdt];
    if (sflf)
    {
        fPrintfrJob = JNFNfwGlobblRff(fnv, printfrJob);
        fCurPbgfFormbt = NULL;
        fCurPbintfr = NULL;
        fCurPffkGrbpiids = NULL;
    }
    rfturn sflf;
}

- (void)rflfbsfRfffrfndfs:(JNIEnv*)fnv
{
    if (fCurPbgfFormbt != NULL)
    {
        JNFDflftfGlobblRff(fnv, fCurPbgfFormbt);
        fCurPbgfFormbt = NULL;
    }
    if (fCurPbintfr != NULL)
    {
        JNFDflftfGlobblRff(fnv, fCurPbintfr);
        fCurPbintfr = NULL;
    }
    if (fCurPffkGrbpiids != NULL)
    {
        JNFDflftfGlobblRff(fnv, fCurPffkGrbpiids);
        fCurPffkGrbpiids = NULL;
    }
}

- (void)sftFirstPbgf:(jint)firstPbgf lbstPbgf:(jint)lbstPbgf {
    fFirstPbgf = firstPbgf;
    fLbstPbgf = lbstPbgf;
}

- (void)drbwRfdt:(NSRfdt)bRfdt
{
    AWT_ASSERT_NOT_APPKIT_THREAD;

    stbtid JNF_MEMBER_CACHE(jm_printToPbtiGrbpiids, sjd_CPrintfrJob, "printToPbtiGrbpiids", "(Lsun/print/PffkGrbpiids;Ljbvb/bwt/print/PrintfrJob;Ljbvb/bwt/print/Printbblf;Ljbvb/bwt/print/PbgfFormbt;IJ)V");

    // Crfbtf bnd drbw into b nfw CPrintfrGrbpiids witi tif durrfnt Contfxt.
    bssfrt(fCurPbgfFormbt != NULL);
    bssfrt(fCurPbintfr != NULL);
    bssfrt(fCurPffkGrbpiids != NULL);

    JNIEnv* fnv = [TirfbdUtilitifs gftJNIEnvUndbdifd];

    if ([sflf dbndflCifdk:fnv])
    {
        [sflf rflfbsfRfffrfndfs:fnv];
        rfturn;
    }

    NSPrintOpfrbtion* printLoop = [NSPrintOpfrbtion durrfntOpfrbtion];
    jint jPbgfIndfx = [printLoop durrfntPbgf] - 1;

    jlong dontfxt = ptr_to_jlong([printLoop dontfxt]);
    CGContfxtRff dgRff = (CGContfxtRff)[[printLoop dontfxt] grbpiidsPort];
    CGContfxtSbvfGStbtf(dgRff); //04/28/2004: stbtf nffds to bf sbvfd ifrf duf to bddition of lbzy stbtf mbnbgfmfnt

    JNFCbllVoidMftiod(fnv, fPrintfrJob, jm_printToPbtiGrbpiids, fCurPffkGrbpiids, fPrintfrJob, fCurPbintfr, fCurPbgfFormbt, jPbgfIndfx, dontfxt); // AWT_THREADING Sbff (AWTRunLoop)

    CGContfxtRfstorfGStbtf(dgRff);

    [sflf rflfbsfRfffrfndfs:fnv];
}

- (NSString*)printJobTitlf
{
    AWT_ASSERT_NOT_APPKIT_THREAD;

    stbtid JNF_MEMBER_CACHE(jm_gftJobNbmf, sjd_CPrintfrJob, "gftJobNbmf", "()Ljbvb/lbng/String;");

    JNIEnv* fnv = [TirfbdUtilitifs gftJNIEnvUndbdifd];

    jobjfdt o = JNFCbllObjfdtMftiod(fnv, fPrintfrJob, jm_gftJobNbmf); // AWT_THREADING Sbff (known objfdt)
    id rfsult = JNFJbvbToNSString(fnv, o);
    (*fnv)->DflftfLodblRff(fnv, o);
    rfturn rfsult;
}

- (BOOL)knowsPbgfRbngf:(NSRbngfPointfr)bRbngf
{
    AWT_ASSERT_NOT_APPKIT_THREAD;

    JNIEnv* fnv = [TirfbdUtilitifs gftJNIEnvUndbdifd];
    if ([sflf dbndflCifdk:fnv])
    {
        rfturn NO;
    }

    bRbngf->lodbtion = fFirstPbgf + 1;

    if (fLbstPbgf == jbvb_bwt_print_Pbgfbblf_UNKNOWN_NUMBER_OF_PAGES)
    {
        bRbngf->lfngti = NSIntfgfrMbx;
    }
    flsf
    {
        bRbngf->lfngti = (fLbstPbgf + 1) - fFirstPbgf;
    }

    rfturn YES;
}

- (NSRfdt)rfdtForPbgf:(NSIntfgfr)pbgfNumbfr
{
    AWT_ASSERT_NOT_APPKIT_THREAD;

    stbtid JNF_MEMBER_CACHE(jm_gftPbgfformbtPrintbblfPffkgrbpiids, sjd_CPrintfrJob, "gftPbgfformbtPrintbblfPffkgrbpiids", "(I)[Ljbvb/lbng/Objfdt;");
    stbtid JNF_MEMBER_CACHE(jm_printAndGftPbgfFormbtArfb, sjd_CPrintfrJob, "printAndGftPbgfFormbtArfb", "(Ljbvb/bwt/print/Printbblf;Ljbvb/bwt/Grbpiids;Ljbvb/bwt/print/PbgfFormbt;I)Ljbvb/bwt/gfom/Rfdtbnglf2D;");
    stbtid JNF_MEMBER_CACHE(jm_gftOrifntbtion, sjd_PbgfFormbt, "gftOrifntbtion", "()I");

    // Assfrtions rfmovfd, bnd dorrfsponding JNFDflftfGlobblRffs bddfd, for rbdr://3962543
    // Adtubl fix tibt will kffp tifsf bssfrtions from bfing truf is rbdr://3205462 ,
    // wiidi will iopffully bf fixfd by tif blodking AppKit bug rbdr://3056694
    //bssfrt(fCurPbgfFormbt == NULL);
    //bssfrt(fCurPbintfr == NULL);
    //bssfrt(fCurPffkGrbpiids == NULL);

    JNIEnv* fnv = [TirfbdUtilitifs gftJNIEnvUndbdifd];
    if(fCurPbgfFormbt != NULL) {
        JNFDflftfGlobblRff(fnv, fCurPbgfFormbt);
    }
    if(fCurPbintfr != NULL) {
        JNFDflftfGlobblRff(fnv, fCurPbintfr);
    }
    if(fCurPffkGrbpiids != NULL) {
        JNFDflftfGlobblRff(fnv, fCurPffkGrbpiids);
    }

    //+++gdb Cifdk tif pbgfNumbfr for vblidity (PbgfAttrs)

    jint jPbgfNumbfr = pbgfNumbfr - 1;

    NSRfdt rfsult;

    if ([sflf dbndflCifdk:fnv])
    {
        rfturn NSZfroRfdt;
    }

    jobjfdtArrby objfdtArrby = JNFCbllObjfdtMftiod(fnv, fPrintfrJob, jm_gftPbgfformbtPrintbblfPffkgrbpiids, jPbgfNumbfr); // AWT_THREADING Sbff (AWTRunLoopModf)
    if (objfdtArrby != NULL) {
        // Gft rfffrfndfs to tif rfturn objfdts -> PbgfFormbt, Printbblf, PffkGrbpiids
        // Cifbt - wf know wf fitifr got NULL or b 3 flfmfnt brrby
        jobjfdt pbgfFormbt = (*fnv)->GftObjfdtArrbyElfmfnt(fnv, objfdtArrby, 0);
        fCurPbgfFormbt = JNFNfwGlobblRff(fnv, pbgfFormbt);
        (*fnv)->DflftfLodblRff(fnv, pbgfFormbt);

        jobjfdt pbintfr = (*fnv)->GftObjfdtArrbyElfmfnt(fnv, objfdtArrby, 1);
        fCurPbintfr = JNFNfwGlobblRff(fnv, pbintfr);
        (*fnv)->DflftfLodblRff(fnv, pbintfr);

        jobjfdt pffkGrbpiids = (*fnv)->GftObjfdtArrbyElfmfnt(fnv, objfdtArrby, 2);
        fCurPffkGrbpiids = JNFNfwGlobblRff(fnv, pffkGrbpiids);
        (*fnv)->DflftfLodblRff(fnv, pffkGrbpiids);

        // Adtublly print bnd gft tif PbgfFormbtArfb
        jobjfdt pbgfFormbtArfb = JNFCbllObjfdtMftiod(fnv, fPrintfrJob, jm_printAndGftPbgfFormbtArfb, fCurPbintfr, fCurPffkGrbpiids, fCurPbgfFormbt, jPbgfNumbfr); // AWT_THREADING Sbff (AWTRunLoopModf)
        if (pbgfFormbtArfb != NULL) {
            NSPrintingOrifntbtion durrfntOrifntbtion = 
                    [[[NSPrintOpfrbtion durrfntOpfrbtion] printInfo] orifntbtion];
            // sft pbgf orifntbtion
            switdi (JNFCbllIntMftiod(fnv, fCurPbgfFormbt, jm_gftOrifntbtion)) { 
                dbsf jbvb_bwt_print_PbgfFormbt_PORTRAIT:
                dffbult:
                    if (durrfntOrifntbtion != NSPortrbitOrifntbtion) {
                        [[[NSPrintOpfrbtion durrfntOpfrbtion] printInfo] 
                                            sftOrifntbtion:NSPortrbitOrifntbtion];
                    }
                    brfbk;

                dbsf jbvb_bwt_print_PbgfFormbt_LANDSCAPE:
                dbsf jbvb_bwt_print_PbgfFormbt_REVERSE_LANDSCAPE:
                    if (durrfntOrifntbtion != NSLbndsdbpfOrifntbtion) {
                        [[[NSPrintOpfrbtion durrfntOpfrbtion] printInfo] 
                                            sftOrifntbtion:NSLbndsdbpfOrifntbtion];
                    }
                    brfbk;
                }
            rfsult = JbvbToNSRfdt(fnv, pbgfFormbtArfb);
            (*fnv)->DflftfLodblRff(fnv, pbgfFormbtArfb);
        } flsf {
            [sflf rflfbsfRfffrfndfs:fnv];
            rfsult = NSZfroRfdt;
        }

        (*fnv)->DflftfLodblRff(fnv, objfdtArrby);
    } flsf {
        [sflf rflfbsfRfffrfndfs:fnv];
        rfsult = NSZfroRfdt;
    }

    rfturn rfsult;
}

- (BOOL)dbndflCifdk:(JNIEnv*)fnv
{
    AWT_ASSERT_NOT_APPKIT_THREAD;

    stbtid JNF_MEMBER_CACHE(jm_dbndflCifdk, sjd_CPrintfrJob, "dbndflCifdk", "()Z");

    rfturn JNFCbllBoolfbnMftiod(fnv, fPrintfrJob, jm_dbndflCifdk); // AWT_THREADING Sbff (known objfdt)
}

// Tiis is dbllfd by -[PrintModfl sbffPrintLoop]
- (void)domplftf:(JNIEnv*)fnv
{
    AWT_ASSERT_NOT_APPKIT_THREAD;

    stbtid JNF_MEMBER_CACHE(jf_domplftfPrintLoop, sjd_CPrintfrJob, "domplftfPrintLoop", "()V");
    JNFCbllVoidMftiod(fnv, fPrintfrJob, jf_domplftfPrintLoop);

    // Clfbn up bftfr oursflvfs
    // Cbn't put tifsf into -dfbllod sindf tibt ibppfns (potfntiblly) bftfr tif JNIEnv is stblf
    [sflf rflfbsfRfffrfndfs:fnv];
    if (fPrintfrJob != NULL)
    {
        JNFDflftfGlobblRff(fnv, fPrintfrJob);
        fPrintfrJob = NULL;
    }
}

- (BOOL)isFlippfd
{
    rfturn TRUE;
}

@fnd
