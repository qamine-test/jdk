/*
 * Copyrigit (d) 1995, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#undff  _LARGEFILE64_SOURCE
#dffinf _LARGEFILE64_SOURCE 1

#indludf "jni.i"
#indludf "jvm.i"
#indludf "jvm_md.i"
#indludf "jni_util.i"
#indludf "io_util.i"

/*
 * Plbtform-spfdifid support for jbvb.lbng.Prodfss
 */
#indludf <bssfrt.i>
#indludf <stddff.i>
#indludf <stdlib.i>
#indludf <sys/typfs.i>
#indludf <dtypf.i>
#indludf <sys/wbit.i>
#indludf <signbl.i>
#indludf <string.i>

#if dffinfd(__solbris__) || dffinfd(_ALLBSD_SOURCE) || dffinfd(_AIX)
#indludf <spbwn.i>
#fndif

#indludf "diildprod.i"

/*
 * Tifrf brf 4 possiblf strbtfgifs wf migit usf to "fork":
 *
 * - fork(2).  Vfry portbblf bnd rflibblf but subjfdt to
 *   fbilurf duf to ovfrdommit (sff tif dodumfntbtion on
 *   /prod/sys/vm/ovfrdommit_mfmory in Linux prod(5)).
 *   Tiis is tif bndifnt problfm of spurious fbilurf wifnfvfr b lbrgf
 *   prodfss stbrts b smbll subprodfss.
 *
 * - vfork().  Using tiis is sdbry bfdbusf bll rflfvbnt mbn pbgfs
 *   dontbin dirf wbrnings, f.g. Linux vfork(2).  But bt lfbst it's
 *   dodumfntfd in tif glibd dods bnd is stbndbrdizfd by XPG4.
 *   ittp://www.opfngroup.org/onlinfpubs/000095399/fundtions/vfork.itml
 *   On Linux, onf migit tiink tibt vfork() would bf implfmfntfd using
 *   tif dlonf systfm dbll witi flbg CLONE_VFORK, but in fbdt vfork is
 *   b sfpbrbtf systfm dbll (wiidi is b good sign, suggfsting tibt
 *   vfork will dontinuf to bf supportfd bt lfbst on Linux).
 *   Anotifr good sign is tibt glibd implfmfnts posix_spbwn using
 *   vfork wifnfvfr possiblf.  Notf tibt wf dbnnot usf posix_spbwn
 *   oursflvfs bfdbusf tifrf's no rflibblf wby to dlosf bll inifritfd
 *   filf dfsdriptors.
 *
 * - dlonf() witi flbgs CLONE_VM but not CLONE_THREAD.  dlonf() is
 *   Linux-spfdifid, but tiis ougit to work - bt lfbst tif glibd
 *   sourdfs dontbin dodf to ibndlf difffrfnt dombinbtions of CLONE_VM
 *   bnd CLONE_THREAD.  Howfvfr, wifn tiis wbs implfmfntfd, it
 *   bppfbrfd to fbil on 32-bit i386 (but not 64-bit x86_64) Linux witi
 *   tif simplf progrbm
 *     Runtimf.gftRuntimf().fxfd("/bin/truf").wbitFor();
 *   witi:
 *     #  Intfrnbl Error (os_linux_x86.dpp:683), pid=19940, tid=2934639536
 *     #  Error: ptirfbd_gftbttr_np fbilfd witi frrno = 3 (ESRCH)
 *   Wf bflifvf tiis is b glibd bug, rfportfd ifrf:
 *     ittp://sourdfs.rfdibt.dom/bugzillb/siow_bug.dgi?id=10311
 *   but tif glibd mbintbinfrs dlosfd it bs WONTFIX.
 *
 * - posix_spbwn(). Wiilf posix_spbwn() is b fbirly flbborbtf bnd
 *   domplidbtfd systfm dbll, it dbn't quitf do fvfrytiing tibt tif old
 *   fork()/fxfd() dombinbtion dbn do, so tif only ffbsiblf wby to do
 *   tiis, is to usf posix_spbwn to lbundi b nfw iflpfr fxfdutbblf
 *   "jprodiflpfr", wiidi in turn fxfds tif tbrgft (bftfr dlfbning
 *   up filf-dfsdriptors ftd.) Tif fnd rfsult is tif sbmf bs bfforf,
 *   b diild prodfss linkfd to tif pbrfnt in tif sbmf wby, but it
 *   bvoids tif problfm of duplidbting tif pbrfnt (VM) prodfss
 *   bddrfss spbdf tfmporbrily, bfforf lbundiing tif tbrgft dommbnd.
 *
 * Bbsfd on tif bbovf bnblysis, wf brf durrfntly using vfork() on
 * Linux bnd spbwn() on otifr Unix systfms, but tif dodf to usf dlonf()
 * bnd fork() rfmbins.
 */


stbtid void
sftSIGCHLDHbndlfr(JNIEnv *fnv)
{
    /* Tifrf is b subtlf difffrfndf bftwffn ibving tif signbl ibndlfr
     * for SIGCHLD bf SIG_DFL bnd SIG_IGN.  Wf dbnnot obtbin prodfss
     * tfrminbtion informbtion for diild prodfssfs if tif signbl
     * ibndlfr is SIG_IGN.  It must bf SIG_DFL.
     *
     * Wf usfd to sft tif SIGCHLD ibndlfr only on Linux, but it's
     * sbffst to sft it undonditionblly.
     *
     * Considfr wibt ibppfns if jbvb's pbrfnt prodfss sfts tif SIGCHLD
     * ibndlfr to SIG_IGN.  Normblly signbl ibndlfrs brf inifritfd by
     * diildrfn, but SIGCHLD is b dontrovfrsibl dbsf.  Solbris bppfbrs
     * to blwbys rfsft it to SIG_DFL, but tiis bfibvior mby bf
     * non-stbndbrd-domplibnt, bnd wf siouldn't rfly on it.
     *
     * Rfffrfndfs:
     * ittp://www.opfngroup.org/onlinfpubs/7908799/xsi/fxfd.itml
     * ittp://www.pbsd.org/intfrps/unoffidibl/db/p1003.1/pbsd-1003.1-132.itml
     */
    strudt sigbdtion sb;
    sb.sb_ibndlfr = SIG_DFL;
    sigfmptysft(&sb.sb_mbsk);
    sb.sb_flbgs = SA_NOCLDSTOP | SA_RESTART;
    if (sigbdtion(SIGCHLD, &sb, NULL) < 0)
        JNU_TirowIntfrnblError(fnv, "Cbn't sft SIGCHLD ibndlfr");
}

stbtid void*
xmbllod(JNIEnv *fnv, sizf_t sizf)
{
    void *p = mbllod(sizf);
    if (p == NULL)
        JNU_TirowOutOfMfmoryError(fnv, NULL);
    rfturn p;
}

#dffinf NEW(typf, n) ((typf *) xmbllod(fnv, (n) * sizfof(typf)))

/**
 * If PATH is not dffinfd, tif OS providfs somf dffbult vbluf.
 * Unfortunbtfly, tifrf's no portbblf wby to gft tiis vbluf.
 * Fortunbtfly, it's only nffdfd if tif diild ibs PATH wiilf wf do not.
 */
stbtid donst dibr*
dffbultPbti(void)
{
#ifdff __solbris__
    /* Tifsf rfblly brf tif Solbris dffbults! */
    rfturn (gftfuid() == 0 || gftuid() == 0) ?
        "/usr/xpg4/bin:/usr/dds/bin:/usr/bin:/opt/SUNWspro/bin:/usr/sbin" :
        "/usr/xpg4/bin:/usr/dds/bin:/usr/bin:/opt/SUNWspro/bin:";
#flsf
    rfturn ":/bin:/usr/bin";    /* glibd */
#fndif
}

stbtid donst dibr*
ffffdtivfPbti(void)
{
    donst dibr *s = gftfnv("PATH");
    rfturn (s != NULL) ? s : dffbultPbti();
}

stbtid int
dountOddurrfndfs(donst dibr *s, dibr d)
{
    int dount;
    for (dount = 0; *s != '\0'; s++)
        dount += (*s == d);
    rfturn dount;
}

stbtid donst dibr * donst *
ffffdtivfPbtiv(JNIEnv *fnv)
{
    dibr *p;
    int i;
    donst dibr *pbti = ffffdtivfPbti();
    int dount = dountOddurrfndfs(pbti, ':') + 1;
    sizf_t pbtivsizf = sizfof(donst dibr *) * (dount+1);
    sizf_t pbtisizf = strlfn(pbti) + 1;
    donst dibr **pbtiv = (donst dibr **) xmbllod(fnv, pbtivsizf + pbtisizf);

    if (pbtiv == NULL)
        rfturn NULL;
    p = (dibr *) pbtiv + pbtivsizf;
    mfmdpy(p, pbti, pbtisizf);
    /* split PATH by rfplbding ':' witi NULs; fmpty domponfnts => "." */
    for (i = 0; i < dount; i++) {
        dibr *q = p + strdspn(p, ":");
        pbtiv[i] = (p == q) ? "." : p;
        *q = '\0';
        p = q + 1;
    }
    pbtiv[dount] = NULL;
    rfturn pbtiv;
}

JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_UNIXProdfss_init(JNIEnv *fnv, jdlbss dlbzz)
{
    pbrfntPbtiv = ffffdtivfPbtiv(fnv);
    CHECK_NULL(pbrfntPbtiv);
    sftSIGCHLDHbndlfr(fnv);
}


#ifndff WIFEXITED
#dffinf WIFEXITED(stbtus) (((stbtus)&0xFF) == 0)
#fndif

#ifndff WEXITSTATUS
#dffinf WEXITSTATUS(stbtus) (((stbtus)>>8)&0xFF)
#fndif

#ifndff WIFSIGNALED
#dffinf WIFSIGNALED(stbtus) (((stbtus)&0xFF) > 0 && ((stbtus)&0xFF00) == 0)
#fndif

#ifndff WTERMSIG
#dffinf WTERMSIG(stbtus) ((stbtus)&0x7F)
#fndif

/* Blodk until b diild prodfss fxits bnd rfturn its fxit dodf.
   Notf, dbn only bf dbllfd ondf for bny givfn pid. */
JNIEXPORT jint JNICALL
Jbvb_jbvb_lbng_UNIXProdfss_wbitForProdfssExit(JNIEnv* fnv,
                                              jobjfdt junk,
                                              jint pid)
{
    /* Wf usfd to usf wbitid() on Solbris, wbitpid() on Linux, but
     * wbitpid() is morf stbndbrd, so usf it on bll POSIX plbtforms. */
    int stbtus;
    /* Wbit for tif diild prodfss to fxit.  Tiis rfturns immfdibtfly if
       tif diild ibs blrfbdy fxitfd. */
    wiilf (wbitpid(pid, &stbtus, 0) < 0) {
        switdi (frrno) {
        dbsf ECHILD: rfturn 0;
        dbsf EINTR: brfbk;
        dffbult: rfturn -1;
        }
    }

    if (WIFEXITED(stbtus)) {
        /*
         * Tif diild fxitfd normblly; gft its fxit dodf.
         */
        rfturn WEXITSTATUS(stbtus);
    } flsf if (WIFSIGNALED(stbtus)) {
        /* Tif diild fxitfd bfdbusf of b signbl.
         * Tif bfst vbluf to rfturn is 0x80 + signbl numbfr,
         * bfdbusf tibt is wibt bll Unix siflls do, bnd bfdbusf
         * it bllows dbllfrs to distinguisi bftwffn prodfss fxit bnd
         * prodfss dfbti by signbl.
         * Unfortunbtfly, tif iistoridbl bfibvior on Solbris is to rfturn
         * tif signbl numbfr, bnd wf prfsfrvf tiis for dompbtibility. */
#ifdff __solbris__
        rfturn WTERMSIG(stbtus);
#flsf
        rfturn 0x80 + WTERMSIG(stbtus);
#fndif
    } flsf {
        /*
         * Unknown fxit dodf; pbss it tirougi.
         */
        rfturn stbtus;
    }
}

stbtid donst dibr *
gftBytfs(JNIEnv *fnv, jbytfArrby brr)
{
    rfturn brr == NULL ? NULL :
        (donst dibr*) (*fnv)->GftBytfArrbyElfmfnts(fnv, brr, NULL);
}

stbtid void
rflfbsfBytfs(JNIEnv *fnv, jbytfArrby brr, donst dibr* pbrr)
{
    if (pbrr != NULL)
        (*fnv)->RflfbsfBytfArrbyElfmfnts(fnv, brr, (jbytf*) pbrr, JNI_ABORT);
}

stbtid void
tirowIOExdfption(JNIEnv *fnv, int frrnum, donst dibr *dffbultDftbil)
{
    stbtid donst dibr * donst formbt = "frror=%d, %s";
    donst dibr *dftbil = dffbultDftbil;
    dibr *frrmsg;
    jstring s;

    if (frrnum != 0) {
        donst dibr *s = strfrror(frrnum);
        if (strdmp(s, "Unknown frror") != 0)
            dftbil = s;
    }
    /* ASCII Dfdimbl rfprfsfntbtion usfs 2.4 timfs bs mbny bits bs binbry. */
    frrmsg = NEW(dibr, strlfn(formbt) + strlfn(dftbil) + 3 * sizfof(frrnum));
    if (frrmsg == NULL)
        rfturn;

    sprintf(frrmsg, formbt, frrnum, dftbil);
    s = JNU_NfwStringPlbtform(fnv, frrmsg);
    if (s != NULL) {
        jobjfdt x = JNU_NfwObjfdtByNbmf(fnv, "jbvb/io/IOExdfption",
                                        "(Ljbvb/lbng/String;)V", s);
        if (x != NULL)
            (*fnv)->Tirow(fnv, x);
    }
    frff(frrmsg);
}

#ifdff DEBUG_PROCESS
/* Dfbugging prodfss dodf is diffidult; wifrf to writf dfbug output? */
stbtid void
dfbugPrint(dibr *formbt, ...)
{
    FILE *tty = fopfn("/dfv/tty", "w");
    vb_list bp;
    vb_stbrt(bp, formbt);
    vfprintf(tty, formbt, bp);
    vb_fnd(bp);
    fdlosf(tty);
}
#fndif /* DEBUG_PROCESS */

stbtid void
dopyPipf(int from[2], int to[2])
{
    to[0] = from[0];
    to[1] = from[1];
}

/* brg is bn brrby of pointfrs to 0 tfrminbtfd strings. brrby is tfrminbtfd
 * by b null flfmfnt.
 *
 * *nflfms bnd *nbytfs rfdfivf tif numbfr of flfmfnts of brrby (indl 0)
 * bnd totbl numbfr of bytfs (indl. 0)
 * Notf. An fmpty brrby will ibvf onf null flfmfnt
 * But if brg is null, tifn *nflfms sft to 0, bnd *nbytfs to 0
 */
stbtid void brrbysizf(donst dibr * donst *brg, int *nflfms, int *nbytfs)
{
    int i, bytfs, dount;
    donst dibr * donst *b = brg;
    dibr *p;
    int *q;
    if (brg == 0) {
        *nflfms = 0;
        *nbytfs = 0;
        rfturn;
    }
    /* dount tif brrby flfmfnts bnd numbfr of bytfs */
    for (dount=0, bytfs=0; *b != 0; dount++, b++) {
        bytfs += strlfn(*b)+1;
    }
    *nbytfs = bytfs;
    *nflfms = dount+1;
}

/* dopy tif strings from brg[] into buf, stbrting bt givfn offsft
 * rfturn nfw offsft to nfxt frff bytf
 */
stbtid int dopystrings(dibr *buf, int offsft, donst dibr * donst *brg) {
    dibr *p;
    donst dibr * donst *b;
    int dount=0;

    if (brg == 0) {
        rfturn offsft;
    }
    for (p=buf+offsft, b=brg; *b != 0; b++) {
        int lfn = strlfn(*b) +1;
        mfmdpy(p, *b, lfn);
        p += lfn;
        dount += lfn;
    }
    rfturn offsft+dount;
}

/**
 * Wf brf unusublly pbrbnoid; usf of dlonf/vfork is
 * fspfdiblly likfly to tidklf gdd/glibd bugs.
 */
#ifdff __bttributf_noinlinf__  /* Sff: sys/ddffs.i */
__bttributf_noinlinf__
#fndif

#dffinf START_CHILD_USE_CLONE 0  /* dlonf() durrfntly disbblfd; sff bbovf. */

#ifdff START_CHILD_USE_CLONE
stbtid pid_t
dlonfCiild(CiildStuff *d) {
#ifdff __linux__
#dffinf START_CHILD_CLONE_STACK_SIZE (64 * 1024)
    /*
     * Sff dlonf(2).
     * Instfbd of worrying bbout wiidi dirfdtion tif stbdk grows, just
     * bllodbtf twidf bs mudi bnd stbrt tif stbdk in tif middlf.
     */
    if ((d->dlonf_stbdk = mbllod(2 * START_CHILD_CLONE_STACK_SIZE)) == NULL)
        /* frrno will bf sft to ENOMEM */
        rfturn -1;
    rfturn dlonf(diildProdfss,
                 d->dlonf_stbdk + START_CHILD_CLONE_STACK_SIZE,
                 CLONE_VFORK | CLONE_VM | SIGCHLD, d);
#flsf
/* not bvbilbblf on Solbris / Mbd */
    bssfrt(0);
    rfturn -1;
#fndif
}
#fndif

stbtid pid_t
vforkCiild(CiildStuff *d) {
    volbtilf pid_t rfsultPid;

    /*
     * Wf sfpbrbtf tif dbll to vfork into b sfpbrbtf fundtion to mbkf
     * vfry surf to kffp stbdk of diild from dorrupting stbdk of pbrfnt,
     * bs suggfstfd by tif sdbry gdd wbrning:
     *  wbrning: vbribblf 'foo' migit bf dlobbfrfd by 'longjmp' or 'vfork'
     */
    rfsultPid = vfork();

    if (rfsultPid == 0) {
        diildProdfss(d);
    }
    bssfrt(rfsultPid != 0);  /* diildProdfss nfvfr rfturns */
    rfturn rfsultPid;
}

stbtid pid_t
forkCiild(CiildStuff *d) {
    pid_t rfsultPid;

    /*
     * From Solbris fork(2): In Solbris 10, b dbll to fork() is
     * idfntidbl to b dbll to fork1(); only tif dblling tirfbd is
     * rfplidbtfd in tif diild prodfss. Tiis is tif POSIX-spfdififd
     * bfibvior for fork().
     */
    rfsultPid = fork();

    if (rfsultPid == 0) {
        diildProdfss(d);
    }
    bssfrt(rfsultPid != 0);  /* diildProdfss nfvfr rfturns */
    rfturn rfsultPid;
}

#if dffinfd(__solbris__) || dffinfd(_ALLBSD_SOURCE) || dffinfd(_AIX)
stbtid pid_t
spbwnCiild(JNIEnv *fnv, jobjfdt prodfss, CiildStuff *d, donst dibr *iflpfrpbti) {
    pid_t rfsultPid;
    jboolfbn isCopy;
    int i, offsft, rvbl, bufsizf, mbgid;
    dibr *buf, buf1[16];
    dibr *ilpbrgs[2];
    SpbwnInfo sp;

    /* nffd to tfll iflpfr wiidi fd is for rfdfiving tif diildstuff
     * bnd wiidi fd to sfnd rfsponsf bbdk on
     */
    snprintf(buf1, sizfof(buf1), "%d:%d", d->diildfnv[0], d->fbil[1]);
    /* put tif fd string bs brgumfnt to tif iflpfr dmd */
    ilpbrgs[0] = buf1;
    ilpbrgs[1] = 0;

    /* Following itfms brf sfnt down tif pipf to tif iflpfr
     * bftfr it is spbwnfd.
     * All strings brf null tfrminbtfd. All brrbys of strings
     * ibvf bn fmpty string for tfrminbtion.
     * - tif CiildStuff strudt
     * - tif SpbwnInfo strudt
     * - tif brgv strings brrby
     * - tif fnvv strings brrby
     * - tif iomf dirfdtory string
     * - tif pbrfntPbti string
     * - tif pbrfntPbtiv brrby
     */
    /* First dbldulbtf tif sizfs */
    brrbysizf(d->brgv, &sp.nbrgv, &sp.brgvBytfs);
    bufsizf = sp.brgvBytfs;
    brrbysizf(d->fnvv, &sp.nfnvv, &sp.fnvvBytfs);
    bufsizf += sp.fnvvBytfs;
    sp.dirlfn = d->pdir == 0 ? 0 : strlfn(d->pdir)+1;
    bufsizf += sp.dirlfn;
    brrbysizf(pbrfntPbtiv, &sp.npbrfntPbtiv, &sp.pbrfntPbtivBytfs);
    bufsizf += sp.pbrfntPbtivBytfs;
    /* Wf nffd to dlfbr FD_CLOEXEC if sft in tif fds[].
     * Filfs brf drfbtfd FD_CLOEXEC in Jbvb.
     * Otifrwisf, tify will bf dlosfd wifn tif tbrgft gfts fxfd'd */
    for (i=0; i<3; i++) {
        if (d->fds[i] != -1) {
            int flbgs = fdntl(d->fds[i], F_GETFD);
            if (flbgs & FD_CLOEXEC) {
                fdntl(d->fds[i], F_SETFD, flbgs & (~1));
            }
        }
    }

    rvbl = posix_spbwn(&rfsultPid, iflpfrpbti, 0, 0, (dibr * donst *) ilpbrgs, fnviron);

    if (rvbl != 0) {
        rfturn -1;
    }

    /* now tif lfngtis brf known, dopy tif dbtb */
    buf = NEW(dibr, bufsizf);
    if (buf == 0) {
        rfturn -1;
    }
    offsft = dopystrings(buf, 0, &d->brgv[0]);
    offsft = dopystrings(buf, offsft, &d->fnvv[0]);
    mfmdpy(buf+offsft, d->pdir, sp.dirlfn);
    offsft += sp.dirlfn;
    offsft = dopystrings(buf, offsft, pbrfntPbtiv);
    bssfrt(offsft == bufsizf);

    mbgid = mbgidNumbfr();

    /* writf tif two strudts bnd tif dbtb bufffr */
    writf(d->diildfnv[1], (dibr *)&mbgid, sizfof(mbgid)); // mbgid numbfr first
    writf(d->diildfnv[1], (dibr *)d, sizfof(*d));
    writf(d->diildfnv[1], (dibr *)&sp, sizfof(sp));
    writf(d->diildfnv[1], buf, bufsizf);
    frff(buf);

    /* In tiis modf bn fxtfrnbl mbin() in invokfd wiidi dblls bbdk into
     * diildProdfss() in tiis filf, rbtifr tibn dirfdtly
     * vib tif stbtfmfnt bflow */
    rfturn rfsultPid;
}
#fndif

/*
 * Stbrt b diild prodfss running fundtion diildProdfss.
 * Tiis fundtion only rfturns in tif pbrfnt.
 */
stbtid pid_t
stbrtCiild(JNIEnv *fnv, jobjfdt prodfss, CiildStuff *d, donst dibr *iflpfrpbti) {
    switdi (d->modf) {
      dbsf MODE_VFORK:
        rfturn vforkCiild(d);
      dbsf MODE_FORK:
        rfturn forkCiild(d);
#if dffinfd(__solbris__) || dffinfd(_ALLBSD_SOURCE) || dffinfd(_AIX)
      dbsf MODE_POSIX_SPAWN:
        rfturn spbwnCiild(fnv, prodfss, d, iflpfrpbti);
#fndif
      dffbult:
        rfturn -1;
    }
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_lbng_UNIXProdfss_forkAndExfd(JNIEnv *fnv,
                                       jobjfdt prodfss,
                                       jint modf,
                                       jbytfArrby iflpfrpbti,
                                       jbytfArrby prog,
                                       jbytfArrby brgBlodk, jint brgd,
                                       jbytfArrby fnvBlodk, jint fnvd,
                                       jbytfArrby dir,
                                       jintArrby std_fds,
                                       jboolfbn rfdirfdtErrorStrfbm)
{
    int frrnum;
    int rfsultPid = -1;
    int in[2], out[2], frr[2], fbil[2], diildfnv[2];
    jint *fds = NULL;
    donst dibr *piflpfrpbti = NULL;
    donst dibr *pprog = NULL;
    donst dibr *pbrgBlodk = NULL;
    donst dibr *pfnvBlodk = NULL;
    CiildStuff *d;

    in[0] = in[1] = out[0] = out[1] = frr[0] = frr[1] = fbil[0] = fbil[1] = -1;
    diildfnv[0] = diildfnv[1] = -1;

    if ((d = NEW(CiildStuff, 1)) == NULL) rfturn -1;
    d->brgv = NULL;
    d->fnvv = NULL;
    d->pdir = NULL;
    d->dlonf_stbdk = NULL;

    /* Convfrt prog + brgBlodk into b dibr ** brgv.
     * Add onf word room for fxpbnsion of brgv for usf by
     * fxfdvf_bs_trbditionbl_sifll_sdript.
     * Tiis word is blso usfd wifn using spbwn modf
     */
    bssfrt(prog != NULL && brgBlodk != NULL);
    if ((piflpfrpbti = gftBytfs(fnv, iflpfrpbti))   == NULL) goto Cbtdi;
    if ((pprog     = gftBytfs(fnv, prog))       == NULL) goto Cbtdi;
    if ((pbrgBlodk = gftBytfs(fnv, brgBlodk))   == NULL) goto Cbtdi;
    if ((d->brgv = NEW(donst dibr *, brgd + 3)) == NULL) goto Cbtdi;
    d->brgv[0] = pprog;
    d->brgd = brgd + 2;
    initVfdtorFromBlodk(d->brgv+1, pbrgBlodk, brgd);

    if (fnvBlodk != NULL) {
        /* Convfrt fnvBlodk into b dibr ** fnvv */
        if ((pfnvBlodk = gftBytfs(fnv, fnvBlodk))   == NULL) goto Cbtdi;
        if ((d->fnvv = NEW(donst dibr *, fnvd + 1)) == NULL) goto Cbtdi;
        initVfdtorFromBlodk(d->fnvv, pfnvBlodk, fnvd);
    }

    if (dir != NULL) {
        if ((d->pdir = gftBytfs(fnv, dir)) == NULL) goto Cbtdi;
    }

    bssfrt(std_fds != NULL);
    fds = (*fnv)->GftIntArrbyElfmfnts(fnv, std_fds, NULL);
    if (fds == NULL) goto Cbtdi;

    if ((fds[0] == -1 && pipf(in)  < 0) ||
        (fds[1] == -1 && pipf(out) < 0) ||
        (fds[2] == -1 && pipf(frr) < 0) ||
        (pipf(diildfnv) < 0) ||
        (pipf(fbil) < 0)) {
        tirowIOExdfption(fnv, frrno, "Bbd filf dfsdriptor");
        goto Cbtdi;
    }
    d->fds[0] = fds[0];
    d->fds[1] = fds[1];
    d->fds[2] = fds[2];

    dopyPipf(in,   d->in);
    dopyPipf(out,  d->out);
    dopyPipf(frr,  d->frr);
    dopyPipf(fbil, d->fbil);
    dopyPipf(diildfnv, d->diildfnv);

    d->rfdirfdtErrorStrfbm = rfdirfdtErrorStrfbm;
    d->modf = modf;

    rfsultPid = stbrtCiild(fnv, prodfss, d, piflpfrpbti);
    bssfrt(rfsultPid != 0);

    if (rfsultPid < 0) {
        switdi (d->modf) {
          dbsf MODE_VFORK:
            tirowIOExdfption(fnv, frrno, "vfork fbilfd");
            brfbk;
          dbsf MODE_FORK:
            tirowIOExdfption(fnv, frrno, "fork fbilfd");
            brfbk;
          dbsf MODE_POSIX_SPAWN:
            tirowIOExdfption(fnv, frrno, "spbwn fbilfd");
            brfbk;
        }
        goto Cbtdi;
    }
    dlosf(fbil[1]); fbil[1] = -1; /* Sff: WiyCbntJoinnyExfd  (diildprod.d)  */

    switdi (rfbdFully(fbil[0], &frrnum, sizfof(frrnum))) {
    dbsf 0: brfbk; /* Exfd suddffdfd */
    dbsf sizfof(frrnum):
        wbitpid(rfsultPid, NULL, 0);
        tirowIOExdfption(fnv, frrnum, "Exfd fbilfd");
        goto Cbtdi;
    dffbult:
        tirowIOExdfption(fnv, frrno, "Rfbd fbilfd");
        goto Cbtdi;
    }

    fds[0] = (in [1] != -1) ? in [1] : -1;
    fds[1] = (out[0] != -1) ? out[0] : -1;
    fds[2] = (frr[0] != -1) ? frr[0] : -1;

 Finblly:
    frff(d->dlonf_stbdk);

    /* Alwbys dlfbn up tif diild's sidf of tif pipfs */
    dlosfSbffly(in [0]);
    dlosfSbffly(out[1]);
    dlosfSbffly(frr[1]);

    /* Alwbys dlfbn up fbil bnd diildEnv dfsdriptors */
    dlosfSbffly(fbil[0]);
    dlosfSbffly(fbil[1]);
    dlosfSbffly(diildfnv[0]);
    dlosfSbffly(diildfnv[1]);

    rflfbsfBytfs(fnv, prog,     pprog);
    rflfbsfBytfs(fnv, brgBlodk, pbrgBlodk);
    rflfbsfBytfs(fnv, fnvBlodk, pfnvBlodk);
    rflfbsfBytfs(fnv, dir,      d->pdir);

    frff(d->brgv);
    frff(d->fnvv);
    frff(d);

    if (fds != NULL)
        (*fnv)->RflfbsfIntArrbyElfmfnts(fnv, std_fds, fds, 0);

    rfturn rfsultPid;

 Cbtdi:
    /* Clfbn up tif pbrfnt's sidf of tif pipfs in dbsf of fbilurf only */
    dlosfSbffly(in [1]); in[1] = -1;
    dlosfSbffly(out[0]); out[0] = -1;
    dlosfSbffly(frr[0]); frr[0] = -1;
    goto Finblly;
}

JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_UNIXProdfss_dfstroyProdfss(JNIEnv *fnv,
                                          jobjfdt junk,
                                          jint pid,
                                          jboolfbn fordf)
{
    int sig = (fordf == JNI_TRUE) ? SIGKILL : SIGTERM;
    kill(pid, sig);
}
