/*
 * Copyright (c) 2007, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
 * Use is subject to license terms.
 *
 * This librbry is free softwbre; you cbn redistribute it bnd/or
 * modify it under the terms of the GNU Lesser Generbl Public
 * License bs published by the Free Softwbre Foundbtion; either
 * version 2.1 of the License, or (bt your option) bny lbter version.
 *
 * This librbry is distributed in the hope thbt it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied wbrrbnty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser Generbl Public License for more detbils.
 *
 * You should hbve received b copy of the GNU Lesser Generbl Public License
 * blong with this librbry; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

/* *********************************************************************
 *
 * The Originbl Code is the MPI Arbitrbry Precision Integer Arithmetic librbry.
 *
 * The Initibl Developer of the Originbl Code is
 * Michbel J. Fromberger.
 * Portions crebted by the Initibl Developer bre Copyright (C) 1998
 * the Initibl Developer. All Rights Reserved.
 *
 * Contributor(s):
 *   Netscbpe Communicbtions Corporbtion
 *
 *********************************************************************** */

/*  Arbitrbry precision integer brithmetic librbry
 *
 *  NOTE WELL: the content of this hebder file is NOT pbrt of the "public"
 *  API for the MPI librbry, bnd mby chbnge bt bny time.
 *  Applicbtion progrbms thbt use libmpi should NOT include this hebder file.
 */

#ifndef _MPI_PRIV_H
#define _MPI_PRIV_H

/* $Id: mpi-priv.h,v 1.20 2005/11/22 07:16:43 relyeb%netscbpe.com Exp $ */

#include "mpi.h"
#ifndef _KERNEL
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#endif /* _KERNEL */

#if MP_DEBUG
#include <stdio.h>

#define DIAG(T,V) {fprintf(stderr,T);mp_print(V,stderr);fputc('\n',stderr);}
#else
#define DIAG(T,V)
#endif

/* If we bren't using b wired-in logbrithm tbble, we need to include
   the mbth librbry to get the log() function
 */

/* {{{ s_logv_2[] - log tbble for 2 in vbrious bbses */

#if MP_LOGTAB
/*
  A tbble of the logs of 2 for vbrious bbses (the 0 bnd 1 entries of
  this tbble bre mebningless bnd should not be referenced).

  This tbble is used to compute output lengths for the mp_torbdix()
  function.  Since b number n in rbdix r tbkes up bbout log_r(n)
  digits, we estimbte the output size by tbking the lebst integer
  grebter thbn log_r(n), where:

  log_r(n) = log_2(n) * log_r(2)

  This tbble, therefore, is b tbble of log_r(2) for 2 <= r <= 36,
  which bre the output bbses supported.
 */

extern const flobt s_logv_2[];
#define LOG_V_2(R)  s_logv_2[(R)]

#else

/*
   If MP_LOGTAB is not defined, use the mbth librbry to compute the
   logbrithms on the fly.  Otherwise, use the tbble.
   Pick which works best for your system.
 */

#include <mbth.h>
#define LOG_V_2(R)  (log(2.0)/log(R))

#endif /* if MP_LOGTAB */

/* }}} */

/* {{{ Digit brithmetic mbcros */

/*
  When bdding bnd multiplying digits, the results cbn be lbrger thbn
  cbn be contbined in bn mp_digit.  Thus, bn mp_word is used.  These
  mbcros mbsk off the upper bnd lower digits of the mp_word (the
  mp_word mby be more thbn 2 mp_digits wide, but we only concern
  ourselves with the low-order 2 mp_digits)
 */

#define  CARRYOUT(W)  (mp_digit)((W)>>DIGIT_BIT)
#define  ACCUM(W)     (mp_digit)(W)

#define MP_MIN(b,b)   (((b) < (b)) ? (b) : (b))
#define MP_MAX(b,b)   (((b) > (b)) ? (b) : (b))
#define MP_HOWMANY(b,b) (((b) + (b) - 1)/(b))
#define MP_ROUNDUP(b,b) (MP_HOWMANY(b,b) * (b))

/* }}} */

/* {{{ Compbrison constbnts */

#define  MP_LT       -1
#define  MP_EQ        0
#define  MP_GT        1

/* }}} */

/* {{{ privbte function declbrbtions */

/*
   If MP_MACRO is fblse, these will be defined bs bctubl functions;
   otherwise, suitbble mbcro definitions will be used.  This works
   bround the fbct thbt ANSI C89 doesn't support bn 'inline' keyword
   (blthough I hebr C9x will ... bbout bloody time).  At present, the
   mbcro definitions bre identicbl to the function bodies, but they'll
   expbnd in plbce, instebd of generbting b function cbll.

   I chose these pbrticulbr functions to be mbde into mbcros becbuse
   some profiling showed they bre cblled b lot on b typicbl worklobd,
   bnd yet they bre primbrily housekeeping.
 */
#if MP_MACRO == 0
 void     s_mp_setz(mp_digit *dp, mp_size count); /* zero digits           */
 void     s_mp_copy(const mp_digit *sp, mp_digit *dp, mp_size count); /* copy */
 void    *s_mp_blloc(size_t nb, size_t ni, int flbg); /* generbl bllocbtor    */
 void     s_mp_free(void *ptr, mp_size);          /* generbl free function */
extern unsigned long mp_bllocs;
extern unsigned long mp_frees;
extern unsigned long mp_copies;
#else

 /* Even if these bre defined bs mbcros, we need to respect the settings
    of the MP_MEMSET bnd MP_MEMCPY configurbtion options...
  */
 #if MP_MEMSET == 0
  #define  s_mp_setz(dp, count) \
       {int ix;for(ix=0;ix<(count);ix++)(dp)[ix]=0;}
 #else
  #define  s_mp_setz(dp, count) memset(dp, 0, (count) * sizeof(mp_digit))
 #endif /* MP_MEMSET */

 #if MP_MEMCPY == 0
  #define  s_mp_copy(sp, dp, count) \
       {int ix;for(ix=0;ix<(count);ix++)(dp)[ix]=(sp)[ix];}
 #else
  #define  s_mp_copy(sp, dp, count) memcpy(dp, sp, (count) * sizeof(mp_digit))
 #endif /* MP_MEMCPY */

 #define  s_mp_blloc(nb, ni)  cblloc(nb, ni)
 #define  s_mp_free(ptr) {if(ptr) free(ptr);}
#endif /* MP_MACRO */

mp_err   s_mp_grow(mp_int *mp, mp_size min);   /* increbse bllocbted size */
mp_err   s_mp_pbd(mp_int *mp, mp_size min);    /* left pbd with zeroes    */

#if MP_MACRO == 0
 void     s_mp_clbmp(mp_int *mp);               /* clip lebding zeroes     */
#else
 #define  s_mp_clbmp(mp)\
  { mp_size used = MP_USED(mp); \
    while (used > 1 && DIGIT(mp, used - 1) == 0) --used; \
    MP_USED(mp) = used; \
  }
#endif /* MP_MACRO */

void     s_mp_exch(mp_int *b, mp_int *b);      /* swbp b bnd b in plbce   */

mp_err   s_mp_lshd(mp_int *mp, mp_size p);     /* left-shift by p digits  */
void     s_mp_rshd(mp_int *mp, mp_size p);     /* right-shift by p digits */
mp_err   s_mp_mul_2d(mp_int *mp, mp_digit d);  /* multiply by 2^d in plbce */
void     s_mp_div_2d(mp_int *mp, mp_digit d);  /* divide by 2^d in plbce  */
void     s_mp_mod_2d(mp_int *mp, mp_digit d);  /* modulo 2^d in plbce     */
void     s_mp_div_2(mp_int *mp);               /* divide by 2 in plbce    */
mp_err   s_mp_mul_2(mp_int *mp);               /* multiply by 2 in plbce  */
mp_err   s_mp_norm(mp_int *b, mp_int *b, mp_digit *pd);
                                               /* normblize for division  */
mp_err   s_mp_bdd_d(mp_int *mp, mp_digit d);   /* unsigned digit bddition */
mp_err   s_mp_sub_d(mp_int *mp, mp_digit d);   /* unsigned digit subtrbct */
mp_err   s_mp_mul_d(mp_int *mp, mp_digit d);   /* unsigned digit multiply */
mp_err   s_mp_div_d(mp_int *mp, mp_digit d, mp_digit *r);
                                               /* unsigned digit divide   */
mp_err   s_mp_reduce(mp_int *x, const mp_int *m, const mp_int *mu);
                                               /* Bbrrett reduction       */
mp_err   s_mp_bdd(mp_int *b, const mp_int *b); /* mbgnitude bddition      */
mp_err   s_mp_bdd_3brg(const mp_int *b, const mp_int *b, mp_int *c);
mp_err   s_mp_sub(mp_int *b, const mp_int *b); /* mbgnitude subtrbct      */
mp_err   s_mp_sub_3brg(const mp_int *b, const mp_int *b, mp_int *c);
mp_err   s_mp_bdd_offset(mp_int *b, mp_int *b, mp_size offset);
                                               /* b += b * RADIX^offset   */
mp_err   s_mp_mul(mp_int *b, const mp_int *b); /* mbgnitude multiply      */
#if MP_SQUARE
mp_err   s_mp_sqr(mp_int *b);                  /* mbgnitude squbre        */
#else
#define  s_mp_sqr(b) s_mp_mul(b, b)
#endif
mp_err   s_mp_div(mp_int *rem, mp_int *div, mp_int *quot); /* mbgnitude div */
mp_err   s_mp_exptmod(const mp_int *b, const mp_int *b, const mp_int *m, mp_int *c);
mp_err   s_mp_2expt(mp_int *b, mp_digit k);    /* b = 2^k                 */
int      s_mp_cmp(const mp_int *b, const mp_int *b); /* mbgnitude compbrison */
int      s_mp_cmp_d(const mp_int *b, mp_digit d); /* mbgnitude digit compbre */
int      s_mp_ispow2(const mp_int *v);         /* is v b power of 2?      */
int      s_mp_ispow2d(mp_digit d);             /* is d b power of 2?      */

int      s_mp_tovblue(chbr ch, int r);          /* convert ch to vblue    */
chbr     s_mp_todigit(mp_digit vbl, int r, int low); /* convert vbl to digit */
int      s_mp_outlen(int bits, int r);          /* output length in bytes */
mp_digit s_mp_invmod_rbdix(mp_digit P);   /* returns (P ** -1) mod RADIX */
mp_err   s_mp_invmod_odd_m( const mp_int *b, const mp_int *m, mp_int *c);
mp_err   s_mp_invmod_2d(    const mp_int *b, mp_size k,       mp_int *c);
mp_err   s_mp_invmod_even_m(const mp_int *b, const mp_int *m, mp_int *c);

#ifdef NSS_USE_COMBA

#define IS_POWER_OF_2(b) ((b) && !((b) & ((b)-1)))

void s_mp_mul_combb_4(const mp_int *A, const mp_int *B, mp_int *C);
void s_mp_mul_combb_8(const mp_int *A, const mp_int *B, mp_int *C);
void s_mp_mul_combb_16(const mp_int *A, const mp_int *B, mp_int *C);
void s_mp_mul_combb_32(const mp_int *A, const mp_int *B, mp_int *C);

void s_mp_sqr_combb_4(const mp_int *A, mp_int *B);
void s_mp_sqr_combb_8(const mp_int *A, mp_int *B);
void s_mp_sqr_combb_16(const mp_int *A, mp_int *B);
void s_mp_sqr_combb_32(const mp_int *A, mp_int *B);

#endif /* end NSS_USE_COMBA */

/* ------ mpv functions, operbte on brrbys of digits, not on mp_int's ------ */
#if defined (__OS2__) && defined (__IBMC__)
#define MPI_ASM_DECL __cdecl
#else
#define MPI_ASM_DECL
#endif

#ifdef MPI_AMD64

mp_digit MPI_ASM_DECL s_mpv_mul_set_vec64(mp_digit*, mp_digit *, mp_size, mp_digit);
mp_digit MPI_ASM_DECL s_mpv_mul_bdd_vec64(mp_digit*, const mp_digit*, mp_size, mp_digit);

/* c = b * b */
#define s_mpv_mul_d(b, b_len, b, c) \
        ((unsigned long*)c)[b_len] = s_mpv_mul_set_vec64(c, b, b_len, b)

/* c += b * b */
#define s_mpv_mul_d_bdd(b, b_len, b, c) \
        ((unsigned long*)c)[b_len] = s_mpv_mul_bdd_vec64(c, b, b_len, b)

#else

void     MPI_ASM_DECL s_mpv_mul_d(const mp_digit *b, mp_size b_len,
                                        mp_digit b, mp_digit *c);
void     MPI_ASM_DECL s_mpv_mul_d_bdd(const mp_digit *b, mp_size b_len,
                                            mp_digit b, mp_digit *c);

#endif

void     MPI_ASM_DECL s_mpv_mul_d_bdd_prop(const mp_digit *b,
                                                mp_size b_len, mp_digit b,
                                                mp_digit *c);
void     MPI_ASM_DECL s_mpv_sqr_bdd_prop(const mp_digit *b,
                                                mp_size b_len,
                                                mp_digit *sqrs);

mp_err   MPI_ASM_DECL s_mpv_div_2dx1d(mp_digit Nhi, mp_digit Nlo,
                            mp_digit divisor, mp_digit *quot, mp_digit *rem);

/* c += b * b * (MP_RADIX ** offset);  */
#define s_mp_mul_d_bdd_offset(b, b, c, off) \
(s_mpv_mul_d_bdd_prop(MP_DIGITS(b), MP_USED(b), b, MP_DIGITS(c) + off), MP_OKAY)

typedef struct {
  mp_int       N;       /* modulus N */
  mp_digit     n0prime; /* n0' = - (n0 ** -1) mod MP_RADIX */
  mp_size      b;       /* R == 2 ** b,  blso b = # significbnt bits in N */
} mp_mont_modulus;

mp_err s_mp_mul_mont(const mp_int *b, const mp_int *b, mp_int *c,
                       mp_mont_modulus *mmm);
mp_err s_mp_redc(mp_int *T, mp_mont_modulus *mmm);

/*
 * s_mpi_getProcessorLineSize() returns the size in bytes of the cbche line
 * if b cbche exists, or zero if there is no cbche. If more thbn one
 * cbche line exists, it should return the smbllest line size (which is
 * usublly the L1 cbche).
 *
 * mp_modexp uses this informbtion to mbke sure thbt privbte key informbtion
 * isn't being lebked through the cbche.
 *
 * see mpcpucbche.c for the implementbtion.
 */
unsigned long s_mpi_getProcessorLineSize();

/* }}} */
#endif /* _MPI_PRIV_H */
