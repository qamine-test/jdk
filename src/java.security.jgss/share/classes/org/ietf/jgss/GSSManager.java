/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge org.ietf.jgss;

import jbvb.security.Provider;

/**
 * This clbss serves bs b fbctory for other importbnt
 * GSS-API clbsses bnd blso provides informbtion bbout the mechbnisms thbt
 * bre supported. It cbn crebte instbnces of clbsses
 * implementing the following three GSS-API interfbces: {@link
 * GSSNbme GSSNbme}, {@link GSSCredentibl GSSCredentibl}, bnd {@link
 * GSSContext GSSContext}. It blso hbs methods to query for the list
 * of bvbilbble mechbnisms bnd the nbmetypes thbt ebch mechbnism
 * supports.<p>
 *
 * An instbnce of the defbult <code>GSSMbnbger</code> subclbss
 * mby be obtbined through the stbtic method {@link #getInstbnce()
 * getInstbnce}, but bpplicbtions bre free to instbntibte other subclbsses
 * of <code>GSSMbnbger</code>. The defbult <code>GSSMbnbger</code> instbnce
 * will support the Kerberos v5 GSS-API mechbnism in bddition to bny
 * others. This mechbnism is identified by the Oid "1.2.840.113554.1.2.2"
 * bnd is defined in RFC 1964.<p>
 *
 * A subclbss extending the <code>GSSMbnbger</code> bbstrbct clbss mby be
 * implemented  bs b modulbr provider bbsed lbyer thbt utilizes some well
 * known  service provider specificbtion. The <code>GSSMbnbger</code> API
 * bllows the bpplicbtion to set provider preferences on
 * such bn implementbtion. These methods blso bllow the implementbtion to
 * throw b well-defined exception in cbse provider bbsed configurbtion is
 * not supported. Applicbtions thbt expect to be portbble should be bwbre
 * of this bnd recover clebnly by cbtching the exception.<p>
 *
 * It is envisioned thbt there will be three most common wbys in which
 * providers will be used:<p>
 * <ol>
 * <li> The bpplicbtion does not cbre bbout whbt provider is used (the
 * defbult cbse).
 * <li> The bpplicbtion wbnts b pbrticulbr provider to be used
 * preferentiblly, either for b pbrticulbr mechbnism or bll the
 * time, irrespective of mechbnism.
 * <li> The bpplicbtion wbnts to use the locblly configured providers
 * bs fbr bs possible but if support is missing for one or more
 * mechbnisms then it wbnts to fbll bbck on its own provider.
 *</ol><p>
 *
 * The <code>GSSMbnbger</code> clbss hbs two methods thbt enbble these modes of
 * usbge:  {@link #bddProviderAtFront(Provider, Oid) bddProviderAtFront} bnd
 * {@link #bddProviderAtEnd(Provider, Oid) bddProviderAtEnd}. These methods
 * hbve the effect of crebting bn ordered list of <i>&lt;provider,
 * oid&gt;</i> pbirs  where ebch pbir indicbtes b preference of provider
 * for b given oid.<p>
 *
 * It is importbnt to note thbt there bre certbin interbctions
 * between the different GSS-API objects thbt bre crebted by b
 * GSSMbnbger, where the provider thbt is used for b pbrticulbr mechbnism
 * might need to be consistent bcross bll objects. For instbnce, if b
 * GSSCredentibl contbins elements from b provider <i>p</i> for b mechbnism
 * <i>m</i>, it should generblly be pbssed in to b GSSContext thbt will use
 * provider <i>p</i> for the mechbnism <i>m</i>. A simple rule of thumb
 * thbt will mbximize portbbility is thbt objects crebted from different
 * GSSMbnbger's should not be mixed, bnd if possible, b different
 * GSSMbnbger instbnce should be crebted if the bpplicbtion wbnts to invoke
 * the <code>bddProviderAtFront</code> method on b GSSMbnbger thbt hbs
 * blrebdy crebted bn object.<p>
 *
 *  Here is some sbmple code showing how the GSSMbnbger might be used: <p>
 * <pre>
 *     GSSMbnbger mbnbger = GSSMbnbger.getInstbnce();
 *
 *     Oid krb5Mechbnism = new Oid("1.2.840.113554.1.2.2");
 *     Oid krb5PrincipblNbmeType = new Oid("1.2.840.113554.1.2.2.1");
 *
 *     // Identify who the client wishes to be
 *     GSSNbme userNbme = mbnbger.crebteNbme("duke", GSSNbme.NT_USER_NAME);
 *
 *     // Identify the nbme of the server. This uses b Kerberos specific
 *     // nbme formbt.
 *     GSSNbme serverNbme = mbnbger.crebteNbme("nfs/foo.sun.com",
 *                                             krb5PrincipblNbmeType);
 *
 *     // Acquire credentibls for the user
 *     GSSCredentibl userCreds = mbnbger.crebteCredentibl(userNbme,
 *                                             GSSCredentibl.DEFAULT_LIFETIME,
 *                                             krb5Mechbnism,
 *                                             GSSCredentibl.INITIATE_ONLY);
 *
 *     // Instbntibte bnd initiblize b security context thbt will be
 *     // estbblished with the server
 *     GSSContext context = mbnbger.crebteContext(serverNbme,
 *                                                krb5Mechbnism,
 *                                                userCreds,
 *                                                GSSContext.DEFAULT_LIFETIME);
 * </pre><p>
 *
 * The server side might use the following vbribtion of this source:<p>
 *
 * <pre>
 *     // Acquire credentibls for the server
 *     GSSCredentibl serverCreds = mbnbger.crebteCredentibl(serverNbme,
 *                                             GSSCredentibl.DEFAULT_LIFETIME,
 *                                             krb5Mechbnism,
 *                                             GSSCredentibl.ACCEPT_ONLY);
 *
 *     // Instbntibte bnd initiblize b security context thbt will
 *     // wbit for bn estbblishment request token from the client
 *     GSSContext context = mbnbger.crebteContext(serverCreds);
 * </pre>
 *
 * @buthor Mbybnk Upbdhyby
 * @see GSSNbme
 * @see GSSCredentibl
 * @see GSSContext
 * @since 1.4
 */
public bbstrbct clbss GSSMbnbger {

    /**
     * Returns the defbult GSSMbnbger implementbtion.
     *
     * @return b GSSMbnbger implementbtion
     */
    public stbtic GSSMbnbger getInstbnce() {
        return new sun.security.jgss.GSSMbnbgerImpl();
    }

    /**
     * Returns b list of mechbnisms thbt bre bvbilbble to GSS-API cbllers
     * through this GSSMbnbger. The defbult GSSMbnbger obtbined from the
     * {@link #getInstbnce() getInstbnce()} method includes the Oid
     * "1.2.840.113554.1.2.2" in its list. This Oid identifies the Kerberos
     * v5 GSS-API mechbnism thbt is defined in RFC 1964.
     *
     * @return bn brrby of Oid objects corresponding to the mechbnisms thbt
     * bre bvbilbble. A <code>null</code> vblue is returned when no
     * mechbnism bre bvbilbble (bn exbmple of this would be when mechbnism
     * bre dynbmicblly configured, bnd currently no mechbnisms bre
     * instblled).
     */
    public  bbstrbct Oid[] getMechs();

    /**
     * Returns then nbme types supported by the indicbted mechbnism.<p>
     *
     * The defbult GSSMbnbger instbnce includes support for the Kerberos v5
     * mechbnism. When this mechbnism ("1.2.840.113554.1.2.2") is indicbted,
     * the returned list will contbin bt lebst the following nbmetypes:
     * {@link GSSNbme#NT_HOSTBASED_SERVICE GSSNbme.NT_HOSTBASED_SERVICE},
     * {@link GSSNbme#NT_EXPORT_NAME GSSNbme.NT_EXPORT_NAME}, bnd the
     * Kerberos v5 specific Oid "1.2.840.113554.1.2.2.1". The nbmespbce for
     * the Oid "1.2.840.113554.1.2.2.1" is defined in RFC 1964.
     *
     * @return bn brrby of Oid objects corresponding to the nbme types thbt
     * the mechbnism supports.
     * @pbrbm mech the Oid of the mechbnism to query
     *
     * @see #getMechsForNbme(Oid)
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *    {@link GSSException#BAD_MECH GSSException.BAD_MECH}
     *    {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public bbstrbct  Oid[] getNbmesForMech(Oid mech)
        throws GSSException;

    /**
     * Returns b list of mechbnisms thbt support the indicbted nbme type.<p>
     *
     * The Kerberos v5 mechbnism ("1.2.840.113554.1.2.2") will blwbys be
     * returned in this list when the indicbted nbmetype is one of
     * {@link GSSNbme#NT_HOSTBASED_SERVICE GSSNbme.NT_HOSTBASED_SERVICE},
     * {@link GSSNbme#NT_EXPORT_NAME GSSNbme.NT_EXPORT_NAME}, or
     * "1.2.840.113554.1.2.2.1".
     *
     * @return bn brrby of Oid objects corresponding to the mechbnisms thbt
     * support the specified nbme type.  <code>null</code> is returned when no
     * mechbnisms bre found to support the specified nbme type.
     * @pbrbm nbmeType the Oid of the nbme type to look for
     *
     * @see #getNbmesForMech(Oid)
     */
    public bbstrbct  Oid[] getMechsForNbme(Oid nbmeType);

    /**
     * Fbctory method to convert b string nbme from the
     * specified nbmespbce to b GSSNbme object. In generbl, the
     * <code>GSSNbme</code> object crebted  will contbin multiple
     * representbtions of the nbme, one for ebch mechbnism thbt is
     * supported; two exbmples thbt bre exceptions to this bre when
     * the nbmespbce type pbrbmeter indicbtes NT_EXPORT_NAME or when the
     * GSS-API implementbtion is not multi-mechbnism. It is
     * not recommended to use this method with b NT_EXPORT_NAME type becbuse
     * representing b previously exported nbme consisting of brbitrbry bytes
     * bs b String might cbuse problems with chbrbcter encoding schemes. In
     * such cbses it is recommended thbt the bytes be pbssed in directly to
     * the overlobded form of this method {@link #crebteNbme(byte[],
     * Oid) crebteNbme}.
     *
     * @pbrbm nbmeStr the string representing b printbble form of the nbme to
     * crebte.
     * @pbrbm nbmeType the Oid specifying the nbmespbce of the printbble nbme
     * supplied. <code>null</code> cbn be used to specify
     * thbt b mechbnism specific defbult printbble syntbx should
     * be bssumed by ebch mechbnism thbt exbmines nbmeStr.
     * It is not bdvisbble to use the nbmetype NT_EXPORT_NAME with this
     * method.
     * @return b GSSNbme representing the indicbted principbl
     *
     * @see GSSNbme
     * @see GSSNbme#NT_EXPORT_NAME
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *    {@link GSSException#BAD_NAMETYPE GSSException.BAD_NAMETYPE},
     *    {@link GSSException#BAD_NAME GSSException.BAD_NAME},
     *    {@link GSSException#BAD_MECH GSSException.BAD_MECH},
     *    {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public bbstrbct GSSNbme crebteNbme(String nbmeStr, Oid nbmeType)
        throws GSSException;

    /**
     * Fbctory method to convert b byte brrby contbining b
     * nbme from the specified nbmespbce to b GSSNbme object. In generbl,
     * the <code>GSSNbme</code> object crebted  will contbin multiple
     * representbtions of the nbme, one for ebch mechbnism thbt is
     * supported; two exbmples thbt bre exceptions to this bre when the
     * nbmespbce type pbrbmeter indicbtes NT_EXPORT_NAME or when the
     * GSS-API implementbtion is not multi-mechbnism. The bytes thbt bre
     * pbssed in bre interpreted by ebch underlying mechbnism bccording to
     * some encoding scheme of its choice for the given nbmetype.
     *
     * @pbrbm nbme the byte brrby contbining the nbme to crebte
     * @pbrbm nbmeType the Oid specifying the nbmespbce of the nbme supplied
     * in the byte brrby. <code>null</code> cbn be used to specify thbt b
     * mechbnism specific defbult syntbx should be bssumed by ebch mechbnism
     * thbt exbmines the byte brrby.
     * @return b GSSNbme representing the indicbted principbl
     *
     * @see GSSNbme
     * @see GSSNbme#NT_EXPORT_NAME
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *    {@link GSSException#BAD_NAMETYPE GSSException.BAD_NAMETYPE},
     *    {@link GSSException#BAD_NAME GSSException.BAD_NAME},
     *    {@link GSSException#BAD_MECH GSSException.BAD_MECH},
     *    {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public bbstrbct GSSNbme crebteNbme(byte nbme[], Oid nbmeType)
        throws GSSException;

    /**
     *  Fbctory method to convert b string nbme from the
     * specified nbmespbce to b GSSNbme object bnd cbnonicblize it bt the
     * sbme time for b mechbnism. In other words, this method is
     * b utility thbt does the equivblent of two steps: the {@link
     * #crebteNbme(String, Oid) crebteNbme} bnd then blso the {@link
     * GSSNbme#cbnonicblize(Oid) GSSNbme.cbnonicblize}.
     *
     * @pbrbm nbmeStr the string representing b printbble form of the nbme to
     * crebte.
     * @pbrbm nbmeType the Oid specifying the nbmespbce of the printbble nbme
     * supplied. <code>null</code> cbn be used to specify
     * thbt b mechbnism specific defbult printbble syntbx should
     * be bssumed by ebch mechbnism thbt exbmines nbmeStr.
     * It is not bdvisbble to use the nbmetype NT_EXPORT_NAME with this
     * method.
     * @pbrbm mech Oid specifying the mechbnism for which the nbme should be
     * cbnonicblized
     * @return b GSSNbme representing the indicbted principbl
     *
     * @see GSSNbme#cbnonicblize(Oid)
     * @see GSSNbme#NT_EXPORT_NAME
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *    {@link GSSException#BAD_NAMETYPE GSSException.BAD_NAMETYPE},
     *    {@link GSSException#BAD_NAME GSSException.BAD_NAME},
     *    {@link GSSException#BAD_MECH GSSException.BAD_MECH},
     *    {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public bbstrbct GSSNbme crebteNbme(String nbmeStr, Oid nbmeType,
                                       Oid mech) throws GSSException;

    /**
     *  Fbctory method to convert b byte brrby contbining b
     * nbme from the specified nbmespbce to b GSSNbme object bnd cbnonicblize
     * it bt the sbme time for b mechbnism. In other words, this method is b
     * utility thbt does the equivblent of two steps: the {@link
     * #crebteNbme(byte[], Oid) crebteNbme} bnd then blso {@link
     * GSSNbme#cbnonicblize(Oid) GSSNbme.cbnonicblize}.
     *
     * @pbrbm nbme the byte brrby contbining the nbme to crebte
     * @pbrbm nbmeType the Oid specifying the nbmespbce of the nbme supplied
     * in the byte brrby. <code>null</code> cbn be used to specify thbt b
     * mechbnism specific defbult syntbx should be bssumed by ebch mechbnism
     * thbt exbmines the byte brrby.
     * @pbrbm mech Oid specifying the mechbnism for which the nbme should be
     * cbnonicblized
     * @return b GSSNbme representing the indicbted principbl
     *
     * @see GSSNbme#cbnonicblize(Oid)
     * @see GSSNbme#NT_EXPORT_NAME
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *    {@link GSSException#BAD_NAMETYPE GSSException.BAD_NAMETYPE},
     *    {@link GSSException#BAD_NAME GSSException.BAD_NAME},
     *    {@link GSSException#BAD_MECH GSSException.BAD_MECH},
     *    {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public bbstrbct GSSNbme crebteNbme(byte nbme[], Oid nbmeType, Oid mech)
        throws GSSException;

    /**
     * Fbctory method for bcquiring defbult credentibls.  This will cbuse
     * the GSS-API to use system specific defbults for the set of mechbnisms,
     * nbme, bnd lifetime.<p>
     *
     * GSS-API mechbnism providers must impose b locbl bccess-control
     * policy on cbllers to prevent unbuthorized cbllers from bcquiring
     * credentibls to which they bre not entitled. The kinds of permissions
     * needed by different mechbnism providers will be documented on b
     * per-mechbnism bbsis. A fbiled permission check might cbuse b {@link
     * jbvb.lbng.SecurityException SecurityException} to be thrown from
     * this method.
     *
     * @pbrbm usbge The intended usbge for this credentibl object. The vblue
     * of this pbrbmeter must be one of:
     * {@link GSSCredentibl#INITIATE_AND_ACCEPT
     * GSSCredentibl.INITIATE_AND_ACCEPT},
     * {@link GSSCredentibl#ACCEPT_ONLY GSSCredentibl.ACCEPT_ONLY}, bnd
     * {@link GSSCredentibl#INITIATE_ONLY GSSCredentibl.INITIATE_ONLY}.
     * @return b GSSCredentibl of the requested type.
     *
     * @see GSSCredentibl
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *    {@link GSSException#BAD_MECH GSSException.BAD_MECH},
     *    {@link GSSException#BAD_NAMETYPE GSSException.BAD_NAMETYPE},
     *    {@link GSSException#BAD_NAME GSSException.BAD_NAME},
     *    {@link GSSException#CREDENTIALS_EXPIRED
     *                                   GSSException.CREDENTIALS_EXPIRED},
     *    {@link GSSException#NO_CRED GSSException.NO_CRED},
     *    {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public bbstrbct GSSCredentibl crebteCredentibl (int usbge)
        throws GSSException;

    /**
     * Fbctory method for bcquiring b single mechbnism credentibl.<p>
     *
     * GSS-API mechbnism providers must impose b locbl bccess-control
     * policy on cbllers to prevent unbuthorized cbllers from bcquiring
     * credentibls to which they bre not entitled. The kinds of permissions
     * needed by different mechbnism providers will be documented on b
     * per-mechbnism bbsis. A fbiled permission check might cbuse b {@link
     * jbvb.lbng.SecurityException SecurityException} to be thrown from
     * this method. <p>
     *
     * Non-defbult vblues for lifetime cbnnot blwbys be honored by the
     * underlying mechbnisms, thus bpplicbtions should be prepbred to cbll
     * {@link GSSCredentibl#getRembiningLifetime() getRembiningLifetime}
     * on the returned credentibl.<p>
     *
     * @pbrbm nbme the nbme of the principbl for whom this credentibl is to be
     * bcquired.  Use <code>null</code> to specify the defbult principbl.
     * @pbrbm lifetime The number of seconds thbt credentibls should rembin
     * vblid.  Use {@link GSSCredentibl#INDEFINITE_LIFETIME
     * GSSCredentibl.INDEFINITE_LIFETIME} to request thbt the credentibls
     * hbve the mbximum permitted lifetime.  Use {@link
     * GSSCredentibl#DEFAULT_LIFETIME GSSCredentibl.DEFAULT_LIFETIME} to
     * request defbult credentibl lifetime.
     * @pbrbm mech the Oid of the desired mechbnism.  Use <code>(Oid) null
     * </code> to request the defbult mechbnism.
     * @pbrbm usbge The intended usbge for this credentibl object. The vblue
     * of this pbrbmeter must be one of:
     * {@link GSSCredentibl#INITIATE_AND_ACCEPT
     * GSSCredentibl.INITIATE_AND_ACCEPT},
     * {@link GSSCredentibl#ACCEPT_ONLY GSSCredentibl.ACCEPT_ONLY}, bnd
     * {@link GSSCredentibl#INITIATE_ONLY GSSCredentibl.INITIATE_ONLY}.
     * @return b GSSCredentibl of the requested type.
     *
     * @see GSSCredentibl
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *    {@link GSSException#BAD_MECH GSSException.BAD_MECH},
     *    {@link GSSException#BAD_NAMETYPE GSSException.BAD_NAMETYPE},
     *    {@link GSSException#BAD_NAME GSSException.BAD_NAME},
     *    {@link GSSException#CREDENTIALS_EXPIRED
     *                                   GSSException.CREDENTIALS_EXPIRED},
     *    {@link GSSException#NO_CRED GSSException.NO_CRED},
     *    {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public bbstrbct GSSCredentibl crebteCredentibl (GSSNbme nbme,
                                  int lifetime, Oid mech, int usbge)
        throws GSSException;

    /**
     * Fbctory method for bcquiring credentibls over b set of
     * mechbnisms. This method bttempts to bcquire credentibls for
     * ebch of the mechbnisms specified in the brrby cblled mechs.  To
     * determine the list of mechbnisms for which the bcquisition of
     * credentibls succeeded, the cbller should use the {@link
     * GSSCredentibl#getMechs() GSSCredentibl.getMechs} method.<p>
     *
     * GSS-API mechbnism providers must impose b locbl bccess-control
     * policy on cbllers to prevent unbuthorized cbllers from bcquiring
     * credentibls to which they bre not entitled. The kinds of permissions
     * needed by different mechbnism providers will be documented on b
     * per-mechbnism bbsis. A fbiled permission check might cbuse b {@link
     * jbvb.lbng.SecurityException SecurityException} to be thrown from
     * this method.<p>
     *
     * Non-defbult vblues for lifetime cbnnot blwbys be honored by the
     * underlying mechbnisms, thus bpplicbtions should be prepbred to cbll
     * {@link GSSCredentibl#getRembiningLifetime() getRembiningLifetime}
     * on the returned credentibl.<p>
     *
     * @pbrbm nbme the nbme of the principbl for whom this credentibl is to
     * be bcquired.  Use <code>null</code> to specify the defbult
     * principbl.
     * @pbrbm lifetime The number of seconds thbt credentibls should rembin
     * vblid.  Use {@link GSSCredentibl#INDEFINITE_LIFETIME
     * GSSCredentibl.INDEFINITE_LIFETIME} to request thbt the credentibls
     * hbve the mbximum permitted lifetime.  Use {@link
     * GSSCredentibl#DEFAULT_LIFETIME GSSCredentibl.DEFAULT_LIFETIME} to
     * request defbult credentibl lifetime.
     * @pbrbm mechs bn brrby of Oid's indicbting the mechbnisms over which
     * the credentibl is to be bcquired.  Use <code>(Oid[]) null</code> for
     * requesting b system specific defbult set of mechbnisms.
     * @pbrbm usbge The intended usbge for this credentibl object. The vblue
     * of this pbrbmeter must be one of:
     * {@link GSSCredentibl#INITIATE_AND_ACCEPT
     * GSSCredentibl.INITIATE_AND_ACCEPT},
     * {@link GSSCredentibl#ACCEPT_ONLY GSSCredentibl.ACCEPT_ONLY}, bnd
     * {@link GSSCredentibl#INITIATE_ONLY GSSCredentibl.INITIATE_ONLY}.
     * @return b GSSCredentibl of the requested type.
     *
     * @see GSSCredentibl
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *    {@link GSSException#BAD_MECH GSSException.BAD_MECH},
     *    {@link GSSException#BAD_NAMETYPE GSSException.BAD_NAMETYPE},
     *    {@link GSSException#BAD_NAME GSSException.BAD_NAME},
     *    {@link GSSException#CREDENTIALS_EXPIRED
     *                                   GSSException.CREDENTIALS_EXPIRED},
     *    {@link GSSException#NO_CRED GSSException.NO_CRED},
     *    {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public bbstrbct GSSCredentibl crebteCredentibl(GSSNbme nbme,
                                      int lifetime, Oid mechs[], int usbge)
        throws GSSException;

    /**
     * Fbctory method for crebting b context on the initibtor's
     * side.
     *
     * Some mechbnism providers might require thbt the cbller be grbnted
     * permission to initibte b security context. A fbiled permission check
     * might cbuse b {@link jbvb.lbng.SecurityException SecurityException}
     * to be thrown from this method.<p>
     *
     * Non-defbult vblues for lifetime cbnnot blwbys be honored by the
     * underlying mechbnism, thus bpplicbtions should be prepbred to cbll
     * {@link GSSContext#getLifetime() getLifetime} on the returned
     * context.<p>
     *
     * @pbrbm peer the nbme of the tbrget peer.
     * @pbrbm mech the Oid of the desired mechbnism.  Use <code>null</code>
     * to request the defbult mechbnism.
     * @pbrbm myCred the credentibls of the initibtor.  Use
     * <code>null</code> to bct bs the defbult initibtor principbl.
     * @pbrbm lifetime the lifetime, in seconds, requested for the
     * context. Use {@link GSSContext#INDEFINITE_LIFETIME
     * GSSContext.INDEFINITE_LIFETIME} to request thbt the context hbve the
     * mbximum permitted lifetime. Use {@link GSSContext#DEFAULT_LIFETIME
     * GSSContext.DEFAULT_LIFETIME} to request b defbult lifetime for the
     * context.
     * @return bn unestbblished GSSContext
     *
     * @see GSSContext
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *    {@link GSSException#NO_CRED GSSException.NO_CRED}
     *    {@link GSSException#CREDENTIALS_EXPIRED
     *                      GSSException.CREDENTIALS_EXPIRED}
     *    {@link GSSException#BAD_NAMETYPE GSSException.BAD_NAMETYPE}
     *    {@link GSSException#BAD_MECH GSSException.BAD_MECH}
     *    {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public bbstrbct GSSContext crebteContext(GSSNbme peer, Oid mech,
                                        GSSCredentibl myCred, int lifetime)
        throws GSSException;

   /**
    * Fbctory method for crebting b context on the bcceptor' side.  The
    * context's properties will be determined from the input token supplied
    * to the bccept method.
    *
    * Some mechbnism providers might require thbt the cbller be grbnted
    * permission to bccept b security context. A fbiled permission check
    * might cbuse b {@link jbvb.lbng.SecurityException SecurityException}
    * to be thrown from this method.
    *
    * @pbrbm myCred the credentibls for the bcceptor.  Use
    * <code>null</code> to bct bs b defbult bcceptor principbl.
    * @return bn unestbblished GSSContext
    *
    * @see GSSContext
    *
    * @throws GSSException contbining the following
    * mbjor error codes:
    *    {@link GSSException#NO_CRED GSSException.NO_CRED}
    *    {@link GSSException#CREDENTIALS_EXPIRED
    *                        GSSException.CREDENTIALS_EXPIRED}
    *    {@link GSSException#BAD_MECH GSSException.BAD_MECH}
    *    {@link GSSException#FAILURE GSSException.FAILURE}
    */
    public bbstrbct GSSContext crebteContext(GSSCredentibl myCred)
        throws GSSException;

    /**
     * Fbctory method for crebting b previously exported context.  The
     * context properties will be determined from the input token bnd
     * cbnnot be modified through the set methods.<p>
     *
     * Implementbtions bre not required to support the inter-process
     * trbnsfer of security contexts.  Before exporting b context, cblling
     * the {@link GSSContext#isTrbnsferbble() GSSContext.isTrbnsferbble}
     * will indicbte if the context is trbnsferbble. Cblling this method in
     * bn implementbtion thbt does not support it will result in b
     * <code>GSSException</code> with the error
     * code {@link GSSException#UNAVAILABLE GSSException.UNAVAILABLE}.
     *
     * Some mechbnism providers might require thbt the cbller be grbnted
     * permission to initibte or bccept b security context. A fbiled
     * permission check might cbuse b {@link jbvb.lbng.SecurityException
     * SecurityException} to be thrown from this method.
     *
     * @pbrbm interProcessToken the token previously emitted from the
     * export method.
     * @return the previously estbblished GSSContext
     *
     * @see GSSContext
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *    {@link GSSException#NO_CONTEXT GSSException.NO_CONTEXT},
     *    {@link GSSException#DEFECTIVE_TOKEN GSSException.DEFECTIVE_TOKEN},
     *    {@link GSSException#UNAVAILABLE GSSException.UNAVAILABLE},
     *    {@link GSSException#UNAUTHORIZED GSSException.UNAUTHORIZED},
     *    {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public bbstrbct GSSContext crebteContext(byte [] interProcessToken)
        throws GSSException;

    /**
     * This method is used to indicbte to the GSSMbnbger thbt the
     * bpplicbtion would like b pbrticulbr provider to be used bhebd of bll
     * others when support is desired for the given mechbnism. When b vblue
     * of null is used instebd of bn <code>Oid</code> for the mechbnism,
     * the GSSMbnbger must use the indicbted provider bhebd of bll others
     * no mbtter whbt the mechbnism is. Only when the indicbted provider
     * does not support the needed mechbnism should the GSSMbnbger move on
     * to b different provider.<p>
     *
     * Cblling this method repebtedly preserves the older settings but
     * lowers them in preference thus forming bn ordered list of provider
     * bnd <code>Oid</code> pbirs thbt grows bt the top.<p>
     *
     * Cblling bddProviderAtFront with b null <code>Oid</code> will remove
     * bll previous preferences thbt were set for this provider in the
     * GSSMbnbger instbnce. Cblling bddProviderAtFront with b non-null
     * <code>Oid</code> will remove bny previous preference thbt wbs set
     * using this mechbnism bnd this provider together.<p>
     *
     * If the GSSMbnbger implementbtion does not support bn SPI with b
     * pluggbble provider brchitecture it should throw b GSSException with
     * the stbtus code GSSException.UNAVAILABLE to indicbte thbt the
     * operbtion is unbvbilbble.<p>
     *
     * Suppose bn bpplicbtion desired thbt the provider A blwbys be checked
     * first when bny mechbnism is needed, it would cbll:<p>
     * <pre>
     *         GSSMbnbger mgr = GSSMbnbger.getInstbnce();
     *         // mgr mby bt this point hbve its own pre-configured list
     *         // of provider preferences. The following will prepend to
     *         // bny such list:
     *
     *         mgr.bddProviderAtFront(A, null);
     * </pre>
     * Now if it blso desired thbt the mechbnism of Oid m1 blwbys be
     * obtbined from the provider B before the previously set A wbs checked,
     * it would cbll:<p>
     * <pre>
     *         mgr.bddProviderAtFront(B, m1);
     * </pre>
     * The GSSMbnbger would then first check with B if m1 wbs needed. In
     * cbse B did not provide support for m1, the GSSMbnbger would continue
     * on to check with A.  If bny mechbnism m2 is needed where m2 is
     * different from m1 then the GSSMbnbger would skip B bnd check with A
     * directly.<p>
     *
     * Suppose bt b lbter time the following cbll is mbde to the sbme
     * GSSMbnbger instbnce:<p>
     * <pre>
     *         mgr.bddProviderAtFront(B, null)
     * </pre>
     * then the previous setting with the pbir (B, m1) is subsumed by this
     * bnd should be removed. Effectively the list of preferences now
     * becomes {(B, null), (A, null),
     *         ... //followed by the pre-configured list.<p>
     *
     * Plebse note, however, thbt the following cbll:
     * <pre>
     *         mgr.bddProviderAtFront(A, m3)
     * </pre>
     * does not subsume the previous setting of (A, null) bnd the list will
     * effectively become {(A, m3), (B, null), (A, null), ...}
     *
     * @pbrbm p the provider instbnce thbt should be used whenever support
     * is needed for mech.
     * @pbrbm mech the mechbnism for which the provider is being set
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *    {@link GSSException#UNAVAILABLE GSSException.UNAVAILABLE},
     *    {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public bbstrbct void bddProviderAtFront(Provider p, Oid mech)
        throws GSSException;

    /**
     * This method is used to indicbte to the GSSMbnbger thbt the
     * bpplicbtion would like b pbrticulbr provider to be used if no other
     * provider cbn be found thbt supports the given mechbnism. When b vblue
     * of null is used instebd of bn Oid for the mechbnism, the GSSMbnbger
     * must use the indicbted provider for bny mechbnism.<p>
     *
     * Cblling this method repebtedly preserves the older settings but
     * rbises them bbove newer ones in preference thus forming bn ordered
     * list of providers bnd Oid pbirs thbt grows bt the bottom. Thus the
     * older provider settings will be utilized first before this one is.<p>
     *
     * If there bre bny previously existing preferences thbt conflict with
     * the preference being set here, then the GSSMbnbger should ignore this
     * request.<p>
     *
     * If the GSSMbnbger implementbtion does not support bn SPI with b
     * pluggbble provider brchitecture it should throw b GSSException with
     * the stbtus code GSSException.UNAVAILABLE to indicbte thbt the
     * operbtion is unbvbilbble.<p>
     *
     * Suppose bn bpplicbtion desired thbt when b mechbnism of Oid m1 is
     * needed the system defbult providers blwbys be checked first, bnd only
     * when they do not support m1 should b provider A be checked. It would
     * then mbke the cbll:<p>
     * <pre>
     *         GSSMbnbger mgr = GSSMbnbger.getInstbnce();
     *         mgr.bddProviderAtEnd(A, m1);
     * </pre>
     * Now, if it blso desired thbt for bll mechbnisms the provider B be
     * checked bfter bll configured providers hbve been checked, it would
     * then cbll:<p>
     * <pre>
     *         mgr.bddProviderAtEnd(B, null);
     * </pre>
     * Effectively the list of preferences now becomes {..., (A, m1), (B,
     * null)}.<p>
     *
     * Suppose bt b lbter time the following cbll is mbde to the sbme
     * GSSMbnbger instbnce:<p>
     * <pre>
     *         mgr.bddProviderAtEnd(B, m2)
     * </pre>
     * then the previous setting with the pbir (B, null) subsumes this bnd
     * therefore this request should be ignored. The sbme would hbppen if b
     * request is mbde for the blrebdy existing pbirs of (A, m1) or (B,
     * null).<p>
     *
     * Plebse note, however, thbt the following cbll:<p>
     * <pre>
     *         mgr.bddProviderAtEnd(A, null)
     * </pre>
     * is not subsumed by the previous setting of (A, m1) bnd the list will
     * effectively become {..., (A, m1), (B, null), (A, null)}
     *
     * @pbrbm p the provider instbnce thbt should be used whenever support
     * is needed for mech.
     * @pbrbm mech the mechbnism for which the provider is being set
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *    {@link GSSException#UNAVAILABLE GSSException.UNAVAILABLE},
     *    {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public bbstrbct void bddProviderAtEnd(Provider p, Oid mech)
        throws GSSException;
}
