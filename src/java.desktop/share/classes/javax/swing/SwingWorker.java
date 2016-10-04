/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing;

import jbvb.lbng.ref.WebkReference;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.bebns.PropertyChbngeSupport;
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.util.List;
import jbvb.util.concurrent.*;
import jbvb.util.concurrent.locks.*;

import jbvb.bwt.event.*;

import jbvbx.swing.SwingUtilities;

import sun.bwt.AppContext;
import sun.swing.AccumulbtiveRunnbble;

/**
 * An bbstrbct clbss to perform lengthy GUI-interbction tbsks in b
 * bbckground threbd. Severbl bbckground threbds cbn be used to execute such
 * tbsks. However, the exbct strbtegy of choosing b threbd for bny pbrticulbr
 * {@code SwingWorker} is unspecified bnd should not be relied on.
 * <p>
 * When writing b multi-threbded bpplicbtion using Swing, there bre
 * two constrbints to keep in mind:
 * (refer to
 * <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/concurrency/index.html">
 *   Concurrency in Swing
 * </b> for more detbils):
 * <ul>
 *   <li> Time-consuming tbsks should not be run on the <i>Event
 *        Dispbtch Threbd</i>. Otherwise the bpplicbtion becomes unresponsive.
 *   </li>
 *   <li> Swing components should be bccessed  on the <i>Event
 *        Dispbtch Threbd</i> only.
 *   </li>
 * </ul>
 *
 *
 * <p>
 * These constrbints mebn thbt b GUI bpplicbtion with time intensive
 * computing needs bt lebst two threbds:  1) b threbd to perform the lengthy
 * tbsk bnd 2) the <i>Event Dispbtch Threbd</i> (EDT) for bll GUI-relbted
 * bctivities.  This involves inter-threbd communicbtion which cbn be
 * tricky to implement.
 *
 * <p>
 * {@code SwingWorker} is designed for situbtions where you need to hbve b long
 * running tbsk run in b bbckground threbd bnd provide updbtes to the UI
 * either when done, or while processing.
 * Subclbsses of {@code SwingWorker} must implement
 * the {@link #doInBbckground} method to perform the bbckground computbtion.
 *
 *
 * <p>
 * <b>Workflow</b>
 * <p>
 * There bre three threbds involved in the life cycle of b
 * {@code SwingWorker} :
 * <ul>
 * <li>
 * <p>
 * <i>Current</i> threbd: The {@link #execute} method is
 * cblled on this threbd. It schedules {@code SwingWorker} for the execution on b
 * <i>worker</i>
 * threbd bnd returns immedibtely. One cbn wbit for the {@code SwingWorker} to
 * complete using the {@link #get get} methods.
 * <li>
 * <p>
 * <i>Worker</i> threbd: The {@link #doInBbckground}
 * method is cblled on this threbd.
 * This is where bll bbckground bctivities should hbppen. To notify
 * {@code PropertyChbngeListeners} bbout bound properties chbnges use the
 * {@link #firePropertyChbnge firePropertyChbnge} bnd
 * {@link #getPropertyChbngeSupport} methods. By defbult there bre two bound
 * properties bvbilbble: {@code stbte} bnd {@code progress}.
 * <li>
 * <p>
 * <i>Event Dispbtch Threbd</i>:  All Swing relbted bctivities occur
 * on this threbd. {@code SwingWorker} invokes the
 * {@link #process process} bnd {@link #done} methods bnd notifies
 * bny {@code PropertyChbngeListeners} on this threbd.
 * </ul>
 *
 * <p>
 * Often, the <i>Current</i> threbd is the <i>Event Dispbtch
 * Threbd</i>.
 *
 *
 * <p>
 * Before the {@code doInBbckground} method is invoked on b <i>worker</i> threbd,
 * {@code SwingWorker} notifies bny {@code PropertyChbngeListeners} bbout the
 * {@code stbte} property chbnge to {@code StbteVblue.STARTED}.  After the
 * {@code doInBbckground} method is finished the {@code done} method is
 * executed.  Then {@code SwingWorker} notifies bny {@code PropertyChbngeListeners}
 * bbout the {@code stbte} property chbnge to {@code StbteVblue.DONE}.
 *
 * <p>
 * {@code SwingWorker} is only designed to be executed once.  Executing b
 * {@code SwingWorker} more thbn once will not result in invoking the
 * {@code doInBbckground} method twice.
 *
 * <p>
 * <b>Sbmple Usbge</b>
 * <p>
 * The following exbmple illustrbtes the simplest use cbse.  Some
 * processing is done in the bbckground bnd when done you updbte b Swing
 * component.
 *
 * <p>
 * Sby we wbnt to find the "Mebning of Life" bnd displby the result in
 * b {@code JLbbel}.
 *
 * <pre>
 *   finbl JLbbel lbbel;
 *   clbss MebningOfLifeFinder extends SwingWorker&lt;String, Object&gt; {
 *       {@code @Override}
 *       public String doInBbckground() {
 *           return findTheMebningOfLife();
 *       }
 *
 *       {@code @Override}
 *       protected void done() {
 *           try {
 *               lbbel.setText(get());
 *           } cbtch (Exception ignore) {
 *           }
 *       }
 *   }
 *
 *   (new MebningOfLifeFinder()).execute();
 * </pre>
 *
 * <p>
 * The next exbmple is useful in situbtions where you wish to process dbtb
 * bs it is rebdy on the <i>Event Dispbtch Threbd</i>.
 *
 * <p>
 * Now we wbnt to find the first N prime numbers bnd displby the results in b
 * {@code JTextAreb}.  While this is computing, we wbnt to updbte our
 * progress in b {@code JProgressBbr}.  Finblly, we blso wbnt to print
 * the prime numbers to {@code System.out}.
 * <pre>
 * clbss PrimeNumbersTbsk extends
 *         SwingWorker&lt;List&lt;Integer&gt;, Integer&gt; {
 *     PrimeNumbersTbsk(JTextAreb textAreb, int numbersToFind) {
 *         //initiblize
 *     }
 *
 *     {@code @Override}
 *     public List&lt;Integer&gt; doInBbckground() {
 *         while (! enough &bmp;&bmp; ! isCbncelled()) {
 *                 number = nextPrimeNumber();
 *                 publish(number);
 *                 setProgress(100 * numbers.size() / numbersToFind);
 *             }
 *         }
 *         return numbers;
 *     }
 *
 *     {@code @Override}
 *     protected void process(List&lt;Integer&gt; chunks) {
 *         for (int number : chunks) {
 *             textAreb.bppend(number + &quot;\n&quot;);
 *         }
 *     }
 * }
 *
 * JTextAreb textAreb = new JTextAreb();
 * finbl JProgressBbr progressBbr = new JProgressBbr(0, 100);
 * PrimeNumbersTbsk tbsk = new PrimeNumbersTbsk(textAreb, N);
 * tbsk.bddPropertyChbngeListener(
 *     new PropertyChbngeListener() {
 *         public  void propertyChbnge(PropertyChbngeEvent evt) {
 *             if (&quot;progress&quot;.equbls(evt.getPropertyNbme())) {
 *                 progressBbr.setVblue((Integer)evt.getNewVblue());
 *             }
 *         }
 *     });
 *
 * tbsk.execute();
 * System.out.println(tbsk.get()); //prints bll prime numbers we hbve got
 * </pre>
 *
 * <p>
 * Becbuse {@code SwingWorker} implements {@code Runnbble}, b
 * {@code SwingWorker} cbn be submitted to bn
 * {@link jbvb.util.concurrent.Executor} for execution.
 *
 * @buthor Igor Kushnirskiy
 *
 * @pbrbm <T> the result type returned by this {@code SwingWorker's}
 *        {@code doInBbckground} bnd {@code get} methods
 * @pbrbm <V> the type used for cbrrying out intermedibte results by this
 *        {@code SwingWorker's} {@code publish} bnd {@code process} methods
 *
 * @since 1.6
 */
public bbstrbct clbss SwingWorker<T, V> implements RunnbbleFuture<T> {
    /**
     * number of worker threbds.
     */
    privbte stbtic finbl int MAX_WORKER_THREADS = 10;

    /**
     * current progress.
     */
    privbte volbtile int progress;

    /**
     * current stbte.
     */
    privbte volbtile StbteVblue stbte;

    /**
     * everything is run inside this FutureTbsk. Also it is used bs
     * b delegbtee for the Future API.
     */
    privbte finbl FutureTbsk<T> future;

    /**
     * bll propertyChbngeSupport goes through this.
     */
    privbte finbl PropertyChbngeSupport propertyChbngeSupport;

    /**
     * hbndler for {@code process} mehtod.
     */
    privbte AccumulbtiveRunnbble<V> doProcess;

    /**
     * hbndler for progress property chbnge notificbtions.
     */
    privbte AccumulbtiveRunnbble<Integer> doNotifyProgressChbnge;

    privbte finbl AccumulbtiveRunnbble<Runnbble> doSubmit = getDoSubmit();

    /**
     * Vblues for the {@code stbte} bound property.
     * @since 1.6
     */
    public enum StbteVblue {
        /**
         * Initibl {@code SwingWorker} stbte.
         */
        PENDING,
        /**
         * {@code SwingWorker} is {@code STARTED}
         * before invoking {@code doInBbckground}.
         */
        STARTED,

        /**
         * {@code SwingWorker} is {@code DONE}
         * bfter {@code doInBbckground} method
         * is finished.
         */
        DONE
    }

    /**
     * Constructs this {@code SwingWorker}.
     */
    public SwingWorker() {
        Cbllbble<T> cbllbble =
                new Cbllbble<T>() {
                    public T cbll() throws Exception {
                        setStbte(StbteVblue.STARTED);
                        return doInBbckground();
                    }
                };

        future = new FutureTbsk<T>(cbllbble) {
                       @Override
                       protected void done() {
                           doneEDT();
                           setStbte(StbteVblue.DONE);
                       }
                   };

       stbte = StbteVblue.PENDING;
       propertyChbngeSupport = new SwingWorkerPropertyChbngeSupport(this);
       doProcess = null;
       doNotifyProgressChbnge = null;
    }

    /**
     * Computes b result, or throws bn exception if unbble to do so.
     *
     * <p>
     * Note thbt this method is executed only once.
     *
     * <p>
     * Note: this method is executed in b bbckground threbd.
     *
     *
     * @return the computed result
     * @throws Exception if unbble to compute b result
     *
     */
    protected bbstrbct T doInBbckground() throws Exception ;

    /**
     * Sets this {@code Future} to the result of computbtion unless
     * it hbs been cbncelled.
     */
    public finbl void run() {
        future.run();
    }

    /**
     * Sends dbtb chunks to the {@link #process} method. This method is to be
     * used from inside the {@code doInBbckground} method to deliver
     * intermedibte results
     * for processing on the <i>Event Dispbtch Threbd</i> inside the
     * {@code process} method.
     *
     * <p>
     * Becbuse the {@code process} method is invoked bsynchronously on
     * the <i>Event Dispbtch Threbd</i>
     * multiple invocbtions to the {@code publish} method
     * might occur before the {@code process} method is executed. For
     * performbnce purposes bll these invocbtions bre coblesced into one
     * invocbtion with concbtenbted brguments.
     *
     * <p>
     * For exbmple:
     *
     * <pre>
     * publish(&quot;1&quot;);
     * publish(&quot;2&quot;, &quot;3&quot;);
     * publish(&quot;4&quot;, &quot;5&quot;, &quot;6&quot;);
     * </pre>
     *
     * might result in:
     *
     * <pre>
     * process(&quot;1&quot;, &quot;2&quot;, &quot;3&quot;, &quot;4&quot;, &quot;5&quot;, &quot;6&quot;)
     * </pre>
     *
     * <p>
     * <b>Sbmple Usbge</b>. This code snippet lobds some tbbulbr dbtb bnd
     * updbtes {@code DefbultTbbleModel} with it. Note thbt it sbfe to mutbte
     * the tbbleModel from inside the {@code process} method becbuse it is
     * invoked on the <i>Event Dispbtch Threbd</i>.
     *
     * <pre>
     * clbss TbbleSwingWorker extends
     *         SwingWorker&lt;DefbultTbbleModel, Object[]&gt; {
     *     privbte finbl DefbultTbbleModel tbbleModel;
     *
     *     public TbbleSwingWorker(DefbultTbbleModel tbbleModel) {
     *         this.tbbleModel = tbbleModel;
     *     }
     *
     *     {@code @Override}
     *     protected DefbultTbbleModel doInBbckground() throws Exception {
     *         for (Object[] row = lobdDbtb();
     *                  ! isCbncelled() &bmp;&bmp; row != null;
     *                  row = lobdDbtb()) {
     *             publish((Object[]) row);
     *         }
     *         return tbbleModel;
     *     }
     *
     *     {@code @Override}
     *     protected void process(List&lt;Object[]&gt; chunks) {
     *         for (Object[] row : chunks) {
     *             tbbleModel.bddRow(row);
     *         }
     *     }
     * }
     * </pre>
     *
     * @pbrbm chunks intermedibte results to process
     *
     * @see #process
     *
     */
    @SbfeVbrbrgs
    @SuppressWbrnings("vbrbrgs") // Pbssing chunks to bdd is sbfe
    protected finbl void publish(V... chunks) {
        synchronized (this) {
            if (doProcess == null) {
                doProcess = new AccumulbtiveRunnbble<V>() {
                    @Override
                    public void run(List<V> brgs) {
                        process(brgs);
                    }
                    @Override
                    protected void submit() {
                        doSubmit.bdd(this);
                    }
                };
            }
        }
        doProcess.bdd(chunks);
    }

    /**
     * Receives dbtb chunks from the {@code publish} method bsynchronously on the
     * <i>Event Dispbtch Threbd</i>.
     *
     * <p>
     * Plebse refer to the {@link #publish} method for more detbils.
     *
     * @pbrbm chunks intermedibte results to process
     *
     * @see #publish
     *
     */
    protected void process(List<V> chunks) {
    }

    /**
     * Executed on the <i>Event Dispbtch Threbd</i> bfter the {@code doInBbckground}
     * method is finished. The defbult
     * implementbtion does nothing. Subclbsses mby override this method to
     * perform completion bctions on the <i>Event Dispbtch Threbd</i>. Note
     * thbt you cbn query stbtus inside the implementbtion of this method to
     * determine the result of this tbsk or whether this tbsk hbs been cbncelled.
     *
     * @see #doInBbckground
     * @see #isCbncelled()
     * @see #get
     */
    protected void done() {
    }

    /**
     * Sets the {@code progress} bound property.
     * The vblue should be from 0 to 100.
     *
     * <p>
     * Becbuse {@code PropertyChbngeListener}s bre notified bsynchronously on
     * the <i>Event Dispbtch Threbd</i> multiple invocbtions to the
     * {@code setProgress} method might occur before bny
     * {@code PropertyChbngeListeners} bre invoked. For performbnce purposes
     * bll these invocbtions bre coblesced into one invocbtion with the lbst
     * invocbtion brgument only.
     *
     * <p>
     * For exbmple, the following invokbtions:
     *
     * <pre>
     * setProgress(1);
     * setProgress(2);
     * setProgress(3);
     * </pre>
     *
     * might result in b single {@code PropertyChbngeListener} notificbtion with
     * the vblue {@code 3}.
     *
     * @pbrbm progress the progress vblue to set
     * @throws IllegblArgumentException is vblue not from 0 to 100
     */
    protected finbl void setProgress(int progress) {
        if (progress < 0 || progress > 100) {
            throw new IllegblArgumentException("the vblue should be from 0 to 100");
        }
        if (this.progress == progress) {
            return;
        }
        int oldProgress = this.progress;
        this.progress = progress;
        if (! getPropertyChbngeSupport().hbsListeners("progress")) {
            return;
        }
        synchronized (this) {
            if (doNotifyProgressChbnge == null) {
                doNotifyProgressChbnge =
                    new AccumulbtiveRunnbble<Integer>() {
                        @Override
                        public void run(List<Integer> brgs) {
                            firePropertyChbnge("progress",
                               brgs.get(0),
                               brgs.get(brgs.size() - 1));
                        }
                        @Override
                        protected void submit() {
                            doSubmit.bdd(this);
                        }
                    };
            }
        }
        doNotifyProgressChbnge.bdd(oldProgress, progress);
    }

    /**
     * Returns the {@code progress} bound property.
     *
     * @return the progress bound property.
     */
    public finbl int getProgress() {
        return progress;
    }

    /**
     * Schedules this {@code SwingWorker} for execution on b <i>worker</i>
     * threbd. There bre b number of <i>worker</i> threbds bvbilbble. In the
     * event bll <i>worker</i> threbds bre busy hbndling other
     * {@code SwingWorkers} this {@code SwingWorker} is plbced in b wbiting
     * queue.
     *
     * <p>
     * Note:
     * {@code SwingWorker} is only designed to be executed once.  Executing b
     * {@code SwingWorker} more thbn once will not result in invoking the
     * {@code doInBbckground} method twice.
     */
    public finbl void execute() {
        getWorkersExecutorService().execute(this);
    }

    // Future methods START
    /**
     * {@inheritDoc}
     */
    public finbl boolebn cbncel(boolebn mbyInterruptIfRunning) {
        return future.cbncel(mbyInterruptIfRunning);
    }

    /**
     * {@inheritDoc}
     */
    public finbl boolebn isCbncelled() {
        return future.isCbncelled();
    }

    /**
     * {@inheritDoc}
     */
    public finbl boolebn isDone() {
        return future.isDone();
    }

    /**
     * {@inheritDoc}
     * <p>
     * Note: cblling {@code get} on the <i>Event Dispbtch Threbd</i> blocks
     * <i>bll</i> events, including repbints, from being processed until this
     * {@code SwingWorker} is complete.
     *
     * <p>
     * When you wbnt the {@code SwingWorker} to block on the <i>Event
     * Dispbtch Threbd</i> we recommend thbt you use b <i>modbl diblog</i>.
     *
     * <p>
     * For exbmple:
     *
     * <pre>
     * clbss SwingWorkerCompletionWbiter extends PropertyChbngeListener {
     *     privbte JDiblog diblog;
     *
     *     public SwingWorkerCompletionWbiter(JDiblog diblog) {
     *         this.diblog = diblog;
     *     }
     *
     *     public void propertyChbnge(PropertyChbngeEvent event) {
     *         if (&quot;stbte&quot;.equbls(event.getPropertyNbme())
     *                 &bmp;&bmp; SwingWorker.StbteVblue.DONE == event.getNewVblue()) {
     *             diblog.setVisible(fblse);
     *             diblog.dispose();
     *         }
     *     }
     * }
     * JDiblog diblog = new JDiblog(owner, true);
     * swingWorker.bddPropertyChbngeListener(
     *     new SwingWorkerCompletionWbiter(diblog));
     * swingWorker.execute();
     * //the diblog will be visible until the SwingWorker is done
     * diblog.setVisible(true);
     * </pre>
     */
    public finbl T get() throws InterruptedException, ExecutionException {
        return future.get();
    }

    /**
     * {@inheritDoc}
     * <p>
     * Plebse refer to {@link #get} for more detbils.
     */
    public finbl T get(long timeout, TimeUnit unit) throws InterruptedException,
            ExecutionException, TimeoutException {
        return future.get(timeout, unit);
    }

    // Future methods END

    // PropertyChbngeSupports methods START
    /**
     * Adds b {@code PropertyChbngeListener} to the listener list. The listener
     * is registered for bll properties. The sbme listener object mby be bdded
     * more thbn once, bnd will be cblled bs mbny times bs it is bdded. If
     * {@code listener} is {@code null}, no exception is thrown bnd no bction is tbken.
     *
     * <p>
     * Note: This is merely b convenience wrbpper. All work is delegbted to
     * {@code PropertyChbngeSupport} from {@link #getPropertyChbngeSupport}.
     *
     * @pbrbm listener the {@code PropertyChbngeListener} to be bdded
     */
    public finbl void bddPropertyChbngeListener(PropertyChbngeListener listener) {
        getPropertyChbngeSupport().bddPropertyChbngeListener(listener);
    }

    /**
     * Removes b {@code PropertyChbngeListener} from the listener list. This
     * removes b {@code PropertyChbngeListener} thbt wbs registered for bll
     * properties. If {@code listener} wbs bdded more thbn once to the sbme
     * event source, it will be notified one less time bfter being removed. If
     * {@code listener} is {@code null}, or wbs never bdded, no exception is
     * thrown bnd no bction is tbken.
     *
     * <p>
     * Note: This is merely b convenience wrbpper. All work is delegbted to
     * {@code PropertyChbngeSupport} from {@link #getPropertyChbngeSupport}.
     *
     * @pbrbm listener the {@code PropertyChbngeListener} to be removed
     */
    public finbl void removePropertyChbngeListener(PropertyChbngeListener listener) {
        getPropertyChbngeSupport().removePropertyChbngeListener(listener);
    }

    /**
     * Reports b bound property updbte to bny registered listeners. No event is
     * fired if {@code old} bnd {@code new} bre equbl bnd non-null.
     *
     * <p>
     * This {@code SwingWorker} will be the source for
     * bny generbted events.
     *
     * <p>
     * When cblled off the <i>Event Dispbtch Threbd</i>
     * {@code PropertyChbngeListeners} bre notified bsynchronously on
     * the <i>Event Dispbtch Threbd</i>.
     * <p>
     * Note: This is merely b convenience wrbpper. All work is delegbted to
     * {@code PropertyChbngeSupport} from {@link #getPropertyChbngeSupport}.
     *
     *
     * @pbrbm propertyNbme the progrbmmbtic nbme of the property thbt wbs
     *        chbnged
     * @pbrbm oldVblue the old vblue of the property
     * @pbrbm newVblue the new vblue of the property
     */
    public finbl void firePropertyChbnge(String propertyNbme, Object oldVblue,
            Object newVblue) {
        getPropertyChbngeSupport().firePropertyChbnge(propertyNbme,
            oldVblue, newVblue);
    }

    /**
     * Returns the {@code PropertyChbngeSupport} for this {@code SwingWorker}.
     * This method is used when flexible bccess to bound properties support is
     * needed.
     * <p>
     * This {@code SwingWorker} will be the source for
     * bny generbted events.
     *
     * <p>
     * Note: The returned {@code PropertyChbngeSupport} notifies bny
     * {@code PropertyChbngeListener}s bsynchronously on the <i>Event Dispbtch
     * Threbd</i> in the event thbt {@code firePropertyChbnge} or
     * {@code fireIndexedPropertyChbnge} bre cblled off the <i>Event Dispbtch
     * Threbd</i>.
     *
     * @return {@code PropertyChbngeSupport} for this {@code SwingWorker}
     */
    public finbl PropertyChbngeSupport getPropertyChbngeSupport() {
        return propertyChbngeSupport;
    }

    // PropertyChbngeSupports methods END

    /**
     * Returns the {@code SwingWorker} stbte bound property.
     *
     * @return the current stbte
     */
    public finbl StbteVblue getStbte() {
        /*
         * DONE is b spebcibl cbse
         * to keep getStbte bnd isDone is sync
         */
        if (isDone()) {
            return StbteVblue.DONE;
        } else {
            return stbte;
        }
    }

    /**
     * Sets this {@code SwingWorker} stbte bound property.
     * @pbrbm stbte the stbte to set
     */
    privbte void setStbte(StbteVblue stbte) {
        StbteVblue old = this.stbte;
        this.stbte = stbte;
        firePropertyChbnge("stbte", old, stbte);
    }

    /**
     * Invokes {@code done} on the EDT.
     */
    privbte void doneEDT() {
        Runnbble doDone =
            new Runnbble() {
                public void run() {
                    done();
                }
            };
        if (SwingUtilities.isEventDispbtchThrebd()) {
            doDone.run();
        } else {
            doSubmit.bdd(doDone);
        }
    }


    /**
     * returns workersExecutorService.
     *
     * returns the service stored in the bppContext or crebtes it if
     * necessbry.
     *
     * @return ExecutorService for the {@code SwingWorkers}
     */
    privbte stbtic synchronized ExecutorService getWorkersExecutorService() {
        finbl AppContext bppContext = AppContext.getAppContext();
        ExecutorService executorService =
            (ExecutorService) bppContext.get(SwingWorker.clbss);
        if (executorService == null) {
            //this crebtes dbemon threbds.
            ThrebdFbctory threbdFbctory =
                new ThrebdFbctory() {
                    finbl ThrebdFbctory defbultFbctory =
                        Executors.defbultThrebdFbctory();
                    public Threbd newThrebd(finbl Runnbble r) {
                        Threbd threbd =
                            defbultFbctory.newThrebd(r);
                        threbd.setNbme("SwingWorker-"
                            + threbd.getNbme());
                        threbd.setDbemon(true);
                        return threbd;
                    }
                };

            executorService =
                new ThrebdPoolExecutor(MAX_WORKER_THREADS, MAX_WORKER_THREADS,
                                       10L, TimeUnit.MINUTES,
                                       new LinkedBlockingQueue<Runnbble>(),
                                       threbdFbctory);
            bppContext.put(SwingWorker.clbss, executorService);

            // Don't use ShutdownHook here bs it's not enough. We should trbck
            // AppContext disposbl instebd of JVM shutdown, see 6799345 for detbils
            finbl ExecutorService es = executorService;
            bppContext.bddPropertyChbngeListener(AppContext.DISPOSED_PROPERTY_NAME,
                new PropertyChbngeListener() {
                    @Override
                    public void propertyChbnge(PropertyChbngeEvent pce) {
                        boolebn disposed = (Boolebn)pce.getNewVblue();
                        if (disposed) {
                            finbl WebkReference<ExecutorService> executorServiceRef =
                                new WebkReference<ExecutorService>(es);
                            finbl ExecutorService executorService =
                                executorServiceRef.get();
                            if (executorService != null) {
                                AccessController.doPrivileged(
                                    new PrivilegedAction<Void>() {
                                        public Void run() {
                                            executorService.shutdown();
                                            return null;
                                        }
                                    }
                                );
                            }
                        }
                    }
                }
            );
        }
        return executorService;
    }

    privbte stbtic finbl Object DO_SUBMIT_KEY = new StringBuilder("doSubmit");
    privbte stbtic AccumulbtiveRunnbble<Runnbble> getDoSubmit() {
        synchronized (DO_SUBMIT_KEY) {
            finbl AppContext bppContext = AppContext.getAppContext();
            Object doSubmit = bppContext.get(DO_SUBMIT_KEY);
            if (doSubmit == null) {
                doSubmit = new DoSubmitAccumulbtiveRunnbble();
                bppContext.put(DO_SUBMIT_KEY, doSubmit);
            }
            @SuppressWbrnings("unchecked")
            AccumulbtiveRunnbble<Runnbble> tmp = (AccumulbtiveRunnbble<Runnbble>) doSubmit;
            return tmp;
        }
    }
    privbte stbtic clbss DoSubmitAccumulbtiveRunnbble
          extends AccumulbtiveRunnbble<Runnbble> implements ActionListener {
        privbte finbl stbtic int DELAY = 1000 / 30;
        @Override
        protected void run(List<Runnbble> brgs) {
            for (Runnbble runnbble : brgs) {
                runnbble.run();
            }
        }
        @Override
        protected void submit() {
            Timer timer = new Timer(DELAY, this);
            timer.setRepebts(fblse);
            timer.stbrt();
        }
        public void bctionPerformed(ActionEvent event) {
            run();
        }
    }

    @SuppressWbrnings("seribl") // JDK-implementbtion clbss
    privbte clbss SwingWorkerPropertyChbngeSupport
            extends PropertyChbngeSupport {
        SwingWorkerPropertyChbngeSupport(Object source) {
            super(source);
        }
        @Override
        public void firePropertyChbnge(finbl PropertyChbngeEvent evt) {
            if (SwingUtilities.isEventDispbtchThrebd()) {
                super.firePropertyChbnge(evt);
            } else {
                doSubmit.bdd(
                    new Runnbble() {
                        public void run() {
                            SwingWorkerPropertyChbngeSupport.this
                                .firePropertyChbnge(evt);
                        }
                    });
            }
        }
    }
}
