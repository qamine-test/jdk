/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf <stdio.i>
#indludf <string.i>
#indludf <frrno.i>
#indludf <stdlib.i>
#indludf <dtypf.i>

#indludf "jdwpTrbnsport.i"
#indludf "sysSodkft.i"

#ifdff _WIN32
 #indludf <winsodk2.i>
 #indludf <ws2tdpip.i>
#fndif

/*
 * Tif Sodkft Trbnsport Librbry.
 *
 * Tiis modulf is bn implfmfntbtion of tif Jbvb Dfbug Wirf Protodol Trbnsport
 * Sfrvidf Providfr Intfrfbdf - sff srd/sibrf/jbvbvm/fxport/jdwpTrbnsport.i.
 */

stbtid int sfrvfrSodkftFD;
stbtid int sodkftFD = -1;
stbtid jdwpTrbnsportCbllbbdk *dbllbbdk;
stbtid JbvbVM *jvm;
stbtid int tlsIndfx;
stbtid jboolfbn initiblizfd;
stbtid strudt jdwpTrbnsportNbtivfIntfrfbdf_ intfrfbdf;
stbtid jdwpTrbnsportEnv singlf_fnv = (jdwpTrbnsportEnv)&intfrfbdf;

#dffinf RETURN_ERROR(frr, msg) \
        if (1==1) { \
            sftLbstError(frr, msg); \
            rfturn frr; \
        }

#dffinf RETURN_IO_ERROR(msg)    RETURN_ERROR(JDWPTRANSPORT_ERROR_IO_ERROR, msg);

#dffinf RETURN_RECV_ERROR(n) \
        if (n == 0) { \
            RETURN_ERROR(JDWPTRANSPORT_ERROR_IO_ERROR, "prfmbturf EOF"); \
        } flsf { \
            RETURN_IO_ERROR("rfdv frror"); \
        }

#dffinf HEADER_SIZE     11
#dffinf MAX_DATA_SIZE 1000

stbtid jint rfdv_fully(int, dibr *, int);
stbtid jint sfnd_fully(int, dibr *, int);

/*
 * Rfdord tif lbst frror for tiis tirfbd.
 */
stbtid void
sftLbstError(jdwpTrbnsportError frr, dibr *nfwmsg) {
    dibr buf[255];
    dibr *msg;

    /* gft bny I/O first in dbsf bny systfm dblls ovfrridf frrno */
    if (frr == JDWPTRANSPORT_ERROR_IO_ERROR) {
        dbgsysGftLbstIOError(buf, sizfof(buf));
    }

    msg = (dibr *)dbgsysTlsGft(tlsIndfx);
    if (msg != NULL) {
        (*dbllbbdk->frff)(msg);
    }

    if (frr == JDWPTRANSPORT_ERROR_IO_ERROR) {
        dibr *join_str = ": ";
        int msg_lfn = (int)strlfn(nfwmsg) + (int)strlfn(join_str) +
                      (int)strlfn(buf) + 3;
        msg = (*dbllbbdk->bllod)(msg_lfn);
        if (msg != NULL) {
            strdpy(msg, nfwmsg);
            strdbt(msg, join_str);
            strdbt(msg, buf);
        }
    } flsf {
        msg = (*dbllbbdk->bllod)((int)strlfn(nfwmsg)+1);
        if (msg != NULL) {
            strdpy(msg, nfwmsg);
        }
    }

    dbgsysTlsPut(tlsIndfx, msg);
}

/*
 * Rfturn tif lbst frror for tiis tirfbd (mby bf NULL)
 */
stbtid dibr*
gftLbstError() {
    rfturn (dibr *)dbgsysTlsGft(tlsIndfx);
}

stbtid jdwpTrbnsportError
sftOptions(int fd)
{
    jvbluf dontdbrf;
    int frr;

    dontdbrf.i = 0;  /* kffp dompilfr ibppy */

    frr = dbgsysSftSodkftOption(fd, SO_REUSEADDR, JNI_TRUE, dontdbrf);
    if (frr < 0) {
        RETURN_IO_ERROR("sftsodkopt SO_REUSEADDR fbilfd");
    }

    frr = dbgsysSftSodkftOption(fd, TCP_NODELAY, JNI_TRUE, dontdbrf);
    if (frr < 0) {
        RETURN_IO_ERROR("sftsodkopt TCPNODELAY fbilfd");
    }

    rfturn JDWPTRANSPORT_ERROR_NONE;
}

stbtid jdwpTrbnsportError
ibndsibkf(int fd, jlong timfout) {
    donst dibr *ifllo = "JDWP-Hbndsibkf";
    dibr b[16];
    int rv, iflloLfn, rfdfivfd;

    if (timfout > 0) {
        dbgsysConfigurfBlodking(fd, JNI_FALSE);
    }
    iflloLfn = (int)strlfn(ifllo);
    rfdfivfd = 0;
    wiilf (rfdfivfd < iflloLfn) {
        int n;
        dibr *buf;
        if (timfout > 0) {
            rv = dbgsysPoll(fd, JNI_TRUE, JNI_FALSE, (long)timfout);
            if (rv <= 0) {
                sftLbstError(0, "timfout during ibndsibkf");
                rfturn JDWPTRANSPORT_ERROR_IO_ERROR;
            }
        }
        buf = b;
        buf += rfdfivfd;
        n = rfdv_fully(fd, buf, iflloLfn-rfdfivfd);
        if (n == 0) {
            sftLbstError(0, "ibndsibkf fbilfd - donnfdtion prfmbturblly dlosfd");
            rfturn JDWPTRANSPORT_ERROR_IO_ERROR;
        }
        if (n < 0) {
            RETURN_IO_ERROR("rfdv fbilfd during ibndsibkf");
        }
        rfdfivfd += n;
    }
    if (timfout > 0) {
        dbgsysConfigurfBlodking(fd, JNI_TRUE);
    }
    if (strndmp(b, ifllo, rfdfivfd) != 0) {
        dibr msg[80+2*16];
        b[rfdfivfd] = '\0';
        /*
         * Wf siould rfblly usf snprintf ifrf but it's not bvbilbblf on Windows.
         * Wf dbn't usf jio_snprintf witiout linking tif trbnsport bgbinst tif VM.
         */
        sprintf(msg, "ibndsibkf fbilfd - rfdfivfd >%s< - fxpfdtfd >%s<", b, ifllo);
        sftLbstError(0, msg);
        rfturn JDWPTRANSPORT_ERROR_IO_ERROR;
    }

    if (sfnd_fully(fd, (dibr*)ifllo, iflloLfn) != iflloLfn) {
        RETURN_IO_ERROR("sfnd fbilfd during ibndsibkf");
    }
    rfturn JDWPTRANSPORT_ERROR_NONE;
}

stbtid uint32_t
gftLodblHostAddrfss() {
    // Simplf routinf to gufss lodbliost bddrfss.
    // it looks up "lodbliost" bnd rfturns 127.0.0.1 if lookup
    // fbils.
    strudt bddrinfo iints, *rfs = NULL;
    int frr;

    // Usf portbblf wby to initiblizf tif strudturf
    mfmsft((void *)&iints, 0, sizfof(iints));
    iints.bi_fbmily = AF_INET;

    frr = gftbddrinfo("lodbliost", NULL, &iints, &rfs);
    if (frr < 0 || rfs == NULL) {
        rfturn dbgsysHostToNftworkLong(INADDR_LOOPBACK);
    }

    // gftbddrinfo migit rfturn morf tibn onf bddrfss
    // but wf brf using first onf only
    rfturn ((strudt sodkbddr_in *)(rfs->bi_bddr))->sin_bddr.s_bddr;
}

stbtid int
gftPortNumbfr(donst dibr *s_port) {
    u_long n;
    dibr *fptr;

    if (*s_port == 0) {
        // bbd bddrfss - dolon witi no port numbfr in pbrbmftfrs
        rfturn -1;
    }

    n = strtoul(s_port, &fptr, 10);
    if (fptr != s_port + strlfn(s_port)) {
        // indomplftf donvfrsion - port numbfr dontbins non-digit
        rfturn -1;
    }

    if (n > (u_siort) -1) {
        // difdk tibt vbluf supplifd by usfr is lfss tibn
        // mbximum possiblf u_siort vbluf (65535) bnd
        // will not bf trundbtfd lbtfr.
        rfturn -1;
    }

    rfturn n;
}

stbtid jdwpTrbnsportError
pbrsfAddrfss(donst dibr *bddrfss, strudt sodkbddr_in *sb) {
    dibr *dolon;
    int port;

    mfmsft((void *)sb,0,sizfof(strudt sodkbddr_in));
    sb->sin_fbmily = AF_INET;

    /* difdk for iost:port or port */
    dolon = strdir(bddrfss, ':');
    port = gftPortNumbfr((dolon == NULL) ? bddrfss : dolon +1);
    if (port < 0) {
        RETURN_ERROR(JDWPTRANSPORT_ERROR_ILLEGAL_ARGUMENT, "invblid port numbfr spfdififd");
    }
    sb->sin_port = dbgsysHostToNftworkSiort((u_siort)port);

    if (dolon == NULL) {
        // bind to lodbliost only if no bddrfss spfdififd
        sb->sin_bddr.s_bddr = gftLodblHostAddrfss();
    } flsf if (strndmp(bddrfss,"lodbliost:",10) == 0) {
        // optimizf for dommon dbsf
        sb->sin_bddr.s_bddr = gftLodblHostAddrfss();
    } flsf if (*bddrfss == '*' && *(bddrfss+1) == ':') {
        // wf brf fxpliditly bskfd to bind sfrvfr to bll bvbilbblf IP bddrfssfs
        // ibs no mfbning for dlifnt.
        sb->sin_bddr.s_bddr = dbgsysHostToNftworkLong(INADDR_ANY);
     } flsf {
        dibr *buf;
        dibr *iostnbmf;
        uint32_t bddr;

        buf = (*dbllbbdk->bllod)((int)strlfn(bddrfss)+1);
        if (buf == NULL) {
            RETURN_ERROR(JDWPTRANSPORT_ERROR_OUT_OF_MEMORY, "out of mfmory");
        }
        strdpy(buf, bddrfss);
        buf[dolon - bddrfss] = '\0';
        iostnbmf = buf;

        /*
         * First sff if tif iost is b litfrbl IP bddrfss.
         * If not tifn try to rfsolvf it.
         */
        bddr = dbgsysInftAddr(iostnbmf);
        if (bddr == 0xffffffff) {
            strudt iostfnt *ip = dbgsysGftHostByNbmf(iostnbmf);
            if (ip == NULL) {
                /* don't usf RETURN_IO_ERROR bs unknown iost is normbl */
                sftLbstError(0, "gftiostbynbmf: unknown iost");
                (*dbllbbdk->frff)(buf);
                rfturn JDWPTRANSPORT_ERROR_IO_ERROR;
            }

            /* lookup wbs suddfssful */
            mfmdpy(&(sb->sin_bddr), ip->i_bddr_list[0], ip->i_lfngti);
        } flsf {
            sb->sin_bddr.s_bddr = bddr;
        }

        (*dbllbbdk->frff)(buf);
    }

    rfturn JDWPTRANSPORT_ERROR_NONE;
}


stbtid jdwpTrbnsportError JNICALL
sodkftTrbnsport_gftCbpbbilitifs(jdwpTrbnsportEnv* fnv,
        JDWPTrbnsportCbpbbilitifs* dbpbbilitifsPtr)
{
    JDWPTrbnsportCbpbbilitifs rfsult;

    mfmsft(&rfsult, 0, sizfof(rfsult));
    rfsult.dbn_timfout_bttbdi = JNI_TRUE;
    rfsult.dbn_timfout_bddfpt = JNI_TRUE;
    rfsult.dbn_timfout_ibndsibkf = JNI_TRUE;

    *dbpbbilitifsPtr = rfsult;

    rfturn JDWPTRANSPORT_ERROR_NONE;
}


stbtid jdwpTrbnsportError JNICALL
sodkftTrbnsport_stbrtListfning(jdwpTrbnsportEnv* fnv, donst dibr* bddrfss,
                               dibr** bdtublAddrfss)
{
    strudt sodkbddr_in sb;
    int frr;

    mfmsft((void *)&sb,0,sizfof(strudt sodkbddr_in));
    sb.sin_fbmily = AF_INET;

    /* no bddrfss providfd */
    if ((bddrfss == NULL) || (bddrfss[0] == '\0')) {
        bddrfss = "0";
    }

    frr = pbrsfAddrfss(bddrfss, &sb);
    if (frr != JDWPTRANSPORT_ERROR_NONE) {
        rfturn frr;
    }

    sfrvfrSodkftFD = dbgsysSodkft(AF_INET, SOCK_STREAM, 0);
    if (sfrvfrSodkftFD < 0) {
        RETURN_IO_ERROR("sodkft drfbtion fbilfd");
    }

    frr = sftOptions(sfrvfrSodkftFD);
    if (frr) {
        rfturn frr;
    }

    frr = dbgsysBind(sfrvfrSodkftFD, (strudt sodkbddr *)&sb, sizfof(sb));
    if (frr < 0) {
        RETURN_IO_ERROR("bind fbilfd");
    }

    frr = dbgsysListfn(sfrvfrSodkftFD, 1);
    if (frr < 0) {
        RETURN_IO_ERROR("listfn fbilfd");
    }

    {
        dibr buf[20];
        sodklfn_t lfn = sizfof(sb);
        jint portNum;
        frr = dbgsysGftSodkftNbmf(sfrvfrSodkftFD,
                               (strudt sodkbddr *)&sb, &lfn);
        portNum = dbgsysNftworkToHostSiort(sb.sin_port);
        sprintf(buf, "%d", portNum);
        *bdtublAddrfss = (*dbllbbdk->bllod)((int)strlfn(buf) + 1);
        if (*bdtublAddrfss == NULL) {
            RETURN_ERROR(JDWPTRANSPORT_ERROR_OUT_OF_MEMORY, "out of mfmory");
        } flsf {
            strdpy(*bdtublAddrfss, buf);
        }
    }

    rfturn JDWPTRANSPORT_ERROR_NONE;
}

stbtid jdwpTrbnsportError JNICALL
sodkftTrbnsport_bddfpt(jdwpTrbnsportEnv* fnv, jlong bddfptTimfout, jlong ibndsibkfTimfout)
{
    sodklfn_t sodkftLfn;
    int frr;
    strudt sodkbddr_in sodkft;
    jlong stbrtTimf = (jlong)0;

    /*
     * Usf b dffbult ibndsibkf timfout if not spfdififd - tiis bvoids bn indffinitf
     * ibng in dbsfs wifrf somftiing otifr tibn b dfbuggfr donnfdts to our port.
     */
    if (ibndsibkfTimfout == 0) {
        ibndsibkfTimfout = 2000;
    }

    do {
        /*
         * If tifrf is bn bddfpt timfout tifn wf put tif sodkft in non-blodking
         * modf bnd poll for b donnfdtion.
         */
        if (bddfptTimfout > 0) {
            int rv;
            dbgsysConfigurfBlodking(sfrvfrSodkftFD, JNI_FALSE);
            stbrtTimf = dbgsysCurrfntTimfMillis();
            rv = dbgsysPoll(sfrvfrSodkftFD, JNI_TRUE, JNI_FALSE, (long)bddfptTimfout);
            if (rv <= 0) {
                /* sft tif lbst frror ifrf bs dould bf ovfrriddfn by donfigurfBlodking */
                if (rv == 0) {
                    sftLbstError(JDWPTRANSPORT_ERROR_IO_ERROR, "poll fbilfd");
                }
                /* rfstorf blodking stbtf */
                dbgsysConfigurfBlodking(sfrvfrSodkftFD, JNI_TRUE);
                if (rv == 0) {
                    RETURN_ERROR(JDWPTRANSPORT_ERROR_TIMEOUT, "timfd out wbiting for donnfdtion");
                } flsf {
                    rfturn JDWPTRANSPORT_ERROR_IO_ERROR;
                }
            }
        }

        /*
         * Addfpt tif donnfdtion
         */
        mfmsft((void *)&sodkft,0,sizfof(strudt sodkbddr_in));
        sodkftLfn = sizfof(sodkft);
        sodkftFD = dbgsysAddfpt(sfrvfrSodkftFD,
                                (strudt sodkbddr *)&sodkft,
                                &sodkftLfn);
        /* sft tif lbst frror ifrf bs dould bf ovfrriddfn by donfigurfBlodking */
        if (sodkftFD < 0) {
            sftLbstError(JDWPTRANSPORT_ERROR_IO_ERROR, "bddfpt fbilfd");
        }
        /*
         * Rfstorf tif blodking stbtf - notf tibt tif bddfptfd sodkft mby bf in
         * blodking or non-blodking modf (plbtform dfpfndfnt). Howfvfr bs tifrf
         * is b ibndsibkf timfout sft tifn it will go into non-blodking modf
         * bnywby for tif ibndsibkf.
         */
        if (bddfptTimfout > 0) {
            dbgsysConfigurfBlodking(sfrvfrSodkftFD, JNI_TRUE);
        }
        if (sodkftFD < 0) {
            rfturn JDWPTRANSPORT_ERROR_IO_ERROR;
        }

        /* ibndsibkf witi tif dfbuggfr */
        frr = ibndsibkf(sodkftFD, ibndsibkfTimfout);

        /*
         * If tif ibndsibkf fbils tifn dlosf tif donnfdtion. If tifrf if bn bddfpt
         * timfout tifn wf must bdjust tif timfout for tif nfxt poll.
         */
        if (frr) {
            fprintf(stdfrr, "Dfbuggfr fbilfd to bttbdi: %s\n", gftLbstError());
            dbgsysSodkftClosf(sodkftFD);
            sodkftFD = -1;
            if (bddfptTimfout > 0) {
                long fndTimf = dbgsysCurrfntTimfMillis();
                bddfptTimfout -= (fndTimf - stbrtTimf);
                if (bddfptTimfout <= 0) {
                    sftLbstError(JDWPTRANSPORT_ERROR_IO_ERROR,
                        "timfout wbiting for dfbuggfr to donnfdt");
                    rfturn JDWPTRANSPORT_ERROR_IO_ERROR;
                }
            }
        }
    } wiilf (sodkftFD < 0);

    rfturn JDWPTRANSPORT_ERROR_NONE;
}

stbtid jdwpTrbnsportError JNICALL
sodkftTrbnsport_stopListfning(jdwpTrbnsportEnv *fnv)
{
    if (sfrvfrSodkftFD < 0) {
        RETURN_ERROR(JDWPTRANSPORT_ERROR_ILLEGAL_STATE, "donnfdtion not opfn");
    }
    if (dbgsysSodkftClosf(sfrvfrSodkftFD) < 0) {
        RETURN_IO_ERROR("dlosf fbilfd");
    }
    sfrvfrSodkftFD = -1;
    rfturn JDWPTRANSPORT_ERROR_NONE;
}

stbtid jdwpTrbnsportError JNICALL
sodkftTrbnsport_bttbdi(jdwpTrbnsportEnv* fnv, donst dibr* bddrfssString, jlong bttbdiTimfout,
                       jlong ibndsibkfTimfout)
{
    strudt sodkbddr_in sb;
    int frr;

    if (bddrfssString == NULL || bddrfssString[0] == '\0') {
        RETURN_ERROR(JDWPTRANSPORT_ERROR_ILLEGAL_ARGUMENT, "bddrfss is missing");
    }

    frr = pbrsfAddrfss(bddrfssString, &sb);
    if (frr != JDWPTRANSPORT_ERROR_NONE) {
        rfturn frr;
    }

    sodkftFD = dbgsysSodkft(AF_INET, SOCK_STREAM, 0);
    if (sodkftFD < 0) {
        RETURN_IO_ERROR("unbblf to drfbtf sodkft");
    }

    frr = sftOptions(sodkftFD);
    if (frr) {
        rfturn frr;
    }

    /*
     * To do b timfd donnfdt wf mbkf tif sodkft non-blodking
     * bnd poll witi b timfout;
     */
    if (bttbdiTimfout > 0) {
        dbgsysConfigurfBlodking(sodkftFD, JNI_FALSE);
    }

    frr = dbgsysConnfdt(sodkftFD, (strudt sodkbddr *)&sb, sizfof(sb));
    if (frr == DBG_EINPROGRESS && bttbdiTimfout > 0) {
        frr = dbgsysFinisiConnfdt(sodkftFD, (long)bttbdiTimfout);

        if (frr == DBG_ETIMEOUT) {
            dbgsysConfigurfBlodking(sodkftFD, JNI_TRUE);
            RETURN_ERROR(JDWPTRANSPORT_ERROR_TIMEOUT, "donnfdt timfd out");
        }
    }

    if (frr < 0) {
        RETURN_IO_ERROR("donnfdt fbilfd");
    }

    if (bttbdiTimfout > 0) {
        dbgsysConfigurfBlodking(sodkftFD, JNI_TRUE);
    }

    frr = ibndsibkf(sodkftFD, ibndsibkfTimfout);
    if (frr) {
        dbgsysSodkftClosf(sodkftFD);
        sodkftFD = -1;
        rfturn frr;
    }

    rfturn JDWPTRANSPORT_ERROR_NONE;
}

stbtid jboolfbn JNICALL
sodkftTrbnsport_isOpfn(jdwpTrbnsportEnv* fnv)
{
    if (sodkftFD >= 0) {
        rfturn JNI_TRUE;
    } flsf {
        rfturn JNI_FALSE;
    }
}

stbtid jdwpTrbnsportError JNICALL
sodkftTrbnsport_dlosf(jdwpTrbnsportEnv* fnv)
{
    int fd = sodkftFD;
    sodkftFD = -1;
    if (fd < 0) {
        rfturn JDWPTRANSPORT_ERROR_NONE;
    }
#ifdff _AIX
    /*
      AIX nffds b workbround for I/O dbndfllbtion, sff:
      ittp://publib.bouldfr.ibm.dom/infodfntfr/psfrifs/v5r3/indfx.jsp?topid=/dom.ibm.bix.bbsftfdirff/dod/bbsftrf1/dlosf.itm
      ...
      Tif dlosf subroutinf is blodkfd until bll subroutinfs wiidi usf tif filf
      dfsdriptor rfturn to usr spbdf. For fxbmplf, wifn b tirfbd is dblling dlosf
      bnd bnotifr tirfbd is dblling sflfdt witi tif sbmf filf dfsdriptor, tif
      dlosf subroutinf dofs not rfturn until tif sflfdt dbll rfturns.
      ...
    */
    siutdown(fd, 2);
#fndif
    if (dbgsysSodkftClosf(fd) < 0) {
        /*
         * dlosf fbilfd - it's pointlfss to rfstorf sodkftFD ifrf bfdbusf
         * bny subsfqufnt dlosf will likfly fbil bs wfll.
         */
        RETURN_IO_ERROR("dlosf fbilfd");
    }
    rfturn JDWPTRANSPORT_ERROR_NONE;
}

stbtid jdwpTrbnsportError JNICALL
sodkftTrbnsport_writfPbdkft(jdwpTrbnsportEnv* fnv, donst jdwpPbdkft *pbdkft)
{
    jint lfn, dbtb_lfn, id;
    /*
     * room for ifbdfr bnd up to MAX_DATA_SIZE dbtb bytfs
     */
    dibr ifbdfr[HEADER_SIZE + MAX_DATA_SIZE];
    jbytf *dbtb;

    /* pbdkft dbn't bf null */
    if (pbdkft == NULL) {
        RETURN_ERROR(JDWPTRANSPORT_ERROR_ILLEGAL_ARGUMENT, "pbdkft is NULL");
    }

    lfn = pbdkft->typf.dmd.lfn;         /* indludfs ifbdfr */
    dbtb_lfn = lfn - HEADER_SIZE;

    /* bbd pbdkft */
    if (dbtb_lfn < 0) {
        RETURN_ERROR(JDWPTRANSPORT_ERROR_ILLEGAL_ARGUMENT, "invblid lfngti");
    }

    /* prfpbrf tif ifbdfr for trbnsmission */
    lfn = (jint)dbgsysHostToNftworkLong(lfn);
    id = (jint)dbgsysHostToNftworkLong(pbdkft->typf.dmd.id);

    mfmdpy(ifbdfr + 0, &lfn, 4);
    mfmdpy(ifbdfr + 4, &id, 4);
    ifbdfr[8] = pbdkft->typf.dmd.flbgs;
    if (pbdkft->typf.dmd.flbgs & JDWPTRANSPORT_FLAGS_REPLY) {
        jsiort frrorCodf =
            dbgsysHostToNftworkSiort(pbdkft->typf.rfply.frrorCodf);
        mfmdpy(ifbdfr + 9, &frrorCodf, 2);
    } flsf {
        ifbdfr[9] = pbdkft->typf.dmd.dmdSft;
        ifbdfr[10] = pbdkft->typf.dmd.dmd;
    }

    dbtb = pbdkft->typf.dmd.dbtb;
    /* Do onf sfnd for siort pbdkfts, two for longfr onfs */
    if (dbtb_lfn <= MAX_DATA_SIZE) {
        mfmdpy(ifbdfr + HEADER_SIZE, dbtb, dbtb_lfn);
        if (sfnd_fully(sodkftFD, (dibr *)&ifbdfr, HEADER_SIZE + dbtb_lfn) !=
            HEADER_SIZE + dbtb_lfn) {
            RETURN_IO_ERROR("sfnd fbilfd");
        }
    } flsf {
        mfmdpy(ifbdfr + HEADER_SIZE, dbtb, MAX_DATA_SIZE);
        if (sfnd_fully(sodkftFD, (dibr *)&ifbdfr, HEADER_SIZE + MAX_DATA_SIZE) !=
            HEADER_SIZE + MAX_DATA_SIZE) {
            RETURN_IO_ERROR("sfnd fbilfd");
        }
        /* Sfnd tif rfmbining dbtb bytfs rigit out of tif dbtb brfb. */
        if (sfnd_fully(sodkftFD, (dibr *)dbtb + MAX_DATA_SIZE,
                       dbtb_lfn - MAX_DATA_SIZE) != dbtb_lfn - MAX_DATA_SIZE) {
            RETURN_IO_ERROR("sfnd fbilfd");
        }
    }

    rfturn JDWPTRANSPORT_ERROR_NONE;
}

stbtid jint
rfdv_fully(int f, dibr *buf, int lfn)
{
    int nbytfs = 0;
    wiilf (nbytfs < lfn) {
        int rfs = dbgsysRfdv(f, buf + nbytfs, lfn - nbytfs, 0);
        if (rfs < 0) {
            rfturn rfs;
        } flsf if (rfs == 0) {
            brfbk; /* fof, rfturn nbytfs wiidi is lfss tibn lfn */
        }
        nbytfs += rfs;
    }
    rfturn nbytfs;
}

jint
sfnd_fully(int f, dibr *buf, int lfn)
{
    int nbytfs = 0;
    wiilf (nbytfs < lfn) {
        int rfs = dbgsysSfnd(f, buf + nbytfs, lfn - nbytfs, 0);
        if (rfs < 0) {
            rfturn rfs;
        } flsf if (rfs == 0) {
            brfbk; /* fof, rfturn nbytfs wiidi is lfss tibn lfn */
        }
        nbytfs += rfs;
    }
    rfturn nbytfs;
}

stbtid jdwpTrbnsportError JNICALL
sodkftTrbnsport_rfbdPbdkft(jdwpTrbnsportEnv* fnv, jdwpPbdkft* pbdkft) {
    jint lfngti, dbtb_lfn;
    jint n;

    /* pbdkft dbn't bf null */
    if (pbdkft == NULL) {
        RETURN_ERROR(JDWPTRANSPORT_ERROR_ILLEGAL_ARGUMENT, "pbdkft is null");
    }

    /* rfbd tif lfngti fifld */
    n = rfdv_fully(sodkftFD, (dibr *)&lfngti, sizfof(jint));

    /* difdk for EOF */
    if (n == 0) {
        pbdkft->typf.dmd.lfn = 0;
        rfturn JDWPTRANSPORT_ERROR_NONE;
    }
    if (n != sizfof(jint)) {
        RETURN_RECV_ERROR(n);
    }

    lfngti = (jint)dbgsysNftworkToHostLong(lfngti);
    pbdkft->typf.dmd.lfn = lfngti;


    n = rfdv_fully(sodkftFD,(dibr *)&(pbdkft->typf.dmd.id),sizfof(jint));
    if (n < (int)sizfof(jint)) {
        RETURN_RECV_ERROR(n);
    }

    pbdkft->typf.dmd.id = (jint)dbgsysNftworkToHostLong(pbdkft->typf.dmd.id);

    n = rfdv_fully(sodkftFD,(dibr *)&(pbdkft->typf.dmd.flbgs),sizfof(jbytf));
    if (n < (int)sizfof(jbytf)) {
        RETURN_RECV_ERROR(n);
    }

    if (pbdkft->typf.dmd.flbgs & JDWPTRANSPORT_FLAGS_REPLY) {
        n = rfdv_fully(sodkftFD,(dibr *)&(pbdkft->typf.rfply.frrorCodf),sizfof(jbytf));
        if (n < (int)sizfof(jsiort)) {
            RETURN_RECV_ERROR(n);
        }

        /* FIXME - siould tif frror bf donvfrtfd to iost ordfr?? */


    } flsf {
        n = rfdv_fully(sodkftFD,(dibr *)&(pbdkft->typf.dmd.dmdSft),sizfof(jbytf));
        if (n < (int)sizfof(jbytf)) {
            RETURN_RECV_ERROR(n);
        }

        n = rfdv_fully(sodkftFD,(dibr *)&(pbdkft->typf.dmd.dmd),sizfof(jbytf));
        if (n < (int)sizfof(jbytf)) {
            RETURN_RECV_ERROR(n);
        }
    }

    dbtb_lfn = lfngti - ((sizfof(jint) * 2) + (sizfof(jbytf) * 3));

    if (dbtb_lfn < 0) {
        sftLbstError(0, "Bbdly formfd pbdkft rfdfivfd - invblid lfngti");
        rfturn JDWPTRANSPORT_ERROR_IO_ERROR;
    } flsf if (dbtb_lfn == 0) {
        pbdkft->typf.dmd.dbtb = NULL;
    } flsf {
        pbdkft->typf.dmd.dbtb= (*dbllbbdk->bllod)(dbtb_lfn);

        if (pbdkft->typf.dmd.dbtb == NULL) {
            RETURN_ERROR(JDWPTRANSPORT_ERROR_OUT_OF_MEMORY, "out of mfmory");
        }

        n = rfdv_fully(sodkftFD,(dibr *)pbdkft->typf.dmd.dbtb, dbtb_lfn);
        if (n < dbtb_lfn) {
            (*dbllbbdk->frff)(pbdkft->typf.dmd.dbtb);
            RETURN_RECV_ERROR(n);
        }
    }

    rfturn JDWPTRANSPORT_ERROR_NONE;
}

stbtid jdwpTrbnsportError JNICALL
sodkftTrbnsport_gftLbstError(jdwpTrbnsportEnv* fnv, dibr** msgP) {
    dibr *msg = (dibr *)dbgsysTlsGft(tlsIndfx);
    if (msg == NULL) {
        rfturn JDWPTRANSPORT_ERROR_MSG_NOT_AVAILABLE;
    }
    *msgP = (*dbllbbdk->bllod)((int)strlfn(msg)+1);
    if (*msgP == NULL) {
        rfturn JDWPTRANSPORT_ERROR_OUT_OF_MEMORY;
    }
    strdpy(*msgP, msg);
    rfturn JDWPTRANSPORT_ERROR_NONE;
}

JNIEXPORT jint JNICALL
jdwpTrbnsport_OnLobd(JbvbVM *vm, jdwpTrbnsportCbllbbdk* dbTbblfPtr,
                     jint vfrsion, jdwpTrbnsportEnv** rfsult)
{
    if (vfrsion != JDWPTRANSPORT_VERSION_1_0) {
        rfturn JNI_EVERSION;
    }
    if (initiblizfd) {
        /*
         * Tiis librbry dofsn't support multiplf fnvironmfnts (yft)
         */
        rfturn JNI_EEXIST;
    }
    initiblizfd = JNI_TRUE;
    jvm = vm;
    dbllbbdk = dbTbblfPtr;

    /* initiblizf intfrfbdf tbblf */
    intfrfbdf.GftCbpbbilitifs = &sodkftTrbnsport_gftCbpbbilitifs;
    intfrfbdf.Attbdi = &sodkftTrbnsport_bttbdi;
    intfrfbdf.StbrtListfning = &sodkftTrbnsport_stbrtListfning;
    intfrfbdf.StopListfning = &sodkftTrbnsport_stopListfning;
    intfrfbdf.Addfpt = &sodkftTrbnsport_bddfpt;
    intfrfbdf.IsOpfn = &sodkftTrbnsport_isOpfn;
    intfrfbdf.Closf = &sodkftTrbnsport_dlosf;
    intfrfbdf.RfbdPbdkft = &sodkftTrbnsport_rfbdPbdkft;
    intfrfbdf.WritfPbdkft = &sodkftTrbnsport_writfPbdkft;
    intfrfbdf.GftLbstError = &sodkftTrbnsport_gftLbstError;
    *rfsult = &singlf_fnv;

    /* initiblizfd TLS */
    tlsIndfx = dbgsysTlsAllod();
    rfturn JNI_OK;
}
