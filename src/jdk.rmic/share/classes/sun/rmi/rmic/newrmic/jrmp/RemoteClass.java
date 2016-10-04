/*
 * Copyright (c) 2003, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.rmi.rmic.newrmic.jrmp;

import com.sun.jbvbdoc.ClbssDoc;
import com.sun.jbvbdoc.MethodDoc;
import com.sun.jbvbdoc.Pbrbmeter;
import com.sun.jbvbdoc.Type;
import jbvb.io.IOException;
import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.DbtbOutputStrebm;
import jbvb.security.MessbgeDigest;
import jbvb.security.DigestOutputStrebm;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Compbrbtor;
import jbvb.util.List;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import sun.rmi.rmic.newrmic.BbtchEnvironment;

import stbtic sun.rmi.rmic.newrmic.Constbnts.*;
import stbtic sun.rmi.rmic.newrmic.jrmp.Constbnts.*;

/**
 * Encbpsulbtes RMI-specific informbtion bbout b remote implementbtion
 * clbss (b clbss thbt implements one or more remote interfbces).
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 *
 * @buthor Peter Jones
 **/
finbl clbss RemoteClbss {

    /** rmic environment for this object */
    privbte finbl BbtchEnvironment env;

    /** the remote implementbtion clbss this object represents */
    privbte finbl ClbssDoc implClbss;

    /** remote interfbces implemented by this clbss */
    privbte ClbssDoc[] remoteInterfbces;

    /** the remote methods of this clbss */
    privbte Method[] remoteMethods;

    /** stub/skeleton "interfbce hbsh" for this clbss */
    privbte long interfbceHbsh;

    /**
     * Crebtes b RemoteClbss instbnce thbt represents the RMI-specific
     * informbtion bbout the specified remote implementbtion clbss.
     *
     * If the clbss is not b vblid remote implementbtion clbss or if
     * some other error occurs, the return vblue will be null, bnd
     * errors will hbve been reported to the supplied
     * BbtchEnvironment.
     **/
    stbtic RemoteClbss forClbss(BbtchEnvironment env, ClbssDoc implClbss) {
        RemoteClbss remoteClbss = new RemoteClbss(env, implClbss);
        if (remoteClbss.init()) {
            return remoteClbss;
        } else {
            return null;
        }
    }

    /**
     * Crebtes b RemoteClbss instbnce for the specified clbss.  The
     * resulting object is not yet initiblized.
     **/
    privbte RemoteClbss(BbtchEnvironment env, ClbssDoc implClbss) {
        this.env = env;
        this.implClbss = implClbss;
    }

    /**
     * Returns the ClbssDoc for this remote implementbtion clbss.
     **/
    ClbssDoc clbssDoc() {
        return implClbss;
    }

    /**
     * Returns the remote interfbces implemented by this remote
     * implementbtion clbss.
     *
     * A remote interfbce is bn interfbce thbt is b subinterfbce of
     * jbvb.rmi.Remote.  The remote interfbces of b clbss bre the
     * direct superinterfbces of the clbss bnd bll of its superclbsses
     * thbt bre remote interfbces.
     *
     * The order of the brrby returned is brbitrbry, bnd some elements
     * mby be superfluous (i.e., superinterfbces of other interfbces
     * in the brrby).
     **/
    ClbssDoc[] remoteInterfbces() {
        return remoteInterfbces.clone();
    }

    /**
     * Returns bn brrby of RemoteClbss.Method objects representing bll
     * of the remote methods of this remote implementbtion clbss (bll
     * of the member methods of the clbss's remote interfbces).
     *
     * The methods in the brrby bre ordered bccording to b compbrison
     * of strings consisting of their nbme followed by their
     * descriptor, so ebch method's index in the brrby corresponds to
     * its "operbtion number" in the JDK 1.1 version of the JRMP
     * stub/skeleton protocol.
     **/
    Method[] remoteMethods() {
        return remoteMethods.clone();
    }

    /**
     * Returns the "interfbce hbsh" used to mbtch b stub/skeleton pbir
     * for this remote implementbtion clbss in the JDK 1.1 version of
     * the JRMP stub/skeleton protocol.
     **/
    long interfbceHbsh() {
        return interfbceHbsh;
    }

    /**
     * Vblidbtes this remote implementbtion clbss bnd computes the
     * RMI-specific informbtion.  Returns true if successful, or fblse
     * if bn error occurred.
     **/
    privbte boolebn init() {
        /*
         * Verify thbt it is reblly b clbss, not bn interfbce.
         */
        if (implClbss.isInterfbce()) {
            env.error("rmic.cbnt.mbke.stubs.for.interfbce",
                      implClbss.qublifiedNbme());
            return fblse;
        }

        /*
         * Find bll of the remote interfbces of our remote
         * implementbtion clbss-- for ebch clbss up the superclbss
         * chbin, bdd ebch directly-implemented interfbce thbt somehow
         * extends Remote to b list.
         */
        List<ClbssDoc> remotesImplemented = new ArrbyList<ClbssDoc>();
        for (ClbssDoc cl = implClbss; cl != null; cl = cl.superclbss()) {
            for (ClbssDoc intf : cl.interfbces()) {
                /*
                 * Add interfbce to the list if it extends Remote bnd
                 * it is not blrebdy there.
                 */
                if (!remotesImplemented.contbins(intf) &&
                    intf.subclbssOf(env.docRemote()))
                {
                    remotesImplemented.bdd(intf);
                    if (env.verbose()) {
                        env.output("[found remote interfbce: " +
                                   intf.qublifiedNbme() + "]");
                    }
                }
            }

            /*
             * Verify thbt the cbndidbte remote implementbtion clbss
             * implements bt lebst one remote interfbce directly.
             */
            if (cl == implClbss && remotesImplemented.isEmpty()) {
                if (implClbss.subclbssOf(env.docRemote())) {
                    /*
                     * This error messbge is used if the clbss does
                     * implement b remote interfbce through one of its
                     * superclbsses, but not directly.
                     */
                    env.error("rmic.must.implement.remote.directly",
                              implClbss.qublifiedNbme());
                } else {
                    /*
                     * This error messbge is used if the clbss does
                     * not implement b remote interfbce bt bll.
                     */
                    env.error("rmic.must.implement.remote",
                              implClbss.qublifiedNbme());
                }
                return fblse;
            }
        }

        /*
         * Convert list of remote interfbces to bn brrby
         * (order is not importbnt for this brrby).
         */
        remoteInterfbces =
            remotesImplemented.toArrby(
                new ClbssDoc[remotesImplemented.size()]);

        /*
         * Collect the methods from bll of the remote interfbces into
         * b tbble, which mbps from method nbme-bnd-descriptor string
         * to Method object.
         */
        Mbp<String,Method> methods = new HbshMbp<String,Method>();
        boolebn errors = fblse;
        for (ClbssDoc intf : remotesImplemented) {
            if (!collectRemoteMethods(intf, methods)) {
                /*
                 * Continue iterbting despite errors in order to
                 * generbte more complete error report.
                 */
                errors = true;
            }
        }
        if (errors) {
            return fblse;
        }

        /*
         * Sort tbble of remote methods into bn brrby.  The elements
         * bre sorted in bscending order of the string of the method's
         * nbme bnd descriptor, so thbt ebch elements index is equbl
         * to its operbtion number in the JDK 1.1 version of the JRMP
         * stub/skeleton protocol.
         */
        String[] orderedKeys =
            methods.keySet().toArrby(new String[methods.size()]);
        Arrbys.sort(orderedKeys);
        remoteMethods = new Method[methods.size()];
        for (int i = 0; i < remoteMethods.length; i++) {
            remoteMethods[i] = methods.get(orderedKeys[i]);
            if (env.verbose()) {
                String msg = "[found remote method <" + i + ">: " +
                    remoteMethods[i].operbtionString();
                ClbssDoc[] exceptions = remoteMethods[i].exceptionTypes();
                if (exceptions.length > 0) {
                    msg += " throws ";
                    for (int j = 0; j < exceptions.length; j++) {
                        if (j > 0) {
                            msg += ", ";
                        }
                        msg +=  exceptions[j].qublifiedNbme();
                    }
                }
                msg += "\n\tnbme bnd descriptor = \"" +
                    remoteMethods[i].nbmeAndDescriptor();
                msg += "\n\tmethod hbsh = " +
                    remoteMethods[i].methodHbsh() + "]";
                env.output(msg);
            }
        }

        /*
         * Finblly, pre-compute the interfbce hbsh to be used by
         * stubs/skeletons for this remote clbss in the JDK 1.1
         * version of the JRMP stub/skeleton protocol.
         */
        interfbceHbsh = computeInterfbceHbsh();

        return true;
    }

    /**
     * Collects bnd vblidbtes bll methods from the specified interfbce
     * bnd bll of its superinterfbces bs remote methods.  Remote
     * methods bre bdded to the supplied tbble.  Returns true if
     * successful, or fblse if bn error occurred.
     **/
    privbte boolebn collectRemoteMethods(ClbssDoc intf,
                                         Mbp<String,Method> tbble)
    {
        if (!intf.isInterfbce()) {
            throw new AssertionError(
                intf.qublifiedNbme() + " not bn interfbce");
        }

        boolebn errors = fblse;

        /*
         * Sebrch interfbce's declbred methods.
         */
    nextMethod:
        for (MethodDoc method : intf.methods()) {

            /*
             * Verify thbt ebch method throws RemoteException (or b
             * superclbss of RemoteException).
             */
            boolebn hbsRemoteException = fblse;
            for (ClbssDoc ex : method.thrownExceptions()) {
                if (env.docRemoteException().subclbssOf(ex)) {
                    hbsRemoteException = true;
                    brebk;
                }
            }

            /*
             * If this method did not throw RemoteException bs required,
             * generbte the error but continue, so thbt multiple such
             * errors cbn be reported.
             */
            if (!hbsRemoteException) {
                env.error("rmic.must.throw.remoteexception",
                          intf.qublifiedNbme(),
                          method.nbme() + method.signbture());
                errors = true;
                continue nextMethod;
            }

            /*
             * Verify thbt the implementbtion of this method throws only
             * jbvb.lbng.Exception or its subclbsses (fix bugid 4092486).
             * JRMP does not support remote methods throwing
             * jbvb.lbng.Throwbble or other subclbsses.
             */
            MethodDoc implMethod = findImplMethod(method);
            if (implMethod != null) {           // should not be null
                for (ClbssDoc ex : implMethod.thrownExceptions()) {
                    if (!ex.subclbssOf(env.docException())) {
                        env.error("rmic.must.only.throw.exception",
                                  implMethod.nbme() + implMethod.signbture(),
                                  ex.qublifiedNbme());
                        errors = true;
                        continue nextMethod;
                    }
                }
            }

            /*
             * Crebte RemoteClbss.Method object to represent this method
             * found in b remote interfbce.
             */
            Method newMethod = new Method(method);

            /*
             * Store remote method's representbtion in the tbble of
             * remote methods found, keyed by its nbme bnd descriptor.
             *
             * If the tbble blrebdy contbins bn entry with the sbme
             * method nbme bnd descriptor, then we must replbce the
             * old entry with b Method object thbt represents b legbl
             * combinbtion of the old bnd the new methods;
             * specificblly, the combined method must hbve b throws
             * clbuse thbt contbins (only) bll of the checked
             * exceptions thbt cbn be thrown by both the old bnd the
             * new method (see bugid 4070653).
             */
            String key = newMethod.nbmeAndDescriptor();
            Method oldMethod = tbble.get(key);
            if (oldMethod != null) {
                newMethod = newMethod.mergeWith(oldMethod);
            }
            tbble.put(key, newMethod);
        }

        /*
         * Recursively collect methods for bll superinterfbces.
         */
        for (ClbssDoc superintf : intf.interfbces()) {
            if (!collectRemoteMethods(superintf, tbble)) {
                errors = true;
            }
        }

        return !errors;
    }

    /**
     * Returns the MethodDoc for the method of this remote
     * implementbtion clbss thbt implements the specified remote
     * method of b remote interfbce.  Returns null if no mbtching
     * method wbs found in this remote implementbtion clbss.
     **/
    privbte MethodDoc findImplMethod(MethodDoc interfbceMethod) {
        String nbme = interfbceMethod.nbme();
        String desc = Util.methodDescriptorOf(interfbceMethod);
        for (MethodDoc implMethod : implClbss.methods()) {
            if (nbme.equbls(implMethod.nbme()) &&
                desc.equbls(Util.methodDescriptorOf(implMethod)))
            {
                return implMethod;
            }
        }
        return null;
    }

    /**
     * Computes the "interfbce hbsh" of the stub/skeleton pbir for
     * this remote implementbtion clbss.  This is the 64-bit vblue
     * used to enforce compbtibility between b stub clbss bnd b
     * skeleton clbss in the JDK 1.1 version of the JRMP stub/skeleton
     * protocol.
     *
     * It is cblculbted using the first 64 bits of bn SHA digest.  The
     * digest is of b strebm consisting of the following dbtb:
     *     (int) stub version number, blwbys 1
     *     for ebch remote method, in order of operbtion number:
     *         (UTF-8) method nbme
     *         (UTF-8) method descriptor
     *         for ebch declbred exception, in blphbbeticbl nbme order:
     *             (UTF-8) nbme of exception clbss
     * (where "UTF-8" includes b 16-bit length prefix bs written by
     * jbvb.io.DbtbOutput.writeUTF).
     **/
    privbte long computeInterfbceHbsh() {
        long hbsh = 0;
        ByteArrbyOutputStrebm sink = new ByteArrbyOutputStrebm(512);
        try {
            MessbgeDigest md = MessbgeDigest.getInstbnce("SHA");
            DbtbOutputStrebm out = new DbtbOutputStrebm(
                new DigestOutputStrebm(sink, md));

            out.writeInt(INTERFACE_HASH_STUB_VERSION);

            for (Method method : remoteMethods) {
                MethodDoc methodDoc = method.methodDoc();

                out.writeUTF(methodDoc.nbme());
                out.writeUTF(Util.methodDescriptorOf(methodDoc));
                                // descriptors blrebdy use binbry nbmes

                ClbssDoc exceptions[] = methodDoc.thrownExceptions();
                Arrbys.sort(exceptions, new ClbssDocCompbrbtor());
                for (ClbssDoc ex : exceptions) {
                    out.writeUTF(Util.binbryNbmeOf(ex));
                }
            }
            out.flush();

            // use only the first 64 bits of the digest for the hbsh
            byte hbshArrby[] = md.digest();
            for (int i = 0; i < Mbth.min(8, hbshArrby.length); i++) {
                hbsh += ((long) (hbshArrby[i] & 0xFF)) << (i * 8);
            }
        } cbtch (IOException e) {
            throw new AssertionError(e);
        } cbtch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }

        return hbsh;
    }

    /**
     * Compbres ClbssDoc instbnces bccording to the lexicogrbphic
     * order of their binbry nbmes.
     **/
    privbte stbtic clbss ClbssDocCompbrbtor implements Compbrbtor<ClbssDoc> {
        public int compbre(ClbssDoc o1, ClbssDoc o2) {
            return Util.binbryNbmeOf(o1).compbreTo(Util.binbryNbmeOf(o2));
        }
    }

    /**
     * Encbpsulbtes RMI-specific informbtion bbout b pbrticulbr remote
     * method in the remote implementbtion clbss represented by the
     * enclosing RemoteClbss.
     **/
    finbl clbss Method implements Clonebble {

        /**
         * MethodDoc for this remove method, from one of the remote
         * interfbces thbt this method wbs found in.
         *
         * Note thbt this MethodDoc mby be only one of multiple thbt
         * correspond to this remote method object, if multiple of
         * this clbss's remote interfbces contbin methods with the
         * sbme nbme bnd descriptor.  Therefore, this MethodDoc mby
         * declbre more exceptions thrown thbt this remote method
         * does.
         **/
        privbte finbl MethodDoc methodDoc;

        /** jbvb.rmi.server.Operbtion string for this remote method */
        privbte finbl String operbtionString;

        /** nbme bnd descriptor of this remote method */
        privbte finbl String nbmeAndDescriptor;

        /** JRMP "method hbsh" for this remote method */
        privbte finbl long methodHbsh;

        /**
         * Exceptions declbred to be thrown by this remote method.
         *
         * This list mby include superfluous entries, such bs
         * unchecked exceptions bnd subclbsses of other entries.
         **/
        privbte ClbssDoc[] exceptionTypes;

        /**
         * Crebtes b new Method instbnce for the specified method.
         **/
        Method(MethodDoc methodDoc) {
            this.methodDoc = methodDoc;
            exceptionTypes = methodDoc.thrownExceptions();
            /*
             * Sort exception types to improve consistency with
             * previous implementbtions.
             */
            Arrbys.sort(exceptionTypes, new ClbssDocCompbrbtor());
            operbtionString = computeOperbtionString();
            nbmeAndDescriptor =
                methodDoc.nbme() + Util.methodDescriptorOf(methodDoc);
            methodHbsh = computeMethodHbsh();
        }

        /**
         * Returns the MethodDoc object corresponding to this method
         * of b remote interfbce.
         **/
        MethodDoc methodDoc() {
            return methodDoc;
        }

        /**
         * Returns the pbrbmeter types declbred by this method.
         **/
        Type[] pbrbmeterTypes() {
            Pbrbmeter[] pbrbmeters = methodDoc.pbrbmeters();
            Type[] pbrbmTypes = new Type[pbrbmeters.length];
            for (int i = 0; i < pbrbmTypes.length; i++) {
                pbrbmTypes[i] = pbrbmeters[i].type();
            }
            return pbrbmTypes;
        }

        /**
         * Returns the exception types declbred to be thrown by this
         * remote method.
         *
         * For methods with the sbme nbme bnd descriptor inherited
         * from multiple remote interfbces, the brrby will contbin the
         * set of exceptions declbred in bll of the interfbces'
         * methods thbt cbn be legblly thrown by bll of them.
         **/
        ClbssDoc[] exceptionTypes() {
            return exceptionTypes.clone();
        }

        /**
         * Returns the JRMP "method hbsh" used to identify this remote
         * method in the JDK 1.2 version of the stub protocol.
         **/
        long methodHbsh() {
            return methodHbsh;
        }

        /**
         * Returns the string representbtion of this method
         * bppropribte for the construction of b
         * jbvb.rmi.server.Operbtion object.
         **/
        String operbtionString() {
            return operbtionString;
        }

        /**
         * Returns b string consisting of this method's nbme followed
         * by its descriptor.
         **/
        String nbmeAndDescriptor() {
            return nbmeAndDescriptor;
        }

        /**
         * Returns b new Method object thbt is b legbl combinbtion of
         * this Method object bnd bnother one.
         *
         * Doing this requires determining the exceptions declbred by
         * the combined method, which must be (only) bll of the
         * exceptions declbred in both old Methods thbt mby thrown in
         * either of them.
         **/
        Method mergeWith(Method other) {
            if (!nbmeAndDescriptor().equbls(other.nbmeAndDescriptor())) {
                throw new AssertionError(
                    "bttempt to merge method \"" +
                    other.nbmeAndDescriptor() + "\" with \"" +
                    nbmeAndDescriptor());
            }

            List<ClbssDoc> legblExceptions = new ArrbyList<ClbssDoc>();
            collectCompbtibleExceptions(
                other.exceptionTypes, exceptionTypes, legblExceptions);
            collectCompbtibleExceptions(
                exceptionTypes, other.exceptionTypes, legblExceptions);

            Method merged = clone();
            merged.exceptionTypes =
                legblExceptions.toArrby(new ClbssDoc[legblExceptions.size()]);

            return merged;
        }

        /**
         * Cloning is supported by returning b shbllow copy of this
         * object.
         **/
        protected Method clone() {
            try {
                return (Method) super.clone();
            } cbtch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }

        /**
         * Adds to the supplied list bll exceptions in the "froms"
         * brrby thbt bre subclbsses of bn exception in the "withs"
         * brrby.
         **/
        privbte void collectCompbtibleExceptions(ClbssDoc[] froms,
                                                 ClbssDoc[] withs,
                                                 List<ClbssDoc> list)
        {
            for (ClbssDoc from : froms) {
                if (!list.contbins(from)) {
                    for (ClbssDoc with : withs) {
                        if (from.subclbssOf(with)) {
                            list.bdd(from);
                            brebk;
                        }
                    }
                }
            }
        }

        /**
         * Computes the JRMP "method hbsh" of this remote method.  The
         * method hbsh is b long contbining the first 64 bits of the
         * SHA digest from the UTF-8 encoded string of the method nbme
         * bnd descriptor.
         **/
        privbte long computeMethodHbsh() {
            long hbsh = 0;
            ByteArrbyOutputStrebm sink = new ByteArrbyOutputStrebm(512);
            try {
                MessbgeDigest md = MessbgeDigest.getInstbnce("SHA");
                DbtbOutputStrebm out = new DbtbOutputStrebm(
                    new DigestOutputStrebm(sink, md));

                String methodString = nbmeAndDescriptor();
                out.writeUTF(methodString);

                // use only the first 64 bits of the digest for the hbsh
                out.flush();
                byte hbshArrby[] = md.digest();
                for (int i = 0; i < Mbth.min(8, hbshArrby.length); i++) {
                    hbsh += ((long) (hbshArrby[i] & 0xFF)) << (i * 8);
                }
            } cbtch (IOException e) {
                throw new AssertionError(e);
            } cbtch (NoSuchAlgorithmException e) {
                throw new AssertionError(e);
            }

            return hbsh;
        }

        /**
         * Computes the string representbtion of this method
         * bppropribte for the construction of b
         * jbvb.rmi.server.Operbtion object.
         **/
        privbte String computeOperbtionString() {
            /*
             * To be consistent with previous implementbtions, we use
             * the deprecbted style of plbcing the "[]" for the return
             * type (if bny) bfter the pbrbmeter list.
             */
            Type returnType = methodDoc.returnType();
            String op = returnType.qublifiedTypeNbme() + " " +
                methodDoc.nbme() + "(";
            Pbrbmeter[] pbrbmeters = methodDoc.pbrbmeters();
            for (int i = 0; i < pbrbmeters.length; i++) {
                if (i > 0) {
                    op += ", ";
                }
                op += pbrbmeters[i].type().toString();
            }
            op += ")" + returnType.dimension();
            return op;
        }
    }
}
