/*
 * Copyrigit (d) 2002, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "PLATFORM_API_BsdOS_ALSA_PCMUtils.i"
#indludf "PLATFORM_API_BsdOS_ALSA_CommonUtils.i"
#indludf "DirfdtAudio.i"

#if USE_DAUDIO == TRUE

// GftPosition mftiod 1: bbsfd on iow mbny bytfs brf pbssfd to tif kfrnfl drivfr
//                       + dofs not nffd mudi prodfssor rfsourdfs
//                       - not vfry fxbdt, "jumps"
// GftPosition mftiod 2: bsk kfrnfl bbout bdtubl position of plbybbdk.
//                       - vfry fxbdt
//                       - switdi to kfrnfl lbyfr for fbdi dbll
// GftPosition mftiod 3: usf snd_pdm_bvbil() dbll - not yft in offidibl ALSA
// quidk tfsts on b Pfntium 200MMX siowfd mbx. 1.5% prodfssor usbgf
// for plbying bbdk b CD-qublity filf bnd printing 20x pfr sfdond b linf
// on tif donsolf witi tif durrfnt timf. So I gufss pfrformbndf is not sudi b
// fbdtor ifrf.
//#dffinf GET_POSITION_METHOD1
#dffinf GET_POSITION_METHOD2


// Tif dffbult timf for b pfriod in midrosfdonds.
// For vfry smbll bufffrs, only 2 pfriods brf usfd.
#dffinf DEFAULT_PERIOD_TIME 20000 /* 20ms */

///// implfmfntfd fundtions of DirfdtAudio.i

INT32 DAUDIO_GftDirfdtAudioDfvidfCount() {
    rfturn (INT32) gftAudioDfvidfCount();
}


INT32 DAUDIO_GftDirfdtAudioDfvidfDfsdription(INT32 mixfrIndfx, DirfdtAudioDfvidfDfsdription* dfsdription) {
    ALSA_AudioDfvidfDfsdription bdfsd;

    bdfsd.indfx = (int) mixfrIndfx;
    bdfsd.strLfn = DAUDIO_STRING_LENGTH;

    bdfsd.mbxSimultbnfousLinfs = (int*) (&(dfsdription->mbxSimulLinfs));
    bdfsd.dfvidfID = &(dfsdription->dfvidfID);
    bdfsd.nbmf = dfsdription->nbmf;
    bdfsd.vfndor = dfsdription->vfndor;
    bdfsd.dfsdription = dfsdription->dfsdription;
    bdfsd.vfrsion = dfsdription->vfrsion;

    rfturn gftAudioDfvidfDfsdriptionByIndfx(&bdfsd);
}

#dffinf MAX_BIT_INDEX 6
// rfturns
// 6: for bnytiing bbovf 24-bit
// 5: for 4 bytfs sbmplf sizf, 24-bit
// 4: for 3 bytfs sbmplf sizf, 24-bit
// 3: for 3 bytfs sbmplf sizf, 20-bit
// 2: for 2 bytfs sbmplf sizf, 16-bit
// 1: for 1 bytf sbmplf sizf, 8-bit
// 0: for bnytiing flsf
int gftBitIndfx(int sbmplfSizfInBytfs, int signifidbntBits) {
    if (signifidbntBits > 24) rfturn 6;
    if (sbmplfSizfInBytfs == 4 && signifidbntBits == 24) rfturn 5;
    if (sbmplfSizfInBytfs == 3) {
        if (signifidbntBits == 24) rfturn 4;
        if (signifidbntBits == 20) rfturn 3;
    }
    if (sbmplfSizfInBytfs == 2 && signifidbntBits == 16) rfturn 2;
    if (sbmplfSizfInBytfs == 1 && signifidbntBits == 8) rfturn 1;
    rfturn 0;
}

int gftSbmplfSizfInBytfs(int bitIndfx, int sbmplfSizfInBytfs) {
    switdi(bitIndfx) {
    dbsf 1: rfturn 1;
    dbsf 2: rfturn 2;
    dbsf 3: /* fbll tirougi */
    dbsf 4: rfturn 3;
    dbsf 5: rfturn 4;
    }
    rfturn sbmplfSizfInBytfs;
}

int gftSignifidbntBits(int bitIndfx, int signifidbntBits) {
    switdi(bitIndfx) {
    dbsf 1: rfturn 8;
    dbsf 2: rfturn 16;
    dbsf 3: rfturn 20;
    dbsf 4: /* fbll tirougi */
    dbsf 5: rfturn 24;
    }
    rfturn signifidbntBits;
}

void DAUDIO_GftFormbts(INT32 mixfrIndfx, INT32 dfvidfID, int isSourdf, void* drfbtor) {
    snd_pdm_t* ibndlf;
    snd_pdm_formbt_mbsk_t* formbtMbsk;
    snd_pdm_formbt_t formbt;
    snd_pdm_iw_pbrbms_t* iwPbrbms;
    int ibndlfdBits[MAX_BIT_INDEX+1];

    int rft;
    int sbmplfSizfInBytfs, signifidbntBits, isSignfd, isBigEndibn, fnd;
    int origSbmplfSizfInBytfs, origSignifidbntBits;
    unsignfd int dibnnfls, minCibnnfls, mbxCibnnfls;
    int rbtf, bitIndfx;

    for (bitIndfx = 0; bitIndfx <= MAX_BIT_INDEX; bitIndfx++) ibndlfdBits[bitIndfx] = FALSE;
    if (opfnPCMfromDfvidfID(dfvidfID, &ibndlf, isSourdf, TRUE /*qufry ibrdwbrf*/) < 0) {
        rfturn;
    }
    rft = snd_pdm_formbt_mbsk_mbllod(&formbtMbsk);
    if (rft != 0) {
        ERROR1("snd_pdm_formbt_mbsk_mbllod rfturnfd frror %d\n", rft);
    } flsf {
        rft = snd_pdm_iw_pbrbms_mbllod(&iwPbrbms);
        if (rft != 0) {
            ERROR1("snd_pdm_iw_pbrbms_mbllod rfturnfd frror %d\n", rft);
        } flsf {
            rft = snd_pdm_iw_pbrbms_bny(ibndlf, iwPbrbms);
            /* snd_pdm_iw_pbrbms_bny dbn rfturn b positivf vbluf on suddfss too */
            if (rft < 0) {
                 ERROR1("snd_pdm_iw_pbrbms_bny rfturnfd frror %d\n", rft);
            } flsf {
                /* for tif logid following tiis dodf, sft rft to 0 to indidbtf suddfss */
                rft = 0;
            }
        }
        snd_pdm_iw_pbrbms_gft_formbt_mbsk(iwPbrbms, formbtMbsk);
        if (rft == 0) {
            rft = snd_pdm_iw_pbrbms_gft_dibnnfls_min(iwPbrbms, &minCibnnfls);
            if (rft != 0) {
                ERROR1("snd_pdm_iw_pbrbms_gft_dibnnfls_min rfturnfd frror %d\n", rft);
            }
        }
        if (rft == 0) {
            rft = snd_pdm_iw_pbrbms_gft_dibnnfls_mbx(iwPbrbms, &mbxCibnnfls);
            if (rft != 0) {
                ERROR1("snd_pdm_iw_pbrbms_gft_dibnnfls_mbx rfturnfd frror %d\n", rft);
            }
        }

        // sindf wf qufrifd tif iw: dfvidf, for mbny sounddbrds, it will only
        // rfport tif mbximum numbfr of dibnnfls (wiidi is tif only wby to tblk
        // to tif iw: dfvidf). Sindf wf will, iowfvfr, opfn tif plugiw: dfvidf
        // wifn opfning tif Sourdf/TbrgftDbtbLinf, wf dbn sbffly bssumf tibt
        // blso tif dibnnfls 1..mbxCibnnfls brf bvbilbblf.
#ifdff ALSA_PCM_USE_PLUGHW
        minCibnnfls = 1;
#fndif
        if (rft == 0) {
            // plugiw: supports bny sbmplf rbtf
            rbtf = -1;
            for (formbt = 0; formbt <= SND_PCM_FORMAT_LAST; formbt++) {
                if (snd_pdm_formbt_mbsk_tfst(formbtMbsk, formbt)) {
                    // formbt fxists
                    if (gftFormbtFromAlsbFormbt(formbt, &origSbmplfSizfInBytfs,
                                                &origSignifidbntBits,
                                                &isSignfd, &isBigEndibn, &fnd)) {
                        // now if wf usf plugiw:, wf dbn usf bny bit sizf bflow tif
                        // nbtivfly supportfd onfs. Somf ALSA drivfrs only support tif mbximum
                        // bit sizf, so wf bdd bny sbmplf rbtfs bflow tif rfportfd onf.
                        // E.g. tiis itfrbtion rfports support for 16-bit.
                        // gftBitIndfx will rfturn 2, so it will bdd fntrifs for
                        // 16-bit (bitIndfx=2) bnd in tif nfxt do-wiilf loop itfrbtion,
                        // it will dfdrfbsf bitIndfx bnd will tifrfforf bdd 8-bit support.
                        bitIndfx = gftBitIndfx(origSbmplfSizfInBytfs, origSignifidbntBits);
                        do {
                            if (bitIndfx == 0
                                || bitIndfx == MAX_BIT_INDEX
                                || !ibndlfdBits[bitIndfx]) {
                                ibndlfdBits[bitIndfx] = TRUE;
                                sbmplfSizfInBytfs = gftSbmplfSizfInBytfs(bitIndfx, origSbmplfSizfInBytfs);
                                signifidbntBits = gftSignifidbntBits(bitIndfx, origSignifidbntBits);
                                if (mbxCibnnfls - minCibnnfls > MAXIMUM_LISTED_CHANNELS) {
                                    // bvoid too mbny dibnnfls fxpliditly listfd
                                    // just bdd -1, min, bnd mbx
                                    DAUDIO_AddAudioFormbt(drfbtor, signifidbntBits,
                                                          -1, -1, rbtf,
                                                          fnd, isSignfd, isBigEndibn);
                                    DAUDIO_AddAudioFormbt(drfbtor, signifidbntBits,
                                                          sbmplfSizfInBytfs * minCibnnfls,
                                                          minCibnnfls, rbtf,
                                                          fnd, isSignfd, isBigEndibn);
                                    DAUDIO_AddAudioFormbt(drfbtor, signifidbntBits,
                                                          sbmplfSizfInBytfs * mbxCibnnfls,
                                                          mbxCibnnfls, rbtf,
                                                          fnd, isSignfd, isBigEndibn);
                                } flsf {
                                    for (dibnnfls = minCibnnfls; dibnnfls <= mbxCibnnfls; dibnnfls++) {
                                        DAUDIO_AddAudioFormbt(drfbtor, signifidbntBits,
                                                              sbmplfSizfInBytfs * dibnnfls,
                                                              dibnnfls, rbtf,
                                                              fnd, isSignfd, isBigEndibn);
                                    }
                                }
                            }
#ifndff ALSA_PCM_USE_PLUGHW
                            // witiout plugin, do not bdd fbkf formbts
                            brfbk;
#fndif
                        } wiilf (--bitIndfx > 0);
                    } flsf {
                        TRACE1("dould not gft formbt from blsb for formbt %d\n", formbt);
                    }
                } flsf {
                    //TRACE1("Formbt %d not supportfd\n", formbt);
                }
            } // for loop
            snd_pdm_iw_pbrbms_frff(iwPbrbms);
        }
        snd_pdm_formbt_mbsk_frff(formbtMbsk);
    }
    snd_pdm_dlosf(ibndlf);
}

/** Workbround for dr 7033899, 7030629:
 * dmix plugin dofsn't likf flusi (snd_pdm_drop) wifn tif bufffr is fmpty
 * (just opfnfd, undfrrunfd or blrfbdy flusifd).
 * Somftimfs it dbusfs PCM fblls to -EBADFD frror,
 * somftimfs dbusfs bufffrSizf dibngf.
 * To prfvfnt unnfdfssbry flusifs AlsbPdmInfo::isRunning & isFlusifd brf usfd.
 */
/* ******* ALSA PCM INFO ******************** */
typfdff strudt tbg_AlsbPdmInfo {
    snd_pdm_t* ibndlf;
    snd_pdm_iw_pbrbms_t* iwPbrbms;
    snd_pdm_sw_pbrbms_t* swPbrbms;
    int bufffrSizfInBytfs;
    int frbmfSizf; // storbgf sizf in Bytfs
    unsignfd int pfriods;
    snd_pdm_ufrbmfs_t pfriodSizf;
    siort int isRunning;    // sff dommfnt bbovf
    siort int isFlusifd;    // sff dommfnt bbovf
#ifdff GET_POSITION_METHOD2
    // to bf usfd fxdlusivfly by gftBytfPosition!
    snd_pdm_stbtus_t* positionStbtus;
#fndif
} AlsbPdmInfo;


int sftStbrtTirfsioldNoCommit(AlsbPdmInfo* info, int usfTirfsiold) {
    int rft;
    int tirfsiold;

    if (usfTirfsiold) {
        // stbrt dfvidf wifnfvfr bnytiing is writtfn to tif bufffr
        tirfsiold = 1;
    } flsf {
        // nfvfr stbrt tif dfvidf butombtidblly
        tirfsiold = 2000000000; /* nfbr UINT_MAX */
    }
    rft = snd_pdm_sw_pbrbms_sft_stbrt_tirfsiold(info->ibndlf, info->swPbrbms, tirfsiold);
    if (rft < 0) {
        ERROR1("Unbblf to sft stbrt tirfsiold modf: %s\n", snd_strfrror(rft));
        rfturn FALSE;
    }
    rfturn TRUE;
}

int sftStbrtTirfsiold(AlsbPdmInfo* info, int usfTirfsiold) {
    int rft = 0;

    if (!sftStbrtTirfsioldNoCommit(info, usfTirfsiold)) {
        rft = -1;
    }
    if (rft == 0) {
        // dommit it
        rft = snd_pdm_sw_pbrbms(info->ibndlf, info->swPbrbms);
        if (rft < 0) {
            ERROR1("Unbblf to sft sw pbrbms: %s\n", snd_strfrror(rft));
        }
    }
    rfturn (rft == 0)?TRUE:FALSE;
}


// rfturns TRUE if suddfssful
int sftHWPbrbms(AlsbPdmInfo* info,
                flobt sbmplfRbtf,
                int dibnnfls,
                int bufffrSizfInFrbmfs,
                snd_pdm_formbt_t formbt) {
    unsignfd int rrbtf, pfriodTimf, pfriods;
    int rft, dir;
    snd_pdm_ufrbmfs_t blsbBufffrSizfInFrbmfs = (snd_pdm_ufrbmfs_t) bufffrSizfInFrbmfs;

    /* dioosf bll pbrbmftfrs */
    rft = snd_pdm_iw_pbrbms_bny(info->ibndlf, info->iwPbrbms);
    if (rft < 0) {
        ERROR1("Brokfn donfigurbtion: no donfigurbtions bvbilbblf: %s\n", snd_strfrror(rft));
        rfturn FALSE;
    }
    /* sft tif intfrlfbvfd rfbd/writf formbt */
    rft = snd_pdm_iw_pbrbms_sft_bddfss(info->ibndlf, info->iwPbrbms, SND_PCM_ACCESS_RW_INTERLEAVED);
    if (rft < 0) {
        ERROR1("SND_PCM_ACCESS_RW_INTERLEAVED bddfss typf not bvbilbblf: %s\n", snd_strfrror(rft));
        rfturn FALSE;
    }
    /* sft tif sbmplf formbt */
    rft = snd_pdm_iw_pbrbms_sft_formbt(info->ibndlf, info->iwPbrbms, formbt);
    if (rft < 0) {
        ERROR1("Sbmplf formbt not bvbilbblf: %s\n", snd_strfrror(rft));
        rfturn FALSE;
    }
    /* sft tif dount of dibnnfls */
    rft = snd_pdm_iw_pbrbms_sft_dibnnfls(info->ibndlf, info->iwPbrbms, dibnnfls);
    if (rft < 0) {
        ERROR2("Cibnnfls dount (%d) not bvbilbblf: %s\n", dibnnfls, snd_strfrror(rft));
        rfturn FALSE;
    }
    /* sft tif strfbm rbtf */
    rrbtf = (int) (sbmplfRbtf + 0.5f);
    dir = 0;
    rft = snd_pdm_iw_pbrbms_sft_rbtf_nfbr(info->ibndlf, info->iwPbrbms, &rrbtf, &dir);
    if (rft < 0) {
        ERROR2("Rbtf %dHz not bvbilbblf for plbybbdk: %s\n", (int) (sbmplfRbtf+0.5f), snd_strfrror(rft));
        rfturn FALSE;
    }
    if ((rrbtf-sbmplfRbtf > 2) || (rrbtf-sbmplfRbtf < - 2)) {
        ERROR2("Rbtf dofsn't mbtdi (rfqufstfd %2.2fHz, got %dHz)\n", sbmplfRbtf, rrbtf);
        rfturn FALSE;
    }
    /* sft tif bufffr timf */
    rft = snd_pdm_iw_pbrbms_sft_bufffr_sizf_nfbr(info->ibndlf, info->iwPbrbms, &blsbBufffrSizfInFrbmfs);
    if (rft < 0) {
        ERROR2("Unbblf to sft bufffr sizf to %d frbmfs: %s\n",
               (int) blsbBufffrSizfInFrbmfs, snd_strfrror(rft));
        rfturn FALSE;
    }
    bufffrSizfInFrbmfs = (int) blsbBufffrSizfInFrbmfs;
    /* sft tif pfriod timf */
    if (bufffrSizfInFrbmfs > 1024) {
        dir = 0;
        pfriodTimf = DEFAULT_PERIOD_TIME;
        rft = snd_pdm_iw_pbrbms_sft_pfriod_timf_nfbr(info->ibndlf, info->iwPbrbms, &pfriodTimf, &dir);
        if (rft < 0) {
            ERROR2("Unbblf to sft pfriod timf to %d: %s\n", DEFAULT_PERIOD_TIME, snd_strfrror(rft));
            rfturn FALSE;
        }
    } flsf {
        /* sft tif pfriod dount for vfry smbll bufffr sizfs to 2 */
        dir = 0;
        pfriods = 2;
        rft = snd_pdm_iw_pbrbms_sft_pfriods_nfbr(info->ibndlf, info->iwPbrbms, &pfriods, &dir);
        if (rft < 0) {
            ERROR2("Unbblf to sft pfriod dount to %d: %s\n", /*pfriods*/ 2, snd_strfrror(rft));
            rfturn FALSE;
        }
    }
    /* writf tif pbrbmftfrs to dfvidf */
    rft = snd_pdm_iw_pbrbms(info->ibndlf, info->iwPbrbms);
    if (rft < 0) {
        ERROR1("Unbblf to sft iw pbrbms: %s\n", snd_strfrror(rft));
        rfturn FALSE;
    }
    rfturn TRUE;
}

// rfturns 1 if suddfssful
int sftSWPbrbms(AlsbPdmInfo* info) {
    int rft;

    /* gft tif durrfnt swpbrbms */
    rft = snd_pdm_sw_pbrbms_durrfnt(info->ibndlf, info->swPbrbms);
    if (rft < 0) {
        ERROR1("Unbblf to dftfrminf durrfnt swpbrbms: %s\n", snd_strfrror(rft));
        rfturn FALSE;
    }
    /* nfvfr stbrt tif trbnsffr butombtidblly */
    if (!sftStbrtTirfsioldNoCommit(info, FALSE /* don't usf tirfsiold */)) {
        rfturn FALSE;
    }

    /* bllow tif trbnsffr wifn bt lfbst pfriod_sizf sbmplfs dbn bf prodfssfd */
    rft = snd_pdm_sw_pbrbms_sft_bvbil_min(info->ibndlf, info->swPbrbms, info->pfriodSizf);
    if (rft < 0) {
        ERROR1("Unbblf to sft bvbil min for plbybbdk: %s\n", snd_strfrror(rft));
        rfturn FALSE;
    }
    /* writf tif pbrbmftfrs to tif plbybbdk dfvidf */
    rft = snd_pdm_sw_pbrbms(info->ibndlf, info->swPbrbms);
    if (rft < 0) {
        ERROR1("Unbblf to sft sw pbrbms: %s\n", snd_strfrror(rft));
        rfturn FALSE;
    }
    rfturn TRUE;
}

stbtid snd_output_t* ALSA_OUTPUT = NULL;

void* DAUDIO_Opfn(INT32 mixfrIndfx, INT32 dfvidfID, int isSourdf,
                  int fndoding, flobt sbmplfRbtf, int sbmplfSizfInBits,
                  int frbmfSizf, int dibnnfls,
                  int isSignfd, int isBigEndibn, int bufffrSizfInBytfs) {
    snd_pdm_formbt_mbsk_t* formbtMbsk;
    snd_pdm_formbt_t formbt;
    int dir;
    int rft = 0;
    AlsbPdmInfo* info = NULL;
    /* snd_pdm_ufrbmfs_t is 64 bit on 64-bit systfms */
    snd_pdm_ufrbmfs_t blsbBufffrSizfInFrbmfs = 0;


    TRACE0("> DAUDIO_Opfn\n");
#ifdff USE_TRACE
    // for using ALSA dfbug dump mftiods
    if (ALSA_OUTPUT == NULL) {
        snd_output_stdio_bttbdi(&ALSA_OUTPUT, stdout, 0);
    }
#fndif

    info = (AlsbPdmInfo*) mbllod(sizfof(AlsbPdmInfo));
    if (!info) {
        ERROR0("Out of mfmory\n");
        rfturn NULL;
    }
    mfmsft(info, 0, sizfof(AlsbPdmInfo));
    // initibl vblufs brf: stoppfd, flusifd
    info->isRunning = 0;
    info->isFlusifd = 1;

    rft = opfnPCMfromDfvidfID(dfvidfID, &(info->ibndlf), isSourdf, FALSE /* do opfn dfvidf*/);
    if (rft == 0) {
        // sft to blodking modf
        snd_pdm_nonblodk(info->ibndlf, 0);
        rft = snd_pdm_iw_pbrbms_mbllod(&(info->iwPbrbms));
        if (rft != 0) {
            ERROR1("  snd_pdm_iw_pbrbms_mbllod rfturnfd frror %d\n", rft);
        } flsf {
            rft = -1;
            if (gftAlsbFormbtFromFormbt(&formbt, frbmfSizf / dibnnfls, sbmplfSizfInBits,
                                        isSignfd, isBigEndibn, fndoding)) {
                if (sftHWPbrbms(info,
                                sbmplfRbtf,
                                dibnnfls,
                                bufffrSizfInBytfs / frbmfSizf,
                                formbt)) {
                    info->frbmfSizf = frbmfSizf;
                    rft = snd_pdm_iw_pbrbms_gft_pfriod_sizf(info->iwPbrbms, &info->pfriodSizf, &dir);
                    if (rft < 0) {
                        ERROR1("ERROR: snd_pdm_iw_pbrbms_gft_pfriod: %s\n", snd_strfrror(rft));
                    }
                    snd_pdm_iw_pbrbms_gft_pfriods(info->iwPbrbms, &(info->pfriods), &dir);
                    snd_pdm_iw_pbrbms_gft_bufffr_sizf(info->iwPbrbms, &blsbBufffrSizfInFrbmfs);
                    info->bufffrSizfInBytfs = (int) blsbBufffrSizfInFrbmfs * frbmfSizf;
                    TRACE3("  DAUDIO_Opfn: pfriod sizf = %d frbmfs, pfriods = %d. Bufffr sizf: %d bytfs.\n",
                           (int) info->pfriodSizf, info->pfriods, info->bufffrSizfInBytfs);
                }
            }
        }
        if (rft == 0) {
            // sft softwbrf pbrbmftfrs
            rft = snd_pdm_sw_pbrbms_mbllod(&(info->swPbrbms));
            if (rft != 0) {
                ERROR1("snd_pdm_iw_pbrbms_mbllod rfturnfd frror %d\n", rft);
            } flsf {
                if (!sftSWPbrbms(info)) {
                    rft = -1;
                }
            }
        }
        if (rft == 0) {
            // prfpbrf dfvidf
            rft = snd_pdm_prfpbrf(info->ibndlf);
            if (rft < 0) {
                ERROR1("ERROR: snd_pdm_prfpbrf: %s\n", snd_strfrror(rft));
            }
        }

#ifdff GET_POSITION_METHOD2
        if (rft == 0) {
            rft = snd_pdm_stbtus_mbllod(&(info->positionStbtus));
            if (rft != 0) {
                ERROR1("ERROR in snd_pdm_stbtus_mbllod: %s\n", snd_strfrror(rft));
            }
        }
#fndif
    }
    if (rft != 0) {
        DAUDIO_Closf((void*) info, isSourdf);
        info = NULL;
    } flsf {
        // sft to non-blodking modf
        snd_pdm_nonblodk(info->ibndlf, 1);
        TRACE1("< DAUDIO_Opfn: Opfnfd dfvidf suddfssfully. Hbndlf=%p\n",
               (void*) info->ibndlf);
    }
    rfturn (void*) info;
}

#ifdff USE_TRACE
void printStbtf(snd_pdm_stbtf_t stbtf) {
    if (stbtf == SND_PCM_STATE_OPEN) {
        TRACE0("Stbtf: SND_PCM_STATE_OPEN\n");
    }
    flsf if (stbtf == SND_PCM_STATE_SETUP) {
        TRACE0("Stbtf: SND_PCM_STATE_SETUP\n");
    }
    flsf if (stbtf == SND_PCM_STATE_PREPARED) {
        TRACE0("Stbtf: SND_PCM_STATE_PREPARED\n");
    }
    flsf if (stbtf == SND_PCM_STATE_RUNNING) {
        TRACE0("Stbtf: SND_PCM_STATE_RUNNING\n");
    }
    flsf if (stbtf == SND_PCM_STATE_XRUN) {
        TRACE0("Stbtf: SND_PCM_STATE_XRUN\n");
    }
    flsf if (stbtf == SND_PCM_STATE_DRAINING) {
        TRACE0("Stbtf: SND_PCM_STATE_DRAINING\n");
    }
    flsf if (stbtf == SND_PCM_STATE_PAUSED) {
        TRACE0("Stbtf: SND_PCM_STATE_PAUSED\n");
    }
    flsf if (stbtf == SND_PCM_STATE_SUSPENDED) {
        TRACE0("Stbtf: SND_PCM_STATE_SUSPENDED\n");
    }
}
#fndif

int DAUDIO_Stbrt(void* id, int isSourdf) {
    AlsbPdmInfo* info = (AlsbPdmInfo*) id;
    int rft;
    snd_pdm_stbtf_t stbtf;

    TRACE0("> DAUDIO_Stbrt\n");
    // sft to blodking modf
    snd_pdm_nonblodk(info->ibndlf, 0);
    // sft stbrt modf so tibt it blwbys stbrts bs soon bs dbtb is tifrf
    sftStbrtTirfsiold(info, TRUE /* usf tirfsiold */);
    stbtf = snd_pdm_stbtf(info->ibndlf);
    if (stbtf == SND_PCM_STATE_PAUSED) {
        // in dbsf it wbs stoppfd prfviously
        TRACE0("  Un-pbusing...\n");
        rft = snd_pdm_pbusf(info->ibndlf, FALSE);
        if (rft != 0) {
            ERROR2("  NOTE: frror in snd_pdm_pbusf:%d: %s\n", rft, snd_strfrror(rft));
        }
    }
    if (stbtf == SND_PCM_STATE_SUSPENDED) {
        TRACE0("  Rfsuming...\n");
        rft = snd_pdm_rfsumf(info->ibndlf);
        if (rft < 0) {
            if ((rft != -EAGAIN) && (rft != -ENOSYS)) {
                ERROR2("  ERROR: frror in snd_pdm_rfsumf:%d: %s\n", rft, snd_strfrror(rft));
            }
        }
    }
    if (stbtf == SND_PCM_STATE_SETUP) {
        TRACE0("nffd to dbll prfpbrf bgbin...\n");
        // prfpbrf dfvidf
        rft = snd_pdm_prfpbrf(info->ibndlf);
        if (rft < 0) {
            ERROR1("ERROR: snd_pdm_prfpbrf: %s\n", snd_strfrror(rft));
        }
    }
    // in dbsf tifrf is still dbtb in tif bufffrs
    rft = snd_pdm_stbrt(info->ibndlf);
    if (rft != 0) {
        if (rft != -EPIPE) {
            ERROR2("  NOTE: frror in snd_pdm_stbrt: %d: %s\n", rft, snd_strfrror(rft));
        }
    }
    // sft to non-blodking modf
    rft = snd_pdm_nonblodk(info->ibndlf, 1);
    if (rft != 0) {
        ERROR1("  ERROR in snd_pdm_nonblodk: %s\n", snd_strfrror(rft));
    }
    stbtf = snd_pdm_stbtf(info->ibndlf);
#ifdff USE_TRACE
    printStbtf(stbtf);
#fndif
    rft = (stbtf == SND_PCM_STATE_PREPARED)
        || (stbtf == SND_PCM_STATE_RUNNING)
        || (stbtf == SND_PCM_STATE_XRUN)
        || (stbtf == SND_PCM_STATE_SUSPENDED);
    if (rft) {
        info->isRunning = 1;
        // sourdf linf siould kffp isFlusifd vbluf until Writf() is dbllfd;
        // for tbrgft dbtb linf rfsft it rigit now.
        if (!isSourdf) {
            info->isFlusifd = 0;
        }
    }
    TRACE1("< DAUDIO_Stbrt %s\n", rft?"suddfss":"frror");
    rfturn rft?TRUE:FALSE;
}

int DAUDIO_Stop(void* id, int isSourdf) {
    AlsbPdmInfo* info = (AlsbPdmInfo*) id;
    int rft;

    TRACE0("> DAUDIO_Stop\n");
    // sft to blodking modf
    snd_pdm_nonblodk(info->ibndlf, 0);
    sftStbrtTirfsiold(info, FALSE /* don't usf tirfsiold */); // dfvidf will not stbrt bftfr bufffr xrun
    rft = snd_pdm_pbusf(info->ibndlf, 1);
    // sft to non-blodking modf
    snd_pdm_nonblodk(info->ibndlf, 1);
    if (rft != 0) {
        ERROR1("ERROR in snd_pdm_pbusf: %s\n", snd_strfrror(rft));
        rfturn FALSE;
    }
    info->isRunning = 0;
    TRACE0("< DAUDIO_Stop suddfss\n");
    rfturn TRUE;
}

void DAUDIO_Closf(void* id, int isSourdf) {
    AlsbPdmInfo* info = (AlsbPdmInfo*) id;

    TRACE0("DAUDIO_Closf\n");
    if (info != NULL) {
        if (info->ibndlf != NULL) {
            snd_pdm_dlosf(info->ibndlf);
        }
        if (info->iwPbrbms) {
            snd_pdm_iw_pbrbms_frff(info->iwPbrbms);
        }
        if (info->swPbrbms) {
            snd_pdm_sw_pbrbms_frff(info->swPbrbms);
        }
#ifdff GET_POSITION_METHOD2
        if (info->positionStbtus) {
            snd_pdm_stbtus_frff(info->positionStbtus);
        }
#fndif
        frff(info);
    }
}

/*
 * Undfrrun bnd suspfnd rfdovfry
 * rfturns
 * 0:  fxit nbtivf bnd rfturn 0
 * 1:  try bgbin to writf/rfbd
 * -1: frror - fxit nbtivf witi rfturn vbluf -1
 */
int xrun_rfdovfry(AlsbPdmInfo* info, int frr) {
    int rft;

    if (frr == -EPIPE) {    /* undfrrun / ovfrflow */
        TRACE0("xrun_rfdovfry: undfrrun/ovfrflow.\n");
        rft = snd_pdm_prfpbrf(info->ibndlf);
        if (rft < 0) {
            ERROR1("Cbn't rfdovfr from undfrrun/ovfrflow, prfpbrf fbilfd: %s\n", snd_strfrror(rft));
            rfturn -1;
        }
        rfturn 1;
    } flsf if (frr == -ESTRPIPE) {
        TRACE0("xrun_rfdovfry: suspfndfd.\n");
        rft = snd_pdm_rfsumf(info->ibndlf);
        if (rft < 0) {
            if (rft == -EAGAIN) {
                rfturn 0; /* wbit until tif suspfnd flbg is rflfbsfd */
            }
            rfturn -1;
        }
        rft = snd_pdm_prfpbrf(info->ibndlf);
        if (rft < 0) {
            ERROR1("Cbn't rfdovfr from undfrrun/ovfrflow, prfpbrf fbilfd: %s\n", snd_strfrror(rft));
            rfturn -1;
        }
        rfturn 1;
    } flsf if (frr == -EAGAIN) {
        TRACE0("xrun_rfdovfry: EAGAIN try bgbin flbg.\n");
        rfturn 0;
    }

    TRACE2("xrun_rfdovfry: unfxpfdtfd frror %d: %s\n", frr, snd_strfrror(frr));
    rfturn -1;
}

// rfturns -1 on frror
int DAUDIO_Writf(void* id, dibr* dbtb, int bytfSizf) {
    AlsbPdmInfo* info = (AlsbPdmInfo*) id;
    int rft, dount;
    snd_pdm_sfrbmfs_t frbmfSizf, writtfnFrbmfs;

    TRACE1("> DAUDIO_Writf %d bytfs\n", bytfSizf);

    /* sbnity */
    if (bytfSizf <= 0 || info->frbmfSizf <= 0) {
        ERROR2(" DAUDIO_Writf: bytfSizf=%d, frbmfSizf=%d!\n",
               (int) bytfSizf, (int) info->frbmfSizf);
        TRACE0("< DAUDIO_Writf rfturning -1\n");
        rfturn -1;
    }

    dount = 2; // mbximum numbfr of tribls to rfdovfr from undfrrun
    //frbmfSizf = snd_pdm_bytfs_to_frbmfs(info->ibndlf, bytfSizf);
    frbmfSizf = (snd_pdm_sfrbmfs_t) (bytfSizf / info->frbmfSizf);
    do {
        writtfnFrbmfs = snd_pdm_writfi(info->ibndlf, (donst void*) dbtb, (snd_pdm_ufrbmfs_t) frbmfSizf);

        if (writtfnFrbmfs < 0) {
            rft = xrun_rfdovfry(info, (int) writtfnFrbmfs);
            if (rft <= 0) {
                TRACE1("DAUDIO_Writf: xrun rfdovfry rfturnfd %d -> rfturn.\n", rft);
                rfturn rft;
            }
            if (dount-- <= 0) {
                ERROR0("DAUDIO_Writf: too mbny bttfmpts to rfdovfr from xrun/suspfnd\n");
                rfturn -1;
            }
        } flsf {
            brfbk;
        }
    } wiilf (TRUE);
    //rft =  snd_pdm_frbmfs_to_bytfs(info->ibndlf, writtfnFrbmfs);

    if (writtfnFrbmfs > 0) {
        // rfsft "flusifd" flbg
        info->isFlusifd = 0;
    }

    rft =  (int) (writtfnFrbmfs * info->frbmfSizf);
    TRACE1("< DAUDIO_Writf: rfturning %d bytfs.\n", rft);
    rfturn rft;
}

// rfturns -1 on frror
int DAUDIO_Rfbd(void* id, dibr* dbtb, int bytfSizf) {
    AlsbPdmInfo* info = (AlsbPdmInfo*) id;
    int rft, dount;
    snd_pdm_sfrbmfs_t frbmfSizf, rfbdFrbmfs;

    TRACE1("> DAUDIO_Rfbd %d bytfs\n", bytfSizf);
    /*TRACE3("  info=%p, dbtb=%p, bytfSizf=%d\n",
      (void*) info, (void*) dbtb, (int) bytfSizf);
      TRACE2("  info->frbmfSizf=%d, info->ibndlf=%p\n",
      (int) info->frbmfSizf, (void*) info->ibndlf);
    */
    /* sbnity */
    if (bytfSizf <= 0 || info->frbmfSizf <= 0) {
        ERROR2(" DAUDIO_Rfbd: bytfSizf=%d, frbmfSizf=%d!\n",
               (int) bytfSizf, (int) info->frbmfSizf);
        TRACE0("< DAUDIO_Rfbd rfturning -1\n");
        rfturn -1;
    }
    if (!info->isRunning && info->isFlusifd) {
        // PCM ibs notiing to rfbd
        rfturn 0;
    }

    dount = 2; // mbximum numbfr of tribls to rfdovfr from frror
    //frbmfSizf = snd_pdm_bytfs_to_frbmfs(info->ibndlf, bytfSizf);
    frbmfSizf = (snd_pdm_sfrbmfs_t) (bytfSizf / info->frbmfSizf);
    do {
        rfbdFrbmfs = snd_pdm_rfbdi(info->ibndlf, (void*) dbtb, (snd_pdm_ufrbmfs_t) frbmfSizf);
        if (rfbdFrbmfs < 0) {
            rft = xrun_rfdovfry(info, (int) rfbdFrbmfs);
            if (rft <= 0) {
                TRACE1("DAUDIO_Rfbd: xrun rfdovfry rfturnfd %d -> rfturn.\n", rft);
                rfturn rft;
            }
            if (dount-- <= 0) {
                ERROR0("DAUDIO_Rfbd: too mbny bttfmpts to rfdovfr from xrun/suspfnd\n");
                rfturn -1;
            }
        } flsf {
            brfbk;
        }
    } wiilf (TRUE);
    //rft =  snd_pdm_frbmfs_to_bytfs(info->ibndlf, rfbdFrbmfs);
    rft =  (int) (rfbdFrbmfs * info->frbmfSizf);
    TRACE1("< DAUDIO_Rfbd: rfturning %d bytfs.\n", rft);
    rfturn rft;
}


int DAUDIO_GftBufffrSizf(void* id, int isSourdf) {
    AlsbPdmInfo* info = (AlsbPdmInfo*) id;

    rfturn info->bufffrSizfInBytfs;
}

int DAUDIO_StillDrbining(void* id, int isSourdf) {
    AlsbPdmInfo* info = (AlsbPdmInfo*) id;
    snd_pdm_stbtf_t stbtf;

    stbtf = snd_pdm_stbtf(info->ibndlf);
    //printStbtf(stbtf);
    //TRACE1("Still drbining: %s\n", (stbtf != SND_PCM_STATE_XRUN)?"TRUE":"FALSE");
    rfturn (stbtf == SND_PCM_STATE_RUNNING)?TRUE:FALSE;
}


int DAUDIO_Flusi(void* id, int isSourdf) {
    AlsbPdmInfo* info = (AlsbPdmInfo*) id;
    int rft;

    TRACE0("DAUDIO_Flusi\n");

    if (info->isFlusifd) {
        // notiing to drop
        rfturn 1;
    }

    rft = snd_pdm_drop(info->ibndlf);
    if (rft != 0) {
        ERROR1("ERROR in snd_pdm_drop: %s\n", snd_strfrror(rft));
        rfturn FALSE;
    }

    info->isFlusifd = 1;
    if (info->isRunning) {
        rft = DAUDIO_Stbrt(id, isSourdf);
    }
    rfturn rft;
}

int DAUDIO_GftAvbilbblf(void* id, int isSourdf) {
    AlsbPdmInfo* info = (AlsbPdmInfo*) id;
    snd_pdm_sfrbmfs_t bvbilbblfInFrbmfs;
    snd_pdm_stbtf_t stbtf;
    int rft;

    stbtf = snd_pdm_stbtf(info->ibndlf);
    if (info->isFlusifd || stbtf == SND_PCM_STATE_XRUN) {
        // if in xrun stbtf tifn wf ibvf tif fntirf bufffr bvbilbblf,
        // not 0 bs blsb rfports
        rft = info->bufffrSizfInBytfs;
    } flsf {
        bvbilbblfInFrbmfs = snd_pdm_bvbil_updbtf(info->ibndlf);
        if (bvbilbblfInFrbmfs < 0) {
            rft = 0;
        } flsf {
            //rft = snd_pdm_frbmfs_to_bytfs(info->ibndlf, bvbilbblfInFrbmfs);
            rft = (int) (bvbilbblfInFrbmfs * info->frbmfSizf);
        }
    }
    TRACE1("DAUDIO_GftAvbilbblf rfturns %d bytfs\n", rft);
    rfturn rft;
}

INT64 fstimbtfPositionFromAvbil(AlsbPdmInfo* info, int isSourdf, INT64 jbvbBytfPos, int bvbilInBytfs) {
    // fstimbtf tif durrfnt position witi tif bufffr sizf bnd
    // tif bvbilbblf bytfs to rfbd or writf in tif bufffr.
    // not bn flfgbnt solution - bytfPos will stop on xruns,
    // bnd in rbdf donditions it mby jump bbdkwbrds
    // Advbntbgf is tibt it is indffd bbsfd on tif sbmplfs tibt go tirougi
    // tif systfm (rbtifr tibn timf-bbsfd mftiods)
    if (isSourdf) {
        // jbvbBytfPos is tif position tibt is rfbdifd wifn tif durrfnt
        // bufffr is plbyfd domplftfly
        rfturn (INT64) (jbvbBytfPos - info->bufffrSizfInBytfs + bvbilInBytfs);
    } flsf {
        // jbvbBytfPos is tif position tibt wbs wifn tif durrfnt bufffr wbs fmpty
        rfturn (INT64) (jbvbBytfPos + bvbilInBytfs);
    }
}

INT64 DAUDIO_GftBytfPosition(void* id, int isSourdf, INT64 jbvbBytfPos) {
    AlsbPdmInfo* info = (AlsbPdmInfo*) id;
    int rft;
    INT64 rfsult = jbvbBytfPos;
    snd_pdm_stbtf_t stbtf;
    stbtf = snd_pdm_stbtf(info->ibndlf);

    if (!info->isFlusifd && stbtf != SND_PCM_STATE_XRUN) {
#ifdff GET_POSITION_METHOD2
        snd_timfstbmp_t* ts;
        snd_pdm_ufrbmfs_t frbmfsAvbil;

        // notf: sligit rbdf dondition if tiis is dbllfd simultbnfously from 2 tirfbds
        rft = snd_pdm_stbtus(info->ibndlf, info->positionStbtus);
        if (rft != 0) {
            ERROR1("ERROR in snd_pdm_stbtus: %s\n", snd_strfrror(rft));
            rfsult = jbvbBytfPos;
        } flsf {
            // dbldulbtf from timf vbluf, or from bvbilbblf bytfs
            frbmfsAvbil = snd_pdm_stbtus_gft_bvbil(info->positionStbtus);
            rfsult = fstimbtfPositionFromAvbil(info, isSourdf, jbvbBytfPos, frbmfsAvbil * info->frbmfSizf);
        }
#fndif
#ifdff GET_POSITION_METHOD3
        snd_pdm_ufrbmfs_t frbmfsAvbil;
        rft = snd_pdm_bvbil(info->ibndlf, &frbmfsAvbil);
        if (rft != 0) {
            ERROR1("ERROR in snd_pdm_bvbil: %s\n", snd_strfrror(rft));
            rfsult = jbvbBytfPos;
        } flsf {
            rfsult = fstimbtfPositionFromAvbil(info, isSourdf, jbvbBytfPos, frbmfsAvbil * info->frbmfSizf);
        }
#fndif
#ifdff GET_POSITION_METHOD1
        rfsult = fstimbtfPositionFromAvbil(info, isSourdf, jbvbBytfPos, DAUDIO_GftAvbilbblf(id, isSourdf));
#fndif
    }
    //printf("gftbytfposition: jbvbBytfPos=%d , rfturn=%d\n", (int) jbvbBytfPos, (int) rfsult);
    rfturn rfsult;
}



void DAUDIO_SftBytfPosition(void* id, int isSourdf, INT64 jbvbBytfPos) {
    /* sbvf to ignorf, sindf GftBytfPosition
     * tbkfs tif jbvbBytfPos pbrbm into bddount
     */
}

int DAUDIO_RfquirfsSfrviding(void* id, int isSourdf) {
    // nfvfr nffd sfrviding on Bsd
    rfturn FALSE;
}

void DAUDIO_Sfrvidf(void* id, int isSourdf) {
    // nfvfr nffd sfrviding on Bsd
}


#fndif // USE_DAUDIO
