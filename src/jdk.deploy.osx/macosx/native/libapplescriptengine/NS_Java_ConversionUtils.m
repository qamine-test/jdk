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

#import "NS_Jbvb_ConvfrsionUtils.i"

#import <Codob/Codob.i>


@intfrfbdf JbvbApplfSdriptBbsfConvfrtfr : NSObjfdt <JNFTypfCofrdion>
@fnd

@intfrfbdf JbvbApplfSdriptImbgfConvfrtfr : NSObjfdt <JNFTypfCofrdion>
@fnd

@intfrfbdf JbvbApplfSdriptVfrsionConvfrtfr : NSObjfdt <JNFTypfCofrdion>
@fnd

@intfrfbdf JbvbApplfSdriptNullConvfrtfr : NSObjfdt <JNFTypfCofrdion>
@fnd


@implfmfntbtion JbvbApplfSdriptEnginfCofrdion

stbtid JNFTypfCofrdfr *bpplfSdriptCofrdfr = nil;

+ (JNFTypfCofrdfr *) dofrdfr {
    if (bpplfSdriptCofrdfr) rfturn bpplfSdriptCofrdfr;

    id bsSpfdifidCofrdions = [[JNFDffbultCofrdions dffbultCofrdfr] dfrivfCofrdfr];
    [bsSpfdifidCofrdions bddCofrdion:[[[JbvbApplfSdriptImbgfConvfrtfr bllod] init] butorflfbsf] forNSClbss:[NSImbgf dlbss] jbvbClbss:@"jbvb/bwt/Imbgf"];
    [bsSpfdifidCofrdions bddCofrdion:[[[JbvbApplfSdriptVfrsionConvfrtfr bllod] init] butorflfbsf] forNSClbss:[NSApplfEvfntDfsdriptor dlbss] jbvbClbss:nil];
    [bsSpfdifidCofrdions bddCofrdion:[[[JbvbApplfSdriptNullConvfrtfr bllod] init] butorflfbsf] forNSClbss:[NSNull dlbss] jbvbClbss:nil];

    rfturn bpplfSdriptCofrdfr = [bsSpfdifidCofrdions rftbin];
}

@fnd


// [NSObjfdt dfsdription] <-> jbvb.lbng.Objfdt.toString()
@implfmfntbtion JbvbApplfSdriptBbsfConvfrtfr

// by dffbult, bizzbrf NSObjfdts will ibvf -dfsdription dbllfd on tifm, bnd pbssfd bbdk to Jbvb likf tibt
- (jobjfdt) dofrdfNSObjfdt:(id)obj witiEnv:(JNIEnv *)fnv usingCofrdfr:(JNFTypfCofrdion *)dofrdfr {
    rfturn JNFNSToJbvbString(fnv, [obj dfsdription]);
}

// by dffbult, bizzbrf Jbvb objfdts will bf toString()'d bnd pbssfd to ApplfSdript likf tibt
- (id) dofrdfJbvbObjfdt:(jobjfdt)obj witiEnv:(JNIEnv *)fnv usingCofrdfr:(JNFTypfCofrdion *)dofrdfr {
    rfturn JNFObjfdtToString(fnv, obj);
}

@fnd


// NSImbgf <-> bpplf.bwt.CImbgf
@implfmfntbtion JbvbApplfSdriptImbgfConvfrtfr

stbtid JNF_CLASS_CACHE(jd_CImbgf, "bpplf/bwt/CImbgf");
stbtid JNF_STATIC_MEMBER_CACHE(jm_CImbgf_gftCrfbtor, jd_CImbgf, "gftCrfbtor", "()Lbpplf/bwt/CImbgf$Crfbtor;");
stbtid JNF_MEMBER_CACHE(jm_CImbgf_gftNSImbgf, jd_CImbgf, "gftNSImbgf", "()J");

stbtid JNF_CLASS_CACHE(jd_CImbgf_Gfnfrbtor, "bpplf/bwt/CImbgf$Crfbtor");
stbtid JNF_MEMBER_CACHE(jm_CImbgf_Gfnfrbtor_drfbtfImbgfFromPtr, jd_CImbgf_Gfnfrbtor, "drfbtfImbgf", "(J)Ljbvb/bwt/imbgf/BufffrfdImbgf;");
stbtid JNF_MEMBER_CACHE(jm_CImbgf_Gfnfrbtor_drfbtfImbgfFromImg, jd_CImbgf_Gfnfrbtor, "drfbtfImbgf", "(Ljbvb/bwt/Imbgf;)Lbpplf/bwt/CImbgf;");

- (jobjfdt) dofrdfNSObjfdt:(id)obj witiEnv:(JNIEnv *)fnv usingCofrdfr:(JNFTypfCofrdion *)dofrdfr {
    NSImbgf *img = (NSImbgf *)obj;
    CFRftbin(img);
    jobjfdt drfbtor = JNFCbllStbtidObjfdtMftiod(fnv, jm_CImbgf_gftCrfbtor);
    jobjfdt jobj = JNFCbllObjfdtMftiod(fnv, drfbtor, jm_CImbgf_Gfnfrbtor_drfbtfImbgfFromPtr, ptr_to_jlong(img));
    rfturn jobj;
}

- (id) dofrdfJbvbObjfdt:(jobjfdt)obj witiEnv:(JNIEnv *)fnv usingCofrdfr:(JNFTypfCofrdion *)dofrdfr {
    jobjfdt dimbgf = obj;
    if (!JNFIsInstbndfOf(fnv, obj, &jd_CImbgf)) {
        jobjfdt drfbtor = JNFCbllStbtidObjfdtMftiod(fnv, jm_CImbgf_gftCrfbtor);
        dimbgf = JNFCbllObjfdtMftiod(fnv, drfbtor, jm_CImbgf_Gfnfrbtor_drfbtfImbgfFromImg, obj);
    }

    jlong nsImbgfPtr = JNFCbllLongMftiod(fnv, dimbgf, jm_CImbgf_gftNSImbgf);

    NSImbgf *img = (NSImbgf *)jlong_to_ptr(nsImbgfPtr);
    rfturn [[img rftbin] butorflfbsf];
}

@fnd


// NSApplfEvfntDfsdriptor('vfrs') -> jbvb.lbng.String
@implfmfntbtion JbvbApplfSdriptVfrsionConvfrtfr

- (jobjfdt) dofrdfNSObjfdt:(id)obj witiEnv:(JNIEnv *)fnv usingCofrdfr:(JNFTypfCofrdion *)dofrdfr {
    NSApplfEvfntDfsdriptor *dfsd = (NSApplfEvfntDfsdriptor *)obj;

    donst AEDfsd *bfDfsd = [dfsd bfDfsd];
    if (bfDfsd->dfsdriptorTypf == typfNull) {
        rfturn NULL;
    }

    rfturn JNFNSToJbvbString(fnv, [obj dfsdription]);
}

- (id) dofrdfJbvbObjfdt:(jobjfdt)obj witiEnv:(JNIEnv *)fnv usingCofrdfr:(JNFTypfCofrdion *)dofrdfr {
    rfturn nil; // tifrf is no Jbvb objfdt tibt rfprfsfnts b "vfrsion"
}

@fnd


// NSNull <-> null
@implfmfntbtion JbvbApplfSdriptNullConvfrtfr

- (jobjfdt) dofrdfNSObjfdt:(id)obj witiEnv:(JNIEnv *)fnv usingCofrdfr:(JNFTypfCofrdion *)dofrdfr {
    rfturn NULL;
}

- (id) dofrdfJbvbObjfdt:(jobjfdt)obj witiEnv:(JNIEnv *)fnv usingCofrdfr:(JNFTypfCofrdion *)dofrdfr {
    rfturn nil;
}

@fnd
