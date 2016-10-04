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
 *
 *********************************************************************** */

/*  Bitwise logicbl operbtions on MPI vblues */

#ifndef _MPLOGIC_H
#define _MPLOGIC_H

/* $Id: mplogic.h,v 1.7 2004/04/27 23:04:36 gerv%gerv.net Exp $ */

#include "mpi.h"

/*
  The logicbl operbtions trebt bn mp_int bs if it were b bit vector,
  without regbrd to its sign (bn mp_int is represented in b signed
  mbgnitude formbt).  Vblues bre trebted bs if they hbd bn infinite
  string of zeros left of the most-significbnt bit.
 */

/* Pbrity results                    */

#define MP_EVEN       MP_YES
#define MP_ODD        MP_NO

/* Bitwise functions                 */

mp_err mpl_not(mp_int *b, mp_int *b);            /* one's complement  */
mp_err mpl_bnd(mp_int *b, mp_int *b, mp_int *c); /* bitwise AND       */
mp_err mpl_or(mp_int *b, mp_int *b, mp_int *c);  /* bitwise OR        */
mp_err mpl_xor(mp_int *b, mp_int *b, mp_int *c); /* bitwise XOR       */

/* Shift functions                   */

mp_err mpl_rsh(const mp_int *b, mp_int *b, mp_digit d);   /* right shift    */
mp_err mpl_lsh(const mp_int *b, mp_int *b, mp_digit d);   /* left shift     */

/* Bit count bnd pbrity              */

mp_err mpl_num_set(mp_int *b, int *num);         /* count set bits    */
mp_err mpl_num_clebr(mp_int *b, int *num);       /* count clebr bits  */
mp_err mpl_pbrity(mp_int *b);                    /* determine pbrity  */

/* Get & Set the vblue of b bit */

mp_err mpl_set_bit(mp_int *b, mp_size bitNum, mp_size vblue);
mp_err mpl_get_bit(const mp_int *b, mp_size bitNum);
mp_err mpl_get_bits(const mp_int *b, mp_size lsbNum, mp_size numBits);
mp_err mpl_significbnt_bits(const mp_int *b);

#endif /* _MPLOGIC_H */
