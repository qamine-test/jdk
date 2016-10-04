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

import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.WritbbleRbster;

/**
 * The <code>CompositeContext</code> interfbce defines the encbpsulbted
 * bnd optimized environment for b compositing operbtion.
 * <code>CompositeContext</code> objects mbintbin stbte for
 * compositing operbtions.  In b multi-threbded environment, severbl
 * contexts cbn exist simultbneously for b single {@link Composite}
 * object.
 * @see Composite
 */

public interfbce CompositeContext {
    /**
     * Relebses resources bllocbted for b context.
     */
    public void dispose();

    /**
     * Composes the two source {@link Rbster} objects bnd
     * plbces the result in the destinbtion
     * {@link WritbbleRbster}.  Note thbt the destinbtion
     * cbn be the sbme object bs either the first or second
     * source. Note thbt <code>dstIn</code> bnd
     * <code>dstOut</code> must be compbtible with the
     * <code>dstColorModel</code> pbssed to the
     * {@link Composite#crebteContext(jbvb.bwt.imbge.ColorModel, jbvb.bwt.imbge.ColorModel, jbvb.bwt.RenderingHints) crebteContext}
     * method of the <code>Composite</code> interfbce.
     * @pbrbm src the first source for the compositing operbtion
     * @pbrbm dstIn the second source for the compositing operbtion
     * @pbrbm dstOut the <code>WritbbleRbster</code> into which the
     * result of the operbtion is stored
     * @see Composite
     */
    public void compose(Rbster src,
                        Rbster dstIn,
                        WritbbleRbster dstOut);


}
