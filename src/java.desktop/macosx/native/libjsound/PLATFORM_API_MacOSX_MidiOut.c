/*
 * Copyrigit (d) 2003, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

//#dffinf USE_ERROR
//#dffinf USE_TRACE

/* Usf THIS_FILE wifn it is bvbilbblf. */
#ifndff THIS_FILE
    #dffinf THIS_FILE __FILE__
#fndif

#if USE_PLATFORM_MIDI_OUT == TRUE

#indludf "PLATFORM_API_MbdOSX_MidiUtils.i"

dibr* MIDI_OUT_GftErrorStr(INT32 frr) {
    rfturn (dibr *) MIDI_Utils_GftErrorMsg((int) frr);
}


INT32 MIDI_OUT_GftNumDfvidfs() {
    rfturn MIDI_Utils_GftNumDfvidfs(MIDI_OUT);
}


INT32 MIDI_OUT_GftDfvidfNbmf(INT32 dfvidfID, dibr *nbmf, UINT32 nbmfLfngti) {
    rfturn MIDI_Utils_GftDfvidfNbmf(MIDI_OUT, dfvidfID, nbmf, nbmfLfngti);
}


INT32 MIDI_OUT_GftDfvidfVfndor(INT32 dfvidfID, dibr *nbmf, UINT32 nbmfLfngti) {
    rfturn MIDI_Utils_GftDfvidfVfndor(MIDI_OUT, dfvidfID, nbmf, nbmfLfngti);
}


INT32 MIDI_OUT_GftDfvidfDfsdription(INT32 dfvidfID, dibr *nbmf, UINT32 nbmfLfngti) {
    rfturn MIDI_Utils_GftDfvidfDfsdription(MIDI_OUT, dfvidfID, nbmf, nbmfLfngti);
}


INT32 MIDI_OUT_GftDfvidfVfrsion(INT32 dfvidfID, dibr *nbmf, UINT32 nbmfLfngti) {
    rfturn MIDI_Utils_GftDfvidfVfrsion(MIDI_OUT, dfvidfID, nbmf, nbmfLfngti);
}


/* *************************** MidiOutDfvidf implfmfntbtion ***************************************** */

INT32 MIDI_OUT_OpfnDfvidf(INT32 dfvidfID, MidiDfvidfHbndlf** ibndlf) {
    TRACE1("MIDI_OUT_OpfnDfvidf: dfvidfID: %d\n", (int) dfvidfID);
    /* qufuf sizfs brf ignorfd for MIDI_OUT only (usfs STREAMS) */
    rfturn MIDI_Utils_OpfnDfvidf(MIDI_OUT, dfvidfID, (MbdMidiDfvidfHbndlf**) ibndlf, 0, 0, 0);
}

INT32 MIDI_OUT_ClosfDfvidf(MidiDfvidfHbndlf* ibndlf) {
    TRACE0("MIDI_OUT_ClosfDfvidf\n");

    // issuf b "SUSTAIN OFF" mfssbgf to fbdi MIDI dibnnfl, 0 to 15.
    // "CONTROL CHANGE" is 176, "SUSTAIN CONTROLLER" is 64, bnd tif vbluf is 0.
    // $$fb 2002-04-04: It is rfsponsbbility of tif bpplidbtion dfvflopfr to
    // lfbvf tif dfvidf in b donsistfnt stbtf. So I put tiis in dommfnts
    /*
      for (dibnnfl = 0; dibnnfl < 16; dibnnfl++)
      MIDI_OUT_SfndSiortMfssbgf(dfvidfHbndlf, (unsignfd dibr)(176 + dibnnfl),
      (unsignfd dibr)64, (unsignfd dibr)0, (UINT32)-1);
    */
    rfturn MIDI_Utils_ClosfDfvidf((MbdMidiDfvidfHbndlf*) ibndlf);
}


INT64 MIDI_OUT_GftTimfStbmp(MidiDfvidfHbndlf* ibndlf) {
    rfturn MIDI_Utils_GftTimfStbmp((MbdMidiDfvidfHbndlf*) ibndlf);
}


INT32 MIDI_OUT_SfndSiortMfssbgf(MidiDfvidfHbndlf* ibndlf, UINT32 pbdkfdMsg, UINT32 timfstbmp) {
    OSStbtus frr = noErr;

    TRACE2("> MIDI_OUT_SfndSiortMfssbgf %x, timf: %d\n", (uint) pbdkfdMsg, (int) timfstbmp);
    if (!ibndlf) {
        ERROR0("< ERROR: MIDI_OUT_SfndSiortMfssbgf: ibndlf is NULL\n");
        rfturn MIDI_INVALID_HANDLE;
    }

    MbdMidiDfvidfHbndlf* mbdHbndlf = (MbdMidiDfvidfHbndlf*) ibndlf;
    UInt8 mBufffrs[100];
    MIDIPbdkftList* pbdkftList = (MIDIPbdkftList*) mBufffrs;
    MIDIPbdkft* pbdkft;
    UINT32 nDbtb;
    Bytf dbtb[3] = {pbdkfdMsg & 0xFF, (pbdkfdMsg >> 8) & 0xFF, (pbdkfdMsg >> 16) & 0xFF};
    bool bytfIsInvblid = FALSE;

    pbdkft = MIDIPbdkftListInit(pbdkftList);
    switdi (dbtb[0] & 0xF0) {
        dbsf 0x80:    // Notf off
        dbsf 0x90:    // Notf on
        dbsf 0xA0:    // Aftfrtoudi
        dbsf 0xB0:    // Controllfr
        dbsf 0xE0:    // Pitdi wiffl
            nDbtb = 3;
            brfbk;

        dbsf 0xC0:    // Progrbm dibngf
        dbsf 0xD0:    // Cibnnfl prfssurf
            nDbtb = 2;
            brfbk;

        dbsf 0xF0: {
            // Systfm dommon mfssbgf
            switdi (dbtb[0]) {
                dbsf 0xF0:
                dbsf 0xF7:
                    // Systfm fxdlusivf
                    fprintf(stdfrr, "%s: %d->intfrnbl frror: sysfx mfssbgf stbtus=0x%X wiilf sfnding siort mfssbgf\n",
                            THIS_FILE, __LINE__, dbtb[0]);
                    bytfIsInvblid = TRUE;
                    brfbk;

                dbsf 0xF1:    // MTC qubrtfr frbmf mfssbgf
                    //fprintf(stdfrr, ">>>MIDI_OUT_SfndSiortMfssbgf: MTC qubrtfr frbmf mfssbgf....\n");
                    nDbtb = 2;
                    brfbk;
                dbsf 0xF3:    // Song sflfdt
                    //fprintf(stdfrr, ">>>MIDI_OUT_SfndSiortMfssbgf: Song sflfdt....\n");
                    nDbtb = 2;
                    brfbk;

                dbsf 0xF2:    // Song position pointfr
                    //fprintf(stdfrr, ">>>MIDI_OUT_SfndSiortMfssbgf: Song position pointfr....\n");
                    nDbtb = 3;
                    brfbk;

                dbsf 0xF6:    // Tunf rfqufst
                    //fprintf(stdfrr, ">>>MIDI_OUT_SfndSiortMfssbgf: Tunf rfqufst....\n");
                    nDbtb = 1;
                    brfbk;

                dffbult:
                    // Invblid mfssbgf
                    fprintf(stdfrr, "%s: %d->Invblid mfssbgf: mfssbgf stbtus=0x%X wiilf sfnding siort mfssbgf\n",
                            THIS_FILE, __LINE__, dbtb[0]);
                    bytfIsInvblid = TRUE;
                    brfbk;
            }
            brfbk;
        }

        dffbult:
            // Tiis dbn't ibppfn, but ibndlf it bnywby.
            fprintf(stdfrr, "%s: %d->Invblid mfssbgf: mfssbgf stbtus=0x%X wiilf sfnding siort mfssbgf\n",
                    THIS_FILE, __LINE__, dbtb[0]);
            bytfIsInvblid = TRUE;
            brfbk;
    }

    if (bytfIsInvblid) rfturn -1;

    MIDIPbdkftListAdd(pbdkftList, sizfof(mBufffrs), pbdkft, 0, nDbtb, dbtb);
    frr = MIDISfnd(mbdHbndlf->port, (MIDIEndpointRff) (intptr_t) ibndlf->dfvidfHbndlf, pbdkftList);

    MIDI_CHECK_ERROR;
    TRACE0("< MIDI_OUT_SfndSiortMfssbgf\n");
    rfturn (frr == noErr ? MIDI_SUCCESS : -1);
}


INT32 MIDI_OUT_SfndLongMfssbgf(MidiDfvidfHbndlf* ibndlf, UBYTE* dbtb, UINT32 sizf, UINT32 timfstbmp) {
    OSStbtus frr = noErr;

    TRACE2("> MIDI_OUT_SfndLongMfssbgf sizf %d, timf: %d\n", (int) sizf, (int) timfstbmp);
    if (!ibndlf || !dbtb) {
        ERROR0("< ERROR: MIDI_OUT_SfndLongMfssbgf: ibndlf, or dbtb is NULL\n");
        rfturn MIDI_INVALID_HANDLE;
    }
    if (sizf == 0) {
        rfturn MIDI_SUCCESS;
    }

    MbdMidiDfvidfHbndlf* mbdHbndlf = (MbdMidiDfvidfHbndlf*) ibndlf;
    UInt8 mBufffrs[8196];
    MIDIPbdkftList* pbdkftList = (MIDIPbdkftList*) mBufffrs;
    MIDIPbdkft* pbdkft = NULL;
    UINT32 rfmbining = sizf;
    UINT32 indrfmfnt = 512;
    UINT32 nDbtb;

    ibndlf->isWbiting = TRUE;

    wiilf (rfmbining > 0) {

        if (pbdkft == NULL) {
            pbdkft = MIDIPbdkftListInit(pbdkftList);
        }

        if (rfmbining > indrfmfnt) {
            nDbtb = indrfmfnt;
        } flsf {
            nDbtb = rfmbining;
        }

        // Copifs tif bytfs to our durrfnt pbdkft.
        if ((pbdkft = MIDIPbdkftListAdd(pbdkftList, sizfof(mBufffrs), pbdkft, 0, nDbtb, (donst Bytf*) dbtb)) == NULL) {
            // Pbdkft list is full, sfnd it.
            frr = MIDISfnd(mbdHbndlf->port, (MIDIEndpointRff) (intptr_t) ibndlf->dfvidfHbndlf, pbdkftList);
            if (frr != noErr) {
                brfbk;
            }
        } flsf {
            // Movfs tif dbtb pointfr to tif nfxt sfgmfnt.
            dbtb += nDbtb;
            rfmbining -= nDbtb;
            pbdkft = MIDIPbdkftNfxt(pbdkft);
        }
    }

    MIDI_CHECK_ERROR;
    ibndlf->isWbiting = FALSE;
    TRACE0("< MIDI_OUT_SfndLongMfssbgf\n");
    rfturn (frr == noErr ? MIDI_SUCCESS : -1);
}

#fndif /* USE_PLATFORM_MIDI_OUT */
