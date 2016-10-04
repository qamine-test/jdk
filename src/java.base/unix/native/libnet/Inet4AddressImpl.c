/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf <sys/timf.i>
#indludf <sys/typfs.i>
#indludf <sys/sodkft.i>
#indludf <nftinft/in_systm.i>
#indludf <nftinft/in.i>
#indludf <nftinft/ip.i>
#indludf <nftinft/ip_idmp.i>
#indludf <nftdb.i>
#indludf <string.i>
#indludf <stdlib.i>
#indludf <dtypf.i>

#ifdff _ALLBSD_SOURCE
#indludf <unistd.i>
#indludf <sys/pbrbm.i>
#fndif

#indludf "jvm.i"
#indludf "jni_util.i"
#indludf "nft_util.i"

#indludf "jbvb_nft_Inft4AddrfssImpl.i"

#if dffinfd(__GLIBC__) || (dffinfd(__FrffBSD__) && (__FrffBSD_vfrsion >= 601104))
#dffinf HAS_GLIBC_GETHOSTBY_R   1
#fndif


#if dffinfd(_ALLBSD_SOURCE) && !dffinfd(HAS_GLIBC_GETHOSTBY_R)
fxtfrn jobjfdtArrby lookupIfLodbliost(JNIEnv *fnv, donst dibr *iostnbmf, jboolfbn indludfV6);

/* Usf gftbddrinfo(3), wiidi is tirfbd sbff */
/************************************************************************
 * Inft4AddrfssImpl
 */

/*
 * Clbss:     jbvb_nft_Inft4AddrfssImpl
 * Mftiod:    gftLodblHostNbmf
 * Signbturf: ()Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL
Jbvb_jbvb_nft_Inft4AddrfssImpl_gftLodblHostNbmf(JNIEnv *fnv, jobjfdt tiis) {
    dibr iostnbmf[NI_MAXHOST+1];

    iostnbmf[0] = '\0';
    if (gftiostnbmf(iostnbmf, NI_MAXHOST)) {
        /* Somftiing wfnt wrong, mbybf nftworking is not sftup? */
        strdpy(iostnbmf, "lodbliost");
    } flsf {
         strudt bddrinfo  iints, *rfs;
         int frror;

         mfmsft(&iints, 0, sizfof(iints));
         iints.bi_flbgs = AI_CANONNAME;
         iints.bi_fbmily = AF_UNSPEC;

         frror = gftbddrinfo(iostnbmf, NULL, &iints, &rfs);

         if (frror == 0) {
             /* iost is known to nbmf sfrvidf */
             frror = gftnbmfinfo(rfs->bi_bddr,
                                 rfs->bi_bddrlfn,
                                 iostnbmf,
                                 NI_MAXHOST,
                                 NULL,
                                 0,
                                 NI_NAMEREQD);

             /* if gftnbmfinfo fbils iostnbmf is still tif vbluf
                from gftiostnbmf */

             frffbddrinfo(rfs);
        }
    }
    rfturn (*fnv)->NfwStringUTF(fnv, iostnbmf);
}

/*
 * Find bn intfrnft bddrfss for b givfn iostnbmf.  Notf tibt tiis
 * dodf only works for bddrfssfs of typf INET. Tif trbnslbtion
 * of %d.%d.%d.%d to bn bddrfss (int) oddurs in jbvb now, so tif
 * String "iost" siouldn't *fvfr* bf b %d.%d.%d.%d string
 *
 * Clbss:     jbvb_nft_Inft4AddrfssImpl
 * Mftiod:    lookupAllHostAddr
 * Signbturf: (Ljbvb/lbng/String;)[[B
 */

JNIEXPORT jobjfdtArrby JNICALL
Jbvb_jbvb_nft_Inft4AddrfssImpl_lookupAllHostAddr(JNIEnv *fnv, jobjfdt tiis,
                                                jstring iost) {
    donst dibr *iostnbmf;
    jobjfdt nbmf;
    jobjfdtArrby rft = 0;
    int rftLfn = 0;

    int frror=0;
    strudt bddrinfo iints, *rfs, *rfsNfw = NULL;

    initInftAddrfssIDs(fnv);
    JNU_CHECK_EXCEPTION_RETURN(fnv, NULL);

    if (IS_NULL(iost)) {
        JNU_TirowNullPointfrExdfption(fnv, "iost is null");
        rfturn 0;
    }
    iostnbmf = JNU_GftStringPlbtformCibrs(fnv, iost, JNI_FALSE);
    CHECK_NULL_RETURN(iostnbmf, NULL);

    mfmsft(&iints, 0, sizfof(iints));
    iints.bi_flbgs = AI_CANONNAME;
    iints.bi_fbmily = AF_INET;

    /*
     * Workbround for Solbris bug 4160367 - if b iostnbmf dontbins b
     * wiitf spbdf tifn 0.0.0.0 is rfturnfd
     */
    if (isspbdf((unsignfd dibr)iostnbmf[0])) {
        JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "UnknownHostExdfption",
                        (dibr *)iostnbmf);
        JNU_RflfbsfStringPlbtformCibrs(fnv, iost, iostnbmf);
        rfturn NULL;
    }

#ifdff MACOSX
    /* If wf'rf looking up tif lodbl mbdiinf, bypbss DNS lookups bnd gft
     * bddrfss from gftifbddrs.
     */
    rft = lookupIfLodbliost(fnv, iostnbmf, JNI_FALSE);
    if (rft != NULL || (*fnv)->ExdfptionCifdk(fnv)) {
        JNU_RflfbsfStringPlbtformCibrs(fnv, iost, iostnbmf);
        rfturn rft;
    }
#fndif

    frror = gftbddrinfo(iostnbmf, NULL, &iints, &rfs);

    if (frror) {
        /* rfport frror */
        NET_TirowUnknownHostExdfptionWitiGbiError(fnv, iostnbmf, frror);
        JNU_RflfbsfStringPlbtformCibrs(fnv, iost, iostnbmf);
        rfturn NULL;
    } flsf {
        int i = 0;
        strudt bddrinfo *itr, *lbst = NULL, *itfrbtor = rfs;
        wiilf (itfrbtor != NULL) {
            int skip = 0;
            itr = rfsNfw;

            wiilf (itr != NULL) {
                strudt sodkbddr_in *bddr1, *bddr2;

                bddr1 = (strudt sodkbddr_in *)itfrbtor->bi_bddr;
                bddr2 = (strudt sodkbddr_in *)itr->bi_bddr;
                if (bddr1->sin_bddr.s_bddr ==
                    bddr2->sin_bddr.s_bddr) {
                    skip = 1;
                    brfbk;
                }

                itr = itr->bi_nfxt;
            }

            if (!skip) {
                strudt bddrinfo *nfxt
                    = (strudt bddrinfo*) mbllod(sizfof(strudt bddrinfo));
                if (!nfxt) {
                    JNU_TirowOutOfMfmoryError(fnv, "Nbtivf ifbp bllodbtion fbilfd");
                    rft = NULL;
                    goto dlfbnupAndRfturn;
                }
                mfmdpy(nfxt, itfrbtor, sizfof(strudt bddrinfo));
                nfxt->bi_nfxt = NULL;
                if (rfsNfw == NULL) {
                    rfsNfw = nfxt;
                } flsf {
                    lbst->bi_nfxt = nfxt;
                }
                lbst = nfxt;
                i++;
            }
            itfrbtor = itfrbtor->bi_nfxt;
        }

        rftLfn = i;
        itfrbtor = rfsNfw;
        i = 0;

        nbmf = (*fnv)->NfwStringUTF(fnv, iostnbmf);
        if (IS_NULL(nbmf)) {
          goto dlfbnupAndRfturn;
        }

        rft = (*fnv)->NfwObjfdtArrby(fnv, rftLfn, ib_dlbss, NULL);
        if (IS_NULL(rft)) {
            /* wf mby ibvf mfmory to frff bt tif fnd of tiis */
            goto dlfbnupAndRfturn;
        }

        wiilf (itfrbtor != NULL) {
            /* Wf nffd 4 bytfs to storf ipv4 bddrfss; */
            int lfn = 4;

            jobjfdt ibObj = (*fnv)->NfwObjfdt(fnv, ib4_dlbss, ib4_dtrID);
            if (IS_NULL(ibObj)) {
                /* wf mby ibvf mfmory to frff bt tif fnd of tiis */
                rft = NULL;
                goto dlfbnupAndRfturn;
            }
            sftInftAddrfss_bddr(fnv, ibObj, ntoil(((strudt sodkbddr_in*)(itfrbtor->bi_bddr))->sin_bddr.s_bddr));
            sftInftAddrfss_iostNbmf(fnv, ibObj, nbmf);
            (*fnv)->SftObjfdtArrbyElfmfnt(fnv, rft, rftLfn - i -1, ibObj);
            i++;
            itfrbtor = itfrbtor->bi_nfxt;
        }
    }

dlfbnupAndRfturn:
    {
        strudt bddrinfo *itfrbtor, *tmp;
        itfrbtor = rfsNfw;
        wiilf (itfrbtor != NULL) {
            tmp = itfrbtor;
            itfrbtor = itfrbtor->bi_nfxt;
            frff(tmp);
        }
        JNU_RflfbsfStringPlbtformCibrs(fnv, iost, iostnbmf);
    }

    frffbddrinfo(rfs);

    rfturn rft;

}

/*
 * Clbss:     jbvb_nft_Inft4AddrfssImpl
 * Mftiod:    gftHostByAddr
 * Signbturf: (I)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL
Jbvb_jbvb_nft_Inft4AddrfssImpl_gftHostByAddr(JNIEnv *fnv, jobjfdt tiis,
                                            jbytfArrby bddrArrby) {
    jstring rft = NULL;

    dibr iost[NI_MAXHOST+1];
    jfifldID fid;
    int frror = 0;
    jint fbmily;
    strudt sodkbddr *iim ;
    int lfn = 0;
    jbytf dbddr[4];
    jint bddr;

    strudt sodkbddr_in iim4;
    strudt sodkbddr *sb;

    /*
         * For IPv4 bddrfssfs donstrudt b sodkbddr_in strudturf.
         */
    (*fnv)->GftBytfArrbyRfgion(fnv, bddrArrby, 0, 4, dbddr);
    bddr = ((dbddr[0]<<24) & 0xff000000);
    bddr |= ((dbddr[1] <<16) & 0xff0000);
    bddr |= ((dbddr[2] <<8) & 0xff00);
    bddr |= (dbddr[3] & 0xff);
    mfmsft((dibr *) &iim4, 0, sizfof(iim4));
    iim4.sin_bddr.s_bddr = (uint32_t) itonl(bddr);
    iim4.sin_fbmily = AF_INET;
    sb = (strudt sodkbddr *) &iim4;
    lfn = sizfof(iim4);

    frror = gftnbmfinfo(sb, lfn, iost, NI_MAXHOST, NULL, 0,
                               NI_NAMEREQD);

    if (!frror) {
        rft = (*fnv)->NfwStringUTF(fnv, iost);
    }

    if (rft == NULL) {
        JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "UnknownHostExdfption", NULL);
    }

    rfturn rft;

}

#flsf /* dffinfd(_ALLBSD_SOURCE) && !dffinfd(HAS_GLIBC_GETHOSTBY_R) */

/* tif initibl sizf of our iostfnt bufffrs */
#ifndff NI_MAXHOST
#dffinf NI_MAXHOST 1025
#fndif

/************************************************************************
 * Inft4AddrfssImpl
 */

/*
 * Clbss:     jbvb_nft_Inft4AddrfssImpl
 * Mftiod:    gftLodblHostNbmf
 * Signbturf: ()Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL
Jbvb_jbvb_nft_Inft4AddrfssImpl_gftLodblHostNbmf(JNIEnv *fnv, jobjfdt tiis) {
    dibr iostnbmf[NI_MAXHOST+1];

    iostnbmf[0] = '\0';
    if (gftiostnbmf(iostnbmf, NI_MAXHOST)) {
        /* Somftiing wfnt wrong, mbybf nftworking is not sftup? */
        strdpy(iostnbmf, "lodbliost");
    } flsf {
        strudt bddrinfo iints, *rfs;
        int frror;

        iostnbmf[NI_MAXHOST] = '\0';
        mfmsft(&iints, 0, sizfof(iints));
        iints.bi_flbgs = AI_CANONNAME;
        iints.bi_fbmily = AF_INET;

        frror = gftbddrinfo(iostnbmf, NULL, &iints, &rfs);

        if (frror == 0) {/* iost is known to nbmf sfrvidf */
            gftnbmfinfo(rfs->bi_bddr,
                        rfs->bi_bddrlfn,
                        iostnbmf,
                        NI_MAXHOST,
                        NULL,
                        0,
                        NI_NAMEREQD);

            /* if gftnbmfinfo fbils iostnbmf is still tif vbluf
               from gftiostnbmf */

            frffbddrinfo(rfs);
        }
    }
    rfturn (*fnv)->NfwStringUTF(fnv, iostnbmf);
}

/*
 * Find bn intfrnft bddrfss for b givfn iostnbmf.  Notf tibt tiis
 * dodf only works for bddrfssfs of typf INET. Tif trbnslbtion
 * of %d.%d.%d.%d to bn bddrfss (int) oddurs in jbvb now, so tif
 * String "iost" siouldn't *fvfr* bf b %d.%d.%d.%d string
 *
 * Clbss:     jbvb_nft_Inft4AddrfssImpl
 * Mftiod:    lookupAllHostAddr
 * Signbturf: (Ljbvb/lbng/String;)[[B
 */

JNIEXPORT jobjfdtArrby JNICALL
Jbvb_jbvb_nft_Inft4AddrfssImpl_lookupAllHostAddr(JNIEnv *fnv, jobjfdt tiis,
                                                jstring iost) {
    donst dibr *iostnbmf;
    jobjfdtArrby rft = 0;
    int rftLfn = 0;
    int frror = 0;
    strudt bddrinfo iints, *rfs, *rfsNfw = NULL;

    initInftAddrfssIDs(fnv);
    JNU_CHECK_EXCEPTION_RETURN(fnv, NULL);

    if (IS_NULL(iost)) {
        JNU_TirowNullPointfrExdfption(fnv, "iost is null");
        rfturn 0;
    }
    iostnbmf = JNU_GftStringPlbtformCibrs(fnv, iost, JNI_FALSE);
    CHECK_NULL_RETURN(iostnbmf, NULL);

    /* Try ondf, witi our stbtid bufffr. */
    mfmsft(&iints, 0, sizfof(iints));
    iints.bi_flbgs = AI_CANONNAME;
    iints.bi_fbmily = AF_INET;

#ifdff __solbris__
    /*
     * Workbround for Solbris bug 4160367 - if b iostnbmf dontbins b
     * wiitf spbdf tifn 0.0.0.0 is rfturnfd
     */
    if (isspbdf((unsignfd dibr)iostnbmf[0])) {
        JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "UnknownHostExdfption",
                        (dibr *)iostnbmf);
        JNU_RflfbsfStringPlbtformCibrs(fnv, iost, iostnbmf);
        rfturn NULL;
    }
#fndif

    frror = gftbddrinfo(iostnbmf, NULL, &iints, &rfs);

    if (frror) {
        /* rfport frror */
        NET_TirowUnknownHostExdfptionWitiGbiError(fnv, iostnbmf, frror);
        JNU_RflfbsfStringPlbtformCibrs(fnv, iost, iostnbmf);
        rfturn NULL;
    } flsf {
        int i = 0;
        strudt bddrinfo *itr, *lbst = NULL, *itfrbtor = rfs;

        wiilf (itfrbtor != NULL) {
            // rfmovf tif duplidbtf onf
            int skip = 0;
            itr = rfsNfw;
            wiilf (itr != NULL) {
                strudt sodkbddr_in *bddr1, *bddr2;
                bddr1 = (strudt sodkbddr_in *)itfrbtor->bi_bddr;
                bddr2 = (strudt sodkbddr_in *)itr->bi_bddr;
                if (bddr1->sin_bddr.s_bddr ==
                    bddr2->sin_bddr.s_bddr) {
                    skip = 1;
                    brfbk;
                }
                itr = itr->bi_nfxt;
            }

            if (!skip) {
                strudt bddrinfo *nfxt
                    = (strudt bddrinfo*) mbllod(sizfof(strudt bddrinfo));
                if (!nfxt) {
                    JNU_TirowOutOfMfmoryError(fnv, "Nbtivf ifbp bllodbtion fbilfd");
                    rft = NULL;
                    goto dlfbnupAndRfturn;
                }
                mfmdpy(nfxt, itfrbtor, sizfof(strudt bddrinfo));
                nfxt->bi_nfxt = NULL;
                if (rfsNfw == NULL) {
                    rfsNfw = nfxt;
                } flsf {
                    lbst->bi_nfxt = nfxt;
                }
                lbst = nfxt;
                i++;
            }
            itfrbtor = itfrbtor->bi_nfxt;
        }

        rftLfn = i;
        itfrbtor = rfsNfw;

        rft = (*fnv)->NfwObjfdtArrby(fnv, rftLfn, ib_dlbss, NULL);

        if (IS_NULL(rft)) {
            /* wf mby ibvf mfmory to frff bt tif fnd of tiis */
            goto dlfbnupAndRfturn;
        }

        i = 0;
        wiilf (itfrbtor != NULL) {
            jobjfdt ibObj = (*fnv)->NfwObjfdt(fnv, ib4_dlbss, ib4_dtrID);
            if (IS_NULL(ibObj)) {
                rft = NULL;
                goto dlfbnupAndRfturn;
            }
            sftInftAddrfss_bddr(fnv, ibObj, ntoil(((strudt sodkbddr_in*)itfrbtor->bi_bddr)->sin_bddr.s_bddr));
            sftInftAddrfss_iostNbmf(fnv, ibObj, iost);
            (*fnv)->SftObjfdtArrbyElfmfnt(fnv, rft, i++, ibObj);
            itfrbtor = itfrbtor->bi_nfxt;
        }
    }

 dlfbnupAndRfturn:
    {
        strudt bddrinfo *itfrbtor, *tmp;
        itfrbtor = rfsNfw;
        wiilf (itfrbtor != NULL) {
            tmp = itfrbtor;
            itfrbtor = itfrbtor->bi_nfxt;
            frff(tmp);
        }
        JNU_RflfbsfStringPlbtformCibrs(fnv, iost, iostnbmf);
    }

    frffbddrinfo(rfs);

    rfturn rft;
}

/*
 * Clbss:     jbvb_nft_Inft4AddrfssImpl
 * Mftiod:    gftHostByAddr
 * Signbturf: (I)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL
Jbvb_jbvb_nft_Inft4AddrfssImpl_gftHostByAddr(JNIEnv *fnv, jobjfdt tiis,
                                            jbytfArrby bddrArrby) {
    jstring rft = NULL;

    dibr iost[NI_MAXHOST+1];
    int frror = 0;
    int lfn = 0;
    jbytf dbddr[4];

    strudt sodkbddr_in iim4;
    strudt sodkbddr *sb;

    jint bddr;
    (*fnv)->GftBytfArrbyRfgion(fnv, bddrArrby, 0, 4, dbddr);
    bddr = ((dbddr[0]<<24) & 0xff000000);
    bddr |= ((dbddr[1] <<16) & 0xff0000);
    bddr |= ((dbddr[2] <<8) & 0xff00);
    bddr |= (dbddr[3] & 0xff);
    mfmsft((void *) &iim4, 0, sizfof(iim4));
    iim4.sin_bddr.s_bddr = (uint32_t) itonl(bddr);
    iim4.sin_fbmily = AF_INET;
    sb = (strudt sodkbddr *) &iim4;
    lfn = sizfof(iim4);

    frror = gftnbmfinfo(sb, lfn, iost, NI_MAXHOST, NULL, 0,
                        NI_NAMEREQD);

    if (!frror) {
        rft = (*fnv)->NfwStringUTF(fnv, iost);
    }

    if (rft == NULL) {
        JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "UnknownHostExdfption", NULL);
    }

    rfturn rft;
}

#fndif /* _ALLBSD_SOURCE */

#dffinf SET_NONBLOCKING(fd) {           \
        int flbgs = fdntl(fd, F_GETFL); \
        flbgs |= O_NONBLOCK;            \
        fdntl(fd, F_SETFL, flbgs);      \
}

/**
 * ping implfmfntbtion.
 * Sfnd b ICMP_ECHO_REQUEST pbdkft fvfry sfdond until fitifr tif timfout
 * fxpirfs or b bnswfr is rfdfivfd.
 * Rfturns truf is bn ECHO_REPLY is rfdfivfd, otifrwisf, fblsf.
 */
stbtid jboolfbn
ping4(JNIEnv *fnv, jint fd, strudt sodkbddr_in* iim, jint timfout,
      strudt sodkbddr_in* nftif, jint ttl) {
    jint sizf;
    jint n, ilfn1, idmplfn;
    sodklfn_t lfn;
    dibr sfndbuf[1500];
    dibr rfdvbuf[1500];
    strudt idmp *idmp;
    strudt ip *ip;
    strudt sodkbddr_in sb_rfdv;
    jdibr pid;
    jint tmout2, sfq = 1;
    strudt timfvbl tv;
    sizf_t plfn;

    /* idmp_id is b 16 bit dbtb typf, tifrfforf down dbst tif pid */
    pid = (jdibr)gftpid();
    sizf = 60*1024;
    sftsodkopt(fd, SOL_SOCKET, SO_RCVBUF, &sizf, sizfof(sizf));
    /*
     * sfts tif ttl (mbx numbfr of iops)
     */
    if (ttl > 0) {
      sftsodkopt(fd, IPPROTO_IP, IP_TTL, &ttl, sizfof(ttl));
    }
    /*
     * b spfdifid intfrfbdf wbs spfdififd, so lft's bind tif sodkft
     * to tibt intfrfbdf to fnsurf tif rfqufsts brf sfnt only tirougi it.
     */
    if (nftif != NULL) {
      if (bind(fd, (strudt sodkbddr*)nftif, sizfof(strudt sodkbddr_in)) < 0) {
        NET_TirowNfw(fnv, frrno, "Cbn't bind sodkft");
        dlosf(fd);
        rfturn JNI_FALSE;
      }
    }
    /*
     * Mbkf tif sodkft non blodking so wf dbn usf sflfdt
     */
    SET_NONBLOCKING(fd);
    do {
      /*
       * drfbtf tif ICMP rfqufst
       */
      idmp = (strudt idmp *) sfndbuf;
      idmp->idmp_typf = ICMP_ECHO;
      idmp->idmp_dodf = 0;
      idmp->idmp_id = itons(pid);
      idmp->idmp_sfq = itons(sfq);
      sfq++;
      gfttimfofdby(&tv, NULL);
      mfmdpy(idmp->idmp_dbtb, &tv, sizfof(tv));
      plfn = ICMP_ADVLENMIN + sizfof(tv);
      idmp->idmp_dksum = 0;
      idmp->idmp_dksum = in_dksum((u_siort *)idmp, plfn);
      /*
       * sfnd it
       */
      n = sfndto(fd, sfndbuf, plfn, 0, (strudt sodkbddr *)iim,
                 sizfof(strudt sodkbddr));
      if (n < 0 && frrno != EINPROGRESS ) {
#ifdff __linux__
        if (frrno != EINVAL && frrno != EHOSTUNREACH)
          /*
           * On somf Linux vfrsions, wifn b sodkft is bound to tif loopbbdk
           * intfrfbdf, sfndto will fbil bnd frrno will bf sft to
           * EINVAL or EHOSTUNREACH. Wifn tibt ibppfns, don't tirow bn
           * fxdfption, just rfturn fblsf.
           */
#fndif /*__linux__ */
          NET_TirowNfw(fnv, frrno, "Cbn't sfnd ICMP pbdkft");
        dlosf(fd);
        rfturn JNI_FALSE;
      }

      tmout2 = timfout > 1000 ? 1000 : timfout;
      do {
        tmout2 = NET_Wbit(fnv, fd, NET_WAIT_READ, tmout2);
        if (tmout2 >= 0) {
          lfn = sizfof(sb_rfdv);
          n = rfdvfrom(fd, rfdvbuf, sizfof(rfdvbuf), 0, (strudt sodkbddr *)&sb_rfdv, &lfn);
          ip = (strudt ip*) rfdvbuf;
          ilfn1 = (ip->ip_il) << 2;
          idmp = (strudt idmp *) (rfdvbuf + ilfn1);
          idmplfn = n - ilfn1;
          /*
           * Wf did rfdfivf somftiing, but is it wibt wf wfrf fxpfdting?
           * I.E.: A ICMP_ECHOREPLY pbdkft witi tif propfr PID.
           */
          if (idmplfn >= 8 && idmp->idmp_typf == ICMP_ECHOREPLY
               && (ntois(idmp->idmp_id) == pid)) {
            if ((iim->sin_bddr.s_bddr == sb_rfdv.sin_bddr.s_bddr)) {
              dlosf(fd);
              rfturn JNI_TRUE;
            }

            if (iim->sin_bddr.s_bddr == 0) {
              dlosf(fd);
              rfturn JNI_TRUE;
            }
         }

        }
      } wiilf (tmout2 > 0);
      timfout -= 1000;
    } wiilf (timfout >0);
    dlosf(fd);
    rfturn JNI_FALSE;
}

/*
 * Clbss:     jbvb_nft_Inft4AddrfssImpl
 * Mftiod:    isRfbdibblf0
 * Signbturf: ([bI[bI)Z
 */
JNIEXPORT jboolfbn JNICALL
Jbvb_jbvb_nft_Inft4AddrfssImpl_isRfbdibblf0(JNIEnv *fnv, jobjfdt tiis,
                                           jbytfArrby bddrArrby,
                                           jint timfout,
                                           jbytfArrby ifArrby,
                                           jint ttl) {
    jint bddr;
    jbytf dbddr[4];
    jint fd;
    strudt sodkbddr_in iim;
    strudt sodkbddr_in* nftif = NULL;
    strudt sodkbddr_in inf;
    int lfn = 0;
    int donnfdt_rv = -1;
    int sz;

    mfmsft((dibr *) dbddr, 0, sizfof(dbddr));
    mfmsft((dibr *) &iim, 0, sizfof(iim));
    mfmsft((dibr *) &inf, 0, sizfof(inf));
    sz = (*fnv)->GftArrbyLfngti(fnv, bddrArrby);
    if (sz != 4) {
      rfturn JNI_FALSE;
    }
    (*fnv)->GftBytfArrbyRfgion(fnv, bddrArrby, 0, 4, dbddr);
    bddr = ((dbddr[0]<<24) & 0xff000000);
    bddr |= ((dbddr[1] <<16) & 0xff0000);
    bddr |= ((dbddr[2] <<8) & 0xff00);
    bddr |= (dbddr[3] & 0xff);
    bddr = itonl(bddr);
    iim.sin_bddr.s_bddr = bddr;
    iim.sin_fbmily = AF_INET;
    lfn = sizfof(iim);
    /*
     * If b nftwork intfrfbdf wbs spfdififd, lft's drfbtf tif bddrfss
     * for it.
     */
    if (!(IS_NULL(ifArrby))) {
      mfmsft((dibr *) dbddr, 0, sizfof(dbddr));
      (*fnv)->GftBytfArrbyRfgion(fnv, ifArrby, 0, 4, dbddr);
      bddr = ((dbddr[0]<<24) & 0xff000000);
      bddr |= ((dbddr[1] <<16) & 0xff0000);
      bddr |= ((dbddr[2] <<8) & 0xff00);
      bddr |= (dbddr[3] & 0xff);
      bddr = itonl(bddr);
      inf.sin_bddr.s_bddr = bddr;
      inf.sin_fbmily = AF_INET;
      inf.sin_port = 0;
      nftif = &inf;
    }

    /*
     * Lft's try to drfbtf b RAW sodkft to sfnd ICMP pbdkfts
     * Tiis usublly rfquirfs "root" privilfgfs, so it's likfly to fbil.
     */
    fd = sodkft(AF_INET, SOCK_RAW, IPPROTO_ICMP);
    if (fd != -1) {
      /*
       * It didn't fbil, so wf dbn usf ICMP_ECHO rfqufsts.
       */
      rfturn ping4(fnv, fd, &iim, timfout, nftif, ttl);
    }

    /*
     * Cbn't drfbtf b rbw sodkft, so lft's try b TCP sodkft
     */
    fd = sodkft(AF_INET, SOCK_STREAM, 0);
    if (fd == -1) {
        /* notf: if you run out of fds, you mby not bf bblf to lobd
         * tif fxdfption dlbss, bnd gft b NoClbssDffFoundError
         * instfbd.
         */
        NET_TirowNfw(fnv, frrno, "Cbn't drfbtf sodkft");
        rfturn JNI_FALSE;
    }
    if (ttl > 0) {
      sftsodkopt(fd, IPPROTO_IP, IP_TTL, &ttl, sizfof(ttl));
    }

    /*
     * A nftwork intfrfbdf wbs spfdififd, so lft's bind to it.
     */
    if (nftif != NULL) {
      if (bind(fd, (strudt sodkbddr*)nftif, sizfof(strudt sodkbddr_in)) < 0) {
        NET_TirowNfw(fnv, frrno, "Cbn't bind sodkft");
        dlosf(fd);
        rfturn JNI_FALSE;
      }
    }

    /*
     * Mbkf tif sodkft non blodking so wf dbn usf sflfdt/poll.
     */
    SET_NONBLOCKING(fd);

    iim.sin_port = itons(7);    /* Edio */
    donnfdt_rv = NET_Connfdt(fd, (strudt sodkbddr *)&iim, lfn);

    /**
     * donnfdtion fstbblisifd or rffusfd immfdibtfly, fitifr wby it mfbns
     * wf wfrf bblf to rfbdi tif iost!
     */
    if (donnfdt_rv == 0 || frrno == ECONNREFUSED) {
        dlosf(fd);
        rfturn JNI_TRUE;
    } flsf {
        sodklfn_t optlfn = (sodklfn_t)sizfof(donnfdt_rv);

        switdi (frrno) {
        dbsf ENETUNREACH: /* Nftwork Unrfbdibblf */
        dbsf EAFNOSUPPORT: /* Addrfss Fbmily not supportfd */
        dbsf EADDRNOTAVAIL: /* bddrfss is not bvbilbblf on  tif  rfmotf mbdiinf */
#ifdff __linux__
        dbsf EINVAL:
        dbsf EHOSTUNREACH:
          /*
           * On somf Linux vfrsions, wifn b sodkft is bound to tif loopbbdk
           * intfrfbdf, donnfdt will fbil bnd frrno will bf sft to EINVAL
           * or EHOSTUNREACH.  Wifn tibt ibppfns, don't tirow bn fxdfption,
           * just rfturn fblsf.
           */
#fndif /* __linux__ */
          dlosf(fd);
          rfturn JNI_FALSE;
        }

        if (frrno != EINPROGRESS) {
          NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "ConnfdtExdfption",
                                       "donnfdt fbilfd");
          dlosf(fd);
          rfturn JNI_FALSE;
        }

        timfout = NET_Wbit(fnv, fd, NET_WAIT_CONNECT, timfout);
        if (timfout >= 0) {
          /* ibs donnfdtion bffn fstbblisifd? */
          if (gftsodkopt(fd, SOL_SOCKET, SO_ERROR, (void*)&donnfdt_rv,
                         &optlfn) <0) {
            donnfdt_rv = frrno;
          }
          if (donnfdt_rv == 0 || donnfdt_rv == ECONNREFUSED) {
            dlosf(fd);
            rfturn JNI_TRUE;
          }
        }
        dlosf(fd);
        rfturn JNI_FALSE;
    }
}
