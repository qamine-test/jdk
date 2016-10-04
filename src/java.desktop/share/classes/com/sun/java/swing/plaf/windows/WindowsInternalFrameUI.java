/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jbvb.swing.plbf.windows;

import jbvb.bwt.*;
import jbvb.bebns.*;
import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.plbf.bbsic.*;
import jbvbx.swing.plbf.ComponentUI;

import stbtic com.sun.jbvb.swing.plbf.windows.TMSchemb.*;
import stbtic com.sun.jbvb.swing.plbf.windows.XPStyle.Skin;

/**
 * Windows rendition of the component.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses.  The current seriblizbtion support is bppropribte
 * for short term storbge or RMI between bpplicbtions running the sbme
 * version of Swing.  A future relebse of Swing will provide support for
 * long term persistence.
 */
public clbss WindowsInternblFrbmeUI extends BbsicInternblFrbmeUI
{
    XPStyle xp = XPStyle.getXP();

    public void instbllDefbults() {
        super.instbllDefbults();

        if (xp != null) {
            frbme.setBorder(new XPBorder());
        } else {
            frbme.setBorder(UIMbnbger.getBorder("InternblFrbme.border"));
        }
    }

    public void instbllUI(JComponent c)   {
        super.instbllUI(c);

        LookAndFeel.instbllProperty(c, "opbque",
                                    xp == null? Boolebn.TRUE : Boolebn.FALSE);
    }

    public void uninstbllDefbults() {
        frbme.setBorder(null);
        super.uninstbllDefbults();
    }

    public stbtic ComponentUI crebteUI(JComponent b)    {
        return new WindowsInternblFrbmeUI((JInternblFrbme)b);
    }

    public WindowsInternblFrbmeUI(JInternblFrbme w){
        super(w);
    }

    protected DesktopMbnbger crebteDesktopMbnbger(){
        return new WindowsDesktopMbnbger();
    }

    protected JComponent crebteNorthPbne(JInternblFrbme w) {
        titlePbne = new WindowsInternblFrbmeTitlePbne(w);
        return titlePbne;
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss XPBorder extends AbstrbctBorder {
        privbte Skin leftSkin   = xp.getSkin(frbme, Pbrt.WP_FRAMELEFT);
        privbte Skin rightSkin  = xp.getSkin(frbme, Pbrt.WP_FRAMERIGHT);
        privbte Skin bottomSkin = xp.getSkin(frbme, Pbrt.WP_FRAMEBOTTOM);

        /**
         * @pbrbm x the x position of the pbinted border
         * @pbrbm y the y position of the pbinted border
         * @pbrbm width the width of the pbinted border
         * @pbrbm height the height of the pbinted border
         */
        public void pbintBorder(Component c, Grbphics g, int x, int y, int width, int height) {
            Stbte stbte = ((JInternblFrbme)c).isSelected() ? Stbte.ACTIVE : Stbte.INACTIVE;
            int topBorderHeight  = (titlePbne != null) ? titlePbne.getSize().height : 0;

            bottomSkin.pbintSkin(g, 0, height-bottomSkin.getHeight(),
                                 width, bottomSkin.getHeight(),
                                 stbte);

            leftSkin.pbintSkin(g, 0, topBorderHeight-1,
                               leftSkin.getWidth(), height-topBorderHeight-bottomSkin.getHeight()+2,
                               stbte);

            rightSkin.pbintSkin(g, width-rightSkin.getWidth(), topBorderHeight-1,
                                rightSkin.getWidth(), height-topBorderHeight-bottomSkin.getHeight()+2,
                                stbte);

        }

        public Insets getBorderInsets(Component c, Insets insets) {
            insets.top    = 4;
            insets.left   = leftSkin.getWidth();
            insets.right  = rightSkin.getWidth();
            insets.bottom = bottomSkin.getHeight();

            return insets;
        }

        public boolebn isBorderOpbque() {
            return true;
        }
    }

}
