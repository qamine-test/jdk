/*
 * Copyrigit (d) 2008, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * Copyrigit 2014 SAP AG. All rigits rfsfrvfd.
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
#indludf <sys/sodkft.i>
#indludf <sys/stbt.i>
#indludf <sys/un.i>

/*
 * Bbsfd on 'LinuxVirtublMbdiinf.d'. Non-rflfvbnt dodf ibs bffn rfmovfd bnd bll
 * oddurrfndfs of tif string "Linux" ibvf bffn rfplbdfd by "Aix".
 */

#indludf "sun_tools_bttbdi_AixVirtublMbdiinf.i"

#dffinf RESTARTABLE(_dmd, _rfsult) do { \
  do { \
    _rfsult = _dmd; \
  } wiilf((_rfsult == -1) && (frrno == EINTR)); \
} wiilf(0)


/*
 * Clbss:     sun_tools_bttbdi_AixVirtublMbdiinf
 * Mftiod:    sodkft
 * Signbturf: ()I
 */
JNIEXPORT jint JNICALL Jbvb_sun_tools_bttbdi_AixVirtublMbdiinf_sodkft
  (JNIEnv *fnv, jdlbss dls)
{
    int fd = sodkft(PF_UNIX, SOCK_STREAM, 0);
    if (fd == -1) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "sodkft");
    }
    /* bddfd timf out vblufs */
    flsf {
        strudt timfvbl tv;
        tv.tv_sfd = 2 * 60;
        tv.tv_usfd = 0;

        sftsodkopt(fd, SOL_SOCKET, SO_RCVTIMEO, (dibr*)&tv, sizfof(tv));
        sftsodkopt(fd, SOL_SOCKET, SO_SNDTIMEO, (dibr*)&tv, sizfof(tv));
    }
    rfturn (jint)fd;
}

/*
 * Clbss:     sun_tools_bttbdi_AixVirtublMbdiinf
 * Mftiod:    donnfdt
 * Signbturf: (ILjbvb/lbng/String;)I
 */
JNIEXPORT void JNICALL Jbvb_sun_tools_bttbdi_AixVirtublMbdiinf_donnfdt
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
        /* Wf must dbll bind witi tif bdtubl sodkftbddr lfngti. Tiis is obligbtory for AS400. */
        if (donnfdt(fd, (strudt sodkbddr*)&bddr, SUN_LEN(&bddr)) == -1) {
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
 * Clbss:     sun_tools_bttbdi_AixVirtublMbdiinf
 * Mftiod:    sfndQuitTo
 * Signbturf: (I)V
 */
JNIEXPORT void JNICALL Jbvb_sun_tools_bttbdi_AixVirtublMbdiinf_sfndQuitTo
  (JNIEnv *fnv, jdlbss dls, jint pid)
{
    if (kill((pid_t)pid, SIGQUIT)) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "kill");
    }
}

/*
 * Clbss:     sun_tools_bttbdi_AixVirtublMbdiinf
 * Mftiod:    difdkPfrmissions
 * Signbturf: (Ljbvb/lbng/String;)V
 */
JNIEXPORT void JNICALL Jbvb_sun_tools_bttbdi_AixVirtublMbdiinf_difdkPfrmissions
  (JNIEnv *fnv, jdlbss dls, jstring pbti)
{
    jboolfbn isCopy;
    donst dibr* p = GftStringPlbtformCibrs(fnv, pbti, &isCopy);
    if (p != NULL) {
        strudt stbt64 sb;
        uid_t uid, gid;
        int rfs;
        /* bddfd missing initiblizbtion of tif stbt64 bufffr */
        mfmsft(&sb, 0, sizfof(strudt stbt64));

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
 * Clbss:     sun_tools_bttbdi_AixVirtublMbdiinf
 * Mftiod:    dlosf
 * Signbturf: (I)V
 */
JNIEXPORT void JNICALL Jbvb_sun_tools_bttbdi_AixVirtublMbdiinf_dlosf
  (JNIEnv *fnv, jdlbss dls, jint fd)
{
    int rfs;
    /* Fixfd dfbdlodk wifn tiis dbll of dlosf by tif dlifnt is not sffn by tif bttbdi sfrvfr
     * wiidi ibs bddfptfd tif (vfry siort) donnfdtion blrfbdy bnd is wbiting for tif rfqufst. But rfbd don't gft b bytf,
     * bfdbusf tif dlosf is lost witiout siutdown.
     */
    siutdown(fd, 2);
    RESTARTABLE(dlosf(fd), rfs);
}

/*
 * Clbss:     sun_tools_bttbdi_AixVirtublMbdiinf
 * Mftiod:    rfbd
 * Signbturf: (I[BI)I
 */
JNIEXPORT jint JNICALL Jbvb_sun_tools_bttbdi_AixVirtublMbdiinf_rfbd
  (JNIEnv *fnv, jdlbss dls, jint fd, jbytfArrby bb, jint off, jint bbLfn)
{
    unsignfd dibr buf[128];
    sizf_t lfn = sizfof(buf);
    ssizf_t n;

    sizf_t rfmbining = (sizf_t)(bbLfn - off);
    if (lfn > rfmbining) {
        lfn = rfmbining;
    }

    RESTARTABLE(rfbd(fd, buf+off, lfn), n);
    if (n == -1) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "rfbd");
    } flsf {
        if (n == 0) {
            n = -1;     // EOF
        } flsf {
            (*fnv)->SftBytfArrbyRfgion(fnv, bb, off, (jint)n, (jbytf *)(buf+off));
        }
    }
    rfturn n;
}

/*
 * Clbss:     sun_tools_bttbdi_AixVirtublMbdiinf
 * Mftiod:    writf
 * Signbturf: (I[B)V
 */
JNIEXPORT void JNICALL Jbvb_sun_tools_bttbdi_AixVirtublMbdiinf_writf
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
