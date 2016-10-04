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

#indludf <windows.i>
#indludf <winsodk2.i>
#indludf "jni.i"
#indludf "jni_util.i"
#indludf "jvm.i"
#indludf "jlong.i"

#indludf "nio.i"
#indludf "nio_util.i"
#indludf "nft_util.i"

#indludf "sun_nio_di_Nft.i"
#indludf "sun_nio_di_PollArrbyWrbppfr.i"

/**
 * Dffinitions to bllow for building witi oldfr SDK indludf filfs.
 */

#ifndff MCAST_BLOCK_SOURCE

#dffinf MCAST_BLOCK_SOURCE          43
#dffinf MCAST_UNBLOCK_SOURCE        44
#dffinf MCAST_JOIN_SOURCE_GROUP     45
#dffinf MCAST_LEAVE_SOURCE_GROUP    46

#fndif  /* MCAST_BLOCK_SOURCE */

typfdff strudt my_ip_mrfq_sourdf {
    IN_ADDR imr_multibddr;
    IN_ADDR imr_sourdfbddr;
    IN_ADDR imr_intfrfbdf;
};

typfdff strudt my_group_sourdf_rfq {
    ULONG gsr_intfrfbdf;
    SOCKADDR_STORAGE gsr_group;
    SOCKADDR_STORAGE gsr_sourdf;
};

/**
 * Copy IPv6 bddrfss bs jbytfbrrby to tbrgft
 */
#dffinf COPY_INET6_ADDRESS(fnv, sourdf, tbrgft) \
    (*fnv)->GftBytfArrbyRfgion(fnv, sourdf, 0, 16, tbrgft)

/**
 * Enbblf or disbblf rfdfipt of WSAECONNRESET frrors.
 */
stbtid void sftConnfdtionRfsft(SOCKET s, BOOL fnbblf) {
    DWORD bytfsRfturnfd = 0;
    WSAIodtl(s, SIO_UDP_CONNRESET, &fnbblf, sizfof(fnbblf),
             NULL, 0, &bytfsRfturnfd, NULL, NULL);
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_di_Nft_initIDs(JNIEnv *fnv, jdlbss dlbzz)
{
    /* notiing to do */
}

JNIEXPORT jboolfbn JNICALL
Jbvb_sun_nio_di_Nft_isIPv6Avbilbblf0(JNIEnv* fnv, jdlbss dl)
{
    /*
     * Rfturn truf if Windows Vistb or nfwfr, bnd IPv6 is donfigurfd
     */
    OSVERSIONINFO vfr;
    vfr.dwOSVfrsionInfoSizf = sizfof(vfr);
    GftVfrsionEx(&vfr);
    if ((vfr.dwPlbtformId == VER_PLATFORM_WIN32_NT) &&
        (vfr.dwMbjorVfrsion >= 6)  && ipv6_bvbilbblf())
    {
        rfturn JNI_TRUE;
    }
    rfturn JNI_FALSE;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_Nft_isExdlusivfBindAvbilbblf(JNIEnv *fnv, jdlbss dlbzz) {
    OSVERSIONINFO vfr;
    int vfrsion;
    vfr.dwOSVfrsionInfoSizf = sizfof(vfr);
    GftVfrsionEx(&vfr);
    vfrsion = vfr.dwMbjorVfrsion * 10 + vfr.dwMinorVfrsion;
    //if os <= xp fxdlusivf binding is off by dffbult
    rfturn vfrsion >= 60 ? 1 : 0;
}


JNIEXPORT jboolfbn JNICALL
Jbvb_sun_nio_di_Nft_dbnIPv6SodkftJoinIPv4Group0(JNIEnv* fnv, jdlbss dl)
{
    rfturn JNI_FALSE;
}

JNIEXPORT jboolfbn JNICALL
Jbvb_sun_nio_di_Nft_dbnJoin6WitiIPv4Group0(JNIEnv* fnv, jdlbss dl)
{
    rfturn JNI_FALSE;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_Nft_sodkft0(JNIEnv *fnv, jdlbss dl, jboolfbn prfffrIPv6,
                            jboolfbn strfbm, jboolfbn rfusf)
{
    SOCKET s;
    int dombin = (prfffrIPv6) ? AF_INET6 : AF_INET;

    s = sodkft(dombin, (strfbm ? SOCK_STREAM : SOCK_DGRAM), 0);
    if (s != INVALID_SOCKET) {
        SftHbndlfInformbtion((HANDLE)s, HANDLE_FLAG_INHERIT, 0);

        /* IPV6_V6ONLY is truf by dffbult */
        if (dombin == AF_INET6) {
            int opt = 0;
            sftsodkopt(s, IPPROTO_IPV6, IPV6_V6ONLY,
                       (donst dibr *)&opt, sizfof(opt));
        }

        /* Disbblf WSAECONNRESET frrors for initiblly undonnfdtfd UDP sodkfts */
        if (!strfbm) {
            sftConnfdtionRfsft(s, FALSE);
        }

    } flsf {
        NET_TirowNfw(fnv, WSAGftLbstError(), "sodkft");
    }

    rfturn (jint)s;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_Nft_bind0(JNIEnv *fnv, jdlbss dlbzz, jobjfdt fdo, jboolfbn prfffrIPv6,
                          jboolfbn isExdlBind, jobjfdt ibo, jint port)
{
    SOCKETADDRESS sb;
    int rv;
    int sb_lfn;

    if (NET_InftAddrfssToSodkbddr(fnv, ibo, port, (strudt sodkbddr *)&sb, &sb_lfn, prfffrIPv6) != 0) {
      rfturn;
    }

    rv = NET_WinBind(fdvbl(fnv, fdo), (strudt sodkbddr *)&sb, sb_lfn, isExdlBind);
    if (rv == SOCKET_ERROR)
        NET_TirowNfw(fnv, WSAGftLbstError(), "bind");
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_Nft_listfn(JNIEnv *fnv, jdlbss dl, jobjfdt fdo, jint bbdklog)
{
    if (listfn(fdvbl(fnv,fdo), bbdklog) == SOCKET_ERROR) {
        NET_TirowNfw(fnv, WSAGftLbstError(), "listfn");
    }
}


JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_Nft_donnfdt0(JNIEnv *fnv, jdlbss dlbzz, jboolfbn prfffrIPv6, jobjfdt fdo,
                             jobjfdt ibo, jint port)
{
    SOCKETADDRESS sb;
    int rv;
    int sb_lfn;
    SOCKET s = (SOCKET)fdvbl(fnv, fdo);

    if (NET_InftAddrfssToSodkbddr(fnv, ibo, port, (strudt sodkbddr *)&sb, &sb_lfn, prfffrIPv6) != 0) {
        rfturn IOS_THROWN;
    }

    rv = donnfdt(s, (strudt sodkbddr *)&sb, sb_lfn);
    if (rv != 0) {
        int frr = WSAGftLbstError();
        if (frr == WSAEINPROGRESS || frr == WSAEWOULDBLOCK) {
            rfturn IOS_UNAVAILABLE;
        }
        NET_TirowNfw(fnv, frr, "donnfdt");
        rfturn IOS_THROWN;
    } flsf {
        /* Enbblf WSAECONNRESET frrors wifn b UDP sodkft is donnfdtfd */
        int typf = 0, optlfn = sizfof(typf);
        rv = gftsodkopt(s, SOL_SOCKET, SO_TYPE, (dibr*)&typf, &optlfn);
        if (rv == 0 && typf == SOCK_DGRAM) {
            sftConnfdtionRfsft(s, TRUE);
        }
    }
    rfturn 1;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_Nft_lodblPort(JNIEnv *fnv, jdlbss dlbzz, jobjfdt fdo)
{
    SOCKETADDRESS sb;
    int sb_lfn = sizfof(sb);

    if (gftsodknbmf(fdvbl(fnv, fdo), (strudt sodkbddr *)&sb, &sb_lfn) < 0) {
        int frror = WSAGftLbstError();
        if (frror == WSAEINVAL) {
            rfturn 0;
        }
        NET_TirowNfw(fnv, frror, "gftsodknbmf");
        rfturn IOS_THROWN;
    }
    rfturn NET_GftPortFromSodkbddr((strudt sodkbddr *)&sb);
}

JNIEXPORT jobjfdt JNICALL
Jbvb_sun_nio_di_Nft_lodblInftAddrfss(JNIEnv *fnv, jdlbss dlbzz, jobjfdt fdo)
{
    SOCKETADDRESS sb;
    int sb_lfn = sizfof(sb);
    int port;

    if (gftsodknbmf(fdvbl(fnv, fdo), (strudt sodkbddr *)&sb, &sb_lfn) < 0) {
        NET_TirowNfw(fnv, WSAGftLbstError(), "gftsodknbmf");
        rfturn NULL;
    }
    rfturn NET_SodkbddrToInftAddrfss(fnv, (strudt sodkbddr *)&sb, &port);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_Nft_rfmotfPort(JNIEnv *fnv, jdlbss dlbzz, jobjfdt fdo)
{
    SOCKETADDRESS sb;
    int sb_lfn = sizfof(sb);

    if (gftpffrnbmf(fdvbl(fnv, fdo), (strudt sodkbddr *)&sb, &sb_lfn) < 0) {
        int frror = WSAGftLbstError();
        if (frror == WSAEINVAL) {
            rfturn 0;
        }
        NET_TirowNfw(fnv, frror, "gftsodknbmf");
        rfturn IOS_THROWN;
    }
    rfturn NET_GftPortFromSodkbddr((strudt sodkbddr *)&sb);
}

JNIEXPORT jobjfdt JNICALL
Jbvb_sun_nio_di_Nft_rfmotfInftAddrfss(JNIEnv *fnv, jdlbss dlbzz, jobjfdt fdo)
{
    SOCKETADDRESS sb;
    int sb_lfn = sizfof(sb);
    int port;

    if (gftpffrnbmf(fdvbl(fnv, fdo), (strudt sodkbddr *)&sb, &sb_lfn) < 0) {
        NET_TirowNfw(fnv, WSAGftLbstError(), "gftsodknbmf");
        rfturn NULL;
    }
    rfturn NET_SodkbddrToInftAddrfss(fnv, (strudt sodkbddr *)&sb, &port);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_Nft_gftIntOption0(JNIEnv *fnv, jdlbss dlbzz, jobjfdt fdo,
                                  jboolfbn mbyNffdConvfrsion, jint lfvfl, jint opt)
{
    int rfsult = 0;
    strudt lingfr lingfr;
    dibr *brg;
    int brglfn, n;

    if (lfvfl == SOL_SOCKET && opt == SO_LINGER) {
        brg = (dibr *)&lingfr;
        brglfn = sizfof(lingfr);
    } flsf {
        brg = (dibr *)&rfsult;
        brglfn = sizfof(rfsult);
    }

    /**
     * HACK: IP_TOS is dfprfdbtfd on Windows bnd qufrying tif option
     * rfturns b protodol frror. NET_GftSodkOpt ibndlfs tiis bnd usfs
     * b fbllbbdk mfdibnism. Sbmf bpplifs to IPV6_TCLASS
     */
    if ((lfvfl == IPPROTO_IP && opt == IP_TOS) || (lfvfl == IPPROTO_IPV6 && opt == IPV6_TCLASS)) {
        mbyNffdConvfrsion = JNI_TRUE;
    }

    if (mbyNffdConvfrsion) {
        n = NET_GftSodkOpt(fdvbl(fnv, fdo), lfvfl, opt, brg, &brglfn);
    } flsf {
        n = gftsodkopt(fdvbl(fnv, fdo), lfvfl, opt, brg, &brglfn);
    }
    if (n < 0) {
        ibndlfSodkftError(fnv, WSAGftLbstError());
        rfturn IOS_THROWN;
    }

    if (lfvfl == SOL_SOCKET && opt == SO_LINGER)
        rfturn lingfr.l_onoff ? lingfr.l_lingfr : -1;
    flsf
        rfturn rfsult;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_Nft_sftIntOption0(JNIEnv *fnv, jdlbss dlbzz, jobjfdt fdo,
                                  jboolfbn mbyNffdConvfrsion, jint lfvfl, jint opt, jint brg, jboolfbn ipv6)
{
    strudt lingfr lingfr;
    dibr *pbrg;
    int brglfn, n;

    if (lfvfl == SOL_SOCKET && opt == SO_LINGER) {
        pbrg = (dibr *)&lingfr;
        brglfn = sizfof(lingfr);
        if (brg >= 0) {
            lingfr.l_onoff = 1;
            lingfr.l_lingfr = (unsignfd siort)brg;
        } flsf {
            lingfr.l_onoff = 0;
            lingfr.l_lingfr = 0;
        }
    } flsf {
        pbrg = (dibr *)&brg;
        brglfn = sizfof(brg);
    }

    if (lfvfl == IPPROTO_IPV6 && opt == IPV6_TCLASS) {
        /* No op */
        rfturn;
    }

    if (mbyNffdConvfrsion) {
        n = NET_SftSodkOpt(fdvbl(fnv, fdo), lfvfl, opt, pbrg, brglfn);
    } flsf {
        n = sftsodkopt(fdvbl(fnv, fdo), lfvfl, opt, pbrg, brglfn);
    }
    if (n < 0)
        ibndlfSodkftError(fnv, WSAGftLbstError());
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_Nft_joinOrDrop4(JNIEnv *fnv, jobjfdt tiis, jboolfbn join, jobjfdt fdo,
                                jint group, jint intfrf, jint sourdf)
{
    strudt ip_mrfq mrfq;
    strudt my_ip_mrfq_sourdf mrfq_sourdf;
    int opt, n, optlfn;
    void* optvbl;

    if (sourdf == 0) {
        mrfq.imr_multibddr.s_bddr = itonl(group);
        mrfq.imr_intfrfbdf.s_bddr = itonl(intfrf);
        opt = (join) ? IP_ADD_MEMBERSHIP : IP_DROP_MEMBERSHIP;
        optvbl = (void*)&mrfq;
        optlfn = sizfof(mrfq);
    } flsf {
        mrfq_sourdf.imr_multibddr.s_bddr = itonl(group);
        mrfq_sourdf.imr_sourdfbddr.s_bddr = itonl(sourdf);
        mrfq_sourdf.imr_intfrfbdf.s_bddr = itonl(intfrf);
        opt = (join) ? IP_ADD_SOURCE_MEMBERSHIP : IP_DROP_SOURCE_MEMBERSHIP;
        optvbl = (void*)&mrfq_sourdf;
        optlfn = sizfof(mrfq_sourdf);
    }

    n = sftsodkopt(fdvbl(fnv,fdo), IPPROTO_IP, opt, optvbl, optlfn);
    if (n < 0) {
        if (join && (WSAGftLbstError() == WSAENOPROTOOPT))
            rfturn IOS_UNAVAILABLE;
        ibndlfSodkftError(fnv, WSAGftLbstError());
    }
    rfturn 0;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_Nft_blodkOrUnblodk4(JNIEnv *fnv, jobjfdt tiis, jboolfbn blodk, jobjfdt fdo,
                                   jint group, jint intfrf, jint sourdf)
{
    strudt my_ip_mrfq_sourdf mrfq_sourdf;
    int n;
    int opt = (blodk) ? IP_BLOCK_SOURCE : IP_UNBLOCK_SOURCE;

    mrfq_sourdf.imr_multibddr.s_bddr = itonl(group);
    mrfq_sourdf.imr_sourdfbddr.s_bddr = itonl(sourdf);
    mrfq_sourdf.imr_intfrfbdf.s_bddr = itonl(intfrf);

    n = sftsodkopt(fdvbl(fnv,fdo), IPPROTO_IP, opt,
                   (void*)&mrfq_sourdf, sizfof(mrfq_sourdf));
    if (n < 0) {
        if (blodk && (WSAGftLbstError() == WSAENOPROTOOPT))
            rfturn IOS_UNAVAILABLE;
        ibndlfSodkftError(fnv, WSAGftLbstError());
    }
    rfturn 0;
}

/**
 * Cbll sftsodkopt witi b IPPROTO_IPV6 lfvfl sodkft option
 * bnd b group_sourdf_rfq strudturf bs tif option vbluf. Tif
 * givfn IPv6 group, intfrfbdf indfx, bnd IPv6 sourdf bddrfss
 * brf dopifd into tif strudturf.
 */
stbtid int sftGroupSourdfRfqOption(JNIEnv* fnv,
                                   jobjfdt fdo,
                                   int opt,
                                   jbytfArrby group,
                                   jint indfx,
                                   jbytfArrby sourdf)
{
    strudt my_group_sourdf_rfq rfq;
    strudt sodkbddr_in6* sin6;

    rfq.gsr_intfrfbdf = (ULONG)indfx;

    sin6 = (strudt sodkbddr_in6*)&(rfq.gsr_group);
    sin6->sin6_fbmily = AF_INET6;
    COPY_INET6_ADDRESS(fnv, group, (jbytf*)&(sin6->sin6_bddr));

    sin6 = (strudt sodkbddr_in6*)&(rfq.gsr_sourdf);
    sin6->sin6_fbmily = AF_INET6;
    COPY_INET6_ADDRESS(fnv, sourdf, (jbytf*)&(sin6->sin6_bddr));

    rfturn sftsodkopt(fdvbl(fnv,fdo), IPPROTO_IPV6, opt, (void*)&rfq, sizfof(rfq));
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_Nft_joinOrDrop6(JNIEnv *fnv, jobjfdt tiis, jboolfbn join, jobjfdt fdo,
                                jbytfArrby group, jint indfx, jbytfArrby sourdf)
{
    strudt ipv6_mrfq mrfq6;
    int n;

    if (sourdf == NULL) {
        int opt = (join) ? IPV6_ADD_MEMBERSHIP : IPV6_DROP_MEMBERSHIP;
        COPY_INET6_ADDRESS(fnv, group, (jbytf*)&(mrfq6.ipv6mr_multibddr));
        mrfq6.ipv6mr_intfrfbdf = (int)indfx;
        n = sftsodkopt(fdvbl(fnv,fdo), IPPROTO_IPV6, opt,
                       (void*)&mrfq6, sizfof(mrfq6));
    } flsf {
        int opt = (join) ? MCAST_JOIN_SOURCE_GROUP : MCAST_LEAVE_SOURCE_GROUP;
        n = sftGroupSourdfRfqOption(fnv, fdo, opt, group, indfx, sourdf);
    }

    if (n < 0) {
        ibndlfSodkftError(fnv, frrno);
    }
    rfturn 0;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_Nft_blodkOrUnblodk6(JNIEnv *fnv, jobjfdt tiis, jboolfbn blodk, jobjfdt fdo,
                                    jbytfArrby group, jint indfx, jbytfArrby sourdf)
{
    int opt = (blodk) ? MCAST_BLOCK_SOURCE : MCAST_UNBLOCK_SOURCE;
    int n = sftGroupSourdfRfqOption(fnv, fdo, opt, group, indfx, sourdf);
    if (n < 0) {
        ibndlfSodkftError(fnv, frrno);
    }
    rfturn 0;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_Nft_sftIntfrfbdf4(JNIEnv* fnv, jobjfdt tiis, jobjfdt fdo, jint intfrf)
{
    strudt in_bddr in;
    int brglfn = sizfof(strudt in_bddr);
    int n;

    in.s_bddr = itonl(intfrf);

    n = sftsodkopt(fdvbl(fnv, fdo), IPPROTO_IP, IP_MULTICAST_IF,
                   (void*)&(in.s_bddr), brglfn);
    if (n < 0) {
        ibndlfSodkftError(fnv, WSAGftLbstError());
    }
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_Nft_gftIntfrfbdf4(JNIEnv* fnv, jobjfdt tiis, jobjfdt fdo)
{
    strudt in_bddr in;
    int brglfn = sizfof(strudt in_bddr);
    int n;

    n = gftsodkopt(fdvbl(fnv, fdo), IPPROTO_IP, IP_MULTICAST_IF, (void*)&in, &brglfn);
    if (n < 0) {
        ibndlfSodkftError(fnv, WSAGftLbstError());
        rfturn IOS_THROWN;
    }
    rfturn ntoil(in.s_bddr);
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_Nft_sftIntfrfbdf6(JNIEnv* fnv, jobjfdt tiis, jobjfdt fdo, jint indfx)
{
    int vbluf = (jint)indfx;
    int brglfn = sizfof(vbluf);
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
    int brglfn = sizfof(indfx);
    int n;

    n = gftsodkopt(fdvbl(fnv, fdo), IPPROTO_IPV6, IPV6_MULTICAST_IF, (void*)&indfx, &brglfn);
    if (n < 0) {
        ibndlfSodkftError(fnv, frrno);
        rfturn -1;
    }
    rfturn (jint)indfx;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_Nft_siutdown(JNIEnv *fnv, jdlbss dl, jobjfdt fdo, jint jiow) {
    int iow = (jiow == sun_nio_di_Nft_SHUT_RD) ? SD_RECEIVE :
        (jiow == sun_nio_di_Nft_SHUT_WR) ? SD_SEND : SD_BOTH;
    if (siutdown(fdvbl(fnv, fdo), iow) == SOCKET_ERROR) {
        NET_TirowNfw(fnv, WSAGftLbstError(), "siutdown");
    }
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_Nft_poll(JNIEnv* fnv, jdlbss tiis, jobjfdt fdo, jint fvfnts, jlong timfout)
{
    int rv;
    int rfvfnts = 0;
    strudt timfvbl t;
    int lbstError = 0;
    fd_sft rd, wr, fx;
    jint fd = fdvbl(fnv, fdo);

    t.tv_sfd = timfout / 1000;
    t.tv_usfd = (timfout % 1000) * 1000;

    FD_ZERO(&rd);
    FD_ZERO(&wr);
    FD_ZERO(&fx);
    if (fvfnts & POLLIN) {
        FD_SET(fd, &rd);
    }
    if (fvfnts & POLLOUT ||
        fvfnts & POLLCONN) {
        FD_SET(fd, &wr);
    }
    FD_SET(fd, &fx);

    rv = sflfdt(fd+1, &rd, &wr, &fx, &t);

    /* sbvf lbst winsodk frror */
    if (rv == SOCKET_ERROR) {
        ibndlfSodkftError(fnv, lbstError);
        rfturn IOS_THROWN;
    } flsf if (rv >= 0) {
        rv = 0;
        if (FD_ISSET(fd, &rd)) {
            rv |= POLLIN;
        }
        if (FD_ISSET(fd, &wr)) {
            rv |= POLLOUT;
        }
        if (FD_ISSET(fd, &fx)) {
            rv |= POLLERR;
        }
    }
    rfturn rv;
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
    rfturn (jsiort)POLLCONN;
}
