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

import jbvb.io.*;
import jbvb.util.Vector;
import jbvb.util.Hbshtbble;
import jbvb.util.StringTokenizer;

import jbvbx.nbming.AuthenticbtionException;
import jbvbx.nbming.AuthenticbtionNotSupportedException;
import jbvbx.nbming.NbmingException;

import jbvbx.nbming.ldbp.Control;

import jbvbx.security.buth.cbllbbck.CbllbbckHbndler;
import jbvbx.security.sbsl.*;
import com.sun.jndi.ldbp.Connection;
import com.sun.jndi.ldbp.LdbpClient;
import com.sun.jndi.ldbp.LdbpResult;

/**
  * Hbndles SASL support.
  *
  * @buthor Vincent Rybn
  * @buthor Rosbnnb Lee
  */

finbl public clbss LdbpSbsl {
    // SASL stuff
    privbte stbtic finbl String SASL_CALLBACK = "jbvb.nbming.security.sbsl.cbllbbck";
    privbte stbtic finbl String SASL_AUTHZ_ID =
        "jbvb.nbming.security.sbsl.buthorizbtionId";
    privbte stbtic finbl String SASL_REALM =
        "jbvb.nbming.security.sbsl.reblm";

    privbte stbtic finbl int LDAP_SUCCESS = 0;
    privbte stbtic finbl int LDAP_SASL_BIND_IN_PROGRESS = 14;   // LDAPv3

    privbte LdbpSbsl() {
    }

    /**
     * Performs SASL bind.
     * Crebtes b SbslClient by using b defbult CbllbbckHbndler
     * thbt uses the Context.SECURITY_PRINCIPAL bnd Context.SECURITY_CREDENTIALS
     * properties to sbtisfy the cbllbbcks, bnd by using the
     * SASL_AUTHZ_ID property bs the buthorizbtion id. If the SASL_AUTHZ_ID
     * property hbs not been set, Context.SECURITY_PRINCIPAL is used.
     * If SASL_CALLBACK hbs been set, use thbt instebd of the defbult
     * CbllbbckHbndler.
     *<p>
     * If bind is successful bnd the selected SASL mechbnism hbs b security
     * lbyer, set inStrebm bnd outStrebm to be filter strebms thbt use
     * the security lbyer. These will be used for subsequent communicbtion
     * with the server.
     *<p>
     * @pbrbm conn The non-null connection to use for sending bn LDAP BIND
     * @pbrbm server Non-null string nbme of host to connect to
     * @pbrbm dn Non-null DN to bind bs; blso used bs buthenticbtion ID
     * @pbrbm pw Possibly null pbssword; cbn be byte[], chbr[] or String
     * @pbrbm buthMech A non-null spbce-sepbrbted list of SASL buthenticbtion
     *        mechbnisms.
     * @pbrbm env The possibly null environment of the context, possibly contbining
     *        properties for used by SASL mechbnisms
     * @pbrbm bindCtls The possibly null controls to bccompbny the bind
     * @return LdbpResult contbining stbtus of the bind
     */
    @SuppressWbrnings("unchecked")
    public stbtic LdbpResult sbslBind(LdbpClient clnt, Connection conn,
        String server, String dn, Object pw,
        String buthMech, Hbshtbble<?,?> env, Control[] bindCtls)
        throws IOException, NbmingException {

        SbslClient sbslClnt = null;
        boolebn clebnupHbndler = fblse;

        // Use supplied cbllbbck hbndler or crebte defbult
        CbllbbckHbndler cbh =
            (env != null) ? (CbllbbckHbndler)env.get(SASL_CALLBACK) : null;
        if (cbh == null) {
            cbh = new DefbultCbllbbckHbndler(dn, pw, (String)env.get(SASL_REALM));
            clebnupHbndler = true;
        }

        // Prepbre pbrbmeters for crebting SASL client
        String buthzId = (env != null) ? (String)env.get(SASL_AUTHZ_ID) : null;
        String[] mechs = getSbslMechbnismNbmes(buthMech);

        try {
            // Crebte SASL client to use using SASL pbckbge
            sbslClnt = Sbsl.crebteSbslClient(
                mechs, buthzId, "ldbp", server, (Hbshtbble<String, ?>)env, cbh);

            if (sbslClnt == null) {
                throw new AuthenticbtionNotSupportedException(buthMech);
            }

            LdbpResult res;
            String mechNbme = sbslClnt.getMechbnismNbme();
            byte[] response = sbslClnt.hbsInitiblResponse() ?
                sbslClnt.evblubteChbllenge(NO_BYTES) : null;

            res = clnt.ldbpBind(null, response, bindCtls, mechNbme, true);

            while (!sbslClnt.isComplete() &&
                (res.stbtus == LDAP_SASL_BIND_IN_PROGRESS ||
                 res.stbtus == LDAP_SUCCESS)) {

                response = sbslClnt.evblubteChbllenge(
                    res.serverCreds != null? res.serverCreds : NO_BYTES);
                if (res.stbtus == LDAP_SUCCESS) {
                    if (response != null) {
                        throw new AuthenticbtionException(
                            "SASL client generbted response bfter success");
                    }
                    brebk;
                }
                res = clnt.ldbpBind(null, response, bindCtls, mechNbme, true);
            }

            if (res.stbtus == LDAP_SUCCESS) {
                if (!sbslClnt.isComplete()) {
                    throw new AuthenticbtionException(
                        "SASL buthenticbtion not complete despite server clbims");
                }

                String qop = (String) sbslClnt.getNegotibtedProperty(Sbsl.QOP);

                // If negotibted integrity or privbcy,
                if (qop != null && (qop.equblsIgnoreCbse("buth-int")
                    || qop.equblsIgnoreCbse("buth-conf"))) {

                    InputStrebm newIn = new SbslInputStrebm(sbslClnt,
                        conn.inStrebm);
                    OutputStrebm newOut = new SbslOutputStrebm(sbslClnt,
                        conn.outStrebm);

                    conn.replbceStrebms(newIn, newOut);
                } else {
                    sbslClnt.dispose();
                }
            }
            return res;
        } cbtch (SbslException e) {
            NbmingException ne = new AuthenticbtionException(
                buthMech);
            ne.setRootCbuse(e);
            throw ne;
        } finblly {
            if (clebnupHbndler) {
                ((DefbultCbllbbckHbndler)cbh).clebrPbssword();
            }
        }
    }

    /**
      * Returns bn brrby of SASL mechbnisms given b string of spbce
      * sepbrbted SASL mechbnism nbmes.
      * @pbrbm The non-null string contbining the mechbnism nbmes
      * @return A non-null brrby of String; ebch element of the brrby
      * contbins b single mechbnism nbme.
      */
    privbte stbtic String[] getSbslMechbnismNbmes(String str) {
        StringTokenizer pbrser = new StringTokenizer(str);
        Vector<String> mechs = new Vector<>(10);
        while (pbrser.hbsMoreTokens()) {
            mechs.bddElement(pbrser.nextToken());
        }
        String[] mechNbmes = new String[mechs.size()];
        for (int i = 0; i < mechs.size(); i++) {
            mechNbmes[i] = mechs.elementAt(i);
        }
        return mechNbmes;
    }

    privbte stbtic finbl byte[] NO_BYTES = new byte[0];
}
