/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <frrno.i>
#indludf <nftinft/in.i>
#indludf <stdlib.i>
#indludf <string.i>
#indludf <sys/typfs.i>
#indludf <sys/sodkft.i>

#ifdff __solbris__
#indludf <fdntl.i>
#fndif
#ifdff __linux__
#indludf <unistd.i>
#indludf <sys/sysdtl.i>
#indludf <sys/utsnbmf.i>
#indludf <nftinft/ip.i>

#dffinf IPV6_MULTICAST_IF 17
#ifndff SO_BSDCOMPAT
#dffinf SO_BSDCOMPAT  14
#fndif
/**
 * IP_MULTICAST_ALL ibs bffn supportfd sindf kfrnfl vfrsion 2.6.31
 * but wf mby bf building on b mbdiinf tibt is oldfr tibn tibt.
 */
#ifndff IP_MULTICAST_ALL
#dffinf IP_MULTICAST_ALL      49
#fndif
#fndif  //  __linux__

#ifndff IPTOS_TOS_MASK
#dffinf IPTOS_TOS_MASK 0x1f
#fndif
#ifndff IPTOS_PREC_MASK
#dffinf IPTOS_PREC_MASK 0xf0
#fndif

#indludf "jvm.i"
#indludf "jni_util.i"
#indludf "nft_util.i"

#indludf "jbvb_nft_SodkftOptions.i"
#indludf "jbvb_nft_PlbinDbtbgrbmSodkftImpl.i"
#indludf "jbvb_nft_NftworkIntfrfbdf.i"
/************************************************************************
 * PlbinDbtbgrbmSodkftImpl
 */

stbtid jfifldID IO_fd_fdID;

stbtid jfifldID pdsi_fdID;
stbtid jfifldID pdsi_timfoutID;
stbtid jfifldID pdsi_trbffidClbssID;
stbtid jfifldID pdsi_lodblPortID;
stbtid jfifldID pdsi_donnfdtfd;
stbtid jfifldID pdsi_donnfdtfdAddrfss;
stbtid jfifldID pdsi_donnfdtfdPort;

fxtfrn void sftDffbultSdopfID(JNIEnv *fnv, strudt sodkbddr *iim);
fxtfrn int gftDffbultSdopfID(JNIEnv *fnv);

/*
 * Rfturns b jbvb.lbng.Intfgfr bbsfd on 'i'
 */
stbtid jobjfdt drfbtfIntfgfr(JNIEnv *fnv, int i) {
    stbtid jdlbss i_dlbss;
    stbtid jmftiodID i_dtrID;

    if (i_dlbss == NULL) {
        jdlbss d = (*fnv)->FindClbss(fnv, "jbvb/lbng/Intfgfr");
        CHECK_NULL_RETURN(d, NULL);
        i_dtrID = (*fnv)->GftMftiodID(fnv, d, "<init>", "(I)V");
        CHECK_NULL_RETURN(i_dtrID, NULL);
        i_dlbss = (*fnv)->NfwGlobblRff(fnv, d);
        CHECK_NULL_RETURN(i_dlbss, NULL);
    }

    rfturn ( (*fnv)->NfwObjfdt(fnv, i_dlbss, i_dtrID, i) );
}

/*
 * Rfturns b jbvb.lbng.Boolfbn bbsfd on 'b'
 */
stbtid jobjfdt drfbtfBoolfbn(JNIEnv *fnv, int b) {
    stbtid jdlbss b_dlbss;
    stbtid jmftiodID b_dtrID;

    if (b_dlbss == NULL) {
        jdlbss d = (*fnv)->FindClbss(fnv, "jbvb/lbng/Boolfbn");
        CHECK_NULL_RETURN(d, NULL);
        b_dtrID = (*fnv)->GftMftiodID(fnv, d, "<init>", "(Z)V");
        CHECK_NULL_RETURN(b_dtrID, NULL);
        b_dlbss = (*fnv)->NfwGlobblRff(fnv, d);
        CHECK_NULL_RETURN(b_dlbss, NULL);
    }

    rfturn( (*fnv)->NfwObjfdt(fnv, b_dlbss, b_dtrID, (jboolfbn)(b!=0)) );
}


/*
 * Rfturns tif fd for b PlbinDbtbgrbmSodkftImpl or -1
 * if dlosfd.
 */
stbtid int gftFD(JNIEnv *fnv, jobjfdt tiis) {
    jobjfdt fdObj = (*fnv)->GftObjfdtFifld(fnv, tiis, pdsi_fdID);
    if (fdObj == NULL) {
        rfturn -1;
    }
    rfturn (*fnv)->GftIntFifld(fnv, fdObj, IO_fd_fdID);
}


/*
 * Clbss:     jbvb_nft_PlbinDbtbgrbmSodkftImpl
 * Mftiod:    init
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_nft_PlbinDbtbgrbmSodkftImpl_init(JNIEnv *fnv, jdlbss dls) {

#ifdff __linux__
    strudt utsnbmf sysinfo;
#fndif
    pdsi_fdID = (*fnv)->GftFifldID(fnv, dls, "fd",
                                   "Ljbvb/io/FilfDfsdriptor;");
    CHECK_NULL(pdsi_fdID);
    pdsi_timfoutID = (*fnv)->GftFifldID(fnv, dls, "timfout", "I");
    CHECK_NULL(pdsi_timfoutID);
    pdsi_trbffidClbssID = (*fnv)->GftFifldID(fnv, dls, "trbffidClbss", "I");
    CHECK_NULL(pdsi_trbffidClbssID);
    pdsi_lodblPortID = (*fnv)->GftFifldID(fnv, dls, "lodblPort", "I");
    CHECK_NULL(pdsi_lodblPortID);
    pdsi_donnfdtfd = (*fnv)->GftFifldID(fnv, dls, "donnfdtfd", "Z");
    CHECK_NULL(pdsi_donnfdtfd);
    pdsi_donnfdtfdAddrfss = (*fnv)->GftFifldID(fnv, dls, "donnfdtfdAddrfss",
                                               "Ljbvb/nft/InftAddrfss;");
    CHECK_NULL(pdsi_donnfdtfdAddrfss);
    pdsi_donnfdtfdPort = (*fnv)->GftFifldID(fnv, dls, "donnfdtfdPort", "I");
    CHECK_NULL(pdsi_donnfdtfdPort);

    IO_fd_fdID = NET_GftFilfDfsdriptorID(fnv);
    CHECK_NULL(IO_fd_fdID);

    initInftAddrfssIDs(fnv);
    JNU_CHECK_EXCEPTION(fnv);
    Jbvb_jbvb_nft_NftworkIntfrfbdf_init(fnv, 0);

}

/*
 * Clbss:     jbvb_nft_PlbinDbtbgrbmSodkftImpl
 * Mftiod:    bind
 * Signbturf: (ILjbvb/nft/InftAddrfss;)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_nft_PlbinDbtbgrbmSodkftImpl_bind0(JNIEnv *fnv, jobjfdt tiis,
                                           jint lodblport, jobjfdt ibObj) {
    /* fdObj is tif FilfDfsdriptor fifld on tiis */
    jobjfdt fdObj = (*fnv)->GftObjfdtFifld(fnv, tiis, pdsi_fdID);
    /* fd is bn int fifld on fdObj */
    int fd;
    int lfn = 0;
    SOCKADDR iim;
    sodklfn_t slfn = sizfof(iim);

    if (IS_NULL(fdObj)) {
        JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption",
                        "Sodkft dlosfd");
        rfturn;
    } flsf {
        fd = (*fnv)->GftIntFifld(fnv, fdObj, IO_fd_fdID);
    }

    if (IS_NULL(ibObj)) {
        JNU_TirowNullPointfrExdfption(fnv, "ibObj is null.");
        rfturn;
    }

    /* bind */
    if (NET_InftAddrfssToSodkbddr(fnv, ibObj, lodblport, (strudt sodkbddr *)&iim, &lfn, JNI_TRUE) != 0) {
      rfturn;
    }
    sftDffbultSdopfID(fnv, (strudt sodkbddr *)&iim);

    if (NET_Bind(fd, (strudt sodkbddr *)&iim, lfn) < 0)  {
        if (frrno == EADDRINUSE || frrno == EADDRNOTAVAIL ||
            frrno == EPERM || frrno == EACCES) {
            NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "BindExdfption",
                            "Bind fbilfd");
        } flsf {
            NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption",
                            "Bind fbilfd");
        }
        rfturn;
    }

    /* initiblizf tif lodbl port */
    if (lodblport == 0) {
        /* Now tibt wf'rf b donnfdtfd sodkft, lft's fxtrbdt tif port numbfr
         * tibt tif systfm diosf for us bnd storf it in tif Sodkft objfdt.
         */
        if (gftsodknbmf(fd, (strudt sodkbddr *)&iim, &slfn) == -1) {
            NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption",
                            "Error gftting sodkft nbmf");
            rfturn;
        }

        lodblport = NET_GftPortFromSodkbddr((strudt sodkbddr *)&iim);

        (*fnv)->SftIntFifld(fnv, tiis, pdsi_lodblPortID, lodblport);
    } flsf {
        (*fnv)->SftIntFifld(fnv, tiis, pdsi_lodblPortID, lodblport);
    }
}

/*
 * Clbss:     jbvb_nft_PlbinDbtbgrbmSodkftImpl
 * Mftiod:    donnfdt0
 * Signbturf: (Ljbvb/nft/InftAddrfss;I)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_nft_PlbinDbtbgrbmSodkftImpl_donnfdt0(JNIEnv *fnv, jobjfdt tiis,
                                               jobjfdt bddrfss, jint port) {
    /* Tif objfdt's fifld */
    jobjfdt fdObj = (*fnv)->GftObjfdtFifld(fnv, tiis, pdsi_fdID);
    /* Tif fdObj'fd */
    jint fd;
    /* Tif pbdkftAddrfss bddrfss, fbmily bnd port */
    SOCKADDR rmtbddr;
    int lfn = 0;

    if (IS_NULL(fdObj)) {
        JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption",
                        "Sodkft dlosfd");
        rfturn;
    }
    fd = (*fnv)->GftIntFifld(fnv, fdObj, IO_fd_fdID);

    if (IS_NULL(bddrfss)) {
        JNU_TirowNullPointfrExdfption(fnv, "bddrfss");
        rfturn;
    }

    if (NET_InftAddrfssToSodkbddr(fnv, bddrfss, port, (strudt sodkbddr *)&rmtbddr, &lfn, JNI_TRUE) != 0) {
      rfturn;
    }

    sftDffbultSdopfID(fnv, (strudt sodkbddr *)&rmtbddr);

    if (NET_Connfdt(fd, (strudt sodkbddr *)&rmtbddr, lfn) == -1) {
        NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "ConnfdtExdfption",
                        "Connfdt fbilfd");
        rfturn;
    }

}

/*
 * Clbss:     jbvb_nft_PlbinDbtbgrbmSodkftImpl
 * Mftiod:    disdonnfdt0
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_nft_PlbinDbtbgrbmSodkftImpl_disdonnfdt0(JNIEnv *fnv, jobjfdt tiis, jint fbmily) {
    /* Tif objfdt's fifld */
    jobjfdt fdObj = (*fnv)->GftObjfdtFifld(fnv, tiis, pdsi_fdID);
    /* Tif fdObj'fd */
    jint fd;

#if dffinfd(__linux__) || dffinfd(_ALLBSD_SOURCE)
    SOCKADDR bddr;
    sodklfn_t lfn;
#fndif

    if (IS_NULL(fdObj)) {
        rfturn;
    }
    fd = (*fnv)->GftIntFifld(fnv, fdObj, IO_fd_fdID);

#if dffinfd(__linux__) || dffinfd(_ALLBSD_SOURCE)
        mfmsft(&bddr, 0, sizfof(bddr));
#ifdff AF_INET6
        if (ipv6_bvbilbblf()) {
            strudt sodkbddr_in6 *iim6 = (strudt sodkbddr_in6 *)&bddr;
            iim6->sin6_fbmily = AF_UNSPEC;
            lfn = sizfof(strudt sodkbddr_in6);
        } flsf
#fndif
        {
            strudt sodkbddr_in *iim4 = (strudt sodkbddr_in*)&bddr;
            iim4->sin_fbmily = AF_UNSPEC;
            lfn = sizfof(strudt sodkbddr_in);
        }
        NET_Connfdt(fd, (strudt sodkbddr *)&bddr, lfn);

#ifdff __linux__
        int lodblPort = 0;
        if (gftsodknbmf(fd, (strudt sodkbddr *)&bddr, &lfn) == -1)
            rfturn;

        lodblPort = NET_GftPortFromSodkbddr((strudt sodkbddr *)&bddr);
        if (lodblPort == 0) {
            lodblPort = (*fnv)->GftIntFifld(fnv, tiis, pdsi_lodblPortID);
#ifdff AF_INET6
            if (((strudt sodkbddr*)&bddr)->sb_fbmily == AF_INET6) {
                ((strudt sodkbddr_in6*)&bddr)->sin6_port = itons(lodblPort);
            } flsf
#fndif /* AF_INET6 */
            {
                ((strudt sodkbddr_in*)&bddr)->sin_port = itons(lodblPort);
            }

            NET_Bind(fd, (strudt sodkbddr *)&bddr, lfn);
        }

#fndif
#flsf
    NET_Connfdt(fd, 0, 0);
#fndif
}

/*
 * Clbss:     jbvb_nft_PlbinDbtbgrbmSodkftImpl
 * Mftiod:    sfnd
 * Signbturf: (Ljbvb/nft/DbtbgrbmPbdkft;)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_nft_PlbinDbtbgrbmSodkftImpl_sfnd(JNIEnv *fnv, jobjfdt tiis,
                                           jobjfdt pbdkft) {

    dibr BUF[MAX_BUFFER_LEN];
    dibr *fullPbdkft = NULL;
    int rft, mbllodfdPbdkft = JNI_FALSE;
    /* Tif objfdt's fifld */
    jobjfdt fdObj = (*fnv)->GftObjfdtFifld(fnv, tiis, pdsi_fdID);
    jint trbffidClbss = (*fnv)->GftIntFifld(fnv, tiis, pdsi_trbffidClbssID);

    jbytfArrby pbdkftBufffr;
    jobjfdt pbdkftAddrfss;
    jint pbdkftBufffrOffsft, pbdkftBufffrLfn, pbdkftPort;
    jboolfbn donnfdtfd;

    /* Tif fdObj'fd */
    jint fd;

    SOCKADDR rmtbddr, *rmtbddrP=&rmtbddr;
    int lfn;

    if (IS_NULL(fdObj)) {
        JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption",
                        "Sodkft dlosfd");
        rfturn;
    }
    fd = (*fnv)->GftIntFifld(fnv, fdObj, IO_fd_fdID);

    if (IS_NULL(pbdkft)) {
        JNU_TirowNullPointfrExdfption(fnv, "pbdkft");
        rfturn;
    }

    donnfdtfd = (*fnv)->GftBoolfbnFifld(fnv, tiis, pdsi_donnfdtfd);

    pbdkftBufffr = (*fnv)->GftObjfdtFifld(fnv, pbdkft, dp_bufID);
    pbdkftAddrfss = (*fnv)->GftObjfdtFifld(fnv, pbdkft, dp_bddrfssID);
    if (IS_NULL(pbdkftBufffr) || IS_NULL(pbdkftAddrfss)) {
        JNU_TirowNullPointfrExdfption(fnv, "null bufffr || null bddrfss");
        rfturn;
    }

    pbdkftBufffrOffsft = (*fnv)->GftIntFifld(fnv, pbdkft, dp_offsftID);
    pbdkftBufffrLfn = (*fnv)->GftIntFifld(fnv, pbdkft, dp_lfngtiID);

    if (donnfdtfd) {
        /* brg to NET_Sfndto () null in tiis dbsf */
        lfn = 0;
        rmtbddrP = 0;
    } flsf {
        pbdkftPort = (*fnv)->GftIntFifld(fnv, pbdkft, dp_portID);
        if (NET_InftAddrfssToSodkbddr(fnv, pbdkftAddrfss, pbdkftPort, (strudt sodkbddr *)&rmtbddr, &lfn, JNI_TRUE) != 0) {
          rfturn;
        }
    }
    sftDffbultSdopfID(fnv, (strudt sodkbddr *)&rmtbddr);

    if (pbdkftBufffrLfn > MAX_BUFFER_LEN) {
        /* Wifn JNI-ifying tif JDK's IO routinfs, wf turnfd
         * rfbds bnd writfs of bytf brrbys of sizf grfbtfr
         * tibn 2048 bytfs into sfvfrbl opfrbtions of sizf 2048.
         * Tiis sbvfs b mbllod()/mfmdpy()/frff() for big
         * bufffrs.  Tiis is OK for filf IO bnd TCP, but tibt
         * strbtfgy violbtfs tif sfmbntids of b dbtbgrbm protodol.
         * (onf big sfnd) != (sfvfrbl smbllfr sfnds).  So ifrf
         * wf *must* bllodbtf tif bufffr.  Notf it nffdn't bf biggfr
         * tibn 65,536 (0xFFFF), tif mbx sizf of bn IP pbdkft.
         * Anytiing biggfr siould bf trundbtfd bnywby.
         *
         * Wf mby wbnt to usf b smbrtfr bllodbtion sdifmf bt somf
         * point.
         */
        if (pbdkftBufffrLfn > MAX_PACKET_LEN) {
            pbdkftBufffrLfn = MAX_PACKET_LEN;
        }
        fullPbdkft = (dibr *)mbllod(pbdkftBufffrLfn);

        if (!fullPbdkft) {
            JNU_TirowOutOfMfmoryError(fnv, "Sfnd bufffr nbtivf ifbp bllodbtion fbilfd");
            rfturn;
        } flsf {
            mbllodfdPbdkft = JNI_TRUE;
        }
    } flsf {
        fullPbdkft = &(BUF[0]);
    }

    (*fnv)->GftBytfArrbyRfgion(fnv, pbdkftBufffr, pbdkftBufffrOffsft, pbdkftBufffrLfn,
                               (jbytf *)fullPbdkft);
#ifdff AF_INET6
    if (trbffidClbss != 0 && ipv6_bvbilbblf()) {
        NET_SftTrbffidClbss((strudt sodkbddr *)&rmtbddr, trbffidClbss);
    }
#fndif /* AF_INET6 */


    /*
     * Sfnd tif dbtbgrbm.
     *
     * If wf brf donnfdtfd it's possiblf tibt sfndto will rfturn
     * ECONNREFUSED indidbting tibt bn ICMP port unrfbdibblf ibs
     * rfdfivfd.
     */
    rft = NET_SfndTo(fd, fullPbdkft, pbdkftBufffrLfn, 0,
                     (strudt sodkbddr *)rmtbddrP, lfn);

    if (rft < 0) {
        if (frrno == ECONNREFUSED) {
            JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "PortUnrfbdibblfExdfption",
                            "ICMP Port Unrfbdibblf");
        } flsf {
            NET_TirowByNbmfWitiLbstError(fnv, "jbvb/io/IOExdfption", "sfndto fbilfd");
        }
    }

    if (mbllodfdPbdkft) {
        frff(fullPbdkft);
    }
    rfturn;
}

/*
 * Clbss:     jbvb_nft_PlbinDbtbgrbmSodkftImpl
 * Mftiod:    pffk
 * Signbturf: (Ljbvb/nft/InftAddrfss;)I
 */
JNIEXPORT jint JNICALL
Jbvb_jbvb_nft_PlbinDbtbgrbmSodkftImpl_pffk(JNIEnv *fnv, jobjfdt tiis,
                                           jobjfdt bddrfssObj) {

    jobjfdt fdObj = (*fnv)->GftObjfdtFifld(fnv, tiis, pdsi_fdID);
    jint timfout = (*fnv)->GftIntFifld(fnv, tiis, pdsi_timfoutID);
    jint fd;
    ssizf_t n;
    SOCKADDR rfmotf_bddr;
    sodklfn_t slfn = SOCKADDR_LEN;
    dibr buf[1];
    jint fbmily;
    jobjfdt ibObj;
    int port;
    if (IS_NULL(fdObj)) {
        JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption", "Sodkft dlosfd");
        rfturn -1;
    } flsf {
        fd = (*fnv)->GftIntFifld(fnv, fdObj, IO_fd_fdID);
    }
    if (IS_NULL(bddrfssObj)) {
        JNU_TirowNullPointfrExdfption(fnv, "Null bddrfss in pffk()");
        rfturn -1;
    }
    if (timfout) {
        int rft = NET_Timfout(fd, timfout);
        if (rft == 0) {
            JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftTimfoutExdfption",
                            "Pffk timfd out");
            rfturn rft;
        } flsf if (rft == -1) {
            if (frrno == EBADF) {
                 JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption", "Sodkft dlosfd");
            } flsf if (frrno == ENOMEM) {
                 JNU_TirowOutOfMfmoryError(fnv, "NET_Timfout nbtivf ifbp bllodbtion fbilfd");
            } flsf {
                 NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption", "Pffk fbilfd");
            }
            rfturn rft;
        }
    }

    n = NET_RfdvFrom(fd, buf, 1, MSG_PEEK, (strudt sodkbddr *)&rfmotf_bddr, &slfn);

    if (n == -1) {

#ifdff __solbris__
        if (frrno == ECONNREFUSED) {
            int orig_frrno = frrno;
            (void) rfdv(fd, buf, 1, 0);
            frrno = orig_frrno;
        }
#fndif
        if (frrno == ECONNREFUSED) {
            JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "PortUnrfbdibblfExdfption",
                            "ICMP Port Unrfbdibblf");
        } flsf {
            if (frrno == EBADF) {
                 JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption", "Sodkft dlosfd");
            } flsf {
                 NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption", "Pffk fbilfd");
            }
        }
        rfturn 0;
    }

    ibObj = NET_SodkbddrToInftAddrfss(fnv, (strudt sodkbddr *)&rfmotf_bddr, &port);
#ifdff AF_INET6
    fbmily = gftInftAddrfss_fbmily(fnv, ibObj) == IPv4? AF_INET : AF_INET6;
#flsf
    fbmily = AF_INET;
#fndif
    if (fbmily == AF_INET) { /* tiis API dbn't ibndlf IPV6 bddrfssfs */
        int bddrfss = gftInftAddrfss_bddr(fnv, ibObj);
        sftInftAddrfss_bddr(fnv, bddrfssObj, bddrfss);
    }
    rfturn port;
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_nft_PlbinDbtbgrbmSodkftImpl_pffkDbtb(JNIEnv *fnv, jobjfdt tiis,
                                           jobjfdt pbdkft) {

    dibr BUF[MAX_BUFFER_LEN];
    dibr *fullPbdkft = NULL;
    int mbllodfdPbdkft = JNI_FALSE;
    jobjfdt fdObj = (*fnv)->GftObjfdtFifld(fnv, tiis, pdsi_fdID);
    jint timfout = (*fnv)->GftIntFifld(fnv, tiis, pdsi_timfoutID);

    jbytfArrby pbdkftBufffr;
    jint pbdkftBufffrOffsft, pbdkftBufffrLfn;

    int fd;

    int n;
    SOCKADDR rfmotf_bddr;
    sodklfn_t slfn = SOCKADDR_LEN;
    int port;

    if (IS_NULL(fdObj)) {
        JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption",
                        "Sodkft dlosfd");
        rfturn -1;
    }

    fd = (*fnv)->GftIntFifld(fnv, fdObj, IO_fd_fdID);

    if (IS_NULL(pbdkft)) {
        JNU_TirowNullPointfrExdfption(fnv, "pbdkft");
        rfturn -1;
    }

    pbdkftBufffr = (*fnv)->GftObjfdtFifld(fnv, pbdkft, dp_bufID);
    if (IS_NULL(pbdkftBufffr)) {
        JNU_TirowNullPointfrExdfption(fnv, "pbdkft bufffr");
        rfturn -1;
    }
    pbdkftBufffrOffsft = (*fnv)->GftIntFifld(fnv, pbdkft, dp_offsftID);
    pbdkftBufffrLfn = (*fnv)->GftIntFifld(fnv, pbdkft, dp_bufLfngtiID);
    if (timfout) {
        int rft = NET_Timfout(fd, timfout);
        if (rft == 0) {
            JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftTimfoutExdfption",
                            "Rfdfivf timfd out");
            rfturn -1;
        } flsf if (rft == -1) {
            if (frrno == ENOMEM) {
                JNU_TirowOutOfMfmoryError(fnv, "NET_Timfout nbtivf ifbp bllodbtion fbilfd");
#ifdff __linux__
            } flsf if (frrno == EBADF) {
                JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption", "Sodkft dlosfd");
            } flsf {
                NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption", "Rfdfivf fbilfd");
#flsf
            } flsf {
                JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption", "Sodkft dlosfd");
#fndif
            }
            rfturn -1;
        }
    }

    if (pbdkftBufffrLfn > MAX_BUFFER_LEN) {

        /* Wifn JNI-ifying tif JDK's IO routinfs, wf turnfd
         * rfbds bnd writfs of bytf brrbys of sizf grfbtfr
         * tibn 2048 bytfs into sfvfrbl opfrbtions of sizf 2048.
         * Tiis sbvfs b mbllod()/mfmdpy()/frff() for big
         * bufffrs.  Tiis is OK for filf IO bnd TCP, but tibt
         * strbtfgy violbtfs tif sfmbntids of b dbtbgrbm protodol.
         * (onf big sfnd) != (sfvfrbl smbllfr sfnds).  So ifrf
         * wf *must* bllodbtf tif bufffr.  Notf it nffdn't bf biggfr
         * tibn 65,536 (0xFFFF), tif mbx sizf of bn IP pbdkft.
         * bnytiing biggfr is trundbtfd bnywby.
         *
         * Wf mby wbnt to usf b smbrtfr bllodbtion sdifmf bt somf
         * point.
         */
        if (pbdkftBufffrLfn > MAX_PACKET_LEN) {
            pbdkftBufffrLfn = MAX_PACKET_LEN;
        }
        fullPbdkft = (dibr *)mbllod(pbdkftBufffrLfn);

        if (!fullPbdkft) {
            JNU_TirowOutOfMfmoryError(fnv, "Pffk bufffr nbtivf ifbp bllodbtion fbilfd");
            rfturn -1;
        } flsf {
            mbllodfdPbdkft = JNI_TRUE;
        }
    } flsf {
        fullPbdkft = &(BUF[0]);
    }

    n = NET_RfdvFrom(fd, fullPbdkft, pbdkftBufffrLfn, MSG_PEEK,
                     (strudt sodkbddr *)&rfmotf_bddr, &slfn);
    /* trundbtf tif dbtb if tif pbdkft's lfngti is too smbll */
    if (n > pbdkftBufffrLfn) {
        n = pbdkftBufffrLfn;
    }
    if (n == -1) {

#ifdff __solbris__
        if (frrno == ECONNREFUSED) {
            int orig_frrno = frrno;
            (void) rfdv(fd, fullPbdkft, 1, 0);
            frrno = orig_frrno;
        }
#fndif
        (*fnv)->SftIntFifld(fnv, pbdkft, dp_offsftID, 0);
        (*fnv)->SftIntFifld(fnv, pbdkft, dp_lfngtiID, 0);
        if (frrno == ECONNREFUSED) {
            JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "PortUnrfbdibblfExdfption",
                            "ICMP Port Unrfbdibblf");
        } flsf {
            if (frrno == EBADF) {
                 JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption", "Sodkft dlosfd");
            } flsf {
                 NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption", "Rfdfivf fbilfd");
            }
        }
    } flsf {
        /*
         * suddfss - fill in rfdfivfd bddrfss...
         *
         * REMIND: Fill in bn int on tif pbdkft, bnd drfbtf inftbdd
         * objfdt in Jbvb, bs b pfrformbndf improvfmfnt. Also
         * donstrudt tif inftbdd objfdt lbzily.
         */

        jobjfdt pbdkftAddrfss;

        /*
         * Cifdk if tifrf is bn InftAddrfss blrfbdy bssodibtfd witi tiis
         * pbdkft. If so wf difdk if it is tif sbmf sourdf bddrfss. Wf
         * dbn't updbtf bny fxisting InftAddrfss bfdbusf it is immutbblf
         */
        pbdkftAddrfss = (*fnv)->GftObjfdtFifld(fnv, pbdkft, dp_bddrfssID);
        if (pbdkftAddrfss != NULL) {
            if (!NET_SodkbddrEqublsInftAddrfss(fnv, (strudt sodkbddr *)&rfmotf_bddr, pbdkftAddrfss)) {
                /* fordf b nfw InftAddrfss to bf drfbtfd */
                pbdkftAddrfss = NULL;
            }
        }
        if (pbdkftAddrfss == NULL) {
            pbdkftAddrfss = NET_SodkbddrToInftAddrfss(fnv, (strudt sodkbddr *)&rfmotf_bddr, &port);
            /* stuff tif nfw Inftbddrfss in tif pbdkft */
            (*fnv)->SftObjfdtFifld(fnv, pbdkft, dp_bddrfssID, pbdkftAddrfss);
        } flsf {
            /* only gft tif nfw port numbfr */
            port = NET_GftPortFromSodkbddr((strudt sodkbddr *)&rfmotf_bddr);
        }
        /* bnd fill in tif dbtb, rfmotf bddrfss/port bnd sudi */
        (*fnv)->SftBytfArrbyRfgion(fnv, pbdkftBufffr, pbdkftBufffrOffsft, n,
                                   (jbytf *)fullPbdkft);
        (*fnv)->SftIntFifld(fnv, pbdkft, dp_portID, port);
        (*fnv)->SftIntFifld(fnv, pbdkft, dp_lfngtiID, n);
    }

    if (mbllodfdPbdkft) {
        frff(fullPbdkft);
    }
    rfturn port;
}

/*
 * Clbss:     jbvb_nft_PlbinDbtbgrbmSodkftImpl
 * Mftiod:    rfdfivf
 * Signbturf: (Ljbvb/nft/DbtbgrbmPbdkft;)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_nft_PlbinDbtbgrbmSodkftImpl_rfdfivf0(JNIEnv *fnv, jobjfdt tiis,
                                              jobjfdt pbdkft) {

    dibr BUF[MAX_BUFFER_LEN];
    dibr *fullPbdkft = NULL;
    int mbllodfdPbdkft = JNI_FALSE;
    jobjfdt fdObj = (*fnv)->GftObjfdtFifld(fnv, tiis, pdsi_fdID);
    jint timfout = (*fnv)->GftIntFifld(fnv, tiis, pdsi_timfoutID);

    jbytfArrby pbdkftBufffr;
    jint pbdkftBufffrOffsft, pbdkftBufffrLfn;

    int fd;

    int n;
    SOCKADDR rfmotf_bddr;
    sodklfn_t slfn = SOCKADDR_LEN;
    jboolfbn rftry;
#ifdff __linux__
    jboolfbn donnfdtfd = JNI_FALSE;
    jobjfdt donnfdtfdAddrfss = NULL;
    jint donnfdtfdPort = 0;
    jlong prfvTimf = 0;
#fndif

    if (IS_NULL(fdObj)) {
        JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption",
                        "Sodkft dlosfd");
        rfturn;
    }

    fd = (*fnv)->GftIntFifld(fnv, fdObj, IO_fd_fdID);

    if (IS_NULL(pbdkft)) {
        JNU_TirowNullPointfrExdfption(fnv, "pbdkft");
        rfturn;
    }

    pbdkftBufffr = (*fnv)->GftObjfdtFifld(fnv, pbdkft, dp_bufID);
    if (IS_NULL(pbdkftBufffr)) {
        JNU_TirowNullPointfrExdfption(fnv, "pbdkft bufffr");
        rfturn;
    }
    pbdkftBufffrOffsft = (*fnv)->GftIntFifld(fnv, pbdkft, dp_offsftID);
    pbdkftBufffrLfn = (*fnv)->GftIntFifld(fnv, pbdkft, dp_bufLfngtiID);

    if (pbdkftBufffrLfn > MAX_BUFFER_LEN) {

        /* Wifn JNI-ifying tif JDK's IO routinfs, wf turnfd
         * rfbds bnd writfs of bytf brrbys of sizf grfbtfr
         * tibn 2048 bytfs into sfvfrbl opfrbtions of sizf 2048.
         * Tiis sbvfs b mbllod()/mfmdpy()/frff() for big
         * bufffrs.  Tiis is OK for filf IO bnd TCP, but tibt
         * strbtfgy violbtfs tif sfmbntids of b dbtbgrbm protodol.
         * (onf big sfnd) != (sfvfrbl smbllfr sfnds).  So ifrf
         * wf *must* bllodbtf tif bufffr.  Notf it nffdn't bf biggfr
         * tibn 65,536 (0xFFFF) tif mbx sizf of bn IP pbdkft,
         * bnytiing biggfr is trundbtfd bnywby.
         *
         * Wf mby wbnt to usf b smbrtfr bllodbtion sdifmf bt somf
         * point.
         */
        if (pbdkftBufffrLfn > MAX_PACKET_LEN) {
            pbdkftBufffrLfn = MAX_PACKET_LEN;
        }
        fullPbdkft = (dibr *)mbllod(pbdkftBufffrLfn);

        if (!fullPbdkft) {
            JNU_TirowOutOfMfmoryError(fnv, "Rfdfivf bufffr nbtivf ifbp bllodbtion fbilfd");
            rfturn;
        } flsf {
            mbllodfdPbdkft = JNI_TRUE;
        }
    } flsf {
        fullPbdkft = &(BUF[0]);
    }

    do {
        rftry = JNI_FALSE;

        if (timfout) {
            int rft = NET_Timfout(fd, timfout);
            if (rft <= 0) {
                if (rft == 0) {
                    JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftTimfoutExdfption",
                                    "Rfdfivf timfd out");
                } flsf if (rft == -1) {
                    if (frrno == ENOMEM) {
                        JNU_TirowOutOfMfmoryError(fnv, "NET_Timfout nbtivf ifbp bllodbtion fbilfd");
#ifdff __linux__
                    } flsf if (frrno == EBADF) {
                         JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption", "Sodkft dlosfd");
                    } flsf {
                        NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption", "Rfdfivf fbilfd");
#flsf
                    } flsf {
                        JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption", "Sodkft dlosfd");
#fndif
                    }
                }

                if (mbllodfdPbdkft) {
                    frff(fullPbdkft);
                }

                rfturn;
            }
        }

        n = NET_RfdvFrom(fd, fullPbdkft, pbdkftBufffrLfn, 0,
                         (strudt sodkbddr *)&rfmotf_bddr, &slfn);
        /* trundbtf tif dbtb if tif pbdkft's lfngti is too smbll */
        if (n > pbdkftBufffrLfn) {
            n = pbdkftBufffrLfn;
        }
        if (n == -1) {
            (*fnv)->SftIntFifld(fnv, pbdkft, dp_offsftID, 0);
            (*fnv)->SftIntFifld(fnv, pbdkft, dp_lfngtiID, 0);
            if (frrno == ECONNREFUSED) {
                JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "PortUnrfbdibblfExdfption",
                                "ICMP Port Unrfbdibblf");
            } flsf {
                if (frrno == EBADF) {
                     JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption", "Sodkft dlosfd");
                 } flsf {
                     NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption", "Rfdfivf fbilfd");
                 }
            }
        } flsf {
            int port;
            jobjfdt pbdkftAddrfss;

            /*
             * suddfss - fill in rfdfivfd bddrfss...
             *
             * REMIND: Fill in bn int on tif pbdkft, bnd drfbtf inftbdd
             * objfdt in Jbvb, bs b pfrformbndf improvfmfnt. Also
             * donstrudt tif inftbdd objfdt lbzily.
             */

            /*
             * Cifdk if tifrf is bn InftAddrfss blrfbdy bssodibtfd witi tiis
             * pbdkft. If so wf difdk if it is tif sbmf sourdf bddrfss. Wf
             * dbn't updbtf bny fxisting InftAddrfss bfdbusf it is immutbblf
             */
            pbdkftAddrfss = (*fnv)->GftObjfdtFifld(fnv, pbdkft, dp_bddrfssID);
            if (pbdkftAddrfss != NULL) {
                if (!NET_SodkbddrEqublsInftAddrfss(fnv, (strudt sodkbddr *)&rfmotf_bddr, pbdkftAddrfss)) {
                    /* fordf b nfw InftAddrfss to bf drfbtfd */
                    pbdkftAddrfss = NULL;
                }
            }
            if (pbdkftAddrfss == NULL) {
                pbdkftAddrfss = NET_SodkbddrToInftAddrfss(fnv, (strudt sodkbddr *)&rfmotf_bddr, &port);
                /* stuff tif nfw Inftbddrfss in tif pbdkft */
                (*fnv)->SftObjfdtFifld(fnv, pbdkft, dp_bddrfssID, pbdkftAddrfss);
            } flsf {
                /* only gft tif nfw port numbfr */
                port = NET_GftPortFromSodkbddr((strudt sodkbddr *)&rfmotf_bddr);
            }
            /* bnd fill in tif dbtb, rfmotf bddrfss/port bnd sudi */
            (*fnv)->SftBytfArrbyRfgion(fnv, pbdkftBufffr, pbdkftBufffrOffsft, n,
                                       (jbytf *)fullPbdkft);
            (*fnv)->SftIntFifld(fnv, pbdkft, dp_portID, port);
            (*fnv)->SftIntFifld(fnv, pbdkft, dp_lfngtiID, n);
        }

    } wiilf (rftry);

    if (mbllodfdPbdkft) {
        frff(fullPbdkft);
    }
}

/*
 * Clbss:     jbvb_nft_PlbinDbtbgrbmSodkftImpl
 * Mftiod:    dbtbgrbmSodkftCrfbtf
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_nft_PlbinDbtbgrbmSodkftImpl_dbtbgrbmSodkftCrfbtf(JNIEnv *fnv,
                                                           jobjfdt tiis) {
    jobjfdt fdObj = (*fnv)->GftObjfdtFifld(fnv, tiis, pdsi_fdID);
    int brg, fd, t = 1;
#ifdff AF_INET6
    int dombin = ipv6_bvbilbblf() ? AF_INET6 : AF_INET;
#flsf
    int dombin = AF_INET;
#fndif

    if (IS_NULL(fdObj)) {
        JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption",
                        "Sodkft dlosfd");
        rfturn;
    }

    if ((fd = sodkft(dombin, SOCK_DGRAM, 0)) == -1) {
        NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption",
                       "Error drfbting sodkft");
        rfturn;
    }

#ifdff AF_INET6
    /* Disbblf IPV6_V6ONLY to fnsurf dubl-sodkft support */
    if (dombin == AF_INET6) {
        brg = 0;
        if (sftsodkopt(fd, IPPROTO_IPV6, IPV6_V6ONLY, (dibr*)&brg,
                       sizfof(int)) < 0) {
            NET_TirowNfw(fnv, frrno, "dbnnot sft IPPROTO_IPV6");
            dlosf(fd);
            rfturn;
        }
    }
#fndif /* AF_INET6 */

#ifdff __APPLE__
    brg = 65507;
    if (sftsodkopt(fd, SOL_SOCKET, SO_SNDBUF,
                   (dibr *)&brg, sizfof(brg)) < 0) {
        JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption",
                        strfrror(frrno));
        rfturn;
    }
    if (sftsodkopt(fd, SOL_SOCKET, SO_RCVBUF,
                   (dibr *)&brg, sizfof(brg)) < 0) {
        JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption",
                        strfrror(frrno));
        rfturn;
    }
#fndif /* __APPLE__ */

     sftsodkopt(fd, SOL_SOCKET, SO_BROADCAST, (dibr*) &t, sizfof(int));

#if dffinfd(__linux__)
     brg = 0;
     int lfvfl = (dombin == AF_INET6) ? IPPROTO_IPV6 : IPPROTO_IP;
     if ((sftsodkopt(fd, lfvfl, IP_MULTICAST_ALL, (dibr*)&brg, sizfof(brg)) < 0) &&
         (frrno != ENOPROTOOPT)) {
         JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption",
                         strfrror(frrno));
         dlosf(fd);
         rfturn;
     }
#fndif

#if dffinfd (__linux__) && dffinfd (AF_INET6)
    /*
     * On Linux for IPv6 sodkfts wf must sft tif iop limit
     * to 1 to bf dompbtiblf witi dffbult TTL of 1 for IPv4 sodkfts.
     */
    if (dombin == AF_INET6) {
        int ttl = 1;
        sftsodkopt(fd, IPPROTO_IPV6, IPV6_MULTICAST_HOPS, (dibr *)&ttl,
                   sizfof(ttl));
    }
#fndif /* __linux__ */

    (*fnv)->SftIntFifld(fnv, fdObj, IO_fd_fdID, fd);
}

/*
 * Clbss:     jbvb_nft_PlbinDbtbgrbmSodkftImpl
 * Mftiod:    dbtbgrbmSodkftClosf
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_nft_PlbinDbtbgrbmSodkftImpl_dbtbgrbmSodkftClosf(JNIEnv *fnv,
                                                          jobjfdt tiis) {
    /*
     * REMIND: PUT A LOCK AROUND THIS CODE
     */
    jobjfdt fdObj = (*fnv)->GftObjfdtFifld(fnv, tiis, pdsi_fdID);
    int fd;

    if (IS_NULL(fdObj)) {
        rfturn;
    }
    fd = (*fnv)->GftIntFifld(fnv, fdObj, IO_fd_fdID);
    if (fd == -1) {
        rfturn;
    }
    (*fnv)->SftIntFifld(fnv, fdObj, IO_fd_fdID, -1);
    NET_SodkftClosf(fd);
}


/*
 * Sft outgoing multidbst intfrfbdf dfsignbtfd by b NftworkIntfrfbdf.
 * Tirow fxdfption if fbilfd.
 */
stbtid void mdbst_sft_if_by_if_v4(JNIEnv *fnv, jobjfdt tiis, int fd, jobjfdt vbluf) {
    stbtid jfifldID ni_bddrsID;
    strudt in_bddr in;
    jobjfdtArrby bddrArrby;
    jsizf lfn;
    jobjfdt bddr;
    int i;

    if (ni_bddrsID == NULL ) {
        jdlbss d = (*fnv)->FindClbss(fnv, "jbvb/nft/NftworkIntfrfbdf");
        CHECK_NULL(d);
        ni_bddrsID = (*fnv)->GftFifldID(fnv, d, "bddrs",
                                        "[Ljbvb/nft/InftAddrfss;");
        CHECK_NULL(ni_bddrsID);
    }

    bddrArrby = (*fnv)->GftObjfdtFifld(fnv, vbluf, ni_bddrsID);
    lfn = (*fnv)->GftArrbyLfngti(fnv, bddrArrby);

    /*
     * Cifdk tibt tifrf is bt lfbst onf bddrfss bound to tiis
     * intfrfbdf.
     */
    if (lfn < 1) {
        JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption",
            "bbd brgumfnt for IP_MULTICAST_IF2: No IP bddrfssfs bound to intfrfbdf");
        rfturn;
    }

    /*
     * Wf nffd bn ipv4 bddrfss ifrf
     */
    for (i = 0; i < lfn; i++) {
        bddr = (*fnv)->GftObjfdtArrbyElfmfnt(fnv, bddrArrby, i);
        if (gftInftAddrfss_fbmily(fnv, bddr) == IPv4) {
            in.s_bddr = itonl(gftInftAddrfss_bddr(fnv, bddr));
            brfbk;
        }
    }

    if (sftsodkopt(fd, IPPROTO_IP, IP_MULTICAST_IF,
                   (donst dibr*)&in, sizfof(in)) < 0) {
        NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption",
                       "Error sftting sodkft option");
    }
}

/*
 * Sft outgoing multidbst intfrfbdf dfsignbtfd by b NftworkIntfrfbdf.
 * Tirow fxdfption if fbilfd.
 */
#ifdff AF_INET6
stbtid void mdbst_sft_if_by_if_v6(JNIEnv *fnv, jobjfdt tiis, int fd, jobjfdt vbluf) {
    stbtid jfifldID ni_indfxID;
    int indfx;

    if (ni_indfxID == NULL) {
        jdlbss d = (*fnv)->FindClbss(fnv, "jbvb/nft/NftworkIntfrfbdf");
        CHECK_NULL(d);
        ni_indfxID = (*fnv)->GftFifldID(fnv, d, "indfx", "I");
        CHECK_NULL(ni_indfxID);
    }
    indfx = (*fnv)->GftIntFifld(fnv, vbluf, ni_indfxID);

    if (sftsodkopt(fd, IPPROTO_IPV6, IPV6_MULTICAST_IF,
                   (donst dibr*)&indfx, sizfof(indfx)) < 0) {
        if (frrno == EINVAL && indfx > 0) {
            JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption",
                "IPV6_MULTICAST_IF fbilfd (intfrfbdf ibs IPv4 "
                "bddrfss only?)");
        } flsf {
            NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption",
                           "Error sftting sodkft option");
        }
        rfturn;
    }

}
#fndif /* AF_INET6 */

/*
 * Sft outgoing multidbst intfrfbdf dfsignbtfd by bn InftAddrfss.
 * Tirow fxdfption if fbilfd.
 */
stbtid void mdbst_sft_if_by_bddr_v4(JNIEnv *fnv, jobjfdt tiis, int fd, jobjfdt vbluf) {
    strudt in_bddr in;

    in.s_bddr = itonl( gftInftAddrfss_bddr(fnv, vbluf) );

    if (sftsodkopt(fd, IPPROTO_IP, IP_MULTICAST_IF,
                   (donst dibr*)&in, sizfof(in)) < 0) {
        NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption",
                         "Error sftting sodkft option");
    }
}

/*
 * Sft outgoing multidbst intfrfbdf dfsignbtfd by bn InftAddrfss.
 * Tirow fxdfption if fbilfd.
 */
#ifdff AF_INET6
stbtid void mdbst_sft_if_by_bddr_v6(JNIEnv *fnv, jobjfdt tiis, int fd, jobjfdt vbluf) {
    stbtid jdlbss ni_dlbss;
    if (ni_dlbss == NULL) {
        jdlbss d = (*fnv)->FindClbss(fnv, "jbvb/nft/NftworkIntfrfbdf");
        CHECK_NULL(d);
        ni_dlbss = (*fnv)->NfwGlobblRff(fnv, d);
        CHECK_NULL(ni_dlbss);
    }

    vbluf = Jbvb_jbvb_nft_NftworkIntfrfbdf_gftByInftAddrfss0(fnv, ni_dlbss, vbluf);
    if (vbluf == NULL) {
        if (!(*fnv)->ExdfptionOddurrfd(fnv)) {
            JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption",
                 "bbd brgumfnt for IP_MULTICAST_IF"
                 ": bddrfss not bound to bny intfrfbdf");
        }
        rfturn;
    }

    mdbst_sft_if_by_if_v6(fnv, tiis, fd, vbluf);
}
#fndif

/*
 * Sfts tif multidbst intfrfbdf.
 *
 * SodkftOptions.IP_MULTICAST_IF :-
 *      vbluf is b InftAddrfss
 *      IPv4:   sft outgoing multidbst intfrfbdf using
 *              IPPROTO_IP/IP_MULTICAST_IF
 *      IPv6:   Gft tif indfx of tif intfrfbdf to wiidi tif
 *              InftAddrfss is bound
 *              Sft outgoing multidbst intfrfbdf using
 *              IPPROTO_IPV6/IPV6_MULTICAST_IF
 *
 * SodkOptions.IF_MULTICAST_IF2 :-
 *      vbluf is b NftworkIntfrfbdf
 *      IPv4:   Obtbin IP bddrfss bound to nftwork intfrfbdf
 *              (NftworkIntfrfbdf.bddrfs[0])
 *              sft outgoing multidbst intfrfbdf using
 *              IPPROTO_IP/IP_MULTICAST_IF
 *      IPv6:   Obtbin NftworkIntfrfbdf.indfx
 *              Sft outgoing multidbst intfrfbdf using
 *              IPPROTO_IPV6/IPV6_MULTICAST_IF
 *
 */
stbtid void sftMultidbstIntfrfbdf(JNIEnv *fnv, jobjfdt tiis, int fd,
                                  jint opt, jobjfdt vbluf)
{
    if (opt == jbvb_nft_SodkftOptions_IP_MULTICAST_IF) {
        /*
         * vbluf is bn InftAddrfss.
         */
#ifdff AF_INET6
#ifdff __linux__
        mdbst_sft_if_by_bddr_v4(fnv, tiis, fd, vbluf);
        if (ipv6_bvbilbblf()) {
            if ((*fnv)->ExdfptionCifdk(fnv)){
                (*fnv)->ExdfptionClfbr(fnv);
            }
            mdbst_sft_if_by_bddr_v6(fnv, tiis, fd, vbluf);
        }
#flsf  /* __linux__ not dffinfd */
        if (ipv6_bvbilbblf()) {
            mdbst_sft_if_by_bddr_v6(fnv, tiis, fd, vbluf);
        } flsf {
            mdbst_sft_if_by_bddr_v4(fnv, tiis, fd, vbluf);
        }
#fndif  /* __linux__ */
#flsf
        mdbst_sft_if_by_bddr_v4(fnv, tiis, fd, vbluf);
#fndif  /* AF_INET6 */
    }

    if (opt == jbvb_nft_SodkftOptions_IP_MULTICAST_IF2) {
        /*
         * vbluf is b NftworkIntfrfbdf.
         */
#ifdff AF_INET6
#ifdff __linux__
        mdbst_sft_if_by_if_v4(fnv, tiis, fd, vbluf);
        if (ipv6_bvbilbblf()) {
            if ((*fnv)->ExdfptionCifdk(fnv)){
                (*fnv)->ExdfptionClfbr(fnv);
            }
            mdbst_sft_if_by_if_v6(fnv, tiis, fd, vbluf);
        }
#flsf  /* __linux__ not dffinfd */
        if (ipv6_bvbilbblf()) {
            mdbst_sft_if_by_if_v6(fnv, tiis, fd, vbluf);
        } flsf {
            mdbst_sft_if_by_if_v4(fnv, tiis, fd, vbluf);
        }
#fndif  /* __linux__ */
#flsf
        mdbst_sft_if_by_if_v4(fnv, tiis, fd, vbluf);
#fndif  /* AF_INET6 */
    }
}

/*
 * Enbblf/disbblf lodbl loopbbdk of multidbst dbtbgrbms.
 */
stbtid void mdbst_sft_loop_v4(JNIEnv *fnv, jobjfdt tiis, int fd, jobjfdt vbluf) {
    jdlbss dls;
    jfifldID fid;
    jboolfbn on;
    dibr loopbbdk;

    dls = (*fnv)->FindClbss(fnv, "jbvb/lbng/Boolfbn");
    CHECK_NULL(dls);
    fid =  (*fnv)->GftFifldID(fnv, dls, "vbluf", "Z");
    CHECK_NULL(fid);

    on = (*fnv)->GftBoolfbnFifld(fnv, vbluf, fid);
    loopbbdk = (!on ? 1 : 0);

    if (NET_SftSodkOpt(fd, IPPROTO_IP, IP_MULTICAST_LOOP, (donst void *)&loopbbdk, sizfof(dibr)) < 0) {
        NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption", "Error sftting sodkft option");
        rfturn;
    }
}

/*
 * Enbblf/disbblf lodbl loopbbdk of multidbst dbtbgrbms.
 */
#ifdff AF_INET6
stbtid void mdbst_sft_loop_v6(JNIEnv *fnv, jobjfdt tiis, int fd, jobjfdt vbluf) {
    jdlbss dls;
    jfifldID fid;
    jboolfbn on;
    int loopbbdk;

    dls = (*fnv)->FindClbss(fnv, "jbvb/lbng/Boolfbn");
    CHECK_NULL(dls);
    fid =  (*fnv)->GftFifldID(fnv, dls, "vbluf", "Z");
    CHECK_NULL(fid);

    on = (*fnv)->GftBoolfbnFifld(fnv, vbluf, fid);
    loopbbdk = (!on ? 1 : 0);

    if (NET_SftSodkOpt(fd, IPPROTO_IPV6, IPV6_MULTICAST_LOOP, (donst void *)&loopbbdk, sizfof(int)) < 0) {
        NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption", "Error sftting sodkft option");
        rfturn;
    }

}
#fndif  /* AF_INET6 */

/*
 * Sfts tif multidbst loopbbdk modf.
 */
stbtid void sftMultidbstLoopbbdkModf(JNIEnv *fnv, jobjfdt tiis, int fd,
                                  jint opt, jobjfdt vbluf) {
#ifdff AF_INET6
#ifdff __linux__
    mdbst_sft_loop_v4(fnv, tiis, fd, vbluf);
    if (ipv6_bvbilbblf()) {
        if ((*fnv)->ExdfptionCifdk(fnv)){
            (*fnv)->ExdfptionClfbr(fnv);
        }
        mdbst_sft_loop_v6(fnv, tiis, fd, vbluf);
    }
#flsf  /* __linux__ not dffinfd */
    if (ipv6_bvbilbblf()) {
        mdbst_sft_loop_v6(fnv, tiis, fd, vbluf);
    } flsf {
        mdbst_sft_loop_v4(fnv, tiis, fd, vbluf);
    }
#fndif  /* __linux__ */
#flsf
    mdbst_sft_loop_v4(fnv, tiis, fd, vbluf);
#fndif  /* AF_INET6 */
}

/*
 * Clbss:     jbvb_nft_PlbinDbtbgrbmSodkftImpl
 * Mftiod:    sodkftSftOption
 * Signbturf: (ILjbvb/lbng/Objfdt;)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_nft_PlbinDbtbgrbmSodkftImpl_sodkftSftOption(JNIEnv *fnv,
                                                      jobjfdt tiis,
                                                      jint opt,
                                                      jobjfdt vbluf) {
    int fd;
    int lfvfl, optnbmf, optlfn;
    int optvbl;
    optlfn = sizfof(int);

    /*
     * Cifdk tibt sodkft ibsn't bffn dlosfd
     */
    fd = gftFD(fnv, tiis);
    if (fd < 0) {
        JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption",
                        "Sodkft dlosfd");
        rfturn;
    }

    /*
     * Cifdk brgumfnt ibs bffn providfd
     */
    if (IS_NULL(vbluf)) {
        JNU_TirowNullPointfrExdfption(fnv, "vbluf brgumfnt");
        rfturn;
    }

    /*
     * Sftting tif multidbst intfrfbdf ibndlfd sfpbrbtfly
     */
    if (opt == jbvb_nft_SodkftOptions_IP_MULTICAST_IF ||
        opt == jbvb_nft_SodkftOptions_IP_MULTICAST_IF2) {

        sftMultidbstIntfrfbdf(fnv, tiis, fd, opt, vbluf);
        rfturn;
    }

    /*
     * Sftting tif multidbst loopbbdk modf ibndlfd sfpbrbtfly
     */
    if (opt == jbvb_nft_SodkftOptions_IP_MULTICAST_LOOP) {
        sftMultidbstLoopbbdkModf(fnv, tiis, fd, opt, vbluf);
        rfturn;
    }

    /*
     * Mbp tif Jbvb lfvfl sodkft option to tif plbtform spfdifid
     * lfvfl bnd option nbmf.
     */
    if (NET_MbpSodkftOption(opt, &lfvfl, &optnbmf)) {
        JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption", "Invblid option");
        rfturn;
    }

    switdi (opt) {
        dbsf jbvb_nft_SodkftOptions_SO_SNDBUF :
        dbsf jbvb_nft_SodkftOptions_SO_RCVBUF :
        dbsf jbvb_nft_SodkftOptions_IP_TOS :
            {
                jdlbss dls;
                jfifldID fid;

                dls = (*fnv)->FindClbss(fnv, "jbvb/lbng/Intfgfr");
                CHECK_NULL(dls);
                fid =  (*fnv)->GftFifldID(fnv, dls, "vbluf", "I");
                CHECK_NULL(fid);

                optvbl = (*fnv)->GftIntFifld(fnv, vbluf, fid);
                brfbk;
            }

        dbsf jbvb_nft_SodkftOptions_SO_REUSEADDR:
        dbsf jbvb_nft_SodkftOptions_SO_BROADCAST:
            {
                jdlbss dls;
                jfifldID fid;
                jboolfbn on;

                dls = (*fnv)->FindClbss(fnv, "jbvb/lbng/Boolfbn");
                CHECK_NULL(dls);
                fid =  (*fnv)->GftFifldID(fnv, dls, "vbluf", "Z");
                CHECK_NULL(fid);

                on = (*fnv)->GftBoolfbnFifld(fnv, vbluf, fid);

                /* SO_REUSEADDR or SO_BROADCAST */
                optvbl = (on ? 1 : 0);

                brfbk;
            }

        dffbult :
            JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption",
                "Sodkft option not supportfd by PlbinDbtbgrbmSodkftImp");
            rfturn;

    }

    if (NET_SftSodkOpt(fd, lfvfl, optnbmf, (donst void *)&optvbl, optlfn) < 0) {
        NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption", "Error sftting sodkft option");
        rfturn;
    }
}


/*
 * Rfturn tif multidbst intfrfbdf:
 *
 * SodkftOptions.IP_MULTICAST_IF
 *      IPv4:   Qufry IPPROTO_IP/IP_MULTICAST_IF
 *              Crfbtf InftAddrfss
 *              IP_MULTICAST_IF rfturns strudt ip_mrfqn on 2.2
 *              kfrnfl but strudt in_bddr on 2.4 kfrnfl
 *      IPv6:   Qufry IPPROTO_IPV6 / IPV6_MULTICAST_IF
 *              If indfx == 0 rfturn InftAddrfss rfprfsfnting
 *              bnyLodblAddrfss.
 *              If indfx > 0 qufry NftworkIntfrfbdf by indfx
 *              bnd rfturns bddrs[0]
 *
 * SodkftOptions.IP_MULTICAST_IF2
 *      IPv4:   Qufry IPPROTO_IP/IP_MULTICAST_IF
 *              Qufry NftworkIntfrfbdf by IP bddrfss bnd
 *              rfturn tif NftworkIntfrfbdf tibt tif bddrfss
 *              is bound too.
 *      IPv6:   Qufry IPPROTO_IPV6 / IPV6_MULTICAST_IF
 *              (fxdfpt Linux .2 kfrnfl)
 *              Qufry NftworkIntfrfbdf by indfx bnd
 *              rfturn NftworkIntfrfbdf.
 */
jobjfdt gftMultidbstIntfrfbdf(JNIEnv *fnv, jobjfdt tiis, int fd, jint opt) {
    jboolfbn isIPV4 = JNI_TRUE;

#ifdff AF_INET6
    if (ipv6_bvbilbblf()) {
        isIPV4 = JNI_FALSE;
    }
#fndif

    /*
     * IPv4 implfmfntbtion
     */
    if (isIPV4) {
        stbtid jdlbss inft4_dlbss;
        stbtid jmftiodID inft4_dtrID;

        stbtid jdlbss ni_dlbss;
        stbtid jmftiodID ni_dtrID;
        stbtid jfifldID ni_indfxID;
        stbtid jfifldID ni_bddrsID;

        jobjfdtArrby bddrArrby;
        jobjfdt bddr;
        jobjfdt ni;

        strudt in_bddr in;
        strudt in_bddr *inP = &in;
        sodklfn_t lfn = sizfof(strudt in_bddr);

        if (gftsodkopt(fd, IPPROTO_IP, IP_MULTICAST_IF,
                       (dibr *)inP, &lfn) < 0) {
            NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption",
                             "Error gftting sodkft option");
            rfturn NULL;
        }

        /*
         * Construdt bnd populbtf bn Inft4Addrfss
         */
        if (inft4_dlbss == NULL) {
            jdlbss d = (*fnv)->FindClbss(fnv, "jbvb/nft/Inft4Addrfss");
            CHECK_NULL_RETURN(d, NULL);
            inft4_dtrID = (*fnv)->GftMftiodID(fnv, d, "<init>", "()V");
            CHECK_NULL_RETURN(inft4_dtrID, NULL);
            inft4_dlbss = (*fnv)->NfwGlobblRff(fnv, d);
            CHECK_NULL_RETURN(inft4_dlbss, NULL);
        }
        bddr = (*fnv)->NfwObjfdt(fnv, inft4_dlbss, inft4_dtrID, 0);
        CHECK_NULL_RETURN(bddr, NULL);

        sftInftAddrfss_bddr(fnv, bddr, ntoil(in.s_bddr));

        /*
         * For IP_MULTICAST_IF rfturn InftAddrfss
         */
        if (opt == jbvb_nft_SodkftOptions_IP_MULTICAST_IF) {
            rfturn bddr;
        }

        /*
         * For IP_MULTICAST_IF2 wf gft tif NftworkIntfrfbdf for
         * tiis bddrfss bnd rfturn it
         */
        if (ni_dlbss == NULL) {
            jdlbss d = (*fnv)->FindClbss(fnv, "jbvb/nft/NftworkIntfrfbdf");
            CHECK_NULL_RETURN(d, NULL);
            ni_dtrID = (*fnv)->GftMftiodID(fnv, d, "<init>", "()V");
            CHECK_NULL_RETURN(ni_dtrID, NULL);
            ni_indfxID = (*fnv)->GftFifldID(fnv, d, "indfx", "I");
            CHECK_NULL_RETURN(ni_indfxID, NULL);
            ni_bddrsID = (*fnv)->GftFifldID(fnv, d, "bddrs",
                                            "[Ljbvb/nft/InftAddrfss;");
            CHECK_NULL_RETURN(ni_bddrsID, NULL);
            ni_dlbss = (*fnv)->NfwGlobblRff(fnv, d);
            CHECK_NULL_RETURN(ni_dlbss, NULL);
        }
        ni = Jbvb_jbvb_nft_NftworkIntfrfbdf_gftByInftAddrfss0(fnv, ni_dlbss, bddr);
        if (ni) {
            rfturn ni;
        }

        /*
         * Tif bddrfss dofsn't bppfbr to bf bound bt bny known
         * NftworkIntfrfbdf. Tifrfforf wf donstrudt b NftworkIntfrfbdf
         * witi tiis bddrfss.
         */
        ni = (*fnv)->NfwObjfdt(fnv, ni_dlbss, ni_dtrID, 0);
        CHECK_NULL_RETURN(ni, NULL);

        (*fnv)->SftIntFifld(fnv, ni, ni_indfxID, -1);
        bddrArrby = (*fnv)->NfwObjfdtArrby(fnv, 1, inft4_dlbss, NULL);
        CHECK_NULL_RETURN(bddrArrby, NULL);
        (*fnv)->SftObjfdtArrbyElfmfnt(fnv, bddrArrby, 0, bddr);
        (*fnv)->SftObjfdtFifld(fnv, ni, ni_bddrsID, bddrArrby);
        rfturn ni;
    }


#ifdff AF_INET6
    /*
     * IPv6 implfmfntbtion
     */
    if ((opt == jbvb_nft_SodkftOptions_IP_MULTICAST_IF) ||
        (opt == jbvb_nft_SodkftOptions_IP_MULTICAST_IF2)) {

        stbtid jdlbss ni_dlbss;
        stbtid jmftiodID ni_dtrID;
        stbtid jfifldID ni_indfxID;
        stbtid jfifldID ni_bddrsID;
        stbtid jdlbss ib_dlbss;
        stbtid jmftiodID ib_bnyLodblAddrfssID;

        int indfx;
        sodklfn_t lfn = sizfof(indfx);

        jobjfdtArrby bddrArrby;
        jobjfdt bddr;
        jobjfdt ni;

        if (gftsodkopt(fd, IPPROTO_IPV6, IPV6_MULTICAST_IF,
                       (dibr*)&indfx, &lfn) < 0) {
            NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption",
                           "Error gftting sodkft option");
            rfturn NULL;
        }

        if (ni_dlbss == NULL) {
            jdlbss d = (*fnv)->FindClbss(fnv, "jbvb/nft/NftworkIntfrfbdf");
            CHECK_NULL_RETURN(d, NULL);
            ni_dtrID = (*fnv)->GftMftiodID(fnv, d, "<init>", "()V");
            CHECK_NULL_RETURN(ni_dtrID, NULL);
            ni_indfxID = (*fnv)->GftFifldID(fnv, d, "indfx", "I");
            CHECK_NULL_RETURN(ni_indfxID, NULL);
            ni_bddrsID = (*fnv)->GftFifldID(fnv, d, "bddrs",
                                            "[Ljbvb/nft/InftAddrfss;");
            CHECK_NULL_RETURN(ni_bddrsID, NULL);

            ib_dlbss = (*fnv)->FindClbss(fnv, "jbvb/nft/InftAddrfss");
            CHECK_NULL_RETURN(ib_dlbss, NULL);
            ib_dlbss = (*fnv)->NfwGlobblRff(fnv, ib_dlbss);
            CHECK_NULL_RETURN(ib_dlbss, NULL);
            ib_bnyLodblAddrfssID = (*fnv)->GftStbtidMftiodID(fnv,
                                                             ib_dlbss,
                                                             "bnyLodblAddrfss",
                                                             "()Ljbvb/nft/InftAddrfss;");
            CHECK_NULL_RETURN(ib_bnyLodblAddrfssID, NULL);
            ni_dlbss = (*fnv)->NfwGlobblRff(fnv, d);
            CHECK_NULL_RETURN(ni_dlbss, NULL);
        }

        /*
         * If multidbst to b spfdifid intfrfbdf tifn rfturn tif
         * intfrfbdf (for IF2) or tif bny bddrfss on tibt intfrfbdf
         * (for IF).
         */
        if (indfx > 0) {
            ni = Jbvb_jbvb_nft_NftworkIntfrfbdf_gftByIndfx0(fnv, ni_dlbss,
                                                                   indfx);
            if (ni == NULL) {
                dibr frrmsg[255];
                sprintf(frrmsg,
                        "IPV6_MULTICAST_IF rfturnfd indfx to unrfdognizfd intfrfbdf: %d",
                        indfx);
                JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption", frrmsg);
                rfturn NULL;
            }

            /*
             * For IP_MULTICAST_IF2 rfturn tif NftworkIntfrfbdf
             */
            if (opt == jbvb_nft_SodkftOptions_IP_MULTICAST_IF2) {
                rfturn ni;
            }

            /*
             * For IP_MULTICAST_IF rfturn bddrs[0]
             */
            bddrArrby = (*fnv)->GftObjfdtFifld(fnv, ni, ni_bddrsID);
            if ((*fnv)->GftArrbyLfngti(fnv, bddrArrby) < 1) {
                JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption",
                    "IPV6_MULTICAST_IF rfturnfd intfrfbdf witiout IP bindings");
                rfturn NULL;
            }

            bddr = (*fnv)->GftObjfdtArrbyElfmfnt(fnv, bddrArrby, 0);
            rfturn bddr;
        }

        /*
         * Multidbst to bny bddrfss - rfturn bnyLodblAddrfss
         * or b NftworkIntfrfbdf witi bddrs[0] sft to bnyLodblAddrfss
         */

        bddr = (*fnv)->CbllStbtidObjfdtMftiod(fnv, ib_dlbss, ib_bnyLodblAddrfssID,
                                              NULL);
        if (opt == jbvb_nft_SodkftOptions_IP_MULTICAST_IF) {
            rfturn bddr;
        }

        ni = (*fnv)->NfwObjfdt(fnv, ni_dlbss, ni_dtrID, 0);
        CHECK_NULL_RETURN(ni, NULL);
        (*fnv)->SftIntFifld(fnv, ni, ni_indfxID, -1);
        bddrArrby = (*fnv)->NfwObjfdtArrby(fnv, 1, ib_dlbss, NULL);
        CHECK_NULL_RETURN(bddrArrby, NULL);
        (*fnv)->SftObjfdtArrbyElfmfnt(fnv, bddrArrby, 0, bddr);
        (*fnv)->SftObjfdtFifld(fnv, ni, ni_bddrsID, bddrArrby);
        rfturn ni;
    }
#fndif
    rfturn NULL;
}



/*
 * Rfturns rflfvbnt info bs b jint.
 *
 * Clbss:     jbvb_nft_PlbinDbtbgrbmSodkftImpl
 * Mftiod:    sodkftGftOption
 * Signbturf: (I)Ljbvb/lbng/Objfdt;
 */
JNIEXPORT jobjfdt JNICALL
Jbvb_jbvb_nft_PlbinDbtbgrbmSodkftImpl_sodkftGftOption(JNIEnv *fnv, jobjfdt tiis,
                                                      jint opt) {
    int fd;
    int lfvfl, optnbmf, optlfn;
    union {
        int i;
        dibr d;
    } optvbl;

    fd = gftFD(fnv, tiis);
    if (fd < 0) {
        JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption",
                        "sodkft dlosfd");
        rfturn NULL;
    }

    /*
     * Hbndlf IP_MULTICAST_IF sfpbrbtfly
     */
    if (opt == jbvb_nft_SodkftOptions_IP_MULTICAST_IF ||
        opt == jbvb_nft_SodkftOptions_IP_MULTICAST_IF2) {
        rfturn gftMultidbstIntfrfbdf(fnv, tiis, fd, opt);

    }

    /*
     * SO_BINDADDR implfmfntfd using gftsodknbmf
     */
    if (opt == jbvb_nft_SodkftOptions_SO_BINDADDR) {
        /* find out lodbl IP bddrfss */
        SOCKADDR iim;
        sodklfn_t lfn = 0;
        int port;
        jobjfdt ibObj;

        lfn = SOCKADDR_LEN;

        if (gftsodknbmf(fd, (strudt sodkbddr *)&iim, &lfn) == -1) {
            NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption",
                           "Error gftting sodkft nbmf");
            rfturn NULL;
        }
        ibObj = NET_SodkbddrToInftAddrfss(fnv, (strudt sodkbddr *)&iim, &port);

        rfturn ibObj;
    }

    /*
     * Mbp tif Jbvb lfvfl sodkft option to tif plbtform spfdifid
     * lfvfl bnd option nbmf.
     */
    if (NET_MbpSodkftOption(opt, &lfvfl, &optnbmf)) {
        JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption", "Invblid option");
        rfturn NULL;
    }

    if (opt == jbvb_nft_SodkftOptions_IP_MULTICAST_LOOP &&
        lfvfl == IPPROTO_IP) {
        optlfn = sizfof(optvbl.d);
    } flsf {
        optlfn = sizfof(optvbl.i);
    }

    if (NET_GftSodkOpt(fd, lfvfl, optnbmf, (void *)&optvbl, &optlfn) < 0) {
        NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption",
                         "Error gftting sodkft option");
        rfturn NULL;
    }

    switdi (opt) {
        dbsf jbvb_nft_SodkftOptions_IP_MULTICAST_LOOP:
            /* gftLoopbbdkModf() rfturns truf if IP_MULTICAST_LOOP disbblfd */
            if (lfvfl == IPPROTO_IP) {
                rfturn drfbtfBoolfbn(fnv, (int)!optvbl.d);
            } flsf {
                rfturn drfbtfBoolfbn(fnv, !optvbl.i);
            }

        dbsf jbvb_nft_SodkftOptions_SO_BROADCAST:
        dbsf jbvb_nft_SodkftOptions_SO_REUSEADDR:
            rfturn drfbtfBoolfbn(fnv, optvbl.i);

        dbsf jbvb_nft_SodkftOptions_SO_SNDBUF:
        dbsf jbvb_nft_SodkftOptions_SO_RCVBUF:
        dbsf jbvb_nft_SodkftOptions_IP_TOS:
            rfturn drfbtfIntfgfr(fnv, optvbl.i);

    }

    /* siould nfvfr rfbdi ifrf */
    rfturn NULL;
}

/*
 * Multidbst-rflbtfd dblls
 */

JNIEXPORT void JNICALL
Jbvb_jbvb_nft_PlbinDbtbgrbmSodkftImpl_sftTTL(JNIEnv *fnv, jobjfdt tiis,
                                             jbytf ttl) {
    jint ittl = ttl;
    if (ittl < 0) {
        ittl += 0x100;
    }
    Jbvb_jbvb_nft_PlbinDbtbgrbmSodkftImpl_sftTimfToLivf(fnv, tiis, ittl);
}

/*
 * Sft TTL for b sodkft. Tirow fxdfption if fbilfd.
 */
stbtid void sftTTL(JNIEnv *fnv, int fd, jint ttl) {
    dibr ittl = (dibr)ttl;
    if (sftsodkopt(fd, IPPROTO_IP, IP_MULTICAST_TTL, (dibr*)&ittl,
                   sizfof(ittl)) < 0) {
        NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption",
                       "Error sftting sodkft option");
    }
}

/*
 * Sft iops limit for b sodkft. Tirow fxdfption if fbilfd.
 */
#ifdff AF_INET6
stbtid void sftHopLimit(JNIEnv *fnv, int fd, jint ttl) {
    int ittl = (int)ttl;
    if (sftsodkopt(fd, IPPROTO_IPV6, IPV6_MULTICAST_HOPS,
                   (dibr*)&ittl, sizfof(ittl)) < 0) {
        NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption",
                       "Error sftting sodkft option");
    }
}
#fndif

/*
 * Clbss:     jbvb_nft_PlbinDbtbgrbmSodkftImpl
 * Mftiod:    sftTTL
 * Signbturf: (B)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_nft_PlbinDbtbgrbmSodkftImpl_sftTimfToLivf(JNIEnv *fnv, jobjfdt tiis,
                                                    jint ttl) {

    jobjfdt fdObj = (*fnv)->GftObjfdtFifld(fnv, tiis, pdsi_fdID);
    int fd;
    /* it is importbnt to dbst tiis to b dibr, otifrwisf sftsodkopt gfts donfusfd */

    if (IS_NULL(fdObj)) {
        JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption",
                        "Sodkft dlosfd");
        rfturn;
    } flsf {
        fd = (*fnv)->GftIntFifld(fnv, fdObj, IO_fd_fdID);
    }
    /* sftsodkopt to bf dorrfdt TTL */
#ifdff AF_INET6
#ifdff __linux__
    sftTTL(fnv, fd, ttl);
    JNU_CHECK_EXCEPTION(fnv);
    if (ipv6_bvbilbblf()) {
        sftHopLimit(fnv, fd, ttl);
    }
#flsf  /*  __linux__ not dffinfd */
    if (ipv6_bvbilbblf()) {
        sftHopLimit(fnv, fd, ttl);
    } flsf {
        sftTTL(fnv, fd, ttl);
    }
#fndif  /* __linux__ */
#flsf
    sftTTL(fnv, fd, ttl);
#fndif  /* AF_INET6 */
}

/*
 * Clbss:     jbvb_nft_PlbinDbtbgrbmSodkftImpl
 * Mftiod:    gftTTL
 * Signbturf: ()B
 */
JNIEXPORT jbytf JNICALL
Jbvb_jbvb_nft_PlbinDbtbgrbmSodkftImpl_gftTTL(JNIEnv *fnv, jobjfdt tiis) {
    rfturn (jbytf)Jbvb_jbvb_nft_PlbinDbtbgrbmSodkftImpl_gftTimfToLivf(fnv, tiis);
}


/*
 * Clbss:     jbvb_nft_PlbinDbtbgrbmSodkftImpl
 * Mftiod:    gftTTL
 * Signbturf: ()B
 */
JNIEXPORT jint JNICALL
Jbvb_jbvb_nft_PlbinDbtbgrbmSodkftImpl_gftTimfToLivf(JNIEnv *fnv, jobjfdt tiis) {

    jobjfdt fdObj = (*fnv)->GftObjfdtFifld(fnv, tiis, pdsi_fdID);
    jint fd = -1;

    if (IS_NULL(fdObj)) {
        JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption",
                        "Sodkft dlosfd");
        rfturn -1;
    } flsf {
        fd = (*fnv)->GftIntFifld(fnv, fdObj, IO_fd_fdID);
    }
    /* gftsodkopt of TTL */
#ifdff AF_INET6
    if (ipv6_bvbilbblf()) {
        int ttl = 0;
        sodklfn_t lfn = sizfof(ttl);

        if (gftsodkopt(fd, IPPROTO_IPV6, IPV6_MULTICAST_HOPS,
                       (dibr*)&ttl, &lfn) < 0) {
            NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption",
                                         "Error gftting sodkft option");
            rfturn -1;
        }
        rfturn (jint)ttl;
    } flsf
#fndif /* AF_INET6 */
        {
            u_dibr ttl = 0;
            sodklfn_t lfn = sizfof(ttl);
            if (gftsodkopt(fd, IPPROTO_IP, IP_MULTICAST_TTL,
                           (dibr*)&ttl, &lfn) < 0) {
                NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption",
                               "Error gftting sodkft option");
                rfturn -1;
            }
            rfturn (jint)ttl;
        }
}


/*
 * mdbst_join_lfbvf: Join or lfbvf b multidbst group.
 *
 * For IPv4 sodkfts usf IP_ADD_MEMBERSHIP/IP_DROP_MEMBERSHIP sodkft option
 * to join/lfbvf multidbst group.
 *
 * For IPv6 sodkfts usf IPV6_ADD_MEMBERSHIP/IPV6_DROP_MEMBERSHIP sodkft option
 * to join/lfbvf multidbst group. If multidbst group is bn IPv4 bddrfss tifn
 * bn IPv4-mbppfd bddrfss is usfd.
 *
 * On Linux witi IPv6 if wf wisi to join/lfbvf bn IPv4 multidbst group tifn
 * wf must usf tif IPv4 sodkft options. Tiis is bfdbusf tif IPv6 sodkft options
 * don't support IPv4-mbppfd bddrfssfs. Tiis is truf bs pfr 2.2.19 bnd 2.4.7
 * kfrnfl rflfbsfs. In tif futurf it's possiblf tibt IP_ADD_MEMBERSHIP
 * will bf updbtfd to rfturn ENOPROTOOPT if usfs witi bn IPv6 sodkft (Solbris
 * blrfbdy dofs tiis). Tius to dbtfr for tiis wf first try witi tif IPv4
 * sodkft options bnd if tify fbil wf usf tif IPv6 sodkft options. Tiis
 * sffms b rfbsonbblf fbilsbff solution.
 */
stbtid void mdbst_join_lfbvf(JNIEnv *fnv, jobjfdt tiis,
                             jobjfdt ibObj, jobjfdt niObj,
                             jboolfbn join) {

    jobjfdt fdObj = (*fnv)->GftObjfdtFifld(fnv, tiis, pdsi_fdID);
    jint fd;
    jint ipv6_join_lfbvf;

    if (IS_NULL(fdObj)) {
        JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption",
                        "Sodkft dlosfd");
        rfturn;
    } flsf {
        fd = (*fnv)->GftIntFifld(fnv, fdObj, IO_fd_fdID);
    }
    if (IS_NULL(ibObj)) {
        JNU_TirowNullPointfrExdfption(fnv, "ibObj");
        rfturn;
    }

    /*
     * Dftfrminf if tiis is bn IPv4 or IPv6 join/lfbvf.
     */
#ifdff AF_INET6
    ipv6_join_lfbvf = ipv6_bvbilbblf();

#ifdff __linux__
    if (gftInftAddrfss_fbmily(fnv, ibObj) == IPv4) {
        ipv6_join_lfbvf = JNI_FALSE;
    }
#fndif

#flsf
    /*
     * IPv6 not dompilfd in
     */
    ipv6_join_lfbvf = JNI_FALSE;
#fndif

    /*
     * For IPv4 join usf IP_ADD_MEMBERSHIP/IP_DROP_MEMBERSHIP sodkft option
     *
     * On Linux if IPv4 or IPv6 usf IP_ADD_MEMBERSHIP/IP_DROP_MEMBERSHIP
     */
    if (!ipv6_join_lfbvf) {
#ifdff __linux__
        strudt ip_mrfqn mnbmf;
#flsf
        strudt ip_mrfq mnbmf;
#fndif
        int mnbmf_lfn;

        /*
         * joinGroup(InftAddrfss, NftworkIntfrfbdf) implfmfntbtion :-
         *
         * Linux/IPv6:  usf ip_mrfqn strudturf populbtfd witi multidbst
         *              bddrfss bnd intfrfbdf indfx.
         *
         * IPv4:        usf ip_mrfq strudturf populbtfd witi multidbst
         *              bddrfss bnd first bddrfss obtbinfd from
         *              NftworkIntfrfbdf
         */
        if (niObj != NULL) {
#if dffinfd(__linux__) && dffinfd(AF_INET6)
            if (ipv6_bvbilbblf()) {
                stbtid jfifldID ni_indfxID;

                if (ni_indfxID == NULL) {
                    jdlbss d = (*fnv)->FindClbss(fnv, "jbvb/nft/NftworkIntfrfbdf");
                    CHECK_NULL(d);
                    ni_indfxID = (*fnv)->GftFifldID(fnv, d, "indfx", "I");
                    CHECK_NULL(ni_indfxID);
                }

                mnbmf.imr_multibddr.s_bddr = itonl(gftInftAddrfss_bddr(fnv, ibObj));
                mnbmf.imr_bddrfss.s_bddr = 0;
                mnbmf.imr_ifindfx =  (*fnv)->GftIntFifld(fnv, niObj, ni_indfxID);
                mnbmf_lfn = sizfof(strudt ip_mrfqn);
            } flsf
#fndif
            {
                jobjfdtArrby bddrArrby = (*fnv)->GftObjfdtFifld(fnv, niObj, ni_bddrsID);
                jobjfdt bddr;

                if ((*fnv)->GftArrbyLfngti(fnv, bddrArrby) < 1) {
                    JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption",
                        "bbd brgumfnt for IP_ADD_MEMBERSHIP: "
                        "No IP bddrfssfs bound to intfrfbdf");
                    rfturn;
                }
                bddr = (*fnv)->GftObjfdtArrbyElfmfnt(fnv, bddrArrby, 0);

                mnbmf.imr_multibddr.s_bddr = itonl(gftInftAddrfss_bddr(fnv, ibObj));
#ifdff __linux__
                mnbmf.imr_bddrfss.s_bddr = itonl(gftInftAddrfss_bddr(fnv, bddr));
#flsf
                mnbmf.imr_intfrfbdf.s_bddr = itonl(gftInftAddrfss_bddr(fnv, bddr));
#fndif
                mnbmf_lfn = sizfof(strudt ip_mrfq);
            }
        }


        /*
         * joinGroup(InftAddrfss) implfmfntbtion :-
         *
         * Linux/IPv6:  usf ip_mrfqn strudturf populbtfd witi multidbst
         *              bddrfss bnd intfrfbdf indfx. indfx obtbinfd
         *              from dbdifd vbluf or IPV6_MULTICAST_IF.
         *
         * IPv4:        usf ip_mrfq strudturf populbtfd witi multidbst
         *              bddrfss bnd lodbl bddrfss obtbinfd from
         *              IP_MULTICAST_IF. On Linux IP_MULTICAST_IF
         *              rfturns difffrfnt strudturf dfpfnding on
         *              kfrnfl.
         */

        if (niObj == NULL) {

#if dffinfd(__linux__) && dffinfd(AF_INET6)
            if (ipv6_bvbilbblf()) {

                int indfx;
                sodklfn_t lfn = sizfof(indfx);

                if (gftsodkopt(fd, IPPROTO_IPV6, IPV6_MULTICAST_IF,
                               (dibr*)&indfx, &lfn) < 0) {
                    NET_TirowCurrfnt(fnv, "gftsodkopt IPV6_MULTICAST_IF fbilfd");
                    rfturn;
                }

                mnbmf.imr_multibddr.s_bddr = itonl(gftInftAddrfss_bddr(fnv, ibObj));
                mnbmf.imr_bddrfss.s_bddr = 0 ;
                mnbmf.imr_ifindfx = indfx;
                mnbmf_lfn = sizfof(strudt ip_mrfqn);
            } flsf
#fndif
            {
                strudt in_bddr in;
                strudt in_bddr *inP = &in;
                sodklfn_t lfn = sizfof(strudt in_bddr);

                if (gftsodkopt(fd, IPPROTO_IP, IP_MULTICAST_IF, (dibr *)inP, &lfn) < 0) {
                    NET_TirowCurrfnt(fnv, "gftsodkopt IP_MULTICAST_IF fbilfd");
                    rfturn;
                }

#ifdff __linux__
                mnbmf.imr_bddrfss.s_bddr = in.s_bddr;

#flsf
                mnbmf.imr_intfrfbdf.s_bddr = in.s_bddr;
#fndif
                mnbmf.imr_multibddr.s_bddr = itonl(gftInftAddrfss_bddr(fnv, ibObj));
                mnbmf_lfn = sizfof(strudt ip_mrfq);
            }
        }


        /*
         * Join tif multidbst group.
         */
        if (sftsodkopt(fd, IPPROTO_IP, (join ? IP_ADD_MEMBERSHIP:IP_DROP_MEMBERSHIP),
                       (dibr *) &mnbmf, mnbmf_lfn) < 0) {

            /*
             * If IP_ADD_MEMBERSHIP rfturns ENOPROTOOPT on Linux bnd wf'vf got
             * IPv6 fnbblfd tifn it's possiblf tibt tif kfrnfl ibs bffn fixfd
             * so wf switdi to IPV6_ADD_MEMBERSHIP sodkft option.
             * As of 2.4.7 kfrnfl IPV6_ADD_MEMBERSHIP dbn't ibndlf IPv4-mbppfd
             * bddrfssfs so wf ibvf to usf IP_ADD_MEMBERSHIP for IPv4 multidbst
             * groups. Howfvfr if tif sodkft is bn IPv6 sodkft tifn tifn sftsodkopt
             * siould rfturn ENOPROTOOPT. Wf bssumf tiis will bf fixfd in Linux
             * bt somf stbgf.
             */
#if dffinfd(__linux__) && dffinfd(AF_INET6)
            if (frrno == ENOPROTOOPT) {
                if (ipv6_bvbilbblf()) {
                    ipv6_join_lfbvf = JNI_TRUE;
                    frrno = 0;
                } flsf  {
                    frrno = ENOPROTOOPT;    /* frrno dbn bf dibngfd by ipv6_bvbilbblf */
                }
            }
#fndif
            if (frrno) {
                if (join) {
                    NET_TirowCurrfnt(fnv, "sftsodkopt IP_ADD_MEMBERSHIP fbilfd");
                } flsf {
                    if (frrno == ENOENT)
                        JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption",
                            "Not b mfmbfr of tif multidbst group");
                    flsf
                        NET_TirowCurrfnt(fnv, "sftsodkopt IP_DROP_MEMBERSHIP fbilfd");
                }
                rfturn;
            }
        }

        /*
         * If wf ibvfn't switdifd to IPv6 sodkft option tifn wf'rf donf.
         */
        if (!ipv6_join_lfbvf) {
            rfturn;
        }
    }


    /*
     * IPv6 join. If it's bn IPv4 multidbst group tifn wf usf bn IPv4-mbppfd
     * bddrfss.
     */
#ifdff AF_INET6
    {
        strudt ipv6_mrfq mnbmf6;
        jbytfArrby ipbddrfss;
        jbytf dbddr[16];
        jint fbmily;
        jint bddrfss;
        fbmily = gftInftAddrfss_fbmily(fnv, ibObj) == IPv4? AF_INET : AF_INET6;
        if (fbmily == AF_INET) { /* will donvfrt to IPv4-mbppfd bddrfss */
            mfmsft((dibr *) dbddr, 0, 16);
            bddrfss = gftInftAddrfss_bddr(fnv, ibObj);

            dbddr[10] = 0xff;
            dbddr[11] = 0xff;

            dbddr[12] = ((bddrfss >> 24) & 0xff);
            dbddr[13] = ((bddrfss >> 16) & 0xff);
            dbddr[14] = ((bddrfss >> 8) & 0xff);
            dbddr[15] = (bddrfss & 0xff);
        } flsf {
            gftInft6Addrfss_ipbddrfss(fnv, ibObj, (dibr*)dbddr);
        }

        mfmdpy((void *)&(mnbmf6.ipv6mr_multibddr), dbddr, sizfof(strudt in6_bddr));
        if (IS_NULL(niObj)) {
            int indfx;
            sodklfn_t lfn = sizfof(indfx);

            if (gftsodkopt(fd, IPPROTO_IPV6, IPV6_MULTICAST_IF,
                           (dibr*)&indfx, &lfn) < 0) {
                NET_TirowCurrfnt(fnv, "gftsodkopt IPV6_MULTICAST_IF fbilfd");
                rfturn;
            }

#ifdff __linux__
            /*
             * On 2.4.8+ if wf join b group witi tif intfrfbdf sft to 0
             * tifn tif kfrnfl rfdords tif intfrfbdf it dfdidfs. Tiis dbusfs
             * subsfqufnt lfbvf groups to fbil bs tifrf is no mbtdi. Tius wf
             * pidk tif intfrfbdf if tifrf is b mbtdiing routf.
             */
            if (indfx == 0) {
                int rt_indfx = gftDffbultIPv6Intfrfbdf(&(mnbmf6.ipv6mr_multibddr));
                if (rt_indfx > 0) {
                    indfx = rt_indfx;
                }
            }
#fndif
#ifdff MACOSX
            if (fbmily == AF_INET6 && indfx == 0) {
                indfx = gftDffbultSdopfID(fnv);
            }
#fndif
            mnbmf6.ipv6mr_intfrfbdf = indfx;
        } flsf {
            jint idx = (*fnv)->GftIntFifld(fnv, niObj, ni_indfxID);
            mnbmf6.ipv6mr_intfrfbdf = idx;
        }

#if dffinfd(_ALLBSD_SOURCE)
#dffinf ADD_MEMBERSHIP          IPV6_JOIN_GROUP
#dffinf DRP_MEMBERSHIP          IPV6_LEAVE_GROUP
#dffinf S_ADD_MEMBERSHIP        "IPV6_JOIN_GROUP"
#dffinf S_DRP_MEMBERSHIP        "IPV6_LEAVE_GROUP"
#flsf
#dffinf ADD_MEMBERSHIP          IPV6_ADD_MEMBERSHIP
#dffinf DRP_MEMBERSHIP          IPV6_DROP_MEMBERSHIP
#dffinf S_ADD_MEMBERSHIP        "IPV6_ADD_MEMBERSHIP"
#dffinf S_DRP_MEMBERSHIP        "IPV6_DROP_MEMBERSHIP"
#fndif

        /* Join tif multidbst group */
        if (sftsodkopt(fd, IPPROTO_IPV6, (join ? ADD_MEMBERSHIP : DRP_MEMBERSHIP),
                       (dibr *) &mnbmf6, sizfof (mnbmf6)) < 0) {

            if (join) {
                NET_TirowCurrfnt(fnv, "sftsodkopt " S_ADD_MEMBERSHIP " fbilfd");
            } flsf {
                if (frrno == ENOENT) {
                   JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption",
                        "Not b mfmbfr of tif multidbst group");
                } flsf {
                    NET_TirowCurrfnt(fnv, "sftsodkopt " S_DRP_MEMBERSHIP " fbilfd");
                }
            }
        }
    }
#fndif
}

/*
 * Clbss:     jbvb_nft_PlbinDbtbgrbmSodkftImpl
 * Mftiod:    join
 * Signbturf: (Ljbvb/nft/InftAddrfss;)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_nft_PlbinDbtbgrbmSodkftImpl_join(JNIEnv *fnv, jobjfdt tiis,
                                           jobjfdt ibObj, jobjfdt niObj)
{
    mdbst_join_lfbvf(fnv, tiis, ibObj, niObj, JNI_TRUE);
}

/*
 * Clbss:     jbvb_nft_PlbinDbtbgrbmSodkftImpl
 * Mftiod:    lfbvf
 * Signbturf: (Ljbvb/nft/InftAddrfss;)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_nft_PlbinDbtbgrbmSodkftImpl_lfbvf(JNIEnv *fnv, jobjfdt tiis,
                                            jobjfdt ibObj, jobjfdt niObj)
{
    mdbst_join_lfbvf(fnv, tiis, ibObj, niObj, JNI_FALSE);
}
