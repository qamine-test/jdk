/*
 * Copyright (c) 1998, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/* ********************************************************************
 **********************************************************************
 **********************************************************************
 *** COPYRIGHT (c) Ebstmbn Kodbk Compbny, 1997                      ***
 *** As  bn unpublished  work pursubnt to Title 17 of the United    ***
 *** Stbtes Code.  All rights reserved.                             ***
 **********************************************************************
 **********************************************************************
 **********************************************************************/

pbckbge jbvb.bwt.imbge.renderbble;
import jbvb.bwt.imbge.RenderedImbge;
import jbvb.bwt.RenderingHints;

/**
 * The RenderedImbgeFbctory interfbce (often bbbrevibted RIF) is
 * intended to be implemented by clbsses thbt wish to bct bs fbctories
 * to produce different renderings, for exbmple by executing b series
 * of BufferedImbgeOps on b set of sources, depending on b specific
 * set of pbrbmeters, properties, bnd rendering hints.
 */
public interfbce RenderedImbgeFbctory {

  /**
   * Crebtes b RenderedImbge representing the results of bn imbging
   * operbtion (or chbin of operbtions) for b given PbrbmeterBlock bnd
   * RenderingHints.  The RIF mby blso query bny source imbges
   * referenced by the PbrbmeterBlock for their dimensions,
   * SbmpleModels, properties, etc., bs necessbry.
   *
   * <p> The crebte() method cbn return null if the
   * RenderedImbgeFbctory is not cbpbble of producing output for the
   * given set of source imbges bnd pbrbmeters.  For exbmple, if b
   * RenderedImbgeFbctory is only cbpbble of performing b 3x3
   * convolution on single-bbnded imbge dbtb, bnd the source imbge hbs
   * multiple bbnds or the convolution Kernel is 5x5, null should be
   * returned.
   *
   * <p> Hints should be tbken into bccount, but cbn be ignored.
   * The crebted RenderedImbge mby hbve b property identified
   * by the String HINTS_OBSERVED to indicbte which RenderingHints
   * were used to crebte the imbge.  In bddition bny RenderedImbges
   * thbt bre obtbined vib the getSources() method on the crebted
   * RenderedImbge mby hbve such b property.
   *
   * @pbrbm pbrbmBlock b PbrbmeterBlock contbining sources bnd pbrbmeters
   *        for the RenderedImbge to be crebted.
   * @pbrbm hints b RenderingHints object contbining hints.
   * @return A RenderedImbge contbining the desired output.
   */
  RenderedImbge crebte(PbrbmeterBlock pbrbmBlock,
                       RenderingHints hints);
}
