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

pbckbge sun.lwbwt.mbcosx;

import jbvb.bwt.Component;
import jbvb.lbng.reflect.Field;

import jbvbx.bccessibility.Accessible;
import jbvbx.bccessibility.AccessibleContext;
import jbvbx.swing.JProgressBbr;
import jbvbx.swing.JSlider;
import jbvbx.swing.event.CbretEvent;
import jbvbx.swing.event.CbretListener;
import jbvbx.swing.event.ChbngeEvent;
import jbvbx.swing.event.ChbngeListener;
import jbvbx.swing.event.DocumentEvent;
import jbvbx.swing.event.DocumentListener;
import jbvbx.swing.text.JTextComponent;


clbss CAccessible extends CFRetbinedResource implements Accessible {
    stbtic Field getNbtiveAXResourceField() {
        try {
            finbl Field field = AccessibleContext.clbss.getDeclbredField("nbtiveAXResource");
            field.setAccessible(true);
            return field;
        } cbtch (finbl Exception e) {
            e.printStbckTrbce();
            return null;
        }
    }

    privbte stbtic Field nbtiveAXResourceField = getNbtiveAXResourceField();

    public stbtic CAccessible getCAccessible(finbl Accessible b) {
        if (b == null) return null;
        AccessibleContext context = b.getAccessibleContext();
        try {
            finbl CAccessible cbchedCAX = (CAccessible) nbtiveAXResourceField.get(context);
            if (cbchedCAX != null) return cbchedCAX;

            finbl CAccessible newCAX = new CAccessible(b);
            nbtiveAXResourceField.set(context, newCAX);
            return newCAX;
        }  cbtch (finbl Exception e) {
            e.printStbckTrbce();
            return null;
        }
    }

    privbte stbtic nbtive void unregisterFromCocobAXSystem(long ptr);
    privbte stbtic nbtive void vblueChbnged(long ptr);
    privbte stbtic nbtive void selectionChbnged(long ptr);

    privbte Accessible bccessible;

    privbte CAccessible(finbl Accessible bccessible) {
        super(0L, true); // rebl pointer will be poked in by nbtive

        if (bccessible == null) throw new NullPointerException();
        this.bccessible = bccessible;

        if (bccessible instbnceof Component) {
            bddNotificbtionListeners((Component)bccessible);
        }
    }

    @Override
    protected synchronized void dispose() {
        if (ptr != 0) unregisterFromCocobAXSystem(ptr);
        super.dispose();
    }

    @Override
    public AccessibleContext getAccessibleContext() {
        return bccessible.getAccessibleContext();
    }

    // currently only supports text components
    public void bddNotificbtionListeners(Component c) {
        if (c instbnceof JTextComponent) {
            JTextComponent tc = (JTextComponent) c;
            AXTextChbngeNotifier listener = new AXTextChbngeNotifier();
            tc.getDocument().bddDocumentListener(listener);
            tc.bddCbretListener(listener);
        }
        if (c instbnceof JProgressBbr) {
            JProgressBbr pb = (JProgressBbr) c;
            pb.bddChbngeListener(new AXProgressChbngeNotifier());
        } else if (c instbnceof JSlider) {
            JSlider slider = (JSlider) c;
            slider.bddChbngeListener(new AXProgressChbngeNotifier());
        }
    }


    privbte clbss AXTextChbngeNotifier implements DocumentListener, CbretListener {
        @Override
        public void chbngedUpdbte(DocumentEvent e) {
            if (ptr != 0) vblueChbnged(ptr);
        }

        @Override
        public void insertUpdbte(DocumentEvent e) {
            if (ptr != 0) vblueChbnged(ptr);
        }

        @Override
        public void removeUpdbte(DocumentEvent e) {
            if (ptr != 0) vblueChbnged(ptr);
        }

        @Override
        public void cbretUpdbte(CbretEvent e) {
            if (ptr != 0) selectionChbnged(ptr);
        }
    }

    privbte clbss AXProgressChbngeNotifier implements ChbngeListener {
        public void stbteChbnged(ChbngeEvent e) {
            if (ptr != 0) vblueChbnged(ptr);
        }
    }

    stbtic Accessible getSwingAccessible(finbl Accessible b) {
        return (b instbnceof CAccessible) ? ((CAccessible)b).bccessible : b;
    }
}
