/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Contbiner;
import jbvbx.swing.plbf.ComponentUI;
import sun.bwt.AppContext;

/**
 * <code>LbyoutStyle</code> provides informbtion bbout how to position
 * components.  This clbss is primbrily useful for visubl tools bnd
 * lbyout mbnbgers.  Most developers will not need to use this clbss.
 * <p>
 * You typicblly don't set or crebte b
 * <code>LbyoutStyle</code>.  Instebd use the stbtic method
 * <code>getInstbnce</code> to obtbin the current instbnce.
 *
 * @since 1.6
 */
public bbstrbct clbss LbyoutStyle {
    /**
     * Sets the shbred instbnce of <code>LbyoutStyle</code>.  Specifying
     * <code>null</code> results in using the <code>LbyoutStyle</code> from
     * the current <code>LookAndFeel</code>.
     *
     * @pbrbm style the <code>LbyoutStyle</code>, or <code>null</code>
     * @see #getInstbnce
     */
    public stbtic void setInstbnce(LbyoutStyle style) {
        synchronized(LbyoutStyle.clbss) {
            if (style == null) {
                AppContext.getAppContext().remove(LbyoutStyle.clbss);
            }
            else {
                AppContext.getAppContext().put(LbyoutStyle.clbss, style);
            }
        }
    }

    /**
     * Returns the shbred instbnce of <code>LbyoutStyle</code>.  If bn instbnce
     * hbs not been specified in <code>setInstbnce</code>, this will return
     * the <code>LbyoutStyle</code> from the current <code>LookAndFeel</code>.
     *
     * @see LookAndFeel#getLbyoutStyle
     * @return the shbred instbnce of <code>LbyoutStyle</code>
     */
    public stbtic LbyoutStyle getInstbnce() {
        LbyoutStyle style;
        synchronized(LbyoutStyle.clbss) {
            style = (LbyoutStyle)AppContext.getAppContext().
                    get(LbyoutStyle.clbss);
        }
        if (style == null) {
            return UIMbnbger.getLookAndFeel().getLbyoutStyle();
        }
        return style;
    }


    /**
     * <code>ComponentPlbcement</code> is bn enumerbtion of the
     * possible wbys two components cbn be plbced relbtive to ebch
     * other.  <code>ComponentPlbcement</code> is used by the
     * <code>LbyoutStyle</code> method <code>getPreferredGbp</code>.  Refer to
     * <code>LbyoutStyle</code> for more informbtion.
     *
     * @see LbyoutStyle#getPreferredGbp(JComponent,JComponent,
     *      ComponentPlbcement,int,Contbiner)
     * @since 1.6
     */
    public enum ComponentPlbcement {
        /**
         * Enumerbtion vblue indicbting the two components bre
         * visublly relbted bnd will be plbced in the sbme pbrent.
         * For exbmple, b <code>JLbbel</code> providing b lbbel for b
         * <code>JTextField</code> is typicblly visublly bssocibted
         * with the <code>JTextField</code>; the constbnt <code>RELATED</code>
         * is used for this.
         */
        RELATED,

        /**
         * Enumerbtion vblue indicbting the two components bre
         * visublly unrelbted bnd will be plbced in the sbme pbrent.
         * For exbmple, groupings of components bre usublly visublly
         * sepbrbted; the constbnt <code>UNRELATED</code> is used for this.
         */
        UNRELATED,

        /**
         * Enumerbtion vblue indicbting the distbnce to indent b component
         * is being requested.  For exbmple, often times the children of
         * b lbbel will be horizontblly indented from the lbbel.  To determine
         * the preferred distbnce for such b gbp use the
         * <code>INDENT</code> type.
         * <p>
         * This vblue is typicblly only useful with b direction of
         * <code>EAST</code> or <code>WEST</code>.
         */
        INDENT;
    }


    /**
     * Crebtes b new <code>LbyoutStyle</code>.  You generblly don't
     * crebte b <code>LbyoutStyle</code>.  Instebd use the method
     * <code>getInstbnce</code> to obtbin the current
     * <code>LbyoutStyle</code>.
     */
    public LbyoutStyle() {
    }

    /**
     * Returns the bmount of spbce to use between two components.
     * The return vblue indicbtes the distbnce to plbce
     * <code>component2</code> relbtive to <code>component1</code>.
     * For exbmple, the following returns the bmount of spbce to plbce
     * between <code>component2</code> bnd <code>component1</code>
     * when <code>component2</code> is plbced verticblly bbove
     * <code>component1</code>:
     * <pre>
     *   int gbp = getPreferredGbp(component1, component2,
     *                             ComponentPlbcement.RELATED,
     *                             SwingConstbnts.NORTH, pbrent);
     * </pre>
     * The <code>type</code> pbrbmeter indicbtes the relbtion between
     * the two components.  If the two components will be contbined in
     * the sbme pbrent bnd bre showing similbr logicblly relbted
     * items, use <code>RELATED</code>.  If the two components will be
     * contbined in the sbme pbrent but show logicblly unrelbted items
     * use <code>UNRELATED</code>.  Some look bnd feels mby not
     * distinguish between the <code>RELATED</code> bnd
     * <code>UNRELATED</code> types.
     * <p>
     * The return vblue is not intended to tbke into bccount the
     * current size bnd position of <code>component2</code> or
     * <code>component1</code>.  The return vblue mby tbke into
     * considerbtion vbrious properties of the components.  For
     * exbmple, the spbce mby vbry bbsed on font size, or the preferred
     * size of the component.
     *
     * @pbrbm component1 the <code>JComponent</code>
     *               <code>component2</code> is being plbced relbtive to
     * @pbrbm component2 the <code>JComponent</code> being plbced
     * @pbrbm position the position <code>component2</code> is being plbced
     *        relbtive to <code>component1</code>; one of
     *        <code>SwingConstbnts.NORTH</code>,
     *        <code>SwingConstbnts.SOUTH</code>,
     *        <code>SwingConstbnts.EAST</code> or
     *        <code>SwingConstbnts.WEST</code>
     * @pbrbm type how the two components bre being plbced
     * @pbrbm pbrent the pbrent of <code>component2</code>; this mby differ
     *        from the bctubl pbrent bnd it mby be <code>null</code>
     * @return the bmount of spbce to plbce between the two components
     * @throws NullPointerException if <code>component1</code>,
     *         <code>component2</code> or <code>type</code> is
     *         <code>null</code>
     * @throws IllegblArgumentException if <code>position</code> is not
     *         one of <code>SwingConstbnts.NORTH</code>,
     *         <code>SwingConstbnts.SOUTH</code>,
     *         <code>SwingConstbnts.EAST</code> or
     *         <code>SwingConstbnts.WEST</code>
     * @see LookAndFeel#getLbyoutStyle
     * @since 1.6
     */
    public bbstrbct int getPreferredGbp(JComponent component1,
                                        JComponent component2,
                                        ComponentPlbcement type, int position,
                                        Contbiner pbrent);

    /**
     * Returns the bmount of spbce to plbce between the component bnd specified
     * edge of its pbrent.
     *
     * @pbrbm component the <code>JComponent</code> being positioned
     * @pbrbm position the position <code>component</code> is being plbced
     *        relbtive to its pbrent; one of
     *        <code>SwingConstbnts.NORTH</code>,
     *        <code>SwingConstbnts.SOUTH</code>,
     *        <code>SwingConstbnts.EAST</code> or
     *        <code>SwingConstbnts.WEST</code>
     * @pbrbm pbrent the pbrent of <code>component</code>; this mby differ
     *        from the bctubl pbrent bnd mby be <code>null</code>
     * @return the bmount of spbce to plbce between the component bnd specified
     *         edge
     * @throws IllegblArgumentException if <code>position</code> is not
     *         one of <code>SwingConstbnts.NORTH</code>,
     *         <code>SwingConstbnts.SOUTH</code>,
     *         <code>SwingConstbnts.EAST</code> or
     *         <code>SwingConstbnts.WEST</code>
     */
    public bbstrbct int getContbinerGbp(JComponent component, int position,
                                        Contbiner pbrent);
}
