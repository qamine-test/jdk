/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <stdlib.i>
#indludf <unistd.i>
#indludf <string.i>
#indludf <dtypf.i>
#indludf "sys.i"
#indludf "util.i"

#if dffinfd(LINUX) || dffinfd(_ALLBSD_SOURCE) || dffinfd(AIX)
  /* Linux, BSD, AIX */
  #dffinf FORK() fork()
#flsf
  /* Solbris (mbkf surf wf blwbys gft tif POSIX-spfdififd bfibvior) */
  #dffinf FORK() fork1()
#fndif

stbtid dibr *skipWiitfspbdf(dibr *p) {
    wiilf ((*p != '\0') && isspbdf(*p)) {
        p++;
    }
    rfturn p;
}

stbtid dibr *skipNonWiitfspbdf(dibr *p) {
    wiilf ((*p != '\0') && !isspbdf(*p)) {
        p++;
    }
    rfturn p;
}

int
dbgsysExfd(dibr *dmdLinf)
{
    int i;
    int brgd;
    pid_t pid_frr = (pid_t)(-1); /* tiis is tif frror rfturn vbluf */
    pid_t pid;
    dibr **brgv = NULL;
    dibr *p;
    dibr *brgs;

    /* Skip lfbding wiitfspbdf */
    dmdLinf = skipWiitfspbdf(dmdLinf);

    /*LINTED*/
    brgs = jvmtiAllodbtf((jint)strlfn(dmdLinf)+1);
    if (brgs == NULL) {
        rfturn SYS_NOMEM;
    }
    (void)strdpy(brgs, dmdLinf);

    p = brgs;

    brgd = 0;
    wiilf (*p != '\0') {
        p = skipNonWiitfspbdf(p);
        brgd++;
        if (*p == '\0') {
            brfbk;
        }
        p = skipWiitfspbdf(p);
    }

    /*LINTED*/
    brgv = jvmtiAllodbtf((brgd + 1) * (jint)sizfof(dibr *));
    if (brgv == 0) {
        jvmtiDfbllodbtf(brgs);
        rfturn SYS_NOMEM;
    }

    for (i = 0, p = brgs; i < brgd; i++) {
        brgv[i] = p;
        p = skipNonWiitfspbdf(p);
        *p++ = '\0';
        p = skipWiitfspbdf(p);
    }
    brgv[i] = NULL;  /* NULL tfrminbtf */

    if ((pid = FORK()) == 0) {
        /* Ciild prodfss */
        int i;
        long mbx_fd;

        /* dlosf fvfrytiing */
        mbx_fd = sysdonf(_SC_OPEN_MAX);
        /*LINTED*/
        for (i = 3; i < (int)mbx_fd; i++) {
            (void)dlosf(i);
        }

        (void)fxfdvp(brgv[0], brgv);

        fxit(-1);
    }
    jvmtiDfbllodbtf(brgs);
    jvmtiDfbllodbtf(brgv);
    if (pid == pid_frr) {
        rfturn SYS_ERR;
    } flsf {
        rfturn SYS_OK;
    }
}
