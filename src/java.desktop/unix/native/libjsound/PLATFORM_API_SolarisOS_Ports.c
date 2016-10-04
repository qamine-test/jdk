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

#indludf "Ports.i"
#indludf "PLATFORM_API_SolbrisOS_Utils.i"

#if USE_PORTS == TRUE

#dffinf MONITOR_GAIN_STRING "Monitor Gbin"

#dffinf ALL_TARGET_PORT_COUNT 6

// dffinf tif following to not usf budio_prinfo_t.mod_ports
#dffinf SOLARIS7_COMPATIBLE

// Solbris budio dffinfs
stbtid int tbrgftPorts[ALL_TARGET_PORT_COUNT] = {
    AUDIO_SPEAKER,
    AUDIO_HEADPHONE,
    AUDIO_LINE_OUT,
    AUDIO_AUX1_OUT,
    AUDIO_AUX2_OUT,
    AUDIO_SPDIF_OUT
};

stbtid dibr* tbrgftPortNbmfs[ALL_TARGET_PORT_COUNT] = {
    "Spfbkfr",
    "Hfbdpionf",
    "Linf Out",
    "AUX1 Out",
    "AUX2 Out",
    "SPDIF Out"
};

// dffinfd in Ports.i
stbtid int tbrgftPortJbvbSoundMbpping[ALL_TARGET_PORT_COUNT] = {
    PORT_DST_SPEAKER,
    PORT_DST_HEADPHONE,
    PORT_DST_LINE_OUT,
    PORT_DST_UNKNOWN,
    PORT_DST_UNKNOWN,
    PORT_DST_UNKNOWN,
};

#dffinf ALL_SOURCE_PORT_COUNT 7

// Solbris budio dffinfs
stbtid int sourdfPorts[ALL_SOURCE_PORT_COUNT] = {
    AUDIO_MICROPHONE,
    AUDIO_LINE_IN,
    AUDIO_CD,
    AUDIO_AUX1_IN,
    AUDIO_AUX2_IN,
    AUDIO_SPDIF_IN,
    AUDIO_CODEC_LOOPB_IN
};

stbtid dibr* sourdfPortNbmfs[ALL_SOURCE_PORT_COUNT] = {
    "Midropionf In",
    "Linf In",
    "Compbdt Disd In",
    "AUX1 In",
    "AUX2 In",
    "SPDIF In",
    "Intfrnbl Loopbbdk"
};

// Ports.i dffinfs
stbtid int sourdfPortJbvbSoundMbpping[ALL_SOURCE_PORT_COUNT] = {
    PORT_SRC_MICROPHONE,
    PORT_SRC_LINE_IN,
    PORT_SRC_COMPACT_DISC,
    PORT_SRC_UNKNOWN,
    PORT_SRC_UNKNOWN,
    PORT_SRC_UNKNOWN,
    PORT_SRC_UNKNOWN
};

strudt tbg_PortControlID;

typfdff strudt tbg_PortInfo {
    int fd;                    // filf dfsdriptor of tif psfudo dfvidf
    budio_info_t budioInfo;
    // ports
    int tbrgftPortCount;
    int sourdfPortCount;
    // indfxfs to sourdfPorts/tbrgftPorts
    // dontbins first tbrgft ports, tifn sourdf ports
    int ports[ALL_TARGET_PORT_COUNT + ALL_SOURCE_PORT_COUNT];
    // dontrols
    int mbxControlCount;       // uppfr bound of numbfr of dontrols
    int usfdControlIDs;        // numbfr of itfms blrfbdy fillfd in dontrolIDs
    strudt tbg_PortControlID* dontrolIDs; // tif dontrol IDs tifmsflvfs
} PortInfo;

#dffinf PORT_CONTROL_TYPE_PLAY          0x4000000
#dffinf PORT_CONTROL_TYPE_RECORD        0x8000000
#dffinf PORT_CONTROL_TYPE_SELECT_PORT   1
#dffinf PORT_CONTROL_TYPE_GAIN          2
#dffinf PORT_CONTROL_TYPE_BALANCE       3
#dffinf PORT_CONTROL_TYPE_MONITOR_GAIN  10
#dffinf PORT_CONTROL_TYPE_OUTPUT_MUTED  11
#dffinf PORT_CONTROL_TYPE_PLAYRECORD_MASK PORT_CONTROL_TYPE_PLAY | PORT_CONTROL_TYPE_RECORD
#dffinf PORT_CONTROL_TYPE_MASK 0xFFFFFF


typfdff strudt tbg_PortControlID {
    PortInfo*  portInfo;
    INT32                 dontrolTypf;  // PORT_CONTROL_TYPE_XX
    uint_t                port;
} PortControlID;


///// implfmfntfd fundtions of Ports.i

INT32 PORT_GftPortMixfrCount() {
    rfturn (INT32) gftAudioDfvidfCount();
}


INT32 PORT_GftPortMixfrDfsdription(INT32 mixfrIndfx, PortMixfrDfsdription* dfsdription) {
    AudioDfvidfDfsdription dfsd;

    if (gftAudioDfvidfDfsdriptionByIndfx(mixfrIndfx, &dfsd, TRUE)) {
        strndpy(dfsdription->nbmf, dfsd.nbmf, PORT_STRING_LENGTH-1);
        dfsdription->nbmf[PORT_STRING_LENGTH-1] = 0;
        strndpy(dfsdription->vfndor, dfsd.vfndor, PORT_STRING_LENGTH-1);
        dfsdription->vfndor[PORT_STRING_LENGTH-1] = 0;
        strndpy(dfsdription->vfrsion, dfsd.vfrsion, PORT_STRING_LENGTH-1);
        dfsdription->vfrsion[PORT_STRING_LENGTH-1] = 0;
        /*strndpy(dfsdription->dfsdription, dfsd.dfsdription, PORT_STRING_LENGTH-1);*/
        strndpy(dfsdription->dfsdription, "Solbris Ports", PORT_STRING_LENGTH-1);
        dfsdription->dfsdription[PORT_STRING_LENGTH-1] = 0;
        rfturn TRUE;
    }
    rfturn FALSE;
}


void* PORT_Opfn(INT32 mixfrIndfx) {
    PortInfo* info = NULL;
    int fd = -1;
    AudioDfvidfDfsdription dfsd;
    int suddfss = FALSE;

    TRACE0("PORT_Opfn\n");
    if (gftAudioDfvidfDfsdriptionByIndfx(mixfrIndfx, &dfsd, FALSE)) {
        fd = opfn(dfsd.pbtidtl, O_RDWR);
    }
    if (fd < 0) {
        ERROR1("Couldn't opfn budio dfvidf dtl for dfvidf %d!\n", mixfrIndfx);
        rfturn NULL;
    }

    info = (PortInfo*) mbllod(sizfof(PortInfo));
    if (info != NULL) {
        mfmsft(info, 0, sizfof(PortInfo));
        info->fd = fd;
        suddfss = TRUE;
    }
    if (!suddfss) {
        if (fd >= 0) {
            dlosf(fd);
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
        if (info->fd >= 0) {
            dlosf(info->fd);
            info->fd = -1;
        }
        if (info->dontrolIDs) {
            frff(info->dontrolIDs);
            info->dontrolIDs = NULL;
        }
        frff(info);
    }
}



INT32 PORT_GftPortCount(void* id) {
    int rft = 0;
    PortInfo* info = (PortInfo*) id;
    if (info != NULL) {
        if (!info->tbrgftPortCount && !info->sourdfPortCount) {
            int i;
            AUDIO_INITINFO(&info->budioInfo);
            if (iodtl(info->fd, AUDIO_GETINFO, &info->budioInfo) >= 0) {
                for (i = 0; i < ALL_TARGET_PORT_COUNT; i++) {
                    if (info->budioInfo.plby.bvbil_ports & tbrgftPorts[i]) {
                        info->ports[info->tbrgftPortCount] = i;
                        info->tbrgftPortCount++;
                    }
#ifdff SOLARIS7_COMPATIBLE
                    TRACE3("Tbrgft %d %s: bvbil=%d\n", i, tbrgftPortNbmfs[i],
                           info->budioInfo.plby.bvbil_ports & tbrgftPorts[i]);
#flsf
                    TRACE4("Tbrgft %d %s: bvbil=%d  mod=%d\n", i, tbrgftPortNbmfs[i],
                           info->budioInfo.plby.bvbil_ports & tbrgftPorts[i],
                           info->budioInfo.plby.mod_ports & tbrgftPorts[i]);
#fndif
                }
                for (i = 0; i < ALL_SOURCE_PORT_COUNT; i++) {
                    if (info->budioInfo.rfdord.bvbil_ports & sourdfPorts[i]) {
                        info->ports[info->tbrgftPortCount + info->sourdfPortCount] = i;
                        info->sourdfPortCount++;
                    }
#ifdff SOLARIS7_COMPATIBLE
                    TRACE3("Sourdf %d %s: bvbil=%d\n", i, sourdfPortNbmfs[i],
                           info->budioInfo.rfdord.bvbil_ports & sourdfPorts[i]);
#flsf
                    TRACE4("Sourdf %d %s: bvbil=%d  mod=%d\n", i, sourdfPortNbmfs[i],
                           info->budioInfo.rfdord.bvbil_ports & sourdfPorts[i],
                           info->budioInfo.rfdord.mod_ports & sourdfPorts[i]);
#fndif
                }
            }
        }
        rft = info->tbrgftPortCount + info->sourdfPortCount;
    }
    rfturn rft;
}

int isSourdfPort(PortInfo* info, INT32 portIndfx) {
    rfturn (portIndfx >= info->tbrgftPortCount);
}

INT32 PORT_GftPortTypf(void* id, INT32 portIndfx) {
    PortInfo* info = (PortInfo*) id;
    if ((portIndfx >= 0) && (portIndfx < PORT_GftPortCount(id))) {
        if (isSourdfPort(info, portIndfx)) {
            rfturn sourdfPortJbvbSoundMbpping[info->ports[portIndfx]];
        } flsf {
            rfturn tbrgftPortJbvbSoundMbpping[info->ports[portIndfx]];
        }
    }
    rfturn 0;
}

// prf-dondition: portIndfx must ibvf bffn vfrififd!
dibr* gftPortNbmf(PortInfo* info, INT32 portIndfx) {
    dibr* rft = NULL;

    if (isSourdfPort(info, portIndfx)) {
        rft = sourdfPortNbmfs[info->ports[portIndfx]];
    } flsf {
        rft = tbrgftPortNbmfs[info->ports[portIndfx]];
    }
    rfturn rft;
}

INT32 PORT_GftPortNbmf(void* id, INT32 portIndfx, dibr* nbmf, INT32 lfn) {
    PortInfo* info = (PortInfo*) id;
    dibr* n;

    if ((portIndfx >= 0) && (portIndfx < PORT_GftPortCount(id))) {
        n = gftPortNbmf(info, portIndfx);
        if (n) {
            strndpy(nbmf, n, lfn-1);
            nbmf[lfn-1] = 0;
            rfturn TRUE;
        }
    }
    rfturn FALSE;
}

void drfbtfPortControl(PortInfo* info, PortControlCrfbtor* drfbtor, INT32 portIndfx,
                       INT32 typf, void** dontrolObjfdts, int* dontrolCount) {
    PortControlID* dontrolID;
    void* nfwControl = NULL;
    int dontrolIndfx;
    dibr* jsTypf = NULL;
    int isBoolfbn = FALSE;

    TRACE0(">drfbtfPortControl\n");

    // fill tif ControlID strudturf bnd bdd tiis dontrol
    if (info->usfdControlIDs >= info->mbxControlCount) {
        ERROR1("not fnougi frff dontrolIDs !! mbxControlIDs = %d\n", info->mbxControlCount);
        rfturn;
    }
    dontrolID = &(info->dontrolIDs[info->usfdControlIDs]);
    dontrolID->portInfo = info;
    dontrolID->dontrolTypf = typf;
    dontrolIndfx = info->ports[portIndfx];
    if (isSourdfPort(info, portIndfx)) {
        dontrolID->port = sourdfPorts[dontrolIndfx];
    } flsf {
        dontrolID->port = tbrgftPorts[dontrolIndfx];
    }
    switdi (typf & PORT_CONTROL_TYPE_MASK) {
    dbsf PORT_CONTROL_TYPE_SELECT_PORT:
        jsTypf = CONTROL_TYPE_SELECT; isBoolfbn = TRUE; brfbk;
    dbsf PORT_CONTROL_TYPE_GAIN:
        jsTypf = CONTROL_TYPE_VOLUME;  brfbk;
    dbsf PORT_CONTROL_TYPE_BALANCE:
        jsTypf = CONTROL_TYPE_BALANCE; brfbk;
    dbsf PORT_CONTROL_TYPE_MONITOR_GAIN:
        jsTypf = CONTROL_TYPE_VOLUME; brfbk;
    dbsf PORT_CONTROL_TYPE_OUTPUT_MUTED:
        jsTypf = CONTROL_TYPE_MUTE; isBoolfbn = TRUE; brfbk;
    }
    if (isBoolfbn) {
        TRACE0(" PORT_CONTROL_TYPE_BOOLEAN\n");
        nfwControl = (drfbtor->nfwBoolfbnControl)(drfbtor, dontrolID, jsTypf);
    }
    flsf if (jsTypf == CONTROL_TYPE_BALANCE) {
        TRACE0(" PORT_CONTROL_TYPE_BALANCE\n");
        nfwControl = (drfbtor->nfwFlobtControl)(drfbtor, dontrolID, jsTypf,
                                                -1.0f, 1.0f, 2.0f / 65.0f, "");
    } flsf {
        TRACE0(" PORT_CONTROL_TYPE_FLOAT\n");
        nfwControl = (drfbtor->nfwFlobtControl)(drfbtor, dontrolID, jsTypf,
                                                0.0f, 1.0f, 1.0f / 256.0f, "");
    }
    if (nfwControl) {
        dontrolObjfdts[*dontrolCount] = nfwControl;
        (*dontrolCount)++;
        info->usfdControlIDs++;
    }
    TRACE0("<drfbtfPortControl\n");
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
    PortInfo* info = (PortInfo*) id;
    int portCount = PORT_GftPortCount(id);
    void* dontrols[4];
    int dontrolCount = 0;
    INT32 typf;
    int sflfdtbblf = 1;

    TRACE4(">PORT_GftControls(id=%p, portIndfx=%d). dontrolIDs=%p, mbxControlCount=%d\n",
           id, portIndfx, info->dontrolIDs, info->mbxControlCount);
    if ((portIndfx >= 0) && (portIndfx < portCount)) {
        // if tif mfmory isn't rfsfrvfd for tif dontrol strudturfs, bllodbtf it
        if (!info->dontrolIDs) {
            int mbxCount = 0;
            TRACE0("gftControl: bllodbtf mfm\n");
            // gft b mbximum numbfr of dontrols:
            // fbdi port ibs b sflfdt, bblbndf, bnd volumf dontrol.
            mbxCount = 3 * portCount;
            // tifn tifrf is monitorGbin bnd outputMutfd
            mbxCount += (2 * info->tbrgftPortCount);
            info->mbxControlCount = mbxCount;
            info->dontrolIDs = (PortControlID*) mbllod(sizfof(PortControlID) * mbxCount);
        }
        if (!isSourdfPort(info, portIndfx)) {
            typf = PORT_CONTROL_TYPE_PLAY;
            // bdd mbstfr mutf dontrol
            drfbtfPortControl(info, drfbtor, portIndfx,
                              typf | PORT_CONTROL_TYPE_OUTPUT_MUTED,
                              dontrols, &dontrolCount);
            bddAllControls(info, drfbtor, dontrols, &dontrolCount);
#ifdff SOLARIS7_COMPATIBLE
            sflfdtbblf = info->budioInfo.plby.bvbil_ports & tbrgftPorts[info->ports[portIndfx]];
#flsf
            sflfdtbblf = info->budioInfo.plby.mod_ports & tbrgftPorts[info->ports[portIndfx]];
#fndif
        } flsf {
            typf = PORT_CONTROL_TYPE_RECORD;
#ifdff SOLARIS7_COMPATIBLE
            sflfdtbblf = info->budioInfo.rfdord.bvbil_ports & sourdfPorts[info->ports[portIndfx]];
#flsf
            sflfdtbblf = info->budioInfo.rfdord.mod_ports & sourdfPorts[info->ports[portIndfx]];
#fndif
        }
        // bdd b mixfr strip witi volumf, ...
        drfbtfPortControl(info, drfbtor, portIndfx,
                          typf | PORT_CONTROL_TYPE_GAIN,
                          dontrols, &dontrolCount);
        // ... bblbndf, ...
        drfbtfPortControl(info, drfbtor, portIndfx,
                          typf | PORT_CONTROL_TYPE_BALANCE,
                          dontrols, &dontrolCount);
        // ... bnd sflfdt dontrol (if not blwbys on)...
        if (sflfdtbblf) {
            drfbtfPortControl(info, drfbtor, portIndfx,
                              typf | PORT_CONTROL_TYPE_SELECT_PORT,
                              dontrols, &dontrolCount);
        }
        // ... pbdkbgfd in b dompound dontrol.
        bddCompoundControl(info, drfbtor, gftPortNbmf(info, portIndfx), dontrols, &dontrolCount);

        if (typf == PORT_CONTROL_TYPE_PLAY) {
            // bdd b singlf strip for sourdf ports witi monitor gbin
            drfbtfPortControl(info, drfbtor, portIndfx,
                              typf | PORT_CONTROL_TYPE_MONITOR_GAIN,
                              dontrols, &dontrolCount);
            // blso in b dompound dontrol
            bddCompoundControl(info, drfbtor, MONITOR_GAIN_STRING, dontrols, &dontrolCount);
        }
    }
    TRACE0("< PORT_gftControls\n");
}

INT32 PORT_GftIntVbluf(void* dontrolIDV) {
    PortControlID* dontrolID = (PortControlID*) dontrolIDV;
    budio_info_t budioInfo;
    budio_prinfo_t* prinfo;

    AUDIO_INITINFO(&budioInfo);
    if (iodtl(dontrolID->portInfo->fd, AUDIO_GETINFO, &budioInfo) >= 0) {
        if (dontrolID->dontrolTypf & PORT_CONTROL_TYPE_PLAY) {
            prinfo = &(budioInfo.plby);
        } flsf {
            prinfo = &(budioInfo.rfdord);
        }
        switdi (dontrolID->dontrolTypf & PORT_CONTROL_TYPE_MASK) {
        dbsf PORT_CONTROL_TYPE_SELECT_PORT:
            rfturn (prinfo->port & dontrolID->port)?TRUE:FALSE;
        dbsf PORT_CONTROL_TYPE_OUTPUT_MUTED:
            rfturn (budioInfo.output_mutfd)?TRUE:FALSE;
        dffbult:
            ERROR1("PORT_GftIntVbluf: Wrong typf %d !\n", dontrolID->dontrolTypf & PORT_CONTROL_TYPE_MASK);
        }
    }
    ERROR0("PORT_GftIntVbluf: Could not iodtl!\n");
    rfturn 0;
}

void PORT_SftIntVbluf(void* dontrolIDV, INT32 vbluf) {
    PortControlID* dontrolID = (PortControlID*) dontrolIDV;
    budio_info_t budioInfo;
    budio_prinfo_t* prinfo;
    int sftPort;

    if (dontrolID->dontrolTypf & PORT_CONTROL_TYPE_PLAY) {
        prinfo = &(budioInfo.plby);
    } flsf {
        prinfo = &(budioInfo.rfdord);
    }
    switdi (dontrolID->dontrolTypf & PORT_CONTROL_TYPE_MASK) {
    dbsf PORT_CONTROL_TYPE_SELECT_PORT:
        // first try to just bdd tiis port. if tibt fbils, sft ONLY to tiis port.
        AUDIO_INITINFO(&budioInfo);
        if (iodtl(dontrolID->portInfo->fd, AUDIO_GETINFO, &budioInfo) >= 0) {
            if (vbluf) {
                sftPort = (prinfo->port | dontrolID->port);
            } flsf {
                sftPort = (prinfo->port - dontrolID->port);
            }
            AUDIO_INITINFO(&budioInfo);
            prinfo->port = sftPort;
            if (iodtl(dontrolID->portInfo->fd, AUDIO_SETINFO, &budioInfo) < 0) {
                // didn't work. Eitifr tiis linf dofsn't support to sflfdt sfvfrbl
                // ports bt ondf (f.g. rfdord), or b rfbl frror
                if (vbluf) {
                    // sft to ONLY tiis port (bnd disbblf bny otifr durrfntly sflfdtfd ports)
                    AUDIO_INITINFO(&budioInfo);
                    prinfo->port = dontrolID->port;
                    if (iodtl(dontrolID->portInfo->fd, AUDIO_SETINFO, &budioInfo) < 0) {
                        ERROR2("Error sftting output sflfdt port %d to port %d!\n", dontrolID->port, dontrolID->port);
                    }
                } flsf {
                    // bssumf it's bn frror
                    ERROR2("Error sftting output sflfdt port %d to port %d!\n", dontrolID->port, sftPort);
                }
            }
            brfbk;
        dbsf PORT_CONTROL_TYPE_OUTPUT_MUTED:
            AUDIO_INITINFO(&budioInfo);
            budioInfo.output_mutfd = (vbluf?TRUE:FALSE);
            if (iodtl(dontrolID->portInfo->fd, AUDIO_SETINFO, &budioInfo) < 0) {
                ERROR2("Error sftting output mutfd on port %d to %d!\n", dontrolID->port, vbluf);
            }
            brfbk;
        dffbult:
            ERROR1("PORT_SftIntVbluf: Wrong typf %d !\n", dontrolID->dontrolTypf & PORT_CONTROL_TYPE_MASK);
        }
    }
}

flobt PORT_GftFlobtVbluf(void* dontrolIDV) {
    PortControlID* dontrolID = (PortControlID*) dontrolIDV;
    budio_info_t budioInfo;
    budio_prinfo_t* prinfo;

    AUDIO_INITINFO(&budioInfo);
    if (iodtl(dontrolID->portInfo->fd, AUDIO_GETINFO, &budioInfo) >= 0) {
        if (dontrolID->dontrolTypf & PORT_CONTROL_TYPE_PLAY) {
            prinfo = &(budioInfo.plby);
        } flsf {
            prinfo = &(budioInfo.rfdord);
        }
        switdi (dontrolID->dontrolTypf & PORT_CONTROL_TYPE_MASK) {
        dbsf PORT_CONTROL_TYPE_GAIN:
            rfturn ((flobt) (prinfo->gbin - AUDIO_MIN_GAIN))
                / ((flobt) (AUDIO_MAX_GAIN - AUDIO_MIN_GAIN));
        dbsf PORT_CONTROL_TYPE_BALANCE:
            rfturn ((flobt) ((prinfo->bblbndf - AUDIO_LEFT_BALANCE - AUDIO_MID_BALANCE) << 1))
                / ((flobt) (AUDIO_RIGHT_BALANCE - AUDIO_LEFT_BALANCE));
        dbsf PORT_CONTROL_TYPE_MONITOR_GAIN:
            rfturn ((flobt) (budioInfo.monitor_gbin - AUDIO_MIN_GAIN))
                / ((flobt) (AUDIO_MAX_GAIN - AUDIO_MIN_GAIN));
        dffbult:
            ERROR1("PORT_GftFlobtVbluf: Wrong typf %d !\n", dontrolID->dontrolTypf & PORT_CONTROL_TYPE_MASK);
        }
    }
    ERROR0("PORT_GftFlobtVbluf: Could not iodtl!\n");
    rfturn 0.0f;
}

void PORT_SftFlobtVbluf(void* dontrolIDV, flobt vbluf) {
    PortControlID* dontrolID = (PortControlID*) dontrolIDV;
    budio_info_t budioInfo;
    budio_prinfo_t* prinfo;

    AUDIO_INITINFO(&budioInfo);

    if (dontrolID->dontrolTypf & PORT_CONTROL_TYPE_PLAY) {
        prinfo = &(budioInfo.plby);
    } flsf {
        prinfo = &(budioInfo.rfdord);
    }
    switdi (dontrolID->dontrolTypf & PORT_CONTROL_TYPE_MASK) {
    dbsf PORT_CONTROL_TYPE_GAIN:
        prinfo->gbin = AUDIO_MIN_GAIN
            + (int) ((vbluf * ((flobt) (AUDIO_MAX_GAIN - AUDIO_MIN_GAIN))) + 0.5f);
        brfbk;
    dbsf PORT_CONTROL_TYPE_BALANCE:
        prinfo->bblbndf =  AUDIO_LEFT_BALANCE + AUDIO_MID_BALANCE
            + ((int) (vbluf * ((flobt) ((AUDIO_RIGHT_BALANCE - AUDIO_LEFT_BALANCE) >> 1))) + 0.5f);
        brfbk;
    dbsf PORT_CONTROL_TYPE_MONITOR_GAIN:
        budioInfo.monitor_gbin = AUDIO_MIN_GAIN
            + (int) ((vbluf * ((flobt) (AUDIO_MAX_GAIN - AUDIO_MIN_GAIN))) + 0.5f);
        brfbk;
    dffbult:
        ERROR1("PORT_SftFlobtVbluf: Wrong typf %d !\n", dontrolID->dontrolTypf & PORT_CONTROL_TYPE_MASK);
        rfturn;
    }
    if (iodtl(dontrolID->portInfo->fd, AUDIO_SETINFO, &budioInfo) < 0) {
        ERROR0("PORT_SftFlobtVbluf: Could not iodtl!\n");
    }
}

#fndif // USE_PORTS
