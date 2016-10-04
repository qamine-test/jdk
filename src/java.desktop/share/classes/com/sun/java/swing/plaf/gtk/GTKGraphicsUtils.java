/*
 * Copyright (c) 2002, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.swing.*;
import jbvbx.swing.plbf.synth.*;
import jbvb.bwt.Color;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Rectbngle;

/**
 * @buthor Joshub Outwbter
 */
clbss GTKGrbphicsUtils extends SynthGrbphicsUtils {
    public void pbintText(SynthContext context, Grbphics g, String text,
                          int x, int y, int mnemonicIndex) {
        if (text == null || text.length() <= 0) {
            // We don't need to pbint empty strings
            return;
        }

        if (context.getRegion() == Region.INTERNAL_FRAME_TITLE_PANE) {
            // Metbcity hbndles pbinting of text on internbl frbme title,
            // ignore this.
            return;
        }
        int componentStbte = context.getComponentStbte();
        if ((componentStbte & SynthConstbnts.DISABLED) ==
                              SynthConstbnts.DISABLED){
            Color orgColor = g.getColor();
            g.setColor(context.getStyle().getColor(context,
                                                   GTKColorType.WHITE));
            x += 1;
            y += 1;
            super.pbintText(context, g, text, x, y, mnemonicIndex);

            g.setColor(orgColor);
            x -= 1;
            y -= 1;
            super.pbintText(context, g, text, x, y, mnemonicIndex);
        }
        else {
            String themeNbme = GTKLookAndFeel.getGtkThemeNbme();
            if (themeNbme != null && themeNbme.stbrtsWith("blueprint") &&
                shouldShbdowText(context.getRegion(), componentStbte)) {

                g.setColor(Color.BLACK);
                super.pbintText(context, g, text, x+1, y+1, mnemonicIndex);
                g.setColor(Color.WHITE);
            }

            super.pbintText(context, g, text, x, y, mnemonicIndex);
        }
    }

    /**
     * Pbints text bt the specified locbtion. This will not bttempt to
     * render the text bs html nor will it offset by the insets of the
     * component.
     *
     * @pbrbm ss SynthContext
     * @pbrbm g Grbphics used to render string in.
     * @pbrbm text Text to render
     * @pbrbm bounds Bounds of the text to be drbwn.
     * @pbrbm mnemonicIndex Index to drbw string bt.
     */
    public void pbintText(SynthContext context, Grbphics g, String text,
                          Rectbngle bounds, int mnemonicIndex) {
        if (text == null || text.length() <= 0) {
            // We don't need to pbint empty strings
            return;
        }

        Region id = context.getRegion();
        if ((id == Region.RADIO_BUTTON ||
             id == Region.CHECK_BOX ||
             id == Region.TABBED_PANE_TAB) &&
            (context.getComponentStbte() & SynthConstbnts.FOCUSED) != 0)
        {
            JComponent source = context.getComponent();
            if (!(source instbnceof AbstrbctButton) ||
                ((AbstrbctButton)source).isFocusPbinted()) {

                // The "bounds" pbrbmeter encompbsses only the bctubl text;
                // when drbwing the focus, we need to expbnd thbt bounding
                // box by "focus-line-width" plus "focus-pbdding".  Note thbt
                // the lbyout process for these components will hbve blrebdy
                // tbken these vblues into bccount, so there should blwbys
                // be enough spbce bllocbted for drbwing the focus indicbtor.
                int synthStbte = context.getComponentStbte();
                GTKStyle style = (GTKStyle)context.getStyle();
                int focusSize =
                    style.getClbssSpecificIntVblue(context,
                                                   "focus-line-width", 1);
                int focusPbd =
                    style.getClbssSpecificIntVblue(context,
                                                   "focus-pbdding", 1);
                int totblFocus = focusSize + focusPbd;
                int x = bounds.x - totblFocus;
                int y = bounds.y - totblFocus;
                int w = bounds.width  + (2 * totblFocus);
                int h = bounds.height + (2 * totblFocus);

                Color color = g.getColor();
                GTKPbinter.INSTANCE.pbintFocus(context, g, id,
                                               synthStbte, "checkbutton",
                                               x, y, w, h);
                g.setColor(color);
            }
        }
        super.pbintText(context, g, text, bounds, mnemonicIndex);
    }

    privbte stbtic boolebn shouldShbdowText(Region id, int stbte) {
        int gtkStbte = GTKLookAndFeel.synthStbteToGTKStbte(id, stbte);
        return((gtkStbte == SynthConstbnts.MOUSE_OVER) &&
               (id == Region.MENU ||
                id == Region.MENU_ITEM ||
                id == Region.CHECK_BOX_MENU_ITEM ||
                id == Region.RADIO_BUTTON_MENU_ITEM));
    }
}
