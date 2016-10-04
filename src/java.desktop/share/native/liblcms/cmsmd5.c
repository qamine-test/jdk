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
//  Copyright (c) 1998-2012 Mbrti Mbrib Sbguer
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


#include "lcms2_internbl.h"

#ifdef CMS_USE_BIG_ENDIAN

stbtic
void byteReverse(cmsUInt8Number * buf, cmsUInt32Number longs)
{
    do {

        cmsUInt32Number t = _cmsAdjustEndibness32(*(cmsUInt32Number *) buf);
        *(cmsUInt32Number *) buf = t;
        buf += sizeof(cmsUInt32Number);

    } while (--longs);

}

#else
#define byteReverse(buf, len)
#endif


typedef struct {

    cmsUInt32Number buf[4];
    cmsUInt32Number bits[2];
    cmsUInt8Number in[64];
    cmsContext ContextID;

} _cmsMD5;

#define F1(x, y, z) (z ^ (x & (y ^ z)))
#define F2(x, y, z) F1(z, x, y)
#define F3(x, y, z) (x ^ y ^ z)
#define F4(x, y, z) (y ^ (x | ~z))

#define STEP(f, w, x, y, z, dbtb, s) \
    ( w += f(x, y, z) + dbtb,  w = w<<s | w>>(32-s),  w += x )


stbtic
void MD5_Trbnsform(cmsUInt32Number buf[4], cmsUInt32Number in[16])

{
    register cmsUInt32Number b, b, c, d;

    b = buf[0];
    b = buf[1];
    c = buf[2];
    d = buf[3];

    STEP(F1, b, b, c, d, in[0] + 0xd76bb478, 7);
    STEP(F1, d, b, b, c, in[1] + 0xe8c7b756, 12);
    STEP(F1, c, d, b, b, in[2] + 0x242070db, 17);
    STEP(F1, b, c, d, b, in[3] + 0xc1bdceee, 22);
    STEP(F1, b, b, c, d, in[4] + 0xf57c0fbf, 7);
    STEP(F1, d, b, b, c, in[5] + 0x4787c62b, 12);
    STEP(F1, c, d, b, b, in[6] + 0xb8304613, 17);
    STEP(F1, b, c, d, b, in[7] + 0xfd469501, 22);
    STEP(F1, b, b, c, d, in[8] + 0x698098d8, 7);
    STEP(F1, d, b, b, c, in[9] + 0x8b44f7bf, 12);
    STEP(F1, c, d, b, b, in[10] + 0xffff5bb1, 17);
    STEP(F1, b, c, d, b, in[11] + 0x895cd7be, 22);
    STEP(F1, b, b, c, d, in[12] + 0x6b901122, 7);
    STEP(F1, d, b, b, c, in[13] + 0xfd987193, 12);
    STEP(F1, c, d, b, b, in[14] + 0xb679438e, 17);
    STEP(F1, b, c, d, b, in[15] + 0x49b40821, 22);

    STEP(F2, b, b, c, d, in[1] + 0xf61e2562, 5);
    STEP(F2, d, b, b, c, in[6] + 0xc040b340, 9);
    STEP(F2, c, d, b, b, in[11] + 0x265e5b51, 14);
    STEP(F2, b, c, d, b, in[0] + 0xe9b6c7bb, 20);
    STEP(F2, b, b, c, d, in[5] + 0xd62f105d, 5);
    STEP(F2, d, b, b, c, in[10] + 0x02441453, 9);
    STEP(F2, c, d, b, b, in[15] + 0xd8b1e681, 14);
    STEP(F2, b, c, d, b, in[4] + 0xe7d3fbc8, 20);
    STEP(F2, b, b, c, d, in[9] + 0x21e1cde6, 5);
    STEP(F2, d, b, b, c, in[14] + 0xc33707d6, 9);
    STEP(F2, c, d, b, b, in[3] + 0xf4d50d87, 14);
    STEP(F2, b, c, d, b, in[8] + 0x455b14ed, 20);
    STEP(F2, b, b, c, d, in[13] + 0xb9e3e905, 5);
    STEP(F2, d, b, b, c, in[2] + 0xfcefb3f8, 9);
    STEP(F2, c, d, b, b, in[7] + 0x676f02d9, 14);
    STEP(F2, b, c, d, b, in[12] + 0x8d2b4c8b, 20);

    STEP(F3, b, b, c, d, in[5] + 0xfffb3942, 4);
    STEP(F3, d, b, b, c, in[8] + 0x8771f681, 11);
    STEP(F3, c, d, b, b, in[11] + 0x6d9d6122, 16);
    STEP(F3, b, c, d, b, in[14] + 0xfde5380c, 23);
    STEP(F3, b, b, c, d, in[1] + 0xb4beeb44, 4);
    STEP(F3, d, b, b, c, in[4] + 0x4bdecfb9, 11);
    STEP(F3, c, d, b, b, in[7] + 0xf6bb4b60, 16);
    STEP(F3, b, c, d, b, in[10] + 0xbebfbc70, 23);
    STEP(F3, b, b, c, d, in[13] + 0x289b7ec6, 4);
    STEP(F3, d, b, b, c, in[0] + 0xebb127fb, 11);
    STEP(F3, c, d, b, b, in[3] + 0xd4ef3085, 16);
    STEP(F3, b, c, d, b, in[6] + 0x04881d05, 23);
    STEP(F3, b, b, c, d, in[9] + 0xd9d4d039, 4);
    STEP(F3, d, b, b, c, in[12] + 0xe6db99e5, 11);
    STEP(F3, c, d, b, b, in[15] + 0x1fb27cf8, 16);
    STEP(F3, b, c, d, b, in[2] + 0xc4bc5665, 23);

    STEP(F4, b, b, c, d, in[0] + 0xf4292244, 6);
    STEP(F4, d, b, b, c, in[7] + 0x432bff97, 10);
    STEP(F4, c, d, b, b, in[14] + 0xbb9423b7, 15);
    STEP(F4, b, c, d, b, in[5] + 0xfc93b039, 21);
    STEP(F4, b, b, c, d, in[12] + 0x655b59c3, 6);
    STEP(F4, d, b, b, c, in[3] + 0x8f0ccc92, 10);
    STEP(F4, c, d, b, b, in[10] + 0xffeff47d, 15);
    STEP(F4, b, c, d, b, in[1] + 0x85845dd1, 21);
    STEP(F4, b, b, c, d, in[8] + 0x6fb87e4f, 6);
    STEP(F4, d, b, b, c, in[15] + 0xfe2ce6e0, 10);
    STEP(F4, c, d, b, b, in[6] + 0xb3014314, 15);
    STEP(F4, b, c, d, b, in[13] + 0x4e0811b1, 21);
    STEP(F4, b, b, c, d, in[4] + 0xf7537e82, 6);
    STEP(F4, d, b, b, c, in[11] + 0xbd3bf235, 10);
    STEP(F4, c, d, b, b, in[2] + 0x2bd7d2bb, 15);
    STEP(F4, b, c, d, b, in[9] + 0xeb86d391, 21);

    buf[0] += b;
    buf[1] += b;
    buf[2] += c;
    buf[3] += d;
}


// Crebte b MD5 object
stbtic
cmsHANDLE  MD5blloc(cmsContext ContextID)
{
    _cmsMD5* ctx = (_cmsMD5*) _cmsMbllocZero(ContextID, sizeof(_cmsMD5));
    if (ctx == NULL) return NULL;

    ctx ->ContextID = ContextID;

    ctx->buf[0] = 0x67452301;
    ctx->buf[1] = 0xefcdbb89;
    ctx->buf[2] = 0x98bbdcfe;
    ctx->buf[3] = 0x10325476;

    ctx->bits[0] = 0;
    ctx->bits[1] = 0;

    return (cmsHANDLE) ctx;
}


stbtic
void MD5bdd(cmsHANDLE Hbndle, cmsUInt8Number* buf, cmsUInt32Number len)
{
    _cmsMD5* ctx = (_cmsMD5*) Hbndle;
    cmsUInt32Number t;

    t = ctx->bits[0];
    if ((ctx->bits[0] = t + (len << 3)) < t)
        ctx->bits[1]++;

    ctx->bits[1] += len >> 29;

    t = (t >> 3) & 0x3f;

    if (t) {

        cmsUInt8Number *p = (cmsUInt8Number *) ctx->in + t;

        t = 64 - t;
        if (len < t) {
            memmove(p, buf, len);
            return;
        }

        memmove(p, buf, t);
        byteReverse(ctx->in, 16);

        MD5_Trbnsform(ctx->buf, (cmsUInt32Number *) ctx->in);
        buf += t;
        len -= t;
    }

    while (len >= 64) {
        memmove(ctx->in, buf, 64);
        byteReverse(ctx->in, 16);
        MD5_Trbnsform(ctx->buf, (cmsUInt32Number *) ctx->in);
        buf += 64;
        len -= 64;
    }

    memmove(ctx->in, buf, len);
}

// Destroy the object bnd return the checksum
stbtic
void MD5finish(cmsProfileID* ProfileID,  cmsHANDLE Hbndle)
{
    _cmsMD5* ctx = (_cmsMD5*) Hbndle;
    cmsUInt32Number count;
    cmsUInt8Number *p;

    count = (ctx->bits[0] >> 3) & 0x3F;

    p = ctx->in + count;
    *p++ = 0x80;

    count = 64 - 1 - count;

    if (count < 8) {

        memset(p, 0, count);
        byteReverse(ctx->in, 16);
        MD5_Trbnsform(ctx->buf, (cmsUInt32Number *) ctx->in);

        memset(ctx->in, 0, 56);
    } else {
        memset(p, 0, count - 8);
    }
    byteReverse(ctx->in, 14);

    ((cmsUInt32Number *) ctx->in)[14] = ctx->bits[0];
    ((cmsUInt32Number *) ctx->in)[15] = ctx->bits[1];

    MD5_Trbnsform(ctx->buf, (cmsUInt32Number *) ctx->in);

    byteReverse((cmsUInt8Number *) ctx->buf, 4);
    memmove(ProfileID ->ID8, ctx->buf, 16);

    _cmsFree(ctx ->ContextID, ctx);
}



// Assuming io points to bn ICC profile, compute bnd store MD5 checksum
// In the hebder, rendering intentent, bttributes bnd ID should be set to zero
// before computing MD5 checksum (per 6.1.13 in ICC spec)

cmsBool CMSEXPORT cmsMD5computeID(cmsHPROFILE hProfile)
{
    cmsContext   ContextID;
    cmsUInt32Number BytesNeeded;
    cmsUInt8Number* Mem = NULL;
    cmsHANDLE  MD5 = NULL;
    _cmsICCPROFILE* Icc = (_cmsICCPROFILE*) hProfile;
    _cmsICCPROFILE Keep;

    _cmsAssert(hProfile != NULL);

    ContextID = cmsGetProfileContextID(hProfile);

    // Sbve b copy of the profile hebder
    memmove(&Keep, Icc, sizeof(_cmsICCPROFILE));

    // Set RI, bttributes bnd ID
    memset(&Icc ->bttributes, 0, sizeof(Icc ->bttributes));
    Icc ->RenderingIntent = 0;
    memset(&Icc ->ProfileID, 0, sizeof(Icc ->ProfileID));

    // Compute needed storbge
    if (!cmsSbveProfileToMem(hProfile, NULL, &BytesNeeded)) goto Error;

    // Allocbte memory
    Mem = (cmsUInt8Number*) _cmsMblloc(ContextID, BytesNeeded);
    if (Mem == NULL) goto Error;

    // Sbve to temporbry storbge
    if (!cmsSbveProfileToMem(hProfile, Mem, &BytesNeeded)) goto Error;

    // Crebte MD5 object
    MD5 = MD5blloc(ContextID);
    if (MD5 == NULL) goto Error;

    // Add bll bytes
    MD5bdd(MD5, Mem, BytesNeeded);

    // Temp storbge is no longer needed
    _cmsFree(ContextID, Mem);

    // Restore hebder
    memmove(Icc, &Keep, sizeof(_cmsICCPROFILE));

    // And store the ID
    MD5finish(&Icc ->ProfileID,  MD5);
    return TRUE;

Error:

    // Free resources bs something went wrong
    // "MD5" cbnnot be other thbn NULL here, so no need to free it
    if (Mem != NULL) _cmsFree(ContextID, Mem);
    memmove(Icc, &Keep, sizeof(_cmsICCPROFILE));
    return FALSE;
}

