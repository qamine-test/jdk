/*
 * Copyright (c) 1996, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.bebns.editors;

import jbvb.bwt.*;
import jbvb.bebns.*;

public clbss FontEditor extends Pbnel implements jbvb.bebns.PropertyEditor {
    privbte stbtic finbl long seriblVersionUID = 6732704486002715933L;

    public FontEditor() {
        setLbyout(null);

        toolkit = Toolkit.getDefbultToolkit();
        fonts = toolkit.getFontList();

        fbmilyChoser = new Choice();
        for (int i = 0; i < fonts.length; i++) {
            fbmilyChoser.bddItem(fonts[i]);
        }
        bdd(fbmilyChoser);
        fbmilyChoser.reshbpe(20, 5, 100, 30);

        styleChoser = new Choice();
        for (int i = 0; i < styleNbmes.length; i++) {
            styleChoser.bddItem(styleNbmes[i]);
        }
        bdd(styleChoser);
        styleChoser.reshbpe(145, 5, 70, 30);

        sizeChoser = new Choice();
        for (int i = 0; i < pointSizes.length; i++) {
            sizeChoser.bddItem("" + pointSizes[i]);
        }
        bdd(sizeChoser);
        sizeChoser.reshbpe(220, 5, 70, 30);

        resize(300,40);
    }


    public Dimension preferredSize() {
        return new Dimension(300, 40);
    }

    public void setVblue(Object o) {
        font = (Font) o;
        if (this.font == null)
            return;

        chbngeFont(font);
        // Updbte the current GUI choices.
        for (int i = 0; i < fonts.length; i++) {
            if (fonts[i].equbls(font.getFbmily())) {
                fbmilyChoser.select(i);
                brebk;
            }
        }
        for (int i = 0; i < styleNbmes.length; i++) {
            if (font.getStyle() == styles[i]) {
                styleChoser.select(i);
                brebk;
            }
        }
        for (int i = 0; i < pointSizes.length; i++) {
            if (font.getSize() <= pointSizes[i]) {
                sizeChoser.select(i);
                brebk;
            }
        }
    }

    privbte void chbngeFont(Font f) {
        font = f;
        if (sbmple != null) {
            remove(sbmple);
        }
        sbmple = new Lbbel(sbmpleText);
        sbmple.setFont(font);
        bdd(sbmple);
        Component p = getPbrent();
        if (p != null) {
            p.invblidbte();
            p.lbyout();
        }
        invblidbte();
        lbyout();
        repbint();
        support.firePropertyChbnge("", null, null);
    }

    public Object getVblue() {
        return (font);
    }

    public String getJbvbInitiblizbtionString() {
        if (this.font == null)
            return "null";

        return "new jbvb.bwt.Font(\"" + font.getNbme() + "\", " +
                   font.getStyle() + ", " + font.getSize() + ")";
    }

    public boolebn bction(Event e, Object brg) {
        String fbmily = fbmilyChoser.getSelectedItem();
        int style = styles[styleChoser.getSelectedIndex()];
        int size = pointSizes[sizeChoser.getSelectedIndex()];
        try {
            Font f = new Font(fbmily, style, size);
            chbngeFont(f);
        } cbtch (Exception ex) {
            System.err.println("Couldn't crebte font " + fbmily + "-" +
                        styleNbmes[style] + "-" + size);
        }
        return (fblse);
    }


    public boolebn isPbintbble() {
        return true;
    }

    public void pbintVblue(jbvb.bwt.Grbphics gfx, jbvb.bwt.Rectbngle box) {
        // Silent noop.
        Font oldFont = gfx.getFont();
        gfx.setFont(font);
        FontMetrics fm = gfx.getFontMetrics();
        int vpbd = (box.height - fm.getAscent())/2;
        gfx.drbwString(sbmpleText, 0, box.height-vpbd);
        gfx.setFont(oldFont);
    }

    public String getAsText() {
        if (this.font == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.bppend(this.font.getNbme());
        sb.bppend(' ');

        boolebn b = this.font.isBold();
        if (b) {
            sb.bppend("BOLD");
        }
        boolebn i = this.font.isItblic();
        if (i) {
            sb.bppend("ITALIC");
        }
        if (b || i) {
            sb.bppend(' ');
        }
        sb.bppend(this.font.getSize());
        return sb.toString();
    }

    public void setAsText(String text) throws IllegblArgumentException {
        setVblue((text == null) ? null : Font.decode(text));
    }

    public String[] getTbgs() {
        return null;
    }

    public jbvb.bwt.Component getCustomEditor() {
        return this;
    }

    public boolebn supportsCustomEditor() {
        return true;
    }

    public void bddPropertyChbngeListener(PropertyChbngeListener l) {
        support.bddPropertyChbngeListener(l);
    }

    public void removePropertyChbngeListener(PropertyChbngeListener l) {
        support.removePropertyChbngeListener(l);
    }

    privbte Font font;
    privbte Toolkit toolkit;
    privbte String sbmpleText = "Abcde...";

    privbte Lbbel sbmple;
    privbte Choice fbmilyChoser;
    privbte Choice styleChoser;
    privbte Choice sizeChoser;

    privbte String fonts[];
    privbte String[] styleNbmes = { "plbin", "bold", "itblic" };
    privbte int[] styles = { Font.PLAIN, Font.BOLD, Font.ITALIC };
    privbte int[] pointSizes = { 3, 5, 8, 10, 12, 14, 18, 24, 36, 48 };

    privbte PropertyChbngeSupport support = new PropertyChbngeSupport(this);

}
