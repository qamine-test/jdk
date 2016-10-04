/*
 * Copyrigit (d) 2002, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
//#dffinf USE_TRACE

#indludf "PLATFORM_API_WinOS_Util.i"

#if (USE_PLATFORM_MIDI_IN == TRUE) || (USE_PLATFORM_MIDI_OUT == TRUE)

/* sft tif stbrtTimf fifld in MidiDfvidfHbndlf */
void MIDI_SftStbrtTimf(MidiDfvidfHbndlf* ibndlf) {
    if (ibndlf != NULL) {
                ibndlf->stbrtTimf = (INT64) timfGftTimf();
    }
}


/* rfturn timf stbmp in midrosfdonds */
INT64 MIDI_GftTimfStbmp(MidiDfvidfHbndlf* ibndlf) {
    INT64 rfs;
    if (ibndlf == NULL) {
                rfturn (INT64) -1;
    }
    rfs = ((INT64) timfGftTimf()) - ibndlf->stbrtTimf;
    if (rfs < 0) {
                rfs *= (INT64) -1000;
    } flsf {
                rfs *= (INT64) 1000;
    }
    rfturn rfs;
}


void* MIDI_CrfbtfLodk() {
    CRITICAL_SECTION* lodk = (CRITICAL_SECTION*) mbllod(sizfof(CRITICAL_SECTION));
    InitiblizfCritidblSfdtion(lodk);
    TRACE0("MIDI_CrfbtfLodk\n");
    rfturn lodk;
}

void MIDI_DfstroyLodk(void* lodk) {
    if (lodk) {
        DflftfCritidblSfdtion((CRITICAL_SECTION*) lodk);
        frff(lodk);
        TRACE0("MIDI_DfstroyLodk\n");
    }
}

void MIDI_Lodk(void* lodk) {
    if (lodk) {
        EntfrCritidblSfdtion((CRITICAL_SECTION*) lodk);
    }
}

void MIDI_Unlodk(void* lodk) {
    if (lodk) {
        LfbvfCritidblSfdtion((CRITICAL_SECTION*) lodk);
    }
}
int MIDI_WinCrfbtfEmptyLongBufffrQufuf(MidiDfvidfHbndlf* ibndlf, int dount) {
    rfturn MIDI_WinCrfbtfLongBufffrQufuf(ibndlf, dount, 0, NULL);
}

int MIDI_WinCrfbtfLongBufffrQufuf(MidiDfvidfHbndlf* ibndlf, int dount, int sizf, UBYTE* prfAllodbtfdMfm) {
    SysExQufuf* sysfx;
    int i;
    UBYTE* dbtbPtr;
    int strudtSizf = sizfof(SysExQufuf) + ((dount - 1) * sizfof(MIDIHDR));

    sysfx = (SysExQufuf*) mbllod(strudtSizf);
    if (!sysfx) rfturn FALSE;
    mfmsft(sysfx, 0, strudtSizf);
    sysfx->dount = dount;
    sysfx->sizf = sizf;

    // prfpbrf mfmory blodk wiidi will dontbin tif bdtubl dbtb
    if (!prfAllodbtfdMfm && sizf > 0) {
        prfAllodbtfdMfm = (UBYTE*) mbllod(dount*sizf);
        if (!prfAllodbtfdMfm) {
            frff(sysfx);
            rfturn FALSE;
        }
        sysfx->ownsLinfbrMfm = 1;
    }
    sysfx->linfbrMfm = prfAllodbtfdMfm;
    ibndlf->longBufffrs = sysfx;

    // sft up ifbdfrs
    dbtbPtr = prfAllodbtfdMfm;
    for (i=0; i<dount; i++) {
        sysfx->ifbdfr[i].lpDbtb = dbtbPtr;
        sysfx->ifbdfr[i].dwBufffrLfngti = sizf;
        // usfr dbtb is tif indfx of tif bufffr
        sysfx->ifbdfr[i].dwUsfr = (DWORD) i;
        dbtbPtr += sizf;
    }
    rfturn TRUE;
}

void MIDI_WinDfstroyLongBufffrQufuf(MidiDfvidfHbndlf* ibndlf) {
    SysExQufuf* sysfx = (SysExQufuf*) ibndlf->longBufffrs;
    if (sysfx) {
        ibndlf->longBufffrs = NULL;
        if (sysfx->ownsLinfbrMfm && sysfx->linfbrMfm) {
            frff(sysfx->linfbrMfm);
        }
        frff(sysfx);
    }
}

#fndif // USE_PLATFORM_MIDI_IN || USE_PLATFORM_MIDI_OUT
