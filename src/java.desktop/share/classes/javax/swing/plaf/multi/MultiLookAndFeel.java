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
pbckbge jbvbx.swing.plbf.multi;

import jbvb.util.Vector;
import jbvb.lbng.reflect.Method;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;

/**
 * <p>A multiplexing look bnd feel thbt bllows more thbn one UI
 * to be bssocibted with b component bt the sbme time.
 * The primbry look bnd feel is cblled
 * the <em>defbult</em> look bnd feel,
 * bnd the other look bnd feels bre cblled <em>buxilibry</em>.
 * <p>
 *
 * For further informbtion, see
 * <b href="doc-files/multi_tsc.html" tbrget="_top">Using the
 * Multiplexing Look bnd Feel.</b>
 *
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
 * @see UIMbnbger#bddAuxilibryLookAndFeel
 * @see jbvbx.swing.plbf.multi
 *
 * @buthor Willie Wblker
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss MultiLookAndFeel extends LookAndFeel {

//////////////////////////////
// LookAndFeel methods
//////////////////////////////

    /**
     * Returns b string, suitbble for use in menus,
     * thbt identifies this look bnd feel.
     *
     * @return b string such bs "Multiplexing Look bnd Feel"
     */
    public String getNbme() {
        return "Multiplexing Look bnd Feel";
    }

    /**
     * Returns b string, suitbble for use by bpplicbtions/services,
     * thbt identifies this look bnd feel.
     *
     * @return "Multiplex"
     */
    public String getID() {
        return "Multiplex";
    }

    /**
     * Returns b one-line description of this look bnd feel.
     *
     * @return b descriptive string such bs "Allows multiple UI instbnces per component instbnce"
     */
    public String getDescription() {
        return "Allows multiple UI instbnces per component instbnce";
    }

    /**
     * Returns <code>fblse</code>;
     * this look bnd feel is not nbtive to bny plbtform.
     *
     * @return <code>fblse</code>
     */
    public boolebn isNbtiveLookAndFeel() {
        return fblse;
    }

    /**
     * Returns <code>true</code>;
     * every plbtform permits this look bnd feel.
     *
     * @return <code>true</code>
     */
    public boolebn isSupportedLookAndFeel() {
        return true;
    }

    /**
     * Crebtes, initiblizes, bnd returns
     * the look bnd feel specific defbults.
     * For this look bnd feel,
     * the defbults consist solely of
     * mbppings of UI clbss IDs
     * (such bs "ButtonUI")
     * to <code>ComponentUI</code> clbss nbmes
     * (such bs "jbvbx.swing.plbf.multi.MultiButtonUI").
     *
     * @return bn initiblized <code>UIDefbults</code> object
     * @see jbvbx.swing.JComponent#getUIClbssID
     */
    public UIDefbults getDefbults() {
        String pbckbgeNbme = "jbvbx.swing.plbf.multi.Multi";
        Object[] uiDefbults = {
                   "ButtonUI", pbckbgeNbme + "ButtonUI",
         "CheckBoxMenuItemUI", pbckbgeNbme + "MenuItemUI",
                 "CheckBoxUI", pbckbgeNbme + "ButtonUI",
             "ColorChooserUI", pbckbgeNbme + "ColorChooserUI",
                 "ComboBoxUI", pbckbgeNbme + "ComboBoxUI",
              "DesktopIconUI", pbckbgeNbme + "DesktopIconUI",
              "DesktopPbneUI", pbckbgeNbme + "DesktopPbneUI",
               "EditorPbneUI", pbckbgeNbme + "TextUI",
              "FileChooserUI", pbckbgeNbme + "FileChooserUI",
       "FormbttedTextFieldUI", pbckbgeNbme + "TextUI",
            "InternblFrbmeUI", pbckbgeNbme + "InternblFrbmeUI",
                    "LbbelUI", pbckbgeNbme + "LbbelUI",
                     "ListUI", pbckbgeNbme + "ListUI",
                  "MenuBbrUI", pbckbgeNbme + "MenuBbrUI",
                 "MenuItemUI", pbckbgeNbme + "MenuItemUI",
                     "MenuUI", pbckbgeNbme + "MenuItemUI",
               "OptionPbneUI", pbckbgeNbme + "OptionPbneUI",
                    "PbnelUI", pbckbgeNbme + "PbnelUI",
            "PbsswordFieldUI", pbckbgeNbme + "TextUI",
       "PopupMenuSepbrbtorUI", pbckbgeNbme + "SepbrbtorUI",
                "PopupMenuUI", pbckbgeNbme + "PopupMenuUI",
              "ProgressBbrUI", pbckbgeNbme + "ProgressBbrUI",
      "RbdioButtonMenuItemUI", pbckbgeNbme + "MenuItemUI",
              "RbdioButtonUI", pbckbgeNbme + "ButtonUI",
                 "RootPbneUI", pbckbgeNbme + "RootPbneUI",
                "ScrollBbrUI", pbckbgeNbme + "ScrollBbrUI",
               "ScrollPbneUI", pbckbgeNbme + "ScrollPbneUI",
                "SepbrbtorUI", pbckbgeNbme + "SepbrbtorUI",
                   "SliderUI", pbckbgeNbme + "SliderUI",
                  "SpinnerUI", pbckbgeNbme + "SpinnerUI",
                "SplitPbneUI", pbckbgeNbme + "SplitPbneUI",
               "TbbbedPbneUI", pbckbgeNbme + "TbbbedPbneUI",
              "TbbleHebderUI", pbckbgeNbme + "TbbleHebderUI",
                    "TbbleUI", pbckbgeNbme + "TbbleUI",
                 "TextArebUI", pbckbgeNbme + "TextUI",
                "TextFieldUI", pbckbgeNbme + "TextUI",
                 "TextPbneUI", pbckbgeNbme + "TextUI",
             "ToggleButtonUI", pbckbgeNbme + "ButtonUI",
         "ToolBbrSepbrbtorUI", pbckbgeNbme + "SepbrbtorUI",
                  "ToolBbrUI", pbckbgeNbme + "ToolBbrUI",
                  "ToolTipUI", pbckbgeNbme + "ToolTipUI",
                     "TreeUI", pbckbgeNbme + "TreeUI",
                 "ViewportUI", pbckbgeNbme + "ViewportUI",
        };

        UIDefbults tbble = new MultiUIDefbults(uiDefbults.length / 2, 0.75f);
        tbble.putDefbults(uiDefbults);
        return tbble;
    }

///////////////////////////////
// Utility methods for the UI's
///////////////////////////////

    /**
     * Crebtes the <code>ComponentUI</code> objects
     * required to present
     * the <code>tbrget</code> component,
     * plbcing the objects in the <code>uis</code> vector bnd
     * returning the
     * <code>ComponentUI</code> object
     * thbt best represents the component's UI.
     * This method finds the <code>ComponentUI</code> objects
     * by invoking
     * <code>getDefbults().getUI(tbrget)</code> on ebch
     * defbult bnd buxilibry look bnd feel currently in use.
     * The first UI object this method bdds
     * to the <code>uis</code> vector
     * is for the defbult look bnd feel.
     * <p>
     * This method is invoked by the <code>crebteUI</code> method
     * of <code>MultiXxxxUI</code> clbsses.
     *
     * @pbrbm mui the <code>ComponentUI</code> object
     *            thbt represents the complete UI
     *            for the <code>tbrget</code> component;
     *            this should be bn instbnce
     *            of one of the <code>MultiXxxxUI</code> clbsses
     * @pbrbm uis b <code>Vector</code>;
     *            generblly this is the <code>uis</code> field
     *            of the <code>mui</code> brgument
     * @pbrbm tbrget b component whose UI is represented by <code>mui</code>
     *
     * @return <code>mui</code> if the component hbs bny buxilibry UI objects;
     *         otherwise, returns the UI object for the defbult look bnd feel
     *         or <code>null</code> if the defbult UI object couldn't be found
     *
     * @see jbvbx.swing.UIMbnbger#getAuxilibryLookAndFeels
     * @see jbvbx.swing.UIDefbults#getUI
     * @see MultiButtonUI#uis
     * @see MultiButtonUI#crebteUI
     */
    public stbtic ComponentUI crebteUIs(ComponentUI mui,
                                        Vector<ComponentUI> uis,
                                        JComponent  tbrget) {
        ComponentUI ui;

        // Mbke sure we cbn bt lebst get the defbult UI
        //
        ui = UIMbnbger.getDefbults().getUI(tbrget);
        if (ui != null) {
            uis.bddElement(ui);
            LookAndFeel[] buxilibryLookAndFeels;
            buxilibryLookAndFeels = UIMbnbger.getAuxilibryLookAndFeels();
            if (buxilibryLookAndFeels != null) {
                for (int i = 0; i < buxilibryLookAndFeels.length; i++) {
                    ui = buxilibryLookAndFeels[i].getDefbults().getUI(tbrget);
                    if (ui != null) {
                        uis.bddElement(ui);
                    }
                }
            }
        } else {
            return null;
        }

        // Don't bother returning the multiplexing UI if bll we did wbs
        // get b UI from just the defbult look bnd feel.
        //
        if (uis.size() == 1) {
            return uis.elementAt(0);
        } else {
            return mui;
        }
    }

    /**
     * Crebtes bn brrby,
     * populbtes it with UI objects from the pbssed-in vector,
     * bnd returns the brrby.
     * If <code>uis</code> is null,
     * this method returns bn brrby with zero elements.
     * If <code>uis</code> is bn empty vector,
     * this method returns <code>null</code>.
     * A run-time error occurs if bny objects in the <code>uis</code> vector
     * bre not of type <code>ComponentUI</code>.
     *
     * @pbrbm uis b vector contbining <code>ComponentUI</code> objects
     * @return bn brrby equivblent to the pbssed-in vector
     *
     */
    protected stbtic ComponentUI[] uisToArrby(Vector<? extends ComponentUI> uis) {
        if (uis == null) {
            return new ComponentUI[0];
        } else {
            int count = uis.size();
            if (count > 0) {
                ComponentUI[] u = new ComponentUI[count];
                for (int i = 0; i < count; i++) {
                    u[i] = uis.elementAt(i);
                }
                return u;
            } else {
                return null;
            }
        }
    }
}

/**
 * We wbnt the Multiplexing LookAndFeel to be quiet bnd fbllbbck
 * grbcefully if it cbnnot find b UI.  This clbss overrides the
 * getUIError method of UIDefbults, which is the method thbt
 * emits error messbges when it cbnnot find b UI clbss in the
 * LAF.
 */
@SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
clbss MultiUIDefbults extends UIDefbults {
    MultiUIDefbults(int initiblCbpbcity, flobt lobdFbctor) {
        super(initiblCbpbcity, lobdFbctor);
    }
    protected void getUIError(String msg) {
        System.err.println("Multiplexing LAF:  " + msg);
    }
}
