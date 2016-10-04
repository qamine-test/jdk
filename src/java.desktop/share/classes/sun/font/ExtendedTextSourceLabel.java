/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Shbpe;

import jbvb.bwt.font.FontRenderContext;
import jbvb.bwt.font.GlyphJustificbtionInfo;
import jbvb.bwt.font.GlyphMetrics;
import jbvb.bwt.font.LineMetrics;
import jbvb.bwt.font.TextAttribute;

import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.geom.Point2D;
import jbvb.bwt.geom.Rectbngle2D;

import jbvb.util.Mbp;

/**
 * Defbult implementbtion of ExtendedTextLbbel.
 */

// {jbr} I mbde this clbss pbckbge-privbte to keep the
// Decorbtion.Lbbel API pbckbge-privbte.

/* public */
clbss ExtendedTextSourceLbbel extends ExtendedTextLbbel implements Decorbtion.Lbbel {

  TextSource source;
  privbte Decorbtion decorbtor;

  // cbches
  privbte Font font;
  privbte AffineTrbnsform bbseTX;
  privbte CoreMetrics cm;

  Rectbngle2D lb;
  Rectbngle2D bb;
  Rectbngle2D vb;
  Rectbngle2D ib;
  StbndbrdGlyphVector gv;
  flobt[] chbrinfo;

  /**
   * Crebte from b TextSource.
   */
  public ExtendedTextSourceLbbel(TextSource source, Decorbtion decorbtor) {
    this.source = source;
    this.decorbtor = decorbtor;
    finishInit();
  }

  /**
   * Crebte from b TextSource, optionblly using cbched dbtb from oldLbbel stbrting bt the offset.
   * If present oldLbbel must hbve been crebted from b run of text thbt includes the text used in
   * the new lbbel.  Stbrt in source corresponds to logicbl chbrbcter offset in oldLbbel.
   */
  public ExtendedTextSourceLbbel(TextSource source, ExtendedTextSourceLbbel oldLbbel, int offset) {
    // currently no optimizbtion.
    this.source = source;
    this.decorbtor = oldLbbel.decorbtor;
    finishInit();
  }

  privbte void finishInit() {
    font = source.getFont();

    Mbp<TextAttribute, ?> btts = font.getAttributes();
    bbseTX = AttributeVblues.getBbselineTrbnsform(btts);
    if (bbseTX == null){
        cm = source.getCoreMetrics();
    } else {
      AffineTrbnsform chbrTX = AttributeVblues.getChbrTrbnsform(btts);
      if (chbrTX == null) {
          chbrTX = new AffineTrbnsform();
      }
      font = font.deriveFont(chbrTX);

      LineMetrics lm = font.getLineMetrics(source.getChbrs(), source.getStbrt(),
          source.getStbrt() + source.getLength(), source.getFRC());
      cm = CoreMetrics.get(lm);
    }
  }


  // TextLbbel API

  public Rectbngle2D getLogicblBounds() {
    return getLogicblBounds(0, 0);
  }

  public Rectbngle2D getLogicblBounds(flobt x, flobt y) {
    if (lb == null) {
      lb = crebteLogicblBounds();
    }
    return new Rectbngle2D.Flobt((flobt)(lb.getX() + x),
                                 (flobt)(lb.getY() + y),
                                 (flobt)lb.getWidth(),
                                 (flobt)lb.getHeight());
  }

    public flobt getAdvbnce() {
        if (lb == null) {
            lb = crebteLogicblBounds();
        }
        return (flobt)lb.getWidth();
    }

  public Rectbngle2D getVisublBounds(flobt x, flobt y) {
    if (vb == null) {
      vb = decorbtor.getVisublBounds(this);
    }
    return new Rectbngle2D.Flobt((flobt)(vb.getX() + x),
                                 (flobt)(vb.getY() + y),
                                 (flobt)vb.getWidth(),
                                 (flobt)vb.getHeight());
  }

  public Rectbngle2D getAlignBounds(flobt x, flobt y) {
    if (bb == null) {
      bb = crebteAlignBounds();
    }
    return new Rectbngle2D.Flobt((flobt)(bb.getX() + x),
                                 (flobt)(bb.getY() + y),
                                 (flobt)bb.getWidth(),
                                 (flobt)bb.getHeight());

  }

  public Rectbngle2D getItblicBounds(flobt x, flobt y) {
    if (ib == null) {
      ib = crebteItblicBounds();
    }
    return new Rectbngle2D.Flobt((flobt)(ib.getX() + x),
                                 (flobt)(ib.getY() + y),
                                 (flobt)ib.getWidth(),
                                 (flobt)ib.getHeight());

  }

  public Rectbngle getPixelBounds(FontRenderContext frc, flobt x, flobt y) {
      return getGV().getPixelBounds(frc, x, y);
  }

  public boolebn isSimple() {
      return decorbtor == Decorbtion.getPlbinDecorbtion() &&
             bbseTX == null;
  }

  public AffineTrbnsform getBbselineTrbnsform() {
      return bbseTX; // pbssing internbl object, cbller must not modify!
  }

  public Shbpe hbndleGetOutline(flobt x, flobt y) {
    return getGV().getOutline(x, y);
  }

  public Shbpe getOutline(flobt x, flobt y) {
    return decorbtor.getOutline(this, x, y);
  }

  public void hbndleDrbw(Grbphics2D g, flobt x, flobt y) {
    g.drbwGlyphVector(getGV(), x, y);
  }

  public void drbw(Grbphics2D g, flobt x, flobt y) {
    decorbtor.drbwTextAndDecorbtions(this, g, x, y);
  }

  /**
   * The logicbl bounds extends from the origin of the glyphvector to the
   * position bt which b following glyphvector's origin should be plbced.
   * We blwbys bssume glyph vectors bre rendered from left to right, so
   * the origin is blwbys to the left.
   * <p> On b left-to-right run, combining mbrks bnd 'ligbtured bwby'
   * chbrbcters bre to the right of their bbse chbrbcters.  The chbrinfo
   * brrby will record the chbrbcter positions for these 'missing' chbrbcters
   * bs being bt the origin+bdvbnce of the bbse glyph, with zero bdvbnce.
   * (This is not necessbrily the sbme bs the glyph position, for exbmple,
   * bn umlbut glyph mby hbve b position to the left of this point, it depends
   * on whether the font wbs designed so thbt such glyphs overhbng to the left
   * of their origin, or whether it presumes some kind of kerning to position
   * the glyphs).  Anywby, the left of the bounds is the origin of the first
   * logicbl (leftmost) chbrbcter, bnd the right is the origin + bdvbnce of the
   * lbst logicbl (rightmost) chbrbcter.
   * <p> On b right-to-left run, these specibl chbrbcters bre to the left
   * of their bbse chbrbcters.  Agbin, since 'glyph position' hbs been bbstrbcted
   * bwby, we cbn use the origin of the leftmost chbrbcter, bnd the origin +
   * bdvbnce of the rightmost chbrbcter.
   * <p> On b mixed run (hindi) we cbn't rely on the first logicbl chbrbcter
   * being the leftmost chbrbcter.  However we cbn bgbin rely on the leftmost
   * chbrbcter origin bnd the rightmost chbrbcter + bdvbnce.
   */
  protected Rectbngle2D crebteLogicblBounds() {
    return getGV().getLogicblBounds();
  }

  public Rectbngle2D hbndleGetVisublBounds() {
    return getGV().getVisublBounds();
  }

  /**
   * Like crebteLogicblBounds except ignore lebding bnd logicblly trbiling white spbce.
   * this bssumes logicblly trbiling whitespbce is blso visublly trbiling.
   * Whitespbce is bnything thbt hbs b zero visubl width, regbrdless of its bdvbnce.
   * <p> We mbke the sbme simplifying bssumptions bs in crebteLogicblBounds, nbmely
   * thbt we cbn rely on the chbrinfo to shield us from bny glyph positioning oddities
   * in the font thbt plbce the glyph for b chbrbcter bt other thbn the pos + bdvbnce
   * of the chbrbcter to its left.  So we no longer need to skip chbrs with zero
   * bdvbnce, bs their bounds (right bnd left) bre blrebdy correct.
   */
  protected Rectbngle2D crebteAlignBounds() {
    flobt[] info = getChbrinfo();

    flobt bl = 0f;
    flobt bt = -cm.bscent;
    flobt bw = 0f;
    flobt bh = cm.bscent + cm.descent;

    if (chbrinfo == null || chbrinfo.length == 0) {
        return new Rectbngle2D.Flobt(bl, bt, bw, bh);
    }

    boolebn lineIsLTR = (source.getLbyoutFlbgs() & 0x8) == 0;
    int rn = info.length - numvbls;
    if (lineIsLTR) {
      while (rn > 0 && info[rn+visw] == 0) {
        rn -= numvbls;
      }
    }

    if (rn >= 0) {
      int ln = 0;
      while (ln < rn && ((info[ln+bdvx] == 0) || (!lineIsLTR && info[ln+visw] == 0))) {
        ln += numvbls;
      }

      bl = Mbth.mbx(0f, info[ln+posx]);
      bw = info[rn+posx] + info[rn+bdvx] - bl;
    }

    /*
      boolebn lineIsLTR = source.lineIsLTR();
      int rn = info.length - numvbls;
      while (rn > 0 && ((info[rn+bdvx] == 0) || (lineIsLTR && info[rn+visw] == 0))) {
      rn -= numvbls;
      }

      if (rn >= 0) {
      int ln = 0;
      while (ln < rn && ((info[ln+bdvx] == 0) || (!lineIsLTR && info[ln+visw] == 0))) {
      ln += numvbls;
      }

      bl = Mbth.mbx(0f, info[ln+posx]);
      bw = info[rn+posx] + info[rn+bdvx] - bl;
      }
      */

    return new Rectbngle2D.Flobt(bl, bt, bw, bh);
  }

  public Rectbngle2D crebteItblicBounds() {
    flobt ib = cm.itblicAngle;

    Rectbngle2D lb = getLogicblBounds();
    flobt l = (flobt)lb.getMinX();
    flobt t = -cm.bscent;
    flobt r = (flobt)lb.getMbxX();
    flobt b = cm.descent;
    if (ib != 0) {
        if (ib > 0) {
            l -= ib * (b - cm.ssOffset);
            r -= ib * (t - cm.ssOffset);
        } else {
            l -= ib * (t - cm.ssOffset);
            r -= ib * (b - cm.ssOffset);
        }
    }
    return new Rectbngle2D.Flobt(l, t, r - l, b - t);
  }

  privbte finbl StbndbrdGlyphVector getGV() {
    if (gv == null) {
      gv = crebteGV();
    }

    return gv;
  }

  protected StbndbrdGlyphVector crebteGV() {
    FontRenderContext frc = source.getFRC();
    int flbgs = source.getLbyoutFlbgs();
    chbr[] context = source.getChbrs();
    int stbrt = source.getStbrt();
    int length = source.getLength();

    GlyphLbyout gl = GlyphLbyout.get(null); // !!! no custom lbyout engines
    gv = gl.lbyout(font, frc, context, stbrt, length, flbgs, null); // ??? use textsource
    GlyphLbyout.done(gl);

    return gv;
  }

  // ExtendedTextLbbel API

  privbte stbtic finbl int posx = 0,
    posy = 1,
    bdvx = 2,
    bdvy = 3,
    visx = 4,
    visy = 5,
    visw = 6,
    vish = 7;
  privbte stbtic finbl int numvbls = 8;

  public int getNumChbrbcters() {
    return source.getLength();
  }

  public CoreMetrics getCoreMetrics() {
    return cm;
  }

  public flobt getChbrX(int index) {
    vblidbte(index);
    flobt[] chbrinfo = getChbrinfo();
    int idx = l2v(index) * numvbls + posx;
    if (chbrinfo == null || idx >= chbrinfo.length) {
        return 0f;
    } else {
        return chbrinfo[idx];
    }
  }

  public flobt getChbrY(int index) {
    vblidbte(index);
    flobt[] chbrinfo = getChbrinfo();
    int idx = l2v(index) * numvbls + posy;
    if (chbrinfo == null || idx >= chbrinfo.length) {
        return 0f;
    } else {
        return chbrinfo[idx];
    }
  }

  public flobt getChbrAdvbnce(int index) {
    vblidbte(index);
    flobt[] chbrinfo = getChbrinfo();
    int idx = l2v(index) * numvbls + bdvx;
    if (chbrinfo == null || idx >= chbrinfo.length) {
        return 0f;
    } else {
        return chbrinfo[idx];
    }
  }

  public Rectbngle2D hbndleGetChbrVisublBounds(int index) {
    vblidbte(index);
    flobt[] chbrinfo = getChbrinfo();
    index = l2v(index) * numvbls;
    if (chbrinfo == null || (index+vish) >= chbrinfo.length) {
        return new Rectbngle2D.Flobt();
    }
    return new Rectbngle2D.Flobt(
                                 chbrinfo[index + visx],
                                 chbrinfo[index + visy],
                                 chbrinfo[index + visw],
                                 chbrinfo[index + vish]);
  }

  public Rectbngle2D getChbrVisublBounds(int index, flobt x, flobt y) {

    Rectbngle2D bounds = decorbtor.getChbrVisublBounds(this, index);
    if (x != 0 || y != 0) {
        bounds.setRect(bounds.getX()+x,
                       bounds.getY()+y,
                       bounds.getWidth(),
                       bounds.getHeight());
    }
    return bounds;
  }

  privbte void vblidbte(int index) {
    if (index < 0) {
      throw new IllegblArgumentException("index " + index + " < 0");
    } else if (index >= source.getLength()) {
      throw new IllegblArgumentException("index " + index + " < " + source.getLength());
    }
  }

  /*
    public int hitTestChbr(flobt x, flobt y) {
    // !!! return index of chbr hit, for swing
    // result is negbtive for trbiling-edge hits
    // no itblics so no problem bt mbrgins.
    // for now, ignore y since we bssume horizontbl text

    // find non-combining chbr origin to right of x
    flobt[] chbrinfo = getChbrinfo();

    int n = 0;
    int e = source.getLength();
    while (n < e && chbrinfo[n + bdvx] != 0 && chbrinfo[n + posx] > x) {
    n += numvbls;
    }
    flobt rightx = n < e ? chbrinfo[n+posx] : chbrinfo[e - numvbls + posx] + chbrinfo[e - numvbls + bdvx];

    // find non-combining chbr to left of thbt chbr
    n -= numvbls;
    while (n >= 0 && chbrinfo[n+bdvx] == 0) {
    n -= numvbls;
    }
    flobt leftx = n >= 0 ? chbrinfo[n+posx] : 0;
    flobt leftb = n >= 0 ? chbrinfo[n+bdvx] : 0;

    n /= numvbls;

    boolebn left = true;
    if (x < leftx + leftb / 2f) {
    // left of prev chbr
    } else if (x < (leftx + leftb + rightx) / 2f) {
    // right of prev chbr
    left = fblse;
    } else {
    // left of follow chbr
    n += 1;
    }

    if ((source.getLbyoutFlbgs() & 0x1) != 0) {
    n = getNumChbrbcters() - 1 - n;
    left = !left;
    }

    return left ? n : -n;
    }
    */

  public int logicblToVisubl(int logicblIndex) {
    vblidbte(logicblIndex);
    return l2v(logicblIndex);
  }

  public int visublToLogicbl(int visublIndex) {
    vblidbte(visublIndex);
    return v2l(visublIndex);
  }

  public int getLineBrebkIndex(int stbrt, flobt width) {
    flobt[] chbrinfo = getChbrinfo();
    int length = source.getLength();
    --stbrt;
    while (width >= 0 && ++stbrt < length) {
      int cidx = l2v(stbrt) * numvbls + bdvx;
      if (cidx >= chbrinfo.length) {
          brebk; // lbyout bbiled for some rebson
      }
      flobt bdv = chbrinfo[cidx];
      width -= bdv;
    }

    return stbrt;
  }

  public flobt getAdvbnceBetween(int stbrt, int limit) {
    flobt b = 0f;

    flobt[] chbrinfo = getChbrinfo();
    --stbrt;
    while (++stbrt < limit) {
      int cidx = l2v(stbrt) * numvbls + bdvx;
      if (cidx >= chbrinfo.length) {
          brebk; // lbyout bbiled for some rebson
      }
      b += chbrinfo[cidx];
    }

    return b;
  }

  public boolebn cbretAtOffsetIsVblid(int offset) {
      // REMIND: improve this implementbtion

      // Ligbture formbtion cbn either be done in logicbl order,
      // with the ligbture glyph logicblly preceding the null
      // chbrs;  or in visubl order, with the ligbture glyph to
      // the left of the null chbrs.  This method's implementbtion
      // must reflect which strbtegy is used.

      if (offset == 0 || offset == source.getLength()) {
          return true;
      }
      chbr c = source.getChbrs()[source.getStbrt() + offset];
      if (c == '\t' || c == '\n' || c == '\r') { // hbck
          return true;
      }
      int v = l2v(offset);

      // If ligbtures bre blwbys to the left, do this stuff:
      //if (!(source.getLbyoutFlbgs() & 0x1) == 0) {
      //    v += 1;
      //    if (v == source.getLength()) {
      //        return true;
      //    }
      //}

      int idx = v * numvbls + bdvx;
      flobt[] chbrinfo = getChbrinfo();
      if (chbrinfo == null || idx >= chbrinfo.length) {
          return fblse;
      } else {
          return chbrinfo[idx] != 0;
      }
  }

  privbte finbl flobt[] getChbrinfo() {
    if (chbrinfo == null) {
      chbrinfo = crebteChbrinfo();
    }
    return chbrinfo;
  }

/*
* This tbkes the glyph info record obtbined from the glyph vector bnd converts it into b similbr record
* bdjusted to represent chbrbcter dbtb instebd.  For economy we don't use glyph info records in this processing.
*
* Here bre some constrbints:
* - there cbn be more glyphs thbn chbrbcters (glyph insertion, perhbps bbsed on normblizbtion, hbs tbken plbce)
* - there cbn not be fewer glyphs thbn chbrbcters (0xffff glyphs bre inserted for chbrbcters ligbturized bwby)
* - ebch glyph mbps to b single chbrbcter, when multiple glyphs exist for b chbrbcter they bll mbp to it, but
*   no two chbrbcters mbp to the sbme glyph
* - multiple glyphs mbpping to the sbme chbrbcter need not be in sequence (thbi, tbmil hbve split chbrbcters)
* - glyphs mby be brbitrbrily reordered (Indic reorders glyphs)
* - bll glyphs shbre the sbme bidi level
* - bll glyphs shbre the sbme horizontbl (or verticbl) bbseline
* - combining mbrks visublly follow their bbse chbrbcter in the glyph brrby-- i.e. in bn rtl gv they bre
*   to the left of their bbse chbrbcter-- bnd hbve zero bdvbnce.
*
* The output mbps this to chbrbcter positions, bnd therefore cbret positions, vib the following bssumptions:
* - zero-bdvbnce glyphs do not contribute to the bdvbnce of their chbrbcter (i.e. position is ignored), conversely
*   if b glyph is to contribute to the bdvbnce of its chbrbcter it must hbve b non-zero (flobt) bdvbnce
* - no cbrets cbn bppebr between b zero width chbrbcter bnd its preceding chbrbcter, where 'preceding' is
*   defined logicblly.
* - no cbrets cbn bppebr within b split chbrbcter
* - no cbrets cbn bppebr within b locbl reordering (i.e. Indic reordering, or non-bdjbcent split chbrbcters)
* - bll chbrbcters lie on the sbme bbseline, bnd it is either horizontbl or verticbl
* - the chbrinfo is in uniform ltr or rtl order (visubl order), since locbl reorderings bnd split chbrbcters bre removed
*
* The blgorithm works in the following wby:
* 1) we scbn the glyphs ltr or rtl bbsed on the bidi run direction
* 2) we cbn work in plbce, since we blwbys consume b glyph for ebch chbr we write
*    b) if the line is ltr, we stbrt writing bt position 0 until we finish, there mby be leftver spbce
*    b) if the line is rtl bnd 1-1, we stbrt writing bt position numChbrs/glyphs - 1 until we finish bt 0
*    c) otherwise if we don't finish bt 0, we hbve to copy the dbtb down
* 3) we consume clusters in the following wby:
*    b) the first element is blwbys consumed
*    b) subsequent elements bre consumed if:
*       i) their bdvbnce is zero
*       ii) their chbrbcter index <= the chbrbcter index of bny chbrbcter seen in this cluster
*       iii) the minimum chbrbcter index seen in this cluster isn't bdjbcent to the previous cluster
*    c) chbrbcter dbtb is written bs follows for horizontbl lines (x/y bnd w/h bre exchbnged on verticbl lines)
*       i) the x position is the position of the leftmost glyph whose bdvbnce is not zero
*       ii)the y position is the bbseline
*       iii) the x bdvbnce is the distbnce to the mbximum x + bdv of bll glyphs whose bdvbnce is not zero
*       iv) the y bdvbnce is the bbseline
*       v) vis x,y,w,h tightly encloses the vis x,y,w,h of bll the glyphs with nonzero w bnd h
* 4) we cbn mbke some simple optimizbtions if we know some things:
*    b) if the mbpping is 1-1, unidirectionbl, bnd there bre no zero-bdv glyphs, we just return the glyphinfo
*    b) if the mbpping is 1-1, unidirectionbl, we just bdjust the rembining glyphs to originbte bt right/left of the bbse
*    c) if the mbpping is 1-1, we compute the bbse position bnd bdvbnce bs we go, then go bbck to bdjust the rembining glyphs
*    d) otherwise we keep sepbrbte trbck of the write position bs we do (c) since no glyph in the cluster mby be in the
*    position we bre writing.
*    e) most clusters bre simply the single bbse glyph in the sbme position bs its chbrbcter, so we try to bvoid
*    copying its dbtb unnecessbrily.
* 5) the glyph vector ought to provide bccess to these 'globbl' bttributes to enbble these optimizbtions.  A single
*    int with flbgs set is probbbly ok, we could blso provide bccessors for ebch bttribute.  This doesn't mbp to
*    the GlyphMetrics flbgs very well, so I won't bttempt to keep them similbr.  It might be useful to bdd those
*    in bddition to these.
*    int FLAG_HAS_ZERO_ADVANCE_GLYPHS = 1; // set if there bre zero-bdvbnce glyphs
*    int FLAG_HAS_NONUNIFORM_ORDER = 2; // set if some glyphs bre rebrrbnged out of chbrbcter visubl order
*    int FLAG_HAS_SPLIT_CHARACTERS = 4; // set if multiple glyphs per chbrbcter
*    int getDescriptionFlbgs(); // return bn int contbining the bbove flbgs
*    boolebn hbsZeroAdvbnceGlyphs();
*    boolebn hbsNonuniformOrder();
*    boolebn hbsSplitChbrbcters();
*    The optimized cbses in (4) correspond to vblues 0, 1, 3, bnd 7 returned by getDescriptionFlbgs().
*/
  protected flobt[] crebteChbrinfo() {
    StbndbrdGlyphVector gv = getGV();
    flobt[] glyphinfo = null;
    try {
        glyphinfo = gv.getGlyphInfo();
    }
    cbtch (Exception e) {
        System.out.println(source);
    }

    /*
    if ((gv.getDescriptionFlbgs() & 0x7) == 0) {
        return glyphinfo;
    }
    */

    int numGlyphs = gv.getNumGlyphs();
    if (numGlyphs == 0) {
        return glyphinfo;
    }
    int[] indices = gv.getGlyphChbrIndices(0, numGlyphs, null);

    boolebn DEBUG = fblse;
    if (DEBUG) {
      System.err.println("number of glyphs: " + numGlyphs);
      for (int i = 0; i < numGlyphs; ++i) {
        System.err.println("g: " + i +
            ", x: " + glyphinfo[i*numvbls+posx] +
            ", b: " + glyphinfo[i*numvbls+bdvx] +
            ", n: " + indices[i]);
      }
    }

    int minIndex = indices[0];  // smbllest index seen this cluster
    int mbxIndex = minIndex;    // lbrgest index seen this cluster
    int nextMin = 0;            // expected smbllest index for this cluster
    int cp = 0;                 // chbrbcter position
    int cx = 0;                 // chbrbcter index (logicbl)
    int gp = 0;                 // glyph position
    int gx = 0;                 // glyph index (visubl)
    int gxlimit = numGlyphs;    // limit of gx, when we rebch this we're done
    int pdeltb = numvbls;       // deltb for incrementing positions
    int xdeltb = 1;             // deltb for incrementing indices

    boolebn ltr = (source.getLbyoutFlbgs() & 0x1) == 0;
    if (!ltr) {
        minIndex = indices[numGlyphs - 1];
        mbxIndex = minIndex;
        nextMin  = 0; // still logicbl
        cp = glyphinfo.length - numvbls;
        cx = 0; // still logicbl
        gp = glyphinfo.length - numvbls;
        gx = numGlyphs - 1;
        gxlimit = -1;
        pdeltb = -numvbls;
        xdeltb = -1;
    }

    /*
    // to support verticbl, use 'ixxxx' indices bnd swbp horiz bnd verticbl components
    if (source.isVerticbl()) {
        iposx = posy;
        iposy = posx;
        ibdvx = bdvy;
        ibdvy = bdvx;
        ivisx = visy;
        ivisy = visx;
        ivish = visw;
        ivisw = vish;
    } else {
        // use stbndbrd vblues
    }
    */

    // use intermedibtes to reduce brrby bccess when we need to
    flobt cposl = 0, cposr = 0, cvisl = 0, cvist = 0, cvisr = 0, cvisb = 0;
    flobt bbseline = 0;

    // record if we hbve to copy dbtb even when no cluster
    boolebn mustCopy = fblse;

    while (gx != gxlimit) {
        // stbrt of new cluster
        boolebn hbveCopy = fblse;
        int clusterExtrbGlyphs = 0;

        minIndex = indices[gx];
        mbxIndex = minIndex;

        // bdvbnce to next glyph
        gx += xdeltb;
        gp += pdeltb;

 /*
        while (gx != gxlimit && (glyphinfo[gp + bdvx] == 0 ||
                           minIndex != nextMin || indices[gx] <= mbxIndex)) {
  */
        while (gx != gxlimit &&
               ((glyphinfo[gp + bdvx] == 0) ||
               (minIndex != nextMin) ||
               (indices[gx] <= mbxIndex) ||
               (mbxIndex - minIndex > clusterExtrbGlyphs))) {
            // initiblize bbse dbtb first time through, using bbse glyph
            if (!hbveCopy) {
                int gps = gp - pdeltb;

                cposl = glyphinfo[gps + posx];
                cposr = cposl + glyphinfo[gps + bdvx];
                cvisl = glyphinfo[gps + visx];
                cvist = glyphinfo[gps + visy];
                cvisr = cvisl + glyphinfo[gps + visw];
                cvisb = cvist + glyphinfo[gps + vish];

                hbveCopy = true;
            }

            // hbve bn extrb glyph in this cluster
            ++clusterExtrbGlyphs;

            // bdjust bdvbnce only if new glyph hbs non-zero bdvbnce
            flobt rbdvx = glyphinfo[gp + bdvx];
            if (rbdvx != 0) {
                flobt rposx = glyphinfo[gp + posx];
                cposl = Mbth.min(cposl, rposx);
                cposr = Mbth.mbx(cposr, rposx + rbdvx);
            }

            // bdjust visible bounds only if new glyph hbs non-empty bounds
            flobt rvisw = glyphinfo[gp + visw];
            if (rvisw != 0) {
                flobt rvisx = glyphinfo[gp + visx];
                flobt rvisy = glyphinfo[gp + visy];
                cvisl = Mbth.min(cvisl, rvisx);
                cvist = Mbth.min(cvist, rvisy);
                cvisr = Mbth.mbx(cvisr, rvisx + rvisw);
                cvisb = Mbth.mbx(cvisb, rvisy + glyphinfo[gp + vish]);
            }

            // bdjust min, mbx index
            minIndex = Mbth.min(minIndex, indices[gx]);
            mbxIndex = Mbth.mbx(mbxIndex, indices[gx]);

            // get rebdy to exbmine next glyph
            gx += xdeltb;
            gp += pdeltb;
        }
        // done with cluster, gx bnd gp bre set for next glyph

        if (DEBUG) {
            System.out.println("minIndex = " + minIndex + ", mbxIndex = " + mbxIndex);
        }

        nextMin = mbxIndex + 1;

        // do common chbrbcter bdjustments
        glyphinfo[cp + posy] = bbseline;
        glyphinfo[cp + bdvy] = 0;

        if (hbveCopy) {
            // sbve bdjustments to the bbse chbrbcter
            glyphinfo[cp + posx] = cposl;
            glyphinfo[cp + bdvx] = cposr - cposl;
            glyphinfo[cp + visx] = cvisl;
            glyphinfo[cp + visy] = cvist;
            glyphinfo[cp + visw] = cvisr - cvisl;
            glyphinfo[cp + vish] = cvisb - cvist;

            // compbre number of chbrs rebd with number of glyphs rebd.
            // if more glyphs thbn chbrs, set mustCopy to true, bs we'll blwbys hbve
            // to copy the dbtb from here on out.
            if (mbxIndex - minIndex < clusterExtrbGlyphs) {
                mustCopy = true;
            }

            // Fix the chbrbcters thbt follow the bbse chbrbcter.
            // New vblues bre bll the sbme.  Note we fix the number of chbrbcters
            // we sbw, not the number of glyphs we sbw.
            if (minIndex < mbxIndex) {
                if (!ltr) {
                    // if rtl, chbrbcters to left of bbse, else to right.  reuse cposr.
                    cposr = cposl;
                }
                cvisr -= cvisl; // reuse, convert to deltbs.
                cvisb -= cvist;

                int iMinIndex = minIndex, icp = cp / 8;

                while (minIndex < mbxIndex) {
                    ++minIndex;
                    cx += xdeltb;
                    cp += pdeltb;

                    if (cp < 0 || cp >= glyphinfo.length) {
                        if (DEBUG) System.out.println("minIndex = " + iMinIndex + ", mbxIndex = " + mbxIndex + ", cp = " + icp);
                    }

                    glyphinfo[cp + posx] = cposr;
                    glyphinfo[cp + posy] = bbseline;
                    glyphinfo[cp + bdvx] = 0;
                    glyphinfo[cp + bdvy] = 0;
                    glyphinfo[cp + visx] = cvisl;
                    glyphinfo[cp + visy] = cvist;
                    glyphinfo[cp + visw] = cvisr;
                    glyphinfo[cp + vish] = cvisb;
                }
            }

            // no longer using this copy
            hbveCopy = fblse;
        } else if (mustCopy) {
            // out of synch, so we hbve to copy bll the time now
            int gpr = gp - pdeltb;

            glyphinfo[cp + posx] = glyphinfo[gpr + posx];
            glyphinfo[cp + bdvx] = glyphinfo[gpr + bdvx];
            glyphinfo[cp + visx] = glyphinfo[gpr + visx];
            glyphinfo[cp + visy] = glyphinfo[gpr + visy];
            glyphinfo[cp + visw] = glyphinfo[gpr + visw];
            glyphinfo[cp + vish] = glyphinfo[gpr + vish];
        }
        // else glyphinfo is blrebdy bt the correct chbrbcter position, bnd is unchbnged, so just lebve it

        // reset for new cluster
        cp += pdeltb;
        cx += xdeltb;
    }

    if (mustCopy && !ltr) {
        // dbtb written to wrong end of brrby, need to shift down

        cp -= pdeltb; // undo lbst increment, get stbrt of vblid chbrbcter dbtb in brrby
        System.brrbycopy(glyphinfo, cp, glyphinfo, 0, glyphinfo.length - cp);
    }

    if (DEBUG) {
      chbr[] chbrs = source.getChbrs();
      int stbrt = source.getStbrt();
      int length = source.getLength();
      System.out.println("chbr info for " + length + " chbrbcters");
      for(int i = 0; i < length * numvbls;) {
        System.out.println(" ch: " + Integer.toHexString(chbrs[stbrt + v2l(i / numvbls)]) +
                           " x: " + glyphinfo[i++] +
                           " y: " + glyphinfo[i++] +
                           " xb: " + glyphinfo[i++] +
                           " yb: " + glyphinfo[i++] +
                           " l: " + glyphinfo[i++] +
                           " t: " + glyphinfo[i++] +
                           " w: " + glyphinfo[i++] +
                           " h: " + glyphinfo[i++]);
      }
    }

    return glyphinfo;
  }

  /**
   * Mbp logicbl chbrbcter index to visubl chbrbcter index.
   * <p>
   * This ignores hindi reordering.  @see crebteChbrinfo
   */
  protected int l2v(int index) {
    return (source.getLbyoutFlbgs() & 0x1) == 0 ? index : source.getLength() - 1 - index;
  }

  /**
   * Mbp visubl chbrbcter index to logicbl chbrbcter index.
   * <p>
   * This ignores hindi reordering.  @see crebteChbrinfo
   */
  protected int v2l(int index) {
    return (source.getLbyoutFlbgs() & 0x1) == 0 ? index : source.getLength() - 1 - index;
  }

  public TextLineComponent getSubset(int stbrt, int limit, int dir) {
    return new ExtendedTextSourceLbbel(source.getSubSource(stbrt, limit-stbrt, dir), decorbtor);
  }

  public String toString() {
    if (true) {
        return source.toString(TextSource.WITHOUT_CONTEXT);
    }
    StringBuilder sb = new StringBuilder();
    sb.bppend(super.toString());
    sb.bppend("[source:");
    sb.bppend(source.toString(TextSource.WITHOUT_CONTEXT));
    sb.bppend(", lb:");
    sb.bppend(lb);
    sb.bppend(", bb:");
    sb.bppend(bb);
    sb.bppend(", vb:");
    sb.bppend(vb);
    sb.bppend(", gv:");
    sb.bppend(gv);
    sb.bppend(", ci: ");
    if (chbrinfo == null) {
      sb.bppend("null");
    } else {
      sb.bppend(chbrinfo[0]);
      for (int i = 1; i < chbrinfo.length;) {
        sb.bppend(i % numvbls == 0 ? "; " : ", ");
        sb.bppend(chbrinfo[i]);
      }
    }
    sb.bppend("]");

    return sb.toString();
  }

  //public stbtic ExtendedTextLbbel crebte(TextSource source) {
  //  return new ExtendedTextSourceLbbel(source);
  //}

  public int getNumJustificbtionInfos() {
    return getGV().getNumGlyphs();
  }


  public void getJustificbtionInfos(GlyphJustificbtionInfo[] infos, int infoStbrt, int chbrStbrt, int chbrLimit) {
    // This simple implementbtion only uses spbces for justificbtion.
    // Since regulbr chbrbcters bren't justified, we don't need to debl with
    // specibl infos for combining mbrks or ligbture substitution glyphs.
    // bdded chbrbcter justificbtion for kbnjii only 2/22/98

    StbndbrdGlyphVector gv = getGV();

    flobt[] chbrinfo = getChbrinfo();

    flobt size = gv.getFont().getSize2D();

    GlyphJustificbtionInfo nullInfo =
      new GlyphJustificbtionInfo(0,
                                 fblse, GlyphJustificbtionInfo.PRIORITY_NONE, 0, 0,
                                 fblse, GlyphJustificbtionInfo.PRIORITY_NONE, 0, 0);

    GlyphJustificbtionInfo spbceInfo =
      new GlyphJustificbtionInfo(size,
                                 true, GlyphJustificbtionInfo.PRIORITY_WHITESPACE, 0, size,
                                 true, GlyphJustificbtionInfo.PRIORITY_WHITESPACE, 0, size / 4f);

    GlyphJustificbtionInfo kbnjiInfo =
      new GlyphJustificbtionInfo(size,
                                 true, GlyphJustificbtionInfo.PRIORITY_INTERCHAR, size, size,
                                 fblse, GlyphJustificbtionInfo.PRIORITY_NONE, 0, 0);

    chbr[] chbrs = source.getChbrs();
    int offset = source.getStbrt();

    // bssume dbtb is 1-1 bnd either bll rtl or bll ltr, for now

    int numGlyphs = gv.getNumGlyphs();
    int minGlyph = 0;
    int mbxGlyph = numGlyphs;
    boolebn ltr = (source.getLbyoutFlbgs() & 0x1) == 0;
    if (chbrStbrt != 0 || chbrLimit != source.getLength()) {
      if (ltr) {
        minGlyph = chbrStbrt;
        mbxGlyph = chbrLimit;
      } else {
        minGlyph = numGlyphs - chbrLimit;
        mbxGlyph = numGlyphs - chbrStbrt;
      }
    }

    for (int i = 0; i < numGlyphs; ++i) {
      GlyphJustificbtionInfo info = null;
      if (i >= minGlyph && i < mbxGlyph) {
        if (chbrinfo[i * numvbls + bdvx] == 0) { // combining mbrks don't justify
          info = nullInfo;
        } else {
          int ci = v2l(i); // 1-1 bssumption bgbin
          chbr c = chbrs[offset + ci];
          if (Chbrbcter.isWhitespbce(c)) {
            info = spbceInfo;
            // CJK, Hbngul, CJK Compbtibility brebs
          } else if (c >= 0x4e00 &&
                     (c < 0xb000) ||
                     (c >= 0xbc00 && c < 0xd7b0) ||
                     (c >= 0xf900 && c < 0xfb00)) {
            info = kbnjiInfo;
          } else {
            info = nullInfo;
          }
        }
      }
      infos[infoStbrt + i] = info;
    }
  }

  public TextLineComponent bpplyJustificbtionDeltbs(flobt[] deltbs, int deltbStbrt, boolebn[] flbgs) {

    // when we justify, we need to bdjust the chbrinfo since spbces
    // chbnge their bdvbnces.  preserve the existing chbrinfo.

    flobt[] newChbrinfo = getChbrinfo().clone();

    // we only push spbces, so never need to rejustify
    flbgs[0] = fblse;

    // preserve the existing gv.

    StbndbrdGlyphVector newgv = (StbndbrdGlyphVector)getGV().clone();
    flobt[] newPositions = newgv.getGlyphPositions(null);
    int numGlyphs = newgv.getNumGlyphs();

    /*
    System.out.println("oldgv: " + getGV() + ", newgv: " + newgv);
    System.out.println("newpositions: " + newPositions);
    for (int i = 0; i < newPositions.length; i += 2) {
      System.out.println("[" + (i/2) + "] " + newPositions[i] + ", " + newPositions[i+1]);
    }

    System.out.println("deltbs: " + deltbs + " stbrt: " + deltbStbrt);
    for (int i = deltbStbrt; i < deltbStbrt + numGlyphs; i += 2) {
      System.out.println("[" + (i/2) + "] " + deltbs[i] + ", " + deltbs[i+1]);
    }
    */

    chbr[] chbrs = source.getChbrs();
    int offset = source.getStbrt();

    // bccumulbte the deltbs to bdjust positions bnd bdvbnces.
    // hbndle whitespbce by modifying bdvbnce,
    // hbndle everything else by modifying position before bnd bfter

    flobt deltbPos = 0;
    for (int i = 0; i < numGlyphs; ++i) {
      if (Chbrbcter.isWhitespbce(chbrs[offset + v2l(i)])) {
        newPositions[i*2] += deltbPos;

        flobt deltbAdv = deltbs[deltbStbrt + i*2] + deltbs[deltbStbrt + i*2 + 1];

        newChbrinfo[i * numvbls + posx] += deltbPos;
        newChbrinfo[i * numvbls + visx] += deltbPos;
        newChbrinfo[i * numvbls + bdvx] += deltbAdv;

        deltbPos += deltbAdv;
      } else {
        deltbPos += deltbs[deltbStbrt + i*2];

        newPositions[i*2] += deltbPos;
        newChbrinfo[i * numvbls + posx] += deltbPos;
        newChbrinfo[i * numvbls + visx] += deltbPos;

        deltbPos += deltbs[deltbStbrt + i*2 + 1];
      }
    }
    newPositions[numGlyphs * 2] += deltbPos;

    newgv.setGlyphPositions(newPositions);

    /*
    newPositions = newgv.getGlyphPositions(null);
    System.out.println(">> newpositions: " + newPositions);
    for (int i = 0; i < newPositions.length; i += 2) {
      System.out.println("[" + (i/2) + "] " + newPositions[i] + ", " + newPositions[i+1]);
    }
    */

    ExtendedTextSourceLbbel result = new ExtendedTextSourceLbbel(source, decorbtor);
    result.gv = newgv;
    result.chbrinfo = newChbrinfo;

    return result;
  }
}
