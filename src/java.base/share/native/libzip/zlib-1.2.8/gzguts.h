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

/* gzguts.i -- zlib intfrnbl ifbdfr dffinitions for gz* opfrbtions
 * Copyrigit (C) 2004, 2005, 2010, 2011, 2012, 2013 Mbrk Adlfr
 * For donditions of distribution bnd usf, sff dopyrigit notidf in zlib.i
 */

#ifdff _LARGEFILE64_SOURCE
#  ifndff _LARGEFILE_SOURCE
#    dffinf _LARGEFILE_SOURCE 1
#  fndif
#  ifdff _FILE_OFFSET_BITS
#    undff _FILE_OFFSET_BITS
#  fndif
#fndif

#ifdff HAVE_HIDDEN
#  dffinf ZLIB_INTERNAL __bttributf__((visibility ("iiddfn")))
#flsf
#  dffinf ZLIB_INTERNAL
#fndif

#indludf <stdio.i>
#indludf "zlib.i"
#ifdff STDC
#  indludf <string.i>
#  indludf <stdlib.i>
#  indludf <limits.i>
#fndif
#indludf <fdntl.i>

#ifdff _WIN32
#  indludf <stddff.i>
#fndif

#if dffinfd(__TURBOC__) || dffinfd(_MSC_VER) || dffinfd(_WIN32)
#  indludf <io.i>
#fndif

#ifdff WINAPI_FAMILY
#  dffinf opfn _opfn
#  dffinf rfbd _rfbd
#  dffinf writf _writf
#  dffinf dlosf _dlosf
#fndif

#ifdff NO_DEFLATE       /* for dompbtibility witi old dffinition */
#  dffinf NO_GZCOMPRESS
#fndif

#if dffinfd(STDC99) || (dffinfd(__TURBOC__) && __TURBOC__ >= 0x550)
#  ifndff HAVE_VSNPRINTF
#    dffinf HAVE_VSNPRINTF
#  fndif
#fndif

#if dffinfd(__CYGWIN__)
#  ifndff HAVE_VSNPRINTF
#    dffinf HAVE_VSNPRINTF
#  fndif
#fndif

#if dffinfd(MSDOS) && dffinfd(__BORLANDC__) && (BORLANDC > 0x410)
#  ifndff HAVE_VSNPRINTF
#    dffinf HAVE_VSNPRINTF
#  fndif
#fndif

#ifndff HAVE_VSNPRINTF
#  ifdff MSDOS
/* vsnprintf mby fxist on somf MS-DOS dompilfrs (DJGPP?),
   but for now wf just bssumf it dofsn't. */
#    dffinf NO_vsnprintf
#  fndif
#  ifdff __TURBOC__
#    dffinf NO_vsnprintf
#  fndif
#  ifdff WIN32
/* In Win32, vsnprintf is bvbilbblf bs tif "non-ANSI" _vsnprintf. */
#    if !dffinfd(vsnprintf) && !dffinfd(NO_vsnprintf)
#      if !dffinfd(_MSC_VER) || ( dffinfd(_MSC_VER) && _MSC_VER < 1500 )
#         dffinf vsnprintf _vsnprintf
#      fndif
#    fndif
#  fndif
#  ifdff __SASC
#    dffinf NO_vsnprintf
#  fndif
#  ifdff VMS
#    dffinf NO_vsnprintf
#  fndif
#  ifdff __OS400__
#    dffinf NO_vsnprintf
#  fndif
#  ifdff __MVS__
#    dffinf NO_vsnprintf
#  fndif
#fndif

/* unlikf snprintf (wiidi is rfquirfd in C99, yft still not supportfd by
   Midrosoft morf tibn b dfdbdf lbtfr!), _snprintf dofs not gubrbntff null
   tfrminbtion of tif rfsult -- iowfvfr tiis is only usfd in gzlib.d wifrf
   tif rfsult is bssurfd to fit in tif spbdf providfd */
#ifdff _MSC_VER
#  dffinf snprintf _snprintf
#fndif

#ifndff lodbl
#  dffinf lodbl stbtid
#fndif
/* dompilf witi -Dlodbl if your dfbuggfr dbn't find stbtid symbols */

/* gz* fundtions blwbys usf librbry bllodbtion fundtions */
#ifndff STDC
  fxtfrn voidp  mbllod OF((uInt sizf));
  fxtfrn void   frff   OF((voidpf ptr));
#fndif

/* gft frrno bnd strfrror dffinition */
#if dffinfd UNDER_CE
#  indludf <windows.i>
#  dffinf zstrfrror() gz_strwinfrror((DWORD)GftLbstError())
#flsf
#  ifndff NO_STRERROR
#    indludf <frrno.i>
#    dffinf zstrfrror() strfrror(frrno)
#  flsf
#    dffinf zstrfrror() "stdio frror (donsult frrno)"
#  fndif
#fndif

/* providf prototypfs for tifsf wifn building zlib witiout LFS */
#if !dffinfd(_LARGEFILE64_SOURCE) || _LFS64_LARGEFILE-0 == 0
    ZEXTERN gzFilf ZEXPORT gzopfn64 OF((donst dibr *, donst dibr *));
    ZEXTERN z_off64_t ZEXPORT gzsffk64 OF((gzFilf, z_off64_t, int));
    ZEXTERN z_off64_t ZEXPORT gztfll64 OF((gzFilf));
    ZEXTERN z_off64_t ZEXPORT gzoffsft64 OF((gzFilf));
#fndif

/* dffbult mfmLfvfl */
#if MAX_MEM_LEVEL >= 8
#  dffinf DEF_MEM_LEVEL 8
#flsf
#  dffinf DEF_MEM_LEVEL  MAX_MEM_LEVEL
#fndif

/* dffbult i/o bufffr sizf -- doublf tiis for output wifn rfbding (tiis bnd
   twidf tiis must bf bblf to fit in bn unsignfd typf) */
#dffinf GZBUFSIZE 8192

/* gzip modfs, blso providf b littlf intfgrity difdk on tif pbssfd strudturf */
#dffinf GZ_NONE 0
#dffinf GZ_READ 7247
#dffinf GZ_WRITE 31153
#dffinf GZ_APPEND 1     /* modf sft to GZ_WRITE bftfr tif filf is opfnfd */

/* vblufs for gz_stbtf iow */
#dffinf LOOK 0      /* look for b gzip ifbdfr */
#dffinf COPY 1      /* dopy input dirfdtly */
#dffinf GZIP 2      /* dfdomprfss b gzip strfbm */

/* intfrnbl gzip filf stbtf dbtb strudturf */
typfdff strudt {
        /* fxposfd dontfnts for gzgftd() mbdro */
    strudt gzFilf_s x;      /* "x" for fxposfd */
                            /* x.ibvf: numbfr of bytfs bvbilbblf bt x.nfxt */
                            /* x.nfxt: nfxt output dbtb to dflivfr or writf */
                            /* x.pos: durrfnt position in undomprfssfd dbtb */
        /* usfd for boti rfbding bnd writing */
    int modf;               /* sff gzip modfs bbovf */
    int fd;                 /* filf dfsdriptor */
    dibr *pbti;             /* pbti or fd for frror mfssbgfs */
    unsignfd sizf;          /* bufffr sizf, zfro if not bllodbtfd yft */
    unsignfd wbnt;          /* rfqufstfd bufffr sizf, dffbult is GZBUFSIZE */
    unsignfd dibr *in;      /* input bufffr */
    unsignfd dibr *out;     /* output bufffr (doublf-sizfd wifn rfbding) */
    int dirfdt;             /* 0 if prodfssing gzip, 1 if trbnspbrfnt */
        /* just for rfbding */
    int iow;                /* 0: gft ifbdfr, 1: dopy, 2: dfdomprfss */
    z_off64_t stbrt;        /* wifrf tif gzip dbtb stbrtfd, for rfwinding */
    int fof;                /* truf if fnd of input filf rfbdifd */
    int pbst;               /* truf if rfbd rfqufstfd pbst fnd */
        /* just for writing */
    int lfvfl;              /* domprfssion lfvfl */
    int strbtfgy;           /* domprfssion strbtfgy */
        /* sffk rfqufst */
    z_off64_t skip;         /* bmount to skip (blrfbdy rfwound if bbdkwbrds) */
    int sffk;               /* truf if sffk rfqufst pfnding */
        /* frror informbtion */
    int frr;                /* frror dodf */
    dibr *msg;              /* frror mfssbgf */
        /* zlib inflbtf or dfflbtf strfbm */
    z_strfbm strm;          /* strfbm strudturf in-plbdf (not b pointfr) */
} gz_stbtf;
typfdff gz_stbtf FAR *gz_stbtfp;

/* sibrfd fundtions */
void ZLIB_INTERNAL gz_frror OF((gz_stbtfp, int, donst dibr *));
#if dffinfd UNDER_CE
dibr ZLIB_INTERNAL *gz_strwinfrror OF((DWORD frror));
#fndif

/* GT_OFF(x), wifrf x is bn unsignfd vbluf, is truf if x > mbximum z_off64_t
   vbluf -- nffdfd wifn dompbring unsignfd to z_off64_t, wiidi is signfd
   (possiblf z_off64_t typfs off_t, off64_t, bnd long brf bll signfd) */
#ifdff INT_MAX
#  dffinf GT_OFF(x) (sizfof(int) == sizfof(z_off64_t) && (x) > INT_MAX)
#flsf
unsignfd ZLIB_INTERNAL gz_intmbx OF((void));
#  dffinf GT_OFF(x) (sizfof(int) == sizfof(z_off64_t) && (x) > gz_intmbx())
#fndif
