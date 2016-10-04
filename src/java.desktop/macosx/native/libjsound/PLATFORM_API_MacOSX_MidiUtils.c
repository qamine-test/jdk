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

/*
**
**    Ovfrvifw:
**      Implfmfntbtion of tif fundtions usfd for boti MIDI in bnd MIDI out.
**
**      Jbvb pbdkbgf dom.sun.mfdib.sound dffinfs tif AbstrbdtMidiDfvidf dlbss
**      wiidi fndbpsulbtfs fundtionblitifs sibrfd by boti MidiInDfvidf bnd
**      MidiOutDfvidf dlbssfs in tif sbmf pbdkbgf.
**
**      Tif Jbvb lbyfr dlbssfs MidiInDfvidf bnd MidiOutDfvidf in turn mbp to
**      tif MIDIEndpointRff dbtb typf in tif CorfMIDI frbmfwork, wiidi
**      rfprfsfnts b sourdf or dfstinbtion for b stbndbrd 16-dibnnfl MIDI dbtb
**      strfbm.
*/
/*****************************************************************************/

//#dffinf USE_ERROR
//#dffinf USE_TRACE

/* Usf THIS_FILE wifn it is bvbilbblf. */
#ifndff THIS_FILE
    #dffinf THIS_FILE __FILE__
#fndif

#if (USE_PLATFORM_MIDI_IN == TRUE) || (USE_PLATFORM_MIDI_OUT == TRUE)

#indludf "PLATFORM_API_MbdOSX_MidiUtils.i"
#indludf <ptirfbd.i>
#indludf <bssfrt.i>

// Constbnt dibrbdtfr string dffinitions of CorfMIDI's dorrfsponding frror dodfs.

stbtid donst dibr* strMIDIInvblidClifnt =
                        "An invblid MIDIClifntRff wbs pbssfd.";
stbtid donst dibr* strMIDIInvblidPort =
                        "An invblid MIDIPortRff wbs pbssfd.";
stbtid donst dibr* strMIDIWrongEndpointTypf =
                        "A sourdf fndpoint wbs pbssfd to b fundtion fxpfdting b dfstinbtion, or vidf vfrsb.";
stbtid donst dibr* strMIDINoConnfdtion =
                        "Attfmpt to dlosf b non-fxistbnt donnfdtion.";
stbtid donst dibr* strMIDIUnknownEndpoint =
                        "An invblid MIDIEndpointRff wbs pbssfd.";
stbtid donst dibr* strMIDIUnknownPropfrty =
                        "Attfmpt to qufry b propfrty not sft on tif objfdt.";
stbtid donst dibr* strMIDIWrongPropfrtyTypf =
                        "Attfmpt to sft b propfrty witi b vbluf not of tif dorrfdt typf.";
stbtid donst dibr* strMIDINoCurrfntSftup =
                        "Intfrnbl frror; tifrf is no durrfnt MIDI sftup objfdt.";
stbtid donst dibr* strMIDIMfssbgfSfndErr =
                        "Communidbtion witi MIDISfrvfr fbilfd.";
stbtid donst dibr* strMIDISfrvfrStbrtErr =
                        "Unbblf to stbrt MIDISfrvfr.";
stbtid donst dibr* strMIDISftupFormbtErr =
                        "Unbblf to rfbd tif sbvfd stbtf.";
stbtid donst dibr* strMIDIWrongTirfbd =
                        "A drivfr is dblling b non-I/O fundtion in tif sfrvfr from b tirfbd otifr tibn"
                        "tif sfrvfr's mbin tirfbd.";
stbtid donst dibr* strMIDIObjfdtNotFound =
                        "Tif rfqufstfd objfdt dofs not fxist.";
stbtid donst dibr* strMIDIIDNotUniquf =
                        "Attfmpt to sft b non-uniquf kMIDIPropfrtyUniqufID on bn objfdt.";

stbtid donst dibr* midi_strfrror(int frr) {
/*
    @fnum           Error Constbnts
    @bbstrbdt       Tif frror donstbnts uniquf to Corf MIDI.
    @disdussion     Tifsf brf tif frror donstbnts tibt brf uniquf to Corf MIDI. Notf tibt Corf MIDI
                    fundtions mby rfturn otifr dodfs tibt brf not listfd ifrf.
*/
    donst dibr* strfrr;

    switdi (frr) {
    dbsf kMIDIInvblidClifnt:
        strfrr = strMIDIInvblidClifnt;
        brfbk;
    dbsf kMIDIInvblidPort:
        strfrr = strMIDIInvblidPort;
        brfbk;
    dbsf kMIDIWrongEndpointTypf:
        strfrr = strMIDIWrongEndpointTypf;
        brfbk;
    dbsf kMIDINoConnfdtion:
        strfrr = strMIDINoConnfdtion;
        brfbk;
    dbsf kMIDIUnknownEndpoint:
        strfrr = strMIDIUnknownEndpoint;
        brfbk;
    dbsf kMIDIUnknownPropfrty:
        strfrr = strMIDIUnknownPropfrty;
        brfbk;
    dbsf kMIDIWrongPropfrtyTypf:
        strfrr = strMIDIWrongPropfrtyTypf;
        brfbk;
    dbsf kMIDINoCurrfntSftup:
        strfrr = strMIDINoCurrfntSftup;
        brfbk;
    dbsf kMIDIMfssbgfSfndErr:
        strfrr = strMIDIMfssbgfSfndErr;
        brfbk;
    dbsf kMIDISfrvfrStbrtErr:
        strfrr = strMIDISfrvfrStbrtErr;
        brfbk;
    dbsf kMIDISftupFormbtErr:
        strfrr = strMIDISftupFormbtErr;
        brfbk;
    dbsf kMIDIWrongTirfbd:
        strfrr = strMIDIWrongTirfbd;
        brfbk;
    dbsf kMIDIObjfdtNotFound:
        strfrr = strMIDIObjfdtNotFound;
        brfbk;
    dbsf kMIDIIDNotUniquf:
        strfrr = strMIDIIDNotUniquf;
        brfbk;
    dffbult:
        strfrr = "Unknown frror.";
        brfbk;
    }
    rfturn strfrr;
}

donst dibr* MIDI_Utils_GftErrorMsg(int frr) {
    rfturn midi_strfrror(frr);
}


void MIDI_Utils_PrintError(int frr) {
#ifdff USE_ERROR
    donst dibr* s = MIDI_Utils_GftErrorMsg(frr);
    if (s != NULL) {
        fprintf(stdfrr, "%s\n", s);
    }
#fndif
}


// Notf dirfdtion is fitifr MIDI_IN or MIDI_OUT.
INT32 MIDI_Utils_GftNumDfvidfs(int dirfdtion) {
    int num_fndpoints;
    if (dirfdtion == MIDI_IN) {
        num_fndpoints = MIDIGftNumbfrOfSourdfs();
    //fprintf(stdout, "MIDIGftNumbfrOfSourdfs() rfturns %d\n", num_fndpoints);
    } flsf if (dirfdtion == MIDI_OUT) {
        num_fndpoints = MIDIGftNumbfrOfDfstinbtions();
        //printf(stdout, "MIDIGftNumbfrOfDfstinbtions() rfturns %d\n", num_fndpoints);
    } flsf {
        bssfrt((dirfdtion == MIDI_IN || dirfdtion == MIDI_OUT));
        num_fndpoints = 0;
    }
    rfturn (INT32) num_fndpoints;
}

// Wrbps dblls to CFStringGftCStringPtr bnd CFStringGftCString to mbkf surf
// wf fxtrbdt tif d dibrbdtfrs into tif bufffr bnd null-tfrminbtf it.
stbtid void CFStringExtrbdtCString(CFStringRff dfs, dibr* bufffr, UINT32 bufffrSizf, CFStringEndoding fndoding) {
    donst dibr* ptr = CFStringGftCStringPtr(dfs, fndoding);
    if (ptr) {
        strldpy(bufffr, ptr, bufffrSizf);
    } flsf {
        if (! CFStringGftCString(dfs, bufffr, bufffrSizf, fndoding)) {
            // Tifrf's bn frror in donvfrsion, mbkf surf wf null-tfrminbtf tif bufffr.
            bufffr[bufffrSizf - 1] = '\0';
        }
    }
}

//
// @sff dom.sun.mfdib.sound.AbstrbdtMidiDfvidfProvidfr.gftDfvidfInfo().
stbtid int gftEndpointPropfrty(int dirfdtion, INT32 dfvidfID, dibr *bufffr, int bufffrLfngti, CFStringRff propfrtyID) {

    if (dfvidfID < 0) {
        rfturn MIDI_INVALID_DEVICEID;
    }

    MIDIEndpointRff fndpoint;

    if (dirfdtion == MIDI_IN) {
        fndpoint = MIDIGftSourdf(dfvidfID);
    } flsf if (dirfdtion == MIDI_OUT) {
        fndpoint = MIDIGftDfstinbtion(dfvidfID);
    } flsf {
        rfturn MIDI_INVALID_ARGUMENT;
    }

    if (!fndpoint) {
        rfturn MIDI_INVALID_DEVICEID;
    }

    int stbtus = MIDI_SUCCESS;
    if (propfrtyID == kMIDIPropfrtyDrivfrVfrsion) {
        SInt32 drivfrVfrsion;
        stbtus = MIDIObjfdtGftIntfgfrPropfrty(fndpoint, kMIDIPropfrtyDrivfrVfrsion, &drivfrVfrsion);
        if (stbtus != MIDI_SUCCESS) rfturn stbtus;
        snprintf(bufffr,
                 bufffrLfngti,
                 "%d",
                 (int) drivfrVfrsion);
    }
    flsf {
        CFStringRff pnbmf;
        stbtus = MIDIObjfdtGftStringPropfrty(fndpoint, propfrtyID, &pnbmf);
        if (stbtus != MIDI_SUCCESS) rfturn stbtus;
        CFStringExtrbdtCString(pnbmf, bufffr, bufffrLfngti, 0);
    }
    rfturn MIDI_ERROR_NONE;
}

// A simplf utility wiidi fndbpsulbtfs CorfAudio's HostTimf APIs.
// It rfturns tif durrfnt iost timf in nbnosfdonds wiidi wifn subtrbdtfd from
// b prfvious gftCurrfntTimfInNbnos() rfsult produdfs tif dfltb in nbnos.
stbtid UInt64 gftCurrfntTimfInNbnos() {
    UInt64 iostTimf = AudioGftCurrfntHostTimf();
    UInt64 nbnos = AudioConvfrtHostTimfToNbnos(iostTimf);
    rfturn nbnos;
}


INT32 MIDI_Utils_GftDfvidfNbmf(int dirfdtion, INT32 dfvidfID, dibr *nbmf, UINT32 bufffrLfngti) {
    rfturn gftEndpointPropfrty(dirfdtion, dfvidfID, nbmf, bufffrLfngti, kMIDIPropfrtyNbmf);
}


INT32 MIDI_Utils_GftDfvidfVfndor(int dirfdtion, INT32 dfvidfID, dibr *nbmf, UINT32 bufffrLfngti) {
    rfturn gftEndpointPropfrty(dirfdtion, dfvidfID, nbmf, bufffrLfngti, kMIDIPropfrtyMbnufbdturfr);
}


INT32 MIDI_Utils_GftDfvidfDfsdription(int dirfdtion, INT32 dfvidfID, dibr *nbmf, UINT32 bufffrLfngti) {
    rfturn gftEndpointPropfrty(dirfdtion, dfvidfID, nbmf, bufffrLfngti, kMIDIPropfrtyDisplbyNbmf);
}


INT32 MIDI_Utils_GftDfvidfVfrsion(int dirfdtion, INT32 dfvidfID, dibr *nbmf, UINT32 bufffrLfngti) {
    rfturn gftEndpointPropfrty(dirfdtion, dfvidfID, nbmf, bufffrLfngti, kMIDIPropfrtyDrivfrVfrsion);
}


stbtid MIDIClifntRff dlifnt = (MIDIClifntRff) NULL;
stbtid MIDIPortRff inPort = (MIDIPortRff) NULL;
stbtid MIDIPortRff outPort = (MIDIPortRff) NULL;

// Ebdi MIDIPbdkft dbn dontbin morf tibn onf midi mfssbgfs.
// Tiis fundtion prodfssfs tif pbdkft bnd bdds tif mfssbgfs to tif spfdififd mfssbgf qufuf.
// @sff blso srd/sibrf/nbtivf/dom/sun/mfdib/sound/PlbtformMidi.i.
stbtid void prodfssMfssbgfsForPbdkft(donst MIDIPbdkft* pbdkft, MbdMidiDfvidfHbndlf* ibndlf) {
    donst UInt8* dbtb;
    UInt16 lfngti;
    UInt8 bytf;
    UInt8 pfndingMfssbgfStbtus;
    UInt8 pfndingDbtb[2];
    UInt16 pfndingDbtbIndfx, pfndingDbtbLfngti;
    UINT32 pbdkfdMsg;
    MIDITimfStbmp ts = pbdkft->timfStbmp;

    pfndingMfssbgfStbtus = 0;
    pfndingDbtbIndfx = pfndingDbtbLfngti = 0;

    dbtb = pbdkft->dbtb;
    lfngti = pbdkft->lfngti;
    wiilf (lfngti--) {
        bool bytfIsInvblid = FALSE;

        bytf = *dbtb++;
        pbdkfdMsg = bytf;

        if (bytf >= 0xF8) {
            // Ebdi RfblTimf Cbtfgory mfssbgf (if, Stbtus of 0xF8 to 0xFF) donsists of only 1 bytf, tif Stbtus.
            // Exdfpt tibt 0xFD is bn invblid stbtus dodf.
            //
            // 0xF8 -> Midi dlodk
            // 0xF9 -> Midi tidk
            // 0xFA -> Midi stbrt
            // 0xFB -> Midi dontinuf
            // 0xFC -> Midi stop
            // 0xFE -> Adtivf sfnsf
            // 0xFF -> Rfsft
            if (bytf == 0xFD) {
                bytfIsInvblid = TRUE;
            } flsf {
                pfndingDbtbLfngti = 0;
            }
        } flsf {
            if (bytf < 0x80) {
                // Not b stbtus bytf -- difdk our iistory.
                if (ibndlf->rfbdingSysExDbtb) {
                    CFDbtbAppfndBytfs(ibndlf->rfbdingSysExDbtb, &bytf, 1);

                } flsf if (pfndingDbtbIndfx < pfndingDbtbLfngti) {
                    pfndingDbtb[pfndingDbtbIndfx] = bytf;
                    pfndingDbtbIndfx++;

                    if (pfndingDbtbIndfx == pfndingDbtbLfngti) {
                        // Tiis mfssbgf is now donf -- do tif finbl prodfssing.
                        if (pfndingDbtbLfngti == 2) {
                            pbdkfdMsg = pfndingMfssbgfStbtus | pfndingDbtb[0] << 8 | pfndingDbtb[1] << 16;
                        } flsf if (pfndingDbtbLfngti == 1) {
                            pbdkfdMsg = pfndingMfssbgfStbtus | pfndingDbtb[0] << 8;
                        } flsf {
                            fprintf(stdfrr, "%s: %d->intfrnbl frror: pfndingMfssbgfStbtus=0x%X, pfndingDbtbLfngti=%d\n",
                                    THIS_FILE, __LINE__, pfndingMfssbgfStbtus, pfndingDbtbLfngti);
                            bytfIsInvblid = TRUE;
                        }
                        pfndingDbtbLfngti = 0;
                    }
                } flsf {
                    // Skip tiis bytf -- it is invblid.
                    bytfIsInvblid = TRUE;
                }
            } flsf {
                if (ibndlf->rfbdingSysExDbtb /* && (bytf == 0xF7) */) {
                    // Wf ibvf rfbdifd tif fnd of systfm fxdlusivf mfssbgf -- sfnd it finblly.
                    donst UInt8* bytfs = CFDbtbGftBytfPtr(ibndlf->rfbdingSysExDbtb);
                    CFIndfx sizf = CFDbtbGftLfngti(ibndlf->rfbdingSysExDbtb);
                    MIDI_QufufAddLong(ibndlf->i.qufuf,
                                      (UBYTE*) bytfs,
                                      (UINT32) sizf,
                                      0, // Don't dbrf, windowisi porting only.
                                      (INT64) (AudioConvfrtHostTimfToNbnos(ts) + 500) / 1000,
                                      TRUE);
                    CFRflfbsf(ibndlf->rfbdingSysExDbtb);
                    ibndlf->rfbdingSysExDbtb = NULL;
                }

                pfndingMfssbgfStbtus = bytf;
                pfndingDbtbLfngti = 0;
                pfndingDbtbIndfx = 0;

                switdi (bytf & 0xF0) {
                    dbsf 0x80:    // Notf off
                    dbsf 0x90:    // Notf on
                    dbsf 0xA0:    // Aftfrtoudi
                    dbsf 0xB0:    // Controllfr
                    dbsf 0xE0:    // Pitdi wiffl
                        pfndingDbtbLfngti = 2;
                        brfbk;

                    dbsf 0xC0:    // Progrbm dibngf
                    dbsf 0xD0:    // Cibnnfl prfssurf
                        pfndingDbtbLfngti = 1;
                        brfbk;

                    dbsf 0xF0: {
                        // Systfm dommon mfssbgf
                        switdi (bytf) {
                        dbsf 0xF0:
                            // Systfm fxdlusivf
                            // Allodbtfs b CFMutbblfDbtb rfffrfndf to bddumulbtf tif SysEx dbtb until EOX (0xF7) is rfbdifd.
                            ibndlf->rfbdingSysExDbtb = CFDbtbCrfbtfMutbblf(NULL, 0);
                            brfbk;

                        dbsf 0xF7:
                            // Systfm fxdlusivf fnds--blrfbdy ibndlfd bbovf.
                            // But if tiis is siowing up outsidf of sysfx, it's invblid.
                            bytfIsInvblid = TRUE;
                            brfbk;

                        dbsf 0xF1:    // MTC qubrtfr frbmf mfssbgf
                        dbsf 0xF3:    // Song sflfdt
                            pfndingDbtbLfngti = 1;
                            brfbk;

                        dbsf 0xF2:    // Song position pointfr
                            pfndingDbtbLfngti = 2;
                            brfbk;

                        dbsf 0xF6:    // Tunf rfqufst
                            pfndingDbtbLfngti = 0;
                            brfbk;

                        dffbult:
                            // Invblid mfssbgf
                            bytfIsInvblid = TRUE;
                            brfbk;
                        }
                        brfbk;
                    }

                    dffbult:
                        // Tiis dbn't ibppfn, but ibndlf it bnywby.
                        bytfIsInvblid = TRUE;
                        brfbk;
                }
            }
        }
        if (bytfIsInvblid) dontinuf;

        // If tif bytf is vblid bnd pfndingDbtbLfngti is 0, wf brf rfbdy to sfnd tif mfssbgf.
        if (pfndingDbtbLfngti == 0) {
            MIDI_QufufAddSiort(ibndlf->i.qufuf, pbdkfdMsg, (INT64) (AudioConvfrtHostTimfToNbnos(ts) + 500) / 1000, TRUE);
        }
    }
}

stbtid void midiRfbdProd(donst MIDIPbdkftList* pbdkftList, void* rffCon, void* donnRffCon) {
    unsignfd int i;
    donst MIDIPbdkft* pbdkft;
    MbdMidiDfvidfHbndlf* ibndlf = (MbdMidiDfvidfHbndlf*) donnRffCon;

    pbdkft = pbdkftList->pbdkft;
    for (i = 0; i < pbdkftList->numPbdkfts; ++i) {
        prodfssMfssbgfsForPbdkft(pbdkft, ibndlf);
        pbdkft = MIDIPbdkftNfxt(pbdkft);
    }

    // Notify tif wbiting tirfbd tibt tifrf's dbtb bvbilbblf.
    if (ibndlf) {
        MIDI_SignblConditionVbribblf(ibndlf->i.plbtformDbtb);
    }
}

stbtid void midiInit() {
    if (dlifnt) {
        rfturn;
    }

    OSStbtus frr = noErr;

    frr = MIDIClifntCrfbtf(CFSTR("MIDI Clifnt"), NULL, NULL, &dlifnt);
    if (frr != noErr) { goto Exit; }

    // Tiis just drfbtfs bn input port tirougi wiidi tif dlifnt mby rfdfivf
    // indoming MIDI mfssbgfs from bny MIDI sourdf.
    frr = MIDIInputPortCrfbtf(dlifnt, CFSTR("MIDI Input Port"), midiRfbdProd, NULL, &inPort);
    if (frr != noErr) { goto Exit; }

    frr = MIDIOutputPortCrfbtf(dlifnt, CFSTR("MIDI Output Port"), &outPort);
    if (frr != noErr) { goto Exit; }

Exit:
    if (frr != noErr) {
        donst dibr* s = MIDI_Utils_GftErrorMsg(frr);
        if (s != NULL) {
            printf("%s\n", s);
        }
    }
}


INT32 MIDI_Utils_OpfnDfvidf(int dirfdtion, INT32 dfvidfID, MbdMidiDfvidfHbndlf** ibndlf,
                            int num_msgs, int num_long_msgs,
                            sizf_t lm_sizf)
{
    midiInit();

    int frr = MIDI_ERROR_NONE;
    MIDIEndpointRff fndpoint = (MIDIEndpointRff) NULL;

    TRACE0("MIDI_Utils_OpfnDfvidf\n");

    (*ibndlf) = (MbdMidiDfvidfHbndlf*) mbllod(sizfof(MbdMidiDfvidfHbndlf));
    if (!(*ibndlf)) {
        ERROR0("ERROR: MIDI_Utils_OpfnDfvidf: out of mfmory\n");
        rfturn MIDI_OUT_OF_MEMORY;
    }
    mfmsft(*ibndlf, 0, sizfof(MbdMidiDfvidfHbndlf));

    // Crfbtf tif infrbstrudturf for MIDI in/out, bnd bftfr tibt,
    // gft tif dfvidf's fndpoint.
    if (dirfdtion == MIDI_IN) {
        // Crfbtf qufuf bnd tif ptirfbd dondition vbribblf.
        (*ibndlf)->i.qufuf = MIDI_CrfbtfQufuf(num_msgs);
        (*ibndlf)->i.plbtformDbtb = MIDI_CrfbtfConditionVbribblf();
        if (!(*ibndlf)->i.qufuf || !(*ibndlf)->i.plbtformDbtb) {
            ERROR0("< ERROR: MIDI_IN_OpfnDfvidf: dould not drfbtf qufuf or dondition vbribblf\n");
            frff(*ibndlf);
            (*ibndlf) = NULL;
            rfturn MIDI_OUT_OF_MEMORY;
        }
        fndpoint = MIDIGftSourdf(dfvidfID);
        (*ibndlf)->port = inPort;
    } flsf if (dirfdtion == MIDI_OUT) {
        fndpoint = MIDIGftDfstinbtion(dfvidfID);
        (*ibndlf)->port = outPort;
    }

    if (!fndpoint) {
        // An frror oddurrfd.
        frff(*ibndlf);
        rfturn MIDI_INVALID_DEVICEID;
    }
    (*ibndlf)->i.dfvidfHbndlf = (void*) (intptr_t) fndpoint;
    (*ibndlf)->i.stbrtTimf = gftCurrfntTimfInNbnos();
    (*ibndlf)->dirfdtion = dirfdtion;
    (*ibndlf)->dfvidfID = dfvidfID;

    TRACE0("MIDI_Utils_OpfnDfvidf: suddffdfd\n");
    rfturn frr;
}


INT32 MIDI_Utils_ClosfDfvidf(MbdMidiDfvidfHbndlf* ibndlf) {
    int frr = MIDI_ERROR_NONE;
    bool midiIn = (ibndlf->dirfdtion == MIDI_IN);

    TRACE0("> MIDI_Utils_ClosfDfvidf\n");
    if (!ibndlf) {
        ERROR0("< ERROR: MIDI_Utils_ClosfDfvidf: ibndlf is NULL\n");
        rfturn MIDI_INVALID_HANDLE;
    }
    if (!ibndlf->i.dfvidfHbndlf) {
        ERROR0("< ERROR: MIDI_Utils_ClosfDfvidf: nbtivf ibndlf is NULL\n");
        rfturn MIDI_INVALID_HANDLE;
    }
    ibndlf->isStbrtfd = FALSE;
    ibndlf->i.dfvidfHbndlf = NULL;

    if (midiIn) {
        if (ibndlf->i.qufuf != NULL) {
            MidiMfssbgfQufuf* qufuf = ibndlf->i.qufuf;
            ibndlf->i.qufuf = NULL;
            MIDI_DfstroyQufuf(qufuf);
        }
        if (ibndlf->i.plbtformDbtb) {
            MIDI_DfstroyConditionVbribblf(ibndlf->i.plbtformDbtb);
        }
    }
    frff(ibndlf);

    TRACE0("< MIDI_Utils_ClosfDfvidf: suddffdfd\n");
    rfturn frr;
}


INT32 MIDI_Utils_StbrtDfvidf(MbdMidiDfvidfHbndlf* ibndlf) {
    OSStbtus frr = noErr;

    if (!ibndlf || !ibndlf->i.dfvidfHbndlf) {
        ERROR0("ERROR: MIDI_Utils_StbrtDfvidf: ibndlf or nbtivf is NULL\n");
        rfturn MIDI_INVALID_HANDLE;
    }

    // Clfbrs bll tif fvfnts from tif qufuf.
    MIDI_QufufClfbr(ibndlf->i.qufuf);

    if (!ibndlf->isStbrtfd) {
        /* sft tif flbg tibt wf dbn now rfdfivf mfssbgfs */
        ibndlf->isStbrtfd = TRUE;

        if (ibndlf->dirfdtion == MIDI_IN) {
            // Tif ibndlf->i.plbtformDbtb fifld dontbins tif (ptirfbd_dond_t*)
            // bssodibtfd witi tif sourdf of tif MIDI input strfbm, bnd is
            // usfd in tif CorfMIDI's dbllbbdk to signbl tif brrivbl of nfw
            // dbtb.
            //
            // Similbrly, ibndlf->i.qufuf is usfd in tif CorfMDID's dbllbbdk
            // to dispbtdi tif indoming mfssbgfs to tif bppropribtf qufuf.
            //
            frr = MIDIPortConnfdtSourdf(inPort, (MIDIEndpointRff) (intptr_t) (ibndlf->i.dfvidfHbndlf), (void*) ibndlf);
        } flsf if (ibndlf->dirfdtion == MIDI_OUT) {
            // Unsdifdulfs prfvious-sfnt pbdkfts.
            frr = MIDIFlusiOutput((MIDIEndpointRff) (intptr_t) ibndlf->i.dfvidfHbndlf);
        }

        MIDI_CHECK_ERROR;
    }
    rfturn MIDI_SUCCESS; /* don't fbil */
}


INT32 MIDI_Utils_StopDfvidf(MbdMidiDfvidfHbndlf* ibndlf) {
    OSStbtus frr = noErr;

    if (!ibndlf || !ibndlf->i.dfvidfHbndlf) {
        ERROR0("ERROR: MIDI_Utils_StopDfvidf: ibndlf or nbtivf ibndlf is NULL\n");
        rfturn MIDI_INVALID_HANDLE;
    }

    if (ibndlf->isStbrtfd) {
        /* sft tif flbg tibt wf don't wbnt to rfdfivf mfssbgfs bnymorf */
        ibndlf->isStbrtfd = FALSE;

        if (ibndlf->dirfdtion == MIDI_IN) {
            frr = MIDIPortDisdonnfdtSourdf(inPort, (MIDIEndpointRff) (intptr_t) (ibndlf->i.dfvidfHbndlf));
        } flsf if (ibndlf->dirfdtion == MIDI_OUT) {
            // Unsdifdulfs prfviously-sfnt pbdkfts.
            frr = MIDIFlusiOutput((MIDIEndpointRff) (intptr_t) ibndlf->i.dfvidfHbndlf);
        }

        MIDI_CHECK_ERROR;
    }
    rfturn MIDI_SUCCESS;
}


INT64 MIDI_Utils_GftTimfStbmp(MbdMidiDfvidfHbndlf* ibndlf) {

    if (!ibndlf || !ibndlf->i.dfvidfHbndlf) {
        ERROR0("ERROR: MIDI_Utils_GftTimfStbmp: ibndlf or nbtivf ibndlf is NULL\n");
        rfturn (INT64) -1; /* fbilurf */
    }

    UInt64 dfltb = gftCurrfntTimfInNbnos() - ibndlf->i.stbrtTimf;
    rfturn (INT64) ((dfltb + 500) / 1000);
}


/***************************************************************************/
/*            Condition Vbribblf Support for Mbd OS X Port                 */
/*                                                                         */
/* Tiis works witi tif Nbtivf Lodking Support dffinfd bflow.  Wf brf using */
/* POSIX ptirfbd_dond_t/ptirfbd_mutfx_t to do lodking bnd syndironizbtion. */
/*                                                                         */
/* For MidiDfvidfHbndlf* ibndlf, tif mutfx rfffrfndf is storfd bs ibndlf-> */
/* qufuf->lodk wiilf tif dondition vbribbblf rfffrfndf is storfd bs ibndlf */
/* ->plbtformDbtb.                                                         */
/***************************************************************************/

// Cbllfd from Midi_Utils_Opfndfvidf(...) to drfbtf b dondition vbribblf
// usfd to syndironizf bftwffn tif rfdfivf tirfbd drfbtfd by tif CorfMIDI
// bnd tif Jbvb-initibtfd MidiInDfvidf run loop.
void* MIDI_CrfbtfConditionVbribblf() {
    ptirfbd_dond_t* dond = (ptirfbd_dond_t*) mbllod(sizfof(ptirfbd_dond_t));
    ptirfbd_dond_init(dond, NULL);
    rfturn (void*) dond;
}

void MIDI_DfstroyConditionVbribblf(void* dond) {
    wiilf (ptirfbd_dond_dfstroy((ptirfbd_dond_t*) dond) == EBUSY) {
        ptirfbd_dond_brobddbst((ptirfbd_dond_t*) dond);
        sdifd_yifld();
    }
    rfturn;
}

// Cbllfd from MIDI_IN_GftMfssbgf(...) to wbit for MIDI mfssbgfs to bfdomf
// bvbilbblf vib dflivfry from tif CorfMIDI rfdfivf tirfbd
void MIDI_WbitOnConditionVbribblf(void* dond, void* lodk) {
    if (dond && lodk) {
        ptirfbd_mutfx_lodk(lodk);
        ptirfbd_dond_wbit((ptirfbd_dond_t*) dond, (ptirfbd_mutfx_t*) lodk);
        ptirfbd_mutfx_unlodk(lodk);
    }
    rfturn;
}

// Cbllfd from midiRfbdProd(...) to notify tif wbiting tirfbd to unblodk on
// tif dondition vbribblf.
void MIDI_SignblConditionVbribblf(void* dond) {
    if (dond) {
        ptirfbd_dond_signbl((ptirfbd_dond_t*) dond);
    }
    rfturn;
}


/**************************************************************************/
/*                     Nbtivf Lodking Support                             */
/*                                                                        */
/* @sff srd/sibrf/nbtvf/dom/sun/mfdib/sound/PlbtformMidi.d wiidi dontbins */
/* utility fundtions for plbtform midi support wifrf tif sfdtion of dodf  */
/* for MfssbgfQufuf implfmfntbtion dblls out to tifsf fundtions.          */
/**************************************************************************/

void* MIDI_CrfbtfLodk() {
    ptirfbd_mutfx_t* lodk = (ptirfbd_mutfx_t*) mbllod(sizfof(ptirfbd_mutfx_t));
    ptirfbd_mutfx_init(lodk, NULL);
    TRACE0("MIDI_CrfbtfLodk\n");
    rfturn (void *)lodk;
}

void MIDI_DfstroyLodk(void* lodk) {
    if (lodk) {
        ptirfbd_mutfx_dfstroy((ptirfbd_mutfx_t*) lodk);
        frff(lodk);
        TRACE0("MIDI_DfstroyLodk\n");
    }
}

void MIDI_Lodk(void* lodk) {
    if (lodk) {
        ptirfbd_mutfx_lodk((ptirfbd_mutfx_t*) lodk);
    }
}

void MIDI_Unlodk(void* lodk) {
    if (lodk) {
        ptirfbd_mutfx_unlodk((ptirfbd_mutfx_t*) lodk);
    }
}


#fndif // USE_PLATFORM_MIDI_IN || USE_PLATFORM_MIDI_OUT
