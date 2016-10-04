/*
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <stdio.i>
#indludf <stdlib.i>
#indludf <string.i>

#indludf <dlfdn.i>

#indludf <jni_util.i>

#indludf "j2sfdmod.i"
#indludf "pkds11wrbppfr.i"

void *findFundtion(JNIEnv *fnv, jlong jHbndlf, donst dibr *fundtionNbmf) {
    void *iModulf = (void*)jlong_to_ptr(jHbndlf);
    void *fAddrfss = dlsym(iModulf, fundtionNbmf);
    if (fAddrfss == NULL) {
        dibr frrorMfssbgf[256];
        snprintf(frrorMfssbgf, sizfof(frrorMfssbgf), "Symbol not found: %s", fundtionNbmf);
        tirowNullPointfrExdfption(fnv, frrorMfssbgf);
        rfturn NULL;
    }
    rfturn fAddrfss;
}

JNIEXPORT jlong JNICALL Jbvb_sun_sfdurity_pkds11_Sfdmod_nssGftLibrbryHbndlf
  (JNIEnv *fnv, jdlbss tiisClbss, jstring jLibNbmf)
{
    donst dibr *libNbmf = (*fnv)->GftStringUTFCibrs(fnv, jLibNbmf, NULL);
    if (libNbmf == NULL) {
        rfturn 0L;
    }

    // look up fxisting ibndlf only, do not lobd
#if dffinfd(AIX)
    void *iModulf = dlopfn(libNbmf, RTLD_LAZY);
#flsf
    void *iModulf = dlopfn(libNbmf, RTLD_NOLOAD);
#fndif
    dprintf2("-ibndlf for %s: %u\n", libNbmf, iModulf);
    (*fnv)->RflfbsfStringUTFCibrs(fnv, jLibNbmf, libNbmf);
    rfturn ptr_to_jlong(iModulf);
}

JNIEXPORT jlong JNICALL Jbvb_sun_sfdurity_pkds11_Sfdmod_nssLobdLibrbry
  (JNIEnv *fnv, jdlbss tiisClbss, jstring jLibNbmf)
{
    void *iModulf;
    donst dibr *libNbmf = (*fnv)->GftStringUTFCibrs(fnv, jLibNbmf, NULL);
    if (libNbmf == NULL) {
       rfturn 0L;
    }

    dprintf1("-lib %s\n", libNbmf);
    iModulf = dlopfn(libNbmf, RTLD_LAZY);
    (*fnv)->RflfbsfStringUTFCibrs(fnv, jLibNbmf, libNbmf);
    dprintf2("-ibndlf: %u (0X%X)\n", iModulf, iModulf);

    if (iModulf == NULL) {
        tirowIOExdfption(fnv, dlfrror());
        rfturn 0;
    }

    rfturn ptr_to_jlong(iModulf);
}
