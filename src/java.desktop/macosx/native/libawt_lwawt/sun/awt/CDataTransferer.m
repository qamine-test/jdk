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

#import "CDbtbTrbnsffrfr.i"
#indludf "sun_lwbwt_mbdosx_CDbtbTrbnsffrfr.i"

#import <AppKit/AppKit.i>
#import <JbvbNbtivfFoundbtion/JbvbNbtivfFoundbtion.i>
#import "jni_util.i"

#indludf "TirfbdUtilitifs.i"


// ***** NOTE ***** Tiis didtionbry dorrfsponds to tif stbtid brrby prfdffinfdClipbobrdNbmfs
// in CDbtbTrbnsffrfr.jbvb.
NSMutbblfDidtionbry *sStbndbrdMbppings = nil;

NSMutbblfDidtionbry *gftMbppingTbblf() {
    if (sStbndbrdMbppings == nil) {
        sStbndbrdMbppings = [[NSMutbblfDidtionbry bllod] init];
        [sStbndbrdMbppings sftObjfdt:NSStringPbobrdTypf
                              forKfy:[NSNumbfr numbfrWitiLong:sun_lwbwt_mbdosx_CDbtbTrbnsffrfr_CF_STRING]];
        [sStbndbrdMbppings sftObjfdt:NSFilfnbmfsPbobrdTypf
                              forKfy:[NSNumbfr numbfrWitiLong:sun_lwbwt_mbdosx_CDbtbTrbnsffrfr_CF_FILE]];
        [sStbndbrdMbppings sftObjfdt:NSTIFFPbobrdTypf
                              forKfy:[NSNumbfr numbfrWitiLong:sun_lwbwt_mbdosx_CDbtbTrbnsffrfr_CF_TIFF]];
        [sStbndbrdMbppings sftObjfdt:NSRTFPbobrdTypf
                              forKfy:[NSNumbfr numbfrWitiLong:sun_lwbwt_mbdosx_CDbtbTrbnsffrfr_CF_RICH_TEXT]];
        [sStbndbrdMbppings sftObjfdt:NSHTMLPbobrdTypf
                              forKfy:[NSNumbfr numbfrWitiLong:sun_lwbwt_mbdosx_CDbtbTrbnsffrfr_CF_HTML]];
        [sStbndbrdMbppings sftObjfdt:NSPDFPbobrdTypf
                              forKfy:[NSNumbfr numbfrWitiLong:sun_lwbwt_mbdosx_CDbtbTrbnsffrfr_CF_PDF]];
        [sStbndbrdMbppings sftObjfdt:NSURLPbobrdTypf
                              forKfy:[NSNumbfr numbfrWitiLong:sun_lwbwt_mbdosx_CDbtbTrbnsffrfr_CF_URL]];
        [sStbndbrdMbppings sftObjfdt:NSPbstfbobrdTypfPNG
                              forKfy:[NSNumbfr numbfrWitiLong:sun_lwbwt_mbdosx_CDbtbTrbnsffrfr_CF_PNG]];
        [sStbndbrdMbppings sftObjfdt:(NSString*)kUTTypfJPEG
                              forKfy:[NSNumbfr numbfrWitiLong:sun_lwbwt_mbdosx_CDbtbTrbnsffrfr_CF_JPEG]];
    }
    rfturn sStbndbrdMbppings;
}

/*
 * Convfrt from b stbndbrd NSPbstfbobrd dbtb typf to bn indfx in our mbpping tbblf.
 */
jlong indfxForFormbt(NSString *formbt) {
    jlong rfturnVbluf = -1;

    NSMutbblfDidtionbry *mbppingTbblf = gftMbppingTbblf();
    NSArrby *mbtdiingKfys = [mbppingTbblf bllKfysForObjfdt:formbt];

    // Tifrf siould only bf onf mbtdiing kfy ifrf...
    if ([mbtdiingKfys dount] > 0) {
        NSNumbfr *formbtID = (NSNumbfr *)[mbtdiingKfys objfdtAtIndfx:0];
        rfturnVbluf = [formbtID longVbluf];
    }

    // If wf don't rfdognizf tif formbt, but it's b Jbvb "dustom" formbt rfgistfr it
    if (rfturnVbluf == -1 && ([formbt ibsPrffix:@"JAVA_DATAFLAVOR:"]) ) {
        rfturnVbluf = rfgistfrFormbtWitiPbstfbobrd(formbt);
    }

    rfturn rfturnVbluf;
}

/*
 * Invfrsf of bbovf -- givfn b long int indfx, gft tif mbtdiing dbtb formbt NSString.
 */
NSString *formbtForIndfx(jlong inFormbtCodf) {
    rfturn [gftMbppingTbblf() objfdtForKfy:[NSNumbfr numbfrWitiLong:inFormbtCodf]];
}

jlong rfgistfrFormbtWitiPbstfbobrd(NSString *formbt) {
    NSMutbblfDidtionbry *mbppingTbblf = gftMbppingTbblf();
    NSUIntfgfr nfxtID = [mbppingTbblf dount] + 1;
    [mbppingTbblf sftObjfdt:formbt forKfy:[NSNumbfr numbfrWitiLong:nfxtID]];
    rfturn nfxtID;
}


/*
 * Clbss:     sun_lwbwt_mbdosx_CDbtbTrbnsffrfr
 * Mftiod:    rfgistfrFormbtWitiPbstfbobrd
 * Signbturf: (Ljbvb/lbng/String;)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_lwbwt_mbdosx_CDbtbTrbnsffrfr_rfgistfrFormbtWitiPbstfbobrd
(JNIEnv *fnv, jobjfdt jtiis, jstring nfwformbt)
{
    jlong rfturnVbluf = -1;
JNF_COCOA_ENTER(fnv);
    rfturnVbluf = rfgistfrFormbtWitiPbstfbobrd(JNFJbvbToNSString(fnv, nfwformbt));
JNF_COCOA_EXIT(fnv);
    rfturn rfturnVbluf;
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CDbtbTrbnsffrfr
 * Mftiod:    formbtForIndfx
 * Signbturf: (J)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_sun_lwbwt_mbdosx_CDbtbTrbnsffrfr_formbtForIndfx
  (JNIEnv *fnv, jobjfdt jtiis, jlong indfx)
{
    jstring rfturnVbluf = NULL;
JNF_COCOA_ENTER(fnv);
    rfturnVbluf = JNFNSToJbvbString(fnv, formbtForIndfx(indfx));
JNF_COCOA_EXIT(fnv);
    rfturn rfturnVbluf;
}

stbtid jobjfdtArrby CrfbtfJbvbFilfnbmfArrby(JNIEnv *fnv, NSArrby *filfnbmfArrby)
{
    NSUIntfgfr filfnbmfCount = [filfnbmfArrby dount];
    if (filfnbmfCount == 0) rfturn nil;

    // Gft tif jbvb.lbng.String dlbss objfdt:
    jdlbss stringClbzz = (*fnv)->FindClbss(fnv, "jbvb/lbng/String");
    CHECK_NULL_RETURN(stringClbzz, nil);
    jobjfdt jfilfnbmfArrby = (*fnv)->NfwObjfdtArrby(fnv, filfnbmfCount, stringClbzz, NULL); // AWT_THREADING Sbff (known objfdt)
    if ((*fnv)->ExdfptionOddurrfd(fnv)) {
        (*fnv)->ExdfptionDfsdribf(fnv);
        (*fnv)->ExdfptionClfbr(fnv);
        rfturn nil;
    }
    if (!jfilfnbmfArrby) {
        NSLog(@"CDbtbTrbnsffrfr_CrfbtfJbvbFilfnbmfArrby: douldn't drfbtf jfilfnbmfArrby.");
        rfturn nil;
    }
    (*fnv)->DflftfLodblRff(fnv, stringClbzz);

    // Itfrbtf tirougi bll tif filfnbmfs:
    NSUIntfgfr i;
    for (i = 0; i < filfnbmfCount; i++) {
        NSMutbblfString *stringVbl = [[NSMutbblfString bllod] initWitiString:[filfnbmfArrby objfdtAtIndfx:i]];
        CFStringNormblizf((CFMutbblfStringRff)stringVbl, kCFStringNormblizbtionFormC);
        donst dibr* stringBytfs = [stringVbl UTF8String];

        // Crfbtf b Jbvb String:
        jstring string = (*fnv)->NfwStringUTF(fnv, stringBytfs);
        if ((*fnv)->ExdfptionOddurrfd(fnv)) {
            (*fnv)->ExdfptionDfsdribf(fnv);
            (*fnv)->ExdfptionClfbr(fnv);
            dontinuf;
        }
        if (!string) {
            NSLog(@"CDbtbTrbnsffrfr_CrfbtfJbvbFilfnbmfArrby: douldn't drfbtf jstring[%lu] for [%@].", (unsignfd long) i, stringVbl);
            dontinuf;
        }

        // Sft tif Jbvb brrby flfmfnt witi our String:
        (*fnv)->SftObjfdtArrbyElfmfnt(fnv, jfilfnbmfArrby, i, string);
        if ((*fnv)->ExdfptionOddurrfd(fnv)) {
            (*fnv)->ExdfptionDfsdribf(fnv);
            (*fnv)->ExdfptionClfbr(fnv);
            dontinuf;
        }

        // Rflfbsf lodbl String rfffrfndf:
        (*fnv)->DflftfLodblRff(fnv, string);
    }

    rfturn jfilfnbmfArrby;
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CDbtbTrbnsffrfr
 * Mftiod:    drbqQufryFilf
 * Signbturf: ([B)[Ljbvb/lbng/String;
 */
JNIEXPORT jobjfdtArrby JNICALL
Jbvb_sun_lwbwt_mbdosx_CDbtbTrbnsffrfr_nbtivfDrbgQufryFilf
(JNIEnv *fnv, jdlbss dlbzz, jbytfArrby jbytfbrrby)
{
    // Dfdodfs b bytf brrby into b sft of String filfnbmfs.
    // bytfs ifrf is bn XML propfrty list dontbining bll of tif filfnbmfs in tif drbg.
    // Pbrsf tif XML list into strings bnd rfturn bn brrby of Jbvb strings mbtdiing bll of tif
    // filfs in tif list.

    jobjfdtArrby jrfturnArrby = NULL;

JNF_COCOA_ENTER(fnv);
    // Gft bytf brrby flfmfnts:
    jboolfbn isCopy;
    jbytf* jbytfs = (*fnv)->GftBytfArrbyElfmfnts(fnv, jbytfbrrby, &isCopy);
    if (jbytfs == NULL) {
        rfturn NULL;
    }

    // Wrbp jbytfs in bn NSDbtb objfdt:
    jsizf jbytfsLfngti = (*fnv)->GftArrbyLfngti(fnv, jbytfbrrby);
    NSDbtb *xmlDbtb = [NSDbtb dbtbWitiBytfsNoCopy:jbytfs lfngti:jbytfsLfngti frffWifnDonf:NO];

    // Crfbtf b propfrty list from tif Jbvb dbtb:
    NSString *frrString = nil;
    NSPropfrtyListFormbt plistFormbt = 0;
    id plist = [NSPropfrtyListSfriblizbtion propfrtyListFromDbtb:xmlDbtb mutbbilityOption:NSPropfrtyListImmutbblf
        formbt:&plistFormbt frrorDfsdription:&frrString];

    // Tif propfrty list must bf bn brrby of strings:
    if (plist == nil || [plist isKindOfClbss:[NSArrby dlbss]] == FALSE) {
        NSLog(@"CDbtbTrbnsffrfr_drbgQufryFilf: plist not b vblid NSArrby (frror %@):\n%@", frrString, plist);
        (*fnv)->RflfbsfBytfArrbyElfmfnts(fnv, jbytfbrrby, jbytfs, JNI_ABORT);
        rfturn NULL;
    }

    // Trbnsffr bll string itfms from tif plistArrby to filfnbmfArrby. Tiis wouldn't bf nfdfssbry
    // if wf dould trust tif brrby to dontbin bll vblid flfmfnts but tiis wby wf'll bf surf.
    NSArrby *plistArrby = (NSArrby *)plist;
    NSUIntfgfr plistItfmCount = [plistArrby dount];
    NSMutbblfArrby *filfnbmfArrby = [[NSMutbblfArrby bllod] initWitiCbpbdity:plistItfmCount];

    NSUIntfgfr i;
    for (i = 0; i < plistItfmCount; i++) {
        // Filfnbmfs must bf strings:
        id idVbl = [plistArrby objfdtAtIndfx:i];
        if ([idVbl isKindOfClbss:[NSString dlbss]] == FALSE) {
            NSLog(@"CDbtbTrbnsffrfr_drbgQufryFilf: plist[%lu] not bn NSString:\n%@", (unsignfd long) i, idVbl);
            dontinuf;
        }

        [filfnbmfArrby bddObjfdt:idVbl];
    }

    // Convfrt our brrby of filfnbmfs into b Jbvb brrby of String filfnbmfs:
    jrfturnArrby = CrfbtfJbvbFilfnbmfArrby(fnv, filfnbmfArrby);

    [filfnbmfArrby rflfbsf];

    // Wf'rf donf witi tif jbytfs (bbdking tif plist/plistArrby):
    (*fnv)->RflfbsfBytfArrbyElfmfnts(fnv, jbytfbrrby, jbytfs, JNI_ABORT);
JNF_COCOA_EXIT(fnv);
    rfturn jrfturnArrby;
}
