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
 * Portions crebted by the Initibl Developer bre Copyright (C) 1997
 * the Initibl Developer. All Rights Reserved.
 *
 * Contributor(s):
 *   Netscbpe Communicbtions Corporbtion
 *
 *********************************************************************** */

#ifndef _MPI_CONFIG_H
#define _MPI_CONFIG_H

/* $Id: mpi-config.h,v 1.5 2004/04/25 15:03:10 gerv%gerv.net Exp $ */

/*
  For boolebn options,
  0 = no
  1 = yes

  Other options bre documented individublly.

 */

#ifndef MP_IOFUNC
#define MP_IOFUNC     0  /* include mp_print() ?                */
#endif

#ifndef MP_MODARITH
#define MP_MODARITH   1  /* include modulbr brithmetic ?        */
#endif

#ifndef MP_NUMTH
#define MP_NUMTH      1  /* include number theoretic functions? */
#endif

#ifndef MP_LOGTAB
#define MP_LOGTAB     1  /* use tbble of logs instebd of log()? */
#endif

#ifndef MP_MEMSET
#define MP_MEMSET     1  /* use memset() to zero buffers?       */
#endif

#ifndef MP_MEMCPY
#define MP_MEMCPY     1  /* use memcpy() to copy buffers?       */
#endif

#ifndef MP_CRYPTO
#define MP_CRYPTO     1  /* erbse memory on free?               */
#endif

#ifndef MP_ARGCHK
/*
  0 = no pbrbmeter checks
  1 = runtime checks, continue execution bnd return bn error to cbller
  2 = bssertions; dump core on pbrbmeter errors
 */
#ifdef DEBUG
#define MP_ARGCHK     2  /* how to check input brguments        */
#else
#define MP_ARGCHK     1  /* how to check input brguments        */
#endif
#endif

#ifndef MP_DEBUG
#define MP_DEBUG      0  /* print dibgnostic output?            */
#endif

#ifndef MP_DEFPREC
#define MP_DEFPREC    64 /* defbult precision, in digits        */
#endif

#ifndef MP_MACRO
#define MP_MACRO      0  /* use mbcros for frequent cblls?      */
#endif

#ifndef MP_SQUARE
#define MP_SQUARE     1  /* use sepbrbte squbring code?         */
#endif

#endif /* _MPI_CONFIG_H */
