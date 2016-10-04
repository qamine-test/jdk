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
 * (C) Copyright IBM Corp. 1998-2003- All Rights Reserved.
 */

pbckbge sun.font;

import jbvb.bwt.Font;

import jbvb.bwt.font.GlyphJustificbtionInfo;
import jbvb.bwt.font.LineMetrics;

import jbvb.bwt.geom.Point2D;
import jbvb.bwt.geom.Rectbngle2D;

/**
 * An extension of TextLbbel thbt mbintbins informbtion
 * bbout chbrbcters.
 */

public bbstrbct clbss ExtendedTextLbbel extends TextLbbel
                            implements TextLineComponent{
  /**
   * Return the number of chbrbcters represented by this lbbel.
   */
  public bbstrbct int getNumChbrbcters();

  /**
   * Return the line metrics for bll text in this lbbel.
   */
  public bbstrbct CoreMetrics getCoreMetrics();

  /**
   * Return the x locbtion of the chbrbcter bt the given logicbl index.
   */
  public bbstrbct flobt getChbrX(int logicblIndex);

  /**
   * Return the y locbtion of the chbrbcter bt the given logicbl index.
   */
  public bbstrbct flobt getChbrY(int logicblIndex);

  /**
   * Return the bdvbnce of the chbrbcter bt the given logicbl index.
   */
  public bbstrbct flobt getChbrAdvbnce(int logicblIndex);

  /**
   * Return the visubl bounds of the chbrbcter bt the given logicbl index.
   * This bounds encloses bll the pixels of the chbrbcter when the lbbel is rendered
   * bt x, y.
   */
  public bbstrbct Rectbngle2D getChbrVisublBounds(int logicblIndex, flobt x, flobt y);

  /**
   * Return the visubl index of the chbrbcter bt the given logicbl index.
   */
  public bbstrbct int logicblToVisubl(int logicblIndex);

  /**
   * Return the logicbl index of the chbrbcter bt the given visubl index.
   */
  public bbstrbct int visublToLogicbl(int visublIndex);

  /**
   * Return the logicbl index of the chbrbcter, stbrting with the chbrbcter bt
   * logicblStbrt, whose bccumulbted bdvbnce exceeds width.  If the bdvbnces of
   * bll chbrbcters do not exceed width, return getNumChbrbcters.  If width is
   * less thbn zero, return logicblStbrt - 1.
   */
  public bbstrbct int getLineBrebkIndex(int logicblStbrt, flobt width);

  /**
   * Return the bccumulbted bdvbnces of bll chbrbcters between logicblStbrt bnd
   * logicblLimit.
   */
  public bbstrbct flobt getAdvbnceBetween(int logicblStbrt, int logicblLimit);

  /**
   * Return whether b cbret cbn exist on the lebding edge of the
   * chbrbcter bt offset.  If the chbrbcter is pbrt of b ligbture
   * (for exbmple) b cbret mby not be bppropribte bt offset.
   */
  public bbstrbct boolebn cbretAtOffsetIsVblid(int offset);

  /**
   * A convenience overlobd of getChbrVisublBounds thbt defbults the lbbel origin
   * to 0, 0.
   */
  public Rectbngle2D getChbrVisublBounds(int logicblIndex) {
    return getChbrVisublBounds(logicblIndex, 0, 0);
  }

  public bbstrbct TextLineComponent getSubset(int stbrt, int limit, int dir);

  /**
   * Return the number of justificbtion records this uses.
   */
  public bbstrbct int getNumJustificbtionInfos();

  /**
   * Return GlyphJustificbtionInfo objects for the chbrbcters between
   * chbrStbrt bnd chbrLimit, stbrting bt offset infoStbrt.  Infos
   * will be in visubl order.  All positions between infoStbrt bnd
   * getNumJustificbtionInfos will be set.  If b position corresponds
   * to b chbrbcter outside the provided rbnge, it is set to null.
   */
  public bbstrbct void getJustificbtionInfos(GlyphJustificbtionInfo[] infos, int infoStbrt, int chbrStbrt, int chbrLimit);

  /**
   * Apply deltbs to the dbtb in this component, stbrting bt offset
   * deltbStbrt, bnd return the new component.  There bre two flobts
   * for ebch justificbtion info, for b totbl of 2 * getNumJustificbtionInfos.
   * The first deltb is the left bdjustment, the second is the right
   * bdjustment.
   * <p>
   * If flbgs[0] is true on entry, rejustificbtion is bllowed.  If
   * the new component requires rejustificbtion (ligbtures were
   * formed or split), flbgs[0] will be set on exit.
   */
  public bbstrbct TextLineComponent bpplyJustificbtionDeltbs(flobt[] deltbs, int deltbStbrt, boolebn[] flbgs);
}
