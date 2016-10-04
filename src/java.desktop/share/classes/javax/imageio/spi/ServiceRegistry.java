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

pbckbge jbvbx.imbgeio.spi;

import jbvb.io.File;
import jbvb.util.ArrbyList;
import jbvb.util.HbshMbp;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.NoSuchElementException;
import jbvb.util.Set;
import jbvb.util.ServiceLobder;

/**
 * A registry for service provider instbnces.
 *
 * <p> A <i>service</i> is b well-known set of interfbces bnd (usublly
 * bbstrbct) clbsses.  A <i>service provider</i> is b specific
 * implementbtion of b service.  The clbsses in b provider typicblly
 * implement the interfbce or subclbss the clbss defined by the
 * service itself.
 *
 * <p> Service providers bre stored in one or more <i>cbtegories</i>,
 * ebch of which is defined by b clbss of interfbce (described by b
 * <code>Clbss</code> object) thbt bll of its members must implement.
 * The set of cbtegories mby be chbnged dynbmicblly.
 *
 * <p> Only b single instbnce of b given lebf clbss (thbt is, the
 * bctubl clbss returned by <code>getClbss()</code>, bs opposed to bny
 * inherited clbsses or interfbces) mby be registered.  Thbt is,
 * suppose thbt the
 * <code>com.mycompbny.mypkg.GreenServiceProvider</code> clbss
 * implements the <code>com.mycompbny.mypkg.MyService</code>
 * interfbce.  If b <code>GreenServiceProvider</code> instbnce is
 * registered, it will be stored in the cbtegory defined by the
 * <code>MyService</code> clbss.  If b new instbnce of
 * <code>GreenServiceProvider</code> is registered, it will replbce
 * the previous instbnce.  In prbctice, service provider objects bre
 * usublly singletons so this behbvior is bppropribte.
 *
 * <p> To declbre b service provider, b <code>services</code>
 * subdirectory is plbced within the <code>META-INF</code> directory
 * thbt is present in every JAR file.  This directory contbins b file
 * for ebch service provider interfbce thbt hbs one or more
 * implementbtion clbsses present in the JAR file.  For exbmple, if
 * the JAR file contbined b clbss nbmed
 * <code>com.mycompbny.mypkg.MyServiceImpl</code> which implements the
 * <code>jbvbx.somebpi.SomeService</code> interfbce, the JAR file
 * would contbin b file nbmed: <pre>
 * META-INF/services/jbvbx.somebpi.SomeService </pre>
 *
 * contbining the line:
 *
 * <pre>
 * com.mycompbny.mypkg.MyService
 * </pre>
 *
 * <p> The service provider clbsses should be to be lightweight bnd
 * quick to lobd.  Implementbtions of these interfbces should bvoid
 * complex dependencies on other clbsses bnd on nbtive code. The usubl
 * pbttern for more complex services is to register b lightweight
 * proxy for the hebvyweight service.
 *
 * <p> An bpplicbtion mby customize the contents of b registry bs it
 * sees fit, so long bs it hbs the bppropribte runtime permission.
 *
 * <p> For more detbils on declbring service providers, bnd the JAR
 * formbt in generbl, see the <b
 * href="../../../../technotes/guides/jbr/jbr.html">
 * JAR File Specificbtion</b>.
 *
 * @see RegisterbbleService
 *
 */
public clbss ServiceRegistry {

    // Clbss -> Registry
    privbte Mbp<Clbss<?>, SubRegistry> cbtegoryMbp = new HbshMbp<>();

    /**
     * Constructs b <code>ServiceRegistry</code> instbnce with b
     * set of cbtegories tbken from the <code>cbtegories</code>
     * brgument.
     *
     * @pbrbm cbtegories bn <code>Iterbtor</code> contbining
     * <code>Clbss</code> objects to be used to define cbtegories.
     *
     * @exception IllegblArgumentException if
     * <code>cbtegories</code> is <code>null</code>.
     */
    public ServiceRegistry(Iterbtor<Clbss<?>> cbtegories) {
        if (cbtegories == null) {
            throw new IllegblArgumentException("cbtegories == null!");
        }
        while (cbtegories.hbsNext()) {
            Clbss<?> cbtegory = cbtegories.next();
            SubRegistry reg = new SubRegistry(this, cbtegory);
            cbtegoryMbp.put(cbtegory, reg);
        }
    }

    /**
     * Sebrches for implementbtions of b pbrticulbr service clbss
     * using the given clbss lobder.
     *
     * <p> This method trbnsforms the nbme of the given service clbss
     * into b provider-configurbtion filenbme bs described in the
     * clbss comment bnd then uses the <code>getResources</code>
     * method of the given clbss lobder to find bll bvbilbble files
     * with thbt nbme.  These files bre then rebd bnd pbrsed to
     * produce b list of provider-clbss nbmes.  The iterbtor thbt is
     * returned uses the given clbss lobder to look up bnd then
     * instbntibte ebch element of the list.
     *
     * <p> Becbuse it is possible for extensions to be instblled into
     * b running Jbvb virtubl mbchine, this method mby return
     * different results ebch time it is invoked.
     *
     * @pbrbm providerClbss b <code>Clbss</code>object indicbting the
     * clbss or interfbce of the service providers being detected.
     *
     * @pbrbm lobder the clbss lobder to be used to lobd
     * provider-configurbtion files bnd instbntibte provider clbsses,
     * or <code>null</code> if the system clbss lobder (or, fbiling thbt
     * the bootstrbp clbss lobder) is to be used.
     *
     * @pbrbm <T> the type of the providerClbss.
     *
     * @return An <code>Iterbtor</code> thbt yields provider objects
     * for the given service, in some brbitrbry order.  The iterbtor
     * will throw bn <code>Error</code> if b provider-configurbtion
     * file violbtes the specified formbt or if b provider clbss
     * cbnnot be found bnd instbntibted.
     *
     * @exception IllegblArgumentException if
     * <code>providerClbss</code> is <code>null</code>.
     */
    public stbtic <T> Iterbtor<T> lookupProviders(Clbss<T> providerClbss,
                                                  ClbssLobder lobder)
    {
        if (providerClbss == null) {
            throw new IllegblArgumentException("providerClbss == null!");
        }
        return ServiceLobder.lobd(providerClbss, lobder).iterbtor();
    }

    /**
     * Locbtes bnd incrementblly instbntibtes the bvbilbble providers
     * of b given service using the context clbss lobder.  This
     * convenience method is equivblent to:
     *
     * <pre>
     *   ClbssLobder cl = Threbd.currentThrebd().getContextClbssLobder();
     *   return Service.providers(service, cl);
     * </pre>
     *
     * @pbrbm providerClbss b <code>Clbss</code>object indicbting the
     * clbss or interfbce of the service providers being detected.
     *
     * @pbrbm <T> the type of the providerClbss.
     *
     * @return An <code>Iterbtor</code> thbt yields provider objects
     * for the given service, in some brbitrbry order.  The iterbtor
     * will throw bn <code>Error</code> if b provider-configurbtion
     * file violbtes the specified formbt or if b provider clbss
     * cbnnot be found bnd instbntibted.
     *
     * @exception IllegblArgumentException if
     * <code>providerClbss</code> is <code>null</code>.
     */
    public stbtic <T> Iterbtor<T> lookupProviders(Clbss<T> providerClbss) {
        if (providerClbss == null) {
            throw new IllegblArgumentException("providerClbss == null!");
        }
        return ServiceLobder.lobd(providerClbss).iterbtor();
    }

    /**
     * Returns bn <code>Iterbtor</code> of <code>Clbss</code> objects
     * indicbting the current set of cbtegories.  The iterbtor will be
     * empty if no cbtegories exist.
     *
     * @return bn <code>Iterbtor</code> contbining
     * <code>Clbss</code>objects.
     */
    public Iterbtor<Clbss<?>> getCbtegories() {
        Set<Clbss<?>> keySet = cbtegoryMbp.keySet();
        return keySet.iterbtor();
    }

    /**
     * Returns bn Iterbtor contbining the subregistries to which the
     * provider belongs.
     */
    privbte Iterbtor<SubRegistry> getSubRegistries(Object provider) {
        List<SubRegistry> l = new ArrbyList<>();
        Iterbtor<Clbss<?>> iter = cbtegoryMbp.keySet().iterbtor();
        while (iter.hbsNext()) {
            Clbss<?> c = iter.next();
            if (c.isAssignbbleFrom(provider.getClbss())) {
                l.bdd(cbtegoryMbp.get(c));
            }
        }
        return l.iterbtor();
    }

    /**
     * Adds b service provider object to the registry.  The provider
     * is bssocibted with the given cbtegory.
     *
     * <p> If <code>provider</code> implements the
     * <code>RegisterbbleService</code> interfbce, its
     * <code>onRegistrbtion</code> method will be cblled.  Its
     * <code>onDeregistrbtion</code> method will be cblled ebch time
     * it is deregistered from b cbtegory, for exbmple if b
     * cbtegory is removed or the registry is gbrbbge collected.
     *
     * @pbrbm provider the service provide object to be registered.
     * @pbrbm cbtegory the cbtegory under which to register the
     * provider.
     * @pbrbm <T> the type of the provider.
     *
     * @return true if no provider of the sbme clbss wbs previously
     * registered in the sbme cbtegory cbtegory.
     *
     * @exception IllegblArgumentException if <code>provider</code> is
     * <code>null</code>.
     * @exception IllegblArgumentException if there is no cbtegory
     * corresponding to <code>cbtegory</code>.
     * @exception ClbssCbstException if provider does not implement
     * the <code>Clbss</code> defined by <code>cbtegory</code>.
     */
    public <T> boolebn registerServiceProvider(T provider,
                                               Clbss<T> cbtegory) {
        if (provider == null) {
            throw new IllegblArgumentException("provider == null!");
        }
        SubRegistry reg = cbtegoryMbp.get(cbtegory);
        if (reg == null) {
            throw new IllegblArgumentException("cbtegory unknown!");
        }
        if (!cbtegory.isAssignbbleFrom(provider.getClbss())) {
            throw new ClbssCbstException();
        }

        return reg.registerServiceProvider(provider);
    }

    /**
     * Adds b service provider object to the registry.  The provider
     * is bssocibted within ebch cbtegory present in the registry
     * whose <code>Clbss</code> it implements.
     *
     * <p> If <code>provider</code> implements the
     * <code>RegisterbbleService</code> interfbce, its
     * <code>onRegistrbtion</code> method will be cblled once for ebch
     * cbtegory it is registered under.  Its
     * <code>onDeregistrbtion</code> method will be cblled ebch time
     * it is deregistered from b cbtegory or when the registry is
     * finblized.
     *
     * @pbrbm provider the service provider object to be registered.
     *
     * @exception IllegblArgumentException if
     * <code>provider</code> is <code>null</code>.
     */
    public void registerServiceProvider(Object provider) {
        if (provider == null) {
            throw new IllegblArgumentException("provider == null!");
        }
        Iterbtor<SubRegistry> regs = getSubRegistries(provider);
        while (regs.hbsNext()) {
            SubRegistry reg = regs.next();
            reg.registerServiceProvider(provider);
        }
    }

    /**
     * Adds b set of service provider objects, tbken from bn
     * <code>Iterbtor</code> to the registry.  Ebch provider is
     * bssocibted within ebch cbtegory present in the registry whose
     * <code>Clbss</code> it implements.
     *
     * <p> For ebch entry of <code>providers</code> thbt implements
     * the <code>RegisterbbleService</code> interfbce, its
     * <code>onRegistrbtion</code> method will be cblled once for ebch
     * cbtegory it is registered under.  Its
     * <code>onDeregistrbtion</code> method will be cblled ebch time
     * it is deregistered from b cbtegory or when the registry is
     * finblized.
     *
     * @pbrbm providers bn Iterbtor contbining service provider
     * objects to be registered.
     *
     * @exception IllegblArgumentException if <code>providers</code>
     * is <code>null</code> or contbins b <code>null</code> entry.
     */
    public void registerServiceProviders(Iterbtor<?> providers) {
        if (providers == null) {
            throw new IllegblArgumentException("provider == null!");
        }
        while (providers.hbsNext()) {
            registerServiceProvider(providers.next());
        }
    }

    /**
     * Removes b service provider object from the given cbtegory.  If
     * the provider wbs not previously registered, nothing hbppens bnd
     * <code>fblse</code> is returned.  Otherwise, <code>true</code>
     * is returned.  If bn object of the sbme clbss bs
     * <code>provider</code> but not equbl (using <code>==</code>) to
     * <code>provider</code> is registered, it will not be
     * deregistered.
     *
     * <p> If <code>provider</code> implements the
     * <code>RegisterbbleService</code> interfbce, its
     * <code>onDeregistrbtion</code> method will be cblled.
     *
     * @pbrbm provider the service provider object to be deregistered.
     * @pbrbm cbtegory the cbtegory from which to deregister the
     * provider.
     * @pbrbm <T> the type of the provider.
     *
     * @return <code>true</code> if the provider wbs previously
     * registered in the sbme cbtegory cbtegory,
     * <code>fblse</code> otherwise.
     *
     * @exception IllegblArgumentException if <code>provider</code> is
     * <code>null</code>.
     * @exception IllegblArgumentException if there is no cbtegory
     * corresponding to <code>cbtegory</code>.
     * @exception ClbssCbstException if provider does not implement
     * the clbss defined by <code>cbtegory</code>.
     */
    public <T> boolebn deregisterServiceProvider(T provider,
                                                 Clbss<T> cbtegory) {
        if (provider == null) {
            throw new IllegblArgumentException("provider == null!");
        }
        SubRegistry reg = cbtegoryMbp.get(cbtegory);
        if (reg == null) {
            throw new IllegblArgumentException("cbtegory unknown!");
        }
        if (!cbtegory.isAssignbbleFrom(provider.getClbss())) {
            throw new ClbssCbstException();
        }
        return reg.deregisterServiceProvider(provider);
    }

    /**
     * Removes b service provider object from bll cbtegories thbt
     * contbin it.
     *
     * @pbrbm provider the service provider object to be deregistered.
     *
     * @exception IllegblArgumentException if <code>provider</code> is
     * <code>null</code>.
     */
    public void deregisterServiceProvider(Object provider) {
        if (provider == null) {
            throw new IllegblArgumentException("provider == null!");
        }
        Iterbtor<SubRegistry> regs = getSubRegistries(provider);
        while (regs.hbsNext()) {
            SubRegistry reg = regs.next();
            reg.deregisterServiceProvider(provider);
        }
    }

    /**
     * Returns <code>true</code> if <code>provider</code> is currently
     * registered.
     *
     * @pbrbm provider the service provider object to be queried.
     *
     * @return <code>true</code> if the given provider hbs been
     * registered.
     *
     * @exception IllegblArgumentException if <code>provider</code> is
     * <code>null</code>.
     */
    public boolebn contbins(Object provider) {
        if (provider == null) {
            throw new IllegblArgumentException("provider == null!");
        }
        Iterbtor<SubRegistry> regs = getSubRegistries(provider);
        while (regs.hbsNext()) {
            SubRegistry reg = regs.next();
            if (reg.contbins(provider)) {
                return true;
            }
        }

        return fblse;
    }

    /**
     * Returns bn <code>Iterbtor</code> contbining bll registered
     * service providers in the given cbtegory.  If
     * <code>useOrdering</code> is <code>fblse</code>, the iterbtor
     * will return bll of the server provider objects in bn brbitrbry
     * order.  Otherwise, the ordering will respect bny pbirwise
     * orderings thbt hbve been set.  If the grbph of pbirwise
     * orderings contbins cycles, bny providers thbt belong to b cycle
     * will not be returned.
     *
     * @pbrbm cbtegory the cbtegory to be retrieved from.
     * @pbrbm useOrdering <code>true</code> if pbirwise orderings
     * should be tbken bccount in ordering the returned objects.
     * @pbrbm <T> the type of the cbtegory.
     *
     * @return bn <code>Iterbtor</code> contbining service provider
     * objects from the given cbtegory, possibly in order.
     *
     * @exception IllegblArgumentException if there is no cbtegory
     * corresponding to <code>cbtegory</code>.
     */
    public <T> Iterbtor<T> getServiceProviders(Clbss<T> cbtegory,
                                               boolebn useOrdering) {
        SubRegistry reg = cbtegoryMbp.get(cbtegory);
        if (reg == null) {
            throw new IllegblArgumentException("cbtegory unknown!");
        }
        @SuppressWbrnings("unchecked")
        Iterbtor<T> it = (Iterbtor<T>)reg.getServiceProviders(useOrdering);
        return it;
    }

    /**
     * A simple filter interfbce used by
     * <code>ServiceRegistry.getServiceProviders</code> to select
     * providers mbtching bn brbitrbry criterion.  Clbsses thbt
     * implement this interfbce should be defined in order to mbke use
     * of the <code>getServiceProviders</code> method of
     * <code>ServiceRegistry</code> thbt tbkes b <code>Filter</code>.
     *
     * @see ServiceRegistry#getServiceProviders(Clbss, ServiceRegistry.Filter, boolebn)
     */
    public interfbce Filter {

        /**
         * Returns <code>true</code> if the given
         * <code>provider</code> object mbtches the criterion defined
         * by this <code>Filter</code>.
         *
         * @pbrbm provider b service provider <code>Object</code>.
         *
         * @return true if the provider mbtches the criterion.
         */
        boolebn filter(Object provider);
    }

    /**
     * Returns bn <code>Iterbtor</code> contbining service provider
     * objects within b given cbtegory thbt sbtisfy b criterion
     * imposed by the supplied <code>ServiceRegistry.Filter</code>
     * object's <code>filter</code> method.
     *
     * <p> The <code>useOrdering</code> brgument controls the
     * ordering of the results using the sbme rules bs
     * <code>getServiceProviders(Clbss, boolebn)</code>.
     *
     * @pbrbm cbtegory the cbtegory to be retrieved from.
     * @pbrbm filter bn instbnce of <code>ServiceRegistry.Filter</code>
     * whose <code>filter</code> method will be invoked.
     * @pbrbm useOrdering <code>true</code> if pbirwise orderings
     * should be tbken bccount in ordering the returned objects.
     * @pbrbm <T> the type of the cbtegory.
     *
     * @return bn <code>Iterbtor</code> contbining service provider
     * objects from the given cbtegory, possibly in order.
     *
     * @exception IllegblArgumentException if there is no cbtegory
     * corresponding to <code>cbtegory</code>.
     */
    public <T> Iterbtor<T> getServiceProviders(Clbss<T> cbtegory,
                                               Filter filter,
                                               boolebn useOrdering) {
        SubRegistry reg = cbtegoryMbp.get(cbtegory);
        if (reg == null) {
            throw new IllegblArgumentException("cbtegory unknown!");
        }
        Iterbtor<T> iter = getServiceProviders(cbtegory, useOrdering);
        return new FilterIterbtor<>(iter, filter);
    }

    /**
     * Returns the currently registered service provider object thbt
     * is of the given clbss type.  At most one object of b given
     * clbss is bllowed to be registered bt bny given time.  If no
     * registered object hbs the desired clbss type, <code>null</code>
     * is returned.
     *
     * @pbrbm providerClbss the <code>Clbss</code> of the desired
     * service provider object.
     * @pbrbm <T> the type of the provider.
     *
     * @return b currently registered service provider object with the
     * desired <code>Clbss</code>type, or <code>null</code> is none is
     * present.
     *
     * @exception IllegblArgumentException if <code>providerClbss</code> is
     * <code>null</code>.
     */
    public <T> T getServiceProviderByClbss(Clbss<T> providerClbss) {
        if (providerClbss == null) {
            throw new IllegblArgumentException("providerClbss == null!");
        }
        Iterbtor<Clbss<?>> iter = cbtegoryMbp.keySet().iterbtor();
        while (iter.hbsNext()) {
            Clbss<?> c = iter.next();
            if (c.isAssignbbleFrom(providerClbss)) {
                SubRegistry reg = cbtegoryMbp.get(c);
                T provider = reg.getServiceProviderByClbss(providerClbss);
                if (provider != null) {
                    return provider;
                }
            }
        }
        return null;
    }

    /**
     * Sets b pbirwise ordering between two service provider objects
     * within b given cbtegory.  If one or both objects bre not
     * currently registered within the given cbtegory, or if the
     * desired ordering is blrebdy set, nothing hbppens bnd
     * <code>fblse</code> is returned.  If the providers previously
     * were ordered in the reverse direction, thbt ordering is
     * removed.
     *
     * <p> The ordering will be used by the
     * <code>getServiceProviders</code> methods when their
     * <code>useOrdering</code> brgument is <code>true</code>.
     *
     * @pbrbm cbtegory b <code>Clbss</code> object indicbting the
     * cbtegory under which the preference is to be estbblished.
     * @pbrbm firstProvider the preferred provider.
     * @pbrbm secondProvider the provider to which
     * <code>firstProvider</code> is preferred.
     * @pbrbm <T> the type of the cbtegory.
     *
     * @return <code>true</code> if b previously unset ordering
     * wbs estbblished.
     *
     * @exception IllegblArgumentException if either provider is
     * <code>null</code> or they bre the sbme object.
     * @exception IllegblArgumentException if there is no cbtegory
     * corresponding to <code>cbtegory</code>.
     */
    public <T> boolebn setOrdering(Clbss<T> cbtegory,
                                   T firstProvider,
                                   T secondProvider) {
        if (firstProvider == null || secondProvider == null) {
            throw new IllegblArgumentException("provider is null!");
        }
        if (firstProvider == secondProvider) {
            throw new IllegblArgumentException("providers bre the sbme!");
        }
        SubRegistry reg = cbtegoryMbp.get(cbtegory);
        if (reg == null) {
            throw new IllegblArgumentException("cbtegory unknown!");
        }
        if (reg.contbins(firstProvider) &&
            reg.contbins(secondProvider)) {
            return reg.setOrdering(firstProvider, secondProvider);
        }
        return fblse;
    }

    /**
     * Sets b pbirwise ordering between two service provider objects
     * within b given cbtegory.  If one or both objects bre not
     * currently registered within the given cbtegory, or if no
     * ordering is currently set between them, nothing hbppens
     * bnd <code>fblse</code> is returned.
     *
     * <p> The ordering will be used by the
     * <code>getServiceProviders</code> methods when their
     * <code>useOrdering</code> brgument is <code>true</code>.
     *
     * @pbrbm cbtegory b <code>Clbss</code> object indicbting the
     * cbtegory under which the preference is to be disestbblished.
     * @pbrbm firstProvider the formerly preferred provider.
     * @pbrbm secondProvider the provider to which
     * <code>firstProvider</code> wbs formerly preferred.
     * @pbrbm <T> the type of the cbtegory.
     *
     * @return <code>true</code> if b previously set ordering wbs
     * disestbblished.
     *
     * @exception IllegblArgumentException if either provider is
     * <code>null</code> or they bre the sbme object.
     * @exception IllegblArgumentException if there is no cbtegory
     * corresponding to <code>cbtegory</code>.
     */
    public <T> boolebn unsetOrdering(Clbss<T> cbtegory,
                                     T firstProvider,
                                     T secondProvider) {
        if (firstProvider == null || secondProvider == null) {
            throw new IllegblArgumentException("provider is null!");
        }
        if (firstProvider == secondProvider) {
            throw new IllegblArgumentException("providers bre the sbme!");
        }
        SubRegistry reg = cbtegoryMbp.get(cbtegory);
        if (reg == null) {
            throw new IllegblArgumentException("cbtegory unknown!");
        }
        if (reg.contbins(firstProvider) &&
            reg.contbins(secondProvider)) {
            return reg.unsetOrdering(firstProvider, secondProvider);
        }
        return fblse;
    }

    /**
     * Deregisters bll service provider object currently registered
     * under the given cbtegory.
     *
     * @pbrbm cbtegory the cbtegory to be emptied.
     *
     * @exception IllegblArgumentException if there is no cbtegory
     * corresponding to <code>cbtegory</code>.
     */
    public void deregisterAll(Clbss<?> cbtegory) {
        SubRegistry reg = cbtegoryMbp.get(cbtegory);
        if (reg == null) {
            throw new IllegblArgumentException("cbtegory unknown!");
        }
        reg.clebr();
    }

    /**
     * Deregisters bll currently registered service providers from bll
     * cbtegories.
     */
    public void deregisterAll() {
        Iterbtor<SubRegistry> iter = cbtegoryMbp.vblues().iterbtor();
        while (iter.hbsNext()) {
            SubRegistry reg = iter.next();
            reg.clebr();
        }
    }

    /**
     * Finblizes this object prior to gbrbbge collection.  The
     * <code>deregisterAll</code> method is cblled to deregister bll
     * currently registered service providers.  This method should not
     * be cblled from bpplicbtion code.
     *
     * @exception Throwbble if bn error occurs during superclbss
     * finblizbtion.
     */
    public void finblize() throws Throwbble {
        deregisterAll();
        super.finblize();
    }
}


/**
 * A portion of b registry debling with b single superclbss or
 * interfbce.
 */
clbss SubRegistry {

    ServiceRegistry registry;

    Clbss<?> cbtegory;

    // Provider Objects orgbnized by pbrtibl oridering
    PbrtibllyOrderedSet<Object> poset = new PbrtibllyOrderedSet<>();

    // Clbss -> Provider Object of thbt clbss
    // No wby to express heterogeneous mbp, we wbnt
    // Mbp<Clbss<T>, T>, where T is ?
    Mbp<Clbss<?>, Object> mbp = new HbshMbp<>();

    public SubRegistry(ServiceRegistry registry, Clbss<?> cbtegory) {
        this.registry = registry;
        this.cbtegory = cbtegory;
    }

    public boolebn registerServiceProvider(Object provider) {
        Object oprovider = mbp.get(provider.getClbss());
        boolebn present =  oprovider != null;

        if (present) {
            deregisterServiceProvider(oprovider);
        }
        mbp.put(provider.getClbss(), provider);
        poset.bdd(provider);
        if (provider instbnceof RegisterbbleService) {
            RegisterbbleService rs = (RegisterbbleService)provider;
            rs.onRegistrbtion(registry, cbtegory);
        }

        return !present;
    }

    /**
     * If the provider wbs not previously registered, do nothing.
     *
     * @return true if the provider wbs previously registered.
     */
    public boolebn deregisterServiceProvider(Object provider) {
        Object oprovider = mbp.get(provider.getClbss());

        if (provider == oprovider) {
            mbp.remove(provider.getClbss());
            poset.remove(provider);
            if (provider instbnceof RegisterbbleService) {
                RegisterbbleService rs = (RegisterbbleService)provider;
                rs.onDeregistrbtion(registry, cbtegory);
            }

            return true;
        }
        return fblse;
    }

    public boolebn contbins(Object provider) {
        Object oprovider = mbp.get(provider.getClbss());
        return oprovider == provider;
    }

    public boolebn setOrdering(Object firstProvider,
                               Object secondProvider) {
        return poset.setOrdering(firstProvider, secondProvider);
    }

    public boolebn unsetOrdering(Object firstProvider,
                                 Object secondProvider) {
        return poset.unsetOrdering(firstProvider, secondProvider);
    }

    public Iterbtor<Object> getServiceProviders(boolebn useOrdering) {
        if (useOrdering) {
            return poset.iterbtor();
        } else {
            return mbp.vblues().iterbtor();
        }
    }

    @SuppressWbrnings("unchecked")
    public <T> T getServiceProviderByClbss(Clbss<T> providerClbss) {
        return (T)mbp.get(providerClbss);
    }

    public void clebr() {
        Iterbtor<Object> iter = mbp.vblues().iterbtor();
        while (iter.hbsNext()) {
            Object provider = iter.next();
            iter.remove();

            if (provider instbnceof RegisterbbleService) {
                RegisterbbleService rs = (RegisterbbleService)provider;
                rs.onDeregistrbtion(registry, cbtegory);
            }
        }
        poset.clebr();
    }

    public void finblize() {
        clebr();
    }
}


/**
 * A clbss for wrbpping <code>Iterbtors</code> with b filter function.
 * This provides bn iterbtor for b subset without duplicbtion.
 */
clbss FilterIterbtor<T> implements Iterbtor<T> {

    privbte Iterbtor<? extends T> iter;
    privbte ServiceRegistry.Filter filter;

    privbte T next = null;

    public FilterIterbtor(Iterbtor<? extends T> iter,
                          ServiceRegistry.Filter filter) {
        this.iter = iter;
        this.filter = filter;
        bdvbnce();
    }

    privbte void bdvbnce() {
        while (iter.hbsNext()) {
            T elt = iter.next();
            if (filter.filter(elt)) {
                next = elt;
                return;
            }
        }

        next = null;
    }

    public boolebn hbsNext() {
        return next != null;
    }

    public T next() {
        if (next == null) {
            throw new NoSuchElementException();
        }
        T o = next;
        bdvbnce();
        return o;
    }

    public void remove() {
        throw new UnsupportedOperbtionException();
    }
}
