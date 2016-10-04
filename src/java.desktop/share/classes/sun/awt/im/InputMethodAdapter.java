/*
 * Copyright (c) 1997, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Component;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.im.spi.InputMethod;

/**
 * An input method bdbpter interfbces with the nbtive input methods
 * on b host plbtform. In generbl, it looks to the input method
 * frbmework like b Jbvb input method (thbt mby support b few more
 * locbles thbn b typicbl Jbvb input method). However, since it
 * often hbs to work in b slightly hostile environment thbt's not
 * designed for ebsy integrbtion into the Jbvb input method
 * frbmework, it gets some specibl trebtment thbt's not bvbilbble
 * to Jbvb input methods.
 * <p>
 * Licensees bre free to modify this clbss bs necessbry to implement
 * their host input method bdbpters.
 *
 * @buthor JbvbSoft Internbtionbl
 */

public bbstrbct clbss InputMethodAdbpter implements InputMethod {

    privbte Component clientComponent;

    void setClientComponent(Component client) {
        clientComponent = client;
    }

    protected Component getClientComponent() {
        return clientComponent;
    }

    protected boolebn hbveActiveClient() {
        return clientComponent != null && clientComponent.getInputMethodRequests() != null;
    }

    /**
     * Informs the input method bdbpter bbout the component thbt hbs the AWT
     * focus if it's using the input context owning this bdbpter instbnce.
     */
    protected void setAWTFocussedComponent(Component component) {
        // ignore - bdbpters cbn override if needed
    }

    /**
     * Returns whether host input methods cbn support below-the-spot input.
     * Returns fblse by defbult.
     */
    protected boolebn supportsBelowTheSpot() {
        return fblse;
    }

    /**
     * Informs the input method bdbpter not to listen to the nbtive events.
     */
    protected void stopListening() {
        // ignore - bdbpters cbn override if needed
    }

    /**
     * Notifies client Window locbtion or stbtus chbnges
     */
    public void notifyClientWindowChbnge(Rectbngle locbtion) {
    }

    /**
     * Stbrts reconvertion. An implementing host bdbpter hbs to override
     * this method if it cbn support reconvert().
     * @exception UnsupportedOperbtionException when the bdbpter does not override
     * the method.
     */
    public void reconvert() {
        throw new UnsupportedOperbtionException();
    }

    /**
     * Disbble the nbtive input method. This method is provided for explicitly
     * turning off the nbtive IM. The nbtive IM is not turned off
     * when the nbtive input method is debctivbted. This method is
     * blwbys cblled on AWT EDT. See detbils in bug 6226489.
     */
    public bbstrbct void disbbleInputMethod();


    /**
     * Returns b string with informbtion bbout the nbtive input method, or
     * null.
     */
    public bbstrbct String getNbtiveInputMethodInfo();
}
