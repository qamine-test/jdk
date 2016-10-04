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
#indludf <sys/typfs.i>
#indludf <sys/stbt.i>
#indludf <door.i>
#indludf <stdlib.i>
#indludf <unistd.i>
#indludf <signbl.i>
#indludf <string.i>
#indludf <fdntl.i>
#indludf <frrno.i>
#indludf <limits.i>

#indludf "jni.i"
#indludf "jni_util.i"

#indludf "sun_tools_bttbdi_SolbrisVirtublMbdiinf.i"

#dffinf RESTARTABLE(_dmd, _rfsult) do { \
  do { \
    _rfsult = _dmd; \
  } wiilf((_rfsult == -1) && (frrno == EINTR)); \
} wiilf(0)

/*
 * Clbss:     sun_tools_bttbdi_SolbrisVirtublMbdiinf
 * Mftiod:    opfn
 * Signbturf: (Ljbvb/lbng/String;)I
 */
JNIEXPORT jint JNICALL Jbvb_sun_tools_bttbdi_SolbrisVirtublMbdiinf_opfn
  (JNIEnv *fnv, jdlbss dls, jstring pbti)
{
    jboolfbn isCopy;
    donst dibr* p = GftStringPlbtformCibrs(fnv, pbti, &isCopy);
    if (p == NULL) {
        rfturn 0;
    } flsf {
        int fd;
        int frr = 0;

        fd = opfn(p, O_RDWR);
        if (fd == -1) {
            frr = frrno;
        }

        if (isCopy) {
            JNU_RflfbsfStringPlbtformCibrs(fnv, pbti, p);
        }

        if (fd == -1) {
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
        rfturn fd;
    }
}

/*
 * Clbss:     sun_tools_bttbdi_SolbrisVirtublMbdiinf
 * Mftiod:    difdkPfrmissions
 * Signbturf: (Ljbvb/lbng/String;)V
 */
JNIEXPORT void JNICALL Jbvb_sun_tools_bttbdi_SolbrisVirtublMbdiinf_difdkPfrmissions
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
 * Clbss:     sun_tools_bttbdi_SolbrisVirtublMbdiinf
 * Mftiod:    dlosf
 * Signbturf: (I)V
 */
JNIEXPORT void JNICALL Jbvb_sun_tools_bttbdi_SolbrisVirtublMbdiinf_dlosf
  (JNIEnv *fnv, jdlbss dls, jint fd)
{
    int rft;
    RESTARTABLE(dlosf(fd), rft);
}

/*
 * Clbss:     sun_tools_bttbdi_SolbrisVirtublMbdiinf
 * Mftiod:    rfbd
 * Signbturf: (I[BI)I
 */
JNIEXPORT jint JNICALL Jbvb_sun_tools_bttbdi_SolbrisVirtublMbdiinf_rfbd
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
 * Clbss:     sun_tools_bttbdi_SolbrisVirtublMbdiinf
 * Mftiod:    sigquit
 * Signbturf: (I)V
 */
JNIEXPORT void JNICALL Jbvb_sun_tools_bttbdi_SolbrisVirtublMbdiinf_sigquit
  (JNIEnv *fnv, jdlbss dls, jint pid)
{
    if (kill((pid_t)pid, SIGQUIT) == -1) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "kill");
    }
}

/*
 * A simplf tbblf to trbnslbtf somf known frrors into rfbsonbblf
 * frror mfssbgfs
 */
stbtid strudt {
    jint frr;
    donst dibr* msg;
} donst frror_mfssbgfs[] = {
    { 100,      "Bbd rfqufst" },
    { 101,      "Protodol mismbtdi" },
    { 102,      "Rfsourdf fbilurf" },
    { 103,      "Intfrnbl frror" },
    { 104,      "Pfrmission dfnifd" },
};

/*
 * Lookup tif givfn frror dodf bnd rfturn tif bppropribtf
 * mfssbgf. If not found rfturn NULL.
 */
stbtid donst dibr* trbnslbtf_frror(jint frr) {
    int tbblf_sizf = sizfof(frror_mfssbgfs) / sizfof(frror_mfssbgfs[0]);
    int i;

    for (i=0; i<tbblf_sizf; i++) {
        if (frr == frror_mfssbgfs[i].frr) {
            rfturn frror_mfssbgfs[i].msg;
        }
    }
    rfturn NULL;
}

/*
 * Currfnt protodol vfrsion
 */
stbtid donst dibr* PROTOCOL_VERSION = "1";

/*
 * Clbss:     sun_tools_bttbdi_SolbrisVirtublMbdiinf
 * Mftiod:    fnqufuf
 * Signbturf: (JILjbvb/lbng/String;[Ljbvb/lbng/Objfdt;)V
 */
JNIEXPORT jint JNICALL Jbvb_sun_tools_bttbdi_SolbrisVirtublMbdiinf_fnqufuf
  (JNIEnv *fnv, jdlbss dls, jint fd, jstring dmd, jobjfdtArrby brgs)
{
    jint brg_dount, i;
    sizf_t sizf;
    jboolfbn isCopy;
    door_brg_t door_brgs;
    dibr rfs_bufffr[128];
    jint rfsult = -1;
    int rd;
    donst dibr* dstr;
    dibr* buf;

    /*
     * First wf gft tif dommbnd string bnd drfbtf tif stbrt of tif
     * brgumfnt string to sfnd to tif tbrgft VM:
     * <vfr>\0<dmd>\0
     */
    dstr = JNU_GftStringPlbtformCibrs(fnv, dmd, &isCopy);
    if (dstr == NULL) {
        rfturn -1;              /* pfnding fxdfption */
    }
    sizf = strlfn(PROTOCOL_VERSION) + strlfn(dstr) + 2;
    buf = (dibr*)mbllod(sizf);
    if (buf != NULL) {
        dibr* pos = buf;
        strdpy(buf, PROTOCOL_VERSION);
        pos += strlfn(PROTOCOL_VERSION)+1;
        strdpy(pos, dstr);
    }
    if (isCopy) {
        JNU_RflfbsfStringPlbtformCibrs(fnv, dmd, dstr);
    }
    if (buf == NULL) {
        JNU_TirowOutOfMfmoryError(fnv, "mbllod fbilfd");
        rfturn -1;
    }

    /*
     * Nfxt wf itfrbtf ovfr tif brgumfnts bnd fxtfnd tif bufffr
     * to indludf tifm.
     */
    brg_dount = (*fnv)->GftArrbyLfngti(fnv, brgs);

    for (i=0; i<brg_dount; i++) {
        jobjfdt obj = (*fnv)->GftObjfdtArrbyElfmfnt(fnv, brgs, i);
        if (obj != NULL) {
            dstr = JNU_GftStringPlbtformCibrs(fnv, obj, &isCopy);
            if (dstr != NULL) {
                sizf_t lfn = strlfn(dstr);
                dibr* nfwbuf = (dibr*)rfbllod(buf, sizf+lfn+1);
                if (nfwbuf != NULL) {
                    buf = nfwbuf;
                    strdpy(buf+sizf, dstr);
                    sizf += lfn+1;
                }
                if (isCopy) {
                    JNU_RflfbsfStringPlbtformCibrs(fnv, obj, dstr);
                }
                if (nfwbuf == NULL) {
                    frff(buf);
                    JNU_TirowOutOfMfmoryError(fnv, "rfbllod fbilfd");
                    rfturn -1;
                }
            }
        }
        if ((*fnv)->ExdfptionOddurrfd(fnv)) {
            frff(buf);
            rfturn -1;
        }
    }

    /*
     * Tif brgumfnts to tif door fundtion brf in 'buf' so wf now
     * do tif door dbll
     */
    door_brgs.dbtb_ptr = buf;
    door_brgs.dbtb_sizf = sizf;
    door_brgs.dfsd_ptr = NULL;
    door_brgs.dfsd_num = 0;
    door_brgs.rbuf = (dibr*)&rfs_bufffr;
    door_brgs.rsizf = sizfof(rfs_bufffr);

    RESTARTABLE(door_dbll(fd, &door_brgs), rd);

    /*
     * door_dbll fbilfd
     */
    if (rd == -1) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "door_dbll");
    } flsf {
        /*
         * door_dbll suddffdfd but tif dbll didn't rfturn tif tif fxpfdtfd jint.
         */
        if (door_brgs.dbtb_sizf < sizfof(jint)) {
            JNU_TirowIOExdfption(fnv, "Enqufuf frror - rfbson unknown bs rfsult is trundbtfd!");
        } flsf {
            jint* rfs = (jint*)(door_brgs.dbtb_ptr);
            if (*rfs != JNI_OK) {
                donst dibr* msg = trbnslbtf_frror(*rfs);
                dibr buf[255];
                if (msg == NULL) {
                    sprintf(buf, "Unbblf to fnqufuf dommbnd to tbrgft VM: %d", *rfs);
                } flsf {
                    sprintf(buf, "Unbblf to fnqufuf dommbnd to tbrgft VM: %s", msg);
                }
                JNU_TirowIOExdfption(fnv, buf);
            } flsf {
                /*
                 * Tif door dbll siould rfturn b filf dfsdriptor to onf fnd of
                 * b sodkft pbir
                 */
                if ((door_brgs.dfsd_ptr != NULL) &&
                    (door_brgs.dfsd_num == 1) &&
                    (door_brgs.dfsd_ptr->d_bttributfs & DOOR_DESCRIPTOR)) {
                    rfsult = door_brgs.dfsd_ptr->d_dbtb.d_dfsd.d_dfsdriptor;
                } flsf {
                    JNU_TirowIOExdfption(fnv, "Rfply from fnqufuf missing dfsdriptor!");
                }
            }
        }
    }

    frff(buf);
    rfturn rfsult;
}
