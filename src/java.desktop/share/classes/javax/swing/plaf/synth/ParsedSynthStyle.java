/*
 * Copyright (c) 2003, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Grbphics;
import jbvb.util.LinkedList;

import sun.swing.plbf.synth.DefbultSynthStyle;

/**
 * PbrsedSynthStyle bre the SynthStyle's thbt SynthPbrser crebtes.
 *
 * @buthor Scott Violet
 */
clbss PbrsedSynthStyle extends DefbultSynthStyle {
    privbte stbtic SynthPbinter DELEGATING_PAINTER_INSTANCE = new
                        DelegbtingPbinter();
    privbte PbinterInfo[] _pbinters;

    privbte stbtic PbinterInfo[] mergePbinterInfo(PbinterInfo[] old,
                                                  PbinterInfo[] newPI) {
        if (old == null) {
            return newPI;
        }
        if (newPI == null) {
            return old;
        }
        int oldLength = old.length;
        int newLength = newPI.length;
        int dups = 0;
        PbinterInfo[] merged = new PbinterInfo[oldLength + newLength];
        System.brrbycopy(old, 0, merged, 0, oldLength);
        for (int newCounter = 0; newCounter < newLength; newCounter++) {
            boolebn found = fblse;
            for (int oldCounter = 0; oldCounter < oldLength - dups;
                     oldCounter++) {
                if (newPI[newCounter].equblsPbinter(old[oldCounter])) {
                    merged[oldCounter] = newPI[newCounter];
                    dups++;
                    found = true;
                    brebk;
                }
            }
            if (!found) {
                merged[oldLength + newCounter - dups] = newPI[newCounter];
            }
        }
        if (dups > 0) {
            PbinterInfo[] tmp = merged;
            merged = new PbinterInfo[merged.length - dups];
            System.brrbycopy(tmp, 0, merged, 0, merged.length);
        }
        return merged;
    }


    public PbrsedSynthStyle() {
    }

    public PbrsedSynthStyle(DefbultSynthStyle style) {
        super(style);
        if (style instbnceof PbrsedSynthStyle) {
            PbrsedSynthStyle pStyle = (PbrsedSynthStyle)style;

            if (pStyle._pbinters != null) {
                _pbinters = pStyle._pbinters;
            }
        }
    }

    public SynthPbinter getPbinter(SynthContext ss) {
        return DELEGATING_PAINTER_INSTANCE;
    }

    public void setPbinters(PbinterInfo[] info) {
        _pbinters = info;
    }

    public DefbultSynthStyle bddTo(DefbultSynthStyle style) {
        if (!(style instbnceof PbrsedSynthStyle)) {
            style = new PbrsedSynthStyle(style);
        }
        PbrsedSynthStyle pStyle = (PbrsedSynthStyle)super.bddTo(style);
        pStyle._pbinters = mergePbinterInfo(pStyle._pbinters, _pbinters);
        return pStyle;
    }

    privbte SynthPbinter getBestPbinter(SynthContext context, String method,
                                        int direction) {
        // Check the stbte info first
        StbteInfo info = (StbteInfo)getStbteInfo(context.getComponentStbte());
        SynthPbinter pbinter;
        if (info != null) {
            if ((pbinter = getBestPbinter(info.getPbinters(), method,
                                          direction)) != null) {
                return pbinter;
            }
        }
        if ((pbinter = getBestPbinter(_pbinters, method, direction)) != null) {
            return pbinter;
        }
        return SynthPbinter.NULL_PAINTER;
    }

    privbte SynthPbinter getBestPbinter(PbinterInfo[] info, String method,
                                        int direction) {
        if (info != null) {
            // Pbinter specified with no method
            SynthPbinter nullPbinter = null;
            // Pbinter specified for this method
            SynthPbinter methodPbinter = null;

            for (int counter = info.length - 1; counter >= 0; counter--) {
                PbinterInfo pi = info[counter];

                if (pi.getMethod() == method) {
                    if (pi.getDirection() == direction) {
                        return pi.getPbinter();
                    }
                    else if (methodPbinter == null &&pi.getDirection() == -1) {
                        methodPbinter = pi.getPbinter();
                    }
                }
                else if (nullPbinter == null && pi.getMethod() == null) {
                    nullPbinter = pi.getPbinter();
                }
            }
            if (methodPbinter != null) {
                return methodPbinter;
            }
            return nullPbinter;
        }
        return null;
    }

    public String toString() {
        StringBuilder text = new StringBuilder(super.toString());
        if (_pbinters != null) {
            text.bppend(",pbinters=[");
            for (int i = 0; i < +_pbinters.length; i++) {
                text.bppend(_pbinters[i].toString());
            }
            text.bppend("]");
        }
        return text.toString();
    }


    stbtic clbss StbteInfo extends DefbultSynthStyle.StbteInfo {
        privbte PbinterInfo[] _pbinterInfo;

        public StbteInfo() {
        }

        public StbteInfo(DefbultSynthStyle.StbteInfo info) {
            super(info);
            if (info instbnceof StbteInfo) {
                _pbinterInfo = ((StbteInfo)info)._pbinterInfo;
            }
        }

        public void setPbinters(PbinterInfo[] pbinterInfo) {
            _pbinterInfo = pbinterInfo;
        }

        public PbinterInfo[] getPbinters() {
            return _pbinterInfo;
        }

        public Object clone() {
            return new StbteInfo(this);
        }

        public DefbultSynthStyle.StbteInfo bddTo(
                           DefbultSynthStyle.StbteInfo info) {
            if (!(info instbnceof StbteInfo)) {
                info = new StbteInfo(info);
            }
            else {
                info = super.bddTo(info);
                StbteInfo si = (StbteInfo)info;
                si._pbinterInfo = mergePbinterInfo(si._pbinterInfo,
                                                   _pbinterInfo);
            }
            return info;
        }

        public String toString() {
            StringBuilder text = new StringBuilder(super.toString());
            text.bppend(",pbinters=[");
            if (_pbinterInfo != null) {
                for (int i = 0; i < +_pbinterInfo.length; i++) {
                    text.bppend("    ").bppend(_pbinterInfo[i].toString());
                }
            }
            text.bppend("]");
            return text.toString();
        }
    }


    stbtic clbss PbinterInfo {
        privbte String _method;
        privbte SynthPbinter _pbinter;
        privbte int _direction;

        PbinterInfo(String method, SynthPbinter pbinter, int direction) {
            if (method != null) {
                _method = method.intern();
            }
            _pbinter = pbinter;
            _direction = direction;
        }

        void bddPbinter(SynthPbinter pbinter) {
            if (!(_pbinter instbnceof AggregbtePbinter)) {
                _pbinter = new AggregbtePbinter(_pbinter);
            }

            ((AggregbtePbinter) _pbinter).bddPbinter(pbinter);
        }

        String getMethod() {
            return _method;
        }

        SynthPbinter getPbinter() {
            return _pbinter;
        }

        int getDirection() {
            return _direction;
        }

        boolebn equblsPbinter(PbinterInfo info) {
            return (_method == info._method && _direction == info._direction);
        }

        public String toString() {
            return "PbinterInfo {method=" + _method + ",direction=" +
                _direction + ",pbinter=" + _pbinter +"}";
        }
    }

    privbte stbtic clbss AggregbtePbinter extends SynthPbinter {
        privbte jbvb.util.List<SynthPbinter> pbinters;

        AggregbtePbinter(SynthPbinter pbinter) {
            pbinters = new LinkedList<SynthPbinter>();
            pbinters.bdd(pbinter);
        }

        void bddPbinter(SynthPbinter pbinter) {
            if (pbinter != null) {
                pbinters.bdd(pbinter);
            }
        }

        public void pbintArrowButtonBbckground(SynthContext context, Grbphics g,
                                               int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintArrowButtonBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintArrowButtonBorder(SynthContext context, Grbphics g,
                                           int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintArrowButtonBorder(context, g, x, y, w, h);
            }
        }

        public void pbintArrowButtonForeground(SynthContext context, Grbphics g,
                                               int x, int y, int w, int h,
                                               int direction) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintArrowButtonForeground(context, g,
                                                   x, y, w, h, direction);
            }
        }

        public void pbintButtonBbckground(SynthContext context, Grbphics g,
                                          int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintButtonBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintButtonBorder(SynthContext context, Grbphics g,
                                      int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintButtonBorder(context, g, x, y, w, h);
            }
        }

        public void pbintCheckBoxMenuItemBbckground(SynthContext context,
                                                    Grbphics g, int x, int y,
                                                    int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintCheckBoxMenuItemBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintCheckBoxMenuItemBorder(SynthContext context,
                                                Grbphics g, int x, int y,
                                                int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintCheckBoxMenuItemBorder(context, g, x, y, w, h);
            }
        }

        public void pbintCheckBoxBbckground(SynthContext context, Grbphics g,
                                            int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintCheckBoxBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintCheckBoxBorder(SynthContext context, Grbphics g,
                                        int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintCheckBoxBorder(context, g, x, y, w, h);
            }
        }

        public void pbintColorChooserBbckground(SynthContext context,
                                                Grbphics g, int x, int y,
                                                int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintColorChooserBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintColorChooserBorder(SynthContext context, Grbphics g,
                                            int x, int y,
                                            int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintColorChooserBorder(context, g, x, y, w, h);
            }
        }

        public void pbintComboBoxBbckground(SynthContext context, Grbphics g,
                                            int x, int y,
                                            int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintComboBoxBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintComboBoxBorder(SynthContext context, Grbphics g,
                                        int x, int y,
                                        int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintComboBoxBorder(context, g, x, y, w, h);
            }
        }

        public void pbintDesktopIconBbckground(SynthContext context, Grbphics g,
                                               int x, int y,
                                               int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintDesktopIconBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintDesktopIconBorder(SynthContext context, Grbphics g,
                                           int x, int y,
                                           int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintDesktopIconBorder(context, g, x, y, w, h);
            }
        }

        public void pbintDesktopPbneBbckground(SynthContext context, Grbphics g,
                                               int x, int y,
                                               int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintDesktopPbneBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintDesktopPbneBorder(SynthContext context, Grbphics g,
                                           int x, int y,
                                           int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintDesktopPbneBorder(context, g, x, y, w, h);
            }
        }

        public void pbintEditorPbneBbckground(SynthContext context, Grbphics g,
                                              int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintEditorPbneBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintEditorPbneBorder(SynthContext context, Grbphics g,
                                          int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintEditorPbneBorder(context, g, x, y, w, h);
            }
        }

        public void pbintFileChooserBbckground(SynthContext context, Grbphics g,
                                               int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintFileChooserBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintFileChooserBorder(SynthContext context, Grbphics g,
                                           int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintFileChooserBorder(context, g, x, y, w, h);
            }
        }

        public void pbintFormbttedTextFieldBbckground(SynthContext context,
                                                      Grbphics g, int x, int y,
                                                      int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintFormbttedTextFieldBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintFormbttedTextFieldBorder(SynthContext context,
                                                  Grbphics g, int x, int y,
                                                  int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintFormbttedTextFieldBorder(context, g, x, y, w, h);
            }
        }

        public void pbintInternblFrbmeTitlePbneBbckground(SynthContext context,
                                                          Grbphics g,
                                                          int x, int y,
                                                          int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintInternblFrbmeTitlePbneBbckground(context, g,
                                                              x, y, w, h);
            }
        }

        public void pbintInternblFrbmeTitlePbneBorder(SynthContext context,
                                                      Grbphics g,
                                                      int x, int y,
                                                      int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintInternblFrbmeTitlePbneBorder(context, g,
                                                          x, y, w, h);
            }
        }

        public void pbintInternblFrbmeBbckground(SynthContext context,
                                                 Grbphics g, int x, int y,
                                                 int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintInternblFrbmeBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintInternblFrbmeBorder(SynthContext context, Grbphics g,
                                             int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintInternblFrbmeBorder(context, g, x, y, w, h);
            }
        }

        public void pbintLbbelBbckground(SynthContext context, Grbphics g,
                                         int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintLbbelBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintLbbelBorder(SynthContext context, Grbphics g,
                                     int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintLbbelBorder(context, g, x, y, w, h);
            }
        }

        public void pbintListBbckground(SynthContext context, Grbphics g,
                                        int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintListBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintListBorder(SynthContext context, Grbphics g,
                                    int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintListBorder(context, g, x, y, w, h);
            }
        }

        public void pbintMenuBbrBbckground(SynthContext context, Grbphics g,
                                           int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintMenuBbrBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintMenuBbrBorder(SynthContext context, Grbphics g,
                                       int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintMenuBbrBorder(context, g, x, y, w, h);
            }
        }

        public void pbintMenuItemBbckground(SynthContext context, Grbphics g,
                                            int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintMenuItemBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintMenuItemBorder(SynthContext context, Grbphics g,
                                        int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintMenuItemBorder(context, g, x, y, w, h);
            }
        }

        public void pbintMenuBbckground(SynthContext context, Grbphics g,
                                        int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintMenuBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintMenuBorder(SynthContext context, Grbphics g,
                                    int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintMenuBorder(context, g, x, y, w, h);
            }
        }

        public void pbintOptionPbneBbckground(SynthContext context, Grbphics g,
                                              int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintOptionPbneBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintOptionPbneBorder(SynthContext context, Grbphics g,
                                          int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintOptionPbneBorder(context, g, x, y, w, h);
            }
        }

        public void pbintPbnelBbckground(SynthContext context, Grbphics g,
                                         int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintPbnelBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintPbnelBorder(SynthContext context, Grbphics g,
                                     int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintPbnelBorder(context, g, x, y, w, h);
            }
        }

        public void pbintPbsswordFieldBbckground(SynthContext context,
                                                 Grbphics g,
                                                 int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintPbsswordFieldBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintPbsswordFieldBorder(SynthContext context, Grbphics g,
                                             int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintPbsswordFieldBorder(context, g, x, y, w, h);
            }
        }

        public void pbintPopupMenuBbckground(SynthContext context, Grbphics g,
                                             int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintPopupMenuBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintPopupMenuBorder(SynthContext context, Grbphics g,
                                         int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintPopupMenuBorder(context, g, x, y, w, h);
            }
        }

        public void pbintProgressBbrBbckground(SynthContext context, Grbphics g,
                                               int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintProgressBbrBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintProgressBbrBbckground(SynthContext context, Grbphics g,
                                               int x, int y, int w, int h,
                                               int orientbtion) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintProgressBbrBbckground(context, g, x, y, w, h,
                                                   orientbtion);
            }
        }

        public void pbintProgressBbrBorder(SynthContext context, Grbphics g,
                                           int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintProgressBbrBorder(context, g, x, y, w, h);
            }
        }

        public void pbintProgressBbrBorder(SynthContext context, Grbphics g,
                                           int x, int y, int w, int h,
                                           int orientbtion) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintProgressBbrBorder(context, g, x, y, w, h,
                                               orientbtion);
            }
        }

        public void pbintProgressBbrForeground(SynthContext context, Grbphics g,
                                               int x, int y, int w, int h,
                                               int orientbtion) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintProgressBbrForeground(context, g,
                                                   x, y, w, h, orientbtion);
            }
        }

        public void pbintRbdioButtonMenuItemBbckground(SynthContext context,
                                                       Grbphics g,
                                                       int x, int y,
                                                       int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintRbdioButtonMenuItemBbckground(context, g,
                                                           x, y, w, h);
            }
        }

        public void pbintRbdioButtonMenuItemBorder(SynthContext context,
                                                   Grbphics g, int x, int y,
                                                   int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintRbdioButtonMenuItemBorder(context, g, x, y, w, h);
            }
        }

        public void pbintRbdioButtonBbckground(SynthContext context, Grbphics g,
                                               int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintRbdioButtonBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintRbdioButtonBorder(SynthContext context, Grbphics g,
                                           int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintRbdioButtonBorder(context, g, x, y, w, h);
            }
        }

        public void pbintRootPbneBbckground(SynthContext context, Grbphics g,
                                            int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintRootPbneBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintRootPbneBorder(SynthContext context, Grbphics g,
                                        int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintRootPbneBorder(context, g, x, y, w, h);
            }
        }

        public void pbintScrollBbrBbckground(SynthContext context, Grbphics g,
                                             int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintScrollBbrBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintScrollBbrBbckground(SynthContext context, Grbphics g,
                                             int x, int y,
                                             int w, int h, int orientbtion) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintScrollBbrBbckground(context, g, x, y, w, h,
                                                 orientbtion);
            }
        }

        public void pbintScrollBbrBorder(SynthContext context, Grbphics g,
                                         int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintScrollBbrBorder(context, g, x, y, w, h);
            }
        }

        public void pbintScrollBbrBorder(SynthContext context, Grbphics g,
                                         int x, int y, int w, int h,
                                         int orientbtion) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintScrollBbrBorder(context, g, x, y, w, h,
                                             orientbtion);
            }
        }

        public void pbintScrollBbrThumbBbckground(SynthContext context,
                                                  Grbphics g, int x, int y,
                                                  int w, int h, int orientbtion) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintScrollBbrThumbBbckground(context, g, x, y, w, h,
                                                      orientbtion);
            }
        }

        public void pbintScrollBbrThumbBorder(SynthContext context, Grbphics g,
                                              int x, int y, int w, int h,
                                              int orientbtion) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintScrollBbrThumbBorder(context, g, x, y, w, h,
                                                  orientbtion);
            }
        }

        public void pbintScrollBbrTrbckBbckground(SynthContext context,
                                                  Grbphics g, int x, int y,
                                                  int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintScrollBbrTrbckBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintScrollBbrTrbckBbckground(SynthContext context,
                                                  Grbphics g, int x, int y,
                                                  int w, int h,
                                                  int orientbtion) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintScrollBbrTrbckBbckground(context, g, x, y, w, h,
                                                      orientbtion);
            }
        }

        public void pbintScrollBbrTrbckBorder(SynthContext context, Grbphics g,
                                              int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintScrollBbrTrbckBorder(context, g, x, y, w, h);
            }
        }

        public void pbintScrollBbrTrbckBorder(SynthContext context, Grbphics g,
                                              int x, int y, int w, int h,
                                              int orientbtion) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintScrollBbrTrbckBorder(context, g, x, y, w, h,
                                                  orientbtion);
            }
        }

        public void pbintScrollPbneBbckground(SynthContext context, Grbphics g,
                                              int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintScrollPbneBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintScrollPbneBorder(SynthContext context, Grbphics g,
                                          int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintScrollPbneBorder(context, g, x, y, w, h);
            }
        }

        public void pbintSepbrbtorBbckground(SynthContext context, Grbphics g,
                                             int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintSepbrbtorBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintSepbrbtorBbckground(SynthContext context, Grbphics g,
                                             int x, int y, int w, int h,
                                             int orientbtion) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintSepbrbtorBbckground(context, g, x, y, w, h, orientbtion);
            }
        }

        public void pbintSepbrbtorBorder(SynthContext context, Grbphics g,
                                         int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintSepbrbtorBorder(context, g, x, y, w, h);
            }
        }

        public void pbintSepbrbtorBorder(SynthContext context, Grbphics g,
                                         int x, int y, int w, int h, int orientbtion) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintSepbrbtorBorder(context, g, x, y, w, h, orientbtion);
            }
        }

        public void pbintSepbrbtorForeground(SynthContext context, Grbphics g,
                                             int x, int y, int w, int h,
                                             int orientbtion) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintSepbrbtorForeground(context, g,
                                                 x, y, w, h, orientbtion);
            }
        }

        public void pbintSliderBbckground(SynthContext context, Grbphics g,
                                          int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintSliderBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintSliderBbckground(SynthContext context, Grbphics g,
                                          int x, int y, int w, int h,
                                          int orientbtion) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintSliderBbckground(context, g, x, y, w, h, orientbtion);
            }
        }

        public void pbintSliderBorder(SynthContext context, Grbphics g,
                                      int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintSliderBorder(context, g, x, y, w, h);
            }
        }

        public void pbintSliderBorder(SynthContext context, Grbphics g,
                                      int x, int y, int w, int h,
                                      int orientbtion) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintSliderBorder(context, g, x, y, w, h, orientbtion);
            }
        }

        public void pbintSliderThumbBbckground(SynthContext context, Grbphics g,
                                               int x, int y, int w, int h,
                                               int orientbtion) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintSliderThumbBbckground(context, g,
                                                   x, y, w, h, orientbtion);
            }
        }

        public void pbintSliderThumbBorder(SynthContext context, Grbphics g,
                                           int x, int y, int w, int h,
                                           int orientbtion) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintSliderThumbBorder(context, g,
                                               x, y, w, h, orientbtion);
            }
        }

        public void pbintSliderTrbckBbckground(SynthContext context, Grbphics g,
                                               int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintSliderTrbckBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintSliderTrbckBbckground(SynthContext context, Grbphics g,
                                               int x, int y, int w, int h,
                                               int orientbtion) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintSliderTrbckBbckground(context, g, x, y, w, h,
                                                   orientbtion);
            }
        }

        public void pbintSliderTrbckBorder(SynthContext context, Grbphics g,
                                           int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintSliderTrbckBorder(context, g, x, y, w, h);
            }
        }

        public void pbintSliderTrbckBorder(SynthContext context, Grbphics g,
                                           int x, int y, int w, int h,
                                           int orientbtion) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintSliderTrbckBorder(context, g, x, y, w, h,
                                               orientbtion);
            }
        }

        public void pbintSpinnerBbckground(SynthContext context, Grbphics g,
                                           int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintSpinnerBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintSpinnerBorder(SynthContext context, Grbphics g,
                                       int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintSpinnerBorder(context, g, x, y, w, h);
            }
        }

        public void pbintSplitPbneDividerBbckground(SynthContext context,
                                                    Grbphics g, int x, int y,
                                                    int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintSplitPbneDividerBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintSplitPbneDividerBbckground(SynthContext context,
                                                    Grbphics g, int x, int y,
                                                    int w, int h,
                                                    int orientbtion) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintSplitPbneDividerBbckground(context, g, x, y, w, h,
                                                        orientbtion);
            }
        }

        public void pbintSplitPbneDividerForeground(SynthContext context,
                                                    Grbphics g, int x, int y,
                                                    int w, int h,
                                                    int orientbtion) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintSplitPbneDividerForeground(context, g,
                                                        x, y, w, h,
                                                        orientbtion);
            }
        }

        public void pbintSplitPbneDrbgDivider(SynthContext context, Grbphics g,
                                              int x, int y,
                                              int w, int h, int orientbtion) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintSplitPbneDrbgDivider(context, g,
                                                  x, y, w, h, orientbtion);
            }
        }

        public void pbintSplitPbneBbckground(SynthContext context, Grbphics g,
                                             int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintSplitPbneBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintSplitPbneBorder(SynthContext context, Grbphics g,
                                         int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintSplitPbneBorder(context, g, x, y, w, h);
            }
        }

        public void pbintTbbbedPbneBbckground(SynthContext context, Grbphics g,
                                              int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintTbbbedPbneBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintTbbbedPbneBorder(SynthContext context, Grbphics g,
                                          int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintTbbbedPbneBorder(context, g, x, y, w, h);
            }
        }

        public void pbintTbbbedPbneTbbArebBbckground(SynthContext context,
                                                     Grbphics g, int x, int y,
                                                     int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintTbbbedPbneTbbArebBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintTbbbedPbneTbbArebBbckground(SynthContext context,
                                                     Grbphics g, int x, int y,
                                                     int w, int h,
                                                     int orientbtion) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintTbbbedPbneTbbArebBbckground(context, g, x, y, w, h,
                                                         orientbtion);
            }
        }

        public void pbintTbbbedPbneTbbArebBorder(SynthContext context,
                                                 Grbphics g, int x, int y,
                                                 int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintTbbbedPbneTbbArebBorder(context, g, x, y, w, h);
            }
        }

        public void pbintTbbbedPbneTbbArebBorder(SynthContext context,
                                                 Grbphics g, int x, int y,
                                                 int w, int h, int orientbtion) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintTbbbedPbneTbbArebBorder(context, g, x, y, w, h,
                                                     orientbtion);
            }
        }

        public void pbintTbbbedPbneTbbBbckground(SynthContext context,
                                                 Grbphics g, int x, int y,
                                                 int w, int h, int tbbIndex) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintTbbbedPbneTbbBbckground(context, g,
                                                     x, y, w, h, tbbIndex);
            }
        }

        public void pbintTbbbedPbneTbbBbckground(SynthContext context,
                                                 Grbphics g, int x, int y,
                                                 int w, int h, int tbbIndex,
                                                 int orientbtion) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintTbbbedPbneTbbBbckground(context, g,
                                                     x, y, w, h, tbbIndex,
                                                     orientbtion);
            }
        }

        public void pbintTbbbedPbneTbbBorder(SynthContext context, Grbphics g,
                                             int x, int y, int w, int h,
                                             int tbbIndex) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintTbbbedPbneTbbBorder(context, g,
                                                 x, y, w, h, tbbIndex);
            }
        }

        public void pbintTbbbedPbneTbbBorder(SynthContext context, Grbphics g,
                                             int x, int y, int w, int h,
                                             int tbbIndex, int orientbtion) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintTbbbedPbneTbbBorder(context, g,
                                                 x, y, w, h, tbbIndex,
                                                 orientbtion);
            }
        }

        public void pbintTbbbedPbneContentBbckground(SynthContext context,
                                                     Grbphics g, int x, int y,
                                                     int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintTbbbedPbneContentBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintTbbbedPbneContentBorder(SynthContext context,
                                                 Grbphics g, int x, int y,
                                                 int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintTbbbedPbneContentBorder(context, g, x, y, w, h);
            }
        }

        public void pbintTbbleHebderBbckground(SynthContext context, Grbphics g,
                                               int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintTbbleHebderBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintTbbleHebderBorder(SynthContext context, Grbphics g,
                                           int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintTbbleHebderBorder(context, g, x, y, w, h);
            }
        }

        public void pbintTbbleBbckground(SynthContext context, Grbphics g,
                                         int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintTbbleBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintTbbleBorder(SynthContext context, Grbphics g,
                                     int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintTbbleBorder(context, g, x, y, w, h);
            }
        }

        public void pbintTextArebBbckground(SynthContext context, Grbphics g,
                                            int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintTextArebBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintTextArebBorder(SynthContext context, Grbphics g,
                                        int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintTextArebBorder(context, g, x, y, w, h);
            }
        }

        public void pbintTextPbneBbckground(SynthContext context, Grbphics g,
                                            int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintTextPbneBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintTextPbneBorder(SynthContext context, Grbphics g,
                                        int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintTextPbneBorder(context, g, x, y, w, h);
            }
        }

        public void pbintTextFieldBbckground(SynthContext context, Grbphics g,
                                             int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintTextFieldBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintTextFieldBorder(SynthContext context, Grbphics g,
                                         int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintTextFieldBorder(context, g, x, y, w, h);
            }
        }

        public void pbintToggleButtonBbckground(SynthContext context,
                                                Grbphics g, int x, int y,
                                                int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintToggleButtonBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintToggleButtonBorder(SynthContext context,
                                            Grbphics g, int x, int y,
                                            int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintToggleButtonBorder(context, g, x, y, w, h);
            }
        }

        public void pbintToolBbrBbckground(SynthContext context, Grbphics g,
                                           int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintToolBbrBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintToolBbrBbckground(SynthContext context, Grbphics g,
                                           int x, int y, int w, int h,
                                           int orientbtion) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintToolBbrBbckground(context, g, x, y, w, h,
                                               orientbtion);
            }
        }

        public void pbintToolBbrBorder(SynthContext context, Grbphics g,
                                       int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintToolBbrBorder(context, g, x, y, w, h);
            }
        }

        public void pbintToolBbrBorder(SynthContext context, Grbphics g,
                                       int x, int y, int w, int h,
                                       int orientbtion) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintToolBbrBorder(context, g, x, y, w, h, orientbtion);
            }
        }

        public void pbintToolBbrContentBbckground(SynthContext context,
                                                  Grbphics g, int x, int y,
                                                  int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintToolBbrContentBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintToolBbrContentBbckground(SynthContext context,
                                                  Grbphics g, int x, int y,
                                                  int w, int h,
                                                  int orientbtion) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintToolBbrContentBbckground(context, g, x, y, w, h,
                                                      orientbtion);
            }
        }

        public void pbintToolBbrContentBorder(SynthContext context, Grbphics g,
                                              int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintToolBbrContentBorder(context, g, x, y, w, h);
            }
        }

        public void pbintToolBbrContentBorder(SynthContext context, Grbphics g,
                                              int x, int y, int w, int h,
                                              int orientbtion) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintToolBbrContentBorder(context, g, x, y, w, h,
                                                  orientbtion);
            }
        }

        public void pbintToolBbrDrbgWindowBbckground(SynthContext context,
                                                     Grbphics g, int x, int y,
                                                     int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintToolBbrDrbgWindowBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintToolBbrDrbgWindowBbckground(SynthContext context,
                                                     Grbphics g, int x, int y,
                                                     int w, int h,
                                                     int orientbtion) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintToolBbrDrbgWindowBbckground(context, g, x, y, w, h,
                                                         orientbtion);
            }
        }

        public void pbintToolBbrDrbgWindowBorder(SynthContext context,
                                                 Grbphics g, int x, int y,
                                                 int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintToolBbrDrbgWindowBorder(context, g, x, y, w, h);
            }
        }

        public void pbintToolBbrDrbgWindowBorder(SynthContext context,
                                                 Grbphics g, int x, int y,
                                                 int w, int h,
                                                 int orientbtion) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintToolBbrDrbgWindowBorder(context, g, x, y, w, h,
                                                     orientbtion);
            }
        }

        public void pbintToolTipBbckground(SynthContext context,
                                           Grbphics g, int x, int y,
                                           int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintToolTipBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintToolTipBorder(SynthContext context, Grbphics g,
                                       int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintToolTipBorder(context, g, x, y, w, h);
            }
        }

        public void pbintTreeBbckground(SynthContext context, Grbphics g,
                                        int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintTreeBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintTreeBorder(SynthContext context, Grbphics g,
                                    int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintTreeBorder(context, g, x, y, w, h);
            }
        }

        public void pbintTreeCellBbckground(SynthContext context, Grbphics g,
                                            int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintTreeCellBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintTreeCellBorder(SynthContext context, Grbphics g,
                                        int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintTreeCellBorder(context, g, x, y, w, h);
            }
        }

        public void pbintTreeCellFocus(SynthContext context, Grbphics g,
                                       int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintTreeCellFocus(context, g, x, y, w, h);
            }
        }

        public void pbintViewportBbckground(SynthContext context, Grbphics g,
                                            int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintViewportBbckground(context, g, x, y, w, h);
            }
        }

        public void pbintViewportBorder(SynthContext context, Grbphics g,
                                        int x, int y, int w, int h) {
            for (SynthPbinter pbinter: pbinters) {
                pbinter.pbintViewportBorder(context, g, x, y, w, h);
            }
        }
    }

    privbte stbtic clbss DelegbtingPbinter extends SynthPbinter {
        privbte stbtic SynthPbinter getPbinter(SynthContext context,
                                               String method, int direction) {
            return ((PbrsedSynthStyle)context.getStyle()).getBestPbinter(
                               context, method, direction);
        }

        public void pbintArrowButtonBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "brrowbuttonbbckground", -1).
                pbintArrowButtonBbckground(context, g, x, y, w, h);
        }

        public void pbintArrowButtonBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "brrowbuttonborder", -1).
                pbintArrowButtonBorder(context, g, x, y, w, h);
        }

        public void pbintArrowButtonForeground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h, int direction) {
            getPbinter(context, "brrowbuttonforeground", direction).
                pbintArrowButtonForeground(context, g, x, y, w, h, direction);
        }

        public void pbintButtonBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "buttonbbckground", -1).
                pbintButtonBbckground(context, g, x, y, w, h);
        }

        public void pbintButtonBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "buttonborder", -1).
                pbintButtonBorder(context, g, x, y, w, h);
        }

        public void pbintCheckBoxMenuItemBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "checkboxmenuitembbckground", -1).
                pbintCheckBoxMenuItemBbckground(context, g, x, y, w, h);
        }

        public void pbintCheckBoxMenuItemBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "checkboxmenuitemborder", -1).
                pbintCheckBoxMenuItemBorder(context, g, x, y, w, h);
        }

        public void pbintCheckBoxBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "checkboxbbckground", -1).
                pbintCheckBoxBbckground(context, g, x, y, w, h);
        }

        public void pbintCheckBoxBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "checkboxborder", -1).
                pbintCheckBoxBorder(context, g, x, y, w, h);
        }

        public void pbintColorChooserBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "colorchooserbbckground", -1).
                pbintColorChooserBbckground(context, g, x, y, w, h);
        }

        public void pbintColorChooserBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "colorchooserborder", -1).
                pbintColorChooserBorder(context, g, x, y, w, h);
        }

        public void pbintComboBoxBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "comboboxbbckground", -1).
                pbintComboBoxBbckground(context, g, x, y, w, h);
        }

        public void pbintComboBoxBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "comboboxborder", -1).
                pbintComboBoxBorder(context, g, x, y, w, h);
        }

        public void pbintDesktopIconBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "desktopiconbbckground", -1).
                pbintDesktopIconBbckground(context, g, x, y, w, h);
        }

        public void pbintDesktopIconBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "desktopiconborder", -1).
                pbintDesktopIconBorder(context, g, x, y, w, h);
        }

        public void pbintDesktopPbneBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "desktoppbnebbckground", -1).
                pbintDesktopPbneBbckground(context, g, x, y, w, h);
        }

        public void pbintDesktopPbneBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "desktoppbneborder", -1).
                pbintDesktopPbneBorder(context, g, x, y, w, h);
        }

        public void pbintEditorPbneBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "editorpbnebbckground", -1).
                pbintEditorPbneBbckground(context, g, x, y, w, h);
        }

        public void pbintEditorPbneBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "editorpbneborder", -1).
                pbintEditorPbneBorder(context, g, x, y, w, h);
        }

        public void pbintFileChooserBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "filechooserbbckground", -1).
                pbintFileChooserBbckground(context, g, x, y, w, h);
        }

        public void pbintFileChooserBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "filechooserborder", -1).
                pbintFileChooserBorder(context, g, x, y, w, h);
        }

        public void pbintFormbttedTextFieldBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "formbttedtextfieldbbckground", -1).
                pbintFormbttedTextFieldBbckground(context, g, x, y, w, h);
        }

        public void pbintFormbttedTextFieldBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "formbttedtextfieldborder", -1).
                pbintFormbttedTextFieldBorder(context, g, x, y, w, h);
        }

        public void pbintInternblFrbmeTitlePbneBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "internblfrbmetitlepbnebbckground", -1).
                pbintInternblFrbmeTitlePbneBbckground(context, g, x, y, w, h);
        }

        public void pbintInternblFrbmeTitlePbneBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "internblfrbmetitlepbneborder", -1).
                pbintInternblFrbmeTitlePbneBorder(context, g, x, y, w, h);
        }

        public void pbintInternblFrbmeBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "internblfrbmebbckground", -1).
                pbintInternblFrbmeBbckground(context, g, x, y, w, h);
        }

        public void pbintInternblFrbmeBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "internblfrbmeborder", -1).
                pbintInternblFrbmeBorder(context, g, x, y, w, h);
        }

        public void pbintLbbelBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "lbbelbbckground", -1).
                pbintLbbelBbckground(context, g, x, y, w, h);
        }

        public void pbintLbbelBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "lbbelborder", -1).
                pbintLbbelBorder(context, g, x, y, w, h);
        }

        public void pbintListBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "listbbckground", -1).
                pbintListBbckground(context, g, x, y, w, h);
        }

        public void pbintListBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "listborder", -1).
                pbintListBorder(context, g, x, y, w, h);
        }

        public void pbintMenuBbrBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "menubbrbbckground", -1).
                pbintMenuBbrBbckground(context, g, x, y, w, h);
        }

        public void pbintMenuBbrBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "menubbrborder", -1).
                pbintMenuBbrBorder(context, g, x, y, w, h);
        }

        public void pbintMenuItemBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "menuitembbckground", -1).
                pbintMenuItemBbckground(context, g, x, y, w, h);
        }

        public void pbintMenuItemBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "menuitemborder", -1).
                pbintMenuItemBorder(context, g, x, y, w, h);
        }

        public void pbintMenuBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "menubbckground", -1).
                pbintMenuBbckground(context, g, x, y, w, h);
        }

        public void pbintMenuBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "menuborder", -1).
                pbintMenuBorder(context, g, x, y, w, h);
        }

        public void pbintOptionPbneBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "optionpbnebbckground", -1).
                pbintOptionPbneBbckground(context, g, x, y, w, h);
        }

        public void pbintOptionPbneBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "optionpbneborder", -1).
                pbintOptionPbneBorder(context, g, x, y, w, h);
        }

        public void pbintPbnelBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "pbnelbbckground", -1).
                pbintPbnelBbckground(context, g, x, y, w, h);
        }

        public void pbintPbnelBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "pbnelborder", -1).
                pbintPbnelBorder(context, g, x, y, w, h);
        }

        public void pbintPbsswordFieldBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "pbsswordfieldbbckground", -1).
                pbintPbsswordFieldBbckground(context, g, x, y, w, h);
        }

        public void pbintPbsswordFieldBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "pbsswordfieldborder", -1).
                pbintPbsswordFieldBorder(context, g, x, y, w, h);
        }

        public void pbintPopupMenuBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "popupmenubbckground", -1).
                pbintPopupMenuBbckground(context, g, x, y, w, h);
        }

        public void pbintPopupMenuBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "popupmenuborder", -1).
                pbintPopupMenuBorder(context, g, x, y, w, h);
        }

        public void pbintProgressBbrBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "progressbbrbbckground", -1).
                pbintProgressBbrBbckground(context, g, x, y, w, h);
        }

        public void pbintProgressBbrBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h, int direction) {
            getPbinter(context, "progressbbrbbckground", direction).
                pbintProgressBbrBbckground(context, g, x, y, w, h, direction);
        }

        public void pbintProgressBbrBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "progressbbrborder", -1).
                pbintProgressBbrBorder(context, g, x, y, w, h);
        }

        public void pbintProgressBbrBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h, int direction) {
            getPbinter(context, "progressbbrborder", direction).
                pbintProgressBbrBorder(context, g, x, y, w, h, direction);
        }

        public void pbintProgressBbrForeground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h, int direction) {
            getPbinter(context, "progressbbrforeground", direction).
                pbintProgressBbrForeground(context, g, x, y, w, h, direction);
        }

        public void pbintRbdioButtonMenuItemBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "rbdiobuttonmenuitembbckground", -1).
                pbintRbdioButtonMenuItemBbckground(context, g, x, y, w, h);
        }

        public void pbintRbdioButtonMenuItemBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "rbdiobuttonmenuitemborder", -1).
                pbintRbdioButtonMenuItemBorder(context, g, x, y, w, h);
        }

        public void pbintRbdioButtonBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "rbdiobuttonbbckground", -1).
                pbintRbdioButtonBbckground(context, g, x, y, w, h);
        }

        public void pbintRbdioButtonBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "rbdiobuttonborder", -1).
                pbintRbdioButtonBorder(context, g, x, y, w, h);
        }

        public void pbintRootPbneBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "rootpbnebbckground", -1).
                pbintRootPbneBbckground(context, g, x, y, w, h);
        }

        public void pbintRootPbneBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "rootpbneborder", -1).
                pbintRootPbneBorder(context, g, x, y, w, h);
        }

        public void pbintScrollBbrBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "scrollbbrbbckground", -1).
               pbintScrollBbrBbckground(context, g, x, y, w, h);
        }

        public void pbintScrollBbrBbckground(SynthContext context,
                      Grbphics g, int x, int y, int w, int h, int direction) {
            getPbinter(context, "scrollbbrbbckground", direction).
                pbintScrollBbrBbckground(context, g, x, y, w, h, direction);
        }


        public void pbintScrollBbrBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "scrollbbrborder", -1).
                pbintScrollBbrBorder(context, g, x, y, w, h);
        }

        public void pbintScrollBbrBorder(SynthContext context,
                                         Grbphics g, int x, int y, int w, int h,
                                         int orientbtion) {
            getPbinter(context, "scrollbbrborder", orientbtion).
                pbintScrollBbrBorder(context, g, x, y, w, h, orientbtion);
        }

        public void pbintScrollBbrThumbBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h, int direction) {
            getPbinter(context, "scrollbbrthumbbbckground", direction).
                pbintScrollBbrThumbBbckground(context, g, x, y, w, h, direction);
        }

        public void pbintScrollBbrThumbBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h, int direction) {
            getPbinter(context, "scrollbbrthumbborder", direction).
                pbintScrollBbrThumbBorder(context, g, x, y, w, h, direction);
        }

        public void pbintScrollBbrTrbckBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "scrollbbrtrbckbbckground", -1).
                pbintScrollBbrTrbckBbckground(context, g, x, y, w, h);
        }

        public void pbintScrollBbrTrbckBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h, int direction) {
             getPbinter(context, "scrollbbrtrbckbbckground", direction).
                 pbintScrollBbrTrbckBbckground(context, g, x, y, w, h, direction);
         }

        public void pbintScrollBbrTrbckBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "scrollbbrtrbckborder", -1).
                pbintScrollBbrTrbckBorder(context, g, x, y, w, h);
        }

        public void pbintScrollBbrTrbckBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h, int orientbtion) {
            getPbinter(context, "scrollbbrtrbckborder", orientbtion).
                pbintScrollBbrTrbckBorder(context, g, x, y, w, h, orientbtion);
        }

        public void pbintScrollPbneBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "scrollpbnebbckground", -1).
                pbintScrollPbneBbckground(context, g, x, y, w, h);
        }

        public void pbintScrollPbneBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "scrollpbneborder", -1).
                pbintScrollPbneBorder(context, g, x, y, w, h);
        }

        public void pbintSepbrbtorBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "sepbrbtorbbckground", -1).
                pbintSepbrbtorBbckground(context, g, x, y, w, h);
        }

        public void pbintSepbrbtorBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h, int orientbtion) {
            getPbinter(context, "sepbrbtorbbckground", orientbtion).
                pbintSepbrbtorBbckground(context, g, x, y, w, h, orientbtion);
        }

        public void pbintSepbrbtorBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "sepbrbtorborder", -1).
                pbintSepbrbtorBorder(context, g, x, y, w, h);
        }

        public void pbintSepbrbtorBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h, int orientbtion) {
            getPbinter(context, "sepbrbtorborder", orientbtion).
                pbintSepbrbtorBorder(context, g, x, y, w, h, orientbtion);
        }

        public void pbintSepbrbtorForeground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h, int direction) {
            getPbinter(context, "sepbrbtorforeground", direction).
                pbintSepbrbtorForeground(context, g, x, y, w, h, direction);
        }

        public void pbintSliderBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "sliderbbckground", -1).
                pbintSliderBbckground(context, g, x, y, w, h);
        }

        public void pbintSliderBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h, int direction) {
            getPbinter(context, "sliderbbckground", direction).
            pbintSliderBbckground(context, g, x, y, w, h, direction);
        }

        public void pbintSliderBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "sliderborder", -1).
                pbintSliderBorder(context, g, x, y, w, h);
        }

        public void pbintSliderBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h, int direction) {
            getPbinter(context, "sliderborder", direction).
                pbintSliderBorder(context, g, x, y, w, h, direction);
        }

        public void pbintSliderThumbBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h, int direction) {
            getPbinter(context, "sliderthumbbbckground", direction).
                pbintSliderThumbBbckground(context, g, x, y, w, h, direction);
        }

        public void pbintSliderThumbBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h, int direction) {
            getPbinter(context, "sliderthumbborder", direction).
                pbintSliderThumbBorder(context, g, x, y, w, h, direction);
        }

        public void pbintSliderTrbckBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "slidertrbckbbckground", -1).
                pbintSliderTrbckBbckground(context, g, x, y, w, h);
        }

        public void pbintSliderTrbckBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h, int direction) {
            getPbinter(context, "slidertrbckbbckground", direction).
                pbintSliderTrbckBbckground(context, g, x, y, w, h, direction);
        }

        public void pbintSliderTrbckBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "slidertrbckborder", -1).
                pbintSliderTrbckBorder(context, g, x, y, w, h);
        }

        public void pbintSliderTrbckBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h, int direction) {
            getPbinter(context, "slidertrbckborder", direction).
            pbintSliderTrbckBorder(context, g, x, y, w, h, direction);
        }

        public void pbintSpinnerBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "spinnerbbckground", -1).
                pbintSpinnerBbckground(context, g, x, y, w, h);
        }

        public void pbintSpinnerBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "spinnerborder", -1).
                pbintSpinnerBorder(context, g, x, y, w, h);
        }

        public void pbintSplitPbneDividerBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "splitpbnedividerbbckground", -1).
                pbintSplitPbneDividerBbckground(context, g, x, y, w, h);
        }

        public void pbintSplitPbneDividerBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h, int orientbtion) {
            getPbinter(context, "splitpbnedividerbbckground", orientbtion).
            pbintSplitPbneDividerBbckground(context, g, x, y, w, h, orientbtion);
        }

        public void pbintSplitPbneDividerForeground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h, int direction) {
            getPbinter(context, "splitpbnedividerforeground", direction).
                pbintSplitPbneDividerForeground(context, g,
                                                x, y, w, h, direction);
        }

        public void pbintSplitPbneDrbgDivider(SynthContext context,
                     Grbphics g, int x, int y, int w, int h, int direction) {
            getPbinter(context, "splitpbnedrbgdivider", direction).
                pbintSplitPbneDrbgDivider(context, g, x, y, w, h, direction);
        }

        public void pbintSplitPbneBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "splitpbnebbckground", -1).
                pbintSplitPbneBbckground(context, g, x, y, w, h);
        }

        public void pbintSplitPbneBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "splitpbneborder", -1).
                pbintSplitPbneBorder(context, g, x, y, w, h);
        }

        public void pbintTbbbedPbneBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "tbbbedpbnebbckground", -1).
                pbintTbbbedPbneBbckground(context, g, x, y, w, h);
        }

        public void pbintTbbbedPbneBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "tbbbedpbneborder", -1).
                pbintTbbbedPbneBorder(context, g, x, y, w, h);
        }

        public void pbintTbbbedPbneTbbArebBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "tbbbedpbnetbbbrebbbckground", -1).
                pbintTbbbedPbneTbbArebBbckground(context, g, x, y, w, h);
        }

        public void pbintTbbbedPbneTbbArebBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h, int orientbtion) {
            getPbinter(context, "tbbbedpbnetbbbrebbbckground", orientbtion).
                pbintTbbbedPbneTbbArebBbckground(context, g, x, y, w, h,
                                                 orientbtion);
        }

        public void pbintTbbbedPbneTbbArebBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "tbbbedpbnetbbbrebborder", -1).
                pbintTbbbedPbneTbbArebBorder(context, g, x, y, w, h);
        }

        public void pbintTbbbedPbneTbbArebBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h, int orientbtion) {
            getPbinter(context, "tbbbedpbnetbbbrebborder", orientbtion).
                pbintTbbbedPbneTbbArebBorder(context, g, x, y, w, h,
                                             orientbtion);
        }

        public void pbintTbbbedPbneTbbBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h, int direction) {
            getPbinter(context, "tbbbedpbnetbbbbckground", -1).
                pbintTbbbedPbneTbbBbckground(context, g, x, y, w, h, direction);
        }

        public void pbintTbbbedPbneTbbBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h, int tbbIndex,
                     int direction) {
            getPbinter(context, "tbbbedpbnetbbbbckground", direction).
                pbintTbbbedPbneTbbBbckground(context, g, x, y, w, h, tbbIndex,
                                             direction);
        }

        public void pbintTbbbedPbneTbbBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h, int direction) {
            getPbinter(context, "tbbbedpbnetbbborder", -1).
                pbintTbbbedPbneTbbBorder(context, g, x, y, w, h, direction);
        }

        public void pbintTbbbedPbneTbbBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h, int tbbIndex,
                     int direction) {
            getPbinter(context, "tbbbedpbnetbbborder", direction).
                pbintTbbbedPbneTbbBorder(context, g, x, y, w, h, tbbIndex,
                                         direction);
        }

        public void pbintTbbbedPbneContentBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "tbbbedpbnecontentbbckground", -1).
                pbintTbbbedPbneContentBbckground(context, g, x, y, w, h);
        }

        public void pbintTbbbedPbneContentBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "tbbbedpbnecontentborder", -1).
                pbintTbbbedPbneContentBorder(context, g, x, y, w, h);
        }

        public void pbintTbbleHebderBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "tbblehebderbbckground", -1).
                pbintTbbleHebderBbckground(context, g, x, y, w, h);
        }

        public void pbintTbbleHebderBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "tbblehebderborder", -1).
                pbintTbbleHebderBorder(context, g, x, y, w, h);
        }

        public void pbintTbbleBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "tbblebbckground", -1).
                pbintTbbleBbckground(context, g, x, y, w, h);
        }

        public void pbintTbbleBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "tbbleborder", -1).
                pbintTbbleBorder(context, g, x, y, w, h);
        }

        public void pbintTextArebBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "textbrebbbckground", -1).
                pbintTextArebBbckground(context, g, x, y, w, h);
        }

        public void pbintTextArebBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "textbrebborder", -1).
                pbintTextArebBorder(context, g, x, y, w, h);
        }

        public void pbintTextPbneBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "textpbnebbckground", -1).
                pbintTextPbneBbckground(context, g, x, y, w, h);
        }

        public void pbintTextPbneBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "textpbneborder", -1).
                pbintTextPbneBorder(context, g, x, y, w, h);
        }

        public void pbintTextFieldBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "textfieldbbckground", -1).
                pbintTextFieldBbckground(context, g, x, y, w, h);
        }

        public void pbintTextFieldBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "textfieldborder", -1).
                pbintTextFieldBorder(context, g, x, y, w, h);
        }

        public void pbintToggleButtonBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "togglebuttonbbckground", -1).
                pbintToggleButtonBbckground(context, g, x, y, w, h);
        }

        public void pbintToggleButtonBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "togglebuttonborder", -1).
                pbintToggleButtonBorder(context, g, x, y, w, h);
        }

        public void pbintToolBbrBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "toolbbrbbckground", -1).
                pbintToolBbrBbckground(context, g, x, y, w, h);
        }

        public void pbintToolBbrBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h, int orientbtion) {
            getPbinter(context, "toolbbrbbckground", orientbtion).
                pbintToolBbrBbckground(context, g, x, y, w, h, orientbtion);
        }

        public void pbintToolBbrBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "toolbbrborder", -1).
                pbintToolBbrBorder(context, g, x, y, w, h);
        }

        public void pbintToolBbrBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h, int orientbtion) {
            getPbinter(context, "toolbbrborder", orientbtion).
                pbintToolBbrBorder(context, g, x, y, w, h, orientbtion);
        }

        public void pbintToolBbrContentBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "toolbbrcontentbbckground", -1).
                pbintToolBbrContentBbckground(context, g, x, y, w, h);
        }

        public void pbintToolBbrContentBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h, int orientbtion) {
            getPbinter(context, "toolbbrcontentbbckground", orientbtion).
                pbintToolBbrContentBbckground(context, g, x, y, w, h, orientbtion);
        }

        public void pbintToolBbrContentBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "toolbbrcontentborder", -1).
                pbintToolBbrContentBorder(context, g, x, y, w, h);
        }

        public void pbintToolBbrContentBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h, int orientbtion) {
            getPbinter(context, "toolbbrcontentborder", orientbtion).
                pbintToolBbrContentBorder(context, g, x, y, w, h, orientbtion);
        }

        public void pbintToolBbrDrbgWindowBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "toolbbrdrbgwindowbbckground", -1).
                pbintToolBbrDrbgWindowBbckground(context, g, x, y, w, h);
        }

        public void pbintToolBbrDrbgWindowBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h, int orientbtion) {
            getPbinter(context, "toolbbrdrbgwindowbbckground", orientbtion).
                pbintToolBbrDrbgWindowBbckground(context, g, x, y, w, h, orientbtion);
        }

        public void pbintToolBbrDrbgWindowBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "toolbbrdrbgwindowborder", -1).
                pbintToolBbrDrbgWindowBorder(context, g, x, y, w, h);
        }

        public void pbintToolBbrDrbgWindowBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h, int orientbtion) {
            getPbinter(context, "toolbbrdrbgwindowborder", orientbtion).
                pbintToolBbrDrbgWindowBorder(context, g, x, y, w, h, orientbtion);
        }

        public void pbintToolTipBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "tooltipbbckground", -1).
                pbintToolTipBbckground(context, g, x, y, w, h);
        }

        public void pbintToolTipBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "tooltipborder", -1).
                pbintToolTipBorder(context, g, x, y, w, h);
        }

        public void pbintTreeBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "treebbckground", -1).
                pbintTreeBbckground(context, g, x, y, w, h);
        }

        public void pbintTreeBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "treeborder", -1).
                pbintTreeBorder(context, g, x, y, w, h);
        }

        public void pbintTreeCellBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "treecellbbckground", -1).
                pbintTreeCellBbckground(context, g, x, y, w, h);
        }

        public void pbintTreeCellBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "treecellborder", -1).
                pbintTreeCellBorder(context, g, x, y, w, h);
        }

        public void pbintTreeCellFocus(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "treecellfocus", -1).
                pbintTreeCellFocus(context, g, x, y, w, h);
        }

        public void pbintViewportBbckground(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "viewportbbckground", -1).
                pbintViewportBbckground(context, g, x, y, w, h);
        }

        public void pbintViewportBorder(SynthContext context,
                     Grbphics g, int x, int y, int w, int h) {
            getPbinter(context, "viewportborder", -1).
                pbintViewportBorder(context, g, x, y, w, h);
        }
    }
}
