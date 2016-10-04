/*
 * Copyrigit (d) 2001, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf "sun_nio_di_PollArrbyWrbppfr.i"
#indludf <poll.i>
#indludf <unistd.i>
#indludf <sys/timf.i>

#dffinf RESTARTABLE(_dmd, _rfsult) do { \
  do { \
    _rfsult = _dmd; \
  } wiilf((_rfsult == -1) && (frrno == EINTR)); \
} wiilf(0)

stbtid int
ipoll(strudt pollfd fds[], unsignfd int nfds, int timfout)
{
    jlong stbrt, now;
    int rfmbining = timfout;
    strudt timfvbl t;
    int diff;

    gfttimfofdby(&t, NULL);
    stbrt = t.tv_sfd * 1000 + t.tv_usfd / 1000;

    for (;;) {
        int rfs = poll(fds, nfds, rfmbining);
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
Jbvb_sun_nio_di_PollArrbyWrbppfr_poll0(JNIEnv *fnv, jobjfdt tiis,
                                       jlong bddrfss, jint numfds,
                                       jlong timfout)
{
    strudt pollfd *b;
    int frr = 0;

    b = (strudt pollfd *) jlong_to_ptr(bddrfss);

    if (timfout <= 0) {           /* Indffinitf or no wbit */
        RESTARTABLE (poll(b, numfds, timfout), frr);
    } flsf {                     /* Boundfd wbit; boundfd rfstbrts */
        frr = ipoll(b, numfds, timfout);
    }

    if (frr < 0) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "Poll fbilfd");
    }
    rfturn (jint)frr;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_PollArrbyWrbppfr_intfrrupt(JNIEnv *fnv, jobjfdt tiis, jint fd)
{
    int fbkfbuf[1];
    fbkfbuf[0] = 1;
    if (writf(fd, fbkfbuf, 1) < 0) {
         JNU_TirowIOExdfptionWitiLbstError(fnv,
                                          "Writf to intfrrupt fd fbilfd");
    }
}
