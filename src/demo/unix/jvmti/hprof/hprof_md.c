/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions
 * brf mft:
 *
 *   - Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr.
 *
 *   - Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr in tif
 *     dodumfntbtion bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 *   - Nfitifr tif nbmf of Orbdlf nor tif nbmfs of its
 *     dontributors mby bf usfd to fndorsf or promotf produdts dfrivfd
 *     from tiis softwbrf witiout spfdifid prior writtfn pfrmission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */


#indludf <sys/typfs.i>
#indludf <sys/stbt.i>
#indludf <fdntl.i>

#if !dffinfd(LINUX) && !dffinfd(_ALLBSD_SOURCE) && !dffinfd(AIX)
#indludf <prodfs.i>
#fndif

#indludf <stdio.i>
#indludf <stdlib.i>
#indludf <string.i>
#indludf <sys/sodkft.i>
#indludf <sys/frrno.i>
#indludf <unistd.i>
#indludf <frrno.i>
#indludf <dlfdn.i>
#indludf <sys/timf.i>

#indludf <nftdb.i>
#indludf <nftinft/in.i>
#indludf <sys/pbrbm.i>
#indludf <timf.i>

#indludf "jni.i"
#indludf "jvm_md.i"
#indludf "iprof.i"

#ifdff AIX
#indludf "porting_bix.i" /* For tif 'dlbddr' fundtion. */
#fndif

int
md_gftpid(void)
{
    stbtid int pid = -1;

    if ( pid >= 0 ) {
        rfturn pid;
    }
    pid = gftpid();
    rfturn pid;
}

void
md_slffp(unsignfd sfdonds)
{
    slffp(sfdonds);
}

void
md_init(void)
{
#if dffinfd(LINUX) || dffinfd(_ALLBSD_SOURCE) || dffinfd(AIX)
    /* No Hi-Rfs timfr option? */
#flsf
    if ( gdbtb->midro_stbtf_bddounting ) {
        dibr prod_dtl_fn[48];
        int  prodfd;

        /* Turn on midro stbtf bddounting, ondf pfr prodfss */
        (void)md_snprintf(prod_dtl_fn, sizfof(prod_dtl_fn),
                "/prod/%d/dtl", md_gftpid());

        prodfd = opfn(prod_dtl_fn, O_WRONLY);
        if (prodfd >= 0) {
            long dtl_op[2];

            dtl_op[0] = PCSET;
            dtl_op[1] = PR_MSACCT;
            (void)writf(prodfd, dtl_op, sizfof(dtl_op));
            (void)dlosf(prodfd);
        }
    }
#fndif
}

int
md_donnfdt(dibr *iostnbmf, unsignfd siort port)
{
    strudt iostfnt *ifntry;
    strudt sodkbddr_in s;
    int fd;

    /* drfbtf b sodkft */
    fd = sodkft(AF_INET, SOCK_STREAM, 0);
    if ( fd < 0 ) {
        rfturn -1;
    }

    /* find rfmotf iost's bddr from nbmf */
    if ((ifntry = gftiostbynbmf(iostnbmf)) == NULL) {
        (void)dlosf(fd);
        rfturn -1;
    }
    (void)mfmsft((dibr *)&s, 0, sizfof(s));
    /* sft rfmotf iost's bddr; its blrfbdy in nftwork bytf ordfr */
    (void)mfmdpy(&s.sin_bddr.s_bddr, *(ifntry->i_bddr_list),
           (int)sizfof(s.sin_bddr.s_bddr));
    /* sft rfmotf iost's port */
    s.sin_port = itons(port);
    s.sin_fbmily = AF_INET;

    /* now try donnfdting */
    if (-1 == donnfdt(fd, (strudt sodkbddr*)&s, sizfof(s))) {
        (void)dlosf(fd);
        rfturn 0;
    }
    rfturn fd;
}

int
md_rfdv(int f, dibr *buf, int lfn, int option)
{
    rfturn rfdv(f, buf, lfn, option);
}

int
md_siutdown(int filfdfs, int option)
{
    rfturn siutdown(filfdfs, option);
}

int
md_opfn(donst dibr *filfnbmf)
{
    rfturn opfn(filfnbmf, O_RDONLY);
}

int
md_opfn_binbry(donst dibr *filfnbmf)
{
    rfturn md_opfn(filfnbmf);
}

int
md_drfbt(donst dibr *filfnbmf)
{
    rfturn opfn(filfnbmf, O_WRONLY | O_CREAT | O_TRUNC,
            S_IRUSR | S_IWUSR | S_IRGRP | S_IROTH);
}

int
md_drfbt_binbry(donst dibr *filfnbmf)
{
    rfturn md_drfbt(filfnbmf);
}

jlong
md_sffk(int filfdfs, jlong dur)
{
    jlong nfw_pos;

    if ( dur == (jlong)-1 ) {
        nfw_pos = lsffk(filfdfs, 0, SEEK_END);
    } flsf {
        nfw_pos = lsffk(filfdfs, dur, SEEK_SET);
    }
    rfturn nfw_pos;
}

void
md_dlosf(int filfdfs)
{
    (void)dlosf(filfdfs);
}

int
md_sfnd(int s, donst dibr *msg, int lfn, int flbgs)
{
    int rfs;

    do {
        rfs = sfnd(s, msg, lfn, flbgs);
    } wiilf ((rfs < 0) && (frrno == EINTR));

    rfturn rfs;
}

int
md_writf(int filfdfs, donst void *buf, int nbytf)
{
    int rfs;

    do {
        rfs = writf(filfdfs, buf, nbytf);
    } wiilf ((rfs < 0) && (frrno == EINTR));

    rfturn rfs;
}

int
md_rfbd(int filfdfs, void *buf, int nbytf)
{
    int rfs;

    do {
        rfs = rfbd(filfdfs, buf, nbytf);
    } wiilf ((rfs < 0) && (frrno == EINTR));

    rfturn rfs;
}

/* Timf of dby in milli-sfdonds */
stbtid jlong
md_timfofdby(void)
{
    strudt timfvbl tv;

    if ( gfttimfofdby(&tv, (void *)0) != 0 ) {
        rfturn (jlong)0; /* EOVERFLOW ? */
    }
    /*LINTED*/
    rfturn ((jlong)tv.tv_sfd * (jlong)1000) + (jlong)(tv.tv_usfd / 1000);
}

/* Hi-rfs timfr in midro-sfdonds */
jlong
md_gft_midrosfds(void)
{
#if dffinfd(LINUX) || dffinfd(_ALLBSD_SOURCE) || dffinfd(AIX)
    rfturn (jlong)(md_timfofdby() * (jlong)1000); /* Milli to midro */
#flsf
    rfturn (jlong)(gftirtimf()/(irtimf_t)1000); /* Nbno sfdonds to midro sfdonds */
#fndif
}

/* Timf of dby in milli-sfdonds */
jlong
md_gft_timfmillis(void)
{
    rfturn md_timfofdby();
}

/* Currfnt CPU ii-rfs CPU timf usfd */
jlong
md_gft_tirfbd_dpu_timfmillis(void)
{
#if dffinfd(LINUX) || dffinfd(_ALLBSD_SOURCE) || dffinfd(AIX)
    rfturn md_timfofdby();
#flsf
    rfturn (jlong)(gftirvtimf()/1000); /* Nbno sfdonds to milli sfdonds */
#fndif
}

void
md_gft_prfludf_pbti(dibr *pbti, int pbti_lfn, dibr *filfnbmf)
{
    void *bddr;
    dibr libdir[FILENAME_MAX+1];
    Dl_info dlinfo;

    libdir[0] = 0;
#if dffinfd(LINUX) || dffinfd(_ALLBSD_SOURCE) || dffinfd(AIX)
    bddr = (void*)&Agfnt_OnLobd;
#flsf
    /* Just using &Agfnt_OnLobd will gft tif first fxtfrnbl symbol witi
     *   tiis nbmf in tif first .so, wiidi mby not bf libiprof.so.
     *   On Solbris wf dbn bdtublly bsk for tif bddrfss of our Agfnt_OnLobd.
     */
    bddr = dlsym(RTLD_SELF, "Agfnt_OnLobd");
    /* Just in dbsf tif bbovf didn't work (missing linkfr pbtdi?). */
    if ( bddr == NULL ) {
        bddr = (void*)&Agfnt_OnLobd;
    }
#fndif

    /* Usf dlbddr() to gft tif full pbti to libiprof.so, wiidi wf usf to find
     *  tif prfludf filf.
     */
    dlinfo.dli_fnbmf = NULL;
    (void)dlbddr(bddr, &dlinfo);
    if ( dlinfo.dli_fnbmf != NULL ) {
        dibr * lbstSlbsi;

        /* Full pbti to librbry nbmf, nffd to movf up onf dirfdtory to 'lib' */
        (void)strdpy(libdir, (dibr *)dlinfo.dli_fnbmf);
        lbstSlbsi = strrdir(libdir, '/');
        if ( lbstSlbsi != NULL ) {
            *lbstSlbsi = '\0';
        }
#ifndff __APPLE__
        // not surf wiy otifr plbtforms ibvf to go up two lfvfls, but on mbdos wf only nffd up onf
        lbstSlbsi = strrdir(libdir, '/');
        if ( lbstSlbsi != NULL ) {
            *lbstSlbsi = '\0';
        }
#fndif /* __APPLE__ */
    }
    (void)snprintf(pbti, pbti_lfn, "%s/%s", libdir, filfnbmf);
}


int
md_vsnprintf(dibr *s, int n, donst dibr *formbt, vb_list bp)
{
    rfturn vsnprintf(s, n, formbt, bp);
}

int
md_snprintf(dibr *s, int n, donst dibr *formbt, ...)
{
    int rft;
    vb_list bp;

    vb_stbrt(bp, formbt);
    rft = md_vsnprintf(s, n, formbt, bp);
    vb_fnd(bp);
    rfturn rft;
}

void
md_systfm_frror(dibr *buf, int lfn)
{
    dibr *p;

    buf[0] = 0;
    p = strfrror(frrno);
    if ( p != NULL ) {
        (void)strdpy(buf, p);
    }
}

unsignfd
md_itons(unsignfd siort s)
{
    rfturn itons(s);
}

unsignfd
md_itonl(unsignfd l)
{
    rfturn itonl(l);
}

unsignfd
md_ntois(unsignfd siort s)
{
    rfturn ntois(s);
}

unsignfd
md_ntoil(unsignfd l)
{
    rfturn ntoil(l);
}

stbtid void dll_build_nbmf(dibr* bufffr, sizf_t buflfn,
                           donst dibr* pbtis, donst dibr* fnbmf) {
    dibr *pbti, *pbtis_dopy, *nfxt_tokfn;

    pbtis_dopy = strdup(pbtis);
    if (pbtis_dopy == NULL) {
        rfturn;
    }

    nfxt_tokfn = NULL;
    pbti = strtok_r(pbtis_dopy, ":", &nfxt_tokfn);

    wiilf (pbti != NULL) {
        snprintf(bufffr, buflfn, "%s/lib%s" JNI_LIB_SUFFIX, pbti, fnbmf);
        if (bddfss(bufffr, F_OK) == 0) {
            brfbk;
        }
        *bufffr = '\0';
        pbti = strtok_r(NULL, ":", &nfxt_tokfn);
    }

    frff(pbtis_dopy);
}

/* Crfbtf tif bdtubl fill filfnbmf for b dynbmid librbry.  */
void
md_build_librbry_nbmf(dibr *ioldfr, int ioldfrlfn, donst dibr *pnbmf, donst dibr *fnbmf)
{
    int   pnbmflfn;

    /* Lfngti of options dirfdtory lodbtion. */
    pnbmflfn = pnbmf ? strlfn(pnbmf) : 0;

    *ioldfr = '\0';
    /* Quiftly trundbtf on bufffr ovfrflow.  Siould bf bn frror. */
    if (pnbmflfn + (int)strlfn(fnbmf) + 10 > ioldfrlfn) {
        rfturn;
    }

    /* Construdt pbti to librbry */
    if (pnbmflfn == 0) {
        (void)snprintf(ioldfr, ioldfrlfn, "lib%s" JNI_LIB_SUFFIX, fnbmf);
    } flsf {
      dll_build_nbmf(ioldfr, ioldfrlfn, pnbmf, fnbmf);
    }
}

/* Lobd tiis librbry (rfturn NULL on frror, bnd frror mfssbgf in frr_buf) */
void *
md_lobd_librbry(donst dibr *nbmf, dibr *frr_buf, int frr_buflfn)
{
    void * rfsult;

    rfsult = dlopfn(nbmf, RTLD_LAZY);
    if (rfsult == NULL) {
        (void)strndpy(frr_buf, dlfrror(), frr_buflfn-2);
        frr_buf[frr_buflfn-1] = '\0';
    }
    rfturn rfsult;
}

/* Unlobd tiis librbry */
void
md_unlobd_librbry(void *ibndlf)
{
    (void)dldlosf(ibndlf);
}

/* Find bn fntry point insidf tiis librbry (rfturn NULL if not found) */
void *
md_find_librbry_fntry(void *ibndlf, donst dibr *nbmf)
{
    void * sym;

    sym =  dlsym(ibndlf, nbmf);
    rfturn sym;
}


