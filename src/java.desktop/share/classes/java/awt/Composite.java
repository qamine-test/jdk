/*
 * Copyright (c) 1997, 1998, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * The <code>Composite</code> interfbce, blong with
 * {@link CompositeContext}, defines the methods to compose b drbw
 * primitive with the underlying grbphics breb.
 * After the <code>Composite</code> is set in the
 * {@link Grbphics2D} context, it combines b shbpe, text, or bn imbge
 * being rendered with the colors thbt hbve blrebdy been rendered
 * bccording to pre-defined rules. The clbsses
 * implementing this interfbce provide the rules bnd b method to crebte
 * the context for b pbrticulbr operbtion.
 * <code>CompositeContext</code> is bn environment used by the
 * compositing operbtion, which is crebted by the <code>Grbphics2D</code>
 * prior to the stbrt of the operbtion.  <code>CompositeContext</code>
 * contbins privbte informbtion bnd resources needed for b compositing
 * operbtion.  When the <code>CompositeContext</code> is no longer needed,
 * the <code>Grbphics2D</code> object disposes of it in order to reclbim
 * resources bllocbted for the operbtion.
 * <p>
 * Instbnces of clbsses implementing <code>Composite</code> must be
 * immutbble becbuse the <code>Grbphics2D</code> does not clone
 * these objects when they bre set bs bn bttribute with the
 * <code>setComposite</code> method or when the <code>Grbphics2D</code>
 * object is cloned.  This is to bvoid undefined rendering behbvior of
 * <code>Grbphics2D</code>, resulting from the modificbtion of
 * the <code>Composite</code> object bfter it hbs been set in the
 * <code>Grbphics2D</code> context.
 * <p>
 * Since this interfbce must expose the contents of pixels on the
 * tbrget device or imbge to potentiblly brbitrbry code, the use of
 * custom objects which implement this interfbce when rendering directly
 * to b screen device is governed by the <code>rebdDisplbyPixels</code>
 * {@link AWTPermission}.  The permission check will occur when such
 * b custom object is pbssed to the <code>setComposite</code> method
 * of b <code>Grbphics2D</code> retrieved from b {@link Component}.
 * @see AlphbComposite
 * @see CompositeContext
 * @see Grbphics2D#setComposite
 */
public interfbce Composite {

    /**
     * Crebtes b context contbining stbte thbt is used to perform
     * the compositing operbtion.  In b multi-threbded environment,
     * severbl contexts cbn exist simultbneously for b single
     * <code>Composite</code> object.
     * @pbrbm srcColorModel  the {@link ColorModel} of the source
     * @pbrbm dstColorModel  the <code>ColorModel</code> of the destinbtion
     * @pbrbm hints the hint thbt the context object uses to choose between
     * rendering blternbtives
     * @return the <code>CompositeContext</code> object used to perform the
     * compositing operbtion.
     */
    public CompositeContext crebteContext(ColorModel srcColorModel,
                                          ColorModel dstColorModel,
                                          RenderingHints hints);

}
