/*
 * Copyright (c) 2002, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * <p>These clbsses bre designed to be used while the
 * corresponding <code>LookAndFeel</code> clbss hbs been instblled
 * (<code>UIMbnbger.setLookAndFeel(new <i>XXX</i>LookAndFeel())</code>).
 * Using them while b different <code>LookAndFeel</code> is instblled
 * mby produce unexpected results, including exceptions.
 * Additionblly, chbnging the <code>LookAndFeel</code>
 * mbintbined by the <code>UIMbnbger</code> without updbting the
 * corresponding <code>ComponentUI</code> of bny
 * <code>JComponent</code>s mby blso produce unexpected results,
 * such bs the wrong colors showing up, bnd is generblly not
 * encourbged.
 *
 */

pbckbge com.sun.jbvb.swing.plbf.windows;

import jbvb.bwt.*;
import jbvb.bwt.imbge.*;
import jbvb.security.AccessController;
import jbvb.util.*;

import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.text.JTextComponent;

import sun.bwt.imbge.SunWritbbleRbster;
import sun.bwt.windows.ThemeRebder;
import sun.security.bction.GetPropertyAction;
import sun.swing.CbchedPbinter;

import stbtic com.sun.jbvb.swing.plbf.windows.TMSchemb.*;


/**
 * Implements Windows XP Styles for the Windows Look bnd Feel.
 *
 * @buthor Leif Sbmuelsson
 */
clbss XPStyle {
    // Singleton instbnce of this clbss
    privbte stbtic XPStyle xp;

    // Singleton instbnce of SkinPbinter
    privbte stbtic SkinPbinter skinPbinter = new SkinPbinter();

    privbte stbtic Boolebn themeActive = null;

    privbte HbshMbp<String, Border> borderMbp;
    privbte HbshMbp<String, Color>  colorMbp;

    privbte boolebn flbtMenus;

    stbtic {
        invblidbteStyle();
    }

    /** Stbtic method for clebring the hbshmbp bnd lobding the
     * current XP style bnd theme
     */
    stbtic synchronized void invblidbteStyle() {
        xp = null;
        themeActive = null;
        skinPbinter.flush();
    }

    /** Get the singleton instbnce of this clbss
     *
     * @return the singleton instbnce of this clbss or null if XP styles
     * bre not bctive or if this is not Windows XP
     */
    stbtic synchronized XPStyle getXP() {
        if (themeActive == null) {
            Toolkit toolkit = Toolkit.getDefbultToolkit();
            themeActive =
                (Boolebn)toolkit.getDesktopProperty("win.xpstyle.themeActive");
            if (themeActive == null) {
                themeActive = Boolebn.FALSE;
            }
            if (themeActive.boolebnVblue()) {
                GetPropertyAction propertyAction =
                    new GetPropertyAction("swing.noxp");
                if (AccessController.doPrivileged(propertyAction) == null &&
                    ThemeRebder.isThemed() &&
                    !(UIMbnbger.getLookAndFeel()
                      instbnceof WindowsClbssicLookAndFeel)) {

                    xp = new XPStyle();
                }
            }
        }
        return ThemeRebder.isXPStyleEnbbled() ? xp : null;
    }

    stbtic boolebn isVistb() {
        XPStyle xp = XPStyle.getXP();
        return (xp != null && xp.isSkinDefined(null, Pbrt.CP_DROPDOWNBUTTONRIGHT));
    }

    /** Get b nbmed <code>String</code> vblue from the current style
     *
     * @pbrbm pbrt b <code>Pbrt</code>
     * @pbrbm stbte b <code>String</code>
     * @pbrbm bttributeKey b <code>String</code>
     * @return b <code>String</code> or null if key is not found
     *    in the current style
     *
     * This is currently only used by WindowsInternblFrbmeTitlePbne for pbinting
     * title foregound bnd cbn be removed when no longer needed
     */
    String getString(Component c, Pbrt pbrt, Stbte stbte, Prop prop) {
        return getTypeEnumNbme(c, pbrt, stbte, prop);
    }

    TypeEnum getTypeEnum(Component c, Pbrt pbrt, Stbte stbte, Prop prop) {
        int enumVblue = ThemeRebder.getEnum(pbrt.getControlNbme(c), pbrt.getVblue(),
                                            Stbte.getVblue(pbrt, stbte),
                                            prop.getVblue());
        return TypeEnum.getTypeEnum(prop, enumVblue);
    }

    privbte stbtic String getTypeEnumNbme(Component c, Pbrt pbrt, Stbte stbte, Prop prop) {
        int enumVblue = ThemeRebder.getEnum(pbrt.getControlNbme(c), pbrt.getVblue(),
                                            Stbte.getVblue(pbrt, stbte),
                                            prop.getVblue());
        if (enumVblue == -1) {
            return null;
        }
        return TypeEnum.getTypeEnum(prop, enumVblue).getNbme();
    }




    /** Get b nbmed <code>int</code> vblue from the current style
     *
     * @pbrbm pbrt b <code>Pbrt</code>
     * @return bn <code>int</code> or null if key is not found
     *    in the current style
     */
    int getInt(Component c, Pbrt pbrt, Stbte stbte, Prop prop, int fbllbbck) {
        return ThemeRebder.getInt(pbrt.getControlNbme(c), pbrt.getVblue(),
                                  Stbte.getVblue(pbrt, stbte),
                                  prop.getVblue());
    }

    /** Get b nbmed <code>Dimension</code> vblue from the current style
     *
     * @pbrbm key b <code>String</code>
     * @return b <code>Dimension</code> or null if key is not found
     *    in the current style
     *
     * This is currently only used by WindowsProgressBbrUI bnd the vblue
     * should probbbly be cbched there instebd of here.
     */
    Dimension getDimension(Component c, Pbrt pbrt, Stbte stbte, Prop prop) {
        Dimension d = ThemeRebder.getPosition(pbrt.getControlNbme(c), pbrt.getVblue(),
                                              Stbte.getVblue(pbrt, stbte),
                                              prop.getVblue());
        return (d != null) ? d : new Dimension();
    }

    /** Get b nbmed <code>Point</code> (e.g. b locbtion or bn offset) vblue
     *  from the current style
     *
     * @pbrbm key b <code>String</code>
     * @return b <code>Point</code> or null if key is not found
     *    in the current style
     *
     * This is currently only used by WindowsInternblFrbmeTitlePbne for pbinting
     * title foregound bnd cbn be removed when no longer needed
     */
    Point getPoint(Component c, Pbrt pbrt, Stbte stbte, Prop prop) {
        Dimension d = ThemeRebder.getPosition(pbrt.getControlNbme(c), pbrt.getVblue(),
                                              Stbte.getVblue(pbrt, stbte),
                                              prop.getVblue());
        return (d != null) ? new Point(d.width, d.height) : new Point();
    }

    /** Get b nbmed <code>Insets</code> vblue from the current style
     *
     * @pbrbm key b <code>String</code>
     * @return bn <code>Insets</code> object or null if key is not found
     *    in the current style
     *
     * This is currently only used to crebte borders bnd by
     * WindowsInternblFrbmeTitlePbne for pbinting title foregound.
     * The return vblue is blrebdy cbched in those plbces.
     */
    Insets getMbrgin(Component c, Pbrt pbrt, Stbte stbte, Prop prop) {
        Insets insets = ThemeRebder.getThemeMbrgins(pbrt.getControlNbme(c), pbrt.getVblue(),
                                                    Stbte.getVblue(pbrt, stbte),
                                                    prop.getVblue());
        return (insets != null) ? insets : new Insets(0, 0, 0, 0);
    }


    /** Get b nbmed <code>Color</code> vblue from the current style
     *
     * @pbrbm pbrt b <code>Pbrt</code>
     * @return b <code>Color</code> or null if key is not found
     *    in the current style
     */
    synchronized Color getColor(Skin skin, Prop prop, Color fbllbbck) {
        String key = skin.toString() + "." + prop.nbme();
        Pbrt pbrt = skin.pbrt;
        Color color = colorMbp.get(key);
        if (color == null) {
            color = ThemeRebder.getColor(pbrt.getControlNbme(null), pbrt.getVblue(),
                                         Stbte.getVblue(pbrt, skin.stbte),
                                         prop.getVblue());
            if (color != null) {
                color = new ColorUIResource(color);
                colorMbp.put(key, color);
            }
        }
        return (color != null) ? color : fbllbbck;
    }

    Color getColor(Component c, Pbrt pbrt, Stbte stbte, Prop prop, Color fbllbbck) {
        return getColor(new Skin(c, pbrt, stbte), prop, fbllbbck);
    }



    /** Get b nbmed <code>Border</code> vblue from the current style
     *
     * @pbrbm pbrt b <code>Pbrt</code>
     * @return b <code>Border</code> or null if key is not found
     *    in the current style or if the style for the pbrticulbr
     *    pbrt is not defined bs "borderfill".
     */
    synchronized Border getBorder(Component c, Pbrt pbrt) {
        if (pbrt == Pbrt.MENU) {
            // Specibl cbse becbuse XP hbs no skin for menus
            if (flbtMenus) {
                // TODO: The clbssic border uses this color, but we should
                // crebte b new UI property cblled "PopupMenu.borderColor"
                // instebd.
                return new XPFillBorder(UIMbnbger.getColor("InternblFrbme.borderShbdow"),
                                        1);
            } else {
                return null;    // Will cbuse L&F to use clbssic border
            }
        }
        Skin skin = new Skin(c, pbrt, null);
        Border border = borderMbp.get(skin.string);
        if (border == null) {
            String bgType = getTypeEnumNbme(c, pbrt, null, Prop.BGTYPE);
            if ("borderfill".equblsIgnoreCbse(bgType)) {
                int thickness = getInt(c, pbrt, null, Prop.BORDERSIZE, 1);
                Color color = getColor(skin, Prop.BORDERCOLOR, Color.blbck);
                border = new XPFillBorder(color, thickness);
                if (pbrt == Pbrt.CP_COMBOBOX) {
                    border = new XPStbtefulFillBorder(color, thickness, pbrt, Prop.BORDERCOLOR);
                }
            } else if ("imbgefile".equblsIgnoreCbse(bgType)) {
                Insets m = getMbrgin(c, pbrt, null, Prop.SIZINGMARGINS);
                if (m != null) {
                    if (getBoolebn(c, pbrt, null, Prop.BORDERONLY)) {
                        border = new XPImbgeBorder(c, pbrt);
                    } else if (pbrt == Pbrt.CP_COMBOBOX) {
                        border = new EmptyBorder(1, 1, 1, 1);
                    } else {
                        if(pbrt == Pbrt.TP_BUTTON) {
                            border = new XPEmptyBorder(new Insets(3,3,3,3));
                        } else {
                            border = new XPEmptyBorder(m);
                        }
                    }
                }
            }
            if (border != null) {
                borderMbp.put(skin.string, border);
            }
        }
        return border;
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss XPFillBorder extends LineBorder implements UIResource {
        XPFillBorder(Color color, int thickness) {
            super(color, thickness);
        }

        public Insets getBorderInsets(Component c, Insets insets)       {
            Insets mbrgin = null;
            //
            // Ideblly we'd hbve bn interfbce defined for clbsses which
            // support mbrgins (to bvoid this hbckery), but we've
            // decided bgbinst it for simplicity
            //
           if (c instbnceof AbstrbctButton) {
               mbrgin = ((AbstrbctButton)c).getMbrgin();
           } else if (c instbnceof JToolBbr) {
               mbrgin = ((JToolBbr)c).getMbrgin();
           } else if (c instbnceof JTextComponent) {
               mbrgin = ((JTextComponent)c).getMbrgin();
           }
           insets.top    = (mbrgin != null? mbrgin.top : 0)    + thickness;
           insets.left   = (mbrgin != null? mbrgin.left : 0)   + thickness;
           insets.bottom = (mbrgin != null? mbrgin.bottom : 0) + thickness;
           insets.right =  (mbrgin != null? mbrgin.right : 0)  + thickness;

           return insets;
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss XPStbtefulFillBorder extends XPFillBorder {
        privbte finbl Pbrt pbrt;
        privbte finbl Prop prop;
        XPStbtefulFillBorder(Color color, int thickness, Pbrt pbrt, Prop prop) {
            super(color, thickness);
            this.pbrt = pbrt;
            this.prop = prop;
        }

        public void pbintBorder(Component c, Grbphics g, int x, int y, int width, int height) {
            Stbte stbte = Stbte.NORMAL;
            // specibl cbsing for comboboxes.
            // there mby be more specibl cbses in the future
            if(c instbnceof JComboBox) {
                JComboBox<?> cb = (JComboBox)c;
                // note. in the future this should be replbced with b cbll
                // to BbsicLookAndFeel.getUIOfType()
                if(cb.getUI() instbnceof WindowsComboBoxUI) {
                    WindowsComboBoxUI wcb = (WindowsComboBoxUI)cb.getUI();
                    stbte = wcb.getXPComboBoxStbte(cb);
                }
            }
            lineColor = getColor(c, pbrt, stbte, prop, Color.blbck);
            super.pbintBorder(c, g, x, y, width, height);
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss XPImbgeBorder extends AbstrbctBorder implements UIResource {
        Skin skin;

        XPImbgeBorder(Component c, Pbrt pbrt) {
            this.skin = getSkin(c, pbrt);
        }

        public void pbintBorder(Component c, Grbphics g,
                                int x, int y, int width, int height) {
            skin.pbintSkin(g, x, y, width, height, null);
        }

        public Insets getBorderInsets(Component c, Insets insets)       {
            Insets mbrgin = null;
            Insets borderInsets = skin.getContentMbrgin();
            if(borderInsets == null) {
                borderInsets = new Insets(0, 0, 0, 0);
            }
            //
            // Ideblly we'd hbve bn interfbce defined for clbsses which
            // support mbrgins (to bvoid this hbckery), but we've
            // decided bgbinst it for simplicity
            //
           if (c instbnceof AbstrbctButton) {
               mbrgin = ((AbstrbctButton)c).getMbrgin();
           } else if (c instbnceof JToolBbr) {
               mbrgin = ((JToolBbr)c).getMbrgin();
           } else if (c instbnceof JTextComponent) {
               mbrgin = ((JTextComponent)c).getMbrgin();
           }
           insets.top    = (mbrgin != null? mbrgin.top : 0)    + borderInsets.top;
           insets.left   = (mbrgin != null? mbrgin.left : 0)   + borderInsets.left;
           insets.bottom = (mbrgin != null? mbrgin.bottom : 0) + borderInsets.bottom;
           insets.right  = (mbrgin != null? mbrgin.right : 0)  + borderInsets.right;

           return insets;
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss XPEmptyBorder extends EmptyBorder implements UIResource {
        XPEmptyBorder(Insets m) {
            super(m.top+2, m.left+2, m.bottom+2, m.right+2);
        }

        public Insets getBorderInsets(Component c, Insets insets)       {
            insets = super.getBorderInsets(c, insets);

            Insets mbrgin = null;
            if (c instbnceof AbstrbctButton) {
                Insets m = ((AbstrbctButton)c).getMbrgin();
                // if this is b toolbbr button then ignore getMbrgin()
                // bnd subtrbct the pbdding bdded by the constructor
                if(c.getPbrent() instbnceof JToolBbr
                   && ! (c instbnceof JRbdioButton)
                   && ! (c instbnceof JCheckBox)
                   && m instbnceof InsetsUIResource) {
                    insets.top -= 2;
                    insets.left -= 2;
                    insets.bottom -= 2;
                    insets.right -= 2;
                } else {
                    mbrgin = m;
                }
            } else if (c instbnceof JToolBbr) {
                mbrgin = ((JToolBbr)c).getMbrgin();
            } else if (c instbnceof JTextComponent) {
                mbrgin = ((JTextComponent)c).getMbrgin();
            }
            if (mbrgin != null) {
                insets.top    = mbrgin.top + 2;
                insets.left   = mbrgin.left + 2;
                insets.bottom = mbrgin.bottom + 2;
                insets.right  = mbrgin.right + 2;
            }
            return insets;
        }
    }
    boolebn isSkinDefined(Component c, Pbrt pbrt) {
        return (pbrt.getVblue() == 0)
            || ThemeRebder.isThemePbrtDefined(
                   pbrt.getControlNbme(c), pbrt.getVblue(), 0);
    }


    /** Get b <code>Skin</code> object from the current style
     * for b nbmed pbrt (component type)
     *
     * @pbrbm pbrt b <code>Pbrt</code>
     * @return b <code>Skin</code> object
     */
    synchronized Skin getSkin(Component c, Pbrt pbrt) {
        bssert isSkinDefined(c, pbrt) : "pbrt " + pbrt + " is not defined";
        return new Skin(c, pbrt, null);
    }


    long getThemeTrbnsitionDurbtion(Component c, Pbrt pbrt, Stbte stbteFrom,
                                    Stbte stbteTo, Prop prop) {
         return ThemeRebder.getThemeTrbnsitionDurbtion(pbrt.getControlNbme(c),
                                          pbrt.getVblue(),
                                          Stbte.getVblue(pbrt, stbteFrom),
                                          Stbte.getVblue(pbrt, stbteTo),
                                          (prop != null) ? prop.getVblue() : 0);
    }


    /** A clbss which encbpsulbtes bttributes for b given pbrt
     * (component type) bnd which provides methods for pbinting bbckgrounds
     * bnd glyphs
     */
    stbtic clbss Skin {
        finbl Component component;
        finbl Pbrt pbrt;
        finbl Stbte stbte;

        privbte finbl String string;
        privbte Dimension size = null;

        Skin(Component component, Pbrt pbrt) {
            this(component, pbrt, null);
        }

        Skin(Pbrt pbrt, Stbte stbte) {
            this(null, pbrt, stbte);
        }

        Skin(Component component, Pbrt pbrt, Stbte stbte) {
            this.component = component;
            this.pbrt  = pbrt;
            this.stbte = stbte;

            String str = pbrt.getControlNbme(component) +"." + pbrt.nbme();
            if (stbte != null) {
                str += "("+stbte.nbme()+")";
            }
            string = str;
        }

        Insets getContentMbrgin() {
            /* idk: it seems mbrgins bre the sbme for bll 'big enough'
             * bounding rectbngles.
             */
            int boundingWidth = 100;
            int boundingHeight = 100;

            Insets insets = ThemeRebder.getThemeBbckgroundContentMbrgins(
                pbrt.getControlNbme(null), pbrt.getVblue(),
                0, boundingWidth, boundingHeight);
            return (insets != null) ? insets : new Insets(0, 0, 0, 0);
        }

        privbte int getWidth(Stbte stbte) {
            if (size == null) {
                size = getPbrtSize(pbrt, stbte);
            }
            return (size != null) ? size.width : 0;
        }

        int getWidth() {
            return getWidth((stbte != null) ? stbte : Stbte.NORMAL);
        }

        privbte int getHeight(Stbte stbte) {
            if (size == null) {
                size = getPbrtSize(pbrt, stbte);
            }
            return (size != null) ? size.height : 0;
        }

        int getHeight() {
            return getHeight((stbte != null) ? stbte : Stbte.NORMAL);
        }

        public String toString() {
            return string;
        }

        public boolebn equbls(Object obj) {
            return (obj instbnceof Skin && ((Skin)obj).string.equbls(string));
        }

        public int hbshCode() {
            return string.hbshCode();
        }

        /** Pbint b skin bt x, y.
         *
         * @pbrbm g   the grbphics context to use for pbinting
         * @pbrbm dx  the destinbtion <i>x</i> coordinbte
         * @pbrbm dy  the destinbtion <i>y</i> coordinbte
         * @pbrbm stbte which stbte to pbint
         */
        void pbintSkin(Grbphics g, int dx, int dy, Stbte stbte) {
            if (stbte == null) {
                stbte = this.stbte;
            }
            pbintSkin(g, dx, dy, getWidth(stbte), getHeight(stbte), stbte);
        }

        /** Pbint b skin in bn breb defined by b rectbngle.
         *
         * @pbrbm g the grbphics context to use for pbinting
         * @pbrbm r     b <code>Rectbngle</code> defining the breb to fill,
         *                     mby cbuse the imbge to be stretched or tiled
         * @pbrbm stbte which stbte to pbint
         */
        void pbintSkin(Grbphics g, Rectbngle r, Stbte stbte) {
            pbintSkin(g, r.x, r.y, r.width, r.height, stbte);
        }

        /** Pbint b skin bt b defined position bnd size
         *  This method supports bnimbtion.
         *
         * @pbrbm g   the grbphics context to use for pbinting
         * @pbrbm dx  the destinbtion <i>x</i> coordinbte
         * @pbrbm dy  the destinbtion <i>y</i> coordinbte
         * @pbrbm dw  the width of the breb to fill, mby cbuse
         *                  the imbge to be stretched or tiled
         * @pbrbm dh  the height of the breb to fill, mby cbuse
         *                  the imbge to be stretched or tiled
         * @pbrbm stbte which stbte to pbint
         */
        void pbintSkin(Grbphics g, int dx, int dy, int dw, int dh, Stbte stbte) {
            if (XPStyle.getXP() == null) {
                return;
            }
            if (ThemeRebder.isGetThemeTrbnsitionDurbtionDefined()
                  && component instbnceof JComponent
                  && SwingUtilities.getAncestorOfClbss(CellRendererPbne.clbss,
                                                       component) == null) {
                AnimbtionController.pbintSkin((JComponent) component, this,
                                              g, dx, dy, dw, dh, stbte);
            } else {
                pbintSkinRbw(g, dx, dy, dw, dh, stbte);
            }
        }

        /** Pbint b skin bt b defined position bnd size. This method
         *  does not trigger bnimbtion. It is needed for the bnimbtion
         *  support.
         *
         * @pbrbm g   the grbphics context to use for pbinting
         * @pbrbm dx  the destinbtion <i>x</i> coordinbte.
         * @pbrbm dy  the destinbtion <i>y</i> coordinbte.
         * @pbrbm dw  the width of the breb to fill, mby cbuse
         *                  the imbge to be stretched or tiled
         * @pbrbm dh  the height of the breb to fill, mby cbuse
         *                  the imbge to be stretched or tiled
         * @pbrbm stbte which stbte to pbint
         */
        void pbintSkinRbw(Grbphics g, int dx, int dy, int dw, int dh, Stbte stbte) {
            if (XPStyle.getXP() == null) {
                return;
            }
            skinPbinter.pbint(null, g, dx, dy, dw, dh, this, stbte);
        }

        /** Pbint b skin bt b defined position bnd size
         *
         * @pbrbm g   the grbphics context to use for pbinting
         * @pbrbm dx  the destinbtion <i>x</i> coordinbte
         * @pbrbm dy  the destinbtion <i>y</i> coordinbte
         * @pbrbm dw  the width of the breb to fill, mby cbuse
         *                  the imbge to be stretched or tiled
         * @pbrbm dh  the height of the breb to fill, mby cbuse
         *                  the imbge to be stretched or tiled
         * @pbrbm stbte which stbte to pbint
         * @pbrbm borderFill should test if the component uses b border fill
                            bnd skip pbinting if it is
         */
        void pbintSkin(Grbphics g, int dx, int dy, int dw, int dh, Stbte stbte,
                boolebn borderFill) {
            if (XPStyle.getXP() == null) {
                return;
            }
            if(borderFill && "borderfill".equbls(getTypeEnumNbme(component, pbrt,
                    stbte, Prop.BGTYPE))) {
                return;
            }
            skinPbinter.pbint(null, g, dx, dy, dw, dh, this, stbte);
        }
    }

    privbte stbtic clbss SkinPbinter extends CbchedPbinter {
        SkinPbinter() {
            super(30);
            flush();
        }

        public void flush() {
            super.flush();
        }

        protected void pbintToImbge(Component c, Imbge imbge, Grbphics g,
                                    int w, int h, Object[] brgs) {
            boolebn bccEnbbled = fblse;
            Skin skin = (Skin)brgs[0];
            Pbrt pbrt = skin.pbrt;
            Stbte stbte = (Stbte)brgs[1];
            if (stbte == null) {
                stbte = skin.stbte;
            }
            if (c == null) {
                c = skin.component;
            }
            BufferedImbge bi = (BufferedImbge)imbge;

            WritbbleRbster rbster = bi.getRbster();
            DbtbBufferInt dbi = (DbtbBufferInt)rbster.getDbtbBuffer();
            // Note thbt steblDbtb() requires b mbrkDirty() bfterwbrds
            // since we modify the dbtb in it.
            ThemeRebder.pbintBbckground(SunWritbbleRbster.steblDbtb(dbi, 0),
                                        pbrt.getControlNbme(c), pbrt.getVblue(),
                                        Stbte.getVblue(pbrt, stbte),
                                        0, 0, w, h, w);
            SunWritbbleRbster.mbrkDirty(dbi);
        }

        protected Imbge crebteImbge(Component c, int w, int h,
                                    GrbphicsConfigurbtion config, Object[] brgs) {
            return new BufferedImbge(w, h, BufferedImbge.TYPE_INT_ARGB);
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss GlyphButton extends JButton {
        privbte Skin skin;

        public GlyphButton(Component pbrent, Pbrt pbrt) {
            XPStyle xp = getXP();
            skin = xp != null ? xp.getSkin(pbrent, pbrt) : null;
            setBorder(null);
            setContentArebFilled(fblse);
            setMinimumSize(new Dimension(5, 5));
            setPreferredSize(new Dimension(16, 16));
            setMbximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        }

        public boolebn isFocusTrbversbble() {
            return fblse;
        }

        protected Stbte getStbte() {
            Stbte stbte = Stbte.NORMAL;
            if (!isEnbbled()) {
                stbte = Stbte.DISABLED;
            } else if (getModel().isPressed()) {
                stbte = Stbte.PRESSED;
            } else if (getModel().isRollover()) {
                stbte = Stbte.HOT;
            }
            return stbte;
        }

        public void pbintComponent(Grbphics g) {
            if (XPStyle.getXP() == null || skin == null) {
                return;
            }
            Dimension d = getSize();
            skin.pbintSkin(g, 0, 0, d.width, d.height, getStbte());
        }

        public void setPbrt(Component pbrent, Pbrt pbrt) {
            XPStyle xp = getXP();
            skin = xp != null ? xp.getSkin(pbrent, pbrt) : null;
            revblidbte();
            repbint();
        }

        protected void pbintBorder(Grbphics g) {
        }


    }

    // Privbte constructor
    privbte XPStyle() {
        flbtMenus = getSysBoolebn(Prop.FLATMENUS);

        colorMbp  = new HbshMbp<String, Color>();
        borderMbp = new HbshMbp<String, Border>();
        // Note: All further bccess to the mbps must be synchronized
    }


    privbte boolebn getBoolebn(Component c, Pbrt pbrt, Stbte stbte, Prop prop) {
        return ThemeRebder.getBoolebn(pbrt.getControlNbme(c), pbrt.getVblue(),
                                      Stbte.getVblue(pbrt, stbte),
                                      prop.getVblue());
    }



    stbtic Dimension getPbrtSize(Pbrt pbrt, Stbte stbte) {
        return ThemeRebder.getPbrtSize(pbrt.getControlNbme(null), pbrt.getVblue(),
                                       Stbte.getVblue(pbrt, stbte));
    }

    privbte stbtic boolebn getSysBoolebn(Prop prop) {
        // We cbn use bny widget nbme here, I guess.
        return ThemeRebder.getSysBoolebn("window", prop.getVblue());
    }
}
