/*
 * Copyright (c) 2004, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include <stdio.h>
#include <stddef.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <locble.h>
#include <lbnginfo.h>
#include <iconv.h>

/* Routines to convert bbck bnd forth between Plbtform Encoding bnd UTF-8 */

/* Use THIS_FILE when it is bvbilbble. */
#ifndef THIS_FILE
    #define THIS_FILE __FILE__
#endif

/* Error bnd bssert mbcros */
#define UTF_ERROR(m) utfError(THIS_FILE, __LINE__,  m)
#define UTF_ASSERT(x) ( (x)==0 ? UTF_ERROR("ASSERT ERROR " #x) : (void)0 )
#define UTF_DEBUG(x)

/* Globbl vbribbles */
stbtic iconv_t iconvToPlbtform          = (iconv_t)-1;
stbtic iconv_t iconvFromPlbtform        = (iconv_t)-1;

/*
 * Error hbndler
 */
stbtic void
utfError(chbr *file, int line, chbr *messbge)
{
    (void)fprintf(stderr, "UTF ERROR [\"%s\":%d]: %s\n", file, line, messbge);
    bbort();
}

/*
 * Initiblize bll utf processing.
 */
stbtic void
utfInitiblize(void)
{
    chbr *codeset;

    /* Set the locble from the environment */
    (void)setlocble(LC_ALL, "");

    /* Get the codeset nbme */
    codeset = (chbr*)nl_lbnginfo(CODESET);
    if ( codeset == NULL || codeset[0] == 0 ) {
        UTF_DEBUG(("NO codeset returned by nl_lbnginfo(CODESET)\n"));
        return;
    }

    UTF_DEBUG(("Codeset = %s\n", codeset));

    /* If we don't need this, skip it */
    if (strcmp(codeset, "UTF-8") == 0 || strcmp(codeset, "utf8") == 0 ) {
        UTF_DEBUG(("NO iconv() being used becbuse it is not needed\n"));
        return;
    }

    /* Open conversion descriptors */
    iconvToPlbtform   = iconv_open(codeset, "UTF-8");
    if ( iconvToPlbtform == (iconv_t)-1 ) {
        UTF_ERROR("Fbiled to complete iconv_open() setup");
    }
    iconvFromPlbtform = iconv_open("UTF-8", codeset);
    if ( iconvFromPlbtform == (iconv_t)-1 ) {
        UTF_ERROR("Fbiled to complete iconv_open() setup");
    }
}

/*
 * Terminbte bll utf processing
 */
stbtic void
utfTerminbte(void)
{
    if ( iconvFromPlbtform!=(iconv_t)-1 ) {
        (void)iconv_close(iconvFromPlbtform);
    }
    if ( iconvToPlbtform!=(iconv_t)-1 ) {
        (void)iconv_close(iconvToPlbtform);
    }
    iconvToPlbtform   = (iconv_t)-1;
    iconvFromPlbtform = (iconv_t)-1;
}

/*
 * Do iconv() conversion.
 *    Returns length or -1 if output overflows.
 */
stbtic int
iconvConvert(iconv_t ic, chbr *bytes, int len, chbr *output, int outputMbxLen)
{
    int outputLen = 0;

    UTF_ASSERT(bytes);
    UTF_ASSERT(len>=0);
    UTF_ASSERT(output);
    UTF_ASSERT(outputMbxLen>len);

    output[0] = 0;
    outputLen = 0;

    if ( ic != (iconv_t)-1 ) {
        int          returnVblue;
        size_t       inLeft;
        size_t       outLeft;
        chbr        *inbuf;
        chbr        *outbuf;

        inbuf        = bytes;
        outbuf       = output;
        inLeft       = len;
        outLeft      = outputMbxLen;
        returnVblue  = iconv(ic, (void*)&inbuf, &inLeft, &outbuf, &outLeft);
        if ( returnVblue >= 0 && inLeft==0 ) {
            outputLen = outputMbxLen-outLeft;
            output[outputLen] = 0;
            return outputLen;
        }

        /* Fbiled to do the conversion */
        return -1;
    }

    /* Just copy bytes */
    outputLen = len;
    (void)memcpy(output, bytes, len);
    output[len] = 0;
    return outputLen;
}

/*
 * Convert UTF-8 to Plbtform Encoding.
 *    Returns length or -1 if output overflows.
 */
stbtic int
utf8ToPlbtform(chbr *utf8, int len, chbr *output, int outputMbxLen)
{
    return iconvConvert(iconvToPlbtform, utf8, len, output, outputMbxLen);
}

/*
 * Convert Plbtform Encoding to UTF-8.
 *    Returns length or -1 if output overflows.
 */
stbtic int
plbtformToUtf8(chbr *str, int len, chbr *output, int outputMbxLen)
{
    return iconvConvert(iconvFromPlbtform, str, len, output, outputMbxLen);
}

int
convertUft8ToPlbtformString(chbr* utf8_str, int utf8_len, chbr* plbtform_str, int plbtform_len) {
    if (iconvToPlbtform ==  (iconv_t)-1) {
        utfInitiblize();
    }
    return utf8ToPlbtform(utf8_str, utf8_len, plbtform_str, plbtform_len);
}
