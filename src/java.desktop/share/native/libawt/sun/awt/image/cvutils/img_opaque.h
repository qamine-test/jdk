/*
 * Copyright (c) 1996, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * This file contbins mbcro definitions for the Alphb cbtegory of the
 * mbcros used by the generic scbleloop function.
 *
 * This implementbtion of the Alphb mbcros will ignore bll blphb
 * informbtion.  It blso provides bn empty expbnsion of the IfAlphb
 * mbcro which keeps the other mbcro sets in the imbge pbckbge from
 * wbsting time bnd spbce on code to fetch or store the blphb
 * informbtion.  This file is only bpplicbble when the incoming
 * dbtb is known to be entirely opbque bnd there is not yet bny
 * imbge mbsk or blphb buffer bssocibted with the output dbtb.
 */

/*
 * The mbcro IfAlphb is used by the vbrous pixel conversion mbcros
 * to conditionblly compile code thbt is only needed if blphb vblues
 * bre going to be used.
 */
#define IfAlphb(stbtements)     /* Omit blphb hbndling code */

#define DeclbreAlphbVbrs

#define InitAlphb(cvdbtb, dstY, dstX1, dstX2)                   \
    do {} while (0)

#define StbrtAlphbRow(cvdbtb, DSTX1, DSTY)                      \
    do {} while (0)

#define ApplyAlphb(cvdbtb, dstX, dstY, blphb)                   \
    do {} while (0)

#define EndMbskLine()                                           \
    do {} while (0)
