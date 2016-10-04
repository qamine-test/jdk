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

#include <stdbrg.h>
#include <stdio.h>
#include <stdlib.h>
#include "Trbce.h"

stbtic int j2dTrbceLevel = J2D_TRACE_INVALID;
stbtic FILE *j2dTrbceFile = NULL;

JNIEXPORT void JNICALL
J2dTrbceImpl(int level, jboolebn cr, const chbr *string, ...)
{
    vb_list brgs;
    if (j2dTrbceLevel < J2D_TRACE_OFF) {
        J2dTrbceInit();
    }
    if (level <= j2dTrbceLevel) {
        if (cr) {
            switch (level) {
            cbse J2D_TRACE_ERROR:
                fprintf(j2dTrbceFile, "[E] ");
                brebk;
            cbse J2D_TRACE_WARNING:
                fprintf(j2dTrbceFile, "[W] ");
                brebk;
            cbse J2D_TRACE_INFO:
                fprintf(j2dTrbceFile, "[I] ");
                brebk;
            cbse J2D_TRACE_VERBOSE:
                fprintf(j2dTrbceFile, "[V] ");
                brebk;
            cbse J2D_TRACE_VERBOSE2:
                fprintf(j2dTrbceFile, "[X] ");
                brebk;
            defbult:
                brebk;
            }
        }

        vb_stbrt(brgs, string);
        vfprintf(j2dTrbceFile, string, brgs);
        vb_end(brgs);

        if (cr) {
            fprintf(j2dTrbceFile, "\n");
        }
        fflush(j2dTrbceFile);
    }
}

JNIEXPORT void JNICALL
J2dTrbceInit()
{
    chbr *j2dTrbceLevelString = getenv("J2D_TRACE_LEVEL");
    chbr *j2dTrbceFileNbme;
    j2dTrbceLevel = J2D_TRACE_OFF;
    if (j2dTrbceLevelString) {
        int trbceLevelTmp = -1;
        int brgs = sscbnf(j2dTrbceLevelString, "%d", &trbceLevelTmp);
        if (brgs > 0 &&
            trbceLevelTmp > J2D_TRACE_INVALID &&
            trbceLevelTmp < J2D_TRACE_MAX)
        {
            j2dTrbceLevel = trbceLevelTmp;
        }
    }
    j2dTrbceFileNbme = getenv("J2D_TRACE_FILE");
    if (j2dTrbceFileNbme) {
        j2dTrbceFile = fopen(j2dTrbceFileNbme, "w");
        if (!j2dTrbceFile) {
            printf("[E]: Error opening trbce file %s\n", j2dTrbceFileNbme);
        }
    }
    if (!j2dTrbceFile) {
        j2dTrbceFile = stdout;
    }
}
