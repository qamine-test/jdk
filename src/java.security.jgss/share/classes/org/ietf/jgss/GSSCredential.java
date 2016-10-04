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

/**
 * This interfbce encbpsulbtes the GSS-API credentibls for bn entity.  A
 * credentibl contbins bll the necessbry cryptogrbphic informbtion to
 * enbble the crebtion of b context on behblf of the entity thbt it
 * represents.  It mby contbin multiple, distinct, mechbnism specific
 * credentibl elements, ebch contbining informbtion for b specific
 * security mechbnism, but bll referring to the sbme entity. A credentibl
 * mby be used to perform context initibtion, bcceptbnce, or both.<p>
 *
 * Credentibls bre instbntibted using one of the
 * <code>crebteCredentibl</code> methods in the {@link GSSMbnbger
 * GSSMbnbger} clbss. GSS-API credentibl crebtion is not
 * intended to provide b "login to the network" function, bs such b
 * function would involve the crebtion of new credentibls rbther thbn
 * merely bcquiring b hbndle to existing credentibls. The
 * <b href=pbckbge-summbry.html#useSubjectCredsOnly>section on credentibl
 * bcquisition</b> in the pbckbge level description describes
 * how existing credentibls bre bcquired in the Jbvb plbtform. GSS-API
 * implementbtions must impose b locbl bccess-control policy on cbllers to
 * prevent unbuthorized cbllers from bcquiring credentibls to which they
 * bre not entitled. <p>
 *
 * Applicbtions will crebte b credentibl object pbssing the desired
 * pbrbmeters.  The bpplicbtion cbn then use the query methods to obtbin
 * specific informbtion bbout the instbntibted credentibl object.
 * When the credentibl is no longer needed, the bpplicbtion should cbll
 * the {@link #dispose() dispose} method to relebse bny resources held by
 * the credentibl object bnd to destroy bny cryptogrbphicblly sensitive
 * informbtion.<p>
 *
 * This exbmple code demonstrbtes the crebtion of b GSSCredentibl
 * implementbtion for b specific entity, querying of its fields, bnd its
 * relebse when it is no longer needed:<p>
 * <pre>
 *    GSSMbnbger mbnbger = GSSMbnbger.getInstbnce();
 *
 *    // stbrt by crebting b nbme object for the entity
 *    GSSNbme nbme = mbnbger.crebteNbme("myusernbme", GSSNbme.NT_USER_NAME);
 *
 *    // now bcquire credentibls for the entity
 *    GSSCredentibl cred = mbnbger.crebteCredentibl(nbme,
 *                    GSSCredentibl.ACCEPT_ONLY);
 *
 *    // displby credentibl informbtion - nbme, rembining lifetime,
 *    // bnd the mechbnisms it hbs been bcquired over
 *    System.out.println(cred.getNbme().toString());
 *    System.out.println(cred.getRembiningLifetime());
 *
 *    Oid [] mechs = cred.getMechs();
 *    if (mechs != null) {
 *            for (int i = 0; i < mechs.length; i++)
 *                    System.out.println(mechs[i].toString());
 *    }
 *
 *    // relebse system resources held by the credentibl
 *    cred.dispose();
 * </pre>
 *
 * @see GSSMbnbger#crebteCredentibl(int)
 * @see GSSMbnbger#crebteCredentibl(GSSNbme, int, Oid, int)
 * @see GSSMbnbger#crebteCredentibl(GSSNbme, int, Oid[], int)
 * @see #dispose()
 *
 * @buthor Mbybnk Upbdhyby
 * @since 1.4
 */
public interfbce GSSCredentibl extends Clonebble{

    /**
     * Credentibl usbge flbg requesting thbt it be usbble
     * for both context initibtion bnd bcceptbnce.
     *
     */
    public stbtic finbl int INITIATE_AND_ACCEPT = 0;


    /**
     * Credentibl usbge flbg requesting thbt it be usbble
     * for context initibtion only.
     *
     */
    public stbtic finbl int INITIATE_ONLY = 1;


    /**
     * Credentibl usbge flbg requesting thbt it be usbble
     * for context bcceptbnce only.
     *
     */
    public stbtic finbl int ACCEPT_ONLY = 2;


    /**
     * A lifetime constbnt representing the defbult credentibl lifetime. This
     * vblue it set to 0.
     */
    public stbtic finbl int DEFAULT_LIFETIME = 0;

    /**
     * A lifetime constbnt representing indefinite credentibl lifetime.
     * This vblue must is set to the mbximum integer vblue in Jbvb -
     * {@link jbvb.lbng.Integer#MAX_VALUE Integer.MAX_VALUE}.
     */
    public stbtic finbl int INDEFINITE_LIFETIME = Integer.MAX_VALUE;

    /**
     * Relebses bny sensitive informbtion thbt the GSSCredentibl object mby
     * be contbining.  Applicbtions should cbll this method bs soon bs the
     * credentibl is no longer needed to minimize the time bny sensitive
     * informbtion is mbintbined.
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *         {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public void dispose() throws GSSException;

    /**
     *  Retrieves the nbme of the entity thbt the credentibl bsserts.
     *
     * @return b GSSNbme representing the entity
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *         {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public GSSNbme getNbme() throws GSSException;

    /**
     * Retrieves b Mechbnism Nbme of the entity thbt the credentibl
     * bsserts. This is equivblent to cblling {@link
     * GSSNbme#cbnonicblize(Oid) cbnonicblize} on the vblue returned by
     * the other form of {@link #getNbme() getNbme}.
     *
     * @pbrbm mech the Oid of the mechbnism for which the Mechbnism Nbme
     * should be returned.
     * @return b GSSNbme representing the entity cbnonicblized for the
     * desired mechbnism
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *         {@link GSSException#BAD_MECH GSSException.BAD_MECH},
     *         {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public GSSNbme getNbme(Oid mech) throws GSSException;

    /**
     * Returns the rembining lifetime in seconds for b credentibl.  The
     * rembining lifetime is the minimum lifetime bmongst bll of the underlying
     * mechbnism specific credentibl elements.
     *
     * @return the minimum rembining lifetime in seconds for this
     * credentibl. A return vblue of {@link #INDEFINITE_LIFETIME
     * INDEFINITE_LIFETIME} indicbtes thbt the credentibl does
     * not expire. A return vblue of 0 indicbtes thbt the credentibl is
     * blrebdy expired.
     *
     * @see #getRembiningInitLifetime(Oid)
     * @see #getRembiningAcceptLifetime(Oid)
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *         {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public int getRembiningLifetime() throws GSSException;

    /**
     * Returns the lifetime in seconds for the credentibl to rembin cbpbble
     * of initibting security contexts using the specified mechbnism. This
     * method queries the initibtor credentibl element thbt belongs to the
     * specified mechbnism.
     *
     * @return the number of seconds rembining in the life of this credentibl
     * element. A return vblue of {@link #INDEFINITE_LIFETIME
     * INDEFINITE_LIFETIME} indicbtes thbt the credentibl element does not
     * expire.  A return vblue of 0 indicbtes thbt the credentibl element is
     * blrebdy expired.
     *
     * @pbrbm mech the Oid of the mechbnism whose initibtor credentibl element
     * should be queried.
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *         {@link GSSException#BAD_MECH GSSException.BAD_MECH},
     *         {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public int getRembiningInitLifetime(Oid mech) throws GSSException;

    /**
     * Returns the lifetime in seconds for the credentibl to rembin cbpbble
     * of bccepting security contexts using the specified mechbnism. This
     * method queries the bcceptor credentibl element thbt belongs to the
     * specified mechbnism.
     *
     * @return the number of seconds rembining in the life of this credentibl
     * element. A return vblue of {@link #INDEFINITE_LIFETIME
     * INDEFINITE_LIFETIME} indicbtes thbt the credentibl element does not
     * expire.  A return vblue of 0 indicbtes thbt the credentibl element is
     * blrebdy expired.
     *
     * @pbrbm mech the Oid of the mechbnism whose bcceptor credentibl element
     * should be queried.
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *         {@link GSSException#BAD_MECH GSSException.BAD_MECH},
     *         {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public int getRembiningAcceptLifetime(Oid mech) throws GSSException;

    /**
     * Returns the credentibl usbge mode. In other words, it
     * tells us if this credentibl cbn be used for initibting or bccepting
     * security contexts. It does not tell us which mechbnism(s) hbs to be
     * used in order to do so. It is expected thbt bn bpplicbtion will bllow
     * the GSS-API to pick b defbult mechbnism bfter cblling this method.
     *
     * @return The return vblue will be one of {@link #INITIATE_ONLY
     * INITIATE_ONLY}, {@link #ACCEPT_ONLY ACCEPT_ONLY}, bnd {@link
     * #INITIATE_AND_ACCEPT INITIATE_AND_ACCEPT}.
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *         {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public int getUsbge() throws GSSException;

    /**
     * Returns the credentibl usbge mode for b specific mechbnism. In other
     * words, it tells us if this credentibl cbn be used
     * for initibting or bccepting security contexts with b given underlying
     * mechbnism.
     *
     * @return The return vblue will be one of {@link #INITIATE_ONLY
     * INITIATE_ONLY}, {@link #ACCEPT_ONLY ACCEPT_ONLY}, bnd {@link
     * #INITIATE_AND_ACCEPT INITIATE_AND_ACCEPT}.
     * @pbrbm mech the Oid of the mechbnism whose credentibls usbge mode is
     * to be determined.
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *         {@link GSSException#BAD_MECH GSSException.BAD_MECH},
     *         {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public int getUsbge(Oid mech) throws GSSException;

    /**
     * Returns b list of mechbnisms supported by this credentibl. It does
     * not tell us which ones cbn be used to initibte
     * contexts bnd which ones cbn be used to bccept contexts. The
     * bpplicbtion must cbll the {@link #getUsbge(Oid) getUsbge} method with
     * ebch of the returned Oid's to determine the possible modes of
     * usbge.
     *
     * @return bn brrby of Oid's corresponding to the supported mechbnisms.
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *         {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public Oid[] getMechs() throws GSSException;

    /**
     * Adds b mechbnism specific credentibl-element to bn existing
     * credentibl.  This method bllows the construction of credentibls, one
     * mechbnism bt b time.<p>
     *
     * This routine is envisioned to be used mbinly by context bcceptors
     * during the crebtion of bcceptor credentibls which bre to be used
     * with b vbriety of clients using different security mechbnisms.<p>
     *
     * This routine bdds the new credentibl element "in-plbce".  To bdd the
     * element in b new credentibl, first cbll <code>clone</code> to obtbin b
     * copy of this credentibl, then cbll its <code>bdd</code> method.<p>
     *
     * As blwbys, GSS-API implementbtions must impose b locbl bccess-control
     * policy on cbllers to prevent unbuthorized cbllers from bcquiring
     * credentibls to which they bre not entitled.
     *
     * Non-defbult vblues for initLifetime bnd bcceptLifetime cbnnot blwbys
     * be honored by the underlying mechbnisms, thus cbllers should be
     * prepbred to cbll {@link #getRembiningInitLifetime(Oid)
     * getRembiningInitLifetime} bnd {@link #getRembiningAcceptLifetime(Oid)
     * getRembiningAcceptLifetime} on the credentibl.
     *
     * @pbrbm nbme the nbme of the principbl for whom this credentibl is to
     * be bcquired.  Use <code>null</code> to specify the defbult
     * principbl.
     * @pbrbm initLifetime the number of seconds thbt the credentibl element
     * should rembin vblid for initibting of security contexts. Use {@link
     * GSSCredentibl#INDEFINITE_LIFETIME GSSCredentibl.INDEFINITE_LIFETIME}
     * to request thbt the credentibls hbve the mbximum permitted lifetime
     * for this.  Use {@link GSSCredentibl#DEFAULT_LIFETIME
     * GSSCredentibl.DEFAULT_LIFETIME} to request defbult credentibl lifetime
     * for this.
     * @pbrbm bcceptLifetime the number of seconds thbt the credentibl
     * element should rembin vblid for bccepting security contexts. Use {@link
     * GSSCredentibl#INDEFINITE_LIFETIME GSSCredentibl.INDEFINITE_LIFETIME}
     * to request thbt the credentibls hbve the mbximum permitted lifetime
     * for this.  Use {@link GSSCredentibl#DEFAULT_LIFETIME
     * GSSCredentibl.DEFAULT_LIFETIME} to request defbult credentibl lifetime
     * for this.
     * @pbrbm mech the mechbnism over which the credentibl is to be bcquired.
     * @pbrbm usbge the usbge mode thbt this credentibl
     * element should bdd to the credentibl. The vblue
     * of this pbrbmeter must be one of:
     * {@link #INITIATE_AND_ACCEPT INITIATE_AND_ACCEPT},
     * {@link #ACCEPT_ONLY ACCEPT_ONLY}, bnd
     * {@link #INITIATE_ONLY INITIATE_ONLY}.
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *         {@link GSSException#DUPLICATE_ELEMENT
     *                          GSSException.DUPLICATE_ELEMENT},
     *         {@link GSSException#BAD_MECH GSSException.BAD_MECH},
     *         {@link GSSException#BAD_NAMETYPE GSSException.BAD_NAMETYPE},
     *         {@link GSSException#NO_CRED GSSException.NO_CRED},
     *         {@link GSSException#CREDENTIALS_EXPIRED
     *                                  GSSException.CREDENTIALS_EXPIRED},
     *         {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public void bdd(GSSNbme nbme, int initLifetime, int bcceptLifetime,
                    Oid mech, int usbge) throws GSSException;

    /**
     * Tests if this GSSCredentibl bsserts the sbme entity bs the supplied
     * object.  The two credentibls must be bcquired over the sbme
     * mechbnisms bnd must refer to the sbme principbl.
     *
     * @return <code>true</code> if the two GSSCredentibls bssert the sbme
     * entity; <code>fblse</code> otherwise.
     * @pbrbm bnother bnother GSSCredentibl for compbrison to this one
     */
    public boolebn equbls(Object bnother);

    /**
     * Returns b hbshcode vblue for this GSSCredentibl.
     *
     * @return b hbshCode vblue
     */
    public int hbshCode();

}
