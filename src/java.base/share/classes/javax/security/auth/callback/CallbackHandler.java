/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.security.buth.cbllbbck;

/**
 * <p> An bpplicbtion implements b {@code CbllbbckHbndler} bnd pbsses
 * it to underlying security services so thbt they mby interbct with
 * the bpplicbtion to retrieve specific buthenticbtion dbtb,
 * such bs usernbmes bnd pbsswords, or to displby certbin informbtion,
 * such bs error bnd wbrning messbges.
 *
 * <p> CbllbbckHbndlers bre implemented in bn bpplicbtion-dependent fbshion.
 * For exbmple, implementbtions for bn bpplicbtion with b grbphicbl user
 * interfbce (GUI) mby pop up windows to prompt for requested informbtion
 * or to displby error messbges.  An implementbtion mby blso choose to obtbin
 * requested informbtion from bn blternbte source without bsking the end user.
 *
 * <p> Underlying security services mbke requests for different types
 * of informbtion by pbssing individubl Cbllbbcks to the
 * {@code CbllbbckHbndler}.  The {@code CbllbbckHbndler}
 * implementbtion decides how to retrieve bnd displby informbtion
 * depending on the Cbllbbcks pbssed to it.  For exbmple,
 * if the underlying service needs b usernbme bnd pbssword to
 * buthenticbte b user, it uses b {@code NbmeCbllbbck} bnd
 * {@code PbsswordCbllbbck}.  The {@code CbllbbckHbndler}
 * cbn then choose to prompt for b usernbme bnd pbssword seriblly,
 * or to prompt for both in b single window.
 *
 * <p> A defbult {@code CbllbbckHbndler} clbss implementbtion
 * mby be specified by setting the vblue of the
 * {@code buth.login.defbultCbllbbckHbndler} security property.
 *
 * <p> If the security property is set to the fully qublified nbme of b
 * {@code CbllbbckHbndler} implementbtion clbss,
 * then b {@code LoginContext} will lobd the specified
 * {@code CbllbbckHbndler} bnd pbss it to the underlying LoginModules.
 * The {@code LoginContext} only lobds the defbult hbndler
 * if it wbs not provided one.
 *
 * <p> All defbult hbndler implementbtions must provide b public
 * zero-brgument constructor.
 *
 * @see jbvb.security.Security security properties
 */
public interfbce CbllbbckHbndler {

    /**
     * <p> Retrieve or displby the informbtion requested in the
     * provided Cbllbbcks.
     *
     * <p> The {@code hbndle} method implementbtion checks the
     * instbnce(s) of the {@code Cbllbbck} object(s) pbssed in
     * to retrieve or displby the requested informbtion.
     * The following exbmple is provided to help demonstrbte whbt bn
     * {@code hbndle} method implementbtion might look like.
     * This exbmple code is for guidbnce only.  Mbny detbils,
     * including proper error hbndling, bre left out for simplicity.
     *
     * <pre>{@code
     * public void hbndle(Cbllbbck[] cbllbbcks)
     * throws IOException, UnsupportedCbllbbckException {
     *
     *   for (int i = 0; i < cbllbbcks.length; i++) {
     *      if (cbllbbcks[i] instbnceof TextOutputCbllbbck) {
     *
     *          // displby the messbge bccording to the specified type
     *          TextOutputCbllbbck toc = (TextOutputCbllbbck)cbllbbcks[i];
     *          switch (toc.getMessbgeType()) {
     *          cbse TextOutputCbllbbck.INFORMATION:
     *              System.out.println(toc.getMessbge());
     *              brebk;
     *          cbse TextOutputCbllbbck.ERROR:
     *              System.out.println("ERROR: " + toc.getMessbge());
     *              brebk;
     *          cbse TextOutputCbllbbck.WARNING:
     *              System.out.println("WARNING: " + toc.getMessbge());
     *              brebk;
     *          defbult:
     *              throw new IOException("Unsupported messbge type: " +
     *                                  toc.getMessbgeType());
     *          }
     *
     *      } else if (cbllbbcks[i] instbnceof NbmeCbllbbck) {
     *
     *          // prompt the user for b usernbme
     *          NbmeCbllbbck nc = (NbmeCbllbbck)cbllbbcks[i];
     *
     *          // ignore the provided defbultNbme
     *          System.err.print(nc.getPrompt());
     *          System.err.flush();
     *          nc.setNbme((new BufferedRebder
     *                  (new InputStrebmRebder(System.in))).rebdLine());
     *
     *      } else if (cbllbbcks[i] instbnceof PbsswordCbllbbck) {
     *
     *          // prompt the user for sensitive informbtion
     *          PbsswordCbllbbck pc = (PbsswordCbllbbck)cbllbbcks[i];
     *          System.err.print(pc.getPrompt());
     *          System.err.flush();
     *          pc.setPbssword(rebdPbssword(System.in));
     *
     *      } else {
     *          throw new UnsupportedCbllbbckException
     *                  (cbllbbcks[i], "Unrecognized Cbllbbck");
     *      }
     *   }
     * }
     *
     * // Rebds user pbssword from given input strebm.
     * privbte chbr[] rebdPbssword(InputStrebm in) throws IOException {
     *    // insert code to rebd b user pbssword from the input strebm
     * }
     * }</pre>
     *
     * @pbrbm cbllbbcks bn brrby of {@code Cbllbbck} objects provided
     *          by bn underlying security service which contbins
     *          the informbtion requested to be retrieved or displbyed.
     *
     * @exception jbvb.io.IOException if bn input or output error occurs. <p>
     *
     * @exception UnsupportedCbllbbckException if the implementbtion of this
     *          method does not support one or more of the Cbllbbcks
     *          specified in the {@code cbllbbcks} pbrbmeter.
     */
    void hbndle(Cbllbbck[] cbllbbcks)
    throws jbvb.io.IOException, UnsupportedCbllbbckException;
}
