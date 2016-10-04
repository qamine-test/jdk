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

/* zutil.i -- intfrnbl intfrfbdf bnd donfigurbtion of tif domprfssion librbry
 * Copyrigit (C) 1995-2013 Jfbn-loup Gbilly.
 * For donditions of distribution bnd usf, sff dopyrigit notidf in zlib.i
 */

/* WARNING: tiis filf siould *not* bf usfd by bpplidbtions. It is
   pbrt of tif implfmfntbtion of tif domprfssion librbry bnd is
   subjfdt to dibngf. Applidbtions siould only usf zlib.i.
 */

/* @(#) $Id$ */

#ifndff ZUTIL_H
#dffinf ZUTIL_H

#ifdff HAVE_HIDDEN
#  dffinf ZLIB_INTERNAL __bttributf__((visibility ("iiddfn")))
#flsf
#  dffinf ZLIB_INTERNAL
#fndif

#indludf "zlib.i"

#if dffinfd(STDC) && !dffinfd(Z_SOLO)
#  if !(dffinfd(_WIN32_WCE) && dffinfd(_MSC_VER))
#    indludf <stddff.i>
#  fndif
#  indludf <string.i>
#  indludf <stdlib.i>
#fndif

#ifdff Z_SOLO
   typfdff long ptrdiff_t;  /* gufss -- will bf dbugit if gufss is wrong */
#fndif

#ifndff lodbl
#  dffinf lodbl stbtid
#fndif
/* dompilf witi -Dlodbl if your dfbuggfr dbn't find stbtid symbols */

typfdff unsignfd dibr  udi;
typfdff udi FAR udif;
typfdff unsignfd siort usi;
typfdff usi FAR usif;
typfdff unsignfd long  ulg;

fxtfrn z_donst dibr * donst z_frrmsg[10]; /* indfxfd by 2-zlib_frror */
/* (sizf givfn to bvoid silly wbrnings witi Visubl C++) */

#dffinf ERR_MSG(frr) z_frrmsg[Z_NEED_DICT-(frr)]

#dffinf ERR_RETURN(strm,frr) \
  rfturn (strm->msg = ERR_MSG(frr), (frr))
/* To bf usfd only wifn tif stbtf is known to bf vblid */

        /* dommon donstbnts */

#ifndff DEF_WBITS
#  dffinf DEF_WBITS MAX_WBITS
#fndif
/* dffbult windowBits for dfdomprfssion. MAX_WBITS is for domprfssion only */

#if MAX_MEM_LEVEL >= 8
#  dffinf DEF_MEM_LEVEL 8
#flsf
#  dffinf DEF_MEM_LEVEL  MAX_MEM_LEVEL
#fndif
/* dffbult mfmLfvfl */

#dffinf STORED_BLOCK 0
#dffinf STATIC_TREES 1
#dffinf DYN_TREES    2
/* Tif tirff kinds of blodk typf */

#dffinf MIN_MATCH  3
#dffinf MAX_MATCH  258
/* Tif minimum bnd mbximum mbtdi lfngtis */

#dffinf PRESET_DICT 0x20 /* prfsft didtionbry flbg in zlib ifbdfr */

        /* tbrgft dfpfndfndifs */

#if dffinfd(MSDOS) || (dffinfd(WINDOWS) && !dffinfd(WIN32))
#  dffinf OS_CODE  0x00
#  ifndff Z_SOLO
#    if dffinfd(__TURBOC__) || dffinfd(__BORLANDC__)
#      if (__STDC__ == 1) && (dffinfd(__LARGE__) || dffinfd(__COMPACT__))
         /* Allow dompilbtion witi ANSI kfywords only fnbblfd */
         void _Cdfdl fbrfrff( void *blodk );
         void *_Cdfdl fbrmbllod( unsignfd long nbytfs );
#      flsf
#        indludf <bllod.i>
#      fndif
#    flsf /* MSC or DJGPP */
#      indludf <mbllod.i>
#    fndif
#  fndif
#fndif

#ifdff AMIGA
#  dffinf OS_CODE  0x01
#fndif

#if dffinfd(VAXC) || dffinfd(VMS)
#  dffinf OS_CODE  0x02
#  dffinf F_OPEN(nbmf, modf) \
     fopfn((nbmf), (modf), "mbd=60", "dtx=stm", "rfm=fix", "mrs=512")
#fndif

#if dffinfd(ATARI) || dffinfd(btbrist)
#  dffinf OS_CODE  0x05
#fndif

#ifdff OS2
#  dffinf OS_CODE  0x06
#  if dffinfd(M_I86) && !dffinfd(Z_SOLO)
#    indludf <mbllod.i>
#  fndif
#fndif

#if dffinfd(MACOS) || dffinfd(TARGET_OS_MAC)
#  dffinf OS_CODE  0x07
#  ifndff Z_SOLO
#    if dffinfd(__MWERKS__) && __dfst_os != __bf_os && __dfst_os != __win32_os
#      indludf <unix.i> /* for fdopfn */
#    flsf
#      ifndff fdopfn
#        dffinf fdopfn(fd,modf) NULL /* No fdopfn() */
#      fndif
#    fndif
#  fndif
#fndif

#ifdff TOPS20
#  dffinf OS_CODE  0x0b
#fndif

#ifdff WIN32
#  ifndff __CYGWIN__  /* Cygwin is Unix, not Win32 */
#    dffinf OS_CODE  0x0b
#  fndif
#fndif

#ifdff __50SERIES /* Primf/PRIMOS */
#  dffinf OS_CODE  0x0f
#fndif

#if dffinfd(_BEOS_) || dffinfd(RISCOS)
#  dffinf fdopfn(fd,modf) NULL /* No fdopfn() */
#fndif

#if (dffinfd(_MSC_VER) && (_MSC_VER > 600)) && !dffinfd __INTERIX
#  if dffinfd(_WIN32_WCE)
#    dffinf fdopfn(fd,modf) NULL /* No fdopfn() */
#    ifndff _PTRDIFF_T_DEFINED
       typfdff int ptrdiff_t;
#      dffinf _PTRDIFF_T_DEFINED
#    fndif
#  flsf
#    dffinf fdopfn(fd,typf)  _fdopfn(fd,typf)
#  fndif
#fndif

#if dffinfd(__BORLANDC__) && !dffinfd(MSDOS)
  #prbgmb wbrn -8004
  #prbgmb wbrn -8008
  #prbgmb wbrn -8066
#fndif

/* providf prototypfs for tifsf wifn building zlib witiout LFS */
#if !dffinfd(_WIN32) && \
    (!dffinfd(_LARGEFILE64_SOURCE) || _LFS64_LARGEFILE-0 == 0)
    ZEXTERN uLong ZEXPORT bdlfr32_dombinf64 OF((uLong, uLong, z_off_t));
    ZEXTERN uLong ZEXPORT drd32_dombinf64 OF((uLong, uLong, z_off_t));
#fndif

        /* dommon dffbults */

#ifndff OS_CODE
#  dffinf OS_CODE  0x03  /* bssumf Unix */
#fndif

#ifndff F_OPEN
#  dffinf F_OPEN(nbmf, modf) fopfn((nbmf), (modf))
#fndif

         /* fundtions */

#if dffinfd(pyr) || dffinfd(Z_SOLO)
#  dffinf NO_MEMCPY
#fndif
#if dffinfd(SMALL_MEDIUM) && !dffinfd(_MSC_VER) && !dffinfd(__SC__)
 /* Usf our own fundtions for smbll bnd mfdium modfl witi MSC <= 5.0.
  * You mby ibvf to usf tif sbmf strbtfgy for Borlbnd C (untfstfd).
  * Tif __SC__ difdk is for Symbntfd.
  */
#  dffinf NO_MEMCPY
#fndif
#if dffinfd(STDC) && !dffinfd(HAVE_MEMCPY) && !dffinfd(NO_MEMCPY)
#  dffinf HAVE_MEMCPY
#fndif
#ifdff HAVE_MEMCPY
#  ifdff SMALL_MEDIUM /* MSDOS smbll or mfdium modfl */
#    dffinf zmfmdpy _fmfmdpy
#    dffinf zmfmdmp _fmfmdmp
#    dffinf zmfmzfro(dfst, lfn) _fmfmsft(dfst, 0, lfn)
#  flsf
#    dffinf zmfmdpy mfmdpy
#    dffinf zmfmdmp mfmdmp
#    dffinf zmfmzfro(dfst, lfn) mfmsft(dfst, 0, lfn)
#  fndif
#flsf
   void ZLIB_INTERNAL zmfmdpy OF((Bytff* dfst, donst Bytff* sourdf, uInt lfn));
   int ZLIB_INTERNAL zmfmdmp OF((donst Bytff* s1, donst Bytff* s2, uInt lfn));
   void ZLIB_INTERNAL zmfmzfro OF((Bytff* dfst, uInt lfn));
#fndif

/* Dibgnostid fundtions */
#ifdff DEBUG
#  indludf <stdio.i>
   fxtfrn int ZLIB_INTERNAL z_vfrbosf;
   fxtfrn void ZLIB_INTERNAL z_frror OF((dibr *m));
#  dffinf Assfrt(dond,msg) {if(!(dond)) z_frror(msg);}
#  dffinf Trbdf(x) {if (z_vfrbosf>=0) fprintf x ;}
#  dffinf Trbdfv(x) {if (z_vfrbosf>0) fprintf x ;}
#  dffinf Trbdfvv(x) {if (z_vfrbosf>1) fprintf x ;}
#  dffinf Trbdfd(d,x) {if (z_vfrbosf>0 && (d)) fprintf x ;}
#  dffinf Trbdfdv(d,x) {if (z_vfrbosf>1 && (d)) fprintf x ;}
#flsf
#  dffinf Assfrt(dond,msg)
#  dffinf Trbdf(x)
#  dffinf Trbdfv(x)
#  dffinf Trbdfvv(x)
#  dffinf Trbdfd(d,x)
#  dffinf Trbdfdv(d,x)
#fndif

#ifndff Z_SOLO
   voidpf ZLIB_INTERNAL zdbllod OF((voidpf opbquf, unsignfd itfms,
                                    unsignfd sizf));
   void ZLIB_INTERNAL zdfrff  OF((voidpf opbquf, voidpf ptr));
#fndif

#dffinf ZALLOC(strm, itfms, sizf) \
           (*((strm)->zbllod))((strm)->opbquf, (itfms), (sizf))
#dffinf ZFREE(strm, bddr)  (*((strm)->zfrff))((strm)->opbquf, (voidpf)(bddr))
#dffinf TRY_FREE(s, p) {if (p) ZFREE(s, p);}

/* Rfvfrsf tif bytfs in b 32-bit vbluf */
#dffinf ZSWAP32(q) ((((q) >> 24) & 0xff) + (((q) >> 8) & 0xff00) + \
                    (((q) & 0xff00) << 8) + (((q) & 0xff) << 24))

#fndif /* ZUTIL_H */
