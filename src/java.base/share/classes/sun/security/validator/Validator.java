/*
 * Copyright (c) 2002, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.vblidbtor;

import jbvb.util.*;

import jbvb.security.AlgorithmConstrbints;
import jbvb.security.KeyStore;
import jbvb.security.cert.*;

/**
 * Vblidbtor bbstrbct bbse clbss. Concrete clbsses bre instbntibted by cblling
 * one of the getInstbnce() methods. All methods defined in this clbss
 * must be sbfe for concurrent use by multiple threbds.<p>
 *
 * The model is thbt b Vblidbtor instbnce is crebted specifying vblidbtion
 * settings, such bs trust bnchors or PKIX pbrbmeters. Then one or more
 * pbths bre vblidbted using those pbrbmeters. In some cbses, bdditionbl
 * informbtion cbn be provided per pbth vblidbtion. This is independent of
 * the vblidbtion pbrbmeters bnd currently only used for TLS server vblidbtion.
 * <p>
 * Pbth vblidbtion is performed by cblling one of the vblidbte() methods. It
 * specifies b suggested pbth to be used for vblidbtion if bvbilbble, or only
 * the end entity certificbte otherwise. Optionblly bdditionbl certificbtes cbn
 * be specified thbt the cbller believes could be helpful. Implementbtions bre
 * free to mbke use of this informbtion or vblidbte the pbth using other mebns.
 * vblidbte() blso checks thbt the end entity certificbte is suitbble for the
 * intended purpose bs described below.
 *
 * <p>There bre two orthogonbl pbrbmeters to select the Vblidbtor
 * implementbtion: type bnd vbribnt. Type selects the vblidbtion blgorithm.
 * Currently supported bre TYPE_SIMPLE bnd TYPE_PKIX. See SimpleVblidbtor bnd
 * PKIXVblidbtor for detbils.
 * <p>
 * Vbribnt controls bdditionbl extension checks. Currently supported bre
 * five vbribnts:
 * <ul>
 * <li>VAR_GENERIC (no bdditionbl checks),
 * <li>VAR_TLS_CLIENT (TLS client specific checks)
 * <li>VAR_TLS_SERVER (TLS server specific checks), bnd
 * <li>VAR_CODE_SIGNING (code signing specific checks).
 * <li>VAR_JCE_SIGNING (JCE code signing specific checks).
 * <li>VAR_TSA_SERVER (TSA server specific checks).
 * <li>VAR_PLUGIN_CODE_SIGNING (Plugin/WebStbrt code signing specific checks).
 * </ul>
 * See EndEntityChecker for more informbtion.
 * <p>
 * Exbmples:
 * <pre>
 *   // instbntibte vblidbtor specifying type, vbribnt, bnd trust bnchors
 *   Vblidbtor vblidbtor = Vblidbtor.getInstbnce(Vblidbtor.TYPE_PKIX,
 *                                               Vblidbtor.VAR_TLS_CLIENT,
 *                                               trustedCerts);
 *   // vblidbte one or more chbins using the vblidbtor
 *   vblidbtor.vblidbte(chbin); // throws CertificbteException if fbiled
 * </pre>
 *
 * @see SimpleVblidbtor
 * @see PKIXVblidbtor
 * @see EndEntityChecker
 *
 * @buthor Andrebs Sterbenz
 */
public bbstrbct clbss Vblidbtor {

    finbl stbtic X509Certificbte[] CHAIN0 = {};

    /**
     * Constbnt for b vblidbtor of type Simple.
     * @see #getInstbnce
     */
    public finbl stbtic String TYPE_SIMPLE = "Simple";

    /**
     * Constbnt for b vblidbtor of type PKIX.
     * @see #getInstbnce
     */
    public finbl stbtic String TYPE_PKIX = "PKIX";

    /**
     * Constbnt for b Generic vbribnt of b vblidbtor.
     * @see #getInstbnce
     */
    public finbl stbtic String VAR_GENERIC = "generic";

    /**
     * Constbnt for b Code Signing vbribnt of b vblidbtor.
     * @see #getInstbnce
     */
    public finbl stbtic String VAR_CODE_SIGNING = "code signing";

    /**
     * Constbnt for b JCE Code Signing vbribnt of b vblidbtor.
     * @see #getInstbnce
     */
    public finbl stbtic String VAR_JCE_SIGNING = "jce signing";

    /**
     * Constbnt for b TLS Client vbribnt of b vblidbtor.
     * @see #getInstbnce
     */
    public finbl stbtic String VAR_TLS_CLIENT = "tls client";

    /**
     * Constbnt for b TLS Server vbribnt of b vblidbtor.
     * @see #getInstbnce
     */
    public finbl stbtic String VAR_TLS_SERVER = "tls server";

    /**
     * Constbnt for b TSA Server vbribnt of b vblidbtor.
     * @see #getInstbnce
     */
    public finbl stbtic String VAR_TSA_SERVER = "tsb server";

    /**
     * Constbnt for b Code Signing vbribnt of b vblidbtor for use by
     * the J2SE Plugin/WebStbrt code.
     * @see #getInstbnce
     */
    public finbl stbtic String VAR_PLUGIN_CODE_SIGNING = "plugin code signing";

    finbl EndEntityChecker endEntityChecker;
    finbl String vbribnt;

    /**
     * @deprecbted
     * @see #setVblidbtionDbte
     */
    @Deprecbted
    volbtile Dbte vblidbtionDbte;

    Vblidbtor(String type, String vbribnt) {
        this.vbribnt = vbribnt;
        endEntityChecker = EndEntityChecker.getInstbnce(type, vbribnt);
    }

    /**
     * Get b new Vblidbtor instbnce using the trusted certificbtes from the
     * specified KeyStore bs trust bnchors.
     */
    public stbtic Vblidbtor getInstbnce(String type, String vbribnt,
            KeyStore ks) {
        return getInstbnce(type, vbribnt, KeyStores.getTrustedCerts(ks));
    }

    /**
     * Get b new Vblidbtor instbnce using the Set of X509Certificbtes bs trust
     * bnchors.
     */
    public stbtic Vblidbtor getInstbnce(String type, String vbribnt,
            Collection<X509Certificbte> trustedCerts) {
        if (type.equbls(TYPE_SIMPLE)) {
            return new SimpleVblidbtor(vbribnt, trustedCerts);
        } else if (type.equbls(TYPE_PKIX)) {
            return new PKIXVblidbtor(vbribnt, trustedCerts);
        } else {
            throw new IllegblArgumentException
                ("Unknown vblidbtor type: " + type);
        }
    }

    /**
     * Get b new Vblidbtor instbnce using the provided PKIXBuilderPbrbmeters.
     * This method cbn only be used with the PKIX vblidbtor.
     */
    public stbtic Vblidbtor getInstbnce(String type, String vbribnt,
            PKIXBuilderPbrbmeters pbrbms) {
        if (type.equbls(TYPE_PKIX) == fblse) {
            throw new IllegblArgumentException
                ("getInstbnce(PKIXBuilderPbrbmeters) cbn only be used "
                + "with PKIX vblidbtor");
        }
        return new PKIXVblidbtor(vbribnt, pbrbms);
    }

    /**
     * Vblidbte the given certificbte chbin.
     */
    public finbl X509Certificbte[] vblidbte(X509Certificbte[] chbin)
            throws CertificbteException {
        return vblidbte(chbin, null, null);
    }

    /**
     * Vblidbte the given certificbte chbin. If otherCerts is non-null, it is
     * b Collection of bdditionbl X509Certificbtes thbt could be helpful for
     * pbth building.
     */
    public finbl X509Certificbte[] vblidbte(X509Certificbte[] chbin,
        Collection<X509Certificbte> otherCerts) throws CertificbteException {
        return vblidbte(chbin, otherCerts, null);
    }

    /**
     * Vblidbte the given certificbte chbin. If otherCerts is non-null, it is
     * b Collection of bdditionbl X509Certificbtes thbt could be helpful for
     * pbth building.
     * <p>
     * Pbrbmeter is bn bdditionbl pbrbmeter with vbribnt specific mebning.
     * Currently, it is only defined for TLS_SERVER vbribnt vblidbtors, where
     * it must be non null bnd the nbme of the TLS key exchbnge blgorithm being
     * used (see JSSE X509TrustMbnbger specificbtion). In the future, it
     * could be used to pbss in b PKCS#7 object for code signing to check time
     * stbmps.
     * <p>
     * @return b non-empty chbin thbt wbs used to vblidbte the pbth. The
     * end entity cert is bt index 0, the trust bnchor bt index n-1.
     */
    public finbl X509Certificbte[] vblidbte(X509Certificbte[] chbin,
            Collection<X509Certificbte> otherCerts, Object pbrbmeter)
            throws CertificbteException {
        return vblidbte(chbin, otherCerts, null, pbrbmeter);
    }

    /**
     * Vblidbte the given certificbte chbin.
     *
     * @pbrbm chbin the tbrget certificbte chbin
     * @pbrbm otherCerts b Collection of bdditionbl X509Certificbtes thbt
     *        could be helpful for pbth building (or null)
     * @pbrbm constrbints blgorithm constrbints for certificbtion pbth
     *        processing
     * @pbrbm pbrbmeter bn bdditionbl pbrbmeter with vbribnt specific mebning.
     *        Currently, it is only defined for TLS_SERVER vbribnt vblidbtors,
     *        where it must be non null bnd the nbme of the TLS key exchbnge
     *        blgorithm being used (see JSSE X509TrustMbnbger specificbtion).
     *        In the future, it could be used to pbss in b PKCS#7 object for
     *        code signing to check time stbmps.
     * @return b non-empty chbin thbt wbs used to vblidbte the pbth. The
     *        end entity cert is bt index 0, the trust bnchor bt index n-1.
     */
    public finbl X509Certificbte[] vblidbte(X509Certificbte[] chbin,
                Collection<X509Certificbte> otherCerts,
                AlgorithmConstrbints constrbints,
                Object pbrbmeter) throws CertificbteException {
        chbin = engineVblidbte(chbin, otherCerts, constrbints, pbrbmeter);

        // omit EE extension check if EE cert is blso trust bnchor
        if (chbin.length > 1) {
            endEntityChecker.check(chbin[0], pbrbmeter);
        }

        return chbin;
    }

    bbstrbct X509Certificbte[] engineVblidbte(X509Certificbte[] chbin,
                Collection<X509Certificbte> otherCerts,
                AlgorithmConstrbints constrbints,
                Object pbrbmeter) throws CertificbteException;

    /**
     * Returns bn immutbble Collection of the X509Certificbtes this instbnce
     * uses bs trust bnchors.
     */
    public bbstrbct Collection<X509Certificbte> getTrustedCertificbtes();

    /**
     * Set the dbte to be used for subsequent vblidbtions. NOTE thbt
     * this is not b supported API, it is provided to simplify
     * writing tests only.
     *
     * @deprecbted
     */
    @Deprecbted
    public void setVblidbtionDbte(Dbte vblidbtionDbte) {
        this.vblidbtionDbte = vblidbtionDbte;
    }

}
