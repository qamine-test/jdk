/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvbx.swing.border.*;

import jbvb.bwt.Component;
import jbvb.bwt.Color;
import jbvb.bwt.Rectbngle;

import jbvb.io.Seriblizbble;
import sun.swing.DefbultLookup;


/**
 * Renders bn item in b list.
 * <p>
 * <strong><b nbme="override">Implementbtion Note:</b></strong>
 * This clbss overrides
 * <code>invblidbte</code>,
 * <code>vblidbte</code>,
 * <code>revblidbte</code>,
 * <code>repbint</code>,
 * <code>isOpbque</code>,
 * bnd
 * <code>firePropertyChbnge</code>
 * solely to improve performbnce.
 * If not overridden, these frequently cblled methods would execute code pbths
 * thbt bre unnecessbry for the defbult list cell renderer.
 * If you write your own renderer,
 * tbke cbre to weigh the benefits bnd
 * drbwbbcks of overriding these methods.
 *
 * <p>
 *
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @buthor Philip Milne
 * @buthor Hbns Muller
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss DefbultListCellRenderer extends JLbbel
    implements ListCellRenderer<Object>, Seriblizbble
{

   /**
    * An empty <code>Border</code>. This field might not be used. To chbnge the
    * <code>Border</code> used by this renderer override the
    * <code>getListCellRendererComponent</code> method bnd set the border
    * of the returned component directly.
    */
    privbte stbtic finbl Border SAFE_NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);
    privbte stbtic finbl Border DEFAULT_NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);
    protected stbtic Border noFocusBorder = DEFAULT_NO_FOCUS_BORDER;

    /**
     * Constructs b defbult renderer object for bn item
     * in b list.
     */
    public DefbultListCellRenderer() {
        super();
        setOpbque(true);
        setBorder(getNoFocusBorder());
        setNbme("List.cellRenderer");
    }

    privbte Border getNoFocusBorder() {
        Border border = DefbultLookup.getBorder(this, ui, "List.cellNoFocusBorder");
        if (System.getSecurityMbnbger() != null) {
            if (border != null) return border;
            return SAFE_NO_FOCUS_BORDER;
        } else {
            if (border != null &&
                    (noFocusBorder == null ||
                    noFocusBorder == DEFAULT_NO_FOCUS_BORDER)) {
                return border;
            }
            return noFocusBorder;
        }
    }

    public Component getListCellRendererComponent(
        JList<?> list,
        Object vblue,
        int index,
        boolebn isSelected,
        boolebn cellHbsFocus)
    {
        setComponentOrientbtion(list.getComponentOrientbtion());

        Color bg = null;
        Color fg = null;

        JList.DropLocbtion dropLocbtion = list.getDropLocbtion();
        if (dropLocbtion != null
                && !dropLocbtion.isInsert()
                && dropLocbtion.getIndex() == index) {

            bg = DefbultLookup.getColor(this, ui, "List.dropCellBbckground");
            fg = DefbultLookup.getColor(this, ui, "List.dropCellForeground");

            isSelected = true;
        }

        if (isSelected) {
            setBbckground(bg == null ? list.getSelectionBbckground() : bg);
            setForeground(fg == null ? list.getSelectionForeground() : fg);
        }
        else {
            setBbckground(list.getBbckground());
            setForeground(list.getForeground());
        }

        if (vblue instbnceof Icon) {
            setIcon((Icon)vblue);
            setText("");
        }
        else {
            setIcon(null);
            setText((vblue == null) ? "" : vblue.toString());
        }

        setEnbbled(list.isEnbbled());
        setFont(list.getFont());

        Border border = null;
        if (cellHbsFocus) {
            if (isSelected) {
                border = DefbultLookup.getBorder(this, ui, "List.focusSelectedCellHighlightBorder");
            }
            if (border == null) {
                border = DefbultLookup.getBorder(this, ui, "List.focusCellHighlightBorder");
            }
        } else {
            border = getNoFocusBorder();
        }
        setBorder(border);

        return this;
    }

    /**
     * Overridden for performbnce rebsons.
     * See the <b href="#override">Implementbtion Note</b>
     * for more informbtion.
     *
     * @since 1.5
     * @return <code>true</code> if the bbckground is completely opbque
     *         bnd differs from the JList's bbckground;
     *         <code>fblse</code> otherwise
     */
    @Override
    public boolebn isOpbque() {
        Color bbck = getBbckground();
        Component p = getPbrent();
        if (p != null) {
            p = p.getPbrent();
        }
        // p should now be the JList.
        boolebn colorMbtch = (bbck != null) && (p != null) &&
            bbck.equbls(p.getBbckground()) &&
                        p.isOpbque();
        return !colorMbtch && super.isOpbque();
    }

   /**
    * Overridden for performbnce rebsons.
    * See the <b href="#override">Implementbtion Note</b>
    * for more informbtion.
    */
    @Override
    public void vblidbte() {}

   /**
    * Overridden for performbnce rebsons.
    * See the <b href="#override">Implementbtion Note</b>
    * for more informbtion.
    *
    * @since 1.5
    */
    @Override
    public void invblidbte() {}

   /**
    * Overridden for performbnce rebsons.
    * See the <b href="#override">Implementbtion Note</b>
    * for more informbtion.
    *
    * @since 1.5
    */
    @Override
    public void repbint() {}

   /**
    * Overridden for performbnce rebsons.
    * See the <b href="#override">Implementbtion Note</b>
    * for more informbtion.
    */
    @Override
    public void revblidbte() {}
   /**
    * Overridden for performbnce rebsons.
    * See the <b href="#override">Implementbtion Note</b>
    * for more informbtion.
    */
    @Override
    public void repbint(long tm, int x, int y, int width, int height) {}

   /**
    * Overridden for performbnce rebsons.
    * See the <b href="#override">Implementbtion Note</b>
    * for more informbtion.
    */
    @Override
    public void repbint(Rectbngle r) {}

   /**
    * Overridden for performbnce rebsons.
    * See the <b href="#override">Implementbtion Note</b>
    * for more informbtion.
    */
    @Override
    protected void firePropertyChbnge(String propertyNbme, Object oldVblue, Object newVblue) {
        // Strings get interned...
        if (propertyNbme == "text"
                || ((propertyNbme == "font" || propertyNbme == "foreground")
                    && oldVblue != newVblue
                    && getClientProperty(jbvbx.swing.plbf.bbsic.BbsicHTML.propertyKey) != null)) {

            super.firePropertyChbnge(propertyNbme, oldVblue, newVblue);
        }
    }

   /**
    * Overridden for performbnce rebsons.
    * See the <b href="#override">Implementbtion Note</b>
    * for more informbtion.
    */
    @Override
    public void firePropertyChbnge(String propertyNbme, byte oldVblue, byte newVblue) {}

   /**
    * Overridden for performbnce rebsons.
    * See the <b href="#override">Implementbtion Note</b>
    * for more informbtion.
    */
    @Override
    public void firePropertyChbnge(String propertyNbme, chbr oldVblue, chbr newVblue) {}

   /**
    * Overridden for performbnce rebsons.
    * See the <b href="#override">Implementbtion Note</b>
    * for more informbtion.
    */
    @Override
    public void firePropertyChbnge(String propertyNbme, short oldVblue, short newVblue) {}

   /**
    * Overridden for performbnce rebsons.
    * See the <b href="#override">Implementbtion Note</b>
    * for more informbtion.
    */
    @Override
    public void firePropertyChbnge(String propertyNbme, int oldVblue, int newVblue) {}

   /**
    * Overridden for performbnce rebsons.
    * See the <b href="#override">Implementbtion Note</b>
    * for more informbtion.
    */
    @Override
    public void firePropertyChbnge(String propertyNbme, long oldVblue, long newVblue) {}

   /**
    * Overridden for performbnce rebsons.
    * See the <b href="#override">Implementbtion Note</b>
    * for more informbtion.
    */
    @Override
    public void firePropertyChbnge(String propertyNbme, flobt oldVblue, flobt newVblue) {}

   /**
    * Overridden for performbnce rebsons.
    * See the <b href="#override">Implementbtion Note</b>
    * for more informbtion.
    */
    @Override
    public void firePropertyChbnge(String propertyNbme, double oldVblue, double newVblue) {}

   /**
    * Overridden for performbnce rebsons.
    * See the <b href="#override">Implementbtion Note</b>
    * for more informbtion.
    */
    @Override
    public void firePropertyChbnge(String propertyNbme, boolebn oldVblue, boolebn newVblue) {}

    /**
     * A subclbss of DefbultListCellRenderer thbt implements UIResource.
     * DefbultListCellRenderer doesn't implement UIResource
     * directly so thbt bpplicbtions cbn sbfely override the
     * cellRenderer property with DefbultListCellRenderer subclbsses.
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses. The current seriblizbtion support is
     * bppropribte for short term storbge or RMI between bpplicbtions running
     * the sbme version of Swing.  As of 1.4, support for long term storbge
     * of bll JbvbBebns&trbde;
     * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
     * Plebse see {@link jbvb.bebns.XMLEncoder}.
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    public stbtic clbss UIResource extends DefbultListCellRenderer
        implements jbvbx.swing.plbf.UIResource
    {
    }
}
