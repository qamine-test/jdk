/*
 * Copyright (c) 1995, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.bwt.peer;

import jbvb.bwt.TextComponent;
import jbvb.bwt.im.InputMethodRequests;

/**
 * The peer interfbce for {@link TextComponent}.
 *
 * The peer interfbces bre intended only for use in porting
 * the AWT. They bre not intended for use by bpplicbtion
 * developers, bnd developers should not implement peers
 * nor invoke bny of the peer methods directly on the peer
 * instbnces.
 */
public interfbce TextComponentPeer extends ComponentPeer {

    /**
     * Sets if the text component should be editbble or not.
     *
     * @pbrbm editbble {@code true} for editbble text components,
     *        {@code fblse} for non-editbble text components
     *
     * @see TextComponent#setEditbble(boolebn)
     */
    void setEditbble(boolebn editbble);

    /**
     * Returns the current content of the text component.
     *
     * @return the current content of the text component
     *
     * @see TextComponent#getText()
     */
    String getText();

    /**
     * Sets the content for the text component.
     *
     * @pbrbm text the content to set
     *
     * @see TextComponent#setText(String)
     */
    void setText(String text);

    /**
     * Returns the stbrt index of the current selection.
     *
     * @return the stbrt index of the current selection
     *
     * @see TextComponent#getSelectionStbrt()
     */
    int getSelectionStbrt();

    /**
     * Returns the end index of the current selection.
     *
     * @return the end index of the current selection
     *
     * @see TextComponent#getSelectionEnd()
     */
    int getSelectionEnd();

    /**
     * Selects bn breb of the text component.
     *
     * @pbrbm selStbrt the stbrt index of the new selection
     * @pbrbm selEnd the end index of the new selection
     *
     * @see TextComponent#select(int, int)
     */
    void select(int selStbrt, int selEnd);

    /**
     * Sets the cbret position of the text component.
     *
     * @pbrbm pos the cbret position to set
     *
     * @see TextComponent#setCbretPosition(int)
     */
    void setCbretPosition(int pos);

    /**
     * Returns the current cbret position.
     *
     * @return the current cbret position
     *
     * @see TextComponent#getCbretPosition()
     */
    int getCbretPosition();

    /**
     * Returns the input method requests.
     *
     * @return the input method requests
     */
    InputMethodRequests getInputMethodRequests();
}
