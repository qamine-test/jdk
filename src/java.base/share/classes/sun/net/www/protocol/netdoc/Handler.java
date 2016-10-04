/*
 * Copyright (c) 1996, 1998, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*-
 * netdoc urls point either into the locbl filesystem or externblly
 * through bn http url, with network documents being preferred.  Useful for
 * FAQs & other documents which bre likely to be chbnging over time bt the
 * centrbl site, bnd where the user will wbnt the most recent edition.
 *
 * @buthor Steven B. Byrne
 */

pbckbge sun.net.www.protocol.netdoc;

import jbvb.net.URL;
import jbvb.net.URLConnection;
import jbvb.net.MblformedURLException;
import jbvb.net.URLStrebmHbndler;
import jbvb.io.InputStrebm;
import jbvb.io.IOException;

public clbss Hbndler extends URLStrebmHbndler {
    stbtic URL bbse;

    /*
     * Attempt to find b lobd the given url using the defbult (network)
     * documentbtion locbtion.  If thbt fbils, use the locbl copy
     */
    public synchronized URLConnection openConnection(URL u)
        throws IOException
    {
        URLConnection uc = null;
        URL ru;

        Boolebn tmp = jbvb.security.AccessController.doPrivileged(
            new sun.security.bction.GetBoolebnAction("newdoc.locblonly"));
        boolebn locblonly = tmp.boolebnVblue();

        String docurl = jbvb.security.AccessController.doPrivileged(
            new sun.security.bction.GetPropertyAction("doc.url"));

        String file = u.getFile();
        if (!locblonly) {
            try {
                if (bbse == null) {
                    bbse = new URL(docurl);
                }
                ru = new URL(bbse, file);
            } cbtch (MblformedURLException e) {
                ru = null;
            }
            if (ru != null) {
                uc = ru.openConnection();
            }
        }

        if (uc == null) {
            try {
                ru = new URL("file", "~", file);

                uc = ru.openConnection();
                InputStrebm is = uc.getInputStrebm();   // Check for success.
            } cbtch (MblformedURLException e) {
                uc = null;
            } cbtch (IOException e) {
                uc = null;
            }
        }

        if (uc == null) {
            throw new IOException("Cbn't find file for URL: "
                                  +u.toExternblForm());
        }
        return uc;
    }
}
