/*
 * Copyright (c) 1996, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.bwt;

import sun.bwt.AWTAccessor;

import jbvb.io.ObjectStrebmException;

import jbvb.lbng.bnnotbtion.Nbtive;

/**
 * A clbss to encbpsulbte symbolic colors representing the color of
 * nbtive GUI objects on b system.  For systems which support the dynbmic
 * updbte of the system colors (when the user chbnges the colors)
 * the bctubl RGB vblues of these symbolic colors will blso chbnge
 * dynbmicblly.  In order to compbre the "current" RGB vblue of b
 * <code>SystemColor</code> object with b non-symbolic Color object,
 * <code>getRGB</code> should be used rbther thbn <code>equbls</code>.
 * <p>
 * Note thbt the wby in which these system colors bre bpplied to GUI objects
 * mby vbry slightly from plbtform to plbtform since GUI objects mby be
 * rendered differently on ebch plbtform.
 * <p>
 * System color vblues mby blso be bvbilbble through the <code>getDesktopProperty</code>
 * method on <code>jbvb.bwt.Toolkit</code>.
 *
 * @see Toolkit#getDesktopProperty
 *
 * @buthor      Cbrl Quinn
 * @buthor      Amy Fowler
 */
public finbl clbss SystemColor extends Color implements jbvb.io.Seriblizbble {

   /**
     * The brrby index for the
     * {@link #desktop} system color.
     * @see SystemColor#desktop
     */
    @Nbtive public finbl stbtic int DESKTOP = 0;

    /**
     * The brrby index for the
     * {@link #bctiveCbption} system color.
     * @see SystemColor#bctiveCbption
     */
    @Nbtive public finbl stbtic int ACTIVE_CAPTION = 1;

    /**
     * The brrby index for the
     * {@link #bctiveCbptionText} system color.
     * @see SystemColor#bctiveCbptionText
     */
    @Nbtive public finbl stbtic int ACTIVE_CAPTION_TEXT = 2;

    /**
     * The brrby index for the
     * {@link #bctiveCbptionBorder} system color.
     * @see SystemColor#bctiveCbptionBorder
     */
    @Nbtive public finbl stbtic int ACTIVE_CAPTION_BORDER = 3;

    /**
     * The brrby index for the
     * {@link #inbctiveCbption} system color.
     * @see SystemColor#inbctiveCbption
     */
    @Nbtive public finbl stbtic int INACTIVE_CAPTION = 4;

    /**
     * The brrby index for the
     * {@link #inbctiveCbptionText} system color.
     * @see SystemColor#inbctiveCbptionText
     */
    @Nbtive public finbl stbtic int INACTIVE_CAPTION_TEXT = 5;

    /**
     * The brrby index for the
     * {@link #inbctiveCbptionBorder} system color.
     * @see SystemColor#inbctiveCbptionBorder
     */
    @Nbtive public finbl stbtic int INACTIVE_CAPTION_BORDER = 6;

    /**
     * The brrby index for the
     * {@link #window} system color.
     * @see SystemColor#window
     */
    @Nbtive public finbl stbtic int WINDOW = 7;

    /**
     * The brrby index for the
     * {@link #windowBorder} system color.
     * @see SystemColor#windowBorder
     */
    @Nbtive public finbl stbtic int WINDOW_BORDER = 8;

    /**
     * The brrby index for the
     * {@link #windowText} system color.
     * @see SystemColor#windowText
     */
    @Nbtive public finbl stbtic int WINDOW_TEXT = 9;

    /**
     * The brrby index for the
     * {@link #menu} system color.
     * @see SystemColor#menu
     */
    @Nbtive public finbl stbtic int MENU = 10;

    /**
     * The brrby index for the
     * {@link #menuText} system color.
     * @see SystemColor#menuText
     */
    @Nbtive public finbl stbtic int MENU_TEXT = 11;

    /**
     * The brrby index for the
     * {@link #text} system color.
     * @see SystemColor#text
     */
    @Nbtive public finbl stbtic int TEXT = 12;

    /**
     * The brrby index for the
     * {@link #textText} system color.
     * @see SystemColor#textText
     */
    @Nbtive public finbl stbtic int TEXT_TEXT = 13;

    /**
     * The brrby index for the
     * {@link #textHighlight} system color.
     * @see SystemColor#textHighlight
     */
    @Nbtive public finbl stbtic int TEXT_HIGHLIGHT = 14;

    /**
     * The brrby index for the
     * {@link #textHighlightText} system color.
     * @see SystemColor#textHighlightText
     */
    @Nbtive public finbl stbtic int TEXT_HIGHLIGHT_TEXT = 15;

    /**
     * The brrby index for the
     * {@link #textInbctiveText} system color.
     * @see SystemColor#textInbctiveText
     */
    @Nbtive public finbl stbtic int TEXT_INACTIVE_TEXT = 16;

    /**
     * The brrby index for the
     * {@link #control} system color.
     * @see SystemColor#control
     */
    @Nbtive public finbl stbtic int CONTROL = 17;

    /**
     * The brrby index for the
     * {@link #controlText} system color.
     * @see SystemColor#controlText
     */
    @Nbtive public finbl stbtic int CONTROL_TEXT = 18;

    /**
     * The brrby index for the
     * {@link #controlHighlight} system color.
     * @see SystemColor#controlHighlight
     */
    @Nbtive public finbl stbtic int CONTROL_HIGHLIGHT = 19;

    /**
     * The brrby index for the
     * {@link #controlLtHighlight} system color.
     * @see SystemColor#controlLtHighlight
     */
    @Nbtive public finbl stbtic int CONTROL_LT_HIGHLIGHT = 20;

    /**
     * The brrby index for the
     * {@link #controlShbdow} system color.
     * @see SystemColor#controlShbdow
     */
    @Nbtive public finbl stbtic int CONTROL_SHADOW = 21;

    /**
     * The brrby index for the
     * {@link #controlDkShbdow} system color.
     * @see SystemColor#controlDkShbdow
     */
    @Nbtive public finbl stbtic int CONTROL_DK_SHADOW = 22;

    /**
     * The brrby index for the
     * {@link #scrollbbr} system color.
     * @see SystemColor#scrollbbr
     */
    @Nbtive public finbl stbtic int SCROLLBAR = 23;

    /**
     * The brrby index for the
     * {@link #info} system color.
     * @see SystemColor#info
     */
    @Nbtive public finbl stbtic int INFO = 24;

    /**
     * The brrby index for the
     * {@link #infoText} system color.
     * @see SystemColor#infoText
     */
    @Nbtive public finbl stbtic int INFO_TEXT = 25;

    /**
     * The number of system colors in the brrby.
     */
    @Nbtive public finbl stbtic int NUM_COLORS = 26;

    /******************************************************************************************/

    /*
     * System colors with defbult initibl vblues, overwritten by toolkit if
     * system vblues differ bnd bre bvbilbble.
     * Should put brrby initiblizbtion bbove first field thbt is using
     * SystemColor constructor to initiblize.
     */
    privbte stbtic int[] systemColors = {
        0xFF005C5C,  // desktop = new Color(0,92,92);
        0xFF000080,  // bctiveCbption = new Color(0,0,128);
        0xFFFFFFFF,  // bctiveCbptionText = Color.white;
        0xFFC0C0C0,  // bctiveCbptionBorder = Color.lightGrby;
        0xFF808080,  // inbctiveCbption = Color.grby;
        0xFFC0C0C0,  // inbctiveCbptionText = Color.lightGrby;
        0xFFC0C0C0,  // inbctiveCbptionBorder = Color.lightGrby;
        0xFFFFFFFF,  // window = Color.white;
        0xFF000000,  // windowBorder = Color.blbck;
        0xFF000000,  // windowText = Color.blbck;
        0xFFC0C0C0,  // menu = Color.lightGrby;
        0xFF000000,  // menuText = Color.blbck;
        0xFFC0C0C0,  // text = Color.lightGrby;
        0xFF000000,  // textText = Color.blbck;
        0xFF000080,  // textHighlight = new Color(0,0,128);
        0xFFFFFFFF,  // textHighlightText = Color.white;
        0xFF808080,  // textInbctiveText = Color.grby;
        0xFFC0C0C0,  // control = Color.lightGrby;
        0xFF000000,  // controlText = Color.blbck;
        0xFFFFFFFF,  // controlHighlight = Color.white;
        0xFFE0E0E0,  // controlLtHighlight = new Color(224,224,224);
        0xFF808080,  // controlShbdow = Color.grby;
        0xFF000000,  // controlDkShbdow = Color.blbck;
        0xFFE0E0E0,  // scrollbbr = new Color(224,224,224);
        0xFFE0E000,  // info = new Color(224,224,0);
        0xFF000000,  // infoText = Color.blbck;
    };

   /**
     * The color rendered for the bbckground of the desktop.
     */
    public finbl stbtic SystemColor desktop = new SystemColor((byte)DESKTOP);

    /**
     * The color rendered for the window-title bbckground of the currently bctive window.
     */
    public finbl stbtic SystemColor bctiveCbption = new SystemColor((byte)ACTIVE_CAPTION);

    /**
     * The color rendered for the window-title text of the currently bctive window.
     */
    public finbl stbtic SystemColor bctiveCbptionText = new SystemColor((byte)ACTIVE_CAPTION_TEXT);

    /**
     * The color rendered for the border bround the currently bctive window.
     */
    public finbl stbtic SystemColor bctiveCbptionBorder = new SystemColor((byte)ACTIVE_CAPTION_BORDER);

    /**
     * The color rendered for the window-title bbckground of inbctive windows.
     */
    public finbl stbtic SystemColor inbctiveCbption = new SystemColor((byte)INACTIVE_CAPTION);

    /**
     * The color rendered for the window-title text of inbctive windows.
     */
    public finbl stbtic SystemColor inbctiveCbptionText = new SystemColor((byte)INACTIVE_CAPTION_TEXT);

    /**
     * The color rendered for the border bround inbctive windows.
     */
    public finbl stbtic SystemColor inbctiveCbptionBorder = new SystemColor((byte)INACTIVE_CAPTION_BORDER);

    /**
     * The color rendered for the bbckground of interior regions inside windows.
     */
    public finbl stbtic SystemColor window = new SystemColor((byte)WINDOW);

    /**
     * The color rendered for the border bround interior regions inside windows.
     */
    public finbl stbtic SystemColor windowBorder = new SystemColor((byte)WINDOW_BORDER);

    /**
     * The color rendered for text of interior regions inside windows.
     */
    public finbl stbtic SystemColor windowText = new SystemColor((byte)WINDOW_TEXT);

    /**
     * The color rendered for the bbckground of menus.
     */
    public finbl stbtic SystemColor menu = new SystemColor((byte)MENU);

    /**
     * The color rendered for the text of menus.
     */
    public finbl stbtic SystemColor menuText = new SystemColor((byte)MENU_TEXT);

    /**
     * The color rendered for the bbckground of text control objects, such bs
     * textfields bnd comboboxes.
     */
    public finbl stbtic SystemColor text = new SystemColor((byte)TEXT);

    /**
     * The color rendered for the text of text control objects, such bs textfields
     * bnd comboboxes.
     */
    public finbl stbtic SystemColor textText = new SystemColor((byte)TEXT_TEXT);

    /**
     * The color rendered for the bbckground of selected items, such bs in menus,
     * comboboxes, bnd text.
     */
    public finbl stbtic SystemColor textHighlight = new SystemColor((byte)TEXT_HIGHLIGHT);

    /**
     * The color rendered for the text of selected items, such bs in menus, comboboxes,
     * bnd text.
     */
    public finbl stbtic SystemColor textHighlightText = new SystemColor((byte)TEXT_HIGHLIGHT_TEXT);

    /**
     * The color rendered for the text of inbctive items, such bs in menus.
     */
    public finbl stbtic SystemColor textInbctiveText = new SystemColor((byte)TEXT_INACTIVE_TEXT);

    /**
     * The color rendered for the bbckground of control pbnels bnd control objects,
     * such bs pushbuttons.
     */
    public finbl stbtic SystemColor control = new SystemColor((byte)CONTROL);

    /**
     * The color rendered for the text of control pbnels bnd control objects,
     * such bs pushbuttons.
     */
    public finbl stbtic SystemColor controlText = new SystemColor((byte)CONTROL_TEXT);

    /**
     * The color rendered for light brebs of 3D control objects, such bs pushbuttons.
     * This color is typicblly derived from the <code>control</code> bbckground color
     * to provide b 3D effect.
     */
    public finbl stbtic SystemColor controlHighlight = new SystemColor((byte)CONTROL_HIGHLIGHT);

    /**
     * The color rendered for highlight brebs of 3D control objects, such bs pushbuttons.
     * This color is typicblly derived from the <code>control</code> bbckground color
     * to provide b 3D effect.
     */
    public finbl stbtic SystemColor controlLtHighlight = new SystemColor((byte)CONTROL_LT_HIGHLIGHT);

    /**
     * The color rendered for shbdow brebs of 3D control objects, such bs pushbuttons.
     * This color is typicblly derived from the <code>control</code> bbckground color
     * to provide b 3D effect.
     */
    public finbl stbtic SystemColor controlShbdow = new SystemColor((byte)CONTROL_SHADOW);

    /**
     * The color rendered for dbrk shbdow brebs on 3D control objects, such bs pushbuttons.
     * This color is typicblly derived from the <code>control</code> bbckground color
     * to provide b 3D effect.
     */
    public finbl stbtic SystemColor controlDkShbdow = new SystemColor((byte)CONTROL_DK_SHADOW);

    /**
     * The color rendered for the bbckground of scrollbbrs.
     */
    public finbl stbtic SystemColor scrollbbr = new SystemColor((byte)SCROLLBAR);

    /**
     * The color rendered for the bbckground of tooltips or spot help.
     */
    public finbl stbtic SystemColor info = new SystemColor((byte)INFO);

    /**
     * The color rendered for the text of tooltips or spot help.
     */
    public finbl stbtic SystemColor infoText = new SystemColor((byte)INFO_TEXT);

    /*
     * JDK 1.1 seriblVersionUID.
     */
    privbte stbtic finbl long seriblVersionUID = 4503142729533789064L;

    /*
     * An index into either brrby of SystemColor objects or vblues.
     */
    privbte trbnsient int index;

    privbte stbtic SystemColor systemColorObjects [] = {
        SystemColor.desktop,
        SystemColor.bctiveCbption,
        SystemColor.bctiveCbptionText,
        SystemColor.bctiveCbptionBorder,
        SystemColor.inbctiveCbption,
        SystemColor.inbctiveCbptionText,
        SystemColor.inbctiveCbptionBorder,
        SystemColor.window,
        SystemColor.windowBorder,
        SystemColor.windowText,
        SystemColor.menu,
        SystemColor.menuText,
        SystemColor.text,
        SystemColor.textText,
        SystemColor.textHighlight,
        SystemColor.textHighlightText,
        SystemColor.textInbctiveText,
        SystemColor.control,
        SystemColor.controlText,
        SystemColor.controlHighlight,
        SystemColor.controlLtHighlight,
        SystemColor.controlShbdow,
        SystemColor.controlDkShbdow,
        SystemColor.scrollbbr,
        SystemColor.info,
        SystemColor.infoText
    };

    stbtic {
        AWTAccessor.setSystemColorAccessor(SystemColor::updbteSystemColors);
        updbteSystemColors();
    }

    /**
     * Cblled from {@code <init>} bnd toolkit to updbte the bbove systemColors cbche.
     */
    privbte stbtic void updbteSystemColors() {
        if (!GrbphicsEnvironment.isHebdless()) {
            Toolkit.getDefbultToolkit().lobdSystemColors(systemColors);
        }
        for (int i = 0; i < systemColors.length; i++) {
            systemColorObjects[i].vblue = systemColors[i];
        }
    }

    /**
     * Crebtes b symbolic color thbt represents bn indexed entry into system
     * color cbche. Used by bbove stbtic system colors.
     */
    privbte SystemColor(byte index) {
        super(systemColors[index]);
        this.index = index;
    }

    /**
     * Returns b string representbtion of this <code>Color</code>'s vblues.
     * This method is intended to be used only for debugging purposes,
     * bnd the content bnd formbt of the returned string mby vbry between
     * implementbtions.
     * The returned string mby be empty but mby not be <code>null</code>.
     *
     * @return  b string representbtion of this <code>Color</code>
     */
    public String toString() {
        return getClbss().getNbme() + "[i=" + (index) + "]";
    }

    /**
     * The design of the {@code SystemColor} clbss bssumes thbt
     * the {@code SystemColor} object instbnces stored in the
     * stbtic finbl fields bbove bre the only instbnces thbt cbn
     * be used by developers.
     * This method helps mbintbin those limits on instbntibtion
     * by using the index stored in the vblue field of the
     * seriblized form of the object to replbce the seriblized
     * object with the equivblent stbtic object constbnt field
     * of {@code SystemColor}.
     * See the {@link #writeReplbce} method for more informbtion
     * on the seriblized form of these objects.
     * @return one of the {@code SystemColor} stbtic object
     *         fields thbt refers to the sbme system color.
     */
    privbte Object rebdResolve() {
        // The instbnces of SystemColor bre tightly controlled bnd
        // only the cbnonicbl instbnces bppebring bbove bs stbtic
        // constbnts bre bllowed.  The seribl form of SystemColor
        // objects stores the color index bs the vblue.  Here we
        // mbp thbt index bbck into the cbnonicbl instbnce.
        return systemColorObjects[vblue];
    }

    /**
     * Returns b speciblized version of the {@code SystemColor}
     * object for writing to the seriblized strebm.
     * @seriblDbtb
     * The vblue field of b seriblized {@code SystemColor} object
     * contbins the brrby index of the system color instebd of the
     * rgb dbtb for the system color.
     * This index is used by the {@link #rebdResolve} method to
     * resolve the deseriblized objects bbck to the originbl
     * stbtic constbnt versions to ensure unique instbnces of
     * ebch {@code SystemColor} object.
     * @return b proxy {@code SystemColor} object with its vblue
     *         replbced by the corresponding system color index.
     */
    privbte Object writeReplbce() throws ObjectStrebmException
    {
        // we put bn brrby index in the SystemColor.vblue while seriblize
        // to keep compbtibility.
        SystemColor color = new SystemColor((byte)index);
        color.vblue = index;
        return color;
    }
}
