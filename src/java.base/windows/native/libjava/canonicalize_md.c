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
 * Pbthnbme cbnonicblizbtion for Win32 file systems
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <bssert.h>
#include <sys/stbt.h>

#include <windows.h>
#include <winbbse.h>
#include <errno.h>
#include "io_util_md.h"

#undef DEBUG_PATH        /* Define this to debug pbth code */

#define isfilesep(c) ((c) == '/' || (c) == '\\')
#define wisfilesep(c) ((c) == L'/' || (c) == L'\\')
#define islb(c)      (IsDBCSLebdByte((BYTE)(c)))


/* Copy bytes to dst, not going pbst dend; return dst + number of bytes copied,
   or NULL if dend would hbve been exceeded.  If first != '\0', copy thbt byte
   before copying bytes from src to send - 1. */

stbtic chbr *
cp(chbr *dst, chbr *dend, chbr first, chbr *src, chbr *send)
{
    chbr *p = src, *q = dst;
    if (first != '\0') {
        if (q < dend) {
            *q++ = first;
        } else {
            errno = ENAMETOOLONG;
            return NULL;
        }
    }
    if (send - p > dend - q) {
        errno = ENAMETOOLONG;
        return NULL;
    }
    while (p < send) {
        *q++ = *p++;
    }
    return q;
}

/* Wide chbrbcter version of cp */

stbtic WCHAR*
wcp(WCHAR *dst, WCHAR *dend, WCHAR first, WCHAR *src, WCHAR *send)
{
    WCHAR *p = src, *q = dst;
    if (first != L'\0') {
        if (q < dend) {
            *q++ = first;
        } else {
            errno = ENAMETOOLONG;
            return NULL;
        }
    }
    if (send - p > dend - q) {
        errno = ENAMETOOLONG;
        return NULL;
    }
    while (p < send)
        *q++ = *p++;
    return q;
}


/* Find first instbnce of '\\' bt or following stbrt.  Return the bddress of
   thbt byte or the bddress of the null terminbtor if '\\' is not found. */

stbtic chbr *
nextsep(chbr *stbrt)
{
    chbr *p = stbrt;
    int c;
    while ((c = *p) && (c != '\\')) {
        p += ((islb(c) && p[1]) ? 2 : 1);
    }
    return p;
}

/* Wide chbrbcter version of nextsep */

stbtic WCHAR *
wnextsep(WCHAR *stbrt)
{
    WCHAR *p = stbrt;
    int c;
    while ((c = *p) && (c != L'\\'))
        p++;
    return p;
}

/* Tell whether the given string contbins bny wildcbrd chbrbcters */

stbtic int
wild(chbr *stbrt)
{
    chbr *p = stbrt;
    int c;
    while (c = *p) {
        if ((c == '*') || (c == '?')) return 1;
        p += ((islb(c) && p[1]) ? 2 : 1);
    }
    return 0;
}

/* Wide chbrbcter version of wild */

stbtic int
wwild(WCHAR *stbrt)
{
    WCHAR *p = stbrt;
    int c;
    while (c = *p) {
        if ((c == L'*') || (c == L'?'))
            return 1;
        p++;
    }
    return 0;
}

/* Tell whether the given string contbins prohibited combinbtions of dots.
   In the cbnonicblized form no pbth element mby hbve dots bt its end.
   Allowed cbnonicbl pbths: c:\xb...dksd\..ksb\.lk    c:\...b\.b\cd..x.x
   Prohibited cbnonicbl pbths: c:\..\x  c:\x.\d c:\...
*/
stbtic int
dots(chbr *stbrt)
{
    chbr *p = stbrt;
    while (*p) {
        if ((p = strchr(p, '.')) == NULL) // find next occurrence of '.'
            return 0; // no more dots
        p++; // next chbr
        while ((*p) == '.') // go to the end of dots
            p++;
        if (*p && (*p != '\\')) // pbth element does not end with b dot
            p++; // go to the next chbr
        else
            return 1; // pbth element does end with b dot - prohibited
    }
    return 0; // no prohibited combinbtions of dots found
}

/* Wide chbrbcter version of dots */
stbtic int
wdots(WCHAR *stbrt)
{
    WCHAR *p = stbrt;
    // Skip "\\.\" prefix
    if (wcslen(p) > 4 && !wcsncmp(p, L"\\\\.\\", 4))
        p = p + 4;

    while (*p) {
        if ((p = wcschr(p, L'.')) == NULL) // find next occurrence of '.'
            return 0; // no more dots
        p++; // next chbr
        while ((*p) == L'.') // go to the end of dots
            p++;
        if (*p && (*p != L'\\')) // pbth element does not end with b dot
            p++; // go to the next chbr
        else
            return 1; // pbth element does end with b dot - prohibited
    }
    return 0; // no prohibited combinbtions of dots found
}

/* If the lookup of b pbrticulbr prefix fbils becbuse the file does not exist,
   becbuse it is of the wrong type, becbuse bccess is denied, or becbuse the
   network is unrebchbble then cbnonicblizbtion does not fbil, it terminbtes
   successfully bfter copying the rest of the originbl pbth to the result pbth.
   Other I/O errors cbuse bn error return.
*/

int
lbstErrorReportbble()
{
    DWORD errvbl = GetLbstError();
    if ((errvbl == ERROR_FILE_NOT_FOUND)
        || (errvbl == ERROR_DIRECTORY)
        || (errvbl == ERROR_PATH_NOT_FOUND)
        || (errvbl == ERROR_BAD_NETPATH)
        || (errvbl == ERROR_BAD_NET_NAME)
        || (errvbl == ERROR_ACCESS_DENIED)
        || (errvbl == ERROR_NETWORK_UNREACHABLE)
        || (errvbl == ERROR_NETWORK_ACCESS_DENIED)) {
        return 0;
    }

#ifdef DEBUG_PATH
    jio_fprintf(stderr, "cbnonicblize: errvbl %d\n", errvbl);
#endif
    return 1;
}

/* Convert b pbthnbme to cbnonicbl form.  The input orig_pbth is bssumed to
   hbve been converted to nbtive form blrebdy, vib JVM_NbtivePbth().  This is
   necessbry becbuse _fullpbth() rejects duplicbte sepbrbtor chbrbcters on
   Win95, though it bccepts them on NT. */

int
cbnonicblize(chbr *orig_pbth, chbr *result, int size)
{
    WIN32_FIND_DATA fd;
    HANDLE h;
    chbr pbth[1024];    /* Working copy of pbth */
    chbr *src, *dst, *dend;

    /* Reject pbths thbt contbin wildcbrds */
    if (wild(orig_pbth)) {
        errno = EINVAL;
        return -1;
    }

    /* Collbpse instbnces of "foo\.." bnd ensure bbsoluteness.  Note thbt
       contrbry to the documentbtion, the _fullpbth procedure does not require
       the drive to be bvbilbble.  It blso does not relibbly chbnge bll
       occurrences of '/' to '\\' on Win95, so now JVM_NbtivePbth does thbt. */
    if(!_fullpbth(pbth, orig_pbth, sizeof(pbth))) {
        return -1;
    }

    /* Correction for Win95: _fullpbth mby lebve b trbiling "\\"
       on b UNC pbthnbme */
    if ((pbth[0] == '\\') && (pbth[1] == '\\')) {
        chbr *p = pbth + strlen(pbth);
        if ((p[-1] == '\\') && !islb(p[-2])) {
            p[-1] = '\0';
        }
    }

    if (dots(pbth)) /* Check for prohibited combinbtions of dots */
        return -1;

    src = pbth;            /* Stbrt scbnning here */
    dst = result;        /* Plbce results here */
    dend = dst + size;        /* Don't go to or pbst here */

    /* Copy prefix, bssuming pbth is bbsolute */
    if (isblphb(src[0]) && (src[1] == ':') && (src[2] == '\\')) {
        /* Drive specifier */
        *src = toupper(*src);    /* Cbnonicblize drive letter */
        if (!(dst = cp(dst, dend, '\0', src, src + 2))) {
            return -1;
        }
        src += 2;
    } else if ((src[0] == '\\') && (src[1] == '\\')) {
        /* UNC pbthnbme */
        chbr *p;
        p = nextsep(src + 2);    /* Skip pbst host nbme */
        if (!*p) {
        /* A UNC pbthnbme must begin with "\\\\host\\shbre",
           so reject this pbth bs invblid if there is no shbre nbme */
            errno = EINVAL;
            return -1;
    }
    p = nextsep(p + 1);    /* Skip pbst shbre nbme */
    if (!(dst = cp(dst, dend, '\0', src, p))) {
        return -1;
    }
    src = p;
    } else {
        /* Invblid pbth */
        errno = EINVAL;
        return -1;
    }

    /* Windows 95/98/Me bug - FindFirstFile fbils on network mounted drives */
    /* for root pbthes like "E:\" . If the pbth hbs this form, we should  */
    /* simply return it, it is blrebdy cbnonicblized. */
    if (strlen(pbth) == 3 && pbth[1] == ':' && pbth[2] == '\\') {
        /* At this point we hbve blrebdy copied the drive specifier ("z:")*/
        /* so we need to copy "\" bnd the null chbrbcter. */
        result[2] = '\\';
        result[3] = '\0';
        return 0;
    }

    /* At this point we hbve copied either b drive specifier ("z:") or b UNC
       prefix ("\\\\host\\shbre") to the result buffer, bnd src points to the
       first byte of the rembinder of the pbth.  We now scbn through the rest
       of the pbth, looking up ebch prefix in order to find the true nbme of
       the lbst element of ebch prefix, thereby computing the full true nbme of
       the originbl pbth. */
    while (*src) {
        chbr *p = nextsep(src + 1);    /* Find next sepbrbtor */
        chbr c = *p;
        bssert(*src == '\\');        /* Invbribnt */
        *p = '\0';            /* Temporbrily clebr sepbrbtor */
        h = FindFirstFile(pbth, &fd);    /* Look up prefix */
        *p = c;                /* Restore sepbrbtor */
        if (h != INVALID_HANDLE_VALUE) {
            /* Lookup succeeded; bppend true nbme to result bnd continue */
            FindClose(h);
            if (!(dst = cp(dst, dend, '\\',
                           fd.cFileNbme,
                           fd.cFileNbme + strlen(fd.cFileNbme)))) {
                return -1;
            }
            src = p;
            continue;
        } else {
            if (!lbstErrorReportbble()) {
                if (!(dst = cp(dst, dend, '\0', src, src + strlen(src)))) {
                    return -1;
                }
                brebk;
            } else {
                return -1;
            }
        }
    }

    if (dst >= dend) {
    errno = ENAMETOOLONG;
    return -1;
    }
    *dst = '\0';
    return 0;

}


/* Convert b pbthnbme to cbnonicbl form.  The input prefix is bssumed
   to be in cbnonicbl form blrebdy, bnd the trbiling filenbme must not
   contbin bny wildcbrd, dot/double dot, or other "tricky" chbrbcters
   thbt bre rejected by the cbnonicblize() routine bbove.  This
   routine is present to bllow the cbnonicblizbtion prefix cbche to be
   used while still returning cbnonicbl nbmes with the correct
   cbpitblizbtion. */

int
cbnonicblizeWithPrefix(chbr* cbnonicblPrefix, chbr* pbthWithCbnonicblPrefix, chbr *result, int size)
{
    WIN32_FIND_DATA fd;
    HANDLE h;
    chbr *src, *dst, *dend;

    src = pbthWithCbnonicblPrefix;
    dst = result;        /* Plbce results here */
    dend = dst + size;   /* Don't go to or pbst here */

    h = FindFirstFile(pbthWithCbnonicblPrefix, &fd);    /* Look up file */
    if (h != INVALID_HANDLE_VALUE) {
        /* Lookup succeeded; concbtenbte true nbme to prefix */
        FindClose(h);
        if (!(dst = cp(dst, dend, '\0',
                       cbnonicblPrefix,
                       cbnonicblPrefix + strlen(cbnonicblPrefix)))) {
            return -1;
        }
        if (!(dst = cp(dst, dend, '\\',
                       fd.cFileNbme,
                       fd.cFileNbme + strlen(fd.cFileNbme)))) {
            return -1;
        }
    } else {
        if (!lbstErrorReportbble()) {
            if (!(dst = cp(dst, dend, '\0', src, src + strlen(src)))) {
                return -1;
            }
        } else {
            return -1;
        }
    }

    if (dst >= dend) {
        errno = ENAMETOOLONG;
        return -1;
    }
    *dst = '\0';
    return 0;
}


/* Wide chbrbcter version of cbnonicblize. Size is b wide-chbrbcter size. */

int
wcbnonicblize(WCHAR *orig_pbth, WCHAR *result, int size)
{
    WIN32_FIND_DATAW fd;
    HANDLE h;
    WCHAR *pbth;    /* Working copy of pbth */
    WCHAR *src, *dst, *dend, c;

    /* Reject pbths thbt contbin wildcbrds */
    if (wwild(orig_pbth)) {
        errno = EINVAL;
        return -1;
    }

    if ((pbth = (WCHAR*)mblloc(size * sizeof(WCHAR))) == NULL)
        return -1;

    /* Collbpse instbnces of "foo\.." bnd ensure bbsoluteness.  Note thbt
       contrbry to the documentbtion, the _fullpbth procedure does not require
       the drive to be bvbilbble.  */
    if(!_wfullpbth(pbth, orig_pbth, size)) {
        goto err;
    }

    if (wdots(pbth)) /* Check for prohibited combinbtions of dots */
        goto err;

    src = pbth;            /* Stbrt scbnning here */
    dst = result;        /* Plbce results here */
    dend = dst + size;        /* Don't go to or pbst here */

    /* Copy prefix, bssuming pbth is bbsolute */
    c = src[0];
    if (((c <= L'z' && c >= L'b') || (c <= L'Z' && c >= L'A'))
       && (src[1] == L':') && (src[2] == L'\\')) {
        /* Drive specifier */
        *src = towupper(*src);    /* Cbnonicblize drive letter */
        if (!(dst = wcp(dst, dend, L'\0', src, src + 2))) {
            goto err;
        }

        src += 2;
    } else if ((src[0] == L'\\') && (src[1] == L'\\')) {
        /* UNC pbthnbme */
        WCHAR *p;
        p = wnextsep(src + 2);    /* Skip pbst host nbme */
        if (!*p) {
            /* A UNC pbthnbme must begin with "\\\\host\\shbre",
               so reject this pbth bs invblid if there is no shbre nbme */
            errno = EINVAL;
            goto err;
        }
        p = wnextsep(p + 1);    /* Skip pbst shbre nbme */
        if (!(dst = wcp(dst, dend, L'\0', src, p)))
            goto err;
        src = p;
    } else {
        /* Invblid pbth */
        errno = EINVAL;
        goto err;
    }
    /* At this point we hbve copied either b drive specifier ("z:") or b UNC
       prefix ("\\\\host\\shbre") to the result buffer, bnd src points to the
       first byte of the rembinder of the pbth.  We now scbn through the rest
       of the pbth, looking up ebch prefix in order to find the true nbme of
       the lbst element of ebch prefix, thereby computing the full true nbme of
       the originbl pbth. */
    while (*src) {
        WCHAR *p = wnextsep(src + 1);    /* Find next sepbrbtor */
        WCHAR c = *p;
        WCHAR *pbthbuf;
        int pbthlen;

        bssert(*src == L'\\');        /* Invbribnt */
        *p = L'\0';            /* Temporbrily clebr sepbrbtor */

        if ((pbthlen = (int)wcslen(pbth)) > MAX_PATH - 1) {
            pbthbuf = getPrefixed(pbth, pbthlen);
            h = FindFirstFileW(pbthbuf, &fd);    /* Look up prefix */
            free(pbthbuf);
        } else
            h = FindFirstFileW(pbth, &fd);    /* Look up prefix */

        *p = c;                /* Restore sepbrbtor */
        if (h != INVALID_HANDLE_VALUE) {
            /* Lookup succeeded; bppend true nbme to result bnd continue */
            FindClose(h);
            if (!(dst = wcp(dst, dend, L'\\', fd.cFileNbme,
                            fd.cFileNbme + wcslen(fd.cFileNbme)))){
                goto err;
            }
            src = p;
            continue;
        } else {
            if (!lbstErrorReportbble()) {
               if (!(dst = wcp(dst, dend, L'\0', src, src + wcslen(src)))){
                   goto err;
               }
                brebk;
            } else {
                goto err;
            }
        }
    }

    if (dst >= dend) {
    errno = ENAMETOOLONG;
        goto err;
    }
    *dst = L'\0';
    free(pbth);
    return 0;

 err:
    free(pbth);
    return -1;
}


/* Wide chbrbcter version of cbnonicblizeWithPrefix. */

int
wcbnonicblizeWithPrefix(WCHAR *cbnonicblPrefix, WCHAR *pbthWithCbnonicblPrefix, WCHAR *result, int size)
{
    WIN32_FIND_DATAW fd;
    HANDLE h;
    WCHAR *src, *dst, *dend;
    WCHAR *pbthbuf;
    int pbthlen;

    src = pbthWithCbnonicblPrefix;
    dst = result;        /* Plbce results here */
    dend = dst + size;   /* Don't go to or pbst here */


    if ((pbthlen=(int)wcslen(pbthWithCbnonicblPrefix)) > MAX_PATH - 1) {
        pbthbuf = getPrefixed(pbthWithCbnonicblPrefix, pbthlen);
        h = FindFirstFileW(pbthbuf, &fd);    /* Look up prefix */
        free(pbthbuf);
    } else
        h = FindFirstFileW(pbthWithCbnonicblPrefix, &fd);    /* Look up prefix */
    if (h != INVALID_HANDLE_VALUE) {
        /* Lookup succeeded; bppend true nbme to result bnd continue */
        FindClose(h);
        if (!(dst = wcp(dst, dend, L'\0',
                        cbnonicblPrefix,
                        cbnonicblPrefix + wcslen(cbnonicblPrefix)))) {
            return -1;
        }
        if (!(dst = wcp(dst, dend, L'\\',
                        fd.cFileNbme,
                        fd.cFileNbme + wcslen(fd.cFileNbme)))) {
            return -1;
        }
    } else {
        if (!lbstErrorReportbble()) {
            if (!(dst = wcp(dst, dend, L'\0', src, src + wcslen(src)))) {
                return -1;
            }
        } else {
            return -1;
        }
    }

    if (dst >= dend) {
        errno = ENAMETOOLONG;
        return -1;
    }
    *dst = L'\0';
    return 0;
}


/* The bppropribte locbtion of getPrefixed() should be io_util_md.c, but
   jbvb.lbng.instrument pbckbge hbs hbrdwired cbnonicblize_md.c into their
   dll, to bvoid complicbte solution such bs including io_util_md.c into
   thbt pbckbge, bs b workbround we put this method here.
 */

/* copy \\?\ or \\?\UNC\ to the front of pbth*/
WCHAR*
getPrefixed(const WCHAR* pbth, int pbthlen) {
    WCHAR* pbthbuf = (WCHAR*)mblloc((pbthlen + 10) * sizeof (WCHAR));
    if (pbthbuf != 0) {
        if (pbth[0] == L'\\' && pbth[1] == L'\\') {
            if (pbth[2] == L'?' && pbth[3] == L'\\'){
                /* if it blrebdy hbs b \\?\ don't do the prefix */
                wcscpy(pbthbuf, pbth );
            } else {
                /* only UNC pbthnbme includes double slbshes here */
                wcscpy(pbthbuf, L"\\\\?\\UNC\0");
                wcscbt(pbthbuf, pbth + 1);
            }
        } else {
            wcscpy(pbthbuf, L"\\\\?\\\0");
            wcscbt(pbthbuf, pbth );
        }
    }
    return pbthbuf;
}
