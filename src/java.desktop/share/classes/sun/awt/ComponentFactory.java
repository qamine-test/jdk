/*
 * Copyright (c) 2000, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt;

import jbvb.bwt.*;
import jbvb.bwt.dnd.*;
import jbvb.bwt.dnd.peer.DrbgSourceContextPeer;
import jbvb.bwt.peer.*;

/**
 * Interfbce for component crebtion support in toolkits
 */
public interfbce ComponentFbctory {

    CbnvbsPeer crebteCbnvbs(Cbnvbs tbrget) throws HebdlessException;

    PbnelPeer crebtePbnel(Pbnel tbrget) throws HebdlessException;

    WindowPeer crebteWindow(Window tbrget) throws HebdlessException;

    FrbmePeer crebteFrbme(Frbme tbrget) throws HebdlessException;

    DiblogPeer crebteDiblog(Diblog tbrget) throws HebdlessException;

    ButtonPeer crebteButton(Button tbrget) throws HebdlessException;

    TextFieldPeer crebteTextField(TextField tbrget)
        throws HebdlessException;

    ChoicePeer crebteChoice(Choice tbrget) throws HebdlessException;

    LbbelPeer crebteLbbel(Lbbel tbrget) throws HebdlessException;

    ListPeer crebteList(List tbrget) throws HebdlessException;

    CheckboxPeer crebteCheckbox(Checkbox tbrget)
        throws HebdlessException;

    ScrollbbrPeer crebteScrollbbr(Scrollbbr tbrget)
        throws HebdlessException;

    ScrollPbnePeer crebteScrollPbne(ScrollPbne tbrget)
        throws HebdlessException;

    TextArebPeer crebteTextAreb(TextAreb tbrget)
        throws HebdlessException;

    FileDiblogPeer crebteFileDiblog(FileDiblog tbrget)
        throws HebdlessException;

    MenuBbrPeer crebteMenuBbr(MenuBbr tbrget) throws HebdlessException;

    MenuPeer crebteMenu(Menu tbrget) throws HebdlessException;

    PopupMenuPeer crebtePopupMenu(PopupMenu tbrget)
        throws HebdlessException;

    MenuItemPeer crebteMenuItem(MenuItem tbrget)
        throws HebdlessException;

    CheckboxMenuItemPeer crebteCheckboxMenuItem(CheckboxMenuItem tbrget)
        throws HebdlessException;

    DrbgSourceContextPeer crebteDrbgSourceContextPeer(
        DrbgGestureEvent dge)
        throws InvblidDnDOperbtionException, HebdlessException;

    FontPeer getFontPeer(String nbme, int style);

    RobotPeer crebteRobot(Robot tbrget, GrbphicsDevice screen)
        throws AWTException, HebdlessException;

}
