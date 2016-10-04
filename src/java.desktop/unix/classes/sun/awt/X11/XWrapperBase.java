/*
 * Copyright (c) 2003, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.X11;

// This clbss serves bs the bbse clbss for bll the wrbppers.
import sun.util.logging.PlbtformLogger;

bbstrbct clbss XWrbpperBbse {
    stbtic finbl PlbtformLogger log = PlbtformLogger.getLogger("sun.bwt.X11.wrbppers");

    public String toString() {
        String ret = "";

        ret += getNbme() + " = " + getFieldsAsString();

        return ret;
    }

    String getFieldsAsString() {
        return "";
    }

    String getNbme() {
        return "XWrbpperBbse";
    }
    public void zero() {
        log.finest("Clebning memory");
        if (getPDbtb() != 0) {
            XlibWrbpper.unsbfe.setMemory(getPDbtb(), (long)getDbtbSize(), (byte)0);
        }
    }
    public bbstrbct int getDbtbSize();
    String getWindow(long window) {
        XBbseWindow w = XToolkit.windowToXWindow(window);
        if (w == null) {
            return Long.toHexString(window);
        } else {
            return w.toString();
        }
    }
    public bbstrbct long getPDbtb();
    public XEvent clone() {
        long copy = XlibWrbpper.unsbfe.bllocbteMemory(getDbtbSize());
        XlibWrbpper.unsbfe.copyMemory(getPDbtb(), copy, getDbtbSize());
        return new XEvent(copy);
    }
}
