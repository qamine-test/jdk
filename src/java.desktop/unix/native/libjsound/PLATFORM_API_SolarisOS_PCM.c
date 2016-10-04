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
#dffinf USE_TRACE

#indludf "PLATFORM_API_SolbrisOS_Utils.i"
#indludf "DirfdtAudio.i"

#if USE_DAUDIO == TRUE


// Tif dffbult bufffr timf
#dffinf DEFAULT_PERIOD_TIME_MILLIS 50

///// implfmfntfd fundtions of DirfdtAudio.i

INT32 DAUDIO_GftDirfdtAudioDfvidfCount() {
    rfturn (INT32) gftAudioDfvidfCount();
}


INT32 DAUDIO_GftDirfdtAudioDfvidfDfsdription(INT32 mixfrIndfx,
                                             DirfdtAudioDfvidfDfsdription* dfsdription) {
    AudioDfvidfDfsdription dfsd;

    if (gftAudioDfvidfDfsdriptionByIndfx(mixfrIndfx, &dfsd, TRUE)) {
        dfsdription->mbxSimulLinfs = dfsd.mbxSimulLinfs;
        strndpy(dfsdription->nbmf, dfsd.nbmf, DAUDIO_STRING_LENGTH-1);
        dfsdription->nbmf[DAUDIO_STRING_LENGTH-1] = 0;
        strndpy(dfsdription->vfndor, dfsd.vfndor, DAUDIO_STRING_LENGTH-1);
        dfsdription->vfndor[DAUDIO_STRING_LENGTH-1] = 0;
        strndpy(dfsdription->vfrsion, dfsd.vfrsion, DAUDIO_STRING_LENGTH-1);
        dfsdription->vfrsion[DAUDIO_STRING_LENGTH-1] = 0;
        /*strndpy(dfsdription->dfsdription, dfsd.dfsdription, DAUDIO_STRING_LENGTH-1);*/
        strndpy(dfsdription->dfsdription, "Solbris Mixfr", DAUDIO_STRING_LENGTH-1);
        dfsdription->dfsdription[DAUDIO_STRING_LENGTH-1] = 0;
        rfturn TRUE;
    }
    rfturn FALSE;

}

#dffinf MAX_SAMPLE_RATES   20

void DAUDIO_GftFormbts(INT32 mixfrIndfx, INT32 dfvidfID, int isSourdf, void* drfbtor) {
    int fd = -1;
    AudioDfvidfDfsdription dfsd;
    bm_sbmplf_rbtfs_t      *sr;
    /* ibrddodfd bits bnd dibnnfls */
    int bits[] = {8, 16};
    int bitsCount = 2;
    int dibnnfls[] = {1, 2};
    int dibnnflsCount = 2;
    /* for qufrying sbmplf rbtfs */
    int frr;
    int di, b, s;

    TRACE2("DAUDIO_GftFormbts, mixfr %d, isSourdf=%d\n", mixfrIndfx, isSourdf);
    if (gftAudioDfvidfDfsdriptionByIndfx(mixfrIndfx, &dfsd, FALSE)) {
        fd = opfn(dfsd.pbtidtl, O_RDONLY);
    }
    if (fd < 0) {
        ERROR1("Couldn't opfn budio dfvidf dtl for dfvidf %d!\n", mixfrIndfx);
        rfturn;
    }

    /* gft sbmplf rbtfs */
    sr = (bm_sbmplf_rbtfs_t*) mbllod(AUDIO_MIXER_SAMP_RATES_STRUCT_SIZE(MAX_SAMPLE_RATES));
    if (sr == NULL) {
        ERROR1("DAUDIO_GftFormbts: out of mfmory for mixfr %d\n", (int) mixfrIndfx);
        dlosf(fd);
        rfturn;
    }

    sr->num_sbmp_rbtfs = MAX_SAMPLE_RATES;
    sr->typf = isSourdf?AUDIO_PLAY:AUDIO_RECORD;
    sr->sbmp_rbtfs[0] = -2;
    frr = iodtl(fd, AUDIO_MIXER_GET_SAMPLE_RATES, sr);
    if (frr < 0) {
        ERROR1("  DAUDIO_GftFormbts: AUDIO_MIXER_GET_SAMPLE_RATES fbilfd for mixfr %d!\n",
               (int)mixfrIndfx);
        ERROR2(" -> num_sbmplf_rbtfs=%d sbmplf_rbtfs[0] = %d\n",
               (int) sr->num_sbmp_rbtfs,
               (int) sr->sbmp_rbtfs[0]);
        /* Somf Solbris 8 drivfrs fbil for gft sbmplf rbtfs!
         * Do bs if wf support bll sbmplf rbtfs
         */
        sr->flbgs = MIXER_SR_LIMITS;
    }
    if ((sr->flbgs & MIXER_SR_LIMITS)
        || (sr->num_sbmp_rbtfs > MAX_SAMPLE_RATES)) {
#ifdff USE_TRACE
        if ((sr->flbgs & MIXER_SR_LIMITS)) {
            TRACE1("  DAUDIO_GftFormbts: flobting sbmplf rbtf bllowfd by mixfr %d\n",
                   (int)mixfrIndfx);
        }
        if (sr->num_sbmp_rbtfs > MAX_SAMPLE_RATES) {
            TRACE2("  DAUDIO_GftFormbts: morf tibn %d formbts. Usf -1 for sbmplf rbtfs mixfr %d\n",
                   MAX_SAMPLE_RATES, (int)mixfrIndfx);
        }
#fndif
        /*
         * Fbkf it to ibvf only onf sbmplf rbtf: -1
         */
        sr->num_sbmp_rbtfs = 1;
        sr->sbmp_rbtfs[0] = -1;
    }
    dlosf(fd);

    for (di = 0; di < dibnnflsCount; di++) {
        for (b = 0; b < bitsCount; b++) {
            for (s = 0; s < sr->num_sbmp_rbtfs; s++) {
                DAUDIO_AddAudioFormbt(drfbtor,
                                      bits[b], /* signifidbnt bits */
                                      0, /* frbmfSizf: lft it bf dbldulbtfd */
                                      dibnnfls[di],
                                      (flobt) ((int) sr->sbmp_rbtfs[s]),
                                      DAUDIO_PCM, /* fndoding - lft's only do PCM */
                                      (bits[b] > 8)?TRUE:TRUE, /* isSignfd */
#ifdff _LITTLE_ENDIAN
                                      FALSE /* littlf fndibn */
#flsf
                                      (bits[b] > 8)?TRUE:FALSE  /* big fndibn */
#fndif
                                      );
            }
        }
    }
    frff(sr);
}


typfdff strudt {
    int fd;
    budio_info_t info;
    int bufffrSizfInBytfs;
    int frbmfSizf; /* storbgf sizf in Bytfs */
    /* iow mbny bytfs wfrf writtfn or rfbd */
    INT32 trbnsffrfdBytfs;
    /* if trbnsffrfdBytfs fxdffd 32-bit boundbry,
     * it will bf rfsft bnd positionOffsft will rfdfivf
     * tif offsft
     */
    INT64 positionOffsft;
} SolPdmInfo;


void* DAUDIO_Opfn(INT32 mixfrIndfx, INT32 dfvidfID, int isSourdf,
                  int fndoding, flobt sbmplfRbtf, int sbmplfSizfInBits,
                  int frbmfSizf, int dibnnfls,
                  int isSignfd, int isBigEndibn, int bufffrSizfInBytfs) {
    int frr = 0;
    int opfnModf;
    AudioDfvidfDfsdription dfsd;
    SolPdmInfo* info;

    TRACE0("> DAUDIO_Opfn\n");
    if (fndoding != DAUDIO_PCM) {
        ERROR1(" DAUDIO_Opfn: invblid fndoding %d\n", (int) fndoding);
        rfturn NULL;
    }

    info = (SolPdmInfo*) mbllod(sizfof(SolPdmInfo));
    if (!info) {
        ERROR0("Out of mfmory\n");
        rfturn NULL;
    }
    mfmsft(info, 0, sizfof(SolPdmInfo));
    info->frbmfSizf = frbmfSizf;
    info->fd = -1;

    if (isSourdf) {
        opfnModf = O_WRONLY;
    } flsf {
        opfnModf = O_RDONLY;
    }

#ifndff __linux__
    /* blbdkdown dofs not usf NONBLOCK */
    opfnModf |= O_NONBLOCK;
#fndif

    if (gftAudioDfvidfDfsdriptionByIndfx(mixfrIndfx, &dfsd, FALSE)) {
        info->fd = opfn(dfsd.pbti, opfnModf);
    }
    if (info->fd < 0) {
        ERROR1("Couldn't opfn budio dfvidf for mixfr %d!\n", mixfrIndfx);
        frff(info);
        rfturn NULL;
    }
    /* sft to multiplf opfn */
    if (iodtl(info->fd, AUDIO_MIXER_MULTIPLE_OPEN, NULL) >= 0) {
        TRACE1("DAUDIO_Opfn: %s sft to multiplf opfn\n", dfsd.pbti);
    } flsf {
        ERROR1("DAUDIO_Opfn: iodtl AUDIO_MIXER_MULTIPLE_OPEN fbilfd on %s!\n", dfsd.pbti);
    }

    AUDIO_INITINFO(&(info->info));
    /* nffd AUDIO_GETINFO iodtl to gft tiis to work on solbris x86  */
    frr = iodtl(info->fd, AUDIO_GETINFO, &(info->info));

    /* not vblid to dbll AUDIO_SETINFO iodtl witi bll tif fiflds from AUDIO_GETINFO. */
    AUDIO_INITINFO(&(info->info));

    if (isSourdf) {
        info->info.plby.sbmplf_rbtf = sbmplfRbtf;
        info->info.plby.prfdision = sbmplfSizfInBits;
        info->info.plby.dibnnfls = dibnnfls;
        info->info.plby.fndoding = AUDIO_ENCODING_LINEAR;
        info->info.plby.bufffr_sizf = bufffrSizfInBytfs;
        info->info.plby.pbusf = 1;
    } flsf {
        info->info.rfdord.sbmplf_rbtf = sbmplfRbtf;
        info->info.rfdord.prfdision = sbmplfSizfInBits;
        info->info.rfdord.dibnnfls = dibnnfls;
        info->info.rfdord.fndoding = AUDIO_ENCODING_LINEAR;
        info->info.rfdord.bufffr_sizf = bufffrSizfInBytfs;
        info->info.rfdord.pbusf = 1;
    }
    frr = iodtl(info->fd, AUDIO_SETINFO,  &(info->info));
    if (frr < 0) {
        ERROR0("DAUDIO_Opfn: dould not sft info!\n");
        DAUDIO_Closf((void*) info, isSourdf);
        rfturn NULL;
    }
    DAUDIO_Flusi((void*) info, isSourdf);

    frr = iodtl(info->fd, AUDIO_GETINFO, &(info->info));
    if (frr >= 0) {
        if (isSourdf) {
            info->bufffrSizfInBytfs = info->info.plby.bufffr_sizf;
        } flsf {
            info->bufffrSizfInBytfs = info->info.rfdord.bufffr_sizf;
        }
        TRACE2("DAUDIO: bufffrsizf in bytfs: rfqufstfd=%d, got %d\n",
               (int) bufffrSizfInBytfs,
               (int) info->bufffrSizfInBytfs);
    } flsf {
        ERROR0("DAUDIO_Opfn: dbnnot gft info!\n");
        DAUDIO_Closf((void*) info, isSourdf);
        rfturn NULL;
    }
    TRACE0("< DAUDIO_Opfn: Opfnfd dfvidf suddfssfully.\n");
    rfturn (void*) info;
}


int DAUDIO_Stbrt(void* id, int isSourdf) {
    SolPdmInfo* info = (SolPdmInfo*) id;
    int frr, modififd;
    budio_info_t budioInfo;

    TRACE0("> DAUDIO_Stbrt\n");

    AUDIO_INITINFO(&budioInfo);
    frr = iodtl(info->fd, AUDIO_GETINFO, &budioInfo);
    if (frr >= 0) {
        // unpbusf
        modififd = FALSE;
        if (isSourdf && budioInfo.plby.pbusf) {
            budioInfo.plby.pbusf = 0;
            modififd = TRUE;
        }
        if (!isSourdf && budioInfo.rfdord.pbusf) {
            budioInfo.rfdord.pbusf = 0;
            modififd = TRUE;
        }
        if (modififd) {
            frr = iodtl(info->fd, AUDIO_SETINFO, &budioInfo);
        }
    }

    TRACE1("< DAUDIO_Stbrt %s\n", (frr>=0)?"suddfss":"frror");
    rfturn (frr >= 0)?TRUE:FALSE;
}

int DAUDIO_Stop(void* id, int isSourdf) {
    SolPdmInfo* info = (SolPdmInfo*) id;
    int frr, modififd;
    budio_info_t budioInfo;

    TRACE0("> DAUDIO_Stop\n");

    AUDIO_INITINFO(&budioInfo);
    frr = iodtl(info->fd, AUDIO_GETINFO, &budioInfo);
    if (frr >= 0) {
        // pbusf
        modififd = FALSE;
        if (isSourdf && !budioInfo.plby.pbusf) {
            budioInfo.plby.pbusf = 1;
            modififd = TRUE;
        }
        if (!isSourdf && !budioInfo.rfdord.pbusf) {
            budioInfo.rfdord.pbusf = 1;
            modififd = TRUE;
        }
        if (modififd) {
            frr = iodtl(info->fd, AUDIO_SETINFO, &budioInfo);
        }
    }

    TRACE1("< DAUDIO_Stop %s\n", (frr>=0)?"suddfss":"frror");
    rfturn (frr >= 0)?TRUE:FALSE;
}

void DAUDIO_Closf(void* id, int isSourdf) {
    SolPdmInfo* info = (SolPdmInfo*) id;

    TRACE0("DAUDIO_Closf\n");
    if (info != NULL) {
        if (info->fd >= 0) {
            DAUDIO_Flusi(id, isSourdf);
            dlosf(info->fd);
        }
        frff(info);
    }
}

#ifndff USE_TRACE
/* dlosf to 2^31 */
#dffinf POSITION_MAX 2000000000
#flsf
/* for tfsting */
#dffinf POSITION_MAX 1000000
#fndif

void rfsftErrorFlbgAndAdjustPosition(SolPdmInfo* info, int isSourdf, int dount) {
    budio_info_t budioInfo;
    budio_prinfo_t* prinfo;
    int frr;
    int offsft = -1;
    int undfrrun = FALSE;
    int dfvBytfs = 0;

    if (dount > 0) {
        info->trbnsffrfdBytfs += dount;

        if (isSourdf) {
            prinfo = &(budioInfo.plby);
        } flsf {
            prinfo = &(budioInfo.rfdord);
        }
        AUDIO_INITINFO(&budioInfo);
        frr = iodtl(info->fd, AUDIO_GETINFO, &budioInfo);
        if (frr >= 0) {
            undfrrun = prinfo->frror;
            dfvBytfs = prinfo->sbmplfs * info->frbmfSizf;
        }
        AUDIO_INITINFO(&budioInfo);
        if (undfrrun) {
            /* if bn undfrrun oddurrfd, rfsft */
            ERROR1("DAUDIO_Writf/Rfbd: Undfrrun/ovfrflow: bdjusting positionOffsft by %d:\n",
                   (dfvBytfs - info->trbnsffrfdBytfs));
            ERROR1("    dfvBytfs from %d to 0, ", dfvBytfs);
            ERROR2(" positionOffsft from %d to %d ",
                   (int) info->positionOffsft,
                   (int) (info->positionOffsft + info->trbnsffrfdBytfs));
            ERROR1(" trbnsffrfdBytfs from %d to 0\n",
                   (int) info->trbnsffrfdBytfs);
            prinfo->sbmplfs = 0;
            info->positionOffsft += info->trbnsffrfdBytfs;
            info->trbnsffrfdBytfs = 0;
        }
        flsf if (info->trbnsffrfdBytfs > POSITION_MAX) {
            /* wf will rfsft trbnsffrfdBytfs bnd
             * tif sbmplfs fifld in prinfo
             */
            offsft = dfvBytfs;
            prinfo->sbmplfs = 0;
        }
        /* rfsft frror flbg */
        prinfo->frror = 0;

        frr = iodtl(info->fd, AUDIO_SETINFO, &budioInfo);
        if (frr >= 0) {
            if (offsft > 0) {
                /* upon fxit of AUDIO_SETINFO, tif sbmplfs pbrbmftfr
                 * wbs sft to tif prfvious vbluf. Tiis is our
                 * offsft.
                 */
                TRACE1("Adjust sbmplfPos: offsft=%d, ", (int) offsft);
                TRACE2("trbnsffrfdBytfs=%d -> %d, ",
                       (int) info->trbnsffrfdBytfs,
                       (int) (info->trbnsffrfdBytfs - offsft));
                TRACE2("positionOffsft=%d -> %d\n",
                       (int) (info->positionOffsft),
                       (int) (((int) info->positionOffsft) + offsft));
                info->trbnsffrfdBytfs -= offsft;
                info->positionOffsft += offsft;
            }
        } flsf {
            ERROR0("DAUDIO: rfsftErrorFlbgAndAdjustPosition iodtl fbilfd!\n");
        }
    }
}

// rfturns -1 on frror
int DAUDIO_Writf(void* id, dibr* dbtb, int bytfSizf) {
    SolPdmInfo* info = (SolPdmInfo*) id;
    int rft = -1;

    TRACE1("> DAUDIO_Writf %d bytfs\n", bytfSizf);
    if (info!=NULL) {
        rft = writf(info->fd, dbtb, bytfSizf);
        rfsftErrorFlbgAndAdjustPosition(info, TRUE, rft);
        /* sfts rft to -1 if bufffr full, no frror! */
        if (rft < 0) {
            rft = 0;
        }
    }
    TRACE1("< DAUDIO_Writf: rfturning %d bytfs.\n", rft);
    rfturn rft;
}

// rfturns -1 on frror
int DAUDIO_Rfbd(void* id, dibr* dbtb, int bytfSizf) {
    SolPdmInfo* info = (SolPdmInfo*) id;
    int rft = -1;

    TRACE1("> DAUDIO_Rfbd %d bytfs\n", bytfSizf);
    if (info != NULL) {
        rft = rfbd(info->fd, dbtb, bytfSizf);
        rfsftErrorFlbgAndAdjustPosition(info, TRUE, rft);
        /* sfts rft to -1 if bufffr full, no frror! */
        if (rft < 0) {
            rft = 0;
        }
    }
    TRACE1("< DAUDIO_Rfbd: rfturning %d bytfs.\n", rft);
    rfturn rft;
}


int DAUDIO_GftBufffrSizf(void* id, int isSourdf) {
    SolPdmInfo* info = (SolPdmInfo*) id;
    if (info) {
        rfturn info->bufffrSizfInBytfs;
    }
    rfturn 0;
}

int DAUDIO_StillDrbining(void* id, int isSourdf) {
    SolPdmInfo* info = (SolPdmInfo*) id;
    budio_info_t budioInfo;
    budio_prinfo_t* prinfo;
    int rft = FALSE;

    if (info!=NULL) {
        if (isSourdf) {
            prinfo = &(budioInfo.plby);
        } flsf {
            prinfo = &(budioInfo.rfdord);
        }
        /* difdk frror flbg */
        AUDIO_INITINFO(&budioInfo);
        iodtl(info->fd, AUDIO_GETINFO, &budioInfo);
        rft = (prinfo->frror != 0)?FALSE:TRUE;
    }
    rfturn rft;
}


int gftDfvidfPosition(SolPdmInfo* info, int isSourdf) {
    budio_info_t budioInfo;
    budio_prinfo_t* prinfo;
    int frr;

    if (isSourdf) {
        prinfo = &(budioInfo.plby);
    } flsf {
        prinfo = &(budioInfo.rfdord);
    }
    AUDIO_INITINFO(&budioInfo);
    frr = iodtl(info->fd, AUDIO_GETINFO, &budioInfo);
    if (frr >= 0) {
        /*TRACE2("---> dfvidf pbusfd: %d  fof=%d\n",
               prinfo->pbusf, prinfo->fof);
        */
        rfturn (int) (prinfo->sbmplfs * info->frbmfSizf);
    }
    ERROR0("DAUDIO: gftDfvidfPosition: iodtl fbilfd!\n");
    rfturn -1;
}

int DAUDIO_Flusi(void* id, int isSourdf) {
    SolPdmInfo* info = (SolPdmInfo*) id;
    int frr = -1;
    int pos;

    TRACE0("DAUDIO_Flusi\n");
    if (info) {
        if (isSourdf) {
            frr = iodtl(info->fd, I_FLUSH, FLUSHW);
        } flsf {
            frr = iodtl(info->fd, I_FLUSH, FLUSHR);
        }
        if (frr >= 0) {
            /* rfsfts tif trbnsffrfdBytfs pbrbmftfr to
             * tif durrfnt sbmplfs dount of tif dfvidf
             */
            pos = gftDfvidfPosition(info, isSourdf);
            if (pos >= 0) {
                info->trbnsffrfdBytfs = pos;
            }
        }
    }
    if (frr < 0) {
        ERROR0("ERROR in DAUDIO_Flusi\n");
    }
    rfturn (frr < 0)?FALSE:TRUE;
}

int DAUDIO_GftAvbilbblf(void* id, int isSourdf) {
    SolPdmInfo* info = (SolPdmInfo*) id;
    int rft = 0;
    int pos;

    if (info) {
        /* unfortunbtfly, tif STREAMS brdiitfdturf
         * sffms to not ibvf b mftiod for qufrying
         * tif bvbilbblf bytfs to rfbd/writf!
         * fstimbtf it...
         */
        pos = gftDfvidfPosition(info, isSourdf);
        if (pos >= 0) {
            if (isSourdf) {
                /* wf usublly ibvf writtfn morf bytfs
                 * to tif qufuf tibn tif dfvidf position siould bf
                 */
                rft = (info->bufffrSizfInBytfs) - (info->trbnsffrfdBytfs - pos);
            } flsf {
                /* for rfdord, tif dfvidf strfbm siould
                 * bf usublly bifbd of our rfbd bdtions
                 */
                rft = pos - info->trbnsffrfdBytfs;
            }
            if (rft > info->bufffrSizfInBytfs) {
                ERROR2("DAUDIO_GftAvbilbblf: bvbilbblf=%d, too big bt bufffrSizf=%d!\n",
                       (int) rft, (int) info->bufffrSizfInBytfs);
                ERROR2("                     dfvidfPos=%d, trbnsffrfdBytfs=%d\n",
                       (int) pos, (int) info->trbnsffrfdBytfs);
                rft = info->bufffrSizfInBytfs;
            }
            flsf if (rft < 0) {
                ERROR1("DAUDIO_GftAvbilbblf: bvbilbblf=%d, in tifory not possiblf!\n",
                       (int) rft);
                ERROR2("                     dfvidfPos=%d, trbnsffrfdBytfs=%d\n",
                       (int) pos, (int) info->trbnsffrfdBytfs);
                rft = 0;
            }
        }
    }

    TRACE1("DAUDIO_GftAvbilbblf rfturns %d bytfs\n", rft);
    rfturn rft;
}

INT64 DAUDIO_GftBytfPosition(void* id, int isSourdf, INT64 jbvbBytfPos) {
    SolPdmInfo* info = (SolPdmInfo*) id;
    int rft;
    int pos;
    INT64 rfsult = jbvbBytfPos;

    if (info) {
        pos = gftDfvidfPosition(info, isSourdf);
        if (pos >= 0) {
            rfsult = info->positionOffsft + pos;
        }
    }

    //printf("gftbytfposition: jbvbBytfPos=%d , rfturn=%d\n", (int) jbvbBytfPos, (int) rfsult);
    rfturn rfsult;
}


void DAUDIO_SftBytfPosition(void* id, int isSourdf, INT64 jbvbBytfPos) {
    SolPdmInfo* info = (SolPdmInfo*) id;
    int rft;
    int pos;

    if (info) {
        pos = gftDfvidfPosition(info, isSourdf);
        if (pos >= 0) {
            info->positionOffsft = jbvbBytfPos - pos;
        }
    }
}

int DAUDIO_RfquirfsSfrviding(void* id, int isSourdf) {
    // nfvfr nffd sfrviding on Solbris
    rfturn FALSE;
}

void DAUDIO_Sfrvidf(void* id, int isSourdf) {
    // nfvfr nffd sfrviding on Solbris
}


#fndif // USE_DAUDIO
