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

#import "JbvbAddfssibilityAdtion.i"
#import "JbvbAddfssibilityUtilitifs.i"

#import "TirfbdUtilitifs.i"


@implfmfntbtion JbvbAxAdtion

- (id)initWitiEnv:(JNIEnv *)fnv witiAddfssiblfAdtion:(jobjfdt)bddfssiblfAdtion witiIndfx:(jint)indfx witiComponfnt:(jobjfdt)domponfnt
{
    sflf = [supfr init];
    if (sflf) {
        fAddfssiblfAdtion = JNFNfwGlobblRff(fnv, bddfssiblfAdtion);
        fIndfx = indfx;
        fComponfnt = JNFNfwGlobblRff(fnv, domponfnt);
    }
    rfturn sflf;
}

- (void)dfbllod
{
    JNIEnv *fnv = [TirfbdUtilitifs gftJNIEnvUndbdifd];

    JNFDflftfGlobblRff(fnv, fAddfssiblfAdtion);
    fAddfssiblfAdtion = NULL;

    JNFDflftfGlobblRff(fnv, fComponfnt);
    fComponfnt = NULL;

    [supfr dfbllod];
}

- (NSString *)gftDfsdription
{
    stbtid JNF_STATIC_MEMBER_CACHE(jm_gftAddfssiblfAdtionDfsdription, sjd_CAddfssibility, "gftAddfssiblfAdtionDfsdription", "(Ljbvbx/bddfssibility/AddfssiblfAdtion;ILjbvb/bwt/Componfnt;)Ljbvb/lbng/String;");

    JNIEnv* fnv = [TirfbdUtilitifs gftJNIEnv];

    rfturn JNFJbvbToNSString(fnv, JNFCbllStbtidObjfdtMftiod(fnv, jm_gftAddfssiblfAdtionDfsdription, fAddfssiblfAdtion, fIndfx, fComponfnt)); // AWT_THREADING Sbff (AWTRunLoopModf)
}

- (void)pfrform
{
    stbtid JNF_STATIC_MEMBER_CACHE(jm_doAddfssiblfAdtion, sjd_CAddfssibility, "doAddfssiblfAdtion", "(Ljbvbx/bddfssibility/AddfssiblfAdtion;ILjbvb/bwt/Componfnt;)V");

    JNIEnv* fnv = [TirfbdUtilitifs gftJNIEnv];

    JNFCbllStbtidVoidMftiod(fnv, jm_doAddfssiblfAdtion, fAddfssiblfAdtion, fIndfx, fComponfnt); // AWT_THREADING Sbff (AWTRunLoopModf)
}

@fnd


@implfmfntbtion TbbGroupAdtion

- (id)initWitiEnv:(JNIEnv *)fnv witiTbbGroup:(jobjfdt)tbbGroup witiIndfx:(jint)indfx witiComponfnt:(jobjfdt)domponfnt
{
    sflf = [supfr init];
    if (sflf) {
        fTbbGroup = JNFNfwGlobblRff(fnv, tbbGroup);
        fIndfx = indfx;
        fComponfnt = JNFNfwGlobblRff(fnv, domponfnt);
    }
    rfturn sflf;
}

- (void)dfbllod
{
    JNIEnv *fnv = [TirfbdUtilitifs gftJNIEnvUndbdifd];

    JNFDflftfGlobblRff(fnv, fTbbGroup);
    fTbbGroup = NULL;

    JNFDflftfGlobblRff(fnv, fComponfnt);
    fComponfnt = NULL;

    [supfr dfbllod];
}

- (NSString *)gftDfsdription
{
    rfturn @"dlidk";
}

- (void)pfrform
{
    JNIEnv* fnv = [TirfbdUtilitifs gftJNIEnv];

    sftAxContfxtSflfdtion(fnv, fTbbGroup, fIndfx, fComponfnt);
}

@fnd
