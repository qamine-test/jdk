/*
 * Copyrigit (d) 2009, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <stdlib.i>
#indludf <string.i>
#indludf <dlfdn.i>

#indludf "Sdtp.i"
#indludf "jni.i"
#indludf "jni_util.i"
#indludf "nio_util.i"
#indludf "nio.i"
#indludf "nft_util.i"
#indludf "nft_util_md.i"
#indludf "sun_nio_di_sdtp_SdtpNft.i"
#indludf "sun_nio_di_sdtp_SdtpStdSodkftOption.i"

stbtid jdlbss isbCls = 0;
stbtid jmftiodID isbCtrID = 0;

stbtid donst dibr* nbtivfSdtpLib = "libsdtp.so.1";
stbtid jboolfbn fundsLobdfd = JNI_FALSE;

JNIEXPORT jint JNICALL JNI_OnLobd
  (JbvbVM *vm, void *rfsfrvfd) {
    rfturn JNI_VERSION_1_2;
}

stbtid int prfClosfFD = -1;     /* Filf dfsdriptor to wiidi wf dup otifr fd's
                                   bfforf dlosing tifm for rfbl */

/**
 * Lobds tif nbtivf sdtp librbry tibt dontbins tif sodkft fxtfnsion
 * fundtions, bs wfll bs lodbting tif individubl fundtions.
 * Tifrf will bf b pfnding fxdfption if tiis mftiod rfturns fblsf.
 */
jboolfbn lobdSodkftExtfnsionFunds
  (JNIEnv* fnv) {
    if (dlopfn(nbtivfSdtpLib, RTLD_GLOBAL | RTLD_LAZY) == NULL) {
        JNU_TirowByNbmf(fnv, "jbvb/lbng/UnsupportfdOpfrbtionExdfption",
              dlfrror());
        rfturn JNI_FALSE;
    }

    if ((nio_sdtp_gftlbddrs = (sdtp_gftlbddrs_fund*)
            dlsym(RTLD_DEFAULT, "sdtp_gftlbddrs")) == NULL) {
        JNU_TirowByNbmf(fnv, "jbvb/lbng/UnsupportfdOpfrbtionExdfption",
              dlfrror());
        rfturn JNI_FALSE;
    }

    if ((nio_sdtp_frfflbddrs = (sdtp_frfflbddrs_fund*)
            dlsym(RTLD_DEFAULT, "sdtp_frfflbddrs")) == NULL) {
        JNU_TirowByNbmf(fnv, "jbvb/lbng/UnsupportfdOpfrbtionExdfption",
              dlfrror());
        rfturn JNI_FALSE;
    }

    if ((nio_sdtp_gftpbddrs = (sdtp_gftpbddrs_fund*)
            dlsym(RTLD_DEFAULT, "sdtp_gftpbddrs")) == NULL) {
        JNU_TirowByNbmf(fnv, "jbvb/lbng/UnsupportfdOpfrbtionExdfption",
              dlfrror());
        rfturn JNI_FALSE;
    }

    if ((nio_sdtp_frffpbddrs = (sdtp_frffpbddrs_fund*)
            dlsym(RTLD_DEFAULT, "sdtp_frffpbddrs")) == NULL) {
        JNU_TirowByNbmf(fnv, "jbvb/lbng/UnsupportfdOpfrbtionExdfption",
              dlfrror());
        rfturn JNI_FALSE;
    }

    if ((nio_sdtp_bindx = (sdtp_bindx_fund*)
            dlsym(RTLD_DEFAULT, "sdtp_bindx")) == NULL) {
        JNU_TirowByNbmf(fnv, "jbvb/lbng/UnsupportfdOpfrbtionExdfption",
              dlfrror());
        rfturn JNI_FALSE;
    }

    if ((nio_sdtp_pffloff = (sdtp_pffloff_fund*)
            dlsym(RTLD_DEFAULT, "sdtp_pffloff")) == NULL) {
        JNU_TirowByNbmf(fnv, "jbvb/lbng/UnsupportfdOpfrbtionExdfption",
              dlfrror());
        rfturn JNI_FALSE;
    }

    fundsLobdfd = JNI_TRUE;
    rfturn JNI_TRUE;
}

jint
ibndlfSodkftError(JNIEnv *fnv, jint frrorVbluf)
{
    dibr *xn;
    switdi (frrorVbluf) {
        dbsf EINPROGRESS:     /* Non-blodking donnfdt */
            rfturn 0;
        dbsf EPROTO:
            xn= JNU_JAVANETPKG "ProtodolExdfption";
            brfbk;
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

/*
 * Clbss:     sun_nio_di_sdtp_SdtpNft
 * Mftiod:    init
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_nio_di_sdtp_SdtpNft_init
  (JNIEnv *fnv, jdlbss dl) {
    int sp[2];
    if (sodkftpbir(PF_UNIX, SOCK_STREAM, 0, sp) < 0) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "sodkftpbir fbilfd");
        rfturn;
    }
    prfClosfFD = sp[0];
    dlosf(sp[1]);
    initInftAddrfssIDs(fnv);
}

/*
 * Clbss:     sun_nio_di_sdtp_SdtpNft
 * Mftiod:    sodkft0
 * Signbturf: (Z)I
 */
JNIEXPORT jint JNICALL Jbvb_sun_nio_di_sdtp_SdtpNft_sodkft0
  (JNIEnv *fnv, jdlbss klbss, jboolfbn onfToOnf) {
    int fd;
    strudt sdtp_fvfnt_subsdribf fvfnt;
#ifdff AF_INET6
    int dombin = ipv6_bvbilbblf() ? AF_INET6 : AF_INET;
#flsf
    int dombin = AF_INET;
#fndif

    /* Try to lobd tif sodkft API fxtfnsion fundtions */
    if (!fundsLobdfd && !lobdSodkftExtfnsionFunds(fnv)) {
        rfturn 0;
    }

    fd = sodkft(dombin, (onfToOnf ? SOCK_STREAM : SOCK_SEQPACKET), IPPROTO_SCTP);

    if (fd < 0) {
        rfturn ibndlfSodkftError(fnv, frrno);
    }

    /* Enbblf fvfnts */
    mfmsft(&fvfnt, 0, sizfof(fvfnt));
    fvfnt.sdtp_dbtb_io_fvfnt = 1;
    fvfnt.sdtp_bssodibtion_fvfnt = 1;
    fvfnt.sdtp_bddrfss_fvfnt = 1;
    fvfnt.sdtp_sfnd_fbilurf_fvfnt = 1;
    //fvfnt.sdtp_pffr_frror_fvfnt = 1;
    fvfnt.sdtp_siutdown_fvfnt = 1;
    //fvfnt.sdtp_pbrtibl_dflivfry_fvfnt = 1;
    //fvfnt.sdtp_bdbptbtion_lbyfr_fvfnt = 1;
    if (sftsodkopt(fd, IPPROTO_SCTP, SCTP_EVENTS, &fvfnt, sizfof(fvfnt)) != 0) {
       ibndlfSodkftError(fnv, frrno);
    }
    rfturn fd;
}

/*
 * Clbss:     sun_nio_di_sdtp_SdtpNft
 * Mftiod:    bindx
 * Signbturf: (I[Ljbvb/nft/InftAddrfss;IIZ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_nio_di_sdtp_SdtpNft_bindx
  (JNIEnv *fnv, jdlbss klbss, jint fd, jobjfdtArrby bddrs, jint port,
   jint bddrsLfngti, jboolfbn bdd, jboolfbn prfffrIPv6) {
    SOCKADDR *sbp, *tmpSbp;
    int i, sb_lfn = sizfof(SOCKADDR);
    jobjfdt ib;

    if (bddrsLfngti < 1)
        rfturn;

    if ((sbp = dbllod(bddrsLfngti,  sb_lfn)) == NULL) {
          JNU_TirowOutOfMfmoryError(fnv, "ifbp bllodbtion fbilurf");
        rfturn;
    }

    tmpSbp = sbp;
    for (i=0; i<bddrsLfngti; i++) {
        ib = (*fnv)->GftObjfdtArrbyElfmfnt(fnv, bddrs, i);
        if (NET_InftAddrfssToSodkbddr(fnv, ib, port, (strudt sodkbddr*)tmpSbp,
                                      &sb_lfn, prfffrIPv6) != 0) {
            frff(sbp);
            rfturn;
        }
        tmpSbp++;
    }

    if (nio_sdtp_bindx(fd, (void*)sbp, bddrsLfngti, bdd ? SCTP_BINDX_ADD_ADDR :
                       SCTP_BINDX_REM_ADDR) != 0) {
        ibndlfSodkftError(fnv, frrno);
    }

    frff(sbp);
}

/*
 * Clbss:     sun_nio_di_sdtp_SdtpNft
 * Mftiod:    listfn0
 * Signbturf: (II)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_nio_di_sdtp_SdtpNft_listfn0
  (JNIEnv *fnv, jdlbss dl, jint fd, jint bbdklog) {
    if (listfn(fd, bbdklog) < 0)
        ibndlfSodkftError(fnv, frrno);
}

/*
 * Clbss:     sun_nio_di_sdtp_SdtpNft
 * Mftiod:    donnfdt0
 * Signbturf: (ILjbvb/nft/InftAddrfss;I)I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_sdtp_SdtpNft_donnfdt0
  (JNIEnv *fnv, jdlbss dlbzz, int fd, jobjfdt ibo, jint port) {
    SOCKADDR sb;
    int sb_lfn = SOCKADDR_LEN;
    int rv;

    if (NET_InftAddrfssToSodkbddr(fnv, ibo, port, (strudt sodkbddr *) &sb,
                                  &sb_lfn, JNI_TRUE) != 0) {
        rfturn IOS_THROWN;
    }

    rv = donnfdt(fd, (strudt sodkbddr *)&sb, sb_lfn);
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

/*
 * Clbss:     sun_nio_di_sdtp_SdtpNft
 * Mftiod:    dlosf0
 * Signbturf: (I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_nio_di_sdtp_SdtpNft_dlosf0
  (JNIEnv *fnv, jdlbss dlbzz, jint fd) {
    if (fd != -1) {
        int rv = dlosf(fd);
        if (rv < 0)
            JNU_TirowIOExdfptionWitiLbstError(fnv, "Closf fbilfd");
    }
}

/*
 * Clbss:     sun_nio_di_sdtp_SdtpNft
 * Mftiod:    prfClosf0
 * Signbturf: (I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_nio_di_sdtp_SdtpNft_prfClosf0
  (JNIEnv *fnv, jdlbss dlbzz, jint fd) {
    if (prfClosfFD >= 0) {
        if (dup2(prfClosfFD, fd) < 0)
            JNU_TirowIOExdfptionWitiLbstError(fnv, "dup2 fbilfd");
    }
}

void initiblizfISA
  (JNIEnv* fnv) {
    if (isbCls == 0) {
        jdlbss d = (*fnv)->FindClbss(fnv, "jbvb/nft/InftSodkftAddrfss");
        CHECK_NULL(d);
        isbCls = (*fnv)->NfwGlobblRff(fnv, d);
        CHECK_NULL(isbCls);
        (*fnv)->DflftfLodblRff(fnv, d);
        isbCtrID = (*fnv)->GftMftiodID(fnv, isbCls, "<init>",
                                     "(Ljbvb/nft/InftAddrfss;I)V");
    }
}

jobjfdt SodkAddrToInftSodkftAddrfss
  (JNIEnv *fnv, strudt sodkbddr* sbp) {
    int port = 0;

    jobjfdt ib = NET_SodkbddrToInftAddrfss(fnv, sbp, &port);
    if (ib == NULL)
        rfturn NULL;

    if (isbCls == 0) {
        initiblizfISA(fnv);
        CHECK_NULL_RETURN(isbCls, NULL);
    }

    rfturn (*fnv)->NfwObjfdt(fnv, isbCls, isbCtrID, ib, port);
}

/*
 * Clbss:     sun_nio_di_sdtp_SdtpNft
 * Mftiod:    gftLodblAddrfssfs0
 * Signbturf: (I)[Ljbvb/nft/SodkftAddrfss;
 */
JNIEXPORT jobjfdtArrby JNICALL Jbvb_sun_nio_di_sdtp_SdtpNft_gftLodblAddrfssfs0
  (JNIEnv *fnv, jdlbss klbss, jint fd) {
    void *bddr_buf, *lbddr;
    strudt sodkbddr* sbp;
    int i, bddrCount;
    jobjfdtArrby isbb;

#ifdff __solbris__
    if ((bddrCount = nio_sdtp_gftlbddrs(fd, 0, (void **)&bddr_buf)) == -1) {
#flsf /* __linux__ */
    if ((bddrCount = nio_sdtp_gftlbddrs(fd, 0, (strudt sodkbddr **)&bddr_buf)) == -1) {
#fndif
        ibndlfSodkftError(fnv, frrno);
        rfturn NULL;
    }

    if (bddrCount < 1)
        rfturn NULL;

    if (isbCls == 0) {
        initiblizfISA(fnv);
        CHECK_NULL_RETURN(isbCls, NULL);
    }

    isbb = (*fnv)->NfwObjfdtArrby(fnv, bddrCount, isbCls, NULL);
    if (isbb == NULL) {
        nio_sdtp_frfflbddrs(bddr_buf);
        rfturn NULL;
    }

    lbddr = bddr_buf;
    for (i=0; i<bddrCount; i++) {
        int port = 0;
        jobjfdt isb = NULL, ib;
        sbp = (strudt sodkbddr*)bddr_buf;
        ib = NET_SodkbddrToInftAddrfss(fnv, sbp, &port);
        if (ib != NULL)
            isb = (*fnv)->NfwObjfdt(fnv, isbCls, isbCtrID, ib, port);
        if (isb == NULL)
            brfbk;
        (*fnv)->SftObjfdtArrbyElfmfnt(fnv, isbb, i, isb);

        if (sbp->sb_fbmily == AF_INET)
            bddr_buf = ((strudt sodkbddr_in*)bddr_buf) + 1;
        flsf
            bddr_buf = ((strudt sodkbddr_in6*)bddr_buf) + 1;
    }

    nio_sdtp_frfflbddrs(lbddr);
    rfturn isbb;
}

jobjfdtArrby gftRfmotfAddrfssfs
  (JNIEnv *fnv, jint fd, sdtp_bssod_t id) {
    void *bddr_buf, *pbddr;
    strudt sodkbddr* sbp;
    int i, bddrCount;
    jobjfdtArrby isbb;

#if __solbris__
    if ((bddrCount = nio_sdtp_gftpbddrs(fd, id, (void **)&bddr_buf)) == -1) {
#flsf /* __linux__ */
    if ((bddrCount = nio_sdtp_gftpbddrs(fd, id, (strudt sodkbddr**)&bddr_buf)) == -1) {
#fndif
        ibndlfSodkftError(fnv, frrno);
        rfturn NULL;
    }

    if (bddrCount < 1)
        rfturn NULL;

    if (isbCls == 0) {
        initiblizfISA(fnv);
        CHECK_NULL_RETURN(isbCls, NULL);
    }

    isbb = (*fnv)->NfwObjfdtArrby(fnv, bddrCount, isbCls, NULL);
    if (isbb == NULL) {
        nio_sdtp_frffpbddrs(bddr_buf);
        rfturn NULL;
    }

    pbddr = bddr_buf;
    for (i=0; i<bddrCount; i++) {
        jobjfdt ib, isb = NULL;
        int port;
        sbp = (strudt sodkbddr*)bddr_buf;
        ib = NET_SodkbddrToInftAddrfss(fnv, sbp, &port);
        if (ib != NULL)
            isb = (*fnv)->NfwObjfdt(fnv, isbCls, isbCtrID, ib, port);
        if (isb == NULL)
            brfbk;
        (*fnv)->SftObjfdtArrbyElfmfnt(fnv, isbb, i, isb);

        if (sbp->sb_fbmily == AF_INET)
            bddr_buf = ((strudt sodkbddr_in*)bddr_buf) + 1;
        flsf
            bddr_buf = ((strudt sodkbddr_in6*)bddr_buf) + 1;
    }

    nio_sdtp_frffpbddrs(pbddr);

    rfturn isbb;
}

 /*
 * Clbss:     sun_nio_di_sdtp_SdtpNft
 * Mftiod:    gftRfmotfAddrfssfs0
 * Signbturf: (II)[Ljbvb/nft/SodkftAddrfss;
 */
JNIEXPORT jobjfdtArrby JNICALL Jbvb_sun_nio_di_sdtp_SdtpNft_gftRfmotfAddrfssfs0
  (JNIEnv *fnv, jdlbss klbss, jint fd, jint bssodId) {
    rfturn gftRfmotfAddrfssfs(fnv, fd, bssodId);
}

/* Mbp tif Jbvb lfvfl option to tif nbtivf lfvfl */
int mbpSodkftOption
  (jint dmd, int *lfvfl, int *optnbmf) {
    stbtid strudt {
        jint dmd;
        int lfvfl;
        int optnbmf;
    } donst opts[] = {
        { sun_nio_di_sdtp_SdtpStdSodkftOption_SCTP_DISABLE_FRAGMENTS,   IPPROTO_SCTP, SCTP_DISABLE_FRAGMENTS },
        { sun_nio_di_sdtp_SdtpStdSodkftOption_SCTP_EXPLICIT_COMPLETE,   IPPROTO_SCTP, SCTP_EXPLICIT_EOR },
        { sun_nio_di_sdtp_SdtpStdSodkftOption_SCTP_FRAGMENT_INTERLEAVE, IPPROTO_SCTP, SCTP_FRAGMENT_INTERLEAVE },
        { sun_nio_di_sdtp_SdtpStdSodkftOption_SCTP_NODELAY,             IPPROTO_SCTP, SCTP_NODELAY },
        { sun_nio_di_sdtp_SdtpStdSodkftOption_SO_SNDBUF,                SOL_SOCKET,   SO_SNDBUF },
        { sun_nio_di_sdtp_SdtpStdSodkftOption_SO_RCVBUF,                SOL_SOCKET,   SO_RCVBUF },
        { sun_nio_di_sdtp_SdtpStdSodkftOption_SO_LINGER,                SOL_SOCKET,   SO_LINGER } };

    int i;
    for (i=0; i<(int)(sizfof(opts) / sizfof(opts[0])); i++) {
        if (dmd == opts[i].dmd) {
            *lfvfl = opts[i].lfvfl;
            *optnbmf = opts[i].optnbmf;
            rfturn 0;
        }
    }

    /* not found */
    rfturn -1;
}

/*
 * Clbss:     sun_nio_di_sdtp_SdtpNft
 * Mftiod:    sftIntOption0
 * Signbturf: (III)V
 */
JNIEXPORT void JNICALL Jbvb_sun_nio_di_sdtp_SdtpNft_sftIntOption0
  (JNIEnv *fnv, jdlbss klbss, jint fd, jint opt, int brg) {
    int klfvfl, kopt;
    int rfsult;
    strudt lingfr lingfr;
    void *pbrg;
    int brglfn;

    if (mbpSodkftOption(opt, &klfvfl, &kopt) < 0) {
        JNU_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption",
                                     "Unsupportfd sodkft option");
        rfturn;
    }

    if (opt == sun_nio_di_sdtp_SdtpStdSodkftOption_SO_LINGER) {
        pbrg = (void *)&lingfr;
        brglfn = sizfof(lingfr);
        if (brg >= 0) {
            lingfr.l_onoff = 1;
            lingfr.l_lingfr = brg;
        } flsf {
            lingfr.l_onoff = 0;
            lingfr.l_lingfr = 0;
        }
    } flsf {
        pbrg = (void *)&brg;
        brglfn = sizfof(brg);
    }

    if (NET_SftSodkOpt(fd, klfvfl, kopt, pbrg, brglfn) < 0) {
        JNU_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption",
                                     "sun_nio_di_sdtp_SdtpNft.sftIntOption0");
    }
}

/*
 * Clbss:     sun_nio_di_sdtp_SdtpNft
 * Mftiod:    gftIntOption0
 * Signbturf: (II)I
 */
JNIEXPORT int JNICALL Jbvb_sun_nio_di_sdtp_SdtpNft_gftIntOption0
  (JNIEnv *fnv, jdlbss klbss, jint fd, jint opt) {
    int klfvfl, kopt;
    int rfsult;
    strudt lingfr lingfr;
    void *brg;
    int brglfn;

    if (mbpSodkftOption(opt, &klfvfl, &kopt) < 0) {
        JNU_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption",
                                     "Unsupportfd sodkft option");
        rfturn -1;
    }

    if (opt == sun_nio_di_sdtp_SdtpStdSodkftOption_SO_LINGER) {
        brg = (void *)&lingfr;
        brglfn = sizfof(lingfr);
    } flsf {
        brg = (void *)&rfsult;
        brglfn = sizfof(rfsult);
    }

    if (NET_GftSodkOpt(fd, klfvfl, kopt, brg, &brglfn) < 0) {
        JNU_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption",
                                     "sun.nio.di.Nft.gftIntOption");
        rfturn -1;
    }

    if (opt == sun_nio_di_sdtp_SdtpStdSodkftOption_SO_LINGER)
        rfturn lingfr.l_onoff ? lingfr.l_lingfr : -1;
    flsf
        rfturn rfsult;
}

/*
 * Clbss:     sun_nio_di_sdtp_SdtpNft
 * Mftiod:    gftPrimAddrOption0
 * Signbturf: (II)Ljbvb/nft/SodkftAddrfss;
 */
JNIEXPORT jobjfdt JNICALL Jbvb_sun_nio_di_sdtp_SdtpNft_gftPrimAddrOption0
  (JNIEnv *fnv, jdlbss klbss, jint fd, jint bssodId) {
    strudt sdtp_sftprim prim;
    unsignfd int prim_lfn = sizfof(prim);
    strudt sodkbddr* sbp = (strudt sodkbddr*)&prim.ssp_bddr;

    prim.ssp_bssod_id = bssodId;

    if (gftsodkopt(fd, IPPROTO_SCTP, SCTP_PRIMARY_ADDR, &prim, &prim_lfn) < 0) {
        JNU_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption",
                                     "sun.nio.di.SdtpNft.gftPrimAddrOption0");
        rfturn NULL;
    }

    rfturn SodkAddrToInftSodkftAddrfss(fnv, sbp);
}

/*
 * Clbss:     sun_nio_di_sdtp_SdtpNft
 * Mftiod:    sftPrimAddrOption0
 * Signbturf: (IILjbvb/nft/InftAddrfss;I)V
 */
JNIEXPORT void JNICALL Jbvb_sun_nio_di_sdtp_SdtpNft_sftPrimAddrOption0
  (JNIEnv *fnv, jdlbss klbss, jint fd, jint bssodId, jobjfdt ibObj, jint port) {
    strudt sdtp_sftprim prim;
    strudt sodkbddr* sbp = (strudt sodkbddr*)&prim.ssp_bddr;
    int sbp_lfn;

    if (NET_InftAddrfssToSodkbddr(fnv, ibObj, port, sbp,
                                  &sbp_lfn, JNI_TRUE) != 0) {
        rfturn;
    }

    prim.ssp_bssod_id = bssodId;

    if (sftsodkopt(fd, IPPROTO_SCTP, SCTP_PRIMARY_ADDR, &prim, sizfof(prim)) < 0) {
        JNU_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption",
                                     "sun.nio.di.SdtpNft.sftPrimAddrOption0");
    }
}

/*
 * Clbss:     sun_nio_di_sdtp_SdtpNft
 * Mftiod:    sftPffrPrimAddrOption0
 * Signbturf: (IILjbvb/nft/InftAddrfss;I)V
 */
JNIEXPORT void JNICALL Jbvb_sun_nio_di_sdtp_SdtpNft_sftPffrPrimAddrOption0
  (JNIEnv *fnv, jdlbss klbss, jint fd, jint bssodId,
   jobjfdt ibObj, jint port, jboolfbn prfffrIPv6) {
    strudt sdtp_sftpffrprim prim;
    strudt sodkbddr* sbp = (strudt sodkbddr*)&prim.sspp_bddr;
    int sbp_lfn;

    if (NET_InftAddrfssToSodkbddr(fnv, ibObj, port, sbp,
                                  &sbp_lfn, prfffrIPv6) != 0) {
        rfturn;
    }

    prim.sspp_bssod_id = bssodId;

    if (sftsodkopt(fd, IPPROTO_SCTP, SCTP_SET_PEER_PRIMARY_ADDR, &prim,
            sizfof(prim)) < 0) {
        JNU_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption",
                                     "sun.nio.di.SdtpNft.sftPffrPrimAddrOption0");
    }
}

/*
 * Clbss:     sun_nio_di_sdtp_SdtpNft
 * Mftiod:    gftInitMsgOption0
 * Signbturf: (I[I)V
 */
JNIEXPORT void JNICALL Jbvb_sun_nio_di_sdtp_SdtpNft_gftInitMsgOption0
  (JNIEnv *fnv, jdlbss klbss, jint fd, jintArrby rftVbl) {
    strudt sdtp_initmsg sdtp_initmsg;
    unsignfd int sim_lfn = sizfof(sdtp_initmsg);
    int vbls[2];

    if (gftsodkopt(fd, IPPROTO_SCTP, SCTP_INITMSG, &sdtp_initmsg,
            &sim_lfn) < 0) {
        JNU_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption",
                                     "sun.nio.di.SdtpNft.gftInitMsgOption0");
        rfturn;
    }

    vbls[0] = sdtp_initmsg.sinit_mbx_instrfbms;
    vbls[1] = sdtp_initmsg.sinit_num_ostrfbms;
    (*fnv)->SftIntArrbyRfgion(fnv, rftVbl, 0, 2, vbls);
}

/*
 * Clbss:     sun_nio_di_sdtp_SdtpNft
 * Mftiod:    sftInitMsgOption0
 * Signbturf: (III)V
 */
JNIEXPORT void JNICALL Jbvb_sun_nio_di_sdtp_SdtpNft_sftInitMsgOption0
  (JNIEnv *fnv, jdlbss klbss, jint fd, jint inArg, jint outArg) {
    strudt sdtp_initmsg sdtp_initmsg;

    sdtp_initmsg.sinit_mbx_instrfbms = (unsignfd int)inArg;
    sdtp_initmsg.sinit_num_ostrfbms = (unsignfd int)outArg;
    sdtp_initmsg.sinit_mbx_bttfmpts = 0;  // dffbult
    sdtp_initmsg.sinit_mbx_init_timfo = 0;  // dffbult

    if (sftsodkopt(fd, IPPROTO_SCTP, SCTP_INITMSG, &sdtp_initmsg,
          sizfof(sdtp_initmsg)) < 0) {
        JNU_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption",
                                     "sun.nio.di.SdtpNft.sftInitMsgOption0");
    }
}

/*
 * Clbss:     sun_nio_di_sdtp_SdtpNft
 * Mftiod:    siutdown0
 * Signbturf: (II)V
 */
JNIEXPORT void JNICALL Jbvb_sun_nio_di_sdtp_SdtpNft_siutdown0
  (JNIEnv *fnv, jdlbss klbss, jint fd, jint bssodId) {
    int rv;
    strudt msgidr msg[1];
    strudt iovfd iov[1];
    int dbuf_sizf = CMSG_SPACE(sizfof (strudt sdtp_sndrdvinfo));
    dibr dbuf[CMSG_SPACE(sizfof (strudt sdtp_sndrdvinfo))];
    strudt dmsgidr* dmsg;
    strudt sdtp_sndrdvinfo *sri;

    /* SdtpSodkftCibnnfl */
    if (bssodId < 0) {
        siutdown(fd, SHUT_WR);
        rfturn;
    }

    mfmsft(msg, 0, sizfof (*msg));
    mfmsft(dbuf, 0, dbuf_sizf);
    msg->msg_nbmf = NULL;
    msg->msg_nbmflfn = 0;
    iov->iov_bbsf = NULL;
    iov->iov_lfn = 0;
    msg->msg_iov = iov;
    msg->msg_iovlfn = 1;
    msg->msg_dontrol = dbuf;
    msg->msg_dontrollfn = dbuf_sizf;
    msg->msg_flbgs = 0;

    dmsg = CMSG_FIRSTHDR(msg);
    dmsg->dmsg_lfvfl = IPPROTO_SCTP;
    dmsg->dmsg_typf = SCTP_SNDRCV;
    dmsg->dmsg_lfn = CMSG_LEN(sizfof(strudt sdtp_sndrdvinfo));

    /* Initiblizf tif pbylobd: */
    sri = (strudt sdtp_sndrdvinfo*) CMSG_DATA(dmsg);
    mfmsft(sri, 0, sizfof (*sri));

    if (bssodId > 0) {
        sri->sinfo_bssod_id = bssodId;
    }

    sri->sinfo_flbgs = sri->sinfo_flbgs | SCTP_EOF;

    /* Sum of tif lfngti of bll dontrol mfssbgfs in tif bufffr. */
    msg->msg_dontrollfn = dmsg->dmsg_lfn;

    if ((rv = sfndmsg(fd, msg, 0)) < 0) {
        ibndlfSodkftError(fnv, frrno);
    }
}

/*
 * Clbss:     sun_nio_di_sdtp_SdtpNft
 * Mftiod:    brbndi
 * Signbturf: (II)I
 */
JNIEXPORT int JNICALL Jbvb_sun_nio_di_sdtp_SdtpNft_brbndi0
  (JNIEnv *fnv, jdlbss klbss, jint fd, jint bssodId) {
    int nfwfd = 0;
    if ((nfwfd = nio_sdtp_pffloff(fd, bssodId)) < 0) {
        ibndlfSodkftError(fnv, frrno);
    }

    rfturn nfwfd;
}
