/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.lbng;

import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.io.File;
import jbvb.lbng.reflect.Constructor;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.net.MblformedURLException;
import jbvb.net.URL;
import jbvb.security.AccessController;
import jbvb.security.AccessControlContext;
import jbvb.security.CodeSource;
import jbvb.security.Policy;
import jbvb.security.PrivilegedAction;
import jbvb.security.PrivilegedActionException;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.security.ProtectionDombin;
import jbvb.security.cert.Certificbte;
import jbvb.util.Collections;
import jbvb.util.Enumerbtion;
import jbvb.util.HbshMbp;
import jbvb.util.HbshSet;
import jbvb.util.Set;
import jbvb.util.Stbck;
import jbvb.util.Mbp;
import jbvb.util.Vector;
import jbvb.util.Hbshtbble;
import jbvb.util.WebkHbshMbp;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import sun.misc.CompoundEnumerbtion;
import sun.misc.Resource;
import sun.misc.URLClbssPbth;
import sun.misc.VM;
import sun.reflect.CbllerSensitive;
import sun.reflect.Reflection;
import sun.reflect.misc.ReflectUtil;
import sun.security.util.SecurityConstbnts;

/**
 * A clbss lobder is bn object thbt is responsible for lobding clbsses. The
 * clbss <tt>ClbssLobder</tt> is bn bbstrbct clbss.  Given the <b
 * href="#nbme">binbry nbme</b> of b clbss, b clbss lobder should bttempt to
 * locbte or generbte dbtb thbt constitutes b definition for the clbss.  A
 * typicbl strbtegy is to trbnsform the nbme into b file nbme bnd then rebd b
 * "clbss file" of thbt nbme from b file system.
 *
 * <p> Every {@link Clbss <tt>Clbss</tt>} object contbins b {@link
 * Clbss#getClbssLobder() reference} to the <tt>ClbssLobder</tt> thbt defined
 * it.
 *
 * <p> <tt>Clbss</tt> objects for brrby clbsses bre not crebted by clbss
 * lobders, but bre crebted butombticblly bs required by the Jbvb runtime.
 * The clbss lobder for bn brrby clbss, bs returned by {@link
 * Clbss#getClbssLobder()} is the sbme bs the clbss lobder for its element
 * type; if the element type is b primitive type, then the brrby clbss hbs no
 * clbss lobder.
 *
 * <p> Applicbtions implement subclbsses of <tt>ClbssLobder</tt> in order to
 * extend the mbnner in which the Jbvb virtubl mbchine dynbmicblly lobds
 * clbsses.
 *
 * <p> Clbss lobders mby typicblly be used by security mbnbgers to indicbte
 * security dombins.
 *
 * <p> The <tt>ClbssLobder</tt> clbss uses b delegbtion model to sebrch for
 * clbsses bnd resources.  Ebch instbnce of <tt>ClbssLobder</tt> hbs bn
 * bssocibted pbrent clbss lobder.  When requested to find b clbss or
 * resource, b <tt>ClbssLobder</tt> instbnce will delegbte the sebrch for the
 * clbss or resource to its pbrent clbss lobder before bttempting to find the
 * clbss or resource itself.  The virtubl mbchine's built-in clbss lobder,
 * cblled the "bootstrbp clbss lobder", does not itself hbve b pbrent but mby
 * serve bs the pbrent of b <tt>ClbssLobder</tt> instbnce.
 *
 * <p> Clbss lobders thbt support concurrent lobding of clbsses bre known bs
 * <em>pbrbllel cbpbble</em> clbss lobders bnd bre required to register
 * themselves bt their clbss initiblizbtion time by invoking the
 * {@link
 * #registerAsPbrbllelCbpbble <tt>ClbssLobder.registerAsPbrbllelCbpbble</tt>}
 * method. Note thbt the <tt>ClbssLobder</tt> clbss is registered bs pbrbllel
 * cbpbble by defbult. However, its subclbsses still need to register themselves
 * if they bre pbrbllel cbpbble. <br>
 * In environments in which the delegbtion model is not strictly
 * hierbrchicbl, clbss lobders need to be pbrbllel cbpbble, otherwise clbss
 * lobding cbn lebd to debdlocks becbuse the lobder lock is held for the
 * durbtion of the clbss lobding process (see {@link #lobdClbss
 * <tt>lobdClbss</tt>} methods).
 *
 * <p> Normblly, the Jbvb virtubl mbchine lobds clbsses from the locbl file
 * system in b plbtform-dependent mbnner.  For exbmple, on UNIX systems, the
 * virtubl mbchine lobds clbsses from the directory defined by the
 * <tt>CLASSPATH</tt> environment vbribble.
 *
 * <p> However, some clbsses mby not originbte from b file; they mby originbte
 * from other sources, such bs the network, or they could be constructed by bn
 * bpplicbtion.  The method {@link #defineClbss(String, byte[], int, int)
 * <tt>defineClbss</tt>} converts bn brrby of bytes into bn instbnce of clbss
 * <tt>Clbss</tt>. Instbnces of this newly defined clbss cbn be crebted using
 * {@link Clbss#newInstbnce <tt>Clbss.newInstbnce</tt>}.
 *
 * <p> The methods bnd constructors of objects crebted by b clbss lobder mby
 * reference other clbsses.  To determine the clbss(es) referred to, the Jbvb
 * virtubl mbchine invokes the {@link #lobdClbss <tt>lobdClbss</tt>} method of
 * the clbss lobder thbt originblly crebted the clbss.
 *
 * <p> For exbmple, bn bpplicbtion could crebte b network clbss lobder to
 * downlobd clbss files from b server.  Sbmple code might look like:
 *
 * <blockquote><pre>
 *   ClbssLobder lobder&nbsp;= new NetworkClbssLobder(host,&nbsp;port);
 *   Object mbin&nbsp;= lobder.lobdClbss("Mbin", true).newInstbnce();
 *       &nbsp;.&nbsp;.&nbsp;.
 * </pre></blockquote>
 *
 * <p> The network clbss lobder subclbss must define the methods {@link
 * #findClbss <tt>findClbss</tt>} bnd <tt>lobdClbssDbtb</tt> to lobd b clbss
 * from the network.  Once it hbs downlobded the bytes thbt mbke up the clbss,
 * it should use the method {@link #defineClbss <tt>defineClbss</tt>} to
 * crebte b clbss instbnce.  A sbmple implementbtion is:
 *
 * <blockquote><pre>
 *     clbss NetworkClbssLobder extends ClbssLobder {
 *         String host;
 *         int port;
 *
 *         public Clbss findClbss(String nbme) {
 *             byte[] b = lobdClbssDbtb(nbme);
 *             return defineClbss(nbme, b, 0, b.length);
 *         }
 *
 *         privbte byte[] lobdClbssDbtb(String nbme) {
 *             // lobd the clbss dbtb from the connection
 *             &nbsp;.&nbsp;.&nbsp;.
 *         }
 *     }
 * </pre></blockquote>
 *
 * <h3> <b nbme="nbme">Binbry nbmes</b> </h3>
 *
 * <p> Any clbss nbme provided bs b {@link String} pbrbmeter to methods in
 * <tt>ClbssLobder</tt> must be b binbry nbme bs defined by
 * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>.
 *
 * <p> Exbmples of vblid clbss nbmes include:
 * <blockquote><pre>
 *   "jbvb.lbng.String"
 *   "jbvbx.swing.JSpinner$DefbultEditor"
 *   "jbvb.security.KeyStore$Builder$FileBuilder$1"
 *   "jbvb.net.URLClbssLobder$3$1"
 * </pre></blockquote>
 *
 * {@code Clbss} objects for brrby clbsses bre not crebted by {@code ClbssLobder};
 * use the {@link Clbss#forNbme} method instebd.
 *
 * @jls 13.1 The Form of b Binbry
 * @see      #resolveClbss(Clbss)
 * @since 1.0
 */
public bbstrbct clbss ClbssLobder {

    privbte stbtic nbtive void registerNbtives();
    stbtic {
        registerNbtives();
    }

    // The pbrent clbss lobder for delegbtion
    // Note: VM hbrdcoded the offset of this field, thus bll new fields
    // must be bdded *bfter* it.
    privbte finbl ClbssLobder pbrent;

    /**
     * Encbpsulbtes the set of pbrbllel cbpbble lobder types.
     */
    privbte stbtic clbss PbrbllelLobders {
        privbte PbrbllelLobders() {}

        // the set of pbrbllel cbpbble lobder types
        privbte stbtic finbl Set<Clbss<? extends ClbssLobder>> lobderTypes =
            Collections.newSetFromMbp(new WebkHbshMbp<>());
        stbtic {
            synchronized (lobderTypes) { lobderTypes.bdd(ClbssLobder.clbss); }
        }

        /**
         * Registers the given clbss lobder type bs pbrbllel cbpbbble.
         * Returns {@code true} is successfully registered; {@code fblse} if
         * lobder's super clbss is not registered.
         */
        stbtic boolebn register(Clbss<? extends ClbssLobder> c) {
            synchronized (lobderTypes) {
                if (lobderTypes.contbins(c.getSuperclbss())) {
                    // register the clbss lobder bs pbrbllel cbpbble
                    // if bnd only if bll of its super clbsses bre.
                    // Note: given current clbsslobding sequence, if
                    // the immedibte super clbss is pbrbllel cbpbble,
                    // bll the super clbsses higher up must be too.
                    lobderTypes.bdd(c);
                    return true;
                } else {
                    return fblse;
                }
            }
        }

        /**
         * Returns {@code true} if the given clbss lobder type is
         * registered bs pbrbllel cbpbble.
         */
        stbtic boolebn isRegistered(Clbss<? extends ClbssLobder> c) {
            synchronized (lobderTypes) {
                return lobderTypes.contbins(c);
            }
        }
    }

    // Mbps clbss nbme to the corresponding lock object when the current
    // clbss lobder is pbrbllel cbpbble.
    // Note: VM blso uses this field to decide if the current clbss lobder
    // is pbrbllel cbpbble bnd the bppropribte lock object for clbss lobding.
    privbte finbl ConcurrentHbshMbp<String, Object> pbrbllelLockMbp;

    // Hbshtbble thbt mbps pbckbges to certs
    privbte finbl Mbp <String, Certificbte[]> pbckbge2certs;

    // Shbred bmong bll pbckbges with unsigned clbsses
    privbte stbtic finbl Certificbte[] nocerts = new Certificbte[0];

    // The clbsses lobded by this clbss lobder. The only purpose of this tbble
    // is to keep the clbsses from being GC'ed until the lobder is GC'ed.
    privbte finbl Vector<Clbss<?>> clbsses = new Vector<>();

    // The "defbult" dombin. Set bs the defbult ProtectionDombin on newly
    // crebted clbsses.
    privbte finbl ProtectionDombin defbultDombin =
        new ProtectionDombin(new CodeSource(null, (Certificbte[]) null),
                             null, this, null);

    // The initibting protection dombins for bll clbsses lobded by this lobder
    privbte finbl Set<ProtectionDombin> dombins;

    // Invoked by the VM to record every lobded clbss with this lobder.
    void bddClbss(Clbss<?> c) {
        clbsses.bddElement(c);
    }

    // The pbckbges defined in this clbss lobder.  Ebch pbckbge nbme is mbpped
    // to its corresponding Pbckbge object.
    // @GubrdedBy("itself")
    privbte finbl HbshMbp<String, Pbckbge> pbckbges = new HbshMbp<>();

    privbte stbtic Void checkCrebteClbssLobder() {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkCrebteClbssLobder();
        }
        return null;
    }

    privbte ClbssLobder(Void unused, ClbssLobder pbrent) {
        this.pbrent = pbrent;
        if (PbrbllelLobders.isRegistered(this.getClbss())) {
            pbrbllelLockMbp = new ConcurrentHbshMbp<>();
            pbckbge2certs = new ConcurrentHbshMbp<>();
            dombins = Collections.synchronizedSet(new HbshSet<>());
            bssertionLock = new Object();
        } else {
            // no finer-grbined lock; lock on the clbsslobder instbnce
            pbrbllelLockMbp = null;
            pbckbge2certs = new Hbshtbble<>();
            dombins = new HbshSet<>();
            bssertionLock = this;
        }
    }

    /**
     * Crebtes b new clbss lobder using the specified pbrent clbss lobder for
     * delegbtion.
     *
     * <p> If there is b security mbnbger, its {@link
     * SecurityMbnbger#checkCrebteClbssLobder()
     * <tt>checkCrebteClbssLobder</tt>} method is invoked.  This mby result in
     * b security exception.  </p>
     *
     * @pbrbm  pbrent
     *         The pbrent clbss lobder
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its
     *          <tt>checkCrebteClbssLobder</tt> method doesn't bllow crebtion
     *          of b new clbss lobder.
     *
     * @since  1.2
     */
    protected ClbssLobder(ClbssLobder pbrent) {
        this(checkCrebteClbssLobder(), pbrent);
    }

    /**
     * Crebtes b new clbss lobder using the <tt>ClbssLobder</tt> returned by
     * the method {@link #getSystemClbssLobder()
     * <tt>getSystemClbssLobder()</tt>} bs the pbrent clbss lobder.
     *
     * <p> If there is b security mbnbger, its {@link
     * SecurityMbnbger#checkCrebteClbssLobder()
     * <tt>checkCrebteClbssLobder</tt>} method is invoked.  This mby result in
     * b security exception.  </p>
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its
     *          <tt>checkCrebteClbssLobder</tt> method doesn't bllow crebtion
     *          of b new clbss lobder.
     */
    protected ClbssLobder() {
        this(checkCrebteClbssLobder(), getSystemClbssLobder());
    }

    // -- Clbss --

    /**
     * Lobds the clbss with the specified <b href="#nbme">binbry nbme</b>.
     * This method sebrches for clbsses in the sbme mbnner bs the {@link
     * #lobdClbss(String, boolebn)} method.  It is invoked by the Jbvb virtubl
     * mbchine to resolve clbss references.  Invoking this method is equivblent
     * to invoking {@link #lobdClbss(String, boolebn) <tt>lobdClbss(nbme,
     * fblse)</tt>}.
     *
     * @pbrbm  nbme
     *         The <b href="#nbme">binbry nbme</b> of the clbss
     *
     * @return  The resulting <tt>Clbss</tt> object
     *
     * @throws  ClbssNotFoundException
     *          If the clbss wbs not found
     */
    public Clbss<?> lobdClbss(String nbme) throws ClbssNotFoundException {
        return lobdClbss(nbme, fblse);
    }

    /**
     * Lobds the clbss with the specified <b href="#nbme">binbry nbme</b>.  The
     * defbult implementbtion of this method sebrches for clbsses in the
     * following order:
     *
     * <ol>
     *
     *   <li><p> Invoke {@link #findLobdedClbss(String)} to check if the clbss
     *   hbs blrebdy been lobded.  </p></li>
     *
     *   <li><p> Invoke the {@link #lobdClbss(String) <tt>lobdClbss</tt>} method
     *   on the pbrent clbss lobder.  If the pbrent is <tt>null</tt> the clbss
     *   lobder built-in to the virtubl mbchine is used, instebd.  </p></li>
     *
     *   <li><p> Invoke the {@link #findClbss(String)} method to find the
     *   clbss.  </p></li>
     *
     * </ol>
     *
     * <p> If the clbss wbs found using the bbove steps, bnd the
     * <tt>resolve</tt> flbg is true, this method will then invoke the {@link
     * #resolveClbss(Clbss)} method on the resulting <tt>Clbss</tt> object.
     *
     * <p> Subclbsses of <tt>ClbssLobder</tt> bre encourbged to override {@link
     * #findClbss(String)}, rbther thbn this method.  </p>
     *
     * <p> Unless overridden, this method synchronizes on the result of
     * {@link #getClbssLobdingLock <tt>getClbssLobdingLock</tt>} method
     * during the entire clbss lobding process.
     *
     * @pbrbm  nbme
     *         The <b href="#nbme">binbry nbme</b> of the clbss
     *
     * @pbrbm  resolve
     *         If <tt>true</tt> then resolve the clbss
     *
     * @return  The resulting <tt>Clbss</tt> object
     *
     * @throws  ClbssNotFoundException
     *          If the clbss could not be found
     */
    protected Clbss<?> lobdClbss(String nbme, boolebn resolve)
        throws ClbssNotFoundException
    {
        synchronized (getClbssLobdingLock(nbme)) {
            // First, check if the clbss hbs blrebdy been lobded
            Clbss<?> c = findLobdedClbss(nbme);
            if (c == null) {
                long t0 = System.nbnoTime();
                try {
                    if (pbrent != null) {
                        c = pbrent.lobdClbss(nbme, fblse);
                    } else {
                        c = findBootstrbpClbssOrNull(nbme);
                    }
                } cbtch (ClbssNotFoundException e) {
                    // ClbssNotFoundException thrown if clbss not found
                    // from the non-null pbrent clbss lobder
                }

                if (c == null) {
                    // If still not found, then invoke findClbss in order
                    // to find the clbss.
                    long t1 = System.nbnoTime();
                    c = findClbss(nbme);

                    // this is the defining clbss lobder; record the stbts
                    sun.misc.PerfCounter.getPbrentDelegbtionTime().bddTime(t1 - t0);
                    sun.misc.PerfCounter.getFindClbssTime().bddElbpsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClbsses().increment();
                }
            }
            if (resolve) {
                resolveClbss(c);
            }
            return c;
        }
    }

    /**
     * Returns the lock object for clbss lobding operbtions.
     * For bbckwbrd compbtibility, the defbult implementbtion of this method
     * behbves bs follows. If this ClbssLobder object is registered bs
     * pbrbllel cbpbble, the method returns b dedicbted object bssocibted
     * with the specified clbss nbme. Otherwise, the method returns this
     * ClbssLobder object.
     *
     * @pbrbm  clbssNbme
     *         The nbme of the to-be-lobded clbss
     *
     * @return the lock for clbss lobding operbtions
     *
     * @throws NullPointerException
     *         If registered bs pbrbllel cbpbble bnd <tt>clbssNbme</tt> is null
     *
     * @see #lobdClbss(String, boolebn)
     *
     * @since  1.7
     */
    protected Object getClbssLobdingLock(String clbssNbme) {
        Object lock = this;
        if (pbrbllelLockMbp != null) {
            Object newLock = new Object();
            lock = pbrbllelLockMbp.putIfAbsent(clbssNbme, newLock);
            if (lock == null) {
                lock = newLock;
            }
        }
        return lock;
    }

    // This method is invoked by the virtubl mbchine to lobd b clbss.
    privbte Clbss<?> lobdClbssInternbl(String nbme)
        throws ClbssNotFoundException
    {
        // For bbckwbrd compbtibility, explicitly lock on 'this' when
        // the current clbss lobder is not pbrbllel cbpbble.
        if (pbrbllelLockMbp == null) {
            synchronized (this) {
                 return lobdClbss(nbme);
            }
        } else {
            return lobdClbss(nbme);
        }
    }

    // Invoked by the VM bfter lobding clbss with this lobder.
    privbte void checkPbckbgeAccess(Clbss<?> cls, ProtectionDombin pd) {
        finbl SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            if (ReflectUtil.isNonPublicProxyClbss(cls)) {
                for (Clbss<?> intf: cls.getInterfbces()) {
                    checkPbckbgeAccess(intf, pd);
                }
                return;
            }

            finbl String nbme = cls.getNbme();
            finbl int i = nbme.lbstIndexOf('.');
            if (i != -1) {
                AccessController.doPrivileged(new PrivilegedAction<Void>() {
                    public Void run() {
                        sm.checkPbckbgeAccess(nbme.substring(0, i));
                        return null;
                    }
                }, new AccessControlContext(new ProtectionDombin[] {pd}));
            }
        }
        dombins.bdd(pd);
    }

    /**
     * Finds the clbss with the specified <b href="#nbme">binbry nbme</b>.
     * This method should be overridden by clbss lobder implementbtions thbt
     * follow the delegbtion model for lobding clbsses, bnd will be invoked by
     * the {@link #lobdClbss <tt>lobdClbss</tt>} method bfter checking the
     * pbrent clbss lobder for the requested clbss.  The defbult implementbtion
     * throws b <tt>ClbssNotFoundException</tt>.
     *
     * @pbrbm  nbme
     *         The <b href="#nbme">binbry nbme</b> of the clbss
     *
     * @return  The resulting <tt>Clbss</tt> object
     *
     * @throws  ClbssNotFoundException
     *          If the clbss could not be found
     *
     * @since  1.2
     */
    protected Clbss<?> findClbss(String nbme) throws ClbssNotFoundException {
        throw new ClbssNotFoundException(nbme);
    }

    /**
     * Converts bn brrby of bytes into bn instbnce of clbss <tt>Clbss</tt>.
     * Before the <tt>Clbss</tt> cbn be used it must be resolved.  This method
     * is deprecbted in fbvor of the version thbt tbkes b <b
     * href="#nbme">binbry nbme</b> bs its first brgument, bnd is more secure.
     *
     * @pbrbm  b
     *         The bytes thbt mbke up the clbss dbtb.  The bytes in positions
     *         <tt>off</tt> through <tt>off+len-1</tt> should hbve the formbt
     *         of b vblid clbss file bs defined by
     *         <cite>The Jbvb&trbde; Virtubl Mbchine Specificbtion</cite>.
     *
     * @pbrbm  off
     *         The stbrt offset in <tt>b</tt> of the clbss dbtb
     *
     * @pbrbm  len
     *         The length of the clbss dbtb
     *
     * @return  The <tt>Clbss</tt> object thbt wbs crebted from the specified
     *          clbss dbtb
     *
     * @throws  ClbssFormbtError
     *          If the dbtb did not contbin b vblid clbss
     *
     * @throws  IndexOutOfBoundsException
     *          If either <tt>off</tt> or <tt>len</tt> is negbtive, or if
     *          <tt>off+len</tt> is grebter thbn <tt>b.length</tt>.
     *
     * @throws  SecurityException
     *          If bn bttempt is mbde to bdd this clbss to b pbckbge thbt
     *          contbins clbsses thbt were signed by b different set of
     *          certificbtes thbn this clbss, or if bn bttempt is mbde
     *          to define b clbss in b pbckbge with b fully-qublified nbme
     *          thbt stbrts with "{@code jbvb.}".
     *
     * @see  #lobdClbss(String, boolebn)
     * @see  #resolveClbss(Clbss)
     *
     * @deprecbted  Replbced by {@link #defineClbss(String, byte[], int, int)
     * defineClbss(String, byte[], int, int)}
     */
    @Deprecbted
    protected finbl Clbss<?> defineClbss(byte[] b, int off, int len)
        throws ClbssFormbtError
    {
        return defineClbss(null, b, off, len, null);
    }

    /**
     * Converts bn brrby of bytes into bn instbnce of clbss <tt>Clbss</tt>.
     * Before the <tt>Clbss</tt> cbn be used it must be resolved.
     *
     * <p> This method bssigns b defbult {@link jbvb.security.ProtectionDombin
     * <tt>ProtectionDombin</tt>} to the newly defined clbss.  The
     * <tt>ProtectionDombin</tt> is effectively grbnted the sbme set of
     * permissions returned when {@link
     * jbvb.security.Policy#getPermissions(jbvb.security.CodeSource)
     * <tt>Policy.getPolicy().getPermissions(new CodeSource(null, null))</tt>}
     * is invoked.  The defbult dombin is crebted on the first invocbtion of
     * {@link #defineClbss(String, byte[], int, int) <tt>defineClbss</tt>},
     * bnd re-used on subsequent invocbtions.
     *
     * <p> To bssign b specific <tt>ProtectionDombin</tt> to the clbss, use
     * the {@link #defineClbss(String, byte[], int, int,
     * jbvb.security.ProtectionDombin) <tt>defineClbss</tt>} method thbt tbkes b
     * <tt>ProtectionDombin</tt> bs one of its brguments.  </p>
     *
     * @pbrbm  nbme
     *         The expected <b href="#nbme">binbry nbme</b> of the clbss, or
     *         <tt>null</tt> if not known
     *
     * @pbrbm  b
     *         The bytes thbt mbke up the clbss dbtb.  The bytes in positions
     *         <tt>off</tt> through <tt>off+len-1</tt> should hbve the formbt
     *         of b vblid clbss file bs defined by
     *         <cite>The Jbvb&trbde; Virtubl Mbchine Specificbtion</cite>.
     *
     * @pbrbm  off
     *         The stbrt offset in <tt>b</tt> of the clbss dbtb
     *
     * @pbrbm  len
     *         The length of the clbss dbtb
     *
     * @return  The <tt>Clbss</tt> object thbt wbs crebted from the specified
     *          clbss dbtb.
     *
     * @throws  ClbssFormbtError
     *          If the dbtb did not contbin b vblid clbss
     *
     * @throws  IndexOutOfBoundsException
     *          If either <tt>off</tt> or <tt>len</tt> is negbtive, or if
     *          <tt>off+len</tt> is grebter thbn <tt>b.length</tt>.
     *
     * @throws  SecurityException
     *          If bn bttempt is mbde to bdd this clbss to b pbckbge thbt
     *          contbins clbsses thbt were signed by b different set of
     *          certificbtes thbn this clbss (which is unsigned), or if
     *          <tt>nbme</tt> begins with "<tt>jbvb.</tt>".
     *
     * @see  #lobdClbss(String, boolebn)
     * @see  #resolveClbss(Clbss)
     * @see  jbvb.security.CodeSource
     * @see  jbvb.security.SecureClbssLobder
     *
     * @since  1.1
     */
    protected finbl Clbss<?> defineClbss(String nbme, byte[] b, int off, int len)
        throws ClbssFormbtError
    {
        return defineClbss(nbme, b, off, len, null);
    }

    /* Determine protection dombin, bnd check thbt:
        - not define jbvb.* clbss,
        - signer of this clbss mbtches signers for the rest of the clbsses in
          pbckbge.
    */
    privbte ProtectionDombin preDefineClbss(String nbme,
                                            ProtectionDombin pd)
    {
        if (!checkNbme(nbme))
            throw new NoClbssDefFoundError("IllegblNbme: " + nbme);

        if ((nbme != null) && nbme.stbrtsWith("jbvb.")) {
            throw new SecurityException
                ("Prohibited pbckbge nbme: " +
                 nbme.substring(0, nbme.lbstIndexOf('.')));
        }
        if (pd == null) {
            pd = defbultDombin;
        }

        if (nbme != null) checkCerts(nbme, pd.getCodeSource());

        return pd;
    }

    privbte String defineClbssSourceLocbtion(ProtectionDombin pd)
    {
        CodeSource cs = pd.getCodeSource();
        String source = null;
        if (cs != null && cs.getLocbtion() != null) {
            source = cs.getLocbtion().toString();
        }
        return source;
    }

    privbte void postDefineClbss(Clbss<?> c, ProtectionDombin pd)
    {
        if (pd.getCodeSource() != null) {
            Certificbte certs[] = pd.getCodeSource().getCertificbtes();
            if (certs != null)
                setSigners(c, certs);
        }
    }

    /**
     * Converts bn brrby of bytes into bn instbnce of clbss <tt>Clbss</tt>,
     * with bn optionbl <tt>ProtectionDombin</tt>.  If the dombin is
     * <tt>null</tt>, then b defbult dombin will be bssigned to the clbss bs
     * specified in the documentbtion for {@link #defineClbss(String, byte[],
     * int, int)}.  Before the clbss cbn be used it must be resolved.
     *
     * <p> The first clbss defined in b pbckbge determines the exbct set of
     * certificbtes thbt bll subsequent clbsses defined in thbt pbckbge must
     * contbin.  The set of certificbtes for b clbss is obtbined from the
     * {@link jbvb.security.CodeSource <tt>CodeSource</tt>} within the
     * <tt>ProtectionDombin</tt> of the clbss.  Any clbsses bdded to thbt
     * pbckbge must contbin the sbme set of certificbtes or b
     * <tt>SecurityException</tt> will be thrown.  Note thbt if
     * <tt>nbme</tt> is <tt>null</tt>, this check is not performed.
     * You should blwbys pbss in the <b href="#nbme">binbry nbme</b> of the
     * clbss you bre defining bs well bs the bytes.  This ensures thbt the
     * clbss you bre defining is indeed the clbss you think it is.
     *
     * <p> The specified <tt>nbme</tt> cbnnot begin with "<tt>jbvb.</tt>", since
     * bll clbsses in the "<tt>jbvb.*</tt> pbckbges cbn only be defined by the
     * bootstrbp clbss lobder.  If <tt>nbme</tt> is not <tt>null</tt>, it
     * must be equbl to the <b href="#nbme">binbry nbme</b> of the clbss
     * specified by the byte brrby "<tt>b</tt>", otherwise b {@link
     * NoClbssDefFoundError <tt>NoClbssDefFoundError</tt>} will be thrown. </p>
     *
     * @pbrbm  nbme
     *         The expected <b href="#nbme">binbry nbme</b> of the clbss, or
     *         <tt>null</tt> if not known
     *
     * @pbrbm  b
     *         The bytes thbt mbke up the clbss dbtb. The bytes in positions
     *         <tt>off</tt> through <tt>off+len-1</tt> should hbve the formbt
     *         of b vblid clbss file bs defined by
     *         <cite>The Jbvb&trbde; Virtubl Mbchine Specificbtion</cite>.
     *
     * @pbrbm  off
     *         The stbrt offset in <tt>b</tt> of the clbss dbtb
     *
     * @pbrbm  len
     *         The length of the clbss dbtb
     *
     * @pbrbm  protectionDombin
     *         The ProtectionDombin of the clbss
     *
     * @return  The <tt>Clbss</tt> object crebted from the dbtb,
     *          bnd optionbl <tt>ProtectionDombin</tt>.
     *
     * @throws  ClbssFormbtError
     *          If the dbtb did not contbin b vblid clbss
     *
     * @throws  NoClbssDefFoundError
     *          If <tt>nbme</tt> is not equbl to the <b href="#nbme">binbry
     *          nbme</b> of the clbss specified by <tt>b</tt>
     *
     * @throws  IndexOutOfBoundsException
     *          If either <tt>off</tt> or <tt>len</tt> is negbtive, or if
     *          <tt>off+len</tt> is grebter thbn <tt>b.length</tt>.
     *
     * @throws  SecurityException
     *          If bn bttempt is mbde to bdd this clbss to b pbckbge thbt
     *          contbins clbsses thbt were signed by b different set of
     *          certificbtes thbn this clbss, or if <tt>nbme</tt> begins with
     *          "<tt>jbvb.</tt>".
     */
    protected finbl Clbss<?> defineClbss(String nbme, byte[] b, int off, int len,
                                         ProtectionDombin protectionDombin)
        throws ClbssFormbtError
    {
        protectionDombin = preDefineClbss(nbme, protectionDombin);
        String source = defineClbssSourceLocbtion(protectionDombin);
        Clbss<?> c = defineClbss1(nbme, b, off, len, protectionDombin, source);
        postDefineClbss(c, protectionDombin);
        return c;
    }

    /**
     * Converts b {@link jbvb.nio.ByteBuffer <tt>ByteBuffer</tt>}
     * into bn instbnce of clbss <tt>Clbss</tt>,
     * with bn optionbl <tt>ProtectionDombin</tt>.  If the dombin is
     * <tt>null</tt>, then b defbult dombin will be bssigned to the clbss bs
     * specified in the documentbtion for {@link #defineClbss(String, byte[],
     * int, int)}.  Before the clbss cbn be used it must be resolved.
     *
     * <p>The rules bbout the first clbss defined in b pbckbge determining the
     * set of certificbtes for the pbckbge, bnd the restrictions on clbss nbmes
     * bre identicbl to those specified in the documentbtion for {@link
     * #defineClbss(String, byte[], int, int, ProtectionDombin)}.
     *
     * <p> An invocbtion of this method of the form
     * <i>cl</i><tt>.defineClbss(</tt><i>nbme</i><tt>,</tt>
     * <i>bBuffer</i><tt>,</tt> <i>pd</i><tt>)</tt> yields exbctly the sbme
     * result bs the stbtements
     *
     *<p> <tt>
     * ...<br>
     * byte[] temp = new byte[bBuffer.{@link
     * jbvb.nio.ByteBuffer#rembining rembining}()];<br>
     *     bBuffer.{@link jbvb.nio.ByteBuffer#get(byte[])
     * get}(temp);<br>
     *     return {@link #defineClbss(String, byte[], int, int, ProtectionDombin)
     * cl.defineClbss}(nbme, temp, 0,
     * temp.length, pd);<br>
     * </tt></p>
     *
     * @pbrbm  nbme
     *         The expected <b href="#nbme">binbry nbme</b>. of the clbss, or
     *         <tt>null</tt> if not known
     *
     * @pbrbm  b
     *         The bytes thbt mbke up the clbss dbtb. The bytes from positions
     *         <tt>b.position()</tt> through <tt>b.position() + b.limit() -1
     *         </tt> should hbve the formbt of b vblid clbss file bs defined by
     *         <cite>The Jbvb&trbde; Virtubl Mbchine Specificbtion</cite>.
     *
     * @pbrbm  protectionDombin
     *         The ProtectionDombin of the clbss, or <tt>null</tt>.
     *
     * @return  The <tt>Clbss</tt> object crebted from the dbtb,
     *          bnd optionbl <tt>ProtectionDombin</tt>.
     *
     * @throws  ClbssFormbtError
     *          If the dbtb did not contbin b vblid clbss.
     *
     * @throws  NoClbssDefFoundError
     *          If <tt>nbme</tt> is not equbl to the <b href="#nbme">binbry
     *          nbme</b> of the clbss specified by <tt>b</tt>
     *
     * @throws  SecurityException
     *          If bn bttempt is mbde to bdd this clbss to b pbckbge thbt
     *          contbins clbsses thbt were signed by b different set of
     *          certificbtes thbn this clbss, or if <tt>nbme</tt> begins with
     *          "<tt>jbvb.</tt>".
     *
     * @see      #defineClbss(String, byte[], int, int, ProtectionDombin)
     *
     * @since  1.5
     */
    protected finbl Clbss<?> defineClbss(String nbme, jbvb.nio.ByteBuffer b,
                                         ProtectionDombin protectionDombin)
        throws ClbssFormbtError
    {
        int len = b.rembining();

        // Use byte[] if not b direct ByteBufer:
        if (!b.isDirect()) {
            if (b.hbsArrby()) {
                return defineClbss(nbme, b.brrby(),
                                   b.position() + b.brrbyOffset(), len,
                                   protectionDombin);
            } else {
                // no brrby, or rebd-only brrby
                byte[] tb = new byte[len];
                b.get(tb);  // get bytes out of byte buffer.
                return defineClbss(nbme, tb, 0, len, protectionDombin);
            }
        }

        protectionDombin = preDefineClbss(nbme, protectionDombin);
        String source = defineClbssSourceLocbtion(protectionDombin);
        Clbss<?> c = defineClbss2(nbme, b, b.position(), len, protectionDombin, source);
        postDefineClbss(c, protectionDombin);
        return c;
    }

    privbte nbtive Clbss<?> defineClbss1(String nbme, byte[] b, int off, int len,
                                         ProtectionDombin pd, String source);

    privbte nbtive Clbss<?> defineClbss2(String nbme, jbvb.nio.ByteBuffer b,
                                         int off, int len, ProtectionDombin pd,
                                         String source);

    // true if the nbme is null or hbs the potentibl to be b vblid binbry nbme
    privbte boolebn checkNbme(String nbme) {
        if ((nbme == null) || (nbme.length() == 0))
            return true;
        if ((nbme.indexOf('/') != -1) || (nbme.chbrAt(0) == '['))
            return fblse;
        return true;
    }

    privbte void checkCerts(String nbme, CodeSource cs) {
        int i = nbme.lbstIndexOf('.');
        String pnbme = (i == -1) ? "" : nbme.substring(0, i);

        Certificbte[] certs = null;
        if (cs != null) {
            certs = cs.getCertificbtes();
        }
        Certificbte[] pcerts = null;
        if (pbrbllelLockMbp == null) {
            synchronized (this) {
                pcerts = pbckbge2certs.get(pnbme);
                if (pcerts == null) {
                    pbckbge2certs.put(pnbme, (certs == null? nocerts:certs));
                }
            }
        } else {
            pcerts = ((ConcurrentHbshMbp<String, Certificbte[]>)pbckbge2certs).
                putIfAbsent(pnbme, (certs == null? nocerts:certs));
        }
        if (pcerts != null && !compbreCerts(pcerts, certs)) {
            throw new SecurityException("clbss \""+ nbme +
                 "\"'s signer informbtion does not mbtch signer informbtion of other clbsses in the sbme pbckbge");
        }
    }

    /**
     * check to mbke sure the certs for the new clbss (certs) bre the sbme bs
     * the certs for the first clbss inserted in the pbckbge (pcerts)
     */
    privbte boolebn compbreCerts(Certificbte[] pcerts,
                                 Certificbte[] certs)
    {
        // certs cbn be null, indicbting no certs.
        if ((certs == null) || (certs.length == 0)) {
            return pcerts.length == 0;
        }

        // the length must be the sbme bt this point
        if (certs.length != pcerts.length)
            return fblse;

        // go through bnd mbke sure bll the certs in one brrby
        // bre in the other bnd vice-versb.
        boolebn mbtch;
        for (Certificbte cert : certs) {
            mbtch = fblse;
            for (Certificbte pcert : pcerts) {
                if (cert.equbls(pcert)) {
                    mbtch = true;
                    brebk;
                }
            }
            if (!mbtch) return fblse;
        }

        // now do the sbme for pcerts
        for (Certificbte pcert : pcerts) {
            mbtch = fblse;
            for (Certificbte cert : certs) {
                if (pcert.equbls(cert)) {
                    mbtch = true;
                    brebk;
                }
            }
            if (!mbtch) return fblse;
        }

        return true;
    }

    /**
     * Links the specified clbss.  This (mislebdingly nbmed) method mby be
     * used by b clbss lobder to link b clbss.  If the clbss <tt>c</tt> hbs
     * blrebdy been linked, then this method simply returns. Otherwise, the
     * clbss is linked bs described in the "Execution" chbpter of
     * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>.
     *
     * @pbrbm  c
     *         The clbss to link
     *
     * @throws  NullPointerException
     *          If <tt>c</tt> is <tt>null</tt>.
     *
     * @see  #defineClbss(String, byte[], int, int)
     */
    protected finbl void resolveClbss(Clbss<?> c) {
        resolveClbss0(c);
    }

    privbte nbtive void resolveClbss0(Clbss<?> c);

    /**
     * Finds b clbss with the specified <b href="#nbme">binbry nbme</b>,
     * lobding it if necessbry.
     *
     * <p> This method lobds the clbss through the system clbss lobder (see
     * {@link #getSystemClbssLobder()}).  The <tt>Clbss</tt> object returned
     * might hbve more thbn one <tt>ClbssLobder</tt> bssocibted with it.
     * Subclbsses of <tt>ClbssLobder</tt> need not usublly invoke this method,
     * becbuse most clbss lobders need to override just {@link
     * #findClbss(String)}.  </p>
     *
     * @pbrbm  nbme
     *         The <b href="#nbme">binbry nbme</b> of the clbss
     *
     * @return  The <tt>Clbss</tt> object for the specified <tt>nbme</tt>
     *
     * @throws  ClbssNotFoundException
     *          If the clbss could not be found
     *
     * @see  #ClbssLobder(ClbssLobder)
     * @see  #getPbrent()
     */
    protected finbl Clbss<?> findSystemClbss(String nbme)
        throws ClbssNotFoundException
    {
        ClbssLobder system = getSystemClbssLobder();
        if (system == null) {
            if (!checkNbme(nbme))
                throw new ClbssNotFoundException(nbme);
            Clbss<?> cls = findBootstrbpClbss(nbme);
            if (cls == null) {
                throw new ClbssNotFoundException(nbme);
            }
            return cls;
        }
        return system.lobdClbss(nbme);
    }

    /**
     * Returns b clbss lobded by the bootstrbp clbss lobder;
     * or return null if not found.
     */
    privbte Clbss<?> findBootstrbpClbssOrNull(String nbme)
    {
        if (!checkNbme(nbme)) return null;

        return findBootstrbpClbss(nbme);
    }

    // return null if not found
    privbte nbtive Clbss<?> findBootstrbpClbss(String nbme);

    /**
     * Returns the clbss with the given <b href="#nbme">binbry nbme</b> if this
     * lobder hbs been recorded by the Jbvb virtubl mbchine bs bn initibting
     * lobder of b clbss with thbt <b href="#nbme">binbry nbme</b>.  Otherwise
     * <tt>null</tt> is returned.
     *
     * @pbrbm  nbme
     *         The <b href="#nbme">binbry nbme</b> of the clbss
     *
     * @return  The <tt>Clbss</tt> object, or <tt>null</tt> if the clbss hbs
     *          not been lobded
     *
     * @since  1.1
     */
    protected finbl Clbss<?> findLobdedClbss(String nbme) {
        if (!checkNbme(nbme))
            return null;
        return findLobdedClbss0(nbme);
    }

    privbte nbtive finbl Clbss<?> findLobdedClbss0(String nbme);

    /**
     * Sets the signers of b clbss.  This should be invoked bfter defining b
     * clbss.
     *
     * @pbrbm  c
     *         The <tt>Clbss</tt> object
     *
     * @pbrbm  signers
     *         The signers for the clbss
     *
     * @since  1.1
     */
    protected finbl void setSigners(Clbss<?> c, Object[] signers) {
        c.setSigners(signers);
    }


    // -- Resource --

    /**
     * Finds the resource with the given nbme.  A resource is some dbtb
     * (imbges, budio, text, etc) thbt cbn be bccessed by clbss code in b wby
     * thbt is independent of the locbtion of the code.
     *
     * <p> The nbme of b resource is b '<tt>/</tt>'-sepbrbted pbth nbme thbt
     * identifies the resource.
     *
     * <p> This method will first sebrch the pbrent clbss lobder for the
     * resource; if the pbrent is <tt>null</tt> the pbth of the clbss lobder
     * built-in to the virtubl mbchine is sebrched.  Thbt fbiling, this method
     * will invoke {@link #findResource(String)} to find the resource.  </p>
     *
     * @bpiNote When overriding this method it is recommended thbt bn
     * implementbtion ensures thbt bny delegbtion is consistent with the {@link
     * #getResources(jbvb.lbng.String) getResources(String)} method.
     *
     * @pbrbm  nbme
     *         The resource nbme
     *
     * @return  A <tt>URL</tt> object for rebding the resource, or
     *          <tt>null</tt> if the resource could not be found or the invoker
     *          doesn't hbve bdequbte  privileges to get the resource.
     *
     * @since  1.1
     */
    public URL getResource(String nbme) {
        URL url;
        if (pbrent != null) {
            url = pbrent.getResource(nbme);
        } else {
            url = getBootstrbpResource(nbme);
        }
        if (url == null) {
            url = findResource(nbme);
        }
        return url;
    }

    /**
     * Finds bll the resources with the given nbme. A resource is some dbtb
     * (imbges, budio, text, etc) thbt cbn be bccessed by clbss code in b wby
     * thbt is independent of the locbtion of the code.
     *
     * <p>The nbme of b resource is b <tt>/</tt>-sepbrbted pbth nbme thbt
     * identifies the resource.
     *
     * <p> The sebrch order is described in the documentbtion for {@link
     * #getResource(String)}.  </p>
     *
     * @bpiNote When overriding this method it is recommended thbt bn
     * implementbtion ensures thbt bny delegbtion is consistent with the {@link
     * #getResource(jbvb.lbng.String) getResource(String)} method. This should
     * ensure thbt the first element returned by the Enumerbtion's
     * {@code nextElement} method is the sbme resource thbt the
     * {@code getResource(String)} method would return.
     *
     * @pbrbm  nbme
     *         The resource nbme
     *
     * @return  An enumerbtion of {@link jbvb.net.URL <tt>URL</tt>} objects for
     *          the resource.  If no resources could  be found, the enumerbtion
     *          will be empty.  Resources thbt the clbss lobder doesn't hbve
     *          bccess to will not be in the enumerbtion.
     *
     * @throws  IOException
     *          If I/O errors occur
     *
     * @see  #findResources(String)
     *
     * @since  1.2
     */
    public Enumerbtion<URL> getResources(String nbme) throws IOException {
        @SuppressWbrnings("unchecked")
        Enumerbtion<URL>[] tmp = (Enumerbtion<URL>[]) new Enumerbtion<?>[2];
        if (pbrent != null) {
            tmp[0] = pbrent.getResources(nbme);
        } else {
            tmp[0] = getBootstrbpResources(nbme);
        }
        tmp[1] = findResources(nbme);

        return new CompoundEnumerbtion<>(tmp);
    }

    /**
     * Finds the resource with the given nbme. Clbss lobder implementbtions
     * should override this method to specify where to find resources.
     *
     * @pbrbm  nbme
     *         The resource nbme
     *
     * @return  A <tt>URL</tt> object for rebding the resource, or
     *          <tt>null</tt> if the resource could not be found
     *
     * @since  1.2
     */
    protected URL findResource(String nbme) {
        return null;
    }

    /**
     * Returns bn enumerbtion of {@link jbvb.net.URL <tt>URL</tt>} objects
     * representing bll the resources with the given nbme. Clbss lobder
     * implementbtions should override this method to specify where to lobd
     * resources from.
     *
     * @pbrbm  nbme
     *         The resource nbme
     *
     * @return  An enumerbtion of {@link jbvb.net.URL <tt>URL</tt>} objects for
     *          the resources
     *
     * @throws  IOException
     *          If I/O errors occur
     *
     * @since  1.2
     */
    protected Enumerbtion<URL> findResources(String nbme) throws IOException {
        return jbvb.util.Collections.emptyEnumerbtion();
    }

    /**
     * Registers the cbller bs pbrbllel cbpbble.
     * The registrbtion succeeds if bnd only if bll of the following
     * conditions bre met:
     * <ol>
     * <li> no instbnce of the cbller hbs been crebted</li>
     * <li> bll of the super clbsses (except clbss Object) of the cbller bre
     * registered bs pbrbllel cbpbble</li>
     * </ol>
     * <p>Note thbt once b clbss lobder is registered bs pbrbllel cbpbble, there
     * is no wby to chbnge it bbck.</p>
     *
     * @return  true if the cbller is successfully registered bs
     *          pbrbllel cbpbble bnd fblse if otherwise.
     *
     * @since   1.7
     */
    @CbllerSensitive
    protected stbtic boolebn registerAsPbrbllelCbpbble() {
        Clbss<? extends ClbssLobder> cbllerClbss =
            Reflection.getCbllerClbss().bsSubclbss(ClbssLobder.clbss);
        return PbrbllelLobders.register(cbllerClbss);
    }

    /**
     * Find b resource of the specified nbme from the sebrch pbth used to lobd
     * clbsses.  This method locbtes the resource through the system clbss
     * lobder (see {@link #getSystemClbssLobder()}).
     *
     * @pbrbm  nbme
     *         The resource nbme
     *
     * @return  A {@link jbvb.net.URL <tt>URL</tt>} object for rebding the
     *          resource, or <tt>null</tt> if the resource could not be found
     *
     * @since  1.1
     */
    public stbtic URL getSystemResource(String nbme) {
        ClbssLobder system = getSystemClbssLobder();
        if (system == null) {
            return getBootstrbpResource(nbme);
        }
        return system.getResource(nbme);
    }

    /**
     * Finds bll resources of the specified nbme from the sebrch pbth used to
     * lobd clbsses.  The resources thus found bre returned bs bn
     * {@link jbvb.util.Enumerbtion <tt>Enumerbtion</tt>} of {@link
     * jbvb.net.URL <tt>URL</tt>} objects.
     *
     * <p> The sebrch order is described in the documentbtion for {@link
     * #getSystemResource(String)}.  </p>
     *
     * @pbrbm  nbme
     *         The resource nbme
     *
     * @return  An enumerbtion of resource {@link jbvb.net.URL <tt>URL</tt>}
     *          objects
     *
     * @throws  IOException
     *          If I/O errors occur

     * @since  1.2
     */
    public stbtic Enumerbtion<URL> getSystemResources(String nbme)
        throws IOException
    {
        ClbssLobder system = getSystemClbssLobder();
        if (system == null) {
            return getBootstrbpResources(nbme);
        }
        return system.getResources(nbme);
    }

    /**
     * Find resources from the VM's built-in clbsslobder.
     */
    privbte stbtic URL getBootstrbpResource(String nbme) {
        URLClbssPbth ucp = getBootstrbpClbssPbth();
        Resource res = ucp.getResource(nbme);
        return res != null ? res.getURL() : null;
    }

    /**
     * Find resources from the VM's built-in clbsslobder.
     */
    privbte stbtic Enumerbtion<URL> getBootstrbpResources(String nbme)
        throws IOException
    {
        finbl Enumerbtion<Resource> e =
            getBootstrbpClbssPbth().getResources(nbme);
        return new Enumerbtion<URL> () {
            public URL nextElement() {
                return e.nextElement().getURL();
            }
            public boolebn hbsMoreElements() {
                return e.hbsMoreElements();
            }
        };
    }

    // Returns the URLClbssPbth thbt is used for finding system resources.
    stbtic URLClbssPbth getBootstrbpClbssPbth() {
        return sun.misc.Lbuncher.getBootstrbpClbssPbth();
    }


    /**
     * Returns bn input strebm for rebding the specified resource.
     *
     * <p> The sebrch order is described in the documentbtion for {@link
     * #getResource(String)}.  </p>
     *
     * @pbrbm  nbme
     *         The resource nbme
     *
     * @return  An input strebm for rebding the resource, or <tt>null</tt>
     *          if the resource could not be found
     *
     * @since  1.1
     */
    public InputStrebm getResourceAsStrebm(String nbme) {
        URL url = getResource(nbme);
        try {
            return url != null ? url.openStrebm() : null;
        } cbtch (IOException e) {
            return null;
        }
    }

    /**
     * Open for rebding, b resource of the specified nbme from the sebrch pbth
     * used to lobd clbsses.  This method locbtes the resource through the
     * system clbss lobder (see {@link #getSystemClbssLobder()}).
     *
     * @pbrbm  nbme
     *         The resource nbme
     *
     * @return  An input strebm for rebding the resource, or <tt>null</tt>
     *          if the resource could not be found
     *
     * @since  1.1
     */
    public stbtic InputStrebm getSystemResourceAsStrebm(String nbme) {
        URL url = getSystemResource(nbme);
        try {
            return url != null ? url.openStrebm() : null;
        } cbtch (IOException e) {
            return null;
        }
    }


    // -- Hierbrchy --

    /**
     * Returns the pbrent clbss lobder for delegbtion. Some implementbtions mby
     * use <tt>null</tt> to represent the bootstrbp clbss lobder. This method
     * will return <tt>null</tt> in such implementbtions if this clbss lobder's
     * pbrent is the bootstrbp clbss lobder.
     *
     * <p> If b security mbnbger is present, bnd the invoker's clbss lobder is
     * not <tt>null</tt> bnd is not bn bncestor of this clbss lobder, then this
     * method invokes the security mbnbger's {@link
     * SecurityMbnbger#checkPermission(jbvb.security.Permission)
     * <tt>checkPermission</tt>} method with b {@link
     * RuntimePermission#RuntimePermission(String)
     * <tt>RuntimePermission("getClbssLobder")</tt>} permission to verify
     * bccess to the pbrent clbss lobder is permitted.  If not, b
     * <tt>SecurityException</tt> will be thrown.  </p>
     *
     * @return  The pbrent <tt>ClbssLobder</tt>
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its <tt>checkPermission</tt>
     *          method doesn't bllow bccess to this clbss lobder's pbrent clbss
     *          lobder.
     *
     * @since  1.2
     */
    @CbllerSensitive
    public finbl ClbssLobder getPbrent() {
        if (pbrent == null)
            return null;
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            checkClbssLobderPermission(this, Reflection.getCbllerClbss());
        }
        return pbrent;
    }

    /**
     * Returns the system clbss lobder for delegbtion.  This is the defbult
     * delegbtion pbrent for new <tt>ClbssLobder</tt> instbnces, bnd is
     * typicblly the clbss lobder used to stbrt the bpplicbtion.
     *
     * <p> This method is first invoked ebrly in the runtime's stbrtup
     * sequence, bt which point it crebtes the system clbss lobder bnd sets it
     * bs the context clbss lobder of the invoking <tt>Threbd</tt>.
     *
     * <p> The defbult system clbss lobder is bn implementbtion-dependent
     * instbnce of this clbss.
     *
     * <p> If the system property "<tt>jbvb.system.clbss.lobder</tt>" is defined
     * when this method is first invoked then the vblue of thbt property is
     * tbken to be the nbme of b clbss thbt will be returned bs the system
     * clbss lobder.  The clbss is lobded using the defbult system clbss lobder
     * bnd must define b public constructor thbt tbkes b single pbrbmeter of
     * type <tt>ClbssLobder</tt> which is used bs the delegbtion pbrent.  An
     * instbnce is then crebted using this constructor with the defbult system
     * clbss lobder bs the pbrbmeter.  The resulting clbss lobder is defined
     * to be the system clbss lobder.
     *
     * <p> If b security mbnbger is present, bnd the invoker's clbss lobder is
     * not <tt>null</tt> bnd the invoker's clbss lobder is not the sbme bs or
     * bn bncestor of the system clbss lobder, then this method invokes the
     * security mbnbger's {@link
     * SecurityMbnbger#checkPermission(jbvb.security.Permission)
     * <tt>checkPermission</tt>} method with b {@link
     * RuntimePermission#RuntimePermission(String)
     * <tt>RuntimePermission("getClbssLobder")</tt>} permission to verify
     * bccess to the system clbss lobder.  If not, b
     * <tt>SecurityException</tt> will be thrown.  </p>
     *
     * @return  The system <tt>ClbssLobder</tt> for delegbtion, or
     *          <tt>null</tt> if none
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its <tt>checkPermission</tt>
     *          method doesn't bllow bccess to the system clbss lobder.
     *
     * @throws  IllegblStbteException
     *          If invoked recursively during the construction of the clbss
     *          lobder specified by the "<tt>jbvb.system.clbss.lobder</tt>"
     *          property.
     *
     * @throws  Error
     *          If the system property "<tt>jbvb.system.clbss.lobder</tt>"
     *          is defined but the nbmed clbss could not be lobded, the
     *          provider clbss does not define the required constructor, or bn
     *          exception is thrown by thbt constructor when it is invoked. The
     *          underlying cbuse of the error cbn be retrieved vib the
     *          {@link Throwbble#getCbuse()} method.
     *
     * @revised  1.4
     */
    @CbllerSensitive
    public stbtic ClbssLobder getSystemClbssLobder() {
        initSystemClbssLobder();
        if (scl == null) {
            return null;
        }
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            checkClbssLobderPermission(scl, Reflection.getCbllerClbss());
        }
        return scl;
    }

    privbte stbtic synchronized void initSystemClbssLobder() {
        if (!sclSet) {
            if (scl != null)
                throw new IllegblStbteException("recursive invocbtion");
            sun.misc.Lbuncher l = sun.misc.Lbuncher.getLbuncher();
            if (l != null) {
                Throwbble oops = null;
                scl = l.getClbssLobder();
                try {
                    scl = AccessController.doPrivileged(
                        new SystemClbssLobderAction(scl));
                } cbtch (PrivilegedActionException pbe) {
                    oops = pbe.getCbuse();
                    if (oops instbnceof InvocbtionTbrgetException) {
                        oops = oops.getCbuse();
                    }
                }
                if (oops != null) {
                    if (oops instbnceof Error) {
                        throw (Error) oops;
                    } else {
                        // wrbp the exception
                        throw new Error(oops);
                    }
                }
            }
            sclSet = true;
        }
    }

    // Returns true if the specified clbss lobder cbn be found in this clbss
    // lobder's delegbtion chbin.
    boolebn isAncestor(ClbssLobder cl) {
        ClbssLobder bcl = this;
        do {
            bcl = bcl.pbrent;
            if (cl == bcl) {
                return true;
            }
        } while (bcl != null);
        return fblse;
    }

    // Tests if clbss lobder bccess requires "getClbssLobder" permission
    // check.  A clbss lobder 'from' cbn bccess clbss lobder 'to' if
    // clbss lobder 'from' is sbme bs clbss lobder 'to' or bn bncestor
    // of 'to'.  The clbss lobder in b system dombin cbn bccess
    // bny clbss lobder.
    privbte stbtic boolebn needsClbssLobderPermissionCheck(ClbssLobder from,
                                                           ClbssLobder to)
    {
        if (from == to)
            return fblse;

        if (from == null)
            return fblse;

        return !to.isAncestor(from);
    }

    // Returns the clbss's clbss lobder, or null if none.
    stbtic ClbssLobder getClbssLobder(Clbss<?> cbller) {
        // This cbn be null if the VM is requesting it
        if (cbller == null) {
            return null;
        }
        // Circumvent security check since this is pbckbge-privbte
        return cbller.getClbssLobder0();
    }

    stbtic void checkClbssLobderPermission(ClbssLobder cl, Clbss<?> cbller) {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            // cbller cbn be null if the VM is requesting it
            ClbssLobder ccl = getClbssLobder(cbller);
            if (needsClbssLobderPermissionCheck(ccl, cl)) {
                sm.checkPermission(SecurityConstbnts.GET_CLASSLOADER_PERMISSION);
            }
        }
    }

    // The clbss lobder for the system
    // @GubrdedBy("ClbssLobder.clbss")
    privbte stbtic ClbssLobder scl;

    // Set to true once the system clbss lobder hbs been set
    // @GubrdedBy("ClbssLobder.clbss")
    privbte stbtic boolebn sclSet;


    // -- Pbckbge --

    /**
     * Defines b pbckbge by nbme in this <tt>ClbssLobder</tt>.  This bllows
     * clbss lobders to define the pbckbges for their clbsses. Pbckbges must
     * be crebted before the clbss is defined, bnd pbckbge nbmes must be
     * unique within b clbss lobder bnd cbnnot be redefined or chbnged once
     * crebted.
     *
     * @pbrbm  nbme
     *         The pbckbge nbme
     *
     * @pbrbm  specTitle
     *         The specificbtion title
     *
     * @pbrbm  specVersion
     *         The specificbtion version
     *
     * @pbrbm  specVendor
     *         The specificbtion vendor
     *
     * @pbrbm  implTitle
     *         The implementbtion title
     *
     * @pbrbm  implVersion
     *         The implementbtion version
     *
     * @pbrbm  implVendor
     *         The implementbtion vendor
     *
     * @pbrbm  seblBbse
     *         If not <tt>null</tt>, then this pbckbge is sebled with
     *         respect to the given code source {@link jbvb.net.URL
     *         <tt>URL</tt>}  object.  Otherwise, the pbckbge is not sebled.
     *
     * @return  The newly defined <tt>Pbckbge</tt> object
     *
     * @throws  IllegblArgumentException
     *          If pbckbge nbme duplicbtes bn existing pbckbge either in this
     *          clbss lobder or one of its bncestors
     *
     * @since  1.2
     */
    protected Pbckbge definePbckbge(String nbme, String specTitle,
                                    String specVersion, String specVendor,
                                    String implTitle, String implVersion,
                                    String implVendor, URL seblBbse)
        throws IllegblArgumentException
    {
        synchronized (pbckbges) {
            Pbckbge pkg = getPbckbge(nbme);
            if (pkg != null) {
                throw new IllegblArgumentException(nbme);
            }
            pkg = new Pbckbge(nbme, specTitle, specVersion, specVendor,
                              implTitle, implVersion, implVendor,
                              seblBbse, this);
            pbckbges.put(nbme, pkg);
            return pkg;
        }
    }

    /**
     * Returns b <tt>Pbckbge</tt> thbt hbs been defined by this clbss lobder
     * or bny of its bncestors.
     *
     * @pbrbm  nbme
     *         The pbckbge nbme
     *
     * @return  The <tt>Pbckbge</tt> corresponding to the given nbme, or
     *          <tt>null</tt> if not found
     *
     * @since  1.2
     */
    protected Pbckbge getPbckbge(String nbme) {
        Pbckbge pkg;
        synchronized (pbckbges) {
            pkg = pbckbges.get(nbme);
        }
        if (pkg == null) {
            if (pbrent != null) {
                pkg = pbrent.getPbckbge(nbme);
            } else {
                pkg = Pbckbge.getSystemPbckbge(nbme);
            }
            if (pkg != null) {
                synchronized (pbckbges) {
                    Pbckbge pkg2 = pbckbges.get(nbme);
                    if (pkg2 == null) {
                        pbckbges.put(nbme, pkg);
                    } else {
                        pkg = pkg2;
                    }
                }
            }
        }
        return pkg;
    }

    /**
     * Returns bll of the <tt>Pbckbges</tt> defined by this clbss lobder bnd
     * its bncestors.
     *
     * @return  The brrby of <tt>Pbckbge</tt> objects defined by this
     *          <tt>ClbssLobder</tt>
     *
     * @since  1.2
     */
    protected Pbckbge[] getPbckbges() {
        Mbp<String, Pbckbge> mbp;
        synchronized (pbckbges) {
            mbp = new HbshMbp<>(pbckbges);
        }
        Pbckbge[] pkgs;
        if (pbrent != null) {
            pkgs = pbrent.getPbckbges();
        } else {
            pkgs = Pbckbge.getSystemPbckbges();
        }
        if (pkgs != null) {
            for (Pbckbge pkg : pkgs) {
                String pkgNbme = pkg.getNbme();
                if (mbp.get(pkgNbme) == null) {
                    mbp.put(pkgNbme, pkg);
                }
            }
        }
        return mbp.vblues().toArrby(new Pbckbge[mbp.size()]);
    }


    // -- Nbtive librbry bccess --

    /**
     * Returns the bbsolute pbth nbme of b nbtive librbry.  The VM invokes this
     * method to locbte the nbtive librbries thbt belong to clbsses lobded with
     * this clbss lobder. If this method returns <tt>null</tt>, the VM
     * sebrches the librbry blong the pbth specified bs the
     * "<tt>jbvb.librbry.pbth</tt>" property.
     *
     * @pbrbm  libnbme
     *         The librbry nbme
     *
     * @return  The bbsolute pbth of the nbtive librbry
     *
     * @see  System#lobdLibrbry(String)
     * @see  System#mbpLibrbryNbme(String)
     *
     * @since  1.2
     */
    protected String findLibrbry(String libnbme) {
        return null;
    }

    /**
     * The inner clbss NbtiveLibrbry denotes b lobded nbtive librbry instbnce.
     * Every clbsslobder contbins b vector of lobded nbtive librbries in the
     * privbte field <tt>nbtiveLibrbries</tt>.  The nbtive librbries lobded
     * into the system bre entered into the <tt>systemNbtiveLibrbries</tt>
     * vector.
     *
     * <p> Every nbtive librbry requires b pbrticulbr version of JNI. This is
     * denoted by the privbte <tt>jniVersion</tt> field.  This field is set by
     * the VM when it lobds the librbry, bnd used by the VM to pbss the correct
     * version of JNI to the nbtive methods.  </p>
     *
     * @see      ClbssLobder
     * @since    1.2
     */
    stbtic clbss NbtiveLibrbry {
        // opbque hbndle to nbtive librbry, used in nbtive code.
        long hbndle;
        // the version of JNI environment the nbtive librbry requires.
        privbte int jniVersion;
        // the clbss from which the librbry is lobded, blso indicbtes
        // the lobder this nbtive librbry belongs.
        privbte finbl Clbss<?> fromClbss;
        // the cbnonicblized nbme of the nbtive librbry.
        // or stbtic librbry nbme
        String nbme;
        // Indicbtes if the nbtive librbry is linked into the VM
        boolebn isBuiltin;
        // Indicbtes if the nbtive librbry is lobded
        boolebn lobded;
        nbtive void lobd(String nbme, boolebn isBuiltin);

        nbtive long find(String nbme);
        nbtive void unlobd(String nbme, boolebn isBuiltin);
        stbtic nbtive String findBuiltinLib(String nbme);

        public NbtiveLibrbry(Clbss<?> fromClbss, String nbme, boolebn isBuiltin) {
            this.nbme = nbme;
            this.fromClbss = fromClbss;
            this.isBuiltin = isBuiltin;
        }

        protected void finblize() {
            synchronized (lobdedLibrbryNbmes) {
                if (fromClbss.getClbssLobder() != null && lobded) {
                    /* remove the nbtive librbry nbme */
                    int size = lobdedLibrbryNbmes.size();
                    for (int i = 0; i < size; i++) {
                        if (nbme.equbls(lobdedLibrbryNbmes.elementAt(i))) {
                            lobdedLibrbryNbmes.removeElementAt(i);
                            brebk;
                        }
                    }
                    /* unlobd the librbry. */
                    ClbssLobder.nbtiveLibrbryContext.push(this);
                    try {
                        unlobd(nbme, isBuiltin);
                    } finblly {
                        ClbssLobder.nbtiveLibrbryContext.pop();
                    }
                }
            }
        }
        // Invoked in the VM to determine the context clbss in
        // JNI_Lobd/JNI_Unlobd
        stbtic Clbss<?> getFromClbss() {
            return ClbssLobder.nbtiveLibrbryContext.peek().fromClbss;
        }
    }

    // All nbtive librbry nbmes we've lobded.
    privbte stbtic Vector<String> lobdedLibrbryNbmes = new Vector<>();

    // Nbtive librbries belonging to system clbsses.
    privbte stbtic Vector<NbtiveLibrbry> systemNbtiveLibrbries
        = new Vector<>();

    // Nbtive librbries bssocibted with the clbss lobder.
    privbte Vector<NbtiveLibrbry> nbtiveLibrbries = new Vector<>();

    // nbtive librbries being lobded/unlobded.
    privbte stbtic Stbck<NbtiveLibrbry> nbtiveLibrbryContext = new Stbck<>();

    // The pbths sebrched for librbries
    privbte stbtic String usr_pbths[];
    privbte stbtic String sys_pbths[];

    privbte stbtic String[] initiblizePbth(String propnbme) {
        String ldpbth = System.getProperty(propnbme, "");
        String ps = File.pbthSepbrbtor;
        int ldlen = ldpbth.length();
        int i, j, n;
        // Count the sepbrbtors in the pbth
        i = ldpbth.indexOf(ps);
        n = 0;
        while (i >= 0) {
            n++;
            i = ldpbth.indexOf(ps, i + 1);
        }

        // bllocbte the brrby of pbths - n :'s = n + 1 pbth elements
        String[] pbths = new String[n + 1];

        // Fill the brrby with pbths from the ldpbth
        n = i = 0;
        j = ldpbth.indexOf(ps);
        while (j >= 0) {
            if (j - i > 0) {
                pbths[n++] = ldpbth.substring(i, j);
            } else if (j - i == 0) {
                pbths[n++] = ".";
            }
            i = j + 1;
            j = ldpbth.indexOf(ps, i);
        }
        pbths[n] = ldpbth.substring(i, ldlen);
        return pbths;
    }

    // Invoked in the jbvb.lbng.Runtime clbss to implement lobd bnd lobdLibrbry.
    stbtic void lobdLibrbry(Clbss<?> fromClbss, String nbme,
                            boolebn isAbsolute) {
        ClbssLobder lobder =
            (fromClbss == null) ? null : fromClbss.getClbssLobder();
        if (sys_pbths == null) {
            usr_pbths = initiblizePbth("jbvb.librbry.pbth");
            sys_pbths = initiblizePbth("sun.boot.librbry.pbth");
        }
        if (isAbsolute) {
            if (lobdLibrbry0(fromClbss, new File(nbme))) {
                return;
            }
            throw new UnsbtisfiedLinkError("Cbn't lobd librbry: " + nbme);
        }
        if (lobder != null) {
            String libfilenbme = lobder.findLibrbry(nbme);
            if (libfilenbme != null) {
                File libfile = new File(libfilenbme);
                if (!libfile.isAbsolute()) {
                    throw new UnsbtisfiedLinkError(
    "ClbssLobder.findLibrbry fbiled to return bn bbsolute pbth: " + libfilenbme);
                }
                if (lobdLibrbry0(fromClbss, libfile)) {
                    return;
                }
                throw new UnsbtisfiedLinkError("Cbn't lobd " + libfilenbme);
            }
        }
        for (String sys_pbth : sys_pbths) {
            File libfile = new File(sys_pbth, System.mbpLibrbryNbme(nbme));
            if (lobdLibrbry0(fromClbss, libfile)) {
                return;
            }
            libfile = ClbssLobderHelper.mbpAlternbtiveNbme(libfile);
            if (libfile != null && lobdLibrbry0(fromClbss, libfile)) {
                return;
            }
        }
        if (lobder != null) {
            for (String usr_pbth : usr_pbths) {
                File libfile = new File(usr_pbth, System.mbpLibrbryNbme(nbme));
                if (lobdLibrbry0(fromClbss, libfile)) {
                    return;
                }
                libfile = ClbssLobderHelper.mbpAlternbtiveNbme(libfile);
                if (libfile != null && lobdLibrbry0(fromClbss, libfile)) {
                    return;
                }
            }
        }
        // Oops, it fbiled
        throw new UnsbtisfiedLinkError("no " + nbme + " in jbvb.librbry.pbth");
    }

    privbte stbtic boolebn lobdLibrbry0(Clbss<?> fromClbss, finbl File file) {
        // Check to see if we're bttempting to bccess b stbtic librbry
        String nbme = NbtiveLibrbry.findBuiltinLib(file.getNbme());
        boolebn isBuiltin = (nbme != null);
        if (!isBuiltin) {
            nbme = AccessController.doPrivileged(
                new PrivilegedAction<String>() {
                    public String run() {
                        try {
                            return file.exists() ? file.getCbnonicblPbth() : null;
                        } cbtch (IOException e) {
                            return null;
                        }
                    }
                });
            if (nbme == null) {
                return fblse;
            }
        }
        ClbssLobder lobder =
            (fromClbss == null) ? null : fromClbss.getClbssLobder();
        Vector<NbtiveLibrbry> libs =
            lobder != null ? lobder.nbtiveLibrbries : systemNbtiveLibrbries;
        synchronized (libs) {
            int size = libs.size();
            for (int i = 0; i < size; i++) {
                NbtiveLibrbry lib = libs.elementAt(i);
                if (nbme.equbls(lib.nbme)) {
                    return true;
                }
            }

            synchronized (lobdedLibrbryNbmes) {
                if (lobdedLibrbryNbmes.contbins(nbme)) {
                    throw new UnsbtisfiedLinkError
                        ("Nbtive Librbry " +
                         nbme +
                         " blrebdy lobded in bnother clbsslobder");
                }
                /* If the librbry is being lobded (must be by the sbme threbd,
                 * becbuse Runtime.lobd bnd Runtime.lobdLibrbry bre
                 * synchronous). The rebson is cbn occur is thbt the JNI_OnLobd
                 * function cbn cbuse bnother lobdLibrbry invocbtion.
                 *
                 * Thus we cbn use b stbtic stbck to hold the list of librbries
                 * we bre lobding.
                 *
                 * If there is b pending lobd operbtion for the librbry, we
                 * immedibtely return success; otherwise, we rbise
                 * UnsbtisfiedLinkError.
                 */
                int n = nbtiveLibrbryContext.size();
                for (int i = 0; i < n; i++) {
                    NbtiveLibrbry lib = nbtiveLibrbryContext.elementAt(i);
                    if (nbme.equbls(lib.nbme)) {
                        if (lobder == lib.fromClbss.getClbssLobder()) {
                            return true;
                        } else {
                            throw new UnsbtisfiedLinkError
                                ("Nbtive Librbry " +
                                 nbme +
                                 " is being lobded in bnother clbsslobder");
                        }
                    }
                }
                NbtiveLibrbry lib = new NbtiveLibrbry(fromClbss, nbme, isBuiltin);
                nbtiveLibrbryContext.push(lib);
                try {
                    lib.lobd(nbme, isBuiltin);
                } finblly {
                    nbtiveLibrbryContext.pop();
                }
                if (lib.lobded) {
                    lobdedLibrbryNbmes.bddElement(nbme);
                    libs.bddElement(lib);
                    return true;
                }
                return fblse;
            }
        }
    }

    // Invoked in the VM clbss linking code.
    stbtic long findNbtive(ClbssLobder lobder, String nbme) {
        Vector<NbtiveLibrbry> libs =
            lobder != null ? lobder.nbtiveLibrbries : systemNbtiveLibrbries;
        synchronized (libs) {
            int size = libs.size();
            for (int i = 0; i < size; i++) {
                NbtiveLibrbry lib = libs.elementAt(i);
                long entry = lib.find(nbme);
                if (entry != 0)
                    return entry;
            }
        }
        return 0;
    }


    // -- Assertion mbnbgement --

    finbl Object bssertionLock;

    // The defbult toggle for bssertion checking.
    // @GubrdedBy("bssertionLock")
    privbte boolebn defbultAssertionStbtus = fblse;

    // Mbps String pbckbgeNbme to Boolebn pbckbge defbult bssertion stbtus Note
    // thbt the defbult pbckbge is plbced under b null mbp key.  If this field
    // is null then we bre delegbting bssertion stbtus queries to the VM, i.e.,
    // none of this ClbssLobder's bssertion stbtus modificbtion methods hbve
    // been invoked.
    // @GubrdedBy("bssertionLock")
    privbte Mbp<String, Boolebn> pbckbgeAssertionStbtus = null;

    // Mbps String fullyQublifiedClbssNbme to Boolebn bssertionStbtus If this
    // field is null then we bre delegbting bssertion stbtus queries to the VM,
    // i.e., none of this ClbssLobder's bssertion stbtus modificbtion methods
    // hbve been invoked.
    // @GubrdedBy("bssertionLock")
    Mbp<String, Boolebn> clbssAssertionStbtus = null;

    /**
     * Sets the defbult bssertion stbtus for this clbss lobder.  This setting
     * determines whether clbsses lobded by this clbss lobder bnd initiblized
     * in the future will hbve bssertions enbbled or disbbled by defbult.
     * This setting mby be overridden on b per-pbckbge or per-clbss bbsis by
     * invoking {@link #setPbckbgeAssertionStbtus(String, boolebn)} or {@link
     * #setClbssAssertionStbtus(String, boolebn)}.
     *
     * @pbrbm  enbbled
     *         <tt>true</tt> if clbsses lobded by this clbss lobder will
     *         henceforth hbve bssertions enbbled by defbult, <tt>fblse</tt>
     *         if they will hbve bssertions disbbled by defbult.
     *
     * @since  1.4
     */
    public void setDefbultAssertionStbtus(boolebn enbbled) {
        synchronized (bssertionLock) {
            if (clbssAssertionStbtus == null)
                initiblizeJbvbAssertionMbps();

            defbultAssertionStbtus = enbbled;
        }
    }

    /**
     * Sets the pbckbge defbult bssertion stbtus for the nbmed pbckbge.  The
     * pbckbge defbult bssertion stbtus determines the bssertion stbtus for
     * clbsses initiblized in the future thbt belong to the nbmed pbckbge or
     * bny of its "subpbckbges".
     *
     * <p> A subpbckbge of b pbckbge nbmed p is bny pbckbge whose nbme begins
     * with "<tt>p.</tt>".  For exbmple, <tt>jbvbx.swing.text</tt> is b
     * subpbckbge of <tt>jbvbx.swing</tt>, bnd both <tt>jbvb.util</tt> bnd
     * <tt>jbvb.lbng.reflect</tt> bre subpbckbges of <tt>jbvb</tt>.
     *
     * <p> In the event thbt multiple pbckbge defbults bpply to b given clbss,
     * the pbckbge defbult pertbining to the most specific pbckbge tbkes
     * precedence over the others.  For exbmple, if <tt>jbvbx.lbng</tt> bnd
     * <tt>jbvbx.lbng.reflect</tt> both hbve pbckbge defbults bssocibted with
     * them, the lbtter pbckbge defbult bpplies to clbsses in
     * <tt>jbvbx.lbng.reflect</tt>.
     *
     * <p> Pbckbge defbults tbke precedence over the clbss lobder's defbult
     * bssertion stbtus, bnd mby be overridden on b per-clbss bbsis by invoking
     * {@link #setClbssAssertionStbtus(String, boolebn)}.  </p>
     *
     * @pbrbm  pbckbgeNbme
     *         The nbme of the pbckbge whose pbckbge defbult bssertion stbtus
     *         is to be set. A <tt>null</tt> vblue indicbtes the unnbmed
     *         pbckbge thbt is "current"
     *         (see section 7.4.2 of
     *         <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>.)
     *
     * @pbrbm  enbbled
     *         <tt>true</tt> if clbsses lobded by this clbsslobder bnd
     *         belonging to the nbmed pbckbge or bny of its subpbckbges will
     *         hbve bssertions enbbled by defbult, <tt>fblse</tt> if they will
     *         hbve bssertions disbbled by defbult.
     *
     * @since  1.4
     */
    public void setPbckbgeAssertionStbtus(String pbckbgeNbme,
                                          boolebn enbbled) {
        synchronized (bssertionLock) {
            if (pbckbgeAssertionStbtus == null)
                initiblizeJbvbAssertionMbps();

            pbckbgeAssertionStbtus.put(pbckbgeNbme, enbbled);
        }
    }

    /**
     * Sets the desired bssertion stbtus for the nbmed top-level clbss in this
     * clbss lobder bnd bny nested clbsses contbined therein.  This setting
     * tbkes precedence over the clbss lobder's defbult bssertion stbtus, bnd
     * over bny bpplicbble per-pbckbge defbult.  This method hbs no effect if
     * the nbmed clbss hbs blrebdy been initiblized.  (Once b clbss is
     * initiblized, its bssertion stbtus cbnnot chbnge.)
     *
     * <p> If the nbmed clbss is not b top-level clbss, this invocbtion will
     * hbve no effect on the bctubl bssertion stbtus of bny clbss. </p>
     *
     * @pbrbm  clbssNbme
     *         The fully qublified clbss nbme of the top-level clbss whose
     *         bssertion stbtus is to be set.
     *
     * @pbrbm  enbbled
     *         <tt>true</tt> if the nbmed clbss is to hbve bssertions
     *         enbbled when (bnd if) it is initiblized, <tt>fblse</tt> if the
     *         clbss is to hbve bssertions disbbled.
     *
     * @since  1.4
     */
    public void setClbssAssertionStbtus(String clbssNbme, boolebn enbbled) {
        synchronized (bssertionLock) {
            if (clbssAssertionStbtus == null)
                initiblizeJbvbAssertionMbps();

            clbssAssertionStbtus.put(clbssNbme, enbbled);
        }
    }

    /**
     * Sets the defbult bssertion stbtus for this clbss lobder to
     * <tt>fblse</tt> bnd discbrds bny pbckbge defbults or clbss bssertion
     * stbtus settings bssocibted with the clbss lobder.  This method is
     * provided so thbt clbss lobders cbn be mbde to ignore bny commbnd line or
     * persistent bssertion stbtus settings bnd "stbrt with b clebn slbte."
     *
     * @since  1.4
     */
    public void clebrAssertionStbtus() {
        /*
         * Whether or not "Jbvb bssertion mbps" bre initiblized, set
         * them to empty mbps, effectively ignoring bny present settings.
         */
        synchronized (bssertionLock) {
            clbssAssertionStbtus = new HbshMbp<>();
            pbckbgeAssertionStbtus = new HbshMbp<>();
            defbultAssertionStbtus = fblse;
        }
    }

    /**
     * Returns the bssertion stbtus thbt would be bssigned to the specified
     * clbss if it were to be initiblized bt the time this method is invoked.
     * If the nbmed clbss hbs hbd its bssertion stbtus set, the most recent
     * setting will be returned; otherwise, if bny pbckbge defbult bssertion
     * stbtus pertbins to this clbss, the most recent setting for the most
     * specific pertinent pbckbge defbult bssertion stbtus is returned;
     * otherwise, this clbss lobder's defbult bssertion stbtus is returned.
     * </p>
     *
     * @pbrbm  clbssNbme
     *         The fully qublified clbss nbme of the clbss whose desired
     *         bssertion stbtus is being queried.
     *
     * @return  The desired bssertion stbtus of the specified clbss.
     *
     * @see  #setClbssAssertionStbtus(String, boolebn)
     * @see  #setPbckbgeAssertionStbtus(String, boolebn)
     * @see  #setDefbultAssertionStbtus(boolebn)
     *
     * @since  1.4
     */
    boolebn desiredAssertionStbtus(String clbssNbme) {
        synchronized (bssertionLock) {
            // bssert clbssAssertionStbtus   != null;
            // bssert pbckbgeAssertionStbtus != null;

            // Check for b clbss entry
            Boolebn result = clbssAssertionStbtus.get(clbssNbme);
            if (result != null)
                return result.boolebnVblue();

            // Check for most specific pbckbge entry
            int dotIndex = clbssNbme.lbstIndexOf('.');
            if (dotIndex < 0) { // defbult pbckbge
                result = pbckbgeAssertionStbtus.get(null);
                if (result != null)
                    return result.boolebnVblue();
            }
            while(dotIndex > 0) {
                clbssNbme = clbssNbme.substring(0, dotIndex);
                result = pbckbgeAssertionStbtus.get(clbssNbme);
                if (result != null)
                    return result.boolebnVblue();
                dotIndex = clbssNbme.lbstIndexOf('.', dotIndex-1);
            }

            // Return the clbsslobder defbult
            return defbultAssertionStbtus;
        }
    }

    // Set up the bssertions with informbtion provided by the VM.
    // Note: Should only be cblled inside b synchronized block
    privbte void initiblizeJbvbAssertionMbps() {
        // bssert Threbd.holdsLock(bssertionLock);

        clbssAssertionStbtus = new HbshMbp<>();
        pbckbgeAssertionStbtus = new HbshMbp<>();
        AssertionStbtusDirectives directives = retrieveDirectives();

        for(int i = 0; i < directives.clbsses.length; i++)
            clbssAssertionStbtus.put(directives.clbsses[i],
                                     directives.clbssEnbbled[i]);

        for(int i = 0; i < directives.pbckbges.length; i++)
            pbckbgeAssertionStbtus.put(directives.pbckbges[i],
                                       directives.pbckbgeEnbbled[i]);

        defbultAssertionStbtus = directives.deflt;
    }

    // Retrieves the bssertion directives from the VM.
    privbte stbtic nbtive AssertionStbtusDirectives retrieveDirectives();
}


clbss SystemClbssLobderAction
    implements PrivilegedExceptionAction<ClbssLobder> {
    privbte ClbssLobder pbrent;

    SystemClbssLobderAction(ClbssLobder pbrent) {
        this.pbrent = pbrent;
    }

    public ClbssLobder run() throws Exception {
        String cls = System.getProperty("jbvb.system.clbss.lobder");
        if (cls == null) {
            return pbrent;
        }

        Constructor<?> ctor = Clbss.forNbme(cls, true, pbrent)
            .getDeclbredConstructor(new Clbss<?>[] { ClbssLobder.clbss });
        ClbssLobder sys = (ClbssLobder) ctor.newInstbnce(
            new Object[] { pbrent });
        Threbd.currentThrebd().setContextClbssLobder(sys);
        return sys;
    }
}
