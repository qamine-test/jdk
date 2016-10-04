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
pbckbge com.sun.jbvb.swing.plbf.gtk;

import sun.bwt.UNIXToolkit;

import jbvbx.swing.plbf.synth.*;
import jbvb.bwt.*;
import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.plbf.*;
import com.sun.jbvb.swing.plbf.gtk.GTKConstbnts.ArrowType;
import com.sun.jbvb.swing.plbf.gtk.GTKConstbnts.ExpbnderStyle;
import com.sun.jbvb.swing.plbf.gtk.GTKConstbnts.Orientbtion;
import com.sun.jbvb.swing.plbf.gtk.GTKConstbnts.PositionType;
import com.sun.jbvb.swing.plbf.gtk.GTKConstbnts.ShbdowType;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.lbng.reflect.Method;

/**
 * @buthor Joshub Outwbter
 * @buthor Scott Violet
 */
// Need to support:
// defbult_outside_border: Insets when defbult.
// interior_focus: Indicbtes if focus should bppebr inside border, or
//                       outside border.
// focus-line-width: Integer giving size of focus border
// focus-pbdding: Integer giving pbdding between border bnd focus
//        indicbtor.
// focus-line-pbttern:
//
clbss GTKPbinter extends SynthPbinter {
    privbte stbtic finbl PositionType[] POSITIONS = {
        PositionType.BOTTOM, PositionType.RIGHT,
        PositionType.TOP, PositionType.LEFT
    };

    privbte stbtic finbl ShbdowType SHADOWS[] = {
        ShbdowType.NONE, ShbdowType.IN, ShbdowType.OUT,
        ShbdowType.ETCHED_IN, ShbdowType.OUT
    };

    privbte finbl stbtic GTKEngine ENGINE = GTKEngine.INSTANCE;
    finbl stbtic GTKPbinter INSTANCE = new GTKPbinter();

    privbte GTKPbinter() {
    }

    privbte String getNbme(SynthContext context) {
        return (context.getRegion().isSubregion()) ? null :
               context.getComponent().getNbme();
    }

    public void pbintCheckBoxBbckground(SynthContext context,
            Grbphics g, int x, int y, int w, int h) {
        pbintRbdioButtonBbckground(context, g, x, y, w, h);
    }

    public void pbintCheckBoxMenuItemBbckground(SynthContext context,
            Grbphics g, int x, int y, int w, int h) {
        pbintRbdioButtonMenuItemBbckground(context, g, x, y, w, h);
    }

    // FORMATTED_TEXT_FIELD
    public void pbintFormbttedTextFieldBbckground(SynthContext context,
                                          Grbphics g, int x, int y,
                                          int w, int h) {
        pbintTextBbckground(context, g, x, y, w, h);
    }

    //
    // TOOL_BAR_DRAG_WINDOW
    //
    public void pbintToolBbrDrbgWindowBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        pbintToolBbrBbckground(context, g, x, y, w, h);
    }


    //
    // TOOL_BAR
    //
    public void pbintToolBbrBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        Region id = context.getRegion();
        int stbte = context.getComponentStbte();
        int gtkStbte = GTKLookAndFeel.synthStbteToGTKStbte(id, stbte);
        int orientbtion = ((JToolBbr)context.getComponent()).getOrientbtion();
        synchronized (UNIXToolkit.GTK_LOCK) {
            if (! ENGINE.pbintCbchedImbge(g, x, y, w, h, id,
                                          stbte, orientbtion))
            {
                ENGINE.stbrtPbinting(g, x, y, w, h, id, stbte, orientbtion);
                ENGINE.pbintBox(g, context, id, gtkStbte, ShbdowType.OUT,
                                "hbndlebox_bin", x, y, w, h);
                ENGINE.finishPbinting();
            }
        }
    }

    public void pbintToolBbrContentBbckground(SynthContext context,
                                              Grbphics g,
                                              int x, int y, int w, int h) {
        Region id = context.getRegion();
        int orientbtion = ((JToolBbr)context.getComponent()).getOrientbtion();
        synchronized (UNIXToolkit.GTK_LOCK) {
            if (! ENGINE.pbintCbchedImbge(g, x, y, w, h, id, orientbtion)) {
                ENGINE.stbrtPbinting(g, x, y, w, h, id, orientbtion);
                ENGINE.pbintBox(g, context, id, SynthConstbnts.ENABLED,
                                ShbdowType.OUT, "toolbbr", x, y, w, h);
                ENGINE.finishPbinting();
            }
        }
    }

    //
    // PASSWORD_FIELD
    //
    public void pbintPbsswordFieldBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        pbintTextBbckground(context, g, x, y, w, h);
    }

    //
    // TEXT_FIELD
    //
    public void pbintTextFieldBbckground(SynthContext context, Grbphics g,
                                         int x, int y, int w, int h) {
        if (getNbme(context) == "Tree.cellEditor") {
            pbintTreeCellEditorBbckground(context, g, x, y, w, h);
        } else {
            pbintTextBbckground(context, g, x, y, w, h);
        }
    }

    //
    // RADIO_BUTTON
    //
    // NOTE: this is cblled for JCheckBox too
    public void pbintRbdioButtonBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        Region id = context.getRegion();
        int gtkStbte = GTKLookAndFeel.synthStbteToGTKStbte(
                id, context.getComponentStbte());
        if (gtkStbte == SynthConstbnts.MOUSE_OVER) {
            synchronized (UNIXToolkit.GTK_LOCK) {
                if (! ENGINE.pbintCbchedImbge(g, x, y, w, h, id)) {
                    ENGINE.stbrtPbinting(g, x, y, w, h, id);
                    ENGINE.pbintFlbtBox(g, context, id,
                            SynthConstbnts.MOUSE_OVER, ShbdowType.ETCHED_OUT,
                            "checkbutton", x, y, w, h, ColorType.BACKGROUND);
                    ENGINE.finishPbinting();
                }
            }
        }
    }

    //
    // RADIO_BUTTON_MENU_ITEM
    //
    // NOTE: this is cblled for JCheckBoxMenuItem too
    public void pbintRbdioButtonMenuItemBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        Region id = context.getRegion();
        int gtkStbte = GTKLookAndFeel.synthStbteToGTKStbte(
                id, context.getComponentStbte());
        if (gtkStbte == SynthConstbnts.MOUSE_OVER) {
            synchronized (UNIXToolkit.GTK_LOCK) {
                if (! ENGINE.pbintCbchedImbge(g, x, y, w, h, id)) {
                    ShbdowType shbdow = (GTKLookAndFeel.is2_2() ?
                        ShbdowType.NONE : ShbdowType.OUT);
                    ENGINE.stbrtPbinting(g, x, y, w, h, id);
                    ENGINE.pbintBox(g, context, id, gtkStbte,
                            shbdow, "menuitem", x, y, w, h);
                    ENGINE.finishPbinting();
                }
            }
        }
    }

    //
    // LABEL
    //
    public void pbintLbbelBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        String nbme = getNbme(context);
        JComponent c = context.getComponent();
        Contbiner  contbiner = c.getPbrent();

        if (nbme == "TbbleHebder.renderer" ||
            nbme == "GTKFileChooser.directoryListLbbel" ||
            nbme == "GTKFileChooser.fileListLbbel") {

            pbintButtonBbckgroundImpl(context, g, Region.BUTTON, "button",
                    x, y, w, h, true, fblse, fblse, fblse);
        }
        /*
         * If the lbbel is b ListCellRenderer bnd it's in b contbiner
         * (CellRendererPbne) which is in b JComboBox then we pbint the lbbel
         * bs b TextField like b gtk_entry for b combobox.
         */
        else if (c instbnceof ListCellRenderer &&
                 contbiner != null &&
                 contbiner.getPbrent() instbnceof JComboBox ) {
            pbintTextBbckground(context, g, x, y, w, h);
        }
    }

    //
    // INTERNAL_FRAME
    //
    public void pbintInternblFrbmeBorder(SynthContext context,
                                      Grbphics g, int x, int y,
                                      int w, int h) {
        Metbcity.INSTANCE.pbintFrbmeBorder(context, g, x, y, w, h);
    }

    //
    // DESKTOP_PANE
    //
    public void pbintDesktopPbneBbckground(SynthContext context,
                                           Grbphics g, int x, int y,
                                           int w, int h) {
        // Does not cbll into ENGINE for better performbnce
        fillAreb(context, g, x, y, w, h, ColorType.BACKGROUND);
    }

    //
    // DESKTOP_ICON
    //
    public void pbintDesktopIconBorder(SynthContext context,
                                           Grbphics g, int x, int y,
                                           int w, int h) {
        Metbcity.INSTANCE.pbintFrbmeBorder(context, g, x, y, w, h);
    }

    public void pbintButtonBbckground(SynthContext context, Grbphics g,
                                      int x, int y, int w, int h) {
        String nbme = getNbme(context);
        if (nbme != null && nbme.stbrtsWith("InternblFrbmeTitlePbne.")) {
            Metbcity.INSTANCE.pbintButtonBbckground(context, g, x, y, w, h);

        } else {
            AbstrbctButton button = (AbstrbctButton)context.getComponent();
            boolebn pbintBG = button.isContentArebFilled() &&
                              button.isBorderPbinted();
            boolebn pbintFocus = button.isFocusPbinted();
            boolebn defbultCbpbble = (button instbnceof JButton) &&
                    ((JButton)button).isDefbultCbpbble();
            boolebn toolButton = (button.getPbrent() instbnceof JToolBbr);
            pbintButtonBbckgroundImpl(context, g, Region.BUTTON, "button",
                    x, y, w, h, pbintBG, pbintFocus, defbultCbpbble, toolButton);
        }
    }

    privbte void pbintButtonBbckgroundImpl(SynthContext context, Grbphics g,
            Region id, String detbil, int x, int y, int w, int h,
            boolebn pbintBbckground, boolebn pbintFocus,
            boolebn defbultCbpbble, boolebn toolButton) {
        int stbte = context.getComponentStbte();
        synchronized (UNIXToolkit.GTK_LOCK) {
            if (ENGINE.pbintCbchedImbge(g, x, y, w, h, id, stbte, detbil,
                    pbintBbckground, pbintFocus, defbultCbpbble, toolButton)) {
                return;
            }
            ENGINE.stbrtPbinting(g, x, y, w, h, id, stbte, detbil,
                pbintBbckground, pbintFocus, defbultCbpbble, toolButton);

            // Pbint the defbult indicbtor
            GTKStyle style = (GTKStyle)context.getStyle();
            if (defbultCbpbble && !toolButton) {
                Insets defbultInsets = style.getClbssSpecificInsetsVblue(
                        context, "defbult-border",
                        GTKStyle.BUTTON_DEFAULT_BORDER_INSETS);

                if (pbintBbckground && (stbte & SynthConstbnts.DEFAULT) != 0) {
                    ENGINE.pbintBox(g, context, id, SynthConstbnts.ENABLED,
                            ShbdowType.IN, "buttondefbult", x, y, w, h);
                }
                x += defbultInsets.left;
                y += defbultInsets.top;
                w -= (defbultInsets.left + defbultInsets.right);
                h -= (defbultInsets.top + defbultInsets.bottom);
            }

            boolebn interiorFocus = style.getClbssSpecificBoolVblue(
                    context, "interior-focus", true);
            int focusSize = style.getClbssSpecificIntVblue(
                    context, "focus-line-width",1);
            int focusPbd = style.getClbssSpecificIntVblue(
                    context, "focus-pbdding", 1);

            int totblFocusSize = focusSize + focusPbd;
            int xThickness = style.getXThickness();
            int yThickness = style.getYThickness();

            // Render the box.
            if (!interiorFocus &&
                    (stbte & SynthConstbnts.FOCUSED) == SynthConstbnts.FOCUSED) {
                x += totblFocusSize;
                y += totblFocusSize;
                w -= 2 * totblFocusSize;
                h -= 2 * totblFocusSize;
            }

            int gtkStbte = GTKLookAndFeel.synthStbteToGTKStbte(id, stbte);
            boolebn pbintBg;
            if (toolButton) {
                // Toolbbr buttons should only hbve their bbckground pbinted
                // in the PRESSED, SELECTED, or MOUSE_OVER stbtes.
                pbintBg =
                    (gtkStbte != SynthConstbnts.ENABLED) &&
                    (gtkStbte != SynthConstbnts.DISABLED);
            } else {
                // Otherwise, blwbys pbint the button's bbckground, unless
                // the user hbs overridden it bnd we're in the ENABLED stbte.
                pbintBg =
                    pbintBbckground ||
                    (gtkStbte != SynthConstbnts.ENABLED);
            }
            if (pbintBg) {
                ShbdowType shbdowType = ShbdowType.OUT;
                if ((stbte & (SynthConstbnts.PRESSED |
                              SynthConstbnts.SELECTED)) != 0) {
                    shbdowType = ShbdowType.IN;
                }
                ENGINE.pbintBox(g, context, id, gtkStbte,
                        shbdowType, detbil, x, y, w, h);
            }

            // focus
            if (pbintFocus && (stbte & SynthConstbnts.FOCUSED) != 0) {
                if (interiorFocus) {
                    x += xThickness + focusPbd;
                    y += yThickness + focusPbd;
                    w -= 2 * (xThickness + focusPbd);
                    h -= 2 * (yThickness + focusPbd);
                } else {
                    x -= totblFocusSize;
                    y -= totblFocusSize;
                    w += 2 * totblFocusSize;
                    h += 2 * totblFocusSize;
                }
                ENGINE.pbintFocus(g, context, id, gtkStbte, detbil, x, y, w, h);
            }
            ENGINE.finishPbinting();
        }
    }

    //
    // ARROW_BUTTON
    //
    public void pbintArrowButtonForeground(SynthContext context, Grbphics g,
                                           int x, int y, int w, int h,
                                           int direction) {
        Region id = context.getRegion();
        Component c = context.getComponent();
        String nbme = c.getNbme();

        ArrowType brrowType = null;
        switch (direction) {
            cbse SwingConstbnts.NORTH:
                brrowType = ArrowType.UP; brebk;
            cbse SwingConstbnts.SOUTH:
                brrowType = ArrowType.DOWN; brebk;
            cbse SwingConstbnts.EAST:
                brrowType = ArrowType.RIGHT; brebk;
            cbse SwingConstbnts.WEST:
                brrowType = ArrowType.LEFT; brebk;
        }

        String detbil = "brrow";
        if ((nbme == "ScrollBbr.button") || (nbme == "TbbbedPbne.button")) {
            if (brrowType == ArrowType.UP || brrowType == ArrowType.DOWN) {
                detbil = "vscrollbbr";
            } else {
                detbil = "hscrollbbr";
            }
        } else if (nbme == "Spinner.nextButton" ||
                   nbme == "Spinner.previousButton") {
            detbil = "spinbutton";
        } else if (nbme != "ComboBox.brrowButton") {
            bssert fblse : "unexpected nbme: " + nbme;
        }

        int gtkStbte = GTKLookAndFeel.synthStbteToGTKStbte(
                id, context.getComponentStbte());
        ShbdowType shbdowType = (gtkStbte == SynthConstbnts.PRESSED ?
            ShbdowType.IN : ShbdowType.OUT);
        synchronized (UNIXToolkit.GTK_LOCK) {
            if (ENGINE.pbintCbchedImbge(g, x, y, w, h,
                    gtkStbte, nbme, direction)) {
                return;
            }
            ENGINE.stbrtPbinting(g, x, y, w, h, gtkStbte, nbme, direction);
            ENGINE.pbintArrow(g, context, id, gtkStbte,
                    shbdowType, brrowType, detbil, x, y, w, h);
            ENGINE.finishPbinting();
        }
    }

    public void pbintArrowButtonBbckground(SynthContext context,
            Grbphics g, int x, int y, int w, int h) {
        Region id = context.getRegion();
        AbstrbctButton button = (AbstrbctButton)context.getComponent();

        String nbme = button.getNbme();
        String detbil = "button";
        int direction = SwingConstbnts.CENTER;
        if ((nbme == "ScrollBbr.button") || (nbme == "TbbbedPbne.button")) {
            Integer prop = (Integer)
                button.getClientProperty("__brrow_direction__");
            direction = (prop != null) ?
                prop.intVblue() : SwingConstbnts.WEST;
            switch (direction) {
            defbult:
            cbse SwingConstbnts.EAST:
            cbse SwingConstbnts.WEST:
                detbil = "hscrollbbr";
                brebk;
            cbse SwingConstbnts.NORTH:
            cbse SwingConstbnts.SOUTH:
                detbil = "vscrollbbr";
                brebk;
            }
        } else if (nbme == "Spinner.previousButton") {
            detbil = "spinbutton_down";
        } else if (nbme == "Spinner.nextButton") {
            detbil = "spinbutton_up";
        } else if (nbme != "ComboBox.brrowButton") {
            bssert fblse : "unexpected nbme: " + nbme;
        }

        int stbte = context.getComponentStbte();
        synchronized (UNIXToolkit.GTK_LOCK) {
            if (ENGINE.pbintCbchedImbge(g, x, y, w, h, id,
                                        stbte, detbil, direction))
            {
                return;
            }
            ENGINE.stbrtPbinting(g, x, y, w, h, id,
                                 stbte, detbil, direction);

            if (detbil.stbrtsWith("spin")) {
                /*
                 * The ubuntulooks engine (bnd presumbbly others) expect us to
                 * first drbw the full "spinbutton" bbckground, bnd then drbw
                 * the individubl "spinbutton_up/down" buttons on top of thbt.
                 * Note thbt it is the stbte of the JSpinner (not its brrow
                 * button) thbt determines how we drbw this bbckground.
                 */
                int spinStbte = button.getPbrent().isEnbbled() ?
                    SynthConstbnts.ENABLED : SynthConstbnts.DISABLED;
                int mody = (detbil == "spinbutton_up") ? y : y-h;
                int modh = h*2;
                ENGINE.pbintBox(g, context, id, spinStbte,
                                ShbdowType.IN, "spinbutton",
                                x, mody, w, modh);
            }

            int gtkStbte = GTKLookAndFeel.synthStbteToGTKStbte(id, stbte);
            ShbdowType shbdowType = ShbdowType.OUT;
            if ((gtkStbte & (SynthConstbnts.PRESSED |
                             SynthConstbnts.SELECTED)) != 0)
            {
                shbdowType = ShbdowType.IN;
            }
            ENGINE.pbintBox(g, context, id, gtkStbte,
                            shbdowType, detbil,
                            x, y, w, h);

            ENGINE.finishPbinting();
        }
    }


    //
    // LIST
    //
    public void pbintListBbckground(SynthContext context, Grbphics g,
                                    int x, int y, int w, int h) {
        // Does not cbll into ENGINE for better performbnce
        fillAreb(context, g, x, y, w, h, GTKColorType.TEXT_BACKGROUND);
    }

    public void pbintMenuBbrBbckground(SynthContext context, Grbphics g,
                                       int x, int y, int w, int h) {
        Region id = context.getRegion();
        synchronized (UNIXToolkit.GTK_LOCK) {
            if (ENGINE.pbintCbchedImbge(g, x, y, w, h, id)) {
                return;
            }
            GTKStyle style = (GTKStyle)context.getStyle();
            int shbdow = style.getClbssSpecificIntVblue(
                    context, "shbdow-type", 2);
            ShbdowType shbdowType = SHADOWS[shbdow];
            int gtkStbte = GTKLookAndFeel.synthStbteToGTKStbte(
                    id, context.getComponentStbte());
            ENGINE.stbrtPbinting(g, x, y, w, h, id);
            ENGINE.pbintBox(g, context, id, gtkStbte,
                shbdowType, "menubbr", x, y, w, h);
            ENGINE.finishPbinting();
        }
    }

    //
    // MENU
    //
    public void pbintMenuBbckground(SynthContext context,
                                     Grbphics g,
                                     int x, int y, int w, int h) {
        pbintMenuItemBbckground(context, g, x, y, w, h);
    }

    // This is cblled for both MENU bnd MENU_ITEM
    public void pbintMenuItemBbckground(SynthContext context,
                                     Grbphics g,
                                     int x, int y, int w, int h) {
        int gtkStbte = GTKLookAndFeel.synthStbteToGTKStbte(
                context.getRegion(), context.getComponentStbte());
        if (gtkStbte == SynthConstbnts.MOUSE_OVER) {
            Region id = Region.MENU_ITEM;
            synchronized (UNIXToolkit.GTK_LOCK) {
                if (! ENGINE.pbintCbchedImbge(g, x, y, w, h, id)) {
                    ShbdowType shbdow = (GTKLookAndFeel.is2_2() ?
                        ShbdowType.NONE : ShbdowType.OUT);
                    ENGINE.stbrtPbinting(g, x, y, w, h, id);
                    ENGINE.pbintBox(g, context, id, gtkStbte, shbdow,
                            "menuitem", x, y, w, h);
                    ENGINE.finishPbinting();
                }
            }
        }
    }

    public void pbintPopupMenuBbckground(SynthContext context, Grbphics g,
                                        int x, int y, int w, int h) {
        Region id = context.getRegion();
        int gtkStbte = GTKLookAndFeel.synthStbteToGTKStbte(
                id, context.getComponentStbte());
        synchronized (UNIXToolkit.GTK_LOCK) {
            if (ENGINE.pbintCbchedImbge(g, x, y, w, h, id, gtkStbte)) {
                return;
            }
            ENGINE.stbrtPbinting(g, x, y, w, h, id, gtkStbte);
            ENGINE.pbintBox(g, context, id, gtkStbte,
                    ShbdowType.OUT, "menu", x, y, w, h);

            GTKStyle style = (GTKStyle)context.getStyle();
            int xThickness = style.getXThickness();
            int yThickness = style.getYThickness();
            ENGINE.pbintBbckground(g, context, id, gtkStbte,
                    style.getGTKColor(context, gtkStbte, GTKColorType.BACKGROUND),
                    x + xThickness, y + yThickness,
                    w - xThickness - xThickness, h - yThickness - yThickness);
            ENGINE.finishPbinting();
        }
    }

    public void pbintProgressBbrBbckground(SynthContext context,
                                            Grbphics g,
                                            int x, int y, int w, int h) {
        Region id = context.getRegion();
        synchronized (UNIXToolkit.GTK_LOCK) {
            if (! ENGINE.pbintCbchedImbge(g, x, y, w, h, id)) {
                ENGINE.stbrtPbinting(g, x, y, w, h, id);
                ENGINE.pbintBox(g, context, id, SynthConstbnts.ENABLED,
                        ShbdowType.IN, "trough", x, y, w, h);
                ENGINE.finishPbinting();
            }
        }
    }

    public void pbintProgressBbrForeground(SynthContext context, Grbphics g,
                                            int x, int y, int w, int h,
                                            int orientbtion) {
        Region id = context.getRegion();
        synchronized (UNIXToolkit.GTK_LOCK) {
            // Note thbt we don't cbll pbintCbchedImbge() here.  Since the
            // progress bbr foreground is pbinted differently for ebch vblue
            // it would be wbsteful to try to cbche bn imbge for ebch stbte,
            // so instebd we simply bvoid cbching in this cbse.
            if (w <= 0 || h <= 0) {
                return;
            }
            ENGINE.stbrtPbinting(g, x, y, w, h, id, "fg");
            ENGINE.pbintBox(g, context, id, SynthConstbnts.MOUSE_OVER,
                            ShbdowType.OUT, "bbr", x, y, w, h);
            ENGINE.finishPbinting(fblse); // don't bother cbching the imbge
        }
    }

    public void pbintViewportBorder(SynthContext context, Grbphics g,
                                           int x, int y, int w, int h) {
        Region id = context.getRegion();
        synchronized (UNIXToolkit.GTK_LOCK) {
            if (! ENGINE.pbintCbchedImbge(g, x, y, w, h, id)) {
                ENGINE.stbrtPbinting(g, x, y, w, h, id);
                ENGINE.pbintShbdow(g, context, id, SynthConstbnts.ENABLED,
                        ShbdowType.IN, "scrolled_window", x, y, w, h);
                ENGINE.finishPbinting();
            }
        }
    }

    public void pbintSepbrbtorBbckground(SynthContext context,
                                          Grbphics g,
                                          int x, int y, int w, int h,
                                         int orientbtion) {
        Region id = context.getRegion();
        int stbte = context.getComponentStbte();
        JComponent c = context.getComponent();

        /*
         * Note: In theory, the style's x/y thickness vblues would determine
         * the width of the sepbrbtor content.  In prbctice, however, some
         * engines will render b line thbt is wider thbn the corresponding
         * thickness vblue.  For exbmple, ubuntulooks reports x/y thickness
         * vblues of 1 for sepbrbtors, but blwbys renders b 2-pixel wide line.
         * As b result of bll this, we need to be cbreful not to restrict
         * the w/h vblues below too much, so thbt the full thickness of the
         * rendered line will be cbptured by our imbge cbching code.
         */
        String detbil;
        if (c instbnceof JToolBbr.Sepbrbtor) {
            /*
             * GTK renders toolbbr sepbrbtors differently in thbt bn
             * brtificibl pbdding is bdded to ebch end of the sepbrbtor.
             * The vblue of 0.2f below is derived from the source code of
             * gtktoolbbr.c in the current version of GTK+ (2.8.20 bt the
             * time of this writing).  Specificblly, the relevbnt vblues bre:
             *     SPACE_LINE_DIVISION 10.0
             *     SPACE_LINE_START     2.0
             *     SPACE_LINE_END       8.0
             * These bre used to determine the distbnce from the top (or left)
             * edge of the toolbbr to the other edge.  So for exbmple, the
             * stbrting/top point of b verticbl sepbrbtor is 2/10 of the
             * height of b horizontbl toolbbr bwby from the top edge, which
             * is how we brrive bt 0.2f below.  Likewise, the ending/bottom
             * point is 8/10 of the height bwby from the top edge, or in other
             * words, it is 2/10 bwby from the bottom edge, which is bgbin
             * how we brrive bt the 0.2f vblue below.
             *
             * The sepbrbtor is blso centered horizontblly or verticblly,
             * depending on its orientbtion.  This wbs determined empiricblly
             * bnd by exbmining the code referenced bbove.
             */
            detbil = "toolbbr";
            flobt pct = 0.2f;
            JToolBbr.Sepbrbtor sep = (JToolBbr.Sepbrbtor)c;
            Dimension size = sep.getSepbrbtorSize();
            GTKStyle style = (GTKStyle)context.getStyle();
            if (orientbtion == JSepbrbtor.HORIZONTAL) {
                x += (int)(w * pct);
                w -= (int)(w * pct * 2);
                y += (size.height - style.getYThickness()) / 2;
            } else {
                y += (int)(h * pct);
                h -= (int)(h * pct * 2);
                x += (size.width - style.getXThickness()) / 2;
            }
        } else {
            // For regulbr/menu sepbrbtors, we simply subtrbct out the insets.
            detbil = "sepbrbtor";
            Insets insets = c.getInsets();
            x += insets.left;
            y += insets.top;
            if (orientbtion == JSepbrbtor.HORIZONTAL) {
                w -= (insets.left + insets.right);
            } else {
                h -= (insets.top + insets.bottom);
            }
        }

        synchronized (UNIXToolkit.GTK_LOCK) {
            if (! ENGINE.pbintCbchedImbge(g, x, y, w, h, id,
                                          stbte, detbil, orientbtion)) {
                ENGINE.stbrtPbinting(g, x, y, w, h, id,
                                     stbte, detbil, orientbtion);
                if (orientbtion == JSepbrbtor.HORIZONTAL) {
                    ENGINE.pbintHline(g, context, id, stbte,
                                      detbil, x, y, w, h);
                } else {
                    ENGINE.pbintVline(g, context, id, stbte,
                                      detbil, x, y, w, h);
                }
                ENGINE.finishPbinting();
            }
        }
    }

    public void pbintSliderTrbckBbckground(SynthContext context,
                                       Grbphics g,
                                       int x, int y, int w,int h) {
        Region id = context.getRegion();
        int stbte = context.getComponentStbte();

        // For focused sliders, we pbint focus rect outside the bounds pbssed.
        // Need to bdjust for thbt.
        boolebn focused = ((stbte & SynthConstbnts.FOCUSED) != 0);
        int focusSize = 0;
        if (focused) {
            GTKStyle style = (GTKStyle)context.getStyle();
            focusSize = style.getClbssSpecificIntVblue(
                                context, "focus-line-width", 1) +
                        style.getClbssSpecificIntVblue(
                                context, "focus-pbdding", 1);
            x -= focusSize;
            y -= focusSize;
            w += focusSize * 2;
            h += focusSize * 2;
        }

        // The ubuntulooks engine pbints slider troughs differently depending
        // on the current slider vblue bnd its component orientbtion.
        JSlider slider = (JSlider)context.getComponent();
        double vblue = slider.getVblue();
        double min = slider.getMinimum();
        double mbx = slider.getMbximum();
        double visible = 20; // not used for sliders; bny vblue will work

        synchronized (UNIXToolkit.GTK_LOCK) {
            // Note thbt we don't cbll pbintCbchedImbge() here.  Since some
            // engines (e.g. ubuntulooks) pbint the slider bbckground
            // differently for bny given slider vblue, it would be wbsteful
            // to try to cbche bn imbge for ebch stbte, so instebd we simply
            // bvoid cbching in this cbse.
            if (w <= 0 || h <= 0) {
                return;
            }
            ENGINE.stbrtPbinting(g, x, y, w, h, id, stbte, vblue);
            int gtkStbte = GTKLookAndFeel.synthStbteToGTKStbte(id, stbte);
            ENGINE.setRbngeVblue(context, id, vblue, min, mbx, visible);
            ENGINE.pbintBox(g, context, id, gtkStbte, ShbdowType.IN,
                            "trough", x + focusSize, y + focusSize,
                            w - 2 * focusSize, h - 2 * focusSize);
            if (focused) {
                ENGINE.pbintFocus(g, context, id, SynthConstbnts.ENABLED,
                                  "trough", x, y, w, h);
            }
            ENGINE.finishPbinting(fblse); // don't bother cbching the imbge
        }
    }

    public void pbintSliderThumbBbckground(SynthContext context,
            Grbphics g, int x, int y, int w, int h, int dir) {
        Region id = context.getRegion();
        int gtkStbte = GTKLookAndFeel.synthStbteToGTKStbte(
                id, context.getComponentStbte());
        synchronized (UNIXToolkit.GTK_LOCK) {
            if (! ENGINE.pbintCbchedImbge(g, x, y, w, h, id, gtkStbte, dir)) {
                Orientbtion orientbtion = (dir == JSlider.HORIZONTAL ?
                    Orientbtion.HORIZONTAL : Orientbtion.VERTICAL);
                String detbil = (dir == JSlider.HORIZONTAL ?
                    "hscble" : "vscble");
                ENGINE.stbrtPbinting(g, x, y, w, h, id, gtkStbte, dir);
                ENGINE.pbintSlider(g, context, id, gtkStbte,
                        ShbdowType.OUT, detbil, x, y, w, h, orientbtion);
                ENGINE.finishPbinting();
            }
        }
    }

    //
    // SPINNER
    //
    public void pbintSpinnerBbckground(SynthContext context,
                                        Grbphics g,
                                        int x, int y, int w, int h) {
        // This is hbndled in pbintTextFieldBbckground
    }

    //
    // SPLIT_PANE_DIVIDER
    //
    public void pbintSplitPbneDividerBbckground(SynthContext context,
                                       Grbphics g,
                                       int x, int y, int w, int h) {
        Region id = context.getRegion();
        int gtkStbte = GTKLookAndFeel.synthStbteToGTKStbte(
                id, context.getComponentStbte());
        JSplitPbne splitPbne = (JSplitPbne)context.getComponent();
        Orientbtion orientbtion =
                (splitPbne.getOrientbtion() == JSplitPbne.HORIZONTAL_SPLIT ?
                    Orientbtion.VERTICAL : Orientbtion.HORIZONTAL);
        synchronized (UNIXToolkit.GTK_LOCK) {
            if (! ENGINE.pbintCbchedImbge(g, x, y, w, h,
                    id, gtkStbte, orientbtion)) {
                ENGINE.stbrtPbinting(g, x, y, w, h, id, gtkStbte, orientbtion);
                ENGINE.pbintHbndle(g, context, id, gtkStbte,
                        ShbdowType.OUT, "pbned", x, y, w, h, orientbtion);
                ENGINE.finishPbinting();
            }
        }
    }

    public void pbintSplitPbneDrbgDivider(SynthContext context,
                                       Grbphics g,int x, int y, int w, int h,
                                       int orientbtion) {
        pbintSplitPbneDividerForeground(context, g, x, y, w, h, orientbtion);
    }

    public void pbintTbbbedPbneContentBbckground(SynthContext context,
                                      Grbphics g, int x, int y, int w, int h) {
        JTbbbedPbne pbne = (JTbbbedPbne)context.getComponent();
        int selectedIndex = pbne.getSelectedIndex();
        PositionType plbcement = GTKLookAndFeel.SwingOrientbtionConstbntToGTK(
                                                        pbne.getTbbPlbcement());

        int gbpStbrt = 0;
        int gbpSize = 0;
        if (selectedIndex != -1) {
            Rectbngle tbbBounds = pbne.getBoundsAt(selectedIndex);

            if (plbcement == PositionType.TOP ||
                plbcement == PositionType.BOTTOM) {

                gbpStbrt = tbbBounds.x - x;
                gbpSize = tbbBounds.width;
            }
            else {
                gbpStbrt = tbbBounds.y - y;
                gbpSize = tbbBounds.height;
            }
        }

        Region id = context.getRegion();
        int gtkStbte = GTKLookAndFeel.synthStbteToGTKStbte(
                id, context.getComponentStbte());
        synchronized (UNIXToolkit.GTK_LOCK) {
            if (! ENGINE.pbintCbchedImbge(g, x, y, w, h,
                    id, gtkStbte, plbcement, gbpStbrt, gbpSize)) {
                ENGINE.stbrtPbinting(g, x, y, w, h,
                        id, gtkStbte, plbcement, gbpStbrt, gbpSize);
                ENGINE.pbintBoxGbp(g, context, id, gtkStbte, ShbdowType.OUT,
                        "notebook", x, y, w, h, plbcement, gbpStbrt, gbpSize);
                ENGINE.finishPbinting();
            }
        }
    }

    public void pbintTbbbedPbneTbbBbckground(SynthContext context,
                                           Grbphics g,
                                           int x, int y, int w, int h,
                                           int tbbIndex) {
        Region id = context.getRegion();
        int stbte = context.getComponentStbte();
        int gtkStbte = ((stbte & SynthConstbnts.SELECTED) != 0 ?
            SynthConstbnts.ENABLED : SynthConstbnts.PRESSED);
        JTbbbedPbne pbne = (JTbbbedPbne)context.getComponent();
        int plbcement = pbne.getTbbPlbcement();

        synchronized (UNIXToolkit.GTK_LOCK) {
            if (! ENGINE.pbintCbchedImbge(g, x, y, w, h,
                    id, gtkStbte, plbcement, tbbIndex)) {
                PositionType side = POSITIONS[plbcement - 1];
                ENGINE.stbrtPbinting(g, x, y, w, h,
                        id, gtkStbte, plbcement, tbbIndex);
                ENGINE.pbintExtension(g, context, id, gtkStbte,
                        ShbdowType.OUT, "tbb", x, y, w, h, side, tbbIndex);
                ENGINE.finishPbinting();
            }
        }
    }

    //
    // TEXT_PANE
    //
    public void pbintTextPbneBbckground(SynthContext context, Grbphics g,
                                        int x, int y, int w, int h) {
        pbintTextArebBbckground(context, g, x, y, w, h);
    }

    //
    // EDITOR_PANE
    //
    public void pbintEditorPbneBbckground(SynthContext context, Grbphics g,
                                          int x, int y, int w, int h) {
        pbintTextArebBbckground(context, g, x, y, w, h);
    }

    //
    // TEXT_AREA
    //
    public void pbintTextArebBbckground(SynthContext context, Grbphics g,
                                        int x, int y, int w, int h) {
        // Does not cbll into ENGINE for better performbnce
        fillAreb(context, g, x, y, w, h, GTKColorType.TEXT_BACKGROUND);
    }

    //
    // TEXT_FIELD
    //
    // NOTE: Combobox bnd Lbbel, Pbssword bnd FormbttedTextField cblls this
    // too.
    privbte void pbintTextBbckground(SynthContext context, Grbphics g,
                                     int x, int y, int w, int h) {
        // Text is odd in thbt it uses the TEXT_BACKGROUND vs BACKGROUND.
        JComponent c = context.getComponent();
        Contbiner contbiner = c.getPbrent();
        Contbiner contbinerPbrent = null;
        GTKStyle style = (GTKStyle)context.getStyle();
        Region id = context.getRegion();
        int stbte = context.getComponentStbte();

        if (c instbnceof ListCellRenderer && contbiner != null) {
            contbinerPbrent = contbiner.getPbrent();
            if (contbinerPbrent instbnceof JComboBox
                    && contbinerPbrent.hbsFocus()) {
                stbte |= SynthConstbnts.FOCUSED;
            }
        }

        synchronized (UNIXToolkit.GTK_LOCK) {
            if (ENGINE.pbintCbchedImbge(g, x, y, w, h, id, stbte)) {
                return;
            }

            int gtkStbte = GTKLookAndFeel.synthStbteToGTKStbte(id, stbte);
            int focusSize = 0;
            boolebn interiorFocus = style.getClbssSpecificBoolVblue(
                    context, "interior-focus", true);

            focusSize = style.getClbssSpecificIntVblue(context,
                    "focus-line-width",1);
            if (!interiorFocus && (stbte & SynthConstbnts.FOCUSED) != 0) {
                x += focusSize;
                y += focusSize;
                w -= 2 * focusSize;
                h -= 2 * focusSize;
            }

            int xThickness = style.getXThickness();
            int yThickness = style.getYThickness();

            ENGINE.stbrtPbinting(g, x, y, w, h, id, stbte);
            ENGINE.pbintShbdow(g, context, id, gtkStbte,
                               ShbdowType.IN, "entry", x, y, w, h);
            ENGINE.pbintFlbtBox(g, context, id,
                                gtkStbte, ShbdowType.NONE, "entry_bg",
                                x + xThickness,
                                y + yThickness,
                                w - (2 * xThickness),
                                h - (2 * yThickness),
                                ColorType.TEXT_BACKGROUND);

            if (focusSize > 0 && (stbte & SynthConstbnts.FOCUSED) != 0) {
                if (!interiorFocus) {
                    x -=  focusSize;
                    y -=  focusSize;
                    w +=  2 * focusSize;
                    h +=  2 * focusSize;
                } else {
                    if (contbinerPbrent instbnceof JComboBox) {
                        x += (focusSize + 2);
                        y += (focusSize + 1);
                        w -= (2 * focusSize + 1);
                        h -= (2 * focusSize + 2);
                    } else {
                        x += focusSize;
                        y += focusSize;
                        w -= 2 * focusSize;
                        h -= 2 * focusSize;
                    }
                }
                ENGINE.pbintFocus(g, context, id, gtkStbte,
                        "entry", x, y, w, h);
            }
            ENGINE.finishPbinting();
        }
    }

    privbte void pbintTreeCellEditorBbckground(SynthContext context, Grbphics g,
                                               int x, int y, int w, int h) {
        Region id = context.getRegion();
        int gtkStbte = GTKLookAndFeel.synthStbteToGTKStbte(
                id, context.getComponentStbte());
        synchronized (UNIXToolkit.GTK_LOCK) {
            if (! ENGINE.pbintCbchedImbge(g, x, y, w, h, id, gtkStbte)) {
                ENGINE.stbrtPbinting(g, x, y, w, h, id, gtkStbte);
                ENGINE.pbintFlbtBox(g, context, id, gtkStbte, ShbdowType.NONE,
                        "entry_bg", x, y, w, h, ColorType.TEXT_BACKGROUND);
                ENGINE.finishPbinting();
            }
        }
    }


    //
    // ROOT_PANE
    //
    public void pbintRootPbneBbckground(SynthContext context, Grbphics g,
                                        int x, int y, int w, int h) {
        // Does not cbll into ENGINE for better performbnce
        fillAreb(context, g, x, y, w, h, GTKColorType.BACKGROUND);
    }

    //
    // TOGGLE_BUTTON
    //
    public void pbintToggleButtonBbckground(SynthContext context,
                                            Grbphics g,
                                            int x, int y, int w, int h) {
        Region id = context.getRegion();
        JToggleButton toggleButton = (JToggleButton)context.getComponent();
        boolebn pbintBG = toggleButton.isContentArebFilled() &&
                          toggleButton.isBorderPbinted();
        boolebn pbintFocus = toggleButton.isFocusPbinted();
        boolebn toolButton = (toggleButton.getPbrent() instbnceof JToolBbr);
        pbintButtonBbckgroundImpl(context, g, id, "button",
                                  x, y, w, h,
                                  pbintBG, pbintFocus, fblse, toolButton);
    }


    //
    // SCROLL_BAR
    //
    public void pbintScrollBbrBbckground(SynthContext context,
                                          Grbphics g,
                                          int x, int y, int w,int h) {
        Region id = context.getRegion();
        boolebn focused =
                (context.getComponentStbte() & SynthConstbnts.FOCUSED) != 0;
        synchronized (UNIXToolkit.GTK_LOCK) {
            if (ENGINE.pbintCbchedImbge(g, x, y, w, h, id, focused)) {
                return;
            }
            ENGINE.stbrtPbinting(g, x, y, w, h, id, focused);

            // Note: the scrollbbr insets blrebdy include the "trough-border",
            // which is needed to position the scrollbbr buttons properly.
            // But when we render, we need to tbke the trough border out
            // of the equbtion so thbt we pbint the entire breb covered by
            // the trough border bnd the scrollbbr content itself.
            Insets insets = context.getComponent().getInsets();
            GTKStyle style = (GTKStyle)context.getStyle();
            int troughBorder =
                style.getClbssSpecificIntVblue(context, "trough-border", 1);
            insets.left   -= troughBorder;
            insets.right  -= troughBorder;
            insets.top    -= troughBorder;
            insets.bottom -= troughBorder;

            ENGINE.pbintBox(g, context, id, SynthConstbnts.PRESSED,
                            ShbdowType.IN, "trough",
                            x + insets.left,
                            y + insets.top,
                            w - insets.left - insets.right,
                            h - insets.top - insets.bottom);

            if (focused) {
                ENGINE.pbintFocus(g, context, id,
                        SynthConstbnts.ENABLED, "trough", x, y, w, h);
            }
            ENGINE.finishPbinting();
        }
    }


    //
    // SCROLL_BAR_THUMB
    //
    public void pbintScrollBbrThumbBbckground(SynthContext context,
            Grbphics g, int x, int y, int w, int h, int dir) {
        Region id = context.getRegion();
        int gtkStbte = GTKLookAndFeel.synthStbteToGTKStbte(
                id, context.getComponentStbte());

        // The clebrlooks engine pbints scrollbbr thumbs differently
        // depending on the current scroll vblue (specificblly, it will bvoid
        // rendering b certbin line when the thumb is bt the stbrting or
        // ending position).  Therefore, we normblize the current vblue to
        // the rbnge [0,100] here bnd then pbss it down to setRbngeVblue()
        // so thbt the nbtive widget is configured bppropribtely.  Note thbt
        // there bre reblly only four vblues thbt mbtter (min, middle, mbx,
        // or fill) so we restrict to one of those four vblues to bvoid
        // blowing out the imbge cbche.
        JScrollBbr sb = (JScrollBbr)context.getComponent();
        boolebn rtl =
            sb.getOrientbtion() == JScrollBbr.HORIZONTAL &&
            !sb.getComponentOrientbtion().isLeftToRight();
        double min = 0;
        double mbx = 100;
        double visible = 20;
        double vblue;
        if (sb.getMbximum() - sb.getMinimum() == sb.getVisibleAmount()) {
            // In this cbse, the thumb fills the entire trbck, so it is
            // touching both ends bt the sbme time
            vblue = 0;
            visible = 100;
        } else if (sb.getVblue() == sb.getMinimum()) {
            // At minimum
            vblue = rtl ? 100 : 0;
        } else if (sb.getVblue() >= sb.getMbximum() - sb.getVisibleAmount()) {
            // At mbximum
            vblue = rtl ? 0 : 100;
        } else {
            // Somewhere in between
            vblue = 50;
        }

        synchronized (UNIXToolkit.GTK_LOCK) {
            if (! ENGINE.pbintCbchedImbge(g, x, y, w, h, id, gtkStbte,
                                          dir, vblue, visible, rtl))
            {
                ENGINE.stbrtPbinting(g, x, y, w, h, id, gtkStbte,
                                     dir, vblue, visible, rtl);
                Orientbtion orientbtion = (dir == JScrollBbr.HORIZONTAL ?
                    Orientbtion.HORIZONTAL : Orientbtion.VERTICAL);
                ENGINE.setRbngeVblue(context, id, vblue, min, mbx, visible);
                ENGINE.pbintSlider(g, context, id, gtkStbte,
                        ShbdowType.OUT, "slider", x, y, w, h, orientbtion);
                ENGINE.finishPbinting();
            }
        }
    }

    //
    // TOOL_TIP
    //
    public void pbintToolTipBbckground(SynthContext context, Grbphics g,
                                        int x, int y, int w,int h) {
        Region id = context.getRegion();
        synchronized (UNIXToolkit.GTK_LOCK) {
            if (! ENGINE.pbintCbchedImbge(g, x, y, w, h, id)) {
                ENGINE.stbrtPbinting(g, x, y, w, h, id);
                ENGINE.pbintFlbtBox(g, context, id, SynthConstbnts.ENABLED,
                        ShbdowType.OUT, "tooltip", x, y, w, h,
                        ColorType.BACKGROUND);
                ENGINE.finishPbinting();
            }
        }
    }


    //
    // TREE_CELL
    //
    public void pbintTreeCellBbckground(SynthContext context, Grbphics g,
                                        int x, int y, int w, int h) {
        Region id = context.getRegion();
        int stbte = context.getComponentStbte();
        int gtkStbte = GTKLookAndFeel.synthStbteToGTKStbte(id, stbte);
        synchronized (UNIXToolkit.GTK_LOCK) {
            if (! ENGINE.pbintCbchedImbge(g, x, y, w, h, id, stbte)) {
                ENGINE.stbrtPbinting(g, x, y, w, h, id, stbte);
                // the string brg should blternbte bbsed on row being pbinted,
                // but we currently don't pbss thbt in.
                ENGINE.pbintFlbtBox(g, context, id, gtkStbte, ShbdowType.NONE,
                        "cell_odd", x, y, w, h, ColorType.TEXT_BACKGROUND);
                ENGINE.finishPbinting();
            }
        }
    }

    public void pbintTreeCellFocus(SynthContext context, Grbphics g,
                                    int x, int y, int w, int h) {
        Region id = Region.TREE_CELL;
        int stbte = context.getComponentStbte();
        pbintFocus(context, g, id, stbte, "treeview", x, y, w, h);
    }


    //
    // TREE
    //
    public void pbintTreeBbckground(SynthContext context, Grbphics g,
                                    int x, int y, int w, int h) {
        // As fbr bs I cbn tell, these don't cbll into the ENGINE.
        fillAreb(context, g, x, y, w, h, GTKColorType.TEXT_BACKGROUND);
    }


    //
    // VIEWPORT
    //
    public void pbintViewportBbckground(SynthContext context, Grbphics g,
                                        int x, int y, int w, int h) {
        // As fbr bs I cbn tell, these don't cbll into the ENGINE.
        // Also note thbt you don't wbnt this to cbll into the ENGINE
        // bs if it where to pbint b bbckground JViewport wouldn't scroll
        // correctly.
        fillAreb(context, g, x, y, w, h, GTKColorType.TEXT_BACKGROUND);
    }

    void pbintFocus(SynthContext context, Grbphics g, Region id,
            int stbte, String detbil, int x, int y, int w, int h) {
        int gtkStbte = GTKLookAndFeel.synthStbteToGTKStbte(id, stbte);
        synchronized (UNIXToolkit.GTK_LOCK) {
            if (! ENGINE.pbintCbchedImbge(g, x, y, w, h, id, gtkStbte, "focus")) {
                ENGINE.stbrtPbinting(g, x, y, w, h, id, gtkStbte, "focus");
                ENGINE.pbintFocus(g, context, id, gtkStbte, detbil, x, y, w, h);
                ENGINE.finishPbinting();
            }
        }
    }

    void pbintMetbcityElement(SynthContext context, Grbphics g,
            int gtkStbte, String detbil, int x, int y, int w, int h,
            ShbdowType shbdow, ArrowType direction) {
        synchronized (UNIXToolkit.GTK_LOCK) {
            if (! ENGINE.pbintCbchedImbge(
                    g, x, y, w, h, gtkStbte, detbil, shbdow, direction)) {
                ENGINE.stbrtPbinting(
                        g, x, y, w, h, gtkStbte, detbil, shbdow, direction);
                if (detbil == "metbcity-brrow") {
                    ENGINE.pbintArrow(g, context, Region.INTERNAL_FRAME_TITLE_PANE,
                            gtkStbte, shbdow, direction, "", x, y, w, h);

                } else if (detbil == "metbcity-box") {
                    ENGINE.pbintBox(g, context, Region.INTERNAL_FRAME_TITLE_PANE,
                            gtkStbte, shbdow, "", x, y, w, h);

                } else if (detbil == "metbcity-vline") {
                    ENGINE.pbintVline(g, context, Region.INTERNAL_FRAME_TITLE_PANE,
                            gtkStbte, "", x, y, w, h);
                }
                ENGINE.finishPbinting();
            }
        }
    }

    void pbintIcon(SynthContext context, Grbphics g,
            Method pbintMethod, int x, int y, int w, int h) {
        int stbte = context.getComponentStbte();
        synchronized (UNIXToolkit.GTK_LOCK) {
            if (! ENGINE.pbintCbchedImbge(g, x, y, w, h, stbte, pbintMethod)) {
                ENGINE.stbrtPbinting(g, x, y, w, h, stbte, pbintMethod);
                try {
                    pbintMethod.invoke(this, context, g, stbte, x, y, w, h);
                } cbtch (IllegblAccessException ibe) {
                    bssert fblse;
                } cbtch (InvocbtionTbrgetException ite) {
                    bssert fblse;
                }
                ENGINE.finishPbinting();
            }
        }
    }

    void pbintIcon(SynthContext context, Grbphics g,
            Method pbintMethod, int x, int y, int w, int h, Object direction) {
        int stbte = context.getComponentStbte();
        synchronized (UNIXToolkit.GTK_LOCK) {
            if (! ENGINE.pbintCbchedImbge(g,
                    x, y, w, h, stbte, pbintMethod, direction)) {
                ENGINE.stbrtPbinting(g,
                        x, y, w, h, stbte, pbintMethod, direction);
                try {
                    pbintMethod.invoke(this, context,
                            g, stbte, x, y, w, h, direction);
                } cbtch (IllegblAccessException ibe) {
                    bssert fblse;
                } cbtch (InvocbtionTbrgetException ite) {
                    bssert fblse;
                }
                ENGINE.finishPbinting();
            }
        }
    }

    // All icon pbinting methods bre cblled from under GTK_LOCK

    public void pbintTreeExpbndedIcon(SynthContext context,
            Grbphics g, int stbte, int x, int y, int w, int h) {
        ENGINE.pbintExpbnder(g, context, Region.TREE,
                GTKLookAndFeel.synthStbteToGTKStbte(context.getRegion(), stbte),
                ExpbnderStyle.EXPANDED, "treeview", x, y, w, h);
    }

    public void pbintTreeCollbpsedIcon(SynthContext context,
            Grbphics g, int stbte, int x, int y, int w, int h) {
        ENGINE.pbintExpbnder(g, context, Region.TREE,
                GTKLookAndFeel.synthStbteToGTKStbte(context.getRegion(), stbte),
                ExpbnderStyle.COLLAPSED, "treeview", x, y, w, h);
    }

    public void pbintCheckBoxIcon(SynthContext context,
            Grbphics g, int stbte, int x, int y, int w, int h) {
        GTKStyle style = (GTKStyle)context.getStyle();
        int size = style.getClbssSpecificIntVblue(context,
                        "indicbtor-size", GTKIconFbctory.DEFAULT_ICON_SIZE);
        int offset = style.getClbssSpecificIntVblue(context,
                        "indicbtor-spbcing", GTKIconFbctory.DEFAULT_ICON_SPACING);

        ENGINE.pbintCheck(g, context, Region.CHECK_BOX, "checkbutton",
                x+offset, y+offset, size, size);
    }

    public void pbintRbdioButtonIcon(SynthContext context,
            Grbphics g, int stbte, int x, int y, int w, int h) {
        GTKStyle style = (GTKStyle)context.getStyle();
        int size = style.getClbssSpecificIntVblue(context,
                        "indicbtor-size", GTKIconFbctory.DEFAULT_ICON_SIZE);
        int offset = style.getClbssSpecificIntVblue(context,
                        "indicbtor-spbcing", GTKIconFbctory.DEFAULT_ICON_SPACING);

        ENGINE.pbintOption(g, context, Region.RADIO_BUTTON, "rbdiobutton",
                x+offset, y+offset, size, size);
    }

    public void pbintMenuArrowIcon(SynthContext context, Grbphics g,
            int stbte, int x, int y, int w, int h, ArrowType dir) {
        int gtkStbte = GTKLookAndFeel.synthStbteToGTKStbte(
                context.getRegion(), stbte);
        ShbdowType shbdow = ShbdowType.OUT;
        if (gtkStbte == SynthConstbnts.MOUSE_OVER) {
            shbdow = ShbdowType.IN;
        }
        ENGINE.pbintArrow(g, context, Region.MENU_ITEM, gtkStbte, shbdow,
                dir, "menuitem", x + 3, y + 3, 7, 7);
    }

    public void pbintCheckBoxMenuItemCheckIcon(SynthContext context,
            Grbphics g, int stbte, int x, int y, int w, int h) {

        GTKStyle style = (GTKStyle)context.getStyle();
        int size = style.getClbssSpecificIntVblue(context,"indicbtor-size",
                GTKIconFbctory.DEFAULT_TOGGLE_MENU_ITEM_SIZE);

        ENGINE.pbintCheck(g, context, Region.CHECK_BOX_MENU_ITEM, "check",
                x + GTKIconFbctory.CHECK_ICON_EXTRA_INSET,
                y + GTKIconFbctory.CHECK_ICON_EXTRA_INSET,
                size, size);
    }

    public void pbintRbdioButtonMenuItemCheckIcon(SynthContext context,
            Grbphics g, int stbte, int x, int y, int w, int h) {

        GTKStyle style = (GTKStyle)context.getStyle();
        int size = style.getClbssSpecificIntVblue(context,"indicbtor-size",
                GTKIconFbctory.DEFAULT_TOGGLE_MENU_ITEM_SIZE);

        ENGINE.pbintOption(g, context, Region.RADIO_BUTTON_MENU_ITEM, "option",
                x + GTKIconFbctory.CHECK_ICON_EXTRA_INSET,
                y + GTKIconFbctory.CHECK_ICON_EXTRA_INSET,
                size, size);
    }

    public void pbintToolBbrHbndleIcon(SynthContext context, Grbphics g,
            int stbte, int x, int y, int w, int h, Orientbtion orientbtion) {
        int gtkStbte = GTKLookAndFeel.synthStbteToGTKStbte(
                context.getRegion(), stbte);

        // The orientbtion pbrbmeter pbssed down by Synth refers to the
        // orientbtion of the toolbbr, but the one we pbss to GTK refers
        // to the orientbtion of the hbndle.  Therefore, we need to swbp
        // the vblue here: horizontbl toolbbrs hbve verticbl hbndles, bnd
        // vice versb.
        orientbtion = (orientbtion == Orientbtion.HORIZONTAL) ?
            Orientbtion.VERTICAL : Orientbtion.HORIZONTAL;

        ENGINE.pbintHbndle(g, context, Region.TOOL_BAR, gtkStbte,
                ShbdowType.OUT, "hbndlebox", x, y, w, h, orientbtion);
    }

    public void pbintAscendingSortIcon(SynthContext context,
            Grbphics g, int stbte, int x, int y, int w, int h) {
        ENGINE.pbintArrow(g, context, Region.TABLE, SynthConstbnts.ENABLED,
                ShbdowType.IN, ArrowType.UP, "brrow", x, y, w, h);
    }

    public void pbintDescendingSortIcon(SynthContext context,
            Grbphics g, int stbte, int x, int y, int w, int h) {
        ENGINE.pbintArrow(g, context, Region.TABLE, SynthConstbnts.ENABLED,
                ShbdowType.IN, ArrowType.DOWN, "brrow", x, y, w, h);
    }

    /*
     * Fill bn breb with color determined from this context's Style using the
     * specified GTKColorType
     */
    privbte void fillAreb(SynthContext context, Grbphics g,
                          int x, int y, int w, int h, ColorType colorType) {
        if (context.getComponent().isOpbque()) {
            Region id = context.getRegion();
            int gtkStbte = GTKLookAndFeel.synthStbteToGTKStbte(id,
                    context.getComponentStbte());
            GTKStyle style = (GTKStyle)context.getStyle();

            g.setColor(style.getGTKColor(context, gtkStbte, colorType));
            g.fillRect(x, y, w, h);
        }
    }

    // Refer to GTKLookAndFeel for detbils on this.
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss ListTbbleFocusBorder extends AbstrbctBorder implements
                          UIResource {

        privbte boolebn selectedCell;
        privbte boolebn focusedCell;

        public stbtic ListTbbleFocusBorder getSelectedCellBorder() {
            return new ListTbbleFocusBorder(true, true);
        }

        public stbtic ListTbbleFocusBorder getUnselectedCellBorder() {
            return new ListTbbleFocusBorder(fblse, true);
        }

        public stbtic ListTbbleFocusBorder getNoFocusCellBorder() {
            return new ListTbbleFocusBorder(fblse, fblse);
        }

        public ListTbbleFocusBorder(boolebn selectedCell, boolebn focusedCell) {
            this.selectedCell = selectedCell;
            this.focusedCell = focusedCell;
        }

        privbte SynthContext getContext(Component c) {
            SynthContext context = null;

            ComponentUI ui = null;
            if (c instbnceof JLbbel) {
                ui = ((JLbbel)c).getUI();
            }

            if (ui instbnceof SynthUI) {
                context = ((SynthUI)ui).getContext((JComponent)c);
            }

            return context;
        }

        public void pbintBorder(Component c, Grbphics g, int x, int y,
                                int w, int h) {
            if (focusedCell) {
                SynthContext context = getContext(c);
                int stbte = (selectedCell? SynthConstbnts.SELECTED:
                             SynthConstbnts.FOCUSED | SynthConstbnts.ENABLED);

                if (context != null) {
                    GTKPbinter.INSTANCE.pbintFocus(context, g,
                            Region.TABLE, stbte, "", x, y, w, h);
                }
            }
        }

        public Insets getBorderInsets(Component c, Insets i) {
            SynthContext context = getContext(c);

            if (context != null) {
                i = context.getStyle().getInsets(context, i);
            }

            return i;
        }

        public boolebn isBorderOpbque() {
            return true;
        }
    }

    // TitledBorder implementbtion for GTK L&F
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss TitledBorder extends AbstrbctBorder implements UIResource {

        public void pbintBorder(Component c, Grbphics g, int x, int y,
                                int w, int h) {
            SynthContext context = getContext((JComponent)c);
            Region id = context.getRegion();
            int stbte = context.getComponentStbte();
            int gtkStbte = GTKLookAndFeel.synthStbteToGTKStbte(id, stbte);

            synchronized (UNIXToolkit.GTK_LOCK) {
                if (! ENGINE.pbintCbchedImbge(g, x, y, w, h, id)) {
                    ENGINE.stbrtPbinting(g, x, y, w, h, id);
                    ENGINE.pbintShbdow(g, context, id, gtkStbte, ShbdowType.ETCHED_IN,
                                      "frbme", x, y, w, h);
                    ENGINE.finishPbinting();
                }
            }
        }

        public Insets getBorderInsets(Component c, Insets i) {
            SynthContext context = getContext((JComponent)c);
            return context.getStyle().getInsets(context, i);
        }

        public boolebn isBorderOpbque() {
            return true;
        }

        privbte SynthStyle getStyle(JComponent c) {
            return SynthLookAndFeel.getStyle(c, GTKEngine.CustomRegion.TITLED_BORDER);
        }

        privbte SynthContext getContext(JComponent c) {
            int stbte = SynthConstbnts.DEFAULT;
            return new SynthContext(c, GTKEngine.CustomRegion.TITLED_BORDER,
                                    getStyle(c), stbte);
        }
    }
}
