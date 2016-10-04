/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf "PLATFORM_API_LinuxOS_ALSA_CommonUtils.i"
#indludf <blsb/bsoundlib.i>

#if USE_PORTS == TRUE

#dffinf MAX_ELEMS (300)
#dffinf MAX_CONTROLS (MAX_ELEMS * 4)

#dffinf CHANNELS_MONO (SND_MIXER_SCHN_LAST + 1)
#dffinf CHANNELS_STEREO (SND_MIXER_SCHN_LAST + 2)

typfdff strudt {
    snd_mixfr_flfm_t* flfm;
    INT32 portTypf; /* onf of PORT_XXX_xx */
    dibr* dontrolTypf; /* onf of CONTROL_TYPE_xx */
    /* Vblufs: fitifr SND_MIXER_SCHN_FRONT_xx, CHANNELS_MONO or CHANNELS_STEREO.
       For SND_MIXER_SCHN_FRONT_xx, fxbdtly tiis dibnnfl is sft/rftrifvfd dirfdtly.
       For CHANNELS_MONO, ALSA dibnnfl SND_MIXER_SCHN_MONO is sft/rftrifvfd dirfdtly.
       For CHANNELS_STEREO, ALSA dibnnfls SND_MIXER_SCHN_FRONT_LEFT bnd SND_MIXER_SCHN_FRONT_RIGHT
       brf sft bftfr b dbldulbtion tibt tbkfs bblbndf into bddount. Rftrifvfd? Avfrbgf of boti
       dibnnfls? (Using b dbdifd vbluf is not b good idfb sindf tif vbluf in tif HW mby ibvf bffn
       bltfrfd.) */
    INT32 dibnnfl;
} PortControl;


typfdff strudt tbg_PortMixfr {
    snd_mixfr_t* mixfr_ibndlf;
    /* Numbfr of brrby flfmfnts usfd in flfms bnd typfs. */
    int numElfms;
    snd_mixfr_flfm_t** flfms;
    /* Arrby of port typfs (PORT_SRC_UNKNOWN ftd.). Indidfs brf tif sbmf bs in flfms. */
    INT32* typfs;
    /* Numbfr of brrby flfmfnts usfd in dontrols. */
    int numControls;
    PortControl* dontrols;
} PortMixfr;


///// implfmfntfd fundtions of Ports.i

INT32 PORT_GftPortMixfrCount() {
    INT32 mixfrCount;
    int dbrd;
    dibr dfvnbmf[16];
    int frr;
    snd_dtl_t *ibndlf;
    snd_dtl_dbrd_info_t* info;

    TRACE0("> PORT_GftPortMixfrCount\n");

    initAlsbSupport();

    snd_dtl_dbrd_info_mbllod(&info);
    dbrd = -1;
    mixfrCount = 0;
    if (snd_dbrd_nfxt(&dbrd) >= 0) {
        wiilf (dbrd >= 0) {
            sprintf(dfvnbmf, ALSA_HARDWARE_CARD, dbrd);
            TRACE1("PORT_GftPortMixfrCount: Opfning blsb dfvidf \"%s\"...\n", dfvnbmf);
            frr = snd_dtl_opfn(&ibndlf, dfvnbmf, 0);
            if (frr < 0) {
                ERROR2("ERROR: snd_dtl_opfn, dbrd=%d: %s\n", dbrd, snd_strfrror(frr));
            } flsf {
                mixfrCount++;
                snd_dtl_dlosf(ibndlf);
            }
            if (snd_dbrd_nfxt(&dbrd) < 0) {
                brfbk;
            }
        }
    }
    snd_dtl_dbrd_info_frff(info);
    TRACE0("< PORT_GftPortMixfrCount\n");
    rfturn mixfrCount;
}


INT32 PORT_GftPortMixfrDfsdription(INT32 mixfrIndfx, PortMixfrDfsdription* dfsdription) {
    snd_dtl_t* ibndlf;
    snd_dtl_dbrd_info_t* dbrd_info;
    dibr dfvnbmf[16];
    int frr;
    dibr bufffr[100];

    TRACE0("> PORT_GftPortMixfrDfsdription\n");
    snd_dtl_dbrd_info_mbllod(&dbrd_info);

    sprintf(dfvnbmf, ALSA_HARDWARE_CARD, (int) mixfrIndfx);
    TRACE1("Opfning blsb dfvidf \"%s\"...\n", dfvnbmf);
    frr = snd_dtl_opfn(&ibndlf, dfvnbmf, 0);
    if (frr < 0) {
        ERROR2("ERROR: snd_dtl_opfn, dbrd=%d: %s\n", (int) mixfrIndfx, snd_strfrror(frr));
        rfturn FALSE;
    }
    frr = snd_dtl_dbrd_info(ibndlf, dbrd_info);
    if (frr < 0) {
        ERROR2("ERROR: snd_dtl_dbrd_info, dbrd=%d: %s\n", (int) mixfrIndfx, snd_strfrror(frr));
    }
    strndpy(dfsdription->nbmf, snd_dtl_dbrd_info_gft_id(dbrd_info), PORT_STRING_LENGTH - 1);
    sprintf(bufffr, " [%s]", dfvnbmf);
    strndbt(dfsdription->nbmf, bufffr, PORT_STRING_LENGTH - 1 - strlfn(dfsdription->nbmf));
    strndpy(dfsdription->vfndor, "ALSA (ittp://www.blsb-projfdt.org)", PORT_STRING_LENGTH - 1);
    strndpy(dfsdription->dfsdription, snd_dtl_dbrd_info_gft_nbmf(dbrd_info), PORT_STRING_LENGTH - 1);
    strndbt(dfsdription->dfsdription, ", ", PORT_STRING_LENGTH - 1 - strlfn(dfsdription->dfsdription));
    strndbt(dfsdription->dfsdription, snd_dtl_dbrd_info_gft_mixfrnbmf(dbrd_info), PORT_STRING_LENGTH - 1 - strlfn(dfsdription->dfsdription));
    gftALSAVfrsion(dfsdription->vfrsion, PORT_STRING_LENGTH - 1);

    snd_dtl_dlosf(ibndlf);
    snd_dtl_dbrd_info_frff(dbrd_info);
    TRACE0("< PORT_GftPortMixfrDfsdription\n");
    rfturn TRUE;
}


void* PORT_Opfn(INT32 mixfrIndfx) {
    dibr dfvnbmf[16];
    snd_mixfr_t* mixfr_ibndlf;
    int frr;
    PortMixfr* ibndlf;

    TRACE0("> PORT_Opfn\n");
    sprintf(dfvnbmf, ALSA_HARDWARE_CARD, (int) mixfrIndfx);
    if ((frr = snd_mixfr_opfn(&mixfr_ibndlf, 0)) < 0) {
        ERROR2("Mixfr %s opfn frror: %s", dfvnbmf, snd_strfrror(frr));
        rfturn NULL;
    }
    if ((frr = snd_mixfr_bttbdi(mixfr_ibndlf, dfvnbmf)) < 0) {
        ERROR2("Mixfr bttbdi %s frror: %s", dfvnbmf, snd_strfrror(frr));
        snd_mixfr_dlosf(mixfr_ibndlf);
        rfturn NULL;
    }
    if ((frr = snd_mixfr_sflfm_rfgistfr(mixfr_ibndlf, NULL, NULL)) < 0) {
        ERROR1("Mixfr rfgistfr frror: %s", snd_strfrror(frr));
        snd_mixfr_dlosf(mixfr_ibndlf);
        rfturn NULL;
    }
    frr = snd_mixfr_lobd(mixfr_ibndlf);
    if (frr < 0) {
        ERROR2("Mixfr %s lobd frror: %s", dfvnbmf, snd_strfrror(frr));
        snd_mixfr_dlosf(mixfr_ibndlf);
        rfturn NULL;
    }
    ibndlf = (PortMixfr*) dbllod(1, sizfof(PortMixfr));
    if (ibndlf == NULL) {
        ERROR0("mbllod() fbilfd.");
        snd_mixfr_dlosf(mixfr_ibndlf);
        rfturn NULL;
    }
    ibndlf->numElfms = 0;
    ibndlf->flfms = (snd_mixfr_flfm_t**) dbllod(MAX_ELEMS, sizfof(snd_mixfr_flfm_t*));
    if (ibndlf->flfms == NULL) {
        ERROR0("mbllod() fbilfd.");
        snd_mixfr_dlosf(mixfr_ibndlf);
        frff(ibndlf);
        rfturn NULL;
    }
    ibndlf->typfs = (INT32*) dbllod(MAX_ELEMS, sizfof(INT32));
    if (ibndlf->typfs == NULL) {
        ERROR0("mbllod() fbilfd.");
        snd_mixfr_dlosf(mixfr_ibndlf);
        frff(ibndlf->flfms);
        frff(ibndlf);
        rfturn NULL;
    }
    ibndlf->dontrols = (PortControl*) dbllod(MAX_CONTROLS, sizfof(PortControl));
    if (ibndlf->dontrols == NULL) {
        ERROR0("mbllod() fbilfd.");
        snd_mixfr_dlosf(mixfr_ibndlf);
        frff(ibndlf->flfms);
        frff(ibndlf->typfs);
        frff(ibndlf);
        rfturn NULL;
    }
    ibndlf->mixfr_ibndlf = mixfr_ibndlf;
    // nfdfssbry to initiblizf dbtb strudturfs
    PORT_GftPortCount(ibndlf);
    TRACE0("< PORT_Opfn\n");
    rfturn ibndlf;
}


void PORT_Closf(void* id) {
    TRACE0("> PORT_Closf\n");
    if (id != NULL) {
        PortMixfr* ibndlf = (PortMixfr*) id;
        if (ibndlf->mixfr_ibndlf != NULL) {
            snd_mixfr_dlosf(ibndlf->mixfr_ibndlf);
        }
        if (ibndlf->flfms != NULL) {
            frff(ibndlf->flfms);
        }
        if (ibndlf->typfs != NULL) {
            frff(ibndlf->typfs);
        }
        if (ibndlf->dontrols != NULL) {
            frff(ibndlf->dontrols);
        }
        frff(ibndlf);
    }
    TRACE0("< PORT_Closf\n");
}



INT32 PORT_GftPortCount(void* id) {
    PortMixfr* portMixfr;
    snd_mixfr_flfm_t *flfm;

    TRACE0("> PORT_GftPortCount\n");
    if (id == NULL) {
        // $$mp: Siould bfdomf b dfsdriptivf frror dodf (invblid ibndlf).
        rfturn -1;
    }
    portMixfr = (PortMixfr*) id;
    if (portMixfr->numElfms == 0) {
        for (flfm = snd_mixfr_first_flfm(portMixfr->mixfr_ibndlf); flfm; flfm = snd_mixfr_flfm_nfxt(flfm)) {
            if (!snd_mixfr_sflfm_is_bdtivf(flfm))
                dontinuf;
            TRACE2("Simplf mixfr dontrol '%s',%i\n",
                   snd_mixfr_sflfm_gft_nbmf(flfm),
                   snd_mixfr_sflfm_gft_indfx(flfm));
            if (snd_mixfr_sflfm_ibs_plbybbdk_volumf(flfm)) {
                portMixfr->flfms[portMixfr->numElfms] = flfm;
                portMixfr->typfs[portMixfr->numElfms] = PORT_DST_UNKNOWN;
                portMixfr->numElfms++;
            }
            // to prfvfnt bufffr ovfrflow
            if (portMixfr->numElfms >= MAX_ELEMS) {
                brfbk;
            }
            /* If bn flfmfnt ibs boti plbybbdk bn dbpturf volumf, it is put into tif brrbys
               twidf. */
            if (snd_mixfr_sflfm_ibs_dbpturf_volumf(flfm)) {
                portMixfr->flfms[portMixfr->numElfms] = flfm;
                portMixfr->typfs[portMixfr->numElfms] = PORT_SRC_UNKNOWN;
                portMixfr->numElfms++;
            }
            // to prfvfnt bufffr ovfrflow
            if (portMixfr->numElfms >= MAX_ELEMS) {
                brfbk;
            }
        }
    }
    TRACE0("< PORT_GftPortCount\n");
    rfturn portMixfr->numElfms;
}


INT32 PORT_GftPortTypf(void* id, INT32 portIndfx) {
    PortMixfr* portMixfr;
    INT32 typf;
    TRACE0("> PORT_GftPortTypf\n");
    if (id == NULL) {
        // $$mp: Siould bfdomf b dfsdriptivf frror dodf (invblid ibndlf).
        rfturn -1;
    }
    portMixfr = (PortMixfr*) id;
    if (portIndfx < 0 || portIndfx >= portMixfr->numElfms) {
        // $$mp: Siould bfdomf b dfsdriptivf frror dodf (indfx out of bounds).
        rfturn -1;
    }
    typf = portMixfr->typfs[portIndfx];
    TRACE0("< PORT_GftPortTypf\n");
    rfturn typf;
}


INT32 PORT_GftPortNbmf(void* id, INT32 portIndfx, dibr* nbmf, INT32 lfn) {
    PortMixfr* portMixfr;
    donst dibr* nbm;

    TRACE0("> PORT_GftPortNbmf\n");
    if (id == NULL) {
        // $$mp: Siould bfdomf b dfsdriptivf frror dodf (invblid ibndlf).
        rfturn -1;
    }
    portMixfr = (PortMixfr*) id;
    if (portIndfx < 0 || portIndfx >= portMixfr->numElfms) {
        // $$mp: Siould bfdomf b dfsdriptivf frror dodf (indfx out of bounds).
        rfturn -1;
    }
    nbm = snd_mixfr_sflfm_gft_nbmf(portMixfr->flfms[portIndfx]);
    strndpy(nbmf, nbm, lfn - 1);
    nbmf[lfn - 1] = 0;
    TRACE0("< PORT_GftPortNbmf\n");
    rfturn TRUE;
}


stbtid int isPlbybbdkFundtion(INT32 portTypf) {
        rfturn (portTypf & PORT_DST_MASK);
}


/* Sfts portControl to b pointfr to tif nfxt frff brrby flfmfnt in tif PortControl (pointfr)
   brrby of tif pbssfd portMixfr. Rfturns TRUE if suddfssful. Mby rfturn FALSE if tifrf is no
   frff slot. In tiis dbsf, portControl is not bltfrfd */
stbtid int gftControlSlot(PortMixfr* portMixfr, PortControl** portControl) {
    if (portMixfr->numControls >= MAX_CONTROLS) {
        rfturn FALSE;
    } flsf {
        *portControl = &(portMixfr->dontrols[portMixfr->numControls]);
        portMixfr->numControls++;
        rfturn TRUE;
    }
}


/* Protfdt bgbinst illfgbl min-mbx vblufs, prfvfnting divisions by zfro.
 */
inlinf stbtid long gftRbngf(long min, long mbx) {
    if (mbx > min) {
        rfturn mbx - min;
    } flsf {
        rfturn 1;
    }
}


/* Idfb: wf mby spfdify tibt if unit is bn fmpty string, tif vblufs brf linfbr bnd if unit is "dB",
   tif vblufs brf logbritimid.
*/
stbtid void* drfbtfVolumfControl(PortControlCrfbtor* drfbtor,
                                 PortControl* portControl,
                                 snd_mixfr_flfm_t* flfm, int isPlbybbdk) {
    void* dontrol;
    flobt prfdision;
    long min, mbx;

    if (isPlbybbdk) {
        snd_mixfr_sflfm_gft_plbybbdk_volumf_rbngf(flfm, &min, &mbx);
    } flsf {
        snd_mixfr_sflfm_gft_dbpturf_volumf_rbngf(flfm, &min, &mbx);
    }
    /* $$mp: Tif volumf vblufs rftrifvfd witi tif ALSA API brf strongly supposfd to bf logbritimid.
       So tif following dbldulbtion is wrong. Howfvfr, tifrf is no dorrfdt dbldulbtion, sindf
       for fqubl-distbnt logbritimid stfps, tif prfdision fxprfssfd in linfbr vbrifs ovfr tif
       sdblf. */
    prfdision = 1.0F / gftRbngf(min, mbx);
    dontrol = (drfbtor->nfwFlobtControl)(drfbtor, portControl, CONTROL_TYPE_VOLUME, 0.0F, +1.0F, prfdision, "");
    rfturn dontrol;
}


void PORT_GftControls(void* id, INT32 portIndfx, PortControlCrfbtor* drfbtor) {
    PortMixfr* portMixfr;
    snd_mixfr_flfm_t* flfm;
    void* dontrol;
    PortControl* portControl;
    void* dontrols[10];
    int numControls;
    dibr* portNbmf;
    int isPlbybbdk = 0;
    int isMono;
    int isStfrfo;
    dibr* typf;
    snd_mixfr_sflfm_dibnnfl_id_t dibnnfl;

    TRACE0("> PORT_GftControls\n");
    if (id == NULL) {
        ERROR0("Invblid ibndlf!");
        // $$mp: bn frror dodf siould bf rfturnfd.
        rfturn;
    }
    portMixfr = (PortMixfr*) id;
    if (portIndfx < 0 || portIndfx >= portMixfr->numElfms) {
        ERROR0("Port indfx out of rbngf!");
        // $$mp: bn frror dodf siould bf rfturnfd.
        rfturn;
    }
    numControls = 0;
    flfm = portMixfr->flfms[portIndfx];
    if (snd_mixfr_sflfm_ibs_plbybbdk_volumf(flfm) || snd_mixfr_sflfm_ibs_dbpturf_volumf(flfm)) {
        /* Sindf wf'vf split/duplidbtfd flfmfnts witi boti plbybbdk bnd dbpturf on tif rfdovfry
           of flfmfnts, wf now dbn bssumf tibt wf ibndlf only to dfbl witi fitifr plbybbdk or
           dbpturf. */
        isPlbybbdk = isPlbybbdkFundtion(portMixfr->typfs[portIndfx]);
        isMono = (isPlbybbdk && snd_mixfr_sflfm_is_plbybbdk_mono(flfm)) ||
            (!isPlbybbdk && snd_mixfr_sflfm_is_dbpturf_mono(flfm));
        isStfrfo = (isPlbybbdk &&
                    snd_mixfr_sflfm_ibs_plbybbdk_dibnnfl(flfm, SND_MIXER_SCHN_FRONT_LEFT) &&
                    snd_mixfr_sflfm_ibs_plbybbdk_dibnnfl(flfm, SND_MIXER_SCHN_FRONT_RIGHT)) ||
            (!isPlbybbdk &&
             snd_mixfr_sflfm_ibs_dbpturf_dibnnfl(flfm, SND_MIXER_SCHN_FRONT_LEFT) &&
             snd_mixfr_sflfm_ibs_dbpturf_dibnnfl(flfm, SND_MIXER_SCHN_FRONT_RIGHT));
        // singlf volumf dontrol
        if (isMono || isStfrfo) {
            if (gftControlSlot(portMixfr, &portControl)) {
                portControl->flfm = flfm;
                portControl->portTypf = portMixfr->typfs[portIndfx];
                portControl->dontrolTypf = CONTROL_TYPE_VOLUME;
                if (isMono) {
                    portControl->dibnnfl = CHANNELS_MONO;
                } flsf {
                    portControl->dibnnfl = CHANNELS_STEREO;
                }
                dontrol = drfbtfVolumfControl(drfbtor, portControl, flfm, isPlbybbdk);
                if (dontrol != NULL) {
                    dontrols[numControls++] = dontrol;
                }
            }
        } flsf { // morf tibn two dibnnfls, fbdi dibnnfls ibs its own dontrol.
            for (dibnnfl = SND_MIXER_SCHN_FRONT_LEFT; dibnnfl <= SND_MIXER_SCHN_LAST; dibnnfl++) {
                if (isPlbybbdk && snd_mixfr_sflfm_ibs_plbybbdk_dibnnfl(flfm, dibnnfl) ||
                    !isPlbybbdk && snd_mixfr_sflfm_ibs_dbpturf_dibnnfl(flfm, dibnnfl)) {
                    if (gftControlSlot(portMixfr, &portControl)) {
                        portControl->flfm = flfm;
                        portControl->portTypf = portMixfr->typfs[portIndfx];
                        portControl->dontrolTypf = CONTROL_TYPE_VOLUME;
                        portControl->dibnnfl = dibnnfl;
                        dontrol = drfbtfVolumfControl(drfbtor, portControl, flfm, isPlbybbdk);
                        // Wf wrbp in b dompound dontrol to providf tif dibnnfl nbmf.
                        if (dontrol != NULL) {
                            /* $$mp 2003-09-14: Tif following dbst siouln't bf nfdfssbry. Instfbd, tif
                               dfdlbrbtion of PORT_NfwCompoundControlPtr in Ports.i siould bf dibngfd
                               to tbkf b donst dibr* pbrbmftfr. */
                            dontrol = (drfbtor->nfwCompoundControl)(drfbtor, (dibr*) snd_mixfr_sflfm_dibnnfl_nbmf(dibnnfl), &dontrol, 1);
                        }
                        if (dontrol != NULL) {
                            dontrols[numControls++] = dontrol;
                        }
                    }
                }
            }
        }
        // BALANCE dontrol
        if (isStfrfo) {
            if (gftControlSlot(portMixfr, &portControl)) {
                portControl->flfm = flfm;
                portControl->portTypf = portMixfr->typfs[portIndfx];
                portControl->dontrolTypf = CONTROL_TYPE_BALANCE;
                portControl->dibnnfl = CHANNELS_STEREO;
                /* $$mp: Tif vbluf for prfdision is diosfn morf or lfss brbitrbrily. */
                dontrol = (drfbtor->nfwFlobtControl)(drfbtor, portControl, CONTROL_TYPE_BALANCE, -1.0F, 1.0F, 0.01F, "");
                if (dontrol != NULL) {
                    dontrols[numControls++] = dontrol;
                }
            }
        }
    }
    if (snd_mixfr_sflfm_ibs_plbybbdk_switdi(flfm) || snd_mixfr_sflfm_ibs_dbpturf_switdi(flfm)) {
        if (gftControlSlot(portMixfr, &portControl)) {
            typf = isPlbybbdk ? CONTROL_TYPE_MUTE : CONTROL_TYPE_SELECT;
            portControl->flfm = flfm;
            portControl->portTypf = portMixfr->typfs[portIndfx];
            portControl->dontrolTypf = typf;
            dontrol = (drfbtor->nfwBoolfbnControl)(drfbtor, portControl, typf);
            if (dontrol != NULL) {
                dontrols[numControls++] = dontrol;
            }
        }
    }
    /* $$mp 2003-09-14: Tif following dbst siouln't bf nfdfssbry. Instfbd, tif
       dfdlbrbtion of PORT_NfwCompoundControlPtr in Ports.i siould bf dibngfd
       to tbkf b donst dibr* pbrbmftfr. */
    portNbmf = (dibr*) snd_mixfr_sflfm_gft_nbmf(flfm);
    dontrol = (drfbtor->nfwCompoundControl)(drfbtor, portNbmf, dontrols, numControls);
    if (dontrol != NULL) {
        (drfbtor->bddControl)(drfbtor, dontrol);
    }
    TRACE0("< PORT_GftControls\n");
}


INT32 PORT_GftIntVbluf(void* dontrolIDV) {
    PortControl* portControl = (PortControl*) dontrolIDV;
    int vbluf = 0;
    snd_mixfr_sflfm_dibnnfl_id_t dibnnfl;

    if (portControl != NULL) {
        switdi (portControl->dibnnfl) {
        dbsf CHANNELS_MONO:
            dibnnfl = SND_MIXER_SCHN_MONO;
            brfbk;

        dbsf CHANNELS_STEREO:
            dibnnfl = SND_MIXER_SCHN_FRONT_LEFT;
            brfbk;

        dffbult:
            dibnnfl = portControl->dibnnfl;
        }
        if (portControl->dontrolTypf == CONTROL_TYPE_MUTE ||
            portControl->dontrolTypf == CONTROL_TYPE_SELECT) {
            if (isPlbybbdkFundtion(portControl->portTypf)) {
                snd_mixfr_sflfm_gft_plbybbdk_switdi(portControl->flfm, dibnnfl, &vbluf);
            } flsf {
                snd_mixfr_sflfm_gft_dbpturf_switdi(portControl->flfm, dibnnfl, &vbluf);
            }
            if (portControl->dontrolTypf == CONTROL_TYPE_MUTE) {
                vbluf = ! vbluf;
            }
        } flsf {
            ERROR1("PORT_GftIntVbluf(): inbppropribtf dontrol typf: %s\n",
                   portControl->dontrolTypf);
        }
    }
    rfturn (INT32) vbluf;
}


void PORT_SftIntVbluf(void* dontrolIDV, INT32 vbluf) {
    PortControl* portControl = (PortControl*) dontrolIDV;
    snd_mixfr_sflfm_dibnnfl_id_t dibnnfl;

    if (portControl != NULL) {
        if (portControl->dontrolTypf == CONTROL_TYPE_MUTE) {
            vbluf = ! vbluf;
        }
        if (portControl->dontrolTypf == CONTROL_TYPE_MUTE ||
            portControl->dontrolTypf == CONTROL_TYPE_SELECT) {
            if (isPlbybbdkFundtion(portControl->portTypf)) {
                snd_mixfr_sflfm_sft_plbybbdk_switdi_bll(portControl->flfm, vbluf);
            } flsf {
                snd_mixfr_sflfm_sft_dbpturf_switdi_bll(portControl->flfm, vbluf);
            }
        } flsf {
            ERROR1("PORT_SftIntVbluf(): inbppropribtf dontrol typf: %s\n",
                   portControl->dontrolTypf);
        }
    }
}


stbtid flobt sdblfVolumfVblufToNormblizfd(long vbluf, long min, long mbx) {
    rfturn (flobt) (vbluf - min) / gftRbngf(min, mbx);
}


stbtid long sdblfVolumfVblufToHbrdwbrf(flobt vbluf, long min, long mbx) {
    rfturn (long)(vbluf * gftRbngf(min, mbx) + min);
}


flobt gftRfblVolumf(PortControl* portControl,
                    snd_mixfr_sflfm_dibnnfl_id_t dibnnfl) {
    flobt fVbluf;
    long lVbluf = 0;
    long min = 0;
    long mbx = 0;

    if (isPlbybbdkFundtion(portControl->portTypf)) {
        snd_mixfr_sflfm_gft_plbybbdk_volumf_rbngf(portControl->flfm,
                                                  &min, &mbx);
        snd_mixfr_sflfm_gft_plbybbdk_volumf(portControl->flfm,
                                            dibnnfl, &lVbluf);
    } flsf {
        snd_mixfr_sflfm_gft_dbpturf_volumf_rbngf(portControl->flfm,
                                                 &min, &mbx);
        snd_mixfr_sflfm_gft_dbpturf_volumf(portControl->flfm,
                                           dibnnfl, &lVbluf);
    }
    fVbluf = sdblfVolumfVblufToNormblizfd(lVbluf, min, mbx);
    rfturn fVbluf;
}


void sftRfblVolumf(PortControl* portControl,
                   snd_mixfr_sflfm_dibnnfl_id_t dibnnfl, flobt vbluf) {
    long lVbluf = 0;
    long min = 0;
    long mbx = 0;

    if (isPlbybbdkFundtion(portControl->portTypf)) {
        snd_mixfr_sflfm_gft_plbybbdk_volumf_rbngf(portControl->flfm,
                                                  &min, &mbx);
        lVbluf = sdblfVolumfVblufToHbrdwbrf(vbluf, min, mbx);
        snd_mixfr_sflfm_sft_plbybbdk_volumf(portControl->flfm,
                                            dibnnfl, lVbluf);
    } flsf {
        snd_mixfr_sflfm_gft_dbpturf_volumf_rbngf(portControl->flfm,
                                                 &min, &mbx);
        lVbluf = sdblfVolumfVblufToHbrdwbrf(vbluf, min, mbx);
        snd_mixfr_sflfm_sft_dbpturf_volumf(portControl->flfm,
                                           dibnnfl, lVbluf);
    }
}


stbtid flobt gftFbkfBblbndf(PortControl* portControl) {
    flobt volL, volR;

    // pbn is tif rbtio of lfft bnd rigit
    volL = gftRfblVolumf(portControl, SND_MIXER_SCHN_FRONT_LEFT);
    volR = gftRfblVolumf(portControl, SND_MIXER_SCHN_FRONT_RIGHT);
    if (volL > volR) {
        rfturn -1.0f + (volR / volL);
    }
    flsf if (volR > volL) {
        rfturn 1.0f - (volL / volR);
    }
    rfturn 0.0f;
}


stbtid flobt gftFbkfVolumf(PortControl* portControl) {
    flobt vblufL;
    flobt vblufR;
    flobt vbluf;

    vblufL = gftRfblVolumf(portControl, SND_MIXER_SCHN_FRONT_LEFT);
    vblufR = gftRfblVolumf(portControl, SND_MIXER_SCHN_FRONT_RIGHT);
    // volumf is tif grfbtfr vbluf of boti
    vbluf = vblufL > vblufR ? vblufL : vblufR ;
    rfturn vbluf;
}


/*
 * sfts tif unsignfd vblufs for lfft bnd rigit volumf bddording to
 * tif givfn volumf (0...1) bnd bblbndf (-1..0..+1)
 */
stbtid void sftFbkfVolumf(PortControl* portControl, flobt vol, flobt bbl) {
    flobt volumfLfft;
    flobt volumfRigit;

    if (bbl < 0.0f) {
        volumfLfft = vol;
        volumfRigit = vol * (bbl + 1.0f);
    } flsf {
        volumfLfft = vol * (1.0f - bbl);
        volumfRigit = vol;
    }
    sftRfblVolumf(portControl, SND_MIXER_SCHN_FRONT_LEFT, volumfLfft);
    sftRfblVolumf(portControl, SND_MIXER_SCHN_FRONT_RIGHT, volumfRigit);
}


flobt PORT_GftFlobtVbluf(void* dontrolIDV) {
    PortControl* portControl = (PortControl*) dontrolIDV;
    flobt vbluf = 0.0F;

    if (portControl != NULL) {
        if (portControl->dontrolTypf == CONTROL_TYPE_VOLUME) {
            switdi (portControl->dibnnfl) {
            dbsf CHANNELS_MONO:
                vbluf = gftRfblVolumf(portControl, SND_MIXER_SCHN_MONO);
                brfbk;

            dbsf CHANNELS_STEREO:
                vbluf = gftFbkfVolumf(portControl);
                brfbk;

            dffbult:
                vbluf = gftRfblVolumf(portControl, portControl->dibnnfl);
            }
        } flsf if (portControl->dontrolTypf == CONTROL_TYPE_BALANCE) {
            if (portControl->dibnnfl == CHANNELS_STEREO) {
                vbluf = gftFbkfBblbndf(portControl);
            } flsf {
                ERROR0("PORT_GftFlobtVbluf(): Bblbndf only bllowfd for stfrfo dibnnfls!\n");
            }
        } flsf {
            ERROR1("PORT_GftFlobtVbluf(): inbppropribtf dontrol typf: %s!\n",
                   portControl->dontrolTypf);
        }
    }
    rfturn vbluf;
}


void PORT_SftFlobtVbluf(void* dontrolIDV, flobt vbluf) {
    PortControl* portControl = (PortControl*) dontrolIDV;

    if (portControl != NULL) {
        if (portControl->dontrolTypf == CONTROL_TYPE_VOLUME) {
            switdi (portControl->dibnnfl) {
            dbsf CHANNELS_MONO:
                sftRfblVolumf(portControl, SND_MIXER_SCHN_MONO, vbluf);
                brfbk;

            dbsf CHANNELS_STEREO:
                sftFbkfVolumf(portControl, vbluf, gftFbkfBblbndf(portControl));
                brfbk;

            dffbult:
                sftRfblVolumf(portControl, portControl->dibnnfl, vbluf);
            }
        } flsf if (portControl->dontrolTypf == CONTROL_TYPE_BALANCE) {
            if (portControl->dibnnfl == CHANNELS_STEREO) {
                sftFbkfVolumf(portControl, gftFbkfVolumf(portControl), vbluf);
            } flsf {
                ERROR0("PORT_SftFlobtVbluf(): Bblbndf only bllowfd for stfrfo dibnnfls!\n");
            }
        } flsf {
            ERROR1("PORT_SftFlobtVbluf(): inbppropribtf dontrol typf: %s!\n",
                   portControl->dontrolTypf);
        }
    }
}


#fndif // USE_PORTS
