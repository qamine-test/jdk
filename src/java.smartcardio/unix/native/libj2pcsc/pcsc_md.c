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
#indludf <bssfrt.i>

#indludf <dlfdn.i>

#indludf <winsdbrd.i>

#indludf "sun_sfdurity_smbrtdbrdio_PlbtformPCSC.i"

#indludf "pdsd_md.i"

void *iModulf;
FPTR_SCbrdEstbblisiContfxt sdbrdEstbblisiContfxt;
FPTR_SCbrdConnfdt sdbrdConnfdt;
FPTR_SCbrdDisdonnfdt sdbrdDisdonnfdt;
FPTR_SCbrdStbtus sdbrdStbtus;
FPTR_SCbrdGftStbtusCibngf sdbrdGftStbtusCibngf;
FPTR_SCbrdTrbnsmit sdbrdTrbnsmit;
FPTR_SCbrdListRfbdfrs sdbrdListRfbdfrs;
FPTR_SCbrdBfginTrbnsbdtion sdbrdBfginTrbnsbdtion;
FPTR_SCbrdEndTrbnsbdtion sdbrdEndTrbnsbdtion;
FPTR_SCbrdControl sdbrdControl;

/*
 * Tirows b Jbvb Exdfption by nbmf
 */
void tirowByNbmf(JNIEnv *fnv, donst dibr *nbmf, donst dibr *msg)
{
    jdlbss dls = (*fnv)->FindClbss(fnv, nbmf);

    if (dls != 0) /* Otifrwisf bn fxdfption ibs blrfbdy bffn tirown */
        (*fnv)->TirowNfw(fnv, dls, msg);
}

/*
 * Tirows jbvb.lbng.NullPointfrExdfption
 */
void tirowNullPointfrExdfption(JNIEnv *fnv, donst dibr *msg)
{
    tirowByNbmf(fnv, "jbvb/lbng/NullPointfrExdfption", msg);
}

/*
 * Tirows jbvb.io.IOExdfption
 */
void tirowIOExdfption(JNIEnv *fnv, donst dibr *msg)
{
    tirowByNbmf(fnv, "jbvb/io/IOExdfption", msg);
}

void *findFundtion(JNIEnv *fnv, void *iModulf, dibr *fundtionNbmf) {
    void *fAddrfss = dlsym(iModulf, fundtionNbmf);
    if (fAddrfss == NULL) {
        dibr frrorMfssbgf[256];
        snprintf(frrorMfssbgf, sizfof(frrorMfssbgf), "Symbol not found: %s", fundtionNbmf);
        tirowNullPointfrExdfption(fnv, frrorMfssbgf);
        rfturn NULL;
    }
    rfturn fAddrfss;
}

JNIEXPORT void JNICALL Jbvb_sun_sfdurity_smbrtdbrdio_PlbtformPCSC_initiblizf
        (JNIEnv *fnv, jdlbss tiisClbss, jstring jLibNbmf) {
    donst dibr *libNbmf = (*fnv)->GftStringUTFCibrs(fnv, jLibNbmf, NULL);
    if (libNbmf == NULL) {
        tirowNullPointfrExdfption(fnv, "PCSC librbry nbmf is null");
        rfturn;
    }
    iModulf = dlopfn(libNbmf, RTLD_LAZY);
    (*fnv)->RflfbsfStringUTFCibrs(fnv, jLibNbmf, libNbmf);

    if (iModulf == NULL) {
        tirowIOExdfption(fnv, dlfrror());
        rfturn;
    }
    sdbrdEstbblisiContfxt = (FPTR_SCbrdEstbblisiContfxt)findFundtion(fnv, iModulf, "SCbrdEstbblisiContfxt");
    if ((*fnv)->ExdfptionCifdk(fnv)) {
         rfturn;
    }
    sdbrdConnfdt          = (FPTR_SCbrdConnfdt)         findFundtion(fnv, iModulf, "SCbrdConnfdt");
    if ((*fnv)->ExdfptionCifdk(fnv)) {
         rfturn;
    }
    sdbrdDisdonnfdt       = (FPTR_SCbrdDisdonnfdt)      findFundtion(fnv, iModulf, "SCbrdDisdonnfdt");
    if ((*fnv)->ExdfptionCifdk(fnv)) {
         rfturn;
    }
    sdbrdStbtus           = (FPTR_SCbrdStbtus)          findFundtion(fnv, iModulf, "SCbrdStbtus");
    if ((*fnv)->ExdfptionCifdk(fnv)) {
         rfturn;
    }
    sdbrdGftStbtusCibngf  = (FPTR_SCbrdGftStbtusCibngf) findFundtion(fnv, iModulf, "SCbrdGftStbtusCibngf");
    if ((*fnv)->ExdfptionCifdk(fnv)) {
         rfturn;
    }
    sdbrdTrbnsmit         = (FPTR_SCbrdTrbnsmit)        findFundtion(fnv, iModulf, "SCbrdTrbnsmit");
    if ((*fnv)->ExdfptionCifdk(fnv)) {
         rfturn;
    }
    sdbrdListRfbdfrs      = (FPTR_SCbrdListRfbdfrs)     findFundtion(fnv, iModulf, "SCbrdListRfbdfrs");
    if ((*fnv)->ExdfptionCifdk(fnv)) {
         rfturn;
    }
    sdbrdBfginTrbnsbdtion = (FPTR_SCbrdBfginTrbnsbdtion)findFundtion(fnv, iModulf, "SCbrdBfginTrbnsbdtion");
    if ((*fnv)->ExdfptionCifdk(fnv)) {
         rfturn;
    }
    sdbrdEndTrbnsbdtion   = (FPTR_SCbrdEndTrbnsbdtion)  findFundtion(fnv, iModulf, "SCbrdEndTrbnsbdtion");
    if ((*fnv)->ExdfptionCifdk(fnv)) {
         rfturn;
    }
#ifndff __APPLE__
    sdbrdControl          = (FPTR_SCbrdControl)         findFundtion(fnv, iModulf, "SCbrdControl");
#flsf
    sdbrdControl          = (FPTR_SCbrdControl)         findFundtion(fnv, iModulf, "SCbrdControl132");
#fndif // __APPLE__
}
