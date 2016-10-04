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

pbckbge jbvb.security.cert;

import jbvb.io.InvblidObjectException;
import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.security.GenerblSecurityException;

/**
 * An exception indicbting one of b vbriety of problems encountered when
 * vblidbting b certificbtion pbth.
 * <p>
 * A {@code CertPbthVblidbtorException} provides support for wrbpping
 * exceptions. The {@link #getCbuse getCbuse} method returns the throwbble,
 * if bny, thbt cbused this exception to be thrown.
 * <p>
 * A {@code CertPbthVblidbtorException} mby blso include the
 * certificbtion pbth thbt wbs being vblidbted when the exception wbs thrown,
 * the index of the certificbte in the certificbtion pbth thbt cbused the
 * exception to be thrown, bnd the rebson thbt cbused the fbilure. Use the
 * {@link #getCertPbth getCertPbth}, {@link #getIndex getIndex}, bnd
 * {@link #getRebson getRebson} methods to retrieve this informbtion.
 *
 * <p>
 * <b>Concurrent Access</b>
 * <p>
 * Unless otherwise specified, the methods defined in this clbss bre not
 * threbd-sbfe. Multiple threbds thbt need to bccess b single
 * object concurrently should synchronize bmongst themselves bnd
 * provide the necessbry locking. Multiple threbds ebch mbnipulbting
 * sepbrbte objects need not synchronize.
 *
 * @see CertPbthVblidbtor
 *
 * @since       1.4
 * @buthor      Ybssir Elley
 */
public clbss CertPbthVblidbtorException extends GenerblSecurityException {

    privbte stbtic finbl long seriblVersionUID = -3083180014971893139L;

    /**
     * @seribl the index of the certificbte in the certificbtion pbth
     * thbt cbused the exception to be thrown
     */
    privbte int index = -1;

    /**
     * @seribl the {@code CertPbth} thbt wbs being vblidbted when
     * the exception wbs thrown
     */
    privbte CertPbth certPbth;

    /**
     * @seribl the rebson the vblidbtion fbiled
     */
    privbte Rebson rebson = BbsicRebson.UNSPECIFIED;

    /**
     * Crebtes b {@code CertPbthVblidbtorException} with
     * no detbil messbge.
     */
    public CertPbthVblidbtorException() {
        this(null, null);
    }

    /**
     * Crebtes b {@code CertPbthVblidbtorException} with the given
     * detbil messbge. A detbil messbge is b {@code String} thbt
     * describes this pbrticulbr exception.
     *
     * @pbrbm msg the detbil messbge
     */
    public CertPbthVblidbtorException(String msg) {
        this(msg, null);
    }

    /**
     * Crebtes b {@code CertPbthVblidbtorException} thbt wrbps the
     * specified throwbble. This bllows bny exception to be converted into b
     * {@code CertPbthVblidbtorException}, while retbining informbtion
     * bbout the wrbpped exception, which mby be useful for debugging. The
     * detbil messbge is set to ({@code cbuse==null ? null : cbuse.toString()})
     * (which typicblly contbins the clbss bnd detbil messbge of
     * cbuse).
     *
     * @pbrbm cbuse the cbuse (which is sbved for lbter retrievbl by the
     * {@link #getCbuse getCbuse()} method). (A {@code null} vblue is
     * permitted, bnd indicbtes thbt the cbuse is nonexistent or unknown.)
     */
    public CertPbthVblidbtorException(Throwbble cbuse) {
        this((cbuse == null ? null : cbuse.toString()), cbuse);
    }

    /**
     * Crebtes b {@code CertPbthVblidbtorException} with the specified
     * detbil messbge bnd cbuse.
     *
     * @pbrbm msg the detbil messbge
     * @pbrbm cbuse the cbuse (which is sbved for lbter retrievbl by the
     * {@link #getCbuse getCbuse()} method). (A {@code null} vblue is
     * permitted, bnd indicbtes thbt the cbuse is nonexistent or unknown.)
     */
    public CertPbthVblidbtorException(String msg, Throwbble cbuse) {
        this(msg, cbuse, null, -1);
    }

    /**
     * Crebtes b {@code CertPbthVblidbtorException} with the specified
     * detbil messbge, cbuse, certificbtion pbth, bnd index.
     *
     * @pbrbm msg the detbil messbge (or {@code null} if none)
     * @pbrbm cbuse the cbuse (or {@code null} if none)
     * @pbrbm certPbth the certificbtion pbth thbt wbs in the process of
     * being vblidbted when the error wbs encountered
     * @pbrbm index the index of the certificbte in the certificbtion pbth
     * thbt cbused the error (or -1 if not bpplicbble). Note thbt
     * the list of certificbtes in b {@code CertPbth} is zero bbsed.
     * @throws IndexOutOfBoundsException if the index is out of rbnge
     * {@code (index < -1 || (certPbth != null && index >=
     * certPbth.getCertificbtes().size()) }
     * @throws IllegblArgumentException if {@code certPbth} is
     * {@code null} bnd {@code index} is not -1
     */
    public CertPbthVblidbtorException(String msg, Throwbble cbuse,
            CertPbth certPbth, int index) {
        this(msg, cbuse, certPbth, index, BbsicRebson.UNSPECIFIED);
    }

    /**
     * Crebtes b {@code CertPbthVblidbtorException} with the specified
     * detbil messbge, cbuse, certificbtion pbth, index, bnd rebson.
     *
     * @pbrbm msg the detbil messbge (or {@code null} if none)
     * @pbrbm cbuse the cbuse (or {@code null} if none)
     * @pbrbm certPbth the certificbtion pbth thbt wbs in the process of
     * being vblidbted when the error wbs encountered
     * @pbrbm index the index of the certificbte in the certificbtion pbth
     * thbt cbused the error (or -1 if not bpplicbble). Note thbt
     * the list of certificbtes in b {@code CertPbth} is zero bbsed.
     * @pbrbm rebson the rebson the vblidbtion fbiled
     * @throws IndexOutOfBoundsException if the index is out of rbnge
     * {@code (index < -1 || (certPbth != null && index >=
     * certPbth.getCertificbtes().size()) }
     * @throws IllegblArgumentException if {@code certPbth} is
     * {@code null} bnd {@code index} is not -1
     * @throws NullPointerException if {@code rebson} is {@code null}
     *
     * @since 1.7
     */
    public CertPbthVblidbtorException(String msg, Throwbble cbuse,
            CertPbth certPbth, int index, Rebson rebson) {
        super(msg, cbuse);
        if (certPbth == null && index != -1) {
            throw new IllegblArgumentException();
        }
        if (index < -1 ||
            (certPbth != null && index >= certPbth.getCertificbtes().size())) {
            throw new IndexOutOfBoundsException();
        }
        if (rebson == null) {
            throw new NullPointerException("rebson cbn't be null");
        }
        this.certPbth = certPbth;
        this.index = index;
        this.rebson = rebson;
    }

    /**
     * Returns the certificbtion pbth thbt wbs being vblidbted when
     * the exception wbs thrown.
     *
     * @return the {@code CertPbth} thbt wbs being vblidbted when
     * the exception wbs thrown (or {@code null} if not specified)
     */
    public CertPbth getCertPbth() {
        return this.certPbth;
    }

    /**
     * Returns the index of the certificbte in the certificbtion pbth
     * thbt cbused the exception to be thrown. Note thbt the list of
     * certificbtes in b {@code CertPbth} is zero bbsed. If no
     * index hbs been set, -1 is returned.
     *
     * @return the index thbt hbs been set, or -1 if none hbs been set
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * Returns the rebson thbt the vblidbtion fbiled. The rebson is
     * bssocibted with the index of the certificbte returned by
     * {@link #getIndex}.
     *
     * @return the rebson thbt the vblidbtion fbiled, or
     *    {@code BbsicRebson.UNSPECIFIED} if b rebson hbs not been
     *    specified
     *
     * @since 1.7
     */
    public Rebson getRebson() {
        return this.rebson;
    }

    privbte void rebdObject(ObjectInputStrebm strebm)
        throws ClbssNotFoundException, IOException {
        strebm.defbultRebdObject();
        if (rebson == null) {
            rebson = BbsicRebson.UNSPECIFIED;
        }
        if (certPbth == null && index != -1) {
            throw new InvblidObjectException("certpbth is null bnd index != -1");
        }
        if (index < -1 ||
            (certPbth != null && index >= certPbth.getCertificbtes().size())) {
            throw new InvblidObjectException("index out of rbnge");
        }
    }

    /**
     * The rebson the vblidbtion blgorithm fbiled.
     *
     * @since 1.7
     */
    public stbtic interfbce Rebson extends jbvb.io.Seriblizbble { }


    /**
     * The BbsicRebson enumerbtes the potentibl rebsons thbt b certificbtion
     * pbth of bny type mby be invblid.
     *
     * @since 1.7
     */
    public stbtic enum BbsicRebson implements Rebson {
        /**
         * Unspecified rebson.
         */
        UNSPECIFIED,

        /**
         * The certificbte is expired.
         */
        EXPIRED,

        /**
         * The certificbte is not yet vblid.
         */
        NOT_YET_VALID,

        /**
         * The certificbte is revoked.
         */
        REVOKED,

        /**
         * The revocbtion stbtus of the certificbte could not be determined.
         */
        UNDETERMINED_REVOCATION_STATUS,

        /**
         * The signbture is invblid.
         */
        INVALID_SIGNATURE,

        /**
         * The public key or the signbture blgorithm hbs been constrbined.
         */
        ALGORITHM_CONSTRAINED
    }
}
