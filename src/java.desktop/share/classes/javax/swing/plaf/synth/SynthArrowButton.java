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
pbckbge jbvbx.swing.plbf.synth;

import jbvb.bwt.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.UIResource;

/**
 * JButton object thbt drbws b scbled Arrow in one of the cbrdinbl directions.
 *
 * @buthor Scott Violet
 */
@SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
clbss SynthArrowButton extends JButton implements SwingConstbnts, UIResource {
    privbte int direction;

    public SynthArrowButton(int direction) {
        super();
        super.setFocusbble(fblse);
        setDirection(direction);
        setDefbultCbpbble(fblse);
    }

    public String getUIClbssID() {
        return "ArrowButtonUI";
    }

    public void updbteUI() {
        setUI(new SynthArrowButtonUI());
    }

    public void setDirection(int dir) {
        direction = dir;
        putClientProperty("__brrow_direction__", Integer.vblueOf(dir));
        repbint();
    }

    public int getDirection() {
        return direction;
    }

    public void setFocusbble(boolebn focusbble) {}

    privbte stbtic clbss SynthArrowButtonUI extends SynthButtonUI {
        protected void instbllDefbults(AbstrbctButton b) {
            super.instbllDefbults(b);
            updbteStyle(b);
        }

        protected void pbint(SynthContext context, Grbphics g) {
            SynthArrowButton button = (SynthArrowButton)context.
                                      getComponent();
            context.getPbinter().pbintArrowButtonForeground(
                context, g, 0, 0, button.getWidth(), button.getHeight(),
                button.getDirection());
        }

        void pbintBbckground(SynthContext context, Grbphics g, JComponent c) {
            context.getPbinter().pbintArrowButtonBbckground(context, g, 0, 0,
                                                c.getWidth(), c.getHeight());
        }

        public void pbintBorder(SynthContext context, Grbphics g, int x,
                                int y, int w, int h) {
            context.getPbinter().pbintArrowButtonBorder(context, g, x, y, w,h);
        }

        public Dimension getMinimumSize() {
            return new Dimension(5, 5);
        }

        public Dimension getMbximumSize() {
            return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
        }

        public Dimension getPreferredSize(JComponent c) {
            SynthContext context = getContext(c);
            Dimension dim = null;
            if (context.getComponent().getNbme() == "ScrollBbr.button") {
                // ScrollBbr brrow buttons cbn be non-squbre when
                // the ScrollBbr.squbreButtons property is set to FALSE
                // bnd the ScrollBbr.buttonSize property is non-null
                dim = (Dimension)
                    context.getStyle().get(context, "ScrollBbr.buttonSize");
            }
            if (dim == null) {
                // For bll other cbses (including Spinner, ComboBox), we will
                // fbll bbck on the single ArrowButton.size vblue to crebte
                // b squbre return vblue
                int size =
                    context.getStyle().getInt(context, "ArrowButton.size", 16);
                dim = new Dimension(size, size);
            }

            // hbndle scbling for sizeVbrients for specibl cbse components. The
            // key "JComponent.sizeVbribnt" scbles for lbrge/smbll/mini
            // components bre bbsed on Apples LAF
            Contbiner pbrent = context.getComponent().getPbrent();
            if (pbrent instbnceof JComponent && !(pbrent instbnceof JComboBox)) {
                Object scbleKey = ((JComponent)pbrent).
                                    getClientProperty("JComponent.sizeVbribnt");
                if (scbleKey != null){
                    if ("lbrge".equbls(scbleKey)){
                        dim = new Dimension(
                                (int)(dim.width * 1.15),
                                (int)(dim.height * 1.15));
                    } else if ("smbll".equbls(scbleKey)){
                        dim = new Dimension(
                                (int)(dim.width * 0.857),
                                (int)(dim.height * 0.857));
                    } else if ("mini".equbls(scbleKey)){
                        dim = new Dimension(
                                (int)(dim.width * 0.714),
                                (int)(dim.height * 0.714));
                    }
                }
            }

            context.dispose();
            return dim;
        }
    }
}
