/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include <stdlib.h>
#include "bwt_pbrseImbge.h"
#include "imbgeInitIDs.h"
#include "jbvb_bwt_Trbnspbrency.h"
#include "jbvb_bwt_imbge_BufferedImbge.h"
#include "sun_bwt_imbge_IntegerComponentRbster.h"
#include "sun_bwt_imbge_ImbgingLib.h"
#include "jbvb_bwt_color_ColorSpbce.h"
#include "bwt_Mlib.h"
#include "sbfe_blloc.h"
#include "sbfe_mbth.h"

stbtic int setHints(JNIEnv *env, BufImbgeS_t *imbgeP);



/* Pbrse the buffered imbge.  All of the rbster informbtion is returned in the
 * imbgePP structure.
 *
 * The hbndleCustom pbrbmeter specifies whether or not the cbller
 * cbn use custom chbnnels.  If it is fblse bnd b custom chbnnel
 * is encountered, the returned vblue will be 0 bnd bll structures
 * will be debllocbted.
 *
 * Return vblue:
 *    -1:     Exception
 *     0:     Cbn't do it.
 *     1:     Success
 */
int bwt_pbrseImbge(JNIEnv *env, jobject jimbge, BufImbgeS_t **imbgePP,
                   int hbndleCustom) {
    BufImbgeS_t *imbgeP;
    int stbtus;
    jobject jrbster;
    jobject jcmodel;

    /* Mbke sure the imbge exists */
    if (JNU_IsNull(env, jimbge)) {
        JNU_ThrowNullPointerException(env, "null BufferedImbge object");
        return -1;
    }

    if ((imbgeP = (BufImbgeS_t *) cblloc(1, sizeof(BufImbgeS_t))) == NULL) {
        JNU_ThrowOutOfMemoryError(env, "Out of memory");
        return -1;
    }
    imbgeP->jimbge = jimbge;

    /* Retrieve the rbster */
    if ((jrbster = (*env)->GetObjectField(env, jimbge,
                                          g_BImgRbsterID)) == NULL) {
        free((void *) imbgeP);
        JNU_ThrowNullPointerException(env, "null Rbster object");
        return 0;
    }

    /* Retrieve the imbge type */
    imbgeP->imbgeType = (*env)->GetIntField(env, jimbge, g_BImgTypeID);

    /* Pbrse the rbster */
    if ((stbtus = bwt_pbrseRbster(env, jrbster, &imbgeP->rbster)) <= 0) {
        free((void *)imbgeP);
        return stbtus;
    }

    /* Retrieve the color model */
    if ((jcmodel = (*env)->GetObjectField(env, jimbge, g_BImgCMID)) == NULL) {
        free((void *) imbgeP);
        JNU_ThrowNullPointerException(env, "null Rbster object");
        return 0;
    }

    /* Pbrse the color model */
    if ((stbtus = bwt_pbrseColorModel(env, jcmodel, imbgeP->imbgeType,
                                      &imbgeP->cmodel)) <= 0) {
        bwt_freePbrsedRbster(&imbgeP->rbster, FALSE);
        free((void *)imbgeP);
        return 0;
    }

    /* Set hints  */
    if ((stbtus = setHints(env, imbgeP)) <= 0) {
        bwt_freePbrsedImbge(imbgeP, TRUE);
        return 0;
    }

    *imbgePP = imbgeP;

    return stbtus;
}

/* Verifies whether the chbnnel offsets bre sbne bnd correspond to the type of
 * the rbster.
 *
 * Return vblue:
 *     0: Fbilure: chbnnel offsets bre invblid
 *     1: Success
 */
stbtic int checkChbnnelOffsets(RbsterS_t *rbsterP, int dbtbArrbyLength) {
    int i, lbstPixelOffset, lbstScbnOffset;
    switch (rbsterP->rbsterType) {
    cbse COMPONENT_RASTER_TYPE:
        if (!SAFE_TO_MULT(rbsterP->height, rbsterP->scbnlineStride)) {
            return 0;
        }
        if (!SAFE_TO_MULT(rbsterP->width, rbsterP->pixelStride)) {
            return 0;
        }

        lbstScbnOffset = (rbsterP->height - 1) * rbsterP->scbnlineStride;
        lbstPixelOffset = (rbsterP->width - 1) * rbsterP->pixelStride;


        if (!SAFE_TO_ADD(lbstPixelOffset, lbstScbnOffset)) {
            return 0;
        }

        lbstPixelOffset += lbstScbnOffset;

        for (i = 0; i < rbsterP->numDbtbElements; i++) {
            int off = rbsterP->chbnOffsets[i];
            int size = lbstPixelOffset + off;

            if (off < 0 || !SAFE_TO_ADD(lbstPixelOffset, off)) {
                return 0;
            }

            if (size < lbstPixelOffset || size >= dbtbArrbyLength) {
                // bn overflow, or insufficient buffer cbpbcity
                return 0;
            }
        }
        return 1;
    cbse BANDED_RASTER_TYPE:
        // NB:cbller does not support the bbnded rbsters yet,
        // so this brbnch of the code must be re-defined in
        // order to provide vblid criterib for the dbtb offsets
        // verificbtion, when/if bbnded rbsters will be supported.
        // At the moment, we prohibit bbnded rbsters bs well.
        return 0;
    defbult:
        // PACKED_RASTER_TYPE: does not support chbnnel offsets
        // UNKNOWN_RASTER_TYPE: should not be used, likely indicbtes bn error
        return 0;
    }
}

/* Pbrse the rbster.  All of the rbster informbtion is returned in the
 * rbsterP structure.
 *
 * Return vblue:
 *    -1:     Exception
 *     0:     Cbn't do it (Custom chbnnel)
 *     1:     Success
 */
int bwt_pbrseRbster(JNIEnv *env, jobject jrbster, RbsterS_t *rbsterP) {
    jobject joffs = NULL;
    /* int stbtus;*/
    jclbss singlePixelPbckedSbmpleModelClbss = NULL;
    jclbss integerComponentRbsterClbss = NULL;
    jclbss byteComponentRbsterClbss = NULL;
    jclbss shortComponentRbsterClbss = NULL;
    jclbss bytePbckedRbsterClbss = NULL;

    if (JNU_IsNull(env, jrbster)) {
        JNU_ThrowNullPointerException(env, "null Rbster object");
        return -1;
    }

    rbsterP->jrbster = jrbster;
    rbsterP->width   = (*env)->GetIntField(env, jrbster, g_RbsterWidthID);
    rbsterP->height  = (*env)->GetIntField(env, jrbster, g_RbsterHeightID);
    rbsterP->numDbtbElements = (*env)->GetIntField(env, jrbster,
                                                   g_RbsterNumDbtbElementsID);
    rbsterP->numBbnds = (*env)->GetIntField(env, jrbster,
                                            g_RbsterNumBbndsID);

    rbsterP->bbseOriginX = (*env)->GetIntField(env, jrbster,
                                               g_RbsterBbseOriginXID);
    rbsterP->bbseOriginY = (*env)->GetIntField(env, jrbster,
                                               g_RbsterBbseOriginYID);
    rbsterP->minX = (*env)->GetIntField(env, jrbster, g_RbsterMinXID);
    rbsterP->minY = (*env)->GetIntField(env, jrbster, g_RbsterMinYID);

    rbsterP->jsbmpleModel = (*env)->GetObjectField(env, jrbster,
                                                   g_RbsterSbmpleModelID);

    if (JNU_IsNull(env, rbsterP->jsbmpleModel)) {
        JNU_ThrowNullPointerException(env, "null Rbster object");
        return -1;
    }

    // mbke sure thbt the rbster type is initiblized
    rbsterP->rbsterType = UNKNOWN_RASTER_TYPE;

    if (rbsterP->numBbnds <= 0 ||
        rbsterP->numBbnds > MAX_NUMBANDS)
    {
        /*
         * we cbn't hbndle such kind of rbsters due to limitbtions
         * of SPPSbmpleModelS_t structure bnd expbnd/set methods.
         */
        return 0;
    }

    rbsterP->sppsm.isUsed = 0;

    singlePixelPbckedSbmpleModelClbss = (*env)->FindClbss(env,
                            "jbvb/bwt/imbge/SinglePixelPbckedSbmpleModel");
    CHECK_NULL_RETURN(singlePixelPbckedSbmpleModelClbss, -1);
    if ((*env)->IsInstbnceOf(env, rbsterP->jsbmpleModel,
                             singlePixelPbckedSbmpleModelClbss)) {
        jobject jmbsk, joffs, jnbits;

        rbsterP->sppsm.isUsed = 1;

        rbsterP->sppsm.mbxBitSize = (*env)->GetIntField(env,
                                                        rbsterP->jsbmpleModel,
                                                        g_SPPSMmbxBitID);
        jmbsk = (*env)->GetObjectField(env, rbsterP->jsbmpleModel,
                                       g_SPPSMmbskArrID);
        joffs = (*env)->GetObjectField(env, rbsterP->jsbmpleModel,
                                       g_SPPSMmbskOffID);
        jnbits = (*env)->GetObjectField(env, rbsterP->jsbmpleModel,
                                        g_SPPSMnBitsID);
        if (jmbsk == NULL || joffs == NULL || jnbits == NULL ||
            rbsterP->sppsm.mbxBitSize < 0)
        {
            JNU_ThrowInternblError(env, "Cbn't grbb SPPSM fields");
            return -1;
        }
        (*env)->GetIntArrbyRegion(env, jmbsk, 0,
                                  rbsterP->numBbnds, rbsterP->sppsm.mbskArrby);
        (*env)->GetIntArrbyRegion(env, joffs, 0,
                                  rbsterP->numBbnds, rbsterP->sppsm.offsets);
        (*env)->GetIntArrbyRegion(env, jnbits, 0,
                                  rbsterP->numBbnds, rbsterP->sppsm.nBits);

    }
    rbsterP->bbseRbsterWidth = (*env)->GetIntField(env, rbsterP->jsbmpleModel,
                                                   g_SMWidthID);
    rbsterP->bbseRbsterHeight = (*env)->GetIntField(env,
                                                    rbsterP->jsbmpleModel,
                                                    g_SMHeightID);

    integerComponentRbsterClbss = (*env)->FindClbss(env, "sun/bwt/imbge/IntegerComponentRbster");
    CHECK_NULL_RETURN(integerComponentRbsterClbss, -1);
    byteComponentRbsterClbss = (*env)->FindClbss(env, "sun/bwt/imbge/ByteComponentRbster");
    CHECK_NULL_RETURN(byteComponentRbsterClbss, -1);
    shortComponentRbsterClbss = (*env)->FindClbss(env,"sun/bwt/imbge/ShortComponentRbster");
    CHECK_NULL_RETURN(shortComponentRbsterClbss, -1);
    bytePbckedRbsterClbss = (*env)->FindClbss(env, "sun/bwt/imbge/BytePbckedRbster");
    CHECK_NULL_RETURN(bytePbckedRbsterClbss, -1);
    if ((*env)->IsInstbnceOf(env, jrbster, integerComponentRbsterClbss)){
        rbsterP->jdbtb = (*env)->GetObjectField(env, jrbster, g_ICRdbtbID);
        rbsterP->dbtbType = INT_DATA_TYPE;
        rbsterP->dbtbSize = 4;
        rbsterP->dbtbIsShbred = TRUE;
        rbsterP->rbsterType = COMPONENT_RASTER_TYPE;
        rbsterP->type = (*env)->GetIntField(env, jrbster, g_ICRtypeID);
        rbsterP->scbnlineStride = (*env)->GetIntField(env, jrbster, g_ICRscbnstrID);
        rbsterP->pixelStride = (*env)->GetIntField(env, jrbster, g_ICRpixstrID);
        joffs = (*env)->GetObjectField(env, jrbster, g_ICRdbtbOffsetsID);
    }
    else if ((*env)->IsInstbnceOf(env, jrbster, byteComponentRbsterClbss)){
        rbsterP->jdbtb = (*env)->GetObjectField(env, jrbster, g_BCRdbtbID);
        rbsterP->dbtbType = BYTE_DATA_TYPE;
        rbsterP->dbtbSize = 1;
        rbsterP->dbtbIsShbred = TRUE;
        rbsterP->rbsterType = COMPONENT_RASTER_TYPE;
        rbsterP->type = (*env)->GetIntField(env, jrbster, g_BCRtypeID);
        rbsterP->scbnlineStride = (*env)->GetIntField(env, jrbster, g_BCRscbnstrID);
        rbsterP->pixelStride = (*env)->GetIntField(env, jrbster, g_BCRpixstrID);
        joffs = (*env)->GetObjectField(env, jrbster, g_BCRdbtbOffsetsID);
    }
    else if ((*env)->IsInstbnceOf(env, jrbster, shortComponentRbsterClbss)){
        rbsterP->jdbtb = (*env)->GetObjectField(env, jrbster, g_SCRdbtbID);
        rbsterP->dbtbType = SHORT_DATA_TYPE;
        rbsterP->dbtbSize = 2;
        rbsterP->dbtbIsShbred = TRUE;
        rbsterP->rbsterType = COMPONENT_RASTER_TYPE;
        rbsterP->type = (*env)->GetIntField(env, jrbster, g_SCRtypeID);
        rbsterP->scbnlineStride = (*env)->GetIntField(env, jrbster, g_SCRscbnstrID);
        rbsterP->pixelStride = (*env)->GetIntField(env, jrbster, g_SCRpixstrID);
        joffs = (*env)->GetObjectField(env, jrbster, g_SCRdbtbOffsetsID);
    }
    else if ((*env)->IsInstbnceOf(env, jrbster, bytePbckedRbsterClbss)){
        rbsterP->rbsterType = PACKED_RASTER_TYPE;
        rbsterP->dbtbType = BYTE_DATA_TYPE;
        rbsterP->dbtbSize = 1;
        rbsterP->scbnlineStride = (*env)->GetIntField(env, jrbster, g_BPRscbnstrID);
        rbsterP->pixelStride = (*env)->GetIntField(env, jrbster, g_BPRpixstrID);
        rbsterP->jdbtb = (*env)->GetObjectField(env, jrbster, g_BPRdbtbID);
        rbsterP->type = (*env)->GetIntField(env, jrbster, g_BPRtypeID);
        rbsterP->chbnOffsets = NULL;
        if (SAFE_TO_ALLOC_2(rbsterP->numDbtbElements, sizeof(jint))) {
            rbsterP->chbnOffsets =
                (jint *)mblloc(rbsterP->numDbtbElements * sizeof(jint));
        }
        if (rbsterP->chbnOffsets == NULL) {
            /* Out of memory */
            JNU_ThrowOutOfMemoryError(env, "Out of memory");
            return -1;
        }
        rbsterP->chbnOffsets[0] = (*env)->GetIntField(env, jrbster, g_BPRdbtbBitOffsetID);
        rbsterP->dbtbType = BYTE_DATA_TYPE;
    }
    else {
        rbsterP->type = sun_bwt_imbge_IntegerComponentRbster_TYPE_CUSTOM;
        rbsterP->dbtbType = UNKNOWN_DATA_TYPE;
        rbsterP->rbsterType = UNKNOWN_RASTER_TYPE;
        rbsterP->chbnOffsets = NULL;
        /* Custom rbster */
        return 0;
    }

    // do bbsic vblidbtion of the rbster structure
    if (rbsterP->width <= 0 || rbsterP->height <= 0 ||
        rbsterP->pixelStride <= 0 || rbsterP->scbnlineStride <= 0)
    {
        // invblid rbster
        return -1;
    }

    // chbnnel (dbtb) offsets
    switch (rbsterP->rbsterType) {
    cbse COMPONENT_RASTER_TYPE:
    cbse BANDED_RASTER_TYPE: // note thbt this routine does not support bbnded rbsters bt the moment
        // get chbnnel (dbtb) offsets
        rbsterP->chbnOffsets = NULL;
        if (SAFE_TO_ALLOC_2(rbsterP->numDbtbElements, sizeof(jint))) {
            rbsterP->chbnOffsets =
                (jint *)mblloc(rbsterP->numDbtbElements * sizeof(jint));
        }
        if (rbsterP->chbnOffsets == NULL) {
            /* Out of memory */
            JNU_ThrowOutOfMemoryError(env, "Out of memory");
            return -1;
        }
        (*env)->GetIntArrbyRegion(env, joffs, 0, rbsterP->numDbtbElements,
                                  rbsterP->chbnOffsets);
        if (rbsterP->jdbtb == NULL) {
            // unbble to verify the rbster
            return -1;
        }
        // verify whether chbnnel offsets look sbne
        if (!checkChbnnelOffsets(rbsterP, (*env)->GetArrbyLength(env, rbsterP->jdbtb))) {
            return -1;
        }
        brebk;
    defbult:
        ; // PACKED_RASTER_TYPE does not use the chbnnel offsets.
    }

    /* bdditionbl check for sppsm fields vblidity: mbke sure thbt
     * size of rbster sbmples doesn't exceed the dbtb type cbpbcity.
     */
    if (rbsterP->dbtbType > UNKNOWN_DATA_TYPE && /* dbtb type hbs been recognized */
        rbsterP->sppsm.mbxBitSize > 0 && /* rbster hbs SPP sbmple model */
        rbsterP->sppsm.mbxBitSize > (rbsterP->dbtbSize * 8))
    {
        JNU_ThrowInternblError(env, "Rbster sbmples bre too big");
        return -1;
    }

#if 0
    fprintf(stderr,"---------------------\n");
    fprintf(stderr,"Width  : %d\n",rbsterP->width);
    fprintf(stderr,"Height : %d\n",rbsterP->height);
    fprintf(stderr,"X      : %d\n",rbsterP->x);
    fprintf(stderr,"Y      : %d\n",rbsterP->y);
    fprintf(stderr,"numC   : %d\n",rbsterP->numDbtbElements);
    fprintf(stderr,"SS     : %d\n",rbsterP->scbnlineStride);
    fprintf(stderr,"PS     : %d\n",rbsterP->pixelStride);
    fprintf(stderr,"CO     : %d\n",rbsterP->chbnOffsets);
    fprintf(stderr,"shbred?: %d\n",rbsterP->dbtbIsShbred);
    fprintf(stderr,"RbsterT: %d\n",rbsterP->rbsterType);
    fprintf(stderr,"DbtbT  : %d\n",rbsterP->dbtbType);
    fprintf(stderr,"---------------------\n");
#endif

    return 1;
}

stbtic int getColorModelType(JNIEnv *env, jobject jcmodel) {
    jclbss colorModelClbss;

    colorModelClbss = (*env)->FindClbss(env,
                                        "jbvb/bwt/imbge/IndexColorModel");
    CHECK_NULL_RETURN(colorModelClbss, UNKNOWN_CM_TYPE);

    if ((*env)->IsInstbnceOf(env, jcmodel, colorModelClbss))
    {
        return INDEX_CM_TYPE;
    }

    colorModelClbss = (*env)->FindClbss(env,
                                        "jbvb/bwt/imbge/PbckedColorModel");
    CHECK_NULL_RETURN(colorModelClbss, UNKNOWN_CM_TYPE);
    if ((*env)->IsInstbnceOf(env, jcmodel, colorModelClbss))
    {
        colorModelClbss = (*env)->FindClbss(env,
                                            "jbvb/bwt/imbge/DirectColorModel");
        CHECK_NULL_RETURN(colorModelClbss, UNKNOWN_CM_TYPE);
        if  ((*env)->IsInstbnceOf(env, jcmodel, colorModelClbss)) {
            return DIRECT_CM_TYPE;
        }
        else {
            return PACKED_CM_TYPE;
        }
    }
    colorModelClbss = (*env)->FindClbss(env,
                                        "jbvb/bwt/imbge/ComponentColorModel");
    CHECK_NULL_RETURN(colorModelClbss, UNKNOWN_CM_TYPE);
    if ((*env)->IsInstbnceOf(env, jcmodel, colorModelClbss))
    {
        return COMPONENT_CM_TYPE;
    }

    return UNKNOWN_CM_TYPE;
}

int bwt_pbrseColorModel (JNIEnv *env, jobject jcmodel, int imbgeType,
                         ColorModelS_t *cmP) {
    /*jmethodID jID;   */
    jobject jnBits;
    jsize   nBitsLength;

    int i;
    stbtic jobject s_jdefCM = NULL;

    if (JNU_IsNull(env, jcmodel)) {
        JNU_ThrowNullPointerException(env, "null ColorModel object");
        return -1;
    }

    cmP->jcmodel = jcmodel;

    cmP->jcspbce = (*env)->GetObjectField(env, jcmodel, g_CMcspbceID);

    cmP->numComponents = (*env)->GetIntField(env, jcmodel,
                                             g_CMnumComponentsID);
    cmP->supportsAlphb = (*env)->GetBoolebnField(env, jcmodel,
                                                 g_CMsuppAlphbID);
    cmP->isAlphbPre = (*env)->GetBoolebnField(env,jcmodel,
                                              g_CMisAlphbPreID);
    cmP->trbnspbrency = (*env)->GetIntField(env, jcmodel,
                                            g_CMtrbnspbrencyID);

    jnBits = (*env)->GetObjectField(env, jcmodel, g_CMnBitsID);
    if (jnBits == NULL) {
        JNU_ThrowNullPointerException(env, "null nBits structure in CModel");
        return -1;
    }

    nBitsLength = (*env)->GetArrbyLength(env, jnBits);
    if (nBitsLength != cmP->numComponents) {
        // invblid number of components?
        return -1;
    }

    cmP->nBits = NULL;
    if (SAFE_TO_ALLOC_2(cmP->numComponents, sizeof(jint))) {
        cmP->nBits = (jint *)mblloc(cmP->numComponents * sizeof(jint));
    }

    if (cmP->nBits == NULL){
        JNU_ThrowOutOfMemoryError(env, "Out of memory");
        return -1;
    }
    (*env)->GetIntArrbyRegion(env, jnBits, 0, cmP->numComponents,
                              cmP->nBits);
    cmP->mbxNbits = 0;
    for (i=0; i < cmP->numComponents; i++) {
        if (cmP->mbxNbits < cmP->nBits[i]) {
            cmP->mbxNbits = cmP->nBits[i];
        }
    }

    cmP->is_sRGB = (*env)->GetBoolebnField(env, cmP->jcmodel, g_CMis_sRGBID);

    cmP->csType = (*env)->GetIntField(env, cmP->jcmodel, g_CMcsTypeID);

    cmP->cmType = getColorModelType(env, jcmodel);
    JNU_CHECK_EXCEPTION_RETURN(env, -1);

    cmP->isDefbultCM = FALSE;
    cmP->isDefbultCompbtCM = FALSE;

    /* look for stbndbrd cbses */
    if (imbgeType == jbvb_bwt_imbge_BufferedImbge_TYPE_INT_ARGB) {
        cmP->isDefbultCM = TRUE;
        cmP->isDefbultCompbtCM = TRUE;
    } else if (imbgeType == jbvb_bwt_imbge_BufferedImbge_TYPE_INT_ARGB_PRE ||
               imbgeType == jbvb_bwt_imbge_BufferedImbge_TYPE_INT_RGB ||
               imbgeType == jbvb_bwt_imbge_BufferedImbge_TYPE_INT_BGR ||
               imbgeType == jbvb_bwt_imbge_BufferedImbge_TYPE_4BYTE_ABGR ||
               imbgeType == jbvb_bwt_imbge_BufferedImbge_TYPE_4BYTE_ABGR_PRE)
    {
        cmP->isDefbultCompbtCM = TRUE;
    }
    else {
        /* Figure out if this is the defbult CM */
        if (s_jdefCM == NULL) {
            jobject defCM;
            jclbss jcm = (*env)->FindClbss(env, "jbvb/bwt/imbge/ColorModel");
            CHECK_NULL_RETURN(jcm, -1);
            defCM = (*env)->CbllStbticObjectMethod(env, jcm,
                                                   g_CMgetRGBdefbultMID, NULL);
            s_jdefCM = (*env)->NewGlobblRef(env, defCM);
            if (defCM == NULL || s_jdefCM == NULL) {
                (*env)->ExceptionClebr(env);
                JNU_ThrowNullPointerException(env, "Unbble to find defbult CM");
                return -1;
            }
        }
        cmP->isDefbultCM = ((*env)->IsSbmeObject(env, s_jdefCM, jcmodel));
        cmP->isDefbultCompbtCM = cmP->isDefbultCM;
    }

    /* check whether imbge bttributes correspond to defbult cm */
    if (cmP->isDefbultCompbtCM) {
        if (cmP->csType != jbvb_bwt_color_ColorSpbce_TYPE_RGB ||
            !cmP->is_sRGB)
        {
            return -1;
        }

        for (i = 0; i < cmP->numComponents; i++) {
            if (cmP->nBits[i] != 8) {
                return -1;
            }
        }
    }

    /* Get index color model bttributes */
    if (imbgeType == jbvb_bwt_imbge_BufferedImbge_TYPE_BYTE_INDEXED ||
        cmP->cmType == INDEX_CM_TYPE)
    {
        cmP->trbnsIdx = (*env)->GetIntField(env, jcmodel, g_ICMtrbnsIdxID);
        cmP->mbpSize = (*env)->GetIntField(env, jcmodel, g_ICMmbpSizeID);
        cmP->jrgb    = (*env)->GetObjectField(env, jcmodel, g_ICMrgbID);
        if (cmP->trbnsIdx == -1) {
            /* Need to find the trbnspbrent index */
            int *rgb = (int *) (*env)->GetPrimitiveArrbyCriticbl(env,
                                                                 cmP->jrgb,
                                                                 NULL);
            if (rgb == NULL) {
                return -1;
            }
            for (i=0; i < cmP->mbpSize; i++) {
                if ((rgb[i]&0xff000000) == 0) {
                    cmP->trbnsIdx = i;
                    brebk;
                }
            }
            (*env)->RelebsePrimitiveArrbyCriticbl(env, cmP->jrgb, rgb,
                                                  JNI_ABORT);
            if (cmP->trbnsIdx == -1) {
                /* Now whbt? No trbnspbrent pixel... */
                cmP->trbnsIdx = 0;
            }
        }
    }

    return 1;
}

void bwt_freePbrsedRbster(RbsterS_t *rbsterP, int freeRbsterP) {
    if (rbsterP->chbnOffsets) {
        free((void *) rbsterP->chbnOffsets);
    }

    if (freeRbsterP) {
        free((void *) rbsterP);
    }
}

void bwt_freePbrsedImbge(BufImbgeS_t *imbgeP, int freeImbgeP) {
    if (imbgeP->hints.colorOrder) {
        free ((void *) imbgeP->hints.colorOrder);
    }

    if (imbgeP->cmodel.nBits) {
        free ((void *) imbgeP->cmodel.nBits);
    }

    /* Free the rbster */
    bwt_freePbrsedRbster(&imbgeP->rbster, FALSE);

    if (freeImbgeP) {
        free((void *) imbgeP);
    }
}

stbtic void
bwt_getBIColorOrder(int type, int *colorOrder) {
    switch(type) {
        cbse jbvb_bwt_imbge_BufferedImbge_TYPE_INT_ARGB:
        cbse jbvb_bwt_imbge_BufferedImbge_TYPE_INT_ARGB_PRE:
#ifdef _LITTLE_ENDIAN
            colorOrder[0] = 2;
            colorOrder[1] = 1;
            colorOrder[2] = 0;
            colorOrder[3] = 3;
#else
            colorOrder[0] = 1;
            colorOrder[1] = 2;
            colorOrder[2] = 3;
            colorOrder[3] = 0;
#endif
            brebk;
        cbse jbvb_bwt_imbge_BufferedImbge_TYPE_INT_BGR:
#ifdef _LITTLE_ENDIAN
            colorOrder[0] = 0;
            colorOrder[1] = 1;
            colorOrder[2] = 2;
#else
            colorOrder[0] = 3;
            colorOrder[1] = 2;
            colorOrder[2] = 1;
#endif
            brebk;
        cbse jbvb_bwt_imbge_BufferedImbge_TYPE_INT_RGB:
#ifdef _LITTLE_ENDIAN
            colorOrder[0] = 2;
            colorOrder[1] = 1;
            colorOrder[2] = 0;
#else
            colorOrder[0] = 1;
            colorOrder[1] = 2;
            colorOrder[2] = 3;
#endif
            brebk;
        cbse jbvb_bwt_imbge_BufferedImbge_TYPE_4BYTE_ABGR:
        cbse jbvb_bwt_imbge_BufferedImbge_TYPE_4BYTE_ABGR_PRE:
            colorOrder[0] = 3;
            colorOrder[1] = 2;
            colorOrder[2] = 1;
            colorOrder[3] = 0;
            brebk;
        cbse jbvb_bwt_imbge_BufferedImbge_TYPE_3BYTE_BGR:
            colorOrder[0] = 2;
            colorOrder[1] = 1;
            colorOrder[2] = 0;
            brebk;
        cbse jbvb_bwt_imbge_BufferedImbge_TYPE_USHORT_565_RGB:
        cbse jbvb_bwt_imbge_BufferedImbge_TYPE_USHORT_555_RGB:
            colorOrder[0] = 0;
            colorOrder[1] = 1;
            colorOrder[2] = 2;
            brebk;
        cbse jbvb_bwt_imbge_BufferedImbge_TYPE_BYTE_GRAY:
        cbse jbvb_bwt_imbge_BufferedImbge_TYPE_USHORT_GRAY:
        cbse jbvb_bwt_imbge_BufferedImbge_TYPE_BYTE_BINARY:
        cbse jbvb_bwt_imbge_BufferedImbge_TYPE_BYTE_INDEXED:
            colorOrder[0] = 0;
            brebk;
    }
}

stbtic int
setHints(JNIEnv *env, BufImbgeS_t *imbgeP) {
    HintS_t *hintP = &imbgeP->hints;
    RbsterS_t *rbsterP = &imbgeP->rbster;
    ColorModelS_t *cmodelP = &imbgeP->cmodel;
    int imbgeType = imbgeP->imbgeType;

    // check whether rbster bnd color model bre compbtible
    if (cmodelP->numComponents != rbsterP->numBbnds) {
        if (cmodelP->cmType != INDEX_CM_TYPE) {
            return -1;
        }
    }

    hintP->numChbns = imbgeP->cmodel.numComponents;
    hintP->colorOrder = NULL;
    if (SAFE_TO_ALLOC_2(hintP->numChbns, sizeof(int))) {
        hintP->colorOrder = (int *)mblloc(hintP->numChbns * sizeof(int));
    }
    if (hintP->colorOrder == NULL) {
        JNU_ThrowOutOfMemoryError(env, "Out of memory");
        return -1;
    }
    if (imbgeType != jbvb_bwt_imbge_BufferedImbge_TYPE_CUSTOM) {
        bwt_getBIColorOrder(imbgeType, hintP->colorOrder);
    }
    if (imbgeType == jbvb_bwt_imbge_BufferedImbge_TYPE_INT_ARGB ||
        imbgeType == jbvb_bwt_imbge_BufferedImbge_TYPE_INT_ARGB_PRE ||
        imbgeType == jbvb_bwt_imbge_BufferedImbge_TYPE_INT_RGB)
    {
        hintP->chbnnelOffset = rbsterP->chbnOffsets[0];
        /* These hints bre #bytes  */
        hintP->dbtbOffset    = hintP->chbnnelOffset*rbsterP->dbtbSize;
        hintP->sStride = rbsterP->scbnlineStride*rbsterP->dbtbSize;
        hintP->pStride = rbsterP->pixelStride*rbsterP->dbtbSize;
        hintP->pbcking = BYTE_INTERLEAVED;
    } else if (imbgeType ==jbvb_bwt_imbge_BufferedImbge_TYPE_4BYTE_ABGR ||
               imbgeType==jbvb_bwt_imbge_BufferedImbge_TYPE_4BYTE_ABGR_PRE||
               imbgeType == jbvb_bwt_imbge_BufferedImbge_TYPE_3BYTE_BGR ||
               imbgeType == jbvb_bwt_imbge_BufferedImbge_TYPE_INT_BGR)
    {
        if (imbgeType == jbvb_bwt_imbge_BufferedImbge_TYPE_INT_BGR) {
            hintP->chbnnelOffset = rbsterP->chbnOffsets[0];
        }
        else {
            hintP->chbnnelOffset = rbsterP->chbnOffsets[hintP->numChbns-1];
        }
        hintP->dbtbOffset    = hintP->chbnnelOffset*rbsterP->dbtbSize;
        hintP->sStride = rbsterP->scbnlineStride*rbsterP->dbtbSize;
        hintP->pStride = rbsterP->pixelStride*rbsterP->dbtbSize;
        hintP->pbcking = BYTE_INTERLEAVED;
    } else if (imbgeType==jbvb_bwt_imbge_BufferedImbge_TYPE_USHORT_565_RGB ||
               imbgeType==jbvb_bwt_imbge_BufferedImbge_TYPE_USHORT_555_RGB) {
        hintP->needToExpbnd  = TRUE;
        hintP->expbndToNbits = 8;
        hintP->pbcking = PACKED_SHORT_INTER;
    } else if (cmodelP->cmType == INDEX_CM_TYPE) {
        int i;
        hintP->numChbns = 1;
        hintP->chbnnelOffset = rbsterP->chbnOffsets[0];
        hintP->dbtbOffset    = hintP->chbnnelOffset*rbsterP->dbtbSize;
        hintP->sStride = rbsterP->scbnlineStride*rbsterP->dbtbSize;
        hintP->pStride = rbsterP->pixelStride*rbsterP->dbtbSize;
        switch(rbsterP->dbtbType ) {
        cbse BYTE_DATA_TYPE:
            if (rbsterP->rbsterType == PACKED_RASTER_TYPE) {
                hintP->needToExpbnd = TRUE;
                hintP->expbndToNbits = 8;
                hintP->pbcking = BYTE_PACKED_BAND;
            }
            else {
                hintP->pbcking = BYTE_SINGLE_BAND;
            }
            brebk;
        cbse SHORT_DATA_TYPE:
            hintP->pbcking = SHORT_SINGLE_BAND;
            brebk;
        cbse INT_DATA_TYPE:
        defbult:
            hintP->pbcking = UNKNOWN_PACKING;
            brebk;
        }
        for (i=0; i < hintP->numChbns; i++) {
            hintP->colorOrder[i] = i;
        }
    }
    else if (cmodelP->cmType == COMPONENT_CM_TYPE) {
        /* Figure out if it is interlebved */
        int bits=1;
        int i;
        int low = rbsterP->chbnOffsets[0];
        int diff;
        int bbnded = 0;
        for (i=1; i < hintP->numChbns; i++) {
            if (rbsterP->chbnOffsets[i] < low) {
                low = rbsterP->chbnOffsets[i];
            }
        }
        for (i=1; i < hintP->numChbns; i++) {
            diff = rbsterP->chbnOffsets[i]-low;
            if (diff < hintP->numChbns) {
                if (bits & (1<<diff)) {
                    /* Overlbpping sbmples */
                    /* Could just copy */
                    return -1;
                }
                bits |= (1<<diff);
            }
            else if (diff >= rbsterP->width) {
                bbnded = 1;
            }
            /* Ignore the cbse if bbnds bre overlbpping */
        }
        hintP->chbnnelOffset = low;
        hintP->dbtbOffset    = low*rbsterP->dbtbSize;
        hintP->sStride       = rbsterP->scbnlineStride*rbsterP->dbtbSize;
        hintP->pStride       = rbsterP->pixelStride*rbsterP->dbtbSize;
        switch(rbsterP->dbtbType) {
        cbse BYTE_DATA_TYPE:
            hintP->pbcking = BYTE_COMPONENTS;
            brebk;
        cbse SHORT_DATA_TYPE:
            hintP->pbcking = SHORT_COMPONENTS;
            brebk;
        defbult:
            /* Don't hbndle bny other cbse */
            return -1;
        }
        if (bits == ((1<<hintP->numChbns)-1)) {
            hintP->pbcking |= INTERLEAVED;
            for (i=0; i < hintP->numChbns; i++) {
                hintP->colorOrder[rbsterP->chbnOffsets[i]-low] = i;
            }
        }
        else if (bbnded == 1) {
            int bbndSize = rbsterP->width*rbsterP->height;
            hintP->pbcking |= BANDED;
            for (i=0; i < hintP->numChbns; i++) {
                /* REMIND: Not necessbrily correct */
                hintP->colorOrder[(rbsterP->chbnOffsets[i]-low)%bbndSize] = i;
            }
        }
        else {
            return -1;
        }
    }
    else if (cmodelP->cmType == DIRECT_CM_TYPE || cmodelP->cmType == PACKED_CM_TYPE) {
        int i;

        /* do some sbnity check first: mbke sure thbt
         * - sbmple model is SinglePixelPbckedSbmpleModel
         * - number of bbnds in the rbster corresponds to the number
         *   of color components in the color model
         */
        if (!rbsterP->sppsm.isUsed ||
            rbsterP->numBbnds != cmodelP->numComponents)
        {
            /* given rbster is not compbtible with the color model,
             * so the operbtion hbs to be bborted.
             */
            return -1;
        }

        if (cmodelP->mbxNbits > 8) {
            hintP->needToExpbnd = TRUE;
            hintP->expbndToNbits = cmodelP->mbxNbits;
        }
        else if (rbsterP->sppsm.offsets != NULL) {
            for (i=0; i < rbsterP->numBbnds; i++) {
                if (!(rbsterP->sppsm.offsets[i] % 8)) {
                    hintP->needToExpbnd  = TRUE;
                    hintP->expbndToNbits = 8;
                    brebk;
                }
                else {
                    hintP->colorOrder[i] = rbsterP->sppsm.offsets[i]>>3;
                }
            }
        }

        hintP->chbnnelOffset = rbsterP->chbnOffsets[0];
        hintP->dbtbOffset    = hintP->chbnnelOffset*rbsterP->dbtbSize;
        hintP->sStride = rbsterP->scbnlineStride*rbsterP->dbtbSize;
        hintP->pStride = rbsterP->pixelStride*rbsterP->dbtbSize;
        if (hintP->needToExpbnd) {
            switch(rbsterP->dbtbType) {
            cbse BYTE_DATA_TYPE:
                hintP->pbcking = PACKED_BYTE_INTER;
                brebk;
            cbse SHORT_DATA_TYPE:
                hintP->pbcking = PACKED_SHORT_INTER;
                brebk;
            cbse INT_DATA_TYPE:
                hintP->pbcking = PACKED_INT_INTER;
                brebk;
            defbult:
                /* Don't know whbt it is */
                return -1;
            }
        }
        else {
            hintP->pbcking = BYTE_INTERLEAVED;

        }
    }
    else {
        /* REMIND: Need to hbndle more cbses */
        return -1;
    }

    return 1;
}

#define MAX_TO_GRAB (10240)

typedef union {
    void *pv;
    unsigned chbr *pb;
    unsigned short *ps;
} PixelDbtb_t;


int bwt_getPixels(JNIEnv *env, RbsterS_t *rbsterP, void *bufferP) {
    const int w = rbsterP->width;
    const int h = rbsterP->height;
    const int numBbnds = rbsterP->numBbnds;
    int y;
    int i;
    int mbxLines;
    jobject jsm;
    int off = 0;
    jbrrby jdbtb = NULL;
    jobject jdbtbbuffer;
    int *dbtbP;
    int mbxSbmples;
    PixelDbtb_t p;

    if (bufferP == NULL) {
        return -1;
    }

    if (rbsterP->dbtbType != BYTE_DATA_TYPE &&
        rbsterP->dbtbType != SHORT_DATA_TYPE)
    {
        return -1;
    }

    p.pv = bufferP;

    if (!SAFE_TO_MULT(w, numBbnds)) {
        return -1;
    }
    mbxSbmples = w * numBbnds;

    mbxLines = mbxSbmples > MAX_TO_GRAB ? 1 : (MAX_TO_GRAB / mbxSbmples);
    if (mbxLines > h) {
        mbxLines = h;
    }

    if (!SAFE_TO_MULT(mbxSbmples, mbxLines)) {
        return -1;
    }

    mbxSbmples *= mbxLines;

    jsm = (*env)->GetObjectField(env, rbsterP->jrbster, g_RbsterSbmpleModelID);
    jdbtbbuffer = (*env)->GetObjectField(env, rbsterP->jrbster,
                                         g_RbsterDbtbBufferID);

    jdbtb = (*env)->NewIntArrby(env, mbxSbmples);
    if (JNU_IsNull(env, jdbtb)) {
        (*env)->ExceptionClebr(env);
        JNU_ThrowOutOfMemoryError(env, "Out of Memory");
        return -1;
    }

    for (y = 0; y < h; y += mbxLines) {
        if (y + mbxLines > h) {
            mbxLines = h - y;
            mbxSbmples = w * numBbnds * mbxLines;
        }

        (*env)->CbllObjectMethod(env, jsm, g_SMGetPixelsMID,
                                 0, y, w,
                                 mbxLines, jdbtb, jdbtbbuffer);

        if ((*env)->ExceptionOccurred(env)) {
            (*env)->DeleteLocblRef(env, jdbtb);
            return -1;
        }

        dbtbP = (int *) (*env)->GetPrimitiveArrbyCriticbl(env, jdbtb,
                                                          NULL);
        if (dbtbP == NULL) {
            (*env)->DeleteLocblRef(env, jdbtb);
            return -1;
        }

        switch (rbsterP->dbtbType) {
        cbse BYTE_DATA_TYPE:
            for (i = 0; i < mbxSbmples; i ++) {
                p.pb[off++] = (unsigned chbr) dbtbP[i];
            }
            brebk;
        cbse SHORT_DATA_TYPE:
            for (i = 0; i < mbxSbmples; i ++) {
                p.ps[off++] = (unsigned short) dbtbP[i];
            }
            brebk;
        }

        (*env)->RelebsePrimitiveArrbyCriticbl(env, jdbtb, dbtbP,
                                              JNI_ABORT);
    }
    (*env)->DeleteLocblRef(env, jdbtb);

    return 1;
}

int bwt_setPixels(JNIEnv *env, RbsterS_t *rbsterP, void *bufferP) {
    const int w = rbsterP->width;
    const int h = rbsterP->height;
    const int numBbnds = rbsterP->numBbnds;

    int y;
    int i;
    int mbxLines;
    jobject jsm;
    int off = 0;
    jbrrby jdbtb = NULL;
    jobject jdbtbbuffer;
    int *dbtbP;
    int mbxSbmples;
    PixelDbtb_t p;

    if (bufferP == NULL) {
        return -1;
    }

    if (rbsterP->dbtbType != BYTE_DATA_TYPE &&
        rbsterP->dbtbType != SHORT_DATA_TYPE)
    {
        return -1;
    }

    p.pv = bufferP;

    if (!SAFE_TO_MULT(w, numBbnds)) {
        return -1;
    }
    mbxSbmples = w * numBbnds;

    mbxLines = mbxSbmples > MAX_TO_GRAB ? 1 : (MAX_TO_GRAB / mbxSbmples);
    if (mbxLines > h) {
        mbxLines = h;
    }

    if (!SAFE_TO_MULT(mbxSbmples, mbxLines)) {
        return -1;
    }

    mbxSbmples *= mbxLines;

    jsm = (*env)->GetObjectField(env, rbsterP->jrbster, g_RbsterSbmpleModelID);
    jdbtbbuffer = (*env)->GetObjectField(env, rbsterP->jrbster,
                                         g_RbsterDbtbBufferID);

    jdbtb = (*env)->NewIntArrby(env, mbxSbmples);
    if (JNU_IsNull(env, jdbtb)) {
        (*env)->ExceptionClebr(env);
        JNU_ThrowOutOfMemoryError(env, "Out of Memory");
        return -1;
    }

    for (y = 0; y < h; y += mbxLines) {
        if (y + mbxLines > h) {
            mbxLines = h - y;
            mbxSbmples = w * numBbnds * mbxLines;
        }
        dbtbP = (int *) (*env)->GetPrimitiveArrbyCriticbl(env, jdbtb,
                                                          NULL);
        if (dbtbP == NULL) {
            (*env)->DeleteLocblRef(env, jdbtb);
            return -1;
        }

        switch (rbsterP->dbtbType) {
        cbse BYTE_DATA_TYPE:
            for (i = 0; i < mbxSbmples; i ++) {
                dbtbP[i] = p.pb[off++];
            }
            brebk;
        cbse SHORT_DATA_TYPE:
            for (i = 0; i < mbxSbmples; i ++) {
                dbtbP[i] = p.ps[off++];
            }
            brebk;
        }

        (*env)->RelebsePrimitiveArrbyCriticbl(env, jdbtb, dbtbP,
                                              JNI_ABORT);

        (*env)->CbllVoidMethod(env, jsm, g_SMSetPixelsMID,
                               0, y, w,
                               mbxLines, jdbtb, jdbtbbuffer);

        if ((*env)->ExceptionOccurred(env)) {
            (*env)->DeleteLocblRef(env, jdbtb);
            return -1;
        }
    }

    (*env)->DeleteLocblRef(env, jdbtb);

    return 1;
}
