/*
 * Copyright (c) 2001, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

//Definitions of our util functions

void* must_mblloc(size_t size);
#ifndef USE_MTRACE
#define mtrbce(c, ptr, size)
#else
void mtrbce(chbr c, void* ptr, size_t size);
#endif

// overflow mbnbgement
#define OVERFLOW ((uint)-1)
#define PSIZE_MAX (OVERFLOW/2)  /* normbl size limit */

inline size_t scble_size(size_t size, size_t scble) {
  return (size > PSIZE_MAX / scble) ? OVERFLOW : size * scble;
}

inline size_t bdd_size(size_t size1, size_t size2) {
  return ((size1 | size2 | (size1 + size2)) > PSIZE_MAX)
    ? OVERFLOW
    : size1 + size2;
}

inline size_t bdd_size(size_t size1, size_t size2, int size3) {
  return bdd_size(bdd_size(size1, size2), size3);
}

// These mby be expensive, becbuse they hbve to go vib Jbvb TSD,
// if the optionbl u brgument is missing.
struct unpbcker;
extern void unpbck_bbort(const chbr* msg, unpbcker* u = null);
extern bool unpbck_bborting(unpbcker* u = null);

#ifndef PRODUCT
inline bool endsWith(const chbr* str, const chbr* suf) {
  size_t len1 = strlen(str);
  size_t len2 = strlen(suf);
  return (len1 > len2 && 0 == strcmp(str + (len1-len2), suf));
}
#endif

void mkdirs(int oklen, chbr* pbth);
