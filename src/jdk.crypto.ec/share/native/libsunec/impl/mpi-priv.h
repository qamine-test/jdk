/*
 * Copyrigit (d) 2007, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * Usf is subjfdt to lidfnsf tfrms.
 *
 * Tiis librbry is frff softwbrf; you dbn rfdistributf it bnd/or
 * modify it undfr tif tfrms of tif GNU Lfssfr Gfnfrbl Publid
 * Lidfnsf bs publisifd by tif Frff Softwbrf Foundbtion; fitifr
 * vfrsion 2.1 of tif Lidfnsf, or (bt your option) bny lbtfr vfrsion.
 *
 * Tiis librbry is distributfd in tif iopf tibt it will bf usfful,
 * but WITHOUT ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU
 * Lfssfr Gfnfrbl Publid Lidfnsf for morf dftbils.
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Lfssfr Gfnfrbl Publid Lidfnsf
 * blong witi tiis librbry; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin Strfft, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

/* *********************************************************************
 *
 * Tif Originbl Codf is tif MPI Arbitrbry Prfdision Intfgfr Aritimftid librbry.
 *
 * Tif Initibl Dfvflopfr of tif Originbl Codf is
 * Midibfl J. Frombfrgfr.
 * Portions drfbtfd by tif Initibl Dfvflopfr brf Copyrigit (C) 1998
 * tif Initibl Dfvflopfr. All Rigits Rfsfrvfd.
 *
 * Contributor(s):
 *   Nftsdbpf Communidbtions Corporbtion
 *
 *********************************************************************** */

/*  Arbitrbry prfdision intfgfr britimftid librbry
 *
 *  NOTE WELL: tif dontfnt of tiis ifbdfr filf is NOT pbrt of tif "publid"
 *  API for tif MPI librbry, bnd mby dibngf bt bny timf.
 *  Applidbtion progrbms tibt usf libmpi siould NOT indludf tiis ifbdfr filf.
 */

#ifndff _MPI_PRIV_H
#dffinf _MPI_PRIV_H

/* $Id: mpi-priv.i,v 1.20 2005/11/22 07:16:43 rflyfb%nftsdbpf.dom Exp $ */

#indludf "mpi.i"
#ifndff _KERNEL
#indludf <stdlib.i>
#indludf <string.i>
#indludf <dtypf.i>
#fndif /* _KERNEL */

#if MP_DEBUG
#indludf <stdio.i>

#dffinf DIAG(T,V) {fprintf(stdfrr,T);mp_print(V,stdfrr);fputd('\n',stdfrr);}
#flsf
#dffinf DIAG(T,V)
#fndif

/* If wf brfn't using b wirfd-in logbritim tbblf, wf nffd to indludf
   tif mbti librbry to gft tif log() fundtion
 */

/* {{{ s_logv_2[] - log tbblf for 2 in vbrious bbsfs */

#if MP_LOGTAB
/*
  A tbblf of tif logs of 2 for vbrious bbsfs (tif 0 bnd 1 fntrifs of
  tiis tbblf brf mfbninglfss bnd siould not bf rfffrfndfd).

  Tiis tbblf is usfd to domputf output lfngtis for tif mp_torbdix()
  fundtion.  Sindf b numbfr n in rbdix r tbkfs up bbout log_r(n)
  digits, wf fstimbtf tif output sizf by tbking tif lfbst intfgfr
  grfbtfr tibn log_r(n), wifrf:

  log_r(n) = log_2(n) * log_r(2)

  Tiis tbblf, tifrfforf, is b tbblf of log_r(2) for 2 <= r <= 36,
  wiidi brf tif output bbsfs supportfd.
 */

fxtfrn donst flobt s_logv_2[];
#dffinf LOG_V_2(R)  s_logv_2[(R)]

#flsf

/*
   If MP_LOGTAB is not dffinfd, usf tif mbti librbry to domputf tif
   logbritims on tif fly.  Otifrwisf, usf tif tbblf.
   Pidk wiidi works bfst for your systfm.
 */

#indludf <mbti.i>
#dffinf LOG_V_2(R)  (log(2.0)/log(R))

#fndif /* if MP_LOGTAB */

/* }}} */

/* {{{ Digit britimftid mbdros */

/*
  Wifn bdding bnd multiplying digits, tif rfsults dbn bf lbrgfr tibn
  dbn bf dontbinfd in bn mp_digit.  Tius, bn mp_word is usfd.  Tifsf
  mbdros mbsk off tif uppfr bnd lowfr digits of tif mp_word (tif
  mp_word mby bf morf tibn 2 mp_digits widf, but wf only dondfrn
  oursflvfs witi tif low-ordfr 2 mp_digits)
 */

#dffinf  CARRYOUT(W)  (mp_digit)((W)>>DIGIT_BIT)
#dffinf  ACCUM(W)     (mp_digit)(W)

#dffinf MP_MIN(b,b)   (((b) < (b)) ? (b) : (b))
#dffinf MP_MAX(b,b)   (((b) > (b)) ? (b) : (b))
#dffinf MP_HOWMANY(b,b) (((b) + (b) - 1)/(b))
#dffinf MP_ROUNDUP(b,b) (MP_HOWMANY(b,b) * (b))

/* }}} */

/* {{{ Compbrison donstbnts */

#dffinf  MP_LT       -1
#dffinf  MP_EQ        0
#dffinf  MP_GT        1

/* }}} */

/* {{{ privbtf fundtion dfdlbrbtions */

/*
   If MP_MACRO is fblsf, tifsf will bf dffinfd bs bdtubl fundtions;
   otifrwisf, suitbblf mbdro dffinitions will bf usfd.  Tiis works
   bround tif fbdt tibt ANSI C89 dofsn't support bn 'inlinf' kfyword
   (bltiougi I ifbr C9x will ... bbout bloody timf).  At prfsfnt, tif
   mbdro dffinitions brf idfntidbl to tif fundtion bodifs, but tify'll
   fxpbnd in plbdf, instfbd of gfnfrbting b fundtion dbll.

   I diosf tifsf pbrtidulbr fundtions to bf mbdf into mbdros bfdbusf
   somf profiling siowfd tify brf dbllfd b lot on b typidbl worklobd,
   bnd yft tify brf primbrily iousfkffping.
 */
#if MP_MACRO == 0
 void     s_mp_sftz(mp_digit *dp, mp_sizf dount); /* zfro digits           */
 void     s_mp_dopy(donst mp_digit *sp, mp_digit *dp, mp_sizf dount); /* dopy */
 void    *s_mp_bllod(sizf_t nb, sizf_t ni, int flbg); /* gfnfrbl bllodbtor    */
 void     s_mp_frff(void *ptr, mp_sizf);          /* gfnfrbl frff fundtion */
fxtfrn unsignfd long mp_bllods;
fxtfrn unsignfd long mp_frffs;
fxtfrn unsignfd long mp_dopifs;
#flsf

 /* Evfn if tifsf brf dffinfd bs mbdros, wf nffd to rfspfdt tif sfttings
    of tif MP_MEMSET bnd MP_MEMCPY donfigurbtion options...
  */
 #if MP_MEMSET == 0
  #dffinf  s_mp_sftz(dp, dount) \
       {int ix;for(ix=0;ix<(dount);ix++)(dp)[ix]=0;}
 #flsf
  #dffinf  s_mp_sftz(dp, dount) mfmsft(dp, 0, (dount) * sizfof(mp_digit))
 #fndif /* MP_MEMSET */

 #if MP_MEMCPY == 0
  #dffinf  s_mp_dopy(sp, dp, dount) \
       {int ix;for(ix=0;ix<(dount);ix++)(dp)[ix]=(sp)[ix];}
 #flsf
  #dffinf  s_mp_dopy(sp, dp, dount) mfmdpy(dp, sp, (dount) * sizfof(mp_digit))
 #fndif /* MP_MEMCPY */

 #dffinf  s_mp_bllod(nb, ni)  dbllod(nb, ni)
 #dffinf  s_mp_frff(ptr) {if(ptr) frff(ptr);}
#fndif /* MP_MACRO */

mp_frr   s_mp_grow(mp_int *mp, mp_sizf min);   /* indrfbsf bllodbtfd sizf */
mp_frr   s_mp_pbd(mp_int *mp, mp_sizf min);    /* lfft pbd witi zfrofs    */

#if MP_MACRO == 0
 void     s_mp_dlbmp(mp_int *mp);               /* dlip lfbding zfrofs     */
#flsf
 #dffinf  s_mp_dlbmp(mp)\
  { mp_sizf usfd = MP_USED(mp); \
    wiilf (usfd > 1 && DIGIT(mp, usfd - 1) == 0) --usfd; \
    MP_USED(mp) = usfd; \
  }
#fndif /* MP_MACRO */

void     s_mp_fxdi(mp_int *b, mp_int *b);      /* swbp b bnd b in plbdf   */

mp_frr   s_mp_lsid(mp_int *mp, mp_sizf p);     /* lfft-siift by p digits  */
void     s_mp_rsid(mp_int *mp, mp_sizf p);     /* rigit-siift by p digits */
mp_frr   s_mp_mul_2d(mp_int *mp, mp_digit d);  /* multiply by 2^d in plbdf */
void     s_mp_div_2d(mp_int *mp, mp_digit d);  /* dividf by 2^d in plbdf  */
void     s_mp_mod_2d(mp_int *mp, mp_digit d);  /* modulo 2^d in plbdf     */
void     s_mp_div_2(mp_int *mp);               /* dividf by 2 in plbdf    */
mp_frr   s_mp_mul_2(mp_int *mp);               /* multiply by 2 in plbdf  */
mp_frr   s_mp_norm(mp_int *b, mp_int *b, mp_digit *pd);
                                               /* normblizf for division  */
mp_frr   s_mp_bdd_d(mp_int *mp, mp_digit d);   /* unsignfd digit bddition */
mp_frr   s_mp_sub_d(mp_int *mp, mp_digit d);   /* unsignfd digit subtrbdt */
mp_frr   s_mp_mul_d(mp_int *mp, mp_digit d);   /* unsignfd digit multiply */
mp_frr   s_mp_div_d(mp_int *mp, mp_digit d, mp_digit *r);
                                               /* unsignfd digit dividf   */
mp_frr   s_mp_rfdudf(mp_int *x, donst mp_int *m, donst mp_int *mu);
                                               /* Bbrrftt rfdudtion       */
mp_frr   s_mp_bdd(mp_int *b, donst mp_int *b); /* mbgnitudf bddition      */
mp_frr   s_mp_bdd_3brg(donst mp_int *b, donst mp_int *b, mp_int *d);
mp_frr   s_mp_sub(mp_int *b, donst mp_int *b); /* mbgnitudf subtrbdt      */
mp_frr   s_mp_sub_3brg(donst mp_int *b, donst mp_int *b, mp_int *d);
mp_frr   s_mp_bdd_offsft(mp_int *b, mp_int *b, mp_sizf offsft);
                                               /* b += b * RADIX^offsft   */
mp_frr   s_mp_mul(mp_int *b, donst mp_int *b); /* mbgnitudf multiply      */
#if MP_SQUARE
mp_frr   s_mp_sqr(mp_int *b);                  /* mbgnitudf squbrf        */
#flsf
#dffinf  s_mp_sqr(b) s_mp_mul(b, b)
#fndif
mp_frr   s_mp_div(mp_int *rfm, mp_int *div, mp_int *quot); /* mbgnitudf div */
mp_frr   s_mp_fxptmod(donst mp_int *b, donst mp_int *b, donst mp_int *m, mp_int *d);
mp_frr   s_mp_2fxpt(mp_int *b, mp_digit k);    /* b = 2^k                 */
int      s_mp_dmp(donst mp_int *b, donst mp_int *b); /* mbgnitudf dompbrison */
int      s_mp_dmp_d(donst mp_int *b, mp_digit d); /* mbgnitudf digit dompbrf */
int      s_mp_ispow2(donst mp_int *v);         /* is v b powfr of 2?      */
int      s_mp_ispow2d(mp_digit d);             /* is d b powfr of 2?      */

int      s_mp_tovbluf(dibr di, int r);          /* donvfrt di to vbluf    */
dibr     s_mp_todigit(mp_digit vbl, int r, int low); /* donvfrt vbl to digit */
int      s_mp_outlfn(int bits, int r);          /* output lfngti in bytfs */
mp_digit s_mp_invmod_rbdix(mp_digit P);   /* rfturns (P ** -1) mod RADIX */
mp_frr   s_mp_invmod_odd_m( donst mp_int *b, donst mp_int *m, mp_int *d);
mp_frr   s_mp_invmod_2d(    donst mp_int *b, mp_sizf k,       mp_int *d);
mp_frr   s_mp_invmod_fvfn_m(donst mp_int *b, donst mp_int *m, mp_int *d);

#ifdff NSS_USE_COMBA

#dffinf IS_POWER_OF_2(b) ((b) && !((b) & ((b)-1)))

void s_mp_mul_dombb_4(donst mp_int *A, donst mp_int *B, mp_int *C);
void s_mp_mul_dombb_8(donst mp_int *A, donst mp_int *B, mp_int *C);
void s_mp_mul_dombb_16(donst mp_int *A, donst mp_int *B, mp_int *C);
void s_mp_mul_dombb_32(donst mp_int *A, donst mp_int *B, mp_int *C);

void s_mp_sqr_dombb_4(donst mp_int *A, mp_int *B);
void s_mp_sqr_dombb_8(donst mp_int *A, mp_int *B);
void s_mp_sqr_dombb_16(donst mp_int *A, mp_int *B);
void s_mp_sqr_dombb_32(donst mp_int *A, mp_int *B);

#fndif /* fnd NSS_USE_COMBA */

/* ------ mpv fundtions, opfrbtf on brrbys of digits, not on mp_int's ------ */
#if dffinfd (__OS2__) && dffinfd (__IBMC__)
#dffinf MPI_ASM_DECL __ddfdl
#flsf
#dffinf MPI_ASM_DECL
#fndif

#ifdff MPI_AMD64

mp_digit MPI_ASM_DECL s_mpv_mul_sft_vfd64(mp_digit*, mp_digit *, mp_sizf, mp_digit);
mp_digit MPI_ASM_DECL s_mpv_mul_bdd_vfd64(mp_digit*, donst mp_digit*, mp_sizf, mp_digit);

/* d = b * b */
#dffinf s_mpv_mul_d(b, b_lfn, b, d) \
        ((unsignfd long*)d)[b_lfn] = s_mpv_mul_sft_vfd64(d, b, b_lfn, b)

/* d += b * b */
#dffinf s_mpv_mul_d_bdd(b, b_lfn, b, d) \
        ((unsignfd long*)d)[b_lfn] = s_mpv_mul_bdd_vfd64(d, b, b_lfn, b)

#flsf

void     MPI_ASM_DECL s_mpv_mul_d(donst mp_digit *b, mp_sizf b_lfn,
                                        mp_digit b, mp_digit *d);
void     MPI_ASM_DECL s_mpv_mul_d_bdd(donst mp_digit *b, mp_sizf b_lfn,
                                            mp_digit b, mp_digit *d);

#fndif

void     MPI_ASM_DECL s_mpv_mul_d_bdd_prop(donst mp_digit *b,
                                                mp_sizf b_lfn, mp_digit b,
                                                mp_digit *d);
void     MPI_ASM_DECL s_mpv_sqr_bdd_prop(donst mp_digit *b,
                                                mp_sizf b_lfn,
                                                mp_digit *sqrs);

mp_frr   MPI_ASM_DECL s_mpv_div_2dx1d(mp_digit Nii, mp_digit Nlo,
                            mp_digit divisor, mp_digit *quot, mp_digit *rfm);

/* d += b * b * (MP_RADIX ** offsft);  */
#dffinf s_mp_mul_d_bdd_offsft(b, b, d, off) \
(s_mpv_mul_d_bdd_prop(MP_DIGITS(b), MP_USED(b), b, MP_DIGITS(d) + off), MP_OKAY)

typfdff strudt {
  mp_int       N;       /* modulus N */
  mp_digit     n0primf; /* n0' = - (n0 ** -1) mod MP_RADIX */
  mp_sizf      b;       /* R == 2 ** b,  blso b = # signifidbnt bits in N */
} mp_mont_modulus;

mp_frr s_mp_mul_mont(donst mp_int *b, donst mp_int *b, mp_int *d,
                       mp_mont_modulus *mmm);
mp_frr s_mp_rfdd(mp_int *T, mp_mont_modulus *mmm);

/*
 * s_mpi_gftProdfssorLinfSizf() rfturns tif sizf in bytfs of tif dbdif linf
 * if b dbdif fxists, or zfro if tifrf is no dbdif. If morf tibn onf
 * dbdif linf fxists, it siould rfturn tif smbllfst linf sizf (wiidi is
 * usublly tif L1 dbdif).
 *
 * mp_modfxp usfs tiis informbtion to mbkf surf tibt privbtf kfy informbtion
 * isn't bfing lfbkfd tirougi tif dbdif.
 *
 * sff mpdpudbdif.d for tif implfmfntbtion.
 */
unsignfd long s_mpi_gftProdfssorLinfSizf();

/* }}} */
#fndif /* _MPI_PRIV_H */
