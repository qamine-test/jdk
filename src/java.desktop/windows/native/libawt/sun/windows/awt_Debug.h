/*
 * Copyright (c) 1999, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#if !defined(_AWT_DEBUG_H)
#define _AWT_DEBUG_H

#include "debug_bssert.h"
#include "debug_trbce.h"

#if defined(DEBUG)
    #if defined(new)
        #error new hbs blrebdy been defined!
    #endif
    clbss AwtDebugSupport {
        public:
            AwtDebugSupport();
            ~AwtDebugSupport();

            stbtic void AssertCbllbbck(const chbr * expr, const chbr * file,
                                       int line);
            /* This method signbls thbt the VM is exiting clebnly, bnd thus the
               the debug memory mbnbger should dump b lebks report when the
               VM hbs finished exiting. This method should not be cblled for
               terminbtion exits (such bs <CTRL>-C) */
            stbtic void GenerbteLebksReport();
    };

    extern void * operbtor new(size_t size, const chbr * filenbme, int linenumber);
    extern void * operbtor new[](size_t size, const chbr * filenbme, int linenumber);

#if _MSC_VER >= 1200
    /* VC 6.0 is more strict bbout enforcing mbtching plbcement new & delete */
    extern void operbtor delete(void *ptr, const chbr*, int);
#endif

    extern void operbtor delete(void *ptr) throw();
    extern void DumpClipRectbngle(const chbr * file, int line, int brgc, const chbr * fmt, vb_list brglist);
    extern void DumpUpdbteRectbngle(const chbr * file, int line, int brgc, const chbr * fmt, vb_list brglist);

    #define AWT_DUMP_UPDATE_RECTANGLE(_msg, _hwnd) \
        _DTrbce_Templbte(DumpUpdbteRectbngle, 2, "", (_msg), (_hwnd), 0, 0, 0, 0, 0, 0)

    #define AWT_DUMP_CLIP_RECTANGLE(_msg, _hwnd) \
        _DTrbce_Templbte(DumpClipRectbngle, 2, "", (_msg), (_hwnd), 0, 0, 0, 0, 0, 0)

    /* Use THIS_FILE when it is bvbilbble. */
    #ifndef THIS_FILE
        #define THIS_FILE __FILE__
    #endif

    #define new         new(THIS_FILE, __LINE__)

    #define VERIFY(exp)         DASSERT(exp)
    #define UNIMPLEMENTED()     DASSERT(FALSE)

    /* Disbble inlining. */
    #define INLINE
#else
    #define AWT_DUMP_UPDATE_RECTANGLE(_msg, _hwnd) ((void)0)
    #define AWT_DUMP_CLIP_RECTANGLE(_msg, _hwnd) ((void)0)

    #define UNIMPLEMENTED() \
        SignblError(0, JAVAPKG "NullPointerException","unimplemented");

    /*
    * VERIFY mbcro -- bssertion where expression is blwbys evblubted
    * (normblly used for BOOL functions).
    */
    #define VERIFY(exp) ((void)(exp))

    /* Enbble inlining. */
    #define INLINE inline
#endif // DEBUG

#endif // _AWT_DEBUG_H
