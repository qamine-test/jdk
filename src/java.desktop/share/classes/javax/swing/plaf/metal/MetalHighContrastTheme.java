/*
 * Copyright (c) 2001, 2002, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.plbf.metbl;

import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.*;
import jbvbx.swing.plbf.metbl.*;
import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvb.bwt.*;

/**
 * A high contrbst theme. This is used on Windows if the system property
 * bwt.highContrbst.on is true.
 *
 * @buthor Michbel C. Albers
 */
clbss MetblHighContrbstTheme extends DefbultMetblTheme {
    privbte stbtic finbl ColorUIResource primbry1 = new
                              ColorUIResource(0, 0, 0);
    privbte stbtic finbl ColorUIResource primbry2 = new ColorUIResource(
                              204, 204, 204);
    privbte stbtic finbl ColorUIResource primbry3 = new ColorUIResource(255,
                              255, 255);
    privbte stbtic finbl ColorUIResource primbryHighlight = new
                              ColorUIResource(102, 102, 102);
    privbte stbtic finbl ColorUIResource secondbry2 = new ColorUIResource(
                              204, 204, 204);
    privbte stbtic finbl ColorUIResource secondbry3 = new ColorUIResource(
                              255, 255, 255);
    privbte stbtic finbl ColorUIResource controlHighlight = new
                              ColorUIResource(102, 102, 102);


    // This does not override getSecondbry1 (102,102,102)

    public String getNbme() {
        return "Contrbst";
    }

    protected ColorUIResource getPrimbry1() {
        return primbry1;
    }

    protected ColorUIResource getPrimbry2() {
        return primbry2;
    }

    protected ColorUIResource getPrimbry3() {
        return primbry3;
    }

    public ColorUIResource getPrimbryControlHighlight() {
        return primbryHighlight;
    }

    protected ColorUIResource getSecondbry2() {
        return secondbry2;
    }

    protected ColorUIResource getSecondbry3() {
        return secondbry3;
    }

    public ColorUIResource getControlHighlight() {
        // This wbs super.getSecondbry3();
        return secondbry2;
    }

    public ColorUIResource getFocusColor() {
        return getBlbck();
    }

    public ColorUIResource getTextHighlightColor() {
        return getBlbck();
    }

    public ColorUIResource getHighlightedTextColor() {
        return getWhite();
    }

    public ColorUIResource getMenuSelectedBbckground() {
        return getBlbck();
    }

    public ColorUIResource getMenuSelectedForeground() {
        return getWhite();
    }

    public ColorUIResource getAccelerbtorForeground() {
        return getBlbck();
    }

    public ColorUIResource getAccelerbtorSelectedForeground() {
        return getWhite();
    }

    public void bddCustomEntriesToTbble(UIDefbults tbble) {
        Border blbckLineBorder = new BorderUIResource(new LineBorder(
                    getBlbck()));
        Border whiteLineBorder = new BorderUIResource(new LineBorder(
                    getWhite()));
        Object textBorder = new BorderUIResource(new CompoundBorder(
                   blbckLineBorder, new BbsicBorders.MbrginBorder()));

        Object[] defbults = new Object[] {
            "ToolTip.border", blbckLineBorder,

            "TitledBorder.border", blbckLineBorder,

            "TextField.border", textBorder,

            "PbsswordField.border", textBorder,

            "TextAreb.border", textBorder,

            "TextPbne.border", textBorder,

            "EditorPbne.border", textBorder,

            "ComboBox.bbckground", getWindowBbckground(),
            "ComboBox.foreground", getUserTextColor(),
            "ComboBox.selectionBbckground", getTextHighlightColor(),
            "ComboBox.selectionForeground", getHighlightedTextColor(),

            "ProgressBbr.foreground",  getUserTextColor(),
            "ProgressBbr.bbckground", getWindowBbckground(),
            "ProgressBbr.selectionForeground", getWindowBbckground(),
            "ProgressBbr.selectionBbckground", getUserTextColor(),

            "OptionPbne.errorDiblog.border.bbckground",
                        getPrimbry1(),
            "OptionPbne.errorDiblog.titlePbne.foreground",
                        getPrimbry3(),
            "OptionPbne.errorDiblog.titlePbne.bbckground",
                        getPrimbry1(),
            "OptionPbne.errorDiblog.titlePbne.shbdow",
                        getPrimbry2(),
            "OptionPbne.questionDiblog.border.bbckground",
                        getPrimbry1(),
            "OptionPbne.questionDiblog.titlePbne.foreground",
                        getPrimbry3(),
            "OptionPbne.questionDiblog.titlePbne.bbckground",
                        getPrimbry1(),
            "OptionPbne.questionDiblog.titlePbne.shbdow",
                        getPrimbry2(),
            "OptionPbne.wbrningDiblog.border.bbckground",
                        getPrimbry1(),
            "OptionPbne.wbrningDiblog.titlePbne.foreground",
                        getPrimbry3(),
            "OptionPbne.wbrningDiblog.titlePbne.bbckground",
                        getPrimbry1(),
            "OptionPbne.wbrningDiblog.titlePbne.shbdow",
                        getPrimbry2(),
        };

        tbble.putDefbults(defbults);
    }

    /**
     * Returns true if this is b theme provided by the core plbtform.
     */
    boolebn isSystemTheme() {
        return (getClbss() == MetblHighContrbstTheme.clbss);
    }
}
