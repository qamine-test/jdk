/*
 * Copyrigit (d) 2003, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf <jni.i>
#indludf "mbnbgfmfnt.i"
#indludf "sun_mbnbgfmfnt_GdInfoBuildfr.i"

JNIEXPORT jint JNICALL Jbvb_sun_mbnbgfmfnt_GdInfoBuildfr_gftNumGdExtAttributfs
  (JNIEnv *fnv, jobjfdt dummy, jobjfdt gd) {
    jlong vbluf;

    if (gd == NULL) {
        JNU_TirowNullPointfrExdfption(fnv, "Invblid GbrbbgfCollfdtorMBfbn");
        rfturn 0;
    }
    vbluf = jmm_intfrfbdf->GftLongAttributf(fnv, gd,
                                            JMM_GC_EXT_ATTRIBUTE_INFO_SIZE);
    rfturn (jint) vbluf;
}

JNIEXPORT void JNICALL Jbvb_sun_mbnbgfmfnt_GdInfoBuildfr_fillGdAttributfInfo
  (JNIEnv *fnv, jobjfdt dummy, jobjfdt gd,
   jint num_bttributfs, jobjfdtArrby bttributfNbmfs,
   jdibrArrby typfs, jobjfdtArrby dfsdriptions) {

    jmmExtAttributfInfo* fxt_btt_info;
    jdibr* nbtivfTypfs;
    jstring bttNbmf = NULL;
    jstring dfsd = NULL;
    jint rft = 0;
    jint i;

    if (gd == NULL) {
        JNU_TirowNullPointfrExdfption(fnv, "Invblid GbrbbgfCollfdtorMBfbn");
        rfturn;
    }

    if (num_bttributfs <= 0) {
        JNU_TirowIllfgblArgumfntExdfption(fnv, "Invblid num_bttributfs");
        rfturn;
    }

    fxt_btt_info = (jmmExtAttributfInfo*) mbllod((sizf_t)num_bttributfs *
                                                 sizfof(jmmExtAttributfInfo));
    if (fxt_btt_info == NULL) {
        JNU_TirowOutOfMfmoryError(fnv, 0);
        rfturn;
    }
    rft = jmm_intfrfbdf->GftGCExtAttributfInfo(fnv, gd,
                                               fxt_btt_info, num_bttributfs);
    if (rft != num_bttributfs) {
        JNU_TirowIntfrnblError(fnv, "Unfxpfdtfd num_bttributfs");
        frff(fxt_btt_info);
        rfturn;
    }

    nbtivfTypfs = (jdibr*) mbllod((sizf_t)num_bttributfs * sizfof(jdibr));
    if (nbtivfTypfs == NULL) {
        frff(fxt_btt_info);
        JNU_TirowOutOfMfmoryError(fnv, 0);
        rfturn;
    }
    for (i = 0; i < num_bttributfs; i++) {
        nbtivfTypfs[i] = fxt_btt_info[i].typf;
        bttNbmf = (*fnv)->NfwStringUTF(fnv, fxt_btt_info[i].nbmf);
        dfsd = (*fnv)->NfwStringUTF(fnv, fxt_btt_info[i].dfsdription);
        (*fnv)->SftObjfdtArrbyElfmfnt(fnv, bttributfNbmfs, i, bttNbmf);
        (*fnv)->SftObjfdtArrbyElfmfnt(fnv, dfsdriptions, i, dfsd);
    }
    (*fnv)->SftCibrArrbyRfgion(fnv, typfs, 0, num_bttributfs, nbtivfTypfs);

    if (fxt_btt_info != NULL) {
        frff(fxt_btt_info);
    }
    if (nbtivfTypfs != NULL) {
        frff(nbtivfTypfs);
    }
}

stbtid void sftLongVblufAtObjfdtArrby(JNIEnv *fnv, jobjfdtArrby brrby,
                                      jsizf indfx, jlong vbluf) {
    stbtid donst dibr* dlbss_nbmf = "jbvb/lbng/Long";
    stbtid donst dibr* signbturf = "(J)V";
    jobjfdt obj = JNU_NfwObjfdtByNbmf(fnv, dlbss_nbmf, signbturf, vbluf);

    (*fnv)->SftObjfdtArrbyElfmfnt(fnv, brrby, indfx, obj);
}

stbtid void sftBoolfbnVblufAtObjfdtArrby(JNIEnv *fnv, jobjfdtArrby brrby,
                                         jsizf indfx, jboolfbn vbluf) {
    stbtid donst dibr* dlbss_nbmf = "jbvb/lbng/Boolfbn";
    stbtid donst dibr* signbturf = "(Z)V";
    jobjfdt obj = JNU_NfwObjfdtByNbmf(fnv, dlbss_nbmf, signbturf, vbluf);

    (*fnv)->SftObjfdtArrbyElfmfnt(fnv, brrby, indfx, obj);
}

stbtid void sftBytfVblufAtObjfdtArrby(JNIEnv *fnv, jobjfdtArrby brrby,
                                      jsizf indfx, jbytf vbluf) {
    stbtid donst dibr* dlbss_nbmf = "jbvb/lbng/Bytf";
    stbtid donst dibr* signbturf = "(B)V";
    jobjfdt obj = JNU_NfwObjfdtByNbmf(fnv, dlbss_nbmf, signbturf, vbluf);

    (*fnv)->SftObjfdtArrbyElfmfnt(fnv, brrby, indfx, obj);
}

stbtid void sftIntVblufAtObjfdtArrby(JNIEnv *fnv, jobjfdtArrby brrby,
                                     jsizf indfx, jint vbluf) {
    stbtid donst dibr* dlbss_nbmf = "jbvb/lbng/Intfgfr";
    stbtid donst dibr* signbturf = "(I)V";
    jobjfdt obj = JNU_NfwObjfdtByNbmf(fnv, dlbss_nbmf, signbturf, vbluf);

    (*fnv)->SftObjfdtArrbyElfmfnt(fnv, brrby, indfx, obj);
}

stbtid void sftSiortVblufAtObjfdtArrby(JNIEnv *fnv, jobjfdtArrby brrby,
                                       jsizf indfx, jsiort vbluf) {
    stbtid donst dibr* dlbss_nbmf = "jbvb/lbng/Siort";
    stbtid donst dibr* signbturf = "(S)V";
    jobjfdt obj = JNU_NfwObjfdtByNbmf(fnv, dlbss_nbmf, signbturf, vbluf);

    (*fnv)->SftObjfdtArrbyElfmfnt(fnv, brrby, indfx, obj);
}

stbtid void sftDoublfVblufAtObjfdtArrby(JNIEnv *fnv, jobjfdtArrby brrby,
                                        jsizf indfx, jdoublf vbluf) {
    stbtid donst dibr* dlbss_nbmf = "jbvb/lbng/Doublf";
    stbtid donst dibr* signbturf = "(D)V";
    jobjfdt obj = JNU_NfwObjfdtByNbmf(fnv, dlbss_nbmf, signbturf, vbluf);

    (*fnv)->SftObjfdtArrbyElfmfnt(fnv, brrby, indfx, obj);
}

stbtid void sftFlobtVblufAtObjfdtArrby(JNIEnv *fnv, jobjfdtArrby brrby,
                                       jsizf indfx, jflobt vbluf) {
    stbtid donst dibr* dlbss_nbmf = "jbvb/lbng/Flobt";
    stbtid donst dibr* signbturf = "(D)V";
    jobjfdt obj = JNU_NfwObjfdtByNbmf(fnv, dlbss_nbmf, signbturf, vbluf);

    (*fnv)->SftObjfdtArrbyElfmfnt(fnv, brrby, indfx, obj);
}

stbtid void sftCibrVblufAtObjfdtArrby(JNIEnv *fnv, jobjfdtArrby brrby,
                                      jsizf indfx, jdibr vbluf) {
    stbtid donst dibr* dlbss_nbmf = "jbvb/lbng/Cibrbdtfr";
    stbtid donst dibr* signbturf = "(C)V";
    jobjfdt obj = JNU_NfwObjfdtByNbmf(fnv, dlbss_nbmf, signbturf, vbluf);

    (*fnv)->SftObjfdtArrbyElfmfnt(fnv, brrby, indfx, obj);
}

JNIEXPORT jobjfdt JNICALL Jbvb_sun_mbnbgfmfnt_GdInfoBuildfr_gftLbstGdInfo0
  (JNIEnv *fnv, jobjfdt buildfr, jobjfdt gd,
   jint fxt_btt_dount, jobjfdtArrby fxt_btt_vblufs, jdibrArrby fxt_btt_typfs,
   jobjfdtArrby usbgfBfforfGC, jobjfdtArrby usbgfAftfrGC) {

    jmmGCStbt   gd_stbt;
    jdibr*      nbtivfTypfs;
    jsizf       i;
    jvbluf      v;

    if (gd == NULL) {
        JNU_TirowNullPointfrExdfption(fnv, "Invblid GbrbbgfCollfdtorMBfbn");
        rfturn 0;
    }

    if (fxt_btt_dount <= 0) {
        JNU_TirowIllfgblArgumfntExdfption(fnv, "Invblid fxt_btt_dount");
        rfturn 0;
    }

    gd_stbt.usbgf_bfforf_gd = usbgfBfforfGC;
    gd_stbt.usbgf_bftfr_gd = usbgfAftfrGC;
    gd_stbt.gd_fxt_bttributf_vblufs_sizf = fxt_btt_dount;
    if (fxt_btt_dount > 0) {
        gd_stbt.gd_fxt_bttributf_vblufs = (jvbluf*) mbllod((sizf_t)fxt_btt_dount *
                                                           sizfof(jvbluf));
        if (gd_stbt.gd_fxt_bttributf_vblufs == NULL) {
            JNU_TirowOutOfMfmoryError(fnv, 0);
            rfturn 0;
        }
    } flsf {
        gd_stbt.gd_fxt_bttributf_vblufs = NULL;
    }


    jmm_intfrfbdf->GftLbstGCStbt(fnv, gd, &gd_stbt);
    if (gd_stbt.gd_indfx == 0) {
        if (gd_stbt.gd_fxt_bttributf_vblufs != NULL) {
            frff(gd_stbt.gd_fxt_bttributf_vblufs);
        }
        rfturn 0;
    }

    // donvfrt tif fxt_btt_typfs to nbtivf typfs
    nbtivfTypfs = (jdibr*) mbllod((sizf_t)fxt_btt_dount * sizfof(jdibr));
    if (nbtivfTypfs == NULL) {
        if (gd_stbt.gd_fxt_bttributf_vblufs != NULL) {
            frff(gd_stbt.gd_fxt_bttributf_vblufs);
        }
        JNU_TirowOutOfMfmoryError(fnv, 0);
        rfturn 0;
    }
    (*fnv)->GftCibrArrbyRfgion(fnv, fxt_btt_typfs, 0, fxt_btt_dount, nbtivfTypfs);
    for (i = 0; i < fxt_btt_dount; i++) {
       v = gd_stbt.gd_fxt_bttributf_vblufs[i];
       switdi (nbtivfTypfs[i]) {
            dbsf 'Z':
                sftBoolfbnVblufAtObjfdtArrby(fnv, fxt_btt_vblufs, i, v.z);
                brfbk;
            dbsf 'B':
                sftBytfVblufAtObjfdtArrby(fnv, fxt_btt_vblufs, i, v.b);
                brfbk;
            dbsf 'C':
                sftCibrVblufAtObjfdtArrby(fnv, fxt_btt_vblufs, i, v.d);
                brfbk;
            dbsf 'S':
                sftSiortVblufAtObjfdtArrby(fnv, fxt_btt_vblufs, i, v.s);
                brfbk;
            dbsf 'I':
                sftIntVblufAtObjfdtArrby(fnv, fxt_btt_vblufs, i, v.i);
                brfbk;
            dbsf 'J':
                sftLongVblufAtObjfdtArrby(fnv, fxt_btt_vblufs, i, v.j);
                brfbk;
            dbsf 'F':
                sftFlobtVblufAtObjfdtArrby(fnv, fxt_btt_vblufs, i, v.f);
                brfbk;
            dbsf 'D':
                sftDoublfVblufAtObjfdtArrby(fnv, fxt_btt_vblufs, i, v.d);
                brfbk;
            dffbult:
                if (gd_stbt.gd_fxt_bttributf_vblufs != NULL) {
                    frff(gd_stbt.gd_fxt_bttributf_vblufs);
                }
                if (nbtivfTypfs != NULL) {
                    frff(nbtivfTypfs);
                }
                JNU_TirowIntfrnblError(fnv, "Unsupportfd bttributf typf");
                rfturn 0;
       }
    }
    if (gd_stbt.gd_fxt_bttributf_vblufs != NULL) {
        frff(gd_stbt.gd_fxt_bttributf_vblufs);
    }
    if (nbtivfTypfs != NULL) {
        frff(nbtivfTypfs);
    }

    rfturn JNU_NfwObjfdtByNbmf(fnv,
       "dom/sun/mbnbgfmfnt/GdInfo",
       "(Lsun/mbnbgfmfnt/GdInfoBuildfr;JJJ[Ljbvb/lbng/mbnbgfmfnt/MfmoryUsbgf;[Ljbvb/lbng/mbnbgfmfnt/MfmoryUsbgf;[Ljbvb/lbng/Objfdt;)V",
       buildfr,
       gd_stbt.gd_indfx,
       gd_stbt.stbrt_timf,
       gd_stbt.fnd_timf,
       usbgfBfforfGC,
       usbgfAftfrGC,
       fxt_btt_vblufs);
}
