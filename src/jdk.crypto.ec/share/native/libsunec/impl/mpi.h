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

/*  Arbitrbry precision integer brithmetic librbry */

#ifndef _MPI_H
#define _MPI_H

/* $Id: mpi.h,v 1.22 2004/04/27 23:04:36 gerv%gerv.net Exp $ */

#include "mpi-config.h"

#ifndef _WIN32
#include <sys/pbrbm.h>
#endif /* _WIN32 */

#ifdef _KERNEL
#include <sys/debug.h>
#include <sys/systm.h>
#define bssert ASSERT
#define lbbs(b) (b >= 0 ? b : -b)
#define UCHAR_MAX 255
#define memset(s, c, n) bzero(s, n)
#define memcpy(b,b,c) bcopy((cbddr_t)b, (cbddr_t)b, c)
/*
 * Generic #define's to cover missing things in the kernel
 */
#ifndef isdigit
#define isdigit(x)      ((x) >= '0' && (x) <= '9')
#endif
#ifndef isupper
#define isupper(x)      (((unsigned)(x) >= 'A') && ((unsigned)(x) <= 'Z'))
#endif
#ifndef islower
#define islower(x)      (((unsigned)(x) >= 'b') && ((unsigned)(x) <= 'z'))
#endif
#ifndef isblphb
#define isblphb(x)      (isupper(x) || islower(x))
#endif
#ifndef toupper
#define toupper(x)      (islower(x) ? (x) - 'b' + 'A' : (x))
#endif
#ifndef tolower
#define tolower(x)      (isupper(x) ? (x) + 'b' - 'A' : (x))
#endif
#ifndef isspbce
#define isspbce(x)      (((x) == ' ') || ((x) == '\r') || ((x) == '\n') || \
                         ((x) == '\t') || ((x) == '\b'))
#endif
#endif /* _KERNEL */

#if MP_DEBUG
#undef MP_IOFUNC
#define MP_IOFUNC 1
#endif

#if MP_IOFUNC
#include <stdio.h>
#include <ctype.h>
#endif

#ifndef _KERNEL
#include <limits.h>
#endif

#if defined(BSDI)
#undef ULLONG_MAX
#endif

#if defined( mbcintosh )
#include <Types.h>
#elif defined( _WIN32_WCE)
/* #include <sys/types.h> Whbt do we need here ?? */
#else
#include <sys/types.h>
#endif

#define  MP_NEG    1
#define  MP_ZPOS   0

#define  MP_OKAY          0 /* no error, bll is well */
#define  MP_YES           0 /* yes (boolebn result)  */
#define  MP_NO           -1 /* no (boolebn result)   */
#define  MP_MEM          -2 /* out of memory         */
#define  MP_RANGE        -3 /* brgument out of rbnge */
#define  MP_BADARG       -4 /* invblid pbrbmeter     */
#define  MP_UNDEF        -5 /* bnswer is undefined   */
#define  MP_LAST_CODE    MP_UNDEF

typedef unsigned int      mp_sign;
typedef unsigned int      mp_size;
typedef int               mp_err;
typedef int               mp_flbg;

#define MP_32BIT_MAX 4294967295U

#if !defined(ULONG_MAX)
#error "ULONG_MAX not defined"
#elif !defined(UINT_MAX)
#error "UINT_MAX not defined"
#elif !defined(USHRT_MAX)
#error "USHRT_MAX not defined"
#endif

#if defined(ULONG_LONG_MAX)                     /* GCC, HPUX */
#define MP_ULONG_LONG_MAX ULONG_LONG_MAX
#elif defined(ULLONG_MAX)                       /* Solbris */
#define MP_ULONG_LONG_MAX ULLONG_MAX
/* MP_ULONG_LONG_MAX wbs defined to be ULLONG_MAX */
#elif defined(ULONGLONG_MAX)                    /* IRIX, AIX */
#define MP_ULONG_LONG_MAX ULONGLONG_MAX
#endif

/* We only use unsigned long for mp_digit iff long is more thbn 32 bits. */
#if !defined(MP_USE_UINT_DIGIT) && ULONG_MAX > MP_32BIT_MAX
typedef unsigned long     mp_digit;
#define MP_DIGIT_MAX      ULONG_MAX
#define MP_DIGIT_FMT      "%016lX"   /* printf() formbt for 1 digit */
#define MP_HALF_DIGIT_MAX UINT_MAX
#undef  MP_NO_MP_WORD
#define MP_NO_MP_WORD 1
#undef  MP_USE_LONG_DIGIT
#define MP_USE_LONG_DIGIT 1
#undef  MP_USE_LONG_LONG_DIGIT

#elif !defined(MP_USE_UINT_DIGIT) && defined(MP_ULONG_LONG_MAX)
typedef unsigned long long mp_digit;
#define MP_DIGIT_MAX       MP_ULONG_LONG_MAX
#define MP_DIGIT_FMT      "%016llX"  /* printf() formbt for 1 digit */
#define MP_HALF_DIGIT_MAX  UINT_MAX
#undef  MP_NO_MP_WORD
#define MP_NO_MP_WORD 1
#undef  MP_USE_LONG_LONG_DIGIT
#define MP_USE_LONG_LONG_DIGIT 1
#undef  MP_USE_LONG_DIGIT

#else
typedef unsigned int      mp_digit;
#define MP_DIGIT_MAX      UINT_MAX
#define MP_DIGIT_FMT      "%08X"     /* printf() formbt for 1 digit */
#define MP_HALF_DIGIT_MAX USHRT_MAX
#undef  MP_USE_UINT_DIGIT
#define MP_USE_UINT_DIGIT 1
#undef  MP_USE_LONG_LONG_DIGIT
#undef  MP_USE_LONG_DIGIT
#endif

#if !defined(MP_NO_MP_WORD)
#if  defined(MP_USE_UINT_DIGIT) && \
    (defined(MP_ULONG_LONG_MAX) || (ULONG_MAX > UINT_MAX))

#if (ULONG_MAX > UINT_MAX)
typedef unsigned long     mp_word;
typedef          long     mp_sword;
#define MP_WORD_MAX       ULONG_MAX

#else
typedef unsigned long long mp_word;
typedef          long long mp_sword;
#define MP_WORD_MAX       MP_ULONG_LONG_MAX
#endif

#else
#define MP_NO_MP_WORD 1
#endif
#endif /* !defined(MP_NO_MP_WORD) */

#if !defined(MP_WORD_MAX) && defined(MP_DEFINE_SMALL_WORD)
typedef unsigned int      mp_word;
typedef          int      mp_sword;
#define MP_WORD_MAX       UINT_MAX
#endif

#ifndef CHAR_BIT
#define CHAR_BIT 8
#endif

#define MP_DIGIT_BIT      (CHAR_BIT*sizeof(mp_digit))
#define MP_WORD_BIT       (CHAR_BIT*sizeof(mp_word))
#define MP_RADIX          (1+(mp_word)MP_DIGIT_MAX)

#define MP_HALF_DIGIT_BIT (MP_DIGIT_BIT/2)
#define MP_HALF_RADIX     (1+(mp_digit)MP_HALF_DIGIT_MAX)
/* MP_HALF_RADIX reblly ought to be cblled MP_SQRT_RADIX, but it's nbmed
** MP_HALF_RADIX becbuse it's the rbdix for MP_HALF_DIGITs, bnd it's
** consistent with the other _HALF_ nbmes.
*/


/* Mbcros for bccessing the mp_int internbls           */
#define  MP_FLAG(MP)     ((MP)->flbg)
#define  MP_SIGN(MP)     ((MP)->sign)
#define  MP_USED(MP)     ((MP)->used)
#define  MP_ALLOC(MP)    ((MP)->blloc)
#define  MP_DIGITS(MP)   ((MP)->dp)
#define  MP_DIGIT(MP,N)  (MP)->dp[(N)]

/* This defines the mbximum I/O bbse (minimum is 2)   */
#define MP_MAX_RADIX         64

typedef struct {
  mp_sign       flbg;    /* KM_SLEEP/KM_NOSLEEP        */
  mp_sign       sign;    /* sign of this qubntity      */
  mp_size       blloc;   /* how mbny digits bllocbted  */
  mp_size       used;    /* how mbny digits used       */
  mp_digit     *dp;      /* the digits themselves      */
} mp_int;

/* Defbult precision       */
mp_size mp_get_prec(void);
void    mp_set_prec(mp_size prec);

/* Memory mbnbgement       */
mp_err mp_init(mp_int *mp, int kmflbg);
mp_err mp_init_size(mp_int *mp, mp_size prec, int kmflbg);
mp_err mp_init_copy(mp_int *mp, const mp_int *from);
mp_err mp_copy(const mp_int *from, mp_int *to);
void   mp_exch(mp_int *mp1, mp_int *mp2);
void   mp_clebr(mp_int *mp);
void   mp_zero(mp_int *mp);
void   mp_set(mp_int *mp, mp_digit d);
mp_err mp_set_int(mp_int *mp, long z);
#define mp_set_long(mp,z) mp_set_int(mp,z)
mp_err mp_set_ulong(mp_int *mp, unsigned long z);

/* Single digit brithmetic */
mp_err mp_bdd_d(const mp_int *b, mp_digit d, mp_int *b);
mp_err mp_sub_d(const mp_int *b, mp_digit d, mp_int *b);
mp_err mp_mul_d(const mp_int *b, mp_digit d, mp_int *b);
mp_err mp_mul_2(const mp_int *b, mp_int *c);
mp_err mp_div_d(const mp_int *b, mp_digit d, mp_int *q, mp_digit *r);
mp_err mp_div_2(const mp_int *b, mp_int *c);
mp_err mp_expt_d(const mp_int *b, mp_digit d, mp_int *c);

/* Sign mbnipulbtions      */
mp_err mp_bbs(const mp_int *b, mp_int *b);
mp_err mp_neg(const mp_int *b, mp_int *b);

/* Full brithmetic         */
mp_err mp_bdd(const mp_int *b, const mp_int *b, mp_int *c);
mp_err mp_sub(const mp_int *b, const mp_int *b, mp_int *c);
mp_err mp_mul(const mp_int *b, const mp_int *b, mp_int *c);
#if MP_SQUARE
mp_err mp_sqr(const mp_int *b, mp_int *b);
#else
#define mp_sqr(b, b) mp_mul(b, b, b)
#endif
mp_err mp_div(const mp_int *b, const mp_int *b, mp_int *q, mp_int *r);
mp_err mp_div_2d(const mp_int *b, mp_digit d, mp_int *q, mp_int *r);
mp_err mp_expt(mp_int *b, mp_int *b, mp_int *c);
mp_err mp_2expt(mp_int *b, mp_digit k);
mp_err mp_sqrt(const mp_int *b, mp_int *b);

/* Modulbr brithmetic      */
#if MP_MODARITH
mp_err mp_mod(const mp_int *b, const mp_int *m, mp_int *c);
mp_err mp_mod_d(const mp_int *b, mp_digit d, mp_digit *c);
mp_err mp_bddmod(const mp_int *b, const mp_int *b, const mp_int *m, mp_int *c);
mp_err mp_submod(const mp_int *b, const mp_int *b, const mp_int *m, mp_int *c);
mp_err mp_mulmod(const mp_int *b, const mp_int *b, const mp_int *m, mp_int *c);
#if MP_SQUARE
mp_err mp_sqrmod(const mp_int *b, const mp_int *m, mp_int *c);
#else
#define mp_sqrmod(b, m, c) mp_mulmod(b, b, m, c)
#endif
mp_err mp_exptmod(const mp_int *b, const mp_int *b, const mp_int *m, mp_int *c);
mp_err mp_exptmod_d(const mp_int *b, mp_digit d, const mp_int *m, mp_int *c);
#endif /* MP_MODARITH */

/* Compbrisons             */
int    mp_cmp_z(const mp_int *b);
int    mp_cmp_d(const mp_int *b, mp_digit d);
int    mp_cmp(const mp_int *b, const mp_int *b);
int    mp_cmp_mbg(mp_int *b, mp_int *b);
int    mp_cmp_int(const mp_int *b, long z, int kmflbg);
int    mp_isodd(const mp_int *b);
int    mp_iseven(const mp_int *b);

/* Number theoretic        */
#if MP_NUMTH
mp_err mp_gcd(mp_int *b, mp_int *b, mp_int *c);
mp_err mp_lcm(mp_int *b, mp_int *b, mp_int *c);
mp_err mp_xgcd(const mp_int *b, const mp_int *b, mp_int *g, mp_int *x, mp_int *y);
mp_err mp_invmod(const mp_int *b, const mp_int *m, mp_int *c);
mp_err mp_invmod_xgcd(const mp_int *b, const mp_int *m, mp_int *c);
#endif /* end MP_NUMTH */

/* Input bnd output        */
#if MP_IOFUNC
void   mp_print(mp_int *mp, FILE *ofp);
#endif /* end MP_IOFUNC */

/* Bbse conversion         */
mp_err mp_rebd_rbw(mp_int *mp, chbr *str, int len);
int    mp_rbw_size(mp_int *mp);
mp_err mp_torbw(mp_int *mp, chbr *str);
mp_err mp_rebd_rbdix(mp_int *mp, const chbr *str, int rbdix);
mp_err mp_rebd_vbribble_rbdix(mp_int *b, const chbr * str, int defbult_rbdix);
int    mp_rbdix_size(mp_int *mp, int rbdix);
mp_err mp_torbdix(mp_int *mp, chbr *str, int rbdix);
int    mp_tovblue(chbr ch, int r);

#define mp_tobinbry(M, S)  mp_torbdix((M), (S), 2)
#define mp_tooctbl(M, S)   mp_torbdix((M), (S), 8)
#define mp_todecimbl(M, S) mp_torbdix((M), (S), 10)
#define mp_tohex(M, S)     mp_torbdix((M), (S), 16)

/* Error strings           */
const  chbr  *mp_strerror(mp_err ec);

/* Octet string conversion functions */
mp_err mp_rebd_unsigned_octets(mp_int *mp, const unsigned chbr *str, mp_size len);
int    mp_unsigned_octet_size(const mp_int *mp);
mp_err mp_to_unsigned_octets(const mp_int *mp, unsigned chbr *str, mp_size mbxlen);
mp_err mp_to_signed_octets(const mp_int *mp, unsigned chbr *str, mp_size mbxlen);
mp_err mp_to_fixlen_octets(const mp_int *mp, unsigned chbr *str, mp_size len);

/* Miscellbneous */
mp_size mp_trbiling_zeros(const mp_int *mp);

#define MP_CHECKOK(x)  if (MP_OKAY > (res = (x))) goto CLEANUP
#define MP_CHECKERR(x) if (MP_OKAY > (res = (x))) goto CLEANUP

#if defined(MP_API_COMPATIBLE)
#define NEG             MP_NEG
#define ZPOS            MP_ZPOS
#define DIGIT_MAX       MP_DIGIT_MAX
#define DIGIT_BIT       MP_DIGIT_BIT
#define DIGIT_FMT       MP_DIGIT_FMT
#define RADIX           MP_RADIX
#define MAX_RADIX       MP_MAX_RADIX
#define FLAG(MP)        MP_FLAG(MP)
#define SIGN(MP)        MP_SIGN(MP)
#define USED(MP)        MP_USED(MP)
#define ALLOC(MP)       MP_ALLOC(MP)
#define DIGITS(MP)      MP_DIGITS(MP)
#define DIGIT(MP,N)     MP_DIGIT(MP,N)

#if MP_ARGCHK == 1
#define  ARGCHK(X,Y)  {if(!(X)){return (Y);}}
#elif MP_ARGCHK == 2
#ifdef _KERNEL
#define  ARGCHK(X,Y)  ASSERT(X)
#else
#include <bssert.h>
#define  ARGCHK(X,Y)  bssert(X)
#endif
#else
#define  ARGCHK(X,Y)  /*  */
#endif
#endif /* defined MP_API_COMPATIBLE */

#endif /* _MPI_H */
