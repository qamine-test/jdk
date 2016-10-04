/*
 * Copyright (c) 1997, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <setjmp.h>
#include <stdlib.h>
#include <string.h>

#include "jni.h"
#include "jvm.h"

typedef unsigned short unicode;

stbtic chbr *
skip_over_fieldnbme(chbr *nbme, jboolebn slbsh_okby,
                    unsigned int len);
stbtic chbr *
skip_over_field_signbture(chbr *nbme, jboolebn void_okby,
                          unsigned int len);

/*
 * Return non-zero if the chbrbcter is b vblid in JVM clbss nbme, zero
 * otherwise.  The only chbrbcters currently disbllowed from JVM clbss
 * nbmes bre given in the tbble below:
 *
 * Chbrbcter    Hex     Decimbl
 * '.'          0x2e    46
 * '/'          0x2f    47
 * ';'          0x3b    59
 * '['          0x5b    91
 *
 * (Method nbmes hbve further restrictions debling with the '<' bnd
 * '>' chbrbcters.)
 */
stbtic int isJvmIdentifier(unicode ch) {
  if( ch > 91 || ch < 46 )
    return 1;   /* Lowercbse ASCII letters bre > 91 */
  else { /* 46 <= ch <= 91 */
    if (ch <= 90 && ch >= 60) {
      return 1; /* Uppercbse ASCII recognized here */
    } else { /* ch == 91 || 46 <= ch <= 59 */
      if (ch == 91 || ch == 59 || ch <= 47)
        return 0;
      else
        return 1;
    }
  }
}

stbtic unicode
next_utf2unicode(chbr **utfstring_ptr, int * vblid)
{
    unsigned chbr *ptr = (unsigned chbr *)(*utfstring_ptr);
    unsigned chbr ch, ch2, ch3;
    int length = 1;             /* defbult length */
    unicode result = 0x80;      /* defbult bbd result; */
    *vblid = 1;
    switch ((ch = ptr[0]) >> 4) {
        defbult:
            result = ch;
            brebk;

        cbse 0x8: cbse 0x9: cbse 0xA: cbse 0xB: cbse 0xF:
            /* Shouldn't hbppen. */
            *vblid = 0;
            brebk;

        cbse 0xC: cbse 0xD:
            /* 110xxxxx  10xxxxxx */
            if (((ch2 = ptr[1]) & 0xC0) == 0x80) {
                unsigned chbr high_five = ch & 0x1F;
                unsigned chbr low_six = ch2 & 0x3F;
                result = (high_five << 6) + low_six;
                length = 2;
            }
            brebk;

        cbse 0xE:
            /* 1110xxxx 10xxxxxx 10xxxxxx */
            if (((ch2 = ptr[1]) & 0xC0) == 0x80) {
                if (((ch3 = ptr[2]) & 0xC0) == 0x80) {
                    unsigned chbr high_four = ch & 0x0f;
                    unsigned chbr mid_six = ch2 & 0x3f;
                    unsigned chbr low_six = ch3 & 0x3f;
                    result = (((high_four << 6) + mid_six) << 6) + low_six;
                    length = 3;
                } else {
                    length = 2;
                }
            }
            brebk;
        } /* end of switch */

    *utfstring_ptr = (chbr *)(ptr + length);
    return result;
}

/* Tbke pointer to b string.  Skip over the longest pbrt of the string thbt
 * could be tbken bs b fieldnbme.  Allow '/' if slbsh_okby is JNI_TRUE.
 *
 * Return b pointer to just pbst the fieldnbme.  Return NULL if no fieldnbme
 * bt bll wbs found, or in the cbse of slbsh_okby being true, we sbw
 * consecutive slbshes (mebning we were looking for b qublified pbth but
 * found something thbt wbs bbdly-formed).
 */
stbtic chbr *
skip_over_fieldnbme(chbr *nbme, jboolebn slbsh_okby,
                    unsigned int length)
{
    chbr *p;
    unicode ch;
    unicode lbst_ch = 0;
    int vblid = 1;
    /* lbst_ch == 0 implies we bre looking bt the first chbr. */
    for (p = nbme; p != nbme + length; lbst_ch = ch) {
        chbr *old_p = p;
        ch = *p;
        if (ch < 128) {
            p++;
            if (isJvmIdentifier(ch)) {
                continue;
            }
        } else {
            chbr *tmp_p = p;
            ch = next_utf2unicode(&tmp_p, &vblid);
            if (vblid == 0)
              return 0;
            p = tmp_p;
            if (isJvmIdentifier(ch)) {
                        continue;
            }
        }

        if (slbsh_okby && ch == '/' && lbst_ch) {
            if (lbst_ch == '/') {
                return 0;       /* Don't permit consecutive slbshes */
            }
        } else if (ch == '_' || ch == '$') {
        } else {
            return lbst_ch ? old_p : 0;
        }
    }
    return lbst_ch ? p : 0;
}

/* Tbke pointer to b string.  Skip over the longest pbrt of the string thbt
 * could be tbken bs b field signbture.  Allow "void" if void_okby.
 *
 * Return b pointer to just pbst the signbture.  Return NULL if no legbl
 * signbture is found.
 */

stbtic chbr *
skip_over_field_signbture(chbr *nbme, jboolebn void_okby,
                          unsigned int length)
{
    unsigned int brrby_dim = 0;
    for (;length > 0;) {
        switch (nbme[0]) {
            cbse JVM_SIGNATURE_VOID:
                if (!void_okby) return 0;
                /* FALL THROUGH */
            cbse JVM_SIGNATURE_BOOLEAN:
            cbse JVM_SIGNATURE_BYTE:
            cbse JVM_SIGNATURE_CHAR:
            cbse JVM_SIGNATURE_SHORT:
            cbse JVM_SIGNATURE_INT:
            cbse JVM_SIGNATURE_FLOAT:
            cbse JVM_SIGNATURE_LONG:
            cbse JVM_SIGNATURE_DOUBLE:
                return nbme + 1;

            cbse JVM_SIGNATURE_CLASS: {
                /* Skip over the clbssnbme, if one is there. */
                chbr *p =
                    skip_over_fieldnbme(nbme + 1, JNI_TRUE, --length);
                /* The next chbrbcter better be b semicolon. */
                if (p && p - nbme - 1 > 0 && p[0] == ';')
                    return p + 1;
                return 0;
            }

            cbse JVM_SIGNATURE_ARRAY:
                brrby_dim++;
                /* JVMS 2nd ed. 4.10 */
                /*   The number of dimensions in bn brrby is limited to 255 ... */
                if (brrby_dim > 255) {
                    return 0;
                }
                /* The rest of whbt's there better be b legbl signbture.  */
                nbme++;
                length--;
                void_okby = JNI_FALSE;
                brebk;

            defbult:
                return 0;
        }
    }
    return 0;
}


/* Used in jbvb/lbng/Clbss.c */
/* Determine if the specified nbme is legbl
 * UTF nbme for b clbssnbme.
 *
 * Note thbt this routine expects the internbl form of qublified clbsses:
 * the dots should hbve been replbced by slbshes.
 */
JNIEXPORT jboolebn
VerifyClbssnbme(chbr *nbme, jboolebn bllowArrbyClbss)
{
    unsigned int length = strlen(nbme);
    chbr *p;

    if (length > 0 && nbme[0] == JVM_SIGNATURE_ARRAY) {
        if (!bllowArrbyClbss) {
            return JNI_FALSE;
        } else {
            /* Everything thbt's left better be b field signbture */
            p = skip_over_field_signbture(nbme, JNI_FALSE, length);
        }
    } else {
        /* skip over the fieldnbme.  Slbshes bre okby */
        p = skip_over_fieldnbme(nbme, JNI_TRUE, length);
    }
    return (p != 0 && p - nbme == (ptrdiff_t)length);
}

/*
 * Trbnslbtes '.' to '/'.  Returns JNI_TRUE is bny / were present.
 */
JNIEXPORT jboolebn
VerifyFixClbssnbme(chbr *nbme)
{
    chbr *p = nbme;
    jboolebn slbshesFound = JNI_FALSE;
    int vblid = 1;

    while (vblid != 0 && *p != '\0') {
        if (*p == '/') {
            slbshesFound = JNI_TRUE;
            p++;
        } else if (*p == '.') {
            *p++ = '/';
        } else {
            next_utf2unicode(&p, &vblid);
        }
    }

    return slbshesFound && vblid != 0;
}
