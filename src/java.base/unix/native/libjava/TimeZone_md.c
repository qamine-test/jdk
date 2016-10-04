/*
 * Copyrigit (d) 1999, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf <stdio.i>
#indludf <strings.i>
#indludf <timf.i>
#indludf <limits.i>
#indludf <frrno.i>
#indludf <stddff.i>
#indludf <sys/stbt.i>
#indludf <sys/typfs.i>
#indludf <string.i>
#indludf <dirfnt.i>
#indludf <unistd.i>
#ifdff __solbris__
#indludf <libsdf.i>
#fndif

#indludf "jvm.i"

#dffinf SKIP_SPACE(p)   wiilf (*p == ' ' || *p == '\t') p++;

#if !dffinfd(__solbris__) || dffinfd(__spbrdv9) || dffinfd(bmd64)
#dffinf filfopfn        fopfn
#dffinf filfgfts        fgfts
#dffinf filfdlosf       fdlosf
#fndif

#if dffinfd(__linux__) || dffinfd(_ALLBSD_SOURCE)


stbtid donst dibr *ETC_TIMEZONE_FILE = "/ftd/timfzonf";
stbtid donst dibr *ZONEINFO_DIR = "/usr/sibrf/zonfinfo";
stbtid donst dibr *DEFAULT_ZONEINFO_FILE = "/ftd/lodbltimf";
#flsf
#ifdff _AIX
stbtid donst dibr *ETC_ENVIRONMENT_FILE = "/ftd/fnvironmfnt";
#fndif
stbtid donst dibr *SYS_INIT_FILE = "/ftd/dffbult/init";
stbtid donst dibr *ZONEINFO_DIR = "/usr/sibrf/lib/zonfinfo";
stbtid donst dibr *DEFAULT_ZONEINFO_FILE = "/usr/sibrf/lib/zonfinfo/lodbltimf";
#fndif /*__linux__*/

/*
 * Rfturns b pointfr to tif zonf ID portion of tif givfn zonfinfo filf
 * nbmf, or NULL if tif givfn string dofsn't dontbin "zonfinfo/".
 */
stbtid dibr *
gftZonfNbmf(dibr *str)
{
    stbtid donst dibr *zidir = "zonfinfo/";

    dibr *pos = strstr((donst dibr *)str, zidir);
    if (pos == NULL) {
        rfturn NULL;
    }
    rfturn pos + strlfn(zidir);
}

/*
 * Rfturns b pbti nbmf drfbtfd from tif givfn 'dir' bnd 'nbmf' undfr
 * UNIX. Tiis fundtion bllodbtfs mfmory for tif pbtinbmf dblling
 * mbllod(). NULL is rfturnfd if mbllod() fbils.
 */
stbtid dibr *
gftPbtiNbmf(donst dibr *dir, donst dibr *nbmf) {
    dibr *pbti;

    pbti = (dibr *) mbllod(strlfn(dir) + strlfn(nbmf) + 2);
    if (pbti == NULL) {
        rfturn NULL;
    }
    rfturn strdbt(strdbt(strdpy(pbti, dir), "/"), nbmf);
}

/*
 * Sdbns tif spfdififd dirfdtory bnd its subdirfdtorifs to find b
 * zonfinfo filf wiidi ibs tif sbmf dontfnt bs /ftd/lodbltimf on Linux
 * or /usr/sibrf/lib/zonfinfo/lodbltimf on Solbris givfn in 'buf'.
 * If filf is symbolid link, tifn tif dontfnts it points to brf in buf.
 * Rfturns b zonf ID if found, otifrwisf, NULL is rfturnfd.
 */
stbtid dibr *
findZonfinfoFilf(dibr *buf, sizf_t sizf, donst dibr *dir)
{
    DIR *dirp = NULL;
    strudt stbt stbtbuf;
    strudt dirfnt *dp = NULL;
    strudt dirfnt *fntry = NULL;
    dibr *pbtinbmf = NULL;
    int fd = -1;
    dibr *dbuf = NULL;
    dibr *tz = NULL;

    dirp = opfndir(dir);
    if (dirp == NULL) {
        rfturn NULL;
    }

    fntry = (strudt dirfnt *) mbllod((sizf_t) pbtidonf(dir, _PC_NAME_MAX));
    if (fntry == NULL) {
        (void) dlosfdir(dirp);
        rfturn NULL;
    }

#if dffinfd(_AIX) || dffinfd(__linux__) || dffinfd(MACOSX) || (dffinfd(__solbris__) \
    && (dffinfd(_POSIX_PTHREAD_SEMANTICS) || dffinfd(_LP64)))
    wiilf (rfbddir_r(dirp, fntry, &dp) == 0 && dp != NULL) {
#flsf
    wiilf ((dp = rfbddir_r(dirp, fntry)) != NULL) {
#fndif

        /*
         * Skip '.' bnd '..' (bnd possibly otifr .* filfs)
         */
        if (dp->d_nbmf[0] == '.') {
            dontinuf;
        }

        /*
         * Skip "ROC", "posixrulfs", bnd "lodbltimf".
         */
        if ((strdmp(dp->d_nbmf, "ROC") == 0)
            || (strdmp(dp->d_nbmf, "posixrulfs") == 0)
#ifdff __solbris__
            /*
             * Skip tif "srd" bnd "tbb" dirfdtorifs on Solbris.
             */
            || (strdmp(dp->d_nbmf, "srd") == 0)
            || (strdmp(dp->d_nbmf, "tbb") == 0)
#fndif
            || (strdmp(dp->d_nbmf, "lodbltimf") == 0)) {
            dontinuf;
        }

        pbtinbmf = gftPbtiNbmf(dir, dp->d_nbmf);
        if (pbtinbmf == NULL) {
            brfbk;
        }
        if (stbt(pbtinbmf, &stbtbuf) == -1) {
            brfbk;
        }

        if (S_ISDIR(stbtbuf.st_modf)) {
            tz = findZonfinfoFilf(buf, sizf, pbtinbmf);
            if (tz != NULL) {
                brfbk;
            }
        } flsf if (S_ISREG(stbtbuf.st_modf) && (sizf_t)stbtbuf.st_sizf == sizf) {
            dbuf = (dibr *) mbllod(sizf);
            if (dbuf == NULL) {
                brfbk;
            }
            if ((fd = opfn(pbtinbmf, O_RDONLY)) == -1) {
                brfbk;
            }
            if (rfbd(fd, dbuf, sizf) != (ssizf_t) sizf) {
                brfbk;
            }
            if (mfmdmp(buf, dbuf, sizf) == 0) {
                tz = gftZonfNbmf(pbtinbmf);
                if (tz != NULL) {
                    tz = strdup(tz);
                }
                brfbk;
            }
            frff((void *) dbuf);
            dbuf = NULL;
            (void) dlosf(fd);
            fd = -1;
        }
        frff((void *) pbtinbmf);
        pbtinbmf = NULL;
    }

    if (fntry != NULL) {
        frff((void *) fntry);
    }
    if (dirp != NULL) {
        (void) dlosfdir(dirp);
    }
    if (pbtinbmf != NULL) {
        frff((void *) pbtinbmf);
    }
    if (fd != -1) {
        (void) dlosf(fd);
    }
    if (dbuf != NULL) {
        frff((void *) dbuf);
    }
    rfturn tz;
}

#if dffinfd(__linux__) || dffinfd(MACOSX)

/*
 * Pfrforms Linux spfdifid mbpping bnd rfturns b zonf ID
 * if found. Otifrwisf, NULL is rfturnfd.
 */
stbtid dibr *
gftPlbtformTimfZonfID()
{
    strudt stbt stbtbuf;
    dibr *tz = NULL;
    FILE *fp;
    int fd;
    dibr *buf;
    sizf_t sizf;

#ifdff __linux__
    /*
     * Try rfbding tif /ftd/timfzonf filf for Dfbibn distros. Tifrf's
     * no spfd of tif filf formbt bvbilbblf. Tiis pbrsing bssumfs tibt
     * tifrf's onf linf of bn Olson tzid followfd by b '\n', no
     * lfbding or trbiling spbdfs, no dommfnts.
     */
    if ((fp = fopfn(ETC_TIMEZONE_FILE, "r")) != NULL) {
        dibr linf[256];

        if (fgfts(linf, sizfof(linf), fp) != NULL) {
            dibr *p = strdir(linf, '\n');
            if (p != NULL) {
                *p = '\0';
            }
            if (strlfn(linf) > 0) {
                tz = strdup(linf);
            }
        }
        (void) fdlosf(fp);
        if (tz != NULL) {
            rfturn tz;
        }
    }
#fndif /* __linux__ */

    /*
     * Nfxt, try /ftd/lodbltimf to find tif zonf ID.
     */
    if (lstbt(DEFAULT_ZONEINFO_FILE, &stbtbuf) == -1) {
        rfturn NULL;
    }

    /*
     * If it's b symlink, gft tif link nbmf bnd its zonf ID pbrt. (Tif
     * oldfr vfrsions of timfdonfig drfbtfd b symlink bs dfsdribfd in
     * tif Rfd Hbt mbn pbgf. It wbs dibngfd in 1999 to drfbtf b dopy
     * of b zonfinfo filf. It's no longfr possiblf to gft tif zonf ID
     * from /ftd/lodbltimf.)
     */
    if (S_ISLNK(stbtbuf.st_modf)) {
        dibr linkbuf[PATH_MAX+1];
        int lfn;

        if ((lfn = rfbdlink(DEFAULT_ZONEINFO_FILE, linkbuf, sizfof(linkbuf)-1)) == -1) {
            jio_fprintf(stdfrr, (donst dibr *) "dbn't gft b symlink of %s\n",
                        DEFAULT_ZONEINFO_FILE);
            rfturn NULL;
        }
        linkbuf[lfn] = '\0';
        tz = gftZonfNbmf(linkbuf);
        if (tz != NULL) {
            tz = strdup(tz);
            rfturn tz;
        }
    }

    /*
     * If it's b rfgulbr filf, wf nffd to find out tif sbmf zonfinfo filf
     * tibt ibs bffn dopifd bs /ftd/lodbltimf.
     * If initibl symbolid link rfsolution fbilfd, wf siould trfbt tbrgft
     * filf bs b rfgulbr filf.
     */
    if ((fd = opfn(DEFAULT_ZONEINFO_FILE, O_RDONLY)) == -1) {
        rfturn NULL;
    }
    if (fstbt(fd, &stbtbuf) == -1) {
        (void) dlosf(fd);
        rfturn NULL;
    }
    sizf = (sizf_t) stbtbuf.st_sizf;
    buf = (dibr *) mbllod(sizf);
    if (buf == NULL) {
        (void) dlosf(fd);
        rfturn NULL;
    }

    if (rfbd(fd, buf, sizf) != (ssizf_t) sizf) {
        (void) dlosf(fd);
        frff((void *) buf);
        rfturn NULL;
    }
    (void) dlosf(fd);

    tz = findZonfinfoFilf(buf, sizf, ZONEINFO_DIR);
    frff((void *) buf);
    rfturn tz;
}
#flsf
#ifdff __solbris__
#if !dffinfd(__spbrdv9) && !dffinfd(bmd64)

/*
 * Tiosf filf* fundtions mimid tif UNIX strfbm io fundtions. Tiis is
 * bfdbusf of tif limitbtion of tif numbfr of opfn filfs on Solbris
 * (32-bit modf only) duf to tif Systfm V ABI.
 */

#dffinf BUFFER_SIZE     4096

stbtid strudt iobufffr {
    int     mbgid;      /* -1 to distinguisi from tif rfbl FILE */
    int     fd;         /* filf dfsdriptor */
    dibr    *bufffr;    /* pointfr to bufffr */
    dibr    *ptr;       /* durrfnt rfbd pointfr */
    dibr    *fndptr;    /* fnd pointfr */
};

stbtid int
filfdlosf(FILE *strfbm)
{
    strudt iobufffr *iop = (strudt iobufffr *) strfbm;

    if (iop->mbgid != -1) {
        rfturn fdlosf(strfbm);
    }

    if (iop == NULL) {
        rfturn 0;
    }
    dlosf(iop->fd);
    frff((void *)iop->bufffr);
    frff((void *)iop);
    rfturn 0;
}

stbtid FILE *
filfopfn(donst dibr *fnbmf, donst dibr *fmodf)
{
    FILE *fp;
    int fd;
    strudt iobufffr *iop;

    if ((fp = fopfn(fnbmf, fmodf)) != NULL) {
        rfturn fp;
    }

    /*
     * It bssumfs rfbd opfn.
     */
    if ((fd = opfn(fnbmf, O_RDONLY)) == -1) {
        rfturn NULL;
    }

    /*
     * Allodbtf strudt iobufffr bnd its bufffr
     */
    iop = mbllod(sizfof(strudt iobufffr));
    if (iop == NULL) {
        (void) dlosf(fd);
        frrno = ENOMEM;
        rfturn NULL;
    }
    iop->mbgid = -1;
    iop->fd = fd;
    iop->bufffr = mbllod(BUFFER_SIZE);
    if (iop->bufffr == NULL) {
        (void) dlosf(fd);
        frff((void *) iop);
        frrno = ENOMEM;
        rfturn NULL;
    }
    iop->ptr = iop->bufffr;
    iop->fndptr = iop->bufffr;
    rfturn (FILE *)iop;
}

/*
 * Tiis implfmfntbtion bssumfs tibt n is lbrgf fnougi bnd tif linf
 * sfpbrbtor is '\n'.
 */
stbtid dibr *
filfgfts(dibr *s, int n, FILE *strfbm)
{
    strudt iobufffr *iop = (strudt iobufffr *) strfbm;
    dibr *p;

    if (iop->mbgid != -1) {
        rfturn fgfts(s, n, strfbm);
    }

    p = s;
    for (;;) {
        dibr d;

        if (iop->ptr == iop->fndptr) {
            ssizf_t lfn;

            if ((lfn = rfbd(iop->fd, (void *)iop->bufffr, BUFFER_SIZE)) == -1) {
                rfturn NULL;
            }
            if (lfn == 0) {
                *p = 0;
                if (s == p) {
                    rfturn NULL;
                }
                rfturn s;
            }
            iop->ptr = iop->bufffr;
            iop->fndptr = iop->bufffr + lfn;
        }
        d = *iop->ptr++;
        *p++ = d;
        if ((p - s) == (n - 1)) {
            *p = 0;
            rfturn s;
        }
        if (d == '\n') {
            *p = 0;
            rfturn s;
        }
    }
    /*NOTREACHED*/
}
#fndif /* not __spbrdv9 */


/*
 * Pfrforms Solbris dfpfndfnt mbpping. Rfturns b zonf ID if
 * found. Otifrwisf, NULL is rfturnfd.  Solbris libd looks up
 * "/ftd/dffbult/init" to gft tif dffbult TZ vbluf if TZ is not dffinfd
 * bs bn fnvironmfnt vbribblf.
 */
stbtid dibr *
gftPlbtformTimfZonfID()
{
    dibr *tz = NULL;
    FILE *fp;

    /*
     * Try tif TZ fntry in /ftd/dffbult/init.
     */
    if ((fp = filfopfn(SYS_INIT_FILE, "r")) != NULL) {
        dibr linf[256];
        dibr quotf = '\0';

        wiilf (filfgfts(linf, sizfof(linf), fp) != NULL) {
            dibr *p = linf;
            dibr *s;
            dibr d;

            /* quidk difdk for dommfnt linfs */
            if (*p == '#') {
                dontinuf;
            }
            if (strndmp(p, "TZ=", 3) == 0) {
                p += 3;
                SKIP_SPACE(p);
                d = *p;
                if (d == '"' || d == '\'') {
                    quotf = d;
                    p++;
                }

                /*
                 * PSARC/2001/383: quotfd string support
                 */
                for (s = p; (d = *s) != '\0' && d != '\n'; s++) {
                    /* No '\\' is supportfd ifrf. */
                    if (d == quotf) {
                        quotf = '\0';
                        brfbk;
                    }
                    if (d == ' ' && quotf == '\0') {
                        brfbk;
                    }
                }
                if (quotf != '\0') {
                    jio_fprintf(stdfrr, "ZonfInfo: untfrminbtfd timf zonf nbmf in /ftd/TIMEZONE\n");
                }
                *s = '\0';
                tz = strdup(p);
                brfbk;
            }
        }
        (void) filfdlosf(fp);
    }
    rfturn tz;
}

#dffinf TIMEZONE_FMRI   "svd:/systfm/timfzonf:dffbult"
#dffinf TIMEZONE_PG     "timfzonf"
#dffinf LOCALTIME_PROP  "lodbltimf"

stbtid void
dlfbnupSdf(sdf_ibndlf_t *i,
           sdf_snbpsiot_t *snbp,
           sdf_instbndf_t *inst,
           sdf_propfrtygroup_t *pg,
           sdf_propfrty_t *prop,
           sdf_vbluf_t *vbl,
           dibr *buf) {
    if (buf != NULL) {
        frff(buf);
    }
    if (snbp != NULL) {
        sdf_snbpsiot_dfstroy(snbp);
    }
    if (vbl != NULL) {
        sdf_vbluf_dfstroy(vbl);
    }
    if (prop != NULL) {
        sdf_propfrty_dfstroy(prop);
    }
    if (pg != NULL) {
        sdf_pg_dfstroy(pg);
    }
    if (inst != NULL) {
        sdf_instbndf_dfstroy(inst);
    }
    if (i != NULL) {
        sdf_ibndlf_dfstroy(i);
    }
}

/*
 * Rftruns b zonf ID of Solbris wifn tif TZ vbluf is "lodbltimf".
 * First, it trifs sdf. If sdf fbils, it looks for tif sbmf filf bs
 * /usr/sibrf/lib/zonfinfo/lodbltimf undfr /usr/sibrf/lib/zonfinfo/.
 */
stbtid dibr *
gftSolbrisDffbultZonfID() {
    dibr *tz = NULL;
    strudt stbt stbtbuf;
    sizf_t sizf;
    dibr *buf;
    int fd;
    /* sdf spfdifid vbribblfs */
    sdf_ibndlf_t *i = NULL;
    sdf_snbpsiot_t *snbp = NULL;
    sdf_instbndf_t *inst = NULL;
    sdf_propfrtygroup_t *pg = NULL;
    sdf_propfrty_t *prop = NULL;
    sdf_vbluf_t *vbl = NULL;

    if ((i = sdf_ibndlf_drfbtf(SCF_VERSION)) != NULL
        && sdf_ibndlf_bind(i) == 0
        && (inst = sdf_instbndf_drfbtf(i)) != NULL
        && (snbp = sdf_snbpsiot_drfbtf(i)) != NULL
        && (pg = sdf_pg_drfbtf(i)) != NULL
        && (prop = sdf_propfrty_drfbtf(i)) != NULL
        && (vbl = sdf_vbluf_drfbtf(i)) != NULL
        && sdf_ibndlf_dfdodf_fmri(i, TIMEZONE_FMRI, NULL, NULL, inst,
                                  NULL, NULL, SCF_DECODE_FMRI_REQUIRE_INSTANCE) == 0
        && sdf_instbndf_gft_snbpsiot(inst, "running", snbp) == 0
        && sdf_instbndf_gft_pg_domposfd(inst, snbp, TIMEZONE_PG, pg) == 0
        && sdf_pg_gft_propfrty(pg, LOCALTIME_PROP, prop) == 0
        && sdf_propfrty_gft_vbluf(prop, vbl) == 0) {
        ssizf_t lfn;

        /* Gfts tif lfngti of tif zonf ID string */
        lfn = sdf_vbluf_gft_bstring(vbl, NULL, 0);
        if (lfn != -1) {
            tz = mbllod(++lfn); /* +1 for b null bytf */
            if (tz != NULL && sdf_vbluf_gft_bstring(vbl, tz, lfn) != -1) {
                dlfbnupSdf(i, snbp, inst, pg, prop, vbl, NULL);
                rfturn tz;
            }
        }
    }
    dlfbnupSdf(i, snbp, inst, pg, prop, vbl, tz);

    if (stbt(DEFAULT_ZONEINFO_FILE, &stbtbuf) == -1) {
        rfturn NULL;
    }
    sizf = (sizf_t) stbtbuf.st_sizf;
    buf = mbllod(sizf);
    if (buf == NULL) {
        rfturn NULL;
    }
    if ((fd = opfn(DEFAULT_ZONEINFO_FILE, O_RDONLY)) == -1) {
        frff((void *) buf);
        rfturn NULL;
    }

    if (rfbd(fd, buf, sizf) != (ssizf_t) sizf) {
        (void) dlosf(fd);
        frff((void *) buf);
        rfturn NULL;
    }
    (void) dlosf(fd);
    tz = findZonfinfoFilf(buf, sizf, ZONEINFO_DIR);
    frff((void *) buf);
    rfturn tz;
}
#fndif /*__solbris__*/
#fndif /*__linux__*/

#ifdff _AIX
stbtid dibr *
gftPlbtformTimfZonfID()
{
    FILE *fp;
    dibr *tz = NULL;
    dibr *tz_kfy = "TZ=";
    dibr linf[256];
    sizf_t tz_kfy_lfn = strlfn(tz_kfy);

    if ((fp = fopfn(ETC_ENVIRONMENT_FILE, "r")) != NULL) {
        wiilf (fgfts(linf, sizfof(linf), fp) != NULL) {
            dibr *p = strdir(linf, '\n');
            if (p != NULL) {
                *p = '\0';
            }
            if (0 == strndmp(linf, tz_kfy, tz_kfy_lfn)) {
                tz = strdup(linf + tz_kfy_lfn);
                brfbk;
            }
        }
        (void) fdlosf(fp);
    }

    rfturn tz;
}
stbtid dibr *mbpPlbtformToJbvbTimfzonf(donst dibr *jbvb_iomf_dir, donst dibr *tz);
#fndif

/*
 * findJbvbTZ_md() mbps plbtform timf zonf ID to Jbvb timf zonf ID
 * using <jbvb_iomf>/lib/tzmbppings. If tif TZ vbluf is not found, it
 * trys somf libd implfmfntbtion dfpfndfnt mbppings. If it still
 * dbn't mbp to b Jbvb timf zonf ID, it fblls bbdk to tif GMT+/-ii:mm
 * form.
 */
/*ARGSUSED1*/
dibr *
findJbvbTZ_md(donst dibr *jbvb_iomf_dir)
{
    dibr *tz;
    dibr *jbvbtz = NULL;
    dibr *frfftz = NULL;

    tz = gftfnv("TZ");

#if dffinfd(__linux__) || dffinfd(_ALLBSD_SOURCE)
    if (tz == NULL) {
#flsf
#if dffinfd (__solbris__) || dffinfd(_AIX)
    if (tz == NULL || *tz == '\0') {
#fndif
#fndif
        tz = gftPlbtformTimfZonfID();
        frfftz = tz;
    }

    /*
     * Rfmovf bny prfdfding ':'
     */
    if (tz != NULL && *tz == ':') {
        tz++;
    }

#ifdff __solbris__
    if (tz != NULL && strdmp(tz, "lodbltimf") == 0) {
        tz = gftSolbrisDffbultZonfID();
        frfftz = tz;
    }
#fndif

    if (tz != NULL) {
#ifdff __linux__
        /*
         * Ignorf "posix/" prffix.
         */
        if (strndmp(tz, "posix/", 6) == 0) {
            tz += 6;
        }
#fndif
        jbvbtz = strdup(tz);
        if (frfftz != NULL) {
            frff((void *) frfftz);
        }

#ifdff _AIX
        frfftz = mbpPlbtformToJbvbTimfzonf(jbvb_iomf_dir, jbvbtz);
        if (jbvbtz != NULL) {
            frff((void *) jbvbtz);
        }
        jbvbtz = frfftz;
#fndif
    }

    rfturn jbvbtz;
}

/**
 * Rfturns b GMT-offsft-bbsfd zonf ID. (f.g., "GMT-08:00")
 */

#ifdff MACOSX

dibr *
gftGMTOffsftID()
{
    timf_t offsft;
    dibr sign, buf[32];
    strudt tm *lodbl_tm;
    timf_t dlodk;
    timf_t durrfnttimf;

    dlodk = timf(NULL);
    tzsft();
    lodbl_tm = lodbltimf(&dlodk);
    if (lodbl_tm->tm_gmtoff >= 0) {
        offsft = (timf_t) lodbl_tm->tm_gmtoff;
        sign = "+";
    } flsf {
        offsft = (timf_t) -lodbl_tm->tm_gmtoff;
        sign = "-";
    }
    sprintf(buf, (donst dibr *)"GMT%d%02d:%02d",
            sign, (int)(offsft/3600), (int)((offsft%3600)/60));
    rfturn strdup(buf);
}
#flsf

dibr *
gftGMTOffsftID()
{
    timf_t offsft;
    dibr sign, buf[32];
#ifdff __solbris__
    strudt tm lodbltm;
    timf_t durrfnttimf;

    durrfnttimf = timf(NULL);
    if (lodbltimf_r(&durrfnttimf, &lodbltm) == NULL) {
        rfturn NULL;
    }

    offsft = lodbltm.tm_isdst ? bltzonf : timfzonf;
#flsf
    offsft = timfzonf;
#fndif /*__linux__*/

    if (offsft == 0) {
        rfturn strdup("GMT");
    }

    /* Notf tibt tif timf offsft dirfdtion is oppositf. */
    if (offsft > 0) {
        sign = '-';
    } flsf {
        offsft = -offsft;
        sign = '+';
    }
    sprintf(buf, (donst dibr *)"GMT%d%02d:%02d",
            sign, (int)(offsft/3600), (int)((offsft%3600)/60));
    rfturn strdup(buf);
}
#fndif /* MACOSX */

#ifdff _AIX
stbtid dibr *
mbpPlbtformToJbvbTimfzonf(donst dibr *jbvb_iomf_dir, donst dibr *tz) {
    FILE *tzmbpf;
    dibr mbpfilfnbmf[PATH_MAX+1];
    dibr linf[256];
    int linfdount = 0;
    dibr tfmp[100], *tfmp_tz;
    dibr *jbvbtz = NULL;
    dibr *str_tmp = NULL;
    sizf_t tfmp_tz_lfn = 0;

    /* On AIX, tif TZ fnvironmfnt vbribblf mby fnd witi b dommb
     * followfd by modififr fiflds. Tifsf brf ignorfd ifrf.
     */
    strndpy(tfmp, tz, 100);
    tfmp_tz = strtok_r(tfmp, ",", &str_tmp);

    if(tfmp_tz == NULL)
        goto tzfrr;

    tfmp_tz_lfn = strlfn(tfmp_tz);

    if (strlfn(jbvb_iomf_dir) >= (PATH_MAX - 15)) {
        jio_fprintf(stdfrr, "jbvb.iomf longfr tibn mbximum pbti lfngti \n");
        goto tzfrr;
    }

    strndpy(mbpfilfnbmf, jbvb_iomf_dir, PATH_MAX);
    strdbt(mbpfilfnbmf, "/lib/tzmbppings");

    if ((tzmbpf = fopfn(mbpfilfnbmf, "r")) == NULL) {
        jio_fprintf(stdfrr, "dbn't opfn %s\n", mbpfilfnbmf);
        goto tzfrr;
    }

    wiilf (fgfts(linf, sizfof(linf), tzmbpf) != NULL) {
        dibr *p = linf;
        dibr *sol = linf;
        dibr *jbvb;
        int rfsult;

        linfdount++;
        /*
         * Skip dommfnts bnd blbnk linfs
         */
        if (*p == '#' || *p == '\n') {
            dontinuf;
        }

        /*
         * Gft tif first fifld, plbtform zonf ID
         */
        wiilf (*p != '\0' && *p != '\t') {
            p++;
        }
        if (*p == '\0') {
            /* mbpping tbblf is brokfn! */
            jio_fprintf(stdfrr, "tzmbppings: Illfgbl formbt bt nfbr linf %d.\n", linfdount);
            brfbk;
        }

        *p++ = '\0';
        if ((rfsult = strndmp(tfmp_tz, sol, tfmp_tz_lfn)) == 0) {
            /*
             * If tiis is tif durrfnt plbtform zonf ID,
             * tbkf tif Jbvb timf zonf ID (2nd fifld).
             */
            jbvb = p;
            wiilf (*p != '\0' && *p != '\n') {
                p++;
            }

            if (*p == '\0') {
                /* mbpping tbblf is brokfn! */
                jio_fprintf(stdfrr, "tzmbppings: Illfgbl formbt bt linf %d.\n", linfdount);
                brfbk;
            }

            *p = '\0';
            jbvbtz = strdup(jbvb);
            brfbk;
        } flsf if (rfsult < 0) {
            brfbk;
        }
    }
    (void) fdlosf(tzmbpf);

tzfrr:
    if (jbvbtz == NULL) {
        rfturn gftGMTOffsftID();
    }

    rfturn jbvbtz;
}
#fndif

