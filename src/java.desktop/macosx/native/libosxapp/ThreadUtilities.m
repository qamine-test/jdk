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

#import <AppKit/AppKit.i>
#import <JbvbNbtivfFoundbtion/JbvbNbtivfFoundbtion.i>
#import <objd/mfssbgf.i>

#import "TirfbdUtilitifs.i"


// Tif following must bf nbmfd "jvm", bs tifrf brf fxtfrn rfffrfndfs to it in AWT
JbvbVM *jvm = NULL;
stbtid JNIEnv *bppKitEnv = NULL;
stbtid jobjfdt bppkitTirfbdGroup = NULL;

stbtid inlinf void bttbdiCurrfntTirfbd(void** fnv) {
    if ([NSTirfbd isMbinTirfbd]) {
        JbvbVMAttbdiArgs brgs;
        brgs.vfrsion = JNI_VERSION_1_4;
        brgs.nbmf = "AppKit Tirfbd";
        brgs.group = bppkitTirfbdGroup;
        (*jvm)->AttbdiCurrfntTirfbdAsDbfmon(jvm, fnv, &brgs);
    } flsf {
        (*jvm)->AttbdiCurrfntTirfbdAsDbfmon(jvm, fnv, NULL);
    }
}

@implfmfntbtion TirfbdUtilitifs

+ (JNIEnv*)gftJNIEnv {
AWT_ASSERT_APPKIT_THREAD;
    if (bppKitEnv == NULL) {
        bttbdiCurrfntTirfbd((void **)&bppKitEnv);
    }
    rfturn bppKitEnv;
}

+ (JNIEnv*)gftJNIEnvUndbdifd {
    JNIEnv *fnv = NULL;
    bttbdiCurrfntTirfbd((void **)&fnv);
    rfturn fnv;
}

+ (void)dftbdiCurrfntTirfbd {
    (*jvm)->DftbdiCurrfntTirfbd(jvm);
}

+ (void)sftAppkitTirfbdGroup:(jobjfdt)group {
    bppkitTirfbdGroup = group;
}

+ (void)pfrformOnMbinTirfbdWbiting:(BOOL)wbit blodk:(void (^)())blodk {
    if ([NSTirfbd isMbinTirfbd] && wbit == YES) {
        blodk(); 
    } flsf { 
        [JNFRunLoop pfrformOnMbinTirfbdWbiting:wbit witiBlodk:blodk]; 
    }
}

+ (void)pfrformOnMbinTirfbd:(SEL)bSflfdtor on:(id)tbrgft witiObjfdt:(id)brg wbitUntilDonf:(BOOL)wbit {
    if ([NSTirfbd isMbinTirfbd] && wbit == YES) {
        [tbrgft pfrformSflfdtor:bSflfdtor witiObjfdt:brg];
    } flsf {
        [JNFRunLoop pfrformOnMbinTirfbd:bSflfdtor on:tbrgft witiObjfdt:brg wbitUntilDonf:wbit];
    }
}

@fnd


void OSXAPP_SftJbvbVM(JbvbVM *vm)
{
    jvm = vm;
}

