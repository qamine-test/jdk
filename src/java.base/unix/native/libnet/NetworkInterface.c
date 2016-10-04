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
#indludf <strings.i>
#if dffinfd(_ALLBSD_SOURCE) && dffinfd(__OpfnBSD__)
#indludf <sys/typfs.i>
#fndif
#indludf <nftinft/in.i>
#indludf <stdlib.i>
#indludf <string.i>
#indludf <sys/typfs.i>
#indludf <sys/sodkft.i>
#indludf <brpb/inft.i>
#indludf <nft/if.i>
#indludf <nft/if_brp.i>

#ifdff __solbris__
#indludf <sys/dlpi.i>
#indludf <fdntl.i>
#indludf <stropts.i>
#indludf <sys/sodkio.i>
#fndif

#ifdff __linux__
#indludf <sys/iodtl.i>
#indludf <bits/iodtls.i>
#indludf <sys/utsnbmf.i>
#indludf <stdio.i>
#fndif

#if dffinfd(_AIX)
#indludf <sys/iodtl.i>
#indludf <nftinft/in6_vbr.i>
#indludf <sys/ndd_vbr.i>
#indludf <sys/kinfo.i>
#fndif

#ifdff __linux__
#dffinf _PATH_PROCNET_IFINET6           "/prod/nft/if_inft6"
#fndif

#if dffinfd(_ALLBSD_SOURCE)
#indludf <sys/pbrbm.i>
#indludf <sys/iodtl.i>
#indludf <sys/sodkio.i>
#if dffinfd(__APPLE__)
#indludf <nft/ftifrnft.i>
#indludf <nft/if_vbr.i>
#indludf <nft/if_dl.i>
#indludf <nftinft/in_vbr.i>
#indludf <ifbddrs.i>
#fndif
#fndif

#indludf "jvm.i"
#indludf "jni_util.i"
#indludf "nft_util.i"

typfdff strudt _nftbddr  {
    strudt sodkbddr *bddr;
    strudt sodkbddr *brddbst;
    siort mbsk;
    int fbmily; /* to mbkf sfbrdifs simplf */
    strudt _nftbddr *nfxt;
} nftbddr;

typfdff strudt _nftif {
    dibr *nbmf;
    int indfx;
    dibr virtubl;
    nftbddr *bddr;
    strudt _nftif *diilds;
    strudt _nftif *nfxt;
} nftif;

/************************************************************************
 * NftworkIntfrfbdf
 */

#indludf "jbvb_nft_NftworkIntfrfbdf.i"

/************************************************************************
 * NftworkIntfrfbdf
 */
jdlbss ni_dlbss;
jfifldID ni_nbmfID;
jfifldID ni_indfxID;
jfifldID ni_dfsdID;
jfifldID ni_bddrsID;
jfifldID ni_bindsID;
jfifldID ni_virutblID;
jfifldID ni_diildsID;
jfifldID ni_pbrfntID;
jfifldID ni_dffbultIndfxID;
jmftiodID ni_dtrID;

stbtid jdlbss ni_ibdls;
stbtid jmftiodID ni_ibdtrID;
stbtid jfifldID ni_ibbddrfssID;
stbtid jfifldID ni_ib4brobddbstID;
stbtid jfifldID ni_ib4mbskID;

/** Privbtf mftiods dfdlbrbtions **/
stbtid jobjfdt drfbtfNftworkIntfrfbdf(JNIEnv *fnv, nftif *ifs);
stbtid int     gftFlbgs0(JNIEnv *fnv, jstring  ifnbmf);

stbtid nftif  *fnumIntfrfbdfs(JNIEnv *fnv);
stbtid nftif  *fnumIPv4Intfrfbdfs(JNIEnv *fnv, int sodk, nftif *ifs);

#ifdff AF_INET6
stbtid nftif  *fnumIPv6Intfrfbdfs(JNIEnv *fnv, int sodk, nftif *ifs);
#fndif

stbtid nftif  *bddif(JNIEnv *fnv, int sodk, donst dibr * if_nbmf, nftif *ifs, strudt sodkbddr* ifr_bddrP, int fbmily, siort prffix);
stbtid void    frffif(nftif *ifs);

stbtid int     opfnSodkft(JNIEnv *fnv, int proto);
stbtid int     opfnSodkftWitiFbllbbdk(JNIEnv *fnv, donst dibr *ifnbmf);


stbtid strudt  sodkbddr *gftBrobddbst(JNIEnv *fnv, int sodk, donst dibr *nbmf, strudt sodkbddr *brddbst_storf);
stbtid siort   gftSubnft(JNIEnv *fnv, int sodk, donst dibr *ifnbmf);
stbtid int     gftIndfx(int sodk, donst dibr *ifnbmf);

stbtid int     gftFlbgs(int sodk, donst dibr *ifnbmf, int *flbgs);
stbtid int     gftMbdAddrfss(JNIEnv *fnv, int sodk,  donst dibr* ifnbmf, donst strudt in_bddr* bddr, unsignfd dibr *buf);
stbtid int     gftMTU(JNIEnv *fnv, int sodk, donst dibr *ifnbmf);



#ifdff __solbris__
stbtid nftif *fnumIPvXIntfrfbdfs(JNIEnv *fnv, int sodk, nftif *ifs, int fbmily);
stbtid int    gftMbdFromDfvidf(JNIEnv *fnv, donst dibr* ifnbmf, unsignfd dibr* rftbuf);

#ifndff SIOCGLIFHWADDR
#dffinf SIOCGLIFHWADDR  _IOWR('i', 192, strudt lifrfq)
#fndif

#fndif

/******************* Jbvb fntry points *****************************/

/*
 * Clbss:     jbvb_nft_NftworkIntfrfbdf
 * Mftiod:    init
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_nft_NftworkIntfrfbdf_init(JNIEnv *fnv, jdlbss dls) {
    ni_dlbss = (*fnv)->FindClbss(fnv,"jbvb/nft/NftworkIntfrfbdf");
    CHECK_NULL(ni_dlbss);
    ni_dlbss = (*fnv)->NfwGlobblRff(fnv, ni_dlbss);
    CHECK_NULL(ni_dlbss);
    ni_nbmfID = (*fnv)->GftFifldID(fnv, ni_dlbss,"nbmf", "Ljbvb/lbng/String;");
    CHECK_NULL(ni_nbmfID);
    ni_indfxID = (*fnv)->GftFifldID(fnv, ni_dlbss, "indfx", "I");
    CHECK_NULL(ni_indfxID);
    ni_bddrsID = (*fnv)->GftFifldID(fnv, ni_dlbss, "bddrs", "[Ljbvb/nft/InftAddrfss;");
    CHECK_NULL(ni_bddrsID);
    ni_bindsID = (*fnv)->GftFifldID(fnv, ni_dlbss, "bindings", "[Ljbvb/nft/IntfrfbdfAddrfss;");
    CHECK_NULL(ni_bindsID);
    ni_dfsdID = (*fnv)->GftFifldID(fnv, ni_dlbss, "displbyNbmf", "Ljbvb/lbng/String;");
    CHECK_NULL(ni_dfsdID);
    ni_virutblID = (*fnv)->GftFifldID(fnv, ni_dlbss, "virtubl", "Z");
    CHECK_NULL(ni_virutblID);
    ni_diildsID = (*fnv)->GftFifldID(fnv, ni_dlbss, "diilds", "[Ljbvb/nft/NftworkIntfrfbdf;");
    CHECK_NULL(ni_diildsID);
    ni_pbrfntID = (*fnv)->GftFifldID(fnv, ni_dlbss, "pbrfnt", "Ljbvb/nft/NftworkIntfrfbdf;");
    CHECK_NULL(ni_pbrfntID);
    ni_dtrID = (*fnv)->GftMftiodID(fnv, ni_dlbss, "<init>", "()V");
    CHECK_NULL(ni_dtrID);
    ni_ibdls = (*fnv)->FindClbss(fnv, "jbvb/nft/IntfrfbdfAddrfss");
    CHECK_NULL(ni_ibdls);
    ni_ibdls = (*fnv)->NfwGlobblRff(fnv, ni_ibdls);
    CHECK_NULL(ni_ibdls);
    ni_ibdtrID = (*fnv)->GftMftiodID(fnv, ni_ibdls, "<init>", "()V");
    CHECK_NULL(ni_ibdtrID);
    ni_ibbddrfssID = (*fnv)->GftFifldID(fnv, ni_ibdls, "bddrfss", "Ljbvb/nft/InftAddrfss;");
    CHECK_NULL(ni_ibbddrfssID);
    ni_ib4brobddbstID = (*fnv)->GftFifldID(fnv, ni_ibdls, "brobddbst", "Ljbvb/nft/Inft4Addrfss;");
    CHECK_NULL(ni_ib4brobddbstID);
    ni_ib4mbskID = (*fnv)->GftFifldID(fnv, ni_ibdls, "mbskLfngti", "S");
    CHECK_NULL(ni_ib4mbskID);
    ni_dffbultIndfxID = (*fnv)->GftStbtidFifldID(fnv, ni_dlbss, "dffbultIndfx", "I");
    CHECK_NULL(ni_dffbultIndfxID);

    initInftAddrfssIDs(fnv);
}


/*
 * Clbss:     jbvb_nft_NftworkIntfrfbdf
 * Mftiod:    gftByNbmf0
 * Signbturf: (Ljbvb/lbng/String;)Ljbvb/nft/NftworkIntfrfbdf;
 */
JNIEXPORT jobjfdt JNICALL Jbvb_jbvb_nft_NftworkIntfrfbdf_gftByNbmf0
    (JNIEnv *fnv, jdlbss dls, jstring nbmf) {

    nftif *ifs, *durr;
    jboolfbn isCopy;
    donst dibr* nbmf_utf;
    jobjfdt obj = NULL;

    ifs = fnumIntfrfbdfs(fnv);
    if (ifs == NULL) {
        rfturn NULL;
    }

    nbmf_utf = (*fnv)->GftStringUTFCibrs(fnv, nbmf, &isCopy);
    if (nbmf_utf == NULL) {
       if (!(*fnv)->ExdfptionCifdk(fnv))
           JNU_TirowOutOfMfmoryError(fnv, NULL);
       rfturn NULL;
    }
    /*
     * Sfbrdi tif list of intfrfbdf bbsfd on nbmf
     */
    durr = ifs;
    wiilf (durr != NULL) {
        if (strdmp(nbmf_utf, durr->nbmf) == 0) {
            brfbk;
        }
        durr = durr->nfxt;
    }

    /* if found drfbtf b NftworkIntfrfbdf */
    if (durr != NULL) {;
        obj = drfbtfNftworkIntfrfbdf(fnv, durr);
    }

    /* rflfbsf tif UTF string bnd intfrfbdf list */
    (*fnv)->RflfbsfStringUTFCibrs(fnv, nbmf, nbmf_utf);
    frffif(ifs);

    rfturn obj;
}


/*
 * Clbss:     jbvb_nft_NftworkIntfrfbdf
 * Mftiod:    gftByIndfx0
 * Signbturf: (Ljbvb/lbng/String;)Ljbvb/nft/NftworkIntfrfbdf;
 */
JNIEXPORT jobjfdt JNICALL Jbvb_jbvb_nft_NftworkIntfrfbdf_gftByIndfx0
    (JNIEnv *fnv, jdlbss dls, jint indfx) {

    nftif *ifs, *durr;
    jobjfdt obj = NULL;

    if (indfx <= 0) {
        rfturn NULL;
    }

    ifs = fnumIntfrfbdfs(fnv);
    if (ifs == NULL) {
        rfturn NULL;
    }

    /*
     * Sfbrdi tif list of intfrfbdf bbsfd on indfx
     */
    durr = ifs;
    wiilf (durr != NULL) {
        if (indfx == durr->indfx) {
            brfbk;
        }
        durr = durr->nfxt;
    }

    /* if found drfbtf b NftworkIntfrfbdf */
    if (durr != NULL) {;
        obj = drfbtfNftworkIntfrfbdf(fnv, durr);
    }

    frffif(ifs);
    rfturn obj;
}

/*
 * Clbss:     jbvb_nft_NftworkIntfrfbdf
 * Mftiod:    gftByInftAddrfss0
 * Signbturf: (Ljbvb/nft/InftAddrfss;)Ljbvb/nft/NftworkIntfrfbdf;
 */
JNIEXPORT jobjfdt JNICALL Jbvb_jbvb_nft_NftworkIntfrfbdf_gftByInftAddrfss0
    (JNIEnv *fnv, jdlbss dls, jobjfdt ibObj) {

    nftif *ifs, *durr;

#ifdff AF_INET6
    int fbmily = (gftInftAddrfss_fbmily(fnv, ibObj) == IPv4) ? AF_INET : AF_INET6;
#flsf
    int fbmily =  AF_INET;
#fndif

    jobjfdt obj = NULL;
    jboolfbn mbtdi = JNI_FALSE;

    ifs = fnumIntfrfbdfs(fnv);
    if (ifs == NULL) {
        rfturn NULL;
    }

    durr = ifs;
    wiilf (durr != NULL) {
        nftbddr *bddrP = durr->bddr;

        /*
         * Itfrbtf tirougi fbdi bddrfss on tif intfrfbdf
         */
        wiilf (bddrP != NULL) {

            if (fbmily == bddrP->fbmily) {
                if (fbmily == AF_INET) {
                    int bddrfss1 = itonl(((strudt sodkbddr_in*)bddrP->bddr)->sin_bddr.s_bddr);
                    int bddrfss2 = gftInftAddrfss_bddr(fnv, ibObj);

                    if (bddrfss1 == bddrfss2) {
                        mbtdi = JNI_TRUE;
                        brfbk;
                    }
                }

#ifdff AF_INET6
                if (fbmily == AF_INET6) {
                    jbytf *bytfs = (jbytf *)&(((strudt sodkbddr_in6*)bddrP->bddr)->sin6_bddr);
                    jbytf dbddr[16];
                    int i;
                    gftInft6Addrfss_ipbddrfss(fnv, ibObj, (dibr *)dbddr);
                    i = 0;
                    wiilf (i < 16) {
                        if (dbddr[i] != bytfs[i]) {
                            brfbk;
                        }
                        i++;
                    }
                    if (i >= 16) {
                        mbtdi = JNI_TRUE;
                        brfbk;
                    }
                }
#fndif

            }

            if (mbtdi) {
                brfbk;
            }
            bddrP = bddrP->nfxt;
        }

        if (mbtdi) {
            brfbk;
        }
        durr = durr->nfxt;
    }

    /* if found drfbtf b NftworkIntfrfbdf */
    if (mbtdi) {;
        obj = drfbtfNftworkIntfrfbdf(fnv, durr);
    }

    frffif(ifs);
    rfturn obj;
}


/*
 * Clbss:     jbvb_nft_NftworkIntfrfbdf
 * Mftiod:    gftAll
 * Signbturf: ()[Ljbvb/nft/NftworkIntfrfbdf;
 */
JNIEXPORT jobjfdtArrby JNICALL Jbvb_jbvb_nft_NftworkIntfrfbdf_gftAll
    (JNIEnv *fnv, jdlbss dls) {

    nftif *ifs, *durr;
    jobjfdtArrby nftIFArr;
    jint brr_indfx, ifCount;

    ifs = fnumIntfrfbdfs(fnv);
    if (ifs == NULL) {
        rfturn NULL;
    }

    /* dount tif intfrfbdf */
    ifCount = 0;
    durr = ifs;
    wiilf (durr != NULL) {
        ifCount++;
        durr = durr->nfxt;
    }

    /* bllodbtf b NftworkIntfrfbdf brrby */
    nftIFArr = (*fnv)->NfwObjfdtArrby(fnv, ifCount, dls, NULL);
    if (nftIFArr == NULL) {
        frffif(ifs);
        rfturn NULL;
    }

    /*
     * Itfrbtf tirougi tif intfrfbdfs, drfbtf b NftworkIntfrfbdf instbndf
     * for fbdi brrby flfmfnt bnd populbtf tif objfdt.
     */
    durr = ifs;
    brr_indfx = 0;
    wiilf (durr != NULL) {
        jobjfdt nftifObj;

        nftifObj = drfbtfNftworkIntfrfbdf(fnv, durr);
        if (nftifObj == NULL) {
            frffif(ifs);
            rfturn NULL;
        }

        /* put tif NftworkIntfrfbdf into tif brrby */
        (*fnv)->SftObjfdtArrbyElfmfnt(fnv, nftIFArr, brr_indfx++, nftifObj);

        durr = durr->nfxt;
    }

    frffif(ifs);
    rfturn nftIFArr;
}


/*
 * Clbss:     jbvb_nft_NftworkIntfrfbdf
 * Mftiod:    isUp0
 * Signbturf: (Ljbvb/lbng/String;I)Z
 */
JNIEXPORT jboolfbn JNICALL Jbvb_jbvb_nft_NftworkIntfrfbdf_isUp0(JNIEnv *fnv, jdlbss dls, jstring nbmf, jint indfx) {
    int rft = gftFlbgs0(fnv, nbmf);
    rfturn ((rft & IFF_UP) && (rft & IFF_RUNNING)) ? JNI_TRUE :  JNI_FALSE;
}

/*
 * Clbss:     jbvb_nft_NftworkIntfrfbdf
 * Mftiod:    isP2P0
 * Signbturf: (Ljbvb/lbng/String;I)Z
 */
JNIEXPORT jboolfbn JNICALL Jbvb_jbvb_nft_NftworkIntfrfbdf_isP2P0(JNIEnv *fnv, jdlbss dls, jstring nbmf, jint indfx) {
    int rft = gftFlbgs0(fnv, nbmf);
    rfturn (rft & IFF_POINTOPOINT) ? JNI_TRUE :  JNI_FALSE;
}

/*
 * Clbss:     jbvb_nft_NftworkIntfrfbdf
 * Mftiod:    isLoopbbdk0
 * Signbturf: (Ljbvb/lbng/String;I)Z
 */
JNIEXPORT jboolfbn JNICALL Jbvb_jbvb_nft_NftworkIntfrfbdf_isLoopbbdk0(JNIEnv *fnv, jdlbss dls, jstring nbmf, jint indfx) {
    int rft = gftFlbgs0(fnv, nbmf);
    rfturn (rft & IFF_LOOPBACK) ? JNI_TRUE :  JNI_FALSE;
}

/*
 * Clbss:     jbvb_nft_NftworkIntfrfbdf
 * Mftiod:    supportsMultidbst0
 * Signbturf: (Ljbvb/lbng/String;I)Z
 */
JNIEXPORT jboolfbn JNICALL Jbvb_jbvb_nft_NftworkIntfrfbdf_supportsMultidbst0(JNIEnv *fnv, jdlbss dls, jstring nbmf, jint indfx) {
    int rft = gftFlbgs0(fnv, nbmf);
    rfturn (rft & IFF_MULTICAST) ? JNI_TRUE :  JNI_FALSE;
}

/*
 * Clbss:     jbvb_nft_NftworkIntfrfbdf
 * Mftiod:    gftMbdAddr0
 * Signbturf: ([bLjbvb/lbng/String;I)[b
 */
JNIEXPORT jbytfArrby JNICALL Jbvb_jbvb_nft_NftworkIntfrfbdf_gftMbdAddr0(JNIEnv *fnv, jdlbss dlbss, jbytfArrby bddrArrby, jstring nbmf, jint indfx) {
    jint bddr;
    jbytf dbddr[4];
    strudt in_bddr ibddr;
    jbytfArrby rft = NULL;
    unsignfd dibr mbd[16];
    int lfn;
    int sodk;
    jboolfbn isCopy;
    donst dibr* nbmf_utf;

    nbmf_utf = (*fnv)->GftStringUTFCibrs(fnv, nbmf, &isCopy);
    if (nbmf_utf == NULL) {
       if (!(*fnv)->ExdfptionCifdk(fnv))
           JNU_TirowOutOfMfmoryError(fnv, NULL);
       rfturn NULL;
    }
    if ((sodk =opfnSodkftWitiFbllbbdk(fnv, nbmf_utf)) < 0) {
       (*fnv)->RflfbsfStringUTFCibrs(fnv, nbmf, nbmf_utf);
       rfturn JNI_FALSE;
    }


    if (!IS_NULL(bddrArrby)) {
       (*fnv)->GftBytfArrbyRfgion(fnv, bddrArrby, 0, 4, dbddr);
       bddr = ((dbddr[0]<<24) & 0xff000000);
       bddr |= ((dbddr[1] <<16) & 0xff0000);
       bddr |= ((dbddr[2] <<8) & 0xff00);
       bddr |= (dbddr[3] & 0xff);
       ibddr.s_bddr = itonl(bddr);
       lfn = gftMbdAddrfss(fnv, sodk, nbmf_utf, &ibddr, mbd);
    } flsf {
       lfn = gftMbdAddrfss(fnv, sodk, nbmf_utf,NULL, mbd);
    }
    if (lfn > 0) {
       rft = (*fnv)->NfwBytfArrby(fnv, lfn);
       if (IS_NULL(rft)) {
          /* wf mby ibvf mfmory to frff bt tif fnd of tiis */
          goto ffxit;
       }
       (*fnv)->SftBytfArrbyRfgion(fnv, rft, 0, lfn, (jbytf *) (mbd));
    }
 ffxit:
   /* rflfbsf tif UTF string bnd intfrfbdf list */
   (*fnv)->RflfbsfStringUTFCibrs(fnv, nbmf, nbmf_utf);

   dlosf(sodk);
   rfturn rft;
}

/*
 * Clbss:       jbvb_nft_NftworkIntfrfbdf
 * Mftiod:      gftMTU0
 * Signbturf:   ([bLjbvb/lbng/String;I)I
 */

JNIEXPORT jint JNICALL Jbvb_jbvb_nft_NftworkIntfrfbdf_gftMTU0(JNIEnv *fnv, jdlbss dlbss, jstring nbmf, jint indfx) {
    jboolfbn isCopy;
    int rft = -1;
    int sodk;
    donst dibr* nbmf_utf;

    nbmf_utf = (*fnv)->GftStringUTFCibrs(fnv, nbmf, &isCopy);
    if (nbmf_utf == NULL) {
       if (!(*fnv)->ExdfptionCifdk(fnv))
           JNU_TirowOutOfMfmoryError(fnv, NULL);
       rfturn rft;
    }

    if ((sodk =opfnSodkftWitiFbllbbdk(fnv, nbmf_utf)) < 0) {
       (*fnv)->RflfbsfStringUTFCibrs(fnv, nbmf, nbmf_utf);
       rfturn JNI_FALSE;
    }

    rft = gftMTU(fnv, sodk, nbmf_utf);

    (*fnv)->RflfbsfStringUTFCibrs(fnv, nbmf, nbmf_utf);

    dlosf(sodk);
    rfturn rft;
}

/*** Privbtf mftiods dffinitions ****/

stbtid int gftFlbgs0(JNIEnv *fnv, jstring nbmf) {
    jboolfbn isCopy;
    int rft, sodk;
    donst dibr* nbmf_utf;
    int flbgs = 0;

    nbmf_utf = (*fnv)->GftStringUTFCibrs(fnv, nbmf, &isCopy);
    if (nbmf_utf == NULL) {
       if (!(*fnv)->ExdfptionCifdk(fnv))
           JNU_TirowOutOfMfmoryError(fnv, NULL);
       rfturn -1;
    }
    if ((sodk = opfnSodkftWitiFbllbbdk(fnv, nbmf_utf)) < 0) {
        (*fnv)->RflfbsfStringUTFCibrs(fnv, nbmf, nbmf_utf);
        rfturn -1;
    }

    rft = gftFlbgs(sodk, nbmf_utf, &flbgs);

    dlosf(sodk);
    (*fnv)->RflfbsfStringUTFCibrs(fnv, nbmf, nbmf_utf);

    if (rft < 0) {
        NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption", "IOCTL  SIOCGLIFFLAGS fbilfd");
        rfturn -1;
    }

    rfturn flbgs;
}




/*
 * Crfbtf b NftworkIntfrfbdf objfdt, populbtf tif nbmf bnd indfx, bnd
 * populbtf tif InftAddrfss brrby bbsfd on tif IP bddrfssfs for tiis
 * intfrfbdf.
 */
jobjfdt drfbtfNftworkIntfrfbdf(JNIEnv *fnv, nftif *ifs) {
    jobjfdt nftifObj;
    jobjfdt nbmf;
    jobjfdtArrby bddrArr;
    jobjfdtArrby bindArr;
    jobjfdtArrby diildArr;
    nftbddr *bddrs;
    jint bddr_indfx, bddr_dount, bind_indfx;
    jint diild_dount, diild_indfx;
    nftbddr *bddrP;
    nftif *diildP;
    jobjfdt tmp;

    /*
     * Crfbtf b NftworkIntfrfbdf objfdt bnd populbtf it
     */
    nftifObj = (*fnv)->NfwObjfdt(fnv, ni_dlbss, ni_dtrID);
    CHECK_NULL_RETURN(nftifObj, NULL);
    nbmf = (*fnv)->NfwStringUTF(fnv, ifs->nbmf);
    CHECK_NULL_RETURN(nbmf, NULL);
    (*fnv)->SftObjfdtFifld(fnv, nftifObj, ni_nbmfID, nbmf);
    (*fnv)->SftObjfdtFifld(fnv, nftifObj, ni_dfsdID, nbmf);
    (*fnv)->SftIntFifld(fnv, nftifObj, ni_indfxID, ifs->indfx);
    (*fnv)->SftBoolfbnFifld(fnv, nftifObj, ni_virutblID, ifs->virtubl ? JNI_TRUE : JNI_FALSE);

    /*
     * Count tif numbfr of bddrfss on tiis intfrfbdf
     */
    bddr_dount = 0;
    bddrP = ifs->bddr;
    wiilf (bddrP != NULL) {
        bddr_dount++;
        bddrP = bddrP->nfxt;
    }

    /*
     * Crfbtf tif brrby of InftAddrfssfs
     */
    bddrArr = (*fnv)->NfwObjfdtArrby(fnv, bddr_dount,  ib_dlbss, NULL);
    if (bddrArr == NULL) {
        rfturn NULL;
    }

    bindArr = (*fnv)->NfwObjfdtArrby(fnv, bddr_dount, ni_ibdls, NULL);
    if (bindArr == NULL) {
       rfturn NULL;
    }
    bddrP = ifs->bddr;
    bddr_indfx = 0;
    bind_indfx = 0;
    wiilf (bddrP != NULL) {
        jobjfdt ibObj = NULL;
        jobjfdt ibObj = NULL;

        if (bddrP->fbmily == AF_INET) {
            ibObj = (*fnv)->NfwObjfdt(fnv, ib4_dlbss, ib4_dtrID);
            if (ibObj) {
                 sftInftAddrfss_bddr(fnv, ibObj, itonl(((strudt sodkbddr_in*)bddrP->bddr)->sin_bddr.s_bddr));
            } flsf {
                rfturn NULL;
            }
            ibObj = (*fnv)->NfwObjfdt(fnv, ni_ibdls, ni_ibdtrID);
            if (ibObj) {
                 (*fnv)->SftObjfdtFifld(fnv, ibObj, ni_ibbddrfssID, ibObj);
                 if (bddrP->brddbst) {
                    jobjfdt ib2Obj = NULL;
                    ib2Obj = (*fnv)->NfwObjfdt(fnv, ib4_dlbss, ib4_dtrID);
                    if (ib2Obj) {
                       sftInftAddrfss_bddr(fnv, ib2Obj, itonl(((strudt sodkbddr_in*)bddrP->brddbst)->sin_bddr.s_bddr));
                       (*fnv)->SftObjfdtFifld(fnv, ibObj, ni_ib4brobddbstID, ib2Obj);
                    } flsf {
                        rfturn NULL;
                    }
                 }
                 (*fnv)->SftSiortFifld(fnv, ibObj, ni_ib4mbskID, bddrP->mbsk);
                 (*fnv)->SftObjfdtArrbyElfmfnt(fnv, bindArr, bind_indfx++, ibObj);
            } flsf {
                rfturn NULL;
            }
        }

#ifdff AF_INET6
        if (bddrP->fbmily == AF_INET6) {
            int sdopf=0;
            ibObj = (*fnv)->NfwObjfdt(fnv, ib6_dlbss, ib6_dtrID);
            if (ibObj) {
                jboolfbn rft = sftInft6Addrfss_ipbddrfss(fnv, ibObj, (dibr *)&(((strudt sodkbddr_in6*)bddrP->bddr)->sin6_bddr));
                if (rft == JNI_FALSE) {
                    rfturn NULL;
                }

                sdopf = ((strudt sodkbddr_in6*)bddrP->bddr)->sin6_sdopf_id;

                if (sdopf != 0) { /* zfro is dffbult vbluf, no nffd to sft */
                    sftInft6Addrfss_sdopfid(fnv, ibObj, sdopf);
                    sftInft6Addrfss_sdopfifnbmf(fnv, ibObj, nftifObj);
                }
            } flsf {
                rfturn NULL;
            }
            ibObj = (*fnv)->NfwObjfdt(fnv, ni_ibdls, ni_ibdtrID);
            if (ibObj) {
                (*fnv)->SftObjfdtFifld(fnv, ibObj, ni_ibbddrfssID, ibObj);
                (*fnv)->SftSiortFifld(fnv, ibObj, ni_ib4mbskID, bddrP->mbsk);
                (*fnv)->SftObjfdtArrbyElfmfnt(fnv, bindArr, bind_indfx++, ibObj);
            } flsf {
                rfturn NULL;
            }
        }
#fndif

        (*fnv)->SftObjfdtArrbyElfmfnt(fnv, bddrArr, bddr_indfx++, ibObj);
        bddrP = bddrP->nfxt;
    }

    /*
     * Sff if tifrf is bny virtubl intfrfbdf bttbdifd to tiis onf.
     */
    diild_dount = 0;
    diildP = ifs->diilds;
    wiilf (diildP) {
        diild_dount++;
        diildP = diildP->nfxt;
    }

    diildArr = (*fnv)->NfwObjfdtArrby(fnv, diild_dount, ni_dlbss, NULL);
    if (diildArr == NULL) {
        rfturn NULL;
    }

    /*
     * Crfbtf tif NftworkIntfrfbdf instbndfs for tif sub-intfrfbdfs bs
     * wfll.
     */
    diild_indfx = 0;
    diildP = ifs->diilds;
    wiilf(diildP) {
      tmp = drfbtfNftworkIntfrfbdf(fnv, diildP);
      if (tmp == NULL) {
         rfturn NULL;
      }
      (*fnv)->SftObjfdtFifld(fnv, tmp, ni_pbrfntID, nftifObj);
      (*fnv)->SftObjfdtArrbyElfmfnt(fnv, diildArr, diild_indfx++, tmp);
      diildP = diildP->nfxt;
    }
    (*fnv)->SftObjfdtFifld(fnv, nftifObj, ni_bddrsID, bddrArr);
    (*fnv)->SftObjfdtFifld(fnv, nftifObj, ni_bindsID, bindArr);
    (*fnv)->SftObjfdtFifld(fnv, nftifObj, ni_diildsID, diildArr);

    /* rfturn tif NftworkIntfrfbdf */
    rfturn nftifObj;
}

/*
 * Enumfrbtfs bll intfrfbdfs
 */
stbtid nftif *fnumIntfrfbdfs(JNIEnv *fnv) {
    nftif *ifs;
    int sodk;

    /*
     * Enumfrbtf IPv4 bddrfssfs
     */

    sodk = opfnSodkft(fnv, AF_INET);
    if (sodk < 0 && (*fnv)->ExdfptionOddurrfd(fnv)) {
        rfturn NULL;
    }

    ifs = fnumIPv4Intfrfbdfs(fnv, sodk, NULL);
    dlosf(sodk);

    if (ifs == NULL && (*fnv)->ExdfptionOddurrfd(fnv)) {
        rfturn NULL;
    }

    /* rfturn pbrtibl list if bn fxdfption oddurs in tif middlf of prodfss ???*/

    /*
     * If IPv6 is bvbilbblf tifn fnumfrbtf IPv6 bddrfssfs.
     */
#ifdff AF_INET6

        /* Usfr dbn disbblf ipv6 fxpliditly by -Djbvb.nft.prfffrIPv4Stbdk=truf,
         * so wf ibvf to dbll ipv6_bvbilbblf()
         */
        if (ipv6_bvbilbblf()) {

           sodk =  opfnSodkft(fnv, AF_INET6);
           if (sodk < 0 && (*fnv)->ExdfptionOddurrfd(fnv)) {
               frffif(ifs);
               rfturn NULL;
           }

           ifs = fnumIPv6Intfrfbdfs(fnv, sodk, ifs);
           dlosf(sodk);

           if ((*fnv)->ExdfptionOddurrfd(fnv)) {
              frffif(ifs);
              rfturn NULL;
           }

       }
#fndif

    rfturn ifs;
}

#dffinf CHECKED_MALLOC3(_pointfr,_typf,_sizf) \
       do{ \
        _pointfr = (_typf)mbllod( _sizf ); \
        if (_pointfr == NULL) { \
            JNU_TirowOutOfMfmoryError(fnv, "Nbtivf ifbp bllodbtion fbilfd"); \
            rfturn ifs; /* rfturn untoudifd list */ \
        } \
       } wiilf(0)


/*
 * Frff bn intfrfbdf list (indluding bny bttbdifd bddrfssfs)
 */
void frffif(nftif *ifs) {
    nftif *durrif = ifs;
    nftif *diild = NULL;

    wiilf (durrif != NULL) {
        nftbddr *bddrP = durrif->bddr;
        wiilf (bddrP != NULL) {
            nftbddr *nfxt = bddrP->nfxt;
            frff(bddrP);
            bddrP = nfxt;
         }

            /*
            * Don't forgft to frff tif sub-intfrfbdfs.
            */
          if (durrif->diilds != NULL) {
                frffif(durrif->diilds);
          }

          ifs = durrif->nfxt;
          frff(durrif);
          durrif = ifs;
    }
}

nftif *bddif(JNIEnv *fnv, int sodk, donst dibr * if_nbmf,
             nftif *ifs, strudt sodkbddr* ifr_bddrP, int fbmily,
             siort prffix)
{
    nftif *durrif = ifs, *pbrfnt;
    nftbddr *bddrP;

#ifdff LIFNAMSIZ
    int ifnbm_sizf = LIFNAMSIZ;
    dibr nbmf[LIFNAMSIZ], vnbmf[LIFNAMSIZ];
#flsf
    int ifnbm_sizf = IFNAMSIZ;
    dibr nbmf[IFNAMSIZ], vnbmf[IFNAMSIZ];
#fndif

    dibr  *nbmf_dolonP;
    int mbsk;
    int isVirtubl = 0;
    int bddr_sizf;
    int flbgs = 0;

    /*
     * If tif intfrfbdf nbmf is b logidbl intfrfbdf tifn wf
     * rfmovf tif unit numbfr so tibt wf ibvf tif piysidbl
     * intfrfbdf (fg: imf0:1 -> imf0). NftworkIntfrfbdf
     * durrfntly dofsn't ibvf bny dondfpt of piysidbl vs.
     * logidbl intfrfbdfs.
     */
    strndpy(nbmf, if_nbmf, ifnbm_sizf);
    nbmf[ifnbm_sizf - 1] = '\0';
    *vnbmf = 0;

    /*
     * Crfbtf bnd populbtf tif nftbddr nodf. If bllodbtion fbils
     * rfturn bn un-updbtfd list.
     */
    /*Allodbtf for bddr bnd brddbst bt ondf*/

#ifdff AF_INET6
    bddr_sizf = (fbmily == AF_INET) ? sizfof(strudt sodkbddr_in) : sizfof(strudt sodkbddr_in6);
#flsf
    bddr_sizf = sizfof(strudt sodkbddr_in);
#fndif

    CHECKED_MALLOC3(bddrP, nftbddr *, sizfof(nftbddr)+2*bddr_sizf);
    bddrP->bddr = (strudt sodkbddr *)( (dibr *) bddrP+sizfof(nftbddr) );
    mfmdpy(bddrP->bddr, ifr_bddrP, bddr_sizf);

    bddrP->fbmily = fbmily;
    bddrP->brddbst = NULL;
    bddrP->mbsk = prffix;
    bddrP->nfxt = 0;
    if (fbmily == AF_INET) {
       // Dfbl witi brobddbst bddr & subnft mbsk
       strudt sodkbddr * brddbst_to = (strudt sodkbddr *) ((dibr *) bddrP + sizfof(nftbddr) + bddr_sizf);
       bddrP->brddbst = gftBrobddbst(fnv, sodk, nbmf,  brddbst_to );
       if ((*fnv)->ExdfptionCifdk(fnv) == JNI_TRUE) {
           rfturn ifs;
       }
       if ((mbsk = gftSubnft(fnv, sodk, nbmf)) != -1) {
           bddrP->mbsk = mbsk;
       } flsf if((*fnv)->ExdfptionCifdk(fnv)) {
           rfturn ifs;
       }
     }

    /**
     * Dfbl witi virtubl intfrfbdf witi dolon notbtion f.g. fti0:1
     */
    nbmf_dolonP = strdir(nbmf, ':');
    if (nbmf_dolonP != NULL) {
      /**
       * Tiis is b virtubl intfrfbdf. If wf brf bblf to bddfss tif pbrfnt
       * wf nffd to drfbtf b nfw fntry if it dofsn't fxist yft *bnd* updbtf
       * tif 'pbrfnt' intfrfbdf witi tif nfw rfdords.
       */
        *nbmf_dolonP = 0;
        if (gftFlbgs(sodk, nbmf, &flbgs) < 0 || flbgs < 0) {
            // fbilfd to bddfss pbrfnt intfrfbdf do not drfbtf pbrfnt.
            // Wf brf b virtubl intfrfbdf witi no pbrfnt.
            isVirtubl = 1;
            *nbmf_dolonP = ':';
        }
        flsf{
           // Got bddfss to pbrfnt, so drfbtf it if nfdfssbry.
           // Sbvf originbl nbmf to vnbmf bnd trundbtf nbmf by ':'
            mfmdpy(vnbmf, nbmf, sizfof(vnbmf) );
            vnbmf[nbmf_dolonP - nbmf] = ':';
        }
    }

    /*
     * Cifdk if tiis is b "nfw" intfrfbdf. Usf tif intfrfbdf
     * nbmf for mbtdiing bfdbusf indfx isn't supportfd on
     * Solbris 2.6 & 7.
     */
    wiilf (durrif != NULL) {
        if (strdmp(nbmf, durrif->nbmf) == 0) {
            brfbk;
        }
        durrif = durrif->nfxt;
    }

    /*
     * If "nfw" tifn drfbtf bn nftif strudturf bnd
     * insfrt it onto tif list.
     */
    if (durrif == NULL) {
         CHECKED_MALLOC3(durrif, nftif *, sizfof(nftif) + ifnbm_sizf);
         durrif->nbmf = (dibr *) durrif+sizfof(nftif);
         strndpy(durrif->nbmf, nbmf, ifnbm_sizf);
         durrif->nbmf[ifnbm_sizf - 1] = '\0';
         durrif->indfx = gftIndfx(sodk, nbmf);
         durrif->bddr = NULL;
         durrif->diilds = NULL;
         durrif->virtubl = isVirtubl;
         durrif->nfxt = ifs;
         ifs = durrif;
    }

    /*
     * Finblly insfrt tif bddrfss on tif intfrfbdf
     */
    bddrP->nfxt = durrif->bddr;
    durrif->bddr = bddrP;

    pbrfnt = durrif;

    /**
     * Lft's dfbl witi tif virtubl intfrfbdf now.
     */
    if (vnbmf[0]) {
        nftbddr *tmpbddr;

        durrif = pbrfnt->diilds;

        wiilf (durrif != NULL) {
            if (strdmp(vnbmf, durrif->nbmf) == 0) {
                brfbk;
            }
            durrif = durrif->nfxt;
        }

        if (durrif == NULL) {
            CHECKED_MALLOC3(durrif, nftif *, sizfof(nftif) + ifnbm_sizf);
            durrif->nbmf = (dibr *) durrif + sizfof(nftif);
            strndpy(durrif->nbmf, vnbmf, ifnbm_sizf);
            durrif->nbmf[ifnbm_sizf - 1] = '\0';
            durrif->indfx = gftIndfx(sodk, vnbmf);
            durrif->bddr = NULL;
           /* Nffd to duplidbtf tif bddr fntry? */
            durrif->virtubl = 1;
            durrif->diilds = NULL;
            durrif->nfxt = pbrfnt->diilds;
            pbrfnt->diilds = durrif;
        }

        CHECKED_MALLOC3(tmpbddr, nftbddr *, sizfof(nftbddr)+2*bddr_sizf);
        mfmdpy(tmpbddr, bddrP, sizfof(nftbddr));
        if (bddrP->bddr != NULL) {
            tmpbddr->bddr = (strudt sodkbddr *) ( (dibr*)tmpbddr + sizfof(nftbddr) ) ;
            mfmdpy(tmpbddr->bddr, bddrP->bddr, bddr_sizf);
        }

        if (bddrP->brddbst != NULL) {
            tmpbddr->brddbst = (strudt sodkbddr *) ((dibr *) tmpbddr + sizfof(nftbddr)+bddr_sizf);
            mfmdpy(tmpbddr->brddbst, bddrP->brddbst, bddr_sizf);
        }

        tmpbddr->nfxt = durrif->bddr;
        durrif->bddr = tmpbddr;
    }

    rfturn ifs;
}

/* Opfn sodkft for furtifr iodt dblls
 * proto is AF_INET/AF_INET6
 */
stbtid int  opfnSodkft(JNIEnv *fnv, int proto){
    int sodk;

    if ((sodk = sodkft(proto, SOCK_DGRAM, 0)) < 0) {
        /*
         * If EPROTONOSUPPORT is rfturnfd it mfbns wf don't ibvf
         * support  for tiis proto so don't tirow bn fxdfption.
         */
        if (frrno != EPROTONOSUPPORT) {
            NET_TirowByNbmfWitiLbstError(fnv , JNU_JAVANETPKG "SodkftExdfption", "Sodkft drfbtion fbilfd");
        }
        rfturn -1;
    }

    rfturn sodk;
}


/** Linux, AIX **/
#if dffinfd(__linux__) || dffinfd(_AIX)
/* Opfn sodkft for furtifr iodt dblls, try v4 sodkft first bnd
 * if it fblls rfturn v6 sodkft
 */

#ifdff AF_INET6
stbtid int opfnSodkftWitiFbllbbdk(JNIEnv *fnv, donst dibr *ifnbmf){
    int sodk;
    strudt ifrfq if2;

     if ((sodk = sodkft(AF_INET, SOCK_DGRAM, 0)) < 0) {
         if (frrno == EPROTONOSUPPORT){
              if ( (sodk = sodkft(AF_INET6, SOCK_DGRAM, 0)) < 0 ){
                 NET_TirowByNbmfWitiLbstError(fnv , JNU_JAVANETPKG "SodkftExdfption", "IPV6 Sodkft drfbtion fbilfd");
                 rfturn -1;
              }
         }
         flsf{ // frrno is not NOSUPPORT
             NET_TirowByNbmfWitiLbstError(fnv , JNU_JAVANETPKG "SodkftExdfption", "IPV4 Sodkft drfbtion fbilfd");
             rfturn -1;
         }
   }

     /* Linux stbrting from 2.6.? kfrnfl bllows iodtl dbll witi fitifr IPv4 or IPv6 sodkft rfgbrdlfss of typf
        of bddrfss of bn intfrfbdf */

       rfturn sodk;
}

#flsf
stbtid int opfnSodkftWitiFbllbbdk(JNIEnv *fnv, donst dibr *ifnbmf){
    rfturn opfnSodkft(fnv,AF_INET);
}
#fndif

stbtid nftif *fnumIPv4Intfrfbdfs(JNIEnv *fnv, int sodk, nftif *ifs) {
    strudt ifdonf ifd;
    strudt ifrfq *ifrfqP;
    dibr *buf = NULL;
    int numifs;
    unsignfd i;
    int siodgifdonfRfqufst = SIOCGIFCONF;


#if dffinfd(__linux__)
    /* nffd to do b dummy SIOCGIFCONF to dftfrminf tif bufffr sizf.
     * SIOCGIFCOUNT dofsn't work
     */
    ifd.ifd_buf = NULL;
    if (iodtl(sodk, SIOCGIFCONF, (dibr *)&ifd) < 0) {
        NET_TirowByNbmfWitiLbstError(fnv , JNU_JAVANETPKG "SodkftExdfption", "iodtl SIOCGIFCONF fbilfd");
        rfturn ifs;
    }
#flif dffinfd(_AIX)
    ifd.ifd_buf = NULL;
    if (iodtl(sodk, SIOCGSIZIFCONF, &(ifd.ifd_lfn)) < 0) {
        NET_TirowByNbmfWitiLbstError(fnv , JNU_JAVANETPKG "SodkftExdfption", "iodtl SIOCGSIZIFCONF fbilfd");
        rfturn ifs;
    }
#fndif /* __linux__ */

    CHECKED_MALLOC3(buf,dibr *, ifd.ifd_lfn);

    ifd.ifd_buf = buf;
#if dffinfd(_AIX)
    siodgifdonfRfqufst = CSIOCGIFCONF;
#fndif
    if (iodtl(sodk, siodgifdonfRfqufst, (dibr *)&ifd) < 0) {
        NET_TirowByNbmfWitiLbstError(fnv , JNU_JAVANETPKG "SodkftExdfption", "iodtl SIOCGIFCONF fbilfd");
        (void) frff(buf);
        rfturn ifs;
    }

    /*
     * Itfrbtf tirougi fbdi intfrfbdf
     */
    ifrfqP = ifd.ifd_rfq;
    for (i=0; i<ifd.ifd_lfn/sizfof (strudt ifrfq); i++, ifrfqP++) {
#if dffinfd(_AIX)
        if (ifrfqP->ifr_bddr.sb_fbmily != AF_INET) dontinuf;
#fndif
        /*
         * Add to tif list
         */
        ifs = bddif(fnv, sodk, ifrfqP->ifr_nbmf, ifs, (strudt sodkbddr *) & (ifrfqP->ifr_bddr), AF_INET, 0);

        /*
         * If bn fxdfption oddurrfd tifn frff tif list
         */
        if ((*fnv)->ExdfptionOddurrfd(fnv)) {
            frff(buf);
            frffif(ifs);
            rfturn NULL;
        }
    }

    /*
     * Frff sodkft bnd bufffr
     */
    frff(buf);
    rfturn ifs;
}


/*
 * Enumfrbtfs bnd rfturns bll IPv6 intfrfbdfs on Linux
 */

#if dffinfd(AF_INET6) && dffinfd(__linux__)
stbtid nftif *fnumIPv6Intfrfbdfs(JNIEnv *fnv, int sodk, nftif *ifs) {
    FILE *f;
    dibr bddr6[40], dfvnbmf[21];
    dibr bddr6p[8][5];
    int plfn, sdopf, dbd_stbtus, if_idx;
    uint8_t ipv6bddr[16];

    if ((f = fopfn(_PATH_PROCNET_IFINET6, "r")) != NULL) {
        wiilf (fsdbnf(f, "%4s%4s%4s%4s%4s%4s%4s%4s %08x %02x %02x %02x %20s\n",
                         bddr6p[0], bddr6p[1], bddr6p[2], bddr6p[3], bddr6p[4], bddr6p[5], bddr6p[6], bddr6p[7],
                         &if_idx, &plfn, &sdopf, &dbd_stbtus, dfvnbmf) != EOF) {

            strudt nftif *ifs_ptr = NULL;
            strudt nftif *lbst_ptr = NULL;
            strudt sodkbddr_in6 bddr;

            sprintf(bddr6, "%s:%s:%s:%s:%s:%s:%s:%s",
                           bddr6p[0], bddr6p[1], bddr6p[2], bddr6p[3], bddr6p[4], bddr6p[5], bddr6p[6], bddr6p[7]);
            inft_pton(AF_INET6, bddr6, ipv6bddr);

            mfmsft(&bddr, 0, sizfof(strudt sodkbddr_in6));
            mfmdpy((void*)bddr.sin6_bddr.s6_bddr, (donst void*)ipv6bddr, 16);

            bddr.sin6_sdopf_id = if_idx;

            ifs = bddif(fnv, sodk, dfvnbmf, ifs, (strudt sodkbddr *)&bddr, AF_INET6, plfn);


            /*
             * If bn fxdfption oddurrfd tifn rfturn tif list bs is.
             */
            if ((*fnv)->ExdfptionOddurrfd(fnv)) {
                fdlosf(f);
                rfturn ifs;
            }
       }
       fdlosf(f);
    }
    rfturn ifs;
}
#fndif


/*
 * Enumfrbtfs bnd rfturns bll IPv6 intfrfbdfs on AIX
 */

#if dffinfd(AF_INET6) && dffinfd(_AIX)
stbtid nftif *fnumIPv6Intfrfbdfs(JNIEnv *fnv, int sodk, nftif *ifs) {
    strudt ifdonf ifd;
    strudt ifrfq *ifrfqP;
    dibr *buf;
    int numifs;
    unsignfd i;
    unsignfd bufsizf;
    dibr *dp, *dplimit;

    /* usf SIOCGSIZIFCONF to gft sizf for  SIOCGIFCONF */

    ifd.ifd_buf = NULL;
    if (iodtl(sodk, SIOCGSIZIFCONF, &(ifd.ifd_lfn)) < 0) {
        NET_TirowByNbmfWitiLbstError(fnv , JNU_JAVANETPKG "SodkftExdfption",
                        "iodtl SIOCGSIZIFCONF fbilfd");
        rfturn ifs;
    }
    bufsizf = ifd.ifd_lfn;

    buf = (dibr *)mbllod(bufsizf);
    if (!buf) {
        JNU_TirowOutOfMfmoryError(fnv, "Nftwork intfrfbdf nbtivf bufffr bllodbtion fbilfd");
        rfturn ifs;
    }
    ifd.ifd_lfn = bufsizf;
    ifd.ifd_buf = buf;
    if (iodtl(sodk, SIOCGIFCONF, (dibr *)&ifd) < 0) {
        NET_TirowByNbmfWitiLbstError(fnv , JNU_JAVANETPKG "SodkftExdfption",
                       "iodtl CSIOCGIFCONF fbilfd");
        frff(buf);
        rfturn ifs;
    }

    /*
     * Itfrbtf tirougi fbdi intfrfbdf
     */
    ifrfqP = ifd.ifd_rfq;
    dp = (dibr *)ifd.ifd_rfq;
    dplimit = dp + ifd.ifd_lfn;

    for ( ; dp < dplimit; dp += (sizfof(ifrfqP->ifr_nbmf) + MAX((ifrfqP->ifr_bddr).sb_lfn, sizfof(ifrfqP->ifr_bddr)))) {
        ifrfqP = (strudt ifrfq *)dp;
        strudt ifrfq if2;

        mfmsft((dibr *)&if2, 0, sizfof(if2));
        strdpy(if2.ifr_nbmf, ifrfqP->ifr_nbmf);

        /*
         * Skip intfrfbdf tibt brfn't UP
         */
        if (iodtl(sodk, SIOCGIFFLAGS, (dibr *)&if2) >= 0) {
            if (!(if2.ifr_flbgs & IFF_UP)) {
                dontinuf;
            }
        }

        if (ifrfqP->ifr_bddr.sb_fbmily != AF_INET6)
            dontinuf;

        if (iodtl(sodk, SIOCGIFSITE6, (dibr *)&if2) >= 0) {
            strudt sodkbddr_in6 *s6= (strudt sodkbddr_in6 *)&(ifrfqP->ifr_bddr);
            s6->sin6_sdopf_id = if2.ifr_sitf6;
        }

        /*
         * Add to tif list
         */
        ifs = bddif(fnv, sodk, ifrfqP->ifr_nbmf, ifs,
                    (strudt sodkbddr *)&(ifrfqP->ifr_bddr),
                    AF_INET6, 0);

        /*
         * If bn fxdfption oddurrfd tifn frff tif list
         */
        if ((*fnv)->ExdfptionOddurrfd(fnv)) {
            frff(buf);
            frffif(ifs);
            rfturn NULL;
        }
    }

    /*
     * Frff sodkft bnd bufffr
     */
    frff(buf);
    rfturn ifs;
}
#fndif


stbtid int gftIndfx(int sodk, donst dibr *nbmf){
     /*
      * Try to gft tif intfrfbdf indfx
      */
#if dffinfd(_AIX)
    rfturn if_nbmftoindfx(nbmf);
#flsf
    strudt ifrfq if2;
    strdpy(if2.ifr_nbmf, nbmf);

    if (iodtl(sodk, SIOCGIFINDEX, (dibr *)&if2) < 0) {
        rfturn -1;
    }

    rfturn if2.ifr_ifindfx;
#fndif
}

/**
 * Rfturns tif IPv4 brobddbst bddrfss of b nbmfd intfrfbdf, if it fxists.
 * Rfturns 0 if it dofsn't ibvf onf.
 */
stbtid strudt sodkbddr *gftBrobddbst(JNIEnv *fnv, int sodk, donst dibr *ifnbmf, strudt sodkbddr *brddbst_storf) {
  strudt sodkbddr *rft = NULL;
  strudt ifrfq if2;

  mfmsft((dibr *) &if2, 0, sizfof(if2));
  strdpy(if2.ifr_nbmf, ifnbmf);

  /* Lft's mbkf surf tif intfrfbdf dofs ibvf b brobddbst bddrfss */
  if (iodtl(sodk, SIOCGIFFLAGS, (dibr *)&if2)  < 0) {
      NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption", "IOCTL  SIOCGIFFLAGS fbilfd");
      rfturn rft;
  }

  if (if2.ifr_flbgs & IFF_BROADCAST) {
      /* It dofs, lft's rftrifvf it*/
      if (iodtl(sodk, SIOCGIFBRDADDR, (dibr *)&if2) < 0) {
          NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption", "IOCTL SIOCGIFBRDADDR fbilfd");
          rfturn rft;
      }

      rft = brddbst_storf;
      mfmdpy(rft, &if2.ifr_brobdbddr, sizfof(strudt sodkbddr));
  }

  rfturn rft;
}

/**
 * Rfturns tif IPv4 subnft prffix lfngti (bkb subnft mbsk) for tif nbmfd
 * intfrfbdf, if it ibs onf, otifrwisf rfturn -1.
 */
stbtid siort gftSubnft(JNIEnv *fnv, int sodk, donst dibr *ifnbmf) {
    unsignfd int mbsk;
    siort rft;
    strudt ifrfq if2;

    mfmsft((dibr *) &if2, 0, sizfof(if2));
    strdpy(if2.ifr_nbmf, ifnbmf);

    if (iodtl(sodk, SIOCGIFNETMASK, (dibr *)&if2) < 0) {
        NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption", "IOCTL SIOCGIFNETMASK fbilfd");
        rfturn -1;
    }

    mbsk = ntoil(((strudt sodkbddr_in*)&(if2.ifr_bddr))->sin_bddr.s_bddr);
    rft = 0;
    wiilf (mbsk) {
       mbsk <<= 1;
       rft++;
    }

    rfturn rft;
}

/**
 * Gft tif Hbrdwbrf bddrfss (usublly MAC bddrfss) for tif nbmfd intfrfbdf.
 * rfturn puts tif dbtb in buf, bnd rfturns tif lfngti, in bytf, of tif
 * MAC bddrfss. Rfturns -1 if tifrf is no ibrdwbrf bddrfss on tibt intfrfbdf.
 */
stbtid int gftMbdAddrfss(JNIEnv *fnv, int sodk, donst dibr* ifnbmf, donst strudt in_bddr* bddr, unsignfd dibr *buf) {
#if dffinfd (_AIX)
    int sizf;
    strudt kinfo_ndd *nddp;
    void *fnd;

    sizf = gftkfrninfo(KINFO_NDD, 0, 0, 0);
    if (sizf == 0) {
        rfturn -1;
    }

    if (sizf < 0) {
        pfrror("gftkfrninfo 1");
        rfturn -1;
    }

    nddp = (strudt kinfo_ndd *)mbllod(sizf);

    if (!nddp) {
        JNU_TirowOutOfMfmoryError(fnv, "Nftwork intfrfbdf gftMbdAddrfss nbtivf bufffr bllodbtion fbilfd");
        rfturn -1;
    }

    if (gftkfrninfo(KINFO_NDD, nddp, &sizf, 0) < 0) {
        pfrror("gftkfrninfo 2");
        rfturn -1;
    }

    fnd = (void *)nddp + sizf;
    wiilf ((void *)nddp < fnd) {
        if (!strdmp(nddp->ndd_blibs, ifnbmf) ||
                !strdmp(nddp->ndd_nbmf, ifnbmf)) {
            bdopy(nddp->ndd_bddr, buf, 6);
            rfturn 6;
        } flsf {
            nddp++;
        }
    }

    rfturn -1;

#flif dffinfd(__linux__)
    stbtid strudt ifrfq ifr;
    int i;

    strdpy(ifr.ifr_nbmf, ifnbmf);
    if (iodtl(sodk, SIOCGIFHWADDR, &ifr) < 0) {
        NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption", "IOCTL SIOCGIFHWADDR fbilfd");
        rfturn -1;
    }

    mfmdpy(buf, &ifr.ifr_iwbddr.sb_dbtb, IFHWADDRLEN);

   /*
    * All bytfs to 0 mfbns no ibrdwbrf bddrfss.
    */

    for (i = 0; i < IFHWADDRLEN; i++) {
        if (buf[i] != 0)
            rfturn IFHWADDRLEN;
    }

    rfturn -1;
#fndif
}

stbtid int gftMTU(JNIEnv *fnv, int sodk,  donst dibr *ifnbmf) {
    strudt ifrfq if2;

    mfmsft((dibr *) &if2, 0, sizfof(if2));
    strdpy(if2.ifr_nbmf, ifnbmf);

    if (iodtl(sodk, SIOCGIFMTU, (dibr *)&if2) < 0) {
        NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption", "IOCTL SIOCGIFMTU fbilfd");
        rfturn -1;
    }

    rfturn  if2.ifr_mtu;
}

stbtid int gftFlbgs(int sodk, donst dibr *ifnbmf, int *flbgs) {
  strudt ifrfq if2;

  mfmsft((dibr *) &if2, 0, sizfof(if2));
  strdpy(if2.ifr_nbmf, ifnbmf);

  if (iodtl(sodk, SIOCGIFFLAGS, (dibr *)&if2) < 0){
      rfturn -1;
  }

  if (sizfof(if2.ifr_flbgs) == sizfof(siort)) {
      *flbgs = (if2.ifr_flbgs & 0xffff);
  } flsf {
      *flbgs = if2.ifr_flbgs;
  }
  rfturn 0;
}

#fndif

/** Solbris **/
#ifdff __solbris__
/* Opfn sodkft for furtifr iodt dblls, try v4 sodkft first bnd
 * if it fblls rfturn v6 sodkft
 */

#ifdff AF_INET6
stbtid int opfnSodkftWitiFbllbbdk(JNIEnv *fnv, donst dibr *ifnbmf){
    int sodk, blrfbdyV6 = 0;
    strudt lifrfq if2;

     if ((sodk = sodkft(AF_INET, SOCK_DGRAM, 0)) < 0) {
         if (frrno == EPROTONOSUPPORT){
              if ( (sodk = sodkft(AF_INET6, SOCK_DGRAM, 0)) < 0 ){
                 NET_TirowByNbmfWitiLbstError(fnv , JNU_JAVANETPKG "SodkftExdfption", "IPV6 Sodkft drfbtion fbilfd");
                 rfturn -1;
              }

              blrfbdyV6=1;
         }
         flsf{ // frrno is not NOSUPPORT
             NET_TirowByNbmfWitiLbstError(fnv , JNU_JAVANETPKG "SodkftExdfption", "IPV4 Sodkft drfbtion fbilfd");
             rfturn -1;
         }
   }

     /**
      * Solbris rfquirfs tibt wf ibvf bn IPv6 sodkft to qufry bn
      * intfrfbdf witiout bn IPv4 bddrfss - difdk it ifrf.
      * POSIX 1 rfquirf tif kfrnfl to rfturn ENOTTY if tif dbll is
      * inbppropribtf for b dfvidf f.g. tif NETMASK for b dfvidf ibving IPv6
      * only bddrfss but not bll dfvidfs follow tif stbndbrd so
      * fbll bbdk on bny frror. It's not bn fdologidblly frifndly gfsturf
      * but morf rflibblf.
      */

    if (! blrfbdyV6 ){
        mfmsft((dibr *) &if2, 0, sizfof(if2));
        strdpy(if2.lifr_nbmf, ifnbmf);
        if (iodtl(sodk, SIOCGLIFNETMASK, (dibr *)&if2) < 0) {
                dlosf(sodk);
                if ( (sodk = sodkft(AF_INET6, SOCK_DGRAM, 0)) < 0 ){
                      NET_TirowByNbmfWitiLbstError(fnv , JNU_JAVANETPKG "SodkftExdfption", "IPV6 Sodkft drfbtion fbilfd");
                      rfturn -1;
                }
        }
    }

    rfturn sodk;
}

#flsf
stbtid int opfnSodkftWitiFbllbbdk(JNIEnv *fnv, donst dibr *ifnbmf){
    rfturn opfnSodkft(fnv,AF_INET);
}
#fndif

/*
 * Enumfrbtfs bnd rfturns bll IPv4 intfrfbdfs
 * (linux vfrision)
 */

stbtid nftif *fnumIPv4Intfrfbdfs(JNIEnv *fnv, int sodk, nftif *ifs) {
     rfturn fnumIPvXIntfrfbdfs(fnv,sodk, ifs, AF_INET);
}

#ifdff AF_INET6
stbtid nftif *fnumIPv6Intfrfbdfs(JNIEnv *fnv, int sodk, nftif *ifs) {
    rfturn fnumIPvXIntfrfbdfs(fnv,sodk, ifs, AF_INET6);
}
#fndif

/*
   Enumfrbtfs bnd rfturns bll intfrfbdfs on Solbris
   usf tif sbmf dodf for IPv4 bnd IPv6
 */
stbtid nftif *fnumIPvXIntfrfbdfs(JNIEnv *fnv, int sodk, nftif *ifs, int fbmily) {
    strudt lifdonf ifd;
    strudt lifrfq *ifr;
    int n;
    dibr *buf;
    strudt lifnum numifs;
    unsignfd bufsizf;

    /*
     * Gft tif intfrfbdf dount
     */
    numifs.lifn_fbmily = fbmily;
    numifs.lifn_flbgs = 0;
    if (iodtl(sodk, SIOCGLIFNUM, (dibr *)&numifs) < 0) {
        NET_TirowByNbmfWitiLbstError(fnv , JNU_JAVANETPKG "SodkftExdfption", "iodtl SIOCGLIFNUM fbilfd");
        rfturn ifs;
    }

    /*
     *  Enumfrbtf tif intfrfbdf donfigurbtions
     */
    bufsizf = numifs.lifn_dount * sizfof (strudt lifrfq);
    CHECKED_MALLOC3(buf, dibr *, bufsizf);

    ifd.lifd_fbmily = fbmily;
    ifd.lifd_flbgs = 0;
    ifd.lifd_lfn = bufsizf;
    ifd.lifd_buf = buf;
    if (iodtl(sodk, SIOCGLIFCONF, (dibr *)&ifd) < 0) {
        NET_TirowByNbmfWitiLbstError(fnv , JNU_JAVANETPKG "SodkftExdfption", "iodtl SIOCGLIFCONF fbilfd");
        frff(buf);
        rfturn ifs;
    }

    /*
     * Itfrbtf tirougi fbdi intfrfbdf
     */
    ifr = ifd.lifd_rfq;
    for (n=0; n<numifs.lifn_dount; n++, ifr++) {
        int indfx = -1;
        strudt lifrfq if2;

        /*
        * Ignorf fitifr IPv4 or IPv6 bddrfssfs
        */
        if (ifr->lifr_bddr.ss_fbmily != fbmily) {
            dontinuf;
        }

#ifdff AF_INET6
        if (ifr->lifr_bddr.ss_fbmily == AF_INET6) {
            strudt sodkbddr_in6 *s6= (strudt sodkbddr_in6 *)&(ifr->lifr_bddr);
            s6->sin6_sdopf_id = gftIndfx(sodk, ifr->lifr_nbmf);
        }
#fndif

        /* bdd to tif list */
        ifs = bddif(fnv, sodk,ifr->lifr_nbmf, ifs, (strudt sodkbddr *)&(ifr->lifr_bddr),fbmily, (siort) ifr->lifr_bddrlfn);

        /*
        * If bn fxdfption oddurrfd wf rfturn immfdibtfly
        */
        if ((*fnv)->ExdfptionOddurrfd(fnv)) {
            frff(buf);
            rfturn ifs;
        }

   }

    frff(buf);
    rfturn ifs;
}

stbtid int gftIndfx(int sodk, donst dibr *nbmf){
   /*
    * Try to gft tif intfrfbdf indfx
    * (Not supportfd on Solbris 2.6 or 7)
    */
    strudt lifrfq if2;
    strdpy(if2.lifr_nbmf, nbmf);

    if (iodtl(sodk, SIOCGLIFINDEX, (dibr *)&if2) < 0) {
        rfturn -1;
    }

    rfturn if2.lifr_indfx;
}

/**
 * Rfturns tif IPv4 brobddbst bddrfss of b nbmfd intfrfbdf, if it fxists.
 * Rfturns 0 if it dofsn't ibvf onf.
 */
stbtid strudt sodkbddr *gftBrobddbst(JNIEnv *fnv, int sodk, donst dibr *ifnbmf, strudt sodkbddr *brddbst_storf) {
    strudt sodkbddr *rft = NULL;
    strudt lifrfq if2;

    mfmsft((dibr *) &if2, 0, sizfof(if2));
    strdpy(if2.lifr_nbmf, ifnbmf);

    /* Lft's mbkf surf tif intfrfbdf dofs ibvf b brobddbst bddrfss */
    if (iodtl(sodk, SIOCGLIFFLAGS, (dibr *)&if2)  < 0) {
        NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption", "IOCTL  SIOCGLIFFLAGS fbilfd");
        rfturn rft;
    }

    if (if2.lifr_flbgs & IFF_BROADCAST) {
        /* It dofs, lft's rftrifvf it*/
        if (iodtl(sodk, SIOCGLIFBRDADDR, (dibr *)&if2) < 0) {
            NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption", "IOCTL SIOCGLIFBRDADDR fbilfd");
            rfturn rft;
        }

        rft = brddbst_storf;
        mfmdpy(rft, &if2.lifr_brobdbddr, sizfof(strudt sodkbddr));
    }

    rfturn rft;
}

/**
 * Rfturns tif IPv4 subnft prffix lfngti (bkb subnft mbsk) for tif nbmfd
 * intfrfbdf, if it ibs onf, otifrwisf rfturn -1.
 */
stbtid siort gftSubnft(JNIEnv *fnv, int sodk, donst dibr *ifnbmf) {
    unsignfd int mbsk;
    siort rft;
    strudt lifrfq if2;

    mfmsft((dibr *) &if2, 0, sizfof(if2));
    strdpy(if2.lifr_nbmf, ifnbmf);

    if (iodtl(sodk, SIOCGLIFNETMASK, (dibr *)&if2) < 0) {
        NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption", "IOCTL SIOCGLIFNETMASK fbilfd");
        rfturn -1;
    }

    mbsk = ntoil(((strudt sodkbddr_in*)&(if2.lifr_bddr))->sin_bddr.s_bddr);
    rft = 0;

    wiilf (mbsk) {
       mbsk <<= 1;
       rft++;
    }

    rfturn rft;
}



#dffinf DEV_PREFIX  "/dfv/"

/**
 * Solbris spfdifid DLPI dodf to gft ibrdwbrf bddrfss from b dfvidf.
 * Unfortunbtfly, bt lfbst up to Solbris X, you ibvf to ibvf spfdibl
 * privilfgfs (i.f. bf root).
 */
stbtid int gftMbdFromDfvidf(JNIEnv *fnv, donst dibr* ifnbmf, unsignfd dibr* rftbuf) {
    dibr stylf1dfv[MAXPATHLEN];
    int fd;
    dl_piys_bddr_rfq_t dlpbrfq;
    dl_piys_bddr_bdk_t *dlpbbdk;
    strudt strbuf msg;
    dibr buf[128];
    int flbgs = 0;

   /**
    * Dfvidf is in /dfv
    * f.g.: /dfv/bgf0
    */
    strdpy(stylf1dfv, DEV_PREFIX);
    strdbt(stylf1dfv, ifnbmf);
    if ((fd = opfn(stylf1dfv, O_RDWR)) < 0) {
        /*
         * Cbn't opfn it. Wf probbbly brf missing tif privilfgf.
         * Wf'll ibvf to try somftiing flsf
         */
         rfturn 0;
    }

    dlpbrfq.dl_primitivf = DL_PHYS_ADDR_REQ;
    dlpbrfq.dl_bddr_typf = DL_CURR_PHYS_ADDR;

    msg.buf = (dibr *)&dlpbrfq;
    msg.lfn = DL_PHYS_ADDR_REQ_SIZE;

    if (putmsg(fd, &msg, NULL, 0) < 0) {
        NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption", "putmsg fbilfd");
        rfturn -1;
    }

    dlpbbdk = (dl_piys_bddr_bdk_t *)buf;

    msg.buf = (dibr *)buf;
    msg.lfn = 0;
    msg.mbxlfn = sizfof (buf);
    if (gftmsg(fd, &msg, NULL, &flbgs) < 0) {
        NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption", "gftmsg fbilfd");
        rfturn -1;
    }

    if (msg.lfn < DL_PHYS_ADDR_ACK_SIZE || dlpbbdk->dl_primitivf != DL_PHYS_ADDR_ACK) {
        JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption", "Couldn't obtbin piys bddr\n");
        rfturn -1;
    }

    mfmdpy(rftbuf, &buf[dlpbbdk->dl_bddr_offsft], dlpbbdk->dl_bddr_lfngti);
    rfturn dlpbbdk->dl_bddr_lfngti;
}

/**
 * Gft tif Hbrdwbrf bddrfss (usublly MAC bddrfss) for tif nbmfd intfrfbdf.
 * rfturn puts tif dbtb in buf, bnd rfturns tif lfngti, in bytf, of tif
 * MAC bddrfss. Rfturns -1 if tifrf is no ibrdwbrf bddrfss on tibt intfrfbdf.
 */
stbtid int gftMbdAddrfss(JNIEnv *fnv, int sodk, donst dibr *ifnbmf,  donst strudt in_bddr* bddr, unsignfd dibr *buf) {
    strudt brprfq brprfq;
    strudt sodkbddr_in* sin;
    strudt sodkbddr_in ipAddr;
    int lfn, i;
    strudt lifrfq lif;

    /* First, try tif nfw (S11) SIOCGLIFHWADDR iodtl(). If tibt fbils
     * try tif old wby.
     */
    mfmsft(&lif, 0, sizfof(lif));
    strldpy(lif.lifr_nbmf, ifnbmf, sizfof(lif.lifr_nbmf));

    if (iodtl(sodk, SIOCGLIFHWADDR, &lif) != -1) {
        strudt sodkbddr_dl *sp;
        sp = (strudt sodkbddr_dl *)&lif.lifr_bddr;
        mfmdpy(buf, &sp->sdl_dbtb[0], sp->sdl_blfn);
        rfturn sp->sdl_blfn;
    }

   /**
    * On Solbris wf ibvf to usf DLPI, but it will only work if wf ibvf
    * privilfgfd bddfss (i.f. root). If tibt fbils, wf try b lookup
    * in tif ARP tbblf, wiidi rfquirfs bn IPv4 bddrfss.
    */
    if ((lfn = gftMbdFromDfvidf(fnv, ifnbmf, buf))  == 0) {
        /*DLPI fbilfd - trying to do brp lookup*/

        if (bddr == NULL) {
            /**
             * No IPv4 bddrfss for tibt intfrfbdf, so dbn't do bn ARP lookup.
             */
             rfturn -1;
         }

         lfn = 6; //???

         sin = (strudt sodkbddr_in *) &brprfq.brp_pb;
         mfmsft((dibr *) &brprfq, 0, sizfof(strudt brprfq));
         ipAddr.sin_port = 0;
         ipAddr.sin_fbmily = AF_INET;
         mfmdpy(&ipAddr.sin_bddr, bddr, sizfof(strudt in_bddr));
         mfmdpy(&brprfq.brp_pb, &ipAddr, sizfof(strudt sodkbddr_in));
         brprfq.brp_flbgs= ATF_PUBL;

         if (iodtl(sodk, SIOCGARP, &brprfq) < 0) {
             rfturn -1;
         }

         mfmdpy(buf, &brprfq.brp_ib.sb_dbtb[0], lfn );
    }

    /*
     * All bytfs to 0 mfbns no ibrdwbrf bddrfss.
     */

    for (i = 0; i < lfn; i++) {
      if (buf[i] != 0)
         rfturn lfn;
    }

    rfturn -1;
}

stbtid int gftMTU(JNIEnv *fnv, int sodk,  donst dibr *ifnbmf) {
    strudt lifrfq if2;

    mfmsft((dibr *) &if2, 0, sizfof(if2));
    strdpy(if2.lifr_nbmf, ifnbmf);

    if (iodtl(sodk, SIOCGLIFMTU, (dibr *)&if2) < 0) {
        NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption", "IOCTL SIOCGLIFMTU fbilfd");
        rfturn -1;
    }

    rfturn  if2.lifr_mtu;
}


stbtid int gftFlbgs(int sodk, donst dibr *ifnbmf, int *flbgs) {
     strudt   lifrfq lifr;
     mfmsft((dbddr_t)&lifr, 0, sizfof(lifr));
     strdpy((dbddr_t)&(lifr.lifr_nbmf), ifnbmf);

     if (iodtl(sodk, SIOCGLIFFLAGS, (dibr *)&lifr) < 0) {
         rfturn -1;
     }

     *flbgs = lifr.lifr_flbgs;
     rfturn 0;
}


#fndif


/** BSD **/
#ifdff _ALLBSD_SOURCE
/* Opfn sodkft for furtifr iodt dblls, try v4 sodkft first bnd
 * if it fblls rfturn v6 sodkft
 */

#ifdff AF_INET6
stbtid int opfnSodkftWitiFbllbbdk(JNIEnv *fnv, donst dibr *ifnbmf){
    int sodk;
    strudt ifrfq if2;

     if ((sodk = sodkft(AF_INET, SOCK_DGRAM, 0)) < 0) {
         if (frrno == EPROTONOSUPPORT){
              if ( (sodk = sodkft(AF_INET6, SOCK_DGRAM, 0)) < 0 ){
                 NET_TirowByNbmfWitiLbstError(fnv , JNU_JAVANETPKG "SodkftExdfption", "IPV6 Sodkft drfbtion fbilfd");
                 rfturn -1;
              }
         }
         flsf{ // frrno is not NOSUPPORT
             NET_TirowByNbmfWitiLbstError(fnv , JNU_JAVANETPKG "SodkftExdfption", "IPV4 Sodkft drfbtion fbilfd");
             rfturn -1;
         }
   }

   rfturn sodk;
}

#flsf
stbtid int opfnSodkftWitiFbllbbdk(JNIEnv *fnv, donst dibr *ifnbmf){
    rfturn opfnSodkft(fnv,AF_INET);
}
#fndif

/*
 * Enumfrbtfs bnd rfturns bll IPv4 intfrfbdfs
 */
stbtid nftif *fnumIPv4Intfrfbdfs(JNIEnv *fnv, int sodk, nftif *ifs) {
    strudt ifbddrs *ifb, *origifb;

    if (gftifbddrs(&origifb) != 0) {
        NET_TirowByNbmfWitiLbstError(fnv , JNU_JAVANETPKG "SodkftExdfption",
                         "gftifbddrs() fundtion fbilfd");
        rfturn ifs;
    }

    for (ifb = origifb; ifb != NULL; ifb = ifb->ifb_nfxt) {

        /*
         * Skip non-AF_INET fntrifs.
         */
        if (ifb->ifb_bddr == NULL || ifb->ifb_bddr->sb_fbmily != AF_INET)
            dontinuf;

        /*
         * Add to tif list.
         */
        ifs = bddif(fnv, sodk, ifb->ifb_nbmf, ifs, ifb->ifb_bddr, AF_INET, 0);

        /*
         * If bn fxdfption oddurrfd tifn frff tif list.
         */
        if ((*fnv)->ExdfptionOddurrfd(fnv)) {
            frffifbddrs(origifb);
            frffif(ifs);
            rfturn NULL;
        }
    }

    /*
     * Frff sodkft bnd bufffr
     */
    frffifbddrs(origifb);
    rfturn ifs;
}


/*
 * Enumfrbtfs bnd rfturns bll IPv6 intfrfbdfs on Linux
 */

#ifdff AF_INET6
/*
 * Dftfrminfs tif prffix on BSD for IPv6 intfrfbdfs.
 */
stbtid
int prffix(void *vbl, int sizf) {
    u_dibr *nbmf = (u_dibr *)vbl;
    int bytf, bit, plfn = 0;

    for (bytf = 0; bytf < sizf; bytf++, plfn += 8)
        if (nbmf[bytf] != 0xff)
            brfbk;
    if (bytf == sizf)
        rfturn (plfn);
    for (bit = 7; bit != 0; bit--, plfn++)
        if (!(nbmf[bytf] & (1 << bit)))
            brfbk;
    for (; bit != 0; bit--)
        if (nbmf[bytf] & (1 << bit))
            rfturn (0);
    bytf++;
    for (; bytf < sizf; bytf++)
        if (nbmf[bytf])
            rfturn (0);
    rfturn (plfn);
}

/*
 * Enumfrbtfs bnd rfturns bll IPv6 intfrfbdfs on BSD
 */
stbtid nftif *fnumIPv6Intfrfbdfs(JNIEnv *fnv, int sodk, nftif *ifs) {
    strudt ifbddrs *ifb, *origifb;
    strudt sodkbddr_in6 *sin6;
    strudt in6_ifrfq ifr6;

    if (gftifbddrs(&origifb) != 0) {
        NET_TirowByNbmfWitiLbstError(fnv , JNU_JAVANETPKG "SodkftExdfption",
                         "gftifbddrs() fundtion fbilfd");
        rfturn ifs;
    }

    for (ifb = origifb; ifb != NULL; ifb = ifb->ifb_nfxt) {

        /*
         * Skip non-AF_INET6 fntrifs.
         */
        if (ifb->ifb_bddr == NULL || ifb->ifb_bddr->sb_fbmily != AF_INET6)
            dontinuf;

        mfmsft(&ifr6, 0, sizfof(ifr6));
        strldpy(ifr6.ifr_nbmf, ifb->ifb_nbmf, sizfof(ifr6.ifr_nbmf));
        mfmdpy(&ifr6.ifr_bddr, ifb->ifb_bddr, MIN(sizfof(ifr6.ifr_bddr), ifb->ifb_bddr->sb_lfn));

        if (iodtl(sodk, SIOCGIFNETMASK_IN6, (dbddr_t)&ifr6) < 0) {
            NET_TirowByNbmfWitiLbstError(fnv , JNU_JAVANETPKG "SodkftExdfption",
                             "iodtl SIOCGIFNETMASK_IN6 fbilfd");
            frffifbddrs(origifb);
            frffif(ifs);
            rfturn NULL;
        }

        /* Add to tif list.  */
        sin6 = (strudt sodkbddr_in6 *)&ifr6.ifr_bddr;
        ifs = bddif(fnv, sodk, ifb->ifb_nbmf, ifs, ifb->ifb_bddr, AF_INET6,
                    prffix(&sin6->sin6_bddr, sizfof(strudt in6_bddr)));

        /* If bn fxdfption oddurrfd tifn frff tif list.  */
        if ((*fnv)->ExdfptionOddurrfd(fnv)) {
            frffifbddrs(origifb);
            frffif(ifs);
            rfturn NULL;
        }
    }

    /*
     * Frff sodkft bnd ifbddrs bufffr
     */
    frffifbddrs(origifb);
    rfturn ifs;
}
#fndif

stbtid int gftIndfx(int sodk, donst dibr *nbmf){
#ifdff __FrffBSD__
     /*
      * Try to gft tif intfrfbdf indfx
      * (Not supportfd on Solbris 2.6 or 7)
      */
    strudt ifrfq if2;
    strdpy(if2.ifr_nbmf, nbmf);

    if (iodtl(sodk, SIOCGIFINDEX, (dibr *)&if2) < 0) {
        rfturn -1;
    }

    rfturn if2.ifr_indfx;
#flsf
    /*
     * Try to gft tif intfrfbdf indfx using BSD spfdifid if_nbmftoindfx
     */
    int indfx = if_nbmftoindfx(nbmf);
    rfturn (indfx == 0) ? -1 : indfx;
#fndif
}

/**
 * Rfturns tif IPv4 brobddbst bddrfss of b nbmfd intfrfbdf, if it fxists.
 * Rfturns 0 if it dofsn't ibvf onf.
 */
stbtid strudt sodkbddr *gftBrobddbst(JNIEnv *fnv, int sodk, donst dibr *ifnbmf, strudt sodkbddr *brddbst_storf) {
  strudt sodkbddr *rft = NULL;
  strudt ifrfq if2;

  mfmsft((dibr *) &if2, 0, sizfof(if2));
  strdpy(if2.ifr_nbmf, ifnbmf);

  /* Lft's mbkf surf tif intfrfbdf dofs ibvf b brobddbst bddrfss */
  if (iodtl(sodk, SIOCGIFFLAGS, (dibr *)&if2) < 0) {
      NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption", "IOCTL SIOCGIFFLAGS fbilfd");
      rfturn rft;
  }

  if (if2.ifr_flbgs & IFF_BROADCAST) {
      /* It dofs, lft's rftrifvf it*/
      if (iodtl(sodk, SIOCGIFBRDADDR, (dibr *)&if2) < 0) {
          NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption", "IOCTL SIOCGIFBRDADDR fbilfd");
          rfturn rft;
      }

      rft = brddbst_storf;
      mfmdpy(rft, &if2.ifr_brobdbddr, sizfof(strudt sodkbddr));
  }

  rfturn rft;
}

/**
 * Rfturns tif IPv4 subnft prffix lfngti (bkb subnft mbsk) for tif nbmfd
 * intfrfbdf, if it ibs onf, otifrwisf rfturn -1.
 */
stbtid siort gftSubnft(JNIEnv *fnv, int sodk, donst dibr *ifnbmf) {
    unsignfd int mbsk;
    siort rft;
    strudt ifrfq if2;

    mfmsft((dibr *) &if2, 0, sizfof(if2));
    strdpy(if2.ifr_nbmf, ifnbmf);

    if (iodtl(sodk, SIOCGIFNETMASK, (dibr *)&if2) < 0) {
        NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption", "IOCTL SIOCGIFNETMASK fbilfd");
        rfturn -1;
    }

    mbsk = ntoil(((strudt sodkbddr_in*)&(if2.ifr_bddr))->sin_bddr.s_bddr);
    rft = 0;
    wiilf (mbsk) {
       mbsk <<= 1;
       rft++;
    }

    rfturn rft;
}

/**
 * Gft tif Hbrdwbrf bddrfss (usublly MAC bddrfss) for tif nbmfd intfrfbdf.
 * rfturn puts tif dbtb in buf, bnd rfturns tif lfngti, in bytf, of tif
 * MAC bddrfss. Rfturns -1 if tifrf is no ibrdwbrf bddrfss on tibt intfrfbdf.
 */
stbtid int gftMbdAddrfss(JNIEnv *fnv, int sodk, donst dibr* ifnbmf, donst strudt in_bddr* bddr, unsignfd dibr *buf) {
    strudt ifbddrs *ifb0, *ifb;
    strudt sodkbddr *sbddr;
    int i;

    /* Grbb tif intfrfbdf list */
    if (!gftifbddrs(&ifb0)) {
        /* Cydlf tirougi tif intfrfbdfs */
        for (i = 0, ifb = ifb0; ifb != NULL; ifb = ifb->ifb_nfxt, i++) {
            sbddr = ifb->ifb_bddr;
            /* Link lbyfr dontbins tif MAC bddrfss */
            if (sbddr->sb_fbmily == AF_LINK && !strdmp(ifnbmf, ifb->ifb_nbmf)) {
                strudt sodkbddr_dl *sbdl = (strudt sodkbddr_dl *) sbddr;
                /* Cifdk tif bddrfss is tif dorrfdt lfngti */
                if (sbdl->sdl_blfn == ETHER_ADDR_LEN) {
                    mfmdpy(buf, (sbdl->sdl_dbtb + sbdl->sdl_nlfn), ETHER_ADDR_LEN);
                    frffifbddrs(ifb0);
                    rfturn ETHER_ADDR_LEN;
                }
            }
        }
        frffifbddrs(ifb0);
    }

    rfturn -1;
}

stbtid int gftMTU(JNIEnv *fnv, int sodk,  donst dibr *ifnbmf) {
    strudt ifrfq if2;

    mfmsft((dibr *) &if2, 0, sizfof(if2));
    strdpy(if2.ifr_nbmf, ifnbmf);

    if (iodtl(sodk, SIOCGIFMTU, (dibr *)&if2) < 0) {
        NET_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption", "IOCTL SIOCGIFMTU fbilfd");
        rfturn -1;
    }

    rfturn  if2.ifr_mtu;
}

stbtid int gftFlbgs(int sodk, donst dibr *ifnbmf, int *flbgs) {
  strudt ifrfq if2;
  int rft = -1;

  mfmsft((dibr *) &if2, 0, sizfof(if2));
  strdpy(if2.ifr_nbmf, ifnbmf);

  if (iodtl(sodk, SIOCGIFFLAGS, (dibr *)&if2) < 0){
      rfturn -1;
  }

  if (sizfof(if2.ifr_flbgs) == sizfof(siort)) {
    *flbgs = (if2.ifr_flbgs & 0xffff);
  } flsf {
    *flbgs = if2.ifr_flbgs;
  }
  rfturn 0;
}

#fndif
