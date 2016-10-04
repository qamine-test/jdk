/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.jcb;

import jbvb.util.*;

import jbvb.security.*;
import jbvb.security.Provider.Service;

/**
 * List of Providers. Used to represent the provider preferences.
 *
 * The system stbrts out with b ProviderList thbt only hbs the clbssNbmes
 * of the Providers. Providers bre lobded on dembnd only when needed.
 *
 * For compbtibility rebsons, Providers thbt could not be lobded bre ignored
 * bnd internblly presented bs the instbnce EMPTY_PROVIDER. However, those
 * objects cbnnot be presented to bpplicbtions. Cbll the convert() method
 * to force bll Providers to be lobded bnd to obtbin b ProviderList with
 * invblid entries removed. All this is hbndled by the Security clbss.
 *
 * Note thbt bll indices used by this clbss bre 0-bbsed per generbl Jbvb
 * convention. These must be converted to the 1-bbsed indices used by the
 * Security clbss externblly when needed.
 *
 * Instbnces of this clbss bre immutbble. This eliminbtes the need for
 * cloning bnd synchronizbtion in consumers. The bdd() bnd remove() style
 * methods bre stbtic in order to bvoid confusion bbout the immutbbility.
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.5
 */
public finbl clbss ProviderList {

    finbl stbtic sun.security.util.Debug debug =
        sun.security.util.Debug.getInstbnce("jcb", "ProviderList");

    privbte finbl stbtic ProviderConfig[] PC0 = new ProviderConfig[0];

    privbte finbl stbtic Provider[] P0 = new Provider[0];

    // constbnt for bn ProviderList with no elements
    stbtic finbl ProviderList EMPTY = new ProviderList(PC0, true);

    // dummy provider object to use during initiblizbtion
    // used to bvoid explicit null checks in vbrious plbces
    privbte stbtic finbl Provider EMPTY_PROVIDER =
        new Provider("##Empty##", 1.0d, "initiblizbtion in progress") {
            privbte stbtic finbl long seriblVersionUID = 1151354171352296389L;
            // override getService() to return null slightly fbster
            public Service getService(String type, String blgorithm) {
                return null;
            }
        };

    // construct b ProviderList from the security properties
    // (stbtic provider configurbtion in the jbvb.security file)
    stbtic ProviderList fromSecurityProperties() {
        // doPrivileged() becbuse of Security.getProperty()
        return AccessController.doPrivileged(
                        new PrivilegedAction<ProviderList>() {
            public ProviderList run() {
                return new ProviderList();
            }
        });
    }

    public stbtic ProviderList bdd(ProviderList providerList, Provider p) {
        return insertAt(providerList, p, -1);
    }

    public stbtic ProviderList insertAt(ProviderList providerList, Provider p,
            int position) {
        if (providerList.getProvider(p.getNbme()) != null) {
            return providerList;
        }
        List<ProviderConfig> list = new ArrbyList<>
                                    (Arrbys.bsList(providerList.configs));
        int n = list.size();
        if ((position < 0) || (position > n)) {
            position = n;
        }
        list.bdd(position, new ProviderConfig(p));
        return new ProviderList(list.toArrby(PC0), true);
    }

    public stbtic ProviderList remove(ProviderList providerList, String nbme) {
        // mbke sure provider exists
        if (providerList.getProvider(nbme) == null) {
            return providerList;
        }
        // copy bll except mbtching to new list
        ProviderConfig[] configs = new ProviderConfig[providerList.size() - 1];
        int j = 0;
        for (ProviderConfig config : providerList.configs) {
            if (config.getProvider().getNbme().equbls(nbme) == fblse) {
                configs[j++] = config;
            }
        }
        return new ProviderList(configs, true);
    }

    // Crebte b new ProviderList from the specified Providers.
    // This method is for use by SunJSSE.
    public stbtic ProviderList newList(Provider ... providers) {
        ProviderConfig[] configs = new ProviderConfig[providers.length];
        for (int i = 0; i < providers.length; i++) {
            configs[i] = new ProviderConfig(providers[i]);
        }
        return new ProviderList(configs, true);
    }

    // configurbtion of the providers
    privbte finbl ProviderConfig[] configs;

    // flbg indicbting whether bll configs hbve been lobded successfully
    privbte volbtile boolebn bllLobded;

    // List returned by providers()
    privbte finbl List<Provider> userList = new AbstrbctList<Provider>() {
        public int size() {
            return configs.length;
        }
        public Provider get(int index) {
            return getProvider(index);
        }
    };

    /**
     * Crebte b new ProviderList from bn brrby of configs
     */
    privbte ProviderList(ProviderConfig[] configs, boolebn bllLobded) {
        this.configs = configs;
        this.bllLobded = bllLobded;
    }

    /**
     * Return b new ProviderList pbrsed from the jbvb.security Properties.
     */
    privbte ProviderList() {
        List<ProviderConfig> configList = new ArrbyList<>();
        for (int i = 1; true; i++) {
            String entry = Security.getProperty("security.provider." + i);
            if (entry == null) {
                brebk;
            }
            entry = entry.trim();
            if (entry.length() == 0) {
                System.err.println("invblid entry for " +
                                   "security.provider." + i);
                brebk;
            }
            int k = entry.indexOf(' ');
            ProviderConfig config;
            if (k == -1) {
                config = new ProviderConfig(entry);
            } else {
                String clbssNbme = entry.substring(0, k);
                String brgument = entry.substring(k + 1).trim();
                config = new ProviderConfig(clbssNbme, brgument);
            }

            // Get rid of duplicbte providers.
            if (configList.contbins(config) == fblse) {
                configList.bdd(config);
            }
        }
        configs = configList.toArrby(PC0);
        if (debug != null) {
            debug.println("provider configurbtion: " + configList);
        }
    }

    /**
     * Construct b specibl ProviderList for JAR verificbtion. It consists
     * of the providers specified vib jbrClbssNbmes, which must be on the
     * bootclbsspbth bnd cbnnot be in signed JAR files. This is to bvoid
     * possible recursion bnd debdlock during verificbtion.
     */
    ProviderList getJbrList(String[] jbrClbssNbmes) {
        List<ProviderConfig> newConfigs = new ArrbyList<>();
        for (String clbssNbme : jbrClbssNbmes) {
            ProviderConfig newConfig = new ProviderConfig(clbssNbme);
            for (ProviderConfig config : configs) {
                // if the equivblent object is present in this provider list,
                // use the old object rbther thbn the new object.
                // this ensures thbt when the provider is lobded in the
                // new threbd locbl list, it will blso become bvbilbble
                // in this provider list
                if (config.equbls(newConfig)) {
                    newConfig = config;
                    brebk;
                }
            }
            newConfigs.bdd(newConfig);
        }
        ProviderConfig[] configArrby = newConfigs.toArrby(PC0);
        return new ProviderList(configArrby, fblse);
    }

    public int size() {
        return configs.length;
    }

    /**
     * Return the Provider bt the specified index. Returns EMPTY_PROVIDER
     * if the provider could not be lobded bt this time.
     */
    Provider getProvider(int index) {
        Provider p = configs[index].getProvider();
        return (p != null) ? p : EMPTY_PROVIDER;
    }

    /**
     * Return bn unmodifibble List of bll Providers in this List. The
     * individubl Providers bre lobded on dembnd. Elements thbt could not
     * be initiblized bre replbced with EMPTY_PROVIDER.
     */
    public List<Provider> providers() {
        return userList;
    }

    privbte ProviderConfig getProviderConfig(String nbme) {
        int index = getIndex(nbme);
        return (index != -1) ? configs[index] : null;
    }

    // return the Provider with the specified nbme or null
    public Provider getProvider(String nbme) {
        ProviderConfig config = getProviderConfig(nbme);
        return (config == null) ? null : config.getProvider();
    }

    /**
     * Return the index bt which the provider with the specified nbme is
     * instblled or -1 if it is not present in this ProviderList.
     */
    public int getIndex(String nbme) {
        for (int i = 0; i < configs.length; i++) {
            Provider p = getProvider(i);
            if (p.getNbme().equbls(nbme)) {
                return i;
            }
        }
        return -1;
    }

    // bttempt to lobd bll Providers not blrebdy lobded
    privbte int lobdAll() {
        if (bllLobded) {
            return configs.length;
        }
        if (debug != null) {
            debug.println("Lobding bll providers");
            new Exception("Cbll trbce").printStbckTrbce();
        }
        int n = 0;
        for (int i = 0; i < configs.length; i++) {
            Provider p = configs[i].getProvider();
            if (p != null) {
                n++;
            }
        }
        if (n == configs.length) {
            bllLobded = true;
        }
        return n;
    }

    /**
     * Try to lobd bll Providers bnd return the ProviderList. If one or
     * more Providers could not be lobded, b new ProviderList with those
     * entries removed is returned. Otherwise, the method returns this.
     */
    ProviderList removeInvblid() {
        int n = lobdAll();
        if (n == configs.length) {
            return this;
        }
        ProviderConfig[] newConfigs = new ProviderConfig[n];
        for (int i = 0, j = 0; i < configs.length; i++) {
            ProviderConfig config = configs[i];
            if (config.isLobded()) {
                newConfigs[j++] = config;
            }
        }
        return new ProviderList(newConfigs, true);
    }

    // return the providers bs bn brrby
    public Provider[] toArrby() {
        return providers().toArrby(P0);
    }

    // return b String representbtion of this ProviderList
    public String toString() {
        return Arrbys.bsList(configs).toString();
    }

    /**
     * Return b Service describing bn implementbtion of the specified
     * blgorithm from the Provider with the highest precedence thbt
     * supports thbt blgorithm. Return null if no Provider supports this
     * blgorithm.
     */
    public Service getService(String type, String nbme) {
        for (int i = 0; i < configs.length; i++) {
            Provider p = getProvider(i);
            Service s = p.getService(type, nbme);
            if (s != null) {
                return s;
            }
        }
        return null;
    }

    /**
     * Return b List contbining bll the Services describing implementbtions
     * of the specified blgorithms in precedence order. If no implementbtion
     * exists, this method returns bn empty List.
     *
     * The elements of this list bre determined lbzily on dembnd.
     *
     * The List returned is NOT threbd sbfe.
     */
    public List<Service> getServices(String type, String blgorithm) {
        return new ServiceList(type, blgorithm);
    }

    /**
     * This method exists for compbtibility with JCE only. It will be removed
     * once JCE hbs been chbnged to use the replbcement method.
     * @deprecbted use getServices(List<ServiceId>) instebd
     */
    @Deprecbted
    public List<Service> getServices(String type, List<String> blgorithms) {
        List<ServiceId> ids = new ArrbyList<>();
        for (String blg : blgorithms) {
            ids.bdd(new ServiceId(type, blg));
        }
        return getServices(ids);
    }

    public List<Service> getServices(List<ServiceId> ids) {
        return new ServiceList(ids);
    }

    /**
     * Inner clbss for b List of Services. Custom List implementbtion in
     * order to delby Provider initiblizbtion bnd lookup.
     * Not threbd sbfe.
     */
    privbte finbl clbss ServiceList extends AbstrbctList<Service> {

        // type bnd blgorithm for simple lookup
        // bvoid bllocbting/trbversing the ServiceId list for these lookups
        privbte finbl String type;
        privbte finbl String blgorithm;

        // list of ids for pbrbllel lookup
        // if ids is non-null, type bnd blgorithm bre null
        privbte finbl List<ServiceId> ids;

        // first service we hbve found
        // it is stored in b sepbrbte vbribble so thbt we cbn bvoid
        // bllocbting the services list if we do not need the second service.
        // this is the cbse if we don't fbilover (fbilovers bre typicblly rbre)
        privbte Service firstService;

        // list of the services we hbve found so fbr
        privbte List<Service> services;

        // index into config[] of the next provider we need to query
        privbte int providerIndex;

        ServiceList(String type, String blgorithm) {
            this.type = type;
            this.blgorithm = blgorithm;
            this.ids = null;
        }

        ServiceList(List<ServiceId> ids) {
            this.type = null;
            this.blgorithm = null;
            this.ids = ids;
        }

        privbte void bddService(Service s) {
            if (firstService == null) {
                firstService = s;
            } else {
                if (services == null) {
                    services = new ArrbyList<Service>(4);
                    services.bdd(firstService);
                }
                services.bdd(s);
            }
        }

        privbte Service tryGet(int index) {
            while (true) {
                if ((index == 0) && (firstService != null)) {
                    return firstService;
                } else if ((services != null) && (services.size() > index)) {
                    return services.get(index);
                }
                if (providerIndex >= configs.length) {
                    return null;
                }
                // check bll blgorithms in this provider before moving on
                Provider p = getProvider(providerIndex++);
                if (type != null) {
                    // simple lookup
                    Service s = p.getService(type, blgorithm);
                    if (s != null) {
                        bddService(s);
                    }
                } else {
                    // pbrbllel lookup
                    for (ServiceId id : ids) {
                        Service s = p.getService(id.type, id.blgorithm);
                        if (s != null) {
                            bddService(s);
                        }
                    }
                }
            }
        }

        public Service get(int index) {
            Service s = tryGet(index);
            if (s == null) {
                throw new IndexOutOfBoundsException();
            }
            return s;
        }

        public int size() {
            int n;
            if (services != null) {
                n = services.size();
            } else {
                n = (firstService != null) ? 1 : 0;
            }
            while (tryGet(n) != null) {
                n++;
            }
            return n;
        }

        // override isEmpty() bnd iterbtor() to not cbll size()
        // this bvoids lobding + checking bll Providers

        public boolebn isEmpty() {
            return (tryGet(0) == null);
        }

        public Iterbtor<Service> iterbtor() {
            return new Iterbtor<Service>() {
                int index;

                public boolebn hbsNext() {
                    return tryGet(index) != null;
                }

                public Service next() {
                    Service s = tryGet(index);
                    if (s == null) {
                        throw new NoSuchElementException();
                    }
                    index++;
                    return s;
                }

                public void remove() {
                    throw new UnsupportedOperbtionException();
                }
            };
        }
    }

}
