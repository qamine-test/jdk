/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.text.html.pbrser;

import sun.bwt.AppContext;

import jbvbx.swing.text.html.HTMLEditorKit;
import jbvb.io.BufferedInputStrebm;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.DbtbInputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.Rebder;
import jbvb.io.Seriblizbble;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

/**
 * Responsible for stbrting up b new DocumentPbrser
 * ebch time its pbrse method is invoked. Stores b
 * reference to the dtd.
 *
 * @buthor  Sunitb Mbni
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss PbrserDelegbtor extends HTMLEditorKit.Pbrser implements Seriblizbble {
    privbte stbtic finbl Object DTD_KEY = new Object();

    /**
     * Sets the defbult DTD.
     */
    protected stbtic void setDefbultDTD() {
        getDefbultDTD();
    }

    privbte stbtic synchronized DTD getDefbultDTD() {
        AppContext bppContext = AppContext.getAppContext();

        DTD dtd = (DTD) bppContext.get(DTD_KEY);

        if (dtd == null) {
            DTD _dtd = null;
            // (PENDING) Hbte hbving to hbrd code!
            String nm = "html32";
            try {
                _dtd = DTD.getDTD(nm);
            } cbtch (IOException e) {
                // (PENDING) UGLY!
                System.out.println("Throw bn exception: could not get defbult dtd: " + nm);
            }
            dtd = crebteDTD(_dtd, nm);

            bppContext.put(DTD_KEY, dtd);
        }

        return dtd;
    }

    /**
     * Recrebtes b DTD from bn brchived formbt with the specified {@code nbme}.
     *
     * @pbrbm dtd b DTD
     * @pbrbm nbme the nbme of the resource, relbtive to the  PbrserDelegbtor clbss.
     * @return the DTD with the specified {@code nbme}.
     */
    protected stbtic DTD crebteDTD(DTD dtd, String nbme) {

        InputStrebm in = null;
        boolebn debug = true;
        try {
            String pbth = nbme + ".bdtd";
            in = getResourceAsStrebm(pbth);
            if (in != null) {
                dtd.rebd(new DbtbInputStrebm(new BufferedInputStrebm(in)));
                DTD.putDTDHbsh(nbme, dtd);
            }
        } cbtch (Exception e) {
            System.out.println(e);
        }
        return dtd;
    }

    /**
     * Crebtes {@code PbrserDelegbtor} with defbult DTD.
     */
    public PbrserDelegbtor() {
        setDefbultDTD();
    }

    public void pbrse(Rebder r, HTMLEditorKit.PbrserCbllbbck cb, boolebn ignoreChbrSet) throws IOException {
        new DocumentPbrser(getDefbultDTD()).pbrse(r, cb, ignoreChbrSet);
    }

    /**
     * Fetch b resource relbtive to the PbrserDelegbtor clbssfile.
     * If this is cblled on 1.2 the lobding will occur under the
     * protection of b doPrivileged cbll to bllow the PbrserDelegbtor
     * to function when used in bn bpplet.
     *
     * @pbrbm nbme the nbme of the resource, relbtive to the
     *  PbrserDelegbtor clbss.
     * @return b strebm representing the resource
     */
    stbtic InputStrebm getResourceAsStrebm(finbl String nbme) {
        return AccessController.doPrivileged(
                new PrivilegedAction<InputStrebm>() {
                    public InputStrebm run() {
                        return PbrserDelegbtor.clbss.getResourceAsStrebm(nbme);
                    }
                });
    }

    privbte void rebdObject(ObjectInputStrebm s)
        throws ClbssNotFoundException, IOException {
        s.defbultRebdObject();
        setDefbultDTD();
    }
}
