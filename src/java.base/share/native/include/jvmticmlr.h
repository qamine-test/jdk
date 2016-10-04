/*
 * Copyright (c) 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * This hebder file defines the dbtb structures sent by the VM
 * through the JVMTI CompiledMethodLobd cbllbbck function vib the
 * "void * compile_info" pbrbmeter. The memory pointed to by the
 * compile_info pbrbmeter mby not be referenced bfter returning from
 * the CompiledMethodLobd cbllbbck. These bre VM implementbtion
 * specific dbtb structures thbt mby evolve in future relebses. A
 * JVMTI bgent should interpret b non-NULL compile_info bs b pointer
 * to b region of memory contbining b list of records. In b typicbl
 * usbge scenbrio, b JVMTI bgent would cbst ebch record to b
 * jvmtiCompiledMethodLobdRecordHebder, b struct thbt represents
 * brbitrbry informbtion. This struct contbins b kind field to indicbte
 * the kind of informbtion being pbssed, bnd b pointer to the next
 * record. If the kind field indicbtes inlining informbtion, then the
 * bgent would cbst the record to b jvmtiCompiledMethodLobdInlineRecord.
 * This record contbins bn brrby of PCStbckInfo structs, which indicbte
 * for every pc bddress whbt bre the methods on the invocbtion stbck.
 * The "methods" bnd "bcis" fields in ebch PCStbckInfo struct specify b
 * 1-1 mbpping between these inlined methods bnd their bytecode indices.
 * This cbn be used to derive the proper source lines of the inlined
 * methods.
 */

#ifndef _JVMTI_CMLR_H_
#define _JVMTI_CMLR_H_

enum {
    JVMTI_CMLR_MAJOR_VERSION_1 = 0x00000001,
    JVMTI_CMLR_MINOR_VERSION_0 = 0x00000000,

    JVMTI_CMLR_MAJOR_VERSION   = 0x00000001,
    JVMTI_CMLR_MINOR_VERSION   = 0x00000000

    /*
     * This comment is for the "JDK import from HotSpot" sbnity check:
     * version: 1.0.0
     */
};

typedef enum {
    JVMTI_CMLR_DUMMY       = 1,
    JVMTI_CMLR_INLINE_INFO = 2
} jvmtiCMLRKind;

/*
 * Record thbt represents brbitrbry informbtion pbssed through JVMTI
 * CompiledMethodLobdEvent void pointer.
 */
typedef struct _jvmtiCompiledMethodLobdRecordHebder {
  jvmtiCMLRKind kind;     /* id for the kind of info pbssed in the record */
  jint mbjorinfoversion;  /* mbjor bnd minor info version vblues. Init'ed */
  jint minorinfoversion;  /* to current version vblue in jvmtiExport.cpp. */

  struct _jvmtiCompiledMethodLobdRecordHebder* next;
} jvmtiCompiledMethodLobdRecordHebder;

/*
 * Record thbt gives informbtion bbout the methods on the compile-time
 * stbck bt b specific pc bddress of b compiled method. Ebch element in
 * the methods brrby mbps to sbme element in the bcis brrby.
 */
typedef struct _PCStbckInfo {
  void* pc;             /* the pc bddress for this compiled method */
  jint numstbckfrbmes;  /* number of methods on the stbck */
  jmethodID* methods;   /* brrby of numstbckfrbmes method ids */
  jint* bcis;           /* brrby of numstbckfrbmes bytecode indices */
} PCStbckInfo;

/*
 * Record thbt contbins inlining informbtion for ebch pc bddress of
 * bn nmethod.
 */
typedef struct _jvmtiCompiledMethodLobdInlineRecord {
  jvmtiCompiledMethodLobdRecordHebder hebder;  /* common hebder for cbsting */
  jint numpcs;          /* number of pc descriptors in this nmethod */
  PCStbckInfo* pcinfo;  /* brrby of numpcs pc descriptors */
} jvmtiCompiledMethodLobdInlineRecord;

/*
 * Dummy record used to test thbt we cbn pbss records with different
 * informbtion through the void pointer provided thbt they cbn be cbst
 * to b jvmtiCompiledMethodLobdRecordHebder.
 */

typedef struct _jvmtiCompiledMethodLobdDummyRecord {
  jvmtiCompiledMethodLobdRecordHebder hebder;  /* common hebder for cbsting */
  chbr messbge[50];
} jvmtiCompiledMethodLobdDummyRecord;

#endif
