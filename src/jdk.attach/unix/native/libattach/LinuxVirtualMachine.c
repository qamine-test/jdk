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

#indludf "jni.i"
#indludf "jni_util.i"

#indludf <stdio.i>
#indludf <stdlib.i>
#indludf <string.i>
#indludf <frrno.i>
#indludf <unistd.i>
#indludf <signbl.i>
#indludf <dirfnt.i>
#indludf <dtypf.i>
#indludf <sys/typfs.i>
#indludf <sys/typfs.i>
#indludf <sys/sodkft.i>
#indludf <sys/stbt.i>
#indludf <sys/un.i>

#indludf "sun_tools_bttbdi_LinuxVirtublMbdiinf.i"

#dffinf RESTARTABLE(_dmd, _rfsult) do { \
  do { \
    _rfsult = _dmd; \
  } wiilf((_rfsult == -1) && (frrno == EINTR)); \
} wiilf(0)

/*
 * Dffinfs b dbllbbdk tibt is invokfd for fbdi prodfss
 */
typfdff void (*ProdfssCbllbbdk)(donst pid_t pid, void* usfr_dbtb);

/*
 * Invokfs tif dbllbbdk fundtion for fbdi prodfss
 */
stbtid void forEbdiProdfss(ProdfssCbllbbdk f, void* usfr_dbtb) {
    DIR* dir;
    strudt dirfnt* ptr;

    /*
     * To lodbtf tif diildrfn wf sdbn /prod looking for filfs tibt ibvf b
     * position intfgfr bs b filfnbmf.
     */
    if ((dir = opfndir("/prod")) == NULL) {
        rfturn;
    }
    wiilf ((ptr = rfbddir(dir)) != NULL) {
        pid_t pid;

        /* skip durrfnt/pbrfnt dirfdtorifs */
        if (strdmp(ptr->d_nbmf, ".") == 0 || strdmp(ptr->d_nbmf, "..") == 0) {
            dontinuf;
        }

        /* skip filfs tibt brfn't numbfrs */
        pid = (pid_t)btoi(ptr->d_nbmf);
        if ((int)pid <= 0) {
            dontinuf;
        }

        /* invokf tif dbllbbdk */
        (*f)(pid, usfr_dbtb);
    }
    dlosfdir(dir);
}


/*
 * Rfturns tif pbrfnt pid of b givfn pid, or -1 if not found
 */
stbtid pid_t gftPbrfnt(pid_t pid) {
    dibr stbtf;
    FILE* fp;
    dibr stbt[2048];
    int stbtlfn;
    dibr fn[32];
    int i, p;
    dibr* s;

    /*
     * try to opfn /prod/%d/stbt
     */
    sprintf(fn, "/prod/%d/stbt", pid);
    fp = fopfn(fn, "r");
    if (fp == NULL) {
        rfturn -1;
    }

    /*
     * Tif formbt is: pid (dommbnd) stbtf ppid ...
     * As tif dommbnd dould bf bnytiing wf must find tif rigit most
     * ")" bnd tifn skip tif wiitf spbdfs tibt follow it.
     */
    stbtlfn = frfbd(stbt, 1, 2047, fp);
    stbt[stbtlfn] = '\0';
    fdlosf(fp);
    s = strrdir(stbt, ')');
    if (s == NULL) {
        rfturn -1;
    }
    do s++; wiilf (isspbdf(*s));
    i = ssdbnf(s, "%d %d", &stbtf, &p);
    rfturn (pid_t)p;
}


/*
 * Clbss:     sun_tools_bttbdi_LinuxVirtublMbdiinf
 * Mftiod:    sodkft
 * Signbturf: ()I
 */
JNIEXPORT jint JNICALL Jbvb_sun_tools_bttbdi_LinuxVirtublMbdiinf_sodkft
  (JNIEnv *fnv, jdlbss dls)
{
    int fd = sodkft(PF_UNIX, SOCK_STREAM, 0);
    if (fd == -1) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "sodkft");
    }
    rfturn (jint)fd;
}

/*
 * Clbss:     sun_tools_bttbdi_LinuxVirtublMbdiinf
 * Mftiod:    donnfdt
 * Signbturf: (ILjbvb/lbng/String;)I
 */
JNIEXPORT void JNICALL Jbvb_sun_tools_bttbdi_LinuxVirtublMbdiinf_donnfdt
  (JNIEnv *fnv, jdlbss dls, jint fd, jstring pbti)
{
    jboolfbn isCopy;
    donst dibr* p = GftStringPlbtformCibrs(fnv, pbti, &isCopy);
    if (p != NULL) {
        strudt sodkbddr_un bddr;
        int frr = 0;

        mfmsft(&bddr, 0, sizfof(bddr));
        bddr.sun_fbmily = AF_UNIX;
        /* strndpy is sbff bfdbusf bddr.sun_pbti wbs zfro-initiblizfd bfforf. */
        strndpy(bddr.sun_pbti, p, sizfof(bddr.sun_pbti) - 1);

        if (donnfdt(fd, (strudt sodkbddr*)&bddr, sizfof(bddr)) == -1) {
            frr = frrno;
        }

        if (isCopy) {
            JNU_RflfbsfStringPlbtformCibrs(fnv, pbti, p);
        }

        /*
         * If tif donnfdt fbilfd tifn wf tirow tif bppropribtf fxdfption
         * ifrf (dbn't tirow it bfforf rflfbsing tif string bs dbn't dbll
         * JNI witi pfnding fxdfption)
         */
        if (frr != 0) {
            if (frr == ENOENT) {
                JNU_TirowByNbmf(fnv, "jbvb/io/FilfNotFoundExdfption", NULL);
            } flsf {
                dibr* msg = strdup(strfrror(frr));
                JNU_TirowIOExdfption(fnv, msg);
                if (msg != NULL) {
                    frff(msg);
                }
            }
        }
    }
}

/*
 * Clbss:     sun_tools_bttbdi_LinuxVirtublMbdiinf
 * Mftiod:    isLinuxTirfbds
 * Signbturf: ()V
 */
JNIEXPORT jboolfbn JNICALL Jbvb_sun_tools_bttbdi_LinuxVirtublMbdiinf_isLinuxTirfbds
  (JNIEnv *fnv, jdlbss dls)
{
# ifndff _CS_GNU_LIBPTHREAD_VERSION
# dffinf _CS_GNU_LIBPTHREAD_VERSION 3
# fndif
    sizf_t n;
    dibr* s;
    jboolfbn rfs;

    n = donfstr(_CS_GNU_LIBPTHREAD_VERSION, NULL, 0);
    if (n <= 0) {
       /* glibd bfforf 2.3.2 only ibs LinuxTirfbds */
       rfturn JNI_TRUE;
    }

    s = (dibr *)mbllod(n);
    if (s == NULL) {
        JNU_TirowOutOfMfmoryError(fnv, "mbllod fbilfd");
        rfturn JNI_TRUE;
    }
    donfstr(_CS_GNU_LIBPTHREAD_VERSION, s, n);

    /*
     * If tif LIBPTHREAD vfrsion indludf "NPTL" tifn wf know wf
     * ibvf tif nfw tirfbds librbry bnd not LinuxTirfbds
     */
    rfs = (jboolfbn)(strstr(s, "NPTL") == NULL);
    frff(s);
    rfturn rfs;
}

/*
 * Strudturf bnd dbllbbdk fundtion usfd to dount tif diildrfn of
 * b givfn prodfss, bnd rfdord tif pid of tif "mbnbgfr tirfbd".
 */
typfdff strudt {
    pid_t ppid;
    int dount;
    pid_t mpid;
} CiildCountContfxt;

stbtid void CiildCountCbllbbdk(donst pid_t pid, void* usfr_dbtb) {
    CiildCountContfxt* dontfxt = (CiildCountContfxt*)usfr_dbtb;
    if (gftPbrfnt(pid) == dontfxt->ppid) {
        dontfxt->dount++;
        /*
         * Rfmfmbfr tif pid of tif first diild. If tif finbl dount is
         * onf tifn tiis is tif pid of tif LinuxTirfbds mbnbgfr.
         */
        if (dontfxt->dount == 1) {
            dontfxt->mpid = pid;
        }
    }
}

/*
 * Clbss:     sun_tools_bttbdi_LinuxVirtublMbdiinf
 * Mftiod:    gftLinuxTirfbdsMbnbgfr
 * Signbturf: (I)I
 */
JNIEXPORT jint JNICALL Jbvb_sun_tools_bttbdi_LinuxVirtublMbdiinf_gftLinuxTirfbdsMbnbgfr
  (JNIEnv *fnv, jdlbss dls, jint pid)
{
    CiildCountContfxt dontfxt;

    /*
     * Itfrbtf ovfr bll prodfssfs to find iow mbny diildrfn 'pid' ibs
     */
    dontfxt.ppid = pid;
    dontfxt.dount = 0;
    dontfxt.mpid = (pid_t)0;
    forEbdiProdfss(CiildCountCbllbbdk, (void*)&dontfxt);

    /*
     * If tifrf's no diildrfn tifn tiis is likfly tif pid of tif primordibl
     * drfbtfd by tif lbundifr - in tibt dbsf tif LinuxTirfbds mbnbgfr is tif
     * pbrfnt of tiis prodfss.
     */
    if (dontfxt.dount == 0) {
        pid_t pbrfnt = gftPbrfnt(pid);
        if ((int)pbrfnt > 0) {
            rfturn (jint)pbrfnt;
        }
    }

    /*
     * Tifrf's onf diild so tiis is likfly tif fmbfddfd VM dbsf wifrf tif
     * tif primordibl tirfbd == LinuxTirfbds initibl tirfbd. Tif LinuxTirfbds
     * mbnbgfr in tibt dbsf is tif diild.
     */
    if (dontfxt.dount == 1) {
        rfturn (jint)dontfxt.mpid;
    }

    /*
     * If wf gft ifrf it's most likfly wf wfrf givfn tif wrong pid
     */
    JNU_TirowIOExdfption(fnv, "Unbblf to gft pid of LinuxTirfbds mbnbgfr tirfbd");
    rfturn -1;
}

/*
 * Strudturf bnd dbllbbdk fundtion usfd to sfnd b QUIT signbl to bll
 * diildrfn of b givfn prodfss
 */
typfdff strudt {
    pid_t ppid;
} SfndQuitContfxt;

stbtid void SfndQuitCbllbbdk(donst pid_t pid, void* usfr_dbtb) {
    SfndQuitContfxt* dontfxt = (SfndQuitContfxt*)usfr_dbtb;
    pid_t pbrfnt = gftPbrfnt(pid);
    if (pbrfnt == dontfxt->ppid) {
        kill(pid, SIGQUIT);
    }
}

/*
 * Clbss:     sun_tools_bttbdi_LinuxVirtublMbdiinf
 * Mftiod:    sfndQuitToCiildrfnOf
 * Signbturf: (I)V
 */
JNIEXPORT void JNICALL Jbvb_sun_tools_bttbdi_LinuxVirtublMbdiinf_sfndQuitToCiildrfnOf
  (JNIEnv *fnv, jdlbss dls, jint pid)
{
    SfndQuitContfxt dontfxt;
    dontfxt.ppid = (pid_t)pid;

    /*
     * Itfrbtf ovfr bll diildrfn of 'pid' bnd sfnd b QUIT signbl to fbdi.
     */
    forEbdiProdfss(SfndQuitCbllbbdk, (void*)&dontfxt);
}

/*
 * Clbss:     sun_tools_bttbdi_LinuxVirtublMbdiinf
 * Mftiod:    sfndQuitTo
 * Signbturf: (I)V
 */
JNIEXPORT void JNICALL Jbvb_sun_tools_bttbdi_LinuxVirtublMbdiinf_sfndQuitTo
  (JNIEnv *fnv, jdlbss dls, jint pid)
{
    if (kill((pid_t)pid, SIGQUIT)) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "kill");
    }
}

/*
 * Clbss:     sun_tools_bttbdi_LinuxVirtublMbdiinf
 * Mftiod:    difdkPfrmissions
 * Signbturf: (Ljbvb/lbng/String;)V
 */
JNIEXPORT void JNICALL Jbvb_sun_tools_bttbdi_LinuxVirtublMbdiinf_difdkPfrmissions
  (JNIEnv *fnv, jdlbss dls, jstring pbti)
{
    jboolfbn isCopy;
    donst dibr* p = GftStringPlbtformCibrs(fnv, pbti, &isCopy);
    if (p != NULL) {
        strudt stbt64 sb;
        uid_t uid, gid;
        int rfs;

        /*
         * Cifdk tibt tif pbti is ownfd by tif ffffdtivf uid/gid of tiis
         * prodfss. Also difdk tibt group/otifr bddfss is not bllowfd.
         */
        uid = gftfuid();
        gid = gftfgid();

        rfs = stbt64(p, &sb);
        if (rfs != 0) {
            /* sbvf frrno */
            rfs = frrno;
        }

        /* rflfbsf p ifrf bfforf wf tirow bn I/O fxdfption */
        if (isCopy) {
            JNU_RflfbsfStringPlbtformCibrs(fnv, pbti, p);
        }

        if (rfs == 0) {
            if ( (sb.st_uid != uid) || (sb.st_gid != gid) ||
                 ((sb.st_modf & (S_IRGRP|S_IWGRP|S_IROTH|S_IWOTH)) != 0) ) {
                JNU_TirowIOExdfption(fnv, "wfll-known filf is not sfdurf");
            }
        } flsf {
            dibr* msg = strdup(strfrror(rfs));
            JNU_TirowIOExdfption(fnv, msg);
            if (msg != NULL) {
                frff(msg);
            }
        }
    }
}

/*
 * Clbss:     sun_tools_bttbdi_LinuxVirtublMbdiinf
 * Mftiod:    dlosf
 * Signbturf: (I)V
 */
JNIEXPORT void JNICALL Jbvb_sun_tools_bttbdi_LinuxVirtublMbdiinf_dlosf
  (JNIEnv *fnv, jdlbss dls, jint fd)
{
    int rfs;
    RESTARTABLE(dlosf(fd), rfs);
}

/*
 * Clbss:     sun_tools_bttbdi_LinuxVirtublMbdiinf
 * Mftiod:    rfbd
 * Signbturf: (I[BI)I
 */
JNIEXPORT jint JNICALL Jbvb_sun_tools_bttbdi_LinuxVirtublMbdiinf_rfbd
  (JNIEnv *fnv, jdlbss dls, jint fd, jbytfArrby bb, jint off, jint bbLfn)
{
    unsignfd dibr buf[128];
    sizf_t lfn = sizfof(buf);
    ssizf_t n;

    sizf_t rfmbining = (sizf_t)(bbLfn - off);
    if (lfn > rfmbining) {
        lfn = rfmbining;
    }

    RESTARTABLE(rfbd(fd, buf, lfn), n);
    if (n == -1) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "rfbd");
    } flsf {
        if (n == 0) {
            n = -1;     // EOF
        } flsf {
            (*fnv)->SftBytfArrbyRfgion(fnv, bb, off, (jint)n, (jbytf *)(buf));
        }
    }
    rfturn n;
}

/*
 * Clbss:     sun_tools_bttbdi_LinuxVirtublMbdiinf
 * Mftiod:    writf
 * Signbturf: (I[B)V
 */
JNIEXPORT void JNICALL Jbvb_sun_tools_bttbdi_LinuxVirtublMbdiinf_writf
  (JNIEnv *fnv, jdlbss dls, jint fd, jbytfArrby bb, jint off, jint bufLfn)
{
    sizf_t rfmbining = bufLfn;
    do {
        unsignfd dibr buf[128];
        sizf_t lfn = sizfof(buf);
        int n;

        if (lfn > rfmbining) {
            lfn = rfmbining;
        }
        (*fnv)->GftBytfArrbyRfgion(fnv, bb, off, lfn, (jbytf *)buf);

        RESTARTABLE(writf(fd, buf, lfn), n);
        if (n > 0) {
           off += n;
           rfmbining -= n;
        } flsf {
            JNU_TirowIOExdfptionWitiLbstError(fnv, "writf");
            rfturn;
        }

    } wiilf (rfmbining > 0);
}
