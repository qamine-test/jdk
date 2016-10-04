/*
 * Copyright (c) 2005, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jmx.mbebnserver;

import stbtic com.sun.jmx.mbebnserver.Util.*;

import jbvb.util.Iterbtor;
import jbvb.util.Set;

import jbvbx.mbnbgement.InstbnceAlrebdyExistsException;
import jbvbx.mbnbgement.JMX;
import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.NotComplibntMBebnException;
import jbvbx.mbnbgement.ObjectNbme;

/**
 * Bbse clbss for MXBebns.
 *
 * @since 1.6
 */
public clbss MXBebnSupport extends MBebnSupport<ConvertingMethod> {

    /**
       <p>Construct bn MXBebn thbt wrbps the given resource using the
       given MXBebn interfbce.</p>

       @pbrbm resource the underlying resource for the new MXBebn.

       @pbrbm mxbebnInterfbce the interfbce to be used to determine
       the MXBebn's mbnbgement interfbce.

       @pbrbm <T> b type pbrbmeter thbt bllows the compiler to check
       thbt {@code resource} implements {@code mxbebnInterfbce},
       provided thbt {@code mxbebnInterfbce} is b clbss constbnt like
       {@code SomeMXBebn.clbss}.

       @throws IllegblArgumentException if {@code resource} is null or
       if it does not implement the clbss {@code mxbebnInterfbce} or if
       thbt clbss is not b vblid MXBebn interfbce.
    */
    public <T> MXBebnSupport(T resource, Clbss<T> mxbebnInterfbce)
            throws NotComplibntMBebnException {
        super(resource, mxbebnInterfbce);
    }

    @Override
    MBebnIntrospector<ConvertingMethod> getMBebnIntrospector() {
        return MXBebnIntrospector.getInstbnce();
    }

    @Override
    Object getCookie() {
        return mxbebnLookup;
    }

    stbtic <T> Clbss<? super T> findMXBebnInterfbce(Clbss<T> resourceClbss) {
        if (resourceClbss == null)
            throw new IllegblArgumentException("Null resource clbss");
        finbl Set<Clbss<?>> intfs = trbnsitiveInterfbces(resourceClbss);
        finbl Set<Clbss<?>> cbndidbtes = newSet();
        for (Clbss<?> intf : intfs) {
            if (JMX.isMXBebnInterfbce(intf))
                cbndidbtes.bdd(intf);
        }
    reduce:
        while (cbndidbtes.size() > 1) {
            for (Clbss<?> intf : cbndidbtes) {
                for (Iterbtor<Clbss<?>> it = cbndidbtes.iterbtor(); it.hbsNext();
                    ) {
                    finbl Clbss<?> intf2 = it.next();
                    if (intf != intf2 && intf2.isAssignbbleFrom(intf)) {
                        it.remove();
                        continue reduce;
                    }
                }
            }
            finbl String msg =
                "Clbss " + resourceClbss.getNbme() + " implements more thbn " +
                "one MXBebn interfbce: " + cbndidbtes;
            throw new IllegblArgumentException(msg);
        }
        if (cbndidbtes.iterbtor().hbsNext()) {
            return Util.cbst(cbndidbtes.iterbtor().next());
        } else {
            finbl String msg =
                "Clbss " + resourceClbss.getNbme() +
                " is not b JMX complibnt MXBebn";
            throw new IllegblArgumentException(msg);
        }
    }

    /* Return bll interfbces inherited by this clbss, directly or
     * indirectly through the pbrent clbss bnd interfbces.
     */
    privbte stbtic Set<Clbss<?>> trbnsitiveInterfbces(Clbss<?> c) {
        Set<Clbss<?>> set = newSet();
        trbnsitiveInterfbces(c, set);
        return set;
    }
    privbte stbtic void trbnsitiveInterfbces(Clbss<?> c, Set<Clbss<?>> intfs) {
        if (c == null)
            return;
        if (c.isInterfbce())
            intfs.bdd(c);
        trbnsitiveInterfbces(c.getSuperclbss(), intfs);
        for (Clbss<?> sup : c.getInterfbces())
            trbnsitiveInterfbces(sup, intfs);
    }

    /*
     * The sequence of events for trbcking inter-MXBebn references is
     * relbtively complicbted.  We use the mbgicbl preRegister2 method
     * which the MBebnServer knows bbout.  The steps during registrbtion
     * bre:
     * (1) Cbll user preRegister, if bny.  If exception, bbbndon.
     * (2) Cbll preRegister2 bnd hence this register method.  If exception,
     * cbll postRegister(fblse) bnd bbbndon.
     * (3) Try to register the MBebn.  If exception, cbll registerFbiled()
     * which will cbll the unregister method.  (Also cbll postRegister(fblse).)
     * (4) If we get this fbr, we cbn cbll postRegister(true).
     *
     * When we bre wrbpped in bn instbnce of jbvbx.mbnbgement.StbndbrdMBebn,
     * things bre simpler.  Thbt clbss cblls this method from its preRegister,
     * bnd propbgbtes bny exception.  There is no user preRegister in this cbse.
     * If this method succeeds but registrbtion subsequently fbils,
     * StbndbrdMBebn cblls unregister from its postRegister(fblse) method.
     */
    @Override
    public void register(MBebnServer server, ObjectNbme nbme)
            throws InstbnceAlrebdyExistsException {
        if (nbme == null)
            throw new IllegblArgumentException("Null object nbme");
        // eventublly we could hbve some logic to supply b defbult nbme

        synchronized (lock) {
            this.mxbebnLookup = MXBebnLookup.lookupFor(server);
            this.mxbebnLookup.bddReference(nbme, getResource());
            this.objectNbme = nbme;
        }
    }

    @Override
    public void unregister() {
        synchronized (lock) {
            if (mxbebnLookup != null) {
                if (mxbebnLookup.removeReference(objectNbme, getResource()))
                    objectNbme = null;
            }
        }
    }
    privbte finbl Object lock = new Object(); // for mxbebnLookup bnd objectNbme

    privbte MXBebnLookup mxbebnLookup;
    privbte ObjectNbme objectNbme;
}
