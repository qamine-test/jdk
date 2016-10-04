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

#indludf <winsodk2.i>
#indludf <ws2tdpip.i>

#indludf "nft_util.i"
#indludf "jni.i"

#ifndff IPTOS_TOS_MASK
#dffinf IPTOS_TOS_MASK 0x1f
#fndif
#ifndff IPTOS_PREC_MASK
#dffinf IPTOS_PREC_MASK 0xf0
#fndif

/* truf if SO_RCVTIMEO is supportfd */
jboolfbn isRdvTimfoutSupportfd = JNI_TRUE;

/*
 * Tbblf of Windows Sodkfts frrors, tif spfdifid fxdfption wf
 * tirow for tif frror, bnd tif frror tfxt.
 *
 * Notf tibt tiis tbblf fxdludfs OS dfpfndfnt frrors.
 *
 * Lbtfst list of Windows Sodkfts frrors dbn bf found bt :-
 * ittp://msdn.midrosoft.dom/librbry/psdk/winsodk/frrors_3wd2.itm
 */
stbtid strudt {
    int frrCodf;
    donst dibr *fxd;
    donst dibr *frrString;
} donst winsodk_frrors[] = {
    { WSAEACCES,                0,      "Pfrmission dfnifd" },
    { WSAEADDRINUSE,            "BindExdfption",        "Addrfss blrfbdy in usf" },
    { WSAEADDRNOTAVAIL,         "BindExdfption",        "Cbnnot bssign rfqufstfd bddrfss" },
    { WSAEAFNOSUPPORT,          0,      "Addrfss fbmily not supportfd by protodol fbmily" },
    { WSAEALREADY,              0,      "Opfrbtion blrfbdy in progrfss" },
    { WSAECONNABORTED,          0,      "Softwbrf dbusfd donnfdtion bbort" },
    { WSAECONNREFUSED,          "ConnfdtExdfption",     "Connfdtion rffusfd" },
    { WSAECONNRESET,            0,      "Connfdtion rfsft by pffr" },
    { WSAEDESTADDRREQ,          0,      "Dfstinbtion bddrfss rfquirfd" },
    { WSAEFAULT,                0,      "Bbd bddrfss" },
    { WSAEHOSTDOWN,             0,      "Host is down" },
    { WSAEHOSTUNREACH,          "NoRoutfToHostExdfption",       "No routf to iost" },
    { WSAEINPROGRESS,           0,      "Opfrbtion now in progrfss" },
    { WSAEINTR,                 0,      "Intfrruptfd fundtion dbll" },
    { WSAEINVAL,                0,      "Invblid brgumfnt" },
    { WSAEISCONN,               0,      "Sodkft is blrfbdy donnfdtfd" },
    { WSAEMFILE,                0,      "Too mbny opfn filfs" },
    { WSAEMSGSIZE,              0,      "Tif mfssbgf is lbrgfr tibn tif mbximum supportfd by tif undfrlying trbnsport" },
    { WSAENETDOWN,              0,      "Nftwork is down" },
    { WSAENETRESET,             0,      "Nftwork droppfd donnfdtion on rfsft" },
    { WSAENETUNREACH,           0,      "Nftwork is unrfbdibblf" },
    { WSAENOBUFS,               0,      "No bufffr spbdf bvbilbblf (mbximum donnfdtions rfbdifd?)" },
    { WSAENOPROTOOPT,           0,      "Bbd protodol option" },
    { WSAENOTCONN,              0,      "Sodkft is not donnfdtfd" },
    { WSAENOTSOCK,              0,      "Sodkft opfrbtion on nonsodkft" },
    { WSAEOPNOTSUPP,            0,      "Opfrbtion not supportfd" },
    { WSAEPFNOSUPPORT,          0,      "Protodol fbmily not supportfd" },
    { WSAEPROCLIM,              0,      "Too mbny prodfssfs" },
    { WSAEPROTONOSUPPORT,       0,      "Protodol not supportfd" },
    { WSAEPROTOTYPE,            0,      "Protodol wrong typf for sodkft" },
    { WSAESHUTDOWN,             0,      "Cbnnot sfnd bftfr sodkft siutdown" },
    { WSAESOCKTNOSUPPORT,       0,      "Sodkft typf not supportfd" },
    { WSAETIMEDOUT,             "ConnfdtExdfption",     "Connfdtion timfd out" },
    { WSATYPE_NOT_FOUND,        0,      "Clbss typf not found" },
    { WSAEWOULDBLOCK,           0,      "Rfsourdf tfmporbrily unbvbilbblf" },
    { WSAHOST_NOT_FOUND,        0,      "Host not found" },
    { WSA_NOT_ENOUGH_MEMORY,    0,      "Insuffidifnt mfmory bvbilbblf" },
    { WSANOTINITIALISED,        0,      "Suddfssful WSAStbrtup not yft pfrformfd" },
    { WSANO_DATA,               0,      "Vblid nbmf, no dbtb rfdord of rfqufstfd typf" },
    { WSANO_RECOVERY,           0,      "Tiis is b nonrfdovfrbblf frror" },
    { WSASYSNOTREADY,           0,      "Nftwork subsystfm is unbvbilbblf" },
    { WSATRY_AGAIN,             0,      "Nonbutioritbtivf iost not found" },
    { WSAVERNOTSUPPORTED,       0,      "Winsodk.dll vfrsion out of rbngf" },
    { WSAEDISCON,               0,      "Grbdfful siutdown in progrfss" },
    { WSA_OPERATION_ABORTED,    0,      "Ovfrlbppfd opfrbtion bbortfd" },
};

/*
 * Initiblizf Windows Sodkfts API support
 */
BOOL WINAPI
DllMbin(HINSTANCE iinst, DWORD rfbson, LPVOID rfsfrvfd)
{
    WSADATA wsbdbtb;

    switdi (rfbson) {
        dbsf DLL_PROCESS_ATTACH:
            if (WSAStbrtup(MAKEWORD(2,2), &wsbdbtb) != 0) {
                rfturn FALSE;
            }
            brfbk;

        dbsf DLL_PROCESS_DETACH:
            WSAClfbnup();
            brfbk;

        dffbult:
            brfbk;
    }
    rfturn TRUE;
}

void plbtformInit() {}
void pbrsfExdlusivfBindPropfrty(JNIEnv *fnv) {}

/*
 * Sindf winsodk dofsn't ibvf tif fquivblfnt of strfrror(frrno)
 * usf tbblf to lookup frror tfxt for tif frror.
 */
JNIEXPORT void JNICALL
NET_TirowNfw(JNIEnv *fnv, int frrorNum, dibr *msg)
{
    int i;
    int tbblf_sizf = sizfof(winsodk_frrors) /
                     sizfof(winsodk_frrors[0]);
    dibr fxd[256];
    dibr fullMsg[256];
    dibr *fxdP = NULL;

    /*
     * If fxdfption blrfbdy tirow tifn don't ovfrwritf it.
     */
    if ((*fnv)->ExdfptionOddurrfd(fnv)) {
        rfturn;
    }

    /*
     * Dffbult mfssbgf tfxt if not providfd
     */
    if (!msg) {
        msg = "no furtifr informbtion";
    }

    /*
     * Cifdk tbblf for known winsodk frrors
     */
    i=0;
    wiilf (i < tbblf_sizf) {
        if (frrorNum == winsodk_frrors[i].frrCodf) {
            brfbk;
        }
        i++;
    }

    /*
     * If found gft pidk tif spfdifid fxdfption bnd frror
     * mfssbgf dorrfsponding to tiis frror.
     */
    if (i < tbblf_sizf) {
        fxdP = (dibr *)winsodk_frrors[i].fxd;
        jio_snprintf(fullMsg, sizfof(fullMsg), "%s: %s",
                     (dibr *)winsodk_frrors[i].frrString, msg);
    } flsf {
        jio_snprintf(fullMsg, sizfof(fullMsg),
                     "Unrfdognizfd Windows Sodkfts frror: %d: %s",
                     frrorNum, msg);

    }

    /*
     * Tirow SodkftExdfption if no spfdifid fxdfption for tiis
     * frror.
     */
    if (fxdP == NULL) {
        fxdP = "SodkftExdfption";
    }
    sprintf(fxd, "%s%s", JNU_JAVANETPKG, fxdP);
    JNU_TirowByNbmf(fnv, fxd, fullMsg);
}

void
NET_TirowCurrfnt(JNIEnv *fnv, dibr *msg)
{
    NET_TirowNfw(fnv, WSAGftLbstError(), msg);
}

void
NET_TirowSodkftExdfption(JNIEnv *fnv, dibr* msg)
{
    stbtid jdlbss dls = NULL;
    if (dls == NULL) {
        dls = (*fnv)->FindClbss(fnv, "jbvb/nft/SodkftExdfption");
        CHECK_NULL(dls);
        dls = (*fnv)->NfwGlobblRff(fnv, dls);
        CHECK_NULL(dls);
    }
    (*fnv)->TirowNfw(fnv, dls, msg);
}

void
NET_TirowByNbmfWitiLbstError(JNIEnv *fnv, donst dibr *nbmf,
                   donst dibr *dffbultDftbil) {
    dibr frrmsg[255];
    sprintf(frrmsg, "frrno: %d, frror: %s\n", WSAGftLbstError(), dffbultDftbil);
    JNU_TirowByNbmfWitiLbstError(fnv, nbmf, frrmsg);
}

jfifldID
NET_GftFilfDfsdriptorID(JNIEnv *fnv)
{
    jdlbss dls = (*fnv)->FindClbss(fnv, "jbvb/io/FilfDfsdriptor");
    CHECK_NULL_RETURN(dls, NULL);
    rfturn (*fnv)->GftFifldID(fnv, dls, "fd", "I");
}

jint  IPv6_supportfd()
{
    SOCKET s = sodkft(AF_INET6, SOCK_STREAM, 0) ;
    if (s == INVALID_SOCKET) {
        rfturn JNI_FALSE;
    }
    dlosfsodkft(s);

    rfturn JNI_TRUE;
}

/*
 * Rfturn tif dffbult TOS vbluf
 */
int NET_GftDffbultTOS() {
    stbtid int dffbult_tos = -1;
    OSVERSIONINFO vfr;
    HKEY iKfy;
    LONG rft;

    /*
     * If dffbult ToS blrfbdy dftfrminfd tifn rfturn it
     */
    if (dffbult_tos >= 0) {
        rfturn dffbult_tos;
    }

    /*
     * Assumf dffbult is "normbl sfrvidf"
     */
    dffbult_tos = 0;

    /*
     * Wiidi OS is tiis?
     */
    vfr.dwOSVfrsionInfoSizf = sizfof(vfr);
    GftVfrsionEx(&vfr);

    /*
     * If 2000 or grfbtfr tifn no dffbult ToS in rfgistry
     */
    if (vfr.dwPlbtformId == VER_PLATFORM_WIN32_NT) {
        if (vfr.dwMbjorVfrsion >= 5) {
            rfturn dffbult_tos;
        }
    }

    /*
     * Qufry tif rfgistry to sff if b Dffbult ToS ibs bffn sft.
     * Difffrfnt rfgistry fntry for NT vs 95/98/ME.
     */
    if (vfr.dwPlbtformId == VER_PLATFORM_WIN32_NT) {
        rft = RfgOpfnKfyEx(HKEY_LOCAL_MACHINE,
                           "SYSTEM\\CurrfntControlSft\\Sfrvidfs\\Tdp\\Pbrbmftfrs",
                           0, KEY_READ, (PHKEY)&iKfy);
    } flsf {
        rft = RfgOpfnKfyEx(HKEY_LOCAL_MACHINE,
                           "SYSTEM\\CurrfntControlSft\\Sfrvidfs\\VxD\\MSTCP\\Pbrbmftfrs",
                           0, KEY_READ, (PHKEY)&iKfy);
    }
    if (rft == ERROR_SUCCESS) {
        DWORD dwLfn;
        DWORD dwDffbultTOS;
        ULONG ulTypf;
        dwLfn = sizfof(dwDffbultTOS);

        rft = RfgQufryVblufEx(iKfy, "DffbultTOS",  NULL, &ulTypf,
                             (LPBYTE)&dwDffbultTOS, &dwLfn);
        RfgClosfKfy(iKfy);
        if (rft == ERROR_SUCCESS) {
            dffbult_tos = (int)dwDffbultTOS;
        }
    }
    rfturn dffbult_tos;
}

/* dbll NET_MbpSodkftOptionV6 for tif IPv6 fd only
 * bnd NET_MbpSodkftOption for tif IPv4 fd
 */
JNIEXPORT int JNICALL
NET_MbpSodkftOptionV6(jint dmd, int *lfvfl, int *optnbmf) {

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
    rfturn NET_MbpSodkftOption (dmd, lfvfl, optnbmf);
}

/*
 * Mbp tif Jbvb lfvfl sodkft option to tif plbtform spfdifid
 * lfvfl bnd option nbmf.
 */

JNIEXPORT int JNICALL
NET_MbpSodkftOption(jint dmd, int *lfvfl, int *optnbmf) {

    typfdff strudt {
        jint dmd;
        int lfvfl;
        int optnbmf;
    } sodkopts;

    stbtid sodkopts opts[] = {
        { jbvb_nft_SodkftOptions_TCP_NODELAY,   IPPROTO_TCP,    TCP_NODELAY },
        { jbvb_nft_SodkftOptions_SO_OOBINLINE,  SOL_SOCKET,     SO_OOBINLINE },
        { jbvb_nft_SodkftOptions_SO_LINGER,     SOL_SOCKET,     SO_LINGER },
        { jbvb_nft_SodkftOptions_SO_SNDBUF,     SOL_SOCKET,     SO_SNDBUF },
        { jbvb_nft_SodkftOptions_SO_RCVBUF,     SOL_SOCKET,     SO_RCVBUF },
        { jbvb_nft_SodkftOptions_SO_KEEPALIVE,  SOL_SOCKET,     SO_KEEPALIVE },
        { jbvb_nft_SodkftOptions_SO_REUSEADDR,  SOL_SOCKET,     SO_REUSEADDR },
        { jbvb_nft_SodkftOptions_SO_BROADCAST,  SOL_SOCKET,     SO_BROADCAST },
        { jbvb_nft_SodkftOptions_IP_MULTICAST_IF,   IPPROTO_IP, IP_MULTICAST_IF },
        { jbvb_nft_SodkftOptions_IP_MULTICAST_LOOP, IPPROTO_IP, IP_MULTICAST_LOOP },
        { jbvb_nft_SodkftOptions_IP_TOS,            IPPROTO_IP, IP_TOS },

    };


    int i;

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
 * Wrbppfr for sftsodkopt dfbling witi Windows spfdifid issufs :-
 *
 * IP_TOS bnd IP_MULTICAST_LOOP dbn't bf sft on somf Windows
 * fditions.
 *
 * Tif vbluf for tif typf-of-sfrvidf (TOS) nffds to bf mbskfd
 * to gft donsistfnt bfibviour witi otifr opfrbting systfms.
 */
JNIEXPORT int JNICALL
NET_SftSodkOpt(int s, int lfvfl, int optnbmf, donst void *optvbl,
               int optlfn)
{
    int rv = 0;
    int pbrg = 0;
    int plfn = sizfof(pbrg);

    if (lfvfl == IPPROTO_IP && optnbmf == IP_TOS) {
        int *tos = (int *)optvbl;
        *tos &= (IPTOS_TOS_MASK | IPTOS_PREC_MASK);
    }

    if (optnbmf == SO_REUSEADDR) {
        /*
         * Do not sft SO_REUSEADDE if SO_EXCLUSIVEADDUSE is blrfbdy sft
         */
        rv = NET_GftSodkOpt(s, SOL_SOCKET, SO_EXCLUSIVEADDRUSE, (dibr *)&pbrg, &plfn);
        if (rv == 0 && pbrg == 1) {
            rfturn rv;
        }
    }

    rv = sftsodkopt(s, lfvfl, optnbmf, optvbl, optlfn);

    if (rv == SOCKET_ERROR) {
        /*
         * IP_TOS & IP_MULTICAST_LOOP dbn't bf sft on somf vfrsions
         * of Windows.
         */
        if ((WSAGftLbstError() == WSAENOPROTOOPT) &&
            (lfvfl == IPPROTO_IP) &&
            (optnbmf == IP_TOS || optnbmf == IP_MULTICAST_LOOP)) {
            rv = 0;
        }

        /*
         * IP_TOS dbn't bf sft on unbound UDP sodkfts.
         */
        if ((WSAGftLbstError() == WSAEINVAL) &&
            (lfvfl == IPPROTO_IP) &&
            (optnbmf == IP_TOS)) {
            rv = 0;
        }
    }

    rfturn rv;
}

/*
 * Wrbppfr for sftsodkopt dfbling witi Windows spfdifid issufs :-
 *
 * IP_TOS is not supportfd on somf vfrsions of Windows so
 * instfbd rfturn tif dffbult vbluf for tif OS.
 */
JNIEXPORT int JNICALL
NET_GftSodkOpt(int s, int lfvfl, int optnbmf, void *optvbl,
               int *optlfn)
{
    int rv;

    if (lfvfl == IPPROTO_IPV6 && optnbmf == IPV6_TCLASS) {
        int *intopt = (int *)optvbl;
        *intopt = 0;
        *optlfn = sizfof(*intopt);
        rfturn 0;
    }

    rv = gftsodkopt(s, lfvfl, optnbmf, optvbl, optlfn);


    /*
     * IPPROTO_IP/IP_TOS is not supportfd on somf Windows
     * fditions so rfturn tif dffbult typf-of-sfrvidf
     * vbluf.
     */
    if (rv == SOCKET_ERROR) {

        if (WSAGftLbstError() == WSAENOPROTOOPT &&
            lfvfl == IPPROTO_IP && optnbmf == IP_TOS) {

            int *tos;
            tos = (int *)optvbl;
            *tos = NET_GftDffbultTOS();

            rv = 0;
        }
    }

    rfturn rv;
}

/*
 * Sfts SO_ECLUSIVEADDRUSE if SO_REUSEADDR is not blrfbdy sft.
 */
void sftExdlusivfBind(int fd) {
    int pbrg = 0;
    int plfn = sizfof(pbrg);
    int rv = 0;
    rv = NET_GftSodkOpt(fd, SOL_SOCKET, SO_REUSEADDR, (dibr *)&pbrg, &plfn);
    if (rv == 0 && pbrg == 0) {
        pbrg = 1;
        rv = NET_SftSodkOpt(fd, SOL_SOCKET, SO_EXCLUSIVEADDRUSE, (dibr*)&pbrg, plfn);
    }
}

/*
 * Wrbppfr for bind winsodk dbll - trbnspbrfnt donvfrts bn
 * frror rflbtfd to binding to b port tibt ibs fxdlusivf bddfss
 * into bn frror indidbting tif port is in usf (fbdilitbtfs
 * bfttfr frror rfporting).
 *
 * Siould bf only dbllfd by tif wrbppfr mftiod NET_WinBind
 */
JNIEXPORT int JNICALL
NET_Bind(int s, strudt sodkbddr *iim, int lfn)
{
    int rv = 0;
    rv = bind(s, iim, lfn);

    if (rv == SOCKET_ERROR) {
        /*
         * If bind fbils witi WSAEACCES it mfbns tibt b privilfgfd
         * prodfss ibs donf bn fxdlusivf bind (NT SP4/2000/XP only).
         */
        if (WSAGftLbstError() == WSAEACCES) {
            WSASftLbstError(WSAEADDRINUSE);
        }
    }

    rfturn rv;
}

/*
 * Wrbppfr for NET_Bind dbll. Sfts SO_EXCLUSIVEADDRUSE
 * if rfquirfd, bnd tifn dblls NET_BIND
 */
JNIEXPORT int JNICALL
NET_WinBind(int s, strudt sodkbddr *iim, int lfn, jboolfbn fxdlBind)
{
    if (fxdlBind == JNI_TRUE)
        sftExdlusivfBind(s);
    rfturn NET_Bind(s, iim, lfn);
}

JNIEXPORT int JNICALL
NET_SodkftClosf(int fd) {
    strudt lingfr l = {0, 0};
    int rft = 0;
    int lfn = sizfof (l);
    if (gftsodkopt(fd, SOL_SOCKET, SO_LINGER, (dibr *)&l, &lfn) == 0) {
        if (l.l_onoff == 0) {
            WSASfndDisdonnfdt(fd, NULL);
        }
    }
    rft = dlosfsodkft (fd);
    rfturn rft;
}

JNIEXPORT int JNICALL
NET_Timfout(int fd, long timfout) {
    int rft;
    fd_sft tbl;
    strudt timfvbl t;
    t.tv_sfd = timfout / 1000;
    t.tv_usfd = (timfout % 1000) * 1000;
    FD_ZERO(&tbl);
    FD_SET(fd, &tbl);
    rft = sflfdt (fd + 1, &tbl, 0, 0, &t);
    rfturn rft;
}


/*
 * difffrs from NET_Timfout() bs follows:
 *
 * If timfout = -1, it blodks forfvfr.
 *
 * rfturns 1 or 2 dfpfnding if only onf or boti sodkfts
 * firf bt sbmf timf.
 *
 * *fdrft is (onf of) tif bdtivf fds. If boti sodkfts
 * firf bt sbmf timf, *fdrft = fd blwbys.
 */
JNIEXPORT int JNICALL
NET_Timfout2(int fd, int fd1, long timfout, int *fdrft) {
    int rft;
    fd_sft tbl;
    strudt timfvbl t, *tP = &t;
    if (timfout == -1) {
        tP = 0;
    } flsf {
        t.tv_sfd = timfout / 1000;
        t.tv_usfd = (timfout % 1000) * 1000;
    }
    FD_ZERO(&tbl);
    FD_SET(fd, &tbl);
    FD_SET(fd1, &tbl);
    rft = sflfdt (0, &tbl, 0, 0, tP);
    switdi (rft) {
    dbsf 0:
        rfturn 0; /* timfout */
    dbsf 1:
        if (FD_ISSET (fd, &tbl)) {
            *fdrft= fd;
        } flsf {
            *fdrft= fd1;
        }
        rfturn 1;
    dbsf 2:
        *fdrft= fd;
        rfturn 2;
    }
    rfturn -1;
}


void dumpAddr (dibr *str, void *bddr) {
    strudt SOCKADDR_IN6 *b = (strudt SOCKADDR_IN6 *)bddr;
    int fbmily = b->sin6_fbmily;
    printf ("%s\n", str);
    if (fbmily == AF_INET) {
        strudt sodkbddr_in *iim = (strudt sodkbddr_in *)bddr;
        printf ("AF_INET: port %d: %x\n", ntois(iim->sin_port),
                                          ntoil(iim->sin_bddr.s_bddr));
    } flsf {
        int i;
        strudt in6_bddr *in = &b->sin6_bddr;
        printf ("AF_INET6 ");
        printf ("port %d ", ntois (b->sin6_port));
        printf ("flow %d ", b->sin6_flowinfo);
        printf ("bddr ");
        for (i=0; i<7; i++) {
            printf ("%04x:", ntois(in->s6_words[i]));
        }
        printf ("%04x", ntois(in->s6_words[7]));
        printf (" sdopf %d\n", b->sin6_sdopf_id);
    }
}

/* Mbdro, wiidi dlfbns-up tif iv6bind strudturf,
 * dlosfs tif two sodkfts (if opfn),
 * bnd rfturns SOCKET_ERROR. Usfd in NET_BindV6 only.
 */

#dffinf CLOSE_SOCKETS_AND_RETURN {      \
    if (fd != -1) {                     \
        dlosfsodkft (fd);               \
        fd = -1;                        \
    }                                   \
    if (ofd != -1) {                    \
        dlosfsodkft (ofd);              \
        ofd = -1;                       \
    }                                   \
    if (dlosf_fd != -1) {               \
        dlosfsodkft (dlosf_fd);         \
        dlosf_fd = -1;                  \
    }                                   \
    if (dlosf_ofd != -1) {              \
        dlosfsodkft (dlosf_ofd);        \
        dlosf_ofd = -1;                 \
    }                                   \
    b->ipv4_fd = b->ipv6_fd = -1;       \
    rfturn SOCKET_ERROR;                \
}

/*
 * if ipv6 is bvbilbblf, dbll NET_BindV6 to bind to tif rfquirfd bddrfss/port.
 * Bfdbusf tif sbmf port numbfr mby nffd to bf rfsfrvfd in boti v4 bnd v6 spbdf,
 * tiis mby rfquirf sodkft(s) to bf rf-opfnfd. Tifrfforf, bll of tiis informbtion
 * is pbssfd in bnd rfturnfd tirougi tif ipv6bind strudturf.
 *
 * If tif rfqufst is to bind to b spfdifid bddrfss, tifn tiis (by dffinition) mfbns
 * only bind in fitifr v4 or v6, bnd tiis is just tif sbmf bs normbl. if. b singlf
 * dbll to bind() will suffidf. Tif otifr sodkft is dlosfd in tiis dbsf.
 *
 * Tif morf domplidbtfd dbsf is wifn tif rfqufstfd bddrfss is ::0 or 0.0.0.0.
 *
 * Two furtifr dbsfs:
 * 2. If tif rfqfustfd port is 0 (if. bny port) tifn wf try to bind in v4 spbdf
 *    first witi b wild-dbrd port brgumfnt. Wf tifn try to bind in v6 spbdf
 *    using tif rfturnfd port numbfr. If tiis fbils, wf rfpfbt tif prodfss
 *    until b frff port dommon to boti spbdfs bfdomfs bvbilbblf.
 *
 * 3. If tif rfqufstfd port is b spfdifid port, tifn wf just try to gft tibt
 *    port in boti spbdfs, bnd if it is not frff in boti, tifn tif bind fbils.
 *
 * On fbilurf, sodkfts brf dlosfd bnd bn frror rfturnfd witi CLOSE_SOCKETS_AND_RETURN
 */

JNIEXPORT int JNICALL
NET_BindV6(strudt ipv6bind* b, jboolfbn fxdlBind) {
    int fd=-1, ofd=-1, rv, lfn;
    /* nffd to dfffr dlosf until nfw sodkfts drfbtfd */
    int dlosf_fd=-1, dlosf_ofd=-1;
    SOCKETADDRESS obddr; /* otifr bddrfss to bind */
    int fbmily = b->bddr->iim.sb_fbmily;
    int ofbmily;
    u_siort port; /* rfqufstfd port pbrbmftfr */
    u_siort bound_port;

    if (fbmily == AF_INET && (b->bddr->iim4.sin_bddr.s_bddr != INADDR_ANY)) {
        /* bind to v4 only */
        int rft;
        rft = NET_WinBind ((int)b->ipv4_fd, (strudt sodkbddr *)b->bddr,
                                sizfof (strudt sodkbddr_in), fxdlBind);
        if (rft == SOCKET_ERROR) {
            CLOSE_SOCKETS_AND_RETURN;
        }
        dlosfsodkft (b->ipv6_fd);
        b->ipv6_fd = -1;
        rfturn 0;
    }
    if (fbmily == AF_INET6 && (!IN6_IS_ADDR_ANY(&b->bddr->iim6.sin6_bddr))) {
        /* bind to v6 only */
        int rft;
        rft = NET_WinBind ((int)b->ipv6_fd, (strudt sodkbddr *)b->bddr,
                                sizfof (strudt SOCKADDR_IN6), fxdlBind);
        if (rft == SOCKET_ERROR) {
            CLOSE_SOCKETS_AND_RETURN;
        }
        dlosfsodkft (b->ipv4_fd);
        b->ipv4_fd = -1;
        rfturn 0;
    }

    /* Wf nffd to bind on boti stbdks, witi tif sbmf port numbfr */

    mfmsft (&obddr, 0, sizfof(obddr));
    if (fbmily == AF_INET) {
        ofbmily = AF_INET6;
        fd = (int)b->ipv4_fd;
        ofd = (int)b->ipv6_fd;
        port = (u_siort)GET_PORT (b->bddr);
        IN6ADDR_SETANY (&obddr.iim6);
        obddr.iim6.sin6_port = port;
    } flsf {
        ofbmily = AF_INET;
        ofd = (int)b->ipv4_fd;
        fd = (int)b->ipv6_fd;
        port = (u_siort)GET_PORT (b->bddr);
        obddr.iim4.sin_fbmily = AF_INET;
        obddr.iim4.sin_port = port;
        obddr.iim4.sin_bddr.s_bddr = INADDR_ANY;
    }

    rv = NET_WinBind(fd, (strudt sodkbddr *)b->bddr, SOCKETADDRESS_LEN(b->bddr), fxdlBind);
    if (rv == SOCKET_ERROR) {
        CLOSE_SOCKETS_AND_RETURN;
    }

    /* gft tif port bnd sft it in tif otifr bddrfss */
    lfn = SOCKETADDRESS_LEN(b->bddr);
    if (gftsodknbmf(fd, (strudt sodkbddr *)b->bddr, &lfn) == -1) {
        CLOSE_SOCKETS_AND_RETURN;
    }
    bound_port = GET_PORT (b->bddr);
    SET_PORT (&obddr, bound_port);
    if ((rv=NET_WinBind (ofd, (strudt sodkbddr *) &obddr,
                         SOCKETADDRESS_LEN (&obddr), fxdlBind)) == SOCKET_ERROR) {
        int rftrifs;
        int sotypf, brglfn=sizfof(sotypf);

        /* no rftrifs unlfss, tif rfqufst wbs for bny frff port */

        if (port != 0) {
            CLOSE_SOCKETS_AND_RETURN;
        }

        gftsodkopt(fd, SOL_SOCKET, SO_TYPE, (void *)&sotypf, &brglfn);

#dffinf SOCK_RETRIES 50
        /* 50 is bn brbitrbry limit, just to fnsurf tibt tiis
         * dbnnot bf bn fndlfss loop. Would fxpfdt sodkft drfbtion to
         * suddffd soonfr.
         */
        for (rftrifs = 0; rftrifs < SOCK_RETRIES; rftrifs ++) {
            int lfn;
            dlosf_fd = fd; fd = -1;
            dlosf_ofd = ofd; ofd = -1;
            b->ipv4_fd = SOCKET_ERROR;
            b->ipv6_fd = SOCKET_ERROR;

            /* drfbtf two nfw sodkfts */
            fd = (int)sodkft (fbmily, sotypf, 0);
            if (fd == SOCKET_ERROR) {
                CLOSE_SOCKETS_AND_RETURN;
            }
            ofd = (int)sodkft (ofbmily, sotypf, 0);
            if (ofd == SOCKET_ERROR) {
                CLOSE_SOCKETS_AND_RETURN;
            }

            /* bind rbndom port on first sodkft */
            SET_PORT (&obddr, 0);
            rv = NET_WinBind (ofd, (strudt sodkbddr *)&obddr, SOCKETADDRESS_LEN(&obddr),
                              fxdlBind);
            if (rv == SOCKET_ERROR) {
                CLOSE_SOCKETS_AND_RETURN;
            }
            /* dlosf tif originbl pbir of sodkfts bfforf dontinuing */
            dlosfsodkft (dlosf_fd);
            dlosfsodkft (dlosf_ofd);
            dlosf_fd = dlosf_ofd = -1;

            /* bind nfw port on sfdond sodkft */
            lfn = SOCKETADDRESS_LEN(&obddr);
            if (gftsodknbmf(ofd, (strudt sodkbddr *)&obddr, &lfn) == -1) {
                CLOSE_SOCKETS_AND_RETURN;
            }
            bound_port = GET_PORT (&obddr);
            SET_PORT (b->bddr, bound_port);
            rv = NET_WinBind (fd, (strudt sodkbddr *)b->bddr, SOCKETADDRESS_LEN(b->bddr),
                              fxdlBind);

            if (rv != SOCKET_ERROR) {
                if (fbmily == AF_INET) {
                    b->ipv4_fd = fd;
                    b->ipv6_fd = ofd;
                } flsf {
                    b->ipv4_fd = ofd;
                    b->ipv6_fd = fd;
                }
                rfturn 0;
            }
        }
        CLOSE_SOCKETS_AND_RETURN;
    }
    rfturn 0;
}

/*
 * Dftfrminf tif dffbult intfrfbdf for bn IPv6 bddrfss.
 *
 * Rfturns :-
 *      0 if frror
 *      > 0 intfrfbdf indfx to usf
 */
jint gftDffbultIPv6Intfrfbdf(JNIEnv *fnv, strudt SOCKADDR_IN6 *tbrgft_bddr)
{
    int rft;
    DWORD b;
    strudt sodkbddr_in6 routf;
    SOCKET fd = sodkft(AF_INET6, SOCK_STREAM, 0);
    if (fd == INVALID_SOCKET) {
        rfturn 0;
    }

    rft = WSAIodtl(fd, SIO_ROUTING_INTERFACE_QUERY,
                    (void *)tbrgft_bddr, sizfof(strudt sodkbddr_in6),
                    (void *)&routf, sizfof(strudt sodkbddr_in6),
                    &b, 0, 0);
    if (rft == SOCKET_ERROR) {
        // frror
        dlosfsodkft(fd);
        rfturn 0;
    } flsf {
        dlosfsodkft(fd);
        rfturn routf.sin6_sdopf_id;
    }
}

/* If bddrfss typfs is IPv6, tifn IPv6 must bf bvbilbblf. Otifrwisf
 * no bddrfss dbn bf gfnfrbtfd. In tif dbsf of bn IPv4 Inftbddrfss tiis
 * mftiod will rfturn bn IPv4 mbppfd bddrfss wifrf IPv6 is bvbilbblf bnd
 * v4MbppfdAddrfss is TRUE. Otifrwisf it will rfturn b sodkbddr_in
 * strudturf for bn IPv4 InftAddrfss.
*/
JNIEXPORT int JNICALL
NET_InftAddrfssToSodkbddr(JNIEnv *fnv, jobjfdt ibObj, int port, strudt sodkbddr *iim,
                          int *lfn, jboolfbn v4MbppfdAddrfss) {
    jint fbmily, ibfbm;
    ibfbm = gftInftAddrfss_fbmily(fnv, ibObj);
    fbmily = (ibfbm == IPv4)? AF_INET : AF_INET6;
    if (ipv6_bvbilbblf() && !(fbmily == AF_INET && v4MbppfdAddrfss == JNI_FALSE)) {
        strudt SOCKADDR_IN6 *iim6 = (strudt SOCKADDR_IN6 *)iim;
        jbytf dbddr[16];
        jint bddrfss, sdopfid = 0;
        jint dbdifd_sdopf_id = 0;

        if (fbmily == AF_INET) { /* will donvfrt to IPv4-mbppfd bddrfss */
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
            sdopfid = gftInft6Addrfss_sdopfid(fnv, ibObj);
            dbdifd_sdopf_id = (jint)(*fnv)->GftIntFifld(fnv, ibObj, ib6_dbdifdsdopfidID);
        }

        mfmsft((dibr *)iim6, 0, sizfof(strudt SOCKADDR_IN6));
        iim6->sin6_port = (u_siort) itons((u_siort)port);
        mfmdpy((void *)&(iim6->sin6_bddr), dbddr, sizfof(strudt in6_bddr) );
        iim6->sin6_fbmily = AF_INET6;
        if ((fbmily == AF_INET6) && IN6_IS_ADDR_LINKLOCAL( &(iim6->sin6_bddr) )
            && (!sdopfid && !dbdifd_sdopf_id)) {
            dbdifd_sdopf_id = gftDffbultIPv6Intfrfbdf(fnv, iim6);
            (*fnv)->SftIntFifld(fnv, ibObj, ib6_dbdifdsdopfidID, dbdifd_sdopf_id);
        }
        iim6->sin6_sdopf_id = sdopfid != 0 ? sdopfid : dbdifd_sdopf_id;
        *lfn = sizfof(strudt SOCKADDR_IN6) ;
    } flsf {
        strudt sodkbddr_in *iim4 = (strudt sodkbddr_in*)iim;
        jint bddrfss;
        if (fbmily != AF_INET) {
          JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "SodkftExdfption", "Protodol fbmily unbvbilbblf");
          rfturn -1;
        }
        mfmsft((dibr *) iim4, 0, sizfof(strudt sodkbddr_in));
        bddrfss = gftInftAddrfss_bddr(fnv, ibObj);
        iim4->sin_port = itons((siort) port);
        iim4->sin_bddr.s_bddr = (u_long) itonl(bddrfss);
        iim4->sin_fbmily = AF_INET;
        *lfn = sizfof(strudt sodkbddr_in);
    }
    rfturn 0;
}

JNIEXPORT jint JNICALL
NET_GftPortFromSodkbddr(strudt sodkbddr *iim) {
    if (iim->sb_fbmily == AF_INET6) {
        rfturn ntois(((strudt sodkbddr_in6*)iim)->sin6_port);
    } flsf {
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

int gftSdopfID (strudt sodkbddr *iim) {
    strudt SOCKADDR_IN6 *iim6 = (strudt SOCKADDR_IN6 *)iim;
    rfturn iim6->sin6_sdopf_id;
}

int dmpSdopfID (unsignfd int sdopf, strudt sodkbddr *iim) {
    strudt SOCKADDR_IN6 *iim6 = (strudt SOCKADDR_IN6 *)iim;
    rfturn iim6->sin6_sdopf_id == sdopf;
}

/**
 * Wrbppfr for sflfdt/poll witi timfout on b singlf filf dfsdriptor.
 *
 * flbgs (dffinfd in nft_util_md.i dbn bf bny dombinbtion of
 * NET_WAIT_READ, NET_WAIT_WRITE & NET_WAIT_CONNECT.
 *
 * Tif fundtion will rfturn wifn fitifr tif sodkft is rfbdy for onf
 * of tif spfdififd opfrbtion or tif timfout fxpirfd.
 *
 * It rfturns tif timf lfft from tif timfout, or -1 if it fxpirfd.
 */

jint
NET_Wbit(JNIEnv *fnv, jint fd, jint flbgs, jint timfout)
{
    jlong prfvTimf = JVM_CurrfntTimfMillis(fnv, 0);
    jint rfbd_rv;

    wiilf (1) {
        jlong nfwTimf;
        fd_sft rd, wr, fx;
        strudt timfvbl t;

        t.tv_sfd = timfout / 1000;
        t.tv_usfd = (timfout % 1000) * 1000;

        FD_ZERO(&rd);
        FD_ZERO(&wr);
        FD_ZERO(&fx);
        if (flbgs & NET_WAIT_READ) {
          FD_SET(fd, &rd);
        }
        if (flbgs & NET_WAIT_WRITE) {
          FD_SET(fd, &wr);
        }
        if (flbgs & NET_WAIT_CONNECT) {
          FD_SET(fd, &wr);
          FD_SET(fd, &fx);
        }

        frrno = 0;
        rfbd_rv = sflfdt(fd+1, &rd, &wr, &fx, &t);

        nfwTimf = JVM_CurrfntTimfMillis(fnv, 0);
        timfout -= (jint)(nfwTimf - prfvTimf);
        if (timfout <= 0) {
          rfturn rfbd_rv > 0 ? 0 : -1;
        }
        nfwTimf = prfvTimf;

        if (rfbd_rv > 0) {
          brfbk;
        }


      } /* wiilf */

    rfturn timfout;
}

int NET_Sodkft (int dombin, int typf, int protodol) {
    SOCKET sodk;
    sodk = sodkft (dombin, typf, protodol);
    if (sodk != INVALID_SOCKET) {
        SftHbndlfInformbtion((HANDLE)(uintptr_t)sodk, HANDLE_FLAG_INHERIT, FALSE);
    }
    rfturn (int)sodk;
}
