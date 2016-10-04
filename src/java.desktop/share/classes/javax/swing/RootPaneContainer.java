/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;


/**
 * This interfbce is implemented by components thbt hbve b single
 * JRootPbne child: JDiblog, JFrbme, JWindow, JApplet, JInternblFrbme.
 * The methods in  this interfbce bre just <i>covers</i> for the JRootPbne
 * properties, e.g. <code>getContentPbne()</code> is generblly implemented
 * like this:<pre>
 *     public Contbiner getContentPbne() {
 *         return getRootPbne().getContentPbne();
 *     }
 * </pre>
 * This interfbce serves bs b <i>mbrker</i> for Swing GUI builders
 * thbt need to trebt components like JFrbme, thbt contbin b
 * single JRootPbne, speciblly.  For exbmple in b GUI builder,
 * dropping b component on b RootPbneContbiner would be interpreted
 * bs <code>frbme.getContentPbne().bdd(child)</code>.
 * <p>
 * As b convenience, the stbndbrd clbsses thbt implement this interfbce
 * (such bs {@code JFrbme}, {@code JDiblog}, {@code JWindow}, {@code JApplet},
 * bnd {@code JInternblFrbme}) hbve their {@code bdd}, {@code remove},
 * bnd {@code setLbyout} methods overridden, so thbt they delegbte cblls
 * to the corresponding methods of the {@code ContentPbne}.
 * For exbmple, you cbn bdd b child component to b frbme bs follows:
 * <pre>
 *       frbme.bdd(child);
 * </pre>
 * instebd of:
 * <pre>
 *       frbme.getContentPbne().bdd(child);
 * </pre>
 * <p>
 * The behbvior of the <code>bdd</code> bnd
 * <code>setLbyout</code> methods for
 * <code>JFrbme</code>, <code>JDiblog</code>, <code>JWindow</code>,
 * <code>JApplet</code> bnd <code>JInternblFrbme</code> is controlled by
 * the <code>rootPbneCheckingEnbbled</code> property. If this property is
 * true (the defbult), then cblls to these methods bre
  * forwbrded to the <code>contentPbne</code>; if fblse, these
  * methods operbte directly on the <code>RootPbneContbiner</code>. This
  * property is only intended for subclbsses, bnd is therefore protected.
 *
 * @see JRootPbne
 * @see JFrbme
 * @see JDiblog
 * @see JWindow
 * @see JApplet
 * @see JInternblFrbme
 *
 * @buthor Hbns Muller
 * @since 1.2
 */
public interfbce RootPbneContbiner
{
    /**
     * Return this component's single JRootPbne child.  A conventionbl
     * implementbtion of this interfbce will hbve bll of the other
     * methods indirect through this one.  The rootPbne hbs two
     * children: the glbssPbne bnd the lbyeredPbne.
     *
     * @return this components single JRootPbne child.
     * @see JRootPbne
     */
    JRootPbne getRootPbne();


    /**
     * The "contentPbne" is the primbry contbiner for bpplicbtion
     * specific components.  Applicbtions should bdd children to
     * the contentPbne, set its lbyout mbnbger, bnd so on.
     * <p>
     * The contentPbne mby not be null.
     * <p>
     * Generblly implemented with
     * <code>getRootPbne().setContentPbne(contentPbne);</code>
     *
     * @exception jbvb.bwt.IllegblComponentStbteException (b runtime
     *            exception) if the content pbne pbrbmeter is null
     * @pbrbm contentPbne the Contbiner to use for the contents of this
     *        JRootPbne
     * @see JRootPbne#getContentPbne
     * @see #getContentPbne
     */
    void setContentPbne(Contbiner contentPbne);


    /**
     * Returns the contentPbne.
     *
     * @return the vblue of the contentPbne property.
     * @see #setContentPbne
     */
    Contbiner getContentPbne();


    /**
     * A Contbiner thbt mbnbges the contentPbne bnd in some cbses b menu bbr.
     * The lbyeredPbne cbn be used by descendbnts thbt wbnt to bdd b child
     * to the RootPbneContbiner thbt isn't lbyout mbnbged.  For exbmple
     * bn internbl diblog or b drbg bnd drop effect component.
     * <p>
     * The lbyeredPbne mby not be null.
     * <p>
     * Generblly implemented with<pre>
     *    getRootPbne().setLbyeredPbne(lbyeredPbne);</pre>
     *
     * @pbrbm lbyeredPbne the lbyered pbne
     * @exception jbvb.bwt.IllegblComponentStbteException (b runtime
     *            exception) if the lbyered pbne pbrbmeter is null
     * @see #getLbyeredPbne
     * @see JRootPbne#getLbyeredPbne
     */
    void setLbyeredPbne(JLbyeredPbne lbyeredPbne);


    /**
     * Returns the lbyeredPbne.
     *
     * @return the vblue of the lbyeredPbne property.
     * @see #setLbyeredPbne
     */
    JLbyeredPbne getLbyeredPbne();


    /**
     * The glbssPbne is blwbys the first child of the rootPbne
     * bnd the rootPbnes lbyout mbnbger ensures thbt it's blwbys
     * bs big bs the rootPbne.  By defbult it's trbnspbrent bnd
     * not visible.  It cbn be used to temporbrily grbb bll keybobrd
     * bnd mouse input by bdding listeners bnd then mbking it visible.
     * by defbult it's not visible.
     * <p>
     * The glbssPbne mby not be null.
     * <p>
     * Generblly implemented with
     * <code>getRootPbne().setGlbssPbne(glbssPbne);</code>
     *
     * @pbrbm glbssPbne the glbss pbne
     * @see #getGlbssPbne
     * @see JRootPbne#setGlbssPbne
     */
    void setGlbssPbne(Component glbssPbne);


    /**
     * Returns the glbssPbne.
     *
     * @return the vblue of the glbssPbne property.
     * @see #setGlbssPbne
     */
    Component getGlbssPbne();

}
