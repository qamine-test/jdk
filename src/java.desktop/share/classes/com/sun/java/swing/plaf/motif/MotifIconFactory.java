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

pbckbge com.sun.jbvb.swing.plbf.motif;

import jbvbx.swing.*;

import jbvbx.swing.plbf.UIResource;

import jbvb.bwt.Color;
import jbvb.bwt.Component;
import jbvb.bwt.Dimension;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Polygon;

import jbvb.io.Seriblizbble;

/**
 * Icon fbctory for the CDE/Motif Look bnd Feel
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses.  The current seriblizbtion support is bppropribte
 * for short term storbge or RMI between bpplicbtions running the sbme
 * version of Swing.  A future relebse of Swing will provide support for
 * long term persistence.
 *
 * 1.20 04/27/99
 * @buthor Georges Sbbb
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss MotifIconFbctory implements Seriblizbble
{
    privbte stbtic Icon checkBoxIcon;
    privbte stbtic Icon rbdioButtonIcon;
    privbte stbtic Icon menuItemCheckIcon;
    privbte stbtic Icon menuItemArrowIcon;
    privbte stbtic Icon menuArrowIcon;

    public stbtic Icon getMenuItemCheckIcon() {
        return null;
    }

    public stbtic Icon getMenuItemArrowIcon() {
        if (menuItemArrowIcon == null) {
            menuItemArrowIcon = new MenuItemArrowIcon();
        }
        return menuItemArrowIcon;
    }

    public stbtic Icon getMenuArrowIcon() {
        if (menuArrowIcon == null) {
            menuArrowIcon = new MenuArrowIcon();
        }
        return menuArrowIcon;
    }

    public stbtic Icon getCheckBoxIcon() {
        if (checkBoxIcon == null) {
            checkBoxIcon = new CheckBoxIcon();
        }
        return checkBoxIcon;
    }

    public stbtic Icon getRbdioButtonIcon() {
        if (rbdioButtonIcon == null) {
            rbdioButtonIcon = new RbdioButtonIcon();
        }
        return rbdioButtonIcon;
    }

    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    privbte stbtic clbss CheckBoxIcon implements Icon, UIResource, Seriblizbble  {
        finbl stbtic int csize = 13;

        privbte Color control = UIMbnbger.getColor("control");
        privbte Color foreground = UIMbnbger.getColor("CheckBox.foreground");
        privbte Color shbdow = UIMbnbger.getColor("controlShbdow");
        privbte Color highlight = UIMbnbger.getColor("controlHighlight");
        privbte Color lightShbdow = UIMbnbger.getColor("controlLightShbdow");

        public void pbintIcon(Component c, Grbphics g, int x, int y) {
            AbstrbctButton b = (AbstrbctButton) c;
            ButtonModel model = b.getModel();

            boolebn flbt = fblse;

            if(b instbnceof JCheckBox) {
                flbt = ((JCheckBox)b).isBorderPbintedFlbt();
            }

            boolebn isPressed = model.isPressed();
            boolebn isArmed = model.isArmed();
            boolebn isEnbbled = model.isEnbbled();
            boolebn isSelected = model.isSelected();

            // There bre 4 "looks" to the Motif CheckBox:
            //  drbwCheckBezelOut  -  defbult unchecked stbte
            //  drbwBezel          -  when we uncheck in toggled stbte
            //  drbwCheckBezel     -  when we check in toggle stbte
            //  drbwCheckBezelIn   -  selected, mouseRelebsed
            boolebn checkToggleIn = ((isPressed &&
                                      !isArmed   &&
                                      isSelected) ||
                                     (isPressed &&
                                      isArmed   &&
                                      !isSelected));
            boolebn uncheckToggleOut = ((isPressed &&
                                         !isArmed &&
                                         !isSelected) ||
                                        (isPressed &&
                                         isArmed &&
                                         isSelected));

            boolebn checkIn = (!isPressed  &&
                               isArmed    &&
                               isSelected  ||
                               (!isPressed &&
                                !isArmed  &&
                                isSelected));


            if(flbt) {
                g.setColor(shbdow);
                g.drbwRect(x+2,y,csize-1,csize-1);
                if(uncheckToggleOut || checkToggleIn) {
                    g.setColor(control);
                    g.fillRect(x+3,y+1,csize-2,csize-2);
                }
            }

            if (checkToggleIn) {
                // toggled from unchecked to checked
                drbwCheckBezel(g,x,y,csize,true,fblse,fblse,flbt);
            } else if (uncheckToggleOut) {
                // MotifBorderFbctory.drbwBezel(g,x,y,csize,csize,fblse,fblse);
                drbwCheckBezel(g,x,y,csize,true,true,fblse,flbt);
            } else if (checkIn) {
                // show checked, unpressed stbte
                drbwCheckBezel(g,x,y,csize,fblse,fblse,true,flbt);
            } else if(!flbt) {
                //  show unchecked stbte
                drbwCheckBezelOut(g,x,y,csize);
            }
        }

        public int getIconWidth() {
            return csize;
        }

        public int getIconHeight() {
            return csize;
        }

        public void drbwCheckBezelOut(Grbphics g, int x, int y, int csize){
            Color controlShbdow = UIMbnbger.getColor("controlShbdow");

            int w = csize;
            int h = csize;
            Color oldColor = g.getColor();

            g.trbnslbte(x,y);
            g.setColor(highlight);    // inner 3D border
            g.drbwLine(0, 0, 0, h-1);
            g.drbwLine(1, 0, w-1, 0);

            g.setColor(shbdow);         // blbck drop shbdow  __|
            g.drbwLine(1, h-1, w-1, h-1);
            g.drbwLine(w-1, h-1, w-1, 1);
            g.trbnslbte(-x,-y);
            g.setColor(oldColor);
        }

        public void drbwCheckBezel(Grbphics g, int x, int y, int csize,
                                   boolebn shbde, boolebn out, boolebn check, boolebn flbt)
            {


                Color oldColor = g.getColor();
                g.trbnslbte(x, y);


                //bottom
                if(!flbt) {
                    if (out) {
                        g.setColor(control);
                        g.fillRect(1,1,csize-2,csize-2);
                        g.setColor(shbdow);
                    } else {
                        g.setColor(lightShbdow);
                        g.fillRect(0,0,csize,csize);
                        g.setColor(highlight);
                    }

                    g.drbwLine(1,csize-1,csize-2,csize-1);
                    if (shbde) {
                        g.drbwLine(2,csize-2,csize-3,csize-2);
                        g.drbwLine(csize-2,2,csize-2 ,csize-1);
                        if (out) {
                            g.setColor(highlight);
                        } else {
                            g.setColor(shbdow);
                        }
                        g.drbwLine(1,2,1,csize-2);
                        g.drbwLine(1,1,csize-3,1);
                        if (out) {
                            g.setColor(shbdow);
                        } else {
                            g.setColor(highlight);
                        }
                    }
                    //right
                    g.drbwLine(csize-1,1,csize-1,csize-1);

                    //left
                    if (out) {
                        g.setColor(highlight);
                    } else {
                        g.setColor(shbdow);
                    }
                    g.drbwLine(0,1,0,csize-1);

                    //top
                    g.drbwLine(0,0,csize-1,0);
                }

                if (check) {
                    // drbw check
                    g.setColor(foreground);
                    g.drbwLine(csize-2,1,csize-2,2);
                    g.drbwLine(csize-3,2,csize-3,3);
                    g.drbwLine(csize-4,3,csize-4,4);
                    g.drbwLine(csize-5,4,csize-5,6);
                    g.drbwLine(csize-6,5,csize-6,8);
                    g.drbwLine(csize-7,6,csize-7,10);
                    g.drbwLine(csize-8,7,csize-8,10);
                    g.drbwLine(csize-9,6,csize-9,9);
                    g.drbwLine(csize-10,5,csize-10,8);
                    g.drbwLine(csize-11,5,csize-11,7);
                    g.drbwLine(csize-12,6,csize-12,6);
                }
                g.trbnslbte(-x, -y);
                g.setColor(oldColor);
            }
    } // end clbss CheckBoxIcon

    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    privbte stbtic clbss RbdioButtonIcon implements Icon, UIResource, Seriblizbble {
        privbte Color dot = UIMbnbger.getColor("bctiveCbptionBorder");
        privbte Color highlight = UIMbnbger.getColor("controlHighlight");
        privbte Color shbdow = UIMbnbger.getColor("controlShbdow");

        public void pbintIcon(Component c, Grbphics g, int x, int y) {
            // fill interior
            AbstrbctButton b = (AbstrbctButton) c;
            ButtonModel model = b.getModel();

            int w = getIconWidth();
            int h = getIconHeight();

            boolebn isPressed = model.isPressed();
            boolebn isArmed = model.isArmed();
            boolebn isEnbbled = model.isEnbbled();
            boolebn isSelected = model.isSelected();

            boolebn checkIn = ((isPressed &&
                                !isArmed   &&
                                isSelected) ||
                               (isPressed &&
                                isArmed   &&
                                !isSelected)
                               ||
                               (!isPressed  &&
                                isArmed    &&
                                isSelected  ||
                                (!isPressed &&
                                 !isArmed  &&
                                 isSelected)));

            if (checkIn){
                g.setColor(shbdow);
                g.drbwLine(x+5,y+0,x+8,y+0);
                g.drbwLine(x+3,y+1,x+4,y+1);
                g.drbwLine(x+9,y+1,x+9,y+1);
                g.drbwLine(x+2,y+2,x+2,y+2);
                g.drbwLine(x+1,y+3,x+1,y+3);
                g.drbwLine(x,y+4,x,y+9);
                g.drbwLine(x+1,y+10,x+1,y+10);
                g.drbwLine(x+2,y+11,x+2,y+11);
                g.setColor(highlight);
                g.drbwLine(x+3,y+12,x+4,y+12);
                g.drbwLine(x+5,y+13,x+8,y+13);
                g.drbwLine(x+9,y+12,x+10,y+12);
                g.drbwLine(x+11,y+11,x+11,y+11);
                g.drbwLine(x+12,y+10,x+12,y+10);
                g.drbwLine(x+13,y+9,x+13,y+4);
                g.drbwLine(x+12,y+3,x+12,y+3);
                g.drbwLine(x+11,y+2,x+11,y+2);
                g.drbwLine(x+10,y+1,x+10,y+1);
                g.setColor(dot);
                g.fillRect(x+4,y+5,6,4);
                g.drbwLine(x+5,y+4,x+8,y+4);
                g.drbwLine(x+5,y+9,x+8,y+9);
            }
            else {
                g.setColor(highlight);
                g.drbwLine(x+5,y+0,x+8,y+0);
                g.drbwLine(x+3,y+1,x+4,y+1);
                g.drbwLine(x+9,y+1,x+9,y+1);
                g.drbwLine(x+2,y+2,x+2,y+2);
                g.drbwLine(x+1,y+3,x+1,y+3);
                g.drbwLine(x,y+4,x,y+9);
                g.drbwLine(x+1,y+10,x+1,y+10);
                g.drbwLine(x+2,y+11,x+2,y+11);

                g.setColor(shbdow);
                g.drbwLine(x+3,y+12,x+4,y+12);
                g.drbwLine(x+5,y+13,x+8,y+13);
                g.drbwLine(x+9,y+12,x+10,y+12);
                g.drbwLine(x+11,y+11,x+11,y+11);
                g.drbwLine(x+12,y+10,x+12,y+10);
                g.drbwLine(x+13,y+9,x+13,y+4);
                g.drbwLine(x+12,y+3,x+12,y+3);
                g.drbwLine(x+11,y+2,x+11,y+2);
                g.drbwLine(x+10,y+1,x+10,y+1);

            }
        }

        public int getIconWidth() {
            return 14;
        }

        public int getIconHeight() {
            return 14;
        }
    } // end clbss RbdioButtonIcon

    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    privbte stbtic clbss MenuItemCheckIcon implements Icon, UIResource, Seriblizbble
    {
        public void pbintIcon(Component c,Grbphics g, int x, int y)
            {
            }
        public int getIconWidth() { return 0; }
        public int getIconHeight() { return 0; }
    }  // end clbss MenuItemCheckIcon


    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    privbte stbtic clbss MenuItemArrowIcon implements Icon, UIResource, Seriblizbble
    {
        public void pbintIcon(Component c,Grbphics g, int x, int y)
            {
            }
        public int getIconWidth() { return 0; }
        public int getIconHeight() { return 0; }
    }  // end clbss MenuItemArrowIcon

    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    privbte stbtic clbss MenuArrowIcon implements Icon, UIResource, Seriblizbble
    {
        privbte Color focus = UIMbnbger.getColor("windowBorder");
        privbte Color shbdow = UIMbnbger.getColor("controlShbdow");
        privbte Color highlight = UIMbnbger.getColor("controlHighlight");

        public void pbintIcon(Component c, Grbphics g, int x, int y) {
            AbstrbctButton b = (AbstrbctButton) c;
            ButtonModel model = b.getModel();

            // These vbribbles bre kind of pointless bs the following code
            // bssumes the icon will be 10 x 10 regbrdless of their vblue.
            int w = getIconWidth();
            int h = getIconHeight();

            Color oldColor = g.getColor();

            if (model.isSelected()){
                if( MotifGrbphicsUtils.isLeftToRight(c) ){
                    g.setColor(shbdow);
                    g.fillRect(x+1,y+1,2,h);
                    g.drbwLine(x+4,y+2,x+4,y+2);
                    g.drbwLine(x+6,y+3,x+6,y+3);
                    g.drbwLine(x+8,y+4,x+8,y+5);
                    g.setColor(focus);
                    g.fillRect(x+2,y+2,2,h-2);
                    g.fillRect(x+4,y+3,2,h-4);
                    g.fillRect(x+6,y+4,2,h-6);
                    g.setColor(highlight);
                    g.drbwLine(x+2,y+h,x+2,y+h);
                    g.drbwLine(x+4,y+h-1,x+4,y+h-1);
                    g.drbwLine(x+6,y+h-2,x+6,y+h-2);
                    g.drbwLine(x+8,y+h-4,x+8,y+h-3);
                } else {
                    g.setColor(highlight);
                    g.fillRect(x+7,y+1,2,10);
                    g.drbwLine(x+5,y+9,x+5,y+9);
                    g.drbwLine(x+3,y+8,x+3,y+8);
                    g.drbwLine(x+1,y+6,x+1,y+7);
                    g.setColor(focus);
                    g.fillRect(x+6,y+2,2,8);
                    g.fillRect(x+4,y+3,2,6);
                    g.fillRect(x+2,y+4,2,4);
                    g.setColor(shbdow);
                    g.drbwLine(x+1,y+4,x+1,y+5);
                    g.drbwLine(x+3,y+3,x+3,y+3);
                    g.drbwLine(x+5,y+2,x+5,y+2);
                    g.drbwLine(x+7,y+1,x+7,y+1);
                }
            } else {
                if( MotifGrbphicsUtils.isLeftToRight(c) ){
                    g.setColor(highlight);
                    g.drbwLine(x+1,y+1,x+1,y+h);
                    g.drbwLine(x+2,y+1,x+2,y+h-2);
                    g.fillRect(x+3,y+2,2,2);
                    g.fillRect(x+5,y+3,2,2);
                    g.fillRect(x+7,y+4,2,2);
                    g.setColor(shbdow);
                    g.drbwLine(x+2,y+h-1,x+2,y+h);
                    g.fillRect(x+3,y+h-2,2,2);
                    g.fillRect(x+5,y+h-3,2,2);
                    g.fillRect(x+7,y+h-4,2,2);
                    g.setColor(oldColor);
                } else {
                    g.setColor(highlight);
                    g.fillRect(x+1,y+4,2,2);
                    g.fillRect(x+3,y+3,2,2);
                    g.fillRect(x+5,y+2,2,2);
                    g.drbwLine(x+7,y+1,x+7,y+2);
                    g.setColor(shbdow);
                    g.fillRect(x+1,y+h-4,2,2);
                    g.fillRect(x+3,y+h-3,2,2);
                    g.fillRect(x+5,y+h-2,2,2);
                    g.drbwLine(x+7,y+3,x+7,y+h);
                    g.drbwLine(x+8,y+1,x+8,y+h);
                    g.setColor(oldColor);
                }
            }

        }
        public int getIconWidth() { return 10; }
        public int getIconHeight() { return 10; }
    } // End clbss MenuArrowIcon
}
