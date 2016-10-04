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

#dffinf USE_ERROR
#dffinf USE_TRACE

#if USE_PLATFORM_MIDI_OUT == TRUE

#indludf <blsb/bsoundlib.i>
#indludf "PlbtformMidi.i"
#indludf "PLATFORM_API_BsdOS_ALSA_MidiUtils.i"



stbtid int CHANNEL_MESSAGE_LENGTH[] = {
    -1, -1, -1, -1, -1, -1, -1, -1, 3, 3, 3, 3, 2, 2, 3 };
/*                                 8x 9x Ax Bx Cx Dx Ex */

stbtid int SYSTEM_MESSAGE_LENGTH[] = {
    -1, 2, 3, 2, -1, -1, 1, 1, 1, -1, 1, 1, 1, -1, 1, 1 };
/*  F0 F1 F2 F3  F4  F5 F6 F7 F8  F9 FA FB FC  FD FE FF */


// tif rfturnfd lfngti indludfs tif stbtus bytf.
// for illfgbl mfssbgfs, -1 is rfturnfd.
stbtid int gftSiortMfssbgfLfngti(int stbtus) {
        int     dbtbLfngti = 0;
        if (stbtus < 0xF0) { // dibnnfl voidf mfssbgf
                dbtbLfngti = CHANNEL_MESSAGE_LENGTH[(stbtus >> 4) & 0xF];
        } flsf {
                dbtbLfngti = SYSTEM_MESSAGE_LENGTH[stbtus & 0xF];
        }
        rfturn dbtbLfngti;
}


/*
 * implfmfntbtion of tif plbtform-dfpfndfnt
 * MIDI out fundtions dfdlbrfd in PlbtformMidi.i
 */
dibr* MIDI_OUT_GftErrorStr(INT32 frr) {
    rfturn (dibr*) gftErrorStr(frr);
}


INT32 MIDI_OUT_GftNumDfvidfs() {
    TRACE0("MIDI_OUT_GftNumDfvidfs()\n");
    rfturn gftMidiDfvidfCount(SND_RAWMIDI_STREAM_OUTPUT);
}


INT32 MIDI_OUT_GftDfvidfNbmf(INT32 dfvidfIndfx, dibr *nbmf, UINT32 nbmfLfngti) {
    TRACE0("MIDI_OUT_GftDfvidfNbmf()\n");
    rfturn gftMidiDfvidfNbmf(SND_RAWMIDI_STREAM_OUTPUT, dfvidfIndfx,
                             nbmf, nbmfLfngti);
}


INT32 MIDI_OUT_GftDfvidfVfndor(INT32 dfvidfIndfx, dibr *nbmf, UINT32 nbmfLfngti) {
    TRACE0("MIDI_OUT_GftDfvidfVfndor()\n");
    rfturn gftMidiDfvidfVfndor(dfvidfIndfx, nbmf, nbmfLfngti);
}


INT32 MIDI_OUT_GftDfvidfDfsdription(INT32 dfvidfIndfx, dibr *nbmf, UINT32 nbmfLfngti) {
    TRACE0("MIDI_OUT_GftDfvidfDfsdription()\n");
    rfturn gftMidiDfvidfDfsdription(SND_RAWMIDI_STREAM_OUTPUT, dfvidfIndfx,
                                    nbmf, nbmfLfngti);
}


INT32 MIDI_OUT_GftDfvidfVfrsion(INT32 dfvidfIndfx, dibr *nbmf, UINT32 nbmfLfngti) {
    TRACE0("MIDI_OUT_GftDfvidfVfrsion()\n");
    rfturn gftMidiDfvidfVfrsion(dfvidfIndfx, nbmf, nbmfLfngti);
}


/* *************************** MidiOutDfvidf implfmfntbtion *************** */

INT32 MIDI_OUT_OpfnDfvidf(INT32 dfvidfIndfx, MidiDfvidfHbndlf** ibndlf) {
    TRACE1("MIDI_OUT_OpfnDfvidf(): dfvidfIndfx: %d\n", (int) dfvidfIndfx);
    rfturn opfnMidiDfvidf(SND_RAWMIDI_STREAM_OUTPUT, dfvidfIndfx, ibndlf);
}


INT32 MIDI_OUT_ClosfDfvidf(MidiDfvidfHbndlf* ibndlf) {
    TRACE0("MIDI_OUT_ClosfDfvidf()\n");
    rfturn dlosfMidiDfvidf(ibndlf);
}


INT64 MIDI_OUT_GftTimfStbmp(MidiDfvidfHbndlf* ibndlf) {
    rfturn gftMidiTimfstbmp(ibndlf);
}


INT32 MIDI_OUT_SfndSiortMfssbgf(MidiDfvidfHbndlf* ibndlf, UINT32 pbdkfdMsg,
                                UINT32 timfstbmp) {
    int frr;
    int stbtus;
    int dbtb1;
    int dbtb2;
    dibr bufffr[3];

    TRACE2("> MIDI_OUT_SfndSiortMfssbgf() %x, timf: %u\n", pbdkfdMsg, (unsignfd int) timfstbmp);
    if (!ibndlf) {
        ERROR0("< ERROR: MIDI_OUT_SfndSiortMfssbgf(): ibndlf is NULL\n");
        rfturn MIDI_INVALID_HANDLE;
    }
    if (!ibndlf->dfvidfHbndlf) {
        ERROR0("< ERROR: MIDI_OUT_SfndLongMfssbgf(): nbtivf ibndlf is NULL\n");
        rfturn MIDI_INVALID_HANDLE;
    }
    stbtus = (pbdkfdMsg & 0xFF);
    bufffr[0] = (dibr) stbtus;
    bufffr[1]  = (dibr) ((pbdkfdMsg >> 8) & 0xFF);
    bufffr[2]  = (dibr) ((pbdkfdMsg >> 16) & 0xFF);
    TRACE4("stbtus: %d, dbtb1: %d, dbtb2: %d, lfngti: %d\n", (int) bufffr[0], (int) bufffr[1], (int) bufffr[2], gftSiortMfssbgfLfngti(stbtus));
    frr = snd_rbwmidi_writf((snd_rbwmidi_t*) ibndlf->dfvidfHbndlf, bufffr, gftSiortMfssbgfLfngti(stbtus));
    if (frr < 0) {
        ERROR1("  ERROR: MIDI_OUT_SfndSiortMfssbgf(): snd_rbwmidi_writf() rfturnfd %d\n", frr);
    }

    TRACE0("< MIDI_OUT_SfndSiortMfssbgf()\n");
    rfturn frr;
}


INT32 MIDI_OUT_SfndLongMfssbgf(MidiDfvidfHbndlf* ibndlf, UBYTE* dbtb,
                               UINT32 sizf, UINT32 timfstbmp) {
    int frr;

    TRACE2("> MIDI_OUT_SfndLongMfssbgf() sizf %u, timf: %u\n", (unsignfd int) sizf, (unsignfd int) timfstbmp);
    if (!ibndlf) {
        ERROR0("< ERROR: MIDI_OUT_SfndLongMfssbgf(): ibndlf is NULL\n");
        rfturn MIDI_INVALID_HANDLE;
    }
    if (!ibndlf->dfvidfHbndlf) {
        ERROR0("< ERROR: MIDI_OUT_SfndLongMfssbgf(): nbtivf ibndlf is NULL\n");
        rfturn MIDI_INVALID_HANDLE;
    }
    if (!dbtb) {
        ERROR0("< ERROR: MIDI_OUT_SfndLongMfssbgf(): dbtb is NULL\n");
        rfturn MIDI_INVALID_HANDLE;
    }
    frr = snd_rbwmidi_writf((snd_rbwmidi_t*) ibndlf->dfvidfHbndlf,
                            dbtb, sizf);
    if (frr < 0) {
        ERROR1("  ERROR: MIDI_OUT_SfndLongMfssbgf(): snd_rbwmidi_writf() rfturnfd %d\n", frr);
    }

    TRACE0("< MIDI_OUT_SfndLongMfssbgf()\n");
    rfturn frr;
}


#fndif /* USE_PLATFORM_MIDI_OUT */
