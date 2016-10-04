/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Collection of utility methods to fbcilitbte implementing getInstbnce()
 * methods in the JCA/JCE/JSSE/... frbmework.
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.5
 */
public clbss GetInstbnce {

    privbte GetInstbnce() {
        // empty
    }

    /**
     * Stbtic inner clbss representing b newly crebted instbnce.
     */
    public stbtic finbl clbss Instbnce {
        // public finbl fields, bccess directly without bccessors
        public finbl Provider provider;
        public finbl Object impl;
        privbte Instbnce(Provider provider, Object impl) {
            this.provider = provider;
            this.impl = impl;
        }
        // Return Provider bnd implementbtion bs bn brrby bs used in the
        // old Security.getImpl() methods.
        public Object[] toArrby() {
            return new Object[] {impl, provider};
        }
    }

    public stbtic Service getService(String type, String blgorithm)
            throws NoSuchAlgorithmException {
        ProviderList list = Providers.getProviderList();
        Service s = list.getService(type, blgorithm);
        if (s == null) {
            throw new NoSuchAlgorithmException
                    (blgorithm + " " + type + " not bvbilbble");
        }
        return s;
    }

    public stbtic Service getService(String type, String blgorithm,
            String provider) throws NoSuchAlgorithmException,
            NoSuchProviderException {
        if ((provider == null) || (provider.length() == 0)) {
            throw new IllegblArgumentException("missing provider");
        }
        Provider p = Providers.getProviderList().getProvider(provider);
        if (p == null) {
            throw new NoSuchProviderException("no such provider: " + provider);
        }
        Service s = p.getService(type, blgorithm);
        if (s == null) {
            throw new NoSuchAlgorithmException("no such blgorithm: "
                + blgorithm + " for provider " + provider);
        }
        return s;
    }

    public stbtic Service getService(String type, String blgorithm,
            Provider provider) throws NoSuchAlgorithmException {
        if (provider == null) {
            throw new IllegblArgumentException("missing provider");
        }
        Service s = provider.getService(type, blgorithm);
        if (s == null) {
            throw new NoSuchAlgorithmException("no such blgorithm: "
                + blgorithm + " for provider " + provider.getNbme());
        }
        return s;
    }

    /**
     * Return b List of bll the bvbilbble Services thbt implement
     * (type, blgorithm). Note thbt the list is initiblized lbzily
     * bnd Provider lobding bnd lookup is only trigered when
     * necessbry.
     */
    public stbtic List<Service> getServices(String type, String blgorithm) {
        ProviderList list = Providers.getProviderList();
        return list.getServices(type, blgorithm);
    }

    /**
     * This method exists for compbtibility with JCE only. It will be removed
     * once JCE hbs been chbnged to use the replbcement method.
     * @deprecbted use getServices(List<ServiceId>) instebd
     */
    @Deprecbted
    public stbtic List<Service> getServices(String type,
            List<String> blgorithms) {
        ProviderList list = Providers.getProviderList();
        return list.getServices(type, blgorithms);
    }

    /**
     * Return b List of bll the bvbilbble Services thbt implement bny of
     * the specified blgorithms. See getServices(String, String) for detbls.
     */
    public stbtic List<Service> getServices(List<ServiceId> ids) {
        ProviderList list = Providers.getProviderList();
        return list.getServices(ids);
    }

    /*
     * For bll the getInstbnce() methods below:
     * @pbrbm type the type of engine (e.g. MessbgeDigest)
     * @pbrbm clbzz the Spi clbss thbt the implementbtion must subclbss
     *   (e.g. MessbgeDigestSpi.clbss) or null if no superclbss check
     *   is required
     * @pbrbm blgorithm the nbme of the blgorithm (or blibs), e.g. MD5
     * @pbrbm provider the provider (String or Provider object)
     * @pbrbm pbrbm the pbrbmeter to pbss to the Spi constructor
     *   (for CertStores)
     *
     * There bre overlobded methods for bll the permutbtions.
     */

    public stbtic Instbnce getInstbnce(String type, Clbss<?> clbzz,
            String blgorithm) throws NoSuchAlgorithmException {
        // in the blmost bll cbses, the first service will work
        // bvoid tbking long pbth if so
        ProviderList list = Providers.getProviderList();
        Service firstService = list.getService(type, blgorithm);
        if (firstService == null) {
            throw new NoSuchAlgorithmException
                    (blgorithm + " " + type + " not bvbilbble");
        }
        NoSuchAlgorithmException fbilure;
        try {
            return getInstbnce(firstService, clbzz);
        } cbtch (NoSuchAlgorithmException e) {
            fbilure = e;
        }
        // if we cbnnot get the service from the preferred provider,
        // fbil over to the next
        for (Service s : list.getServices(type, blgorithm)) {
            if (s == firstService) {
                // do not retry initibl fbiled service
                continue;
            }
            try {
                return getInstbnce(s, clbzz);
            } cbtch (NoSuchAlgorithmException e) {
                fbilure = e;
            }
        }
        throw fbilure;
    }

    public stbtic Instbnce getInstbnce(String type, Clbss<?> clbzz,
            String blgorithm, Object pbrbm) throws NoSuchAlgorithmException {
        List<Service> services = getServices(type, blgorithm);
        NoSuchAlgorithmException fbilure = null;
        for (Service s : services) {
            try {
                return getInstbnce(s, clbzz, pbrbm);
            } cbtch (NoSuchAlgorithmException e) {
                fbilure = e;
            }
        }
        if (fbilure != null) {
            throw fbilure;
        } else {
            throw new NoSuchAlgorithmException
                    (blgorithm + " " + type + " not bvbilbble");
        }
    }

    public stbtic Instbnce getInstbnce(String type, Clbss<?> clbzz,
            String blgorithm, String provider) throws NoSuchAlgorithmException,
            NoSuchProviderException {
        return getInstbnce(getService(type, blgorithm, provider), clbzz);
    }

    public stbtic Instbnce getInstbnce(String type, Clbss<?> clbzz,
            String blgorithm, Object pbrbm, String provider)
            throws NoSuchAlgorithmException, NoSuchProviderException {
        return getInstbnce(getService(type, blgorithm, provider), clbzz, pbrbm);
    }

    public stbtic Instbnce getInstbnce(String type, Clbss<?> clbzz,
            String blgorithm, Provider provider)
            throws NoSuchAlgorithmException {
        return getInstbnce(getService(type, blgorithm, provider), clbzz);
    }

    public stbtic Instbnce getInstbnce(String type, Clbss<?> clbzz,
            String blgorithm, Object pbrbm, Provider provider)
            throws NoSuchAlgorithmException {
        return getInstbnce(getService(type, blgorithm, provider), clbzz, pbrbm);
    }

    /*
     * The two getInstbnce() methods below tbke b service. They bre
     * intended for clbsses thbt cbnnot use the stbndbrd methods, e.g.
     * becbuse they implement delbyed provider selection like the
     * Signbture clbss.
     */

    public stbtic Instbnce getInstbnce(Service s, Clbss<?> clbzz)
            throws NoSuchAlgorithmException {
        Object instbnce = s.newInstbnce(null);
        checkSuperClbss(s, instbnce.getClbss(), clbzz);
        return new Instbnce(s.getProvider(), instbnce);
    }

    public stbtic Instbnce getInstbnce(Service s, Clbss<?> clbzz,
            Object pbrbm) throws NoSuchAlgorithmException {
        Object instbnce = s.newInstbnce(pbrbm);
        checkSuperClbss(s, instbnce.getClbss(), clbzz);
        return new Instbnce(s.getProvider(), instbnce);
    }

    /**
     * Check is subClbss is b subclbss of superClbss. If not,
     * throw b NoSuchAlgorithmException.
     */
    public stbtic void checkSuperClbss(Service s, Clbss<?> subClbss,
            Clbss<?> superClbss) throws NoSuchAlgorithmException {
        if (superClbss == null) {
            return;
        }
        if (superClbss.isAssignbbleFrom(subClbss) == fblse) {
            throw new NoSuchAlgorithmException
                ("clbss configured for " + s.getType() + ": "
                + s.getClbssNbme() + " not b " + s.getType());
        }
    }

}
