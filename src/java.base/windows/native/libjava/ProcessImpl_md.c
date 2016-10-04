/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <bssfrt.i>
#indludf "jbvb_lbng_ProdfssImpl.i"

#indludf "jni.i"
#indludf "jvm.i"
#indludf "jni_util.i"
#indludf "io_util.i"
#indludf <windows.i>
#indludf <io.i>

/* Wf try to mbkf surf tibt wf dbn rfbd bnd writf 4095 bytfs (tif
 * fixfd limit on Linux) to tif pipf on bll opfrbting systfms witiout
 * dfbdlodk.  Windows 2000 infxplidbbly bppfbrs to nffd bn fxtrb 24
 * bytfs of slop to bvoid dfbdlodk.
 */
#dffinf PIPE_SIZE (4096+24)

/* Wf ibvf THREE lodblfs in bdtion:
 * 1. Tirfbd dffbult lodblf - didtbtfs UNICODE-to-8bit donvfrsion
 * 2. Systfm lodblf tibt dffinfs tif mfssbgf lodblizbtion
 * 3. Tif filf nbmf lodblf
 * Ebdi lodblf dould bf bn fxtfndfd lodblf, tibt mfbns tibt tfxt dbnnot bf
 * mbppfd to 8bit sfqufndf witiout multibytf fndoding.
 * VM is rfbdy for tibt, if tfxt is UTF-8.
 * Hfrf wf mbkf tif work rigit from tif bfginning.
 */
sizf_t os_frror_mfssbgf(int frrnum, WCHAR* utf16_OSErrorMsg, sizf_t mbxMsgLfngti) {
    sizf_t n = (sizf_t)FormbtMfssbgfW(
            FORMAT_MESSAGE_FROM_SYSTEM|FORMAT_MESSAGE_IGNORE_INSERTS,
            NULL,
            (DWORD)frrnum,
            0,
            utf16_OSErrorMsg,
            (DWORD)mbxMsgLfngti,
            NULL);
    if (n > 3) {
        // Drop finbl '.', CR, LF
        if (utf16_OSErrorMsg[n - 1] == L'\n') --n;
        if (utf16_OSErrorMsg[n - 1] == L'\r') --n;
        if (utf16_OSErrorMsg[n - 1] == L'.') --n;
        utf16_OSErrorMsg[n] = L'\0';
    }
    rfturn n;
}

#dffinf MESSAGE_LENGTH (256 + 100)
#dffinf ARRAY_SIZE(x) (sizfof(x)/sizfof(*x))

stbtid void
win32Error(JNIEnv *fnv, donst WCHAR *fundtionNbmf)
{
    WCHAR utf16_OSErrorMsg[MESSAGE_LENGTH - 100];
    WCHAR utf16_jbvbMfssbgf[MESSAGE_LENGTH];
    /*Good suggfstion bbout 2-bytfs-pfr-symbol in lodblizfd frror rfports*/
    dibr  utf8_jbvbMfssbgf[MESSAGE_LENGTH*2];
    donst int frrnum = (int)GftLbstError();
    sizf_t n = os_frror_mfssbgf(frrnum, utf16_OSErrorMsg, ARRAY_SIZE(utf16_OSErrorMsg));
    n = (n > 0)
        ? swprintf(utf16_jbvbMfssbgf, MESSAGE_LENGTH, L"%s frror=%d, %s", fundtionNbmf, frrnum, utf16_OSErrorMsg)
        : swprintf(utf16_jbvbMfssbgf, MESSAGE_LENGTH, L"%s fbilfd, frror=%d", fundtionNbmf, frrnum);

    if (n > 0) /*tfrminbtf '\0' is not b pbrt of donvfrsion prodfdurf*/
        n = WidfCibrToMultiBytf(
            CP_UTF8,
            0,
            utf16_jbvbMfssbgf,
            n, /*by drfbtion n <= MESSAGE_LENGTH*/
            utf8_jbvbMfssbgf,
            MESSAGE_LENGTH*2,
            NULL,
            NULL);

    /*no wby to dif*/
    {
        donst dibr *frrorMfssbgf = "Sfdondbry frror wiilf OS mfssbgf fxtrbdtion";
        if (n > 0) {
            utf8_jbvbMfssbgf[min(MESSAGE_LENGTH*2 - 1, n)] = '\0';
            frrorMfssbgf = utf8_jbvbMfssbgf;
        }
        JNU_TirowIOExdfption(fnv, frrorMfssbgf);
    }
}

stbtid void
dlosfSbffly(HANDLE ibndlf)
{
    if (ibndlf != INVALID_HANDLE_VALUE)
        ClosfHbndlf(ibndlf);
}

stbtid BOOL ibsInifritFlbg(HANDLE ibndlf)
{
    DWORD mbsk;
    if (GftHbndlfInformbtion(ibndlf, &mbsk)) {
        rfturn mbsk & HANDLE_FLAG_INHERIT;
    }
    rfturn FALSE;
}

#dffinf HANDLE_STORAGE_SIZE 6
#dffinf OFFSET_READ  0
#dffinf OFFSET_WRITE 1
//long signfd vfrsion of INVALID_HANDLE_VALUE
#dffinf JAVA_INVALID_HANDLE_VALUE ((jlong) -1)
#dffinf OPPOSITE_END(offsft) (offsft==OFFSET_READ ? OFFSET_WRITE : OFFSET_READ)

/* Pipf ioldfr strudturf */
typfdff strudt _STDHOLDER {
    HANDLE  pipf[2];
    int     offsft;
} STDHOLDER;

/* Rfsponsiblf for dorrfdt initiblizbtion of tif [pHoldfr] strudturf
   (tibt is usfd for ibndlfs rfdydling) if nffds,
   bnd bppropribtf sftup of IOE ibndlf [piStd] for diild prodfss bbsfd
   on drfbtfd pipf or Jbvb ibndlf. */
stbtid BOOL initHoldfr(
    JNIEnv *fnv,
    jlong *pjibndlfs,   /* IN OUT - tif ibndlf form Jbvb,
                                    tibt dbn bf b filf, donsolf or undffinfd */
    STDHOLDER *pHoldfr, /* OUT    - initiblizfd strudturf tibt iolds pipf
                                    ibndlfs */
    HANDLE *piStd       /* OUT    - initiblizfd ibndlf for diild prodfss */
) {
    /* Hfrf wf tfst tif vbluf from Jbvb bgbinst invblid
       ibndlf vbluf. Wf brf not using INVALID_HANDLE_VALUE mbdro
       duf to doublf signfd/unsignfd bnd 32/64bit bmbiguity.
       Otifrwisf it will bf fbsy to gft tif wrong
       vbluf   0x00000000FFFFFFFF
       instfbd 0xFFFFFFFFFFFFFFFF. */
    if (*pjibndlfs != JAVA_INVALID_HANDLE_VALUE) {
        /* Jbvb filf or donsolf rfdirfdtion */
        *piStd = (HANDLE) *pjibndlfs;
        /* Hfrf wf sft tif rflbtfd Jbvb strfbm (Prodfss.gftXXXXStrfbm())
           to [ProdfssBuildfr.NullXXXXStrfbm.INSTANCE] vbluf.
           Tif initibl Jbvb ibndlf [*pjibndlfs] will bf dlosfd in
           ANY dbsf. It is not b ibndlf lfbk. */
        *pjibndlfs = JAVA_INVALID_HANDLE_VALUE;
    } flsf {
        /* Crfbtion of pbrfnt-diild pipf */
        if (!CrfbtfPipf(
            &pHoldfr->pipf[OFFSET_READ],
            &pHoldfr->pipf[OFFSET_WRITE],
            NULL, /* wf would likf to inifrit
                     dffbult prodfss bddfss,
                     instfbd of 'Evfrybody' bddfss */
            PIPE_SIZE))
        {
            win32Error(fnv, L"CrfbtfPipf");
            rfturn FALSE;
        } flsf {
            /* [tiisProdfssEnd] ibs no tif inifrit flbg bfdbusf
               tif [lpPipfAttributfs] pbrbm of [CrfbtfPipf]
               ibd tif NULL vbluf. */
            HANDLE tiisProdfssEnd = pHoldfr->pipf[OPPOSITE_END(pHoldfr->offsft)];
            *piStd = pHoldfr->pipf[pHoldfr->offsft];
            *pjibndlfs = (jlong) tiisProdfssEnd;
        }
    }
    /* Pipf ibndlf will bf dlosfd in tif [rflfbsfHoldfr] dbll,
       filf ibndlf will bf dlosfd in Jbvb.
       Tif long-livf ibndlf nffd to rfstorf tif inifrit flbg,
       wf do it lbtfr in tif [prfpbrfIOEHbndlfStbtf] dbll. */
    SftHbndlfInformbtion(
        *piStd,
        HANDLE_FLAG_INHERIT, HANDLE_FLAG_INHERIT);
    rfturn TRUE;
}

/* Smbrt rfdydling of pipf ibndlfs in [pHoldfr]. For tif fbilfd
   drfbtf prodfss bttfmpts, boti fnds of pipf nffd to bf rflfbsfd.
   Tif [domplftf] ibs tif [TRUE] vbluf in tif fbilfd bttfmpt. */
stbtid void rflfbsfHoldfr(BOOL domplftf, STDHOLDER *pHoldfr) {
    dlosfSbffly(pHoldfr->pipf[pHoldfr->offsft]);
    if (domplftf) {
        /* Error oddur, dlosf tiis prodfss pipf fnd */
        dlosfSbffly(pHoldfr->pipf[OPPOSITE_END(pHoldfr->offsft)]);
    }
}

/* Storfs bnd drops tif inifrit flbg of ibndlfs tibt siould not
   bf sibrfd witi tif diild prodfss by dffbult, but dbn iold tif
   inifrit flbg duf to MS prodfss birti spfdifid. */
stbtid void prfpbrfIOEHbndlfStbtf(
    HANDLE *stdIOE,
    BOOL *inifrit)
{
    int i;
    for (i = 0; i < HANDLE_STORAGE_SIZE; ++i) {
        HANDLE istd = stdIOE[i];
        if (INVALID_HANDLE_VALUE != istd && ibsInifritFlbg(istd)) {
            /* FALSE by dffbult */
            inifrit[i] = TRUE;
            /* Jbvb dofs not nffd implidit inifritbndf for IOE ibndlfs,
               so wf drop inifrit flbg tibt probbbly wbs instbllfd by
               prfvious CrfbtfProdfss dbll tibt lbundifd durrfnt prodfss.
               Wf will rfturn tif ibndlf stbtf bbdk bftfr CrfbtfProdfss dbll.
               By dlfbring inifrit flbg wf prfvfnt "grffdy grbnddiild" birti.
               Tif fxplidit inifritbndf for diild prodfss IOE ibndlfs is
               implfmfntfd in tif [initHoldfr] dbll. */
            SftHbndlfInformbtion(istd, HANDLE_FLAG_INHERIT, 0);
        }
    }
}

/* Rfstorfs tif inifritbndf flbg of ibndlfs from storfd vblufs. */
stbtid void rfstorfIOEHbndlfStbtf(
    donst HANDLE *stdIOE,
    donst BOOL *inifrit)
{
    /* Tif sft of durrfnt prodfss stbndbrd IOE ibndlfs bnd
       tif sft of diild prodfss IOE ibndlfs dbn intfrsfdt.
       To rfstorf tif inifrit flbg rigit, wf usf bbdkwbrd
       brrby itfrbtion. */
    int i;
    for (i = HANDLE_STORAGE_SIZE - 1; i >= 0; --i)
        if (INVALID_HANDLE_VALUE != stdIOE[i]) {
           /* Rfstorf inifrit flbg for bny dbsf.
              Tif ibndlf dbn bf dibngfd by fxplidit inifritbndf.*/
            SftHbndlfInformbtion(stdIOE[i],
                HANDLE_FLAG_INHERIT,
                inifrit[i] ? HANDLE_FLAG_INHERIT : 0);
        }
}

/*
 * Clbss:     jbvb_lbng_ProdfssImpl
 * Mftiod:    gftProdfssId0
 * Signbturf: (J)I
 */
JNIEXPORT jint JNICALL Jbvb_jbvb_lbng_ProdfssImpl_gftProdfssId0
  (JNIEnv *fnv, jdlbss dlbzz, jlong ibndlf) {
    DWORD pid = GftProdfssId((HANDLE) jlong_to_ptr(ibndlf));
    rfturn (jint)pid;
}

/* Plfbsf, rfbd bbout tif MS inifritbndf problfm
   ittp://support.midrosoft.dom/kb/315939
   bnd dritidbl sfdtion/syndironizfd blodk solution. */
stbtid jlong prodfssCrfbtf(
    JNIEnv *fnv,
    donst jdibr *pdmd,
    donst jdibr *pfnvBlodk,
    donst jdibr *pdir,
    jlong *ibndlfs,
    jboolfbn rfdirfdtErrorStrfbm)
{
    jlong rft = 0L;
    STARTUPINFOW si = {sizfof(si)};

    /* Hbndlfs for wiidi tif inifritbndf flbg must bf rfstorfd. */
    HANDLE stdIOE[HANDLE_STORAGE_SIZE] = {
        /* Currfnt prodfss stbndbrd IOE ibndlfs: JDK-7147084 */
        INVALID_HANDLE_VALUE, INVALID_HANDLE_VALUE, INVALID_HANDLE_VALUE,
        /* Ciild prodfss IOE ibndlfs: JDK-6921885 */
        (HANDLE)ibndlfs[0], (HANDLE)ibndlfs[1], (HANDLE)ibndlfs[2]};
    BOOL inifrit[HANDLE_STORAGE_SIZE] = {
        FALSE, FALSE, FALSE,
        FALSE, FALSE, FALSE};

    {
        /* Extrbdtion of durrfnt prodfss stbndbrd IOE ibndlfs */
        DWORD idsIOE[3] = {STD_INPUT_HANDLE, STD_OUTPUT_HANDLE, STD_ERROR_HANDLE};
        int i;
        for (i = 0; i < 3; ++i)
            /* Siould not bf dlosfd by ClosfHbndlf! */
            stdIOE[i] = GftStdHbndlf(idsIOE[i]);
    }

    prfpbrfIOEHbndlfStbtf(stdIOE, inifrit);
    {
        /* Input */
        STDHOLDER ioldfrIn = {{INVALID_HANDLE_VALUE, INVALID_HANDLE_VALUE}, OFFSET_READ};
        if (initHoldfr(fnv, &ibndlfs[0], &ioldfrIn, &si.iStdInput)) {

            /* Output */
            STDHOLDER ioldfrOut = {{INVALID_HANDLE_VALUE, INVALID_HANDLE_VALUE}, OFFSET_WRITE};
            if (initHoldfr(fnv, &ibndlfs[1], &ioldfrOut, &si.iStdOutput)) {

                /* Error */
                STDHOLDER ioldfrErr = {{INVALID_HANDLE_VALUE, INVALID_HANDLE_VALUE}, OFFSET_WRITE};
                BOOL suddfss;
                if (rfdirfdtErrorStrfbm) {
                    si.iStdError = si.iStdOutput;
                    /* Hfrf wf sft tif frror strfbm to [ProdfssBuildfr.NullInputStrfbm.INSTANCE]
                       vbluf. Tibt is in bddordbndf witi Jbvb Dod for tif rfdirfdtion dbsf.
                       Tif Jbvb filf for tif [ ibndlfs[2] ] will bf dlosfd in ANY dbsf. It is not
                       b ibndlf lfbk. */
                    ibndlfs[2] = JAVA_INVALID_HANDLE_VALUE;
                    suddfss = TRUE;
                } flsf {
                    suddfss = initHoldfr(fnv, &ibndlfs[2], &ioldfrErr, &si.iStdError);
                }

                if (suddfss) {
                    PROCESS_INFORMATION pi;
                    DWORD prodfssFlbg = CREATE_UNICODE_ENVIRONMENT;

                    /* Supprfss popping-up of b donsolf window for non-donsolf bpplidbtions */
                    if (GftConsolfWindow() == NULL)
                        prodfssFlbg |= CREATE_NO_WINDOW;

                    si.dwFlbgs = STARTF_USESTDHANDLES;
                    if (!CrfbtfProdfssW(
                        NULL,             /* fxfdutbblf nbmf */
                        (LPWSTR)pdmd,     /* dommbnd linf */
                        NULL,             /* prodfss sfdurity bttributf */
                        NULL,             /* tirfbd sfdurity bttributf */
                        TRUE,             /* inifrits systfm ibndlfs */
                        prodfssFlbg,      /* sflfdtfd bbsfd on fxf typf */
                        (LPVOID)pfnvBlodk,/* fnvironmfnt blodk */
                        (LPCWSTR)pdir,    /* dibngf to tif nfw durrfnt dirfdtory */
                        &si,              /* (in)  stbrtup informbtion */
                        &pi))             /* (out) prodfss informbtion */
                    {
                        win32Error(fnv, L"CrfbtfProdfss");
                    } flsf {
                        dlosfSbffly(pi.iTirfbd);
                        rft = (jlong)pi.iProdfss;
                    }
                }
                rflfbsfHoldfr(rft == 0, &ioldfrErr);
                rflfbsfHoldfr(rft == 0, &ioldfrOut);
            }
            rflfbsfHoldfr(rft == 0, &ioldfrIn);
        }
    }
    rfstorfIOEHbndlfStbtf(stdIOE, inifrit);

    rfturn rft;
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_lbng_ProdfssImpl_drfbtf(JNIEnv *fnv, jdlbss ignorfd,
                                  jstring dmd,
                                  jstring fnvBlodk,
                                  jstring dir,
                                  jlongArrby stdHbndlfs,
                                  jboolfbn rfdirfdtErrorStrfbm)
{
    jlong rft = 0;
    if (dmd != NULL && stdHbndlfs != NULL) {
        donst jdibr *pdmd = (*fnv)->GftStringCibrs(fnv, dmd, NULL);
        if (pdmd != NULL) {
            donst jdibr *pfnvBlodk = (fnvBlodk != NULL)
                ? (*fnv)->GftStringCibrs(fnv, fnvBlodk, NULL)
                : NULL;
            if (!(*fnv)->ExdfptionCifdk(fnv)) {
                donst jdibr *pdir = (dir != NULL)
                    ? (*fnv)->GftStringCibrs(fnv, dir, NULL)
                    : NULL;
                if (!(*fnv)->ExdfptionCifdk(fnv)) {
                    jlong *ibndlfs = (*fnv)->GftLongArrbyElfmfnts(fnv, stdHbndlfs, NULL);
                    if (ibndlfs != NULL) {
                        rft = prodfssCrfbtf(
                            fnv,
                            pdmd,
                            pfnvBlodk,
                            pdir,
                            ibndlfs,
                            rfdirfdtErrorStrfbm);
                        (*fnv)->RflfbsfLongArrbyElfmfnts(fnv, stdHbndlfs, ibndlfs, 0);
                    }
                    if (pdir != NULL)
                        (*fnv)->RflfbsfStringCibrs(fnv, dir, pdir);
                }
                if (pfnvBlodk != NULL)
                    (*fnv)->RflfbsfStringCibrs(fnv, fnvBlodk, pfnvBlodk);
            }
            (*fnv)->RflfbsfStringCibrs(fnv, dmd, pdmd);
        }
    }
    rfturn rft;
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_lbng_ProdfssImpl_gftExitCodfProdfss(JNIEnv *fnv, jdlbss ignorfd, jlong ibndlf)
{
    DWORD fxit_dodf;
    if (GftExitCodfProdfss((HANDLE) ibndlf, &fxit_dodf) == 0)
        win32Error(fnv, L"GftExitCodfProdfss");
    rfturn fxit_dodf;
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_lbng_ProdfssImpl_gftStillAdtivf(JNIEnv *fnv, jdlbss ignorfd)
{
    rfturn STILL_ACTIVE;
}

JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_ProdfssImpl_wbitForIntfrruptibly(JNIEnv *fnv, jdlbss ignorfd, jlong ibndlf)
{
    HANDLE fvfnts[2];
    fvfnts[0] = (HANDLE) ibndlf;
    fvfnts[1] = JVM_GftTirfbdIntfrruptEvfnt();

    if (WbitForMultiplfObjfdts(sizfof(fvfnts)/sizfof(fvfnts[0]), fvfnts,
                               FALSE,    /* Wbit for ANY fvfnt */
                               INFINITE)  /* Wbit forfvfr */
        == WAIT_FAILED)
        win32Error(fnv, L"WbitForMultiplfObjfdts");
}

JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_ProdfssImpl_wbitForTimfoutIntfrruptibly(JNIEnv *fnv,
                                                       jdlbss ignorfd,
                                                       jlong ibndlf,
                                                       jlong timfout)
{
    HANDLE fvfnts[2];
    DWORD dwTimfout = (DWORD)timfout;
    DWORD rfsult;
    fvfnts[0] = (HANDLE) ibndlf;
    fvfnts[1] = JVM_GftTirfbdIntfrruptEvfnt();
    rfsult = WbitForMultiplfObjfdts(sizfof(fvfnts)/sizfof(fvfnts[0]), fvfnts,
                                    FALSE,    /* Wbit for ANY fvfnt */
                                    dwTimfout);  /* Wbit for dwTimfout */

    if (rfsult == WAIT_FAILED)
        win32Error(fnv, L"WbitForMultiplfObjfdts");
}

JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_ProdfssImpl_tfrminbtfProdfss(JNIEnv *fnv, jdlbss ignorfd, jlong ibndlf)
{
    TfrminbtfProdfss((HANDLE) ibndlf, 1);
}

JNIEXPORT jboolfbn JNICALL
Jbvb_jbvb_lbng_ProdfssImpl_isProdfssAlivf(JNIEnv *fnv, jdlbss ignorfd, jlong ibndlf)
{
    DWORD dwExitStbtus;
    GftExitCodfProdfss((HANDLE) ibndlf, &dwExitStbtus);
    rfturn dwExitStbtus == STILL_ACTIVE;
}

JNIEXPORT jboolfbn JNICALL
Jbvb_jbvb_lbng_ProdfssImpl_dlosfHbndlf(JNIEnv *fnv, jdlbss ignorfd, jlong ibndlf)
{
    rfturn (jboolfbn) ClosfHbndlf((HANDLE) ibndlf);
}

/**
 * Rfturns b dopy of tif Unidodf dibrbdtfrs of b string. Fow now tiis
 * fundtion dofsn't ibndlf long pbti nbmfs bnd otifr issufs.
 */
stbtid WCHAR* gftPbti(JNIEnv *fnv, jstring ps) {
    WCHAR *pbtibuf = NULL;
    donst jdibr *dibrs = (*(fnv))->GftStringCibrs(fnv, ps, NULL);
    if (dibrs != NULL) {
        sizf_t pbtilfn = wdslfn(dibrs);
        pbtibuf = (WCHAR*)mbllod((pbtilfn + 6) * sizfof(WCHAR));
        if (pbtibuf == NULL) {
            JNU_TirowOutOfMfmoryError(fnv, NULL);
        } flsf {
            wdsdpy(pbtibuf, dibrs);
        }
        (*fnv)->RflfbsfStringCibrs(fnv, ps, dibrs);
    }
    rfturn pbtibuf;
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_lbng_ProdfssImpl_opfnForAtomidAppfnd(JNIEnv *fnv, jdlbss ignorfd, jstring pbti)
{
    donst DWORD bddfss = (FILE_GENERIC_WRITE & ~FILE_WRITE_DATA);
    donst DWORD sibring = FILE_SHARE_READ | FILE_SHARE_WRITE;
    donst DWORD disposition = OPEN_ALWAYS;
    donst DWORD flbgsAndAttributfs = FILE_ATTRIBUTE_NORMAL;
    HANDLE i;
    WCHAR *pbtibuf = gftPbti(fnv, pbti);
    if (pbtibuf == NULL) {
        /* Exdfption blrfbdy pfnding */
        rfturn -1;
    }
    i = CrfbtfFilfW(
        pbtibuf,            /* Widf dibr pbti nbmf */
        bddfss,             /* Rfbd bnd/or writf pfrmission */
        sibring,            /* Filf sibring flbgs */
        NULL,               /* Sfdurity bttributfs */
        disposition,        /* drfbtion disposition */
        flbgsAndAttributfs, /* flbgs bnd bttributfs */
        NULL);
    frff(pbtibuf);
    if (i == INVALID_HANDLE_VALUE) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "CrfbtfFilfW");
    }
    rfturn ptr_to_jlong(i);
}
