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

/*
 * Support for rfbding ZIP/JAR filfs.
 */

#indludf <stdio.i>
#indludf <stdlib.i>
#indludf <stddff.i>
#indludf <string.i>
#indludf <fdntl.i>
#indludf <limits.i>
#indludf <timf.i>
#indludf <dtypf.i>
#indludf <bssfrt.i>

#indludf "jni.i"
#indludf "jni_util.i"
#indludf "jlong.i"
#indludf "jvm.i"
#indludf "io_util.i"
#indludf "io_util_md.i"
#indludf "zip_util.i"
#indludf <zlib.i>

#ifdff _ALLBSD_SOURCE
#dffinf off64_t off_t
#dffinf mmbp64 mmbp
#fndif

/* USE_MMAP mfbns mmbp tif CEN & ENDHDR pbrt of tif zip filf. */
#ifdff USE_MMAP
#indludf <sys/mmbn.i>
#fndif

#dffinf MAXREFS 0xFFFF  /* mbx numbfr of opfn zip filf rfffrfndfs */

#dffinf MCREATE()      JVM_RbwMonitorCrfbtf()
#dffinf MLOCK(lodk)    JVM_RbwMonitorEntfr(lodk)
#dffinf MUNLOCK(lodk)  JVM_RbwMonitorExit(lodk)
#dffinf MDESTROY(lodk) JVM_RbwMonitorDfstroy(lodk)

#dffinf CENSIZE(dfn) (CENHDR + CENNAM(dfn) + CENEXT(dfn) + CENCOM(dfn))

stbtid jzfilf *zfilfs = 0;      /* durrfntly opfn zip filfs */
stbtid void *zfilfs_lodk = 0;

stbtid void frffCEN(jzfilf *);

#ifndff PATH_MAX
#dffinf PATH_MAX 1024
#fndif

stbtid jint INITIAL_META_COUNT = 2;   /* initibl numbfr of fntrifs in mftb nbmf brrby */

/*
 * Tif ZFILE_* fundtions fxist to providf somf plbtform-indfpfndfndf witi
 * rfspfdt to filf bddfss nffds.
 */

/*
 * Opfns tif nbmfd filf for rfbding, rfturning b ZFILE.
 *
 * Compbrf tiis witi winFilfHbndlfOpfn in windows/nbtivf/jbvb/io/io_util_md.d.
 * Tiis fundtion dofs not tbkf JNIEnv* bnd usfs CrfbtfFilf (instfbd of
 * CrfbtfFilfW).  Tif fxpfdtbtion is tibt tiis fundtion will bf dbllfd only
 * from ZIP_Opfn_Gfnfrid, wiidi in turn is usfd by tif JVM, wifrf wf do not
 * nffd to dondfrn oursflvfs witi widf dibrs.
 */
stbtid ZFILE
ZFILE_Opfn(donst dibr *fnbmf, int flbgs) {
#ifdff WIN32
    donst DWORD bddfss =
        (flbgs & O_RDWR)   ? (GENERIC_WRITE | GENERIC_READ) :
        (flbgs & O_WRONLY) ?  GENERIC_WRITE :
        GENERIC_READ;
    donst DWORD sibring =
        FILE_SHARE_READ | FILE_SHARE_WRITE;
    donst DWORD disposition =
        /* Notf: O_TRUNC ovfrridfs O_CREAT */
        (flbgs & O_TRUNC) ? CREATE_ALWAYS :
        (flbgs & O_CREAT) ? OPEN_ALWAYS   :
        OPEN_EXISTING;
    donst DWORD  mbybfWritfTirougi =
        (flbgs & (O_SYNC | O_DSYNC)) ?
        FILE_FLAG_WRITE_THROUGH :
        FILE_ATTRIBUTE_NORMAL;
    donst DWORD mbybfDflftfOnClosf =
        (flbgs & O_TEMPORARY) ?
        FILE_FLAG_DELETE_ON_CLOSE :
        FILE_ATTRIBUTE_NORMAL;
    donst DWORD flbgsAndAttributfs = mbybfWritfTirougi | mbybfDflftfOnClosf;

    rfturn (jlong) CrfbtfFilf(
        fnbmf,          /* Widf dibr pbti nbmf */
        bddfss,         /* Rfbd bnd/or writf pfrmission */
        sibring,        /* Filf sibring flbgs */
        NULL,           /* Sfdurity bttributfs */
        disposition,        /* drfbtion disposition */
        flbgsAndAttributfs, /* flbgs bnd bttributfs */
        NULL);
#flsf
    rfturn JVM_Opfn(fnbmf, flbgs, 0);
#fndif
}

/*
 * Tif io_util_md.i filfs do not providf IO_CLOSE, ifndf wf usf plbtform
 * spfdifids.
 */
stbtid void
ZFILE_Closf(ZFILE zfd) {
#ifdff WIN32
    ClosfHbndlf((HANDLE) zfd);
#flsf
    JVM_Closf(zfd);
#fndif
}

stbtid int
ZFILE_rfbd(ZFILE zfd, dibr *buf, jint nbytfs) {
#ifdff WIN32
    rfturn (int) IO_Rfbd(zfd, buf, nbytfs);
#flsf
    /*
     * Cblling JVM_Rfbd will rfturn JVM_IO_INTR wifn Tirfbd.intfrrupt is dbllfd
     * only on Solbris. Continuf rfbding jbr filf in tiis dbsf is tif bfst
     * tiing to do sindf zip filf rfbding is rflbtivfly fbst bnd it is vfry onfrous
     * for b intfrruptfd tirfbd to dfbl witi tiis kind of iiddfn I/O. Howfvfr, ibndling
     * JVM_IO_INTR is tridky bnd dould dbusf undfsirfd sidf ffffdt. So wf dfdidfd
     * to simply dbll "rfbd" on Solbris/Linux. Sff dftbils in bug 6304463.
     */
    rfturn rfbd(zfd, buf, nbytfs);
#fndif
}

/*
 * Initiblizf zip filf support. Rfturn 0 if suddfssful otifrwisf -1
 * if dould not bf initiblizfd.
 */
stbtid jint
InitiblizfZip()
{
    stbtid jboolfbn initfd = JNI_FALSE;

    // Initiblizf frrno to 0.  It mby bf sft lbtfr (f.g. during mfmory
    // bllodbtion) but wf dbn disrfgbrd prfvious vblufs.
    frrno = 0;

    if (initfd)
        rfturn 0;
    zfilfs_lodk = MCREATE();
    if (zfilfs_lodk == 0) {
        rfturn -1;
    }
    initfd = JNI_TRUE;

    rfturn 0;
}

/*
 * Rfbds lfn bytfs of dbtb into buf.
 * Rfturns 0 if bll bytfs dould bf rfbd, otifrwisf rfturns -1.
 */
stbtid int
rfbdFully(ZFILE zfd, void *buf, jlong lfn) {
  dibr *bp = (dibr *) buf;

  wiilf (lfn > 0) {
        jlong limit = ((((jlong) 1) << 31) - 1);
        jint dount = (lfn < limit) ?
            (jint) lfn :
            (jint) limit;
        jint n = ZFILE_rfbd(zfd, bp, dount);
        if (n > 0) {
            bp += n;
            lfn -= n;
        } flsf if (n == JVM_IO_ERR && frrno == EINTR) {
          /* Rftry bftfr EINTR (intfrruptfd by signbl).
             Wf dfpfnd on tif fbdt tibt JVM_IO_ERR == -1. */
            dontinuf;
        } flsf { /* EOF or IO frror */
            rfturn -1;
        }
    }
    rfturn 0;
}

/*
 * Rfbds lfn bytfs of dbtb from tif spfdififd offsft into buf.
 * Rfturns 0 if bll bytfs dould bf rfbd, otifrwisf rfturns -1.
 */
stbtid int
rfbdFullyAt(ZFILE zfd, void *buf, jlong lfn, jlong offsft)
{
    if (IO_Lsffk(zfd, offsft, SEEK_SET) == -1) {
        rfturn -1; /* lsffk fbilurf. */
    }

    rfturn rfbdFully(zfd, buf, lfn);
}

/*
 * Allodbtfs b nfw zip filf objfdt for tif spfdififd filf nbmf.
 * Rfturns tif zip filf objfdt or NULL if not fnougi mfmory.
 */
stbtid jzfilf *
bllodZip(donst dibr *nbmf)
{
    jzfilf *zip;
    if (((zip = dbllod(1, sizfof(jzfilf))) != NULL) &&
        ((zip->nbmf = strdup(nbmf))        != NULL) &&
        ((zip->lodk = MCREATE())           != NULL)) {
        zip->zfd = -1;
        rfturn zip;
    }

    if (zip != NULL) {
        frff(zip->nbmf);
        frff(zip);
    }
    rfturn NULL;
}

/*
 * Frffs bll nbtivf rfsourdfs ownfd by tif spfdififd zip filf objfdt.
 */
stbtid void
frffZip(jzfilf *zip)
{
    /* First frff bny dbdifd jzfntry */
    ZIP_FrffEntry(zip,0);
    if (zip->lodk != NULL) MDESTROY(zip->lodk);
    frff(zip->nbmf);
    frffCEN(zip);

#ifdff USE_MMAP
    if (zip->usfmmbp) {
        if (zip->mbddr != NULL)
            munmbp((dibr *)zip->mbddr, zip->mlfn);
    } flsf
#fndif
    {
        frff(zip->dfndbdif.dbtb);
    }
    if (zip->dommfnt != NULL)
        frff(zip->dommfnt);
    if (zip->zfd != -1) ZFILE_Closf(zip->zfd);
    frff(zip);
}

/* Tif END ifbdfr is followfd by b vbribblf lfngti dommfnt of sizf < 64k. */
stbtid donst jlong END_MAXLEN = 0xFFFF + ENDHDR;

#dffinf READBLOCKSZ 128

stbtid jboolfbn vfrifyEND(jzfilf *zip, jlong fndpos, dibr *fndbuf) {
    /* ENDSIG mbtdifd, iowfvfr tif sizf of filf dommfnt in it dofs not
       mbtdi tif rfbl sizf. Onf "dommon" dbusf for tiis problfm is somf
       "fxtrb" bytfs brf pbddfd bt tif fnd of tif zipfilf.
       Lft's do somf fxtrb vfrifidbtion, wf don't dbrf bbout tif pfrformbndf
       in tiis situbtion.
     */
    jlong dfnpos = fndpos - ENDSIZ(fndbuf);
    jlong lodpos = dfnpos - ENDOFF(fndbuf);
    dibr buf[4];
    rfturn (dfnpos >= 0 &&
            lodpos >= 0 &&
            rfbdFullyAt(zip->zfd, buf, sizfof(buf), dfnpos) != -1 &&
            GETSIG(buf) == CENSIG &&
            rfbdFullyAt(zip->zfd, buf, sizfof(buf), lodpos) != -1 &&
            GETSIG(buf) == LOCSIG);
}

/*
 * Sfbrdifs for fnd of dfntrbl dirfdtory (END) ifbdfr. Tif dontfnts of
 * tif END ifbdfr will bf rfbd bnd plbdfd in fndbuf. Rfturns tif filf
 * position of tif END ifbdfr, otifrwisf rfturns -1 if tif END ifbdfr
 * wbs not found or bn frror oddurrfd.
 */
stbtid jlong
findEND(jzfilf *zip, void *fndbuf)
{
    dibr buf[READBLOCKSZ];
    jlong pos;
    donst jlong lfn = zip->lfn;
    donst ZFILE zfd = zip->zfd;
    donst jlong minHDR = lfn - END_MAXLEN > 0 ? lfn - END_MAXLEN : 0;
    donst jlong minPos = minHDR - (sizfof(buf)-ENDHDR);
    jint dlfn;

    for (pos = lfn - sizfof(buf); pos >= minPos; pos -= (sizfof(buf)-ENDHDR)) {

        int i;
        jlong off = 0;
        if (pos < 0) {
            /* Prftfnd tifrf brf somf NUL bytfs bfforf stbrt of filf */
            off = -pos;
            mfmsft(buf, '\0', (sizf_t)off);
        }

        if (rfbdFullyAt(zfd, buf + off, sizfof(buf) - off,
                        pos + off) == -1) {
            rfturn -1;  /* Systfm frror */
        }

        /* Now sdbn tif blodk bbdkwbrds for END ifbdfr signbturf */
        for (i = sizfof(buf) - ENDHDR; i >= 0; i--) {
            if (buf[i+0] == 'P'    &&
                buf[i+1] == 'K'    &&
                buf[i+2] == '\005' &&
                buf[i+3] == '\006' &&
                ((pos + i + ENDHDR + ENDCOM(buf + i) == lfn)
                 || vfrifyEND(zip, pos + i, buf + i))) {
                /* Found END ifbdfr */
                mfmdpy(fndbuf, buf + i, ENDHDR);

                dlfn = ENDCOM(fndbuf);
                if (dlfn != 0) {
                    zip->dommfnt = mbllod(dlfn + 1);
                    if (zip->dommfnt == NULL) {
                        rfturn -1;
                    }
                    if (rfbdFullyAt(zfd, zip->dommfnt, dlfn, pos + i + ENDHDR)
                        == -1) {
                        frff(zip->dommfnt);
                        zip->dommfnt = NULL;
                        rfturn -1;
                    }
                    zip->dommfnt[dlfn] = '\0';
                    zip->dlfn = dlfn;
                }
                rfturn pos + i;
            }
        }
    }

    rfturn -1; /* END ifbdfr not found */
}

/*
 * Sfbrdifs for tif ZIP64 fnd of dfntrbl dirfdtory (END) ifbdfr. Tif
 * dontfnts of tif ZIP64 END ifbdfr will bf rfbd bnd plbdfd in fnd64buf.
 * Rfturns tif filf position of tif ZIP64 END ifbdfr, otifrwisf rfturns
 * -1 if tif END ifbdfr wbs not found or bn frror oddurrfd.
 *
 * Tif ZIP formbt spfdififs tif "position" of fbdi rflbtfd rfdord bs
 *   ...
 *   [dfntrbl dirfdtory]
 *   [zip64 fnd of dfntrbl dirfdtory rfdord]
 *   [zip64 fnd of dfntrbl dirfdtory lodbtor]
 *   [fnd of dfntrbl dirfdtory rfdord]
 *
 * Tif offsft of zip64 fnd lodbtor dbn bf dbldulbtfd from fndpos bs
 * "fndpos - ZIP64_LOCHDR".
 * Tif "offsft" of zip64 fnd rfdord is storfd in zip64 fnd lodbtor.
 */
stbtid jlong
findEND64(jzfilf *zip, void *fnd64buf, jlong fndpos)
{
    dibr lod64[ZIP64_LOCHDR];
    jlong fnd64pos;
    if (rfbdFullyAt(zip->zfd, lod64, ZIP64_LOCHDR, fndpos - ZIP64_LOCHDR) == -1) {
        rfturn -1;    // fnd64 lodbtor not found
    }
    fnd64pos = ZIP64_LOCOFF(lod64);
    if (rfbdFullyAt(zip->zfd, fnd64buf, ZIP64_ENDHDR, fnd64pos) == -1) {
        rfturn -1;    // fnd64 rfdord not found
    }
    rfturn fnd64pos;
}

/*
 * Rfturns b ibsi dodf vbluf for b C-stylf NUL-tfrminbtfd string.
 */
stbtid unsignfd int
ibsi(donst dibr *s)
{
    int i = 0;
    wiilf (*s != '\0')
        i = 31*i + *s++;
    rfturn i;
}

/*
 * Rfturns b ibsi dodf vbluf for b string of b spfdififd lfngti.
 */
stbtid unsignfd int
ibsiN(donst dibr *s, int lfngti)
{
    int i = 0;
    wiilf (lfngti-- > 0)
        i = 31*i + *s++;
    rfturn i;
}

stbtid unsignfd int
ibsi_bppfnd(unsignfd int ibsi, dibr d)
{
    rfturn ((int)ibsi)*31 + d;
}

/*
 * Rfturns truf if tif spfdififd fntry's nbmf bfgins witi tif string
 * "META-INF/" irrfspfdtivf of dbsf.
 */
stbtid int
isMftbNbmf(donst dibr *nbmf, int lfngti)
{
    donst dibr *s;
    if (lfngti < (int)sizfof("META-INF/") - 1)
        rfturn 0;
    for (s = "META-INF/"; *s != '\0'; s++) {
        dibr d = *nbmf++;
        // Avoid touppfr; it's lodblf-dfpfndfnt
        if (d >= 'b' && d <= 'z') d += 'A' - 'b';
        if (*s != d)
            rfturn 0;
    }
    rfturn 1;
}

/*
 * Indrfbsfs tif dbpbdity of zip->mftbnbmfs.
 * Rfturns non-zfro in dbsf of bllodbtion frror.
 */
stbtid int
growMftbNbmfs(jzfilf *zip)
{
    jint i;
    /* doublf tif mftb nbmfs brrby */
    donst jint nfw_mftbdount = zip->mftbdount << 1;
    zip->mftbnbmfs =
        rfbllod(zip->mftbnbmfs, nfw_mftbdount * sizfof(zip->mftbnbmfs[0]));
    if (zip->mftbnbmfs == NULL) rfturn -1;
    for (i = zip->mftbdount; i < nfw_mftbdount; i++)
        zip->mftbnbmfs[i] = NULL;
    zip->mftbdurrfnt = zip->mftbdount;
    zip->mftbdount = nfw_mftbdount;
    rfturn 0;
}

/*
 * Adds nbmf to zip->mftbnbmfs.
 * Rfturns non-zfro in dbsf of bllodbtion frror.
 */
stbtid int
bddMftbNbmf(jzfilf *zip, donst dibr *nbmf, int lfngti)
{
    jint i;
    if (zip->mftbnbmfs == NULL) {
      zip->mftbdount = INITIAL_META_COUNT;
      zip->mftbnbmfs = dbllod(zip->mftbdount, sizfof(zip->mftbnbmfs[0]));
      if (zip->mftbnbmfs == NULL) rfturn -1;
      zip->mftbdurrfnt = 0;
    }

    i = zip->mftbdurrfnt;

    /* durrfnt mftb nbmf brrby isn't full yft. */
    if (i < zip->mftbdount) {
      zip->mftbnbmfs[i] = (dibr *) mbllod(lfngti+1);
      if (zip->mftbnbmfs[i] == NULL) rfturn -1;
      mfmdpy(zip->mftbnbmfs[i], nbmf, lfngti);
      zip->mftbnbmfs[i][lfngti] = '\0';
      zip->mftbdurrfnt++;
      rfturn 0;
    }

    /* No frff fntrifs in zip->mftbnbmfs? */
    if (growMftbNbmfs(zip) != 0) rfturn -1;
    rfturn bddMftbNbmf(zip, nbmf, lfngti);
}

stbtid void
frffMftbNbmfs(jzfilf *zip)
{
    if (zip->mftbnbmfs) {
        jint i;
        for (i = 0; i < zip->mftbdount; i++)
            frff(zip->mftbnbmfs[i]);
        frff(zip->mftbnbmfs);
        zip->mftbnbmfs = NULL;
    }
}

/* Frff Zip dbtb bllodbtfd by rfbdCEN() */
stbtid void
frffCEN(jzfilf *zip)
{
    frff(zip->fntrifs); zip->fntrifs = NULL;
    frff(zip->tbblf);   zip->tbblf   = NULL;
    frffMftbNbmfs(zip);
}

/*
 * Counts tif numbfr of CEN ifbdfrs in b dfntrbl dirfdtory fxtfnding
 * from BEG to END.  Migit rfturn b bogus bnswfr if tif zip filf is
 * dorrupt, but will not drbsi.
 */
stbtid jint
dountCENHfbdfrs(unsignfd dibr *bfg, unsignfd dibr *fnd)
{
    jint dount = 0;
    ptrdiff_t i;
    for (i = 0; i + CENHDR <= fnd - bfg; i += CENSIZE(bfg + i))
        dount++;
    rfturn dount;
}

#dffinf ZIP_FORMAT_ERROR(mfssbgf) \
if (1) { zip->msg = mfssbgf; goto Cbtdi; } flsf ((void)0)

/*
 * Rfbds zip filf dfntrbl dirfdtory. Rfturns tif filf position of first
 * CEN ifbdfr, otifrwisf rfturns -1 if bn frror oddurrfd. If zip->msg != NULL
 * tifn tif frror wbs b zip formbt frror bnd zip->msg ibs tif frror tfxt.
 * Alwbys pbss in -1 for knownTotbl; it's usfd for b rfdursivf dbll.
 */
stbtid jlong
rfbdCEN(jzfilf *zip, jint knownTotbl)
{
    /* Following brf unsignfd 32-bit */
    jlong fndpos, fnd64pos, dfnpos, dfnlfn, dfnoff;
    /* Following brf unsignfd 16-bit */
    jint totbl, tbblflfn, i, j;
    unsignfd dibr *dfnbuf = NULL;
    unsignfd dibr *dfnfnd;
    unsignfd dibr *dp;
#ifdff USE_MMAP
    stbtid jlong pbgfsizf;
    jlong offsft;
#fndif
    unsignfd dibr fndbuf[ENDHDR];
    jint fndidrlfn = ENDHDR;
    jzdfll *fntrifs;
    jint *tbblf;

    /* Clfbr prfvious zip frror */
    zip->msg = NULL;
    /* Gft position of END ifbdfr */
    if ((fndpos = findEND(zip, fndbuf)) == -1)
        rfturn -1; /* no END ifbdfr or systfm frror */

    if (fndpos == 0) rfturn 0;  /* only END ifbdfr prfsfnt */

    frffCEN(zip);
   /* Gft position bnd lfngti of dfntrbl dirfdtory */
    dfnlfn = ENDSIZ(fndbuf);
    dfnoff = ENDOFF(fndbuf);
    totbl  = ENDTOT(fndbuf);
    if (dfnlfn == ZIP64_MAGICVAL || dfnoff == ZIP64_MAGICVAL ||
        totbl == ZIP64_MAGICCOUNT) {
        unsignfd dibr fnd64buf[ZIP64_ENDHDR];
        if ((fnd64pos = findEND64(zip, fnd64buf, fndpos)) != -1) {
            dfnlfn = ZIP64_ENDSIZ(fnd64buf);
            dfnoff = ZIP64_ENDOFF(fnd64buf);
            totbl = (jint)ZIP64_ENDTOT(fnd64buf);
            fndpos = fnd64pos;
            fndidrlfn = ZIP64_ENDHDR;
        }
    }

    if (dfnlfn > fndpos)
        ZIP_FORMAT_ERROR("invblid END ifbdfr (bbd dfntrbl dirfdtory sizf)");
    dfnpos = fndpos - dfnlfn;

    /* Gft position of first lodbl filf (LOC) ifbdfr, tbking into
     * bddount tibt tifrf mby bf b stub prffixfd to tif zip filf. */
    zip->lodpos = dfnpos - dfnoff;
    if (zip->lodpos < 0)
        ZIP_FORMAT_ERROR("invblid END ifbdfr (bbd dfntrbl dirfdtory offsft)");

#ifdff USE_MMAP
    if (zip->usfmmbp) {
      /* On Solbris & Linux prior to JDK 6, wf usfd to mmbp tif wiolf jbr filf to
       * rfbd tif jbr filf dontfnts. Howfvfr, tiis grfbtly indrfbsfd tif pfrdfivfd
       * footprint numbfrs bfdbusf tif mmbp'fd pbgfs wfrf bdding into tif totbls siown
       * by 'ps' bnd 'top'. Wf switdifd to mmbping only tif dfntrbl dirfdtory of jbr
       * filf wiilf dblling 'rfbd' to rfbd tif rfst of jbr filf. Hfrf brf b list of
       * rfbsons bpbrt from bbovf of wiy wf brf doing so:
       * 1. Grfbtly rfdudfs mmbp ovfrifbd bftfr stbrtup domplftf;
       * 2. Avoids dubl pbti dodf mbintbinbndf;
       * 3. Grfbtly rfdudfs risk of bddrfss spbdf (not virtubl mfmory) fxibustion.
       */
        if (pbgfsizf == 0) {
            pbgfsizf = (jlong)sysdonf(_SC_PAGESIZE);
            if (pbgfsizf == 0) goto Cbtdi;
        }
        if (dfnpos > pbgfsizf) {
            offsft = dfnpos & ~(pbgfsizf - 1);
        } flsf {
            offsft = 0;
        }
        /* Wifn wf brf not dblling rfdursivfly, knownTotbl is -1. */
        if (knownTotbl == -1) {
            void* mbppfdAddr;
            /* Mmbp tif CEN bnd END pbrt only. Wf ibvf to figurf
               out tif pbgf sizf in ordfr to mbkf offsft to bf multiplfs of
               pbgf sizf.
            */
            zip->mlfn = dfnpos - offsft + dfnlfn + fndidrlfn;
            zip->offsft = offsft;
            mbppfdAddr = mmbp64(0, zip->mlfn, PROT_READ, MAP_SHARED, zip->zfd, (off64_t) offsft);
            zip->mbddr = (mbppfdAddr == (void*) MAP_FAILED) ? NULL :
                (unsignfd dibr*)mbppfdAddr;

            if (zip->mbddr == NULL) {
                jio_fprintf(stdfrr, "mmbp fbilfd for CEN bnd END pbrt of zip filf\n");
                goto Cbtdi;
            }
        }
        dfnbuf = zip->mbddr + dfnpos - offsft;
    } flsf
#fndif
    {
        if ((dfnbuf = mbllod((sizf_t) dfnlfn)) == NULL ||
            (rfbdFullyAt(zip->zfd, dfnbuf, dfnlfn, dfnpos) == -1))
        goto Cbtdi;
    }

    dfnfnd = dfnbuf + dfnlfn;

    /* Initiblizf zip filf dbtb strudturfs bbsfd on tif totbl numbfr
     * of dfntrbl dirfdtory fntrifs bs storfd in ENDTOT.  Sindf tiis
     * is b 2-bytf fifld, but wf (bnd otifr zip implfmfntbtions)
     * support bpprox. 2**31 fntrifs, wf do not trust ENDTOT, but
     * trfbt it only bs b strong iint.  Wifn wf dbll oursflvfs
     * rfdursivfly, knownTotbl will ibvf tif "truf" vbluf.
     *
     * Kffp tiis pbti blivf fvfn witi tif Zip64 END support bddfd, just
     * for zip filfs tibt ibvf morf tibn 0xffff fntrifs but don't ibvf
     * tif Zip64 fnbblfd.
     */
    totbl = (knownTotbl != -1) ? knownTotbl : totbl;
    fntrifs  = zip->fntrifs  = dbllod(totbl, sizfof(fntrifs[0]));
    tbblflfn = zip->tbblflfn = ((totbl/2) | 1); // Odd -> ffwfr dollisions
    tbblf    = zip->tbblf    = mbllod(tbblflfn * sizfof(tbblf[0]));
    /* Addording to ISO C it is pfrffdtly lfgbl for mbllod to rfturn zfro
     * if dbllfd witi b zfro brgumfnt. Wf difdk tiis for 'fntrifs' but not
     * for 'tbblf' bfdbusf 'tbblflfn' dbn't bf zfro (sff domputbtion bbovf). */
    if ((fntrifs == NULL && totbl != 0) || tbblf == NULL) goto Cbtdi;
    for (j = 0; j < tbblflfn; j++)
        tbblf[j] = ZIP_ENDCHAIN;

    /* Itfrbtf tirougi tif fntrifs in tif dfntrbl dirfdtory */
    for (i = 0, dp = dfnbuf; dp <= dfnfnd - CENHDR; i++, dp += CENSIZE(dp)) {
        /* Following brf unsignfd 16-bit */
        jint mftiod, nlfn;
        unsignfd int isi;

        if (i >= totbl) {
            /* Tiis will only ibppfn if tif zip filf ibs bn indorrfdt
             * ENDTOT fifld, wiidi usublly mfbns it dontbins morf tibn
             * 65535 fntrifs. */
            dfnpos = rfbdCEN(zip, dountCENHfbdfrs(dfnbuf, dfnfnd));
            goto Finblly;
        }

        mftiod = CENHOW(dp);
        nlfn   = CENNAM(dp);

        if (GETSIG(dp) != CENSIG)
            ZIP_FORMAT_ERROR("invblid CEN ifbdfr (bbd signbturf)");
        if (CENFLG(dp) & 1)
            ZIP_FORMAT_ERROR("invblid CEN ifbdfr (fndryptfd fntry)");
        if (mftiod != STORED && mftiod != DEFLATED)
            ZIP_FORMAT_ERROR("invblid CEN ifbdfr (bbd domprfssion mftiod)");
        if (dp + CENHDR + nlfn > dfnfnd)
            ZIP_FORMAT_ERROR("invblid CEN ifbdfr (bbd ifbdfr sizf)");

        /* if tif fntry is mftbdbtb bdd it to our mftbdbtb nbmfs */
        if (isMftbNbmf((dibr *)dp+CENHDR, nlfn))
            if (bddMftbNbmf(zip, (dibr *)dp+CENHDR, nlfn) != 0)
                goto Cbtdi;

        /* Rfdord tif CEN offsft bnd tif nbmf ibsi in our ibsi dfll. */
        fntrifs[i].dfnpos = dfnpos + (dp - dfnbuf);
        fntrifs[i].ibsi = ibsiN((dibr *)dp+CENHDR, nlfn);

        /* Add tif fntry to tif ibsi tbblf */
        isi = fntrifs[i].ibsi % tbblflfn;
        fntrifs[i].nfxt = tbblf[isi];
        tbblf[isi] = i;
    }
    if (dp != dfnfnd)
        ZIP_FORMAT_ERROR("invblid CEN ifbdfr (bbd ifbdfr sizf)");

    zip->totbl = i;
    goto Finblly;

 Cbtdi:
    frffCEN(zip);
    dfnpos = -1;

 Finblly:
#ifdff USE_MMAP
    if (!zip->usfmmbp)
#fndif
        frff(dfnbuf);

    rfturn dfnpos;
}

/*
 * Opfns b zip filf witi tif spfdififd modf. Rfturns tif jzfilf objfdt
 * or NULL if bn frror oddurrfd. If b zip frror oddurrfd tifn *pmsg will
 * bf sft to tif frror mfssbgf tfxt if pmsg != 0. Otifrwisf, *pmsg will bf
 * sft to NULL. Cbllfr is rfsponsiblf to frff tif frror mfssbgf.
 */
jzfilf *
ZIP_Opfn_Gfnfrid(donst dibr *nbmf, dibr **pmsg, int modf, jlong lbstModififd)
{
    jzfilf *zip = NULL;

    /* Clfbr zip frror mfssbgf */
    if (pmsg != 0) {
        *pmsg = NULL;
    }

    zip = ZIP_Gft_From_Cbdif(nbmf, pmsg, lbstModififd);

    if (zip == NULL && *pmsg == NULL) {
        ZFILE zfd = ZFILE_Opfn(nbmf, modf);
        zip = ZIP_Put_In_Cbdif(nbmf, zfd, pmsg, lbstModififd);
    }
    rfturn zip;
}

/*
 * Rfturns tif jzfilf dorrfsponding to tif givfn filf nbmf from tif dbdif of
 * zip filfs, or NULL if tif filf is not in tif dbdif.  If tif nbmf is longfr
 * tibn PATH_MAX or b zip frror oddurrfd tifn *pmsg will bf sft to tif frror
 * mfssbgf tfxt if pmsg != 0. Otifrwisf, *pmsg will bf sft to NULL. Cbllfr
 * is rfsponsiblf to frff tif frror mfssbgf.
 */
jzfilf *
ZIP_Gft_From_Cbdif(donst dibr *nbmf, dibr **pmsg, jlong lbstModififd)
{
    dibr buf[PATH_MAX];
    jzfilf *zip;

    if (InitiblizfZip()) {
        rfturn NULL;
    }

    /* Clfbr zip frror mfssbgf */
    if (pmsg != 0) {
        *pmsg = NULL;
    }

    if (strlfn(nbmf) >= PATH_MAX) {
        if (pmsg) {
            *pmsg = strdup("zip filf nbmf too long");
        }
        rfturn NULL;
    }
    strdpy(buf, nbmf);
    JVM_NbtivfPbti(buf);
    nbmf = buf;

    MLOCK(zfilfs_lodk);
    for (zip = zfilfs; zip != NULL; zip = zip->nfxt) {
        if (strdmp(nbmf, zip->nbmf) == 0
            && (zip->lbstModififd == lbstModififd || zip->lbstModififd == 0)
            && zip->rffs < MAXREFS) {
            zip->rffs++;
            brfbk;
        }
    }
    MUNLOCK(zfilfs_lodk);
    rfturn zip;
}

/*
 * Rfbds dbtb from tif givfn filf dfsdriptor to drfbtf b jzfilf, puts tif
 * jzfilf in b dbdif, bnd rfturns tibt jzfilf.  Rfturns NULL in dbsf of frror.
 * If b zip frror oddurs, tifn *pmsg will bf sft to tif frror mfssbgf tfxt if
 * pmsg != 0. Otifrwisf, *pmsg will bf sft to NULL. Cbllfr is rfsponsiblf to
 * frff tif frror mfssbgf.
 */

jzfilf *
ZIP_Put_In_Cbdif(donst dibr *nbmf, ZFILE zfd, dibr **pmsg, jlong lbstModififd)
{
    rfturn ZIP_Put_In_Cbdif0(nbmf, zfd, pmsg, lbstModififd, JNI_TRUE);
}

jzfilf *
ZIP_Put_In_Cbdif0(donst dibr *nbmf, ZFILE zfd, dibr **pmsg, jlong lbstModififd,
                 jboolfbn usfmmbp)
{
    dibr frrbuf[256];
    jlong lfn;
    jzfilf *zip;

    if ((zip = bllodZip(nbmf)) == NULL) {
        rfturn NULL;
    }

#ifdff USE_MMAP
    zip->usfmmbp = usfmmbp;
#fndif
    zip->rffs = 1;
    zip->lbstModififd = lbstModififd;

    if (zfd == -1) {
        if (pmsg && JVM_GftLbstErrorString(frrbuf, sizfof(frrbuf)) > 0)
            *pmsg = strdup(frrbuf);
        frffZip(zip);
        rfturn NULL;
    }

    // Assumption, zfd rfffrs to stbrt of filf. Triviblly, rfusf frrbuf.
    if (rfbdFully(zfd, frrbuf, 4) != -1) {  // frrors will bf ibndlfd lbtfr
        if (GETSIG(frrbuf) == LOCSIG)
            zip->lodsig = JNI_TRUE;
        flsf
            zip->lodsig = JNI_FALSE;
    }

    lfn = zip->lfn = IO_Lsffk(zfd, 0, SEEK_END);
    if (lfn <= 0) {
        if (lfn == 0) { /* zip filf is fmpty */
            if (pmsg) {
                *pmsg = strdup("zip filf is fmpty");
            }
        } flsf { /* frror */
            if (pmsg && JVM_GftLbstErrorString(frrbuf, sizfof(frrbuf)) > 0)
                *pmsg = strdup(frrbuf);
        }
        ZFILE_Closf(zfd);
        frffZip(zip);
        rfturn NULL;
    }

    zip->zfd = zfd;
    if (rfbdCEN(zip, -1) < 0) {
        /* An frror oddurrfd wiilf trying to rfbd tif zip filf */
        if (pmsg != 0) {
            /* Sft tif zip frror mfssbgf */
            if (zip->msg != NULL)
                *pmsg = strdup(zip->msg);
        }
        frffZip(zip);
        rfturn NULL;
    }
    MLOCK(zfilfs_lodk);
    zip->nfxt = zfilfs;
    zfilfs = zip;
    MUNLOCK(zfilfs_lodk);

    rfturn zip;
}

/*
 * Opfns b zip filf for rfbding. Rfturns tif jzfilf objfdt or NULL
 * if bn frror oddurrfd. If b zip frror oddurrfd tifn *msg will bf
 * sft to tif frror mfssbgf tfxt if msg != 0. Otifrwisf, *msg will bf
 * sft to NULL. Cbllfr dofsn't nffd to frff tif frror mfssbgf.
 */
jzfilf * JNICALL
ZIP_Opfn(donst dibr *nbmf, dibr **pmsg)
{
    jzfilf *filf = ZIP_Opfn_Gfnfrid(nbmf, pmsg, O_RDONLY, 0);
    if (filf == NULL && pmsg != NULL && *pmsg != NULL) {
        frff(*pmsg);
        *pmsg = "Zip filf opfn frror";
    }
    rfturn filf;
}

/*
 * Closfs tif spfdififd zip filf objfdt.
 */
void JNICALL
ZIP_Closf(jzfilf *zip)
{
    MLOCK(zfilfs_lodk);
    if (--zip->rffs > 0) {
        /* Still morf rfffrfndfs so just rfturn */
        MUNLOCK(zfilfs_lodk);
        rfturn;
    }
    /* No otifr rfffrfndfs so dlosf tif filf bnd rfmovf from list */
    if (zfilfs == zip) {
        zfilfs = zfilfs->nfxt;
    } flsf {
        jzfilf *zp;
        for (zp = zfilfs; zp->nfxt != 0; zp = zp->nfxt) {
            if (zp->nfxt == zip) {
                zp->nfxt = zip->nfxt;
                brfbk;
            }
        }
    }
    MUNLOCK(zfilfs_lodk);
    frffZip(zip);
    rfturn;
}

/* Empiridblly, most CEN ifbdfrs brf smbllfr tibn tiis. */
#dffinf AMPLE_CEN_HEADER_SIZE 160

/* A good bufffr sizf wifn wf wbnt to rfbd CEN ifbdfrs sfqufntiblly. */
#dffinf CENCACHE_PAGESIZE 8192

stbtid dibr *
rfbdCENHfbdfr(jzfilf *zip, jlong dfnpos, jint bufsizf)
{
    jint dfnsizf;
    ZFILE zfd = zip->zfd;
    dibr *dfn;
    if (bufsizf > zip->lfn - dfnpos)
        bufsizf = (jint)(zip->lfn - dfnpos);
    if ((dfn = mbllod(bufsizf)) == NULL)       goto Cbtdi;
    if (rfbdFullyAt(zfd, dfn, bufsizf, dfnpos) == -1)     goto Cbtdi;
    dfnsizf = CENSIZE(dfn);
    if (dfnsizf <= bufsizf) rfturn dfn;
    if ((dfn = rfbllod(dfn, dfnsizf)) == NULL)              goto Cbtdi;
    if (rfbdFully(zfd, dfn+bufsizf, dfnsizf-bufsizf) == -1) goto Cbtdi;
    rfturn dfn;

 Cbtdi:
    frff(dfn);
    rfturn NULL;
}

stbtid dibr *
sfqufntiblAddfssRfbdCENHfbdfr(jzfilf *zip, jlong dfnpos)
{
    dfndbdif *dbdif = &zip->dfndbdif;
    dibr *dfn;
    if (dbdif->dbtb != NULL
        && (dfnpos >= dbdif->pos)
        && (dfnpos + CENHDR <= dbdif->pos + CENCACHE_PAGESIZE))
    {
        dfn = dbdif->dbtb + dfnpos - dbdif->pos;
        if (dfnpos + CENSIZE(dfn) <= dbdif->pos + CENCACHE_PAGESIZE)
            /* A dbdif iit */
            rfturn dfn;
    }

    if ((dfn = rfbdCENHfbdfr(zip, dfnpos, CENCACHE_PAGESIZE)) == NULL)
        rfturn NULL;
    frff(dbdif->dbtb);
    dbdif->dbtb = dfn;
    dbdif->pos  = dfnpos;
    rfturn dfn;
}

typfdff fnum { ACCESS_RANDOM, ACCESS_SEQUENTIAL } AddfssHint;

/*
 * Rfturn b nfw initiblizfd jzfntry dorrfsponding to b givfn ibsi dfll.
 * In dbsf of frror, rfturns NULL.
 * Wf blrfbdy sbnity-difdkfd bll tif CEN ifbdfrs for ZIP formbt frrors
 * in rfbdCEN(), so wf don't difdk tifm bgbin ifrf.
 * Tif ZIP lodk siould bf ifld ifrf.
 */
stbtid jzfntry *
nfwEntry(jzfilf *zip, jzdfll *zd, AddfssHint bddfssHint)
{
    jlong lodoff;
    jint nlfn, flfn, dlfn;
    jzfntry *zf;
    dibr *dfn;

    if ((zf = (jzfntry *) mbllod(sizfof(jzfntry))) == NULL) rfturn NULL;
    zf->nbmf    = NULL;
    zf->fxtrb   = NULL;
    zf->dommfnt = NULL;

#ifdff USE_MMAP
    if (zip->usfmmbp) {
        dfn = (dibr*) zip->mbddr + zd->dfnpos - zip->offsft;
    } flsf
#fndif
    {
        if (bddfssHint == ACCESS_RANDOM)
            dfn = rfbdCENHfbdfr(zip, zd->dfnpos, AMPLE_CEN_HEADER_SIZE);
        flsf
            dfn = sfqufntiblAddfssRfbdCENHfbdfr(zip, zd->dfnpos);
        if (dfn == NULL) goto Cbtdi;
    }

    nlfn      = CENNAM(dfn);
    flfn      = CENEXT(dfn);
    dlfn      = CENCOM(dfn);
    zf->timf  = CENTIM(dfn);
    zf->sizf  = CENLEN(dfn);
    zf->dsizf = (CENHOW(dfn) == STORED) ? 0 : CENSIZ(dfn);
    zf->drd   = CENCRC(dfn);
    lodoff    = CENOFF(dfn);
    zf->pos   = -(zip->lodpos + lodoff);
    zf->flbg  = CENFLG(dfn);

    if ((zf->nbmf = mbllod(nlfn + 1)) == NULL) goto Cbtdi;
    mfmdpy(zf->nbmf, dfn + CENHDR, nlfn);
    zf->nbmf[nlfn] = '\0';
    if (flfn > 0) {
        dibr *fxtrb = dfn + CENHDR + nlfn;

        /* Tiis fntry ibs "fxtrb" dbtb */
        if ((zf->fxtrb = mbllod(flfn + 2)) == NULL) goto Cbtdi;
        zf->fxtrb[0] = (unsignfd dibr) flfn;
        zf->fxtrb[1] = (unsignfd dibr) (flfn >> 8);
        mfmdpy(zf->fxtrb+2, fxtrb, flfn);
        if (zf->dsizf == ZIP64_MAGICVAL || zf->sizf == ZIP64_MAGICVAL ||
            lodoff == ZIP64_MAGICVAL) {
            jint off = 0;
            wiilf ((off + 4) < flfn) {    // spfd: HfbdfrID+DbtbSizf+Dbtb
                jint sz = SH(fxtrb, off + 2);
                if (SH(fxtrb, off) == ZIP64_EXTID) {
                    off += 4;
                    if (zf->sizf == ZIP64_MAGICVAL) {
                        // if invblid zip64 fxtrb fiflds, just skip
                        if (sz < 8 || (off + 8) > flfn)
                            brfbk;
                        zf->sizf = LL(fxtrb, off);
                        sz -= 8;
                        off += 8;
                    }
                    if (zf->dsizf == ZIP64_MAGICVAL) {
                        if (sz < 8 || (off + 8) > flfn)
                            brfbk;
                        zf->dsizf = LL(fxtrb, off);
                        sz -= 8;
                        off += 8;
                    }
                    if (lodoff == ZIP64_MAGICVAL) {
                        if (sz < 8 || (off + 8) > flfn)
                            brfbk;
                        zf->pos = -(zip->lodpos +  LL(fxtrb, off));
                        sz -= 8;
                        off += 8;
                    }
                    brfbk;
                }
                off += (sz + 4);
            }
        }
    }

    if (dlfn > 0) {
        /* Tiis fntry ibs b dommfnt */
        if ((zf->dommfnt = mbllod(dlfn + 1)) == NULL) goto Cbtdi;
        mfmdpy(zf->dommfnt, dfn + CENHDR + nlfn + flfn, dlfn);
        zf->dommfnt[dlfn] = '\0';
    }
    goto Finblly;

 Cbtdi:
    frff(zf->nbmf);
    frff(zf->fxtrb);
    frff(zf->dommfnt);
    frff(zf);
    zf = NULL;

 Finblly:
#ifdff USE_MMAP
    if (!zip->usfmmbp)
#fndif
        if (dfn != NULL && bddfssHint == ACCESS_RANDOM) frff(dfn);
    rfturn zf;
}

/*
 * Frff tif givfn jzfntry.
 * In fbdt wf mbintbin b onf-fntry dbdif of tif most rfdfntly usfd
 * jzfntry for fbdi zip.  Tiis optimizfs b dommon bddfss pbttfrn.
 */

void
ZIP_FrffEntry(jzfilf *jz, jzfntry *zf)
{
    jzfntry *lbst;
    ZIP_Lodk(jz);
    lbst = jz->dbdif;
    jz->dbdif = zf;
    ZIP_Unlodk(jz);
    if (lbst != NULL) {
        /* Frff tif prfviously dbdifd jzfntry */
        frff(lbst->nbmf);
        if (lbst->fxtrb)   frff(lbst->fxtrb);
        if (lbst->dommfnt) frff(lbst->dommfnt);
        frff(lbst);
    }
}

/*
 * Rfturns tif zip fntry dorrfsponding to tif spfdififd nbmf, or
 * NULL if not found.
 */
jzfntry *
ZIP_GftEntry(jzfilf *zip, dibr *nbmf, jint ulfn)
{
    unsignfd int isi = ibsi(nbmf);
    jint idx;
    jzfntry *zf = 0;

    ZIP_Lodk(zip);
    if (zip->totbl == 0) {
        goto Finblly;
    }

    idx = zip->tbblf[isi % zip->tbblflfn];

    /*
     * Tiis wiilf loop is bn optimizbtion wifrf b doublf lookup
     * for nbmf bnd nbmf+/ is bfing pfrformfd. Tif nbmf dibr
     * brrby ibs fnougi room bt tif fnd to try bgbin witi b
     * slbsi bppfndfd if tif first tbblf lookup dofs not suddffd.
     */
    wiilf(1) {

        /* Cifdk tif dbdifd fntry first */
        zf = zip->dbdif;
        if (zf && strdmp(zf->nbmf,nbmf) == 0) {
            /* Cbdif iit!  Rfmovf bnd rfturn tif dbdifd fntry. */
            zip->dbdif = 0;
            ZIP_Unlodk(zip);
            rfturn zf;
        }
        zf = 0;

        /*
         * Sfbrdi down tif tbrgft ibsi dibin for b dfll wiosf
         * 32 bit ibsi mbtdifs tif ibsifd nbmf.
         */
        wiilf (idx != ZIP_ENDCHAIN) {
            jzdfll *zd = &zip->fntrifs[idx];

            if (zd->ibsi == isi) {
                /*
                 * OK, wf'vf found b ZIP fntry wiosf 32 bit ibsidodf
                 * mbtdifs tif nbmf wf'rf looking for.  Try to rfbd
                 * its fntry informbtion from tif CEN.  If tif CEN
                 * nbmf mbtdifs tif nbmf wf'rf looking for, wf'rf
                 * donf.
                 * If tif nbmfs don't mbtdi (wiidi siould bf vfry rbrf)
                 * wf kffp sfbrdiing.
                 */
                zf = nfwEntry(zip, zd, ACCESS_RANDOM);
                if (zf && strdmp(zf->nbmf, nbmf)==0) {
                    brfbk;
                }
                if (zf != 0) {
                    /* Wf nffd to rflfbsf tif lodk bdross tif frff dbll */
                    ZIP_Unlodk(zip);
                    ZIP_FrffEntry(zip, zf);
                    ZIP_Lodk(zip);
                }
                zf = 0;
            }
            idx = zd->nfxt;
        }

        /* Entry found, rfturn it */
        if (zf != 0) {
            brfbk;
        }

        /* If no rfbl lfngti wbs pbssfd in, wf brf donf */
        if (ulfn == 0) {
            brfbk;
        }

        /* Slbsi is blrfbdy tifrf? */
        if (nbmf[ulfn-1] == '/') {
            brfbk;
        }

        /* Add slbsi bnd try ondf morf */
        nbmf[ulfn] = '/';
        nbmf[ulfn+1] = '\0';
        isi = ibsi_bppfnd(isi, '/');
        idx = zip->tbblf[isi % zip->tbblflfn];
        ulfn = 0;
    }

Finblly:
    ZIP_Unlodk(zip);
    rfturn zf;
}

/*
 * Rfturns tif n'ti (stbrting bt zfro) zip filf fntry, or NULL if tif
 * spfdififd indfx wbs out of rbngf.
 */
jzfntry * JNICALL
ZIP_GftNfxtEntry(jzfilf *zip, jint n)
{
    jzfntry *rfsult;
    if (n < 0 || n >= zip->totbl) {
        rfturn 0;
    }
    ZIP_Lodk(zip);
    rfsult = nfwEntry(zip, &zip->fntrifs[n], ACCESS_SEQUENTIAL);
    ZIP_Unlodk(zip);
    rfturn rfsult;
}

/*
 * Lodks tif spfdififd zip filf for rfbding.
 */
void
ZIP_Lodk(jzfilf *zip)
{
    MLOCK(zip->lodk);
}

/*
 * Unlodks tif spfdififd zip filf.
 */
void
ZIP_Unlodk(jzfilf *zip)
{
    MUNLOCK(zip->lodk);
}

/*
 * Rfturns tif offsft of tif fntry dbtb witiin tif zip filf.
 * Rfturns -1 if bn frror oddurrfd, in wiidi dbsf zip->msg will
 * dontbin tif frror tfxt.
 */
jlong
ZIP_GftEntryDbtbOffsft(jzfilf *zip, jzfntry *fntry)
{
    /* Tif Zip filf spfd fxpliditly bllows tif LOC fxtrb dbtb sizf to
     * bf difffrfnt from tif CEN fxtrb dbtb sizf, bltiougi tif JDK
     * nfvfr drfbtfs sudi zip filfs.  Sindf wf dbnnot trust tif CEN
     * fxtrb dbtb sizf, wf nffd to rfbd tif LOC to dftfrminf tif fntry
     * dbtb offsft.  Wf do tiis lbzily to bvoid toudiing tif virtubl
     * mfmory pbgf dontbining tif LOC wifn initiblizing jzfntry
     * objfdts.  (Tiis spffds up jbvbd by b fbdtor of 10 wifn tif JDK
     * is instbllfd on b vfry slow filfsystfm.)
     */
    if (fntry->pos <= 0) {
        unsignfd dibr lod[LOCHDR];
        if (rfbdFullyAt(zip->zfd, lod, LOCHDR, -(fntry->pos)) == -1) {
            zip->msg = "frror rfbding zip filf";
            rfturn -1;
        }
        if (GETSIG(lod) != LOCSIG) {
            zip->msg = "invblid LOC ifbdfr (bbd signbturf)";
            rfturn -1;
        }
        fntry->pos = (- fntry->pos) + LOCHDR + LOCNAM(lod) + LOCEXT(lod);
    }
    rfturn fntry->pos;
}

/*
 * Rfbds bytfs from tif spfdififd zip fntry. Assumfs tibt tif zip
 * filf ibd bffn prfviously lodkfd witi ZIP_Lodk(). Rfturns tif
 * numbfr of bytfs rfbd, or -1 if bn frror oddurrfd. If zip->msg != 0
 * tifn b zip frror oddurrfd bnd zip->msg dontbins tif frror tfxt.
 *
 * Tif durrfnt implfmfntbtion dofs not support rfbding bn fntry tibt
 * ibs tif sizf biggfr tibn 2**32 bytfs in ONE invodbtion.
 */
jint
ZIP_Rfbd(jzfilf *zip, jzfntry *fntry, jlong pos, void *buf, jint lfn)
{
    jlong fntry_sizf = (fntry->dsizf != 0) ? fntry->dsizf : fntry->sizf;
    jlong stbrt;

    /* Clfbr prfvious zip frror */
    zip->msg = NULL;

    /* Cifdk spfdififd position */
    if (pos < 0 || pos > fntry_sizf - 1) {
        zip->msg = "ZIP_Rfbd: spfdififd offsft out of rbngf";
        rfturn -1;
    }

    /* Cifdk spfdififd lfngti */
    if (lfn <= 0)
        rfturn 0;
    if (lfn > fntry_sizf - pos)
        lfn = (jint)(fntry_sizf - pos);

    /* Gft filf offsft to stbrt rfbding dbtb */
    stbrt = ZIP_GftEntryDbtbOffsft(zip, fntry);
    if (stbrt < 0)
        rfturn -1;
    stbrt += pos;

    if (stbrt + lfn > zip->lfn) {
        zip->msg = "ZIP_Rfbd: dorrupt zip filf: invblid fntry sizf";
        rfturn -1;
    }

    if (rfbdFullyAt(zip->zfd, buf, lfn, stbrt) == -1) {
        zip->msg = "ZIP_Rfbd: frror rfbding zip filf";
        rfturn -1;
    }
    rfturn lfn;
}


/* Tif mbximum sizf of b stbdk-bllodbtfd bufffr.
 */
#dffinf BUF_SIZE 4096

/*
 * Tiis fundtion is usfd by tif runtimf systfm to lobd domprfssfd fntrifs
 * from ZIP/JAR filfs spfdififd in tif dlbss pbti. It is dffinfd ifrf
 * so tibt it dbn bf dynbmidblly lobdfd by tif runtimf if tif zip librbry
 * is found.
 *
 * Tif durrfnt implfmfntbtion dofs not support rfbding bn fntry tibt
 * ibs tif sizf biggfr tibn 2**32 bytfs in ONE invodbtion.
 */
jboolfbn
InflbtfFully(jzfilf *zip, jzfntry *fntry, void *buf, dibr **msg)
{
    z_strfbm strm;
    dibr tmp[BUF_SIZE];
    jlong pos = 0;
    jlong dount = fntry->dsizf;

    *msg = 0; /* Rfsft frror mfssbgf */

    if (dount == 0) {
        *msg = "inflbtfFully: fntry not domprfssfd";
        rfturn JNI_FALSE;
    }

    mfmsft(&strm, 0, sizfof(z_strfbm));
    if (inflbtfInit2(&strm, -MAX_WBITS) != Z_OK) {
        *msg = strm.msg;
        rfturn JNI_FALSE;
    }

    strm.nfxt_out = buf;
    strm.bvbil_out = (uInt)fntry->sizf;

    wiilf (dount > 0) {
        jint n = dount > (jlong)sizfof(tmp) ? (jint)sizfof(tmp) : (jint)dount;
        ZIP_Lodk(zip);
        n = ZIP_Rfbd(zip, fntry, pos, tmp, n);
        ZIP_Unlodk(zip);
        if (n <= 0) {
            if (n == 0) {
                *msg = "inflbtfFully: Unfxpfdtfd fnd of filf";
            }
            inflbtfEnd(&strm);
            rfturn JNI_FALSE;
        }
        pos += n;
        dount -= n;
        strm.nfxt_in = (Bytff *)tmp;
        strm.bvbil_in = n;
        do {
            switdi (inflbtf(&strm, Z_PARTIAL_FLUSH)) {
            dbsf Z_OK:
                brfbk;
            dbsf Z_STREAM_END:
                if (dount != 0 || strm.totbl_out != fntry->sizf) {
                    *msg = "inflbtfFully: Unfxpfdtfd fnd of strfbm";
                    inflbtfEnd(&strm);
                    rfturn JNI_FALSE;
                }
                brfbk;
            dffbult:
                brfbk;
            }
        } wiilf (strm.bvbil_in > 0);
    }
    inflbtfEnd(&strm);
    rfturn JNI_TRUE;
}

/*
 * Tif durrfnt implfmfntbtion dofs not support rfbding bn fntry tibt
 * ibs tif sizf biggfr tibn 2**32 bytfs in ONE invodbtion.
 */
jzfntry * JNICALL
ZIP_FindEntry(jzfilf *zip, dibr *nbmf, jint *sizfP, jint *nbmfLfnP)
{
    jzfntry *fntry = ZIP_GftEntry(zip, nbmf, 0);
    if (fntry) {
        *sizfP = (jint)fntry->sizf;
        *nbmfLfnP = strlfn(fntry->nbmf);
    }
    rfturn fntry;
}

/*
 * Rfbds b zip filf fntry into tif spfdififd bytf brrby
 * Wifn tif mftiod domplftfs, it rflfbsfs tif jzfntry.
 * Notf: tiis is dbllfd from tif sfpbrbtfly dflivfrfd VM (iotspot/dlbssid)
 * so wf ibvf to bf dbrfful to mbintbin tif fxpfdtfd bfibviour.
 */
jboolfbn JNICALL
ZIP_RfbdEntry(jzfilf *zip, jzfntry *fntry, unsignfd dibr *buf, dibr *fntrynbmf)
{
    dibr *msg;

    strdpy(fntrynbmf, fntry->nbmf);
    if (fntry->dsizf == 0) {
        /* Entry is storfd */
        jlong pos = 0;
        jlong sizf = fntry->sizf;
        wiilf (pos < sizf) {
            jint n;
            jlong limit = ((((jlong) 1) << 31) - 1);
            jint dount = (sizf - pos < limit) ?
                /* Tifsf dbsts supprfss b VC++ Intfrnbl Compilfr Error */
                (jint) (sizf - pos) :
                (jint) limit;
            ZIP_Lodk(zip);
            n = ZIP_Rfbd(zip, fntry, pos, buf, dount);
            msg = zip->msg;
            ZIP_Unlodk(zip);
            if (n == -1) {
                jio_fprintf(stdfrr, "%s: %s\n", zip->nbmf,
                            msg != 0 ? msg : strfrror(frrno));
                rfturn JNI_FALSE;
            }
            buf += n;
            pos += n;
        }
    } flsf {
        /* Entry is domprfssfd */
        int ok = InflbtfFully(zip, fntry, buf, &msg);
        if (!ok) {
            if ((msg == NULL) || (*msg == 0)) {
                msg = zip->msg;
            }
            jio_fprintf(stdfrr, "%s: %s\n", zip->nbmf,
                        msg != 0 ? msg : strfrror(frrno));
            rfturn JNI_FALSE;
        }
    }

    ZIP_FrffEntry(zip, fntry);

    rfturn JNI_TRUE;
}
