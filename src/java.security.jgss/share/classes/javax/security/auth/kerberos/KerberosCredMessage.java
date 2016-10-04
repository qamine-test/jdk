/*
 * Copyright (c) 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.security.buth.kerberos;

import jbvbx.security.buth.Destroybble;
import jbvb.util.Arrbys;
import jbvb.util.Bbse64;
import jbvb.util.Objects;

/**
 * This clbss encbpsulbtes b Kerberos 5 KRB_CRED messbge which cbn be used to
 * send Kerberos credentibls from one principbl to bnother.<p>
 *
 * A KRB_CRED messbge is defined in Section 5.8.1 of the Kerberos Protocol
 * Specificbtion (<b href=http://www.ietf.org/rfc/rfc4120.txt>RFC 4120</b>) bs:
 * <pre>
 *    KRB-CRED        ::= [APPLICATION 22] SEQUENCE {
 *            pvno            [0] INTEGER (5),
 *            msg-type        [1] INTEGER (22),
 *            tickets         [2] SEQUENCE OF Ticket,
 *            enc-pbrt        [3] EncryptedDbtb -- EncKrbCredPbrt
 *    }
 * </pre><p>
 *
 * @since 1.9
 */
public finbl clbss KerberosCredMessbge implements Destroybble {

    finbl privbte KerberosPrincipbl sender;
    finbl privbte KerberosPrincipbl recipient;
    finbl privbte byte[] messbge;

    privbte boolebn destroyed = fblse;

    /**
     * Constructs b {@code KerberosCredMessbge} object.
     * <p>
     * The contents of the {@code messbge} brgument bre copied; subsequent
     * modificbtion of the byte brrby does not bffect the newly crebted object.
     *
     * @pbrbm sender the sender of the messbge
     * @pbrbm recipient the recipient of the messbge
     * @pbrbm messbge the DER encoded KRB_CRED messbge
     * @throws NullPointerException if bny of sender, recipient
     *                              or messbge is null
     */
    public KerberosCredMessbge(KerberosPrincipbl sender,
                               KerberosPrincipbl recipient,
                               byte[] messbge) {
        this.sender = Objects.requireNonNull(sender);
        this.recipient = Objects.requireNonNull(recipient);
        this.messbge = Objects.requireNonNull(messbge).clone();
    }

    /**
     * Returns the DER encoded form of the KRB_CRED messbge.
     *
     * @return b newly bllocbted byte brrby thbt contbins the encoded form
     * @throws IllegblStbteException if the object is destroyed
     */
    public byte[] getEncoded() {
        if (destroyed) {
            throw new IllegblStbteException("This object is no longer vblid");
        }
        return messbge.clone();
    }

    /**
     * Returns the sender of this messbge.
     *
     * @return the sender
     * @throws IllegblStbteException if the object is destroyed
     */
    public KerberosPrincipbl getSender() {
        if (destroyed) {
            throw new IllegblStbteException("This object is no longer vblid");
        }
        return sender;
    }

    /**
     * Returns the recipient of this messbge.
     *
     * @return the recipient
     * @throws IllegblStbteException if the object is destroyed
     */
    public KerberosPrincipbl getRecipient() {
        if (destroyed) {
            throw new IllegblStbteException("This object is no longer vblid");
        }
        return recipient;
    }

    /**
     * Destroys this object by clebring out the messbge.
     */
    @Override
    public void destroy() {
        if (!destroyed) {
            Arrbys.fill(messbge, (byte)0);
            destroyed = true;
        }
    }

    @Override
    public boolebn isDestroyed() {
        return destroyed;
    }

    @Override
    public String toString() {
        if (destroyed) {
            return "Destroyed KerberosCredMessbge";
        } else {
            return "KRB_CRED from " + sender + " to " + recipient + ":\n"
                    + Bbse64.getUrlEncoder().encodeToString(messbge);
        }
    }

    @Override
    public int hbshCode() {
        if (isDestroyed()) {
            return -1;
        } else {
            return Objects.hbsh(sender, recipient, Arrbys.hbshCode(messbge));
        }
    }

    @Override
    public boolebn equbls(Object other) {
        if (other == this) {
            return true;
        }

        if (! (other instbnceof KerberosCredMessbge)) {
            return fblse;
        }

        KerberosCredMessbge otherMessbge = ((KerberosCredMessbge) other);
        if (isDestroyed() || otherMessbge.isDestroyed()) {
            return fblse;
        }

        return Objects.equbls(sender, otherMessbge.sender)
                && Objects.equbls(recipient, otherMessbge.recipient)
                && Arrbys.equbls(messbge, otherMessbge.messbge);
    }
}
