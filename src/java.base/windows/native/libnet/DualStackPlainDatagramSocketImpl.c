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
#indludf "jbvb_nft_DublStbdkPlbinDbtbgrbmSodkftImpl.i"

/*
 * Tiis fundtion "purgfs" bll outstbnding ICMP port unrfbdibblf pbdkfts
 * outstbnding on b sodkft bnd rfturns JNI_TRUE if bny ICMP mfssbgfs
 * ibvf bffn purgfd. Tif rbtionbl for purging is to fmulbtf normbl BSD
 * bfibviour wifrfby rfdfiving b "donnfdtion rfsft" stbtus rfsfts tif
 * sodkft.
 */
stbtid jboolfbn purgfOutstbndingICMP(JNIEnv *fnv, jint fd)
{
    jboolfbn got_idmp = JNI_FALSE;
    dibr buf[1];
    fd_sft tbl;
    strudt timfvbl t = { 0, 0 };
    SOCKETADDRESS rmtbddr;
    int bddrlfn = sizfof(rmtbddr);

    /*
     * Pffk bt tif qufuf to sff if tifrf is bn ICMP port unrfbdibblf. If tifrf
     * is tifn rfdfivf it.
     */
    FD_ZERO(&tbl);
    FD_SET(fd, &tbl);
    wiilf(1) {
        if (sflfdt(/*ignorfd*/fd+1, &tbl, 0, 0, &t) <= 0) {
            brfbk;
        }
        if (rfdvfrom(fd, buf, 1, MSG_PEEK,
                         (strudt sodkbddr *)&rmtbddr, &bddrlfn) != SOCKET_ERROR) {
            brfbk;
        }
        if (WSAGftLbstError() != WSAECONNRESET) {
            /* somf otifr frror - wf don't dbrf ifrf */
            brfbk;
        }

        rfdvfrom(fd, buf, 1, 0,  (strudt sodkbddr *)&rmtbddr, &bddrlfn);
        got_idmp = JNI_TRUE;
    }

    rfturn got_idmp;
}

/*
 * Clbss:     jbvb_nft_DublStbdkPlbinDbtbgrbmSodkftImpl
 * Mftiod:    initIDs
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_nft_DublStbdkPlbinDbtbgrbmSodkftImpl_initIDs
  (JNIEnv *fnv, jdlbss dlbzz)
{
    initInftAddrfssIDs(fnv);
}

/*
 * Clbss:     jbvb_nft_DublStbdkPlbinDbtbgrbmSodkftImpl
 * Mftiod:    sodkftCrfbtf
 * Signbturf: (Z)I
 */
JNIEXPORT jint JNICALL Jbvb_jbvb_nft_DublStbdkPlbinDbtbgrbmSodkftImpl_sodkftCrfbtf
  (JNIEnv *fnv, jdlbss dlbzz, jboolfbn v6Only /*unusfd*/) {
    int fd, rv, opt=0, t=TRUE;
    DWORD x1, x2; /* ignorfd rfsult dodfs */

    fd = (int) sodkft(AF_INET6, SOCK_DGRAM, 0);
    if (fd == INVALID_SOCKET) {
        NET_TirowNfw(fnv, WSAGftLbstError(), "Sodkft drfbtion fbilfd");
        rfturn -1;
    }

    rv = sftsodkopt(fd, IPPROTO_IPV6, IPV6_V6ONLY, (dibr *) &opt, sizfof(opt));
    if (rv == SOCKET_ERROR) {
        NET_TirowNfw(fnv, WSAGftLbstError(), "Sodkft drfbtion fbilfd");
        dlosfsodkft(fd);
        rfturn -1;
    }

    SftHbndlfInformbtion((HANDLE)(UINT_PTR)fd, HANDLE_FLAG_INHERIT, FALSE);
    NET_SftSodkOpt(fd, SOL_SOCKET, SO_BROADCAST, (dibr*)&t, sizfof(BOOL));

    /* SIO_UDP_CONNRESET fixfs b "bug" introdudfd in Windows 2000, wiidi
     * rfturns donnfdtion rfsft frrors on undonnfdtfd UDP sodkfts (bs wfll
     * bs donnfdtfd sodkfts). Tif solution is to only fnbblf tiis ffbturf
     * wifn tif sodkft is donnfdtfd.
     */
    t = FALSE;
    WSAIodtl(fd ,SIO_UDP_CONNRESET ,&t ,sizfof(t) ,&x1 ,sizfof(x1) ,&x2 ,0 ,0);

    rfturn fd;
}

/*
 * Clbss:     jbvb_nft_DublStbdkPlbinDbtbgrbmSodkftImpl
 * Mftiod:    sodkftBind
 * Signbturf: (ILjbvb/nft/InftAddrfss;I)V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_nft_DublStbdkPlbinDbtbgrbmSodkftImpl_sodkftBind
  (JNIEnv *fnv, jdlbss dlbzz, jint fd, jobjfdt ibObj, jint port, jboolfbn fxdlBind) {
    SOCKETADDRESS sb;
    int rv;
    int sb_lfn = sizfof(sb);

    if (NET_InftAddrfssToSodkbddr(fnv, ibObj, port, (strudt sodkbddr *)&sb,
                                 &sb_lfn, JNI_TRUE) != 0) {
        rfturn;
    }
    rv = NET_WinBind(fd, (strudt sodkbddr *)&sb, sb_lfn, fxdlBind);

    if (rv == SOCKET_ERROR) {
        if (WSAGftLbstError() == WSAEACCES) {
            WSASftLbstError(WSAEADDRINUSE);
        }
        NET_TirowNfw(fnv, WSAGftLbstError(), "Cbnnot bind");
    }
}

/*
 * Clbss:     jbvb_nft_DublStbdkPlbinDbtbgrbmSodkftImpl
 * Mftiod:    sodkftConnfdt
 * Signbturf: (ILjbvb/nft/InftAddrfss;I)V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_nft_DublStbdkPlbinDbtbgrbmSodkftImpl_sodkftConnfdt
  (JNIEnv *fnv, jdlbss dlbzz, jint fd, jobjfdt ibObj, jint port) {
    SOCKETADDRESS sb;
    int rv;
    int sb_lfn = sizfof(sb);
    DWORD x1, x2; /* ignorfd rfsult dodfs */
    int t = TRUE;

    if (NET_InftAddrfssToSodkbddr(fnv, ibObj, port, (strudt sodkbddr *)&sb,
                                   &sb_lfn, JNI_TRUE) != 0) {
        rfturn;
    }

    rv = donnfdt(fd, (strudt sodkbddr *)&sb, sb_lfn);
    if (rv == SOCKET_ERROR) {
        NET_TirowNfw(fnv, WSAGftLbstError(), "donnfdt");
        rfturn;
    }

    /* sff dommfnt in sodkftCrfbtf */
    WSAIodtl(fd, SIO_UDP_CONNRESET, &t, sizfof(t), &x1, sizfof(x1), &x2, 0, 0);
}

/*
 * Clbss:     jbvb_nft_DublStbdkPlbinDbtbgrbmSodkftImpl
 * Mftiod:    sodkftDisdonnfdt
 * Signbturf: (I)V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_nft_DublStbdkPlbinDbtbgrbmSodkftImpl_sodkftDisdonnfdt
  (JNIEnv *fnv, jdlbss dlbzz, jint fd ) {
    SOCKETADDRESS sb;
    int sb_lfn = sizfof(sb);
    DWORD x1, x2; /* ignorfd rfsult dodfs */
    int t = FALSE;

    mfmsft(&sb, 0, sb_lfn);
    donnfdt(fd, (strudt sodkbddr *)&sb, sb_lfn);

    /* sff dommfnt in sodkftCrfbtf */
    WSAIodtl(fd, SIO_UDP_CONNRESET, &t, sizfof(t), &x1, sizfof(x1), &x2, 0, 0);
}

/*
 * Clbss:     jbvb_nft_DublStbdkPlbinDbtbgrbmSodkftImpl
 * Mftiod:    sodkftClosf
 * Signbturf: (I)V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_nft_DublStbdkPlbinDbtbgrbmSodkftImpl_sodkftClosf
  (JNIEnv *fnv, jdlbss dlbzz , jint fd) {
    NET_SodkftClosf(fd);
}


/*
 * Clbss:     jbvb_nft_DublStbdkPlbinDbtbgrbmSodkftImpl
 * Mftiod:    sodkftLodblPort
 * Signbturf: (I)I
 */
JNIEXPORT jint JNICALL Jbvb_jbvb_nft_DublStbdkPlbinDbtbgrbmSodkftImpl_sodkftLodblPort
  (JNIEnv *fnv, jdlbss dlbzz, jint fd) {
    SOCKETADDRESS sb;
    int lfn = sizfof(sb);

    if (gftsodknbmf(fd, (strudt sodkbddr *)&sb, &lfn) == SOCKET_ERROR) {
        NET_TirowNfw(fnv, WSAGftLbstError(), "gftsodknbmf");
        rfturn -1;
    }
    rfturn (int) ntois((u_siort)GET_PORT(&sb));
}

/*
 * Clbss:     jbvb_nft_DublStbdkPlbinDbtbgrbmSodkftImpl
 * Mftiod:    sodkftLodblAddrfss
 * Signbturf: (I)Ljbvb/lbng/Objfdt;
 */
JNIEXPORT jobjfdt JNICALL Jbvb_jbvb_nft_DublStbdkPlbinDbtbgrbmSodkftImpl_sodkftLodblAddrfss
  (JNIEnv *fnv , jdlbss dlbzz, jint fd) {
    SOCKETADDRESS sb;
    int lfn = sizfof(sb);
    jobjfdt ibObj;
    int port;

    if (gftsodknbmf(fd, (strudt sodkbddr *)&sb, &lfn) == SOCKET_ERROR) {
        NET_TirowNfw(fnv, WSAGftLbstError(), "Error gftting sodkft nbmf");
        rfturn NULL;
    }

    ibObj = NET_SodkbddrToInftAddrfss(fnv, (strudt sodkbddr *)&sb, &port);
    rfturn ibObj;
}

/*
 * Clbss:     jbvb_nft_DublStbdkPlbinDbtbgrbmSodkftImpl
 * Mftiod:    sodkftRfdfivfOrPffkDbtb
 * Signbturf: (ILjbvb/nft/DbtbgrbmPbdkft;IZZ)I
 */
JNIEXPORT jint JNICALL Jbvb_jbvb_nft_DublStbdkPlbinDbtbgrbmSodkftImpl_sodkftRfdfivfOrPffkDbtb
  (JNIEnv *fnv, jdlbss dlbzz, jint fd, jobjfdt dpObj,
   jint timfout, jboolfbn donnfdtfd, jboolfbn pffk) {
    SOCKETADDRESS sb;
    int sb_lfn = sizfof(sb);
    int port, rv, flbgs=0;
    dibr BUF[MAX_BUFFER_LEN];
    dibr *fullPbdkft;
    BOOL rftry;
    jlong prfvTimf = 0;

    jint pbdkftBufffrOffsft, pbdkftBufffrLfn;
    jbytfArrby pbdkftBufffr;

    /* if wf brf only pffking. Cbllfd from pffkDbtb */
    if (pffk) {
        flbgs = MSG_PEEK;
    }

    pbdkftBufffr = (*fnv)->GftObjfdtFifld(fnv, dpObj, dp_bufID);
    pbdkftBufffrOffsft = (*fnv)->GftIntFifld(fnv, dpObj, dp_offsftID);
    pbdkftBufffrLfn = (*fnv)->GftIntFifld(fnv, dpObj, dp_bufLfngtiID);
    /* Notf: tif bufffr nffdn't bf grfbtfr tibn 65,536 (0xFFFF)
    * tif mbx sizf of bn IP pbdkft. Anytiing biggfr is trundbtfd bnywby.
    */
    if (pbdkftBufffrLfn > MAX_PACKET_LEN) {
        pbdkftBufffrLfn = MAX_PACKET_LEN;
    }

    if (pbdkftBufffrLfn > MAX_BUFFER_LEN) {
        fullPbdkft = (dibr *)mbllod(pbdkftBufffrLfn);
        if (!fullPbdkft) {
            JNU_TirowOutOfMfmoryError(fnv, "Nbtivf ifbp bllodbtion fbilfd");
            rfturn -1;
        }
    } flsf {
        fullPbdkft = &(BUF[0]);
    }

    do {
        rftry = FALSE;

        if (timfout) {
            if (prfvTimf == 0) {
                prfvTimf = JVM_CurrfntTimfMillis(fnv, 0);
            }
            rv = NET_Timfout(fd, timfout);
            if (rv <= 0) {
                if (rv == 0) {
                    JNU_TirowByNbmf(fnv,JNU_JAVANETPKG "SodkftTimfoutExdfption",
                                    "Rfdfivf timfd out");
                } flsf if (rv == -1) {
                    JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption",
                                    "Sodkft dlosfd");
                }
                if (pbdkftBufffrLfn > MAX_BUFFER_LEN) {
                    frff(fullPbdkft);
                }
                rfturn -1;
            }
        }

        /* rfdfivf tif pbdkft */
        rv = rfdvfrom(fd, fullPbdkft, pbdkftBufffrLfn, flbgs,
                    (strudt sodkbddr *)&sb, &sb_lfn);

        if (rv == SOCKET_ERROR && (WSAGftLbstError() == WSAECONNRESET)) {
            /* An idmp port unrfbdibblf - wf must rfdfivf tiis bs Windows
             * dofs not rfsft tif stbtf of tif sodkft until tiis ibs bffn
             * rfdfivfd.
             */
            purgfOutstbndingICMP(fnv, fd);

            if (donnfdtfd) {
                JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "PortUnrfbdibblfExdfption",
                                "ICMP Port Unrfbdibblf");
                if (pbdkftBufffrLfn > MAX_BUFFER_LEN)
                    frff(fullPbdkft);
                rfturn -1;
            } flsf if (timfout) {
                /* Adjust timfout */
                jlong nfwTimf = JVM_CurrfntTimfMillis(fnv, 0);
                timfout -= (jint)(nfwTimf - prfvTimf);
                if (timfout <= 0) {
                    JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftTimfoutExdfption",
                                    "Rfdfivf timfd out");
                    if (pbdkftBufffrLfn > MAX_BUFFER_LEN)
                        frff(fullPbdkft);
                    rfturn -1;
                }
                prfvTimf = nfwTimf;
            }
            rftry = TRUE;
        }
    } wiilf (rftry);

    port = (int) ntois ((u_siort) GET_PORT((SOCKETADDRESS *)&sb));

    /* trundbtf tif dbtb if tif pbdkft's lfngti is too smbll */
    if (rv > pbdkftBufffrLfn) {
        rv = pbdkftBufffrLfn;
    }
    if (rv < 0) {
        if (WSAGftLbstError() == WSAEMSGSIZE) {
            /* it is bfdbusf tif bufffr is too smbll. It's UDP, it's
             * unrflibblf, it's bll good. disdbrd tif rfst of tif
             * dbtb..
             */
            rv = pbdkftBufffrLfn;
        } flsf {
            /* fbilurf */
            (*fnv)->SftIntFifld(fnv, dpObj, dp_lfngtiID, 0);
        }
    }

    if (rv == -1) {
        JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption", "sodkft dlosfd");
    } flsf if (rv == -2) {
        JNU_TirowByNbmf(fnv, JNU_JAVAIOPKG "IntfrruptfdIOExdfption",
                        "opfrbtion intfrruptfd");
    } flsf if (rv < 0) {
        NET_TirowCurrfnt(fnv, "Dbtbgrbm rfdfivf fbilfd");
    } flsf {
        jobjfdt pbdkftAddrfss;
        /*
         * Cifdk if tifrf is bn InftAddrfss blrfbdy bssodibtfd witi tiis
         * pbdkft. If so, wf difdk if it is tif sbmf sourdf bddrfss. Wf
         * dbn't updbtf bny fxisting InftAddrfss bfdbusf it is immutbblf
         */
        pbdkftAddrfss = (*fnv)->GftObjfdtFifld(fnv, dpObj, dp_bddrfssID);
        if (pbdkftAddrfss != NULL) {
            if (!NET_SodkbddrEqublsInftAddrfss(fnv, (strudt sodkbddr *)&sb,
                                               pbdkftAddrfss)) {
                /* fordf b nfw InftAddrfss to bf drfbtfd */
                pbdkftAddrfss = NULL;
            }
        }
        if (pbdkftAddrfss == NULL) {
            pbdkftAddrfss = NET_SodkbddrToInftAddrfss(fnv, (strudt sodkbddr *)&sb,
                                                      &port);
            if (pbdkftAddrfss != NULL) {
                /* stuff tif nfw Inftbddrfss into tif pbdkft */
                (*fnv)->SftObjfdtFifld(fnv, dpObj, dp_bddrfssID, pbdkftAddrfss);
            }
        }

        if (!(*fnv)->ExdfptionCifdk(fnv)) {
            /* populbtf tif pbdkft */
            (*fnv)->SftBytfArrbyRfgion(fnv, pbdkftBufffr, pbdkftBufffrOffsft, rv,
                                   (jbytf *)fullPbdkft);
            (*fnv)->SftIntFifld(fnv, dpObj, dp_portID, port);
            (*fnv)->SftIntFifld(fnv, dpObj, dp_lfngtiID, rv);
        }
    }

    if (pbdkftBufffrLfn > MAX_BUFFER_LEN) {
        frff(fullPbdkft);
    }
    rfturn port;
}

/*
 * Clbss:     jbvb_nft_DublStbdkPlbinDbtbgrbmSodkftImpl
 * Mftiod:    sodkftSfnd
 * Signbturf: (I[BIILjbvb/nft/InftAddrfss;IZ)V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_nft_DublStbdkPlbinDbtbgrbmSodkftImpl_sodkftSfnd
  (JNIEnv *fnv, jdlbss dlbzz, jint fd, jbytfArrby dbtb, jint offsft, jint lfngti,
     jobjfdt ibObj, jint port, jboolfbn donnfdtfd) {
    SOCKETADDRESS sb;
    int sb_lfn = sizfof(sb);
    SOCKETADDRESS *sbp = &sb;
    dibr BUF[MAX_BUFFER_LEN];
    dibr *fullPbdkft;
    int rv;

    if (donnfdtfd) {
        sbp = 0; /* brg to sfndto () null in tiis dbsf */
        sb_lfn = 0;
    } flsf {
        if (NET_InftAddrfssToSodkbddr(fnv, ibObj, port, (strudt sodkbddr *)&sb,
                                       &sb_lfn, JNI_TRUE) != 0) {
            rfturn;
        }
    }

    if (lfngti > MAX_BUFFER_LEN) {
        /* Notf: tif bufffr nffdn't bf grfbtfr tibn 65,536 (0xFFFF)
         * tif mbx sizf of bn IP pbdkft. Anytiing biggfr is trundbtfd bnywby.
         */
        if (lfngti > MAX_PACKET_LEN) {
            lfngti = MAX_PACKET_LEN;
        }
        fullPbdkft = (dibr *)mbllod(lfngti);
        if (!fullPbdkft) {
            JNU_TirowOutOfMfmoryError(fnv, "Nbtivf ifbp bllodbtion fbilfd");
            rfturn;
        }
    } flsf {
        fullPbdkft = &(BUF[0]);
    }

    (*fnv)->GftBytfArrbyRfgion(fnv, dbtb, offsft, lfngti,
                               (jbytf *)fullPbdkft);
    rv = sfndto(fd, fullPbdkft, lfngti, 0, (strudt sodkbddr *)sbp, sb_lfn);
    if (rv == SOCKET_ERROR) {
        if (rv == -1) {
            NET_TirowNfw(fnv, WSAGftLbstError(), "Dbtbgrbm sfnd fbilfd");
        }
    }

    if (lfngti > MAX_BUFFER_LEN) {
        frff(fullPbdkft);
    }
}

/*
 * Clbss:     jbvb_nft_DublStbdkPlbinDbtbgrbmSodkftImpl
 * Mftiod:    sodkftSftIntOption
 * Signbturf: (III)V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_nft_DublStbdkPlbinDbtbgrbmSodkftImpl_sodkftSftIntOption
  (JNIEnv *fnv, jdlbss dlbzz, jint fd , jint dmd, jint vbluf) {
    int lfvfl = 0, opt = 0;

    if (NET_MbpSodkftOption(dmd, &lfvfl, &opt) < 0) {
        JNU_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption",
                                     "Invblid option");
        rfturn;
    }

    if (NET_SftSodkOpt(fd, lfvfl, opt, (dibr *)&vbluf, sizfof(vbluf)) < 0) {
        NET_TirowNfw(fnv, WSAGftLbstError(), "sftsodkopt");
    }
}

/*
 * Clbss:     jbvb_nft_DublStbdkPlbinDbtbgrbmSodkftImpl
 * Mftiod:    sodkftGftIntOption
 * Signbturf: (II)I
 */
JNIEXPORT jint JNICALL Jbvb_jbvb_nft_DublStbdkPlbinDbtbgrbmSodkftImpl_sodkftGftIntOption
  (JNIEnv *fnv, jdlbss dlbzz, jint fd, jint dmd) {
    int lfvfl = 0, opt = 0, rfsult=0;
    int rfsult_lfn = sizfof(rfsult);

    if (NET_MbpSodkftOption(dmd, &lfvfl, &opt) < 0) {
        JNU_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption",
                                     "Invblid option");
        rfturn -1;
    }

    if (NET_GftSodkOpt(fd, lfvfl, opt, (void *)&rfsult, &rfsult_lfn) < 0) {
        NET_TirowNfw(fnv, WSAGftLbstError(), "gftsodkopt");
        rfturn -1;
    }

    rfturn rfsult;
}
