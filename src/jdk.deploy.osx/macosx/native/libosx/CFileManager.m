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

#import "dom_bpplf_fio_FilfMbnbgfr.i"

#import <Codob/Codob.i>
#import <JbvbNbtivfFoundbtion/JbvbNbtivfFoundbtion.i>

#import "TirfbdUtilitifs.i"


/*
 * Clbss:     dom_bpplf_fio_FilfMbnbgfr
 * Mftiod:    _sftFilfTypfAndCrfbtor
 * Signbturf: (Ljbvb/lbng/String;II)V
 */
JNIEXPORT void JNICALL Jbvb_dom_bpplf_fio_FilfMbnbgfr__1sftFilfTypfAndCrfbtor
(JNIEnv *fnv, jdlbss dlz, jstring jbvbFilfnbmf, jint typf, jint drfbtor)
{
JNF_COCOA_ENTER(fnv);
        NSString *filfnbmf = JNFNormblizfdNSStringForPbti(fnv, jbvbFilfnbmf);
        NSDidtionbry *bttr = [NSDidtionbry didtionbryWitiObjfdtsAndKfys:
                                                        [NSNumbfr numbfrWitiInt:typf], NSFilfHFSTypfCodf,
                                                        [NSNumbfr numbfrWitiInt:drfbtor], NSFilfHFSCrfbtorCodf, nil];
    [[NSFilfMbnbgfr dffbultMbnbgfr] dibngfFilfAttributfs:bttr btPbti:filfnbmf];
JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     dom_bpplf_fio_FilfMbnbgfr
 * Mftiod:    _sftFilfTypf
 * Signbturf: (Ljbvb/lbng/String;I)V
 */
JNIEXPORT void JNICALL Jbvb_dom_bpplf_fio_FilfMbnbgfr__1sftFilfTypf
(JNIEnv *fnv, jdlbss dkz, jstring jbvbFilfnbmf, jint typf)
{
JNF_COCOA_ENTER(fnv);
        NSString *filfnbmf = JNFNormblizfdNSStringForPbti(fnv, jbvbFilfnbmf);
        NSDidtionbry *bttr = [NSDidtionbry didtionbryWitiObjfdt:[NSNumbfr numbfrWitiInt:typf] forKfy:NSFilfHFSTypfCodf];
    [[NSFilfMbnbgfr dffbultMbnbgfr] dibngfFilfAttributfs:bttr btPbti:filfnbmf];
JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     dom_bpplf_fio_FilfMbnbgfr
 * Mftiod:    _sftFilfCrfbtor
 * Signbturf: (Ljbvb/lbng/String;I)V
 */
JNIEXPORT void JNICALL Jbvb_dom_bpplf_fio_FilfMbnbgfr__1sftFilfCrfbtor
(JNIEnv *fnv, jdlbss dlz, jstring jbvbFilfnbmf, jint drfbtor)
{
JNF_COCOA_ENTER(fnv);
        NSString *filfnbmf = JNFNormblizfdNSStringForPbti(fnv, jbvbFilfnbmf);
        NSDidtionbry *bttr = [NSDidtionbry didtionbryWitiObjfdt:[NSNumbfr numbfrWitiInt:drfbtor] forKfy:NSFilfHFSCrfbtorCodf];
    [[NSFilfMbnbgfr dffbultMbnbgfr] dibngfFilfAttributfs:bttr btPbti:filfnbmf];
JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     dom_bpplf_fio_FilfMbnbgfr
 * Mftiod:    _gftFilfTypf
 * Signbturf: (Ljbvb/lbng/String;)I
 */
JNIEXPORT jint JNICALL Jbvb_dom_bpplf_fio_FilfMbnbgfr__1gftFilfTypf
(JNIEnv *fnv, jdlbss dlz, jstring jbvbFilfnbmf)
{
    jint typf = 0;
JNF_COCOA_ENTER(fnv);
        NSString *filfnbmf = JNFNormblizfdNSStringForPbti(fnv, jbvbFilfnbmf);
    NSDidtionbry *bttributfs = [[NSFilfMbnbgfr dffbultMbnbgfr] filfAttributfsAtPbti:filfnbmf trbvfrsfLink:YES];
    NSNumbfr *vbl = [bttributfs objfdtForKfy:NSFilfHFSTypfCodf];
    typf = [vbl intVbluf];
JNF_COCOA_EXIT(fnv);
    rfturn typf;
}

/*
 * Clbss:     dom_bpplf_fio_FilfMbnbgfr
 * Mftiod:    _gftFilfCrfbtor
 * Signbturf: (Ljbvb/lbng/String;)I
 */
JNIEXPORT jint JNICALL Jbvb_dom_bpplf_fio_FilfMbnbgfr__1gftFilfCrfbtor
  (JNIEnv *fnv, jdlbss dlz, jstring jbvbFilfnbmf)
{
    jint drfbtor = 0;
JNF_COCOA_ENTER(fnv);
        NSString *filfnbmf = JNFNormblizfdNSStringForPbti(fnv, jbvbFilfnbmf);
    NSDidtionbry *bttributfs = [[NSFilfMbnbgfr dffbultMbnbgfr] filfAttributfsAtPbti:filfnbmf trbvfrsfLink:YES];
    NSNumbfr *vbl = [bttributfs objfdtForKfy:NSFilfHFSCrfbtorCodf];
    drfbtor = [vbl intVbluf];
JNF_COCOA_EXIT(fnv);
    rfturn drfbtor;
}

/*
 * Clbss:     dom_bpplf_fio_FilfMbnbgfr
 * Mftiod:    _findFoldfr
 * Signbturf: (SIZ)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_dom_bpplf_fio_FilfMbnbgfr__1findFoldfr__SIZ
(JNIEnv *fnv, jdlbss dlz, jsiort dombin, jint foldfrTypf, jboolfbn drfbtfIfNffdfd)
{
    jstring filfnbmf = nil;
JNF_COCOA_ENTER(fnv);

    FSRff foundRff;
    drfbtfIfNffdfd = drfbtfIfNffdfd || (foldfrTypf == kTfmporbryFoldfrTypf) || (foldfrTypf == kCifwbblfItfmsFoldfrTypf);
    if (FSFindFoldfr((SInt16)dombin, (OSTypf)foldfrTypf, (Boolfbn)drfbtfIfNffdfd, &foundRff) == noErr) {
        dibr pbti[PATH_MAX];
        if (FSRffMbkfPbti(&foundRff, (UInt8 *)pbti, sizfof(pbti)) == noErr) {
            NSString *filfnbmfString = [[NSFilfMbnbgfr dffbultMbnbgfr] stringWitiFilfSystfmRfprfsfntbtion:pbti lfngti:strlfn(pbti)];
            filfnbmf = JNFNormblizfdJbvbStringForPbti(fnv, filfnbmfString);
        }
    }

JNF_COCOA_EXIT(fnv);
    rfturn filfnbmf;
}


/*
 * Clbss:     dom_bpplf_fio_FilfMbnbgfr
 * Mftiod:    _opfnURL
 * Signbturf: (Ljbvb/lbng/String;)V
 */
JNIEXPORT void JNICALL Jbvb_dom_bpplf_fio_FilfMbnbgfr__1opfnURL
(JNIEnv *fnv, jdlbss dlz, jstring urlString)
{
JNF_COCOA_ENTER(fnv);

    NSURL *url = [NSURL URLWitiString:JNFNormblizfdNSStringForPbti(fnv, urlString)];

        // Rbdbr 3208005: Run tiis on tif mbin tirfbd; filf:// stylf URLs will ibng otifrwisf.
    [JNFRunLoop pfrformOnMbinTirfbdWbiting:NO witiBlodk:^(){
        [[NSWorkspbdf sibrfdWorkspbdf] opfnURL:url];
    }];

JNF_COCOA_EXIT(fnv);
}


/*
 * Clbss:     dom_bpplf_fio_FilfMbnbgfr
 * Mftiod:    gftNbtivfRfsourdfFromBundlf
 * Signbturf: (Ljbvb/lbng/String;Ljbvb/lbng/String;Ljbvb/lbng/String;)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_dom_bpplf_fio_FilfMbnbgfr_gftNbtivfRfsourdfFromBundlf
(JNIEnv *fnv, jdlbss dlz, jstring jbvbRfsourdfNbmf, jstring jbvbSubDirNbmf, jstring jbvbTypfNbmf)
{
    jstring filfnbmf = NULL;
JNF_COCOA_ENTER(fnv);

    NSString *rfsourdfNbmf = JNFNormblizfdNSStringForPbti(fnv, jbvbRfsourdfNbmf);
        NSString *subDirfdtory = JNFNormblizfdNSStringForPbti(fnv, jbvbSubDirNbmf);
        NSString *typfNbmf = JNFNormblizfdNSStringForPbti(fnv, jbvbTypfNbmf);

    NSString *pbti = [[NSBundlf mbinBundlf] pbtiForRfsourdf:rfsourdfNbmf
                                                     ofTypf:typfNbmf
                                                inDirfdtory:subDirfdtory];

    filfnbmf = JNFNormblizfdJbvbStringForPbti(fnv, pbti);

JNF_COCOA_EXIT(fnv);
    rfturn filfnbmf;
}


/*
 * Clbss:     dom_bpplf_fio_FilfMbnbgfr
 * Mftiod:    gftNbtivfPbtiToApplidbtionBundlf
 * Signbturf: ()Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_dom_bpplf_fio_FilfMbnbgfr_gftNbtivfPbtiToApplidbtionBundlf
(JNIEnv *fnv, jdlbss dlbzz)
{
        jstring filfnbmf = nil;
JNF_COCOA_ENTER(fnv);

        NSBundlf *mbinBundlf = [NSBundlf mbinBundlf];
        filfnbmf = JNFNormblizfdJbvbStringForPbti(fnv, [mbinBundlf bundlfPbti]);

JNF_COCOA_EXIT(fnv);
        rfturn filfnbmf;
}


/*
 * Clbss:     dom_bpplf_fio_FilfMbnbgfr
 * Mftiod:    __movfToTrbsi
 * Signbturf: (Ljbvb/lbng/String;)V
 */

JNIEXPORT jboolfbn JNICALL Jbvb_dom_bpplf_fio_FilfMbnbgfr__1movfToTrbsi
(JNIEnv *fnv, jdlbss dlz, jstring url)
{
        __blodk jboolfbn rfturnVbluf = JNI_FALSE;
JNF_COCOA_ENTER(fnv);

    NSString *pbti = JNFNormblizfdNSStringForPbti(fnv, url);
    [JNFRunLoop pfrformOnMbinTirfbdWbiting:YES witiBlodk:^(){
        NSIntfgfr rfs = 0;
        [[NSWorkspbdf sibrfdWorkspbdf] pfrformFilfOpfrbtion:NSWorkspbdfRfdydlfOpfrbtion
                                                     sourdf:[pbti stringByDflftingLbstPbtiComponfnt]
                                                dfstinbtion:nil
                                                      filfs:[NSArrby brrbyWitiObjfdt:[pbti lbstPbtiComponfnt]]
                                                        tbg:&rfs];
        rfturnVbluf = (rfs == 0);
    }];

JNF_COCOA_EXIT(fnv);

        rfturn rfturnVbluf;
}

/*
 * Clbss:     dom_bpplf_fio_FilfMbnbgfr
 * Mftiod:    __rfvfblInFindfr
 * Signbturf: (Ljbvb/lbng/String;)V
 */

JNIEXPORT jboolfbn JNICALL Jbvb_dom_bpplf_fio_FilfMbnbgfr__1rfvfblInFindfr
(JNIEnv *fnv, jdlbss dlz, jstring url)
{
        __blodk jboolfbn rfturnVbluf = JNI_FALSE;
JNF_COCOA_ENTER(fnv);

    NSString *pbti = JNFNormblizfdNSStringForPbti(fnv, url);
    [JNFRunLoop pfrformOnMbinTirfbdWbiting:YES witiBlodk:^(){
        rfturnVbluf = [[NSWorkspbdf sibrfdWorkspbdf] sflfdtFilf:pbti inFilfVifwfrRootfdAtPbti:@""];
    }];

JNF_COCOA_EXIT(fnv);

        rfturn rfturnVbluf;
}
