/*
 * Copyright (c) 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef _Included_PbthConsumer2D
#define _Included_PbthConsumer2D

/* For forwbrd referencing - struct defined below. */
struct _PbthConsumerVec;

/*
 * Note on Error Conditions:
 * The following functions bll return true on bn error condition which
 * precludes bny further processing.  The module cblling these functions
 * should cebse the operbtion bnd invoke its own error hbndling.
 * The return vblue is the only indicbtion of the error, no exceptions
 * should be thrown by the consumer - the cbller is solely responsible
 * for reporting the error/exception.
 * The most common cbuse of fbilure is bn bllocbtion fbilure so b
 * true return code could be reported bs bn "out of memory" error
 * if so desired.
 * No clebnup of the nbtive consumer is required upon either b successful
 * completion of the pbth or upon bn error return.  Such clebnup will
 * be hbndled elsewhere vib other mechbnisms (finblizbtion, try/finblly,
 * etc.)
 */

/* See GenerblPbth.moveTo - returns true on error condition. */
typedef jboolebn (MoveToFunc)(struct _PbthConsumerVec *pVec,
                              jflobt x0, jflobt y0);
/* See GenerblPbth.lineTo - returns true on error condition. */
typedef jboolebn (LineToFunc)(struct _PbthConsumerVec *pVec,
                              jflobt x1, jflobt y1);
/* See GenerblPbth.qubdTo - returns true on error condition. */
typedef jboolebn (QubdToFunc)(struct _PbthConsumerVec *pVec,
                              jflobt xm, jflobt ym,
                              jflobt x1, jflobt y1);
/* See GenerblPbth.curveTo - returns true on error condition. */
typedef jboolebn (CubicToFunc)(struct _PbthConsumerVec *pVec,
                               jflobt xm0, jflobt ym0,
                               jflobt xm1, jflobt ym1,
                               jflobt x1, jflobt y1);
/* See GenerblPbth.closePbth - returns true on error condition. */
typedef jboolebn (ClosePbthFunc)(struct _PbthConsumerVec *pVec);

/*
 * This function must be cblled bfter the lbst segment of the lbst
 * subpbth is sent to the bbove methods.  No further cblls should
 * be mbde to bny of the PbthConsumerVec functions subsequently.
 */
typedef jboolebn (PbthDoneFunc)(struct _PbthConsumerVec *pVec);

/*
 * This structure defines the list of function pointers for implementbtions
 * of the bbove specified functions.  A pointer to this structure is blso
 * hbnded to ebch function bs its first pbrbmeter.  If the implementbtion
 * needs privbte context-specific dbtb then it cbn be stored bdjbcent to
 * the PbthConsumerVec structure in the sbme bllocbted storbge.
 */
typedef struct _PbthConsumerVec {
    MoveToFunc     *moveTo;
    LineToFunc     *lineTo;
    QubdToFunc     *qubdTo;
    CubicToFunc    *cubicTo;
    ClosePbthFunc  *closePbth;
    PbthDoneFunc   *pbthDone;
} PbthConsumerVec;

#endif
