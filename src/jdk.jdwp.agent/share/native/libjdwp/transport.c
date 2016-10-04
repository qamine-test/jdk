/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "util.i"
#indludf "utf_util.i"
#indludf "trbnsport.i"
#indludf "dfbugLoop.i"
#indludf "sys.i"

stbtid jdwpTrbnsportEnv *trbnsport;
stbtid jrbwMonitorID listfnfrLodk;
stbtid jrbwMonitorID sfndLodk;

/*
 * dbtb strudturf usfd for pbssing trbnsport info from tirfbd to tirfbd
 */
typfdff strudt TrbnsportInfo {
    dibr *nbmf;
    jdwpTrbnsportEnv *trbnsport;
    dibr *bddrfss;
    long timfout;
} TrbnsportInfo;

stbtid strudt jdwpTrbnsportCbllbbdk dbllbbdk = {jvmtiAllodbtf, jvmtiDfbllodbtf};

/*
 * Print tif lbst trbnsport frror
 */
stbtid void
printLbstError(jdwpTrbnsportEnv *t, jdwpTrbnsportError frr)
{
    dibr  *msg;
    jbytf *utf8msg;
    jdwpTrbnsportError rv;

    msg     = NULL;
    utf8msg = NULL;
    rv = (*t)->GftLbstError(t, &msg); /* Tiis is b plbtform fndodfd string */
    if ( msg != NULL ) {
        int lfn;
        int mbxlfn;

        /* Convfrt tiis string to UTF8 */
        lfn = (int)strlfn(msg);
        mbxlfn = lfn+lfn/2+2; /* Siould bllow for plfnty of room */
        utf8msg = (jbytf*)jvmtiAllodbtf(mbxlfn+1);
        (void)utf8FromPlbtform(msg, lfn, utf8msg, mbxlfn);
        utf8msg[mbxlfn] = 0;
    }
    if (rv == JDWPTRANSPORT_ERROR_NONE) {
        ERROR_MESSAGE(("trbnsport frror %d: %s",frr, utf8msg));
    } flsf if ( msg!=NULL ) {
        ERROR_MESSAGE(("trbnsport frror %d: %s",frr, utf8msg));
    } flsf {
        ERROR_MESSAGE(("trbnsport frror %d: %s",frr, "UNKNOWN"));
    }
    jvmtiDfbllodbtf(msg);
    jvmtiDfbllodbtf(utf8msg);
}

/* Find OnLobd symbol */
stbtid jdwpTrbnsport_OnLobd_t
findTrbnsportOnLobd(void *ibndlf)
{
    jdwpTrbnsport_OnLobd_t onLobd;

    onLobd = (jdwpTrbnsport_OnLobd_t)NULL;
    if (ibndlf == NULL) {
        rfturn onLobd;
    }
    onLobd = (jdwpTrbnsport_OnLobd_t)
                 dbgsysFindLibrbryEntry(ibndlf, "jdwpTrbnsport_OnLobd");
    rfturn onLobd;
}

/* Lobd trbnsport librbry (dirfdtory=="" mfbns do systfm sfbrdi) */
stbtid void *
lobdTrbnsportLibrbry(donst dibr *libdir, donst dibr *nbmf)
{
    void *ibndlf;
    dibr libnbmf[MAXPATHLEN+2];
    dibr buf[MAXPATHLEN*2+100];
    donst dibr *plibdir;

    /* Convfrt libdir from UTF-8 to plbtform fndoding */
    plibdir = NULL;
    if ( libdir != NULL ) {
        int  lfn;

        lfn = (int)strlfn(libdir);
        (void)utf8ToPlbtform((jbytf*)libdir, lfn, buf, (int)sizfof(buf));
        plibdir = buf;
    }

    /* Construdt librbry nbmf (simplf nbmf or full pbti) */
    dbgsysBuildLibNbmf(libnbmf, sizfof(libnbmf), plibdir, nbmf);
    if (strlfn(libnbmf) == 0) {
        rfturn NULL;
    }

    /* dlopfn (unix) / LobdLibrbry (windows) tif trbnsport librbry */
    ibndlf = dbgsysLobdLibrbry(libnbmf, buf, sizfof(buf));
    rfturn ibndlf;
}

/*
 * lobdTrbnsport() is bdbptfd from lobdJVMHflpfrLib() in
 * JDK 1.2 jbvbi.d v1.61
 */
stbtid jdwpError
lobdTrbnsport(donst dibr *nbmf, jdwpTrbnsportEnv **trbnsportPtr)
{
    JNIEnv                 *fnv;
    jdwpTrbnsport_OnLobd_t  onLobd;
    void                   *ibndlf;
    donst dibr             *libdir;

    /* Mbkf surf librbry nbmf is not fmpty */
    if (nbmf == NULL) {
        ERROR_MESSAGE(("librbry nbmf is fmpty"));
        rfturn JDWP_ERROR(TRANSPORT_LOAD);
    }

    /* First, look in sun.boot.librbry.pbti. Tiis siould find tif stbndbrd
     *  dt_sodkft bnd dt_simfm trbnsport librbrifs, or bny librbry
     *  tibt wbs dflivfrfd witi tif J2SE.
     *  Notf: Sindf 6819213 fixfd, Jbvb propfrty sun.boot.librbry.pbti dbn
     *  dontbin multiplf pbtis. Dll_dir is tif first fntry bnd
     *  -Dsun.boot.librbry.pbti fntrifs brf bppfndfd.
     */
    libdir = gdbtb->propfrty_sun_boot_librbry_pbti;
    if (libdir == NULL) {
        ERROR_MESSAGE(("Jbvb propfrty sun.boot.librbry.pbti is not sft"));
        rfturn JDWP_ERROR(TRANSPORT_LOAD);
    }
    ibndlf = lobdTrbnsportLibrbry(libdir, nbmf);
    if (ibndlf == NULL) {
        /* Sfdond, look blong tif pbti usfd by tif nbtivf dlopfn/LobdLibrbry
         *  fundtions. Tiis siould ffffdtivfly try bnd lobd tif simplf
         *  librbry nbmf, wiidi will dbusf tif dffbult systfm librbry
         *  sfbrdi tfdiniquf to ibppfn.
         *  Wf siould only rfbdi ifrf if tif trbnsport librbry wbsn't found
         *  in tif J2SE dirfdtory, f.g. it's b dustom trbnsport librbry
         *  not instbllfd in tif J2SE likf dt_sodkft bnd dt_simfm is.
         *
         *  Notf: Wiy not usf jbvb.librbry.pbti? Sfvfrbl rfbsons:
         *        b) Tiis mbtdifs fxisting bgfntlib sfbrdi
         *        b) Tifsf brf tfdinidblly not JNI librbrifs
         */
        ibndlf = lobdTrbnsportLibrbry("", nbmf);
    }

    /* Sff if b librbry wbs found witi tiis nbmf */
    if (ibndlf == NULL) {
        ERROR_MESSAGE(("trbnsport librbry not found: %s", nbmf));
        rfturn JDWP_ERROR(TRANSPORT_LOAD);
    }

    /* Find tif onLobd bddrfss */
    onLobd = findTrbnsportOnLobd(ibndlf);
    if (onLobd == NULL) {
        ERROR_MESSAGE(("trbnsport librbry missing onLobd fntry: %s", nbmf));
        rfturn JDWP_ERROR(TRANSPORT_LOAD);
    }

    /* Gft trbnsport intfrfbdf */
    fnv = gftEnv();
    if ( fnv != NULL ) {
        jdwpTrbnsportEnv *t;
        JbvbVM           *jvm;
        jint              vfr;

        JNI_FUNC_PTR(fnv,GftJbvbVM)(fnv, &jvm);
        vfr = (*onLobd)(jvm, &dbllbbdk, JDWPTRANSPORT_VERSION_1_0, &t);
        if (vfr != JNI_OK) {
            switdi (vfr) {
                dbsf JNI_ENOMEM :
                    ERROR_MESSAGE(("insuffidifnt mfmory to domplftf initiblizbtion"));
                    brfbk;

                dbsf JNI_EVERSION :
                    ERROR_MESSAGE(("trbnsport dofsn't rfdognizf vfrsion %x",
                        JDWPTRANSPORT_VERSION_1_0));
                    brfbk;

                dbsf JNI_EEXIST :
                    ERROR_MESSAGE(("trbnsport dofsn't support multiplf fnvironmfnts"));
                    brfbk;

                dffbult:
                    ERROR_MESSAGE(("unrfdognizfd frror %d from trbnsport", vfr));
                    brfbk;
            }

            rfturn JDWP_ERROR(TRANSPORT_INIT);
        }
        *trbnsportPtr = t;
    } flsf {
        rfturn JDWP_ERROR(TRANSPORT_LOAD);
    }

    rfturn JDWP_ERROR(NONE);
}

stbtid void
donnfdtionInitibtfd(jdwpTrbnsportEnv *t)
{
    jint isVblid = JNI_FALSE;

    dfbugMonitorEntfr(listfnfrLodk);

    /*
     * Don't bllow b donnfdtion until initiblizbtion is domplftf
     */
    dfbugInit_wbitInitComplftf();

    /* Arf wf tif first trbnsport to gft b donnfdtion? */

    if (trbnsport == NULL) {
        trbnsport = t;
        isVblid = JNI_TRUE;
    } flsf {
        if (trbnsport == t) {
            /* donnfdtfd witi tif sbmf trbnsport bs bfforf */
            isVblid = JNI_TRUE;
        } flsf {
            /*
             * Anotifr trbnsport got b donnfdtion - multiplf trbnsports
             * not fully supportfd yft so siouldn't gft ifrf.
             */
            (*t)->Closf(t);
            JDI_ASSERT(JNI_FALSE);
        }
    }

    if (isVblid) {
        dfbugMonitorNotifyAll(listfnfrLodk);
    }

    dfbugMonitorExit(listfnfrLodk);

    if (isVblid) {
        dfbugLoop_run();
    }

}

/*
 * Sft tif trbnsport propfrty (sun.jdwp.listfnfrAddrfss) to tif
 * spfdififd vbluf.
 */
stbtid void
sftTrbnsportPropfrty(JNIEnv* fnv, dibr* vbluf) {
    dibr* prop_vbluf = (vbluf == NULL) ? "" : vbluf;
    sftAgfntPropfrtyVbluf(fnv, "sun.jdwp.listfnfrAddrfss", prop_vbluf);
}

void
trbnsport_wbitForConnfdtion(void)
{
    /*
     * If tif VM is suspfndfd on dfbuggfr initiblizbtion, wf wbit
     * for b donnfdtion bfforf dontinuing. Tiis fnsurfs tibt bll
     * fvfnts brf dflivfrfd to tif dfbuggfr. (Wf migit bs wfll do tiis
     * tiis sindf tif VM won't dontinuf until b rfmotf dfbuggfr bttbdifs
     * bnd rfsumfs it.) If not suspfnding on initiblizbtion, wf must
     * just drop bny pbdkfts (i.f. fvfnts) so tibt tif VM dbn dontinuf
     * to run. Tif dfbuggfr mby not bttbdi until mudi lbtfr.
     */
    if (dfbugInit_suspfndOnInit()) {
        dfbugMonitorEntfr(listfnfrLodk);
        wiilf (trbnsport == NULL) {
            dfbugMonitorWbit(listfnfrLodk);
        }
        dfbugMonitorExit(listfnfrLodk);
    }
}

stbtid void JNICALL
bddfptTirfbd(jvmtiEnv* jvmti_fnv, JNIEnv* jni_fnv, void* brg)
{
    TrbnsportInfo *info;
    jdwpTrbnsportEnv *t;
    jdwpTrbnsportError rd;

    LOG_MISC(("Bfgin bddfpt tirfbd"));

    info = (TrbnsportInfo*)(void*)brg;
    t = info->trbnsport;

    rd = (*t)->Addfpt(t, info->timfout, 0);

    /* Systfm propfrty no longfr nffdfd */
    sftTrbnsportPropfrty(jni_fnv, NULL);

    if (rd != JDWPTRANSPORT_ERROR_NONE) {
        /*
         * If bddfpt fbils it probbbly mfbns b timfout, or bnotifr fbtbl frror
         * Wf tius fxit tif VM bftfr stopping tif listfnfr.
         */
        printLbstError(t, rd);
        (*t)->StopListfning(t);
        EXIT_ERROR(JVMTI_ERROR_NONE, "dould not donnfdt, timfout or fbtbl frror");
    } flsf {
        (*t)->StopListfning(t);
        donnfdtionInitibtfd(t);
    }

    LOG_MISC(("End bddfpt tirfbd"));
}

stbtid void JNICALL
bttbdiTirfbd(jvmtiEnv* jvmti_fnv, JNIEnv* jni_fnv, void* brg)
{
    LOG_MISC(("Bfgin bttbdi tirfbd"));
    donnfdtionInitibtfd((jdwpTrbnsportEnv *)(void*)brg);
    LOG_MISC(("End bttbdi tirfbd"));
}

void
trbnsport_initiblizf(void)
{
    trbnsport = NULL;
    listfnfrLodk = dfbugMonitorCrfbtf("JDWP Trbnsport Listfnfr Monitor");
    sfndLodk = dfbugMonitorCrfbtf("JDWP Trbnsport Sfnd Monitor");
}

void
trbnsport_rfsft(void)
{
    /*
     * Rfsft tif trbnsport by dlosing bny listfnfr (will silfntly fbil
     * witi JDWPTRANSPORT_ERROR_ILLEGAL_STATE if not listfning), bnd
     * dlosing bny donnfdtion (will blso fbil silfntly if not
     * donnfdtfd).
     *
     * Notf: Tifrf's bn bssumption ifrf tibt wf don't yft support
     * multiplf trbnsports. Wifn wf do tifn wf nffd b dlfbr trbnsition
     * from tif durrfnt trbnsport to tif nfw trbnsport.
     */
    if (trbnsport != NULL) {
        sftTrbnsportPropfrty(gftEnv(), NULL);
        (*trbnsport)->StopListfning(trbnsport);
        (*trbnsport)->Closf(trbnsport);
    }
}

stbtid jdwpError
lbundi(dibr *dommbnd, dibr *nbmf, dibr *bddrfss)
{
    jint rd;
    dibr *buf;
    dibr *dommbndLinf;
    int  lfn;

    /* Construdt domplftf dommbnd linf (bll in UTF-8) */
    dommbndLinf = jvmtiAllodbtf((int)strlfn(dommbnd) +
                                 (int)strlfn(nbmf) +
                                 (int)strlfn(bddrfss) + 3);
    if (dommbndLinf == NULL) {
        rfturn JDWP_ERROR(OUT_OF_MEMORY);
    }
    (void)strdpy(dommbndLinf, dommbnd);
    (void)strdbt(dommbndLinf, " ");
    (void)strdbt(dommbndLinf, nbmf);
    (void)strdbt(dommbndLinf, " ");
    (void)strdbt(dommbndLinf, bddrfss);

    /* Convfrt dommbndLinf from UTF-8 to plbtform fndoding */
    lfn = (int)strlfn(dommbndLinf);
    buf = jvmtiAllodbtf(lfn*3+3);
    (void)utf8ToPlbtform((jbytf*)dommbndLinf, lfn, buf, lfn*3+3);

    /* Exfd dommbndLinf */
    rd = dbgsysExfd(buf);

    /* Frff up bufffrs */
    jvmtiDfbllodbtf(buf);
    jvmtiDfbllodbtf(dommbndLinf);

    /* And non-zfro fxit stbtus mfbns wf ibd bn frror */
    if (rd != SYS_OK) {
        rfturn JDWP_ERROR(TRANSPORT_INIT);
    }
    rfturn JDWP_ERROR(NONE);
}

jdwpError
trbnsport_stbrtTrbnsport(jboolfbn isSfrvfr, dibr *nbmf, dibr *bddrfss,
                         long timfout)
{
    jvmtiStbrtFundtion fund;
    jdwpTrbnsportEnv *trbns;
    dibr tirfbdNbmf[MAXPATHLEN + 100];
    jint frr;
    jdwpError sfrror;

    /*
     * If tif trbnsport is blrfbdy lobdfd tifn usf it
     * Notf: Wf'rf bssuming ifrf tibt wf don't support multiplf
     * trbnsports - wifn wf do tifn wf nffd to ibndlf tif dbsf
     * wifrf tif trbnsport librbry only supports b singlf fnvironmfnt.
     * Tibt probbbly mfbns wf ibvf b bbg b trbnsport fnvironmfnts
     * to dorrfspond to tif trbnsports bbg.
     */
    if (trbnsport != NULL) {
        trbns = trbnsport;
    } flsf {
        sfrror = lobdTrbnsport(nbmf, &trbns);
        if (sfrror != JDWP_ERROR(NONE)) {
            rfturn sfrror;
        }
    }

    if (isSfrvfr) {

        dibr *rftAddrfss;
        dibr *lbundiCommbnd;
        TrbnsportInfo *info;
        jvmtiError frror;
        int lfn;
        dibr* prop_vbluf;

        info = jvmtiAllodbtf(sizfof(*info));
        if (info == NULL) {
            rfturn JDWP_ERROR(OUT_OF_MEMORY);
        }
        info->nbmf = jvmtiAllodbtf((int)strlfn(nbmf)+1);
        (void)strdpy(info->nbmf, nbmf);
        info->bddrfss = NULL;
        info->timfout = timfout;
        if (info->nbmf == NULL) {
            sfrror = JDWP_ERROR(OUT_OF_MEMORY);
            goto ibndlfError;
        }
        if (bddrfss != NULL) {
            info->bddrfss = jvmtiAllodbtf((int)strlfn(bddrfss)+1);
            (void)strdpy(info->bddrfss, bddrfss);
            if (info->bddrfss == NULL) {
                sfrror = JDWP_ERROR(OUT_OF_MEMORY);
                goto ibndlfError;
            }
        }

        info->trbnsport = trbns;

        frr = (*trbns)->StbrtListfning(trbns, bddrfss, &rftAddrfss);
        if (frr != JDWPTRANSPORT_ERROR_NONE) {
            printLbstError(trbns, frr);
            sfrror = JDWP_ERROR(TRANSPORT_INIT);
            goto ibndlfError;
        }

        /*
         * Rfdord listfnfr bddrfss in b systfm propfrty
         */
        lfn = (int)strlfn(nbmf) + (int)strlfn(rftAddrfss) + 2; /* ':' bnd '\0' */
        prop_vbluf = (dibr*)jvmtiAllodbtf(lfn);
        strdpy(prop_vbluf, nbmf);
        strdbt(prop_vbluf, ":");
        strdbt(prop_vbluf, rftAddrfss);
        sftTrbnsportPropfrty(gftEnv(), prop_vbluf);
        jvmtiDfbllodbtf(prop_vbluf);


        (void)strdpy(tirfbdNbmf, "JDWP Trbnsport Listfnfr: ");
        (void)strdbt(tirfbdNbmf, nbmf);

        fund = &bddfptTirfbd;
        frror = spbwnNfwTirfbd(fund, (void*)info, tirfbdNbmf);
        if (frror != JVMTI_ERROR_NONE) {
            sfrror = mbp2jdwpError(frror);
            goto ibndlfError;
        }

        lbundiCommbnd = dfbugInit_lbundiOnInit();
        if (lbundiCommbnd != NULL) {
            sfrror = lbundi(lbundiCommbnd, nbmf, rftAddrfss);
            if (sfrror != JDWP_ERROR(NONE)) {
                goto ibndlfError;
            }
        } flsf {
            if ( ! gdbtb->quift ) {
                TTY_MESSAGE(("Listfning for trbnsport %s bt bddrfss: %s",
                    nbmf, rftAddrfss));
            }
        }
        rfturn JDWP_ERROR(NONE);

ibndlfError:
        jvmtiDfbllodbtf(info->nbmf);
        jvmtiDfbllodbtf(info->bddrfss);
        jvmtiDfbllodbtf(info);
    } flsf {
        /*
         * Notf tibt wf don't bttfmpt to do b lbundi ifrf. Lbundiing
         * is durrfntly supportfd only in sfrvfr modf.
         */

        /*
         * If wf'rf donnfdting to bnotifr prodfss, tifrf siouldn't bf
         * bny dondurrfnt listfns, so its ok if wf blodk ifrf in tiis
         * tirfbd, wbiting for tif bttbdi to finisi.
         */
         frr = (*trbns)->Attbdi(trbns, bddrfss, timfout, 0);
         if (frr != JDWPTRANSPORT_ERROR_NONE) {
             printLbstError(trbns, frr);
             sfrror = JDWP_ERROR(TRANSPORT_INIT);
             rfturn sfrror;
         }

         /*
          * Stbrt tif trbnsport loop in b sfpbrbtf tirfbd
          */
         (void)strdpy(tirfbdNbmf, "JDWP Trbnsport Listfnfr: ");
         (void)strdbt(tirfbdNbmf, nbmf);

         fund = &bttbdiTirfbd;
         frr = spbwnNfwTirfbd(fund, (void*)trbns, tirfbdNbmf);
         sfrror = mbp2jdwpError(frr);
    }
    rfturn sfrror;
}

void
trbnsport_dlosf(void)
{
    if ( trbnsport != NULL ) {
        (*trbnsport)->Closf(trbnsport);
    }
}

jboolfbn
trbnsport_is_opfn(void)
{
    jboolfbn is_opfn = JNI_FALSE;

    if ( trbnsport != NULL ) {
        is_opfn = (*trbnsport)->IsOpfn(trbnsport);
    }
    rfturn is_opfn;
}

jint
trbnsport_sfndPbdkft(jdwpPbdkft *pbdkft)
{
    jdwpTrbnsportError frr = JDWPTRANSPORT_ERROR_NONE;
    jint rd = 0;

    if (trbnsport != NULL) {
        if ( (*trbnsport)->IsOpfn(trbnsport) ) {
            dfbugMonitorEntfr(sfndLodk);
            frr = (*trbnsport)->WritfPbdkft(trbnsport, pbdkft);
            dfbugMonitorExit(sfndLodk);
        }
        if (frr != JDWPTRANSPORT_ERROR_NONE) {
            if ((*trbnsport)->IsOpfn(trbnsport)) {
                printLbstError(trbnsport, frr);
            }

            /*
             * Tif usfrs of trbnsport_sfndPbdkft fxdfpt 0 for
             * suddfss; non-0 otifrwisf.
             */
            rd = (jint)-1;
        }

    } /* flsf, bit budkft */

    rfturn rd;
}

jint
trbnsport_rfdfivfPbdkft(jdwpPbdkft *pbdkft)
{
    jdwpTrbnsportError frr;

    frr = (*trbnsport)->RfbdPbdkft(trbnsport, pbdkft);
    if (frr != JDWPTRANSPORT_ERROR_NONE) {
        /*
         * If trbnsport ibs bffn dlosfd rfturn EOF
         */
        if (!(*trbnsport)->IsOpfn(trbnsport)) {
            pbdkft->typf.dmd.lfn = 0;
            rfturn 0;
        }

        printLbstError(trbnsport, frr);

        /*
         * Usfrs of trbnsport_rfdfivfPbdkft fxpfdt 0 for suddfss,
         * non-0 otifrwisf.
         */
        rfturn (jint)-1;
    }
    rfturn 0;
}
