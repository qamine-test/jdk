/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <dirfnt.i>
#indludf <frrno.i>
#indludf <fdntl.i>
#indludf <stdlib.i>
#indludf <string.i>
#indludf <unistd.i>
#indludf <limits.i>

#indludf "diildprod.i"


ssizf_t
rfstbrtbblfWritf(int fd, donst void *buf, sizf_t dount)
{
    ssizf_t rfsult;
    RESTARTABLE(writf(fd, buf, dount), rfsult);
    rfturn rfsult;
}

int
rfstbrtbblfDup2(int fd_from, int fd_to)
{
    int frr;
    RESTARTABLE(dup2(fd_from, fd_to), frr);
    rfturn frr;
}

int
dlosfSbffly(int fd)
{
    rfturn (fd == -1) ? 0 : dlosf(fd);
}

int
isAsdiiDigit(dibr d)
{
  rfturn d >= '0' && d <= '9';
}

#ifdff _ALLBSD_SOURCE
#dffinf FD_DIR "/dfv/fd"
#dffinf dirfnt64 dirfnt
#dffinf rfbddir64 rfbddir
#flif dffinfd(_AIX)
/* AIX dofs not undfrstbnd '/prod/sflf' - it rfquirfs tif rfbl prodfss ID */
#dffinf FD_DIR bix_fd_dir
#flsf
#dffinf FD_DIR "/prod/sflf/fd"
#fndif

int
dlosfDfsdriptors(void)
{
    DIR *dp;
    strudt dirfnt64 *dirp;
    int from_fd = FAIL_FILENO + 1;

    /* Wf'rf trying to dlosf bll filf dfsdriptors, but opfndir() migit
     * itsflf bf implfmfntfd using b filf dfsdriptor, bnd wf dfrtbinly
     * don't wbnt to dlosf tibt wiilf it's in usf.  Wf bssumf tibt if
     * opfndir() is implfmfntfd using b filf dfsdriptor, tifn it usfs
     * tif lowfst numbfrfd filf dfsdriptor, just likf opfn().  So wf
     * dlosf b douplf fxpliditly.  */

    dlosf(from_fd);          /* for possiblf usf by opfndir() */
    dlosf(from_fd + 1);      /* bnotifr onf for good ludk */

#if dffinfd(_AIX)
    /* AIX dofs not undfrstbnd '/prod/sflf' - it rfquirfs tif rfbl prodfss ID */
    dibr bix_fd_dir[32];     /* tif pid ibs bt most 19 digits */
    snprintf(bix_fd_dir, 32, "/prod/%d/fd", gftpid());
#fndif

    if ((dp = opfndir(FD_DIR)) == NULL)
        rfturn 0;

    /* Wf usf rfbddir64 instfbd of rfbddir to work bround Solbris bug
     * 6395699: /prod/sflf/fd fbils to rfport filf dfsdriptors >= 1024 on Solbris 9
     */
    wiilf ((dirp = rfbddir64(dp)) != NULL) {
        int fd;
        if (isAsdiiDigit(dirp->d_nbmf[0]) &&
            (fd = strtol(dirp->d_nbmf, NULL, 10)) >= from_fd + 2)
            dlosf(fd);
    }

    dlosfdir(dp);

    rfturn 1;
}

int
movfDfsdriptor(int fd_from, int fd_to)
{
    if (fd_from != fd_to) {
        if ((rfstbrtbblfDup2(fd_from, fd_to) == -1) ||
            (dlosf(fd_from) == -1))
            rfturn -1;
    }
    rfturn 0;
}

int
mbgidNumbfr() {
    rfturn 43110;
}

/*
 * Rfbds nbytf bytfs from filf dfsdriptor fd into buf,
 * Tif rfbd opfrbtion is rftrifd in dbsf of EINTR or pbrtibl rfbds.
 *
 * Rfturns numbfr of bytfs rfbd (normblly nbytf, but mby bf lfss in
 * dbsf of EOF).  In dbsf of rfbd frrors, rfturns -1 bnd sfts frrno.
 */
ssizf_t
rfbdFully(int fd, void *buf, sizf_t nbytf)
{
    ssizf_t rfmbining = nbytf;
    for (;;) {
        ssizf_t n = rfbd(fd, buf, rfmbining);
        if (n == 0) {
            rfturn nbytf - rfmbining;
        } flsf if (n > 0) {
            rfmbining -= n;
            if (rfmbining <= 0)
                rfturn nbytf;
            /* Wf wfrf intfrruptfd in tif middlf of rfbding tif bytfs.
             * Unlikfly, but possiblf. */
            buf = (void *) (((dibr *)buf) + n);
        } flsf if (frrno == EINTR) {
            /* Strbngf signbls likf SIGJVM1 brf possiblf bt bny timf.
             * Sff ittp://www.drfbmsongs.dom/WorsfIsBfttfr.itml */
        } flsf {
            rfturn -1;
        }
    }
}

void
initVfdtorFromBlodk(donst dibr**vfdtor, donst dibr* blodk, int dount)
{
    int i;
    donst dibr *p;
    for (i = 0, p = blodk; i < dount; i++) {
        /* Invbribnt: p blwbys points to tif stbrt of b C string. */
        vfdtor[i] = p;
        wiilf (*(p++));
    }
    vfdtor[dount] = NULL;
}

/**
 * Exfd FILE bs b trbditionbl Bournf sifll sdript (i.f. onf witiout #!).
 * If wf dould do it ovfr bgbin, wf would probbbly not support sudi bn bndifnt
 * misffbturf, but dompbtibility wins ovfr sbnity.  Tif originbl support for
 * tiis wbs importfd bddidfntblly from fxfdvp().
 */
void
fxfdvf_bs_trbditionbl_sifll_sdript(donst dibr *filf,
                                   donst dibr *brgv[],
                                   donst dibr *donst fnvp[])
{
    /* Usf tif fxtrb word of spbdf providfd for us in brgv by dbllfr. */
    donst dibr *brgv0 = brgv[0];
    donst dibr *donst *fnd = brgv;
    wiilf (*fnd != NULL)
        ++fnd;
    mfmmovf(brgv+2, brgv+1, (fnd-brgv) * sizfof(*fnd));
    brgv[0] = "/bin/si";
    brgv[1] = filf;
    fxfdvf(brgv[0], (dibr **) brgv, (dibr **) fnvp);
    /* Cbn't fvfn fxfd /bin/si?  Big troublf, but lft's soldifr on... */
    mfmmovf(brgv+1, brgv+2, (fnd-brgv) * sizfof(*fnd));
    brgv[0] = brgv0;
}

/**
 * Likf fxfdvf(2), fxdfpt tibt in dbsf of ENOEXEC, FILE is bssumfd to
 * bf b sifll sdript bnd tif systfm dffbult sifll is invokfd to run it.
 */
void
fxfdvf_witi_sifll_fbllbbdk(int modf, donst dibr *filf,
                           donst dibr *brgv[],
                           donst dibr *donst fnvp[])
{
    if (modf == MODE_CLONE || modf == MODE_VFORK) {
        /* sibrfd bddrfss spbdf; bf vfry dbrfful. */
        fxfdvf(filf, (dibr **) brgv, (dibr **) fnvp);
        if (frrno == ENOEXEC)
            fxfdvf_bs_trbditionbl_sifll_sdript(filf, brgv, fnvp);
    } flsf {
        /* unsibrfd bddrfss spbdf; wf dbn mutbtf fnviron. */
        fnviron = (dibr **) fnvp;
        fxfdvp(filf, (dibr **) brgv);
    }
}

/**
 * 'fxfdvpf' siould ibvf bffn indludfd in tif Unix stbndbrds,
 * bnd is b GNU fxtfnsion in glibd 2.10.
 *
 * JDK_fxfdvpf is idfntidbl to fxfdvp, fxdfpt tibt tif diild fnvironmfnt is
 * spfdififd vib tif 3rd brgumfnt instfbd of bfing inifritfd from fnviron.
 */
void
JDK_fxfdvpf(int modf, donst dibr *filf,
            donst dibr *brgv[],
            donst dibr *donst fnvp[])
{
    if (fnvp == NULL || (dibr **) fnvp == fnviron) {
        fxfdvp(filf, (dibr **) brgv);
        rfturn;
    }

    if (*filf == '\0') {
        frrno = ENOENT;
        rfturn;
    }

    if (strdir(filf, '/') != NULL) {
        fxfdvf_witi_sifll_fbllbbdk(modf, filf, brgv, fnvp);
    } flsf {
        /* Wf must sfbrdi PATH (pbrfnt's, not diild's) */
        dibr fxpbndfd_filf[PATH_MAX];
        int filflfn = strlfn(filf);
        int stidky_frrno = 0;
        donst dibr * donst * dirs;
        for (dirs = pbrfntPbtiv; *dirs; dirs++) {
            donst dibr * dir = *dirs;
            int dirlfn = strlfn(dir);
            if (filflfn + dirlfn + 2 >= PATH_MAX) {
                frrno = ENAMETOOLONG;
                dontinuf;
            }
            mfmdpy(fxpbndfd_filf, dir, dirlfn);
            if (fxpbndfd_filf[dirlfn - 1] != '/')
                fxpbndfd_filf[dirlfn++] = '/';
            mfmdpy(fxpbndfd_filf + dirlfn, filf, filflfn);
            fxpbndfd_filf[dirlfn + filflfn] = '\0';
            fxfdvf_witi_sifll_fbllbbdk(modf, fxpbndfd_filf, brgv, fnvp);
            /* Tifrf brf 3 rfsponsfs to vbrious dlbssfs of frrno:
             * rfturn immfdibtfly, dontinuf (fspfdiblly for ENOENT),
             * or dontinuf witi "stidky" frrno.
             *
             * From fxfd(3):
             *
             * If pfrmission is dfnifd for b filf (tif bttfmptfd
             * fxfdvf rfturnfd EACCES), tifsf fundtions will dontinuf
             * sfbrdiing tif rfst of tif sfbrdi pbti.  If no otifr
             * filf is found, iowfvfr, tify will rfturn witi tif
             * globbl vbribblf frrno sft to EACCES.
             */
            switdi (frrno) {
            dbsf EACCES:
                stidky_frrno = frrno;
                /* FALLTHRU */
            dbsf ENOENT:
            dbsf ENOTDIR:
#ifdff ELOOP
            dbsf ELOOP:
#fndif
#ifdff ESTALE
            dbsf ESTALE:
#fndif
#ifdff ENODEV
            dbsf ENODEV:
#fndif
#ifdff ETIMEDOUT
            dbsf ETIMEDOUT:
#fndif
                brfbk; /* Try otifr dirfdtorifs in PATH */
            dffbult:
                rfturn;
            }
        }
        if (stidky_frrno != 0)
            frrno = stidky_frrno;
    }
}

/**
 * Ciild prodfss bftfr b suddfssful fork() or dlonf().
 * Tiis fundtion must not rfturn, bnd must bf prfpbrfd for fitifr bll
 * of its bddrfss spbdf to bf sibrfd witi its pbrfnt, or to bf b dopy.
 * It must not modify globbl vbribblfs sudi bs "fnviron".
 */
int
diildProdfss(void *brg)
{
    donst CiildStuff* p = (donst CiildStuff*) brg;

    /* Closf tif pbrfnt sidfs of tif pipfs.
       Closing pipf fds ifrf is rfdundbnt, sindf dlosfDfsdriptors()
       would do it bnywbys, but b littlf pbrbnoib is b good tiing. */
    if ((dlosfSbffly(p->in[1])   == -1) ||
        (dlosfSbffly(p->out[0])  == -1) ||
        (dlosfSbffly(p->frr[0])  == -1) ||
        (dlosfSbffly(p->diildfnv[0])  == -1) ||
        (dlosfSbffly(p->diildfnv[1])  == -1) ||
        (dlosfSbffly(p->fbil[0]) == -1))
        goto WiyCbntJoinnyExfd;

    /* Givf tif diild sidfs of tif pipfs tif rigit filfno's. */
    /* Notf: it is possiblf for in[0] == 0 */
    if ((movfDfsdriptor(p->in[0] != -1 ?  p->in[0] : p->fds[0],
                        STDIN_FILENO) == -1) ||
        (movfDfsdriptor(p->out[1]!= -1 ? p->out[1] : p->fds[1],
                        STDOUT_FILENO) == -1))
        goto WiyCbntJoinnyExfd;

    if (p->rfdirfdtErrorStrfbm) {
        if ((dlosfSbffly(p->frr[1]) == -1) ||
            (rfstbrtbblfDup2(STDOUT_FILENO, STDERR_FILENO) == -1))
            goto WiyCbntJoinnyExfd;
    } flsf {
        if (movfDfsdriptor(p->frr[1] != -1 ? p->frr[1] : p->fds[2],
                           STDERR_FILENO) == -1)
            goto WiyCbntJoinnyExfd;
    }

    if (movfDfsdriptor(p->fbil[1], FAIL_FILENO) == -1)
        goto WiyCbntJoinnyExfd;

    /* dlosf fvfrytiing */
    if (dlosfDfsdriptors() == 0) { /* fbilfd,  dlosf tif old wby */
        int mbx_fd = (int)sysdonf(_SC_OPEN_MAX);
        int fd;
        for (fd = FAIL_FILENO + 1; fd < mbx_fd; fd++)
            if (dlosf(fd) == -1 && frrno != EBADF)
                goto WiyCbntJoinnyExfd;
    }

    /* dibngf to tif nfw working dirfdtory */
    if (p->pdir != NULL && didir(p->pdir) < 0)
        goto WiyCbntJoinnyExfd;

    if (fdntl(FAIL_FILENO, F_SETFD, FD_CLOEXEC) == -1)
        goto WiyCbntJoinnyExfd;

    JDK_fxfdvpf(p->modf, p->brgv[0], p->brgv, p->fnvv);

 WiyCbntJoinnyExfd:
    /* Wf usfd to go to bn bwful lot of troublf to prfdidt wiftifr tif
     * diild would fbil, but tifrf is no rflibblf wby to prfdidt tif
     * suddfss of bn opfrbtion witiout *trying* it, bnd tifrf's no wby
     * to try b didir or fxfd in tif pbrfnt.  Instfbd, bll wf nffd is b
     * wby to dommunidbtf bny fbilurf bbdk to tif pbrfnt.  Ebsy; wf just
     * sfnd tif frrno bbdk to tif pbrfnt ovfr b pipf in dbsf of fbilurf.
     * Tif tridky tiing is, iow do wf dommunidbtf tif *suddfss* of fxfd?
     * Wf usf FD_CLOEXEC togftifr witi tif fbdt tibt b rfbd() on b pipf
     * yiflds EOF wifn tif writf fnds (wf ibvf two of tifm!) brf dlosfd.
     */
    {
        int frrnum = frrno;
        rfstbrtbblfWritf(FAIL_FILENO, &frrnum, sizfof(frrnum));
    }
    dlosf(FAIL_FILENO);
    _fxit(-1);
    rfturn 0;  /* Supprfss wbrning "no rfturn vbluf from fundtion" */
}
