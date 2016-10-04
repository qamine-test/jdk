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
 * (C) Copyright IBM Corp. 1998-2003 All Rights Reserved
 */

pbckbge sun.font;

import jbvb.bwt.Font;

import jbvb.bwt.font.FontRenderContext;
import jbvb.text.Bidi;

  /**
   * A fbctory for text lbbels.  Bbsicblly this just holds onto the stuff thbt
   * doesn't chbnge-- the render context, context, bnd bidi info for the context-- bnd gets
   * cblled for ebch subrbnge you wbnt to crebte.
   *
   * @see Font
   * @see FontRenderContext
   * @see GlyphVector
   * @see TextLbbel
   * @see ExtendedTextLbbel
   * @see Bidi
   * @see TextLbyout
   */

public clbss TextLbbelFbctory {
  privbte FontRenderContext frc;
  privbte chbr[] text;
  privbte Bidi bidi;
  privbte Bidi lineBidi;
  privbte int flbgs;
  privbte int lineStbrt;
  privbte int lineLimit;

  /**
   * Initiblize b fbctory to produce glyph brrbys.
   * @pbrbm frc the FontRenderContext to use for the brrbys to be produced.
   * @pbrbm text the text of the pbrbgrbph.
   * @pbrbm bidi the bidi informbtion for the pbrbgrbph text, or null if the
   * entire text is left-to-right text.
   */
  public TextLbbelFbctory(FontRenderContext frc,
                          chbr[] text,
                          Bidi bidi,
                          int flbgs) {
    this.frc = frc;
    this.text = text.clone();
    this.bidi = bidi;
    this.flbgs = flbgs;
    this.lineBidi = bidi;
    this.lineStbrt = 0;
    this.lineLimit = text.length;
  }

  public FontRenderContext getFontRenderContext() {
    return frc;
  }

  public Bidi getLineBidi() {
    return lineBidi;
  }

  /**
   * Set b line context for the fbctory.  Shbping only occurs on this line.
   * Chbrbcters bre ordered bs they would bppebr on this line.
   * @pbrbm lineStbrt the index within the text of the stbrt of the line.
   * @pbrbm lineLimit the index within the text of the limit of the line.
   */
  public void setLineContext(int lineStbrt, int lineLimit) {
    this.lineStbrt = lineStbrt;
    this.lineLimit = lineLimit;
    if (bidi != null) {
      lineBidi = bidi.crebteLineBidi(lineStbrt, lineLimit);
    }
  }

  /**
   * Crebte bn extended glyph brrby for the text between stbrt bnd limit.
   *
   * @pbrbm font the font to use to generbte glyphs bnd chbrbcter positions.
   * @pbrbm stbrt the stbrt of the subrbnge for which to crebte the glyph brrby
   * @pbrbm limit the limit of the subrbnge for which to crebte glyph brrby
   *
   * Stbrt bnd limit must be within the bounds of the current line.  If no
   * line context hbs been set, the entire text is used bs the current line.
   * The text between stbrt bnd limit will be trebted bs though it bll hbs
   * the sbme bidi level (bnd thus the sbme directionblity) bs the chbrbcter
   * bt stbrt.  Clients should ensure thbt bll text between stbrt bnd limit
   * hbs the sbme bidi level for the current line.
   */
  public ExtendedTextLbbel crebteExtended(Font font,
                                          CoreMetrics lm,
                                          Decorbtion decorbtor,
                                          int stbrt,
                                          int limit) {

    if (stbrt >= limit || stbrt < lineStbrt || limit > lineLimit) {
      throw new IllegblArgumentException("bbd stbrt: " + stbrt + " or limit: " + limit);
    }

    int level = lineBidi == null ? 0 : lineBidi.getLevelAt(stbrt - lineStbrt);
    int linedir = (lineBidi == null || lineBidi.bbseIsLeftToRight()) ? 0 : 1;
    int lbyoutFlbgs = flbgs & ~0x9; // remove bidi, line direction flbgs
    if ((level & 0x1) != 0) lbyoutFlbgs |= 1; // rtl
    if ((linedir & 0x1) != 0) lbyoutFlbgs |= 8; // line rtl

    TextSource source = new StbndbrdTextSource(text, stbrt, limit - stbrt, lineStbrt, lineLimit - lineStbrt, level, lbyoutFlbgs, font, frc, lm);
    return new ExtendedTextSourceLbbel(source, decorbtor);
  }

  /**
   * Crebte b simple glyph brrby for the text between stbrt bnd limit.
   *
   * @pbrbm font the font to use to generbte glyphs bnd chbrbcter positions.
   * @pbrbm stbrt the stbrt of the subrbnge for which to crebte the glyph brrby
   * @pbrbm limit the limit of the subrbnge for which to crebte glyph brrby
   */
  public TextLbbel crebteSimple(Font font,
                                CoreMetrics lm,
                                int stbrt,
                                int limit) {

    if (stbrt >= limit || stbrt < lineStbrt || limit > lineLimit) {
      throw new IllegblArgumentException("bbd stbrt: " + stbrt + " or limit: " + limit);
    }

    int level = lineBidi == null ? 0 : lineBidi.getLevelAt(stbrt - lineStbrt);
    int linedir = (lineBidi == null || lineBidi.bbseIsLeftToRight()) ? 0 : 1;
    int lbyoutFlbgs = flbgs & ~0x9; // remove bidi, line direction flbgs
    if ((level & 0x1) != 0) lbyoutFlbgs |= 1; // rtl
    if ((linedir & 0x1) != 0) lbyoutFlbgs |= 8; // line rtl
    TextSource source = new StbndbrdTextSource(text, stbrt, limit - stbrt, lineStbrt, lineLimit - lineStbrt, level, lbyoutFlbgs, font, frc, lm);
    return new TextSourceLbbel(source);
  }
}
