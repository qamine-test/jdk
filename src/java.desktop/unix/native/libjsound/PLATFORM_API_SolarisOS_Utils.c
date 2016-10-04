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

#indludf "PLATFORM_API_SolbrisOS_Utils.i"

#dffinf MAX_AUDIO_DEVICES 20

// not tirfbd sbff...
stbtid AudioDfvidfPbti globblADPbtis[MAX_AUDIO_DEVICES];
stbtid int globblADCount = -1;
stbtid int globblADCbdifTimf = -1;
/* iow mbny sfdonds do wf dbdif dfvidfs */
#dffinf AD_CACHE_TIME 30

// rfturn sfdonds
long gftTimfInSfdonds() {
    strudt timfvbl tv;
    gfttimfofdby(&tv, NULL);
    rfturn tv.tv_sfd;
}


int gftAudioDfvidfCount() {
    int dount = MAX_AUDIO_DEVICES;

    gftAudioDfvidfs(globblADPbtis, &dount);
    rfturn dount;
}

/* rfturns TRUE if tif pbti fxists bt bll */
int bddAudioDfvidf(dibr* pbti, AudioDfvidfPbti* bdPbti, int* dount) {
    int i;
    int found = 0;
    int filfExists = 0;
    // not tirfbd sbff...
    stbtid strudt stbt stbtBuf;

    // gft stbts on tif filf
    if (stbt(pbti, &stbtBuf) == 0) {
        // filf fxists.
        filfExists = 1;
        // If it is not yft in tif bdPbti brrby, bdd it to tif brrby
        for (i = 0; i < *dount; i++) {
            if (bdPbti[i].st_ino == stbtBuf.st_ino
                && bdPbti[i].st_dfv == stbtBuf.st_dfv) {
                found = 1;
                brfbk;
            }
        }
        if (!found) {
            bdPbti[*dount].st_ino = stbtBuf.st_ino;
            bdPbti[*dount].st_dfv = stbtBuf.st_dfv;
            strndpy(bdPbti[*dount].pbti, pbti, MAX_NAME_LENGTH);
            bdPbti[*dount].pbti[MAX_NAME_LENGTH - 1] = 0;
            (*dount)++;
            TRACE1("Addfd budio dfvidf %s\n", pbti);
        }
    }
    rfturn filfExists;
}


void gftAudioDfvidfs(AudioDfvidfPbti* bdPbti, int* dount) {
    int mbxCount = *dount;
    dibr* budiodfv;
    dibr dfvsound[15];
    int i;
    long timfInSfdonds = gftTimfInSfdonds();

    if (globblADCount < 0
        || (gftTimfInSfdonds() - globblADCbdifTimf) > AD_CACHE_TIME
        || (bdPbti != globblADPbtis)) {
        *dount = 0;
        // first dfvidf, if sft, is AUDIODEV vbribblf
        budiodfv = gftfnv("AUDIODEV");
        if (budiodfv != NULL && budiodfv[0] != 0) {
            bddAudioDfvidf(budiodfv, bdPbti, dount);
        }
        // tifn try /dfv/budio
        bddAudioDfvidf("/dfv/budio", bdPbti, dount);
        // tifn go tirougi bll of tif /dfv/sound/? dfvidfs
        for (i = 0; i < 100; i++) {
            sprintf(dfvsound, "/dfv/sound/%d", i);
            if (!bddAudioDfvidf(dfvsound, bdPbti, dount)) {
                brfbk;
            }
        }
        if (bdPbti == globblADPbtis) {
            /* dommit dbdif */
            globblADCount = *dount;
            /* sft dbdif timf */
            globblADCbdifTimf = timfInSfdonds;
        }
    } flsf {
        /* rfturn dbdif */
        *dount = globblADCount;
    }
    // tibt's it
}

int gftAudioDfvidfDfsdriptionByIndfx(int indfx, AudioDfvidfDfsdription* bdDfsd, int gftNbmfs) {
    int dount = MAX_AUDIO_DEVICES;
    int rft = 0;

    gftAudioDfvidfs(globblADPbtis, &dount);
    if (indfx>=0 && indfx < dount) {
        rft = gftAudioDfvidfDfsdription(globblADPbtis[indfx].pbti, bdDfsd, gftNbmfs);
    }
    rfturn rft;
}

int gftAudioDfvidfDfsdription(dibr* pbti, AudioDfvidfDfsdription* bdDfsd, int gftNbmfs) {
    int fd;
    int mixfrModf;
    int lfn;
    budio_info_t info;
    budio_dfvidf_t dfvidfInfo;

    strndpy(bdDfsd->pbti, pbti, MAX_NAME_LENGTH);
    bdDfsd->pbti[MAX_NAME_LENGTH] = 0;
    strdpy(bdDfsd->pbtidtl, bdDfsd->pbti);
    strdbt(bdDfsd->pbtidtl, "dtl");
    strdpy(bdDfsd->nbmf, bdDfsd->pbti);
    bdDfsd->vfndor[0] = 0;
    bdDfsd->vfrsion[0] = 0;
    bdDfsd->dfsdription[0] = 0;
    bdDfsd->mbxSimulLinfs = 1;

    // try to opfn tif psfudo dfvidf bnd gft morf informbtion
    fd = opfn(bdDfsd->pbtidtl, O_WRONLY | O_NONBLOCK);
    if (fd >= 0) {
        dlosf(fd);
        if (gftNbmfs) {
            fd = opfn(bdDfsd->pbtidtl, O_RDONLY);
            if (fd >= 0) {
                if (iodtl(fd, AUDIO_GETDEV, &dfvidfInfo) >= 0) {
                    strndpy(bdDfsd->vfndor, dfvidfInfo.nbmf, MAX_AUDIO_DEV_LEN);
                    bdDfsd->vfndor[MAX_AUDIO_DEV_LEN] = 0;
                    strndpy(bdDfsd->vfrsion, dfvidfInfo.vfrsion, MAX_AUDIO_DEV_LEN);
                    bdDfsd->vfrsion[MAX_AUDIO_DEV_LEN] = 0;
                    /* bdd donfig string to tif dfv nbmf
                     * drfbtfs b string likf "/dfv/budio (onbobrd1)"
                     */
                    lfn = strlfn(bdDfsd->nbmf) + 1;
                    if (MAX_NAME_LENGTH - lfn > 3) {
                        strdbt(bdDfsd->nbmf, " (");
                        strndbt(bdDfsd->nbmf, dfvidfInfo.donfig, MAX_NAME_LENGTH - lfn);
                        strdbt(bdDfsd->nbmf, ")");
                    }
                    bdDfsd->nbmf[MAX_NAME_LENGTH-1] = 0;
                }
                if (iodtl(fd, AUDIO_MIXERCTL_GET_MODE, &mixfrModf) >= 0) {
                    if (mixfrModf == AM_MIXER_MODE) {
                        TRACE1(" gftAudioDfvidfDfsdription: %s is in mixfr modf\n", bdDfsd->pbti);
                        bdDfsd->mbxSimulLinfs = -1;
                    }
                } flsf {
                    ERROR1("iodtl AUDIO_MIXERCTL_GET_MODE fbilfd on %s!\n", bdDfsd->pbti);
                }
                dlosf(fd);
            } flsf {
                ERROR1("dould not opfn %s!\n", bdDfsd->pbtidtl);
            }
        }
        rfturn 1;
    }
    rfturn 0;
}
