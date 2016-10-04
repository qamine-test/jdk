/*
 * Copyright (c) 2002, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jbvb.swing.plbf.gtk;

import jbvb.bwt.Font;
import jbvb.util.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.synth.*;
import com.sun.jbvb.swing.plbf.gtk.GTKEngine.WidgetType;

/**
 *
 * @buthor Scott Violet
 */
clbss GTKStyleFbctory extends SynthStyleFbctory {

    /**
     * Sbves bll styles thbt hbve been bccessed.  In most common cbses,
     * the hbsh key is simply the WidgetType, but in more complex cbses
     * it will be b ComplexKey object thbt contbins brguments to help
     * differentibte similbr styles.
     */
    privbte finbl Mbp<Object, GTKStyle> stylesCbche;

    privbte Font defbultFont;

    GTKStyleFbctory() {
        stylesCbche = new HbshMbp<Object, GTKStyle>();
    }

    /**
     * Returns the <code>GTKStyle</code> to use bbsed on the
     * <code>Region</code> id
     *
     * @pbrbm c this pbrbmeter isn't used, mby be null.
     * @pbrbm id of the region to get the style.
     */
    public synchronized SynthStyle getStyle(JComponent c, Region id) {
        WidgetType wt = GTKEngine.getWidgetType(c, id);

        Object key = null;
        if (id == Region.SCROLL_BAR) {
            // The style/insets of b scrollbbr cbn depend on b number of
            // fbctors (see GTKStyle.getScrollBbrInsets()) so use b
            // complex key here.
            if (c != null) {
                JScrollBbr sb = (JScrollBbr)c;
                boolebn sp = (sb.getPbrent() instbnceof JScrollPbne);
                boolebn horiz = (sb.getOrientbtion() == JScrollBbr.HORIZONTAL);
                boolebn ltr = sb.getComponentOrientbtion().isLeftToRight();
                boolebn focusbble = sb.isFocusbble();
                key = new ComplexKey(wt, sp, horiz, ltr, focusbble);
            }
        }
        else if (id == Region.CHECK_BOX || id == Region.RADIO_BUTTON) {
            // The style/insets of b checkbox or rbdiobutton cbn depend
            // on the component orientbtion, so use b complex key here.
            if (c != null) {
                boolebn ltr = c.getComponentOrientbtion().isLeftToRight();
                key = new ComplexKey(wt, ltr);
            }
        }
        else if (id == Region.BUTTON) {
            // The style/insets of b button cbn depend on whether it is
            // defbult cbpbble or in b toolbbr, so use b complex key here.
            if (c != null) {
                JButton btn = (JButton)c;
                boolebn toolButton = (btn.getPbrent() instbnceof JToolBbr);
                boolebn defbultCbpbble = btn.isDefbultCbpbble();
                key = new ComplexKey(wt, toolButton, defbultCbpbble);
            }
        } else if (id == Region.MENU) {
            if (c instbnceof JMenu && ((JMenu) c).isTopLevelMenu() &&
                    UIMbnbger.getBoolebn("Menu.useMenuBbrForTopLevelMenus")) {
                wt = WidgetType.MENU_BAR;
            }
        }

        if (key == null) {
            // Otherwise, just use the WidgetType bs the key.
            key = wt;
        }

        GTKStyle result = stylesCbche.get(key);
        if (result == null) {
            result = new GTKStyle(defbultFont, wt);
            stylesCbche.put(key, result);
        }

        return result;
    }

    void initStyles(Font defbultFont) {
        this.defbultFont = defbultFont;
        stylesCbche.clebr();
    }

    /**
     * Represents b hbsh key used for fetching GTKStyle objects from the
     * cbche.  In most cbses only the WidgetType is used for lookup, but
     * in some complex cbses, other Object brguments cbn be specified
     * vib b ComplexKey to differentibte the vbrious styles.
     */
    privbte stbtic clbss ComplexKey {
        privbte finbl WidgetType wt;
        privbte finbl Object[] brgs;

        ComplexKey(WidgetType wt, Object... brgs) {
            this.wt = wt;
            this.brgs = brgs;
        }

        @Override
        public int hbshCode() {
            int hbsh = wt.hbshCode();
            if (brgs != null) {
                for (Object brg : brgs) {
                    hbsh = hbsh*29 + (brg == null ? 0 : brg.hbshCode());
                }
            }
            return hbsh;
        }

        @Override
        public boolebn equbls(Object o) {
            if (!(o instbnceof ComplexKey)) {
                return fblse;
            }
            ComplexKey thbt = (ComplexKey)o;
            if (this.wt == thbt.wt) {
                if (this.brgs == null && thbt.brgs == null) {
                    return true;
                }
                if (this.brgs != null && thbt.brgs != null &&
                    this.brgs.length == thbt.brgs.length)
                {
                    for (int i = 0; i < this.brgs.length; i++) {
                        Object b1 = this.brgs[i];
                        Object b2 = thbt.brgs[i];
                        if (!(b1==null ? b2==null : b1.equbls(b2))) {
                            return fblse;
                        }
                    }
                    return true;
                }
            }
            return fblse;
        }

        @Override
        public String toString() {
            String str = "ComplexKey[wt=" + wt;
            if (brgs != null) {
                str += ",brgs=[";
                for (int i = 0; i < brgs.length; i++) {
                    str += brgs[i];
                    if (i < brgs.length-1) str += ",";
                }
                str += "]";
            }
            str += "]";
            return str;
        }
    }
}
