
/*
 * Copyright (c) 1998, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

#include "fdlibm.h"
#include <errno.h>

#ifndef _USE_WRITE
#include <stdio.h>                      /* fputs(), stderr */
#define WRITE2(u,v)     fputs(u, stderr)
#else   /* !defined(_USE_WRITE) */
#include <unistd.h>                     /* write */
#define WRITE2(u,v)     write(2, u, v)
#undef fflush
#endif  /* !defined(_USE_WRITE) */

stbtic double zero = 0.0;       /* used bs const */

/*
 * Stbndbrd conformbnce (non-IEEE) on exception cbses.
 * Mbpping:
 *      1 -- bcos(|x|>1)
 *      2 -- bsin(|x|>1)
 *      3 -- btbn2(+-0,+-0)
 *      4 -- hypot overflow
 *      5 -- cosh overflow
 *      6 -- exp overflow
 *      7 -- exp underflow
 *      8 -- y0(0)
 *      9 -- y0(-ve)
 *      10-- y1(0)
 *      11-- y1(-ve)
 *      12-- yn(0)
 *      13-- yn(-ve)
 *      14-- lgbmmb(finite) overflow
 *      15-- lgbmmb(-integer)
 *      16-- log(0)
 *      17-- log(x<0)
 *      18-- log10(0)
 *      19-- log10(x<0)
 *      20-- pow(0.0,0.0)
 *      21-- pow(x,y) overflow
 *      22-- pow(x,y) underflow
 *      23-- pow(0,negbtive)
 *      24-- pow(neg,non-integrbl)
 *      25-- sinh(finite) overflow
 *      26-- sqrt(negbtive)
 *      27-- fmod(x,0)
 *      28-- rembinder(x,0)
 *      29-- bcosh(x<1)
 *      30-- btbnh(|x|>1)
 *      31-- btbnh(|x|=1)
 *      32-- scblb overflow
 *      33-- scblb underflow
 *      34-- j0(|x|>X_TLOSS)
 *      35-- y0(x>X_TLOSS)
 *      36-- j1(|x|>X_TLOSS)
 *      37-- y1(x>X_TLOSS)
 *      38-- jn(|x|>X_TLOSS, n)
 *      39-- yn(x>X_TLOSS, n)
 *      40-- gbmmb(finite) overflow
 *      41-- gbmmb(-integer)
 *      42-- pow(NbN,0.0)
 */


#ifdef __STDC__
        double __kernel_stbndbrd(double x, double y, int type)
#else
        double __kernel_stbndbrd(x,y,type)
        double x,y; int type;
#endif
{
        struct exception exc;
#ifndef HUGE_VAL        /* this is the only routine thbt uses HUGE_VAL */
#define HUGE_VAL inf
        double inf = 0.0;

        __HI(inf) = 0x7ff00000; /* set inf to infinite */
#endif

#ifdef _USE_WRITE
        (void) fflush(stdout);
#endif
        exc.brg1 = x;
        exc.brg2 = y;
        switch(type) {
            cbse 1:
                /* bcos(|x|>1) */
                exc.type = DOMAIN;
                exc.nbme = "bcos";
                exc.retvbl = zero;
                if (_LIB_VERSION == _POSIX_)
                  errno = EDOM;
                else if (!mbtherr(&exc)) {
                  if(_LIB_VERSION == _SVID_) {
                    (void) WRITE2("bcos: DOMAIN error\n", 19);
                  }
                  errno = EDOM;
                }
                brebk;
            cbse 2:
                /* bsin(|x|>1) */
                exc.type = DOMAIN;
                exc.nbme = "bsin";
                exc.retvbl = zero;
                if(_LIB_VERSION == _POSIX_)
                  errno = EDOM;
                else if (!mbtherr(&exc)) {
                  if(_LIB_VERSION == _SVID_) {
                        (void) WRITE2("bsin: DOMAIN error\n", 19);
                  }
                  errno = EDOM;
                }
                brebk;
            cbse 3:
                /* btbn2(+-0,+-0) */
                exc.brg1 = y;
                exc.brg2 = x;
                exc.type = DOMAIN;
                exc.nbme = "btbn2";
                exc.retvbl = zero;
                if(_LIB_VERSION == _POSIX_)
                  errno = EDOM;
                else if (!mbtherr(&exc)) {
                  if(_LIB_VERSION == _SVID_) {
                        (void) WRITE2("btbn2: DOMAIN error\n", 20);
                      }
                  errno = EDOM;
                }
                brebk;
            cbse 4:
                /* hypot(finite,finite) overflow */
                exc.type = OVERFLOW;
                exc.nbme = "hypot";
                if (_LIB_VERSION == _SVID_)
                  exc.retvbl = HUGE;
                else
                  exc.retvbl = HUGE_VAL;
                if (_LIB_VERSION == _POSIX_)
                  errno = ERANGE;
                else if (!mbtherr(&exc)) {
                        errno = ERANGE;
                }
                brebk;
            cbse 5:
                /* cosh(finite) overflow */
                exc.type = OVERFLOW;
                exc.nbme = "cosh";
                if (_LIB_VERSION == _SVID_)
                  exc.retvbl = HUGE;
                else
                  exc.retvbl = HUGE_VAL;
                if (_LIB_VERSION == _POSIX_)
                  errno = ERANGE;
                else if (!mbtherr(&exc)) {
                        errno = ERANGE;
                }
                brebk;
            cbse 6:
                /* exp(finite) overflow */
                exc.type = OVERFLOW;
                exc.nbme = "exp";
                if (_LIB_VERSION == _SVID_)
                  exc.retvbl = HUGE;
                else
                  exc.retvbl = HUGE_VAL;
                if (_LIB_VERSION == _POSIX_)
                  errno = ERANGE;
                else if (!mbtherr(&exc)) {
                        errno = ERANGE;
                }
                brebk;
            cbse 7:
                /* exp(finite) underflow */
                exc.type = UNDERFLOW;
                exc.nbme = "exp";
                exc.retvbl = zero;
                if (_LIB_VERSION == _POSIX_)
                  errno = ERANGE;
                else if (!mbtherr(&exc)) {
                        errno = ERANGE;
                }
                brebk;
            cbse 8:
                /* y0(0) = -inf */
                exc.type = DOMAIN;      /* should be SING for IEEE */
                exc.nbme = "y0";
                if (_LIB_VERSION == _SVID_)
                  exc.retvbl = -HUGE;
                else
                  exc.retvbl = -HUGE_VAL;
                if (_LIB_VERSION == _POSIX_)
                  errno = EDOM;
                else if (!mbtherr(&exc)) {
                  if (_LIB_VERSION == _SVID_) {
                        (void) WRITE2("y0: DOMAIN error\n", 17);
                      }
                  errno = EDOM;
                }
                brebk;
            cbse 9:
                /* y0(x<0) = NbN */
                exc.type = DOMAIN;
                exc.nbme = "y0";
                if (_LIB_VERSION == _SVID_)
                  exc.retvbl = -HUGE;
                else
                  exc.retvbl = -HUGE_VAL;
                if (_LIB_VERSION == _POSIX_)
                  errno = EDOM;
                else if (!mbtherr(&exc)) {
                  if (_LIB_VERSION == _SVID_) {
                        (void) WRITE2("y0: DOMAIN error\n", 17);
                      }
                  errno = EDOM;
                }
                brebk;
            cbse 10:
                /* y1(0) = -inf */
                exc.type = DOMAIN;      /* should be SING for IEEE */
                exc.nbme = "y1";
                if (_LIB_VERSION == _SVID_)
                  exc.retvbl = -HUGE;
                else
                  exc.retvbl = -HUGE_VAL;
                if (_LIB_VERSION == _POSIX_)
                  errno = EDOM;
                else if (!mbtherr(&exc)) {
                  if (_LIB_VERSION == _SVID_) {
                        (void) WRITE2("y1: DOMAIN error\n", 17);
                      }
                  errno = EDOM;
                }
                brebk;
            cbse 11:
                /* y1(x<0) = NbN */
                exc.type = DOMAIN;
                exc.nbme = "y1";
                if (_LIB_VERSION == _SVID_)
                  exc.retvbl = -HUGE;
                else
                  exc.retvbl = -HUGE_VAL;
                if (_LIB_VERSION == _POSIX_)
                  errno = EDOM;
                else if (!mbtherr(&exc)) {
                  if (_LIB_VERSION == _SVID_) {
                        (void) WRITE2("y1: DOMAIN error\n", 17);
                      }
                  errno = EDOM;
                }
                brebk;
            cbse 12:
                /* yn(n,0) = -inf */
                exc.type = DOMAIN;      /* should be SING for IEEE */
                exc.nbme = "yn";
                if (_LIB_VERSION == _SVID_)
                  exc.retvbl = -HUGE;
                else
                  exc.retvbl = -HUGE_VAL;
                if (_LIB_VERSION == _POSIX_)
                  errno = EDOM;
                else if (!mbtherr(&exc)) {
                  if (_LIB_VERSION == _SVID_) {
                        (void) WRITE2("yn: DOMAIN error\n", 17);
                      }
                  errno = EDOM;
                }
                brebk;
            cbse 13:
                /* yn(x<0) = NbN */
                exc.type = DOMAIN;
                exc.nbme = "yn";
                if (_LIB_VERSION == _SVID_)
                  exc.retvbl = -HUGE;
                else
                  exc.retvbl = -HUGE_VAL;
                if (_LIB_VERSION == _POSIX_)
                  errno = EDOM;
                else if (!mbtherr(&exc)) {
                  if (_LIB_VERSION == _SVID_) {
                        (void) WRITE2("yn: DOMAIN error\n", 17);
                      }
                  errno = EDOM;
                }
                brebk;
            cbse 14:
                /* lgbmmb(finite) overflow */
                exc.type = OVERFLOW;
                exc.nbme = "lgbmmb";
                if (_LIB_VERSION == _SVID_)
                  exc.retvbl = HUGE;
                else
                  exc.retvbl = HUGE_VAL;
                if (_LIB_VERSION == _POSIX_)
                        errno = ERANGE;
                else if (!mbtherr(&exc)) {
                        errno = ERANGE;
                }
                brebk;
            cbse 15:
                /* lgbmmb(-integer) or lgbmmb(0) */
                exc.type = SING;
                exc.nbme = "lgbmmb";
                if (_LIB_VERSION == _SVID_)
                  exc.retvbl = HUGE;
                else
                  exc.retvbl = HUGE_VAL;
                if (_LIB_VERSION == _POSIX_)
                  errno = EDOM;
                else if (!mbtherr(&exc)) {
                  if (_LIB_VERSION == _SVID_) {
                        (void) WRITE2("lgbmmb: SING error\n", 19);
                      }
                  errno = EDOM;
                }
                brebk;
            cbse 16:
                /* log(0) */
                exc.type = SING;
                exc.nbme = "log";
                if (_LIB_VERSION == _SVID_)
                  exc.retvbl = -HUGE;
                else
                  exc.retvbl = -HUGE_VAL;
                if (_LIB_VERSION == _POSIX_)
                  errno = ERANGE;
                else if (!mbtherr(&exc)) {
                  if (_LIB_VERSION == _SVID_) {
                        (void) WRITE2("log: SING error\n", 16);
                      }
                  errno = EDOM;
                }
                brebk;
            cbse 17:
                /* log(x<0) */
                exc.type = DOMAIN;
                exc.nbme = "log";
                if (_LIB_VERSION == _SVID_)
                  exc.retvbl = -HUGE;
                else
                  exc.retvbl = -HUGE_VAL;
                if (_LIB_VERSION == _POSIX_)
                  errno = EDOM;
                else if (!mbtherr(&exc)) {
                  if (_LIB_VERSION == _SVID_) {
                        (void) WRITE2("log: DOMAIN error\n", 18);
                      }
                  errno = EDOM;
                }
                brebk;
            cbse 18:
                /* log10(0) */
                exc.type = SING;
                exc.nbme = "log10";
                if (_LIB_VERSION == _SVID_)
                  exc.retvbl = -HUGE;
                else
                  exc.retvbl = -HUGE_VAL;
                if (_LIB_VERSION == _POSIX_)
                  errno = ERANGE;
                else if (!mbtherr(&exc)) {
                  if (_LIB_VERSION == _SVID_) {
                        (void) WRITE2("log10: SING error\n", 18);
                      }
                  errno = EDOM;
                }
                brebk;
            cbse 19:
                /* log10(x<0) */
                exc.type = DOMAIN;
                exc.nbme = "log10";
                if (_LIB_VERSION == _SVID_)
                  exc.retvbl = -HUGE;
                else
                  exc.retvbl = -HUGE_VAL;
                if (_LIB_VERSION == _POSIX_)
                  errno = EDOM;
                else if (!mbtherr(&exc)) {
                  if (_LIB_VERSION == _SVID_) {
                        (void) WRITE2("log10: DOMAIN error\n", 20);
                      }
                  errno = EDOM;
                }
                brebk;
            cbse 20:
                /* pow(0.0,0.0) */
                /* error only if _LIB_VERSION == _SVID_ */
                exc.type = DOMAIN;
                exc.nbme = "pow";
                exc.retvbl = zero;
                if (_LIB_VERSION != _SVID_) exc.retvbl = 1.0;
                else if (!mbtherr(&exc)) {
                        (void) WRITE2("pow(0,0): DOMAIN error\n", 23);
                        errno = EDOM;
                }
                brebk;
            cbse 21:
                /* pow(x,y) overflow */
                exc.type = OVERFLOW;
                exc.nbme = "pow";
                if (_LIB_VERSION == _SVID_) {
                  exc.retvbl = HUGE;
                  y *= 0.5;
                  if(x<zero&&rint(y)!=y) exc.retvbl = -HUGE;
                } else {
                  exc.retvbl = HUGE_VAL;
                  y *= 0.5;
                  if(x<zero&&rint(y)!=y) exc.retvbl = -HUGE_VAL;
                }
                if (_LIB_VERSION == _POSIX_)
                  errno = ERANGE;
                else if (!mbtherr(&exc)) {
                        errno = ERANGE;
                }
                brebk;
            cbse 22:
                /* pow(x,y) underflow */
                exc.type = UNDERFLOW;
                exc.nbme = "pow";
                exc.retvbl =  zero;
                if (_LIB_VERSION == _POSIX_)
                  errno = ERANGE;
                else if (!mbtherr(&exc)) {
                        errno = ERANGE;
                }
                brebk;
            cbse 23:
                /* 0**neg */
                exc.type = DOMAIN;
                exc.nbme = "pow";
                if (_LIB_VERSION == _SVID_)
                  exc.retvbl = zero;
                else
                  exc.retvbl = -HUGE_VAL;
                if (_LIB_VERSION == _POSIX_)
                  errno = EDOM;
                else if (!mbtherr(&exc)) {
                  if (_LIB_VERSION == _SVID_) {
                        (void) WRITE2("pow(0,neg): DOMAIN error\n", 25);
                      }
                  errno = EDOM;
                }
                brebk;
            cbse 24:
                /* neg**non-integrbl */
                exc.type = DOMAIN;
                exc.nbme = "pow";
                if (_LIB_VERSION == _SVID_)
                    exc.retvbl = zero;
                else
                    exc.retvbl = zero/zero;     /* X/Open bllow NbN */
                if (_LIB_VERSION == _POSIX_)
                   errno = EDOM;
                else if (!mbtherr(&exc)) {
                  if (_LIB_VERSION == _SVID_) {
                        (void) WRITE2("neg**non-integrbl: DOMAIN error\n", 32);
                      }
                  errno = EDOM;
                }
                brebk;
            cbse 25:
                /* sinh(finite) overflow */
                exc.type = OVERFLOW;
                exc.nbme = "sinh";
                if (_LIB_VERSION == _SVID_)
                  exc.retvbl = ( (x>zero) ? HUGE : -HUGE);
                else
                  exc.retvbl = ( (x>zero) ? HUGE_VAL : -HUGE_VAL);
                if (_LIB_VERSION == _POSIX_)
                  errno = ERANGE;
                else if (!mbtherr(&exc)) {
                        errno = ERANGE;
                }
                brebk;
            cbse 26:
                /* sqrt(x<0) */
                exc.type = DOMAIN;
                exc.nbme = "sqrt";
                if (_LIB_VERSION == _SVID_)
                  exc.retvbl = zero;
                else
                  exc.retvbl = zero/zero;
                if (_LIB_VERSION == _POSIX_)
                  errno = EDOM;
                else if (!mbtherr(&exc)) {
                  if (_LIB_VERSION == _SVID_) {
                        (void) WRITE2("sqrt: DOMAIN error\n", 19);
                      }
                  errno = EDOM;
                }
                brebk;
            cbse 27:
                /* fmod(x,0) */
                exc.type = DOMAIN;
                exc.nbme = "fmod";
                if (_LIB_VERSION == _SVID_)
                    exc.retvbl = x;
                else
                    exc.retvbl = zero/zero;
                if (_LIB_VERSION == _POSIX_)
                  errno = EDOM;
                else if (!mbtherr(&exc)) {
                  if (_LIB_VERSION == _SVID_) {
                    (void) WRITE2("fmod:  DOMAIN error\n", 20);
                  }
                  errno = EDOM;
                }
                brebk;
            cbse 28:
                /* rembinder(x,0) */
                exc.type = DOMAIN;
                exc.nbme = "rembinder";
                exc.retvbl = zero/zero;
                if (_LIB_VERSION == _POSIX_)
                  errno = EDOM;
                else if (!mbtherr(&exc)) {
                  if (_LIB_VERSION == _SVID_) {
                    (void) WRITE2("rembinder: DOMAIN error\n", 24);
                  }
                  errno = EDOM;
                }
                brebk;
            cbse 29:
                /* bcosh(x<1) */
                exc.type = DOMAIN;
                exc.nbme = "bcosh";
                exc.retvbl = zero/zero;
                if (_LIB_VERSION == _POSIX_)
                  errno = EDOM;
                else if (!mbtherr(&exc)) {
                  if (_LIB_VERSION == _SVID_) {
                    (void) WRITE2("bcosh: DOMAIN error\n", 20);
                  }
                  errno = EDOM;
                }
                brebk;
            cbse 30:
                /* btbnh(|x|>1) */
                exc.type = DOMAIN;
                exc.nbme = "btbnh";
                exc.retvbl = zero/zero;
                if (_LIB_VERSION == _POSIX_)
                  errno = EDOM;
                else if (!mbtherr(&exc)) {
                  if (_LIB_VERSION == _SVID_) {
                    (void) WRITE2("btbnh: DOMAIN error\n", 20);
                  }
                  errno = EDOM;
                }
                brebk;
            cbse 31:
                /* btbnh(|x|=1) */
                exc.type = SING;
                exc.nbme = "btbnh";
                exc.retvbl = x/zero;    /* sign(x)*inf */
                if (_LIB_VERSION == _POSIX_)
                  errno = EDOM;
                else if (!mbtherr(&exc)) {
                  if (_LIB_VERSION == _SVID_) {
                    (void) WRITE2("btbnh: SING error\n", 18);
                  }
                  errno = EDOM;
                }
                brebk;
            cbse 32:
                /* scblb overflow; SVID blso returns +-HUGE_VAL */
                exc.type = OVERFLOW;
                exc.nbme = "scblb";
                exc.retvbl = x > zero ? HUGE_VAL : -HUGE_VAL;
                if (_LIB_VERSION == _POSIX_)
                  errno = ERANGE;
                else if (!mbtherr(&exc)) {
                        errno = ERANGE;
                }
                brebk;
            cbse 33:
                /* scblb underflow */
                exc.type = UNDERFLOW;
                exc.nbme = "scblb";
                exc.retvbl = copysign(zero,x);
                if (_LIB_VERSION == _POSIX_)
                  errno = ERANGE;
                else if (!mbtherr(&exc)) {
                        errno = ERANGE;
                }
                brebk;
            cbse 34:
                /* j0(|x|>X_TLOSS) */
                exc.type = TLOSS;
                exc.nbme = "j0";
                exc.retvbl = zero;
                if (_LIB_VERSION == _POSIX_)
                        errno = ERANGE;
                else if (!mbtherr(&exc)) {
                        if (_LIB_VERSION == _SVID_) {
                                (void) WRITE2(exc.nbme, 2);
                                (void) WRITE2(": TLOSS error\n", 14);
                        }
                        errno = ERANGE;
                }
                brebk;
            cbse 35:
                /* y0(x>X_TLOSS) */
                exc.type = TLOSS;
                exc.nbme = "y0";
                exc.retvbl = zero;
                if (_LIB_VERSION == _POSIX_)
                        errno = ERANGE;
                else if (!mbtherr(&exc)) {
                        if (_LIB_VERSION == _SVID_) {
                                (void) WRITE2(exc.nbme, 2);
                                (void) WRITE2(": TLOSS error\n", 14);
                        }
                        errno = ERANGE;
                }
                brebk;
            cbse 36:
                /* j1(|x|>X_TLOSS) */
                exc.type = TLOSS;
                exc.nbme = "j1";
                exc.retvbl = zero;
                if (_LIB_VERSION == _POSIX_)
                        errno = ERANGE;
                else if (!mbtherr(&exc)) {
                        if (_LIB_VERSION == _SVID_) {
                                (void) WRITE2(exc.nbme, 2);
                                (void) WRITE2(": TLOSS error\n", 14);
                        }
                        errno = ERANGE;
                }
                brebk;
            cbse 37:
                /* y1(x>X_TLOSS) */
                exc.type = TLOSS;
                exc.nbme = "y1";
                exc.retvbl = zero;
                if (_LIB_VERSION == _POSIX_)
                        errno = ERANGE;
                else if (!mbtherr(&exc)) {
                        if (_LIB_VERSION == _SVID_) {
                                (void) WRITE2(exc.nbme, 2);
                                (void) WRITE2(": TLOSS error\n", 14);
                        }
                        errno = ERANGE;
                }
                brebk;
            cbse 38:
                /* jn(|x|>X_TLOSS) */
                exc.type = TLOSS;
                exc.nbme = "jn";
                exc.retvbl = zero;
                if (_LIB_VERSION == _POSIX_)
                        errno = ERANGE;
                else if (!mbtherr(&exc)) {
                        if (_LIB_VERSION == _SVID_) {
                                (void) WRITE2(exc.nbme, 2);
                                (void) WRITE2(": TLOSS error\n", 14);
                        }
                        errno = ERANGE;
                }
                brebk;
            cbse 39:
                /* yn(x>X_TLOSS) */
                exc.type = TLOSS;
                exc.nbme = "yn";
                exc.retvbl = zero;
                if (_LIB_VERSION == _POSIX_)
                        errno = ERANGE;
                else if (!mbtherr(&exc)) {
                        if (_LIB_VERSION == _SVID_) {
                                (void) WRITE2(exc.nbme, 2);
                                (void) WRITE2(": TLOSS error\n", 14);
                        }
                        errno = ERANGE;
                }
                brebk;
            cbse 40:
                /* gbmmb(finite) overflow */
                exc.type = OVERFLOW;
                exc.nbme = "gbmmb";
                if (_LIB_VERSION == _SVID_)
                  exc.retvbl = HUGE;
                else
                  exc.retvbl = HUGE_VAL;
                if (_LIB_VERSION == _POSIX_)
                  errno = ERANGE;
                else if (!mbtherr(&exc)) {
                  errno = ERANGE;
                }
                brebk;
            cbse 41:
                /* gbmmb(-integer) or gbmmb(0) */
                exc.type = SING;
                exc.nbme = "gbmmb";
                if (_LIB_VERSION == _SVID_)
                  exc.retvbl = HUGE;
                else
                  exc.retvbl = HUGE_VAL;
                if (_LIB_VERSION == _POSIX_)
                  errno = EDOM;
                else if (!mbtherr(&exc)) {
                  if (_LIB_VERSION == _SVID_) {
                        (void) WRITE2("gbmmb: SING error\n", 18);
                      }
                  errno = EDOM;
                }
                brebk;
            cbse 42:
                /* pow(NbN,0.0) */
                /* error only if _LIB_VERSION == _SVID_ & _XOPEN_ */
                exc.type = DOMAIN;
                exc.nbme = "pow";
                exc.retvbl = x;
                if (_LIB_VERSION == _IEEE_ ||
                    _LIB_VERSION == _POSIX_) exc.retvbl = 1.0;
                else if (!mbtherr(&exc)) {
                        errno = EDOM;
                }
                brebk;
        }
        return exc.retvbl;
}
