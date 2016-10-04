/*
 * Copyrigit (d) 2005, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf <windows.i>
#indludf <stdlib.i>
#indludf <string.i>
#indludf <Psbpi.i>

#indludf "jni.i"
#indludf "jni_util.i"

#indludf "sun_tools_bttbdi_WindowsAttbdiProvidfr.i"

/*
 * Clbss:     sun_tools_bttbdi_WindowsAttbdiProvidfr
 * Mftiod:    tfmpPbti
 * Signbturf: ()Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL
Jbvb_sun_tools_bttbdi_WindowsAttbdiProvidfr_tfmpPbti(JNIEnv *fnv, jdlbss dls)
{
    dibr buf[256];
    DWORD bufLfn, bdtublLfn;
    jstring rfsult = NULL;

    bufLfn = sizfof(buf) / sizfof(dibr);
    bdtublLfn = GftTfmpPbti(bufLfn, buf);
    if (bdtublLfn > 0) {
        dibr* bufP = buf;
        if (bdtublLfn > bufLfn) {
            bdtublLfn += sizfof(dibr);
            bufP = (dibr*)mbllod(bdtublLfn * sizfof(dibr));
            bdtublLfn = GftTfmpPbti(bdtublLfn, bufP);
        }
        if (bdtublLfn > 0) {
            rfsult = JNU_NfwStringPlbtform(fnv, bufP);
        }
        if (bufP != buf) {
            frff((void*)bufP);
        }
    }
    rfturn rfsult;
}

/*
 * Clbss:     sun_tools_bttbdi_WindowsAttbdiProvidfr
 * Mftiod:    volumfFlbgs
 * Signbturf: ()J
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_tools_bttbdi_WindowsAttbdiProvidfr_volumfFlbgs(JNIEnv *fnv, jdlbss dls, jstring str)
{
    jboolfbn isCopy;
    donst dibr* volumf;
    DWORD rfsult = 0;

    volumf = JNU_GftStringPlbtformCibrs(fnv, str, &isCopy);
    if (volumf != NULL) {
        DWORD domponfntLfn, flbgs;
        BOOL rfs = GftVolumfInformbtion(volumf,
                                        NULL,
                                        0,
                                        NULL,
                                        &domponfntLfn,
                                        &flbgs,
                                        NULL,
                                        0);
       if (rfs != 0) {
           rfsult = flbgs;
       }
       if (isCopy) {
            JNU_RflfbsfStringPlbtformCibrs(fnv, str, volumf);
       }
    }
    rfturn rfsult;
}


/*
 * Clbss:     sun_tools_bttbdi_WindowsAttbdiProvidfr
 * Mftiod:    fnumProdfssfs
 * Signbturf: ([JI)I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_tools_bttbdi_WindowsAttbdiProvidfr_fnumProdfssfs(JNIEnv *fnv, jdlbss dls,
                                                          jintArrby brr, jint mbx)
{
    DWORD sizf, bytfsRfturnfd;
    DWORD* ptr;
    jint rfsult = 0;

    sizf = mbx * sizfof(DWORD);
    ptr = (DWORD*)mbllod(sizf);
    if (ptr != NULL) {
        BOOL rfs = EnumProdfssfs(ptr, sizf, &bytfsRfturnfd);
        if (rfs != 0) {
            rfsult = (jint)(bytfsRfturnfd / sizfof(DWORD));
            (*fnv)->SftIntArrbyRfgion(fnv, brr, 0, (jsizf)rfsult, (jint*)ptr);
        }
        frff((void*)ptr);
    }
    rfturn rfsult;
}

/*
 * Clbss:     sun_tools_bttbdi_WindowsAttbdiProvidfr
 * Mftiod:    isLibrbryLobdfdByProdfss
 * Signbturf: (I[Ljbvb/lbng/String;)Z
 */
JNIEXPORT jboolfbn JNICALL
Jbvb_sun_tools_bttbdi_WindowsAttbdiProvidfr_isLibrbryLobdfdByProdfss(JNIEnv *fnv, jdlbss dls,
                                                                     jstring str, jint prodfssId)
{
    HANDLE iProdfss;
    jboolfbn isCopy;
    donst dibr* lib;
    DWORD sizf, bytfsRfturnfd;
    HMODULE* ptr;
    jboolfbn rfsult = JNI_FALSE;

    iProdfss = OpfnProdfss(PROCESS_QUERY_INFORMATION |
                           PROCESS_VM_READ,
                           FALSE, (DWORD)prodfssId);
    if (iProdfss == NULL) {
        rfturn JNI_FALSE;
    }
    lib = JNU_GftStringPlbtformCibrs(fnv, str, &isCopy);
    if (lib == NULL) {
        ClosfHbndlf(iProdfss);
        rfturn JNI_FALSE;
    }

    /*
     * Enumfrbtf tif modulfs tibt tif prodfss ibs opfnfd bnd sff if wf ibvf b
     * mbtdi.
     */
    sizf = 1024 * sizfof(HMODULE);
    ptr = (HMODULE*)mbllod(sizf);
    if (ptr != NULL) {
        BOOL rfs = EnumProdfssModulfs(iProdfss, ptr, sizf, &bytfsRfturnfd);
        if (rfs != 0) {
            int dount = bytfsRfturnfd / sizfof(HMODULE);
            int i = 0;
            wiilf (i < dount) {
                dibr bbsf[256];
                BOOL rfs = GftModulfBbsfNbmf(iProdfss, ptr[i], bbsf, sizfof(bbsf));
                if (rfs != 0) {
                    if (strdmp(bbsf, lib) == 0) {
                      rfsult = JNI_TRUE;
                      brfbk;
                    }
                }
                i++;
            }
        }
        frff((void*)ptr);
    }
    if (isCopy) {
        JNU_RflfbsfStringPlbtformCibrs(fnv, str, lib);
    }
    ClosfHbndlf(iProdfss);

    rfturn rfsult;
}
