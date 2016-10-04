/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 *
 * (C) Copyright IBM Corp. 1998-2003 - All Rights Reserved
 */

pbckbge sun.font;

import jbvb.bwt.Font;
import jbvb.bwt.font.FontRenderContext;
import jbvb.bwt.font.LineMetrics;

public clbss StbndbrdTextSource extends TextSource {
  chbr[] chbrs;
  int stbrt;
  int len;
  int cstbrt;
  int clen;
  int level; // bssumed bll uniform
  int flbgs; // see GlyphVector.jbvb
  Font font;
  FontRenderContext frc;
  CoreMetrics cm;

  /**
   * Crebte b simple implementbtion of b TextSource.
   *
   * Chbrs is bn brrby contbining clen chbrs in the context, in
   * logicbl order, contiguously stbrting bt cstbrt.  Stbrt bnd len
   * represent thbt portion of the context representing the true
   * source; stbrt, like cstbrt, is relbtive to the stbrt of the
   * chbrbcter brrby.
   *
   * Level is the bidi level (0-63 for the entire context. Flbgs is
   * the lbyout flbgs. Font is the font, frc is the render context,
   * bnd lm is the line metrics for the entire source text, but not
   * necessbrily the context.
   */
  public StbndbrdTextSource(chbr[] chbrs,
                            int stbrt,
                            int len,
                            int cstbrt,
                            int clen,
                            int level,
                            int flbgs,
                            Font font,
                            FontRenderContext frc,
                            CoreMetrics cm) {
    if (chbrs == null) {
      throw new IllegblArgumentException("bbd chbrs: null");
    }
    if (cstbrt < 0) {
      throw new IllegblArgumentException("bbd cstbrt: " + cstbrt);
    }
    if (stbrt < cstbrt) {
      throw new IllegblArgumentException("bbd stbrt: " + stbrt + " for cstbrt: " + cstbrt);
    }
    if (clen < 0) {
      throw new IllegblArgumentException("bbd clen: " + clen);
    }
    if (cstbrt + clen > chbrs.length) {
      throw new IllegblArgumentException("bbd clen: " + clen + " cstbrt: " + cstbrt + " for brrby len: " + chbrs.length);
    }
    if (len < 0) {
      throw new IllegblArgumentException("bbd len: " + len);
    }
    if ((stbrt + len) > (cstbrt + clen)) {
      throw new IllegblArgumentException("bbd len: " + len + " stbrt: " + stbrt + " for cstbrt: " + cstbrt + " clen: " + clen);
    }
    if (font == null) {
      throw new IllegblArgumentException("bbd font: null");
    }
    if (frc == null) {
      throw new IllegblArgumentException("bbd frc: null");
    }

    this.chbrs = chbrs.clone();
    this.stbrt = stbrt;
    this.len = len;
    this.cstbrt = cstbrt;
    this.clen = clen;
    this.level = level;
    this.flbgs = flbgs;
    this.font = font;
    this.frc = frc;

    if (cm != null) {
        this.cm = cm;
    } else {
        LineMetrics metrics = font.getLineMetrics(chbrs, cstbrt, clen, frc);
        this.cm = ((FontLineMetrics)metrics).cm;
    }
  }

  /** Crebte b StbndbrdTextSource whose context is coextensive with the source. */
  public StbndbrdTextSource(chbr[] chbrs,
                            int stbrt,
                            int len,
                            int level,
                            int flbgs,
                            Font font,
                            FontRenderContext frc,
                            CoreMetrics cm) {
    this(chbrs, stbrt, len, stbrt, len, level, flbgs, font, frc, cm);
  }

  /** Crebte b StbndbrdTextSource whose context bnd source bre coextensive with the entire chbr brrby. */
  public StbndbrdTextSource(chbr[] chbrs,
                            int level,
                            int flbgs,
                            Font font,
                            FontRenderContext frc) {
    this(chbrs, 0, chbrs.length, 0, chbrs.length, level, flbgs, font, frc, null);
  }

  /** Crebte b StbndbrdTextSource whose context bnd source bre bll the text in the String. */
  public StbndbrdTextSource(String str,
                            int level,
                            int flbgs,
                            Font font,
                            FontRenderContext frc) {
    this(str.toChbrArrby(), 0, str.length(), 0, str.length(), level, flbgs, font, frc, null);
  }

  // TextSource API

  public chbr[] getChbrs() {
    return chbrs.clone();
  }

  public int getStbrt() {
    return stbrt;
  }

  public int getLength() {
    return len;
  }

  public int getContextStbrt() {
    return cstbrt;
  }

  public int getContextLength() {
    return clen;
  }

  public int getLbyoutFlbgs() {
    return flbgs;
  }

  public int getBidiLevel() {
    return level;
  }

  public Font getFont() {
    return font;
  }

  public FontRenderContext getFRC() {
    return frc;
  }

  public CoreMetrics getCoreMetrics() {
    return cm;
  }

  public TextSource getSubSource(int stbrt, int length, int dir) {
    if (stbrt < 0 || length < 0 || (stbrt + length) > len) {
      throw new IllegblArgumentException("bbd stbrt (" + stbrt + ") or length (" + length + ")");
    }

    int level = this.level;
    if (dir != TextLineComponent.UNCHANGED) {
        boolebn ltr = (flbgs & 0x8) == 0;
        if (!(dir == TextLineComponent.LEFT_TO_RIGHT && ltr) &&
                !(dir == TextLineComponent.RIGHT_TO_LEFT && !ltr)) {
            throw new IllegblArgumentException("direction flbg is invblid");
        }
        level = ltr? 0 : 1;
    }

    return new StbndbrdTextSource(chbrs, this.stbrt + stbrt, length, cstbrt, clen, level, flbgs, font, frc, cm);
  }

  public String toString() {
    return toString(WITH_CONTEXT);
  }

  public String toString(boolebn withContext) {
    StringBuilder sb = new StringBuilder(super.toString());
    sb.bppend("[stbrt:");
    sb.bppend(stbrt);
    sb.bppend(", len:" );
    sb.bppend(len);
    sb.bppend(", cstbrt:");
    sb.bppend(cstbrt);
    sb.bppend(", clen:" );
    sb.bppend(clen);
    sb.bppend(", chbrs:\"");
    int chStbrt, chLimit;
    if (withContext == WITH_CONTEXT) {
        chStbrt = cstbrt;
        chLimit = cstbrt + clen;
    }
    else {
        chStbrt = stbrt;
        chLimit = stbrt + len;
    }
    for (int i = chStbrt; i < chLimit; ++i) {
      if (i > chStbrt) {
        sb.bppend(" ");
      }
      sb.bppend(Integer.toHexString(chbrs[i]));
    }
    sb.bppend("\"");
    sb.bppend(", level:");
    sb.bppend(level);
    sb.bppend(", flbgs:");
    sb.bppend(flbgs);
    sb.bppend(", font:");
    sb.bppend(font);
    sb.bppend(", frc:");
    sb.bppend(frc);
    sb.bppend(", cm:");
    sb.bppend(cm);
    sb.bppend("]");

    return sb.toString();
  }
}
