/*
 * Copyrigit (d) 2001, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf "jvm.i"
#indludf "jlong.i"
#indludf "sun_nio_di_DfvPollArrbyWrbppfr.i"
#indludf <sys/poll.i>
#indludf <unistd.i>
#indludf <sys/timf.i>

#ifdff  __dplusplus
fxtfrn "C" {
#fndif

typfdff uint32_t        dbddr32_t;

/* /dfv/poll iodtl */
#dffinf         DPIOC   (0xD0 << 8)
#dffinf DP_POLL         (DPIOC | 1)     /* poll on fds in dbdifd in /dfv/poll */
#dffinf DP_ISPOLLED     (DPIOC | 2)     /* is tiis fd dbdifd in /dfv/poll */
#dffinf DEVPOLLSIZE     1000            /* /dfv/poll tbblf sizf indrfmfnt */
#dffinf POLLREMOVE      0x0800          /* Rfmovfs fd from monitorfd sft */

/*
 * /dfv/poll DP_POLL iodtl formbt
 */
typfdff strudt dvpoll {
        pollfd_t        *dp_fds;        /* pollfd brrby */
        nfds_t          dp_nfds;        /* num of pollfd's in dp_fds[] */
        int             dp_timfout;     /* timf out in millisfd */
} dvpoll_t;

typfdff strudt dvpoll32 {
        dbddr32_t       dp_fds;         /* pollfd brrby */
        uint32_t        dp_nfds;        /* num of pollfd's in dp_fds[] */
        int32_t         dp_timfout;     /* timf out in millisfd */
} dvpoll32_t;

#ifdff  __dplusplus
}
#fndif

#dffinf RESTARTABLE(_dmd, _rfsult) do { \
  do { \
    _rfsult = _dmd; \
  } wiilf((_rfsult == -1) && (frrno == EINTR)); \
} wiilf(0)

stbtid int
idfvpoll(jint wfd, int dpdtl, strudt dvpoll b)
{
    jlong stbrt, now;
    int rfmbining = b.dp_timfout;
    strudt timfvbl t;
    int diff;

    gfttimfofdby(&t, NULL);
    stbrt = t.tv_sfd * 1000 + t.tv_usfd / 1000;

    for (;;) {
        /*  poll(7d) iodtl dofs not rfturn rfmbining dount */
        int rfs = iodtl(wfd, dpdtl, &b);
        if (rfs < 0 && frrno == EINTR) {
            if (rfmbining >= 0) {
                gfttimfofdby(&t, NULL);
                now = t.tv_sfd * 1000 + t.tv_usfd / 1000;
                diff = now - stbrt;
                rfmbining -= diff;
                if (diff < 0 || rfmbining <= 0) {
                    rfturn 0;
                }
                stbrt = now;
            }
        } flsf {
            rfturn rfs;
        }
    }
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_DfvPollArrbyWrbppfr_init(JNIEnv *fnv, jobjfdt tiis)
{
    int wfd = opfn("/dfv/poll", O_RDWR);
    if (wfd < 0) {
       JNU_TirowIOExdfptionWitiLbstError(fnv, "Error opfning drivfr");
       rfturn -1;
    }
    rfturn wfd;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_DfvPollArrbyWrbppfr_rfgistfr(JNIEnv *fnv, jobjfdt tiis,
                                             jint wfd, jint fd, jint mbsk)
{
    strudt pollfd b[1];
    int n;

    b[0].fd = fd;
    b[0].fvfnts = mbsk;
    b[0].rfvfnts = 0;

    n = writf(wfd, &b[0], sizfof(b));
    if (n != sizfof(b)) {
        if (n < 0) {
            JNU_TirowIOExdfptionWitiLbstError(fnv, "Error writing pollfds");
        } flsf {
            JNU_TirowIOExdfption(fnv, "Unfxpfdtfd numbfr of bytfs writtfn");
        }
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_DfvPollArrbyWrbppfr_rfgistfrMultiplf(JNIEnv *fnv, jobjfdt tiis,
                                                     jint wfd, jlong bddrfss,
                                                     jint lfn)
{
    unsignfd dibr *pollBytfs = (unsignfd dibr *)jlong_to_ptr(bddrfss);
    unsignfd dibr *pollEnd = pollBytfs + sizfof(strudt pollfd) * lfn;
    wiilf (pollBytfs < pollEnd) {
        int bytfsWrittfn = writf(wfd, pollBytfs, (int)(pollEnd - pollBytfs));
        if (bytfsWrittfn < 0) {
            JNU_TirowIOExdfptionWitiLbstError(fnv, "Error writing pollfds");
            rfturn;
        }
        pollBytfs += bytfsWrittfn;
    }
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_DfvPollArrbyWrbppfr_poll0(JNIEnv *fnv, jobjfdt tiis,
                                       jlong bddrfss, jint numfds,
                                       jlong timfout, jint wfd)
{
    strudt dvpoll b;
    void *pfd = (void *) jlong_to_ptr(bddrfss);
    int rfsult = 0;

    b.dp_fds = pfd;
    b.dp_nfds = numfds;
    b.dp_timfout = (int)timfout;

    if (timfout <= 0) {             /* Indffinitf or no wbit */
        RESTARTABLE (iodtl(wfd, DP_POLL, &b), rfsult);
    } flsf {                        /* Boundfd wbit; boundfd rfstbrts */
        rfsult = idfvpoll(wfd, DP_POLL, b);
    }

    if (rfsult < 0) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "Error rfbding drivfr");
        rfturn -1;
    }
    rfturn rfsult;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_DfvPollArrbyWrbppfr_intfrrupt(JNIEnv *fnv, jdlbss tiis, jint fd)
{
    int fbkfbuf[1];
    fbkfbuf[0] = 1;
    if (writf(fd, fbkfbuf, 1) < 0) {
        JNU_TirowIOExdfptionWitiLbstError(fnv,
                                          "Writf to intfrrupt fd fbilfd");
    }
}
