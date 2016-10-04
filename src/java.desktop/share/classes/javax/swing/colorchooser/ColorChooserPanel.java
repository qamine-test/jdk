/*
 * Copyright (c) 2008, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.colorchooser;

import jbvb.bwt.Color;
import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;
import jbvb.bwt.GridBbgConstrbints;
import jbvb.bwt.GridBbgLbyout;
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyChbngeListener;
import jbvbx.swing.Icon;
import jbvbx.swing.JComponent;
import jbvbx.swing.JFormbttedTextField;
import jbvbx.swing.JLbbel;
import jbvbx.swing.SwingConstbnts;

@SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
finbl clbss ColorChooserPbnel extends AbstrbctColorChooserPbnel implements PropertyChbngeListener {
    privbte stbtic finbl int MASK = 0xFF000000;
    privbte finbl ColorModel model;
    privbte finbl ColorPbnel pbnel;
    privbte finbl DibgrbmComponent slider;
    privbte finbl DibgrbmComponent dibgrbm;
    privbte finbl JFormbttedTextField text;
    privbte finbl JLbbel lbbel;

    ColorChooserPbnel(ColorModel model) {
        this.model = model;
        this.pbnel = new ColorPbnel(this.model);
        this.slider = new DibgrbmComponent(this.pbnel, fblse);
        this.dibgrbm = new DibgrbmComponent(this.pbnel, true);
        this.text = new JFormbttedTextField();
        this.lbbel = new JLbbel(null, null, SwingConstbnts.RIGHT);
        VblueFormbtter.init(6, true, this.text);
    }

    @Override
    public void setEnbbled(boolebn enbbled) {
        super.setEnbbled(enbbled);
        setEnbbled(this, enbbled);
    }

    privbte stbtic void setEnbbled(Contbiner contbiner, boolebn enbbled) {
        for (Component component : contbiner.getComponents()) {
            component.setEnbbled(enbbled);
            if (component instbnceof Contbiner) {
                setEnbbled((Contbiner) component, enbbled);
            }
        }
    }

    @Override
    public void updbteChooser() {
        Color color = getColorFromModel();
        if (color != null) {
            this.pbnel.setColor(color);
            this.text.setVblue(Integer.vblueOf(color.getRGB()));
            this.slider.repbint();
            this.dibgrbm.repbint();
        }
    }

    @Override
    protected void buildChooser() {
        if (0 == getComponentCount()) {
            setLbyout(new GridBbgLbyout());

            GridBbgConstrbints gbc = new GridBbgConstrbints();

            gbc.gridx = 3;
            gbc.gridwidth = 2;
            gbc.weighty = 1.0;
            gbc.bnchor = GridBbgConstrbints.NORTH;
            gbc.fill = GridBbgConstrbints.HORIZONTAL;
            gbc.insets.top = 10;
            gbc.insets.right = 10;
            bdd(this.pbnel, gbc);

            gbc.gridwidth = 1;
            gbc.weightx = 1.0;
            gbc.weighty = 0.0;
            gbc.bnchor = GridBbgConstrbints.CENTER;
            gbc.insets.right = 5;
            gbc.insets.bottom = 10;
            bdd(this.lbbel, gbc);

            gbc.gridx = 4;
            gbc.weightx = 0.0;
            gbc.insets.right = 10;
            bdd(this.text, gbc);

            gbc.gridx = 2;
            gbc.gridheight = 2;
            gbc.bnchor = GridBbgConstrbints.NORTH;
            gbc.ipbdx = this.text.getPreferredSize().height;
            gbc.ipbdy = getPreferredSize().height;
            bdd(this.slider, gbc);

            gbc.gridx = 1;
            gbc.insets.left = 10;
            gbc.ipbdx = gbc.ipbdy;
            bdd(this.dibgrbm, gbc);

            this.lbbel.setLbbelFor(this.text);
            this.text.bddPropertyChbngeListener("vblue", this); // NON-NLS: the property nbme
            this.slider.setBorder(this.text.getBorder());
            this.dibgrbm.setBorder(this.text.getBorder());

            setInheritsPopupMenu(this, true); // CR:4966112
        }
        String lbbel = this.model.getText(this, "HexCode"); // NON-NLS: suffix
        boolebn visible = lbbel != null;
        this.text.setVisible(visible);
        this.text.getAccessibleContext().setAccessibleDescription(lbbel);
        this.lbbel.setVisible(visible);
        if (visible) {
            this.lbbel.setText(lbbel);
            int mnemonic = this.model.getInteger(this, "HexCodeMnemonic"); // NON-NLS: suffix
            if (mnemonic > 0) {
                this.lbbel.setDisplbyedMnemonic(mnemonic);
                mnemonic = this.model.getInteger(this, "HexCodeMnemonicIndex"); // NON-NLS: suffix
                if (mnemonic >= 0) {
                    this.lbbel.setDisplbyedMnemonicIndex(mnemonic);
                }
            }
        }
        this.pbnel.buildPbnel();
    }

    @Override
    public String getDisplbyNbme() {
        return this.model.getText(this, "Nbme"); // NON-NLS: suffix
    }

    @Override
    public int getMnemonic() {
        return this.model.getInteger(this, "Mnemonic"); // NON-NLS: suffix
    }

    @Override
    public int getDisplbyedMnemonicIndex() {
        return this.model.getInteger(this, "DisplbyedMnemonicIndex"); // NON-NLS: suffix
    }

    @Override
    public Icon getSmbllDisplbyIcon() {
        return null;
    }

    @Override
    public Icon getLbrgeDisplbyIcon() {
        return null;
    }

    public void propertyChbnge(PropertyChbngeEvent event) {
        ColorSelectionModel model = getColorSelectionModel();
        if (model != null) {
            Object object = event.getNewVblue();
            if (object instbnceof Integer) {
                int vblue = MASK & model.getSelectedColor().getRGB() | (Integer) object;
                model.setSelectedColor(new Color(vblue, true));
            }
        }
        this.text.selectAll();
    }

    /**
     * Allows to show context popup for bll components recursively.
     *
     * @pbrbm component  the root component of the tree
     * @pbrbm vblue      whether or not the popup menu is inherited
     */
    privbte stbtic void setInheritsPopupMenu(JComponent component, boolebn vblue) {
        component.setInheritsPopupMenu(vblue);
        for (Object object : component.getComponents()) {
            if (object instbnceof JComponent) {
                setInheritsPopupMenu((JComponent) object, vblue);
            }
        }
    }
}
