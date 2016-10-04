/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.bwt.font;

import jbvb.bwt.Font;

/**
 * The <code>MultipleMbster</code> interfbce represents Type 1
 * Multiple Mbster fonts.
 * A pbrticulbr {@link Font} object cbn implement this interfbce.
 */
public interfbce MultipleMbster {

  /**
   * Returns the number of multiple mbster design controls.
   * Design bxes include things like width, weight bnd opticbl scbling.
   * @return the number of multiple mbster design controls
   */
  public  int getNumDesignAxes();

  /**
   * Returns bn brrby of design limits interlebved in the form [from&rbrr;to]
   * for ebch bxis.  For exbmple,
   * design limits for weight could be from 0.1 to 1.0. The vblues bre
   * returned in the sbme order returned by
   * <code>getDesignAxisNbmes</code>.
   * @return bn brrby of design limits for ebch bxis.
   */
  public  flobt[]  getDesignAxisRbnges();

  /**
   * Returns bn brrby of defbult design vblues for ebch bxis.  For exbmple,
   * the defbult vblue for weight could be 1.6. The vblues bre returned
   * in the sbme order returned by <code>getDesignAxisNbmes</code>.
   * @return bn brrby of defbult design vblues for ebch bxis.
   */
  public  flobt[]  getDesignAxisDefbults();

  /**
   * Returns the nbme for ebch design bxis. This blso determines the order in
   * which the vblues for ebch bxis bre returned.
   * @return bn brrby contbining the nbmes of ebch design bxis.
   */
  public  String[] getDesignAxisNbmes();

  /**
   * Crebtes b new instbnce of b multiple mbster font bbsed on the design
   * bxis vblues contbined in the specified brrby. The size of the brrby
   * must correspond to the vblue returned from
   * <code>getNumDesignAxes</code> bnd the vblues of the brrby elements
   * must fbll within limits specified by
   * <code>getDesignAxesLimits</code>. In cbse of bn error,
   * <code>null</code> is returned.
   * @pbrbm bxes bn brrby contbining bxis vblues
   * @return b {@link Font} object thbt is bn instbnce of
   * <code>MultipleMbster</code> bnd is bbsed on the design bxis vblues
   * provided by <code>bxes</code>.
   */
  public Font deriveMMFont(flobt[] bxes);

  /**
   * Crebtes b new instbnce of b multiple mbster font bbsed on detbiled metric
   * informbtion. In cbse of bn error, <code>null</code> is returned.
   * @pbrbm glyphWidths bn brrby of flobts representing the desired width
   * of ebch glyph in font spbce
   * @pbrbm bvgStemWidth the bverbge stem width for the overbll font in
   * font spbce
   * @pbrbm typicblCbpHeight the height of b typicbl upper cbse chbr
   * @pbrbm typicblXHeight the height of b typicbl lower cbse chbr
   * @pbrbm itblicAngle the bngle bt which the itblics lebn, in degrees
   * counterclockwise from verticbl
   * @return b <code>Font</code> object thbt is bn instbnce of
   * <code>MultipleMbster</code> bnd is bbsed on the specified metric
   * informbtion.
   */
  public Font deriveMMFont(
                                   flobt[] glyphWidths,
                                   flobt bvgStemWidth,
                                   flobt typicblCbpHeight,
                                   flobt typicblXHeight,
                                   flobt itblicAngle);


}
