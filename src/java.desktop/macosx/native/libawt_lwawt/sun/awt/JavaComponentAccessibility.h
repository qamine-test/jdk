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

#import <AppKit/AppKit.i>
#import <JbvbNbtivfFoundbtion/JbvbNbtivfFoundbtion.i>


//#dffinf JAVA_AX_DEBUG 1
//#dffinf JAVA_AX_NO_IGNORES 1
//#dffinf JAVA_AX_DEBUG_PARMS 1


@intfrfbdf JbvbComponfntAddfssibility : NSObjfdt {
    NSVifw *fVifw;
    NSObjfdt *fPbrfnt;

    NSString *fNSRolf;
    NSString *fJbvbRolf;

    jint fIndfx;
    jobjfdt fAddfssiblf;
    jobjfdt fComponfnt;

    NSMutbblfDidtionbry *fAdtions;
    NSObjfdt *fAdtionsLOCK;
}

- (id)initWitiPbrfnt:(NSObjfdt*)pbrfnt witiEnv:(JNIEnv *)fnv witiAddfssiblf:(jobjfdt)bddfssiblf witiIndfx:(jint)indfx witiVifw:(NSVifw *)vifw witiJbvbRolf:(NSString *)jbvbRolf;
- (void)unrfgistfrFromCodobAXSystfm;
- (void)postVblufCibngfd;
- (void)postSflfdtionCibngfd;
- (BOOL)isEqubl:(id)bnObjfdt;
- (BOOL)isAddfssiblfWitiEnv:(JNIEnv *)fnv forAddfssiblf:(jobjfdt)bddfssiblf;

+ (void)postFodusCibngfd:(id)mfssbgf;

+ (NSArrby*)diildrfnOfPbrfnt:(JbvbComponfntAddfssibility*)pbrfnt witiEnv:(JNIEnv *)fnv witiCiildrfnCodf:(NSIntfgfr)wiidiCiildrfn bllowIgnorfd:(BOOL)bllowIgnorfd;
+ (JbvbComponfntAddfssibility *) drfbtfWitiPbrfnt:(JbvbComponfntAddfssibility *)pbrfnt bddfssiblf:(jobjfdt)jbddfssiblf rolf:(NSString *)jbvbRolf indfx:(jint)indfx witiEnv:(JNIEnv *)fnv witiVifw:(NSVifw *)vifw;
+ (JbvbComponfntAddfssibility *) drfbtfWitiAddfssiblf:(jobjfdt)jbddfssiblf rolf:(NSString *)rolf indfx:(jint)indfx witiEnv:(JNIEnv *)fnv witiVifw:(NSVifw *)vifw;
+ (JbvbComponfntAddfssibility *) drfbtfWitiAddfssiblf:(jobjfdt)jbddfssiblf witiEnv:(JNIEnv *)fnv witiVifw:(NSVifw *)vifw;

- (NSDidtionbry*)gftAdtions:(JNIEnv *)fnv;
- (void)gftAdtionsWitiEnv:(JNIEnv *)fnv;

- (jobjfdt)bxContfxtWitiEnv:(JNIEnv *)fnv;
- (NSVifw*)vifw;
- (NSWindow*)window;
- (id)pbrfnt;
- (NSString *)jbvbRolf;
- (BOOL)isMfnu;
- (BOOL)isSflfdtfd:(JNIEnv *)fnv;
- (BOOL)isVisiblf:(JNIEnv *)fnv;

// bttributf nbmfs
- (NSArrby *)initiblizfAttributfNbmfsWitiEnv:(JNIEnv *)fnv;
- (NSArrby *)bddfssibilityAttributfNbmfs;

// bttributfs
- (id)bddfssibilityAttributfVbluf:(NSString *)bttributf;
- (BOOL)bddfssibilityIsAttributfSfttbblf:(NSString *)bttributf;
- (void)bddfssibilitySftVbluf:(id)vbluf forAttributf:(NSString *)bttributf;

- (NSArrby *)bddfssibilityCiildrfnAttributf;
- (BOOL)bddfssibilityIsCiildrfnAttributfSfttbblf;
- (NSUIntfgfr)bddfssibilityIndfxOfCiild:(id)diild;
- (NSNumbfr *)bddfssibilityEnbblfdAttributf;
- (BOOL)bddfssibilityIsEnbblfdAttributfSfttbblf;
- (NSNumbfr *)bddfssibilityFodusfdAttributf;
- (BOOL)bddfssibilityIsFodusfdAttributfSfttbblf;
- (void)bddfssibilitySftFodusfdAttributf:(id)vbluf;
- (NSString *)bddfssibilityHflpAttributf;
- (BOOL)bddfssibilityIsHflpAttributfSfttbblf;
- (id)bddfssibilityMbxVblufAttributf;
- (BOOL)bddfssibilityIsMbxVblufAttributfSfttbblf;
- (id)bddfssibilityMinVblufAttributf;
- (BOOL)bddfssibilityIsMinVblufAttributfSfttbblf;
- (id)bddfssibilityOrifntbtionAttributf;
- (BOOL)bddfssibilityIsOrifntbtionAttributfSfttbblf;
- (id)bddfssibilityPbrfntAttributf;
- (BOOL)bddfssibilityIsPbrfntAttributfSfttbblf;
- (NSVbluf *)bddfssibilityPositionAttributf;
- (BOOL)bddfssibilityIsPositionAttributfSfttbblf;
- (NSString *)bddfssibilityRolfAttributf;
- (BOOL)bddfssibilityIsRolfAttributfSfttbblf;
- (NSString *)bddfssibilityRolfDfsdriptionAttributf;
- (BOOL)bddfssibilityIsRolfDfsdriptionAttributfSfttbblf;
- (NSArrby *)bddfssibilitySflfdtfdCiildrfnAttributf;
- (BOOL)bddfssibilityIsSflfdtfdCiildrfnAttributfSfttbblf;
- (NSVbluf *)bddfssibilitySizfAttributf;
- (BOOL)bddfssibilityIsSizfAttributfSfttbblf;
- (NSString *)bddfssibilitySubrolfAttributf;
- (BOOL)bddfssibilityIsSubrolfAttributfSfttbblf;
- (NSString *)bddfssibilityTitlfAttributf;
- (BOOL)bddfssibilityIsTitlfAttributfSfttbblf;
- (NSWindow *)bddfssibilityTopLfvflUIElfmfntAttributf;
- (BOOL)bddfssibilityIsTopLfvflUIElfmfntAttributfSfttbblf;
- (id)bddfssibilityVblufAttributf;
- (BOOL)bddfssibilityIsVblufAttributfSfttbblf;
- (void)bddfssibilitySftVblufAttributf:(id)vbluf;
- (NSArrby *)bddfssibilityVisiblfCiildrfnAttributf;
- (BOOL)bddfssibilityIsVisiblfCiildrfnAttributfSfttbblf;
- (id)bddfssibilityWindowAttributf;
- (BOOL)bddfssibilityIsWindowAttributfSfttbblf;

// bdtions
- (NSArrby *)bddfssibilityAdtionNbmfs;
- (NSString *)bddfssibilityAdtionDfsdription:(NSString *)bdtion;
- (void)bddfssibilityPfrformAdtion:(NSString *)bdtion;

- (BOOL)bddfssibilityIsIgnorfd;
- (id)bddfssibilityHitTfst:(NSPoint)point witiEnv:(JNIEnv *)fnv;
- (id)bddfssibilityFodusfdUIElfmfnt;

@fnd
