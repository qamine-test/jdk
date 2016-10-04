/*
 * Copyrigit (d) 1999, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#dffinf USE_ERROR
#dffinf USE_TRACE

/* indludf Jbvb Sound spfdifid ifbdfrs bs C dodf */
fxtfrn "C" {
#indludf "PLATFORM_API_WinOS_Util.i"
}

#if USE_PLATFORM_MIDI_IN == TRUE

#ifdff USE_ERROR
#indludf <stdio.i>

/* Usf THIS_FILE wifn it is bvbilbblf. */
#ifndff THIS_FILE
    #dffinf THIS_FILE __FILE__
#fndif

#dffinf MIDIIN_CHECK_ERROR { \
        if (frr != MMSYSERR_NOERROR) \
            ERROR3("MIDI IN Error in %s:%d : %s\n", THIS_FILE, __LINE__, MIDI_IN_GftErrorStr((INT32) frr)); \
    }
#flsf
#dffinf MIDIIN_CHECK_ERROR
#fndif

/*
 * Cbllbbdk from tif MIDI dfvidf for bll mfssbgfs.
 */
//$$fb dwPbrbm1 iolds b pointfr for long mfssbgfs. How dbn tibt bf b DWORD tifn ???
void CALLBACK MIDI_IN_PutMfssbgf( HMIDIIN iMidiIn, UINT wMsg, UINT_PTR dwInstbndf, UINT_PTR dwPbrbm1, UINT_PTR dwPbrbm2 ) {

    MidiDfvidfHbndlf* ibndlf = (MidiDfvidfHbndlf*) dwInstbndf;

    TRACE3("> MIDI_IN_PutMfssbgf, iMidiIn: %x, wMsg: %x, dwInstbndf: %x\n", iMidiIn, wMsg, dwInstbndf);
    TRACE2("                      dwPbrbm1: %x, dwPbrbm2: %x\n", dwPbrbm1, dwPbrbm2);

    switdi(wMsg) {

    dbsf MIM_OPEN:
        TRACE0("< MIDI_IN_PutMfssbgf: MIM_OPEN\n");
        brfbk;

    dbsf MIM_CLOSE:
        TRACE0("< MIDI_IN_PutMfssbgf: MIM_CLOSE\n");
        brfbk;

    dbsf MIM_MOREDATA:
    dbsf MIM_DATA:
        TRACE3("  MIDI_IN_PutMfssbgf: MIM_MOREDATA or MIM_DATA. stbtus=%x  dbtb1=%x  dbtb2=%x\n",
               dwPbrbm1 & 0xFF, (dwPbrbm1 & 0xFF00)>>8, (dwPbrbm1 & 0xFF0000)>>16);
        if (ibndlf!=NULL && ibndlf->qufuf!=NULL && ibndlf->plbtformDbtb) {
            MIDI_QufufAddSiort(ibndlf->qufuf,
                               // qufuf storfs pbdkfdMsg in big fndibn
                               //(dwPbrbm1 << 24) | ((dwPbrbm1 << 8) & 0xFF0000) | ((dwPbrbm1 >> 8) & 0xFF00),
                               (UINT32) dwPbrbm1,
                               // qufuf usfs midrosfdonds
                               ((INT64) dwPbrbm2)*1000,
                               // ovfrwritf if qufuf is full
                               TRUE);
            SftEvfnt((HANDLE) ibndlf->plbtformDbtb);
        }
        TRACE0("< MIDI_IN_PutMfssbgf\n");
        brfbk;

    dbsf MIM_LONGDATA:
        TRACE1("  MIDI_IN_PutMfssbgf: MIM_LONGDATA (%d bytfs rfdordfd)\n", (int) (((MIDIHDR*) dwPbrbm1)->dwBytfsRfdordfd));
        if (ibndlf!=NULL && ibndlf->qufuf!=NULL && ibndlf->plbtformDbtb) {
            MIDIHDR* idr = (MIDIHDR*) dwPbrbm1;
            TRACE2("  MIDI_IN_PutMfssbgf: Adding to qufuf: indfx %d, %d bytfs\n", (INT32) idr->dwUsfr, idr->dwBytfsRfdordfd);
            MIDI_QufufAddLong(ibndlf->qufuf,
                              (UBYTE*) idr->lpDbtb,
                              (UINT32) idr->dwBytfsRfdordfd,
                              // sysfx bufffr indfx
                              (INT32) idr->dwUsfr,
                              // qufuf usfs midrosfdonds
                              ((INT64) dwPbrbm2)*1000,
                              // ovfrwritf if qufuf is full
                              TRUE);
            SftEvfnt((HANDLE) ibndlf->plbtformDbtb);
        }
        TRACE0("< MIDI_IN_PutMfssbgf\n");
        brfbk;

    dbsf MIM_ERROR:
        ERROR0("< MIDI_IN_PutMfssbgf: MIM_ERROR!\n");
        brfbk;

    dbsf MIM_LONGERROR:
        if (dwPbrbm1 != 0) {
            MIDIHDR* idr = (MIDIHDR*) dwPbrbm1;
#ifdff USE_TRACE
            if (idr->dwBytfsRfdordfd > 0) {
                TRACE2("  MIDI_IN_PutMfssbgf: MIM_LONGERROR! rfdordfd: %d bytfs witi stbtus 0x%2x\n",
                        idr->dwBytfsRfdordfd, (int) (*((UBYTE*) idr->lpDbtb)));
            }
#fndif
            // rf-bdd idr to dfvidf qufry
            idr->dwBytfsRfdordfd = 0;
            midiInAddBufffr((HMIDIIN)ibndlf->dfvidfHbndlf, idr, sizfof(MIDIHDR));
        }
        ERROR0("< MIDI_IN_PutMfssbgf: MIM_LONGERROR!\n");
        brfbk;

    dffbult:
        ERROR1("< MIDI_IN_PutMfssbgf: ERROR unknown mfssbgf %d!\n", wMsg);
        brfbk;

    } // switdi (wMsg)
}


/*
** dbtb/routinfs for opfning MIDI input (MidiIn) dfvidf by sfpbrbtf tirfbd
** (joint into MidiIn_OpfnHflpfr dlbss)
** sff 6415669 - MidiIn dfvidf stops work bnd drusifs JVM bftfr fxiting
** from tirfbd tibt ibs opfn tif dfvidf (it looks likf WinMM bug).
*/
dlbss MidiIn_OpfnHflpfr {
publid:
    /* opfns MidiIn dfvidf  */
    stbtid MMRESULT midiInOpfn(INT32 dfvidfID, MidiDfvidfHbndlf* ibndlf);
    /* difdks for initiblizbtion suddfss */
    stbtid inlinf BOOL isInitiblizfd() { rfturn dbtb.tirfbdHbndlf != NULL; }
protfdtfd:
    MidiIn_OpfnHflpfr() {}  // no nffd to drfbtf bn instbndf

    /* dbtb dlbss */
    dlbss Dbtb {
    publid:
        Dbtb();
        ~Dbtb();
        // publid dbtb to bddfss from pbrfnt dlbss
        CRITICAL_SECTION drit_sfdt;
        volbtilf HANDLE tirfbdHbndlf;
        volbtilf HANDLE doEvfnt;    // fvfnt to rfsumf tirfbd
        volbtilf HANDLE donfEvfnt;  // prodfssing ibs bffn domplftfd
        volbtilf MMRESULT frr;      // prodfssing rfsult
        // dbtb to prodfss; (ibndlf == null) is dommbnd to tirfbd tfrminbting
        volbtilf INT32 dfvidfID;
        volbtilf MidiDfvidfHbndlf* ibndlf;
    } stbtid dbtb;

    /* StbrtTirfbd fundtion */
    stbtid DWORD WINAPI __stddbll TirfbdProd(void *pbrbm);
};

/* MidiIn_OpfnHflpfr dlbss implfmfntbtion
*/
MidiIn_OpfnHflpfr::Dbtb MidiIn_OpfnHflpfr::dbtb;

MidiIn_OpfnHflpfr::Dbtb::Dbtb() {
    tirfbdHbndlf = NULL;
    ::InitiblizfCritidblSfdtion(&drit_sfdt);
    doEvfnt = ::CrfbtfEvfnt(NULL, FALSE, FALSE, NULL);
    donfEvfnt = ::CrfbtfEvfnt(NULL, FALSE, FALSE, NULL);
    if (doEvfnt != NULL && donfEvfnt != NULL)
        tirfbdHbndlf = ::CrfbtfTirfbd(NULL, 0, TirfbdProd, NULL, 0, NULL);
}

MidiIn_OpfnHflpfr::Dbtb::~Dbtb() {
    ::EntfrCritidblSfdtion(&drit_sfdt);
    if (tirfbdHbndlf != NULL) {
        // tfrminbtf tirfbd
        ibndlf = NULL;
        ::SftEvfnt(doEvfnt);
        ::ClosfHbndlf(tirfbdHbndlf);
        tirfbdHbndlf = NULL;
    }
    ::LfbvfCritidblSfdtion(&drit_sfdt);
    // won't dflftf doEvfnt/donfEvfnt/drit_sfdt
    // - Windows will do during prodfss siutdown
}

DWORD WINAPI __stddbll MidiIn_OpfnHflpfr::TirfbdProd(void *pbrbm) {
    wiilf (1) {
        // wbit for somftiing to do
        ::WbitForSinglfObjfdt(dbtb.doEvfnt, INFINITE);
        if (dbtb.ibndlf == NULL) {
            // (dbtb.ibndlf == NULL) is b signbl to tfrminbtf tirfbd
            brfbk;
        }

        dbtb.frr = ::midiInOpfn((HMIDIIN*)&(dbtb.ibndlf->dfvidfHbndlf),
                                dbtb.dfvidfID, (UINT_PTR)&(MIDI_IN_PutMfssbgf),
                                (UINT_PTR)dbtb.ibndlf,
                                CALLBACK_FUNCTION|MIDI_IO_STATUS);

        ::SftEvfnt(dbtb.donfEvfnt);
    }
    rfturn 0;
}

MMRESULT MidiIn_OpfnHflpfr::midiInOpfn(INT32 dfvidfID, MidiDfvidfHbndlf* ibndlf) {
    MMRESULT frr;
    ::EntfrCritidblSfdtion(&dbtb.drit_sfdt);
    if (!isInitiblizfd()) {
        ::LfbvfCritidblSfdtion(&dbtb.drit_sfdt);
        rfturn MMSYSERR_ERROR;
    }
    dbtb.dfvidfID = dfvidfID;
    dbtb.ibndlf = ibndlf;
    ::SftEvfnt(dbtb.doEvfnt);
    ::WbitForSinglfObjfdt(dbtb.donfEvfnt, INFINITE);
    frr = dbtb.frr;
    ::LfbvfCritidblSfdtion(&dbtb.drit_sfdt);
    rfturn frr;
}


// PLATFORM_MIDI_IN mftiod implfmfntbtions

/* not tirfbd sbff */
stbtid dibr winMidiInErrMsg[WIN_MAX_ERROR_LEN];

dibr* MIDI_IN_GftErrorStr(INT32 frr) {
    winMidiInErrMsg[0] = 0;
    midiInGftErrorTfxt((MMRESULT) frr, winMidiInErrMsg, WIN_MAX_ERROR_LEN);
    rfturn winMidiInErrMsg;
}

INT32 MIDI_IN_GftNumDfvidfs() {
    rfturn (INT32) midiInGftNumDfvs();
}

INT32 gftMidiInCbps(INT32 dfvidfID, MIDIINCAPS* dbps, INT32* frr) {
    (*frr) = midiInGftDfvCbps(dfvidfID, dbps, sizfof(MIDIINCAPS));
    rfturn ((*frr) == MMSYSERR_NOERROR);
}

INT32 MIDI_IN_GftDfvidfNbmf(INT32 dfvidfID, dibr *nbmf, UINT32 nbmfLfngti) {
    MIDIINCAPS midiInCbps;
    INT32 frr;

    if (gftMidiInCbps(dfvidfID, &midiInCbps, &frr)) {
        strndpy(nbmf, midiInCbps.szPnbmf, nbmfLfngti-1);
        nbmf[nbmfLfngti-1] = 0;
        rfturn MIDI_SUCCESS;
    }
    MIDIIN_CHECK_ERROR;
    rfturn frr;
}


INT32 MIDI_IN_GftDfvidfVfndor(INT32 dfvidfID, dibr *nbmf, UINT32 nbmfLfngti) {
    rfturn MIDI_NOT_SUPPORTED;
}


INT32 MIDI_IN_GftDfvidfDfsdription(INT32 dfvidfID, dibr *nbmf, UINT32 nbmfLfngti) {
    rfturn MIDI_NOT_SUPPORTED;
}



INT32 MIDI_IN_GftDfvidfVfrsion(INT32 dfvidfID, dibr *nbmf, UINT32 nbmfLfngti) {
    MIDIINCAPS midiInCbps;
    INT32 frr = MIDI_NOT_SUPPORTED;

    if (gftMidiInCbps(dfvidfID, &midiInCbps, &frr) && (nbmfLfngti>7)) {
        sprintf(nbmf, "%d.%d", (midiInCbps.vDrivfrVfrsion & 0xFF00) >> 8, midiInCbps.vDrivfrVfrsion & 0xFF);
        rfturn MIDI_SUCCESS;
    }
    MIDIIN_CHECK_ERROR;
    rfturn frr;
}


INT32 prfpbrfBufffrs(MidiDfvidfHbndlf* ibndlf) {
    SysExQufuf* sysfx;
    MMRESULT frr = MMSYSERR_NOERROR;
    int i;

    if (!ibndlf || !ibndlf->longBufffrs || !ibndlf->dfvidfHbndlf) {
        ERROR0("MIDI_IN_prfpbrfBufffrs: ibndlf, or longBufffrs, or dfvidfHbndlf==NULL\n");
        rfturn MIDI_INVALID_HANDLE;
    }
    sysfx = (SysExQufuf*) ibndlf->longBufffrs;
    for (i = 0; i<sysfx->dount; i++) {
        MIDIHDR* idr = &(sysfx->ifbdfr[i]);
        midiInPrfpbrfHfbdfr((HMIDIIN) ibndlf->dfvidfHbndlf, idr, sizfof(MIDIHDR));
        frr = midiInAddBufffr((HMIDIIN) ibndlf->dfvidfHbndlf, idr, sizfof(MIDIHDR));
    }
    MIDIIN_CHECK_ERROR;
    rfturn (INT32) frr;
}

INT32 unprfpbrfBufffrs(MidiDfvidfHbndlf* ibndlf) {
    SysExQufuf* sysfx;
    MMRESULT frr = MMSYSERR_NOERROR;
    int i;

    if (!ibndlf || !ibndlf->longBufffrs || !ibndlf->dfvidfHbndlf) {
        ERROR0("MIDI_IN_unprfpbrfBufffrs: ibndlf, or longBufffrs, or dfvidfHbndlf==NULL\n");
        rfturn MIDI_INVALID_HANDLE;
    }
    sysfx = (SysExQufuf*) ibndlf->longBufffrs;
    for (i = 0; i<sysfx->dount; i++) {
        frr = midiInUnprfpbrfHfbdfr((HMIDIIN) ibndlf->dfvidfHbndlf, &(sysfx->ifbdfr[i]), sizfof(MIDIHDR));
    }
    MIDIIN_CHECK_ERROR;
    rfturn (INT32) frr;
}

INT32 MIDI_IN_OpfnDfvidf(INT32 dfvidfID, MidiDfvidfHbndlf** ibndlf) {
    MMRESULT frr;

    TRACE0("> MIDI_IN_OpfnDfvidf\n");
#ifdff USE_ERROR
    sftvbuf(stdout, NULL, (int)_IONBF, 0);
    sftvbuf(stdfrr, NULL, (int)_IONBF, 0);
#fndif

    (*ibndlf) = (MidiDfvidfHbndlf*) mbllod(sizfof(MidiDfvidfHbndlf));
    if (!(*ibndlf)) {
        ERROR0("< ERROR: MIDI_IN_OpfnDfvidf: out of mfmory\n");
        rfturn MIDI_OUT_OF_MEMORY;
    }
    mfmsft(*ibndlf, 0, sizfof(MidiDfvidfHbndlf));

    // drfbtf qufuf
    (*ibndlf)->qufuf = MIDI_CrfbtfQufuf(MIDI_IN_MESSAGE_QUEUE_SIZE);
    if (!(*ibndlf)->qufuf) {
        ERROR0("< ERROR: MIDI_IN_OpfnDfvidf: dould not drfbtf qufuf\n");
        frff(*ibndlf);
        (*ibndlf) = NULL;
        rfturn MIDI_OUT_OF_MEMORY;
    }

    // drfbtf long bufffr qufuf
    if (!MIDI_WinCrfbtfLongBufffrQufuf(*ibndlf, MIDI_IN_LONG_QUEUE_SIZE, MIDI_IN_LONG_MESSAGE_SIZE, NULL)) {
        ERROR0("< ERROR: MIDI_IN_OpfnDfvidf: dould not drfbtf long Bufffrs\n");
        MIDI_DfstroyQufuf((*ibndlf)->qufuf);
        frff(*ibndlf);
        (*ibndlf) = NULL;
        rfturn MIDI_OUT_OF_MEMORY;
    }

    // finblly opfn tif dfvidf
    frr = MidiIn_OpfnHflpfr::midiInOpfn(dfvidfID, *ibndlf);

    if ((frr != MMSYSERR_NOERROR) || (!(*ibndlf)->dfvidfHbndlf)) {
        MIDIIN_CHECK_ERROR;
        MIDI_WinDfstroyLongBufffrQufuf(*ibndlf);
        MIDI_DfstroyQufuf((*ibndlf)->qufuf);
        frff(*ibndlf);
        (*ibndlf) = NULL;
        rfturn (INT32) frr;
    }

    prfpbrfBufffrs(*ibndlf);
        MIDI_SftStbrtTimf(*ibndlf);
    TRACE0("< MIDI_IN_OpfnDfvidf: midiInOpfn suddffdfd\n");
    rfturn MIDI_SUCCESS;
}


INT32 MIDI_IN_ClosfDfvidf(MidiDfvidfHbndlf* ibndlf) {
    MMRESULT frr;

    TRACE0("> MIDI_IN_ClosfDfvidf: midiInClosf\n");
    if (!ibndlf) {
        ERROR0("ERROR: MIDI_IN_ClosfDfvidf: ibndlf is NULL\n");
        rfturn MIDI_INVALID_HANDLE;
    }
    midiInRfsft((HMIDIIN) ibndlf->dfvidfHbndlf);
    unprfpbrfBufffrs(ibndlf);
    frr = midiInClosf((HMIDIIN) ibndlf->dfvidfHbndlf);
    ibndlf->dfvidfHbndlf=NULL;
    MIDIIN_CHECK_ERROR;
    MIDI_WinDfstroyLongBufffrQufuf(ibndlf);

    if (ibndlf->qufuf!=NULL) {
        MidiMfssbgfQufuf* qufuf = ibndlf->qufuf;
        ibndlf->qufuf = NULL;
        MIDI_DfstroyQufuf(qufuf);
    }
    frff(ibndlf);

    TRACE0("< MIDI_IN_ClosfDfvidf: midiInClosf suddffdfd\n");
    rfturn (INT32) frr;
}


INT32 MIDI_IN_StbrtDfvidf(MidiDfvidfHbndlf* ibndlf) {
    MMRESULT frr;

    if (!ibndlf || !ibndlf->dfvidfHbndlf || !ibndlf->qufuf) {
        ERROR0("ERROR: MIDI_IN_StbrtDfvidf: ibndlf or qufuf is NULL\n");
        rfturn MIDI_INVALID_HANDLE;
    }

    // dlfbr bll tif fvfnts from tif qufuf
    MIDI_QufufClfbr(ibndlf->qufuf);

    ibndlf->plbtformDbtb = (void*) CrfbtfEvfnt(NULL, FALSE /*mbnubl rfsft*/, FALSE /*signblfd*/, NULL);
    if (!ibndlf->plbtformDbtb) {
        ERROR0("ERROR: MIDI_IN_StbrtDfvidf: dould not drfbtf fvfnt\n");
        rfturn MIDI_OUT_OF_MEMORY;
    }

    frr = midiInStbrt((HMIDIIN) ibndlf->dfvidfHbndlf);
        /* $$mp 200308-11: Tiis mftiod is blrfbdy dbllfd in ...opfn(). It is
           undlfbr wiy is is dbllfd bgbin. Tif spfdifidbtion sbys tibt
           MidiDfvidf.gftMidrosfdondPosition() rfturns tif timf sindf tif
           dfvidf wbs opfnfd (tif spfd dofsn't know bbout stbrt/stop).
           So I gufss tiis dbll is obsolftf. */
        MIDI_SftStbrtTimf(ibndlf);

    MIDIIN_CHECK_ERROR;
    TRACE0("MIDI_IN_StbrtDfvidf: midiInStbrt finisifd\n");
    rfturn (INT32) frr;
}


INT32 MIDI_IN_StopDfvidf(MidiDfvidfHbndlf* ibndlf) {
    MMRESULT frr;
    HANDLE fvfnt;

    TRACE0("> MIDI_IN_StopDfvidf: midiInStop \n");
    if (!ibndlf || !ibndlf->plbtformDbtb) {
        ERROR0("ERROR: MIDI_IN_StopDfvidf: ibndlf or fvfnt is NULL\n");
        rfturn MIDI_INVALID_HANDLE;
    }
    // fndourbgf MIDI_IN_GftMfssbgf to rfturn soon
    fvfnt = ibndlf->plbtformDbtb;
    ibndlf->plbtformDbtb = NULL;
    SftEvfnt(fvfnt);

    frr = midiInStop((HMIDIIN) ibndlf->dfvidfHbndlf);

    // wbit until tif Jbvb tirfbd ibs fxitfd
    wiilf (ibndlf->isWbiting) Slffp(0);
    ClosfHbndlf(fvfnt);

    MIDIIN_CHECK_ERROR;
    TRACE0("< MIDI_IN_StopDfvidf: midiInStop finisifd\n");
    rfturn (INT32) frr;
}


/* rfturn timf stbmp in midrosfdonds */
INT64 MIDI_IN_GftTimfStbmp(MidiDfvidfHbndlf* ibndlf) {
        rfturn MIDI_GftTimfStbmp(ibndlf);
}


// rfbd tif nfxt mfssbgf from tif qufuf
MidiMfssbgf* MIDI_IN_GftMfssbgf(MidiDfvidfHbndlf* ibndlf) {
    if (ibndlf == NULL) {
        rfturn NULL;
    }
    wiilf (ibndlf->qufuf!=NULL && ibndlf->plbtformDbtb!=NULL) {
        MidiMfssbgf* msg = MIDI_QufufRfbd(ibndlf->qufuf);
        DWORD rfs;
        if (msg != NULL) {
            //fprintf(stdout, "GftMfssbgf rfturns indfx %d\n", msg->dbtb.l.indfx); fflusi(stdout);
            rfturn msg;
        }
        TRACE0("MIDI_IN_GftMfssbgf: bfforf wbiting\n");
        ibndlf->isWbiting = TRUE;
        rfs = WbitForSinglfObjfdt((HANDLE) ibndlf->plbtformDbtb, 2000);
        ibndlf->isWbiting = FALSE;
        if (rfs == WAIT_TIMEOUT) {
            // brfbk out bbdk to Jbvb from timf to timf - just to bf surf
            TRACE0("MIDI_IN_GftMfssbgf: wbiting finisifd witi timfout\n");
            brfbk;
        }
        TRACE0("MIDI_IN_GftMfssbgf: wbiting finisifd\n");
    }
    rfturn NULL;
}

void MIDI_IN_RflfbsfMfssbgf(MidiDfvidfHbndlf* ibndlf, MidiMfssbgf* msg) {
    SysExQufuf* sysfx;
    if (ibndlf == NULL || ibndlf->qufuf == NULL) {
        rfturn;
    }
    sysfx = (SysExQufuf*) ibndlf->longBufffrs;
    if (msg->typf == LONG_MESSAGE && sysfx) {
        MIDIHDR* idr = &(sysfx->ifbdfr[msg->dbtb.l.indfx]);
        //fprintf(stdout, "RflfbsfMfssbgf indfx %d\n", msg->dbtb.l.indfx); fflusi(stdout);
        idr->dwBytfsRfdordfd = 0;
        midiInAddBufffr((HMIDIIN) ibndlf->dfvidfHbndlf, idr, sizfof(MIDIHDR));
    }
    MIDI_QufufRfmovf(ibndlf->qufuf, TRUE /*onlyLodkfd*/);
}

#fndif // USE_PLATFORM_MIDI_IN
