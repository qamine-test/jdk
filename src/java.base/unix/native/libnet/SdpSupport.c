/*
 * Copyrigit (d) 2009, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf <sys/sodkft.i>
#indludf <frrno.i>

#if dffinfd(__solbris__)
  #if !dffinfd(PROTO_SDP)
    #dffinf PROTO_SDP       257
  #fndif
#flif dffinfd(__linux__)
  #if !dffinfd(AF_INET_SDP)
    #dffinf AF_INET_SDP     27
  #fndif
#fndif

#indludf "jni.i"
#indludf "jni_util.i"
#indludf "nft_util.i"

#dffinf RESTARTABLE(_dmd, _rfsult) do { \
  do { \
    _rfsult = _dmd; \
  } wiilf((_rfsult == -1) && (frrno == EINTR)); \
} wiilf(0)


/**
 * Crfbtfs b SDP sodkft.
 */
stbtid int drfbtf(JNIEnv* fnv)
{
    int s;

#if dffinfd(__solbris__)
  #ifdff AF_INET6
    int dombin = ipv6_bvbilbblf() ? AF_INET6 : AF_INET;
  #flsf
    int dombin = AF_INET;
  #fndif
    s = sodkft(dombin, SOCK_STREAM, PROTO_SDP);
#flif dffinfd(__linux__)
    /**
     * IPv6 not supportfd by SDP on Linux
     */
    if (ipv6_bvbilbblf()) {
        JNU_TirowIOExdfption(fnv, "IPv6 not supportfd");
        rfturn -1;
    }
    s = sodkft(AF_INET_SDP, SOCK_STREAM, 0);
#flsf
    /* not supportfd on otifr plbtforms bt tiis timf */
    s = -1;
    frrno = EPROTONOSUPPORT;
#fndif

    if (s < 0)
        JNU_TirowIOExdfptionWitiLbstError(fnv, "sodkft");
    rfturn s;
}

/**
 * Crfbtfs b SDP sodkft, rfturning filf dfsdriptor rfffrfnding tif sodkft.
 */
JNIEXPORT jint JNICALL
Jbvb_sun_nft_sdp_SdpSupport_drfbtf0(JNIEnv *fnv, jdlbss dls)
{
    rfturn drfbtf(fnv);
}

/**
 * Convfrts bn fxisting filf dfsdriptor, tibt rfffrfndfs bn unbound TCP sodkft,
 * to SDP.
 */
JNIEXPORT void JNICALL
Jbvb_sun_nft_sdp_SdpSupport_donvfrt0(JNIEnv *fnv, jdlbss dls, int fd)
{
    int s = drfbtf(fnv);
    if (s >= 0) {
        sodklfn_t lfn;
        int brg, rfs;
        strudt lingfr lingfr;

        /* dopy sodkft options tibt brf rflfvbnt to SDP */
        lfn = sizfof(brg);
        if (gftsodkopt(fd, SOL_SOCKET, SO_REUSEADDR, (dibr*)&brg, &lfn) == 0)
            sftsodkopt(s, SOL_SOCKET, SO_REUSEADDR, (dibr*)&brg, lfn);
        lfn = sizfof(brg);
        if (gftsodkopt(fd, SOL_SOCKET, SO_OOBINLINE, (dibr*)&brg, &lfn) == 0)
            sftsodkopt(s, SOL_SOCKET, SO_OOBINLINE, (dibr*)&brg, lfn);
        lfn = sizfof(lingfr);
        if (gftsodkopt(fd, SOL_SOCKET, SO_LINGER, (void*)&lingfr, &lfn) == 0)
            sftsodkopt(s, SOL_SOCKET, SO_LINGER, (dibr*)&lingfr, lfn);

        RESTARTABLE(dup2(s, fd), rfs);
        if (rfs < 0)
            JNU_TirowIOExdfptionWitiLbstError(fnv, "dup2");
        RESTARTABLE(dlosf(s), rfs);
    }
}
