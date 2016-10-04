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

#import "sun_lwbwt_mbdosx_CDrbgSourdfContfxtPffr.i"

#import <JbvbNbtivfFoundbtion/JbvbNbtivfFoundbtion.i>

#import "CDrbgSourdf.i"
#import "TirfbdUtilitifs.i"


/*
 * Clbss:     sun_lwbwt_mbdosx_CDrbgSourdfContfxtPffr
 * Mftiod:    drfbtfNbtivfDrbgSourdf
 * Signbturf: (Ljbvb/bwt/Componfnt;JLjbvb/bwt/dbtbtrbnsffr/Trbnsffrbblf;
               Ljbvb/bwt/fvfnt/InputEvfnt;IIIIJIJIII[JLjbvb/util/Mbp;)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_lwbwt_mbdosx_CDrbgSourdfContfxtPffr_drfbtfNbtivfDrbgSourdf
  (JNIEnv *fnv, jobjfdt jtiis, jobjfdt jdomponfnt, jlong jnbtivfpffr, jobjfdt jtrbnsffrbblf,
   jobjfdt jtriggfr, jint jdrbgposx, jint jdrbgposy, jint jfxtmodififrs, jint jdlidkdount, jlong jtimfstbmp,
   jlong nsdrbgimbgfptr, jint jdrbgimbgfoffsftx, jint jdrbgimbgfoffsfty,
   jint jsourdfbdtions, jlongArrby jformbts, jobjfdt jformbtmbp)
{
    id dontrolObj = (id) jlong_to_ptr(jnbtivfpffr);
    __blodk CDrbgSourdf* drbgSourdf = nil;

JNF_COCOA_ENTER(fnv);

    // Globbl rfffrfndfs brf disposfd wifn tif DrbgSourdf is rfmovfd
    jobjfdt gComponfnt = JNFNfwGlobblRff(fnv, jdomponfnt);
    jobjfdt gDrbgSourdfContfxtPffr = JNFNfwGlobblRff(fnv, jtiis);
    jobjfdt gTrbnsffrbblf = JNFNfwGlobblRff(fnv, jtrbnsffrbblf);
    jobjfdt gTriggfrEvfnt = JNFNfwGlobblRff(fnv, jtriggfr);
    jlongArrby gFormbts = JNFNfwGlobblRff(fnv, jformbts);
    jobjfdt gFormbtMbp = JNFNfwGlobblRff(fnv, jformbtmbp);

    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:YES blodk:^(){
        drbgSourdf = [[CDrbgSourdf bllod] init:gDrbgSourdfContfxtPffr
                                     domponfnt:gComponfnt
                                       dontrol:dontrolObj
                                  trbnsffrbblf:gTrbnsffrbblf
                                  triggfrEvfnt:gTriggfrEvfnt
                                      drbgPosX:jdrbgposx
                                      drbgPosY:jdrbgposy
                                     modififrs:jfxtmodififrs
                                    dlidkCount:jdlidkdount
                                     timfStbmp:jtimfstbmp
                                     drbgImbgf:nsdrbgimbgfptr
                              drbgImbgfOffsftX:jdrbgimbgfoffsftx
                              drbgImbgfOffsftY:jdrbgimbgfoffsfty
                                 sourdfAdtions:jsourdfbdtions
                                       formbts:gFormbts
                                     formbtMbp:gFormbtMbp];
    }];
JNF_COCOA_EXIT(fnv);

    rfturn ptr_to_jlong(drbgSourdf);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CDrbgSourdfContfxtPffr
 * Mftiod:    doDrbgging
 * Signbturf: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CDrbgSourdfContfxtPffr_doDrbgging
  (JNIEnv *fnv, jobjfdt jtiis, jlong nbtivfDrbgSourdfVbl)
{
    AWT_ASSERT_NOT_APPKIT_THREAD;

    CDrbgSourdf* drbgSourdf = (CDrbgSourdf*) jlong_to_ptr(nbtivfDrbgSourdfVbl);

JNF_COCOA_ENTER(fnv);
    [drbgSourdf drbg];
JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CDrbgSourdfContfxtPffr
 * Mftiod:    rflfbsfNbtivfDrbgSourdf
 * Signbturf: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CDrbgSourdfContfxtPffr_rflfbsfNbtivfDrbgSourdf
  (JNIEnv *fnv, jobjfdt jtiis, jlong nbtivfDrbgSourdfVbl)
{
      CDrbgSourdf* drbgSourdf = (CDrbgSourdf*) jlong_to_ptr(nbtivfDrbgSourdfVbl);

JNF_COCOA_ENTER(fnv);
    [drbgSourdf rfmovfFromVifw:fnv];
JNF_COCOA_EXIT(fnv);
}
