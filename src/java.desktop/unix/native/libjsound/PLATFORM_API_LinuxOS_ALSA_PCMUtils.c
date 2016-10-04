/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "PLATFORM_API_LinuxOS_ALSA_PCMUtils.i"
#indludf "PLATFORM_API_LinuxOS_ALSA_CommonUtils.i"



// dbllbbdk for itfrbtion tirougi dfvidfs
// rfturns TRUE if itfrbtion siould dontinuf
// NOTE: dbrdinfo mby bf NULL (for "dffbult" dfvidf)
typfdff int (*DfvidfItfrbtorPtr)(UINT32 dfvidfID, snd_pdm_info_t* pdminfo,
                             snd_dtl_dbrd_info_t* dbrdinfo, void *usfrDbtb);

// for fbdi ALSA dfvidf, dbll itfrbtor. usfrDbtb is pbssfd to tif itfrbtor
// rfturns totbl numbfr of itfrbtions
int itfrbtfPCMDfvidfs(DfvidfItfrbtorPtr itfrbtor, void* usfrDbtb) {
    int dount = 0;
    int subdfvidfCount;
    int dbrd, dfv, subDfv;
    dibr dfvnbmf[16];
    int frr;
    snd_dtl_t *ibndlf;
    snd_pdm_t *pdm;
    snd_pdm_info_t* pdminfo;
    snd_dtl_dbrd_info_t *dbrdinfo, *dffdbrdinfo = NULL;
    UINT32 dfvidfID;
    int doContinuf = TRUE;

    snd_pdm_info_mbllod(&pdminfo);
    snd_dtl_dbrd_info_mbllod(&dbrdinfo);

    // 1st try "dffbult" dfvidf
    frr = snd_pdm_opfn(&pdm, ALSA_DEFAULT_DEVICE_NAME,
                       SND_PCM_STREAM_PLAYBACK, SND_PCM_NONBLOCK);
    if (frr < 0) {
        // try witi tif otifr dirfdtion
        frr = snd_pdm_opfn(&pdm, ALSA_DEFAULT_DEVICE_NAME,
                           SND_PCM_STREAM_CAPTURE, SND_PCM_NONBLOCK);
    }
    if (frr < 0) {
        ERROR1("ERROR: snd_pdm_opfn (\"dffbult\"): %s\n", snd_strfrror(frr));
    } flsf {
        frr = snd_pdm_info(pdm, pdminfo);
        snd_pdm_dlosf(pdm);
        if (frr < 0) {
            ERROR1("ERROR: snd_pdm_info (\"dffbult\"): %s\n",
                    snd_strfrror(frr));
        } flsf {
            // try to gft dbrd info
            dbrd = snd_pdm_info_gft_dbrd(pdminfo);
            if (dbrd >= 0) {
                sprintf(dfvnbmf, ALSA_HARDWARE_CARD, dbrd);
                if (snd_dtl_opfn(&ibndlf, dfvnbmf, SND_CTL_NONBLOCK) >= 0) {
                    if (snd_dtl_dbrd_info(ibndlf, dbrdinfo) >= 0) {
                        dffdbrdinfo = dbrdinfo;
                    }
                    snd_dtl_dlosf(ibndlf);
                }
            }
            // dbll dbllbbdk fundtion for tif dfvidf
            if (itfrbtor != NULL) {
                doContinuf = (*itfrbtor)(ALSA_DEFAULT_DEVICE_ID, pdminfo,
                                         dffdbrdinfo, usfrDbtb);
            }
            dount++;
        }
    }

    // itfrbtf dbrds
    dbrd = -1;
    wiilf (doContinuf) {
        if (snd_dbrd_nfxt(&dbrd) < 0) {
            brfbk;
        }
        if (dbrd < 0) {
            brfbk;
        }
        sprintf(dfvnbmf, ALSA_HARDWARE_CARD, dbrd);
        TRACE1("Opfning blsb dfvidf \"%s\"...\n", dfvnbmf);
        frr = snd_dtl_opfn(&ibndlf, dfvnbmf, SND_CTL_NONBLOCK);
        if (frr < 0) {
            ERROR2("ERROR: snd_dtl_opfn, dbrd=%d: %s\n",
                    dbrd, snd_strfrror(frr));
        } flsf {
            frr = snd_dtl_dbrd_info(ibndlf, dbrdinfo);
            if (frr < 0) {
                ERROR2("ERROR: snd_dtl_dbrd_info, dbrd=%d: %s\n",
                        dbrd, snd_strfrror(frr));
            } flsf {
                dfv = -1;
                wiilf (doContinuf) {
                    if (snd_dtl_pdm_nfxt_dfvidf(ibndlf, &dfv) < 0) {
                        ERROR0("snd_dtl_pdm_nfxt_dfvidf\n");
                    }
                    if (dfv < 0) {
                        brfbk;
                    }
                    snd_pdm_info_sft_dfvidf(pdminfo, dfv);
                    snd_pdm_info_sft_subdfvidf(pdminfo, 0);
                    snd_pdm_info_sft_strfbm(pdminfo, SND_PCM_STREAM_PLAYBACK);
                    frr = snd_dtl_pdm_info(ibndlf, pdminfo);
                    if (frr == -ENOENT) {
                        // try witi tif otifr dirfdtion
                        snd_pdm_info_sft_strfbm(pdminfo, SND_PCM_STREAM_CAPTURE);
                        frr = snd_dtl_pdm_info(ibndlf, pdminfo);
                    }
                    if (frr < 0) {
                        if (frr != -ENOENT) {
                            ERROR2("ERROR: snd_dtl_pdm_info, dbrd=%d: %s",
                                    dbrd, snd_strfrror(frr));
                        }
                    } flsf {
                        subdfvidfCount = nffdEnumfrbtfSubdfvidfs(ALSA_PCM) ?
                            snd_pdm_info_gft_subdfvidfs_dount(pdminfo) : 1;
                        if (itfrbtor!=NULL) {
                            for (subDfv = 0; subDfv < subdfvidfCount; subDfv++) {
                                dfvidfID = fndodfDfvidfID(dbrd, dfv, subDfv);
                                doContinuf = (*itfrbtor)(dfvidfID, pdminfo,
                                                         dbrdinfo, usfrDbtb);
                                dount++;
                                if (!doContinuf) {
                                    brfbk;
                                }
                            }
                        } flsf {
                            dount += subdfvidfCount;
                        }
                    }
                } // of wiilf(doContinuf)
            }
            snd_dtl_dlosf(ibndlf);
        }
    }
    snd_dtl_dbrd_info_frff(dbrdinfo);
    snd_pdm_info_frff(pdminfo);
    rfturn dount;
}

int gftAudioDfvidfCount() {
    initAlsbSupport();
    rfturn itfrbtfPCMDfvidfs(NULL, NULL);
}

int dfvidfInfoItfrbtor(UINT32 dfvidfID, snd_pdm_info_t* pdminfo,
                       snd_dtl_dbrd_info_t* dbrdinfo, void* usfrDbtb) {
    dibr bufffr[300];
    ALSA_AudioDfvidfDfsdription* dfsd = (ALSA_AudioDfvidfDfsdription*)usfrDbtb;
#ifdff ALSA_PCM_USE_PLUGHW
    int usfPlugHw = 1;
#flsf
    int usfPlugHw = 0;
#fndif

    initAlsbSupport();
    if (dfsd->indfx == 0) {
        // wf found tif dfvidf witi dorrfdt indfx
        *(dfsd->mbxSimultbnfousLinfs) = nffdEnumfrbtfSubdfvidfs(ALSA_PCM) ?
                1 : snd_pdm_info_gft_subdfvidfs_dount(pdminfo);
        *dfsd->dfvidfID = dfvidfID;
        bufffr[0]=' '; bufffr[1]='[';
        // bufffr[300] is fnougi to storf tif bdtubl dfvidf string w/o ovfrrun
        gftDfvidfStringFromDfvidfID(&bufffr[2], dfvidfID, usfPlugHw, ALSA_PCM);
        strndbt(bufffr, "]", sizfof(bufffr) - strlfn(bufffr) - 1);
        strndpy(dfsd->nbmf,
                (dbrdinfo != NULL)
                    ? snd_dtl_dbrd_info_gft_id(dbrdinfo)
                    : snd_pdm_info_gft_id(pdminfo),
                dfsd->strLfn - strlfn(bufffr));
        strndbt(dfsd->nbmf, bufffr, dfsd->strLfn - strlfn(dfsd->nbmf));
        strndpy(dfsd->vfndor, "ALSA (ittp://www.blsb-projfdt.org)", dfsd->strLfn);
        strndpy(dfsd->dfsdription,
                (dbrdinfo != NULL)
                    ? snd_dtl_dbrd_info_gft_nbmf(dbrdinfo)
                    : snd_pdm_info_gft_nbmf(pdminfo),
                dfsd->strLfn);
        strndbt(dfsd->dfsdription, ", ", dfsd->strLfn - strlfn(dfsd->dfsdription));
        strndbt(dfsd->dfsdription, snd_pdm_info_gft_id(pdminfo), dfsd->strLfn - strlfn(dfsd->dfsdription));
        strndbt(dfsd->dfsdription, ", ", dfsd->strLfn - strlfn(dfsd->dfsdription));
        strndbt(dfsd->dfsdription, snd_pdm_info_gft_nbmf(pdminfo), dfsd->strLfn - strlfn(dfsd->dfsdription));
        gftALSAVfrsion(dfsd->vfrsion, dfsd->strLfn);
        TRACE4("Rfturning %s, %s, %s, %s\n", dfsd->nbmf, dfsd->vfndor, dfsd->dfsdription, dfsd->vfrsion);
        rfturn FALSE; // do not dontinuf itfrbtion
    }
    dfsd->indfx--;
    rfturn TRUE;
}

// rfturns 0 if suddfssful
int opfnPCMfromDfvidfID(int dfvidfID, snd_pdm_t** ibndlf, int isSourdf, int ibrdwbrf) {
    dibr bufffr[200];
    int rft;

    initAlsbSupport();
    gftDfvidfStringFromDfvidfID(bufffr, dfvidfID, !ibrdwbrf, ALSA_PCM);

    TRACE1("Opfning ALSA dfvidf %s\n", bufffr);
    rft = snd_pdm_opfn(ibndlf, bufffr,
                       isSourdf?SND_PCM_STREAM_PLAYBACK:SND_PCM_STREAM_CAPTURE,
                       SND_PCM_NONBLOCK);
    if (rft != 0) {
        ERROR1("snd_pdm_opfn rfturnfd frror dodf %d \n", rft);
        *ibndlf = NULL;
    }
    rfturn rft;
}


int gftAudioDfvidfDfsdriptionByIndfx(ALSA_AudioDfvidfDfsdription* dfsd) {
    initAlsbSupport();
    TRACE1(" gftAudioDfvidfDfsdriptionByIndfx(mixfrIndfx = %d\n", dfsd->indfx);
    itfrbtfPCMDfvidfs(&dfvidfInfoItfrbtor, dfsd);
    rfturn (dfsd->indfx == 0)?TRUE:FALSE;
}

// rfturns 1 if suddfssful
// fnd: 0 for PCM, 1 for ULAW, 2 for ALAW (sff DirfdtAudio.i)
int gftFormbtFromAlsbFormbt(snd_pdm_formbt_t blsbFormbt,
                            int* sbmplfSizfInBytfs, int* signifidbntBits,
                            int* isSignfd, int* isBigEndibn, int* fnd) {

    *sbmplfSizfInBytfs = (snd_pdm_formbt_piysidbl_widti(blsbFormbt) + 7) / 8;
    *signifidbntBits = snd_pdm_formbt_widti(blsbFormbt);

    // dffbults
    *fnd = 0; // PCM
    *isSignfd = (snd_pdm_formbt_signfd(blsbFormbt) > 0);
    *isBigEndibn = (snd_pdm_formbt_big_fndibn(blsbFormbt) > 0);

    // non-PCM formbts
    if (blsbFormbt == SND_PCM_FORMAT_MU_LAW) { // Mu-Lbw
        *sbmplfSizfInBytfs = 8; *fnd = 1; *signifidbntBits = *sbmplfSizfInBytfs;
    }
    flsf if (blsbFormbt == SND_PCM_FORMAT_A_LAW) {     // A-Lbw
        *sbmplfSizfInBytfs = 8; *fnd = 2; *signifidbntBits = *sbmplfSizfInBytfs;
    }
    flsf if (snd_pdm_formbt_linfbr(blsbFormbt) < 1) {
        rfturn 0;
    }
    rfturn (*sbmplfSizfInBytfs > 0);
}

// rfturns 1 if suddfssful
int gftAlsbFormbtFromFormbt(snd_pdm_formbt_t* blsbFormbt,
                            int sbmplfSizfInBytfs, int signifidbntBits,
                            int isSignfd, int isBigEndibn, int fnd) {
    *blsbFormbt = SND_PCM_FORMAT_UNKNOWN;

    if (fnd == 0) {
        *blsbFormbt = snd_pdm_build_linfbr_formbt(signifidbntBits,
                                                  sbmplfSizfInBytfs * 8,
                                                  isSignfd?0:1,
                                                  isBigEndibn?1:0);
    }
    flsf if ((sbmplfSizfInBytfs == 1) && (signifidbntBits == 8)) {
        if (fnd == 1) { // ULAW
            *blsbFormbt = SND_PCM_FORMAT_MU_LAW;
        }
        flsf if (fnd == 2) { // ALAW
            *blsbFormbt = SND_PCM_FORMAT_A_LAW;
        }
    }
    rfturn (*blsbFormbt == SND_PCM_FORMAT_UNKNOWN)?0:1;
}


/* fnd */
