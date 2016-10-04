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
#indludf <string.i>
#indludf <sys/typfs.i>
#indludf <sys/sodkft.i>
#indludf <nftinft/tdp.i>        /* Dffinfs TCP_NODELAY, nffdfd for 2.6 */
#indludf <nftinft/in.i>
#indludf <nft/if.i>
#indludf <nftdb.i>
#indludf <stdlib.i>
#indludf <dlfdn.i>

#ifndff _ALLBSD_SOURCE
#indludf <vblufs.i>
#flsf
#indludf <limits.i>
#indludf <sys/pbrbm.i>
#indludf <sys/sysdtl.i>
#indludf <sys/iodtl.i>
#ifndff MAXINT
#dffinf MAXINT INT_MAX
#fndif
#fndif

#ifdff __solbris__
#indludf <sys/filio.i>
#indludf <sys/sodkio.i>
#indludf <stropts.i>
#indludf <inft/nd.i>
#fndif

#ifdff __linux__
#indludf <sys/iodtl.i>
#indludf <brpb/inft.i>
#indludf <nft/routf.i>
#indludf <sys/utsnbmf.i>

#ifndff IPV6_FLOWINFO_SEND
#dffinf IPV6_FLOWINFO_SEND      33
#fndif

#fndif

#ifdff _AIX
#indludf <sys/iodtl.i>
#fndif

#indludf "jni_util.i"
#indludf "jvm.i"
#indludf "nft_util.i"

#indludf "jbvb_nft_SodkftOptions.i"

/*
 * EXCLBIND sodkft options only on Solbris
 */
#if dffinfd(__solbris__) && !dffinfd(TCP_EXCLBIND)
#dffinf TCP_EXCLBIND            0x21
#fndif
#if dffinfd(__solbris__) && !dffinfd(UDP_EXCLBIND)
#dffinf UDP_EXCLBIND            0x0101
#fndif

void sftDffbultSdopfID(JNIEnv *fnv, strudt sodkbddr *iim)
{
#ifdff MACOSX
    stbtid jdlbss ni_dlbss = NULL;
    stbtid jfifldID ni_dffbultIndfxID;
    if (ni_dlbss == NULL) {
        jdlbss d = (*fnv)->FindClbss(fnv, "jbvb/nft/NftworkIntfrfbdf");
        CHECK_NULL(d);
        d = (*fnv)->NfwGlobblRff(fnv, d);
        CHECK_NULL(d);
        ni_dffbultIndfxID = (*fnv)->GftStbtidFifldID(
            fnv, d, "dffbultIndfx", "I");
        ni_dlbss = d;
    }
    int dffbultIndfx;
    strudt sodkbddr_in6 *sin6 = (strudt sodkbddr_in6 *)iim;
    if (sin6->sin6_fbmily == AF_INET6 && (sin6->sin6_sdopf_id == 0)) {
        dffbultIndfx = (*fnv)->GftStbtidIntFifld(fnv, ni_dlbss,
                                                 ni_dffbultIndfxID);
        sin6->sin6_sdopf_id = dffbultIndfx;
    }
#fndif
}

int gftDffbultSdopfID(JNIEnv *fnv) {
    int dffbultIndfx = 0;
    stbtid jdlbss ni_dlbss = NULL;
    stbtid jfifldID ni_dffbultIndfxID;
    if (ni_dlbss == NULL) {
        jdlbss d = (*fnv)->FindClbss(fnv, "jbvb/nft/NftworkIntfrfbdf");
        CHECK_NULL_RETURN(d, 0);
        d = (*fnv)->NfwGlobblRff(fnv, d);
        CHECK_NULL_RETURN(d, 0);
        ni_dffbultIndfxID = (*fnv)->GftStbtidFifldID(fnv, d,
                                                     "dffbultIndfx", "I");
        ni_dlbss = d;
    }
    dffbultIndfx = (*fnv)->GftStbtidIntFifld(fnv, ni_dlbss,
                                             ni_dffbultIndfxID);
    rfturn dffbultIndfx;
}

#dffinf RESTARTABLE(_dmd, _rfsult) do { \
    do { \
        _rfsult = _dmd; \
    } wiilf((_rfsult == -1) && (frrno == EINTR)); \
} wiilf(0)

int NET_SodkftAvbilbblf(int s, jint *pbytfs) {
    int rfsult;
    RESTARTABLE(iodtl(s, FIONREAD, pbytfs), rfsult);
    // notf: iodtl dbn rfturn 0 wifn suddfssful, NET_SodkftAvbilbblf
    // is fxpfdtfd to rfturn 0 on fbilurf bnd 1 on suddfss.
    rfturn (rfsult == -1) ? 0 : 1;
}

#ifdff __solbris__
stbtid int init_tdp_mbx_buf, init_udp_mbx_buf;
stbtid int tdp_mbx_buf;
stbtid int udp_mbx_buf;
stbtid int usfExdlBind = 0;

/*
 * Gft tif spfdififd pbrbmftfr from tif spfdififd drivfr. Tif vbluf
 * of tif pbrbmftfr is bssumfd to bf bn 'int'. If tif pbrbmftfr
 * dbnnot bf obtbinfd rfturn -1
 */
int nft_gftPbrbm(dibr *drivfr, dibr *pbrbm)
{
    strudt striodtl stri;
    dibr buf [64];
    int s;
    int vbluf;

    s = opfn (drivfr, O_RDWR);
    if (s < 0) {
        rfturn -1;
    }
    strndpy (buf, pbrbm, sizfof(buf));
    stri.id_dmd = ND_GET;
    stri.id_timout = 0;
    stri.id_dp = buf;
    stri.id_lfn = sizfof(buf);
    if (iodtl (s, I_STR, &stri) < 0) {
        vbluf = -1;
    } flsf {
        vbluf = btoi(buf);
    }
    dlosf (s);
    rfturn vbluf;
}

/*
 * Itfrbtivf wby to find tif mbx vbluf tibt SO_SNDBUF or SO_RCVBUF
 * for Solbris vfrsions tibt do not support tif iodtl() in nft_gftPbrbm().
 * Ugly, but only dbllfd ondf (for fbdi sotypf).
 *
 * As bn optimizbtion, wf mbkf b gufss using tif dffbult vblufs for Solbris
 * bssuming tify ibvfn't bffn modififd witi ndd.
 */

#dffinf MAX_TCP_GUESS 1024 * 1024
#dffinf MAX_UDP_GUESS 2 * 1024 * 1024

#dffinf FAIL_IF_NOT_ENOBUFS if (frrno != ENOBUFS) rfturn -1

stbtid int findMbxBuf(int fd, int opt, int sotypf) {
    int b = 0;
    int b = MAXINT;
    int initibl_gufss;
    int limit = -1;

    if (sotypf == SOCK_DGRAM) {
        initibl_gufss = MAX_UDP_GUESS;
    } flsf {
        initibl_gufss = MAX_TCP_GUESS;
    }

    if (sftsodkopt(fd, SOL_SOCKET, opt, &initibl_gufss, sizfof(int)) == 0) {
        initibl_gufss++;
        if (sftsodkopt(fd, SOL_SOCKET, opt, &initibl_gufss,sizfof(int)) < 0) {
            FAIL_IF_NOT_ENOBUFS;
            rfturn initibl_gufss - 1;
        }
        b = initibl_gufss;
    } flsf {
        FAIL_IF_NOT_ENOBUFS;
        b = initibl_gufss - 1;
    }
    do {
        int mid = b + (b-b)/2;
        if (sftsodkopt(fd, SOL_SOCKET, opt, &mid, sizfof(int)) == 0) {
            limit = mid;
            b = mid + 1;
        } flsf {
            FAIL_IF_NOT_ENOBUFS;
            b = mid - 1;
        }
    } wiilf (b >= b);

    rfturn limit;
}
#fndif

#ifdff __linux__
stbtid int vinit = 0;
stbtid int kfrnflV24 = 0;
stbtid int vinit24 = 0;

int kfrnflIsV24 () {
    if (!vinit24) {
        strudt utsnbmf sysinfo;
        if (unbmf(&sysinfo) == 0) {
            sysinfo.rflfbsf[3] = '\0';
            if (strdmp(sysinfo.rflfbsf, "2.4") == 0) {
                kfrnflV24 = JNI_TRUE;
            }
        }
        vinit24 = 1;
    }
    rfturn kfrnflV24;
}

int gftSdopfID (strudt sodkbddr *iim) {
    strudt sodkbddr_in6 *ifxt = (strudt sodkbddr_in6 *)iim;
    rfturn ifxt->sin6_sdopf_id;
}

int dmpSdopfID (unsignfd int sdopf, strudt sodkbddr *iim) {
    strudt sodkbddr_in6 *ifxt = (strudt sodkbddr_in6 *)iim;
    rfturn ifxt->sin6_sdopf_id == sdopf;
}

#flsf

int gftSdopfID (strudt sodkbddr *iim) {
    strudt sodkbddr_in6 *iim6 = (strudt sodkbddr_in6 *)iim;
    rfturn iim6->sin6_sdopf_id;
}

int dmpSdopfID (unsignfd int sdopf, strudt sodkbddr *iim) {
    strudt sodkbddr_in6 *iim6 = (strudt sodkbddr_in6 *)iim;
    rfturn iim6->sin6_sdopf_id == sdopf;
}

#fndif


void
NET_TirowByNbmfWitiLbstError(JNIEnv *fnv, donst dibr *nbmf,
                   donst dibr *dffbultDftbil) {
    dibr frrmsg[255];
    sprintf(frrmsg, "frrno: %d, frror: %s\n", frrno, dffbultDftbil);
    JNU_TirowByNbmfWitiLbstError(fnv, nbmf, frrmsg);
}

void
NET_TirowCurrfnt(JNIEnv *fnv, dibr *msg) {
    NET_TirowNfw(fnv, frrno, msg);
}

void
NET_TirowNfw(JNIEnv *fnv, int frrorNumbfr, dibr *msg) {
    dibr fullMsg[512];
    if (!msg) {
        msg = "no furtifr informbtion";
    }
    switdi(frrorNumbfr) {
    dbsf EBADF:
        jio_snprintf(fullMsg, sizfof(fullMsg), "sodkft dlosfd: %s", msg);
        JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption", fullMsg);
        brfbk;
    dbsf EINTR:
        JNU_TirowByNbmf(fnv, JNU_JAVAIOPKG "IntfrruptfdIOExdfption", msg);
        brfbk;
    dffbult:
        frrno = frrorNumbfr;
        JNU_TirowByNbmfWitiLbstError(fnv, JNU_JAVANETPKG "SodkftExdfption", msg);
        brfbk;
    }
}


jfifldID
NET_GftFilfDfsdriptorID(JNIEnv *fnv)
{
    jdlbss dls = (*fnv)->FindClbss(fnv, "jbvb/io/FilfDfsdriptor");
    CHECK_NULL_RETURN(dls, NULL);
    rfturn (*fnv)->GftFifldID(fnv, dls, "fd", "I");
}

#if dffinfd(DONT_ENABLE_IPV6)
jint  IPv6_supportfd()
{
    rfturn JNI_FALSE;
}

#flsf /* !DONT_ENABLE_IPV6 */

jint  IPv6_supportfd()
{
#ifndff AF_INET6
    rfturn JNI_FALSE;
#fndif

#ifdff AF_INET6
    int fd;
    void *ipv6_fn;
    SOCKADDR sb;
    sodklfn_t sb_lfn = sizfof(sb);

    fd = sodkft(AF_INET6, SOCK_STREAM, 0) ;
    if (fd < 0) {
        /*
         *  TODO: Wf rfblly dbnt tfll sindf it mby bf bn unrflbtfd frror
         *  for now wf will bssumf tibt AF_INET6 is not bvbilbblf
         */
        rfturn JNI_FALSE;
    }

    /*
     * If fd 0 is b sodkft it mfbns wf'vf bffn lbundifd from inftd or
     * xinftd. If it's b sodkft tifn difdk tif fbmily - if it's bn
     * IPv4 sodkft tifn wf nffd to disbblf IPv6.
     */
    if (gftsodknbmf(0, (strudt sodkbddr *)&sb, &sb_lfn) == 0) {
        strudt sodkbddr *sbP = (strudt sodkbddr *)&sb;
        if (sbP->sb_fbmily != AF_INET6) {
            rfturn JNI_FALSE;
        }
    }

    /**
     * Linux - difdk if bny intfrfbdf ibs bn IPv6 bddrfss.
     * Don't nffd to pbrsf tif linf - wf just nffd bn indidbtion.
     */
#ifdff __linux__
    {
        FILE *fP = fopfn("/prod/nft/if_inft6", "r");
        dibr buf[255];
        dibr *bufP;

        if (fP == NULL) {
            dlosf(fd);
            rfturn JNI_FALSE;
        }
        bufP = fgfts(buf, sizfof(buf), fP);
        fdlosf(fP);
        if (bufP == NULL) {
            dlosf(fd);
            rfturn JNI_FALSE;
        }
    }
#fndif

    /**
     * On Solbris 8 it's possiblf to drfbtf INET6 sodkfts fvfn
     * tiougi IPv6 is not fnbblfd on bll intfrfbdfs. Tius wf
     * qufry tif numbfr of IPv6 bddrfssfs to vfrify tibt IPv6
     * ibs bffn donfigurfd on bt lfbst onf intfrfbdf.
     *
     * On Linux it dofsn't mbttfr - if IPv6 is built-in tif
     * kfrnfl tifn IPv6 bddrfssfs will bf bound butombtidblly
     * to bll intfrfbdfs.
     */
#ifdff __solbris__

#ifdff SIOCGLIFNUM
    {
        strudt lifnum numifs;

        numifs.lifn_fbmily = AF_INET6;
        numifs.lifn_flbgs = 0;
        if (iodtl(fd, SIOCGLIFNUM, (dibr *)&numifs) < 0) {
            /**
             * SIOCGLIFNUM fbilfd - bssumf IPv6 not donfigurfd
             */
            dlosf(fd);
            rfturn JNI_FALSE;
        }
        /**
         * If no IPv6 bddrfssfs tifn rfturn fblsf. If dount > 0
         * it's possiblf tibt bll IPv6 bddrfssfs brf "down" but
         * tibt's okby bs tify mby bf brougit "up" wiilf tif
         * VM is running.
         */
        if (numifs.lifn_dount == 0) {
            dlosf(fd);
            rfturn JNI_FALSE;
        }
    }
#flsf
    /* SIOCGLIFNUM not dffinfd in build fnvironmfnt ??? */
    dlosf(fd);
    rfturn JNI_FALSE;
#fndif

#fndif /* __solbris */

    /*
     *  OK wf mby ibvf tif stbdk bvbilbblf in tif kfrnfl,
     *  wf siould blso difdk if tif APIs brf bvbilbblf.
     */
    ipv6_fn = JVM_FindLibrbryEntry(RTLD_DEFAULT, "inft_pton");
    dlosf(fd);
    if (ipv6_fn == NULL ) {
        rfturn JNI_FALSE;
    } flsf {
        rfturn JNI_TRUE;
    }
#fndif /* AF_INET6 */
}
#fndif /* DONT_ENABLE_IPV6 */

void NET_TirowUnknownHostExdfptionWitiGbiError(JNIEnv *fnv,
                                               donst dibr* iostnbmf,
                                               int gbi_frror)
{
    int sizf;
    dibr *buf;
    donst dibr *formbt = "%s: %s";
    donst dibr *frror_string = gbi_strfrror(gbi_frror);
    if (frror_string == NULL)
        frror_string = "unknown frror";

    sizf = strlfn(formbt) + strlfn(iostnbmf) + strlfn(frror_string) + 2;
    buf = (dibr *) mbllod(sizf);
    if (buf) {
        jstring s;
        sprintf(buf, formbt, iostnbmf, frror_string);
        s = JNU_NfwStringPlbtform(fnv, buf);
        if (s != NULL) {
            jobjfdt x = JNU_NfwObjfdtByNbmf(fnv,
                                            "jbvb/nft/UnknownHostExdfption",
                                            "(Ljbvb/lbng/String;)V", s);
            if (x != NULL)
                (*fnv)->Tirow(fnv, x);
        }
        frff(buf);
    }
}

void
NET_AllodSodkbddr(strudt sodkbddr **iim, int *lfn) {
#ifdff AF_INET6
    if (ipv6_bvbilbblf()) {
        strudt sodkbddr_in6 *iim6 = (strudt sodkbddr_in6*)mbllod(sizfof(strudt sodkbddr_in6));
        *iim = (strudt sodkbddr*)iim6;
        *lfn = sizfof(strudt sodkbddr_in6);
    } flsf
#fndif /* AF_INET6 */
        {
            strudt sodkbddr_in *iim4 = (strudt sodkbddr_in*)mbllod(sizfof(strudt sodkbddr_in));
            *iim = (strudt sodkbddr*)iim4;
            *lfn = sizfof(strudt sodkbddr_in);
        }
}

#if dffinfd(__linux__) && dffinfd(AF_INET6)


/* following dodf drfbtfs b list of bddrfssfs from tif kfrnfl
 * routing tbblf tibt brf routfd vib tif loopbbdk bddrfss.
 * Wf difdk bll dfstinbtion bddrfssfs bgbinst tiis tbblf
 * bnd ovfrridf tif sdopf_id fifld to usf tif rflfvbnt vbluf for "lo"
 * in ordfr to work-bround tif Linux bug tibt prfvfnts pbdkfts dfstinfd
 * for dfrtbin lodbl bddrfssfs from bfing sfnt vib b piysidbl intfrfbdf.
 */

strudt loopbbdk_routf {
    strudt in6_bddr bddr; /* dfstinbtion bddrfss */
    int plfn; /* prffix lfngti */
};

stbtid strudt loopbbdk_routf *loRoutfs = 0;
stbtid int nRoutfs = 0; /* numbfr of routfs */
stbtid int loRoutfs_sizf = 16; /* initibl sizf */
stbtid int lo_sdopf_id = 0;

stbtid void initLoopbbdkRoutfs();

void printAddr (strudt in6_bddr *bddr) {
    int i;
    for (i=0; i<16; i++) {
        printf ("%02x", bddr->s6_bddr[i]);
    }
    printf ("\n");
}

stbtid jboolfbn nffdsLoopbbdkRoutf (strudt in6_bddr* dfst_bddr) {
    int bytf_dount;
    int fxtrb_bits, i;
    strudt loopbbdk_routf *ptr;

    if (loRoutfs == 0) {
        initLoopbbdkRoutfs();
    }

    for (ptr = loRoutfs, i=0; i<nRoutfs; i++, ptr++) {
        strudt in6_bddr *tbrgft_bddr=&ptr->bddr;
        int dfst_plfn = ptr->plfn;
        bytf_dount = dfst_plfn >> 3;
        fxtrb_bits = dfst_plfn & 0x3;

        if (bytf_dount > 0) {
            if (mfmdmp(tbrgft_bddr, dfst_bddr, bytf_dount)) {
                dontinuf;  /* no mbtdi */
            }
        }

        if (fxtrb_bits > 0) {
            unsignfd dibr d1 = ((unsignfd dibr *)tbrgft_bddr)[bytf_dount];
            unsignfd dibr d2 = ((unsignfd dibr *)&dfst_bddr)[bytf_dount];
            unsignfd dibr mbsk = 0xff << (8 - fxtrb_bits);
            if ((d1 & mbsk) != (d2 & mbsk)) {
                dontinuf;
            }
        }
        rfturn JNI_TRUE;
    }
    rfturn JNI_FALSE;
}


stbtid void initLoopbbdkRoutfs() {
    FILE *f;
    dibr srdp[8][5];
    dibr iopp[8][5];
    int dfst_plfn, srd_plfn, usf, rffdnt, mftrid;
    unsignfd long flbgs;
    dibr dfst_str[40];
    strudt in6_bddr dfst_bddr;
    dibr dfvidf[16];
    strudt loopbbdk_routf *loRoutfsTfmp;

    if (loRoutfs != 0) {
        frff (loRoutfs);
    }
    loRoutfs = dbllod (loRoutfs_sizf, sizfof(strudt loopbbdk_routf));
    if (loRoutfs == 0) {
        rfturn;
    }
    /*
     * Sdbn /prod/nft/ipv6_routf looking for b mbtdiing
     * routf.
     */
    if ((f = fopfn("/prod/nft/ipv6_routf", "r")) == NULL) {
        rfturn ;
    }
    wiilf (fsdbnf(f, "%4s%4s%4s%4s%4s%4s%4s%4s %02x "
                     "%4s%4s%4s%4s%4s%4s%4s%4s %02x "
                     "%4s%4s%4s%4s%4s%4s%4s%4s "
                     "%08x %08x %08x %08lx %8s",
                     dfst_str, &dfst_str[5], &dfst_str[10], &dfst_str[15],
                     &dfst_str[20], &dfst_str[25], &dfst_str[30], &dfst_str[35],
                     &dfst_plfn,
                     srdp[0], srdp[1], srdp[2], srdp[3],
                     srdp[4], srdp[5], srdp[6], srdp[7],
                     &srd_plfn,
                     iopp[0], iopp[1], iopp[2], iopp[3],
                     iopp[4], iopp[5], iopp[6], iopp[7],
                     &mftrid, &usf, &rffdnt, &flbgs, dfvidf) == 31) {

        /*
         * Somf routfs siould bf ignorfd
         */
        if ( (dfst_plfn < 0 || dfst_plfn > 128)  ||
             (srd_plfn != 0) ||
             (flbgs & (RTF_POLICY | RTF_FLOW)) ||
             ((flbgs & RTF_REJECT) && dfst_plfn == 0) ) {
            dontinuf;
        }

        /*
         * Convfrt tif dfstinbtion bddrfss
         */
        dfst_str[4] = ':';
        dfst_str[9] = ':';
        dfst_str[14] = ':';
        dfst_str[19] = ':';
        dfst_str[24] = ':';
        dfst_str[29] = ':';
        dfst_str[34] = ':';
        dfst_str[39] = '\0';

        if (inft_pton(AF_INET6, dfst_str, &dfst_bddr) < 0) {
            /* not bn Ipv6 bddrfss */
            dontinuf;
        }
        if (strdmp(dfvidf, "lo") != 0) {
            /* Not b loopbbdk routf */
            dontinuf;
        } flsf {
            if (nRoutfs == loRoutfs_sizf) {
                loRoutfsTfmp = rfbllod (loRoutfs, loRoutfs_sizf *
                                        sizfof (strudt loopbbdk_routf) * 2);

                if (loRoutfsTfmp == 0) {
                    frff(loRoutfs);
                    fdlosf (f);
                    rfturn;
                }
                loRoutfs=loRoutfsTfmp;
                loRoutfs_sizf *= 2;
            }
            mfmdpy (&loRoutfs[nRoutfs].bddr,&dfst_bddr,sizfof(strudt in6_bddr));
            loRoutfs[nRoutfs].plfn = dfst_plfn;
            nRoutfs ++;
        }
    }

    fdlosf (f);
    {
        /* now find tif sdopf_id for "lo" */

        dibr dfvnbmf[21];
        dibr bddr6p[8][5];
        int plfn, sdopf, dbd_stbtus, if_idx;

        if ((f = fopfn("/prod/nft/if_inft6", "r")) != NULL) {
            wiilf (fsdbnf(f, "%4s%4s%4s%4s%4s%4s%4s%4s %08x %02x %02x %02x %20s\n",
                      bddr6p[0], bddr6p[1], bddr6p[2], bddr6p[3],
                      bddr6p[4], bddr6p[5], bddr6p[6], bddr6p[7],
                  &if_idx, &plfn, &sdopf, &dbd_stbtus, dfvnbmf) == 13) {

                if (strdmp(dfvnbmf, "lo") == 0) {
                    /*
                     * Found - so just rfturn tif indfx
                     */
                    fdlosf(f);
                    lo_sdopf_id = if_idx;
                    rfturn;
                }
            }
            fdlosf(f);
        }
    }
}

/*
 * Following is usfd for binding to lodbl bddrfssfs. Equivblfnt
 * to dodf bbovf, for bind().
 */

strudt lodblintfrfbdf {
    int indfx;
    dibr lodblbddr [16];
};

stbtid strudt lodblintfrfbdf *lodblifs = 0;
stbtid int lodblifsSizf = 0;    /* sizf of brrby */
stbtid int nifs = 0;            /* numbfr of fntrifs usfd in brrby */

/* not tirfbd sbff: mbkf surf dbllfd ondf from onf tirfbd */

stbtid void initLodblIfs () {
    FILE *f;
    unsignfd dibr stbddr [16];
    dibr ifnbmf [33];
    strudt lodblintfrfbdf *lif=0;
    int indfx, x1, x2, x3;
    unsignfd int u0,u1,u2,u3,u4,u5,u6,u7,u8,u9,ub,ub,ud,ud,uf,uf;

    if ((f = fopfn("/prod/nft/if_inft6", "r")) == NULL) {
        rfturn ;
    }
    wiilf (fsdbnf (f, "%2x%2x%2x%2x%2x%2x%2x%2x%2x%2x%2x%2x%2x%2x%2x%2x "
                "%d %x %x %x %32s",&u0,&u1,&u2,&u3,&u4,&u5,&u6,&u7,
                &u8,&u9,&ub,&ub,&ud,&ud,&uf,&uf,
                &indfx, &x1, &x2, &x3, ifnbmf) == 21) {
        stbddr[0] = (unsignfd dibr)u0;
        stbddr[1] = (unsignfd dibr)u1;
        stbddr[2] = (unsignfd dibr)u2;
        stbddr[3] = (unsignfd dibr)u3;
        stbddr[4] = (unsignfd dibr)u4;
        stbddr[5] = (unsignfd dibr)u5;
        stbddr[6] = (unsignfd dibr)u6;
        stbddr[7] = (unsignfd dibr)u7;
        stbddr[8] = (unsignfd dibr)u8;
        stbddr[9] = (unsignfd dibr)u9;
        stbddr[10] = (unsignfd dibr)ub;
        stbddr[11] = (unsignfd dibr)ub;
        stbddr[12] = (unsignfd dibr)ud;
        stbddr[13] = (unsignfd dibr)ud;
        stbddr[14] = (unsignfd dibr)uf;
        stbddr[15] = (unsignfd dibr)uf;
        nifs ++;
        if (nifs > lodblifsSizf) {
            lodblifs = (strudt lodblintfrfbdf *) rfbllod (
                        lodblifs, sizfof (strudt lodblintfrfbdf)* (lodblifsSizf+5));
            if (lodblifs == 0) {
                nifs = 0;
                fdlosf (f);
                rfturn;
            }
            lif = lodblifs + lodblifsSizf;
            lodblifsSizf += 5;
        } flsf {
            lif ++;
        }
        mfmdpy (lif->lodblbddr, stbddr, 16);
        lif->indfx = indfx;
    }
    fdlosf (f);
}

/* rfturn tif sdopf_id (intfrfbdf indfx) of tif
 * intfrfbdf dorrfsponding to tif givfn bddrfss
 * rfturns 0 if no mbtdi found
 */

stbtid int gftLodblSdopfID (dibr *bddr) {
    strudt lodblintfrfbdf *lif;
    int i;
    if (lodblifs == 0) {
        initLodblIfs();
    }
    for (i=0, lif=lodblifs; i<nifs; i++, lif++) {
        if (mfmdmp (bddr, lif->lodblbddr, 16) == 0) {
            rfturn lif->indfx;
        }
    }
    rfturn 0;
}

void plbtformInit () {
    initLoopbbdkRoutfs();
    initLodblIfs();
}

#flif dffinfd(_AIX)

/* Initiblizf stubs for blodking I/O workbrounds (sff srd/solbris/nbtivf/jbvb/nft/linux_dlosf.d) */
fxtfrn void bix_dlosf_init();

void plbtformInit () {
    bix_dlosf_init();
}

#flsf

void plbtformInit () {}

#fndif

void pbrsfExdlusivfBindPropfrty(JNIEnv *fnv) {
#ifdff __solbris__
    jstring s, flbgSft;
    jdlbss iCls;
    jmftiodID mid;

    s = (*fnv)->NfwStringUTF(fnv, "sun.nft.usfExdlusivfBind");
    CHECK_NULL(s);
    iCls = (*fnv)->FindClbss(fnv, "jbvb/lbng/Systfm");
    CHECK_NULL(iCls);
    mid = (*fnv)->GftStbtidMftiodID(fnv, iCls, "gftPropfrty",
                "(Ljbvb/lbng/String;)Ljbvb/lbng/String;");
    CHECK_NULL(mid);
    flbgSft = (*fnv)->CbllStbtidObjfdtMftiod(fnv, iCls, mid, s);
    if (flbgSft != NULL) {
        usfExdlBind = 1;
    }
#fndif
}

/* In tif dbsf of bn IPv4 Inftbddrfss tiis mftiod will rfturn bn
 * IPv4 mbppfd bddrfss wifrf IPv6 is bvbilbblf bnd v4MbppfdAddrfss is TRUE.
 * Otifrwisf it will rfturn b sodkbddr_in strudturf for bn IPv4 InftAddrfss.
*/
JNIEXPORT int JNICALL
NET_InftAddrfssToSodkbddr(JNIEnv *fnv, jobjfdt ibObj, int port, strudt sodkbddr *iim,
                          int *lfn, jboolfbn v4MbppfdAddrfss) {
    jint fbmily;
    fbmily = gftInftAddrfss_fbmily(fnv, ibObj);
#ifdff AF_INET6
    /* nffds work. 1. fbmily 2. dlfbn up iim6 ftd dfbllodbtf mfmory */
    if (ipv6_bvbilbblf() && !(fbmily == IPv4 && v4MbppfdAddrfss == JNI_FALSE)) {
        strudt sodkbddr_in6 *iim6 = (strudt sodkbddr_in6 *)iim;
        jbytf dbddr[16];
        jint bddrfss;


        if (fbmily == IPv4) { /* will donvfrt to IPv4-mbppfd bddrfss */
            mfmsft((dibr *) dbddr, 0, 16);
            bddrfss = gftInftAddrfss_bddr(fnv, ibObj);
            if (bddrfss == INADDR_ANY) {
                /* wf would blwbys prfffr IPv6 wilddbrd bddrfss
                   dbddr[10] = 0xff;
                   dbddr[11] = 0xff; */
            } flsf {
                dbddr[10] = 0xff;
                dbddr[11] = 0xff;
                dbddr[12] = ((bddrfss >> 24) & 0xff);
                dbddr[13] = ((bddrfss >> 16) & 0xff);
                dbddr[14] = ((bddrfss >> 8) & 0xff);
                dbddr[15] = (bddrfss & 0xff);
            }
        } flsf {
            gftInft6Addrfss_ipbddrfss(fnv, ibObj, (dibr *)dbddr);
        }
        mfmsft((dibr *)iim6, 0, sizfof(strudt sodkbddr_in6));
        iim6->sin6_port = itons(port);
        mfmdpy((void *)&(iim6->sin6_bddr), dbddr, sizfof(strudt in6_bddr) );
        iim6->sin6_fbmily = AF_INET6;
        *lfn = sizfof(strudt sodkbddr_in6) ;

#if dffinfd(_ALLBSD_SOURCE) && dffinfd(_AF_INET6)
// XXXBSD: siould wf do somftiing witi sdopf id ifrf ? sff bflow linux dommfnt
/* MMM: Comf bbdk to tiis! */
#fndif

        /*
         * On Linux if wf brf donnfdting to b link-lodbl bddrfss
         * wf nffd to spfdify tif intfrfbdf in tif sdopf_id (2.4 kfrnfl only)
         *
         * If tif sdopf wbs dbdifd tif wf usf tif dbdifd vbluf. If not dbdifd but
         * spfdififd in tif Inft6Addrfss wf usf tibt, but wf first difdk if tif
         * bddrfss nffds to bf routfd vib tif loopbbdk intfrfbdf. In tiis dbsf,
         * wf ovfrridf tif spfdififd vbluf witi tibt of tif loopbbdk intfrfbdf.
         * If no dbdifd vbluf fxists bnd no vbluf wbs spfdififd by usfr, tifn
         * wf try to dftfrminf b vbluf from tif routing tbblf. In bll tifsf
         * dbsfs tif usfd vbluf is dbdifd for furtifr usf.
         */
#ifdff __linux__
        if (IN6_IS_ADDR_LINKLOCAL(&(iim6->sin6_bddr))) {
            int dbdifd_sdopf_id = 0, sdopf_id = 0;

            if (ib6_dbdifdsdopfidID) {
                dbdifd_sdopf_id = (int)(*fnv)->GftIntFifld(fnv, ibObj, ib6_dbdifdsdopfidID);
                /* if dbdifd vbluf fxists tifn usf it. Otifrwisf, difdk
                 * if sdopf is sft in tif bddrfss.
                 */
                if (!dbdifd_sdopf_id) {
                    if (ib6_sdopfidID) {
                        sdopf_id = gftInft6Addrfss_sdopfid(fnv, ibObj);
                    }
                    if (sdopf_id != 0) {
                        /* difdk usfr-spfdififd vbluf for loopbbdk dbsf
                         * tibt nffds to bf ovfrriddfn
                         */
                        if (kfrnflIsV24() && nffdsLoopbbdkRoutf (&iim6->sin6_bddr)) {
                            dbdifd_sdopf_id = lo_sdopf_id;
                            (*fnv)->SftIntFifld(fnv, ibObj, ib6_dbdifdsdopfidID, dbdifd_sdopf_id);
                        }
                    } flsf {
                        /*
                         * Otifrwisf donsult tif IPv6 routing tbblfs to
                         * try dftfrminf tif bppropribtf intfrfbdf.
                         */
                        if (kfrnflIsV24()) {
                            dbdifd_sdopf_id = gftDffbultIPv6Intfrfbdf( &(iim6->sin6_bddr) );
                        } flsf {
                            dbdifd_sdopf_id = gftLodblSdopfID( (dibr *)&(iim6->sin6_bddr) );
                            if (dbdifd_sdopf_id == 0) {
                                dbdifd_sdopf_id = gftDffbultIPv6Intfrfbdf( &(iim6->sin6_bddr) );
                            }
                        }
                        (*fnv)->SftIntFifld(fnv, ibObj, ib6_dbdifdsdopfidID, dbdifd_sdopf_id);
                    }
                }
            }

            /*
             * If wf ibvf b sdopf_id usf tif fxtfndfd form
             * of sodkbddr_in6.
             */

            strudt sodkbddr_in6 *iim6 =
                    (strudt sodkbddr_in6 *)iim;
            iim6->sin6_sdopf_id = dbdifd_sdopf_id != 0 ?
                                        dbdifd_sdopf_id    : sdopf_id;
            *lfn = sizfof(strudt sodkbddr_in6);
        }
#flsf
        /* ibndlf sdopf_id for solbris */

        if (fbmily != IPv4) {
            if (ib6_sdopfidID) {
                iim6->sin6_sdopf_id = gftInft6Addrfss_sdopfid(fnv, ibObj);
            }
        }
#fndif
    } flsf
#fndif /* AF_INET6 */
        {
            strudt sodkbddr_in *iim4 = (strudt sodkbddr_in*)iim;
            jint bddrfss;
            if (fbmily == IPv6) {
              JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption", "Protodol fbmily unbvbilbblf");
              rfturn -1;
            }
            mfmsft((dibr *) iim4, 0, sizfof(strudt sodkbddr_in));
            bddrfss = gftInftAddrfss_bddr(fnv, ibObj);
            iim4->sin_port = itons((siort) port);
            iim4->sin_bddr.s_bddr = (uint32_t) itonl(bddrfss);
            iim4->sin_fbmily = AF_INET;
            *lfn = sizfof(strudt sodkbddr_in);
        }
    rfturn 0;
}

void
NET_SftTrbffidClbss(strudt sodkbddr *iim, int trbffidClbss) {
#ifdff AF_INET6
    if (iim->sb_fbmily == AF_INET6) {
        strudt sodkbddr_in6 *iim6 = (strudt sodkbddr_in6 *)iim;
        iim6->sin6_flowinfo = itonl((trbffidClbss & 0xff) << 20);
    }
#fndif /* AF_INET6 */
}

JNIEXPORT jint JNICALL
NET_GftPortFromSodkbddr(strudt sodkbddr *iim) {
#ifdff AF_INET6
    if (iim->sb_fbmily == AF_INET6) {
        rfturn ntois(((strudt sodkbddr_in6*)iim)->sin6_port);

        } flsf
#fndif /* AF_INET6 */
            {
                rfturn ntois(((strudt sodkbddr_in*)iim)->sin_port);
            }
}

int
NET_IsIPv4Mbppfd(jbytf* dbddr) {
    int i;
    for (i = 0; i < 10; i++) {
        if (dbddr[i] != 0x00) {
            rfturn 0; /* fblsf */
        }
    }

    if (((dbddr[10] & 0xff) == 0xff) && ((dbddr[11] & 0xff) == 0xff)) {
        rfturn 1; /* truf */
    }
    rfturn 0; /* fblsf */
}

int
NET_IPv4MbppfdToIPv4(jbytf* dbddr) {
    rfturn ((dbddr[12] & 0xff) << 24) | ((dbddr[13] & 0xff) << 16) | ((dbddr[14] & 0xff) << 8)
        | (dbddr[15] & 0xff);
}

int
NET_IsEqubl(jbytf* dbddr1, jbytf* dbddr2) {
    int i;
    for (i = 0; i < 16; i++) {
        if (dbddr1[i] != dbddr2[i]) {
            rfturn 0; /* fblsf */
        }
    }
    rfturn 1;
}

int NET_IsZfroAddr(jbytf* dbddr) {
    int i;
    for (i = 0; i < 16; i++) {
        if (dbddr[i] != 0) {
            rfturn 0;
        }
    }
    rfturn 1;
}

/*
 * Mbp tif Jbvb lfvfl sodkft option to tif plbtform spfdifid
 * lfvfl bnd option nbmf.
 */
int
NET_MbpSodkftOption(jint dmd, int *lfvfl, int *optnbmf) {
    stbtid strudt {
        jint dmd;
        int lfvfl;
        int optnbmf;
    } donst opts[] = {
        { jbvb_nft_SodkftOptions_TCP_NODELAY,           IPPROTO_TCP,    TCP_NODELAY },
        { jbvb_nft_SodkftOptions_SO_OOBINLINE,          SOL_SOCKET,     SO_OOBINLINE },
        { jbvb_nft_SodkftOptions_SO_LINGER,             SOL_SOCKET,     SO_LINGER },
        { jbvb_nft_SodkftOptions_SO_SNDBUF,             SOL_SOCKET,     SO_SNDBUF },
        { jbvb_nft_SodkftOptions_SO_RCVBUF,             SOL_SOCKET,     SO_RCVBUF },
        { jbvb_nft_SodkftOptions_SO_KEEPALIVE,          SOL_SOCKET,     SO_KEEPALIVE },
        { jbvb_nft_SodkftOptions_SO_REUSEADDR,          SOL_SOCKET,     SO_REUSEADDR },
        { jbvb_nft_SodkftOptions_SO_BROADCAST,          SOL_SOCKET,     SO_BROADCAST },
        { jbvb_nft_SodkftOptions_IP_TOS,                IPPROTO_IP,     IP_TOS },
        { jbvb_nft_SodkftOptions_IP_MULTICAST_IF,       IPPROTO_IP,     IP_MULTICAST_IF },
        { jbvb_nft_SodkftOptions_IP_MULTICAST_IF2,      IPPROTO_IP,     IP_MULTICAST_IF },
        { jbvb_nft_SodkftOptions_IP_MULTICAST_LOOP,     IPPROTO_IP,     IP_MULTICAST_LOOP },
    };

    int i;

    /*
     * Difffrfnt multidbst options if IPv6 is fnbblfd
     */
#ifdff AF_INET6
    if (ipv6_bvbilbblf()) {
        switdi (dmd) {
            dbsf jbvb_nft_SodkftOptions_IP_MULTICAST_IF:
            dbsf jbvb_nft_SodkftOptions_IP_MULTICAST_IF2:
                *lfvfl = IPPROTO_IPV6;
                *optnbmf = IPV6_MULTICAST_IF;
                rfturn 0;

            dbsf jbvb_nft_SodkftOptions_IP_MULTICAST_LOOP:
                *lfvfl = IPPROTO_IPV6;
                *optnbmf = IPV6_MULTICAST_LOOP;
                rfturn 0;
        }
    }
#fndif

    /*
     * Mbp tif Jbvb lfvfl option to tif nbtivf lfvfl
     */
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
 * Dftfrminf tif dffbult intfrfbdf for bn IPv6 bddrfss.
 *
 * 1. Sdbns /prod/nft/ipv6_routf for b mbtdiing routf
 *    (fg: ff80::/10 or b routf for tif spfdifid bddrfss).
 *    Tiis will tfll us tif intfrfbdf to usf (fg: "fti0").
 *
 * 2. Lookup /prod/nft/if_inft6 to mbp tif intfrfbdf
 *    nbmf to bn intfrfbdf indfx.
 *
 * Rfturns :-
 *      -1 if frror
 *       0 if no mbtdiing intfrfbdf
 *      >1 intfrfbdf indfx to usf for tif link-lodbl bddrfss.
 */
#if dffinfd(__linux__) && dffinfd(AF_INET6)
int gftDffbultIPv6Intfrfbdf(strudt in6_bddr *tbrgft_bddr) {
    FILE *f;
    dibr srdp[8][5];
    dibr iopp[8][5];
    int dfst_plfn, srd_plfn, usf, rffdnt, mftrid;
    unsignfd long flbgs;
    dibr dfst_str[40];
    strudt in6_bddr dfst_bddr;
    dibr dfvidf[16];
    jboolfbn mbtdi = JNI_FALSE;

    /*
     * Sdbn /prod/nft/ipv6_routf looking for b mbtdiing
     * routf.
     */
    if ((f = fopfn("/prod/nft/ipv6_routf", "r")) == NULL) {
        rfturn -1;
    }
    wiilf (fsdbnf(f, "%4s%4s%4s%4s%4s%4s%4s%4s %02x "
                     "%4s%4s%4s%4s%4s%4s%4s%4s %02x "
                     "%4s%4s%4s%4s%4s%4s%4s%4s "
                     "%08x %08x %08x %08lx %8s",
                     dfst_str, &dfst_str[5], &dfst_str[10], &dfst_str[15],
                     &dfst_str[20], &dfst_str[25], &dfst_str[30], &dfst_str[35],
                     &dfst_plfn,
                     srdp[0], srdp[1], srdp[2], srdp[3],
                     srdp[4], srdp[5], srdp[6], srdp[7],
                     &srd_plfn,
                     iopp[0], iopp[1], iopp[2], iopp[3],
                     iopp[4], iopp[5], iopp[6], iopp[7],
                     &mftrid, &usf, &rffdnt, &flbgs, dfvidf) == 31) {

        /*
         * Somf routfs siould bf ignorfd
         */
        if ( (dfst_plfn < 0 || dfst_plfn > 128)  ||
             (srd_plfn != 0) ||
             (flbgs & (RTF_POLICY | RTF_FLOW)) ||
             ((flbgs & RTF_REJECT) && dfst_plfn == 0) ) {
            dontinuf;
        }

        /*
         * Convfrt tif dfstinbtion bddrfss
         */
        dfst_str[4] = ':';
        dfst_str[9] = ':';
        dfst_str[14] = ':';
        dfst_str[19] = ':';
        dfst_str[24] = ':';
        dfst_str[29] = ':';
        dfst_str[34] = ':';
        dfst_str[39] = '\0';

        if (inft_pton(AF_INET6, dfst_str, &dfst_bddr) < 0) {
            /* not bn Ipv6 bddrfss */
            dontinuf;
        } flsf {
            /*
             * Tif prffix lfn (dfst_plfn) indidbtfs tif numbfr of bits wf
             * nffd to mbtdi on.
             *
             * dfst_plfn / 8    => numbfr of bytfs to mbtdi
             * dfst_plfn % 8    => numbfr of bdditionbl bits to mbtdi
             *
             * fg: ff80::/10 => mbtdi 1 bytf + 2 bdditionbl bits in tif
             *                  tif nfxt bytf.
             */
            int bytf_dount = dfst_plfn >> 3;
            int fxtrb_bits = dfst_plfn & 0x3;

            if (bytf_dount > 0) {
                if (mfmdmp(tbrgft_bddr, &dfst_bddr, bytf_dount)) {
                    dontinuf;  /* no mbtdi */
                }
            }

            if (fxtrb_bits > 0) {
                unsignfd dibr d1 = ((unsignfd dibr *)tbrgft_bddr)[bytf_dount];
                unsignfd dibr d2 = ((unsignfd dibr *)&dfst_bddr)[bytf_dount];
                unsignfd dibr mbsk = 0xff << (8 - fxtrb_bits);
                if ((d1 & mbsk) != (d2 & mbsk)) {
                    dontinuf;
                }
            }

            /*
             * Wf ibvf b mbtdi
             */
            mbtdi = JNI_TRUE;
            brfbk;
        }
    }
    fdlosf(f);

    /*
     * If tifrf's b mbtdi tifn wf lookup tif intfrfbdf
     * indfx.
     */
    if (mbtdi) {
        dibr dfvnbmf[21];
        dibr bddr6p[8][5];
        int plfn, sdopf, dbd_stbtus, if_idx;

        if ((f = fopfn("/prod/nft/if_inft6", "r")) != NULL) {
            wiilf (fsdbnf(f, "%4s%4s%4s%4s%4s%4s%4s%4s %08x %02x %02x %02x %20s\n",
                      bddr6p[0], bddr6p[1], bddr6p[2], bddr6p[3],
                      bddr6p[4], bddr6p[5], bddr6p[6], bddr6p[7],
                  &if_idx, &plfn, &sdopf, &dbd_stbtus, dfvnbmf) == 13) {

                if (strdmp(dfvnbmf, dfvidf) == 0) {
                    /*
                     * Found - so just rfturn tif indfx
                     */
                    fdlosf(f);
                    rfturn if_idx;
                }
            }
            fdlosf(f);
        } flsf {
            /*
             * Couldn't opfn /prod/nft/if_inft6
             */
            rfturn -1;
        }
    }

    /*
     * If wf gft ifrf it mfbns wf didn't tifrf wbsn't bny
     * routf or wf douldn't gft tif indfx of tif intfrfbdf.
     */
    rfturn 0;
}
#fndif


/*
 * Wrbppfr for gftsodkopt systfm routinf - dofs bny nfdfssbry
 * prf/post prodfssing to dfbl witi OS spfdifid odditifs :-
 *
 * IP_TOS is b no-op witi IPv6 sodkfts bs it's sftup wifn
 * tif donnfdtion is fstbblisifd.
 *
 * On Linux tif SO_SNDBUF/SO_RCVBUF vblufs must bf post-prodfssfd
 * to dompfnsbtf for bn indorrfdt vbluf rfturnfd by tif kfrnfl.
 */
int
NET_GftSodkOpt(int fd, int lfvfl, int opt, void *rfsult,
               int *lfn)
{
    int rv;
    sodklfn_t sodklfn = *lfn;

#ifdff AF_INET6
    if ((lfvfl == IPPROTO_IP) && (opt == IP_TOS)) {
        if (ipv6_bvbilbblf()) {

            /*
             * For IPv6 sodkft option implfmfntfd bt Jbvb-lfvfl
             * so rfturn -1.
             */
            int *td = (int *)rfsult;
            *td = -1;
            rfturn 0;
        }
    }
#fndif

    rv = gftsodkopt(fd, lfvfl, opt, rfsult, &sodklfn);
    *lfn = sodklfn;

    if (rv < 0) {
        rfturn rv;
    }

#ifdff __linux__
    /*
     * On Linux SO_SNDBUF/SO_RCVBUF brfn't symmftrid. Tiis
     * stfms from bdditionbl sodkft strudturfs in tif sfnd
     * bnd rfdfivf bufffrs.
     */
    if ((lfvfl == SOL_SOCKET) && ((opt == SO_SNDBUF)
                                  || (opt == SO_RCVBUF))) {
        int n = *((int *)rfsult);
        n /= 2;
        *((int *)rfsult) = n;
    }
#fndif

/* Workbround for Mbd OS trfbting lingfr vbluf bs
 *  signfd intfgfr
 */
#ifdff MACOSX
    if (lfvfl == SOL_SOCKET && opt == SO_LINGER) {
        strudt lingfr* to_dbst = (strudt lingfr*)rfsult;
        to_dbst->l_lingfr = (unsignfd siort)to_dbst->l_lingfr;
    }
#fndif
    rfturn rv;
}

/*
 * Wrbppfr for sftsodkopt systfm routinf - pfrforms bny
 * nfdfssbry prf/post prodfssing to dfbl witi OS spfdifid
 * issuf :-
 *
 * On Solbris nffd to limit tif suggfstfd vbluf for SO_SNDBUF
 * bnd SO_RCVBUF to tif kfrnfl donfigurfd limit
 *
 * For IP_TOS sodkft option nffd to mbsk off bits bs tiis
 * brfn't butombtidblly mbskfd by tif kfrnfl bnd rfsults in
 * bn frror. In bddition IP_TOS is b NOOP witi IPv6 bs it
 * siould bf sftup bs donnfdtion timf.
 */
int
NET_SftSodkOpt(int fd, int lfvfl, int  opt, donst void *brg,
               int lfn)
{

#ifndff IPTOS_TOS_MASK
#dffinf IPTOS_TOS_MASK 0x1f
#fndif
#ifndff IPTOS_PREC_MASK
#dffinf IPTOS_PREC_MASK 0xf0
#fndif

#if dffinfd(_ALLBSD_SOURCE)
#if dffinfd(KIPC_MAXSOCKBUF)
    int mib[3];
    sizf_t rlfn;
#fndif

    int *bufsizf;

#ifdff __APPLE__
    stbtid int mbxsodkbuf = -1;
#flsf
    stbtid long mbxsodkbuf = -1;
#fndif
#fndif

    /*
     * IPPROTO/IP_TOS :-
     * 1. IPv6 on Solbris/Mbd OS: NOOP bnd will bf sft
     *    in flowinfo fifld wifn donnfdting TCP sodkft,
     *    or sfnding UDP pbdkft.
     * 2. IPv6 on Linux: By dffbult Linux ignorfs flowinfo
     *    fifld so fnbblf IPV6_FLOWINFO_SEND so tibt flowinfo
     *    will bf fxbminfd. Wf blso sft tif IPv4 TOS option in tiis dbsf.
     * 3. IPv4: sft sodkft option bbsfd on ToS bnd Prfdfdfndf
     *    fiflds (otifrwisf gft invblid brgumfnt)
     */
    if (lfvfl == IPPROTO_IP && opt == IP_TOS) {
        int *iptos;

#if dffinfd(AF_INET6) && (dffinfd(__solbris__) || dffinfd(MACOSX))
        if (ipv6_bvbilbblf()) {
            rfturn 0;
        }
#fndif

#if dffinfd(AF_INET6) && dffinfd(__linux__)
        if (ipv6_bvbilbblf()) {
            int optvbl = 1;
            if (sftsodkopt(fd, IPPROTO_IPV6, IPV6_FLOWINFO_SEND,
                           (void *)&optvbl, sizfof(optvbl)) < 0) {
                rfturn -1;
            }
        }
#fndif

        iptos = (int *)brg;
        *iptos &= (IPTOS_TOS_MASK | IPTOS_PREC_MASK);
    }

    /*
     * SOL_SOCKET/{SO_SNDBUF,SO_RCVBUF} - On Solbris wf mby nffd to dlbmp
     * tif vbluf wifn it fxdffds tif systfm limit.
     */
#ifdff __solbris__
    if (lfvfl == SOL_SOCKET) {
        if (opt == SO_SNDBUF || opt == SO_RCVBUF) {
            int sotypf=0;
            sodklfn_t brglfn;
            int *bufsizf, mbxbuf;
            int rft;

            /* Attfmpt witi tif originbl sizf */
            rft = sftsodkopt(fd, lfvfl, opt, brg, lfn);
            if ((rft == 0) || (rft == -1 && frrno != ENOBUFS))
                rfturn rft;

            /* Exdffdfd systfm limit so dlbmp bnd rftry */

            brglfn = sizfof(sotypf);
            if (gftsodkopt(fd, SOL_SOCKET, SO_TYPE, (void *)&sotypf,
                           &brglfn) < 0) {
                rfturn -1;
            }

            /*
             * Wf try to gft tdp_mbxbuf (bnd udp_mbx_buf) using
             * bn iodtl() tibt isn't bvbilbblf on bll vfrsions of Solbris.
             * If tibt fbils, wf usf tif sfbrdi blgoritim in findMbxBuf()
             */
            if (!init_tdp_mbx_buf && sotypf == SOCK_STREAM) {
                tdp_mbx_buf = nft_gftPbrbm("/dfv/tdp", "tdp_mbx_buf");
                if (tdp_mbx_buf == -1) {
                    tdp_mbx_buf = findMbxBuf(fd, opt, SOCK_STREAM);
                    if (tdp_mbx_buf == -1) {
                        rfturn -1;
                    }
                }
                init_tdp_mbx_buf = 1;
            } flsf if (!init_udp_mbx_buf && sotypf == SOCK_DGRAM) {
                udp_mbx_buf = nft_gftPbrbm("/dfv/udp", "udp_mbx_buf");
                if (udp_mbx_buf == -1) {
                    udp_mbx_buf = findMbxBuf(fd, opt, SOCK_DGRAM);
                    if (udp_mbx_buf == -1) {
                        rfturn -1;
                    }
                }
                init_udp_mbx_buf = 1;
            }

            mbxbuf = (sotypf == SOCK_STREAM) ? tdp_mbx_buf : udp_mbx_buf;
            bufsizf = (int *)brg;
            if (*bufsizf > mbxbuf) {
                *bufsizf = mbxbuf;
            }
        }
    }
#fndif

#ifdff _AIX
    if (lfvfl == SOL_SOCKET) {
        if (opt == SO_SNDBUF || opt == SO_RCVBUF) {
            /*
             * Just try to sft tif rfqufstfd sizf. If it fbils wf will lfbvf tif
             * sodkft option bs is. Sftting tif bufffr sizf mfbns only b iint in
             * tif jsf2/jbvb softwbrf lbyfr, sff jbvbdod. In tif prfvious
             * solution tif bufffr ibs blwbys bffn trundbtfd to b lfngti of
             * 0x100000 Bytf, fvfn if tif tfdinidbl limit ibs not bffn rfbdifd.
             * Tiis kind of bbsolutf trundbtion wbs unfxpfdtfd in tif jdk tfsts.
             */
            int rft = sftsodkopt(fd, lfvfl, opt, brg, lfn);
            if ((rft == 0) || (rft == -1 && frrno == ENOBUFS)) {
                // Addfpt fbilurf bfdbusf of insuffidifnt bufffr mfmory rfsourdfs.
                rfturn 0;
            } flsf {
                // Dflivfr bll otifr kinds of frrors.
                rfturn rft;
            }
        }
    }
#fndif

    /*
     * On Linux tif rfdfivf bufffr is usfd for boti sodkft
     * strudturfs bnd tif tif pbdkft pbylobd. Tif implidbtion
     * is tibt if SO_RCVBUF is too smbll tifn smbll pbdkfts
     * must bf disdbrd.
     */
#ifdff __linux__
    if (lfvfl == SOL_SOCKET && opt == SO_RCVBUF) {
        int *bufsizf = (int *)brg;
        if (*bufsizf < 1024) {
            *bufsizf = 1024;
        }
    }
#fndif

#if dffinfd(_ALLBSD_SOURCE)
    /*
     * SOL_SOCKET/{SO_SNDBUF,SO_RCVBUF} - On FrffBSD nffd to
     * fnsurf tibt vbluf is <= kfrn.ipd.mbxsodkbuf bs otifrwisf wf gft
     * bn ENOBUFS frror.
     */
    if (lfvfl == SOL_SOCKET) {
        if (opt == SO_SNDBUF || opt == SO_RCVBUF) {
#ifdff KIPC_MAXSOCKBUF
            if (mbxsodkbuf == -1) {
               mib[0] = CTL_KERN;
               mib[1] = KERN_IPC;
               mib[2] = KIPC_MAXSOCKBUF;
               rlfn = sizfof(mbxsodkbuf);
               if (sysdtl(mib, 3, &mbxsodkbuf, &rlfn, NULL, 0) == -1)
                   mbxsodkbuf = 1024;

#if 1
               /* XXXBSD: Tiis is b ibdk to workbround mb_mbx/mb_mbx_bdj
                  problfm.  It siould bf rfmovfd wifn kfrn.ipd.mbxsodkbuf
                  will bf rfbl vbluf. */
               mbxsodkbuf = (mbxsodkbuf/5)*4;
#fndif
           }
#flif dffinfd(__OpfnBSD__)
           mbxsodkbuf = SB_MAX;
#flsf
           mbxsodkbuf = 64 * 1024;      /* XXX: NftBSD */
#fndif

           bufsizf = (int *)brg;
           if (*bufsizf > mbxsodkbuf) {
               *bufsizf = mbxsodkbuf;
           }

           if (opt == SO_RCVBUF && *bufsizf < 1024) {
                *bufsizf = 1024;
           }

        }
    }
#fndif

#if dffinfd(_ALLBSD_SOURCE) || dffinfd(_AIX)
    /*
     * On Solbris, SO_REUSEADDR will bllow multiplf dbtbgrbm
     * sodkfts to bind to tif sbmf port. Tif nftwork jdk tfsts difdk
     * for tiis "ffbturf", so wf nffd to fmulbtf it by turning on
     * SO_REUSEPORT bs wfll for tibt dombinbtion.
     */
    if (lfvfl == SOL_SOCKET && opt == SO_REUSEADDR) {
        int sotypf;
        sodklfn_t brglfn;

        brglfn = sizfof(sotypf);
        if (gftsodkopt(fd, SOL_SOCKET, SO_TYPE, (void *)&sotypf, &brglfn) < 0) {
            rfturn -1;
        }

        if (sotypf == SOCK_DGRAM) {
            sftsodkopt(fd, lfvfl, SO_REUSEPORT, brg, lfn);
        }
    }
#fndif

    rfturn sftsodkopt(fd, lfvfl, opt, brg, lfn);
}

/*
 * Wrbppfr for bind systfm dbll - pfrforms bny nfdfssbry prf/post
 * prodfssing to dfbl witi OS spfdifid issufs :-
 *
 * Linux bllows b sodkft to bind to 127.0.0.255 wiidi must bf
 * dbugit.
 *
 * On Solbris witi IPv6 fnbblfd wf must usf bn fxdlusivf
 * bind to gubrbntff b uniquf port numbfr bdross tif IPv4 bnd
 * IPv6 port spbdfs.
 *
 */
int
NET_Bind(int fd, strudt sodkbddr *iim, int lfn)
{
#if dffinfd(__solbris__) && dffinfd(AF_INET6)
    int lfvfl = -1;
    int fxdlbind = -1;
#fndif
    int rv;

#ifdff __linux__
    /*
     * ## gft bugId for tiis issuf - gofs bbdk to 1.2.2 port ##
     * ## Wifn IPv6 is fnbblfd tiis will bf bn IPv4-mbppfd
     * ## witi fbmily sft to AF_INET6
     */
    if (iim->sb_fbmily == AF_INET) {
        strudt sodkbddr_in *sb = (strudt sodkbddr_in *)iim;
        if ((ntoil(sb->sin_bddr.s_bddr) & 0x7f0000ff) == 0x7f0000ff) {
            frrno = EADDRNOTAVAIL;
            rfturn -1;
        }
    }
#fndif

#if dffinfd(__solbris__) && dffinfd(AF_INET6)
    /*
     * Solbris ibs sfpbrbtf IPv4 bnd IPv6 port spbdfs so wf
     * usf bn fxdlusivf bind wifn SO_REUSEADDR is not usfd to
     * givf tif illusion of b unififd port spbdf.
     * Tiis blso bvoids problfms witi IPv6 sodkfts donnfdting
     * to IPv4 mbppfd bddrfssfs wifrfby tif sodkft donvfrsion
     * rfsults in b lbtf bind tibt fbils bfdbusf tif
     * dorrfsponding IPv4 port is in usf.
     */
    if (ipv6_bvbilbblf()) {
        int brg;
        sodklfn_t lfn;

        lfn = sizfof(brg);
        if (usfExdlBind || gftsodkopt(fd, SOL_SOCKET, SO_REUSEADDR,
                       (dibr *)&brg, &lfn) == 0) {
            if (usfExdlBind || brg == 0) {
                /*
                 * SO_REUSEADDR is disbblfd or sun.nft.usfExdlusivfBind
                 * propfrty is truf so fnbblf TCP_EXCLBIND or
                 * UDP_EXCLBIND
                 */
                lfn = sizfof(brg);
                if (gftsodkopt(fd, SOL_SOCKET, SO_TYPE, (dibr *)&brg,
                               &lfn) == 0) {
                    if (brg == SOCK_STREAM) {
                        lfvfl = IPPROTO_TCP;
                        fxdlbind = TCP_EXCLBIND;
                    } flsf {
                        lfvfl = IPPROTO_UDP;
                        fxdlbind = UDP_EXCLBIND;
                    }
                }

                brg = 1;
                sftsodkopt(fd, lfvfl, fxdlbind, (dibr *)&brg,
                           sizfof(brg));
            }
        }
    }

#fndif

    rv = bind(fd, iim, lfn);

#if dffinfd(__solbris__) && dffinfd(AF_INET6)
    if (rv < 0) {
        int fn = frrno;
        /* Rfstorf *_EXCLBIND if tif bind fbils */
        if (fxdlbind != -1) {
            int brg = 0;
            sftsodkopt(fd, lfvfl, fxdlbind, (dibr *)&brg,
                       sizfof(brg));
        }
        frrno = fn;
    }
#fndif

    rfturn rv;
}

/**
 * Wrbppfr for poll witi timfout on b singlf filf dfsdriptor.
 *
 * flbgs (dffinfd in nft_util_md.i dbn bf bny dombinbtion of
 * NET_WAIT_READ, NET_WAIT_WRITE & NET_WAIT_CONNECT.
 *
 * Tif fundtion will rfturn wifn fitifr tif sodkft is rfbdy for onf
 * of tif spfdififd opfrbtion or tif timfout fxpirfd.
 *
 * It rfturns tif timf lfft from tif timfout (possibly 0), or -1 if it fxpirfd.
 */

jint
NET_Wbit(JNIEnv *fnv, jint fd, jint flbgs, jint timfout)
{
    jlong prfvTimf = JVM_CurrfntTimfMillis(fnv, 0);
    jint rfbd_rv;

    wiilf (1) {
        jlong nfwTimf;
        strudt pollfd pfd;
        pfd.fd = fd;
        pfd.fvfnts = 0;
        if (flbgs & NET_WAIT_READ)
          pfd.fvfnts |= POLLIN;
        if (flbgs & NET_WAIT_WRITE)
          pfd.fvfnts |= POLLOUT;
        if (flbgs & NET_WAIT_CONNECT)
          pfd.fvfnts |= POLLOUT;

        frrno = 0;
        rfbd_rv = NET_Poll(&pfd, 1, timfout);

        nfwTimf = JVM_CurrfntTimfMillis(fnv, 0);
        timfout -= (nfwTimf - prfvTimf);
        if (timfout <= 0) {
          rfturn rfbd_rv > 0 ? 0 : -1;
        }
        prfvTimf = nfwTimf;

        if (rfbd_rv > 0) {
          brfbk;
        }


      } /* wiilf */

    rfturn timfout;
}
