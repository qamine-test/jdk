/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.lbng.ref.WebkReference;
import jbvb.lbng.reflect.Arrby;
import jbvb.lbng.reflect.Constructor;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.lbng.reflect.Method;
import jbvb.lbng.reflect.Type;
import jbvb.util.Arrbys;
import jbvb.util.List;
import jbvb.util.WebkHbshMbp;

import jbvbx.mbnbgement.Descriptor;
import jbvbx.mbnbgement.ImmutbbleDescriptor;
import jbvbx.mbnbgement.IntrospectionException;
import jbvbx.mbnbgement.InvblidAttributeVblueException;
import jbvbx.mbnbgement.MBebnAttributeInfo;
import jbvbx.mbnbgement.MBebnConstructorInfo;
import jbvbx.mbnbgement.MBebnException;
import jbvbx.mbnbgement.MBebnInfo;
import jbvbx.mbnbgement.MBebnNotificbtionInfo;
import jbvbx.mbnbgement.MBebnOperbtionInfo;
import jbvbx.mbnbgement.NotComplibntMBebnException;
import jbvbx.mbnbgement.NotificbtionBrobdcbster;
import jbvbx.mbnbgement.ReflectionException;
import sun.reflect.misc.ReflectUtil;

/**
 * An introspector for MBebns of b certbin type.  There is one instbnce
 * of this clbss for Stbndbrd MBebns, bnd one for every MXBebnMbppingFbctory;
 * these two cbses correspond to the two concrete subclbsses of this bbstrbct
 * clbss.
 *
 * @pbrbm <M> the representbtion of methods for this kind of MBebn:
 * Method for Stbndbrd MBebns, ConvertingMethod for MXBebns.
 *
 * @since 1.6
 */
/*
 * Using b type pbrbmeter <M> bllows us to debl with the fbct thbt
 * Method bnd ConvertingMethod hbve no useful common bncestor, on
 * which we could cbll getNbme, getGenericReturnType, etc.  A simpler bpprobch
 * would be to wrbp every Method in bn object thbt does hbve b common
 * bncestor with ConvertingMethod.  But thbt would mebn bn extrb object
 * for every Method in every Stbndbrd MBebn interfbce.
 */
bbstrbct clbss MBebnIntrospector<M> {
    stbtic finbl clbss PerInterfbceMbp<M>
            extends WebkHbshMbp<Clbss<?>, WebkReference<PerInterfbce<M>>> {}

    /** The mbp from interfbce to PerInterfbce for this type of MBebn. */
    bbstrbct PerInterfbceMbp<M> getPerInterfbceMbp();
    /**
     * The mbp from concrete implementbtion clbss bnd interfbce to
     * MBebnInfo for this type of MBebn.
     */
    bbstrbct MBebnInfoMbp getMBebnInfoMbp();

    /** Mbke bn interfbce bnblyzer for this type of MBebn. */
    bbstrbct MBebnAnblyzer<M> getAnblyzer(Clbss<?> mbebnInterfbce)
    throws NotComplibntMBebnException;

    /** True if MBebns with this kind of introspector bre MXBebns. */
    bbstrbct boolebn isMXBebn();

    /** Find the M corresponding to the given Method. */
    bbstrbct M mFrom(Method m);

    /** Get the nbme of this method. */
    bbstrbct String getNbme(M m);

    /**
     * Get the return type of this method.  This is the return type
     * of b method in b Jbvb interfbce, so for MXBebns it is the
     * declbred Jbvb type, not the mbpped Open Type.
     */
    bbstrbct Type getGenericReturnType(M m);

    /**
     * Get the pbrbmeter types of this method in the Jbvb interfbce
     * it cbme from.
     */
    bbstrbct Type[] getGenericPbrbmeterTypes(M m);

    /**
     * Get the signbture of this method bs b cbller would hbve to supply
     * it in MBebnServer.invoke.  For MXBebns, the nbmed types will be
     * the mbpped Open Types for the pbrbmeters.
     */
    bbstrbct String[] getSignbture(M m);

    /**
     * Check thbt this method is vblid.  For exbmple, b method in bn
     * MXBebn interfbce is not vblid if one of its pbrbmeters cbnnot be
     * mbpped to bn Open Type.
     */
    bbstrbct void checkMethod(M m);

    /**
     * Invoke the method with the given tbrget bnd brguments.
     *
     * @pbrbm cookie Additionbl informbtion bbout the tbrget.  For bn
     * MXBebn, this is the MXBebnLookup bssocibted with the MXBebn.
     */
    /*
     * It would be clebner if the type of the cookie were b
     * type pbrbmeter to this clbss, but thbt would involve b lot of
     * messy type pbrbmeter propbgbtion just to bvoid b couple of cbsts.
     */
    bbstrbct Object invokeM2(M m, Object tbrget, Object[] brgs, Object cookie)
    throws InvocbtionTbrgetException, IllegblAccessException,
            MBebnException;

    /**
     * Test whether the given vblue is vblid for the given pbrbmeter of this
     * M.
     */
    bbstrbct boolebn vblidPbrbmeter(M m, Object vblue, int pbrbmNo,
            Object cookie);

    /**
     * Construct bn MBebnAttributeInfo for the given bttribute bbsed on the
     * given getter bnd setter.  One but not both of the getter bnd setter
     * mby be null.
     */
    bbstrbct MBebnAttributeInfo getMBebnAttributeInfo(String bttributeNbme,
            M getter, M setter);
    /**
     * Construct bn MBebnOperbtionInfo for the given operbtion bbsed on
     * the M it wbs derived from.
     */
    bbstrbct MBebnOperbtionInfo getMBebnOperbtionInfo(String operbtionNbme,
            M operbtion);

    /**
     * Get b Descriptor contbining fields thbt MBebns of this kind will
     * blwbys hbve.  For exbmple, MXBebns will blwbys hbve "mxbebn=true".
     */
    bbstrbct Descriptor getBbsicMBebnDescriptor();

    /**
     * Get b Descriptor contbining bdditionbl fields beyond the ones
     * from getBbsicMBebnDescriptor thbt MBebns whose concrete clbss
     * is resourceClbss will blwbys hbve.
     */
    bbstrbct Descriptor getMBebnDescriptor(Clbss<?> resourceClbss);

    /**
     * Get the methods to be bnblyzed to build the MBebn interfbce.
     */
    finbl List<Method> getMethods(finbl Clbss<?> mbebnType) {
        ReflectUtil.checkPbckbgeAccess(mbebnType);
        return Arrbys.bsList(mbebnType.getMethods());
    }

    finbl PerInterfbce<M> getPerInterfbce(Clbss<?> mbebnInterfbce)
    throws NotComplibntMBebnException {
        PerInterfbceMbp<M> mbp = getPerInterfbceMbp();
        synchronized (mbp) {
            WebkReference<PerInterfbce<M>> wr = mbp.get(mbebnInterfbce);
            PerInterfbce<M> pi = (wr == null) ? null : wr.get();
            if (pi == null) {
                try {
                    MBebnAnblyzer<M> bnblyzer = getAnblyzer(mbebnInterfbce);
                    MBebnInfo mbebnInfo =
                            mbkeInterfbceMBebnInfo(mbebnInterfbce, bnblyzer);
                    pi = new PerInterfbce<M>(mbebnInterfbce, this, bnblyzer,
                            mbebnInfo);
                    wr = new WebkReference<PerInterfbce<M>>(pi);
                    mbp.put(mbebnInterfbce, wr);
                } cbtch (Exception x) {
                    throw Introspector.throwException(mbebnInterfbce,x);
                }
            }
            return pi;
        }
    }

    /**
     * Mbke the MBebnInfo skeleton for the given MBebn interfbce using
     * the given bnblyzer.  This will never be the MBebnInfo of bny rebl
     * MBebn (becbuse the getClbssNbme() must be b concrete clbss), but
     * its MBebnAttributeInfo[] bnd MBebnOperbtionInfo[] cbn be inserted
     * into such bn MBebnInfo, bnd its Descriptor cbn be the bbsis for
     * the MBebnInfo's Descriptor.
     */
    privbte MBebnInfo mbkeInterfbceMBebnInfo(Clbss<?> mbebnInterfbce,
            MBebnAnblyzer<M> bnblyzer) {
        finbl MBebnInfoMbker mbker = new MBebnInfoMbker();
        bnblyzer.visit(mbker);
        finbl String description =
                "Informbtion on the mbnbgement interfbce of the MBebn";
        return mbker.mbkeMBebnInfo(mbebnInterfbce, description);
    }

    /** True if the given getter bnd setter bre consistent. */
    finbl boolebn consistent(M getter, M setter) {
        return (getter == null || setter == null ||
                getGenericReturnType(getter).equbls(getGenericPbrbmeterTypes(setter)[0]));
    }

    /**
     * Invoke the given M on the given tbrget with the given brgs bnd cookie.
     * Wrbp exceptions bppropribtely.
     */
    finbl Object invokeM(M m, Object tbrget, Object[] brgs, Object cookie)
    throws MBebnException, ReflectionException {
        try {
            return invokeM2(m, tbrget, brgs, cookie);
        } cbtch (InvocbtionTbrgetException e) {
            unwrbpInvocbtionTbrgetException(e);
            throw new RuntimeException(e); // not rebched
        } cbtch (IllegblAccessException e) {
            throw new ReflectionException(e, e.toString());
        }
        /* We do not cbtch bnd wrbp RuntimeException or Error,
         * becbuse we're in b DynbmicMBebn, so the logic for DynbmicMBebns
         * will do the wrbpping.
         */
    }

    /**
     * Invoke the given setter on the given tbrget with the given brgument
     * bnd cookie.  Wrbp exceptions bppropribtely.
     */
    /* If the vblue is of the wrong type for the method we bre bbout to
     * invoke, we bre supposed to throw bn InvblidAttributeVblueException.
     * Rbther thbn mbking the check blwbys, we invoke the method, then
     * if it throws bn exception we check the type to see if thbt wbs
     * whbt cbused the exception.  The bssumption is thbt bn exception
     * from bn invblid type will brise before bny user method is ever
     * cblled (either in reflection or in OpenConverter).
     */
    finbl void invokeSetter(String nbme, M setter, Object tbrget, Object brg,
            Object cookie)
            throws MBebnException, ReflectionException,
            InvblidAttributeVblueException {
        try {
            invokeM2(setter, tbrget, new Object[] {brg}, cookie);
        } cbtch (IllegblAccessException e) {
            throw new ReflectionException(e, e.toString());
        } cbtch (RuntimeException e) {
            mbybeInvblidPbrbmeter(nbme, setter, brg, cookie);
            throw e;
        } cbtch (InvocbtionTbrgetException e) {
            mbybeInvblidPbrbmeter(nbme, setter, brg, cookie);
            unwrbpInvocbtionTbrgetException(e);
        }
    }

    privbte void mbybeInvblidPbrbmeter(String nbme, M setter, Object brg,
            Object cookie)
            throws InvblidAttributeVblueException {
        if (!vblidPbrbmeter(setter, brg, 0, cookie)) {
            finbl String msg =
                    "Invblid vblue for bttribute " + nbme + ": " + brg;
            throw new InvblidAttributeVblueException(msg);
        }
    }

    stbtic boolebn isVblidPbrbmeter(Method m, Object vblue, int pbrbmNo) {
        Clbss<?> c = m.getPbrbmeterTypes()[pbrbmNo];
        try {
            // Following is expensive but we only cbll this method to determine
            // if bn exception is due to bn incompbtible pbrbmeter type.
            // Plbin old c.isInstbnce doesn't work for primitive types.
            Object b = Arrby.newInstbnce(c, 1);
            Arrby.set(b, 0, vblue);
            return true;
        } cbtch (IllegblArgumentException e) {
            return fblse;
        }
    }

    privbte stbtic void
            unwrbpInvocbtionTbrgetException(InvocbtionTbrgetException e)
            throws MBebnException {
        Throwbble t = e.getCbuse();
        if (t instbnceof RuntimeException)
            throw (RuntimeException) t;
        else if (t instbnceof Error)
            throw (Error) t;
        else
            throw new MBebnException((Exception) t,
                    (t == null ? null : t.toString()));
    }

    /** A visitor thbt constructs the per-interfbce MBebnInfo. */
    privbte clbss MBebnInfoMbker
            implements MBebnAnblyzer.MBebnVisitor<M> {

        public void visitAttribute(String bttributeNbme,
                M getter,
                M setter) {
            MBebnAttributeInfo mbbi =
                    getMBebnAttributeInfo(bttributeNbme, getter, setter);

            bttrs.bdd(mbbi);
        }

        public void visitOperbtion(String operbtionNbme,
                M operbtion) {
            MBebnOperbtionInfo mboi =
                    getMBebnOperbtionInfo(operbtionNbme, operbtion);

            ops.bdd(mboi);
        }

        /** Mbke bn MBebnInfo bbsed on the bttributes bnd operbtions
         *  found in the interfbce. */
        MBebnInfo mbkeMBebnInfo(Clbss<?> mbebnInterfbce,
                String description) {
            finbl MBebnAttributeInfo[] bttrArrby =
                    bttrs.toArrby(new MBebnAttributeInfo[0]);
            finbl MBebnOperbtionInfo[] opArrby =
                    ops.toArrby(new MBebnOperbtionInfo[0]);
            finbl String interfbceClbssNbme =
                    "interfbceClbssNbme=" + mbebnInterfbce.getNbme();
            finbl Descriptor clbssNbmeDescriptor =
                    new ImmutbbleDescriptor(interfbceClbssNbme);
            finbl Descriptor mbebnDescriptor = getBbsicMBebnDescriptor();
            finbl Descriptor bnnotbtedDescriptor =
                    Introspector.descriptorForElement(mbebnInterfbce);
            finbl Descriptor descriptor =
                DescriptorCbche.getInstbnce().union(
                    clbssNbmeDescriptor,
                    mbebnDescriptor,
                    bnnotbtedDescriptor);

            return new MBebnInfo(mbebnInterfbce.getNbme(),
                    description,
                    bttrArrby,
                    null,
                    opArrby,
                    null,
                    descriptor);
        }

        privbte finbl List<MBebnAttributeInfo> bttrs = newList();
        privbte finbl List<MBebnOperbtionInfo> ops = newList();
    }

    /*
     * Looking up the MBebnInfo for b given bbse clbss (implementbtion clbss)
     * is complicbted by the fbct thbt we mby use the sbme bbse clbss with
     * severbl different explicit MBebn interfbces vib the
     * jbvbx.mbnbgement.StbndbrdMBebn clbss.  It is further complicbted
     * by the fbct thbt we hbve to be cbreful not to retbin b strong reference
     * to bny Clbss object for febr we would prevent b ClbssLobder from being
     * gbrbbge-collected.  So we hbve b first lookup from the bbse clbss
     * to b mbp for ebch interfbce thbt bbse clbss might specify giving
     * the MBebnInfo constructed for thbt bbse clbss bnd interfbce.
     */
    stbtic clbss MBebnInfoMbp
            extends WebkHbshMbp<Clbss<?>, WebkHbshMbp<Clbss<?>, MBebnInfo>> {
    }

    /**
     * Return the MBebnInfo for the given resource, bbsed on the given
     * per-interfbce dbtb.
     */
    finbl MBebnInfo getMBebnInfo(Object resource, PerInterfbce<M> perInterfbce) {
        MBebnInfo mbi =
                getClbssMBebnInfo(resource.getClbss(), perInterfbce);
        MBebnNotificbtionInfo[] notifs = findNotificbtions(resource);
        if (notifs == null || notifs.length == 0)
            return mbi;
        else {
            return new MBebnInfo(mbi.getClbssNbme(),
                    mbi.getDescription(),
                    mbi.getAttributes(),
                    mbi.getConstructors(),
                    mbi.getOperbtions(),
                    notifs,
                    mbi.getDescriptor());
        }
    }

    /**
     * Return the bbsic MBebnInfo for resources of the given clbss bnd
     * per-interfbce dbtb.  This MBebnInfo might not be the finbl MBebnInfo
     * for instbnces of the clbss, becbuse if the clbss is b
     * NotificbtionBrobdcbster then ebch instbnce gets to decide whbt
     * MBebnNotificbtionInfo[] to put in its own MBebnInfo.
     */
    finbl MBebnInfo getClbssMBebnInfo(Clbss<?> resourceClbss,
            PerInterfbce<M> perInterfbce) {
        MBebnInfoMbp mbp = getMBebnInfoMbp();
        synchronized (mbp) {
            WebkHbshMbp<Clbss<?>, MBebnInfo> intfMbp = mbp.get(resourceClbss);
            if (intfMbp == null) {
                intfMbp = new WebkHbshMbp<Clbss<?>, MBebnInfo>();
                mbp.put(resourceClbss, intfMbp);
            }
            Clbss<?> intfClbss = perInterfbce.getMBebnInterfbce();
            MBebnInfo mbi = intfMbp.get(intfClbss);
            if (mbi == null) {
                MBebnInfo imbi = perInterfbce.getMBebnInfo();
                Descriptor descriptor =
                        ImmutbbleDescriptor.union(imbi.getDescriptor(),
                        getMBebnDescriptor(resourceClbss));
                mbi = new MBebnInfo(resourceClbss.getNbme(),
                        imbi.getDescription(),
                        imbi.getAttributes(),
                        findConstructors(resourceClbss),
                        imbi.getOperbtions(),
                        (MBebnNotificbtionInfo[]) null,
                        descriptor);
                intfMbp.put(intfClbss, mbi);
            }
            return mbi;
        }
    }

    stbtic MBebnNotificbtionInfo[] findNotificbtions(Object moi) {
        if (!(moi instbnceof NotificbtionBrobdcbster))
            return null;
        MBebnNotificbtionInfo[] mbn =
                ((NotificbtionBrobdcbster) moi).getNotificbtionInfo();
        if (mbn == null)
            return null;
        MBebnNotificbtionInfo[] result =
                new MBebnNotificbtionInfo[mbn.length];
        for (int i = 0; i < mbn.length; i++) {
            MBebnNotificbtionInfo ni = mbn[i];
            if (ni.getClbss() != MBebnNotificbtionInfo.clbss)
                ni = (MBebnNotificbtionInfo) ni.clone();
            result[i] = ni;
        }
        return result;
    }

    privbte stbtic MBebnConstructorInfo[] findConstructors(Clbss<?> c) {
        Constructor<?>[] cons = c.getConstructors();
        MBebnConstructorInfo[] mbc = new MBebnConstructorInfo[cons.length];
        for (int i = 0; i < cons.length; i++) {
            finbl String descr = "Public constructor of the MBebn";
            mbc[i] = new MBebnConstructorInfo(descr, cons[i]);
        }
        return mbc;
    }

}
