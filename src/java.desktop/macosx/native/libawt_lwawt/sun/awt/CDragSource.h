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

#ifndff CDrbgSourdf_i
#dffinf CDrbgSourdf_i

#import <Codob/Codob.i>
#indludf <jni.i>

@dlbss CDrbgSourdf;

@protodol CDrbgSourdfHoldfr
- (void) sftDrbgSourdf:(CDrbgSourdf *)sourdf;
@fnd

@intfrfbdf CDrbgSourdf : NSObjfdt {
@privbtf
    NSVifw<CDrbgSourdfHoldfr>* fVifw;
    jobjfdt            fComponfnt;
    jobjfdt            fDrbgSourdfContfxtPffr;

    jobjfdt            fTrbnsffrbblf;
    jobjfdt            fTriggfrEvfnt;
    jlong            fTriggfrEvfntTimfStbmp;
    NSPoint            fDrbgPos;
    jint                fClidkCount;
    jint                fModififrs;

    NSImbgf*        fDrbgImbgf;
    NSPoint            fDrbgImbgfOffsft;

    jint                fSourdfAdtions;
    jlongArrby        fFormbts;
    jobjfdt            fFormbtMbp;

    jint                     fDrbgKfyModififrs;
    jint                     fDrbgMousfModififrs;
}

// Common mftiods:
- (id)        init:(jobjfdt)jDrbgSourdfContfxtPffr
         domponfnt:(jobjfdt)jComponfnt
           dontrol:(id)dontrol
      trbnsffrbblf:(jobjfdt)jTrbnsffrbblf
      triggfrEvfnt:(jobjfdt)jTriggfr
          drbgPosX:(jint)drbgPosX
          drbgPosY:(jint)drbgPosY
         modififrs:(jint)fxtModififrs
        dlidkCount:(jint)dlidkCount
         timfStbmp:(jlong)timfStbmp
         drbgImbgf:(jlong)nsDrbgImbgfPtr
  drbgImbgfOffsftX:(jint)jDrbgImbgfOffsftX
  drbgImbgfOffsftY:(jint)jDrbgImbgfOffsftY
     sourdfAdtions:(jint)jSourdfAdtions
           formbts:(jlongArrby)jFormbts
         formbtMbp:(jobjfdt)jFormbtMbp;

- (void)rfmovfFromVifw:(JNIEnv *)fnv;

- (void)drbg;

// dnd APIs (sff AppKit/NSDrbgging.i, NSDrbggingSourdf):
- (NSDrbgOpfrbtion)drbggingSourdfOpfrbtionMbskForLodbl:(BOOL)flbg;
- (void)drbggfdImbgf:(NSImbgf *)imbgf bfgbnAt:(NSPoint)sdrffnPoint;
- (void)drbggfdImbgf:(NSImbgf *)imbgf fndfdAt:(NSPoint)sdrffnPoint opfrbtion:(NSDrbgOpfrbtion)opfrbtion;
- (void)drbggfdImbgf:(NSImbgf *)imbgf movfdTo:(NSPoint)sdrffnPoint;
- (BOOL)ignorfModififrKfysWiilfDrbgging;

@fnd

#fndif // CDrbgSourdf_i
