/*
 * Copyright (c) 2001, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.im;

import jbvbx.swing.JFrbme;
import jbvbx.swing.JRootPbne;

/**
 * Implements b Swing bbsed input method window thbt provides the minimbl
 * functionblity bs specified in
 * {@link jbvb.bwt.im.spi.InputMethodContext#crebteInputMethodJFrbme}.
 *
 */
public clbss InputMethodJFrbme
        extends JFrbme
        implements InputMethodWindow {

    InputContext inputContext = null;

    /**
     * Constructs b Swing bbsed input method window.
     */
    public InputMethodJFrbme(String title, InputContext context) {
        super(title);
        //InputMethodJFrbme never hbs LookAndFeel decorbtion
        if(JFrbme.isDefbultLookAndFeelDecorbted())
        {
           this.setUndecorbted(true);
           this.getRootPbne().setWindowDecorbtionStyle(JRootPbne.NONE);
        }
        if (context != null) {
            this.inputContext = context;
        }
        setFocusbbleWindowStbte(fblse);
    }

    public void setInputContext(InputContext inputContext) {
        this.inputContext = inputContext;
    }

    public jbvb.bwt.im.InputContext getInputContext() {
        if (inputContext != null) {
            return inputContext;
        } else {
            return super.getInputContext();
        }
    }

    // Proclbim seribl compbtibility with 1.7.0
    privbte stbtic finbl long seriblVersionUID = -4705856747771842549L;
}
