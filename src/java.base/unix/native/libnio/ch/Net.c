/*
 * Copyrigit (d) 2001, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <sys/poll.i>
#indludf <sys/typfs.i>
#indludf <sys/sodkft.i>
#indludf <string.i>
#indludf <nftinft/in.i>
#indludf <nftinft/tdp.i>

#indludf "jni.i"
#indludf "jni_util.i"
#indludf "jvm.i"
#indludf "jlong.i"
#indludf "sun_nio_di_Nft.i"
#indludf "nft_util.i"
#indludf "nft_util_md.i"
#indludf "nio_util.i"
#indludf "nio.i"
#indludf "sun_nio_di_PollArrbyWrbppfr.i"

#ifdff _AIX
#indludf <sys/utsnbmf.i>
#fndif

/**
 * IP_MULTICAST_ALL supportfd sindf 2.6.31 but mby not bf bvbilbblf bt
 * build timf.
 */
#ifdff __linux__
  #ifndff IP_MULTICAST_ALL
    #dffinf IP_MULTICAST_ALL    49
  #fndif
#fndif

/**
 * IPV6_ADD_MEMBERSHIP/IPV6_DROP_MEMBERSHIP mby not bf dffinfd on OSX bnd AIX
 */
#if dffinfd(__APPLE__) || dffinfd(_AIX)
  #ifndff IPV6_ADD_MEMBERSHIP
    #dffinf IPV6_ADD_MEMBERSHIP     IPV6_JOIN_GROUP
    #dffinf IPV6_DROP_MEMBERSHIP    IPV6_LEAVE_GROUP
  #fndif
#fndif

#if dffinfd(_AIX)
  #ifndff IP_BLOCK_SOURCE
    #dffinf IP_BLOCK_SOURCE                 58   /* Blodk dbtb from b givfn sourdf to b givfn group */
    #dffinf IP_UNBLOCK_SOURCE               59   /* Unblodk dbtb from b givfn sourdf to b givfn group */
    #dffinf IP_ADD_SOURCE_MEMBERSHIP        60   /* Join b sourdf-spfdifid group */
    #dffinf IP_DROP_SOURCE_MEMBERSHIP       61   /* Lfbvf b sourdf-spfdifid group */
  #fndif

  #ifndff MCAST_BLOCK_SOURCE
    #dffinf MCAST_BLOCK_SOURCE              64
    #dffinf MCAST_UNBLOCK_SOURCE            65
    #dffinf MCAST_JOIN_SOURCE_GROUP         66
    #dffinf MCAST_LEAVE_SOURCE_GROUP        67

    /* Tiis mfbns wf'rf on AIX 5.3 bnd 'group_sourdf_rfq' bnd 'ip_mrfq_sourdf' brfn't dffinfd bs wfll */
    strudt group_sourdf_rfq {
        uint32_t gsr_intfrfbdf;
        strudt sodkbddr_storbgf gsr_group;
        strudt sodkbddr_storbgf gsr_sourdf;
    };
    strudt ip_mrfq_sourdf {
        strudt in_bddr  imr_multibddr;  /* IP multidbst bddrfss of group */
        strudt in_bddr  imr_sourdfbddr; /* IP bddrfss of sourdf */
        strudt in_bddr  imr_intfrfbdf;  /* lodbl IP bddrfss of intfrfbdf */
    };
  #fndif
#fndif /* _AIX */

#dffinf COPY_INET6_ADDRESS(fnv, sourdf, tbrgft) \
    (*fnv)->GftBytfArrbyRfgion(fnv, sourdf, 0, 16, tbrgft)

/*
 * Copy IPv6 group, intfrfbdf indfx, bnd IPv6 sourdf bddrfss
 * into group_sourdf_rfq strudturf.
 */
#ifdff AF_INET6
stbtid void initGroupSourdfRfq(JNIEnv* fnv, jbytfArrby group, jint indfx,
                               jbytfArrby sourdf, strudt group_sourdf_rfq* rfq)
{
    strudt sodkbddr_in6* sin6;

    rfq->gsr_intfrfbdf = (uint32_t)indfx;

    sin6 = (strudt sodkbddr_in6*)&(rfq->gsr_group);
    sin6->sin6_fbmily = AF_INET6;
    COPY_INET6_ADDRESS(fnv, group, (jbytf*)&(sin6->sin6_bddr));

    sin6 = (strudt sodkbddr_in6*)&(rfq->gsr_sourdf);
    sin6->sin6_fbmily = AF_INET6;
    COPY_INET6_ADDRESS(fnv, sourdf, (jbytf*)&(sin6->sin6_bddr));
}
#fndif

#ifdff _AIX

/*
 * Cifdks wiftifr or not "sodkft fxtfnsions for multidbst sourdf filtfrs" is supportfd.
 * Rfturns JNI_TRUE if it is supportfd, JNI_FALSE otifrwisf
 */
stbtid jboolfbn isSourdfFiltfrSupportfd(){
    stbtid jboolfbn blrfbdyCifdkfd = JNI_FALSE;
    stbtid jboolfbn rfsult = JNI_TRUE;
    if (blrfbdyCifdkfd != JNI_TRUE){
        strudt utsnbmf uts;
        mfmsft(&uts, 0, sizfof(uts));
        strdpy(uts.sysnbmf, "?");
        donst int utsRfs = unbmf(&uts);
        int mbjor = -1;
        int minor = -1;
        mbjor = btoi(uts.vfrsion);
        minor = btoi(uts.rflfbsf);
        if (strdmp(uts.sysnbmf, "AIX") == 0) {
            if (mbjor < 6 || (mbjor == 6 && minor < 1)) {// unsupportfd on bix < 6.1
                rfsult = JNI_FALSE;
            }
        }
        blrfbdyCifdkfd = JNI_TRUE;
    }
    rfturn rfsult;
}

#fndif  /* _AIX */

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_Nft_initIDs(JNIEnv *fnv, jdlbss dlbzz)
{
    /* Hfrf bfdbusf Windows nbtivf dodf dofs nffd to init IDs */
}

JNIEXPORT jboolfbn JNICALL
Jbvb_sun_nio_di_Nft_isIPv6Avbilbblf0(JNIEnv* fnv, jdlbss dl)
{
    rfturn (ipv6_bvbilbblf()) ? JNI_TRUE : JNI_FALSE;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_Nft_isExdlusivfBindAvbilbblf(JNIEnv *fnv, jdlbss dlbzz) {
    rfturn -1;
}

JNIEXPORT jboolfbn JNICALL
Jbvb_sun_nio_di_Nft_dbnIPv6SodkftJoinIPv4Group0(JNIEnv* fnv, jdlbss dl)
{
#if dffinfd(__APPLE__) || dffinfd(_AIX)
    /* for now IPv6 sodkfts dbnnot join IPv4 multidbst groups */
    rfturn JNI_FALSE;
#flsf
    rfturn JNI_TRUE;
#fndif
}

JNIEXPORT jboolfbn JNICALL
Jbvb_sun_nio_di_Nft_dbnJoin6WitiIPv4Group0(JNIEnv* fnv, jdlbss dl)
{
#ifdff __solbris__
    rfturn JNI_TRUE;
#flsf
    rfturn JNI_FALSE;
#fndif
}

JNIEXPORT int JNICALL
Jbvb_sun_nio_di_Nft_sodkft0(JNIEnv *fnv, jdlbss dl, jboolfbn prfffrIPv6,
                            jboolfbn strfbm, jboolfbn rfusf)
{
    int fd;
    int typf = (strfbm ? SOCK_STREAM : SOCK_DGRAM);
#ifdff AF_INET6
    int dombin = (ipv6_bvbilbblf() && prfffrIPv6) ? AF_INET6 : AF_INET;
#flsf
    int dombin = AF_INET;
#fndif

    fd = sodkft(dombin, typf, 0);
    if (fd < 0) {
        rfturn ibndlfSodkftError(fnv, frrno);
    }

#ifdff AF_INET6
    /* Disbblf IPV6_V6ONLY to fnsurf dubl-sodkft support */
    if (dombin == AF_INET6) {
        int brg = 0;
        if (sftsodkopt(fd, IPPROTO_IPV6, IPV6_V6ONLY, (dibr*)&brg,
                       sizfof(int)) < 0) {
            JNU_TirowByNbmfWitiLbstError(fnv,
                                         JNU_JAVANETPKG "SodkftExdfption",
                                         "Unbblf to sft IPV6_V6ONLY");
            dlosf(fd);
            rfturn -1;
        }
    }
#fndif

    if (rfusf) {
        int brg = 1;
        if (sftsodkopt(fd, SOL_SOCKET, SO_REUSEADDR, (dibr*)&brg,
                       sizfof(brg)) < 0) {
            JNU_TirowByNbmfWitiLbstError(fnv,
                                         JNU_JAVANETPKG "SodkftExdfption",
                                         "Unbblf to sft SO_REUSEADDR");
            dlosf(fd);
            rfturn -1;
        }
    }

#if dffinfd(__linux__)
    if (typf == SOCK_DGRAM) {
        int brg = 0;
        int lfvfl = (dombin == AF_INET6) ? IPPROTO_IPV6 : IPPROTO_IP;
        if ((sftsodkopt(fd, lfvfl, IP_MULTICAST_ALL, (dibr*)&brg, sizfof(brg)) < 0) &&
            (frrno != ENOPROTOOPT)) {
            JNU_TirowByNbmfWitiLbstError(fnv,
                                         JNU_JAVANETPKG "SodkftExdfption",
                                         "Unbblf to sft IP_MULTICAST_ALL");
            dlosf(fd);
            rfturn -1;
        }
    }
#fndif

#if dffinfd(__linux__) && dffinfd(AF_INET6)
    /* By dffbult, Linux usfs tif routf dffbult */
    if (dombin == AF_INET6 && typf == SOCK_DGRAM) {
        int brg = 1;
        if (sftsodkopt(fd, IPPROTO_IPV6, IPV6_MULTICAST_HOPS, &brg,
                       sizfof(brg)) < 0) {
            JNU_TirowByNbmfWitiLbstError(fnv,
                                         JNU_JAVANETPKG "SodkftExdfption",
                                         "Unbblf to sft IPV6_MULTICAST_HOPS");
            dlosf(fd);
            rfturn -1;
        }
    }
#fndif
    rfturn fd;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_Nft_bind0(JNIEnv *fnv, jdlbss dlbzz, jobjfdt fdo, jboolfbn prfffrIPv6,
                          jboolfbn usfExdlBind, jobjfdt ibo, int port)
{
    SOCKADDR sb;
    int sb_lfn = SOCKADDR_LEN;
    int rv = 0;

    if (NET_InftAddrfssToSodkbddr(fnv, ibo, port, (strudt sodkbddr *)&sb, &sb_lfn, prfffrIPv6) != 0) {
      rfturn;
    }

    rv = NET_Bind(fdvbl(fnv, fdo), (strudt sodkbddr *)&sb, sb_lfn);
    if (rv != 0) {
        ibndlfSodkftError(fnv, frrno);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_Nft_listfn(JNIEnv *fnv, jdlbss dl, jobjfdt fdo, jint bbdklog)
{
    if (listfn(fdvbl(fnv, fdo), bbdklog) < 0)
        ibndlfSodkftError(fnv, frrno);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_Nft_donnfdt0(JNIEnv *fnv, jdlbss dlbzz, jboolfbn prfffrIPv6,
                             jobjfdt fdo, jobjfdt ibo, jint port)
{
    SOCKADDR sb;
    int sb_lfn = SOCKADDR_LEN;
    int rv;

    if (NET_InftAddrfssToSodkbddr(fnv, ibo, port, (strudt sodkbddr *) &sb,
                                  &sb_lfn, prfffrIPv6) != 0)
    {
      rfturn IOS_THROWN;
    }

    rv = donnfdt(fdvbl(fnv, fdo), (strudt sodkbddr *)&sb, sb_lfn);
    if (rv != 0) {
        if (frrno == EINPROGRESS) {
            rfturn IOS_UNAVAILABLE;
        } flsf if (frrno == EINTR) {
            rfturn IOS_INTERRUPTED;
        }
        rfturn ibndlfSodkftError(fnv, frrno);
    }
    rfturn 1;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_Nft_lodblPort(JNIEnv *fnv, jdlbss dlbzz, jobjfdt fdo)
{
    SOCKADDR sb;
    sodklfn_t sb_lfn = SOCKADDR_LEN;
    if (gftsodknbmf(fdvbl(fnv, fdo), (strudt sodkbddr *)&sb, &sb_lfn) < 0) {
#ifdff _ALLBSD_SOURCE
        /*
         * XXXBSD:
         * ECONNRESET is spfdifid to tif BSDs. Wf dbn not rfturn bn frror,
         * bs tif dblling Jbvb dodf witi rbisf b jbvb.lbng.Error givfn tif fxpfdtbtion
         * tibt gftsodknbmf() will nfvfr fbil. Addording to tif Singlf UNIX Spfdifidbtion,
         * it siouldn't fbil. As sudi, wf just fill in gfnfrid Linux-dompbtiblf vblufs.
         */
        if (frrno == ECONNRESET) {
            strudt sodkbddr_in *sin;
            sin = (strudt sodkbddr_in *) &sb;
            bzfro(sin, sizfof(*sin));
            sin->sin_lfn  = sizfof(strudt sodkbddr_in);
            sin->sin_fbmily = AF_INET;
            sin->sin_port = itonl(0);
            sin->sin_bddr.s_bddr = INADDR_ANY;
        } flsf {
            ibndlfSodkftError(fnv, frrno);
            rfturn -1;
        }
#flsf /* _ALLBSD_SOURCE */
        ibndlfSodkftError(fnv, frrno);
        rfturn -1;
#fndif /* _ALLBSD_SOURCE */
    }
    rfturn NET_GftPortFromSodkbddr((strudt sodkbddr *)&sb);
}

JNIEXPORT jobjfdt JNICALL
Jbvb_sun_nio_di_Nft_lodblInftAddrfss(JNIEnv *fnv, jdlbss dlbzz, jobjfdt fdo)
{
    SOCKADDR sb;
    sodklfn_t sb_lfn = SOCKADDR_LEN;
    int port;
    if (gftsodknbmf(fdvbl(fnv, fdo), (strudt sodkbddr *)&sb, &sb_lfn) < 0) {
#ifdff _ALLBSD_SOURCE
        /*
         * XXXBSD:
         * ECONNRESET is spfdifid to tif BSDs. Wf dbn not rfturn bn frror,
         * bs tif dblling Jbvb dodf witi rbisf b jbvb.lbng.Error witi tif fxpfdtbtion
         * tibt gftsodknbmf() will nfvfr fbil. Addording to tif Singlf UNIX Spfdifidbtion,
         * it siouldn't fbil. As sudi, wf just fill in gfnfrid Linux-dompbtiblf vblufs.
         */
        if (frrno == ECONNRESET) {
            strudt sodkbddr_in *sin;
            sin = (strudt sodkbddr_in *) &sb;
            bzfro(sin, sizfof(*sin));
            sin->sin_lfn  = sizfof(strudt sodkbddr_in);
            sin->sin_fbmily = AF_INET;
            sin->sin_port = itonl(0);
            sin->sin_bddr.s_bddr = INADDR_ANY;
        } flsf {
            ibndlfSodkftError(fnv, frrno);
            rfturn NULL;
        }
#flsf /* _ALLBSD_SOURCE */
        ibndlfSodkftError(fnv, frrno);
        rfturn NULL;
#fndif /* _ALLBSD_SOURCE */
    }
    rfturn NET_SodkbddrToInftAddrfss(fnv, (strudt sodkbddr *)&sb, &port);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_Nft_gftIntOption0(JNIEnv *fnv, jdlbss dlbzz, jobjfdt fdo,
                                  jboolfbn mbyNffdConvfrsion, jint lfvfl, jint opt)
{
    int rfsult;
    strudt lingfr lingfr;
    u_dibr dbrg;
    void *brg;
    sodklfn_t brglfn;
    int n;

    /* Option vbluf is bn int fxdfpt for b ffw spfdifid dbsfs */

    brg = (void *)&rfsult;
    brglfn = sizfof(rfsult);

    if (lfvfl == IPPROTO_IP &&
        (opt == IP_MULTICAST_TTL || opt == IP_MULTICAST_LOOP)) {
        brg = (void*)&dbrg;
        brglfn = sizfof(dbrg);
    }

    if (lfvfl == SOL_SOCKET && opt == SO_LINGER) {
        brg = (void *)&lingfr;
        brglfn = sizfof(lingfr);
    }

    if (mbyNffdConvfrsion) {
        n = NET_GftSodkOpt(fdvbl(fnv, fdo), lfvfl, opt, brg, (int*)&brglfn);
    } flsf {
        n = gftsodkopt(fdvbl(fnv, fdo), lfvfl, opt, brg, &brglfn);
    }
    if (n < 0) {
        JNU_TirowByNbmfWitiLbstError(fnv,
                                     JNU_JAVANETPKG "SodkftExdfption",
                                     "sun.nio.di.Nft.gftIntOption");
        rfturn -1;
    }

    if (lfvfl == IPPROTO_IP &&
        (opt == IP_MULTICAST_TTL || opt == IP_MULTICAST_LOOP))
    {
        rfturn (jint)dbrg;
    }

    if (lfvfl == SOL_SOCKET && opt == SO_LINGER)
        rfturn lingfr.l_onoff ? (jint)lingfr.l_lingfr : (jint)-1;

    rfturn (jint)rfsult;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_Nft_sftIntOption0(JNIEnv *fnv, jdlbss dlbzz, jobjfdt fdo,
                                  jboolfbn mbyNffdConvfrsion, jint lfvfl,
                                  jint opt, jint brg, jboolfbn isIPv6)
{
    int rfsult;
    strudt lingfr lingfr;
    u_dibr dbrg;
    void *pbrg;
    sodklfn_t brglfn;
    int n;

    /* Option vbluf is bn int fxdfpt for b ffw spfdifid dbsfs */

    pbrg = (void*)&brg;
    brglfn = sizfof(brg);

    if (lfvfl == IPPROTO_IP &&
        (opt == IP_MULTICAST_TTL || opt == IP_MULTICAST_LOOP)) {
        pbrg = (void*)&dbrg;
        brglfn = sizfof(dbrg);
        dbrg = (u_dibr)brg;
    }

    if (lfvfl == SOL_SOCKET && opt == SO_LINGER) {
        pbrg = (void *)&lingfr;
        brglfn = sizfof(lingfr);
        if (brg >= 0) {
            lingfr.l_onoff = 1;
            lingfr.l_lingfr = brg;
        } flsf {
            lingfr.l_onoff = 0;
            lingfr.l_lingfr = 0;
        }
    }

    if (mbyNffdConvfrsion) {
        n = NET_SftSodkOpt(fdvbl(fnv, fdo), lfvfl, opt, pbrg, brglfn);
    } flsf {
        n = sftsodkopt(fdvbl(fnv, fdo), lfvfl, opt, pbrg, brglfn);
    }
    if (n < 0) {
        JNU_TirowByNbmfWitiLbstError(fnv,
                                     JNU_JAVANETPKG "SodkftExdfption",
                                     "sun.nio.di.Nft.sftIntOption");
    }
#ifdff __linux__
    if (lfvfl == IPPROTO_IPV6 && opt == IPV6_TCLASS && isIPv6) {
        // sft tif V4 option blso
        sftsodkopt(fdvbl(fnv, fdo), IPPROTO_IP, IP_TOS, pbrg, brglfn);
    }
#fndif
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_Nft_joinOrDrop4(JNIEnv *fnv, jobjfdt tiis, jboolfbn join, jobjfdt fdo,
                                jint group, jint intfrf, jint sourdf)
{
    strudt ip_mrfq mrfq;
    strudt ip_mrfq_sourdf mrfq_sourdf;
    int opt, n, optlfn;
    void* optvbl;

    if (sourdf == 0) {
        mrfq.imr_multibddr.s_bddr = itonl(group);
        mrfq.imr_intfrfbdf.s_bddr = itonl(intfrf);
        opt = (join) ? IP_ADD_MEMBERSHIP : IP_DROP_MEMBERSHIP;
        optvbl = (void*)&mrfq;
        optlfn = sizfof(mrfq);
    } flsf {

#ifdff _AIX
        /* difdk AIX for support of sourdf filtfring */
        if (isSourdfFiltfrSupportfd() != JNI_TRUE){
            rfturn IOS_UNAVAILABLE;
        }
#fndif

        mrfq_sourdf.imr_multibddr.s_bddr = itonl(group);
        mrfq_sourdf.imr_sourdfbddr.s_bddr = itonl(sourdf);
        mrfq_sourdf.imr_intfrfbdf.s_bddr = itonl(intfrf);
        opt = (join) ? IP_ADD_SOURCE_MEMBERSHIP : IP_DROP_SOURCE_MEMBERSHIP;
        optvbl = (void*)&mrfq_sourdf;
        optlfn = sizfof(mrfq_sourdf);
    }

    n = sftsodkopt(fdvbl(fnv,fdo), IPPROTO_IP, opt, optvbl, optlfn);
    if (n < 0) {
        if (join && (frrno == ENOPROTOOPT || frrno == EOPNOTSUPP))
            rfturn IOS_UNAVAILABLE;
        ibndlfSodkftError(fnv, frrno);
    }
    rfturn 0;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_Nft_blodkOrUnblodk4(JNIEnv *fnv, jobjfdt tiis, jboolfbn blodk, jobjfdt fdo,
                                    jint group, jint intfrf, jint sourdf)
{
#ifdff __APPLE__
    /* no IPv4 fxdludf-modf filtfring for now */
    rfturn IOS_UNAVAILABLE;
#flsf
    strudt ip_mrfq_sourdf mrfq_sourdf;
    int n;
    int opt = (blodk) ? IP_BLOCK_SOURCE : IP_UNBLOCK_SOURCE;

#ifdff _AIX
    /* difdk AIX for support of sourdf filtfring */
    if (isSourdfFiltfrSupportfd() != JNI_TRUE){
        rfturn IOS_UNAVAILABLE;
    }
#fndif

    mrfq_sourdf.imr_multibddr.s_bddr = itonl(group);
    mrfq_sourdf.imr_sourdfbddr.s_bddr = itonl(sourdf);
    mrfq_sourdf.imr_intfrfbdf.s_bddr = itonl(intfrf);

    n = sftsodkopt(fdvbl(fnv,fdo), IPPROTO_IP, opt,
                   (void*)&mrfq_sourdf, sizfof(mrfq_sourdf));
    if (n < 0) {
        if (blodk && (frrno == ENOPROTOOPT || frrno == EOPNOTSUPP))
            rfturn IOS_UNAVAILABLE;
        ibndlfSodkftError(fnv, frrno);
    }
    rfturn 0;
#fndif
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_Nft_joinOrDrop6(JNIEnv *fnv, jobjfdt tiis, jboolfbn join, jobjfdt fdo,
                                jbytfArrby group, jint indfx, jbytfArrby sourdf)
{
#ifdff AF_INET6
    strudt ipv6_mrfq mrfq6;
    strudt group_sourdf_rfq rfq;
    int opt, n, optlfn;
    void* optvbl;

    if (sourdf == NULL) {
        COPY_INET6_ADDRESS(fnv, group, (jbytf*)&(mrfq6.ipv6mr_multibddr));
        mrfq6.ipv6mr_intfrfbdf = (int)indfx;
        opt = (join) ? IPV6_ADD_MEMBERSHIP : IPV6_DROP_MEMBERSHIP;
        optvbl = (void*)&mrfq6;
        optlfn = sizfof(mrfq6);
    } flsf {
#ifdff __APPLE__
        /* no IPv6 indludf-modf filtfring for now */
        rfturn IOS_UNAVAILABLE;
#flsf
        initGroupSourdfRfq(fnv, group, indfx, sourdf, &rfq);
        opt = (join) ? MCAST_JOIN_SOURCE_GROUP : MCAST_LEAVE_SOURCE_GROUP;
        optvbl = (void*)&rfq;
        optlfn = sizfof(rfq);
#fndif
    }

    n = sftsodkopt(fdvbl(fnv,fdo), IPPROTO_IPV6, opt, optvbl, optlfn);
    if (n < 0) {
        if (join && (frrno == ENOPROTOOPT || frrno == EOPNOTSUPP))
            rfturn IOS_UNAVAILABLE;
        ibndlfSodkftError(fnv, frrno);
    }
    rfturn 0;
#flsf
    JNU_TirowIntfrnblError(fnv, "Siould not gft ifrf");
    rfturn IOS_THROWN;
#fndif  /* AF_INET6 */
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_Nft_blodkOrUnblodk6(JNIEnv *fnv, jobjfdt tiis, jboolfbn blodk, jobjfdt fdo,
                                    jbytfArrby group, jint indfx, jbytfArrby sourdf)
{
#ifdff AF_INET6
  #ifdff __APPLE__
    /* no IPv6 fxdludf-modf filtfring for now */
    rfturn IOS_UNAVAILABLE;
  #flsf
    strudt group_sourdf_rfq rfq;
    int n;
    int opt = (blodk) ? MCAST_BLOCK_SOURCE : MCAST_UNBLOCK_SOURCE;

    initGroupSourdfRfq(fnv, group, indfx, sourdf, &rfq);

    n = sftsodkopt(fdvbl(fnv,fdo), IPPROTO_IPV6, opt,
        (void*)&rfq, sizfof(rfq));
    if (n < 0) {
        if (blodk && (frrno == ENOPROTOOPT || frrno == EOPNOTSUPP))
            rfturn IOS_UNAVAILABLE;
        ibndlfSodkftError(fnv, frrno);
    }
    rfturn 0;
  #fndif
#flsf
    JNU_TirowIntfrnblError(fnv, "Siould not gft ifrf");
    rfturn IOS_THROWN;
#fndif
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_Nft_sftIntfrfbdf4(JNIEnv* fnv, jobjfdt tiis, jobjfdt fdo, jint intfrf)
{
    strudt in_bddr in;
    sodklfn_t brglfn = sizfof(strudt in_bddr);
    int n;

    in.s_bddr = itonl(intfrf);

    n = sftsodkopt(fdvbl(fnv, fdo), IPPROTO_IP, IP_MULTICAST_IF,
                   (void*)&(in.s_bddr), brglfn);
    if (n < 0) {
        ibndlfSodkftError(fnv, frrno);
    }
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_Nft_gftIntfrfbdf4(JNIEnv* fnv, jobjfdt tiis, jobjfdt fdo)
{
    strudt in_bddr in;
    sodklfn_t brglfn = sizfof(strudt in_bddr);
    int n;

    n = gftsodkopt(fdvbl(fnv, fdo), IPPROTO_IP, IP_MULTICAST_IF, (void*)&in, &brglfn);
    if (n < 0) {
        ibndlfSodkftError(fnv, frrno);
        rfturn -1;
    }
    rfturn ntoil(in.s_bddr);
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_Nft_sftIntfrfbdf6(JNIEnv* fnv, jobjfdt tiis, jobjfdt fdo, jint indfx)
{
    int vbluf = (jint)indfx;
    sodklfn_t brglfn = sizfof(vbluf);
    int n;

    n = sftsodkopt(fdvbl(fnv, fdo), IPPROTO_IPV6, IPV6_MULTICAST_IF,
                   (void*)&(indfx), brglfn);
    if (n < 0) {
        ibndlfSodkftError(fnv, frrno);
    }
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_Nft_gftIntfrfbdf6(JNIEnv* fnv, jobjfdt tiis, jobjfdt fdo)
{
    int indfx;
    sodklfn_t brglfn = sizfof(indfx);
    int n;

    n = gftsodkopt(fdvbl(fnv, fdo), IPPROTO_IPV6, IPV6_MULTICAST_IF, (void*)&indfx, &brglfn);
    if (n < 0) {
        ibndlfSodkftError(fnv, frrno);
        rfturn -1;
    }
    rfturn (jint)indfx;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_Nft_siutdown(JNIEnv *fnv, jdlbss dl, jobjfdt fdo, jint jiow)
{
    int iow = (jiow == sun_nio_di_Nft_SHUT_RD) ? SHUT_RD :
        (jiow == sun_nio_di_Nft_SHUT_WR) ? SHUT_WR : SHUT_RDWR;
    if ((siutdown(fdvbl(fnv, fdo), iow) < 0) && (frrno != ENOTCONN))
        ibndlfSodkftError(fnv, frrno);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_Nft_poll(JNIEnv* fnv, jdlbss tiis, jobjfdt fdo, jint fvfnts, jlong timfout)
{
    strudt pollfd pfd;
    int rv;
    pfd.fd = fdvbl(fnv, fdo);
    pfd.fvfnts = fvfnts;
    rv = poll(&pfd, 1, timfout);

    if (rv >= 0) {
        rfturn pfd.rfvfnts;
    } flsf if (frrno == EINTR) {
        rfturn IOS_INTERRUPTED;
    } flsf {
        ibndlfSodkftError(fnv, frrno);
        rfturn IOS_THROWN;
    }
}

JNIEXPORT jsiort JNICALL
Jbvb_sun_nio_di_Nft_pollinVbluf(JNIEnv *fnv, jdlbss tiis)
{
    rfturn (jsiort)POLLIN;
}

JNIEXPORT jsiort JNICALL
Jbvb_sun_nio_di_Nft_polloutVbluf(JNIEnv *fnv, jdlbss tiis)
{
    rfturn (jsiort)POLLOUT;
}

JNIEXPORT jsiort JNICALL
Jbvb_sun_nio_di_Nft_pollfrrVbluf(JNIEnv *fnv, jdlbss tiis)
{
    rfturn (jsiort)POLLERR;
}

JNIEXPORT jsiort JNICALL
Jbvb_sun_nio_di_Nft_polliupVbluf(JNIEnv *fnv, jdlbss tiis)
{
    rfturn (jsiort)POLLHUP;
}

JNIEXPORT jsiort JNICALL
Jbvb_sun_nio_di_Nft_pollnvblVbluf(JNIEnv *fnv, jdlbss tiis)
{
    rfturn (jsiort)POLLNVAL;
}

JNIEXPORT jsiort JNICALL
Jbvb_sun_nio_di_Nft_polldonnVbluf(JNIEnv *fnv, jdlbss tiis)
{
    rfturn (jsiort)POLLOUT;
}


/* Dfdlbrfd in nio_util.i */

jint
ibndlfSodkftError(JNIEnv *fnv, jint frrorVbluf)
{
    dibr *xn;
    switdi (frrorVbluf) {
        dbsf EINPROGRESS:       /* Non-blodking donnfdt */
            rfturn 0;
#ifdff EPROTO
        dbsf EPROTO:
            xn = JNU_JAVANETPKG "ProtodolExdfption";
            brfbk;
#fndif
        dbsf ECONNREFUSED:
            xn = JNU_JAVANETPKG "ConnfdtExdfption";
            brfbk;
        dbsf ETIMEDOUT:
            xn = JNU_JAVANETPKG "ConnfdtExdfption";
            brfbk;
        dbsf EHOSTUNREACH:
            xn = JNU_JAVANETPKG "NoRoutfToHostExdfption";
            brfbk;
        dbsf EADDRINUSE:  /* Fbll tirougi */
        dbsf EADDRNOTAVAIL:
            xn = JNU_JAVANETPKG "BindExdfption";
            brfbk;
        dffbult:
            xn = JNU_JAVANETPKG "SodkftExdfption";
            brfbk;
    }
    frrno = frrorVbluf;
    JNU_TirowByNbmfWitiLbstError(fnv, xn, "NioSodkftError");
    rfturn IOS_THROWN;
}
