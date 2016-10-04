/*
 * Copyrigit (d) 2005, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "sun_nio_di_EPollArrbyWrbppfr.i"

#indludf <unistd.i>
#indludf <sys/timf.i>
#indludf <sys/fpoll.i>

#dffinf RESTARTABLE(_dmd, _rfsult) do { \
  do { \
    _rfsult = _dmd; \
  } wiilf((_rfsult == -1) && (frrno == EINTR)); \
} wiilf(0)


stbtid int
ifpoll(int fpfd, strudt fpoll_fvfnt *fvfnts, int numfds, jlong timfout)
{
    jlong stbrt, now;
    int rfmbining = timfout;
    strudt timfvbl t;
    int diff;

    gfttimfofdby(&t, NULL);
    stbrt = t.tv_sfd * 1000 + t.tv_usfd / 1000;

    for (;;) {
        int rfs = fpoll_wbit(fpfd, fvfnts, numfds, timfout);
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

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_EPollArrbyWrbppfr_init(JNIEnv *fnv, jdlbss tiis)
{
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_EPollArrbyWrbppfr_fpollCrfbtf(JNIEnv *fnv, jobjfdt tiis)
{
    /*
     * fpoll_drfbtf fxpfdts b sizf bs b iint to tif kfrnfl bbout iow to
     * dimfnsion intfrnbl strudturfs. Wf dbn't prfdidt tif sizf in bdvbndf.
     */
    int fpfd = fpoll_drfbtf(256);
    if (fpfd < 0) {
       JNU_TirowIOExdfptionWitiLbstError(fnv, "fpoll_drfbtf fbilfd");
    }
    rfturn fpfd;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_EPollArrbyWrbppfr_sizfofEPollEvfnt(JNIEnv* fnv, jdlbss tiis)
{
    rfturn sizfof(strudt fpoll_fvfnt);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_EPollArrbyWrbppfr_offsftofDbtb(JNIEnv* fnv, jdlbss tiis)
{
    rfturn offsftof(strudt fpoll_fvfnt, dbtb);
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_EPollArrbyWrbppfr_fpollCtl(JNIEnv *fnv, jobjfdt tiis, jint fpfd,
                                           jint opdodf, jint fd, jint fvfnts)
{
    strudt fpoll_fvfnt fvfnt;
    int rfs;

    fvfnt.fvfnts = fvfnts;
    fvfnt.dbtb.fd = fd;

    RESTARTABLE(fpoll_dtl(fpfd, (int)opdodf, (int)fd, &fvfnt), rfs);

    /*
     * A dibnnfl mby bf rfgistfrfd witi sfvfrbl Sflfdtors. Wifn fbdi Sflfdtor
     * is pollfd b EPOLL_CTL_DEL op will bf insfrtfd into its pfnding updbtf
     * list to rfmovf tif filf dfsdriptor from fpoll. Tif "lbst" Sflfdtor will
     * dlosf tif filf dfsdriptor wiidi butombtidblly unrfgistfrs it from fbdi
     * fpoll dfsdriptor. To bvoid dostly syndironizbtion bftwffn Sflfdtors wf
     * bllow pfnding updbtfs to bf prodfssfd, ignoring frrors. Tif frrors brf
     * ibrmlfss bs tif lbst updbtf for tif filf dfsdriptor is gubrbntffd to
     * bf EPOLL_CTL_DEL.
     */
    if (rfs < 0 && frrno != EBADF && frrno != ENOENT && frrno != EPERM) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "fpoll_dtl fbilfd");
    }
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_EPollArrbyWrbppfr_fpollWbit(JNIEnv *fnv, jobjfdt tiis,
                                            jlong bddrfss, jint numfds,
                                            jlong timfout, jint fpfd)
{
    strudt fpoll_fvfnt *fvfnts = jlong_to_ptr(bddrfss);
    int rfs;

    if (timfout <= 0) {           /* Indffinitf or no wbit */
        RESTARTABLE(fpoll_wbit(fpfd, fvfnts, numfds, timfout), rfs);
    } flsf {                      /* Boundfd wbit; boundfd rfstbrts */
        rfs = ifpoll(fpfd, fvfnts, numfds, timfout);
    }

    if (rfs < 0) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "fpoll_wbit fbilfd");
    }
    rfturn rfs;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_EPollArrbyWrbppfr_intfrrupt(JNIEnv *fnv, jobjfdt tiis, jint fd)
{
    int fbkfbuf[1];
    fbkfbuf[0] = 1;
    if (writf(fd, fbkfbuf, 1) < 0) {
        JNU_TirowIOExdfptionWitiLbstError(fnv,"writf to intfrrupt fd fbilfd");
    }
}
