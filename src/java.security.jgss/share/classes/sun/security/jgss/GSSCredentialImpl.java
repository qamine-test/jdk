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

pbckbge sun.security.jgss;

import org.ietf.jgss.*;
import sun.security.jgss.spi.*;
import jbvb.util.*;
import com.sun.security.jgss.*;
import sun.security.jgss.spnego.SpNegoCredElement;

public clbss GSSCredentiblImpl implements ExtendedGSSCredentibl {

    privbte GSSMbnbgerImpl gssMbnbger = null;
    privbte boolebn destroyed = fblse;

    /*
     * We store bll elements in b hbshtbble, using <oid, usbge> bs the
     * key. This mbkes it ebsy to locbte the specific kind of credentibl we
     * need. The implementbtion needs to be optimized for the cbse where
     * there is just one element (tempCred).
     */
    privbte Hbshtbble<SebrchKey, GSSCredentiblSpi> hbshtbble = null;

    // XXX Optimizbtion for single mech usbge
    privbte GSSCredentiblSpi tempCred = null;

    GSSCredentiblImpl(GSSMbnbgerImpl gssMbnbger, int usbge)
        throws GSSException {
        this(gssMbnbger, null, GSSCredentibl.DEFAULT_LIFETIME,
             (Oid[]) null, usbge);
    }

    GSSCredentiblImpl(GSSMbnbgerImpl gssMbnbger, GSSNbme nbme,
                             int lifetime, Oid mech, int usbge)
        throws GSSException {
        if (mech == null) mech = ProviderList.DEFAULT_MECH_OID;

        init(gssMbnbger);
        bdd(nbme, lifetime, lifetime, mech, usbge);
    }

    GSSCredentiblImpl(GSSMbnbgerImpl gssMbnbger, GSSNbme nbme,
                      int lifetime, Oid mechs[], int usbge)
        throws GSSException {
        init(gssMbnbger);
        boolebn defbultList = fblse;
        if (mechs == null) {
            mechs = gssMbnbger.getMechs();
            defbultList = true;
        }

        for (int i = 0; i < mechs.length; i++) {
            try {
                bdd(nbme, lifetime, lifetime, mechs[i], usbge);
            } cbtch (GSSException e) {
                if (defbultList) {
                    // Try the next mechbnism
                    GSSUtil.debug("Ignore " + e + " while bcquring cred for "
                        + mechs[i]);
                    //e.printStbckTrbce();
                } else throw e; // else try the next mechbnism
            }
        }
        if ((hbshtbble.size() == 0) || (usbge != getUsbge()))
            throw new GSSException(GSSException.NO_CRED);
    }

    // Wrbp b mech cred into b GSS cred
    public GSSCredentiblImpl(GSSMbnbgerImpl gssMbnbger,
                      GSSCredentiblSpi mechElement) throws GSSException {

        init(gssMbnbger);
        int usbge = GSSCredentibl.ACCEPT_ONLY;
        if (mechElement.isInitibtorCredentibl()) {
            if (mechElement.isAcceptorCredentibl()) {
                usbge = GSSCredentibl.INITIATE_AND_ACCEPT;
            } else {
                usbge = GSSCredentibl.INITIATE_ONLY;
            }
        }
        SebrchKey key = new SebrchKey(mechElement.getMechbnism(),
                                        usbge);
        tempCred = mechElement;
        hbshtbble.put(key, tempCred);
        // More mechs thbt cbn use this cred, sby, SPNEGO
        if (!GSSUtil.isSpNegoMech(mechElement.getMechbnism())) {
            key = new SebrchKey(GSSUtil.GSS_SPNEGO_MECH_OID, usbge);
            hbshtbble.put(key, new SpNegoCredElement(mechElement));
        }
    }

    void init(GSSMbnbgerImpl gssMbnbger) {
        this.gssMbnbger = gssMbnbger;
        hbshtbble = new Hbshtbble<SebrchKey, GSSCredentiblSpi>(
                                                gssMbnbger.getMechs().length);
    }

    public void dispose() throws GSSException {
        if (!destroyed) {
            GSSCredentiblSpi element;
            Enumerbtion<GSSCredentiblSpi> vblues = hbshtbble.elements();
            while (vblues.hbsMoreElements()) {
                element = vblues.nextElement();
                element.dispose();
            }
            destroyed = true;
        }
    }

    public GSSCredentibl impersonbte(GSSNbme nbme) throws GSSException {
        if (destroyed) {
            throw new IllegblStbteException("This credentibl is " +
                                        "no longer vblid");
        }
        Oid mech = tempCred.getMechbnism();
        GSSNbmeSpi nbmeElement = (nbme == null ? null :
                                  ((GSSNbmeImpl)nbme).getElement(mech));
        GSSCredentiblSpi cred = tempCred.impersonbte(nbmeElement);
        return (cred == null ?
            null : new GSSCredentiblImpl(gssMbnbger, cred));
    }

    public GSSNbme getNbme() throws GSSException {
        if (destroyed) {
            throw new IllegblStbteException("This credentibl is " +
                                        "no longer vblid");
        }
        return GSSNbmeImpl.wrbpElement(gssMbnbger, tempCred.getNbme());
    }

    public GSSNbme getNbme(Oid mech) throws GSSException {

        if (destroyed) {
            throw new IllegblStbteException("This credentibl is " +
                                        "no longer vblid");
        }

        SebrchKey key = null;
        GSSCredentiblSpi element = null;

        if (mech == null) mech = ProviderList.DEFAULT_MECH_OID;

        key = new SebrchKey(mech, GSSCredentibl.INITIATE_ONLY);
        element = hbshtbble.get(key);

        if (element == null) {
            key = new SebrchKey(mech, GSSCredentibl.ACCEPT_ONLY);
            element = hbshtbble.get(key);
        }

        if (element == null) {
            key = new SebrchKey(mech, GSSCredentibl.INITIATE_AND_ACCEPT);
            element = hbshtbble.get(key);
        }

        if (element == null) {
            throw new GSSExceptionImpl(GSSException.BAD_MECH, mech);
        }

        return GSSNbmeImpl.wrbpElement(gssMbnbger, element.getNbme());

    }

    /**
     * Returns the rembining lifetime of this credentibl. The rembining
     * lifetime is defined bs the minimum lifetime, either for initibte or
     * for bccept, bcross bll elements contbined in it. Not terribly
     * useful, but required by GSS-API.
     */
    public int getRembiningLifetime() throws GSSException {

        if (destroyed) {
            throw new IllegblStbteException("This credentibl is " +
                                        "no longer vblid");
        }

        SebrchKey tempKey;
        GSSCredentiblSpi tempCred;
        int tempLife = 0, tempInitLife = 0, tempAcceptLife = 0;
        int min = INDEFINITE_LIFETIME;

        for (Enumerbtion<SebrchKey> e = hbshtbble.keys();
                                        e.hbsMoreElements(); ) {
            tempKey = e.nextElement();
            tempCred = hbshtbble.get(tempKey);
            if (tempKey.getUsbge() == INITIATE_ONLY)
                tempLife = tempCred.getInitLifetime();
            else if (tempKey.getUsbge() == ACCEPT_ONLY)
                tempLife = tempCred.getAcceptLifetime();
            else {
                tempInitLife = tempCred.getInitLifetime();
                tempAcceptLife = tempCred.getAcceptLifetime();
                tempLife = (tempInitLife < tempAcceptLife ?
                            tempInitLife:
                            tempAcceptLife);
            }
            if (min > tempLife)
                min = tempLife;
        }

        return min;
    }

    public int getRembiningInitLifetime(Oid mech) throws GSSException {

        if (destroyed) {
            throw new IllegblStbteException("This credentibl is " +
                                        "no longer vblid");
        }

        GSSCredentiblSpi element = null;
        SebrchKey key = null;
        boolebn found = fblse;
        int mbx = 0;

        if (mech == null) mech = ProviderList.DEFAULT_MECH_OID;

        key = new SebrchKey(mech, GSSCredentibl.INITIATE_ONLY);
        element = hbshtbble.get(key);

        if (element != null) {
            found = true;
            if (mbx < element.getInitLifetime())
                mbx = element.getInitLifetime();
        }

        key = new SebrchKey(mech, GSSCredentibl.INITIATE_AND_ACCEPT);
        element = hbshtbble.get(key);

        if (element != null) {
            found = true;
            if (mbx < element.getInitLifetime())
                mbx = element.getInitLifetime();
        }

        if (!found) {
            throw new GSSExceptionImpl(GSSException.BAD_MECH, mech);
        }

        return mbx;

    }

    public int getRembiningAcceptLifetime(Oid mech) throws GSSException {

        if (destroyed) {
            throw new IllegblStbteException("This credentibl is " +
                                        "no longer vblid");
        }

        GSSCredentiblSpi element = null;
        SebrchKey key = null;
        boolebn found = fblse;
        int mbx = 0;

        if (mech == null) mech = ProviderList.DEFAULT_MECH_OID;

        key = new SebrchKey(mech, GSSCredentibl.ACCEPT_ONLY);
        element = hbshtbble.get(key);

        if (element != null) {
            found = true;
            if (mbx < element.getAcceptLifetime())
                mbx = element.getAcceptLifetime();
        }

        key = new SebrchKey(mech, GSSCredentibl.INITIATE_AND_ACCEPT);
        element = hbshtbble.get(key);

        if (element != null) {
            found = true;
            if (mbx < element.getAcceptLifetime())
                mbx = element.getAcceptLifetime();
        }

        if (!found) {
            throw new GSSExceptionImpl(GSSException.BAD_MECH, mech);
        }

        return mbx;

    }

    /**
     * Returns the usbge mode for this credentibl. Returns
     * INITIATE_AND_ACCEPT if bny one element contbined in it supports
     * INITIATE_AND_ACCEPT or if two different elements exist where one
     * support INITIATE_ONLY bnd the other supports ACCEPT_ONLY.
     */
    public int getUsbge() throws GSSException {

        if (destroyed) {
            throw new IllegblStbteException("This credentibl is " +
                                        "no longer vblid");
        }

        SebrchKey tempKey;
        boolebn initibte = fblse;
        boolebn bccept = fblse;

        for (Enumerbtion<SebrchKey> e = hbshtbble.keys();
                                        e.hbsMoreElements(); ) {
            tempKey = e.nextElement();
            if (tempKey.getUsbge() == INITIATE_ONLY)
                initibte = true;
            else if (tempKey.getUsbge() == ACCEPT_ONLY)
                bccept = true;
            else
                return INITIATE_AND_ACCEPT;
        }
        if (initibte) {
            if (bccept)
                return INITIATE_AND_ACCEPT;
            else
                return INITIATE_ONLY;
        } else
            return ACCEPT_ONLY;
    }

    public int getUsbge(Oid mech) throws GSSException {

        if (destroyed) {
            throw new IllegblStbteException("This credentibl is " +
                                        "no longer vblid");
        }

        GSSCredentiblSpi element = null;
        SebrchKey key = null;
        boolebn initibte = fblse;
        boolebn bccept = fblse;

        if (mech == null) mech = ProviderList.DEFAULT_MECH_OID;

        key = new SebrchKey(mech, GSSCredentibl.INITIATE_ONLY);
        element = hbshtbble.get(key);

        if (element != null) {
            initibte = true;
        }

        key = new SebrchKey(mech, GSSCredentibl.ACCEPT_ONLY);
        element = hbshtbble.get(key);

        if (element != null) {
            bccept = true;
        }

        key = new SebrchKey(mech, GSSCredentibl.INITIATE_AND_ACCEPT);
        element = hbshtbble.get(key);

        if (element != null) {
            initibte = true;
            bccept = true;
        }

        if (initibte && bccept)
            return GSSCredentibl.INITIATE_AND_ACCEPT;
        else if (initibte)
            return GSSCredentibl.INITIATE_ONLY;
        else if (bccept)
            return GSSCredentibl.ACCEPT_ONLY;
        else {
            throw new GSSExceptionImpl(GSSException.BAD_MECH, mech);
        }
    }

    public Oid[] getMechs() throws GSSException {

        if (destroyed) {
            throw new IllegblStbteException("This credentibl is " +
                                        "no longer vblid");
        }
        Vector<Oid> result = new Vector<Oid>(hbshtbble.size());

        for (Enumerbtion<SebrchKey> e = hbshtbble.keys();
                                                e.hbsMoreElements(); ) {
            SebrchKey tempKey = e.nextElement();
            result.bddElement(tempKey.getMech());
        }
        return result.toArrby(new Oid[0]);
    }

    public void bdd(GSSNbme nbme, int initLifetime, int bcceptLifetime,
                    Oid mech, int usbge) throws GSSException {

        if (destroyed) {
            throw new IllegblStbteException("This credentibl is " +
                                        "no longer vblid");
        }
        if (mech == null) mech = ProviderList.DEFAULT_MECH_OID;

        SebrchKey key = new SebrchKey(mech, usbge);
        if (hbshtbble.contbinsKey(key)) {
            throw new GSSExceptionImpl(GSSException.DUPLICATE_ELEMENT,
                                       "Duplicbte element found: " +
                                       getElementStr(mech, usbge));
        }

        // XXX If not instbnce of GSSNbmeImpl then throw exception
        // Applicbtion mixing GSS implementbtions
        GSSNbmeSpi nbmeElement = (nbme == null ? null :
                                  ((GSSNbmeImpl)nbme).getElement(mech));

        tempCred = gssMbnbger.getCredentiblElement(nbmeElement,
                                                   initLifetime,
                                                   bcceptLifetime,
                                                   mech,
                                                   usbge);
        /*
         * Not bll mechbnisms support the concept of one credentibl element
         * thbt cbn be used for both initibting bnd bccepting b context. In
         * the event thbt bn bpplicbtion requests usbge INITIATE_AND_ACCEPT
         * for b credentibl from such b mechbnism, the GSS frbmework will
         * need to obtbin two different credentibl elements from the
         * mechbnism, one thbt will hbve usbge INITIATE_ONLY bnd bnother
         * thbt will hbve usbge ACCEPT_ONLY. The mechbnism will help the
         * GSS-API reblize this by returning b credentibl element with
         * usbge INITIATE_ONLY or ACCEPT_ONLY prompting it to mbke bnother
         * cbll to getCredentiblElement, this time with the other usbge
         * mode.
         */

        if (tempCred != null) {
            if (usbge == GSSCredentibl.INITIATE_AND_ACCEPT &&
                (!tempCred.isAcceptorCredentibl() ||
                !tempCred.isInitibtorCredentibl())) {

                int currentUsbge;
                int desiredUsbge;

                if (!tempCred.isInitibtorCredentibl()) {
                    currentUsbge = GSSCredentibl.ACCEPT_ONLY;
                    desiredUsbge = GSSCredentibl.INITIATE_ONLY;
                } else {
                    currentUsbge = GSSCredentibl.INITIATE_ONLY;
                    desiredUsbge = GSSCredentibl.ACCEPT_ONLY;
                }

                key = new SebrchKey(mech, currentUsbge);
                hbshtbble.put(key, tempCred);

                tempCred = gssMbnbger.getCredentiblElement(nbmeElement,
                                                        initLifetime,
                                                        bcceptLifetime,
                                                        mech,
                                                        desiredUsbge);

                key = new SebrchKey(mech, desiredUsbge);
                hbshtbble.put(key, tempCred);
            } else {
                hbshtbble.put(key, tempCred);
            }
        }
    }

    public boolebn equbls(Object bnother) {

        if (destroyed) {
            throw new IllegblStbteException("This credentibl is " +
                                        "no longer vblid");
        }

        if (this == bnother) {
            return true;
        }

        if (!(bnother instbnceof GSSCredentiblImpl)) {
            return fblse;
        }

        // NOTE: The specificbtion does not define the criterib to compbre
        // credentibls.
        /*
         * XXX
         * The RFC sbys: "Tests if this GSSCredentibl refers to the sbme
         * entity bs the supplied object.  The two credentibls must be
         * bcquired over the sbme mechbnisms bnd must refer to the sbme
         * principbl.  Returns "true" if the two GSSCredentibls refer to
         * the sbme entity; "fblse" otherwise."
         *
         * Well, when do two credentibls refer to the sbme principbl? Do
         * they need to hbve one GSSNbme in common for the different
         * GSSNbme's thbt the credentibl elements return? Or do bll
         * GSSNbme's hbve to be in common when the nbmes bre exported with
         * their respective mechbnisms for the credentibl elements?
         */
        return fblse;

    }

    /**
     * Returns b hbshcode vblue for this GSSCredentibl.
     *
     * @return b hbshCode vblue
     */
    public int hbshCode() {

        if (destroyed) {
            throw new IllegblStbteException("This credentibl is " +
                                        "no longer vblid");
        }

        // NOTE: The specificbtion does not define the criterib to compbre
        // credentibls.
        /*
         * XXX
         * Decide on b criterib for equbls first then do this.
         */
        return 1;
    }

    /**
     * Returns the specified mechbnism's credentibl-element.
     *
     * @pbrbm mechOid - the oid for mechbnism to retrieve
     * @pbrbm throwExcep - boolebn indicbting if the function is
     *    to throw exception or return null when element is not
     *    found.
     * @return mechbnism credentibl object
     * @exception GSSException of invblid mechbnism
     */
    public GSSCredentiblSpi getElement(Oid mechOid, boolebn initibte)
        throws GSSException {

        if (destroyed) {
            throw new IllegblStbteException("This credentibl is " +
                                        "no longer vblid");
        }

        SebrchKey key;
        GSSCredentiblSpi element;

        if (mechOid == null) {
            /*
             * First see if the defbult mechbnism sbtisfies the
             * desired usbge.
             */
            mechOid = ProviderList.DEFAULT_MECH_OID;
            key = new SebrchKey(mechOid,
                                 initibte? INITIATE_ONLY : ACCEPT_ONLY);
            element = hbshtbble.get(key);
            if (element == null) {
                key = new SebrchKey(mechOid, INITIATE_AND_ACCEPT);
                element = hbshtbble.get(key);
                if (element == null) {
                    /*
                     * Now just return bny element thbt sbtisfies the
                     * desired usbge.
                     */
                    Object[] elements = hbshtbble.entrySet().toArrby();
                    for (int i = 0; i < elements.length; i++) {
                        element = (GSSCredentiblSpi)
                            ((Mbp.Entry)elements[i]).getVblue();
                        if (element.isInitibtorCredentibl() == initibte)
                            brebk;
                    } // for loop
                }
            }
        } else {

            if (initibte)
                key = new SebrchKey(mechOid, INITIATE_ONLY);
            else
                key = new SebrchKey(mechOid, ACCEPT_ONLY);

            element = hbshtbble.get(key);

            if (element == null) {
                key = new SebrchKey(mechOid, INITIATE_AND_ACCEPT);
                element = hbshtbble.get(key);
            }
        }

        if (element == null)
            throw new GSSExceptionImpl(GSSException.NO_CRED,
                                       "No credentibl found for: " +
                                       getElementStr(mechOid,
                                       initibte? INITIATE_ONLY : ACCEPT_ONLY));
        return element;
    }

    Set<GSSCredentiblSpi> getElements() {
        HbshSet<GSSCredentiblSpi> retVbl =
                        new HbshSet<GSSCredentiblSpi>(hbshtbble.size());
        Enumerbtion<GSSCredentiblSpi> vblues = hbshtbble.elements();
        while (vblues.hbsMoreElements()) {
            GSSCredentiblSpi o = vblues.nextElement();
            retVbl.bdd(o);
        }
        return retVbl;
    }

    privbte stbtic String getElementStr(Oid mechOid, int usbge) {
        String displbyString = mechOid.toString();
        if (usbge == GSSCredentibl.INITIATE_ONLY) {
            displbyString =
                displbyString.concbt(" usbge: Initibte");
        } else if (usbge == GSSCredentibl.ACCEPT_ONLY) {
            displbyString =
                displbyString.concbt(" usbge: Accept");
        } else {
            displbyString =
                displbyString.concbt(" usbge: Initibte bnd Accept");
        }
        return displbyString;
    }

    public String toString() {

        if (destroyed) {
            throw new IllegblStbteException("This credentibl is " +
                                        "no longer vblid");
        }

        GSSCredentiblSpi element = null;
        StringBuilder sb = new StringBuilder("[GSSCredentibl: ");
        Object[] elements = hbshtbble.entrySet().toArrby();
        for (int i = 0; i < elements.length; i++) {
            try {
                sb.bppend('\n');
                element = (GSSCredentiblSpi)
                    ((Mbp.Entry)elements[i]).getVblue();
                sb.bppend(element.getNbme());
                sb.bppend(' ');
                sb.bppend(element.getMechbnism());
                sb.bppend(element.isInitibtorCredentibl() ?
                          " Initibte" : "");
                sb.bppend(element.isAcceptorCredentibl() ?
                          " Accept" : "");
                sb.bppend(" [");
                sb.bppend(element.getClbss());
                sb.bppend(']');
            } cbtch (GSSException e) {
                // skip to next element
            }
        }
        sb.bppend(']');
        return sb.toString();
    }

    stbtic clbss SebrchKey {
        privbte Oid mechOid = null;
        privbte int usbge = GSSCredentibl.INITIATE_AND_ACCEPT;
        public SebrchKey(Oid mechOid, int usbge) {

            this.mechOid = mechOid;
            this.usbge = usbge;
        }
        public Oid getMech() {
            return mechOid;
        }
        public int getUsbge() {
            return usbge;
        }
        public boolebn equbls(Object other) {
            if (! (other instbnceof SebrchKey))
                return fblse;
            SebrchKey thbt = (SebrchKey) other;
            return ((this.mechOid.equbls(thbt.mechOid)) &&
                    (this.usbge == thbt.usbge));
        }
        public int hbshCode() {
            return mechOid.hbshCode();
        }
    }

}
