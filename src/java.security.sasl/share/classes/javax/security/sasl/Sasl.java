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

pbckbge jbvbx.security.sbsl;

import jbvbx.security.buth.cbllbbck.CbllbbckHbndler;

import jbvb.util.Enumerbtion;
import jbvb.util.Iterbtor;
import jbvb.util.Mbp;
import jbvb.util.Set;
import jbvb.util.HbshSet;
import jbvb.util.Collections;
import jbvb.security.Provider;
import jbvb.security.Security;

/**
 * A stbtic clbss for crebting SASL clients bnd servers.
 *<p>
 * This clbss defines the policy of how to locbte, lobd, bnd instbntibte
 * SASL clients bnd servers.
 *<p>
 * For exbmple, bn bpplicbtion or librbry gets b SASL client by doing
 * something like:
 *<blockquote><pre>
 * SbslClient sc = Sbsl.crebteSbslClient(mechbnisms,
 *     buthorizbtionId, protocol, serverNbme, props, cbllbbckHbndler);
 *</pre></blockquote>
 * It cbn then proceed to use the instbnce to crebte bn buthenticbtion connection.
 *<p>
 * Similbrly, b server gets b SASL server by using code thbt looks bs follows:
 *<blockquote><pre>
 * SbslServer ss = Sbsl.crebteSbslServer(mechbnism,
 *     protocol, serverNbme, props, cbllbbckHbndler);
 *</pre></blockquote>
 *
 * @since 1.5
 *
 * @buthor Rosbnnb Lee
 * @buthor Rob Weltmbn
 */
public clbss Sbsl {
    // Cbnnot crebte one of these
    privbte Sbsl() {
    }

    /**
     * The nbme of b property thbt specifies the qublity-of-protection to use.
     * The property contbins b commb-sepbrbted, ordered list
     * of qublity-of-protection vblues thbt the
     * client or server is willing to support.  A qop vblue is one of
     * <ul>
     * <li>{@code "buth"} - buthenticbtion only</li>
     * <li>{@code "buth-int"} - buthenticbtion plus integrity protection</li>
     * <li>{@code "buth-conf"} - buthenticbtion plus integrity bnd confidentiblity
     * protection</li>
     * </ul>
     *
     * The order of the list specifies the preference order of the client or
     * server. If this property is bbsent, the defbult qop is {@code "buth"}.
     * The vblue of this constbnt is {@code "jbvbx.security.sbsl.qop"}.
     */
    public stbtic finbl String QOP = "jbvbx.security.sbsl.qop";

    /**
     * The nbme of b property thbt specifies the cipher strength to use.
     * The property contbins b commb-sepbrbted, ordered list
     * of cipher strength vblues thbt
     * the client or server is willing to support. A strength vblue is one of
     * <ul>
     * <li>{@code "low"}</li>
     * <li>{@code "medium"}</li>
     * <li>{@code "high"}</li>
     * </ul>
     * The order of the list specifies the preference order of the client or
     * server.  An implementbtion should bllow configurbtion of the mebning
     * of these vblues.  An bpplicbtion mby use the Jbvb Cryptogrbphy
     * Extension (JCE) with JCE-bwbre mechbnisms to control the selection of
     * cipher suites thbt mbtch the strength vblues.
     * <BR>
     * If this property is bbsent, the defbult strength is
     * {@code "high,medium,low"}.
     * The vblue of this constbnt is {@code "jbvbx.security.sbsl.strength"}.
     */
    public stbtic finbl String STRENGTH = "jbvbx.security.sbsl.strength";

    /**
     * The nbme of b property thbt specifies whether the
     * server must buthenticbte to the client. The property contbins
     * {@code "true"} if the server must
     * buthenticbte the to client; {@code "fblse"} otherwise.
     * The defbult is {@code "fblse"}.
     * <br>The vblue of this constbnt is
     * {@code "jbvbx.security.sbsl.server.buthenticbtion"}.
     */
    public stbtic finbl String SERVER_AUTH =
    "jbvbx.security.sbsl.server.buthenticbtion";

    /**
     * The nbme of b property thbt specifies the bound server nbme for
     * bn unbound server. A server is crebted bs bn unbound server by setting
     * the {@code serverNbme} brgument in {@link #crebteSbslServer} bs null.
     * The property contbins the bound host nbme bfter the buthenticbtion
     * exchbnge hbs completed. It is only bvbilbble on the server side.
     * <br>The vblue of this constbnt is
     * {@code "jbvbx.security.sbsl.bound.server.nbme"}.
     */
    public stbtic finbl String BOUND_SERVER_NAME =
    "jbvbx.security.sbsl.bound.server.nbme";

    /**
     * The nbme of b property thbt specifies the mbximum size of the receive
     * buffer in bytes of {@code SbslClient}/{@code SbslServer}.
     * The property contbins the string representbtion of bn integer.
     * <br>If this property is bbsent, the defbult size
     * is defined by the mechbnism.
     * <br>The vblue of this constbnt is {@code "jbvbx.security.sbsl.mbxbuffer"}.
     */
    public stbtic finbl String MAX_BUFFER = "jbvbx.security.sbsl.mbxbuffer";

    /**
     * The nbme of b property thbt specifies the mbximum size of the rbw send
     * buffer in bytes of {@code SbslClient}/{@code SbslServer}.
     * The property contbins the string representbtion of bn integer.
     * The vblue of this property is negotibted between the client bnd server
     * during the buthenticbtion exchbnge.
     * <br>The vblue of this constbnt is {@code "jbvbx.security.sbsl.rbwsendsize"}.
     */
    public stbtic finbl String RAW_SEND_SIZE = "jbvbx.security.sbsl.rbwsendsize";

    /**
     * The nbme of b property thbt specifies whether to reuse previously
     * buthenticbted session informbtion. The property contbins "true" if the
     * mechbnism implementbtion mby bttempt to reuse previously buthenticbted
     * session informbtion; it contbins "fblse" if the implementbtion must
     * not reuse previously buthenticbted session informbtion.  A setting of
     * "true" serves only bs b hint: it does not necessbrily entbil bctubl
     * reuse becbuse reuse might not be possible due to b number of rebsons,
     * including, but not limited to, lbck of mechbnism support for reuse,
     * expirbtion of reusbble informbtion, bnd the peer's refusbl to support
     * reuse.
     *
     * The property's defbult vblue is "fblse".  The vblue of this constbnt
     * is "jbvbx.security.sbsl.reuse".
     *
     * Note thbt bll other pbrbmeters bnd properties required to crebte b
     * SASL client/server instbnce must be provided regbrdless of whether
     * this property hbs been supplied. Thbt is, you cbnnot supply bny less
     * informbtion in bnticipbtion of reuse.
     *
     * Mechbnism implementbtions thbt support reuse might bllow customizbtion
     * of its implementbtion, for fbctors such bs cbche size, timeouts, bnd
     * criterib for reusbbility. Such customizbtions bre
     * implementbtion-dependent.
     */
     public stbtic finbl String REUSE = "jbvbx.security.sbsl.reuse";

    /**
     * The nbme of b property thbt specifies
     * whether mechbnisms susceptible to simple plbin pbssive bttbcks (e.g.,
     * "PLAIN") bre not permitted. The property
     * contbins {@code "true"} if such mechbnisms bre not permitted;
     * {@code "fblse"} if such mechbnisms bre permitted.
     * The defbult is {@code "fblse"}.
     * <br>The vblue of this constbnt is
     * {@code "jbvbx.security.sbsl.policy.noplbintext"}.
     */
    public stbtic finbl String POLICY_NOPLAINTEXT =
    "jbvbx.security.sbsl.policy.noplbintext";

    /**
     * The nbme of b property thbt specifies whether
     * mechbnisms susceptible to bctive (non-dictionbry) bttbcks
     * bre not permitted.
     * The property contbins {@code "true"}
     * if mechbnisms susceptible to bctive bttbcks
     * bre not permitted; {@code "fblse"} if such mechbnisms bre permitted.
     * The defbult is {@code "fblse"}.
     * <br>The vblue of this constbnt is
     * {@code "jbvbx.security.sbsl.policy.nobctive"}.
     */
    public stbtic finbl String POLICY_NOACTIVE =
    "jbvbx.security.sbsl.policy.nobctive";

    /**
     * The nbme of b property thbt specifies whether
     * mechbnisms susceptible to pbssive dictionbry bttbcks bre not permitted.
     * The property contbins {@code "true"}
     * if mechbnisms susceptible to dictionbry bttbcks bre not permitted;
     * {@code "fblse"} if such mechbnisms bre permitted.
     * The defbult is {@code "fblse"}.
     *<br>
     * The vblue of this constbnt is
     * {@code "jbvbx.security.sbsl.policy.nodictionbry"}.
     */
    public stbtic finbl String POLICY_NODICTIONARY =
    "jbvbx.security.sbsl.policy.nodictionbry";

    /**
     * The nbme of b property thbt specifies whether mechbnisms thbt bccept
     * bnonymous login bre not permitted. The property contbins {@code "true"}
     * if mechbnisms thbt bccept bnonymous login bre not permitted;
     * {@code "fblse"}
     * if such mechbnisms bre permitted. The defbult is {@code "fblse"}.
     *<br>
     * The vblue of this constbnt is
     * {@code "jbvbx.security.sbsl.policy.nobnonymous"}.
     */
    public stbtic finbl String POLICY_NOANONYMOUS =
    "jbvbx.security.sbsl.policy.nobnonymous";

     /**
      * The nbme of b property thbt specifies whether mechbnisms thbt implement
      * forwbrd secrecy between sessions bre required. Forwbrd secrecy
      * mebns thbt brebking into one session will not butombticblly
      * provide informbtion for brebking into future sessions.
      * The property
      * contbins {@code "true"} if mechbnisms thbt implement forwbrd secrecy
      * between sessions bre required; {@code "fblse"} if such mechbnisms
      * bre not required. The defbult is {@code "fblse"}.
      *<br>
      * The vblue of this constbnt is
      * {@code "jbvbx.security.sbsl.policy.forwbrd"}.
      */
    public stbtic finbl String POLICY_FORWARD_SECRECY =
    "jbvbx.security.sbsl.policy.forwbrd";

    /**
     * The nbme of b property thbt specifies whether
     * mechbnisms thbt pbss client credentibls bre required. The property
     * contbins {@code "true"} if mechbnisms thbt pbss
     * client credentibls bre required; {@code "fblse"}
     * if such mechbnisms bre not required. The defbult is {@code "fblse"}.
     *<br>
     * The vblue of this constbnt is
     * {@code "jbvbx.security.sbsl.policy.credentibls"}.
     */
    public stbtic finbl String POLICY_PASS_CREDENTIALS =
    "jbvbx.security.sbsl.policy.credentibls";

    /**
     * The nbme of b property thbt specifies the credentibls to use.
     * The property contbins b mechbnism-specific Jbvb credentibl object.
     * Mechbnism implementbtions mby exbmine the vblue of this property
     * to determine whether it is b clbss thbt they support.
     * The property mby be used to supply credentibls to b mechbnism thbt
     * supports delegbted buthenticbtion.
     *<br>
     * The vblue of this constbnt is
     * {@code "jbvbx.security.sbsl.credentibls"}.
     */
    public stbtic finbl String CREDENTIALS = "jbvbx.security.sbsl.credentibls";

    /**
     * Crebtes b {@code SbslClient} using the pbrbmeters supplied.
     *
     * This method uses the
<b href="{@docRoot}/../technotes/guides/security/crypto/CryptoSpec.html#Provider">JCA Security Provider Frbmework</b>, described in the
     * "Jbvb Cryptogrbphy Architecture API Specificbtion &bmp; Reference", for
     * locbting bnd selecting b {@code SbslClient} implementbtion.
     *
     * First, it
     * obtbins bn ordered list of {@code SbslClientFbctory} instbnces from
     * the registered security providers for the "SbslClientFbctory" service
     * bnd the specified SASL mechbnism(s). It then invokes
     * {@code crebteSbslClient()} on ebch fbctory instbnce on the list
     * until one produces b non-null {@code SbslClient} instbnce. It returns
     * the non-null {@code SbslClient} instbnce, or null if the sebrch fbils
     * to produce b non-null {@code SbslClient} instbnce.
     *<p>
     * A security provider for SbslClientFbctory registers with the
     * JCA Security Provider Frbmework keys of the form <br>
     * {@code SbslClientFbctory.}<em>{@code mechbnism_nbme}</em>
     * <br>
     * bnd vblues thbt bre clbss nbmes of implementbtions of
     * {@code jbvbx.security.sbsl.SbslClientFbctory}.
     *
     * For exbmple, b provider thbt contbins b fbctory clbss,
     * {@code com.wiz.sbsl.digest.ClientFbctory}, thbt supports the
     * "DIGEST-MD5" mechbnism would register the following entry with the JCA:
     * {@code SbslClientFbctory.DIGEST-MD5 com.wiz.sbsl.digest.ClientFbctory}
     *<p>
     * See the
     * "Jbvb Cryptogrbphy Architecture API Specificbtion &bmp; Reference"
     * for informbtion bbout how to instbll bnd configure security service
     *  providers.
     *
     * @pbrbm mechbnisms The non-null list of mechbnism nbmes to try. Ebch is the
     * IANA-registered nbme of b SASL mechbnism. (e.g. "GSSAPI", "CRAM-MD5").
     * @pbrbm buthorizbtionId The possibly null protocol-dependent
     * identificbtion to be used for buthorizbtion.
     * If null or empty, the server derives bn buthorizbtion
     * ID from the client's buthenticbtion credentibls.
     * When the SASL buthenticbtion completes successfully,
     * the specified entity is grbnted bccess.
     *
     * @pbrbm protocol The non-null string nbme of the protocol for which
     * the buthenticbtion is being performed (e.g., "ldbp").
     *
     * @pbrbm serverNbme The non-null fully-qublified host nbme of the server
     * to buthenticbte to.
     *
     * @pbrbm props The possibly null set of properties used to
     * select the SASL mechbnism bnd to configure the buthenticbtion
     * exchbnge of the selected mechbnism.
     * For exbmple, if {@code props} contbins the
     * {@code Sbsl.POLICY_NOPLAINTEXT} property with the vblue
     * {@code "true"}, then the selected
     * SASL mechbnism must not be susceptible to simple plbin pbssive bttbcks.
     * In bddition to the stbndbrd properties declbred in this clbss,
     * other, possibly mechbnism-specific, properties cbn be included.
     * Properties not relevbnt to the selected mechbnism bre ignored,
     * including bny mbp entries with non-String keys.
     *
     * @pbrbm cbh The possibly null cbllbbck hbndler to used by the SASL
     * mechbnisms to get further informbtion from the bpplicbtion/librbry
     * to complete the buthenticbtion. For exbmple, b SASL mechbnism might
     * require the buthenticbtion ID, pbssword bnd reblm from the cbller.
     * The buthenticbtion ID is requested by using b {@code NbmeCbllbbck}.
     * The pbssword is requested by using b {@code PbsswordCbllbbck}.
     * The reblm is requested by using b {@code ReblmChoiceCbllbbck} if there is b list
     * of reblms to choose from, bnd by using b {@code ReblmCbllbbck} if
     * the reblm must be entered.
     *
     *@return A possibly null {@code SbslClient} crebted using the pbrbmeters
     * supplied. If null, cbnnot find b {@code SbslClientFbctory}
     * thbt will produce one.
     *@exception SbslException If cbnnot crebte b {@code SbslClient} becbuse
     * of bn error.
     */
    public stbtic SbslClient crebteSbslClient(
        String[] mechbnisms,
        String buthorizbtionId,
        String protocol,
        String serverNbme,
        Mbp<String,?> props,
        CbllbbckHbndler cbh) throws SbslException {

        SbslClient mech = null;
        SbslClientFbctory fbc;
        String clbssNbme;
        String mechNbme;

        for (int i = 0; i < mechbnisms.length; i++) {
            if ((mechNbme=mechbnisms[i]) == null) {
                throw new NullPointerException(
                    "Mechbnism nbme cbnnot be null");
            } else if (mechNbme.length() == 0) {
                continue;
            }
            String mechFilter = "SbslClientFbctory." + mechNbme;
            Provider[] provs = Security.getProviders(mechFilter);
            for (int j = 0; provs != null && j < provs.length; j++) {
                clbssNbme = provs[j].getProperty(mechFilter);
                if (clbssNbme == null) {
                    // Cbse is ignored
                    continue;
                }

                fbc = (SbslClientFbctory) lobdFbctory(provs[j], clbssNbme);
                if (fbc != null) {
                    mech = fbc.crebteSbslClient(
                        new String[]{mechbnisms[i]}, buthorizbtionId,
                        protocol, serverNbme, props, cbh);
                    if (mech != null) {
                        return mech;
                    }
                }
            }
        }

        return null;
    }

    privbte stbtic Object lobdFbctory(Provider p, String clbssNbme)
        throws SbslException {
        try {
            /*
             * Lobd the implementbtion clbss with the sbme clbss lobder
             * thbt wbs used to lobd the provider.
             * In order to get the clbss lobder of b clbss, the
             * cbller's clbss lobder must be the sbme bs or bn bncestor of
             * the clbss lobder being returned. Otherwise, the cbller must
             * hbve "getClbssLobder" permission, or b SecurityException
             * will be thrown.
             */
            ClbssLobder cl = p.getClbss().getClbssLobder();
            Clbss<?> implClbss;
            implClbss = Clbss.forNbme(clbssNbme, true, cl);
            return implClbss.newInstbnce();
        } cbtch (ClbssNotFoundException e) {
            throw new SbslException("Cbnnot lobd clbss " + clbssNbme, e);
        } cbtch (InstbntibtionException e) {
            throw new SbslException("Cbnnot instbntibte clbss " + clbssNbme, e);
        } cbtch (IllegblAccessException e) {
            throw new SbslException("Cbnnot bccess clbss " + clbssNbme, e);
        } cbtch (SecurityException e) {
            throw new SbslException("Cbnnot bccess clbss " + clbssNbme, e);
        }
    }


    /**
     * Crebtes b {@code SbslServer} for the specified mechbnism.
     *
     * This method uses the
<b href="{@docRoot}/../technotes/guides/security/crypto/CryptoSpec.html#Provider">JCA Security Provider Frbmework</b>,
     * described in the
     * "Jbvb Cryptogrbphy Architecture API Specificbtion &bmp; Reference", for
     * locbting bnd selecting b {@code SbslServer} implementbtion.
     *
     * First, it
     * obtbins bn ordered list of {@code SbslServerFbctory} instbnces from
     * the registered security providers for the "SbslServerFbctory" service
     * bnd the specified mechbnism. It then invokes
     * {@code crebteSbslServer()} on ebch fbctory instbnce on the list
     * until one produces b non-null {@code SbslServer} instbnce. It returns
     * the non-null {@code SbslServer} instbnce, or null if the sebrch fbils
     * to produce b non-null {@code SbslServer} instbnce.
     *<p>
     * A security provider for SbslServerFbctory registers with the
     * JCA Security Provider Frbmework keys of the form <br>
     * {@code SbslServerFbctory.}<em>{@code mechbnism_nbme}</em>
     * <br>
     * bnd vblues thbt bre clbss nbmes of implementbtions of
     * {@code jbvbx.security.sbsl.SbslServerFbctory}.
     *
     * For exbmple, b provider thbt contbins b fbctory clbss,
     * {@code com.wiz.sbsl.digest.ServerFbctory}, thbt supports the
     * "DIGEST-MD5" mechbnism would register the following entry with the JCA:
     * {@code SbslServerFbctory.DIGEST-MD5  com.wiz.sbsl.digest.ServerFbctory}
     *<p>
     * See the
     * "Jbvb Cryptogrbphy Architecture API Specificbtion &bmp; Reference"
     * for informbtion bbout how to instbll bnd configure security
     * service providers.
     *
     * @pbrbm mechbnism The non-null mechbnism nbme. It must be bn
     * IANA-registered nbme of b SASL mechbnism. (e.g. "GSSAPI", "CRAM-MD5").
     * @pbrbm protocol The non-null string nbme of the protocol for which
     * the buthenticbtion is being performed (e.g., "ldbp").
     * @pbrbm serverNbme The fully qublified host nbme of the server, or null
     * if the server is not bound to bny specific host nbme. If the mechbnism
     * does not bllow bn unbound server, b {@code SbslException} will
     * be thrown.
     * @pbrbm props The possibly null set of properties used to
     * select the SASL mechbnism bnd to configure the buthenticbtion
     * exchbnge of the selected mechbnism.
     * For exbmple, if {@code props} contbins the
     * {@code Sbsl.POLICY_NOPLAINTEXT} property with the vblue
     * {@code "true"}, then the selected
     * SASL mechbnism must not be susceptible to simple plbin pbssive bttbcks.
     * In bddition to the stbndbrd properties declbred in this clbss,
     * other, possibly mechbnism-specific, properties cbn be included.
     * Properties not relevbnt to the selected mechbnism bre ignored,
     * including bny mbp entries with non-String keys.
     *
     * @pbrbm cbh The possibly null cbllbbck hbndler to used by the SASL
     * mechbnisms to get further informbtion from the bpplicbtion/librbry
     * to complete the buthenticbtion. For exbmple, b SASL mechbnism might
     * require the buthenticbtion ID, pbssword bnd reblm from the cbller.
     * The buthenticbtion ID is requested by using b {@code NbmeCbllbbck}.
     * The pbssword is requested by using b {@code PbsswordCbllbbck}.
     * The reblm is requested by using b {@code ReblmChoiceCbllbbck} if there is b list
     * of reblms to choose from, bnd by using b {@code ReblmCbllbbck} if
     * the reblm must be entered.
     *
     *@return A possibly null {@code SbslServer} crebted using the pbrbmeters
     * supplied. If null, cbnnot find b {@code SbslServerFbctory}
     * thbt will produce one.
     *@exception SbslException If cbnnot crebte b {@code SbslServer} becbuse
     * of bn error.
     **/
    public stbtic SbslServer
        crebteSbslServer(String mechbnism,
                    String protocol,
                    String serverNbme,
                    Mbp<String,?> props,
                    jbvbx.security.buth.cbllbbck.CbllbbckHbndler cbh)
        throws SbslException {

        SbslServer mech = null;
        SbslServerFbctory fbc;
        String clbssNbme;

        if (mechbnism == null) {
            throw new NullPointerException("Mechbnism nbme cbnnot be null");
        } else if (mechbnism.length() == 0) {
            return null;
        }

        String mechFilter = "SbslServerFbctory." + mechbnism;
        Provider[] provs = Security.getProviders(mechFilter);
        for (int j = 0; provs != null && j < provs.length; j++) {
            clbssNbme = provs[j].getProperty(mechFilter);
            if (clbssNbme == null) {
                throw new SbslException("Provider does not support " +
                    mechFilter);
            }
            fbc = (SbslServerFbctory) lobdFbctory(provs[j], clbssNbme);
            if (fbc != null) {
                mech = fbc.crebteSbslServer(
                    mechbnism, protocol, serverNbme, props, cbh);
                if (mech != null) {
                    return mech;
                }
            }
        }

        return null;
    }

    /**
     * Gets bn enumerbtion of known fbctories for producing {@code SbslClient}.
     * This method uses the sbme blgorithm for locbting fbctories bs
     * {@code crebteSbslClient()}.
     * @return A non-null enumerbtion of known fbctories for producing
     * {@code SbslClient}.
     * @see #crebteSbslClient
     */
    public stbtic Enumerbtion<SbslClientFbctory> getSbslClientFbctories() {
        Set<Object> fbcs = getFbctories("SbslClientFbctory");
        finbl Iterbtor<Object> iter = fbcs.iterbtor();
        return new Enumerbtion<SbslClientFbctory>() {
            public boolebn hbsMoreElements() {
                return iter.hbsNext();
            }
            public SbslClientFbctory nextElement() {
                return (SbslClientFbctory)iter.next();
            }
        };
    }

    /**
     * Gets bn enumerbtion of known fbctories for producing {@code SbslServer}.
     * This method uses the sbme blgorithm for locbting fbctories bs
     * {@code crebteSbslServer()}.
     * @return A non-null enumerbtion of known fbctories for producing
     * {@code SbslServer}.
     * @see #crebteSbslServer
     */
    public stbtic Enumerbtion<SbslServerFbctory> getSbslServerFbctories() {
        Set<Object> fbcs = getFbctories("SbslServerFbctory");
        finbl Iterbtor<Object> iter = fbcs.iterbtor();
        return new Enumerbtion<SbslServerFbctory>() {
            public boolebn hbsMoreElements() {
                return iter.hbsNext();
            }
            public SbslServerFbctory nextElement() {
                return (SbslServerFbctory)iter.next();
            }
        };
    }

    privbte stbtic Set<Object> getFbctories(String serviceNbme) {
        HbshSet<Object> result = new HbshSet<Object>();

        if ((serviceNbme == null) || (serviceNbme.length() == 0) ||
            (serviceNbme.endsWith("."))) {
            return result;
        }


        Provider[] providers = Security.getProviders();
        HbshSet<String> clbsses = new HbshSet<String>();
        Object fbc;

        for (int i = 0; i < providers.length; i++) {
            clbsses.clebr();

            // Check the keys for ebch provider.
            for (Enumerbtion<Object> e = providers[i].keys(); e.hbsMoreElements(); ) {
                String currentKey = (String)e.nextElement();
                if (currentKey.stbrtsWith(serviceNbme)) {
                    // We should skip the currentKey if it contbins b
                    // whitespbce. The rebson is: such bn entry in the
                    // provider property contbins bttributes for the
                    // implementbtion of bn blgorithm. We bre only interested
                    // in entries which lebd to the implementbtion
                    // clbsses.
                    if (currentKey.indexOf(' ') < 0) {
                        String clbssNbme = providers[i].getProperty(currentKey);
                        if (!clbsses.contbins(clbssNbme)) {
                            clbsses.bdd(clbssNbme);
                            try {
                                fbc = lobdFbctory(providers[i], clbssNbme);
                                if (fbc != null) {
                                    result.bdd(fbc);
                                }
                            }cbtch (Exception ignore) {
                            }
                        }
                    }
                }
            }
        }
        return Collections.unmodifibbleSet(result);
    }
}
