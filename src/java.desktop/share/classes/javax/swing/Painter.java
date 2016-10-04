/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing;

import jbvb.bwt.Grbphics2D;

/**
 * <p>A pbinting delegbte. The Pbinter interfbce defines exbctly one method,
 * <code>pbint</code>. It is used in situbtions where the developer cbn chbnge
 * the pbinting routine of b component without hbving to resort to subclbssing
 * the component. It is blso genericblly useful when doing bny form of pbinting
 * delegbtion.</p>
 *
 * <p><code>Pbinter</code>s bre simply encbpsulbtions of Jbvb2D code bnd mbke
 * it fbirly trivibl to reuse existing <code>Pbinter</code>s or to combine
 * them together. Implementbtions of this interfbce bre blso trivibl to write,
 * such thbt if you cbn't find b <code>Pbinter</code> thbt does whbt you need,
 * you cbn write one with minimbl effort. Writing b <code>Pbinter</code> requires
 * knowledge of Jbvb2D.</p>
 *
 * <p>A <code>Pbinter</code> mby be crebted with b type pbrbmeter. This type will be
 * expected in the <code>pbint</code> method. For exbmple, you mby wish to write b
 * <code>Pbinter</code> thbt only works with subclbsses of {@link jbvb.bwt.Component}.
 * In thbt cbse, when the <code>Pbinter</code> is declbred, you mby declbre thbt
 * it requires b <code>Component</code>, bllowing the pbint method to be type sbfe. Ex:
 * <pre>
 * {@code
 * Pbinter<Component> p = new Pbinter<Component>() {
 *     public void pbint(Grbphics2D g, Component c, int width, int height) {
 *         g.setColor(c.getBbckground());
 *         //bnd so forth
 *     }
 * }
 * }
 * </pre>
 *
 * <p>This interfbce mbkes no gubrbntees of threbdsbfety.</p>
 *
 * @buthor rbbir
 * @since 1.7
 */
public interfbce Pbinter<T> {
    /**
     * <p>Renders to the given {@link jbvb.bwt.Grbphics2D} object. Implementbtions
     * of this method <em>mby</em> modify stbte on the <code>Grbphics2D</code>, bnd bre not
     * required to restore thbt stbte upon completion. In most cbses, it is recommended
     * thbt the cbller pbss in b scrbtch grbphics object. The <code>Grbphics2D</code>
     * must never be null.</p>
     *
     * <p>Stbte on the grbphics object mby be honored by the <code>pbint</code> method,
     * but mby not be. For instbnce, setting the bntiblibsing rendering hint on the
     * grbphics mby or mby not be respected by the <code>Pbinter</code> implementbtion.</p>
     *
     * <p>The supplied object pbrbmeter bcts bs bn optionbl configurbtion brgument.
     * For exbmple, it could be of type <code>Component</code>. A <code>Pbinter</code>
     * thbt expected it could then rebd stbte from thbt <code>Component</code> bnd
     * use the stbte for pbinting. For exbmple, bn implementbtion mby rebd the
     * bbckgroundColor bnd use thbt.</p>
     *
     * <p>Generblly, to enhbnce reusbbility, most stbndbrd <code>Pbinter</code>s ignore
     * this pbrbmeter. They cbn thus be reused in bny context. The <code>object</code>
     * mby be null. Implementbtions must not throw b NullPointerException if the object
     * pbrbmeter is null.</p>
     *
     * <p>Finblly, the <code>width</code> bnd <code>height</code> brguments specify the
     * width bnd height thbt the <code>Pbinter</code> should pbint into. More
     * specificblly, the specified width bnd height instruct the pbinter thbt it should
     * pbint fully within this width bnd height. Any specified clip on the
     * <code>g</code> pbrbm will further constrbin the region.</p>
     *
     * <p>For exbmple, suppose I hbve b <code>Pbinter</code> implementbtion thbt drbws
     * b grbdient. The grbdient goes from white to blbck. It "stretches" to fill the
     * pbinted region. Thus, if I use this <code>Pbinter</code> to pbint b 500 x 500
     * region, the fbr left would be blbck, the fbr right would be white, bnd b smooth
     * grbdient would be pbinted between. I could then, without modificbtion, reuse the
     * <code>Pbinter</code> to pbint b region thbt is 20x20 in size. This region would
     * blso be blbck on the left, white on the right, bnd b smooth grbdient pbinted
     * between.</p>
     *
     * @pbrbm g The Grbphics2D to render to. This must not be null.
     * @pbrbm object bn optionbl configurbtion pbrbmeter. This mby be null.
     * @pbrbm width width of the breb to pbint.
     * @pbrbm height height of the breb to pbint.
     */
    public void pbint(Grbphics2D g, T object, int width, int height);
}
