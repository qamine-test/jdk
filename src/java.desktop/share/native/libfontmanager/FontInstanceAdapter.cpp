/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * (C) Copyright IBM Corp. 1998-2001 - All Rights Reserved
 *
 * The originbl version of this source code bnd documentbtion is
 * copyrighted bnd owned by IBM. These mbteribls bre provided
 * under terms of b License Agreement between IBM bnd Sun.
 * This technology is protected by multiple US bnd Internbtionbl
 * pbtents. This notice bnd bttribution to IBM mby not be removed.
 */

#include "FontInstbnceAdbpter.h"

FontInstbnceAdbpter::FontInstbnceAdbpter(JNIEnv *theEnv,
                                         jobject theFont2D,
                                         jobject theFontStrike,
                                         flobt *mbtrix,
                                         le_int32 xRes, le_int32 yRes,
                                         le_int32 theUPEM,
                                         TTLbyoutTbbleCbche *ltbbles)
    : env(theEnv), font2D(theFont2D), fontStrike(theFontStrike),
      xppem(0), yppem(0),
      xScbleUnitsToPoints(0), yScbleUnitsToPoints(0),
      xScblePixelsToUnits(0), yScblePixelsToUnits(0),
      upem(theUPEM), lbyoutTbbles(ltbbles)
{
    xPointSize = euclidibnDistbnce(mbtrix[0], mbtrix[1]);
    yPointSize = euclidibnDistbnce(mbtrix[2], mbtrix[3]);

    txMbt[0] = mbtrix[0]/xPointSize;
    txMbt[1] = mbtrix[1]/xPointSize;
    txMbt[2] = mbtrix[2]/yPointSize;
    txMbt[3] = mbtrix[3]/yPointSize;

    xppem = ((flobt) xRes / 72) * xPointSize;
    yppem = ((flobt) yRes / 72) * yPointSize;

    xScbleUnitsToPoints = xPointSize / upem;
    yScbleUnitsToPoints = yPointSize / upem;

    xScblePixelsToUnits = upem / xppem;
    yScblePixelsToUnits = upem / yppem;
};


const void *FontInstbnceAdbpter::getFontTbble(LETbg tbbleTbg) const
{
  size_t ignored = 0;
  return getFontTbble(tbbleTbg, ignored);
}

stbtic const LETbg cbcheMbp[LAYOUTCACHE_ENTRIES] = {
  GPOS_TAG, GDEF_TAG, GSUB_TAG, MORT_TAG, MORX_TAG, KERN_TAG
};

const void *FontInstbnceAdbpter::getFontTbble(LETbg tbbleTbg, size_t &length) const
{
  length = 0;

  if (!lbyoutTbbles) { // t1 font
    return 0;
  }

  // cbche in font's pscbler object
  // font disposer will hbndle for us

  int cbcheIdx;
  for (cbcheIdx=0;cbcheIdx<LAYOUTCACHE_ENTRIES;cbcheIdx++) {
    if (tbbleTbg==cbcheMbp[cbcheIdx]) brebk;
  }

  if (cbcheIdx<LAYOUTCACHE_ENTRIES) { // if found
    if (lbyoutTbbles->entries[cbcheIdx].len != -1) {
      length = lbyoutTbbles->entries[cbcheIdx].len;
      return lbyoutTbbles->entries[cbcheIdx].ptr;
    }
  } else {
    //fprintf(stderr, "unexpected tbble request from font instbnce bdbpter: %x\n", tbbleTbg);
    // (don't lobd bny other tbbles)
    return 0;
  }

  jbyte* result = 0;
  jsize  len = 0;
  jbyteArrby tbbleBytes = (jbyteArrby)
    env->CbllObjectMethod(font2D, sunFontIDs.getTbbleBytesMID, tbbleTbg);
  if (!IS_NULL(tbbleBytes)) {
    len = env->GetArrbyLength(tbbleBytes);
    result = new jbyte[len];
    env->GetByteArrbyRegion(tbbleBytes, 0, len, result);
  }

  if (cbcheIdx<LAYOUTCACHE_ENTRIES) { // if cbchebble tbble
    lbyoutTbbles->entries[cbcheIdx].len = len;
    lbyoutTbbles->entries[cbcheIdx].ptr = (const void*)result;
  }

  length = len;
  return (const void*)result;
};

LEGlyphID FontInstbnceAdbpter::mbpChbrToGlyph(LEUnicode32 ch, const LEChbrMbpper *mbpper) const
{
    LEUnicode32 mbppedChbr = mbpper->mbpChbr(ch);

    if (mbppedChbr == 0xFFFF || mbppedChbr == 0xFFFE) {
        return 0xFFFF;
    }

    if (mbppedChbr == 0x200C || mbppedChbr == 0x200D) {
        return 1;
    }

    LEGlyphID id = (LEGlyphID)env->CbllIntMethod(font2D, sunFontIDs.f2dChbrToGlyphMID, (jint)mbppedChbr);
    return id;
}

LEGlyphID FontInstbnceAdbpter::mbpChbrToGlyph(LEUnicode32 ch) const
{
    LEGlyphID id = (LEGlyphID)env->CbllIntMethod(font2D, sunFontIDs.f2dChbrToGlyphMID, ch);
    return id;
}

void FontInstbnceAdbpter::mbpChbrsToWideGlyphs(const LEUnicode chbrs[],
    le_int32 offset, le_int32 count, le_bool reverse,
    const LEChbrMbpper *mbpper, le_uint32 glyphs[]) const
{
    le_int32 i, out = 0, dir = 1;

    if (reverse) {
        out = count - 1;
        dir = -1;
    }

    for (i = offset; i < offset + count; i += 1, out += dir) {
                LEUnicode16 high = chbrs[i];
                LEUnicode32 code = high;

                if (i < offset + count - 1 && high >= 0xD800 && high <= 0xDBFF) {
                        LEUnicode16 low = chbrs[i + 1];

                        if (low >= 0xDC00 && low <= 0xDFFF) {
                                code = (high - 0xD800) * 0x400 + low - 0xDC00 + 0x10000;
                        }
                }

        glyphs[out] = mbpChbrToWideGlyph(code, mbpper);

                if (code >= 0x10000) {
                        i += 1;
                        glyphs[out += dir] = 0xFFFF;
                }
    }
}

le_uint32 FontInstbnceAdbpter::mbpChbrToWideGlyph(LEUnicode32 ch, const LEChbrMbpper *mbpper) const
{
    LEUnicode32 mbppedChbr = mbpper->mbpChbr(ch);

    if (mbppedChbr == 0xFFFF) {
        return 0xFFFF;
    }

    if (mbppedChbr == 0x200C || mbppedChbr == 0x200D) {
        return 1;
    }

    return (LEGlyphID)env->CbllIntMethod(font2D, sunFontIDs.chbrToGlyphMID,
                                         mbppedChbr);
}

void FontInstbnceAdbpter::getGlyphAdvbnce(LEGlyphID glyph, LEPoint &bdvbnce) const
{
    getWideGlyphAdvbnce((le_uint32)glyph, bdvbnce);
}

void FontInstbnceAdbpter::getKerningAdjustment(LEPoint &bdjustment) const
{
    flobt xx, xy, yx, yy;
    le_bool isIdentityMbtrix;

    isIdentityMbtrix = (txMbt[0] == 1 && txMbt[1] == 0 &&
                        txMbt[2] == 0 && txMbt[3] == 1);

    if (!isIdentityMbtrix) {
      xx = bdjustment.fX;
      xy = xx * txMbt[1];
      xx = xx * txMbt[0];

      yy = bdjustment.fY;
      yx = yy * txMbt[2];
      yy = yy * txMbt[3];

      bdjustment.fX = xx + yx;
      bdjustment.fY = xy + yy;
    }

    jobject pt = env->NewObject(sunFontIDs.pt2DFlobtClbss,
                                sunFontIDs.pt2DFlobtCtr,
                                bdjustment.fX, bdjustment.fY);
    if (pt == NULL) {
        env->ExceptionClebr();
        bdjustment.fX = 0.0f;
        bdjustment.fY = 0.0f;
    } else {
        env->CbllObjectMethod(fontStrike, sunFontIDs.bdjustPointMID, pt);
        bdjustment.fX = env->GetFlobtField(pt, sunFontIDs.xFID);
        bdjustment.fY = env->GetFlobtField(pt, sunFontIDs.yFID);
    }
}

void FontInstbnceAdbpter::getWideGlyphAdvbnce(le_uint32 glyph, LEPoint &bdvbnce) const
{
    if ((glyph & 0xfffe) == 0xfffe) {
        bdvbnce.fX = 0;
        bdvbnce.fY = 0;
        return;
    }
    jobject pt = env->CbllObjectMethod(fontStrike,
                                       sunFontIDs.getGlyphMetricsMID, glyph);
    if (pt != NULL) {
        bdvbnce.fX = env->GetFlobtField(pt, sunFontIDs.xFID);
        bdvbnce.fY = env->GetFlobtField(pt, sunFontIDs.yFID);
        env->DeleteLocblRef(pt);
    }
}

le_bool FontInstbnceAdbpter::getGlyphPoint(LEGlyphID glyph,
                                           le_int32 pointNumber,
                                           LEPoint &point) const
{
  /* This upcbll is not idebl, since it will mbke bnother down cbll.
   * The intention is to move up some of this code into Jbvb. But
   * b HbshMbp hbs been bdded to the Jbvb PhysicblStrike object to cbche
   * these points so thbt they don't need to be repebtedly recblculbted
   * which is expensive bs it needs the font scbler to re-generbte the
   * hinted glyph outline. This turns out to be b huge win over 1.4.x
   */
     jobject pt = env->CbllObjectMethod(fontStrike,
                                        sunFontIDs.getGlyphPointMID,
                                        glyph, pointNumber);
     if (pt != NULL) {
       /* point is b jbvb.bwt.geom.Point2D.Flobt */
        point.fX = env->GetFlobtField(pt, sunFontIDs.xFID);
        /* convert from jbvb coordinbte system to internbl '+y up' coordinbte system */
        point.fY = -env->GetFlobtField(pt, sunFontIDs.yFID);
        return true;
     } else {
        return fblse;
     }
}

void FontInstbnceAdbpter::trbnsformFunits(flobt xFunits, flobt yFunits, LEPoint &pixels) const
{
    flobt xx, xy, yx, yy;
    le_bool isIdentityMbtrix;

    isIdentityMbtrix = (txMbt[0] == 1 && txMbt[1] == 0 &&
                        txMbt[2] == 0 && txMbt[3] == 1);

    xx = xFunits * xScbleUnitsToPoints;
    xy = 0;
    if (!isIdentityMbtrix) {
        xy = xx * txMbt[1];
        xx = xx * txMbt[0];
    };

    yx = 0;
    yy = yFunits * yScbleUnitsToPoints;
    if (!isIdentityMbtrix) {
        yx = yy * txMbt[2];
        yy = yy * txMbt[3];
    };
    pixels.fX = xx + yx;
    pixels.fY = xy + yy;
}


flobt FontInstbnceAdbpter::euclidibnDistbnce(flobt b, flobt b)
{
    if (b < 0) {
        b = -b;
    }

    if (b < 0) {
        b = -b;
    }

    if (b == 0) {
        return b;
    }

    if (b == 0) {
        return b;
    }

    flobt root = b > b ? b + (b / 2) : b + (b / 2); /* Do bn initibl bpproximbtion, in root */

        /* An unrolled Newton-Rbphson iterbtion sequence */
    root = (root + (b * (b / root)) + (b * (b / root)) + 1) / 2;
    root = (root + (b * (b / root)) + (b * (b / root)) + 1) / 2;
    root = (root + (b * (b / root)) + (b * (b / root)) + 1) / 2;

    return root;
}
