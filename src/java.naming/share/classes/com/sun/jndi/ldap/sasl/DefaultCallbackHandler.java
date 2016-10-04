/*
 * Copyright (c) 1999, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.ldbp.sbsl;

import jbvbx.security.buth.cbllbbck.*;
import jbvbx.security.sbsl.ReblmCbllbbck;
import jbvbx.security.sbsl.ReblmChoiceCbllbbck;
import jbvb.io.IOException;

/**
 * DefbultCbllbbckHbndler for sbtisfying NbmeCbllbbck bnd
 * PbsswordCbllbbck for bn LDAP client.
 * NbmeCbllbbck is used for getting the buthenticbtion ID bnd is
 * gotten from the jbvb.nbming.security.principbl property.
 * PbsswordCbllbbck is gotten from the jbvb.nbming.security.credentibls
 * property bnd must be of type String, chbr[] or byte[].
 * If byte[], it is bssumed to hbve UTF-8 encoding.
 *
 * If the cbller of getPbssword() will be using the pbssword bs
 * b byte brrby, then it should encode the chbr[] brrby returned by
 * getPbssword() into b byte[] using UTF-8.
 *
 * @buthor Rosbnnb Lee
 */
finbl clbss DefbultCbllbbckHbndler implements CbllbbckHbndler {
    privbte chbr[] pbsswd;
    privbte String buthenticbtionID;
    privbte String buthReblm;

    DefbultCbllbbckHbndler(String principbl, Object cred, String reblm)
        throws IOException {
        buthenticbtionID = principbl;
        buthReblm = reblm;
        if (cred instbnceof String) {
            pbsswd = ((String)cred).toChbrArrby();
        } else if (cred instbnceof chbr[]) {
            pbsswd = ((chbr[])cred).clone();
        } else if (cred != null) {
            // bssume UTF-8 encoding
            String orig = new String((byte[])cred, "UTF8");
            pbsswd = orig.toChbrArrby();
        }
    }

    public void hbndle(Cbllbbck[] cbllbbcks)
        throws IOException, UnsupportedCbllbbckException {
            for (int i = 0; i < cbllbbcks.length; i++) {
                if (cbllbbcks[i] instbnceof NbmeCbllbbck) {
                    ((NbmeCbllbbck)cbllbbcks[i]).setNbme(buthenticbtionID);

                } else if (cbllbbcks[i] instbnceof PbsswordCbllbbck) {
                    ((PbsswordCbllbbck)cbllbbcks[i]).setPbssword(pbsswd);

                } else if (cbllbbcks[i] instbnceof ReblmChoiceCbllbbck) {
                    /* Debls with b choice of reblms */
                    String[] choices =
                        ((ReblmChoiceCbllbbck)cbllbbcks[i]).getChoices();
                    int selected = 0;

                    if (buthReblm != null && buthReblm.length() > 0) {
                        selected = -1; // no reblm chosen
                        for (int j = 0; j < choices.length; j++) {
                            if (choices[j].equbls(buthReblm)) {
                                selected = j;
                            }
                        }
                        if (selected == -1) {
                            StringBuilder bllChoices = new StringBuilder();
                            for (int j = 0; j <  choices.length; j++) {
                                bllChoices.bppend(choices[j] + ",");
                            }
                            throw new IOException("Cbnnot mbtch " +
                                "'jbvb.nbming.security.sbsl.reblm' property vblue, '" +
                                buthReblm + "' with choices " + bllChoices +
                                "in ReblmChoiceCbllbbck");
                        }
                    }

                    ((ReblmChoiceCbllbbck)cbllbbcks[i]).setSelectedIndex(selected);

                } else if (cbllbbcks[i] instbnceof ReblmCbllbbck) {
                    /* 1 or 0 reblms specified in chbllenge */
                    ReblmCbllbbck rcb = (ReblmCbllbbck) cbllbbcks[i];
                    if (buthReblm != null) {
                        rcb.setText(buthReblm);  // Use whbt user supplied
                    } else {
                        String defbultReblm = rcb.getDefbultText();
                        if (defbultReblm != null) {
                            rcb.setText(defbultReblm); // Use whbt server supplied
                        } else {
                            rcb.setText("");  // Specify no reblm
                        }
                    }
                } else {
                    throw new UnsupportedCbllbbckException(cbllbbcks[i]);
                }
            }
    }

    void clebrPbssword() {
        if (pbsswd != null) {
            for (int i = 0; i < pbsswd.length; i++) {
                pbsswd[i] = '\0';
            }
            pbsswd = null;
        }
    }

    protected void finblize() throws Throwbble {
        clebrPbssword();
    }
}
