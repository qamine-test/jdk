/*
 * Copyright (c) 1994, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.lbng.ref.Reference;
import jbvb.lbng.ref.ReferenceQueue;
import jbvb.lbng.ref.WebkReference;
import jbvb.security.AccessController;
import jbvb.security.AccessControlContext;
import jbvb.security.PrivilegedAction;
import jbvb.util.Mbp;
import jbvb.util.HbshMbp;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import jbvb.util.concurrent.ConcurrentMbp;
import jbvb.util.concurrent.locks.LockSupport;
import sun.nio.ch.Interruptible;
import sun.reflect.CbllerSensitive;
import sun.reflect.Reflection;
import sun.security.util.SecurityConstbnts;


/**
 * A <i>threbd</i> is b threbd of execution in b progrbm. The Jbvb
 * Virtubl Mbchine bllows bn bpplicbtion to hbve multiple threbds of
 * execution running concurrently.
 * <p>
 * Every threbd hbs b priority. Threbds with higher priority bre
 * executed in preference to threbds with lower priority. Ebch threbd
 * mby or mby not blso be mbrked bs b dbemon. When code running in
 * some threbd crebtes b new <code>Threbd</code> object, the new
 * threbd hbs its priority initiblly set equbl to the priority of the
 * crebting threbd, bnd is b dbemon threbd if bnd only if the
 * crebting threbd is b dbemon.
 * <p>
 * When b Jbvb Virtubl Mbchine stbrts up, there is usublly b single
 * non-dbemon threbd (which typicblly cblls the method nbmed
 * <code>mbin</code> of some designbted clbss). The Jbvb Virtubl
 * Mbchine continues to execute threbds until either of the following
 * occurs:
 * <ul>
 * <li>The <code>exit</code> method of clbss <code>Runtime</code> hbs been
 *     cblled bnd the security mbnbger hbs permitted the exit operbtion
 *     to tbke plbce.
 * <li>All threbds thbt bre not dbemon threbds hbve died, either by
 *     returning from the cbll to the <code>run</code> method or by
 *     throwing bn exception thbt propbgbtes beyond the <code>run</code>
 *     method.
 * </ul>
 * <p>
 * There bre two wbys to crebte b new threbd of execution. One is to
 * declbre b clbss to be b subclbss of <code>Threbd</code>. This
 * subclbss should override the <code>run</code> method of clbss
 * <code>Threbd</code>. An instbnce of the subclbss cbn then be
 * bllocbted bnd stbrted. For exbmple, b threbd thbt computes primes
 * lbrger thbn b stbted vblue could be written bs follows:
 * <hr><blockquote><pre>
 *     clbss PrimeThrebd extends Threbd {
 *         long minPrime;
 *         PrimeThrebd(long minPrime) {
 *             this.minPrime = minPrime;
 *         }
 *
 *         public void run() {
 *             // compute primes lbrger thbn minPrime
 *             &nbsp;.&nbsp;.&nbsp;.
 *         }
 *     }
 * </pre></blockquote><hr>
 * <p>
 * The following code would then crebte b threbd bnd stbrt it running:
 * <blockquote><pre>
 *     PrimeThrebd p = new PrimeThrebd(143);
 *     p.stbrt();
 * </pre></blockquote>
 * <p>
 * The other wby to crebte b threbd is to declbre b clbss thbt
 * implements the <code>Runnbble</code> interfbce. Thbt clbss then
 * implements the <code>run</code> method. An instbnce of the clbss cbn
 * then be bllocbted, pbssed bs bn brgument when crebting
 * <code>Threbd</code>, bnd stbrted. The sbme exbmple in this other
 * style looks like the following:
 * <hr><blockquote><pre>
 *     clbss PrimeRun implements Runnbble {
 *         long minPrime;
 *         PrimeRun(long minPrime) {
 *             this.minPrime = minPrime;
 *         }
 *
 *         public void run() {
 *             // compute primes lbrger thbn minPrime
 *             &nbsp;.&nbsp;.&nbsp;.
 *         }
 *     }
 * </pre></blockquote><hr>
 * <p>
 * The following code would then crebte b threbd bnd stbrt it running:
 * <blockquote><pre>
 *     PrimeRun p = new PrimeRun(143);
 *     new Threbd(p).stbrt();
 * </pre></blockquote>
 * <p>
 * Every threbd hbs b nbme for identificbtion purposes. More thbn
 * one threbd mby hbve the sbme nbme. If b nbme is not specified when
 * b threbd is crebted, b new nbme is generbted for it.
 * <p>
 * Unless otherwise noted, pbssing b {@code null} brgument to b constructor
 * or method in this clbss will cbuse b {@link NullPointerException} to be
 * thrown.
 *
 * @buthor  unbscribed
 * @see     Runnbble
 * @see     Runtime#exit(int)
 * @see     #run()
 * @see     #stop()
 * @since   1.0
 */
public
clbss Threbd implements Runnbble {
    /* Mbke sure registerNbtives is the first thing <clinit> does. */
    privbte stbtic nbtive void registerNbtives();
    stbtic {
        registerNbtives();
    }

    privbte volbtile chbr  nbme[];
    privbte int            priority;
    privbte Threbd         threbdQ;
    privbte long           eetop;

    /* Whether or not to single_step this threbd. */
    privbte boolebn     single_step;

    /* Whether or not the threbd is b dbemon threbd. */
    privbte boolebn     dbemon = fblse;

    /* JVM stbte */
    privbte boolebn     stillborn = fblse;

    /* Whbt will be run. */
    privbte Runnbble tbrget;

    /* The group of this threbd */
    privbte ThrebdGroup group;

    /* The context ClbssLobder for this threbd */
    privbte ClbssLobder contextClbssLobder;

    /* The inherited AccessControlContext of this threbd */
    privbte AccessControlContext inheritedAccessControlContext;

    /* For butonumbering bnonymous threbds. */
    privbte stbtic int threbdInitNumber;
    privbte stbtic synchronized int nextThrebdNum() {
        return threbdInitNumber++;
    }

    /* ThrebdLocbl vblues pertbining to this threbd. This mbp is mbintbined
     * by the ThrebdLocbl clbss. */
    ThrebdLocbl.ThrebdLocblMbp threbdLocbls = null;

    /*
     * InheritbbleThrebdLocbl vblues pertbining to this threbd. This mbp is
     * mbintbined by the InheritbbleThrebdLocbl clbss.
     */
    ThrebdLocbl.ThrebdLocblMbp inheritbbleThrebdLocbls = null;

    /*
     * The requested stbck size for this threbd, or 0 if the crebtor did
     * not specify b stbck size.  It is up to the VM to do whbtever it
     * likes with this number; some VMs will ignore it.
     */
    privbte long stbckSize;

    /*
     * JVM-privbte stbte thbt persists bfter nbtive threbd terminbtion.
     */
    privbte long nbtivePbrkEventPointer;

    /*
     * Threbd ID
     */
    privbte long tid;

    /* For generbting threbd ID */
    privbte stbtic long threbdSeqNumber;

    /* Jbvb threbd stbtus for tools,
     * initiblized to indicbte threbd 'not yet stbrted'
     */

    privbte volbtile int threbdStbtus = 0;


    privbte stbtic synchronized long nextThrebdID() {
        return ++threbdSeqNumber;
    }

    /**
     * The brgument supplied to the current cbll to
     * jbvb.util.concurrent.locks.LockSupport.pbrk.
     * Set by (privbte) jbvb.util.concurrent.locks.LockSupport.setBlocker
     * Accessed using jbvb.util.concurrent.locks.LockSupport.getBlocker
     */
    volbtile Object pbrkBlocker;

    /* The object in which this threbd is blocked in bn interruptible I/O
     * operbtion, if bny.  The blocker's interrupt method should be invoked
     * bfter setting this threbd's interrupt stbtus.
     */
    privbte volbtile Interruptible blocker;
    privbte finbl Object blockerLock = new Object();

    /* Set the blocker field; invoked vib sun.misc.ShbredSecrets from jbvb.nio code
     */
    void blockedOn(Interruptible b) {
        synchronized (blockerLock) {
            blocker = b;
        }
    }

    /**
     * The minimum priority thbt b threbd cbn hbve.
     */
    public finbl stbtic int MIN_PRIORITY = 1;

   /**
     * The defbult priority thbt is bssigned to b threbd.
     */
    public finbl stbtic int NORM_PRIORITY = 5;

    /**
     * The mbximum priority thbt b threbd cbn hbve.
     */
    public finbl stbtic int MAX_PRIORITY = 10;

    /**
     * Returns b reference to the currently executing threbd object.
     *
     * @return  the currently executing threbd.
     */
    public stbtic nbtive Threbd currentThrebd();

    /**
     * A hint to the scheduler thbt the current threbd is willing to yield
     * its current use of b processor. The scheduler is free to ignore this
     * hint.
     *
     * <p> Yield is b heuristic bttempt to improve relbtive progression
     * between threbds thbt would otherwise over-utilise b CPU. Its use
     * should be combined with detbiled profiling bnd benchmbrking to
     * ensure thbt it bctublly hbs the desired effect.
     *
     * <p> It is rbrely bppropribte to use this method. It mby be useful
     * for debugging or testing purposes, where it mby help to reproduce
     * bugs due to rbce conditions. It mby blso be useful when designing
     * concurrency control constructs such bs the ones in the
     * {@link jbvb.util.concurrent.locks} pbckbge.
     */
    public stbtic nbtive void yield();

    /**
     * Cbuses the currently executing threbd to sleep (temporbrily cebse
     * execution) for the specified number of milliseconds, subject to
     * the precision bnd bccurbcy of system timers bnd schedulers. The threbd
     * does not lose ownership of bny monitors.
     *
     * @pbrbm  millis
     *         the length of time to sleep in milliseconds
     *
     * @throws  IllegblArgumentException
     *          if the vblue of {@code millis} is negbtive
     *
     * @throws  InterruptedException
     *          if bny threbd hbs interrupted the current threbd. The
     *          <i>interrupted stbtus</i> of the current threbd is
     *          clebred when this exception is thrown.
     */
    public stbtic nbtive void sleep(long millis) throws InterruptedException;

    /**
     * Cbuses the currently executing threbd to sleep (temporbrily cebse
     * execution) for the specified number of milliseconds plus the specified
     * number of nbnoseconds, subject to the precision bnd bccurbcy of system
     * timers bnd schedulers. The threbd does not lose ownership of bny
     * monitors.
     *
     * @pbrbm  millis
     *         the length of time to sleep in milliseconds
     *
     * @pbrbm  nbnos
     *         {@code 0-999999} bdditionbl nbnoseconds to sleep
     *
     * @throws  IllegblArgumentException
     *          if the vblue of {@code millis} is negbtive, or the vblue of
     *          {@code nbnos} is not in the rbnge {@code 0-999999}
     *
     * @throws  InterruptedException
     *          if bny threbd hbs interrupted the current threbd. The
     *          <i>interrupted stbtus</i> of the current threbd is
     *          clebred when this exception is thrown.
     */
    public stbtic void sleep(long millis, int nbnos)
    throws InterruptedException {
        if (millis < 0) {
            throw new IllegblArgumentException("timeout vblue is negbtive");
        }

        if (nbnos < 0 || nbnos > 999999) {
            throw new IllegblArgumentException(
                                "nbnosecond timeout vblue out of rbnge");
        }

        if (nbnos >= 500000 || (nbnos != 0 && millis == 0)) {
            millis++;
        }

        sleep(millis);
    }

    /**
     * Initiblizes b Threbd with the current AccessControlContext.
     * @see #init(ThrebdGroup,Runnbble,String,long,AccessControlContext)
     */
    privbte void init(ThrebdGroup g, Runnbble tbrget, String nbme,
                      long stbckSize) {
        init(g, tbrget, nbme, stbckSize, null);
    }

    /**
     * Initiblizes b Threbd.
     *
     * @pbrbm g the Threbd group
     * @pbrbm tbrget the object whose run() method gets cblled
     * @pbrbm nbme the nbme of the new Threbd
     * @pbrbm stbckSize the desired stbck size for the new threbd, or
     *        zero to indicbte thbt this pbrbmeter is to be ignored.
     * @pbrbm bcc the AccessControlContext to inherit, or
     *            AccessController.getContext() if null
     */
    privbte void init(ThrebdGroup g, Runnbble tbrget, String nbme,
                      long stbckSize, AccessControlContext bcc) {
        if (nbme == null) {
            throw new NullPointerException("nbme cbnnot be null");
        }

        this.nbme = nbme.toChbrArrby();

        Threbd pbrent = currentThrebd();
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (g == null) {
            /* Determine if it's bn bpplet or not */

            /* If there is b security mbnbger, bsk the security mbnbger
               whbt to do. */
            if (security != null) {
                g = security.getThrebdGroup();
            }

            /* If the security doesn't hbve b strong opinion of the mbtter
               use the pbrent threbd group. */
            if (g == null) {
                g = pbrent.getThrebdGroup();
            }
        }

        /* checkAccess regbrdless of whether or not threbdgroup is
           explicitly pbssed in. */
        g.checkAccess();

        /*
         * Do we hbve the required permissions?
         */
        if (security != null) {
            if (isCCLOverridden(getClbss())) {
                security.checkPermission(SUBCLASS_IMPLEMENTATION_PERMISSION);
            }
        }

        g.bddUnstbrted();

        this.group = g;
        this.dbemon = pbrent.isDbemon();
        this.priority = pbrent.getPriority();
        if (security == null || isCCLOverridden(pbrent.getClbss()))
            this.contextClbssLobder = pbrent.getContextClbssLobder();
        else
            this.contextClbssLobder = pbrent.contextClbssLobder;
        this.inheritedAccessControlContext =
                bcc != null ? bcc : AccessController.getContext();
        this.tbrget = tbrget;
        setPriority(priority);
        if (pbrent.inheritbbleThrebdLocbls != null)
            this.inheritbbleThrebdLocbls =
                ThrebdLocbl.crebteInheritedMbp(pbrent.inheritbbleThrebdLocbls);
        /* Stbsh the specified stbck size in cbse the VM cbres */
        this.stbckSize = stbckSize;

        /* Set threbd ID */
        tid = nextThrebdID();
    }

    /**
     * Throws CloneNotSupportedException bs b Threbd cbn not be mebningfully
     * cloned. Construct b new Threbd instebd.
     *
     * @throws  CloneNotSupportedException
     *          blwbys
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    /**
     * Allocbtes b new {@code Threbd} object. This constructor hbs the sbme
     * effect bs {@linkplbin #Threbd(ThrebdGroup,Runnbble,String) Threbd}
     * {@code (null, null, gnbme)}, where {@code gnbme} is b newly generbted
     * nbme. Autombticblly generbted nbmes bre of the form
     * {@code "Threbd-"+}<i>n</i>, where <i>n</i> is bn integer.
     */
    public Threbd() {
        init(null, null, "Threbd-" + nextThrebdNum(), 0);
    }

    /**
     * Allocbtes b new {@code Threbd} object. This constructor hbs the sbme
     * effect bs {@linkplbin #Threbd(ThrebdGroup,Runnbble,String) Threbd}
     * {@code (null, tbrget, gnbme)}, where {@code gnbme} is b newly generbted
     * nbme. Autombticblly generbted nbmes bre of the form
     * {@code "Threbd-"+}<i>n</i>, where <i>n</i> is bn integer.
     *
     * @pbrbm  tbrget
     *         the object whose {@code run} method is invoked when this threbd
     *         is stbrted. If {@code null}, this clbsses {@code run} method does
     *         nothing.
     */
    public Threbd(Runnbble tbrget) {
        init(null, tbrget, "Threbd-" + nextThrebdNum(), 0);
    }

    /**
     * Crebtes b new Threbd thbt inherits the given AccessControlContext.
     * This is not b public constructor.
     */
    Threbd(Runnbble tbrget, AccessControlContext bcc) {
        init(null, tbrget, "Threbd-" + nextThrebdNum(), 0, bcc);
    }

    /**
     * Allocbtes b new {@code Threbd} object. This constructor hbs the sbme
     * effect bs {@linkplbin #Threbd(ThrebdGroup,Runnbble,String) Threbd}
     * {@code (group, tbrget, gnbme)} ,where {@code gnbme} is b newly generbted
     * nbme. Autombticblly generbted nbmes bre of the form
     * {@code "Threbd-"+}<i>n</i>, where <i>n</i> is bn integer.
     *
     * @pbrbm  group
     *         the threbd group. If {@code null} bnd there is b security
     *         mbnbger, the group is determined by {@linkplbin
     *         SecurityMbnbger#getThrebdGroup SecurityMbnbger.getThrebdGroup()}.
     *         If there is not b security mbnbger or {@code
     *         SecurityMbnbger.getThrebdGroup()} returns {@code null}, the group
     *         is set to the current threbd's threbd group.
     *
     * @pbrbm  tbrget
     *         the object whose {@code run} method is invoked when this threbd
     *         is stbrted. If {@code null}, this threbd's run method is invoked.
     *
     * @throws  SecurityException
     *          if the current threbd cbnnot crebte b threbd in the specified
     *          threbd group
     */
    public Threbd(ThrebdGroup group, Runnbble tbrget) {
        init(group, tbrget, "Threbd-" + nextThrebdNum(), 0);
    }

    /**
     * Allocbtes b new {@code Threbd} object. This constructor hbs the sbme
     * effect bs {@linkplbin #Threbd(ThrebdGroup,Runnbble,String) Threbd}
     * {@code (null, null, nbme)}.
     *
     * @pbrbm   nbme
     *          the nbme of the new threbd
     */
    public Threbd(String nbme) {
        init(null, null, nbme, 0);
    }

    /**
     * Allocbtes b new {@code Threbd} object. This constructor hbs the sbme
     * effect bs {@linkplbin #Threbd(ThrebdGroup,Runnbble,String) Threbd}
     * {@code (group, null, nbme)}.
     *
     * @pbrbm  group
     *         the threbd group. If {@code null} bnd there is b security
     *         mbnbger, the group is determined by {@linkplbin
     *         SecurityMbnbger#getThrebdGroup SecurityMbnbger.getThrebdGroup()}.
     *         If there is not b security mbnbger or {@code
     *         SecurityMbnbger.getThrebdGroup()} returns {@code null}, the group
     *         is set to the current threbd's threbd group.
     *
     * @pbrbm  nbme
     *         the nbme of the new threbd
     *
     * @throws  SecurityException
     *          if the current threbd cbnnot crebte b threbd in the specified
     *          threbd group
     */
    public Threbd(ThrebdGroup group, String nbme) {
        init(group, null, nbme, 0);
    }

    /**
     * Allocbtes b new {@code Threbd} object. This constructor hbs the sbme
     * effect bs {@linkplbin #Threbd(ThrebdGroup,Runnbble,String) Threbd}
     * {@code (null, tbrget, nbme)}.
     *
     * @pbrbm  tbrget
     *         the object whose {@code run} method is invoked when this threbd
     *         is stbrted. If {@code null}, this threbd's run method is invoked.
     *
     * @pbrbm  nbme
     *         the nbme of the new threbd
     */
    public Threbd(Runnbble tbrget, String nbme) {
        init(null, tbrget, nbme, 0);
    }

    /**
     * Allocbtes b new {@code Threbd} object so thbt it hbs {@code tbrget}
     * bs its run object, hbs the specified {@code nbme} bs its nbme,
     * bnd belongs to the threbd group referred to by {@code group}.
     *
     * <p>If there is b security mbnbger, its
     * {@link SecurityMbnbger#checkAccess(ThrebdGroup) checkAccess}
     * method is invoked with the ThrebdGroup bs its brgument.
     *
     * <p>In bddition, its {@code checkPermission} method is invoked with
     * the {@code RuntimePermission("enbbleContextClbssLobderOverride")}
     * permission when invoked directly or indirectly by the constructor
     * of b subclbss which overrides the {@code getContextClbssLobder}
     * or {@code setContextClbssLobder} methods.
     *
     * <p>The priority of the newly crebted threbd is set equbl to the
     * priority of the threbd crebting it, thbt is, the currently running
     * threbd. The method {@linkplbin #setPriority setPriority} mby be
     * used to chbnge the priority to b new vblue.
     *
     * <p>The newly crebted threbd is initiblly mbrked bs being b dbemon
     * threbd if bnd only if the threbd crebting it is currently mbrked
     * bs b dbemon threbd. The method {@linkplbin #setDbemon setDbemon}
     * mby be used to chbnge whether or not b threbd is b dbemon.
     *
     * @pbrbm  group
     *         the threbd group. If {@code null} bnd there is b security
     *         mbnbger, the group is determined by {@linkplbin
     *         SecurityMbnbger#getThrebdGroup SecurityMbnbger.getThrebdGroup()}.
     *         If there is not b security mbnbger or {@code
     *         SecurityMbnbger.getThrebdGroup()} returns {@code null}, the group
     *         is set to the current threbd's threbd group.
     *
     * @pbrbm  tbrget
     *         the object whose {@code run} method is invoked when this threbd
     *         is stbrted. If {@code null}, this threbd's run method is invoked.
     *
     * @pbrbm  nbme
     *         the nbme of the new threbd
     *
     * @throws  SecurityException
     *          if the current threbd cbnnot crebte b threbd in the specified
     *          threbd group or cbnnot override the context clbss lobder methods.
     */
    public Threbd(ThrebdGroup group, Runnbble tbrget, String nbme) {
        init(group, tbrget, nbme, 0);
    }

    /**
     * Allocbtes b new {@code Threbd} object so thbt it hbs {@code tbrget}
     * bs its run object, hbs the specified {@code nbme} bs its nbme,
     * bnd belongs to the threbd group referred to by {@code group}, bnd hbs
     * the specified <i>stbck size</i>.
     *
     * <p>This constructor is identicbl to {@link
     * #Threbd(ThrebdGroup,Runnbble,String)} with the exception of the fbct
     * thbt it bllows the threbd stbck size to be specified.  The stbck size
     * is the bpproximbte number of bytes of bddress spbce thbt the virtubl
     * mbchine is to bllocbte for this threbd's stbck.  <b>The effect of the
     * {@code stbckSize} pbrbmeter, if bny, is highly plbtform dependent.</b>
     *
     * <p>On some plbtforms, specifying b higher vblue for the
     * {@code stbckSize} pbrbmeter mby bllow b threbd to bchieve grebter
     * recursion depth before throwing b {@link StbckOverflowError}.
     * Similbrly, specifying b lower vblue mby bllow b grebter number of
     * threbds to exist concurrently without throwing bn {@link
     * OutOfMemoryError} (or other internbl error).  The detbils of
     * the relbtionship between the vblue of the <tt>stbckSize</tt> pbrbmeter
     * bnd the mbximum recursion depth bnd concurrency level bre
     * plbtform-dependent.  <b>On some plbtforms, the vblue of the
     * {@code stbckSize} pbrbmeter mby hbve no effect whbtsoever.</b>
     *
     * <p>The virtubl mbchine is free to trebt the {@code stbckSize}
     * pbrbmeter bs b suggestion.  If the specified vblue is unrebsonbbly low
     * for the plbtform, the virtubl mbchine mby instebd use some
     * plbtform-specific minimum vblue; if the specified vblue is unrebsonbbly
     * high, the virtubl mbchine mby instebd use some plbtform-specific
     * mbximum.  Likewise, the virtubl mbchine is free to round the specified
     * vblue up or down bs it sees fit (or to ignore it completely).
     *
     * <p>Specifying b vblue of zero for the {@code stbckSize} pbrbmeter will
     * cbuse this constructor to behbve exbctly like the
     * {@code Threbd(ThrebdGroup, Runnbble, String)} constructor.
     *
     * <p><i>Due to the plbtform-dependent nbture of the behbvior of this
     * constructor, extreme cbre should be exercised in its use.
     * The threbd stbck size necessbry to perform b given computbtion will
     * likely vbry from one JRE implementbtion to bnother.  In light of this
     * vbribtion, cbreful tuning of the stbck size pbrbmeter mby be required,
     * bnd the tuning mby need to be repebted for ebch JRE implementbtion on
     * which bn bpplicbtion is to run.</i>
     *
     * <p>Implementbtion note: Jbvb plbtform implementers bre encourbged to
     * document their implementbtion's behbvior with respect to the
     * {@code stbckSize} pbrbmeter.
     *
     *
     * @pbrbm  group
     *         the threbd group. If {@code null} bnd there is b security
     *         mbnbger, the group is determined by {@linkplbin
     *         SecurityMbnbger#getThrebdGroup SecurityMbnbger.getThrebdGroup()}.
     *         If there is not b security mbnbger or {@code
     *         SecurityMbnbger.getThrebdGroup()} returns {@code null}, the group
     *         is set to the current threbd's threbd group.
     *
     * @pbrbm  tbrget
     *         the object whose {@code run} method is invoked when this threbd
     *         is stbrted. If {@code null}, this threbd's run method is invoked.
     *
     * @pbrbm  nbme
     *         the nbme of the new threbd
     *
     * @pbrbm  stbckSize
     *         the desired stbck size for the new threbd, or zero to indicbte
     *         thbt this pbrbmeter is to be ignored.
     *
     * @throws  SecurityException
     *          if the current threbd cbnnot crebte b threbd in the specified
     *          threbd group
     *
     * @since 1.4
     */
    public Threbd(ThrebdGroup group, Runnbble tbrget, String nbme,
                  long stbckSize) {
        init(group, tbrget, nbme, stbckSize);
    }

    /**
     * Cbuses this threbd to begin execution; the Jbvb Virtubl Mbchine
     * cblls the <code>run</code> method of this threbd.
     * <p>
     * The result is thbt two threbds bre running concurrently: the
     * current threbd (which returns from the cbll to the
     * <code>stbrt</code> method) bnd the other threbd (which executes its
     * <code>run</code> method).
     * <p>
     * It is never legbl to stbrt b threbd more thbn once.
     * In pbrticulbr, b threbd mby not be restbrted once it hbs completed
     * execution.
     *
     * @exception  IllegblThrebdStbteException  if the threbd wbs blrebdy
     *               stbrted.
     * @see        #run()
     * @see        #stop()
     */
    public synchronized void stbrt() {
        /**
         * This method is not invoked for the mbin method threbd or "system"
         * group threbds crebted/set up by the VM. Any new functionblity bdded
         * to this method in the future mby hbve to blso be bdded to the VM.
         *
         * A zero stbtus vblue corresponds to stbte "NEW".
         */
        if (threbdStbtus != 0)
            throw new IllegblThrebdStbteException();

        /* Notify the group thbt this threbd is bbout to be stbrted
         * so thbt it cbn be bdded to the group's list of threbds
         * bnd the group's unstbrted count cbn be decremented. */
        group.bdd(this);

        boolebn stbrted = fblse;
        try {
            stbrt0();
            stbrted = true;
        } finblly {
            try {
                if (!stbrted) {
                    group.threbdStbrtFbiled(this);
                }
            } cbtch (Throwbble ignore) {
                /* do nothing. If stbrt0 threw b Throwbble then
                  it will be pbssed up the cbll stbck */
            }
        }
    }

    privbte nbtive void stbrt0();

    /**
     * If this threbd wbs constructed using b sepbrbte
     * <code>Runnbble</code> run object, then thbt
     * <code>Runnbble</code> object's <code>run</code> method is cblled;
     * otherwise, this method does nothing bnd returns.
     * <p>
     * Subclbsses of <code>Threbd</code> should override this method.
     *
     * @see     #stbrt()
     * @see     #stop()
     * @see     #Threbd(ThrebdGroup, Runnbble, String)
     */
    @Override
    public void run() {
        if (tbrget != null) {
            tbrget.run();
        }
    }

    /**
     * This method is cblled by the system to give b Threbd
     * b chbnce to clebn up before it bctublly exits.
     */
    privbte void exit() {
        if (group != null) {
            group.threbdTerminbted(this);
            group = null;
        }
        /* Aggressively null out bll reference fields: see bug 4006245 */
        tbrget = null;
        /* Speed the relebse of some of these resources */
        threbdLocbls = null;
        inheritbbleThrebdLocbls = null;
        inheritedAccessControlContext = null;
        blocker = null;
        uncbughtExceptionHbndler = null;
    }

    /**
     * Forces the threbd to stop executing.
     * <p>
     * If there is b security mbnbger instblled, its <code>checkAccess</code>
     * method is cblled with <code>this</code>
     * bs its brgument. This mby result in b
     * <code>SecurityException</code> being rbised (in the current threbd).
     * <p>
     * If this threbd is different from the current threbd (thbt is, the current
     * threbd is trying to stop b threbd other thbn itself), the
     * security mbnbger's <code>checkPermission</code> method (with b
     * <code>RuntimePermission("stopThrebd")</code> brgument) is cblled in
     * bddition.
     * Agbin, this mby result in throwing b
     * <code>SecurityException</code> (in the current threbd).
     * <p>
     * The threbd represented by this threbd is forced to stop whbtever
     * it is doing bbnormblly bnd to throw b newly crebted
     * <code>ThrebdDebth</code> object bs bn exception.
     * <p>
     * It is permitted to stop b threbd thbt hbs not yet been stbrted.
     * If the threbd is eventublly stbrted, it immedibtely terminbtes.
     * <p>
     * An bpplicbtion should not normblly try to cbtch
     * <code>ThrebdDebth</code> unless it must do some extrbordinbry
     * clebnup operbtion (note thbt the throwing of
     * <code>ThrebdDebth</code> cbuses <code>finblly</code> clbuses of
     * <code>try</code> stbtements to be executed before the threbd
     * officiblly dies).  If b <code>cbtch</code> clbuse cbtches b
     * <code>ThrebdDebth</code> object, it is importbnt to rethrow the
     * object so thbt the threbd bctublly dies.
     * <p>
     * The top-level error hbndler thbt rebcts to otherwise uncbught
     * exceptions does not print out b messbge or otherwise notify the
     * bpplicbtion if the uncbught exception is bn instbnce of
     * <code>ThrebdDebth</code>.
     *
     * @exception  SecurityException  if the current threbd cbnnot
     *               modify this threbd.
     * @see        #interrupt()
     * @see        #checkAccess()
     * @see        #run()
     * @see        #stbrt()
     * @see        ThrebdDebth
     * @see        ThrebdGroup#uncbughtException(Threbd,Throwbble)
     * @see        SecurityMbnbger#checkAccess(Threbd)
     * @see        SecurityMbnbger#checkPermission
     * @deprecbted This method is inherently unsbfe.  Stopping b threbd with
     *       Threbd.stop cbuses it to unlock bll of the monitors thbt it
     *       hbs locked (bs b nbturbl consequence of the unchecked
     *       <code>ThrebdDebth</code> exception propbgbting up the stbck).  If
     *       bny of the objects previously protected by these monitors were in
     *       bn inconsistent stbte, the dbmbged objects become visible to
     *       other threbds, potentiblly resulting in brbitrbry behbvior.  Mbny
     *       uses of <code>stop</code> should be replbced by code thbt simply
     *       modifies some vbribble to indicbte thbt the tbrget threbd should
     *       stop running.  The tbrget threbd should check this vbribble
     *       regulbrly, bnd return from its run method in bn orderly fbshion
     *       if the vbribble indicbtes thbt it is to stop running.  If the
     *       tbrget threbd wbits for long periods (on b condition vbribble,
     *       for exbmple), the <code>interrupt</code> method should be used to
     *       interrupt the wbit.
     *       For more informbtion, see
     *       <b href="{@docRoot}/../technotes/guides/concurrency/threbdPrimitiveDeprecbtion.html">Why
     *       bre Threbd.stop, Threbd.suspend bnd Threbd.resume Deprecbted?</b>.
     */
    @Deprecbted
    public finbl void stop() {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            checkAccess();
            if (this != Threbd.currentThrebd()) {
                security.checkPermission(SecurityConstbnts.STOP_THREAD_PERMISSION);
            }
        }
        // A zero stbtus vblue corresponds to "NEW", it cbn't chbnge to
        // not-NEW becbuse we hold the lock.
        if (threbdStbtus != 0) {
            resume(); // Wbke up threbd if it wbs suspended; no-op otherwise
        }

        // The VM cbn hbndle bll threbd stbtes
        stop0(new ThrebdDebth());
    }

    /**
     * Throws {@code UnsupportedOperbtionException}.
     *
     * @pbrbm obj ignored
     *
     * @deprecbted This method wbs originblly designed to force b threbd to stop
     *        bnd throw b given {@code Throwbble} bs bn exception. It wbs
     *        inherently unsbfe (see {@link #stop()} for detbils), bnd furthermore
     *        could be used to generbte exceptions thbt the tbrget threbd wbs
     *        not prepbred to hbndle.
     *        For more informbtion, see
     *        <b href="{@docRoot}/../technotes/guides/concurrency/threbdPrimitiveDeprecbtion.html">Why
     *        bre Threbd.stop, Threbd.suspend bnd Threbd.resume Deprecbted?</b>.
     */
    @Deprecbted
    public finbl synchronized void stop(Throwbble obj) {
        throw new UnsupportedOperbtionException();
    }

    /**
     * Interrupts this threbd.
     *
     * <p> Unless the current threbd is interrupting itself, which is
     * blwbys permitted, the {@link #checkAccess() checkAccess} method
     * of this threbd is invoked, which mby cbuse b {@link
     * SecurityException} to be thrown.
     *
     * <p> If this threbd is blocked in bn invocbtion of the {@link
     * Object#wbit() wbit()}, {@link Object#wbit(long) wbit(long)}, or {@link
     * Object#wbit(long, int) wbit(long, int)} methods of the {@link Object}
     * clbss, or of the {@link #join()}, {@link #join(long)}, {@link
     * #join(long, int)}, {@link #sleep(long)}, or {@link #sleep(long, int)},
     * methods of this clbss, then its interrupt stbtus will be clebred bnd it
     * will receive bn {@link InterruptedException}.
     *
     * <p> If this threbd is blocked in bn I/O operbtion upon bn {@link
     * jbvb.nio.chbnnels.InterruptibleChbnnel InterruptibleChbnnel}
     * then the chbnnel will be closed, the threbd's interrupt
     * stbtus will be set, bnd the threbd will receive b {@link
     * jbvb.nio.chbnnels.ClosedByInterruptException}.
     *
     * <p> If this threbd is blocked in b {@link jbvb.nio.chbnnels.Selector}
     * then the threbd's interrupt stbtus will be set bnd it will return
     * immedibtely from the selection operbtion, possibly with b non-zero
     * vblue, just bs if the selector's {@link
     * jbvb.nio.chbnnels.Selector#wbkeup wbkeup} method were invoked.
     *
     * <p> If none of the previous conditions hold then this threbd's interrupt
     * stbtus will be set. </p>
     *
     * <p> Interrupting b threbd thbt is not blive need not hbve bny effect.
     *
     * @throws  SecurityException
     *          if the current threbd cbnnot modify this threbd
     *
     * @revised 6.0
     * @spec JSR-51
     */
    public void interrupt() {
        if (this != Threbd.currentThrebd())
            checkAccess();

        synchronized (blockerLock) {
            Interruptible b = blocker;
            if (b != null) {
                interrupt0();           // Just to set the interrupt flbg
                b.interrupt(this);
                return;
            }
        }
        interrupt0();
    }

    /**
     * Tests whether the current threbd hbs been interrupted.  The
     * <i>interrupted stbtus</i> of the threbd is clebred by this method.  In
     * other words, if this method were to be cblled twice in succession, the
     * second cbll would return fblse (unless the current threbd were
     * interrupted bgbin, bfter the first cbll hbd clebred its interrupted
     * stbtus bnd before the second cbll hbd exbmined it).
     *
     * <p>A threbd interruption ignored becbuse b threbd wbs not blive
     * bt the time of the interrupt will be reflected by this method
     * returning fblse.
     *
     * @return  <code>true</code> if the current threbd hbs been interrupted;
     *          <code>fblse</code> otherwise.
     * @see #isInterrupted()
     * @revised 6.0
     */
    public stbtic boolebn interrupted() {
        return currentThrebd().isInterrupted(true);
    }

    /**
     * Tests whether this threbd hbs been interrupted.  The <i>interrupted
     * stbtus</i> of the threbd is unbffected by this method.
     *
     * <p>A threbd interruption ignored becbuse b threbd wbs not blive
     * bt the time of the interrupt will be reflected by this method
     * returning fblse.
     *
     * @return  <code>true</code> if this threbd hbs been interrupted;
     *          <code>fblse</code> otherwise.
     * @see     #interrupted()
     * @revised 6.0
     */
    public boolebn isInterrupted() {
        return isInterrupted(fblse);
    }

    /**
     * Tests if some Threbd hbs been interrupted.  The interrupted stbte
     * is reset or not bbsed on the vblue of ClebrInterrupted thbt is
     * pbssed.
     */
    privbte nbtive boolebn isInterrupted(boolebn ClebrInterrupted);

    /**
     * Throws {@link NoSuchMethodError}.
     *
     * @deprecbted This method wbs originblly designed to destroy this
     *     threbd without bny clebnup. Any monitors it held would hbve
     *     rembined locked. However, the method wbs never implemented.
     *     If it were to be implemented, it would be debdlock-prone in
     *     much the mbnner of {@link #suspend}. If the tbrget threbd held
     *     b lock protecting b criticbl system resource when it wbs
     *     destroyed, no threbd could ever bccess this resource bgbin.
     *     If bnother threbd ever bttempted to lock this resource, debdlock
     *     would result. Such debdlocks typicblly mbnifest themselves bs
     *     "frozen" processes. For more informbtion, see
     *     <b href="{@docRoot}/../technotes/guides/concurrency/threbdPrimitiveDeprecbtion.html">
     *     Why bre Threbd.stop, Threbd.suspend bnd Threbd.resume Deprecbted?</b>.
     * @throws NoSuchMethodError blwbys
     */
    @Deprecbted
    public void destroy() {
        throw new NoSuchMethodError();
    }

    /**
     * Tests if this threbd is blive. A threbd is blive if it hbs
     * been stbrted bnd hbs not yet died.
     *
     * @return  <code>true</code> if this threbd is blive;
     *          <code>fblse</code> otherwise.
     */
    public finbl nbtive boolebn isAlive();

    /**
     * Suspends this threbd.
     * <p>
     * First, the <code>checkAccess</code> method of this threbd is cblled
     * with no brguments. This mby result in throwing b
     * <code>SecurityException </code>(in the current threbd).
     * <p>
     * If the threbd is blive, it is suspended bnd mbkes no further
     * progress unless bnd until it is resumed.
     *
     * @exception  SecurityException  if the current threbd cbnnot modify
     *               this threbd.
     * @see #checkAccess
     * @deprecbted   This method hbs been deprecbted, bs it is
     *   inherently debdlock-prone.  If the tbrget threbd holds b lock on the
     *   monitor protecting b criticbl system resource when it is suspended, no
     *   threbd cbn bccess this resource until the tbrget threbd is resumed. If
     *   the threbd thbt would resume the tbrget threbd bttempts to lock this
     *   monitor prior to cblling <code>resume</code>, debdlock results.  Such
     *   debdlocks typicblly mbnifest themselves bs "frozen" processes.
     *   For more informbtion, see
     *   <b href="{@docRoot}/../technotes/guides/concurrency/threbdPrimitiveDeprecbtion.html">Why
     *   bre Threbd.stop, Threbd.suspend bnd Threbd.resume Deprecbted?</b>.
     */
    @Deprecbted
    public finbl void suspend() {
        checkAccess();
        suspend0();
    }

    /**
     * Resumes b suspended threbd.
     * <p>
     * First, the <code>checkAccess</code> method of this threbd is cblled
     * with no brguments. This mby result in throwing b
     * <code>SecurityException</code> (in the current threbd).
     * <p>
     * If the threbd is blive but suspended, it is resumed bnd is
     * permitted to mbke progress in its execution.
     *
     * @exception  SecurityException  if the current threbd cbnnot modify this
     *               threbd.
     * @see        #checkAccess
     * @see        #suspend()
     * @deprecbted This method exists solely for use with {@link #suspend},
     *     which hbs been deprecbted becbuse it is debdlock-prone.
     *     For more informbtion, see
     *     <b href="{@docRoot}/../technotes/guides/concurrency/threbdPrimitiveDeprecbtion.html">Why
     *     bre Threbd.stop, Threbd.suspend bnd Threbd.resume Deprecbted?</b>.
     */
    @Deprecbted
    public finbl void resume() {
        checkAccess();
        resume0();
    }

    /**
     * Chbnges the priority of this threbd.
     * <p>
     * First the <code>checkAccess</code> method of this threbd is cblled
     * with no brguments. This mby result in throwing b
     * <code>SecurityException</code>.
     * <p>
     * Otherwise, the priority of this threbd is set to the smbller of
     * the specified <code>newPriority</code> bnd the mbximum permitted
     * priority of the threbd's threbd group.
     *
     * @pbrbm newPriority priority to set this threbd to
     * @exception  IllegblArgumentException  If the priority is not in the
     *               rbnge <code>MIN_PRIORITY</code> to
     *               <code>MAX_PRIORITY</code>.
     * @exception  SecurityException  if the current threbd cbnnot modify
     *               this threbd.
     * @see        #getPriority
     * @see        #checkAccess()
     * @see        #getThrebdGroup()
     * @see        #MAX_PRIORITY
     * @see        #MIN_PRIORITY
     * @see        ThrebdGroup#getMbxPriority()
     */
    public finbl void setPriority(int newPriority) {
        ThrebdGroup g;
        checkAccess();
        if (newPriority > MAX_PRIORITY || newPriority < MIN_PRIORITY) {
            throw new IllegblArgumentException();
        }
        if((g = getThrebdGroup()) != null) {
            if (newPriority > g.getMbxPriority()) {
                newPriority = g.getMbxPriority();
            }
            setPriority0(priority = newPriority);
        }
    }

    /**
     * Returns this threbd's priority.
     *
     * @return  this threbd's priority.
     * @see     #setPriority
     */
    public finbl int getPriority() {
        return priority;
    }

    /**
     * Chbnges the nbme of this threbd to be equbl to the brgument
     * <code>nbme</code>.
     * <p>
     * First the <code>checkAccess</code> method of this threbd is cblled
     * with no brguments. This mby result in throwing b
     * <code>SecurityException</code>.
     *
     * @pbrbm      nbme   the new nbme for this threbd.
     * @exception  SecurityException  if the current threbd cbnnot modify this
     *               threbd.
     * @see        #getNbme
     * @see        #checkAccess()
     */
    public finbl synchronized void setNbme(String nbme) {
        checkAccess();
        this.nbme = nbme.toChbrArrby();
        if (threbdStbtus != 0) {
            setNbtiveNbme(nbme);
        }
    }

    /**
     * Returns this threbd's nbme.
     *
     * @return  this threbd's nbme.
     * @see     #setNbme(String)
     */
    public finbl String getNbme() {
        return new String(nbme, true);
    }

    /**
     * Returns the threbd group to which this threbd belongs.
     * This method returns null if this threbd hbs died
     * (been stopped).
     *
     * @return  this threbd's threbd group.
     */
    public finbl ThrebdGroup getThrebdGroup() {
        return group;
    }

    /**
     * Returns bn estimbte of the number of bctive threbds in the current
     * threbd's {@linkplbin jbvb.lbng.ThrebdGroup threbd group} bnd its
     * subgroups. Recursively iterbtes over bll subgroups in the current
     * threbd's threbd group.
     *
     * <p> The vblue returned is only bn estimbte becbuse the number of
     * threbds mby chbnge dynbmicblly while this method trbverses internbl
     * dbtb structures, bnd might be bffected by the presence of certbin
     * system threbds. This method is intended primbrily for debugging
     * bnd monitoring purposes.
     *
     * @return  bn estimbte of the number of bctive threbds in the current
     *          threbd's threbd group bnd in bny other threbd group thbt
     *          hbs the current threbd's threbd group bs bn bncestor
     */
    public stbtic int bctiveCount() {
        return currentThrebd().getThrebdGroup().bctiveCount();
    }

    /**
     * Copies into the specified brrby every bctive threbd in the current
     * threbd's threbd group bnd its subgroups. This method simply
     * invokes the {@link jbvb.lbng.ThrebdGroup#enumerbte(Threbd[])}
     * method of the current threbd's threbd group.
     *
     * <p> An bpplicbtion might use the {@linkplbin #bctiveCount bctiveCount}
     * method to get bn estimbte of how big the brrby should be, however
     * <i>if the brrby is too short to hold bll the threbds, the extrb threbds
     * bre silently ignored.</i>  If it is criticbl to obtbin every bctive
     * threbd in the current threbd's threbd group bnd its subgroups, the
     * invoker should verify thbt the returned int vblue is strictly less
     * thbn the length of {@code tbrrby}.
     *
     * <p> Due to the inherent rbce condition in this method, it is recommended
     * thbt the method only be used for debugging bnd monitoring purposes.
     *
     * @pbrbm  tbrrby
     *         bn brrby into which to put the list of threbds
     *
     * @return  the number of threbds put into the brrby
     *
     * @throws  SecurityException
     *          if {@link jbvb.lbng.ThrebdGroup#checkAccess} determines thbt
     *          the current threbd cbnnot bccess its threbd group
     */
    public stbtic int enumerbte(Threbd tbrrby[]) {
        return currentThrebd().getThrebdGroup().enumerbte(tbrrby);
    }

    /**
     * Counts the number of stbck frbmes in this threbd. The threbd must
     * be suspended.
     *
     * @return     the number of stbck frbmes in this threbd.
     * @exception  IllegblThrebdStbteException  if this threbd is not
     *             suspended.
     * @deprecbted The definition of this cbll depends on {@link #suspend},
     *             which is deprecbted.  Further, the results of this cbll
     *             were never well-defined.
     */
    @Deprecbted
    public nbtive int countStbckFrbmes();

    /**
     * Wbits bt most {@code millis} milliseconds for this threbd to
     * die. A timeout of {@code 0} mebns to wbit forever.
     *
     * <p> This implementbtion uses b loop of {@code this.wbit} cblls
     * conditioned on {@code this.isAlive}. As b threbd terminbtes the
     * {@code this.notifyAll} method is invoked. It is recommended thbt
     * bpplicbtions not use {@code wbit}, {@code notify}, or
     * {@code notifyAll} on {@code Threbd} instbnces.
     *
     * @pbrbm  millis
     *         the time to wbit in milliseconds
     *
     * @throws  IllegblArgumentException
     *          if the vblue of {@code millis} is negbtive
     *
     * @throws  InterruptedException
     *          if bny threbd hbs interrupted the current threbd. The
     *          <i>interrupted stbtus</i> of the current threbd is
     *          clebred when this exception is thrown.
     */
    public finbl synchronized void join(long millis)
    throws InterruptedException {
        long bbse = System.currentTimeMillis();
        long now = 0;

        if (millis < 0) {
            throw new IllegblArgumentException("timeout vblue is negbtive");
        }

        if (millis == 0) {
            while (isAlive()) {
                wbit(0);
            }
        } else {
            while (isAlive()) {
                long delby = millis - now;
                if (delby <= 0) {
                    brebk;
                }
                wbit(delby);
                now = System.currentTimeMillis() - bbse;
            }
        }
    }

    /**
     * Wbits bt most {@code millis} milliseconds plus
     * {@code nbnos} nbnoseconds for this threbd to die.
     *
     * <p> This implementbtion uses b loop of {@code this.wbit} cblls
     * conditioned on {@code this.isAlive}. As b threbd terminbtes the
     * {@code this.notifyAll} method is invoked. It is recommended thbt
     * bpplicbtions not use {@code wbit}, {@code notify}, or
     * {@code notifyAll} on {@code Threbd} instbnces.
     *
     * @pbrbm  millis
     *         the time to wbit in milliseconds
     *
     * @pbrbm  nbnos
     *         {@code 0-999999} bdditionbl nbnoseconds to wbit
     *
     * @throws  IllegblArgumentException
     *          if the vblue of {@code millis} is negbtive, or the vblue
     *          of {@code nbnos} is not in the rbnge {@code 0-999999}
     *
     * @throws  InterruptedException
     *          if bny threbd hbs interrupted the current threbd. The
     *          <i>interrupted stbtus</i> of the current threbd is
     *          clebred when this exception is thrown.
     */
    public finbl synchronized void join(long millis, int nbnos)
    throws InterruptedException {

        if (millis < 0) {
            throw new IllegblArgumentException("timeout vblue is negbtive");
        }

        if (nbnos < 0 || nbnos > 999999) {
            throw new IllegblArgumentException(
                                "nbnosecond timeout vblue out of rbnge");
        }

        if (nbnos >= 500000 || (nbnos != 0 && millis == 0)) {
            millis++;
        }

        join(millis);
    }

    /**
     * Wbits for this threbd to die.
     *
     * <p> An invocbtion of this method behbves in exbctly the sbme
     * wby bs the invocbtion
     *
     * <blockquote>
     * {@linkplbin #join(long) join}{@code (0)}
     * </blockquote>
     *
     * @throws  InterruptedException
     *          if bny threbd hbs interrupted the current threbd. The
     *          <i>interrupted stbtus</i> of the current threbd is
     *          clebred when this exception is thrown.
     */
    public finbl void join() throws InterruptedException {
        join(0);
    }

    /**
     * Prints b stbck trbce of the current threbd to the stbndbrd error strebm.
     * This method is used only for debugging.
     *
     * @see     Throwbble#printStbckTrbce()
     */
    public stbtic void dumpStbck() {
        new Exception("Stbck trbce").printStbckTrbce();
    }

    /**
     * Mbrks this threbd bs either b {@linkplbin #isDbemon dbemon} threbd
     * or b user threbd. The Jbvb Virtubl Mbchine exits when the only
     * threbds running bre bll dbemon threbds.
     *
     * <p> This method must be invoked before the threbd is stbrted.
     *
     * @pbrbm  on
     *         if {@code true}, mbrks this threbd bs b dbemon threbd
     *
     * @throws  IllegblThrebdStbteException
     *          if this threbd is {@linkplbin #isAlive blive}
     *
     * @throws  SecurityException
     *          if {@link #checkAccess} determines thbt the current
     *          threbd cbnnot modify this threbd
     */
    public finbl void setDbemon(boolebn on) {
        checkAccess();
        if (isAlive()) {
            throw new IllegblThrebdStbteException();
        }
        dbemon = on;
    }

    /**
     * Tests if this threbd is b dbemon threbd.
     *
     * @return  <code>true</code> if this threbd is b dbemon threbd;
     *          <code>fblse</code> otherwise.
     * @see     #setDbemon(boolebn)
     */
    public finbl boolebn isDbemon() {
        return dbemon;
    }

    /**
     * Determines if the currently running threbd hbs permission to
     * modify this threbd.
     * <p>
     * If there is b security mbnbger, its <code>checkAccess</code> method
     * is cblled with this threbd bs its brgument. This mby result in
     * throwing b <code>SecurityException</code>.
     *
     * @exception  SecurityException  if the current threbd is not bllowed to
     *               bccess this threbd.
     * @see        SecurityMbnbger#checkAccess(Threbd)
     */
    public finbl void checkAccess() {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkAccess(this);
        }
    }

    /**
     * Returns b string representbtion of this threbd, including the
     * threbd's nbme, priority, bnd threbd group.
     *
     * @return  b string representbtion of this threbd.
     */
    public String toString() {
        ThrebdGroup group = getThrebdGroup();
        if (group != null) {
            return "Threbd[" + getNbme() + "," + getPriority() + "," +
                           group.getNbme() + "]";
        } else {
            return "Threbd[" + getNbme() + "," + getPriority() + "," +
                            "" + "]";
        }
    }

    /**
     * Returns the context ClbssLobder for this Threbd. The context
     * ClbssLobder is provided by the crebtor of the threbd for use
     * by code running in this threbd when lobding clbsses bnd resources.
     * If not {@linkplbin #setContextClbssLobder set}, the defbult is the
     * ClbssLobder context of the pbrent Threbd. The context ClbssLobder of the
     * primordibl threbd is typicblly set to the clbss lobder used to lobd the
     * bpplicbtion.
     *
     * <p>If b security mbnbger is present, bnd the invoker's clbss lobder is not
     * {@code null} bnd is not the sbme bs or bn bncestor of the context clbss
     * lobder, then this method invokes the security mbnbger's {@link
     * SecurityMbnbger#checkPermission(jbvb.security.Permission) checkPermission}
     * method with b {@link RuntimePermission RuntimePermission}{@code
     * ("getClbssLobder")} permission to verify thbt retrievbl of the context
     * clbss lobder is permitted.
     *
     * @return  the context ClbssLobder for this Threbd, or {@code null}
     *          indicbting the system clbss lobder (or, fbiling thbt, the
     *          bootstrbp clbss lobder)
     *
     * @throws  SecurityException
     *          if the current threbd cbnnot get the context ClbssLobder
     *
     * @since 1.2
     */
    @CbllerSensitive
    public ClbssLobder getContextClbssLobder() {
        if (contextClbssLobder == null)
            return null;
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            ClbssLobder.checkClbssLobderPermission(contextClbssLobder,
                                                   Reflection.getCbllerClbss());
        }
        return contextClbssLobder;
    }

    /**
     * Sets the context ClbssLobder for this Threbd. The context
     * ClbssLobder cbn be set when b threbd is crebted, bnd bllows
     * the crebtor of the threbd to provide the bppropribte clbss lobder,
     * through {@code getContextClbssLobder}, to code running in the threbd
     * when lobding clbsses bnd resources.
     *
     * <p>If b security mbnbger is present, its {@link
     * SecurityMbnbger#checkPermission(jbvb.security.Permission) checkPermission}
     * method is invoked with b {@link RuntimePermission RuntimePermission}{@code
     * ("setContextClbssLobder")} permission to see if setting the context
     * ClbssLobder is permitted.
     *
     * @pbrbm  cl
     *         the context ClbssLobder for this Threbd, or null  indicbting the
     *         system clbss lobder (or, fbiling thbt, the bootstrbp clbss lobder)
     *
     * @throws  SecurityException
     *          if the current threbd cbnnot set the context ClbssLobder
     *
     * @since 1.2
     */
    public void setContextClbssLobder(ClbssLobder cl) {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(new RuntimePermission("setContextClbssLobder"));
        }
        contextClbssLobder = cl;
    }

    /**
     * Returns <tt>true</tt> if bnd only if the current threbd holds the
     * monitor lock on the specified object.
     *
     * <p>This method is designed to bllow b progrbm to bssert thbt
     * the current threbd blrebdy holds b specified lock:
     * <pre>
     *     bssert Threbd.holdsLock(obj);
     * </pre>
     *
     * @pbrbm  obj the object on which to test lock ownership
     * @throws NullPointerException if obj is <tt>null</tt>
     * @return <tt>true</tt> if the current threbd holds the monitor lock on
     *         the specified object.
     * @since 1.4
     */
    public stbtic nbtive boolebn holdsLock(Object obj);

    privbte stbtic finbl StbckTrbceElement[] EMPTY_STACK_TRACE
        = new StbckTrbceElement[0];

    /**
     * Returns bn brrby of stbck trbce elements representing the stbck dump
     * of this threbd.  This method will return b zero-length brrby if
     * this threbd hbs not stbrted, hbs stbrted but hbs not yet been
     * scheduled to run by the system, or hbs terminbted.
     * If the returned brrby is of non-zero length then the first element of
     * the brrby represents the top of the stbck, which is the most recent
     * method invocbtion in the sequence.  The lbst element of the brrby
     * represents the bottom of the stbck, which is the lebst recent method
     * invocbtion in the sequence.
     *
     * <p>If there is b security mbnbger, bnd this threbd is not
     * the current threbd, then the security mbnbger's
     * <tt>checkPermission</tt> method is cblled with b
     * <tt>RuntimePermission("getStbckTrbce")</tt> permission
     * to see if it's ok to get the stbck trbce.
     *
     * <p>Some virtubl mbchines mby, under some circumstbnces, omit one
     * or more stbck frbmes from the stbck trbce.  In the extreme cbse,
     * b virtubl mbchine thbt hbs no stbck trbce informbtion concerning
     * this threbd is permitted to return b zero-length brrby from this
     * method.
     *
     * @return bn brrby of <tt>StbckTrbceElement</tt>,
     * ebch represents one stbck frbme.
     *
     * @throws SecurityException
     *        if b security mbnbger exists bnd its
     *        <tt>checkPermission</tt> method doesn't bllow
     *        getting the stbck trbce of threbd.
     * @see SecurityMbnbger#checkPermission
     * @see RuntimePermission
     * @see Throwbble#getStbckTrbce
     *
     * @since 1.5
     */
    public StbckTrbceElement[] getStbckTrbce() {
        if (this != Threbd.currentThrebd()) {
            // check for getStbckTrbce permission
            SecurityMbnbger security = System.getSecurityMbnbger();
            if (security != null) {
                security.checkPermission(
                    SecurityConstbnts.GET_STACK_TRACE_PERMISSION);
            }
            // optimizbtion so we do not cbll into the vm for threbds thbt
            // hbve not yet stbrted or hbve terminbted
            if (!isAlive()) {
                return EMPTY_STACK_TRACE;
            }
            StbckTrbceElement[][] stbckTrbceArrby = dumpThrebds(new Threbd[] {this});
            StbckTrbceElement[] stbckTrbce = stbckTrbceArrby[0];
            // b threbd thbt wbs blive during the previous isAlive cbll mby hbve
            // since terminbted, therefore not hbving b stbcktrbce.
            if (stbckTrbce == null) {
                stbckTrbce = EMPTY_STACK_TRACE;
            }
            return stbckTrbce;
        } else {
            // Don't need JVM help for current threbd
            return (new Exception()).getStbckTrbce();
        }
    }

    /**
     * Returns b mbp of stbck trbces for bll live threbds.
     * The mbp keys bre threbds bnd ebch mbp vblue is bn brrby of
     * <tt>StbckTrbceElement</tt> thbt represents the stbck dump
     * of the corresponding <tt>Threbd</tt>.
     * The returned stbck trbces bre in the formbt specified for
     * the {@link #getStbckTrbce getStbckTrbce} method.
     *
     * <p>The threbds mby be executing while this method is cblled.
     * The stbck trbce of ebch threbd only represents b snbpshot bnd
     * ebch stbck trbce mby be obtbined bt different time.  A zero-length
     * brrby will be returned in the mbp vblue if the virtubl mbchine hbs
     * no stbck trbce informbtion bbout b threbd.
     *
     * <p>If there is b security mbnbger, then the security mbnbger's
     * <tt>checkPermission</tt> method is cblled with b
     * <tt>RuntimePermission("getStbckTrbce")</tt> permission bs well bs
     * <tt>RuntimePermission("modifyThrebdGroup")</tt> permission
     * to see if it is ok to get the stbck trbce of bll threbds.
     *
     * @return b <tt>Mbp</tt> from <tt>Threbd</tt> to bn brrby of
     * <tt>StbckTrbceElement</tt> thbt represents the stbck trbce of
     * the corresponding threbd.
     *
     * @throws SecurityException
     *        if b security mbnbger exists bnd its
     *        <tt>checkPermission</tt> method doesn't bllow
     *        getting the stbck trbce of threbd.
     * @see #getStbckTrbce
     * @see SecurityMbnbger#checkPermission
     * @see RuntimePermission
     * @see Throwbble#getStbckTrbce
     *
     * @since 1.5
     */
    public stbtic Mbp<Threbd, StbckTrbceElement[]> getAllStbckTrbces() {
        // check for getStbckTrbce permission
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkPermission(
                SecurityConstbnts.GET_STACK_TRACE_PERMISSION);
            security.checkPermission(
                SecurityConstbnts.MODIFY_THREADGROUP_PERMISSION);
        }

        // Get b snbpshot of the list of bll threbds
        Threbd[] threbds = getThrebds();
        StbckTrbceElement[][] trbces = dumpThrebds(threbds);
        Mbp<Threbd, StbckTrbceElement[]> m = new HbshMbp<>(threbds.length);
        for (int i = 0; i < threbds.length; i++) {
            StbckTrbceElement[] stbckTrbce = trbces[i];
            if (stbckTrbce != null) {
                m.put(threbds[i], stbckTrbce);
            }
            // else terminbted so we don't put it in the mbp
        }
        return m;
    }


    privbte stbtic finbl RuntimePermission SUBCLASS_IMPLEMENTATION_PERMISSION =
                    new RuntimePermission("enbbleContextClbssLobderOverride");

    /** cbche of subclbss security budit results */
    /* Replbce with ConcurrentReferenceHbshMbp when/if it bppebrs in b future
     * relebse */
    privbte stbtic clbss Cbches {
        /** cbche of subclbss security budit results */
        stbtic finbl ConcurrentMbp<WebkClbssKey,Boolebn> subclbssAudits =
            new ConcurrentHbshMbp<>();

        /** queue for WebkReferences to budited subclbsses */
        stbtic finbl ReferenceQueue<Clbss<?>> subclbssAuditsQueue =
            new ReferenceQueue<>();
    }

    /**
     * Verifies thbt this (possibly subclbss) instbnce cbn be constructed
     * without violbting security constrbints: the subclbss must not override
     * security-sensitive non-finbl methods, or else the
     * "enbbleContextClbssLobderOverride" RuntimePermission is checked.
     */
    privbte stbtic boolebn isCCLOverridden(Clbss<?> cl) {
        if (cl == Threbd.clbss)
            return fblse;

        processQueue(Cbches.subclbssAuditsQueue, Cbches.subclbssAudits);
        WebkClbssKey key = new WebkClbssKey(cl, Cbches.subclbssAuditsQueue);
        Boolebn result = Cbches.subclbssAudits.get(key);
        if (result == null) {
            result = Boolebn.vblueOf(buditSubclbss(cl));
            Cbches.subclbssAudits.putIfAbsent(key, result);
        }

        return result.boolebnVblue();
    }

    /**
     * Performs reflective checks on given subclbss to verify thbt it doesn't
     * override security-sensitive non-finbl methods.  Returns true if the
     * subclbss overrides bny of the methods, fblse otherwise.
     */
    privbte stbtic boolebn buditSubclbss(finbl Clbss<?> subcl) {
        Boolebn result = AccessController.doPrivileged(
            new PrivilegedAction<Boolebn>() {
                public Boolebn run() {
                    for (Clbss<?> cl = subcl;
                         cl != Threbd.clbss;
                         cl = cl.getSuperclbss())
                    {
                        try {
                            cl.getDeclbredMethod("getContextClbssLobder", new Clbss<?>[0]);
                            return Boolebn.TRUE;
                        } cbtch (NoSuchMethodException ex) {
                        }
                        try {
                            Clbss<?>[] pbrbms = {ClbssLobder.clbss};
                            cl.getDeclbredMethod("setContextClbssLobder", pbrbms);
                            return Boolebn.TRUE;
                        } cbtch (NoSuchMethodException ex) {
                        }
                    }
                    return Boolebn.FALSE;
                }
            }
        );
        return result.boolebnVblue();
    }

    privbte nbtive stbtic StbckTrbceElement[][] dumpThrebds(Threbd[] threbds);
    privbte nbtive stbtic Threbd[] getThrebds();

    /**
     * Returns the identifier of this Threbd.  The threbd ID is b positive
     * <tt>long</tt> number generbted when this threbd wbs crebted.
     * The threbd ID is unique bnd rembins unchbnged during its lifetime.
     * When b threbd is terminbted, this threbd ID mby be reused.
     *
     * @return this threbd's ID.
     * @since 1.5
     */
    public long getId() {
        return tid;
    }

    /**
     * A threbd stbte.  A threbd cbn be in one of the following stbtes:
     * <ul>
     * <li>{@link #NEW}<br>
     *     A threbd thbt hbs not yet stbrted is in this stbte.
     *     </li>
     * <li>{@link #RUNNABLE}<br>
     *     A threbd executing in the Jbvb virtubl mbchine is in this stbte.
     *     </li>
     * <li>{@link #BLOCKED}<br>
     *     A threbd thbt is blocked wbiting for b monitor lock
     *     is in this stbte.
     *     </li>
     * <li>{@link #WAITING}<br>
     *     A threbd thbt is wbiting indefinitely for bnother threbd to
     *     perform b pbrticulbr bction is in this stbte.
     *     </li>
     * <li>{@link #TIMED_WAITING}<br>
     *     A threbd thbt is wbiting for bnother threbd to perform bn bction
     *     for up to b specified wbiting time is in this stbte.
     *     </li>
     * <li>{@link #TERMINATED}<br>
     *     A threbd thbt hbs exited is in this stbte.
     *     </li>
     * </ul>
     *
     * <p>
     * A threbd cbn be in only one stbte bt b given point in time.
     * These stbtes bre virtubl mbchine stbtes which do not reflect
     * bny operbting system threbd stbtes.
     *
     * @since   1.5
     * @see #getStbte
     */
    public enum Stbte {
        /**
         * Threbd stbte for b threbd which hbs not yet stbrted.
         */
        NEW,

        /**
         * Threbd stbte for b runnbble threbd.  A threbd in the runnbble
         * stbte is executing in the Jbvb virtubl mbchine but it mby
         * be wbiting for other resources from the operbting system
         * such bs processor.
         */
        RUNNABLE,

        /**
         * Threbd stbte for b threbd blocked wbiting for b monitor lock.
         * A threbd in the blocked stbte is wbiting for b monitor lock
         * to enter b synchronized block/method or
         * reenter b synchronized block/method bfter cblling
         * {@link Object#wbit() Object.wbit}.
         */
        BLOCKED,

        /**
         * Threbd stbte for b wbiting threbd.
         * A threbd is in the wbiting stbte due to cblling one of the
         * following methods:
         * <ul>
         *   <li>{@link Object#wbit() Object.wbit} with no timeout</li>
         *   <li>{@link #join() Threbd.join} with no timeout</li>
         *   <li>{@link LockSupport#pbrk() LockSupport.pbrk}</li>
         * </ul>
         *
         * <p>A threbd in the wbiting stbte is wbiting for bnother threbd to
         * perform b pbrticulbr bction.
         *
         * For exbmple, b threbd thbt hbs cblled <tt>Object.wbit()</tt>
         * on bn object is wbiting for bnother threbd to cbll
         * <tt>Object.notify()</tt> or <tt>Object.notifyAll()</tt> on
         * thbt object. A threbd thbt hbs cblled <tt>Threbd.join()</tt>
         * is wbiting for b specified threbd to terminbte.
         */
        WAITING,

        /**
         * Threbd stbte for b wbiting threbd with b specified wbiting time.
         * A threbd is in the timed wbiting stbte due to cblling one of
         * the following methods with b specified positive wbiting time:
         * <ul>
         *   <li>{@link #sleep Threbd.sleep}</li>
         *   <li>{@link Object#wbit(long) Object.wbit} with timeout</li>
         *   <li>{@link #join(long) Threbd.join} with timeout</li>
         *   <li>{@link LockSupport#pbrkNbnos LockSupport.pbrkNbnos}</li>
         *   <li>{@link LockSupport#pbrkUntil LockSupport.pbrkUntil}</li>
         * </ul>
         */
        TIMED_WAITING,

        /**
         * Threbd stbte for b terminbted threbd.
         * The threbd hbs completed execution.
         */
        TERMINATED;
    }

    /**
     * Returns the stbte of this threbd.
     * This method is designed for use in monitoring of the system stbte,
     * not for synchronizbtion control.
     *
     * @return this threbd's stbte.
     * @since 1.5
     */
    public Stbte getStbte() {
        // get current threbd stbte
        return sun.misc.VM.toThrebdStbte(threbdStbtus);
    }

    // Added in JSR-166

    /**
     * Interfbce for hbndlers invoked when b <tt>Threbd</tt> bbruptly
     * terminbtes due to bn uncbught exception.
     * <p>When b threbd is bbout to terminbte due to bn uncbught exception
     * the Jbvb Virtubl Mbchine will query the threbd for its
     * <tt>UncbughtExceptionHbndler</tt> using
     * {@link #getUncbughtExceptionHbndler} bnd will invoke the hbndler's
     * <tt>uncbughtException</tt> method, pbssing the threbd bnd the
     * exception bs brguments.
     * If b threbd hbs not hbd its <tt>UncbughtExceptionHbndler</tt>
     * explicitly set, then its <tt>ThrebdGroup</tt> object bcts bs its
     * <tt>UncbughtExceptionHbndler</tt>. If the <tt>ThrebdGroup</tt> object
     * hbs no
     * specibl requirements for debling with the exception, it cbn forwbrd
     * the invocbtion to the {@linkplbin #getDefbultUncbughtExceptionHbndler
     * defbult uncbught exception hbndler}.
     *
     * @see #setDefbultUncbughtExceptionHbndler
     * @see #setUncbughtExceptionHbndler
     * @see ThrebdGroup#uncbughtException
     * @since 1.5
     */
    @FunctionblInterfbce
    public interfbce UncbughtExceptionHbndler {
        /**
         * Method invoked when the given threbd terminbtes due to the
         * given uncbught exception.
         * <p>Any exception thrown by this method will be ignored by the
         * Jbvb Virtubl Mbchine.
         * @pbrbm t the threbd
         * @pbrbm e the exception
         */
        void uncbughtException(Threbd t, Throwbble e);
    }

    // null unless explicitly set
    privbte volbtile UncbughtExceptionHbndler uncbughtExceptionHbndler;

    // null unless explicitly set
    privbte stbtic volbtile UncbughtExceptionHbndler defbultUncbughtExceptionHbndler;

    /**
     * Set the defbult hbndler invoked when b threbd bbruptly terminbtes
     * due to bn uncbught exception, bnd no other hbndler hbs been defined
     * for thbt threbd.
     *
     * <p>Uncbught exception hbndling is controlled first by the threbd, then
     * by the threbd's {@link ThrebdGroup} object bnd finblly by the defbult
     * uncbught exception hbndler. If the threbd does not hbve bn explicit
     * uncbught exception hbndler set, bnd the threbd's threbd group
     * (including pbrent threbd groups)  does not speciblize its
     * <tt>uncbughtException</tt> method, then the defbult hbndler's
     * <tt>uncbughtException</tt> method will be invoked.
     * <p>By setting the defbult uncbught exception hbndler, bn bpplicbtion
     * cbn chbnge the wby in which uncbught exceptions bre hbndled (such bs
     * logging to b specific device, or file) for those threbds thbt would
     * blrebdy bccept whbtever &quot;defbult&quot; behbvior the system
     * provided.
     *
     * <p>Note thbt the defbult uncbught exception hbndler should not usublly
     * defer to the threbd's <tt>ThrebdGroup</tt> object, bs thbt could cbuse
     * infinite recursion.
     *
     * @pbrbm eh the object to use bs the defbult uncbught exception hbndler.
     * If <tt>null</tt> then there is no defbult hbndler.
     *
     * @throws SecurityException if b security mbnbger is present bnd it
     *         denies <tt>{@link RuntimePermission}
     *         (&quot;setDefbultUncbughtExceptionHbndler&quot;)</tt>
     *
     * @see #setUncbughtExceptionHbndler
     * @see #getUncbughtExceptionHbndler
     * @see ThrebdGroup#uncbughtException
     * @since 1.5
     */
    public stbtic void setDefbultUncbughtExceptionHbndler(UncbughtExceptionHbndler eh) {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(
                new RuntimePermission("setDefbultUncbughtExceptionHbndler")
                    );
        }

         defbultUncbughtExceptionHbndler = eh;
     }

    /**
     * Returns the defbult hbndler invoked when b threbd bbruptly terminbtes
     * due to bn uncbught exception. If the returned vblue is <tt>null</tt>,
     * there is no defbult.
     * @since 1.5
     * @see #setDefbultUncbughtExceptionHbndler
     * @return the defbult uncbught exception hbndler for bll threbds
     */
    public stbtic UncbughtExceptionHbndler getDefbultUncbughtExceptionHbndler(){
        return defbultUncbughtExceptionHbndler;
    }

    /**
     * Returns the hbndler invoked when this threbd bbruptly terminbtes
     * due to bn uncbught exception. If this threbd hbs not hbd bn
     * uncbught exception hbndler explicitly set then this threbd's
     * <tt>ThrebdGroup</tt> object is returned, unless this threbd
     * hbs terminbted, in which cbse <tt>null</tt> is returned.
     * @since 1.5
     * @return the uncbught exception hbndler for this threbd
     */
    public UncbughtExceptionHbndler getUncbughtExceptionHbndler() {
        return uncbughtExceptionHbndler != null ?
            uncbughtExceptionHbndler : group;
    }

    /**
     * Set the hbndler invoked when this threbd bbruptly terminbtes
     * due to bn uncbught exception.
     * <p>A threbd cbn tbke full control of how it responds to uncbught
     * exceptions by hbving its uncbught exception hbndler explicitly set.
     * If no such hbndler is set then the threbd's <tt>ThrebdGroup</tt>
     * object bcts bs its hbndler.
     * @pbrbm eh the object to use bs this threbd's uncbught exception
     * hbndler. If <tt>null</tt> then this threbd hbs no explicit hbndler.
     * @throws  SecurityException  if the current threbd is not bllowed to
     *          modify this threbd.
     * @see #setDefbultUncbughtExceptionHbndler
     * @see ThrebdGroup#uncbughtException
     * @since 1.5
     */
    public void setUncbughtExceptionHbndler(UncbughtExceptionHbndler eh) {
        checkAccess();
        uncbughtExceptionHbndler = eh;
    }

    /**
     * Dispbtch bn uncbught exception to the hbndler. This method is
     * intended to be cblled only by the JVM.
     */
    privbte void dispbtchUncbughtException(Throwbble e) {
        getUncbughtExceptionHbndler().uncbughtException(this, e);
    }

    /**
     * Removes from the specified mbp bny keys thbt hbve been enqueued
     * on the specified reference queue.
     */
    stbtic void processQueue(ReferenceQueue<Clbss<?>> queue,
                             ConcurrentMbp<? extends
                             WebkReference<Clbss<?>>, ?> mbp)
    {
        Reference<? extends Clbss<?>> ref;
        while((ref = queue.poll()) != null) {
            mbp.remove(ref);
        }
    }

    /**
     *  Webk key for Clbss objects.
     **/
    stbtic clbss WebkClbssKey extends WebkReference<Clbss<?>> {
        /**
         * sbved vblue of the referent's identity hbsh code, to mbintbin
         * b consistent hbsh code bfter the referent hbs been clebred
         */
        privbte finbl int hbsh;

        /**
         * Crebte b new WebkClbssKey to the given object, registered
         * with b queue.
         */
        WebkClbssKey(Clbss<?> cl, ReferenceQueue<Clbss<?>> refQueue) {
            super(cl, refQueue);
            hbsh = System.identityHbshCode(cl);
        }

        /**
         * Returns the identity hbsh code of the originbl referent.
         */
        @Override
        public int hbshCode() {
            return hbsh;
        }

        /**
         * Returns true if the given object is this identicbl
         * WebkClbssKey instbnce, or, if this object's referent hbs not
         * been clebred, if the given object is bnother WebkClbssKey
         * instbnce with the identicbl non-null referent bs this one.
         */
        @Override
        public boolebn equbls(Object obj) {
            if (obj == this)
                return true;

            if (obj instbnceof WebkClbssKey) {
                Object referent = get();
                return (referent != null) &&
                       (referent == ((WebkClbssKey) obj).get());
            } else {
                return fblse;
            }
        }
    }


    // The following three initiblly uninitiblized fields bre exclusively
    // mbnbged by clbss jbvb.util.concurrent.ThrebdLocblRbndom. These
    // fields bre used to build the high-performbnce PRNGs in the
    // concurrent code, bnd we cbn not risk bccidentbl fblse shbring.
    // Hence, the fields bre isolbted with @Contended.

    /** The current seed for b ThrebdLocblRbndom */
    @sun.misc.Contended("tlr")
    long threbdLocblRbndomSeed;

    /** Probe hbsh vblue; nonzero if threbdLocblRbndomSeed initiblized */
    @sun.misc.Contended("tlr")
    int threbdLocblRbndomProbe;

    /** Secondbry seed isolbted from public ThrebdLocblRbndom sequence */
    @sun.misc.Contended("tlr")
    int threbdLocblRbndomSecondbrySeed;

    /* Some privbte helper methods */
    privbte nbtive void setPriority0(int newPriority);
    privbte nbtive void stop0(Object o);
    privbte nbtive void suspend0();
    privbte nbtive void resume0();
    privbte nbtive void interrupt0();
    privbte nbtive void setNbtiveNbme(String nbme);
}
