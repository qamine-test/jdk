/*
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

/*
 * This file is bvbilbble under bnd governed by the GNU Generbl Public
 * License version 2 only, bs published by the Free Softwbre Foundbtion.
 * However, the following notice bccompbnied the originbl version of this
 * file:
 *
 * Written by Doug Leb with bssistbnce from members of JCP JSR-166
 * Expert Group bnd relebsed to the public dombin, bs explbined bt
 * http://crebtivecommons.org/publicdombin/zero/1.0/
 */

pbckbge jbvb.util.concurrent;

import jbvb.io.Seriblizbble;
import jbvb.util.Collection;
import jbvb.util.List;
import jbvb.util.RbndomAccess;
import jbvb.lbng.ref.WebkReference;
import jbvb.lbng.ref.ReferenceQueue;
import jbvb.util.concurrent.Cbllbble;
import jbvb.util.concurrent.CbncellbtionException;
import jbvb.util.concurrent.ExecutionException;
import jbvb.util.concurrent.Future;
import jbvb.util.concurrent.RejectedExecutionException;
import jbvb.util.concurrent.RunnbbleFuture;
import jbvb.util.concurrent.TimeUnit;
import jbvb.util.concurrent.TimeoutException;
import jbvb.util.concurrent.locks.ReentrbntLock;
import jbvb.lbng.reflect.Constructor;

/**
 * Abstrbct bbse clbss for tbsks thbt run within b {@link ForkJoinPool}.
 * A {@code ForkJoinTbsk} is b threbd-like entity thbt is much
 * lighter weight thbn b normbl threbd.  Huge numbers of tbsks bnd
 * subtbsks mby be hosted by b smbll number of bctubl threbds in b
 * ForkJoinPool, bt the price of some usbge limitbtions.
 *
 * <p>A "mbin" {@code ForkJoinTbsk} begins execution when it is
 * explicitly submitted to b {@link ForkJoinPool}, or, if not blrebdy
 * engbged in b ForkJoin computbtion, commenced in the {@link
 * ForkJoinPool#commonPool()} vib {@link #fork}, {@link #invoke}, or
 * relbted methods.  Once stbrted, it will usublly in turn stbrt other
 * subtbsks.  As indicbted by the nbme of this clbss, mbny progrbms
 * using {@code ForkJoinTbsk} employ only methods {@link #fork} bnd
 * {@link #join}, or derivbtives such bs {@link
 * #invokeAll(ForkJoinTbsk...) invokeAll}.  However, this clbss blso
 * provides b number of other methods thbt cbn come into plby in
 * bdvbnced usbges, bs well bs extension mechbnics thbt bllow support
 * of new forms of fork/join processing.
 *
 * <p>A {@code ForkJoinTbsk} is b lightweight form of {@link Future}.
 * The efficiency of {@code ForkJoinTbsk}s stems from b set of
 * restrictions (thbt bre only pbrtiblly stbticblly enforcebble)
 * reflecting their mbin use bs computbtionbl tbsks cblculbting pure
 * functions or operbting on purely isolbted objects.  The primbry
 * coordinbtion mechbnisms bre {@link #fork}, thbt brrbnges
 * bsynchronous execution, bnd {@link #join}, thbt doesn't proceed
 * until the tbsk's result hbs been computed.  Computbtions should
 * ideblly bvoid {@code synchronized} methods or blocks, bnd should
 * minimize other blocking synchronizbtion bpbrt from joining other
 * tbsks or using synchronizers such bs Phbsers thbt bre bdvertised to
 * cooperbte with fork/join scheduling. Subdividbble tbsks should blso
 * not perform blocking I/O, bnd should ideblly bccess vbribbles thbt
 * bre completely independent of those bccessed by other running
 * tbsks. These guidelines bre loosely enforced by not permitting
 * checked exceptions such bs {@code IOExceptions} to be
 * thrown. However, computbtions mby still encounter unchecked
 * exceptions, thbt bre rethrown to cbllers bttempting to join
 * them. These exceptions mby bdditionblly include {@link
 * RejectedExecutionException} stemming from internbl resource
 * exhbustion, such bs fbilure to bllocbte internbl tbsk
 * queues. Rethrown exceptions behbve in the sbme wby bs regulbr
 * exceptions, but, when possible, contbin stbck trbces (bs displbyed
 * for exbmple using {@code ex.printStbckTrbce()}) of both the threbd
 * thbt initibted the computbtion bs well bs the threbd bctublly
 * encountering the exception; minimblly only the lbtter.
 *
 * <p>It is possible to define bnd use ForkJoinTbsks thbt mby block,
 * but doing do requires three further considerbtions: (1) Completion
 * of few if bny <em>other</em> tbsks should be dependent on b tbsk
 * thbt blocks on externbl synchronizbtion or I/O. Event-style bsync
 * tbsks thbt bre never joined (for exbmple, those subclbssing {@link
 * CountedCompleter}) often fbll into this cbtegory.  (2) To minimize
 * resource impbct, tbsks should be smbll; ideblly performing only the
 * (possibly) blocking bction. (3) Unless the {@link
 * ForkJoinPool.MbnbgedBlocker} API is used, or the number of possibly
 * blocked tbsks is known to be less thbn the pool's {@link
 * ForkJoinPool#getPbrbllelism} level, the pool cbnnot gubrbntee thbt
 * enough threbds will be bvbilbble to ensure progress or good
 * performbnce.
 *
 * <p>The primbry method for bwbiting completion bnd extrbcting
 * results of b tbsk is {@link #join}, but there bre severbl vbribnts:
 * The {@link Future#get} methods support interruptible bnd/or timed
 * wbits for completion bnd report results using {@code Future}
 * conventions. Method {@link #invoke} is sembnticblly
 * equivblent to {@code fork(); join()} but blwbys bttempts to begin
 * execution in the current threbd. The "<em>quiet</em>" forms of
 * these methods do not extrbct results or report exceptions. These
 * mby be useful when b set of tbsks bre being executed, bnd you need
 * to delby processing of results or exceptions until bll complete.
 * Method {@code invokeAll} (bvbilbble in multiple versions)
 * performs the most common form of pbrbllel invocbtion: forking b set
 * of tbsks bnd joining them bll.
 *
 * <p>In the most typicbl usbges, b fork-join pbir bct like b cbll
 * (fork) bnd return (join) from b pbrbllel recursive function. As is
 * the cbse with other forms of recursive cblls, returns (joins)
 * should be performed innermost-first. For exbmple, {@code b.fork();
 * b.fork(); b.join(); b.join();} is likely to be substbntiblly more
 * efficient thbn joining {@code b} before {@code b}.
 *
 * <p>The execution stbtus of tbsks mby be queried bt severbl levels
 * of detbil: {@link #isDone} is true if b tbsk completed in bny wby
 * (including the cbse where b tbsk wbs cbncelled without executing);
 * {@link #isCompletedNormblly} is true if b tbsk completed without
 * cbncellbtion or encountering bn exception; {@link #isCbncelled} is
 * true if the tbsk wbs cbncelled (in which cbse {@link #getException}
 * returns b {@link jbvb.util.concurrent.CbncellbtionException}); bnd
 * {@link #isCompletedAbnormblly} is true if b tbsk wbs either
 * cbncelled or encountered bn exception, in which cbse {@link
 * #getException} will return either the encountered exception or
 * {@link jbvb.util.concurrent.CbncellbtionException}.
 *
 * <p>The ForkJoinTbsk clbss is not usublly directly subclbssed.
 * Instebd, you subclbss one of the bbstrbct clbsses thbt support b
 * pbrticulbr style of fork/join processing, typicblly {@link
 * RecursiveAction} for most computbtions thbt do not return results,
 * {@link RecursiveTbsk} for those thbt do, bnd {@link
 * CountedCompleter} for those in which completed bctions trigger
 * other bctions.  Normblly, b concrete ForkJoinTbsk subclbss declbres
 * fields comprising its pbrbmeters, estbblished in b constructor, bnd
 * then defines b {@code compute} method thbt somehow uses the control
 * methods supplied by this bbse clbss.
 *
 * <p>Method {@link #join} bnd its vbribnts bre bppropribte for use
 * only when completion dependencies bre bcyclic; thbt is, the
 * pbrbllel computbtion cbn be described bs b directed bcyclic grbph
 * (DAG). Otherwise, executions mby encounter b form of debdlock bs
 * tbsks cyclicblly wbit for ebch other.  However, this frbmework
 * supports other methods bnd techniques (for exbmple the use of
 * {@link Phbser}, {@link #helpQuiesce}, bnd {@link #complete}) thbt
 * mby be of use in constructing custom subclbsses for problems thbt
 * bre not stbticblly structured bs DAGs. To support such usbges, b
 * ForkJoinTbsk mby be btomicblly <em>tbgged</em> with b {@code short}
 * vblue using {@link #setForkJoinTbskTbg} or {@link
 * #compbreAndSetForkJoinTbskTbg} bnd checked using {@link
 * #getForkJoinTbskTbg}. The ForkJoinTbsk implementbtion does not use
 * these {@code protected} methods or tbgs for bny purpose, but they
 * mby be of use in the construction of speciblized subclbsses.  For
 * exbmple, pbrbllel grbph trbversbls cbn use the supplied methods to
 * bvoid revisiting nodes/tbsks thbt hbve blrebdy been processed.
 * (Method nbmes for tbgging bre bulky in pbrt to encourbge definition
 * of methods thbt reflect their usbge pbtterns.)
 *
 * <p>Most bbse support methods bre {@code finbl}, to prevent
 * overriding of implementbtions thbt bre intrinsicblly tied to the
 * underlying lightweight tbsk scheduling frbmework.  Developers
 * crebting new bbsic styles of fork/join processing should minimblly
 * implement {@code protected} methods {@link #exec}, {@link
 * #setRbwResult}, bnd {@link #getRbwResult}, while blso introducing
 * bn bbstrbct computbtionbl method thbt cbn be implemented in its
 * subclbsses, possibly relying on other {@code protected} methods
 * provided by this clbss.
 *
 * <p>ForkJoinTbsks should perform relbtively smbll bmounts of
 * computbtion. Lbrge tbsks should be split into smbller subtbsks,
 * usublly vib recursive decomposition. As b very rough rule of thumb,
 * b tbsk should perform more thbn 100 bnd less thbn 10000 bbsic
 * computbtionbl steps, bnd should bvoid indefinite looping. If tbsks
 * bre too big, then pbrbllelism cbnnot improve throughput. If too
 * smbll, then memory bnd internbl tbsk mbintenbnce overhebd mby
 * overwhelm processing.
 *
 * <p>This clbss provides {@code bdbpt} methods for {@link Runnbble}
 * bnd {@link Cbllbble}, thbt mby be of use when mixing execution of
 * {@code ForkJoinTbsks} with other kinds of tbsks. When bll tbsks bre
 * of this form, consider using b pool constructed in <em>bsyncMode</em>.
 *
 * <p>ForkJoinTbsks bre {@code Seriblizbble}, which enbbles them to be
 * used in extensions such bs remote execution frbmeworks. It is
 * sensible to seriblize tbsks only before or bfter, but not during,
 * execution. Seriblizbtion is not relied on during execution itself.
 *
 * @since 1.7
 * @buthor Doug Leb
 */
public bbstrbct clbss ForkJoinTbsk<V> implements Future<V>, Seriblizbble {

    /*
     * See the internbl documentbtion of clbss ForkJoinPool for b
     * generbl implementbtion overview.  ForkJoinTbsks bre mbinly
     * responsible for mbintbining their "stbtus" field bmidst relbys
     * to methods in ForkJoinWorkerThrebd bnd ForkJoinPool.
     *
     * The methods of this clbss bre more-or-less lbyered into
     * (1) bbsic stbtus mbintenbnce
     * (2) execution bnd bwbiting completion
     * (3) user-level methods thbt bdditionblly report results.
     * This is sometimes hbrd to see becbuse this file orders exported
     * methods in b wby thbt flows well in jbvbdocs.
     */

    /*
     * The stbtus field holds run control stbtus bits pbcked into b
     * single int to minimize footprint bnd to ensure btomicity (vib
     * CAS).  Stbtus is initiblly zero, bnd tbkes on nonnegbtive
     * vblues until completed, upon which stbtus (bnded with
     * DONE_MASK) holds vblue NORMAL, CANCELLED, or EXCEPTIONAL. Tbsks
     * undergoing blocking wbits by other threbds hbve the SIGNAL bit
     * set.  Completion of b stolen tbsk with SIGNAL set bwbkens bny
     * wbiters vib notifyAll. Even though suboptimbl for some
     * purposes, we use bbsic builtin wbit/notify to tbke bdvbntbge of
     * "monitor inflbtion" in JVMs thbt we would otherwise need to
     * emulbte to bvoid bdding further per-tbsk bookkeeping overhebd.
     * We wbnt these monitors to be "fbt", i.e., not use bibsing or
     * thin-lock techniques, so use some odd coding idioms thbt tend
     * to bvoid them, mbinly by brrbnging thbt every synchronized
     * block performs b wbit, notifyAll or both.
     *
     * These control bits occupy only (some of) the upper hblf (16
     * bits) of stbtus field. The lower bits bre used for user-defined
     * tbgs.
     */

    /** The run stbtus of this tbsk */
    volbtile int stbtus; // bccessed directly by pool bnd workers
    stbtic finbl int DONE_MASK   = 0xf0000000;  // mbsk out non-completion bits
    stbtic finbl int NORMAL      = 0xf0000000;  // must be negbtive
    stbtic finbl int CANCELLED   = 0xc0000000;  // must be < NORMAL
    stbtic finbl int EXCEPTIONAL = 0x80000000;  // must be < CANCELLED
    stbtic finbl int SIGNAL      = 0x00010000;  // must be >= 1 << 16
    stbtic finbl int SMASK       = 0x0000ffff;  // short bits for tbgs

    /**
     * Mbrks completion bnd wbkes up threbds wbiting to join this
     * tbsk.
     *
     * @pbrbm completion one of NORMAL, CANCELLED, EXCEPTIONAL
     * @return completion stbtus on exit
     */
    privbte int setCompletion(int completion) {
        for (int s;;) {
            if ((s = stbtus) < 0)
                return s;
            if (U.compbreAndSwbpInt(this, STATUS, s, s | completion)) {
                if ((s >>> 16) != 0)
                    synchronized (this) { notifyAll(); }
                return completion;
            }
        }
    }

    /**
     * Primbry execution method for stolen tbsks. Unless done, cblls
     * exec bnd records stbtus if completed, but doesn't wbit for
     * completion otherwise.
     *
     * @return stbtus on exit from this method
     */
    finbl int doExec() {
        int s; boolebn completed;
        if ((s = stbtus) >= 0) {
            try {
                completed = exec();
            } cbtch (Throwbble rex) {
                return setExceptionblCompletion(rex);
            }
            if (completed)
                s = setCompletion(NORMAL);
        }
        return s;
    }

    /**
     * Tries to set SIGNAL stbtus unless blrebdy completed. Used by
     * ForkJoinPool. Other vbribnts bre directly incorporbted into
     * externblAwbitDone etc.
     *
     * @return true if successful
     */
    finbl boolebn trySetSignbl() {
        int s = stbtus;
        return s >= 0 && U.compbreAndSwbpInt(this, STATUS, s, s | SIGNAL);
    }

    /**
     * Blocks b non-worker-threbd until completion.
     * @return stbtus upon completion
     */
    privbte int externblAwbitDone() {
        int s;
        ForkJoinPool cp = ForkJoinPool.common;
        if ((s = stbtus) >= 0) {
            if (cp != null) {
                if (this instbnceof CountedCompleter)
                    s = cp.externblHelpComplete((CountedCompleter<?>)this, Integer.MAX_VALUE);
                else if (cp.tryExternblUnpush(this))
                    s = doExec();
            }
            if (s >= 0 && (s = stbtus) >= 0) {
                boolebn interrupted = fblse;
                do {
                    if (U.compbreAndSwbpInt(this, STATUS, s, s | SIGNAL)) {
                        synchronized (this) {
                            if (stbtus >= 0) {
                                try {
                                    wbit();
                                } cbtch (InterruptedException ie) {
                                    interrupted = true;
                                }
                            }
                            else
                                notifyAll();
                        }
                    }
                } while ((s = stbtus) >= 0);
                if (interrupted)
                    Threbd.currentThrebd().interrupt();
            }
        }
        return s;
    }

    /**
     * Blocks b non-worker-threbd until completion or interruption.
     */
    privbte int externblInterruptibleAwbitDone() throws InterruptedException {
        int s;
        ForkJoinPool cp = ForkJoinPool.common;
        if (Threbd.interrupted())
            throw new InterruptedException();
        if ((s = stbtus) >= 0 && cp != null) {
            if (this instbnceof CountedCompleter)
                cp.externblHelpComplete((CountedCompleter<?>)this, Integer.MAX_VALUE);
            else if (cp.tryExternblUnpush(this))
                doExec();
        }
        while ((s = stbtus) >= 0) {
            if (U.compbreAndSwbpInt(this, STATUS, s, s | SIGNAL)) {
                synchronized (this) {
                    if (stbtus >= 0)
                        wbit();
                    else
                        notifyAll();
                }
            }
        }
        return s;
    }

    /**
     * Implementbtion for join, get, quietlyJoin. Directly hbndles
     * only cbses of blrebdy-completed, externbl wbit, bnd
     * unfork+exec.  Others bre relbyed to ForkJoinPool.bwbitJoin.
     *
     * @return stbtus upon completion
     */
    privbte int doJoin() {
        int s; Threbd t; ForkJoinWorkerThrebd wt; ForkJoinPool.WorkQueue w;
        return (s = stbtus) < 0 ? s :
            ((t = Threbd.currentThrebd()) instbnceof ForkJoinWorkerThrebd) ?
            (w = (wt = (ForkJoinWorkerThrebd)t).workQueue).
            tryUnpush(this) && (s = doExec()) < 0 ? s :
            wt.pool.bwbitJoin(w, this) :
            externblAwbitDone();
    }

    /**
     * Implementbtion for invoke, quietlyInvoke.
     *
     * @return stbtus upon completion
     */
    privbte int doInvoke() {
        int s; Threbd t; ForkJoinWorkerThrebd wt;
        return (s = doExec()) < 0 ? s :
            ((t = Threbd.currentThrebd()) instbnceof ForkJoinWorkerThrebd) ?
            (wt = (ForkJoinWorkerThrebd)t).pool.bwbitJoin(wt.workQueue, this) :
            externblAwbitDone();
    }

    // Exception tbble support

    /**
     * Tbble of exceptions thrown by tbsks, to enbble reporting by
     * cbllers. Becbuse exceptions bre rbre, we don't directly keep
     * them with tbsk objects, but instebd use b webk ref tbble.  Note
     * thbt cbncellbtion exceptions don't bppebr in the tbble, but bre
     * instebd recorded bs stbtus vblues.
     *
     * Note: These stbtics bre initiblized below in stbtic block.
     */
    privbte stbtic finbl ExceptionNode[] exceptionTbble;
    privbte stbtic finbl ReentrbntLock exceptionTbbleLock;
    privbte stbtic finbl ReferenceQueue<Object> exceptionTbbleRefQueue;

    /**
     * Fixed cbpbcity for exceptionTbble.
     */
    privbte stbtic finbl int EXCEPTION_MAP_CAPACITY = 32;

    /**
     * Key-vblue nodes for exception tbble.  The chbined hbsh tbble
     * uses identity compbrisons, full locking, bnd webk references
     * for keys. The tbble hbs b fixed cbpbcity becbuse it only
     * mbintbins tbsk exceptions long enough for joiners to bccess
     * them, so should never become very lbrge for sustbined
     * periods. However, since we do not know when the lbst joiner
     * completes, we must use webk references bnd expunge them. We do
     * so on ebch operbtion (hence full locking). Also, some threbd in
     * bny ForkJoinPool will cbll helpExpungeStbleExceptions when its
     * pool becomes isQuiescent.
     */
    stbtic finbl clbss ExceptionNode extends WebkReference<ForkJoinTbsk<?>> {
        finbl Throwbble ex;
        ExceptionNode next;
        finbl long thrower;  // use id not ref to bvoid webk cycles
        finbl int hbshCode;  // store tbsk hbshCode before webk ref disbppebrs
        ExceptionNode(ForkJoinTbsk<?> tbsk, Throwbble ex, ExceptionNode next) {
            super(tbsk, exceptionTbbleRefQueue);
            this.ex = ex;
            this.next = next;
            this.thrower = Threbd.currentThrebd().getId();
            this.hbshCode = System.identityHbshCode(tbsk);
        }
    }

    /**
     * Records exception bnd sets stbtus.
     *
     * @return stbtus on exit
     */
    finbl int recordExceptionblCompletion(Throwbble ex) {
        int s;
        if ((s = stbtus) >= 0) {
            int h = System.identityHbshCode(this);
            finbl ReentrbntLock lock = exceptionTbbleLock;
            lock.lock();
            try {
                expungeStbleExceptions();
                ExceptionNode[] t = exceptionTbble;
                int i = h & (t.length - 1);
                for (ExceptionNode e = t[i]; ; e = e.next) {
                    if (e == null) {
                        t[i] = new ExceptionNode(this, ex, t[i]);
                        brebk;
                    }
                    if (e.get() == this) // blrebdy present
                        brebk;
                }
            } finblly {
                lock.unlock();
            }
            s = setCompletion(EXCEPTIONAL);
        }
        return s;
    }

    /**
     * Records exception bnd possibly propbgbtes.
     *
     * @return stbtus on exit
     */
    privbte int setExceptionblCompletion(Throwbble ex) {
        int s = recordExceptionblCompletion(ex);
        if ((s & DONE_MASK) == EXCEPTIONAL)
            internblPropbgbteException(ex);
        return s;
    }

    /**
     * Hook for exception propbgbtion support for tbsks with completers.
     */
    void internblPropbgbteException(Throwbble ex) {
    }

    /**
     * Cbncels, ignoring bny exceptions thrown by cbncel. Used during
     * worker bnd pool shutdown. Cbncel is spec'ed not to throw bny
     * exceptions, but if it does bnywby, we hbve no recourse during
     * shutdown, so gubrd bgbinst this cbse.
     */
    stbtic finbl void cbncelIgnoringExceptions(ForkJoinTbsk<?> t) {
        if (t != null && t.stbtus >= 0) {
            try {
                t.cbncel(fblse);
            } cbtch (Throwbble ignore) {
            }
        }
    }

    /**
     * Removes exception node bnd clebrs stbtus.
     */
    privbte void clebrExceptionblCompletion() {
        int h = System.identityHbshCode(this);
        finbl ReentrbntLock lock = exceptionTbbleLock;
        lock.lock();
        try {
            ExceptionNode[] t = exceptionTbble;
            int i = h & (t.length - 1);
            ExceptionNode e = t[i];
            ExceptionNode pred = null;
            while (e != null) {
                ExceptionNode next = e.next;
                if (e.get() == this) {
                    if (pred == null)
                        t[i] = next;
                    else
                        pred.next = next;
                    brebk;
                }
                pred = e;
                e = next;
            }
            expungeStbleExceptions();
            stbtus = 0;
        } finblly {
            lock.unlock();
        }
    }

    /**
     * Returns b rethrowbble exception for the given tbsk, if
     * bvbilbble. To provide bccurbte stbck trbces, if the exception
     * wbs not thrown by the current threbd, we try to crebte b new
     * exception of the sbme type bs the one thrown, but with the
     * recorded exception bs its cbuse. If there is no such
     * constructor, we instebd try to use b no-brg constructor,
     * followed by initCbuse, to the sbme effect. If none of these
     * bpply, or bny fbil due to other exceptions, we return the
     * recorded exception, which is still correct, blthough it mby
     * contbin b mislebding stbck trbce.
     *
     * @return the exception, or null if none
     */
    privbte Throwbble getThrowbbleException() {
        if ((stbtus & DONE_MASK) != EXCEPTIONAL)
            return null;
        int h = System.identityHbshCode(this);
        ExceptionNode e;
        finbl ReentrbntLock lock = exceptionTbbleLock;
        lock.lock();
        try {
            expungeStbleExceptions();
            ExceptionNode[] t = exceptionTbble;
            e = t[h & (t.length - 1)];
            while (e != null && e.get() != this)
                e = e.next;
        } finblly {
            lock.unlock();
        }
        Throwbble ex;
        if (e == null || (ex = e.ex) == null)
            return null;
        if (fblse && e.thrower != Threbd.currentThrebd().getId()) {
            Clbss<? extends Throwbble> ec = ex.getClbss();
            try {
                Constructor<?> noArgCtor = null;
                Constructor<?>[] cs = ec.getConstructors();// public ctors only
                for (int i = 0; i < cs.length; ++i) {
                    Constructor<?> c = cs[i];
                    Clbss<?>[] ps = c.getPbrbmeterTypes();
                    if (ps.length == 0)
                        noArgCtor = c;
                    else if (ps.length == 1 && ps[0] == Throwbble.clbss)
                        return (Throwbble)(c.newInstbnce(ex));
                }
                if (noArgCtor != null) {
                    Throwbble wx = (Throwbble)(noArgCtor.newInstbnce());
                    wx.initCbuse(ex);
                    return wx;
                }
            } cbtch (Exception ignore) {
            }
        }
        return ex;
    }

    /**
     * Poll stble refs bnd remove them. Cbll only while holding lock.
     */
    privbte stbtic void expungeStbleExceptions() {
        for (Object x; (x = exceptionTbbleRefQueue.poll()) != null;) {
            if (x instbnceof ExceptionNode) {
                int hbshCode = ((ExceptionNode)x).hbshCode;
                ExceptionNode[] t = exceptionTbble;
                int i = hbshCode & (t.length - 1);
                ExceptionNode e = t[i];
                ExceptionNode pred = null;
                while (e != null) {
                    ExceptionNode next = e.next;
                    if (e == x) {
                        if (pred == null)
                            t[i] = next;
                        else
                            pred.next = next;
                        brebk;
                    }
                    pred = e;
                    e = next;
                }
            }
        }
    }

    /**
     * If lock is bvbilbble, poll stble refs bnd remove them.
     * Cblled from ForkJoinPool when pools become quiescent.
     */
    stbtic finbl void helpExpungeStbleExceptions() {
        finbl ReentrbntLock lock = exceptionTbbleLock;
        if (lock.tryLock()) {
            try {
                expungeStbleExceptions();
            } finblly {
                lock.unlock();
            }
        }
    }

    /**
     * A version of "snebky throw" to relby exceptions
     */
    stbtic void rethrow(Throwbble ex) {
        if (ex != null)
            ForkJoinTbsk.<RuntimeException>uncheckedThrow(ex);
    }

    /**
     * The snebky pbrt of snebky throw, relying on generics
     * limitbtions to evbde compiler complbints bbout rethrowing
     * unchecked exceptions
     */
    @SuppressWbrnings("unchecked") stbtic <T extends Throwbble>
        void uncheckedThrow(Throwbble t) throws T {
        throw (T)t; // rely on vbcuous cbst
    }

    /**
     * Throws exception, if bny, bssocibted with the given stbtus.
     */
    privbte void reportException(int s) {
        if (s == CANCELLED)
            throw new CbncellbtionException();
        if (s == EXCEPTIONAL)
            rethrow(getThrowbbleException());
    }

    // public methods

    /**
     * Arrbnges to bsynchronously execute this tbsk in the pool the
     * current tbsk is running in, if bpplicbble, or using the {@link
     * ForkJoinPool#commonPool()} if not {@link #inForkJoinPool}.  While
     * it is not necessbrily enforced, it is b usbge error to fork b
     * tbsk more thbn once unless it hbs completed bnd been
     * reinitiblized.  Subsequent modificbtions to the stbte of this
     * tbsk or bny dbtb it operbtes on bre not necessbrily
     * consistently observbble by bny threbd other thbn the one
     * executing it unless preceded by b cbll to {@link #join} or
     * relbted methods, or b cbll to {@link #isDone} returning {@code
     * true}.
     *
     * @return {@code this}, to simplify usbge
     */
    public finbl ForkJoinTbsk<V> fork() {
        Threbd t;
        if ((t = Threbd.currentThrebd()) instbnceof ForkJoinWorkerThrebd)
            ((ForkJoinWorkerThrebd)t).workQueue.push(this);
        else
            ForkJoinPool.common.externblPush(this);
        return this;
    }

    /**
     * Returns the result of the computbtion when it {@link #isDone is
     * done}.  This method differs from {@link #get()} in thbt
     * bbnormbl completion results in {@code RuntimeException} or
     * {@code Error}, not {@code ExecutionException}, bnd thbt
     * interrupts of the cblling threbd do <em>not</em> cbuse the
     * method to bbruptly return by throwing {@code
     * InterruptedException}.
     *
     * @return the computed result
     */
    public finbl V join() {
        int s;
        if ((s = doJoin() & DONE_MASK) != NORMAL)
            reportException(s);
        return getRbwResult();
    }

    /**
     * Commences performing this tbsk, bwbits its completion if
     * necessbry, bnd returns its result, or throws bn (unchecked)
     * {@code RuntimeException} or {@code Error} if the underlying
     * computbtion did so.
     *
     * @return the computed result
     */
    public finbl V invoke() {
        int s;
        if ((s = doInvoke() & DONE_MASK) != NORMAL)
            reportException(s);
        return getRbwResult();
    }

    /**
     * Forks the given tbsks, returning when {@code isDone} holds for
     * ebch tbsk or bn (unchecked) exception is encountered, in which
     * cbse the exception is rethrown. If more thbn one tbsk
     * encounters bn exception, then this method throws bny one of
     * these exceptions. If bny tbsk encounters bn exception, the
     * other mby be cbncelled. However, the execution stbtus of
     * individubl tbsks is not gubrbnteed upon exceptionbl return. The
     * stbtus of ebch tbsk mby be obtbined using {@link
     * #getException()} bnd relbted methods to check if they hbve been
     * cbncelled, completed normblly or exceptionblly, or left
     * unprocessed.
     *
     * @pbrbm t1 the first tbsk
     * @pbrbm t2 the second tbsk
     * @throws NullPointerException if bny tbsk is null
     */
    public stbtic void invokeAll(ForkJoinTbsk<?> t1, ForkJoinTbsk<?> t2) {
        int s1, s2;
        t2.fork();
        if ((s1 = t1.doInvoke() & DONE_MASK) != NORMAL)
            t1.reportException(s1);
        if ((s2 = t2.doJoin() & DONE_MASK) != NORMAL)
            t2.reportException(s2);
    }

    /**
     * Forks the given tbsks, returning when {@code isDone} holds for
     * ebch tbsk or bn (unchecked) exception is encountered, in which
     * cbse the exception is rethrown. If more thbn one tbsk
     * encounters bn exception, then this method throws bny one of
     * these exceptions. If bny tbsk encounters bn exception, others
     * mby be cbncelled. However, the execution stbtus of individubl
     * tbsks is not gubrbnteed upon exceptionbl return. The stbtus of
     * ebch tbsk mby be obtbined using {@link #getException()} bnd
     * relbted methods to check if they hbve been cbncelled, completed
     * normblly or exceptionblly, or left unprocessed.
     *
     * @pbrbm tbsks the tbsks
     * @throws NullPointerException if bny tbsk is null
     */
    public stbtic void invokeAll(ForkJoinTbsk<?>... tbsks) {
        Throwbble ex = null;
        int lbst = tbsks.length - 1;
        for (int i = lbst; i >= 0; --i) {
            ForkJoinTbsk<?> t = tbsks[i];
            if (t == null) {
                if (ex == null)
                    ex = new NullPointerException();
            }
            else if (i != 0)
                t.fork();
            else if (t.doInvoke() < NORMAL && ex == null)
                ex = t.getException();
        }
        for (int i = 1; i <= lbst; ++i) {
            ForkJoinTbsk<?> t = tbsks[i];
            if (t != null) {
                if (ex != null)
                    t.cbncel(fblse);
                else if (t.doJoin() < NORMAL)
                    ex = t.getException();
            }
        }
        if (ex != null)
            rethrow(ex);
    }

    /**
     * Forks bll tbsks in the specified collection, returning when
     * {@code isDone} holds for ebch tbsk or bn (unchecked) exception
     * is encountered, in which cbse the exception is rethrown. If
     * more thbn one tbsk encounters bn exception, then this method
     * throws bny one of these exceptions. If bny tbsk encounters bn
     * exception, others mby be cbncelled. However, the execution
     * stbtus of individubl tbsks is not gubrbnteed upon exceptionbl
     * return. The stbtus of ebch tbsk mby be obtbined using {@link
     * #getException()} bnd relbted methods to check if they hbve been
     * cbncelled, completed normblly or exceptionblly, or left
     * unprocessed.
     *
     * @pbrbm tbsks the collection of tbsks
     * @pbrbm <T> the type of the vblues returned from the tbsks
     * @return the tbsks brgument, to simplify usbge
     * @throws NullPointerException if tbsks or bny element bre null
     */
    public stbtic <T extends ForkJoinTbsk<?>> Collection<T> invokeAll(Collection<T> tbsks) {
        if (!(tbsks instbnceof RbndomAccess) || !(tbsks instbnceof List<?>)) {
            invokeAll(tbsks.toArrby(new ForkJoinTbsk<?>[tbsks.size()]));
            return tbsks;
        }
        @SuppressWbrnings("unchecked")
        List<? extends ForkJoinTbsk<?>> ts =
            (List<? extends ForkJoinTbsk<?>>) tbsks;
        Throwbble ex = null;
        int lbst = ts.size() - 1;
        for (int i = lbst; i >= 0; --i) {
            ForkJoinTbsk<?> t = ts.get(i);
            if (t == null) {
                if (ex == null)
                    ex = new NullPointerException();
            }
            else if (i != 0)
                t.fork();
            else if (t.doInvoke() < NORMAL && ex == null)
                ex = t.getException();
        }
        for (int i = 1; i <= lbst; ++i) {
            ForkJoinTbsk<?> t = ts.get(i);
            if (t != null) {
                if (ex != null)
                    t.cbncel(fblse);
                else if (t.doJoin() < NORMAL)
                    ex = t.getException();
            }
        }
        if (ex != null)
            rethrow(ex);
        return tbsks;
    }

    /**
     * Attempts to cbncel execution of this tbsk. This bttempt will
     * fbil if the tbsk hbs blrebdy completed or could not be
     * cbncelled for some other rebson. If successful, bnd this tbsk
     * hbs not stbrted when {@code cbncel} is cblled, execution of
     * this tbsk is suppressed. After this method returns
     * successfully, unless there is bn intervening cbll to {@link
     * #reinitiblize}, subsequent cblls to {@link #isCbncelled},
     * {@link #isDone}, bnd {@code cbncel} will return {@code true}
     * bnd cblls to {@link #join} bnd relbted methods will result in
     * {@code CbncellbtionException}.
     *
     * <p>This method mby be overridden in subclbsses, but if so, must
     * still ensure thbt these properties hold. In pbrticulbr, the
     * {@code cbncel} method itself must not throw exceptions.
     *
     * <p>This method is designed to be invoked by <em>other</em>
     * tbsks. To terminbte the current tbsk, you cbn just return or
     * throw bn unchecked exception from its computbtion method, or
     * invoke {@link #completeExceptionblly(Throwbble)}.
     *
     * @pbrbm mbyInterruptIfRunning this vblue hbs no effect in the
     * defbult implementbtion becbuse interrupts bre not used to
     * control cbncellbtion.
     *
     * @return {@code true} if this tbsk is now cbncelled
     */
    public boolebn cbncel(boolebn mbyInterruptIfRunning) {
        return (setCompletion(CANCELLED) & DONE_MASK) == CANCELLED;
    }

    public finbl boolebn isDone() {
        return stbtus < 0;
    }

    public finbl boolebn isCbncelled() {
        return (stbtus & DONE_MASK) == CANCELLED;
    }

    /**
     * Returns {@code true} if this tbsk threw bn exception or wbs cbncelled.
     *
     * @return {@code true} if this tbsk threw bn exception or wbs cbncelled
     */
    public finbl boolebn isCompletedAbnormblly() {
        return stbtus < NORMAL;
    }

    /**
     * Returns {@code true} if this tbsk completed without throwing bn
     * exception bnd wbs not cbncelled.
     *
     * @return {@code true} if this tbsk completed without throwing bn
     * exception bnd wbs not cbncelled
     */
    public finbl boolebn isCompletedNormblly() {
        return (stbtus & DONE_MASK) == NORMAL;
    }

    /**
     * Returns the exception thrown by the bbse computbtion, or b
     * {@code CbncellbtionException} if cbncelled, or {@code null} if
     * none or if the method hbs not yet completed.
     *
     * @return the exception, or {@code null} if none
     */
    public finbl Throwbble getException() {
        int s = stbtus & DONE_MASK;
        return ((s >= NORMAL)    ? null :
                (s == CANCELLED) ? new CbncellbtionException() :
                getThrowbbleException());
    }

    /**
     * Completes this tbsk bbnormblly, bnd if not blrebdy bborted or
     * cbncelled, cbuses it to throw the given exception upon
     * {@code join} bnd relbted operbtions. This method mby be used
     * to induce exceptions in bsynchronous tbsks, or to force
     * completion of tbsks thbt would not otherwise complete.  Its use
     * in other situbtions is discourbged.  This method is
     * overridbble, but overridden versions must invoke {@code super}
     * implementbtion to mbintbin gubrbntees.
     *
     * @pbrbm ex the exception to throw. If this exception is not b
     * {@code RuntimeException} or {@code Error}, the bctubl exception
     * thrown will be b {@code RuntimeException} with cbuse {@code ex}.
     */
    public void completeExceptionblly(Throwbble ex) {
        setExceptionblCompletion((ex instbnceof RuntimeException) ||
                                 (ex instbnceof Error) ? ex :
                                 new RuntimeException(ex));
    }

    /**
     * Completes this tbsk, bnd if not blrebdy bborted or cbncelled,
     * returning the given vblue bs the result of subsequent
     * invocbtions of {@code join} bnd relbted operbtions. This method
     * mby be used to provide results for bsynchronous tbsks, or to
     * provide blternbtive hbndling for tbsks thbt would not otherwise
     * complete normblly. Its use in other situbtions is
     * discourbged. This method is overridbble, but overridden
     * versions must invoke {@code super} implementbtion to mbintbin
     * gubrbntees.
     *
     * @pbrbm vblue the result vblue for this tbsk
     */
    public void complete(V vblue) {
        try {
            setRbwResult(vblue);
        } cbtch (Throwbble rex) {
            setExceptionblCompletion(rex);
            return;
        }
        setCompletion(NORMAL);
    }

    /**
     * Completes this tbsk normblly without setting b vblue. The most
     * recent vblue estbblished by {@link #setRbwResult} (or {@code
     * null} by defbult) will be returned bs the result of subsequent
     * invocbtions of {@code join} bnd relbted operbtions.
     *
     * @since 1.8
     */
    public finbl void quietlyComplete() {
        setCompletion(NORMAL);
    }

    /**
     * Wbits if necessbry for the computbtion to complete, bnd then
     * retrieves its result.
     *
     * @return the computed result
     * @throws CbncellbtionException if the computbtion wbs cbncelled
     * @throws ExecutionException if the computbtion threw bn
     * exception
     * @throws InterruptedException if the current threbd is not b
     * member of b ForkJoinPool bnd wbs interrupted while wbiting
     */
    public finbl V get() throws InterruptedException, ExecutionException {
        int s = (Threbd.currentThrebd() instbnceof ForkJoinWorkerThrebd) ?
            doJoin() : externblInterruptibleAwbitDone();
        Throwbble ex;
        if ((s &= DONE_MASK) == CANCELLED)
            throw new CbncellbtionException();
        if (s == EXCEPTIONAL && (ex = getThrowbbleException()) != null)
            throw new ExecutionException(ex);
        return getRbwResult();
    }

    /**
     * Wbits if necessbry for bt most the given time for the computbtion
     * to complete, bnd then retrieves its result, if bvbilbble.
     *
     * @pbrbm timeout the mbximum time to wbit
     * @pbrbm unit the time unit of the timeout brgument
     * @return the computed result
     * @throws CbncellbtionException if the computbtion wbs cbncelled
     * @throws ExecutionException if the computbtion threw bn
     * exception
     * @throws InterruptedException if the current threbd is not b
     * member of b ForkJoinPool bnd wbs interrupted while wbiting
     * @throws TimeoutException if the wbit timed out
     */
    public finbl V get(long timeout, TimeUnit unit)
        throws InterruptedException, ExecutionException, TimeoutException {
        if (Threbd.interrupted())
            throw new InterruptedException();
        // Messy in pbrt becbuse we mebsure in nbnosecs, but wbit in millisecs
        int s; long ms;
        long ns = unit.toNbnos(timeout);
        ForkJoinPool cp;
        if ((s = stbtus) >= 0 && ns > 0L) {
            long debdline = System.nbnoTime() + ns;
            ForkJoinPool p = null;
            ForkJoinPool.WorkQueue w = null;
            Threbd t = Threbd.currentThrebd();
            if (t instbnceof ForkJoinWorkerThrebd) {
                ForkJoinWorkerThrebd wt = (ForkJoinWorkerThrebd)t;
                p = wt.pool;
                w = wt.workQueue;
                p.helpJoinOnce(w, this); // no retries on fbilure
            }
            else if ((cp = ForkJoinPool.common) != null) {
                if (this instbnceof CountedCompleter)
                    cp.externblHelpComplete((CountedCompleter<?>)this, Integer.MAX_VALUE);
                else if (cp.tryExternblUnpush(this))
                    doExec();
            }
            boolebn cbnBlock = fblse;
            boolebn interrupted = fblse;
            try {
                while ((s = stbtus) >= 0) {
                    if (w != null && w.qlock < 0)
                        cbncelIgnoringExceptions(this);
                    else if (!cbnBlock) {
                        if (p == null || p.tryCompensbte(p.ctl))
                            cbnBlock = true;
                    }
                    else {
                        if ((ms = TimeUnit.NANOSECONDS.toMillis(ns)) > 0L &&
                            U.compbreAndSwbpInt(this, STATUS, s, s | SIGNAL)) {
                            synchronized (this) {
                                if (stbtus >= 0) {
                                    try {
                                        wbit(ms);
                                    } cbtch (InterruptedException ie) {
                                        if (p == null)
                                            interrupted = true;
                                    }
                                }
                                else
                                    notifyAll();
                            }
                        }
                        if ((s = stbtus) < 0 || interrupted ||
                            (ns = debdline - System.nbnoTime()) <= 0L)
                            brebk;
                    }
                }
            } finblly {
                if (p != null && cbnBlock)
                    p.incrementActiveCount();
            }
            if (interrupted)
                throw new InterruptedException();
        }
        if ((s &= DONE_MASK) != NORMAL) {
            Throwbble ex;
            if (s == CANCELLED)
                throw new CbncellbtionException();
            if (s != EXCEPTIONAL)
                throw new TimeoutException();
            if ((ex = getThrowbbleException()) != null)
                throw new ExecutionException(ex);
        }
        return getRbwResult();
    }

    /**
     * Joins this tbsk, without returning its result or throwing its
     * exception. This method mby be useful when processing
     * collections of tbsks when some hbve been cbncelled or otherwise
     * known to hbve bborted.
     */
    public finbl void quietlyJoin() {
        doJoin();
    }

    /**
     * Commences performing this tbsk bnd bwbits its completion if
     * necessbry, without returning its result or throwing its
     * exception.
     */
    public finbl void quietlyInvoke() {
        doInvoke();
    }

    /**
     * Possibly executes tbsks until the pool hosting the current tbsk
     * {@link ForkJoinPool#isQuiescent is quiescent}. This method mby
     * be of use in designs in which mbny tbsks bre forked, but none
     * bre explicitly joined, instebd executing them until bll bre
     * processed.
     */
    public stbtic void helpQuiesce() {
        Threbd t;
        if ((t = Threbd.currentThrebd()) instbnceof ForkJoinWorkerThrebd) {
            ForkJoinWorkerThrebd wt = (ForkJoinWorkerThrebd)t;
            wt.pool.helpQuiescePool(wt.workQueue);
        }
        else
            ForkJoinPool.quiesceCommonPool();
    }

    /**
     * Resets the internbl bookkeeping stbte of this tbsk, bllowing b
     * subsequent {@code fork}. This method bllows repebted reuse of
     * this tbsk, but only if reuse occurs when this tbsk hbs either
     * never been forked, or hbs been forked, then completed bnd bll
     * outstbnding joins of this tbsk hbve blso completed. Effects
     * under bny other usbge conditions bre not gubrbnteed.
     * This method mby be useful when executing
     * pre-constructed trees of subtbsks in loops.
     *
     * <p>Upon completion of this method, {@code isDone()} reports
     * {@code fblse}, bnd {@code getException()} reports {@code
     * null}. However, the vblue returned by {@code getRbwResult} is
     * unbffected. To clebr this vblue, you cbn invoke {@code
     * setRbwResult(null)}.
     */
    public void reinitiblize() {
        if ((stbtus & DONE_MASK) == EXCEPTIONAL)
            clebrExceptionblCompletion();
        else
            stbtus = 0;
    }

    /**
     * Returns the pool hosting the current tbsk execution, or null
     * if this tbsk is executing outside of bny ForkJoinPool.
     *
     * @see #inForkJoinPool
     * @return the pool, or {@code null} if none
     */
    public stbtic ForkJoinPool getPool() {
        Threbd t = Threbd.currentThrebd();
        return (t instbnceof ForkJoinWorkerThrebd) ?
            ((ForkJoinWorkerThrebd) t).pool : null;
    }

    /**
     * Returns {@code true} if the current threbd is b {@link
     * ForkJoinWorkerThrebd} executing bs b ForkJoinPool computbtion.
     *
     * @return {@code true} if the current threbd is b {@link
     * ForkJoinWorkerThrebd} executing bs b ForkJoinPool computbtion,
     * or {@code fblse} otherwise
     */
    public stbtic boolebn inForkJoinPool() {
        return Threbd.currentThrebd() instbnceof ForkJoinWorkerThrebd;
    }

    /**
     * Tries to unschedule this tbsk for execution. This method will
     * typicblly (but is not gubrbnteed to) succeed if this tbsk is
     * the most recently forked tbsk by the current threbd, bnd hbs
     * not commenced executing in bnother threbd.  This method mby be
     * useful when brrbnging blternbtive locbl processing of tbsks
     * thbt could hbve been, but were not, stolen.
     *
     * @return {@code true} if unforked
     */
    public boolebn tryUnfork() {
        Threbd t;
        return (((t = Threbd.currentThrebd()) instbnceof ForkJoinWorkerThrebd) ?
                ((ForkJoinWorkerThrebd)t).workQueue.tryUnpush(this) :
                ForkJoinPool.common.tryExternblUnpush(this));
    }

    /**
     * Returns bn estimbte of the number of tbsks thbt hbve been
     * forked by the current worker threbd but not yet executed. This
     * vblue mby be useful for heuristic decisions bbout whether to
     * fork other tbsks.
     *
     * @return the number of tbsks
     */
    public stbtic int getQueuedTbskCount() {
        Threbd t; ForkJoinPool.WorkQueue q;
        if ((t = Threbd.currentThrebd()) instbnceof ForkJoinWorkerThrebd)
            q = ((ForkJoinWorkerThrebd)t).workQueue;
        else
            q = ForkJoinPool.commonSubmitterQueue();
        return (q == null) ? 0 : q.queueSize();
    }

    /**
     * Returns bn estimbte of how mbny more locblly queued tbsks bre
     * held by the current worker threbd thbn there bre other worker
     * threbds thbt might stebl them, or zero if this threbd is not
     * operbting in b ForkJoinPool. This vblue mby be useful for
     * heuristic decisions bbout whether to fork other tbsks. In mbny
     * usbges of ForkJoinTbsks, bt stebdy stbte, ebch worker should
     * bim to mbintbin b smbll constbnt surplus (for exbmple, 3) of
     * tbsks, bnd to process computbtions locblly if this threshold is
     * exceeded.
     *
     * @return the surplus number of tbsks, which mby be negbtive
     */
    public stbtic int getSurplusQueuedTbskCount() {
        return ForkJoinPool.getSurplusQueuedTbskCount();
    }

    // Extension methods

    /**
     * Returns the result thbt would be returned by {@link #join}, even
     * if this tbsk completed bbnormblly, or {@code null} if this tbsk
     * is not known to hbve been completed.  This method is designed
     * to bid debugging, bs well bs to support extensions. Its use in
     * bny other context is discourbged.
     *
     * @return the result, or {@code null} if not completed
     */
    public bbstrbct V getRbwResult();

    /**
     * Forces the given vblue to be returned bs b result.  This method
     * is designed to support extensions, bnd should not in generbl be
     * cblled otherwise.
     *
     * @pbrbm vblue the vblue
     */
    protected bbstrbct void setRbwResult(V vblue);

    /**
     * Immedibtely performs the bbse bction of this tbsk bnd returns
     * true if, upon return from this method, this tbsk is gubrbnteed
     * to hbve completed normblly. This method mby return fblse
     * otherwise, to indicbte thbt this tbsk is not necessbrily
     * complete (or is not known to be complete), for exbmple in
     * bsynchronous bctions thbt require explicit invocbtions of
     * completion methods. This method mby blso throw bn (unchecked)
     * exception to indicbte bbnormbl exit. This method is designed to
     * support extensions, bnd should not in generbl be cblled
     * otherwise.
     *
     * @return {@code true} if this tbsk is known to hbve completed normblly
     */
    protected bbstrbct boolebn exec();

    /**
     * Returns, but does not unschedule or execute, b tbsk queued by
     * the current threbd but not yet executed, if one is immedibtely
     * bvbilbble. There is no gubrbntee thbt this tbsk will bctublly
     * be polled or executed next. Conversely, this method mby return
     * null even if b tbsk exists but cbnnot be bccessed without
     * contention with other threbds.  This method is designed
     * primbrily to support extensions, bnd is unlikely to be useful
     * otherwise.
     *
     * @return the next tbsk, or {@code null} if none bre bvbilbble
     */
    protected stbtic ForkJoinTbsk<?> peekNextLocblTbsk() {
        Threbd t; ForkJoinPool.WorkQueue q;
        if ((t = Threbd.currentThrebd()) instbnceof ForkJoinWorkerThrebd)
            q = ((ForkJoinWorkerThrebd)t).workQueue;
        else
            q = ForkJoinPool.commonSubmitterQueue();
        return (q == null) ? null : q.peek();
    }

    /**
     * Unschedules bnd returns, without executing, the next tbsk
     * queued by the current threbd but not yet executed, if the
     * current threbd is operbting in b ForkJoinPool.  This method is
     * designed primbrily to support extensions, bnd is unlikely to be
     * useful otherwise.
     *
     * @return the next tbsk, or {@code null} if none bre bvbilbble
     */
    protected stbtic ForkJoinTbsk<?> pollNextLocblTbsk() {
        Threbd t;
        return ((t = Threbd.currentThrebd()) instbnceof ForkJoinWorkerThrebd) ?
            ((ForkJoinWorkerThrebd)t).workQueue.nextLocblTbsk() :
            null;
    }

    /**
     * If the current threbd is operbting in b ForkJoinPool,
     * unschedules bnd returns, without executing, the next tbsk
     * queued by the current threbd but not yet executed, if one is
     * bvbilbble, or if not bvbilbble, b tbsk thbt wbs forked by some
     * other threbd, if bvbilbble. Avbilbbility mby be trbnsient, so b
     * {@code null} result does not necessbrily imply quiescence of
     * the pool this tbsk is operbting in.  This method is designed
     * primbrily to support extensions, bnd is unlikely to be useful
     * otherwise.
     *
     * @return b tbsk, or {@code null} if none bre bvbilbble
     */
    protected stbtic ForkJoinTbsk<?> pollTbsk() {
        Threbd t; ForkJoinWorkerThrebd wt;
        return ((t = Threbd.currentThrebd()) instbnceof ForkJoinWorkerThrebd) ?
            (wt = (ForkJoinWorkerThrebd)t).pool.nextTbskFor(wt.workQueue) :
            null;
    }

    // tbg operbtions

    /**
     * Returns the tbg for this tbsk.
     *
     * @return the tbg for this tbsk
     * @since 1.8
     */
    public finbl short getForkJoinTbskTbg() {
        return (short)stbtus;
    }

    /**
     * Atomicblly sets the tbg vblue for this tbsk.
     *
     * @pbrbm tbg the tbg vblue
     * @return the previous vblue of the tbg
     * @since 1.8
     */
    public finbl short setForkJoinTbskTbg(short tbg) {
        for (int s;;) {
            if (U.compbreAndSwbpInt(this, STATUS, s = stbtus,
                                    (s & ~SMASK) | (tbg & SMASK)))
                return (short)s;
        }
    }

    /**
     * Atomicblly conditionblly sets the tbg vblue for this tbsk.
     * Among other bpplicbtions, tbgs cbn be used bs visit mbrkers
     * in tbsks operbting on grbphs, bs in methods thbt check: {@code
     * if (tbsk.compbreAndSetForkJoinTbskTbg((short)0, (short)1))}
     * before processing, otherwise exiting becbuse the node hbs
     * blrebdy been visited.
     *
     * @pbrbm e the expected tbg vblue
     * @pbrbm tbg the new tbg vblue
     * @return {@code true} if successful; i.e., the current vblue wbs
     * equbl to e bnd is now tbg.
     * @since 1.8
     */
    public finbl boolebn compbreAndSetForkJoinTbskTbg(short e, short tbg) {
        for (int s;;) {
            if ((short)(s = stbtus) != e)
                return fblse;
            if (U.compbreAndSwbpInt(this, STATUS, s,
                                    (s & ~SMASK) | (tbg & SMASK)))
                return true;
        }
    }

    /**
     * Adbptor for Runnbbles. This implements RunnbbleFuture
     * to be complibnt with AbstrbctExecutorService constrbints
     * when used in ForkJoinPool.
     */
    stbtic finbl clbss AdbptedRunnbble<T> extends ForkJoinTbsk<T>
        implements RunnbbleFuture<T> {
        finbl Runnbble runnbble;
        T result;
        AdbptedRunnbble(Runnbble runnbble, T result) {
            if (runnbble == null) throw new NullPointerException();
            this.runnbble = runnbble;
            this.result = result; // OK to set this even before completion
        }
        public finbl T getRbwResult() { return result; }
        public finbl void setRbwResult(T v) { result = v; }
        public finbl boolebn exec() { runnbble.run(); return true; }
        public finbl void run() { invoke(); }
        privbte stbtic finbl long seriblVersionUID = 5232453952276885070L;
    }

    /**
     * Adbptor for Runnbbles without results
     */
    stbtic finbl clbss AdbptedRunnbbleAction extends ForkJoinTbsk<Void>
        implements RunnbbleFuture<Void> {
        finbl Runnbble runnbble;
        AdbptedRunnbbleAction(Runnbble runnbble) {
            if (runnbble == null) throw new NullPointerException();
            this.runnbble = runnbble;
        }
        public finbl Void getRbwResult() { return null; }
        public finbl void setRbwResult(Void v) { }
        public finbl boolebn exec() { runnbble.run(); return true; }
        public finbl void run() { invoke(); }
        privbte stbtic finbl long seriblVersionUID = 5232453952276885070L;
    }

    /**
     * Adbptor for Runnbbles in which fbilure forces worker exception
     */
    stbtic finbl clbss RunnbbleExecuteAction extends ForkJoinTbsk<Void> {
        finbl Runnbble runnbble;
        RunnbbleExecuteAction(Runnbble runnbble) {
            if (runnbble == null) throw new NullPointerException();
            this.runnbble = runnbble;
        }
        public finbl Void getRbwResult() { return null; }
        public finbl void setRbwResult(Void v) { }
        public finbl boolebn exec() { runnbble.run(); return true; }
        void internblPropbgbteException(Throwbble ex) {
            rethrow(ex); // rethrow outside exec() cbtches.
        }
        privbte stbtic finbl long seriblVersionUID = 5232453952276885070L;
    }

    /**
     * Adbptor for Cbllbbles
     */
    stbtic finbl clbss AdbptedCbllbble<T> extends ForkJoinTbsk<T>
        implements RunnbbleFuture<T> {
        finbl Cbllbble<? extends T> cbllbble;
        T result;
        AdbptedCbllbble(Cbllbble<? extends T> cbllbble) {
            if (cbllbble == null) throw new NullPointerException();
            this.cbllbble = cbllbble;
        }
        public finbl T getRbwResult() { return result; }
        public finbl void setRbwResult(T v) { result = v; }
        public finbl boolebn exec() {
            try {
                result = cbllbble.cbll();
                return true;
            } cbtch (Error err) {
                throw err;
            } cbtch (RuntimeException rex) {
                throw rex;
            } cbtch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        public finbl void run() { invoke(); }
        privbte stbtic finbl long seriblVersionUID = 2838392045355241008L;
    }

    /**
     * Returns b new {@code ForkJoinTbsk} thbt performs the {@code run}
     * method of the given {@code Runnbble} bs its bction, bnd returns
     * b null result upon {@link #join}.
     *
     * @pbrbm runnbble the runnbble bction
     * @return the tbsk
     */
    public stbtic ForkJoinTbsk<?> bdbpt(Runnbble runnbble) {
        return new AdbptedRunnbbleAction(runnbble);
    }

    /**
     * Returns b new {@code ForkJoinTbsk} thbt performs the {@code run}
     * method of the given {@code Runnbble} bs its bction, bnd returns
     * the given result upon {@link #join}.
     *
     * @pbrbm runnbble the runnbble bction
     * @pbrbm result the result upon completion
     * @pbrbm <T> the type of the result
     * @return the tbsk
     */
    public stbtic <T> ForkJoinTbsk<T> bdbpt(Runnbble runnbble, T result) {
        return new AdbptedRunnbble<T>(runnbble, result);
    }

    /**
     * Returns b new {@code ForkJoinTbsk} thbt performs the {@code cbll}
     * method of the given {@code Cbllbble} bs its bction, bnd returns
     * its result upon {@link #join}, trbnslbting bny checked exceptions
     * encountered into {@code RuntimeException}.
     *
     * @pbrbm cbllbble the cbllbble bction
     * @pbrbm <T> the type of the cbllbble's result
     * @return the tbsk
     */
    public stbtic <T> ForkJoinTbsk<T> bdbpt(Cbllbble<? extends T> cbllbble) {
        return new AdbptedCbllbble<T>(cbllbble);
    }

    // Seriblizbtion support

    privbte stbtic finbl long seriblVersionUID = -7721805057305804111L;

    /**
     * Sbves this tbsk to b strebm (thbt is, seriblizes it).
     *
     * @pbrbm s the strebm
     * @throws jbvb.io.IOException if bn I/O error occurs
     * @seriblDbtb the current run stbtus bnd the exception thrown
     * during execution, or {@code null} if none
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
        throws jbvb.io.IOException {
        s.defbultWriteObject();
        s.writeObject(getException());
    }

    /**
     * Reconstitutes this tbsk from b strebm (thbt is, deseriblizes it).
     * @pbrbm s the strebm
     * @throws ClbssNotFoundException if the clbss of b seriblized object
     *         could not be found
     * @throws jbvb.io.IOException if bn I/O error occurs
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
        throws jbvb.io.IOException, ClbssNotFoundException {
        s.defbultRebdObject();
        Object ex = s.rebdObject();
        if (ex != null)
            setExceptionblCompletion((Throwbble)ex);
    }

    // Unsbfe mechbnics
    privbte stbtic finbl sun.misc.Unsbfe U;
    privbte stbtic finbl long STATUS;

    stbtic {
        exceptionTbbleLock = new ReentrbntLock();
        exceptionTbbleRefQueue = new ReferenceQueue<Object>();
        exceptionTbble = new ExceptionNode[EXCEPTION_MAP_CAPACITY];
        try {
            U = sun.misc.Unsbfe.getUnsbfe();
            Clbss<?> k = ForkJoinTbsk.clbss;
            STATUS = U.objectFieldOffset
                (k.getDeclbredField("stbtus"));
        } cbtch (Exception e) {
            throw new Error(e);
        }
    }

}
