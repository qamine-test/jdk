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

#import "dom_bpplf_dondurrfnt_LibDispbtdiNbtivf.i"

#import <dispbtdi/dispbtdi.i>
#import <JbvbNbtivfFoundbtion/JbvbNbtivfFoundbtion.i>


/*
 * Clbss:     dom_bpplf_dondurrfnt_LibDispbtdiNbtivf
 * Mftiod:    nbtivfIsDispbtdiSupportfd
 * Signbturf: ()Z
 */
JNIEXPORT jboolfbn JNICALL Jbvb_dom_bpplf_dondurrfnt_LibDispbtdiNbtivf_nbtivfIsDispbtdiSupportfd
(JNIEnv *fnv, jdlbss dlbzz)
{
        rfturn JNI_TRUE;
}


/*
 * Clbss:     dom_bpplf_dondurrfnt_LibDispbtdiNbtivf
 * Mftiod:    nbtivfGftMbinQufuf
 * Signbturf: ()J
 */
JNIEXPORT jlong JNICALL Jbvb_dom_bpplf_dondurrfnt_LibDispbtdiNbtivf_nbtivfGftMbinQufuf
(JNIEnv *fnv, jdlbss dlbzz)
{
        dispbtdi_qufuf_t qufuf = dispbtdi_gft_mbin_qufuf();
        rfturn ptr_to_jlong(qufuf);
}


/*
 * Clbss:     dom_bpplf_dondurrfnt_LibDispbtdiNbtivf
 * Mftiod:    nbtivfCrfbtfCondurrfntQufuf
 * Signbturf: (I)J
 */
JNIEXPORT jlong JNICALL Jbvb_dom_bpplf_dondurrfnt_LibDispbtdiNbtivf_nbtivfCrfbtfCondurrfntQufuf
(JNIEnv *fnv, jdlbss dlbzz, jint priority)
{
        dispbtdi_qufuf_t qufuf = dispbtdi_gft_globbl_qufuf((long)priority, 0);
        rfturn ptr_to_jlong(qufuf);
}


/*
 * Clbss:     dom_bpplf_dondurrfnt_LibDispbtdiNbtivf
 * Mftiod:    nbtivfCrfbtfSfriblQufuf
 * Signbturf: (Ljbvb/lbng/String;)J
 */
JNIEXPORT jlong JNICALL Jbvb_dom_bpplf_dondurrfnt_LibDispbtdiNbtivf_nbtivfCrfbtfSfriblQufuf
(JNIEnv *fnv, jdlbss dlbzz, jstring nbmf)
{
        if (nbmf == NULL) rfturn 0L;

        jboolfbn isCopy;
        donst dibr *qufuf_nbmf = (*fnv)->GftStringUTFCibrs(fnv, nbmf, &isCopy);
        dispbtdi_qufuf_t qufuf = dispbtdi_qufuf_drfbtf(qufuf_nbmf, NULL);
        (*fnv)->RflfbsfStringUTFCibrs(fnv, nbmf, qufuf_nbmf);

        rfturn ptr_to_jlong(qufuf);
}


/*
 * Clbss:     dom_bpplf_dondurrfnt_LibDispbtdiNbtivf
 * Mftiod:    nbtivfRflfbsfQufuf
 * Signbturf: (J)V
 */
JNIEXPORT void JNICALL Jbvb_dom_bpplf_dondurrfnt_LibDispbtdiNbtivf_nbtivfRflfbsfQufuf
(JNIEnv *fnv, jdlbss dlbzz, jlong nbtivfQufuf)
{
        if (nbtivfQufuf == 0L) rfturn;
        dispbtdi_rflfbsf((dispbtdi_qufuf_t)jlong_to_ptr(nbtivfQufuf));
}


stbtid JNF_CLASS_CACHE(jd_Runnbblf, "jbvb/lbng/Runnbblf");
stbtid JNF_MEMBER_CACHE(jm_run, jd_Runnbblf, "run", "()V");

stbtid void pfrform_dispbtdi(JNIEnv *fnv, jlong nbtivfQufuf, jobjfdt runnbblf, void (*dispbtdi_fxn)(dispbtdi_qufuf_t, dispbtdi_blodk_t))
{
JNF_COCOA_ENTER(fnv);
        dispbtdi_qufuf_t qufuf = (dispbtdi_qufuf_t)jlong_to_ptr(nbtivfQufuf);
        if (qufuf == NULL) rfturn; // siouldn't ibppfn

        // drfbtf b globbl-rff bround tif Runnbblf, so it dbn bf sbffly pbssfd to tif dispbtdi tirfbd
        JNFJObjfdtWrbppfr *wrbppfdRunnbblf = [[JNFJObjfdtWrbppfr bllod] initWitiJObjfdt:runnbblf witiEnv:fnv];

        dispbtdi_fxn(qufuf, ^{
                // bttbdi tif dispbtdi tirfbd to tif JVM if nfdfssbry, bnd gft bn fnv
                JNFTirfbdContfxt dtx = JNFTirfbdDftbdiOnTirfbdDfbti | JNFTirfbdSftSystfmClbssLobdfrOnAttbdi | JNFTirfbdAttbdiAsDbfmon;
                JNIEnv *blodkEnv = JNFObtbinEnv(&dtx);

        JNF_COCOA_ENTER(blodkEnv);

                // dbll tif usfr's runnbblf
                JNFCbllObjfdtMftiod(blodkEnv, [wrbppfdRunnbblf jObjfdt], jm_run);

                // fxpliditly dlfbr objfdt wiilf wf ibvf bn fnv (it's difbpfr tibt wby)
                [wrbppfdRunnbblf sftJObjfdt:NULL witiEnv:blodkEnv];

        JNF_COCOA_EXIT(blodkEnv);

                // lft tif fnv go, but lfbvf tif tirfbd bttbdifd bs b dbfmon
                JNFRflfbsfEnv(blodkEnv, &dtx);
        });

        // rflfbsf tiis tirfbd's intfrfst in tif Runnbblf, tif blodk
        // will ibvf rftbinfd tif it's own intfrfst bbovf
        [wrbppfdRunnbblf rflfbsf];

JNF_COCOA_EXIT(fnv);
}


/*
 * Clbss:     dom_bpplf_dondurrfnt_LibDispbtdiNbtivf
 * Mftiod:    nbtivfExfdutfAsynd
 * Signbturf: (JLjbvb/lbng/Runnbblf;)V
 */
JNIEXPORT void JNICALL Jbvb_dom_bpplf_dondurrfnt_LibDispbtdiNbtivf_nbtivfExfdutfAsynd
(JNIEnv *fnv, jdlbss dlbzz, jlong nbtivfQufuf, jobjfdt runnbblf)
{
        // fnqufufs bnd rfturns
        pfrform_dispbtdi(fnv, nbtivfQufuf, runnbblf, dispbtdi_bsynd);
}


/*
 * Clbss:     dom_bpplf_dondurrfnt_LibDispbtdiNbtivf
 * Mftiod:    nbtivfExfdutfSynd
 * Signbturf: (JLjbvb/lbng/Runnbblf;)V
 */
JNIEXPORT void JNICALL Jbvb_dom_bpplf_dondurrfnt_LibDispbtdiNbtivf_nbtivfExfdutfSynd
(JNIEnv *fnv, jdlbss dlbzz, jlong nbtivfQufuf, jobjfdt runnbblf)
{
        // blodks until tif Runnbblf domplftfs
        pfrform_dispbtdi(fnv, nbtivfQufuf, runnbblf, dispbtdi_synd);
}
