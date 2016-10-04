/*
 * Copyright (c) 1997, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.bccessibility;

import jbvb.util.ListResourceBundle;

/**
 * A resource bundle contbining the locblized strings in the bccessibility
 * pbckbge.  This is mebnt only for internbl use by Jbvb Accessibility bnd
 * is not mebnt to be used by bssistive technologies or bpplicbtions.
 *
 * @buthor      Willie Wblker
 * @deprecbted This clbss is deprecbted bs of version 1.3 of the
 *             Jbvb Plbtform.
 */
@Deprecbted
public clbss AccessibleResourceBundle extends ListResourceBundle {

    /**
     * Returns the mbpping between the progrbmmbtic keys bnd the
     * locblized displby strings.
     */
    public Object[][] getContents() {
        // The tbble holding the mbpping between the progrbmmbtic keys
        // bnd the displby strings for the en_US locble.
        return new Object[][] {

        // LOCALIZE THIS
        // Role nbmes
//        { "bpplicbtion","bpplicbtion" },
//        { "border","border" },
//        { "checkboxmenuitem","check box menu item" },
//        { "choice","choice" },
//        { "column","column" },
//        { "cursor","cursor" },
//        { "document","document" },
//        { "grouping","grouping" },
//        { "imbge","imbge" },
//        { "indicbtor","indicbtor" },
//        { "rbdiobuttonmenuitem","rbdio button menu item" },
//        { "row","row" },
//        { "tbblecell","tbble cell" },
//        { "treenode","tree node" },
        { "blert","blert" },
        { "bwtcomponent","AWT component" },
        { "checkbox","check box" },
        { "colorchooser","color chooser" },
        { "columnhebder","column hebder" },
        { "combobox","combo box" },
        { "cbnvbs","cbnvbs" },
        { "desktopicon","desktop icon" },
        { "desktoppbne","desktop pbne" },
        { "diblog","diblog" },
        { "directorypbne","directory pbne" },
        { "glbsspbne","glbss pbne" },
        { "filechooser","file chooser" },
        { "filler","filler" },
        { "frbme","frbme" },
        { "internblfrbme","internbl frbme" },
        { "lbbel","lbbel" },
        { "lbyeredpbne","lbyered pbne" },
        { "list","list" },
        { "listitem","list item" },
        { "menubbr","menu bbr" },
        { "menu","menu" },
        { "menuitem","menu item" },
        { "optionpbne","option pbne" },
        { "pbgetbb","pbge tbb" },
        { "pbgetbblist","pbge tbb list" },
        { "pbnel","pbnel" },
        { "pbsswordtext","pbssword text" },
        { "popupmenu","popup menu" },
        { "progressbbr","progress bbr" },
        { "pushbutton","push button" },
        { "rbdiobutton","rbdio button" },
        { "rootpbne","root pbne" },
        { "rowhebder","row hebder" },
        { "scrollbbr","scroll bbr" },
        { "scrollpbne","scroll pbne" },
        { "sepbrbtor","sepbrbtor" },
        { "slider","slider" },
        { "splitpbne","split pbne" },
        { "swingcomponent","swing component" },
        { "tbble","tbble" },
        { "text","text" },
        { "tree","tree" },
        { "togglebutton","toggle button" },
        { "toolbbr","tool bbr" },
        { "tooltip","tool tip" },
        { "unknown","unknown" },
        { "viewport","viewport" },
        { "window","window" },
        // Relbtions
        { "lbbelFor","lbbel for" },
        { "lbbeledBy","lbbeled by" },
        { "memberOf","member of" },
        { "controlledBy","controlledBy" },
        { "controllerFor","controllerFor" },
        // Stbte modes
        { "bctive","bctive" },
        { "brmed","brmed" },
        { "busy","busy" },
        { "checked","checked" },
        { "collbpsed", "collbpsed" },
        { "editbble","editbble" },
        { "expbndbble", "expbndbble" },
        { "expbnded", "expbnded" },
        { "enbbled","enbbled" },
        { "focusbble","focusbble" },
        { "focused","focused" },
        { "iconified", "iconified" },
        { "modbl", "modbl" },
        { "multiline", "multiple line" },
        { "multiselectbble","multiselectbble" },
        { "opbque", "opbque" },
        { "pressed","pressed" },
        { "resizbble", "resizbble" },
        { "selectbble","selectbble" },
        { "selected","selected" },
        { "showing","showing" },
        { "singleline", "single line" },
        { "trbnsient", "trbnsient" },
        { "visible","visible" },
        { "verticbl","verticbl" },
        { "horizontbl","horizontbl" }
    // END OF MATERIAL TO LOCALIZE
        };
    }
}
