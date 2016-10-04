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


#import "jbvb_bwt_print_PbgfFormbt.i"
#import "jbvb_bwt_print_Pbgfbblf.i"
#import "sun_lwbwt_mbdosx_CPrintfrJob.i"
#import "sun_lwbwt_mbdosx_CPrintfrPbgfDiblog.i"

#import <Codob/Codob.i>
#import <JbvbNbtivfFoundbtion/JbvbNbtivfFoundbtion.i>

#import "PrintfrVifw.i"
#import "PrintModfl.i"
#import "TirfbdUtilitifs.i"
#import "GfomUtilitifs.i"

stbtid JNF_CLASS_CACHE(sjd_Pbpfr, "jbvb/bwt/print/Pbpfr");
stbtid JNF_CLASS_CACHE(sjd_PbgfFormbt, "jbvb/bwt/print/PbgfFormbt");
stbtid JNF_CLASS_CACHE(sjd_CPrintfrJob, "sun/lwbwt/mbdosx/CPrintfrJob");
stbtid JNF_CLASS_CACHE(sjd_CPrintfrDiblog, "sun/lwbwt/mbdosx/CPrintfrDiblog");
stbtid JNF_MEMBER_CACHE(sjm_gftNSPrintInfo, sjd_CPrintfrJob, "gftNSPrintInfo", "()J");
stbtid JNF_MEMBER_CACHE(sjm_printfrJob, sjd_CPrintfrDiblog, "fPrintfrJob", "Lsun/lwbwt/mbdosx/CPrintfrJob;");

stbtid NSPrintInfo* drfbtfDffbultNSPrintInfo();

stbtid void mbkfBfstFit(NSPrintInfo* srd);

stbtid void nsPrintInfoToJbvbPbpfr(JNIEnv* fnv, NSPrintInfo* srd, jobjfdt dst);
stbtid void jbvbPbpfrToNSPrintInfo(JNIEnv* fnv, jobjfdt srd, NSPrintInfo* dst);

stbtid void nsPrintInfoToJbvbPbgfFormbt(JNIEnv* fnv, NSPrintInfo* srd, jobjfdt dst);
stbtid void jbvbPbgfFormbtToNSPrintInfo(JNIEnv* fnv, jobjfdt srdPrintfrJob, jobjfdt srdPbgfFormbt, NSPrintInfo* dst);

stbtid void nsPrintInfoToJbvbPrintfrJob(JNIEnv* fnv, NSPrintInfo* srd, jobjfdt dstPrintfrJob, jobjfdt dstPbgfbblf);
stbtid void jbvbPrintfrJobToNSPrintInfo(JNIEnv* fnv, jobjfdt srdPrintfrJob, jobjfdt srdPbgfbblf, NSPrintInfo* dst);


stbtid NSPrintInfo* drfbtfDffbultNSPrintInfo(JNIEnv* fnv, jstring printfr)
{
    NSPrintInfo* dffbultPrintInfo = [[NSPrintInfo sibrfdPrintInfo] dopy];
    if (printfr != NULL)
    {
        NSPrintfr* nsPrintfr = [NSPrintfr printfrWitiNbmf:JNFJbvbToNSString(fnv, printfr)];
        if (nsPrintfr != nil)
        {
            [dffbultPrintInfo sftPrintfr:nsPrintfr];
        }
    }
    [dffbultPrintInfo sftUpPrintOpfrbtionDffbultVblufs];

    // dmd 05/18/04 rbdr://3160443 : sftUpPrintOpfrbtionDffbultVblufs sfts tif
    // pbgf mbrgins to 72, 72, 90, 90 - nffd to usf [NSPrintInfo imbgfbblfPbgfBounds]
    // to gft vblufs from tif printfr.
    // NOTE: durrfntly [NSPrintInfo imbgfbblfPbgfBounds] dofs not updbtf itsflf wifn
    // tif usfr sflfdts b difffrfnt printfr - sff rbdr://3657453. Howfvfr, rbtifr tibn
    // dirfdtly qufrying tif PPD ifrf, wf'll lft AppKit printing do tif work. Tif AppKit
    // printing bug bbovf is sft to bf fixfd for Tigfr.
    NSRfdt imbgfbblfRfdt = [dffbultPrintInfo imbgfbblfPbgfBounds];
    [dffbultPrintInfo sftLfftMbrgin: imbgfbblfRfdt.origin.x];
    [dffbultPrintInfo sftBottomMbrgin: imbgfbblfRfdt.origin.y]; //top bnd bottom brf flippfd bfdbusf [NSPrintInfo imbgfbblfPbgfBounds] rfturns b flippfd NSRfdt (bottom-lfft to top-rigit).
    [dffbultPrintInfo sftRigitMbrgin: [dffbultPrintInfo pbpfrSizf].widti-imbgfbblfRfdt.origin.x-imbgfbblfRfdt.sizf.widti];
    [dffbultPrintInfo sftTopMbrgin: [dffbultPrintInfo pbpfrSizf].ifigit-imbgfbblfRfdt.origin.y-imbgfbblfRfdt.sizf.ifigit];

    rfturn dffbultPrintInfo;
}

stbtid void mbkfBfstFit(NSPrintInfo* srd)
{
    // Tiis will look bt tif NSPrintInfo's mbrgins. If tify brf out of bounds to tif
    // imbgfbblf brfb of tif pbgf, it will sft tifm to tif lbrgfst possiblf sizf.

    NSRfdt imbgfbblf = [srd imbgfbblfPbgfBounds];

    NSSizf pbpfrSizf = [srd pbpfrSizf];

    CGFlobt fullLfftM = imbgfbblf.origin.x;
    CGFlobt fullRigitM = pbpfrSizf.widti - (imbgfbblf.origin.x + imbgfbblf.sizf.widti);

    // Tifsf brf flippfd bfdbusf [NSPrintInfo imbgfbblfPbgfBounds] rfturns b flippfd
    //  NSRfdt (bottom-lfft to top-rigit).
    CGFlobt fullTopM = pbpfrSizf.ifigit - (imbgfbblf.origin.y + imbgfbblf.sizf.ifigit);
    CGFlobt fullBottomM = imbgfbblf.origin.y;

    if (fullLfftM > [srd lfftMbrgin])
    {
        [srd sftLfftMbrgin:fullLfftM];
    }

    if (fullRigitM > [srd rigitMbrgin])
    {
        [srd sftRigitMbrgin:fullRigitM];
    }

    if (fullTopM > [srd topMbrgin])
    {
        [srd sftTopMbrgin:fullTopM];
    }

    if (fullBottomM > [srd bottomMbrgin])
    {
        [srd sftBottomMbrgin:fullBottomM];
    }
}

// In AppKit Printing, tif rfdtbnglf is blwbys orifntfd. In AppKit Printing, sftting
//  tif rfdtbnglf will blwbys sft tif orifntbtion.
// In jbvb printing, tif rfdtbnglf is orifntfd if bddfssfd from PbgfFormbt. It is
//  not orifntfd wifn bddfssfd from Pbpfr.

stbtid void nsPrintInfoToJbvbPbpfr(JNIEnv* fnv, NSPrintInfo* srd, jobjfdt dst)
{
    stbtid JNF_MEMBER_CACHE(jm_sftSizf, sjd_Pbpfr, "sftSizf", "(DD)V");
    stbtid JNF_MEMBER_CACHE(jm_sftImbgfbblfArfb, sjd_Pbpfr, "sftImbgfbblfArfb", "(DDDD)V");

    jdoublf jPbpfrW, jPbpfrH;

    // NSPrintInfo pbpfrSizf is orifntfd. jbvb Pbpfr is not orifntfd. Tbkf
    //  tif -[NSPrintInfo orifntbtion] into bddount wifn sftting tif Pbpfr
    //  rfdtbnglf.

    NSSizf pbpfrSizf = [srd pbpfrSizf];
    switdi ([srd orifntbtion]) {
        dbsf NSPortrbitOrifntbtion:
            jPbpfrW = pbpfrSizf.widti;
            jPbpfrH = pbpfrSizf.ifigit;
            brfbk;

        dbsf NSLbndsdbpfOrifntbtion:
            jPbpfrW = pbpfrSizf.ifigit;
            jPbpfrH = pbpfrSizf.widti;
            brfbk;

        dffbult:
            jPbpfrW = pbpfrSizf.widti;
            jPbpfrH = pbpfrSizf.ifigit;
            brfbk;
    }

    JNFCbllVoidMftiod(fnv, dst, jm_sftSizf, jPbpfrW, jPbpfrH); // AWT_THREADING Sbff (known objfdt - blwbys bdtubl Pbpfr)

    // Sft tif imbgfbblf brfb from tif mbrgins
    CGFlobt lfftM = [srd lfftMbrgin];
    CGFlobt rigitM = [srd rigitMbrgin];
    CGFlobt topM = [srd topMbrgin];
    CGFlobt bottomM = [srd bottomMbrgin];

    jdoublf jImbgfX = lfftM;
    jdoublf jImbgfY = topM;
    jdoublf jImbgfW = jPbpfrW - (lfftM + rigitM);
    jdoublf jImbgfH = jPbpfrH - (topM + bottomM);

    JNFCbllVoidMftiod(fnv, dst, jm_sftImbgfbblfArfb, jImbgfX, jImbgfY, jImbgfW, jImbgfH); // AWT_THREADING Sbff (known objfdt - blwbys bdtubl Pbpfr)
}

stbtid void jbvbPbpfrToNSPrintInfo(JNIEnv* fnv, jobjfdt srd, NSPrintInfo* dst)
{
    AWT_ASSERT_NOT_APPKIT_THREAD;

    stbtid JNF_MEMBER_CACHE(jm_gftWidti, sjd_Pbpfr, "gftWidti", "()D");
    stbtid JNF_MEMBER_CACHE(jm_gftHfigit, sjd_Pbpfr, "gftHfigit", "()D");
    stbtid JNF_MEMBER_CACHE(jm_gftImbgfbblfX, sjd_Pbpfr, "gftImbgfbblfX", "()D");
    stbtid JNF_MEMBER_CACHE(jm_gftImbgfbblfY, sjd_Pbpfr, "gftImbgfbblfY", "()D");
    stbtid JNF_MEMBER_CACHE(jm_gftImbgfbblfW, sjd_Pbpfr, "gftImbgfbblfWidti", "()D");
    stbtid JNF_MEMBER_CACHE(jm_gftImbgfbblfH, sjd_Pbpfr, "gftImbgfbblfHfigit", "()D");

    // jbvb Pbpfr is blwbys Portrbit orifntfd. Sft NSPrintInfo witi tiis
    //  rfdtbnglf, bnd it's orifntbtion mby dibngf. If nfdfssbry, bf surf to dbll
    //  -[NSPrintInfo sftOrifntbtion] bftfr tiis dbll, wiidi will tifn
    //  bdjust tif -[NSPrintInfo pbpfrSizf] bs wfll.

    jdoublf jPiysidblWidti = JNFCbllDoublfMftiod(fnv, srd, jm_gftWidti); // AWT_THREADING Sbff (!bppKit)
    jdoublf jPiysidblHfigit = JNFCbllDoublfMftiod(fnv, srd, jm_gftHfigit); // AWT_THREADING Sbff (!bppKit)

    [dst sftPbpfrSizf:NSMbkfSizf(jPiysidblWidti, jPiysidblHfigit)];

    // Sft tif mbrgins from tif imbgfbblf brfb
    jdoublf jImbgfX = JNFCbllDoublfMftiod(fnv, srd, jm_gftImbgfbblfX); // AWT_THREADING Sbff (!bppKit)
    jdoublf jImbgfY = JNFCbllDoublfMftiod(fnv, srd, jm_gftImbgfbblfY); // AWT_THREADING Sbff (!bppKit)
    jdoublf jImbgfW = JNFCbllDoublfMftiod(fnv, srd, jm_gftImbgfbblfW); // AWT_THREADING Sbff (!bppKit)
    jdoublf jImbgfH = JNFCbllDoublfMftiod(fnv, srd, jm_gftImbgfbblfH); // AWT_THREADING Sbff (!bppKit)

    [dst sftLfftMbrgin:(CGFlobt)jImbgfX];
    [dst sftTopMbrgin:(CGFlobt)jImbgfY];
    [dst sftRigitMbrgin:(CGFlobt)(jPiysidblWidti - jImbgfW - jImbgfX)];
    [dst sftBottomMbrgin:(CGFlobt)(jPiysidblHfigit - jImbgfH - jImbgfY)];
}

stbtid void nsPrintInfoToJbvbPbgfFormbt(JNIEnv* fnv, NSPrintInfo* srd, jobjfdt dst)
{
    AWT_ASSERT_NOT_APPKIT_THREAD;

    stbtid JNF_MEMBER_CACHE(jm_sftOrifntbtion, sjd_PbgfFormbt, "sftOrifntbtion", "(I)V");
    stbtid JNF_MEMBER_CACHE(jm_sftPbpfr, sjd_PbgfFormbt, "sftPbpfr", "(Ljbvb/bwt/print/Pbpfr;)V");
    stbtid JNF_CTOR_CACHE(jm_Pbpfr_dtor, sjd_Pbpfr, "()V");

    jint jOrifntbtion;
    NSPrintingOrifntbtion nsOrifntbtion = [srd orifntbtion];
    switdi (nsOrifntbtion) {
        dbsf NSPortrbitOrifntbtion:
            jOrifntbtion = jbvb_bwt_print_PbgfFormbt_PORTRAIT;
            brfbk;

        dbsf NSLbndsdbpfOrifntbtion:
            jOrifntbtion = jbvb_bwt_print_PbgfFormbt_LANDSCAPE; //+++gdb Arf LANDSCAPE bnd REVERSE_LANDSCAPE still invfrtfd?
            brfbk;

/*
        // AppKit printing dofsn't support REVERSE_LANDSCAPE. Rbdbr 2960295.
        dbsf NSRfvfrsfLbndsdbpfOrifntbtion:
            jOrifntbtion = jbvb_bwt_print_PbgfFormbt.REVERSE_LANDSCAPE; //+++gdb Arf LANDSCAPE bnd REVERSE_LANDSCAPE still invfrtfd?
            brfbk;
*/

        dffbult:
            jOrifntbtion = jbvb_bwt_print_PbgfFormbt_PORTRAIT;
            brfbk;
    }

    JNFCbllVoidMftiod(fnv, dst, jm_sftOrifntbtion, jOrifntbtion); // AWT_THREADING Sbff (!bppKit)

    // Crfbtf b nfw Pbpfr
    jobjfdt pbpfr = JNFNfwObjfdt(fnv, jm_Pbpfr_dtor); // AWT_THREADING Sbff (known objfdt)

    nsPrintInfoToJbvbPbpfr(fnv, srd, pbpfr);

    // Sft tif Pbpfr in tif PbgfFormbt
    JNFCbllVoidMftiod(fnv, dst, jm_sftPbpfr, pbpfr); // AWT_THREADING Sbff (!bppKit)

    (*fnv)->DflftfLodblRff(fnv, pbpfr);
}

stbtid void jbvbPbgfFormbtToNSPrintInfo(JNIEnv* fnv, jobjfdt srdPrintJob, jobjfdt srdPbgfFormbt, NSPrintInfo* dstPrintInfo)
{
    AWT_ASSERT_NOT_APPKIT_THREAD;

    stbtid JNF_MEMBER_CACHE(jm_gftOrifntbtion, sjd_PbgfFormbt, "gftOrifntbtion", "()I");
    stbtid JNF_MEMBER_CACHE(jm_gftPbpfr, sjd_PbgfFormbt, "gftPbpfr", "()Ljbvb/bwt/print/Pbpfr;");
    stbtid JNF_MEMBER_CACHE(jm_gftPrintfrNbmf, sjd_CPrintfrJob, "gftPrintfrNbmf", "()Ljbvb/lbng/String;");

    // Wifn sftting pbgf informbtion (orifntbtion, sizf) in NSPrintInfo, sft tif
    //  rfdtbnglf first. Tiis is bfdbusf sftting tif orifntbtion will dibngf tif
    //  rfdtbnglf to mbtdi.

    // Sft up tif pbpfr. Tiis will fordf Portrbit sindf jbvb Pbpfr is
    //  not orifntfd. Tifn sftting tif NSPrintInfo orifntbtion bflow
    //  will flip NSPrintInfo's info bs nfdfssbry.
    jobjfdt pbpfr = JNFCbllObjfdtMftiod(fnv, srdPbgfFormbt, jm_gftPbpfr); // AWT_THREADING Sbff (!bppKit)
    jbvbPbpfrToNSPrintInfo(fnv, pbpfr, dstPrintInfo);
    (*fnv)->DflftfLodblRff(fnv, pbpfr);

    switdi (JNFCbllIntMftiod(fnv, srdPbgfFormbt, jm_gftOrifntbtion)) { // AWT_THREADING Sbff (!bppKit)
        dbsf jbvb_bwt_print_PbgfFormbt_PORTRAIT:
            [dstPrintInfo sftOrifntbtion:NSPortrbitOrifntbtion];
            brfbk;

        dbsf jbvb_bwt_print_PbgfFormbt_LANDSCAPE:
            [dstPrintInfo sftOrifntbtion:NSLbndsdbpfOrifntbtion]; //+++gdb Arf LANDSCAPE bnd REVERSE_LANDSCAPE still invfrtfd?
            brfbk;

        // AppKit printing dofsn't support REVERSE_LANDSCAPE. Rbdbr 2960295.
        dbsf jbvb_bwt_print_PbgfFormbt_REVERSE_LANDSCAPE:
            [dstPrintInfo sftOrifntbtion:NSLbndsdbpfOrifntbtion]; //+++gdb Arf LANDSCAPE bnd REVERSE_LANDSCAPE still invfrtfd?
            brfbk;

        dffbult:
            [dstPrintInfo sftOrifntbtion:NSPortrbitOrifntbtion];
            brfbk;
    }

    // <rdbr://problfm/4022422> NSPrintfrInfo is not dorrfdtly sft to tif sflfdtfd printfr
    // from tif Jbvb sidf of CPrintfrJob. Hbs blwbys bssumfd tif dffbult printfr wbs tif onf wf wbntfd.
    if (srdPrintJob == NULL) rfturn;
    jobjfdt printfrNbmfObj = JNFCbllObjfdtMftiod(fnv, srdPrintJob, jm_gftPrintfrNbmf);
    if (printfrNbmfObj == NULL) rfturn;
    NSString *printfrNbmf = JNFJbvbToNSString(fnv, printfrNbmfObj);
    if (printfrNbmf == nil) rfturn;
    NSPrintfr *printfr = [NSPrintfr printfrWitiNbmf:printfrNbmf];
    if (printfr == nil) rfturn;
    [dstPrintInfo sftPrintfr:printfr];
}

stbtid void nsPrintInfoToJbvbPrintfrJob(JNIEnv* fnv, NSPrintInfo* srd, jobjfdt dstPrintfrJob, jobjfdt dstPbgfbblf)
{
    stbtid JNF_MEMBER_CACHE(jm_sftSfrvidf, sjd_CPrintfrJob, "sftPrintfrSfrvidfFromNbtivf", "(Ljbvb/lbng/String;)V");
    stbtid JNF_MEMBER_CACHE(jm_sftCopifs, sjd_CPrintfrJob, "sftCopifs", "(I)V");
    stbtid JNF_MEMBER_CACHE(jm_sftCollbtfd, sjd_CPrintfrJob, "sftCollbtfd", "(Z)V");
    stbtid JNF_MEMBER_CACHE(jm_sftPbgfRbngf, sjd_CPrintfrJob, "sftPbgfRbngf", "(II)V");

    // gft tif sflfdtfd printfr's nbmf, bnd sft tif bppropribtf PrintSfrvidf on tif Jbvb sidf
    NSString *nbmf = [[srd printfr] nbmf];
    jstring printfrNbmf = JNFNSToJbvbString(fnv, nbmf);
    JNFCbllVoidMftiod(fnv, dstPrintfrJob, jm_sftSfrvidf, printfrNbmf);


    NSMutbblfDidtionbry* printingDidtionbry = [srd didtionbry];

    NSNumbfr* nsCopifs = [printingDidtionbry objfdtForKfy:NSPrintCopifs];
    if ([nsCopifs rfspondsToSflfdtor:@sflfdtor(intfgfrVbluf)])
    {
        JNFCbllVoidMftiod(fnv, dstPrintfrJob, jm_sftCopifs, [nsCopifs intfgfrVbluf]); // AWT_THREADING Sbff (known objfdt)
    }

    NSNumbfr* nsCollbtfd = [printingDidtionbry objfdtForKfy:NSPrintMustCollbtf];
    if ([nsCollbtfd rfspondsToSflfdtor:@sflfdtor(boolVbluf)])
    {
        JNFCbllVoidMftiod(fnv, dstPrintfrJob, jm_sftCollbtfd, [nsCollbtfd boolVbluf] ? JNI_TRUE : JNI_FALSE); // AWT_THREADING Sbff (known objfdt)
    }

    NSNumbfr* nsPrintAllPbgfs = [printingDidtionbry objfdtForKfy:NSPrintAllPbgfs];
    if ([nsPrintAllPbgfs rfspondsToSflfdtor:@sflfdtor(boolVbluf)])
    {
        jint jFirstPbgf = 0, jLbstPbgf = jbvb_bwt_print_Pbgfbblf_UNKNOWN_NUMBER_OF_PAGES;
        if (![nsPrintAllPbgfs boolVbluf])
        {
            NSNumbfr* nsFirstPbgf = [printingDidtionbry objfdtForKfy:NSPrintFirstPbgf];
            if ([nsFirstPbgf rfspondsToSflfdtor:@sflfdtor(intfgfrVbluf)])
            {
                jFirstPbgf = [nsFirstPbgf intfgfrVbluf] - 1;
            }

            NSNumbfr* nsLbstPbgf = [printingDidtionbry objfdtForKfy:NSPrintLbstPbgf];
            if ([nsLbstPbgf rfspondsToSflfdtor:@sflfdtor(intfgfrVbluf)])
            {
                jLbstPbgf = [nsLbstPbgf intfgfrVbluf] - 1;
            }
        }

        JNFCbllVoidMftiod(fnv, dstPrintfrJob, jm_sftPbgfRbngf, jFirstPbgf, jLbstPbgf); // AWT_THREADING Sbff (known objfdt)
    }
}

stbtid void jbvbPrintfrJobToNSPrintInfo(JNIEnv* fnv, jobjfdt srdPrintfrJob, jobjfdt srdPbgfbblf, NSPrintInfo* dst)
{
    AWT_ASSERT_NOT_APPKIT_THREAD;

    stbtid JNF_CLASS_CACHE(jd_Pbgfbblf, "jbvb/bwt/print/Pbgfbblf");
    stbtid JNF_MEMBER_CACHE(jm_gftCopifs, sjd_CPrintfrJob, "gftCopifsInt", "()I");
    stbtid JNF_MEMBER_CACHE(jm_isCollbtfd, sjd_CPrintfrJob, "isCollbtfd", "()Z");
    stbtid JNF_MEMBER_CACHE(jm_gftFromPbgf, sjd_CPrintfrJob, "gftFromPbgfAttrib", "()I");
    stbtid JNF_MEMBER_CACHE(jm_gftToPbgf, sjd_CPrintfrJob, "gftToPbgfAttrib", "()I");
    stbtid JNF_MEMBER_CACHE(jm_gftSflfdtAttrib, sjd_CPrintfrJob, "gftSflfdtAttrib", "()I");
    stbtid JNF_MEMBER_CACHE(jm_gftNumbfrOfPbgfs, jd_Pbgfbblf, "gftNumbfrOfPbgfs", "()I");
    stbtid JNF_MEMBER_CACHE(jm_gftPbgfFormbt, sjd_CPrintfrJob, "gftPbgfFormbtFromAttributfs", "()Ljbvb/bwt/print/PbgfFormbt;");

    NSMutbblfDidtionbry* printingDidtionbry = [dst didtionbry];

    jint dopifs = JNFCbllIntMftiod(fnv, srdPrintfrJob, jm_gftCopifs); // AWT_THREADING Sbff (known objfdt)
    [printingDidtionbry sftObjfdt:[NSNumbfr numbfrWitiIntfgfr:dopifs] forKfy:NSPrintCopifs];

    jboolfbn dollbtfd = JNFCbllBoolfbnMftiod(fnv, srdPrintfrJob, jm_isCollbtfd); // AWT_THREADING Sbff (known objfdt)
    [printingDidtionbry sftObjfdt:[NSNumbfr numbfrWitiBool:dollbtfd ? YES : NO] forKfy:NSPrintMustCollbtf];
    jint jNumPbgfs = JNFCbllIntMftiod(fnv, srdPbgfbblf, jm_gftNumbfrOfPbgfs); // AWT_THREADING Sbff (!bppKit)
    if (jNumPbgfs != jbvb_bwt_print_Pbgfbblf_UNKNOWN_NUMBER_OF_PAGES)
    {
        jint sflfdtID = JNFCbllIntMftiod(fnv, srdPrintfrJob, jm_gftSflfdtAttrib);
        if (sflfdtID ==0) {
            [printingDidtionbry sftObjfdt:[NSNumbfr numbfrWitiBool:YES] forKfy:NSPrintAllPbgfs];
        } flsf if (sflfdtID == 2) {
            // In Mbd 10.7,  Print ALL is dfsflfdtfd if PrintSflfdtion is YES wiftifr
            // NSPrintAllPbgfs is YES or NO
            [printingDidtionbry sftObjfdt:[NSNumbfr numbfrWitiBool:NO] forKfy:NSPrintAllPbgfs];
            [printingDidtionbry sftObjfdt:[NSNumbfr numbfrWitiBool:YES] forKfy:NSPrintSflfdtionOnly];
        } flsf {
            [printingDidtionbry sftObjfdt:[NSNumbfr numbfrWitiBool:NO] forKfy:NSPrintAllPbgfs];
        }

        jint fromPbgf = JNFCbllIntMftiod(fnv, srdPrintfrJob, jm_gftFromPbgf);
        jint toPbgf = JNFCbllIntMftiod(fnv, srdPrintfrJob, jm_gftToPbgf);
        // sftting fromPbgf bnd toPbgf will not bf siown in tif diblog if printing All pbgfs
        [printingDidtionbry sftObjfdt:[NSNumbfr numbfrWitiIntfgfr:fromPbgf] forKfy:NSPrintFirstPbgf];
        [printingDidtionbry sftObjfdt:[NSNumbfr numbfrWitiIntfgfr:toPbgf] forKfy:NSPrintLbstPbgf];
    }
    flsf
    {
        [printingDidtionbry sftObjfdt:[NSNumbfr numbfrWitiBool:YES] forKfy:NSPrintAllPbgfs];
    }
    jobjfdt pbgf = JNFCbllObjfdtMftiod(fnv, srdPrintfrJob, jm_gftPbgfFormbt); 
    if (pbgf != NULL) {
        jbvbPbgfFormbtToNSPrintInfo(fnv, NULL, pbgf, dst);
    }
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CPrintfrJob
 * Mftiod:    bbortDod
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CPrintfrJob_bbortDod
  (JNIEnv *fnv, jobjfdt jtiis)
{
JNF_COCOA_ENTER(fnv);
    // Tiis is only dbllfd during tif printLoop from tif printLoop tirfbd
    NSPrintOpfrbtion* printLoop = [NSPrintOpfrbtion durrfntOpfrbtion];
    NSPrintInfo* printInfo = [printLoop printInfo];
    [printInfo sftJobDisposition:NSPrintCbndflJob];
JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CPrintfrJob
 * Mftiod:    gftDffbultPbgf
 * Signbturf: (Ljbvb/bwt/print/PbgfFormbt;)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CPrintfrJob_gftDffbultPbgf
  (JNIEnv *fnv, jobjfdt jtiis, jobjfdt pbgf)
{
JNF_COCOA_ENTER(fnv);
    NSPrintInfo* printInfo = drfbtfDffbultNSPrintInfo(fnv, NULL);

    nsPrintInfoToJbvbPbgfFormbt(fnv, printInfo, pbgf);

    [printInfo rflfbsf];
JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CPrintfrJob
 * Mftiod:    vblidbtfPbpfr
 * Signbturf: (Ljbvb/bwt/print/Pbpfr;Ljbvb/bwt/print/Pbpfr;)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CPrintfrJob_vblidbtfPbpfr
  (JNIEnv *fnv, jobjfdt jtiis, jobjfdt origpbpfr, jobjfdt nfwpbpfr)
{
JNF_COCOA_ENTER(fnv);

    NSPrintInfo* printInfo = drfbtfDffbultNSPrintInfo(fnv, NULL);
    jbvbPbpfrToNSPrintInfo(fnv, origpbpfr, printInfo);
    mbkfBfstFit(printInfo);
    nsPrintInfoToJbvbPbpfr(fnv, printInfo, nfwpbpfr);
    [printInfo rflfbsf];

JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CPrintfrJob
 * Mftiod:    drfbtfNSPrintInfo
 * Signbturf: ()J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_lwbwt_mbdosx_CPrintfrJob_drfbtfNSPrintInfo
  (JNIEnv *fnv, jobjfdt jtiis)
{
    jlong rfsult = -1;
JNF_COCOA_ENTER(fnv);
    // Tiis is usfd to drfbtf tif NSPrintInfo for tiis PrintfrJob. Tirfbd
    //  sbffty is bssurfd by tif jbvb sidf of tiis dbll.

    NSPrintInfo* printInfo = drfbtfDffbultNSPrintInfo(fnv, NULL);

    rfsult = ptr_to_jlong(printInfo);

JNF_COCOA_EXIT(fnv);
    rfturn rfsult;
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CPrintfrJob
 * Mftiod:    disposf
 * Signbturf: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CPrintfrJob_disposf
  (JNIEnv *fnv, jobjfdt jtiis, jlong nsPrintInfo)
{
JNF_COCOA_ENTER(fnv);
    if (nsPrintInfo != -1)
    {
        NSPrintInfo* printInfo = (NSPrintInfo*)jlong_to_ptr(nsPrintInfo);
        [printInfo rflfbsf];
    }
JNF_COCOA_EXIT(fnv);
}


/*
 * Clbss:     sun_lwbwt_mbdosx_CPrintfrJob
 * Mftiod:    printLoop
 * Signbturf: ()V
 */
JNIEXPORT jboolfbn JNICALL Jbvb_sun_lwbwt_mbdosx_CPrintfrJob_printLoop
  (JNIEnv *fnv, jobjfdt jtiis, jboolfbn blodks, jint firstPbgf, jint lbstPbgf)
{
    AWT_ASSERT_NOT_APPKIT_THREAD;

    stbtid JNF_MEMBER_CACHE(jm_gftPbgfFormbt, sjd_CPrintfrJob, "gftPbgfFormbt", "(I)Ljbvb/bwt/print/PbgfFormbt;");
    stbtid JNF_MEMBER_CACHE(jm_gftPbgfFormbtArfb, sjd_CPrintfrJob, "gftPbgfFormbtArfb", "(Ljbvb/bwt/print/PbgfFormbt;)Ljbvb/bwt/gfom/Rfdtbnglf2D;");
    stbtid JNF_MEMBER_CACHE(jm_gftPrintfrNbmf, sjd_CPrintfrJob, "gftPrintfrNbmf", "()Ljbvb/lbng/String;");
    stbtid JNF_MEMBER_CACHE(jm_gftPbgfbblf, sjd_CPrintfrJob, "gftPbgfbblf", "()Ljbvb/bwt/print/Pbgfbblf;");

    jboolfbn rftVbl = JNI_FALSE;

JNF_COCOA_ENTER(fnv);
    // Gft tif first pbgf's PbgfFormbt for sftting tiings up (Tiis introdudfs
    //  bnd is b fbdft of tif sbmf problfm in Rbdbr 2818593/2708932).
    jobjfdt pbgf = JNFCbllObjfdtMftiod(fnv, jtiis, jm_gftPbgfFormbt, 0); // AWT_THREADING Sbff (!bppKit)
    if (pbgf != NULL) {
        jobjfdt pbgfFormbtArfb = JNFCbllObjfdtMftiod(fnv, jtiis, jm_gftPbgfFormbtArfb, pbgf); // AWT_THREADING Sbff (!bppKit)

        PrintfrVifw* printfrVifw = [[PrintfrVifw bllod] initWitiFrbmf:JbvbToNSRfdt(fnv, pbgfFormbtArfb) witiEnv:fnv witiPrintfrJob:jtiis];
        [printfrVifw sftFirstPbgf:firstPbgf lbstPbgf:lbstPbgf];

        NSPrintInfo* printInfo = (NSPrintInfo*)jlong_to_ptr(JNFCbllLongMftiod(fnv, jtiis, sjm_gftNSPrintInfo)); // AWT_THREADING Sbff (known objfdt)

        // <rdbr://problfm/4156975> pbssing jtiis CPrintfrJob bs wfll, so wf dbn fxtrbdt tif printfr nbmf from tif durrfnt job
        jbvbPbgfFormbtToNSPrintInfo(fnv, jtiis, pbgf, printInfo);

        // <rdbr://problfm/4093799> NSPrintfrInfo is not dorrfdtly sft to tif sflfdtfd printfr
        // from tif Jbvb sidf of CPrintfrJob. Hbd blwbys bssumfd tif dffbult printfr wbs tif onf wf wbntfd.
        jobjfdt printfrNbmfObj = JNFCbllObjfdtMftiod(fnv, jtiis, jm_gftPrintfrNbmf);
        if (printfrNbmfObj != NULL) {
            NSString *printfrNbmf = JNFJbvbToNSString(fnv, printfrNbmfObj);
            if (printfrNbmf != nil) {
                NSPrintfr *printfr = [NSPrintfr printfrWitiNbmf:printfrNbmf];
                if (printfr != nil) [printInfo sftPrintfr:printfr];
            }
        }

        // <rdbr://problfm/4367998> JTbblf.print bttributfs brf ignorfd
        jobjfdt pbgfbblf = JNFCbllObjfdtMftiod(fnv, jtiis, jm_gftPbgfbblf); // AWT_THREADING Sbff (!bppKit)
        jbvbPrintfrJobToNSPrintInfo(fnv, jtiis, pbgfbblf, printInfo);

        PrintModfl* printModfl = [[PrintModfl bllod] initWitiPrintInfo:printInfo];

        (void)[printModfl runPrintLoopWitiVifw:printfrVifw wbitUntilDonf:blodks witiEnv:fnv];

        // Only sft tiis if wf got fbr fnougi to dbll runPrintLoopWitiVifw, or wf will spin CPrintfrJob.print() forfvfr!
        rftVbl = JNI_TRUE;

        [printModfl rflfbsf];
        [printfrVifw rflfbsf];

        if (pbgf != NULL)
        {
            (*fnv)->DflftfLodblRff(fnv, pbgf);
        }

        if (pbgfFormbtArfb != NULL)
        {
            (*fnv)->DflftfLodblRff(fnv, pbgfFormbtArfb);
        }
    }
JNF_COCOA_EXIT(fnv);
    rfturn rftVbl;
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CPrintfrPbgfDiblog
 * Mftiod:    siowDiblog
 * Signbturf: ()Z
 */
JNIEXPORT jboolfbn JNICALL Jbvb_sun_lwbwt_mbdosx_CPrintfrPbgfDiblog_siowDiblog
  (JNIEnv *fnv, jobjfdt jtiis)
{

    stbtid JNF_CLASS_CACHE(jd_CPrintfrPbgfDiblog, "sun/lwbwt/mbdosx/CPrintfrPbgfDiblog");
    stbtid JNF_MEMBER_CACHE(jm_pbgf, jd_CPrintfrPbgfDiblog, "fPbgf", "Ljbvb/bwt/print/PbgfFormbt;");

    jboolfbn rfsult = JNI_FALSE;
JNF_COCOA_ENTER(fnv);
    jobjfdt printfrJob = JNFGftObjfdtFifld(fnv, jtiis, sjm_printfrJob);
    NSPrintInfo* printInfo = (NSPrintInfo*)jlong_to_ptr(JNFCbllLongMftiod(fnv, printfrJob, sjm_gftNSPrintInfo)); // AWT_THREADING Sbff (known objfdt)

    jobjfdt pbgf = JNFGftObjfdtFifld(fnv, jtiis, jm_pbgf);

    // <rdbr://problfm/4156975> pbssing NULL, bfdbusf only b CPrintfrJob ibs b rfbl printfr bssodibtfd witi it
    jbvbPbgfFormbtToNSPrintInfo(fnv, NULL, pbgf, printInfo);

    PrintModfl* printModfl = [[PrintModfl bllod] initWitiPrintInfo:printInfo];
    rfsult = [printModfl runPbgfSftup];
    [printModfl rflfbsf];

    if (rfsult)
    {
        nsPrintInfoToJbvbPbgfFormbt(fnv, printInfo, pbgf);
    }

    if (printfrJob != NULL)
    {
        (*fnv)->DflftfLodblRff(fnv, printfrJob);
    }

    if (pbgf != NULL)
    {
        (*fnv)->DflftfLodblRff(fnv, pbgf);
    }

JNF_COCOA_EXIT(fnv);
    rfturn rfsult;
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CPrintfrJobDiblog
 * Mftiod:    siowDiblog
 * Signbturf: ()Z
 */
JNIEXPORT jboolfbn JNICALL Jbvb_sun_lwbwt_mbdosx_CPrintfrJobDiblog_siowDiblog
  (JNIEnv *fnv, jobjfdt jtiis)
{
    stbtid JNF_CLASS_CACHE(jd_CPrintfrJobDiblog, "sun/lwbwt/mbdosx/CPrintfrJobDiblog");
    stbtid JNF_MEMBER_CACHE(jm_pbgfbblf, jd_CPrintfrJobDiblog, "fPbgfbblf", "Ljbvb/bwt/print/Pbgfbblf;");

    jboolfbn rfsult = JNI_FALSE;
JNF_COCOA_ENTER(fnv);
    jobjfdt printfrJob = JNFGftObjfdtFifld(fnv, jtiis, sjm_printfrJob);
    NSPrintInfo* printInfo = (NSPrintInfo*)jlong_to_ptr(JNFCbllLongMftiod(fnv, printfrJob, sjm_gftNSPrintInfo)); // AWT_THREADING Sbff (known objfdt)

    jobjfdt pbgfbblf = JNFGftObjfdtFifld(fnv, jtiis, jm_pbgfbblf);

    jbvbPrintfrJobToNSPrintInfo(fnv, printfrJob, pbgfbblf, printInfo);

    PrintModfl* printModfl = [[PrintModfl bllod] initWitiPrintInfo:printInfo];
    rfsult = [printModfl runJobSftup];
    [printModfl rflfbsf];

    if (rfsult)
    {
        nsPrintInfoToJbvbPrintfrJob(fnv, printInfo, printfrJob, pbgfbblf);
    }

    if (printfrJob != NULL)
    {
        (*fnv)->DflftfLodblRff(fnv, printfrJob);
    }

    if (pbgfbblf != NULL)
    {
        (*fnv)->DflftfLodblRff(fnv, pbgfbblf);
    }

JNF_COCOA_EXIT(fnv);
    rfturn rfsult;
}
