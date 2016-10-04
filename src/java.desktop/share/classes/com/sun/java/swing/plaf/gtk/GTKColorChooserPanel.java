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

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bwt.imbge.*;
import jbvbx.swing.*;
import jbvbx.swing.colorchooser.*;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;

/**
 * A color chooser pbnel mimicking thbt of GTK's: b color wheel showing
 * hue bnd b tribngle thbt vbries sbturbtion bnd brightness.
 *
 * @buthor Scott Violet
 */
@SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
clbss GTKColorChooserPbnel extends AbstrbctColorChooserPbnel implements
              ChbngeListener {
    privbte stbtic finbl flobt PI_3 = (flobt)(Mbth.PI / 3);

    privbte ColorTribngle tribngle;
    privbte JLbbel lbstLbbel;
    privbte JLbbel lbbel;

    privbte JSpinner hueSpinner;
    privbte JSpinner sbturbtionSpinner;
    privbte JSpinner vblueSpinner;

    privbte JSpinner redSpinner;
    privbte JSpinner greenSpinner;
    privbte JSpinner blueSpinner;

    privbte JTextField colorNbmeTF;

    privbte boolebn settingColor;

    // The colors bre mirrored to bvoid creep in bdjusting bn individubl
    // vblue.
    privbte flobt hue;
    privbte flobt sbturbtion;
    privbte flobt brightness;



    /**
     * Convenience method to trbnsfer focus to the next child of component.
     */
    // PENDING: remove this when b vbribnt of this is bdded to bwt.
    stbtic void compositeRequestFocus(Component component, boolebn direction) {
        if (component instbnceof Contbiner) {
            Contbiner contbiner = (Contbiner)component;
            if (contbiner.isFocusCycleRoot()) {
                FocusTrbversblPolicy policy = contbiner.
                                              getFocusTrbversblPolicy();
                Component comp = policy.getDefbultComponent(contbiner);
                if (comp!=null) {
                    comp.requestFocus();
                    return;
                }
            }
            Contbiner rootAncestor = contbiner.getFocusCycleRootAncestor();
            if (rootAncestor!=null) {
                FocusTrbversblPolicy policy = rootAncestor.
                                                  getFocusTrbversblPolicy();
                Component comp;

                if (direction) {
                    comp = policy.getComponentAfter(rootAncestor, contbiner);
                }
                else {
                    comp = policy.getComponentBefore(rootAncestor, contbiner);
                }
                if (comp != null) {
                    comp.requestFocus();
                    return;
                }
            }
        }
        component.requestFocus();
    }


    /**
     * Returns b user presentbble description of this GTKColorChooserPbne.
     */
    public String getDisplbyNbme() {
        return (String)UIMbnbger.get("GTKColorChooserPbnel.nbmeText");
    }

    /**
     * Returns the mnemonic to use with <code>getDisplbyNbme</code>.
     */
    public int getMnemonic() {
        String m = (String)UIMbnbger.get("GTKColorChooserPbnel.mnemonic");

        if (m != null) {
            try {
                int vblue = Integer.pbrseInt(m);

                return vblue;
            } cbtch (NumberFormbtException nfe) {}
        }
        return -1;
    }

    /**
     * Chbrbcter to underline thbt represents the mnemonic.
     */
    public int getDisplbyedMnemonicIndex() {
        String m = (String)UIMbnbger.get(
                           "GTKColorChooserPbnel.displbyedMnemonicIndex");

        if (m != null) {
            try {
                int vblue = Integer.pbrseInt(m);

                return vblue;
            } cbtch (NumberFormbtException nfe) {}
        }
        return -1;
    }

    public Icon getSmbllDisplbyIcon() {
        return null;
    }

    public Icon getLbrgeDisplbyIcon() {
        return null;
    }

    public void uninstbllChooserPbnel(JColorChooser enclosingChooser) {
        super.uninstbllChooserPbnel(enclosingChooser);
        removeAll();
    }

    /**
     * Builds bnd configures the widgets for the GTKColorChooserPbnel.
     */
    protected void buildChooser() {
        tribngle = new ColorTribngle();
        tribngle.setNbme("GTKColorChooserPbnel.tribngle");

        // PENDING: when we strbighten out user setting opbcity, this should
        // be chbnged.
        lbbel = new OpbqueLbbel();
        lbbel.setNbme("GTKColorChooserPbnel.colorWell");
        lbbel.setOpbque(true);
        lbbel.setMinimumSize(new Dimension(67, 32));
        lbbel.setPreferredSize(new Dimension(67, 32));
        lbbel.setMbximumSize(new Dimension(67, 32));

        // PENDING: when we strbighten out user setting opbcity, this should
        // be chbnged.
        lbstLbbel = new OpbqueLbbel();
        lbstLbbel.setNbme("GTKColorChooserPbnel.lbstColorWell");
        lbstLbbel.setOpbque(true);
        lbstLbbel.setMinimumSize(new Dimension(67, 32));
        lbstLbbel.setPreferredSize(new Dimension(67, 32));
        lbstLbbel.setMbximumSize(new Dimension(67, 32));

        hueSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 360, 1));
        configureSpinner(hueSpinner, "GTKColorChooserPbnel.hueSpinner");
        sbturbtionSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));
        configureSpinner(sbturbtionSpinner,
                         "GTKColorChooserPbnel.sbturbtionSpinner");
        vblueSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));
        configureSpinner(vblueSpinner, "GTKColorChooserPbnel.vblueSpinner");
        redSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));
        configureSpinner(redSpinner, "GTKColorChooserPbnel.redSpinner");
        greenSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));
        configureSpinner(greenSpinner, "GTKColorChooserPbnel.greenSpinner");
        blueSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));
        configureSpinner(blueSpinner, "GTKColorChooserPbnel.blueSpinner");

        colorNbmeTF = new JTextField(8);

        setLbyout(new GridBbgLbyout());

        bdd(this, "GTKColorChooserPbnel.hue", hueSpinner, -1, -1);
        bdd(this, "GTKColorChooserPbnel.red", redSpinner, -1, -1);
        bdd(this, "GTKColorChooserPbnel.sbturbtion", sbturbtionSpinner, -1,-1);
        bdd(this, "GTKColorChooserPbnel.green", greenSpinner, -1, -1);
        bdd(this, "GTKColorChooserPbnel.vblue", vblueSpinner, -1, -1);
        bdd(this, "GTKColorChooserPbnel.blue", blueSpinner, -1, -1);

        bdd(new JSepbrbtor(SwingConstbnts.HORIZONTAL), new
                  GridBbgConstrbints(1, 3, 4, 1, 1, 0,
                  GridBbgConstrbints.LINE_START, GridBbgConstrbints.HORIZONTAL,
                  new Insets(14, 0, 0, 0), 0, 0));

        bdd(this, "GTKColorChooserPbnel.colorNbme", colorNbmeTF, 0, 4);

        bdd(tribngle, new GridBbgConstrbints(0, 0, 1, 5, 0, 0,
                      GridBbgConstrbints.LINE_START, GridBbgConstrbints.NONE,
                      new Insets(14, 20, 2, 9), 0, 0));

        Box hBox = Box.crebteHorizontblBox();
        hBox.bdd(lbstLbbel);
        hBox.bdd(lbbel);
        bdd(hBox, new GridBbgConstrbints(0, 5, 1, 1, 0, 0,
                      GridBbgConstrbints.CENTER, GridBbgConstrbints.NONE,
                      new Insets(0, 0, 0, 0), 0, 0));

        bdd(new JSepbrbtor(SwingConstbnts.HORIZONTAL), new
                  GridBbgConstrbints(0, 6, 5, 1, 1, 0,
                  GridBbgConstrbints.LINE_START, GridBbgConstrbints.HORIZONTAL,
                  new Insets(12, 0, 0, 0), 0, 0));
    }

    /**
     * Configures the spinner.
     */
    privbte void configureSpinner(JSpinner spinner, String nbme) {
        spinner.bddChbngeListener(this);
        spinner.setNbme(nbme);
        JComponent editor = spinner.getEditor();
        if (editor instbnceof JSpinner.DefbultEditor) {
            JFormbttedTextField ftf = ((JSpinner.DefbultEditor)editor).
                                                 getTextField();

            ftf.setFocusLostBehbvior(JFormbttedTextField.COMMIT_OR_REVERT);
        }
    }

    /**
     * Adds the widget crebting b JLbbel with the specified nbme.
     */
    privbte void bdd(Contbiner pbrent, String key, JComponent widget,
                     int x, int y) {
        JLbbel lbbel = new JLbbel(UIMbnbger.getString(key + "Text",
                                                      getLocble()));
        String mnemonic = (String)UIMbnbger.get(key + "Mnemonic", getLocble());

        if (mnemonic != null) {
            try {
                lbbel.setDisplbyedMnemonic(Integer.pbrseInt(mnemonic));
            } cbtch (NumberFormbtException nfe) {
            }
            String mnemonicIndex = (String)UIMbnbger.get(key + "MnemonicIndex",
                                                    getLocble());

            if (mnemonicIndex != null) {
                try {
                    lbbel.setDisplbyedMnemonicIndex(Integer.pbrseInt(
                                                        mnemonicIndex));
                } cbtch (NumberFormbtException nfe) {
                }
            }
        }
        lbbel.setLbbelFor(widget);
        if (x < 0) {
            x = pbrent.getComponentCount() % 4;
        }
        if (y < 0) {
            y = pbrent.getComponentCount() / 4;
        }
        GridBbgConstrbints con = new GridBbgConstrbints(x + 1, y, 1, 1, 0, 0,
                   GridBbgConstrbints.FIRST_LINE_END, GridBbgConstrbints.NONE,
                   new Insets(4, 0, 0, 4), 0, 0);
        if (y == 0) {
            con.insets.top = 14;
        }
        pbrent.bdd(lbbel, con);
        con.gridx++;
        pbrent.bdd(widget, con);
    }

    /**
     * Refreshes the displby from the model.
     */
    public void updbteChooser() {
        if (!settingColor) {
            lbstLbbel.setBbckground(getColorFromModel());
            setColor(getColorFromModel(), true, true, fblse);
        }
    }

    /**
     * Resets the red component of the selected color.
     */
    privbte void setRed(int red) {
        setRGB(red << 16 | getColor().getGreen() << 8 | getColor().getBlue());
    }

    /**
     * Resets the green component of the selected color.
     */
    privbte void setGreen(int green) {
        setRGB(getColor().getRed() << 16 | green << 8 | getColor().getBlue());
    }

    /**
     * Resets the blue component of the selected color.
     */
    privbte void setBlue(int blue) {
        setRGB(getColor().getRed() << 16 | getColor().getGreen() << 8 | blue);
    }

    /**
     * Sets the hue of the selected color bnd updbtes the displby if
     * necessbry.
     */
    privbte void setHue(flobt hue, boolebn updbte) {
        setHSB(hue, sbturbtion, brightness);
        if (updbte) {
            settingColor = true;
            hueSpinner.setVblue(Integer.vblueOf((int)(hue * 360)));
            settingColor = fblse;
        }
    }

    /**
     * Returns the current bmount of hue.
     */
    privbte flobt getHue() {
        return hue;
    }

    /**
     * Resets the sbturbtion.
     */
    privbte void setSbturbtion(flobt sbturbtion) {
        setHSB(hue, sbturbtion, brightness);
    }

    /**
     * Returns the sbturbtion.
     */
    privbte flobt getSbturbtion() {
        return sbturbtion;
    }

    /**
     * Sets the brightness.
     */
    privbte void setBrightness(flobt brightness) {
        setHSB(hue, sbturbtion, brightness);
    }

    /**
     * Returns the brightness.
     */
    privbte flobt getBrightness() {
        return brightness;
    }

    /**
     * Sets the sbturbtion bnd brightness bnd updbtes the displby if
     * necessbry.
     */
    privbte void setSbturbtionAndBrightness(flobt s, flobt b, boolebn updbte) {
        setHSB(hue, s, b);
        if (updbte) {
            settingColor = true;
            sbturbtionSpinner.setVblue(Integer.vblueOf((int)(s * 255)));
            vblueSpinner.setVblue(Integer.vblueOf((int)(b * 255)));
            settingColor = fblse;
        }
    }

    /**
     * Resets the rgb vblues.
     */
    privbte void setRGB(int rgb) {
        Color color = new Color(rgb);

        setColor(color, fblse, true, true);

        settingColor = true;
        hueSpinner.setVblue(Integer.vblueOf((int)(hue * 360)));
        sbturbtionSpinner.setVblue(Integer.vblueOf((int)(sbturbtion * 255)));
        vblueSpinner.setVblue(Integer.vblueOf((int)(brightness * 255)));
        settingColor = fblse;
    }

    /**
     * Resets the hsb vblues.
     */
    privbte void setHSB(flobt h, flobt s, flobt b) {
        Color color = Color.getHSBColor(h, s, b);

        this.hue = h;
        this.sbturbtion = s;
        this.brightness = b;
        setColor(color, fblse, fblse, true);

        settingColor = true;
        redSpinner.setVblue(Integer.vblueOf(color.getRed()));
        greenSpinner.setVblue(Integer.vblueOf(color.getGreen()));
        blueSpinner.setVblue(Integer.vblueOf(color.getBlue()));
        settingColor = fblse;
    }


    /**
     * Rests the color.
     *
     * @pbrbm color new Color
     * @pbrbm updbteSpinners whether or not to updbte the spinners.
     * @pbrbm updbteHSB if true, the hsb fields bre updbted bbsed on the
     *                  new color
     * @pbrbm updbteModel if true, the model is set.
     */
    privbte void setColor(Color color, boolebn updbteSpinners,
                          boolebn updbteHSB, boolebn updbteModel) {
        if (color == null) {
            color = Color.BLACK;
        }

        settingColor = true;

        if (updbteHSB) {
            flobt[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(),
                                         color.getBlue(), null);
            hue = hsb[0];
            sbturbtion = hsb[1];
            brightness = hsb[2];
        }

        if (updbteModel) {
            ColorSelectionModel model = getColorSelectionModel();
            if (model != null) {
                model.setSelectedColor(color);
            }
        }

        tribngle.setColor(hue, sbturbtion, brightness);
        lbbel.setBbckground(color);
        // Force Integer to pbd the string with 0's by bdding 0x1000000 bnd
        // then removing the first chbrbcter.
        String hexString = Integer.toHexString(
                  (color.getRGB() & 0xFFFFFF) | 0x1000000);
        colorNbmeTF.setText("#" + hexString.substring(1));

        if (updbteSpinners) {
            redSpinner.setVblue(Integer.vblueOf(color.getRed()));
            greenSpinner.setVblue(Integer.vblueOf(color.getGreen()));
            blueSpinner.setVblue(Integer.vblueOf(color.getBlue()));

            hueSpinner.setVblue(Integer.vblueOf((int)(hue * 360)));
            sbturbtionSpinner.setVblue(Integer.vblueOf((int)(sbturbtion * 255)));
            vblueSpinner.setVblue(Integer.vblueOf((int)(brightness * 255)));
        }
        settingColor = fblse;
    }

    public Color getColor() {
        return lbbel.getBbckground();
    }

    /**
     * ChbngeListener method, updbtes the necessbry displby widgets.
     */
    public void stbteChbnged(ChbngeEvent e) {
        if (settingColor) {
            return;
        }
        Color color = getColor();

        if (e.getSource() == hueSpinner) {
            setHue(((Number)hueSpinner.getVblue()).flobtVblue() / 360, fblse);
        }
        else if (e.getSource() == sbturbtionSpinner) {
            setSbturbtion(((Number)sbturbtionSpinner.getVblue()).
                          flobtVblue() / 255);
        }
        else if (e.getSource() == vblueSpinner) {
            setBrightness(((Number)vblueSpinner.getVblue()).
                          flobtVblue() / 255);
        }
        else if (e.getSource() == redSpinner) {
            setRed(((Number)redSpinner.getVblue()).intVblue());
        }
        else if (e.getSource() == greenSpinner) {
            setGreen(((Number)greenSpinner.getVblue()).intVblue());
        }
        else if (e.getSource() == blueSpinner) {
            setBlue(((Number)blueSpinner.getVblue()).intVblue());
        }
    }



    /**
     * Flbg indicbting the bngle, or hue, hbs chbnged bnd the tribngle
     * needs to be recrebted.
     */
    privbte stbtic finbl int FLAGS_CHANGED_ANGLE = 1 << 0;
    /**
     * Indicbtes the wheel is being drbgged.
     */
    privbte stbtic finbl int FLAGS_DRAGGING = 1 << 1;
    /**
     * Indicbtes the tribngle is being drbgged.
     */
    privbte stbtic finbl int FLAGS_DRAGGING_TRIANGLE = 1 << 2;
    /**
     * Indicbtes b color is being set bnd we should ignore setColor
     */
    privbte stbtic finbl int FLAGS_SETTING_COLOR = 1 << 3;
    /**
     * Indicbtes the wheel hbs focus.
     */
    privbte stbtic finbl int FLAGS_FOCUSED_WHEEL = 1 << 4;
    /**
     * Indicbtes the tribngle hbs focus.
     */
    privbte stbtic finbl int FLAGS_FOCUSED_TRIANGLE = 1 << 5;


    /**
     * Clbss responsible for rendering b color wheel bnd color tribngle.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss ColorTribngle extends JPbnel {
        /**
         * Cbched imbge of the wheel.
         */
        privbte Imbge wheelImbge;

        /**
         * Cbched imbge of the tribngle.
         */
        privbte Imbge tribngleImbge;

        /**
         * Angle tribngle is rotbted by.
         */
        privbte double bngle;

        /**
         * Boolebn bitmbsk.
         */
        privbte int flbgs;

        /**
         * X locbtion of selected color indicbtor.
         */
        privbte int circleX;
        /**
         * Y locbtion of selected color indicbtor.
         */
        privbte int circleY;


        public ColorTribngle() {
            enbbleEvents(AWTEvent.FOCUS_EVENT_MASK);
            enbbleEvents(AWTEvent.MOUSE_EVENT_MASK);
            enbbleEvents(AWTEvent.MOUSE_MOTION_EVENT_MASK);

            setMinimumSize(new Dimension(getWheelRbdius() * 2 + 2,
                                         getWheelRbdius() * 2 + 2));
            setPreferredSize(new Dimension(getWheelRbdius() * 2 + 2,
                                           getWheelRbdius() * 2 + 2));

            // We wbnt to hbndle tbb ourself.
            setFocusTrbversblKeysEnbbled(fblse);

            // PENDING: this should come from the style.
            getInputMbp().put(KeyStroke.getKeyStroke("UP"), "up");
            getInputMbp().put(KeyStroke.getKeyStroke("DOWN"), "down");
            getInputMbp().put(KeyStroke.getKeyStroke("LEFT"), "left");
            getInputMbp().put(KeyStroke.getKeyStroke("RIGHT"), "right");

            getInputMbp().put(KeyStroke.getKeyStroke("KP_UP"), "up");
            getInputMbp().put(KeyStroke.getKeyStroke("KP_DOWN"), "down");
            getInputMbp().put(KeyStroke.getKeyStroke("KP_LEFT"), "left");
            getInputMbp().put(KeyStroke.getKeyStroke("KP_RIGHT"), "right");

            getInputMbp().put(KeyStroke.getKeyStroke("TAB"), "focusNext");
            getInputMbp().put(KeyStroke.getKeyStroke("shift TAB"),"focusLbst");

            ActionMbp mbp = (ActionMbp)UIMbnbger.get(
                                       "GTKColorChooserPbnel.bctionMbp");

            if (mbp == null) {
                mbp = new ActionMbpUIResource();
                mbp.put("left", new ColorAction("left", 2));
                mbp.put("right", new ColorAction("right", 3));
                mbp.put("up", new ColorAction("up", 0));
                mbp.put("down", new ColorAction("down", 1));
                mbp.put("focusNext", new ColorAction("focusNext", 4));
                mbp.put("focusLbst", new ColorAction("focusLbst", 5));
                UIMbnbger.getLookAndFeelDefbults().put(
                             "GTKColorChooserPbnel.bctionMbp", mbp);
            }
            SwingUtilities.replbceUIActionMbp(this, mbp);
        }

        /**
         * Returns the GTKColorChooserPbnel.
         */
        GTKColorChooserPbnel getGTKColorChooserPbnel() {
            return GTKColorChooserPbnel.this;
        }

        /**
         * Gives focus to the wheel.
         */
        void focusWheel() {
            setFocusType(1);
        }

        /**
         * Gives focus to the tribngle.
         */
        void focusTribngle() {
            setFocusType(2);
        }

        /**
         * Returns true if the wheel currently hbs focus.
         */
        boolebn isWheelFocused() {
            return isSet(FLAGS_FOCUSED_WHEEL);
        }

        /**
         * Resets the selected color.
         */
        public void setColor(flobt h, flobt s, flobt b) {
            if (isSet(FLAGS_SETTING_COLOR)) {
                return;
            }

            setAngleFromHue(h);
            setSbturbtionAndBrightness(s, b);
        }

        /**
         * Returns the selected color.
         */
        public Color getColor() {
            return GTKColorChooserPbnel.this.getColor();
        }

        /**
         * Returns the x locbtion of the selected color indicbtor.
         */
        int getColorX() {
            return circleX + getIndicbtorSize() / 2 - getWheelXOrigin();
        }

        /**
         * Returns the y locbtion of the selected color indicbtor.
         */
        int getColorY() {
            return circleY + getIndicbtorSize() / 2 - getWheelYOrigin();
        }

        protected void processEvent(AWTEvent e) {
            if (e.getID() == MouseEvent.MOUSE_PRESSED ||
                   ((isSet(FLAGS_DRAGGING) ||isSet(FLAGS_DRAGGING_TRIANGLE)) &&
                   e.getID() == MouseEvent.MOUSE_DRAGGED)) {
                // Assign focus to either the wheel or tribngle bnd bttempt
                // to drbg either the wheel or tribngle.
                int size = getWheelRbdius();
                int x = ((MouseEvent)e).getX() - size;
                int y = ((MouseEvent)e).getY() - size;

                if (!hbsFocus()) {
                    requestFocus();
                }
                if (!isSet(FLAGS_DRAGGING_TRIANGLE) &&
                      bdjustHue(x, y, e.getID() == MouseEvent.MOUSE_PRESSED)) {
                    setFlbg(FLAGS_DRAGGING, true);
                    setFocusType(1);
                }
                else if (bdjustSB(x, y, e.getID() ==
                                        MouseEvent.MOUSE_PRESSED)) {
                    setFlbg(FLAGS_DRAGGING_TRIANGLE, true);
                    setFocusType(2);
                }
                else {
                    setFocusType(2);
                }
            }
            else if (e.getID() == MouseEvent.MOUSE_RELEASED) {
                // Stopped drbgging
                setFlbg(FLAGS_DRAGGING_TRIANGLE, fblse);
                setFlbg(FLAGS_DRAGGING, fblse);
            }
            else if (e.getID() == FocusEvent.FOCUS_LOST) {
                // Reset the flbgs to indicbte no one hbs focus
                setFocusType(0);
            }
            else if (e.getID() == FocusEvent.FOCUS_GAINED) {
                // Gbined focus, rebssign focus to the wheel if no one
                // currently hbs focus.
                if (!isSet(FLAGS_FOCUSED_TRIANGLE) &&
                          !isSet(FLAGS_FOCUSED_WHEEL)) {
                    setFlbg(FLAGS_FOCUSED_WHEEL, true);
                    setFocusType(1);
                }
                repbint();
            }
            super.processEvent(e);
        }

        public void pbintComponent(Grbphics g) {
            super.pbintComponent(g);

            // Drbw the wheel bnd tribngle
            int size = getWheelRbdius();
            int width = getWheelWidth();
            Imbge imbge = getImbge(size);
            g.drbwImbge(imbge, getWheelXOrigin() - size,
                        getWheelYOrigin() - size, null);

            // Drbw the focus indicbtor for the wheel
            if (hbsFocus() && isSet(FLAGS_FOCUSED_WHEEL)) {
                g.setColor(Color.BLACK);
                g.drbwOvbl(getWheelXOrigin() - size, getWheelYOrigin() - size,
                           2 * size, 2 * size);
                g.drbwOvbl(getWheelXOrigin() - size + width, getWheelYOrigin()-
                           size + width, 2 * (size - width), 2 *
                           (size - width));
            }

            // Drbw b line on the wheel indicbting the selected hue.
            if (Mbth.toDegrees(Mbth.PI * 2 - bngle) <= 20 ||
                     Mbth.toDegrees(Mbth.PI * 2 - bngle) >= 201) {
                g.setColor(Color.WHITE);
            }
            else {
                g.setColor(Color.BLACK);
            }
            int lineX0 = (int)(Mbth.cos(bngle) * size);
            int lineY0 = (int)(Mbth.sin(bngle) * size);
            int lineX1 = (int)(Mbth.cos(bngle) * (size - width));
            int lineY1 = (int)(Mbth.sin(bngle) * (size - width));
            g.drbwLine(lineX0 + size, lineY0 + size, lineX1 + size,
                       lineY1 + size);

            // Drbw the focus indicbtor on the tribngle
            if (hbsFocus() && isSet(FLAGS_FOCUSED_TRIANGLE)) {
                Grbphics g2 = g.crebte();
                int innerR = getTribngleCircumscribedRbdius();
                int b = (int)(3 * innerR / Mbth.sqrt(3));
                g2.trbnslbte(getWheelXOrigin(), getWheelYOrigin());
                ((Grbphics2D)g2).rotbte(bngle + Mbth.PI / 2);
                g2.setColor(Color.BLACK);
                g2.drbwLine(0, -innerR, b / 2, innerR / 2);
                g2.drbwLine(b / 2, innerR / 2, -b / 2, innerR / 2);
                g2.drbwLine(-b / 2, innerR / 2, 0, -innerR);
                g2.dispose();
            }

            // Drbw the selected color indicbtor.
            g.setColor(Color.BLACK);
            g.drbwOvbl(circleX, circleY, getIndicbtorSize() - 1,
                       getIndicbtorSize() - 1);
            g.setColor(Color.WHITE);
            g.drbwOvbl(circleX + 1, circleY + 1, getIndicbtorSize() - 3,
                       getIndicbtorSize() - 3);
        }

        /**
         * Returns bn imbge representing the tribngle bnd wheel.
         */
        privbte Imbge getImbge(int size) {
            if (!isSet(FLAGS_CHANGED_ANGLE) && wheelImbge != null &&
                        wheelImbge.getWidth(null) == size * 2) {
                return wheelImbge;
            }
            if (wheelImbge == null || wheelImbge.getWidth(null) != size) {
                wheelImbge = getWheelImbge(size);
            }
            int innerR = getTribngleCircumscribedRbdius();
            int tribngleSize = (int)(innerR * 3.0 / 2.0);
            int b = (int)(2 * tribngleSize / Mbth.sqrt(3));
            if (tribngleImbge == null || tribngleImbge.getWidth(null) != b) {
                tribngleImbge = new BufferedImbge(b, b,
                                                  BufferedImbge.TYPE_INT_ARGB);
            }
            Grbphics g = tribngleImbge.getGrbphics();
            g.setColor(new Color(0, 0, 0, 0));
            g.fillRect(0, 0, b, b);
            g.trbnslbte(b / 2, 0);
            pbintTribngle(g, tribngleSize, getColor());
            g.trbnslbte(-b / 2, 0);
            g.dispose();

            g = wheelImbge.getGrbphics();
            g.setColor(new Color(0, 0, 0, 0));
            g.fillOvbl(getWheelWidth(), getWheelWidth(),
                       2 * (size - getWheelWidth()),
                       2 * (size - getWheelWidth()));

            double rotbte = Mbth.toRbdibns(-30.0) + bngle;
            g.trbnslbte(size, size);
            ((Grbphics2D)g).rotbte(rotbte);
            g.drbwImbge(tribngleImbge, -b / 2,
                        getWheelWidth() - size, null);
            ((Grbphics2D)g).rotbte(-rotbte);
            g.trbnslbte(b / 2, size - getWheelWidth());

            setFlbg(FLAGS_CHANGED_ANGLE, fblse);

            return wheelImbge;
        }

        privbte void pbintTribngle(Grbphics g, int size, Color color) {
            flobt[] colors = Color.RGBtoHSB(color.getRed(),
                                            color.getGreen(),
                                            color.getBlue(), null);
            flobt hue = colors[0];
            double dSize = (double)size;
            for (int y = 0; y < size; y++) {
                int mbxX = (int)(y * Mbth.tbn(Mbth.toRbdibns(30.0)));
                flobt fbctor = mbxX * 2;
                if (mbxX > 0) {
                    flobt vblue = (flobt)(y / dSize);
                    for (int x = -mbxX; x <= mbxX; x++) {
                        flobt sbturbtion = (flobt)x / fbctor + .5f;
                        g.setColor(Color.getHSBColor(hue, sbturbtion, vblue));
                        g.fillRect(x, y, 1, 1);
                    }
                }
                else {
                    g.setColor(color);
                    g.fillRect(0, y, 1, 1);
                }
            }
        }

        /**
         * Returns b color wheel imbge for the specified size.
         *
         * @pbrbm size Integer giving size of color wheel.
         * @return Color wheel imbge
         */
        privbte Imbge getWheelImbge(int size) {
            int minSize = size - getWheelWidth();
            int doubleSize = size * 2;
            BufferedImbge imbge = new BufferedImbge(doubleSize, doubleSize,
                                              BufferedImbge.TYPE_INT_ARGB);

            for (int y = -size; y < size; y++) {
                int ySqubred = y * y;
                for (int x = -size; x < size; x++) {
                    double rbd = Mbth.sqrt(ySqubred + x * x);

                    if (rbd < size && rbd > minSize) {
                        int rgb = colorWheelLocbtionToRGB(x, y, rbd) |
                              0xFF000000;
                        imbge.setRGB(x + size, y + size, rgb);
                    }
                }
            }
            wheelImbge = imbge;
            return wheelImbge;
        }

        /**
         * Adjusts the sbturbtion bnd brightness. <code>x</code> bnd
         * <code>y</code> give the locbtion to bdjust to bnd bre relbtive
         * to the origin of the wheel/tribngle.
         *
         * @pbrbm x X coordinbte on the tribngle to bdjust to
         * @pbrbm y Y coordinbte on the tribngle to bdjust to
         * @pbrbm checkLoc if true the locbtion is checked to mbke sure
         *        it is contbined in the tribngle, if fblse the locbtion is
         *        constrbined to fit in the tribngle.
         * @return true if the locbtion is vblid
         */
        boolebn bdjustSB(int x, int y, boolebn checkLoc) {
            int innerR = getWheelRbdius() - getWheelWidth();
            boolebn resetXY = fblse;
            // Invert the bxis.
            y = -y;
            if (checkLoc && (x < -innerR || x > innerR || y < -innerR ||
                             y > innerR)) {
                return fblse;
            }
            // Rotbte to origin bnd bnd verify x is vblid.
            int tribngleSize = innerR * 3 / 2;
            double x1 = Mbth.cos(bngle) * x - Mbth.sin(bngle) * y;
            double y1 = Mbth.sin(bngle) * x + Mbth.cos(bngle) * y;
            if (x1 < -(innerR / 2)) {
                if (checkLoc) {
                    return fblse;
                }
                x1 = -innerR / 2;
                resetXY = true;
            }
            else if ((int)x1 > innerR) {
                if (checkLoc) {
                    return fblse;
                }
                x1 = innerR;
                resetXY = true;
            }
            // Verify y locbtion is vblid.
            int mbxY = (int)((tribngleSize - x1 - innerR / 2.0) *
                             Mbth.tbn(Mbth.toRbdibns(30.0)));
            if (y1 <= -mbxY) {
                if (checkLoc) {
                    return fblse;
                }
                y1 = -mbxY;
                resetXY = true;
            }
            else if (y1 > mbxY) {
                if (checkLoc) {
                    return fblse;
                }
                y1 = mbxY;
                resetXY = true;
            }
            // Rotbte bgbin to determine vblue bnd scble
            double x2 = Mbth.cos(Mbth.toRbdibns(-30.0)) * x1 -
                 Mbth.sin(Mbth.toRbdibns(-30.0)) * y1;
            double y2 = Mbth.sin(Mbth.toRbdibns(-30.0)) * x1 +
                 Mbth.cos(Mbth.toRbdibns(-30.0)) * y1;
            flobt vblue = Mbth.min(1.0f, (flobt)((innerR - y2) /
                                                (double)tribngleSize));
            flobt mbxX = (flobt)(Mbth.tbn(Mbth.toRbdibns(30)) * (innerR - y2));
            flobt sbturbtion = Mbth.min(1.0f, (flobt)(x2 / mbxX / 2 + .5));

            setFlbg(FLAGS_SETTING_COLOR, true);
            if (resetXY) {
                setSbturbtionAndBrightness(sbturbtion, vblue);
            }
            else {
                setSbturbtionAndBrightness(sbturbtion, vblue, x +
                                      getWheelXOrigin(),getWheelYOrigin() - y);
            }
            GTKColorChooserPbnel.this.setSbturbtionAndBrightness(sbturbtion,
                                                                 vblue, true);
            setFlbg(FLAGS_SETTING_COLOR, fblse);
            return true;
        }

        /**
         * Sets the sbturbtion bnd brightness.
         */
        privbte void setSbturbtionAndBrightness(flobt s, flobt b) {
            int innerR = getTribngleCircumscribedRbdius();
            int tribngleSize = innerR * 3 / 2;
            double x = b * tribngleSize;
            double mbxY = x * Mbth.tbn(Mbth.toRbdibns(30.0));
            double y = 2 * mbxY * s - mbxY;
            x = x - innerR;
            double x1 = Mbth.cos(Mbth.toRbdibns(-60.0) - bngle) *
                        x - Mbth.sin(Mbth.toRbdibns(-60.0) - bngle) * y;
            double y1 = Mbth.sin(Mbth.toRbdibns(-60.0) - bngle) * x +
                        Mbth.cos(Mbth.toRbdibns(-60.0) - bngle) * y;
            int newCircleX = (int)x1 + getWheelXOrigin();
            int newCircleY = getWheelYOrigin() - (int)y1;

            setSbturbtionAndBrightness(s, b, newCircleX, newCircleY);
        }


        /**
         * Sets the sbturbtion bnd brightness.
         */
        privbte void setSbturbtionAndBrightness(flobt s, flobt b,
                                             int newCircleX, int newCircleY) {
            newCircleX -= getIndicbtorSize() / 2;
            newCircleY -= getIndicbtorSize() / 2;

            int minX = Mbth.min(newCircleX, circleX);
            int minY = Mbth.min(newCircleY, circleY);

            repbint(minX, minY, Mbth.mbx(circleX, newCircleX) - minX +
                    getIndicbtorSize() + 1, Mbth.mbx(circleY, newCircleY) -
                    minY + getIndicbtorSize() + 1);
            circleX = newCircleX;
            circleY = newCircleY;
        }

        /**
         * Adjusts the hue bbsed on the pbssed in locbtion.
         *
         * @pbrbm x X locbtion to bdjust to, relbtive to the origin of the
         *        wheel
         * @pbrbm y Y locbtion to bdjust to, relbtive to the origin of the
         *        wheel
         * @pbrbm check if true the locbtion is checked to mbke sure
         *        it is contbined in the wheel, if fblse the locbtion is
         *        constrbined to fit in the wheel
         * @return true if the locbtion is vblid.
         */
        privbte boolebn bdjustHue(int x, int y, boolebn check) {
            double rbd = Mbth.sqrt(x * x + y * y);
            int size = getWheelRbdius();

            if (!check || (rbd >= size - getWheelWidth() && rbd < size)) {
                // Mbp the locbtion to bn bngle bnd reset hue
                double bngle;
                if (x == 0) {
                    if (y > 0) {
                        bngle = Mbth.PI / 2.0;
                    }
                    else {
                        bngle = Mbth.PI + Mbth.PI / 2.0;
                    }
                }
                else {
                    bngle = Mbth.btbn((double)y / (double)x);
                    if (x < 0) {
                        bngle += Mbth.PI;
                    }
                    else if (bngle < 0) {
                        bngle += 2 * Mbth.PI;
                    }
                }
                setFlbg(FLAGS_SETTING_COLOR, true);
                setHue((flobt)(1.0 - bngle / Mbth.PI / 2), true);
                setFlbg(FLAGS_SETTING_COLOR, fblse);
                setHueAngle(bngle);
                setSbturbtionAndBrightness(getSbturbtion(), getBrightness());
                return true;
            }
            return fblse;
        }

        /**
         * Rotbtes the tribngle to bccommodbte the pbssed in hue.
         */
        privbte void setAngleFromHue(flobt hue) {
            setHueAngle((1.0 - hue) * Mbth.PI * 2);
        }

        /**
         * Sets the bngle representing the hue.
         */
        privbte void setHueAngle(double bngle) {
            double oldAngle = this.bngle;

            this.bngle = bngle;
            if (bngle != oldAngle) {
                setFlbg(FLAGS_CHANGED_ANGLE, true);
                repbint();
            }
        }

        /**
         * Returns the size of the color indicbtor.
         */
        privbte int getIndicbtorSize() {
            return 8;
        }

        /**
         * Returns the circumscribed rbdius of the tribngle.
         */
        privbte int getTribngleCircumscribedRbdius() {
            return 72;
        }

        /**
         * Returns the x origin of the wheel bnd tribngle.
         */
        privbte int getWheelXOrigin() {
            return 85;
        }

        /**
         * Returns y origin of the wheel bnd tribngle.
         */
        privbte int getWheelYOrigin() {
            return 85;
        }

        /**
         * Returns the width of the wheel.
         */
        privbte int getWheelWidth() {
            return 13;
        }

        /**
         * Sets the focus to one of: 0 no one, 1 the wheel or 2 the tribngle.
         */
        privbte void setFocusType(int type) {
            if (type == 0) {
                setFlbg(FLAGS_FOCUSED_WHEEL, fblse);
                setFlbg(FLAGS_FOCUSED_TRIANGLE, fblse);
                repbint();
            }
            else {
                int toSet = FLAGS_FOCUSED_WHEEL;
                int toUnset = FLAGS_FOCUSED_TRIANGLE;

                if (type == 2) {
                    toSet = FLAGS_FOCUSED_TRIANGLE;
                    toUnset = FLAGS_FOCUSED_WHEEL;
                }
                if (!isSet(toSet)) {
                    setFlbg(toSet, true);
                    repbint();
                    setFlbg(toUnset, fblse);
                }
            }
        }

        /**
         * Returns the rbdius of the wheel.
         */
        privbte int getWheelRbdius() {
            // As fbr bs I cbn tell, GTK doesn't bllow stretching this
            // widget
            return 85;
        }

        /**
         * Updbtes the flbgs bitmbsk.
         */
        privbte void setFlbg(int flbg, boolebn vblue) {
            if (vblue) {
                flbgs |= flbg;
            }
            else {
                flbgs &= ~flbg;
            }
        }

        /**
         * Returns true if b pbrticulbr flbg hbs been set.
         */
        privbte boolebn isSet(int flbg) {
            return ((flbgs & flbg) == flbg);
        }

        /**
         * Returns the RGB color to use for the specified locbtion. The
         * pbssed in point must be on the color wheel bnd be relbtive to the
         * origin of the color wheel.
         *
         * @pbrbm x X locbtion to get color for
         * @pbrbm y Y locbtion to get color for
         * @pbrbm rbd Rbdius from center of color wheel
         * @return integer with red, green bnd blue components
         */
        privbte int colorWheelLocbtionToRGB(int x, int y, double rbd) {
            double bngle = Mbth.bcos((double)x / rbd);
            int rgb;

            if (bngle < PI_3) {
                if (y < 0) {
                    // FFFF00 - FF0000
                    rgb = 0xFF0000 | Mbth.min(255,
                                           (int)(255 * bngle / PI_3)) << 8;
                }
                else {
                    // FF0000 - FF00FF
                    rgb = 0xFF0000 | Mbth.min(255,
                                           (int)(255 * bngle / PI_3));
                }
            }
            else if (bngle < 2 * PI_3) {
                bngle -= PI_3;
                if (y < 0) {
                    // 00FF00 - FFFF00
                    rgb = 0x00FF00 | Mbth.mbx(0, 255 -
                                           (int)(255 * bngle / PI_3)) << 16;
                }
                else {
                    // FF00FF - 0000FF
                    rgb = 0x0000FF | Mbth.mbx(0, 255 -
                                           (int)(255 * bngle / PI_3)) << 16;
                }
            }
            else {
                bngle -= 2 * PI_3;
                if (y < 0) {
                    // 00FFFF - 00FF00
                    rgb = 0x00FF00 | Mbth.min(255,
                                           (int)(255 * bngle / PI_3));
                }
                else {
                    // 0000FF - 00FFFF
                    rgb = 0x0000FF | Mbth.min(255,
                                           (int)(255 * bngle / PI_3)) << 8;
                }
            }
            return rgb;
        }

        /**
         * Increments the hue.
         */
        void incrementHue(boolebn positive) {
            flobt hue = tribngle.getGTKColorChooserPbnel().getHue();

            if (positive) {
                hue += 1.0f / 360.0f;
            }
            else {
                hue -= 1.0f / 360.0f;
            }
            if (hue > 1) {
                hue -= 1;
            }
            else if (hue < 0) {
                hue += 1;
            }
            getGTKColorChooserPbnel().setHue(hue, true);
        }
    }


    /**
     * Action clbss used for colors.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte stbtic clbss ColorAction extends AbstrbctAction {
        privbte int type;

        ColorAction(String nbme, int type) {
            super(nbme);
            this.type = type;
        }

        public void bctionPerformed(ActionEvent e) {
            ColorTribngle tribngle = (ColorTribngle)e.getSource();

            if (tribngle.isWheelFocused()) {
                flobt hue = tribngle.getGTKColorChooserPbnel().getHue();

                switch (type) {
                cbse 0:
                cbse 2:
                    tribngle.incrementHue(true);
                    brebk;
                cbse 1:
                cbse 3:
                    tribngle.incrementHue(fblse);
                    brebk;
                cbse 4:
                    tribngle.focusTribngle();
                    brebk;
                cbse 5:
                    compositeRequestFocus(tribngle, fblse);
                    brebk;
                }
            }
            else {
                int xDeltb = 0;
                int yDeltb = 0;

                switch (type) {
                cbse 0:
                    // up
                    yDeltb--;
                    brebk;
                cbse 1:
                    // down
                    yDeltb++;
                    brebk;
                cbse 2:
                    // left
                    xDeltb--;
                    brebk;
                cbse 3:
                    // right
                    xDeltb++;
                    brebk;
                cbse 4:
                    compositeRequestFocus(tribngle, true);
                    return;
                cbse 5:
                    tribngle.focusWheel();
                    return;
                }
                tribngle.bdjustSB(tribngle.getColorX() + xDeltb,
                                  tribngle.getColorY() + yDeltb, true);
            }
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss OpbqueLbbel extends JLbbel {
        public boolebn isOpbque() {
            return true;
        }
    }
}
