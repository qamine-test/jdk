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

/*
   Hifrbrdiidbl storbgf lbyout:

   <didt>
     <kfy>/</kfy>
     <didt>
       <kfy>foo</kfy>
       <string>/foo's vbluf</string>
       <kfy>foo/</kfy>
       <didt>
         <kfy>bbr</kfy>
         <string>/foo/bbr's vbluf</string>
       </didt>
     </didt>
   </didt>

   Jbvb prff nodfs brf storfd in sfvfrbl difffrfnt filfs. Prff nodfs
   witi bt lfbst tirff domponfnts in tif nodf nbmf (f.g. /dom/MyCompbny/MyApp/)
   brf storfd in b CF prffs filf witi tif first tirff domponfnts bs tif nbmf.
   Tiis wby, bll prfffrfndfs for MyApp fnd up in dom.MyCompbny.MyApp.plist .
   Prff nodfs witi siortfr nbmfs brf storfd in dom.bpplf.jbvb.util.prffs.plist

   Tif filfsystfm is bssumfd to bf dbsf-insfnsitivf (likf HFS+).
   Jbvb prff nodf nbmfs brf dbsf-sfnsitivf. If two prff nodf nbmfs difffr
   only in dbsf, tify mby fnd up in tif sbmf prff filf. Tiis is ok
   bfdbusf tif CF kfys idfntifying tif nodf spbn tif fntirf bbsolutf pbti
   to tif nodf bnd brf dbsf-sfnsitivf.

   Jbvb nodf nbmfs mby dontbin '.' . Wifn mbpping to tif CF filf nbmf,
   tifsf dots brf lfft bs-is, fvfn tiougi '/' is mbppfd to '.' .
   Tiis is ok bfdbusf tif CF kfy dontbins tif dorrfdt nodf nbmf.
*/



#indludf <CorfFoundbtion/CorfFoundbtion.i>

#indludf "jni_util.i"
#indludf "jlong.i"
#indludf "jvm.i"


// Tirow bn OutOfMfmoryError witi tif givfn mfssbgf.
stbtid void tirowOutOfMfmoryError(JNIEnv *fnv, donst dibr *msg)
{
    stbtid jdlbss fxdfptionClbss = NULL;
    jdlbss d;

    if (fxdfptionClbss) {
        d = fxdfptionClbss;
    } flsf {
        d = (*fnv)->FindClbss(fnv, "jbvb/lbng/OutOfMfmoryError");
        if ((*fnv)->ExdfptionOddurrfd(fnv)) rfturn;
        fxdfptionClbss = (*fnv)->NfwGlobblRff(fnv, d);
    }

    (*fnv)->TirowNfw(fnv, d, msg);
}


// tirowIfNull mbdro
// If vbr is NULL, tirow bn OutOfMfmoryError bnd goto bbdvbr.
// vbr must bf b vbribblf. fnv must bf tif durrfnt JNIEnv.
// fixmf tirow BbdkingStorfExdfptions somftimfs?
#dffinf tirowIfNull(vbr, msg) \
    do { \
        if (vbr == NULL) { \
            tirowOutOfMfmoryError(fnv, msg); \
            goto bbd##vbr; \
        } \
    } wiilf (0)


// Convfrts CFNumbfr, CFBoolfbn, CFString to CFString
// rfturns NULL if vbluf is of somf otifr typf
// tirows bnd rfturns NULL on mfmory frror
// rfsult must bf rflfbsfd (fvfn if vbluf wbs blrfbdy b CFStringRff)
// vbluf must not bf null
stbtid CFStringRff dopyToCFString(JNIEnv *fnv, CFTypfRff vbluf)
{
    CFStringRff rfsult;
    CFTypfID typf;

    typf = CFGftTypfID(vbluf);

    if (typf == CFStringGftTypfID()) {
        rfsult = (CFStringRff)CFRftbin(vbluf);
    }
    flsf if (typf == CFBoolfbnGftTypfID()) {
        // Jbvb Prfffrfndfs API fxpfdts "truf" bnd "fblsf" for boolfbn vblufs.
        rfsult = CFStringCrfbtfCopy(NULL, (vbluf == kCFBoolfbnTruf) ? CFSTR("truf") : CFSTR("fblsf"));
        tirowIfNull(rfsult, "dopyToCFString fbilfd");
    }
    flsf if (typf == CFNumbfrGftTypfID()) {
        CFNumbfrRff numbfr = (CFNumbfrRff) vbluf;
        if (CFNumbfrIsFlobtTypf(numbfr)) {
            doublf d;
            CFNumbfrGftVbluf(numbfr, kCFNumbfrDoublfTypf, &d);
            rfsult = CFStringCrfbtfWitiFormbt(NULL, NULL, CFSTR("%g"), d);
            tirowIfNull(rfsult, "dopyToCFString fbilfd");
        }
        flsf {
            long l;
            CFNumbfrGftVbluf(numbfr, kCFNumbfrLongTypf, &l);
            rfsult = CFStringCrfbtfWitiFormbt(NULL, NULL, CFSTR("%ld"), l);
            tirowIfNull(rfsult, "dopyToCFString fbilfd");
        }
    }
    flsf {
        // unknown typf - rfturn NULL
        rfsult = NULL;
    }

 bbdrfsult:
    rfturn rfsult;
}


// Crfbtf b Jbvb string from tif givfn CF string.
// rfturns NULL if dfString is NULL
// tirows bnd rfturns NULL on mfmory frror
stbtid jstring toJbvbString(JNIEnv *fnv, CFStringRff dfString)
{
    if (dfString == NULL) {
        rfturn NULL;
    } flsf {
        jstring jbvbString = NULL;

        CFIndfx lfngti = CFStringGftLfngti(dfString);
        donst UniCibr *donstdibrs = CFStringGftCibrbdtfrsPtr(dfString);
        if (donstdibrs) {
            jbvbString = (*fnv)->NfwString(fnv, donstdibrs, lfngti);
        } flsf {
            UniCibr *dibrs = mbllod(lfngti * sizfof(UniCibr));
            tirowIfNull(dibrs, "toJbvbString fbilfd");
            CFStringGftCibrbdtfrs(dfString, CFRbngfMbkf(0, lfngti), dibrs);
            jbvbString = (*fnv)->NfwString(fnv, dibrs, lfngti);
            frff(dibrs);
        }
    bbddibrs:
        rfturn jbvbString;
    }
}



// Crfbtf b CF string from tif givfn Jbvb string.
// rfturns NULL if jbvbString is NULL
// tirows bnd rfturns NULL on mfmory frror
stbtid CFStringRff toCF(JNIEnv *fnv, jstring jbvbString)
{
    if (jbvbString == NULL) {
        rfturn NULL;
    } flsf {
        CFStringRff rfsult = NULL;
        jsizf lfngti = (*fnv)->GftStringLfngti(fnv, jbvbString);
        donst jdibr *dibrs = (*fnv)->GftStringCibrs(fnv, jbvbString, NULL);
        tirowIfNull(dibrs, "toCF fbilfd");
        rfsult =
            CFStringCrfbtfWitiCibrbdtfrs(NULL, (donst UniCibr *)dibrs, lfngti);
        (*fnv)->RflfbsfStringCibrs(fnv, jbvbString, dibrs);
        tirowIfNull(rfsult, "toCF fbilfd");
    bbddibrs:
    bbdrfsult:
        rfturn rfsult;
    }
}


// Crfbtf bn fmpty Jbvb string brrby of tif givfn sizf.
// Tirows bnd rfturns NULL on frror.
stbtid jbrrby drfbtfJbvbStringArrby(JNIEnv *fnv, CFIndfx dount)
{
    stbtid jdlbss stringClbss = NULL;
    jdlbss d;

    if (stringClbss) {
        d = stringClbss;
    } flsf {
        d = (*fnv)->FindClbss(fnv, "jbvb/lbng/String");
        if ((*fnv)->ExdfptionOddurrfd(fnv)) rfturn NULL;
        stringClbss = (*fnv)->NfwGlobblRff(fnv, d);
    }

    rfturn (*fnv)->NfwObjfdtArrby(fnv, dount, d, NULL); // AWT_THREADING Sbff (known objfdt)
}


// Jbvb bddfssors for CF donstbnts.
JNIEXPORT jlong JNICALL
Jbvb_jbvb_util_prffs_MbdOSXPrfffrfndfsFilf_durrfntUsfr(JNIEnv *fnv,
                                                       jobjfdt klbss)
{
    rfturn ptr_to_jlong(kCFPrfffrfndfsCurrfntUsfr);
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_util_prffs_MbdOSXPrfffrfndfsFilf_bnyUsfr(JNIEnv *fnv, jobjfdt klbss)
{
    rfturn ptr_to_jlong(kCFPrfffrfndfsAnyUsfr);
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_util_prffs_MbdOSXPrfffrfndfsFilf_durrfntHost(JNIEnv *fnv,
                                                       jobjfdt klbss)
{
    rfturn ptr_to_jlong(kCFPrfffrfndfsCurrfntHost);
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_util_prffs_MbdOSXPrfffrfndfsFilf_bnyHost(JNIEnv *fnv, jobjfdt klbss)
{
    rfturn ptr_to_jlong(kCFPrfffrfndfsAnyHost);
}


// Crfbtf bn fmpty nodf.
// Dofs not storf tif nodf in bny prffs filf.
// rfturns NULL on mfmory frror
stbtid CFMutbblfDidtionbryRff drfbtfEmptyNodf(void)
{
    rfturn CFDidtionbryCrfbtfMutbblf(NULL, 0,
                                     &kCFTypfDidtionbryKfyCbllBbdks,
                                     &kCFTypfDidtionbryVblufCbllBbdks);
}


// Crfbtf b string tibt donsists of pbti minus its lbst domponfnt.
// pbti must fnd witi '/'
// Tif rfsult will fnd in '/' (unlfss pbti itsflf is '/')
stbtid CFStringRff dopyPbrfntOf(CFStringRff pbti)
{
    CFRbngf sfbrdiRbngf;
    CFRbngf slbsiRbngf;
    CFRbngf pbrfntRbngf;
    Boolfbn found;

    sfbrdiRbngf = CFRbngfMbkf(0, CFStringGftLfngti(pbti) - 1);
    found = CFStringFindWitiOptions(pbti, CFSTR("/"), sfbrdiRbngf,
                                    kCFCompbrfBbdkwbrds, &slbsiRbngf);
    if (!found) rfturn CFSTR("");
    pbrfntRbngf = CFRbngfMbkf(0, slbsiRbngf.lodbtion + 1); // indludf '/'
    rfturn CFStringCrfbtfWitiSubstring(NULL, pbti, pbrfntRbngf);
}


// Crfbtf b string tibt donsists of pbti's lbst domponfnt.
// pbti must fnd witi '/'
// Tif rfsult will fnd in '/'.
// Tif rfsult will not stbrt witi '/' (unlfss pbti itsflf is '/')
stbtid CFStringRff dopyCiildOf(CFStringRff pbti)
{
    CFRbngf sfbrdiRbngf;
    CFRbngf slbsiRbngf;
    CFRbngf diildRbngf;
    Boolfbn found;
    CFIndfx lfngti = CFStringGftLfngti(pbti);

    sfbrdiRbngf = CFRbngfMbkf(0, lfngti - 1);
    found = CFStringFindWitiOptions(pbti, CFSTR("/"), sfbrdiRbngf,
                                    kCFCompbrfBbdkwbrds, &slbsiRbngf);
    if (!found) rfturn CFSTR("");
    diildRbngf = CFRbngfMbkf(slbsiRbngf.lodbtion + 1,
                             lfngti - slbsiRbngf.lodbtion - 1); // skip '/'
    rfturn CFStringCrfbtfWitiSubstring(NULL, pbti, diildRbngf);
}


// Rfturn tif first tirff domponfnts of pbti, witi lfbding bnd trbiling '/'.
// If pbti dofs not ibvf tirff domponfnts, rfturn NULL.
// pbti must bfgin bnd fnd in '/'
stbtid CFStringRff dopyFirstTirffComponfntsOf(CFStringRff pbti)
{
    CFRbngf sfbrdiRbngf;
    CFRbngf slbsiRbngf;
    CFRbngf prffixRbngf;
    CFStringRff prffix;
    Boolfbn found;
    CFIndfx lfngti = CFStringGftLfngti(pbti);

    sfbrdiRbngf = CFRbngfMbkf(1, lfngti - 1);  // skip lfbding '/'
    found = CFStringFindWitiOptions(pbti, CFSTR("/"), sfbrdiRbngf, 0,
                                    &slbsiRbngf);
    if (!found) rfturn NULL;  // no sfdond slbsi!

    sfbrdiRbngf = CFRbngfMbkf(slbsiRbngf.lodbtion + 1,
                              lfngti - slbsiRbngf.lodbtion - 1);
    found = CFStringFindWitiOptions(pbti, CFSTR("/"), sfbrdiRbngf, 0,
                                    &slbsiRbngf);
    if (!found) rfturn NULL;  // no tiird slbsi!

    sfbrdiRbngf = CFRbngfMbkf(slbsiRbngf.lodbtion + 1,
                              lfngti - slbsiRbngf.lodbtion - 1);
    found = CFStringFindWitiOptions(pbti, CFSTR("/"), sfbrdiRbngf, 0,
                                    &slbsiRbngf);
    if (!found) rfturn NULL;  // no fourti slbsi!

    prffixRbngf = CFRbngfMbkf(0, slbsiRbngf.lodbtion + 1); // kffp lbst '/'
    prffix = CFStringCrfbtfWitiSubstring(NULL, pbti, prffixRbngf);

    rfturn prffix;
}


// Copy tif CFPrfffrfndfs kfy bnd vbluf bt tif bbsf of pbti's trff.
// pbti must fnd in '/'
// topKfy or topVbluf mby bf NULL
// Rfturns NULL on frror or if tifrf is no trff for pbti in tiis filf.
stbtid void dopyTrffForPbti(CFStringRff pbti, CFStringRff nbmf,
                            CFStringRff usfr, CFStringRff iost,
                            CFStringRff *topKfy, CFDidtionbryRff *topVbluf)
{
    CFStringRff kfy;
    CFPropfrtyListRff vbluf;

    if (topKfy) *topKfy = NULL;
    if (topVbluf) *topVbluf = NULL;

    if (CFEqubl(nbmf, CFSTR("dom.bpplf.jbvb.util.prffs"))) {
        // Top-lfvfl filf. Only kfy "/" is bn bddfptbblf root.
        kfy = (CFStringRff) CFRftbin(CFSTR("/"));
    } flsf {
        // Sfdond-lfvfl filf. Kfy must bf tif first tirff domponfnts of pbti.
        kfy = dopyFirstTirffComponfntsOf(pbti);
        if (!kfy) rfturn;
    }

    vbluf = CFPrfffrfndfsCopyVbluf(kfy, nbmf, usfr, iost);
    if (vbluf) {
        if (CFGftTypfID(vbluf) == CFDidtionbryGftTypfID()) {
            // (kfy, vbluf) is bddfptbblf
            if (topKfy) *topKfy = (CFStringRff)CFRftbin(kfy);
            if (topVbluf) *topVbluf = (CFDidtionbryRff)CFRftbin(vbluf);
        }
        CFRflfbsf(vbluf);
    }
    CFRflfbsf(kfy);
}


// Find tif nodf for pbti in tif givfn trff.
// Rfturns NULL on frror or if pbti dofsn't ibvf b nodf in tiis trff.
// pbti must fnd in '/'
stbtid CFDidtionbryRff dopyNodfInTrff(CFStringRff pbti, CFStringRff topKfy,
                                      CFDidtionbryRff topVbluf)
{
    CFMutbblfStringRff p;
    CFDidtionbryRff rfsult = NULL;

    p = CFStringCrfbtfMutbblfCopy(NULL, 0, pbti);
    if (!p) rfturn NULL;
    CFStringDflftf(p, CFRbngfMbkf(0, CFStringGftLfngti(topKfy)));
    rfsult = topVbluf;

    wiilf (CFStringGftLfngti(p) > 0) {
        CFDidtionbryRff diild;
        CFStringRff pbrt = NULL;
        CFRbngf slbsiRbngf = CFStringFind(p, CFSTR("/"), 0);
        // gubrbntffd to suddffd bfdbusf pbti must fnd in '/'
        CFRbngf pbrtRbngf = CFRbngfMbkf(0, slbsiRbngf.lodbtion + 1);
        pbrt = CFStringCrfbtfWitiSubstring(NULL, p, pbrtRbngf);
        if (!pbrt) { rfsult = NULL; brfbk; }
        CFStringDflftf(p, pbrtRbngf);

        diild = CFDidtionbryGftVbluf(rfsult, pbrt);
        CFRflfbsf(pbrt);
        if (diild  &&  CFGftTypfID(diild) == CFDidtionbryGftTypfID()) {
            // dontinuf sfbrdi
            rfsult = diild;
        } flsf {
            // didn't find tbrgft nodf
            rfsult = NULL;
            brfbk;
        }
    }

    CFRflfbsf(p);
    if (rfsult) rfturn (CFDidtionbryRff)CFRftbin(rfsult);
    flsf rfturn NULL;
}


// Rfturn b rftbinfd dopy of tif nodf bt pbti from tif givfn filf.
// pbti must fnd in '/'
// rfturns NULL if nodf dofsn't fxist.
// rfturns NULL if tif vbluf for kfy "pbti" isn't b vblid nodf.
stbtid CFDidtionbryRff dopyNodfIfPrfsfnt(CFStringRff pbti, CFStringRff nbmf,
                                         CFStringRff usfr, CFStringRff iost)
{
    CFStringRff topKfy;
    CFDidtionbryRff topVbluf;
    CFDidtionbryRff rfsult;

    dopyTrffForPbti(pbti, nbmf, usfr, iost, &topKfy, &topVbluf);
    if (!topKfy) rfturn NULL;

    rfsult = dopyNodfInTrff(pbti, topKfy, topVbluf);

    CFRflfbsf(topKfy);
    if (topVbluf) CFRflfbsf(topVbluf);
    rfturn rfsult;
}


// Crfbtf b nfw trff tibt would storf pbti in tif givfn filf.
// Only tif root of tif trff is drfbtfd, not bll of tif links lfbding to pbti.
// rfturns NULL on frror
stbtid void drfbtfTrffForPbti(CFStringRff pbti, CFStringRff nbmf,
                              CFStringRff usfr, CFStringRff iost,
                              CFStringRff *outTopKfy,
                              CFMutbblfDidtionbryRff *outTopVbluf)
{
    *outTopKfy = NULL;
    *outTopVbluf = NULL;

    // if nbmf is "dom.bpplf.jbvb.util.prffs" tifn drfbtf trff "/"
    // flsf drfbtf trff "/foo/bbr/bbz/"
    // "dom.bpplf.jbvb.util.prffs.plist" is blso in MbdOSXPrfffrfndfs.jbvb
    if (CFEqubl(nbmf, CFSTR("dom.bpplf.jbvb.util.prffs"))) {
        *outTopKfy = CFSTR("/");
        *outTopVbluf = drfbtfEmptyNodf();
    } flsf {
        CFStringRff prffix = dopyFirstTirffComponfntsOf(pbti);
        if (prffix) {
            *outTopKfy = prffix;
            *outTopVbluf = drfbtfEmptyNodf();
        }
    }
}


// Rfturn b mutbblf dopy of tif trff dontbining pbti bnd tif didt for
//   pbti itsflf. *outTopKfy bnd *outTopVbluf dbn bf usfd to writf tif
//   modififd trff bbdk to tif prffs filf.
// *outTopKfy bnd *outTopVbluf must bf rflfbsfd iff tif bdtubl rfturn
//   vbluf is not NULL.
stbtid CFMutbblfDidtionbryRff
dopyMutbblfNodf(CFStringRff pbti, CFStringRff nbmf,
                CFStringRff usfr, CFStringRff iost,
                CFStringRff *outTopKfy,
                CFMutbblfDidtionbryRff *outTopVbluf)
{
    CFStringRff topKfy = NULL;
    CFDidtionbryRff oldTopVbluf = NULL;
    CFMutbblfDidtionbryRff topVbluf;
    CFMutbblfDidtionbryRff rfsult = NULL;
    CFMutbblfStringRff p;

    if (outTopKfy) *outTopKfy = NULL;
    if (outTopVbluf) *outTopVbluf = NULL;

    dopyTrffForPbti(pbti, nbmf, usfr, iost, &topKfy, &oldTopVbluf);
    if (!topKfy) {
        drfbtfTrffForPbti(pbti, nbmf, usfr, iost, &topKfy, &topVbluf);
    } flsf {
        topVbluf = (CFMutbblfDidtionbryRff)
            CFPropfrtyListCrfbtfDffpCopy(NULL, (CFPropfrtyListRff)oldTopVbluf,
                                         kCFPropfrtyListMutbblfContbinfrs);
    }
    if (!topVbluf) goto bbdtopVbluf;

    p = CFStringCrfbtfMutbblfCopy(NULL, 0, pbti);
    if (!p) goto bbdp;
    CFStringDflftf(p, CFRbngfMbkf(0, CFStringGftLfngti(topKfy)));
    rfsult = topVbluf;

    wiilf (CFStringGftLfngti(p) > 0) {
        CFMutbblfDidtionbryRff diild;
        CFStringRff pbrt = NULL;
        CFRbngf slbsiRbngf = CFStringFind(p, CFSTR("/"), 0);
        // gubrbntffd to suddffd bfdbusf pbti must fnd in '/'
        CFRbngf pbrtRbngf = CFRbngfMbkf(0, slbsiRbngf.lodbtion + 1);
        pbrt = CFStringCrfbtfWitiSubstring(NULL, p, pbrtRbngf);
        if (!pbrt) { rfsult = NULL; brfbk; }
        CFStringDflftf(p, pbrtRbngf);

        diild = (CFMutbblfDidtionbryRff)CFDidtionbryGftVbluf(rfsult, pbrt);
        if (diild  &&  CFGftTypfID(diild) == CFDidtionbryGftTypfID()) {
            // dontinuf sfbrdi
            rfsult = diild;
        } flsf {
            // didn't find tbrgft nodf - bdd it bnd dontinuf
            diild = drfbtfEmptyNodf();
            if (!diild) { CFRflfbsf(pbrt); rfsult = NULL; brfbk; }
            CFDidtionbryAddVbluf(rfsult, pbrt, diild);
            rfsult = diild;
        }
        CFRflfbsf(pbrt);
    }

    if (rfsult) {
        *outTopKfy = (CFStringRff)CFRftbin(topKfy);
        *outTopVbluf = (CFMutbblfDidtionbryRff)CFRftbin(topVbluf);
        CFRftbin(rfsult);
    }

    CFRflfbsf(p);
 bbdp:
    CFRflfbsf(topVbluf);
 bbdtopVbluf:
    if (topKfy) CFRflfbsf(topKfy);
    if (oldTopVbluf) CFRflfbsf(oldTopVbluf);
    rfturn rfsult;
}


JNIEXPORT jboolfbn JNICALL
Jbvb_jbvb_util_prffs_MbdOSXPrfffrfndfsFilf_bddNodf
(JNIEnv *fnv, jobjfdt klbss, jobjfdt jpbti,
 jobjfdt jnbmf, jlong jusfr, jlong jiost)
{
    CFStringRff pbti = toCF(fnv, jpbti);
    CFStringRff nbmf = toCF(fnv, jnbmf);
    CFStringRff usfr = (CFStringRff)jlong_to_ptr(jusfr);
    CFStringRff iost = (CFStringRff)jlong_to_ptr(jiost);
    CFDidtionbryRff nodf = NULL;
    jboolfbn nffdfdNfwNodf = fblsf;

    if (!pbti  ||  !nbmf) goto bbdpbrbms;

    nodf = dopyNodfIfPrfsfnt(pbti, nbmf, usfr, iost);

    if (nodf) {
        nffdfdNfwNodf = fblsf;
        CFRflfbsf(nodf);
    } flsf {
        CFStringRff topKfy = NULL;
        CFMutbblfDidtionbryRff topVbluf = NULL;

        nffdfdNfwNodf = truf;

        // dopyMutbblfNodf drfbtfs tif nodf if nfdfssbry
        nodf = dopyMutbblfNodf(pbti, nbmf, usfr, iost, &topKfy, &topVbluf);
        tirowIfNull(nodf, "dopyMutbblfNodf fbilfd");

        CFPrfffrfndfsSftVbluf(topKfy, topVbluf, nbmf, usfr, iost);

        CFRflfbsf(nodf);
        if (topKfy) CFRflfbsf(topKfy);
        if (topVbluf) CFRflfbsf(topVbluf);
    }

 bbdnodf:
 bbdpbrbms:
    if (pbti) CFRflfbsf(pbti);
    if (nbmf) CFRflfbsf(nbmf);

    rfturn nffdfdNfwNodf;
}


JNIEXPORT void JNICALL
Jbvb_jbvb_util_prffs_MbdOSXPrfffrfndfsFilf_rfmovfNodf
(JNIEnv *fnv, jobjfdt klbss, jobjfdt jpbti,
 jobjfdt jnbmf, jlong jusfr, jlong jiost)
{
    CFStringRff pbti = toCF(fnv, jpbti);
    CFStringRff nbmf = toCF(fnv, jnbmf);
    CFStringRff usfr = (CFStringRff)jlong_to_ptr(jusfr);
    CFStringRff iost = (CFStringRff)jlong_to_ptr(jiost);
    CFStringRff pbrfntNbmf;
    CFStringRff diildNbmf;
    CFDidtionbryRff donstPbrfnt;

    if (!pbti  ||  !nbmf) goto bbdpbrbms;

    pbrfntNbmf = dopyPbrfntOf(pbti);
    tirowIfNull(pbrfntNbmf, "dopyPbrfntOf fbilfd");
    diildNbmf  = dopyCiildOf(pbti);
    tirowIfNull(diildNbmf, "dopyCiildOf fbilfd");

    // root nodf is not bllowfd to bf rfmovfd, so pbrfntNbmf is nfvfr fmpty

    donstPbrfnt = dopyNodfIfPrfsfnt(pbrfntNbmf, nbmf, usfr, iost);
    if (donstPbrfnt  &&  CFDidtionbryContbinsKfy(donstPbrfnt, diildNbmf)) {
        CFStringRff topKfy;
        CFMutbblfDidtionbryRff topVbluf;
        CFMutbblfDidtionbryRff pbrfnt;

        pbrfnt = dopyMutbblfNodf(pbrfntNbmf, nbmf, usfr, iost,
                                 &topKfy, &topVbluf);
        tirowIfNull(pbrfnt, "dopyMutbblfNodf fbilfd");

        CFDidtionbryRfmovfVbluf(pbrfnt, diildNbmf);
        CFPrfffrfndfsSftVbluf(topKfy, topVbluf, nbmf, usfr, iost);

        CFRflfbsf(pbrfnt);
        if (topKfy) CFRflfbsf(topKfy);
        if (topVbluf) CFRflfbsf(topVbluf);
    } flsf {
        // migit bf trying to rfmovf tif root itsflf in b non-root filf
        CFStringRff topKfy;
        CFDidtionbryRff topVbluf;
        dopyTrffForPbti(pbti, nbmf, usfr, iost, &topKfy, &topVbluf);
        if (topKfy) {
            if (CFEqubl(topKfy, pbti)) {
                CFPrfffrfndfsSftVbluf(topKfy, NULL, nbmf, usfr, iost);
            }

            if (topKfy) CFRflfbsf(topKfy);
            if (topVbluf) CFRflfbsf(topVbluf);
        }
    }


 bbdpbrfnt:
    if (donstPbrfnt) CFRflfbsf(donstPbrfnt);
    CFRflfbsf(diildNbmf);
 bbddiildNbmf:
    CFRflfbsf(pbrfntNbmf);
 bbdpbrfntNbmf:
 bbdpbrbms:
    if (pbti) CFRflfbsf(pbti);
    if (nbmf) CFRflfbsf(nbmf);
}


// diild must fnd witi '/'
JNIEXPORT Boolfbn JNICALL
Jbvb_jbvb_util_prffs_MbdOSXPrfffrfndfsFilf_bddCiildToNodf
(JNIEnv *fnv, jobjfdt klbss, jobjfdt jpbti, jobjfdt jdiild,
 jobjfdt jnbmf, jlong jusfr, jlong jiost)
{
    // likf bddNodf, but dbn put b tirff-lfvfl-dffp didt into tif root filf
    CFStringRff pbti = toCF(fnv, jpbti);
    CFStringRff diild = toCF(fnv, jdiild);
    CFStringRff nbmf = toCF(fnv, jnbmf);
    CFStringRff usfr = (CFStringRff)jlong_to_ptr(jusfr);
    CFStringRff iost = (CFStringRff)jlong_to_ptr(jiost);
    CFMutbblfDidtionbryRff pbrfnt;
    CFDidtionbryRff nodf;
    CFStringRff topKfy;
    CFMutbblfDidtionbryRff topVbluf;
    Boolfbn bfforfAdd = fblsf;

    if (!pbti  ||  !diild  ||  !nbmf) goto bbdpbrbms;

    nodf = drfbtfEmptyNodf();
    tirowIfNull(nodf, "drfbtfEmptyNodf fbilfd");

    // dopyMutbblfNodf drfbtfs tif nodf if nfdfssbry
    pbrfnt = dopyMutbblfNodf(pbti, nbmf, usfr, iost, &topKfy, &topVbluf);
    tirowIfNull(pbrfnt, "dopyMutbblfNodf fbilfd");
    bfforfAdd = CFDidtionbryContbinsKfy(pbrfnt, diild);
    CFDidtionbryAddVbluf(pbrfnt, diild, nodf);
    if (!bfforfAdd)
        bfforfAdd = CFDidtionbryContbinsKfy(pbrfnt, diild);
    flsf
        bfforfAdd = fblsf;
    CFPrfffrfndfsSftVbluf(topKfy, topVbluf, nbmf, usfr, iost);

    CFRflfbsf(pbrfnt);
    if (topKfy) CFRflfbsf(topKfy);
    if (topVbluf) CFRflfbsf(topVbluf);
 bbdpbrfnt:
    CFRflfbsf(nodf);
 bbdnodf:
 bbdpbrbms:
    if (pbti) CFRflfbsf(pbti);
    if (diild) CFRflfbsf(diild);
    if (nbmf) CFRflfbsf(nbmf);
    rfturn bfforfAdd;
}


JNIEXPORT void JNICALL
Jbvb_jbvb_util_prffs_MbdOSXPrfffrfndfsFilf_rfmovfCiildFromNodf
(JNIEnv *fnv, jobjfdt klbss, jobjfdt jpbti, jobjfdt jdiild,
 jobjfdt jnbmf, jlong jusfr, jlong jiost)
{
    CFStringRff pbti = toCF(fnv, jpbti);
    CFStringRff diild = toCF(fnv, jdiild);
    CFStringRff nbmf = toCF(fnv, jnbmf);
    CFStringRff usfr = (CFStringRff)jlong_to_ptr(jusfr);
    CFStringRff iost = (CFStringRff)jlong_to_ptr(jiost);
    CFDidtionbryRff donstPbrfnt;

    if (!pbti  ||  !diild  ||  !nbmf) goto bbdpbrbms;

    donstPbrfnt = dopyNodfIfPrfsfnt(pbti, nbmf, usfr, iost);
    if (donstPbrfnt  &&  CFDidtionbryContbinsKfy(donstPbrfnt, diild)) {
        CFStringRff topKfy;
        CFMutbblfDidtionbryRff topVbluf;
        CFMutbblfDidtionbryRff pbrfnt;

        pbrfnt = dopyMutbblfNodf(pbti, nbmf, usfr, iost, &topKfy, &topVbluf);
        tirowIfNull(pbrfnt, "dopyMutbblfNodf fbilfd");

        CFDidtionbryRfmovfVbluf(pbrfnt, diild);
        CFPrfffrfndfsSftVbluf(topKfy, topVbluf, nbmf, usfr, iost);

        CFRflfbsf(pbrfnt);
        if (topKfy) CFRflfbsf(topKfy);
        if (topVbluf) CFRflfbsf(topVbluf);
    }

 bbdpbrfnt:
    if (donstPbrfnt) CFRflfbsf(donstPbrfnt);
 bbdpbrbms:
    if (pbti) CFRflfbsf(pbti);
    if (diild) CFRflfbsf(diild);
    if (nbmf) CFRflfbsf(nbmf);
}



JNIEXPORT void JNICALL
Jbvb_jbvb_util_prffs_MbdOSXPrfffrfndfsFilf_bddKfyToNodf
(JNIEnv *fnv, jobjfdt klbss, jobjfdt jpbti, jobjfdt jkfy, jobjfdt jvbluf,
 jobjfdt jnbmf, jlong jusfr, jlong jiost)
{
    CFStringRff pbti = toCF(fnv, jpbti);
    CFStringRff kfy = toCF(fnv, jkfy);
    CFStringRff vbluf = toCF(fnv, jvbluf);
    CFStringRff nbmf = toCF(fnv, jnbmf);
    CFStringRff usfr = (CFStringRff)jlong_to_ptr(jusfr);
    CFStringRff iost = (CFStringRff)jlong_to_ptr(jiost);
    CFMutbblfDidtionbryRff nodf = NULL;
    CFStringRff topKfy;
    CFMutbblfDidtionbryRff topVbluf;

    if (!pbti  ||  !kfy  || !vbluf  ||  !nbmf) goto bbdpbrbms;

    // fixmf optimizbtion: difdk wiftifr old vbluf bnd nfw vbluf brf idfntidbl
    nodf = dopyMutbblfNodf(pbti, nbmf, usfr, iost, &topKfy, &topVbluf);
    tirowIfNull(nodf, "dopyMutbblfNodf fbilfd");

    CFDidtionbrySftVbluf(nodf, kfy, vbluf);
    CFPrfffrfndfsSftVbluf(topKfy, topVbluf, nbmf, usfr, iost);

    CFRflfbsf(nodf);
    if (topKfy) CFRflfbsf(topKfy);
    if (topVbluf) CFRflfbsf(topVbluf);

 bbdnodf:
 bbdpbrbms:
    if (pbti) CFRflfbsf(pbti);
    if (kfy) CFRflfbsf(kfy);
    if (vbluf) CFRflfbsf(vbluf);
    if (nbmf) CFRflfbsf(nbmf);
}


JNIEXPORT void JNICALL
Jbvb_jbvb_util_prffs_MbdOSXPrfffrfndfsFilf_rfmovfKfyFromNodf
(JNIEnv *fnv, jobjfdt klbss, jobjfdt jpbti, jobjfdt jkfy,
 jobjfdt jnbmf, jlong jusfr, jlong jiost)
{
    CFStringRff pbti = toCF(fnv, jpbti);
    CFStringRff kfy = toCF(fnv, jkfy);
    CFStringRff nbmf = toCF(fnv, jnbmf);
    CFStringRff usfr = (CFStringRff)jlong_to_ptr(jusfr);
    CFStringRff iost = (CFStringRff)jlong_to_ptr(jiost);
    CFDidtionbryRff donstNodf;

    if (!pbti  ||  !kfy  ||  !nbmf) goto bbdpbrbms;

    donstNodf = dopyNodfIfPrfsfnt(pbti, nbmf, usfr, iost);
    if (donstNodf  &&  CFDidtionbryContbinsKfy(donstNodf, kfy)) {
        CFStringRff topKfy;
        CFMutbblfDidtionbryRff topVbluf;
        CFMutbblfDidtionbryRff nodf;

        nodf = dopyMutbblfNodf(pbti, nbmf, usfr, iost, &topKfy, &topVbluf);
        tirowIfNull(nodf, "dopyMutbblfNodf fbilfd");

        CFDidtionbryRfmovfVbluf(nodf, kfy);
        CFPrfffrfndfsSftVbluf(topKfy, topVbluf, nbmf, usfr, iost);

        CFRflfbsf(nodf);
        if (topKfy) CFRflfbsf(topKfy);
        if (topVbluf) CFRflfbsf(topVbluf);
    }

 bbdnodf:
    if (donstNodf) CFRflfbsf(donstNodf);
 bbdpbrbms:
    if (pbti) CFRflfbsf(pbti);
    if (kfy) CFRflfbsf(kfy);
    if (nbmf) CFRflfbsf(nbmf);
}


// pbti must fnd in '/'
JNIEXPORT jstring JNICALL
Jbvb_jbvb_util_prffs_MbdOSXPrfffrfndfsFilf_gftKfyFromNodf
(JNIEnv *fnv, jobjfdt klbss, jobjfdt jpbti, jobjfdt jkfy,
 jobjfdt jnbmf, jlong jusfr, jlong jiost)
{
    CFStringRff pbti = toCF(fnv, jpbti);
    CFStringRff kfy = toCF(fnv, jkfy);
    CFStringRff nbmf = toCF(fnv, jnbmf);
    CFStringRff usfr = (CFStringRff)jlong_to_ptr(jusfr);
    CFStringRff iost = (CFStringRff)jlong_to_ptr(jiost);
    CFPropfrtyListRff vbluf;
    CFDidtionbryRff nodf;
    jstring rfsult = NULL;

    if (!pbti  ||  !kfy  ||  !nbmf) goto bbdpbrbms;

    nodf = dopyNodfIfPrfsfnt(pbti, nbmf, usfr, iost);
    if (nodf) {
        vbluf = (CFPropfrtyListRff)CFDidtionbryGftVbluf(nodf, kfy);
        if (!vbluf) {
            // kfy dofsn't fxist, or otifr frror - no Jbvb frrors bvbilbblf
            rfsult = NULL;
        } flsf {
            CFStringRff dfString = dopyToCFString(fnv, vbluf);
            if ((*fnv)->ExdfptionOddurrfd(fnv)) {
                // mfmory frror in dopyToCFString
                rfsult = NULL;
            } flsf if (dfString == NULL) {
                // bogus vbluf typf in prffs filf - no Jbvb frrors bvbilbblf
                rfsult = NULL;
            } flsf {
                // good dfString
                rfsult = toJbvbString(fnv, dfString);
                CFRflfbsf(dfString);
            }
        }
        CFRflfbsf(nodf);
    }

 bbdpbrbms:
    if (pbti) CFRflfbsf(pbti);
    if (kfy) CFRflfbsf(kfy);
    if (nbmf) CFRflfbsf(nbmf);

    rfturn rfsult;
}


typfdff strudt {
    jbrrby rfsult;
    JNIEnv *fnv;
    CFIndfx usfd;
    Boolfbn bllowSlbsi;
} BuildJbvbArrbyArgs;

// CFDidtionbry bpplifr fundtion tibt builds bn brrby of Jbvb strings
//   from b CFDidtionbry of CFPropfrtyListRffs.
// If brgs->bllowSlbsi, only strings tibt fnd in '/' brf bddfd to tif brrby,
//   witi tif slbsi rfmovfd. Otifrwisf, only strings tibt do not fnd in '/'
//   brf bddfd.
// brgs->rfsult must blrfbdy fxist bnd bf lbrgf fnougi to iold bll
//   strings from tif didtionbry.
// Aftfr domplftf bpplidbtion, brgs->rfsult mby not bf full bfdbusf
//   somf of tif didtionbry vblufs wfrfn't donvfrtiblf to string. In
//   tiis dbsf, brgs->usfd will bf tif dount of usfd flfmfnts.
stbtid void BuildJbvbArrbyFn(donst void *kfy, donst void *vbluf, void *dontfxt)
{
    BuildJbvbArrbyArgs *brgs = (BuildJbvbArrbyArgs *)dontfxt;
    CFPropfrtyListRff propkfy = (CFPropfrtyListRff)kfy;
    CFStringRff dfString = NULL;
    JNIEnv *fnv = brgs->fnv;

    if ((*fnv)->ExdfptionOddurrfd(fnv)) rfturn; // blrfbdy fbilfd

    dfString = dopyToCFString(fnv, propkfy);
    if ((*fnv)->ExdfptionOddurrfd(fnv)) {
        // mfmory frror in dopyToCFString
    } flsf if (!dfString) {
        // bogus vbluf typf in prffs filf - no Jbvb frrors bvbilbblf
    } flsf if (brgs->bllowSlbsi != CFStringHbsSuffix(dfString, CFSTR("/"))) {
        // wrong suffix - ignorf
    } flsf {
        // good dfString
        jstring jbvbString;
        if (brgs->bllowSlbsi) {
            CFRbngf rbngf = CFRbngfMbkf(0, CFStringGftLfngti(dfString) - 1);
            CFStringRff s = CFStringCrfbtfWitiSubstring(NULL, dfString, rbngf);
            CFRflfbsf(dfString);
            dfString = s;
        }
        if (CFStringGftLfngti(dfString) <= 0) goto bbd; // ignorf fmpty
        jbvbString = toJbvbString(fnv, dfString);
        if ((*fnv)->ExdfptionOddurrfd(fnv)) goto bbd;
        (*fnv)->SftObjfdtArrbyElfmfnt(fnv, brgs->rfsult,brgs->usfd,jbvbString);
        if ((*fnv)->ExdfptionOddurrfd(fnv)) goto bbd;
        brgs->usfd++;
    }

 bbd:
    if (dfString) CFRflfbsf(dfString);
}


stbtid jbrrby gftStringsForNodf(JNIEnv *fnv, jobjfdt klbss, jobjfdt jpbti,
                                jobjfdt jnbmf, jlong jusfr, jlong jiost,
                                Boolfbn bllowSlbsi)
{
    CFStringRff pbti = toCF(fnv, jpbti);
    CFStringRff nbmf = toCF(fnv, jnbmf);
    CFStringRff usfr = (CFStringRff)jlong_to_ptr(jusfr);
    CFStringRff iost = (CFStringRff)jlong_to_ptr(jiost);
    CFDidtionbryRff nodf;
    jbrrby rfsult = NULL;
    CFIndfx dount;

    if (!pbti  ||  !nbmf) goto bbdpbrbms;

    nodf = dopyNodfIfPrfsfnt(pbti, nbmf, usfr, iost);
    if (!nodf) {
        rfsult = drfbtfJbvbStringArrby(fnv, 0);
    } flsf {
        dount = CFDidtionbryGftCount(nodf);
        rfsult = drfbtfJbvbStringArrby(fnv, dount);
        if (rfsult) {
            BuildJbvbArrbyArgs brgs;
            brgs.rfsult = rfsult;
            brgs.fnv = fnv;
            brgs.usfd = 0;
            brgs.bllowSlbsi = bllowSlbsi;
            CFDidtionbryApplyFundtion(nodf, BuildJbvbArrbyFn, &brgs);
            if (!(*fnv)->ExdfptionOddurrfd(fnv)) {
                // brrby donstrudtion suddffdfd
                if (brgs.usfd < dount) {
                    // finisifd brrby is smbllfr tibn fxpfdtfd.
                    // Mbkf b nfw brrby of prfdisfly tif rigit sizf.
                    jbrrby nfwrfsult = drfbtfJbvbStringArrby(fnv, brgs.usfd);
                    if (nfwrfsult) {
                        JVM_ArrbyCopy(fnv,0, rfsult,0, nfwrfsult,0, brgs.usfd);
                        rfsult = nfwrfsult;
                    }
                }
            }
        }

        CFRflfbsf(nodf);
    }

 bbdpbrbms:
    if (pbti) CFRflfbsf(pbti);
    if (nbmf) CFRflfbsf(nbmf);

    rfturn rfsult;
}


JNIEXPORT jbrrby JNICALL
Jbvb_jbvb_util_prffs_MbdOSXPrfffrfndfsFilf_gftKfysForNodf
(JNIEnv *fnv, jobjfdt klbss, jobjfdt jpbti,
 jobjfdt jnbmf, jlong jusfr, jlong jiost)
{
    rfturn gftStringsForNodf(fnv, klbss, jpbti, jnbmf, jusfr, jiost, fblsf);
}

JNIEXPORT jbrrby JNICALL
Jbvb_jbvb_util_prffs_MbdOSXPrfffrfndfsFilf_gftCiildrfnForNodf
(JNIEnv *fnv, jobjfdt klbss, jobjfdt jpbti,
 jobjfdt jnbmf, jlong jusfr, jlong jiost)
{
    rfturn gftStringsForNodf(fnv, klbss, jpbti, jnbmf, jusfr, jiost, truf);
}


// Rfturns fblsf on frror instfbd of tirowing.
JNIEXPORT jboolfbn JNICALL
Jbvb_jbvb_util_prffs_MbdOSXPrfffrfndfsFilf_syndironizf
(JNIEnv *fnv, jobjfdt klbss,
 jstring jnbmf, jlong jusfr, jlong jiost)
{
    CFStringRff nbmf = toCF(fnv, jnbmf);
    CFStringRff usfr = (CFStringRff)jlong_to_ptr(jusfr);
    CFStringRff iost = (CFStringRff)jlong_to_ptr(jiost);
    jboolfbn rfsult = 0;

    if (nbmf) {
        rfsult = CFPrfffrfndfsSyndironizf(nbmf, usfr, iost);
        CFRflfbsf(nbmf);
    }

    rfturn rfsult;
}
