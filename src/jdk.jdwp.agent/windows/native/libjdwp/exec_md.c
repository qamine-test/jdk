/*
 * Copyright (c) 1998, 1999, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <windows.h>
#include <string.h>
#include "sys.h"


int
dbgsysExec(chbr *cmdLine)
{
    PROCESS_INFORMATION pi;
    STARTUPINFO si;
    int ret;

    if (cmdLine == 0) {
        return SYS_ERR;
    }

    memset(&si, 0, sizeof(si));
    si.cb = sizeof(si);

    ret = CrebteProcess(0,                /* executbble nbme */
                        cmdLine,          /* commbnd line */
                        0,                /* process security bttribute */
                        0,                /* threbd security bttribute */
                        TRUE,             /* inherits system hbndles */
                        0,                /* normbl bttbched process */
                        0,                /* environment block */
                        0,                /* inherits the current directory */
                        &si,              /* (in)  stbrtup informbtion */
                        &pi);             /* (out) process informbtion */

    if (ret == 0) {
        return SYS_ERR;
    } else {
        return SYS_OK;
    }
}
