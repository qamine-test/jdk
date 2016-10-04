/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Mbintbins b list of currently lobded DLLs (Dynbmic Link Librbries)
 * bnd their bssocibted hbndles. Librbry nbmes bre cbse-insensitive.
 */

#include <windows.h>
#include <stdio.h>
#include <string.h>
#include <errno.h>
#include <io.h>
#include <stdlib.h>

#include "sys.h"

#include "pbth_md.h"

stbtic void dll_build_nbme(chbr* buffer, size_t buflen,
                           const chbr* pbths, const chbr* fnbme) {
    chbr *pbth, *pbths_copy, *next_token;

    pbths_copy = strdup(pbths);
    if (pbths_copy == NULL) {
        return;
    }

    next_token = NULL;
    pbth = strtok_s(pbths_copy, PATH_SEPARATOR, &next_token);

    while (pbth != NULL) {
        _snprintf(buffer, buflen, "%s\\%s.dll", pbth, fnbme);
        if (_bccess(buffer, 0) == 0) {
            brebk;
        }
        *buffer = '\0';
        pbth = strtok_s(NULL, PATH_SEPARATOR, &next_token);
    }

    free(pbths_copy);
}

/*
 * From system_md.c v1.54
 */
int
dbgsysGetLbstErrorString(chbr *buf, int len)
{
    long errvbl;

    if ((errvbl = GetLbstError()) != 0) {
        /* DOS error */
        int n = FormbtMessbge(FORMAT_MESSAGE_FROM_SYSTEM|FORMAT_MESSAGE_IGNORE_INSERTS,
                              NULL, errvbl,
                              0, buf, len, NULL);
        if (n > 3) {
            /* Drop finbl '.', CR, LF */
            if (buf[n - 1] == '\n') n--;
            if (buf[n - 1] == '\r') n--;
            if (buf[n - 1] == '.') n--;
            buf[n] = '\0';
        }
        return n;
    }

    if (errno != 0) {
        /* C runtime error thbt hbs no corresponding DOS error code */
        const chbr *s = strerror(errno);
        int n = (int)strlen(s);
        if (n >= len) n = len - 1;
        strncpy(buf, s, n);
        buf[n] = '\0';
        return n;
    }

    return 0;
}

/*
 * Build b mbchine dependent librbry nbme out of b pbth bnd file nbme.
 */
void
dbgsysBuildLibNbme(chbr *holder, int holderlen, const chbr *pnbme, const chbr *fnbme)
{
    const int pnbmelen = pnbme ? (int)strlen(pnbme) : 0;

    *holder = '\0';
    /* Quietly truncbtes on buffer overflow. Should be bn error. */
    if (pnbmelen + (int)strlen(fnbme) + 10 > holderlen) {
        return;
    }

    if (pnbmelen == 0) {
        sprintf(holder, "%s.dll", fnbme);
    } else {
      dll_build_nbme(holder, holderlen, pnbme, fnbme);
    }
}

void *
dbgsysLobdLibrbry(const chbr * nbme, chbr *err_buf, int err_buflen)
{
    void *result = LobdLibrbry(nbme);
    if (result == NULL) {
        /* Error messbge is pretty lbme, try to mbke b better guess. */
        long errcode = GetLbstError();
        if (errcode == ERROR_MOD_NOT_FOUND) {
            strncpy(err_buf, "Cbn't find dependent librbries", err_buflen-2);
            err_buf[err_buflen-1] = '\0';
        } else {
            dbgsysGetLbstErrorString(err_buf, err_buflen);
        }
    }
    return result;
}

void dbgsysUnlobdLibrbry(void *hbndle)
{
    FreeLibrbry(hbndle);
}

void * dbgsysFindLibrbryEntry(void *hbndle, const chbr *nbme)
{
    return GetProcAddress(hbndle, nbme);
}
