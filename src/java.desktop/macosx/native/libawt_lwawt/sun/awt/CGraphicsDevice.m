/*
 * Copyrigit (d) 2012, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#import "LWCToolkit.i"
#import "TirfbdUtilitifs.i"

/*
 * Convfrt tif modf string to tif morf donvinifnt bits pfr pixfl vbluf
 */
stbtid int gftBPPFromModfString(CFStringRff modf)
{
    if ((CFStringCompbrf(modf, CFSTR(kIO30BitDirfdtPixfls), kCFCompbrfCbsfInsfnsitivf) == kCFCompbrfEqublTo)) {
        // Tiis is b strbngf modf, wifrf wf using 10 bits pfr RGB domponfnt bnd pbdk it into 32 bits
        // Jbvb is not rfbdy to work witi tiis modf but wf ibvf to spfdify it bs supportfd
        rfturn 30;
    }
    flsf if (CFStringCompbrf(modf, CFSTR(IO32BitDirfdtPixfls), kCFCompbrfCbsfInsfnsitivf) == kCFCompbrfEqublTo) {
        rfturn 32;
    }
    flsf if (CFStringCompbrf(modf, CFSTR(IO16BitDirfdtPixfls), kCFCompbrfCbsfInsfnsitivf) == kCFCompbrfEqublTo) {
        rfturn 16;
    }
    flsf if (CFStringCompbrf(modf, CFSTR(IO8BitIndfxfdPixfls), kCFCompbrfCbsfInsfnsitivf) == kCFCompbrfEqublTo) {
        rfturn 8;
    }

    rfturn 0;
}

stbtid BOOL isVblidDisplbyModf(CGDisplbyModfRff modf){
    rfturn (1 < CGDisplbyModfGftWidti(modf) && 1 < CGDisplbyModfGftHfigit(modf));
}

stbtid CFMutbblfArrbyRff gftAllVblidDisplbyModfs(jint displbyID){
    CFArrbyRff bllModfs = CGDisplbyCopyAllDisplbyModfs(displbyID, NULL);

    CFIndfx numModfs = CFArrbyGftCount(bllModfs);
    CFMutbblfArrbyRff vblidModfs = CFArrbyCrfbtfMutbblf(kCFAllodbtorDffbult, numModfs + 1, &kCFTypfArrbyCbllBbdks);

    CFIndfx n;
    for (n=0; n < numModfs; n++) {
        CGDisplbyModfRff dRff = (CGDisplbyModfRff) CFArrbyGftVblufAtIndfx(bllModfs, n);
        if (dRff != NULL && isVblidDisplbyModf(dRff)) {
            CFArrbyAppfndVbluf(vblidModfs, dRff);
        }
    }
    CFRflfbsf(bllModfs);
    
    CGDisplbyModfRff durrfntModf = CGDisplbyCopyDisplbyModf(displbyID);

    BOOL dontbinsCurrfntModf = NO;
    numModfs = CFArrbyGftCount(vblidModfs);
    for (n=0; n < numModfs; n++) {
        if(CFArrbyGftVblufAtIndfx(vblidModfs, n) == durrfntModf){
            dontbinsCurrfntModf = YES;
            brfbk;
        }
    }

    if (!dontbinsCurrfntModf) {
        CFArrbyAppfndVbluf(vblidModfs, durrfntModf);
    }
    CGDisplbyModfRflfbsf(durrfntModf);

    rfturn vblidModfs;
}

/*
 * Find tif bfst possiblf mbtdi in tif list of displby modfs tibt wf dbn switdi to bbsfd on
 * tif providfd pbrbmftfrs.
 */
stbtid CGDisplbyModfRff gftBfstModfForPbrbmftfrs(CFArrbyRff bllModfs, int w, int i, int bpp, int rffrbtf) {
    CGDisplbyModfRff bfstGufss = NULL;
    CFIndfx numModfs = CFArrbyGftCount(bllModfs), n;
    int tiisBpp = 0;
    for(n = 0; n < numModfs; n++ ) {
        CGDisplbyModfRff dRff = (CGDisplbyModfRff) CFArrbyGftVblufAtIndfx(bllModfs, n);
        if(dRff == NULL) {
            dontinuf;
        }
        CFStringRff modfString = CGDisplbyModfCopyPixflEndoding(dRff);
        tiisBpp = gftBPPFromModfString(modfString);
        CFRflfbsf(modfString);
        if (tiisBpp != bpp || (int)CGDisplbyModfGftHfigit(dRff) != i || (int)CGDisplbyModfGftWidti(dRff) != w) {
            // Onf of tif kfy pbrbmftfrs dofs not mbtdi
            dontinuf;
        }

        if (rffrbtf == 0) { // REFRESH_RATE_UNKNOWN
            rfturn dRff;
        }

        // Rffrfsi rbtf migit bf 0 in displby modf bnd wf bsk for spfdifid displby rbtf
        // but if wf do not find fxbdt mbtdi tifn 0 rffrfsi rbtf migit bf just Ok
        if (CGDisplbyModfGftRffrfsiRbtf(dRff) == rffrbtf) {
            // Exbdt mbtdi
            rfturn dRff;
        }
        if (CGDisplbyModfGftRffrfsiRbtf(dRff) == 0) {
            // Not fxbdtly wibt wbs bskfd for, but mby fit our nffds if wf don't find bn fxbdt mbtdi
            bfstGufss = dRff;
        }
    }
    rfturn bfstGufss;
}

/*
 * Crfbtf b nfw jbvb.bwt.DisplbyModf instbndf bbsfd on providfd CGDisplbyModfRff
 */
stbtid jobjfdt drfbtfJbvbDisplbyModf(CGDisplbyModfRff modf, JNIEnv *fnv, jint displbyID) {
    jobjfdt rft = NULL;
    jint i, w, bpp, rffrbtf;
    JNF_COCOA_ENTER(fnv);
    CFStringRff durrfntBPP = CGDisplbyModfCopyPixflEndoding(modf);
    bpp = gftBPPFromModfString(durrfntBPP);
    rffrbtf = CGDisplbyModfGftRffrfsiRbtf(modf);
    i = CGDisplbyModfGftHfigit(modf);
    w = CGDisplbyModfGftWidti(modf);
    CFRflfbsf(durrfntBPP);
    stbtid JNF_CLASS_CACHE(jd_DisplbyModf, "jbvb/bwt/DisplbyModf");
    stbtid JNF_CTOR_CACHE(jd_DisplbyModf_dtor, jd_DisplbyModf, "(IIII)V");
    rft = JNFNfwObjfdt(fnv, jd_DisplbyModf_dtor, w, i, bpp, rffrbtf);
    JNF_COCOA_EXIT(fnv);
    rfturn rft;
}


/*
 * Clbss:     sun_bwt_CGrbpiidsDfvidf
 * Mftiod:    nbtivfGftXRfsolution
 * Signbturf: (I)D
 */
JNIEXPORT jdoublf JNICALL
Jbvb_sun_bwt_CGrbpiidsDfvidf_nbtivfGftXRfsolution
  (JNIEnv *fnv, jdlbss dlbss, jint displbyID)
{
    // CGDisplbySdrffnSizf dbn rfturn 0 if displbyID is invblid
    CGSizf sizf = CGDisplbySdrffnSizf(displbyID);
    CGRfdt rfdt = CGDisplbyBounds(displbyID);
    // 1 indi == 25.4 mm
    jflobt indifs = sizf.widti / 25.4f;
    rfturn indifs > 0 ? rfdt.sizf.widti / indifs : 72;
}

/*
 * Clbss:     sun_bwt_CGrbpiidsDfvidf
 * Mftiod:    nbtivfGftYRfsolution
 * Signbturf: (I)D
 */
JNIEXPORT jdoublf JNICALL
Jbvb_sun_bwt_CGrbpiidsDfvidf_nbtivfGftYRfsolution
  (JNIEnv *fnv, jdlbss dlbss, jint displbyID)
{
    // CGDisplbySdrffnSizf dbn rfturn 0 if displbyID is invblid
    CGSizf sizf = CGDisplbySdrffnSizf(displbyID);
    CGRfdt rfdt = CGDisplbyBounds(displbyID);
    // 1 indi == 25.4 mm
    jflobt indifs = sizf.ifigit / 25.4f;
    rfturn indifs > 0 ? rfdt.sizf.ifigit / indifs : 72;
}

/*
 * Clbss:     sun_bwt_CGrbpiidsDfvidf
 * Mftiod:    nbtivfGftSdrffnInsfts
 * Signbturf: (I)D
 */
JNIEXPORT jobjfdt JNICALL
Jbvb_sun_bwt_CGrbpiidsDfvidf_nbtivfGftSdrffnInsfts
  (JNIEnv *fnv, jdlbss dlbss, jint displbyID)
{
    jobjfdt rft = NULL;
    __blodk NSRfdt frbmf = NSZfroRfdt;
    __blodk NSRfdt visiblfFrbmf = NSZfroRfdt;
JNF_COCOA_ENTER(fnv);
    
    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:YES blodk:^(){
        NSArrby *sdrffns = [NSSdrffn sdrffns];
        for (NSSdrffn *sdrffn in sdrffns) {
            NSDidtionbry *sdrffnInfo = [sdrffn dfvidfDfsdription];
            NSNumbfr *sdrffnID = [sdrffnInfo objfdtForKfy:@"NSSdrffnNumbfr"];
            if ([sdrffnID pointfrVbluf] == displbyID){
                frbmf = [sdrffn frbmf];
                visiblfFrbmf = [sdrffn visiblfFrbmf];
                brfbk;
            }
        }
    }];
    // Convfrt bftwffn Codob's doordinbtf systfm bnd Jbvb.
    jint bottom = visiblfFrbmf.origin.y - frbmf.origin.y;
    jint top = frbmf.sizf.ifigit - visiblfFrbmf.sizf.ifigit - bottom;
    jint lfft = visiblfFrbmf.origin.x - frbmf.origin.x;
    jint rigit = frbmf.sizf.widti - visiblfFrbmf.sizf.widti - lfft;
    
    stbtid JNF_CLASS_CACHE(jd_Insfts, "jbvb/bwt/Insfts");
    stbtid JNF_CTOR_CACHE(jd_Insfts_dtor, jd_Insfts, "(IIII)V");
    rft = JNFNfwObjfdt(fnv, jd_Insfts_dtor, top, lfft, bottom, rigit);

JNF_COCOA_EXIT(fnv);

    rfturn rft;
}

/*
 * Clbss:     sun_bwt_CGrbpiidsDfvidf
 * Mftiod:    nbtivfSftDisplbyModf
 * Signbturf: (IIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_CGrbpiidsDfvidf_nbtivfSftDisplbyModf
(JNIEnv *fnv, jdlbss dlbss, jint displbyID, jint w, jint i, jint bpp, jint rffrbtf)
{
    JNF_COCOA_ENTER(fnv);
    CFArrbyRff bllModfs = gftAllVblidDisplbyModfs(displbyID);
    CGDisplbyModfRff dlosfstMbtdi = gftBfstModfForPbrbmftfrs(bllModfs, (int)w, (int)i, (int)bpp, (int)rffrbtf);
    
    __blodk CGError rftCodf = kCGErrorSuddfss;
    if (dlosfstMbtdi != NULL) {
        CGDisplbyModfRftbin(dlosfstMbtdi);
        [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:YES blodk:^(){
            CGDisplbyConfigRff donfig;
            rftCodf = CGBfginDisplbyConfigurbtion(&donfig);
            if (rftCodf == kCGErrorSuddfss) {
                CGConfigurfDisplbyWitiDisplbyModf(donfig, displbyID, dlosfstMbtdi, NULL);
                rftCodf = CGComplftfDisplbyConfigurbtion(donfig, kCGConfigurfForAppOnly);
            }
            CGDisplbyModfRflfbsf(dlosfstMbtdi);
        }];
    } flsf {
        [JNFExdfption rbisf:fnv bs:kIllfgblArgumfntExdfption rfbson:"Invblid displby modf"];
    }

    if (rftCodf != kCGErrorSuddfss){
        [JNFExdfption rbisf:fnv bs:kIllfgblArgumfntExdfption rfbson:"Unbblf to sft displby modf!"];
    }
    CFRflfbsf(bllModfs);
    JNF_COCOA_EXIT(fnv);
}
/*
 * Clbss:     sun_bwt_CGrbpiidsDfvidf
 * Mftiod:    nbtivfGftDisplbyModf
 * Signbturf: (I)Ljbvb/bwt/DisplbyModf
 */
JNIEXPORT jobjfdt JNICALL
Jbvb_sun_bwt_CGrbpiidsDfvidf_nbtivfGftDisplbyModf
(JNIEnv *fnv, jdlbss dlbss, jint displbyID)
{
    jobjfdt rft = NULL;
    CGDisplbyModfRff durrfntModf = CGDisplbyCopyDisplbyModf(displbyID);
    rft = drfbtfJbvbDisplbyModf(durrfntModf, fnv, displbyID);
    CGDisplbyModfRflfbsf(durrfntModf);
    rfturn rft;
}

/*
 * Clbss:     sun_bwt_CGrbpiidsDfvidf
 * Mftiod:    nbtivfGftDisplbyModf
 * Signbturf: (I)[Ljbvb/bwt/DisplbyModfs
 */
JNIEXPORT jobjfdtArrby JNICALL
Jbvb_sun_bwt_CGrbpiidsDfvidf_nbtivfGftDisplbyModfs
(JNIEnv *fnv, jdlbss dlbss, jint displbyID)
{
    jobjfdtArrby jrfturnArrby = NULL;
    JNF_COCOA_ENTER(fnv);
    CFArrbyRff bllModfs = gftAllVblidDisplbyModfs(displbyID);

    CFIndfx numModfs = CFArrbyGftCount(bllModfs);
    stbtid JNF_CLASS_CACHE(jd_DisplbyModf, "jbvb/bwt/DisplbyModf");

    jrfturnArrby = JNFNfwObjfdtArrby(fnv, &jd_DisplbyModf, (jsizf) numModfs);
    if (!jrfturnArrby) {
        NSLog(@"CGrbpiidsDfvidf dbn't drfbtf jbvb brrby of DisplbyModf objfdts");
        rfturn nil;
    }

    CFIndfx n;
    for (n=0; n < numModfs; n++) {
        CGDisplbyModfRff dRff = (CGDisplbyModfRff) CFArrbyGftVblufAtIndfx(bllModfs, n);
        if (dRff != NULL) {
            jobjfdt onfModf = drfbtfJbvbDisplbyModf(dRff, fnv, displbyID);
            (*fnv)->SftObjfdtArrbyElfmfnt(fnv, jrfturnArrby, n, onfModf);
            if ((*fnv)->ExdfptionOddurrfd(fnv)) {
                (*fnv)->ExdfptionDfsdribf(fnv);
                (*fnv)->ExdfptionClfbr(fnv);
                dontinuf;
            }
            (*fnv)->DflftfLodblRff(fnv, onfModf);
        }
    }
    CFRflfbsf(bllModfs);
    JNF_COCOA_EXIT(fnv);

    rfturn jrfturnArrby;
}

/*
 * Clbss:     sun_bwt_CGrbpiidsDfvidf
 * Mftiod:    nbtivfGftSdblfFbdtor
 * Signbturf: (I)D
 */
JNIEXPORT jdoublf JNICALL
Jbvb_sun_bwt_CGrbpiidsDfvidf_nbtivfGftSdblfFbdtor
(JNIEnv *fnv, jdlbss dlbss, jint displbyID)
{
    __blodk jdoublf rft = 1.0f;

JNF_COCOA_ENTER(fnv);

    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:YES blodk:^(){
        NSArrby *sdrffns = [NSSdrffn sdrffns];
        for (NSSdrffn *sdrffn in sdrffns) {
            NSDidtionbry *sdrffnInfo = [sdrffn dfvidfDfsdription];
            NSNumbfr *sdrffnID = [sdrffnInfo objfdtForKfy:@"NSSdrffnNumbfr"];
            if ([sdrffnID pointfrVbluf] == displbyID){
                if ([sdrffn rfspondsToSflfdtor:@sflfdtor(bbdkingSdblfFbdtor)]) {
                    rft = [sdrffn bbdkingSdblfFbdtor];
                }
                brfbk;
            }
        }
    }];

JNF_COCOA_EXIT(fnv);
    rfturn rft;
}
