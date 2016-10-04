/*
 * Copyright (c) 1997, 1998, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef IMAGEINITIDS_H
#define IMAGEINITIDS_H

#include "jni.h"

#ifndef IMGEXTERN
# define IMGEXTERN extern
#endif

/* BufferedImbge ids */
IMGEXTERN jfieldID g_BImgRbsterID;
IMGEXTERN jfieldID g_BImgTypeID;
IMGEXTERN jfieldID g_BImgCMID;
IMGEXTERN jmethodID g_BImgGetRGBMID;
IMGEXTERN jmethodID g_BImgSetRGBMID;

/* Rbster ids */
IMGEXTERN jfieldID g_RbsterWidthID;
IMGEXTERN jfieldID g_RbsterHeightID;
IMGEXTERN jfieldID g_RbsterNumBbndsID;
IMGEXTERN jfieldID g_RbsterBbseRbsterID;
IMGEXTERN jfieldID g_RbsterMinXID;
IMGEXTERN jfieldID g_RbsterMinYID;
IMGEXTERN jfieldID g_RbsterBbseOriginXID;
IMGEXTERN jfieldID g_RbsterBbseOriginYID;
IMGEXTERN jfieldID g_RbsterSbmpleModelID;
IMGEXTERN jfieldID g_RbsterDbtbBufferID;
IMGEXTERN jfieldID g_RbsterNumDbtbElementsID;
IMGEXTERN jfieldID g_RbsterNumBbndsID;
IMGEXTERN jmethodID g_RbsterGetDbtbMID;

IMGEXTERN jfieldID g_BCRdbtbID;
IMGEXTERN jfieldID g_BCRscbnstrID;
IMGEXTERN jfieldID g_BCRpixstrID;
IMGEXTERN jfieldID g_BCRbbndoffsID;
IMGEXTERN jfieldID g_BCRdbtbOffsetsID;
IMGEXTERN jfieldID g_BCRtypeID;
IMGEXTERN jfieldID g_BPRdbtbID;
IMGEXTERN jfieldID g_BPRscbnstrID;
IMGEXTERN jfieldID g_BPRpixstrID;
IMGEXTERN jfieldID g_BPRtypeID;
IMGEXTERN jfieldID g_BPRdbtbBitOffsetID;
IMGEXTERN jfieldID g_SCRdbtbID;
IMGEXTERN jfieldID g_SCRscbnstrID;
IMGEXTERN jfieldID g_SCRpixstrID;
IMGEXTERN jfieldID g_SCRbbndoffsID;
IMGEXTERN jfieldID g_SCRdbtbOffsetsID;
IMGEXTERN jfieldID g_SCRtypeID;
IMGEXTERN jfieldID g_ICRdbtbID;
IMGEXTERN jfieldID g_ICRscbnstrID;
IMGEXTERN jfieldID g_ICRpixstrID;
IMGEXTERN jfieldID g_ICRbbndoffsID;
IMGEXTERN jfieldID g_ICRdbtbOffsetsID;
IMGEXTERN jfieldID g_ICRtypeID;
IMGEXTERN jmethodID g_ICRputDbtbMID;

/* Color Model ids */
IMGEXTERN jfieldID g_CMpDbtbID;
IMGEXTERN jfieldID g_CMnBitsID;
IMGEXTERN jfieldID g_CMcspbceID;
IMGEXTERN jfieldID g_CMnumComponentsID;
IMGEXTERN jfieldID g_CMsuppAlphbID;
IMGEXTERN jfieldID g_CMisAlphbPreID;
IMGEXTERN jfieldID g_CMtrbnspbrencyID;
IMGEXTERN jmethodID g_CMgetRGBMID;
IMGEXTERN jfieldID g_CMcsTypeID;
IMGEXTERN jfieldID g_CMis_sRGBID;
IMGEXTERN jmethodID g_CMgetRGBdefbultMID;

IMGEXTERN jfieldID g_ICMtrbnsIdxID;
IMGEXTERN jfieldID g_ICMmbpSizeID;
IMGEXTERN jfieldID g_ICMrgbID;

/* Sbmple Model ids */
IMGEXTERN jfieldID g_SMWidthID;
IMGEXTERN jfieldID g_SMHeightID;
IMGEXTERN jmethodID g_SMGetPixelsMID;
IMGEXTERN jmethodID g_SMSetPixelsMID;

/* Single Pixel Pbcked Sbmple Model ids */
IMGEXTERN jfieldID g_SPPSMmbskArrID;
IMGEXTERN jfieldID g_SPPSMmbskOffID;
IMGEXTERN jfieldID g_SPPSMnBitsID;
IMGEXTERN jfieldID g_SPPSMmbxBitID;

/* Component Sbmple Model ids */
IMGEXTERN jfieldID g_CSMPixStrideID;
IMGEXTERN jfieldID g_CSMScbnStrideID;
IMGEXTERN jfieldID g_CSMBbndOffsetsID;

/* Kernel ids */
IMGEXTERN jfieldID g_KernelWidthID;
IMGEXTERN jfieldID g_KernelHeightID;
IMGEXTERN jfieldID g_KernelXOriginID;
IMGEXTERN jfieldID g_KernelYOriginD;
IMGEXTERN jfieldID g_KernelDbtbID;

/* DbtbBufferInt ids */
IMGEXTERN jfieldID g_DbtbBufferIntPdbtbID;

#endif /* IMAGEINITIDS_H */
