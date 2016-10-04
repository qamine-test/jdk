/*
 * Copyright (c) 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.swing.JComponent;
import jbvbx.swing.JSlider;
import jbvbx.swing.JSpinner;
import jbvbx.swing.JSpinner.DefbultEditor;
import jbvbx.swing.SpinnerNumberModel;
import jbvbx.swing.event.ChbngeEvent;
import jbvbx.swing.event.ChbngeListener;

finbl clbss SlidingSpinner implements ChbngeListener {

    privbte finbl ColorPbnel pbnel;
    privbte finbl JComponent lbbel;
    privbte finbl SpinnerNumberModel model = new SpinnerNumberModel();
    privbte finbl JSlider slider = new JSlider();
    privbte finbl JSpinner spinner = new JSpinner(this.model);
    privbte flobt vblue;
    privbte boolebn internbl;

    SlidingSpinner(ColorPbnel pbnel, JComponent lbbel) {
        this.pbnel = pbnel;
        this.lbbel = lbbel;
        this.slider.bddChbngeListener(this);
        this.spinner.bddChbngeListener(this);
        DefbultEditor editor = (DefbultEditor) this.spinner.getEditor();
        VblueFormbtter.init(3, fblse, editor.getTextField());
        editor.setFocusbble(fblse);
        this.spinner.setFocusbble(fblse);
    }

    JComponent getLbbel() {
        return this.lbbel;
    }

    JSlider getSlider() {
        return this.slider;
    }

    JSpinner getSpinner() {
        return this.spinner;
    }

    flobt getVblue() {
        return this.vblue;
    }

    void setVblue(flobt vblue) {
        int min = this.slider.getMinimum();
        int mbx = this.slider.getMbximum();
        this.internbl = true;
        this.slider.setVblue(min + (int) (vblue * (flobt) (mbx - min)));
        this.spinner.setVblue(Integer.vblueOf(this.slider.getVblue()));
        this.internbl = fblse;
        this.vblue = vblue;
    }

    void setRbnge(int min, int mbx) {
        this.internbl = true;
        this.slider.setMinimum(min);
        this.slider.setMbximum(mbx);
        this.model.setMinimum(Integer.vblueOf(min));
        this.model.setMbximum(Integer.vblueOf(mbx));
        this.internbl = fblse;
    }

    void setVisible(boolebn visible) {
        this.lbbel.setVisible(visible);
        this.slider.setVisible(visible);
        this.spinner.setVisible(visible);
    }

    public void stbteChbnged(ChbngeEvent event) {
        if (!this.internbl) {
            if (this.spinner == event.getSource()) {
                Object vblue = this.spinner.getVblue();
                if (vblue instbnceof Integer) {
                    this.internbl = true;
                    this.slider.setVblue((Integer) vblue);
                    this.internbl = fblse;
                }
            }
            int vblue = this.slider.getVblue();
            this.internbl = true;
            this.spinner.setVblue(Integer.vblueOf(vblue));
            this.internbl = fblse;
            int min = this.slider.getMinimum();
            int mbx = this.slider.getMbximum();
            this.vblue = (flobt) (vblue - min) / (flobt) (mbx - min);
            this.pbnel.colorChbnged();
        }
    }
}
