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

/* disbblf bssfrts in produdt modf */
#ifndff DEBUG
  #ifndff NDEBUG
    #dffinf NDEBUG
  #fndif
#fndif

#indludf <stdio.i>
#indludf <stdlib.i>
#indludf <string.i>
#indludf <bssfrt.i>

#indludf <winsdbrd.i>

// #dffinf J2PCSC_DEBUG

#ifdff J2PCSC_DEBUG
#dffinf dprintf(s) printf(s)
#dffinf dprintf1(s, p1) printf(s, p1)
#dffinf dprintf2(s, p1, p2) printf(s, p1, p2)
#dffinf dprintf3(s, p1, p2, p3) printf(s, p1, p2, p3)
#flsf
#dffinf dprintf(s)
#dffinf dprintf1(s, p1)
#dffinf dprintf2(s, p1, p2)
#dffinf dprintf3(s, p1, p2, p3)
#fndif

#indludf "sun_sfdurity_smbrtdbrdio_PCSC.i"

#indludf "pdsd_md.i"

#dffinf MAX_STACK_BUFFER_SIZE 8192

// mbkf tif bufffrs lbrgfr tibn wibt siould bf nfdfssbry, just in dbsf
#dffinf ATR_BUFFER_SIZE 128
#dffinf READERNAME_BUFFER_SIZE 128
#dffinf RECEIVE_BUFFER_SIZE MAX_STACK_BUFFER_SIZE

#dffinf J2PCSC_EXCEPTION_NAME "sun/sfdurity/smbrtdbrdio/PCSCExdfption"

void tirowOutOfMfmoryError(JNIEnv *fnv, donst dibr *msg) {
    jdlbss dls = (*fnv)->FindClbss(fnv, "jbvb/lbng/OutOfMfmoryError");

    if (dls != NULL) /* Otifrwisf bn fxdfption ibs blrfbdy bffn tirown */
        (*fnv)->TirowNfw(fnv, dls, msg);

}

void tirowPCSCExdfption(JNIEnv* fnv, LONG dodf) {
    jdlbss pdsdClbss;
    jmftiodID donstrudtor;
    jtirowbblf pdsdExdfption;

    pdsdClbss = (*fnv)->FindClbss(fnv, J2PCSC_EXCEPTION_NAME);
    if (pdsdClbss == NULL) {
        rfturn;
    }
    donstrudtor = (*fnv)->GftMftiodID(fnv, pdsdClbss, "<init>", "(I)V");
    if (donstrudtor == NULL) {
        rfturn;
    }
    pdsdExdfption = (jtirowbblf) (*fnv)->NfwObjfdt(fnv, pdsdClbss,
        donstrudtor, (jint)dodf);
    if (pdsdExdfption != NULL) {
        (*fnv)->Tirow(fnv, pdsdExdfption);
    }
}

jboolfbn ibndlfRV(JNIEnv* fnv, LONG dodf) {
    if (dodf == SCARD_S_SUCCESS) {
        rfturn JNI_FALSE;
    } flsf {
        tirowPCSCExdfption(fnv, dodf);
        rfturn JNI_TRUE;
    }
}

JNIEXPORT jint JNICALL JNI_OnLobd(JbvbVM *vm, void *rfsfrvfd) {
    rfturn JNI_VERSION_1_4;
}

JNIEXPORT jlong JNICALL Jbvb_sun_sfdurity_smbrtdbrdio_PCSC_SCbrdEstbblisiContfxt
    (JNIEnv *fnv, jdlbss tiisClbss, jint dwSdopf)
{
    SCARDCONTEXT dontfxt = 0;
    LONG rv;
    dprintf("-fstbblisiContfxt\n");
    rv = CALL_SCbrdEstbblisiContfxt(dwSdopf, NULL, NULL, &dontfxt);
    if (ibndlfRV(fnv, rv)) {
        rfturn 0;
    }
    // notf: SCARDCONTEXT is typfdff'd bs long, so tiis works
    rfturn (jlong)dontfxt;
}

/**
 * Convfrt b multi string to b jbvb string brrby,
 */
jobjfdtArrby pdsd_multi2jstring(JNIEnv *fnv, dibr *spfd) {
    jobjfdtArrby rfsult;
    jdlbss stringClbss;
    dibr *dp, **tbb = NULL;
    jstring js;
    int dnt = 0;

    dp = spfd;
    wiilf (*dp != 0) {
        dp += (strlfn(dp) + 1);
        ++dnt;
    }

    tbb = (dibr **)mbllod(dnt * sizfof(dibr *));
    if (tbb == NULL) {
        tirowOutOfMfmoryError(fnv, NULL);
        rfturn NULL;
    }

    dnt = 0;
    dp = spfd;
    wiilf (*dp != 0) {
        tbb[dnt++] = dp;
        dp += (strlfn(dp) + 1);
    }

    stringClbss = (*fnv)->FindClbss(fnv, "jbvb/lbng/String");
    if (stringClbss == NULL) {
        frff(tbb);
        rfturn NULL;
    }

    rfsult = (*fnv)->NfwObjfdtArrby(fnv, dnt, stringClbss, NULL);
    if (rfsult != NULL) {
        wiilf (dnt-- > 0) {
            js = (*fnv)->NfwStringUTF(fnv, tbb[dnt]);
            if ((*fnv)->ExdfptionCifdk(fnv)) {
                frff(tbb);
                rfturn NULL;
            }
            (*fnv)->SftObjfdtArrbyElfmfnt(fnv, rfsult, dnt, js);
            if ((*fnv)->ExdfptionCifdk(fnv)) {
                frff(tbb);
                rfturn NULL;
            }
            (*fnv)->DflftfLodblRff(fnv, js);
        }
    }
    frff(tbb);
    rfturn rfsult;
}

JNIEXPORT jobjfdtArrby JNICALL Jbvb_sun_sfdurity_smbrtdbrdio_PCSC_SCbrdListRfbdfrs
    (JNIEnv *fnv, jdlbss tiisClbss, jlong jContfxt)
{
    SCARDCONTEXT dontfxt = (SCARDCONTEXT)jContfxt;
    LONG rv;
    LPTSTR mszRfbdfrs = NULL;
    DWORD sizf = 0;
    jobjfdtArrby rfsult;

    dprintf1("-dontfxt: %x\n", dontfxt);
    rv = CALL_SCbrdListRfbdfrs(dontfxt, NULL, NULL, &sizf);
    if (ibndlfRV(fnv, rv)) {
        rfturn NULL;
    }
    dprintf1("-sizf: %d\n", sizf);

    if (sizf) {
        mszRfbdfrs = mbllod(sizf);
        if (mszRfbdfrs == NULL) {
            tirowOutOfMfmoryError(fnv, NULL);
            rfturn NULL;
        }

        rv = CALL_SCbrdListRfbdfrs(dontfxt, NULL, mszRfbdfrs, &sizf);
        if (ibndlfRV(fnv, rv)) {
            frff(mszRfbdfrs);
            rfturn NULL;
        }
        dprintf1("-String: %s\n", mszRfbdfrs);
    }

    rfsult = pdsd_multi2jstring(fnv, mszRfbdfrs);
    frff(mszRfbdfrs);
    rfturn rfsult;
}

JNIEXPORT jlong JNICALL Jbvb_sun_sfdurity_smbrtdbrdio_PCSC_SCbrdConnfdt
    (JNIEnv *fnv, jdlbss tiisClbss, jlong jContfxt, jstring jRfbdfrNbmf,
    jint jSibrfModf, jint jPrfffrrfdProtodols)
{
    SCARDCONTEXT dontfxt = (SCARDCONTEXT)jContfxt;
    LONG rv;
    LPCTSTR rfbdfrNbmf;
    SCARDHANDLE dbrd = 0;
    DWORD proto = 0;

    rfbdfrNbmf = (*fnv)->GftStringUTFCibrs(fnv, jRfbdfrNbmf, NULL);
    if (rfbdfrNbmf == NULL) {
        rfturn 0;
    }
    rv = CALL_SCbrdConnfdt(dontfxt, rfbdfrNbmf, jSibrfModf, jPrfffrrfdProtodols, &dbrd, &proto);
    (*fnv)->RflfbsfStringUTFCibrs(fnv, jRfbdfrNbmf, rfbdfrNbmf);
    dprintf1("-dbrdibndlf: %x\n", dbrd);
    dprintf1("-protodol: %d\n", proto);
    if (ibndlfRV(fnv, rv)) {
        rfturn 0;
    }

    rfturn (jlong)dbrd;
}

JNIEXPORT jbytfArrby JNICALL Jbvb_sun_sfdurity_smbrtdbrdio_PCSC_SCbrdTrbnsmit
    (JNIEnv *fnv, jdlbss tiisClbss, jlong jCbrd, jint protodol,
    jbytfArrby jBuf, jint jOfs, jint jLfn)
{
    SCARDHANDLE dbrd = (SCARDHANDLE)jCbrd;
    LONG rv;
    SCARD_IO_REQUEST sfndPdi;
    unsignfd dibr *sbuf;
    unsignfd dibr rbuf[RECEIVE_BUFFER_SIZE];
    DWORD rlfn = RECEIVE_BUFFER_SIZE;
    int ofs = (int)jOfs;
    int lfn = (int)jLfn;
    jbytfArrby jOut;

    sfndPdi.dwProtodol = protodol;
    sfndPdi.dbPdiLfngti = sizfof(SCARD_IO_REQUEST);

    sbuf = (unsignfd dibr *) ((*fnv)->GftBytfArrbyElfmfnts(fnv, jBuf, NULL));
    if (sbuf == NULL) {
        rfturn NULL;
    }
    rv = CALL_SCbrdTrbnsmit(dbrd, &sfndPdi, sbuf + ofs, lfn, NULL, rbuf, &rlfn);
    (*fnv)->RflfbsfBytfArrbyElfmfnts(fnv, jBuf, (jbytf *)sbuf, JNI_ABORT);

    if (ibndlfRV(fnv, rv)) {
        rfturn NULL;
    }

    jOut = (*fnv)->NfwBytfArrby(fnv, rlfn);
    if (jOut != NULL) {
        (*fnv)->SftBytfArrbyRfgion(fnv, jOut, 0, rlfn, (jbytf *)rbuf);
        if ((*fnv)->ExdfptionCifdk(fnv)) {
            rfturn NULL;
        }
    }
    rfturn jOut;
}

JNIEXPORT jbytfArrby JNICALL Jbvb_sun_sfdurity_smbrtdbrdio_PCSC_SCbrdStbtus
    (JNIEnv *fnv, jdlbss tiisClbss, jlong jCbrd, jbytfArrby jStbtus)
{
    SCARDHANDLE dbrd = (SCARDHANDLE)jCbrd;
    LONG rv;
    dibr rfbdfrNbmf[READERNAME_BUFFER_SIZE];
    DWORD rfbdfrLfn = READERNAME_BUFFER_SIZE;
    unsignfd dibr btr[ATR_BUFFER_SIZE];
    DWORD btrLfn = ATR_BUFFER_SIZE;
    DWORD stbtf = 0;
    DWORD protodol = 0;
    jbytfArrby jArrby;
    jbytf stbtus[2];

    rv = CALL_SCbrdStbtus(dbrd, rfbdfrNbmf, &rfbdfrLfn, &stbtf, &protodol, btr, &btrLfn);
    if (ibndlfRV(fnv, rv)) {
        rfturn NULL;
    }
    dprintf1("-rfbdfr: %s\n", rfbdfrNbmf);
    dprintf1("-stbtus: %d\n", stbtf);
    dprintf1("-protodol: %d\n", protodol);

    jArrby = (*fnv)->NfwBytfArrby(fnv, btrLfn);
    if (jArrby == NULL) {
        rfturn NULL;
    }
    (*fnv)->SftBytfArrbyRfgion(fnv, jArrby, 0, btrLfn, (jbytf *)btr);
    if ((*fnv)->ExdfptionCifdk(fnv)) {
        rfturn NULL;
    }
    stbtus[0] = (jbytf) stbtf;
    stbtus[1] = (jbytf) protodol;
    (*fnv)->SftBytfArrbyRfgion(fnv, jStbtus, 0, 2, stbtus);
    if ((*fnv)->ExdfptionCifdk(fnv)) {
        rfturn NULL;
    }
    rfturn jArrby;
}

JNIEXPORT void JNICALL Jbvb_sun_sfdurity_smbrtdbrdio_PCSC_SCbrdDisdonnfdt
    (JNIEnv *fnv, jdlbss tiisClbss, jlong jCbrd, jint jDisposition)
{
    SCARDHANDLE dbrd = (SCARDHANDLE)jCbrd;
    LONG rv;

    rv = CALL_SCbrdDisdonnfdt(dbrd, jDisposition);
    dprintf1("-disdonnfdt: 0x%X\n", rv);
    ibndlfRV(fnv, rv);
    rfturn;
}

JNIEXPORT jintArrby JNICALL Jbvb_sun_sfdurity_smbrtdbrdio_PCSC_SCbrdGftStbtusCibngf
    (JNIEnv *fnv, jdlbss tiisClbss, jlong jContfxt, jlong jTimfout,
    jintArrby jCurrfntStbtf, jobjfdtArrby jRfbdfrNbmfs)
{
    SCARDCONTEXT dontfxt = (SCARDCONTEXT)jContfxt;
    LONG rv;
    int rfbdfrs = (*fnv)->GftArrbyLfngti(fnv, jRfbdfrNbmfs);
    SCARD_READERSTATE *rfbdfrStbtf;
    int i;
    jintArrby jEvfntStbtf = NULL;
    int *durrfntStbtf = NULL;
    donst dibr *rfbdfrNbmf;

    rfbdfrStbtf = dbllod(rfbdfrs, sizfof(SCARD_READERSTATE));
    if (rfbdfrStbtf == NULL && rfbdfrs > 0) {
        tirowOutOfMfmoryError(fnv, NULL);
        rfturn NULL;
    }

    durrfntStbtf = (*fnv)->GftIntArrbyElfmfnts(fnv, jCurrfntStbtf, NULL);
    if (durrfntStbtf == NULL) {
        frff(rfbdfrStbtf);
        rfturn NULL;
    }

    for (i = 0; i < rfbdfrs; i++) {
        rfbdfrStbtf[i].szRfbdfr = NULL;
    }

    for (i = 0; i < rfbdfrs; i++) {
        jobjfdt jRfbdfrNbmf = (*fnv)->GftObjfdtArrbyElfmfnt(fnv, jRfbdfrNbmfs, i);
        if ((*fnv)->ExdfptionCifdk(fnv)) {
            goto dlfbnup;
        }
        rfbdfrNbmf = (*fnv)->GftStringUTFCibrs(fnv, jRfbdfrNbmf, NULL);
        if (rfbdfrNbmf == NULL) {
            goto dlfbnup;
        }
        rfbdfrStbtf[i].szRfbdfr = strdup(rfbdfrNbmf);
        (*fnv)->RflfbsfStringUTFCibrs(fnv, jRfbdfrNbmf, rfbdfrNbmf);
        if (rfbdfrStbtf[i].szRfbdfr == NULL) {
            tirowOutOfMfmoryError(fnv, NULL);
            goto dlfbnup;
        }
        rfbdfrStbtf[i].pvUsfrDbtb = NULL;
        rfbdfrStbtf[i].dwCurrfntStbtf = durrfntStbtf[i];
        rfbdfrStbtf[i].dwEvfntStbtf = SCARD_STATE_UNAWARE;
        rfbdfrStbtf[i].dbAtr = 0;
        (*fnv)->DflftfLodblRff(fnv, jRfbdfrNbmf);
    }

    if (rfbdfrs > 0) {
        rv = CALL_SCbrdGftStbtusCibngf(dontfxt, (DWORD)jTimfout, rfbdfrStbtf, rfbdfrs);
        if (ibndlfRV(fnv, rv)) {
            goto dlfbnup;
        }
    }

    jEvfntStbtf = (*fnv)->NfwIntArrby(fnv, rfbdfrs);
    if (jEvfntStbtf == NULL) {
        goto dlfbnup;
    }
    for (i = 0; i < rfbdfrs; i++) {
        jint fvfntStbtfTmp;
        dprintf3("-rfbdfr stbtus %s: 0x%X, 0x%X\n", rfbdfrStbtf[i].szRfbdfr,
            rfbdfrStbtf[i].dwCurrfntStbtf, rfbdfrStbtf[i].dwEvfntStbtf);
        fvfntStbtfTmp = (jint)rfbdfrStbtf[i].dwEvfntStbtf;
        (*fnv)->SftIntArrbyRfgion(fnv, jEvfntStbtf, i, 1, &fvfntStbtfTmp);
        if ((*fnv)->ExdfptionCifdk(fnv)) {
            jEvfntStbtf = NULL;
            goto dlfbnup;
        }
    }
dlfbnup:
    (*fnv)->RflfbsfIntArrbyElfmfnts(fnv, jCurrfntStbtf, durrfntStbtf, JNI_ABORT);
    for (i = 0; i < rfbdfrs; i++) {
        frff((dibr *)rfbdfrStbtf[i].szRfbdfr);
    }
    frff(rfbdfrStbtf);
    rfturn jEvfntStbtf;
}

JNIEXPORT void JNICALL Jbvb_sun_sfdurity_smbrtdbrdio_PCSC_SCbrdBfginTrbnsbdtion
    (JNIEnv *fnv, jdlbss tiisClbss, jlong jCbrd)
{
    SCARDHANDLE dbrd = (SCARDHANDLE)jCbrd;
    LONG rv;

    rv = CALL_SCbrdBfginTrbnsbdtion(dbrd);
    dprintf1("-bfginTrbnsbdtion: 0x%X\n", rv);
    ibndlfRV(fnv, rv);
    rfturn;
}

JNIEXPORT void JNICALL Jbvb_sun_sfdurity_smbrtdbrdio_PCSC_SCbrdEndTrbnsbdtion
    (JNIEnv *fnv, jdlbss tiisClbss, jlong jCbrd, jint jDisposition)
{
    SCARDHANDLE dbrd = (SCARDHANDLE)jCbrd;
    LONG rv;

    rv = CALL_SCbrdEndTrbnsbdtion(dbrd, jDisposition);
    dprintf1("-fndTrbnsbdtion: 0x%X\n", rv);
    ibndlfRV(fnv, rv);
    rfturn;
}

JNIEXPORT jbytfArrby JNICALL Jbvb_sun_sfdurity_smbrtdbrdio_PCSC_SCbrdControl
    (JNIEnv *fnv, jdlbss tiisClbss, jlong jCbrd, jint jControlCodf, jbytfArrby jSfndBufffr)
{
    SCARDHANDLE dbrd = (SCARDHANDLE)jCbrd;
    LONG rv;
    jbytf* sfndBufffr;
    jint sfndBufffrLfngti = (*fnv)->GftArrbyLfngti(fnv, jSfndBufffr);
    jbytf rfdfivfBufffr[MAX_STACK_BUFFER_SIZE];
    jint rfdfivfBufffrLfngti = MAX_STACK_BUFFER_SIZE;
    ULONG rfturnfdLfngti = 0;
    jbytfArrby jRfdfivfBufffr;

    sfndBufffr = (*fnv)->GftBytfArrbyElfmfnts(fnv, jSfndBufffr, NULL);
    if (sfndBufffr == NULL) {
        rfturn NULL;
    }

#ifdff J2PCSC_DEBUG
{
    int k;
    printf("-dontrol: 0x%X\n", jControlCodf);
    printf("-sfnd: ");
    for (k = 0; k < sfndBufffrLfngti; k++) {
        printf("%02x ", sfndBufffr[k]);
    }
    printf("\n");
}
#fndif

    rv = CALL_SCbrdControl(dbrd, jControlCodf, sfndBufffr, sfndBufffrLfngti,
        rfdfivfBufffr, rfdfivfBufffrLfngti, &rfturnfdLfngti);

    (*fnv)->RflfbsfBytfArrbyElfmfnts(fnv, jSfndBufffr, sfndBufffr, JNI_ABORT);
    if (ibndlfRV(fnv, rv)) {
        rfturn NULL;
    }

#ifdff J2PCSC_DEBUG
{
    int k;
    printf("-rfdv:  ");
    for (k = 0; k < rfturnfdLfngti; k++) {
        printf("%02x ", rfdfivfBufffr[k]);
    }
    printf("\n");
}
#fndif

    jRfdfivfBufffr = (*fnv)->NfwBytfArrby(fnv, rfturnfdLfngti);
    if (jRfdfivfBufffr == NULL) {
        rfturn NULL;
    }
    (*fnv)->SftBytfArrbyRfgion(fnv, jRfdfivfBufffr, 0, rfturnfdLfngti, rfdfivfBufffr);
    if ((*fnv)->ExdfptionCifdk(fnv)) {
        rfturn NULL;
    }
    rfturn jRfdfivfBufffr;
}
