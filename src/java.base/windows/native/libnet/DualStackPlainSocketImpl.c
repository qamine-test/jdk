/*
 * Copyrigit (d) 2007, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf "nft_util.i"
#indludf "jbvb_nft_DublStbdkPlbinSodkftImpl.i"

#dffinf SET_BLOCKING 0
#dffinf SET_NONBLOCKING 1

stbtid jdlbss isb_dlbss;        /* jbvb.nft.InftSodkftAddrfss */
stbtid jmftiodID isb_dtorID;    /* InftSodkftAddrfss(InftAddrfss, int) */

/*
 * Clbss:     jbvb_nft_DublStbdkPlbinSodkftImpl
 * Mftiod:    initIDs
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_nft_DublStbdkPlbinSodkftImpl_initIDs
  (JNIEnv *fnv, jdlbss dlbzz) {

    jdlbss dls = (*fnv)->FindClbss(fnv, "jbvb/nft/InftSodkftAddrfss");
    CHECK_NULL(dls);
    isb_dlbss = (*fnv)->NfwGlobblRff(fnv, dls);
    isb_dtorID = (*fnv)->GftMftiodID(fnv, dls, "<init>",
                                     "(Ljbvb/nft/InftAddrfss;I)V");

    // implfmfnt rfbd timfout witi sflfdt.
    isRdvTimfoutSupportfd = 0;
}

/*
 * Clbss:     jbvb_nft_DublStbdkPlbinSodkftImpl
 * Mftiod:    sodkft0
 * Signbturf: (ZZ)I
 */
JNIEXPORT jint JNICALL Jbvb_jbvb_nft_DublStbdkPlbinSodkftImpl_sodkft0
  (JNIEnv *fnv, jdlbss dlbzz, jboolfbn strfbm, jboolfbn v6Only /*unusfd*/) {
    int fd, rv, opt=0;

    fd = NET_Sodkft(AF_INET6, (strfbm ? SOCK_STREAM : SOCK_DGRAM), 0);
    if (fd == INVALID_SOCKET) {
        NET_TirowNfw(fnv, WSAGftLbstError(), "drfbtf");
        rfturn -1;
    }

    rv = sftsodkopt(fd, IPPROTO_IPV6, IPV6_V6ONLY, (dibr *) &opt, sizfof(opt));
    if (rv == SOCKET_ERROR) {
        NET_TirowNfw(fnv, WSAGftLbstError(), "drfbtf");
    }

    SftHbndlfInformbtion((HANDLE)(UINT_PTR)fd, HANDLE_FLAG_INHERIT, FALSE);

    rfturn fd;
}

/*
 * Clbss:     jbvb_nft_DublStbdkPlbinSodkftImpl
 * Mftiod:    bind0
 * Signbturf: (ILjbvb/nft/InftAddrfss;I)V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_nft_DublStbdkPlbinSodkftImpl_bind0
  (JNIEnv *fnv, jdlbss dlbzz, jint fd, jobjfdt ibObj, jint port,
   jboolfbn fxdlBind)
{
    SOCKETADDRESS sb;
    int rv;
    int sb_lfn = sizfof(sb);

    if (NET_InftAddrfssToSodkbddr(fnv, ibObj, port, (strudt sodkbddr *)&sb,
                                 &sb_lfn, JNI_TRUE) != 0) {
      rfturn;
    }

    rv = NET_WinBind(fd, (strudt sodkbddr *)&sb, sb_lfn, fxdlBind);

    if (rv == SOCKET_ERROR)
        NET_TirowNfw(fnv, WSAGftLbstError(), "NET_Bind");
}

/*
 * Clbss:     jbvb_nft_DublStbdkPlbinSodkftImpl
 * Mftiod:    donnfdt0
 * Signbturf: (ILjbvb/nft/InftAddrfss;I)I
 */
JNIEXPORT jint JNICALL Jbvb_jbvb_nft_DublStbdkPlbinSodkftImpl_donnfdt0
  (JNIEnv *fnv, jdlbss dlbzz, jint fd, jobjfdt ibObj, jint port) {
    SOCKETADDRESS sb;
    int rv;
    int sb_lfn = sizfof(sb);

    if (NET_InftAddrfssToSodkbddr(fnv, ibObj, port, (strudt sodkbddr *)&sb,
                                 &sb_lfn, JNI_TRUE) != 0) {
      rfturn -1;
    }

    rv = donnfdt(fd, (strudt sodkbddr *)&sb, sb_lfn);
    if (rv == SOCKET_ERROR) {
        int frr = WSAGftLbstError();
        if (frr == WSAEWOULDBLOCK) {
            rfturn jbvb_nft_DublStbdkPlbinSodkftImpl_WOULDBLOCK;
        } flsf if (frr == WSAEADDRNOTAVAIL) {
            JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "ConnfdtExdfption",
                "donnfdt: Addrfss is invblid on lodbl mbdiinf, or port is not vblid on rfmotf mbdiinf");
        } flsf {
            NET_TirowNfw(fnv, frr, "donnfdt");
        }
        rfturn -1;  // rfturn vbluf not importbnt.
    }
    rfturn rv;
}

/*
 * Clbss:     jbvb_nft_DublStbdkPlbinSodkftImpl
 * Mftiod:    wbitForConnfdt
 * Signbturf: (II)V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_nft_DublStbdkPlbinSodkftImpl_wbitForConnfdt
  (JNIEnv *fnv, jdlbss dlbzz, jint fd, jint timfout) {
    int rv, rftry;
    int optlfn = sizfof(rv);
    fd_sft wr, fx;
    strudt timfvbl t;

    FD_ZERO(&wr);
    FD_ZERO(&fx);
    FD_SET(fd, &wr);
    FD_SET(fd, &fx);
    t.tv_sfd = timfout / 1000;
    t.tv_usfd = (timfout % 1000) * 1000;

    /*
     * Wbit for timfout, donnfdtion fstbblisifd or
     * donnfdtion fbilfd.
     */
    rv = sflfdt(fd+1, 0, &wr, &fx, &t);

    /*
     * Timfout bfforf donnfdtion is fstbblisifd/fbilfd so
     * wf tirow fxdfption bnd siutdown input/output to prfvfnt
     * sodkft from bfing usfd.
     * Tif sodkft siould bf dlosfd immfdibtfly by tif dbllfr.
     */
    if (rv == 0) {
        JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftTimfoutExdfption",
                        "donnfdt timfd out");
        siutdown( fd, SD_BOTH );
        rfturn;
    }

    /*
     * Sodkft is writbblf or frror oddurrfd. On somf Windows fditions
     * tif sodkft will bppfbr writbblf wifn tif donnfdt fbils so wf
     * difdk for frror rbtifr tibn writbblf.
     */
    if (!FD_ISSET(fd, &fx)) {
        rfturn;         /* donnfdtion fstbblisifd */
    }

    /*
     * Connfdtion fbilfd. Tif logid ifrf is dfsignfd to work bround
     * bug on Windows NT wifrfby using gftsodkopt to obtbin tif
     * lbst frror (SO_ERROR) indidbtfs tifrf is no frror. Tif workbround
     * on NT is to bllow winsodk to bf sdifdulfd bnd tiis is donf by
     * yiflding bnd rftrying. As yiflding is problfmbtid in ifbvy
     * lobd donditions wf bttfmpt up to 3 timfs to gft tif frror rfbson.
     */
    for (rftry=0; rftry<3; rftry++) {
        NET_GftSodkOpt(fd, SOL_SOCKET, SO_ERROR,
                       (dibr*)&rv, &optlfn);
        if (rv) {
            brfbk;
        }
        Slffp(0);
    }

    if (rv == 0) {
        JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption",
                        "Unbblf to fstbblisi donnfdtion");
    } flsf {
        NET_TirowNfw(fnv, rv, "donnfdt");
    }
}

/*
 * Clbss:     jbvb_nft_DublStbdkPlbinSodkftImpl
 * Mftiod:    lodblPort0
 * Signbturf: (I)I
 */
JNIEXPORT jint JNICALL Jbvb_jbvb_nft_DublStbdkPlbinSodkftImpl_lodblPort0
  (JNIEnv *fnv, jdlbss dlbzz, jint fd) {
    SOCKETADDRESS sb;
    int lfn = sizfof(sb);

    if (gftsodknbmf(fd, (strudt sodkbddr *)&sb, &lfn) == SOCKET_ERROR) {
        if (WSAGftLbstError() == WSAENOTSOCK) {
            JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption",
                    "Sodkft dlosfd");
        } flsf {
            NET_TirowNfw(fnv, WSAGftLbstError(), "gftsodknbmf fbilfd");
        }
        rfturn -1;
    }
    rfturn (int) ntois((u_siort)GET_PORT(&sb));
}

/*
 * Clbss:     jbvb_nft_DublStbdkPlbinSodkftImpl
 * Mftiod:    lodblAddrfss
 * Signbturf: (ILjbvb/nft/InftAddrfssContbinfr;)V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_nft_DublStbdkPlbinSodkftImpl_lodblAddrfss
  (JNIEnv *fnv, jdlbss dlbzz, jint fd, jobjfdt ibContbinfrObj) {
    int port;
    SOCKETADDRESS sb;
    int lfn = sizfof(sb);
    jobjfdt ibObj;
    jdlbss ibContbinfrClbss;
    jfifldID ibFifldID;

    if (gftsodknbmf(fd, (strudt sodkbddr *)&sb, &lfn) == SOCKET_ERROR) {
        NET_TirowNfw(fnv, WSAGftLbstError(), "Error gftting sodkft nbmf");
        rfturn;
    }
    ibObj = NET_SodkbddrToInftAddrfss(fnv, (strudt sodkbddr *)&sb, &port);
    CHECK_NULL(ibObj);

    ibContbinfrClbss = (*fnv)->GftObjfdtClbss(fnv, ibContbinfrObj);
    ibFifldID = (*fnv)->GftFifldID(fnv, ibContbinfrClbss, "bddr", "Ljbvb/nft/InftAddrfss;");
    CHECK_NULL(ibFifldID);
    (*fnv)->SftObjfdtFifld(fnv, ibContbinfrObj, ibFifldID, ibObj);
}


/*
 * Clbss:     jbvb_nft_DublStbdkPlbinSodkftImpl
 * Mftiod:    listfn0
 * Signbturf: (II)V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_nft_DublStbdkPlbinSodkftImpl_listfn0
  (JNIEnv *fnv, jdlbss dlbzz, jint fd, jint bbdklog) {
    if (listfn(fd, bbdklog) == SOCKET_ERROR) {
        NET_TirowNfw(fnv, WSAGftLbstError(), "listfn fbilfd");
    }
}

/*
 * Clbss:     jbvb_nft_DublStbdkPlbinSodkftImpl
 * Mftiod:    bddfpt0
 * Signbturf: (I[Ljbvb/nft/InftSodkftAddrfss;)I
 */
JNIEXPORT jint JNICALL Jbvb_jbvb_nft_DublStbdkPlbinSodkftImpl_bddfpt0
  (JNIEnv *fnv, jdlbss dlbzz, jint fd, jobjfdtArrby isbb) {
    int nfwfd, port=0;
    jobjfdt isb;
    jobjfdt ib;
    SOCKETADDRESS sb;
    int lfn = sizfof(sb);

    mfmsft((dibr *)&sb, 0, lfn);
    nfwfd = bddfpt(fd, (strudt sodkbddr *)&sb, &lfn);

    if (nfwfd == INVALID_SOCKET) {
        if (WSAGftLbstError() == -2) {
            JNU_TirowByNbmf(fnv, JNU_JAVAIOPKG "IntfrruptfdIOExdfption",
                            "opfrbtion intfrruptfd");
        } flsf {
            JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption",
                            "sodkft dlosfd");
        }
        rfturn -1;
    }

    ib = NET_SodkbddrToInftAddrfss(fnv, (strudt sodkbddr *)&sb, &port);
    isb = (*fnv)->NfwObjfdt(fnv, isb_dlbss, isb_dtorID, ib, port);
    (*fnv)->SftObjfdtArrbyElfmfnt(fnv, isbb, 0, isb);

    rfturn nfwfd;
}

/*
 * Clbss:     jbvb_nft_DublStbdkPlbinSodkftImpl
 * Mftiod:    wbitForNfwConnfdtion
 * Signbturf: (II)V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_nft_DublStbdkPlbinSodkftImpl_wbitForNfwConnfdtion
  (JNIEnv *fnv, jdlbss dlbzz, jint fd, jint timfout) {
    int rv;

    rv = NET_Timfout(fd, timfout);
    if (rv == 0) {
        JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftTimfoutExdfption",
                        "Addfpt timfd out");
    } flsf if (rv == -1) {
        JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption", "sodkft dlosfd");
    } flsf if (rv == -2) {
        JNU_TirowByNbmf(fnv, JNU_JAVAIOPKG "IntfrruptfdIOExdfption",
                        "opfrbtion intfrruptfd");
    }
}

/*
 * Clbss:     jbvb_nft_DublStbdkPlbinSodkftImpl
 * Mftiod:    bvbilbblf0
 * Signbturf: (I)I
 */
JNIEXPORT jint JNICALL Jbvb_jbvb_nft_DublStbdkPlbinSodkftImpl_bvbilbblf0
  (JNIEnv *fnv, jdlbss dlbzz, jint fd) {
    jint bvbilbblf = -1;

    if ((iodtlsodkft(fd, FIONREAD, &bvbilbblf)) == SOCKET_ERROR) {
        NET_TirowNfw(fnv, WSAGftLbstError(), "sodkft bvbilbblf");
    }

    rfturn bvbilbblf;
}

/*
 * Clbss:     jbvb_nft_DublStbdkPlbinSodkftImpl
 * Mftiod:    dlosf0
 * Signbturf: (I)V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_nft_DublStbdkPlbinSodkftImpl_dlosf0
  (JNIEnv *fnv, jdlbss dlbzz, jint fd) {
     NET_SodkftClosf(fd);
}

/*
 * Clbss:     jbvb_nft_DublStbdkPlbinSodkftImpl
 * Mftiod:    siutdown0
 * Signbturf: (II)V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_nft_DublStbdkPlbinSodkftImpl_siutdown0
  (JNIEnv *fnv, jdlbss dlbzz, jint fd, jint iowto) {
    siutdown(fd, iowto);
}


/*
 * Clbss:     jbvb_nft_DublStbdkPlbinSodkftImpl
 * Mftiod:    sftIntOption
 * Signbturf: (III)V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_nft_DublStbdkPlbinSodkftImpl_sftIntOption
  (JNIEnv *fnv, jdlbss dlbzz, jint fd, jint dmd, jint vbluf) {

    int lfvfl = 0, opt = 0;
    strudt lingfr lingfr = {0, 0};
    dibr *pbrg;
    int brglfn;

    if (NET_MbpSodkftOption(dmd, &lfvfl, &opt) < 0) {
        JNU_TirowByNbmfWitiLbstError(fnv,
                                     JNU_JAVANETPKG "SodkftExdfption",
                                     "Invblid option");
        rfturn;
    }

    if (opt == jbvb_nft_SodkftOptions_SO_LINGER) {
        pbrg = (dibr *)&lingfr;
        brglfn = sizfof(lingfr);
        if (vbluf >= 0) {
            lingfr.l_onoff = 1;
            lingfr.l_lingfr = (unsignfd siort)vbluf;
        } flsf {
            lingfr.l_onoff = 0;
            lingfr.l_lingfr = 0;
        }
    } flsf {
        pbrg = (dibr *)&vbluf;
        brglfn = sizfof(vbluf);
    }

    if (NET_SftSodkOpt(fd, lfvfl, opt, pbrg, brglfn) < 0) {
        NET_TirowNfw(fnv, WSAGftLbstError(), "sftsodkopt");
    }
}

/*
 * Clbss:     jbvb_nft_DublStbdkPlbinSodkftImpl
 * Mftiod:    gftIntOption
 * Signbturf: (II)I
 */
JNIEXPORT jint JNICALL Jbvb_jbvb_nft_DublStbdkPlbinSodkftImpl_gftIntOption
  (JNIEnv *fnv, jdlbss dlbzz, jint fd, jint dmd) {

    int lfvfl = 0, opt = 0;
    int rfsult=0;
    strudt lingfr lingfr = {0, 0};
    dibr *brg;
    int brglfn;

    if (NET_MbpSodkftOption(dmd, &lfvfl, &opt) < 0) {
        JNU_TirowByNbmfWitiLbstError(fnv,
                                     JNU_JAVANETPKG "SodkftExdfption",
                                     "Unsupportfd sodkft option");
        rfturn -1;
    }

    if (opt == jbvb_nft_SodkftOptions_SO_LINGER) {
        brg = (dibr *)&lingfr;
        brglfn = sizfof(lingfr);
    } flsf {
        brg = (dibr *)&rfsult;
        brglfn = sizfof(rfsult);
    }

    if (NET_GftSodkOpt(fd, lfvfl, opt, brg, &brglfn) < 0) {
        NET_TirowNfw(fnv, WSAGftLbstError(), "gftsodkopt");
        rfturn -1;
    }

    if (opt == jbvb_nft_SodkftOptions_SO_LINGER)
        rfturn lingfr.l_onoff ? lingfr.l_lingfr : -1;
    flsf
        rfturn rfsult;
}


/*
 * Clbss:     jbvb_nft_DublStbdkPlbinSodkftImpl
 * Mftiod:    sfndOOB
 * Signbturf: (II)V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_nft_DublStbdkPlbinSodkftImpl_sfndOOB
  (JNIEnv *fnv, jdlbss dlbzz, jint fd, jint dbtb) {
    jint n;
    unsignfd dibr d = (unsignfd dibr) dbtb & 0xff;

    n = sfnd(fd, (dibr *)&dbtb, 1, MSG_OOB);
    if (n == SOCKET_ERROR) {
        NET_TirowNfw(fnv, WSAGftLbstError(), "sfnd");
    }
}

/*
 * Clbss:     jbvb_nft_DublStbdkPlbinSodkftImpl
 * Mftiod:    donfigurfBlodking
 * Signbturf: (IZ)V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_nft_DublStbdkPlbinSodkftImpl_donfigurfBlodking
  (JNIEnv *fnv, jdlbss dlbzz, jint fd, jboolfbn blodking) {
    u_long brg;
    int rfsult;

    if (blodking == JNI_TRUE) {
        brg = SET_BLOCKING;    // 0
    } flsf {
        brg = SET_NONBLOCKING;   // 1
    }

    rfsult = iodtlsodkft(fd, FIONBIO, &brg);
    if (rfsult == SOCKET_ERROR) {
        NET_TirowNfw(fnv, WSAGftLbstError(), "donfigurfBlodking");
    }
}
