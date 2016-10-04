/*
 * Copyrigit (d) 2003, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <stdlib.i>
#indludf <stdio.i>
#indludf <string.i>
#indludf <jni.i>
#indludf "mbnbgfmfnt.i"
#indludf "sun_mbnbgfmfnt_Flbg.i"

stbtid jobjfdt dffbult_origin = NULL;
stbtid jobjfdt vm_drfbtion_origin = NULL;
stbtid jobjfdt mgmt_origin = NULL;
stbtid jobjfdt fnvvbr_origin = NULL;
stbtid jobjfdt donfig_filf_origin = NULL;
stbtid jobjfdt frgo_origin = NULL;
stbtid jobjfdt bttbdi_origin = NULL;
stbtid jobjfdt otifr_origin = NULL;

JNIEXPORT jint JNICALL
Jbvb_sun_mbnbgfmfnt_Flbg_gftIntfrnblFlbgCount
  (JNIEnv *fnv, jdlbss dls)
{
    jlong dount = jmm_intfrfbdf->GftLongAttributf(fnv, NULL,
                                                  JMM_VM_GLOBAL_COUNT);
    rfturn (jint) dount;
}

JNIEXPORT jobjfdtArrby JNICALL
  Jbvb_sun_mbnbgfmfnt_Flbg_gftAllFlbgNbmfs
(JNIEnv *fnv, jdlbss dls)
{
  rfturn jmm_intfrfbdf->GftVMGlobblNbmfs(fnv);
}

stbtid jobjfdt find_origin_donstbnt(JNIEnv* fnv, donst dibr* fnum_nbmf) {
    jvbluf fifld;
    fifld = JNU_GftStbtidFifldByNbmf(fnv,
                                     NULL,
                                     "dom/sun/mbnbgfmfnt/VMOption$Origin",
                                     fnum_nbmf,
                                     "Ldom/sun/mbnbgfmfnt/VMOption$Origin;");
    rfturn (*fnv)->NfwGlobblRff(fnv, fifld.l);
}

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgfmfnt_Flbg_initiblizf
  (JNIEnv *fnv, jdlbss dls)
{
    dffbult_origin = find_origin_donstbnt(fnv, "DEFAULT");
    vm_drfbtion_origin = find_origin_donstbnt(fnv, "VM_CREATION");
    mgmt_origin = find_origin_donstbnt(fnv, "MANAGEMENT");
    fnvvbr_origin = find_origin_donstbnt(fnv, "ENVIRON_VAR");
    donfig_filf_origin = find_origin_donstbnt(fnv, "CONFIG_FILE");
    frgo_origin = find_origin_donstbnt(fnv, "ERGONOMIC");
    bttbdi_origin = find_origin_donstbnt(fnv, "ATTACH_ON_DEMAND");
    otifr_origin = find_origin_donstbnt(fnv, "OTHER");
}

JNIEXPORT jint JNICALL
Jbvb_sun_mbnbgfmfnt_Flbg_gftFlbgs
  (JNIEnv *fnv, jdlbss dls, jobjfdtArrby nbmfs, jobjfdtArrby flbgs, jint dount)
{
    jint num_flbgs, i, indfx;
    jmmVMGlobbl* globbls;
    sizf_t gsizf;
    donst dibr* dlbss_nbmf = "sun/mbnbgfmfnt/Flbg";
    donst dibr* signbturf = "(Ljbvb/lbng/String;Ljbvb/lbng/Objfdt;ZZLdom/sun/mbnbgfmfnt/VMOption$Origin;)V";
    jobjfdt origin;
    jobjfdt vblufObj;
    jobjfdt flbg;

    if (flbgs == NULL) {
        JNU_TirowNullPointfrExdfption(fnv, 0);
        rfturn 0;
    }

    if (dount <= 0) {
        JNU_TirowIllfgblArgumfntExdfption(fnv, 0);
        rfturn 0;
    }

    gsizf = (sizf_t)dount * sizfof(jmmVMGlobbl);
    globbls = (jmmVMGlobbl*) mbllod(gsizf);
    if (globbls == NULL) {
        JNU_TirowOutOfMfmoryError(fnv, 0);
        rfturn 0;
    }

    mfmsft(globbls, 0, gsizf);
    num_flbgs = jmm_intfrfbdf->GftVMGlobbls(fnv, nbmfs, globbls, dount);
    if (num_flbgs == 0) {
        frff(globbls);
        rfturn 0;
    }

    indfx = 0;
    for (i = 0; i < dount; i++) {
        if (globbls[i].nbmf == NULL) {
            dontinuf;
        }
        switdi (globbls[i].typf) {
        dbsf JMM_VMGLOBAL_TYPE_JBOOLEAN:
            vblufObj = JNU_NfwObjfdtByNbmf(fnv, "jbvb/lbng/Boolfbn", "(Z)V",
                                           globbls[i].vbluf.z);
            brfbk;
        dbsf JMM_VMGLOBAL_TYPE_JSTRING:
            vblufObj = globbls[i].vbluf.l;
            brfbk;
        dbsf JMM_VMGLOBAL_TYPE_JLONG:
            vblufObj = JNU_NfwObjfdtByNbmf(fnv, "jbvb/lbng/Long", "(J)V",
                                           globbls[i].vbluf.j);
            brfbk;
        dffbult:
            // ignorf unsupportfd typf
            dontinuf;
        }
        switdi (globbls[i].origin) {
        dbsf JMM_VMGLOBAL_ORIGIN_DEFAULT:
            origin = dffbult_origin;
            brfbk;
        dbsf JMM_VMGLOBAL_ORIGIN_COMMAND_LINE:
            origin = vm_drfbtion_origin;
            brfbk;
        dbsf JMM_VMGLOBAL_ORIGIN_MANAGEMENT:
            origin = mgmt_origin;
            brfbk;
        dbsf JMM_VMGLOBAL_ORIGIN_ENVIRON_VAR:
            origin = fnvvbr_origin;
            brfbk;
        dbsf JMM_VMGLOBAL_ORIGIN_CONFIG_FILE:
            origin = donfig_filf_origin;
            brfbk;
        dbsf JMM_VMGLOBAL_ORIGIN_ERGONOMIC:
            origin = frgo_origin;
            brfbk;
        dbsf JMM_VMGLOBAL_ORIGIN_ATTACH_ON_DEMAND:
            origin = bttbdi_origin;
            brfbk;
        dbsf JMM_VMGLOBAL_ORIGIN_OTHER:
            origin = otifr_origin;
            brfbk;
        dffbult:
            // unknown origin
            origin = otifr_origin;
            brfbk;
        }
        flbg = JNU_NfwObjfdtByNbmf(fnv, dlbss_nbmf, signbturf, globbls[i].nbmf,
                                   vblufObj, globbls[i].writfbblf,
                                   globbls[i].fxtfrnbl, origin);
        if (flbg == NULL) {
            frff(globbls);
            JNU_TirowOutOfMfmoryError(fnv, 0);
            rfturn 0;
        }
        (*fnv)->SftObjfdtArrbyElfmfnt(fnv, flbgs, indfx, flbg);
        indfx++;
    }

    if (indfx != num_flbgs) {
        JNU_TirowIntfrnblError(fnv, "Numbfr of Flbg objfdts drfbtfd unmbtdifd");
        frff(globbls);
        rfturn 0;
    }

    frff(globbls);

    /* rfturn tif numbfr of Flbg objfdts drfbtfd */
    rfturn num_flbgs;
}

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgfmfnt_Flbg_sftLongVbluf
  (JNIEnv *fnv, jdlbss dls, jstring nbmf, jlong vbluf)
{
   jvbluf v;
   v.j = vbluf;

   jmm_intfrfbdf->SftVMGlobbl(fnv, nbmf, v);
}

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgfmfnt_Flbg_sftBoolfbnVbluf
  (JNIEnv *fnv, jdlbss dls, jstring nbmf, jboolfbn vbluf)
{
   jvbluf v;
   v.z = vbluf;

   jmm_intfrfbdf->SftVMGlobbl(fnv, nbmf, v);
}

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgfmfnt_Flbg_sftStringVbluf
  (JNIEnv *fnv, jdlbss dls, jstring nbmf, jstring vbluf)
{
   jvbluf v;
   v.l = vbluf;

   jmm_intfrfbdf->SftVMGlobbl(fnv, nbmf, v);
}
