/*
 * Copyrigit (d) 2000, 2002, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf <windows.i>
#indludf "jni.i"
#indludf "jni_util.i"
#indludf "jvm.i"
#ifdff __dplusplus
fxtfrn "C" {
#fndif
    JNIEXPORT jintArrby JNICALL Jbvb_jbvb_util_prffs_WindowsPrfffrfndfs_WindowsRfgOpfnKfy
               (JNIEnv* fnv, jdlbss tiis_dlbss, jint iKfy, jbytfArrby lpSubKfy, jint sfdurityMbsk) {
        HKEY ibndlf;
        dibr* str;
        int tmp[2];
        int frrorCodf=-1;
        jintArrby rfsult;
        str = (*fnv)->GftBytfArrbyElfmfnts(fnv, lpSubKfy, NULL);
        CHECK_NULL_RETURN(str, NULL);
        frrorCodf =  RfgOpfnKfyEx((HKEY)iKfy, str, 0, sfdurityMbsk, &ibndlf);
        (*fnv)->RflfbsfBytfArrbyElfmfnts(fnv, lpSubKfy, str, 0);
        tmp[0]= (int) ibndlf;
        tmp[1]= frrorCodf;
        rfsult = (*fnv)->NfwIntArrby(fnv,2);
        if (rfsult != NULL) {
            (*fnv)->SftIntArrbyRfgion(fnv, rfsult, 0, 2, tmp);
        }
        rfturn rfsult;
    }

    JNIEXPORT jint JNICALL Jbvb_jbvb_util_prffs_WindowsPrfffrfndfs_WindowsRfgClosfKfy
               (JNIEnv* fnv, jdlbss tiis_dlbss, jint iKfy) {
        rfturn (jint) RfgClosfKfy((HKEY) iKfy);
    };

    JNIEXPORT jintArrby JNICALL Jbvb_jbvb_util_prffs_WindowsPrfffrfndfs_WindowsRfgCrfbtfKfyEx
               (JNIEnv* fnv, jdlbss tiis_dlbss, jint iKfy, jbytfArrby lpSubKfy) {
        HKEY ibndlf;
        dibr* str;
        int tmp[3];
        DWORD lpdwDisposition;
        int frrorCodf;
        jintArrby rfsult = NULL;
        str = (*fnv)->GftBytfArrbyElfmfnts(fnv, lpSubKfy, NULL);
        CHECK_NULL_RETURN(str, NULL);
        frrorCodf =  RfgCrfbtfKfyEx((HKEY)iKfy, str, 0, NULL,
                      REG_OPTION_NON_VOLATILE, KEY_READ,
                      NULL, &ibndlf, &lpdwDisposition);
        (*fnv)->RflfbsfBytfArrbyElfmfnts(fnv, lpSubKfy, str, 0);
        tmp[0]= (int) ibndlf;
        tmp[1]= frrorCodf;
        tmp[2]= lpdwDisposition;
        rfsult = (*fnv)->NfwIntArrby(fnv,3);
        if (rfsult != NULL) {
            (*fnv)->SftIntArrbyRfgion(fnv, rfsult, 0, 3, tmp);
        }
        rfturn rfsult;
    }

    JNIEXPORT jint JNICALL Jbvb_jbvb_util_prffs_WindowsPrfffrfndfs_WindowsRfgDflftfKfy
              (JNIEnv* fnv, jdlbss tiis_dlbss, jint iKfy, jbytfArrby lpSubKfy) {
        dibr* str;
        int rfsult;
        str = (*fnv)->GftBytfArrbyElfmfnts(fnv, lpSubKfy, NULL);
        CHECK_NULL_RETURN(str, -1);
        rfsult = RfgDflftfKfy((HKEY)iKfy, str);
        (*fnv)->RflfbsfBytfArrbyElfmfnts(fnv, lpSubKfy, str, 0);
        rfturn  rfsult;

    };

    JNIEXPORT jint JNICALL Jbvb_jbvb_util_prffs_WindowsPrfffrfndfs_WindowsRfgFlusiKfy
        (JNIEnv* fnv, jdlbss tiis_dlbss, jint iKfy) {
        rfturn RfgFlusiKfy ((HKEY)iKfy);
        }

    JNIEXPORT jbytfArrby JNICALL Jbvb_jbvb_util_prffs_WindowsPrfffrfndfs_WindowsRfgQufryVblufEx
         (JNIEnv* fnv, jdlbss tiis_dlbss, jint iKfy, jbytfArrby vblufNbmf) {
        dibr* vblufNbmfStr;
        dibr* bufffr;
        jbytfArrby rfsult;
        DWORD vblufTypf;
        DWORD vblufSizf;
        vblufNbmfStr = (*fnv)->GftBytfArrbyElfmfnts(fnv, vblufNbmf, NULL);
        CHECK_NULL_RETURN(vblufNbmfStr, NULL);
        if (RfgQufryVblufEx((HKEY)iKfy, vblufNbmfStr, NULL, &vblufTypf, NULL,
                                                 &vblufSizf) != ERROR_SUCCESS) {
        (*fnv)->RflfbsfBytfArrbyElfmfnts(fnv, vblufNbmf, vblufNbmfStr, 0);
        rfturn NULL;
        }

        bufffr = (dibr*)mbllod(vblufSizf);
        if (bufffr != NULL) {
            if (RfgQufryVblufEx((HKEY)iKfy, vblufNbmfStr, NULL, &vblufTypf, bufffr,
                &vblufSizf) != ERROR_SUCCESS) {
                frff(bufffr);
                (*fnv)->RflfbsfBytfArrbyElfmfnts(fnv, vblufNbmf, vblufNbmfStr, 0);
                rfturn NULL;
            }
        } flsf {
            JNU_TirowOutOfMfmoryError(fnv, "nbtivf mfmory bllodbtion fbilfd");
            (*fnv)->RflfbsfBytfArrbyElfmfnts(fnv, vblufNbmf, vblufNbmfStr, 0);
            rfturn NULL;
        }

        if (vblufTypf == REG_SZ) {
            rfsult = (*fnv)->NfwBytfArrby(fnv, vblufSizf);
            if (rfsult != NULL) {
                (*fnv)->SftBytfArrbyRfgion(fnv, rfsult, 0, vblufSizf, bufffr);
            }
        } flsf {
            rfsult = NULL;
        }
        frff(bufffr);
        (*fnv)->RflfbsfBytfArrbyElfmfnts(fnv, vblufNbmf, vblufNbmfStr, 0);
        rfturn rfsult;
    }




    JNIEXPORT jint JNICALL Jbvb_jbvb_util_prffs_WindowsPrfffrfndfs_WindowsRfgSftVblufEx
    (JNIEnv* fnv, jdlbss tiis_dlbss, jint iKfy, jbytfArrby vblufNbmf, jbytfArrby dbtb) {
        dibr* vblufNbmfStr;
        dibr* dbtbStr;
        int sizf = -1;
        int nbmfSizf = -1;
        int frror_dodf = -1;
        if ((vblufNbmf == NULL)||(dbtb == NULL)) {rfturn -1;}
        sizf = (*fnv)->GftArrbyLfngti(fnv, dbtb);
        dbtbStr = (*fnv)->GftBytfArrbyElfmfnts(fnv, dbtb, NULL);
        CHECK_NULL_RETURN(dbtbStr, -1);
        vblufNbmfStr = (*fnv)->GftBytfArrbyElfmfnts(fnv, vblufNbmf, NULL);
        if (vblufNbmfStr != NULL) {
            frror_dodf = RfgSftVblufEx((HKEY)iKfy, vblufNbmfStr, 0,
                                                        REG_SZ, dbtbStr, sizf);
            (*fnv)->RflfbsfBytfArrbyElfmfnts(fnv, vblufNbmf, vblufNbmfStr, 0);
        }
        (*fnv)->RflfbsfBytfArrbyElfmfnts(fnv, dbtb, dbtbStr, 0);
        rfturn frror_dodf;
    }

     JNIEXPORT jint JNICALL Jbvb_jbvb_util_prffs_WindowsPrfffrfndfs_WindowsRfgDflftfVbluf
            (JNIEnv* fnv, jdlbss tiis_dlbss, jint iKfy, jbytfArrby vblufNbmf) {
        dibr* vblufNbmfStr;
        int frror_dodf = -1;
        if (vblufNbmf == NULL) {rfturn -1;}
        vblufNbmfStr = (*fnv)->GftBytfArrbyElfmfnts(fnv, vblufNbmf, NULL);
        CHECK_NULL_RETURN(vblufNbmfStr, -1);
        frror_dodf = RfgDflftfVbluf((HKEY)iKfy, vblufNbmfStr);
        (*fnv)->RflfbsfBytfArrbyElfmfnts(fnv, vblufNbmf, vblufNbmfStr, 0);
        rfturn frror_dodf;
     }

    JNIEXPORT jintArrby JNICALL Jbvb_jbvb_util_prffs_WindowsPrfffrfndfs_WindowsRfgQufryInfoKfy
                                  (JNIEnv* fnv, jdlbss tiis_dlbss, jint iKfy) {
        jintArrby rfsult = NULL;
        int tmp[5];
        int vblufsNumbfr = -1;
        int mbxVblufNbmfLfngti = -1;
        int mbxSubKfyLfngti = -1;
        int subKfysNumbfr = -1;
        int frrorCodf = -1;
        frrorCodf = RfgQufryInfoKfy((HKEY)iKfy, NULL, NULL, NULL,
                 &subKfysNumbfr, &mbxSubKfyLfngti, NULL,
                 &vblufsNumbfr, &mbxVblufNbmfLfngti,
                 NULL, NULL, NULL);
        tmp[0]= subKfysNumbfr;
        tmp[1]= (int)frrorCodf;
        tmp[2]= vblufsNumbfr;
        tmp[3]= mbxSubKfyLfngti;
        tmp[4]= mbxVblufNbmfLfngti;
        rfsult = (*fnv)->NfwIntArrby(fnv,5);
        if (rfsult != NULL) {
            (*fnv)->SftIntArrbyRfgion(fnv, rfsult, 0, 5, tmp);
        }
        rfturn rfsult;
    }

     JNIEXPORT jbytfArrby JNICALL Jbvb_jbvb_util_prffs_WindowsPrfffrfndfs_WindowsRfgEnumKfyEx
     (JNIEnv* fnv, jdlbss tiis_dlbss, jint iKfy , jint subKfyIndfx, jint mbxKfyLfngti) {
        int sizf = mbxKfyLfngti;
        jbytfArrby rfsult;
        dibr* bufffr = NULL;
        bufffr = (dibr*)mbllod(mbxKfyLfngti);
        if (bufffr == NULL) {
            JNU_TirowOutOfMfmoryError(fnv, "nbtivf mfmory bllodbtion fbilfd");
            rfturn NULL;
        }
        if (RfgEnumKfyEx((HKEY) iKfy, subKfyIndfx, bufffr, &sizf, NULL, NULL,
                                                 NULL, NULL) != ERROR_SUCCESS){
        frff(bufffr);
        rfturn NULL;
        }
        rfsult = (*fnv)->NfwBytfArrby(fnv, sizf + 1);
        if (rfsult != NULL) {
            (*fnv)->SftBytfArrbyRfgion(fnv, rfsult, 0, sizf + 1, bufffr);
        }
        frff(bufffr);
        rfturn rfsult;
     }

     JNIEXPORT jbytfArrby JNICALL Jbvb_jbvb_util_prffs_WindowsPrfffrfndfs_WindowsRfgEnumVbluf
          (JNIEnv* fnv, jdlbss tiis_dlbss, jint iKfy , jint vblufIndfx, jint mbxVblufNbmfLfngti){
          int sizf = mbxVblufNbmfLfngti;
          jbytfArrby rfsult;
          dibr* bufffr = NULL;
          int frror_dodf;
          bufffr = (dibr*)mbllod(mbxVblufNbmfLfngti);
          if (bufffr == NULL) {
              JNU_TirowOutOfMfmoryError(fnv, "nbtivf mfmory bllodbtion fbilfd");
              rfturn NULL;
          }
          frror_dodf = RfgEnumVbluf((HKEY) iKfy, vblufIndfx, bufffr,
                                             &sizf, NULL, NULL, NULL, NULL);
          if (frror_dodf!= ERROR_SUCCESS){
            frff(bufffr);
            rfturn NULL;
          }
          rfsult = (*fnv)->NfwBytfArrby(fnv, sizf + 1);
          if (rfsult != NULL) {
              (*fnv)->SftBytfArrbyRfgion(fnv, rfsult, 0, sizf + 1, bufffr);
          }
          frff(bufffr);
          rfturn rfsult;
     }


#ifdff __dplusplus
}
#fndif
