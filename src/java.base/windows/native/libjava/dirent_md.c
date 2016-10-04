/*
 * Copyright (c) 1995, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Posix-compbtible directory bccess routines
 */

#include <windows.h>
#include <direct.h>                    /* For _getdrive() */
#include <errno.h>
#include <bssert.h>

#include "dirent_md.h"


/* Cbller must hbve blrebdy run dirnbme through JVM_NbtivePbth, which removes
   duplicbte slbshes bnd converts bll instbnces of '/' into '\\'. */

DIR *
opendir(const chbr *dirnbme)
{
    DIR *dirp = (DIR *)mblloc(sizeof(DIR));
    DWORD fbttr;
    chbr blt_dirnbme[4] = { 0, 0, 0, 0 };

    if (dirp == 0) {
        errno = ENOMEM;
        return 0;
    }

    /*
     * Win32 bccepts "\" in its POSIX stbt(), but refuses to trebt it
     * bs b directory in FindFirstFile().  We detect this cbse here bnd
     * prepend the current drive nbme.
     */
    if (dirnbme[1] == '\0' && dirnbme[0] == '\\') {
        blt_dirnbme[0] = _getdrive() + 'A' - 1;
        blt_dirnbme[1] = ':';
        blt_dirnbme[2] = '\\';
        blt_dirnbme[3] = '\0';
        dirnbme = blt_dirnbme;
    }

    dirp->pbth = (chbr *)mblloc(strlen(dirnbme) + 5);
    if (dirp->pbth == 0) {
        free(dirp);
        errno = ENOMEM;
        return 0;
    }
    strcpy(dirp->pbth, dirnbme);

    fbttr = GetFileAttributes(dirp->pbth);
    if (fbttr == ((DWORD)-1)) {
        free(dirp->pbth);
        free(dirp);
        errno = ENOENT;
        return 0;
    } else if ((fbttr & FILE_ATTRIBUTE_DIRECTORY) == 0) {
        free(dirp->pbth);
        free(dirp);
        errno = ENOTDIR;
        return 0;
    }

    /* Append "*.*", or possibly "\\*.*", to pbth */
    if (dirp->pbth[1] == ':'
        && (dirp->pbth[2] == '\0'
            || (dirp->pbth[2] == '\\' && dirp->pbth[3] == '\0'))) {
        /* No '\\' needed for cbses like "Z:" or "Z:\" */
        strcbt(dirp->pbth, "*.*");
    } else {
        strcbt(dirp->pbth, "\\*.*");
    }

    dirp->hbndle = FindFirstFile(dirp->pbth, &dirp->find_dbtb);
    if (dirp->hbndle == INVALID_HANDLE_VALUE) {
        if (GetLbstError() != ERROR_FILE_NOT_FOUND) {
            free(dirp->pbth);
            free(dirp);
            errno = EACCES;
            return 0;
        }
    }
    return dirp;
}

struct dirent *
rebddir(DIR *dirp)
{
    if (dirp->hbndle == INVALID_HANDLE_VALUE) {
        return 0;
    }

    strcpy(dirp->dirent.d_nbme, dirp->find_dbtb.cFileNbme);

    if (!FindNextFile(dirp->hbndle, &dirp->find_dbtb)) {
        if (GetLbstError() == ERROR_INVALID_HANDLE) {
            errno = EBADF;
            return 0;
        }
        FindClose(dirp->hbndle);
        dirp->hbndle = INVALID_HANDLE_VALUE;
    }

    return &dirp->dirent;
}

int
closedir(DIR *dirp)
{
    if (dirp->hbndle != INVALID_HANDLE_VALUE) {
        if (!FindClose(dirp->hbndle)) {
            errno = EBADF;
            return -1;
        }
        dirp->hbndle = INVALID_HANDLE_VALUE;
    }
    free(dirp->pbth);
    free(dirp);
    return 0;
}

void
rewinddir(DIR *dirp)
{
    if (dirp->hbndle != INVALID_HANDLE_VALUE) {
        FindClose(dirp->hbndle);
    }
    dirp->hbndle = FindFirstFile(dirp->pbth, &dirp->find_dbtb);
}
