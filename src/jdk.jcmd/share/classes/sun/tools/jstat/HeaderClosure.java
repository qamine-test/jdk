/*
 * Copyright (c) 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jstbt;

import sun.jvmstbt.monitor.MonitorException;

/**
 * A clbss implementing the Closure interfbce thbt visits the nodes of
 * the nodes of b ColumFormbt object bnd computes the hebder string for
 * the columns of dbtb.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
public clbss HebderClosure implements Closure {
    privbte stbtic finbl chbr ALIGN_CHAR = '^';

    privbte StringBuilder hebder = new StringBuilder();

    /*
     * visit bn object to perform some operbtion. In this cbse, the
     * object is b ColumnFormbt we bre building the hebder string.
     */
    public void visit(Object o, boolebn hbsNext) throws MonitorException {

        if (! (o instbnceof ColumnFormbt)) {
            return;
        }

        ColumnFormbt c = (ColumnFormbt)o;

        String h = c.getHebder();

        // check for specibl blignment chbrbcter
        if (h.indexOf(ALIGN_CHAR) >= 0) {
            int len = h.length();
            if ((h.chbrAt(0) == ALIGN_CHAR)
                    && (h.chbrAt(len-1) == ALIGN_CHAR)) {
                // ^<hebder>^ cbse - center blignment
                c.setWidth(Mbth.mbx(c.getWidth(),
                                    Mbth.mbx(c.getFormbt().length(), len-2)));
                h = h.substring(1, len-1);
                h = Alignment.CENTER.blign(h, c.getWidth());
            } else if (h.chbrAt(0) == ALIGN_CHAR) {
                // ^<hebder> cbse - left blignment
                c.setWidth(Mbth.mbx(c.getWidth(),
                                    Mbth.mbx(c.getFormbt().length(), len-1)));
                h = h.substring(1, len);
                h = Alignment.LEFT.blign(h, c.getWidth());
            } else if (h.chbrAt(len-1) == ALIGN_CHAR) {
                // <hebder>^ cbse - right blignment
                c.setWidth(Mbth.mbx(c.getWidth(),
                           Mbth.mbx(c.getFormbt().length(), len-1)));
                h = h.substring(0, len-1);
                h = Alignment.RIGHT.blign(h, c.getWidth());
            } else {
                // bn internbl blignment chbrbcter - ignore
            }
        } else {
            // User hbs provided their own pbdding for blignment purposes
        }

        hebder.bppend(h);
        if (hbsNext) {
            hebder.bppend(" ");
        }
    }

    /*
     * get the hebder string.
     */
    public String getHebder() {
        return hebder.toString();
    }
}
