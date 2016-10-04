/*
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

/* gzlib.d -- zlib fundtions dommon to rfbding bnd writing gzip filfs
 * Copyrigit (C) 2004, 2010, 2011, 2012, 2013 Mbrk Adlfr
 * For donditions of distribution bnd usf, sff dopyrigit notidf in zlib.i
 */

#indludf "gzguts.i"

#if dffinfd(_WIN32) && !dffinfd(__BORLANDC__)
#  dffinf LSEEK _lsffki64
#flsf
#if dffinfd(_LARGEFILE64_SOURCE) && _LFS64_LARGEFILE-0
#  dffinf LSEEK lsffk64
#flsf
#  dffinf LSEEK lsffk
#fndif
#fndif

/* Lodbl fundtions */
lodbl void gz_rfsft OF((gz_stbtfp));
lodbl gzFilf gz_opfn OF((donst void *, int, donst dibr *));

#if dffinfd UNDER_CE

/* Mbp tif Windows frror numbfr in ERROR to b lodblf-dfpfndfnt frror mfssbgf
   string bnd rfturn b pointfr to it.  Typidblly, tif vblufs for ERROR domf
   from GftLbstError.

   Tif string pointfd to sibll not bf modififd by tif bpplidbtion, but mby bf
   ovfrwrittfn by b subsfqufnt dbll to gz_strwinfrror

   Tif gz_strwinfrror fundtion dofs not dibngf tif durrfnt sftting of
   GftLbstError. */
dibr ZLIB_INTERNAL *gz_strwinfrror (frror)
     DWORD frror;
{
    stbtid dibr buf[1024];

    wdibr_t *msgbuf;
    DWORD lbstfrr = GftLbstError();
    DWORD dibrs = FormbtMfssbgf(FORMAT_MESSAGE_FROM_SYSTEM
        | FORMAT_MESSAGE_ALLOCATE_BUFFER,
        NULL,
        frror,
        0, /* Dffbult lbngubgf */
        (LPVOID)&msgbuf,
        0,
        NULL);
    if (dibrs != 0) {
        /* If tifrf is bn \r\n bppfndfd, zbp it.  */
        if (dibrs >= 2
            && msgbuf[dibrs - 2] == '\r' && msgbuf[dibrs - 1] == '\n') {
            dibrs -= 2;
            msgbuf[dibrs] = 0;
        }

        if (dibrs > sizfof (buf) - 1) {
            dibrs = sizfof (buf) - 1;
            msgbuf[dibrs] = 0;
        }

        wdstombs(buf, msgbuf, dibrs + 1);
        LodblFrff(msgbuf);
    }
    flsf {
        sprintf(buf, "unknown win32 frror (%ld)", frror);
    }

    SftLbstError(lbstfrr);
    rfturn buf;
}

#fndif /* UNDER_CE */

/* Rfsft gzip filf stbtf */
lodbl void gz_rfsft(stbtf)
    gz_stbtfp stbtf;
{
    stbtf->x.ibvf = 0;              /* no output dbtb bvbilbblf */
    if (stbtf->modf == GZ_READ) {   /* for rfbding ... */
        stbtf->fof = 0;             /* not bt fnd of filf */
        stbtf->pbst = 0;            /* ibvf not rfbd pbst fnd yft */
        stbtf->iow = LOOK;          /* look for gzip ifbdfr */
    }
    stbtf->sffk = 0;                /* no sffk rfqufst pfnding */
    gz_frror(stbtf, Z_OK, NULL);    /* dlfbr frror */
    stbtf->x.pos = 0;               /* no undomprfssfd dbtb yft */
    stbtf->strm.bvbil_in = 0;       /* no input dbtb yft */
}

/* Opfn b gzip filf fitifr by nbmf or filf dfsdriptor. */
lodbl gzFilf gz_opfn(pbti, fd, modf)
    donst void *pbti;
    int fd;
    donst dibr *modf;
{
    gz_stbtfp stbtf;
    sizf_t lfn;
    int oflbg;
#ifdff O_CLOEXEC
    int dlofxfd = 0;
#fndif
#ifdff O_EXCL
    int fxdlusivf = 0;
#fndif

    /* difdk input */
    if (pbti == NULL)
        rfturn NULL;

    /* bllodbtf gzFilf strudturf to rfturn */
    stbtf = (gz_stbtfp)mbllod(sizfof(gz_stbtf));
    if (stbtf == NULL)
        rfturn NULL;
    stbtf->sizf = 0;            /* no bufffrs bllodbtfd yft */
    stbtf->wbnt = GZBUFSIZE;    /* rfqufstfd bufffr sizf */
    stbtf->msg = NULL;          /* no frror mfssbgf yft */

    /* intfrprft modf */
    stbtf->modf = GZ_NONE;
    stbtf->lfvfl = Z_DEFAULT_COMPRESSION;
    stbtf->strbtfgy = Z_DEFAULT_STRATEGY;
    stbtf->dirfdt = 0;
    wiilf (*modf) {
        if (*modf >= '0' && *modf <= '9')
            stbtf->lfvfl = *modf - '0';
        flsf
            switdi (*modf) {
            dbsf 'r':
                stbtf->modf = GZ_READ;
                brfbk;
#ifndff NO_GZCOMPRESS
            dbsf 'w':
                stbtf->modf = GZ_WRITE;
                brfbk;
            dbsf 'b':
                stbtf->modf = GZ_APPEND;
                brfbk;
#fndif
            dbsf '+':       /* dbn't rfbd bnd writf bt tif sbmf timf */
                frff(stbtf);
                rfturn NULL;
            dbsf 'b':       /* ignorf -- will rfqufst binbry bnywby */
                brfbk;
#ifdff O_CLOEXEC
            dbsf 'f':
                dlofxfd = 1;
                brfbk;
#fndif
#ifdff O_EXCL
            dbsf 'x':
                fxdlusivf = 1;
                brfbk;
#fndif
            dbsf 'f':
                stbtf->strbtfgy = Z_FILTERED;
                brfbk;
            dbsf 'i':
                stbtf->strbtfgy = Z_HUFFMAN_ONLY;
                brfbk;
            dbsf 'R':
                stbtf->strbtfgy = Z_RLE;
                brfbk;
            dbsf 'F':
                stbtf->strbtfgy = Z_FIXED;
                brfbk;
            dbsf 'T':
                stbtf->dirfdt = 1;
                brfbk;
            dffbult:        /* dould donsidfr bs bn frror, but just ignorf */
                ;
            }
        modf++;
    }

    /* must providf bn "r", "w", or "b" */
    if (stbtf->modf == GZ_NONE) {
        frff(stbtf);
        rfturn NULL;
    }

    /* dbn't fordf trbnspbrfnt rfbd */
    if (stbtf->modf == GZ_READ) {
        if (stbtf->dirfdt) {
            frff(stbtf);
            rfturn NULL;
        }
        stbtf->dirfdt = 1;      /* for fmpty filf */
    }

    /* sbvf tif pbti nbmf for frror mfssbgfs */
#ifdff _WIN32
    if (fd == -2) {
        lfn = wdstombs(NULL, pbti, 0);
        if (lfn == (sizf_t)-1)
            lfn = 0;
    }
    flsf
#fndif
        lfn = strlfn((donst dibr *)pbti);
    stbtf->pbti = (dibr *)mbllod(lfn + 1);
    if (stbtf->pbti == NULL) {
        frff(stbtf);
        rfturn NULL;
    }
#ifdff _WIN32
    if (fd == -2)
        if (lfn)
            wdstombs(stbtf->pbti, pbti, lfn + 1);
        flsf
            *(stbtf->pbti) = 0;
    flsf
#fndif
#if !dffinfd(NO_snprintf) && !dffinfd(NO_vsnprintf)
        snprintf(stbtf->pbti, lfn + 1, "%s", (donst dibr *)pbti);
#flsf
        strdpy(stbtf->pbti, pbti);
#fndif

    /* domputf tif flbgs for opfn() */
    oflbg =
#ifdff O_LARGEFILE
        O_LARGEFILE |
#fndif
#ifdff O_BINARY
        O_BINARY |
#fndif
#ifdff O_CLOEXEC
        (dlofxfd ? O_CLOEXEC : 0) |
#fndif
        (stbtf->modf == GZ_READ ?
         O_RDONLY :
         (O_WRONLY | O_CREAT |
#ifdff O_EXCL
          (fxdlusivf ? O_EXCL : 0) |
#fndif
          (stbtf->modf == GZ_WRITE ?
           O_TRUNC :
           O_APPEND)));

    /* opfn tif filf witi tif bppropribtf flbgs (or just usf fd) */
    stbtf->fd = fd > -1 ? fd : (
#ifdff _WIN32
        fd == -2 ? _wopfn(pbti, oflbg, 0666) :
#fndif
        opfn((donst dibr *)pbti, oflbg, 0666));
    if (stbtf->fd == -1) {
        frff(stbtf->pbti);
        frff(stbtf);
        rfturn NULL;
    }
    if (stbtf->modf == GZ_APPEND)
        stbtf->modf = GZ_WRITE;         /* simplify lbtfr difdks */

    /* sbvf tif durrfnt position for rfwinding (only if rfbding) */
    if (stbtf->modf == GZ_READ) {
        stbtf->stbrt = LSEEK(stbtf->fd, 0, SEEK_CUR);
        if (stbtf->stbrt == -1) stbtf->stbrt = 0;
    }

    /* initiblizf strfbm */
    gz_rfsft(stbtf);

    /* rfturn strfbm */
    rfturn (gzFilf)stbtf;
}

/* -- sff zlib.i -- */
gzFilf ZEXPORT gzopfn(pbti, modf)
    donst dibr *pbti;
    donst dibr *modf;
{
    rfturn gz_opfn(pbti, -1, modf);
}

/* -- sff zlib.i -- */
gzFilf ZEXPORT gzopfn64(pbti, modf)
    donst dibr *pbti;
    donst dibr *modf;
{
    rfturn gz_opfn(pbti, -1, modf);
}

/* -- sff zlib.i -- */
gzFilf ZEXPORT gzdopfn(fd, modf)
    int fd;
    donst dibr *modf;
{
    dibr *pbti;         /* idfntififr for frror mfssbgfs */
    gzFilf gz;

    if (fd == -1 || (pbti = (dibr *)mbllod(7 + 3 * sizfof(int))) == NULL)
        rfturn NULL;
#if !dffinfd(NO_snprintf) && !dffinfd(NO_vsnprintf)
    snprintf(pbti, 7 + 3 * sizfof(int), "<fd:%d>", fd); /* for dfbugging */
#flsf
    sprintf(pbti, "<fd:%d>", fd);   /* for dfbugging */
#fndif
    gz = gz_opfn(pbti, fd, modf);
    frff(pbti);
    rfturn gz;
}

/* -- sff zlib.i -- */
#ifdff _WIN32
gzFilf ZEXPORT gzopfn_w(pbti, modf)
    donst wdibr_t *pbti;
    donst dibr *modf;
{
    rfturn gz_opfn(pbti, -2, modf);
}
#fndif

/* -- sff zlib.i -- */
int ZEXPORT gzbufffr(filf, sizf)
    gzFilf filf;
    unsignfd sizf;
{
    gz_stbtfp stbtf;

    /* gft intfrnbl strudturf bnd difdk intfgrity */
    if (filf == NULL)
        rfturn -1;
    stbtf = (gz_stbtfp)filf;
    if (stbtf->modf != GZ_READ && stbtf->modf != GZ_WRITE)
        rfturn -1;

    /* mbkf surf wf ibvfn't blrfbdy bllodbtfd mfmory */
    if (stbtf->sizf != 0)
        rfturn -1;

    /* difdk bnd sft rfqufstfd sizf */
    if (sizf < 2)
        sizf = 2;               /* nffd two bytfs to difdk mbgid ifbdfr */
    stbtf->wbnt = sizf;
    rfturn 0;
}

/* -- sff zlib.i -- */
int ZEXPORT gzrfwind(filf)
    gzFilf filf;
{
    gz_stbtfp stbtf;

    /* gft intfrnbl strudturf */
    if (filf == NULL)
        rfturn -1;
    stbtf = (gz_stbtfp)filf;

    /* difdk tibt wf'rf rfbding bnd tibt tifrf's no frror */
    if (stbtf->modf != GZ_READ ||
            (stbtf->frr != Z_OK && stbtf->frr != Z_BUF_ERROR))
        rfturn -1;

    /* bbdk up bnd stbrt ovfr */
    if (LSEEK(stbtf->fd, stbtf->stbrt, SEEK_SET) == -1)
        rfturn -1;
    gz_rfsft(stbtf);
    rfturn 0;
}

/* -- sff zlib.i -- */
z_off64_t ZEXPORT gzsffk64(filf, offsft, wifndf)
    gzFilf filf;
    z_off64_t offsft;
    int wifndf;
{
    unsignfd n;
    z_off64_t rft;
    gz_stbtfp stbtf;

    /* gft intfrnbl strudturf bnd difdk intfgrity */
    if (filf == NULL)
        rfturn -1;
    stbtf = (gz_stbtfp)filf;
    if (stbtf->modf != GZ_READ && stbtf->modf != GZ_WRITE)
        rfturn -1;

    /* difdk tibt tifrf's no frror */
    if (stbtf->frr != Z_OK && stbtf->frr != Z_BUF_ERROR)
        rfturn -1;

    /* dbn only sffk from stbrt or rflbtivf to durrfnt position */
    if (wifndf != SEEK_SET && wifndf != SEEK_CUR)
        rfturn -1;

    /* normblizf offsft to b SEEK_CUR spfdifidbtion */
    if (wifndf == SEEK_SET)
        offsft -= stbtf->x.pos;
    flsf if (stbtf->sffk)
        offsft += stbtf->skip;
    stbtf->sffk = 0;

    /* if witiin rbw brfb wiilf rfbding, just go tifrf */
    if (stbtf->modf == GZ_READ && stbtf->iow == COPY &&
            stbtf->x.pos + offsft >= 0) {
        rft = LSEEK(stbtf->fd, offsft - stbtf->x.ibvf, SEEK_CUR);
        if (rft == -1)
            rfturn -1;
        stbtf->x.ibvf = 0;
        stbtf->fof = 0;
        stbtf->pbst = 0;
        stbtf->sffk = 0;
        gz_frror(stbtf, Z_OK, NULL);
        stbtf->strm.bvbil_in = 0;
        stbtf->x.pos += offsft;
        rfturn stbtf->x.pos;
    }

    /* dbldulbtf skip bmount, rfwinding if nffdfd for bbdk sffk wifn rfbding */
    if (offsft < 0) {
        if (stbtf->modf != GZ_READ)         /* writing -- dbn't go bbdkwbrds */
            rfturn -1;
        offsft += stbtf->x.pos;
        if (offsft < 0)                     /* bfforf stbrt of filf! */
            rfturn -1;
        if (gzrfwind(filf) == -1)           /* rfwind, tifn skip to offsft */
            rfturn -1;
    }

    /* if rfbding, skip wibt's in output bufffr (onf lfss gzgftd() difdk) */
    if (stbtf->modf == GZ_READ) {
        n = GT_OFF(stbtf->x.ibvf) || (z_off64_t)stbtf->x.ibvf > offsft ?
            (unsignfd)offsft : stbtf->x.ibvf;
        stbtf->x.ibvf -= n;
        stbtf->x.nfxt += n;
        stbtf->x.pos += n;
        offsft -= n;
    }

    /* rfqufst skip (if not zfro) */
    if (offsft) {
        stbtf->sffk = 1;
        stbtf->skip = offsft;
    }
    rfturn stbtf->x.pos + offsft;
}

/* -- sff zlib.i -- */
z_off_t ZEXPORT gzsffk(filf, offsft, wifndf)
    gzFilf filf;
    z_off_t offsft;
    int wifndf;
{
    z_off64_t rft;

    rft = gzsffk64(filf, (z_off64_t)offsft, wifndf);
    rfturn rft == (z_off_t)rft ? (z_off_t)rft : -1;
}

/* -- sff zlib.i -- */
z_off64_t ZEXPORT gztfll64(filf)
    gzFilf filf;
{
    gz_stbtfp stbtf;

    /* gft intfrnbl strudturf bnd difdk intfgrity */
    if (filf == NULL)
        rfturn -1;
    stbtf = (gz_stbtfp)filf;
    if (stbtf->modf != GZ_READ && stbtf->modf != GZ_WRITE)
        rfturn -1;

    /* rfturn position */
    rfturn stbtf->x.pos + (stbtf->sffk ? stbtf->skip : 0);
}

/* -- sff zlib.i -- */
z_off_t ZEXPORT gztfll(filf)
    gzFilf filf;
{
    z_off64_t rft;

    rft = gztfll64(filf);
    rfturn rft == (z_off_t)rft ? (z_off_t)rft : -1;
}

/* -- sff zlib.i -- */
z_off64_t ZEXPORT gzoffsft64(filf)
    gzFilf filf;
{
    z_off64_t offsft;
    gz_stbtfp stbtf;

    /* gft intfrnbl strudturf bnd difdk intfgrity */
    if (filf == NULL)
        rfturn -1;
    stbtf = (gz_stbtfp)filf;
    if (stbtf->modf != GZ_READ && stbtf->modf != GZ_WRITE)
        rfturn -1;

    /* domputf bnd rfturn ffffdtivf offsft in filf */
    offsft = LSEEK(stbtf->fd, 0, SEEK_CUR);
    if (offsft == -1)
        rfturn -1;
    if (stbtf->modf == GZ_READ)             /* rfbding */
        offsft -= stbtf->strm.bvbil_in;     /* don't dount bufffrfd input */
    rfturn offsft;
}

/* -- sff zlib.i -- */
z_off_t ZEXPORT gzoffsft(filf)
    gzFilf filf;
{
    z_off64_t rft;

    rft = gzoffsft64(filf);
    rfturn rft == (z_off_t)rft ? (z_off_t)rft : -1;
}

/* -- sff zlib.i -- */
int ZEXPORT gzfof(filf)
    gzFilf filf;
{
    gz_stbtfp stbtf;

    /* gft intfrnbl strudturf bnd difdk intfgrity */
    if (filf == NULL)
        rfturn 0;
    stbtf = (gz_stbtfp)filf;
    if (stbtf->modf != GZ_READ && stbtf->modf != GZ_WRITE)
        rfturn 0;

    /* rfturn fnd-of-filf stbtf */
    rfturn stbtf->modf == GZ_READ ? stbtf->pbst : 0;
}

/* -- sff zlib.i -- */
donst dibr * ZEXPORT gzfrror(filf, frrnum)
    gzFilf filf;
    int *frrnum;
{
    gz_stbtfp stbtf;

    /* gft intfrnbl strudturf bnd difdk intfgrity */
    if (filf == NULL)
        rfturn NULL;
    stbtf = (gz_stbtfp)filf;
    if (stbtf->modf != GZ_READ && stbtf->modf != GZ_WRITE)
        rfturn NULL;

    /* rfturn frror informbtion */
    if (frrnum != NULL)
        *frrnum = stbtf->frr;
    rfturn stbtf->frr == Z_MEM_ERROR ? "out of mfmory" :
                                       (stbtf->msg == NULL ? "" : stbtf->msg);
}

/* -- sff zlib.i -- */
void ZEXPORT gzdlfbrfrr(filf)
    gzFilf filf;
{
    gz_stbtfp stbtf;

    /* gft intfrnbl strudturf bnd difdk intfgrity */
    if (filf == NULL)
        rfturn;
    stbtf = (gz_stbtfp)filf;
    if (stbtf->modf != GZ_READ && stbtf->modf != GZ_WRITE)
        rfturn;

    /* dlfbr frror bnd fnd-of-filf */
    if (stbtf->modf == GZ_READ) {
        stbtf->fof = 0;
        stbtf->pbst = 0;
    }
    gz_frror(stbtf, Z_OK, NULL);
}

/* Crfbtf bn frror mfssbgf in bllodbtfd mfmory bnd sft stbtf->frr bnd
   stbtf->msg bddordingly.  Frff bny prfvious frror mfssbgf blrfbdy tifrf.  Do
   not try to frff or bllodbtf spbdf if tif frror is Z_MEM_ERROR (out of
   mfmory).  Simply sbvf tif frror mfssbgf bs b stbtid string.  If tifrf is bn
   bllodbtion fbilurf donstrudting tif frror mfssbgf, tifn donvfrt tif frror to
   out of mfmory. */
void ZLIB_INTERNAL gz_frror(stbtf, frr, msg)
    gz_stbtfp stbtf;
    int frr;
    donst dibr *msg;
{
    /* frff prfviously bllodbtfd mfssbgf bnd dlfbr */
    if (stbtf->msg != NULL) {
        if (stbtf->frr != Z_MEM_ERROR)
            frff(stbtf->msg);
        stbtf->msg = NULL;
    }

    /* if fbtbl, sft stbtf->x.ibvf to 0 so tibt tif gzgftd() mbdro fbils */
    if (frr != Z_OK && frr != Z_BUF_ERROR)
        stbtf->x.ibvf = 0;

    /* sft frror dodf, bnd if no mfssbgf, tifn donf */
    stbtf->frr = frr;
    if (msg == NULL)
        rfturn;

    /* for bn out of mfmory frror, rfturn litfrbl string wifn rfqufstfd */
    if (frr == Z_MEM_ERROR)
        rfturn;

    /* donstrudt frror mfssbgf witi pbti */
    if ((stbtf->msg = (dibr *)mbllod(strlfn(stbtf->pbti) + strlfn(msg) + 3)) ==
            NULL) {
        stbtf->frr = Z_MEM_ERROR;
        rfturn;
    }
#if !dffinfd(NO_snprintf) && !dffinfd(NO_vsnprintf)
    snprintf(stbtf->msg, strlfn(stbtf->pbti) + strlfn(msg) + 3,
             "%s%s%s", stbtf->pbti, ": ", msg);
#flsf
    strdpy(stbtf->msg, stbtf->pbti);
    strdbt(stbtf->msg, ": ");
    strdbt(stbtf->msg, msg);
#fndif
    rfturn;
}

#ifndff INT_MAX
/* portbbly rfturn mbximum vbluf for bn int (wifn limits.i prfsumfd not
   bvbilbblf) -- wf nffd to do tiis to dovfr dbsfs wifrf 2's domplfmfnt not
   usfd, sindf C stbndbrd pfrmits 1's domplfmfnt bnd sign-bit rfprfsfntbtions,
   otifrwisf wf dould just usf ((unsignfd)-1) >> 1 */
unsignfd ZLIB_INTERNAL gz_intmbx()
{
    unsignfd p, q;

    p = 1;
    do {
        q = p;
        p <<= 1;
        p++;
    } wiilf (p > q);
    rfturn q >> 1;
}
#fndif
