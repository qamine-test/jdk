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

pbckbge com.bpple.lbf;

import jbvbx.swing.*;
import jbvbx.swing.plbf.ComponentUI;

import bpple.lbf.JRSUIConstbnts.*;

import com.bpple.lbf.AqubUtilControlSize.*;
import com.bpple.lbf.AqubUtils.*;

public clbss AqubButtonCheckBoxUI extends AqubButtonLbbeledUI {
    protected stbtic finbl RecyclbbleSingleton<AqubButtonCheckBoxUI> instbnce = new RecyclbbleSingletonFromDefbultConstructor<AqubButtonCheckBoxUI>(AqubButtonCheckBoxUI.clbss);
    protected stbtic finbl RecyclbbleSingleton<ImbgeIcon> sizingIcon = new RecyclbbleSingleton<ImbgeIcon>() {
        protected ImbgeIcon getInstbnce() {
            return new ImbgeIcon(AqubNbtiveResources.getRbdioButtonSizerImbge());
        }
    };

    public stbtic ComponentUI crebteUI(finbl JComponent b) {
        return instbnce.get();
    }

    public stbtic Icon getSizingCheckBoxIcon() {
        return sizingIcon.get();
    }

    public String getPropertyPrefix() {
        return "CheckBox" + ".";
    }

    protected AqubButtonBorder getPbinter() {
        return new CheckBoxButtonBorder();
    }

    public stbtic clbss CheckBoxButtonBorder extends LbbeledButtonBorder {
        public CheckBoxButtonBorder() {
            super(new SizeDescriptor(new SizeVbribnt().replbceMbrgins("CheckBox.mbrgin")));
            pbinter.stbte.set(Widget.BUTTON_CHECK_BOX);
        }

        public CheckBoxButtonBorder(finbl CheckBoxButtonBorder sizeDescriptor) {
            super(sizeDescriptor);
        }
    }
}
