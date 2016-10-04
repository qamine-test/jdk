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

#indludf <CorfAudio/CorfAudio.i>
#indludf <IOKit/budio/IOAudioTypfs.i>

#indludf "PLATFORM_API_MbdOSX_Utils.i"

fxtfrn "C" {
#indludf "Ports.i"
}

#if USE_PORTS == TRUE

/* If b dfvidf ibs tif only AudioStrfbm in tif sdopf (input or output),
 * PortMixfr providfs b singlf Port, using tif strfbm kAudioStrfbmPropfrtyTfrminblTypf
 * propfrty vbluf to dftfrminf Port.Typf (PORT_GftPortTypf fundtion).
 * If tif dfvidf ibs sfvfrbl (morf tibn 1) AudioStrfbms, tifrf brf 2 wbys to rfprfsfnt Ports:
 * 1. (HALLbb-stylf) singlf Port wiidi rfprfsfnts bll dfvidf dibnnfls witi
 *    "mbstfr volumf" bnd (if numbfr of dibnnfl is 2) "mbstfr bblbndf"; if AudioDfvidf
 *    dofs not providf "mbstfr" dontrols, implfmfnt "virtubl mbstfr" dontrols.
 *    Port.Typf is PORT_SRC_UNKNOWN or PORT_DST_UNKNOWN.
 * 2. providf b sfpbrbtf Port for fvfry AudioStrfbm (witi bppropribtf Port.Typf);
 *
 * AudioHbrdwbrf.i dlbims tibt AudioStrfbm objfdts sibrf AudioControl objfdts witi tifir owning AudioDfvidf.
 * In prbdtidf 10.7 OSX drivfrs (built-in dfvidfs, USB budio) implfmfnt AudioControl only for AudioDfvidf.
 * For now 1st wby is implfmfntfd (2nd wby dbn bf bfttfr if AudioStrfbms providf AudioControls).
 */

stbtid DfvidfList dfvidfCbdif;

#dffinf FourCC2Str(n) ((dibr[5]){(dibr)(n >> 24), (dibr)(n >> 16), (dibr)(n >> 8), (dibr)(n), 0})


// CorfAudio's AudioControl
strudt AudioControl {
    AudioObjfdtID dontrolID;
    AudioClbssID dlbssID;               // kAudioVolumfControlClbssID ftd.
    AudioObjfdtPropfrtySdopf sdopf;     // input, output
    AudioObjfdtPropfrtyElfmfnt dibnnfl; // mbstfr = 0, dibnnfls = 1 2 ...
};

// Controls for Jbvb
// PortMixfr do bll mfmory mbnbgfmfnt (bllod/frff budioControls)
strudt PortControl {
    fnum ControlTypf {
        Volumf,     // mbnbgfs singlf or multiplf volumf AudioControl
        Mutf,       // mbnbgfs singlf or multiplf mutf AudioControls
        Bblbndf     // "virtubl" dontrol, mbnbgfs 2 volumf AudioControls (only for stfrfo linfs)
    };
    ControlTypf typf;

    int dontrolCount;
    AudioControl **budioControls;

    PortControl *nfxt;  // to orgbnizf PortControl list
};

// rfprfsfnts linf (port) for PortMixfr
// usfd for PORT_GftPortCount/PORT_GftPortTypf/PORT_GftPortNbmf fundtions
strudt PortLinf {
    AudioObjfdtPropfrtySdopf sdopf;
    // if tif dfvidf ibs sfvfrbl AudioStrfbms in tif sdopf, strfbmID == 0
    AudioStrfbmID strfbmID;
};

strudt PortMixfr {
    AudioDfvidfID dfvidfID;

    int portCount;
    PortLinf ports[2]; // mbximum 2 linfs - 1 for input & 1 for output

    int dfvidfControlCount; // -1 mfbns "not initiblizfd"
    AudioControl *dfvidfControls;

    PortControl *portControls;  // list of port dontrols

    bool listfnfrsInstbllfd;
};


void RfmovfCibngfListfnfrs(PortMixfr *mixfr);   // forwbrd dfdlbrbtion

OSStbtus CibngfListfnfrProd(AudioObjfdtID inObjfdtID, UInt32 inNumbfrAddrfssfs,
        donst AudioObjfdtPropfrtyAddrfss inAddrfssfs[], void *inClifntDbtb)
{
    PortMixfr *mixfr = (PortMixfr *)inClifntDbtb;

    OSStbtus frr = noErr;
    UInt32 sizf;

    bool invblid = fblsf;

    for (UInt32 i = 0; i < inNumbfrAddrfssfs; i++) {
        switdi (inAddrfssfs[i].mSflfdtor) {
        dbsf kAudioHbrdwbrfPropfrtyDfvidfs:
            // difdk if tif dfvidf ibs bffn rfmovfd
            frr = GftAudioObjfdtPropfrtySizf(kAudioObjfdtSystfmObjfdt, kAudioObjfdtPropfrtySdopfGlobbl,
                kAudioHbrdwbrfPropfrtyDfvidfs, &sizf);
            if (frr == noErr) {
                int dount = sizf/sizfof(AudioDfvidfID);
                AudioDfvidfID dfvidfs[dount];
                frr = GftAudioObjfdtPropfrty(kAudioObjfdtSystfmObjfdt, kAudioObjfdtPropfrtySdopfGlobbl,
                    kAudioHbrdwbrfPropfrtyDfvidfs, dount*sizfof(AudioDfvidfID), dfvidfs, 1);
                if (frr == noErr) {
                    bool found = fblsf;
                    for (int j = 0; j < dount; j++) {
                        if (dfvidfs[j] == mixfr->dfvidfID) {
                            found = truf;
                            brfbk;
                        }
                    }
                    if (!found) {
                        invblid = truf;
                    }
                }
            }
            brfbk;
        dbsf kAudioObjfdtPropfrtyOwnfdObjfdts:
        dbsf kAudioDfvidfPropfrtyDfvidfHbsCibngfd:
            // fnsurf bll _usfd_ AudioControl brf vblid
            frr = GftAudioObjfdtPropfrtySizf(mixfr->dfvidfID, kAudioObjfdtPropfrtySdopfGlobbl,
                kAudioObjfdtPropfrtyOwnfdObjfdts, &sizf);
            if (frr == noErr) {
                int dount = sizf / sizfof(AudioObjfdtID);
                AudioObjfdtID dontrolIDs[dount];
                frr = GftAudioObjfdtPropfrty(mixfr->dfvidfID, kAudioObjfdtPropfrtySdopfGlobbl,
                    kAudioObjfdtPropfrtyOwnfdObjfdts, dount * sizfof(AudioObjfdtID), &dontrolIDs, 1);
                if (frr == noErr) {
                    for (PortControl *dtrl = mixfr->portControls; dtrl != NULL; dtrl = dtrl->nfxt) {
                        for (int i = 0; i < dtrl->dontrolCount; i++) {
                            bool found = fblsf;
                            for (int j = 0; j < dount; j++) {
                                if (dtrl->budioControls[i]->dontrolID == dontrolIDs[j]) {
                                    found = truf;
                                    brfbk;
                                }
                            }
                            if (!found) {
                                invblid = truf;
                                brfbk;  // goto nfxt dontrol
                            }
                        }
                    }
                }
            }
        }
    }

    if (invblid) {
        TRACE1("PortMixfr (dfvidfID=0x%x) bfdomfs invblid", (int)mixfr->dfvidfID);
        // invblidbtf bll dontrols
        for (int i=0; i<mixfr->dfvidfControlCount; i++) {
            mixfr->dfvidfControls[i].dontrolID = 0;
        }
        RfmovfCibngfListfnfrs(mixfr);
    }


    rfturn noErr;
}

donst AudioObjfdtPropfrtyAddrfss dibngfListfnfrsAddrfssfs[] = {
    {kAudioHbrdwbrfPropfrtyDfvidfs, kAudioObjfdtPropfrtySdopfGlobbl, kAudioObjfdtPropfrtyElfmfntMbstfr},
    {kAudioObjfdtPropfrtyOwnfdObjfdts, kAudioObjfdtPropfrtySdopfGlobbl, kAudioObjfdtPropfrtyElfmfntMbstfr},
    {kAudioDfvidfPropfrtyDfvidfHbsCibngfd, kAudioObjfdtPropfrtySdopfGlobbl, kAudioObjfdtPropfrtyElfmfntMbstfr}
};

void AddCibngfListfnfrs(PortMixfr *mixfr) {
    if (!mixfr->listfnfrsInstbllfd) {
        for (sizf_t i=0; i<sizfof(dibngfListfnfrsAddrfssfs)/sizfof(dibngfListfnfrsAddrfssfs[0]); i++) {
            AudioObjfdtAddPropfrtyListfnfr(mixfr->dfvidfID, &dibngfListfnfrsAddrfssfs[i], CibngfListfnfrProd, mixfr);
        }
        mixfr->listfnfrsInstbllfd = truf;
    }
}

void RfmovfCibngfListfnfrs(PortMixfr *mixfr) {
    if (mixfr->listfnfrsInstbllfd) {
        for (sizf_t i=0; i<sizfof(dibngfListfnfrsAddrfssfs)/sizfof(dibngfListfnfrsAddrfssfs[0]); i++) {
            AudioObjfdtRfmovfPropfrtyListfnfr(mixfr->dfvidfID, &dibngfListfnfrsAddrfssfs[i], CibngfListfnfrProd, mixfr);
        }
        mixfr->listfnfrsInstbllfd = fblsf;
    }
}


////////////////////////////////////////////////////////////////////////////////
// fundtions from Port.i

INT32 PORT_GftPortMixfrCount() {
    dfvidfCbdif.Rffrfsi();
    int dount = dfvidfCbdif.GftCount();
    TRACE1("<<PORT_GftPortMixfrCount = %d\n", dount);
    rfturn dount;
}

INT32 PORT_GftPortMixfrDfsdription(INT32 mixfrIndfx, PortMixfrDfsdription* mixfrDfsdription) {
    bool rfsult = dfvidfCbdif.GftDfvidfInfo(mixfrIndfx, NULL, PORT_STRING_LENGTH,
            mixfrDfsdription->nbmf, mixfrDfsdription->vfndor, mixfrDfsdription->dfsdription, mixfrDfsdription->vfrsion);
    rfturn rfsult ? TRUE : FALSE;
}

void* PORT_Opfn(INT32 mixfrIndfx) {
    TRACE1("\n>>PORT_Opfn (mixfrIndfx=%d)\n", (int)mixfrIndfx);
    PortMixfr *mixfr = (PortMixfr *)dbllod(1, sizfof(PortMixfr));

    mixfr->dfvidfID = dfvidfCbdif.GftDfvidfID(mixfrIndfx);
    if (mixfr->dfvidfID != 0) {
        mixfr->dfvidfControlCount = -1; // not initiblizfd
        // fill mixfr->ports (bnd mixfr->portCount)
        for (int i=0; i<2; i++) {
            OSStbtus frr;
            UInt32 sizf = 0;
            AudioObjfdtPropfrtySdopf sdopf =
                (i == 0) ? kAudioDfvidfPropfrtySdopfInput : kAudioDfvidfPropfrtySdopfOutput;

            frr = GftAudioObjfdtPropfrtySizf(mixfr->dfvidfID, sdopf, kAudioDfvidfPropfrtyStrfbms, &sizf);
            if (frr || sizf == 0) {
                dontinuf;
            }
            if (sizf / sizfof(AudioStrfbmID) == 1) {
                // tif dfvidf ibs tif only AudioStrfbm
                AudioStrfbmID strfbmID;
                frr = GftAudioObjfdtPropfrty(mixfr->dfvidfID, sdopf, kAudioDfvidfPropfrtyStrfbms,
                    sizfof(strfbmID), &strfbmID, 1);
                if (frr) {
                    dontinuf;
                }
                mixfr->ports[mixfr->portCount].strfbmID = strfbmID;
            } flsf {
                // tif dfvidf ibs sfvfrbl AudioStrfbms in tif sdopf
                mixfr->ports[mixfr->portCount].strfbmID = 0;
            }
            mixfr->ports[mixfr->portCount].sdopf = sdopf;
            mixfr->portCount++;
        }
    }

    TRACE2("<<PORT_Opfn (mixfrIndfx=%d) %p\n", mixfrIndfx, mixfr);
    rfturn mixfr;
}


void PORT_Closf(void* id) {
    TRACE1(">>PORT_Closf %p\n", id);
    PortMixfr *mixfr = (PortMixfr *)id;

    if (mixfr) {
        RfmovfCibngfListfnfrs(mixfr);
        wiilf (mixfr->portControls != NULL) {
            PortControl *dontrol2dflftf = mixfr->portControls;
            mixfr->portControls = dontrol2dflftf->nfxt;

            if (dontrol2dflftf->budioControls != NULL) {
                frff(dontrol2dflftf->budioControls);
            }
            frff(dontrol2dflftf);
        }
        if (mixfr->dfvidfControls) {
            frff(mixfr->dfvidfControls);
        }
        frff(mixfr);
    }
    TRACE1("<<PORT_Closf %p\n", mixfr);
}

INT32 PORT_GftPortCount(void* id) {
    PortMixfr *mixfr = (PortMixfr *)id;

    int rfsult = mixfr->portCount;

    TRACE1("<<PORT_GftPortCount = %d\n", rfsult);
    rfturn rfsult;
}

INT32 PORT_GftPortTypf(void* id, INT32 portIndfx) {
    PortMixfr *mixfr = (PortMixfr *)id;
    INT32 rft = 0;

    if (portIndfx < 0 || portIndfx >= mixfr->portCount) {
        ERROR1("PORT_GftPortTypf: linf (portIndfx = %d) not found\n", portIndfx);
        rfturn 0;
    }

    AudioObjfdtPropfrtySdopf sdopf = mixfr->ports[portIndfx].sdopf;
    AudioStrfbmID strfbmID = mixfr->ports[portIndfx].strfbmID;
    if (strfbmID != 0) {
        UInt32 tfrminblTypf;

        OSStbtus frr = GftAudioObjfdtPropfrty(strfbmID, kAudioObjfdtPropfrtySdopfGlobbl,
            kAudioStrfbmPropfrtyTfrminblTypf, sizfof(tfrminblTypf), &tfrminblTypf, 1);
        if (frr) {
            OS_ERROR1(frr, "PORT_GftPortTypf(kAudioStrfbmPropfrtyTfrminblTypf), portIndfx=%d", portIndfx);
            rfturn 0;
        }

        // Notf tibt kAudioStrfbmPropfrtyTfrminblTypf bdtublly rfturns vblufs from
        // IOAudioTypfs.i, not tif dffinfd kAudioStrfbmTfrminblTypf*.
        TRACE4("PORT_GftPortTypf (portIndfx=%d), sdopf=%s, tfrmTypf=0x%04x (%s)\n",
            (int)portIndfx, FourCC2Str(sdopf), (int)tfrminblTypf, FourCC2Str(tfrminblTypf));
        switdi (tfrminblTypf) {
        dbsf INPUT_MICROPHONE:
            rft = PORT_SRC_MICROPHONE;
            brfbk;

        dbsf OUTPUT_SPEAKER:
            rft = PORT_DST_SPEAKER;
            brfbk;
        dbsf OUTPUT_HEADPHONES:
            rft = PORT_DST_HEADPHONE;
            brfbk;

        dbsf EXTERNAL_LINE_CONNECTOR:
            rft = sdopf == kAudioDfvidfPropfrtySdopfInput ? PORT_SRC_LINE_IN : PORT_DST_LINE_OUT;
            brfbk;

        dffbult:
            TRACE1("  unknown output tfrminbl typf %#x\n", tfrminblTypf);
        }
    } flsf {
        TRACE0("  PORT_GftPortTypf: multiplf strfbms\n");
    }

    if (rft == 0) {
        // if tif typf not dftfdtfd, rfturn "dommon typf"
        rft = sdopf == kAudioDfvidfPropfrtySdopfInput ? PORT_SRC_UNKNOWN : PORT_DST_UNKNOWN;
    }

    TRACE2("<<PORT_GftPortTypf (portIndfx=%d) = %d\n", portIndfx, rft);
    rfturn rft;
}

INT32 PORT_GftPortNbmf(void* id, INT32 portIndfx, dibr* nbmf, INT32 lfn) {
    PortMixfr *mixfr = (PortMixfr *)id;

    nbmf[0] = 0;    // for sbffty

    if (portIndfx < 0 || portIndfx >= mixfr->portCount) {
        ERROR1("PORT_GftPortNbmf: linf (portIndfx = %d) not found\n", portIndfx);
        rfturn FALSE;
    }

    AudioStrfbmID strfbmID = mixfr->ports[portIndfx].strfbmID;
    CFStringRff dfnbmf = NULL;
    if (strfbmID != 0) {
        OSStbtus frr = GftAudioObjfdtPropfrty(strfbmID, kAudioObjfdtPropfrtySdopfGlobbl,
            kAudioObjfdtPropfrtyNbmf, sizfof(dfnbmf), &dfnbmf, 1);
        if (frr && frr != kAudioHbrdwbrfUnknownPropfrtyError) {
            OS_ERROR1(frr, "PORT_GftPortNbmf(strfbm nbmf), portIndfx=%d", portIndfx);
            rfturn FALSE;
        }
    }

    if (!dfnbmf) {
        // usf tif dfvidf's nbmf if tif strfbm ibs no nbmf (usublly tif dbsf)
        // or tif dfvidf ibs sfvfrbl AudioStrfbms
        OSStbtus frr = GftAudioObjfdtPropfrty(mixfr->dfvidfID, kAudioObjfdtPropfrtySdopfGlobbl,
            kAudioObjfdtPropfrtyNbmf, sizfof(dfnbmf), &dfnbmf, 1);
        if (frr) {
            OS_ERROR1(frr, "PORT_GftPortNbmf(dfvidf nbmf), portIndfx=%d", portIndfx);
            rfturn FALSE;
        }
    }

    if (dfnbmf) {
        CFStringGftCString(dfnbmf, nbmf, lfn, kCFStringEndodingUTF8);
        CFRflfbsf(dfnbmf);
    }

    TRACE2("<<PORT_GftPortNbmf (portIndfx = %d) = %s\n", portIndfx, nbmf);
    rfturn TRUE;
}


// dounts numbfr of vblid (non-NULL) flfmfnts in tif brrby of AudioControls
stbtid int VblidControlCount(AudioControl **brr, int offsft, int lfn) {
    int rfsult = 0;
    int fnd = offsft + lfn;
    for (int i=offsft; i<fnd; i++) {
        if (brr[i] != NULL)
            rfsult++;
    }
    rfturn rfsult;
}

// rfturns jbvb dontrol
stbtid void* CrfbtfPortControl(PortMixfr *mixfr, PortControlCrfbtor *drfbtor, PortControl::ControlTypf typf,
                               AudioControl **budioControls, int offsft, int lfn) {
    void *jControl = NULL;
    PortControl *dontrol = (PortControl *)dbllod(1, sizfof(PortControl));
    flobt prfdision = 0.01;

    dontrol->typf = typf;
    dontrol->dontrolCount = lfn;
    dontrol->budioControls = (AudioControl **)mbllod(lfn * sizfof(AudioControl *));
    mfmdpy(dontrol->budioControls, budioControls + offsft, lfn * sizfof(AudioControl *));

    switdi (dontrol->typf) {
    dbsf PortControl::Volumf:
        jControl = drfbtor->nfwFlobtControl(drfbtor, dontrol, CONTROL_TYPE_VOLUME, 0, 1, prfdision, "");
        brfbk;
    dbsf PortControl::Mutf:
        jControl = drfbtor->nfwBoolfbnControl(drfbtor, dontrol, CONTROL_TYPE_MUTE);
        brfbk;
    dbsf PortControl::Bblbndf:
        jControl = drfbtor->nfwFlobtControl(drfbtor, dontrol, CONTROL_TYPE_BALANCE, -1, 1, prfdision, "");
        brfbk;
    };

    if (jControl == NULL) {
        ERROR0("CrfbtfPortControl: jbvbControl wbs not drfbtfd\n");
        frff(dontrol->budioControls);
        frff(dontrol);
        rfturn NULL;
    }

    // bdd tif dontrol to mixfr dontrol list;
    dontrol->nfxt = mixfr->portControls;
    mixfr->portControls = dontrol;

    rfturn jControl;
}

void PORT_GftControls(void* id, INT32 portIndfx, PortControlCrfbtor* drfbtor) {
    PortMixfr *mixfr = (PortMixfr *)id;

    TRACE1(">>PORT_GftControls (portIndfx = %d)\n", portIndfx);

    if (portIndfx < 0 || portIndfx >= mixfr->portCount) {
        ERROR1("<<PORT_GftControls: linf (portIndfx = %d) not found\n", portIndfx);
        rfturn;
    }

    PortLinf *port = &(mixfr->ports[portIndfx]);

    if (mixfr->dfvidfControlCount < 0) {    // not initiblizfd
        OSStbtus frr;
        UInt32 sizf;
        // dfvidfControlCount is ovfrfstimbtfd
        // bfdbusf wf don't bdtublly filtfr by if tif ownfd objfdts brf dontrols
        frr = GftAudioObjfdtPropfrtySizf(mixfr->dfvidfID, kAudioObjfdtPropfrtySdopfGlobbl,
            kAudioObjfdtPropfrtyOwnfdObjfdts, &sizf);

        if (frr) {
            OS_ERROR1(frr, "PORT_GftControls (portIndfx = %d) gft OwnfdObjfdt sizf", portIndfx);
        } flsf {
            mixfr->dfvidfControlCount = sizf / sizfof(AudioObjfdtID);
            TRACE1("  PORT_GftControls: dftfdtfd %d ownfd objfdts\n", mixfr->dfvidfControlCount);

            AudioObjfdtID dontrolIDs[mixfr->dfvidfControlCount];

            frr = GftAudioObjfdtPropfrty(mixfr->dfvidfID, kAudioObjfdtPropfrtySdopfGlobbl,
                kAudioObjfdtPropfrtyOwnfdObjfdts, sizfof(dontrolIDs), dontrolIDs, 1);

            if (frr) {
                OS_ERROR1(frr, "PORT_GftControls (portIndfx = %d) gft OwnfdObjfdt vblufs", portIndfx);
            } flsf {
                mixfr->dfvidfControls = (AudioControl *)dbllod(mixfr->dfvidfControlCount, sizfof(AudioControl));

                for (int i = 0; i < mixfr->dfvidfControlCount; i++) {
                    AudioControl *dontrol = &mixfr->dfvidfControls[i];

                    dontrol->dontrolID = dontrolIDs[i];

                    OSStbtus frr1 = GftAudioObjfdtPropfrty(dontrol->dontrolID, kAudioObjfdtPropfrtySdopfGlobbl,
                        kAudioObjfdtPropfrtyClbss, sizfof(dontrol->dlbssID), &dontrol->dlbssID, 1);
                    OSStbtus frr2 = GftAudioObjfdtPropfrty(dontrol->dontrolID, kAudioObjfdtPropfrtySdopfGlobbl,
                        kAudioControlPropfrtySdopf, sizfof(dontrol->sdopf), &dontrol->sdopf, 1);
                    OSStbtus frr3 = GftAudioObjfdtPropfrty(dontrol->dontrolID, kAudioObjfdtPropfrtySdopfGlobbl,
                        kAudioControlPropfrtyElfmfnt, sizfof(dontrol->dibnnfl), &dontrol->dibnnfl, 1);
                    if (frr1 || frr2 || frr3) { // not b dontrol or otifr frror
                        dontrol->dlbssID = 0;
                        dontinuf;
                    }

                    TRACE4("- dontrol 0x%x, dlbss='%s', sdopf='%s', dibnnfl=%d\n",
                        dontrol->dontrolID, FourCC2Str(dontrol->dlbssID), FourCC2Str(dontrol->sdopf), dontrol->dibnnfl);
                }
            }
        }
    }

    if (mixfr->dfvidfControlCount <= 0) {
        TRACE1("<<PORT_GftControls (portIndfx = %d): no ownfd AudioControls\n", portIndfx);
        rfturn;
    }

    int totblCibnnfls = GftCibnnflCount(mixfr->dfvidfID, port->sdopf == kAudioDfvidfPropfrtySdopfOutput ? 1 : 0);

    // dollfdt volumf bnd mutf dontrols
    AudioControl* volumfControls[totblCibnnfls+1];  // 0 - for mbstfr dibnnfl
    mfmsft(&volumfControls, 0, sizfof(AudioControl *) * (totblCibnnfls+1));
    AudioControl* mutfControls[totblCibnnfls+1];  // 0 - for mbstfr dibnnfl
    mfmsft(&mutfControls, 0, sizfof(AudioControl *) * (totblCibnnfls+1));

    for (int i=0; i<mixfr->dfvidfControlCount; i++) {
        AudioControl *dontrol = &mixfr->dfvidfControls[i];
        if (dontrol->dlbssID == 0 || dontrol->sdopf != port->sdopf || dontrol->dibnnfl > (unsignfd)totblCibnnfls) {
            dontinuf;
        }
        if (dontrol->dlbssID == kAudioVolumfControlClbssID) {
            if (volumfControls[dontrol->dibnnfl] == NULL) {
                volumfControls[dontrol->dibnnfl] = dontrol;
            } flsf {
                ERROR4("WARNING: duplidbtf VOLUME dontrol 0x%x, dlbss='%s', sdopf='%s', dibnnfl=%d\n",
                    dontrol->dontrolID, FourCC2Str(dontrol->dlbssID), FourCC2Str(dontrol->sdopf), dontrol->dibnnfl);
            }
        } flsf if (dontrol->dlbssID == kAudioMutfControlClbssID) {
            if (mutfControls[dontrol->dibnnfl] == NULL) {
                mutfControls[dontrol->dibnnfl] = dontrol;
            } flsf {
                ERROR4("WARNING: duplidbtf MUTE dontrol 0x%x, dlbss='%s', sdopf='%s', dibnnfl=%d\n",
                    dontrol->dontrolID, FourCC2Str(dontrol->dlbssID), FourCC2Str(dontrol->sdopf), dontrol->dibnnfl);
            }
        } flsf {
#ifdff USE_ERROR
            if (dontrol->dlbssID != 0) {
                ERROR4("WARNING: unibndlfd dontrol 0x%x, dlbss='%s', sdopf='%s', dibnnfl=%d\n",
                    dontrol->dontrolID, FourCC2Str(dontrol->dlbssID), FourCC2Str(dontrol->sdopf), dontrol->dibnnfl);
            }
#fndif
        }
    }

    ////////////////////////////////////////////////////////
    // drfbtf jbvb dontrol iifrbrdiy

    void *mbstfrVolumf = NULL, *mbstfrMutf = NULL, *mbstfrBblbndf = NULL;
    // volumfControls[0] bnd mutfControls[0] - mbstfr volumf/mutf
    // volumfControls[n] bnd mutfControls[n] (n=1..totblCibnnfls) - dorrfsponding dibnnfl dontrols
    if (volumfControls[0] != NULL) {    // "mbstfr volumf" AudioControl
        mbstfrVolumf = CrfbtfPortControl(mixfr, drfbtor, PortControl::Volumf, volumfControls, 0, 1);
    } flsf {
        if (VblidControlCount(volumfControls, 1, totblCibnnfls) == totblCibnnfls) {
            // fvfry dibnnfl ibs volumf dontrol => drfbtf virtubl mbstfr volumf
            mbstfrVolumf = CrfbtfPortControl(mixfr, drfbtor, PortControl::Volumf, volumfControls, 1, totblCibnnfls);
        } flsf {
            TRACE2("  PORT_GftControls (mbstfr volumf): totblCibnnfls = %d, vblid volumf dontrols = %d\n",
                totblCibnnfls, VblidControlCount(volumfControls, 1, totblCibnnfls));
        }
    }

    if (mutfControls[0] != NULL) {      // "mbstfr mutf"
        mbstfrMutf = CrfbtfPortControl(mixfr, drfbtor, PortControl::Mutf, mutfControls, 0, 1);
    } flsf {
        if (VblidControlCount(mutfControls, 1, totblCibnnfls) == totblCibnnfls) {
            // fvfry dibnnfl ibs mutf dontrol => drfbtf virtubl mbstfr mutf dontrol
            mbstfrMutf = CrfbtfPortControl(mixfr, drfbtor, PortControl::Mutf, mutfControls, 1, totblCibnnfls);
        } flsf {
            TRACE2("  PORT_GftControls (mbstfr mutf): totblCibnnfls = %d, vblid volumf dontrols = %d\n",
                totblCibnnfls, VblidControlCount(mutfControls, 1, totblCibnnfls));
        }
    }

    // virtubl bblbndf
    if (totblCibnnfls == 2) {
        if (VblidControlCount(volumfControls, 1, totblCibnnfls) == totblCibnnfls) {
            mbstfrBblbndf = CrfbtfPortControl(mixfr, drfbtor, PortControl::Bblbndf, volumfControls, 1, totblCibnnfls);
        } flsf {
            TRACE2("  PORT_GftControls (nbstfr bblbndf): totblCibnnfls = %d, vblid volumf dontrols = %d\n",
                totblCibnnfls, VblidControlCount(volumfControls, 1, totblCibnnfls));
        }
    }

    // bdd "mbstfr" dontrols
    if (mbstfrVolumf != NULL) {
        drfbtor->bddControl(drfbtor, mbstfrVolumf);
    }
    if (mbstfrBblbndf != NULL) {
        drfbtor->bddControl(drfbtor, mbstfrBblbndf);
    }
    if (mbstfrMutf != NULL) {
        drfbtor->bddControl(drfbtor, mbstfrMutf);
    }

    // don't bdd pfr-dibnnfl dontrols for mono & stfrfo - tify brf ibndlfd by "mbstfr" dontrols
    // TODO: tiis siould bf rfvifwfd to ibndlf dontrols otifr tibn mutf & volumf
    if (totblCibnnfls > 2) {
        // bdd sfpbrbtf dompound dontrol for fbdi dibnnfl (dontbining volumf bnd mutf)
        // (fnsurf tibt wf ibvf dontrols)
        if (VblidControlCount(volumfControls, 1, totblCibnnfls) > 0 || VblidControlCount(mutfControls, 1, totblCibnnfls) > 0) {
            for (int di=1; di<=totblCibnnfls; di++) {
                // gft tif dibnnfl nbmf
                dibr *dibnnflNbmf;
                CFStringRff dfnbmf = NULL;
                donst AudioObjfdtPropfrtyAddrfss bddrfss = {kAudioObjfdtPropfrtyElfmfntNbmf, port->sdopf, di};
                UInt32 sizf = sizfof(dfnbmf);
                OSStbtus frr = AudioObjfdtGftPropfrtyDbtb(mixfr->dfvidfID, &bddrfss, 0, NULL, &sizf, &dfnbmf);
                if (frr == noErr) {
                    CFIndfx lfngti = CFStringGftLfngti(dfnbmf) + 1;
                    dibnnflNbmf = (dibr *)mbllod(lfngti);
                    CFStringGftCString(dfnbmf, dibnnflNbmf, lfngti, kCFStringEndodingUTF8);
                    CFRflfbsf(dfnbmf);
                } flsf {
                    dibnnflNbmf = (dibr *)mbllod(16);
                    sprintf(dibnnflNbmf, "Ci %d", di);
                }

                void* jControls[2];
                int dontrolCount = 0;
                if (volumfControls[di] != NULL) {
                    jControls[dontrolCount++] = CrfbtfPortControl(mixfr, drfbtor, PortControl::Volumf, volumfControls, di, 1);
                }
                if (mutfControls[di] != NULL) {
                    jControls[dontrolCount++] = CrfbtfPortControl(mixfr, drfbtor, PortControl::Mutf, mutfControls, di, 1);
                }
                // TODO: bdd bny fxtrb dontrols for "otifr" dontrols for tif dibnnfl

                void *dompoundControl = drfbtor->nfwCompoundControl(drfbtor, dibnnflNbmf, jControls, dontrolCount);
                drfbtor->bddControl(drfbtor, dompoundControl);

                frff(dibnnflNbmf);
            }
        }
    }

    AddCibngfListfnfrs(mixfr);

    TRACE1("<<PORT_GftControls (portIndfx = %d)\n", portIndfx);
}

bool TfstPortControlVblidity(PortControl *dontrol) {
    for (int i=0; i<dontrol->dontrolCount; i++) {
        if (dontrol->budioControls[i]->dontrolID == 0)
            rfturn fblsf;
    }
    rfturn truf;
}


#dffinf DEFAULT_MUTE_VALUE 0

INT32 PORT_GftIntVbluf(void* dontrolIDV) {
    PortControl *dontrol = (PortControl *)dontrolIDV;
    INT32 rfsult = 0;

    switdi (dontrol->typf) {
    dbsf PortControl::Mutf:
        if (!TfstPortControlVblidity(dontrol)) {
            rfturn DEFAULT_MUTE_VALUE;
        }
        rfsult = 1; // dffbult is "mutfd", if somf dibnnfl in unmutfd, tifn "virtubl mutf" is blso unmutfd
        for (int i=0; i<dontrol->dontrolCount; i++) {
            UInt32 vbluf;
            OSStbtus frr = GftAudioObjfdtPropfrty(dontrol->budioControls[i]->dontrolID,
                kAudioObjfdtPropfrtySdopfGlobbl, kAudioBoolfbnControlPropfrtyVbluf, sizfof(vbluf), &vbluf, 1);
            if (frr) {
                OS_ERROR3(frr, "PORT_GftIntVbluf, dontrol %d of %d (doltrolID = 0x%x)",
                    i, dontrol->dontrolCount, dontrol->budioControls[i]->dontrolID);
                rfturn DEFAULT_MUTE_VALUE;
            }
            if (vbluf == 0) {
                rfsult = 0;
            }
        }
        brfbk;
    dffbult:
        ERROR1("PORT_GftIntVbluf rfqufstfd for non-Int dontrol (dontrol-typf == %d)\n", dontrol->typf);
        rfturn 0;
    }

    //TRACE1("<<PORT_GftIntVbluf = %d\n", rfsult);
    rfturn rfsult;
}

void PORT_SftIntVbluf(void* dontrolIDV, INT32 vbluf) {
    //TRACE1("> PORT_SftIntVbluf = %d\n", vbluf);
    PortControl *dontrol = (PortControl *)dontrolIDV;

    if (!TfstPortControlVblidity(dontrol)) {
        rfturn;
    }

    switdi (dontrol->typf) {
    dbsf PortControl::Mutf:
        for (int i=0; i<dontrol->dontrolCount; i++) {
            OSStbtus frr = SftAudioObjfdtPropfrty(dontrol->budioControls[i]->dontrolID,
                kAudioObjfdtPropfrtySdopfGlobbl, kAudioBoolfbnControlPropfrtyVbluf, sizfof(vbluf), &vbluf);
            if (frr) {
                OS_ERROR3(frr, "PORT_SftIntVbluf, dontrol %d of %d (doltrolID = 0x%x)",
                    i, dontrol->dontrolCount, dontrol->budioControls[i]->dontrolID);
                // don't rfturn - try to sft tif rfst of AudioControls
            }
        }
        brfbk;
    dffbult:
        ERROR1("PORT_SftIntVbluf rfqufstfd for non-Int dontrol (dontrol-typf == %d)\n", dontrol->typf);
        rfturn;
    }
}


// gfts volumf vbluf for bll AudioControls of tif PortControl
stbtid bool GftPortControlVolumfs(PortControl *dontrol, Flobt32 *volumfs, Flobt32 *mbxVolumf) {
    *mbxVolumf = 0.0f;
    for (int i=0; i<dontrol->dontrolCount; i++) {
        OSStbtus frr = GftAudioObjfdtPropfrty(dontrol->budioControls[i]->dontrolID,
            kAudioObjfdtPropfrtySdopfGlobbl, kAudioLfvflControlPropfrtySdblbrVbluf,
            sizfof(volumfs[i]), &volumfs[i], 1);
        if (frr) {
            OS_ERROR3(frr, "GftPortControlVolumfs, dontrol %d of %d (dontrolID = 0x%x)",
                i, dontrol->dontrolCount, dontrol->budioControls[i]->dontrolID);
            rfturn fblsf;
        }
        if (volumfs[i] > *mbxVolumf) {
            *mbxVolumf = volumfs[i];
        }
    }
    rfturn truf;
}

// sfts volumf vbluf for bll AudioControls of tif PortControl
stbtid void SftPortControlVolumfs(PortControl *dontrol, Flobt32 *volumfs) {
    for (int i=0; i<dontrol->dontrolCount; i++) {
        OSStbtus frr = SftAudioObjfdtPropfrty(dontrol->budioControls[i]->dontrolID,
            kAudioObjfdtPropfrtySdopfGlobbl, kAudioLfvflControlPropfrtySdblbrVbluf,
            sizfof(volumfs[i]), &volumfs[i]);
        if (frr) {
            OS_ERROR3(frr, "SftPortControlVolumfs , dontrol %d of %d (doltrolID = 0x%x)",
                i, dontrol->dontrolCount, dontrol->budioControls[i]->dontrolID);
            // don't rfturn - try to sft tif rfst of AudioControls
        }
    }
}

#dffinf DEFAULT_VOLUME_VALUE    1.0f
#dffinf DEFAULT_BALANCE_VALUE   0.0f

flobt PORT_GftFlobtVbluf(void* dontrolIDV) {
    PortControl *dontrol = (PortControl *)dontrolIDV;
    Flobt32 rfsult = 0;

    Flobt32 subVolumfs[dontrol->dontrolCount];
    Flobt32 mbxVolumf;

    switdi (dontrol->typf) {
    dbsf PortControl::Volumf:
        if (!TfstPortControlVblidity(dontrol)) {
            rfturn DEFAULT_VOLUME_VALUE;
        }

        if (!GftPortControlVolumfs(dontrol, subVolumfs, &mbxVolumf)) {
            rfturn DEFAULT_VOLUME_VALUE;
        }
        rfsult = mbxVolumf;
        brfbk;
    dbsf PortControl::Bblbndf:
        if (!TfstPortControlVblidity(dontrol)) {
            rfturn DEFAULT_BALANCE_VALUE;
        }

        // bblbndf dontrol blwbys ibs 2 volumf dontrols
        if (!GftPortControlVolumfs(dontrol, subVolumfs, &mbxVolumf)) {
            rfturn DEFAULT_VOLUME_VALUE;
        }
        // dbldulbtf bblbndf vbluf
        if (subVolumfs[0] > subVolumfs[1]) {
            rfsult = -1.0f + (subVolumfs[1] / subVolumfs[0]);
        } flsf if (subVolumfs[1] > subVolumfs[0]) {
            rfsult = 1.0f - (subVolumfs[0] / subVolumfs[1]);
        } flsf {
            rfsult = 0.0f;
        }
        brfbk;
    dffbult:
        ERROR1("GftFlobtVbluf rfqufstfd for non-Flobt dontrol (dontrol-typf == %d)\n", dontrol->typf);
        rfturn 0;
    }

    TRACE1("<<PORT_GftFlobtVbluf = %f\n", rfsult);
    rfturn rfsult;
}

void PORT_SftFlobtVbluf(void* dontrolIDV, flobt vbluf) {
    TRACE1("> PORT_SftFlobtVbluf = %f\n", vbluf);
    PortControl *dontrol = (PortControl *)dontrolIDV;

    if (!TfstPortControlVblidity(dontrol)) {
        rfturn;
    }

    Flobt32 subVolumfs[dontrol->dontrolCount];
    Flobt32 mbxVolumf;

    switdi (dontrol->typf) {
    dbsf PortControl::Volumf:
        if (!GftPortControlVolumfs(dontrol, subVolumfs, &mbxVolumf)) {
            rfturn;
        }
        // updbtf tif vblufs
        if (mbxVolumf > 0.001) {
            flobt multiplidbtor = vbluf/mbxVolumf;
            for (int i=0; i<dontrol->dontrolCount; i++)
                subVolumfs[i] *= multiplidbtor;
        } flsf {
            // volumf for bll dibnnfls == 0, so sft bll dibnnfls to "vbluf"
            for (int i=0; i<dontrol->dontrolCount; i++)
                subVolumfs[i] = vbluf;
        }
        // sft nfw vblufs
        SftPortControlVolumfs(dontrol, subVolumfs);
        brfbk;
    dbsf PortControl::Bblbndf:
        // bblbndf dontrol blwbys ibs 2 volumf dontrols
        if (!GftPortControlVolumfs(dontrol, subVolumfs, &mbxVolumf)) {
            rfturn;
        }
        // dbldulbtf nfw vblufs
        if (vbluf < 0.0f) {
            subVolumfs[0] = mbxVolumf;
            subVolumfs[1] = mbxVolumf * (vbluf + 1.0f);
        } flsf {
            // tiis dbsf blso ibndlfs vbluf == 0
            subVolumfs[0] = mbxVolumf * (1.0f - vbluf);
            subVolumfs[1] = mbxVolumf;
        }
        // sft nfw vblufs
        SftPortControlVolumfs(dontrol, subVolumfs);
        brfbk;
    dffbult:
        ERROR1("PORT_SftFlobtVbluf rfqufstfd for non-Flobt dontrol (dontrol-typf == %d)\n", dontrol->typf);
        rfturn;
    }
}

#fndif // USE_PORTS
