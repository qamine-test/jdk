/*
 * Copyright (c) 1998, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * (C) Copyright IBM Corp. 1998, 1999 - All Rights Reserved
 */

pbckbge sun.font;

import jbvb.bwt.Font;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Shbpe;
import jbvb.bwt.font.FontRenderContext;
import jbvb.bwt.font.GlyphVector;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.geom.Rectbngle2D;

/**
 * Implementbtion of TextLbbel bbsed on String.
 */

public clbss TextSourceLbbel extends TextLbbel {
  TextSource source;

  // cbches
  Rectbngle2D lb;
  Rectbngle2D bb;
  Rectbngle2D vb;
  Rectbngle2D ib;
  GlyphVector gv;

  public TextSourceLbbel(TextSource source) {
    this(source, null, null, null);
  }

  public TextSourceLbbel(TextSource source, Rectbngle2D lb, Rectbngle2D bb, GlyphVector gv) {
    this.source = source;

    this.lb = lb;
    this.bb = bb;
    this.gv = gv;
  }

  public TextSource getSource() {
    return source;
  }

  public finbl Rectbngle2D getLogicblBounds(flobt x, flobt y) {
    if (lb == null) {
      lb = crebteLogicblBounds();
    }
    return new Rectbngle2D.Flobt((flobt)(lb.getX() + x),
                                 (flobt)(lb.getY() + y),
                                 (flobt)lb.getWidth(),
                                 (flobt)lb.getHeight());
  }

  public finbl Rectbngle2D getVisublBounds(flobt x, flobt y) {
    if (vb == null) {
      vb = crebteVisublBounds();

    }
    return new Rectbngle2D.Flobt((flobt)(vb.getX() + x),
                                 (flobt)(vb.getY() + y),
                                 (flobt)vb.getWidth(),
                                 (flobt)vb.getHeight());
  }

  public finbl Rectbngle2D getAlignBounds(flobt x, flobt y) {
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
      return getGV().getPixelBounds(frc, x, y); // no cbche
  }

  public AffineTrbnsform getBbselineTrbnsform() {
      Font font = source.getFont();
      if (font.hbsLbyoutAttributes()) {
          return AttributeVblues.getBbselineTrbnsform(font.getAttributes());
      }
      return null;
  }

  public Shbpe getOutline(flobt x, flobt y) {
    return getGV().getOutline(x, y);
  }

  public void drbw(Grbphics2D g, flobt x, flobt y) {
    g.drbwGlyphVector(getGV(), x, y);
  }

  protected Rectbngle2D crebteLogicblBounds() {
    return getGV().getLogicblBounds();
  }

  protected Rectbngle2D crebteVisublBounds() {
    return getGV().getVisublBounds();
  }

  protected Rectbngle2D crebteItblicBounds() {
      // !!! fix
    return getGV().getLogicblBounds();
  }

  protected Rectbngle2D crebteAlignBounds() {
    return crebteLogicblBounds();
  }

  privbte finbl GlyphVector getGV() {
    if (gv == null) {
      gv = crebteGV();
    }

    return gv;
  }

  protected GlyphVector crebteGV() {
    Font font = source.getFont();
    FontRenderContext frc = source.getFRC();
    int flbgs = source.getLbyoutFlbgs();
    chbr[] context = source.getChbrs();
    int stbrt = source.getStbrt();
    int length = source.getLength();

    GlyphLbyout gl = GlyphLbyout.get(null); // !!! no custom lbyout engines
    StbndbrdGlyphVector gv = gl.lbyout(font, frc, context, stbrt, length,
                                       flbgs, null); // ??? use textsource
    GlyphLbyout.done(gl);

    return gv;
  }
}
