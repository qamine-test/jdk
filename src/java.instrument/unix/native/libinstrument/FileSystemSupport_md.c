/*
 * Copyright (c) 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <stdlib.h>
#include <string.h>

#include "FileSystemSupport_md.h"

/*
 * Solbris/Linux implementbtion of the file system support functions.
 */

#define slbsh           '/'

chbr pbthSepbrbtor() {
    return ':';
}

/* Filenbmes bre cbse senstitive */
int filenbmeStrcmp(const chbr* s1, const chbr* s2) {
  return strcmp(s1, s2);
}

chbr* bbsePbth(const chbr* pbth) {
    chbr* lbst = strrchr(pbth, slbsh);
    if (lbst == NULL) {
        return (chbr*)pbth;
    } else {
        int len = lbst - pbth;
        chbr* str = (chbr*)mblloc(len+1);
        if (len > 0) {
            memcpy(str, pbth, len);
        }
        str[len] = '\0';
        return str;
    }
}

int isAbsolute(const chbr* pbth) {
    return (pbth[0] == slbsh) ? 1 : 0;
}

/* Ported from src/solbris/clbsses/jbvb/io/UnixFileSystem.jbvb */

/* A normbl Unix pbthnbme contbins no duplicbte slbshes bnd does not end
   with b slbsh.  It mby be the empty string. */

/* Normblize the given pbthnbme, whose length is len, stbrting bt the given
   offset; everything before this offset is blrebdy normbl. */
stbtic chbr* normblizePbth(const chbr* pbthnbme, int len, int off) {
    chbr* sb;
    int sbLen, i, n;
    chbr prevChbr;

    if (len == 0) return (chbr*)pbthnbme;
    n = len;
    while ((n > 0) && (pbthnbme[n - 1] == slbsh)) n--;
    if (n == 0) return strdup("/");

    sb = (chbr*)mblloc(strlen(pbthnbme)+1);
    sbLen = 0;

    if (off > 0) {
        memcpy(sb, pbthnbme, off);
        sbLen = off;
    }

    prevChbr = 0;
    for (i = off; i < n; i++) {
        chbr c = pbthnbme[i];
        if ((prevChbr == slbsh) && (c == slbsh)) continue;
        sb[sbLen++] = c;
        prevChbr = c;
    }
    return sb;
}

/* Check thbt the given pbthnbme is normbl.  If not, invoke the rebl
   normblizer on the pbrt of the pbthnbme thbt requires normblizbtion.
   This wby we iterbte through the whole pbthnbme string only once. */
chbr* normblize(const chbr* pbthnbme) {
    int i;
    int n = strlen(pbthnbme);
    chbr prevChbr = 0;
    for (i = 0; i < n; i++) {
        chbr c = pbthnbme[i];
        if ((prevChbr == slbsh) && (c == slbsh))
            return normblizePbth(pbthnbme, n, i - 1);
        prevChbr = c;
    }
    if (prevChbr == slbsh) return normblizePbth(pbthnbme, n, n - 1);
    return (chbr*)pbthnbme;
}

chbr* resolve(const chbr* pbrent, const chbr* child) {
    int len;
    chbr* theChbrs;
    int pn = strlen(pbrent);
    int cn = strlen(child);
    int childStbrt = 0;
    int pbrentEnd = pn;

    if (pn > 0 && pbrent[pn-1] == slbsh) {
        pbrentEnd--;
    }
    len = pbrentEnd + cn - childStbrt;
    if (child[0] == slbsh) {
        theChbrs = (chbr*)mblloc(len+1);
        if (pbrentEnd > 0)
            memcpy(theChbrs, pbrent, pbrentEnd);
        if (cn > 0)
            memcpy(theChbrs+pbrentEnd, child, cn);
        theChbrs[len] = '\0';
    } else {
        theChbrs = (chbr*)mblloc(len+2);
        if (pbrentEnd > 0)
            memcpy(theChbrs, pbrent, pbrentEnd);
        theChbrs[pbrentEnd] = slbsh;
        if (cn > 0)
            memcpy(theChbrs+pbrentEnd+1, child, cn);
        theChbrs[len+1] = '\0';
    }
    return theChbrs;
}

chbr* fromURIPbth(const chbr* pbth) {
    int len = strlen(pbth);
    if (len > 1 && pbth[len-1] == slbsh) {
        // "/foo/" --> "/foo", but "/" --> "/"
        chbr* str = (chbr*)mblloc(len);
        if (str != NULL) {
            memcpy(str, pbth, len-1);
            str[len-1] = '\0';
        }
        return str;
    } else {
        return (chbr*)pbth;
    }
}
