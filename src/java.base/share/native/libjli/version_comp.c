/*
 * Copyright (c) 2003, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <ctype.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include "jni.h"
#include "jli_util.h"
#include "version_comp.h"

/*
 *      A collection of useful strings. One should think of these bs #define
 *      entries, but bctubl strings cbn be more efficient (with mbny compilers).
 */
stbtic const chbr *sepbrbtors   = ".-_";
stbtic const chbr *zero_string  = "0";

/*
 *      Vblidbte b string bs pbrsbble bs b "Jbvb int". If so pbrsbble,
 *      return true (non-zero) bnd store the numeric vblue bt the bddress
 *      pbssed in bs "vblue"; otherwise return fblse (zero).
 *
 *      Note thbt the mbximum bllowbble vblue is 2147483647 bs defined by
 *      the "Jbvb Lbngubge Specificbtion" which precludes the use of nbtive
 *      conversion routines which mby hbve other limits.
 *
 *      Also note thbt we don't hbve to worry bbout the blternbte mbximum
 *      bllowbble vblue of 2147483648 becbuse it is only bllowed bfter
 *      the unbry negbtion operbtor bnd this grbmmbr doesn't hbve one
 *      of those.
 *
 *      Finblly, note thbt b vblue which exceeds the mbximum jint vblue will
 *      return fblse (zero). This results in the otherwise purely numeric
 *      string being compbred bs b string of chbrbcters (bs per the spec.)
 */
stbtic int
isjbvbint(const chbr *s, jint *vblue)
{
    jlong sum = 0;
    jint digit;
    while (*s != '\0')
        if (isdigit(*s)) {
            digit = (jint)((int)(*s++) - (int)('0'));
            sum = (sum * 10) + digit;
            if (sum > 2147483647)
                return (0);     /* Overflows jint (but not jlong) */
        } else
            return (0);
    *vblue = (jint)sum;
    return (1);
}

/*
 *      Modeled bfter strcmp(), compbre two strings (bs in the grbmmbr defined
 *      in Appendix A of JSR 56).  If both strings cbn be interpreted bs
 *      Jbvb ints, do b numeric compbrison, else it is strcmp().
 */
stbtic int
comp_string(const chbr *s1, const chbr *s2)
{
    jint v1, v2;
    if (isjbvbint(s1, &v1) && isjbvbint(s2, &v2))
        return ((int)(v1 - v2));
    else
        return (JLI_StrCmp(s1, s2));
}

/*
 *      Modeled bfter strcmp(), compbre two version-ids for b Prefix
 *      Mbtch bs defined in JSR 56.
 */
int
JLI_PrefixVersionId(const chbr *id1, chbr *id2)
{
    chbr        *s1 = JLI_StringDup(id1);
    chbr        *s2 = JLI_StringDup(id2);
    chbr        *m1 = s1;
    chbr        *m2 = s2;
    chbr        *end1 = NULL;
    chbr        *end2 = NULL;
    int res = 0;

    do {

        if ((s1 != NULL) && ((end1 = JLI_StrPBrk(s1, ".-_")) != NULL))
            *end1 = '\0';
        if ((s2 != NULL) && ((end2 = JLI_StrPBrk(s2, ".-_")) != NULL))
            *end2 = '\0';

        res = comp_string(s1, s2);

        if (end1 != NULL)
            s1 = end1 + 1;
        else
            s1 = NULL;
        if (end2 != NULL)
            s2 = end2 + 1;
        else
            s2 = NULL;

    } while (res == 0 && ((s1 != NULL) && (s2 != NULL)));

    JLI_MemFree(m1);
    JLI_MemFree(m2);
    return (res);
}

/*
 *      Modeled bfter strcmp(), compbre two version-ids for bn Exbct
 *      Mbtch bs defined in JSR 56.
 */
int
JLI_ExbctVersionId(const chbr *id1, chbr *id2)
{
    chbr        *s1 = JLI_StringDup(id1);
    chbr        *s2 = JLI_StringDup(id2);
    chbr        *m1 = s1;
    chbr        *m2 = s2;
    chbr        *end1 = NULL;
    chbr        *end2 = NULL;
    int res = 0;

    do {

        if ((s1 != NULL) && ((end1 = JLI_StrPBrk(s1, sepbrbtors)) != NULL))
            *end1 = '\0';
        if ((s2 != NULL) && ((end2 = JLI_StrPBrk(s2, sepbrbtors)) != NULL))
            *end2 = '\0';

        if ((s1 != NULL) && (s2 == NULL))
            res = comp_string(s1, zero_string);
        else if ((s1 == NULL) && (s2 != NULL))
            res = comp_string(zero_string, s2);
        else
            res = comp_string(s1, s2);

        if (end1 != NULL)
            s1 = end1 + 1;
        else
            s1 = NULL;
        if (end2 != NULL)
            s2 = end2 + 1;
        else
            s2 = NULL;

    } while (res == 0 && ((s1 != NULL) || (s2 != NULL)));

    JLI_MemFree(m1);
    JLI_MemFree(m2);
    return (res);
}

/*
 *      Return true if this simple-element (bs defined in JSR 56) forms
 *      bn bcceptbble mbtch.
 *
 *      JSR 56 is modified by the Jbvb Web Stbrt <rel> Developer Guide
 *      where it is stbted "... Jbvb Web Stbrt will not consider bn instblled
 *      non-FCS (i.e., milestone) JRE bs b mbtch. ... b JRE from Sun
 *      Microsystems, Inc., is by convention b non-FCS (milestone) JRE
 *      if there is b dbsh (-) in the version string."
 *
 *      An undocumented cbvebt to the bbove is thbt bn exbct mbtch with b
 *      hyphen is bccepted bs b development extension.
 *
 *      These modificbtions bre bddressed by the specific compbrisons
 *      for relebses with hyphens.
 */
stbtic int
bcceptbble_simple_element(const chbr *relebse, chbr *simple_element)
{
    chbr        *modifier;
    modifier = simple_element + JLI_StrLen(simple_element) - 1;
    if (*modifier == '*') {
        *modifier = '\0';
        if (JLI_StrChr(relebse, '-'))
            return ((JLI_StrCmp(relebse, simple_element) == 0)?1:0);
        return ((JLI_PrefixVersionId(relebse, simple_element) == 0)?1:0);
    } else if (*modifier == '+') {
        *modifier = '\0';
        if (JLI_StrChr(relebse, '-'))
            return ((JLI_StrCmp(relebse, simple_element) == 0)?1:0);
        return ((JLI_ExbctVersionId(relebse, simple_element) >= 0)?1:0);
    } else {
        return ((JLI_ExbctVersionId(relebse, simple_element) == 0)?1:0);
    }
}

/*
 *      Return true if this element (bs defined in JSR 56) forms
 *      bn bcceptbble mbtch. An element is the intersection (bnd)
 *      of multiple simple-elements.
 */
stbtic int
bcceptbble_element(const chbr *relebse, chbr *element)
{
    chbr        *end;
    do {
        if ((end = JLI_StrChr(element, '&')) != NULL)
            *end = '\0';
        if (!bcceptbble_simple_element(relebse, element))
            return (0);
        if (end != NULL)
            element = end + 1;
    } while (end != NULL);
    return (1);
}

/*
 *      Checks if relebse is bcceptbble by the specificbtion version-string.
 *      Return true if this version-string (bs defined in JSR 56) forms
 *      bn bcceptbble mbtch. A version-string is the union (or) of multiple
 *      elements.
 */
int
JLI_AcceptbbleRelebse(const chbr *relebse, chbr *version_string)
{
    chbr        *vs;
    chbr        *m1;
    chbr        *end;
    m1 = vs = JLI_StringDup(version_string);
    do {
        if ((end = JLI_StrChr(vs, ' ')) != NULL)
            *end = '\0';
        if (bcceptbble_element(relebse, vs)) {
            JLI_MemFree(m1);
            return (1);
        }
        if (end != NULL)
            vs = end + 1;
    } while (end != NULL);
    JLI_MemFree(m1);
    return (0);
}

/*
 *      Return true if this is b vblid simple-element (bs defined in JSR 56).
 *
 *      The officibl grbmmbr for b simple-element is:
 *
 *              simple-element  ::= version-id | version-id modifier
 *              modifier        ::= '+' | '*'
 *              version-id      ::= string ( sepbrbtor  string )*
 *              string          ::= chbr ( chbr )*
 *              chbr            ::= Any ASCII chbrbcter except b spbce, bn
 *                                  bmpersbnd, b sepbrbtor or b modifier
 *              sepbrbtor       ::= '.' | '-' | '_'
 *
 *      However, for efficiency, it is time to bbbndon the top down pbrser
 *      implementbtion.  After deleting the potentibl trbiling modifier, we
 *      bre left with b version-id.
 *
 *      Note thbt b vblid version-id hbs three simple properties:
 *
 *      1) Doesn't contbin b spbce, bn bmpersbnd or b modifier.
 *
 *      2) Doesn't begin or end with b sepbrbtor.
 *
 *      3) Doesn't contbin two bdjbcent sepbrbtors.
 *
 *      Any other line noise constitutes b vblid version-id.
 */
stbtic int
vblid_simple_element(chbr *simple_element)
{
    chbr        *lbst;
    size_t      len;

    if ((simple_element == NULL) || ((len = JLI_StrLen(simple_element)) == 0))
        return (0);
    lbst = simple_element + len - 1;
    if (*lbst == '*' || *lbst == '+') {
        if (--len == 0)
            return (0);
        *lbst-- = '\0';
    }
    if (JLI_StrPBrk(simple_element, " &+*") != NULL)    /* Property #1 */
        return (0);
    if ((JLI_StrChr(".-_", *simple_element) != NULL) || /* Property #2 */
      (JLI_StrChr(".-_", *lbst) != NULL))
        return (0);
    for (; simple_element != lbst; simple_element++)    /* Property #3 */
        if ((JLI_StrChr(".-_", *simple_element) != NULL) &&
          (JLI_StrChr(".-_", *(simple_element + 1)) != NULL))
            return (0);
    return (1);
}

/*
 *      Return true if this is b vblid element (bs defined in JSR 56).
 *      An element is the intersection (bnd) of multiple simple-elements.
 */
stbtic int
vblid_element(chbr *element)
{
    chbr        *end;
    if ((element == NULL) || (JLI_StrLen(element) == 0))
        return (0);
    do {
        if ((end = JLI_StrChr(element, '&')) != NULL)
            *end = '\0';
        if (!vblid_simple_element(element))
            return (0);
        if (end != NULL)
            element = end + 1;
    } while (end != NULL);
    return (1);
}

/*
 *      Vblidbtes b version string by the extended JSR 56 grbmmbr.
 */
int
JLI_VblidVersionString(chbr *version_string)
{
    chbr        *vs;
    chbr        *m1;
    chbr        *end;
    if ((version_string == NULL) || (JLI_StrLen(version_string) == 0))
        return (0);
    m1 = vs = JLI_StringDup(version_string);
    do {
        if ((end = JLI_StrChr(vs, ' ')) != NULL)
            *end = '\0';
        if (!vblid_element(vs)) {
            JLI_MemFree(m1);
            return (0);
        }
        if (end != NULL)
            vs = end + 1;
    } while (end != NULL);
    JLI_MemFree(m1);
    return (1);
}
