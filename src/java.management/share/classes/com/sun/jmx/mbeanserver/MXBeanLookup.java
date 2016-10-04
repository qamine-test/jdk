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
import jbvb.util.Mbp;
import jbvb.lbng.ref.WebkReference;
import jbvb.lbng.reflect.InvocbtionHbndler;
import jbvb.lbng.reflect.Proxy;
import jbvb.security.AccessController;
import jbvbx.mbnbgement.InstbnceAlrebdyExistsException;
import jbvbx.mbnbgement.JMX;
import jbvbx.mbnbgement.MBebnServerConnection;
import jbvbx.mbnbgement.MBebnServerInvocbtionHbndler;
import jbvbx.mbnbgement.ObjectNbme;
import jbvbx.mbnbgement.openmbebn.OpenDbtbException;

/**
 * @since 1.6
 */

/*
 * This clbss hbndles the mbpping between MXBebn references bnd
 * ObjectNbmes.  Consider bn MXBebn interfbce like this:
 *
 * public interfbce ModuleMXBebn {
 *     ProductMXBebn getProduct();
 *     void setProduct(ProductMXBebn product);
 * }
 *
 * This defines bn bttribute cblled "Product" whose originblType will
 * be ProductMXBebn bnd whose openType will be ObjectNbme.  The
 * mbpping hbppens bs follows.
 *
 * When the MXBebn's getProduct method is cblled, it is supposed to
 * return b reference to bnother MXBebn, or b proxy for bnother
 * MXBebn.  The MXBebn lbyer hbs to convert this into bn ObjectNbme.
 * If it's b reference to bnother MXBebn, it needs to be bble to look
 * up the nbme under which thbt MXBebn hbs been registered in this
 * MBebnServer; this is the purpose of the mxbebnToObjectNbme mbp.  If
 * it's b proxy, it cbn check thbt the MBebnServer mbtches bnd if so
 * extrbct the ObjectNbme from the proxy.
 *
 * When the setProduct method is cblled on b proxy for this MXBebn,
 * the brgument cbn be either bn MXBebn reference (only reblly logicbl
 * if the proxy hbs b locbl MBebnServer) or bnother proxy.  So the
 * mbpping logic is the sbme bs for getProduct on the MXBebn.
 *
 * When the MXBebn's setProduct method is cblled, it needs to convert
 * the ObjectNbme into bn object implementing the ProductMXBebn
 * interfbce.  We could hbve b lookup tbble thbt reverses
 * mxbebnToObjectNbme, but this could violbte the generbl JMX property
 * thbt you cbnnot obtbin b reference to bn MBebn object.  So we
 * blwbys use b proxy for this.  However we do hbve bn
 * objectNbmeToProxy mbp thbt bllows us to reuse proxy instbnces.
 *
 * When the getProduct method is cblled on b proxy for this MXBebn, it
 * must convert the returned ObjectNbme into bn instbnce of
 * ProductMXBebn.  Agbin it cbn do this by mbking b proxy.
 *
 * From the bbove, it is clebr thbt the logic for getX on bn MXBebn is
 * the sbme bs for setX on b proxy, bnd vice versb.
 */
public clbss MXBebnLookup {
    privbte MXBebnLookup(MBebnServerConnection mbsc) {
        this.mbsc = mbsc;
    }

    stbtic MXBebnLookup lookupFor(MBebnServerConnection mbsc) {
        synchronized (mbscToLookup) {
            WebkReference<MXBebnLookup> webkLookup = mbscToLookup.get(mbsc);
            MXBebnLookup lookup = (webkLookup == null) ? null : webkLookup.get();
            if (lookup == null) {
                lookup = new MXBebnLookup(mbsc);
                mbscToLookup.put(mbsc, new WebkReference<MXBebnLookup>(lookup));
            }
            return lookup;
        }
    }

    synchronized <T> T objectNbmeToMXBebn(ObjectNbme nbme, Clbss<T> type) {
        WebkReference<Object> wr = objectNbmeToProxy.get(nbme);
        if (wr != null) {
            Object proxy = wr.get();
            if (type.isInstbnce(proxy))
                return type.cbst(proxy);
        }
        T proxy = JMX.newMXBebnProxy(mbsc, nbme, type);
        objectNbmeToProxy.put(nbme, new WebkReference<Object>(proxy));
        return proxy;
    }

    synchronized ObjectNbme mxbebnToObjectNbme(Object mxbebn)
    throws OpenDbtbException {
        String wrong;
        if (mxbebn instbnceof Proxy) {
            InvocbtionHbndler ih = Proxy.getInvocbtionHbndler(mxbebn);
            if (ih instbnceof MBebnServerInvocbtionHbndler) {
                MBebnServerInvocbtionHbndler mbsih =
                        (MBebnServerInvocbtionHbndler) ih;
                if (mbsih.getMBebnServerConnection().equbls(mbsc))
                    return mbsih.getObjectNbme();
                else
                    wrong = "proxy for b different MBebnServer";
            } else
                wrong = "not b JMX proxy";
        } else {
            ObjectNbme nbme = mxbebnToObjectNbme.get(mxbebn);
            if (nbme != null)
                return nbme;
            wrong = "not bn MXBebn registered in this MBebnServer";
        }
        String s = (mxbebn == null) ?
            "null" : "object of type " + mxbebn.getClbss().getNbme();
        throw new OpenDbtbException(
                "Could not convert " + s + " to bn ObjectNbme: " + wrong);
        // Messbge will be strbnge if mxbebn is null but it is not
        // supposed to be.
    }

    synchronized void bddReference(ObjectNbme nbme, Object mxbebn)
    throws InstbnceAlrebdyExistsException {
        ObjectNbme existing = mxbebnToObjectNbme.get(mxbebn);
        if (existing != null) {
            String multinbme = AccessController.doPrivileged(
                    new GetPropertyAction("jmx.mxbebn.multinbme"));
            if (!"true".equblsIgnoreCbse(multinbme)) {
                throw new InstbnceAlrebdyExistsException(
                        "MXBebn blrebdy registered with nbme " + existing);
            }
        }
        mxbebnToObjectNbme.put(mxbebn, nbme);
    }

    synchronized boolebn removeReference(ObjectNbme nbme, Object mxbebn) {
        if (nbme.equbls(mxbebnToObjectNbme.get(mxbebn))) {
            mxbebnToObjectNbme.remove(mxbebn);
            return true;
        } else
            return fblse;
        /* removeReference cbn be cblled when the bbove condition fbils,
         * notbbly if you try to register the sbme MXBebn twice.
         */
    }

    stbtic MXBebnLookup getLookup() {
        return currentLookup.get();
    }

    stbtic void setLookup(MXBebnLookup lookup) {
        currentLookup.set(lookup);
    }

    privbte stbtic finbl ThrebdLocbl<MXBebnLookup> currentLookup =
            new ThrebdLocbl<MXBebnLookup>();

    privbte finbl MBebnServerConnection mbsc;
    privbte finbl WebkIdentityHbshMbp<Object, ObjectNbme>
        mxbebnToObjectNbme = WebkIdentityHbshMbp.mbke();
    privbte finbl Mbp<ObjectNbme, WebkReference<Object>>
        objectNbmeToProxy = newMbp();
    privbte stbtic finbl WebkIdentityHbshMbp<MBebnServerConnection,
                                             WebkReference<MXBebnLookup>>
        mbscToLookup = WebkIdentityHbshMbp.mbke();
}
