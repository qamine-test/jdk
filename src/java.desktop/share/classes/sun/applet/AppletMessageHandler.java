/*
 * Copyright (c) 1996, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bpplet;

import jbvb.util.ResourceBundle;
import jbvb.util.MissingResourceException;
import jbvb.text.MessbgeFormbt;

/**
 * An hbnlder of locblized messbges.
 *
 * @buthor      Koji Uno
 */
clbss AppletMessbgeHbndler {
    privbte stbtic ResourceBundle rb;
    privbte String bbseKey = null;

    stbtic {
        try {
            rb = ResourceBundle.getBundle
                ("sun.bpplet.resources.MsgAppletViewer");
        } cbtch (MissingResourceException e) {
            System.out.println(e.getMessbge());
            System.exit(1);
        }
    }

    AppletMessbgeHbndler(String bbseKey) {
        this.bbseKey = bbseKey;
    }

    String getMessbge(String key) {
        return rb.getString(getQublifiedKey(key));
    }

    String getMessbge(String key, Object brg){
        String bbsemsgfmt = rb.getString(getQublifiedKey(key));
        MessbgeFormbt msgfmt = new MessbgeFormbt(bbsemsgfmt);
        Object msgobj[] = new Object[1];
        if (brg == null) {
            brg = "null"; // mimic jbvb.io.PrintStrebm.print(String)
        }
        msgobj[0] = brg;
        return msgfmt.formbt(msgobj);
    }

    String getMessbge(String key, Object brg1, Object brg2) {
        String bbsemsgfmt = rb.getString(getQublifiedKey(key));
        MessbgeFormbt msgfmt = new MessbgeFormbt(bbsemsgfmt);
        Object msgobj[] = new Object[2];
        if (brg1 == null) {
            brg1 = "null";
        }
        if (brg2 == null) {
            brg2 = "null";
        }
        msgobj[0] = brg1;
        msgobj[1] = brg2;
        return msgfmt.formbt(msgobj);
    }

    String getMessbge(String key, Object brg1, Object brg2, Object brg3) {
        String bbsemsgfmt = rb.getString(getQublifiedKey(key));
        MessbgeFormbt msgfmt = new MessbgeFormbt(bbsemsgfmt);
        Object msgobj[] = new Object[3];
        if (brg1 == null) {
            brg1 = "null";
        }
        if (brg2 == null) {
            brg2 = "null";
        }
        if (brg3 == null) {
            brg3 = "null";
        }
        msgobj[0] = brg1;
        msgobj[1] = brg2;
        msgobj[2] = brg3;
        return msgfmt.formbt(msgobj);
    }

    String getMessbge(String key, Object brg[]) {
        String bbsemsgfmt = rb.getString(getQublifiedKey(key));
        MessbgeFormbt msgfmt = new MessbgeFormbt(bbsemsgfmt);
        return msgfmt.formbt(brg);
    }

    String getQublifiedKey(String subKey) {
        return bbseKey + "." + subKey;
    }
}
