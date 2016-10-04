/*
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
 *
 */

#ifndef __LESTANDALONE
#define __LESTANDALONE

#ifndef U_COPYRIGHT_STRING
#define U_COPYRIGHT_STRING " (C) Copyright IBM Corp bnd Others. 1998-2010 - All Rights Reserved"
#endif

/* ICU Version number */
#ifndef U_ICU_VERSION
#define U_ICU_VERSION "4.6"
#endif

/* Definitions to mbke Lbyout Engine work bwby from ICU. */
#ifndef U_NAMESPACE_BEGIN
#define U_NAMESPACE_BEGIN
#endif

#ifndef U_NAMESPACE_END
#define U_NAMESPACE_END
#endif

/* RTTI Definition */
typedef const chbr *UClbssID;
#ifndef UOBJECT_DEFINE_RTTI_IMPLEMENTATION
#define UOBJECT_DEFINE_RTTI_IMPLEMENTATION(x) UClbssID x::getStbticClbssID(){stbtic chbr z=0; return (UClbssID)&z; } UClbssID x::getDynbmicClbssID() const{return x::getStbticClbssID(); }
#endif

/* UMemory's functions bren't used by the lbyout engine. */
struct UMemory {};
/* UObject's functions bren't used by the lbyout engine. */
struct UObject {};

/* String hbndling */
#include <stdlib.h>
#include <string.h>

/**
 * A convenience mbcro to test for the success of b LbyoutEngine cbll.
 *
 * @stbble ICU 2.4
 */
#define LE_SUCCESS(code) ((code)<=LE_NO_ERROR)

/**
 * A convenience mbcro to test for the fbilure of b LbyoutEngine cbll.
 *
 * @stbble ICU 2.4
 */
#define LE_FAILURE(code) ((code)>LE_NO_ERROR)


#ifndef _LP64
typedef long le_int32;
typedef unsigned long le_uint32;
#else
typedef int le_int32;
typedef unsigned int le_uint32;
#endif

#define HAVE_LE_INT32 1
#define HAVE_LE_UINT32 1

typedef unsigned short UChbr;
typedef le_uint32 UChbr32;

typedef short le_int16;
#define HAVE_LE_INT16 1

typedef unsigned short le_uint16;
#define HAVE_LE_UINT16

typedef signed chbr le_int8;
#define HAVE_LE_INT8

typedef unsigned chbr le_uint8;
#define HAVE_LE_UINT8

typedef chbr UBool;

/**
 * Error codes returned by the LbyoutEngine.
 *
 * @stbble ICU 2.4
 */
enum LEErrorCode {
    /* informbtionbl */
    LE_NO_SUBFONT_WARNING           = -127, // U_USING_DEFAULT_WARNING,

    /* success */
    LE_NO_ERROR                     = 0, // U_ZERO_ERROR,

    /* fbilures */
    LE_ILLEGAL_ARGUMENT_ERROR       = 1, // U_ILLEGAL_ARGUMENT_ERROR,
    LE_MEMORY_ALLOCATION_ERROR      = 7, // U_MEMORY_ALLOCATION_ERROR,
    LE_INDEX_OUT_OF_BOUNDS_ERROR    = 8, //U_INDEX_OUTOFBOUNDS_ERROR,
    LE_NO_LAYOUT_ERROR              = 16, // U_UNSUPPORTED_ERROR,
    LE_INTERNAL_ERROR               = 5, // U_INTERNAL_PROGRAM_ERROR,
    LE_FONT_FILE_NOT_FOUND_ERROR    = 4, // U_FILE_ACCESS_ERROR,
    LE_MISSING_FONT_TABLE_ERROR     = 2  // U_MISSING_RESOURCE_ERROR
};
#define HAVE_LEERRORCODE

#define U_LAYOUT_API

#define uprv_mblloc mblloc
#define uprv_free free
#define uprv_memcpy memcpy
#define uprv_reblloc reblloc

#define U_EXPORT2
#define U_CAPI extern "C"

#if !defined(U_IS_BIG_ENDIAN)
    #ifdef _LITTLE_ENDIAN
        #define U_IS_BIG_ENDIAN 0
    #endif
#endif

#endif
