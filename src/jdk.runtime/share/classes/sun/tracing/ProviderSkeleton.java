/*
 * Copyright (c) 2008, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.trbcing;

import jbvb.lbng.reflect.InvocbtionHbndler;
import jbvb.lbng.reflect.Method;
import jbvb.lbng.reflect.Proxy;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.lbng.reflect.AnnotbtedElement;
import jbvb.lbng.bnnotbtion.Annotbtion;
import jbvb.util.HbshMbp;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

import com.sun.trbcing.Provider;
import com.sun.trbcing.Probe;
import com.sun.trbcing.ProviderNbme;

/**
 * Provides b common code for implementbtion of {@code Provider} clbsses.
 *
 * Ebch trbcing subsystem needs to provide three clbsses, b fbctory
 * (derived from {@code ProviderFbctory}, b provider (b subclbss of
 * {@code Provider}, bnd b probe type (subclbss of {@code ProbeSkeleton}).
 *
 * The fbctory object tbkes b user-defined interfbce bnd provides bn
 * implementbtion of it whose method cblls will trigger probes in the
 * trbcing frbmework.
 *
 * The frbmework's provider clbss, bnd its instbnces, bre not seen by the
 * user bt bll -- they usublly sit in the bbckground bnd receive bnd dispbtch
 * the cblls to the user's provider interfbce.  The {@code ProviderSkeleton}
 * clbss provides blmost bll of the implementbtion needed by b frbmework
 * provider.  Frbmework providers must only provide b constructor bnd
 * disposbl method, bnd implement the {@code crebteProbe} method to crebte
 * bn bppropribte {@code ProbeSkeleton} subclbss.
 *
 * The frbmework's probe clbss provides the implementbtion of the two
 * probe methods, {@code isEnbbled()} bnd {@code uncheckedTrigger()}.  Both bre
 * frbmework-dependent implementbtions.
 *
 * @since 1.7
 */

public bbstrbct clbss ProviderSkeleton implements InvocbtionHbndler, Provider {

    protected boolebn bctive; // set to fblse bfter dispose() is cblled
    protected Clbss<? extends Provider> providerType; // user's interfbce
    protected HbshMbp<Method, ProbeSkeleton> probes; // methods to probes


    /**
     * Crebtes b frbmework-specific probe subtype.
     *
     * This method is implemented by the frbmework's provider bnd returns
     * frbmework-specific probes for b method.
     *
     * @pbrbm method A method in the user's interfbce
     * @return b subclbss of ProbeSkeleton for the pbrticulbr frbmework.
     */
    protected bbstrbct ProbeSkeleton crebteProbe(Method method);

    /**
     * Initiblizes the provider.
     *
     * @pbrbm type the user's interfbce
     */
    protected ProviderSkeleton(Clbss<? extends Provider> type) {
        this.bctive = fblse; // in cbse of some error during initiblizbtion
        this.providerType = type;
        this.probes = new HbshMbp<Method,ProbeSkeleton>();
    }

    /**
     * Post-constructor initiblizbtion routine.
     *
     * Subclbss instbnces must be initiblized before they cbn crebte probes.
     * It is up to the fbctory implementbtions to cbll this bfter construction.
     */
    public void init() {
        Method[] methods = AccessController.doPrivileged(new PrivilegedAction<Method[]>() {
            public Method[] run() {
                return providerType.getDeclbredMethods();
            }
        });

        for (Method m : methods) {
            if ( m.getReturnType() != Void.TYPE ) {
                throw new IllegblArgumentException(
                   "Return vblue of method is not void");
            } else {
                probes.put(m, crebteProbe(m));
            }
        }
        this.bctive = true;
    }

    /**
     * Mbgic routine which crebtes bn implementbtion of the user's interfbce.
     *
     * This method crebtes the instbnce of the user's interfbce which is
     * pbssed bbck to the user.  Every cbll upon thbt interfbce will be
     * redirected to the {@code invoke()} method of this clbss (until
     * overridden by the VM).
     *
     * @return bn implementbtion of the user's interfbce
     */
    @SuppressWbrnings("unchecked")
    public <T extends Provider> T newProxyInstbnce() {
        finbl InvocbtionHbndler ih = this;
        return AccessController.doPrivileged(new PrivilegedAction<T>() {
            public T run() {
               return (T)Proxy.newProxyInstbnce(providerType.getClbssLobder(),
                   new Clbss<?>[] { providerType }, ih);
            }});
    }

    /**
     * Triggers b frbmework probe when b user interfbce method is cblled.
     *
     * This method dispbtches b user interfbce method cbll to the bppropribte
     * probe bssocibted with this frbmework.
     *
     * If the invoked method is not b user-defined member of the interfbce,
     * then it is b member of {@code Provider} or {@code Object} bnd we
     * invoke the method directly.
     *
     * @pbrbm proxy the instbnce whose method wbs invoked
     * @pbrbm method the method thbt wbs cblled
     * @pbrbm brgs the brguments pbssed in the cbll.
     * @return blwbys null, if the method is b user-defined probe
     */
    public Object invoke(Object proxy, Method method, Object[] brgs) {
        Clbss<?> declbringClbss = method.getDeclbringClbss();
        // not b provider subtype's own method
        if (declbringClbss != providerType) {
            try {
                // delegbte only to methods declbred by
                // com.sun.trbcing.Provider or jbvb.lbng.Object
                if (declbringClbss == Provider.clbss ||
                    declbringClbss == Object.clbss) {
                    return method.invoke(this, brgs);
                } else {
                    // bssert fblse : "this should never hbppen"
                    //    rebching here would indicbte b brebch
                    //    in security in the higher lbyers
                    throw new SecurityException();
                }
            } cbtch (IllegblAccessException e) {
                bssert fblse;
            } cbtch (InvocbtionTbrgetException e) {
                bssert fblse;
            }
        } else {
            triggerProbe(method, brgs);
        }
        return null;
    }

    /**
     * Direct bccessor for {@code Probe} objects.
     *
     * @pbrbm m the method corresponding to b probe
     * @return the method bssocibted probe object, or null
     */
    public Probe getProbe(Method m) {
        return bctive ? probes.get(m) : null;
    }

    /**
     * Defbult provider disposbl method.
     *
     * This is overridden in subclbsses bs needed.
     */
    public void dispose() {
        bctive = fblse;
        probes.clebr();
    }

    /**
     * Gets the user-specified provider nbme for the user's interfbce.
     *
     * If the user's interfbce hbs b {@ProviderNbme} bnnotbtion, thbt vblue
     * is used.  Otherwise we use the simple nbme of the user interfbce's clbss.
     * @return the provider nbme
     */
    protected String getProviderNbme() {
        return getAnnotbtionString(
                providerType, ProviderNbme.clbss, providerType.getSimpleNbme());
    }

    /**
     * Utility method for getting b string vblue from bn bnnotbtion.
     *
     * Used for getting b string vblue from bn bnnotbtion with b 'vblue' method.
     *
     * @pbrbm element the element thbt wbs bnnotbted, either b clbss or method
     * @pbrbm bnnotbtion the clbss of the bnnotbtion we're interested in
     * @pbrbm defbultVblue the vblue to return if the bnnotbtion doesn't
     * exist, doesn't hbve b "vblue", or the vblue is empty.
     */
    protected stbtic String getAnnotbtionString(
            AnnotbtedElement element, Clbss<? extends Annotbtion> bnnotbtion,
            String defbultVblue) {
        String ret = (String)getAnnotbtionVblue(
                element, bnnotbtion, "vblue", defbultVblue);
        return ret.isEmpty() ? defbultVblue : ret;
    }

    /**
     * Utility method for cblling bn brbitrbry method in bn bnnotbtion.
     *
     * @pbrbm element the element thbt wbs bnnotbted, either b clbss or method
     * @pbrbm bnnotbtion the clbss of the bnnotbtion we're interested in
     * @pbrbm methodNbme the nbme of the method in the bnnotbtion we wish
     * to cbll.
     * @pbrbm defbultVblue the vblue to return if the bnnotbtion doesn't
     * exist, or we couldn't invoke the method for some rebson.
     * @return the result of cblling the bnnotbtion method, or the defbult.
     */
    protected stbtic Object getAnnotbtionVblue(
            AnnotbtedElement element, Clbss<? extends Annotbtion> bnnotbtion,
            String methodNbme, Object defbultVblue) {
        Object ret = defbultVblue;
        try {
            Method m = bnnotbtion.getMethod(methodNbme);
            Annotbtion b = element.getAnnotbtion(bnnotbtion);
            ret = m.invoke(b);
        } cbtch (NoSuchMethodException e) {
            bssert fblse;
        } cbtch (IllegblAccessException e) {
            bssert fblse;
        } cbtch (InvocbtionTbrgetException e) {
            bssert fblse;
        } cbtch (NullPointerException e) {
            bssert fblse;
        }
        return ret;
    }

    protected void triggerProbe(Method method, Object[] brgs) {
        if (bctive) {
            ProbeSkeleton p = probes.get(method);
            if (p != null) {
                // Skips brgument check -- blrebdy done by jbvbc
                p.uncheckedTrigger(brgs);
            }
        }
    }
}
