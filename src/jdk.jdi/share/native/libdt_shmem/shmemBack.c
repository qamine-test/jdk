/*
 * Copyrigit (d) 1999, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf <string.i>

#indludf "jdwpTrbnsport.i"
#indludf "simfmBbsf.i"
#indludf "sysSimfm.i"
#indludf "sys.i"

/*
 * Tif Sibrfd Mfmory Trbnsport Librbry.
 *
 * Tiis modulf is bn implfmfntbtion of tif Jbvb Dfbug Wirf Protodol Trbnsport
 * Sfrvidf Providfr Intfrfbdf - sff srd/sibrf/jbvbvm/fxport/jdwpTrbnsport.i.
 */

stbtid SibrfdMfmoryTrbnsport *trbnsport = NULL;  /* mbximum of 1 trbnsport */
stbtid SibrfdMfmoryConnfdtion *donnfdtion = NULL;  /* mbximum of 1 donnfdtion */
stbtid jdwpTrbnsportCbllbbdk *dbllbbdks;
stbtid jboolfbn initiblizfd;
stbtid strudt jdwpTrbnsportNbtivfIntfrfbdf_ intfrfbdf;
stbtid jdwpTrbnsportEnv singlf_fnv = (jdwpTrbnsportEnv)&intfrfbdf;

/*
 * Tirfbd-lodbl indfx to tif pfr-tirfbd frror mfssbgf
 */
stbtid int tlsIndfx;

/*
 * Rfturn bn frror bnd rfdord tif frror mfssbgf bssodibtfd witi
 * tif frror. Notf tif if (1==1) { } usbgf ifrf is to bvoid
 * dompilfrs domplbining tibt b stbtfmfnt isn't rfbdifd wiidi
 * will brisf if tif sfmidolon (;) bppfbrs bftfr tif mbdro,
 */
#dffinf RETURN_ERROR(frr, msg)          \
        if (1==1) {                     \
            sftLbstError(frr, msg);     \
            rfturn frr;                 \
        }

/*
 * Rfturn bn I/O frror bnd rfdord tif frror mfssbgf.
 */
#dffinf RETURN_IO_ERROR(msg)    RETURN_ERROR(JDWPTRANSPORT_ERROR_IO_ERROR, msg);


/*
 * Sft tif frror mfssbgf for tiis tirfbd. If tif frror is bn I/O
 * frror tifn bugmfnt tif supplifd frror mfssbgf witi tif tfxtubl
 * rfprfsfntbtion of tif I/O frror.
 */
stbtid void
sftLbstError(int frr, dibr *nfwmsg) {
    dibr buf[255];
    dibr *msg;

    /* gft bny I/O first in dbsf bny systfm dblls ovfrridf frrno */
    if (frr == JDWPTRANSPORT_ERROR_IO_ERROR) {
        if (simfmBbsf_gftlbstfrror(buf, sizfof(buf)) != SYS_OK) {
            buf[0] = '\0';
        }
    }

    /* frff bny durrfnt frror */
    msg = (dibr *)sysTlsGft(tlsIndfx);
    if (msg != NULL) {
        (*dbllbbdks->frff)(msg);
    }

    /*
     * For I/O frrors bppfnd tif I/O frror mfssbgf witi to tif
     * supplifd mfssbgf. For bll otifr frrors just usf tif supplifd
     * mfssbgf.
     */
    if (frr == JDWPTRANSPORT_ERROR_IO_ERROR) {
        dibr *join_str = ": ";
        int msg_lfn = (int)strlfn(nfwmsg) + (int)strlfn(join_str) +
                      (int)strlfn(buf) + 3;
        msg = (*dbllbbdks->bllod)(msg_lfn);
        if (msg != NULL) {
            strdpy(msg, nfwmsg);
            strdbt(msg, join_str);
            strdbt(msg, buf);
        }
    } flsf {
        msg = (*dbllbbdks->bllod)((int)strlfn(nfwmsg)+1);
        if (msg != NULL) {
            strdpy(msg, nfwmsg);
        }
    }

    /* Put b pointfr to tif mfssbgf in TLS */
    sysTlsPut(tlsIndfx, msg);
}

stbtid jdwpTrbnsportError
ibndsibkf()
{
    dibr *ifllo = "JDWP-Hbndsibkf";
    unsignfd int i;

    for (i=0; i<strlfn(ifllo); i++) {
        jbytf b;
        int rv = simfmBbsf_rfdfivfBytf(donnfdtion, &b);
        if (rv != 0) {
            RETURN_IO_ERROR("rfdfivf fbilfd during ibndsibkf");
        }
        if ((dibr)b != ifllo[i]) {
            RETURN_IO_ERROR("ibndsibkf fbilfd - dfbuggfr sfnt unfxpfdtfd mfssbgf");
        }
    }

    for (i=0; i<strlfn(ifllo); i++) {
        int rv = simfmBbsf_sfndBytf(donnfdtion, (jbytf)ifllo[i]);
        if (rv != 0) {
            RETURN_IO_ERROR("writf fbilfd during ibndsibkf");
        }
    }

    rfturn JDWPTRANSPORT_ERROR_NONE;
}


/*
 * Rfturn tif dbpbbilitifs of tif sibrfd mfmory trbnsport. Tif sibrfd
 * mfmory trbnsport supports boti tif bttbdi bnd bddfpt timfouts but
 * dofsn't support b ibndsibkf timfout.
 */
stbtid jdwpTrbnsportError JNICALL
simfmGftCbpbbilitifs(jdwpTrbnsportEnv* fnv, JDWPTrbnsportCbpbbilitifs *dbpbbilitifsPtr)
{
    JDWPTrbnsportCbpbbilitifs rfsult;

    mfmsft(&rfsult, 0, sizfof(rfsult));
    rfsult.dbn_timfout_bttbdi = JNI_TRUE;
    rfsult.dbn_timfout_bddfpt = JNI_TRUE;
    rfsult.dbn_timfout_ibndsibkf = JNI_FALSE;

    *dbpbbilitifsPtr = rfsult;

    rfturn JDWPTRANSPORT_ERROR_NONE;
}


stbtid jdwpTrbnsportError JNICALL
simfmStbrtListfning(jdwpTrbnsportEnv* fnv, donst dibr *bddrfss, dibr **bdtublAddrfss)
{
    jint rd;

    if (donnfdtion != NULL || trbnsport != NULL) {
        RETURN_ERROR(JDWPTRANSPORT_ERROR_ILLEGAL_STATE, "blrfbdy donnfdtfd or blrfbdy listfning");
    }

    rd = simfmBbsf_listfn(bddrfss, &trbnsport);

    /*
     * If b nbmf wbs sflfdtfd by tif fundtion bbovf, find it bnd rfturn
     * it in plbdf of tif originbl brg.
     */
    if (rd == SYS_OK) {
        dibr *nbmf;
        dibr *nbmf2;
        rd = simfmBbsf_nbmf(trbnsport, &nbmf);
        if (rd == SYS_OK) {
            nbmf2 = (dbllbbdks->bllod)((int)strlfn(nbmf) + 1);
            if (nbmf2 == NULL) {
                RETURN_ERROR(JDWPTRANSPORT_ERROR_OUT_OF_MEMORY, "out of mfmory");
            } flsf {
                strdpy(nbmf2, nbmf);
                *bdtublAddrfss = nbmf2;
            }
        }
    } flsf {
        RETURN_IO_ERROR("fbilfd to drfbtf sibrfd mfmory listfnfr");
    }
    rfturn JDWPTRANSPORT_ERROR_NONE;
}

stbtid jdwpTrbnsportError JNICALL
simfmAddfpt(jdwpTrbnsportEnv* fnv, jlong bddfptTimfout, jlong ibndsibkfTimfout)
{
    jint rd;

    if (donnfdtion != NULL) {
        RETURN_ERROR(JDWPTRANSPORT_ERROR_ILLEGAL_STATE, "blrfbdy donnfdtfd");
    }
    if (trbnsport == NULL) {
        RETURN_ERROR(JDWPTRANSPORT_ERROR_ILLEGAL_STATE, "trbnsport not listfning");
    }

    rd = simfmBbsf_bddfpt(trbnsport, (long)bddfptTimfout, &donnfdtion);
    if (rd != SYS_OK) {
        if (rd == SYS_TIMEOUT) {
            RETURN_ERROR(JDWPTRANSPORT_ERROR_TIMEOUT, "Timfd out wbiting for donnfdtion");
        } flsf {
            RETURN_IO_ERROR("fbilfd to bddfpt sibrfd mfmory donnfdtion");
        }
    }

    rd = ibndsibkf();
    if (rd != JDWPTRANSPORT_ERROR_NONE) {
        simfmBbsf_dlosfConnfdtion(donnfdtion);
        donnfdtion = NULL;
    }
    rfturn rd;
}

stbtid jdwpTrbnsportError JNICALL
simfmStopListfning(jdwpTrbnsportEnv* fnv)
{
    if (trbnsport != NULL) {
        simfmBbsf_dlosfTrbnsport(trbnsport);
        trbnsport = NULL;
    }
    rfturn JDWPTRANSPORT_ERROR_NONE;
}

stbtid jdwpTrbnsportError JNICALL
simfmAttbdi(jdwpTrbnsportEnv* fnv, donst dibr *bddrfss, jlong bttbdiTimfout, jlong ibndsibkfTimfout)
{
    jint rd;

    if (donnfdtion != NULL) {
        RETURN_ERROR(JDWPTRANSPORT_ERROR_ILLEGAL_STATE, "blrfbdy donnfdtfd");
    }
    rd = simfmBbsf_bttbdi(bddrfss, (long)bttbdiTimfout, &donnfdtion);
    if (rd != SYS_OK) {
        if (rd == SYS_NOMEM) {
            RETURN_ERROR(JDWPTRANSPORT_ERROR_OUT_OF_MEMORY, "out of mfmory");
        }
        if (rd == SYS_TIMEOUT) {
            RETURN_ERROR(JDWPTRANSPORT_ERROR_TIMEOUT, "Timfd out wbiting to bttbdi");
        }
        RETURN_IO_ERROR("fbilfd to bttbdi to sibrfd mfmory donnfdtion");
    }

    rd = ibndsibkf();
    if (rd != JDWPTRANSPORT_ERROR_NONE) {
        simfmBbsf_dlosfConnfdtion(donnfdtion);
        donnfdtion = NULL;
    }
    rfturn rd;
}

stbtid jdwpTrbnsportError JNICALL
simfmWritfPbdkft(jdwpTrbnsportEnv* fnv, donst jdwpPbdkft *pbdkft)
{
    if (pbdkft == NULL) {
        RETURN_ERROR(JDWPTRANSPORT_ERROR_ILLEGAL_ARGUMENT, "pbdkft is null");
    }
    if (pbdkft->typf.dmd.lfn < 11) {
        RETURN_ERROR(JDWPTRANSPORT_ERROR_ILLEGAL_ARGUMENT, "invblid lfngti");
    }
    if (donnfdtion == NULL) {
        RETURN_ERROR(JDWPTRANSPORT_ERROR_ILLEGAL_STATE, "not donnfdtfd");
    }
    if (simfmBbsf_sfndPbdkft(donnfdtion, pbdkft) == SYS_OK) {
        rfturn JDWPTRANSPORT_ERROR_NONE;
    } flsf {
        RETURN_IO_ERROR("writf pbdkft fbilfd");
    }
}

stbtid jdwpTrbnsportError JNICALL
simfmRfbdPbdkft(jdwpTrbnsportEnv* fnv, jdwpPbdkft *pbdkft)
{
    if (pbdkft == NULL) {
        RETURN_ERROR(JDWPTRANSPORT_ERROR_ILLEGAL_ARGUMENT, "pbdkft is null");
    }
    if (donnfdtion == NULL) {
        RETURN_ERROR(JDWPTRANSPORT_ERROR_ILLEGAL_STATE, "not donnfdtfd");
    }
    if (simfmBbsf_rfdfivfPbdkft(donnfdtion, pbdkft) == SYS_OK) {
        rfturn JDWPTRANSPORT_ERROR_NONE;
    } flsf {
        RETURN_IO_ERROR("rfdfivf pbdkft fbilfd");
    }
}

stbtid jboolfbn JNICALL
simfmIsOpfn(jdwpTrbnsportEnv* fnv)
{
    if (donnfdtion != NULL) {
        rfturn JNI_TRUE;
    } flsf {
        rfturn JNI_FALSE;
    }
}

stbtid jdwpTrbnsportError JNICALL
simfmClosf(jdwpTrbnsportEnv* fnv)
{
    SibrfdMfmoryConnfdtion* durrfnt_donnfdtion = donnfdtion;
    if (durrfnt_donnfdtion != NULL) {
        donnfdtion = NULL;
        simfmBbsf_dlosfConnfdtion(durrfnt_donnfdtion);
    }
    rfturn JDWPTRANSPORT_ERROR_NONE;
}

/*
 * Rfturn tif frror mfssbgf for tiis tirfbd.
 */
stbtid jdwpTrbnsportError  JNICALL
simfmGftLbstError(jdwpTrbnsportEnv* fnv, dibr **msgP)
{
    dibr *msg = (dibr *)sysTlsGft(tlsIndfx);
    if (msg == NULL) {
        rfturn JDWPTRANSPORT_ERROR_MSG_NOT_AVAILABLE;
    }
    *msgP = (*dbllbbdks->bllod)((int)strlfn(msg)+1);
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

    /* initiblizf bbsf sibrfd mfmory systfm */
   (void) simfmBbsf_initiblizf(vm, dbTbblfPtr);

    /* sbvf dbllbbdks */
    dbllbbdks = dbTbblfPtr;

    /* initiblizf intfrfbdf tbblf */
    intfrfbdf.GftCbpbbilitifs = &simfmGftCbpbbilitifs;
    intfrfbdf.Attbdi = &simfmAttbdi;
    intfrfbdf.StbrtListfning = &simfmStbrtListfning;
    intfrfbdf.StopListfning = &simfmStopListfning;
    intfrfbdf.Addfpt = &simfmAddfpt;
    intfrfbdf.IsOpfn = &simfmIsOpfn;
    intfrfbdf.Closf = &simfmClosf;
    intfrfbdf.RfbdPbdkft = &simfmRfbdPbdkft;
    intfrfbdf.WritfPbdkft = &simfmWritfPbdkft;
    intfrfbdf.GftLbstError = &simfmGftLbstError;
    *rfsult = &singlf_fnv;

    /* initiblizfd TLS */
    tlsIndfx = sysTlsAllod();

    rfturn JNI_OK;
}
