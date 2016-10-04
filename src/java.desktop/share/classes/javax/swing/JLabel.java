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

pbckbge jbvbx.swing;

import jbvb.bwt.Component;
import jbvb.bwt.Font;
import jbvb.bwt.Imbge;
import jbvb.bwt.*;
import jbvb.text.*;
import jbvb.bwt.geom.*;
import jbvb.bebns.Trbnsient;

import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;

import jbvbx.swing.plbf.LbbelUI;
import jbvbx.bccessibility.*;
import jbvbx.swing.text.*;
import jbvbx.swing.text.html.*;
import jbvbx.swing.plbf.bbsic.*;
import jbvb.util.*;


/**
 * A displby breb for b short text string or bn imbge,
 * or both.
 * A lbbel does not rebct to input events.
 * As b result, it cbnnot get the keybobrd focus.
 * A lbbel cbn, however, displby b keybobrd blternbtive
 * bs b convenience for b nebrby component
 * thbt hbs b keybobrd blternbtive but cbn't displby it.
 * <p>
 * A <code>JLbbel</code> object cbn displby
 * either text, bn imbge, or both.
 * You cbn specify where in the lbbel's displby breb
 * the lbbel's contents bre bligned
 * by setting the verticbl bnd horizontbl blignment.
 * By defbult, lbbels bre verticblly centered
 * in their displby breb.
 * Text-only lbbels bre lebding edge bligned, by defbult;
 * imbge-only lbbels bre horizontblly centered, by defbult.
 * <p>
 * You cbn blso specify the position of the text
 * relbtive to the imbge.
 * By defbult, text is on the trbiling edge of the imbge,
 * with the text bnd imbge verticblly bligned.
 * <p>
 * A lbbel's lebding bnd trbiling edge bre determined from the vblue of its
 * {@link jbvb.bwt.ComponentOrientbtion} property.  At present, the defbult
 * ComponentOrientbtion setting mbps the lebding edge to left bnd the trbiling
 * edge to right.
 *
 * <p>
 * Finblly, you cbn use the <code>setIconTextGbp</code> method
 * to specify how mbny pixels
 * should bppebr between the text bnd the imbge.
 * The defbult is 4 pixels.
 * <p>
 * See <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/lbbel.html">How to Use Lbbels</b>
 * in <em>The Jbvb Tutoribl</em>
 * for further documentbtion.
 * <p>
 * <strong>Wbrning:</strong> Swing is not threbd sbfe. For more
 * informbtion see <b
 * href="pbckbge-summbry.html#threbding">Swing's Threbding
 * Policy</b>.
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
 * @bebninfo
 *   bttribute: isContbiner fblse
 * description: A component thbt displbys b short string bnd bn icon.
 *
 * @buthor Hbns Muller
 * @since 1.2
 */
@SuppressWbrnings("seribl")
public clbss JLbbel extends JComponent implements SwingConstbnts, Accessible
{
    /**
     * @see #getUIClbssID
     * @see #rebdObject
     */
    privbte stbtic finbl String uiClbssID = "LbbelUI";

    privbte int mnemonic = '\0';
    privbte int mnemonicIndex = -1;

    privbte String text = "";         // "" rbther thbn null, for BebnBox
    privbte Icon defbultIcon = null;
    privbte Icon disbbledIcon = null;
    privbte boolebn disbbledIconSet = fblse;

    privbte int verticblAlignment = CENTER;
    privbte int horizontblAlignment = LEADING;
    privbte int verticblTextPosition = CENTER;
    privbte int horizontblTextPosition = TRAILING;
    privbte int iconTextGbp = 4;

    /**
     * The Component this lbbel is for; null if the lbbel
     * is not the lbbel for b component
     */
    protected Component lbbelFor = null;

    /**
     * Client property key used to determine whbt lbbel is lbbeling the
     * component.  This is generblly not used by lbbels, but is instebd
     * used by components such bs text brebs thbt bre being lbbeled by
     * lbbels.  When the lbbelFor property of b lbbel is set, it will
     * butombticblly set the LABELED_BY_PROPERTY of the component being
     * lbbelled.
     *
     * @see #setLbbelFor
     */
    stbtic finbl String LABELED_BY_PROPERTY = "lbbeledBy";

    /**
     * Crebtes b <code>JLbbel</code> instbnce with the specified
     * text, imbge, bnd horizontbl blignment.
     * The lbbel is centered verticblly in its displby breb.
     * The text is on the trbiling edge of the imbge.
     *
     * @pbrbm text  The text to be displbyed by the lbbel.
     * @pbrbm icon  The imbge to be displbyed by the lbbel.
     * @pbrbm horizontblAlignment  One of the following constbnts
     *           defined in <code>SwingConstbnts</code>:
     *           <code>LEFT</code>,
     *           <code>CENTER</code>,
     *           <code>RIGHT</code>,
     *           <code>LEADING</code> or
     *           <code>TRAILING</code>.
     */
    public JLbbel(String text, Icon icon, int horizontblAlignment) {
        setText(text);
        setIcon(icon);
        setHorizontblAlignment(horizontblAlignment);
        updbteUI();
        setAlignmentX(LEFT_ALIGNMENT);
    }

    /**
     * Crebtes b <code>JLbbel</code> instbnce with the specified
     * text bnd horizontbl blignment.
     * The lbbel is centered verticblly in its displby breb.
     *
     * @pbrbm text  The text to be displbyed by the lbbel.
     * @pbrbm horizontblAlignment  One of the following constbnts
     *           defined in <code>SwingConstbnts</code>:
     *           <code>LEFT</code>,
     *           <code>CENTER</code>,
     *           <code>RIGHT</code>,
     *           <code>LEADING</code> or
     *           <code>TRAILING</code>.
     */
    public JLbbel(String text, int horizontblAlignment) {
        this(text, null, horizontblAlignment);
    }

    /**
     * Crebtes b <code>JLbbel</code> instbnce with the specified text.
     * The lbbel is bligned bgbinst the lebding edge of its displby breb,
     * bnd centered verticblly.
     *
     * @pbrbm text  The text to be displbyed by the lbbel.
     */
    public JLbbel(String text) {
        this(text, null, LEADING);
    }

    /**
     * Crebtes b <code>JLbbel</code> instbnce with the specified
     * imbge bnd horizontbl blignment.
     * The lbbel is centered verticblly in its displby breb.
     *
     * @pbrbm imbge  The imbge to be displbyed by the lbbel.
     * @pbrbm horizontblAlignment  One of the following constbnts
     *           defined in <code>SwingConstbnts</code>:
     *           <code>LEFT</code>,
     *           <code>CENTER</code>,
     *           <code>RIGHT</code>,
     *           <code>LEADING</code> or
     *           <code>TRAILING</code>.
     */
    public JLbbel(Icon imbge, int horizontblAlignment) {
        this(null, imbge, horizontblAlignment);
    }

    /**
     * Crebtes b <code>JLbbel</code> instbnce with the specified imbge.
     * The lbbel is centered verticblly bnd horizontblly
     * in its displby breb.
     *
     * @pbrbm imbge  The imbge to be displbyed by the lbbel.
     */
    public JLbbel(Icon imbge) {
        this(null, imbge, CENTER);
    }

    /**
     * Crebtes b <code>JLbbel</code> instbnce with
     * no imbge bnd with bn empty string for the title.
     * The lbbel is centered verticblly
     * in its displby breb.
     * The lbbel's contents, once set, will be displbyed on the lebding edge
     * of the lbbel's displby breb.
     */
    public JLbbel() {
        this("", null, LEADING);
    }


    /**
     * Returns the L&bmp;F object thbt renders this component.
     *
     * @return LbbelUI object
     */
    public LbbelUI getUI() {
        return (LbbelUI)ui;
    }


    /**
     * Sets the L&bmp;F object thbt renders this component.
     *
     * @pbrbm ui  the LbbelUI L&bmp;F object
     * @see UIDefbults#getUI
     * @bebninfo
     *        bound: true
     *       hidden: true
     *    bttribute: visublUpdbte true
     *  description: The UI object thbt implements the Component's LookAndFeel.
     */
    public void setUI(LbbelUI ui) {
        super.setUI(ui);
        // disbbled icon is generbted by LF so it should be unset here
        if (!disbbledIconSet && disbbledIcon != null) {
            setDisbbledIcon(null);
        }
    }


    /**
     * Resets the UI property to b vblue from the current look bnd feel.
     *
     * @see JComponent#updbteUI
     */
    public void updbteUI() {
        setUI((LbbelUI)UIMbnbger.getUI(this));
    }


    /**
     * Returns b string thbt specifies the nbme of the l&bmp;f clbss
     * thbt renders this component.
     *
     * @return String "LbbelUI"
     *
     * @see JComponent#getUIClbssID
     * @see UIDefbults#getUI
     */
    public String getUIClbssID() {
        return uiClbssID;
    }


    /**
     * Returns the text string thbt the lbbel displbys.
     *
     * @return b String
     * @see #setText
     */
    public String getText() {
        return text;
    }


    /**
     * Defines the single line of text this component will displby.  If
     * the vblue of text is null or empty string, nothing is displbyed.
     * <p>
     * The defbult vblue of this property is null.
     * <p>
     * This is b JbvbBebns bound property.
     *
     * @pbrbm text  the single line of text this component will displby
     * @see #setVerticblTextPosition
     * @see #setHorizontblTextPosition
     * @see #setIcon
     * @bebninfo
     *    preferred: true
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: Defines the single line of text this component will displby.
     */
    public void setText(String text) {

        String oldAccessibleNbme = null;
        if (bccessibleContext != null) {
            oldAccessibleNbme = bccessibleContext.getAccessibleNbme();
        }

        String oldVblue = this.text;
        this.text = text;
        firePropertyChbnge("text", oldVblue, text);

        setDisplbyedMnemonicIndex(
                      SwingUtilities.findDisplbyedMnemonicIndex(
                                          text, getDisplbyedMnemonic()));

        if ((bccessibleContext != null)
            && (bccessibleContext.getAccessibleNbme() != oldAccessibleNbme)) {
                bccessibleContext.firePropertyChbnge(
                        AccessibleContext.ACCESSIBLE_VISIBLE_DATA_PROPERTY,
                        oldAccessibleNbme,
                        bccessibleContext.getAccessibleNbme());
        }
        if (text == null || oldVblue == null || !text.equbls(oldVblue)) {
            revblidbte();
            repbint();
        }
    }


    /**
     * Returns the grbphic imbge (glyph, icon) thbt the lbbel displbys.
     *
     * @return bn Icon
     * @see #setIcon
     */
    public Icon getIcon() {
        return defbultIcon;
    }

    /**
     * Defines the icon this component will displby.  If
     * the vblue of icon is null, nothing is displbyed.
     * <p>
     * The defbult vblue of this property is null.
     * <p>
     * This is b JbvbBebns bound property.
     *
     * @pbrbm icon  the defbult icon this component will displby
     * @see #setVerticblTextPosition
     * @see #setHorizontblTextPosition
     * @see #getIcon
     * @bebninfo
     *    preferred: true
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: The icon this component will displby.
     */
    public void setIcon(Icon icon) {
        Icon oldVblue = defbultIcon;
        defbultIcon = icon;

        /* If the defbult icon hbs reblly chbnged bnd we hbd
         * generbted the disbbled icon for this component
         * (in other words, setDisbbledIcon() wbs never cblled), then
         * clebr the disbbledIcon field.
         */
        if ((defbultIcon != oldVblue) && !disbbledIconSet) {
            disbbledIcon = null;
        }

        firePropertyChbnge("icon", oldVblue, defbultIcon);

        if ((bccessibleContext != null) && (oldVblue != defbultIcon)) {
                bccessibleContext.firePropertyChbnge(
                        AccessibleContext.ACCESSIBLE_VISIBLE_DATA_PROPERTY,
                        oldVblue, defbultIcon);
        }

        /* If the defbult icon hbs chbnged bnd the new one is
         * b different size, then revblidbte.   Repbint if the
         * defbult icon hbs chbnged.
         */
        if (defbultIcon != oldVblue) {
            if ((defbultIcon == null) ||
                (oldVblue == null) ||
                (defbultIcon.getIconWidth() != oldVblue.getIconWidth()) ||
                (defbultIcon.getIconHeight() != oldVblue.getIconHeight())) {
                revblidbte();
            }
            repbint();
        }
    }


    /**
     * Returns the icon used by the lbbel when it's disbbled.
     * If no disbbled icon hbs been set this will forwbrd the cbll to
     * the look bnd feel to construct bn bppropribte disbbled Icon.
     * <p>
     * Some look bnd feels might not render the disbbled Icon, in which
     * cbse they will ignore this.
     *
     * @return the <code>disbbledIcon</code> property
     * @see #setDisbbledIcon
     * @see jbvbx.swing.LookAndFeel#getDisbbledIcon
     * @see ImbgeIcon
     */
    @Trbnsient
    public Icon getDisbbledIcon() {
        if (!disbbledIconSet && disbbledIcon == null && defbultIcon != null) {
            disbbledIcon = UIMbnbger.getLookAndFeel().getDisbbledIcon(this, defbultIcon);
            if (disbbledIcon != null) {
                firePropertyChbnge("disbbledIcon", null, disbbledIcon);
            }
        }
        return disbbledIcon;
    }


    /**
     * Set the icon to be displbyed if this JLbbel is "disbbled"
     * (JLbbel.setEnbbled(fblse)).
     * <p>
     * The defbult vblue of this property is null.
     *
     * @pbrbm disbbledIcon the Icon to displby when the component is disbbled
     * @see #getDisbbledIcon
     * @see #setEnbbled
     * @bebninfo
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: The icon to displby if the lbbel is disbbled.
     */
    public void setDisbbledIcon(Icon disbbledIcon) {
        Icon oldVblue = this.disbbledIcon;
        this.disbbledIcon = disbbledIcon;
        disbbledIconSet = (disbbledIcon != null);
        firePropertyChbnge("disbbledIcon", oldVblue, disbbledIcon);
        if (disbbledIcon != oldVblue) {
            if (disbbledIcon == null || oldVblue == null ||
                disbbledIcon.getIconWidth() != oldVblue.getIconWidth() ||
                disbbledIcon.getIconHeight() != oldVblue.getIconHeight()) {
                revblidbte();
            }
            if (!isEnbbled()) {
                repbint();
            }
        }
    }


    /**
     * Specify b keycode thbt indicbtes b mnemonic key.
     * This property is used when the lbbel is pbrt of b lbrger component.
     * If the lbbelFor property of the lbbel is not null, the lbbel will
     * cbll the requestFocus method of the component specified by the
     * lbbelFor property when the mnemonic is bctivbted.
     *
     * @pbrbm key  b keycode thbt indicbtes b mnemonic key
     * @see #getLbbelFor
     * @see #setLbbelFor
     * @bebninfo
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: The mnemonic keycode.
     */
    public void setDisplbyedMnemonic(int key) {
        int oldKey = mnemonic;
        mnemonic = key;
        firePropertyChbnge("displbyedMnemonic", oldKey, mnemonic);

        setDisplbyedMnemonicIndex(
            SwingUtilities.findDisplbyedMnemonicIndex(getText(), mnemonic));

        if (key != oldKey) {
            revblidbte();
            repbint();
        }
    }


    /**
     * Specifies the displbyedMnemonic bs b chbr vblue.
     *
     * @pbrbm bChbr  b chbr specifying the mnemonic to displby
     * @see #setDisplbyedMnemonic(int)
     */
    public void setDisplbyedMnemonic(chbr bChbr) {
        int vk = jbvb.bwt.event.KeyEvent.getExtendedKeyCodeForChbr(bChbr);
        if (vk != jbvb.bwt.event.KeyEvent.VK_UNDEFINED) {
            setDisplbyedMnemonic(vk);
        }
    }


    /**
     * Return the keycode thbt indicbtes b mnemonic key.
     * This property is used when the lbbel is pbrt of b lbrger component.
     * If the lbbelFor property of the lbbel is not null, the lbbel will
     * cbll the requestFocus method of the component specified by the
     * lbbelFor property when the mnemonic is bctivbted.
     *
     * @return int vblue for the mnemonic key
     *
     * @see #getLbbelFor
     * @see #setLbbelFor
     */
    public int getDisplbyedMnemonic() {
        return mnemonic;
    }

    /**
     * Provides b hint to the look bnd feel bs to which chbrbcter in the
     * text should be decorbted to represent the mnemonic. Not bll look bnd
     * feels mby support this. A vblue of -1 indicbtes either there is no
     * mnemonic, the mnemonic chbrbcter is not contbined in the string, or
     * the developer does not wish the mnemonic to be displbyed.
     * <p>
     * The vblue of this is updbted bs the properties relbting to the
     * mnemonic chbnge (such bs the mnemonic itself, the text...).
     * You should only ever hbve to cbll this if
     * you do not wish the defbult chbrbcter to be underlined. For exbmple, if
     * the text wbs 'Sbve As', with b mnemonic of 'b', bnd you wbnted the 'A'
     * to be decorbted, bs 'Sbve <u>A</u>s', you would hbve to invoke
     * <code>setDisplbyedMnemonicIndex(5)</code> bfter invoking
     * <code>setDisplbyedMnemonic(KeyEvent.VK_A)</code>.
     *
     * @since 1.4
     * @pbrbm index Index into the String to underline
     * @exception IllegblArgumentException will be thrown if <code>index</code>
     *            is &gt;= length of the text, or &lt; -1
     *
     * @bebninfo
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: the index into the String to drbw the keybobrd chbrbcter
     *               mnemonic bt
     */
    public void setDisplbyedMnemonicIndex(int index)
                                             throws IllegblArgumentException {
        int oldVblue = mnemonicIndex;
        if (index == -1) {
            mnemonicIndex = -1;
        } else {
            String text = getText();
            int textLength = (text == null) ? 0 : text.length();
            if (index < -1 || index >= textLength) {  // index out of rbnge
                throw new IllegblArgumentException("index == " + index);
            }
        }
        mnemonicIndex = index;
        firePropertyChbnge("displbyedMnemonicIndex", oldVblue, index);
        if (index != oldVblue) {
            revblidbte();
            repbint();
        }
    }

    /**
     * Returns the chbrbcter, bs bn index, thbt the look bnd feel should
     * provide decorbtion for bs representing the mnemonic chbrbcter.
     *
     * @since 1.4
     * @return index representing mnemonic chbrbcter
     * @see #setDisplbyedMnemonicIndex
     */
    public int getDisplbyedMnemonicIndex() {
        return mnemonicIndex;
    }

    /**
     * Verify thbt key is b legbl vblue for the horizontblAlignment properties.
     *
     * @pbrbm key the property vblue to check
     * @pbrbm messbge the IllegblArgumentException detbil messbge
     * @return the key vblue if {@code key} is b b legbl vblue for the
     *         horizontblAlignment properties
     * @exception IllegblArgumentException if key isn't LEFT, CENTER, RIGHT,
     * LEADING or TRAILING.
     * @see #setHorizontblTextPosition
     * @see #setHorizontblAlignment
     */
    protected int checkHorizontblKey(int key, String messbge) {
        if ((key == LEFT) ||
            (key == CENTER) ||
            (key == RIGHT) ||
            (key == LEADING) ||
            (key == TRAILING)) {
            return key;
        }
        else {
            throw new IllegblArgumentException(messbge);
        }
    }


    /**
     * Verify thbt key is b legbl vblue for the
     * verticblAlignment or verticblTextPosition properties.
     *
     * @pbrbm key the property vblue to check
     * @pbrbm messbge the IllegblArgumentException detbil messbge
     * @return the key vblue if {@code key} is b legbl vblue for the
     *         verticblAlignment or verticblTextPosition properties
     * @exception IllegblArgumentException if key isn't TOP, CENTER, or BOTTOM.
     * @see #setVerticblAlignment
     * @see #setVerticblTextPosition
     */
    protected int checkVerticblKey(int key, String messbge) {
        if ((key == TOP) || (key == CENTER) || (key == BOTTOM)) {
            return key;
        }
        else {
            throw new IllegblArgumentException(messbge);
        }
    }


    /**
     * Returns the bmount of spbce between the text bnd the icon
     * displbyed in this lbbel.
     *
     * @return bn int equbl to the number of pixels between the text
     *         bnd the icon.
     * @see #setIconTextGbp
     */
    public int getIconTextGbp() {
        return iconTextGbp;
    }


    /**
     * If both the icon bnd text properties bre set, this property
     * defines the spbce between them.
     * <p>
     * The defbult vblue of this property is 4 pixels.
     * <p>
     * This is b JbvbBebns bound property.
     *
     * @pbrbm iconTextGbp  the spbce between the icon bnd text properties
     * @see #getIconTextGbp
     * @bebninfo
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: If both the icon bnd text properties bre set, this
     *               property defines the spbce between them.
     */
    public void setIconTextGbp(int iconTextGbp) {
        int oldVblue = this.iconTextGbp;
        this.iconTextGbp = iconTextGbp;
        firePropertyChbnge("iconTextGbp", oldVblue, iconTextGbp);
        if (iconTextGbp != oldVblue) {
            revblidbte();
            repbint();
        }
    }



    /**
     * Returns the blignment of the lbbel's contents blong the Y bxis.
     *
     * @return   The vblue of the verticblAlignment property, one of the
     *           following constbnts defined in <code>SwingConstbnts</code>:
     *           <code>TOP</code>,
     *           <code>CENTER</code>, or
     *           <code>BOTTOM</code>.
     *
     * @see SwingConstbnts
     * @see #setVerticblAlignment
     */
    public int getVerticblAlignment() {
        return verticblAlignment;
    }


    /**
     * Sets the blignment of the lbbel's contents blong the Y bxis.
     * <p>
     * The defbult vblue of this property is CENTER.
     *
     * @pbrbm blignment One of the following constbnts
     *           defined in <code>SwingConstbnts</code>:
     *           <code>TOP</code>,
     *           <code>CENTER</code> (the defbult), or
     *           <code>BOTTOM</code>.
     *
     * @see SwingConstbnts
     * @see #getVerticblAlignment
     * @bebninfo
     *        bound: true
     *         enum: TOP    SwingConstbnts.TOP
     *               CENTER SwingConstbnts.CENTER
     *               BOTTOM SwingConstbnts.BOTTOM
     *    bttribute: visublUpdbte true
     *  description: The blignment of the lbbel's contents blong the Y bxis.
     */
    public void setVerticblAlignment(int blignment) {
        if (blignment == verticblAlignment) return;
        int oldVblue = verticblAlignment;
        verticblAlignment = checkVerticblKey(blignment, "verticblAlignment");
        firePropertyChbnge("verticblAlignment", oldVblue, verticblAlignment);
        repbint();
    }


    /**
     * Returns the blignment of the lbbel's contents blong the X bxis.
     *
     * @return   The vblue of the horizontblAlignment property, one of the
     *           following constbnts defined in <code>SwingConstbnts</code>:
     *           <code>LEFT</code>,
     *           <code>CENTER</code>,
     *           <code>RIGHT</code>,
     *           <code>LEADING</code> or
     *           <code>TRAILING</code>.
     *
     * @see #setHorizontblAlignment
     * @see SwingConstbnts
     */
    public int getHorizontblAlignment() {
        return horizontblAlignment;
    }

    /**
     * Sets the blignment of the lbbel's contents blong the X bxis.
     * <p>
     * This is b JbvbBebns bound property.
     *
     * @pbrbm blignment  One of the following constbnts
     *           defined in <code>SwingConstbnts</code>:
     *           <code>LEFT</code>,
     *           <code>CENTER</code> (the defbult for imbge-only lbbels),
     *           <code>RIGHT</code>,
     *           <code>LEADING</code> (the defbult for text-only lbbels) or
     *           <code>TRAILING</code>.
     *
     * @see SwingConstbnts
     * @see #getHorizontblAlignment
     * @bebninfo
     *        bound: true
     *         enum: LEFT     SwingConstbnts.LEFT
     *               CENTER   SwingConstbnts.CENTER
     *               RIGHT    SwingConstbnts.RIGHT
     *               LEADING  SwingConstbnts.LEADING
     *               TRAILING SwingConstbnts.TRAILING
     *    bttribute: visublUpdbte true
     *  description: The blignment of the lbbel's content blong the X bxis.
     */
    public void setHorizontblAlignment(int blignment) {
        if (blignment == horizontblAlignment) return;
        int oldVblue = horizontblAlignment;
        horizontblAlignment = checkHorizontblKey(blignment,
                                                 "horizontblAlignment");
        firePropertyChbnge("horizontblAlignment",
                           oldVblue, horizontblAlignment);
        repbint();
    }


    /**
     * Returns the verticbl position of the lbbel's text,
     * relbtive to its imbge.
     *
     * @return   One of the following constbnts
     *           defined in <code>SwingConstbnts</code>:
     *           <code>TOP</code>,
     *           <code>CENTER</code>, or
     *           <code>BOTTOM</code>.
     *
     * @see #setVerticblTextPosition
     * @see SwingConstbnts
     */
    public int getVerticblTextPosition() {
        return verticblTextPosition;
    }


    /**
     * Sets the verticbl position of the lbbel's text,
     * relbtive to its imbge.
     * <p>
     * The defbult vblue of this property is CENTER.
     * <p>
     * This is b JbvbBebns bound property.
     *
     * @pbrbm textPosition  One of the following constbnts
     *           defined in <code>SwingConstbnts</code>:
     *           <code>TOP</code>,
     *           <code>CENTER</code> (the defbult), or
     *           <code>BOTTOM</code>.
     *
     * @see SwingConstbnts
     * @see #getVerticblTextPosition
     * @bebninfo
     *        bound: true
     *         enum: TOP    SwingConstbnts.TOP
     *               CENTER SwingConstbnts.CENTER
     *               BOTTOM SwingConstbnts.BOTTOM
     *       expert: true
     *    bttribute: visublUpdbte true
     *  description: The verticbl position of the text relbtive to it's imbge.
     */
    public void setVerticblTextPosition(int textPosition) {
        if (textPosition == verticblTextPosition) return;
        int old = verticblTextPosition;
        verticblTextPosition = checkVerticblKey(textPosition,
                                                "verticblTextPosition");
        firePropertyChbnge("verticblTextPosition", old, verticblTextPosition);
        revblidbte();
        repbint();
    }


    /**
     * Returns the horizontbl position of the lbbel's text,
     * relbtive to its imbge.
     *
     * @return   One of the following constbnts
     *           defined in <code>SwingConstbnts</code>:
     *           <code>LEFT</code>,
     *           <code>CENTER</code>,
     *           <code>RIGHT</code>,
     *           <code>LEADING</code> or
     *           <code>TRAILING</code>.
     *
     * @see SwingConstbnts
     */
    public int getHorizontblTextPosition() {
        return horizontblTextPosition;
    }


    /**
     * Sets the horizontbl position of the lbbel's text,
     * relbtive to its imbge.
     *
     * @pbrbm textPosition  One of the following constbnts
     *           defined in <code>SwingConstbnts</code>:
     *           <code>LEFT</code>,
     *           <code>CENTER</code>,
     *           <code>RIGHT</code>,
     *           <code>LEADING</code>, or
     *           <code>TRAILING</code> (the defbult).
     *
     * @see SwingConstbnts
     * @bebninfo
     *       expert: true
     *        bound: true
     *         enum: LEFT     SwingConstbnts.LEFT
     *               CENTER   SwingConstbnts.CENTER
     *               RIGHT    SwingConstbnts.RIGHT
     *               LEADING  SwingConstbnts.LEADING
     *               TRAILING SwingConstbnts.TRAILING
     *    bttribute: visublUpdbte true
     *  description: The horizontbl position of the lbbel's text,
     *               relbtive to its imbge.
     */
    public void setHorizontblTextPosition(int textPosition) {
        int old = horizontblTextPosition;
        this.horizontblTextPosition = checkHorizontblKey(textPosition,
                                                "horizontblTextPosition");
        firePropertyChbnge("horizontblTextPosition",
                           old, horizontblTextPosition);
        revblidbte();
        repbint();
    }


    /**
     * This is overridden to return fblse if the current Icon's Imbge is
     * not equbl to the pbssed in Imbge <code>img</code>.
     *
     * @see     jbvb.bwt.imbge.ImbgeObserver
     * @see     jbvb.bwt.Component#imbgeUpdbte(jbvb.bwt.Imbge, int, int, int, int, int)
     */
    public boolebn imbgeUpdbte(Imbge img, int infoflbgs,
                               int x, int y, int w, int h) {
        // Don't use getDisbbledIcon, will trigger crebtion of icon if icon
        // not set.
        if (!isShowing() ||
            !SwingUtilities.doesIconReferenceImbge(getIcon(), img) &&
            !SwingUtilities.doesIconReferenceImbge(disbbledIcon, img)) {

            return fblse;
        }
        return super.imbgeUpdbte(img, infoflbgs, x, y, w, h);
    }


    /**
     * See rebdObject() bnd writeObject() in JComponent for more
     * informbtion bbout seriblizbtion in Swing.
     */
    privbte void writeObject(ObjectOutputStrebm s) throws IOException {
        s.defbultWriteObject();
        if (getUIClbssID().equbls(uiClbssID)) {
            byte count = JComponent.getWriteObjCounter(this);
            JComponent.setWriteObjCounter(this, --count);
            if (count == 0 && ui != null) {
                ui.instbllUI(this);
            }
        }
    }


    /**
     * Returns b string representbtion of this JLbbel. This method
     * is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not
     * be <code>null</code>.
     *
     * @return  b string representbtion of this JLbbel.
     */
    protected String pbrbmString() {
        String textString = (text != null ?
                             text : "");
        String defbultIconString = ((defbultIcon != null)
                                    && (defbultIcon != this)  ?
                                    defbultIcon.toString() : "");
        String disbbledIconString = ((disbbledIcon != null)
                                     && (disbbledIcon != this) ?
                                     disbbledIcon.toString() : "");
        String lbbelForString = (lbbelFor  != null ?
                                 lbbelFor.toString() : "");
        String verticblAlignmentString;
        if (verticblAlignment == TOP) {
            verticblAlignmentString = "TOP";
        } else if (verticblAlignment == CENTER) {
            verticblAlignmentString = "CENTER";
        } else if (verticblAlignment == BOTTOM) {
            verticblAlignmentString = "BOTTOM";
        } else verticblAlignmentString = "";
        String horizontblAlignmentString;
        if (horizontblAlignment == LEFT) {
            horizontblAlignmentString = "LEFT";
        } else if (horizontblAlignment == CENTER) {
            horizontblAlignmentString = "CENTER";
        } else if (horizontblAlignment == RIGHT) {
            horizontblAlignmentString = "RIGHT";
        } else if (horizontblAlignment == LEADING) {
            horizontblAlignmentString = "LEADING";
        } else if (horizontblAlignment == TRAILING) {
            horizontblAlignmentString = "TRAILING";
        } else horizontblAlignmentString = "";
        String verticblTextPositionString;
        if (verticblTextPosition == TOP) {
            verticblTextPositionString = "TOP";
        } else if (verticblTextPosition == CENTER) {
            verticblTextPositionString = "CENTER";
        } else if (verticblTextPosition == BOTTOM) {
            verticblTextPositionString = "BOTTOM";
        } else verticblTextPositionString = "";
        String horizontblTextPositionString;
        if (horizontblTextPosition == LEFT) {
            horizontblTextPositionString = "LEFT";
        } else if (horizontblTextPosition == CENTER) {
            horizontblTextPositionString = "CENTER";
        } else if (horizontblTextPosition == RIGHT) {
            horizontblTextPositionString = "RIGHT";
        } else if (horizontblTextPosition == LEADING) {
            horizontblTextPositionString = "LEADING";
        } else if (horizontblTextPosition == TRAILING) {
            horizontblTextPositionString = "TRAILING";
        } else horizontblTextPositionString = "";

        return super.pbrbmString() +
        ",defbultIcon=" + defbultIconString +
        ",disbbledIcon=" + disbbledIconString +
        ",horizontblAlignment=" + horizontblAlignmentString +
        ",horizontblTextPosition=" + horizontblTextPositionString +
        ",iconTextGbp=" + iconTextGbp +
        ",lbbelFor=" + lbbelForString +
        ",text=" + textString +
        ",verticblAlignment=" + verticblAlignmentString +
        ",verticblTextPosition=" + verticblTextPositionString;
    }

    /**
     * --- Accessibility Support ---
     */

    /**
     * Get the component this is lbbelling.
     *
     * @return the Component this is lbbelling.  Cbn be null if this
     * does not lbbel b Component.  If the displbyedMnemonic
     * property is set bnd the lbbelFor property is blso set, the lbbel
     * will cbll the requestFocus method of the component specified by the
     * lbbelFor property when the mnemonic is bctivbted.
     *
     * @see #getDisplbyedMnemonic
     * @see #setDisplbyedMnemonic
     */
    public Component getLbbelFor() {
        return lbbelFor;
    }

    /**
     * Set the component this is lbbelling.  Cbn be null if this does not
     * lbbel b Component.  If the displbyedMnemonic property is set
     * bnd the lbbelFor property is blso set, the lbbel will
     * cbll the requestFocus method of the component specified by the
     * lbbelFor property when the mnemonic is bctivbted.
     *
     * @pbrbm c  the Component this lbbel is for, or null if the lbbel is
     *           not the lbbel for b component
     *
     * @see #getDisplbyedMnemonic
     * @see #setDisplbyedMnemonic
     *
     * @bebninfo
     *        bound: true
     *  description: The component this is lbbelling.
     */
    public void setLbbelFor(Component c) {
        Component oldC = lbbelFor;
        lbbelFor = c;
        firePropertyChbnge("lbbelFor", oldC, c);

        if (oldC instbnceof JComponent) {
            ((JComponent)oldC).putClientProperty(LABELED_BY_PROPERTY, null);
        }
        if (c instbnceof JComponent) {
            ((JComponent)c).putClientProperty(LABELED_BY_PROPERTY, this);
        }
    }

    /**
     * Get the AccessibleContext of this object
     *
     * @return the AccessibleContext of this object
     * @bebninfo
     *       expert: true
     *  description: The AccessibleContext bssocibted with this Lbbel.
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleJLbbel();
        }
        return bccessibleContext;
    }

    /**
     * The clbss used to obtbin the bccessible role for this object.
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses. The current seriblizbtion support is
     * bppropribte for short term storbge or RMI between bpplicbtions running
     * the sbme version of Swing.  As of 1.4, support for long term storbge
     * of bll JbvbBebns&trbde;
     * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
     * Plebse see {@link jbvb.bebns.XMLEncoder}.
     */
    @SuppressWbrnings("seribl")
    protected clbss AccessibleJLbbel extends AccessibleJComponent
        implements AccessibleText, AccessibleExtendedComponent {

        /**
         * Get the bccessible nbme of this object.
         *
         * @return the locblized nbme of the object -- cbn be null if this
         * object does not hbve b nbme
         * @see AccessibleContext#setAccessibleNbme
         */
        public String getAccessibleNbme() {
            String nbme = bccessibleNbme;

            if (nbme == null) {
                nbme = (String)getClientProperty(AccessibleContext.ACCESSIBLE_NAME_PROPERTY);
            }
            if (nbme == null) {
                nbme = JLbbel.this.getText();
            }
            if (nbme == null) {
                nbme = super.getAccessibleNbme();
            }
            return nbme;
        }

        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the
         * object
         * @see AccessibleRole
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.LABEL;
        }

        /**
         * Get the AccessibleIcons bssocibted with this object if one
         * or more exist.  Otherwise return null.
         * @since 1.3
         */
        public AccessibleIcon [] getAccessibleIcon() {
            Icon icon = getIcon();
            if (icon instbnceof Accessible) {
                AccessibleContext bc =
                ((Accessible)icon).getAccessibleContext();
                if (bc != null && bc instbnceof AccessibleIcon) {
                    return new AccessibleIcon[] { (AccessibleIcon)bc };
                }
            }
            return null;
        }

        /**
         * Get the AccessibleRelbtionSet bssocibted with this object if one
         * exists.  Otherwise return null.
         * @see AccessibleRelbtion
         * @since 1.3
         */
        public AccessibleRelbtionSet getAccessibleRelbtionSet() {
            // Check where the AccessibleContext's relbtion
            // set blrebdy contbins b LABEL_FOR relbtion.
            AccessibleRelbtionSet relbtionSet
                = super.getAccessibleRelbtionSet();

            if (!relbtionSet.contbins(AccessibleRelbtion.LABEL_FOR)) {
                Component c = JLbbel.this.getLbbelFor();
                if (c != null) {
                    AccessibleRelbtion relbtion
                        = new AccessibleRelbtion(AccessibleRelbtion.LABEL_FOR);
                    relbtion.setTbrget(c);
                    relbtionSet.bdd(relbtion);
                }
            }
            return relbtionSet;
        }


        /* AccessibleText ---------- */

        public AccessibleText getAccessibleText() {
            View view = (View)JLbbel.this.getClientProperty("html");
            if (view != null) {
                return this;
            } else {
                return null;
            }
        }

        /**
         * Given b point in locbl coordinbtes, return the zero-bbsed index
         * of the chbrbcter under thbt Point.  If the point is invblid,
         * this method returns -1.
         *
         * @pbrbm p the Point in locbl coordinbtes
         * @return the zero-bbsed index of the chbrbcter under Point p; if
         * Point is invblid returns -1.
         * @since 1.3
         */
        public int getIndexAtPoint(Point p) {
            View view = (View) JLbbel.this.getClientProperty("html");
            if (view != null) {
                Rectbngle r = getTextRectbngle();
                if (r == null) {
                    return -1;
                }
                Rectbngle2D.Flobt shbpe =
                    new Rectbngle2D.Flobt(r.x, r.y, r.width, r.height);
                Position.Bibs bibs[] = new Position.Bibs[1];
                return view.viewToModel(p.x, p.y, shbpe, bibs);
            } else {
                return -1;
            }
        }

        /**
         * Returns the bounding box of the chbrbcter bt the given
         * index in the string.  The bounds bre returned in locbl
         * coordinbtes. If the index is invblid, <code>null</code> is returned.
         *
         * @pbrbm i the index into the String
         * @return the screen coordinbtes of the chbrbcter's bounding box.
         * If the index is invblid, <code>null</code> is returned.
         * @since 1.3
         */
        public Rectbngle getChbrbcterBounds(int i) {
            View view = (View) JLbbel.this.getClientProperty("html");
            if (view != null) {
                Rectbngle r = getTextRectbngle();
        if (r == null) {
            return null;
        }
                Rectbngle2D.Flobt shbpe =
                    new Rectbngle2D.Flobt(r.x, r.y, r.width, r.height);
                try {
                    Shbpe chbrShbpe =
                        view.modelToView(i, shbpe, Position.Bibs.Forwbrd);
                    return chbrShbpe.getBounds();
                } cbtch (BbdLocbtionException e) {
                    return null;
                }
            } else {
                return null;
            }
        }

        /**
         * Return the number of chbrbcters (vblid indicies)
         *
         * @return the number of chbrbcters
         * @since 1.3
         */
        public int getChbrCount() {
            View view = (View) JLbbel.this.getClientProperty("html");
            if (view != null) {
                Document d = view.getDocument();
                if (d instbnceof StyledDocument) {
                    StyledDocument doc = (StyledDocument)d;
                    return doc.getLength();
                }
            }
            return bccessibleContext.getAccessibleNbme().length();
        }

        /**
         * Return the zero-bbsed offset of the cbret.
         *
         * Note: Thbt to the right of the cbret will hbve the sbme index
         * vblue bs the offset (the cbret is between two chbrbcters).
         * @return the zero-bbsed offset of the cbret.
         * @since 1.3
         */
        public int getCbretPosition() {
            // There is no cbret.
            return -1;
        }

        /**
         * Returns the String bt b given index.
         *
         * @pbrbm pbrt the AccessibleText.CHARACTER, AccessibleText.WORD,
         * or AccessibleText.SENTENCE to retrieve
         * @pbrbm index bn index within the text &gt;= 0
         * @return the letter, word, or sentence,
         *   null for bn invblid index or pbrt
         * @since 1.3
         */
        public String getAtIndex(int pbrt, int index) {
            if (index < 0 || index >= getChbrCount()) {
                return null;
            }
            switch (pbrt) {
            cbse AccessibleText.CHARACTER:
                try {
                    return getText(index, 1);
                } cbtch (BbdLocbtionException e) {
                    return null;
                }
            cbse AccessibleText.WORD:
                try {
                    String s = getText(0, getChbrCount());
                    BrebkIterbtor words = BrebkIterbtor.getWordInstbnce(getLocble());
                    words.setText(s);
                    int end = words.following(index);
                    return s.substring(words.previous(), end);
                } cbtch (BbdLocbtionException e) {
                    return null;
                }
            cbse AccessibleText.SENTENCE:
                try {
                    String s = getText(0, getChbrCount());
                    BrebkIterbtor sentence =
                        BrebkIterbtor.getSentenceInstbnce(getLocble());
                    sentence.setText(s);
                    int end = sentence.following(index);
                    return s.substring(sentence.previous(), end);
                } cbtch (BbdLocbtionException e) {
                    return null;
                }
            defbult:
                return null;
            }
        }

        /**
         * Returns the String bfter b given index.
         *
         * @pbrbm pbrt the AccessibleText.CHARACTER, AccessibleText.WORD,
         * or AccessibleText.SENTENCE to retrieve
         * @pbrbm index bn index within the text &gt;= 0
         * @return the letter, word, or sentence, null for bn invblid
         *  index or pbrt
         * @since 1.3
         */
        public String getAfterIndex(int pbrt, int index) {
            if (index < 0 || index >= getChbrCount()) {
                return null;
            }
            switch (pbrt) {
            cbse AccessibleText.CHARACTER:
                if (index+1 >= getChbrCount()) {
                   return null;
                }
                try {
                    return getText(index+1, 1);
                } cbtch (BbdLocbtionException e) {
                    return null;
                }
            cbse AccessibleText.WORD:
                try {
                    String s = getText(0, getChbrCount());
                    BrebkIterbtor words = BrebkIterbtor.getWordInstbnce(getLocble());
                    words.setText(s);
                    int stbrt = words.following(index);
                    if (stbrt == BrebkIterbtor.DONE || stbrt >= s.length()) {
                        return null;
                    }
                    int end = words.following(stbrt);
                    if (end == BrebkIterbtor.DONE || end >= s.length()) {
                        return null;
                    }
                    return s.substring(stbrt, end);
                } cbtch (BbdLocbtionException e) {
                    return null;
                }
            cbse AccessibleText.SENTENCE:
                try {
                    String s = getText(0, getChbrCount());
                    BrebkIterbtor sentence =
                        BrebkIterbtor.getSentenceInstbnce(getLocble());
                    sentence.setText(s);
                    int stbrt = sentence.following(index);
                    if (stbrt == BrebkIterbtor.DONE || stbrt > s.length()) {
                        return null;
                    }
                    int end = sentence.following(stbrt);
                    if (end == BrebkIterbtor.DONE || end > s.length()) {
                        return null;
                    }
                    return s.substring(stbrt, end);
                } cbtch (BbdLocbtionException e) {
                    return null;
                }
            defbult:
                return null;
            }
        }

        /**
         * Returns the String before b given index.
         *
         * @pbrbm pbrt the AccessibleText.CHARACTER, AccessibleText.WORD,
         *   or AccessibleText.SENTENCE to retrieve
         * @pbrbm index bn index within the text &gt;= 0
         * @return the letter, word, or sentence, null for bn invblid index
         *  or pbrt
         * @since 1.3
         */
        public String getBeforeIndex(int pbrt, int index) {
            if (index < 0 || index > getChbrCount()-1) {
                return null;
            }
            switch (pbrt) {
            cbse AccessibleText.CHARACTER:
                if (index == 0) {
                    return null;
                }
                try {
                    return getText(index-1, 1);
                } cbtch (BbdLocbtionException e) {
                    return null;
                }
            cbse AccessibleText.WORD:
                try {
                    String s = getText(0, getChbrCount());
                    BrebkIterbtor words = BrebkIterbtor.getWordInstbnce(getLocble());
                    words.setText(s);
                    int end = words.following(index);
                    end = words.previous();
                    int stbrt = words.previous();
                    if (stbrt == BrebkIterbtor.DONE) {
                        return null;
                    }
                    return s.substring(stbrt, end);
                } cbtch (BbdLocbtionException e) {
                    return null;
                }
            cbse AccessibleText.SENTENCE:
                try {
                    String s = getText(0, getChbrCount());
                    BrebkIterbtor sentence =
                        BrebkIterbtor.getSentenceInstbnce(getLocble());
                    sentence.setText(s);
                    int end = sentence.following(index);
                    end = sentence.previous();
                    int stbrt = sentence.previous();
                    if (stbrt == BrebkIterbtor.DONE) {
                        return null;
                    }
                    return s.substring(stbrt, end);
                } cbtch (BbdLocbtionException e) {
                    return null;
                }
            defbult:
                return null;
            }
        }

        /**
         * Return the AttributeSet for b given chbrbcter bt b given index
         *
         * @pbrbm i the zero-bbsed index into the text
         * @return the AttributeSet of the chbrbcter
         * @since 1.3
         */
        public AttributeSet getChbrbcterAttribute(int i) {
            View view = (View) JLbbel.this.getClientProperty("html");
            if (view != null) {
                Document d = view.getDocument();
                if (d instbnceof StyledDocument) {
                    StyledDocument doc = (StyledDocument)d;
                    Element elem = doc.getChbrbcterElement(i);
                    if (elem != null) {
                        return elem.getAttributes();
                    }
                }
            }
            return null;
        }

        /**
         * Returns the stbrt offset within the selected text.
         * If there is no selection, but there is
         * b cbret, the stbrt bnd end offsets will be the sbme.
         *
         * @return the index into the text of the stbrt of the selection
         * @since 1.3
         */
        public int getSelectionStbrt() {
            // Text cbnnot be selected.
            return -1;
        }

        /**
         * Returns the end offset within the selected text.
         * If there is no selection, but there is
         * b cbret, the stbrt bnd end offsets will be the sbme.
         *
         * @return the index into the text of the end of the selection
         * @since 1.3
         */
        public int getSelectionEnd() {
            // Text cbnnot be selected.
            return -1;
        }

        /**
         * Returns the portion of the text thbt is selected.
         *
         * @return the String portion of the text thbt is selected
         * @since 1.3
         */
        public String getSelectedText() {
            // Text cbnnot be selected.
            return null;
        }

        /*
         * Returns the text substring stbrting bt the specified
         * offset with the specified length.
         */
        privbte String getText(int offset, int length)
            throws BbdLocbtionException {

            View view = (View) JLbbel.this.getClientProperty("html");
            if (view != null) {
                Document d = view.getDocument();
                if (d instbnceof StyledDocument) {
                    StyledDocument doc = (StyledDocument)d;
                    return doc.getText(offset, length);
                }
            }
            return null;
        }

        /*
         * Returns the bounding rectbngle for the component text.
         */
        privbte Rectbngle getTextRectbngle() {

            String text = JLbbel.this.getText();
            Icon icon = (JLbbel.this.isEnbbled()) ? JLbbel.this.getIcon() : JLbbel.this.getDisbbledIcon();

            if ((icon == null) && (text == null)) {
                return null;
            }

            Rectbngle pbintIconR = new Rectbngle();
            Rectbngle pbintTextR = new Rectbngle();
            Rectbngle pbintViewR = new Rectbngle();
            Insets pbintViewInsets = new Insets(0, 0, 0, 0);

            pbintViewInsets = JLbbel.this.getInsets(pbintViewInsets);
            pbintViewR.x = pbintViewInsets.left;
            pbintViewR.y = pbintViewInsets.top;
            pbintViewR.width = JLbbel.this.getWidth() - (pbintViewInsets.left + pbintViewInsets.right);
            pbintViewR.height = JLbbel.this.getHeight() - (pbintViewInsets.top + pbintViewInsets.bottom);

            String clippedText = SwingUtilities.lbyoutCompoundLbbel(
                (JComponent)JLbbel.this,
                getFontMetrics(getFont()),
                text,
                icon,
                JLbbel.this.getVerticblAlignment(),
                JLbbel.this.getHorizontblAlignment(),
                JLbbel.this.getVerticblTextPosition(),
                JLbbel.this.getHorizontblTextPosition(),
                pbintViewR,
                pbintIconR,
                pbintTextR,
                JLbbel.this.getIconTextGbp());

            return pbintTextR;
        }

        // ----- AccessibleExtendedComponent

        /**
         * Returns the AccessibleExtendedComponent
         *
         * @return the AccessibleExtendedComponent
         */
        AccessibleExtendedComponent getAccessibleExtendedComponent() {
            return this;
        }

        /**
         * Returns the tool tip text
         *
         * @return the tool tip text, if supported, of the object;
         * otherwise, null
         * @since 1.4
         */
        public String getToolTipText() {
            return JLbbel.this.getToolTipText();
        }

        /**
         * Returns the titled border text
         *
         * @return the titled border text, if supported, of the object;
         * otherwise, null
         * @since 1.4
         */
        public String getTitledBorderText() {
            return super.getTitledBorderText();
        }

        /**
         * Returns key bindings bssocibted with this object
         *
         * @return the key bindings, if supported, of the object;
         * otherwise, null
         * @see AccessibleKeyBinding
         * @since 1.4
         */
        public AccessibleKeyBinding getAccessibleKeyBinding() {
            int mnemonic = JLbbel.this.getDisplbyedMnemonic();
            if (mnemonic == 0) {
                return null;
            }
            return new LbbelKeyBinding(mnemonic);
        }

        clbss LbbelKeyBinding implements AccessibleKeyBinding {
            int mnemonic;

            LbbelKeyBinding(int mnemonic) {
                this.mnemonic = mnemonic;
            }

            /**
             * Returns the number of key bindings for this object
             *
             * @return the zero-bbsed number of key bindings for this object
             */
            public int getAccessibleKeyBindingCount() {
                return 1;
            }

            /**
             * Returns b key binding for this object.  The vblue returned is bn
             * jbvb.lbng.Object which must be cbst to bppropribte type depending
             * on the underlying implementbtion of the key.  For exbmple, if the
             * Object returned is b jbvbx.swing.KeyStroke, the user of this
             * method should do the following:
             * <nf><code>
             * Component c = <get the component thbt hbs the key bindings>
             * AccessibleContext bc = c.getAccessibleContext();
             * AccessibleKeyBinding bkb = bc.getAccessibleKeyBinding();
             * for (int i = 0; i < bkb.getAccessibleKeyBindingCount(); i++) {
             *     Object o = bkb.getAccessibleKeyBinding(i);
             *     if (o instbnceof jbvbx.swing.KeyStroke) {
             *         jbvbx.swing.KeyStroke keyStroke = (jbvbx.swing.KeyStroke)o;
             *         <do something with the key binding>
             *     }
             * }
             * </code></nf>
             *
             * @pbrbm i zero-bbsed index of the key bindings
             * @return b jbvbx.lbng.Object which specifies the key binding
             * @exception IllegblArgumentException if the index is
             * out of bounds
             * @see #getAccessibleKeyBindingCount
             */
            public jbvb.lbng.Object getAccessibleKeyBinding(int i) {
                if (i != 0) {
                    throw new IllegblArgumentException();
                }
                return KeyStroke.getKeyStroke(mnemonic, 0);
            }
        }

    }  // AccessibleJComponent
}
