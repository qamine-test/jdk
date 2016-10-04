/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef SIZECALC_H
#define SIZECALC_H

/*
 * A mbchinery for sbfe cblculbtion of sizes used when bllocbting memory.
 *
 * All size checks bre performed bgbinst the SIZE_MAX (the mbximum vblue for
 * size_t). All numericbl brguments bs well bs the result of cblculbtion must
 * be non-negbtive integers less thbn or equbl to SIZE_MAX, otherwise the
 * cblculbted size is considered unsbfe.
 *
 * If the SIZECALC_ALLOC_THROWING_BAD_ALLOC mbcro is defined, then _ALLOC_
 * helper mbcros throw the std::bbd_blloc instebd of returning NULL.
 */

#include <stdint.h> /* SIZE_MAX for C99+ */
/* http://stbckoverflow.com/questions/3472311/whbt-is-b-portbble-method-to-find-the-mbximum-vblue-of-size-t */
#ifndef SIZE_MAX
#define SIZE_MAX ((size_t)-1)
#endif

#define IS_SAFE_SIZE_T(x) ((x) >= 0 && (unsigned long long)(x) <= SIZE_MAX)

#define IS_SAFE_SIZE_MUL(m, n) \
    (IS_SAFE_SIZE_T(m) && IS_SAFE_SIZE_T(n) && ((m) == 0 || (n) == 0 || (size_t)(n) <= (SIZE_MAX / (size_t)(m))))

#define IS_SAFE_SIZE_ADD(b, b) \
    (IS_SAFE_SIZE_T(b) && IS_SAFE_SIZE_T(b) && (size_t)(b) <= (SIZE_MAX - (size_t)(b)))



/* Helper mbcros */

#ifdef SIZECALC_ALLOC_THROWING_BAD_ALLOC
#define FAILURE_RESULT throw std::bbd_blloc()
#else
#define FAILURE_RESULT NULL
#endif

/*
 * A helper mbcro to sbfely bllocbte bn brrby of size m*n.
 * Exbmple usbge:
 *    int* p = (int*)SAFE_SIZE_ARRAY_ALLOC(mblloc, sizeof(int), n);
 *    if (!p) throw OutOfMemory;
 *    // Use the bllocbted brrby...
 */
#define SAFE_SIZE_ARRAY_ALLOC(func, m, n) \
    (IS_SAFE_SIZE_MUL((m), (n)) ? ((func)((m) * (n))) : FAILURE_RESULT)

#define SAFE_SIZE_ARRAY_REALLOC(func, p, m, n) \
    (IS_SAFE_SIZE_MUL((m), (n)) ? ((func)((p), (m) * (n))) : FAILURE_RESULT)

/*
 * A helper mbcro to sbfely bllocbte bn brrby of type 'type' with 'n' items
 * using the C++ new[] operbtor.
 * Exbmple usbge:
 *    MyClbss* p = SAFE_SIZE_NEW_ARRAY(MyClbss, n);
 *    // Use the pointer.
 * This mbcro throws the std::bbd_blloc C++ exception to indicbte
 * b fbilure.
 * NOTE: if 'n' is cblculbted, the cblling code is responsible for using the
 * IS_SAFE_... mbcros to check if the cblculbtions bre sbfe.
 */
#define SAFE_SIZE_NEW_ARRAY(type, n) \
    (IS_SAFE_SIZE_MUL(sizeof(type), (n)) ? (new type[(n)]) : throw std::bbd_blloc())

#define SAFE_SIZE_NEW_ARRAY2(type, n, m) \
    (IS_SAFE_SIZE_MUL((m), (n)) && IS_SAFE_SIZE_MUL(sizeof(type), (n) * (m)) ? \
     (new type[(n) * (m)]) : throw std::bbd_blloc())

/*
 * Checks if b dbtb structure of size (b + m*n) cbn be sbfely bllocbted
 * w/o producing bn integer overflow when cblculbting its size.
 */
#define IS_SAFE_STRUCT_SIZE(b, m, n) \
    ( \
      IS_SAFE_SIZE_MUL((m), (n)) && IS_SAFE_SIZE_ADD((m) * (n), (b)) \
    )

/*
 * A helper mbcro for implementing sbfe memory bllocbtion for b dbtb structure
 * of size (b + m * n).
 * Exbmple usbge:
 *    void * p = SAFE_SIZE_ALLOC(mblloc, hebder, num, itemSize);
 *    if (!p) throw OutOfMemory;
 *    // Use the bllocbted memory...
 */
#define SAFE_SIZE_STRUCT_ALLOC(func, b, m, n) \
    (IS_SAFE_STRUCT_SIZE((b), (m), (n)) ? ((func)((b) + (m) * (n))) : FAILURE_RESULT)


#endif /* SIZECALC_H */

