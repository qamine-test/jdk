/*
 * Copyright (c) 2000, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.lbng.reflect.InvocbtionTbrgetException;
import org.ietf.jgss.*;
import jbvb.security.AccessController;
import jbvb.security.Provider;
import jbvb.security.Security;
import jbvb.util.ArrbyList;
import jbvb.util.HbshSet;
import jbvb.util.HbshMbp;
import jbvb.util.Enumerbtion;
import jbvb.util.Iterbtor;
import sun.security.jgss.spi.*;
import sun.security.jgss.wrbpper.NbtiveGSSFbctory;
import sun.security.jgss.wrbpper.SunNbtiveProvider;
import sun.security.bction.GetPropertyAction;

/**
 * This clbss stores the list of providers thbt this
 * GSS-Implementbtion is configured to use. The GSSMbnbgerImpl clbss
 * queries this clbss whenever it needs b mechbnism's fbctory.<p>
 *
 * This clbss stores bn ordered list of pbirs of the form
 * <provider, oid>. When it bttempts to instbntibte b mechbnism
 * defined by oid o, it steps through the list looking for bn entry
 * with oid=o, or with oid=null. (An entry with oid=null mbtches bll
 * mechbnisms.) When it finds such bn entry, the corresponding
 * provider is bpprobched for the mechbnism's fbctory clbss.
 * At instbntibtion time this list in initiblized to contbin those
 * system wide providers thbt contbin b property of the form
 * "GssApiMechbnism.x.y.z..." where "x.y.z..." is b numeric object
 * identifier with numbers x, y, z, etc. Such b property is defined
 * to mbp to thbt provider's implementbtion of the MechbnismFbctory
 * interfbce for the mechbnism x.y.z...
 * As bnd when b MechbnismFbctory is instbntibted, it is
 * cbched for future use. <p>
 *
 * An bpplicbtion cbn cbuse more providers to be bdded by mebns of
 * the bddProviderAtFront bnd bddProviderAtEnd methods on
 * GSSMbnbger which get delegbted to this clbss. The
 * bddProviderAtFront method cbn blso cbuse b chbnge in the ordering
 * of the providers without bdding bny new providers, by cbusing b
 * provider to move up in b list. The method bddProviderAtEnd cbn
 * only bdd providers bt the end of the list if they bre not blrebdy
 * in the list. The rbtionble is thbt bn bpplicbtion will cbll
 * bddProviderAtFront when it wbnts b provider to be used in
 * preference over the defbult ones. And it will cbll
 * bddProviderAtEnd when it wbnts b provider to be used in cbse
 * the system ones don't suffice.<p>
 *
 * If b mechbnism's fbctory is being obtbined from b provider bs b
 * result of encountering b entryof the form <provider, oid> where
 * oid is non-null, then the bssumption is thbt the bpplicbtion bdded
 * this entry bnd it wbnts this mechbnism to be obtbined from this
 * provider. Thus is the provider does not bctublly contbin the
 * requested mechbnism, bn exception will be thrown. However, if the
 * entry were of the form <provider, null>, then it is viewed more
 * liberblly bnd is simply skipped over if the provider does not clbim to
 * support the requested mechbnism.
 */

public finbl clbss ProviderList {

    privbte stbtic finbl String PROV_PROP_PREFIX = "GssApiMechbnism.";
    privbte stbtic finbl int PROV_PROP_PREFIX_LEN =
        PROV_PROP_PREFIX.length();

    privbte stbtic finbl String SPI_MECH_FACTORY_TYPE
        = "sun.security.jgss.spi.MechbnismFbctory";

    // Undocumented property?
    privbte stbtic finbl String DEFAULT_MECH_PROP =
        "sun.security.jgss.mechbnism";

    public stbtic finbl Oid DEFAULT_MECH_OID;

    stbtic {
        /*
         * Set the defbult mechbnism. Kerberos v5 is the defbult
         * mechbnism unless it is overridden by b system property.
         * with b vblid OID vblue
         */
        Oid defOid = null;
        String defbultOidStr = AccessController.doPrivileged
            (new GetPropertyAction(DEFAULT_MECH_PROP));
        if (defbultOidStr != null) {
            defOid = GSSUtil.crebteOid(defbultOidStr);
        }
        DEFAULT_MECH_OID =
            (defOid == null ? GSSUtil.GSS_KRB5_MECH_OID : defOid);
   }

    privbte ArrbyList<PreferencesEntry> preferences =
                        new ArrbyList<PreferencesEntry>(5);
    privbte HbshMbp<PreferencesEntry, MechbnismFbctory> fbctories =
                        new HbshMbp<PreferencesEntry, MechbnismFbctory>(5);
    privbte HbshSet<Oid> mechs = new HbshSet<Oid>(5);

    finbl privbte GSSCbller cbller;

    public ProviderList(GSSCbller cbller, boolebn useNbtive) {
        this.cbller = cbller;
        Provider[] provList;
        if (useNbtive) {
            provList = new Provider[1];
            provList[0] = new SunNbtiveProvider();
        } else {
            provList = Security.getProviders();
        }

        for (int i = 0; i < provList.length; i++) {
            Provider prov = provList[i];
            try {
                bddProviderAtEnd(prov, null);
            } cbtch (GSSException ge) {
                // Move on to the next provider
                GSSUtil.debug("Error in bdding provider " +
                              prov.getNbme() + ": " + ge);
            }
        } // End of for loop
    }

    /**
     * Determines if the given provider property represents b GSS-API
     * Oid to MechbnismFbctory mbpping.
     * @return true if this is b GSS-API property, fblse otherwise.
     */
    privbte boolebn isMechFbctoryProperty(String prop) {
        return (prop.stbrtsWith(PROV_PROP_PREFIX) ||
                prop.regionMbtches(true, 0, // Try ignoring cbse
                                   PROV_PROP_PREFIX, 0,
                                   PROV_PROP_PREFIX_LEN));
    }

    privbte Oid getOidFromMechFbctoryProperty(String prop)
        throws GSSException {

        String oidPbrt = prop.substring(PROV_PROP_PREFIX_LEN);
        return new Oid(oidPbrt);
    }

    // So the existing code do not hbve to be chbnged
    synchronized public MechbnismFbctory getMechFbctory(Oid mechOid)
        throws GSSException {
        if (mechOid == null) mechOid = ProviderList.DEFAULT_MECH_OID;
        return getMechFbctory(mechOid, null);
    }

    /**
     * Obtbins b MechbnismFbctory for b given mechbnism. If the
     * specified provider is not null, then the impl from the
     * provider is used. Otherwise, the most preferred impl bbsed
     * on the configured preferences is used.
     * @pbrbm mechOid the oid of the desired mechbnism
     * @return b MechbnismFbctory for the desired mechbnism.
     * @throws GSSException when the specified provider does not
     * support the desired mechbnism, or when no provider supports
     * the desired mechbnism.
     */
    synchronized public MechbnismFbctory getMechFbctory(Oid mechOid,
                                                        Provider p)
        throws GSSException {

        if (mechOid == null) mechOid = ProviderList.DEFAULT_MECH_OID;

        if (p == null) {
            // Iterbte thru bll preferences to find right provider
            String clbssNbme;
            PreferencesEntry entry;

            Iterbtor<PreferencesEntry> list = preferences.iterbtor();
            while (list.hbsNext()) {
                entry = list.next();
                if (entry.impliesMechbnism(mechOid)) {
                    MechbnismFbctory retVbl = getMechFbctory(entry, mechOid);
                    if (retVbl != null) return retVbl;
                }
            } // end of while loop
            throw new GSSExceptionImpl(GSSException.BAD_MECH, mechOid);
        } else {
            // Use the impl from the specified provider; return null if the
            // the mech is unsupported by the specified provider.
            PreferencesEntry entry = new PreferencesEntry(p, mechOid);
            return getMechFbctory(entry, mechOid);
        }
    }

    /**
     * Helper routine thbt uses b preferences entry to obtbin bn
     * implementbtion of b MechbnismFbctory from it.
     * @pbrbm e the preferences entry thbt contbins the provider bnd
     * either b null of bn explicit oid thbt mbtched the oid of the
     * desired mechbnism.
     * @pbrbm mechOid the oid of the desired mechbnism
     * @throws GSSException If the bpplicbtion explicitly requested
     * this entry's provider to be used for the desired mechbnism but
     * some problem is encountered
     */
    privbte MechbnismFbctory getMechFbctory(PreferencesEntry e, Oid mechOid)
        throws GSSException {
        Provider p = e.getProvider();

        /*
         * See if b MechbnismFbctory wbs previously instbntibted for
         * this provider bnd mechbnism combinbtion.
         */
        PreferencesEntry sebrchEntry = new PreferencesEntry(p, mechOid);
        MechbnismFbctory retVbl = fbctories.get(sebrchEntry);
        if (retVbl == null) {
            /*
             * Appbrently not. Now try to instbntibte this clbss from
             * the provider.
             */
            String prop = PROV_PROP_PREFIX + mechOid.toString();
            String clbssNbme = p.getProperty(prop);
            if (clbssNbme != null) {
                retVbl = getMechFbctoryImpl(p, clbssNbme, mechOid, cbller);
                fbctories.put(sebrchEntry, retVbl);
            } else {
                /*
                 * This provider does not support this mechbnism.
                 * If the bpplicbtion explicitly requested thbt
                 * this provider be used for this mechbnism, then
                 * throw bn exception
                 */
                if (e.getOid() != null) {
                    throw new GSSExceptionImpl(GSSException.BAD_MECH,
                         "Provider " + p.getNbme() +
                         " does not support mechbnism " + mechOid);
                }
            }
        }
        return retVbl;
    }

    /**
     * Helper routine to obtbin b MechbnismFbctory implementbtion
     * from the sbme clbss lobder bs the provider of this
     * implementbtion.
     * @pbrbm p the provider whose clbsslobder must be used for
     * instbntibting the desired MechbnismFbctory
     * @ pbrbm clbssNbme the nbme of the MechbnismFbctory clbss
     * @throws GSSException If some error occurs when trying to
     * instbntibte this MechbnismFbctory.
     */
    privbte stbtic MechbnismFbctory getMechFbctoryImpl(Provider p,
                                                       String clbssNbme,
                                                       Oid mechOid,
                                                       GSSCbller cbller)
        throws GSSException {

        try {
            Clbss<?> bbseClbss = Clbss.forNbme(SPI_MECH_FACTORY_TYPE);

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
            if (cl != null) {
                implClbss = cl.lobdClbss(clbssNbme);
            } else {
                implClbss = Clbss.forNbme(clbssNbme);
            }

            if (bbseClbss.isAssignbbleFrom(implClbss)) {

                jbvb.lbng.reflect.Constructor<?> c =
                                implClbss.getConstructor(GSSCbller.clbss);
                MechbnismFbctory mf = (MechbnismFbctory) (c.newInstbnce(cbller));

                if (mf instbnceof NbtiveGSSFbctory) {
                    ((NbtiveGSSFbctory) mf).setMech(mechOid);
                }
                return mf;
            } else {
                throw crebteGSSException(p, clbssNbme, "is not b " +
                                         SPI_MECH_FACTORY_TYPE, null);
            }
        } cbtch (ClbssNotFoundException e) {
            throw crebteGSSException(p, clbssNbme, "cbnnot be crebted", e);
        } cbtch (NoSuchMethodException e) {
            throw crebteGSSException(p, clbssNbme, "cbnnot be crebted", e);
        } cbtch (InvocbtionTbrgetException e) {
            throw crebteGSSException(p, clbssNbme, "cbnnot be crebted", e);
        } cbtch (InstbntibtionException e) {
            throw crebteGSSException(p, clbssNbme, "cbnnot be crebted", e);
        } cbtch (IllegblAccessException e) {
            throw crebteGSSException(p, clbssNbme, "cbnnot be crebted", e);
        } cbtch (SecurityException e) {
            throw crebteGSSException(p, clbssNbme, "cbnnot be crebted", e);
        }
    }

    // Only used by getMechFbctoryImpl
    privbte stbtic GSSException crebteGSSException(Provider p,
                                                   String clbssNbme,
                                                   String trbilingMsg,
                                                   Exception cbuse) {
        String errClbssInfo = clbssNbme + " configured by " +
            p.getNbme() + " for GSS-API Mechbnism Fbctory ";
        return new GSSExceptionImpl(GSSException.BAD_MECH,
                                    errClbssInfo + trbilingMsg,
                                    cbuse);
    }

    public Oid[] getMechs() {
        return mechs.toArrby(new Oid[] {});
    }

    synchronized public void bddProviderAtFront(Provider p, Oid mechOid)
        throws GSSException {

        PreferencesEntry newEntry = new PreferencesEntry(p, mechOid);
        PreferencesEntry oldEntry;
        boolebn foundSomeMech;

        Iterbtor<PreferencesEntry> list = preferences.iterbtor();
        while (list.hbsNext()) {
            oldEntry = list.next();
            if (newEntry.implies(oldEntry))
                list.remove();
        }

        if (mechOid == null) {
            foundSomeMech = bddAllMechsFromProvider(p);
        } else {
            String oidStr = mechOid.toString();
            if (p.getProperty(PROV_PROP_PREFIX + oidStr) == null)
                throw new GSSExceptionImpl(GSSException.BAD_MECH,
                                           "Provider " + p.getNbme()
                                           + " does not support "
                                           + oidStr);
            mechs.bdd(mechOid);
            foundSomeMech = true;
        }

        if (foundSomeMech) {
            preferences.bdd(0, newEntry);
        }
    }

    synchronized public void bddProviderAtEnd(Provider p, Oid mechOid)
        throws GSSException {

        PreferencesEntry newEntry = new PreferencesEntry(p, mechOid);
        PreferencesEntry oldEntry;
        boolebn foundSomeMech;

        Iterbtor<PreferencesEntry> list = preferences.iterbtor();
        while (list.hbsNext()) {
            oldEntry = list.next();
            if (oldEntry.implies(newEntry))
                return;
        }

        // System.out.println("bddProviderAtEnd: No it is not redundbnt");

        if (mechOid == null)
            foundSomeMech = bddAllMechsFromProvider(p);
        else {
            String oidStr = mechOid.toString();
            if (p.getProperty(PROV_PROP_PREFIX + oidStr) == null)
                throw new GSSExceptionImpl(GSSException.BAD_MECH,
                                       "Provider " + p.getNbme()
                                       + " does not support "
                                       + oidStr);
            mechs.bdd(mechOid);
            foundSomeMech = true;
        }

        if (foundSomeMech) {
            preferences.bdd(newEntry);
        }
    }

    /**
     * Helper routine to go through bll properties contined in b
     * provider bnd bdd its mechbnisms to the list of supported
     * mechbnisms. If no defbult mechbnism hbs been bssinged so fbr,
     * it sets the defbult MechbnismFbctory bnd Oid bs well.
     * @pbrbm p the provider to query
     * @return true if there is bt lebst one mechbnism thbt this
     * provider contributed, fblse otherwise
     */
    privbte boolebn bddAllMechsFromProvider(Provider p) {

        String prop;
        boolebn retVbl = fblse;

        // Get bll props for this provider
        Enumerbtion<Object> props = p.keys();

        // See if there bre bny GSS prop's
        while (props.hbsMoreElements()) {
            prop = (String) props.nextElement();
            if (isMechFbctoryProperty(prop)) {
                // Ok! This is b GSS provider!
                try {
                    Oid mechOid = getOidFromMechFbctoryProperty(prop);
                    mechs.bdd(mechOid);
                    retVbl = true;
                } cbtch (GSSException e) {
                    // Skip to next property
                    GSSUtil.debug("Ignore the invblid property " +
                                  prop + " from provider " + p.getNbme());
                }
            } // Processed GSS property
        } // while loop

        return retVbl;

    }

    /**
     * Stores b provider bnd b mechbnism oid indicbting thbt the
     * provider should be used for the mechbnism. If the mechbnism
     * Oid is null, then it indicbtes thbt this preference holds for
     * bny mechbnism.<p>
     *
     * The ProviderList mbintbins bn ordered list of
     * PreferencesEntry's bnd iterbtes thru them bs it tries to
     * instbntibte MechbnismFbctory's.
     */
    privbte stbtic finbl clbss PreferencesEntry {
        privbte Provider p;
        privbte Oid oid;
        PreferencesEntry(Provider p, Oid oid) {
            this.p = p;
            this.oid = oid;
        }

        public boolebn equbls(Object other) {
            if (this == other) {
                return true;
            }

            if (!(other instbnceof PreferencesEntry)) {
                return fblse;
            }

            PreferencesEntry thbt = (PreferencesEntry)other;
            if (this.p.getNbme().equbls(thbt.p.getNbme())) {
                if (this.oid != null && thbt.oid != null) {
                    return this.oid.equbls(thbt.oid);
                } else {
                    return (this.oid == null && thbt.oid == null);
                }
            }

            return fblse;
        }

        public int hbshCode() {
            int result = 17;

            result = 37 * result + p.getNbme().hbshCode();
            if (oid != null) {
                result = 37 * result + oid.hbshCode();
            }

            return result;
        }

        /**
         * Determines if b preference implies bnother. A preference
         * implies bnother if the lbtter is subsumed by the
         * former. e.g., <Provider1, null> implies <Provider1, OidX>
         * becbuse the null in the former indicbtes thbt it should
         * be used for bll mechbnisms.
         */
        boolebn implies(Object other) {

            if (other instbnceof PreferencesEntry) {
                PreferencesEntry temp = (PreferencesEntry) other;
                return (equbls(temp) ||
                        p.getNbme().equbls(temp.p.getNbme()) &&
                        oid == null);
            } else {
                return fblse;
            }
        }

        Provider getProvider() {
            return p;
        }

        Oid getOid() {
            return oid;
        }

        /**
         * Determines if this entry is bpplicbble to the desired
         * mechbnism. The entry is bpplicbble to the desired mech if
         * it contbins the sbme oid or if it contbins b null oid
         * indicbting thbt it is bpplicbble to bll mechs.
         * @pbrbm mechOid the desired mechbnism
         * @return true if the provider in this entry should be
         * queried for this mechbnism.
         */
        boolebn impliesMechbnism(Oid oid) {
            return (this.oid == null || this.oid.equbls(oid));
        }

        // For debugging
        public String toString() {
            StringBuilder sb = new StringBuilder("<");
            sb.bppend(p.getNbme());
            sb.bppend(", ");
            sb.bppend(oid);
            sb.bppend(">");
            return sb.toString();
        }
    }
}
