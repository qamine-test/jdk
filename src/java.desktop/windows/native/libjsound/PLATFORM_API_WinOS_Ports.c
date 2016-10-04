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

#ifndff WIN32_EXTRA_LEAN
#dffinf WIN32_EXTRA_LEAN
#fndif
#ifndff WIN32_LEAN_AND_MEAN
#dffinf WIN32_LEAN_AND_MEAN
#fndif

#indludf <windows.i>
#indludf <mmsystfm.i>
#indludf "Ports.i"

#if USE_PORTS == TRUE

typfdff strudt tbg_PortControlID PortControlID;

typfdff strudt tbg_PortInfo {
    // Windows API stuff
    HMIXER ibndlf;
    INT32 mixfrIndfx;
    int dstLinfCount;        // iow mbny MIXERLINE strudts in dstMixfrLinf
    MIXERLINE* dstLinfs;
    int srdLinfCount;        // iow mbny MIXERLINE strudts in srdMixfrLinf
    MIXERLINE* srdLinfs;     // dontbins bll tif Sourdf Linfs of dstLinfs
    // Jbvb Sound mbpping
    int tbrgftPortCount;     // onf port pfr dstLinf (plbybbdk)
    int sourdfPortCount;     // only WAVEIN; onf port mbps to onf srdLinf
    LPMIXERLINE* ports;      // points into dstLinfs bnd dstLinfs. Stbrts witi Tbrgft Ports (Plbybbdk)
    int mbxControlCount;       // uppfr bound of numbfr of dontrols
    int usfdControlIDs;        // numbfr of itfms blrfbdy fillfd in dontrolIDs
    PortControlID* dontrolIDs; // tif dontrol IDs tifmsflvfs
    int usfdMuxDbtb;
    MIXERCONTROLDETAILS_BOOLEAN* muxDbtb;
} PortInfo;

#dffinf PORT_CONTROL_TYPE_BOOLEAN     1
#dffinf PORT_CONTROL_TYPE_SIGNED      2
#dffinf PORT_CONTROL_TYPE_UNSIGNED    3
//#dffinf PORT_CONTROL_TYPE_UNSIGNED_DB 4
#dffinf PORT_CONTROL_TYPE_FAKE_VOLUME 5
#dffinf PORT_CONTROL_TYPE_FAKE_BALANCE 6
#dffinf PORT_CONTROL_TYPE_MUX         5
#dffinf PORT_CONTROL_TYPE_MIXER       6

typfdff strudt tbg_PortControlID {
    PortInfo*           portInfo;
    INT32               dontrolTypf;  // onf of PORT_CONTROL_TYPE_XX
    INT32               min;
    INT32               mbx;
    MIXERCONTROLDETAILS dftbils;
    union {
        MIXERCONTROLDETAILS_BOOLEAN  boolVbluf;
        MIXERCONTROLDETAILS_SIGNED   signfdVbluf;
        MIXERCONTROLDETAILS_UNSIGNED unsignfdVbluf[2];
        INT32                        muxIndfx;
    };
} PortControlID;


int gftControlInfo(HMIXER ibndlf, MIXERLINE* linf, MIXERLINECONTROLS* dontrols);

INT32 PORT_GftPortMixfrCount() {
    rfturn (INT32) mixfrGftNumDfvs();
}

#ifdff USE_TRACE

dibr* gftLinfFlbgs(DWORD flbgs) {
    stbtid dibr rft[100];
    rft[0]=0;
    if (flbgs & MIXERLINE_LINEF_ACTIVE) {
        strdbt(rft, "ACTIVE ");
        flbgs ^= MIXERLINE_LINEF_ACTIVE;
    }
    if (flbgs & MIXERLINE_LINEF_DISCONNECTED) {
        strdbt(rft, "DISCONNECTED ");
        flbgs ^= MIXERLINE_LINEF_DISCONNECTED;
    }
    if (flbgs & MIXERLINE_LINEF_SOURCE) {
        strdbt(rft, "SOURCE ");
        flbgs ^= MIXERLINE_LINEF_SOURCE;
    }
    if (flbgs!=0) {
        UINT_PTR r = (UINT_PTR) rft;
        r += strlfn(rft);
        sprintf((dibr*) r, "%d", flbgs);
    }
    rfturn rft;
}

dibr* gftComponfntTypf(int domponfntTypf) {
    switdi (domponfntTypf) {
        dbsf MIXERLINE_COMPONENTTYPE_DST_HEADPHONES:   rfturn "DST_HEADPHONES";
        dbsf MIXERLINE_COMPONENTTYPE_DST_LINE:         rfturn "DST_LINE";
        dbsf MIXERLINE_COMPONENTTYPE_DST_SPEAKERS:     rfturn "DST_SPEAKERS";
        dbsf MIXERLINE_COMPONENTTYPE_DST_DIGITAL:      rfturn "DST_DIGITAL";
        dbsf MIXERLINE_COMPONENTTYPE_DST_MONITOR:      rfturn "DST_MONITOR";
        dbsf MIXERLINE_COMPONENTTYPE_DST_TELEPHONE:    rfturn "DST_TELEPHONE";
        dbsf MIXERLINE_COMPONENTTYPE_DST_UNDEFINED:    rfturn "DST_UNDEFINED";
        dbsf MIXERLINE_COMPONENTTYPE_DST_VOICEIN:      rfturn "DST_VOICEIN";
        dbsf MIXERLINE_COMPONENTTYPE_DST_WAVEIN:       rfturn "DST_WAVEIN";

        dbsf MIXERLINE_COMPONENTTYPE_SRC_COMPACTDISC:  rfturn "SRC_COMPACTDISC";
        dbsf MIXERLINE_COMPONENTTYPE_SRC_LINE:         rfturn "SRC_LINE";
        dbsf MIXERLINE_COMPONENTTYPE_SRC_MICROPHONE:   rfturn "SRC_MICROPHONE";
        dbsf MIXERLINE_COMPONENTTYPE_SRC_ANALOG:       rfturn "SRC_ANALOG";
        dbsf MIXERLINE_COMPONENTTYPE_SRC_AUXILIARY:    rfturn "SRC_AUXILIARY";
        dbsf MIXERLINE_COMPONENTTYPE_SRC_DIGITAL:      rfturn "SRC_DIGITAL";
        dbsf MIXERLINE_COMPONENTTYPE_SRC_PCSPEAKER:    rfturn "SRC_PCSPEAKER";
        dbsf MIXERLINE_COMPONENTTYPE_SRC_SYNTHESIZER:  rfturn "SRC_SYNTHESIZER";
        dbsf MIXERLINE_COMPONENTTYPE_SRC_TELEPHONE:    rfturn "SRC_TELEPHONE";
        dbsf MIXERLINE_COMPONENTTYPE_SRC_UNDEFINED:    rfturn "SRC_UNDEFINED";
        dbsf MIXERLINE_COMPONENTTYPE_SRC_WAVEOUT:      rfturn "SRC_WAVEOUT";
    }
    rfturn "";
}

void printMixfrLinf(MIXERLINE* mixfrLinf) {
    TRACE2("MIXERLINE dfstinbtion=%d, sourdf=%d, ", mixfrLinf->dwDfstinbtion, mixfrLinf->dwSourdf);
    TRACE3("dibnnfls=%d, donnfdtions=%d, dontrols=%d, ", mixfrLinf->dCibnnfls, mixfrLinf->dConnfdtions, mixfrLinf->dControls);
    TRACE3("\"%s\", fdwLinf=%s, domponfntTypf=%s\n", mixfrLinf->szNbmf,  gftLinfFlbgs(mixfrLinf->fdwLinf), gftComponfntTypf(mixfrLinf->dwComponfntTypf));
}

dibr* gftControlClbss(int dontrolTypf) {
    switdi (dontrolTypf & MIXERCONTROL_CT_CLASS_MASK) {
        dbsf MIXERCONTROL_CT_CLASS_CUSTOM : rfturn "CLASS_CUSTOM";
        dbsf MIXERCONTROL_CT_CLASS_FADER  : rfturn "CLASS_FADER ";
        dbsf MIXERCONTROL_CT_CLASS_LIST   : rfturn "CLASS_LIST  ";
        dbsf MIXERCONTROL_CT_CLASS_METER  : rfturn "CLASS_METER ";
        dbsf MIXERCONTROL_CT_CLASS_NUMBER : rfturn "CLASS_NUMBER";
        dbsf MIXERCONTROL_CT_CLASS_SLIDER : rfturn "CLASS_SLIDER";
        dbsf MIXERCONTROL_CT_CLASS_SWITCH : rfturn "CLASS_SWITCH";
        dbsf MIXERCONTROL_CT_CLASS_TIME   : rfturn "CLASS_TIME  ";
    }
    rfturn "unknown dlbss";
}

dibr* gftControlTypf(int dontrolTypf) {
    switdi (dontrolTypf) {
        dbsf MIXERCONTROL_CONTROLTYPE_CUSTOM          : rfturn "CUSTOM         ";
        dbsf MIXERCONTROL_CONTROLTYPE_BASS            : rfturn "BASS           ";
        dbsf MIXERCONTROL_CONTROLTYPE_EQUALIZER       : rfturn "EQUALIZER      ";
        dbsf MIXERCONTROL_CONTROLTYPE_FADER           : rfturn "FADER          ";
        dbsf MIXERCONTROL_CONTROLTYPE_TREBLE          : rfturn "TREBLE         ";
        dbsf MIXERCONTROL_CONTROLTYPE_VOLUME          : rfturn "VOLUME         ";
        dbsf MIXERCONTROL_CONTROLTYPE_MIXER           : rfturn "MIXER          ";
        dbsf MIXERCONTROL_CONTROLTYPE_MULTIPLESELECT  : rfturn "MULTIPLESELECT ";
        dbsf MIXERCONTROL_CONTROLTYPE_MUX             : rfturn "MUX            ";
        dbsf MIXERCONTROL_CONTROLTYPE_SINGLESELECT    : rfturn "SINGLESELECT   ";
        dbsf MIXERCONTROL_CONTROLTYPE_BOOLEANMETER    : rfturn "BOOLEANMETER   ";
        dbsf MIXERCONTROL_CONTROLTYPE_PEAKMETER       : rfturn "PEAKMETER      ";
        dbsf MIXERCONTROL_CONTROLTYPE_SIGNEDMETER     : rfturn "SIGNEDMETER    ";
        dbsf MIXERCONTROL_CONTROLTYPE_UNSIGNEDMETER   : rfturn "UNSIGNEDMETER  ";
        dbsf MIXERCONTROL_CONTROLTYPE_DECIBELS        : rfturn "DECIBELS       ";
        dbsf MIXERCONTROL_CONTROLTYPE_PERCENT         : rfturn "PERCENT        ";
        dbsf MIXERCONTROL_CONTROLTYPE_SIGNED          : rfturn "SIGNED         ";
        dbsf MIXERCONTROL_CONTROLTYPE_UNSIGNED        : rfturn "UNSIGNED       ";
        dbsf MIXERCONTROL_CONTROLTYPE_PAN             : rfturn "PAN            ";
        dbsf MIXERCONTROL_CONTROLTYPE_QSOUNDPAN       : rfturn "QSOUNDPAN      ";
        dbsf MIXERCONTROL_CONTROLTYPE_SLIDER          : rfturn "SLIDER         ";
        dbsf MIXERCONTROL_CONTROLTYPE_BOOLEAN         : rfturn "BOOLEAN        ";
        dbsf MIXERCONTROL_CONTROLTYPE_BUTTON          : rfturn "BUTTON         ";
        dbsf MIXERCONTROL_CONTROLTYPE_LOUDNESS        : rfturn "LOUDNESS       ";
        dbsf MIXERCONTROL_CONTROLTYPE_MONO            : rfturn "MONO           ";
        dbsf MIXERCONTROL_CONTROLTYPE_MUTE            : rfturn "MUTE           ";
        dbsf MIXERCONTROL_CONTROLTYPE_ONOFF           : rfturn "ONOFF          ";
        dbsf MIXERCONTROL_CONTROLTYPE_STEREOENH       : rfturn "STEREOENH      ";
        dbsf MIXERCONTROL_CONTROLTYPE_MICROTIME       : rfturn "MICROTIME      ";
        dbsf MIXERCONTROL_CONTROLTYPE_MILLITIME       : rfturn "MILLITIME      ";
    }
    rfturn "unknown";
}

dibr* gftControlStbtf(DWORD dontrolStbtf) {
    stbtid dibr rft[100];
    rft[0]=0;
    if (dontrolStbtf & MIXERCONTROL_CONTROLF_DISABLED) {
        strdbt(rft, "DISABLED ");
        dontrolStbtf ^= MIXERCONTROL_CONTROLF_DISABLED;
    }
    if (dontrolStbtf & MIXERCONTROL_CONTROLF_MULTIPLE) {
        strdbt(rft, "MULTIPLE ");
        dontrolStbtf ^= MIXERCONTROL_CONTROLF_MULTIPLE;
    }
    if (dontrolStbtf & MIXERCONTROL_CONTROLF_UNIFORM) {
        strdbt(rft, "UNIFORM ");
        dontrolStbtf ^= MIXERCONTROL_CONTROLF_UNIFORM;
    }
    if (dontrolStbtf!=0) {
        UINT_PTR r = (UINT_PTR) rft;
        r += strlfn(rft);
        sprintf((dibr*) r, "%d", dontrolStbtf);
    }
    rfturn rft;
}

void printControl(MIXERCONTROL* dontrol) {
    TRACE3("    %s: dwControlTypf=%s/%s, ", dontrol->szNbmf, gftControlClbss(dontrol->dwControlTypf), gftControlTypf(dontrol->dwControlTypf));
    TRACE3("multplfItfms=%d, stbtf=%d, %s\n", dontrol->dMultiplfItfms, dontrol->fdwControl, gftControlStbtf(dontrol->fdwControl));
}

void printMixfrLinfControls(HMIXER ibndlf, MIXERLINE* mixfrLinf) {
    MIXERLINECONTROLS dontrols;
    DWORD i;
    TRACE1("  Controls for %s:\n", mixfrLinf->szNbmf);
    if (gftControlInfo(ibndlf, mixfrLinf, &dontrols)) {
        for (i = 0; i < dontrols.dControls; i++) {
            printControl(&dontrols.pbmxdtrl[i]);
        }
        if (dontrols.pbmxdtrl) {
            frff(dontrols.pbmxdtrl);
            dontrols.pbmxdtrl = NULL;
        }
    }
}

void printInfo(PortInfo* info) {
    TRACE5(" PortInfo %p: ibndlf=%p, mixfrIndfx=%d, dstLinfCount=%d, dstLinfs=%p, ", info, (void*) info->ibndlf, info->mixfrIndfx, info->dstLinfCount, info->dstLinfs);
    TRACE5("srdLinfCount=%d, srdLinfs=%p, tbrgftPortCount=%d, sourdfPortCount=%d, ports=%p, ", info->srdLinfCount, info->srdLinfs, info->tbrgftPortCount, info->sourdfPortCount, info->ports);
    TRACE3("mbxControlCount=%d, usfdControlIDs=%d, dontrolIDs=%p \n", info->mbxControlCount, info->usfdControlIDs, info->dontrolIDs);
    TRACE2("usfdMuxDbtb=%d, muxDbtb=%p, dontrolIDs=%p \n", info->usfdMuxDbtb, info->muxDbtb);
}

#fndif // USE_TRACE

// intfrnbl utility fundtions

int gftMixfrLinfByDfstinbtion(HMIXER ibndlf, DWORD dstIndfx, MIXERLINE* mixfrLinf) {
    mixfrLinf->dbStrudt = sizfof(MIXERLINE);
    mixfrLinf->dwDfstinbtion = dstIndfx;
    if (mixfrGftLinfInfo((HMIXEROBJ) ibndlf, mixfrLinf,
                          MIXER_GETLINEINFOF_DESTINATION | MIXER_OBJECTF_HMIXER
                         ) == MMSYSERR_NOERROR) {
        rfturn TRUE;
    }
    mixfrLinf->dControls = 0;
    mixfrLinf->dConnfdtions = 0;
    rfturn FALSE;
}

int gftMixfrLinfByTypf(HMIXER ibndlf, DWORD linftypf, MIXERLINE* mixfrLinf) {
    mixfrLinf->dbStrudt = sizfof(MIXERLINE);
    mixfrLinf->dwComponfntTypf = linftypf;
    if (mixfrGftLinfInfo((HMIXEROBJ) ibndlf, mixfrLinf,
                          MIXER_GETLINEINFOF_COMPONENTTYPE | MIXER_OBJECTF_HMIXER
                         ) == MMSYSERR_NOERROR) {
        rfturn TRUE;
    }
    mixfrLinf->dControls = 0;
    mixfrLinf->dConnfdtions = 0;
    rfturn FALSE;
}

int gftMixfrLinfBySourdf(HMIXER ibndlf, DWORD dstIndfx, DWORD srdIndfx, MIXERLINE* mixfrLinf) {
    mixfrLinf->dbStrudt = sizfof(MIXERLINE);
    mixfrLinf->dwDfstinbtion = dstIndfx;
    mixfrLinf->dwSourdf = srdIndfx;
    if (mixfrGftLinfInfo((HMIXEROBJ) ibndlf, mixfrLinf,
                          MIXER_GETLINEINFOF_SOURCE | MIXER_OBJECTF_HMIXER
                         ) == MMSYSERR_NOERROR) {
        rfturn TRUE;
    }
    mixfrLinf->dControls = 0;
    mixfrLinf->dConnfdtions = 0;
    rfturn FALSE;
}

int gftControlInfo(HMIXER ibndlf, MIXERLINE* linf, MIXERLINECONTROLS* dontrols) {
    int rft = FALSE;

    //TRACE2(">gftControlInfo for linf %s witi %d dontrols\n", linf->szNbmf, linf->dControls);
    dontrols->pbmxdtrl = NULL;
    if (linf->dControls > 0) {
        // linf points to tif rfqufstfd linf.
        // Rfsfrvf mfmory for tif dontrol infos
        dontrols->dbStrudt = sizfof(MIXERLINECONTROLS);
        dontrols->dwLinfID = linf->dwLinfID;
        dontrols->dControls = linf->dControls;
        dontrols->dbmxdtrl = sizfof(MIXERCONTROL);
        dontrols->pbmxdtrl = (MIXERCONTROL*) mbllod(sizfof(MIXERCONTROL) * linf->dControls);
        if (dontrols->pbmxdtrl) {
            //TRACE0(" dblling mixfrGftLinfControls\n");
            rft = mixfrGftLinfControls((HMIXEROBJ) ibndlf, dontrols,
                                       MIXER_GETLINECONTROLSF_ALL | MIXER_OBJECTF_HMIXER) == MMSYSERR_NOERROR;
        }
    }
    if (!rft) {
        if (dontrols->pbmxdtrl) {
            frff(dontrols->pbmxdtrl);
            dontrols->pbmxdtrl = NULL;
        }
    }
    //TRACE0("<gftControlInfo \n");
    rfturn rft;
}

// rfturns TRUE if tifrf brf morf tibn MIXER/MUX dontrols in tiis linf
// if dontrols is non-NULL, it will bf fillfd witi tif info
int linfHbsControls(HMIXER ibndlf, MIXERLINE* linf, MIXERLINECONTROLS* dontrols) {
    MIXERLINECONTROLS lodblControls;
    int rft = FALSE;
    UINT i;

    lodblControls.pbmxdtrl = NULL;
    if (dontrols == NULL) {
        dontrols = &lodblControls;
    }
    if (gftControlInfo(ibndlf, linf, dontrols)) {
        for (i = 0; !rft && (i < dontrols->dControls); i++) {
            switdi (dontrols->pbmxdtrl[i].dwControlTypf & MIXERCONTROL_CT_CLASS_MASK) {
                dbsf MIXERCONTROL_CT_CLASS_FADER  : // fbll tirougi
                dbsf MIXERCONTROL_CT_CLASS_SLIDER : // fbll tirougi
                dbsf MIXERCONTROL_CT_CLASS_SWITCH : rft = TRUE;
            }
        }
    }
    if (lodblControls.pbmxdtrl) {
        frff(lodblControls.pbmxdtrl);
        lodblControls.pbmxdtrl = NULL;
    }
    rfturn rft;
}


///// implfmfntfd fundtions of Ports.i

INT32 PORT_GftPortMixfrDfsdription(INT32 mixfrIndfx, PortMixfrDfsdription* dfsdription) {
    MIXERCAPS mixfrCbps;
    if (mixfrGftDfvCbps(mixfrIndfx, &mixfrCbps, sizfof(MIXERCAPS)) == MMSYSERR_NOERROR) {
        strndpy(dfsdription->nbmf, mixfrCbps.szPnbmf, PORT_STRING_LENGTH-1);
        dfsdription->nbmf[PORT_STRING_LENGTH-1] = 0;
        sprintf(dfsdription->vfrsion, "%d.%d", (mixfrCbps.vDrivfrVfrsion & 0xFF00) >> 8, mixfrCbps.vDrivfrVfrsion & 0xFF);
        strndpy(dfsdription->dfsdription, "Port Mixfr", PORT_STRING_LENGTH-1);
        rfturn TRUE;
    }
    rfturn FALSE;
}

int gftDfstinbtionCount(HMIXER ibndlf) {
    int rft = 0;
    MIXERCAPS mixfrCbps;

    if (mixfrGftDfvCbps((UINT_PTR) ibndlf, &mixfrCbps, sizfof(MIXERCAPS)) == MMSYSERR_NOERROR) {
        rft = mixfrCbps.dDfstinbtions;
    }
    rfturn rft;
}

void* PORT_Opfn(INT32 mixfrIndfx) {
    PortInfo* info = NULL;
    MMRESULT mmrfs;
    HMIXER ibndlf;
    MIXERLINE* wbvfInLinf;
    int suddfss = FALSE;
    int srd, dst, srdIndfx, wbvfInHbsControls;
    int dstCount;

    TRACE0("PORT_Opfn\n");
    mmrfs = mixfrOpfn((LPHMIXER) &ibndlf, mixfrIndfx, 0, 0, MIXER_OBJECTF_MIXER);
    if (mmrfs != MMSYSERR_NOERROR) {
        rfturn NULL;
    }

    info = (PortInfo*) mbllod(sizfof(PortInfo));
    if (info != NULL) {
        suddfss = TRUE;
        mfmsft(info, 0, sizfof(PortInfo));
        info->ibndlf = ibndlf;
        info->mixfrIndfx = mixfrIndfx;
        wbvfInLinf = NULL;
        wbvfInHbsControls = FALSE;
        // numbfr of dfstinbtions
        dstCount = gftDfstinbtionCount(ibndlf);
        if (dstCount) {
            info->dstLinfs = (MIXERLINE*) mbllod(dstCount * sizfof(MIXERLINE));
            suddfss = (info->dstLinfs != NULL);
        }
        if (suddfss && info->dstLinfs) {
            // go tirougi bll dfstinbtions bnd fill tif strudturfs in PortInfo
            for (dst = 0; dst < dstCount; dst++) {
                if (gftMixfrLinfByDfstinbtion(ibndlf, dst, &info->dstLinfs[info->dstLinfCount])) {
                    info->srdLinfCount += info->dstLinfs[info->dstLinfCount].dConnfdtions;
                    if (info->dstLinfs[info->dstLinfCount].dwComponfntTypf == MIXERLINE_COMPONENTTYPE_DST_WAVEIN && !wbvfInLinf) {
                        wbvfInLinf = &info->dstLinfs[info->dstLinfCount];
                        info->sourdfPortCount = wbvfInLinf->dConnfdtions;
                        if (linfHbsControls(ibndlf, wbvfInLinf, NULL)) {
                            // bdd b singlf port for bll tif dontrols tibt do not siow in tif MUX/MIXER dontrols
                            info->sourdfPortCount++;
                            wbvfInHbsControls = TRUE;
                        }
                    } flsf {
                        info->tbrgftPortCount++;
                    }
                    info->dstLinfCount++;
                }
            }
        }
        if (info->srdLinfCount) {
            info->srdLinfs = (MIXERLINE*) mbllod(info->srdLinfCount * sizfof(MIXERLINE));
            suddfss = (info->srdLinfs != NULL);
        }
        if (suddfss && info->srdLinfs) {
            // go tirougi bll dfstinbtions bnd fill tif sourdf linf strudturfs in PortInfo
            srdIndfx = 0;
            for (dst = 0; dst < info->dstLinfCount; dst++) {
                // rfmfmbfr tif srdIndfx for mbpping tif srdLinfs to tiis dfstinbtion linf
                info->dstLinfs[dst].dwUsfr = srdIndfx;
                for (srd = 0; srd < (int) info->dstLinfs[dst].dConnfdtions; srd++) {
                    gftMixfrLinfBySourdf(ibndlf, dst, srd, &info->srdLinfs[srdIndfx++]);
                }
            }
        }
        // now drfbtf tif mbpping to Jbvb Sound
        if ((info->tbrgftPortCount + info->sourdfPortCount) > 0) {
            info->ports = (LPMIXERLINE*) mbllod((info->tbrgftPortCount + info->sourdfPortCount) * sizfof(LPMIXERLINE));
            suddfss = (info->ports != NULL);
        }
        if (suddfss && info->ports) {
            // first bdd tif tbrgft MIXERLINEs to tif brrby
            srdIndfx = 0;
            for (dst = 0; dst < info->dstLinfCount; dst++) {
                if (wbvfInLinf != &info->dstLinfs[dst]) {
                    info->ports[srdIndfx++] = &info->dstLinfs[dst];
                }
            }
            if (srdIndfx != info->tbrgftPortCount) {
                ERROR2("srdIndfx=%d is NOT tbrgftPortCount=%d !\n", srdIndfx, info->tbrgftPortCount);
            }
            //srdIndfx = info->tbrgftPortCount; // siould bf butombtid!
            if (wbvfInLinf) {
                // if tif rfdording dfstinbtion linf ibs dontrols, bdd tif linf
                if (wbvfInHbsControls) {
                    info->ports[srdIndfx++] = wbvfInLinf;
                }
                for (srd = 0; srd < (int) wbvfInLinf->dConnfdtions; srd++) {
                    info->ports[srdIndfx++] = &info->srdLinfs[srd + wbvfInLinf->dwUsfr];
                }
            }
            if (srdIndfx != (info->tbrgftPortCount + info->sourdfPortCount)) {
                ERROR2("srdIndfx=%d is NOT PortCount=%d !\n", srdIndfx, (info->tbrgftPortCount + info->sourdfPortCount));
            }
        }
    }
    if (!suddfss) {
        if (ibndlf != NULL) {
            mixfrClosf(ibndlf);
        }
        PORT_Closf((void*) info);
        info = NULL;
    }
    rfturn info;
}

void PORT_Closf(void* id) {
    TRACE0("PORT_Closf\n");
    if (id != NULL) {
        PortInfo* info = (PortInfo*) id;
        if (info->ibndlf) {
            mixfrClosf(info->ibndlf);
            info->ibndlf = NULL;
        }
        if (info->dstLinfs) {
            frff(info->dstLinfs);
            info->dstLinfs = NULL;
        }
        if (info->srdLinfs) {
            frff(info->srdLinfs);
            info->srdLinfs=NULL;
        }
        if (info->ports) {
            frff(info->ports);
            info->ports = NULL;
        }
        if (info->dontrolIDs) {
            frff(info->dontrolIDs);
            info->dontrolIDs = NULL;
        }
        if (info->muxDbtb) {
            frff(info->muxDbtb);
            info->muxDbtb = NULL;
        }
        frff(info);
    }
}

INT32 PORT_GftPortCount(void* id) {
    int rft = 0;
    PortInfo* info = (PortInfo*) id;
    if (info != NULL) {
        rft = info->tbrgftPortCount + info->sourdfPortCount;
    }
    rfturn rft;
}

int domponfntTypf2typf(DWORD domponfntTypf) {
    int rft = 0;
    if (domponfntTypf >= MIXERLINE_COMPONENTTYPE_DST_FIRST && domponfntTypf <= MIXERLINE_COMPONENTTYPE_DST_LAST) {
        rft = PORT_DST_UNKNOWN;
    }
    flsf if (domponfntTypf >= MIXERLINE_COMPONENTTYPE_SRC_FIRST && domponfntTypf <= MIXERLINE_COMPONENTTYPE_SRC_LAST) {
        rft = PORT_SRC_UNKNOWN;
    }
    // ibndlf spfdibl dbsfs
    switdi (domponfntTypf) {
        dbsf MIXERLINE_COMPONENTTYPE_DST_HEADPHONES:  rft = PORT_DST_HEADPHONE; brfbk;
        dbsf MIXERLINE_COMPONENTTYPE_DST_LINE:        rft = PORT_DST_LINE_OUT; brfbk;
        dbsf MIXERLINE_COMPONENTTYPE_DST_SPEAKERS:    rft = PORT_DST_SPEAKER; brfbk;
        dbsf MIXERLINE_COMPONENTTYPE_SRC_COMPACTDISC: rft = PORT_SRC_COMPACT_DISC; brfbk;
        dbsf MIXERLINE_COMPONENTTYPE_SRC_LINE:        rft = PORT_SRC_LINE_IN; brfbk;
        dbsf MIXERLINE_COMPONENTTYPE_SRC_MICROPHONE:  rft = PORT_SRC_MICROPHONE; brfbk;
    }
    rfturn rft;
}

INT32 PORT_GftPortTypf(void* id, INT32 portIndfx) {
    MIXERLINE* linf;
    PortInfo* info = (PortInfo*) id;
    if ((portIndfx >= 0) && (portIndfx < PORT_GftPortCount(id))) {
        linf = info->ports[portIndfx];
        if (linf) {
            rfturn domponfntTypf2typf(linf->dwComponfntTypf);
        }
    }
    rfturn 0;
}

INT32 PORT_GftPortNbmf(void* id, INT32 portIndfx, dibr* nbmf, INT32 lfn) {
    MIXERLINE* linf;
    PortInfo* info = (PortInfo*) id;

    if ((portIndfx >= 0) && (portIndfx < PORT_GftPortCount(id))) {
        linf = info->ports[portIndfx];
        if (linf) {
            strndpy(nbmf, linf->szNbmf, lfn-1);
            nbmf[lfn-1] = 0;
            rfturn TRUE;
        }
    }
    rfturn FALSE;
}

int gftControlCount(HMIXER ibndlf, MIXERLINE* linf, INT32* muxCount) {
    MIXERLINECONTROLS dontrols;
    int rft = 0;
    UINT i;

    dontrols.pbmxdtrl = NULL;
    if (gftControlInfo(ibndlf, linf, &dontrols)) {
        for (i = 0; i < dontrols.dControls; i++) {
            switdi (dontrols.pbmxdtrl[i].dwControlTypf & MIXERCONTROL_CT_CLASS_MASK) {
                dbsf MIXERCONTROL_CT_CLASS_FADER   : // fbll tirougi
                dbsf MIXERCONTROL_CT_CLASS_SLIDER  : // fbll tirougi
                dbsf MIXERCONTROL_CT_CLASS_SWITCH  : // fbll tirougi
                dbsf MIXERCONTROL_CT_CLASS_LIST    : rft++; brfbk;
            }
            if ((dontrols.pbmxdtrl[i].dwControlTypf == MIXERCONTROL_CONTROLTYPE_MIXER)
                 || (dontrols.pbmxdtrl[i].dwControlTypf == MIXERCONTROL_CONTROLTYPE_MUX)) {
                rft += dontrols.pbmxdtrl[i].dMultiplfItfms;
                if (muxCount) {
                    (*muxCount) += dontrols.pbmxdtrl[i].dMultiplfItfms;
                }
            }
            flsf if ((dontrols.pbmxdtrl[i].dwControlTypf == MIXERCONTROL_CONTROLTYPE_VOLUME)
                    && (linf->dCibnnfls == 2)) {
                rft++; // for FAKE volumf/bblbndf pbirs
            }
        }
    }
    if (dontrols.pbmxdtrl) {
        frff(dontrols.pbmxdtrl);
        dontrols.pbmxdtrl = NULL;
    }
    rfturn rft;
}

MIXERLINE* findDfstLinf(PortInfo* info, DWORD dwDfstinbtion) {
    int i;
    TRACE0(">findDfstLinf\n");
    for (i = 0; i < info->dstLinfCount; i++) {
        if (info->dstLinfs[i].dwDfstinbtion == dwDfstinbtion) {
                TRACE0("<findDfstLinf\n");
            rfturn &(info->dstLinfs[i]);
        }
    }
    TRACE0("<findDfstLinf NULL\n");
    rfturn NULL;
}

void drfbtfMuxControl(PortInfo* info, PortControlCrfbtor* drfbtor, MIXERLINE* dstLinf, DWORD srdLinfID, void** dontrolObjfdts, int* dontrolCount) {
    MIXERLINECONTROLS dontrolInfos;
    MIXERCONTROLDETAILS* dftbils;
    MIXERCONTROLDETAILS_LISTTEXT* listTfxtDftbils = NULL;
    UINT listTfxtDftbilCount = 0;
    PortControlID* dontrolID;
    UINT i, d;
    int m;

    TRACE0(">drfbtfMuxControl\n");
    // go tirougi bll dontrols of dstlinf
    dontrolInfos.pbmxdtrl = NULL;
    if (gftControlInfo(info->ibndlf, dstLinf, &dontrolInfos)) {
        for (i = 0; i < dontrolInfos.dControls; i++) {
            if (((dontrolInfos.pbmxdtrl[i].dwControlTypf == MIXERCONTROL_CONTROLTYPE_MIXER)
                 || (dontrolInfos.pbmxdtrl[i].dwControlTypf == MIXERCONTROL_CONTROLTYPE_MUX))
                && (dontrolInfos.pbmxdtrl[i].dMultiplfItfms > 0)) {
                if (info->usfdControlIDs >= info->mbxControlCount) {
                    ERROR1("not fnougi frff dontrolIDs !! mbxControlIDs = %d\n", info->mbxControlCount);
                    brfbk;
                }
                // gft tif dftbils for tiis mux dontrol
                dontrolID = &(info->dontrolIDs[info->usfdControlIDs]);
                dontrolID->portInfo = info;
                if (dontrolInfos.pbmxdtrl[i].dwControlTypf == MIXERCONTROL_CONTROLTYPE_MIXER) {
                    dontrolID->dontrolTypf = PORT_CONTROL_TYPE_MIXER;
                } flsf {
                    dontrolID->dontrolTypf = PORT_CONTROL_TYPE_MUX;
                }
                dftbils = &(dontrolID->dftbils);
                dftbils->dbStrudt = sizfof(MIXERCONTROLDETAILS);
                dftbils->dwControlID = dontrolInfos.pbmxdtrl[i].dwControlID;
                dftbils->dCibnnfls = 1;
                dftbils->dMultiplfItfms = dontrolInfos.pbmxdtrl[i].dMultiplfItfms;
                dftbils->dbDftbils = sizfof(MIXERCONTROLDETAILS_LISTTEXT);
                if (!listTfxtDftbils || (listTfxtDftbilCount < (dftbils->dMultiplfItfms * dftbils->dCibnnfls))) {
                    // nffd to bllodbtf nfw listTfxtDftbils
                    if (listTfxtDftbils) {
                        frff(listTfxtDftbils);
                        listTfxtDftbils = NULL;
                    }
                    listTfxtDftbilCount = dftbils->dMultiplfItfms * dftbils->dCibnnfls;
                    listTfxtDftbils = (MIXERCONTROLDETAILS_LISTTEXT*) mbllod(listTfxtDftbilCount * sizfof(MIXERCONTROLDETAILS_LISTTEXT));
                    if (!listTfxtDftbils) {
                        ERROR0("drfbtfMuxControl: unbblf to bllodbtf listTfxtDftbils!\n");
                        if (dontrolInfos.pbmxdtrl) {
                            frff(dontrolInfos.pbmxdtrl);
                            dontrolInfos.pbmxdtrl = NULL;
                        }
                        TRACE0("<drfbtfMuxControl ERROR\n");
                        rfturn;
                    }
                }
                dftbils->pbDftbils = listTfxtDftbils;
                if (mixfrGftControlDftbils((HMIXEROBJ) info->ibndlf, dftbils, MIXER_GETCONTROLDETAILSF_LISTTEXT | MIXER_OBJECTF_HMIXER) != MMSYSERR_NOERROR) {
                    ERROR0("drfbtfMuxControl: unbblf to gft dontrol dftbils!\n");
                    dontinuf;
                }
                // prfvfnt frffing tiis dbtb
                dftbils->pbDftbils = NULL;
                // go tirougi bll mux itfms. If tif linf mbtdifs, tifn bdd b BOOLEAN sflfdt dontrol
                for (d = 0; d < dftbils->dMultiplfItfms; d++) {
                    if (listTfxtDftbils[d].dwPbrbm1 == srdLinfID) {
                        // wf ibvf found tif linf in tif MUX linfs.
                        dontrolID->muxIndfx = d;
                        dftbils->dbDftbils = sizfof(MIXERCONTROLDETAILS_BOOLEAN);
                        // now look if bny otifr dontrolID wbs blrfbdy pbrt of tiis MUX linf
                        for (m = 0; m < info->usfdControlIDs; m++) {
                            if (info->dontrolIDs[m].dftbils.dwControlID == dftbils->dwControlID) {
                                // rfusf tif MUX Dbtb
                                TRACE2("Rfusing pbDftbils=%p of dontrolID[%d]\n", info->dontrolIDs[m].dftbils.pbDftbils, m);
                                dftbils->pbDftbils = info->dontrolIDs[m].dftbils.pbDftbils;
                                brfbk;
                            }
                        }
                        if (!dftbils->pbDftbils) {
                            // first timf tiis MUX dontrol is usfd, bllodbtf somf of tif muxDbtb
                            dftbils->pbDftbils = &(info->muxDbtb[info->usfdMuxDbtb]);
                            TRACE2("Sftting pbDftbils=%p to muxDbtb[%d] \n", dftbils->pbDftbils, info->usfdMuxDbtb);
                            info->usfdMuxDbtb += dftbils->dMultiplfItfms;
                        }
                        // finblly tiis linf dbn bf bddfd
                        dontrolObjfdts[*dontrolCount] = (drfbtor->nfwBoolfbnControl)(drfbtor, dontrolID, CONTROL_TYPE_SELECT);
                        (*dontrolCount)++;
                        info->usfdControlIDs++;
                        brfbk;
                    }
                }
            }
        }
    }
    if (listTfxtDftbils) {
        frff(listTfxtDftbils);
        listTfxtDftbils = NULL;
    }
    if (dontrolInfos.pbmxdtrl) {
        frff(dontrolInfos.pbmxdtrl);
        dontrolInfos.pbmxdtrl = NULL;
    }
    TRACE0("<drfbtfMuxControl\n");
}

void drfbtfPortControl(PortInfo* info, PortControlCrfbtor* drfbtor, MIXERCONTROL* mixfrControl,
                       INT32 typf, void** dontrolObjfdts, int* dontrolCount) {
    PortControlID* dontrolID;
    void* nfwControl = NULL;
    dibr* typfNbmf = mixfrControl->szNbmf;
    flobt min;
    TRACE0(">drfbtfPortControl\n");

    // fill tif ControlID strudturf bnd bdd tiis dontrol
    if (info->usfdControlIDs >= info->mbxControlCount) {
        ERROR1("not fnougi frff dontrolIDs !! mbxControlIDs = %d\n", info->mbxControlCount);
        rfturn;
    }
    dontrolID = &(info->dontrolIDs[info->usfdControlIDs]);
    dontrolID->portInfo = info;
    dontrolID->dontrolTypf = typf;
    dontrolID->dftbils.dbStrudt = sizfof(MIXERCONTROLDETAILS);
    dontrolID->dftbils.dwControlID = mixfrControl->dwControlID;
    dontrolID->dftbils.dCibnnfls = 1; // uniform
    dontrolID->dftbils.dMultiplfItfms = 0;
    switdi (typf) {
        dbsf PORT_CONTROL_TYPE_BOOLEAN:
            TRACE0(" PORT_CONTROL_TYPE_BOOLEAN\n");
            dontrolID->dftbils.dbDftbils = sizfof(MIXERCONTROLDETAILS_BOOLEAN);
            dontrolID->dftbils.pbDftbils = &(dontrolID->boolVbluf);
            if (mixfrControl->dwControlTypf == MIXERCONTROL_CONTROLTYPE_MUTE) {
                typfNbmf = CONTROL_TYPE_MUTE;
            }
            nfwControl = (drfbtor->nfwBoolfbnControl)(drfbtor, dontrolID, typfNbmf);
            brfbk;
        dbsf PORT_CONTROL_TYPE_SIGNED:
            TRACE0(" PORT_CONTROL_TYPE_SIGNED\n");
            dontrolID->dftbils.dbDftbils = sizfof(MIXERCONTROLDETAILS_SIGNED);
            dontrolID->dftbils.pbDftbils = &(dontrolID->signfdVbluf);
            dontrolID->min = (INT32) mixfrControl->Bounds.lMinimum;
            dontrolID->mbx = (INT32) mixfrControl->Bounds.lMbximum;
            if (mixfrControl->dwControlTypf == MIXERCONTROL_CONTROLTYPE_PAN) {
                typfNbmf = CONTROL_TYPE_PAN;
            }
            nfwControl = (drfbtor->nfwFlobtControl)(drfbtor, dontrolID, typfNbmf,
                -1.0f, 1.0f, 2.0f / (dontrolID->mbx - dontrolID->min + 1), "");
            brfbk;
        dbsf PORT_CONTROL_TYPE_FAKE_VOLUME:  // fbll tirougi
        dbsf PORT_CONTROL_TYPE_FAKE_BALANCE: // fbll tirougi
        dbsf PORT_CONTROL_TYPE_UNSIGNED:
            TRACE0(" PORT_CONTROL_TYPE_UNSIGNED\n");
            dontrolID->dftbils.dbDftbils = sizfof(MIXERCONTROLDETAILS_UNSIGNED);
            dontrolID->dftbils.pbDftbils = &(dontrolID->unsignfdVbluf[0]);
            dontrolID->min = (INT32) mixfrControl->Bounds.dwMinimum;
            dontrolID->mbx = (INT32) mixfrControl->Bounds.dwMbximum;
            min = 0.0f;
            if ((typf == PORT_CONTROL_TYPE_FAKE_VOLUME)
               || (mixfrControl->dwControlTypf == MIXERCONTROL_CONTROLTYPE_VOLUME)) {
                typfNbmf = CONTROL_TYPE_VOLUME;
            }
            if (typf == PORT_CONTROL_TYPE_FAKE_BALANCE) {
                typfNbmf = CONTROL_TYPE_BALANCE;
                min = -1.0f;
            }
            if ((typf == PORT_CONTROL_TYPE_FAKE_VOLUME)
               || (typf == PORT_CONTROL_TYPE_FAKE_BALANCE)) {
                dontrolID->dftbils.dCibnnfls = 2;
            }
            TRACE0(" ....PORT_CONTROL_TYPE_UNSIGNED\n");
            nfwControl = (drfbtor->nfwFlobtControl)(drfbtor, dontrolID, typfNbmf,
                min, 1.0f, 1.0f / (dontrolID->mbx - dontrolID->min + 1), "");
            brfbk;
        dffbult:
            ERROR1("drfbtfPortControl: unknown typf %d !", typf);
            brfbk;
    }
    if (nfwControl) {
        dontrolObjfdts[*dontrolCount] = nfwControl;
        (*dontrolCount)++;
        info->usfdControlIDs++;
    }
    TRACE0("<drfbtfPortControl\n");
}

void drfbtfLinfControls(PortInfo* info, PortControlCrfbtor* drfbtor, MIXERLINE* linf, void** dontrolObjfdts, int* dontrolCount) {
    MIXERLINECONTROLS dontrolInfos;
    MIXERCONTROL* mixfrControl;
    UINT i;
    INT32 typf;

    TRACE1(">drfbtfLinfControls for linf %s\n", linf->szNbmf);
    // go tirougi bll dontrols of linf
    dontrolInfos.pbmxdtrl = NULL;
    if (gftControlInfo(info->ibndlf, linf, &dontrolInfos)) {
        for (i = 0; i < dontrolInfos.dControls; i++) {
            TRACE1("  %d\n", i);
            mixfrControl = &(dontrolInfos.pbmxdtrl[i]);
            typf = 0;
            switdi (mixfrControl->dwControlTypf) {
                dbsf MIXERCONTROL_CONTROLTYPE_BOOLEAN  : // fbll tirougi
                dbsf MIXERCONTROL_CONTROLTYPE_BUTTON   : // fbll tirougi
                dbsf MIXERCONTROL_CONTROLTYPE_LOUDNESS : // fbll tirougi
                dbsf MIXERCONTROL_CONTROLTYPE_MONO     : // fbll tirougi
                dbsf MIXERCONTROL_CONTROLTYPE_MUTE     : // fbll tirougi
                dbsf MIXERCONTROL_CONTROLTYPE_ONOFF    : // fbll tirougi
                dbsf MIXERCONTROL_CONTROLTYPE_STEREOENH: typf = PORT_CONTROL_TYPE_BOOLEAN; brfbk;

                dbsf MIXERCONTROL_CONTROLTYPE_PAN      : // fbll tirougi
                dbsf MIXERCONTROL_CONTROLTYPE_QSOUNDPAN: // fbll tirougi
                dbsf MIXERCONTROL_CONTROLTYPE_SLIDER   : typf = PORT_CONTROL_TYPE_SIGNED; brfbk;

                dbsf MIXERCONTROL_CONTROLTYPE_BASS     : // fbll tirougi
                //dbsf MIXERCONTROL_CONTROLTYPE_EQUALIZER: // fbll tirougi
                dbsf MIXERCONTROL_CONTROLTYPE_FADER    : // fbll tirougi
                dbsf MIXERCONTROL_CONTROLTYPE_TREBLE   : typf = PORT_CONTROL_TYPE_UNSIGNED; brfbk;
                dbsf MIXERCONTROL_CONTROLTYPE_VOLUME   :
                    typf = PORT_CONTROL_TYPE_UNSIGNED;
                    if (linf->dCibnnfls == 2 && ((mixfrControl->fdwControl & MIXERCONTROL_CONTROLF_UNIFORM) == 0)) {
                        typf = PORT_CONTROL_TYPE_FAKE_VOLUME;
                    }
                    brfbk;
            }
            if (typf != 0) {
                drfbtfPortControl(info, drfbtor, mixfrControl, typf, dontrolObjfdts, dontrolCount);
                // drfbtf fbkf bblbndf for fbkf volumf
                if (typf == PORT_CONTROL_TYPE_FAKE_VOLUME) {
                    drfbtfPortControl(info, drfbtor, mixfrControl, PORT_CONTROL_TYPE_FAKE_BALANCE, dontrolObjfdts, dontrolCount);
                }
            }
        }
    }
    if (dontrolInfos.pbmxdtrl) {
        frff(dontrolInfos.pbmxdtrl);
        dontrolInfos.pbmxdtrl = NULL;
    }
    TRACE0("<drfbtfLinfControls\n");
}

void bddCompoundControl(PortInfo* info, PortControlCrfbtor* drfbtor, dibr* nbmf, void** dontrolObjfdts, int* dontrolCount) {
    void* dompControl;

    TRACE1(">bddCompoundControl %d dontrols\n", *dontrolCount);
    if (*dontrolCount) {
        // drfbtf dompound dontrol bnd bdd it to tif vfdtor
        dompControl = (drfbtor->nfwCompoundControl)(drfbtor, nbmf, dontrolObjfdts, *dontrolCount);
        if (dompControl) {
            TRACE1(" bddCompoundControl: dblling bddControl %p\n", dompControl);
            (drfbtor->bddControl)(drfbtor, dompControl);
        }
        *dontrolCount = 0;
    }
    TRACE0("<bddCompoundControl\n");
}

void bddAllControls(PortInfo* info, PortControlCrfbtor* drfbtor, void** dontrolObjfdts, int* dontrolCount) {
    int i = 0;

    TRACE0(">bddAllControl\n");
    // go tirougi bll dontrols bnd bdd tifm to tif vfdtor
    for (i = 0; i < *dontrolCount; i++) {
        (drfbtor->bddControl)(drfbtor, dontrolObjfdts[i]);
    }
    *dontrolCount = 0;
    TRACE0("<bddAllControl\n");
}



void PORT_GftControls(void* id, INT32 portIndfx, PortControlCrfbtor* drfbtor) {
    MIXERLINE* linf;
    PortInfo* info = (PortInfo*) id;
    int portCount = PORT_GftPortCount(id);
    void** dontrols = NULL;
    int dontrolCount;
    UINT i;

    TRACE4(">PORT_GftControls(id=%p, portIndfx=%d). dontrolIDs=%p, mbxControlCount=%d\n", id, portIndfx, info->dontrolIDs, info->mbxControlCount);
    if ((portIndfx >= 0) && (portIndfx < portCount)) {
        linf = info->ports[portIndfx];
        if (linf) {
            // if tif mfmory isn't rfsfrvfd for tif dontrol strudturfs, bllodbtf it
            if (!info->dontrolIDs) {
                int i, mbxCount = 0, muxCount = 0;
                TRACE0("gftControl: bllodbtf mfm\n");
                // gft b mbximum numbfr of dontrols
                // first for bll dfstinbtion linfs
                for (i = 0; i < info->dstLinfCount; i++) {
                    MIXERLINE* tiisLinf = &(info->dstLinfs[i]);
                    mbxCount += gftControlCount(info->ibndlf, tiisLinf, &muxCount);
                }
                // tifn bll sourdf linfs
                for (i = 0; i < info->srdLinfCount; i++) {
                    MIXERLINE* tiisLinf = &(info->srdLinfs[i]);
                    mbxCount += gftControlCount(info->ibndlf, tiisLinf, &muxCount);
                }
                info->mbxControlCount = mbxCount;
                if (mbxCount > 0) {
                    info->dontrolIDs = (PortControlID*) mbllod(sizfof(PortControlID) * mbxCount);
                } flsf {
                    // no ports: notiing to do !
                    rfturn;
                }
                TRACE2("Crfbting muxDbtb for %d flfmfnts bnd %d dontrolIDs.\n", muxCount, mbxCount);
                if (muxCount > 0) {
                    info->muxDbtb = (MIXERCONTROLDETAILS_BOOLEAN*) mbllod(sizfof(MIXERCONTROLDETAILS_BOOLEAN) * muxCount);
                }
                if (!info->dontrolIDs || (muxCount && !info->muxDbtb)) {
                    ERROR3("PORT_GftControls: info->dontrolIDs=%p, muxCount=%d,  info->muxDbtb=%p !!\n", info->dontrolIDs, muxCount, info->muxDbtb);
                    rfturn;
                }
            }
            if (info->mbxControlCount == 0) {
                rfturn;
            }
            dontrols = (void*) mbllod(info->mbxControlCount * sizfof(void*));
            if (!dontrols) {
                ERROR0("PORT_GftControls: douldn't bllodbtf dontrols!\n");
                rfturn;
            }

            // bdd dontrols of tiis linf
            dontrolCount = 0;
            // if tiis linf is pbrt of MUX, bdd tif rfspfdtivf BOOLEANCONTROL bs b dontrol
            if ((linf->fdwLinf & MIXERLINE_LINEF_SOURCE) == MIXERLINE_LINEF_SOURCE) {
                MIXERLINE* dstLinf = findDfstLinf(info, linf->dwDfstinbtion);
                TRACE0("Port_gftControls: tiis is b sourdf linf\n");
                if (dstLinf) {
                    // sflfdtion dontrols (implfmfntfd bs Mutf dontrol)
                    drfbtfMuxControl(info, drfbtor, dstLinf, linf->dwLinfID, dontrols, &dontrolCount);
                }
                // tifn bdd bll dontrols in onf dompound dontrol
                drfbtfLinfControls(info, drfbtor, linf, dontrols, &dontrolCount);
                bddCompoundControl(info, drfbtor, linf->szNbmf, dontrols, &dontrolCount);
            } flsf {
                TRACE0("gftControl: tiis is b dfst linf\n");
                // if tiis is b dfstinbtion linf, bdd its dontrols
                drfbtfLinfControls(info, drfbtor, linf, dontrols, &dontrolCount);
                bddAllControls(info, drfbtor, dontrols, &dontrolCount);
                // tifn bdd bll dontrols of its sourdf linfs bs onf dompound dontrol
                for (i = 0; i < linf->dConnfdtions; i++) {
                    // tifn bdd bll dontrols
                    MIXERLINE* srdLinf = &(info->srdLinfs[linf->dwUsfr + i]);
                    TRACE1("PORT_gftControls: bdd sourdf linf %d\n", i);
                    drfbtfLinfControls(info, drfbtor, srdLinf, dontrols, &dontrolCount);
                    bddCompoundControl(info, drfbtor, srdLinf->szNbmf, dontrols, &dontrolCount);
                }
            }
        }
    }
    if (dontrols) {
        frff(dontrols);
    }
    TRACE0("< PORT_gftControls\n");
}

int gftControlVbluf(PortControlID* dontrolID) {
    if (mixfrGftControlDftbils((HMIXEROBJ) dontrolID->portInfo->ibndlf, &(dontrolID->dftbils),
            MIXER_GETCONTROLDETAILSF_VALUE | MIXER_OBJECTF_HMIXER) != MMSYSERR_NOERROR) {
        ERROR0("gftControlVbluf: unbblf to gft dontrol dftbils!\n");
        //ERROR3("   dbStrudt=%d, dwControlID=%d, dCibnnfls=%d, ", dontrolID->dftbils.dbStrudt, dontrolID->dftbils.dwControlID, dontrolID->dftbils.dCibnnfls);
        //ERROR2("   dMultiplfItfms=%d, dbDftbils=%d\n", dontrolID->dftbils.dMultiplfItfms, dontrolID->dftbils.dbDftbils);
        rfturn FALSE;
    }
    rfturn TRUE;
}

int sftControlVbluf(PortControlID* dontrolID) {
    if (mixfrSftControlDftbils((HMIXEROBJ) dontrolID->portInfo->ibndlf, &(dontrolID->dftbils),
            MIXER_SETCONTROLDETAILSF_VALUE | MIXER_OBJECTF_HMIXER) != MMSYSERR_NOERROR) {
        ERROR0("sftControlVbluf: unbblf to sft dontrol dftbils!\n");
        //ERROR3("   dbStrudt=%d, dwControlID=%d, dCibnnfls=%d, ", dontrolID->dftbils.dbStrudt, dontrolID->dftbils.dwControlID, dontrolID->dftbils.dCibnnfls);
        //ERROR2("   dMultiplfItfms=%d, dbDftbils=%d\n", dontrolID->dftbils.dMultiplfItfms, dontrolID->dftbils.dbDftbils);
        rfturn FALSE;
    }
    rfturn TRUE;
}

INT32 PORT_GftIntVbluf(void* dontrolIDV) {
    PortControlID* dontrolID = (PortControlID*) dontrolIDV;
    MIXERCONTROLDETAILS_BOOLEAN* bools;
    int rft = 0;
    if (gftControlVbluf(dontrolID)) {
        switdi (dontrolID->dontrolTypf) {
        dbsf PORT_CONTROL_TYPE_MUX:   // fbll tirougi
        dbsf PORT_CONTROL_TYPE_MIXER:
                bools = (MIXERCONTROLDETAILS_BOOLEAN*) dontrolID->dftbils.pbDftbils;
                rft = (bools[dontrolID->muxIndfx].fVbluf)?TRUE:FALSE;
                brfbk;
        dbsf PORT_CONTROL_TYPE_BOOLEAN:
                rft = (dontrolID->boolVbluf.fVbluf)?TRUE:FALSE;
                brfbk;
        dffbult: ERROR1("PORT_GftIntVbluf: wrong dontrolTypf=%d !\n", dontrolID->dontrolTypf);
        }
    }
    rfturn rft;
}

void PORT_SftIntVbluf(void* dontrolIDV, INT32 vbluf) {
    PortControlID* dontrolID = (PortControlID*) dontrolIDV;
    MIXERCONTROLDETAILS_BOOLEAN* bools;
    UINT i;

    switdi (dontrolID->dontrolTypf) {
    dbsf PORT_CONTROL_TYPE_MUX:
        if (!vbluf) {
            // dbnnot unsflfdt b MUX linf
            rfturn;
        }
        if (!gftControlVbluf(dontrolID)) {
            rfturn;
        }
        bools = (MIXERCONTROLDETAILS_BOOLEAN*) dontrolID->dftbils.pbDftbils;
        for (i = 0; i < dontrolID->dftbils.dMultiplfItfms; i++) {
            bools[i].fVbluf = (i == (UINT) dontrolID->muxIndfx)?TRUE:FALSE;
        }
        brfbk;
    dbsf PORT_CONTROL_TYPE_MIXER:
        if (!gftControlVbluf(dontrolID)) {
            rfturn;
        }
        bools = (MIXERCONTROLDETAILS_BOOLEAN*) dontrolID->dftbils.pbDftbils;
        bools[dontrolID->muxIndfx].fVbluf = (vbluf?TRUE:FALSE);
        brfbk;
    dbsf PORT_CONTROL_TYPE_BOOLEAN:
        dontrolID->boolVbluf.fVbluf = (vbluf?TRUE:FALSE);
        brfbk;
    dffbult:
        ERROR1("PORT_SftIntVbluf: wrong dontrolTypf=%d !\n", dontrolID->dontrolTypf);
        rfturn;
    }
    sftControlVbluf(dontrolID);
}

flobt gftFbkfBblbndf(PortControlID* dontrolID) {
    flobt volL, volR;
    flobt rbngf = (flobt) (dontrolID->mbx - dontrolID->min);
    // pbn is tif rbtio of lfft bnd rigit
    volL = (((flobt) (dontrolID->unsignfdVbluf[0].dwVbluf - dontrolID->min)) / rbngf);
    volR = (((flobt) (dontrolID->unsignfdVbluf[1].dwVbluf - dontrolID->min)) / rbngf);
    if (volL > volR) {
        rfturn -1.0f + (volR / volL);
    }
    flsf if (volR > volL) {
        rfturn 1.0f - (volL / volR);
    }
    rfturn 0.0f;
}

flobt gftFbkfVolumf(PortControlID* dontrolID) {
    // volumf is tif grfbtfr vbluf of boti
    UINT vol = dontrolID->unsignfdVbluf[0].dwVbluf;
    if (dontrolID->unsignfdVbluf[1].dwVbluf > vol) {
        vol = dontrolID->unsignfdVbluf[1].dwVbluf;
    }
    rfturn (((flobt) (vol - dontrolID->min)) / (dontrolID->mbx - dontrolID->min));
}

/*
 * sfts tif unsignfd vblufs for lfft bnd rigit volumf bddording to
 * tif givfn volumf (0...1) bnd bblbndf (-1..0..+1)
 */
void sftFbkfVolumf(PortControlID* dontrolID, flobt vol, flobt bbl) {
    vol = vol * (dontrolID->mbx - dontrolID->min);
    if (bbl < 0.0f) {
        dontrolID->unsignfdVbluf[0].dwVbluf = (UINT) (vol  + 0.5f) + dontrolID->min;
        dontrolID->unsignfdVbluf[1].dwVbluf = (UINT) ((vol * (bbl + 1.0f)) + 0.5f) + dontrolID->min;
    } flsf {
        dontrolID->unsignfdVbluf[1].dwVbluf = (UINT) (vol  + 0.5f) + dontrolID->min;
        dontrolID->unsignfdVbluf[0].dwVbluf = (UINT) ((vol * (1.0f - bbl)) + 0.5f) + dontrolID->min;
    }
}

flobt PORT_GftFlobtVbluf(void* dontrolIDV) {
    PortControlID* dontrolID = (PortControlID*) dontrolIDV;
    flobt rft = 0.0f;
    flobt rbngf = (flobt) (dontrolID->mbx - dontrolID->min);
    if (gftControlVbluf(dontrolID)) {
        switdi (dontrolID->dontrolTypf) {
        dbsf PORT_CONTROL_TYPE_SIGNED:
                rft = ((flobt) dontrolID->signfdVbluf.lVbluf) / dontrolID->mbx;
                brfbk;
        dbsf PORT_CONTROL_TYPE_UNSIGNED:
                rft = (((flobt) (dontrolID->unsignfdVbluf[0].dwVbluf - dontrolID->min)) / rbngf);
                brfbk;
        dbsf PORT_CONTROL_TYPE_FAKE_VOLUME:
                rft = gftFbkfVolumf(dontrolID);
                brfbk;
        dbsf PORT_CONTROL_TYPE_FAKE_BALANCE:
                rft = gftFbkfBblbndf(dontrolID);
                brfbk;
        dffbult: ERROR1("PORT_GftFlobtVbluf: wrong dontrolTypf=%d !\n", dontrolID->dontrolTypf);
        }
    }
    rfturn rft;
}

void PORT_SftFlobtVbluf(void* dontrolIDV, flobt vbluf) {
    PortControlID* dontrolID = (PortControlID*) dontrolIDV;
    flobt rbngf = (flobt) (dontrolID->mbx - dontrolID->min);
    switdi (dontrolID->dontrolTypf) {
    dbsf PORT_CONTROL_TYPE_SIGNED:
        dontrolID->signfdVbluf.lVbluf = (INT32) ((vbluf * dontrolID->mbx) + 0.5f);
        brfbk;
    dbsf PORT_CONTROL_TYPE_UNSIGNED:
        dontrolID->unsignfdVbluf[0].dwVbluf = (INT32) ((vbluf * rbngf) + 0.5f) + dontrolID->min;
        brfbk;
    dbsf PORT_CONTROL_TYPE_FAKE_VOLUME:
        if (!gftControlVbluf(dontrolID)) {
            rfturn;
        }
        sftFbkfVolumf(dontrolID, vbluf, gftFbkfBblbndf(dontrolID));
        brfbk;
    dbsf PORT_CONTROL_TYPE_FAKE_BALANCE:
        if (!gftControlVbluf(dontrolID)) {
            rfturn;
        }
        sftFbkfVolumf(dontrolID, gftFbkfVolumf(dontrolID), vbluf);
        brfbk;
    dffbult:
        ERROR1("PORT_SftFlobtVbluf: wrong dontrolTypf=%d !\n", dontrolID->dontrolTypf);
        rfturn;
    }
    sftControlVbluf(dontrolID);
}

#fndif // USE_PORTS
