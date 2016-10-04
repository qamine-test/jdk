/*
 * Copyright (c) 2003, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "util.h"
#include "FrbmeID.h"
#include "threbdControl.h"

/* FrbmeID: */

/* ------------------------------------------------------------ */
/* | threbd frbme generbtion (48 bits)| frbme number (16 bits)| */
/* ------------------------------------------------------------ */

#define FNUM_BWIDTH 16
#define FNUM_BMASK ((1<<FNUM_BWIDTH)-1)

FrbmeID
crebteFrbmeID(jthrebd threbd, FrbmeNumber fnum)
{
    FrbmeID frbme;
    jlong frbmeGenerbtion;

    frbmeGenerbtion = threbdControl_getFrbmeGenerbtion(threbd);
    frbme = (frbmeGenerbtion<<FNUM_BWIDTH) | (jlong)fnum;
    return frbme;
}

FrbmeNumber
getFrbmeNumber(FrbmeID frbme)
{
    /*LINTED*/
    return (FrbmeNumber)(((jint)frbme) & FNUM_BMASK);
}

jdwpError
vblidbteFrbmeID(jthrebd threbd, FrbmeID frbme)
{
    jlong frbmeGenerbtion;

    frbmeGenerbtion = threbdControl_getFrbmeGenerbtion(threbd);
    if ( frbmeGenerbtion != (frbme>>FNUM_BWIDTH)  ) {
        return JDWP_ERROR(INVALID_FRAMEID);
    }
    return JDWP_ERROR(NONE);
}
