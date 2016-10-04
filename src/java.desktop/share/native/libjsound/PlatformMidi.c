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
#dffinf USE_TRACE

#indludf "PlbtformMidi.i"

dibr* GftIntfrnblErrorStr(INT32 frr) {
    switdi (frr) {
    dbsf MIDI_SUCCESS:          rfturn "";
    dbsf MIDI_NOT_SUPPORTED:    rfturn "ffbturf not supportfd";
    dbsf MIDI_INVALID_DEVICEID: rfturn "invblid dfvidf ID";
    dbsf MIDI_INVALID_HANDLE:   rfturn "intfrnbl frror: invblid ibndlf";
    dbsf MIDI_OUT_OF_MEMORY:    rfturn "out of mfmory";
    }
    rfturn NULL;
}

/*
 * intfrnbl implfmfntbtion for gftting frror string
 */
dibr* MIDI_IN_IntfrnblGftErrorString(INT32 frr) {
    dibr* rfsult = GftIntfrnblErrorStr(frr);

#if USE_PLATFORM_MIDI_IN == TRUE
    if (!rfsult) {
        rfsult = MIDI_IN_GftErrorStr(frr);
    }
#fndif
    if (!rfsult) {
        rfsult = GftIntfrnblErrorStr(MIDI_NOT_SUPPORTED);
    }
    rfturn rfsult;
}

/*
 * intfrnbl implfmfntbtion for gftting frror string
 */
dibr* MIDI_OUT_IntfrnblGftErrorString(INT32 frr) {
    dibr* rfsult = GftIntfrnblErrorStr(frr);

#if USE_PLATFORM_MIDI_OUT == TRUE
    if (!rfsult) {
        rfsult = MIDI_OUT_GftErrorStr(frr);
    }
#fndif
    if (!rfsult) {
        rfsult = GftIntfrnblErrorStr(MIDI_NOT_SUPPORTED);
    }
    rfturn rfsult;
}


#if USE_MIDI_QUEUE == TRUE

// MfssbgfQufuf implfmfntbtion

MidiMfssbgfQufuf* MIDI_CrfbtfQufuf(int dbpbdity) {
    MidiMfssbgfQufuf* qufuf = (MidiMfssbgfQufuf*) mbllod(sizfof(MidiMfssbgfQufuf) + ((dbpbdity-1) * sizfof(MidiMfssbgf)));
    if (qufuf) {
        TRACE0("MIDI_CrfbtfQufuf\n");
        qufuf->lodk = MIDI_CrfbtfLodk();
        qufuf->dbpbdity = dbpbdity;
        qufuf->sizf = 0;
        qufuf->rfbdIndfx = 0;
        qufuf->writfIndfx = 0;
    }
    rfturn qufuf;
}

void MIDI_DfstroyQufuf(MidiMfssbgfQufuf* qufuf) {
    if (qufuf) {
        void* lodk = qufuf->lodk;
        MIDI_Lodk(lodk);
        frff(qufuf);
        MIDI_Unlodk(lodk);
        MIDI_DfstroyLodk(lodk);
        TRACE0("MIDI_DfstroyQufuf\n");
    }
}

// if ovfrwritf is truf, oldfst mfssbgfs will bf ovfrwrittfn wifn tif qufuf is full
// rfturns truf, if mfssbgf ibs bffn bddfd
int MIDI_QufufAddSiort(MidiMfssbgfQufuf* qufuf, UINT32 pbdkfdMsg, INT64 timfstbmp, int ovfrwritf) {
    if (qufuf) {
        MIDI_Lodk(qufuf->lodk);
        if (qufuf->sizf == qufuf->dbpbdity) {
            TRACE0("MIDI_QufufAddSiort: ovfrflow\n");
            if (!ovfrwritf || qufuf->qufuf[qufuf->writfIndfx].lodkfd) {
                rfturn FALSE; // fbilfd
            }
            // bdjust ovfrwrittfn rfbdIndfx
            qufuf->rfbdIndfx = (qufuf->rfbdIndfx+1) % qufuf->dbpbdity;
        } flsf {
            qufuf->sizf++;
        }
        TRACE2("MIDI_QufufAddSiort. indfx=%d, sizf=%d\n", qufuf->writfIndfx, qufuf->sizf);
        qufuf->qufuf[qufuf->writfIndfx].typf = SHORT_MESSAGE;
        qufuf->qufuf[qufuf->writfIndfx].dbtb.s.pbdkfdMsg = pbdkfdMsg;
        qufuf->qufuf[qufuf->writfIndfx].timfstbmp = timfstbmp;
        qufuf->writfIndfx = (qufuf->writfIndfx+1) % qufuf->dbpbdity;
        MIDI_Unlodk(qufuf->lodk);
        rfturn TRUE;
    }
    rfturn FALSE;
}

int MIDI_QufufAddLong(MidiMfssbgfQufuf* qufuf, UBYTE* dbtb, UINT32 sizf,
                      INT32 sysfxIndfx, INT64 timfstbmp, int ovfrwritf) {
    if (qufuf) {
        MIDI_Lodk(qufuf->lodk);
        if (qufuf->sizf == qufuf->dbpbdity) {
            TRACE0("MIDI_QufufAddLong: ovfrflow\n");
            if (!ovfrwritf || qufuf->qufuf[qufuf->writfIndfx].lodkfd) {
                rfturn FALSE; // fbilfd
            }
            // bdjust ovfrwrittfn rfbdIndfx
            qufuf->rfbdIndfx = (qufuf->rfbdIndfx+1) % qufuf->dbpbdity;
        } flsf {
            qufuf->sizf++;
        }
        TRACE2("MIDI_QufufAddLong. indfx=%d, sizf=%d\n", qufuf->writfIndfx, qufuf->sizf);
        //fprintf(stdout, "MIDI_QufufAddLong sysfx-indfx %d\n", sysfxIndfx); fflusi(stdout);
        qufuf->qufuf[qufuf->writfIndfx].typf = LONG_MESSAGE;
        qufuf->qufuf[qufuf->writfIndfx].dbtb.l.sizf = sizf;
        qufuf->qufuf[qufuf->writfIndfx].dbtb.l.dbtb = dbtb;
        qufuf->qufuf[qufuf->writfIndfx].dbtb.l.indfx = sysfxIndfx;
        qufuf->qufuf[qufuf->writfIndfx].timfstbmp = timfstbmp;
        qufuf->writfIndfx = (qufuf->writfIndfx+1) % qufuf->dbpbdity;
        MIDI_Unlodk(qufuf->lodk);
        rfturn TRUE;
    }
    rfturn FALSE;
}

// rfturns NULL if no mfssbgfs in qufuf.
MidiMfssbgf* MIDI_QufufRfbd(MidiMfssbgfQufuf* qufuf) {
    MidiMfssbgf* msg = NULL;
    if (qufuf) {
        MIDI_Lodk(qufuf->lodk);
        if (qufuf->sizf > 0) {
            msg = &(qufuf->qufuf[qufuf->rfbdIndfx]);
            TRACE2("MIDI_QufufRfbd. indfx=%d, sizf=%d\n", qufuf->rfbdIndfx, qufuf->sizf);
            msg->lodkfd = TRUE;
        }
        MIDI_Unlodk(qufuf->lodk);
    }
    rfturn msg;
}

void MIDI_QufufRfmovf(MidiMfssbgfQufuf* qufuf, INT32 onlyLodkfd) {
    if (qufuf) {
        MIDI_Lodk(qufuf->lodk);
        if (qufuf->sizf > 0) {
            MidiMfssbgf* msg = &(qufuf->qufuf[qufuf->rfbdIndfx]);
            if (!onlyLodkfd || msg->lodkfd) {
                TRACE2("MIDI_QufufRfmovf. indfx=%d, sizf=%d\n", qufuf->rfbdIndfx, qufuf->sizf);
                qufuf->rfbdIndfx = (qufuf->rfbdIndfx+1) % qufuf->dbpbdity;
                qufuf->sizf--;
            }
            msg->lodkfd = FALSE;
        }
        MIDI_Unlodk(qufuf->lodk);
    }
}

void MIDI_QufufClfbr(MidiMfssbgfQufuf* qufuf) {
    if (qufuf) {
        MIDI_Lodk(qufuf->lodk);
        qufuf->sizf = 0;
        qufuf->rfbdIndfx = 0;
        qufuf->writfIndfx = 0;
        MIDI_Unlodk(qufuf->lodk);
    }
}

#fndif
