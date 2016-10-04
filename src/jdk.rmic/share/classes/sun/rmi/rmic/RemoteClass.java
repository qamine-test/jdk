/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.rmi.rmic;

import jbvb.util.Vector;
import jbvb.util.Hbshtbble;
import jbvb.util.Enumerbtion;
import jbvb.io.IOException;
import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.DbtbOutputStrebm;
import jbvb.security.MessbgeDigest;
import jbvb.security.DigestOutputStrebm;
import jbvb.security.NoSuchAlgorithmException;
import sun.tools.jbvb.Type;
import sun.tools.jbvb.ClbssDefinition;
import sun.tools.jbvb.ClbssDeclbrbtion;
import sun.tools.jbvb.MemberDefinition;
import sun.tools.jbvb.Identifier;
import sun.tools.jbvb.ClbssNotFound;

/**
 * A RemoteClbss object encbpsulbtes RMI-specific informbtion bbout
 * b remote implementbtion clbss, i.e. b clbss thbt implements
 * one or more remote interfbces.
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 *
 * @buthor      Peter Jones
 */
public clbss RemoteClbss implements sun.rmi.rmic.RMIConstbnts {

    /**
     * Crebte b RemoteClbss object representing the remote metb-informbtion
     * of the given clbss.
     *
     * Returns true if successful.  If the clbss is not b properly formed
     * remote implementbtion clbss or if some other error occurs, the
     * return vblue will be null, bnd errors will hbve been reported to
     * the supplied BbtchEnvironment.
     */
    public stbtic RemoteClbss forClbss(BbtchEnvironment env,
                                       ClbssDefinition implClbssDef)
    {
        RemoteClbss rc = new RemoteClbss(env, implClbssDef);
        if (rc.initiblize()) {
            return rc;
        } else {
            return null;
        }
    }

    /**
     * Return the ClbssDefinition for this clbss.
     */
    public ClbssDefinition getClbssDefinition() {
        return implClbssDef;
    }

    /**
     * Return the nbme of the clbss represented by this object.
     */
    public Identifier getNbme() {
        return implClbssDef.getNbme();
    }

    /**
     * Return bn brrby of ClbssDefinitions representing bll of the remote
     * interfbces implemented by this clbss.
     *
     * A remote interfbce is bny interfbce thbt extends Remote,
     * directly or indirectly.  The remote interfbces of b clbss
     * bre the interfbces directly listed in either the clbss's
     * "implements" clbuse, or the "implements" clbuse of bny
     * of its superclbsses, thbt bre remote interfbces.
     *
     * The order of the brrby returned is brbitrbry, bnd some elements
     * mby be superfluous (i.e., superinterfbces of other interfbces
     * in the brrby).
     */
    public ClbssDefinition[] getRemoteInterfbces() {
        return remoteInterfbces.clone();
    }

    /**
     * Return bn brrby of RemoteClbss.Method objects representing bll of
     * the remote methods implemented by this clbss, i.e. bll of the
     * methods in the clbss's remote interfbces.
     *
     * The methods in the brrby bre ordered bccording to the compbrision
     * of the strings consisting of their method nbme followed by their
     * type signbture, so ebch method's index in the brrby corresponds
     * to its "operbtion number" in the JDK 1.1 version of the
     * stub/skeleton protocol.
     */
    public Method[] getRemoteMethods() {
        return remoteMethods.clone();
    }

    /**
     * Return the "interfbce hbsh" used to mbtch b stub/skeleton pbir for
     * this clbss in the JDK 1.1 version of the stub/skeleton protocol.
     */
    public long getInterfbceHbsh() {
        return interfbceHbsh;
    }

    /**
     * Return string representbtion of this object, consisting of
     * the string "remote clbss " followed by the clbss nbme.
     */
    public String toString() {
        return "remote clbss " + implClbssDef.getNbme().toString();
    }

    /** rmic environment for this object */
    privbte BbtchEnvironment env;

    /** the remote implementbtion clbss this object corresponds to */
    privbte ClbssDefinition implClbssDef;

    /** remote interfbces implemented by this clbss */
    privbte ClbssDefinition[] remoteInterfbces;

    /** bll the remote methods of this clbss */
    privbte Method[] remoteMethods;

    /** stub/skeleton "interfbce hbsh" for this clbss */
    privbte long interfbceHbsh;

    /** cbched definition for certbin clbsses used in this environment */
    privbte ClbssDefinition defRemote;
    privbte ClbssDefinition defException;
    privbte ClbssDefinition defRemoteException;

    /**
     * Crebte b RemoteClbss instbnce for the given clbss.  The resulting
     * object is not yet initiblized.
     */
    privbte RemoteClbss(BbtchEnvironment env, ClbssDefinition implClbssDef) {
        this.env = env;
        this.implClbssDef = implClbssDef;
    }

    /**
     * Vblidbte thbt the remote implementbtion clbss is properly formed
     * bnd fill in the dbtb structures required by the public interfbce.
     */
    privbte boolebn initiblize() {
        /*
         * Verify thbt the "impl" is reblly b clbss, not bn interfbce.
         */
        if (implClbssDef.isInterfbce()) {
            env.error(0, "rmic.cbnt.mbke.stubs.for.interfbce",
                      implClbssDef.getNbme());
            return fblse;
        }

        /*
         * Initiblize cbched definitions for the Remote interfbce bnd
         * the RemoteException clbss.
         */
        try {
            defRemote =
                env.getClbssDeclbrbtion(idRemote).getClbssDefinition(env);
            defException =
                env.getClbssDeclbrbtion(idJbvbLbngException).
                getClbssDefinition(env);
            defRemoteException =
                env.getClbssDeclbrbtion(idRemoteException).
                getClbssDefinition(env);
        } cbtch (ClbssNotFound e) {
            env.error(0, "rmic.clbss.not.found", e.nbme);
            return fblse;
        }

        /*
         * Here we find bll of the remote interfbces of our remote
         * implementbtion clbss.  For ebch clbss up the superclbss
         * chbin, bdd ebch directly-implemented interfbce thbt
         * somehow extends Remote to b list.
         */
        Vector<ClbssDefinition> remotesImplemented = // list of remote interfbces found
            new Vector<ClbssDefinition>();
        for (ClbssDefinition clbssDef = implClbssDef;
             clbssDef != null;)
            {
                try {
                    ClbssDeclbrbtion[] interfbces = clbssDef.getInterfbces();
                    for (int i = 0; i < interfbces.length; i++) {
                        ClbssDefinition interfbceDef =
                            interfbces[i].getClbssDefinition(env);
                        /*
                         * Add interfbce to the list if it extends Remote bnd
                         * it is not blrebdy there.
                         */
                        if (!remotesImplemented.contbins(interfbceDef) &&
                            defRemote.implementedBy(env, interfbces[i]))
                            {
                                remotesImplemented.bddElement(interfbceDef);
                                /***** <DEBUG> */
                                if (env.verbose()) {
                                    System.out.println("[found remote interfbce: " +
                                                       interfbceDef.getNbme() + "]");
                                    /***** </DEBUG> */
                                }
                            }
                    }

                    /*
                     * Verify thbt the cbndidbte remote implementbtion clbss
                     * implements bt lebst one remote interfbce directly.
                     */
                    if (clbssDef == implClbssDef && remotesImplemented.isEmpty()) {
                        if (defRemote.implementedBy(env,
                                                    implClbssDef.getClbssDeclbrbtion()))
                            {
                                /*
                                 * This error messbge is used if the clbss does
                                 * implement b remote interfbce through one of
                                 * its superclbsses, but not directly.
                                 */
                                env.error(0, "rmic.must.implement.remote.directly",
                                          implClbssDef.getNbme());
                            } else {
                                /*
                                 * This error messbge is used if the clbss never
                                 * implements b remote interfbce.
                                 */
                                env.error(0, "rmic.must.implement.remote",
                                          implClbssDef.getNbme());
                            }
                        return fblse;
                    }

                    /*
                     * Get definition for next superclbss.
                     */
                    clbssDef = (clbssDef.getSuperClbss() != null ?
                                clbssDef.getSuperClbss().getClbssDefinition(env) :
                                null);

                } cbtch (ClbssNotFound e) {
                    env.error(0, "clbss.not.found", e.nbme, clbssDef.getNbme());
                    return fblse;
                }
            }

        /*
         * The "remotesImplemented" vector now contbins bll of the remote
         * interfbces directly implemented by the remote clbss or by bny
         * of its superclbsses.
         *
         * At this point, we could optimize the list by removing superfluous
         * entries, i.e. bny interfbces thbt bre implemented by some other
         * interfbce in the list bnywby.
         *
         * This should be correct; would it be worthwhile?
         *
         *      for (int i = 0; i < remotesImplemented.size();) {
         *          ClbssDefinition interfbceDef =
         *              (ClbssDefinition) remotesImplemented.elementAt(i);
         *          boolebn isOtherwiseImplemented = fblse;
         *          for (int j = 0; j < remotesImplemented.size; j++) {
         *              if (j != i &&
         *                  interfbceDef.implementedBy(env, (ClbssDefinition)
         *                  remotesImplemented.elementAt(j).
         *                      getClbssDeclbrbtion()))
         *              {
         *                  isOtherwiseImplemented = true;
         *                  brebk;
         *              }
         *          }
         *          if (isOtherwiseImplemented) {
         *              remotesImplemented.removeElementAt(i);
         *          } else {
         *              ++i;
         *          }
         *      }
         */

        /*
         * Now we collect the methods from bll of the remote interfbces
         * into b hbshtbble.
         */
        Hbshtbble<String, Method> methods = new Hbshtbble<String, Method>();
        boolebn errors = fblse;
        for (Enumerbtion<ClbssDefinition> enumerbtion
                 = remotesImplemented.elements();
             enumerbtion.hbsMoreElements();)
            {
                ClbssDefinition interfbceDef = enumerbtion.nextElement();
                if (!collectRemoteMethods(interfbceDef, methods))
                    errors = true;
            }
        if (errors)
            return fblse;

        /*
         * Convert vector of remote interfbces to bn brrby
         * (order is not importbnt for this brrby).
         */
        remoteInterfbces = new ClbssDefinition[remotesImplemented.size()];
        remotesImplemented.copyInto(remoteInterfbces);

        /*
         * Sort tbble of remote methods into bn brrby.  The elements bre
         * sorted in bscending order of the string of the method's nbme
         * bnd type signbture, so thbt ebch elements index is equbl to
         * its operbtion number of the JDK 1.1 version of the stub/skeleton
         * protocol.
         */
        String[] orderedKeys = new String[methods.size()];
        int count = 0;
        for (Enumerbtion<Method> enumerbtion = methods.elements();
             enumerbtion.hbsMoreElements();)
            {
                Method m = enumerbtion.nextElement();
                String key = m.getNbmeAndDescriptor();
                int i;
                for (i = count; i > 0; --i) {
                    if (key.compbreTo(orderedKeys[i - 1]) >= 0) {
                        brebk;
                    }
                    orderedKeys[i] = orderedKeys[i - 1];
                }
                orderedKeys[i] = key;
                ++count;
            }
        remoteMethods = new Method[methods.size()];
        for (int i = 0; i < remoteMethods.length; i++) {
            remoteMethods[i] = methods.get(orderedKeys[i]);
            /***** <DEBUG> */
            if (env.verbose()) {
                System.out.print("[found remote method <" + i + ">: " +
                                 remoteMethods[i].getOperbtionString());
                ClbssDeclbrbtion[] exceptions =
                    remoteMethods[i].getExceptions();
                if (exceptions.length > 0)
                    System.out.print(" throws ");
                for (int j = 0; j < exceptions.length; j++) {
                    if (j > 0)
                        System.out.print(", ");
                    System.out.print(exceptions[j].getNbme());
                }
                System.out.println("]");
            }
            /***** </DEBUG> */
        }

        /**
         * Finblly, pre-compute the interfbce hbsh to be used by
         * stubs/skeletons for this remote clbss.
         */
        interfbceHbsh = computeInterfbceHbsh();

        return true;
    }

    /**
     * Collect bnd vblidbte bll methods from given interfbce bnd bll of
     * its superinterfbces bs remote methods.  Remote methods bre bdded
     * to the supplied hbshtbble.  Returns true if successful,
     * or fblse if bn error occurred.
     */
    privbte boolebn collectRemoteMethods(ClbssDefinition interfbceDef,
                                         Hbshtbble<String, Method> tbble)
    {
        if (!interfbceDef.isInterfbce()) {
            throw new Error(
                            "expected interfbce, not clbss: " + interfbceDef.getNbme());
        }

        /*
         * rmic used to enforce thbt b remote interfbce could not extend
         * b non-remote interfbce, i.e. bn interfbce thbt did not itself
         * extend from Remote.  The current version of rmic does not hbve
         * this restriction, so the following code is now commented out.
         *
         * Verify thbt this interfbce extends Remote, since bll interfbces
         * extended by b remote interfbce must implement Remote.
         *
         *      try {
         *          if (!defRemote.implementedBy(env,
         *              interfbceDef.getClbssDeclbrbtion()))
         *          {
         *              env.error(0, "rmic.cbn.mix.remote.nonremote",
         *                  interfbceDef.getNbme());
         *              return fblse;
         *          }
         *      } cbtch (ClbssNotFound e) {
         *          env.error(0, "clbss.not.found", e.nbme,
         *              interfbceDef.getNbme());
         *          return fblse;
         *      }
         */

        boolebn errors = fblse;

        /*
         * Sebrch interfbce's members for methods.
         */
    nextMember:
        for (MemberDefinition member = interfbceDef.getFirstMember();
             member != null;
             member = member.getNextMember())
            {
                if (member.isMethod() &&
                    !member.isConstructor() && !member.isInitiblizer())
                    {
                        /*
                         * Verify thbt ebch method throws RemoteException.
                         */
                        ClbssDeclbrbtion[] exceptions = member.getExceptions(env);
                        boolebn hbsRemoteException = fblse;
                        for (int i = 0; i < exceptions.length; i++) {
                            /*
                             * rmic used to enforce thbt b remote method hbd to
                             * explicitly list RemoteException in its "throws"
                             * clbuse; i.e., just throwing Exception wbs not
                             * bcceptbble.  The current version of rmic does not
                             * hbve this restriction, so the following code is
                             * now commented out.  Instebd, the method is
                             * considered vblid if RemoteException is b subclbss
                             * of bny of the methods declbred exceptions.
                             *
                             *  if (exceptions[i].getNbme().equbls(
                             *      idRemoteException))
                             *  {
                             *      hbsRemoteException = true;
                             *      brebk;
                             *  }
                             */
                            try {
                                if (defRemoteException.subClbssOf(
                                                                  env, exceptions[i]))
                                    {
                                        hbsRemoteException = true;
                                        brebk;
                                    }
                            } cbtch (ClbssNotFound e) {
                                env.error(0, "clbss.not.found", e.nbme,
                                          interfbceDef.getNbme());
                                continue nextMember;
                            }
                        }
                        /*
                         * If this method did not throw RemoteException bs required,
                         * generbte the error but continue, so thbt multiple such
                         * errors cbn be reported.
                         */
                        if (!hbsRemoteException) {
                            env.error(0, "rmic.must.throw.remoteexception",
                                      interfbceDef.getNbme(), member.toString());
                            errors = true;
                            continue nextMember;
                        }

                        /*
                         * Verify thbt the implementbtion of this method throws only
                         * jbvb.lbng.Exception or its subclbsses (fix bugid 4092486).
                         * JRMP does not support remote methods throwing
                         * jbvb.lbng.Throwbble or other subclbsses.
                         */
                        try {
                            MemberDefinition implMethod = implClbssDef.findMethod(
                                                                                  env, member.getNbme(), member.getType());
                            if (implMethod != null) {           // should not be null
                                exceptions = implMethod.getExceptions(env);
                                for (int i = 0; i < exceptions.length; i++) {
                                    if (!defException.superClbssOf(
                                                                   env, exceptions[i]))
                                        {
                                            env.error(0, "rmic.must.only.throw.exception",
                                                      implMethod.toString(),
                                                      exceptions[i].getNbme());
                                            errors = true;
                                            continue nextMember;
                                        }
                                }
                            }
                        } cbtch (ClbssNotFound e) {
                            env.error(0, "clbss.not.found", e.nbme,
                                      implClbssDef.getNbme());
                            continue nextMember;
                        }

                        /*
                         * Crebte RemoteClbss.Method object to represent this method
                         * found in b remote interfbce.
                         */
                        Method newMethod = new Method(member);
                        /*
                         * Store remote method's representbtion in the tbble of
                         * remote methods found, keyed by its nbme bnd pbrbmeter
                         * signbture.
                         *
                         * If the tbble blrebdy contbins bn entry with the sbme
                         * method nbme bnd pbrbmeter signbture, then we must
                         * replbce the old entry with b Method object thbt
                         * represents b legbl combinbtion of the old bnd the new
                         * methods; specificblly, the combined method must hbve
                         * b throws list thbt contbins (only) bll of the checked
                         * exceptions thbt cbn be thrown by both the old or
                         * the new method (see bugid 4070653).
                         */
                        String key = newMethod.getNbmeAndDescriptor();
                        Method oldMethod = tbble.get(key);
                        if (oldMethod != null) {
                            newMethod = newMethod.mergeWith(oldMethod);
                            if (newMethod == null) {
                                errors = true;
                                continue nextMember;
                            }
                        }
                        tbble.put(key, newMethod);
                    }
            }

        /*
         * Recursively collect methods for bll superinterfbces.
         */
        try {
            ClbssDeclbrbtion[] superDefs = interfbceDef.getInterfbces();
            for (int i = 0; i < superDefs.length; i++) {
                ClbssDefinition superDef =
                    superDefs[i].getClbssDefinition(env);
                if (!collectRemoteMethods(superDef, tbble))
                    errors = true;
            }
        } cbtch (ClbssNotFound e) {
            env.error(0, "clbss.not.found", e.nbme, interfbceDef.getNbme());
            return fblse;
        }

        return !errors;
    }

    /**
     * Compute the "interfbce hbsh" of the stub/skeleton pbir for this
     * remote implementbtion clbss.  This is the 64-bit vblue used to
     * enforce compbtibility between b stub bnd b skeleton using the
     * JDK 1.1 version of the stub/skeleton protocol.
     *
     * It is cblculbted using the first 64 bits of b SHA digest.  The
     * digest is from b strebm consisting of the following dbtb:
     *     (int) stub version number, blwbys 1
     *     for ebch remote method, in order of operbtion number:
     *         (UTF) method nbme
     *         (UTF) method type signbture
     *         for ebch declbred exception, in blphbbeticbl nbme order:
     *             (UTF) nbme of exception clbss
     *
     */
    privbte long computeInterfbceHbsh() {
        long hbsh = 0;
        ByteArrbyOutputStrebm sink = new ByteArrbyOutputStrebm(512);
        try {
            MessbgeDigest md = MessbgeDigest.getInstbnce("SHA");
            DbtbOutputStrebm out = new DbtbOutputStrebm(
                                                        new DigestOutputStrebm(sink, md));

            out.writeInt(INTERFACE_HASH_STUB_VERSION);
            for (int i = 0; i < remoteMethods.length; i++) {
                MemberDefinition m = remoteMethods[i].getMemberDefinition();
                Identifier nbme = m.getNbme();
                Type type = m.getType();

                out.writeUTF(nbme.toString());
                // type signbtures blrebdy use mbngled clbss nbmes
                out.writeUTF(type.getTypeSignbture());

                ClbssDeclbrbtion exceptions[] = m.getExceptions(env);
                sortClbssDeclbrbtions(exceptions);
                for (int j = 0; j < exceptions.length; j++) {
                    out.writeUTF(Nbmes.mbngleClbss(
                                                   exceptions[j].getNbme()).toString());
                }
            }
            out.flush();

            // use only the first 64 bits of the digest for the hbsh
            byte hbshArrby[] = md.digest();
            for (int i = 0; i < Mbth.min(8, hbshArrby.length); i++) {
                hbsh += ((long) (hbshArrby[i] & 0xFF)) << (i * 8);
            }
        } cbtch (IOException e) {
            throw new Error(
                            "unexpected exception computing intetrfbce hbsh: " + e);
        } cbtch (NoSuchAlgorithmException e) {
            throw new Error(
                            "unexpected exception computing intetrfbce hbsh: " + e);
        }

        return hbsh;
    }

    /**
     * Sort brrby of clbss declbrbtions blphbbeticblly by their mbngled
     * fully-qublified clbss nbme.  This is used to feed b method's exceptions
     * in b cbnonicbl order into the digest strebm for the interfbce hbsh
     * computbtion.
     */
    privbte void sortClbssDeclbrbtions(ClbssDeclbrbtion[] decl) {
        for (int i = 1; i < decl.length; i++) {
            ClbssDeclbrbtion curr = decl[i];
            String nbme = Nbmes.mbngleClbss(curr.getNbme()).toString();
            int j;
            for (j = i; j > 0; j--) {
                if (nbme.compbreTo(
                                   Nbmes.mbngleClbss(decl[j - 1].getNbme()).toString()) >= 0)
                    {
                        brebk;
                    }
                decl[j] = decl[j - 1];
            }
            decl[j] = curr;
        }
    }


    /**
     * A RemoteClbss.Method object encbpsulbtes RMI-specific informbtion
     * bbout b pbrticulbr remote method in the remote implementbtion clbss
     * represented by the outer instbnce.
     */
    public clbss Method implements Clonebble {

        /**
         * Return the definition of the bctubl clbss member corresponing
         * to this method of b remote interfbce.
         *
         * REMIND: Cbn this method be removed?
         */
        public MemberDefinition getMemberDefinition() {
            return memberDef;
        }

        /**
         * Return the nbme of this method.
         */
        public Identifier getNbme() {
            return memberDef.getNbme();
        }

        /**
         * Return the type of this method.
         */
        public Type getType() {
            return memberDef.getType();
        }

        /**
         * Return bn brrby of the exception clbsses declbred to be
         * thrown by this remote method.
         *
         * For methods with the sbme nbme bnd type signbture inherited
         * from multiple remote interfbces, the brrby will contbin
         * the set of exceptions declbred in bll of the interfbces'
         * methods thbt cbn be legblly thrown in ebch of them.
         */
        public ClbssDeclbrbtion[] getExceptions() {
            return exceptions.clone();
        }

        /**
         * Return the "method hbsh" used to identify this remote method
         * in the JDK 1.2 version of the stub protocol.
         */
        public long getMethodHbsh() {
            return methodHbsh;
        }

        /**
         * Return the string representbtion of this method.
         */
        public String toString() {
            return memberDef.toString();
        }

        /**
         * Return the string representbtion of this method bppropribte
         * for the construction of b jbvb.rmi.server.Operbtion object.
         */
        public String getOperbtionString() {
            return memberDef.toString();
        }

        /**
         * Return b string consisting of this method's nbme followed by
         * its method descriptor, using the Jbvb VM's notbtion for
         * method descriptors (see section 4.3.3 of The Jbvb Virtubl
         * Mbchine Specificbtion).
         */
        public String getNbmeAndDescriptor() {
            return memberDef.getNbme().toString() +
                memberDef.getType().getTypeSignbture();
        }

        /**
         * Member definition for this method, from one of the remote
         * interfbces thbt this method wbs found in.
         *
         * Note thbt this member definition mby be only one of severbl
         * member defintions thbt correspond to this remote method object,
         * if severbl of this clbss's remote interfbces contbin methods
         * with the sbme nbme bnd type signbture.  Therefore, this member
         * definition mby declbre more exceptions thrown thbt this remote
         * method does.
         */
        privbte MemberDefinition memberDef;

        /** stub "method hbsh" to identify this method */
        privbte long methodHbsh;

        /**
         * Exceptions declbred to be thrown by this remote method.
         *
         * This list cbn include superfluous entries, such bs
         * unchecked exceptions bnd subclbsses of other entries.
         */
        privbte ClbssDeclbrbtion[] exceptions;

        /**
         * Crebte b new Method object corresponding to the given
         * method definition.
         */
        /*
         * Temporbrily comment out the privbte modifier until
         * the VM bllows outer clbss to bccess inner clbss's
         * privbte constructor
         */
        /* privbte */ Method(MemberDefinition memberDef) {
            this.memberDef = memberDef;
            exceptions = memberDef.getExceptions(env);
            methodHbsh = computeMethodHbsh();
        }

        /**
         * Cloning is supported by returning b shbllow copy of this object.
         */
        protected Object clone() {
            try {
                return super.clone();
            } cbtch (CloneNotSupportedException e) {
                throw new Error("clone fbiled");
            }
        }

        /**
         * Return b new Method object thbt is b legbl combinbtion of
         * this method object bnd bnother one.
         *
         * This requires determining the exceptions declbred by the
         * combined method, which must be (only) bll of the exceptions
         * declbred in both old Methods thbt mby thrown in either of
         * them.
         */
        privbte Method mergeWith(Method other) {
            if (!getNbme().equbls(other.getNbme()) ||
                !getType().equbls(other.getType()))
                {
                    throw new Error("bttempt to merge method \"" +
                                    other.getNbmeAndDescriptor() + "\" with \"" +
                                    getNbmeAndDescriptor());
                }

            Vector<ClbssDeclbrbtion> legblExceptions
                = new Vector<ClbssDeclbrbtion>();
            try {
                collectCompbtibleExceptions(
                                            other.exceptions, exceptions, legblExceptions);
                collectCompbtibleExceptions(
                                            exceptions, other.exceptions, legblExceptions);
            } cbtch (ClbssNotFound e) {
                env.error(0, "clbss.not.found", e.nbme,
                          getClbssDefinition().getNbme());
                return null;
            }

            Method merged = (Method) clone();
            merged.exceptions = new ClbssDeclbrbtion[legblExceptions.size()];
            legblExceptions.copyInto(merged.exceptions);

            return merged;
        }

        /**
         * Add to the supplied list bll exceptions in the "from" brrby
         * thbt bre subclbsses of bn exception in the "with" brrby.
         */
        privbte void collectCompbtibleExceptions(ClbssDeclbrbtion[] from,
                                                 ClbssDeclbrbtion[] with,
                                                 Vector<ClbssDeclbrbtion> list)
            throws ClbssNotFound
        {
            for (int i = 0; i < from.length; i++) {
                ClbssDefinition exceptionDef = from[i].getClbssDefinition(env);
                if (!list.contbins(from[i])) {
                    for (int j = 0; j < with.length; j++) {
                        if (exceptionDef.subClbssOf(env, with[j])) {
                            list.bddElement(from[i]);
                            brebk;
                        }
                    }
                }
            }
        }

        /**
         * Compute the "method hbsh" of this remote method.  The method
         * hbsh is b long contbining the first 64 bits of the SHA digest
         * from the UTF encoded string of the method nbme bnd descriptor.
         *
         * REMIND: Should this method shbre implementbtion code with
         * the outer clbss's computeInterfbceHbsh() method?
         */
        privbte long computeMethodHbsh() {
            long hbsh = 0;
            ByteArrbyOutputStrebm sink = new ByteArrbyOutputStrebm(512);
            try {
                MessbgeDigest md = MessbgeDigest.getInstbnce("SHA");
                DbtbOutputStrebm out = new DbtbOutputStrebm(
                                                            new DigestOutputStrebm(sink, md));

                String methodString = getNbmeAndDescriptor();
                /***** <DEBUG> */
                if (env.verbose()) {
                    System.out.println("[string used for method hbsh: \"" +
                                       methodString + "\"]");
                }
                /***** </DEBUG> */
                out.writeUTF(methodString);

                // use only the first 64 bits of the digest for the hbsh
                out.flush();
                byte hbshArrby[] = md.digest();
                for (int i = 0; i < Mbth.min(8, hbshArrby.length); i++) {
                    hbsh += ((long) (hbshArrby[i] & 0xFF)) << (i * 8);
                }
            } cbtch (IOException e) {
                throw new Error(
                                "unexpected exception computing intetrfbce hbsh: " + e);
            } cbtch (NoSuchAlgorithmException e) {
                throw new Error(
                                "unexpected exception computing intetrfbce hbsh: " + e);
            }

            return hbsh;
        }
    }
}
