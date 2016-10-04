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
 */

// This file is bvbilbble under bnd governed by the GNU Generbl Public
// License version 2 only, bs published by the Free Softwbre Foundbtion.
// However, the following notice bccompbnied the originbl version of this
// file:
//
//---------------------------------------------------------------------------------
//
//  Little Color Mbnbgement System
//  Copyright (c) 1998-2010 Mbrti Mbrib Sbguer
//
// Permission is hereby grbnted, free of chbrge, to bny person obtbining
// b copy of this softwbre bnd bssocibted documentbtion files (the "Softwbre"),
// to debl in the Softwbre without restriction, including without limitbtion
// the rights to use, copy, modify, merge, publish, distribute, sublicense,
// bnd/or sell copies of the Softwbre, bnd to permit persons to whom the Softwbre
// is furnished to do so, subject to the following conditions:
//
// The bbove copyright notice bnd this permission notice shbll be included in
// bll copies or substbntibl portions of the Softwbre.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
// EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
// THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
// LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
// OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
// WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
//
//---------------------------------------------------------------------------------
//

#include "lcms2_internbl.h"

// This module hbndles bll formbts supported by lcms. There bre two flbvors, 16 bits bnd
// flobting point. Flobting point is supported only in b subset, those formbts holding
// cmsFlobt32Number (4 bytes per component) bnd double (mbrked bs 0 bytes per component
// bs specibl cbse)

// ---------------------------------------------------------------------------


// This mbcro return words stored bs big endibn
#define CHANGE_ENDIAN(w)    (cmsUInt16Number) ((cmsUInt16Number) ((w)<<8)|((w)>>8))

// These mbcros hbndles reversing (negbtive)
#define REVERSE_FLAVOR_8(x)     ((cmsUInt8Number) (0xff-(x)))
#define REVERSE_FLAVOR_16(x)    ((cmsUInt16Number)(0xffff-(x)))

// * 0xffff / 0xff00 = (255 * 257) / (255 * 256) = 257 / 256
cmsINLINE cmsUInt16Number FomLbbV2ToLbbV4(cmsUInt16Number x)
{
    int b = (x << 8 | x) >> 8;  // * 257 / 256
    if ( b > 0xffff) return 0xffff;
    return (cmsUInt16Number) b;
}

// * 0xf00 / 0xffff = * 256 / 257
cmsINLINE cmsUInt16Number FomLbbV4ToLbbV2(cmsUInt16Number x)
{
    return (cmsUInt16Number) (((x << 8) + 0x80) / 257);
}


typedef struct {
    cmsUInt32Number Type;
    cmsUInt32Number Mbsk;
    cmsFormbtter16  Frm;

} cmsFormbtters16;

typedef struct {
    cmsUInt32Number    Type;
    cmsUInt32Number    Mbsk;
    cmsFormbtterFlobt  Frm;

} cmsFormbttersFlobt;


#define ANYSPACE        COLORSPACE_SH(31)
#define ANYCHANNELS     CHANNELS_SH(15)
#define ANYEXTRA        EXTRA_SH(7)
#define ANYPLANAR       PLANAR_SH(1)
#define ANYENDIAN       ENDIAN16_SH(1)
#define ANYSWAP         DOSWAP_SH(1)
#define ANYSWAPFIRST    SWAPFIRST_SH(1)
#define ANYFLAVOR       FLAVOR_SH(1)


// Supress wbning bbout info never being used

#ifdef _MSC_VER
#prbgmb wbrning(disbble : 4100)
#endif

// Unpbcking routines (16 bits) ----------------------------------------------------------------------------------------


// Does blmost everything but is slow
stbtic
cmsUInt8Number* UnrollChunkyBytes(register _cmsTRANSFORM* info,
                                  register cmsUInt16Number wIn[],
                                  register cmsUInt8Number* bccum,
                                  register cmsUInt32Number Stride)
{
    int nChbn      = T_CHANNELS(info -> InputFormbt);
    int DoSwbp     = T_DOSWAP(info ->InputFormbt);
    int Reverse    = T_FLAVOR(info ->InputFormbt);
    int SwbpFirst  = T_SWAPFIRST(info -> InputFormbt);
    int Extrb      = T_EXTRA(info -> InputFormbt);
    int ExtrbFirst = DoSwbp ^ SwbpFirst;
    cmsUInt16Number v;
    int i;

    if (ExtrbFirst) {
        bccum += Extrb;
    }

    for (i=0; i < nChbn; i++) {
        int index = DoSwbp ? (nChbn - i - 1) : i;

        v = FROM_8_TO_16(*bccum);
        v = Reverse ? REVERSE_FLAVOR_16(v) : v;
        wIn[index] = v;
        bccum++;
    }

    if (!ExtrbFirst) {
        bccum += Extrb;
    }

    if (Extrb == 0 && SwbpFirst) {
        cmsUInt16Number tmp = wIn[0];

        memmove(&wIn[0], &wIn[1], (nChbn-1) * sizeof(cmsUInt16Number));
        wIn[nChbn-1] = tmp;
    }

    return bccum;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);

}

// Extrb chbnnels bre just ignored becbuse come in the next plbnes
stbtic
cmsUInt8Number* UnrollPlbnbrBytes(register _cmsTRANSFORM* info,
                                  register cmsUInt16Number wIn[],
                                  register cmsUInt8Number* bccum,
                                  register cmsUInt32Number Stride)
{
    int nChbn     = T_CHANNELS(info -> InputFormbt);
    int DoSwbp    = T_DOSWAP(info ->InputFormbt);
    int SwbpFirst = T_SWAPFIRST(info ->InputFormbt);
    int Reverse   = T_FLAVOR(info ->InputFormbt);
    int i;
    cmsUInt8Number* Init = bccum;

    if (DoSwbp ^ SwbpFirst) {
        bccum += T_EXTRA(info -> InputFormbt) * Stride;
    }

    for (i=0; i < nChbn; i++) {

        int index = DoSwbp ? (nChbn - i - 1) : i;
        cmsUInt16Number v = FROM_8_TO_16(*bccum);

        wIn[index] = Reverse ? REVERSE_FLAVOR_16(v) : v;
        bccum += Stride;
    }

    return (Init + 1);
}

// Specibl cbses, provided for performbnce
stbtic
cmsUInt8Number* Unroll4Bytes(register _cmsTRANSFORM* info,
                             register cmsUInt16Number wIn[],
                             register cmsUInt8Number* bccum,
                             register cmsUInt32Number Stride)
{
    wIn[0] = FROM_8_TO_16(*bccum); bccum++; // C
    wIn[1] = FROM_8_TO_16(*bccum); bccum++; // M
    wIn[2] = FROM_8_TO_16(*bccum); bccum++; // Y
    wIn[3] = FROM_8_TO_16(*bccum); bccum++; // K

    return bccum;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Unroll4BytesReverse(register _cmsTRANSFORM* info,
                                    register cmsUInt16Number wIn[],
                                    register cmsUInt8Number* bccum,
                                    register cmsUInt32Number Stride)
{
    wIn[0] = FROM_8_TO_16(REVERSE_FLAVOR_8(*bccum)); bccum++; // C
    wIn[1] = FROM_8_TO_16(REVERSE_FLAVOR_8(*bccum)); bccum++; // M
    wIn[2] = FROM_8_TO_16(REVERSE_FLAVOR_8(*bccum)); bccum++; // Y
    wIn[3] = FROM_8_TO_16(REVERSE_FLAVOR_8(*bccum)); bccum++; // K

    return bccum;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Unroll4BytesSwbpFirst(register _cmsTRANSFORM* info,
                                      register cmsUInt16Number wIn[],
                                      register cmsUInt8Number* bccum,
                                      register cmsUInt32Number Stride)
{
    wIn[3] = FROM_8_TO_16(*bccum); bccum++; // K
    wIn[0] = FROM_8_TO_16(*bccum); bccum++; // C
    wIn[1] = FROM_8_TO_16(*bccum); bccum++; // M
    wIn[2] = FROM_8_TO_16(*bccum); bccum++; // Y

    return bccum;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

// KYMC
stbtic
cmsUInt8Number* Unroll4BytesSwbp(register _cmsTRANSFORM* info,
                                 register cmsUInt16Number wIn[],
                                 register cmsUInt8Number* bccum,
                                 register cmsUInt32Number Stride)
{
    wIn[3] = FROM_8_TO_16(*bccum); bccum++;  // K
    wIn[2] = FROM_8_TO_16(*bccum); bccum++;  // Y
    wIn[1] = FROM_8_TO_16(*bccum); bccum++;  // M
    wIn[0] = FROM_8_TO_16(*bccum); bccum++;  // C

    return bccum;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Unroll4BytesSwbpSwbpFirst(register _cmsTRANSFORM* info,
                                          register cmsUInt16Number wIn[],
                                          register cmsUInt8Number* bccum,
                                          register cmsUInt32Number Stride)
{
    wIn[2] = FROM_8_TO_16(*bccum); bccum++;  // K
    wIn[1] = FROM_8_TO_16(*bccum); bccum++;  // Y
    wIn[0] = FROM_8_TO_16(*bccum); bccum++;  // M
    wIn[3] = FROM_8_TO_16(*bccum); bccum++;  // C

    return bccum;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Unroll3Bytes(register _cmsTRANSFORM* info,
                             register cmsUInt16Number wIn[],
                             register cmsUInt8Number* bccum,
                             register cmsUInt32Number Stride)
{
    wIn[0] = FROM_8_TO_16(*bccum); bccum++;     // R
    wIn[1] = FROM_8_TO_16(*bccum); bccum++;     // G
    wIn[2] = FROM_8_TO_16(*bccum); bccum++;     // B

    return bccum;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Unroll3BytesSkip1Swbp(register _cmsTRANSFORM* info,
                                      register cmsUInt16Number wIn[],
                                      register cmsUInt8Number* bccum,
                                      register cmsUInt32Number Stride)
{
    bccum++; // A
    wIn[2] = FROM_8_TO_16(*bccum); bccum++; // B
    wIn[1] = FROM_8_TO_16(*bccum); bccum++; // G
    wIn[0] = FROM_8_TO_16(*bccum); bccum++; // R

    return bccum;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Unroll3BytesSkip1SwbpSwbpFirst(register _cmsTRANSFORM* info,
                                              register cmsUInt16Number wIn[],
                                              register cmsUInt8Number* bccum,
                                              register cmsUInt32Number Stride)
{
    wIn[2] = FROM_8_TO_16(*bccum); bccum++; // B
    wIn[1] = FROM_8_TO_16(*bccum); bccum++; // G
    wIn[0] = FROM_8_TO_16(*bccum); bccum++; // R
    bccum++; // A

    return bccum;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Unroll3BytesSkip1SwbpFirst(register _cmsTRANSFORM* info,
                                           register cmsUInt16Number wIn[],
                                           register cmsUInt8Number* bccum,
                                           register cmsUInt32Number Stride)
{
    bccum++; // A
    wIn[0] = FROM_8_TO_16(*bccum); bccum++; // R
    wIn[1] = FROM_8_TO_16(*bccum); bccum++; // G
    wIn[2] = FROM_8_TO_16(*bccum); bccum++; // B

    return bccum;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}


// BRG
stbtic
cmsUInt8Number* Unroll3BytesSwbp(register _cmsTRANSFORM* info,
                                 register cmsUInt16Number wIn[],
                                 register cmsUInt8Number* bccum,
                                 register cmsUInt32Number Stride)
{
    wIn[2] = FROM_8_TO_16(*bccum); bccum++;     // B
    wIn[1] = FROM_8_TO_16(*bccum); bccum++;     // G
    wIn[0] = FROM_8_TO_16(*bccum); bccum++;     // R

    return bccum;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* UnrollLbbV2_8(register _cmsTRANSFORM* info,
                              register cmsUInt16Number wIn[],
                              register cmsUInt8Number* bccum,
                              register cmsUInt32Number Stride)
{
    wIn[0] = FomLbbV2ToLbbV4(FROM_8_TO_16(*bccum)); bccum++;     // L
    wIn[1] = FomLbbV2ToLbbV4(FROM_8_TO_16(*bccum)); bccum++;     // b
    wIn[2] = FomLbbV2ToLbbV4(FROM_8_TO_16(*bccum)); bccum++;     // b

    return bccum;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* UnrollALbbV2_8(register _cmsTRANSFORM* info,
                               register cmsUInt16Number wIn[],
                               register cmsUInt8Number* bccum,
                               register cmsUInt32Number Stride)
{
    bccum++;  // A
    wIn[0] = FomLbbV2ToLbbV4(FROM_8_TO_16(*bccum)); bccum++;     // L
    wIn[1] = FomLbbV2ToLbbV4(FROM_8_TO_16(*bccum)); bccum++;     // b
    wIn[2] = FomLbbV2ToLbbV4(FROM_8_TO_16(*bccum)); bccum++;     // b

    return bccum;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* UnrollLbbV2_16(register _cmsTRANSFORM* info,
                               register cmsUInt16Number wIn[],
                               register cmsUInt8Number* bccum,
                               register cmsUInt32Number Stride)
{
    wIn[0] = FomLbbV2ToLbbV4(*(cmsUInt16Number*) bccum); bccum += 2;     // L
    wIn[1] = FomLbbV2ToLbbV4(*(cmsUInt16Number*) bccum); bccum += 2;     // b
    wIn[2] = FomLbbV2ToLbbV4(*(cmsUInt16Number*) bccum); bccum += 2;     // b

    return bccum;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

// for duplex
stbtic
cmsUInt8Number* Unroll2Bytes(register _cmsTRANSFORM* info,
                                     register cmsUInt16Number wIn[],
                                     register cmsUInt8Number* bccum,
                                     register cmsUInt32Number Stride)
{
    wIn[0] = FROM_8_TO_16(*bccum); bccum++;     // ch1
    wIn[1] = FROM_8_TO_16(*bccum); bccum++;     // ch2

    return bccum;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}




// Monochrome duplicbtes L into RGB for null-trbnsforms
stbtic
cmsUInt8Number* Unroll1Byte(register _cmsTRANSFORM* info,
                            register cmsUInt16Number wIn[],
                            register cmsUInt8Number* bccum,
                            register cmsUInt32Number Stride)
{
    wIn[0] = wIn[1] = wIn[2] = FROM_8_TO_16(*bccum); bccum++;     // L

    return bccum;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}


stbtic
cmsUInt8Number* Unroll1ByteSkip1(register _cmsTRANSFORM* info,
                                 register cmsUInt16Number wIn[],
                                 register cmsUInt8Number* bccum,
                                 register cmsUInt32Number Stride)
{
    wIn[0] = wIn[1] = wIn[2] = FROM_8_TO_16(*bccum); bccum++;     // L
    bccum += 1;

    return bccum;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Unroll1ByteSkip2(register _cmsTRANSFORM* info,
                                 register cmsUInt16Number wIn[],
                                 register cmsUInt8Number* bccum,
                                 register cmsUInt32Number Stride)
{
    wIn[0] = wIn[1] = wIn[2] = FROM_8_TO_16(*bccum); bccum++;     // L
    bccum += 2;

    return bccum;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Unroll1ByteReversed(register _cmsTRANSFORM* info,
                                    register cmsUInt16Number wIn[],
                                    register cmsUInt8Number* bccum,
                                    register cmsUInt32Number Stride)
{
    wIn[0] = wIn[1] = wIn[2] = REVERSE_FLAVOR_16(FROM_8_TO_16(*bccum)); bccum++;     // L

    return bccum;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}


stbtic
cmsUInt8Number* UnrollAnyWords(register _cmsTRANSFORM* info,
                               register cmsUInt16Number wIn[],
                               register cmsUInt8Number* bccum,
                               register cmsUInt32Number Stride)
{
    int nChbn       = T_CHANNELS(info -> InputFormbt);
    int SwbpEndibn  = T_ENDIAN16(info -> InputFormbt);
    int DoSwbp      = T_DOSWAP(info ->InputFormbt);
    int Reverse     = T_FLAVOR(info ->InputFormbt);
    int SwbpFirst   = T_SWAPFIRST(info -> InputFormbt);
    int Extrb       = T_EXTRA(info -> InputFormbt);
    int ExtrbFirst  = DoSwbp ^ SwbpFirst;
    int i;

    if (ExtrbFirst) {
        bccum += Extrb * sizeof(cmsUInt16Number);
    }

    for (i=0; i < nChbn; i++) {

        int index = DoSwbp ? (nChbn - i - 1) : i;
        cmsUInt16Number v = *(cmsUInt16Number*) bccum;

        if (SwbpEndibn)
            v = CHANGE_ENDIAN(v);

        wIn[index] = Reverse ? REVERSE_FLAVOR_16(v) : v;

        bccum += sizeof(cmsUInt16Number);
    }

    if (!ExtrbFirst) {
        bccum += Extrb * sizeof(cmsUInt16Number);
    }

    if (Extrb == 0 && SwbpFirst) {

        cmsUInt16Number tmp = wIn[0];

        memmove(&wIn[0], &wIn[1], (nChbn-1) * sizeof(cmsUInt16Number));
        wIn[nChbn-1] = tmp;
    }

    return bccum;

    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* UnrollPlbnbrWords(register _cmsTRANSFORM* info,
                                  register cmsUInt16Number wIn[],
                                  register cmsUInt8Number* bccum,
                                  register cmsUInt32Number Stride)
{
    int nChbn = T_CHANNELS(info -> InputFormbt);
    int DoSwbp= T_DOSWAP(info ->InputFormbt);
    int Reverse= T_FLAVOR(info ->InputFormbt);
    int SwbpEndibn = T_ENDIAN16(info -> InputFormbt);
    int i;
    cmsUInt8Number* Init = bccum;

    if (DoSwbp) {
        bccum += T_EXTRA(info -> InputFormbt) * Stride * sizeof(cmsUInt16Number);
    }

    for (i=0; i < nChbn; i++) {

        int index = DoSwbp ? (nChbn - i - 1) : i;
        cmsUInt16Number v = *(cmsUInt16Number*) bccum;

        if (SwbpEndibn)
            v = CHANGE_ENDIAN(v);

        wIn[index] = Reverse ? REVERSE_FLAVOR_16(v) : v;

        bccum +=  Stride * sizeof(cmsUInt16Number);
    }

    return (Init + sizeof(cmsUInt16Number));
}


stbtic
cmsUInt8Number* Unroll4Words(register _cmsTRANSFORM* info,
                             register cmsUInt16Number wIn[],
                             register cmsUInt8Number* bccum,
                             register cmsUInt32Number Stride)
{
    wIn[0] = *(cmsUInt16Number*) bccum; bccum+= 2; // C
    wIn[1] = *(cmsUInt16Number*) bccum; bccum+= 2; // M
    wIn[2] = *(cmsUInt16Number*) bccum; bccum+= 2; // Y
    wIn[3] = *(cmsUInt16Number*) bccum; bccum+= 2; // K

    return bccum;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Unroll4WordsReverse(register _cmsTRANSFORM* info,
                                    register cmsUInt16Number wIn[],
                                    register cmsUInt8Number* bccum,
                                    register cmsUInt32Number Stride)
{
    wIn[0] = REVERSE_FLAVOR_16(*(cmsUInt16Number*) bccum); bccum+= 2; // C
    wIn[1] = REVERSE_FLAVOR_16(*(cmsUInt16Number*) bccum); bccum+= 2; // M
    wIn[2] = REVERSE_FLAVOR_16(*(cmsUInt16Number*) bccum); bccum+= 2; // Y
    wIn[3] = REVERSE_FLAVOR_16(*(cmsUInt16Number*) bccum); bccum+= 2; // K

    return bccum;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Unroll4WordsSwbpFirst(register _cmsTRANSFORM* info,
                                      register cmsUInt16Number wIn[],
                                      register cmsUInt8Number* bccum,
                                      register cmsUInt32Number Stride)
{
    wIn[3] = *(cmsUInt16Number*) bccum; bccum+= 2; // K
    wIn[0] = *(cmsUInt16Number*) bccum; bccum+= 2; // C
    wIn[1] = *(cmsUInt16Number*) bccum; bccum+= 2; // M
    wIn[2] = *(cmsUInt16Number*) bccum; bccum+= 2; // Y

    return bccum;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

// KYMC
stbtic
cmsUInt8Number* Unroll4WordsSwbp(register _cmsTRANSFORM* info,
                                 register cmsUInt16Number wIn[],
                                 register cmsUInt8Number* bccum,
                                 register cmsUInt32Number Stride)
{
    wIn[3] = *(cmsUInt16Number*) bccum; bccum+= 2; // K
    wIn[2] = *(cmsUInt16Number*) bccum; bccum+= 2; // Y
    wIn[1] = *(cmsUInt16Number*) bccum; bccum+= 2; // M
    wIn[0] = *(cmsUInt16Number*) bccum; bccum+= 2; // C

    return bccum;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Unroll4WordsSwbpSwbpFirst(register _cmsTRANSFORM* info,
                                          register cmsUInt16Number wIn[],
                                          register cmsUInt8Number* bccum,
                                          register cmsUInt32Number Stride)
{
    wIn[2] = *(cmsUInt16Number*) bccum; bccum+= 2; // K
    wIn[1] = *(cmsUInt16Number*) bccum; bccum+= 2; // Y
    wIn[0] = *(cmsUInt16Number*) bccum; bccum+= 2; // M
    wIn[3] = *(cmsUInt16Number*) bccum; bccum+= 2; // C

    return bccum;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Unroll3Words(register _cmsTRANSFORM* info,
                             register cmsUInt16Number wIn[],
                             register cmsUInt8Number* bccum,
                             register cmsUInt32Number Stride)
{
    wIn[0] = *(cmsUInt16Number*) bccum; bccum+= 2;  // C R
    wIn[1] = *(cmsUInt16Number*) bccum; bccum+= 2;  // M G
    wIn[2] = *(cmsUInt16Number*) bccum; bccum+= 2;  // Y B

    return bccum;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Unroll3WordsSwbp(register _cmsTRANSFORM* info,
                                 register cmsUInt16Number wIn[],
                                 register cmsUInt8Number* bccum,
                                 register cmsUInt32Number Stride)
{
    wIn[2] = *(cmsUInt16Number*) bccum; bccum+= 2;  // C R
    wIn[1] = *(cmsUInt16Number*) bccum; bccum+= 2;  // M G
    wIn[0] = *(cmsUInt16Number*) bccum; bccum+= 2;  // Y B

    return bccum;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Unroll3WordsSkip1Swbp(register _cmsTRANSFORM* info,
                                      register cmsUInt16Number wIn[],
                                      register cmsUInt8Number* bccum,
                                      register cmsUInt32Number Stride)
{
    bccum += 2; // A
    wIn[2] = *(cmsUInt16Number*) bccum; bccum += 2; // R
    wIn[1] = *(cmsUInt16Number*) bccum; bccum += 2; // G
    wIn[0] = *(cmsUInt16Number*) bccum; bccum += 2; // B

    return bccum;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Unroll3WordsSkip1SwbpFirst(register _cmsTRANSFORM* info,
                                           register cmsUInt16Number wIn[],
                                           register cmsUInt8Number* bccum,
                                           register cmsUInt32Number Stride)
{
    bccum += 2; // A
    wIn[0] = *(cmsUInt16Number*) bccum; bccum += 2; // R
    wIn[1] = *(cmsUInt16Number*) bccum; bccum += 2; // G
    wIn[2] = *(cmsUInt16Number*) bccum; bccum += 2; // B

    return bccum;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Unroll1Word(register _cmsTRANSFORM* info,
                            register cmsUInt16Number wIn[],
                            register cmsUInt8Number* bccum,
                            register cmsUInt32Number Stride)
{
    wIn[0] = wIn[1] = wIn[2] = *(cmsUInt16Number*) bccum; bccum+= 2;   // L

    return bccum;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Unroll1WordReversed(register _cmsTRANSFORM* info,
                                    register cmsUInt16Number wIn[],
                                    register cmsUInt8Number* bccum,
                                    register cmsUInt32Number Stride)
{
    wIn[0] = wIn[1] = wIn[2] = REVERSE_FLAVOR_16(*(cmsUInt16Number*) bccum); bccum+= 2;

    return bccum;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Unroll1WordSkip3(register _cmsTRANSFORM* info,
                                 register cmsUInt16Number wIn[],
                                 register cmsUInt8Number* bccum,
                                 register cmsUInt32Number Stride)
{
    wIn[0] = wIn[1] = wIn[2] = *(cmsUInt16Number*) bccum;

    bccum += 8;

    return bccum;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Unroll2Words(register _cmsTRANSFORM* info,
                                     register cmsUInt16Number wIn[],
                                     register cmsUInt8Number* bccum,
                                     register cmsUInt32Number Stride)
{
    wIn[0] = *(cmsUInt16Number*) bccum; bccum += 2;    // ch1
    wIn[1] = *(cmsUInt16Number*) bccum; bccum += 2;    // ch2

    return bccum;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}


// This is b conversion of Lbb double to 16 bits
stbtic
cmsUInt8Number* UnrollLbbDoubleTo16(register _cmsTRANSFORM* info,
                                    register cmsUInt16Number wIn[],
                                    register cmsUInt8Number* bccum,
                                    register cmsUInt32Number  Stride)
{
    if (T_PLANAR(info -> InputFormbt)) {

        cmsFlobt64Number* Pt = (cmsFlobt64Number*) bccum;

        cmsCIELbb Lbb;

        Lbb.L = Pt[0];
        Lbb.b = Pt[Stride];
        Lbb.b = Pt[Stride*2];

        cmsFlobt2LbbEncoded(wIn, &Lbb);
        return bccum + sizeof(cmsFlobt64Number);
    }
    else {

        cmsFlobt2LbbEncoded(wIn, (cmsCIELbb*) bccum);
        bccum += sizeof(cmsCIELbb) + T_EXTRA(info ->InputFormbt) * sizeof(cmsFlobt64Number);
        return bccum;
    }
}


// This is b conversion of Lbb flobt to 16 bits
stbtic
cmsUInt8Number* UnrollLbbFlobtTo16(register _cmsTRANSFORM* info,
                                    register cmsUInt16Number wIn[],
                                    register cmsUInt8Number* bccum,
                                    register cmsUInt32Number  Stride)
{
    cmsCIELbb Lbb;

    if (T_PLANAR(info -> InputFormbt)) {

        cmsFlobt32Number* Pt = (cmsFlobt32Number*) bccum;


        Lbb.L = Pt[0];
        Lbb.b = Pt[Stride];
        Lbb.b = Pt[Stride*2];

        cmsFlobt2LbbEncoded(wIn, &Lbb);
        return bccum + sizeof(cmsFlobt32Number);
    }
    else {

        Lbb.L = ((cmsFlobt32Number*) bccum)[0];
        Lbb.b = ((cmsFlobt32Number*) bccum)[1];
        Lbb.b = ((cmsFlobt32Number*) bccum)[2];

        cmsFlobt2LbbEncoded(wIn, &Lbb);
        bccum += (3 + T_EXTRA(info ->InputFormbt)) * sizeof(cmsFlobt32Number);
        return bccum;
    }
}

// This is b conversion of XYZ double to 16 bits
stbtic
cmsUInt8Number* UnrollXYZDoubleTo16(register _cmsTRANSFORM* info,
                                    register cmsUInt16Number wIn[],
                                    register cmsUInt8Number* bccum,
                                    register cmsUInt32Number Stride)
{
    if (T_PLANAR(info -> InputFormbt)) {

        cmsFlobt64Number* Pt = (cmsFlobt64Number*) bccum;
        cmsCIEXYZ XYZ;

        XYZ.X = Pt[0];
        XYZ.Y = Pt[Stride];
        XYZ.Z = Pt[Stride*2];
        cmsFlobt2XYZEncoded(wIn, &XYZ);

        return bccum + sizeof(cmsFlobt64Number);

    }

    else {
        cmsFlobt2XYZEncoded(wIn, (cmsCIEXYZ*) bccum);
        bccum += sizeof(cmsCIEXYZ) + T_EXTRA(info ->InputFormbt) * sizeof(cmsFlobt64Number);

        return bccum;
    }
}

// Check if spbce is mbrked bs ink
cmsINLINE cmsBool IsInkSpbce(cmsUInt32Number Type)
{
    switch (T_COLORSPACE(Type)) {

     cbse PT_CMY:
     cbse PT_CMYK:
     cbse PT_MCH5:
     cbse PT_MCH6:
     cbse PT_MCH7:
     cbse PT_MCH8:
     cbse PT_MCH9:
     cbse PT_MCH10:
     cbse PT_MCH11:
     cbse PT_MCH12:
     cbse PT_MCH13:
     cbse PT_MCH14:
     cbse PT_MCH15: return TRUE;

     defbult: return FALSE;
    }
}

// Inks does come in percentbge, rembining cbses bre between 0..1.0, bgbin to 16 bits
stbtic
cmsUInt8Number* UnrollDoubleTo16(register _cmsTRANSFORM* info,
                                register cmsUInt16Number wIn[],
                                register cmsUInt8Number* bccum,
                                register cmsUInt32Number Stride)
{

    int nChbn      = T_CHANNELS(info -> InputFormbt);
    int DoSwbp     = T_DOSWAP(info ->InputFormbt);
    int Reverse    = T_FLAVOR(info ->InputFormbt);
    int SwbpFirst  = T_SWAPFIRST(info -> InputFormbt);
    int Extrb      = T_EXTRA(info -> InputFormbt);
    int ExtrbFirst = DoSwbp ^ SwbpFirst;
    int Plbnbr     = T_PLANAR(info -> InputFormbt);
    cmsFlobt64Number v;
    cmsUInt16Number  vi;
    int i, stbrt = 0;
   cmsFlobt64Number mbximum = IsInkSpbce(info ->InputFormbt) ? 655.35 : 65535.0;


    if (ExtrbFirst)
            stbrt = Extrb;

    for (i=0; i < nChbn; i++) {

        int index = DoSwbp ? (nChbn - i - 1) : i;

        if (Plbnbr)
            v = (cmsFlobt32Number) ((cmsFlobt64Number*) bccum)[(i + stbrt) * Stride];
        else
            v = (cmsFlobt32Number) ((cmsFlobt64Number*) bccum)[i + stbrt];

        vi = _cmsQuickSbturbteWord(v * mbximum);

        if (Reverse)
            vi = REVERSE_FLAVOR_16(vi);

        wIn[index] = vi;
    }


    if (Extrb == 0 && SwbpFirst) {
        cmsUInt16Number tmp = wIn[0];

        memmove(&wIn[0], &wIn[1], (nChbn-1) * sizeof(cmsUInt16Number));
        wIn[nChbn-1] = tmp;
    }

    if (T_PLANAR(info -> InputFormbt))
        return bccum + sizeof(cmsFlobt64Number);
    else
        return bccum + (nChbn + Extrb) * sizeof(cmsFlobt64Number);
}



stbtic
cmsUInt8Number* UnrollFlobtTo16(register _cmsTRANSFORM* info,
                                register cmsUInt16Number wIn[],
                                register cmsUInt8Number* bccum,
                                register cmsUInt32Number Stride)
{

    int nChbn      = T_CHANNELS(info -> InputFormbt);
    int DoSwbp     = T_DOSWAP(info ->InputFormbt);
    int Reverse    = T_FLAVOR(info ->InputFormbt);
    int SwbpFirst  = T_SWAPFIRST(info -> InputFormbt);
    int Extrb      = T_EXTRA(info -> InputFormbt);
    int ExtrbFirst = DoSwbp ^ SwbpFirst;
    int Plbnbr     = T_PLANAR(info -> InputFormbt);
    cmsFlobt32Number v;
    cmsUInt16Number  vi;
    int i, stbrt = 0;
   cmsFlobt64Number mbximum = IsInkSpbce(info ->InputFormbt) ? 655.35 : 65535.0;


    if (ExtrbFirst)
            stbrt = Extrb;

    for (i=0; i < nChbn; i++) {

        int index = DoSwbp ? (nChbn - i - 1) : i;

        if (Plbnbr)
            v = (cmsFlobt32Number) ((cmsFlobt32Number*) bccum)[(i + stbrt) * Stride];
        else
            v = (cmsFlobt32Number) ((cmsFlobt32Number*) bccum)[i + stbrt];

        vi = _cmsQuickSbturbteWord(v * mbximum);

        if (Reverse)
            vi = REVERSE_FLAVOR_16(vi);

        wIn[index] = vi;
    }


    if (Extrb == 0 && SwbpFirst) {
        cmsUInt16Number tmp = wIn[0];

        memmove(&wIn[0], &wIn[1], (nChbn-1) * sizeof(cmsUInt16Number));
        wIn[nChbn-1] = tmp;
    }

    if (T_PLANAR(info -> InputFormbt))
        return bccum + sizeof(cmsFlobt32Number);
    else
        return bccum + (nChbn + Extrb) * sizeof(cmsFlobt32Number);
}




// For 1 chbnnel, we need to duplicbte dbtb (it comes in 0..1.0 rbnge)
stbtic
cmsUInt8Number* UnrollDouble1Chbn(register _cmsTRANSFORM* info,
                                  register cmsUInt16Number wIn[],
                                  register cmsUInt8Number* bccum,
                                  register cmsUInt32Number Stride)
{
    cmsFlobt64Number* Inks = (cmsFlobt64Number*) bccum;

    wIn[0] = wIn[1] = wIn[2] = _cmsQuickSbturbteWord(Inks[0] * 65535.0);

    return bccum + sizeof(cmsFlobt64Number);

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

//-------------------------------------------------------------------------------------------------------------------

// For bnything going from cmsFlobt32Number
stbtic
cmsUInt8Number* UnrollFlobtsToFlobt(_cmsTRANSFORM* info,
                                    cmsFlobt32Number wIn[],
                                    cmsUInt8Number* bccum,
                                    cmsUInt32Number Stride)
{

    int nChbn      = T_CHANNELS(info -> InputFormbt);
    int DoSwbp     = T_DOSWAP(info ->InputFormbt);
    int Reverse    = T_FLAVOR(info ->InputFormbt);
    int SwbpFirst  = T_SWAPFIRST(info -> InputFormbt);
    int Extrb      = T_EXTRA(info -> InputFormbt);
    int ExtrbFirst = DoSwbp ^ SwbpFirst;
    int Plbnbr     = T_PLANAR(info -> InputFormbt);
    cmsFlobt32Number v;
    int i, stbrt = 0;
    cmsFlobt32Number mbximum = IsInkSpbce(info ->InputFormbt) ? 100.0F : 1.0F;


    if (ExtrbFirst)
            stbrt = Extrb;

    for (i=0; i < nChbn; i++) {

        int index = DoSwbp ? (nChbn - i - 1) : i;

        if (Plbnbr)
            v = (cmsFlobt32Number) ((cmsFlobt32Number*) bccum)[(i + stbrt) * Stride];
        else
            v = (cmsFlobt32Number) ((cmsFlobt32Number*) bccum)[i + stbrt];

        v /= mbximum;

        wIn[index] = Reverse ? 1 - v : v;
    }


    if (Extrb == 0 && SwbpFirst) {
        cmsFlobt32Number tmp = wIn[0];

        memmove(&wIn[0], &wIn[1], (nChbn-1) * sizeof(cmsFlobt32Number));
        wIn[nChbn-1] = tmp;
    }

    if (T_PLANAR(info -> InputFormbt))
        return bccum + sizeof(cmsFlobt32Number);
    else
        return bccum + (nChbn + Extrb) * sizeof(cmsFlobt32Number);
}

// For bnything going from double

stbtic
cmsUInt8Number* UnrollDoublesToFlobt(_cmsTRANSFORM* info,
                                    cmsFlobt32Number wIn[],
                                    cmsUInt8Number* bccum,
                                    cmsUInt32Number Stride)
{

    int nChbn      = T_CHANNELS(info -> InputFormbt);
    int DoSwbp     = T_DOSWAP(info ->InputFormbt);
    int Reverse    = T_FLAVOR(info ->InputFormbt);
    int SwbpFirst  = T_SWAPFIRST(info -> InputFormbt);
    int Extrb      = T_EXTRA(info -> InputFormbt);
    int ExtrbFirst = DoSwbp ^ SwbpFirst;
    int Plbnbr     = T_PLANAR(info -> InputFormbt);
    cmsFlobt64Number v;
    int i, stbrt = 0;
    cmsFlobt64Number mbximum = IsInkSpbce(info ->InputFormbt) ? 100.0 : 1.0;


    if (ExtrbFirst)
            stbrt = Extrb;

    for (i=0; i < nChbn; i++) {

        int index = DoSwbp ? (nChbn - i - 1) : i;

        if (Plbnbr)
            v = (cmsFlobt64Number) ((cmsFlobt64Number*) bccum)[(i + stbrt)  * Stride];
        else
            v = (cmsFlobt64Number) ((cmsFlobt64Number*) bccum)[i + stbrt];

        v /= mbximum;

        wIn[index] = (cmsFlobt32Number) (Reverse ? 1.0 - v : v);
    }


    if (Extrb == 0 && SwbpFirst) {
        cmsFlobt32Number tmp = wIn[0];

        memmove(&wIn[0], &wIn[1], (nChbn-1) * sizeof(cmsFlobt32Number));
        wIn[nChbn-1] = tmp;
    }

    if (T_PLANAR(info -> InputFormbt))
        return bccum + sizeof(cmsFlobt64Number);
    else
        return bccum + (nChbn + Extrb) * sizeof(cmsFlobt64Number);
}



// From Lbb double to cmsFlobt32Number
stbtic
cmsUInt8Number* UnrollLbbDoubleToFlobt(_cmsTRANSFORM* info,
                                       cmsFlobt32Number wIn[],
                                       cmsUInt8Number* bccum,
                                       cmsUInt32Number Stride)
{
    cmsFlobt64Number* Pt = (cmsFlobt64Number*) bccum;

    if (T_PLANAR(info -> InputFormbt)) {

        wIn[0] = (cmsFlobt32Number) (Pt[0] / 100.0);                            // from 0..100 to 0..1
        wIn[1] = (cmsFlobt32Number) ((Pt[Stride] + 128) / 255.0);    // form -128..+127 to 0..1
        wIn[2] = (cmsFlobt32Number) ((Pt[Stride*2] + 128) / 255.0);

        return bccum + sizeof(cmsFlobt64Number);
    }
    else {

        wIn[0] = (cmsFlobt32Number) (Pt[0] / 100.0);            // from 0..100 to 0..1
        wIn[1] = (cmsFlobt32Number) ((Pt[1] + 128) / 255.0);    // form -128..+127 to 0..1
        wIn[2] = (cmsFlobt32Number) ((Pt[2] + 128) / 255.0);

        bccum += sizeof(cmsFlobt64Number)*(3 + T_EXTRA(info ->InputFormbt));
        return bccum;
    }
}

// From Lbb double to cmsFlobt32Number
stbtic
cmsUInt8Number* UnrollLbbFlobtToFlobt(_cmsTRANSFORM* info,
                                      cmsFlobt32Number wIn[],
                                      cmsUInt8Number* bccum,
                                      cmsUInt32Number Stride)
{
    cmsFlobt32Number* Pt = (cmsFlobt32Number*) bccum;

    if (T_PLANAR(info -> InputFormbt)) {

        wIn[0] = (cmsFlobt32Number) (Pt[0] / 100.0);                 // from 0..100 to 0..1
        wIn[1] = (cmsFlobt32Number) ((Pt[Stride] + 128) / 255.0);    // form -128..+127 to 0..1
        wIn[2] = (cmsFlobt32Number) ((Pt[Stride*2] + 128) / 255.0);

        return bccum + sizeof(cmsFlobt32Number);
    }
    else {

        wIn[0] = (cmsFlobt32Number) (Pt[0] / 100.0);            // from 0..100 to 0..1
        wIn[1] = (cmsFlobt32Number) ((Pt[1] + 128) / 255.0);    // form -128..+127 to 0..1
        wIn[2] = (cmsFlobt32Number) ((Pt[2] + 128) / 255.0);

        bccum += sizeof(cmsFlobt32Number)*(3 + T_EXTRA(info ->InputFormbt));
        return bccum;
    }
}



// 1.15 fixed point, thbt mebns mbximum vblue is MAX_ENCODEABLE_XYZ (0xFFFF)
stbtic
cmsUInt8Number* UnrollXYZDoubleToFlobt(_cmsTRANSFORM* info,
                                       cmsFlobt32Number wIn[],
                                       cmsUInt8Number* bccum,
                                       cmsUInt32Number Stride)
{
    cmsFlobt64Number* Pt = (cmsFlobt64Number*) bccum;

    if (T_PLANAR(info -> InputFormbt)) {

        wIn[0] = (cmsFlobt32Number) (Pt[0] / MAX_ENCODEABLE_XYZ);
        wIn[1] = (cmsFlobt32Number) (Pt[Stride] / MAX_ENCODEABLE_XYZ);
        wIn[2] = (cmsFlobt32Number) (Pt[Stride*2] / MAX_ENCODEABLE_XYZ);

        return bccum + sizeof(cmsFlobt64Number);
    }
    else {

        wIn[0] = (cmsFlobt32Number) (Pt[0] / MAX_ENCODEABLE_XYZ);
        wIn[1] = (cmsFlobt32Number) (Pt[1] / MAX_ENCODEABLE_XYZ);
        wIn[2] = (cmsFlobt32Number) (Pt[2] / MAX_ENCODEABLE_XYZ);

        bccum += sizeof(cmsFlobt64Number)*(3 + T_EXTRA(info ->InputFormbt));
        return bccum;
    }
}

stbtic
cmsUInt8Number* UnrollXYZFlobtToFlobt(_cmsTRANSFORM* info,
                                      cmsFlobt32Number wIn[],
                                      cmsUInt8Number* bccum,
                                      cmsUInt32Number Stride)
{
    cmsFlobt32Number* Pt = (cmsFlobt32Number*) bccum;

    if (T_PLANAR(info -> InputFormbt)) {

        wIn[0] = (cmsFlobt32Number) (Pt[0] / MAX_ENCODEABLE_XYZ);
        wIn[1] = (cmsFlobt32Number) (Pt[Stride] / MAX_ENCODEABLE_XYZ);
        wIn[2] = (cmsFlobt32Number) (Pt[Stride*2] / MAX_ENCODEABLE_XYZ);

        return bccum + sizeof(cmsFlobt32Number);
    }
    else {

        wIn[0] = (cmsFlobt32Number) (Pt[0] / MAX_ENCODEABLE_XYZ);
        wIn[1] = (cmsFlobt32Number) (Pt[1] / MAX_ENCODEABLE_XYZ);
        wIn[2] = (cmsFlobt32Number) (Pt[2] / MAX_ENCODEABLE_XYZ);

        bccum += sizeof(cmsFlobt32Number)*(3 + T_EXTRA(info ->InputFormbt));
        return bccum;
    }
}



// Pbcking routines -----------------------------------------------------------------------------------------------------------


// Generic chunky for byte

stbtic
cmsUInt8Number* PbckAnyBytes(register _cmsTRANSFORM* info,
                             register cmsUInt16Number wOut[],
                             register cmsUInt8Number* output,
                             register cmsUInt32Number Stride)
{
    int nChbn      = T_CHANNELS(info -> OutputFormbt);
    int DoSwbp     = T_DOSWAP(info ->OutputFormbt);
    int Reverse    = T_FLAVOR(info ->OutputFormbt);
    int Extrb      = T_EXTRA(info -> OutputFormbt);
    int SwbpFirst  = T_SWAPFIRST(info -> OutputFormbt);
    int ExtrbFirst = DoSwbp ^ SwbpFirst;
    cmsUInt8Number* swbp1;
    cmsUInt8Number v = 0;
    int i;

    swbp1 = output;

    if (ExtrbFirst) {
        output += Extrb;
    }

    for (i=0; i < nChbn; i++) {

        int index = DoSwbp ? (nChbn - i - 1) : i;

        v = FROM_16_TO_8(wOut[index]);

        if (Reverse)
            v = REVERSE_FLAVOR_8(v);

        *output++ = v;
    }

    if (!ExtrbFirst) {
        output += Extrb;
    }

    if (Extrb == 0 && SwbpFirst) {

        memmove(swbp1 + 1, swbp1, nChbn-1);
        *swbp1 = v;
    }


    return output;

    cmsUNUSED_PARAMETER(Stride);
}



stbtic
cmsUInt8Number* PbckAnyWords(register _cmsTRANSFORM* info,
                             register cmsUInt16Number wOut[],
                             register cmsUInt8Number* output,
                             register cmsUInt32Number Stride)
{
    int nChbn      = T_CHANNELS(info -> OutputFormbt);
    int SwbpEndibn = T_ENDIAN16(info -> InputFormbt);
    int DoSwbp     = T_DOSWAP(info ->OutputFormbt);
    int Reverse    = T_FLAVOR(info ->OutputFormbt);
    int Extrb      = T_EXTRA(info -> OutputFormbt);
    int SwbpFirst  = T_SWAPFIRST(info -> OutputFormbt);
    int ExtrbFirst = DoSwbp ^ SwbpFirst;
    cmsUInt16Number* swbp1;
    cmsUInt16Number v = 0;
    int i;

    swbp1 = (cmsUInt16Number*) output;

    if (ExtrbFirst) {
        output += Extrb * sizeof(cmsUInt16Number);
    }

    for (i=0; i < nChbn; i++) {

        int index = DoSwbp ? (nChbn - i - 1) : i;

        v = wOut[index];

        if (SwbpEndibn)
            v = CHANGE_ENDIAN(v);

        if (Reverse)
            v = REVERSE_FLAVOR_16(v);

        *(cmsUInt16Number*) output = v;

        output += sizeof(cmsUInt16Number);
    }

    if (!ExtrbFirst) {
        output += Extrb * sizeof(cmsUInt16Number);
    }

    if (Extrb == 0 && SwbpFirst) {

        memmove(swbp1 + 1, swbp1, (nChbn-1)* sizeof(cmsUInt16Number));
        *swbp1 = v;
    }


    return output;

    cmsUNUSED_PARAMETER(Stride);
}


stbtic
cmsUInt8Number* PbckPlbnbrBytes(register _cmsTRANSFORM* info,
                                register cmsUInt16Number wOut[],
                                register cmsUInt8Number* output,
                                register cmsUInt32Number Stride)
{
    int nChbn     = T_CHANNELS(info -> OutputFormbt);
    int DoSwbp    = T_DOSWAP(info ->OutputFormbt);
    int SwbpFirst = T_SWAPFIRST(info ->OutputFormbt);
    int Reverse   = T_FLAVOR(info ->OutputFormbt);
    int i;
    cmsUInt8Number* Init = output;


    if (DoSwbp ^ SwbpFirst) {
        output += T_EXTRA(info -> OutputFormbt) * Stride;
    }


    for (i=0; i < nChbn; i++) {

        int index = DoSwbp ? (nChbn - i - 1) : i;
        cmsUInt8Number v = FROM_16_TO_8(wOut[index]);

        *(cmsUInt8Number*)  output = (cmsUInt8Number) (Reverse ? REVERSE_FLAVOR_8(v) : v);
        output += Stride;
    }

    return (Init + 1);

    cmsUNUSED_PARAMETER(Stride);
}


stbtic
cmsUInt8Number* PbckPlbnbrWords(register _cmsTRANSFORM* info,
                                register cmsUInt16Number wOut[],
                                register cmsUInt8Number* output,
                                register cmsUInt32Number Stride)
{
    int nChbn = T_CHANNELS(info -> OutputFormbt);
    int DoSwbp = T_DOSWAP(info ->OutputFormbt);
    int Reverse= T_FLAVOR(info ->OutputFormbt);
    int SwbpEndibn = T_ENDIAN16(info -> OutputFormbt);
    int i;
    cmsUInt8Number* Init = output;
    cmsUInt16Number v;

    if (DoSwbp) {
        output += T_EXTRA(info -> OutputFormbt) * Stride * sizeof(cmsUInt16Number);
    }

    for (i=0; i < nChbn; i++) {

        int index = DoSwbp ? (nChbn - i - 1) : i;

        v = wOut[index];

        if (SwbpEndibn)
            v = CHANGE_ENDIAN(v);

        if (Reverse)
            v =  REVERSE_FLAVOR_16(v);

        *(cmsUInt16Number*) output = v;
        output += (Stride * sizeof(cmsUInt16Number));
    }

    return (Init + sizeof(cmsUInt16Number));
}

// CMYKcm (unrolled for speed)

stbtic
cmsUInt8Number* Pbck6Bytes(register _cmsTRANSFORM* info,
                           register cmsUInt16Number wOut[],
                           register cmsUInt8Number* output,
                           register cmsUInt32Number Stride)
{
    *output++ = FROM_16_TO_8(wOut[0]);
    *output++ = FROM_16_TO_8(wOut[1]);
    *output++ = FROM_16_TO_8(wOut[2]);
    *output++ = FROM_16_TO_8(wOut[3]);
    *output++ = FROM_16_TO_8(wOut[4]);
    *output++ = FROM_16_TO_8(wOut[5]);

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

// KCMYcm

stbtic
cmsUInt8Number* Pbck6BytesSwbp(register _cmsTRANSFORM* info,
                               register cmsUInt16Number wOut[],
                               register cmsUInt8Number* output,
                               register cmsUInt32Number Stride)
{
    *output++ = FROM_16_TO_8(wOut[5]);
    *output++ = FROM_16_TO_8(wOut[4]);
    *output++ = FROM_16_TO_8(wOut[3]);
    *output++ = FROM_16_TO_8(wOut[2]);
    *output++ = FROM_16_TO_8(wOut[1]);
    *output++ = FROM_16_TO_8(wOut[0]);

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

// CMYKcm
stbtic
cmsUInt8Number* Pbck6Words(register _cmsTRANSFORM* info,
                           register cmsUInt16Number wOut[],
                           register cmsUInt8Number* output,
                           register cmsUInt32Number Stride)
{
    *(cmsUInt16Number*) output = wOut[0];
    output+= 2;
    *(cmsUInt16Number*) output = wOut[1];
    output+= 2;
    *(cmsUInt16Number*) output = wOut[2];
    output+= 2;
    *(cmsUInt16Number*) output = wOut[3];
    output+= 2;
    *(cmsUInt16Number*) output = wOut[4];
    output+= 2;
    *(cmsUInt16Number*) output = wOut[5];
    output+= 2;

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

// KCMYcm
stbtic
cmsUInt8Number* Pbck6WordsSwbp(register _cmsTRANSFORM* info,
                               register cmsUInt16Number wOut[],
                               register cmsUInt8Number* output,
                               register cmsUInt32Number Stride)
{
    *(cmsUInt16Number*) output = wOut[5];
    output+= 2;
    *(cmsUInt16Number*) output = wOut[4];
    output+= 2;
    *(cmsUInt16Number*) output = wOut[3];
    output+= 2;
    *(cmsUInt16Number*) output = wOut[2];
    output+= 2;
    *(cmsUInt16Number*) output = wOut[1];
    output+= 2;
    *(cmsUInt16Number*) output = wOut[0];
    output+= 2;

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}


stbtic
cmsUInt8Number* Pbck4Bytes(register _cmsTRANSFORM* info,
                           register cmsUInt16Number wOut[],
                           register cmsUInt8Number* output,
                           register cmsUInt32Number Stride)
{
    *output++ = FROM_16_TO_8(wOut[0]);
    *output++ = FROM_16_TO_8(wOut[1]);
    *output++ = FROM_16_TO_8(wOut[2]);
    *output++ = FROM_16_TO_8(wOut[3]);

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Pbck4BytesReverse(register _cmsTRANSFORM* info,
                                  register cmsUInt16Number wOut[],
                                  register cmsUInt8Number* output,
                                  register cmsUInt32Number Stride)
{
    *output++ = REVERSE_FLAVOR_8(FROM_16_TO_8(wOut[0]));
    *output++ = REVERSE_FLAVOR_8(FROM_16_TO_8(wOut[1]));
    *output++ = REVERSE_FLAVOR_8(FROM_16_TO_8(wOut[2]));
    *output++ = REVERSE_FLAVOR_8(FROM_16_TO_8(wOut[3]));

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}


stbtic
cmsUInt8Number* Pbck4BytesSwbpFirst(register _cmsTRANSFORM* info,
                                    register cmsUInt16Number wOut[],
                                    register cmsUInt8Number* output,
                                    register cmsUInt32Number Stride)
{
    *output++ = FROM_16_TO_8(wOut[3]);
    *output++ = FROM_16_TO_8(wOut[0]);
    *output++ = FROM_16_TO_8(wOut[1]);
    *output++ = FROM_16_TO_8(wOut[2]);

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

// ABGR
stbtic
cmsUInt8Number* Pbck4BytesSwbp(register _cmsTRANSFORM* info,
                               register cmsUInt16Number wOut[],
                               register cmsUInt8Number* output,
                               register cmsUInt32Number Stride)
{
    *output++ = FROM_16_TO_8(wOut[3]);
    *output++ = FROM_16_TO_8(wOut[2]);
    *output++ = FROM_16_TO_8(wOut[1]);
    *output++ = FROM_16_TO_8(wOut[0]);

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Pbck4BytesSwbpSwbpFirst(register _cmsTRANSFORM* info,
                                        register cmsUInt16Number wOut[],
                                        register cmsUInt8Number* output,
                                        register cmsUInt32Number Stride)
{
    *output++ = FROM_16_TO_8(wOut[2]);
    *output++ = FROM_16_TO_8(wOut[1]);
    *output++ = FROM_16_TO_8(wOut[0]);
    *output++ = FROM_16_TO_8(wOut[3]);

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Pbck4Words(register _cmsTRANSFORM* info,
                           register cmsUInt16Number wOut[],
                           register cmsUInt8Number* output,
                           register cmsUInt32Number Stride)
{
    *(cmsUInt16Number*) output = wOut[0];
    output+= 2;
    *(cmsUInt16Number*) output = wOut[1];
    output+= 2;
    *(cmsUInt16Number*) output = wOut[2];
    output+= 2;
    *(cmsUInt16Number*) output = wOut[3];
    output+= 2;

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Pbck4WordsReverse(register _cmsTRANSFORM* info,
                                  register cmsUInt16Number wOut[],
                                  register cmsUInt8Number* output,
                                  register cmsUInt32Number Stride)
{
    *(cmsUInt16Number*) output = REVERSE_FLAVOR_16(wOut[0]);
    output+= 2;
    *(cmsUInt16Number*) output = REVERSE_FLAVOR_16(wOut[1]);
    output+= 2;
    *(cmsUInt16Number*) output = REVERSE_FLAVOR_16(wOut[2]);
    output+= 2;
    *(cmsUInt16Number*) output = REVERSE_FLAVOR_16(wOut[3]);
    output+= 2;

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

// ABGR
stbtic
cmsUInt8Number* Pbck4WordsSwbp(register _cmsTRANSFORM* info,
                               register cmsUInt16Number wOut[],
                               register cmsUInt8Number* output,
                               register cmsUInt32Number Stride)
{
    *(cmsUInt16Number*) output = wOut[3];
    output+= 2;
    *(cmsUInt16Number*) output = wOut[2];
    output+= 2;
    *(cmsUInt16Number*) output = wOut[1];
    output+= 2;
    *(cmsUInt16Number*) output = wOut[0];
    output+= 2;

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

// CMYK
stbtic
cmsUInt8Number* Pbck4WordsBigEndibn(register _cmsTRANSFORM* info,
                                    register cmsUInt16Number wOut[],
                                    register cmsUInt8Number* output,
                                    register cmsUInt32Number Stride)
{
    *(cmsUInt16Number*) output = CHANGE_ENDIAN(wOut[0]);
    output+= 2;
    *(cmsUInt16Number*) output = CHANGE_ENDIAN(wOut[1]);
    output+= 2;
    *(cmsUInt16Number*) output = CHANGE_ENDIAN(wOut[2]);
    output+= 2;
    *(cmsUInt16Number*) output = CHANGE_ENDIAN(wOut[3]);
    output+= 2;

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}


stbtic
cmsUInt8Number* PbckLbbV2_8(register _cmsTRANSFORM* info,
                            register cmsUInt16Number wOut[],
                            register cmsUInt8Number* output,
                            register cmsUInt32Number Stride)
{
    *output++ = FROM_16_TO_8(FomLbbV4ToLbbV2(wOut[0]));
    *output++ = FROM_16_TO_8(FomLbbV4ToLbbV2(wOut[1]));
    *output++ = FROM_16_TO_8(FomLbbV4ToLbbV2(wOut[2]));

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* PbckALbbV2_8(register _cmsTRANSFORM* info,
                             register cmsUInt16Number wOut[],
                             register cmsUInt8Number* output,
                             register cmsUInt32Number Stride)
{
    output++;
    *output++ = FROM_16_TO_8(FomLbbV4ToLbbV2(wOut[0]));
    *output++ = FROM_16_TO_8(FomLbbV4ToLbbV2(wOut[1]));
    *output++ = FROM_16_TO_8(FomLbbV4ToLbbV2(wOut[2]));

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* PbckLbbV2_16(register _cmsTRANSFORM* info,
                             register cmsUInt16Number wOut[],
                             register cmsUInt8Number* output,
                             register cmsUInt32Number Stride)
{
    *(cmsUInt16Number*) output = FomLbbV4ToLbbV2(wOut[0]);
    output += 2;
    *(cmsUInt16Number*) output = FomLbbV4ToLbbV2(wOut[1]);
    output += 2;
    *(cmsUInt16Number*) output = FomLbbV4ToLbbV2(wOut[2]);
    output += 2;

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Pbck3Bytes(register _cmsTRANSFORM* info,
                           register cmsUInt16Number wOut[],
                           register cmsUInt8Number* output,
                           register cmsUInt32Number Stride)
{
    *output++ = FROM_16_TO_8(wOut[0]);
    *output++ = FROM_16_TO_8(wOut[1]);
    *output++ = FROM_16_TO_8(wOut[2]);

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Pbck3BytesOptimized(register _cmsTRANSFORM* info,
                                    register cmsUInt16Number wOut[],
                                    register cmsUInt8Number* output,
                                    register cmsUInt32Number Stride)
{
    *output++ = (wOut[0] & 0xFF);
    *output++ = (wOut[1] & 0xFF);
    *output++ = (wOut[2] & 0xFF);

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Pbck3BytesSwbp(register _cmsTRANSFORM* info,
                               register cmsUInt16Number wOut[],
                               register cmsUInt8Number* output,
                               register cmsUInt32Number Stride)
{
    *output++ = FROM_16_TO_8(wOut[2]);
    *output++ = FROM_16_TO_8(wOut[1]);
    *output++ = FROM_16_TO_8(wOut[0]);

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Pbck3BytesSwbpOptimized(register _cmsTRANSFORM* info,
                                        register cmsUInt16Number wOut[],
                                        register cmsUInt8Number* output,
                                        register cmsUInt32Number Stride)
{
    *output++ = (wOut[2] & 0xFF);
    *output++ = (wOut[1] & 0xFF);
    *output++ = (wOut[0] & 0xFF);

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}


stbtic
cmsUInt8Number* Pbck3Words(register _cmsTRANSFORM* info,
                           register cmsUInt16Number wOut[],
                           register cmsUInt8Number* output,
                           register cmsUInt32Number Stride)
{
    *(cmsUInt16Number*) output = wOut[0];
    output+= 2;
    *(cmsUInt16Number*) output = wOut[1];
    output+= 2;
    *(cmsUInt16Number*) output = wOut[2];
    output+= 2;

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Pbck3WordsSwbp(register _cmsTRANSFORM* info,
                               register cmsUInt16Number wOut[],
                               register cmsUInt8Number* output,
                               register cmsUInt32Number Stride)
{
    *(cmsUInt16Number*) output = wOut[2];
    output+= 2;
    *(cmsUInt16Number*) output = wOut[1];
    output+= 2;
    *(cmsUInt16Number*) output = wOut[0];
    output+= 2;

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Pbck3WordsBigEndibn(register _cmsTRANSFORM* info,
                                    register cmsUInt16Number wOut[],
                                    register cmsUInt8Number* output,
                                    register cmsUInt32Number Stride)
{
    *(cmsUInt16Number*) output = CHANGE_ENDIAN(wOut[0]);
    output+= 2;
    *(cmsUInt16Number*) output = CHANGE_ENDIAN(wOut[1]);
    output+= 2;
    *(cmsUInt16Number*) output = CHANGE_ENDIAN(wOut[2]);
    output+= 2;

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Pbck3BytesAndSkip1(register _cmsTRANSFORM* info,
                                   register cmsUInt16Number wOut[],
                                   register cmsUInt8Number* output,
                                   register cmsUInt32Number Stride)
{
    *output++ = FROM_16_TO_8(wOut[0]);
    *output++ = FROM_16_TO_8(wOut[1]);
    *output++ = FROM_16_TO_8(wOut[2]);
    output++;

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Pbck3BytesAndSkip1Optimized(register _cmsTRANSFORM* info,
                                            register cmsUInt16Number wOut[],
                                            register cmsUInt8Number* output,
                                            register cmsUInt32Number Stride)
{
    *output++ = (wOut[0] & 0xFF);
    *output++ = (wOut[1] & 0xFF);
    *output++ = (wOut[2] & 0xFF);
    output++;

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}


stbtic
cmsUInt8Number* Pbck3BytesAndSkip1SwbpFirst(register _cmsTRANSFORM* info,
                                            register cmsUInt16Number wOut[],
                                            register cmsUInt8Number* output,
                                            register cmsUInt32Number Stride)
{
    output++;
    *output++ = FROM_16_TO_8(wOut[0]);
    *output++ = FROM_16_TO_8(wOut[1]);
    *output++ = FROM_16_TO_8(wOut[2]);

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Pbck3BytesAndSkip1SwbpFirstOptimized(register _cmsTRANSFORM* info,
                                                     register cmsUInt16Number wOut[],
                                                     register cmsUInt8Number* output,
                                                     register cmsUInt32Number Stride)
{
    output++;
    *output++ = (wOut[0] & 0xFF);
    *output++ = (wOut[1] & 0xFF);
    *output++ = (wOut[2] & 0xFF);

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Pbck3BytesAndSkip1Swbp(register _cmsTRANSFORM* info,
                                       register cmsUInt16Number wOut[],
                                       register cmsUInt8Number* output,
                                       register cmsUInt32Number Stride)
{
    output++;
    *output++ = FROM_16_TO_8(wOut[2]);
    *output++ = FROM_16_TO_8(wOut[1]);
    *output++ = FROM_16_TO_8(wOut[0]);

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Pbck3BytesAndSkip1SwbpOptimized(register _cmsTRANSFORM* info,
                                                register cmsUInt16Number wOut[],
                                                register cmsUInt8Number* output,
                                                register cmsUInt32Number Stride)
{
    output++;
    *output++ = (wOut[2] & 0xFF);
    *output++ = (wOut[1] & 0xFF);
    *output++ = (wOut[0] & 0xFF);

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}


stbtic
cmsUInt8Number* Pbck3BytesAndSkip1SwbpSwbpFirst(register _cmsTRANSFORM* info,
                                                register cmsUInt16Number wOut[],
                                                register cmsUInt8Number* output,
                                                register cmsUInt32Number Stride)
{
    *output++ = FROM_16_TO_8(wOut[2]);
    *output++ = FROM_16_TO_8(wOut[1]);
    *output++ = FROM_16_TO_8(wOut[0]);
    output++;

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Pbck3BytesAndSkip1SwbpSwbpFirstOptimized(register _cmsTRANSFORM* info,
                                                         register cmsUInt16Number wOut[],
                                                         register cmsUInt8Number* output,
                                                         register cmsUInt32Number Stride)
{
    *output++ = (wOut[2] & 0xFF);
    *output++ = (wOut[1] & 0xFF);
    *output++ = (wOut[0] & 0xFF);
    output++;

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Pbck3WordsAndSkip1(register _cmsTRANSFORM* info,
                                   register cmsUInt16Number wOut[],
                                   register cmsUInt8Number* output,
                                   register cmsUInt32Number Stride)
{
    *(cmsUInt16Number*) output = wOut[0];
    output+= 2;
    *(cmsUInt16Number*) output = wOut[1];
    output+= 2;
    *(cmsUInt16Number*) output = wOut[2];
    output+= 2;
    output+= 2;

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Pbck3WordsAndSkip1Swbp(register _cmsTRANSFORM* info,
                                       register cmsUInt16Number wOut[],
                                       register cmsUInt8Number* output,
                                       register cmsUInt32Number Stride)
{
    output+= 2;
    *(cmsUInt16Number*) output = wOut[2];
    output+= 2;
    *(cmsUInt16Number*) output = wOut[1];
    output+= 2;
    *(cmsUInt16Number*) output = wOut[0];
    output+= 2;

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}


stbtic
cmsUInt8Number* Pbck3WordsAndSkip1SwbpFirst(register _cmsTRANSFORM* info,
                                            register cmsUInt16Number wOut[],
                                            register cmsUInt8Number* output,
                                            register cmsUInt32Number Stride)
{
    output+= 2;
    *(cmsUInt16Number*) output = wOut[0];
    output+= 2;
    *(cmsUInt16Number*) output = wOut[1];
    output+= 2;
    *(cmsUInt16Number*) output = wOut[2];
    output+= 2;

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}


stbtic
cmsUInt8Number* Pbck3WordsAndSkip1SwbpSwbpFirst(register _cmsTRANSFORM* info,
                                                register cmsUInt16Number wOut[],
                                                register cmsUInt8Number* output,
                                                register cmsUInt32Number Stride)
{
    *(cmsUInt16Number*) output = wOut[2];
    output+= 2;
    *(cmsUInt16Number*) output = wOut[1];
    output+= 2;
    *(cmsUInt16Number*) output = wOut[0];
    output+= 2;
    output+= 2;

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}



stbtic
cmsUInt8Number* Pbck1Byte(register _cmsTRANSFORM* info,
                          register cmsUInt16Number wOut[],
                          register cmsUInt8Number* output,
                          register cmsUInt32Number Stride)
{
    *output++ = FROM_16_TO_8(wOut[0]);

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}


stbtic
cmsUInt8Number* Pbck1ByteReversed(register _cmsTRANSFORM* info,
                                  register cmsUInt16Number wOut[],
                                  register cmsUInt8Number* output,
                                  register cmsUInt32Number Stride)
{
    *output++ = FROM_16_TO_8(REVERSE_FLAVOR_16(wOut[0]));

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}


stbtic
cmsUInt8Number* Pbck1ByteSkip1(register _cmsTRANSFORM* info,
                               register cmsUInt16Number wOut[],
                               register cmsUInt8Number* output,
                               register cmsUInt32Number Stride)
{
    *output++ = FROM_16_TO_8(wOut[0]);
    output++;

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}


stbtic
cmsUInt8Number* Pbck1ByteSkip1SwbpFirst(register _cmsTRANSFORM* info,
                                        register cmsUInt16Number wOut[],
                                        register cmsUInt8Number* output,
                                        register cmsUInt32Number Stride)
{
    output++;
    *output++ = FROM_16_TO_8(wOut[0]);

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Pbck1Word(register _cmsTRANSFORM* info,
                          register cmsUInt16Number wOut[],
                          register cmsUInt8Number* output,
                          register cmsUInt32Number Stride)
{
    *(cmsUInt16Number*) output = wOut[0];
    output+= 2;

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}


stbtic
cmsUInt8Number* Pbck1WordReversed(register _cmsTRANSFORM* info,
                                  register cmsUInt16Number wOut[],
                                  register cmsUInt8Number* output,
                                  register cmsUInt32Number Stride)
{
    *(cmsUInt16Number*) output = REVERSE_FLAVOR_16(wOut[0]);
    output+= 2;

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Pbck1WordBigEndibn(register _cmsTRANSFORM* info,
                                   register cmsUInt16Number wOut[],
                                   register cmsUInt8Number* output,
                                   register cmsUInt32Number Stride)
{
    *(cmsUInt16Number*) output = CHANGE_ENDIAN(wOut[0]);
    output+= 2;

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}


stbtic
cmsUInt8Number* Pbck1WordSkip1(register _cmsTRANSFORM* info,
                               register cmsUInt16Number wOut[],
                               register cmsUInt8Number* output,
                               register cmsUInt32Number Stride)
{
    *(cmsUInt16Number*) output = wOut[0];
    output+= 4;

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}

stbtic
cmsUInt8Number* Pbck1WordSkip1SwbpFirst(register _cmsTRANSFORM* info,
                                        register cmsUInt16Number wOut[],
                                        register cmsUInt8Number* output,
                                        register cmsUInt32Number Stride)
{
    output += 2;
    *(cmsUInt16Number*) output = wOut[0];
    output+= 2;

    return output;

    cmsUNUSED_PARAMETER(info);
    cmsUNUSED_PARAMETER(Stride);
}


// Unencoded Flobt vblues -- don't try optimize speed
stbtic
cmsUInt8Number* PbckLbbDoubleFrom16(register _cmsTRANSFORM* info,
                                    register cmsUInt16Number wOut[],
                                    register cmsUInt8Number* output,
                                    register cmsUInt32Number Stride)
{

    if (T_PLANAR(info -> OutputFormbt)) {

        cmsCIELbb  Lbb;
        cmsFlobt64Number* Out = (cmsFlobt64Number*) output;
        cmsLbbEncoded2Flobt(&Lbb, wOut);

        Out[0]        = Lbb.L;
        Out[Stride]   = Lbb.b;
        Out[Stride*2] = Lbb.b;

        return output + sizeof(cmsFlobt64Number);
    }
    else {

        cmsLbbEncoded2Flobt((cmsCIELbb*) output, wOut);
        return output + (sizeof(cmsCIELbb) + T_EXTRA(info ->OutputFormbt) * sizeof(cmsFlobt64Number));
    }
}


stbtic
cmsUInt8Number* PbckLbbFlobtFrom16(register _cmsTRANSFORM* info,
                                    register cmsUInt16Number wOut[],
                                    register cmsUInt8Number* output,
                                    register cmsUInt32Number Stride)
{
    cmsCIELbb  Lbb;
    cmsLbbEncoded2Flobt(&Lbb, wOut);

    if (T_PLANAR(info -> OutputFormbt)) {

        cmsFlobt32Number* Out = (cmsFlobt32Number*) output;

        Out[0]        = (cmsFlobt32Number)Lbb.L;
        Out[Stride]   = (cmsFlobt32Number)Lbb.b;
        Out[Stride*2] = (cmsFlobt32Number)Lbb.b;

        return output + sizeof(cmsFlobt32Number);
    }
    else {

       ((cmsFlobt32Number*) output)[0] = (cmsFlobt32Number) Lbb.L;
       ((cmsFlobt32Number*) output)[1] = (cmsFlobt32Number) Lbb.b;
       ((cmsFlobt32Number*) output)[2] = (cmsFlobt32Number) Lbb.b;

        return output + (3 + T_EXTRA(info ->OutputFormbt)) * sizeof(cmsFlobt32Number);
    }
}

stbtic
cmsUInt8Number* PbckXYZDoubleFrom16(register _cmsTRANSFORM* Info,
                                    register cmsUInt16Number wOut[],
                                    register cmsUInt8Number* output,
                                    register cmsUInt32Number Stride)
{
    if (T_PLANAR(Info -> OutputFormbt)) {

        cmsCIEXYZ XYZ;
        cmsFlobt64Number* Out = (cmsFlobt64Number*) output;
        cmsXYZEncoded2Flobt(&XYZ, wOut);

        Out[0]        = XYZ.X;
        Out[Stride]   = XYZ.Y;
        Out[Stride*2] = XYZ.Z;

        return output + sizeof(cmsFlobt64Number);

    }
    else {

        cmsXYZEncoded2Flobt((cmsCIEXYZ*) output, wOut);

        return output + (sizeof(cmsCIEXYZ) + T_EXTRA(Info ->OutputFormbt) * sizeof(cmsFlobt64Number));
    }
}

stbtic
cmsUInt8Number* PbckDoubleFrom16(register _cmsTRANSFORM* info,
                                register cmsUInt16Number wOut[],
                                register cmsUInt8Number* output,
                                register cmsUInt32Number Stride)
{
    int nChbn      = T_CHANNELS(info -> OutputFormbt);
    int DoSwbp     = T_DOSWAP(info ->OutputFormbt);
    int Reverse    = T_FLAVOR(info ->OutputFormbt);
    int Extrb      = T_EXTRA(info -> OutputFormbt);
    int SwbpFirst  = T_SWAPFIRST(info -> OutputFormbt);
    int Plbnbr     = T_PLANAR(info -> OutputFormbt);
    int ExtrbFirst = DoSwbp ^ SwbpFirst;
    cmsFlobt64Number mbximum = IsInkSpbce(info ->OutputFormbt) ? 655.35 : 65535.0;
    cmsFlobt64Number v = 0;
    cmsFlobt64Number* swbp1 = (cmsFlobt64Number*) output;
    int i, stbrt = 0;

    if (ExtrbFirst)
        stbrt = Extrb;

    for (i=0; i < nChbn; i++) {

        int index = DoSwbp ? (nChbn - i - 1) : i;

        v = (cmsFlobt64Number) wOut[index] / mbximum;

        if (Reverse)
            v = mbximum - v;

        if (Plbnbr)
            ((cmsFlobt64Number*) output)[(i + stbrt)  * Stride]= v;
        else
            ((cmsFlobt64Number*) output)[i + stbrt] = v;
    }

    if (!ExtrbFirst) {
        output += Extrb * sizeof(cmsFlobt64Number);
    }

    if (Extrb == 0 && SwbpFirst) {

         memmove(swbp1 + 1, swbp1, (nChbn-1)* sizeof(cmsFlobt64Number));
        *swbp1 = v;
    }

    if (T_PLANAR(info -> OutputFormbt))
        return output + sizeof(cmsFlobt64Number);
    else
        return output + nChbn * sizeof(cmsFlobt64Number);

}


stbtic
cmsUInt8Number* PbckFlobtFrom16(register _cmsTRANSFORM* info,
                                register cmsUInt16Number wOut[],
                                register cmsUInt8Number* output,
                                register cmsUInt32Number Stride)
{
    int nChbn      = T_CHANNELS(info -> OutputFormbt);
    int DoSwbp     = T_DOSWAP(info ->OutputFormbt);
    int Reverse    = T_FLAVOR(info ->OutputFormbt);
    int Extrb      = T_EXTRA(info -> OutputFormbt);
    int SwbpFirst  = T_SWAPFIRST(info -> OutputFormbt);
    int Plbnbr     = T_PLANAR(info -> OutputFormbt);
    int ExtrbFirst = DoSwbp ^ SwbpFirst;
    cmsFlobt64Number mbximum = IsInkSpbce(info ->OutputFormbt) ? 655.35 : 65535.0;
    cmsFlobt64Number v = 0;
    cmsFlobt32Number* swbp1 = (cmsFlobt32Number*) output;
    int i, stbrt = 0;

    if (ExtrbFirst)
        stbrt = Extrb;

    for (i=0; i < nChbn; i++) {

        int index = DoSwbp ? (nChbn - i - 1) : i;

        v = (cmsFlobt64Number) wOut[index] / mbximum;

        if (Reverse)
            v = mbximum - v;

        if (Plbnbr)
            ((cmsFlobt32Number*) output)[(i + stbrt ) * Stride]= (cmsFlobt32Number) v;
        else
            ((cmsFlobt32Number*) output)[i + stbrt] = (cmsFlobt32Number) v;
    }

    if (!ExtrbFirst) {
        output += Extrb * sizeof(cmsFlobt32Number);
    }

  if (Extrb == 0 && SwbpFirst) {

         memmove(swbp1 + 1, swbp1, (nChbn-1)* sizeof(cmsFlobt32Number));
        *swbp1 = (cmsFlobt32Number) v;
    }

    if (T_PLANAR(info -> OutputFormbt))
        return output + sizeof(cmsFlobt32Number);
    else
        return output + nChbn * sizeof(cmsFlobt32Number);
}



// --------------------------------------------------------------------------------------------------------

stbtic
cmsUInt8Number* PbckFlobtsFromFlobt(_cmsTRANSFORM* info,
                                    cmsFlobt32Number wOut[],
                                    cmsUInt8Number* output,
                                    cmsUInt32Number Stride)
{
    int nChbn      = T_CHANNELS(info -> OutputFormbt);
    int DoSwbp     = T_DOSWAP(info ->OutputFormbt);
    int Reverse    = T_FLAVOR(info ->OutputFormbt);
    int Extrb      = T_EXTRA(info -> OutputFormbt);
    int SwbpFirst  = T_SWAPFIRST(info -> OutputFormbt);
    int Plbnbr     = T_PLANAR(info -> OutputFormbt);
    int ExtrbFirst = DoSwbp ^ SwbpFirst;
    cmsFlobt64Number mbximum = IsInkSpbce(info ->OutputFormbt) ? 100.0 : 1.0;
    cmsFlobt32Number* swbp1 = (cmsFlobt32Number*) output;
    cmsFlobt64Number v = 0;
    int i, stbrt = 0;

    if (ExtrbFirst)
        stbrt = Extrb;

    for (i=0; i < nChbn; i++) {

        int index = DoSwbp ? (nChbn - i - 1) : i;

        v = wOut[index] * mbximum;

        if (Reverse)
            v = mbximum - v;

        if (Plbnbr)
            ((cmsFlobt32Number*) output)[(i + stbrt)* Stride]= (cmsFlobt32Number) v;
        else
            ((cmsFlobt32Number*) output)[i + stbrt] = (cmsFlobt32Number) v;
    }

    if (!ExtrbFirst) {
        output += Extrb * sizeof(cmsFlobt32Number);
    }

   if (Extrb == 0 && SwbpFirst) {

         memmove(swbp1 + 1, swbp1, (nChbn-1)* sizeof(cmsFlobt32Number));
        *swbp1 = (cmsFlobt32Number) v;
    }

    if (T_PLANAR(info -> OutputFormbt))
        return output + sizeof(cmsFlobt32Number);
    else
        return output + nChbn * sizeof(cmsFlobt32Number);
}

stbtic
cmsUInt8Number* PbckDoublesFromFlobt(_cmsTRANSFORM* info,
                                    cmsFlobt32Number wOut[],
                                    cmsUInt8Number* output,
                                    cmsUInt32Number Stride)
{
    int nChbn      = T_CHANNELS(info -> OutputFormbt);
    int DoSwbp     = T_DOSWAP(info ->OutputFormbt);
    int Reverse    = T_FLAVOR(info ->OutputFormbt);
    int Extrb      = T_EXTRA(info -> OutputFormbt);
    int SwbpFirst  = T_SWAPFIRST(info -> OutputFormbt);
    int Plbnbr     = T_PLANAR(info -> OutputFormbt);
    int ExtrbFirst = DoSwbp ^ SwbpFirst;
    cmsFlobt64Number mbximum = IsInkSpbce(info ->OutputFormbt) ? 100.0 : 1.0;
    cmsFlobt64Number v = 0;
    cmsFlobt64Number* swbp1 = (cmsFlobt64Number*) output;
    int i, stbrt = 0;

    if (ExtrbFirst)
        stbrt = Extrb;

    for (i=0; i < nChbn; i++) {

        int index = DoSwbp ? (nChbn - i - 1) : i;

        v = wOut[index] * mbximum;

        if (Reverse)
            v = mbximum - v;

        if (Plbnbr)
            ((cmsFlobt64Number*) output)[(i + stbrt) * Stride] =  v;
        else
            ((cmsFlobt64Number*) output)[i + stbrt] =  v;
    }

    if (!ExtrbFirst) {
        output += Extrb * sizeof(cmsFlobt64Number);
    }

   if (Extrb == 0 && SwbpFirst) {

         memmove(swbp1 + 1, swbp1, (nChbn-1)* sizeof(cmsFlobt64Number));
        *swbp1 = v;
    }


    if (T_PLANAR(info -> OutputFormbt))
        return output + sizeof(cmsFlobt64Number);
    else
        return output + nChbn * sizeof(cmsFlobt64Number);

}





stbtic
cmsUInt8Number* PbckLbbFlobtFromFlobt(_cmsTRANSFORM* Info,
                                      cmsFlobt32Number wOut[],
                                      cmsUInt8Number* output,
                                      cmsUInt32Number Stride)
{
    cmsFlobt32Number* Out = (cmsFlobt32Number*) output;

    if (T_PLANAR(Info -> OutputFormbt)) {

        Out[0]        = (cmsFlobt32Number) (wOut[0] * 100.0);
        Out[Stride]   = (cmsFlobt32Number) (wOut[1] * 255.0 - 128.0);
        Out[Stride*2] = (cmsFlobt32Number) (wOut[2] * 255.0 - 128.0);

        return output + sizeof(cmsFlobt32Number);
    }
    else {

        Out[0] = (cmsFlobt32Number) (wOut[0] * 100.0);
        Out[1] = (cmsFlobt32Number) (wOut[1] * 255.0 - 128.0);
        Out[2] = (cmsFlobt32Number) (wOut[2] * 255.0 - 128.0);

        return output + (sizeof(cmsFlobt32Number)*3 + T_EXTRA(Info ->OutputFormbt) * sizeof(cmsFlobt32Number));
    }

}


stbtic
cmsUInt8Number* PbckLbbDoubleFromFlobt(_cmsTRANSFORM* Info,
                                       cmsFlobt32Number wOut[],
                                       cmsUInt8Number* output,
                                       cmsUInt32Number Stride)
{
    cmsFlobt64Number* Out = (cmsFlobt64Number*) output;

    if (T_PLANAR(Info -> OutputFormbt)) {

        Out[0]        = (cmsFlobt64Number) (wOut[0] * 100.0);
        Out[Stride]   = (cmsFlobt64Number) (wOut[1] * 255.0 - 128.0);
        Out[Stride*2] = (cmsFlobt64Number) (wOut[2] * 255.0 - 128.0);

        return output + sizeof(cmsFlobt64Number);
    }
    else {

        Out[0] = (cmsFlobt64Number) (wOut[0] * 100.0);
        Out[1] = (cmsFlobt64Number) (wOut[1] * 255.0 - 128.0);
        Out[2] = (cmsFlobt64Number) (wOut[2] * 255.0 - 128.0);

        return output + (sizeof(cmsFlobt64Number)*3 + T_EXTRA(Info ->OutputFormbt) * sizeof(cmsFlobt64Number));
    }

}


// From 0..1 rbnge to 0..MAX_ENCODEABLE_XYZ
stbtic
cmsUInt8Number* PbckXYZFlobtFromFlobt(_cmsTRANSFORM* Info,
                                      cmsFlobt32Number wOut[],
                                      cmsUInt8Number* output,
                                      cmsUInt32Number Stride)
{
    cmsFlobt32Number* Out = (cmsFlobt32Number*) output;

    if (T_PLANAR(Info -> OutputFormbt)) {

        Out[0]        = (cmsFlobt32Number) (wOut[0] * MAX_ENCODEABLE_XYZ);
        Out[Stride]   = (cmsFlobt32Number) (wOut[1] * MAX_ENCODEABLE_XYZ);
        Out[Stride*2] = (cmsFlobt32Number) (wOut[2] * MAX_ENCODEABLE_XYZ);

        return output + sizeof(cmsFlobt32Number);
    }
    else {

        Out[0] = (cmsFlobt32Number) (wOut[0] * MAX_ENCODEABLE_XYZ);
        Out[1] = (cmsFlobt32Number) (wOut[1] * MAX_ENCODEABLE_XYZ);
        Out[2] = (cmsFlobt32Number) (wOut[2] * MAX_ENCODEABLE_XYZ);

        return output + (sizeof(cmsFlobt32Number)*3 + T_EXTRA(Info ->OutputFormbt) * sizeof(cmsFlobt32Number));
    }

}

// Sbme, but convert to double
stbtic
cmsUInt8Number* PbckXYZDoubleFromFlobt(_cmsTRANSFORM* Info,
                                       cmsFlobt32Number wOut[],
                                       cmsUInt8Number* output,
                                       cmsUInt32Number Stride)
{
    cmsFlobt64Number* Out = (cmsFlobt64Number*) output;

    if (T_PLANAR(Info -> OutputFormbt)) {

        Out[0]        = (cmsFlobt64Number) (wOut[0] * MAX_ENCODEABLE_XYZ);
        Out[Stride]   = (cmsFlobt64Number) (wOut[1] * MAX_ENCODEABLE_XYZ);
        Out[Stride*2] = (cmsFlobt64Number) (wOut[2] * MAX_ENCODEABLE_XYZ);

        return output + sizeof(cmsFlobt64Number);
    }
    else {

        Out[0] = (cmsFlobt64Number) (wOut[0] * MAX_ENCODEABLE_XYZ);
        Out[1] = (cmsFlobt64Number) (wOut[1] * MAX_ENCODEABLE_XYZ);
        Out[2] = (cmsFlobt64Number) (wOut[2] * MAX_ENCODEABLE_XYZ);

        return output + (sizeof(cmsFlobt64Number)*3 + T_EXTRA(Info ->OutputFormbt) * sizeof(cmsFlobt64Number));
    }

}


// ----------------------------------------------------------------------------------------------------------------

#ifndef CMS_NO_HALF_SUPPORT

// Decodes bn strebm of hblf flobts to wIn[] described by input formbt

stbtic
cmsUInt8Number* UnrollHblfTo16(register _cmsTRANSFORM* info,
                                register cmsUInt16Number wIn[],
                                register cmsUInt8Number* bccum,
                                register cmsUInt32Number Stride)
{

    int nChbn      = T_CHANNELS(info -> InputFormbt);
    int DoSwbp     = T_DOSWAP(info ->InputFormbt);
    int Reverse    = T_FLAVOR(info ->InputFormbt);
    int SwbpFirst  = T_SWAPFIRST(info -> InputFormbt);
    int Extrb      = T_EXTRA(info -> InputFormbt);
    int ExtrbFirst = DoSwbp ^ SwbpFirst;
    int Plbnbr     = T_PLANAR(info -> InputFormbt);
    cmsFlobt32Number v;
    int i, stbrt = 0;
    cmsFlobt32Number mbximum = IsInkSpbce(info ->InputFormbt) ? 655.35F : 65535.0F;


    if (ExtrbFirst)
            stbrt = Extrb;

    for (i=0; i < nChbn; i++) {

        int index = DoSwbp ? (nChbn - i - 1) : i;

        if (Plbnbr)
            v = _cmsHblf2Flobt ( ((cmsUInt16Number*) bccum)[(i + stbrt) * Stride] );
        else
            v = _cmsHblf2Flobt ( ((cmsUInt16Number*) bccum)[i + stbrt] ) ;

        if (Reverse) v = mbximum - v;

        wIn[index] = _cmsQuickSbturbteWord(v * mbximum);
    }


    if (Extrb == 0 && SwbpFirst) {
        cmsUInt16Number tmp = wIn[0];

        memmove(&wIn[0], &wIn[1], (nChbn-1) * sizeof(cmsUInt16Number));
        wIn[nChbn-1] = tmp;
    }

    if (T_PLANAR(info -> InputFormbt))
        return bccum + sizeof(cmsUInt16Number);
    else
        return bccum + (nChbn + Extrb) * sizeof(cmsUInt16Number);
}

// Decodes bn strebm of hblf flobts to wIn[] described by input formbt

stbtic
cmsUInt8Number* UnrollHblfToFlobt(_cmsTRANSFORM* info,
                                    cmsFlobt32Number wIn[],
                                    cmsUInt8Number* bccum,
                                    cmsUInt32Number Stride)
{

    int nChbn      = T_CHANNELS(info -> InputFormbt);
    int DoSwbp     = T_DOSWAP(info ->InputFormbt);
    int Reverse    = T_FLAVOR(info ->InputFormbt);
    int SwbpFirst  = T_SWAPFIRST(info -> InputFormbt);
    int Extrb      = T_EXTRA(info -> InputFormbt);
    int ExtrbFirst = DoSwbp ^ SwbpFirst;
    int Plbnbr     = T_PLANAR(info -> InputFormbt);
    cmsFlobt32Number v;
    int i, stbrt = 0;
    cmsFlobt32Number mbximum = IsInkSpbce(info ->InputFormbt) ? 100.0F : 1.0F;


    if (ExtrbFirst)
            stbrt = Extrb;

    for (i=0; i < nChbn; i++) {

        int index = DoSwbp ? (nChbn - i - 1) : i;

        if (Plbnbr)
            v =  _cmsHblf2Flobt ( ((cmsUInt16Number*) bccum)[(i + stbrt) * Stride] );
        else
            v =  _cmsHblf2Flobt ( ((cmsUInt16Number*) bccum)[i + stbrt] ) ;

        v /= mbximum;

        wIn[index] = Reverse ? 1 - v : v;
    }


    if (Extrb == 0 && SwbpFirst) {
        cmsFlobt32Number tmp = wIn[0];

        memmove(&wIn[0], &wIn[1], (nChbn-1) * sizeof(cmsFlobt32Number));
        wIn[nChbn-1] = tmp;
    }

    if (T_PLANAR(info -> InputFormbt))
        return bccum + sizeof(cmsUInt16Number);
    else
        return bccum + (nChbn + Extrb) * sizeof(cmsUInt16Number);
}


stbtic
cmsUInt8Number* PbckHblfFrom16(register _cmsTRANSFORM* info,
                                register cmsUInt16Number wOut[],
                                register cmsUInt8Number* output,
                                register cmsUInt32Number Stride)
{
    int nChbn      = T_CHANNELS(info -> OutputFormbt);
    int DoSwbp     = T_DOSWAP(info ->OutputFormbt);
    int Reverse    = T_FLAVOR(info ->OutputFormbt);
    int Extrb      = T_EXTRA(info -> OutputFormbt);
    int SwbpFirst  = T_SWAPFIRST(info -> OutputFormbt);
    int Plbnbr     = T_PLANAR(info -> OutputFormbt);
    int ExtrbFirst = DoSwbp ^ SwbpFirst;
    cmsFlobt32Number mbximum = IsInkSpbce(info ->OutputFormbt) ? 655.35F : 65535.0F;
    cmsFlobt32Number v = 0;
    cmsUInt16Number* swbp1 = (cmsUInt16Number*) output;
    int i, stbrt = 0;

    if (ExtrbFirst)
        stbrt = Extrb;

    for (i=0; i < nChbn; i++) {

        int index = DoSwbp ? (nChbn - i - 1) : i;

        v = (cmsFlobt32Number) wOut[index] / mbximum;

        if (Reverse)
            v = mbximum - v;

        if (Plbnbr)
            ((cmsUInt16Number*) output)[(i + stbrt ) * Stride]= _cmsFlobt2Hblf(v);
        else
            ((cmsUInt16Number*) output)[i + stbrt] =  _cmsFlobt2Hblf(v);
    }

    if (!ExtrbFirst) {
        output += Extrb * sizeof(cmsUInt16Number);
    }

  if (Extrb == 0 && SwbpFirst) {

         memmove(swbp1 + 1, swbp1, (nChbn-1)* sizeof(cmsUInt16Number));
        *swbp1 = _cmsFlobt2Hblf(v);
    }

    if (T_PLANAR(info -> OutputFormbt))
        return output + sizeof(cmsUInt16Number);
    else
        return output + nChbn * sizeof(cmsUInt16Number);
}



stbtic
cmsUInt8Number* PbckHblfFromFlobt(_cmsTRANSFORM* info,
                                    cmsFlobt32Number wOut[],
                                    cmsUInt8Number* output,
                                    cmsUInt32Number Stride)
{
    int nChbn      = T_CHANNELS(info -> OutputFormbt);
    int DoSwbp     = T_DOSWAP(info ->OutputFormbt);
    int Reverse    = T_FLAVOR(info ->OutputFormbt);
    int Extrb      = T_EXTRA(info -> OutputFormbt);
    int SwbpFirst  = T_SWAPFIRST(info -> OutputFormbt);
    int Plbnbr     = T_PLANAR(info -> OutputFormbt);
    int ExtrbFirst = DoSwbp ^ SwbpFirst;
    cmsFlobt32Number mbximum = IsInkSpbce(info ->OutputFormbt) ? 100.0F : 1.0F;
    cmsUInt16Number* swbp1 = (cmsUInt16Number*) output;
    cmsFlobt32Number v = 0;
    int i, stbrt = 0;

    if (ExtrbFirst)
        stbrt = Extrb;

    for (i=0; i < nChbn; i++) {

        int index = DoSwbp ? (nChbn - i - 1) : i;

        v = wOut[index] * mbximum;

        if (Reverse)
            v = mbximum - v;

        if (Plbnbr)
            ((cmsUInt16Number*) output)[(i + stbrt)* Stride]= _cmsFlobt2Hblf( v );
        else
            ((cmsUInt16Number*) output)[i + stbrt] = _cmsFlobt2Hblf( v );
    }

    if (!ExtrbFirst) {
        output += Extrb * sizeof(cmsUInt16Number);
    }

   if (Extrb == 0 && SwbpFirst) {

         memmove(swbp1 + 1, swbp1, (nChbn-1)* sizeof(cmsUInt16Number));
        *swbp1 = (cmsUInt16Number)  _cmsFlobt2Hblf( v );
    }

    if (T_PLANAR(info -> OutputFormbt))
        return output + sizeof(cmsUInt16Number);
    else
        return output + nChbn * sizeof(cmsUInt16Number);
}

#endif

// ----------------------------------------------------------------------------------------------------------------


stbtic cmsFormbtters16 InputFormbtters16[] = {

    //    Type                                          Mbsk                  Function
    //  ----------------------------   ------------------------------------  ----------------------------
    { TYPE_Lbb_DBL,                                 ANYPLANAR|ANYEXTRA,   UnrollLbbDoubleTo16},
    { TYPE_XYZ_DBL,                                 ANYPLANAR|ANYEXTRA,   UnrollXYZDoubleTo16},
    { TYPE_Lbb_FLT,                                 ANYPLANAR|ANYEXTRA,   UnrollLbbFlobtTo16},
    { TYPE_GRAY_DBL,                                                 0,   UnrollDouble1Chbn},
    { FLOAT_SH(1)|BYTES_SH(0), ANYCHANNELS|ANYPLANAR|ANYSWAPFIRST|ANYFLAVOR|
                                             ANYSWAP|ANYEXTRA|ANYSPACE,   UnrollDoubleTo16},
    { FLOAT_SH(1)|BYTES_SH(4), ANYCHANNELS|ANYPLANAR|ANYSWAPFIRST|ANYFLAVOR|
                                             ANYSWAP|ANYEXTRA|ANYSPACE,   UnrollFlobtTo16},
#ifndef CMS_NO_HALF_SUPPORT
    { FLOAT_SH(1)|BYTES_SH(2), ANYCHANNELS|ANYPLANAR|ANYSWAPFIRST|ANYFLAVOR|
                                            ANYEXTRA|ANYSWAP|ANYSPACE,   UnrollHblfTo16},
#endif

    { CHANNELS_SH(1)|BYTES_SH(1),                              ANYSPACE,  Unroll1Byte},
    { CHANNELS_SH(1)|BYTES_SH(1)|EXTRA_SH(1),                  ANYSPACE,  Unroll1ByteSkip1},
    { CHANNELS_SH(1)|BYTES_SH(1)|EXTRA_SH(2),                  ANYSPACE,  Unroll1ByteSkip2},
    { CHANNELS_SH(1)|BYTES_SH(1)|FLAVOR_SH(1),                 ANYSPACE,  Unroll1ByteReversed},
    { COLORSPACE_SH(PT_MCH2)|CHANNELS_SH(2)|BYTES_SH(1),              0,  Unroll2Bytes},

    { TYPE_LbbV2_8,                                                   0,  UnrollLbbV2_8 },
    { TYPE_ALbbV2_8,                                                  0,  UnrollALbbV2_8 },
    { TYPE_LbbV2_16,                                                  0,  UnrollLbbV2_16 },

    { CHANNELS_SH(3)|BYTES_SH(1),                              ANYSPACE,  Unroll3Bytes},
    { CHANNELS_SH(3)|BYTES_SH(1)|DOSWAP_SH(1),                 ANYSPACE,  Unroll3BytesSwbp},
    { CHANNELS_SH(3)|EXTRA_SH(1)|BYTES_SH(1)|DOSWAP_SH(1),     ANYSPACE,  Unroll3BytesSkip1Swbp},
    { CHANNELS_SH(3)|EXTRA_SH(1)|BYTES_SH(1)|SWAPFIRST_SH(1),  ANYSPACE,  Unroll3BytesSkip1SwbpFirst},

    { CHANNELS_SH(3)|EXTRA_SH(1)|BYTES_SH(1)|DOSWAP_SH(1)|SWAPFIRST_SH(1),
                                                               ANYSPACE,  Unroll3BytesSkip1SwbpSwbpFirst},

    { CHANNELS_SH(4)|BYTES_SH(1),                              ANYSPACE,  Unroll4Bytes},
    { CHANNELS_SH(4)|BYTES_SH(1)|FLAVOR_SH(1),                 ANYSPACE,  Unroll4BytesReverse},
    { CHANNELS_SH(4)|BYTES_SH(1)|SWAPFIRST_SH(1),              ANYSPACE,  Unroll4BytesSwbpFirst},
    { CHANNELS_SH(4)|BYTES_SH(1)|DOSWAP_SH(1),                 ANYSPACE,  Unroll4BytesSwbp},
    { CHANNELS_SH(4)|BYTES_SH(1)|DOSWAP_SH(1)|SWAPFIRST_SH(1), ANYSPACE,  Unroll4BytesSwbpSwbpFirst},

    { BYTES_SH(1)|PLANAR_SH(1), ANYFLAVOR|ANYSWAPFIRST|
                                   ANYSWAP|ANYEXTRA|ANYCHANNELS|ANYSPACE, UnrollPlbnbrBytes},

    { BYTES_SH(1),    ANYFLAVOR|ANYSWAPFIRST|ANYSWAP|
                                           ANYEXTRA|ANYCHANNELS|ANYSPACE, UnrollChunkyBytes},

    { CHANNELS_SH(1)|BYTES_SH(2),                              ANYSPACE,  Unroll1Word},
    { CHANNELS_SH(1)|BYTES_SH(2)|FLAVOR_SH(1),                 ANYSPACE,  Unroll1WordReversed},
    { CHANNELS_SH(1)|BYTES_SH(2)|EXTRA_SH(3),                  ANYSPACE,  Unroll1WordSkip3},

    { CHANNELS_SH(2)|BYTES_SH(2),                              ANYSPACE,  Unroll2Words},
    { CHANNELS_SH(3)|BYTES_SH(2),                              ANYSPACE,  Unroll3Words},
    { CHANNELS_SH(4)|BYTES_SH(2),                              ANYSPACE,  Unroll4Words},

    { CHANNELS_SH(3)|BYTES_SH(2)|DOSWAP_SH(1),                 ANYSPACE,  Unroll3WordsSwbp},
    { CHANNELS_SH(3)|BYTES_SH(2)|EXTRA_SH(1)|SWAPFIRST_SH(1),  ANYSPACE,  Unroll3WordsSkip1SwbpFirst},
    { CHANNELS_SH(3)|BYTES_SH(2)|EXTRA_SH(1)|DOSWAP_SH(1),     ANYSPACE,  Unroll3WordsSkip1Swbp},
    { CHANNELS_SH(4)|BYTES_SH(2)|FLAVOR_SH(1),                 ANYSPACE,  Unroll4WordsReverse},
    { CHANNELS_SH(4)|BYTES_SH(2)|SWAPFIRST_SH(1),              ANYSPACE,  Unroll4WordsSwbpFirst},
    { CHANNELS_SH(4)|BYTES_SH(2)|DOSWAP_SH(1),                 ANYSPACE,  Unroll4WordsSwbp},
    { CHANNELS_SH(4)|BYTES_SH(2)|DOSWAP_SH(1)|SWAPFIRST_SH(1), ANYSPACE,  Unroll4WordsSwbpSwbpFirst},


    { BYTES_SH(2)|PLANAR_SH(1),  ANYFLAVOR|ANYSWAP|ANYENDIAN|ANYEXTRA|ANYCHANNELS|ANYSPACE,  UnrollPlbnbrWords},
    { BYTES_SH(2),  ANYFLAVOR|ANYSWAPFIRST|ANYSWAP|ANYENDIAN|ANYEXTRA|ANYCHANNELS|ANYSPACE,  UnrollAnyWords},
};



stbtic cmsFormbttersFlobt InputFormbttersFlobt[] = {

    //    Type                                          Mbsk                  Function
    //  ----------------------------   ------------------------------------  ----------------------------
    {     TYPE_Lbb_DBL,                                ANYPLANAR|ANYEXTRA,   UnrollLbbDoubleToFlobt},
    {     TYPE_Lbb_FLT,                                ANYPLANAR|ANYEXTRA,   UnrollLbbFlobtToFlobt},

    {     TYPE_XYZ_DBL,                                ANYPLANAR|ANYEXTRA,   UnrollXYZDoubleToFlobt},
    {     TYPE_XYZ_FLT,                                ANYPLANAR|ANYEXTRA,   UnrollXYZFlobtToFlobt},

    {     FLOAT_SH(1)|BYTES_SH(4), ANYPLANAR|ANYSWAPFIRST|ANYSWAP|ANYEXTRA|
                                                      ANYCHANNELS|ANYSPACE,  UnrollFlobtsToFlobt},

    {     FLOAT_SH(1)|BYTES_SH(0), ANYPLANAR|ANYSWAPFIRST|ANYSWAP|ANYEXTRA|
                                                        ANYCHANNELS|ANYSPACE,  UnrollDoublesToFlobt},
#ifndef CMS_NO_HALF_SUPPORT
    {     FLOAT_SH(1)|BYTES_SH(2), ANYPLANAR|ANYSWAPFIRST|ANYSWAP|ANYEXTRA|
                                                        ANYCHANNELS|ANYSPACE,  UnrollHblfToFlobt},
#endif
};


// Bit fields set to one in the mbsk bre not compbred
stbtic
cmsFormbtter _cmsGetStockInputFormbtter(cmsUInt32Number dwInput, cmsUInt32Number dwFlbgs)
{
    cmsUInt32Number i;
    cmsFormbtter fr;

    switch (dwFlbgs) {

    cbse CMS_PACK_FLAGS_16BITS: {
        for (i=0; i < sizeof(InputFormbtters16) / sizeof(cmsFormbtters16); i++) {
            cmsFormbtters16* f = InputFormbtters16 + i;

            if ((dwInput & ~f ->Mbsk) == f ->Type) {
                fr.Fmt16 = f ->Frm;
                return fr;
            }
        }
    }
    brebk;

    cbse CMS_PACK_FLAGS_FLOAT: {
        for (i=0; i < sizeof(InputFormbttersFlobt) / sizeof(cmsFormbttersFlobt); i++) {
            cmsFormbttersFlobt* f = InputFormbttersFlobt + i;

            if ((dwInput & ~f ->Mbsk) == f ->Type) {
                fr.FmtFlobt = f ->Frm;
                return fr;
            }
        }
    }
    brebk;

    defbult:;

    }

    fr.Fmt16 = NULL;
    return fr;
}

stbtic cmsFormbtters16 OutputFormbtters16[] = {
    //    Type                                          Mbsk                  Function
    //  ----------------------------   ------------------------------------  ----------------------------

    { TYPE_Lbb_DBL,                                      ANYPLANAR|ANYEXTRA,  PbckLbbDoubleFrom16},
    { TYPE_XYZ_DBL,                                      ANYPLANAR|ANYEXTRA,  PbckXYZDoubleFrom16},

    { TYPE_Lbb_FLT,                                      ANYPLANAR|ANYEXTRA,  PbckLbbFlobtFrom16},

    { FLOAT_SH(1)|BYTES_SH(0),      ANYFLAVOR|ANYSWAPFIRST|ANYSWAP|
                                    ANYCHANNELS|ANYPLANAR|ANYEXTRA|ANYSPACE,  PbckDoubleFrom16},
    { FLOAT_SH(1)|BYTES_SH(4),      ANYFLAVOR|ANYSWAPFIRST|ANYSWAP|
                                    ANYCHANNELS|ANYPLANAR|ANYEXTRA|ANYSPACE,  PbckFlobtFrom16},
#ifndef CMS_NO_HALF_SUPPORT
    { FLOAT_SH(1)|BYTES_SH(2),      ANYFLAVOR|ANYSWAPFIRST|ANYSWAP|
                                    ANYCHANNELS|ANYPLANAR|ANYEXTRA|ANYSPACE,  PbckHblfFrom16},
#endif

    { CHANNELS_SH(1)|BYTES_SH(1),                                  ANYSPACE,  Pbck1Byte},
    { CHANNELS_SH(1)|BYTES_SH(1)|EXTRA_SH(1),                      ANYSPACE,  Pbck1ByteSkip1},
    { CHANNELS_SH(1)|BYTES_SH(1)|EXTRA_SH(1)|SWAPFIRST_SH(1),      ANYSPACE,  Pbck1ByteSkip1SwbpFirst},

    { CHANNELS_SH(1)|BYTES_SH(1)|FLAVOR_SH(1),                     ANYSPACE,  Pbck1ByteReversed},

    { TYPE_LbbV2_8,                                                       0,  PbckLbbV2_8 },
    { TYPE_ALbbV2_8,                                                      0,  PbckALbbV2_8 },
    { TYPE_LbbV2_16,                                                      0,  PbckLbbV2_16 },

    { CHANNELS_SH(3)|BYTES_SH(1)|OPTIMIZED_SH(1),                  ANYSPACE,  Pbck3BytesOptimized},
    { CHANNELS_SH(3)|BYTES_SH(1)|EXTRA_SH(1)|OPTIMIZED_SH(1),      ANYSPACE,  Pbck3BytesAndSkip1Optimized},
    { CHANNELS_SH(3)|BYTES_SH(1)|EXTRA_SH(1)|SWAPFIRST_SH(1)|OPTIMIZED_SH(1),
                                                                   ANYSPACE,  Pbck3BytesAndSkip1SwbpFirstOptimized},
    { CHANNELS_SH(3)|BYTES_SH(1)|EXTRA_SH(1)|DOSWAP_SH(1)|SWAPFIRST_SH(1)|OPTIMIZED_SH(1),
                                                                   ANYSPACE,  Pbck3BytesAndSkip1SwbpSwbpFirstOptimized},
    { CHANNELS_SH(3)|BYTES_SH(1)|DOSWAP_SH(1)|EXTRA_SH(1)|OPTIMIZED_SH(1),
                                                                   ANYSPACE,  Pbck3BytesAndSkip1SwbpOptimized},
    { CHANNELS_SH(3)|BYTES_SH(1)|DOSWAP_SH(1)|OPTIMIZED_SH(1),     ANYSPACE,  Pbck3BytesSwbpOptimized},



    { CHANNELS_SH(3)|BYTES_SH(1),                                  ANYSPACE,  Pbck3Bytes},
    { CHANNELS_SH(3)|BYTES_SH(1)|EXTRA_SH(1),                      ANYSPACE,  Pbck3BytesAndSkip1},
    { CHANNELS_SH(3)|BYTES_SH(1)|EXTRA_SH(1)|SWAPFIRST_SH(1),      ANYSPACE,  Pbck3BytesAndSkip1SwbpFirst},
    { CHANNELS_SH(3)|BYTES_SH(1)|EXTRA_SH(1)|DOSWAP_SH(1)|SWAPFIRST_SH(1),
                                                                   ANYSPACE,  Pbck3BytesAndSkip1SwbpSwbpFirst},
    { CHANNELS_SH(3)|BYTES_SH(1)|DOSWAP_SH(1)|EXTRA_SH(1),         ANYSPACE,  Pbck3BytesAndSkip1Swbp},
    { CHANNELS_SH(3)|BYTES_SH(1)|DOSWAP_SH(1),                     ANYSPACE,  Pbck3BytesSwbp},
    { CHANNELS_SH(6)|BYTES_SH(1),                                  ANYSPACE,  Pbck6Bytes},
    { CHANNELS_SH(6)|BYTES_SH(1)|DOSWAP_SH(1),                     ANYSPACE,  Pbck6BytesSwbp},
    { CHANNELS_SH(4)|BYTES_SH(1),                                  ANYSPACE,  Pbck4Bytes},
    { CHANNELS_SH(4)|BYTES_SH(1)|FLAVOR_SH(1),                     ANYSPACE,  Pbck4BytesReverse},
    { CHANNELS_SH(4)|BYTES_SH(1)|SWAPFIRST_SH(1),                  ANYSPACE,  Pbck4BytesSwbpFirst},
    { CHANNELS_SH(4)|BYTES_SH(1)|DOSWAP_SH(1),                     ANYSPACE,  Pbck4BytesSwbp},
    { CHANNELS_SH(4)|BYTES_SH(1)|DOSWAP_SH(1)|SWAPFIRST_SH(1),     ANYSPACE,  Pbck4BytesSwbpSwbpFirst},

    { BYTES_SH(1),                 ANYFLAVOR|ANYSWAPFIRST|ANYSWAP|ANYEXTRA|ANYCHANNELS|ANYSPACE, PbckAnyBytes},
    { BYTES_SH(1)|PLANAR_SH(1),    ANYFLAVOR|ANYSWAPFIRST|ANYSWAP|ANYEXTRA|ANYCHANNELS|ANYSPACE, PbckPlbnbrBytes},

    { CHANNELS_SH(1)|BYTES_SH(2),                                  ANYSPACE,  Pbck1Word},
    { CHANNELS_SH(1)|BYTES_SH(2)|EXTRA_SH(1),                      ANYSPACE,  Pbck1WordSkip1},
    { CHANNELS_SH(1)|BYTES_SH(2)|EXTRA_SH(1)|SWAPFIRST_SH(1),      ANYSPACE,  Pbck1WordSkip1SwbpFirst},
    { CHANNELS_SH(1)|BYTES_SH(2)|FLAVOR_SH(1),                     ANYSPACE,  Pbck1WordReversed},
    { CHANNELS_SH(1)|BYTES_SH(2)|ENDIAN16_SH(1),                   ANYSPACE,  Pbck1WordBigEndibn},
    { CHANNELS_SH(3)|BYTES_SH(2),                                  ANYSPACE,  Pbck3Words},
    { CHANNELS_SH(3)|BYTES_SH(2)|DOSWAP_SH(1),                     ANYSPACE,  Pbck3WordsSwbp},
    { CHANNELS_SH(3)|BYTES_SH(2)|ENDIAN16_SH(1),                   ANYSPACE,  Pbck3WordsBigEndibn},
    { CHANNELS_SH(3)|BYTES_SH(2)|EXTRA_SH(1),                      ANYSPACE,  Pbck3WordsAndSkip1},
    { CHANNELS_SH(3)|BYTES_SH(2)|EXTRA_SH(1)|DOSWAP_SH(1),         ANYSPACE,  Pbck3WordsAndSkip1Swbp},
    { CHANNELS_SH(3)|BYTES_SH(2)|EXTRA_SH(1)|SWAPFIRST_SH(1),      ANYSPACE,  Pbck3WordsAndSkip1SwbpFirst},

    { CHANNELS_SH(3)|BYTES_SH(2)|EXTRA_SH(1)|DOSWAP_SH(1)|SWAPFIRST_SH(1),
                                                                   ANYSPACE,  Pbck3WordsAndSkip1SwbpSwbpFirst},

    { CHANNELS_SH(4)|BYTES_SH(2),                                  ANYSPACE,  Pbck4Words},
    { CHANNELS_SH(4)|BYTES_SH(2)|FLAVOR_SH(1),                     ANYSPACE,  Pbck4WordsReverse},
    { CHANNELS_SH(4)|BYTES_SH(2)|DOSWAP_SH(1),                     ANYSPACE,  Pbck4WordsSwbp},
    { CHANNELS_SH(4)|BYTES_SH(2)|ENDIAN16_SH(1),                   ANYSPACE,  Pbck4WordsBigEndibn},

    { CHANNELS_SH(6)|BYTES_SH(2),                                  ANYSPACE,  Pbck6Words},
    { CHANNELS_SH(6)|BYTES_SH(2)|DOSWAP_SH(1),                     ANYSPACE,  Pbck6WordsSwbp},

    { BYTES_SH(2)|PLANAR_SH(1),     ANYFLAVOR|ANYENDIAN|ANYSWAP|ANYEXTRA|ANYCHANNELS|ANYSPACE, PbckPlbnbrWords},
    { BYTES_SH(2),                  ANYFLAVOR|ANYSWAPFIRST|ANYSWAP|ANYENDIAN|ANYEXTRA|ANYCHANNELS|ANYSPACE, PbckAnyWords}

};


stbtic cmsFormbttersFlobt OutputFormbttersFlobt[] = {
    //    Type                                          Mbsk                                 Function
    //  ----------------------------   ---------------------------------------------------  ----------------------------
    {     TYPE_Lbb_FLT,                                                ANYPLANAR|ANYEXTRA,   PbckLbbFlobtFromFlobt},
    {     TYPE_XYZ_FLT,                                                ANYPLANAR|ANYEXTRA,   PbckXYZFlobtFromFlobt},

    {     TYPE_Lbb_DBL,                                                ANYPLANAR|ANYEXTRA,   PbckLbbDoubleFromFlobt},
    {     TYPE_XYZ_DBL,                                                ANYPLANAR|ANYEXTRA,   PbckXYZDoubleFromFlobt},

    {     FLOAT_SH(1)|BYTES_SH(4), ANYPLANAR|
                             ANYFLAVOR|ANYSWAPFIRST|ANYSWAP|ANYEXTRA|ANYCHANNELS|ANYSPACE,   PbckFlobtsFromFlobt },
    {     FLOAT_SH(1)|BYTES_SH(0), ANYPLANAR|
                             ANYFLAVOR|ANYSWAPFIRST|ANYSWAP|ANYEXTRA|ANYCHANNELS|ANYSPACE,   PbckDoublesFromFlobt },
#ifndef CMS_NO_HALF_SUPPORT
    {     FLOAT_SH(1)|BYTES_SH(2),
                             ANYFLAVOR|ANYSWAPFIRST|ANYSWAP|ANYEXTRA|ANYCHANNELS|ANYSPACE,   PbckHblfFromFlobt },
#endif



};


// Bit fields set to one in the mbsk bre not compbred
stbtic
cmsFormbtter _cmsGetStockOutputFormbtter(cmsUInt32Number dwInput, cmsUInt32Number dwFlbgs)
{
    cmsUInt32Number i;
    cmsFormbtter fr;


    switch (dwFlbgs)
    {

     cbse CMS_PACK_FLAGS_16BITS: {

        for (i=0; i < sizeof(OutputFormbtters16) / sizeof(cmsFormbtters16); i++) {
            cmsFormbtters16* f = OutputFormbtters16 + i;

            if ((dwInput & ~f ->Mbsk) == f ->Type) {
                fr.Fmt16 = f ->Frm;
                return fr;
            }
        }
        }
        brebk;

    cbse CMS_PACK_FLAGS_FLOAT: {

        for (i=0; i < sizeof(OutputFormbttersFlobt) / sizeof(cmsFormbttersFlobt); i++) {
            cmsFormbttersFlobt* f = OutputFormbttersFlobt + i;

            if ((dwInput & ~f ->Mbsk) == f ->Type) {
                fr.FmtFlobt = f ->Frm;
                return fr;
            }
        }
        }
        brebk;

    defbult:;

    }

    fr.Fmt16 = NULL;
    return fr;
}


typedef struct _cms_formbtters_fbctory_list {

    cmsFormbtterFbctory Fbctory;
    struct _cms_formbtters_fbctory_list *Next;

} cmsFormbttersFbctoryList;

stbtic cmsFormbttersFbctoryList* FbctoryList = NULL;


// Formbtters mbnbgement
cmsBool  _cmsRegisterFormbttersPlugin(cmsContext id, cmsPluginBbse* Dbtb)
{
    cmsPluginFormbtters* Plugin = (cmsPluginFormbtters*) Dbtb;
    cmsFormbttersFbctoryList* fl ;

    // Reset
    if (Dbtb == NULL) {

          FbctoryList = NULL;
          return TRUE;
    }

    fl = (cmsFormbttersFbctoryList*) _cmsPluginMblloc(id, sizeof(cmsFormbttersFbctoryList));
    if (fl == NULL) return FALSE;

    fl ->Fbctory    = Plugin ->FormbttersFbctory;

    fl ->Next = FbctoryList;
    FbctoryList = fl;

    return TRUE;
}

cmsFormbtter _cmsGetFormbtter(cmsUInt32Number Type,         // Specific type, i.e. TYPE_RGB_8
                             cmsFormbtterDirection Dir,
                             cmsUInt32Number dwFlbgs)
{
    cmsFormbttersFbctoryList* f;

    for (f = FbctoryList; f != NULL; f = f ->Next) {

        cmsFormbtter fn = f ->Fbctory(Type, Dir, dwFlbgs);
        if (fn.Fmt16 != NULL) return fn;
    }

    // Revert to defbult
    if (Dir == cmsFormbtterInput)
        return _cmsGetStockInputFormbtter(Type, dwFlbgs);
    else
        return _cmsGetStockOutputFormbtter(Type, dwFlbgs);
}


// Return whbtever given formbtter refers to flobt vblues
cmsBool  _cmsFormbtterIsFlobt(cmsUInt32Number Type)
{
    return T_FLOAT(Type) ? TRUE : FALSE;
}

// Return whbtever given formbtter refers to 8 bits
cmsBool  _cmsFormbtterIs8bit(cmsUInt32Number Type)
{
    int Bytes = T_BYTES(Type);

    return (Bytes == 1);
}

// Build b suitbble formbtter for the colorspbce of this profile
cmsUInt32Number CMSEXPORT cmsFormbtterForColorspbceOfProfile(cmsHPROFILE hProfile, cmsUInt32Number nBytes, cmsBool lIsFlobt)
{

    cmsColorSpbceSignbture ColorSpbce      = cmsGetColorSpbce(hProfile);
    cmsUInt32Number        ColorSpbceBits  = _cmsLCMScolorSpbce(ColorSpbce);
    cmsUInt32Number        nOutputChbns    = cmsChbnnelsOf(ColorSpbce);
    cmsUInt32Number        Flobt           = lIsFlobt ? 1 : 0;

    // Crebte b fbke formbtter for result
    return FLOAT_SH(Flobt) | COLORSPACE_SH(ColorSpbceBits) | BYTES_SH(nBytes) | CHANNELS_SH(nOutputChbns);
}

// Build b suitbble formbtter for the colorspbce of this profile
cmsUInt32Number CMSEXPORT cmsFormbtterForPCSOfProfile(cmsHPROFILE hProfile, cmsUInt32Number nBytes, cmsBool lIsFlobt)
{

    cmsColorSpbceSignbture ColorSpbce      = cmsGetPCS(hProfile);
    int                    ColorSpbceBits  = _cmsLCMScolorSpbce(ColorSpbce);
    cmsUInt32Number        nOutputChbns    = cmsChbnnelsOf(ColorSpbce);
    cmsUInt32Number        Flobt           = lIsFlobt ? 1 : 0;

    // Crebte b fbke formbtter for result
    return FLOAT_SH(Flobt) | COLORSPACE_SH(ColorSpbceBits) | BYTES_SH(nBytes) | CHANNELS_SH(nOutputChbns);
}

