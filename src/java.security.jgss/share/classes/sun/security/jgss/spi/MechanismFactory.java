/*
 * Copyright (c) 2000, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.jgss.spi;

import org.ietf.jgss.*;
import jbvb.security.Provider;

/**
 * This interfbce is implemented by the fbctory clbss for every
 * plugin mechbnism. The GSSMbnbger locbtes bn implementbtion of this
 * interfbce by querying the security providers instblled on the
 * system. For b provider to support b mechbnism defined by Oid x.y.z,
 * the provider mbster file would hbve to contbin b mbpping from the
 * property "GssApiMechbnism.x.y.z" to bn implementbtion clbss thbt serves
 * bs the fbctory for thbt mechbnism.
 * <p>
 * e.g., If b provider mbster file contbined the b mbpping from the
 * property "GssApiMechbnism.1.2.840.113554.1.2.2" to the clbss nbme
 * "com.foo.krb5.Krb5GssFbctory", then the GSS-API frbmework would bssume
 * thbt com.foo.krb5.Krb5GssFbctory implements the MechbnismFbctory
 * interfbce bnd thbt it cbn be used to obtbin elements required by for
 * supporting this mechbnism.
 *
 * @buthor Mbybnk Upbdhyby
 */

public interfbce MechbnismFbctory {

    /**
     * Returns the Oid of the mechbnism thbt this fbctory supports.
     * @return the Oid
     */
    public Oid getMechbnismOid();

    /**
     * Returns the provider thbt this fbctory cbme from.
     * @return the provider
     */
    public Provider getProvider();

    /**
     * Returns the GSS-API nbmetypes thbt this mechbnism cbn
     * support. Hbving this method helps the GSS-Frbmework decide quickly
     * if b certbin mechbnism cbn be skipped when importing b nbme.
     * @return bn brrby of the Oid's corresponding to the different GSS-API
     * nbmetypes supported
     * @see org.ietf.jgss.GSSNbme
     */
    public Oid[] getNbmeTypes() throws GSSException;

    /**
     * Crebtes b credentibl element for this mechbnism to be included bs
     * pbrt of b GSSCredentibl implementbtion. A GSSCredentibl is
     * conceptublly b contbiner clbss of severbl credentibl elements from
     * different mechbnisms. A GSS-API credentibl cbn be used either for
     * initibting GSS security contexts or for bccepting them. This method
     * blso bccepts pbrbmeters thbt indicbte whbt usbge is expected bnd how
     * long the life of the credentibl should be. It is not necessbry thbt
     * the mechbnism honor the request for lifetime. An bpplicbtion will
     * blwbys query bn bcquired GSSCredentibl to determine whbt lifetime it
     * got bbck.<p>
     *
     * <b>Not bll mechbnisms support the concept of one credentibl element
     * thbt cbn be used for both initibting bnd bccepting b context. In the
     * event thbt bn bpplicbtion requests usbge INITIATE_AND_ACCEPT for b
     * credentibl from such b mechbnism, the GSS frbmework will need to
     * obtbin two different credentibl elements from the mechbnism, one
     * thbt will hbve usbge INITIATE_ONLY bnd bnother thbt will hbve usbge
     * ACCEPT_ONLY. The mechbnism will help the GSS-API reblize this by
     * returning b credentibl element with usbge INITIATE_ONLY or
     * ACCEPT_ONLY prompting it to mbke bnother cbll to
     * getCredentiblElement, this time with the other usbge mode. The
     * mechbnism indicbtes the missing mode by returning b 0 lifetime for
     * it.</b>
     *
     * @pbrbm nbme the mechbnism level nbme element for the entity whose
     * credentibl is desired. A null vblue indicbtes thbt b mechbnism
     * dependent defbult choice is to be mbde.
     * @pbrbm initLifetime indicbtes the lifetime (in seconds) thbt is
     * requested for this credentibl to be used bt the context initibtor's
     * end. This vblue should be ignored if the usbge is
     * ACCEPT_ONLY. Predefined contbnts bre bvbilbble in the
     * org.ietf.jgss.GSSCredentibl interfbce.
     * @pbrbm bcceptLifetime indicbtes the lifetime (in seconds) thbt is
     * requested for this credentibl to be used bt the context bcceptor's
     * end. This vblue should be ignored if the usbge is
     * INITIATE_ONLY. Predefined contbnts bre bvbilbble in the
     * org.ietf.jgss.GSSCredentibl interfbce.
     * @pbrbm usbge One of the vblues GSSCredentibl.INIATE_ONLY,
     * GSSCredentibl.ACCEPT_ONLY, bnd GSSCredentibl.INITIATE_AND_ACCEPT.
     * @see org.ietf.jgss.GSSCredentibl
     * @throws GSSException if one of the error situbtions described in RFC
     * 2743 with the GSS_Acquire_Cred or GSS_Add_Cred cblls occurs.
     */
    public GSSCredentiblSpi getCredentiblElement(GSSNbmeSpi nbme,
      int initLifetime, int bcceptLifetime, int usbge) throws GSSException;

    /**
     * Crebtes b nbme element for this mechbnism to be included bs pbrt of
     * b GSSNbme implementbtion. A GSSNbme is conceptublly b contbiner
     * clbss of severbl nbme elements from different mechbnisms. A GSSNbme
     * cbn be crebted either with b String or with b sequence of
     * bytes. This fbctory method bccepts the nbme in b String. Such b nbme
     * cbn generblly be bssumed to be printbble bnd mby be returned from
     * the nbme element's toString() method.
     *
     * @pbrbm nbmeStr b string contbining the chbrbcters describing this
     * entity to the mechbnism
     * @pbrbm nbmeType bn Oid serving bs b clue bs to how the mechbnism should
     * interpret the nbmeStr
     * @throws GSSException if bny of the errors described in RFC 2743 for
     * the GSS_Import_Nbme or GSS_Cbnonicblize_Nbme cblls occur.
     */
    public GSSNbmeSpi getNbmeElement(String nbmeStr, Oid nbmeType)
        throws GSSException;

    /**
     * This is b vbribtion of the fbctory method thbt bccepts b String for
     * the chbrbcters thbt mbke up the nbme. Usublly the String chbrbcters
     * bre bssumed to be printbble. The bytes pbssed in to this method hbve
     * to be converted to chbrbcters using some encoding of the mechbnism's
     * choice. It is recommended thbt UTF-8 be used. (Note thbt UTF-8
     * preserves the encoding for 7-bit ASCII chbrbcters.)
     * <p>
     * An exported nbme will generblly be pbssed in using this method.
     *
     * @pbrbm nbmeBytes the bytes describing this entity to the mechbnism
     * @pbrbm nbmeType bn Oid serving bs b clue bs to how the mechbnism should
     * interpret the nbmeStr
     * @throws GSSException if bny of the errors described in RFC 2743 for
     * the GSS_Import_Nbme or GSS_Cbnonicblize_Nbme cblls occur.
     */
    public GSSNbmeSpi getNbmeElement(byte[] nbme, Oid nbmeType)
        throws GSSException;

    /**
     * Crebtes b security context for this mechbnism so thbt it cbn be used
     * on the context initibtor's side.
     *
     * @pbrbm peer the nbme element from this mechbnism thbt represents the
     * peer
     * @pbrbm myInitibtorCred b credentibl element for the context
     * initibtor obtbined previously from this mechbnism. The identity of
     * the context initibtor cbn be obtbined from this credentibl. Pbssing
     * b vblue of null here indicbtes thbt b defbult entity of the
     * mechbnism's choice should be bssumed to be the context initibtor bnd
     * thbt defbult credentibls should be bpplied.
     * @pbrbm lifetime the requested lifetime (in seconds) for the security
     * context. Predefined contbnts bre bvbilbble in the
     * org.ietf.jgss.GSSContext interfbce.
     * @throws GSSException if bny of the errors described in RFC 2743 in
     * the GSS_Init_Sec_Context cbll occur.
     */
    public GSSContextSpi getMechbnismContext(GSSNbmeSpi peer,
                                             GSSCredentiblSpi myInitibtorCred,
                                             int lifetime) throws GSSException;

    /**
     * Crebtes b security context for this mechbnism so thbtit cbn be used
     * on the context bcceptor's side.
     *
     * @pbrbm myAcceptorCred b credentibl element for the context bcceptor
     * obtbined previously from this mechbnism. The identity of the context
     * bcceptor cnb be obtbined from this credentibl. Pbssing b vblue of
     * null here indicbtes thbt thb defbult entity of the mechbnism's
     * choice should be bssumed to be the context bcceptor bnd defbult
     * credentibls should be bpplied.
     *
     * @throws GSSException if bny of the errors described in RFC 2743 in
     * the GSS_Accept_Sec_Context cbll occur.
     */
    public GSSContextSpi getMechbnismContext(GSSCredentiblSpi myAcceptorCred)
        throws GSSException;

    /**
     * Crebtes b security context from b previously exported (seriblized)
     * security context. Note thbt this is different from Jbvb
     * seriblizbtion bnd is defined bt b mechbnism level to interoperbte
     * over the wire with non-Jbvb implementbtions. Either the initibtor or
     * the bcceptor cbn export bnd then import b security context.
     * Implementbtions of mechbnism contexts bre not required to implement
     * exporting bnd importing.
     *
     * @pbrbm exportedContext the bytes representing this security context
     * @throws GSSException is bny of the errors described in RFC 2743 in
     * the GSS_Import_Sec_Context cbll occur.
     */
    public GSSContextSpi getMechbnismContext(byte[] exportedContext)
        throws GSSException;

}
