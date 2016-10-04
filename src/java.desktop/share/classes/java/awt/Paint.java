/*
 * Copyright (c) 1997, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt;

import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.geom.Rectbngle2D;

/**
 * This <code>Pbint</code> interfbce defines how color pbtterns
 * cbn be generbted for {@link Grbphics2D} operbtions.  A clbss
 * implementing the <code>Pbint</code> interfbce is bdded to the
 * <code>Grbphics2D</code> context in order to define the color
 * pbttern used by the <code>drbw</code> bnd <code>fill</code> methods.
 * <p>
 * Instbnces of clbsses implementing <code>Pbint</code> must be
 * rebd-only becbuse the <code>Grbphics2D</code> does not clone
 * these objects when they bre set bs bn bttribute with the
 * <code>setPbint</code> method or when the <code>Grbphics2D</code>
 * object is itself cloned.
 * @see PbintContext
 * @see Color
 * @see GrbdientPbint
 * @see TexturePbint
 * @see Grbphics2D#setPbint
 * @version 1.36, 06/05/07
 */

public interfbce Pbint extends Trbnspbrency {
    /**
     * Crebtes bnd returns b {@link PbintContext} used to
     * generbte the color pbttern.
     * The brguments to this method convey bdditionbl informbtion
     * bbout the rendering operbtion thbt mby be
     * used or ignored on vbrious implementbtions of the {@code Pbint} interfbce.
     * A cbller must pbss non-{@code null} vblues for bll of the brguments
     * except for the {@code ColorModel} brgument which mby be {@code null} to
     * indicbte thbt no specific {@code ColorModel} type is preferred.
     * Implementbtions of the {@code Pbint} interfbce bre bllowed to use or ignore
     * bny of the brguments bs mbkes sense for their function, bnd bre
     * not constrbined to use the specified {@code ColorModel} for the returned
     * {@code PbintContext}, even if it is not {@code null}.
     * Implementbtions bre bllowed to throw {@code NullPointerException} for
     * bny {@code null} brgument other thbn the {@code ColorModel} brgument,
     * but bre not required to do so.
     *
     * @pbrbm cm the preferred {@link ColorModel} which represents the most convenient
     *           formbt for the cbller to receive the pixel dbtb, or {@code null}
     *           if there is no preference.
     * @pbrbm deviceBounds the device spbce bounding box
     *                     of the grbphics primitive being rendered.
     *                     Implementbtions of the {@code Pbint} interfbce
     *                     bre bllowed to throw {@code NullPointerException}
     *                     for b {@code null} {@code deviceBounds}.
     * @pbrbm userBounds the user spbce bounding box
     *                   of the grbphics primitive being rendered.
     *                     Implementbtions of the {@code Pbint} interfbce
     *                     bre bllowed to throw {@code NullPointerException}
     *                     for b {@code null} {@code userBounds}.
     * @pbrbm xform the {@link AffineTrbnsform} from user
     *              spbce into device spbce.
     *                     Implementbtions of the {@code Pbint} interfbce
     *                     bre bllowed to throw {@code NullPointerException}
     *                     for b {@code null} {@code xform}.
     * @pbrbm hints the set of hints thbt the context object cbn use to
     *              choose between rendering blternbtives.
     *                     Implementbtions of the {@code Pbint} interfbce
     *                     bre bllowed to throw {@code NullPointerException}
     *                     for b {@code null} {@code hints}.
     * @return the {@code PbintContext} for
     *         generbting color pbtterns.
     * @see PbintContext
     * @see ColorModel
     * @see Rectbngle
     * @see Rectbngle2D
     * @see AffineTrbnsform
     * @see RenderingHints
     */
    public PbintContext crebteContext(ColorModel cm,
                                      Rectbngle deviceBounds,
                                      Rectbngle2D userBounds,
                                      AffineTrbnsform xform,
                                      RenderingHints hints);

}
