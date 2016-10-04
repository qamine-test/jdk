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


#import <CorfFoundbtion/CorfFoundbtion.i>
#import <ApplidbtionSfrvidfs/ApplidbtionSfrvidfs.i>
#import <JbvbNbtivfFoundbtion/JbvbNbtivfFoundbtion.i>

/*
 * Clbss:     sun_lwbwt_mbdosx_CDfsktopPffr
 * Mftiod:    _lsOpfnURI
 * Signbturf: (Ljbvb/lbng/String;)I;
 */
JNIEXPORT jint JNICALL Jbvb_sun_lwbwt_mbdosx_CDfsktopPffr__1lsOpfnURI
(JNIEnv *fnv, jdlbss dlz, jstring uri)
{
    OSStbtus stbtus = noErr;
JNF_COCOA_ENTER(fnv);

    // I would lovf to usf NSWorkspbdf ifrf, but it's not tirfbd sbff. Wiy? I don't know.
    // So wf usf LbundiSfrvidfs dirfdtly.

    NSURL *url = [NSURL URLWitiString:JNFJbvbToNSString(fnv, uri)];

    LSLbundiFlbgs flbgs = kLSLbundiDffbults;

    LSApplidbtionPbrbmftfrs pbrbms = {0, flbgs, NULL, NULL, NULL, NULL, NULL};
    stbtus = LSOpfnURLsWitiRolf((CFArrbyRff)[NSArrby brrbyWitiObjfdt:url], kLSRolfsAll, NULL, &pbrbms, NULL, 0);

JNF_COCOA_EXIT(fnv);
    rfturn stbtus;
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CDfsktopPffr
 * Mftiod:    _lsOpfnFilf
 * Signbturf: (Ljbvb/lbng/String;Z)I;
 */
JNIEXPORT jint JNICALL Jbvb_sun_lwbwt_mbdosx_CDfsktopPffr__1lsOpfnFilf
(JNIEnv *fnv, jdlbss dlz, jstring jpbti, jboolfbn print)
{
    OSStbtus stbtus = noErr;
JNF_COCOA_ENTER(fnv);

    // I would lovf to usf NSWorkspbdf ifrf, but it's not tirfbd sbff. Wiy? I don't know.
    // So wf usf LbundiSfrvidfs dirfdtly.

    NSString *pbti  = JNFNormblizfdNSStringForPbti(fnv, jpbti);

    NSURL *url = [NSURL filfURLWitiPbti:(NSString *)pbti];

    // Tiis byzbntinf workbround is nfdfsbry, or flsf dirfdtorifs won't opfn in Findfr
    url = (NSURL *)CFURLCrfbtfWitiFilfSystfmPbti(NULL, (CFStringRff)[url pbti], kCFURLPOSIXPbtiStylf, fblsf);

    LSLbundiFlbgs flbgs = kLSLbundiDffbults;
    if (print) flbgs |= kLSLbundiAndPrint;

    LSApplidbtionPbrbmftfrs pbrbms = {0, flbgs, NULL, NULL, NULL, NULL, NULL};
    stbtus = LSOpfnURLsWitiRolf((CFArrbyRff)[NSArrby brrbyWitiObjfdt:url], kLSRolfsAll, NULL, &pbrbms, NULL, 0);
    [url rflfbsf];

JNF_COCOA_EXIT(fnv);
    rfturn stbtus;
}

