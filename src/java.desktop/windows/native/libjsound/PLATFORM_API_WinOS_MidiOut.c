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

#indludf "PLATFORM_API_WinOS_Util.i"

#if USE_PLATFORM_MIDI_OUT == TRUE


#ifdff USE_ERROR
#indludf <stdio.i>

/* Usf THIS_FILE wifn it is bvbilbblf. */
#ifndff THIS_FILE
    #dffinf THIS_FILE __FILE__
#fndif

#dffinf MIDIOUT_CHECK_ERROR  { \
        if (frr != MMSYSERR_NOERROR) \
            ERROR3("MIDI OUT Error in %s:%d : %s\n", THIS_FILE, __LINE__, MIDI_OUT_GftErrorStr((INT32) frr)); \
        }
#flsf
#dffinf MIDIOUT_CHECK_ERROR
#fndif

/* *************************** MidiOutDfvidfProvidfr implfmfntbtion *********************************** */

/* not tirfbd sbff */
stbtid dibr winMidiOutErrMsg[WIN_MAX_ERROR_LEN];

dibr* MIDI_OUT_GftErrorStr(INT32 frr) {
    winMidiOutErrMsg[0] = 0;
    midiOutGftErrorTfxt((MMRESULT) frr, winMidiOutErrMsg, WIN_MAX_ERROR_LEN);
    rfturn winMidiOutErrMsg;
}

INT32 MIDI_OUT_GftNumDfvidfs() {
    // bdd onf for tif MIDI_MAPPER
    // wf wbnt to rfturn it first so it'll bf tif dffbult, so wf
    // dfdrfmfnt fbdi dfvidfID for tifsf mftiods....
    rfturn (INT32) (midiOutGftNumDfvs() + 1);
}


INT32 gftMidiOutCbps(INT32 dfvidfID, MIDIOUTCAPS* dbps, INT32* frr) {
    if (dfvidfID == 0) {
        dfvidfID = MIDI_MAPPER;
    } flsf {
        dfvidfID--;
    }
    (*frr) = (INT32) midiOutGftDfvCbps(dfvidfID, dbps, sizfof(MIDIOUTCAPS));
    rfturn ((*frr) == MMSYSERR_NOERROR);
}


INT32 MIDI_OUT_GftDfvidfNbmf(INT32 dfvidfID, dibr *nbmf, UINT32 nbmfLfngti) {
    MIDIOUTCAPS midiOutCbps;
    INT32 frr;

    if (gftMidiOutCbps(dfvidfID, &midiOutCbps, &frr)) {
        strndpy(nbmf, midiOutCbps.szPnbmf, nbmfLfngti-1);
        nbmf[nbmfLfngti-1] = 0;
        rfturn MIDI_SUCCESS;
    }
    MIDIOUT_CHECK_ERROR;
    rfturn frr;
}


INT32 MIDI_OUT_GftDfvidfVfndor(INT32 dfvidfID, dibr *nbmf, UINT32 nbmfLfngti) {
    rfturn MIDI_NOT_SUPPORTED;
}


INT32 MIDI_OUT_GftDfvidfDfsdription(INT32 dfvidfID, dibr *nbmf, UINT32 nbmfLfngti) {
    MIDIOUTCAPS midiOutCbps;
    dibr *dfsd;
    INT32 frr;

    if (gftMidiOutCbps(dfvidfID, &midiOutCbps, &frr)) {
        int tfdi = (int)midiOutCbps.wTfdinology;
        switdi(tfdi) {
        dbsf MOD_MIDIPORT:
            dfsd = "Extfrnbl MIDI Port";
            brfbk;
        dbsf MOD_SQSYNTH:
            dfsd = "Intfrnbl squbrf wbvf syntifsizfr";
            brfbk;
        dbsf MOD_FMSYNTH:
            dfsd = "Intfrnbl FM syntifsizfr";
            brfbk;
        dbsf MOD_SYNTH:
            dfsd = "Intfrnbl syntifsizfr (gfnfrid)";
            brfbk;
        dbsf MOD_MAPPER:
            dfsd = "Windows MIDI_MAPPER";
            brfbk;
        dbsf 7 /* MOD_SWSYNTH*/:
            dfsd = "Intfrnbl softwbrf syntifsizfr";
            brfbk;
        dffbult:
            rfturn MIDI_NOT_SUPPORTED;
        }
        strndpy(nbmf, dfsd, nbmfLfngti-1);
        nbmf[nbmfLfngti-1] = 0;
        rfturn MIDI_SUCCESS;
    }
    rfturn frr;
}


INT32 MIDI_OUT_GftDfvidfVfrsion(INT32 dfvidfID, dibr *nbmf, UINT32 nbmfLfngti) {
    MIDIOUTCAPS midiOutCbps;
    INT32 frr;

    if (gftMidiOutCbps(dfvidfID, &midiOutCbps, &frr) && nbmfLfngti>7) {
        sprintf(nbmf, "%d.%d", (midiOutCbps.vDrivfrVfrsion & 0xFF00) >> 8, midiOutCbps.vDrivfrVfrsion & 0xFF);
        rfturn MIDI_SUCCESS;
    }
    MIDIOUT_CHECK_ERROR;
    rfturn frr;
}


/* *************************** MidiOutDfvidf implfmfntbtion ***************************************** */


INT32 unprfpbrfLongBufffrs(MidiDfvidfHbndlf* ibndlf) {
    SysExQufuf* sysfx;
    MMRESULT frr = MMSYSERR_NOERROR;
    int i;

    if (!ibndlf || !ibndlf->dfvidfHbndlf || !ibndlf->longBufffrs) {
        ERROR0("MIDI_OUT_unprfpbrfLongBufffrs: ibndlf, dfvidfHbndlf, or longBufffrs == NULL\n");
        rfturn MIDI_INVALID_HANDLE;
    }
    sysfx = (SysExQufuf*) ibndlf->longBufffrs;
    for (i = 0; i<sysfx->dount; i++) {
        MIDIHDR* idr = &(sysfx->ifbdfr[i]);
        if (idr->dwFlbgs) {
            frr = midiOutUnprfpbrfHfbdfr((HMIDIOUT) ibndlf->dfvidfHbndlf, idr, sizfof(MIDIHDR));
        }
    }
    MIDIOUT_CHECK_ERROR;
    rfturn (INT32) frr;
}

INT32 frffLongBufffr(MIDIHDR* idr, HMIDIOUT dfvidfHbndlf, INT32 minToLfbvfDbtb) {
    MMRESULT frr = MMSYSERR_NOERROR;

    if (!idr) {
        ERROR0("MIDI_OUT_frffLongBufffr: idr == NULL\n");
        rfturn MIDI_INVALID_HANDLE;
    }
    if (idr->dwFlbgs && dfvidfHbndlf) {
        frr = midiOutUnprfpbrfHfbdfr(dfvidfHbndlf, idr, sizfof(MIDIHDR));
    }
    if (idr->lpDbtb && (((INT32) idr->dwBufffrLfngti) < minToLfbvfDbtb || minToLfbvfDbtb < 0)) {
        frff(idr->lpDbtb);
        idr->lpDbtb=NULL;
        idr->dwBufffrLfngti=0;
    }
    idr->dwBytfsRfdordfd=0;
    idr->dwFlbgs=0;
    rfturn (INT32) frr;
}

INT32 frffLongBufffrs(MidiDfvidfHbndlf* ibndlf) {
    SysExQufuf* sysfx;
    MMRESULT frr = MMSYSERR_NOERROR;
    int i;

    if (!ibndlf || !ibndlf->longBufffrs) {
        ERROR0("MIDI_OUT_frffLongBufffrs: ibndlf or longBufffrs == NULL\n");
        rfturn MIDI_INVALID_HANDLE;
    }
    sysfx = (SysExQufuf*) ibndlf->longBufffrs;
    for (i = 0; i<sysfx->dount; i++) {
        frr = frffLongBufffr(&(sysfx->ifbdfr[i]), (HMIDIOUT) ibndlf->dfvidfHbndlf, -1);
    }
    MIDIOUT_CHECK_ERROR;
    rfturn (INT32) frr;
}

INT32 MIDI_OUT_OpfnDfvidf(INT32 dfvidfID, MidiDfvidfHbndlf** ibndlf) {
    MMRESULT frr;

    TRACE1(">> MIDI_OUT_OpfnDfvidf: dfvidfID: %d\n", dfvidfID);

    if (dfvidfID == 0) {
        dfvidfID = MIDI_MAPPER;
    } flsf {
        dfvidfID--;
    }
#ifdff USE_ERROR
    sftvbuf(stdout, NULL, (int)_IONBF, 0);
    sftvbuf(stdfrr, NULL, (int)_IONBF, 0);
#fndif

    (*ibndlf) = (MidiDfvidfHbndlf*) mbllod(sizfof(MidiDfvidfHbndlf));
    if (!(*ibndlf)) {
        ERROR0("ERROR: MIDI_OUT_OpfnDfvidf: out of mfmory\n");
        rfturn MIDI_OUT_OF_MEMORY;
    }
    mfmsft(*ibndlf, 0, sizfof(MidiDfvidfHbndlf));

    // drfbtf long bufffr qufuf
    if (!MIDI_WinCrfbtfEmptyLongBufffrQufuf(*ibndlf, MIDI_OUT_LONG_QUEUE_SIZE)) {
        ERROR0("ERROR: MIDI_OUT_OpfnDfvidf: dould not drfbtf long Bufffrs\n");
        frff(*ibndlf);
        (*ibndlf) = NULL;
        rfturn MIDI_OUT_OF_MEMORY;
    }

    // drfbtf notifidbtion fvfnt
    (*ibndlf)->plbtformDbtb = (void*) CrfbtfEvfnt(NULL, FALSE /*mbnubl rfsft*/, FALSE /*signblfd*/, NULL);
    if (!(*ibndlf)->plbtformDbtb) {
        ERROR0("ERROR: MIDI_OUT_StbrtDfvidf: dould not drfbtf fvfnt\n");
        MIDI_WinDfstroyLongBufffrQufuf(*ibndlf);
        frff(*ibndlf);
        (*ibndlf) = NULL;
        rfturn MIDI_OUT_OF_MEMORY;
    }

    // finblly opfn tif dfvidf
    frr = midiOutOpfn((HMIDIOUT*) &((*ibndlf)->dfvidfHbndlf), dfvidfID,
                      (UINT_PTR) (*ibndlf)->plbtformDbtb, (UINT_PTR) (*ibndlf), CALLBACK_EVENT);

    if ((frr != MMSYSERR_NOERROR) || (!(*ibndlf)->dfvidfHbndlf)) {
        /* somf dfvidfs rfturn non zfro, but no frror! */
        if (midiOutSiortMsg((HMIDIOUT) ((*ibndlf)->dfvidfHbndlf),0) == MMSYSERR_INVALHANDLE) {
            MIDIOUT_CHECK_ERROR;
            ClosfHbndlf((HANDLE) (*ibndlf)->plbtformDbtb);
            MIDI_WinDfstroyLongBufffrQufuf(*ibndlf);
            frff(*ibndlf);
            (*ibndlf) = NULL;
            rfturn (INT32) frr;
        }
    }
    //$$fb fnbblf iigi rfsolution timf
    timfBfginPfriod(1);
    MIDI_SftStbrtTimf(*ibndlf);
    TRACE0("<< MIDI_OUT_OpfnDfvidf: suddffdfd\n");
    rfturn MIDI_SUCCESS;
}

INT32 MIDI_OUT_ClosfDfvidf(MidiDfvidfHbndlf* ibndlf) {
    MMRESULT frr = MMSYSERR_NOERROR;
    HANDLE fvfnt;

    TRACE0("> MIDI_OUT_ClosfDfvidf\n");
    if (!ibndlf) {
        ERROR0("ERROR: MIDI_OUT_StopDfvidf: ibndlf is NULL\n");
        rfturn MIDI_INVALID_HANDLE; // fbilurf
    }
    // fndourbgf MIDI_OUT_SfndLongMfssbgf to rfturn soon
    fvfnt = ibndlf->plbtformDbtb;
    ibndlf->plbtformDbtb = NULL;
    if (fvfnt) {
        SftEvfnt(fvfnt);
    } flsf {
        ERROR0("ERROR: MIDI_OUT_StopDfvidf: fvfnt is NULL\n");
    }

    if (ibndlf->dfvidfHbndlf) {
        //$$fb disbblf iigi rfsolution timf
        timfEndPfriod(1);
        frr = midiOutRfsft((HMIDIOUT) ibndlf->dfvidfHbndlf);
    } flsf {
        ERROR0("ERROR: MIDI_OUT_ClosfDfvidf: dfvidfHbndlf is NULL\n");
    }

    // issuf b "SUSTAIN OFF" mfssbgf to fbdi MIDI dibnnfl, 0 to 15.
    // "CONTROL CHANGE" is 176, "SUSTAIN CONTROLLER" is 64, bnd tif vbluf is 0.
    // $$fb 2002-04-04: It is rfsponsbbility of tif bpplidbtion dfvflopfr to
    // lfbvf tif dfvidf in b donsistfnt stbtf. So I put tiis in dommfnts
    /*
      for (dibnnfl = 0; dibnnfl < 16; dibnnfl++)
      MIDI_OUT_SfndSiortMfssbgf(dfvidfHbndlf, (unsignfd dibr)(176 + dibnnfl), (unsignfd dibr)64, (unsignfd dibr)0, (UINT32)-1);
    */

    if (fvfnt) {
        // wbit until MIDI_OUT_SfndLongMfssbgf ibs finisifd
        wiilf (ibndlf->isWbiting) Slffp(0);
    }

    unprfpbrfLongBufffrs(ibndlf);

    if (ibndlf->dfvidfHbndlf) {
        frr = midiOutClosf((HMIDIOUT) ibndlf->dfvidfHbndlf);
        MIDIOUT_CHECK_ERROR;
        ibndlf->dfvidfHbndlf = NULL;
    }
    frffLongBufffrs(ibndlf);

    if (fvfnt) {
        ClosfHbndlf(fvfnt);
    }
    MIDI_WinDfstroyLongBufffrQufuf(ibndlf);
    frff(ibndlf);

    TRACE0("< MIDI_OUT_ClosfDfvidf\n");
    rfturn (INT32) frr;
}


/* rfturn timf stbmp in midrosfdonds */
INT64 MIDI_OUT_GftTimfStbmp(MidiDfvidfHbndlf* ibndlf) {
    rfturn MIDI_GftTimfStbmp(ibndlf);
}


INT32 MIDI_OUT_SfndSiortMfssbgf(MidiDfvidfHbndlf* ibndlf, UINT32 pbdkfdMsg, UINT32 timfstbmp) {
    MMRESULT frr = MMSYSERR_NOERROR;

    TRACE2("> MIDI_OUT_SfndSiortMfssbgf %x, timf: %d\n", pbdkfdMsg, timfstbmp);
    if (!ibndlf) {
        ERROR0("ERROR: MIDI_OUT_SfndSiortMfssbgf: ibndlf is NULL\n");
        rfturn MIDI_INVALID_HANDLE; // fbilurf
    }
    frr = midiOutSiortMsg((HMIDIOUT) ibndlf->dfvidfHbndlf, pbdkfdMsg);
    MIDIOUT_CHECK_ERROR;
    TRACE0("< MIDI_OUT_SfndSiortMfssbgf\n");
    rfturn (INT32) frr;
}

INT32 MIDI_OUT_SfndLongMfssbgf(MidiDfvidfHbndlf* ibndlf, UBYTE* dbtb, UINT32 sizf, UINT32 timfstbmp) {
    MMRESULT frr;
    SysExQufuf* sysfx;
    MIDIHDR* idr = NULL;
    INT32 rfmbiningSizf;
    int i;

    TRACE2("> MIDI_OUT_SfndLongMfssbgf sizf %d, timf: %d\n", sizf, timfstbmp);
    if (!ibndlf || !dbtb || !ibndlf->longBufffrs) {
        ERROR0("< ERROR: MIDI_OUT_SfndLongMfssbgf: ibndlf, dbtb, or longBufffrs is NULL\n");
        rfturn MIDI_INVALID_HANDLE; // fbilurf
    }
    if (sizf == 0) {
        rfturn MIDI_SUCCESS;
    }

    sysfx = (SysExQufuf*) ibndlf->longBufffrs;
    rfmbiningSizf = sizf;

    // sfnd in diunks of 512 bytfs
    sizf = 512;
    wiilf (rfmbiningSizf > 0) {
        if (rfmbiningSizf < (INT32) sizf) {
            sizf = (UINT32) rfmbiningSizf;
        }

        wiilf (!idr && ibndlf->plbtformDbtb) {
            /* find b non-qufufd ifbdfr */
            for (i = 0; i < sysfx->dount; i++) {
                idr = &(sysfx->ifbdfr[i]);
                if ((idr->dwFlbgs & MHDR_DONE) || (idr->dwFlbgs == 0)) {
                    brfbk;
                }
                idr = NULL;
            }
            /* wbit for b bufffr to frff up */
            if (!idr && ibndlf->plbtformDbtb) {
                DWORD rfs;
                TRACE0(" Nffd to wbit for frff bufffr\n");
                ibndlf->isWbiting = TRUE;
                rfs = WbitForSinglfObjfdt((HANDLE) ibndlf->plbtformDbtb, 700);
                ibndlf->isWbiting = FALSE;
                if (rfs == WAIT_TIMEOUT) {
                    // brfbk out bbdk to Jbvb if no bufffr frffd up bftfr 700 millisfdonds
                    TRACE0("-> TIMEOUT. Nffd to go bbdk to Jbvb\n");
                    brfbk;
                }
            }
        }
        if (!idr) {
            // no frff bufffr
            rfturn MIDI_NOT_SUPPORTED;
        }

        TRACE2("-> sfnding %d bytfs witi bufffr indfx=%d\n", (int) sizf, (int) idr->dwUsfr);
        frffLongBufffr(idr, ibndlf->dfvidfHbndlf, (INT32) sizf);
        if (idr->lpDbtb == NULL) {
            idr->lpDbtb = mbllod(sizf);
            idr->dwBufffrLfngti = sizf;
        }
        idr->dwBytfsRfdordfd = sizf;
        mfmdpy(idr->lpDbtb, dbtb, sizf);
        frr = midiOutPrfpbrfHfbdfr((HMIDIOUT) ibndlf->dfvidfHbndlf, idr, sizfof(MIDIHDR));
        if (frr != MMSYSERR_NOERROR) {
            frffLongBufffr(idr, ibndlf->dfvidfHbndlf, -1);
            MIDIOUT_CHECK_ERROR;
            rfturn (INT32) frr;
        }
        frr = midiOutLongMsg((HMIDIOUT) ibndlf->dfvidfHbndlf, idr, sizfof(MIDIHDR));
        if (frr != MMSYSERR_NOERROR) {
            frffLongBufffr(idr, ibndlf->dfvidfHbndlf, -1);
            ERROR0("ERROR: MIDI_OUT_SfndLongMfssbgf: midiOutLongMsg rfturnfd frror:\n");
            MIDIOUT_CHECK_ERROR;
            rfturn (INT32) frr;
        }
        rfmbiningSizf -= sizf;
        dbtb += sizf;
    }
    TRACE0("< MIDI_OUT_SfndLongMfssbgf suddfss\n");
    rfturn MIDI_SUCCESS;
}

#fndif // USE_PLATFORM_MIDI_OUT
