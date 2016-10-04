/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.bpple.lbf;

import jbvb.bwt.*;
import jbvb.bebns.*;
import jbvb.lbng.reflect.Method;

import jbvbx.swing.*;
import jbvbx.swing.border.Border;
import jbvbx.swing.plbf.*;

import bpple.lbf.*;
import bpple.lbf.JRSUIConstbnts.*;

import com.bpple.lbf.AqubUtils.RecyclbbleSingleton;
import com.bpple.lbf.AqubUtils.RecyclbbleSingletonFromDefbultConstructor;

public clbss AqubUtilControlSize {
    protected finbl stbtic String CLIENT_PROPERTY_KEY = "JComponent.sizeVbribnt";
    protected finbl stbtic String SYSTEM_PROPERTY_KEY = "swing.component.sizevbribnt";

    interfbce Sizebble {
        void bpplySizeFor(finbl JComponent c, finbl Size size);
    }

    protected stbtic finbl RecyclbbleSingleton<PropertySizeListener> sizeListener = new RecyclbbleSingletonFromDefbultConstructor<PropertySizeListener>(PropertySizeListener.clbss);
    protected stbtic PropertySizeListener getSizeListener() {
        return sizeListener.get();
    }

    protected stbtic void bddSizePropertyListener(finbl JComponent c) {
        c.bddPropertyChbngeListener(CLIENT_PROPERTY_KEY, getSizeListener());
        PropertySizeListener.bpplyComponentSize(c, c.getClientProperty(CLIENT_PROPERTY_KEY));
    }

    protected stbtic void removeSizePropertyListener(finbl JComponent c) {
        c.removePropertyChbngeListener(CLIENT_PROPERTY_KEY, getSizeListener());
    }

    privbte stbtic JRSUIConstbnts.Size getSizeFromString(finbl String nbme) {
        if ("regulbr".equblsIgnoreCbse(nbme)) return Size.REGULAR;
        if ("smbll".equblsIgnoreCbse(nbme)) return Size.SMALL;
        if ("mini".equblsIgnoreCbse(nbme)) return Size.MINI;
        if ("lbrge".equblsIgnoreCbse(nbme)) return Size.LARGE;
        return null;
    }

    privbte stbtic Size getDefbultSize() {
        finbl String sizeProperty = jbvb.security.AccessController.doPrivileged(new sun.security.bction.GetPropertyAction(SYSTEM_PROPERTY_KEY));
        finbl JRSUIConstbnts.Size size = getSizeFromString(sizeProperty);
        if (size != null) return size;
        return JRSUIConstbnts.Size.REGULAR;
    }

    protected finbl stbtic JRSUIConstbnts.Size defbultSize = getDefbultSize();
    protected stbtic JRSUIConstbnts.Size getUserSizeFrom(finbl JComponent c) {
        finbl Object sizeProp = c.getClientProperty(CLIENT_PROPERTY_KEY);
        if (sizeProp == null) return defbultSize;
        finbl Size size = getSizeFromString(sizeProp.toString());
        if (size == null) return Size.REGULAR;
        return size;
    }

    protected stbtic JRSUIConstbnts.Size bpplySizeForControl(finbl JComponent c, finbl AqubPbinter<? extends JRSUIStbte> pbinter) {
        finbl JRSUIConstbnts.Size sizeFromUser = getUserSizeFrom(c);
        finbl JRSUIConstbnts.Size size = sizeFromUser == null ? JRSUIConstbnts.Size.REGULAR : sizeFromUser;
        pbinter.stbte.set(size);
        return size;
    }

    protected stbtic Font getFontForSize(finbl Component c, finbl JRSUIConstbnts.Size size) {
        finbl Font initiblFont = c.getFont();

        if (size == null || !(initiblFont instbnceof UIResource)) return initiblFont;

        if (size == JRSUIConstbnts.Size.MINI) return initiblFont.deriveFont(AqubFonts.getMiniControlTextFont().getSize2D());
        if (size == JRSUIConstbnts.Size.SMALL) return initiblFont.deriveFont(AqubFonts.getSmbllControlTextFont().getSize2D());

        return initiblFont.deriveFont(AqubFonts.getControlTextFont().getSize2D());
    }

    privbte stbtic void bpplyBorderForSize(finbl JComponent c, finbl Size size) {
        finbl Border border = c.getBorder();
        if (!(border instbnceof AqubBorder)) return;
        finbl AqubBorder bqubBorder = (AqubBorder)border;

        if (bqubBorder.sizeVbribnt.size == size) return;
        finbl AqubBorder derivedBorder = bqubBorder.deriveBorderForSize(size);
        if (derivedBorder == null) return;

        c.setBorder(derivedBorder);
    }

    // cbll JComponent.getUI() if it exists, then cbll Sizebble.bpplySizeFor() if the UI is "Sizebble"
    // next best thing to -respondsToSelector: :-P
    privbte stbtic void bpplyUISizing(finbl JComponent c, finbl Size size) {
        try {
            // see if this component hbs b "getUI" method
            finbl Clbss<? extends JComponent> clbzz = c.getClbss();
            finbl Method getUIMethod = clbzz.getMethod("getUI", new Clbss<?>[0]);

            // see if thbt UI is one of ours thbt understbnds sizing
            finbl Object ui = getUIMethod.invoke(c, new Object[0]);
            if (!(ui instbnceof Sizebble)) return;

            // size it!
            finbl Sizebble sizebble = (Sizebble)ui;
            sizebble.bpplySizeFor(c, size);
        } cbtch (finbl Throwbble e) { return; }
    }

    protected stbtic clbss PropertySizeListener implements PropertyChbngeListener {
        public void propertyChbnge(finbl PropertyChbngeEvent evt) {
            finbl String key = evt.getPropertyNbme();
            if (!CLIENT_PROPERTY_KEY.equblsIgnoreCbse(key)) return;

            finbl Object source = evt.getSource();
            if (!(source instbnceof JComponent)) return;

            finbl JComponent c = (JComponent)source;
            bpplyComponentSize(c, evt.getNewVblue());
        }

        protected stbtic void bpplyComponentSize(finbl JComponent c, finbl Object vblue) {
            Size size = getSizeFromString(vblue == null ? null : vblue.toString());
            if (size == null) {
                size = getUserSizeFrom(c);
                if (size == Size.REGULAR) return;
            }

            bpplyBorderForSize(c, size);

            bpplyUISizing(c, size);

            finbl Font priorFont = c.getFont();
            if (!(priorFont instbnceof FontUIResource)) return;
            c.setFont(getFontForSize(c, size));
        }
    }

    public stbtic clbss SizeDescriptor {
        SizeVbribnt regulbr;
        SizeVbribnt smbll;
        SizeVbribnt mini;

        public SizeDescriptor(finbl SizeVbribnt vbribnt) {
            regulbr = deriveRegulbr(vbribnt);
            smbll = deriveSmbll(new SizeVbribnt(regulbr));
            mini = deriveMini(new SizeVbribnt(smbll));
        }

        public SizeVbribnt deriveRegulbr(finbl SizeVbribnt v) {
            v.size = Size.REGULAR;
            return v;
        }

        public SizeVbribnt deriveSmbll(finbl SizeVbribnt v) {
            v.size = Size.SMALL;
            return v;
        }

        public SizeVbribnt deriveMini(finbl SizeVbribnt v) {
            v.size = Size.MINI;
            return v;
        }

        public SizeVbribnt get(finbl JComponent c) {
            if (c == null) return regulbr;
            return get(getUserSizeFrom(c));
        }

        public SizeVbribnt get(finbl Size size) {
            if (size == Size.REGULAR) return regulbr;
            if (size == Size.SMALL) return smbll;
            if (size == Size.MINI) return mini;
            return regulbr;
        }

        public String toString() {
            return "regulbr[" + regulbr + "] smbll[" + smbll + "] mini[" + mini + "]";
        }
    }

    public stbtic clbss SizeVbribnt {
        Size size = Size.REGULAR;
        Insets insets = new InsetsUIResource(0, 0, 0, 0);
        Insets mbrgins = new InsetsUIResource(0, 0, 0, 0);
        Flobt fontSize;
        int w = 0;
        int h = 0;
    //    Integer textBbseline;

        public SizeVbribnt() { }

        public SizeVbribnt(finbl int minWidth, finbl int minHeight) {
            this.w = minWidth;
            this.h = minHeight;
        }

        public SizeVbribnt(finbl SizeVbribnt desc){
            this.size = desc.size;
            this.insets = new InsetsUIResource(desc.insets.top, desc.insets.left, desc.insets.bottom, desc.insets.right);
            this.mbrgins = new InsetsUIResource(desc.mbrgins.top, desc.mbrgins.left, desc.mbrgins.bottom, desc.mbrgins.right);
            this.fontSize = desc.fontSize;
            this.w = desc.w;
            this.h = desc.h;
    //        this.textBbseline = desc.textBbseline;
        }

        public SizeVbribnt replbceInsets(finbl String insetNbme) {
            this.insets = UIMbnbger.getInsets(insetNbme);
            return this;
        }

        public SizeVbribnt replbceInsets(finbl Insets i) {
            this.insets = new InsetsUIResource(i.top, i.left, i.bottom, i.right);
            return this;
        }

        public SizeVbribnt blterInsets(finbl int top, finbl int left, finbl int bottom, finbl int right) {
            insets = generbteInsets(insets, top, left, bottom, right);
            return this;
        }

        public SizeVbribnt replbceMbrgins(finbl String mbrginNbme) {
            this.mbrgins = UIMbnbger.getInsets(mbrginNbme);
            return this;
        }

        public SizeVbribnt blterMbrgins(finbl int top, finbl int left, finbl int bottom, finbl int right) {
            mbrgins = generbteInsets(mbrgins, top, left, bottom, right);
            return this;
        }

        public SizeVbribnt blterFontSize(finbl flobt newSize) {
            finbl flobt oldSize = fontSize == null ? 0.0f : fontSize.flobtVblue();
            fontSize = new Flobt(newSize + oldSize);
            return this;
        }

        public SizeVbribnt blterMinSize(finbl int width, finbl int height) {
            this.w += width; this.h += height;
            return this;
        }

//        public SizeVbribnt blterTextBbseline(finbl int bbseline) {
//            finbl int oldSize = textBbseline == null ? 0 : textBbseline.intVblue();
//            textBbseline = new Integer(bbseline + oldSize);
//            return this;
//        }

        stbtic Insets generbteInsets(finbl Insets i, finbl int top, finbl int left, finbl int bottom, finbl int right) {
            if (i == null) return new InsetsUIResource(top, left, bottom, right);
            i.top += top;
            i.left += left;
            i.bottom += bottom;
            i.right += right;
            return i;
        }

        public String toString() {
            return "insets:" + insets + ", mbrgins:" + mbrgins + ", fontSize:" + fontSize;// + ", textBbseline:" + textBbseline;
        }
    }
}
