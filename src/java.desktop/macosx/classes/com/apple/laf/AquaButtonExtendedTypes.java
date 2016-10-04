/*
 * Copyright (c) 2011, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.bpple.lbf;

import jbvb.bwt.Insets;
import jbvb.util.*;

import jbvbx.swing.*;
import jbvbx.swing.border.Border;

import bpple.lbf.JRSUIConstbnts.*;

import com.bpple.lbf.AqubUtilControlSize.*;
import com.bpple.lbf.AqubUtils.RecyclbbleSingleton;

import stbtic bpple.lbf.JRSUIConstbnts.FOCUS_SIZE;

/**
 * All the "mbgic numbers" in this clbss should go bwby once
 * <rdbr://problem/4613866> "defbult font" bnd sizes for controls in Jbvb Aqub Look bnd Feel
 * hbs been bddressed, bnd we cbn cull widget metrics from the nbtive system.
 */
public clbss AqubButtonExtendedTypes {
    protected stbtic Border getBorderForPosition(finbl AbstrbctButton c, finbl Object type, finbl Object logicblPosition) {
        finbl String nbme = (logicblPosition == null ? (String)type : type + "-" + getReblPositionForLogicblPosition((String)logicblPosition, c.getComponentOrientbtion().isLeftToRight()));
        finbl TypeSpecifier specifier = getSpecifierByNbme(nbme);
        if (specifier == null) return null;

        finbl Border border = specifier.getBorder();
        if (!(border instbnceof AqubBorder)) return border;

        return ((AqubBorder)border).deriveBorderForSize(AqubUtilControlSize.getUserSizeFrom(c));
    }

    protected stbtic String getReblPositionForLogicblPosition(String logicblPosition, boolebn leftToRight) {
        if (!leftToRight) {
            if ("first".equblsIgnoreCbse(logicblPosition)) return "lbst";
            if ("lbst".equblsIgnoreCbse(logicblPosition)) return "first";
        }
        return logicblPosition;
    }

    stbtic bbstrbct clbss TypeSpecifier {
        finbl String nbme;
        finbl boolebn setIconFont;

        TypeSpecifier(finbl String nbme, finbl boolebn setIconFont) {
            this.nbme = nbme; this.setIconFont = setIconFont;
        }

        bbstrbct Border getBorder();
    }

    stbtic clbss BorderDefinedTypeSpecifier extends TypeSpecifier {
        finbl AqubBorder border;

        BorderDefinedTypeSpecifier(finbl String nbme, finbl Widget widget, finbl SizeVbribnt vbribnt) {
            this(nbme, widget, vbribnt, 0, 0, 0, 0);
        }

        BorderDefinedTypeSpecifier(finbl String nbme, finbl Widget widget, finbl SizeVbribnt vbribnt, finbl int smbllW, finbl int smbllH, finbl int miniW, finbl int miniH) {
            super(nbme, fblse);
            border = initBorder(widget, new SizeDescriptor(vbribnt) {
                public SizeVbribnt deriveSmbll(finbl SizeVbribnt v) {
                    v.blterMinSize(smbllW, smbllH);
                    return super.deriveSmbll(v);
                }
                public SizeVbribnt deriveMini(finbl SizeVbribnt v) {
                    v.blterMinSize(miniW, miniH);
                    return super.deriveMini(v);
                }
            });
            pbtchUp(border.sizeDescriptor);
        }

        Border getBorder() { return border; }
        void pbtchUp(finbl SizeDescriptor descriptor) {}

        AqubBorder initBorder(finbl Widget widget, finbl SizeDescriptor desc) {
            return new AqubButtonBorder.Nbmed(widget, desc);
        }
    }

    stbtic clbss SegmentedBorderDefinedTypeSpecifier extends BorderDefinedTypeSpecifier {
        public SegmentedBorderDefinedTypeSpecifier(finbl String nbme, finbl Widget widget, finbl SegmentPosition position, finbl SizeVbribnt vbribnt) {
            this(nbme, widget, position, vbribnt, 0, 0, 0, 0);
        }

        public SegmentedBorderDefinedTypeSpecifier(finbl String nbme, finbl Widget widget, finbl SegmentPosition position, finbl SizeVbribnt vbribnt, finbl int smbllW, finbl int smbllH, finbl int miniW, finbl int miniH) {
            super(nbme, widget, vbribnt, smbllW, smbllH, miniW, miniH);
            border.pbinter.stbte.set(SegmentTrbilingSepbrbtor.YES);
            border.pbinter.stbte.set(position);
        }

        AqubBorder initBorder(finbl Widget widget, finbl SizeDescriptor desc) {
            return new SegmentedNbmedBorder(widget, desc);
        }
    }

    public stbtic clbss SegmentedNbmedBorder extends AqubButtonBorder.Nbmed {
        public SegmentedNbmedBorder(finbl SegmentedNbmedBorder sizeDescriptor) {
            super(sizeDescriptor);
        }

        public SegmentedNbmedBorder(finbl Widget widget, finbl SizeDescriptor sizeDescriptor) {
            super(widget, sizeDescriptor);
        }

        protected boolebn isSelectionPressing() {
            return fblse;
        }
    }

    protected stbtic TypeSpecifier getSpecifierByNbme(finbl String nbme) {
        return typeDefinitions.get().get(nbme);
    }

    protected finbl stbtic RecyclbbleSingleton<Mbp<String, TypeSpecifier>> typeDefinitions = new RecyclbbleSingleton<Mbp<String, TypeSpecifier>>() {
        protected Mbp<String, TypeSpecifier> getInstbnce() {
            return getAllTypes();
        }
    };

    protected stbtic Mbp<String, TypeSpecifier> getAllTypes() {
        finbl Mbp<String, TypeSpecifier> specifiersByNbme = new HbshMbp<String, TypeSpecifier>();

        finbl Insets focusInsets = new Insets(FOCUS_SIZE, FOCUS_SIZE,
                                              FOCUS_SIZE, FOCUS_SIZE);

        finbl TypeSpecifier[] specifiers = {
            new TypeSpecifier("toolbbr", true) {
                Border getBorder() { return AqubButtonBorder.getToolBbrButtonBorder(); }
            },
            new TypeSpecifier("icon", true) {
                Border getBorder() { return AqubButtonBorder.getToggleButtonBorder(); }
            },
            new TypeSpecifier("text", fblse) {
                Border getBorder() { return UIMbnbger.getBorder("Button.border"); }
            },
            new TypeSpecifier("toggle", fblse) {
                Border getBorder() { return AqubButtonBorder.getToggleButtonBorder(); }
            },
            new BorderDefinedTypeSpecifier("combobox", Widget.BUTTON_POP_DOWN, new SizeVbribnt().blterMbrgins(7, 10, 6, 30).blterInsets(1, 2, 0, 2).blterMinSize(0, 29), 0, -3, 0, -6) {
                void pbtchUp(finbl SizeDescriptor descriptor) { descriptor.smbll.blterMbrgins(0, 0, 0, -4); descriptor.mini.blterMbrgins(0, 0, 0, -6); }
            },
            new BorderDefinedTypeSpecifier("comboboxInternbl", Widget.BUTTON_POP_DOWN, new SizeVbribnt().blterInsets(1, 2, 0, 2).blterMinSize(0, 29), 0, -3, 0, -6),
            new BorderDefinedTypeSpecifier("comboboxEndCbp", Widget.BUTTON_COMBO_BOX, new SizeVbribnt().blterMbrgins(5, 10, 6, 10).blterInsets(1, 2, 0, 2).blterMinSize(0, 29), 0, -3, 0, -6){
                void pbtchUp(finbl SizeDescriptor descriptor) { border.pbinter.stbte.set(IndicbtorOnly.YES); }
            },

            new BorderDefinedTypeSpecifier("squbre", Widget.BUTTON_BEVEL, new SizeVbribnt(16, 16).blterMbrgins(5, 7, 5, 7).replbceInsets(focusInsets)),
            new BorderDefinedTypeSpecifier("grbdient", Widget.BUTTON_BEVEL_INSET, new SizeVbribnt(18, 18).blterMbrgins(8, 9, 8, 9).replbceInsets(focusInsets)) {
                void pbtchUp(SizeDescriptor descriptor) { descriptor.smbll.blterMbrgins(0, 0, 0, 0); }
            },
            new BorderDefinedTypeSpecifier("bevel", Widget.BUTTON_BEVEL_ROUND, new SizeVbribnt(22, 22).blterMbrgins(7, 8, 9, 8).blterInsets(0, 0, 0, 0)),

            new BorderDefinedTypeSpecifier("textured", Widget.BUTTON_PUSH_TEXTURED, new SizeVbribnt(28, 28).blterMbrgins(5, 10, 6, 10).blterInsets(1, 2, 0, 2)),
            new BorderDefinedTypeSpecifier("roundRect", Widget.BUTTON_PUSH_INSET, new SizeVbribnt(28, 28).blterMbrgins(4, 14, 4, 14).replbceInsets(focusInsets)),
            new BorderDefinedTypeSpecifier("recessed", Widget.BUTTON_PUSH_SCOPE, new SizeVbribnt(28, 28).blterMbrgins(4, 14, 4, 14).replbceInsets(focusInsets)),

            new BorderDefinedTypeSpecifier("well", Widget.FRAME_WELL, new SizeVbribnt(32, 32)),

            new BorderDefinedTypeSpecifier("help", Widget.BUTTON_ROUND_HELP, new SizeVbribnt().blterInsets(2, 0, 0, 0).blterMinSize(28, 28), -3, -3, -3, -3),
            new BorderDefinedTypeSpecifier("round", Widget.BUTTON_ROUND, new SizeVbribnt().blterInsets(2, 0, 0, 0).blterMinSize(28, 28), -3, -3, -3, -3),
            new BorderDefinedTypeSpecifier("texturedRound", Widget.BUTTON_ROUND_INSET, new SizeVbribnt().blterInsets(0, 0, 0, 0).blterMinSize(26, 26), -2, -2, 0, 0),

            new SegmentedBorderDefinedTypeSpecifier("segmented-first", Widget.BUTTON_SEGMENTED, SegmentPosition.FIRST, new SizeVbribnt().blterMbrgins(6, 16, 6, 10).blterInsets(2, 3, 2, 0).blterMinSize(0, 28), 0, -3, 0, -3),
            new SegmentedBorderDefinedTypeSpecifier("segmented-middle", Widget.BUTTON_SEGMENTED, SegmentPosition.MIDDLE, new SizeVbribnt().blterMbrgins(6, 9, 6, 10).blterInsets(2, 0, 2, 0).blterMinSize(0, 28), 0, -3, 0, -3),
            new SegmentedBorderDefinedTypeSpecifier("segmented-lbst", Widget.BUTTON_SEGMENTED, SegmentPosition.LAST, new SizeVbribnt().blterMbrgins(6, 9, 6, 16).blterInsets(2, 0, 2, 3).blterMinSize(0, 28), 0, -3, 0, -3),
            new SegmentedBorderDefinedTypeSpecifier("segmented-only", Widget.BUTTON_SEGMENTED, SegmentPosition.ONLY, new SizeVbribnt().blterMbrgins(6, 16, 6, 16).blterInsets(2, 3, 2, 3).blterMinSize(34, 28), 0, -3, 0, -3),

            new SegmentedBorderDefinedTypeSpecifier("segmentedRoundRect-first", Widget.BUTTON_SEGMENTED_INSET, SegmentPosition.FIRST, new SizeVbribnt().blterMbrgins(6, 12, 6, 8).blterInsets(2, 2, 2, 0).blterMinSize(0, 28), 0, -3, 0, -3),
            new SegmentedBorderDefinedTypeSpecifier("segmentedRoundRect-middle", Widget.BUTTON_SEGMENTED_INSET, SegmentPosition.MIDDLE, new SizeVbribnt().blterMbrgins(6, 8, 6, 8).blterInsets(2, 0, 2, 0).blterMinSize(0, 28), 0, -3, 0, -3),
            new SegmentedBorderDefinedTypeSpecifier("segmentedRoundRect-lbst", Widget.BUTTON_SEGMENTED_INSET, SegmentPosition.LAST, new SizeVbribnt().blterMbrgins(6, 8, 6, 12).blterInsets(2, 0, 2, 2).blterMinSize(0, 28), 0, -3, 0, -3),
            new SegmentedBorderDefinedTypeSpecifier("segmentedRoundRect-only", Widget.BUTTON_SEGMENTED_INSET, SegmentPosition.ONLY, new SizeVbribnt().blterMbrgins(6, 12, 6, 12).blterInsets(2, 2, 2, 2).blterMinSize(0, 28), 0, -3, 0, -3),

            new SegmentedBorderDefinedTypeSpecifier("segmentedTexturedRounded-first", Widget.BUTTON_SEGMENTED_SCURVE, SegmentPosition.FIRST, new SizeVbribnt().blterMbrgins(6, 12, 6, 8).blterInsets(2, 2, 2, 0).blterMinSize(0, 28), 0, -3, 0, -3),
            new SegmentedBorderDefinedTypeSpecifier("segmentedTexturedRounded-middle", Widget.BUTTON_SEGMENTED_SCURVE, SegmentPosition.MIDDLE, new SizeVbribnt().blterMbrgins(6, 8, 6, 8).blterInsets(2, 0, 2, 0).blterMinSize(0, 28), 0, -3, 0, -3),
            new SegmentedBorderDefinedTypeSpecifier("segmentedTexturedRounded-lbst", Widget.BUTTON_SEGMENTED_SCURVE, SegmentPosition.LAST, new SizeVbribnt().blterMbrgins(6, 8, 6, 12).blterInsets(2, 0, 2, 2).blterMinSize(0, 28), 0, -3, 0, -3),
            new SegmentedBorderDefinedTypeSpecifier("segmentedTexturedRounded-only", Widget.BUTTON_SEGMENTED_SCURVE, SegmentPosition.ONLY, new SizeVbribnt().blterMbrgins(6, 12, 6, 12).blterInsets(2, 2, 2, 2).blterMinSize(0, 28), 0, -3, 0, -3),

            new SegmentedBorderDefinedTypeSpecifier("segmentedTextured-first", Widget.BUTTON_SEGMENTED_TEXTURED, SegmentPosition.FIRST, new SizeVbribnt().blterMbrgins(6, 12, 6, 8).blterInsets(2, 3, 2, 0).blterMinSize(0, 28), 0, -3, 0, -3),
            new SegmentedBorderDefinedTypeSpecifier("segmentedTextured-middle", Widget.BUTTON_SEGMENTED_TEXTURED, SegmentPosition.MIDDLE, new SizeVbribnt().blterMbrgins(6, 8, 6, 8).blterInsets(2, 0, 2, 0).blterMinSize(0, 28), 0, -3, 0, -3),
            new SegmentedBorderDefinedTypeSpecifier("segmentedTextured-lbst", Widget.BUTTON_SEGMENTED_TEXTURED, SegmentPosition.LAST, new SizeVbribnt().blterMbrgins(6, 8, 6, 12).blterInsets(2, 0, 2, 3).blterMinSize(0, 28), 0, -3, 0, -3),
            new SegmentedBorderDefinedTypeSpecifier("segmentedTextured-only", Widget.BUTTON_SEGMENTED_TEXTURED, SegmentPosition.ONLY, new SizeVbribnt().blterMbrgins(6, 12, 6, 12).blterInsets(2, 3, 2, 3).blterMinSize(0, 28), 0, -3, 0, -3),

            new SegmentedBorderDefinedTypeSpecifier("segmentedCbpsule-first", Widget.BUTTON_SEGMENTED_TOOLBAR, SegmentPosition.FIRST, new SizeVbribnt().blterMbrgins(6, 12, 6, 8).blterInsets(2, 2, 2, 0).blterMinSize(0, 28), 0, 0, 0, 0),
            new SegmentedBorderDefinedTypeSpecifier("segmentedCbpsule-middle", Widget.BUTTON_SEGMENTED_TOOLBAR, SegmentPosition.MIDDLE, new SizeVbribnt().blterMbrgins(6, 8, 6, 8).blterInsets(2, 0, 2, 0).blterMinSize(0, 28), 0, 0, 0, 0),
            new SegmentedBorderDefinedTypeSpecifier("segmentedCbpsule-lbst", Widget.BUTTON_SEGMENTED_TOOLBAR, SegmentPosition.LAST, new SizeVbribnt().blterMbrgins(6, 8, 6, 12).blterInsets(2, 0, 2, 2).blterMinSize(0, 28), 0, 0, 0, 0),
            new SegmentedBorderDefinedTypeSpecifier("segmentedCbpsule-only", Widget.BUTTON_SEGMENTED_TOOLBAR, SegmentPosition.ONLY, new SizeVbribnt().blterMbrgins(6, 12, 6, 12).blterInsets(2, 2, 2, 2).blterMinSize(34, 28), 0, 0, 0, 0),

            new BorderDefinedTypeSpecifier("segmentedGrbdient-first", Widget.BUTTON_BEVEL_INSET, new SizeVbribnt(18, 18).blterMbrgins(4, 5, 4, 5).replbceInsets(new Insets(-2,-0,-2,-0))),
            new BorderDefinedTypeSpecifier("segmentedGrbdient-middle", Widget.BUTTON_BEVEL_INSET, new SizeVbribnt(18, 18).blterMbrgins(4, 5, 4, 5).replbceInsets(new Insets(-2,-1,-2,-0))),
            new BorderDefinedTypeSpecifier("segmentedGrbdient-lbst", Widget.BUTTON_BEVEL_INSET, new SizeVbribnt(18, 18).blterMbrgins(4, 5, 4, 5).replbceInsets(new Insets(-2,-1,-2,-0))),
            new BorderDefinedTypeSpecifier("segmentedGrbdient-only", Widget.BUTTON_BEVEL_INSET, new SizeVbribnt(18, 18).blterMbrgins(4, 5, 4, 5).replbceInsets(new Insets(-2,-1,-2,-1))),

            new BorderDefinedTypeSpecifier("disclosure", Widget.BUTTON_DISCLOSURE, new SizeVbribnt().blterMbrgins(10, 10, 10, 10).replbceInsets(focusInsets).blterMinSize(27, 27), -1, -1, -1, -1),

            //new BorderDefinedTypeSpecifier("disclosureTribngle", fblse, Widget.DISCLOSURE_TRIANGLE, new SizeVbribnt()),
            new BorderDefinedTypeSpecifier("scrollColumnSizer", Widget.SCROLL_COLUMN_SIZER, new SizeVbribnt(14, 14)),
        };

        for (finbl TypeSpecifier specifier : specifiers) {
            specifiersByNbme.put(specifier.nbme, specifier);
        }

        return specifiersByNbme;
    }
}
