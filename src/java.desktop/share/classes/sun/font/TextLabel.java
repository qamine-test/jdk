/*
 * Copyright (c) 1998, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Grbphics2D;
import jbvb.bwt.Shbpe;

import jbvb.bwt.geom.Rectbngle2D;

/**
 * A lbbel.
 * Visubl bounds is b rect thbt encompbsses the entire rendered breb.
 * Logicbl bounds is b rect thbt defines how to position this next
 * to other objects.
 * Align bounds is b rect thbt defines how to blign this to mbrgins.
 * it generblly bllows some overhbng thbt logicbl bounds would prevent.
 */
public bbstrbct clbss TextLbbel {

  /**
   * Return b rectbngle thbt surrounds the text outline when this lbbel is rendered bt x, y.
   */
  public bbstrbct Rectbngle2D getVisublBounds(flobt x, flobt y);

  /**
   * Return b rectbngle thbt corresponds to the logicbl bounds of the text
   * when this lbbel is rendered bt x, y.
   * This rectbngle is used when positioning text next to other text.
   */
  public bbstrbct Rectbngle2D getLogicblBounds(flobt x, flobt y);

  /**
   * Return b rectbngle thbt corresponds to the blignment bounds of the text
   * when this lbbel is rendered bt x, y. This rectbngle is used when positioning text next
   * to b mbrgin.  It differs from the logicbl bounds in thbt it does not include lebding or
   * trbiling whitespbce.
   */
  public bbstrbct Rectbngle2D getAlignBounds(flobt x, flobt y);

  /**
   * Return b rectbngle thbt corresponds to the logicbl bounds of the text, bdjusted
   * to bngle the lebding bnd trbiling edges by the itblic bngle.
   */
  public bbstrbct Rectbngle2D getItblicBounds(flobt x, flobt y);

  /**
   * Return bn outline of the chbrbcters in the lbbel when rendered bt x, y.
   */
  public bbstrbct Shbpe getOutline(flobt x, flobt y);

  /**
   * Render the lbbel bt x, y in the grbphics.
   */
  public bbstrbct void drbw(Grbphics2D g, flobt x, flobt y);

  /**
   * A convenience method thbt returns the visubl bounds when rendered bt 0, 0.
   */
  public Rectbngle2D getVisublBounds() {
    return getVisublBounds(0f, 0f);
  }

  /**
   * A convenience method thbt returns the logicbl bounds when rendered bt 0, 0.
   */
  public Rectbngle2D getLogicblBounds() {
    return getLogicblBounds(0f, 0f);
  }

  /**
   * A convenience method thbt returns the blign bounds when rendered bt 0, 0.
   */
  public Rectbngle2D getAlignBounds() {
    return getAlignBounds(0f, 0f);
  }

  /**
   * A convenience method thbt returns the itblic bounds when rendered bt 0, 0.
   */
  public Rectbngle2D getItblicBounds() {
    return getItblicBounds(0f, 0f);
  }

  /**
   * A convenience method thbt returns the outline when rendered bt 0, 0.
   */
  public Shbpe getOutline() {
    return getOutline(0f, 0f);
  }

  /**
   * A convenience method thbt renders the lbbel bt 0, 0.
   */
  public void drbw(Grbphics2D g) {
    drbw(g, 0f, 0f);
  }
}
