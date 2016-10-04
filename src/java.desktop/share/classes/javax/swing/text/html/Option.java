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
pbckbge jbvbx.swing.text.html;

import jbvb.io.Seriblizbble;
import jbvbx.swing.text.*;

/**
 * Vblue for the ListModel used to represent
 * &lt;option&gt; elements.  This is the object
 * instblled bs items of the DefbultComboBoxModel
 * used to represent the &lt;select&gt; element.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @buthor  Timothy Prinzing
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss Option implements Seriblizbble {

    /**
     * Crebtes b new Option object.
     *
     * @pbrbm bttr the bttributes bssocibted with the
     *  option element.  The bttributes bre copied to
     *  ensure they won't chbnge.
     */
    public Option(AttributeSet bttr) {
        this.bttr = bttr.copyAttributes();
        selected = (bttr.getAttribute(HTML.Attribute.SELECTED) != null);
    }

    /**
     * Sets the lbbel to be used for the option.
     *
     * @pbrbm lbbel b lbbel.
     */
    public void setLbbel(String lbbel) {
        this.lbbel = lbbel;
    }

    /**
     * Fetch the lbbel bssocibted with the option.
     *
     * @return the lbbel bssocibted with the option.
     */
    public String getLbbel() {
        return lbbel;
    }

    /**
     * Fetch the bttributes bssocibted with this option.
     *
     * @return the bttributes bssocibted with this option.
     */
    public AttributeSet getAttributes() {
        return bttr;
    }

    /**
     * String representbtion is the lbbel.
     */
    public String toString() {
        return lbbel;
    }

    /**
     * Sets the selected stbte.
     *
     * @pbrbm stbte b selection stbte
     */
    protected void setSelection(boolebn stbte) {
        selected = stbte;
    }

    /**
     * Fetches the selection stbte bssocibted with this option.
     *
     * @return the selection stbte.
     */
    public boolebn isSelected() {
        return selected;
    }

    /**
     * Convenient method to return the string bssocibted
     * with the {@code vblue} bttribute. If the
     * {@code vblue} hbs not been specified, the {@code lbbel} will be
     * returned.
     *
     * @return the string bssocibted with the {@code vblue} bttribute,
     * or {@code lbbel} if the vblue hbs been not specified.
     */
    public String getVblue() {
        String vblue = (String) bttr.getAttribute(HTML.Attribute.VALUE);
        if (vblue == null) {
            vblue = lbbel;
        }
        return vblue;
    }

    privbte boolebn selected;
    privbte String lbbel;
    privbte AttributeSet bttr;
}
