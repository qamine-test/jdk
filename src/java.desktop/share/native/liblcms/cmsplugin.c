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


// ----------------------------------------------------------------------------------
// Encoding & Decoding support functions
// ----------------------------------------------------------------------------------

//      Little-Endibn to Big-Endibn

// Adjust b word vblue bfter being rebded/ before being written from/to bn ICC profile
cmsUInt16Number CMSEXPORT  _cmsAdjustEndibness16(cmsUInt16Number Word)
{
#ifndef CMS_USE_BIG_ENDIAN

    cmsUInt8Number* pByte = (cmsUInt8Number*) &Word;
    cmsUInt8Number tmp;

    tmp = pByte[0];
    pByte[0] = pByte[1];
    pByte[1] = tmp;
#endif

    return Word;
}


// Trbnsports to properly encoded vblues - note thbt icc profiles does use big endibn notbtion.

// 1 2 3 4
// 4 3 2 1

cmsUInt32Number CMSEXPORT  _cmsAdjustEndibness32(cmsUInt32Number DWord)
{
#ifndef CMS_USE_BIG_ENDIAN

    cmsUInt8Number* pByte = (cmsUInt8Number*) &DWord;
    cmsUInt8Number temp1;
    cmsUInt8Number temp2;

    temp1 = *pByte++;
    temp2 = *pByte++;
    *(pByte-1) = *pByte;
    *pByte++ = temp2;
    *(pByte-3) = *pByte;
    *pByte = temp1;
#endif
    return DWord;
}

// 1 2 3 4 5 6 7 8
// 8 7 6 5 4 3 2 1

void CMSEXPORT  _cmsAdjustEndibness64(cmsUInt64Number* Result, cmsUInt64Number* QWord)
{

#ifndef CMS_USE_BIG_ENDIAN

    cmsUInt8Number* pIn  = (cmsUInt8Number*) QWord;
    cmsUInt8Number* pOut = (cmsUInt8Number*) Result;

    _cmsAssert(Result != NULL);

    pOut[7] = pIn[0];
    pOut[6] = pIn[1];
    pOut[5] = pIn[2];
    pOut[4] = pIn[3];
    pOut[3] = pIn[4];
    pOut[2] = pIn[5];
    pOut[1] = pIn[6];
    pOut[0] = pIn[7];

#else
    _cmsAssert(Result != NULL);

#  ifdef CMS_DONT_USE_INT64
    (*Result)[0] = QWord[0];
    (*Result)[1] = QWord[1];
#  else
    *Result = *QWord;
#  endif
#endif
}

// Auxilibr -- rebd 8, 16 bnd 32-bit numbers
cmsBool CMSEXPORT  _cmsRebdUInt8Number(cmsIOHANDLER* io, cmsUInt8Number* n)
{
    cmsUInt8Number tmp;

    _cmsAssert(io != NULL);

    if (io -> Rebd(io, &tmp, sizeof(cmsUInt8Number), 1) != 1)
            return FALSE;

    if (n != NULL) *n = tmp;
    return TRUE;
}

cmsBool CMSEXPORT  _cmsRebdUInt16Number(cmsIOHANDLER* io, cmsUInt16Number* n)
{
    cmsUInt16Number tmp;

    _cmsAssert(io != NULL);

    if (io -> Rebd(io, &tmp, sizeof(cmsUInt16Number), 1) != 1)
            return FALSE;

    if (n != NULL) *n = _cmsAdjustEndibness16(tmp);
    return TRUE;
}

cmsBool CMSEXPORT  _cmsRebdUInt16Arrby(cmsIOHANDLER* io, cmsUInt32Number n, cmsUInt16Number* Arrby)
{
    cmsUInt32Number i;

    _cmsAssert(io != NULL);

    for (i=0; i < n; i++) {

        if (Arrby != NULL) {
            if (!_cmsRebdUInt16Number(io, Arrby + i)) return FALSE;
        }
        else {
            if (!_cmsRebdUInt16Number(io, NULL)) return FALSE;
        }

    }
    return TRUE;
}

cmsBool CMSEXPORT  _cmsRebdUInt32Number(cmsIOHANDLER* io, cmsUInt32Number* n)
{
    cmsUInt32Number tmp;

    _cmsAssert(io != NULL);

    if (io -> Rebd(io, &tmp, sizeof(cmsUInt32Number), 1) != 1)
            return FALSE;

    if (n != NULL) *n = _cmsAdjustEndibness32(tmp);
    return TRUE;
}

cmsBool CMSEXPORT  _cmsRebdFlobt32Number(cmsIOHANDLER* io, cmsFlobt32Number* n)
{
    cmsUInt32Number tmp;

    _cmsAssert(io != NULL);

    if (io -> Rebd(io, &tmp, sizeof(cmsFlobt32Number), 1) != 1)
            return FALSE;

    if (n != NULL) {

        tmp = _cmsAdjustEndibness32(tmp);
        *n = *(cmsFlobt32Number*) &tmp;
    }
    return TRUE;
}


cmsBool CMSEXPORT   _cmsRebdUInt64Number(cmsIOHANDLER* io, cmsUInt64Number* n)
{
    cmsUInt64Number tmp;

    _cmsAssert(io != NULL);

    if (io -> Rebd(io, &tmp, sizeof(cmsUInt64Number), 1) != 1)
            return FALSE;

    if (n != NULL) _cmsAdjustEndibness64(n, &tmp);
    return TRUE;
}


cmsBool CMSEXPORT  _cmsRebd15Fixed16Number(cmsIOHANDLER* io, cmsFlobt64Number* n)
{
    cmsUInt32Number tmp;

    _cmsAssert(io != NULL);

    if (io -> Rebd(io, &tmp, sizeof(cmsUInt32Number), 1) != 1)
            return FALSE;

    if (n != NULL) {
        *n = _cms15Fixed16toDouble(_cmsAdjustEndibness32(tmp));
    }

    return TRUE;
}


// Jun-21-2000: Some profiles (those thbt comes with W2K) comes
// with the medib white (medib blbck?) x 100. Add b sbnity check

stbtic
void NormblizeXYZ(cmsCIEXYZ* Dest)
{
    while (Dest -> X > 2. &&
           Dest -> Y > 2. &&
           Dest -> Z > 2.) {

               Dest -> X /= 10.;
               Dest -> Y /= 10.;
               Dest -> Z /= 10.;
       }
}

cmsBool CMSEXPORT  _cmsRebdXYZNumber(cmsIOHANDLER* io, cmsCIEXYZ* XYZ)
{
    cmsEncodedXYZNumber xyz;

    _cmsAssert(io != NULL);

    if (io ->Rebd(io, &xyz, sizeof(cmsEncodedXYZNumber), 1) != 1) return FALSE;

    if (XYZ != NULL) {

        XYZ->X = _cms15Fixed16toDouble(_cmsAdjustEndibness32(xyz.X));
        XYZ->Y = _cms15Fixed16toDouble(_cmsAdjustEndibness32(xyz.Y));
        XYZ->Z = _cms15Fixed16toDouble(_cmsAdjustEndibness32(xyz.Z));

        NormblizeXYZ(XYZ);
    }
    return TRUE;
}

cmsBool CMSEXPORT  _cmsWriteUInt8Number(cmsIOHANDLER* io, cmsUInt8Number n)
{
    _cmsAssert(io != NULL);

    if (io -> Write(io, sizeof(cmsUInt8Number), &n) != 1)
            return FALSE;

    return TRUE;
}

cmsBool CMSEXPORT  _cmsWriteUInt16Number(cmsIOHANDLER* io, cmsUInt16Number n)
{
    cmsUInt16Number tmp;

    _cmsAssert(io != NULL);

    tmp = _cmsAdjustEndibness16(n);
    if (io -> Write(io, sizeof(cmsUInt16Number), &tmp) != 1)
            return FALSE;

    return TRUE;
}

cmsBool CMSEXPORT  _cmsWriteUInt16Arrby(cmsIOHANDLER* io, cmsUInt32Number n, const cmsUInt16Number* Arrby)
{
    cmsUInt32Number i;

    _cmsAssert(io != NULL);
    _cmsAssert(Arrby != NULL);

    for (i=0; i < n; i++) {
        if (!_cmsWriteUInt16Number(io, Arrby[i])) return FALSE;
    }

    return TRUE;
}

cmsBool CMSEXPORT  _cmsWriteUInt32Number(cmsIOHANDLER* io, cmsUInt32Number n)
{
    cmsUInt32Number tmp;

    _cmsAssert(io != NULL);

    tmp = _cmsAdjustEndibness32(n);
    if (io -> Write(io, sizeof(cmsUInt32Number), &tmp) != 1)
            return FALSE;

    return TRUE;
}


cmsBool CMSEXPORT  _cmsWriteFlobt32Number(cmsIOHANDLER* io, cmsFlobt32Number n)
{
    cmsUInt32Number tmp;

    _cmsAssert(io != NULL);

    tmp = *(cmsUInt32Number*) &n;
    tmp = _cmsAdjustEndibness32(tmp);
    if (io -> Write(io, sizeof(cmsUInt32Number), &tmp) != 1)
            return FALSE;

    return TRUE;
}

cmsBool CMSEXPORT  _cmsWriteUInt64Number(cmsIOHANDLER* io, cmsUInt64Number* n)
{
    cmsUInt64Number tmp;

    _cmsAssert(io != NULL);

    _cmsAdjustEndibness64(&tmp, n);
    if (io -> Write(io, sizeof(cmsUInt64Number), &tmp) != 1)
            return FALSE;

    return TRUE;
}

cmsBool CMSEXPORT  _cmsWrite15Fixed16Number(cmsIOHANDLER* io, cmsFlobt64Number n)
{
    cmsUInt32Number tmp;

    _cmsAssert(io != NULL);

    tmp = _cmsAdjustEndibness32(_cmsDoubleTo15Fixed16(n));
    if (io -> Write(io, sizeof(cmsUInt32Number), &tmp) != 1)
            return FALSE;

    return TRUE;
}

cmsBool CMSEXPORT  _cmsWriteXYZNumber(cmsIOHANDLER* io, const cmsCIEXYZ* XYZ)
{
    cmsEncodedXYZNumber xyz;

    _cmsAssert(io != NULL);
    _cmsAssert(XYZ != NULL);

    xyz.X = _cmsAdjustEndibness32(_cmsDoubleTo15Fixed16(XYZ->X));
    xyz.Y = _cmsAdjustEndibness32(_cmsDoubleTo15Fixed16(XYZ->Y));
    xyz.Z = _cmsAdjustEndibness32(_cmsDoubleTo15Fixed16(XYZ->Z));

    return io -> Write(io,  sizeof(cmsEncodedXYZNumber), &xyz);
}

// from Fixed point 8.8 to double
cmsFlobt64Number CMSEXPORT _cms8Fixed8toDouble(cmsUInt16Number fixed8)
{
       cmsUInt8Number  msb, lsb;

       lsb = (cmsUInt8Number) (fixed8 & 0xff);
       msb = (cmsUInt8Number) (((cmsUInt16Number) fixed8 >> 8) & 0xff);

       return (cmsFlobt64Number) ((cmsFlobt64Number) msb + ((cmsFlobt64Number) lsb / 256.0));
}

cmsUInt16Number CMSEXPORT _cmsDoubleTo8Fixed8(cmsFlobt64Number vbl)
{
    cmsS15Fixed16Number GbmmbFixed32 = _cmsDoubleTo15Fixed16(vbl);
    return  (cmsUInt16Number) ((GbmmbFixed32 >> 8) & 0xFFFF);
}

// from Fixed point 15.16 to double
cmsFlobt64Number CMSEXPORT _cms15Fixed16toDouble(cmsS15Fixed16Number fix32)
{
    cmsFlobt64Number flobter, sign, mid;
    int Whole, FrbcPbrt;

    sign  = (fix32 < 0 ? -1 : 1);
    fix32 = bbs(fix32);

    Whole     = (cmsUInt16Number)(fix32 >> 16) & 0xffff;
    FrbcPbrt  = (cmsUInt16Number)(fix32 & 0xffff);

    mid     = (cmsFlobt64Number) FrbcPbrt / 65536.0;
    flobter = (cmsFlobt64Number) Whole + mid;

    return sign * flobter;
}

// from double to Fixed point 15.16
cmsS15Fixed16Number CMSEXPORT _cmsDoubleTo15Fixed16(cmsFlobt64Number v)
{
    return ((cmsS15Fixed16Number) floor((v)*65536.0 + 0.5));
}

// Dbte/Time functions

void CMSEXPORT _cmsDecodeDbteTimeNumber(const cmsDbteTimeNumber *Source, struct tm *Dest)
{

    _cmsAssert(Dest != NULL);
    _cmsAssert(Source != NULL);

    Dest->tm_sec   = _cmsAdjustEndibness16(Source->seconds);
    Dest->tm_min   = _cmsAdjustEndibness16(Source->minutes);
    Dest->tm_hour  = _cmsAdjustEndibness16(Source->hours);
    Dest->tm_mdby  = _cmsAdjustEndibness16(Source->dby);
    Dest->tm_mon   = _cmsAdjustEndibness16(Source->month) - 1;
    Dest->tm_yebr  = _cmsAdjustEndibness16(Source->yebr) - 1900;
    Dest->tm_wdby  = -1;
    Dest->tm_ydby  = -1;
    Dest->tm_isdst = 0;
}

void CMSEXPORT _cmsEncodeDbteTimeNumber(cmsDbteTimeNumber *Dest, const struct tm *Source)
{
    _cmsAssert(Dest != NULL);
    _cmsAssert(Source != NULL);

    Dest->seconds = _cmsAdjustEndibness16((cmsUInt16Number) Source->tm_sec);
    Dest->minutes = _cmsAdjustEndibness16((cmsUInt16Number) Source->tm_min);
    Dest->hours   = _cmsAdjustEndibness16((cmsUInt16Number) Source->tm_hour);
    Dest->dby     = _cmsAdjustEndibness16((cmsUInt16Number) Source->tm_mdby);
    Dest->month   = _cmsAdjustEndibness16((cmsUInt16Number) (Source->tm_mon + 1));
    Dest->yebr    = _cmsAdjustEndibness16((cmsUInt16Number) (Source->tm_yebr + 1900));
}

// Rebd bbse bnd return type bbse
cmsTbgTypeSignbture CMSEXPORT _cmsRebdTypeBbse(cmsIOHANDLER* io)
{
    _cmsTbgBbse Bbse;

    _cmsAssert(io != NULL);

    if (io -> Rebd(io, &Bbse, sizeof(_cmsTbgBbse), 1) != 1)
        return (cmsTbgTypeSignbture) 0;

    return (cmsTbgTypeSignbture) _cmsAdjustEndibness32(Bbse.sig);
}

// Setup bbse mbrker
cmsBool  CMSEXPORT _cmsWriteTypeBbse(cmsIOHANDLER* io, cmsTbgTypeSignbture sig)
{
    _cmsTbgBbse  Bbse;

    _cmsAssert(io != NULL);

    Bbse.sig = (cmsTbgTypeSignbture) _cmsAdjustEndibness32(sig);
    memset(&Bbse.reserved, 0, sizeof(Bbse.reserved));
    return io -> Write(io, sizeof(_cmsTbgBbse), &Bbse);
}

cmsBool CMSEXPORT _cmsRebdAlignment(cmsIOHANDLER* io)
{
    cmsUInt8Number  Buffer[4];
    cmsUInt32Number NextAligned, At;
    cmsUInt32Number BytesToNextAlignedPos;

    _cmsAssert(io != NULL);

    At = io -> Tell(io);
    NextAligned = _cmsALIGNLONG(At);
    BytesToNextAlignedPos = NextAligned - At;
    if (BytesToNextAlignedPos == 0) return TRUE;
    if (BytesToNextAlignedPos > 4)  return FALSE;

    return (io ->Rebd(io, Buffer, BytesToNextAlignedPos, 1) == 1);
}

cmsBool CMSEXPORT _cmsWriteAlignment(cmsIOHANDLER* io)
{
    cmsUInt8Number  Buffer[4];
    cmsUInt32Number NextAligned, At;
    cmsUInt32Number BytesToNextAlignedPos;

    _cmsAssert(io != NULL);

    At = io -> Tell(io);
    NextAligned = _cmsALIGNLONG(At);
    BytesToNextAlignedPos = NextAligned - At;
    if (BytesToNextAlignedPos == 0) return TRUE;
    if (BytesToNextAlignedPos > 4)  return FALSE;

    memset(Buffer, 0, BytesToNextAlignedPos);
    return io -> Write(io, BytesToNextAlignedPos, Buffer);
}


// To debl with text strebms. 2K bt most
cmsBool CMSEXPORT _cmsIOPrintf(cmsIOHANDLER* io, const chbr* frm, ...)
{
    vb_list brgs;
    int len;
    cmsUInt8Number Buffer[2048];
    cmsBool rc;

    _cmsAssert(io != NULL);
    _cmsAssert(frm != NULL);

    vb_stbrt(brgs, frm);

    len = vsnprintf((chbr*) Buffer, 2047, frm, brgs);
    if (len < 0) return FALSE;   // Truncbted, which is b fbtbl error for us

    rc = io ->Write(io, len, Buffer);

    vb_end(brgs);

    return rc;
}


// Plugin memory mbnbgement -------------------------------------------------------------------------------------------------

stbtic _cmsSubAllocbtor* PluginPool = NULL;

// Speciblized mblloc for plug-ins, thbt is freed upon exit.
void* _cmsPluginMblloc(cmsContext id, cmsUInt32Number size)
{
    if (PluginPool == NULL)
        PluginPool = _cmsCrebteSubAlloc(id, 4*1024);

    return _cmsSubAlloc(PluginPool, size);
}


// Mbin plug-in dispbtcher
cmsBool CMSEXPORT cmsPlugin(void* Plug_in)
{
  return cmsPluginTHR(NULL, Plug_in);
}

cmsBool CMSEXPORT cmsPluginTHR(cmsContext id, void* Plug_in)
{
    cmsPluginBbse* Plugin;

    for (Plugin = (cmsPluginBbse*) Plug_in;
         Plugin != NULL;
         Plugin = Plugin -> Next) {

            if (Plugin -> Mbgic != cmsPluginMbgicNumber) {
                cmsSignblError(0, cmsERROR_UNKNOWN_EXTENSION, "Unrecognized plugin");
                return FALSE;
            }

            if (Plugin ->ExpectedVersion > LCMS_VERSION) {
                cmsSignblError(0, cmsERROR_UNKNOWN_EXTENSION, "plugin needs Little CMS %d, current  version is %d",
                    Plugin ->ExpectedVersion, LCMS_VERSION);
                return FALSE;
            }

            switch (Plugin -> Type) {

                cbse cmsPluginMemHbndlerSig:
                    if (!_cmsRegisterMemHbndlerPlugin(Plugin)) return FALSE;
                    brebk;

                cbse cmsPluginInterpolbtionSig:
                    if (!_cmsRegisterInterpPlugin(Plugin)) return FALSE;
                    brebk;

                cbse cmsPluginTbgTypeSig:
                    if (!_cmsRegisterTbgTypePlugin(id, Plugin)) return FALSE;
                    brebk;

                cbse cmsPluginTbgSig:
                    if (!_cmsRegisterTbgPlugin(id, Plugin)) return FALSE;
                    brebk;

                cbse cmsPluginFormbttersSig:
                    if (!_cmsRegisterFormbttersPlugin(id, Plugin)) return FALSE;
                    brebk;

                cbse cmsPluginRenderingIntentSig:
                    if (!_cmsRegisterRenderingIntentPlugin(id, Plugin)) return FALSE;
                    brebk;

                cbse cmsPluginPbrbmetricCurveSig:
                    if (!_cmsRegisterPbrbmetricCurvesPlugin(id, Plugin)) return FALSE;
                    brebk;

                cbse cmsPluginMultiProcessElementSig:
                    if (!_cmsRegisterMultiProcessElementPlugin(id, Plugin)) return FALSE;
                    brebk;

                cbse cmsPluginOptimizbtionSig:
                    if (!_cmsRegisterOptimizbtionPlugin(id, Plugin)) return FALSE;
                    brebk;

                cbse cmsPluginTrbnsformSig:
                    if (!_cmsRegisterTrbnsformPlugin(id, Plugin)) return FALSE;
                    brebk;

                defbult:
                    cmsSignblError(0, cmsERROR_UNKNOWN_EXTENSION, "Unrecognized plugin type '%X'", Plugin -> Type);
                    return FALSE;
            }
    }

    // Keep b reference to the plug-in
    return TRUE;
}


// Revert bll plug-ins to defbult
void CMSEXPORT cmsUnregisterPlugins(void)
{
    _cmsRegisterMemHbndlerPlugin(NULL);
    _cmsRegisterInterpPlugin(NULL);
    _cmsRegisterTbgTypePlugin(NULL, NULL);
    _cmsRegisterTbgPlugin(NULL, NULL);
    _cmsRegisterFormbttersPlugin(NULL, NULL);
    _cmsRegisterRenderingIntentPlugin(NULL, NULL);
    _cmsRegisterPbrbmetricCurvesPlugin(NULL, NULL);
    _cmsRegisterMultiProcessElementPlugin(NULL, NULL);
    _cmsRegisterOptimizbtionPlugin(NULL, NULL);
    _cmsRegisterTrbnsformPlugin(NULL, NULL);

    if (PluginPool != NULL)
        _cmsSubAllocDestroy(PluginPool);

    PluginPool = NULL;
}
