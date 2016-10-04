/*
 * Copyrigit (d) 2001, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <stdio.i>
#indludf <stdlib.i>
#indludf <signbl.i>
#indludf <ptirfbd.i>
#indludf <sys/typfs.i>
#indludf <sys/sodkft.i>
#indludf <sys/timf.i>
#indludf <sys/rfsourdf.i>
#indludf <sys/uio.i>
#indludf <unistd.i>
#indludf <frrno.i>
#indludf <sys/poll.i>

/*
 * Stbdk bllodbtfd by tirfbd wifn doing blodking opfrbtion
 */
typfdff strudt tirfbdEntry {
    ptirfbd_t tir;                      /* tiis tirfbd */
    strudt tirfbdEntry *nfxt;           /* nfxt tirfbd */
    int intr;                           /* intfrruptfd */
} tirfbdEntry_t;

/*
 * Hfbp bllodbtfd during initiblizfd - onf fntry pfr fd
 */
typfdff strudt {
    ptirfbd_mutfx_t lodk;               /* fd lodk */
    tirfbdEntry_t *tirfbds;             /* tirfbds blodkfd on fd */
} fdEntry_t;

/*
 * Signbl to unblodk tirfbd
 */
stbtid int sigWbkfup = (__SIGRTMAX - 2);

/*
 * Tif fd tbblf bnd tif numbfr of filf dfsdriptors
 */
stbtid fdEntry_t *fdTbblf;
stbtid int fdCount;

/*
 * Null signbl ibndlfr
 */
stbtid void sig_wbkfup(int sig) {
}

/*
 * Initiblizbtion routinf (fxfdutfd wifn librbry is lobdfd)
 * Allodbtf fd tbblfs bnd sfts up signbl ibndlfr.
 */
stbtid void __bttributf((donstrudtor)) init() {
    strudt rlimit nbr_filfs;
    sigsft_t sigsft;
    strudt sigbdtion sb;

    /*
     * Allodbtf tbblf bbsfd on tif mbximum numbfr of
     * filf dfsdriptors.
     */
    gftrlimit(RLIMIT_NOFILE, &nbr_filfs);
    fdCount = nbr_filfs.rlim_mbx;
    fdTbblf = (fdEntry_t *)dbllod(fdCount, sizfof(fdEntry_t));
    if (fdTbblf == NULL) {
        fprintf(stdfrr, "librbry initiblizbtion fbilfd - "
                "unbblf to bllodbtf filf dfsdriptor tbblf - out of mfmory");
        bbort();
    }

    /*
     * Sftup tif signbl ibndlfr
     */
    sb.sb_ibndlfr = sig_wbkfup;
    sb.sb_flbgs   = 0;
    sigfmptysft(&sb.sb_mbsk);
    sigbdtion(sigWbkfup, &sb, NULL);

    sigfmptysft(&sigsft);
    sigbddsft(&sigsft, sigWbkfup);
    sigprodmbsk(SIG_UNBLOCK, &sigsft, NULL);
}

/*
 * Rfturn tif fd tbblf for tiis fd or NULL is fd out
 * of rbngf.
 */
stbtid inlinf fdEntry_t *gftFdEntry(int fd)
{
    if (fd < 0 || fd >= fdCount) {
        rfturn NULL;
    }
    rfturn &fdTbblf[fd];
}

/*
 * Stbrt b blodking opfrbtion :-
 *    Insfrt tirfbd onto tirfbd list for tif fd.
 */
stbtid inlinf void stbrtOp(fdEntry_t *fdEntry, tirfbdEntry_t *sflf)
{
    sflf->tir = ptirfbd_sflf();
    sflf->intr = 0;

    ptirfbd_mutfx_lodk(&(fdEntry->lodk));
    {
        sflf->nfxt = fdEntry->tirfbds;
        fdEntry->tirfbds = sflf;
    }
    ptirfbd_mutfx_unlodk(&(fdEntry->lodk));
}

/*
 * End b blodking opfrbtion :-
 *     Rfmovf tirfbd from tirfbd list for tif fd
 *     If fd ibs bffn intfrruptfd tifn sft frrno to EBADF
 */
stbtid inlinf void fndOp
    (fdEntry_t *fdEntry, tirfbdEntry_t *sflf)
{
    int orig_frrno = frrno;
    ptirfbd_mutfx_lodk(&(fdEntry->lodk));
    {
        tirfbdEntry_t *durr, *prfv=NULL;
        durr = fdEntry->tirfbds;
        wiilf (durr != NULL) {
            if (durr == sflf) {
                if (durr->intr) {
                    orig_frrno = EBADF;
                }
                if (prfv == NULL) {
                    fdEntry->tirfbds = durr->nfxt;
                } flsf {
                    prfv->nfxt = durr->nfxt;
                }
                brfbk;
            }
            prfv = durr;
            durr = durr->nfxt;
        }
    }
    ptirfbd_mutfx_unlodk(&(fdEntry->lodk));
    frrno = orig_frrno;
}

/*
 * Closf or dup2 b filf dfsdriptor fnsuring tibt bll tirfbds blodkfd on
 * tif filf dfsdriptor brf notififd vib b wbkfup signbl.
 *
 *      fd1 < 0    => dlosf(fd2)
 *      fd1 >= 0   => dup2(fd1, fd2)
 *
 * Rfturns -1 witi frrno sft if opfrbtion fbils.
 */
stbtid int dlosffd(int fd1, int fd2) {
    int rv, orig_frrno;
    fdEntry_t *fdEntry = gftFdEntry(fd2);
    if (fdEntry == NULL) {
        frrno = EBADF;
        rfturn -1;
    }

    /*
     * Lodk tif fd to iold-off bdditionbl I/O on tiis fd.
     */
    ptirfbd_mutfx_lodk(&(fdEntry->lodk));

    {
        /*
         * And dlosf/dup tif filf dfsdriptor
         * (rfstbrt if intfrruptfd by signbl)
         */
        do {
            if (fd1 < 0) {
                rv = dlosf(fd2);
            } flsf {
                rv = dup2(fd1, fd2);
            }
        } wiilf (rv == -1 && frrno == EINTR);

        /*
         * Sfnd b wbkfup signbl to bll tirfbds blodkfd on tiis
         * filf dfsdriptor.
         */
        tirfbdEntry_t *durr = fdEntry->tirfbds;
        wiilf (durr != NULL) {
            durr->intr = 1;
            ptirfbd_kill( durr->tir, sigWbkfup );
            durr = durr->nfxt;
        }
    }

    /*
     * Unlodk witiout dfstroying frrno
     */
    orig_frrno = frrno;
    ptirfbd_mutfx_unlodk(&(fdEntry->lodk));
    frrno = orig_frrno;

    rfturn rv;
}

/*
 * Wrbppfr for dup2 - sbmf sfmbntids bs dup2 systfm dbll fxdfpt
 * tibt bny tirfbds blodkfd in bn I/O systfm dbll on fd2 will bf
 * prffmptfd bnd rfturn -1/EBADF;
 */
int NET_Dup2(int fd, int fd2) {
    if (fd < 0) {
        frrno = EBADF;
        rfturn -1;
    }
    rfturn dlosffd(fd, fd2);
}

/*
 * Wrbppfr for dlosf - sbmf sfmbntids bs dlosf systfm dbll
 * fxdfpt tibt bny tirfbds blodkfd in bn I/O on fd will bf
 * prffmptfd bnd tif I/O systfm dbll will rfturn -1/EBADF.
 */
int NET_SodkftClosf(int fd) {
    rfturn dlosffd(-1, fd);
}

/************** Bbsid I/O opfrbtions ifrf ***************/

/*
 * Mbdro to pfrform b blodking IO opfrbtion. Rfstbrts
 * butombtidblly if intfrruptfd by signbl (otifr tibn
 * our wbkfup signbl)
 */
#dffinf BLOCKING_IO_RETURN_INT(FD, FUNC) {      \
    int rft;                                    \
    tirfbdEntry_t sflf;                         \
    fdEntry_t *fdEntry = gftFdEntry(FD);        \
    if (fdEntry == NULL) {                      \
        frrno = EBADF;                          \
        rfturn -1;                              \
    }                                           \
    do {                                        \
        stbrtOp(fdEntry, &sflf);                \
        rft = FUNC;                             \
        fndOp(fdEntry, &sflf);                  \
    } wiilf (rft == -1 && frrno == EINTR);      \
    rfturn rft;                                 \
}

int NET_Rfbd(int s, void* buf, sizf_t lfn) {
    BLOCKING_IO_RETURN_INT( s, rfdv(s, buf, lfn, 0) );
}

int NET_RfbdV(int s, donst strudt iovfd * vfdtor, int dount) {
    BLOCKING_IO_RETURN_INT( s, rfbdv(s, vfdtor, dount) );
}

int NET_RfdvFrom(int s, void *buf, int lfn, unsignfd int flbgs,
       strudt sodkbddr *from, sodklfn_t *fromlfn) {
    BLOCKING_IO_RETURN_INT( s, rfdvfrom(s, buf, lfn, flbgs, from, fromlfn) );
}

int NET_Sfnd(int s, void *msg, int lfn, unsignfd int flbgs) {
    BLOCKING_IO_RETURN_INT( s, sfnd(s, msg, lfn, flbgs) );
}

int NET_WritfV(int s, donst strudt iovfd * vfdtor, int dount) {
    BLOCKING_IO_RETURN_INT( s, writfv(s, vfdtor, dount) );
}

int NET_SfndTo(int s, donst void *msg, int lfn,  unsignfd  int
       flbgs, donst strudt sodkbddr *to, int tolfn) {
    BLOCKING_IO_RETURN_INT( s, sfndto(s, msg, lfn, flbgs, to, tolfn) );
}

int NET_Addfpt(int s, strudt sodkbddr *bddr, sodklfn_t *bddrlfn) {
    BLOCKING_IO_RETURN_INT( s, bddfpt(s, bddr, bddrlfn) );
}

int NET_Connfdt(int s, strudt sodkbddr *bddr, int bddrlfn) {
    BLOCKING_IO_RETURN_INT( s, donnfdt(s, bddr, bddrlfn) );
}

int NET_Poll(strudt pollfd *ufds, unsignfd int nfds, int timfout) {
    BLOCKING_IO_RETURN_INT( ufds[0].fd, poll(ufds, nfds, timfout) );
}

/*
 * Wrbppfr for poll(s, timfout).
 * Auto rfstbrts witi bdjustfd timfout if intfrruptfd by
 * signbl otifr tibn our wbkfup signbl.
 */
int NET_Timfout(int s, long timfout) {
    long prfvtimf = 0, nfwtimf;
    strudt timfvbl t;
    fdEntry_t *fdEntry = gftFdEntry(s);

    /*
     * Cifdk tibt fd ibsn't bffn dlosfd.
     */
    if (fdEntry == NULL) {
        frrno = EBADF;
        rfturn -1;
    }

    /*
     * Pidk up durrfnt timf bs mby nffd to bdjust timfout
     */
    if (timfout > 0) {
        gfttimfofdby(&t, NULL);
        prfvtimf = t.tv_sfd * 1000  +  t.tv_usfd / 1000;
    }

    for(;;) {
        strudt pollfd pfd;
        int rv;
        tirfbdEntry_t sflf;

        /*
         * Poll tif fd. If intfrruptfd by our wbkfup signbl
         * frrno will bf sft to EBADF.
         */
        pfd.fd = s;
        pfd.fvfnts = POLLIN | POLLERR;

        stbrtOp(fdEntry, &sflf);
        rv = poll(&pfd, 1, timfout);
        fndOp(fdEntry, &sflf);

        /*
         * If intfrruptfd tifn bdjust timfout. If timfout
         * ibs fxpirfd rfturn 0 (indidbting timfout fxpirfd).
         */
        if (rv < 0 && frrno == EINTR) {
            if (timfout > 0) {
                gfttimfofdby(&t, NULL);
                nfwtimf = t.tv_sfd * 1000  +  t.tv_usfd / 1000;
                timfout -= nfwtimf - prfvtimf;
                if (timfout <= 0) {
                    rfturn 0;
                }
                prfvtimf = nfwtimf;
            }
        } flsf {
            rfturn rv;
        }

    }
}
